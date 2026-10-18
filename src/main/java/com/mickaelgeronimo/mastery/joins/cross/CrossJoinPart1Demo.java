package com.mickaelgeronimo.mastery.joins.cross;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 28 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junções Cruzadas (Cross Joins) — O Produto Cartesiano Combinatório (N x M),
 *       Laços Aninhados vs Stream flatMap() e a Matriz de 100 Combinações
 * ==============================================================================
 */
public class CrossJoinPart1Demo {

    record Customer(int id, String firstName) {}
    record Product(int id, String name) {}
    record CustomerProductCombo(String customerName, String productName) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 28 (PARTE 1) - JUNÇÕES CRUZADAS (CROSS JOINS)             ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS 10 CLIENTES (sql_store.customers)
        List<Customer> customers = Arrays.asList(
            new Customer(4, "Ambur"),
            new Customer(1, "Babara"),
            new Customer(5, "Clemmie"),
            new Customer(6, "Elka"),
            new Customer(3, "Freddi"),
            new Customer(8, "Freddi"),
            new Customer(7, "Ilene"),
            new Customer(2, "Ines"),
            new Customer(10, "Levy"),
            new Customer(9, "Ruth")
        );

        // 2. CARREGAMENTO DOS 10 PRODUTOS (sql_store.products)
        List<Product> products = Arrays.asList(
            new Product(1, "Foam Dinner Plate"),
            new Product(2, "Pork - Bacon,back Peameal"),
            new Product(3, "Lettuce - Romaine, Cello"),
            new Product(4, "Brocolli - Fresh"),
            new Product(5, "Sauce - Ranch Dressing"),
            new Product(6, "Petit Baguette"),
            new Product(7, "Sweet Pea Sprouts"),
            new Product(8, "Island Oasis - Raspberry"),
            new Product(9, "Longan"),
            new Product(10, "Broom - Push")
        );

        // Ordenamos clientes por nome (idêntico ao ORDER BY c.first_name do Mosh)
        customers.sort(Comparator.comparing(Customer::firstName));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O PRODUTO CARTESIANO GERADO EM JAVA (10 x 10 = 100 COMBINAÇÕES)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. PRODUTO CARTESIANO (CROSS JOIN): 10 CLIENTES x 10 PRODUTOS = 100 LINHAS ---");
        System.out.println("+------------+-----------------------------+");
        System.out.println("| customer   | product                     |");
        System.out.println("+------------+-----------------------------+");

        int count = 0;
        for (Customer c : customers) {
            for (Product p : products) {
                // Exibimos as primeiras 12 combinações e as últimas para visualização didática
                if (count < 12 || count >= 98) {
                    System.out.printf("| %-10s | %-27s |\n", c.firstName(), p.name());
                } else if (count == 12) {
                    System.out.println("| ...        | ... (mais 86 combinações)   |");
                }
                count++;
            }
        }
        System.out.println("+------------+-----------------------------+");
        System.out.println(count + " rows in set (0.00 sec) -> Exatamente 100 combinações geradas!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: COMO O JAVA MODERNO GERA O CROSS JOIN COM STREAM API (flatMap)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. CROSS JOIN EM JAVA MODERNO COM Stream.flatMap() ---");
        List<CustomerProductCombo> combos = customers.stream()
                .flatMap(c -> products.stream().map(p -> new CustomerProductCombo(c.firstName(), p.name())))
                .collect(Collectors.toList());

        System.out.println("Total de pares gerados via flatMap(): " + combos.size());
        System.out.println("Exemplo do primeiro par: " + combos.get(0));
        System.out.println("Exemplo do centésimo par: " + combos.get(99) + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: EXPLÍCITO vs IMPLÍCITO (A REGRA DE ENGENHARIA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. SINTAXE EXPLÍCITA vs IMPLÍCITA ---");
        System.out.println("✅ Explícita: FROM customers c CROSS JOIN products p");
        System.out.println("⚠️ Implícita: FROM customers c, products p");
        System.out.println("Lição do Mosh: Use sempre a forma EXPLÍCITA para que ninguém na sua equipe");
        System.out.println("duvide se você esqueceu o WHERE por acidente ou se queria mesmo um CROSS JOIN!\n");
    }
}
