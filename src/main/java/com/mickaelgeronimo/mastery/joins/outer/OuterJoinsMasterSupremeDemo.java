package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 23 (PARTE 5 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: O Desafio Supremo de 6 Tabelas com Cascading Outer Joins,
 *       Projeções com Java Records e Auditoria de Índices B-Tree
 * ==============================================================================
 */
public class OuterJoinsMasterSupremeDemo {

    // Record para Projeção DTO Imutável (Java 16+)
    record GlobalReportDTO(
        int customerId,
        String customerName,
        String phone,
        String orderId,
        String orderDate,
        String status,
        String product,
        String quantity,
        String unitPrice,
        String shipper
    ) {}

    static class Customer {
        int id;
        String name;
        String phone;
        public Customer(int id, String name, String phone) { this.id = id; this.name = name; this.phone = phone; }
    }

    static class Order {
        int orderId;
        int customerId;
        String date;
        int statusId;
        Integer shipperId;
        public Order(int orderId, int customerId, String date, int statusId, Integer shipperId) {
            this.orderId = orderId; this.customerId = customerId; this.date = date; this.statusId = statusId; this.shipperId = shipperId;
        }
    }

    static class OrderItem {
        int orderId;
        int productId;
        int qty;
        double price;
        public OrderItem(int orderId, int productId, int qty, double price) {
            this.orderId = orderId; this.productId = productId; this.qty = qty; this.price = price;
        }
    }

    static class Product {
        int id;
        String name;
        public Product(int id, String name) { this.id = id; this.name = name; }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: GRAND FINALE CAPÍTULO 23 - RELATÓRIO 360° COM 6 TABELAS");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DAS BASES RELACIONAIS
        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara MacCaffrey", "781-932-9754"),
            new Customer(2, "Ines Brushfield", "804-427-9456"),
            new Customer(3, "Freddi Boagey", "719-724-7869"),
            new Customer(4, "Ambur Roseburgh", "407-231-8017"),
            new Customer(5, "Clemmie Betchley", "415-144-6037"),
            new Customer(6, "Elka Twiddell", "312-480-8498"),
            new Customer(7, "Ilene Palamis", "615-641-4759"),
            new Customer(8, "Levy Mynett", "970-362-9528"),
            new Customer(9, "Reggy Gabarn", "555-019-2834"),
            new Customer(10, "Babette Larriman", "901-725-5468")
        );

        Map<Integer, String> statuses = Map.of(1, "Processed", 2, "Shipped", 3, "Delivered");
        Map<Integer, String> shippers = Map.of(
            1, "Hettinger LLC", 2, "Varner-Rowe", 3, "Bernier-Rempel", 4, "Carrier", 5, "Mauritz-Dullaghan"
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
            new Order(1, 6, "2019-01-30", 1, 1),
            new Order(2, 7, "2018-08-02", 1, 4),
            new Order(3, 8, "2017-12-01", 1, null),
            new Order(4, 2, "2017-01-22", 1, null),
            new Order(5, 5, "2017-08-25", 2, 5),
            new Order(6, 10, "2018-11-18", 1, null),
            new Order(7, 2, "2018-09-22", 2, null),
            new Order(8, 5, "2018-06-08", 1, 2),
            new Order(9, 10, "2017-07-05", 1, null),
            new Order(10, 6, "2018-04-22", 2, 1)
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
        // SIMULAÇÃO DO RELATÓRIO 360° COM CASCADING OUTER JOINS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. PROJEÇÃO DO RELATÓRIO CORPORATIVO 360° (PRIMEIROS REGISTROS) ---");
        System.out.println("+----+----------------------+----------+------------+-------------+----------------------+------+-----------+---------------------+");
        System.out.println("| ID | Cliente              | Pedido   | Data       | Status      | Produto Vendido      | Qtd  | Preço     | Transportadora      |");
        System.out.println("+----+----------------------+----------+------------+-------------+----------------------+------+-----------+---------------------+");

        List<GlobalReportDTO> report = new ArrayList<>();

        for (Customer c : customers) {
            boolean hasOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    hasOrder = true;
                    boolean hasItems = false;
                    for (OrderItem oi : items) {
                        if (o.orderId == oi.orderId) {
                            hasItems = true;
                            Product p = products.get(oi.productId);
                            String shipperName = (o.shipperId != null && shippers.containsKey(o.shipperId))
                                    ? shippers.get(o.shipperId) : "Sem Despacho";

                            report.add(new GlobalReportDTO(
                                c.id, c.name, c.phone, String.valueOf(o.orderId), o.date,
                                statuses.get(o.statusId), p.name, String.valueOf(oi.qty),
                                String.format("$%.2f", oi.price), shipperName
                            ));
                        }
                    }
                    if (!hasItems) {
                        String shipperName = (o.shipperId != null) ? shippers.get(o.shipperId) : "Sem Despacho";
                        report.add(new GlobalReportDTO(
                            c.id, c.name, c.phone, String.valueOf(o.orderId), o.date,
                            statuses.get(o.statusId), "Nenhum Item", "-", "-", shipperName
                        ));
                    }
                }
            }
            if (!hasOrder) {
                // Cliente inativo preservado pelo LEFT JOIN!
                report.add(new GlobalReportDTO(
                    c.id, c.name, c.phone, "NULL", "NULL", "Sem Histórico", "Nenhum Item", "-", "-", "Aguardando Compra"
                ));
            }
        }

        // Imprime as primeiras 8 linhas da projeção
        for (int i = 0; i < Math.min(8, report.size()); i++) {
            GlobalReportDTO r = report.get(i);
            System.out.printf("| %-2d | %-20s | %-8s | %-10s | %-11s | %-20s | %-4s | %-9s | %-19s |\n",
                    r.customerId, r.customerName, r.orderId, r.orderDate, r.status,
                    r.product.length() > 20 ? r.product.substring(0, 17) + "..." : r.product,
                    r.quantity, r.unitPrice, r.shipper);
        }
        System.out.println("+----+----------------------+----------+------------+-------------+----------------------+------+-----------+---------------------+");
        System.out.println("Total de registros gerados no relatório 360°: " + report.size() + " linhas.");
        System.out.println("✨ Todos os 10 clientes, 10 pedidos, itens e transportadoras combinados com segurança total!\n");

        // --------------------------------------------------------------------------------------
        // DEMONSTRAÇÃO DE ÍNDICES NO LEFT JOIN (POR QUE NÃO HÁ COMUTATIVIDADE?)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A REGRA DE PERFORMANCE DO LEFT JOIN ---");
        System.out.println("• No INNER JOIN: A ordem das tabelas NÃO IMPORTA (A JOIN B é igual a B JOIN A).");
        System.out.println("  O otimizador escolhe a tabela menor para iniciar e a maior para indexar.");
        System.out.println("• No LEFT JOIN: A ordem é RÍGIDA E FIXA! (A LEFT JOIN B NUNCA É IGUAL A B LEFT JOIN A)!");
        System.out.println("  A tabela da ESQUERDA (A) SEMPRE é lida primeiro via Full Scan.");
        System.out.println("  A tabela da DIREITA (B) DEVE OBRIGATORIAMENTE possuir índice B-Tree na chave estrangeira,");
        System.out.println("  senão o banco fará buscas lentas para cada linha da tabela mestra!");

        System.out.println("\n=========================================================================================");
        System.out.println("   FIM DO CAPÍTULO 23! VOCÊ AGORA DOMINA 100% DAS JUNÇÕES EXTERNAS EM SQL E JAVA!");
        System.out.println("=========================================================================================");
    }
}
