-- ==============================================================================
-- CAPÍTULO 27 (PARTE 2 - FINAL): O GRAND FINALE DAS JUNÇÕES NATURAIS
-- Os 2 Grandes Desastres do NATURAL JOIN (Produto Cartesiano e Colisão de Preços),
-- Por Que Grandes Empresas Banem Esse Recurso e O Dicionário Supremo de Joins
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 11
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_27_natural_joins_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. DESASTRE 1: NENHUMA COLUNA COMUM = PRODUTO CARTESIANO ACIDENTAL (CROSS JOIN)
-- ------------------------------------------------------------------------------
-- A tabela 'orders' tem: order_id, customer_id, status...
-- A tabela 'order_statuses' tem: order_status_id, name...
-- Como não há NENHUMA coluna com o mesmo nome, o NATURAL JOIN não avisa erro:
-- ele gera um PRODUTO CARTESIANO COMPLETO (10 pedidos x 3 status = 30 linhas)!
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    os.name AS status_name
FROM orders o
NATURAL JOIN order_statuses os
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. DESASTRE 2: COLISÃO DE COLUNAS HOMÔNIMAS (HISTÓRICO DE PREÇOS DESTRUÍDO)
-- ------------------------------------------------------------------------------
-- A tabela 'order_items' tem: product_id E unit_price.
-- A tabela 'products' tem: product_id E unit_price.
-- O NATURAL JOIN junta em:
--   order_items.product_id = products.product_id
--   AND order_items.unit_price = products.unit_price
-- Se o preço do produto no estoque mudar, as vendas do passado DESAPARECEM do relatório!
-- ------------------------------------------------------------------------------

SELECT 
    oi.order_id,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price
FROM order_items oi
NATURAL JOIN products p
ORDER BY oi.order_id;

-- ------------------------------------------------------------------------------
-- 3. A SOLUÇÃO PROFISSIONAL: EXPLICITUDE COM 'ON' OU 'USING'
-- ------------------------------------------------------------------------------
-- Nunca deixe o banco adivinhar! Declare exatamente o que deseja:
-- ------------------------------------------------------------------------------

-- Para orders e order_statuses:
SELECT 
    o.order_id,
    os.name AS status_name
FROM orders o
JOIN order_statuses os
    ON o.status = os.order_status_id
ORDER BY o.order_id;

-- Para order_items e products:
SELECT 
    oi.order_id,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price
FROM order_items oi
JOIN products p
    USING (product_id)
ORDER BY oi.order_id;
