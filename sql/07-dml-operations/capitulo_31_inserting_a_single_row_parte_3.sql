-- ==============================================================================
-- CAPÍTULO 31 (PARTE 3): ENGENHARIA DE INSERÇÃO, TRANSAÇÕES ACID E UPSERT
-- Transações (COMMIT / ROLLBACK), INSERT IGNORE (Supressão de Erros),
-- ON DUPLICATE KEY UPDATE (UPSERT do MySQL 8) e Subqueries no VALUES
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 3
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_31_inserting_a_single_row_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. TRANSAÇÕES ACID: INSERÇÃO SEGURA COM COMMIT E ROLLBACK
-- ------------------------------------------------------------------------------
-- Por padrão no MySQL, autocommit está ativado.
-- Em sistemas críticos (bancários/e-commerce), controlamos a transação manualmente:
-- ------------------------------------------------------------------------------

START TRANSACTION;

INSERT INTO customers (first_name, last_name, address, city, state, points)
VALUES ('Lucas', 'Teste', 'Rua Temporaria 99', 'Campinas', 'SP', 50);

-- Se algo der errado no backend Java, desfazemos tudo sem deixar vestígios:
ROLLBACK;

-- ------------------------------------------------------------------------------
-- 2. INSERT IGNORE: SILENCIANDO CONFLITOS DE CHAVE PRIMÁRIA
-- ------------------------------------------------------------------------------
-- Se tentarmos inserir um cliente com ID 1 (que já existe), o INSERT comum aborta.
-- O 'INSERT IGNORE' transforma o erro fatal em um simples AVISO (Warning),
-- descarta a linha conflitante e permite que o restante da aplicação continue viva!
-- ------------------------------------------------------------------------------

INSERT IGNORE INTO customers (customer_id, first_name, last_name, address, city, state)
VALUES (1, 'Clone', 'Fantasma', 'Rua X', 'Cidade', 'SP');

SHOW WARNINGS;

-- ------------------------------------------------------------------------------
-- 3. O PODEROSO 'UPSERT': ON DUPLICATE KEY UPDATE (SINTAXE MODERNA MYSQL 8)
-- ------------------------------------------------------------------------------
-- "Tente inserir este cliente. Se o ID já existir, NÃO DÊ ERRO: em vez disso,
-- atualize os pontos do cliente somando os novos pontos recebidos!"
-- No MySQL 8.0.20+, usamos a cláusula 'AS new_row' para referenciar os novos valores:
-- ------------------------------------------------------------------------------

INSERT INTO customers (customer_id, first_name, last_name, address, city, state, points)
VALUES (1, 'Babara', 'MacCaffrey', 'address', 'city', 'CA', 100) AS new_row
ON DUPLICATE KEY UPDATE 
    points = customers.points + new_row.points;

-- ------------------------------------------------------------------------------
-- 4. INSERÇÃO COM SUBQUERY ESCALAR NO VALUES (MÉDIA DE PONTOS DINÂMICA)
-- ------------------------------------------------------------------------------
-- Inserimos um cliente novo dando a ele como bônus inicial a MÉDIA EXATA
-- de pontos de todos os clientes cadastrados na loja:
-- ------------------------------------------------------------------------------

INSERT INTO customers (
    first_name,
    last_name,
    address,
    city,
    state,
    points
)
VALUES (
    'Fernanda',
    'Lima',
    'Rua das Acácias 12',
    'Belo Horizonte',
    'MG',
    (SELECT ROUND(AVG(points)) FROM (SELECT points FROM customers) AS temp_cust)
);
