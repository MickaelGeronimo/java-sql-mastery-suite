package com.mickaelgeronimo.mastery.dml.copy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 34 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Resolução do Exercício Oficial do Mosh (sql_invoicing) —
 *       Criação da Tabela invoices_archived com JOIN, DTO Projection e Filtro
 * ==============================================================================
 */
public class CreatingCopyOfTablePart2ExerciseDemo {

    // Modelos Relacionais Originais
    public record Client(int clientId, String name) {}

    public record Invoice(
        int invoiceId,
        String number,
        int clientId,
        BigDecimal invoiceTotal,
        BigDecimal paymentTotal,
        LocalDate invoiceDate,
        LocalDate dueDate,
        LocalDate paymentDate
    ) {}

    // Modelo Desnormalizado da Tabela Clonada (invoices_archived)
    public record InvoiceArchived(
        int invoiceId,
        String number,
        String client,
        BigDecimal invoiceTotal,
        BigDecimal paymentTotal,
        LocalDate invoiceDate,
        LocalDate dueDate,
        LocalDate paymentDate
    ) {
        @Override
        public String toString() {
            return String.format("| %10d | %-12s | %-10s | %13s | %13s | %-12s | %-10s | %-12s |",
                invoiceId, number, client, invoiceTotal, paymentTotal,
                invoiceDate, dueDate, paymentDate);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 34 (PARTE 2) - EXERCÍCIO OFICIAL sql_invoicing (MOSH)      ");
        System.out.println("=========================================================================================\n");

        // 1. Dados da Tabela 'clients'
        Map<Integer, Client> clientsTable = Map.of(
            1, new Client(1, "Vinte"),
            2, new Client(2, "Myworks"),
            3, new Client(3, "Yadel"),
            5, new Client(5, "Topgo")
        );

        // 2. Dados da Tabela 'invoices' (com pagas e pendentes)
        List<Invoice> invoicesTable = List.of(
            new Invoice(1, "91-953-3396", 2, new BigDecimal("101.79"), new BigDecimal("101.79"), LocalDate.of(2019, 3, 9), LocalDate.of(2019, 3, 29), LocalDate.of(2019, 3, 9)),
            new Invoice(2, "03-898-6735", 5, new BigDecimal("175.32"), new BigDecimal("0.00"), LocalDate.of(2019, 6, 11), LocalDate.of(2019, 7, 1), null), // Não paga
            new Invoice(3, "20-228-0335", 3, new BigDecimal("147.99"), new BigDecimal("147.99"), LocalDate.of(2019, 6, 11), LocalDate.of(2019, 7, 1), LocalDate.of(2019, 6, 11)),
            new Invoice(4, "56-934-0748", 3, new BigDecimal("152.21"), new BigDecimal("152.21"), LocalDate.of(2019, 1, 26), LocalDate.of(2019, 2, 15), LocalDate.of(2019, 1, 26)),
            new Invoice(5, "87-052-3121", 5, new BigDecimal("169.36"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 18), LocalDate.of(2019, 8, 7), null), // Não paga
            new Invoice(7, "10-410-6593", 3, new BigDecimal("130.44"), new BigDecimal("130.44"), LocalDate.of(2019, 1, 15), LocalDate.of(2019, 2, 4), LocalDate.of(2019, 1, 15)),
            new Invoice(8, "42-297-8798", 1, new BigDecimal("90.10"), new BigDecimal("90.10"), LocalDate.of(2019, 2, 12), LocalDate.of(2019, 3, 4), LocalDate.of(2019, 2, 12)),
            new Invoice(11, "65-078-8587", 1, new BigDecimal("176.76"), new BigDecimal("176.76"), LocalDate.of(2019, 1, 8), LocalDate.of(2019, 1, 28), LocalDate.of(2019, 1, 8)),
            new Invoice(13, "52-269-9803", 5, new BigDecimal("149.78"), new BigDecimal("149.78"), LocalDate.of(2019, 1, 27), LocalDate.of(2019, 2, 16), LocalDate.of(2019, 1, 27)),
            new Invoice(15, "07-552-2249", 5, new BigDecimal("135.60"), new BigDecimal("135.60"), LocalDate.of(2019, 1, 15), LocalDate.of(2019, 2, 4), LocalDate.of(2019, 1, 15))
        );

        System.out.println("--- 1. EXECUTANDO A CONSULTA CTAS COM JOIN E FILTRO payment_date IS NOT NULL ---");
        System.out.println("SQL: CREATE TABLE invoices_archived AS");
        System.out.println("     SELECT i.invoice_id, i.number, c.name AS client, i.invoice_total, i.payment_total,");
        System.out.println("            i.invoice_date, i.due_date, i.payment_date");
        System.out.println("     FROM invoices i JOIN clients c USING (client_id)");
        System.out.println("     WHERE i.payment_date IS NOT NULL;\n");

        // Execução em Java simulando o JOIN e WHERE
        List<InvoiceArchived> archivedList = invoicesTable.stream()
            .filter(inv -> inv.paymentDate() != null) // payment_date IS NOT NULL
            .map(inv -> new InvoiceArchived(
                inv.invoiceId(),
                inv.number(),
                clientsTable.get(inv.clientId()).name(), // JOIN clients c USING (client_id)
                inv.invoiceTotal(),
                inv.paymentTotal(),
                inv.invoiceDate(),
                inv.dueDate(),
                inv.paymentDate()
            ))
            .sorted(Comparator.comparingInt(InvoiceArchived::invoiceId))
            .collect(Collectors.toList());

        System.out.printf("-> MySQL Console: Query OK, %d rows affected (0.02 sec)%n", archivedList.size());
        System.out.printf("->                Records: %d  Duplicates: 0  Warnings: 0%n%n", archivedList.size());

        // Exibição dos dados clonados
        System.out.println(">>> CONTEÚDO DA NOVA TABELA invoices_archived:");
        System.out.println("+------------+--------------+------------+---------------+---------------+--------------+------------+--------------+");
        System.out.println("| invoice_id | number       | client     | invoice_total | payment_total | invoice_date | due_date   | payment_date |");
        System.out.println("+------------+--------------+------------+---------------+---------------+--------------+------------+--------------+");
        archivedList.forEach(System.out::println);
        System.out.println("+------------+--------------+------------+---------------+---------------+--------------+------------+--------------+\n");

        // Relatório analítico agrupado por cliente
        System.out.println("--- 2. RELATÓRIO ANALÍTICO FINANCEIRO SOBRE A TABELA ARQUIVADA ---");
        Map<String, List<InvoiceArchived>> groupedByClient = archivedList.stream()
            .collect(Collectors.groupingBy(InvoiceArchived::client));

        System.out.println("+------------+--------------------+----------------+------------+");
        System.out.println("| client     | total_invoices_paid| total_invoiced | total_paid |");
        System.out.println("+------------+--------------------+----------------+------------+");

        groupedByClient.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().stream().map(InvoiceArchived::paymentTotal).reduce(BigDecimal.ZERO, BigDecimal::add)
                .compareTo(e1.getValue().stream().map(InvoiceArchived::paymentTotal).reduce(BigDecimal.ZERO, BigDecimal::add)))
            .forEach(entry -> {
                String clientName = entry.getKey();
                int count = entry.getValue().size();
                BigDecimal totalInv = entry.getValue().stream().map(InvoiceArchived::invoiceTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal totalPay = entry.getValue().stream().map(InvoiceArchived::paymentTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                System.out.printf("| %-10s | %18d | %14s | %10s |%n", clientName, count, totalInv, totalPay);
            });

        System.out.println("+------------+--------------------+----------------+------------+");
        System.out.println("\nDemostração do exercício do Mosh concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }
}
