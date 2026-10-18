-- ==============================================================================
-- CAPÍTULO 29 (PARTE 6 FINAL SUPREMO): UNIONS RECURSIVAS E AUDITORIA TEMPORAL
-- O Ápice da Álgebra Relacional: WITH RECURSIVE + UNION ALL para Árvores Hierárquicas,
-- Histórico Temporal Unificado e o Fechamento Definitivo da Seção 3
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 13
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_29_unions_parte_6_final.sql
-- ==============================================================================

USE sql_hr;

-- ------------------------------------------------------------------------------
-- 1. O PODER SUPREMO DO UNION ALL: CTEs RECURSIVAS (WITH RECURSIVE)
-- ------------------------------------------------------------------------------
-- Em vez de escrever 5 ou 10 LEFT JOINs manuais para descobrir a hierarquia da empresa,
-- usamos uma CTE Recursiva cujo motor central é exatamente o operador 'UNION ALL'.
-- A consulta possui duas metades coladas pelo UNION ALL:
--   A) Âncora (Anchor): Pega a raiz da árvore (o CEO, reports_to IS NULL) no nível 1.
--   B) Recursão (Recursive Member): Junta os subordinados com o nível anterior (+1).
-- ------------------------------------------------------------------------------

WITH RECURSIVE employee_hierarchy AS (
    -- 1. Âncora: O Presidente / CEO (Nível 1)
    SELECT 
        employee_id,
        first_name,
        last_name,
        reports_to,
        1 AS level,
        CAST(CONCAT(first_name, ' ', last_name) AS CHAR(255)) AS management_path
    FROM employees
    WHERE reports_to IS NULL

    UNION ALL

    -- 2. Membro Recursivo: Cada subordinado do nível imediatamente anterior
    SELECT 
        e.employee_id,
        e.first_name,
        e.last_name,
        e.reports_to,
        eh.level + 1 AS level,
        CAST(CONCAT(eh.management_path, ' -> ', e.first_name, ' ', e.last_name) AS CHAR(255)) AS management_path
    FROM employees e
    JOIN employee_hierarchy eh
        ON e.reports_to = eh.employee_id
)
SELECT 
    employee_id,
    CONCAT(REPEAT('   ', level - 1), first_name, ' ', last_name) AS organizational_chart,
    level,
    management_path
FROM employee_hierarchy
ORDER BY management_path;

-- ------------------------------------------------------------------------------
-- 2. AUDITORIA TEMPORAL: UNINDO DADOS ATUAIS COM DADOS ARQUIVADOS
-- ------------------------------------------------------------------------------
-- Em arquiteturas enterprise, pedidos arquivados vão para tabelas de cold storage.
-- Para que a auditoria fiscal veja uma linha do tempo única, usamos UNION ALL:
-- ------------------------------------------------------------------------------

USE sql_store;

SELECT 
    order_id,
    customer_id,
    order_date,
    'LIVE_PRODUCTION' AS storage_partition
FROM orders
WHERE order_date >= '2019-01-01'
UNION ALL
SELECT 
    order_id,
    customer_id,
    order_date,
    'COLD_ARCHIVE' AS storage_partition
FROM orders
WHERE order_date < '2019-01-01'
ORDER BY order_date DESC;
