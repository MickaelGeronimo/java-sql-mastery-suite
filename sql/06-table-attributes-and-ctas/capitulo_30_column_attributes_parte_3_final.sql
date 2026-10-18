-- ==============================================================================
-- CAPÍTULO 30 (PARTE 3 FINAL): ENGENHARIA DE ATRIBUTOS AVANÇADOS
-- Colunas Geradas (VIRTUAL vs STORED), TIMESTAMP vs DATETIME, CHECK Constraints (MySQL 8)
-- e Consulta ao Catálogo do Sistema (information_schema.COLUMNS)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 2
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_30_column_attributes_parte_3_final.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. O CHECKBOX 'G' DO WORKBENCH: COLUNAS GERADAS (GENERATED COLUMNS)
-- ------------------------------------------------------------------------------
-- Uma coluna gerada calcula seu valor automaticamente a partir de uma fórmula.
--   A) VIRTUAL (Padrão): Calculada em tempo de execução no SELECT (0 bytes em disco).
--   B) STORED: Calculada no INSERT/UPDATE e gravada fisicamente em disco (permite índice!).
-- Exemplo: Criando uma coluna virtual de valor total na tabela order_items:
-- ------------------------------------------------------------------------------

-- SELECT 
--     order_id, 
--     product_id, 
--     quantity, 
--     unit_price,
--     (quantity * unit_price) AS line_total
-- FROM order_items;

-- ------------------------------------------------------------------------------
-- 2. DATETIME vs TIMESTAMP: VALORES PADRÃO AUTOMÁTICOS
-- ------------------------------------------------------------------------------
-- DATETIME: 8 bytes, vai de 1000 a 9999. Não tem fuso horário (armazena exatamente o digitado).
-- TIMESTAMP: 4 bytes, vai de 1970 a 2038. Converte automaticamente para UTC e fuso local!
-- Padrão Enterprise para Auditoria:
--   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 3. RESTRIÇÕES CHECK (VALIDAÇÃO DE DOMÍNIO NO MYSQL 8.0.16+)
-- ------------------------------------------------------------------------------
-- Impede a inserção de dados ilegais como pontuações negativas ou preços zerados.
-- Se violada, dispara:
-- ERROR 3819 (HY000): Check constraint 'check_points_positive' is violated.
-- ------------------------------------------------------------------------------

-- ------------------------------------------------------------------------------
-- 4. CONSULTA PROFISSIONAL AO CATÁLOGO: information_schema.COLUMNS
-- ------------------------------------------------------------------------------
-- É assim que o Spring Data, Hibernate e ferramentas corporativas descobrem
-- os tipos e restrições de cada coluna diretamente pelo motor relacional:
-- ------------------------------------------------------------------------------

SELECT 
    column_name,
    data_type,
    character_maximum_length AS max_len,
    is_nullable,
    column_default,
    extra
FROM information_schema.columns
WHERE table_schema = 'sql_store' AND table_name = 'customers'
ORDER BY ordinal_position;
