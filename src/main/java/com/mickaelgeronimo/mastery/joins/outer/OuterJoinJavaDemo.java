package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 23 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junções Externas (Outer Joins) — LEFT JOIN e RIGHT JOIN vs INNER JOIN,
 *       Tratamento de Nulos e o Exercício Oficial de Produtos e Itens de Pedidos
 * ==============================================================================
 */
public class OuterJoinJavaDemo {

    // Entidade Cliente
    static class Customer {
        int id;
        String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // Entidade Pedido
    static class Order {
        int orderId;
        int customerId;

        public Order(int orderId, int customerId) {
            this.orderId = orderId;
            this.customerId = customerId;
        }
    }

    // Entidade Produto
    static class Product {
        int productId;
        String name;

        public Product(int productId, String name) {
            this.productId = productId;
            this.name = name;
        }
    }

    // Entidade Item do Pedido
    static class OrderItem {
        int orderId;
        int productId;
        int quantity;

        public OrderItem(int orderId, int productId, int quantity) {
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: COMO O 'LEFT JOIN' (OUTER JOIN) FUNCIONA NA MEMÓRIA DA JVM");
        System.out.println("=========================================================================================\n");

        // 1. CARGA DE CLIENTES (10 Clientes)
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

        // 2. CARGA DE PEDIDOS (10 Pedidos distribuídos entre apenas 6 clientes)
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

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O COMPORTAMENTO DO INNER JOIN (CLUBE EXCLUSIVO - DESCARTA QUEM NÃO COMPROU)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O INNER JOIN (DESCARTA CLIENTES SEM PEDIDOS) ---");
        System.out.println("+-------------+----------------------+----------+");
        System.out.println("| customer_id | first_name           | order_id |");
        System.out.println("+-------------+----------------------+----------+");

        int innerCount = 0;
        for (Customer c : customers) {
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    System.out.printf("| %-11d | %-20s | %-8d |\n", c.id, c.name, o.orderId);
                    innerCount++;
                }
            }
        }
        System.out.println("+-------------+----------------------+----------+");
        System.out.println("Total de linhas: " + innerCount + " linhas.");
        System.out.println("⚠️ NOTA: Clientes 1, 3, 4 e 9 DESAPARECERAM do relatório porque nunca compraram!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O PODER DO LEFT JOIN (OUTER JOIN - PRESERVA A TABELA DA ESQUERDA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. O LEFT JOIN (PRESERVA TODOS OS CLIENTES + GERA NULL PARA QUEM NÃO COMPROU) ---");
        System.out.println("+-------------+----------------------+------------------+");
        System.out.println("| customer_id | first_name           | order_id         |");
        System.out.println("+-------------+----------------------+------------------+");

        int leftCount = 0;
        for (Customer c : customers) {
            boolean hasOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    System.out.printf("| %-11d | %-20s | %-16d |\n", c.id, c.name, o.orderId);
                    hasOrder = true;
                    leftCount++;
                }
            }
            // SE O CLIENTE NÃO TIVER NENHUM PEDIDO, ELE NÃO É DESCARTADO!
            // ELE É PRESERVADO COM 'NULL' NA COLUNA DO PEDIDO!
            if (!hasOrder) {
                System.out.printf("| %-11d | %-20s | %-16s |\n", c.id, c.name, "NULL (Sem compra)");
                leftCount++;
            }
        }
        System.out.println("+-------------+----------------------+------------------+");
        System.out.println("Total de linhas no LEFT JOIN: " + leftCount + " linhas.");
        System.out.println("✨ SUCESSO: Todos os 10 clientes apareceram no relatório do time de Marketing!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: O EXERCÍCIO OFICIAL DO MOSH (PRODUTOS LEFT JOIN ITENS DE PEDIDOS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. O EXERCÍCIO OFICIAL DO MOSH: PRODUTOS LEFT JOIN ITENS DE PEDIDOS ---");
        List<Product> products = Arrays.asList(
            new Product(1, "Foam Dinner Plate"),
            new Product(2, "Pork - Bacon Cooked"),
            new Product(3, "Lettuce - Romaine"),
            new Product(4, "Brocolli - Stir Fry"),
            new Product(5, "Sauce - Ranch"),
            new Product(6, "Petit Baguette"),
            new Product(7, "Sweet Pea Sprouts"), // NUNCA VENDIDO!
            new Product(8, "Island Oasis - Raspberry"),
            new Product(9, "Longan"),
            new Product(10, "Broom - Push")
        );

        List<OrderItem> items = Arrays.asList(
            new OrderItem(1, 4, 4),
            new OrderItem(2, 1, 2),
            new OrderItem(2, 4, 4),
            new OrderItem(2, 6, 1),
            new OrderItem(3, 3, 10),
            new OrderItem(4, 9, 4),
            new OrderItem(5, 2, 3),
            new OrderItem(5, 6, 2),
            new OrderItem(6, 3, 4),
            new OrderItem(6, 5, 1),
            new OrderItem(7, 3, 7),
            new OrderItem(7, 5, 2),
            new OrderItem(8, 5, 2),
            new OrderItem(8, 8, 2),
            new OrderItem(9, 10, 1),
            new OrderItem(10, 1, 10),
            new OrderItem(10, 9, 9)
        );

        System.out.println("+------------+--------------------------------+--------------------+");
        System.out.println("| product_id | name                           | quantity           |");
        System.out.println("+------------+--------------------------------+--------------------+");

        for (Product p : products) {
            boolean sold = false;
            for (OrderItem item : items) {
                if (p.productId == item.productId) {
                    System.out.printf("| %-10d | %-30s | %-18d |\n", p.productId, p.name, item.quantity);
                    sold = true;
                }
            }
            if (!sold) {
                System.out.printf("| %-10d | %-30s | %-18s |\n", p.productId, p.name, "NULL (Sem vendas!)");
            }
        }
        System.out.println("+------------+--------------------------------+--------------------+");
        System.out.println("🔍 OBSERVE: O Produto 7 ('Sweet Pea Sprouts') apareceu com quantity = NULL!");
        System.out.println("Se usássemos INNER JOIN, o Produto 7 seria completamente ignorado no relatório!\n");

        System.out.println("=========================================================================================");
        System.out.println("   FIM DA DEMONSTRAÇÃO: LEFT JOIN PRESERVA TUDO DA TABELA DA ESQUERDA!");
        System.out.println("=========================================================================================");
    }
}
