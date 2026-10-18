-- ==============================================================================
-- SEÇÃO 5: RESUMINDO DADOS (SUMMARIZING DATA)
-- CAPÍTULO 40 (PARTE 2): OPERADOR DISTINCT E EXERCÍCIO OFICIAL COM UNION
-- Curso: Complete SQL Mastery (Mosh Hamedani)
-- Banco de Dados: sql_invoicing
-- Arquivo: capitulo_40_aggregate_functions_parte_2.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O OPERADOR DISTINCT DENTRO DE FUNÇÕES DE AGREGAÇÃO
-- ------------------------------------------------------------------------------
-- Por padrão, COUNT(coluna) conta todas as ocorrências não-nulas, inclusive
-- valores repetidos (duplicados).
-- Quando inserimos DISTINCT dentro dos parênteses da função, o motor descarta
-- duplicatas antes de realizar a operação de contagem.
-- ------------------------------------------------------------------------------

SELECT 
    COUNT(client_id) AS total_faturas,
    COUNT(DISTINCT client_id) AS total_clientes_com_fatura
FROM invoices;

-- ------------------------------------------------------------------------------
-- 2. EXERCÍCIO OFICIAL DO MOSH HAMEDANI: RELATÓRIO FINANCEIRO SEMESTRAL
-- ------------------------------------------------------------------------------
-- O Desafio do Autor:
-- Escreva uma consulta que gere uma tabela sumária com 4 colunas:
--   1. date_range (Intervalo de datas)
--   2. total_sales (Soma de invoice_total)
--   3. total_payments (Soma de payment_total)
--   4. what_we_expect (Diferença: total_sales - total_payments)
--
-- Contendo exatamente 3 linhas combinadas com UNION:
--   - 'First half of 2019' (01/01/2019 até 30/06/2019)
--   - 'Second half of 2019' (01/07/2019 até 31/12/2019)
--   - 'Total' (Ano completo de 2019)
-- ------------------------------------------------------------------------------

SELECT 
    'First half of 2019' AS date_range,
    SUM(invoice_total) AS total_sales,
    SUM(payment_total) AS total_payments,
    SUM(invoice_total - payment_total) AS what_we_expect
FROM invoices
WHERE invoice_date BETWEEN '2019-01-01' AND '2019-06-30'

UNION

SELECT 
    'Second half of 2019' AS date_range,
    SUM(invoice_total) AS total_sales,
    SUM(payment_total) AS total_payments,
    SUM(invoice_total - payment_total) AS what_we_expect
FROM invoices
WHERE invoice_date BETWEEN '2019-07-01' AND '2019-12-31'

UNION

SELECT 
    'Total' AS date_range,
    SUM(invoice_total) AS total_sales,
    SUM(payment_total) AS total_payments,
    SUM(invoice_total - payment_total) AS what_we_expect
FROM invoices
WHERE invoice_date BETWEEN '2019-01-01' AND '2019-12-31';

-- ------------------------------------------------------------------------------
-- 3. ABORDAGEM BÔNUS DE ENGENHARIA SÊNIOR: AGREGAÇÃO CONDICIONAL (CASE WHEN)
-- ------------------------------------------------------------------------------
-- No padrão corporativo, executar 3 vezes o escaneamento da tabela via UNION
-- pode ser mais custoso em tabelas com bilhões de registros.
-- Engenheiros sêniores usam agregação condicional com CASE WHEN em uma única passada:
-- ------------------------------------------------------------------------------

SELECT 
    SUM(CASE WHEN invoice_date BETWEEN '2019-01-01' AND '2019-06-30' THEN invoice_total ELSE 0 END) AS first_half_sales,
    SUM(CASE WHEN invoice_date BETWEEN '2019-07-01' AND '2019-12-31' THEN invoice_total ELSE 0 END) AS second_half_sales,
    SUM(invoice_total) AS total_sales
FROM invoices
WHERE invoice_date BETWEEN '2019-01-01' AND '2019-12-31';
