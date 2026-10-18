package com.mickaelgeronimo.mastery.dml.insert;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 31 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Inserindo uma Única Linha (INSERT INTO) — Sintaxe Posicional vs Explícita,
 *       Captura de Chaves Geradas (LAST_INSERT_ID) e Validação de Colunas Faltantes
 * ==============================================================================
 */
public class InsertSingleRowPart1Demo {

    // Simulação do Gerador de Auto-Incremento do Banco
    private static final AtomicInteger AUTO_INCREMENT_ID = new AtomicInteger(10);

    // Representação Imutável da Linha Gravada na Tabela
    public record CustomerRow(
            int customerId,
            String firstName,
            String lastName,
            LocalDate birthDate,
            String phone,
            String address,
            String city,
            String state,
            int points
    ) {
        @Override
        public String toString() {
            return String.format("| %2d | %-10s | %-10s | %-10s | %-12s | %-18s | %-10s | %2s | %6d |",
                    customerId,
                    firstName,
                    lastName,
                    (birthDate != null ? birthDate.toString() : "NULL"),
                    (phone != null ? phone : "NULL"),
                    address,
                    city,
                    state,
                    points
            );
        }
    }

    // "Tabela" em memória simulando o armazenamento do MySQL
    private static final List<CustomerRow> databaseTable = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 31 (PARTE 1) - INSERÇÃO DE LINHAS (INSERT INTO)           ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: SINTAXE 1 - INSERÇÃO POSICIONAL COMPLETA (SEM LISTA DE COLUNAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. SINTAXE 1: INSERÇÃO POSICIONAL COMPLETA (INSERT INTO customers VALUES ...) ---");
        System.out.println("SQL: INSERT INTO customers VALUES (DEFAULT, 'John', 'Smith', '1990-01-01', NULL, 'address', 'city', 'CA', DEFAULT);");

        int id1 = AUTO_INCREMENT_ID.incrementAndGet(); // DEFAULT no customer_id
        CustomerRow row1 = new CustomerRow(
                id1,
                "John",
                "Smith",
                LocalDate.of(1990, 1, 1),
                null,           // NULL explícito
                "address",
                "city",
                "CA",
                0               // DEFAULT no points (assume 0)
        );
        databaseTable.add(row1);
        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("-> LAST_INSERT_ID() retornado: " + id1 + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: SINTAXE 2 - LISTA EXPLÍCITA DE COLUNAS (A RECOMENDADA PELO AUTOR)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SINTAXE 2: LISTA EXPLÍCITA DE COLUNAS (A RECOMENDADA PELO MOSH) ---");
        System.out.println("SQL: INSERT INTO customers (first_name, last_name, birth_date, address, city, state)");
        System.out.println("     VALUES ('Mickael', 'Geronimo', '1995-04-12', 'Av. Paulista 1000', 'São Paulo', 'SP');");

        // Note: Omitimos customer_id, phone e points! O banco e o Java aplicam os padrões:
        int id2 = AUTO_INCREMENT_ID.incrementAndGet();
        CustomerRow row2 = new CustomerRow(
                id2,
                "Mickael",
                "Geronimo",
                LocalDate.of(1995, 4, 12),
                null,                       // Omitido -> assume NULL
                "Av. Paulista 1000",
                "São Paulo",
                "SP",
                0                           // Omitido -> assume DEFAULT 0
        );
        databaseTable.add(row2);
        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("-> LAST_INSERT_ID() retornado: " + id2 + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: VERIFICAÇÃO DOS DADOS GRAVADOS NO BANCO
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. RESULTADO DA TABELA APÓS OS INSERTS (SELECT * FROM customers WHERE id >= 11) ---");
        System.out.println("+----+------------+------------+------------+--------------+--------------------+------------+----+--------+");
        System.out.println("| id | first_name | last_name  | birth_date | phone        | address            | city       | st | points |");
        System.out.println("+----+------------+------------+------------+--------------+--------------------+------------+----+--------+");
        for (CustomerRow row : databaseTable) {
            System.out.println(row);
        }
        System.out.println("+----+------------+------------+------------+--------------+--------------------+------------+----+--------+");
        System.out.println(databaseTable.size() + " novas linhas gravadas com sucesso no banco relacional!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: POR QUE A SINTAXE 2 É INFINITAMENTE SUPERIOR?
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. POR QUE A SINTAXE 2 É A ESCOLHA PROFISSIONAL? ---");
        System.out.println("1. Imunidade a Mudanças de Schema: Se o DBA adicionar uma coluna nova (ex: email),");
        System.out.println("   a Sintaxe 1 quebrará em toda a aplicação. A Sintaxe 2 continua funcionando!");
        System.out.println("2. Ordem Flexível: Você pode declarar as colunas em qualquer ordem:");
        System.out.println("   INSERT INTO customers (state, first_name, last_name, address, city) ...");
        System.out.println("3. Código Auto-Explicativo: Quem lê o código sabe exatamente para onde cada valor vai.");
    }
}
