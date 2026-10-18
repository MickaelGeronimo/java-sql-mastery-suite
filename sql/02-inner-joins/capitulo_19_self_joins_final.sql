-- ==============================================================================
-- CAPÍTULO 19 (PARTE 3 - FINAL): O GRAND FINALE DAS AUTO-JUNÇÕES (SELF JOIN)
-- 1. Combinando Self Join com Multi-Table Join (offices para empregado e gerente)
-- 2. Agrupamento e Métricas (Contagem de Subordinados Diretos com GROUP BY)
-- 3. CTE Recursiva Moderna (WITH RECURSIVE) para Profundidade Infinita no MySQL 8.0
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_19_self_joins_final.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. SELF JOIN COMBINADO COM MULTI-TABLE JOIN
-- Liga o funcionário ao seu escritório E o gerente ao escritório do gerente!
-- ------------------------------------------------------------------------------
SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    oe.city AS employee_city,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name,
    om.city AS manager_city,
    CASE 
        WHEN e.office_id = m.office_id THEN 'Mesmo Escritorio'
        ELSE 'Escritorio Distinto / Remoto'
    END AS situacao_trabalho
FROM employees e
JOIN employees m 
    ON e.reports_to = m.employee_id
JOIN offices oe 
    ON e.office_id = oe.office_id
JOIN offices om 
    ON m.office_id = om.office_id
ORDER BY e.employee_id ASC;

-- ------------------------------------------------------------------------------
-- 2. CONTAGEM DE SUBORDINADOS DIRETOS POR GERENTE (GROUP BY)
-- ------------------------------------------------------------------------------
SELECT 
    m.employee_id AS manager_id,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name,
    m.job_title,
    COUNT(e.employee_id) AS total_subordinates
FROM employees e
JOIN employees m 
    ON e.reports_to = m.employee_id
GROUP BY m.employee_id, m.first_name, m.last_name, m.job_title
ORDER BY total_subordinates DESC;

-- ------------------------------------------------------------------------------
-- 3. CTE RECURSIVA MODERNA (WITH RECURSIVE) NO MYSQL 8.0+
-- Desce toda a árvore hierárquica calculando o nível da pessoa na empresa!
-- ------------------------------------------------------------------------------
WITH RECURSIVE employee_hierarchy AS (
    -- Nível 1: Começa pela CEO (reports_to IS NULL)
    SELECT 
        employee_id,
        CONCAT(first_name, ' ', last_name) AS employee_name,
        reports_to,
        1 AS hierarchy_level
    FROM employees
    WHERE reports_to IS NULL

    UNION ALL

    -- Níveis seguintes: Liga os subordinados ao nível anterior
    SELECT 
        e.employee_id,
        CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
        e.reports_to,
        eh.hierarchy_level + 1
    FROM employees e
    JOIN employee_hierarchy eh 
        ON e.reports_to = eh.employee_id
)
SELECT 
    hierarchy_level,
    employee_id,
    employee_name,
    reports_to
FROM employee_hierarchy
ORDER BY hierarchy_level ASC, employee_id ASC;
