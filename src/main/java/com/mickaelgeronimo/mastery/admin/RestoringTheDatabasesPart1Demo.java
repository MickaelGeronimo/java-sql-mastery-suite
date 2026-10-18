package com.mickaelgeronimo.mastery.admin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 39 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Restaurando os Bancos de Dados (Restoring the Databases),
 *       Simulação de Database Seeder / Flyway Migrations e Reset de Fábrica
 * ==============================================================================
 */
public class RestoringTheDatabasesPart1Demo {

    public record Client(int id, String name, String city, String state) {}

    public static class Invoice {
        private final int invoiceId;
        private final String number;
        private final int clientId;
        private final BigDecimal invoiceTotal;
        private BigDecimal paymentTotal;
        private final LocalDate invoiceDate;
        private final LocalDate dueDate;
        private LocalDate paymentDate;

        public Invoice(int invoiceId, String number, int clientId, BigDecimal invoiceTotal,
                       BigDecimal paymentTotal, LocalDate invoiceDate, LocalDate dueDate, LocalDate paymentDate) {
            this.invoiceId = invoiceId;
            this.number = number;
            this.clientId = clientId;
            this.invoiceTotal = invoiceTotal;
            this.paymentTotal = paymentTotal;
            this.invoiceDate = invoiceDate;
            this.dueDate = dueDate;
            this.paymentDate = paymentDate;
        }

        public int getInvoiceId() { return invoiceId; }
        public String getNumber() { return number; }
        public int getClientId() { return clientId; }
        public BigDecimal getInvoiceTotal() { return invoiceTotal; }
        public BigDecimal getPaymentTotal() { return paymentTotal; }
        public LocalDate getInvoiceDate() { return invoiceDate; }
        public LocalDate getDueDate() { return dueDate; }
        public LocalDate getPaymentDate() { return paymentDate; }

        public void setPaymentTotal(BigDecimal paymentTotal) { this.paymentTotal = paymentTotal; }
        public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

        @Override
        public String toString() {
            return String.format("| %10d | %-12s | %9d | %13s | %13s | %-12s |",
                invoiceId, number, clientId, invoiceTotal, paymentTotal,
                paymentDate == null ? "NULL" : paymentDate.toString());
        }
    }

    public static class DatabaseRestoreService {
        private final List<Client> clientsTable = new ArrayList<>();
        private final List<Invoice> invoicesTable = new ArrayList<>();

        public List<Client> getClients() { return Collections.unmodifiableList(clientsTable); }
        public List<Invoice> getInvoices() { return Collections.unmodifiableList(invoicesTable); }

        /**
         * Simula a execução do script 'create-databases.sql' do Mosh Hamedani:
         * Limpa todo o estado mutado e re-insere os dados de semente (seed data) originais.
         */
        public void restoreToFactoryPristineState() {
            clientsTable.clear();
            invoicesTable.clear();

            // 1. Recriando Clientes Originais (DDL + Seeding)
            clientsTable.add(new Client(1, "Vinte", "Syracuse", "NY"));
            clientsTable.add(new Client(2, "Myworks", "Huntington", "NY"));
            clientsTable.add(new Client(3, "Yadel", "San Francisco", "CA"));
            clientsTable.add(new Client(4, "Kwamlat", "Orlando", "FL"));
            clientsTable.add(new Client(5, "Topiclounge", "Portland", "OR"));

            // 2. Recriando Faturas Originais
            invoicesTable.add(new Invoice(1, "91-953-3396", 1, new BigDecimal("101.79"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 9), LocalDate.of(2019, 3, 29), null));
            invoicesTable.add(new Invoice(2, "03-898-6735", 2, new BigDecimal("175.32"), new BigDecimal("8.18"), LocalDate.of(2019, 6, 11), LocalDate.of(2019, 7, 1), LocalDate.of(2019, 2, 12)));
            invoicesTable.add(new Invoice(3, "20-295-2670", 3, new BigDecimal("147.99"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 29), LocalDate.of(2019, 8, 18), null));
            invoicesTable.add(new Invoice(4, "61-645-1249", 3, new BigDecimal("152.15"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 30), LocalDate.of(2019, 2, 19), null));
            invoicesTable.add(new Invoice(5, "17-562-3155", 4, new BigDecimal("169.36"), new BigDecimal("0.00"), LocalDate.of(2019, 2, 12), LocalDate.of(2019, 3, 4), null));
        }

