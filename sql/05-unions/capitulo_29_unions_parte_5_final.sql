-- ==============================================================================
-- CAPÍTULO 29 (PARTE 5 FINAL): O GRANDE PROJETO DE CONSOLIDAÇÃO MULTI-BANCOS
-- Federação de Dados, Consolidação de Stakeholders (Store + Invoicing + HR),
-- Resolução de Collations, CTEs Agrupadas e Formatura Suprema da Seção 3
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 13 (PARTE FINAL DA SEÇÃO 3)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_29_unions_parte_5_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O DESAFIO ENTERPRISE: O FEED CONSOLIDADO DE STAKEHOLDERS DA HOLDING
-- ------------------------------------------------------------------------------
-- A diretoria da empresa precisa de uma lista única de todas as pessoas e empresas
-- com as quais mantemos contato comercial, financeiro ou empregatício.
-- Cruzamos 4 tabelas espalhadas em 3 bancos de dados distintos:
--   1. sql_store.customers   (10 clientes da loja virtual)
--   2. sql_invoicing.clients (5 clientes corporativos de faturamento)
--   3. sql_hr.employees      (10 colaboradores da folha de pagamento)
--   4. sql_store.shippers    (5 transportadoras parceiras)
-- Resultado esperado: 30 registros consolidados em streaming puro (UNION ALL)!
-- ------------------------------------------------------------------------------

SELECT 
    customer_id AS entity_id,
    CONCAT(first_name, ' ', last_name) AS entity_name,
    'Store Customer' AS role,
    'sql_store' AS origin_database
FROM sql_store.customers
UNION ALL
SELECT 
    client_id AS entity_id,
    name AS entity_name,
    'Invoicing Client' AS role,
    'sql_invoicing' AS origin_database
FROM sql_invoicing.clients
UNION ALL
SELECT 
    employee_id AS entity_id,
    CONCAT(first_name, ' ', last_name) AS entity_name,
    job_title AS role,
    'sql_hr' AS origin_database
FROM sql_hr.employees
UNION ALL
SELECT 
    shipper_id AS entity_id,
    name AS entity_name,
    'Logistics Shipper' AS role,
    'sql_store' AS origin_database
FROM sql_store.shippers
ORDER BY origin_database, entity_name;

-- ------------------------------------------------------------------------------
-- 2. ENGENHARIA DE CTE: RESUMO EXECUTIVO DE ENTIDADES POR SISTEMA DE ORIGEM
-- ------------------------------------------------------------------------------
-- Envelopamos a consulta quádrupla em uma Common Table Expression (CTE)
-- para extrair a volumetria exata de entidades gerenciadas por sistema:
-- ------------------------------------------------------------------------------

WITH enterprise_stakeholders AS (
    SELECT customer_id AS entity_id, CONCAT(first_name, ' ', last_name) AS entity_name, 'Store Customer' AS role, 'sql_store' AS origin_database FROM sql_store.customers
    UNION ALL
    SELECT client_id AS entity_id, name AS entity_name, 'Invoicing Client' AS role, 'sql_invoicing' AS origin_database FROM sql_invoicing.clients
    UNION ALL
    SELECT employee_id AS entity_id, CONCAT(first_name, ' ', last_name) AS entity_name, job_title AS role, 'sql_hr' AS origin_database FROM sql_hr.employees
    UNION ALL
    SELECT shipper_id AS entity_id, name AS entity_name, 'Logistics Shipper' AS role, 'sql_store' AS origin_database FROM sql_store.shippers
)
SELECT 
    origin_database,
    role,
    COUNT(*) AS total_entities
FROM enterprise_stakeholders
GROUP BY origin_database, role
ORDER BY origin_database, total_entities DESC;

-- ------------------------------------------------------------------------------
-- 3. RESOLUÇÃO DO ERRO 1267 (HY000): CONFLITO DE CHARSETS E COLLATIONS
-- ------------------------------------------------------------------------------
-- Em ambientes corporativos legados, tabelas antigas usam latin1_swedish_ci e
-- tabelas modernas usam utf8mb4_0900_ai_ci. Ao uni-las com UNION, o MySQL dispara:
-- ERROR 1267 (HY000): Illegal mix of collations for operation 'UNION'.
-- Para blindar o código contra esse erro, forçamos o collation no primeiro SELECT:
-- ------------------------------------------------------------------------------

SELECT 
    CONVERT(name USING utf8mb4) COLLATE utf8mb4_unicode_ci AS entity_name
FROM sql_invoicing.clients
UNION
SELECT 
    CONVERT(first_name USING utf8mb4) COLLATE utf8mb4_unicode_ci AS entity_name
FROM sql_store.customers;
