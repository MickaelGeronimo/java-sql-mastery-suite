package com.mickaelgeronimo.mastery.unions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ==============================================================================
 * CAPÍTULO 29 (PARTE 5 FINAL): DEMONSTRAÇÃO PRÁTICA SUPREMA EM JAVA 17
 * Tema: Consolidação Multi-Bancos de Stakeholders (Store, Invoicing, HR),
 *       Arquitetura de Agregação de Feeds e Formatura Geral da Seção 3
 * ==============================================================================
 */
public class UnionPart5FinalMasterDemo {

    // DTO Unificado do Feed Corporativo de Stakeholders
    public record Stakeholder(int id, String name, String role, String originDb) {
        @Override
        public String toString() {
            return String.format("| %9d | %-32s | %-18s | %-13s |", id, name, role, originDb);
        }
    }

    public record VolumeReport(String originDb, String role, long count) {
        @Override
        public String toString() {
            return String.format("| %-15s | %-20s | %14d |", originDb, role, count);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 29 (PARTE 5 FINAL) - CONSOLIDAÇÃO SUPREMA MULTI-BANCOS    ");
        System.out.println("=========================================================================================\n");

        // 1. DADOS ORIGINÁRIOS DE 4 TABELAS ESPALHADAS EM 3 BANCOS DE DADOS
        List<Stakeholder> storeCustomers = Arrays.asList(
            new Stakeholder(1, "Babara MacCaffrey", "Store Customer", "sql_store"),
            new Stakeholder(2, "Ines Brushfield", "Store Customer", "sql_store"),
            new Stakeholder(3, "Freddi Boagey", "Store Customer", "sql_store"),
            new Stakeholder(4, "Ambur Roseburgh", "Store Customer", "sql_store"),
            new Stakeholder(5, "Clemmie Betchley", "Store Customer", "sql_store"),
            new Stakeholder(6, "Elka Twiddell", "Store Customer", "sql_store"),
            new Stakeholder(7, "Ilene Dowson", "Store Customer", "sql_store"),
            new Stakeholder(8, "Freddi Carnduff", "Store Customer", "sql_store"),
            new Stakeholder(9, "Ruth Cornfoot", "Store Customer", "sql_store"),
            new Stakeholder(10, "Levy Mynett", "Store Customer", "sql_store")
        );

        List<Stakeholder> invoicingClients = Arrays.asList(
            new Stakeholder(1, "Vinte", "Invoicing Client", "sql_invoicing"),
            new Stakeholder(2, "Myworks", "Invoicing Client", "sql_invoicing"),
            new Stakeholder(3, "Yadel", "Invoicing Client", "sql_invoicing"),
            new Stakeholder(4, "Kwambam", "Invoicing Client", "sql_invoicing"),
            new Stakeholder(5, "Topiclounge", "Invoicing Client", "sql_invoicing")
        );

        List<Stakeholder> hrEmployees = Arrays.asList(
            new Stakeholder(37270, "Yovonnda Magrannell", "Executive Vice Pres", "sql_hr"),
            new Stakeholder(33391, "Darcy Hercules", "Marketing Manager", "sql_hr"),
            new Stakeholder(37851, "Sayer Weiss", "Account Representative", "sql_hr"),
            new Stakeholder(40448, "Mindy Boffee", "VP Sales", "sql_hr"),
            new Stakeholder(56274, "Keriann Kelly", "VP Marketing", "sql_hr"),
            new Stakeholder(63196, "Alaster Gatrill", "Assistant Professor", "sql_hr"),
            new Stakeholder(67370, "Elladine Pridham", "General Manager", "sql_hr"),
            new Stakeholder(68249, "Nanci Sneath", "Account Representative", "sql_hr"),
            new Stakeholder(72540, "Yuri van der Kolk", "Account Representative", "sql_hr"),
            new Stakeholder(80529, "Salome Sowden", "Sales Representative", "sql_hr")
        );

        List<Stakeholder> storeShippers = Arrays.asList(
            new Stakeholder(1, "Hettinger LLC", "Logistics Shipper", "sql_store"),
            new Stakeholder(2, "Schultz, Tremblay", "Logistics Shipper", "sql_store"),
            new Stakeholder(3, "Sallie Goodrich", "Logistics Shipper", "sql_store"),
            new Stakeholder(4, "Batz-Bauch", "Logistics Shipper", "sql_store"),
            new Stakeholder(5, "Kemp, Breitenberg", "Logistics Shipper", "sql_store")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O UNION ALL MULTI-BANCOS EXECUTADO VIA STREAM API (30 REGISTROS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. RELATÓRIO FEDERADO DE STAKEHOLDERS (4 TABELAS EM 3 BANCOS) ---");
        System.out.println("+-----------+----------------------------------+--------------------+---------------+");
        System.out.println("| entity_id | entity_name                      | role               | origin_db     |");
        System.out.println("+-----------+----------------------------------+--------------------+---------------+");

        List<Stakeholder> consolidatedFeed = Stream.of(
                    hrEmployees.stream(),
                    invoicingClients.stream(),
                    storeCustomers.stream(),
                    storeShippers.stream()
                )
                .flatMap(Function.identity())
                .sorted(Comparator.comparing(Stakeholder::originDb).thenComparing(Stakeholder::name))
                .collect(Collectors.toList());

        for (Stakeholder s : consolidatedFeed) {
            System.out.println(s);
        }
        System.out.println("+-----------+----------------------------------+--------------------+---------------+");
        System.out.println(consolidatedFeed.size() + " total entities consolidated seamlessly!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: SIMULAÇÃO DA CTE COM GROUP BY (RESUMO POR BANCO E PAPEL)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. RESUMO EXECUTIVO DE VOLUMETRIA (SIMULAÇÃO DA CTE COM GROUP BY) ---");
        System.out.println("+-----------------+----------------------+----------------+");
        System.out.println("| origin_database | role                 | total_entities |");
        System.out.println("+-----------------+----------------------+----------------+");

        Map<String, Long> volumeMap = consolidatedFeed.stream()
                .collect(Collectors.groupingBy(
                        s -> s.originDb() + ";" + s.role(),
                        TreeMap::new,
                        Collectors.counting()
                ));

        volumeMap.forEach((key, count) -> {
            String[] parts = key.split(";");
            System.out.printf("| %-15s | %-20s | %14d |\n", parts[0], parts[1], count);
        });
        System.out.println("+-----------------+----------------------+----------------+");
        System.out.println("\nPARABÉNS! SEÇÃO 3 (CONSULTAS MULTI-TABELAS) CONCLUÍDA COM SUCESSO TOTAL!");
    }
}
