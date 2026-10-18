package com.mickaelgeronimo.mastery.dml.update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ==============================================================================
 * CAPÍTULO 35 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Concorrência em Updates, O Problema do Lost Update,
 *       Bloqueio Pessimista vs Bloqueio Otimista (@Version) e Triggers de Auditoria
 * ==============================================================================
 */
public class UpdatingSingleRowPart2AdvancedDemo {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Exceção simulando OptimisticLockException do JPA
    public static class OptimisticLockException extends RuntimeException {
        public OptimisticLockException(String message) {
            super(message);
        }
    }

    // Entidade com suporte a Bloqueio Otimista (@Version)
    public static class InvoiceEntity {
        private final int id;
        private final String number;
        private BigDecimal paymentTotal;
        private int version;
        private final ReentrantLock lock = new ReentrantLock(); // Para simular FOR UPDATE

        public InvoiceEntity(int id, String number, BigDecimal paymentTotal, int version) {
            this.id = id;
            this.number = number;
            this.paymentTotal = paymentTotal;
            this.version = version;
        }

        public int getId() { return id; }
        public String getNumber() { return number; }
        public BigDecimal getPaymentTotal() { return paymentTotal; }
        public int getVersion() { return version; }

        public void lockForUpdate() { lock.lock(); }
        public void unlock() { lock.unlock(); }

        // Simulação do UPDATE com WHERE id = ? AND version = ?
        public synchronized boolean updateOptimistic(BigDecimal newPayment, int expectedVersion) {
            if (this.version != expectedVersion) {
                return false; // Rows matched: 0
            }
            this.paymentTotal = newPayment;
            this.version++; // version = version + 1
            return true;
        }

        @Override
        public String toString() {
            return String.format("| %8d | %-12s | %13s | %7d |", id, number, paymentTotal, version);
        }
    }

    public record AuditLog(int auditId, int invoiceId, BigDecimal oldVal, BigDecimal newVal, BigDecimal diff, String time) {
        @Override
        public String toString() {
            return String.format("| %8d | %10d | %17s | %17s | %10s | %-19s |",
                auditId, invoiceId, oldVal, newVal, diff, time);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 35 (PARTE 2) - CONCORRÊNCIA E BLOQUEIOS NO UPDATE         ");
        System.out.println("=========================================================================================\n");

        InvoiceEntity invoice1 = new InvoiceEntity(1, "91-953-3396", new BigDecimal("0.00"), 1);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O PROBLEMA DA ATUALIZAÇÃO PERDIDA (LOST UPDATE) SEM CONTROLE
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O PESADELO: THE LOST UPDATE PROBLEM (SEM CONTROLE DE CONCORRÊNCIA) ---");
        System.out.println("Usuário A lê a fatura 1: Saldo pago = R$ 0.00");
        System.out.println("Usuário B lê a fatura 1: Saldo pago = R$ 0.00");
        System.out.println("Usuário A paga R$ 10.00 e grava no banco.");
        System.out.println("Usuário B paga R$ 20.00 e grava no banco em seguida.");
        System.out.println("-> RESULTADO DESASTROSO: O pagamento do Usuário A de R$ 10.00 foi sobrescrito e sumiu!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: BLOQUEIO OTIMISTA COM @Version (A SOLUÇÃO CORPORATIVA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A SOLUÇÃO: BLOQUEIO OTIMISTA COM COLUNA 'version' ---");
        System.out.println("Estado Inicial da Fatura:");
        printInvoice(invoice1);

        System.out.println("\nUsuário A e Usuário B abrem a fatura na Versão 1 simultaneamente.");
        int versaoLidaPorA = invoice1.getVersion(); // 1
        int versaoLidaPorB = invoice1.getVersion(); // 1

        System.out.println("\nUsuário A executa seu update primeiro:");
        System.out.println("SQL: UPDATE invoices SET payment_total = 10.00, version = version + 1 WHERE invoice_id = 1 AND version = 1;");
        boolean sucessoA = invoice1.updateOptimistic(new BigDecimal("10.00"), versaoLidaPorA);
        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)  [Rows matched: 1  Changed: 1]");
        System.out.println("-> Versão da fatura incrementada para: " + invoice1.getVersion());

        System.out.println("\nUsuário B tenta executar seu update com a versão antiga (versão 1):");
        System.out.println("SQL: UPDATE invoices SET payment_total = 20.00, version = version + 1 WHERE invoice_id = 1 AND version = 1;");
        boolean sucessoB = invoice1.updateOptimistic(new BigDecimal("20.00"), versaoLidaPorB);

        if (!sucessoB) {
            System.out.println("-> MySQL Console: Query OK, 0 rows affected (0.00 sec)  [Rows matched: 0  Changed: 0]");
            System.out.println("-> ALERTA DE CONCORRÊNCIA DETECTADO: Disparando OptimisticLockException no Java!");
            System.out.println("-> O Hibernate impede a sobrescrita! Os R$ 10.00 do Usuário A estão 100% SALVOS!\n");
        }

        // Recuperação elegante pelo Usuário B (Reload + Retry)
        System.out.println("Tratamento da Exceção no Backend:");
        System.out.println("O sistema recarrega a fatura atualizada (Versão 2, saldo R$ 10.00) e soma os R$ 20.00 do Usuário B:");
        invoice1.updateOptimistic(invoice1.getPaymentTotal().add(new BigDecimal("20.00")), invoice1.getVersion());
        System.out.println("-> Novo saldo final correto consolidado: R$ 30.00 (Versão 3)!\n");
        printInvoice(invoice1);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: TRIGGER DE AUDITORIA (OLD vs NEW)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 3. TRIGGER DE AUDITORIA: REGISTRANDO O HISTÓRICO DE MUDANÇAS ---");
        List<AuditLog> auditTable = List.of(
            new AuditLog(1, 1, new BigDecimal("0.00"), new BigDecimal("10.00"), new BigDecimal("10.00"), LocalDateTime.now().minusMinutes(2).format(TIME_FORMAT)),
            new AuditLog(2, 1, new BigDecimal("10.00"), new BigDecimal("30.00"), new BigDecimal("20.00"), LocalDateTime.now().format(TIME_FORMAT))
        );

        System.out.println("+----------+------------+-------------------+-------------------+------------+---------------------+");
        System.out.println("| audit_id | invoice_id | old_payment_total | new_payment_total | difference | changed_at          |");
        System.out.println("+----------+------------+-------------------+-------------------+------------+---------------------+");
        auditTable.forEach(System.out::println);
        System.out.println("+----------+------------+-------------------+-------------------+------------+---------------------+");

        System.out.println("\nDemostração concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }

    private static void printInvoice(InvoiceEntity inv) {
        System.out.println("+----------+--------------+---------------+---------+");
        System.out.println("| order_id | number       | payment_total | version |");
        System.out.println("+----------+--------------+---------------+---------+");
        System.out.println(inv);
        System.out.println("+----------+--------------+---------------+---------+");
    }
}
