package com.mickaelgeronimo.mastery.unions;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ==============================================================================
 * CAPÍTULO 29 (PARTE 3): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Engenharia de Uniões, CTEs com Agregações, Extrato Contábil Unificado (Ledger)
 *       e o Padrão de Processamento Polimórfico de Eventos Financeiros
 * ==============================================================================
 */
public class UnionPart3MasterDemo {

    // 1. DTO de Cliente e Relatório de Fidelidade
    public record Customer(int id, String firstName, int points) {}
    public record CustomerLoyalty(int id, String firstName, int points, String tier) {}

    // DTO da Agregação (Simulação da CTE com GROUP BY)
    public record LoyaltySummary(String tier, long totalCustomers, double avgPoints, int minPoints, int maxPoints) {
        @Override
        public String toString() {
            return String.format("| %-8s | %15d | %10.2f | %10d | %10d |",
                    tier, totalCustomers, avgPoints, minPoints, maxPoints);
        }
    }

    // 2. DTOs do Extrato Contábil Unificado (Financial Ledger)
    public record Invoice(int invoiceId, String number, int clientId, double invoiceTotal, LocalDate invoiceDate) {}
    public record Payment(int paymentId, int clientId, double amount, int paymentMethod, LocalDate paymentDate) {}

    public record FinancialTransaction(LocalDate date, String description, int clientId, double debit, double credit) {
        @Override
        public String toString() {
            return String.format("| %-10s | %-28s | %9d | %10.2f | %10.2f |",
                    date, description, clientId, debit, credit);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 29 (PARTE 3) - MASTER CLASS DE UNIONS E FINANCIAL LEDGER   ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // PARTE 1: SIMULAÇÃO DA CTE SQL: GROUP BY SOBRE O RESULTADO DO UNION
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. SIMULAÇÃO DE CTE + GROUP BY: RESUMO ESTATÍSTICO DAS FAIXAS DE FIDELIDADE ---");
        System.out.println("+----------+-----------------+------------+------------+------------+");
        System.out.println("| type     | total_customers | avg_points | min_points | max_points |");
        System.out.println("+----------+-----------------+------------+------------+------------+");

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

        // Gera o fluxo unificado (idêntico ao UNION ALL das 3 faixas)
        Stream<CustomerLoyalty> bronzeStream = customers.stream()
                .filter(c -> c.points() < 2000)
                .map(c -> new CustomerLoyalty(c.id(), c.firstName(), c.points(), "Bronze"));

        Stream<CustomerLoyalty> silverStream = customers.stream()
                .filter(c -> c.points() >= 2000 && c.points() <= 3000)
                .map(c -> new CustomerLoyalty(c.id(), c.firstName(), c.points(), "Silver"));

        Stream<CustomerLoyalty> goldStream = customers.stream()
                .filter(c -> c.points() > 3000)
                .map(c -> new CustomerLoyalty(c.id(), c.firstName(), c.points(), "Gold"));

        List<CustomerLoyalty> unifiedLoyaltyList = Stream.of(bronzeStream, silverStream, goldStream)
                .flatMap(Function.identity())
                .collect(Collectors.toList());

        // Agrupamento por 'tier' (Equivalente ao GROUP BY type na CTE)
        Map<String, List<CustomerLoyalty>> groupedByTier = unifiedLoyaltyList.stream()
                .collect(Collectors.groupingBy(CustomerLoyalty::tier));

        List<LoyaltySummary> summaries = new ArrayList<>();
        for (Map.Entry<String, List<CustomerLoyalty>> entry : groupedByTier.entrySet()) {
            String tier = entry.getKey();
            List<CustomerLoyalty> list = entry.getValue();
            long count = list.size();
            double avg = list.stream().mapToInt(CustomerLoyalty::points).average().orElse(0.0);
            int min = list.stream().mapToInt(CustomerLoyalty::points).min().orElse(0);
            int max = list.stream().mapToInt(CustomerLoyalty::points).max().orElse(0);
            summaries.add(new LoyaltySummary(tier, count, avg, min, max));
        }

        // Ordenar por total_customers DESC
        summaries.sort(Comparator.comparingLong(LoyaltySummary::totalCustomers).reversed());

        for (LoyaltySummary s : summaries) {
            System.out.println(s);
        }
        System.out.println("+----------+-----------------+------------+------------+------------+");
        System.out.println(summaries.size() + " rows in set (0.00 sec)\n");

        // --------------------------------------------------------------------------------------
        // PARTE 2: EXTRATO CONTÁBIL UNIFICADO (FINANCIAL LEDGER COM UNION ALL)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. EXTRATO CONTÁBIL UNIFICADO (sql_invoicing: Faturas + Pagamentos) ---");
        System.out.println("+------------+------------------------------+-----------+------------+------------+");
        System.out.println("| date       | description                  | client_id | debit      | credit     |");
        System.out.println("+------------+------------------------------+-----------+------------+------------+");

        List<Invoice> sampleInvoices = Arrays.asList(
            new Invoice(1, "91-953-3396", 2, 101.79, LocalDate.of(2019, 3, 9)),
            new Invoice(2, "03-898-6735", 5, 175.32, LocalDate.of(2019, 6, 11)),
            new Invoice(3, "20-228-0335", 3, 147.99, LocalDate.of(2019, 7, 31))
        );

        List<Payment> samplePayments = Arrays.asList(
            new Payment(1, 5, 10.00, 1, LocalDate.of(2019, 2, 12)),
            new Payment(2, 1, 12.00, 1, LocalDate.of(2019, 1, 3)),
            new Payment(3, 3, 147.99, 2, LocalDate.of(2019, 6, 9))
        );

        // Stream de Débitos (Faturas emitidas)
        Stream<FinancialTransaction> debitTransactions = sampleInvoices.stream()
                .map(inv -> new FinancialTransaction(
                        inv.invoiceDate(),
                        "Invoice #" + inv.number(),
                        inv.clientId(),
                        inv.invoiceTotal(),
                        0.00
                ));

        // Stream de Créditos (Pagamentos recebidos)
        Stream<FinancialTransaction> creditTransactions = samplePayments.stream()
                .map(pmt -> new FinancialTransaction(
                        pmt.paymentDate(),
                        "Payment via Method #" + pmt.paymentMethod(),
                        pmt.clientId(),
                        0.00,
                        pmt.amount()
                ));

        // UNION ALL + ORDER BY date ASC
        List<FinancialTransaction> ledger = Stream.concat(debitTransactions, creditTransactions)
                .sorted(Comparator.comparing(FinancialTransaction::date))
                .collect(Collectors.toList());

        for (FinancialTransaction tx : ledger) {
            System.out.println(tx);
        }
        System.out.println("+------------+------------------------------+-----------+------------+------------+");
        System.out.println(ledger.size() + " transactions in chronological ledger.\n");

        System.out.println("SUCESSO: Todos os conceitos corporativos de UNION implementados com precisão cirúrgica!");
    }
}
