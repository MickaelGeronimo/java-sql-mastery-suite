-- ==============================================================================
-- CAPÍTULO 26 (PARTE 3): A CLÁUSULA USING (THE USING CLAUSE)
-- A Cura da Ambiguidade (Adeus Erro 1052), Junções Multi-Bancos (Cross-DB),
-- Anti-Joins com USING e Análise do Otimizador (EXPLAIN)
-- Curso: SQL Completo (Mosh Hamedani) - Seção 3, Aula 10
-- Banco de Dados: MySQL 8.0 / PostgreSQL 18
-- Arquivo: capitulo_26_the_using_clause_parte_3.sql
-- ==============================================================================

USE sql_store;

-- ------------------------------------------------------------------------------
-- 1. A CURA DA AMBIGUIDADE (SEM ERRO 1052 NO SELECT)
-- ------------------------------------------------------------------------------
-- Na sintaxe clássica com ON:
--   SELECT customer_id FROM orders o JOIN customers c ON o.customer_id = c.customer_id;
-- O MySQL emite: ERROR 1052 (23000): Column 'customer_id' in field list is ambiguous.
--
-- MAS COM O 'USING':
-- Como a coluna 'customer_id' é fundida em uma chave única de amarração,
-- você PODE selecioná-la DIRETAMENTE sem colocar o prefixo 'o.' nem 'c.'!
-- ------------------------------------------------------------------------------

SELECT 
    customer_id, -- Sem prefixo! 100% legal e seguro com USING!
    o.order_id,
    c.first_name
FROM orders o
JOIN customers c
    USING (customer_id)
ORDER BY o.order_id;

-- ------------------------------------------------------------------------------
-- 2. JUNÇÃO MULTI-BANCOS (CROSS-DATABASE) COM USING
-- ------------------------------------------------------------------------------
-- Podemos conectar a tabela 'order_items' (banco sql_store)
-- com a tabela 'products' (banco sql_inventory) usando USING (product_id).
-- ------------------------------------------------------------------------------

SELECT 
    oi.order_id,
    product_id,
    p.name AS product_name,
    oi.quantity,
    oi.unit_price
FROM order_items oi
JOIN sql_inventory.products p
    USING (product_id)
ORDER BY oi.order_id, product_id;

-- ------------------------------------------------------------------------------
-- 3. O PADRÃO ANTI-JOIN COM USING: CLIENTES SEM NENHUM PEDIDO
-- ------------------------------------------------------------------------------
-- Quem são os clientes inativos cadastrados que nunca compraram nada?
-- Usamos 'LEFT JOIN orders o USING (customer_id)' e filtramos 'WHERE o.order_id IS NULL'!
-- ------------------------------------------------------------------------------

SELECT 
    c.customer_id,
    c.first_name,
    c.phone
FROM customers c
LEFT JOIN orders o
    USING (customer_id)
WHERE o.order_id IS NULL
ORDER BY c.customer_id;

-- ------------------------------------------------------------------------------
-- 4. ANÁLISE DE PERFORMANCE COM EXPLAIN: O USING É MAIS RÁPIDO QUE O ON?
-- ------------------------------------------------------------------------------
-- O otimizador de consultas (Query Optimizer) reescreve internamente o USING
-- para a mesma representação relacional do ON.
-- O plano de execução, custo de I/O e uso de índices B-Tree são RIGOROSAMENTE IDÊNTICOS!
-- ------------------------------------------------------------------------------

EXPLAIN
SELECT 
    customer_id,
    o.order_id,
    c.first_name
FROM orders o
JOIN customers c
    USING (customer_id);
