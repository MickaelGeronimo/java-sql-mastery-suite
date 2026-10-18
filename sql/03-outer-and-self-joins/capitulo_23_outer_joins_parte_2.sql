-- ==============================================================================
-- CAPÍTULO 23 (PARTE 2): JUNÇÕES EXTERNAS AVANÇADAS (OUTER JOINS DEEP-DIVE)
-- Anti-Joins (WHERE ... IS NULL), A Armadilha do Filtro no WHERE vs ON,
-- Agregações com COUNT(coluna) e Emulação do FULL OUTER JOIN no MySQL
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_23_outer_joins_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O PADRÃO ANTI-JOIN: ENCONTRANDO APENAS QUEM NUNCA COMPROU
-- ------------------------------------------------------------------------------
-- Para encontrar clientes inativos (prospects frios para o marketing):
-- Fazemos LEFT JOIN e filtramos WHERE o.order_id IS NULL.
SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    c.phone
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 2. ANTI-JOIN EM PRODUTOS: ENCONTRANDO O PRODUTO NUNCA VENDIDO
-- ------------------------------------------------------------------------------
-- Isola exatamente o produto 7 ('Sweet Pea Sprouts') que nunca teve vendas.
SELECT 
    p.product_id,
    p.name AS product_name,
    p.unit_price
FROM products p
LEFT JOIN order_items oi
    ON p.product_id = oi.product_id
WHERE oi.order_id IS NULL;

-- ------------------------------------------------------------------------------
-- 3. A ARMADILHA MORTAL DO FILTRO NO WHERE (TRANSFORMAÇÃO EM INNER JOIN ACIDENTAL)
-- ------------------------------------------------------------------------------
-- CUIDADO: Ao colocar um filtro da tabela da direita no WHERE:
-- Todos os clientes com NULL são descartados porque NULL >= '2018-01-01' é FALSO!
-- A consulta volta a se comportar como um INNER JOIN sem você perceber!
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    o.order_date
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
WHERE o.order_date >= '2018-01-01'
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 4. A CORREÇÃO DE ARQUITETO: FILTRANDO DENTRO DA CLÁUSULA ON
-- ------------------------------------------------------------------------------
-- Ao mover o filtro para o ON, preservamos todos os clientes inativos na esquerda!
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    o.order_date
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
    AND o.order_date >= '2018-01-01'
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 5. A ARMADILHA DO COUNT(*) vs COUNT(coluna) EM OUTER JOINS
-- ------------------------------------------------------------------------------
-- ERRADO: COUNT(*) conta a linha do NULL e diz que o cliente inativo fez 1 pedido!
-- CERTO: COUNT(o.order_id) ignora nulos e diz corretamente que ele fez 0 pedidos!
SELECT 
    c.customer_id,
    c.first_name,
    COUNT(o.order_id) AS total_orders_correto,
    COUNT(*) AS total_orders_errado
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.first_name
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 6. FULL OUTER JOIN NO MYSQL (EMULAÇÃO VIA UNION)
-- ------------------------------------------------------------------------------
-- O MySQL não possui a palavra FULL JOIN nativa.
-- Emulamos combinando LEFT JOIN e RIGHT JOIN com UNION.
SELECT c.customer_id, c.first_name, o.order_id
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id
UNION
SELECT c.customer_id, c.first_name, o.order_id
FROM customers c
RIGHT JOIN orders o ON c.customer_id = o.customer_id;
