package com.mickaelgeronimo.mastery.joins.using_clause;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 26 (PARTE 5 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Grand Finale da Cláusula USING — Relatório Financeiro 360° em sql_invoicing,
 *       Chaves Simples e Compostas, e Integração Corporativa Type-Safe (jOOQ)
 * ==============================================================================
 */
public class UsingClauseFinalMasterDemo {

    record Client(int clientId, String name) {}
    record Invoice(int invoiceId, int clientId, double invoiceTotal) {}
    record PaymentCompositeKey(int clientId, int invoiceId) {}
    record Payment(int paymentId, int clientId, int invoiceId, double amount, int paymentMethodId) {}
    record PaymentMethod(int id, String name) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 26 (PARTE 5 - FINAL) - MASTER THE USING CLAUSE            ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS CLIENTES (sql_invoicing.clients)
        Map<Integer, Client> clients = Map.of(
            1, new Client(1, "Vinte"),
            2, new Client(2, "Myworks"),
            3, new Client(3, "Yadel"),
            5, new Client(5, "Topiclounge")
        );

        // 2. CARREGAMENTO DAS FATURAS (invoices 1 a 6)
        List<Invoice> invoices = Arrays.asList(
            new Invoice(1, 1, 101.79),
            new Invoice(2, 1, 175.32),
            new Invoice(3, 3, 147.99),
            new Invoice(4, 2, 121.22),
            new Invoice(5, 5, 130.44),
            new Invoice(6, 1, 168.00) // Sem pagamento (pendente)
        );

        // 3. CARREGAMENTO DOS PAGAMENTOS INDEXADOS POR CHAVE COMPOSTA (client_id, invoice_id)
        Map<PaymentCompositeKey, Payment> payments = Map.of(
            new PaymentCompositeKey(1, 1), new Payment(1, 1, 1, 74.55, 1),
            new PaymentCompositeKey(1, 2), new Payment(8, 1, 2, 8.18, 1),
            new PaymentCompositeKey(3, 3), new Payment(4, 3, 3, 0.03, 1),
            new PaymentCompositeKey(2, 4), new Payment(7, 2, 4, 87.44, 1),
            new PaymentCompositeKey(5, 5), new Payment(5, 5, 5, 80.31, 1)
        );

        // 4. MÉTODOS DE PAGAMENTO
        Map<Integer, PaymentMethod> methods = Map.of(
            1, new PaymentMethod(1, "Credit Card"),
            2, new PaymentMethod(2, "Cash")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O RELATÓRIO FINANCEIRO 360° COM USING SIMPLES, COMPOSTO E ON
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. RELATÓRIO FINANCEIRO 360° CONSOLIDADO (sql_invoicing) ---");
        System.out.println("+-----------+-------------+------------+---------------+----------------+----------------------+");
        System.out.println("| client_id | client_name | invoice_id | invoice_total | payment_amount | payment_method       |");
        System.out.println("+-----------+-------------+------------+---------------+----------------+----------------------+");

        // Ordenamos por client_id e invoice_id
        List<Invoice> sortedInvoices = new ArrayList<>(invoices);
        sortedInvoices.sort(Comparator.comparingInt(Invoice::clientId).thenComparingInt(Invoice::invoiceId));

        for (Invoice inv : sortedInvoices) {
            Client c = clients.get(inv.clientId());
            PaymentCompositeKey pKey = new PaymentCompositeKey(inv.clientId(), inv.invoiceId());
            Payment p = payments.get(pKey);

            double payAmount = (p != null) ? p.amount() : 0.00;
            String payMethod = (p != null && methods.containsKey(p.paymentMethodId()))
                    ? methods.get(p.paymentMethodId()).name()
                    : "Pendente / Não Pago";

            System.out.printf("| %9d | %-11s | %10d | %13.2f | %14.2f | %-20s |\n",
                    inv.clientId(), c.name(), inv.invoiceId(), inv.invoiceTotal(), payAmount, payMethod);
        }
        System.out.println("+-----------+-------------+------------+---------------+----------------+----------------------+");
        System.out.println("6 rows in set (0.00 sec) -> Fatura 6 preservada como 'Pendente / Não Pago' com payment = 0.00!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: COMO O JAVA TYPE-SAFE (jOOQ) IMPLEMENTA A CLÁUSULA USING
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A CLÁUSULA USING NO FRAMEWORK JAVA jOOQ ---");
        System.out.println("Em aplicações empresariais Java usando jOOQ:");
        System.out.println("  dsl.select(ORDERS.ORDER_ID, CUSTOMERS.FIRST_NAME)");
        System.out.println("     .from(ORDERS)");
        System.out.println("     .join(CUSTOMERS).using(ORDERS.CUSTOMER_ID)  <-- Suporte nativo type-safe!");
        System.out.println("     .fetch();");
        System.out.println("Vantagem: O compilador Java valida os tipos e nomes das colunas em tempo de compilação!\n");

        System.out.println("Grand Finale do Capítulo 26 (The USING Clause) Concluído com Sucesso!");
    }
}
