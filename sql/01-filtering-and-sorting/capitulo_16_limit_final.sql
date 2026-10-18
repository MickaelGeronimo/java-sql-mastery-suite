-- ==============================================================================
-- CAPÍTULO 16 (PARTE 3 - FINAL): O GRAND FINALE DA CLÁUSULA LIMIT E SEÇÃO 2
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_16_limit_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O FAMOSO ERRO 1235 DO MYSQL E O HACK DA TABELA DERIVADA (DERIVED TABLE)
-- ------------------------------------------------------------------------------
-- Tentativa direta que causa: ERROR 1235 (42000): This version of MySQL doesn't yet support 'LIMIT & IN/ALL/ANY/SOME subquery'
-- SELECT * FROM orders WHERE customer_id IN (SELECT customer_id FROM customers ORDER BY points DESC LIMIT 3);

-- SOLUÇÃO DA ENGENHARIA SÊNIOR (DERIVED TABLE WRAPPER):
SELECT 
    order_id, 
    customer_id, 
    order_date, 
    status
FROM orders
WHERE customer_id IN (
    SELECT customer_id 
    FROM (
        SELECT customer_id 
        FROM customers 
        ORDER BY points DESC 
        LIMIT 3
    ) AS top_3_loyal_customers
)
ORDER BY order_date DESC;

-- ------------------------------------------------------------------------------
-- 2. A INJUSTIÇA DO EMPATE: LIMIT 3 vs DENSE_RANK()
-- ------------------------------------------------------------------------------
-- Consulta com LIMIT 3 (Corta arbitrariamente empates de pontuação):
SELECT 
    customer_id, 
    first_name, 
    points 
FROM customers 
ORDER BY points DESC 
LIMIT 3;

-- Consulta Justa com Window Function DENSE_RANK() (Inclui todos os empatados no Top 3):
SELECT 
    posicao_podio,
    customer_id, 
    first_name, 
    points
FROM (
    SELECT 
        customer_id, 
        first_name, 
        points,
        DENSE_RANK() OVER (ORDER BY points DESC) AS posicao_podio
    FROM customers
) AS ranking_clientes
WHERE posicao_podio <= 3
ORDER BY posicao_podio ASC, customer_id ASC;

-- ------------------------------------------------------------------------------
-- 3. O TRUQUE DE ENGENHARIA: LIMIT 0 (INSPEÇÃO DE METADADOS SEM TRAFEGAR DADOS)
-- ------------------------------------------------------------------------------
SELECT * 
FROM customers 
LIMIT 0;

-- ------------------------------------------------------------------------------
-- 4. O GRANDE DESAFIO SUPREMO DE FECHAMENTO DA SEÇÃO 2 (SINGLE TABLE)
-- Reúne: SELECT (cálculo e alias), FROM, WHERE (AND, OR, NOT, IS NOT NULL,
-- IN, BETWEEN, REGEXP), ORDER BY (multicoluna) e LIMIT (com paginação OFFSET).
-- ------------------------------------------------------------------------------
SELECT 
    customer_id,
    CONCAT(first_name, ' ', last_name) AS full_name,
    city,
    state,
    points,
    ROUND(points * 1.10, 2) AS projected_points
FROM customers
WHERE (state IN ('VA', 'FL', 'TX', 'CA', 'CO') OR points BETWEEN 1000 AND 3000)
  AND phone IS NOT NULL
  AND NOT (city REGEXP '^[0-9]')
ORDER BY points DESC, last_name ASC
LIMIT 3 OFFSET 0;
