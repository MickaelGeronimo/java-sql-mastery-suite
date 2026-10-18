-- ==============================================================================
-- CAPÍTULO 36 (PARTE 3 - FINAL SUPREMO): ATUALIZAÇÕES EM MASSA DE ALTA ESCALA
-- A Cláusula LIMIT no UPDATE do MySQL, Estratégia de Chunking (Lotes Fracionados),
-- Stored Procedure com ROW_COUNT(), O Padrão CTE no PostgreSQL 18 e o Dicionário Supremo
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 8 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_36_updating_multiple_rows_parte_3_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A CLÁUSULA LIMIT E ORDER BY NO COMANDO UPDATE (RECURSO NATIVO DO MYSQL)
-- ------------------------------------------------------------------------------
-- Em bancos de dados de produção com milhões de registros, nunca atualizamos
-- tudo de uma vez. O MySQL permite usar ORDER BY e LIMIT para atualizar
-- uma fatia segura de registros por vez:
-- ------------------------------------------------------------------------------

-- Inspecionando clientes que possuem menos de 1000 pontos:
SELECT customer_id, first_name, points
FROM customers
WHERE points < 1000
ORDER BY customer_id;

-- Desativando Safe Updates para a sessão:
SET SQL_SAFE_UPDATES = 0;

-- Atualizando APENAS os 2 primeiros clientes com menos de 1000 pontos:
UPDATE customers
SET points = points + 25
WHERE points < 1000
ORDER BY customer_id
LIMIT 2;

-- Verificando quantas linhas foram afetadas através da função ROW_COUNT():
SELECT ROW_COUNT() AS linhas_afetadas_no_lote;

SET SQL_SAFE_UPDATES = 1;

-- ------------------------------------------------------------------------------
-- 2. STORED PROCEDURE DE CHUNKING (PROCESSAMENTO EM LOTES FRACIONADOS)
-- ------------------------------------------------------------------------------
-- Em Big Data corporativo, se tentarmos atualizar 5 milhões de linhas em uma só
-- transação, o banco sofrerá:
--   1. Esgotamento do Buffer Pool do InnoDB.
--   2. Travamento de milhares de conexões simultâneas (Error 1205: Lock wait timeout).
--   3. Lag gigantesco de replicação para as réplicas de leitura.
--
-- A solução sênior é a Procedure de Chunking: atualiza lotes pequenos
-- repetidamente em loop até que não sobre nenhuma linha pendente:
-- ------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_chunked_bonus_update;

DELIMITER //

CREATE PROCEDURE sp_chunked_bonus_update(
    IN p_chunk_size INT,
    OUT p_total_updated INT
)
BEGIN
    DECLARE v_rows_updated INT DEFAULT 1;
    SET p_total_updated = 0;

    -- Enquanto houver linhas sendo atualizadas no lote anterior:
    WHILE v_rows_updated > 0 DO
        -- Atualiza uma fatia controlada (p_chunk_size) concedendo bônus e marcando
        UPDATE customers
        SET points = 1000
        WHERE points < 1000
        LIMIT p_chunk_size;

        -- Captura quantas linhas este lote específico alterou
        SET v_rows_updated = ROW_COUNT();
        SET p_total_updated = p_total_updated + v_rows_updated;
    END WHILE;
END //

DELIMITER ;

-- Testando a procedure com pedaços de 2 em 2 registros:
SET SQL_SAFE_UPDATES = 0;
CALL sp_chunked_bonus_update(2, @total_geral_atualizado);
SET SQL_SAFE_UPDATES = 1;

SELECT @total_geral_atualizado AS total_clientes_atualizados_em_lotes;

-- ------------------------------------------------------------------------------
-- 3. COMPARATIVO CROSS-ENGINE: COMO FAZER CHUNKING NO POSTGRESQL 18?
-- ------------------------------------------------------------------------------
-- No PostgreSQL, o comando UPDATE NÃO aceita a cláusula LIMIT diretamente!
-- Engenheiros sênior utilizam uma CTE (Common Table Expression) com ctid ou id:
-- ------------------------------------------------------------------------------
/*
WITH batch AS (
    SELECT customer_id
    FROM customers
    WHERE points < 1000
    ORDER BY customer_id
    LIMIT 2
    FOR UPDATE SKIP LOCKED
)
UPDATE customers c
SET points = 1000
FROM batch b
WHERE c.customer_id = b.customer_id;
*/
