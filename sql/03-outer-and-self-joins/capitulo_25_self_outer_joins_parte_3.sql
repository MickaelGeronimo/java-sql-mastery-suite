-- ==============================================================================
-- CAPÍTULO 25 (PARTE 3): AUTO-JUNÇÕES EXTERNAS EM MÚLTIPLOS NÍVEIS
-- Hierarquia Corporativa Multinível (Colaborador -> Gerente -> Diretor Geral),
-- A Catástrofe das Zero Linhas e Classificação Dinâmica de Nível Hierárquico
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 9
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_25_self_outer_joins_parte_3.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. A CATÁSTROFE DAS ZERO LINHAS: A QUEBRA DA CORRENTE NO 3º NÍVEL
-- ------------------------------------------------------------------------------
-- Observe este erro clássico de desenvolvedores inexperientes:
-- O programador usa LEFT JOIN para trazer o gerente ('m'), mas comete o deslize
-- de usar 'JOIN' (INNER) para buscar o chefe do chefe ('gm').
-- Resultado estarrecedor: ZERO LINHAS RETORNADAS (0 rows in set)!
-- A consulta apaga toda a empresa da tela porque o chefe de todos é Yovonnda,
-- e o campo reports_to dela é NULL!
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name AS employee,
    m.first_name AS manager,
    gm.first_name AS grand_manager
FROM employees e
LEFT JOIN employees m 
    ON e.reports_to = m.employee_id
JOIN employees gm 
    ON m.reports_to = gm.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 2. A SOLUÇÃO CORRETA: CASCADING DOUBLE SELF LEFT JOIN
-- ------------------------------------------------------------------------------
-- Se o primeiro elo foi opcional (LEFT JOIN), o segundo elo hierárquico
-- OBRIGATORIAMENTE deve ser LEFT JOIN para não quebrar a corrente!
-- Todos os 10 funcionários voltam a aparecer intactos.
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    e.first_name AS employee,
    IFNULL(m.first_name, 'Nenhum (Topo)') AS manager,
    IFNULL(gm.first_name, 'Nenhum (Chefe Imediato é o Topo)') AS grand_manager
FROM employees e
LEFT JOIN employees m 
    ON e.reports_to = m.employee_id
LEFT JOIN employees gm 
    ON m.reports_to = gm.employee_id
ORDER BY e.employee_id;

-- ------------------------------------------------------------------------------
-- 3. CLASSIFICAÇÃO DINÂMICA DE CARGO COM EXPRESSÃO CONDICIONAL (CASE WHEN)
-- ------------------------------------------------------------------------------
-- Identificando automaticamente o escalão de cada colaborador:
--   - Nível 1: CEO / Diretoria Executiva (não responde a ninguém)
--   - Nível 2: Média Gestão / Subordinado direto da Presidência
--   - Nível 3: Equipe Operacional / Especialista
-- ------------------------------------------------------------------------------

SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    e.job_title,
    IFNULL(m.first_name, '---') AS immediate_boss,
    CASE 
        WHEN e.reports_to IS NULL THEN '👑 Nível 1: Diretoria Executiva (CEO)'
        WHEN m.reports_to IS NULL THEN '👔 Nível 2: Reporte Direto à Presidência'
        ELSE '💻 Nível 3: Operacional / Terceiro Escalão'
    END AS hierarchy_tier
FROM employees e
LEFT JOIN employees m 
    ON e.reports_to = m.employee_id
ORDER BY e.employee_id;
