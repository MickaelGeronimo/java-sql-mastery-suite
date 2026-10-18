-- ==============================================================================
-- CAPÍTULO 19: AUTO-JUNÇÕES (SELF JOINS)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_19_self_joins.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. CONSULTA BÁSICA DO AUTOR: DESCOBRINDO O GERENTE DE CADA FUNCIONÁRIO
-- ------------------------------------------------------------------------------
SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
JOIN employees m
    ON e.reports_to = m.employee_id;

-- ------------------------------------------------------------------------------
-- 2. DEMONSTRAÇÃO DO ERRO 1066 (FALTA DE APELIDOS DISTINTOS)
-- Se você tentar juntar a tabela sem dar apelidos diferentes, o MySQL emite:
-- ERROR 1066 (42000): Not unique table/alias: 'employees'
-- ------------------------------------------------------------------------------
-- SELECT * FROM employees JOIN employees ON employees.reports_to = employees.employee_id;

-- ------------------------------------------------------------------------------
-- 3. RELATÓRIO EXECUTIVO PROFISSIONAL COMPLETO
-- Com nomes completos, cargos e ordenação
-- ------------------------------------------------------------------------------
SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    e.job_title,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name
FROM employees e
JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id ASC;

-- ------------------------------------------------------------------------------
-- 4. OUTRO CENÁRIO REAL DE SELF JOIN: ENCONTRAR DUPLICIDADES NA MESMA TABELA
-- Exemplo no banco sql_store: clientes que compartilham o mesmo telefone!
-- ------------------------------------------------------------------------------
USE sql_store;

SELECT 
    c1.customer_id AS id_cliente_1,
    CONCAT(c1.first_name, ' ', c1.last_name) AS cliente_1,
    c2.customer_id AS id_cliente_2,
    CONCAT(c2.first_name, ' ', c2.last_name) AS cliente_2,
    c1.phone
FROM customers c1
JOIN customers c2 
    ON c1.phone = c2.phone 
    AND c1.customer_id < c2.customer_id;
