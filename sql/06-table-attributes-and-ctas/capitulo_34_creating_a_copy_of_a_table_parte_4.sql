-- ==============================================================================
-- CAPÍTULO 34 (PARTE 4 - FINAL SUPREMO): PIPELINE DE ETL, PARTICIONAMENTO E AUDITORIA
-- 1. Criação de Tabela de Arquivo Morto com Particionamento por Ano (PARTITION BY RANGE)
-- 2. Carga em Massa via INSERT INTO ... SELECT no Arquivo Particionado
-- 3. Auditoria Matemática de Integridade com CHECKSUM TABLE
-- 4. Otimização de Performance com Partition Pruning (EXPLAIN PARTITIONS)
-- 5. O Dicionário Supremo da Cópia e Arquivamento de Tabelas
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 6 (Encerramento de Mestre)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_34_creating_a_copy_of_a_table_parte_4.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CRIAÇÃO DE TABELA HISTÓRICA PARTICIONADA POR FAIXA DE ANOS (PARTITION BY RANGE)
-- ------------------------------------------------------------------------------
-- Em arquiteturas corporativas de grande escala, arquivar tabelas cópias em um
-- único bloco gera lentidão. O particionamento divide o arquivo no disco por ano:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS orders_partitioned_archive;

CREATE TABLE orders_partitioned_archive (
    order_id INT NOT NULL,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    status TINYINT NOT NULL,
    comments VARCHAR(2000),
    shipped_date DATE,
    shipper_id SMALLINT,
    PRIMARY KEY (order_id, order_date) -- No MySQL, a chave de particionamento deve compor a PK!
)
PARTITION BY RANGE (YEAR(order_date)) (
    PARTITION p_2017 VALUES LESS THAN (2018),
    PARTITION p_2018 VALUES LESS THAN (2019),
    PARTITION p_2019 VALUES LESS THAN (2020),
    PARTITION p_future VALUES LESS THAN MAXVALUE
);

-- ------------------------------------------------------------------------------
-- 2. POPULANDO O ARQUIVO PARTICIPADO VIA 'INSERT INTO ... SELECT'
-- ------------------------------------------------------------------------------
-- O comando lê a tabela orders original e distribui cada linha automaticamente
-- na sua respectiva partição de disco sem intervenção humana:
-- ------------------------------------------------------------------------------

INSERT INTO orders_partitioned_archive
SELECT 
    order_id,
    customer_id,
    order_date,
    status,
    comments,
    shipped_date,
    shipper_id
FROM orders;

-- ------------------------------------------------------------------------------
-- 3. AUDITORIA MATEMÁTICA DE INTEGRIDADE: CHECKSUM TABLE
-- ------------------------------------------------------------------------------
-- Como provar ao setor de auditoria que nenhum registro foi alterado ou perdido?
-- O comando CHECKSUM TABLE calcula um hash criptográfico 32-bit de todos os dados:
-- ------------------------------------------------------------------------------

CHECKSUM TABLE orders, orders_partitioned_archive;

-- ------------------------------------------------------------------------------
-- 4. PROVANDO A EFICIÊNCIA DO PARTICIONAMENTO (PARTITION PRUNING COM EXPLAIN)
-- ------------------------------------------------------------------------------
-- Ao buscar um pedido de 2018, o MySQL nem olha para as partições de 2017 ou 2019!
-- Ele acessa diretamente a partição 'p_2018' (Partition Pruning):
-- ------------------------------------------------------------------------------

EXPLAIN 
SELECT * 
FROM orders_partitioned_archive 
WHERE order_date BETWEEN '2018-01-01' AND '2018-12-31';

-- ------------------------------------------------------------------------------
-- 5. RELATÓRIO DE DISTRIBUIÇÃO DAS LINHAS POR PARTIÇÃO
-- ------------------------------------------------------------------------------
-- Inspeciona a quantidade exata de linhas que caíram em cada gaveta física do disco:
-- ------------------------------------------------------------------------------

SELECT 
    table_name,
    partition_name,
    table_rows
FROM information_schema.partitions
WHERE table_name = 'orders_partitioned_archive';
