package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 23 (PARTE 3): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junções Externas com Múltiplas Tabelas (Cascading LEFT JOIN),
 *       A Catástrofe da Quebra de Corrente e Prevenção de NullPointerException
 * ==============================================================================
 */
public class CascadingOuterJoinAndNullSafetyDemo {

    static class Customer {
        int id;
        String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Order {
        int orderId;
        int customerId;
        String orderDate;
        Integer shipperId; // Pode ser NULL se o pedido não foi despachado!
        int statusId;

        public Order(int orderId, int customerId, String orderDate, Integer shipperId, int statusId) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
            this.shipperId = shipperId;
            this.statusId = statusId;
        }
    }

    static class Shipper {
        int id;
        String name;

        public Shipper(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class OrderStatus {
        int id;
        String name;

        public OrderStatus(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CASCADING LEFT JOIN, QUEBRA DE CORRENTE E SEGURANÇA CONTRA NULOS");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS (sql_store)
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

        Map<Integer, Shipper> shippers = new HashMap<>();
        shippers.put(1, new Shipper(1, "Hettinger LLC"));
        shippers.put(2, new Shipper(2, "Varner-Rowe"));
        shippers.put(3, new Shipper(3, "Bernier-Rempel"));
        shippers.put(4, new Shipper(4, "Carrier"));
        shippers.put(5, new Shipper(5, "Mauritz-Dullaghan"));

        Map<Integer, OrderStatus> statuses = new HashMap<>();
        statuses.put(1, new OrderStatus(1, "Processed"));
        statuses.put(2, new OrderStatus(2, "Shipped"));
        statuses.put(3, new OrderStatus(3, "Delivered"));

        List<Order> orders = Arrays.asList(
            new Order(1, 6, "2019-01-30", 1, 1),
            new Order(2, 7, "2018-08-02", 4, 1),
            new Order(3, 8, "2017-12-01", null, 1), // Sem transportadora
            new Order(4, 2, "2017-01-22", null, 1), // Sem transportadora
            new Order(5, 5, "2017-08-25", 5, 2),
            new Order(6, 10, "2018-11-18", null, 1), // Sem transportadora
            new Order(7, 2, "2018-09-22", null, 2), // Sem transportadora
            new Order(8, 5, "2018-06-08", 2, 1),
            new Order(9, 10, "2017-07-05", null, 1), // Sem transportadora
            new Order(10, 6, "2018-04-22", 1, 2)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O EFEITO CASCATA PERFEITO (CASCADING LEFT JOIN EM 3 TABELAS)
        // SQL: customers c LEFT JOIN orders o ON ... LEFT JOIN shippers sh ON ...
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CASCADING LEFT JOIN COMPLETO (PRESERVAÇÃO TOTAL DOS 10 CLIENTES) ---");
        System.out.println("+-------------+----------------------+----------+--------------------------------+");
        System.out.println("| customer_id | first_name           | order_id | shipper_name                   |");
        System.out.println("+-------------+----------------------+----------+--------------------------------+");

        int cascadeCount = 0;
        for (Customer c : customers) {
            boolean hasOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    hasOrder = true;
                    // Tratamento elegante de nulo para transportadora (LEFT JOIN):
                    String shipperName = (o.shipperId != null && shippers.containsKey(o.shipperId))
                            ? shippers.get(o.shipperId).name
                            : "NULL (Sem despacho)";
                    System.out.printf("| %-11d | %-20s | %-8d | %-30s |\n", c.id, c.name, o.orderId, shipperName);
                    cascadeCount++;
                }
            }
            if (!hasOrder) {
                // Cliente sem compras:
                System.out.printf("| %-11d | %-20s | %-8s | %-30s |\n", c.id, c.name, "NULL", "NULL (Sem compras)");
                cascadeCount++;
            }
        }
        System.out.println("+-------------+----------------------+----------+--------------------------------+");
        System.out.println("Total de linhas preservadas: " + cascadeCount + " (EXATAMENTE as 14 linhas esperadas!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A CATÁSTROFE DA QUEBRA DE CORRENTE (INNER JOIN NO FINAL)
        // SQL: customers c LEFT JOIN orders o ON ... JOIN shippers sh ON ...
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A CATÁSTROFE DA QUEBRA DE CORRENTE (O QUE UM INNER JOIN FAZ NO FINAL?) ---");
        int brokenCount = 0;
        for (Customer c : customers) {
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    // CUIDADO: Se shippers for INNER JOIN, exige o.shipperId != null obrigatoriamente!
                    if (o.shipperId != null && shippers.containsKey(o.shipperId)) {
                        brokenCount++;
                    }
                }
            }
        }
        System.out.println("🚨 Total de linhas após colocar um INNER JOIN shippers: " + brokenCount + " linhas!");
        System.out.println("De 14 linhas originais, despencou para apenas 5 linhas!");
        System.out.println("Perda catastrófica de 9 linhas (64% dos dados corporativos foram sumariamente apagados!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: O EXERCÍCIO OFICIAL DO MOSH (TODOS OS PEDIDOS COM OU SEM TRANSPORTADORA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. O EXERCÍCIO OFICIAL DO MOSH HAMEDANI: RELATÓRIO DE DESPACHO ---");
        System.out.println("+------------+----------+----------------------+----------------------+--------------------+");
        System.out.println("| order_date | order_id | first_name           | shipper              | status             |");
        System.out.println("+------------+----------+----------------------+----------------------+--------------------+");

        for (Order o : orders) {
            // 1. Busca Cliente (INNER JOIN: todo pedido tem cliente)
            Customer cust = customers.stream().filter(c -> c.id == o.customerId).findFirst().orElse(null);
            String custName = cust != null ? cust.name : "Desconhecido";

            // 2. Busca Transportadora com Optional (LEFT JOIN: pode ser nulo!)
            String shipperName = Optional.ofNullable(o.shipperId)
                    .map(shippers::get)
                    .map(s -> s.name)
                    .orElse("Sem Transportadora");

            // 3. Busca Status (INNER JOIN: todo pedido tem status)
            String statusName = statuses.containsKey(o.statusId) ? statuses.get(o.statusId).name : "N/A";

            System.out.printf("| %-10s | %-8d | %-20s | %-20s | %-18s |\n",
                    o.orderDate, o.orderId, custName, shipperName, statusName);
        }
        System.out.println("+------------+----------+----------------------+----------------------+--------------------+");
        System.out.println("✨ Todos os 10 pedidos listados com sucesso! Os 5 pedidos não despachados exibem 'Sem Transportadora'!\n");

        System.out.println("=========================================================================================");
        System.out.println("   FIM DA PARTE 3: UMA VEZ LEFT JOIN, TODA A CADEIA DEPENDENTE DEVE SER LEFT JOIN!");
        System.out.println("=========================================================================================");
    }
}
