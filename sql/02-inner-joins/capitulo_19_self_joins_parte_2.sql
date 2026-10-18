-- ==============================================================================
-- CAPÍTULO 19 (PARTE 2): AUTO-JUNÇÕES AVANÇADAS (ADVANCED SELF JOINS)
-- 1. Double Self Join (3 Níveis de Gestão: Funcionário -> Chefe -> Diretor)
-- 2. Auditoria Salarial (Funcionários que Ganham Mais que o Próprio Gerente)
-- 3. Colegas de Trabalho no Mesmo Escritório (Self Join por office_id)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_19_self_joins_parte_2.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. DOUBLE SELF JOIN: RASTREANDO 3 NÍVEIS HIERÁRQUICOS
-- ------------------------------------------------------------------------------
SELECT 
    e.employee_id,
    e.first_name AS employee,
    m.first_name AS manager,
    IFNULL(gm.first_name, '--- CEO / Topo ---') AS grand_manager
FROM employees e
JOIN employees m 
    ON e.reports_to = m.employee_id
LEFT JOIN employees gm 
    ON m.reports_to = gm.employee_id
ORDER BY e.employee_id ASC;

-- ------------------------------------------------------------------------------
-- 2. AUDITORIA SALARIAL COM SELF JOIN
-- Encontrar funcionários cujo salário supere o salário do próprio superior direto!
-- ------------------------------------------------------------------------------
SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    e.salary AS employee_salary,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name,
    m.salary AS manager_salary,
    (e.salary - m.salary) AS salary_difference
FROM employees e
JOIN employees m 
    ON e.reports_to = m.employee_id
WHERE e.salary > m.salary
ORDER BY salary_difference DESC;

-- ------------------------------------------------------------------------------
-- 3. PARES DE COLEGAS NO MESMO ESCRITÓRIO (PEER MATCHING)
-- Conecta funcionários que trabalham no mesmo 'office_id' evitando duplicidade com '<'
-- ------------------------------------------------------------------------------
SELECT 
    e1.employee_id AS emp1_id,
    CONCAT(e1.first_name, ' ', e1.last_name) AS employee_1,
    e2.employee_id AS emp2_id,
    CONCAT(e2.first_name, ' ', e2.last_name) AS employee_2,
    e1.office_id
FROM employees e1
JOIN employees e2 
    ON e1.office_id = e2.office_id 
    AND e1.employee_id < e2.employee_id
ORDER BY e1.office_id ASC, e1.employee_id ASC;
