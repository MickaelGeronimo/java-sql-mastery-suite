-- ==============================================================================
-- CAPÍTULO 24 (PARTE 3): CADEIAS SERIAIS PROFUNDAS COM OUTER JOINS
-- Conectando 5 Níveis de Dependência Serial (customers -> orders -> items -> products -> notes)
-- Agregações em Outer Joins e Prevenção de Corrupção em SUM() e COUNT()
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_24_outer_join_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A CADEIA SERIAL PROFUNDA DE 5 NÍVEIS COM CASCADING LEFT JOIN
-- ------------------------------------------------------------------------------
-- Cada nível depende do anterior e pode produzir nulos:
-- Nível 1: Clientes sem pedidos (NULL nos pedidos)
-- Nível 2: Pedidos
-- Nível 3: Itens vendidos
-- Nível 4: Nome do Produto
-- Nível 5: Anotações Logísticas do Item (apenas 2 itens têm anotação!)
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    p.name AS product_name,
    oi.quantity,
    COALESCE(oin.note, 'Sem Anotações Especiais') AS logistical_note
FROM customers c
LEFT JOIN orders o 
    ON c.customer_id = o.customer_id
LEFT JOIN order_items oi 
    ON o.order_id = oi.order_id
LEFT JOIN products p 
    ON oi.product_id = p.product_id
LEFT JOIN order_item_notes oin 
    ON oi.order_id = oin.order_id 
    AND oi.product_id = oin.product_id
ORDER BY c.customer_id, o.order_id, p.product_id;

-- ------------------------------------------------------------------------------
-- 2. A ARMADILHA DO FILTRO SERIAL NO WHERE (A MORTE DA CADEIA)
-- ------------------------------------------------------------------------------
-- Se você quiser filtrar anotações que contenham 'fragile' ou data >= 2018:
-- NUNCA coloque no WHERE se quiser manter todos os clientes!
-- Mova para o ON da tabela correspondente!
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    oin.note
FROM customers c
LEFT JOIN orders o 
    ON c.customer_id = o.customer_id
LEFT JOIN order_items oi 
    ON o.order_id = oi.order_id
LEFT JOIN order_item_notes oin 
    ON oi.order_id = oin.order_id 
    AND oi.product_id = oin.product_id
    AND oin.note LIKE '%fragile%' -- Filtro no ON preserva a cadeia!
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 3. AGREGAÇÕES CORRETAS EM OUTER JOINS (SUM e COUNT DISTINCT)
-- ------------------------------------------------------------------------------
-- Para calcular o faturamento e contagem por cliente preservando inativos:
-- 1. Use COUNT(DISTINCT o.order_id) para não inflar contagem com múltiplos itens.
-- 2. Use IFNULL(SUM(...), 0) para clientes inativos não ficarem com NULL no financeiro.
SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    COUNT(DISTINCT o.order_id) AS total_pedidos,
    IFNULL(SUM(oi.quantity * oi.unit_price), 0.00) AS total_gasto
FROM customers c
LEFT JOIN orders o 
    ON c.customer_id = o.customer_id
LEFT JOIN order_items oi 
    ON o.order_id = oi.order_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY total_gasto DESC;
