-- ==============================================================================
-- CAPÍTULO 17 (PARTE 4 - FINAL): O GRAND FINALE DAS JUNÇÕES INTERNAS (INNER JOIN)
-- 1. Ordem das Tabelas e Comutatividade (Otimizador CBO e Hash Join)
-- 2. Filtro no ON vs Filtro no WHERE em INNER JOIN
-- 3. Junções Não-Equitativas (Non-Equi Joins com BETWEEN)
-- 4. O Grande Desafio Supremo de 4 Tabelas (orders + customers + order_items + products)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_17_inner_joins_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A ORDEM DAS TABELAS IMPORTA NO INNER JOIN? (COMUTATIVIDADE)
-- Ambas produzem exatamente o mesmo resultado e o mesmo plano de execução!
-- ------------------------------------------------------------------------------
-- Opção A: orders primeiro
SELECT o.order_id, c.first_name, c.last_name
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id;

-- Opção B: customers primeiro
SELECT o.order_id, c.first_name, c.last_name
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id;

-- ------------------------------------------------------------------------------
-- 2. FILTRO NO ON vs FILTRO NO WHERE (EM INNER JOIN)
-- No INNER JOIN, o otimizador trata ambas as formas de forma equivalente.
-- ------------------------------------------------------------------------------
-- Filtro no WHERE (Recomendado para clareza):
SELECT o.order_id, c.first_name, c.state
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
WHERE c.state = 'VA';

-- Filtro no ON (Funciona igual em INNER JOIN, mas perigoso em LEFT JOIN):
SELECT o.order_id, c.first_name, c.state
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id 
    AND c.state = 'VA';

-- ------------------------------------------------------------------------------
-- 3. JUNÇÕES NÃO-EQUITATIVAS (NON-EQUI JOIN)
-- Simulando faixas de classificação de clientes sem usar igualdade direta
-- ------------------------------------------------------------------------------
SELECT 
    c.customer_id,
    c.first_name,
    c.points,
    CASE 
        WHEN c.points < 1000 THEN 'Bronze'
        WHEN c.points BETWEEN 1000 AND 2000 THEN 'Silver'
        ELSE 'Gold'
    END AS tier
FROM customers c
ORDER BY c.points DESC;

-- ------------------------------------------------------------------------------
-- 4. O GRANDE DESAFIO SUPREMO DE FECHAMENTO (4 TABELAS UNIDAS)
-- Relatório executivo completo:
-- 1. orders (pedido)
-- 2. customers (cliente que comprou)
-- 3. order_items (itens comprados no pedido)
-- 4. products (nome do produto)
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    c.city,
    c.state,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    ROUND(oi.quantity * oi.unit_price, 2) AS line_total
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id
JOIN order_items oi 
    ON o.order_id = oi.order_id
JOIN products p 
    ON oi.product_id = p.product_id
WHERE c.state IN ('VA', 'FL', 'CO', 'CA', 'TX')
ORDER BY line_total DESC, o.order_id ASC
LIMIT 5;
