-- ==============================================================================
-- CAPÍTULO 39 (PARTE 4 - FINAL SUPREMO DA SEÇÃO 4): O SCRIPT MESTRE UNIFICADO
-- Curso: Complete SQL Mastery (Mosh Hamedani)
-- Restauração Transversal dos 4 Bancos: sql_store, sql_invoicing, sql_hr, sql_inventory
-- Arquivo: capitulo_39_restoring_the_databases_parte_4_final.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- ETAPA 0: CONFIGURAÇÕES GLOBAIS DE SEGURANÇA E ENCODING
-- Desativa verificações de chave estrangeira temporariamente para permitir
-- destruição e reconstrução atômica sem dependências de ordem de execução.
-- ------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
SET NAMES utf8mb4;

-- ==============================================================================
-- 1. BANCO DE DADOS: sql_store (E-COMMERCE / LOJA VIRTUAL)
-- ==============================================================================
DROP DATABASE IF EXISTS sql_store;
CREATE DATABASE sql_store CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE sql_store;

-- 1.1 Tabela de Clientes
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

INSERT INTO customers (customer_id, first_name, last_name, birth_date, phone, address, city, state, points) VALUES
(1, 'Babara', 'MacCaffrey', '1986-03-28', '781-932-9754', '0 Nevada Parkway', 'Huntington', 'WV', 2273),
(2, 'Ines', 'Brushfield', '1986-04-13', '804-427-9456', '14187 Commercial Trail', 'Hampton', 'VA', 947),
(3, 'Freddi', 'Boagey', '1985-02-07', '719-724-7869', '251 Springs Junction', 'Colorado Springs', 'CO', 2967),
(4, 'Ambur', 'Roseburgh', '1974-04-14', '407-231-8017', '30 Arapahoe Terrace', 'Orlando', 'FL', 457),
(5, 'Clemmie', 'Bagehot', '1973-11-07', NULL, '48140 New Castle Court', 'Arlington', 'TX', 3675),
(6, 'Elka', 'Twiddell', '1991-09-04', '312-480-8498', '7 Manley Drive', 'Chicago', 'IL', 3073),
(7, 'Ilene', 'Dowson', '1964-08-30', '615-641-4759', '50 Lillian Boulevard', 'Nashville', 'TN', 1672),
(8, 'Lucien', 'Rallings', '1976-12-06', '706-538-4228', '720 Welch Avenue', 'Augusta', 'GA', 1586),
(9, 'Romola', 'Rumgay', '1992-05-23', '559-181-3744', '3520 Lotheville Alley', 'Fresno', 'CA', 2065),
(10, 'Levy', 'Mynett', '1969-10-13', '404-246-3370', '68 Lawn Avenue', 'Atlanta', 'GA', 796);

-- 1.2 Tabela de Status dos Pedidos
CREATE TABLE order_statuses (
    order_status_id TINYINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO order_statuses VALUES 
(1, 'Processed'),
(2, 'Shipped'),
(3, 'Delivered');

-- 1.3 Tabela de Pedidos
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    status TINYINT NOT NULL DEFAULT 1,
    comments VARCHAR(2000),
    shipped_date DATE,
    shipper_id INT,
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) 
        REFERENCES customers (customer_id) ON UPDATE CASCADE,
    CONSTRAINT fk_orders_order_statuses FOREIGN KEY (status) 
        REFERENCES order_statuses (order_status_id) ON UPDATE CASCADE
);

