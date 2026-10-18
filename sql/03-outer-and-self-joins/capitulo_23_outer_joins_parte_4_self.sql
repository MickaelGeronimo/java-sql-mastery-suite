-- ==============================================================================
-- CAPÍTULO 23 (PARTE 4): AUTO-JUNÇÕES EXTERNAS (SELF OUTER JOINS)
-- O Resgate do CEO, A Inversão com RIGHT JOIN e Hierarquia Multinível
-- Curso: SQL Completo (Mosh Hamedani)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_23_outer_joins_parte_4_self.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. A LIMITAÇÃO DO INNER SELF JOIN (O CEO DESAPARECE DO ORGANOGRAMA)
-- ------------------------------------------------------------------------------
-- No INNER JOIN, o CEO (Yovonnda, cujo reports_to é NULL) é descartado.
-- Apenas 4 funcionários aparecem.
SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 2. A SOLUÇÃO: SELF LEFT JOIN (PRESERVANDO TODOS OS FUNCIONÁRIOS + O CEO)
-- ------------------------------------------------------------------------------
-- Colocamos 'e' na tabela da esquerda com LEFT JOIN.
-- Todos os 5 funcionários aparecem! O CEO aparece com manager = NULL.
SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 3. TRATANDO O NULO COM IFNULL (ELEGÂNCIA CORPORATIVA)
-- ------------------------------------------------------------------------------
SELECT 
    e.employee_id,
    e.first_name,
    IFNULL(m.first_name, 'Nenhum (CEO / Presidente)') AS manager
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 4. O CONTRASTE DO SELF RIGHT JOIN (QUEM SÃO TODOS OS GERENTES?)
-- ------------------------------------------------------------------------------
-- Preserva todas as pessoas no papel de gerente (m).
-- Pessoas que não gerenciam ninguém aparecem com subordinado = NULL!
SELECT 
    e.first_name AS subordinate,
    m.first_name AS manager
FROM employees e
RIGHT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY m.first_name;

-- ------------------------------------------------------------------------------
-- 5. HIERARQUIA CORPORATIVA EM 3 NÍVEIS COM DOUBLE SELF LEFT JOIN
-- ------------------------------------------------------------------------------
-- Funcionário -> Gerente -> Diretor Geral
SELECT 
    e.first_name AS employee_name,
    IFNULL(m.first_name, 'Nenhum') AS manager_name,
    IFNULL(gm.first_name, 'Nenhum') AS general_manager_name
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id
LEFT JOIN employees gm
    ON m.reports_to = gm.employee_id
ORDER BY e.employee_id;
