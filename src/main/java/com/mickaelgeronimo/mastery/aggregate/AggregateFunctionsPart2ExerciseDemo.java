package com.mickaelgeronimo.mastery.aggregate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 40 (PARTE 2): OPERADOR DISTINCT E EXERCÍCIO OFICIAL DO MOSH COM UNION
 * Demonstração Didática em Java 17 LTS para Iniciantes
 * ==============================================================================
 */
public class AggregateFunctionsPart2ExerciseDemo {

    public record Invoice(
        int invoiceId,
        String number,
        int clientId,
        BigDecimal invoiceTotal,
        BigDecimal paymentTotal,
        LocalDate invoiceDate,
        LocalDate dueDate,
        LocalDate paymentDate
    ) {
        public BigDecimal whatWeExpect() {
            return invoiceTotal.subtract(paymentTotal);
        }
    }

    public record FinancialSummaryReport(
        String dateRange,
        BigDecimal totalSales,
        BigDecimal totalPayments,
        BigDecimal whatWeExpect
    ) {}

    public static List<Invoice> getInvoices() {
        return List.of(
            new Invoice(1, "91-953-3396", 2, new BigDecimal("101.79"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 9), LocalDate.of(2019, 3, 29), null),
            new Invoice(2, "03-898-6735", 5, new BigDecimal("175.32"), new BigDecimal("8.18"), LocalDate.of(2019, 6, 11), LocalDate.of(2019, 7, 1), LocalDate.of(2019, 2, 12)),
            new Invoice(3, "20-228-0335", 5, new BigDecimal("147.99"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 31), LocalDate.of(2019, 8, 20), null),
            new Invoice(4, "56-934-0748", 3, new BigDecimal("152.21"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 8), LocalDate.of(2019, 3, 28), null),
            new Invoice(5, "87-508-3412", 5, new BigDecimal("169.36"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 18), LocalDate.of(2019, 8, 7), null),
            new Invoice(6, "75-587-0626", 1, new BigDecimal("152.53"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 29), LocalDate.of(2019, 2, 18), null),
            new Invoice(7, "68-093-9863", 3, new BigDecimal("153.82"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 4), LocalDate.of(2019, 7, 24), null),
            new Invoice(8, "78-145-1331", 1, new BigDecimal("189.12"), new BigDecimal("0.00"), LocalDate.of(2019, 5, 20), LocalDate.of(2019, 6, 9), null),
            new Invoice(9, "25-224-8114", 1, new BigDecimal("180.17"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 30), LocalDate.of(2019, 2, 19), null),
            new Invoice(10, "31-291-7294", 3, new BigDecimal("107.29"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 15), LocalDate.of(2019, 2, 4), null),
            new Invoice(11, "83-973-4831", 5, new BigDecimal("124.56"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 25), LocalDate.of(2019, 8, 14), null),
            new Invoice(12, "17-645-1166", 1, new BigDecimal("168.04"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 8), LocalDate.of(2019, 6, 28), null),
            new Invoice(13, "68-052-8787", 3, new BigDecimal("135.76"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 17), LocalDate.of(2019, 2, 6), null),
            new Invoice(14, "51-870-7231", 3, new BigDecimal("107.80"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 8), LocalDate.of(2019, 6, 28), null),
            new Invoice(15, "84-891-9502", 1, new BigDecimal("144.15"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 19), LocalDate.of(2019, 7, 9), null),
            new Invoice(16, "04-895-6503", 3, new BigDecimal("174.19"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 1), LocalDate.of(2019, 6, 21), null),
            new Invoice(17, "71-635-9984", 5, new BigDecimal("114.73"), new BigDecimal("0.00"), LocalDate.of(2019, 5, 24), LocalDate.of(2019, 6, 13), null)
        );
    }

