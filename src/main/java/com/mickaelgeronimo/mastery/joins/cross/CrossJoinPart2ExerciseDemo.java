package com.mickaelgeronimo.mastery.joins.cross;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 28 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Resolução do Exercício Oficial do Mosh Hamedani — Cross Join entre
 *       Transportadoras e Produtos (shippers x products) gerando 50 linhas
 * ==============================================================================
 */
public class CrossJoinPart2ExerciseDemo {

    record Shipper(int id, String name) {}
    record Product(int id, String name, double unitPrice) {}
    record FreightSimulation(String shipperName, String productName, double estimatedFreight) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 28 (PARTE 2) - EXERCÍCIO OFICIAL (SHIPPERS x PRODUCTS)    ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DAS 5 TRANSPORTADORAS (sql_store.shippers)
        List<Shipper> shippers = Arrays.asList(
            new Shipper(1, "Hettinger LLC"),
            new Shipper(2, "Schultz, Tremblay and Hilpert"),
            new Shipper(3, "Sallie Goodrich"),
            new Shipper(4, "Batz-Bauch"),
            new Shipper(5, "Kemp, Breitenberg and Goyette")
        );

        // 2. CARREGAMENTO DOS 10 PRODUTOS (sql_store.products)
        List<Product> products = Arrays.asList(
            new Product(1, "Foam Dinner Plate", 3.35),
            new Product(2, "Pork - Bacon,back Peameal", 3.35),
            new Product(3, "Lettuce - Romaine, Cello", 3.35),
            new Product(4, "Brocolli - Fresh", 3.35),
            new Product(5, "Sauce - Ranch Dressing", 3.35),
            new Product(6, "Petit Baguette", 3.35),
            new Product(7, "Sweet Pea Sprouts", 3.35),
            new Product(8, "Island Oasis - Raspberry", 3.35),
            new Product(9, "Longan", 3.35),
            new Product(10, "Broom - Push", 3.35)
        );

        // Ordenamos transportadoras pelo nome (idêntico ao ORDER BY sh.name do Mosh)
        shippers.sort(Comparator.comparing(Shipper::name));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A RESOLUÇÃO DO EXERCÍCIO OFICIAL (5 x 10 = 50 LINHAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. EXERCÍCIO DO MOSH: shippers CROSS JOIN products (50 LINHAS) ---");
        System.out.println("+--------------------------------+-----------------------------+");
        System.out.println("| shipper                        | product                     |");
        System.out.println("+--------------------------------+-----------------------------+");

        int totalCombos = 0;
        for (Shipper sh : shippers) {
            for (Product p : products) {
                // Exibimos as primeiras 12 linhas e as últimas para clareza didática
                if (totalCombos < 12 || totalCombos >= 48) {
                    System.out.printf("| %-30s | %-27s |\n", sh.name(), p.name());
                } else if (totalCombos == 12) {
                    System.out.println("| ...                            | ... (mais 36 combinações)   |");
                }
                totalCombos++;
            }
        }
        System.out.println("+--------------------------------+-----------------------------+");
        System.out.println(totalCombos + " rows in set (0.00 sec) -> 5 transportadoras x 10 produtos = 50 linhas!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: APLICAÇÃO DE NEGÓCIO REAL: COTAÇÃO DE FRETES (Stream API)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. APLICAÇÃO CORPORATIVA: MATRIZ DE SIMULAÇÃO DE FRETES (JAVA STREAMS) ---");
        System.out.println("+--------------------------------+-----------------------------+-------------------+");
        System.out.println("| Transportadora                 | Produto                     | Frete Estimado    |");
        System.out.println("+--------------------------------+-----------------------------+-------------------+");

        List<FreightSimulation> quotes = shippers.stream()
                .flatMap(sh -> products.stream().map(p -> {
                    double freight = Math.round((p.unitPrice() * 0.15 + 5.00) * 100.0) / 100.0;
                    return new FreightSimulation(sh.name(), p.name(), freight);
                }))
                .collect(Collectors.toList());

        for (int i = 0; i < 5; i++) {
            FreightSimulation fs = quotes.get(i);
            System.out.printf("| %-30s | %-27s | R$ %14.2f |\n", fs.shipperName(), fs.productName(), fs.estimatedFreight());
        }
        System.out.println("| ...                            | ...                         | ...               |");
        System.out.println("+--------------------------------+-----------------------------+-------------------+");
        System.out.println("Matriz de " + quotes.size() + " cotações de frete gerada com sucesso em memória!\n");
    }
}
