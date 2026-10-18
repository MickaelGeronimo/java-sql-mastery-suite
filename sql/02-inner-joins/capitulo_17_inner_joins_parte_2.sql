-- ==============================================================================
-- CAPÍTULO 17 (PARTE 2): INNER JOINS AVANÇADOS
-- 1. Junção Entre Múltiplos Bancos (Joining Across Databases)
-- 2. Auto-Junção (Self Joins)
-- 3. Junção de Múltiplas Tabelas (3 ou mais tabelas)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_17_inner_joins_parte_2.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. JUNÇÃO ENTRE MÚLTIPLOS BANCOS DE DADOS (CROSS-DATABASE JOIN)
-- ------------------------------------------------------------------------------
-- Conectando a tabela 'order_items' (do banco sql_store)
-- com a tabela 'products' (do banco sql_inventory)
USE sql_store;

SELECT 
    oi.order_id,
    oi.product_id,
    p.name,
    oi.quantity,
    oi.unit_price
FROM order_items oi
JOIN sql_inventory.products p
    ON oi.product_id = p.product_id;

-- ------------------------------------------------------------------------------
-- 2. AUTO-JUNÇÃO (SELF JOIN): CONECTANDO UMA TABELA COM ELA MESMA
-- ------------------------------------------------------------------------------
-- Na tabela 'employees' do banco sql_hr, cada funcionário tem a coluna 'reports_to',
-- que aponta para o 'employee_id' do seu respectivo chefe!
USE sql_hr;

SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id ASC;

-- ------------------------------------------------------------------------------
-- 3. JUNÇÃO DE MÚLTIPLAS TABELAS (3 TABELAS EM CADEIA NO sql_store)
-- ------------------------------------------------------------------------------
USE sql_store;

SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name,
    os.name AS status
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
JOIN order_statuses os
    ON o.status = os.order_status_id
ORDER BY o.order_id ASC;

-- ------------------------------------------------------------------------------
-- 4. O EXERCÍCIO OFICIAL DE MÚLTIPLAS TABELAS (sql_invoicing)
-- Unir: payments, clients e payment_methods
-- ------------------------------------------------------------------------------
USE sql_invoicing;

SELECT 
    p.date,
    p.invoice_id,
    p.amount,
    c.name AS client,
    pm.name AS payment_method
FROM payments p
JOIN clients c
    ON p.client_id = c.client_id
JOIN payment_methods pm
    ON p.payment_method = pm.payment_method_id
ORDER BY p.date ASC;
