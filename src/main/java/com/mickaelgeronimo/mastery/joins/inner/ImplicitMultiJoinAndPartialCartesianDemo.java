package com.mickaelgeronimo.mastery.joins.inner;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 22 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junção Implícita com Múltiplas Tabelas, O Perigo do Produto Cartesiano
 *       Parcial e Auto-Junção Implícita (Self Join)
 * ==============================================================================
 */
public class ImplicitMultiJoinAndPartialCartesianDemo {

    // Entidade Pedido
    static class Order {
        int orderId;
        int customerId;
        String orderDate;
        int statusId;

        public Order(int orderId, int customerId, String orderDate, int statusId) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
            this.statusId = statusId;
        }
    }

    // Entidade Cliente
    static class Customer {
        int customerId;
        String name;

        public Customer(int customerId, String name) {
            this.customerId = customerId;
            this.name = name;
        }
    }

    // Entidade Status do Pedido (Tabela de Domínio / Lookup)
    static class OrderStatus {
        int statusId;
        String statusName;

        public OrderStatus(int statusId, String statusName) {
            this.statusId = statusId;
            this.statusName = statusName;
        }
    }

    // Entidade Funcionário para Self Join
    static class Employee {
        int id;
        String name;
        Integer reportsTo; // null para o CEO

        public Employee(int id, String name, Integer reportsTo) {
            this.id = id;
            this.name = name;
            this.reportsTo = reportsTo;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO IMPLÍCITA AVANÇADA, PRODUTO CARTESIANO PARCIAL E SELF JOIN");
        System.out.println("=========================================================================================\n");

        // DADOS OFICIAIS DO BANCO sql_store
        List<OrderStatus> statuses = Arrays.asList(
            new OrderStatus(1, "Processed"),
            new OrderStatus(2, "Shipped"),
            new OrderStatus(3, "Delivered")
        );

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

        List<Order> orders = Arrays.asList(
            new Order(1, 6, "2019-01-30", 1),
            new Order(2, 7, "2018-08-02", 1),
            new Order(3, 8, "2017-12-01", 1),
            new Order(4, 2, "2017-01-22", 1),
            new Order(5, 5, "2017-08-25", 2),
            new Order(6, 10, "2018-11-18", 1),
            new Order(7, 2, "2018-09-22", 2),
            new Order(8, 5, "2018-06-08", 1),
            new Order(9, 10, "2017-07-05", 1),
            new Order(10, 6, "2018-04-22", 2)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: JUNÇÃO DE 3 TABELAS CORRETA (orders + customers + order_statuses)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. JUNÇÃO IMPLÍCITA CORRETA DE 3 TABELAS (2 CONDIÇÕES NO WHERE) ---");
        System.out.println("+----------+------------+--------------------+--------------------+");
        System.out.println("| order_id | order_date | customer_name      | status_name        |");
        System.out.println("+----------+------------+--------------------+--------------------+");

        int validCount = 0;
        for (Order o : orders) {
            for (Customer c : customers) {
                for (OrderStatus os : statuses) {
                    // Duas condições de amarração simultâneas (o.customerId == c.customerId AND o.statusId == os.statusId):
                    if (o.customerId == c.customerId && o.statusId == os.statusId) {
                        System.out.printf("| %-8d | %-10s | %-18s | %-18s |\n",
                                o.orderId, o.orderDate, c.name, os.statusName);
                        validCount++;
                    }
                }
            }
        }
        System.out.println("+----------+------------+--------------------+--------------------+");
        System.out.println("Total de registros corretos: " + validCount + " (Exatamente 10 pedidos!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A CATÁSTROFE DA JUNÇÃO CARTESIANA PARCIAL
        // O desenvolvedor ligou orders e customers, mas ESQUECEU a amarração de order_statuses!
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A CATÁSTROFE DO PRODUTO CARTESIANO PARCIAL (ESQUECENDO 1 TABELA) ---");
        System.out.println("SQL: FROM orders o, customers c, order_statuses os WHERE o.customer_id = c.customer_id");
        System.out.println("Primeiras 6 linhas do desastre:");
        System.out.println("+----------+--------------------+--------------------+---------------------------------------+");
        System.out.println("| order_id | customer_name      | status_atribuído   | Diagnóstico de Negócio                |");
        System.out.println("+----------+--------------------+--------------------+---------------------------------------+");

        int partialCartesianCount = 0;
        for (Order o : orders) {
            for (Customer c : customers) {
                if (o.customerId == c.customerId) {
                    // O programador ESQUECEU de checar o statusId!
                    for (OrderStatus os : statuses) {
                        partialCartesianCount++;
                        if (partialCartesianCount <= 6) {
                            boolean isRealStatus = (o.statusId == os.statusId);
                            String diag = isRealStatus ? "CORRETO (Status Real)" : "ALERTA: FALSO! Status Fantasma";
                            System.out.printf("| %-8d | %-18s | %-18s | %-37s |\n",
                                    o.orderId, c.name, os.statusName, diag);
                        }
                    }
                }
            }
        }
        System.out.println("+----------+--------------------+--------------------+---------------------------------------+");
        System.out.println("... (linhas omitidas) ...");
        System.out.println("🚨 TOTAL DE LINHAS GERADAS: " + partialCartesianCount + " LINHAS (10 pedidos x 3 status = 30 linhas)!");
        System.out.println("Desastre: O Pedido 1 aparece no relatório como 'Processed', 'Shipped' e 'Delivered' simultaneamente!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: AUTO-JUNÇÃO IMPLÍCITA (SELF JOIN: sql_hr.employees)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. AUTO-JUNÇÃO IMPLÍCITA (SELF JOIN: FUNCIONÁRIO E SEU GERENTE) ---");
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", null),   // CEO
            new Employee(33391, "Darcy", 37270),
            new Employee(37851, "Sayer", 37270),
            new Employee(40448, "Minna", 33391),
            new Employee(56274, "Keriann", 37270)
        );

        System.out.println("+-------------+----------------------+----------------------+");
        System.out.println("| employee_id | employee_name        | manager_name         |");
        System.out.println("+-------------+----------------------+----------------------+");

        int employeeMatches = 0;
        for (Employee e : employees) {
            for (Employee m : employees) {
                // Sintaxe implícita: WHERE e.reports_to = m.employee_id
                // Note que o CEO tem reportsTo == null, portanto nunca dá match!
                if (e.reportsTo != null && e.reportsTo.equals(m.id)) {
                    System.out.printf("| %-11d | %-20s | %-20s |\n",
                            e.id, e.name, m.name);
                    employeeMatches++;
                }
            }
        }
        System.out.println("+-------------+----------------------+----------------------+");
        System.out.println("Total de subordinados encontrados: " + employeeMatches);
        System.out.println("Nota Pedagógica: O CEO (Yovonnda) sumiu da lista porque reports_to é NULL!");
        System.out.println("Na sintaxe implícita é IMPOSSÍVEL fazer LEFT JOIN de forma padronizada!\n");

        System.out.println("=========================================================================================");
        System.out.println("   FIM DA DEMONSTRAÇÃO: SINTAXE EXPLÍCITA EVITA PRODUTOS CARTESIANOS PARCIAIS!");
        System.out.println("=========================================================================================");
    }
}
