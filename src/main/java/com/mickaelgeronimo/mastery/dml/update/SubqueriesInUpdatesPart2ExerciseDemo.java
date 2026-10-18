package com.mickaelgeronimo.mastery.dml.update;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 37 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: O Exercício Oficial do Mosh — Atualização de Comentários ('Gold customer')
 *       na Tabela 'orders' Usando Subconsulta na Tabela 'customers' (points > 3000)
 * ==============================================================================
 */
public class SubqueriesInUpdatesPart2ExerciseDemo {

    public record Customer(int id, String firstName, int points) {}

    public static class Order {
        private final int orderId;
        private final int customerId;
        private final LocalDate orderDate;
        private String comments;

        public Order(int orderId, int customerId, LocalDate orderDate, String comments) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
            this.comments = comments;
        }

        public int getOrderId() { return orderId; }
        public int getCustomerId() { return customerId; }
        public LocalDate getOrderDate() { return orderDate; }
        public String getComments() { return comments; }

        public void setComments(String comments) {
            this.comments = comments;
        }

        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %-16s |",
                orderId, customerId, orderDate.toString(),
                comments == null ? "NULL" : comments);
        }
    }

    public static class StoreDatabaseService {
        private final List<Customer> customers = new ArrayList<>();
        private final List<Order> orders = new ArrayList<>();

        public void addCustomer(Customer c) { customers.add(c); }
        public void addOrder(Order o) { orders.add(o); }

        public List<Customer> getCustomers() { return Collections.unmodifiableList(customers); }
        public List<Order> getOrders() { return Collections.unmodifiableList(orders); }

        // Subconsulta isolada: Busca IDs de clientes com mais de 3000 pontos
        public Set<Integer> findGoldCustomerIds(int minPoints) {
            return customers.stream()
                .filter(c -> c.points() > minPoints)
                .map(Customer::id)
                .collect(Collectors.toSet());
        }

        // Execução do UPDATE orders SET comments = 'Gold customer' WHERE customer_id IN (...)
        public int updateOrdersCommentsForGoldCustomers(Set<Integer> goldCustomerIds, String tag) {
            int rowsAffected = 0;
            for (Order o : orders) {
                if (goldCustomerIds.contains(o.getCustomerId())) {
                    o.setComments(tag);
                    rowsAffected++;
                }
            }
            return rowsAffected;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 37 (PARTE 2) - EXERCÍCIO OFICIAL DE PEDIDOS GOLD (sql_store)               ");
        System.out.println("==========================================================================================================\n");

        StoreDatabaseService service = new StoreDatabaseService();

        // 1. Clientes fiéis à base do Mosh (sql_store.customers)
        service.addCustomer(new Customer(1, "Babara", 2273));
        service.addCustomer(new Customer(2, "Ines", 947));
        service.addCustomer(new Customer(3, "Freddi", 2967));
        service.addCustomer(new Customer(4, "Ambur", 357));
        service.addCustomer(new Customer(5, "Clemmie", 3675)); // VIP (> 3000)
        service.addCustomer(new Customer(6, "Elka", 3073));     // VIP (> 3000)
        service.addCustomer(new Customer(7, "Ilene", 1672));
        service.addCustomer(new Customer(8, "Freddi", 994));
        service.addCustomer(new Customer(9, "Ruth", 1018));
        service.addCustomer(new Customer(10, "Levy", 796));

        // 2. Pedidos fiéis à base do Mosh (sql_store.orders)
        service.addOrder(new Order(1, 6, LocalDate.of(2019, 1, 30), null));  // Elka (VIP)
        service.addOrder(new Order(2, 7, LocalDate.of(2018, 8, 2), null));
        service.addOrder(new Order(3, 8, LocalDate.of(2017, 12, 1), null));
        service.addOrder(new Order(4, 2, LocalDate.of(2017, 1, 22), null));
        service.addOrder(new Order(5, 5, LocalDate.of(2017, 8, 25), null));  // Clemmie (VIP)
        service.addOrder(new Order(6, 10, LocalDate.of(2018, 11, 18), null));
        service.addOrder(new Order(7, 2, LocalDate.of(2018, 9, 22), null));
        service.addOrder(new Order(8, 5, LocalDate.of(2018, 6, 8), null));   // Clemmie (VIP)
        service.addOrder(new Order(9, 10, LocalDate.of(2017, 7, 5), null));
        service.addOrder(new Order(10, 6, LocalDate.of(2018, 4, 22), null)); // Elka (VIP)

        // --------------------------------------------------------------------------------------
        // PASSO 1: EXECUTANDO A SUBCONSULTA ISOLADAMENTE (REGRA DE OURO DO MOSH)
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 1: TESTANDO A SUBCONSULTA ISOLADAMENTE ---");
        System.out.println("SQL: SELECT customer_id FROM customers WHERE points > 3000;");
        Set<Integer> goldIds = service.findGoldCustomerIds(3000);
        System.out.println("-> Clientes Gold identificados: " + goldIds);
        System.out.println("   Clemmie Barden (ID 5: 3.675 pts) e Elka Twiddell (ID 6: 3.073 pts)\n");

        // --------------------------------------------------------------------------------------
        // PASSO 2: AUDITORIA PRÉVIA DOS PEDIDOS QUE SERÃO ATUALIZADOS
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 2: AUDITORIA PRÉVIA DOS PEDIDOS ELEGÍVEIS ---");
        List<Order> eligibleOrders = service.getOrders().stream()
            .filter(o -> goldIds.contains(o.getCustomerId()))
            .collect(Collectors.toList());

        printOrdersTable(eligibleOrders);
        System.out.println("Total de pedidos que receberão a etiqueta 'Gold customer': " + eligibleOrders.size() + "\n");

        // --------------------------------------------------------------------------------------
        // PASSO 3: EXECUÇÃO DA ATUALIZAÇÃO OFICIAL COM SUBCONSULTA
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 3: EXECUTANDO O UPDATE COM SUBCONSULTA NO BANCO DE DADOS ---");
        System.out.println("SQL: SET SQL_SAFE_UPDATES = 0;");
        System.out.println("SQL: UPDATE orders SET comments = 'Gold customer' WHERE customer_id IN (SELECT customer_id FROM customers WHERE points > 3000);");
        System.out.println("SQL: SET SQL_SAFE_UPDATES = 1;\n");

        int rowsAffected = service.updateOrdersCommentsForGoldCustomers(goldIds, "Gold customer");
        System.out.println("-> Console MySQL:");
        System.out.println("   Query OK, " + rowsAffected + " rows affected (0.01 sec)");
        System.out.println("   Rows matched: " + rowsAffected + "  Changed: " + rowsAffected + "  Warnings: 0\n");

        // --------------------------------------------------------------------------------------
        // PASSO 4: AUDITORIA PÓS-UPDATE DE TODOS OS 10 PEDIDOS (JOIN orders + customers)
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 4: RELATÓRIO CONSOLIDADO DE TODOS OS PEDIDOS (JOIN COM CLIENTES) ---");
        System.out.println("+----------+-------------+----------------------+--------+-----------------+");
        System.out.println("| order_id | customer_id | first_name           | points | comments        |");
        System.out.println("+----------+-------------+----------------------+--------+-----------------+");

        Map<Integer, Customer> customerMap = service.getCustomers().stream()
            .collect(Collectors.toMap(Customer::id, c -> c));

        for (Order o : service.getOrders()) {
            Customer c = customerMap.get(o.getCustomerId());
            System.out.printf("| %8d | %11d | %-20s | %6d | %-15s |%n",
                o.getOrderId(), o.getCustomerId(), c.firstName(), c.points(),
                o.getComments() == null ? "NULL" : o.getComments());
        }
        System.out.println("+----------+-------------+----------------------+--------+-----------------+");

        System.out.println("\nConstatações Finais:");
        System.out.println("1. Os pedidos 1, 5, 8 e 10 foram carimbados como 'Gold customer'.");
        System.out.println("2. Todos os demais pedidos de clientes comuns mantiveram comments = NULL.");
        System.out.println("3. A integridade relacional entre as tabelas 'orders' e 'customers' foi 100% mantida!\n");

        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 37 (PARTE 2) CONCLUÍDA COM 100% DE ÊXITO!                                     ");
        System.out.println("==========================================================================================================");
    }

    private static void printOrdersTable(List<Order> list) {
        System.out.println("+----------+-------------+------------+------------------+");
        System.out.println("| order_id | customer_id | order_date | comments         |");
        System.out.println("+----------+-------------+------------+------------------+");
        for (Order o : list) {
            System.out.println(o);
        }
        System.out.println("+----------+-------------+------------+------------------+");
    }
}
