-- ==============================================================================
-- CAPÍTULO 22 (PARTE 1): SINTAXE DE JUNÇÃO IMPLÍCITA (IMPLICIT JOIN SYNTAX)
-- Curso: SQL Completo (Mosh Hamedani)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_22_implicit_join_syntax.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. SINTAXE EXPLÍCITA MODERNA (SQL-92) - O PADRÃO OURO RECOMENDADO
-- ------------------------------------------------------------------------------
-- Utiliza a palavra-chave JOIN e isola a amarração das tabelas na cláusula ON.
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. SINTAXE IMPLÍCITA LEGADA (SQL-89) - COMO OS ANTIGOS ESCREVIAM
-- ------------------------------------------------------------------------------
-- Tabelas separadas por vírgula no FROM. A amarração vai dentro do WHERE.
-- Produz EXATAMENTE o mesmo resultado de 10 linhas.
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.last_name
FROM orders o, customers c
WHERE o.customer_id = c.customer_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 3. A CATÁSTROFE DO ESQUECIMENTO: O PRODUTO CARTESIANO (CROSS JOIN ACIDENTAL)
-- ------------------------------------------------------------------------------
-- Se você esquecer a cláusula WHERE na sintaxe implícita:
-- 10 pedidos x 10 clientes = 100 LINHAS DE DADOS TOTALMENTE SEM SENTIDO!
SELECT 
    o.order_id,
    c.first_name,
    c.last_name
FROM orders o, customers c
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 4. A REDE DE PROTEÇÃO DA SINTAXE EXPLÍCITA
-- ------------------------------------------------------------------------------
-- Se você esquecer a cláusula ON na sintaxe explícita, o MySQL NÃO EXECUTA!
-- Ele gera o ERROR 1064 (42000): Syntax error imediatamente, te salvando!
-- (Descomente para testar o erro no console):
-- SELECT * FROM orders o JOIN customers c;

-- ------------------------------------------------------------------------------
-- 5. MISTURA DE REGRAS DE NEGÓCIO COM AMARRAÇÃO DE TABELAS
-- ------------------------------------------------------------------------------
-- Sintaxe Explícita: Separação limpa entre estrutura (ON) e filtro de dados (WHERE).
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.state
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
WHERE o.order_date >= '2018-01-01'
  AND c.state = 'VA';

-- Sintaxe Implícita: Tudo jogado misturado no WHERE (ruim para manutenção).
SELECT 
    o.order_id,
    o.order_date,
    c.first_name,
    c.state
FROM orders o, customers c
WHERE o.customer_id = c.customer_id
  AND o.order_date >= '2018-01-01'
  AND c.state = 'VA';
