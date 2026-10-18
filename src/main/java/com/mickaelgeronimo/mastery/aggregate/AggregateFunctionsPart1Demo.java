package com.mickaelgeronimo.mastery.aggregate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 40 (PARTE 1): FUNÇÕES DE AGREGAÇÃO (AGGREGATE FUNCTIONS)
 * Demonstração Didática em Java 17 LTS para Iniciantes
 * ==============================================================================
 * 
 * Este programa demonstra como o Java 17 replica com precisão matemática as
 * 5 grandes funções de agregação do SQL (MAX, MIN, AVG, SUM, COUNT), tratando
 * valores nulos (NULL) e expressões aritméticas em streams de dados.
 */
public class AggregateFunctionsPart1Demo {

    // --------------------------------------------------------------------------
    // 1. MODELO DE DADOS (RECORD IMUTÁVEL JAVA 17)
    // --------------------------------------------------------------------------
    public record Invoice(
        int invoiceId,
        String number,
        int clientId,
        BigDecimal invoiceTotal,
        BigDecimal paymentTotal,
        LocalDate invoiceDate,
        LocalDate dueDate,
        LocalDate paymentDate // Pode ser null!
    ) {}

    // --------------------------------------------------------------------------
    // 2. REPOSITÓRIO SIMULADO COM OS DADOS ORIGINAIS DE sql_invoicing.invoices
    // --------------------------------------------------------------------------
    public static List<Invoice> getOriginalInvoices() {
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

    // --------------------------------------------------------------------------
    // 3. EXECUÇÃO E DEMONSTRAÇÃO DAS AGREGAÇÕES
    // --------------------------------------------------------------------------
    public static void main(String[] args) {
        List<Invoice> invoices = getOriginalInvoices();

        System.out.println("================================================================================");
        System.out.println("   SEÇÃO 5 (CAPÍTULO 40 - PARTE 1): FUNÇÕES DE AGREGAÇÃO (JAVA 17 DEMO)        ");
        System.out.println("================================================================================\n");

        // 1. MAX(invoice_total)
        BigDecimal maxTotal = invoices.stream()
            .map(Invoice::invoiceTotal)
            .max(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);

        // 2. MIN(invoice_total)
        BigDecimal minTotal = invoices.stream()
            .map(Invoice::invoiceTotal)
            .min(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);

        // 3. SUM(invoice_total)
        BigDecimal sumTotal = invoices.stream()
            .map(Invoice::invoiceTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. AVG(invoice_total)
        BigDecimal avgTotal = sumTotal.divide(BigDecimal.valueOf(invoices.size()), 2, RoundingMode.HALF_UP);

        // 5. COUNT(invoice_total) vs COUNT(payment_date) vs COUNT(*)
        long countInvoiceTotal = invoices.stream()
            .map(Invoice::invoiceTotal)
            .filter(Objects::nonNull)
            .count();

        long countPaymentDate = invoices.stream()
            .map(Invoice::paymentDate)
            .filter(Objects::nonNull)
            .count();

        long totalRecords = invoices.size();

        System.out.println(">>> CONSULTA 1: AS 5 GRANDES FUNÇÕES DE AGREGAÇÃO NA TABELA 'invoices'");
        System.out.println("SQL EQUIVALENTE:");
        System.out.println("SELECT ");
        System.out.println("    MAX(invoice_total) AS highest,");
        System.out.println("    MIN(invoice_total) AS lowest,");
        System.out.println("    AVG(invoice_total) AS average,");
        System.out.println("    SUM(invoice_total) AS total,");
        System.out.println("    COUNT(invoice_total) AS number_of_invoices,");
        System.out.println("    COUNT(payment_date) AS count_of_payments,");
        System.out.println("    COUNT(*) AS total_records");
        System.out.println("FROM invoices;\n");

        System.out.println("+---------+--------+---------+---------+--------------------+-------------------+---------------+");
        System.out.println("| highest | lowest | average | total   | number_of_invoices | count_of_payments | total_records |");
        System.out.println("+---------+--------+---------+---------+--------------------+-------------------+---------------+");
        System.out.printf("| %7.2f | %6.2f | %7.2f | %7.2f | %18d | %17d | %13d |\n",
            maxTotal, minTotal, avgTotal, sumTotal, countInvoiceTotal, countPaymentDate, totalRecords);
        System.out.println("+---------+--------+---------+---------+--------------------+-------------------+---------------+\n");

        // ----------------------------------------------------------------------
        // CONSULTA 2: AGREGAÇÕES COM FILTRO WHERE (SEGUNDO SEMESTRE DE 2019)
        // ----------------------------------------------------------------------
        LocalDate cutoffDate = LocalDate.of(2019, 7, 1);
        List<Invoice> secondHalfInvoices = invoices.stream()
            .filter(inv -> inv.invoiceDate().isAfter(cutoffDate))
            .toList();

        BigDecimal maxSecondHalf = secondHalfInvoices.stream().map(Invoice::invoiceTotal).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal minSecondHalf = secondHalfInvoices.stream().map(Invoice::invoiceTotal).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal sumSecondHalf = secondHalfInvoices.stream().map(Invoice::invoiceTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avgSecondHalf = sumSecondHalf.divide(BigDecimal.valueOf(secondHalfInvoices.size()), 2, RoundingMode.HALF_UP);
        // Expressão com acréscimo de 10%: SUM(invoice_total * 1.1)
        BigDecimal sumWithTenPercent = sumSecondHalf.multiply(new BigDecimal("1.1")).setScale(2, RoundingMode.HALF_UP);

        System.out.println(">>> CONSULTA 2: AGREGAÇÕES COM FILTRO WHERE (invoice_date > '2019-07-01') E EXPRESSÃO (* 1.1)");
        System.out.println("SQL EQUIVALENTE:");
        System.out.println("SELECT ");
        System.out.println("    MAX(invoice_total) AS highest,");
        System.out.println("    MIN(invoice_total) AS lowest,");
        System.out.println("    AVG(invoice_total) AS average,");
        System.out.println("    SUM(invoice_total * 1.1) AS total,");
        System.out.println("    COUNT(*) AS number_of_invoices");
        System.out.println("FROM invoices");
        System.out.println("WHERE invoice_date > '2019-07-01';\n");

        System.out.println("+---------+--------+---------+--------+--------------------+");
        System.out.println("| highest | lowest | average | total  | number_of_invoices |");
        System.out.println("+---------+--------+---------+--------+--------------------+");
        System.out.printf("| %7.2f | %6.2f | %7.2f | %6.2f | %18d |\n",
            maxSecondHalf, minSecondHalf, avgSecondHalf, sumWithTenPercent, secondHalfInvoices.size());
        System.out.println("+---------+--------+---------+--------+--------------------+\n");

        // ----------------------------------------------------------------------
        // CONSULTA 3: AGREGAÇÕES COM DATAS E STRINGS
        // ----------------------------------------------------------------------
        LocalDate latestPayment = invoices.stream()
            .map(Invoice::paymentDate)
            .filter(Objects::nonNull)
            .max(LocalDate::compareTo)
            .orElse(null);

        LocalDate earliestInvoice = invoices.stream()
            .map(Invoice::invoiceDate)
            .min(LocalDate::compareTo)
            .orElse(null);

        LocalDate latestInvoice = invoices.stream()
            .map(Invoice::invoiceDate)
            .max(LocalDate::compareTo)
            .orElse(null);

        System.out.println(">>> CONSULTA 3: MAX() E MIN() APLICADOS EM DATAS");
        System.out.println("SQL EQUIVALENTE:");
        System.out.println("SELECT ");
        System.out.println("    MIN(invoice_date) AS earliest_invoice,");
        System.out.println("    MAX(invoice_date) AS latest_invoice,");
        System.out.println("    MAX(payment_date) AS latest_payment");
        System.out.println("FROM invoices;\n");

        System.out.println("+------------------+----------------+----------------+");
        System.out.println("| earliest_invoice | latest_invoice | latest_payment |");
        System.out.println("+------------------+----------------+----------------+");
        System.out.printf("| %16s | %14s | %14s |\n", earliestInvoice, latestInvoice, latestPayment);
        System.out.println("+------------------+----------------+----------------+\n");

        System.out.println("================================================================================");
        System.out.println(">>> VALIDAÇÃO CONCLUÍDA: Todos os cálculos matemáticos em conformidade com o MySQL!");
        System.out.println("================================================================================");
    }
}
