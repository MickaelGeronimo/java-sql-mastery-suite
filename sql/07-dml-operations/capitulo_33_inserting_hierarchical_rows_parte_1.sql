-- ==============================================================================
-- CAPÍTULO 33 (PARTE 1): INSERINDO LINHAS HIERÁRQUICAS (INSERTING HIERARCHICAL ROWS)
-- Relação Pai e Filho (Parent-Child), orders vs order_items,
-- A Função Mágica LAST_INSERT_ID() e Isolamento por Conexão (Thread-Safe)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 5
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_33_inserting_hierarchical_rows_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A DEMONSTRAÇÃO OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- Cenário: O cliente 1 realizou um novo pedido na data '2019-01-02' com status 1 (Processed).
-- Como order_id é AUTO_INCREMENT, não passamos nada para ele.
-- O MySQL gera automaticamente o próximo ID da fila (ex: 11).
-- ------------------------------------------------------------------------------

INSERT INTO orders (customer_id, order_date, status)
VALUES (1, '2019-01-02', 1);

-- ------------------------------------------------------------------------------
-- 2. RECUPERANDO O ID GERADO COM A FUNÇÃO LAST_INSERT_ID()
-- ------------------------------------------------------------------------------
-- A função LAST_INSERT_ID() devolve o último valor de AUTO_INCREMENT gerado
-- EXCLUSIVAMENTE dentro desta conexão ativa (sessão do usuário).
-- ------------------------------------------------------------------------------

SELECT LAST_INSERT_ID();

-- ------------------------------------------------------------------------------
-- 3. INSERINDO OS FILHOS (order_items) USANDO LAST_INSERT_ID()
-- ------------------------------------------------------------------------------
-- Agora gravamos os produtos que compõem esse pedido.
-- A coluna order_id recebe o resultado dinâmico de LAST_INSERT_ID().
-- O cliente comprou:
--   - 1 unidade do Produto 1 por 2.95
--   - 1 unidade do Produto 2 por 3.95
-- ------------------------------------------------------------------------------

INSERT INTO order_items
VALUES 
    (LAST_INSERT_ID(), 1, 1, 2.95),
    (LAST_INSERT_ID(), 2, 1, 3.95);

-- ------------------------------------------------------------------------------
-- 4. VERIFICAÇÃO INTEGRAL: CONSULTANDO O PAI E OS FILHOS CRIADOS
-- ------------------------------------------------------------------------------

-- Visualizando o registro Pai na tabela orders:
SELECT 
    order_id,
    customer_id,
    order_date,
    status,
    comments,
    shipped_date,
    shipper_id
FROM orders
WHERE order_id = LAST_INSERT_ID();

-- Visualizando os registros Filhos na tabela order_items:
SELECT 
    order_id,
    product_id,
    quantity,
    unit_price
FROM order_items
WHERE order_id = LAST_INSERT_ID();

-- ------------------------------------------------------------------------------
-- 5. CONSULTA CONSOLIDADA COM JOIN (PAI + FILHOS EM UMA ÚNICA VISÃO)
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    (oi.quantity * oi.unit_price) AS total_item_price
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
JOIN products p ON oi.product_id = p.product_id
WHERE o.order_id = LAST_INSERT_ID();
