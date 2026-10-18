-- ==============================================================================
-- CAPÍTULO 31 (PARTE 1): INSERINDO UMA ÚNICA LINHA (INSERTING A SINGLE ROW)
-- Abertura da Prática de Inserção: As Duas Sintaxes do INSERT INTO,
-- Uso de DEFAULT e NULL, e a Lista Explícita de Colunas (Recomendada)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 3
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_31_inserting_a_single_row_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. SINTAXE 1: INSERÇÃO POSICIONAL COMPLETA (SEM LISTA DE COLUNAS)
-- ------------------------------------------------------------------------------
-- Nesta sintaxe, omitimos o parêntese com o nome das colunas após 'customers'.
-- CONSEQUÊNCIA: Somos estritamente obrigados a fornecer valores para TODAS as
-- 9 colunas da tabela na mesma ordem exata em que foram criadas no esquema!
-- Usamos 'DEFAULT' para o customer_id (Auto Increment) e para points (Default 0).
-- Usamos 'NULL' para o phone (que aceita nulo).
-- ------------------------------------------------------------------------------

INSERT INTO customers
VALUES (
    DEFAULT,
    'John',
    'Smith',
    '1990-01-01',
    NULL,
    'address',
    'city',
    'CA',
    DEFAULT
);

-- ------------------------------------------------------------------------------
-- 2. SINTAXE 2 (A RECOMENDADA PELO AUTOR): LISTA EXPLÍCITA DE COLUNAS
-- ------------------------------------------------------------------------------
-- Esta é a sintaxe de padrão da indústria (Enterprise Standard):
-- Declaramos explicitamente apenas as colunas que desejamos preencher.
-- Todas as colunas com AUTO_INCREMENT, DEFAULT ou NULLABLE podem ser OMITIDAS!
-- ------------------------------------------------------------------------------

INSERT INTO customers (
    first_name,
    last_name,
    birth_date,
    address,
    city,
    state
)
VALUES (
    'Mickael',
    'Geronimo',
    '1995-04-12',
    'Av. Paulista 1000',
    'São Paulo',
    'SP'
);

-- ------------------------------------------------------------------------------
-- 3. VERIFICAÇÃO DO REGISTRO INSERIDO
-- ------------------------------------------------------------------------------
-- Consultamos o último ID gerado pelo auto-incremento da nossa sessão:
-- ------------------------------------------------------------------------------

SELECT * 
FROM customers 
WHERE customer_id = LAST_INSERT_ID();
