-- ==============================================================================
-- CAPÍTULO 26 (PARTE 1): A CLÁUSULA USING (THE USING CLAUSE)
-- A Elegância Sintática, Eliminação da Redundância e Junções Múltiplas
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 10
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_26_the_using_clause_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. SINTAXE CLÁSSICA COM 'ON' (REPETITIVA E PROLIXA)
-- ------------------------------------------------------------------------------
-- Observe que a coluna 'customer_id' existe com o MESMO NOME exato
-- em ambas as tabelas ('orders' e 'customers').
-- Escrever 'o.customer_id = c.customer_id' é redundante.
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    c.first_name
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. A SOLUÇÃO ELEGANTE COM 'USING' (THE USING CLAUSE)
-- ------------------------------------------------------------------------------
-- Quando os nomes das colunas de amarração forem RIGOROSAMENTE IDÊNTICOS,
-- podemos substituir toda a cláusula 'ON' por 'USING (nome_da_coluna)'.
-- Os parênteses são OBRIGATÓRIOS.
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    c.first_name
FROM orders o
JOIN customers c
    USING (customer_id)
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 3. MÚLTIPLAS JUNÇÕES COM 'USING' E 'LEFT JOIN'
-- ------------------------------------------------------------------------------
-- O 'USING' funciona tanto para INNER JOIN quanto para OUTER JOINS (LEFT/RIGHT).
-- Aqui conectamos orders com customers (via customer_id)
-- e depois com shippers (via shipper_id com LEFT JOIN para não perder pedidos não enviados).
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    c.first_name,
    sh.name AS shipper
FROM orders o
JOIN customers c
    USING (customer_id)
LEFT JOIN shippers sh
    USING (shipper_id)
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 4. ONDE O 'USING' NÃO PODE SER USADO? (A REGRA DOS NOMES DIFERENTES)
-- ------------------------------------------------------------------------------
-- Se as tabelas usarem nomes de colunas diferentes para a mesma chave,
-- o 'USING' NÃO FUNCIONA! Exemplo no sql_hr:
-- 'e.reports_to = m.employee_id' -> Nomes diferentes exigem 'ON'!
-- ------------------------------------------------------------------------------
