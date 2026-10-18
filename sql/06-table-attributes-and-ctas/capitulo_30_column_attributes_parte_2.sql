-- ==============================================================================
-- CAPÍTULO 30 (PARTE 2): TIPOS DE DADOS AVANÇADOS, CHAVES COMPOSTAS E FKs
-- Inspecionando orders, order_items e products: TINYINT, SMALLINT, DECIMAL(4,2),
-- Chaves Compostas sem Auto-Incremento e Integridade Referencial (Erro 1452)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 2
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_30_column_attributes_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. INSPEÇÃO DA TABELA 'orders': TINYINT, SMALLINT E VALORES PADRÃO
-- ------------------------------------------------------------------------------
-- Observe o campo 'status': TINYINT com Default 1 (1 = Processado).
-- Observe o campo 'shipper_id': SMALLINT que aceita NULL (pedido pendente de envio).
-- ------------------------------------------------------------------------------

DESCRIBE orders;

-- ------------------------------------------------------------------------------
-- 2. INSPEÇÃO DA TABELA 'order_items': CHAVE COMPOSTA E DECIMAL(4,2)
-- ------------------------------------------------------------------------------
-- Observe que order_id e product_id formam uma Chave Primária Composta (PRI em ambos!).
-- NENHUM deles tem auto_increment! Portanto, AMBOS são 100% obrigatórios no INSERT!
-- Observe o campo 'unit_price': DECIMAL(4,2) para evitar erros de arredondamento financeiro.
-- ------------------------------------------------------------------------------

DESCRIBE order_items;

-- ------------------------------------------------------------------------------
-- 3. INSPEÇÃO DA TABELA 'products': ESTOQUE E PREÇO UNITÁRIO
-- ------------------------------------------------------------------------------

DESCRIBE products;

-- ------------------------------------------------------------------------------
-- 4. SIMULAÇÃO DE ERRO 1452: VIOLAÇÃO DE INTEGRIDADE REFERENCIAL (FOREIGN KEY)
-- ------------------------------------------------------------------------------
-- Se tentarmos inserir um pedido para um cliente que NÃO existe (ex: customer_id = 9999),
-- o motor do MySQL aborta a inserção para proteger o banco de dados contra órfãos!
-- ------------------------------------------------------------------------------
-- INSERT INTO orders (customer_id, order_date, status)
-- VALUES (9999, '2026-10-12', 1);
-- ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails
-- (`sql_store`.`orders`, CONSTRAINT `fk_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`))
