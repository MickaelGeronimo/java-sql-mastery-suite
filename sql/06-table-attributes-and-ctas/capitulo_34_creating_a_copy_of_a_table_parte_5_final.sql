-- ==============================================================================
-- CAPÍTULO 34 (PARTE 5 - FINAL SUPREMO): MATERIALIZED VIEWS, CDC (CHANGE DATA CAPTURE)
-- E CÓPIA ENTRE BANCOS DISTINTOS (CROSS-DATABASE TABLE COPY)
-- 1. Cópia entre Bancos Distintos (Cross-Database: sql_store para sql_invoicing)
-- 2. Emulação de Materialized View com Tabela Cópia e Trigger de Sincronização
-- 3. Tabela Cópia com Rastreamento Histórico Temporal (CDC / Audit Trail)
-- 4. O Agendador de Eventos (Event Scheduler) para Atualização Noturna de Cópias
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 6 (Encerramento Definitivo)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_34_creating_a_copy_of_a_table_parte_5_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CÓPIA ENTRE BANCOS DE DADOS DISTINTOS (CROSS-DATABASE TABLE COPY)
-- ------------------------------------------------------------------------------
-- Em corporações, frequentemente precisamos tirar uma cópia de uma tabela do
-- banco de vendas (sql_store) e salvá-la no banco financeiro (sql_invoicing).
-- Usamos a qualificação completa: nome_do_banco.nome_da_tabela:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS sql_invoicing.orders_cross_backup;

-- Cria a tabela cópia no banco sql_invoicing baseada na estrutura do sql_store:
CREATE TABLE sql_invoicing.orders_cross_backup LIKE sql_store.orders;

-- Transfere todos os dados entre os dois bancos em um único comando:
INSERT INTO sql_invoicing.orders_cross_backup
SELECT * FROM sql_store.orders;

-- Conferindo que os dados foram copiados para o outro banco:
SELECT * FROM sql_invoicing.orders_cross_backup;

-- ------------------------------------------------------------------------------
-- 2. EMULANDO UMA 'MATERIALIZED VIEW' NO MYSQL
-- ------------------------------------------------------------------------------
-- Bancos como PostgreSQL e Oracle possuem "Views Materializadas" (tabelas cópias
-- que guardam resultados de consultas complexas).
-- No MySQL, criamos uma tabela física e a sincronizamos automaticamente via TRIGGER:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS customer_orders_summary;

CREATE TABLE customer_orders_summary (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    total_orders INT NOT NULL DEFAULT 0,
    latest_order_date DATE
);

-- Carga inicial com INSERT INTO ... SELECT:
INSERT INTO customer_orders_summary (customer_id, customer_name, total_orders, latest_order_date)
SELECT 
    c.customer_id,
    CONCAT(c.first_name, ' ', c.last_name),
    COUNT(o.order_id),
    MAX(o.order_date)
FROM customers c
LEFT JOIN orders o USING (customer_id)
GROUP BY c.customer_id, customer_name;

-- ------------------------------------------------------------------------------
-- 3. TRIGGER DE SINCRONIZAÇÃO EM TEMPO REAL DA TABELA CÓPIA
-- ------------------------------------------------------------------------------
-- Sempre que um novo pedido entrar em 'orders', a tabela cópia de resumo
-- é atualizada automaticamente em milissegundos:
-- ------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tr_sync_customer_summary_after_insert;

DELIMITER //

CREATE TRIGGER tr_sync_customer_summary_after_insert
AFTER INSERT ON orders
FOR EACH ROW
BEGIN
    UPDATE customer_orders_summary
    SET total_orders = total_orders + 1,
        latest_order_date = NEW.order_date
    WHERE customer_id = NEW.customer_id;
END //

DELIMITER ;

-- ------------------------------------------------------------------------------
-- 4. TABELA CÓPIA COM HISTÓRICO DE MUDANÇAS (CDC - CHANGE DATA CAPTURE)
-- ------------------------------------------------------------------------------
-- Em vez de sobrescrever, cada alteração gera uma nova linha com carimbo de tempo:
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS orders_audit_trail;

CREATE TABLE orders_audit_trail (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    status TINYINT NOT NULL,
    action_type ENUM('SNAPSHOT_COPY', 'REALTIME_INSERT', 'REALTIME_UPDATE') NOT NULL,
    captured_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cópia inicial catalogada como 'SNAPSHOT_COPY':
INSERT INTO orders_audit_trail (order_id, customer_id, order_date, status, action_type)
SELECT 
    order_id, customer_id, order_date, status, 'SNAPSHOT_COPY'
FROM orders;

-- Conferindo a trilha de auditoria:
SELECT * FROM orders_audit_trail;
