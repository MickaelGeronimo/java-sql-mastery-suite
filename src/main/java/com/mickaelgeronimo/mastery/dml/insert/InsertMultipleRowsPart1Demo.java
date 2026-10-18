package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 32 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Inserindo Múltiplas Linhas (INSERT INTO ... VALUES (), (), ()) —
 *       Inserção em Lote (Batch Insert), O Exercício Oficial e Performance
 * ==============================================================================
 */
public class InsertMultipleRowsPart1Demo {

    // Geradores de Auto-Incremento
    private static final AtomicInteger SHIPPER_ID_SEQ = new AtomicInteger(5);   // Próximos: 6, 7, 8
    private static final AtomicInteger PRODUCT_ID_SEQ = new AtomicInteger(10);  // Próximos: 11, 12, 13

    // Modelos de Domínio
    public record Shipper(int id, String name) {
        @Override
        public String toString() {
            return String.format("| %10d | %-32s |", id, name);
        }
    }

    public record Product(int id, String name, int quantityInStock, BigDecimal unitPrice) {
        @Override
        public String toString() {
            return String.format("| %10d | %-16s | %17d | %10s |", id, name, quantityInStock, unitPrice);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 32 (PARTE 1) - INSERINDO MÚLTIPLAS LINHAS (BATCH INSERT)   ");
        System.out.println("=========================================================================================\n");

        // Tabelas em memória
        List<Shipper> shippersTable = new ArrayList<>(Arrays.asList(
            new Shipper(1, "Hettinger LLC"),
            new Shipper(2, "Schultz, Tremblay and Hilpert"),
            new Shipper(3, "Sallie Goodrich"),
            new Shipper(4, "Batz-Bauch"),
            new Shipper(5, "Kemp, Breitenberg and Goyette")
        ));

        List<Product> productsTable = new ArrayList<>(Arrays.asList(
            new Product(1, "Foam Dinner Plate", 70, new BigDecimal("1.21")),
            new Product(2, "Pork - Bacon", 49, new BigDecimal("4.65")),
            new Product(3, "Lettuce - Romaine", 38, new BigDecimal("3.35")),
            new Product(4, "Brocolli - Fresh", 90, new BigDecimal("4.53")),
            new Product(5, "Sauce - Ranch", 94, new BigDecimal("1.63")),
            new Product(6, "Petit Baguette", 14, new BigDecimal("2.39")),
            new Product(7, "Sweet Pea Sprouts", 98, new BigDecimal("3.29")),
            new Product(8, "Island Oasis", 26, new BigDecimal("0.74")),
            new Product(9, "Longan", 67, new BigDecimal("2.26")),
            new Product(10, "Broom - Push", 6, new BigDecimal("1.09"))
        ));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: INSERÇÃO EM LOTE DE 3 TRANSPORTADORAS (shippers)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. INSERÇÃO EM LOTE DE TRANSPORTADORAS (shippers) ---");
        System.out.println("SQL: INSERT INTO shippers (name) VALUES ('Shipper1'), ('Shipper2'), ('Shipper3');");

        List<String> newShipperNames = List.of("Shipper1", "Shipper2", "Shipper3");
        for (String name : newShipperNames) {
            shippersTable.add(new Shipper(SHIPPER_ID_SEQ.incrementAndGet(), name));
        }

        System.out.println("-> MySQL Console: Query OK, 3 rows affected (0.00 sec)");
        System.out.println("->                Records: 3  Duplicates: 0  Warnings: 0\n");

        System.out.println("+------------+----------------------------------+");
        System.out.println("| shipper_id | name                             |");
        System.out.println("+------------+----------------------------------+");
        shippersTable.forEach(System.out::println);
        System.out.println("+------------+----------------------------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O EXERCÍCIO OFICIAL DO MOSH (INSERIR 3 PRODUTOS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. O EXERCÍCIO OFICIAL DO MOSH: INSERIR 3 PRODUTOS EM BATCH ---");
        System.out.println("SQL: INSERT INTO products (name, quantity_in_stock, unit_price)");
        System.out.println("     VALUES ('Product1', 10, 1.95), ('Product2', 20, 2.95), ('Product3', 30, 3.95);");

        productsTable.add(new Product(PRODUCT_ID_SEQ.incrementAndGet(), "Product1", 10, new BigDecimal("1.95")));
        productsTable.add(new Product(PRODUCT_ID_SEQ.incrementAndGet(), "Product2", 20, new BigDecimal("2.95")));
        productsTable.add(new Product(PRODUCT_ID_SEQ.incrementAndGet(), "Product3", 30, new BigDecimal("3.95")));

        System.out.println("-> MySQL Console: Query OK, 3 rows affected (0.01 sec)");
        System.out.println("->                Records: 3  Duplicates: 0  Warnings: 0\n");

        System.out.println("+------------+------------------+-------------------+------------+");
        System.out.println("| product_id | name             | quantity_in_stock | unit_price |");
        System.out.println("+------------+------------------+-------------------+------------+");
        productsTable.stream().filter(p -> p.id() >= 11).forEach(System.out::println);
        System.out.println("+------------+------------------+-------------------+------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: POR QUE INSERÇÃO EM LOTE É TÃO MAIS RÁPIDA?
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. ARQUITETURA DE PERFORMANCE: BATCH vs SINGLE INSERT ---");
        System.out.println("No Single Insert (Loop de 1000 INSERTs individuais):");
        System.out.println("  - 1000 viagens de rede (Roundtrips) ida e volta.");
        System.out.println("  - 1000 transações individuais confirmadas com gravação em disco (fsync).");
        System.out.println("  - Tempo estimado: ~15 a 30 segundos!");
        System.out.println("No Batch Insert (1 único comando com 1000 tuplas):");
        System.out.println("  - 1 ÚNICA viagem de rede.");
        System.out.println("  - 1 ÚNICA confirmação de transação atômica no disco.");
        System.out.println("  - Tempo estimado: ~0.08 segundos (mais de 200x mais rápido!).\n");

        System.out.println("Demostração concluída com 100% de sucesso!");
    }
}
