-- ==============================================================================
-- CAPÍTULO 20 (PARTE 3 - FINAL): O GRAND FINALE DAS JUNÇÕES DE MÚLTIPLAS TABELAS
-- 1. Multi-Join Atravessando Múltiplos Bancos (sql_store + sql_inventory)
-- 2. A Armadilha do Fan-Out (Multiplicação de Linhas 1:N x 1:N)
-- 3. O Desafio Supremo de 5 Tabelas (orders + customers + order_items + products + statuses)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_20_joining_multiple_tables_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. RELATÓRIO SUPREMO DE AUDITORIA UNINDO 5 TABELAS (INCLUINDO OUTRO BANCO!)
-- ------------------------------------------------------------------------------
SELECT 
    o.order_id,
    o.order_date,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    c.city,
    c.state,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price,
    ROUND(oi.quantity * oi.unit_price, 2) AS line_total,
    ROUND((oi.quantity * oi.unit_price) * 0.10, 2) AS estimated_tax,
    ROUND((oi.quantity * oi.unit_price) * 1.10, 2) AS total_with_tax,
    os.name AS order_status
FROM orders o
JOIN customers c 
    ON o.customer_id = c.customer_id
JOIN order_items oi 
    ON o.order_id = oi.order_id
JOIN sql_inventory.products p 
    ON oi.product_id = p.product_id
JOIN order_statuses os 
    ON o.status = os.order_status_id
WHERE c.state IN ('VA', 'FL', 'CA', 'TX', 'CO')
ORDER BY total_with_tax DESC, o.order_id ASC
LIMIT 5;

-- ------------------------------------------------------------------------------
-- 2. DEMONSTRAÇÃO DO FAN-OUT TRAP (EXPLICAÇÃO TEÓRICA E PREVENÇÃO)
-- Se uma tabela mãe for ligada a duas tabelas filhas independentes:
-- Resultado = Itens x Pagamentos (Multiplicação espúria!)
-- ------------------------------------------------------------------------------
-- A regra da boa engenharia: agregue uma das tabelas via subquery antes do JOIN!
