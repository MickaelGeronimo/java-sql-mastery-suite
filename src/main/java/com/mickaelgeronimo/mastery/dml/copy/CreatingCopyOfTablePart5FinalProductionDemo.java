package com.mickaelgeronimo.mastery.dml.copy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 34 (PARTE 5 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Cópia entre Bancos Distintos (Cross-Database), Emulação de Materialized Views
 *       com Sincronização por Triggers e Change Data Capture (CDC / Auditoria)
 * ==============================================================================
 */
public class CreatingCopyOfTablePart5FinalProductionDemo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public static class CustomerSummary {
        private final int customerId;
        private final String customerName;
        private int totalOrders;
        private LocalDate latestOrderDate;

        public CustomerSummary(int customerId, String customerName, int totalOrders, LocalDate latestOrderDate) {
            this.customerId = customerId;
            this.customerName = customerName;
            this.totalOrders = totalOrders;
            this.latestOrderDate = latestOrderDate;
        }

        public void incrementOrders(LocalDate orderDate) {
            this.totalOrders++;
            this.latestOrderDate = orderDate;
        }

        @Override
        public String toString() {
            return String.format("| %11d | %-20s | %12d | %-17s |",
                customerId, customerName, totalOrders,
                latestOrderDate == null ? "Nenhum" : latestOrderDate.toString());
        }
    }

    public record OrderAuditEntry(
        int auditId,
        int orderId,
        int customerId,
        LocalDate orderDate,
        byte status,
        String actionType,
        String capturedAt
    ) {
        @Override
        public String toString() {
            return String.format("| %8d | %8d | %11d | %-10s | %6d | %-17s | %-19s |",
                auditId, orderId, customerId, orderDate, status, actionType, capturedAt);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 34 (PARTE 5 FINAL) - MATERIALIZED VIEWS & AUDITORIA CDC    ");
        System.out.println("=========================================================================================\n");

        // 1. Dados em sql_store.orders
        List<Order> sqlStoreOrders = new ArrayList<>(List.of(
            new Order(1, 6, LocalDate.of(2019, 1, 30), (byte) 1),
            new Order(2, 7, LocalDate.of(2018, 8, 2), (byte) 2),
            new Order(3, 8, LocalDate.of(2017, 12, 1), (byte) 1),
            new Order(4, 2, LocalDate.of(2017, 1, 22), (byte) 1),
            new Order(5, 5, LocalDate.of(2017, 8, 25), (byte) 2)
        ));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: CÓPIA ENTRE BANCOS DISTINTOS (CROSS-DATABASE COPY)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CÓPIA ENTRE BANCOS: sql_store.orders -> sql_invoicing.orders_cross_backup ---");
        System.out.println("SQL: CREATE TABLE sql_invoicing.orders_cross_backup LIKE sql_store.orders;");
        System.out.println("     INSERT INTO sql_invoicing.orders_cross_backup SELECT * FROM sql_store.orders;");

        List<Order> sqlInvoicingBackup = new ArrayList<>(sqlStoreOrders);
        System.out.printf("-> Total de linhas copiadas para o banco financeiro: %d registros.%n%n", sqlInvoicingBackup.size());

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: EMULAÇÃO DE MATERIALIZED VIEW COM ATUALIZAÇÃO EM TEMPO REAL POR TRIGGER
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. MATERIALIZED VIEW: TABELA customer_orders_summary COM SYNC TRIGGER ---");
        Map<Integer, CustomerSummary> summaryTable = new LinkedHashMap<>();
        summaryTable.put(2, new CustomerSummary(2, "Ines Brush", 1, LocalDate.of(2017, 1, 22)));
        summaryTable.put(5, new CustomerSummary(5, "Clemmie Barden", 1, LocalDate.of(2017, 8, 25)));
        summaryTable.put(6, new CustomerSummary(6, "Elka Twiddell", 1, LocalDate.of(2019, 1, 30)));
        summaryTable.put(7, new CustomerSummary(7, "Ilene Denner", 1, LocalDate.of(2018, 8, 2)));
        summaryTable.put(8, new CustomerSummary(8, "Thacher Naseby", 1, LocalDate.of(2017, 12, 1)));

        System.out.println(">>> TABELA DE RESUMO ANTES DO NOVO PEDIDO:");
        printSummary(summaryTable);

        System.out.println("\nSimulando a inserção de um NOVO PEDIDO para a cliente 6 (Elka Twiddell):");
        System.out.println("SQL: INSERT INTO orders (customer_id, order_date, status) VALUES (6, '2026-10-13', 1);");

        Order novoPedido = new Order(16, 6, LocalDate.of(2026, 10, 13), (byte) 1);
        sqlStoreOrders.add(novoPedido);

        // Disparo do Trigger simulado: AFTER INSERT ON orders
        summaryTable.get(6).incrementOrders(novoPedido.orderDate());
        System.out.println("-> Trigger tr_sync_customer_summary disparado automaticamente!");
        System.out.println("-> Linha da cliente 6 incrementada de 1 para 2 pedidos!\n");

        System.out.println(">>> TABELA DE RESUMO APÓS O TRIGGER (SINCRONIZAÇÃO EM TEMPO REAL):");
        printSummary(summaryTable);

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: CHANGE DATA CAPTURE (CDC / TABELA DE HISTÓRICO TEMPORAL)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 3. CHANGE DATA CAPTURE (CDC): TABELA orders_audit_trail ---");
        List<OrderAuditEntry> auditTrail = new ArrayList<>();
        int auditIdCounter = 1;

        // Carga inicial
        for (Order o : sqlStoreOrders.subList(0, 5)) {
            auditTrail.add(new OrderAuditEntry(
                auditIdCounter++, o.orderId(), o.customerId(), o.orderDate(),
                o.status(), "SNAPSHOT_COPY", LocalDateTime.now().minusHours(2).format(FORMATTER)
            ));
        }

        // Evento em tempo real
        auditTrail.add(new OrderAuditEntry(
            auditIdCounter++, novoPedido.orderId(), novoPedido.customerId(), novoPedido.orderDate(),
            novoPedido.status(), "REALTIME_INSERT", LocalDateTime.now().format(FORMATTER)
        ));

        System.out.println("+----------+----------+-------------+------------+--------+-------------------+---------------------+");
        System.out.println("| audit_id | order_id | customer_id | order_date | status | action_type       | captured_at         |");
        System.out.println("+----------+----------+-------------+------------+--------+-------------------+---------------------+");
        auditTrail.forEach(System.out::println);
        System.out.println("+----------+----------+-------------+------------+--------+-------------------+---------------------+");

        System.out.println("\nDemostração de encerramento definitivo do Capítulo 34 concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }

    private static void printSummary(Map<Integer, CustomerSummary> map) {
        System.out.println("+-------------+----------------------+--------------+-------------------+");
        System.out.println("| customer_id | customer_name        | total_orders | latest_order_date |");
        System.out.println("+-------------+----------------------+--------------+-------------------+");
        map.values().forEach(System.out::println);
        System.out.println("+-------------+----------------------+--------------+-------------------+");
    }
}
