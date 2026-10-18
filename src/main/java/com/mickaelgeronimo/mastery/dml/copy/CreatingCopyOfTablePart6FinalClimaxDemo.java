package com.mickaelgeronimo.mastery.dml.copy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 34 (PARTE 6 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Disaster Recovery e Auto-Cura de Dados usando Tabelas Cópias,
 *       Algoritmo de Reconciliação (WHERE NOT EXISTS) e Snapshots Dinâmicos
 * ==============================================================================
 */
public class CreatingCopyOfTablePart6FinalClimaxDemo {

    private static final DateTimeFormatter SNAP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 34 (PARTE 6 FINAL) - DISASTER RECOVERY & AUTO-CURA        ");
        System.out.println("=========================================================================================\n");

        // 1. Tabela Original de Produção com 10 Pedidos
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

        // --------------------------------------------------------------------------------------
        // PASSO 1: CRIAÇÃO DO SNAPSHOT DE SEGURANÇA
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. TIRANDO SNAPSHOT DE SEGURANÇA (orders_snapshot_master) ---");
        Map<Integer, Order> snapshotBackup = new LinkedHashMap<>(productionOrders);
        System.out.printf("Snapshot criado com sucesso com %d registros preservados intactos.%n%n", snapshotBackup.size());

        // --------------------------------------------------------------------------------------
        // PASSO 2: O DESASTRE CORPORATIVO (EXCLUSÃO ACIDENTAL)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SIMULAÇÃO DO DESASTRE: DELETE ACIDENTAL EM PRODUÇÃO ---");
        System.out.println("Comando Acidental: DELETE FROM orders WHERE order_date < '2018-01-01';");

        // Remove pedidos anteriores a 2018 (IDs 3, 4, 5 e 9)
        LocalDate cutoff = LocalDate.of(2018, 1, 1);
        productionOrders.entrySet().removeIf(entry -> entry.getValue().orderDate().isBefore(cutoff));

        System.out.printf("-> Total de linhas restantes em produção após o incidente: %d registros!%n", productionOrders.size());
        System.out.println("-> ALERTA VERMELHO: 4 pedidos críticos desapareceram do sistema!\n");

        System.out.println(">>> TABELA orders EM ESTADO DEGRADADO (APENAS 6 REGISTROS):");
        printTable(productionOrders.values());

        // --------------------------------------------------------------------------------------
        // PASSO 3: O ALGORITMO DE AUTO-CURA (DISASTER RECOVERY COM WHERE NOT EXISTS)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 3. EXECUTANDO A AUTO-CURA (DISASTER RECOVERY) ---");
        System.out.println("SQL: INSERT INTO orders");
        System.out.println("     SELECT * FROM orders_snapshot_master b");
        System.out.println("     WHERE NOT EXISTS (SELECT 1 FROM orders o WHERE o.order_id = b.order_id);");

        // Identificando registros que estão no backup mas sumiram da produção
        List<Order> missingOrders = snapshotBackup.values().stream()
            .filter(b -> !productionOrders.containsKey(b.orderId()))
            .sorted(Comparator.comparingInt(Order::orderId))
            .collect(Collectors.toList());

        System.out.printf("-> Registros órfãos identificados para restauração imediata: %d pedidos.%n", missingOrders.size());
        for (Order o : missingOrders) {
            productionOrders.put(o.orderId(), o);
        }

        System.out.println("-> Restauração concluída com sucesso! Tabela orders voltou a ter 10 registros!\n");

        System.out.println(">>> TABELA orders 100% RECUPERADA E CURADA:");
        printTable(productionOrders.values().stream().sorted(Comparator.comparingInt(Order::orderId)).collect(Collectors.toList()));

        // --------------------------------------------------------------------------------------
        // PASSO 4: SIMULAÇÃO DA PROCEDURE DE SNAPSHOT DINÂMICO
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 4. AUTOMAÇÃO DE SNAPSHOT DINÂMICO COM DATA/HORA ---");
        String dynamicTableName = "shippers_snap_" + LocalDateTime.now().format(SNAP_FORMATTER);
        System.out.println("Chamada da Procedure: CALL sp_create_quick_snapshot('shippers');");
        System.out.println("-> Tabela física gerada: " + dynamicTableName);
        System.out.println("-> Resposta: 'Snapshot criado com sucesso: " + dynamicTableName + "'");

        System.out.println("\nDemostração de encerramento absoluto do Capítulo 34 concluída com 100% de sucesso!");
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
