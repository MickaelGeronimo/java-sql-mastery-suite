-- ==============================================================================
-- CAPÍTULO 17 (PARTE 3): JUNÇÕES COMPOSTAS E SINTAXE IMPLÍCITA
-- 1. Chaves Compostas e Condições de Junção Compostas (Compound Join Conditions)
-- 2. Sintaxe Implícita (SQL-89) vs Sintaxe Explícita (SQL-92)
-- 3. O Perigo Mortal do Produto Cartesiano Acidental (CROSS JOIN)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_17_inner_joins_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CONDIÇÃO DE JUNÇÃO COMPOSTA (COMPOUND JOIN CONDITION)
-- ------------------------------------------------------------------------------
-- A tabela 'order_items' tem chave primária composta: (order_id, product_id).
-- Para conectar com 'order_item_notes', precisamos ligar AMBAS as colunas usando AND!
SELECT 
    oi.order_id,
    oi.product_id,
    oi.quantity,
    oi.unit_price,
    oin.note_id,
    oin.note
FROM order_items oi
JOIN order_item_notes oin
    ON oi.order_id = oin.order_id
    AND oi.product_id = oin.product_id;

-- ------------------------------------------------------------------------------
-- 2. SINTAXE IMPLÍCITA DE JUNÇÃO (SQL-89 LEGADO)
-- ------------------------------------------------------------------------------
-- As tabelas são separadas por vírgula no FROM e a condição de junção vai no WHERE:
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name
FROM orders o, customers c
WHERE o.customer_id = c.customer_id
ORDER BY o.order_id ASC;

-- COMPARAÇÃO COM A SINTAXE EXPLÍCITA MODERNA (SQL-92 RECOMENDADA):
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
ORDER BY o.order_id ASC;

-- ------------------------------------------------------------------------------
-- 3. O PERIGO DO PRODUTO CARTESIANO ACIDENTAL (CROSS JOIN)
-- ------------------------------------------------------------------------------
-- Se você esquecer a cláusula WHERE na sintaxe implícita:
-- Cada pedido é multiplicado por TODOS os clientes (10 pedidos x 10 clientes = 100 linhas!)
SELECT 
    o.order_id,
    c.first_name
FROM orders o, customers c;
