package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 24 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Resolução do Exercício Oficial de 4 Tabelas do Mosh Hamedani
 *       e o Mapeamento Arquitetural de Nulabilidade no JPA/Hibernate
 * ==============================================================================
 */
public class HybridOuterJoinEnterpriseExerciseDemo {

    static class Customer {
        int id;
        String firstName;

        public Customer(int id, String firstName) {
            this.id = id;
            this.firstName = firstName;
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

    // Entidade Pedido com metadados de nulabilidade
    static class Order {
        int orderId;
        String orderDate;
        int customerId;    // NOT NULL: Relacionamento Obrigatório (INNER JOIN)
        Integer shipperId; // NULLABLE: Relacionamento Opcional (LEFT JOIN)
        int statusId;      // NOT NULL: Relacionamento Obrigatório (INNER JOIN)

        public Order(int orderId, String orderDate, int customerId, Integer shipperId, int statusId) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.customerId = customerId;
            this.shipperId = shipperId;
            this.statusId = statusId;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: EXERCÍCIO OFICIAL DE 4 TABELAS DO MOSH (CAPÍTULO 24 - PARTE 2)");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS (sql_store)
        Map<Integer, Customer> customers = Map.of(
            1, new Customer(1, "Babara"),
            2, new Customer(2, "Ines"),
            3, new Customer(3, "Freddi"),
            4, new Customer(4, "Ambur"),
            5, new Customer(5, "Clemmie"),
            6, new Customer(6, "Elka"),
            7, new Customer(7, "Ilene"),
            8, new Customer(8, "Levy"),
            9, new Customer(9, "Reggy"),
            10, new Customer(10, "Babette")
        );

        Map<Integer, Shipper> shippers = Map.of(
            1, new Shipper(1, "Hettinger LLC"),
            2, new Shipper(2, "Varner-Rowe"),
            3, new Shipper(3, "Bernier-Rempel"),
            4, new Shipper(4, "Carrier"),
            5, new Shipper(5, "Mauritz-Dullaghan")
        );

        Map<Integer, OrderStatus> statuses = Map.of(
            1, new OrderStatus(1, "Processed"),
            2, new OrderStatus(2, "Shipped"),
            3, new OrderStatus(3, "Delivered")
        );

        List<Order> orders = Arrays.asList(
            new Order(1, "2019-01-30", 6, 1, 1),
            new Order(2, "2018-08-02", 7, 4, 1),
            new Order(3, "2017-12-01", 8, null, 1), // Sem transportadora
            new Order(4, "2017-01-22", 2, null, 1), // Sem transportadora
            new Order(5, "2017-08-25", 5, 5, 2),
            new Order(6, "2018-11-18", 10, null, 1), // Sem transportadora
            new Order(7, "2018-09-22", 2, null, 2), // Sem transportadora
            new Order(8, "2018-06-08", 5, 2, 1),
            new Order(9, "2017-07-05", 10, null, 1), // Sem transportadora
            new Order(10, "2018-04-22", 6, 1, 2)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A RESOLUÇÃO OFICIAL DO MOSH HAMEDANI
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O RELATÓRIO OFICIAL DE 4 TABELAS EXECUTADO NA JVM ---");
        System.out.println("+------------+----------+------------+--------------------+-----------+");
        System.out.println("| order_date | order_id | first_name | shipper            | status    |");
        System.out.println("+------------+----------+------------+--------------------+-----------+");

        for (Order o : orders) {
            // INNER JOIN: customers (chave NOT NULL)
            Customer c = customers.get(o.customerId);

            // LEFT JOIN: shippers (chave NULLABLE)
            String shipperName = (o.shipperId != null && shippers.containsKey(o.shipperId))
                    ? shippers.get(o.shipperId).name
                    : "NULL (Sem Transportadora)";

            // INNER JOIN: order_statuses (chave NOT NULL)
            OrderStatus os = statuses.get(o.statusId);

            System.out.printf("| %-10s | %-8d | %-10s | %-18s | %-9s |\n",
                    o.orderDate, o.orderId, c.firstName, shipperName, os.name);
        }
        System.out.println("+------------+----------+------------+--------------------+-----------+");
        System.out.println("✨ Total de pedidos listados: " + orders.size() + " de 10 pedidos válidos.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O ERRO DE USAR INNER JOIN EM SHIPPERS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. O QUE ACONTECE SE USARMOS 'INNER JOIN' EM SHIPPERS? ---");
        int countComInner = 0;
        List<Integer> pedidosPerdidos = new ArrayList<>();

        for (Order o : orders) {
            if (o.shipperId != null) {
                countComInner++;
            } else {
                pedidosPerdidos.add(o.orderId);
            }
        }
        System.out.println("• Quantidade de pedidos exibidos com INNER JOIN: " + countComInner + " pedidos.");
        System.out.println("🚨 PEDIDOS DELETADOS SILENCIOSAMENTE: " + pedidosPerdidos);
        System.out.println("Motivo: Os pedidos 3, 4, 6, 7 e 9 ainda não têm transportadora definida!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A REGRA DO JPA / HIBERNATE NO SPRING BOOT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A REGRA DE OURO NO JAVA (SPRING DATA JPA / HIBERNATE) ---");
        System.out.println("• @ManyToOne(optional = false) -> O Hibernate gera 'INNER JOIN' (customer e status)");
        System.out.println("• @ManyToOne(optional = true)  -> O Hibernate gera 'LEFT OUTER JOIN' (shipper)");
        System.out.println("Regra de Arquitetura: Sempre olhe o banco de dados antes de escrever anotações no Java!");

        System.out.println("\n=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO: EXERCÍCIO OFICIAL DE 4 TABELAS TOTALMENTE DOMINADO!");
        System.out.println("=========================================================================================");
    }
}
