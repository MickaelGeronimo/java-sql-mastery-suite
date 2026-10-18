-- ==============================================================================
-- CAPÍTULO 29 (PARTE 2): EXERCÍCIO OFICIAL DO MOSH - CLASSIFICAÇÃO DE CLIENTES
-- Múltiplos UNIONs, Cláusula ORDER BY Global, UNION vs UNION ALL e Desempenho
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 13
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_29_unions_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O EXERCÍCIO OFICIAL DO MOSH: PROGRAMA DE FIDELIDADE (BRONZE, SILVER, GOLD)
-- ------------------------------------------------------------------------------
-- Requisitos do Autor:
-- Gerar um relatório unificado com 4 colunas:
--   customer_id, first_name, points, type
-- Regras de Negócio:
--   - points < 2000                -> type = 'Bronze'
--   - points BETWEEN 2000 AND 3000 -> type = 'Silver'
--   - points > 3000                -> type = 'Gold'
-- Ordenação:
--   - Ordenar o resultado final alfabeticamente por first_name.
-- ------------------------------------------------------------------------------

SELECT 
    customer_id,
    first_name,
    points,
    'Bronze' AS type
FROM customers
WHERE points < 2000
UNION
SELECT 
    customer_id,
    first_name,
    points,
    'Silver' AS type
FROM customers
WHERE points BETWEEN 2000 AND 3000
UNION
SELECT 
    customer_id,
    first_name,
    points,
    'Gold' AS type
FROM customers
WHERE points > 3000
ORDER BY first_name;

-- ------------------------------------------------------------------------------
-- 2. VERSÃO OTIMIZADA PARA ALTA PERFORMANCE COM UNION ALL
-- ------------------------------------------------------------------------------
-- Como as três faixas de pontuação são matematicamente disjuntas (mutuamente exclusivas),
-- é impossível existir qualquer linha duplicada entre as 3 consultas.
-- O uso de UNION ALL evita que o banco crie uma tabela temporária em memória ou
-- disco para deduplicação (DISTINCT/SORT), economizando CPU e I/O valiosos!
-- ------------------------------------------------------------------------------

SELECT 
    customer_id,
    first_name,
    points,
    'Bronze' AS type
FROM customers
WHERE points < 2000
UNION ALL
SELECT 
    customer_id,
    first_name,
    points,
    'Silver' AS type
FROM customers
WHERE points BETWEEN 2000 AND 3000
UNION ALL
SELECT 
    customer_id,
    first_name,
    points,
    'Gold' AS type
FROM customers
WHERE points > 3000
ORDER BY first_name;
