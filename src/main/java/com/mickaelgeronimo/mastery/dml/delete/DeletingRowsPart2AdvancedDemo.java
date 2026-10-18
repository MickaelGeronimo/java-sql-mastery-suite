package com.mickaelgeronimo.mastery.dml.delete;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 38 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Integridade Referencial, Simulação do Erro 1451 (Foreign Key Constraint),
 *       Ações ON DELETE CASCADE e o Padrão Corporativo de Soft Delete
 * ==============================================================================
 */
public class DeletingRowsPart2AdvancedDemo {

    // Simulação do erro nativo ERROR 1451 (23000) do MySQL
    public static class ForeignKeyConstraintException extends RuntimeException {
        public ForeignKeyConstraintException(String message) {
            super(message);
        }
    }

    public static class Client {
        private final int id;
        private final String name;
        private boolean deleted;
        private LocalDateTime deletedAt;

        public Client(int id, String name) {
            this.id = id;
            this.name = name;
            this.deleted = false;
            this.deletedAt = null;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public boolean isDeleted() { return deleted; }
        public LocalDateTime getDeletedAt() { return deletedAt; }

        public void softDelete() {
            this.deleted = true;
            this.deletedAt = LocalDateTime.now();
        }

        public void undelete() {
            this.deleted = false;
            this.deletedAt = null;
        }

        @Override
        public String toString() {
            String dateStr = deletedAt == null ? "NULL" : deletedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return String.format("| %9d | %-12s | %-10s | %-19s |", id, name, deleted ? "TRUE (Sim)" : "FALSE (Não)", dateStr);
        }
    }

    public static class Invoice {
        private final int invoiceId;
        private final int clientId;
        private final BigDecimal total;

        public Invoice(int invoiceId, int clientId, BigDecimal total) {
            this.invoiceId = invoiceId;
            this.clientId = clientId;
            this.total = total;
        }

        public int getInvoiceId() { return invoiceId; }
        public int getClientId() { return clientId; }
        public BigDecimal getTotal() { return total; }

        @Override
        public String toString() {
            return String.format("| %10d | %9d | %13s |", invoiceId, clientId, total);
        }
    }

    public static class EnterpriseRelationalDatabase {
        private final List<Client> clientsTable = new ArrayList<>();
        private final List<Invoice> invoicesTable = new ArrayList<>();

        public void addClient(Client c) { clientsTable.add(c); }
        public void addInvoice(Invoice i) { invoicesTable.add(i); }

        public List<Client> getClients() { return Collections.unmodifiableList(clientsTable); }
        public List<Invoice> getInvoices() { return Collections.unmodifiableList(invoicesTable); }

        // 1. Simulação do Hard Delete com ON DELETE RESTRICT (Dispara Erro 1451 se houver faturas filhas)
        public void hardDeleteClientRestrict(int clientId) {
            boolean hasChildInvoices = invoicesTable.stream().anyMatch(i -> i.getClientId() == clientId);
            if (hasChildInvoices) {
                throw new ForeignKeyConstraintException(
                    "ERROR 1451 (23000): Cannot delete or update a parent row: a foreign key constraint fails " +
                    "(`sql_invoicing`.`invoices`, CONSTRAINT `fk_invoices_clients` FOREIGN KEY (`client_id`) " +
                    "REFERENCES `clients` (`client_id`) ON DELETE RESTRICT ON UPDATE CASCADE). " +
                    "Existem faturas filhas associadas ao cliente de ID " + clientId + "!"
                );
            }
            clientsTable.removeIf(c -> c.getId() == clientId);
        }

        // 2. Simulação do Hard Delete com ON DELETE CASCADE (Apaga o pai e todas as faturas filhas)
        public int hardDeleteClientCascade(int clientId) {
            clientsTable.removeIf(c -> c.getId() == clientId);
            int invoicesBefore = invoicesTable.size();
            invoicesTable.removeIf(i -> i.getClientId() == clientId);
            return invoicesBefore - invoicesTable.size();
        }

        // 3. Padrão Soft Delete (Exclusão Lógica)
        public void softDeleteClient(int clientId) {
            clientsTable.stream()
                .filter(c -> c.getId() == clientId)
                .findFirst()
                .ifPresent(Client::softDelete);
        }

