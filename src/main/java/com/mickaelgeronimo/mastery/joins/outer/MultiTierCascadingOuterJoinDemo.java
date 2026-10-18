package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 24 (PARTE 3): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Cadeias Seriais Profundas de 5 Níveis com Outer Joins,
 *       Tratamento de Grafos de Objetos em Memória e Agregações Seguras
 * ==============================================================================
 */
public class MultiTierCascadingOuterJoinDemo {

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
        public Product(int id, String name) { this.id = id; this.name = name; }
    }

    static class OrderItemNote {
        int orderId;
        int productId;
        String note;
        public OrderItemNote(int orderId, int productId, String note) {
            this.orderId = orderId; this.productId = productId; this.note = note;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CADEIAS SERIAIS PROFUNDAS COM OUTER JOINS (CAPÍTULO 24 - PARTE 3)");
        System.out.println("=========================================================================================\n");

        // 1. CARGA DAS 5 TABELAS DO BANCO sql_store
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

        Map<Integer, Product> products = Map.of(
            1, new Product(1, "Foam Dinner Plate"),
            2, new Product(2, "Pork - Bacon Cooked"),
            3, new Product(3, "Lettuce - Romaine"),
            4, new Product(4, "Brocolli - Stir Fry"),
            5, new Product(5, "Sauce - Ranch"),
            6, new Product(6, "Petit Baguette"),
            7, new Product(7, "Sweet Pea Sprouts"),
            8, new Product(8, "Island Oasis - Raspberry"),
            9, new Product(9, "Longan"),
            10, new Product(10, "Broom - Push")
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

        // Apenas dois itens possuem anotações logísticas especiais:
        List<OrderItemNote> notes = Arrays.asList(
            new OrderItemNote(2, 1, "Verificar validade curta do lote"),
            new OrderItemNote(2, 4, "Carga frágil, manusear com cuidado")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A CADEIA SERIAL DE 5 NÍVEIS COM CASCADING LEFT JOIN
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CADEIA SERIAL DE 5 NÍVEIS: CLIENTE -> PEDIDO -> ITEM -> PRODUTO -> NOTA ---");
        System.out.println("+----+----------------------+----------+----------------------+------+---------------------------------------+");
        System.out.println("| ID | Cliente              | Pedido   | Produto              | Qtd  | Anotação Logística Especial           |");
        System.out.println("+----+----------------------+----------+----------------------+------+---------------------------------------+");

        int serialRows = 0;
        for (Customer c : customers) {
            boolean hasOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    hasOrder = true;
                    for (OrderItem oi : items) {
                        if (o.orderId == oi.orderId) {
                            Product p = products.get(oi.productId);

                            // Busca Anotação (Nível 5)
                            String noteText = "Sem Anotações Especiais";
                            for (OrderItemNote oin : notes) {
                                if (oin.orderId == oi.orderId && oin.productId == oi.productId) {
                                    noteText = oin.note;
                                    break;
                                }
                            }

                            System.out.printf("| %-2d | %-20s | %-8d | %-20s | %-4d | %-37s |\n",
                                    c.id, c.name, o.orderId, p.name, oi.quantity, noteText);
                            serialRows++;
                        }
                    }
                }
            }
            if (!hasOrder) {
                // Cliente inativo preservado por toda a cadeia serial!
                System.out.printf("| %-2d | %-20s | %-8s | %-20s | %-4s | %-37s |\n",
                        c.id, c.name, "NULL", "Nenhum Produto", "-", "Não Aplicável (Sem Compras)");
                serialRows++;
            }
        }
        System.out.println("+----+----------------------+----------+----------------------+------+---------------------------------------+");
        System.out.println("Total de registros na cadeia serial: " + serialRows + " linhas.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: AGREGAÇÃO FINANCEIRA SEGURA COM OUTER JOINS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. RELATÓRIO FINANCEIRO AGREGADO (PRESERVANDO CLIENTES INATIVOS COM ZERO) ---");
        System.out.println("+----+----------------------+---------------+---------------------+");
        System.out.println("| ID | Cliente              | Total Pedidos | Total Gasto (R$)    |");
        System.out.println("+----+----------------------+---------------+---------------------+");

        for (Customer c : customers) {
            Set<Integer> uniqueOrders = new HashSet<>();
            double totalSpent = 0.0;

            for (Order o : orders) {
                if (c.id == o.customerId) {
                    uniqueOrders.add(o.orderId);
                    for (OrderItem oi : items) {
                        if (o.orderId == oi.orderId) {
                            totalSpent += (oi.quantity * oi.unitPrice);
                        }
                    }
                }
            }

            System.out.printf("| %-2d | %-20s | %-13d | R$ %,14.2f |\n",
                    c.id, c.name, uniqueOrders.size(), totalSpent);
        }
        System.out.println("+----+----------------------+---------------+---------------------+");
        System.out.println("✨ Veja: Babara, Freddi, Ambur e Reggy possuem 0 pedidos e R$ 0,00 gastos com segurança total!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO: CADEIAS SERIAIS PROFUNDAS EXIGEM CASCADING LEFT JOIN TOTAL!");
        System.out.println("=========================================================================================");
    }
}
