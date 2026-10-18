-- ==============================================================================
-- CAPÍTULO 18: JUNÇÃO ENTRE MÚLTIPLOS BANCOS DE DADOS (JOINING ACROSS DATABASES)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_18_joining_across_databases.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. CASO 1: O BANCO ATIVO É O 'sql_store'
-- Apenas a tabela externa ('sql_inventory.products') precisa do prefixo do banco!
-- ------------------------------------------------------------------------------
USE sql_store;

SELECT 
    oi.order_id,
    oi.product_id,
    p.name,
    oi.quantity,
    oi.unit_price
FROM order_items oi
JOIN sql_inventory.products p
    ON oi.product_id = p.product_id
ORDER BY oi.order_id ASC, oi.product_id ASC;

-- ------------------------------------------------------------------------------
-- 2. CASO 2: O BANCO ATIVO É O 'sql_inventory'
-- Agora o banco ativo inverteu! Apenas 'sql_store.order_items' precisa do prefixo!
-- ------------------------------------------------------------------------------
USE sql_inventory;

SELECT 
    oi.order_id,
    oi.product_id,
    p.name,
    oi.quantity,
    oi.unit_price
FROM sql_store.order_items oi
JOIN products p
    ON oi.product_id = p.product_id
ORDER BY oi.order_id ASC, oi.product_id ASC;

-- ------------------------------------------------------------------------------
-- 3. CASO 3: CONSULTA STATELESS (SEM 'USE' - QUALIFICAÇÃO TOTAL)
-- Padrão de microsserviços e pools de conexão Java (ambas totalmente qualificadas):
-- ------------------------------------------------------------------------------
SELECT 
    oi.order_id,
    oi.product_id,
    p.name,
    oi.quantity,
    oi.unit_price
FROM sql_store.order_items oi
JOIN sql_inventory.products p
    ON oi.product_id = p.product_id
ORDER BY oi.order_id ASC, oi.product_id ASC;

-- ------------------------------------------------------------------------------
-- 4. DEMONSTRAÇÃO DO ERRO COMUM (ESQUECER O PREFIXO DO BANCO VIZINHO)
-- Se você rodar o código abaixo estando em 'sql_store', ele vai falhar com:
-- ERROR 1146 (42S02): Table 'sql_store.products' doesn't exist
-- ------------------------------------------------------------------------------
-- USE sql_store;
-- SELECT * FROM order_items oi JOIN products p ON oi.product_id = p.product_id;