        // 4. Restauração (Undelete)
        public void undeleteClient(int clientId) {
            clientsTable.stream()
                .filter(c -> c.getId() == clientId)
                .findFirst()
                .ifPresent(Client::undelete);
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 38 (PARTE 2) - INTEGRIDADE REFERENCIAL, ERRO 1451 & SOFT DELETE            ");
        System.out.println("==========================================================================================================\n");

        EnterpriseRelationalDatabase db = new EnterpriseRelationalDatabase();

        // Cadastrando clientes
        db.addClient(new Client(1, "Vinte"));
        db.addClient(new Client(2, "Myworks"));
        db.addClient(new Client(3, "Yadel"));

        // Cadastrando faturas
        db.addInvoice(new Invoice(1, 1, new BigDecimal("101.79")));
        db.addInvoice(new Invoice(2, 2, new BigDecimal("175.32"))); // Fatura da Myworks
        db.addInvoice(new Invoice(3, 3, new BigDecimal("147.99")));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O DISPARO DO ERRO 1451 (ON DELETE RESTRICT)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. TENTATIVA DE HARD DELETE EM CLIENTE COM FATURAS (ON DELETE RESTRICT) ---");
        System.out.println("SQL: DELETE FROM clients WHERE client_id = 2;");

        try {
            db.hardDeleteClientRestrict(2);
        } catch (ForeignKeyConstraintException ex) {
            System.out.println("-> RESPOSTA DO MOTOR DE BANCO DE DADOS (InnoDB):");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> PROTEÇÃO GARANTIDA: A integridade do banco impediu que faturas ficassem sem cliente (órfãs)!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O PERIGO DO ON DELETE CASCADE
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. DEMONSTRAÇÃO DO COMPORTAMENTO DO 'ON DELETE CASCADE' ---");
        System.out.println("Se a Foreign Key estivesse com CASCADE, apagar o cliente 1 apagaria suas faturas filhas silenciosamente:");
        int childDeleted = db.hardDeleteClientCascade(1);
        System.out.println("-> Cliente 1 removido fisicamente e " + childDeleted + " fatura(s) filha(s) destruída(s) em cascata!");
        System.out.println("-> ATENÇÃO: Em sistemas financeiros, isso representa risco gravíssimo de perda contábil!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: O PADRÃO CORPORATIVO PROFISSIONAL (SOFT DELETE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A SOLUÇÃO CORPORATIVA: SOFT DELETE (EXCLUSÃO LÓGICA) ---");
        System.out.println("SQL: UPDATE clients SET is_deleted = TRUE, deleted_at = NOW() WHERE client_id = 2;\n");

        db.softDeleteClient(2);
        System.out.println("-> Cliente 2 ('Myworks') inativado logicamente com sucesso!");

        System.out.println("\n>>> VISÃO DA APLICAÇÃO (CLIENTES ATIVOS: WHERE is_deleted = FALSE):");
        List<Client> activeClients = db.getClients().stream()
            .filter(c -> !c.isDeleted())
            .collect(Collectors.toList());
        printClients(activeClients);

        System.out.println("\n>>> VISÃO DA AUDITORIA FISCAL (REGISTROS INATIVADOS: WHERE is_deleted = TRUE):");
        List<Client> deletedClients = db.getClients().stream()
            .filter(Client::isDeleted)
            .collect(Collectors.toList());
        printClients(deletedClients);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: RECUPERAÇÃO INSTANTÂNEA (UNDELETE)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 4. RESTAURAÇÃO IMEDIATA (UNDELETE LÓGICO) ---");
        System.out.println("SQL: UPDATE clients SET is_deleted = FALSE, deleted_at = NULL WHERE client_id = 2;");
        db.undeleteClient(2);

        System.out.println(">>> CLIENTES ATIVOS APÓS O UNDELETE:");
        printClients(db.getClients().stream().filter(c -> !c.isDeleted()).collect(Collectors.toList()));

        System.out.println("\n==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 38 (PARTE 2) CONCLUÍDA COM 100% DE ÊXITO!                                     ");
        System.out.println("==========================================================================================================");
    }

    private static void printClients(List<Client> list) {
        System.out.println("+-----------+--------------+------------+---------------------+");
        System.out.println("| client_id | name         | is_deleted | deleted_at          |");
        System.out.println("+-----------+--------------+------------+---------------------+");
        for (Client c : list) {
            System.out.println(c);
        }
        System.out.println("+-----------+--------------+------------+---------------------+");
    }
}
