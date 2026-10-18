-- ==============================================================================
-- CAPÍTULO 29 (PARTE 1): UNIÕES (UNIONS)
-- O Empilhamento Vertical de Linhas, As Duas Regras de Ouro e União de Tabelas Distintas
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 13
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_29_unions_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A CONSULTA OFICIAL DO AUTOR: PEDIDOS ATIVOS vs ARQUIVADOS
-- ------------------------------------------------------------------------------
-- Enquanto os JOINs juntam colunas horizontalmente (lado a lado),
-- o UNION empilha resultados verticalmente (linhas em cima de linhas).
-- Criamos um relatório classificando pedidos de 2019 como 'Active'
-- e pedidos de anos anteriores como 'Archived'.
-- ------------------------------------------------------------------------------

SELECT 
    order_id,
    order_date,
    'Active' AS status
FROM orders
WHERE order_date >= '2019-01-01'
UNION
SELECT 
    order_id,
    order_date,
    'Archived' AS status
FROM orders
WHERE order_date < '2019-01-01';

-- ------------------------------------------------------------------------------
-- 2. DEMONSTRAÇÃO DO ERRO 1222: QUANTIDADE DIFERENTE DE COLUNAS
-- ------------------------------------------------------------------------------
-- REGRA DE OURO 1: Cada SELECT dentro do UNION DEVE retornar exatamente
-- o mesmo número de colunas! Caso contrário, o MySQL cancela a consulta.
-- ------------------------------------------------------------------------------
-- SELECT order_id, order_date FROM orders
-- UNION
-- SELECT first_name FROM customers;
-- ERROR 1222 (21000): The used SELECT statements have a different number of columns

-- ------------------------------------------------------------------------------
-- 3. UNIÃO DE TABELAS COMPLETAMENTE DISTINTAS (customers + shippers)
-- ------------------------------------------------------------------------------
-- REGRA DE OURO 2: O primeiro SELECT define o nome das colunas no relatório final!
-- Aqui unimos clientes e transportadoras em uma lista única de nomes:
-- ------------------------------------------------------------------------------

SELECT first_name AS contact_name
FROM customers
UNION
SELECT name
FROM shippers;
