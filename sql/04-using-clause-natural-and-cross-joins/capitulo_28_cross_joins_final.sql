-- ==============================================================================
-- CAPÍTULO 28 (PARTE 3 - FINAL): O GRAND FINALE DAS JUNÇÕES CRUZADAS
-- O Triplo Cross Join Multinível (A x B x C = 150 Linhas), Geração de Carga,
-- A Bomba de Memória RAM (OOM) e Proteções de Segurança do DBA
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 12
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_28_cross_joins_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O TRIPLO CROSS JOIN MULTINÍVEL (A x B x C)
-- ------------------------------------------------------------------------------
-- Cruzamos 3 tabelas simultaneamente:
--   shippers (5) x order_statuses (3) x products (10)
-- 5 x 3 x 10 = 150 LINHAS de matriz combinatória tridimensional!
-- ------------------------------------------------------------------------------

SELECT 
    sh.name AS shipper_name,
    os.name AS status_name,
    p.name AS product_name
FROM shippers sh
CROSS JOIN order_statuses os
CROSS JOIN products p
ORDER BY sh.name, os.name, p.name;

-- ------------------------------------------------------------------------------
-- 2. GERAÇÃO DE MASSA DE DADOS PARA TESTES DE CARGA (MOCK DATA GENERATOR)
-- ------------------------------------------------------------------------------
-- Como equipes de performance usam o Cross Join para multiplicar dados:
-- Cruzar números de 0 a 9 consigo mesmo gera sequências numéricas instantâneas!
-- ------------------------------------------------------------------------------

SELECT 
    (t1.digit * 10 + t2.digit) AS generated_number
FROM 
    (SELECT 0 AS digit UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 
     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t1
CROSS JOIN 
    (SELECT 0 AS digit UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 
     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t2
ORDER BY generated_number
LIMIT 20;

-- ------------------------------------------------------------------------------
-- 3. TRAVA DE SEGURANÇA DO DBA CONTRA ESTOURO DE MEMÓRIA (OOM KILLER)
-- ------------------------------------------------------------------------------
-- Para evitar que desenvolvedores travem o servidor com cross joins de milhões de linhas:
-- SET sql_big_selects = 0; -- Aborta consultas que excedam o limite seguro de I/O!
-- ------------------------------------------------------------------------------
