-- ==============================================================================
-- CAPÍTULO 39 (PARTE 1): RESTAURANDO OS BANCOS DE DADOS (RESTORING THE DATABASES)
-- Fechamento da Seção 4: Inserindo, Atualizando e Deletando Dados
-- O Reset de Fábrica Idempotente (DROP DATABASE / CREATE DATABASE / SEEDING),
-- Recuperação de Dados Deletados e Restauração de Integridade
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 11
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_39_restoring_the_databases_parte_1.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. O CONTEXTO DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- Ao longo dos capítulos 30 a 38, realizamos dezenas de alterações nos bancos:
--   - Inserimos novos clientes e pedidos em 'sql_store'.
--   - Atualizamos pontos de clientes e faturas em 'sql_invoicing'.
--   - Excluímos a fatura 1 e as faturas da 'Myworks'.
--
-- Para que possamos iniciar as próximas seções do curso (Agrupamento, Subconsultas
-- Complexas, Views, Triggers e Transações) em um ambiente 100% limpo e idêntico
-- ao do instrutor, precisamos restaurar os bancos de dados ao estado original!
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 2. RESTAURAÇÃO IDEMPOTENTE DO BANCO 'sql_invoicing'
-- ------------------------------------------------------------------------------
-- O padrão de restauração limpa descarta o schema inteiro e recria tudo do zero:
-- ------------------------------------------------------------------------------

DROP DATABASE IF EXISTS sql_invoicing;
CREATE DATABASE sql_invoicing;
USE sql_invoicing;

-- 2.1. Recriando a tabela 'payment_methods'
CREATE TABLE payment_methods (
    payment_method_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO payment_methods VALUES (1, 'Credit Card');
INSERT INTO payment_methods VALUES (2, 'Cash');
INSERT INTO payment_methods VALUES (3, 'PayPal');
INSERT INTO payment_methods VALUES (4, 'Wire Transfer');

-- 2.2. Recriando a tabela 'clients'
CREATE TABLE clients (
    client_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state CHAR(2) NOT NULL,
    phone VARCHAR(50) NOT NULL
);

INSERT INTO clients VALUES (1, 'Vinte', '3 Nevada Parkway', 'Syracuse', 'NY', '315-252-7305');
INSERT INTO clients VALUES (2, 'Myworks', '34267 Glendale Parkway', 'Huntington', 'NY', '631-396-1849');
INSERT INTO clients VALUES (3, 'Yadel', '096 Pawling Parkway', 'San Francisco', 'CA', '415-144-6037');
INSERT INTO clients VALUES (4, 'Kwamlat', '865 Kropf Center', 'Orlando', 'FL', '407-313-1772');
INSERT INTO clients VALUES (5, 'Topiclounge', '0863 Farmco Road', 'Portland', 'OR', '971-888-9999');

-- 2.3. Recriando a tabela 'invoices' (com todas as Chaves Estrangeiras e restrições)
CREATE TABLE invoices (
    invoice_id INT PRIMARY KEY,
    number VARCHAR(50) NOT NULL,
    client_id INT NOT NULL,
    invoice_total DECIMAL(9, 2) NOT NULL,
    payment_total DECIMAL(9, 2) NOT NULL DEFAULT 0.00,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE NULL,
    CONSTRAINT fk_invoices_clients FOREIGN KEY (client_id) REFERENCES clients (client_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Inserindo o lote original de 5 faturas principais:
INSERT INTO invoices VALUES (1, '91-953-3396', 1, 101.79, 0.00, '2019-03-09', '2019-03-29', NULL);
INSERT INTO invoices VALUES (2, '03-898-6735', 2, 175.32, 8.18, '2019-06-11', '2019-07-01', '2019-02-12');
INSERT INTO invoices VALUES (3, '20-295-2670', 3, 147.99, 0.00, '2019-07-29', '2019-08-18', NULL);
INSERT INTO invoices VALUES (4, '61-645-1249', 3, 152.15, 0.00, '2019-01-30', '2019-02-19', NULL);
INSERT INTO invoices VALUES (5, '17-562-3155', 4, 169.36, 0.00, '2019-02-12', '2019-03-04', NULL);

-- 2.4. Recriando a tabela 'payments'
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    invoice_id INT NOT NULL,
    date DATE NOT NULL,
    amount DECIMAL(9, 2) NOT NULL,
    payment_method TINYINT NOT NULL,
    CONSTRAINT fk_payments_clients FOREIGN KEY (client_id) REFERENCES clients (client_id) ON UPDATE CASCADE,
    CONSTRAINT fk_payments_invoices FOREIGN KEY (invoice_id) REFERENCES invoices (invoice_id) ON UPDATE CASCADE,
    CONSTRAINT fk_payments_payment_methods FOREIGN KEY (payment_method) REFERENCES payment_methods (payment_method_id)
);

INSERT INTO payments VALUES (1, 5, 2, '2019-02-12', 8.18, 1);
INSERT INTO payments VALUES (2, 1, 6, '2019-01-03', 74.55, 1);
INSERT INTO payments VALUES (3, 3, 11, '2019-01-15', 0.03, 1);
INSERT INTO payments VALUES (4, 2, 13, '2019-01-15', 0.03, 1);

-- ------------------------------------------------------------------------------
-- 3. AUDITORIA PÓS-RESTAURAÇÃO: CERTIFICANDO QUE O BANCO ESTÁ 100% PRISTINO
-- ------------------------------------------------------------------------------

-- 1. A fatura 1 (que havia sido deletada) foi ressuscitada com sucesso?
SELECT invoice_id, number, client_id, invoice_total, payment_total, payment_date
FROM invoices
WHERE invoice_id = 1;

-- 2. As faturas da 'Myworks' (client_id = 2) foram restauradas com sucesso?
SELECT invoice_id, number, client_id, invoice_total, payment_total, payment_date
FROM invoices
WHERE client_id = 2;

-- 3. Total consolidado de faturas restauradas:
SELECT COUNT(*) AS total_faturas_ativas FROM invoices;
