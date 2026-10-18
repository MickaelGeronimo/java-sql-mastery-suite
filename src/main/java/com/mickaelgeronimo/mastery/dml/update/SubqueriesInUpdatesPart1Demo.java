package com.mickaelgeronimo.mastery.dml.update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 37 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Usando Subconsultas em Atualizações (Using Subqueries in Updates),
 *       Subconsultas Escalares (=), Simulação do Erro 1242 e o Operador IN
 * ==============================================================================
 */
public class SubqueriesInUpdatesPart1Demo {

    // Simulação do erro nativo ERROR 1242 (21000) do MySQL
    public static class SubqueryMultiRowException extends RuntimeException {
        public SubqueryMultiRowException(String message) {
            super(message);
        }
    }

    public record Client(int id, String name, String state) {}

    public static class Invoice {
        private final int invoiceId;
        private final String number;
        private final int clientId;
        private final BigDecimal invoiceTotal;
        private BigDecimal paymentTotal;
        private final LocalDate dueDate;
        private LocalDate paymentDate;

        public Invoice(int invoiceId, String number, int clientId, BigDecimal invoiceTotal,
                       BigDecimal paymentTotal, LocalDate dueDate, LocalDate paymentDate) {
            this.invoiceId = invoiceId;
            this.number = number;
            this.clientId = clientId;
            this.invoiceTotal = invoiceTotal;
            this.paymentTotal = paymentTotal;
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

        public void setPaymentTotal(BigDecimal paymentTotal) { this.paymentTotal = paymentTotal; }
        public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

        @Override
        public String toString() {
            return String.format("| %10d | %-12s | %9d | %13s | %13s | %-10s | %-12s |",
                invoiceId, number, clientId, invoiceTotal, paymentTotal,
                dueDate.toString(),
                paymentDate == null ? "NULL" : paymentDate.toString());
        }
    }

    public static class InvoicingService {
        private final List<Client> clientsTable = new ArrayList<>();
        private final List<Invoice> invoicesTable = new ArrayList<>();

        public void addClient(Client c) { clientsTable.add(c); }
        public void addInvoice(Invoice i) { invoicesTable.add(i); }

        public List<Client> getClients() { return Collections.unmodifiableList(clientsTable); }
        public List<Invoice> getInvoices() { return Collections.unmodifiableList(invoicesTable); }

        // 1. Subconsulta para buscar ID por Nome
        public List<Integer> findClientIdsByName(String name) {
            return clientsTable.stream()
                .filter(c -> c.name().equalsIgnoreCase(name))
                .map(Client::id)
                .collect(Collectors.toList());
        }

        // 2. Subconsulta para buscar IDs por Estados
        public List<Integer> findClientIdsByStates(Set<String> states) {
            return clientsTable.stream()
                .filter(c -> states.contains(c.state()))
                .map(Client::id)
                .collect(Collectors.toList());
        }

        // 3. Atualização Escalar com '=' (Lança exceção se a subconsulta retornar > 1 linha)
        public int updateInvoicesScalarSubquery(List<Integer> subqueryResult, BigDecimal newPayment) {
            if (subqueryResult.size() > 1) {
                throw new SubqueryMultiRowException(
                    "ERROR 1242 (21000): Subquery returns more than 1 row. " +
                    "O operador '=' espera 1 único valor, mas a subconsulta retornou " + subqueryResult.size() + " IDs!"
                );
            }
            if (subqueryResult.isEmpty()) {
                return 0;
            }

            int targetId = subqueryResult.get(0);
            int updated = 0;
            for (Invoice inv : invoicesTable) {
                if (inv.getClientId() == targetId) {
                    inv.setPaymentTotal(newPayment);
                    inv.setPaymentDate(inv.getDueDate());
                    updated++;
                }
            }
            return updated;
        }

