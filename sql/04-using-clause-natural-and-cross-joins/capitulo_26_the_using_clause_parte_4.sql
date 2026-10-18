-- ==============================================================================
-- CAPÍTULO 26 (PARTE 4): A CLÁUSULA USING (THE USING CLAUSE)
-- A Mega-Refatoração de 6 Tabelas, Tabelas Derivadas (Subqueries) com USING,
-- A Armadilha dos Falsos Cognatos e Modelagem de Banco com jOOQ
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 10
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_26_the_using_clause_parte_4.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A MEGA-REFATORAÇÃO CORPORATIVA DE 6 TABELAS COM USING
-- ------------------------------------------------------------------------------
-- Observe como uma consulta monumental de produção fica 4x mais limpa:
-- 4 junções usam 'USING' e apenas 1 usa 'ON' (pois 'status' != 'order_status_id')!
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    ROUND(oi.quantity * oi.unit_price, 2) AS line_total,
    os.name AS order_status,
    IFNULL(sh.name, 'Aguardando Despacho') AS shipper_name
FROM orders o
JOIN customers c 
    USING (customer_id)
JOIN order_items oi 
    USING (order_id)
JOIN sql_inventory.products p 
    USING (product_id)
LEFT JOIN shippers sh 
    USING (shipper_id)
JOIN order_statuses os 
    ON o.status = os.order_status_id
WHERE o.order_id <= 3
ORDER BY o.order_id, p.name;

-- ------------------------------------------------------------------------------
-- 2. TABELAS DERIVADAS (SUBQUERIES NO FROM) COM A CLÁUSULA USING
-- ------------------------------------------------------------------------------
-- Quando uma subquery projeta uma coluna com o mesmo nome (ex: client_id),
-- podemos conectá-la à tabela principal usando USING (client_id)!
-- ------------------------------------------------------------------------------

USE sql_invoicing;

SELECT 
    c.client_id,
    c.name AS client_name,
    tot.total_invoices,
    tot.sum_invoiced
FROM clients c
JOIN (
    SELECT 
        client_id,
        COUNT(*) AS total_invoices,
        ROUND(SUM(invoice_total), 2) AS sum_invoiced
    FROM invoices
    GROUP BY client_id
) tot 
    USING (client_id)
ORDER BY tot.sum_invoiced DESC;

-- ------------------------------------------------------------------------------
-- 3. A ARMADILHA DOS FALSOS COGNATOS (THE FALSE HOMONYM TRAP)
-- ------------------------------------------------------------------------------
-- CUIDADO: Se duas tabelas têm colunas com nomes genéricos idênticos (ex: 'name'
-- ou 'date'), JAMAIS faça USING nelas a menos que queira cruzar o mesmo texto!
-- Exemplo do perigo:
--   SELECT * FROM products JOIN shippers USING (name);
-- O MySQL tentará encontrar um produto cujo nome seja IGUAL ao nome da transportadora!
-- ------------------------------------------------------------------------------
