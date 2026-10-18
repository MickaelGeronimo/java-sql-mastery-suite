package com.mickaelgeronimo.mastery.dml.copy;

import java.time.LocalDate;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 34 (PARTE 3 - FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Clonagem Perfeita com LIKE (Preservação de PK e AI),
 *       Tabelas Temporárias de Sessão e Migração Atômica (Blue-Green Table Swap)
 * ==============================================================================
 */
public class CreatingCopyOfTablePart3FinalDemo {

    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public record TableMetadata(String tableName, boolean hasPrimaryKey, boolean hasAutoIncrement, int rowCount) {
        @Override
        public String toString() {
            return String.format("| %-20s | %-12s | %-15s | %10d |",
                tableName,
                hasPrimaryKey ? "SIM (PRI)" : "NÃO (Perdido)",
                hasAutoIncrement ? "SIM (Ativo)" : "NÃO (Perdido)",
                rowCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 34 (PARTE 3 FINAL) - CLONAGEM PERFEITA & MIGRAÇÃO ATÔMICA ");
        System.out.println("=========================================================================================\n");

        // 1. Tabela Original de Produção
        List<Order> ordersProduction = new ArrayList<>(List.of(
            new Order(1, 6, LocalDate.of(2019, 1, 30), (byte) 1),
            new Order(2, 7, LocalDate.of(2018, 8, 2), (byte) 2),
            new Order(3, 8, LocalDate.of(2017, 12, 1), (byte) 1)
        ));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A BATALHA DAS ESTRATÉGIAS DE CÓPIA (AS SELECT vs LIKE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. A BATALHA DAS ESTRATÉGIAS: AS SELECT vs LIKE ---");

        TableMetadata original = new TableMetadata("orders (Original)", true, true, ordersProduction.size());
        TableMetadata ctasCopy = new TableMetadata("orders_archived (CTAS)", false, false, ordersProduction.size());
        TableMetadata likeOnly = new TableMetadata("orders_like (Só LIKE)", true, true, 0);
        TableMetadata perfectCopy = new TableMetadata("orders_perfect (LIKE+INSERT)", true, true, ordersProduction.size());

        System.out.println("+----------------------+--------------+-----------------+------------+");
        System.out.println("| Nome da Tabela       | Chave Primária| Auto-Incremento | Qtd Linhas |");
        System.out.println("+----------------------+--------------+-----------------+------------+");
        System.out.println(original);
        System.out.println(ctasCopy);
        System.out.println(likeOnly);
        System.out.println(perfectCopy);
        System.out.println("+----------------------+--------------+-----------------+------------+\n");

        System.out.println("💡 CONCLUSÃO DE ARQUITETURA:");
        System.out.println("  - CREATE TABLE ... AS SELECT: Copia os dados, mas PERDE PK e Auto-Increment!");
        System.out.println("  - CREATE TABLE ... LIKE: Clona 100% da estrutura com PK e AI, mas fica VAZIA!");
        System.out.println("  - LIKE + INSERT INTO ... SELECT: O padrão profissional! Clona estrutura E popula dados!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: TABELA TEMPORÁRIA EM SESSÃO (CREATE TEMPORARY TABLE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. TABELA TEMPORÁRIA EM MEMÓRIA: CREATE TEMPORARY TABLE ---");
        System.out.println("Simulando criação de: CREATE TEMPORARY TABLE temp_order_summary AS ...");
        Map<Integer, Integer> tempCustomerOrderCount = new HashMap<>();
        for (Order o : ordersProduction) {
            tempCustomerOrderCount.merge(o.customerId(), 1, Integer::sum);
        }

        System.out.println("+-------------+--------------+");
        System.out.println("| customer_id | total_orders |");
        System.out.println("+-------------+--------------+");
        tempCustomerOrderCount.forEach((cid, count) -> System.out.printf("| %11d | %12d |%n", cid, count));
        System.out.println("+-------------+--------------+");
        System.out.println("-> A tabela temporária é destruída automaticamente pelo banco ao encerrar a conexão!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: ZERO-DOWNTIME TABLE SWAP (RENAME TABLE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. MIGRAÇÃO CORPORATIVA ZERO-DOWNTIME: RENAME TABLE SWAP ---");
        System.out.println("Cenário: Atualizando a tabela 'orders' para uma nova versão sem derrubar o backend:");
        System.out.println("Passo 1: CREATE TABLE orders_v2 LIKE orders;");
        System.out.println("Passo 2: Migra dados com novos índices/colunas...");
        System.out.println("Passo 3: RENAME TABLE orders TO orders_old, orders_v2 TO orders;");
        System.out.println("-> Executado atomicamente em 1 milissegundo no MySQL!");
        System.out.println("-> A aplicação Java continua funcionando sem receber erros de indisponibilidade.");
        System.out.println("=========================================================================================\n");
    }
}
