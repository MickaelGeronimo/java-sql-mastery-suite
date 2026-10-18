-- ==============================================================================
-- CAPÍTULO 37 (PARTE 2): O EXERCÍCIO OFICIAL DO MOSH (sql_store.orders)
-- Classificação de Clientes VIP com Subconsultas, Atualização em Tabela Cruzada,
-- A Regra de Pré-Auditoria, Safe Updates e Comparativo com UPDATE JOIN
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 9 (Exercício Oficial)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_37_using_subqueries_in_updates_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. ENUNCIADO DO EXERCÍCIO OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- "No banco de dados sql_store, escreva uma instrução SQL para:
--  Atualizar os comentários (coluna 'comments') dos pedidos de quaisquer clientes
--  que tenham mais de 3.000 pontos (points > 3000).
--  Defina os comentários como 'Gold customer'!"
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 2. PASSO 1: TESTANDO A SUBCONSULTA ISOLADAMENTE (REGRA DE OURO DO MOSH)
-- ------------------------------------------------------------------------------
-- Quem são os clientes com mais de 3.000 pontos na base sql_store?
-- ------------------------------------------------------------------------------

SELECT customer_id, first_name, last_name, points
FROM customers
WHERE points > 3000;
-- -> Retorna exatamente 2 clientes VIPs:
--    Customer 5: Clemmie Barden (3.675 pontos)
--    Customer 6: Elka Twiddell (3.073 pontos)

-- ------------------------------------------------------------------------------
-- 3. PASSO 2: AUDITORIA PRÉVIA DOS PEDIDOS QUE SERÃO AFETADOS
-- ------------------------------------------------------------------------------
-- Quais pedidos pertencem aos clientes 5 e 6 antes de disparar o UPDATE?
-- ------------------------------------------------------------------------------

SELECT order_id, customer_id, order_date, comments
FROM orders
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE points > 3000
)
ORDER BY order_id;
-- -> Identificamos previamente os pedidos 1, 5, 8 e 10 (todos com comments = NULL).

-- ------------------------------------------------------------------------------
-- 4. PASSO 3: A RESOLUÇÃO OFICIAL DO MOSH COM CONTROLE DE SESSÃO
-- ------------------------------------------------------------------------------
-- Como a coluna orders.customer_id é Foreign Key (e não a PK order_id),
-- desativamos temporariamente o SQL_SAFE_UPDATES:
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE orders
SET comments = 'Gold customer'
WHERE customer_id IN (
    SELECT customer_id
    FROM customers
    WHERE points > 3000
);

SET SQL_SAFE_UPDATES = 1;

-- ------------------------------------------------------------------------------
-- 5. PASSO 4: AUDITORIA PÓS-UPDATE DE TODOS OS PEDIDOS (COM JOIN)
-- ------------------------------------------------------------------------------
-- Verificando a integridade de todos os 10 pedidos da tabela orders:
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    o.customer_id,
    c.first_name,
    c.points,
    o.comments
FROM orders o
JOIN customers c USING (customer_id)
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 6. TÓPICO AVANÇADO: O PADRÃO ALTERNATIVO 'UPDATE ... JOIN' (EXCLUSIVO DO MYSQL)
-- ------------------------------------------------------------------------------
-- No MySQL, além da subconsulta ANSI padronizada, você pode fazer um JOIN direto
-- dentro da cláusula UPDATE:
--
-- UPDATE orders o
-- JOIN customers c USING (customer_id)
-- SET o.comments = 'Gold customer'
-- WHERE c.points > 3000;
--
-- Vantagem do UPDATE com Subquery IN: É ANSI SQL puro, compatível com PostgreSQL e Oracle!
-- ------------------------------------------------------------------------------
