-- ==============================================================================
-- CAPÍTULO 36 (PARTE 1): ATUALIZANDO MÚLTIPLAS LINHAS (UPDATING MULTIPLE ROWS)
-- A Mecânica de Atualização em Lote, O Erro 1175 (Safe Updates Mode),
-- O Operador IN e a Atualização Baseada em Outras Colunas (due_date)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 8
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_36_updating_multiple_rows_parte_1.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. CONSULTA PRÉVIA: COMO ESTÃO AS FATURAS DO CLIENTE 3?
-- ------------------------------------------------------------------------------
-- Antes de alterar qualquer linha em massa, o profissional experiente SEMPRE
-- roda um SELECT com o mesmo WHERE para ter certeza de quais linhas serão afetadas:
-- ------------------------------------------------------------------------------

SELECT 
    invoice_id,
    number,
    client_id,
    invoice_total,
    payment_total,
    invoice_date,
    due_date,
    payment_date
FROM invoices
WHERE client_id = 3;

-- ------------------------------------------------------------------------------
-- 2. O COMANDO DO AUTOR (MOSH) E O IMPACTO DO SAFE UPDATES (ERRO 1175)
-- ------------------------------------------------------------------------------
-- Cenário proposto pelo autor:
-- "Vamos atualizar todas as faturas do cliente 3:
--  definir payment_total = 40 e a data de pagamento (payment_date) igual à due_date!"
--
-- Se você executar o comando abaixo no MySQL Workbench com as configurações padrão:
--
-- UPDATE invoices
-- SET 
--     payment_total = 40,
--     payment_date = due_date
-- WHERE client_id = 3;
--
-- O MySQL Workbench rejeita a instrução e dispara o erro:
-- -> Error Code: 1175. You are using safe update mode and you tried to update 
--    a table without a WHERE that uses a KEY column.
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 3. A SOLUÇÃO PROFISSIONAL: DESATIVAR O MODO SEGURO PARA A SESSÃO
-- ------------------------------------------------------------------------------
-- O modo seguro exige que o WHERE filtre pela Chave Primária (invoice_id).
-- Como estamos filtrando por client_id (que pode ter várias faturas),
-- precisamos desativar temporariamente a trava de segurança na nossa sessão:
-- ------------------------------------------------------------------------------

-- Desativa o Safe Updates na sessão atual:
SET SQL_SAFE_UPDATES = 0;

-- Agora o UPDATE em lote é executado com sucesso:
UPDATE invoices
SET 
    payment_total = 40,
    payment_date = due_date
WHERE client_id = 3;

-- Reativa o Safe Updates para garantir a segurança das operações futuras:
SET SQL_SAFE_UPDATES = 1;

-- ------------------------------------------------------------------------------
-- 4. VERIFICANDO AS FATURAS DO CLIENTE 3 APÓS A ATUALIZAÇÃO EM MASSA
-- ------------------------------------------------------------------------------

SELECT 
    invoice_id,
    number,
    client_id,
    invoice_total,
    payment_total,
    due_date,
    payment_date
FROM invoices
WHERE client_id = 3;

-- ------------------------------------------------------------------------------
-- 5. ATUALIZANDO FATURAS DE MÚLTIPLOS CLIENTES COM O OPERADOR 'IN'
-- ------------------------------------------------------------------------------
-- E se a empresa decidir que os clientes 3 e 4 receberam um subsídio ou quitação parcial?
-- Podemos combinar o UPDATE com o operador IN para atingir múltiplos grupos:
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE invoices
SET 
    payment_total = 100,
    payment_date = due_date
WHERE client_id IN (3, 4);

SET SQL_SAFE_UPDATES = 1;

-- ------------------------------------------------------------------------------
-- 6. CONFERINDO OS DADOS DOS CLIENTES 3 E 4 APÓS O UPDATE COM 'IN'
-- ------------------------------------------------------------------------------

SELECT 
    invoice_id,
    number,
    client_id,
    invoice_total,
    payment_total,
    due_date,
    payment_date
FROM invoices
WHERE client_id IN (3, 4)
ORDER BY client_id, invoice_id;

-- ------------------------------------------------------------------------------
-- 7. ALERTA SUPREMO: E SE ESQUECERMOS A CLÁUSULA 'WHERE'?
-- ------------------------------------------------------------------------------
-- Se o Safe Updates estiver desativado (0) e executarmos:
--
-- UPDATE invoices
-- SET 
--     payment_total = 40,
--     payment_date = due_date;
--
-- TODAS as faturas da tabela no banco de dados serão alteradas indiscriminadamente!
-- É por essa razão que o MySQL Workbench mantém o Error 1175 ativado por padrão.
-- ------------------------------------------------------------------------------
