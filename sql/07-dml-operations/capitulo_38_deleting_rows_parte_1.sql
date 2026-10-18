-- ==============================================================================
-- CAPÍTULO 38 (PARTE 1): EXCLUINDO LINHAS (DELETING ROWS)
-- A Sintaxe DELETE FROM, Exclusão Unitária, Exclusão em Lote,
-- Uso de Subconsultas no DELETE, Safe Updates (Erro 1175) e a Falta do WHERE
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 10
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_38_deleting_rows_parte_1.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. CONSULTA PRÉVIA: LOCALIZANDO A FATURA 1 ANTES DA EXCLUSÃO
-- ------------------------------------------------------------------------------
-- Regra de Ouro do DBA: Antes de deletar qualquer registro físico,
-- SEMPRE faça um SELECT para inspecionar exatamente o que será destruído!
-- ------------------------------------------------------------------------------

SELECT invoice_id, number, client_id, invoice_total, payment_total
FROM invoices
WHERE invoice_id = 1;

-- ------------------------------------------------------------------------------
-- 2. EXCLUSÃO CIRÚRGICA DE UMA ÚNICA LINHA (CHAVE PRIMÁRIA)
-- ------------------------------------------------------------------------------
-- Como estamos filtrando pela Chave Primária (invoice_id = 1),
-- o comando é executado mesmo com o SQL_SAFE_UPDATES ativado!
-- ------------------------------------------------------------------------------

DELETE FROM invoices
WHERE invoice_id = 1;

-- Verificando que a fatura 1 foi fisicamente removida:
SELECT invoice_id, number, client_id, invoice_total 
FROM invoices 
WHERE invoice_id = 1;
-- -> Retorna: Empty set (0.00 sec)

-- ------------------------------------------------------------------------------
-- 3. EXCLUSÃO COM SUBCONSULTA (EXEMPLO OFICIAL DO MOSH)
-- ------------------------------------------------------------------------------
-- Cenário proposto pelo autor:
-- "Vamos excluir todas as faturas do cliente chamado 'Myworks'!"
--
-- Passo 3.1: Testando a subconsulta isolada:
SELECT client_id 
FROM clients 
WHERE name = 'Myworks';
-- -> Retorna client_id = 2

-- Passo 3.2: Pré-auditoria das faturas da 'Myworks':
SELECT invoice_id, number, client_id, invoice_total
FROM invoices
WHERE client_id = (
    SELECT client_id 
    FROM clients 
    WHERE name = 'Myworks'
);

-- Passo 3.3: Executando a exclusão com subconsulta e controle de modo seguro:
-- Como estamos filtrando por client_id (que não é PK de invoices),
-- desativamos temporariamente o Safe Updates:
SET SQL_SAFE_UPDATES = 0;

DELETE FROM invoices
WHERE client_id = (
    SELECT client_id 
    FROM clients 
    WHERE name = 'Myworks'
);

SET SQL_SAFE_UPDATES = 1;

-- Passo 3.4: Conferindo que não resta nenhuma fatura da 'Myworks':
SELECT invoice_id, number, client_id 
FROM invoices 
WHERE client_id = 2;
-- -> Retorna: Empty set (0.00 sec)

-- ------------------------------------------------------------------------------
-- 4. O ALERTA SUPREMO: E SE ESQUECERMOS A CLÁUSULA 'WHERE'?
-- ------------------------------------------------------------------------------
-- Se você executar:
--
-- DELETE FROM invoices;
--
-- TODAS as faturas da tabela serão apagadas do banco de dados!
-- O Safe Updates existe justamente para impedir essa tragédia acidental.
-- ------------------------------------------------------------------------------
