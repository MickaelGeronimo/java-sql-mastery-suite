package com.mickaelgeronimo.mastery.joins.cross;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 28 (PARTE 3 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Grand Finale de Junções Cruzadas — O Triplo Cross Join Multinível (A x B x C),
 *       Gerador de Dados de Teste, Prevenção de OutOfMemoryError e Proteções
 * ==============================================================================
 */
public class CrossJoinFinalMasterDemo {

    record Shipper(int id, String name) {}
    record OrderStatus(int id, String name) {}
    record Product(int id, String name) {}
    record Matrix3D(String shipper, String status, String product) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 28 (PARTE 3 - FINAL) - MASTER CROSS JOINS                 ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS (5 Transportadoras, 3 Status, 10 Produtos)
        List<Shipper> shippers = Arrays.asList(
            new Shipper(4, "Batz-Bauch"),
            new Shipper(1, "Hettinger LLC"),
            new Shipper(5, "Kemp, Breitenberg and Goyette"),
            new Shipper(3, "Sallie Goodrich"),
            new Shipper(2, "Schultz, Tremblay and Hilpert")
        );

        List<OrderStatus> statuses = Arrays.asList(
            new OrderStatus(1, "Processed"),
            new OrderStatus(2, "Shipped"),
            new OrderStatus(3, "Delivered")
        );

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

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O TRIPLO CROSS JOIN MULTINÍVEL (5 x 3 x 10 = 150 LINHAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O TRIPLO CROSS JOIN MULTINÍVEL (5 x 3 x 10 = 150 COMBINAÇÕES) ---");
        System.out.println("+--------------------------------+-----------+-----------------------------+");
        System.out.println("| shipper_name                   | status    | product_name                |");
        System.out.println("+--------------------------------+-----------+-----------------------------+");

        int totalCount = 0;
        for (Shipper sh : shippers) {
            for (OrderStatus st : statuses) {
                for (Product p : products) {
                    if (totalCount < 10 || totalCount >= 145) {
                        System.out.printf("| %-30s | %-9s | %-27s |\n", sh.name(), st.name(), p.name());
                    } else if (totalCount == 10) {
                        System.out.println("| ...                            | ...       | ... (mais 135 combinações)  |");
                    }
                    totalCount++;
                }
            }
        }
        System.out.println("+--------------------------------+-----------+-----------------------------+");
        System.out.println(totalCount + " rows in set (0.00 sec) -> Matriz 3D completa de 150 linhas gerada!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: GERADOR DE MASSA DE DADOS (MOCK DATA GENERATOR)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. GERADOR DE MASSA DE TESTES (MOCK DATA COM PRODUTO CARTESIANO) ---");
        List<Integer> digits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> numbers = digits.stream()
                .flatMap(d1 -> digits.stream().map(d2 -> d1 * 10 + d2))
                .collect(Collectors.toList());

        System.out.println("Números gerados de 00 a 99 (10 x 10 = " + numbers.size() + " números):");
        System.out.println("Primeiros 15 números: " + numbers.subList(0, 15));
        System.out.println("Últimos 10 números:   " + numbers.subList(90, 100) + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: ALERTA DE ARQUITETURA — O PERIGO DO OUT OF MEMORY (OOM)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. ALERTA DE ARQUITETURA: A BOMBA DE MEMÓRIA RAM (OOM KILLER) ---");
        System.out.println("⚠️ No banco de dados e na JVM:");
        System.out.println("   Tabela A (10.000) x Tabela B (10.000) = 100.000.000 (100 MILHÕES de linhas)!");
        System.out.println("💥 Carregar isso em uma List<T> estoura a memória: java.lang.OutOfMemoryError: Java heap space!");
        System.out.println("✅ Solução DBA: SET sql_big_selects = 0 (bloqueia cartesianos perigosos).");
        System.out.println("✅ Solução Java: Avaliação sob demanda (Lazy Streams / Iterator / Paginação).\n");

        System.out.println("Grand Finale do Capítulo 28 (Cross Joins) Concluído com Sucesso Absoluto!");
    }
}
