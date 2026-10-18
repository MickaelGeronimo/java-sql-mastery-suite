-- ==============================================================================
-- CAPÍTULO 35 (PARTE 1): ATUALIZANDO UMA ÚNICA LINHA (UPDATING A SINGLE ROW)
-- A Estrutura UPDATE ... SET ... WHERE, Atualizando Múltiplas Colunas,
-- Expressões Matemáticas, Valores DEFAULT e o Cinto de Segurança (Safe Updates)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 7
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_35_updating_a_single_row_parte_1.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. A DEMONSTRAÇÃO OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- Cenário: O cliente efetuou um pagamento de $10.00 na fatura número 1 no dia '2019-03-01'.
-- Precisamos atualizar duas colunas simultaneamente: payment_total e payment_date:
-- ------------------------------------------------------------------------------

UPDATE invoices
SET 
    payment_total = 10,
    payment_date = '2019-03-01'
WHERE invoice_id = 1;

-- ------------------------------------------------------------------------------
-- 2. VERIFICANDO A FATURA 1 APÓS A ATUALIZAÇÃO
-- ------------------------------------------------------------------------------

SELECT 
    invoice_id,
    number,
    client_id,
    invoice_total,
    payment_total,
    payment_date,
    due_date
FROM invoices
WHERE invoice_id = 1;

-- ------------------------------------------------------------------------------
-- 3. REVERTENDO OU RESETANDO DADOS COM 'DEFAULT' E 'NULL'
-- ------------------------------------------------------------------------------
-- E se o pagamento foi cadastrado por engano e precisamos cancelar/estornar?
-- O autor ensina que podemos resetar colunas usando o valor DEFAULT ou NULL:
-- ------------------------------------------------------------------------------

UPDATE invoices
SET 
    payment_total = 0,
    payment_date = NULL
WHERE invoice_id = 1;

-- Verificando que a fatura 1 voltou ao estado original pendente:
SELECT invoice_id, payment_total, payment_date 
FROM invoices 
WHERE invoice_id = 1;

-- ------------------------------------------------------------------------------
-- 4. ATUALIZANDO COM EXPRESSÕES MATEMÁTICAS E OUTRAS COLUNAS
-- ------------------------------------------------------------------------------
-- O autor demonstra algo incrível: o valor do SET pode ser uma conta matemática
-- baseada nas colunas existentes da própria linha!
-- Exemplo: O cliente da fatura 3 pagou 50% do total da fatura na data de vencimento:
-- ------------------------------------------------------------------------------

UPDATE invoices
SET 
    payment_total = invoice_total * 0.5,
    payment_date = due_date
WHERE invoice_id = 3;

-- Conferindo a fatura 3 com 50% pago e data de quitação igual à data de vencimento:
SELECT 
    invoice_id,
    invoice_total,
    payment_total,
    payment_date,
    due_date
FROM invoices
WHERE invoice_id = 3;

-- ------------------------------------------------------------------------------
-- 5. O ALERTA GRAVÍSSIMO DO MOSH: A FALTA DO WHERE E O SAFE UPDATE MODE
-- ------------------------------------------------------------------------------
-- Se você esquecer a cláusula WHERE, TODAS as faturas da tabela serão alteradas!
-- O MySQL Workbench possui o 'Safe Updates' que bloqueia atualizações sem WHERE na PK:
-- ------------------------------------------------------------------------------

-- Inspecionando o modo seguro do MySQL:
SHOW VARIABLES LIKE 'sql_safe_updates';
