-- ==============================================================================
-- CAPÍTULO 28 (PARTE 2): A RESOLUÇÃO DO EXERCÍCIO OFICIAL DO MOSH
-- O Cruzamento de Transportadoras e Produtos (shippers x products)
-- Sintaxes Explícita e Implícita, Matriz de Fretes e 50 Linhas
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 12
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_28_cross_joins_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. EXERCÍCIO OFICIAL - PARTE 1: SINTAXE EXPLÍCITA (CROSS JOIN)
-- ------------------------------------------------------------------------------
-- Enunciado: Faça um cross join entre shippers e products:
-- 1. Usando a sintaxe explícita.
-- 5 transportadoras x 10 produtos = 50 linhas!
-- ------------------------------------------------------------------------------

SELECT 
    sh.name AS shipper,
    p.name AS product
FROM shippers sh
CROSS JOIN products p
ORDER BY sh.name;

-- ------------------------------------------------------------------------------
-- 2. EXERCÍCIO OFICIAL - PARTE 2: SINTAXE IMPLÍCITA (SQL-89)
-- ------------------------------------------------------------------------------
-- 2. Usando a sintaxe implícita com vírgula no FROM.
-- ------------------------------------------------------------------------------

SELECT 
    sh.name AS shipper,
    p.name AS product
FROM shippers sh, products p
ORDER BY sh.name;

-- ------------------------------------------------------------------------------
-- 3. APLICAÇÃO CORPORATIVA: MATRIZ DE SIMULAÇÃO DE CUSTO DE FRETE
-- ------------------------------------------------------------------------------
-- Em um e-commerce real, o Cross Join serve para simular o frete de cada produto
-- com cada transportadora parceira antes da escolha do cliente!
-- ------------------------------------------------------------------------------

SELECT 
    sh.name AS shipper,
    p.name AS product,
    p.unit_price,
    ROUND(p.unit_price * 0.15 + 5.00, 2) AS estimated_freight
FROM shippers sh
CROSS JOIN products p
ORDER BY sh.name, p.name;
