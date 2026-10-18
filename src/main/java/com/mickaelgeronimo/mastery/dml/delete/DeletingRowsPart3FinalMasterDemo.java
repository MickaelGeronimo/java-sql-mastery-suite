package com.mickaelgeronimo.mastery.dml.delete;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 38 (PARTE 3 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: A Batalha DELETE vs TRUNCATE vs DROP, Comportamento do AUTO_INCREMENT,
 *       Chunking de Alta Escala (Batch Purge) e o Dicionário Supremo da Exclusão
 * ==============================================================================
 */
public class DeletingRowsPart3FinalMasterDemo {

    public static class TempInvoice {
        private final int tempId;
        private final String invoiceNumber;
        private final BigDecimal amount;

        public TempInvoice(int tempId, String invoiceNumber, BigDecimal amount) {
            this.tempId = tempId;
            this.invoiceNumber = invoiceNumber;
            this.amount = amount;
        }

        public int getTempId() { return tempId; }
        public String getInvoiceNumber() { return invoiceNumber; }
        public BigDecimal getAmount() { return amount; }

        @Override
        public String toString() {
            return String.format("| %7d | %-12s | %10s |", tempId, invoiceNumber, amount);
        }
    }

    public static class DatabaseTableEngine {
        private final List<TempInvoice> table = new ArrayList<>();
        private final AtomicInteger autoIncrementSequence = new AtomicInteger(1);

        public TempInvoice insert(String number, BigDecimal amount) {
            int newId = autoIncrementSequence.getAndIncrement();
            TempInvoice inv = new TempInvoice(newId, number, amount);
            table.add(inv);
            return inv;
        }

        public List<TempInvoice> findAll() {
            return Collections.unmodifiableList(table);
        }

        public int getNextAutoIncrementId() {
            return autoIncrementSequence.get();
        }

        /**
         * Simula o DELETE DML:
         * Remove tuplas linha a linha e PRESERVA o ponteiro do AUTO_INCREMENT!
         */
        public int deleteWhereIdIn(Set<Integer> ids) {
            int before = table.size();
            table.removeIf(i -> ids.contains(i.getTempId()));
            return before - table.size();
        }

        /**
         * Simula o TRUNCATE DDL:
         * Destrói a estrutura de dados física e RESETA o contador de AUTO_INCREMENT para 1!
         */
        public void truncateTable() {
            table.clear();
            autoIncrementSequence.set(1); // RESET FÍSICO DO CONTADOR!
        }

        /**
         * Simula o Chunked Delete com LIMIT (Batch Purge):
         * Apaga em pedaços de 'chunkSize' até que a tabela esteja limpa sem travar o banco.
         */
        public int executeChunkedDelete(int chunkSize) {
            int totalDeleted = 0;
            int iteration = 1;

            System.out.println("--- INICIANDO PURGE EM LOTES FRACIONADOS (CHUNKING COM LIMIT) ---");
            System.out.printf("Tamanho da fatia de deleção: %d registros por transação%n%n", chunkSize);

            while (!table.isEmpty()) {
                // Seleciona a fatia limitada
                List<TempInvoice> chunk = table.stream()
                    .sorted(Comparator.comparingInt(TempInvoice::getTempId))
                    .limit(chunkSize)
                    .collect(Collectors.toList());

                Set<Integer> idsToDelete = chunk.stream().map(TempInvoice::getTempId).collect(Collectors.toSet());
                int deletedInChunk = deleteWhereIdIn(idsToDelete);
                totalDeleted += deletedInChunk;

                System.out.printf("   [Lote %02d]: %d registros deletados e transação COMMITADA! (Total apagado: %d)%n",
                    iteration, deletedInChunk, totalDeleted);

                iteration++;
            }

            System.out.println("-> [Fim do Purge]: ROW_COUNT() = 0. Tabela 100% limpa com zero sobrecarga de locks!\n");
            return totalDeleted;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 38 (PARTE 3 FINAL) - A BATALHA DELETE VS TRUNCATE & CHUNKING               ");
        System.out.println("==========================================================================================================\n");

        DatabaseTableEngine db = new DatabaseTableEngine();

        // 1. Inserindo 5 faturas iniciais
        db.insert("INV-001", new BigDecimal("100.00"));
        db.insert("INV-002", new BigDecimal("150.00"));
        db.insert("INV-003", new BigDecimal("200.00"));
        db.insert("INV-004", new BigDecimal("250.00"));
        db.insert("INV-005", new BigDecimal("300.00"));

        System.out.println(">>> 1. ESTADO INICIAL DA TABELA (5 Registros gerados):");
        printTable(db.findAll());
        System.out.println("Próximo valor da sequência AUTO_INCREMENT: " + db.getNextAutoIncrementId() + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O EFEITO DO 'DELETE' NO AUTO_INCREMENT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. EXECUTANDO 'DELETE' DOS IDs 1 E 2 ---");
        System.out.println("SQL: DELETE FROM invoices_temp_purge WHERE temp_id IN (1, 2);");
        int deleted = db.deleteWhereIdIn(Set.of(1, 2));
        System.out.println("-> Linhas apagadas pelo DELETE: " + deleted);

        System.out.println("Inserindo um novo registro ('INV-006')...");
        TempInvoice inv6 = db.insert("INV-006", new BigDecimal("400.00"));
        System.out.println("-> Novo registro inserido: ID = " + inv6.getTempId() + " (" + inv6.getInvoiceNumber() + ")");
        System.out.println("-> CONSTATAÇÃO VITAL: O comando DELETE preservou a sequência! O ID foi para 6 e NÃO reutilizou 1 ou 2!\n");

        printTable(db.findAll());
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O EFEITO DO 'TRUNCATE' NO AUTO_INCREMENT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. EXECUTANDO 'TRUNCATE TABLE' (RESET COMPLETO DE DDL) ---");
        System.out.println("SQL: TRUNCATE TABLE invoices_temp_purge;");
        db.truncateTable();
        System.out.println("-> Tabela limpa instantaneamente!");
        System.out.println("-> Contador AUTO_INCREMENT resetado para: " + db.getNextAutoIncrementId());

        System.out.println("Inserindo novo registro ('INV-RESTART')...");
        TempInvoice invRestart = db.insert("INV-RESTART", new BigDecimal("500.00"));
        System.out.println("-> Novo registro inserido: ID = " + invRestart.getTempId() + " (" + invRestart.getInvoiceNumber() + ")");
        System.out.println("-> CONSTATAÇÃO VITAL: O TRUNCATE zerou o AUTO_INCREMENT de volta para 1!\n");

        printTable(db.findAll());
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: CHUNKED DELETION (EXCLUSÃO EM LOTES FRACIONADOS DE ALTA ESCALA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. DEMONSTRAÇÃO DE PURGE EM LOTES (CHUNKING COM LIMIT) ---");
        // Populando dados para o purge
        db.truncateTable();
        for (int i = 1; i <= 6; i++) {
            db.insert("INV-BATCH-" + i, new BigDecimal(i * 100));
        }
        System.out.println("Tabela populada com 6 faturas de teste.");
        printTable(db.findAll());
        System.out.println();

        // Executando em lotes de 2 registros por transação
        int totalPurged = db.executeChunkedDelete(2);
        System.out.println("Total geral de registros expurgados em lotes seguros: " + totalPurged);
        System.out.println("Registros restantes na tabela: " + db.findAll().size());

        System.out.println("\n==========================================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 38 (PARTE 3 FINAL) CONCLUÍDA COM 100% DE ÊXITO!                               ");
        System.out.println("==========================================================================================================");
    }

    private static void printTable(List<TempInvoice> list) {
        System.out.println("+---------+--------------+------------+");
        System.out.println("| temp_id | invoice_num  | amount     |");
        System.out.println("+---------+--------------+------------+");
        for (TempInvoice inv : list) {
            System.out.println(inv);
        }
        System.out.println("+---------+--------------+------------+");
    }
}
