-- ==============================================================================
-- CAPÍTULO 24 (PARTE 4): JUNÇÃO EXTERNA ENTRE MÚLTIPLAS TABELAS AVANÇADA
-- Cross-Database Outer Joins, A Simetria 360° com UNION e
-- Otimização Arquitetural com Tabelas Derivadas (Derived Tables)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_24_outer_join_parte_4.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. JUNÇÃO EXTERNA ENTRE MÚLTIPLOS BANCOS DE DADOS (sql_store + sql_inventory)
-- ------------------------------------------------------------------------------
-- Conecta clientes e pedidos (sql_store) ao estoque central (sql_inventory.products).
-- Preserva clientes inativos e pedidos com itens em estoque.
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    p.name AS product_name,
    p.quantity_in_stock
FROM sql_store.customers c
LEFT JOIN sql_store.orders o 
    ON c.customer_id = o.customer_id
LEFT JOIN sql_store.order_items oi 
    ON o.order_id = oi.order_id
LEFT JOIN sql_inventory.products p 
    ON oi.product_id = p.product_id
ORDER BY c.customer_id, o.order_id;

-- ------------------------------------------------------------------------------
-- 2. O PADRÃO OURO DE ENGENHARIA: MULTI-TABELAS COM TABELA DERIVADA (SUBQUERY)
-- ------------------------------------------------------------------------------
-- Em vez de juntar tabelas e agregar depois (o que pode multiplicar linhas),
-- nós pré-agregamos o financeiro dos pedidos e fazemos um ÚNICO LEFT JOIN!
-- Extremamente rápido e à prova de falhas de Fan-Out!
SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    IFNULL(resumo.total_pedidos, 0) AS total_pedidos,
    IFNULL(resumo.total_gasto, 0.00) AS total_gasto
FROM customers c
LEFT JOIN (
    SELECT 
        o.customer_id,
        COUNT(DISTINCT o.order_id) AS total_pedidos,
        ROUND(SUM(oi.quantity * oi.unit_price), 2) AS total_gasto
    FROM orders o
    JOIN order_items oi 
        ON o.order_id = oi.order_id
    GROUP BY o.customer_id
) resumo 
    ON c.customer_id = resumo.customer_id
ORDER BY total_gasto DESC;

-- ------------------------------------------------------------------------------
-- 3. A SIMETRIA 360° COM UNION: RESGATANDO CLIENTES E PRODUTOS SEM VENDAS
-- ------------------------------------------------------------------------------
-- Lado 1: Clientes sem compras
-- Lado 2: Produtos sem vendas (Produto 7)
SELECT 
    c.first_name AS customer_name,
    o.order_id,
    p.name AS product_name
FROM customers c
LEFT JOIN orders o ON c.customer_id = o.customer_id
LEFT JOIN order_items oi ON o.order_id = oi.order_id
LEFT JOIN products p ON oi.product_id = p.product_id
WHERE o.order_id IS NULL -- Apenas clientes inativos!

UNION

SELECT 
    c.first_name AS customer_name,
    o.order_id,
    p.name AS product_name
FROM products p
LEFT JOIN order_items oi ON p.product_id = oi.product_id
LEFT JOIN orders o ON oi.order_id = o.order_id
LEFT JOIN customers c ON o.customer_id = c.customer_id
WHERE oi.order_id IS NULL; -- Apenas produtos encalhados!
