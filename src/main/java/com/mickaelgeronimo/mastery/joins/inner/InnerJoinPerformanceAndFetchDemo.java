package com.mickaelgeronimo.mastery.joins.inner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InnerJoinPerformanceAndFetchDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. O infame Problema do N+1 (N+1 Query Problem) no Java quando não usamos INNER JOIN.
 * 2. A Solução do Arquiteto: O JOIN FETCH (trazer os dados associados em uma única tacada).
 * 3. Non-Equi Join: Como juntar dados sem chave estrangeira usando faixas (BETWEEN).
 */
public class InnerJoinPerformanceAndFetchDemo {

    // Modelo de Faixa de Fidelidade (sem FK, apenas intervalos de pontos)
    static class LoyaltyTier {
        String tierName;
        int minPoints;
        int maxPoints;

        LoyaltyTier(String tierName, int minPoints, int maxPoints) {
            this.tierName = tierName;
            this.minPoints = minPoints;
            this.maxPoints = maxPoints;
        }
    }

    static class Customer {
        int id;
        String name;
        int points;

        Customer(int id, String name, int points) {
            this.id = id;
            this.name = name;
            this.points = points;
        }
    }

    static class Order {
        int orderId;
        int customerId;
        double total;

        Order(int orderId, int customerId, double total) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.total = total;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: PERFORMANCE DE JOINS E O PROBLEMA DO N+1");
        System.out.println("==========================================================================\n");

        // Simulação do Banco de Dados
        Map<Integer, Customer> customerTable = new HashMap<>();
        customerTable.put(1, new Customer(1, "Mickael Geronimo", 2273));
        customerTable.put(2, new Customer(2, "Lucas Silva", 947));
        customerTable.put(3, new Customer(3, "Ana Pereira", 1480));

        List<Order> orderTable = new ArrayList<>();
        orderTable.add(new Order(101, 1, 350.00));
        orderTable.add(new Order(102, 1, 120.50));
        orderTable.add(new Order(103, 2, 89.90));
        orderTable.add(new Order(104, 3, 450.00));

        System.out.println("--- 1. O PESADELO DO N+1 QUERIES (SEM INNER JOIN) ---");
        System.out.println("Consulta 1: SELECT * FROM orders; (Retorna 4 pedidos)");
        int queriesExecutadas = 1;
        for (Order o : orderTable) {
            System.out.println("  -> Query adicional disparada: SELECT * FROM customers WHERE customer_id = " + o.customerId);
            queriesExecutadas++;
            Customer c = customerTable.get(o.customerId);
            // Uso dos dados...
        }
        System.out.println("💥 Total de queries enviadas ao banco: " + queriesExecutadas);
        System.out.println("Se fossem 10.000 pedidos, seriam 10.001 consultas ao banco! O servidor trava!\n");

        System.out.println("--- 2. A SOLUÇÃO: UMA ÚNICA QUERY COM INNER JOIN (OU JOIN FETCH) ---");
        System.out.println("Query Única: SELECT * FROM orders o JOIN customers c ON o.customer_id = c.customer_id;");
        System.out.println("✨ Total de queries enviadas ao banco: 1 consulta única!");
        System.out.println("+----------+----------------------+--------+");
        System.out.println("| order_id | customer_name        | total  |");
        System.out.println("+----------+----------------------+--------+");
        for (Order o : orderTable) {
            Customer c = customerTable.get(o.customerId);
            System.out.printf("| %8d | %-20s | R$ %6.2f |\n", o.orderId, c.name, o.total);
        }
        System.out.println("+----------+----------------------+--------+\n");

        System.out.println("--- 3. NON-EQUI JOIN (JUNÇÃO SEM IGUALDADE / FAIXAS DE PONTOS) ---");
        List<LoyaltyTier> tiers = new ArrayList<>();
        tiers.add(new LoyaltyTier("Bronze", 0, 999));
        tiers.add(new LoyaltyTier("Silver", 1000, 1999));
        tiers.add(new LoyaltyTier("Gold", 2000, 9999));

        System.out.println("SQL: SELECT c.name, c.points, t.tierName FROM customers c JOIN tiers t ON c.points BETWEEN t.min AND t.max;");
        System.out.println("+----------------------+--------+----------+");
        System.out.println("| customer_name        | points | tier     |");
        System.out.println("+----------------------+--------+----------+");
        for (Customer c : customerTable.values()) {
            for (LoyaltyTier t : tiers) {
                if (c.points >= t.minPoints && c.points <= t.maxPoints) {
                    System.out.printf("| %-20s | %6d | %-8s |\n", c.name, c.points, t.tierName);
                    break;
                }
            }
        }
        System.out.println("+----------------------+--------+----------+\n");

        System.out.println("==========================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! O INNER JOIN RESOLVE PERFORMANCE E MODELAGEM!");
        System.out.println("==========================================================================");
    }
}
