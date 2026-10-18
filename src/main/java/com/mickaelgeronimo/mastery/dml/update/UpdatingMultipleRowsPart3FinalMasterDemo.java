package com.mickaelgeronimo.mastery.dml.update;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 36 (PARTE 3 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Atualizações em Massa de Alta Escala (Batch Chunking / Throttling),
 *       A Cláusula LIMIT no UPDATE, Controle de Memória no Hibernate e Dicionário
 * ==============================================================================
 */
public class UpdatingMultipleRowsPart3FinalMasterDemo {

    public static class Customer {
        private final int id;
        private final String firstName;
        private int points;

        public Customer(int id, String firstName, int points) {
            this.id = id;
            this.firstName = firstName;
            this.points = points;
        }

        public int getId() { return id; }
        public String getFirstName() { return firstName; }
        public int getPoints() { return points; }

        public void setPoints(int points) {
            this.points = points;
        }

        public void addPoints(int delta) {
            this.points += delta;
        }

        @Override
        public String toString() {
            return String.format("| %11d | %-12s | %6d |", id, firstName, points);
        }
    }

    public static class ChunkingDatabaseService {
        private final List<Customer> databaseTable = new ArrayList<>();

        public void addCustomer(Customer c) {
            databaseTable.add(c);
        }

        public List<Customer> findAll() {
            return Collections.unmodifiableList(databaseTable);
        }

        /**
         * Simula a execução do comando com LIMIT nativo do MySQL:
         * UPDATE customers SET points = points + :delta WHERE points < 1000 ORDER BY customer_id LIMIT :chunkSize
         */
        public int updateChunkDelta(int delta, int maxPoints, int chunkSize) {
            List<Customer> targetBatch = databaseTable.stream()
                .filter(c -> c.getPoints() < maxPoints)
                .sorted(Comparator.comparingInt(Customer::getId))
                .limit(chunkSize)
                .collect(Collectors.toList());

            for (Customer c : targetBatch) {
                c.addPoints(delta);
            }

            return targetBatch.size(); // Retorna o equivalente a ROW_COUNT()
        }

        /**
         * Simula a Stored Procedure corporativa 'sp_chunked_bonus_update':
         * Nivelamento seguro de pontos em lotes fracionados (Throttling) até que
         * nenhuma linha pendente reste na base.
         */
        public int executeFullChunkedBatch(int targetPoints, int chunkSize) {
            int totalProcessed = 0;
            int iteration = 1;

            System.out.println("--- INICIANDO PROCESSAMENTO EM LOTES (CHUNKING / THROTTLING) ---");
            System.out.printf("Tamanho do Lote (Chunk Size): %d registros por transação%n%n", chunkSize);

            while (true) {
                List<Customer> targetBatch = databaseTable.stream()
                    .filter(c -> c.getPoints() < targetPoints)
                    .sorted(Comparator.comparingInt(Customer::getId))
                    .limit(chunkSize)
                    .collect(Collectors.toList());

                if (targetBatch.isEmpty()) {
                    System.out.println("-> [Fim dos Lotes]: ROW_COUNT() = 0. Nenhuma linha restante para atualizar.");
                    break;
                }

                for (Customer c : targetBatch) {
                    c.setPoints(targetPoints);
                }

                int affectedInThisChunk = targetBatch.size();
                totalProcessed += affectedInThisChunk;
                System.out.printf("   [Lote %02d]: %d registros alterados para %d e transação COMMITADA com sucesso! (Total acumulado: %d)%n",
                    iteration, affectedInThisChunk, targetPoints, totalProcessed);

                iteration++;
            }

            return totalProcessed;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 36 (PARTE 3 FINAL) - CHUNKING & LIMIT NO UPDATE           ");
        System.out.println("=========================================================================================\n");

        ChunkingDatabaseService service = new ChunkingDatabaseService();

        // 10 clientes oficiais do Mosh
        service.addCustomer(new Customer(1, "Babara", 2323));
        service.addCustomer(new Customer(2, "Ines", 947));     // < 1000
        service.addCustomer(new Customer(3, "Freddi", 3017));
        service.addCustomer(new Customer(4, "Ambur", 357));    // < 1000
        service.addCustomer(new Customer(5, "Clemmie", 3725));
        service.addCustomer(new Customer(6, "Elka", 3073));
        service.addCustomer(new Customer(7, "Ilene", 1722));
        service.addCustomer(new Customer(8, "Freddi", 994));   // < 1000
        service.addCustomer(new Customer(9, "Ruth", 1068));
        service.addCustomer(new Customer(10, "Levy", 796));    // < 1000

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: INSPEÇÃO PRÉVIA DOS CLIENTES COM MENOS DE 1000 PONTOS
        // --------------------------------------------------------------------------------------
        System.out.println(">>> 1. ESTADO INICIAL: CLIENTES COM PONTOS < 1000 (CANDIDATOS A ATUALIZAÇÃO)");
        List<Customer> lowPoints = service.findAll().stream()
            .filter(c -> c.getPoints() < 1000)
            .collect(Collectors.toList());
        printTable(lowPoints);
        System.out.println("Total de clientes com menos de 1000 pontos: " + lowPoints.size() + "\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: ATUALIZAÇÃO COM LIMIT CIRÚRGICO (MYSQL UPDATE ... LIMIT 2)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. DEMONSTRAÇÃO DO 'UPDATE ... LIMIT 2' DO MYSQL ---");
        System.out.println("SQL: UPDATE customers SET points = points + 25 WHERE points < 1000 ORDER BY customer_id LIMIT 2;");

        int singleLimitRows = service.updateChunkDelta(25, 1000, 2);
        System.out.println("-> Console MySQL: Query OK, " + singleLimitRows + " rows affected (0.01 sec)");
        System.out.println("-> SELECT ROW_COUNT(); -> Retorna: " + singleLimitRows + "\n");

        System.out.println(">>> TABELA APÓS O UPDATE COM LIMIT 2 (Apenas os 2 primeiros receberam +25):");
        printTable(service.findAll().stream().filter(c -> c.getId() == 2 || c.getId() == 4).collect(Collectors.toList()));
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: EXECUÇÃO COMPLETA DA STORED PROCEDURE DE CHUNKING
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. EXECUÇÃO DA PROCEDURE CORPORATIVA 'sp_chunked_bonus_update' ---");
        System.out.println("SQL: CALL sp_chunked_bonus_update(2, @total_atualizado);\n");

        // Nivelando todos os que ainda estão abaixo de 1000 para exatamente 1000 em lotes de 2:
        int totalUpdated = service.executeFullChunkedBatch(1000, 2);

        System.out.println("\n-> Resumo de Execução da Stored Procedure:");
        System.out.println("   Total de linhas alteradas em fatias seguras: " + totalUpdated);
        System.out.println("   Nenhum lock prolongado foi mantido na tabela!");
        System.out.println("   A replicação para bancos réplica permaneceu 100% fluida e sem atrasos!\n");

        System.out.println(">>> ESTADO FINAL DE TODOS OS CLIENTES DA BASE:");
        printTable(service.findAll());

        System.out.println("\n=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO DO CAPÍTULO 36 (PARTE 3 FINAL) CONCLUÍDA COM 100% DE ÊXITO!              ");
        System.out.println("=========================================================================================");
    }

    private static void printTable(List<Customer> list) {
        System.out.println("+-------------+--------------+--------+");
        System.out.println("| customer_id | first_name   | points |");
        System.out.println("+-------------+--------------+--------+");
        for (Customer c : list) {
            System.out.println(c);
        }
        System.out.println("+-------------+--------------+--------+");
    }
}
