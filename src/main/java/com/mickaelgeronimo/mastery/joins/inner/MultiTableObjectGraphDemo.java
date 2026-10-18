package com.mickaelgeronimo.mastery.joins.inner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MultiTableObjectGraphDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Navegação em Grafo de Objetos de Múltiplas Tabelas (Customer -> Order -> OrderItem -> Product).
 * 2. O Efeito Funil do INNER JOIN: como dados nulos (ex: pedidos sem transportadora) são descartados.
 * 3. Agregação financeira (GROUP BY SUM) calculando o total gasto por cliente através de 4 tabelas.
 */
public class MultiTableObjectGraphDemo {

    // 1. Tabela customers
    static class Customer {
        int id;
        String name;

        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // 2. Tabela shippers (Transportadoras)
    static class Shipper {
        int id;
        String name;

        Shipper(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // 3. Tabela orders
    static class Order {
        int orderId;
        int customerId;
        LocalDate orderDate;
        Integer shipperId; // Pode ser null se ainda não foi enviado!

        Order(int orderId, int customerId, LocalDate orderDate, Integer shipperId) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
            this.shipperId = shipperId;
        }
    }

    // 4. Tabela order_items
    static class OrderItem {
        int orderId;
        int productId;
        int quantity;
        double unitPrice;

        OrderItem(int orderId, int productId, int quantity, double unitPrice) {
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double getSubtotal() {
            return quantity * unitPrice;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: MULTI-TABLE JOINS AVANÇADOS (GRAFO, EFEITO FUNIL E TOTAIS)");
        System.out.println("=========================================================================================\n");

        // Populando dados
        Map<Integer, Customer> customers = new HashMap<>();
        customers.put(1, new Customer(1, "Mickael Geronimo"));
        customers.put(2, new Customer(2, "Lucas Silva"));

        Map<Integer, Shipper> shippers = new HashMap<>();
        shippers.put(1, new Shipper(1, "FedEx"));
        shippers.put(2, new Shipper(2, "UPS"));

        List<Order> orders = new ArrayList<>();
        // Pedido 101: Já tem transportadora (FedEx)
        orders.add(new Order(101, 1, LocalDate.of(2026, 1, 10), 1));
        // Pedido 102: NÃO TEM TRANSPORTADORA AINDA (shipperId = null)!
        orders.add(new Order(102, 1, LocalDate.of(2026, 2, 15), null));
        // Pedido 103: Tem transportadora (UPS)
        orders.add(new Order(103, 2, LocalDate.of(2026, 3, 20), 2));

        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(101, 10, 2, 50.00));  // 100.00
        items.add(new OrderItem(102, 20, 1, 150.00)); // 150.00
        items.add(new OrderItem(103, 30, 3, 30.00));  // 90.00

        System.out.println("--- 1. O EFEITO FUNIL DO INNER JOIN (JOIN COM SHIPPERS) ---");
        System.out.println("+----------+----------------------+------------+--------------------+");
        System.out.println("| order_id | customer_name        | order_date | shipper_name       |");
        System.out.println("+----------+----------------------+------------+--------------------+");

        int pedidosListados = 0;
        for (Order o : orders) {
            Customer c = customers.get(o.customerId);
            // INNER JOIN: se shipperId é null ou não existe em shippers, descarta!
            if (o.shipperId != null && shippers.containsKey(o.shipperId)) {
                Shipper s = shippers.get(o.shipperId);
                System.out.printf("| %8d | %-20s | %10s | %-18s |\n",
                        o.orderId, c.name, o.orderDate, s.name);
                pedidosListados++;
            }
        }
        System.out.println("+----------+----------------------+------------+--------------------+");
        System.out.println("Total de pedidos no banco: " + orders.size() + " | Listados no INNER JOIN: " + pedidosListados);
        System.out.println("⚠️ ALERTA: O Pedido 102 SUMIU porque ainda não tem transportadora (shipper_id = null)!\n");

        System.out.println("--- 2. AGREGAÇÃO MULTI-TABELA: TOTAL GASTO POR CLIENTE (GROUP BY SUM) ---");
        System.out.println("+----------------------+---------------+----------------------+");
        System.out.println("| Cliente              | Total Pedidos | Total Gasto (R$)     |");
        System.out.println("+----------------------+---------------+----------------------+");

        for (Customer c : customers.values()) {
            int countOrders = 0;
            double totalSpent = 0.0;

            for (Order o : orders) {
                if (o.customerId == c.id) {
                    countOrders++;
                    for (OrderItem oi : items) {
                        if (oi.orderId == o.orderId) {
                            totalSpent += oi.getSubtotal();
                        }
                    }
                }
            }
            System.out.printf("| %-20s | %13d | R$ %16.2f |\n", c.name, countOrders, totalSpent);
        }
        System.out.println("+----------------------+---------------+----------------------+\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! MULTI-TABLE OBJECT GRAPH EXECUTADO COM PERFEIÇÃO!");
        System.out.println("=========================================================================================");
    }
}
