-- ==============================================================================
-- CAPÍTULO 24 (PARTE 5 - FINAL): O GRAND FINALE DAS JUNÇÕES EXTERNAS MULTI-TABELAS
-- O Relatório Executivo Financeiro 360°, Cálculo de Saldo Devedor com Nulos,
-- Otimização de Índices e a Armadilha do Excesso de LEFT JOIN
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_24_outer_join_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O DESAFIO SUPREMO FINANCEIRO 360° (sql_invoicing)
-- ------------------------------------------------------------------------------
-- Reúne 4 tabelas com cascading LEFT JOIN:
-- clients -> invoices -> payments -> payment_methods
-- Calcula o Saldo Devedor real (invoice_total - pagamentos).
-- Clientes sem faturas aparecem com 'Sem Faturas'.
-- Faturas sem pagamento aparecem com 'Pendente' e saldo integral.
SELECT 
    c.client_id,
    c.name AS client_name,
    c.phone,
    i.invoice_id,
    i.invoice_total,
    IFNULL(p.amount, 0.00) AS amount_paid,
    ROUND(i.invoice_total - IFNULL(p.amount, 0.00), 2) AS balance_due,
    p.date AS payment_date,
    COALESCE(pm.name, 'Não Quitado') AS payment_method
FROM clients c
LEFT JOIN invoices i 
    ON c.client_id = i.client_id
LEFT JOIN payments p 
    ON i.invoice_id = p.invoice_id
LEFT JOIN payment_methods pm 
    ON p.payment_method = pm.payment_method_id
ORDER BY c.client_id, i.invoice_id;

-- ------------------------------------------------------------------------------
-- 2. ANÁLISE DE PERFORMANCE DO OTIMIZADOR DE CONSULTAS (EXPLAIN)
-- ------------------------------------------------------------------------------
-- Demonstra como o MySQL 8.0 fixa a tabela da esquerda (clients)
-- e realiza buscas por índice (type = ref) nas tabelas subsequentes.
EXPLAIN
SELECT 
    c.name,
    i.invoice_total,
    p.amount
FROM clients c
LEFT JOIN invoices i ON c.client_id = i.client_id
LEFT JOIN payments p ON i.invoice_id = p.invoice_id;

-- ------------------------------------------------------------------------------
-- 3. A REGRA DE OURO CONTRA O 'EXCESSO DE LEFT JOIN' (OVER-JOINING)
-- ------------------------------------------------------------------------------
-- ANTI-PADRÃO: Usar LEFT JOIN em tudo por preguiça ou medo!
-- PADRÃO DE ARQUITETURA:
-- Use INNER JOIN onde a FK for NOT NULL (ex: payment_methods em payments).
-- Use LEFT JOIN onde a FK for NULLABLE (ex: payments em invoices).
