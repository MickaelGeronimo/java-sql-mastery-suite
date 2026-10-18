-- ==============================================================================
-- CAPÍTULO 26 (PARTE 5 - FINAL): O GRAND FINALE DA CLÁUSULA USING
-- O Relatório Financeiro 360° em sql_invoicing (Chaves Simples, Compostas e ON),
-- O Dicionário Supremo e Integração com Java/jOOQ
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 10
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_26_the_using_clause_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O DESAFIO SUPREMO DE FECHAMENTO: O RELATÓRIO FINANCEIRO 360°
-- ------------------------------------------------------------------------------
-- Esta consulta consolida 100% do aprendizado do Capítulo 26:
--   1. clients -> invoices: Chave simples com 'USING (client_id)'
--   2. invoices -> payments: Chave composta com 'LEFT JOIN payments USING (client_id, invoice_id)'
--   3. payments -> payment_methods: Nomes diferentes com 'LEFT JOIN ON p.payment_method = pm.payment_method_id'
-- ------------------------------------------------------------------------------

SELECT 
    client_id,
    c.name AS client_name,
    i.invoice_id,
    i.invoice_total,
    IFNULL(p.amount, 0.00) AS payment_amount,
    IFNULL(pm.name, 'Pendente / Não Pago') AS payment_method
FROM clients c
JOIN invoices i
    USING (client_id)
LEFT JOIN payments p
    USING (client_id, invoice_id)
LEFT JOIN payment_methods pm
    ON p.payment_method = pm.payment_method_id
WHERE i.invoice_id <= 6
ORDER BY client_id, i.invoice_id;

-- ------------------------------------------------------------------------------
-- 2. COMPARAÇÃO DEFINITIVA: ON vs USING vs NATURAL JOIN
-- ------------------------------------------------------------------------------
-- No próximo capítulo (Capítulo 27), o autor apresentará o NATURAL JOIN.
-- Observe as diferenças:
--   - ON: Você controla 100% das regras e nomes.
--   - USING: Você escolhe explicitamente as colunas entre parênteses.
--   - NATURAL JOIN: O banco tenta adivinhar sozinho todas as colunas com mesmo nome!
-- ------------------------------------------------------------------------------
