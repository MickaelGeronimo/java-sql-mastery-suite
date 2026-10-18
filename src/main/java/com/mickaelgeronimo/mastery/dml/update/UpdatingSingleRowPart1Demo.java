package com.mickaelgeronimo.mastery.dml.update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 35 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Atualizando uma Única Linha (UPDATE ... SET ... WHERE),
 *       Expressões em Colunas, Rows Matched vs Changed e Safe Update Mode
 * ==============================================================================
 */
public class UpdatingSingleRowPart1Demo {

    public static class Invoice {
        private final int invoiceId;
        private final String number;
        private final int clientId;
        private final BigDecimal invoiceTotal;
        private BigDecimal paymentTotal;
        private LocalDate invoiceDate;
        private LocalDate dueDate;
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
            return String.format("| %10d | %-12s | %9d | %13s | %13s | %-12s | %-10s | %-12s |",
                invoiceId, number, clientId, invoiceTotal, paymentTotal,
                invoiceDate, dueDate, paymentDate == null ? "NULL" : paymentDate.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 35 (PARTE 1) - ATUALIZANDO UMA ÚNICA LINHA (UPDATE)       ");
        System.out.println("=========================================================================================\n");

        // Tabela invoices em memória
        Map<Integer, Invoice> invoicesTable = new LinkedHashMap<>();
        invoicesTable.put(1, new Invoice(1, "91-953-3396", 2, new BigDecimal("101.79"), new BigDecimal("0.00"),
            LocalDate.of(2019, 3, 9), LocalDate.of(2019, 3, 29), null));
        invoicesTable.put(3, new Invoice(3, "20-228-0335", 3, new BigDecimal("147.99"), new BigDecimal("0.00"),
            LocalDate.of(2019, 6, 11), LocalDate.of(2019, 7, 1), null));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: ATUALIZAÇÃO BÁSICA NA FATURA 1 (PAGAMENTO DE $10.00)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. ATUALIZANDO DUAS COLUNAS NA FATURA 1 ---");
        System.out.println("SQL: UPDATE invoices");
        System.out.println("     SET payment_total = 10, payment_date = '2019-03-01'");
        System.out.println("     WHERE invoice_id = 1;");

        Invoice inv1 = invoicesTable.get(1);
        inv1.setPaymentTotal(new BigDecimal("10.00"));
        inv1.setPaymentDate(LocalDate.of(2019, 3, 1));

        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("->                Rows matched: 1  Changed: 1  Warnings: 0\n");
        printInvoice(inv1);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O SEGREDO DO MYSQL - ROWS MATCHED vs CHANGED
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 2. EXECUTANDO O MESMO UPDATE COM OS MESMOS VALORES NOVAMENTE ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = 10, payment_date = '2019-03-01' WHERE invoice_id = 1;");
        System.out.println("-> MySQL Console: Query OK, 0 rows affected (0.00 sec)");
        System.out.println("->                Rows matched: 1  Changed: 0  Warnings: 0");
        System.out.println("💡 O MySQL é inteligente: ele encontrou a linha (matched: 1), mas não gravou no disco (changed: 0) porque os valores já eram idênticos!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: RESETANDO VALORES COM 0 E NULL
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. RESETANDO / CANCELANDO O PAGAMENTO COM NULL ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = 0, payment_date = NULL WHERE invoice_id = 1;");
        inv1.setPaymentTotal(new BigDecimal("0.00"));
        inv1.setPaymentDate(null);

        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("->                Rows matched: 1  Changed: 1  Warnings: 0\n");
        printInvoice(inv1);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: ATUALIZAÇÃO COM EXPRESSÃO MATEMÁTICA E OUTRA COLUNA (FATURA 3)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 4. ATUALIZANDO COM EXPRESSÃO (50% DO TOTAL) E DATA DE VENCIMENTO ---");
        System.out.println("SQL: UPDATE invoices");
        System.out.println("     SET payment_total = invoice_total * 0.5, payment_date = due_date");
        System.out.println("     WHERE invoice_id = 3;");

        Invoice inv3 = invoicesTable.get(3);
        BigDecimal metade = inv3.getInvoiceTotal().multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
        inv3.setPaymentTotal(metade);
        inv3.setPaymentDate(inv3.getDueDate()); // payment_date = due_date

        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("->                Rows matched: 1  Changed: 1  Warnings: 0\n");
        printInvoice(inv3);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 5: COMO O JAVA FAZ ISSO COM JPA / HIBERNATE (DIRTY CHECKING)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 5. COMO OS DESENVOLVEDORES JAVA TRATAM O UPDATE NO HIBERNATE ---");
        System.out.println("@Transactional");
        System.out.println("public void registrarPagamento(Long invoiceId) {");
        System.out.println("    Invoice inv = invoiceRepository.findById(invoiceId).orElseThrow();");
        System.out.println("    inv.setPaymentTotal(new BigDecimal(\"10.00\")); // Altera o objeto na memória");
        System.out.println("    inv.setPaymentDate(LocalDate.now());");
        System.out.println("    // O Hibernate detecta a alteração automaticamente (Dirty Checking)");
        System.out.println("    // e dispara o comando SQL UPDATE no commit sem você precisar escrever o SQL!");
        System.out.println("}");
        System.out.println("\nDemostração concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }

    private static void printInvoice(Invoice inv) {
        System.out.println("+------------+--------------+-----------+---------------+---------------+--------------+------------+--------------+");
        System.out.println("| invoice_id | number       | client_id | invoice_total | payment_total | invoice_date | due_date   | payment_date |");
        System.out.println("+------------+--------------+-----------+---------------+---------------+--------------+------------+--------------+");
        System.out.println(inv);
        System.out.println("+------------+--------------+-----------+---------------+---------------+--------------+------------+--------------+");
    }
}
