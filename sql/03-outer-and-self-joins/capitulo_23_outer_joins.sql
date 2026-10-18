-- ==============================================================================
-- CAPÍTULO 23 (PARTE 1): JUNÇÕES EXTERNAS (OUTER JOINS)
-- LEFT JOIN e RIGHT JOIN: Incluindo Registros Sem Correspondência e Tratando Nulos
-- Curso: SQL Completo (Mosh Hamedani)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_23_outer_joins.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O PROBLEMA DA JUNÇÃO INTERNA (INNER JOIN DESCARTA QUEM NÃO TEM PEDIDO)
-- ------------------------------------------------------------------------------
-- Dos 10 clientes cadastrados, apenas 6 fizeram compras.
-- O INNER JOIN retorna apenas os clientes com pedidos (descarta os outros 4).
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM customers c
JOIN orders o
    ON c.customer_id = o.customer_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 2. A SOLUÇÃO: LEFT JOIN (LEFT OUTER JOIN)
-- ------------------------------------------------------------------------------
-- Retorna TODOS os clientes da tabela da ESQUERDA (customers),
-- mesmo que eles NUNCA tenham feito nenhum pedido no sistema!
-- Para clientes sem pedidos, a coluna o.order_id virá como NULL.
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 3. O RIGHT JOIN (RIGHT OUTER JOIN) E A EQUIVALÊNCIA
-- ------------------------------------------------------------------------------
-- Se invertermos a ordem das tabelas no FROM, podemos usar RIGHT JOIN
-- para obter rigorosamente o mesmo resultado.
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM orders o
RIGHT JOIN customers c
    ON c.customer_id = o.customer_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 4. O EXERCÍCIO OFICIAL DO MOSH HAMEDANI: PRODUTOS E ITENS DE PEDIDOS
-- ------------------------------------------------------------------------------
-- Desafio: Listar TODOS os produtos (products), tenham eles sido vendidos ou não!
-- Exibir: product_id, name, quantity (da tabela order_items).
-- Se um produto nunca foi vendido, quantity deve vir como NULL.
SELECT 
    p.product_id,
    p.name,
    oi.quantity
FROM products p
LEFT JOIN order_items oi
    ON p.product_id = oi.product_id
ORDER BY p.product_id;
