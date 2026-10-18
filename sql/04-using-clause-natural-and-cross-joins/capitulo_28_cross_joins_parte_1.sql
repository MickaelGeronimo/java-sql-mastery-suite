-- ==============================================================================
-- CAPÍTULO 28 (PARTE 1): JUNÇÕES CRUZADAS (CROSS JOINS)
-- O Produto Cartesiano Combinatório, Sintaxe Explícita vs Implícita e 100 Linhas
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 12
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_28_cross_joins_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A CONSULTA OFICIAL DO AUTOR (SINTAXE EXPLÍCITA COM 'CROSS JOIN')
-- ------------------------------------------------------------------------------
-- Queremos combinar CADA cliente com CADA produto da loja.
-- Não existe cláusula ON, pois não estamos comparando colunas:
-- estamos multiplicando as tabelas!
-- 10 clientes x 10 produtos = 100 LINHAS no resultado!
-- ------------------------------------------------------------------------------

SELECT 
    c.first_name AS customer,
    p.name AS product
FROM customers c
CROSS JOIN products p
ORDER BY c.first_name;

-- ------------------------------------------------------------------------------
-- 2. A SINTAXE IMPLÍCITA DO CROSS JOIN (SQL-89)
-- ------------------------------------------------------------------------------
-- Separamos as tabelas por vírgula no FROM e NÃO colocamos cláusula WHERE.
-- O resultado é rigorosamente idêntico: 100 linhas.
-- Porém, a sintaxe explícita com 'CROSS JOIN' é muito superior, pois deixa
-- a intenção do desenvolvedor 100% clara para a equipe.
-- ------------------------------------------------------------------------------

SELECT 
    c.first_name AS customer,
    p.name AS product
FROM customers c, products p
ORDER BY c.first_name;
