package com.mickaelgeronimo.mastery.joins.inner;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 22 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Sintaxe de Junção Implícita (SQL-89) vs Sintaxe Explícita (SQL-92)
 *       e a Catástrofe do Produto Cartesiano (Cross Join Acidental)
 * ==============================================================================
 */
public class ImplicitJoinVsExplicitDemo {

    // Entidade Pedido (Order)
    static class Order {
        int orderId;
        int customerId;
        String orderDate;

        public Order(int orderId, int customerId, String orderDate) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
        }
    }

    // Entidade Cliente (Customer)
    static class Customer {
        int customerId;
        String firstName;
        String lastName;
        String state;

        public Customer(int customerId, String firstName, String lastName, String state) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.state = state;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: SINTAXE EXPLÍCITA vs IMPLÍCITA E O PERIGO DO PRODUTO CARTESIANO");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS (Idênticos ao banco sql_store do Mosh)
        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara", "MacCaffrey", "MA"),
            new Customer(2, "Ines", "Brushfield", "PA"),
            new Customer(3, "Freddi", "Boagey", "FL"),
            new Customer(4, "Ambur", "Roseburgh", "CA"),
            new Customer(5, "Clemmie", "Betchley", "TX"),
            new Customer(6, "Elka", "Twiddell", "VA"),
            new Customer(7, "Ilene", "Palamis", "IL"),
            new Customer(8, "Levy", "Mynett", "CO"),
            new Customer(9, "Reggy", "Gabarn", "TN"),
            new Customer(10, "Babette", "Larriman", "NY")
        );

        List<Order> orders = Arrays.asList(
            new Order(1, 6, "2019-01-30"),
            new Order(2, 7, "2018-08-02"),
            new Order(3, 8, "2017-12-01"),
            new Order(4, 2, "2017-01-22"),
            new Order(5, 5, "2017-08-25"),
            new Order(6, 10, "2018-11-18"),
            new Order(7, 2, "2018-09-22"),
            new Order(8, 5, "2018-06-08"),
            new Order(9, 10, "2017-07-05"),
            new Order(10, 6, "2018-04-22")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: JUNÇÃO EXPLÍCITA MODERNA (SQL-92: JOIN ... ON)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. SINTAXE EXPLÍCITA (PADRÃO SEGURO: JOIN ... ON) ---");
        System.out.println("+----------+------------+--------------------+-------+");
        System.out.println("| order_id | order_date | customer_name      | state |");
        System.out.println("+----------+------------+--------------------+-------+");

        int explicitMatches = 0;
        for (Order o : orders) {
            for (Customer c : customers) {
                // Cláusula ON: o.customerId == c.customerId
                if (o.customerId == c.customerId) {
                    System.out.printf("| %-8d | %-10s | %-18s | %-5s |\n",
                            o.orderId, o.orderDate, c.firstName + " " + c.lastName, c.state);
                    explicitMatches++;
                }
            }
        }
        System.out.println("+----------+------------+--------------------+-------+");
        System.out.println("Total de linhas retornadas: " + explicitMatches + " (EXATAMENTE os 10 pedidos válidos)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: SINTAXE IMPLÍCITA COM WHERE (SQL-89: FROM orders, customers WHERE ...)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SINTAXE IMPLÍCITA CORRETA (SQL-89: FROM t1, t2 WHERE t1.id = t2.id) ---");
        int implicitMatches = 0;
        for (Order o : orders) {
            for (Customer c : customers) {
                // Cláusula WHERE servindo como ponte de ligação:
                if (o.customerId == c.customerId) {
                    implicitMatches++;
                }
            }
        }
        System.out.println("Resultado da Junção Implícita com WHERE: " + implicitMatches + " linhas.");
        System.out.println("=> Funciona? SIM! Produz o mesmo resultado. Porém é uma bomba-relógio.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A CATÁSTROFE DO ESQUECIMENTO DO 'WHERE' (PRODUTO CARTESIANO / CROSS JOIN)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A CATÁSTROFE: ESQUECER A CLÁUSULA 'WHERE' NA SINTAXE IMPLÍCITA ---");
        System.out.println("SQL: SELECT * FROM orders o, customers c; (SEM WHERE!)");
        System.out.println("Primeiras 6 linhas geradas pelo Produto Cartesiano:");
        System.out.println("+----------+------------------------+---------------------------------------+");
        System.out.println("| order_id | Pedido Pertence Real?  | Cliente Vinculado no Cross Join Falso |");
        System.out.println("+----------+------------------------+---------------------------------------+");

        int cartesianCount = 0;
        for (Order o : orders) {
            for (Customer c : customers) {
                // ATENÇÃO: NENHUM FILTRO OU CONEXÃO AQUI!
                cartesianCount++;
                if (cartesianCount <= 6) {
                    boolean isReal = (o.customerId == c.customerId);
                    String status = isReal ? "SIM (Real Dono)" : "FALSO! (Vínculo Incorreto)";
                    System.out.printf("| %-8d | %-22s | %-37s |\n",
                            o.orderId, status, c.firstName + " " + c.lastName + " (ID " + c.customerId + ")");
                }
            }
        }
        System.out.println("+----------+------------------------+---------------------------------------+");
        System.out.println("... (linhas omitidas) ...");
        System.out.println("🚨 TOTAL DE LINHAS GERADAS NO CONSOLE: " + cartesianCount + " LINHAS!");
        System.out.println("Cálculo Matemático: " + orders.size() + " pedidos x " + customers.size() + " clientes = " + cartesianCount + " linhas!");
        System.out.println("Resultado: 90% das linhas geradas são DADOS CORROMPIDOS E FICTÍCIOS!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: TABELA COMPARATIVA DE ESCALA DO DESASTRE CARTESIANO
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. SIMULAÇÃO DE IMPACTO EM AMBIENTE DE PRODUÇÃO REAL ---");
        long[][] cenarios = {
            {10, 10},
            {1000, 1000},
            {50000, 10000},
            {1000000, 500000}
        };

        System.out.println("+-------------------+---------------------+-------------------------+-------------------------------+");
        System.out.println("| Qtd Pedidos (A)   | Qtd Clientes (B)    | Linhas no Produto (AxB) | Impacto no Servidor / Memória |");
        System.out.println("+-------------------+---------------------+-------------------------+-------------------------------+");
        for (long[] c : cenarios) {
            long total = c[0] * c[1];
            String impacto;
            if (total <= 100) impacto = "Normal (imperceptível)";
            else if (total <= 1_000_000) impacto = "Lentidão perceptível no banco";
            else if (total <= 500_000_000) impacto = "TRAVA O BANCO / 100% de CPU";
            else impacto = "CRASH TOTAL (OutOfMemoryError)";

            System.out.printf("| %,17d | %,19d | %,23d | %-29s |\n", c[0], c[1], total, impacto);
        }
        System.out.println("+-------------------+---------------------+-------------------------+-------------------------------+");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 5: A REDE DE SEGURANÇA SINTÁTICA
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 5. COMPARATIVO DE SEGURANÇA DO DESENVOLVEDOR ---");
        System.out.println("• Sintaxe Explícita: Se você esquecer o 'ON' -> O banco BLOQUEIA a query com erro de sintaxe.");
        System.out.println("• Sintaxe Implícita: Se você esquecer o 'WHERE' -> O banco EXECUTA CALADO e derruba o servidor!");
        System.out.println("=========================================================================================");
        System.out.println("   CONCLUSÃO: NUNCA USE SINTAXE IMPLÍCITA EM PROJETOS NOVOS! USE SEMPRE 'JOIN ... ON'");
        System.out.println("=========================================================================================");
    }
}
