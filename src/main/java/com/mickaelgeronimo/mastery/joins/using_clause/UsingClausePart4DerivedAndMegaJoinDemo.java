package com.mickaelgeronimo.mastery.joins.using_clause;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 26 (PARTE 4): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Mega-Refatoração com USING, Tabelas Derivadas Agrupadas e a Armadilha
 *       dos Falsos Cognatos (False Homonym Trap)
 * ==============================================================================
 */
public class UsingClausePart4DerivedAndMegaJoinDemo {

    // --- MODELOS DE DADOS PARA A MEGA-CONSULTA DE 6 TABELAS ---
    record Customer(int customerId, String name) {}
    record Product(int productId, String name) {}
    record Shipper(int shipperId, String name) {}
    record OrderStatus(int id, String name) {}
    record OrderItem(int orderId, int productId, int quantity, double unitPrice) {}
    record Order(int orderId, String orderDate, int customerId, Integer shipperId, int status) {}

    // --- MODELOS DE FATURAMENTO (sql_invoicing) ---
    record Client(int clientId, String name) {}
    record Invoice(int invoiceId, int clientId, double invoiceTotal) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 26 (PARTE 4) - MEGA-REFATORAÇÃO E TABELAS DERIVADAS       ");
        System.out.println("=========================================================================================\n");

        // 1. CARGA DE DADOS DA LOJA (sql_store)
        Map<Integer, Customer> customers = Map.of(
            6, new Customer(6, "Elka Twiddell"),
            7, new Customer(7, "Ilene Prowse"),
            8, new Customer(8, "Freddi Boagey")
        );

        Map<Integer, Product> products = Map.of(
            1, new Product(1, "Foam Dinner Plate"),
            2, new Product(2, "Pork - Bacon,back Peameal"),
            3, new Product(3, "Lettuce - Romaine, Cello"),
            4, new Product(4, "Brocolli - Fresh"),
            6, new Product(6, "Petit Baguette")
        );

        Map<Integer, Shipper> shippers = Map.of(
            1, new Shipper(1, "Hettinger LLC"),
            4, new Shipper(4, "Batz-Bauch")
        );

        Map<Integer, OrderStatus> statuses = Map.of(
            1, new OrderStatus(1, "Processed"),
            2, new OrderStatus(2, "Shipped"),
            3, new OrderStatus(3, "Delivered")
        );

        List<Order> orders = Arrays.asList(
            new Order(1, "2019-01-30", 6, 1, 1),
            new Order(2, "2018-08-02", 7, 4, 1),
            new Order(3, "2017-12-01", 8, null, 1) // Sem transportadora
        );

        List<OrderItem> items = Arrays.asList(
            new OrderItem(1, 4, 4, 3.74),
            new OrderItem(2, 1, 2, 9.10),
            new OrderItem(2, 4, 4, 1.66),
            new OrderItem(2, 6, 2, 2.94),
            new OrderItem(3, 3, 10, 9.50)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A MEGA-CONSULTA DE 6 TABELAS REFATORADA COM USING
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. MEGA-REFATORAÇÃO DE 6 TABELAS COM A CLÁUSULA USING ---");
        System.out.println("+----+------------+----------------+--------------------------+-----+-------+-------+-----------+----------------------+");
        System.out.println("| ID | Data       | Cliente        | Produto                  | Qtd | Preço | Total | Status    | Transportadora       |");
        System.out.println("+----+------------+----------------+--------------------------+-----+-------+-------+-----------+----------------------+");

        for (OrderItem oi : items) {
            Order o = orders.stream().filter(ord -> ord.orderId() == oi.orderId()).findFirst().orElse(null);
            if (o != null) {
                Customer c = customers.get(o.customerId());
                Product p = products.get(oi.productId());
                OrderStatus os = statuses.get(o.status());
                String shName = (o.shipperId() != null && shippers.containsKey(o.shipperId()))
                        ? shippers.get(o.shipperId()).name()
                        : "Aguardando Despacho";

                double lineTotal = oi.quantity() * oi.unitPrice();

                System.out.printf("| %2d | %-10s | %-14s | %-24s | %3d | %5.2f | %5.2f | %-9s | %-20s |\n",
                        o.orderId(), o.orderDate(), c.name(), p.name(), oi.quantity(), oi.unitPrice(), lineTotal, os.name(), shName);
            }
        }
        System.out.println("+----+------------+----------------+--------------------------+-----+-------+-------+-----------+----------------------+");
        System.out.println("5 rows in set (0.00 sec) -> 4 junções usando USING e apenas 1 com ON!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: TABELAS DERIVADAS (SUBQUERY NO FROM) COM USING (client_id)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. TABELAS DERIVADAS (SUBQUERY GROUP BY) COM USING (client_id) ---");
        System.out.println("+-----------+----------------------+----------------+--------------+");
        System.out.println("| client_id | client_name          | total_invoices | sum_invoiced |");
        System.out.println("+-----------+----------------------+----------------+--------------+");

        Map<Integer, Client> clientMap = Map.of(
            1, new Client(1, "Vinte"),
            2, new Client(2, "Myworks"),
            3, new Client(3, "Yadel"),
            5, new Client(5, "Topiclounge")
        );

        List<Invoice> invoices = Arrays.asList(
            new Invoice(1, 1, 101.79),
            new Invoice(2, 1, 175.32),
            new Invoice(3, 3, 147.99),
            new Invoice(4, 2, 121.22),
            new Invoice(5, 5, 130.44),
            new Invoice(6, 1, 168.00)
        );

        // Agregação na subquery derivada
        Map<Integer, List<Invoice>> grouped = invoices.stream().collect(Collectors.groupingBy(Invoice::clientId));

        for (Map.Entry<Integer, List<Invoice>> entry : grouped.entrySet()) {
            int clientId = entry.getKey();
            Client cl = clientMap.get(clientId);
            if (cl != null) {
                int count = entry.getValue().size();
                double sum = entry.getValue().stream().mapToDouble(Invoice::invoiceTotal).sum();
                System.out.printf("| %9d | %-20s | %14d | %12.2f |\n", clientId, cl.name(), count, sum);
            }
        }
        System.out.println("+-----------+----------------------+----------------+--------------+");
        System.out.println("4 rows in set (0.00 sec) -> Subquery derivada casada perfeitamente com USING!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A ARMADILHA DOS FALSOS COGNATOS (FALSE HOMONYM TRAP)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. ALERTA DE ARQUITETURA: A ARMADILHA DOS FALSOS COGNATOS ---");
        System.out.println("⚠️ Se duas tabelas têm colunas chamadas 'name':");
        System.out.println("   products(name) e shippers(name)");
        System.out.println("💥 Fazer 'JOIN shippers USING (name)' fará o banco comparar o NOME DO PRODUTO");
        System.out.println("   com o NOME DA TRANSPORTADORA! (Ex: 'Foam Dinner Plate' == 'Hettinger LLC') -> FALSO!");
        System.out.println("✅ Regra: NUNCA use USING em atributos genéricos descritivos ('name', 'date', 'status').");
        System.out.println("   Use USING exclusivamente em chaves identificadoras ('customer_id', 'product_id')!\n");
    }
}
