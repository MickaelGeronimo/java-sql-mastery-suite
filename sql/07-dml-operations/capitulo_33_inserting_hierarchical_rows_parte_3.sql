-- ==============================================================================
-- CAPÍTULO 33 (PARTE 3): PROCEDURES ARMAZENADAS, A CLÁUSULA 'RETURNING' E CHECKOUT ATÔMICO
-- 1. O Problema das Múltiplas Viagens de Rede em Checkouts Hierárquicos
-- 2. Stored Procedure 'sp_place_order' com Handler de Exceção e Rollback Automático
-- 3. A Cláusula Moderna 'RETURNING' (Padrão ANSI SQL / PostgreSQL / MariaDB)
-- 4. Inserção Hierárquica em 1 Único Comando com Data-Modifying CTE (PostgreSQL)
-- 5. Consulta e Execução da Procedure no MySQL
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 5 (Módulo Avançado de Engenharia)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_33_inserting_hierarchical_rows_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CRIANDO UMA STORED PROCEDURE PARA CHECKOUT HIERÁRQUICO ATÔMICO
-- ------------------------------------------------------------------------------
-- Em vez da aplicação Java enviar 4 comandos separados na rede (START TX, INSERT PAI,
-- INSERT FILHOS, COMMIT), empacotamos tudo dentro de um procedimento no próprio banco.
-- Isso reduz a latência de rede para apenas UMA viagem!
-- ------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_place_order;

DELIMITER //

CREATE PROCEDURE sp_place_order(
    IN p_customer_id INT,
    IN p_product_id INT,
    IN p_quantity INT,
    IN p_unit_price DECIMAL(4, 2),
    OUT p_new_order_id INT
)
BEGIN
    -- Declarando manipulador de erros: Se ocorrer qualquer erro SQL, desfaz tudo!
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET p_new_order_id = -1; -- Sinaliza erro para a aplicação
    END;

    START TRANSACTION;

    -- 1. Insere o Pai
    INSERT INTO orders (customer_id, order_date, status)
    VALUES (p_customer_id, CURDATE(), 1);

    -- 2. Captura a Chave Primária
    SET p_new_order_id = LAST_INSERT_ID();

    -- 3. Insere o Filho amarrado ao Pai
    INSERT INTO order_items (order_id, product_id, quantity, unit_price)
    VALUES (p_new_order_id, p_product_id, p_quantity, p_unit_price);

    -- 4. Confirma tudo atômica e permanentemente
    COMMIT;
END //

DELIMITER ;

-- ------------------------------------------------------------------------------
-- 2. EXECUTANDO A STORED PROCEDURE NO MYSQL
-- ------------------------------------------------------------------------------

-- Executando a compra para o cliente 4, comprando 3 unidades do Produto 1 por 2.50:
CALL sp_place_order(4, 1, 3, 2.50, @generated_order_id);

-- Consultando o ID que a procedure gerou e devolveu no parâmetro OUT:
SELECT @generated_order_id AS novo_pedido_id;

-- Conferindo que o Pai e o Filho foram gravados perfeitamente:
SELECT * FROM orders WHERE order_id = @generated_order_id;
SELECT * FROM order_items WHERE order_id = @generated_order_id;

-- ------------------------------------------------------------------------------
-- 3. O PADRÃO MODERNO RETURNING (POSTGRESQL 18 / ANSI SQL)
-- ------------------------------------------------------------------------------
-- No PostgreSQL, você não precisa de LAST_INSERT_ID() nem de procedures complexas.
-- A cláusula RETURNING devolve a chave primária gerada no próprio SELECT da query!
-- ------------------------------------------------------------------------------
/*
-- Exemplo em PostgreSQL:
INSERT INTO orders (customer_id, order_date, status)
VALUES (4, CURRENT_DATE, 1)
RETURNING order_id;

-- E com Data-Modifying CTE (Pai e Filho em UMA ÚNICA instrução SQL!):
WITH novo_pedido AS (
    INSERT INTO orders (customer_id, order_date, status)
    VALUES (4, CURRENT_DATE, 1)
    RETURNING order_id
)
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
SELECT order_id, 1, 3, 2.50
FROM novo_pedido;
*/
