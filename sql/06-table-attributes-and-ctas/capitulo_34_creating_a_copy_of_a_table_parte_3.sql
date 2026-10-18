-- ==============================================================================
-- CAPÍTULO 34 (PARTE 3 - FINAL SUPREMO): A SOLUÇÃO DA PK COM 'CREATE TABLE ... LIKE',
-- TABELAS TEMPORÁRIAS (TEMPORARY TABLE) E O SWAP ATÔMICO DE MIGRAÇÃO (RENAME TABLE)
-- 1. A Técnica Secreta do DBA: CREATE TABLE ... LIKE (Preservando PK e Auto-Increment)
-- 2. Carga com INSERT INTO ... SELECT (Clonagem Perfeita com Metadados e Dados)
-- 3. Tabelas Temporárias de Sessão (CREATE TEMPORARY TABLE)
-- 4. O Swap Atômico de Tabelas Zero-Downtime (RENAME TABLE)
-- 5. O Dicionário Supremo da Cópia de Tabelas
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 6 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_34_creating_a_copy_of_a_table_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A TÉCNICA DEFINITIVA: CREATE TABLE ... LIKE
-- ------------------------------------------------------------------------------
-- Na Parte 1, vimos que CREATE TABLE ... AS SELECT perde a PK e o Auto-Increment.
-- Como um Engenheiro Sênior resolve isso? Usando a instrução LIKE!
-- O comando LIKE clona 100% da estrutura, índices, chaves primárias e auto-increment!
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS orders_perfect_copy;

-- Passo 1: Clona a estrutura perfeita (com PK, AI e Índices), porém vazia (0 linhas):
CREATE TABLE orders_perfect_copy LIKE orders;

-- Conferindo com DESCRIBE: Chave Primária (PRI) e Auto_Increment ESTÃO LÁ!
DESCRIBE orders_perfect_copy;

-- Passo 2: Copia todos os dados com INSERT INTO ... SELECT:
INSERT INTO orders_perfect_copy
SELECT * FROM orders;

-- Conferindo que os dados e os metadados estão 100% sincronizados:
SELECT * FROM orders_perfect_copy;

-- ------------------------------------------------------------------------------
-- 2. TABELAS TEMPORÁRIAS EM MEMÓRIA (CREATE TEMPORARY TABLE)
-- ------------------------------------------------------------------------------
-- Uma tabela temporária existe APENAS enquanto esta conexão do usuário estiver aberta.
-- Assim que você fechar o MySQL Workbench ou desconectar a aplicação Java,
-- a tabela evapora automaticamente da memória sem deixar sujeira no banco!
-- ------------------------------------------------------------------------------

DROP TEMPORARY TABLE IF EXISTS temp_order_summary;

CREATE TEMPORARY TABLE temp_order_summary AS
SELECT 
    customer_id,
    COUNT(order_id) AS total_orders,
    MAX(order_date) AS latest_order
FROM orders
GROUP BY customer_id;

-- Consultando a tabela temporária:
SELECT * FROM temp_order_summary;

-- ------------------------------------------------------------------------------
-- 3. MIGRAÇÃO CORPORATIVA ZERO-DOWNTIME (O SWAP ATÔMICO DE TABELAS)
-- ------------------------------------------------------------------------------
-- Como empresas gigantes (como Uber, Netflix e Nubank) alteram tabelas de milhões
-- de linhas sem derrubar o sistema?
-- Elas criam uma tabela nova clonada com LIKE, populam, e trocam os nomes num instante:
-- ------------------------------------------------------------------------------

-- Simulando a tabela orders_v2:
DROP TABLE IF EXISTS orders_v2;
CREATE TABLE orders_v2 LIKE orders;

-- Populando a nova tabela:
INSERT INTO orders_v2 SELECT * FROM orders;

-- A Troca Atômica em 1 Milissegundo (Zero-Downtime Swap):
-- RENAME TABLE orders TO orders_old, orders_v2 TO orders;
-- (A tabela nova assume a produção sem interrupção de serviço!)
