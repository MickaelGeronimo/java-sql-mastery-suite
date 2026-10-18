-- ==============================================================================
-- CAPÍTULO 37 (PARTE 3 - FINAL SUPREMO): SUBCONSULTAS AVANÇADAS EM ATUALIZAÇÕES
-- Subconsultas na Cláusula SET, Subconsultas Correlacionadas, O Erro 1093
-- (Can't specify target table for update) e o Dicionário Supremo
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 9 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_37_using_subqueries_in_updates_parte_3_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. SUBCONSULTA DINÂMICA NA CLÁUSULA SET
-- ------------------------------------------------------------------------------
-- Uma subconsulta não serve apenas para o WHERE!
-- Podemos usá-la dentro da cláusula SET para calcular dinamicamente o valor
-- que será gravado na coluna:
-- ------------------------------------------------------------------------------

-- Inspecionando a fatura 3 antes do cálculo:
SELECT invoice_id, client_id, invoice_total, payment_total 
FROM invoices 
WHERE invoice_id = 3;

-- ------------------------------------------------------------------------------
-- 2. O FAMOSO ERRO 1093: ATUALIZANDO UMA TABELA CONSULTANDO A SI MESMA!
-- ------------------------------------------------------------------------------
-- Suponha que queremos atualizar a fatura 3 definindo seu payment_total como
-- sendo exatamente o valor da fatura 1:
--
-- Se você tentar escrever de forma ingênua:
-- UPDATE invoices
-- SET payment_total = (SELECT invoice_total FROM invoices WHERE invoice_id = 1)
-- WHERE invoice_id = 3;
--
-- No MySQL, isso dispara o clássico:
-- -> ERROR 1093 (HY000): You can't specify target table 'invoices' for update in FROM clause
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 3. A SOLUÇÃO DE ARQUITETO PARA O ERRO 1093: A TABELA DERIVADA (DERIVED TABLE)
-- ------------------------------------------------------------------------------
-- O MySQL impede a leitura da mesma tabela que está com trava de escrita.
-- Para contornar, envelopamos a leitura em uma subconsulta derivada 'SELECT * FROM (...) AS tmp':
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE invoices
SET payment_total = (
    SELECT invoice_total 
    FROM (SELECT invoice_total FROM invoices WHERE invoice_id = 1) AS sub_table
)
WHERE invoice_id = 3;

SET SQL_SAFE_UPDATES = 1;

-- Verificando que a fatura 3 recebeu o valor de invoice_total da fatura 1:
SELECT invoice_id, client_id, invoice_total, payment_total 
FROM invoices 
WHERE invoice_id = 3;

-- ------------------------------------------------------------------------------
-- 4. SUBCONSULTA CORRELACIONADA VIA 'UPDATE ... JOIN' (MÉTODO RECOMENDADO MYSQL)
-- ------------------------------------------------------------------------------
-- E se quisermos definir o pagamento de faturas pendentes com base na MÉDIA
-- de faturas de cada cliente?
-- O padrão de alta performance no MySQL é o UPDATE com JOIN derivado:
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE invoices i
JOIN (
    SELECT client_id, ROUND(AVG(invoice_total), 2) AS media_cliente
    FROM invoices
    GROUP BY client_id
) medias USING (client_id)
SET i.payment_total = medias.media_cliente,
    i.payment_date = CURDATE()
WHERE i.payment_total = 0 AND i.client_id = 3;

SET SQL_SAFE_UPDATES = 1;

-- Auditando as faturas do cliente 3:
SELECT invoice_id, client_id, invoice_total, payment_total, payment_date
FROM invoices
WHERE client_id = 3;

-- ------------------------------------------------------------------------------
-- 5. COMPARATIVO CROSS-ENGINE: COMO O POSTGRESQL 18 TRATA ISSO?
-- ------------------------------------------------------------------------------
-- No PostgreSQL, você NÃO tem o Erro 1093! O Postgres permite ler da mesma
-- tabela que está sendo atualizada sem truque de subconsulta derivada:
--
-- UPDATE invoices
-- SET payment_total = (SELECT invoice_total FROM invoices WHERE invoice_id = 1)
-- WHERE invoice_id = 3;
-- -> Executa instantaneamente sem erro!
-- ------------------------------------------------------------------------------
