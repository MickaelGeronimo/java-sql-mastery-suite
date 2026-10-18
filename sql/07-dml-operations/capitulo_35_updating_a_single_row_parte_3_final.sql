-- ==============================================================================
-- CAPÍTULO 35 (PARTE 3 - FINAL SUPREMO): STORED PROCEDURES DE PAGAMENTO,
-- VALIDAÇÃO DE OVERPAYMENT, A CLÁUSULA RETURNING E O DICIONÁRIO SUPREMO DO UPDATE
-- 1. Stored Procedure 'sp_make_payment' com Validação de Saldo e Prevenção de Overpayment
-- 2. Tratamento de Erros de Negócio com SIGNAL SQLSTATE '45000'
-- 3. A Cláusula Moderna 'UPDATE ... RETURNING' (Padrão PostgreSQL 18 / MariaDB)
-- 4. O Dicionário Supremo da Atualização Unitária (UPDATE)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 7 (Conclusão Suprema)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_35_updating_a_single_row_parte_3_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. STORED PROCEDURE 'sp_make_payment' COM REGRAS DE NEGÓCIO FINANCEIRAS
-- ------------------------------------------------------------------------------
-- Em sistemas bancários reais, a aplicação Java nunca roda um UPDATE solto.
-- Ela chama uma procedure que valida se o cliente não está pagando a mais (overpayment)
-- e calcula o saldo devedor restante em uma única viagem atômica:
-- ------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS sp_make_payment;

DELIMITER //

CREATE PROCEDURE sp_make_payment(
    IN p_invoice_id INT,
    IN p_amount DECIMAL(9, 2),
    OUT p_remaining_balance DECIMAL(9, 2)
)
BEGIN
    DECLARE v_total DECIMAL(9, 2);
    DECLARE v_current_paid DECIMAL(9, 2);

    -- 1. Busca a fatura com bloqueio pessimista (FOR UPDATE)
    SELECT invoice_total, payment_total 
    INTO v_total, v_current_paid
    FROM invoices 
    WHERE invoice_id = p_invoice_id
    FOR UPDATE;

    -- 2. Validação: A fatura existe?
    IF v_total IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro de Negócio: Fatura não encontrada no sistema!';
    END IF;

    -- 3. Validação de Overpayment: O pagamento ultrapassa o valor total da fatura?
    IF (v_current_paid + p_amount) > v_total THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro Financeiro: O valor pago ultrapassa o total da fatura (Overpayment)!';
    END IF;

    -- 4. Executa a atualização segura da linha única
    UPDATE invoices
    SET 
        payment_total = payment_total + p_amount,
        payment_date = CURDATE()
    WHERE invoice_id = p_invoice_id;

    -- 5. Calcula o saldo restante e devolve no parâmetro OUT
    SET p_remaining_balance = v_total - (v_current_paid + p_amount);
END //

DELIMITER ;

-- ------------------------------------------------------------------------------
-- 2. TESTANDO O PAGAMENTO VÁLIDO NA FATURA 1 VIA PROCEDURE
-- ------------------------------------------------------------------------------

-- Realizando um pagamento de R$ 15.00 na fatura 1:
CALL sp_make_payment(1, 15.00, @saldo_restante);

-- Consultando o saldo que ainda falta pagar retornado no parâmetro OUT:
SELECT @saldo_restante AS saldo_devedor_restante;

-- Conferindo os dados da fatura 1 atualizada:
SELECT invoice_id, invoice_total, payment_total, payment_date
FROM invoices
WHERE invoice_id = 1;

-- ------------------------------------------------------------------------------
-- 3. TESTANDO A PROTEÇÃO CONTRA PAGAMENTO A MAIOR (OVERPAYMENT COM SIGNAL)
-- ------------------------------------------------------------------------------
-- A fatura 1 tem saldo total de ~101.79. Se tentarmos pagar R$ 500.00:
-- CALL sp_make_payment(1, 500.00, @saldo);
-- -> ERROR 1644 (45000): Erro Financeiro: O valor pago ultrapassa o total da fatura (Overpayment)!

-- ------------------------------------------------------------------------------
-- 4. O PADRÃO MODERNO 'UPDATE ... RETURNING' (POSTGRESQL 18 / MARIADB)
-- ------------------------------------------------------------------------------
-- No PostgreSQL moderno, você altera e recebe a resposta do saldo na MESMA QUERY!
-- ------------------------------------------------------------------------------
/*
UPDATE invoices
SET 
    payment_total = payment_total + 15.00,
    payment_date = CURRENT_DATE
WHERE invoice_id = 1
RETURNING 
    invoice_id,
    payment_total,
    (invoice_total - payment_total) AS saldo_devedor_restante;
*/
