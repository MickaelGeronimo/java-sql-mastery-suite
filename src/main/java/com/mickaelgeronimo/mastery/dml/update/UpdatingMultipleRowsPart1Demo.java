package com.mickaelgeronimo.mastery.dml.update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 36 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Atualizando Múltiplas Linhas (Updating Multiple Rows),
 *       Simulação do Erro 1175 (Safe Updates), Operador IN e Cuidado com JPA/Hibernate
 * ==============================================================================
 */
public class UpdatingMultipleRowsPart1Demo {

    // Exceção personalizada para simular o Error 1175 do MySQL Workbench
    public static class SafeUpdateException extends RuntimeException {
        public SafeUpdateException(String message) {
            super(message);
        }
    }

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
        public LocalDate getDueDate() { return dueDate; }
        public LocalDate getPaymentDate() { return paymentDate; }

        public void setPaymentTotal(BigDecimal paymentTotal) {
            this.paymentTotal = paymentTotal;
        }

        public void setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
        }

        @Override
        public String toString() {
            return String.format("| %10d | %-12s | %9d | %13s | %13s | %-10s | %-12s |",
                invoiceId, number, clientId, invoiceTotal, paymentTotal,
                dueDate.toString(),
                paymentDate == null ? "NULL" : paymentDate.toString());
        }
    }

    // Repositório simulando a tabela 'invoices' e as regras do motor de banco de dados
    public static class InvoiceDatabase {
        private final List<Invoice> table = new ArrayList<>();
        private boolean safeUpdates = true; // Por padrão, o Safe Updates vem ativado!

        public void setSafeUpdates(boolean enabled) {
            this.safeUpdates = enabled;
        }

        public void addInvoice(Invoice inv) {
            table.add(inv);
        }

        public List<Invoice> findAll() {
            return Collections.unmodifiableList(table);
        }

        public List<Invoice> findByClientId(int clientId) {
            return table.stream()
                .filter(i -> i.getClientId() == clientId)
                .collect(Collectors.toList());
        }

        /**
         * Simula a execução do comando:
         * UPDATE invoices SET payment_total = :paymentTotal, payment_date = due_date WHERE client_id = :clientId
         */
        public int updateByClientId(int clientId, BigDecimal newPayment) {
            // Se o modo de segurança estiver ativo e não filtramos pela Chave Primária (invoice_id)
            if (safeUpdates) {
                throw new SafeUpdateException(
                    "Error Code: 1175. You are using safe update mode and you tried to update " +
                    "a table without a WHERE that uses a KEY column (invoice_id). " +
                    "To disable safe mode, toggle the option or execute 'SET SQL_SAFE_UPDATES = 0;'."
                );
            }

            int rowsMatched = 0;
            for (Invoice inv : table) {
                if (inv.getClientId() == clientId) {
                    inv.setPaymentTotal(newPayment);
                    inv.setPaymentDate(inv.getDueDate()); // payment_date = due_date
                    rowsMatched++;
                }
            }
            return rowsMatched;
        }

        /**
         * Simula a execução do comando com operador IN:
         * UPDATE invoices SET payment_total = :paymentTotal, payment_date = due_date WHERE client_id IN (...)
         */
        public int updateByClientIdsIn(Set<Integer> clientIds, BigDecimal newPayment) {
            if (safeUpdates) {
                throw new SafeUpdateException("Error Code: 1175: Operação bloqueada pelo Safe Updates Mode!");
            }

            int rowsMatched = 0;
            for (Invoice inv : table) {
                if (clientIds.contains(inv.getClientId())) {
                    inv.setPaymentTotal(newPayment);
                    inv.setPaymentDate(inv.getDueDate());
                    rowsMatched++;
                }
            }
            return rowsMatched;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 36 (PARTE 1) - UPDATING MULTIPLE ROWS & ERROR 1175                 ");
        System.out.println("==================================================================================================\n");

        InvoiceDatabase db = new InvoiceDatabase();

        // Carregando dados fiéis ao banco 'sql_invoicing' do Mosh
        db.addInvoice(new Invoice(1, "91-953-3396", 1, new BigDecimal("101.79"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 9), LocalDate.of(2019, 3, 29), null));
        db.addInvoice(new Invoice(2, "03-898-6735", 2, new BigDecimal("175.32"), new BigDecimal("8.18"), LocalDate.of(2019, 6, 11), LocalDate.of(2019, 7, 1), LocalDate.of(2019, 2, 12)));
        db.addInvoice(new Invoice(3, "20-295-2670", 3, new BigDecimal("147.99"), new BigDecimal("0.00"), LocalDate.of(2019, 7, 29), LocalDate.of(2019, 8, 18), null));
        db.addInvoice(new Invoice(4, "61-645-1249", 3, new BigDecimal("152.15"), new BigDecimal("0.00"), LocalDate.of(2019, 1, 30), LocalDate.of(2019, 2, 19), null));
        db.addInvoice(new Invoice(5, "17-562-3155", 4, new BigDecimal("169.36"), new BigDecimal("0.00"), LocalDate.of(2019, 2, 12), LocalDate.of(2019, 3, 4), null));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: CONSULTA INICIAL DAS FATURAS DO CLIENTE 3
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CONSULTA PRÉVIA: FATURAS DO CLIENTE 3 (WHERE client_id = 3) ---");
        printTable(db.findByClientId(3));
        System.out.println("O Cliente 3 possui exatamente 2 faturas com payment_total = 0.00 e payment_date = NULL.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: DISPARO DO ERRO 1175 COM SAFE UPDATES ATIVADO
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. TENTATIVA DE UPDATE EM MASSA COM O MODO SEGURO ATIVADO (PADRÃO WORKBENCH) ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = 40, payment_date = due_date WHERE client_id = 3;");

        try {
            db.updateByClientId(3, new BigDecimal("40.00"));
        } catch (SafeUpdateException ex) {
            System.out.println("-> RESPOSTA DO MOTOR DE BANCO DE DADOS:");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> MOTIVO: client_id NÃO é a Chave Primária da tabela invoices!");
            System.out.println("   O banco de dados bloqueou para evitar modificações acidentais em lote.\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: DESATIVANDO SAFE UPDATES E EXECUTANDO A ATUALIZAÇÃO DO CLIENTE 3
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. DESATIVANDO O MODO SEGURO: SET SQL_SAFE_UPDATES = 0; ---");
        db.setSafeUpdates(false);

        int rowsUpdated = db.updateByClientId(3, new BigDecimal("40.00"));
        System.out.println("-> Executando UPDATE com sucesso!");
        System.out.println("-> Console MySQL: Query OK, " + rowsUpdated + " rows affected (0.01 sec)");
        System.out.println("   Rows matched: " + rowsUpdated + "  Changed: " + rowsUpdated + "  Warnings: 0\n");

        // Reativando a trava de segurança imediatamente por boa prática:
        db.setSafeUpdates(true);
        System.out.println("-> Reativando o modo seguro por segurança: SET SQL_SAFE_UPDATES = 1;\n");

        System.out.println(">>> FATURAS DO CLIENTE 3 APÓS O UPDATE EM MASSA:");
        printTable(db.findByClientId(3));
        System.out.println("Repare que payment_total agora é 40.00 e payment_date assumiu a due_date de cada linha!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: ATUALIZANDO MÚLTIPLOS CLIENTES COM OPERADOR 'IN (3, 4)'
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. ATUALIZANDO CLIENTES 3 E 4 COM O OPERADOR 'IN': client_id IN (3, 4) ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = 100, payment_date = due_date WHERE client_id IN (3, 4);");

        db.setSafeUpdates(false);
        int rowsUpdatedMultiple = db.updateByClientIdsIn(Set.of(3, 4), new BigDecimal("100.00"));
        db.setSafeUpdates(true);

        System.out.println("-> Console MySQL: Query OK, " + rowsUpdatedMultiple + " rows affected (0.02 sec)");
        System.out.println("   Rows matched: " + rowsUpdatedMultiple + "  Changed: " + rowsUpdatedMultiple + "  Warnings: 0\n");

        System.out.println(">>> FATURAS DOS CLIENTES 3 E 4 APÓS O UPDATE COM 'IN':");
        List<Invoice> clients3And4 = db.findAll().stream()
            .filter(i -> i.getClientId() == 3 || i.getClientId() == 4)
            .collect(Collectors.toList());
        printTable(clients3And4);

        System.out.println("==================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 36 (PARTE 1) CONCLUÍDA COM 100% DE ÊXITO!                             ");
        System.out.println("==================================================================================================");
    }

    private static void printTable(List<Invoice> list) {
        System.out.println("+------------+--------------+-----------+---------------+---------------+------------+--------------+");
        System.out.println("| invoice_id | number       | client_id | invoice_total | payment_total | due_date   | payment_date |");
        System.out.println("+------------+--------------+-----------+---------------+---------------+------------+--------------+");
        for (Invoice inv : list) {
            System.out.println(inv);
        }
        System.out.println("+------------+--------------+-----------+---------------+---------------+------------+--------------+");
    }
}
