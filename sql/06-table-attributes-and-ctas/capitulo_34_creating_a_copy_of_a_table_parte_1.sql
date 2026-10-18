-- ==============================================================================
-- CAPÍTULO 34 (PARTE 1): CRIANDO UMA CÓPIA DE UMA TABELA (CREATING A COPY OF A TABLE)
-- A Técnica CTAS (CREATE TABLE ... AS SELECT), O Perigo da Perda de PK e Auto-Increment,
-- O Comando TRUNCATE e a Inserção com Subquery (INSERT INTO ... SELECT sem VALUES)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 6
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_34_creating_a_copy_of_a_table_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A DEMONSTRAÇÃO OFICIAL DO AUTOR: CLONANDO A TABELA 'orders'
-- ------------------------------------------------------------------------------
-- A sintaxe CTAS (Create Table As Select) cria uma nova tabela e já copia
-- imediatamente todas as linhas retornadas pela consulta SELECT:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS orders_archived;

CREATE TABLE orders_archived AS
SELECT * FROM orders;

-- ------------------------------------------------------------------------------
-- 2. VERIFICANDO OS DADOS COPIADOS
-- ------------------------------------------------------------------------------
-- Todas as linhas e colunas foram copiadas para a nova tabela:
-- ------------------------------------------------------------------------------

SELECT * 
FROM orders_archived;

-- ------------------------------------------------------------------------------
-- 3. A GRANDE ARMADILHA APONTADA PELO AUTOR: A PERDA DE ATRIBUTOS (PK e AI)
-- ------------------------------------------------------------------------------
-- Ao usar CREATE TABLE ... AS SELECT, o MySQL cria a estrutura física, MAS:
--   - NÃO copia a Chave Primária (Primary Key);
--   - NÃO copia o Auto-Incremento (Auto_Increment);
--   - NÃO copia Chaves Estrangeiras (Foreign Keys) nem Índices!
-- Podemos comprovar isso inspecionando as colunas com DESCRIBE:
-- ------------------------------------------------------------------------------

DESCRIBE orders;
DESCRIBE orders_archived;

-- ------------------------------------------------------------------------------
-- 4. LIMPANDO A TABELA DE CÓPIA COM O COMANDO 'TRUNCATE'
-- ------------------------------------------------------------------------------
-- O comando TRUNCATE apaga instantaneamente todas as linhas da tabela,
-- resetando a tabela para o estado vazio sem deletar a estrutura física:
-- ------------------------------------------------------------------------------

TRUNCATE TABLE orders_archived;

-- Verificando que a tabela ficou completamente vazia (0 rows):
SELECT * FROM orders_archived;

-- ------------------------------------------------------------------------------
-- 5. POPULANDO A TABELA USANDO 'INSERT INTO ... SELECT' (SEM A PALAVRA VALUES!)
-- ------------------------------------------------------------------------------
-- O autor ensina uma variação poderosa: quando a tabela já existe, nós NÃO usamos
-- o comando VALUES. Nós usamos uma consulta SELECT como fonte direta de dados!
-- Aqui, filtramos apenas os pedidos históricos realizados antes de 2019:
-- ------------------------------------------------------------------------------

INSERT INTO orders_archived
SELECT *
FROM orders
WHERE order_date < '2019-01-01';

-- ------------------------------------------------------------------------------
-- 6. VERIFICANDO OS DADOS ARQUIVADOS FILTRADOS
-- ------------------------------------------------------------------------------
-- Apenas os pedidos de 2017 e 2018 foram arquivados na tabela de backup:
-- ------------------------------------------------------------------------------

SELECT 
    order_id,
    customer_id,
    order_date,
    status
FROM orders_archived
ORDER BY order_date;
