package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 32 (PARTE 3 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Inserção em Lote Massiva, JDBC Batch API (addBatch / executeBatch),
 *       rewriteBatchedStatements=true, Transações Atômicas de Lote e Bulk CSV Load
 * ==============================================================================
 */
public class InsertMultipleRowsPart3FinalMasterDemo {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Registro imutável de Produto Staging em Java 17
    public record StagingProduct(
        int id,
        String name,
        int quantityInStock,
        BigDecimal unitPrice,
        String importedAt
    ) {
        @Override
        public String toString() {
            return String.format("| %10d | %-30s | %17d | %10s | %-19s |",
                id, name, quantityInStock, unitPrice, importedAt);
        }
    }

    // Banco de dados em memória simulado com suporte a transações (ACID)
    public static class StagingDatabase {
        private final Map<Integer, StagingProduct> committedData = new LinkedHashMap<>();
        private final Map<Integer, StagingProduct> uncommittedBuffer = new LinkedHashMap<>();
        private final AtomicInteger idGenerator = new AtomicInteger(0);
        private boolean inTransaction = false;

        public void beginTransaction() {
            this.inTransaction = true;
            this.uncommittedBuffer.clear();
        }

        public void commit() {
            if (!inTransaction) {
                throw new IllegalStateException("Nenhuma transação ativa para commit.");
            }
            this.committedData.putAll(uncommittedBuffer);
            this.uncommittedBuffer.clear();
            this.inTransaction = false;
        }

        public void rollback() {
            if (!inTransaction) {
                throw new IllegalStateException("Nenhuma transação ativa para rollback.");
            }
            this.uncommittedBuffer.clear();
            this.inTransaction = false;
        }

        public int insertDirect(String name, int qty, BigDecimal price) {
            int newId = idGenerator.incrementAndGet();
            String timestamp = LocalDateTime.now().format(ISO_FORMATTER);
            StagingProduct product = new StagingProduct(newId, name, qty, price, timestamp);
            if (inTransaction) {
                uncommittedBuffer.put(newId, product);
            } else {
                committedData.put(newId, product);
            }
            return newId;
        }

        public boolean insertWithId(int explicitId, String name, int qty, BigDecimal price, boolean ignoreCollisions) {
            String timestamp = LocalDateTime.now().format(ISO_FORMATTER);
            StagingProduct product = new StagingProduct(explicitId, name, qty, price, timestamp);

            if (committedData.containsKey(explicitId) || uncommittedBuffer.containsKey(explicitId)) {
                if (ignoreCollisions) {
                    return false; // Ignorado silenciosamente com aviso (INSERT IGNORE)
                } else {
                    throw new RuntimeException("Duplicate entry '" + explicitId + "' for key 'PRIMARY'");
                }
            }

            if (inTransaction) {
                uncommittedBuffer.put(explicitId, product);
            } else {
                committedData.put(explicitId, product);
            }
            if (explicitId > idGenerator.get()) {
                idGenerator.set(explicitId);
            }
            return true;
        }

        public Collection<StagingProduct> getAllCommitted() {
            return Collections.unmodifiableCollection(committedData.values());
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 32 (PARTE 3 FINAL) - BULK INSERTS & JDBC BATCH MASTER     ");
        System.out.println("=========================================================================================\n");

        StagingDatabase db = new StagingDatabase();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: TRANSAÇÃO ATÔMICA EM LOTE COM COMMIT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. TRANSAÇÃO ATÔMICA EM LOTE COM COMMIT (SUCESSO COMPLETO) ---");
        System.out.println("Iniciando Transação: START TRANSACTION;");
        db.beginTransaction();

        System.out.println("Adicionando 3 itens em lote na staging_products:");
        db.insertDirect("Fone Bluetooth Gamer", 50, new BigDecimal("29.90"));
        db.insertDirect("Mouse Sem Fio Ultra", 120, new BigDecimal("15.50"));
        db.insertDirect("Teclado Mecânico RGB", 80, new BigDecimal("45.00"));

        System.out.println("Confirmando lote com gravação atômica: COMMIT;");
        db.commit();
        System.out.println("-> MySQL Console: Query OK, 3 rows affected (0.00 sec)\n");

        printTable(db.getAllCommitted(), "TABELA staging_products APÓS COMMIT DO LOTE 1");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: TRANSAÇÃO ATÔMICA COM ROLLBACK (ABORTANDO O LOTE)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 2. TRANSAÇÃO ATÔMICA EM LOTE COM ROLLBACK (DESFAZENDO TUDO) ---");
        System.out.println("Iniciando Transação: START TRANSACTION;");
        db.beginTransaction();

        System.out.println("Adicionando 2 itens com suspeita de corrupção:");
        db.insertDirect("Item Corrompido 1", 10, new BigDecimal("9.99"));
        db.insertDirect("Item Corrompido 2", 20, new BigDecimal("19.99"));

        System.out.println("Detectada inconsistência nas regras de negócio! Executando: ROLLBACK;");
        db.rollback();
        System.out.println("-> MySQL Console: Query OK, 0 rows affected (0.00 sec) - Lote cancelado!\n");

        printTable(db.getAllCommitted(), "TABELA staging_products APÓS ROLLBACK DO LOTE 2 (INTACTA)");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: BATCH COM 'INSERT IGNORE' (FALHA PARCIAL TOLERADA)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 3. BATCH COM 'INSERT IGNORE' (PULA DUPLICATAS E GRAVA O RESTANTE) ---");
        System.out.println("SQL: INSERT IGNORE INTO staging_products (product_id, name, quantity_in_stock, unit_price) VALUES");
        System.out.println("     (1, 'Tentativa Sobrescrita ID 1', 99, 1.00),");
        System.out.println("     (100, 'Produto Novo Importado 100', 45, 12.30),");
        System.out.println("     (101, 'Produto Novo Importado 101', 30, 8.50);");

        int inserted = 0;
        int warnings = 0;

        // Tentativa de inserir ID 1 (já existe)
        boolean r1 = db.insertWithId(1, "Tentativa Sobrescrita ID 1", 99, new BigDecimal("1.00"), true);
        if (r1) inserted++; else warnings++;

        // Inserção ID 100 (inédito)
        boolean r2 = db.insertWithId(100, "Produto Novo Importado 100", 45, new BigDecimal("12.30"), true);
        if (r2) inserted++; else warnings++;

        // Inserção ID 101 (inédito)
        boolean r3 = db.insertWithId(101, "Produto Novo Importado 101", 30, new BigDecimal("8.50"), true);
        if (r3) inserted++; else warnings++;

        System.out.printf("-> MySQL Console: Query OK, %d rows affected, %d warning (0.01 sec)%n", inserted, warnings);
        System.out.printf("->                Records: 3  Duplicates: %d  Warnings: %d%n%n", warnings, warnings);
        System.out.println("SHOW WARNINGS;");
        System.out.println("+---------+------+-----------------------------------------------------+");
        System.out.println("| Level   | Code | Message                                             |");
        System.out.println("+---------+------+-----------------------------------------------------+");
        System.out.println("| Warning | 1062 | Duplicate entry '1' for key 'staging_products.PRIMARY' |");
        System.out.println("+---------+------+-----------------------------------------------------+\n");

        printTable(db.getAllCommitted(), "TABELA staging_products FINAL APÓS INSERT IGNORE");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: SIMULAÇÃO DO JDBC BATCH API E 'rewriteBatchedStatements=true'
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 4. ARQUITETURA JDBC: PreparedStatement.addBatch() & rewriteBatchedStatements ---");
        System.out.println("No ecossistema Java/Spring profissional:");
        System.out.println("String url = \"jdbc:mysql://localhost:3306/sql_store?rewriteBatchedStatements=true\";");
        System.out.println("try (PreparedStatement pstmt = conn.prepareStatement(\"INSERT INTO staging_products (name, quantity_in_stock, unit_price) VALUES (?, ?, ?)\")) {");
        System.out.println("    for (Product p : listaDe1000Produtos) {");
        System.out.println("        pstmt.setString(1, p.name());");
        System.out.println("        pstmt.setInt(2, p.quantityInStock());");
        System.out.println("        pstmt.setBigDecimal(3, p.unitPrice());");
        System.out.println("        pstmt.addBatch(); // Acumula na memória do driver");
        System.out.println("    }");
        System.out.println("    pstmt.executeBatch(); // Dispara para o MySQL em lote único reescrito!");
        System.out.println("    conn.commit();");
        System.out.println("}");
        System.out.println("\nSem 'rewriteBatchedStatements=true': O driver envia 1000 viagens individuais escondidas!");
        System.out.println("Com 'rewriteBatchedStatements=true': O driver reescreve tudo em 'INSERT INTO ... VALUES (), (), ()'!");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 5: BULK CSV LOAD (LOAD DATA INFILE)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 5. O CAMPEÃO MUNDIAL DA VELOCIDADE: LOAD DATA INFILE ---");
        System.out.println("Velocidade para inserir 1.000.000 de registros no MySQL:");
        System.out.println("1. Loop de Single Inserts (1 por vez) : ~45 a 60 minutos!");
        System.out.println("2. Batch Insert tradicional (Chunk 1000): ~15 a 25 segundos.");
        System.out.println("3. LOAD DATA INFILE (CSV nativo do disco): ~1.8 a 3.2 segundos! (Mais de 1000x mais rápido!).");
        System.out.println("=========================================================================================\n");
    }

    private static void printTable(Collection<StagingProduct> products, String title) {
        System.out.println(">>> " + title + ":");
        System.out.println("+------------+--------------------------------+-------------------+------------+---------------------+");
        System.out.println("| product_id | name                           | quantity_in_stock | unit_price | imported_at         |");
        System.out.println("+------------+--------------------------------+-------------------+------------+---------------------+");
        for (StagingProduct p : products) {
            System.out.println(p);
        }
        System.out.println("+------------+--------------------------------+-------------------+------------+---------------------+");
    }
}
