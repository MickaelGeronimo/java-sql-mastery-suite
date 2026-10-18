-- ==============================================================================
-- CAPÍTULO 25 (PARTE 4 - FINAL): O GRAND FINALE DAS AUTO-JUNÇÕES EXTERNAS
-- Auditoria Salarial 360°, Gaps de Liderança, Índices B-Tree e Mapeamento JPA
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 9
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_25_self_outer_joins_final.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. AUDITORIA SALARIAL 360° COM SELF LEFT JOIN
-- ------------------------------------------------------------------------------
-- Comparamos o salário de cada colaborador com o salário de seu chefe direto.
-- Calculamos a diferença (gap salarial).
-- Usamos IFNULL para que a Presidente da empresa não fique com resultado nulo,
-- exibindo '--- (CEO / Salário Máximo)' no relatório da diretoria!
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    e.job_title,
    e.salary AS employee_salary,
    IFNULL(m.first_name, 'Nenhum (Topo)') AS manager_name,
    IFNULL(m.salary, e.salary) AS manager_salary,
    CASE 
        WHEN m.salary IS NULL THEN 0.00
        ELSE (m.salary - e.salary)
    END AS manager_salary_gap
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY manager_salary_gap DESC, e.salary DESC;

-- ------------------------------------------------------------------------------
-- 2. PARES DE COLEGAS NO MESMO ESCRITÓRIO (PEER MATCHING COM OUTER JOIN)
-- ------------------------------------------------------------------------------
-- Quem trabalha junto no mesmo escritório?
-- Se um funcionário estivesse sozinho em uma filial, o INNER JOIN o eliminaria.
-- Com LEFT JOIN, preservamos qualquer funcionário isolado!
-- Usamos 'e1.employee_id < e2.employee_id' para evitar duplicidades espelhadas.
-- ------------------------------------------------------------------------------

SELECT 
    e1.office_id,
    CONCAT(e1.first_name, ' ', e1.last_name) AS employee_1,
    IFNULL(CONCAT(e2.first_name, ' ', e2.last_name), 'Trabalha Sozinho(a)') AS office_partner
FROM employees e1
LEFT JOIN employees e2
    ON e1.office_id = e2.office_id
    AND e1.employee_id < e2.employee_id
ORDER BY e1.office_id, e1.employee_id;

-- ------------------------------------------------------------------------------
-- 3. A REGRA DE OURO DA PERFORMANCE: ÍNDICES B-TREE EM AUTO-JUNÇÕES
-- ------------------------------------------------------------------------------
-- Em tabelas gigantes de RH (ex: 200.000 funcionários da Amazon ou Petrobras),
-- fazer um SELF JOIN sem índice na coluna de amarração causa um TABLE SCAN
-- cruzado brutal: 200.000 x 200.000 = 40 BILHÕES de comparações!
-- Por isso, toda coluna de auto-relacionamento DEVE ter índice:
-- ------------------------------------------------------------------------------

-- CREATE INDEX idx_employees_reports_to ON employees (reports_to);

EXPLAIN 
SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id;
