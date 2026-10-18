-- ==============================================================================
-- CAPÍTULO 21: CONDIÇÕES DE JUNÇÃO COMPOSTAS (COMPOUND JOIN CONDITIONS)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_21_compound_join_conditions.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CONSULTA BÁSICA DO AUTOR: CONECTANDO VIA CHAVE COMPOSTA COM 'AND'
-- ------------------------------------------------------------------------------
SELECT *
FROM order_items oi
JOIN order_item_notes oin
    ON oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id;

-- ------------------------------------------------------------------------------
-- 2. SELEÇÃO DE COLUNAS ESPECÍFICAS RESOLVENDO AMBIGUIDADES
-- ------------------------------------------------------------------------------
SELECT 
    oi.order_id,
    oi.product_id,
    oi.quantity,
    oi.unit_price,
    oin.note_id,
    oin.note
FROM order_items oi
JOIN order_item_notes oin
    ON oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id;

-- ------------------------------------------------------------------------------
-- 3. EXPANDINDO PARA 3 TABELAS: ADICIONANDO O NOME DO PRODUTO (products)
-- ------------------------------------------------------------------------------
SELECT 
    oi.order_id,
    oi.product_id,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    oin.note_id,
    oin.note
FROM order_items oi
JOIN order_item_notes oin
    ON oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id
JOIN products p 
    ON oi.product_id = p.product_id
ORDER BY oi.order_id ASC, oi.product_id ASC;
