-- ==============================================================================
-- CAPÍTULO 39 (PARTE 3 FINAL MASTERCLASS): RESTAURAÇÃO TOTAL DOS BANCOS DE DADOS
-- Curso: Complete SQL Mastery (Mosh Hamedani)
-- Bancos Alvo: sql_hr, sql_inventory e Auditoria Geral de Todos os 4 Bancos
-- Arquivo: capitulo_39_restoring_the_databases_parte_3_final.sql
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. DESATIVAÇÃO TEMPORÁRIA DE VERIFICAÇÃO DE CHAVES ESTRANGEIRAS
-- Em scripts de restauração em lote (Dumps / Seeds), desativar o FOREIGN_KEY_CHECKS
-- evita que a remoção ou criação de tabelas falhe devido a dependências circulares.
-- ------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------------------------
-- 2. RESTAURAÇÃO COMPLETA DO BANCO 'sql_hr' (RECURSOS HUMANOS / RH)
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS sql_hr;
CREATE DATABASE sql_hr;
USE sql_hr;

-- 2.1. Tabela 'offices' (Escritórios regionais da empresa)
CREATE TABLE offices (
    office_id INT PRIMARY KEY AUTO_INCREMENT,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL
);

INSERT INTO offices (office_id, address, city, state) VALUES 
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

-- 2.2. Tabela 'employees' (Funcionários e hierarquia de liderança via self-join)
CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    salary INT NOT NULL,
    reports_to INT,
    office_id INT NOT NULL,
    KEY fk_employees_offices_idx (office_id),
    KEY fk_employees_employees_idx (reports_to),
    CONSTRAINT fk_employees_offices FOREIGN KEY (office_id) 
        REFERENCES offices (office_id) ON UPDATE CASCADE,
    CONSTRAINT fk_employees_managers FOREIGN KEY (reports_to) 
        REFERENCES employees (employee_id) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO employees (employee_id, first_name, last_name, job_title, salary, reports_to, office_id) VALUES 
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

-- ------------------------------------------------------------------------------
-- 3. RESTAURAÇÃO COMPLETA DO BANCO 'sql_inventory' (CATÁLOGO / ESTOQUE)
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS sql_inventory;
CREATE DATABASE sql_inventory;
USE sql_inventory;

-- 3.1. Tabela 'products'
CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    quantity_in_stock INT NOT NULL,
    unit_price DECIMAL(4, 2) NOT NULL
);

INSERT INTO products (product_id, name, quantity_in_stock, unit_price) VALUES 
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
-- 4. REATIVAÇÃO DO CONTROLE DE INTEGRIDADE REFERENCIAL
-- ------------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 1;

-- ------------------------------------------------------------------------------
-- 5. AUDITORIA CONSOLIDADA DE TODOS OS 4 BANCOS DE DADOS DO CURSO
-- Consulta ao INFORMATION_SCHEMA para certificar que todas as 12 tabelas
-- do curso existem com o total esperado de registros.
-- ------------------------------------------------------------------------------
SELECT 
    table_schema AS database_name,
    table_name,
    table_rows AS estimated_row_count
FROM information_schema.tables
WHERE table_schema IN ('sql_store', 'sql_invoicing', 'sql_hr', 'sql_inventory')
ORDER BY table_schema, table_name;
