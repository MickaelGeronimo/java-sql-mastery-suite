-- ==============================================================================
-- CAPÍTULO 25 (PARTE 1): AUTO-JUNÇÕES EXTERNAS (SELF OUTER JOINS)
-- O Resgate do CEO no Organograma e a Eliminação do "Lixo da Memória"
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 9
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_25_self_outer_joins_parte_1.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. O PROBLEMA HISTÓRICO: O INNER JOIN QUE APAGOU O DIRETOR SUPREMO
-- ------------------------------------------------------------------------------
-- No Capítulo 19, aprendemos o SELF JOIN básico usando INNER JOIN.
-- Observe com atenção o que acontece: a pessoa de maior autoridade da empresa
-- (Yovonnda Magdalena, ID 37270) simplesmente SOME do resultado!
-- Motivo: O campo 'reports_to' dela é NULL (ela não responde a ninguém).
-- Como NULL = 37270 resulta em UNKNOWN (Falso), o INNER JOIN descarta sua linha!
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 2. A SOLUÇÃO DEFINITIVA DO AUTOR: O SELF LEFT JOIN
-- ------------------------------------------------------------------------------
-- Agora transformamos o 'JOIN' em 'LEFT JOIN'.
-- O clone da esquerda ('e' = lista de todos os funcionários) torna-se SOBERANO.
-- O MySQL percorre cada funcionário:
--   - Se o funcionário tem chefe: traz o nome do chefe da cópia 'm'.
--   - Se o funcionário NÃO tem chefe (Yovonnda): mantém a pessoa e preenche 'm' com NULL.
-- Resultado: 10 funcionários retornados (100% da empresa preservada)!
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    m.first_name AS manager
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 3. EMBELEZAMENTO CORPORATIVO COM IFNULL / COALESCE
-- ------------------------------------------------------------------------------
-- Em um painel gerencial, exibir a palavra literal "NULL" assusta diretores.
-- Usamos IFNULL (ou COALESCE no padrão ANSI SQL / PostgreSQL) para substituir
-- o valor nulo por uma descrição amigável de liderança.
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name,
    IFNULL(m.first_name, 'Nenhum (Líder Supremo / CEO)') AS manager
FROM employees e
LEFT JOIN employees m
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;
