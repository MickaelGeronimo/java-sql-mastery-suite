package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 33 (PARTE 2): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Inserção Hierárquica em 3 Níveis (Pai -> Filho -> Neto),
 *       Prevenção de Órfãos com Transações ACID, Erro 1452 (FK) e Rollback
 * ==============================================================================
 */
public class InsertHierarchicalRowsPart2AdvancedDemo {

    // 1. Entidades de Domínio Imutáveis (Java 17 Records)
    public record Order(int orderId, int customerId, LocalDate orderDate, byte status) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d |", orderId, customerId, orderDate, status);
        }
    }

    public record OrderItem(int orderId, int productId, int quantity, BigDecimal unitPrice) {
        @Override
        public String toString() {
            return String.format("| %8d | %10d | %8d | %10s |", orderId, productId, quantity, unitPrice);
        }
    }

    public record OrderItemNote(int noteId, int orderId, int productId, String note) {
        @Override
        public String toString() {
            return String.format("| %7d | %8d | %10d | %-45s |", noteId, orderId, productId, note);
        }
    }

    // Exceção customizada simulando o Erro 1452 do MySQL
    public static class ForeignKeyConstraintViolationException extends RuntimeException {
        public ForeignKeyConstraintViolationException(String message) {
            super(message);
        }
    }

    // Gerenciador de Banco de Dados com Suporte a ACID e Integridade Referencial
    public static class RelationalTransactionalEngine {
        private final Set<Integer> existingCustomers = Set.of(1, 2, 3, 4, 5);
        private final Set<Integer> existingProducts = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        private final AtomicInteger orderIdGenerator = new AtomicInteger(11); // Começa em 12
        private final AtomicInteger noteIdGenerator = new AtomicInteger(0);    // Começa em 1

        // Tabelas persistidas no disco
        private final List<Order> ordersTable = new ArrayList<>();
        private final List<OrderItem> orderItemsTable = new ArrayList<>();
        private final List<OrderItemNote> notesTable = new ArrayList<>();

        // Buffers transacionais em memória (uncommitted)
        private final List<Order> txOrders = new ArrayList<>();
        private final List<OrderItem> txOrderItems = new ArrayList<>();
        private final List<OrderItemNote> txNotes = new ArrayList<>();
        private boolean inTransaction = false;

        public void beginTransaction() {
            this.inTransaction = true;
            this.txOrders.clear();
            this.txOrderItems.clear();
            this.txNotes.clear();
        }

        public void commit() {
            if (!inTransaction) throw new IllegalStateException("Nenhuma transação ativa.");
            ordersTable.addAll(txOrders);
            orderItemsTable.addAll(txOrderItems);
            notesTable.addAll(txNotes);
            clearTx();
        }

        public void rollback() {
            if (!inTransaction) throw new IllegalStateException("Nenhuma transação ativa.");
            clearTx();
        }

        private void clearTx() {
            txOrders.clear();
            txOrderItems.clear();
            txNotes.clear();
            inTransaction = false;
        }

        // Inserção do Pai (Nível 1)
        public int insertOrder(int customerId, LocalDate orderDate, byte status) {
            if (!existingCustomers.contains(customerId)) {
                throw new ForeignKeyConstraintViolationException(
                    "ERROR 1452 (23000): Cannot add child row: customer_id '" + customerId + "' does not exist in customers."
                );
            }
            int newOrderId = orderIdGenerator.incrementAndGet();
            Order order = new Order(newOrderId, customerId, orderDate, status);
            if (inTransaction) txOrders.add(order); else ordersTable.add(order);
            return newOrderId;
        }

        // Inserção do Filho (Nível 2)
        public void insertOrderItem(int orderId, int productId, int quantity, BigDecimal unitPrice) {
            if (!existingProducts.contains(productId)) {
                throw new ForeignKeyConstraintViolationException(
                    "ERROR 1452 (23000): Cannot add child row: product_id '" + productId + "' does not exist in products."
                );
            }
            OrderItem item = new OrderItem(orderId, productId, quantity, unitPrice);
            if (inTransaction) txOrderItems.add(item); else orderItemsTable.add(item);
        }

        // Inserção do Neto (Nível 3)
        public int insertOrderItemNote(int orderId, int productId, String note) {
            int newNoteId = noteIdGenerator.incrementAndGet();
            OrderItemNote itemNote = new OrderItemNote(newNoteId, orderId, productId, note);
            if (inTransaction) txNotes.add(itemNote); else notesTable.add(itemNote);
            return newNoteId;
        }

        public List<Order> getOrders() { return ordersTable; }
        public List<OrderItem> getOrderItems() { return orderItemsTable; }
        public List<OrderItemNote> getNotes() { return notesTable; }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 33 (PARTE 2) - TRANSAÇÕES HIERÁRQUICAS E INTEGRIDADE      ");
        System.out.println("=========================================================================================\n");

        RelationalTransactionalEngine engine = new RelationalTransactionalEngine();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: HIERARQUIA COMPLETA DE 3 NÍVEIS COM SUCESSO E COMMIT
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. INSERÇÃO HIERÁRQUICA DE 3 NÍVEIS COM SUCESSO (COMMIT) ---");
        System.out.println("Iniciando Transação: START TRANSACTION;");
        engine.beginTransaction();

        try {
            // Nível 1: Pai (Order)
            int orderId = engine.insertOrder(2, LocalDate.of(2019, 1, 5), (byte) 1);
            System.out.println("Passo 1 (Pai)  -> Pedido inserido com ID: " + orderId);

            // Nível 2: Filhos (OrderItems)
            engine.insertOrderItem(orderId, 3, 2, new BigDecimal("3.35"));
            engine.insertOrderItem(orderId, 4, 1, new BigDecimal("9.00"));
            System.out.println("Passo 2 (Filho)-> 2 itens vinculados ao Pedido " + orderId);

            // Nível 3: Neto (OrderItemNote com seu próprio Auto-Increment)
            int noteId = engine.insertOrderItemNote(orderId, 3, "Favor embalar para presente com laço azul");
            System.out.println("Passo 3 (Neto) -> Nota de personalização inserida com Note ID: " + noteId);

            engine.commit();
            System.out.println("Passo 4        -> Transação confirmada com sucesso: COMMIT;\n");
        } catch (Exception ex) {
            engine.rollback();
            System.err.println("Falha inesperada: " + ex.getMessage());
        }

        printState(engine, "ESTADO DO BANCO APÓS O COMMIT DA HIERARQUIA DO PEDIDO 12");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: FALHA NO FILHO (ERRO 1452) E ROLLBACK AUTOMÁTICO (SEM PEDIDOS ÓRFÃOS!)
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- 2. TENTATIVA DE INSERÇÃO COM PRODUTO INEXISTENTE (ERRO 1452 E ROLLBACK) ---");
        System.out.println("Iniciando Transação: START TRANSACTION;");
        engine.beginTransaction();

        try {
            // Nível 1: Pai é inserido com sucesso
            int orderIdFalho = engine.insertOrder(3, LocalDate.of(2019, 1, 10), (byte) 1);
            System.out.println("Passo 1 (Pai)  -> Pedido temporário criado com ID: " + orderIdFalho);

            // Nível 2: Tentativa de inserir o produto 999 (NÃO EXISTE!)
            System.out.println("Passo 2 (Filho)-> Tentando inserir Produto ID 999...");
            engine.insertOrderItem(orderIdFalho, 999, 1, new BigDecimal("19.90"));

            engine.commit(); // Nunca chegará aqui!
        } catch (ForeignKeyConstraintViolationException ex) {
            System.out.println("-> CAPTURADA EXCEÇÃO DE INTEGRIDADE:");
            System.out.println("   " + ex.getMessage());
            System.out.println("-> Acionando Proteção de Arquitetura: ROLLBACK;");
            engine.rollback();
            System.out.println("-> Pedido Pai e itens intermediários foram 100% DESFEITOS! Nenhum órfão gerado.\n");
        }

        printState(engine, "ESTADO DO BANCO APÓS O ROLLBACK (PERMANECE 100% LIMPO)");
    }

    private static void printState(RelationalTransactionalEngine engine, String title) {
        System.out.println(">>> " + title + ":");
        System.out.println("TABELA orders (PAI):");
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("| order_id | customer_id | order_date | status |");
        System.out.println("+----------+-------------+------------+--------+");
        for (Order o : engine.getOrders()) System.out.println(o);
        System.out.println("+----------+-------------+------------+--------+");

        System.out.println("TABELA order_items (FILHOS):");
        System.out.println("+----------+------------+----------+------------+");
        System.out.println("| order_id | product_id | quantity | unit_price |");
        System.out.println("+----------+------------+----------+------------+");
        for (OrderItem oi : engine.getOrderItems()) System.out.println(oi);
        System.out.println("+----------+------------+----------+------------+");

        System.out.println("TABELA order_item_notes (NETOS):");
        System.out.println("+---------+----------+------------+-----------------------------------------------+");
        System.out.println("| note_id | order_id | product_id | note                                          |");
        System.out.println("+---------+----------+------------+-----------------------------------------------+");
        for (OrderItemNote n : engine.getNotes()) System.out.println(n);
        System.out.println("+---------+----------+------------+-----------------------------------------------+\n");
    }
}
