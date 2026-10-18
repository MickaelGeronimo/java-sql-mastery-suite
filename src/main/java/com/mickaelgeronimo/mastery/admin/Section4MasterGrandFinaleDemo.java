package com.mickaelgeronimo.mastery.admin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 39 (PARTE 2 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: O Grande Fechamento da Seção 4 (Inserting, Updating, and Deleting Data)
 *       Multi-Database Reset, Auditoria Cruzada e o Super Dicionário Consolidado
 * ==============================================================================
 */
public class Section4MasterGrandFinaleDemo {

    public record Customer(int id, String firstName, int points) {}
    public static class Order {
        private final int id;
        private final int customerId;
        private String comments;

        public Order(int id, int customerId, String comments) {
            this.id = id;
            this.customerId = customerId;
            this.comments = comments;
        }

        public int getId() { return id; }
        public int getCustomerId() { return customerId; }
        public String getComments() { return comments; }
        public void setComments(String comments) { this.comments = comments; }

        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-16s |", id, customerId, comments == null ? "NULL" : comments);
        }
    }

    public record Client(int id, String name) {}
    public static class Invoice {
        private final int id;
        private final int clientId;
        private final BigDecimal total;
        private BigDecimal paid;

        public Invoice(int id, int clientId, BigDecimal total, BigDecimal paid) {
            this.id = id;
            this.clientId = clientId;
            this.total = total;
            this.paid = paid;
        }

        public int getId() { return id; }
        public int getClientId() { return clientId; }
        public BigDecimal getTotal() { return total; }
        public BigDecimal getPaid() { return paid; }
        public void setPaid(BigDecimal paid) { this.paid = paid; }

        @Override
        public String toString() {
            return String.format("| %10d | %9d | %13s | %13s |", id, clientId, total, paid);
        }
    }

    public static class EnterpriseMultiDatabaseManager {
        // sql_store
        private final List<Customer> customers = new ArrayList<>();
        private final List<Order> orders = new ArrayList<>();

        // sql_invoicing
        private final List<Client> clients = new ArrayList<>();
        private final List<Invoice> invoices = new ArrayList<>();

        public void restoreAllDatabases() {
            customers.clear();
            orders.clear();
            clients.clear();
            invoices.clear();

            // 1. Restaurando 'sql_store'
            customers.add(new Customer(1, "Babara", 2273));
            customers.add(new Customer(2, "Ines", 947));
            customers.add(new Customer(3, "Freddi", 2967));
            customers.add(new Customer(4, "Ambur", 357));
            customers.add(new Customer(5, "Clemmie", 3675));
            customers.add(new Customer(6, "Elka", 3073));
            customers.add(new Customer(7, "Ilene", 1672));
            customers.add(new Customer(8, "Freddi", 994));
            customers.add(new Customer(9, "Ruth", 1018));
            customers.add(new Customer(10, "Levy", 796));

            orders.add(new Order(1, 6, null));
            orders.add(new Order(2, 7, null));
            orders.add(new Order(5, 5, null));
            orders.add(new Order(8, 5, null));
            orders.add(new Order(10, 6, null));

            // 2. Restaurando 'sql_invoicing'
            clients.add(new Client(1, "Vinte"));
            clients.add(new Client(2, "Myworks"));
            clients.add(new Client(3, "Yadel"));

            invoices.add(new Invoice(1, 1, new BigDecimal("101.79"), new BigDecimal("0.00")));
            invoices.add(new Invoice(2, 2, new BigDecimal("175.32"), new BigDecimal("8.18")));
            invoices.add(new Invoice(3, 3, new BigDecimal("147.99"), new BigDecimal("0.00")));
            invoices.add(new Invoice(4, 3, new BigDecimal("152.15"), new BigDecimal("0.00")));
            invoices.add(new Invoice(5, 4, new BigDecimal("169.36"), new BigDecimal("0.00")));
        }

        public List<Customer> getCustomers() { return customers; }
        public List<Order> getOrders() { return orders; }
        public List<Invoice> getInvoices() { return invoices; }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 39 (PARTE 2 FINAL SUPREMO) - FECHAMENTO GERAL DA SEÇÃO 4                    ");
        System.out.println("==========================================================================================================\n");

        EnterpriseMultiDatabaseManager manager = new EnterpriseMultiDatabaseManager();

        System.out.println("--- 1. DISPARANDO RESTAURAÇÃO COMPLETA MULTI-DATABASE (sql_store & sql_invoicing) ---");
        manager.restoreAllDatabases();
        System.out.println("-> Bancos 'sql_store' e 'sql_invoicing' re-criados com dados originais de fábrica!\n");

        // --------------------------------------------------------------------------------------
        // AUDITORIA 1: PONTOS DOS CLIENTES (sql_store.customers)
        // --------------------------------------------------------------------------------------
        System.out.println(">>> 2. AUDITORIA: CLIENTES DA LOJA VIRTUAL (sql_store.customers):");
        System.out.println("+-------------+--------------+--------+");
        System.out.println("| customer_id | first_name   | points |");
        System.out.println("+-------------+--------------+--------+");
        for (Customer c : manager.getCustomers()) {
            if (c.id() <= 6) {
                System.out.printf("| %11d | %-12s | %6d |%n", c.id(), c.firstName(), c.points());
            }
        }
        System.out.println("+-------------+--------------+--------+");
        System.out.println("Confirmado: Babara voltou a 2.273 pts e Clemmie a 3.675 pts (Bônus resetados!)\n");

        // --------------------------------------------------------------------------------------
        // AUDITORIA 2: COMENTÁRIOS DOS PEDIDOS (sql_store.orders)
        // --------------------------------------------------------------------------------------
        System.out.println(">>> 3. AUDITORIA: PEDIDOS DA LOJA VIRTUAL (sql_store.orders):");
        System.out.println("+----------+-------------+------------------+");
        System.out.println("| order_id | customer_id | comments         |");
        System.out.println("+----------+-------------+------------------+");
        for (Order o : manager.getOrders()) {
            System.out.println(o);
        }
        System.out.println("+----------+-------------+------------------+");
        System.out.println("Confirmado: Todos os comentários voltaram a ser NULL (Etiquetas 'Gold customer' limpas!)\n");

        // --------------------------------------------------------------------------------------
        // AUDITORIA 3: FATURAS FINANCEIRAS (sql_invoicing.invoices)
        // --------------------------------------------------------------------------------------
        System.out.println(">>> 4. AUDITORIA: FATURAS FINANCEIRAS (sql_invoicing.invoices):");
        System.out.println("+------------+-----------+---------------+---------------+");
        System.out.println("| invoice_id | client_id | invoice_total | payment_total |");
        System.out.println("+------------+-----------+---------------+---------------+");
        for (Invoice inv : manager.getInvoices()) {
            System.out.println(inv);
        }
        System.out.println("+------------+-----------+---------------+---------------+");
        System.out.println("Confirmado: Fatura 1 ressuscitada e Fatura 2 retornada a R$ 8.18!\n");

        System.out.println("==========================================================================================================");
        System.out.println("   SEÇÃO 4 (INSERTING, UPDATING, AND DELETING DATA) CONCLUÍDA COM 100% DE SUCESSO!                        ");
        System.out.println("   AMBIENTE TOTALMENTE HOMOLOGADO PARA A SEÇÃO 5: SUMMARIZING DATA (AGRUPAMENTO E AGREGAÇÕES)!           ");
        System.out.println("==========================================================================================================");
    }
}
