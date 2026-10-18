-- ==============================================================================
-- CAPÍTULO 24 (PARTE 1): JUNÇÃO EXTERNA ENTRE MÚLTIPLAS TABELAS
-- (OUTER JOIN BETWEEN MULTIPLE TABLES)
-- Curso: SQL Completo (Mosh Hamedani)
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_24_outer_join_multiple_tables.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. PONTO DE PARTIDA: O LEFT JOIN COM 2 TABELAS (customers + orders)
-- ------------------------------------------------------------------------------
-- Preserva todos os 10 clientes (ativos e inativos). Retorna 14 linhas.
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 2. A ARMADILHA FATAL: ADICIONANDO A 3ª TABELA COM INNER JOIN (shippers)
-- ------------------------------------------------------------------------------
-- CUIDADO: Este é o maior erro cometido por iniciantes!
-- Colocar 'JOIN shippers sh' descarta os nulos e faz o relatório despencar
-- de 14 linhas para APENAS 5 LINHAS (perda silenciosa de 64% dos dados!).
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    sh.name AS shipper
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
JOIN shippers sh
    ON o.shipper_id = sh.shipper_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 3. A SOLUÇÃO DEFINITIVA DO MOSH: CASCADING LEFT JOIN (TUDO COM LEFT JOIN)
-- ------------------------------------------------------------------------------
-- Ao usar 'LEFT JOIN shippers sh', todas as 14 linhas são preservadas com perfeição!
SELECT 
    c.customer_id,
    c.first_name,
    o.order_id,
    sh.name AS shipper
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
LEFT JOIN shippers sh
    ON o.shipper_id = sh.shipper_id
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 4. TRATANDO OS DOIS TIPOS DE NULOS COM ELEGÂNCIA (CASE / IFNULL)
-- ------------------------------------------------------------------------------
-- 1. Cliente inativo: order_id é NULL e shipper é NULL.
-- 2. Pedido não despachado: order_id existe, mas shipper é NULL.
SELECT 
    c.customer_id,
    c.first_name,
    IFNULL(o.order_id, 'Sem Compras') AS order_id,
    CASE 
        WHEN o.order_id IS NULL THEN 'Não Aplicável'
        WHEN sh.name IS NULL THEN 'Aguardando Despacho no Galpão'
        ELSE sh.name 
    END AS status_envio
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
LEFT JOIN shippers sh
    ON o.shipper_id = sh.shipper_id
ORDER BY c.customer_id;
