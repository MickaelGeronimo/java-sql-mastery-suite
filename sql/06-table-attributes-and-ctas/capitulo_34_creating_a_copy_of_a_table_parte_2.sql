-- ==============================================================================
-- CAPÍTULO 34 (PARTE 2): O EXERCÍCIO OFICIAL DO MOSH (sql_invoicing)
-- Criação da Tabela 'invoices_archived' com JOIN entre invoices e clients,
-- Desnormalização de Dados, Renomeação de Colunas (Aliases) e Filtro payment_date IS NOT NULL
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 6
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_34_creating_a_copy_of_a_table_parte_2.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. ENUNCIADO DO EXERCÍCIO OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- "No banco de dados sql_invoicing, crie uma cópia dos registros da tabela invoices.
--  Chame a nova tabela de 'invoices_archived'.
--  No lugar da coluna client_id, queremos ter a coluna com o nome do cliente ('client').
--  Portanto, você precisará fazer um JOIN entre as tabelas invoices e clients,
--  e copiar apenas as faturas que possuem data de pagamento (payment_date IS NOT NULL)!"
-- ------------------------------------------------------------------------------

-- Limpando qualquer versão anterior da tabela:
DROP TABLE IF EXISTS invoices_archived;

-- ------------------------------------------------------------------------------
-- 2. A RESOLUÇÃO OFICIAL: CTAS COM JOIN E FILTRO
-- ------------------------------------------------------------------------------
-- Veja a mágica:
--   1. O alias 'c.name AS client' define o nome físico da coluna na nova tabela!
--   2. O JOIN combina duas tabelas relacionais em uma única tabela desnormalizada!
--   3. O WHERE filtra apenas faturas liquidadas!
-- ------------------------------------------------------------------------------

CREATE TABLE invoices_archived AS
SELECT 
    i.invoice_id,
    i.number,
    c.name AS client,
    i.invoice_total,
    i.payment_total,
    i.invoice_date,
    i.due_date,
    i.payment_date
FROM invoices i
JOIN clients c
    USING (client_id)
WHERE i.payment_date IS NOT NULL;

-- ------------------------------------------------------------------------------
-- 3. VERIFICANDO A TABELA DE FATURAS ARQUIVADAS CRIADA
-- ------------------------------------------------------------------------------

SELECT * 
FROM invoices_archived
ORDER BY invoice_id;

-- ------------------------------------------------------------------------------
-- 4. CONFERINDO A ESTRUTURA FÍSICA GERADA AUTOMATICAMENTE COM DESCRIBE
-- ------------------------------------------------------------------------------
-- Repare como a coluna 'client' foi criada como VARCHAR(50) herdando o tipo de c.name:
-- ------------------------------------------------------------------------------

DESCRIBE invoices_archived;

-- ------------------------------------------------------------------------------
-- 5. RELATÓRIO ANALÍTICO FINANCEIRO SOBRE A TABELA ARQUIVADA
-- ------------------------------------------------------------------------------
-- Calculando o total arrecadado por cliente a partir da nova tabela:
-- ------------------------------------------------------------------------------

SELECT 
    client,
    COUNT(invoice_id) AS total_invoices_paid,
    SUM(invoice_total) AS total_invoiced,
    SUM(payment_total) AS total_paid
FROM invoices_archived
GROUP BY client
ORDER BY total_paid DESC;
