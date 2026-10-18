-- ==============================================================================
-- CAPÍTULO 32 (PARTE 2): BATCH INSERTS AVANÇADOS, BATCH UPSERT E LIMITES DE PACOTE
-- Chaves Compostas em Lote (order_items), Batch UPSERT (ON DUPLICATE KEY UPDATE em Lote),
-- O Erro 1153 (max_allowed_packet) e Estratégia de Fatiamento (Chunking)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 4, Aula 4
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_32_inserting_multiple_rows_parte_2.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. BATCH INSERT COM CHAVES COMPOSTAS (A TABELA 'order_items')
-- ------------------------------------------------------------------------------
-- Em um e-commerce real, quando o cliente finaliza o pedido (ex: pedido 11),
-- todos os itens do carrinho são gravados de uma só vez na tabela order_items.
-- Como order_items tem PK composta (order_id, product_id) sem auto-incremento,
-- passamos ambos os identificadores explicitamente em cada tupla:
-- ------------------------------------------------------------------------------

INSERT INTO order_items (
    order_id,
    product_id,
    quantity,
    unit_price
)
VALUES 
    (11, 1, 2, 9.10),
    (11, 2, 1, 4.65),
    (11, 3, 5, 3.35);

-- ------------------------------------------------------------------------------
-- 2. VERIFICAÇÃO DOS ITENS DO PEDIDO 11
-- ------------------------------------------------------------------------------

SELECT * 
FROM order_items 
WHERE order_id = 11;

-- ------------------------------------------------------------------------------
-- 3. O PODEROSO 'BATCH UPSERT' (ON DUPLICATE KEY UPDATE EM LOTE)
-- ------------------------------------------------------------------------------
-- Imagine que uma remessa de estoque chegou. Ela contém produtos novos e antigos.
-- Se o produto já existir, SOMAMOS a quantidade em estoque e atualizamos o preço!
-- Se o produto for novo, inserimos normalmente no catálogo!
-- ------------------------------------------------------------------------------

INSERT INTO products (
    product_id,
    name,
    quantity_in_stock,
    unit_price
)
VALUES 
    (1, 'Foam Dinner Plate', 30, 1.35),      -- Já existe (ID 1) -> Atualiza estoque e preço
    (2, 'Pork - Bacon,back Peameal', 15, 4.80), -- Já existe (ID 2) -> Atualiza estoque e preço
    (14, 'Produto Inédito Black Friday', 100, 19.90) -- Novo (ID 14) -> Insere normalmente
AS new_products
ON DUPLICATE KEY UPDATE 
    quantity_in_stock = products.quantity_in_stock + new_products.quantity_in_stock,
    unit_price = new_products.unit_price;

-- ------------------------------------------------------------------------------
-- 4. VERIFICAÇÃO DOS PRODUTOS APÓS O BATCH UPSERT
-- ------------------------------------------------------------------------------

SELECT product_id, name, quantity_in_stock, unit_price
FROM products
WHERE product_id IN (1, 2, 14);

-- ------------------------------------------------------------------------------
-- 5. LIMITES FÍSICOS DO SERVIDOR: max_allowed_packet (ERRO 1153)
-- ------------------------------------------------------------------------------
-- Inspecionamos a variável global do servidor que dita o tamanho máximo do comando:
-- ------------------------------------------------------------------------------

SHOW VARIABLES LIKE 'max_allowed_packet';
