-- ==============================================================================
-- CAPÍTULO 23 (PARTE 5 - FINAL): O GRAND FINALE DAS JUNÇÕES EXTERNAS
-- O Desafio Supremo 360° de 6 Tabelas, Otimização de Índices no LEFT JOIN
-- e o Dicionário Definitivo de Outer Joins para Engenheiros de Software
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_23_outer_joins_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O DESAFIO SUPREMO DE 6 TABELAS COM OUTER JOINS
-- ------------------------------------------------------------------------------
-- Reúne:
-- 1. customers (todos os 10 clientes, ativos e inativos)
-- 2. orders (pedidos com ou sem transportadora)
-- 3. order_items (itens de compra)
-- 4. products (todos os produtos)
-- 5. shippers (transportadoras)
-- 6. order_statuses (status do pedido)
SELECT 
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    c.phone,
    o.order_id,
    o.order_date,
    COALESCE(os.name, 'Sem Histórico') AS status_name,
    COALESCE(p.name, 'Nenhum Item') AS product_name,
    oi.quantity,
    oi.unit_price,
    COALESCE(sh.name, 'Aguardando Despacho') AS shipper_name
FROM customers c
LEFT JOIN orders o 
    ON c.customer_id = o.customer_id
LEFT JOIN order_statuses os 
    ON o.status = os.order_status_id
LEFT JOIN order_items oi 
    ON o.order_id = oi.order_id
LEFT JOIN products p 
    ON oi.product_id = p.product_id
LEFT JOIN shippers sh 
    ON o.shipper_id = sh.shipper_id
ORDER BY c.customer_id, o.order_id, p.product_id;

-- ------------------------------------------------------------------------------
-- 2. O DESAFIO CORPORATIVO DE FATURAMENTO (sql_invoicing)
-- ------------------------------------------------------------------------------
-- Clientes -> Faturas -> Pagamentos -> Métodos de Pagamento
USE sql_invoicing;

SELECT 
    c.client_id,
    c.name AS client_name,
    i.invoice_id,
    i.invoice_total,
    p.date AS payment_date,
    p.amount AS payment_amount,
    COALESCE(pm.name, 'Não Pago / Pendente') AS payment_method
FROM clients c
LEFT JOIN invoices i
    ON c.client_id = i.client_id
LEFT JOIN payments p
    ON i.invoice_id = p.invoice_id
LEFT JOIN payment_methods pm
    ON p.payment_method = pm.payment_method_id
ORDER BY c.client_id, i.invoice_id;

-- ------------------------------------------------------------------------------
-- 3. PLANO DE EXECUÇÃO E ÍNDICES NO LEFT JOIN (EXPLAIN)
-- ------------------------------------------------------------------------------
-- O MySQL NÃO PODE reordenar tabelas em um LEFT JOIN!
-- A tabela da esquerda SEMPRE será lida primeiro.
-- Portanto, a tabela da direita DEVE ter índice na coluna de amarração!
EXPLAIN
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM sql_store.customers c
LEFT JOIN sql_store.orders o 
    ON c.customer_id = o.customer_id;
