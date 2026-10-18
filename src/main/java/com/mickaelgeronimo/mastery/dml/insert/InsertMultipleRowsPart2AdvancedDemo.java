package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ==============================================================================
 * CAPÍTULO 32 (PARTE 2): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Batch Insert com Chaves Compostas (order_items), Batch UPSERT em Lote,
 *       e Algoritmo de Fatiamento (Chunking) para Contornar o max_allowed_packet
 * ==============================================================================
 */
public class InsertMultipleRowsPart2AdvancedDemo {

    public record OrderItemId(int orderId, int productId) {}
    public record OrderItem(OrderItemId id, int quantity, BigDecimal unitPrice) {
        @Override
        public String toString() {
            return String.format("| %8d | %10d | %8d | %10s |", id.orderId(), id.productId(), quantity, unitPrice);
        }
    }

    public static class Product {
        private final int id;
        private final String name;
        private int quantityInStock;
        private BigDecimal unitPrice;

        public Product(int id, String name, int quantityInStock, BigDecimal unitPrice) {
            this.id = id;
            this.name = name;
            this.quantityInStock = quantityInStock;
            this.unitPrice = unitPrice;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public int getQuantityInStock() { return quantityInStock; }
        public BigDecimal getUnitPrice() { return unitPrice; }

        public void addStock(int extra) { this.quantityInStock += extra; }
        public void setUnitPrice(BigDecimal price) { this.unitPrice = price; }

        @Override
        public String toString() {
            return String.format("| %10d | %-30s | %17d | %10s |", id, name, quantityInStock, unitPrice);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 32 (PARTE 2) - BATCH UPSERT, CHAVES COMPOSTAS E CHUNKING  ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: BATCH INSERT COM CHAVES COMPOSTAS (order_items)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. BATCH INSERT COM CHAVE PRIMÁRIA COMPOSTA (order_items) ---");
        System.out.println("SQL: INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES");
        System.out.println("     (11, 1, 2, 9.10), (11, 2, 1, 4.65), (11, 3, 5, 3.35);");

        List<OrderItem> cartItems = List.of(
            new OrderItem(new OrderItemId(11, 1), 2, new BigDecimal("9.10")),
            new OrderItem(new OrderItemId(11, 2), 1, new BigDecimal("4.65")),
            new OrderItem(new OrderItemId(11, 3), 5, new BigDecimal("3.35"))
        );

        System.out.println("+----------+------------+----------+------------+");
        System.out.println("| order_id | product_id | quantity | unit_price |");
        System.out.println("+----------+------------+----------+------------+");
        cartItems.forEach(System.out::println);
        System.out.println("+----------+------------+----------+------------+");
        System.out.println("Query OK, 3 rows affected (0.00 sec) - Todos os itens do carrinho gravados em lote!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: BATCH UPSERT (ON DUPLICATE KEY UPDATE EM LOTE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. BATCH UPSERT: ATUALIZAÇÃO E INSERÇÃO SIMULTÂNEA EM LOTE ---");
        Map<Integer, Product> productCatalog = new LinkedHashMap<>();
        productCatalog.put(1, new Product(1, "Foam Dinner Plate", 70, new BigDecimal("1.21")));
        productCatalog.put(2, new Product(2, "Pork - Bacon,back Peameal", 49, new BigDecimal("4.65")));

        // Lote de remessa com 3 itens (IDs 1 e 2 já existem, ID 14 é novo):
        List<Product> incomingBatch = List.of(
            new Product(1, "Foam Dinner Plate", 30, new BigDecimal("1.35")),
            new Product(2, "Pork - Bacon,back Peameal", 15, new BigDecimal("4.80")),
            new Product(14, "Produto Inédito Black Friday", 100, new BigDecimal("19.90"))
        );

        int rowsAffected = 0;
        for (Product incoming : incomingBatch) {
            if (productCatalog.containsKey(incoming.getId())) {
                // Colisão de chave: Atualiza estoque e preço (conta como 2 no MySQL!)
                Product existing = productCatalog.get(incoming.getId());
                existing.addStock(incoming.getQuantityInStock());
                existing.setUnitPrice(incoming.getUnitPrice());
                rowsAffected += 2;
            } else {
                // Chave inédita: Insere normalmente (conta como 1)
                productCatalog.put(incoming.getId(), incoming);
                rowsAffected += 1;
            }
        }

        System.out.println("-> MySQL Console: Query OK, " + rowsAffected + " rows affected (0.01 sec)");
        System.out.println("->                Records: 3  Duplicates: 2  Warnings: 0\n");

        System.out.println("+------------+--------------------------------+-------------------+------------+");
        System.out.println("| product_id | name                           | quantity_in_stock | unit_price |");
        System.out.println("+------------+--------------------------------+-------------------+------------+");
        productCatalog.values().forEach(System.out::println);
        System.out.println("+------------+--------------------------------+-------------------+------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: ALGORITMO DE CHUNKING (FATIAMENTO CONTRA max_allowed_packet)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. ESTRATÉGIA DE CHUNKING: FATIANDO CARGAS GIGANTES EM LOTES SEGUROS ---");
        List<String> largePayload = IntStream.rangeClosed(1, 25)
                .mapToObj(i -> "Produto_Carga_" + i)
                .collect(Collectors.toList());

        int chunkSize = 10; // Tamanho seguro do lote para não estourar o pacote de rede
        int totalBatches = (int) Math.ceil((double) largePayload.size() / chunkSize);

        System.out.println("Total de itens a inserir: " + largePayload.size());
        System.out.println("Tamanho de cada lote (Chunk Size): " + chunkSize);
        System.out.println("Total de comandos INSERT gerados: " + totalBatches + "\n");

        for (int i = 0; i < largePayload.size(); i += chunkSize) {
            List<String> chunk = largePayload.subList(i, Math.min(i + chunkSize, largePayload.size()));
            int batchNum = (i / chunkSize) + 1;
            System.out.println("Disparando Lote #" + batchNum + " com " + chunk.size() + " registros:");
            System.out.println("  INSERT INTO products (name) VALUES " +
                    chunk.stream().map(n -> "('" + n + "')").collect(Collectors.joining(", ")) + ";");
        }

        System.out.println("\nDemostração avançada de Batch Inserts concluída com 100% de sucesso!");
    }
}
