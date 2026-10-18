-- ==============================================================================
-- CAPÍTULO 32 (PARTE 1): INSERINDO MÚLTIPLAS LINHAS (INSERTING MULTIPLE ROWS)
-- Inserção em Lote (Batch Inserts), A Tabela 'shippers', O Exercício Oficial
-- dos 'products' e a Diferença Brutal de Performance (1 Viagem vs N Viagens)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 4
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_32_inserting_multiple_rows_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A DEMONSTRAÇÃO OFICIAL DO AUTOR: INSERINDO 3 TRANSPORTADORAS (shippers)
-- ------------------------------------------------------------------------------
-- Em vez de executar 3 comandos INSERT separados, separamos as tuplas por vírgula!
-- O MySQL executa tudo em uma única transação atômica e uma única viagem de rede.
-- ------------------------------------------------------------------------------

INSERT INTO shippers (name)
VALUES 
    ('Shipper1'),
    ('Shipper2'),
    ('Shipper3');

-- ------------------------------------------------------------------------------
-- 2. VERIFICAÇÃO DAS TRANSPORTADORAS INSERIDAS
-- ------------------------------------------------------------------------------
-- Os novos shippers receberão automaticamente os IDs 6, 7 e 8 via auto_increment:
-- ------------------------------------------------------------------------------

SELECT * 
FROM shippers 
ORDER BY shipper_id;

-- ------------------------------------------------------------------------------
-- 3. O EXERCÍCIO OFICIAL DO MOSH: INSERIR 3 PRODUTOS NA TABELA 'products'
-- ------------------------------------------------------------------------------
-- Enunciado do Autor:
-- "Insira três novos produtos na tabela products em um único comando:
--   - Colunas necessárias: name, quantity_in_stock, unit_price
--   - O product_id é gerado automaticamente pelo Auto-Increment (receberá 11, 12, 13)."
-- ------------------------------------------------------------------------------

INSERT INTO products (name, quantity_in_stock, unit_price)
VALUES 
    ('Product1', 10, 1.95),
    ('Product2', 20, 2.95),
    ('Product3', 30, 3.95);

-- ------------------------------------------------------------------------------
-- 4. VERIFICAÇÃO DOS PRODUTOS DO EXERCÍCIO
-- ------------------------------------------------------------------------------

SELECT * 
FROM products 
WHERE product_id >= 11
ORDER BY product_id;
