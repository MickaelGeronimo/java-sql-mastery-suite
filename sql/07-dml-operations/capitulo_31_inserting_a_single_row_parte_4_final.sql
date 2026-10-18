-- ==============================================================================
-- CAPÍTULO 31 (PARTE 4 FINAL SUPREMO): ENGENHARIA DE PREPARED STATEMENTS,
-- SINTAXE INSERT...SET, TRIGGERS DE AUDITORIA E FECHAMENTO DO CAPÍTULO 31
-- Declarações Preparadas (PREPARE / EXECUTE), Sintaxe INSERT SET Chave=Valor,
-- Gatilhos de Auditoria e a Formatura da Inserção Unitária
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 3
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_31_inserting_a_single_row_parte_4_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. PREPARED STATEMENTS EM SQL PURO: SEGURANÇA MÁXIMA CONTRA SQL INJECTION
-- ------------------------------------------------------------------------------
-- No desenvolvimento profissional com Java/JDBC, NUNCA concatenamos strings.
-- Usamos 'Prepared Statements' onde o plano de execução é pré-compilado pelo motor:
-- ------------------------------------------------------------------------------

PREPARE insert_customer_stmt FROM 
'INSERT INTO customers (first_name, last_name, address, city, state, points)
 VALUES (?, ?, ?, ?, ?, ?)';

SET @v_first = 'Carlos';
SET @v_last  = 'Moraes';
SET @v_addr  = 'Rua das Araucárias 100';
SET @v_city  = 'Curitiba';
SET @v_state = 'PR';
SET @v_points = 350;

EXECUTE insert_customer_stmt USING @v_first, @v_last, @v_addr, @v_city, @v_state, @v_points;

DEALLOCATE PREPARE insert_customer_stmt;

-- ------------------------------------------------------------------------------
-- 2. A SINTAXE ALTERNATIVA DO MYSQL: INSERT ... SET (CHAVE = VALOR)
-- ------------------------------------------------------------------------------
-- O MySQL possui uma sintaxe muito elegante que se assemelha ao comando UPDATE.
-- VANTAGEM: Não precisa abrir dois parênteses separados (colunas e valores).
-- Você associa cada coluna diretamente ao seu valor!
-- LIMITAÇÃO: Funciona apenas para inserir UMA ÚNICA LINHA por vez!
-- ------------------------------------------------------------------------------

INSERT INTO customers
SET 
    first_name = 'Lucas',
    last_name  = 'Macedo',
    address    = 'Rua dos Pinhais 22',
    city       = 'Porto Alegre',
    state      = 'RS',
    points     = 180;

-- ------------------------------------------------------------------------------
-- 3. VERIFICAÇÃO DOS DOIS CLIENTES INSERIDOS
-- ------------------------------------------------------------------------------

SELECT customer_id, first_name, last_name, city, state, points
FROM customers
WHERE customer_id >= 11
ORDER BY customer_id;