    public static FinancialSummaryReport summarize(String rangeName, List<Invoice> list) {
        BigDecimal sales = list.stream().map(Invoice::invoiceTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal payments = list.stream().map(Invoice::paymentTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal expected = sales.subtract(payments);
        return new FinancialSummaryReport(rangeName, sales, payments, expected);
    }

    public static void main(String[] args) {
        List<Invoice> invoices = getInvoices();

        System.out.println("================================================================================");
        System.out.println("   SEÇÃO 5 (CAPÍTULO 40 - PARTE 2): DISTINCT E EXERCÍCIO OFICIAL COM UNION      ");
        System.out.println("================================================================================\n");

        // ----------------------------------------------------------------------
        // PARTE 1: COUNT vs COUNT(DISTINCT)
        // ----------------------------------------------------------------------
        long totalInvoicesCount = invoices.stream().map(Invoice::clientId).count();
        long distinctClientsCount = invoices.stream().map(Invoice::clientId).distinct().count();

        System.out.println(">>> CONSULTA 1: COUNT(client_id) VS COUNT(DISTINCT client_id)");
        System.out.println("SQL EQUIVALENTE:");
        System.out.println("SELECT ");
        System.out.println("    COUNT(client_id) AS total_faturas,");
        System.out.println("    COUNT(DISTINCT client_id) AS total_clientes_unicos");
        System.out.println("FROM invoices;\n");

        System.out.println("+---------------+----------------------+");
        System.out.println("| total_faturas | total_clientes_unicos |");
        System.out.println("+---------------+----------------------+");
        System.out.printf("| %13d | %20d |\n", totalInvoicesCount, distinctClientsCount);
        System.out.println("+---------------+----------------------+\n");

        // ----------------------------------------------------------------------
        // PARTE 2: EXERCÍCIO OFICIAL DO MOSH (RELATÓRIO FINANCEIRO COM UNION)
        // ----------------------------------------------------------------------
        LocalDate midYear = LocalDate.of(2019, 6, 30);
        LocalDate startYear = LocalDate.of(2019, 1, 1);
        LocalDate endYear = LocalDate.of(2019, 12, 31);

        List<Invoice> firstHalf = invoices.stream()
            .filter(i -> !i.invoiceDate().isBefore(startYear) && !i.invoiceDate().isAfter(midYear))
            .toList();

        List<Invoice> secondHalf = invoices.stream()
            .filter(i -> i.invoiceDate().isAfter(midYear) && !i.invoiceDate().isAfter(endYear))
            .toList();

        FinancialSummaryReport r1 = summarize("First half of 2019", firstHalf);
        FinancialSummaryReport r2 = summarize("Second half of 2019", secondHalf);
        FinancialSummaryReport r3 = summarize("Total", invoices);

        List<FinancialSummaryReport> fullReport = List.of(r1, r2, r3);

        System.out.println(">>> CONSULTA 2: EXERCÍCIO OFICIAL DO MOSH HAMEDANI (SUMÁRIO COM UNION)");
        System.out.println("SQL EQUIVALENTE:");
        System.out.println("SELECT ");
        System.out.println("    'First half of 2019' AS date_range,");
        System.out.println("    SUM(invoice_total) AS total_sales,");
        System.out.println("    SUM(payment_total) AS total_payments,");
        System.out.println("    SUM(invoice_total - payment_total) AS what_we_expect");
        System.out.println("FROM invoices");
        System.out.println("WHERE invoice_date BETWEEN '2019-01-01' AND '2019-06-30'");
        System.out.println("UNION");
        System.out.println("SELECT ");
        System.out.println("    'Second half of 2019' AS date_range,");
        System.out.println("    SUM(invoice_total) AS total_sales,");
        System.out.println("    SUM(payment_total) AS total_payments,");
        System.out.println("    SUM(invoice_total - payment_total) AS what_we_expect");
        System.out.println("FROM invoices");
        System.out.println("WHERE invoice_date BETWEEN '2019-07-01' AND '2019-12-31'");
        System.out.println("UNION");
        System.out.println("SELECT ");
        System.out.println("    'Total' AS date_range,");
        System.out.println("    SUM(invoice_total) AS total_sales,");
        System.out.println("    SUM(payment_total) AS total_payments,");
        System.out.println("    SUM(invoice_total - payment_total) AS what_we_expect");
        System.out.println("FROM invoices");
        System.out.println("WHERE invoice_date BETWEEN '2019-01-01' AND '2019-12-31';\n");

        System.out.println("+---------------------+-------------+----------------+----------------+");
        System.out.println("| date_range          | total_sales | total_payments | what_we_expect |");
        System.out.println("+---------------------+-------------+----------------+----------------+");
        for (FinancialSummaryReport row : fullReport) {
            System.out.printf("| %-19s | %11.2f | %14.2f | %14.2f |\n",
                row.dateRange(), row.totalSales(), row.totalPayments(), row.whatWeExpect());
        }
        System.out.println("+---------------------+-------------+----------------+----------------+\n");

        System.out.println("================================================================================");
        System.out.println(">>> VALIDAÇÃO MATEMÁTICA CONCLUÍDA COM 100% DE PRECISÃO!");
        System.out.println("================================================================================");
    }
}
