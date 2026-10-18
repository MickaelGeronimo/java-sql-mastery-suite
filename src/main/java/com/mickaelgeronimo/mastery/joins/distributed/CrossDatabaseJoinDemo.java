package com.mickaelgeronimo.mastery.joins.distributed;

import java.util.ArrayList;
import java.util.List;

/**
 * CrossDatabaseJoinDemo
 * 
 * Demonstração prática para iniciantes de como funciona uma Junção Entre
 * Múltiplos Bancos de Dados (Cross-Database Join).
 * 
 * Cenário Corporativo:
 * - Banco de Vendas (sql_store): guarda os itens do pedido (order_items).
 * - Banco de Almoxarifado/Estoque (sql_inventory): guarda o catálogo oficial de produtos (products).
 * 
 * No Java, isso simula dois microsserviços ou dois bancos distintos sendo
 * combinados em um único DTO de relatório para a diretoria.
 */
public class CrossDatabaseJoinDemo {

    // 1. Representação da tabela sql_store.order_items
    static class OrderItem {
        int orderId;
        int productId;
        int quantity;
        double unitPrice; // Preço na data da compra

        OrderItem(int orderId, int productId, int quantity, double unitPrice) {
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
    }

    // 2. Representação da tabela sql_inventory.products
    static class InventoryProduct {
        int productId;
        String name;
        int quantityInStock;
        double currentCatalogPrice; // Preço de vitrine atual

        InventoryProduct(int productId, String name, int quantityInStock, double currentCatalogPrice) {
            this.productId = productId;
            this.name = name;
            this.quantityInStock = quantityInStock;
            this.currentCatalogPrice = currentCatalogPrice;
        }
    }

    // 3. DTO final combinado (Resultado do Cross-Database Join)
    static class CrossDbReportDTO {
        int orderId;
        int productId;
        String productName;
        int quantitySold;
        double pricePaid;
        double subtotal;

        CrossDbReportDTO(int orderId, int productId, String productName, int quantitySold, double pricePaid) {
            this.orderId = orderId;
            this.productId = productId;
            this.productName = productName;
            this.quantitySold = quantitySold;
            this.pricePaid = pricePaid;
            this.subtotal = quantitySold * pricePaid;
        }

        @Override
        public String toString() {
            return String.format("| %8d | %10d | %-24s | %8d | R$ %6.2f | R$ %7.2f |",
                    orderId, productId, productName, quantitySold, pricePaid, subtotal);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO ENTRE BANCOS DIFERENTES (sql_store + sql_inventory)");
        System.out.println("=========================================================================================\n");

        // Simulando dados do Banco 1: sql_store
        List<OrderItem> storeOrderItems = new ArrayList<>();
        storeOrderItems.add(new OrderItem(1, 4, 4, 3.74));
        storeOrderItems.add(new OrderItem(2, 1, 2, 9.10));
        storeOrderItems.add(new OrderItem(2, 4, 4, 1.66));
        storeOrderItems.add(new OrderItem(2, 6, 2, 2.94));
        storeOrderItems.add(new OrderItem(3, 3, 10, 9.02));

        // Simulando dados do Banco 2: sql_inventory
        List<InventoryProduct> inventoryProducts = new ArrayList<>();
        inventoryProducts.add(new InventoryProduct(1, "Foam Dinner Plate", 70, 10.50));
        inventoryProducts.add(new InventoryProduct(2, "Pork - Bacon Cooked", 49, 4.00));
        inventoryProducts.add(new InventoryProduct(3, "Sweet Pea Sprout", 38, 9.50));
        inventoryProducts.add(new InventoryProduct(4, "Brocolli - Fresh", 90, 4.20));
        inventoryProducts.add(new InventoryProduct(6, "Scallops - Large", 14, 3.50));

        System.out.println("Executando a consulta SQL:");
        System.out.println("USE sql_store;");
        System.out.println("SELECT oi.order_id, oi.product_id, p.name, oi.quantity, oi.unit_price");
        System.out.println("FROM order_items oi");
        System.out.println("JOIN sql_inventory.products p ON oi.product_id = p.product_id;\n");

        System.out.println("+----------+------------+--------------------------+----------+-----------+-----------+");
        System.out.println("| order_id | product_id | product_name             | quantity | unit_pric | subtotal  |");
        System.out.println("+----------+------------+--------------------------+----------+-----------+-----------+");

        List<CrossDbReportDTO> report = new ArrayList<>();

        for (OrderItem oi : storeOrderItems) {
            for (InventoryProduct p : inventoryProducts) {
                if (oi.productId == p.productId) {
                    report.add(new CrossDbReportDTO(oi.orderId, oi.productId, p.name, oi.quantity, oi.unitPrice));
                    break;
                }
            }
        }

        report.forEach(System.out::println);
        System.out.println("+----------+------------+--------------------------+----------+-----------+-----------+");
        System.out.println("Total de itens cruzados com sucesso: " + report.size());
        System.out.println("\nNota Arquitetural:");
        System.out.println("- oi.unit_price veio de 'sql_store' (preço faturado no pedido).");
        System.out.println("- p.name veio de 'sql_inventory' (catálogo oficial de produtos).");
        System.out.println("- O prefixo 'sql_inventory.products' permitiu a travessia de bancos sem trocar de conexão!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! CROSS-DATABASE JOIN COMPILADO E EXECUTADO!");
        System.out.println("=========================================================================================");
    }
}
