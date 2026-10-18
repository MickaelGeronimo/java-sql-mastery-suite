-- ==============================================================================
-- CAPÍTULO 25 (PARTE 2): AUTO-JUNÇÕES EXTERNAS (SELF OUTER JOINS)
-- A Inversão com RIGHT JOIN, A Equivalência com LEFT JOIN e o Padrão Anti-Join
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 9
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_25_self_outer_joins_parte_2.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. A INVERSÃO DIDÁTICA: O SELF RIGHT JOIN
-- ------------------------------------------------------------------------------
-- Na Parte 1, vimos 'employees e LEFT JOIN employees m'.
-- Agora, o que acontece se colocamos 'RIGHT JOIN employees m'?
-- A tabela 'm' (gerentes) torna-se a tabela SOBERANA da direita!
-- Todos os funcionários da empresa são testados no papel de gerente.
-- - Para Yovonnda (que lidera 9 pessoas): surgem 9 linhas com seus liderados.
-- - Para os demais 9 funcionários (que não lideram ninguém): surge 1 linha para
--   cada um com as colunas do subordinado ('e') preenchidas com NULL!
-- Total: 9 + 9 = 18 linhas!
-- ------------------------------------------------------------------------------

SELECT 
    m.employee_id AS manager_id,
    m.first_name AS manager_name,
    e.employee_id AS subordinate_id,
    e.first_name AS subordinate_name
FROM employees e
RIGHT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY m.employee_id, e.employee_id;

-- ------------------------------------------------------------------------------
-- 2. A BOA PRÁTICA DA ENGENHARIA: REESCREVENDO COM LEFT JOIN
-- ------------------------------------------------------------------------------
-- No mercado de trabalho e nos guias de estilo (Google SQL Style Guide),
-- o uso de RIGHT JOIN é desaconselhado pois dificulta a leitura da esquerda
-- para a direita.
-- A regra de ouro é: INVERTA AS TABELAS e use LEFT JOIN!
-- 'employees e RIGHT JOIN employees m' vira 'employees m LEFT JOIN employees e'.
-- O resultado é rigorosamente idêntico.
-- ------------------------------------------------------------------------------

SELECT 
    m.employee_id AS manager_id,
    m.first_name AS manager_name,
    e.employee_id AS subordinate_id,
    e.first_name AS subordinate_name
FROM employees m
LEFT JOIN employees e
    ON m.employee_id = e.reports_to
ORDER BY m.employee_id, e.employee_id;

-- ------------------------------------------------------------------------------
-- 3. O PADRÃO ANTI-JOIN: QUEM SÃO OS COLABORADORES QUE NÃO LIDERAM NINGUÉM?
-- ------------------------------------------------------------------------------
-- Em auditorias de RH, a diretoria quer saber: "Quais colaboradores são
-- exclusivamente operacionais / especialistas técnicos e não possuem liderados?"
-- Aplicamos o filtro 'WHERE e.employee_id IS NULL'!
-- ------------------------------------------------------------------------------

SELECT 
    m.employee_id,
    CONCAT(m.first_name, ' ', m.last_name) AS employee_name,
    m.job_title
FROM employees m
LEFT JOIN employees e
    ON m.employee_id = e.reports_to
WHERE e.employee_id IS NULL
ORDER BY m.employee_id;

-- ------------------------------------------------------------------------------
-- 4. AGROPAMENTO E CONTAGEM SEGURA: QUANTOS LIDERADOS CADA GERENTE TEM?
-- ------------------------------------------------------------------------------
-- CUIDADO: Se você usar COUNT(*), quem tem 0 subordinados será contado como 1!
-- O correto é usar COUNT(e.employee_id), pois a função COUNT ignora nulos!
-- ------------------------------------------------------------------------------

SELECT 
    m.employee_id AS manager_id,
    m.first_name AS manager_name,
    m.job_title,
    COUNT(e.employee_id) AS total_subordinates
FROM employees m
LEFT JOIN employees e
    ON m.employee_id = e.reports_to
GROUP BY m.employee_id, m.first_name, m.job_title
ORDER BY total_subordinates DESC, m.employee_id;
