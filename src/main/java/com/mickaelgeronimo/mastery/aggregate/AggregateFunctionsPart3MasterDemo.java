package com.mickaelgeronimo.mastery.aggregate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 40 (PARTE 3 FINAL): JUNÇÕES COM AGREGAÇÃO, PERFORMANCE E GROUP_CONCAT
 * Demonstração Didática em Java 17 LTS para Iniciantes
 * ==============================================================================
 */
public class AggregateFunctionsPart3MasterDemo {

    public record Client(int clientId, String name) {}

    public record Invoice(
        int invoiceId,
        String number,
        int clientId,
        BigDecimal invoiceTotal,
        BigDecimal paymentTotal,
        LocalDate invoiceDate
    ) {}

    public record ClientInvoiceSummary(
        String clientName,
        long invoiceCount,
        BigDecimal totalBilled,
        BigDecimal totalPaid,
        BigDecimal totalBalance
    ) {}

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   CAPÍTULO 40 (PARTE 3 FINAL): ENGENHARIA AVANÇADA DE AGREGAÇÕES EM JAVA 17    ");
        System.out.println("================================================================================\n");

        // Clientes de sql_invoicing
        Map<Integer, Client> clients = Map.of(
            1, new Client(1, "Vinte"),
            2, new Client(2, "Myworks"),
            3, new Client(3, "Yadel"),
            4, new Client(4, "Kwality Food"),
            5, new Client(5, "Topiclounge")
        );

        // 17 Faturas originais de sql_invoicing
        List<Invoice> invoices = List.of(
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

        // ----------------------------------------------------------------------
        // 1. AGREGAÇÃO GLOBAL COM INNER JOIN ENTRE CLIENTS E INVOICES
        // ----------------------------------------------------------------------
        BigDecimal grandTotalInvoices = invoices.stream()
            .map(Invoice::invoiceTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grandTotalPayments = invoices.stream()
            .map(Invoice::paymentTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        long distinctClientsBilled = invoices.stream()
            .map(Invoice::clientId)
            .distinct()
            .count();

        // ----------------------------------------------------------------------
        // 2. EQUIVALENTE AO GROUP_CONCAT(number SEPARATOR ', ')
        // ----------------------------------------------------------------------
        String concatenatedInvoiceNumbers = invoices.stream()
            .map(Invoice::number)
            .limit(5)
            .collect(Collectors.joining(", ", "", "... (amostra das 5 primeiras)"));

        // ----------------------------------------------------------------------
        // 3. ESTATÍSTICAS RESUMIDAS COM DoubleSummaryStatistics
        // ----------------------------------------------------------------------
        DoubleSummaryStatistics stats = invoices.stream()
            .mapToDouble(i -> i.invoiceTotal().doubleValue())
            .summaryStatistics();

        System.out.println(">>> CONSULTA 1: AUDITORIA GLOBAL MULTI-TABELA COM JOIN");
        System.out.println("SQL EQUIVALENTE:");
        System.out.println("SELECT ");
        System.out.println("    COUNT(DISTINCT c.client_id) AS clientes_ativos,");
        System.out.println("    COUNT(i.invoice_id) AS total_faturas,");
        System.out.println("    SUM(i.invoice_total) AS total_faturado,");
        System.out.println("    SUM(i.payment_total) AS total_liquidado,");
        System.out.println("    GROUP_CONCAT(DISTINCT c.name ORDER BY c.name SEPARATOR ', ') AS clientes_com_fatura");
        System.out.println("FROM clients c");
        System.out.println("JOIN invoices i ON c.client_id = i.client_id;\n");

        String clientsConcat = invoices.stream()
            .map(i -> clients.get(i.clientId()).name())
            .distinct()
            .sorted()
            .collect(Collectors.joining(", "));

        System.out.println("+-----------------+---------------+----------------+-----------------+----------------------------------------+");
        System.out.println("| clientes_ativos | total_faturas | total_faturado | total_liquidado | clientes_com_fatura                    |");
        System.out.println("+-----------------+---------------+----------------+-----------------+----------------------------------------+");
        System.out.printf("| %15d | %13d | %14.2f | %15.2f | %-38s |\n",
            distinctClientsBilled, invoices.size(), grandTotalInvoices, grandTotalPayments, clientsConcat);
        System.out.println("+-----------------+---------------+----------------+-----------------+----------------------------------------+\n");

        System.out.println(">>> DEMONSTRAÇÃO 2: DoubleSummaryStatistics DO JAVA 17");
        System.out.printf("• Quantidade (count): %d\n", stats.getCount());
        System.out.printf("• Mínimo (min):       R$ %.2f\n", stats.getMin());
        System.out.printf("• Máximo (max):       R$ %.2f\n", stats.getMax());
        System.out.printf("• Média (average):    R$ %.2f\n", stats.getAverage());
        System.out.printf("• Somatório (sum):    R$ %.2f\n\n", stats.getSum());

        System.out.println(">>> DEMONSTRAÇÃO 3: GROUP_CONCAT EM JAVA (Collectors.joining)");
        System.out.println("Amostra de Números de Fatura: " + concatenatedInvoiceNumbers + "\n");

        System.out.println("================================================================================");
        System.out.println(">>> CONCLUSÃO DA PARTE 3 FINAL: Todos os motores de agregação validados!");
        System.out.println("================================================================================");
    }
}
