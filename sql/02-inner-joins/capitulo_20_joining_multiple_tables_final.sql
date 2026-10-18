-- ==============================================================================
-- CAPÍTULO 20 (PARTE 5 - FINAL): O GRAND FINALE DAS JUNÇÕES DE MÚLTIPLAS TABELAS
-- 1. O Relatório Supremo 360° com 6 Tabelas Simultâneas (Loja + Estoque)
-- 2. Controle do Otimizador com STRAIGHT_JOIN
-- 3. Índices de Cobertura (Covering Indexes) em Múltiplos Joins
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_20_joining_multiple_tables_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O RELATÓRIO EXECUTIVO GLOBAL 360° (6 TABELAS REUNIDAS)
-- 1. orders (pedido)
-- 2. customers (cliente comprador)
-- 3. order_statuses (status da entrega)
-- 4. order_items (itens vendidos)
-- 5. sql_inventory.products (catálogo do armazém)
-- 6. shippers (transportadora responsável)
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    c.city,
    c.state,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    ROUND(oi.quantity * oi.unit_price, 2) AS line_total,
    os.name AS order_status,
    IFNULL(sh.name, 'Aguardando Despacho') AS shipper_name
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id
JOIN order_statuses os 
    ON o.status = os.order_status_id
JOIN order_items oi 
    ON o.order_id = oi.order_id
JOIN sql_inventory.products p 
    ON oi.product_id = p.product_id
LEFT JOIN shippers sh 
    ON o.shipper_id = sh.shipper_id
WHERE c.state IN ('VA', 'FL', 'CA', 'TX', 'CO')
ORDER BY line_total DESC, o.order_id ASC
LIMIT 5;

-- ------------------------------------------------------------------------------
-- 2. CONTROLE DO OTIMIZADOR DE CONSULTAS COM STRAIGHT_JOIN
-- Força o MySQL a avaliar as tabelas exatamente na ordem escrita no FROM!
-- ------------------------------------------------------------------------------
SELECT STRAIGHT_JOIN
    o.order_id,
    c.first_name,
    os.name AS status
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_statuses os ON o.status = os.order_status_id
ORDER BY o.order_id ASC;
