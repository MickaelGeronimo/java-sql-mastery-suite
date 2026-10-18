package com.mickaelgeronimo.mastery.unions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ==============================================================================
 * CAPÍTULO 29 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Exercício Oficial do Mosh — Programa de Fidelidade (Bronze, Silver, Gold),
 *       Múltiplos UNIONs, Ordenação Global e Comparação UNION vs UNION ALL
 * ==============================================================================
 */
public class UnionPart2ExerciseDemo {

    // 1. DTO representando a entidade do banco de dados (sql_store.customers)
    public record Customer(int id, String firstName, int points) {}

    // 2. DTO do relatório final projetado pelo SELECT consolidado
    public record CustomerLoyaltyReport(int customerId, String firstName, int points, String type) {
        @Override
        public String toString() {
            return String.format("| %11d | %-12s | %6d | %-8s |", customerId, firstName, points, type);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 29 (PARTE 2) - EXERCÍCIO OFICIAL DE FIDELIDADE (UNIONS)   ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS 10 CLIENTES EXATOS DA BASE DE DADOS DO MOSH (sql_store.customers)
        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara", 2273),
            new Customer(2, "Ines", 947),
            new Customer(3, "Freddi", 2967),
            new Customer(4, "Ambur", 357),
            new Customer(5, "Clemmie", 3675),
            new Customer(6, "Elka", 3073),
            new Customer(7, "Ilene", 1672),
            new Customer(8, "Freddi", 994),
            new Customer(9, "Ruth", 1018),
            new Customer(10, "Levy", 796)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: AS 3 CONSULTAS INDIVIDUAIS (BRONZE, SILVER, GOLD)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. PROCESSAMENTO DAS 3 FAIXAS DE FIDELIDADE VIA STREAM API ---");

        // Subconsulta 1: Clientes Bronze (< 2000 pontos)
        List<CustomerLoyaltyReport> bronzeList = customers.stream()
                .filter(c -> c.points() < 2000)
                .map(c -> new CustomerLoyaltyReport(c.id(), c.firstName(), c.points(), "Bronze"))
                .collect(Collectors.toList());

        // Subconsulta 2: Clientes Silver (BETWEEN 2000 AND 3000 pontos)
        List<CustomerLoyaltyReport> silverList = customers.stream()
                .filter(c -> c.points() >= 2000 && c.points() <= 3000)
                .map(c -> new CustomerLoyaltyReport(c.id(), c.firstName(), c.points(), "Silver"))
                .collect(Collectors.toList());

        // Subconsulta 3: Clientes Gold (> 3000 pontos)
        List<CustomerLoyaltyReport> goldList = customers.stream()
                .filter(c -> c.points() > 3000)
                .map(c -> new CustomerLoyaltyReport(c.id(), c.firstName(), c.points(), "Gold"))
                .collect(Collectors.toList());

        System.out.println("Clientes Bronze encontrados: " + bronzeList.size());
        System.out.println("Clientes Silver encontrados: " + silverList.size());
        System.out.println("Clientes Gold encontrados:   " + goldList.size() + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O RESULTADO FINAL DO EXERCÍCIO OFICIAL DO AUTOR COM ORDER BY GLOBAL
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. RELATÓRIO OFICIAL DO MOSH (3 UNIONS COM ORDER BY first_name) ---");
        System.out.println("+-------------+--------------+--------+----------+");
        System.out.println("| customer_id | first_name   | points | type     |");
        System.out.println("+-------------+--------------+--------+----------+");

        // Concatenação de múltiplos Streams equivalente a:
        // SELECT ... UNION SELECT ... UNION SELECT ... ORDER BY first_name
        List<CustomerLoyaltyReport> fullReport = Stream.of(bronzeList.stream(), silverList.stream(), goldList.stream())
                .flatMap(Function.identity()) // Descompacta os 3 streams em um único fluxo contínuo
                .sorted(Comparator.comparing(CustomerLoyaltyReport::firstName)) // ORDER BY first_name
                .collect(Collectors.toList());

        for (CustomerLoyaltyReport row : fullReport) {
            System.out.println(row);
        }
        System.out.println("+-------------+--------------+--------+----------+");
        System.out.println(fullReport.size() + " rows in set (0.00 sec)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: DIFERENÇA VITAL: UNION (DISTINCT) vs UNION ALL (SEM DEDUPLICAÇÃO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. COMPARAÇÃO ARQUITETURAL: UNION vs UNION ALL ---");
        System.out.println("No SQL:");
        System.out.println("  - UNION: Executa um DISTINCT implícito. O banco precisa alocar espaço temporário");
        System.out.println("           (em RAM ou disco) e ordenar/hashear todas as linhas para eliminar duplicatas.");
        System.out.println("  - UNION ALL: Apenas 'cospe' os dados de uma consulta após a outra sem checar duplicatas.");
        System.out.println("           É ordens de grandeza mais rápido!");
        System.out.println("Em Java:");
        System.out.println("  - UNION     == Stream.concat(...).distinct().toList()  [Custo extra do Set/hash]");
        System.out.println("  - UNION ALL == Stream.concat(...).toList()             [Zero overhead de checagem]\n");
        System.out.println("REGRA DE ENGENHARIA DE DADOS:");
        System.out.println("  Se você SABE que suas consultas retornam dados disjuntos (como faixas <2000, 2000-3000, >3000),");
        System.out.println("  SEMPRE use UNION ALL para obter máxima performance sem desperdício de CPU!");
    }
}
