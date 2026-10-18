package com.mickaelgeronimo.mastery.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 39 (PARTE 3 FINAL MASTERCLASS): AUTOMAÇÃO DE RESTAURAÇÃO DE BANCO
 * Demonstração em Java 17 LTS: Testcontainers, Docker Entrypoint e Seeding
 * ==============================================================================
 * 
 * Este programa ensina ao iniciante como o comando 'create-databases.sql' do Mosh
 * é implementado e automatizado no mundo profissional de desenvolvimento Java:
 * 1. Orquestração de múltiplos bancos (sql_store, sql_invoicing, sql_hr, sql_inventory).
 * 2. Gestão do ciclo de vida: DROP, CREATE, FOREIGN_KEY_CHECKS e SEEDING.
 * 3. Simulação de Testcontainers e anotação Spring @Sql(executionPhase = BEFORE_TEST_METHOD).
 */
public class DatabaseRestoreMasterAutomationDemo {

    // --------------------------------------------------------------------------
    // 1. MODELOS DE DADOS EM JAVA 17 (RECORDS IMUTÁVEIS)
    // --------------------------------------------------------------------------
    public record TableMetadata(String tableName, int initialRowCount, List<String> primaryKeys) {}

    public record DatabaseSchema(
        String databaseName, 
        String description, 
        List<TableMetadata> tables
    ) {
        public int totalInitialRows() {
            return tables.stream().mapToInt(TableMetadata::initialRowCount).sum();
        }
    }

    public record ExecutionLog(
        LocalDateTime timestamp, 
        String database, 
        String action, 
        String status, 
        String details
    ) {}

    // --------------------------------------------------------------------------
    // 2. ORQUESTRADOR DE RESTAURAÇÃO DE BANCOS DE DADOS
    // --------------------------------------------------------------------------
    public static class DatabaseRestoreOrchestrator {
        private final Map<String, DatabaseSchema> registeredDatabases = new LinkedHashMap<>();
        private final List<ExecutionLog> executionHistory = new ArrayList<>();
        private boolean foreignKeyChecksEnabled = true;

        public void registerDatabase(DatabaseSchema schema) {
            registeredDatabases.put(schema.databaseName(), schema);
        }

        public void setForeignKeyChecks(boolean enabled) {
            this.foreignKeyChecksEnabled = enabled;
            String state = enabled ? "ENABLED (1)" : "DISABLED (0)";
            log("GLOBAL", "SET FOREIGN_KEY_CHECKS", "SUCCESS", "Foreign key constraint checks set to " + state);
        }

        public boolean isForeignKeyChecksEnabled() {
            return foreignKeyChecksEnabled;
        }

        public void executeMasterScript(String scriptName) {
            System.out.println("================================================================================");
            System.out.println(">>> INICIANDO EXECUÇÃO DO SCRIPT MESTRE DE RESTAURAÇÃO: " + scriptName);
            System.out.println("================================================================================");

            // Passo 1: Desabilitar checagem de chaves estrangeiras para evitar erros de ordem de deleção
            setForeignKeyChecks(false);

            // Passo 2: Executar DROP e CREATE para cada um dos bancos de dados do curso
            for (DatabaseSchema db : registeredDatabases.values()) {
                System.out.println("\n[PROCESSANDO BANCO: `" + db.databaseName() + "` (" + db.description() + ")]");
                
                // DROP DATABASE IF EXISTS
                log(db.databaseName(), "DROP DATABASE IF EXISTS", "SUCCESS", "Schema existente descartado atomicamente.");
                System.out.println("  ✓ DROP DATABASE IF EXISTS `" + db.databaseName() + "`;");

                // CREATE DATABASE
                log(db.databaseName(), "CREATE DATABASE", "SUCCESS", "Schema recriado com UTF-8 e collation padrão.");
                System.out.println("  ✓ CREATE DATABASE `" + db.databaseName() + "`;");

                // USE database
                System.out.println("  ✓ USE `" + db.databaseName() + "`;");

                // Reconstrução de tabelas e Carga de Dados (Seeding)
                for (TableMetadata table : db.tables()) {
                    log(db.databaseName(), "CREATE & SEED TABLE `" + table.tableName() + "`", "SUCCESS", 
                        "Tabela criada com PK " + table.primaryKeys() + " e " + table.initialRowCount() + " registros inseridos.");
                    System.out.printf("    - Tabela `%-18s`: %2d registros semeados (Seed baseline)\n", 
                        table.tableName() + "`", table.initialRowCount());
                }
            }

            // Passo 3: Reativar checagem de integridade referencial
            System.out.println();
            setForeignKeyChecks(true);

            System.out.println("================================================================================");
            System.out.println(">>> SCRIPT MESTRE EXECUTADO COM SUCESSO! TODOS OS BANCOS RESTAURADOS (100% LIMPOS)");
            System.out.println("================================================================================\n");
        }

