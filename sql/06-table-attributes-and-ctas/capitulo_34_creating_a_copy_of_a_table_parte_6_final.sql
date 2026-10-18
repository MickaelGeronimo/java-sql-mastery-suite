-- ==============================================================================
-- CAPÍTULO 34 (PARTE 6 - FINAL SUPREMO DEFINITIVO): DISASTER RECOVERY, RESTAURAÇÃO
-- E SNAPSHOTS DINÂMICOS AUTOMATIZADOS (AUTO-CURA DE DADOS)
-- 1. Criação do Snapshot de Segurança (orders_snapshot_master)
-- 2. Simulação de Desastre: Exclusão Acidental em Massa de Pedidos em Produção
-- 3. O Script de Auto-Cura (Disaster Recovery): Restaurando Dados a partir da Cópia
-- 4. Stored Procedure de Snapshot Dinâmico com Data/Hora (Dynamic SQL com PREPARE)
-- 5. Consulta ao Dicionário para Auditoria de Espaço em Disco (information_schema)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 6 (O Clímax Absoluto)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_34_creating_a_copy_of_a_table_parte_6_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CRIAÇÃO DO SNAPSHOT DE SEGURANÇA (A TABELA DE SALVAMENTO)
-- ------------------------------------------------------------------------------
-- Antes de qualquer manutenção, tiramos o snapshot perfeito com LIKE + INSERT:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS orders_snapshot_master;

CREATE TABLE orders_snapshot_master LIKE orders;

INSERT INTO orders_snapshot_master
SELECT * FROM orders;

-- Conferindo que o snapshot tem 10 linhas intactas:
SELECT COUNT(*) AS total_backup_rows FROM orders_snapshot_master;

-- ------------------------------------------------------------------------------
-- 2. SIMULAÇÃO DO DESASTRE CORPORATIVO (EXCLUSÃO ACIDENTAL)
-- ------------------------------------------------------------------------------
-- Um script mal configurado apagou acidentalmente todos os pedidos anteriores a 2018:
-- ------------------------------------------------------------------------------

DELETE FROM orders
WHERE order_date < '2018-01-01';

-- Conferindo o estrago: A tabela original orders agora tem menos registros!
SELECT COUNT(*) AS total_rows_after_disaster FROM orders;

-- ------------------------------------------------------------------------------
-- 3. O SCRIPT DE AUTO-CURA (DISASTER RECOVERY): RESTAURANDO A PARTIR DA CÓPIA
-- ------------------------------------------------------------------------------
-- Como restaurar APENAS os registros que foram apagados, sem gerar erro 1062
-- de chave primária duplicada para os registros que continuam vivos?
-- Usamos INSERT INTO ... SELECT combinado com WHERE NOT EXISTS:
-- ------------------------------------------------------------------------------

INSERT INTO orders
SELECT *
FROM orders_snapshot_master b
WHERE NOT EXISTS (
    SELECT 1 
    FROM orders o 
    WHERE o.order_id = b.order_id
);

-- Conferindo que a tabela orders foi 100% restaurada e curada (10 linhas de volta!):
SELECT COUNT(*) AS total_rows_restored FROM orders;

-- ------------------------------------------------------------------------------
-- 4. STORED PROCEDURE AUTOMATIZADA DE SNAPSHOT COM DATA E HORA
-- ------------------------------------------------------------------------------
-- Em vez de digitar os comandos manualmente, criamos um procedimento que recebe
-- o nome da tabela e gera automaticamente uma cópia datada usando Dynamic SQL:
-- ------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_create_quick_snapshot;

DELIMITER //

CREATE PROCEDURE sp_create_quick_snapshot(IN p_source_table VARCHAR(64))
BEGIN
    SET @backup_table_name = CONCAT(p_source_table, '_snap_', DATE_FORMAT(NOW(), '%Y%m%d_%H%i%s'));

    -- 1. Cria a estrutura com LIKE
    SET @sql_create = CONCAT('CREATE TABLE ', @backup_table_name, ' LIKE ', p_source_table);
    PREPARE stmt_create FROM @sql_create;
    EXECUTE stmt_create;
    DEALLOCATE PREPARE stmt_create;

    -- 2. Popula com INSERT INTO ... SELECT
    SET @sql_insert = CONCAT('INSERT INTO ', @backup_table_name, ' SELECT * FROM ', p_source_table);
    PREPARE stmt_insert FROM @sql_insert;
    EXECUTE stmt_insert;
    DEALLOCATE PREPARE stmt_insert;

    SELECT CONCAT('Snapshot criado com sucesso: ', @backup_table_name) AS status_message;
END //

DELIMITER ;

-- Executando a procedure para criar um snapshot instantâneo da tabela 'shippers':
CALL sp_create_quick_snapshot('shippers');

-- ------------------------------------------------------------------------------
-- 5. AUDITORIA DE CONSUMO DE DISCO DAS TABELAS CÓPIAS NO DICIONÁRIO
-- ------------------------------------------------------------------------------

SELECT 
    table_name,
    table_rows,
    ROUND((data_length + index_length) / 1024, 2) AS total_size_kb
FROM information_schema.tables
WHERE table_schema = 'sql_store'
  AND (table_name LIKE '%backup%' OR table_name LIKE '%snap%' OR table_name LIKE '%archive%');
