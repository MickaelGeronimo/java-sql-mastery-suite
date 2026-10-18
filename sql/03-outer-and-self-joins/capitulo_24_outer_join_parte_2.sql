-- ==============================================================================
-- CAPÍTULO 24 (PARTE 2): O EXERCÍCIO OFICIAL DE 4 TABELAS DO MOSH HAMEDANI
--orders + customers + shippers + order_statuses
-- Curso: SQL Completo (Mosh Hamedani)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_24_outer_join_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O EXERCÍCIO OFICIAL DO MOSH HAMEDANI NA ÍNTEGRA
-- ------------------------------------------------------------------------------
-- Desafio: Produzir um relatório de todos os pedidos contendo:
-- order_date, order_id, first_name (cliente), shipper (transportadora), status.
-- Todos os pedidos devem aparecer, mesmo os que ainda não foram despachados!
SELECT 
    o.order_date,
    o.order_id,
    c.first_name,
    sh.name AS shipper,
    os.name AS status
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
LEFT JOIN shippers sh
    ON o.shipper_id = sh.shipper_id
JOIN order_statuses os
    ON o.status = os.order_status_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. VERSÃO REFINADA COM TRATAMENTO DE NULOS (IFNULL)
-- ------------------------------------------------------------------------------
-- Substitui o NULL da transportadora por um texto executivo limpo.
SELECT 
    o.order_date,
    o.order_id,
    c.first_name,
    IFNULL(sh.name, 'Aguardando Coleta') AS shipper,
    os.name AS status
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
LEFT JOIN shippers sh
    ON o.shipper_id = sh.shipper_id
JOIN order_statuses os
    ON o.status = os.order_status_id
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 3. INSPEÇÃO DE ENGENHARIA: POR QUE JOIN É SEGURO PARA CLIENTES E STATUS?
-- ------------------------------------------------------------------------------
-- Se inspecionarmos a estrutura da tabela orders:
-- customer_id é NOT NULL (todo pedido DEVE ter cliente) -> INNER JOIN seguro!
-- status é NOT NULL (todo pedido DEVE ter status) -> INNER JOIN seguro!
-- shipper_id é NULLABLE (nem todo pedido tem transportadora) -> LEFT JOIN OBRIGATÓRIO!
DESCRIBE orders;
