package com.mickaelgeronimo.mastery.joins.distributed;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * ==============================================================================
 * CAPÍTULO 24 (PARTE 5 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junção Externa Multi-Tabelas em Arquitetura de Microsserviços
 *       usando CompletableFuture, Streams e Projeções Financeiras com Saldo Devedor
 * ==============================================================================
 */
public class MicroserviceMultiTableOuterJoinDemo {

    // DTOs de Microsserviços Distribuídos
    record ClientDTO(int id, String name, String phone) {}
    record InvoiceDTO(int invoiceId, int clientId, double total) {}
    record PaymentDTO(int paymentId, int invoiceId, double amount, String method) {}

    // DTO Consolidado do Relatório 360°
    record EnterpriseBillingReport(
        int clientId,
        String clientName,
        String invoiceId,
        double invoiceTotal,
        double amountPaid,
        double balanceDue,
        String paymentMethod,
        String status
    ) {}

    public static void main(String[] args) throws Exception {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: GRAND FINALE CAPÍTULO 24 - JUNÇÃO EXTERNA DISTRIBUÍDA (MICROSSERVIÇOS)");
        System.out.println("=========================================================================================\n");

        System.out.println("Disparando chamadas assíncronas paralelas para 3 microsserviços distintos...");

        // 1. Microsserviço 1: ClientService (Base PostgreSQL)
        CompletableFuture<List<ClientDTO>> futureClients = CompletableFuture.supplyAsync(() -> {
            return Arrays.asList(
                new ClientDTO(1, "Vinte", "555-0101"),
                new ClientDTO(2, "Myworks", "555-0102"),
                new ClientDTO(3, "Topiclounge", "555-0103"),
                new ClientDTO(4, "Kwam", "555-0104"),
                new ClientDTO(5, "Yadel", "555-0105"),
                new ClientDTO(6, "Acme Corp (Novo)", "555-0106") // Cliente sem faturas!
            );
        });

        // 2. Microsserviço 2: BillingService (Base MySQL)
        CompletableFuture<List<InvoiceDTO>> futureInvoices = CompletableFuture.supplyAsync(() -> {
            return Arrays.asList(
                new InvoiceDTO(1, 1, 101.79),
                new InvoiceDTO(2, 1, 175.32),
                new InvoiceDTO(3, 3, 147.99),
                new InvoiceDTO(4, 2, 152.21), // Fatura pendente sem pagamentos!
                new InvoiceDTO(5, 4, 169.36)
            );
        });

        // 3. Microsserviço 3: PaymentGatewayService (Base Oracle)
        CompletableFuture<List<PaymentDTO>> futurePayments = CompletableFuture.supplyAsync(() -> {
            return Arrays.asList(
                new PaymentDTO(1, 1, 74.55, "Credit Card"),
                new PaymentDTO(2, 2, 8.18, "Credit Card"),
                new PaymentDTO(3, 3, 0.03, "Credit Card"),
                new PaymentDTO(4, 5, 80.31, "Credit Card")
            );
        });

        // Aguarda a resposta paralela de todos os microsserviços
        CompletableFuture.allOf(futureClients, futureInvoices, futurePayments).join();

        List<ClientDTO> clients = futureClients.get();
        List<InvoiceDTO> invoices = futureInvoices.get();
        List<PaymentDTO> payments = futurePayments.get();

        System.out.println("✅ Dados coletados de todos os serviços com sucesso! Executando Cascading LEFT JOIN em memória...\n");

        // --------------------------------------------------------------------------------------
        // CASCADING LEFT JOIN DISTRIBUÍDO EM MEMÓRIA
        // --------------------------------------------------------------------------------------
        List<EnterpriseBillingReport> reports = new ArrayList<>();

        for (ClientDTO c : clients) {
            boolean hasInvoice = false;
            for (InvoiceDTO inv : invoices) {
                if (inv.clientId == c.id) {
                    hasInvoice = true;
                    // Procura pagamentos para esta fatura (LEFT JOIN):
                    PaymentDTO matchedPayment = null;
                    for (PaymentDTO p : payments) {
                        if (p.invoiceId == inv.invoiceId) {
                            matchedPayment = p;
                            break;
                        }
                    }

                    double paid = (matchedPayment != null) ? matchedPayment.amount : 0.0;
                    double balance = inv.total - paid;
                    String method = (matchedPayment != null) ? matchedPayment.method : "Nenhum";
                    String status = (balance <= 0.01) ? "Totalmente Liquidado"
                            : (paid > 0 ? "Parcialmente Pago" : "TOTALMENTE PENDENTE");

                    reports.add(new EnterpriseBillingReport(
                        c.id, c.name, String.valueOf(inv.invoiceId), inv.total, paid, balance, method, status
                    ));
                }
            }
            if (!hasInvoice) {
                // Cliente recém-cadastrado sem faturas:
                reports.add(new EnterpriseBillingReport(
                    c.id, c.name, "Sem Faturas", 0.0, 0.0, 0.0, "N/A", "Sem Débitos"
                ));
            }
        }

        // --------------------------------------------------------------------------------------
        // EXIBIÇÃO DA PROJEÇÃO DO RELATÓRIO EXECUTIVO
        // --------------------------------------------------------------------------------------
        System.out.println("+----+----------------------+-------------+--------------+------------+--------------+-----------------+-----------------------+");
        System.out.println("| ID | Cliente              | Fatura      | Total Fatura | Valor Pago | Saldo Devedor| Forma Pagamento | Situação Financeira   |");
        System.out.println("+----+----------------------+-------------+--------------+------------+--------------+-----------------+-----------------------+");

        for (EnterpriseBillingReport r : reports) {
            System.out.printf("| %-2d | %-20s | %-11s | $%,11.2f | $%,9.2f | $%,11.2f | %-15s | %-21s |\n",
                    r.clientId, r.clientName, r.invoiceId, r.invoiceTotal, r.amountPaid, r.balanceDue, r.paymentMethod, r.status);
        }
        System.out.println("+----+----------------------+-------------+--------------+------------+--------------+-----------------+-----------------------+");

        System.out.println("\n🔍 OBSERVAÇÕES DE ENGENHARIA:");
        System.out.println("1. Cliente 2 (Myworks): Fatura de $152,21 com $0,00 pagos -> Status: TOTALMENTE PENDENTE!");
        System.out.println("2. Cliente 6 (Acme Corp): Sem faturas -> Preservado pelo LEFT JOIN com Status: Sem Débitos!");
        System.out.println("3. Clientes 1, 3 e 4: Pagamentos associados e saldos remanescentes calculados com precisão!");

        System.out.println("\n=========================================================================================");
        System.out.println("   CAPÍTULO 24 CONCLUÍDO COM SUCESSO! VOCÊ É MESTRE EM JUNÇÕES EXTERNAS MULTI-TABELAS!");
        System.out.println("=========================================================================================");
    }
}
