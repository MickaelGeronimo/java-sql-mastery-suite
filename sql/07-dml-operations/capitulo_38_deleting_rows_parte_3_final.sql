-- ==============================================================================
-- CAPÍTULO 38 (PARTE 3 - FINAL SUPREMO): A BATALHA DELETE VS TRUNCATE VS DROP,
-- CHUNKING DE ALTA ESCALA COM LIMIT E O DICIONÁRIO SUPREMO DA EXCLUSÃO
-- 1. A Batalha dos 3 Destruidores: DELETE vs TRUNCATE vs DROP
-- 2. O Impacto no AUTO_INCREMENT e nos Logs de Transação (Undo/Redo)
-- 3. Exclusão em Lotes Fracionados (Chunking) com LIMIT e ROW_COUNT()
-- 4. Comparativo Cross-Engine (PostgreSQL 18 vs MySQL 8.0)
-- 5. O Dicionário Supremo da Exclusão de Dados
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 10 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_38_deleting_rows_parte_3_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. CRIANDO UMA TABELA DE TESTES PARA A BATALHA: 'invoices_temp_purge'
-- ------------------------------------------------------------------------------
-- Criamos uma réplica com Chave Primária e Auto Incremento para demonstrar
-- o comportamento físico de cada comando:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS invoices_temp_purge;

CREATE TABLE invoices_temp_purge (
    temp_id INT PRIMARY KEY AUTO_INCREMENT,
    invoice_number VARCHAR(50) NOT NULL,
    amount DECIMAL(9, 2) NOT NULL,
    created_at DATE NOT NULL
);

-- Inserindo 5 registros de teste:
INSERT INTO invoices_temp_purge (invoice_number, amount, created_at)
VALUES 
    ('INV-001', 100.00, '2017-01-10'),
    ('INV-002', 150.00, '2017-02-15'),
    ('INV-003', 200.00, '2018-05-20'),
    ('INV-004', 250.00, '2019-08-25'),
    ('INV-005', 300.00, '2019-11-30');

-- ------------------------------------------------------------------------------
-- 2. TESTE 1: 'DELETE FROM' E O COMPORTAMENTO DO AUTO_INCREMENT
-- ------------------------------------------------------------------------------
-- O DELETE apaga as linhas, mas PRESERVA o ponteiro do AUTO_INCREMENT!
-- ------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;
DELETE FROM invoices_temp_purge WHERE temp_id IN (1, 2);
SET SQL_SAFE_UPDATES = 1;

-- Inserindo um novo registro:
INSERT INTO invoices_temp_purge (invoice_number, amount, created_at)
VALUES ('INV-006', 400.00, '2020-01-01');

-- Consultando: O novo registro assumiu temp_id = 6 (e NÃO reutilizou 1 ou 2):
SELECT * FROM invoices_temp_purge;

-- ------------------------------------------------------------------------------
-- 3. TESTE 2: 'TRUNCATE TABLE' E O RESET DO AUTO_INCREMENT
-- ------------------------------------------------------------------------------
-- O TRUNCATE é um DDL: ele destrói as páginas de dados e recria a tabela vazia!
-- O contador de AUTO_INCREMENT é RESETADO de volta para 1!
-- ------------------------------------------------------------------------------

TRUNCATE TABLE invoices_temp_purge;

-- Inserindo um novo registro após o TRUNCATE:
INSERT INTO invoices_temp_purge (invoice_number, amount, created_at)
VALUES ('INV-RESTART', 500.00, CURDATE());

-- Verificando: O registro assumiu temp_id = 1!
SELECT * FROM invoices_temp_purge;

-- ------------------------------------------------------------------------------
-- 4. EXCLUSÃO EM LOTES FRACIONADOS (CHUNKING / THROTTLING DE DELEÇÃO)
-- ------------------------------------------------------------------------------
-- Em Big Data, rodar 'DELETE FROM orders WHERE order_date < 2018' em 10 milhões
-- de linhas travará o banco. Usamos a Stored Procedure de Chunking com LIMIT:
-- ------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_chunked_delete_invoices;

DELIMITER //

CREATE PROCEDURE sp_chunked_delete_invoices(
    IN p_chunk_size INT,
    OUT p_total_deleted INT
)
BEGIN
    DECLARE v_rows_deleted INT DEFAULT 1;
    SET p_total_deleted = 0;

    WHILE v_rows_deleted > 0 DO
        -- Deleta uma fatia segura de registros por vez com LIMIT
        DELETE FROM invoices_temp_purge
        ORDER BY temp_id
        LIMIT p_chunk_size;

        -- Captura quantas linhas este lote apagou
        SET v_rows_deleted = ROW_COUNT();
        SET p_total_deleted = p_total_deleted + v_rows_deleted;
    END WHILE;
END //

DELIMITER ;

-- Executando o purge em lotes de 1 em 1:
CALL sp_chunked_delete_invoices(1, @total_apagado);

SELECT @total_apagado AS total_registros_expurgados;

-- Limpando a tabela de teste ao final:
DROP TABLE IF EXISTS invoices_temp_purge;
