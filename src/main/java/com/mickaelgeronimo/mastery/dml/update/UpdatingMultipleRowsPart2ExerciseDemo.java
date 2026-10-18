package com.mickaelgeronimo.mastery.dml.update;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 36 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: O Exercício Oficial do Mosh — Bônus de 50 Pontos para Nascidos Antes de 1990,
 *       Auditoria Prévia e Posterior, Safe Updates e Proteção de Idempotência
 * ==============================================================================
 */
public class UpdatingMultipleRowsPart2ExerciseDemo {

    public static class Customer {
        private final int id;
        private final String firstName;
        private final String lastName;
        private final LocalDate birthDate;
        private int points;
        private boolean bonusApplied;

        public Customer(int id, String firstName, String lastName, LocalDate birthDate, int points) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.points = points;
            this.bonusApplied = false;
        }

        public int getId() { return id; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public LocalDate getBirthDate() { return birthDate; }
        public int getPoints() { return points; }
        public boolean isBonusApplied() { return bonusApplied; }

        public void addPoints(int delta) {
            this.points += delta;
            this.bonusApplied = true;
        }

        @Override
        public String toString() {
            String status = birthDate.isBefore(LocalDate.of(1990, 1, 1)) 
                ? "Beneficiado (+50)" 
                : "Inalterado (>= 1990)";
            return String.format("| %11d | %-12s | %-14s | %-10s | %6d | %-24s |",
                id, firstName, lastName, birthDate.toString(), points, status);
        }
    }

    public static void main(String[] args) {
        System.out.println("========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 36 (PARTE 2) - EXERCÍCIO OFICIAL DE BÔNUS (sql_store.customers)         ");
        System.out.println("========================================================================================================\n");

        // 1. CARREGAMENTO DOS 10 CLIENTES OFICIAIS DO BANCO DO MOSH (sql_store.customers)
        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara", "MacCaffrey", LocalDate.of(1986, 7, 17), 2273),
            new Customer(2, "Ines", "Brushfield", LocalDate.of(1986, 4, 13), 947),
            new Customer(3, "Freddi", "Boath", LocalDate.of(1985, 2, 7), 2967),
            new Customer(4, "Ambur", "Roseburgh", LocalDate.of(1974, 4, 14), 357),
            new Customer(5, "Clemmie", "Barden", LocalDate.of(1973, 11, 7), 3675),
            new Customer(6, "Elka", "Twiddell", LocalDate.of(1991, 9, 4), 3073), // 1991 (NÃO recebe)
            new Customer(7, "Ilene", "Dowson", LocalDate.of(1964, 8, 30), 1672),
            new Customer(8, "Freddi", "Deamer", LocalDate.of(1993, 2, 7), 994),   // 1993 (NÃO recebe)
            new Customer(9, "Ruth", "Rannigan", LocalDate.of(1969, 10, 15), 1018),
            new Customer(10, "Levy", "Mynett", LocalDate.of(1969, 10, 13), 796)
        );

        LocalDate cutoffDate = LocalDate.of(1990, 1, 1);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: AUDITORIA PRÉVIA DOS CLIENTES QUE ATENDEM AO PREDICADO
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. AUDITORIA PRÉVIA: CLIENTES NASCIDOS ANTES DE 1990 (WHERE birth_date < '1990-01-01') ---");
        List<Customer> eligibleBefore = customers.stream()
            .filter(c -> c.getBirthDate().isBefore(cutoffDate))
            .collect(Collectors.toList());

        printCustomerTable(eligibleBefore);
        System.out.println("Total de clientes elegíveis identificados: " + eligibleBefore.size() + " de 10 clientes.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: EXECUÇÃO DO UPDATE EM LOTE (SET points = points + 50)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. EXECUTANDO A RESOLUÇÃO OFICIAL DO MOSH NO BANCO DE DADOS ---");
        System.out.println("SQL: SET SQL_SAFE_UPDATES = 0;");
        System.out.println("SQL: UPDATE customers SET points = points + 50 WHERE birth_date < '1990-01-01';");
        System.out.println("SQL: SET SQL_SAFE_UPDATES = 1;\n");

        int rowsAffected = 0;
        for (Customer c : customers) {
            if (c.getBirthDate().isBefore(cutoffDate)) {
                c.addPoints(50);
                rowsAffected++;
            }
        }

        System.out.println("-> Console MySQL:");
        System.out.println("   Query OK, " + rowsAffected + " rows affected (0.01 sec)");
        System.out.println("   Rows matched: " + rowsAffected + "  Changed: " + rowsAffected + "  Warnings: 0\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: AUDITORIA PÓS-UPDATE DE TODA A TABELA CUSTOMERS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. AUDITORIA COMPLETA DE TODOS OS 10 CLIENTES APÓS O UPDATE ---");
        printCustomerTable(customers);
        System.out.println("Constatação:");
        System.out.println("1. Os 8 clientes nascidos antes de 1990 receberam exatamente 50 pontos extras.");
        System.out.println("2. Elka Twiddell (ID 6, 1991) permaneceu com 3073 pontos intactos.");
        System.out.println("3. Freddi Deamer (ID 8, 1993) permaneceu com 994 pontos intactos.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: O TESTE DE IDEMPOTÊNCIA CORPORATIVA (EVITANDO CRÉDITO DUPLO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. TESTE DE RESILIÊNCIA E IDEMPOTÊNCIA (PREVENÇÃO DE CRÉDITO DUPLO) ---");
        System.out.println("Tentativa de reprocessar o comando em lote acidentalmente:");

        int secondRunRows = 0;
        for (Customer c : customers) {
            // Em arquiteturas profissionais, usamos uma flag ou tabela de controle:
            if (c.getBirthDate().isBefore(cutoffDate) && !c.isBonusApplied()) {
                c.addPoints(50);
                secondRunRows++;
            }
        }

        System.out.println("-> Console MySQL (com cláusula de idempotência AND bonus_applied = FALSE):");
        System.out.println("   Query OK, " + secondRunRows + " rows affected (0.00 sec)");
        System.out.println("   Rows matched: " + secondRunRows + "  Changed: " + secondRunRows + "  Warnings: 0");
        System.out.println("-> Nenhum ponto extra foi creditado indevidamente! O sistema permanece 100% íntegro!\n");

        System.out.println("========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 36 (PARTE 2) CONCLUÍDA COM 100% DE ÊXITO!                                   ");
        System.out.println("========================================================================================================");
    }

    private static void printCustomerTable(List<Customer> list) {
        System.out.println("+-------------+--------------+----------------+------------+--------+--------------------------+");
        System.out.println("| customer_id | first_name   | last_name      | birth_date | points | status_campanha          |");
        System.out.println("+-------------+--------------+----------------+------------+--------+--------------------------+");
        for (Customer c : list) {
            System.out.println(c);
        }
        System.out.println("+-------------+--------------+----------------+------------+--------+--------------------------+");
    }
}
