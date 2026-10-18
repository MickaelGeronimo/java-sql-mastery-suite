-- ==============================================================================
-- SEÇÃO 5: RESUMINDO DADOS (SUMMARIZING DATA)
-- CAPÍTULO 40 (PARTE 3 FINAL MASTERCLASS): JUNÇÕES, PERFORMANCE E O DICIONÁRIO
-- Curso: Complete SQL Mastery (Mosh Hamedani)
-- Banco de Dados: sql_invoicing / sql_store
-- Arquivo: capitulo_40_aggregate_functions_parte_3_final.sql
-- ==============================================================================

USE sql_invoicing;

-- ------------------------------------------------------------------------------
-- 1. AGREGAÇÃO MULTI-TABELA (JOIN ENTRE CLIENTES E FATURAS)
-- ------------------------------------------------------------------------------
-- Em relatórios corporativos, precisamos combinar nomes e dados cadastrais
-- com os agregados financeiros das faturas.
-- Também utilizamos a famosa função GROUP_CONCAT para listar em uma única célula
-- os nomes de todos os clientes que possuem faturas ativas.
-- ------------------------------------------------------------------------------

SELECT 
    COUNT(DISTINCT c.client_id) AS clientes_ativos,
    COUNT(i.invoice_id) AS total_faturas,
    SUM(i.invoice_total) AS total_faturado,
    SUM(i.payment_total) AS total_liquidado,
    GROUP_CONCAT(DISTINCT c.name ORDER BY c.name SEPARATOR ', ') AS clientes_com_fatura
FROM clients c
JOIN invoices i ON c.client_id = i.client_id;

-- ------------------------------------------------------------------------------
-- 2. A ARMADILHA DA MULTIPLICAÇÃO EM JOINS 1:N (THE DOUBLE COUNTING TRAP)
-- ------------------------------------------------------------------------------
-- ATENÇÃO CRÍTICA: Se você fizer um JOIN entre uma tabela pai (ex: orders) e 
-- uma tabela filha (order_items), e tentar somar uma coluna da tabela pai,
-- o valor da tabela pai será somado QUANTAS VEZES HOUVER ITENS NA FILHA!
-- Veja a diferença no banco 'sql_store':
-- ------------------------------------------------------------------------------

USE sql_store;

-- 2.1. Consulta Correta: Somando a coluna da tabela filha (itens)
SELECT 
    COUNT(DISTINCT o.order_id) AS total_pedidos,
    COUNT(oi.product_id) AS total_itens_vendidos,
    SUM(oi.quantity * oi.unit_price) AS faturamento_real
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id;

-- ------------------------------------------------------------------------------
-- 3. FUNÇÕES ESTATÍSTICAS E DE DISPERSÃO
-- ------------------------------------------------------------------------------
-- Além das 5 grandes, o SQL oferece funções estatísticas avançadas para
-- análise de risco e variância:
-- - STDDEV_SAMP(): Desvio padrão amostral dos valores.
-- - VARIANCE(): Variância estatística.
-- ------------------------------------------------------------------------------

USE sql_invoicing;

SELECT 
    ROUND(AVG(invoice_total), 2) AS media_vendas,
    ROUND(STDDEV_SAMP(invoice_total), 2) AS desvio_padrao,
    ROUND(VARIANCE(invoice_total), 2) AS variancia
FROM invoices;

-- ------------------------------------------------------------------------------
-- 4. ANÁLISE DE PERFORMANCE COM EXPLAIN: O PODER DOS ÍNDICES NO MIN() E MAX()
-- ------------------------------------------------------------------------------
-- Quando consultamos MIN() ou MAX() em uma coluna indexada (como a Chave Primária),
-- o motor NÃO lê a tabela inteira! Ele simplesmente consulta a primeira folha
-- ou a última folha da árvore B-Tree em tempo O(1).
-- Observe no resultado do EXPLAIN a mensagem: "Select tables optimized away".
-- ------------------------------------------------------------------------------

EXPLAIN SELECT 
    MIN(invoice_id) AS primeiro_id,
    MAX(invoice_id) AS ultimo_id
FROM invoices;
