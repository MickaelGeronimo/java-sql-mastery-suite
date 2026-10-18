-- ==============================================================================
-- CAPÍTULO 21 (PARTE 3 - FINAL): O GRAND FINALE DAS CONDIÇÕES COMPOSTAS
-- 1. Self Join em Tabelas com Chave Composta (Pedidos com Itens e Quantidades Idênticas)
-- 2. A Catástrofe da Armadilha do OR em Condições Compostas
-- 3. O Desafio Supremo de Rastreamento com Compound LEFT JOIN (6 Tabelas Reunidas)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_21_compound_joins_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. SELF JOIN COMPOSTO EM TABELA COM CHAVE COMPOSTA (order_items)
-- Encontra pedidos diferentes onde os clientes compraram o mesmo produto e na mesma quantidade!
-- ------------------------------------------------------------------------------
SELECT 
    oi1.order_id AS pedido_1,
    oi2.order_id AS pedido_2,
    oi1.product_id,
    p.name AS product_name,
    oi1.quantity
FROM order_items oi1
JOIN order_items oi2 
    ON oi1.product_id = oi2.product_id
    AND oi1.quantity = oi2.quantity
    AND oi1.order_id < oi2.order_id
JOIN products p 
    ON oi1.product_id = p.product_id
ORDER BY oi1.product_id ASC, oi1.order_id ASC;

-- ------------------------------------------------------------------------------
-- 2. O DESAFIO SUPREMO DE FECHAMENTO (COMPOUND LEFT JOIN COM 6 ELEMENTOS)
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    ROUND(oi.quantity * oi.unit_price, 2) AS line_total,
    IFNULL(oin.note, '--- Sem instruções especiais ---') AS special_note,
    IFNULL(sh.name, 'Aguardando Despacho') AS shipper_name
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id
JOIN order_items oi 
    ON o.order_id = oi.order_id
JOIN products p 
    ON oi.product_id = p.product_id
LEFT JOIN order_item_notes oin
    ON oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id
LEFT JOIN shippers sh 
    ON o.shipper_id = sh.shipper_id
WHERE c.state IN ('VA', 'FL', 'CA', 'TX', 'CO')
ORDER BY line_total DESC, o.order_id ASC
LIMIT 5;
