-- ==============================================================================
-- CAPÍTULO 29 (PARTE 4): TÉCNICAS AVANÇADAS DE UNIÃO E TEORIA DOS CONJUNTOS
-- Emulação de FULL OUTER JOIN, Paginação de Uniões (LIMIT/OFFSET), e a Tríade
-- Relacional: UNION, INTERSECT e EXCEPT (MySQL 8.0.31+ / PostgreSQL)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 13
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_29_unions_parte_4.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A EMULAÇÃO CANÔNICA DO FULL OUTER JOIN NO MYSQL USANDO UNION
-- ------------------------------------------------------------------------------
-- O MySQL NÃO implementa a sintaxe nativa 'FULL OUTER JOIN'.
-- Na indústria de software, quando precisamos preservar TODOS os registros da
-- esquerda E TODOS os registros da direita, unimos um LEFT JOIN com um RIGHT JOIN.
-- O operador 'UNION' (sem ALL) é fundamental aqui para eliminar a duplicação
-- dos registros que deram match em ambos os lados!
-- ------------------------------------------------------------------------------

SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM customers c
LEFT JOIN orders o 
    ON c.customer_id = o.customer_id
UNION
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM customers c
RIGHT JOIN orders o 
    ON c.customer_id = o.customer_id
ORDER BY customer_id;

-- ------------------------------------------------------------------------------
-- 2. PAGINAÇÃO ENTERPRISE DE CONSULTAS UNIFICADAS COM LIMIT E OFFSET
-- ------------------------------------------------------------------------------
-- O 'ORDER BY' e o 'LIMIT / OFFSET' colocados no rodapé atuam sobre o fluxo
-- unificado completo. Aqui paginamos a página 2 (pulando 3 e pegando os 3 seguintes):
-- ------------------------------------------------------------------------------

SELECT 
    customer_id,
    first_name,
    points,
    'Bronze' AS type
FROM customers
WHERE points < 2000
UNION ALL
SELECT 
    customer_id,
    first_name,
    points,
    'Silver' AS type
FROM customers
WHERE points BETWEEN 2000 AND 3000
UNION ALL
SELECT 
    customer_id,
    first_name,
    points,
    'Gold' AS type
FROM customers
WHERE points > 3000
ORDER BY first_name
LIMIT 3 OFFSET 3;

-- ------------------------------------------------------------------------------
-- 3. A TRÍADE RELACIONAL DA TEORIA DOS CONJUNTOS: UNION, INTERSECT E EXCEPT
-- ------------------------------------------------------------------------------
-- No MySQL 8.0.31+ e PostgreSQL, temos os 3 grandes operadores de conjuntos:
--   A) UNION: Junta todos os produtos (A união B).
--   B) INTERSECT: Produtos que existem no catálogo E foram vendidos (A inter B).
--   C) EXCEPT: Produtos que existem no catálogo MAS NUNCA foram vendidos (Estoque parado!).
-- ------------------------------------------------------------------------------

-- Exemplo C: Produtos que NUNCA foram vendidos (Estoque Encalhado):
SELECT product_id FROM products
EXCEPT
SELECT product_id FROM order_items;
