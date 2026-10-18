package com.mickaelgeronimo.mastery.dml.insert;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 31 (PARTE 3): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Transações ACID (Commit/Rollback), INSERT IGNORE, UPSERT (ON DUPLICATE KEY UPDATE)
 *       e Subqueries de Inserção Dinâmica no Java Moderno
 * ==============================================================================
 */
public class InsertSingleRowPart3MasterDemo {

    public static class Customer {
        private final int id;
        private final String name;
        private int points;

        public Customer(int id, String name, int points) {
            this.id = id;
            this.name = name;
            this.points = points;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public int getPoints() { return points; }
        public void addPoints(int extra) { this.points += extra; }

        @Override
        public String toString() {
            return String.format("| %2d | %-20s | %6d pts |", id, name, points);
        }
    }

    // Tabela permanente em memória (simulando armazenamento InnoDB)
    private static final Map<Integer, Customer> storage = new LinkedHashMap<>();

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 31 (PARTE 3) - TRANSAÇÕES, UPSERT E INSERT IGNORE         ");
        System.out.println("=========================================================================================\n");

        // Carga inicial de clientes
        storage.put(1, new Customer(1, "Babara MacCaffrey", 2273));
        storage.put(2, new Customer(2, "Ines Brushfield", 947));
        storage.put(3, new Customer(3, "Freddi Boagey", 2967));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: TRANSAÇÃO ACID COM ROLLBACK (DESFAZENDO A INSERÇÃO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. TRANSAÇÃO ACID: START TRANSACTION -> INSERT -> ROLLBACK ---");
        System.out.println("SQL: START TRANSACTION;");
        System.out.println("SQL: INSERT INTO customers VALUES (99, 'Lucas Fantasma', 500);");

        // Buffer temporário da transação
        Map<Integer, Customer> txBuffer = new HashMap<>(storage);
        txBuffer.put(99, new Customer(99, "Lucas Fantasma", 500));
        System.out.println("-> Linha gravada provisoriamente na transação: Lucas Fantasma (ID 99)");

        System.out.println("SQL: ROLLBACK; (Falha simulada no backend: desfazendo transação!)");
        txBuffer.clear(); // Desfaz o buffer
        System.out.println("-> Status do Armazenamento Permanente: O ID 99 NÃO existe! O banco permaneceu intacto.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: INSERT IGNORE (SILENCIANDO ERROS DE CHAVE PRIMÁRIA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. INSERT IGNORE: SUPRIMINDO DUPLICAÇÃO DE CHAVE PRIMÁRIA ---");
        System.out.println("Tentando inserir cliente com ID 1 (que já pertence a Babara)...");

        int attemptedId = 1;
        Customer duplicateCandidate = new Customer(1, "Clone Malicioso", 10);

        if (storage.containsKey(attemptedId)) {
            // Em vez de lançar exceção (Crash), o INSERT IGNORE emite um Warning:
            System.out.println("-> MySQL Console: Query OK, 0 rows affected, 1 warning (0.00 sec)");
            System.out.println("-> SHOW WARNINGS: Warning 1062: Duplicate entry '1' for key 'customers.PRIMARY'");
            System.out.println("-> Linha conflitante descartada com segurança sem derrubar o servidor!\n");
        } else {
            storage.put(attemptedId, duplicateCandidate);
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: O PODEROSO UPSERT (ON DUPLICATE KEY UPDATE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. O PODEROSO UPSERT (INSERT ... ON DUPLICATE KEY UPDATE) ---");
        System.out.println("Babara comprou mais e acumulou +100 pontos. Executando UPSERT no ID 1...");
        System.out.println("SQL: INSERT INTO customers (customer_id, ...) VALUES (1, ..., 100) AS new_row");
        System.out.println("     ON DUPLICATE KEY UPDATE points = customers.points + new_row.points;");

        int upsertId = 1;
        int newPoints = 100;

        if (storage.containsKey(upsertId)) {
            // Chave existe -> Atualiza (UPDATE)
            Customer existing = storage.get(upsertId);
            existing.addPoints(newPoints);
            System.out.println("-> MySQL Console: Query OK, 2 rows affected (0.01 sec) [Contador de UPSERT do MySQL]");
            System.out.println("-> Pontos atualizados de 2273 para: " + existing.getPoints() + " pts!\n");
        } else {
            // Chave não existe -> Insere (INSERT)
            storage.put(upsertId, new Customer(upsertId, "Novo Cliente", newPoints));
            System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: INSERÇÃO COM SUBQUERY (MÉDIA DE PONTOS DINÂMICA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. INSERÇÃO COM SUBQUERY ESCALAR NO VALUES (MÉDIA DINÂMICA) ---");
        double avgPoints = storage.values().stream()
                .mapToInt(Customer::getPoints)
                .average()
                .orElse(0.0);
        int initialBonus = (int) Math.round(avgPoints);
        System.out.println("Média calculada da loja: " + initialBonus + " pts.");

        int newId = 15;
        Customer cDyn = new Customer(newId, "Fernanda Lima", initialBonus);
        storage.put(newId, cDyn);
        System.out.println("-> Cliente Fernanda Lima cadastrada com pontuação inicial baseada na média: " + cDyn + "\n");

        // --------------------------------------------------------------------------------------
        // RELATÓRIO FINAL
        // --------------------------------------------------------------------------------------
        System.out.println("--- TABELA FINAL CONSOLIDADA APÓS TODAS AS OPERAÇÕES AVANÇADAS ---");
        System.out.println("+----+----------------------+------------+");
        System.out.println("| id | name                 | points     |");
        System.out.println("+----+----------------------+------------+");
        storage.values().forEach(System.out::println);
        System.out.println("+----+----------------------+------------+");
        System.out.println("\nDemostração avançada de engenharia de INSERT concluída com 100% de sucesso!");
    }
}
