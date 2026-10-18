package com.mickaelgeronimo.mastery.joins.inner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ManyToManyBridgeDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Resolução de Relacionamentos Muitos-para-Muitos (N:M) com Tabela Associativa (Junction Table).
 * 2. Por que arquitetos seniores em Java evitam @ManyToMany puro e usam a classe intermediária (OrderItem).
 * 3. Multi-Join com agregação prévia (Derived Table) eliminando a armadilha do Fan-Out.
 */
public class ManyToManyBridgeDemo {

    // 1. Entidade Order (Lado A)
    static class Order {
        int orderId;
        int customerId;

        Order(int orderId, int customerId) {
            this.orderId = orderId;
            this.customerId = customerId;
        }
    }

    // 2. Entidade Product (Lado B)
    static class Product {
        int productId;
        String name;

        Product(int productId, String name) {
            this.productId = productId;
            this.name = name;
        }
    }

    // 3. A TABELA ASSOCIATIVA / PONTE (OrderItem)
    // Contém chaves estrangeiras para ambos os lados e ATRIBUTOS PRÓPRIOS (quantity, unitPrice)!
    static class OrderItemBridge {
        int orderId;
        int productId;
        int quantity;
        double unitPrice;

        OrderItemBridge(int orderId, int productId, int quantity, double unitPrice) {
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double getSubtotal() {
            return quantity * unitPrice;
        }
    }

    // 4. Faixas de Fidelidade (Loyalty Tiers)
    static class Tier {
        String name;
        double minSpent;
        double maxSpent;

        Tier(String name, double minSpent, double maxSpent) {
            this.name = name;
            this.minSpent = minSpent;
            this.maxSpent = maxSpent;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: TABELA ASSOCIATIVA (N:M) E MULTI-JOIN COM TABELA DERIVADA");
        System.out.println("=========================================================================================\n");

        // Populando dados
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, 101)); // Cliente 101
        orders.add(new Order(2, 101)); // Cliente 101 fez 2 pedidos!
        orders.add(new Order(3, 102)); // Cliente 102 fez 1 pedido

        Map<Integer, Product> products = new HashMap<>();
        products.put(1, new Product(1, "Notebook Gamer"));
        products.put(2, new Product(2, "Mouse Sem Fio"));
        products.put(3, new Product(3, "Teclado Mecânico"));

        // Itens de pedidos conectando Orders com Products (Many-to-Many resolvido!)
        List<OrderItemBridge> items = new ArrayList<>();
        items.add(new OrderItemBridge(1, 1, 1, 4500.00)); // Pedido 1: 1 Notebook
        items.add(new OrderItemBridge(1, 2, 2, 150.00));  // Pedido 1: 2 Mouses
        items.add(new OrderItemBridge(2, 3, 1, 350.00));  // Pedido 2: 1 Teclado
        items.add(new OrderItemBridge(3, 2, 1, 150.00));  // Pedido 3: 1 Mouse

        System.out.println("--- 1. MULTI-JOIN VIA TABELA ASSOCIATIVA (Order -> OrderItem -> Product) ---");
        System.out.println("+----------+----------------------+----------+------------+------------+");
        System.out.println("| order_id | product_name         | quantity | unit_price | subtotal   |");
        System.out.println("+----------+----------------------+----------+------------+------------+");
        for (OrderItemBridge item : items) {
            Product p = products.get(item.productId);
            System.out.printf("| %8d | %-20s | %8d | R$ %7.2f | R$ %7.2f |\n",
                    item.orderId, p.name, item.quantity, item.unitPrice, item.getSubtotal());
        }
        System.out.println("+----------+----------------------+----------+------------+------------+\n");

        System.out.println("--- 2. MULTI-JOIN COM AGREGAÇÃO PRÉVIA (DERIVED TABLE) E FAIXAS DE CLIENTE ---");
        // Passo A: Agregação prévia do total gasto por cliente (calculado sem duplicar linhas)
        Map<Integer, Double> clientTotals = new HashMap<>();
        for (Order o : orders) {
            double orderSum = 0;
            for (OrderItemBridge item : items) {
                if (item.orderId == o.orderId) {
                    orderSum += item.getSubtotal();
                }
            }
            clientTotals.put(o.customerId, clientTotals.getOrDefault(o.customerId, 0.0) + orderSum);
        }

        // Tabela de Tiers
        List<Tier> tiers = new ArrayList<>();
        tiers.add(new Tier("Bronze", 0.0, 1000.00));
        tiers.add(new Tier("Silver", 1000.01, 3000.00));
        tiers.add(new Tier("Diamond", 3000.01, 99999.00));

        System.out.println("+-------------+-----------------+----------+");
        System.out.println("| customer_id | total_gasto     | categoria|");
        System.out.println("+-------------+-----------------+----------+");
        for (Map.Entry<Integer, Double> entry : clientTotals.entrySet()) {
            String clientTier = "Sem Categoria";
            for (Tier t : tiers) {
                if (entry.getValue() >= t.minSpent && entry.getValue() <= t.maxSpent) {
                    clientTier = t.name;
                    break;
                }
            }
            System.out.printf("| %11d | R$ %12.2f | %-8s |\n",
                    entry.getKey(), entry.getValue(), clientTier);
        }
        System.out.println("+-------------+-----------------+----------+\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! TABELAS ASSOCIATIVAS E TABELAS DERIVADAS DOMINADAS!");
        System.out.println("=========================================================================================");
    }
}
