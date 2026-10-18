package com.mickaelgeronimo.mastery.joins.using_clause;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 26 (PARTE 3): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Recursos Avançados do USING — Resolução Automática de Ambiguidade,
 *       Junções Multi-Bancos (Cross-DB) e Anti-Joins de Clientes Inativos
 * ==============================================================================
 */
public class UsingClausePart3AdvancedDemo {

    // --- MODELOS DO BANCO sql_store ---
    record Customer(int customerId, String firstName, String phone) {}
    record Order(int orderId, int customerId) {}
    record OrderItem(int orderId, int productId, int quantity, double unitPrice) {}

    // --- MODELO DO BANCO sql_inventory ---
    record Product(int productId, String name) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 26 (PARTE 3) - RECURSOS AVANÇADOS DA CLÁUSULA USING       ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS CLIENTES (10 Clientes)
        List<Customer> allCustomers = Arrays.asList(
            new Customer(1, "Babara", "781-932-9754"),
            new Customer(2, "Ines", "804-427-9456"),
            new Customer(3, "Freddi", "719-724-7869"),
            new Customer(4, "Ambur", "407-231-8017"),   // INATIVO (Sem Pedidos)
            new Customer(5, "Clemmie", "904-406-0780"),
            new Customer(6, "Elka", "312-480-8498"),
            new Customer(7, "Ilene", "415-644-6123"),
            new Customer(8, "Freddi", "615-641-4759"),
            new Customer(9, "Ruth", "808-839-8178"),    // INATIVO (Sem Pedidos)
            new Customer(10, "Levy", "904-468-4560")
        );

        // 2. CARREGAMENTO DOS PEDIDOS
        List<Order> orders = Arrays.asList(
            new Order(1, 6),
            new Order(2, 7),
            new Order(3, 8),
            new Order(4, 10),
            new Order(5, 2),
            new Order(6, 1),
            new Order(7, 5),
            new Order(8, 8),
            new Order(9, 3),
            new Order(10, 6)
        );

        // 3. CARREGAMENTO DOS PRODUTOS DO BANCO sql_inventory
        Map<Integer, Product> inventoryProducts = Map.of(
            1, new Product(1, "Foam Dinner Plate"),
            2, new Product(2, "Pork - Bacon,back Peameal"),
            3, new Product(3, "Lettuce - Romaine, Cello"),
            4, new Product(4, "Brocolli - Fresh"),
            5, new Product(5, "Sauce - Ranch Dressing"),
            6, new Product(6, "Petit Baguette")
        );

        // 4. ITENS DE PEDIDOS (sql_store.order_items)
        List<OrderItem> orderItems = Arrays.asList(
            new OrderItem(1, 4, 4, 3.74),
            new OrderItem(2, 1, 2, 9.10),
            new OrderItem(2, 4, 4, 1.66),
            new OrderItem(2, 6, 2, 2.94),
            new OrderItem(3, 3, 10, 9.50)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A CURA DA AMBIGUIDADE (SELECT customer_id SEM PREFIXO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CURA DA AMBIGUIDADE: 'customer_id' SEM PREFIXO NO SELECT ---");
        System.out.println("+-------------+----------+------------+");
        System.out.println("| customer_id | order_id | first_name |");
        System.out.println("+-------------+----------+------------+");

        Map<Integer, Customer> customerMap = allCustomers.stream()
                .collect(Collectors.toMap(Customer::customerId, c -> c));

        for (Order o : orders) {
            Customer c = customerMap.get(o.customerId());
            // No SQL com USING, 'customer_id' não precisa de 'o.' nem de 'c.'!
            System.out.printf("| %11d | %8d | %-10s |\n", o.customerId(), o.orderId(), c.firstName());
        }
        System.out.println("+-------------+----------+------------+");
        System.out.println("10 rows in set (0.00 sec) -> Sem Erro 1052 de Coluna Ambígua!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: JUNÇÃO MULTI-BANCOS (CROSS-DATABASE) COM USING (product_id)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. JUNÇÃO MULTI-BANCOS (sql_store.order_items + sql_inventory.products) ---");
        System.out.println("+----------+------------+-----------------------------+----------+------------+");
        System.out.println("| order_id | product_id | product_name                | quantity | unit_price |");
        System.out.println("+----------+------------+-----------------------------+----------+------------+");

        for (OrderItem oi : orderItems) {
            Product p = inventoryProducts.get(oi.productId());
            String pName = (p != null) ? p.name() : "Desconhecido";
            System.out.printf("| %8d | %10d | %-27s | %8d | %10.2f |\n",
                    oi.orderId(), oi.productId(), pName, oi.quantity(), oi.unitPrice());
        }
        System.out.println("+----------+------------+-----------------------------+----------+------------+");
        System.out.println("5 rows in set (0.00 sec) -> Cross-Database Join executado com sucesso!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: ANTI-JOIN COM USING (CLIENTES QUE NUNCA COMPRARAM)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. ANTI-JOIN COM USING: CLIENTES SEM NENHUM PEDIDO CADASTRADO ---");
        System.out.println("+-------------+------------+--------------+");
        System.out.println("| customer_id | first_name | phone        |");
        System.out.println("+-------------+------------+--------------+");

        Set<Integer> customersWithOrders = orders.stream()
                .map(Order::customerId)
                .collect(Collectors.toSet());

        List<Customer> inactiveCustomers = allCustomers.stream()
                .filter(c -> !customersWithOrders.contains(c.customerId()))
                .collect(Collectors.toList());

        for (Customer c : inactiveCustomers) {
            System.out.printf("| %11d | %-10s | %-12s |\n", c.customerId(), c.firstName(), c.phone());
        }
        System.out.println("+-------------+------------+--------------+");
        System.out.println("2 rows in set (0.00 sec) -> Clientes 4 (Ambur) e 9 (Ruth) identificados com precisão!\n");
    }
}
