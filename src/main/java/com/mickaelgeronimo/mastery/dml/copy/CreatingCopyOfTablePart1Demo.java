package com.mickaelgeronimo.mastery.dml.copy;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 34 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Criando Cópia de Tabela (CREATE TABLE ... AS SELECT / CTAS),
 *       Perda de Metadados (PK e Auto-Increment), TRUNCATE e INSERT INTO SELECT
 * ==============================================================================
 */
public class CreatingCopyOfTablePart1Demo {

    // Modelo de Domínio
    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    // Metadados de Coluna (Simulação do DESCRIBE do MySQL)
    public record ColumnMetadata(String field, String type, String isNull, String key, String defaultValue, String extra) {
        @Override
        public String toString() {
            return String.format("| %-15s | %-12s | %-4s | %-4s | %-7s | %-14s |",
                field, type, isNull, key, defaultValue, extra);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 34 (PARTE 1) - CRIANDO UMA CÓPIA DE UMA TABELA (CTAS)     ");
        System.out.println("=========================================================================================\n");

        // 1. Dados da Tabela Original 'orders'
        List<Order> ordersTable = new ArrayList<>(List.of(
            new Order(1, 6, LocalDate.of(2019, 1, 30), (byte) 1),
            new Order(2, 7, LocalDate.of(2018, 8, 2), (byte) 2),
            new Order(3, 8, LocalDate.of(2017, 12, 1), (byte) 1),
            new Order(4, 2, LocalDate.of(2017, 1, 22), (byte) 1),
            new Order(5, 5, LocalDate.of(2017, 8, 25), (byte) 2),
            new Order(6, 10, LocalDate.of(2018, 11, 18), (byte) 1),
            new Order(7, 2, LocalDate.of(2018, 9, 7), (byte) 2),
            new Order(8, 5, LocalDate.of(2018, 6, 8), (byte) 1),
            new Order(9, 10, LocalDate.of(2017, 7, 5), (byte) 2),
            new Order(10, 6, LocalDate.of(2018, 4, 22), (byte) 2)
        ));

        // Metadados originais de 'orders'
        List<ColumnMetadata> originalMetadata = List.of(
            new ColumnMetadata("order_id", "int", "NO", "PRI", "NULL", "auto_increment"),
            new ColumnMetadata("customer_id", "int", "NO", "MUL", "NULL", ""),
            new ColumnMetadata("order_date", "date", "NO", "", "NULL", ""),
            new ColumnMetadata("status", "tinyint", "NO", "", "1", "")
        );

        // Metadados clonados via CTAS (CREATE TABLE orders_archived AS SELECT * FROM orders)
        // O MySQL descarta 'PRI', 'MUL' e 'auto_increment'!
        List<ColumnMetadata> ctasMetadata = List.of(
            new ColumnMetadata("order_id", "int", "NO", "", "0", ""),
            new ColumnMetadata("customer_id", "int", "NO", "", "0", ""),
            new ColumnMetadata("order_date", "date", "NO", "", "NULL", ""),
            new ColumnMetadata("status", "tinyint", "NO", "", "1", "")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: AVALIAÇÃO DOS DADOS COPIADOS (CTAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CÓPIA COMPLETA COM: CREATE TABLE orders_archived AS SELECT * FROM orders ---");
        List<Order> ordersArchived = new ArrayList<>(ordersTable);
        System.out.printf("Total de registros copiados instantaneamente: %d linhas.%n%n", ordersArchived.size());

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A ARMADILHA DOS METADADOS (COMPROVAÇÃO COM DESCRIBE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A GRANDE ARMADILHA DE METADADOS: DESCRIBE orders vs DESCRIBE orders_archived ---");
        System.out.println(">>> TABELA ORIGINAL (orders) - Note as colunas Key=PRI e Extra=auto_increment:");
        printMetadata(originalMetadata);

        System.out.println("\n>>> TABELA CLONADA VIA CTAS (orders_archived) - Key e Extra foram PERDIDOS:");
        printMetadata(ctasMetadata);

        System.out.println("\n⚠️ ALERTA DO MOSH: O MySQL copiou os dados, mas NÃO copiou a Chave Primária (PK) nem o Auto-Increment (AI)!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: LIMPANDO A TABELA COM TRUNCATE TABLE
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. LIMPANDO A TABELA CLONADA COM TRUNCATE TABLE orders_archived ---");
        System.out.println("Comando: TRUNCATE TABLE orders_archived;");
        ordersArchived.clear();
        System.out.println("-> MySQL Console: Query OK, 0 rows affected (0.02 sec)");
        System.out.printf("-> Total de registros na orders_archived após TRUNCATE: %d linhas (Vazia!).%n%n", ordersArchived.size());

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: INSERÇÃO CONDICIONAL VIA 'INSERT INTO ... SELECT' (SEM VALUES)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. INSERÇÃO FILTRADA COM: INSERT INTO orders_archived SELECT * FROM orders WHERE order_date < '2019-01-01' ---");
        LocalDate cutoffDate = LocalDate.of(2019, 1, 1);

        // Subquery executada via Stream API em Java
        List<Order> filteredOrders = ordersTable.stream()
            .filter(o -> o.orderDate().isBefore(cutoffDate))
            .sorted(Comparator.comparing(Order::orderDate))
            .collect(Collectors.toList());

        ordersArchived.addAll(filteredOrders);

        System.out.printf("-> MySQL Console: Query OK, %d rows affected (0.01 sec)%n", filteredOrders.size());
        System.out.println("->                Records: 9  Duplicates: 0  Warnings: 0\n");

        System.out.println(">>> CONTEÚDO DA TABELA orders_archived (SOMENTE PEDIDOS ANTERIORES A 2019):");
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("| order_id | customer_id | order_date | status |");
        System.out.println("+----------+-------------+------------+--------+");
        ordersArchived.forEach(System.out::println);
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("\nDemostração concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }

    private static void printMetadata(List<ColumnMetadata> metaList) {
        System.out.println("+-----------------+--------------+------+------+---------+----------------+");
        System.out.println("| Field           | Type         | Null | Key  | Default | Extra          |");
        System.out.println("+-----------------+--------------+------+------+---------+----------------+");
        metaList.forEach(System.out::println);
        System.out.println("+-----------------+--------------+------+------+---------+----------------+");
    }
}