        // Simulação de mutações feitas ao longo da Seção 4 (Dirty State)
        public void simulateDirtyState() {
            // Apaga fatura 1 (Capítulo 38)
            invoicesTable.removeIf(i -> i.getInvoiceId() == 1);
            // Altera pagamento da fatura 2 para 50.00 (Capítulo 37)
            for (Invoice i : invoicesTable) {
                if (i.getInvoiceId() == 2) {
                    i.setPaymentTotal(new BigDecimal("50.00"));
                    i.setPaymentDate(LocalDate.now());
                }
            }
            // Adiciona fatura espúria de teste
            invoicesTable.add(new Invoice(999, "TEST-DIRTY", 99, new BigDecimal("999.99"), BigDecimal.ZERO, LocalDate.now(), LocalDate.now(), null));
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 39 (PARTE 1) - RESTAURANDO OS BANCOS DE DADOS (DATABASE RESTORE)           ");
        System.out.println("==========================================================================================================\n");

        DatabaseRestoreService service = new DatabaseRestoreService();

        // 1. Inicializando e simulando as alterações da Seção 4
        service.restoreToFactoryPristineState();
        service.simulateDirtyState();

        System.out.println(">>> 1. ESTADO MODIFICADO / SUJO DA BASE (Após os exercícios dos Capítulos 30 a 38):");
        System.out.println("Repare: A fatura 1 está ausente (deletada), a fatura 2 foi alterada e existe uma fatura de teste 999:");
        printInvoices(service.getInvoices());
        System.out.println();

        // --------------------------------------------------------------------------------------
        // 2. DISPARANDO A RESTAURAÇÃO DE FÁBRICA (FACTORY RESET / SEEDING)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. EXECUTANDO A RESTAURAÇÃO: SCRIPT 'create-databases.sql' ---");
        System.out.println("SQL: DROP DATABASE IF EXISTS sql_invoicing;");
        System.out.println("SQL: CREATE DATABASE sql_invoicing;");
        System.out.println("SQL: [Recriando tabelas DDL e inserindo dados originais de fábrica...]\n");

        service.restoreToFactoryPristineState();

        System.out.println("-> Console MySQL: Query OK, 11 statements executed (0.05 sec)");
        System.out.println("-> Banco de dados 'sql_invoicing' 100% restaurado ao estado original de fábrica!\n");

        // --------------------------------------------------------------------------------------
        // 3. AUDITORIA PÓS-RESTAURAÇÃO
        // --------------------------------------------------------------------------------------
        System.out.println(">>> 3. TABELA DE FATURAS APÓS A RESTAURAÇÃO COMPLETA:");
        printInvoices(service.getInvoices());

        System.out.println("\nConstatações da Auditoria:");
        System.out.println("1. A fatura 1 ('91-953-3396') foi ressuscitada com payment_total = 0.00 e payment_date = NULL.");
        System.out.println("2. A fatura 2 ('03-898-6735') voltou ao saldo histórico de R$ 8.18.");
        System.out.println("3. A fatura temporária 999 foi completamente expurgada do sistema.");
        System.out.println("4. O ambiente está 100% pronto e higienizado para o início da Seção 5 (Summarizing Data)!\n");

        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 39 (PARTE 1) CONCLUÍDA COM 100% DE ÊXITO!                                     ");
        System.out.println("==========================================================================================================");
    }

    private static void printInvoices(List<Invoice> list) {
        System.out.println("+------------+--------------+-----------+---------------+---------------+--------------+");
        System.out.println("| invoice_id | number       | client_id | invoice_total | payment_total | payment_date |");
        System.out.println("+------------+--------------+-----------+---------------+---------------+--------------+");
        for (Invoice inv : list) {
            System.out.println(inv);
        }
        System.out.println("+------------+--------------+-----------+---------------+---------------+--------------+");
    }
}
