package com.mickaelgeronimo.mastery.dml.insert;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 31 (PARTE 2): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Inversão de Colunas no INSERT, Expressões Dinâmicas, Inserção em Tabelas
 *       Relacionadas (orders) e a Captura dos 4 Erros Capitais do SGBD
 * ==============================================================================
 */
public class InsertSingleRowPart2AdvancedDemo {

    private static final AtomicInteger CUSTOMER_ID_SEQ = new AtomicInteger(12); // Próximo: 13
    private static final AtomicInteger ORDER_ID_SEQ = new AtomicInteger(10);    // Próximo: 11

    public record Customer(
            int id, String firstName, String lastName,
            LocalDate birthDate, String phone, String address,
            String city, String state, int points
    ) {
        @Override
        public String toString() {
            return String.format("| %2d | %-10s | %-10s | %-10s | %-18s | %-14s | %2s | %6d |",
                    id, firstName, lastName,
                    (birthDate != null ? birthDate.toString() : "NULL"),
                    address, city, state, points);
        }
    }

    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 31 (PARTE 2) - INSERÇÃO AVANÇADA E CAPTURA DE ERROS        ");
        System.out.println("=========================================================================================\n");

        List<Customer> customerTable = new ArrayList<>();
        List<Order> orderTable = new ArrayList<>();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: REORDENAÇÃO DE COLUNAS (INVERSÃO DA ORDEM FÍSICA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. INSERÇÃO COM COLUNAS REORDENADAS (state, last_name, first_name...) ---");
        int id1 = CUSTOMER_ID_SEQ.incrementAndGet();
        Customer c1 = new Customer(
                id1,
                "Juliana",              // first_name
                "Ferreira",             // last_name
                LocalDate.of(1992, 8, 15),
                null,                   // phone
                "Av. Atlântica 500",    // address
                "Rio de Janeiro",       // city
                "RJ",                   // state
                250                     // points
        );
        customerTable.add(c1);
        System.out.println("Linha inserida: " + c1 + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: EXPRESSÕES E FUNÇÕES DINÂMICAS (UPPER, CONCAT, MATEMÁTICA, DATAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. INSERÇÃO COM FUNÇÕES E CÁLCULOS DINÂMICOS NO VALUES ---");
        int id2 = CUSTOMER_ID_SEQ.incrementAndGet();
        String dynFirst = "roberto".toUpperCase();
        String dynLast = "Car" + "valho";
        LocalDate dynBirth = LocalDate.now().minusYears(30);
        int dynPoints = (50 * 2) + 100; // 200 pontos promocionais

        Customer c2 = new Customer(
                id2, dynFirst, dynLast, dynBirth, null,
                "Rua das Palmeiras 45", "Curitiba", "PR", dynPoints
        );
        customerTable.add(c2);
        System.out.println("Linha com cálculos inserida: " + c2 + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: INSERÇÃO EM TABELA RELACIONADA (orders)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. INSERÇÃO NA TABELA 'orders' COM CHAVE ESTRANGEIRA (FK = 1) ---");
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("| order_id | customer_id | order_date | status |");
        System.out.println("+----------+-------------+------------+--------+");
        int newOrderId = ORDER_ID_SEQ.incrementAndGet();
        Order newOrder = new Order(newOrderId, 1, LocalDate.now(), (byte) 1);
        orderTable.add(newOrder);
        System.out.println(newOrder);
        System.out.println("+----------+-------------+------------+--------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: SIMULAÇÃO DOS 4 ERROS CAPITAIS DO COMANDO INSERT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. OS 4 ERROS CAPITAIS DO INSERT DISSECADOS PELO MOTOR ---");

        // Erro 1: Omitir coluna Not Null
        System.out.println("A) Testando Erro 1364 (Coluna Not Null sem Default omitida)...");
        System.out.println("   [BLOQUEIO] ERROR 1364 (HY000): Field 'last_name' doesn't have a default value");

        // Erro 2: Contagem de colunas != contagem de valores
        System.out.println("B) Testando Erro 1136 (Descompasso de colunas e valores)...");
        System.out.println("   [BLOQUEIO] ERROR 1136 (21S01): Column count doesn't match value count at row 1");

        // Erro 3: Dado maior que a coluna (CHAR 2)
        System.out.println("C) Testando Erro 1406 (Texto maior que a capacidade da coluna)...");
        System.out.println("   [BLOQUEIO] ERROR 1406 (22001): Data too long for column 'state' at row 1 ('RIO DE JANEIRO')");

        // Erro 4: Duplicação de Chave Primária
        System.out.println("D) Testando Erro 1062 (Chave Primária duplicada manualmente)...");
        System.out.println("   [BLOQUEIO] ERROR 1062 (23000): Duplicate entry '1' for key 'customers.PRIMARY'\n");

        System.out.println("Demostração de inserção avançada concluída com 100% de precisão!");
    }
}
