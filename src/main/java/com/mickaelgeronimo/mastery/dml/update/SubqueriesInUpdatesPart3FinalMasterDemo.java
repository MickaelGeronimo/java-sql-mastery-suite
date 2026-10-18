package com.mickaelgeronimo.mastery.dml.update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 37 (PARTE 3 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Subconsultas na Cláusula SET, Simulação do Erro 1093 do MySQL,
 *       Cálculo de Médias Correlacionadas e o Dicionário Supremo de Subqueries
 * ==============================================================================
 */
public class SubqueriesInUpdatesPart3FinalMasterDemo {

    // Simulação do erro nativo ERROR 1093 (HY000) do MySQL
    public static class MySQL1093TargetTableException extends RuntimeException {
        public MySQL1093TargetTableException(String message) {
            super(message);
        }
    }

    public static class Invoice {
        private final int invoiceId;
        private final int clientId;
        private final BigDecimal invoiceTotal;
        private BigDecimal paymentTotal;
        private LocalDate paymentDate;

        public Invoice(int invoiceId, int clientId, BigDecimal invoiceTotal, BigDecimal paymentTotal, LocalDate paymentDate) {
            this.invoiceId = invoiceId;
            this.clientId = clientId;
            this.invoiceTotal = invoiceTotal;
            this.paymentTotal = paymentTotal;
            this.paymentDate = paymentDate;
        }

        public int getInvoiceId() { return invoiceId; }
        public int getClientId() { return clientId; }
        public BigDecimal getInvoiceTotal() { return invoiceTotal; }
        public BigDecimal getPaymentTotal() { return paymentTotal; }
        public LocalDate getPaymentDate() { return paymentDate; }

        public void setPaymentTotal(BigDecimal paymentTotal) { this.paymentTotal = paymentTotal; }
        public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

        @Override
        public String toString() {
            return String.format("| %10d | %9d | %13s | %13s | %-12s |",
                invoiceId, clientId, invoiceTotal, paymentTotal,
                paymentDate == null ? "NULL" : paymentDate.toString());
        }
    }

    public static class InvoicingEngine {
        private final List<Invoice> table = new ArrayList<>();
        private boolean isWriteLocked = false;

        public void addInvoice(Invoice inv) { table.add(inv); }
        public List<Invoice> findAll() { return Collections.unmodifiableList(table); }

        /**
         * Simula a tentativa ingênua de ler da mesma tabela que está sofrendo UPDATE:
         * UPDATE invoices SET payment_total = (SELECT invoice_total FROM invoices WHERE invoice_id = 1) WHERE invoice_id = 3
         */
        public void naiveSameTableUpdate(int targetId, int sourceId) {
            // No MySQL, o início da transação de UPDATE bloqueia a tabela contra leituras diretas no mesmo contexto
            this.isWriteLocked = true;
            try {
                if (isWriteLocked) {
                    throw new MySQL1093TargetTableException(
                        "ERROR 1093 (HY000): You can't specify target table 'invoices' for update in FROM clause! " +
                        "O MySQL não permite consultar a mesma tabela que está sendo modificada sem uma Derived Table."
                    );
                }
            } finally {
                this.isWriteLocked = false;
            }
        }

        /**
         * Solução 1: Simula o uso da Tabela Derivada (Snapshot em memória):
         * (SELECT invoice_total FROM (SELECT * FROM invoices) AS tmp WHERE invoice_id = 1)
         */
        public void derivedTableUpdate(int targetId, int sourceId) {
            // Cria um snapshot desvinculado (Derived Table em memória)
            BigDecimal sourceValue = table.stream()
                .filter(i -> i.getInvoiceId() == sourceId)
                .map(Invoice::getInvoiceTotal)
                .findFirst()
                .orElse(BigDecimal.ZERO);

            // Aplica a alteração com segurança
            for (Invoice inv : table) {
                if (inv.getInvoiceId() == targetId) {
                    inv.setPaymentTotal(sourceValue);
                    break;
                }
            }
        }

        /**
         * Solução 2: Simula o 'UPDATE ... JOIN' com agregação correlacionada:
         * Atualiza faturas zeradas do cliente para a MÉDIA de faturas daquele cliente.
         */
        public int updateInvoicesWithClientAverage(int clientId) {
            // 1. Calcula a média do cliente agrupando dados
            OptionalDouble avg = table.stream()
                .filter(i -> i.getClientId() == clientId)
                .mapToDouble(i -> i.getInvoiceTotal().doubleValue())
                .average();

            if (avg.isEmpty()) return 0;

            BigDecimal clientAvg = BigDecimal.valueOf(avg.getAsDouble()).setScale(2, RoundingMode.HALF_UP);

            // 2. Aplica a média nas faturas com payment_total = 0 daquele cliente
            int rowsUpdated = 0;
            for (Invoice inv : table) {
                if (inv.getClientId() == clientId && inv.getPaymentTotal().compareTo(BigDecimal.ZERO) == 0) {
                    inv.setPaymentTotal(clientAvg);
                    inv.setPaymentDate(LocalDate.now());
                    rowsUpdated++;
                }
            }
            return rowsUpdated;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 37 (PARTE 3 FINAL) - SUBQUERIES NO SET & ERROR 1093                        ");
        System.out.println("==========================================================================================================\n");

        InvoicingEngine engine = new InvoicingEngine();

        // Carregando dados fiéis ao banco sql_invoicing
        engine.addInvoice(new Invoice(1, 1, new BigDecimal("101.79"), new BigDecimal("0.00"), null));
        engine.addInvoice(new Invoice(2, 2, new BigDecimal("175.32"), new BigDecimal("50.00"), LocalDate.of(2019, 7, 1)));
        engine.addInvoice(new Invoice(3, 3, new BigDecimal("147.99"), new BigDecimal("0.00"), null));
        engine.addInvoice(new Invoice(4, 3, new BigDecimal("152.15"), new BigDecimal("0.00"), null));
        engine.addInvoice(new Invoice(5, 4, new BigDecimal("169.36"), new BigDecimal("0.00"), null));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O DISPARO DO ERRO 1093 DO MYSQL
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. TENTATIVA INGÊNUA DE ATUALIZAR CONSULTANDO A MESMA TABELA NO SET ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = (SELECT invoice_total FROM invoices WHERE invoice_id = 1) WHERE invoice_id = 3;");

        try {
            engine.naiveSameTableUpdate(3, 1);
        } catch (MySQL1093TargetTableException ex) {
            System.out.println("-> RESPOSTA DO MOTOR DE BANCO DE DADOS:");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> EXPLICAÇÃO: O MySQL bloqueia a leitura direta da tabela que está sofrendo lock exclusivo de escrita!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A CORREÇÃO COM TABELA DERIVADA (DERIVED TABLE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A CORREÇÃO DE ARQUITETO: ENVELOPAMENTO COM TABELA DERIVADA (DERIVED TABLE) ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = (SELECT invoice_total FROM (SELECT invoice_total FROM invoices WHERE invoice_id = 1) AS sub) WHERE invoice_id = 3;");

        engine.derivedTableUpdate(3, 1);
        System.out.println("-> Executado com sucesso via tabela derivada!");
        System.out.println("-> Fatura 3 recebeu o valor de invoice_total da fatura 1 (R$ 101.79):");
        printInvoices(engine.findAll().stream().filter(i -> i.getInvoiceId() == 3).collect(Collectors.toList()));
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: SUBCONSULTA CORRELACIONADA COM MÉDIA VIA 'UPDATE ... JOIN'
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. SUBCONSULTA CORRELACIONADA COM MÉDIA DE FATURAS DO CLIENTE 3 ---");
        System.out.println("SQL: UPDATE invoices i JOIN (SELECT client_id, AVG(invoice_total) AS media FROM invoices GROUP BY client_id) m USING (client_id)");
        System.out.println("     SET i.payment_total = m.media, i.payment_date = CURDATE() WHERE i.payment_total = 0 AND i.client_id = 3;\n");

        int rowsUpdated = engine.updateInvoicesWithClientAverage(3);
        System.out.println("-> Linhas afetadas pelo cálculo de média correlacionada: " + rowsUpdated);
        System.out.println(">>> FATURAS DO CLIENTE 3 APÓS O CÁLCULO:");
        printInvoices(engine.findAll().stream().filter(i -> i.getClientId() == 3).collect(Collectors.toList()));

        System.out.println("\n==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 37 (PARTE 3 FINAL) CONCLUÍDA COM 100% DE ÊXITO!                               ");
        System.out.println("==========================================================================================================");
    }

    private static void printInvoices(List<Invoice> list) {
        System.out.println("+------------+-----------+---------------+---------------+--------------+");
        System.out.println("| invoice_id | client_id | invoice_total | payment_total | payment_date |");
        System.out.println("+------------+-----------+---------------+---------------+--------------+");
        for (Invoice inv : list) {
            System.out.println(inv);
        }
        System.out.println("+------------+-----------+---------------+---------------+--------------+");
    }
}
