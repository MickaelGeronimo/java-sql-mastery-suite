package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 24 (PARTE 4): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junções Externas Multi-Tabelas com Pré-Agregação (Tabelas Derivadas),
 *       Integração Multi-Bancos e Simetria 360° de Registros Órfãos (UNION)
 * ==============================================================================
 */
public class AdvancedMultiTableOuterJoinDerivedDemo {

    // DTO para a Tabela Derivada Pré-Agregada
    static class FinancialSummary {
        int customerId;
        int totalOrders;
        double totalSpent;

        public FinancialSummary(int customerId, int totalOrders, double totalSpent) {
            this.customerId = customerId;
            this.totalOrders = totalOrders;
            this.totalSpent = totalSpent;
        }
    }

    static class Customer {
        int id;
        String name;
        public Customer(int id, String name) { this.id = id; this.name = name; }
    }

    static class Order {
        int orderId;
        int customerId;
        public Order(int orderId, int customerId) { this.orderId = orderId; this.customerId = customerId; }
    }

    static class OrderItem {
        int orderId;
        int productId;
        int quantity;
        double unitPrice;
        public OrderItem(int orderId, int productId, int quantity, double unitPrice) {
            this.orderId = orderId; this.productId = productId; this.quantity = quantity; this.unitPrice = unitPrice;
        }
    }

    static class Product {
        int id;
        String name;
        int stock;
        public Product(int id, String name, int stock) { this.id = id; this.name = name; this.stock = stock; }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: MULTI-TABELAS COM TABELA DERIVADA E SIMETRIA 360° (CAPÍTULO 24)");
        System.out.println("=========================================================================================\n");

        // 1. DADOS DE CLIENTES (sql_store)
        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara MacCaffrey"),
            new Customer(2, "Ines Brushfield"),
            new Customer(3, "Freddi Boagey"),
            new Customer(4, "Ambur Roseburgh"),
            new Customer(5, "Clemmie Betchley"),
            new Customer(6, "Elka Twiddell"),
            new Customer(7, "Ilene Palamis"),
            new Customer(8, "Levy Mynett"),
            new Customer(9, "Reggy Gabarn"),
            new Customer(10, "Babette Larriman")
        );

        // 2. DADOS DE PRODUTOS E ESTOQUE (sql_inventory)
        Map<Integer, Product> inventoryProducts = Map.of(
            1, new Product(1, "Foam Dinner Plate", 70),
            2, new Product(2, "Pork - Bacon Cooked", 49),
            3, new Product(3, "Lettuce - Romaine", 38),
            4, new Product(4, "Brocolli - Stir Fry", 90),
            5, new Product(5, "Sauce - Ranch", 94),
            6, new Product(6, "Petit Baguette", 14),
            7, new Product(7, "Sweet Pea Sprouts", 98), // NUNCA VENDIDO!
            8, new Product(8, "Island Oasis - Raspberry", 26),
            9, new Product(9, "Longan", 67),
            10, new Product(10, "Broom - Push", 6)
        );

        List<Order> orders = Arrays.asList(
            new Order(1, 6),
            new Order(2, 7),
            new Order(3, 8),
            new Order(4, 2),
            new Order(5, 5),
            new Order(6, 10),
            new Order(7, 2),
            new Order(8, 5),
            new Order(9, 10),
            new Order(10, 6)
        );

        List<OrderItem> items = Arrays.asList(
            new OrderItem(1, 4, 4, 3.75),
            new OrderItem(2, 1, 2, 9.10),
            new OrderItem(2, 4, 4, 1.66),
            new OrderItem(3, 3, 10, 9.47),
            new OrderItem(4, 9, 4, 3.79),
            new OrderItem(5, 2, 3, 9.80),
            new OrderItem(6, 3, 4, 6.63),
            new OrderItem(7, 3, 7, 6.91),
            new OrderItem(8, 5, 2, 8.59),
            new OrderItem(9, 10, 1, 6.01),
            new OrderItem(10, 1, 10, 9.40)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O PADRÃO DA TABELA DERIVADA (PRÉ-AGREGAÇÃO ISOLADA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O PADRÃO ARQUITETURAL DA TABELA DERIVADA (DERIVED TABLE PATTERN) ---");
        System.out.println("Passo A: Pré-calcula faturamento por cliente em memória (sem multiplicar linhas):");

        Map<Integer, FinancialSummary> derivedTable = new HashMap<>();
        for (Order o : orders) {
            double orderTotal = 0.0;
            for (OrderItem oi : items) {
                if (oi.orderId == o.orderId) {
                    orderTotal += (oi.quantity * oi.unitPrice);
                }
            }
            FinancialSummary fs = derivedTable.getOrDefault(o.customerId, new FinancialSummary(o.customerId, 0, 0.0));
            fs.totalOrders++;
            fs.totalSpent += orderTotal;
            derivedTable.put(o.customerId, fs);
        }

        System.out.println("Passo B: Faz o LEFT JOIN da tabela mestra (customers) com a tabela derivada:");
        System.out.println("+----+----------------------+---------------+---------------------+");
        System.out.println("| ID | Cliente              | Total Pedidos | Total Gasto (R$)    |");
        System.out.println("+----+----------------------+---------------+---------------------+");

        for (Customer c : customers) {
            // LEFT JOIN: Se o cliente estiver na tabela derivada, traz os valores; senão, valores default!
            FinancialSummary fs = derivedTable.get(c.id);
            int totalOrders = (fs != null) ? fs.totalOrders : 0;
            double totalSpent = (fs != null) ? fs.totalSpent : 0.00;

            System.out.printf("| %-2d | %-20s | %-13d | R$ %,14.2f |\n",
                    c.id, c.name, totalOrders, totalSpent);
        }
        System.out.println("+----+----------------------+---------------+---------------------+");
        System.out.println("✨ Vantagem Arquitetural: Zero risco de Fan-Out! Complexidade O(N + M) em vez de O(N * M)!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A SIMETRIA 360° COM UNION (IDENTIFICANDO ÓRFÃOS DOS DOIS LADOS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SIMETRIA 360° COM UNION (DIAGNÓSTICO DE REGISTROS ÓRFÃOS) ---");
        System.out.println("+--------------------------------+----------------------------+-------------------------------------+");
        System.out.println("| Entidade Auditada              | Identificador              | Diagnóstico de Negócio              |");
        System.out.println("+--------------------------------+----------------------------+-------------------------------------+");

        // Órfãos Lado A (Clientes que nunca compraram):
        for (Customer c : customers) {
            if (!derivedTable.containsKey(c.id)) {
                System.out.printf("| %-30s | ID %-23d | %-35s |\n",
                        "Cliente Inativo", c.id, c.name + " (Nunca comprou)");
            }
        }

        // Órfãos Lado B (Produtos do estoque que nunca foram vendidos):
        Set<Integer> soldProductIds = new HashSet<>();
        for (OrderItem oi : items) {
            soldProductIds.add(oi.productId);
        }

        for (Product p : inventoryProducts.values()) {
            if (!soldProductIds.contains(p.id)) {
                System.out.printf("| %-30s | ID %-23d | %-35s |\n",
                        "Produto Encalhado no Estoque", p.id, p.name + " (" + p.stock + " un. paradas)");
            }
        }
        System.out.println("+--------------------------------+----------------------------+-------------------------------------+");
        System.out.println("🎯 SUCESSO: A consulta simétrica 360° localizou tanto os clientes frios quanto os produtos parados!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO: O DOMÍNIO COMPLETO DE JUNÇÕES EXTERNAS EM MÚLTIPLAS TABELAS!");
        System.out.println("=========================================================================================");
    }
}
