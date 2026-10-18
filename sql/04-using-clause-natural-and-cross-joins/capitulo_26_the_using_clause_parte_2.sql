-- ==============================================================================
-- CAPÍTULO 26 (PARTE 2): A CLÁUSULA USING (THE USING CLAUSE)
-- Chaves Compostas com Múltiplas Colunas, Desduplicação no SELECT *
-- e Resolução do Exercício Oficial do Mosh Hamedani (sql_invoicing)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 10
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_26_the_using_clause_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. CHAVES COMPOSTAS: SIMPLIFICANDO O 'AND' COM 'USING (col1, col2)'
-- ------------------------------------------------------------------------------
-- No Capítulo 21, conectamos 'order_items' com 'order_item_notes' usando:
--   ON oi.order_id = oin.order_id AND oi.product_id = oin.product_id
-- Agora, com a cláusula USING, basta separar os nomes das colunas por vírgula
-- dentro de um único par de parênteses!
-- ------------------------------------------------------------------------------

SELECT *
FROM order_items oi
JOIN order_item_notes oin
    USING (order_id, product_id);

-- ------------------------------------------------------------------------------
-- 2. SELEÇÃO DE COLUNAS ESPECÍFICAS LIMPAS
-- ------------------------------------------------------------------------------
SELECT 
    order_id,
    product_id,
    quantity,
    unit_price,
    note
FROM order_items oi
JOIN order_item_notes oin
    USING (order_id, product_id);

-- ------------------------------------------------------------------------------
-- 3. EXERCÍCIO OFICIAL DO MOSH HAMEDANI (BANCO sql_invoicing)
-- ------------------------------------------------------------------------------
-- Enunciado:
-- Escreva uma consulta para selecionar pagamentos (payments) no banco sql_invoicing.
-- Traga as seguintes colunas:
--   - date (data do pagamento)
--   - client (nome do cliente da tabela clients)
--   - amount (valor pago)
--   - payment_method (nome do método de pagamento da tabela payment_methods)
--
-- DESAFIO PEDAGÓGICO:
-- Por que usamos USING para 'clients' e ON para 'payment_methods'?
--   1. payments e clients compartilham a coluna 'client_id' (nomes IGUAIS -> USING).
--   2. payments tem 'payment_method' e payment_methods tem 'payment_method_id'
--      (nomes DIFERENTES -> ON OBRIGATÓRIO!).
-- ------------------------------------------------------------------------------

USE sql_invoicing;

SELECT 
    p.date,
    c.name AS client,
    p.amount,
    pm.name AS payment_method
FROM payments p
JOIN clients c
    USING (client_id)
JOIN payment_methods pm
    ON p.payment_method = pm.payment_method_id
ORDER BY p.date;
