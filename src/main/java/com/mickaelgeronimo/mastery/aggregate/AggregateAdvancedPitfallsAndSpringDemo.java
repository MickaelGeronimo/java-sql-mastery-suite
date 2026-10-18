package com.mickaelgeronimo.mastery.aggregate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 40 (PARTE 4 FINAL SUPREMO): ERROS DE MOTOR, SUBQUERIES E SPRING BOOT
 * Demonstração Didática em Java 17 LTS para Iniciantes
 * ==============================================================================
 * 
 * Este programa demonstra:
 * 1. Por que não se pode misturar colunas individuais com agregações sem GROUP BY.
 * 2. Como filtrar registros acima da média (simulando a Subquery com AVG no WHERE).
 * 3. Uso defensivo de COALESCE/Optional para evitar NullPointerException em tabelas vazias.
 * 4. Padrão de Projeção DTO do Spring Data JPA com Java Records.
 */
public class AggregateAdvancedPitfallsAndSpringDemo {

    // --------------------------------------------------------------------------
    // 1. MODELOS DE DADOS EM JAVA 17
    // --------------------------------------------------------------------------
    public record Invoice(
        int invoiceId,
        String number,
        int clientId,
        BigDecimal invoiceTotal,
        BigDecimal paymentTotal,
        LocalDate invoiceDate
    ) {}

    // DTO de Projeção usado pelo Spring Data JPA
    public record RevenueMetricsDTO(
        long totalInvoices,
        BigDecimal minInvoice,
        BigDecimal maxInvoice,
        BigDecimal avgInvoice,
        BigDecimal sumInvoice
    ) {}

