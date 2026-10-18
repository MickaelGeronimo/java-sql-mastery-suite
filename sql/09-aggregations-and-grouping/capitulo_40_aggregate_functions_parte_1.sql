-- ==============================================================================
-- SEÇÃO 5: RESUMINDO DADOS (SUMMARIZING DATA)
-- CAPÍTULO 40 (PARTE 1): FUNÇÕES DE AGREGAÇÃO (AGGREGATE FUNCTIONS)
-- Curso: Complete SQL Mastery (Mosh Hamedani)
-- Banco de Dados: sql_invoicing
-- Arquivo: capitulo_40_aggregate_functions_parte_1.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. AS 5 GRANDES FUNÇÕES DE AGREGAÇÃO DO PADRÃO SQL (ANSI SQL)
-- ------------------------------------------------------------------------------
-- Uma função de agregação processa um conjunto de valores (uma coluna inteira)
-- e retorna um ÚNICO valor resumido.
-- - MAX(): Retorna o maior valor.
-- - MIN(): Retorna o menor valor.
-- - AVG(): Calcula a média aritmética dos valores.
-- - SUM(): Calcula o somatório matemático dos valores.
-- - COUNT(): Conta a quantidade de valores presentes.
-- ------------------------------------------------------------------------------

SELECT 
    MAX(invoice_total) AS highest,
    MIN(invoice_total) AS lowest,
    AVG(invoice_total) AS average,
    SUM(invoice_total) AS total,
    COUNT(invoice_total) AS number_of_invoices,
    COUNT(payment_date) AS count_of_payments,
    COUNT(*) AS total_records
FROM invoices;

-- ------------------------------------------------------------------------------
-- 2. AGREGAÇÕES COM FILTRAGEM VIA CLÁUSULA WHERE
-- ------------------------------------------------------------------------------
-- As funções de agregação operam APENAS sobre as linhas que passam pelo WHERE.
-- Neste exemplo, resumimos faturas emitidas a partir do segundo semestre de 2019.
-- Também aplicamos uma expressão aritmética dentro do SUM: (invoice_total * 1.1)
-- para simular um cálculo com imposto ou taxa de serviço de 10%.
-- ------------------------------------------------------------------------------

SELECT 
    MAX(invoice_total) AS highest,
    MIN(invoice_total) AS lowest,
    AVG(invoice_total) AS average,
    SUM(invoice_total * 1.1) AS total,
    COUNT(*) AS number_of_invoices
FROM invoices
WHERE invoice_date > '2019-07-01';

-- ------------------------------------------------------------------------------
-- 3. FUNÇÕES DE AGREGAÇÃO COM DATAS E STRINGS
-- ------------------------------------------------------------------------------
-- MAX() e MIN() funcionam perfeitamente com valores que NÃO são numéricos!
-- - Em colunas de data (DATE):
--     MIN() retorna a data mais antiga (no passado).
--     MAX() retorna a data mais recente (no futuro / mais nova).
-- - Em colunas de texto (VARCHAR):
--     MIN() retorna o texto alfabeticamente primeiro (ordem A-Z).
--     MAX() retorna o texto alfabeticamente último (ordem Z-A).
--
-- ATENÇÃO: AVG() e SUM() operam EXCLUSIVAMENTE em colunas numéricas!
-- ------------------------------------------------------------------------------

SELECT 
    MIN(invoice_date) AS earliest_invoice,
    MAX(invoice_date) AS latest_invoice,
    MAX(payment_date) AS latest_payment
FROM invoices;

-- ------------------------------------------------------------------------------
-- 4. O COMPORTAMENTO DE VALORES NULL NAS FUNÇÕES DE AGREGAÇÃO
-- ------------------------------------------------------------------------------
-- Regra de ouro da engenharia de banco de dados:
-- - COUNT(nome_da_coluna) IGNORA valores NULL (conta apenas células preenchidas).
-- - COUNT(*) CONTA TODAS as linhas da tabela, independentemente de serem NULL.
-- - AVG(coluna) calcula a média dividindo APENAS pelas linhas onde a coluna NÃO é NULL!
--   (AVG = SUM / COUNT(coluna), e NÃO SUM / COUNT(*)).
-- ------------------------------------------------------------------------------

SELECT 
    COUNT(*) AS total_linhas_tabela,
    COUNT(payment_date) AS faturas_com_pagamento_realizado,
    COUNT(*) - COUNT(payment_date) AS faturas_pendentes_sem_pagamento
FROM invoices;
