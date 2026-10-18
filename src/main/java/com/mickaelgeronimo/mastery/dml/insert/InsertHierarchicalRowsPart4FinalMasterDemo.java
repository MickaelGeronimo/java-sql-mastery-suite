package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 33 (PARTE 4 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Exercício Completo de Checkout Hierárquico, Triggers de Baixa de Estoque,
 *       Cálculos Financeiros com BigDecimal e Padrão Aggregate Root (DDD)
 * ==============================================================================
 */
public class InsertHierarchicalRowsPart4FinalMasterDemo {

    // 1. Modelos de Domínio (Java 17 Records & Classes)
    public record Customer(int id, String firstName, String lastName) {}

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

        public void deductStock(int quantity) {
            if (this.quantityInStock < quantity) {
                throw new IllegalStateException("Estoque insuficiente para o produto: " + name);
            }
            this.quantityInStock -= quantity;
        }

        @Override
        public String toString() {
            return String.format("| %10d | %-28s | %17d |", id, name, quantityInStock);
        }
    }

    public record OrderItem(
        int orderId,
        int productId,
        String productName,
        int quantity,
        BigDecimal unitPrice
    ) {
        public BigDecimal getSubtotal() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }

        @Override
        public String toString() {
            return String.format("| %8d | %-25s | %8d | %10s | %10s |",
                orderId, productName, quantity, unitPrice, getSubtotal());
        }
    }

    public record Order(
        int orderId,
        int customerId,
        String customerName,
        LocalDate orderDate,
        byte status,
        String comments,
        List<OrderItem> items
    ) {
        public BigDecimal calculateGrandTotal() {
            return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        public int calculateTotalItemsCount() {
            return items.stream().mapToInt(OrderItem::quantity).sum();
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 33 (PARTE 4 FINAL) - EXERCÍCIO OFICIAL E AUDITORIA        ");
        System.out.println("=========================================================================================\n");

        // 1. Estoque inicial
        Map<Integer, Product> catalog = new LinkedHashMap<>();
        catalog.put(1, new Product(1, "Foam Dinner Plate", 70, new BigDecimal("3.50")));
        catalog.put(2, new Product(2, "Pork - Bacon,back Peameal", 49, new BigDecimal("5.00")));
        catalog.put(3, new Product(3, "Lettuce - Romaine, Cello", 38, new BigDecimal("2.20")));

        System.out.println("--- 1. ESTOQUE DOS PRODUTOS ANTES DA COMPRA ---");
        System.out.println("+------------+------------------------------+-------------------+");
        System.out.println("| product_id | name                         | quantity_in_stock |");
        System.out.println("+------------+------------------------------+-------------------+");
        catalog.values().forEach(System.out::println);
        System.out.println("+------------+------------------------------+-------------------+\n");

        // 2. Executando o Exercício Oficial
        System.out.println("--- 2. EXECUTANDO O CHECKOUT HIERÁRQUICO COMPLETO ---");
        System.out.println("Cliente: Clemmie Barden (ID 5)");
        System.out.println("Data: 2019-01-15 | Status: 1 (Processed)");
        System.out.println("Comentários: 'Entrega expressa prioritária'\n");

        AtomicInteger orderSeq = new AtomicInteger(14); // Próximo: 15
        int newOrderId = orderSeq.incrementAndGet();

        // Itens da compra
        List<OrderItem> cartItems = List.of(
            new OrderItem(newOrderId, 1, catalog.get(1).getName(), 2, new BigDecimal("3.50")),
            new OrderItem(newOrderId, 2, catalog.get(2).getName(), 1, new BigDecimal("5.00")),
            new OrderItem(newOrderId, 3, catalog.get(3).getName(), 4, new BigDecimal("2.20"))
        );

        // Disparando o Trigger de Baixa de Estoque para cada item
        for (OrderItem item : cartItems) {
            Product product = catalog.get(item.productId());
            product.deductStock(item.quantity()); // Simula o trigger: AFTER INSERT ON order_items
        }

        Order order = new Order(
            newOrderId, 5, "Clemmie Barden", LocalDate.of(2019, 1, 15),
            (byte) 1, "Entrega expressa prioritária", cartItems
        );

        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)  [orders]");
        System.out.println("-> MySQL Console: Query OK, 3 rows affected (0.00 sec)  [order_items]");
        System.out.println("-> Trigger tr_after_insert_order_item disparado: 3 atualizações em products!\n");

        // 3. Estoque após a compra
        System.out.println("--- 3. ESTOQUE DOS PRODUTOS APÓS A COMPRA (TRIGGER EM AÇÃO) ---");
        System.out.println("+------------+------------------------------+-------------------+");
        System.out.println("| product_id | name                         | quantity_in_stock |");
        System.out.println("+------------+------------------------------+-------------------+");
        catalog.values().forEach(System.out::println);
        System.out.println("+------------+------------------------------+-------------------+\n");

        // 4. Relatório Analítico de Itens do Pedido
        System.out.println("--- 4. ITENS DO PEDIDO (DETALHAMENTO FINANCEIRO) ---");
        System.out.println("+----------+---------------------------+----------+------------+------------+");
        System.out.println("| order_id | product_name              | quantity | unit_price | subtotal   |");
        System.out.println("+----------+---------------------------+----------+------------+------------+");
        order.items().forEach(System.out::println);
        System.out.println("+----------+---------------------------+----------+------------+------------+\n");

        // 5. Resumo Consolidado do Pedido (Grand Total)
        System.out.println("--- 5. FATURA RESUMIDA DO PEDIDO ---");
        System.out.println("+----------+-----------------+-----------------------+-------------------+-------------------+");
        System.out.println("| order_id | customer_name   | total_distinct_items  | total_items_count | grand_total_price |");
        System.out.println("+----------+-----------------+-----------------------+-------------------+-------------------+");
        System.out.printf("| %8d | %-15s | %21d | %17d | %17s |%n",
            order.orderId(),
            order.customerName(),
            order.items().size(),
            order.calculateTotalItemsCount(),
            "R$ " + order.calculateGrandTotal()
        );
        System.out.println("+----------+-----------------+-----------------------+-------------------+-------------------+");
        System.out.println("\nDemostração de encerramento do Capítulo 33 concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }
}
