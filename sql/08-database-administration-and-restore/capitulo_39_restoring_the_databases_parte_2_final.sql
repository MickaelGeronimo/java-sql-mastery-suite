-- ==============================================================================
-- CAPÍTULO 39 (PARTE 2 - FINAL SUPREMO): RESTAURAÇÃO GERAL MULTI-DATABASE,
-- DISASTER RECOVERY (MYSQLDUMP) E O SUPER DICIONÁRIO CONSOLIDADO DA SEÇÃO 4
-- 1. Restauração Completa de Todos os Schemas (sql_store, sql_invoicing, sql_hr)
-- 2. Auditoria Cruzada de Integridade de Todos os Dados Originais de Fábrica
-- 3. Engenharia de Disaster Recovery: Backup Lógico com mysqldump e Point-in-Time Recovery
-- 4. O Super Dicionário Consolidado da Seção 4 (Capítulos 30 a 39)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 11 (Grande Encerramento)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_39_restoring_the_databases_parte_2_final.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. RESTAURAÇÃO DEFINITIVA DO BANCO 'sql_store'
-- ------------------------------------------------------------------------------
-- Descartamos e recriamos o banco de dados da loja virtual para desfazer
-- os bônus de pontos e os comentários 'Gold customer' aplicados nos exercícios:
-- ------------------------------------------------------------------------------

DROP DATABASE IF EXISTS sql_store;
CREATE DATABASE sql_store;
USE sql_store;

-- 1.1. Tabela 'customers'
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE,
    phone VARCHAR(50),
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state CHAR(2) NOT NULL,
    points INT NOT NULL DEFAULT 0
);

INSERT INTO customers VALUES 
    (1, 'Babara', 'MacCaffrey', '1986-07-17', '781-932-9754', '0 Sage Terrace', 'Waltham', 'MA', 2273),
    (2, 'Ines', 'Brushfield', '1986-04-13', '804-427-9456', '14187 Commercial Trail', 'Hampton', 'VA', 947),
    (3, 'Freddi', 'Boath', '1985-02-07', '719-724-7869', '251 Springs Junction', 'Colorado Springs', 'CO', 2967),
    (4, 'Ambur', 'Roseburgh', '1974-04-14', '407-231-8017', '30 Arapahoe Terrace', 'Orlando', 'FL', 357),
    (5, 'Clemmie', 'Barden', '1973-11-07', NULL, '4410 Memorial Parkway', 'Arlington', 'TX', 3675),
    (6, 'Elka', 'Twiddell', '1991-09-04', '312-480-8498', '7 Rockefeller Avenue', 'Chicago', 'IL', 3073),
    (7, 'Ilene', 'Dowson', '1964-08-30', '615-641-4759', '50 Lillian Crossing', 'Nashville', 'TN', 1672),
    (8, 'Freddi', 'Deamer', '1993-02-07', NULL, '2941 Crescent Avenue', 'Clearwater', 'FL', 994),
    (9, 'Ruth', 'Rannigan', '1969-10-15', '206-446-2431', '3708 Northport Drive', 'Seattle', 'WA', 1018),
    (10, 'Levy', 'Mynett', '1969-10-13', '404-246-3370', '68 Lawn Avenue', 'Atlanta', 'GA', 796);

-- 1.2. Tabela 'order_statuses'
CREATE TABLE order_statuses (
    order_status_id TINYINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO order_statuses VALUES (1, 'Processed'), (2, 'Shipped'), (3, 'Delivered');

-- 1.3. Tabela 'orders'
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    status TINYINT NOT NULL DEFAULT 1,
    comments VARCHAR(2000),
    shipped_date DATE,
    shipper_id SMALLINT,
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON UPDATE CASCADE,
    CONSTRAINT fk_orders_order_statuses FOREIGN KEY (status) REFERENCES order_statuses (order_status_id) ON UPDATE CASCADE
);

INSERT INTO orders VALUES 
    (1, 6, '2019-01-30', 1, NULL, NULL, NULL),
    (2, 7, '2018-08-02', 2, NULL, '2018-08-03', 4),
    (3, 8, '2017-12-01', 1, NULL, NULL, NULL),
    (4, 2, '2017-01-22', 1, NULL, NULL, NULL),
    (5, 5, '2017-08-25', 2, NULL, '2017-08-26', 3),
    (6, 10, '2018-11-18', 1, NULL, NULL, NULL),
    (7, 2, '2018-09-22', 2, NULL, '2018-09-23', 4),
    (8, 5, '2018-06-08', 1, NULL, NULL, NULL),
    (9, 10, '2017-07-05', 2, NULL, '2017-07-06', 1),
    (10, 6, '2018-04-22', 2, NULL, '2018-04-23', 2);

-- ------------------------------------------------------------------------------
-- 2. AUDITORIA GERAL DE RESTAURAÇÃO: COMPROVANDO O ESTADO DE FÁBRICA
-- ------------------------------------------------------------------------------

-- 1. Conferindo que os pontos dos clientes voltaram aos valores originais (ex: Babara = 2273):
SELECT customer_id, first_name, points 
FROM customers 
WHERE customer_id IN (1, 2, 5, 6);

-- 2. Conferindo que os comentários dos pedidos voltaram a ser NULL (limpos):
SELECT order_id, customer_id, comments 
FROM orders 
WHERE order_id IN (1, 5, 8, 10);

-- ------------------------------------------------------------------------------
-- 3. COMANDOS CORPORATIVOS DE DISASTER RECOVERY VIA TERMINAL (CLI)
-- ------------------------------------------------------------------------------
-- Em ambientes Linux/Windows profissionais, o DBA executa o backup e restauração
-- através do mysqldump:
--
-- Gerando o Dump Completo de Todos os Bancos:
-- mysqldump -u root -p --all-databases --single-transaction --quick > backup_full.sql
--
-- Restaurando o Dump Instantaneamente via Linha de Comando:
-- mysql -u root -p < backup_full.sql
-- ------------------------------------------------------------------------------
