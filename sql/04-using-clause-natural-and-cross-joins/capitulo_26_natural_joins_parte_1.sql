-- ==============================================================================
-- CAPÍTULO 26 / 27 (PARTE 1): JUNÇÕES NATURAIS (NATURAL JOINS)
-- O Comportamento "Automágico", A Adivinhação de Colunas e o Alerta do Autor
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 11
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_26_natural_joins_parte_1.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O CÓDIGO OFICIAL DO AUTOR: A JUNÇÃO "SEM CLÁUSULA DE LIGAÇÃO"
-- ------------------------------------------------------------------------------
-- Observe com atenção: NÃO existe cláusula 'ON', e NÃO existe cláusula 'USING'!
-- O motor do banco de dados (MySQL) examina os metadados de ambas as tabelas
-- e procura automaticamente todas as colunas que possuem o MESMO NOME.
-- Como 'orders' e 'customers' só têm 'customer_id' em comum, o banco
-- executa a amarração por 'customer_id'.
-- ------------------------------------------------------------------------------

SELECT 
    o.order_id,
    c.first_name
FROM orders o
NATURAL JOIN customers c
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. DESDUPLICAÇÃO AUTOMÁTICA NO SELECT *
-- ------------------------------------------------------------------------------
-- Assim como na cláusula USING, o NATURAL JOIN funde a coluna comum
-- ('customer_id') em uma única coluna no início da projeção.
-- ------------------------------------------------------------------------------

SELECT *
FROM orders o
NATURAL JOIN customers c
ORDER BY order_id;

-- ------------------------------------------------------------------------------
-- 3. NATURAL JOIN COM CHAVES COMPOSTAS AUTOMÁTICAS
-- ------------------------------------------------------------------------------
-- Entre 'order_items' e 'order_item_notes', as colunas comuns são
-- 'order_id' e 'product_id'.
-- O NATURAL JOIN amarra AMBAS as colunas automaticamente sem precisarmos
-- escrever nada!
-- ------------------------------------------------------------------------------

SELECT 
    order_id,
    product_id,
    quantity,
    unit_price,
    note
FROM order_items oi
NATURAL JOIN order_item_notes oin;

-- ------------------------------------------------------------------------------
-- 4. O GRANDE ALERTA DO AUTOR: POR QUE O NATURAL JOIN É PERIGOSO EM PRODUÇÃO?
-- ------------------------------------------------------------------------------
-- Mosh Hamedani enfatiza enfaticamente:
-- "Eu NÃO recomendo que você use NATURAL JOIN no dia a dia!"
-- Motivo: Ele faz uma 'adivinhação' cega baseada em nomes de colunas.
-- Se um colega adicionar uma coluna com o mesmo nome em ambas as tabelas no futuro
-- (ex: 'created_at', 'status', 'updated_at'), o NATURAL JOIN tentará amarrar
-- por ela também, QUEBRANDO sua consulta em produção silenciosamente!
-- ------------------------------------------------------------------------------
