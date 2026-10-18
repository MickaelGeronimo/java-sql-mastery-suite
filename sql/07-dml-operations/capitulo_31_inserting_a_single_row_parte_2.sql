-- ==============================================================================
-- CAPÍTULO 31 (PARTE 2): REORDENAÇÃO, EXPRESSÕES DINÂMICAS E ERROS DO INSERT
-- Inversão da Ordem das Colunas, Funções no VALUES (CURDATE, UPPER, Matemática),
-- Inserção na Tabela 'orders' e os 4 Erros Capitais de Inserção
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 3
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_31_inserting_a_single_row_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. REORDENAÇÃO COMPLETA DAS COLUNAS NO INSERT
-- ------------------------------------------------------------------------------
-- O autor Mosh explica que NÃO precisamos seguir a ordem física do banco de dados!
-- Podemos embaralhar a ordem das colunas como quisermos, desde que os valores
-- no VALUES acompanhem rigorosamente a mesma ordem declarada entre os parênteses:
-- ------------------------------------------------------------------------------

INSERT INTO customers (
    state,
    last_name,
    first_name,
    city,
    address,
    birth_date,
    points
)
VALUES (
    'RJ',
    'Ferreira',
    'Juliana',
    'Rio de Janeiro',
    'Av. Atlântica 500',
    '1992-08-15',
    250
);

-- ------------------------------------------------------------------------------
-- 2. INSERÇÃO COM EXPRESSÕES E FUNÇÕES DINÂMICAS NO VALUES
-- ------------------------------------------------------------------------------
-- No VALUES não precisamos passar apenas textos estáticos ("hardcoded").
-- Podemos usar funções do SQL como CURDATE(), UPPER(), CONCAT() e cálculos matemáticos:
-- ------------------------------------------------------------------------------

INSERT INTO customers (
    first_name,
    last_name,
    birth_date,
    address,
    city,
    state,
    points
)
VALUES (
    UPPER('roberto'),
    CONCAT('Car', 'valho'),
    DATE_SUB(CURDATE(), INTERVAL 30 YEAR), -- Calcula data de nascimento de quem tem 30 anos
    'Rua das Palmeiras 45',
    'Curitiba',
    'PR',
    (50 * 2) + 100                          -- Bônus calculado: 200 pontos promocionais
);

-- ------------------------------------------------------------------------------
-- 3. INSERINDO EM TABELA RELACIONADA: A TABELA 'orders'
-- ------------------------------------------------------------------------------
-- Inserimos um novo pedido associado a um cliente existente (ex: cliente ID 1).
-- Omitimos order_id (Auto Increment) e os campos opcionais (shipped_date, shipper_id, comments):
-- ------------------------------------------------------------------------------

INSERT INTO orders (
    customer_id,
    order_date,
    status
)
VALUES (
    1,
    CURDATE(),
    1
);

-- ------------------------------------------------------------------------------
-- 4. DEMONSTRAÇÃO DOS 4 ERROS CAPITAIS DO COMANDO INSERT
-- ------------------------------------------------------------------------------

-- ERRO 1: Omitir coluna Not Null sem Default (Erro 1364)
-- INSERT INTO customers (first_name) VALUES ('Pedro');
-- ERROR 1364 (HY000): Field 'last_name' doesn't have a default value

-- ERRO 2: Descompasso entre número de colunas e valores (Erro 1136)
-- INSERT INTO customers (first_name, last_name) VALUES ('Pedro');
-- ERROR 1136 (21S01): Column count doesn't match value count at row 1

-- ERRO 3: Texto maior que o limite da coluna (Erro 1406 no modo estrito)
-- INSERT INTO customers (first_name, last_name, address, city, state)
-- VALUES ('A', 'B', 'C', 'D', 'RIO DE JANEIRO'); -- state é CHAR(2)!
-- ERROR 1406 (22001): Data too long for column 'state' at row 1

-- ERRO 4: Duplicar Chave Primária (Erro 1062)
-- INSERT INTO customers (customer_id, first_name, last_name, address, city, state)
-- VALUES (1, 'Clone', 'Silva', 'Rua 1', 'Cidade', 'SP');
-- ERROR 1062 (23000): Duplicate entry '1' for key 'customers.PRIMARY'
