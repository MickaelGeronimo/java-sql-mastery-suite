-- ==============================================================================
-- CAPÍTULO 37 (PARTE 1): USANDO SUBCONSULTAS EM ATUALIZAÇÕES (USING SUBQUERIES IN UPDATES)
-- Subconsultas Escalares (com =), O Erro 1242 (Subquery returns more than 1 row),
-- O Operador IN para Múltiplos Registros e a Regra de Ouro da Pré-Auditoria
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 9
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_37_using_subqueries_in_updates_parte_1.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O PROBLEMA DO MUNDO REAL: NÃO SABEMOS O ID DO CLIENTE!
-- ------------------------------------------------------------------------------
-- Em sistemas reais, um operador do financeiro não diz:
-- "Atualize as faturas do client_id = 2".
-- Ele diz:
-- "O cliente 'Myworks' pagou 50 dólares em suas faturas hoje. Atualize o sistema!"
--
-- Como fazemos para descobrir o ID do cliente de forma dinâmica no banco?
-- Executamos uma subconsulta na tabela 'clients':
-- ------------------------------------------------------------------------------

-- Testando a subconsulta isoladamente (Regra de Ouro do Mosh):
SELECT client_id 
FROM clients 
WHERE name = 'Myworks';
-- -> Retorna o valor escalar único: 2

-- ------------------------------------------------------------------------------
-- 2. AUDITORIA PRÉVIA: QUAIS FATURAS PERTENCEM À 'Myworks' (client_id = 2)?
-- ------------------------------------------------------------------------------

SELECT invoice_id, number, client_id, invoice_total, payment_total, due_date, payment_date
FROM invoices
WHERE client_id = (
    SELECT client_id 
    FROM clients 
    WHERE name = 'Myworks'
);

-- ------------------------------------------------------------------------------
-- 3. A ATUALIZAÇÃO COM SUBCONSULTA ESCALAR (OPERADOR IGUAL =)
-- ------------------------------------------------------------------------------
-- Como a subconsulta retorna exatamente 1 único valor (escalar), podemos usar
-- o operador de igualdade '=' com total segurança:
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE invoices
SET 
    payment_total = 50,
    payment_date = due_date
WHERE client_id = (
    SELECT client_id 
    FROM clients 
    WHERE name = 'Myworks'
);

SET SQL_SAFE_UPDATES = 1;

-- Verificando o resultado na fatura da Myworks:
SELECT invoice_id, number, client_id, payment_total, payment_date
FROM invoices
WHERE client_id = (
    SELECT client_id 
    FROM clients 
    WHERE name = 'Myworks'
);

-- ------------------------------------------------------------------------------
-- 4. O ERRO 1242: E SE A SUBCONSULTA RETORNAR MÚLTIPLAS LINHAS?
-- ------------------------------------------------------------------------------
-- Cenário proposto pelo autor Mosh:
-- "Queremos atualizar as faturas de todos os clientes dos estados da Califórnia (CA)
--  e de Nova York (NY)!"
--
-- Primeiro, inspecionamos a subconsulta:
SELECT client_id, name, state 
FROM clients 
WHERE state IN ('CA', 'NY');
-- -> Retorna 3 clientes: Vinte (1, NY), Myworks (2, NY) e Yadel (3, CA)

-- Se tentarmos usar o operador de igualdade '=':
-- UPDATE invoices
-- SET payment_total = 50, payment_date = due_date
-- WHERE client_id = (
--     SELECT client_id 
--     FROM clients 
--     WHERE state IN ('CA', 'NY')
-- );
-- -> ERROR 1242 (21000): Subquery returns more than 1 row
-- MOTIVO: O operador '=' espera 1 único valor à direita, mas a subconsulta trouxe 3 valores!

-- ------------------------------------------------------------------------------
-- 5. A CORREÇÃO OFICIAL COM O OPERADOR 'IN'
-- ------------------------------------------------------------------------------
-- Para suportar múltiplos valores vindos de uma subconsulta, trocamos '=' por 'IN':
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE invoices
SET 
    payment_total = 50,
    payment_date = due_date
WHERE client_id IN (
    SELECT client_id 
    FROM clients 
    WHERE state IN ('CA', 'NY')
);

SET SQL_SAFE_UPDATES = 1;

-- ------------------------------------------------------------------------------
-- 6. AUDITORIA PÓS-UPDATE DE TODAS AS FATURAS DOS CLIENTES DE 'CA' E 'NY'
-- ------------------------------------------------------------------------------

SELECT 
    i.invoice_id,
    i.number,
    c.name AS client_name,
    c.state,
    i.payment_total,
    i.payment_date
FROM invoices i
JOIN clients c USING (client_id)
WHERE c.state IN ('CA', 'NY')
ORDER BY c.state, c.name, i.invoice_id;
