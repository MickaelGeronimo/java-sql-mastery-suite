package com.mickaelgeronimo.mastery.dml.columns;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 30 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Atributos de Coluna (Column Attributes) — PK, NN, AI, Default e Tipos de Dados
 *       Como o Java moderno e o JPA/Hibernate espelham o esquema relacional
 * ==============================================================================
 */
public class ColumnAttributesPart1Demo {

    // Simulação do Gerador de Auto-Incremento do MySQL (AI - Auto Increment)
    private static final AtomicInteger AUTO_INCREMENT_CUSTOMER_ID = new AtomicInteger(10);

    // Representação do Modelo de Domínio com Validações Estritas (Espelhando os Atributos SQL)
    public static class CustomerEntity {
        // Atributo: PK + AI (Primary Key + Auto Increment)
        private final Integer customerId;

        // Atributo: VARCHAR(50) + NN (Not Null)
        private final String firstName;
        private final String lastName;
        private final String address;
        private final String city;

        // Atributo: CHAR(2) + NN (Not Null)
        private final String state;

        // Atributo: DATE + Aceita NULL (Nullable)
        private final LocalDate birthDate;

        // Atributo: VARCHAR(50) + Aceita NULL (Nullable)
        private final String phone;

        // Atributo: INT + NN + Default 0
        private final int points;

        // Construtor com Regras de Validação Defensiva
        public CustomerEntity(
                Integer customerId,
                String firstName,
                String lastName,
                LocalDate birthDate,
                String phone,
                String address,
                String city,
                String state,
                Integer points
        ) {
            // 1. Simulação do Auto Increment (se o ID for nulo, o gerador assume)
            this.customerId = (customerId == null) ? AUTO_INCREMENT_CUSTOMER_ID.incrementAndGet() : customerId;

            // 2. Validação NOT NULL (NN) para colunas obrigatórias
            this.firstName = validateVarchar(firstName, "first_name", 50, true);
            this.lastName = validateVarchar(lastName, "last_name", 50, true);
            this.address = validateVarchar(address, "address", 50, true);
            this.city = validateVarchar(city, "city", 50, true);

            // 3. Validação CHAR(2) para o estado (UF)
            if (state == null || state.trim().length() != 2) {
                throw new IllegalArgumentException("Erro SQL: Coluna 'state' (CHAR 2) não pode ser nula e deve ter exatamente 2 caracteres!");
            }
            this.state = state.trim().toUpperCase();

            // 4. Colunas Opcionais (Nullable)
            this.birthDate = birthDate;
            this.phone = (phone != null) ? validateVarchar(phone, "phone", 50, false) : null;

            // 5. Simulação do Default 0 (se points for nulo, assume 0)
            this.points = (points != null) ? points : 0;
        }

        private static String validateVarchar(String value, String fieldName, int maxLength, boolean notNull) {
            if (notNull && (value == null || value.trim().isEmpty())) {
                throw new IllegalArgumentException("Erro SQL (NN Violation): Coluna '" + fieldName + "' não pode ser nula (Field cannot be null)!");
            }
            if (value != null && value.length() > maxLength) {
                throw new IllegalArgumentException("Erro SQL (Data Truncation): O valor de '" + fieldName + "' ultrapassa o limite de " + maxLength + " caracteres!");
            }
            return value;
        }

        @Override
        public String toString() {
            return String.format("| %2d | %-10s | %-10s | %-10s | %-12s | %-12s | %-10s | %2s | %5d |",
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

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 30 (PARTE 1) - ATRIBUTOS DE COLUNA (COLUMN ATTRIBUTES)     ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: INSPEÇÃO DOS ATRIBUTOS (METADADOS EQUIVALENTES AO DESCRIBE customers)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. METADADOS DA TABELA 'customers' (EQUIVALENTE AO DESCRIBE SQL) ---");
        System.out.println("+-------------+-------------+------+-----+---------+----------------+");
        System.out.println("| Field       | Type        | Null | Key | Default | Extra          |");
        System.out.println("+-------------+-------------+------+-----+---------+----------------+");
        System.out.println("| customer_id | int         | NO   | PRI | NULL    | auto_increment |");
        System.out.println("| first_name  | varchar(50) | NO   |     | NULL    |                |");
        System.out.println("| last_name   | varchar(50) | NO   |     | NULL    |                |");
        System.out.println("| birth_date  | date        | YES  |     | NULL    |                |");
        System.out.println("| phone       | varchar(50) | YES  |     | NULL    |                |");
        System.out.println("| address     | varchar(50) | NO   |     | NULL    |                |");
        System.out.println("| city        | varchar(50) | NO   |     | NULL    |                |");
        System.out.println("| state       | char(2)     | NO   |     | NULL    |                |");
        System.out.println("| points      | int         | NO   |     | 0       |                |");
        System.out.println("+-------------+-------------+------+-----+---------+----------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: INSERÇÃO BEM-SUCEDIDA APROVEITANDO AUTO-INCREMENT E DEFAULT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. INSERÇÃO VÁLIDA: APROVEITANDO AUTO-INCREMENT (AI) E DEFAULT (0) ---");
        System.out.println("+----+------------+------------+------------+--------------+--------------+------------+----+-------+");
        System.out.println("| id | first_name | last_name  | birth_date | phone        | address      | city       | st |points |");
        System.out.println("+----+------------+------------+------------+--------------+--------------+------------+----+-------+");

        // Omitimos o ID (passamos null -> AI gera 11) e omitimos points (passamos null -> Default assume 0)
        CustomerEntity newCustomer1 = new CustomerEntity(
                null,           // customer_id -> Auto Increment
                "Carlos",       // first_name -> Not Null
                "Silva",        // last_name -> Not Null
                null,           // birth_date -> Nullable
                null,           // phone -> Nullable
                "Rua das Flores 123", // address -> Not Null
                "São Paulo",    // city -> Not Null
                "SP",           // state -> CHAR(2) Not Null
                null            // points -> Default 0
        );

        System.out.println(newCustomer1);

        CustomerEntity newCustomer2 = new CustomerEntity(
                null,           // customer_id -> Auto Increment assume 12!
                "Mariana",
                "Souza",
                LocalDate.of(1996, 5, 20),
                "11-98765-4321",
                "Av Paulista 900",
                "São Paulo",
                "SP",
                500             // Pontos informados explicitamente
        );

        System.out.println(newCustomer2);
        System.out.println("+----+------------+------------+------------+--------------+--------------+------------+----+-------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: TESTANDO AS RESTRIÇÕES (VIOLAÇÃO DE NOT NULL E CHAR LENGTH)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. TESTE DE ERROS: VIOLAÇÃO DE ATRIBUTOS (COMO O SGBD REAGE) ---");

        try {
            System.out.println("Tentando inserir cliente sem primeiro nome (violando first_name Not Null)...");
            new CustomerEntity(null, null, "Silva", null, null, "Rua A", "Campinas", "SP", 0);
        } catch (IllegalArgumentException ex) {
            System.out.println("   [BLOQUEIO CONFIRMADO] " + ex.getMessage());
        }

        try {
            System.out.println("Tentando inserir estado com mais de 2 letras (violando CHAR 2)...");
            new CustomerEntity(null, "Lucas", "Moraes", null, null, "Rua B", "Curitiba", "PARANÁ", 0);
        } catch (IllegalArgumentException ex) {
            System.out.println("   [BLOQUEIO CONFIRMADO] " + ex.getMessage());
        }

        System.out.println("\nDemostração concluída com 100% de sucesso!");
    }
}
