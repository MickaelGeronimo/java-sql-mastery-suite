-- ==============================================================================
-- CAPÍTULO 36 (PARTE 2): O EXERCÍCIO OFICIAL DO MOSH (sql_store)
-- Bônus de Pontos Cumulativos (points = points + 50), Filtros por Datas Históricas,
-- Desbloqueio do Safe Updates, Auditoria Prévia e Pós, e Idempotência
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 8 (Exercício Oficial)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_36_updating_multiple_rows_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. ENUNCIADO DO EXERCÍCIO OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- "Escreva uma instrução SQL para:
--  Dar a quaisquer clientes nascidos antes de 1990 50 pontos extras.
--  Consulte a tabela customers do banco de dados sql_store."
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 2. AUDITORIA PRÉVIA: QUAIS CLIENTES NASCERAM ANTES DE 1990?
-- ------------------------------------------------------------------------------
-- Regra profissional: Antes de alterar dados em lote, faça a leitura prévia
-- para saber exatamente quem atende ao predicado e quais são os pontos atuais:
-- ------------------------------------------------------------------------------

SELECT 
    customer_id,
    first_name,
    last_name,
    birth_date,
    points AS pontos_atuais
FROM customers
WHERE birth_date < '1990-01-01'
ORDER BY customer_id;

-- ------------------------------------------------------------------------------
-- 3. A RESOLUÇÃO OFICIAL DO MOSH COM CONTROLE DE SESSÃO SEGURO
-- ------------------------------------------------------------------------------
-- Como a coluna birth_date não é a Chave Primária, o MySQL Workbench bloqueará
-- com o Error 1175 se o SQL_SAFE_UPDATES estiver ligado.
-- Desativamos a trava para a sessão, executamos a soma e reativamos:
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

UPDATE customers
SET points = points + 50
WHERE birth_date < '1990-01-01';

SET SQL_SAFE_UPDATES = 1;

-- ------------------------------------------------------------------------------
-- 4. AUDITORIA PÓS-UPDATE: CONFERÊNCIA GERAL DE TODOS OS CLIENTES
-- ------------------------------------------------------------------------------
-- Conferindo a tabela inteira para certificar:
-- 1. Que os nascidos antes de 1990 ganharam exatamente +50 pontos.
-- 2. Que os nascidos em 1990 em diante mantiveram seus pontos originais!
-- ------------------------------------------------------------------------------

SELECT 
    customer_id,
    first_name,
    last_name,
    birth_date,
    points AS pontos_finais,
    CASE 
        WHEN birth_date < '1990-01-01' THEN 'Beneficiado (+50)'
        ELSE 'Inalterado (Nascido >= 1990)'
    END AS status_campanha
FROM customers
ORDER BY customer_id;

-- ------------------------------------------------------------------------------
-- 5. O PERIGO DA NÃO-IDEMPOTÊNCIA: E SE EXECUTARMOS DUAS VEZES?
-- ------------------------------------------------------------------------------
-- Em computação, 'idempotência' significa que executar a mesma operação 10 vezes
-- produz o mesmo resultado de executar 1 vez.
--
-- ATENÇÃO: 'points = points + 50' NÃO É IDEMPOTENTE!
-- Se uma fila de mensageria (Kafka/RabbitMQ) reprocessar este comando por engano,
-- os clientes ganharão +100, +150, +200 pontos indevidamente!
--
-- Solução Corporativa Idempotente (usando flag ou tabela de controle de bônus):
--
-- ALTER TABLE customers ADD COLUMN bonus_1990_applied BOOLEAN DEFAULT FALSE;
--
-- UPDATE customers
-- SET 
--     points = points + 50,
--     bonus_1990_applied = TRUE
-- WHERE birth_date < '1990-01-01' AND bonus_1990_applied = FALSE;
-- ------------------------------------------------------------------------------