INSERT INTO orders (order_id, customer_id, order_date, status, comments, shipped_date, shipper_id) VALUES
(1, 6, '2019-01-30', 1, NULL, NULL, NULL),
(2, 7, '2018-08-02', 2, NULL, '2018-08-03', 4),
(3, 8, '2017-12-01', 1, NULL, NULL, NULL),
(4, 2, '2017-01-22', 1, NULL, NULL, NULL),
(5, 5, '2017-08-25', 2, '', '2017-08-26', 3),
(6, 10, '2018-11-18', 1, 'Aliquam erat volutpat.', NULL, NULL),
(7, 2, '2018-09-22', 2, NULL, '2018-09-23', 4),
(8, 5, '2018-06-08', 1, 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', NULL, NULL),
(9, 10, '2017-07-05', 2, 'Nulla mollis molestie lorem.', '2017-07-06', 1),
(10, 6, '2018-04-22', 2, NULL, '2018-04-23', 2);

-- 1.4 Tabela de Itens do Pedido
CREATE TABLE order_items (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(4, 2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_order_items_orders FOREIGN KEY (order_id) 
        REFERENCES orders (order_id) ON UPDATE CASCADE
);

INSERT INTO order_items VALUES 
(1, 4, 4, 3.74), (2, 1, 2, 9.10), (2, 4, 4, 1.66), (2, 6, 2, 2.94),
(3, 3, 10, 9.12), (4, 3, 7, 6.99), (4, 10, 7, 6.40), (5, 2, 3, 9.89),
(6, 1, 4, 8.65), (6, 2, 4, 3.28), (6, 3, 4, 7.46), (6, 5, 1, 3.45),
(7, 3, 7, 9.17), (8, 5, 2, 6.94), (8, 8, 2, 8.59), (9, 6, 5, 7.28),
(10, 1, 10, 6.01), (10, 9, 9, 4.06);

-- 1.5 Tabela de Notas dos Itens do Pedido
CREATE TABLE order_item_notes (
    note_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    note VARCHAR(255) NOT NULL
);

INSERT INTO order_item_notes (note_id, order_id, product_id, note) VALUES
(1, 1, 2, 'first note'),
(2, 1, 2, 'second note');

-- ==============================================================================
-- 2. BANCO DE DADOS: sql_invoicing (ERP FINANCEIRO / CONTABILIDADE)
-- ==============================================================================
DROP DATABASE IF EXISTS sql_invoicing;
CREATE DATABASE sql_invoicing CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE sql_invoicing;

-- 2.1 Tabela de Formas de Pagamento
CREATE TABLE payment_methods (
    payment_method_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

INSERT INTO payment_methods VALUES 
(1, 'Credit Card'),
(2, 'Cash'),
(3, 'PayPal'),
(4, 'Wire Transfer');

-- 2.2 Tabela de Clientes Financeiros
CREATE TABLE clients (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state CHAR(2) NOT NULL,
    phone VARCHAR(50)
);

INSERT INTO clients VALUES 
(1, 'Vinte', '3 Nevada Parkway', 'Syracuse', 'NY', '315-252-7305'),
(2, 'Myworks', '34267 Glendale Parkway', 'Huntington', 'WV', '304-659-1170'),
(3, 'Yadel', '096 Pawling Parkway', 'San Francisco', 'CA', '415-144-6037'),
(4, 'Kwality Food', '50 Lillian Boulevard', 'Nashville', 'TN', '615-641-4759'),
(5, 'Topiclounge', '0863 Farmco Road', 'Portland', 'OR', '971-888-9129');

-- 2.3 Tabela de Faturas (Invoices)
CREATE TABLE invoices (
    invoice_id INT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(50) NOT NULL,
    client_id INT NOT NULL,
    invoice_total DECIMAL(9, 2) NOT NULL,
    payment_total DECIMAL(9, 2) NOT NULL DEFAULT 0.00,
    invoice_date DATE NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    CONSTRAINT fk_invoices_clients FOREIGN KEY (client_id) 
        REFERENCES clients (client_id) ON UPDATE CASCADE
);

INSERT INTO invoices VALUES 
(1, '91-953-3396', 2, 101.79, 0.00, '2019-03-09', '2019-03-29', NULL),
(2, '03-898-6735', 5, 175.32, 8.18, '2019-06-11', '2019-07-01', '2019-02-12'),
(3, '20-228-0335', 5, 147.99, 0.00, '2019-07-31', '2019-08-20', NULL),
(4, '56-934-0748', 3, 152.21, 0.00, '2019-03-08', '2019-03-28', NULL),
(5, '87-508-3412', 5, 169.36, 0.00, '2019-07-18', '2019-08-07', NULL),
(6, '75-587-0626', 1, 152.53, 0.00, '2019-01-29', '2019-02-18', NULL),
(7, '68-093-9863', 3, 153.82, 0.00, '2019-07-04', '2019-07-24', NULL),
(8, '78-145-1331', 1, 189.12, 0.00, '2019-05-20', '2019-06-09', NULL),
(9, '25-224-8114', 1, 180.17, 0.00, '2019-01-30', '2019-02-19', NULL),
(10, '31-291-7294', 3, 107.29, 0.00, '2019-01-15', '2019-02-04', NULL),
(11, '83-973-4831', 5, 124.56, 0.00, '2019-07-25', '2019-08-14', NULL),
(12, '17-645-1166', 1, 168.04, 0.00, '2019-06-08', '2019-06-28', NULL),
(13, '68-052-8787', 3, 135.76, 0.00, '2019-01-17', '2019-02-06', NULL),
(14, '51-870-7231', 3, 107.80, 0.00, '2019-06-08', '2019-06-28', NULL),
(15, '84-891-9502', 1, 144.15, 0.00, '2019-06-19', '2019-07-09', NULL),
(16, '04-895-6503', 3, 174.19, 0.00, '2019-06-01', '2019-06-21', NULL),
(17, '71-635-9984', 5, 114.73, 0.00, '2019-05-24', '2019-06-13', NULL);

-- 2.4 Tabela de Pagamentos
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    invoice_id INT NOT NULL,
    date DATE NOT NULL,
    amount DECIMAL(9, 2) NOT NULL,
    payment_method TINYINT NOT NULL,
    CONSTRAINT fk_payments_clients FOREIGN KEY (client_id) 
        REFERENCES clients (client_id) ON UPDATE CASCADE,
    CONSTRAINT fk_payments_invoices FOREIGN KEY (invoice_id) 
        REFERENCES invoices (invoice_id) ON UPDATE CASCADE,
    CONSTRAINT fk_payments_payment_methods FOREIGN KEY (payment_method) 
        REFERENCES payment_methods (payment_method_id)
);

INSERT INTO payments VALUES 
(1, 5, 2, '2019-02-12', 8.18, 1),
(2, 1, 6, '2019-01-15', 10.00, 1),
(3, 3, 11, '2019-01-15', 0.03, 2),
(4, 5, 13, '2019-01-15', 4.95, 2),
(5, 3, 15, '2019-01-15', 10.00, 1),
(6, 1, 17, '2019-01-15', 20.00, 1),
(7, 5, 2, '2019-01-15', 10.00, 1),
(8, 5, 2, '2019-01-15', 10.00, 1);

-- ==============================================================================
-- 3. BANCO DE DADOS: sql_hr (RECURSOS HUMANOS / RH)
-- ==============================================================================
DROP DATABASE IF EXISTS sql_hr;
CREATE DATABASE sql_hr CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE sql_hr;

-- 3.1 Tabela de Escritórios
CREATE TABLE offices (
    office_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL
);

INSERT INTO offices VALUES 
(1, '03 Bayside Parkway', 'San Francisco', 'CA'),
(2, '4720 Lake View Court', 'Columbus', 'OH'),
(3, '67584 American Drive', 'Portland', 'OR'),
(4, '874 Nova Road', 'Atlanta', 'GA'),
(5, '45448 Fair Oaks Crossing', 'Denver', 'CO'),
(6, '24058 Talmadge Crossing', 'Seattle', 'WA'),
(7, '80400 Moulton Terrace', 'Honolulu', 'HI'),
(8, '4101 Prairie Rose Trail', 'Paterson', 'NJ'),
(9, '2880 Prairie View Way', 'Des Moines', 'IA'),
(10, '3617 Superior Trail', 'Sacramento', 'CA');

-- 3.2 Tabela de Funcionários
CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    salary INT NOT NULL,
    reports_to INT,
    office_id INT NOT NULL,
    CONSTRAINT fk_employees_offices FOREIGN KEY (office_id) 
        REFERENCES offices (office_id) ON UPDATE CASCADE,
    CONSTRAINT fk_employees_managers FOREIGN KEY (reports_to) 
        REFERENCES employees (employee_id) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO employees VALUES 
(37270, 'Yovonnda', 'Magrannell', 'Executive Vice President', 63996, NULL, 10),
(33391, 'Darcy', 'Northey', 'Account Executive', 62186, 37270, 1),
(37851, 'Sayer', 'Golson', 'Junior Executive', 65902, 37270, 2),
(40448, 'Mindy', 'Crissil', 'Action Officer', 94860, 37270, 1),
(56274, 'Keriann', 'Alloisi', 'Staff Assistant I', 110150, 37270, 1),
(63196, 'Alaster', 'Scutchin', 'Assistant Sales Agent', 121525, 37270, 2),
(67009, 'North', ' de Clerer', 'VP Product Management', 114257, 37270, 2),
(67370, 'Elladine', 'Rising', 'Social Worker', 96767, 37270, 2),
(68249, 'Nanny', 'Hacket', 'Financial Advisor', 92831, 37270, 1),
(72540, 'Guthrey', 'Iacopetti', 'Office Assistant I', 117690, 37270, 3),
(72913, 'Kassia', 'Kimble', 'Chief Design Engineer', 74547, 37270, 3),
(75900, 'Virge', 'Goodrum', 'Information Systems Manager', 54578, 37270, 3),
(76196, 'Mirilla', 'Janowski', 'Cost Accountant', 119241, 37270, 3),
(80529, 'Lynde', 'Aronson', 'Junior Executive', 77182, 37270, 4),
(80679, 'Mildrid', 'Sokale', 'Geologist II', 67987, 37270, 4),
(84950, 'Wilden', 'Shoorbrooke', 'Financial Analyst', 85228, 37270, 4),
(92736, 'Tybalt', 'Equi', 'Assistant Media Planner', 74760, 37270, 4),
(94932, 'Hellene', 'Drive', 'Human Resources Analyst II', 84461, 37270, 5),
(95166, 'Dawn', 'Megne', 'Web Developer II', 83438, 37270, 5),
(95313, 'Shannon', 'Padbury', 'Quality Engineer', 90623, 37270, 5);

-- ==============================================================================
-- 4. BANCO DE DADOS: sql_inventory (ESTOQUE E ARMAZÉM)
-- ==============================================================================
DROP DATABASE IF EXISTS sql_inventory;
CREATE DATABASE sql_inventory CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE sql_inventory;

-- 4.1 Tabela de Produtos
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    quantity_in_stock INT NOT NULL,
    unit_price DECIMAL(4, 2) NOT NULL
);

INSERT INTO products VALUES 
(1, 'Foam Dinner Plate', 70, 1.21),
(2, 'Pork - Bacon,back Peameal', 49, 4.65),
(3, 'Lettuce - Romaine, Cello', 38, 3.35),
(4, 'Brocolli - Mini Head', 90, 4.53),
(5, 'Sauce - Sambal Oelek', 94, 1.63),
(6, 'Wasabi Paste', 14, 2.01),
(7, 'Ecolab - Power Fusion', 41, 4.95),
(8, 'Squash - Butternut', 11, 1.64),
(9, 'Sole - Fillet 8 - 10 Oz', 44, 2.25),
(10, 'Spinach - Baby', 32, 1.99);

-- ------------------------------------------------------------------------------
-- ETAPA FINAL: REATIVAÇÃO DA INTEGRIDADE REFERENCIAL
-- ------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 1;

-- ------------------------------------------------------------------------------
-- VERIFICAÇÃO GERAL DO SISTEMA
-- ------------------------------------------------------------------------------
SELECT 
    table_schema AS database_name,
    COUNT(*) AS total_tables,
    SUM(table_rows) AS total_records
FROM information_schema.tables
WHERE table_schema IN ('sql_store', 'sql_invoicing', 'sql_hr', 'sql_inventory')
GROUP BY table_schema
ORDER BY table_schema;
