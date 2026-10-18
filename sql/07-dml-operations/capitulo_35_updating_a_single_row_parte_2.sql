-- ==============================================================================
-- CAPÍTULO 35 (PARTE 2): CONCORRÊNCIA, BLOQUEIOS E ATUALIZAÇÃO SEGURA
-- 1. O Problema da Atualização Perdida (Lost Update Problem)
-- 2. Bloqueio Pessimista com SELECT ... FOR UPDATE (Row-Level Locking)
-- 3. Bloqueio Otimista com Coluna de Versão (Optimistic Locking com version)
-- 4. O Erro 1205 (Lock Wait Timeout Exceeded)
-- 5. Trigger de Auditoria no UPDATE (OLD vs NEW)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 7 (Módulo Avançado de Concorrência)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_35_updating_a_single_row_parte_2.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. ADICIONANDO UMA COLUNA DE VERSÃO PARA O BLOQUEIO OTIMISTA
-- ------------------------------------------------------------------------------
-- Em arquiteturas corporativas modernas (Java / Spring Data / Hibernate),
-- cada tabela possui uma coluna 'version' para prevenir sobrescritas acidentais:
-- ------------------------------------------------------------------------------

-- Adicionando a coluna version caso não exista:
ALTER TABLE invoices 
ADD COLUMN IF NOT EXISTS version INT NOT NULL DEFAULT 1;

-- ------------------------------------------------------------------------------
-- 2. BLOQUEIO PESSIMISTA: 'SELECT ... FOR UPDATE' (EXCLUSÃO TOTAL DE CONCORRÊNCIA)
-- ------------------------------------------------------------------------------
-- Quando a Conexão 1 executa SELECT ... FOR UPDATE, o MySQL tranca fisicamente
-- a linha da fatura 1 no InnoDB. Nenhuma outra transação pode alterá-la até o COMMIT:
-- ------------------------------------------------------------------------------

START TRANSACTION;

-- Trancando a linha 1 exclusivamente:
SELECT invoice_id, payment_total, version
FROM invoices 
WHERE invoice_id = 1
FOR UPDATE;

-- Realizando o pagamento seguro com incremento atômico:
UPDATE invoices
SET 
    payment_total = payment_total + 25.00,
    payment_date = '2019-03-05'
WHERE invoice_id = 1;

-- Liberando o tranco para os outros usuários:
COMMIT;

-- ------------------------------------------------------------------------------
-- 3. BLOQUEIO OTIMISTA: ATUALIZANDO COM CHECAGEM DE VERSÃO (WHERE version = X)
-- ------------------------------------------------------------------------------
-- Se dois usuários lerem a fatura na versão 1:
-- O Usuário A executa primeiro e passa a versão para 2:
-- ------------------------------------------------------------------------------

UPDATE invoices
SET 
    payment_total = payment_total + 10.00,
    version = version + 1
WHERE invoice_id = 1 AND version = 1;

-- O Usuário B tenta atualizar achando que a fatura ainda está na versão 1:
-- O MySQL encontra ZERO linhas correspondentes (Rows matched: 0)!
UPDATE invoices
SET 
    payment_total = payment_total + 20.00,
    version = version + 1
WHERE invoice_id = 1 AND version = 1;

-- No Java, 'Rows matched: 0' dispara imediatamente um OptimisticLockException!

-- ------------------------------------------------------------------------------
-- 4. CRIANDO UMA TABELA DE AUDITORIA DE ALTERAÇÕES (invoice_audit_log)
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS invoice_audit_log;

CREATE TABLE invoice_audit_log (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT NOT NULL,
    old_payment_total DECIMAL(9, 2),
    new_payment_total DECIMAL(9, 2),
    difference DECIMAL(9, 2),
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- 5. TRIGGER DE AUDITORIA NO UPDATE (COMPARANDO OLD vs NEW)
-- ------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tr_invoices_after_update;

DELIMITER //

CREATE TRIGGER tr_invoices_after_update
AFTER UPDATE ON invoices
FOR EACH ROW
BEGIN
    -- Se o valor do pagamento foi alterado, registra a auditoria:
    IF OLD.payment_total <> NEW.payment_total THEN
        INSERT INTO invoice_audit_log (
            invoice_id,
            old_payment_total,
            new_payment_total,
            difference
        )
        VALUES (
            NEW.invoice_id,
            OLD.payment_total,
            NEW.payment_total,
            NEW.payment_total - OLD.payment_total
        );
    END IF;
END //

DELIMITER ;

-- Testando o trigger: alterando a fatura 2
UPDATE invoices
SET 
    payment_total = 50.00,
    payment_date = '2019-06-15'
WHERE invoice_id = 2;

-- Consultando o log de auditoria gerado pelo trigger:
SELECT * FROM invoice_audit_log;
