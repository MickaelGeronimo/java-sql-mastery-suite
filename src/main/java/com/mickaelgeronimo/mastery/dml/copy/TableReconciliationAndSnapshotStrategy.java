package com.mickaelgeronimo.mastery.dml.copy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Enterprise Disaster Recovery & Reconciled Snapshot Strategy
 * 
 * Demonstrates:
 * 1. Consistent snapshot isolation using staging copy tables.
 * 2. Uncorrupted state reconciliation via NOT EXISTS anti-join logic.
 * 3. Dynamic temporal point-in-time recovery procedure simulation.
 */
public class TableReconciliationAndSnapshotStrategy {

    private static final DateTimeFormatter SNAP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   ENTERPRISE RECONCILIATION & DISASTER RECOVERY VIA COPY TABLES                         ");
        System.out.println("=========================================================================================\n");

        // 1. Production baseline with 10 orders
        Map<Integer, Order> productionOrders = new LinkedHashMap<>();
        productionOrders.put(1, new Order(1, 6, LocalDate.of(2019, 1, 30), (byte) 1));
        productionOrders.put(2, new Order(2, 7, LocalDate.of(2018, 8, 2), (byte) 2));
        productionOrders.put(3, new Order(3, 8, LocalDate.of(2017, 12, 1), (byte) 1));
        productionOrders.put(4, new Order(4, 2, LocalDate.of(2017, 1, 22), (byte) 1));
        productionOrders.put(5, new Order(5, 5, LocalDate.of(2017, 8, 25), (byte) 2));
        productionOrders.put(6, new Order(6, 10, LocalDate.of(2018, 11, 18), (byte) 1));
        productionOrders.put(7, new Order(7, 2, LocalDate.of(2018, 9, 7), (byte) 2));
        productionOrders.put(8, new Order(8, 5, LocalDate.of(2018, 6, 8), (byte) 1));
        productionOrders.put(9, new Order(9, 10, LocalDate.of(2017, 7, 5), (byte) 2));
        productionOrders.put(10, new Order(10, 6, LocalDate.of(2018, 4, 22), (byte) 2));

        // 1. Snapshot creation
        System.out.println("--- 1. CREATING CONSISTENT BACKUP SNAPSHOT (orders_snapshot_master) ---");
        Map<Integer, Order> snapshotBackup = new LinkedHashMap<>(productionOrders);
        System.out.printf("Snapshot created successfully with %d records intact.%n%n", snapshotBackup.size());

        // 2. Incident simulation
        System.out.println("--- 2. SIMULATING UNBOUNDED DELETION INCIDENT ---");
        System.out.println("Command: DELETE FROM orders WHERE order_date < '2018-01-01';");

        LocalDate cutoff = LocalDate.of(2018, 1, 1);
        productionOrders.entrySet().removeIf(entry -> entry.getValue().orderDate().isBefore(cutoff));

        System.out.printf("-> Remaining production records: %d records.%n", productionOrders.size());
        System.out.println("-> Degraded production state detected.\n");

        System.out.println(">>> PRODUCTION TABLE (DEGRADED STATE):");
        printTable(productionOrders.values());

        // 3. Automated reconciliation via NOT EXISTS
        System.out.println("\n--- 3. EXECUTING POINT-IN-TIME RECONCILIATION ---");
        System.out.println("SQL: INSERT INTO orders");
        System.out.println("     SELECT * FROM orders_snapshot_master b");
        System.out.println("     WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.order_id = b.order_id);");

        List<Order> missingOrders = snapshotBackup.values().stream()
            .filter(b -> !productionOrders.containsKey(b.orderId()))
            .sorted(Comparator.comparingInt(Order::orderId))
            .collect(Collectors.toList());

        System.out.printf("-> Missing records identified for reconciliation: %d records.%n", missingOrders.size());
        for (Order o : missingOrders) {
            productionOrders.put(o.orderId(), o);
        }

        System.out.println("-> Reconciliation completed. Production table restored to 10 records!\n");

        System.out.println(">>> PRODUCTION TABLE FULLY RECONCILED:");
        printTable(productionOrders.values().stream().sorted(Comparator.comparingInt(Order::orderId)).collect(Collectors.toList()));

        // 4. Dynamic temporal snapshotting
        System.out.println("\n--- 4. DYNAMIC TEMPORAL SNAPSHOTTING ---");
        String dynamicTableName = "shippers_snap_" + LocalDateTime.now().format(SNAP_FORMATTER);
        System.out.println("Executing Procedure: CALL sp_create_quick_snapshot('shippers');");
        System.out.println("-> Snapshot table materialized: " + dynamicTableName);

        System.out.println("\nReconciliation verification successful.");
        System.out.println("=========================================================================================\n");
    }

    private static void printTable(Collection<Order> list) {
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("| order_id | customer_id | order_date | status |");
        System.out.println("+----------+-------------+------------+--------+");
        list.forEach(System.out::println);
        System.out.println("+----------+-------------+------------+--------+");
    }
}
