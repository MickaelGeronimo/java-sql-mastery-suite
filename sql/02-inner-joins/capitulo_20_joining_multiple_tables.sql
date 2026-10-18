-- ==============================================================================
-- CAPÍTULO 20: JUNÇÃO DE MÚLTIPLAS TABELAS (JOINING MULTIPLE TABLES)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_20_joining_multiple_tables.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. EXEMPLO 1: UNINDO 3 TABELAS NO BANCO 'sql_store'
-- Conectando pedidos (orders) com clientes (customers) e status (order_statuses)
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
-- 2. O EXERCÍCIO OFICIAL DO AUTOR NO BANCO 'sql_invoicing'
-- Desafio: Juntar payments com clients e com payment_methods.
-- Retornar: date, invoice_id, amount, client (nome) e payment_method (nome).
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

-- ------------------------------------------------------------------------------
-- 3. A ARMADILHA DA AMBIGUIDADE (ERRO 1052)
-- Se você esquecer de qualificar 'c.name' ou 'pm.name', o MySQL emite:
-- ERROR 1052 (23000): Column 'name' in field list is ambiguous
-- ------------------------------------------------------------------------------
-- SELECT p.date, p.invoice_id, p.amount, name FROM payments p JOIN clients c ON p.client_id = c.client_id JOIN payment_methods pm ON p.payment_method = pm.payment_method_id;
