package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 24 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junção Externa Entre Múltiplas Tabelas (Outer Join Between Multiple Tables),
 *       A Quebra de Registros por INNER JOIN Indevido e os Dois Tipos de Nulo
 * ==============================================================================
 */
public class MultiTableOuterJoinJavaDemo {

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
        Integer shipperId; // Pode ser NULL se ainda não foi despachado!

        public Order(int orderId, int customerId, Integer shipperId) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.shipperId = shipperId;
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

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO EXTERNA ENTRE MÚLTIPLAS TABELAS (CAPÍTULO 24 - PARTE 1)");
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

        List<Order> orders = Arrays.asList(
            new Order(1, 6, 1),
            new Order(2, 7, 4),
            new Order(3, 8, null), // Sem transportadora
            new Order(4, 2, null), // Sem transportadora
            new Order(5, 5, 5),
            new Order(6, 10, null), // Sem transportadora
            new Order(7, 2, null), // Sem transportadora
            new Order(8, 5, 2),
            new Order(9, 10, null), // Sem transportadora
            new Order(10, 6, 1)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O ERRO CRÍTICO (LEFT JOIN orders + INNER JOIN shippers)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O ERRO FATAL: COLOCAR INNER JOIN NA TERCEIRA TABELA ---");
        System.out.println("SQL: customers c LEFT JOIN orders o ON ... JOIN shippers sh ON ...");
        System.out.println("+-------------+----------------------+----------+----------------------+");
        System.out.println("| customer_id | first_name           | order_id | shipper              |");
        System.out.println("+-------------+----------------------+----------+----------------------+");

        int brokenCount = 0;
        for (Customer c : customers) {
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    // INNER JOIN com shippers exige shipperId != null obrigatoriamente!
                    if (o.shipperId != null && shippers.containsKey(o.shipperId)) {
                        System.out.printf("| %-11d | %-20s | %-8d | %-20s |\n",
                                c.id, c.name, o.orderId, shippers.get(o.shipperId).name);
                        brokenCount++;
                    }
                }
            }
        }
        System.out.println("+-------------+----------------------+----------+----------------------+");
        System.out.println("🚨 TOTAL DE LINHAS RETORNADAS: " + brokenCount + " linhas!");
        System.out.println("Desastre: Onde foram parar os clientes inativos e os pedidos não despachados?");
        System.out.println("Foram todos DELETADOS do resultado pelo INNER JOIN indevido!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A SOLUÇÃO CORRETA (CASCADING LEFT JOIN COMPLETO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A SOLUÇÃO PROFISSIONAL: CASCADING LEFT JOIN COMPLETO ---");
        System.out.println("SQL: customers c LEFT JOIN orders o ON ... LEFT JOIN shippers sh ON ...");
        System.out.println("+-------------+----------------------+----------+--------------------------------+");
        System.out.println("| customer_id | first_name           | order_id | shipper                        |");
        System.out.println("+-------------+----------------------+----------+--------------------------------+");

        int fullCount = 0;
        for (Customer c : customers) {
            boolean hasOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    hasOrder = true;
                    String shipperName = (o.shipperId != null && shippers.containsKey(o.shipperId))
                            ? shippers.get(o.shipperId).name
                            : "NULL (Aguardando Despacho)";

                    System.out.printf("| %-11d | %-20s | %-8d | %-30s |\n",
                            c.id, c.name, o.orderId, shipperName);
                    fullCount++;
                }
            }
            if (!hasOrder) {
                // Cliente inativo preservado!
                System.out.printf("| %-11d | %-20s | %-8s | %-30s |\n",
                        c.id, c.name, "NULL", "NULL (Sem compras)");
                fullCount++;
            }
        }
        System.out.println("+-------------+----------------------+----------+--------------------------------+");
        System.out.println("✨ TOTAL DE LINHAS RETORNADAS: " + fullCount + " linhas (EXATAMENTE AS 14 LINHAS VÁLIDAS!).\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A ANATOMIA DOS DOIS TIPOS DE NULO
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A ANATOMIA DOS DOIS TIPOS DE 'NULL' NO RELATÓRIO ---");
        System.out.println("• Tipo 1 (Cliente Inativo): order_id é NULL E shipper é NULL. (Ex: Babara MacCaffrey, Freddi Boagey)");
        System.out.println("  Significado: O cliente nunca fez nenhuma compra no site.");
        System.out.println("• Tipo 2 (Pedido Pendente): order_id EXISTE (ex: Pedido 4), mas shipper é NULL.");
        System.out.println("  Significado: A compra foi feita e paga, mas ainda está no armazém aguardando coleta da transportadora.");

        System.out.println("\n=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO: NUNCA USE INNER JOIN DEPOIS DE UM LEFT JOIN NA MESMA CADEIA!");
        System.out.println("=========================================================================================");
    }
}