        // 4. Atualização Multi-valorada com 'IN' (Suporta zero, uma ou múltiplas linhas)
        public int updateInvoicesInSubquery(List<Integer> subqueryResult, BigDecimal newPayment) {
            Set<Integer> targetIds = new HashSet<>(subqueryResult);
            int updated = 0;
            for (Invoice inv : invoicesTable) {
                if (targetIds.contains(inv.getClientId())) {
                    inv.setPaymentTotal(newPayment);
                    inv.setPaymentDate(inv.getDueDate());
                    updated++;
                }
            }
            return updated;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 37 (PARTE 1) - SUBCONSULTAS EM UPDATES & ERROR 1242                        ");
        System.out.println("==========================================================================================================\n");

        InvoicingService service = new InvoicingService();

        // 5 Clientes oficiais do Mosh
        service.addClient(new Client(1, "Vinte", "NY"));
        service.addClient(new Client(2, "Myworks", "NY"));
        service.addClient(new Client(3, "Yadel", "CA"));
        service.addClient(new Client(4, "Kwamlat", "FL"));
        service.addClient(new Client(5, "Topiclounge", "OR"));

        // Faturas oficiais
        service.addInvoice(new Invoice(1, "91-953-3396", 1, new BigDecimal("101.79"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 29), null));
        service.addInvoice(new Invoice(2, "03-898-6735", 2, new BigDecimal("175.32"), new BigDecimal("8.18"), LocalDate.of(2019, 7, 1), LocalDate.of(2019, 2, 12)));
        service.addInvoice(new Invoice(3, "20-295-2670", 3, new BigDecimal("147.99"), new BigDecimal("0.00"), LocalDate.of(2019, 8, 18), null));
        service.addInvoice(new Invoice(4, "61-645-1249", 3, new BigDecimal("152.15"), new BigDecimal("0.00"), LocalDate.of(2019, 2, 19), null));
        service.addInvoice(new Invoice(5, "17-562-3155", 4, new BigDecimal("169.36"), new BigDecimal("0.00"), LocalDate.of(2019, 3, 4), null));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: SUBCONSULTA ESCALAR (=) COM 'Myworks'
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. EXECUTANDO A SUBCONSULTA ISOLADA (REGRA DE OURO DO MOSH) ---");
        System.out.println("SQL Subquery: SELECT client_id FROM clients WHERE name = 'Myworks';");
        List<Integer> myworksIds = service.findClientIdsByName("Myworks");
        System.out.println("-> Resultado da Subconsulta: client_id = " + myworksIds.get(0) + " (1 único valor escalar)\n");

        System.out.println("--- 2. EXECUTANDO O UPDATE COM SUBCONSULTA ESCALAR (=) ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = 50, payment_date = due_date WHERE client_id = (SELECT client_id FROM clients WHERE name = 'Myworks');");

        int rowsUpdatedScalar = service.updateInvoicesScalarSubquery(myworksIds, new BigDecimal("50.00"));
        System.out.println("-> Console MySQL: Query OK, " + rowsUpdatedScalar + " row affected (0.01 sec)");
        System.out.println("   Rows matched: " + rowsUpdatedScalar + "  Changed: " + rowsUpdatedScalar + "  Warnings: 0\n");

        System.out.println(">>> FATURA DA 'Myworks' APÓS O UPDATE:");
        printInvoices(service.getInvoices().stream().filter(i -> i.getClientId() == 2).collect(Collectors.toList()));
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: DISPARO DO ERRO 1242 AO USAR '=' COM SUBCONSULTA MULTI-VALORADA
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. TENTATIVA COM OPERADOR '=' E SUBCONSULTA QUE RETORNA MÚLTIPLAS LINHAS ---");
        System.out.println("SQL Subquery: SELECT client_id FROM clients WHERE state IN ('CA', 'NY');");
        List<Integer> caNyIds = service.findClientIdsByStates(Set.of("CA", "NY"));
        System.out.println("-> A subconsulta retornou " + caNyIds.size() + " IDs: " + caNyIds);

        System.out.println("SQL: UPDATE invoices SET payment_total = 50, payment_date = due_date WHERE client_id = (SELECT client_id FROM clients WHERE state IN ('CA', 'NY'));");
        try {
            service.updateInvoicesScalarSubquery(caNyIds, new BigDecimal("50.00"));
        } catch (SubqueryMultiRowException ex) {
            System.out.println("-> RESPOSTA DO MOTOR DE BANCO DE DADOS:");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> EXPLICAÇÃO: O '=' não consegue comparar um número com uma lista de números!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: CORREÇÃO COM OPERADOR 'IN'
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. CORREÇÃO DEFINITIVA COM O OPERADOR 'IN' ---");
        System.out.println("SQL: UPDATE invoices SET payment_total = 50, payment_date = due_date WHERE client_id IN (SELECT client_id FROM clients WHERE state IN ('CA', 'NY'));");

        int rowsUpdatedIn = service.updateInvoicesInSubquery(caNyIds, new BigDecimal("50.00"));
        System.out.println("-> Console MySQL: Query OK, " + rowsUpdatedIn + " rows affected (0.01 sec)");
        System.out.println("   Rows matched: " + rowsUpdatedIn + "  Changed: " + rowsUpdatedIn + "  Warnings: 0\n");

        System.out.println(">>> FATURAS DE TODOS OS CLIENTES DOS ESTADOS 'CA' E 'NY' ATUALIZADAS:");
        Set<Integer> targetSet = new HashSet<>(caNyIds);
        printInvoices(service.getInvoices().stream().filter(i -> targetSet.contains(i.getClientId())).collect(Collectors.toList()));

        System.out.println("\n==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 37 (PARTE 1) CONCLUÍDA COM 100% DE ÊXITO!                                     ");
        System.out.println("==========================================================================================================");
    }

    private static void printInvoices(List<Invoice> list) {
        System.out.println("+------------+--------------+-----------+---------------+---------------+------------+--------------+");
        System.out.println("| invoice_id | number       | client_id | invoice_total | payment_total | due_date   | payment_date |");
        System.out.println("+------------+--------------+-----------+---------------+---------------+------------+--------------+");
        for (Invoice inv : list) {
            System.out.println(inv);
        }
        System.out.println("+------------+--------------+-----------+---------------+---------------+------------+--------------+");
    }
}
