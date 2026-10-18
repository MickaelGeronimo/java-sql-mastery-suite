-- ==============================================================================
-- CAPÍTULO 21 (PARTE 2): CONDIÇÕES DE JUNÇÃO COMPOSTAS AVANÇADAS
-- 1. Compound LEFT JOIN (Preservando Itens sem Anotações Personalizadas)
-- 2. Junção Composta Híbrida (Igualdade de Código + Intervalo de Datas)
-- 3. A Regra do Prefixo Mais à Esquerda (Leftmost Prefix Rule) em Índices Compostos
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_21_compound_joins_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. COMPOUND LEFT JOIN: TODOS OS ITENS DE PEDIDOS, COM OU SEM ANOTAÇÕES
-- ------------------------------------------------------------------------------
SELECT 
    oi.order_id,
    oi.product_id,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    IFNULL(oin.note, '--- Sem anotação ---') AS note
FROM order_items oi
JOIN products p 
    ON oi.product_id = p.product_id
LEFT JOIN order_item_notes oin
    ON oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id
ORDER BY oi.order_id ASC, oi.product_id ASC;

-- ------------------------------------------------------------------------------
-- 2. JUNÇÃO COMPOSTA HÍBRIDA (EQUI-JOIN + INTERVALO TEMPORAL)
-- Exemplo conceitual de busca de preços históricos por período de vigência:
-- ------------------------------------------------------------------------------
-- SELECT 
--     o.order_id,
--     oi.product_id,
--     o.order_date,
--     hp.price AS historical_price
-- FROM orders o
-- JOIN order_items oi ON o.order_id = oi.order_id
-- JOIN historical_prices hp 
--     ON oi.product_id = hp.product_id
--     AND o.order_date BETWEEN hp.start_date AND hp.end_date;
