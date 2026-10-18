-- ==============================================================================
-- CAPÍTULO 33 (PARTE 4 - FINAL SUPREMO): O EXERCÍCIO OFICIAL, TRIGGERS EM CASCATA E AUDITORIA
-- 1. O Exercício Oficial de Fixação: Checkout Completo com Comentários e 3 Produtos
-- 2. Gatilho de Baixa Automática de Estoque (AFTER INSERT ON order_items)
-- 3. Consulta Analítica Consolidada com Subtotais e Valor Total do Pedido (SUM / GROUP BY)
-- 4. O Dicionário Supremo da Inserção Hierárquica
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 5 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_33_inserting_hierarchical_rows_parte_4_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CRIANDO UM TRIGGER DE ESTOQUE: INTEGRAÇÃO REAL ENTRE PEDIDO E CATÁLOGO
-- ------------------------------------------------------------------------------
-- Em um sistema profissional, quando um registro Filho é inserido em order_items,
-- o estoque do produto na tabela products DEVE ser subtraído automaticamente!
-- ------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tr_after_insert_order_item;

DELIMITER //

CREATE TRIGGER tr_after_insert_order_item
AFTER INSERT ON order_items
FOR EACH ROW
BEGIN
    UPDATE products
    SET quantity_in_stock = quantity_in_stock - NEW.quantity
    WHERE product_id = NEW.product_id;
END //

DELIMITER ;

-- ------------------------------------------------------------------------------
-- 2. CONFERÊNCIA DO ESTOQUE DOS PRODUTOS ANTES DA COMPRA
-- ------------------------------------------------------------------------------

SELECT product_id, name, quantity_in_stock 
FROM products 
WHERE product_id IN (1, 2, 3);

-- ------------------------------------------------------------------------------
-- 3. O EXERCÍCIO OFICIAL SUPREMO: CHECKOUT COMPLETO EM TRANSAÇÃO
-- ------------------------------------------------------------------------------
-- Enunciado:
-- "Crie um novo pedido para a cliente 5 (Clemmie Barden) com data '2019-01-15',
--  status 1 (Processed) e o comentário 'Entrega expressa prioritária'.
--  Em seguida, vincule 3 produtos a este pedido:
--    - Produto 1: 2 unidades por R$ 3.50
--    - Produto 2: 1 unidade por R$ 5.00
--    - Produto 3: 4 unidades por R$ 2.20
--  Tudo deve ser executado de forma 100% atômica (tudo ou nada)!"
-- ------------------------------------------------------------------------------

START TRANSACTION;

-- Passo 1: Inserindo o Pedido Pai
INSERT INTO orders (
    customer_id,
    order_date,
    status,
    comments
)
VALUES (
    5,
    '2019-01-15',
    1,
    'Entrega expressa prioritária'
);

-- Passo 2: Gravando o ID do Pedido com segurança em variável
SET @novo_order_id = LAST_INSERT_ID();

-- Passo 3: Inserindo os 3 Filhos em Lote
INSERT INTO order_items (
    order_id,
    product_id,
    quantity,
    unit_price
)
VALUES 
    (@novo_order_id, 1, 2, 3.50),
    (@novo_order_id, 2, 1, 5.00),
    (@novo_order_id, 3, 4, 2.20);

-- Passo 4: Confirmando a transação
COMMIT;

-- ------------------------------------------------------------------------------
-- 4. CONFERINDO O ESTOQUE DOS PRODUTOS APÓS A BAIXA DO TRIGGER
-- ------------------------------------------------------------------------------
-- O Produto 1 perdeu 2 unidades, o Produto 2 perdeu 1 e o Produto 3 perdeu 4:
-- ------------------------------------------------------------------------------

SELECT product_id, name, quantity_in_stock 
FROM products 
WHERE product_id IN (1, 2, 3);

-- ------------------------------------------------------------------------------
-- 5. RELATÓRIO ANALÍTICO FINANCEIRO DO PEDIDO CRIADO
-- ------------------------------------------------------------------------------
-- Exibe os dados do cliente, data, itens individuais, preços e o total consolidado:
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    o.comments,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    (oi.quantity * oi.unit_price) AS subtotal
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
JOIN products p ON oi.product_id = p.product_id
WHERE o.order_id = @novo_order_id;

-- Resumo Geral da Fatura (Total a Pagar):
SELECT 
    o.order_id,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    COUNT(oi.product_id) AS total_distinct_items,
    SUM(oi.quantity) AS total_items_count,
    SUM(oi.quantity * oi.unit_price) AS grand_total_price
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
WHERE o.order_id = @novo_order_id
GROUP BY o.order_id, customer_name;
