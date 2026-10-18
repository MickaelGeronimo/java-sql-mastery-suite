-- ==============================================================================
-- CAPÍTULO 27 (PARTE 1): JUNÇÕES NATURAIS (NATURAL JOINS)
-- A Sintaxe "Automágica", A Inspeção de Colunas e O Alerta Severo do Autor
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 11
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_27_natural_joins_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A CONSULTA OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- No NATURAL JOIN, você NÃO especifica a cláusula ON e NÃO usa a cláusula USING.
-- O motor do MySQL examina sozinho os esquemas de ambas as tabelas ('orders' e 'customers'),
-- descobre que a única coluna com o mesmo nome é 'customer_id' e faz a junção por ela!
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    c.first_name
FROM orders o
NATURAL JOIN customers c
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. O COMPORTAMENTO DO SELECT * NO NATURAL JOIN
-- ------------------------------------------------------------------------------
-- Assim como na cláusula USING, o NATURAL JOIN funde a coluna comum 'customer_id'
-- e a exibe apenas UMA vez no início do resultado, sem duplicidades.
-- ------------------------------------------------------------------------------

SELECT *
FROM orders o
NATURAL JOIN customers c
WHERE o.order_id <= 3
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 3. O SEVERO ALERTA DO AUTOR: POR QUE NÃO USAR EM PRODUÇÃO?
-- ------------------------------------------------------------------------------
-- O autor Mosh Hamedani avisa textualmente no vídeo:
-- "Embora junções naturais sejam fáceis de escrever, elas podem produzir
-- resultados inesperados! Porque você deixa o banco de dados ADIVINHAR a junção.
-- Recomendo fortemente que você fique longe delas e use sempre ON ou USING!"
-- ------------------------------------------------------------------------------
