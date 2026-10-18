-- ==============================================================================
-- CAPÍTULO 17 (PARTE 1): JUNÇÃO INTERNA (INNER JOIN)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_17_inner_joins.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O PRIMEIRO JOIN: JUNTANDO PEDIDOS (orders) E CLIENTES (customers)
-- ------------------------------------------------------------------------------
-- Retorna todas as colunas de ambas as tabelas lado a lado
SELECT *
FROM orders
JOIN customers 
    ON orders.customer_id = customers.customer_id;

-- ------------------------------------------------------------------------------
-- 2. SELECIONANDO COLUNAS ESPECÍFICAS E A ARMADILHA DA AMBIGUIDADE (ERRO 1052)
-- ------------------------------------------------------------------------------
-- Se você tentar rodar o código abaixo, causará:
-- ERROR 1052 (23000): Column 'customer_id' in field list is ambiguous
-- SELECT order_id, customer_id, first_name, last_name FROM orders JOIN customers ON orders.customer_id = customers.customer_id;

-- CORREÇÃO QUALIFICANDO A COLUNA COM O NOME DA TABELA:
SELECT 
    order_id, 
    orders.customer_id, 
    first_name, 
    last_name
FROM orders
JOIN customers 
    ON orders.customer_id = customers.customer_id;

-- ------------------------------------------------------------------------------
-- 3. USANDO APELIDOS DE TABELAS (TABLE ALIASES) PARA CÓDIGO PROFISSIONAL
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id, 
    o.customer_id, 
    c.first_name, 
    c.last_name
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id;

-- ------------------------------------------------------------------------------
-- 4. O EXERCÍCIO OFICIAL DO AUTOR: UNINDO ITENS DE PEDIDOS E PRODUTOS
-- Desafio: Juntar order_items com products.
-- Retornar: order_id, product_id, name (do produto), quantity e unit_price.
-- ATENÇÃO: A coluna 'unit_price' existe em AMBAS as tabelas!
-- Devemos usar 'oi.unit_price' (o preço histórico pago no ato da compra).
-- ------------------------------------------------------------------------------
SELECT 
    oi.order_id, 
    oi.product_id, 
    p.name, 
    oi.quantity, 
    oi.unit_price
FROM order_items oi
JOIN products p 
    ON oi.product_id = p.product_id
ORDER BY oi.order_id ASC, oi.product_id ASC;