        public void runIntegrityAudit() {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("AUDITORIA GERAL DE RESTAURAÇÃO (PÓS-EXECUÇÃO DO CREATE-DATABASES.SQL)");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-18s | %-32s | %-12s | %-10s\n", "BANCO DE DADOS", "PROPÓSITO NO CURSO", "QTD TABELAS", "TOTAL ROWS");
            System.out.println("-------------------+----------------------------------+--------------+-----------");

            int grandTotalTables = 0;
            int grandTotalRows = 0;

            for (DatabaseSchema db : registeredDatabases.values()) {
                int tableCount = db.tables().size();
                int rowCount = db.totalInitialRows();
                grandTotalTables += tableCount;
                grandTotalRows += rowCount;

                System.out.printf("%-18s | %-32s | %12d | %10d\n", 
                    db.databaseName(), db.description(), tableCount, rowCount);
            }
            System.out.println("-------------------+----------------------------------+--------------+-----------");
            System.out.printf("%-18s | %-32s | %12d | %10d\n", "TOTAL CONSOLIDADO", "Ambiente Mosh Hamedani Completo", grandTotalTables, grandTotalRows);
            System.out.println("--------------------------------------------------------------------------------\n");
        }

        private void log(String db, String action, String status, String details) {
            executionHistory.add(new ExecutionLog(LocalDateTime.now(), db, action, status, details));
        }

        public List<ExecutionLog> getLogs() {
            return Collections.unmodifiableList(executionHistory);
        }
    }

    // --------------------------------------------------------------------------
    // 3. SIMULAÇÃO DE TESTE DE INTEGRAÇÃO COM SPRING BOOT & TESTCONTAINERS
    // --------------------------------------------------------------------------
    public static class SpringIntegrationTestSimulation {
        public static void runTestWithAutomaticReset(DatabaseRestoreOrchestrator orchestrator) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("SIMULAÇÃO DE CICLO DE TESTE AUTOMATIZADO COM @Sql(ExecutionPhase.BEFORE_TEST)");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("@SpringBootTest");
            System.out.println("@Testcontainers");
            System.out.println("class OrderProcessingIntegrationTest {");
            System.out.println("    @Test");
            System.out.println("    @Sql(scripts = \"/create-databases.sql\", executionPhase = BEFORE_TEST_METHOD)");
            System.out.println("    void testOrderCreationWithCleanDatabase() {");
            System.out.println("        1. [BEFORE_TEST] Script disparado: Banco de testes volta ao estado zero.");
            System.out.println("        2. [TEST RUN]    Inserindo novo pedido no `sql_store.orders`...");
            System.out.println("        3. [ASSERTION]   Validado que apenas o novo pedido foi afetado!");
            System.out.println("        4. [ISOLATION]   Nenhum dado residual de testes anteriores poluiu o ambiente.");
            System.out.println("    }");
            System.out.println("}");
            System.out.println("--------------------------------------------------------------------------------\n");
        }
    }

    // --------------------------------------------------------------------------
    // 4. MÉTODO MAIN DE EXECUÇÃO E DEMONSTRAÇÃO
    // --------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("       ENGENHARIA DE RESTAURAÇÃO DE BANCO DE DADOS EM JAVA 17 LTS               ");
        System.out.println("================================================================================\n");

        DatabaseRestoreOrchestrator orchestrator = new DatabaseRestoreOrchestrator();

        // 1. Banco 1: sql_store (E-Commerce / Loja Virtual)
        orchestrator.registerDatabase(new DatabaseSchema(
            "sql_store", 
            "E-Commerce: Pedidos, Clientes", 
            List.of(
                new TableMetadata("customers", 10, List.of("customer_id")),
                new TableMetadata("orders", 10, List.of("order_id")),
                new TableMetadata("order_items", 18, List.of("order_id", "product_id")),
                new TableMetadata("order_statuses", 4, List.of("order_status_id")),
                new TableMetadata("order_item_notes", 2, List.of("note_id"))
            )
        ));

        // 2. Banco 2: sql_invoicing (ERP Financeiro / Contabilidade)
        orchestrator.registerDatabase(new DatabaseSchema(
            "sql_invoicing", 
            "Financeiro: Clientes, Faturas", 
            List.of(
                new TableMetadata("clients", 5, List.of("client_id")),
                new TableMetadata("invoices", 17, List.of("invoice_id")),
                new TableMetadata("payments", 8, List.of("payment_id")),
                new TableMetadata("payment_methods", 4, List.of("payment_method_id"))
            )
        ));

        // 3. Banco 3: sql_hr (Recursos Humanos / Gestão de Pessoal)
        orchestrator.registerDatabase(new DatabaseSchema(
            "sql_hr", 
            "RH: Escritórios, Funcionários", 
            List.of(
                new TableMetadata("offices", 10, List.of("office_id")),
                new TableMetadata("employees", 20, List.of("employee_id"))
            )
        ));

        // 4. Banco 4: sql_inventory (Catálogo e Armazém de Produtos)
        orchestrator.registerDatabase(new DatabaseSchema(
            "sql_inventory", 
            "Logística: Estoque de Produtos", 
            List.of(
                new TableMetadata("products", 10, List.of("product_id"))
            )
        ));

        // Executar o processo mestre de restauração (equivalente ao create-databases.sql)
        orchestrator.executeMasterScript("create-databases.sql");

        // Executar auditoria consolidada dos dados restaurados
        orchestrator.runIntegrityAudit();

        // Demonstrar integração corporativa com Spring Boot & Testcontainers
        SpringIntegrationTestSimulation.runTestWithAutomaticReset(orchestrator);

        System.out.println(">>> EXECUÇÃO CONCLUÍDA: Todos os 4 bancos de dados operacionais e prontos para a Seção 5!");
    }
}
