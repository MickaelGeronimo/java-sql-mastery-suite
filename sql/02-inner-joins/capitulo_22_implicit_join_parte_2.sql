-- ==============================================================================
-- CAPÍTULO 22 (PARTE 2): SINTAXE DE JUNÇÃO IMPLÍCITA AVANÇADA
-- Múltiplas Tabelas, Self Joins Implícitos, O Plano de Execução (EXPLAIN)
-- e o Desastre da Junção Cartesiana Parcial
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_22_implicit_join_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. SINTAXE IMPLÍCITA COM 3 TABELAS (orders + customers + order_statuses)
-- ------------------------------------------------------------------------------
-- Três tabelas no FROM separadas por vírgula.
-- Duas condições de amarração dentro do WHERE unidas por AND.
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name,
    os.name AS status_name
FROM orders o, customers c, order_statuses os
WHERE o.customer_id = c.customer_id
  AND o.status = os.order_status_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. A COMPARATIVO COM A SINTAXE EXPLÍCITA CORRESPONDENTE (SQL-92)
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name,
    os.name AS status_name
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
JOIN order_statuses os
    ON o.status = os.order_status_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 3. A CATÁSTROFE DA JUNÇÃO CARTESIANA PARCIAL (ESQUECENDO 1 CONDIÇÃO)
-- ------------------------------------------------------------------------------
-- O programador ligou orders com customers, mas ESQUECEU de ligar order_statuses!
-- Resultado: 10 pedidos válidos x 3 status = 30 LINHAS!
-- Cada pedido agora aparece 3 vezes: como Processed, Shipped e Delivered ao mesmo tempo!
SELECT 
    o.order_id,
    c.first_name,
    os.name AS status_name
FROM orders o, customers c, order_statuses os
WHERE o.customer_id = c.customer_id
-- O programador esqueceu: AND o.status = os.order_status_id
ORDER BY o.order_id, os.order_status_id;

-- ------------------------------------------------------------------------------
-- 4. AUTO-JUNÇÃO IMPLÍCITA (IMPLICIT SELF JOIN NA sql_hr.employees)
-- ------------------------------------------------------------------------------
USE sql_hr;

SELECT 
    e.employee_id,
    e.first_name AS employee_name,
    m.first_name AS manager_name
FROM employees e, employees m
WHERE e.reports_to = m.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 5. POR BAIXO DO CAPÔ: O PLANO DE EXECUÇÃO DO MYSQL (EXPLAIN)
-- ------------------------------------------------------------------------------
-- O otimizador do MySQL 8.0 reescreve a sintaxe implícita internamente em um INNER JOIN!
EXPLAIN
SELECT 
    o.order_id,
    c.first_name
FROM sql_store.orders o, sql_store.customers c
WHERE o.customer_id = c.customer_id;

EXPLAIN
SELECT 
    o.order_id,
    c.first_name
FROM sql_store.orders o
JOIN sql_store.customers c
    ON o.customer_id = c.customer_id;
