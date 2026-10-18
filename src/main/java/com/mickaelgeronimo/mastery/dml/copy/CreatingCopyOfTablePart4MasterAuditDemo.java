package com.mickaelgeronimo.mastery.dml.copy;

import java.time.LocalDate;
import java.util.*;
import java.util.zip.CRC32;

/**
 * ==============================================================================
 * CAPÍTULO 34 (PARTE 4 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Pipeline de Arquivamento com Particionamento por Range (Ano),
 *       Auditoria Criptográfica de Integridade (CHECKSUM) e Partition Pruning
 * ==============================================================================
 */
public class CreatingCopyOfTablePart4MasterAuditDemo {

    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 34 (PARTE 4 FINAL) - PARTICIONAMENTO E AUDITORIA CHECKSUM ");
        System.out.println("=========================================================================================\n");

        // 1. Dados da Tabela Original 'orders'
        List<Order> ordersOriginal = List.of(
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
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: ROTEAMENTO AUTOMÁTICO EM PARTIÇÕES FÍSICAS (PARTITION BY RANGE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CARGA EM MASSA NO ARQUIVO PARTICIONADO POR ANO ---");
        Map<String, List<Order>> partitionedStorage = new TreeMap<>();
        partitionedStorage.put("p_2017", new ArrayList<>());
        partitionedStorage.put("p_2018", new ArrayList<>());
        partitionedStorage.put("p_2019", new ArrayList<>());
        partitionedStorage.put("p_future", new ArrayList<>());

        for (Order o : ordersOriginal) {
            int year = o.orderDate().getYear();
            if (year < 2018) {
                partitionedStorage.get("p_2017").add(o);
            } else if (year < 2019) {
                partitionedStorage.get("p_2018").add(o);
            } else if (year < 2020) {
                partitionedStorage.get("p_2019").add(o);
            } else {
                partitionedStorage.get("p_future").add(o);
            }
        }

        System.out.println(">>> DISTRIBUIÇÃO FÍSICA DAS LINHAS POR PARTIÇÃO (information_schema.partitions):");
        System.out.println("+----------------------------+-----------------+------------+");
        System.out.println("| table_name                 | partition_name  | table_rows |");
        System.out.println("+----------------------------+-----------------+------------+");
        partitionedStorage.forEach((partName, list) -> {
            System.out.printf("| %-26s | %-15s | %10d |%n", "orders_partitioned_archive", partName, list.size());
        });
        System.out.println("+----------------------------+-----------------+------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: AUDITORIA MATEMÁTICA DE INTEGRIDADE (SIMULAÇÃO DO CHECKSUM TABLE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. AUDITORIA MATEMÁTICA: SIMULAÇÃO DO CHECKSUM TABLE ---");
        long checksumOriginal = calculateChecksum(ordersOriginal);

        List<Order> allPartitionedRows = new ArrayList<>();
        partitionedStorage.values().forEach(allPartitionedRows::addAll);
        long checksumArchive = calculateChecksum(allPartitionedRows);

        System.out.println("+----------------------------+------------+");
        System.out.println("| Table                      | Checksum   |");
        System.out.println("+----------------------------+------------+");
        System.out.printf("| %-26s | %10d |%n", "sql_store.orders", checksumOriginal);
        System.out.printf("| %-26s | %10d |%n", "orders_partitioned_archive", checksumArchive);
        System.out.println("+----------------------------+------------+");

        if (checksumOriginal == checksumArchive) {
            System.out.println("✔️ AUDITORIA APROVADA: Checksum 100% idêntico! Nenhum byte foi corrompido durante a cópia.\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: PARTITION PRUNING EM AÇÃO (EXPLAIN QUERY)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. PERFORMANCE: PARTITION PRUNING (CONSULTA DIRETA EM p_2018) ---");
        System.out.println("Query: SELECT * FROM orders_partitioned_archive WHERE order_date BETWEEN '2018-01-01' AND '2018-12-31';");
        System.out.println("-> O Otimizador do MySQL ignora p_2017, p_2019 e p_future!");
        System.out.println("-> Partições escaneadas: [p_2018] exclusivamente!\n");

        System.out.println(">>> REGISTROS RESGATADOS DIRETAMENTE DA PARTIÇÃO p_2018:");
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("| order_id | customer_id | order_date | status |");
        System.out.println("+----------+-------------+------------+--------+");
        partitionedStorage.get("p_2018").forEach(System.out::println);
        System.out.println("+----------+-------------+------------+--------+");

        System.out.println("\nDemostração de encerramento supremo do Capítulo 34 concluída com 100% de sucesso!");
        System.out.println("=========================================================================================\n");
    }

    private static long calculateChecksum(List<Order> orders) {
        CRC32 crc = new CRC32();
        List<Order> sorted = new ArrayList<>(orders);
        sorted.sort(Comparator.comparingInt(Order::orderId));
        for (Order o : sorted) {
            String rowStr = o.orderId() + ";" + o.customerId() + ";" + o.orderDate() + ";" + o.status();
            crc.update(rowStr.getBytes());
        }
        return crc.getValue();
    }
}
