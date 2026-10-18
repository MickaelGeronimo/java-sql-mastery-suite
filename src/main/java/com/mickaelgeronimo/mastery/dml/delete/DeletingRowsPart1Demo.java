package com.mickaelgeronimo.mastery.dml.delete;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 38 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Excluindo Linhas (Deleting Rows), Exclusão por Chave Primária,
 *       Exclusão com Subconsulta, Simulação do Safe Updates (Erro 1175) e Spring Data JPA
 * ==============================================================================
 */
public class DeletingRowsPart1Demo {

    // Simulação do Error 1175 do MySQL para DELETE sem Chave Primária
    public static class SafeDeleteException extends RuntimeException {
        public SafeDeleteException(String message) {
            super(message);
        }
    }

    public record Client(int id, String name) {}

    public static class Invoice {
        private final int invoiceId;
        private final String number;
        private final int clientId;
        private final BigDecimal invoiceTotal;

        public Invoice(int invoiceId, String number, int clientId, BigDecimal invoiceTotal) {
            this.invoiceId = invoiceId;
            this.number = number;
            this.clientId = clientId;
            this.invoiceTotal = invoiceTotal;
        }

        public int getInvoiceId() { return invoiceId; }
        public String getNumber() { return number; }
        public int getClientId() { return clientId; }
        public BigDecimal getInvoiceTotal() { return invoiceTotal; }

        @Override
        public String toString() {
            return String.format("| %10d | %-12s | %9d | %13s |", invoiceId, number, clientId, invoiceTotal);
        }
    }

    public static class InvoicingRepository {
        private final List<Client> clients = new ArrayList<>();
        private final List<Invoice> invoices = new ArrayList<>();
        private boolean safeUpdates = true;

        public void setSafeUpdates(boolean enabled) { this.safeUpdates = enabled; }
        public void addClient(Client c) { clients.add(c); }
        public void addInvoice(Invoice i) { invoices.add(i); }

        public List<Client> findAllClients() { return Collections.unmodifiableList(clients); }
        public List<Invoice> findAllInvoices() { return Collections.unmodifiableList(invoices); }

        // 1. Exclusão cirúrgica por Chave Primária (Permitida mesmo com Safe Updates = true)
        public boolean deleteById(int invoiceId) {
            return invoices.removeIf(i -> i.getInvoiceId() == invoiceId);
        }

        // 2. Exclusão por coluna não-chave (Bloqueada se Safe Updates = true)
        public int deleteByClientId(int clientId) {
            if (safeUpdates) {
                throw new SafeDeleteException(
                    "Error Code: 1175. You are using safe update mode and you tried to delete " +
                    "from a table without a WHERE that uses a KEY column (invoice_id). " +
                    "Execute 'SET SQL_SAFE_UPDATES = 0;' to allow batch deletions."
                );
            }

            int sizeBefore = invoices.size();
            invoices.removeIf(i -> i.getClientId() == clientId);
            return sizeBefore - invoices.size();
        }

        // 3. Exclusão usando Subconsulta (Busca o ID do cliente pelo nome e apaga suas faturas)
        public int deleteInvoicesByClientName(String clientName) {
            Optional<Client> clientOpt = clients.stream()
                .filter(c -> c.name().equalsIgnoreCase(clientName))
                .findFirst();

            if (clientOpt.isEmpty()) return 0;

            int targetClientId = clientOpt.get().id();
            return deleteByClientId(targetClientId);
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 38 (PARTE 1) - EXCLUINDO LINHAS (DELETE FROM) & ERROR 1175                 ");
        System.out.println("==========================================================================================================\n");

        InvoicingRepository repo = new InvoicingRepository();

        // Clientes
        repo.addClient(new Client(1, "Vinte"));
        repo.addClient(new Client(2, "Myworks"));
        repo.addClient(new Client(3, "Yadel"));

        // Faturas
        repo.addInvoice(new Invoice(1, "91-953-3396", 1, new BigDecimal("101.79")));
        repo.addInvoice(new Invoice(2, "03-898-6735", 2, new BigDecimal("175.32"))); // Myworks
        repo.addInvoice(new Invoice(3, "20-295-2670", 3, new BigDecimal("147.99")));
        repo.addInvoice(new Invoice(4, "61-645-1249", 3, new BigDecimal("152.15")));

        System.out.println(">>> ESTADO INICIAL DA TABELA DE FATURAS:");
        printInvoices(repo.findAllInvoices());
        System.out.println("Total de faturas cadastradas: " + repo.findAllInvoices().size() + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: EXCLUSÃO DE UMA ÚNICA LINHA PELA CHAVE PRIMÁRIA
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. EXCLUSÃO POR CHAVE PRIMÁRIA (DELETE FROM invoices WHERE invoice_id = 1) ---");
        boolean deletedPk = repo.deleteById(1);
        System.out.println("-> Console MySQL: Query OK, 1 row affected (0.01 sec)");
        System.out.println("-> Registro de ID 1 removido com sucesso!");
        printInvoices(repo.findAllInvoices());
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: DISPARO DO ERRO 1175 AO TENTAR DELETAR SEM USAR A PK
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. TENTATIVA DE EXCLUSÃO COM SAFE UPDATES ATIVADO ---");
        System.out.println("SQL: DELETE FROM invoices WHERE client_id = 2;");

        try {
            repo.deleteByClientId(2);
        } catch (SafeDeleteException ex) {
            System.out.println("-> RESPOSTA DO MOTOR DE BANCO DE DADOS:");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> EXPLICAÇÃO: O MySQL Workbench protege a tabela contra deleções massivas acidentais!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: EXCLUSÃO COM SUBCONSULTA (EXEMPLO DO MOSH: CLIENTE 'Myworks')
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. EXCLUSÃO COM SUBCONSULTA: DESATIVANDO SAFE UPDATES ---");
        System.out.println("SQL: SET SQL_SAFE_UPDATES = 0;");
        System.out.println("SQL: DELETE FROM invoices WHERE client_id = (SELECT client_id FROM clients WHERE name = 'Myworks');");
        System.out.println("SQL: SET SQL_SAFE_UPDATES = 1;\n");

        repo.setSafeUpdates(false);
        int rowsDeletedSubquery = repo.deleteInvoicesByClientName("Myworks");
        repo.setSafeUpdates(true);

        System.out.println("-> Console MySQL: Query OK, " + rowsDeletedSubquery + " row affected (0.01 sec)");
        System.out.println("-> Todas as faturas da cliente 'Myworks' foram apagadas!");

        System.out.println("\n>>> TABELA DE FATURAS APÓS AS DELEÇÕES:");
        printInvoices(repo.findAllInvoices());

        System.out.println("\n==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 38 (PARTE 1) CONCLUÍDA COM 100% DE ÊXITO!                                     ");
        System.out.println("==========================================================================================================");
    }

    private static void printInvoices(List<Invoice> list) {
        System.out.println("+------------+--------------+-----------+---------------+");
        System.out.println("| invoice_id | number       | client_id | invoice_total |");
        System.out.println("+------------+--------------+-----------+---------------+");
        for (Invoice inv : list) {
            System.out.println(inv);
        }
        System.out.println("+------------+--------------+-----------+---------------+");
    }
}
