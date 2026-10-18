-- ==============================================================================
-- CAPÍTULO 20 (PARTE 2): JUNÇÕES DE MÚLTIPLAS TABELAS AVANÇADAS
-- 1. Esquema em Estrela com 4 Tabelas (orders + customers + order_statuses + shippers)
-- 2. O Perigo do Efeito Funil (Descarte de Pedidos com shipper_id NULL)
-- 3. Junção em Cadeia com Agregação Financeira (customers -> orders -> order_items)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_20_joining_multiple_tables_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. ESQUEMA EM ESTRELA COM 4 TABELAS
-- Adicionando a transportadora (shippers) à consulta de pedidos
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    os.name AS status,
    sh.name AS shipper
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id
JOIN order_statuses os 
    ON o.status = os.order_status_id
JOIN shippers sh 
    ON o.shipper_id = sh.shipper_id
ORDER BY o.order_id ASC;

-- ------------------------------------------------------------------------------
-- 2. JUNÇÃO EM CADEIA COM AGREGAÇÃO (TOTAL GASTO POR CLIENTE)
-- customers -> orders -> order_items
-- ------------------------------------------------------------------------------
SELECT 
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    COUNT(DISTINCT o.order_id) AS total_orders,
    ROUND(SUM(oi.quantity * oi.unit_price), 2) AS total_spent
FROM customers c
JOIN orders o 
    ON c.customer_id = o.customer_id
JOIN order_items oi 
    ON o.order_id = oi.order_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY total_spent DESC;
