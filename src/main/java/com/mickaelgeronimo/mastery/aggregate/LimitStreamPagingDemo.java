package com.mickaelgeronimo.mastery.aggregate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LimitStreamPagingDemo
 * 
 * Demonstração prática para iniciantes de como a paginação SQL (LIMIT e OFFSET)
 * funciona exatamente igual aos métodos .skip() e .limit() da Stream API do Java.
 */
public class LimitStreamPagingDemo {

    // Modelo representando o Cliente (Customer)
    static class Customer {
        int id;
        String name;
        int points;

        Customer(int id, String name, int points) {
            this.id = id;
            this.name = name;
            this.points = points;
        }

        @Override
        public String toString() {
            return String.format("| %2d | %-12s | %6d pts |", id, name, points);
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: PAGINAÇÃO COM STREAM API (.skip & .limit)");
        System.out.println("==========================================================\n");

        // 1. Simulação da nossa tabela sql_store.customers
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Mickael", 2273));
        customers.add(new Customer(2, "Lucas", 947));
        customers.add(new Customer(3, "Ana", 1480));
        customers.add(new Customer(4, "Carla", 350));
        customers.add(new Customer(5, "Cleo", 2273)); // Empate com Mickael!
        customers.add(new Customer(6, "Elka", 2073));
        customers.add(new Customer(7, "Ambur", 2845)); // Maior pontuador!
        customers.add(new Customer(8, "Romano", 1892));
        customers.add(new Customer(9, "Levy", 450));
        customers.add(new Customer(10, "Babbara", 596));

        System.out.println("--- 1. Top 3 Clientes Mais Fiéis (SQL: ORDER BY points DESC LIMIT 3) ---");
        List<Customer> top3 = customers.stream()
                .sorted(Comparator.comparingInt((Customer c) -> c.points).reversed())
                .limit(3)
                .collect(Collectors.toList());

        top3.forEach(System.out::println);

        System.out.println("\n--- 2. Paginação na Web: Página 2, tamanho 3 (SQL: LIMIT 3 OFFSET 3) ---");
        int pageSize = 3;
        int pageNumber = 2; // Página 2
        int offset = (pageNumber - 1) * pageSize; // (2 - 1) * 3 = 3

        List<Customer> page2 = customers.stream()
                .sorted(Comparator.comparingInt((Customer c) -> c.points).reversed())
                .skip(offset) // Pula os 3 primeiros registros (OFFSET 3)
                .limit(pageSize) // Pega os 3 seguintes (LIMIT 3)
                .collect(Collectors.toList());

        page2.forEach(System.out::println);

        System.out.println("\n--- 3. Truque do LIMIT 0 (Metadados / Zero Registros) ---");
        List<Customer> limitZero = customers.stream()
                .limit(0)
                .collect(Collectors.toList());
        System.out.println("Tamanho da lista com limit(0): " + limitZero.size() + " elementos.");

        System.out.println("\n==========================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! stream().skip() e .limit() são 1:1 com SQL!");
        System.out.println("==========================================================");
    }
}
