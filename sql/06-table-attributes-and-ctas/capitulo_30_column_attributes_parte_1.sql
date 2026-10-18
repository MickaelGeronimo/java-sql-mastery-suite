-- ==============================================================================
-- CAPÍTULO 30 (PARTE 1): ATRIBUTOS DE COLUNA (COLUMN ATTRIBUTES)
-- Abertura da Seção 4: Inserindo, Atualizando e Deletando Dados
-- Inspecionando a Tabela 'customers': PK, NN, AI, Tipos e Valores Padrão
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 2
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_30_column_attributes_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. INSPEÇÃO ESTRUTURAL DA TABELA (A TELA DO WORKBENCH NO TERMINAL)
-- ------------------------------------------------------------------------------
-- No vídeo, o autor Mosh abre a ferramenta gráfica do MySQL Workbench clicando
-- no ícone da chave inglesa (Wrench icon / Alter Table) para inspecionar os
-- atributos das colunas da tabela 'customers'.
-- No terminal SQL profissional, o comando exato equivalente é o DESCRIBE:
-- ------------------------------------------------------------------------------

DESCRIBE customers;

-- ------------------------------------------------------------------------------
-- 2. INSPEÇÃO DETALHADA COM SHOW FULL COLUMNS
-- ------------------------------------------------------------------------------
-- Revela detalhes extras como Collations, Privilégios e Comentários:
-- ------------------------------------------------------------------------------

SHOW FULL COLUMNS FROM customers;

-- ------------------------------------------------------------------------------
-- 3. ENGENHARIA REVERSA: O DDL COMPLETO DE CRIAÇÃO DA TABELA
-- ------------------------------------------------------------------------------
-- Exibe o comando CREATE TABLE original com todas as restrições (Constraints):
-- ------------------------------------------------------------------------------

SHOW CREATE TABLE customers;

-- ------------------------------------------------------------------------------
-- 4. O QUE APRENDEMOS COM OS ATRIBUTOS: O QUE É OBRIGATÓRIO VS OPCIONAL NO INSERT?
-- ------------------------------------------------------------------------------
-- Para poder inserir registros sem erro no próximo capítulo, classificamos:
--
-- COLUNAS OBRIGATÓRIAS (Not Null SEM Default e SEM Auto Increment):
--   1. first_name (VARCHAR 50, Not Null)
--   2. last_name  (VARCHAR 50, Not Null)
--   3. address    (VARCHAR 50, Not Null)
--   4. city       (VARCHAR 50, Not Null)
--   5. state      (CHAR 2, Not Null)
--
-- COLUNAS OPCIONAIS (Aceitam NULL ou possuem Auto Increment / Default):
--   1. customer_id (Auto Increment -> O MySQL gera o próximo ID sozinho: 11, 12...)
--   2. birth_date  (Aceita NULL)
--   3. phone       (Aceita NULL)
--   4. points      (Possui Default = 0 -> assume 0 se omitido)
-- ------------------------------------------------------------------------------