    // --------------------------------------------------------------------------
    // 2. REPOSITÓRIO E DADOS
    // --------------------------------------------------------------------------
    public static List<Invoice> getInvoices() {
        return List.of(
            new Invoice(1, "91-953-3396", 2, new BigDecimal("101.79"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 9)),
            new Invoice(2, "03-898-6735", 5, new BigDecimal("175.32"), new BigDecimal("8.18"), LocalDate.of(2019, 6, 11)),
            new Invoice(3, "20-228-0335", 5, new BigDecimal("147.99"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 31)),
            new Invoice(4, "56-934-0748", 3, new BigDecimal("152.21"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 8)),
            new Invoice(5, "87-508-3412", 5, new BigDecimal("169.36"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 18)),
            new Invoice(6, "75-587-0626", 1, new BigDecimal("152.53"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 29)),
            new Invoice(7, "68-093-9863", 3, new BigDecimal("153.82"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 4)),
            new Invoice(8, "78-145-1331", 1, new BigDecimal("189.12"), new BigDecimal("0.00"), LocalDate.of(2019, 5, 20)),
            new Invoice(9, "25-224-8114", 1, new BigDecimal("180.17"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 30)),
            new Invoice(10, "31-291-7294", 3, new BigDecimal("107.29"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 15)),
            new Invoice(11, "83-973-4831", 5, new BigDecimal("124.56"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 25)),
            new Invoice(12, "17-645-1166", 1, new BigDecimal("168.04"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 8)),
            new Invoice(13, "68-052-8787", 3, new BigDecimal("135.76"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 17)),
            new Invoice(14, "51-870-7231", 3, new BigDecimal("107.80"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 8)),
            new Invoice(15, "84-891-9502", 1, new BigDecimal("144.15"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 19)),
            new Invoice(16, "04-895-6503", 3, new BigDecimal("174.19"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 1)),
            new Invoice(17, "71-635-9984", 5, new BigDecimal("114.73"), new BigDecimal("0.00"), LocalDate.of(2019, 5, 24))
        );
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   CAPÍTULO 40 (PARTE 4 FINAL): PITFALLS, SUBQUERIES E SPRING DATA JPA DTOs     ");
        System.out.println("================================================================================\n");

        List<Invoice> invoices = getInvoices();

        // ----------------------------------------------------------------------
        // 1. CÁLCULO DA MÉDIA GLOBAL (Passo 1 da Subquery)
        // ----------------------------------------------------------------------
        BigDecimal totalSum = invoices.stream()
            .map(Invoice::invoiceTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal average = totalSum.divide(BigDecimal.valueOf(invoices.size()), 2, RoundingMode.HALF_UP);

        System.out.println(">>> CENÁRIO 1: FILTRAGEM ACIMA DA MÉDIA (SUBQUERY NO WHERE)");
        System.out.println("SQL: SELECT * FROM invoices WHERE invoice_total > (SELECT AVG(invoice_total) FROM invoices);\n");
        System.out.printf("Média Global Calculada: R$ %.2f\n\n", average);

        List<Invoice> aboveAverage = invoices.stream()
            .filter(i -> i.invoiceTotal().compareTo(average) > 0)
            .sorted(Comparator.comparing(Invoice::invoiceTotal).reversed())
            .toList();

        System.out.println("+------------+--------------+-----------+---------------+--------------+");
        System.out.println("| invoice_id | number       | client_id | invoice_total | invoice_date |");
        System.out.println("+------------+--------------+-----------+---------------+--------------+");
        for (Invoice inv : aboveAverage) {
            System.out.printf("| %10d | %-12s | %9d | %13.2f | %-12s |\n",
                inv.invoiceId(), inv.number(), inv.clientId(), inv.invoiceTotal(), inv.invoiceDate());
        }
        System.out.println("+------------+--------------+-----------+---------------+--------------+");
        System.out.printf("Total de faturas acima da média: %d faturas (de um total de 17)\n\n", aboveAverage.size());

        // ----------------------------------------------------------------------
        // 2. DEFESA CONTRA NULL: COALESCE(SUM(total), 0) COM Optional EM JAVA
        // ----------------------------------------------------------------------
        System.out.println(">>> CENÁRIO 2: TRATAMENTO DEFENSIVO DE FILTROS COM ZERO RESULTADOS");
        System.out.println("SQL: SELECT COALESCE(SUM(invoice_total), 0) FROM invoices WHERE invoice_date > '2050-01-01';\n");

        List<Invoice> emptyList = invoices.stream()
            .filter(i -> i.invoiceDate().isAfter(LocalDate.of(2050, 1, 1)))
            .toList();

        BigDecimal safeSum = emptyList.stream()
            .map(Invoice::invoiceTotal)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO); // Equivalente exato ao COALESCE(SUM(...), 0)

        System.out.printf("Resultado seguro sem NullPointerException: R$ %.2f\n\n", safeSum);

        // ----------------------------------------------------------------------
        // 3. PROJEÇÃO DTO DO SPRING BOOT (RevenueMetricsDTO)
        // ----------------------------------------------------------------------
        System.out.println(">>> CENÁRIO 3: PADRÃO SPRING DATA JPA DTO PROJECTION");
        System.out.println("@Query(\"\"\"");
        System.out.println("    SELECT new com.example.dto.RevenueMetricsDTO(");
        System.out.println("        COUNT(i), MIN(i.invoiceTotal), MAX(i.invoiceTotal),");
        System.out.println("        AVG(i.invoiceTotal), SUM(i.invoiceTotal)");
        System.out.println("    )");
        System.out.println("    FROM Invoice i");
        System.out.println("\"\"\")");
        System.out.println("RevenueMetricsDTO getGlobalMetrics();\n");

        RevenueMetricsDTO metrics = new RevenueMetricsDTO(
            invoices.size(),
            invoices.stream().map(Invoice::invoiceTotal).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO),
            invoices.stream().map(Invoice::invoiceTotal).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO),
            average,
            totalSum
        );

        System.out.println("Objeto DTO Gerado em Memória no Spring Boot:");
        System.out.println("• totalInvoices: " + metrics.totalInvoices());
        System.out.println("• minInvoice:    R$ " + metrics.minInvoice());
        System.out.println("• maxInvoice:    R$ " + metrics.maxInvoice());
        System.out.println("• avgInvoice:    R$ " + metrics.avgInvoice());
        System.out.println("• sumInvoice:    R$ " + metrics.sumInvoice() + "\n");

        System.out.println("================================================================================");
        System.out.println(">>> CAPÍTULO 40 100% CONCLUÍDO COM SUCESSO! PREPARADO PARA O CAPÍTULO 41!");
        System.out.println("================================================================================");
    }
}
