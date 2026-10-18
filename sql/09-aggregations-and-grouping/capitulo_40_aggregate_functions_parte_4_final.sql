-- ==============================================================================
-- SEÇÃO 5: RESUMINDO DADOS (SUMMARIZING DATA)
-- CAPÍTULO 40 (PARTE 4 - FINAL SUPREMO): ERROS DE MOTOR, SUBQUERIES E DEFESA
-- Curso: Complete SQL Mastery (Mosh Hamedani)
-- Banco de Dados: sql_invoicing
-- Arquivo: capitulo_40_aggregate_functions_parte_4_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. O ERRO CLÁSSICO DE INICIANTE: ERROR 1140 (ONLY_FULL_GROUP_BY)
-- ------------------------------------------------------------------------------
-- Se você tentar colocar uma coluna individual ao lado de uma função agregada
-- SEM a cláusula GROUP BY, o motor MySQL trava com o seguinte erro:
-- ERROR 1140 (42000): In aggregated query without GROUP BY, expression #1 of 
-- SELECT list contains nonaggregated column 'sql_invoicing.invoices.client_id'; 
-- this is incompatible with sql_mode=only_full_group_by
-- ------------------------------------------------------------------------------
-- Linha com erro para teste do aluno (descomente para ver o erro):
-- SELECT client_id, SUM(invoice_total) FROM invoices;

-- ------------------------------------------------------------------------------
-- 2. O ERRO 1111: TENTAR USAR FUNÇÃO AGREGADA DIRETAMENTE NO WHERE
-- ------------------------------------------------------------------------------
-- O comando WHERE avalia as linhas UMA A UMA antes da agregação existir.
-- Por isso, você NÃO pode escrever: WHERE invoice_total > AVG(invoice_total);
-- Se tentar, o MySQL lança:
-- ERROR 1111 (HY000): Invalid use of group function
-- ------------------------------------------------------------------------------
-- Linha com erro para teste do aluno:
-- SELECT * FROM invoices WHERE invoice_total > AVG(invoice_total);

-- ------------------------------------------------------------------------------
-- 3. A SOLUÇÃO CORRETA: SUBQUERY COM FUNÇÃO DE AGREGAÇÃO
-- ------------------------------------------------------------------------------
-- O motor executa primeiro a subquery interna (SELECT AVG(invoice_total)),
-- descobre que a média é 146.99, e depois filtra as faturas acima dessa média.
-- ------------------------------------------------------------------------------

SELECT 
    invoice_id,
    number,
    client_id,
    invoice_total,
    invoice_date
FROM invoices
WHERE invoice_total > (
    SELECT AVG(invoice_total) 
    FROM invoices
)
ORDER BY invoice_total DESC;

-- ------------------------------------------------------------------------------
-- 4. TRATAMENTO DEFENSIVO DE VALORES NULL COM COALESCE
-- ------------------------------------------------------------------------------
-- Se uma consulta agregada não encontrar nenhuma linha no WHERE, o SUM() retorna NULL.
-- Em aplicações Java, receber NULL pode causar NullPointerException.
-- O comando COALESCE(SUM(...), 0) garante que retornará 0.00 se não houver dados.
-- ------------------------------------------------------------------------------

SELECT 
    COALESCE(SUM(invoice_total), 0) AS safe_total,
    COUNT(*) AS total_linhas
FROM invoices
WHERE invoice_date > '2050-01-01';

-- ------------------------------------------------------------------------------
-- 5. PRECISÃO NUMÉRICA: ROUND() VS TRUNCATE() EM MÉDIAS
-- ------------------------------------------------------------------------------
-- - AVG(): Gera dízimas periódicas com até 6 ou mais casas decimais.
-- - ROUND(valor, 2): Arredonda matematicamente para o número mais próximo (>= 5 sobe).
-- - TRUNCATE(valor, 2): Simplesmente corta as casas decimais sem arredondar.
-- ------------------------------------------------------------------------------

SELECT 
    AVG(invoice_total) AS raw_average,
    ROUND(AVG(invoice_total), 2) AS rounded_average,
    TRUNCATE(AVG(invoice_total), 2) AS truncated_average
FROM invoices;
