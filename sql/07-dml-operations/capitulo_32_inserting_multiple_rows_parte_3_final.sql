-- ==============================================================================
-- CAPÍTULO 32 (PARTE 3 - FINAL SUPREMO): INSERÇÃO EM LOTE MASSIVA, BULK LOAD E ARQUITETURA
-- 1. Criação de Tabela de Carga Rápida (staging_products)
-- 2. Mecanismo de Bulk Load com LOAD DATA INFILE (O Mais Rápido do MySQL)
-- 3. Transações Atômicas de Lote com COMMIT e ROLLBACK em Caso de Falha
-- 4. O Comportamento do INSERT IGNORE em Lotes com Falha Parcial
-- 5. Inspeção do Dicionário e Variáveis de Sistema (secure_file_priv, bulk_insert_buffer_size)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 4 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_32_inserting_multiple_rows_parte_3_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. INSPEÇÃO DAS VARIÁVEIS DE SISTEMA DO MYSQL PARA CARGAS MASSIVAS
-- ------------------------------------------------------------------------------
-- O MySQL possui configurações específicas de memória para otimizar batch inserts.
-- bulk_insert_buffer_size: tamanho do cache de memória para inserções em árvore de índices.
-- secure_file_priv: pasta segura onde o MySQL autoriza a leitura de arquivos CSV/TXT.
-- ------------------------------------------------------------------------------

SHOW VARIABLES LIKE 'bulk_insert_buffer_size';
SHOW VARIABLES LIKE 'secure_file_priv';

-- ------------------------------------------------------------------------------
-- 2. CRIAÇÃO DE TABELA DE STAGING (ÁREA DE CARGA BRUTA TEMPORÁRIA)
-- ------------------------------------------------------------------------------
-- Em arquiteturas corporativas de Big Data / ETL (Extract, Transform, Load),
-- nunca inserimos milhões de linhas diretamente na tabela principal de produção.
-- Criamos uma tabela intermediária leve (staging), desprovida de índices e FKs pesadas:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS staging_products;

CREATE TABLE staging_products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    quantity_in_stock INT NOT NULL,
    unit_price DECIMAL(4, 2) NOT NULL,
    imported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- 3. CARGA MASSIVA VIA CSV NATIVO: A SINTAXE DO 'LOAD DATA INFILE'
-- ------------------------------------------------------------------------------
-- Esta é a instrução mais veloz de todo o ecossistema MySQL (bypassa o parser SQL).
-- Se um arquivo CSV estiver disponível no diretório autorizado (secure_file_priv):
-- ------------------------------------------------------------------------------
/*
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/products_import.csv'
INTO TABLE staging_products
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(name, quantity_in_stock, unit_price);
*/

-- ------------------------------------------------------------------------------
-- 4. SIMULAÇÃO DE TRANSAÇÃO ATÔMICA EM LOTE: SUCESSO vs ROLLBACK
-- ------------------------------------------------------------------------------
-- Se um lote com 5 produtos for inserido, mas uma regra de negócio falhar,
-- a instrução ROLLBACK desfaz a inserção de TODOS os itens do lote simultaneamente!
-- ------------------------------------------------------------------------------

-- Teste de Sucesso (COMMIT):
START TRANSACTION;

INSERT INTO staging_products (name, quantity_in_stock, unit_price)
VALUES 
    ('Fone Bluetooth Gamer', 50, 29.90),
    ('Mouse Sem Fio Ultra', 120, 15.50),
    ('Teclado Mecânico RGB', 80, 45.00);

COMMIT;

-- Verificando que o lote foi efetivamente gravado no disco:
SELECT * FROM staging_products;

-- Teste de Aborto / Desistência (ROLLBACK):
START TRANSACTION;

INSERT INTO staging_products (name, quantity_in_stock, unit_price)
VALUES 
    ('Item Corrompido 1', 10, 9.99),
    ('Item Corrompido 2', 20, 19.99);

-- Ocorreu um erro ou cancelamento na aplicação Java -> Desfazemos tudo:
ROLLBACK;

-- Verificando que os itens cancelados NÃO existem na tabela:
SELECT * FROM staging_products;

-- ------------------------------------------------------------------------------
-- 5. BATCH COM 'INSERT IGNORE': SUPORTANDO FALHAS PARCIAIS
-- ------------------------------------------------------------------------------
-- Se um lote contiver 3 produtos, mas um deles tentar violar uma restrição única
-- (ou violar tipos), o 'INSERT IGNORE' pula a linha defeituosa e grava as válidas:
-- ------------------------------------------------------------------------------

INSERT IGNORE INTO staging_products (product_id, name, quantity_in_stock, unit_price)
VALUES 
    (1, 'Tentativa de Sobrescrever ID 1', 99, 1.00), -- Já existe (Primary Key duplicada!) -> Ignorado
    (100, 'Produto Novo Importado 100', 45, 12.30),  -- Inédito -> Gravado
    (101, 'Produto Novo Importado 101', 30, 8.50);   -- Inédito -> Gravado

-- O console responderá com Warnings:
-- Query OK, 2 rows affected, 1 warning (0.01 sec)
-- Records: 3  Duplicates: 1  Warnings: 1

SHOW WARNINGS;

-- Verificando o resultado final na tabela de staging:
SELECT * FROM staging_products ORDER BY product_id;
