-- ==============================================================================
-- CAPÍTULO 29 (PARTE 3): ENGENHARIA DE UNIONS, CTES, EXPLAIN E LEDGER FINANCEIRO
-- Plano de Execução (EXPLAIN), Agregações com CTEs, União Contábil e Regras de Coerção
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 13 (Grand Finale da Seção 3)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_29_unions_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. INSPEÇÃO DO PLANO DE EXECUÇÃO (EXPLAIN): UNION vs UNION ALL
-- ------------------------------------------------------------------------------
-- Ao rodar EXPLAIN em um UNION tradicional, note a linha final:
-- '<union1,2,3> | Using temporary'.
-- O MySQL cria uma tabela temporária em RAM/disco para rodar o algoritmo de DISTINCT.
-- ------------------------------------------------------------------------------

EXPLAIN 
SELECT customer_id, first_name, points, 'Bronze' AS type FROM customers WHERE points < 2000
UNION
SELECT customer_id, first_name, points, 'Silver' AS type FROM customers WHERE points BETWEEN 2000 AND 3000
UNION
SELECT customer_id, first_name, points, 'Gold' AS type FROM customers WHERE points > 3000;

-- ------------------------------------------------------------------------------
-- 2. EXPLAIN COM UNION ALL: ZERO OVERHEAD DE TABELA TEMPORÁRIA
-- ------------------------------------------------------------------------------
-- Com UNION ALL, a linha intermediária de '<union1,2,3> Using temporary' DESAPARECE!
-- As linhas fluem diretamente das tabelas para o cliente (Streaming contínuo).
-- ------------------------------------------------------------------------------

EXPLAIN 
SELECT customer_id, first_name, points, 'Bronze' AS type FROM customers WHERE points < 2000
UNION ALL
SELECT customer_id, first_name, points, 'Silver' AS type FROM customers WHERE points BETWEEN 2000 AND 3000
UNION ALL
SELECT customer_id, first_name, points, 'Gold' AS type FROM customers WHERE points > 3000;

-- ------------------------------------------------------------------------------
-- 3. ENVELOPANDO O UNION EM UMA CTE (COMMON TABLE EXPRESSION) PARA AGREGAR DADOS
-- ------------------------------------------------------------------------------
-- E se a diretoria quiser saber: "Quantos clientes temos em cada faixa e qual
-- é a média de pontuação de cada grupo?".
-- O UNION fornece as linhas categorizadas e a CTE externa executa o GROUP BY!
-- ------------------------------------------------------------------------------

WITH customer_loyalty AS (
    SELECT customer_id, first_name, points, 'Bronze' AS type FROM customers WHERE points < 2000
    UNION ALL
    SELECT customer_id, first_name, points, 'Silver' AS type FROM customers WHERE points BETWEEN 2000 AND 3000
    UNION ALL
    SELECT customer_id, first_name, points, 'Gold' AS type FROM customers WHERE points > 3000
)
SELECT 
    type,
    COUNT(*) AS total_customers,
    ROUND(AVG(points), 2) AS avg_points,
    MIN(points) AS min_points,
    MAX(points) AS max_points
FROM customer_loyalty
GROUP BY type
ORDER BY total_customers DESC;

-- ------------------------------------------------------------------------------
-- 4. APLICAÇÃO CORPORATIVA REAL: EXTRATO CONTÁBIL UNIFICADO (FINANCIAL LEDGER)
-- ------------------------------------------------------------------------------
-- No banco 'sql_invoicing', unimos Notas Emitidas (Débito do Cliente)
-- e Pagamentos Recebidos (Crédito do Cliente) em uma única linha do tempo contábil.
-- ------------------------------------------------------------------------------

USE sql_invoicing;

SELECT 
    invoice_date AS transaction_date,
    CONCAT('Invoice #', number) AS description,
    client_id,
    invoice_total AS debit,
    0.00 AS credit
FROM invoices
UNION ALL
SELECT 
    date AS transaction_date,
    CONCAT('Payment via Method #', payment_method) AS description,
    client_id,
    0.00 AS debit,
    amount AS credit
FROM payments
ORDER BY transaction_date;
