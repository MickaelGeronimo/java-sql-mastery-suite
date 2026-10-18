-- ==============================================================================
-- CAPÍTULO 20 (PARTE 4): MULTI-JOINS AVANÇADOS COM TABELAS ASSOCIATIVAS E DERIVADAS
-- 1. Resolução Many-to-Many (N:M) via Tabela Associativa (orders -> items -> products)
-- 2. Multi-Join com Tabela Derivada (Subconsulta no JOIN para evitar Fan-Out)
-- 3. Classificação de Tiers com CASE WHEN após Multi-Join Agregado
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_20_joining_multiple_tables_parte_4.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. NAVEGAÇÃO COMPLETA NA TABELA ASSOCIATIVA (MANY-TO-MANY RESOLVIDO)
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    ROUND(oi.quantity * oi.unit_price, 2) AS subtotal
FROM orders o
JOIN order_items oi 
    ON o.order_id = oi.order_id
JOIN products p 
    ON oi.product_id = p.product_id
ORDER BY o.order_id ASC, oi.product_id ASC;

-- ------------------------------------------------------------------------------
-- 2. MULTI-JOIN COM TABELA DERIVADA (DERIVED TABLE WRAPPER)
-- Evita a multiplicação de linhas (Fan-Out) agregando os itens ANTES de juntar com clientes!
-- ------------------------------------------------------------------------------
SELECT 
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    c.city,
    c.state,
    totals.total_spent,
    CASE 
        WHEN totals.total_spent < 80 THEN 'Bronze Tier'
        WHEN totals.total_spent BETWEEN 80 AND 120 THEN 'Silver Tier'
        ELSE 'Gold Tier'
    END AS customer_tier
FROM customers c
JOIN (
    SELECT 
        o.customer_id,
        ROUND(SUM(oi.quantity * oi.unit_price), 2) AS total_spent
    FROM orders o
    JOIN order_items oi 
        ON o.order_id = oi.order_id
    GROUP BY o.customer_id
) totals 
    ON c.customer_id = totals.customer_id
ORDER BY totals.total_spent DESC;
