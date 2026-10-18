-- ==============================================================================
-- CAPÍTULO 22 (PARTE 3 - FINAL): O GRAND FINALE DA SINTAXE IMPLÍCITA
-- Refatoração Profissional de Legados, O Exercício Oficial do Mosh,
-- Diagnóstico DBA de Produto Cartesiano (KILL) e o Desafio Supremo de 5 Tabelas
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_22_implicit_join_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O EXERCÍCIO OFICIAL DO AUTOR (MOSH HAMEDANI) EM SINTAXE IMPLÍCITA
-- ------------------------------------------------------------------------------
-- Unir pagamentos (payments), clientes (clients) e métodos de pagamento (payment_methods).
SELECT 
    p.date,
    p.invoice_id,
    p.amount,
    c.name AS client_name,
    pm.name AS payment_method
FROM payments p, clients c, payment_methods pm
WHERE p.client_id = c.client_id
  AND p.payment_method = pm.payment_method_id
ORDER BY p.date;

-- ------------------------------------------------------------------------------
-- 2. REFATORAÇÃO PROFISSIONAL PARA SINTAXE EXPLÍCITA (SQL-92)
-- ------------------------------------------------------------------------------
SELECT 
    p.date,
    p.invoice_id,
    p.amount,
    c.name AS client_name,
    pm.name AS payment_method
FROM payments p
JOIN clients c
    ON p.client_id = c.client_id
JOIN payment_methods pm
    ON p.payment_method = pm.payment_method_id
ORDER BY p.date;

-- ------------------------------------------------------------------------------
-- 3. COMANDOS DO DBA PARA DETECTAR E MATAR UM PRODUTO CARTESIANO TRAVADO
-- ------------------------------------------------------------------------------
-- 1. Ver todas as conexões e queries ativas no banco:
-- SHOW FULL PROCESSLIST;

-- 2. Matar o processo que está travando o servidor (substitua pelo ID da lista):
-- KILL 12345;

-- ------------------------------------------------------------------------------
-- 4. O DESAFIO SUPREMO DE FECHAMENTO (5 TABELAS NA LOJA VIRTUAL)
-- orders + customers + order_items + products + order_statuses
-- ------------------------------------------------------------------------------
USE sql_store;

-- Versão Legada Implícita (SQL-89 - 5 tabelas e 4 condições no WHERE):
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    os.name AS status_name
FROM orders o, customers c, order_items oi, products p, order_statuses os
WHERE o.customer_id = c.customer_id
  AND o.order_id = oi.order_id
  AND oi.product_id = p.product_id
  AND o.status = os.order_status_id
ORDER BY o.order_id, p.product_id;

-- Versão Moderna Refatorada (SQL-92 - O Padrão de Engenharia Limpo):
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    os.name AS status_name
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
JOIN order_items oi
    ON o.order_id = oi.order_id
JOIN products p
    ON oi.product_id = p.product_id
JOIN order_statuses os
    ON o.status = os.order_status_id
ORDER BY o.order_id, p.product_id;
