-- ==============================================================================
-- CAPÍTULO 23 (PARTE 3): JUNÇÕES EXTERNAS ENTRE MÚLTIPLAS TABELAS
-- O Efeito Cascata do LEFT JOIN, A Armadilha da Quebra de Corrente e
-- O Exercício Oficial de Pedidos, Clientes, Transportadoras e Status
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_23_outer_joins_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A REGRA DE OURO DO EFEITO CASCATA (CASCADING LEFT JOIN)
-- ------------------------------------------------------------------------------
-- Conectando 3 tabelas: customers -> orders -> shippers.
-- Dos 10 clientes, 4 nunca compraram.
-- Dos 10 pedidos, 5 ainda NÃO foram despachados (shipper_id IS NULL).
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    sh.name AS shipper_name
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
LEFT JOIN shippers sh
    ON o.shipper_id = sh.shipper_id
ORDER BY c.customer_id, o.order_id;

-- ------------------------------------------------------------------------------
-- 2. A CATÁSTROFE DA QUEBRA DA CORRENTE (INNER JOIN NO FINAL)
-- ------------------------------------------------------------------------------
-- CUIDADO EXTREMO: Se você começar com LEFT JOIN e colocar um INNER JOIN depois:
-- De 14 linhas, o resultado DESPENCA para apenas 5 linhas!
-- 64% dos dados desaparecem porque o INNER JOIN descarta qualquer NULL!
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    sh.name AS shipper_name
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
JOIN shippers sh -- <- O ASSASSINO DOS DADOS NULOS!
    ON o.shipper_id = sh.shipper_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 3. O EXERCÍCIO OFICIAL DO AUTOR (MOSH HAMEDANI)
-- ------------------------------------------------------------------------------
-- Desafio: Produzir um relatório de todos os pedidos contendo:
-- order_date, order_id, first_name (cliente), shipper (transportadora), status.
-- Todos os pedidos devem aparecer, mesmo os que ainda não têm transportadora!
SELECT 
    o.order_date,
    o.order_id,
    c.first_name,
    IFNULL(sh.name, 'Sem Transportadora') AS shipper,
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
-- 4. COMBINANDO JOINS INTERNOS E EXTERNOS COM DISCERNIMENTO DE ENGENHARIA
-- ------------------------------------------------------------------------------
-- Por que usamos JOIN para customers e order_statuses, mas LEFT JOIN para shippers?
-- 1. Todo pedido DEVE ter um cliente obrigatoriamente (FK não nula) -> JOIN
-- 2. Todo pedido DEVE ter um status obrigatoriamente (FK não nula) -> JOIN
-- 3. Nem todo pedido tem transportadora ainda (FK aceita NULL) -> LEFT JOIN!
