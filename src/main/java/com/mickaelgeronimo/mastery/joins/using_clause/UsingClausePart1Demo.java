package com.mickaelgeronimo.mastery.joins.using_clause;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 26 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: A Cláusula USING (The USING Clause) — Eliminação de Redundância,
 *       Sintaxe Limpa e Junções Múltiplas com Chaves Compartilhadas
 * ==============================================================================
 */
public class UsingClausePart1Demo {

    // Registros representando as tabelas do banco sql_store
    record Customer(int customerId, String firstName) {}
    record Shipper(int shipperId, String name) {}
    record Order(int orderId, int customerId, Integer shipperId) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 26 (PARTE 1) - A CLÁUSULA USING (THE USING CLAUSE)        ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS CLIENTES (sql_store.customers)
        Map<Integer, Customer> customers = Map.of(
            1, new Customer(1, "Babara"),
            2, new Customer(2, "Ines"),
            3, new Customer(3, "Freddi"),
            5, new Customer(5, "Clemmie"),
            6, new Customer(6, "Elka"),
            7, new Customer(7, "Ilene"),
            8, new Customer(8, "Freddi"),
            10, new Customer(10, "Levy")
        );

        // 2. CARREGAMENTO DAS TRANSPORTADORAS (sql_store.shippers)
        Map<Integer, Shipper> shippers = Map.of(
            1, new Shipper(1, "Hettinger LLC"),
            2, new Shipper(2, "Schultz, Tremblay and Hilpert"),
            3, new Shipper(3, "Sallie Goodrich"),
            4, new Shipper(4, "Batz-Bauch"),
            5, new Shipper(5, "Kemp, Breitenberg and Goyette")
        );

        // 3. CARREGAMENTO DOS PEDIDOS (sql_store.orders)
        List<Order> orders = Arrays.asList(
            new Order(1, 6, 1),
            new Order(2, 7, 4),
            new Order(3, 8, null), // Sem transportadora (ainda não enviado)
            new Order(4, 10, null),
            new Order(5, 2, 3),
            new Order(6, 1, 2),
            new Order(7, 5, null),
            new Order(8, 8, 2),
            new Order(9, 3, 1),
            new Order(10, 6, 2)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: EQUIVALÊNCIA SINTÁTICA: 'ON o.customer_id = c.customer_id' vs 'USING (customer_id)'
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CONSULTA BÁSICA COM 'USING (customer_id)' ---");
        System.out.println("✨ Quando a coluna tem exatamente o mesmo nome, o USING substitui o 'ON':");
        System.out.println("+----------+------------+");
        System.out.println("| order_id | first_name |");
        System.out.println("+----------+------------+");

        for (Order o : orders) {
            Customer c = customers.get(o.customerId());
            if (c != null) {
                System.out.printf("| %8d | %-10s |\n", o.orderId(), c.firstName());
            }
        }
        System.out.println("+----------+------------+");
        System.out.println("10 rows in set (0.00 sec) -> Resultado idêntico ao 'ON', porém mais conciso!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: MÚLTIPLAS JUNÇÕES COM 'USING' E 'LEFT JOIN'
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. MÚLTIPLAS JUNÇÕES COM 'USING' E 'LEFT JOIN' (orders + customers + shippers) ---");
        System.out.println("+----------+------------+--------------------------------+");
        System.out.println("| order_id | first_name | shipper                        |");
        System.out.println("+----------+------------+--------------------------------+");

        for (Order o : orders) {
            Customer c = customers.get(o.customerId());
            String shipperName = (o.shipperId() != null && shippers.containsKey(o.shipperId()))
                    ? shippers.get(o.shipperId()).name()
                    : "NULL";

            System.out.printf("| %8d | %-10s | %-30s |\n",
                    o.orderId(),
                    (c != null ? c.firstName() : "NULL"),
                    shipperName);
        }
        System.out.println("+----------+------------+--------------------------------+");
        System.out.println("10 rows in set (0.00 sec) -> Pedidos 3, 4 e 7 preservados com shipper = NULL!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: POR QUE O 'USING' NÃO FUNCIONA COM NOMES DIFERENTES?
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A REGRA DE OURO DOS NOMES DIFERENTES ---");
        System.out.println("No banco sql_hr:");
        System.out.println("  tabela employees e: campo 'reports_to'");
        System.out.println("  tabela employees m: campo 'employee_id'");
        System.out.println("💥 Tentar 'USING (reports_to)' dá erro: a tabela 'm' não tem a coluna 'reports_to'!");
        System.out.println("✅ Portanto, quando os nomes são diferentes, o 'ON' continua sendo 100% OBRIGATÓRIO!\n");
    }
}
