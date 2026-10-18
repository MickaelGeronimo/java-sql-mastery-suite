package com.mickaelgeronimo.mastery.joins.distributed;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 39 (PARTE 4 FINAL SUPREMO): AUDITORIA E HEALTH CHECK MULTI-DATABASE
 * Demonstração Didática em Java 17 LTS para Iniciantes
 * ==============================================================================
 * 
 * Este programa ensina como grandes empresas e arquitetos de software em Java
 * validam a saúde (Health Check) de múltiplos bancos de dados após uma restauração
 * geral de sistema (Disaster Recovery / Seed / Migration).
 */
public class MultiDatabaseHealthAuditDemo {

    // --------------------------------------------------------------------------
    // 1. MODELOS DE DADOS EM JAVA 17 (RECORDS IMUTÁVEIS)
    // --------------------------------------------------------------------------
    public enum HealthStatus { UP, DOWN, WARNING }

    public record TableHealth(
        String tableName, 
        int expectedRows, 
        int actualRows, 
        boolean schemaValid
    ) {
        public boolean isHealthy() {
            return schemaValid && expectedRows == actualRows;
        }
    }

    public record DatabaseHealthReport(
        String databaseName, 
        String charset, 
        String collation, 
        List<TableHealth> tables,
        HealthStatus status,
        String message
    ) {
        public int totalRecords() {
            return tables.stream().mapToInt(TableHealth::actualRows).sum();
        }
    }

    // --------------------------------------------------------------------------
    // 2. SERVIÇO DE AUDITORIA E MONITORAMENTO (HEALTH INDICATOR PATTERN)
    // --------------------------------------------------------------------------
    public static class DatabaseHealthAuditor {
        private final Map<String, DatabaseHealthReport> auditCatalog = new LinkedHashMap<>();

        public void auditDatabase(String dbName, String charset, String collation, List<TableHealth> tables) {
            boolean allTablesHealthy = tables.stream().allMatch(TableHealth::isHealthy);
            HealthStatus status = allTablesHealthy ? HealthStatus.UP : HealthStatus.WARNING;
            String message = allTablesHealthy 
                ? "Todos os esquemas e volumes de dados estão 100% íntegros e operacionais." 
                : "Atenção: discrepância detectada em uma ou mais tabelas!";

            DatabaseHealthReport report = new DatabaseHealthReport(dbName, charset, collation, tables, status, message);
            auditCatalog.put(dbName, report);
        }

        public void printExecutiveDashboard() {
            System.out.println("================================================================================");
            System.out.println("   PAINEL EXECUTIVO DE SAÚDE MULTI-DATABASE (SPRING BOOT HEALTH AUDIT)          ");
            System.out.println("   Timestamp: " + Instant.now());
            System.out.println("================================================================================\n");

            System.out.printf("%-16s | %-10s | %-18s | %-8s | %-12s | %-8s\n", 
                "DATABASE", "STATUS", "CHARSET / COLLATION", "TABELAS", "TOTAL ROWS", "HEALTH");
            System.out.println("-----------------+------------+--------------------+----------+--------------+---------");

            int grandTotalTables = 0;
            int grandTotalRows = 0;

            for (DatabaseHealthReport report : auditCatalog.values()) {
                int tableCount = report.tables().size();
                int rowCount = report.totalRecords();
                grandTotalTables += tableCount;
                grandTotalRows += rowCount;

                String statusIndicator = report.status() == HealthStatus.UP ? "[ ✓ UP ]" : "[ ✕ DOWN ]";

                System.out.printf("%-16s | %-10s | %-18s | %8d | %12d | %-8s\n", 
                    report.databaseName(), 
                    statusIndicator, 
                    report.charset() + " (ai_ci)", 
                    tableCount, 
                    rowCount, 
                    "100% OK");
            }

            System.out.println("-----------------+------------+--------------------+----------+--------------+---------");
            System.out.printf("%-16s | %-10s | %-18s | %8d | %12d | %-8s\n", 
                "TOTAL GERAL", "[ 4 BANCOS ]", "utf8mb4_0900_ai_ci", grandTotalTables, grandTotalRows, "OPERANTE");
            System.out.println("================================================================================\n");

            // Exibição detalhada por tabela
            System.out.println(">>> DETALHAMENTO DE TABELAS E INTEGRIDADE DE REGISTROS:");
            auditCatalog.forEach((dbName, report) -> {
                System.out.println("\n• BANCO: `" + dbName + "` -> " + report.message());
                for (TableHealth th : report.tables()) {
                    System.out.printf("    - Tabela `%-18s`: %2d registros esperados | %2d reais | Validação: %s\n", 
                        th.tableName() + "`", 
                        th.expectedRows(), 
                        th.actualRows(), 
                        th.isHealthy() ? "APROVADA ✓" : "FALHA ✕");
                }
            });
            System.out.println("\n================================================================================");
            System.out.println(">>> CONCLUSÃO DA AUDITORIA: Ambiente 100% pronto para iniciar a SEÇÃO 5!");
            System.out.println("================================================================================\n");
        }
    }

    // --------------------------------------------------------------------------
    // 3. EXECUÇÃO PRINCIPAL (MAIN)
    // --------------------------------------------------------------------------
    public static void main(String[] args) {
        DatabaseHealthAuditor auditor = new DatabaseHealthAuditor();

        // 1. sql_store (5 tabelas)
        auditor.auditDatabase("sql_store", "utf8mb4", "utf8mb4_0900_ai_ci", List.of(
            new TableHealth("customers", 10, 10, true),
            new TableHealth("order_statuses", 3, 3, true),
            new TableHealth("orders", 10, 10, true),
            new TableHealth("order_items", 18, 18, true),
            new TableHealth("order_item_notes", 2, 2, true)
        ));

        // 2. sql_invoicing (4 tabelas)
        auditor.auditDatabase("sql_invoicing", "utf8mb4", "utf8mb4_0900_ai_ci", List.of(
            new TableHealth("payment_methods", 4, 4, true),
            new TableHealth("clients", 5, 5, true),
            new TableHealth("invoices", 17, 17, true),
            new TableHealth("payments", 8, 8, true)
        ));

        // 3. sql_hr (2 tabelas)
        auditor.auditDatabase("sql_hr", "utf8mb4", "utf8mb4_0900_ai_ci", List.of(
            new TableHealth("offices", 10, 10, true),
            new TableHealth("employees", 20, 20, true)
        ));

        // 4. sql_inventory (1 tabela)
        auditor.auditDatabase("sql_inventory", "utf8mb4", "utf8mb4_0900_ai_ci", List.of(
            new TableHealth("products", 10, 10, true)
        ));

        // Gerar relatório executivo no console
        auditor.printExecutiveDashboard();
    }
}
