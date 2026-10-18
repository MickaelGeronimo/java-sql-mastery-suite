package com.mickaelgeronimo.mastery.dml.update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 35 (PARTE 3 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Stored Procedures de Pagamento, Prevenção de Overpayment (SIGNAL SQLSTATE),
 *       Simulação do UPDATE RETURNING e o Dicionário Supremo do UPDATE
 * ==============================================================================
 */
public class UpdatingSingleRowPart3FinalMasterDemo {

    // Exceção de Regra de Negócio Financeira
    public static class OverpaymentException extends RuntimeException {
        public OverpaymentException(String message) {
            super(message);
        }
    }

    public static class Invoice {
        private final int invoiceId;
        private final String number;
        private final BigDecimal invoiceTotal;
        private BigDecimal paymentTotal;
        private LocalDate paymentDate;

        public Invoice(int invoiceId, String number, BigDecimal invoiceTotal, BigDecimal paymentTotal) {
            this.invoiceId = invoiceId;
            this.number = number;
            this.invoiceTotal = invoiceTotal;
            this.paymentTotal = paymentTotal;
        }

        public int getInvoiceId() { return invoiceId; }
        public String getNumber() { return number; }
        public BigDecimal getInvoiceTotal() { return invoiceTotal; }
        public BigDecimal getPaymentTotal() { return paymentTotal; }
        public LocalDate getPaymentDate() { return paymentDate; }

        /**
         * Método com regra de negócio blindada (Domain-Driven Design):
         * Simula a Stored Procedure 'sp_make_payment' do banco de dados.
         */
        public BigDecimal registerPayment(BigDecimal amount) {
            BigDecimal newTotal = this.paymentTotal.add(amount);

            // Validação de Overpayment (Equivalente ao SIGNAL SQLSTATE '45000' do MySQL)
            if (newTotal.compareTo(this.invoiceTotal) > 0) {
                throw new OverpaymentException(
                    "ERROR 1644 (45000): Erro Financeiro: O valor pago (R$ " + newTotal + 
                    ") ultrapassa o total da fatura (R$ " + this.invoiceTotal + ")!"
                );
            }

            this.paymentTotal = newTotal;
            this.paymentDate = LocalDate.now();
            return this.invoiceTotal.subtract(this.paymentTotal); // Saldo devedor restante (OUT)
        }

        @Override
        public String toString() {
            return String.format("| %10d | %-12s | %13s | %13s | %-12s |",
                invoiceId, number, invoiceTotal, paymentTotal,
                paymentDate == null ? "NULL" : paymentDate.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 35 (PARTE 3 FINAL) - OVERPAYMENT & UPDATE RETURNING       ");
        System.out.println("=========================================================================================\n");

        Invoice inv1 = new Invoice(1, "91-953-3396", new BigDecimal("101.79"), new BigDecimal("30.00"));

        System.out.println(">>> ESTADO INICIAL DA FATURA 1:");
        printInvoice(inv1);
        System.out.println("Saldo Devedor Atual: R$ " + inv1.getInvoiceTotal().subtract(inv1.getPaymentTotal()) + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: PAGAMENTO VÁLIDO DE R$ 15.00 VIA STORED PROCEDURE
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. EXECUTANDO PAGAMENTO VÁLIDO VIA STORED PROCEDURE 'sp_make_payment' ---");
        System.out.println("SQL: CALL sp_make_payment(1, 15.00, @saldo_restante);");

        BigDecimal saldoRestante = inv1.registerPayment(new BigDecimal("15.00"));

        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("-> Parâmetro OUT retornado (@saldo_restante): R$ " + saldoRestante + "\n");

        System.out.println(">>> FATURA 1 ATUALIZADA APÓS O PAGAMENTO:");
        printInvoice(inv1);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: TENTATIVA DE OVERPAYMENT (PAGAMENTO A MAIOR BLOQUEADO)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 2. TENTATIVA DE OVERPAYMENT (PAGAMENTO A MAIOR DE R$ 500.00) ---");
        System.out.println("SQL: CALL sp_make_payment(1, 500.00, @saldo_restante);");

        try {
            inv1.registerPayment(new BigDecimal("500.00"));
        } catch (OverpaymentException ex) {
            System.out.println("-> CAPTURADA EXCEÇÃO DE NEGÓCIO NO BACKEND JAVA:");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> O MySQL barrou a transação via SIGNAL SQLSTATE '45000'!");
            System.out.println("-> O saldo permanece 100% INTACTO e protegido contra fraude ou erro operacional!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A CLÁUSULA MODERNA 'UPDATE ... RETURNING' (POSTGRESQL 18)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. O PADRÃO MODERNO: UPDATE ... RETURNING ---");
        System.out.println("No PostgreSQL 18 e MariaDB, a query atualiza e devolve o saldo na MESMA INSTRUÇÃO:");
        System.out.println("UPDATE invoices");
        System.out.println("SET payment_total = payment_total + 15.00, payment_date = CURRENT_DATE");
        System.out.println("WHERE invoice_id = 1");
        System.out.println("RETURNING invoice_id, payment_total, (invoice_total - payment_total) AS saldo_devedor_restante;\n");

        System.out.println("+------------+---------------+------------------------+");
        System.out.println("| invoice_id | payment_total | saldo_devedor_restante |");
        System.out.println("+------------+---------------+------------------------+");
        System.out.printf("| %10d | %13s | %22s |%n", inv1.getInvoiceId(), inv1.getPaymentTotal(), saldoRestante);
        System.out.println("+------------+---------------+------------------------+");

        System.out.println("\nDemostração de encerramento do Capítulo 35 concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }

    private static void printInvoice(Invoice inv) {
        System.out.println("+------------+--------------+---------------+---------------+--------------+");
        System.out.println("| invoice_id | number       | invoice_total | payment_total | payment_date |");
        System.out.println("+------------+--------------+---------------+---------------+--------------+");
        System.out.println(inv);
        System.out.println("+------------+--------------+---------------+---------------+--------------+");
    }
}
