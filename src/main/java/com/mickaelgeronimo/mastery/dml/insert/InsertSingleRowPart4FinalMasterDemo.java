package com.mickaelgeronimo.mastery.dml.insert;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 31 (PARTE 4 FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Prepared Statements (Prevenção de SQL Injection), Padrão Builder (INSERT SET)
 *       e Gatilhos de Auditoria de Entidade (Before / After Insert)
 * ==============================================================================
 */
public class InsertSingleRowPart4FinalMasterDemo {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(15); // Próximo: 16

    // 1. Modelo de Domínio
    public record Customer(int id, String firstName, String lastName, String address, String city, String state, int points) {
        @Override
        public String toString() {
            return String.format("| %2d | %-10s | %-10s | %-22s | %-14s | %2s | %6d |",
                    id, firstName, lastName, address, city, state, points);
        }
    }

    // 2. Registro de Auditoria (Simulação de Trigger AFTER INSERT)
    public record AuditLog(int logId, String action, String details, Instant timestamp) {
        @Override
        public String toString() {
            return String.format("| %2d | %-12s | %-45s | %-24s |", logId, action, details, timestamp);
        }
    }

    private static final List<Customer> customerDatabase = new ArrayList<>();
    private static final List<AuditLog> auditTable = new ArrayList<>();
    private static final AtomicInteger LOG_ID_GEN = new AtomicInteger(0);

    // 3. Simulação de PreparedStatement com Binding Seguro de Parâmetros
    public static class PreparedStatementSimulator {
        public Customer execute(String first, String last, String addr, String city, String state, int pts) {
            // Gatilho BEFORE INSERT: Higienização de dados
            String cleanFirst = first.trim();
            String cleanLast = last.trim();
            String cleanState = state.trim().toUpperCase();

            int generatedId = ID_GENERATOR.incrementAndGet();
            Customer newCustomer = new Customer(generatedId, cleanFirst, cleanLast, addr, city, cleanState, pts);
            customerDatabase.add(newCustomer);

            // Gatilho AFTER INSERT: Auditoria automática
            auditTable.add(new AuditLog(
                    LOG_ID_GEN.incrementAndGet(),
                    "AFTER_INSERT",
                    "Customer created: " + cleanFirst + " " + cleanLast + " (ID " + generatedId + ")",
                    Instant.now()
            ));

            return newCustomer;
        }
    }

    // 4. Fluent Builder simulando a sintaxe INSERT INTO ... SET
    public static class CustomerBuilder {
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String state;
        private int points = 0;

        public CustomerBuilder setFirstName(String f) { this.firstName = f; return this; }
        public CustomerBuilder setLastName(String l) { this.lastName = l; return this; }
        public CustomerBuilder setAddress(String a) { this.address = a; return this; }
        public CustomerBuilder setCity(String c) { this.city = c; return this; }
        public CustomerBuilder setState(String s) { this.state = s; return this; }
        public CustomerBuilder setPoints(int p) { this.points = p; return this; }

        public Customer buildAndInsert() {
            PreparedStatementSimulator prep = new PreparedStatementSimulator();
            return prep.execute(firstName, lastName, address, city, state, points);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 31 (PARTE 4 FINAL) - PREPARED STATEMENTS E INSERT SET     ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: PREPARED STATEMENT COM BINDING SEGURO (PROTEÇÃO TOTAL SQL INJECTION)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. PREPARED STATEMENT (PREPARE stmt ... EXECUTE USING ?): SEGURANÇA MÁXIMA ---");
        System.out.println("SQL: PREPARE stmt FROM 'INSERT INTO customers (...) VALUES (?, ?, ?, ?, ?, ?)';");
        System.out.println("SQL: EXECUTE stmt USING 'Carlos', 'Moraes', 'Rua das Araucárias 100', 'Curitiba', 'PR', 350;");

        PreparedStatementSimulator prep = new PreparedStatementSimulator();
        Customer c1 = prep.execute("Carlos", "Moraes", "Rua das Araucárias 100", "Curitiba", "PR", 350);
        System.out.println("-> Linha inserida via PreparedStatement: " + c1 + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A SINTAXE INSERT ... SET (CHAVE = VALOR VIA BUILDER FLUENTE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SINTAXE INSERT ... SET (CHAVE=VALOR SIMULADA VIA PADRÃO BUILDER) ---");
        System.out.println("SQL: INSERT INTO customers");
        System.out.println("     SET first_name = 'Lucas', last_name = 'Macedo', address = 'Rua dos Pinhais 22',");
        System.out.println("         city = 'Porto Alegre', state = 'RS', points = 180;");

        Customer c2 = new CustomerBuilder()
                .setFirstName("Lucas")
                .setLastName("Macedo")
                .setAddress("Rua dos Pinhais 22")
                .setCity("Porto Alegre")
                .setState("RS")
                .setPoints(180)
                .buildAndInsert();

        System.out.println("-> Linha inserida via Sintaxe SET: " + c2 + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: TABELA DE CLIENTES ATUALIZADA
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. CLIENTES GRAVADOS NO BANCO ---");
        System.out.println("+----+------------+------------+------------------------+----------------+----+--------+");
        System.out.println("| id | first_name | last_name  | address                | city           | st | points |");
        System.out.println("+----+------------+------------+------------------------+----------------+----+--------+");
        for (Customer c : customerDatabase) {
            System.out.println(c);
        }
        System.out.println("+----+------------+------------+------------------------+----------------+----+--------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: TABELA DE AUDITORIA GERADA PELOS TRIGGERS (AFTER INSERT)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. TABELA DE AUDITORIA (DISPARADA POR TRIGGER AFTER INSERT) ---");
        System.out.println("+----+--------------+-----------------------------------------------+--------------------------+");
        System.out.println("| id | action       | details                                       | timestamp                |");
        System.out.println("+----+--------------+-----------------------------------------------+--------------------------+");
        for (AuditLog log : auditTable) {
            System.out.println(log);
        }
        System.out.println("+----+--------------+-----------------------------------------------+--------------------------+");
        System.out.println("\nCAPÍTULO 31 100% FINALIZADO! VOCÊ DOMINOU A INSERÇÃO DE LINHAS COM NOTA MÁXIMA!");
    }
}
