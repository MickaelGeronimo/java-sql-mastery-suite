-- ==============================================================================
-- CAPÍTULO 33 (PARTE 2): INSERÇÃO HIERÁRQUICA AVANÇADA, TRANSAÇÕES E PROTEÇÃO CONTRA ÓRFÃOS
-- 1. O Perigo dos "Pedidos Fantasmas" (Filhos Órfãos sem Transação)
-- 2. Transações Atômicas (START TRANSACTION, COMMIT e ROLLBACK) em Hierarquias
-- 3. Violação de Chave Estrangeira (Erro 1452) e Integridade Referencial
-- 4. O Problema do Sobrescrita de LAST_INSERT_ID() e a Solução com Variáveis (@order_id)
-- 5. Hierarquia de 3 Níveis (Pai -> Filho -> Neto / orders -> order_items -> order_item_notes)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 5 (Aprofundamento)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_33_inserting_hierarchical_rows_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CRIANDO A TABELA 'NETA' (order_item_notes) PARA O TESTE MULTINÍVEL
-- ------------------------------------------------------------------------------
-- Em um e-commerce moderno, cada item do pedido pode ter observações ou
-- personalizações (ex: "Gravar nome na camiseta", "Embalar para presente"):
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS order_item_notes;

CREATE TABLE order_item_notes (
    note_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    note VARCHAR(255) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

-- ------------------------------------------------------------------------------
-- 2. CENÁRIO DE RISCO: A FALHA SEM TRANSAÇÃO (O PEDIDO FANTASMA)
-- ------------------------------------------------------------------------------
-- Se executarmos os inserts sem transação e o insert dos filhos falhar
-- (ex: por tentar comprar um produto inexistente com ID 999 - Erro 1452),
-- o registro Pai em 'orders' ficaria gravado sozinho no banco, virando lixo!
-- ------------------------------------------------------------------------------

-- Testando a violação de integridade referencial intencional:
-- INSERT INTO order_items (order_id, product_id, quantity, unit_price)
-- VALUES (11, 999, 1, 10.00); 
-- -> ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails

-- ------------------------------------------------------------------------------
-- 3. A ARQUITETURA PROFISSIONAL: TRANSAÇÃO ATÔMICA COM COMMIT
-- ------------------------------------------------------------------------------
-- Com START TRANSACTION, se qualquer linha falhar, o ROLLBACK desfaz o Pai e os Filhos!
-- Se tudo der certo, o COMMIT consolida a hierarquia completa.
-- ------------------------------------------------------------------------------

START TRANSACTION;

-- Passo 1: Insere o Pai (Pedido 12)
INSERT INTO orders (customer_id, order_date, status)
VALUES (2, '2019-01-05', 1);

-- Passo 2: O TRUQUE DE SENIOR: Guardar o LAST_INSERT_ID() em uma variável!
-- Isso protege o ID de ser sobrescrito se o próximo insert tiver auto-incremento!
SET @current_order_id = LAST_INSERT_ID();

-- Passo 3: Insere os Filhos (order_items) usando a variável segura @current_order_id
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
VALUES 
    (@current_order_id, 3, 2, 3.35),
    (@current_order_id, 4, 1, 9.00);

-- Passo 4: Insere o Neto (order_item_notes) que também possui AUTO_INCREMENT!
-- Note que aqui a tabela 'order_item_notes' gera um novo AUTO_INCREMENT (note_id = 1),
-- mas como guardamos @current_order_id, o ID do pedido NÃO foi perdido!
INSERT INTO order_item_notes (order_id, product_id, note)
VALUES 
    (@current_order_id, 3, 'Favor embalar para presente com laço azul');

-- Se tudo correu com perfeição, gravamos a hierarquia completa:
COMMIT;

-- ------------------------------------------------------------------------------
-- 4. VERIFICAÇÃO DA HIERARQUIA COMPLETA GRAVADA (PAI + FILHOS + NETO)
-- ------------------------------------------------------------------------------

SELECT * FROM orders WHERE order_id = @current_order_id;
SELECT * FROM order_items WHERE order_id = @current_order_id;
SELECT * FROM order_item_notes WHERE order_id = @current_order_id;

-- ------------------------------------------------------------------------------
-- 5. CENÁRIO DE ROLLBACK AUTOMÁTICO (ABORTANDO HIERARQUIA EM CASO DE ERRO)
-- ------------------------------------------------------------------------------

START TRANSACTION;

-- Tentamos criar um pedido 13:
INSERT INTO orders (customer_id, order_date, status)
VALUES (3, '2019-01-10', 1);

SET @tentativa_order_id = LAST_INSERT_ID();

-- Simulando que o estoque do produto 5 acabou ou uma regra de pagamento falhou no Java:
-- Em vez de gravar um pedido incompleto, executamos ROLLBACK:
ROLLBACK;

-- Provando que o pedido 13 NÃO EXISTE no banco:
SELECT * FROM orders WHERE order_id = @tentativa_order_id;
