package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 33 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Inserindo Linhas Hierárquicas (Pai e Filho / Parent-Child),
 *       Simulação do LAST_INSERT_ID(), JDBC Generated Keys e JPA Cascade
 * ==============================================================================
 */
public class InsertHierarchicalRowsPart1Demo {

    // Registros imutáveis de Domínio (Java 17 Records)
    public record Order(
        int orderId,
        int customerId,
        LocalDate orderDate,
        byte status,
        String comments,
        LocalDate shippedDate,
        Integer shipperId
    ) {
        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-10s | %6d | %-12s | %-12s | %-10s |",
                orderId, customerId, orderDate, status,
                comments == null ? "NULL" : comments,
                shippedDate == null ? "NULL" : shippedDate.toString(),
                shipperId == null ? "NULL" : shipperId.toString());
        }
    }

    public record OrderItem(
        int orderId,
        int productId,
        int quantity,
        BigDecimal unitPrice
    ) {
        @Override
        public String toString() {
            return String.format("| %8d | %10d | %8d | %10s |",
                orderId, productId, quantity, unitPrice);
        }
    }

    // Simulação do Banco de Dados com Gerador de Auto-Incremento e Sessão
    public static class RelationalDatabaseSession {
        private static final AtomicInteger GLOBAL_ORDER_SEQ = new AtomicInteger(10); // Próximo: 11
        private final List<Order> ordersTable = new ArrayList<>();
        private final List<OrderItem> orderItemsTable = new ArrayList<>();

        // Simula o buffer de sessão exclusivo do MySQL (Thread-Safe por Conexão)
        private int lastInsertId = 0;

        public int insertOrder(int customerId, LocalDate orderDate, byte status) {
            int newOrderId = GLOBAL_ORDER_SEQ.incrementAndGet();
            Order newOrder = new Order(newOrderId, customerId, orderDate, status, null, null, null);
            ordersTable.add(newOrder);
            this.lastInsertId = newOrderId; // Atualiza a variável de sessão do cliente
            return newOrderId;
        }

        public int getLastInsertId() {
            return this.lastInsertId;
        }

        public void insertOrderItems(List<OrderItem> items) {
            this.orderItemsTable.addAll(items);
        }

        public List<Order> getOrders() { return Collections.unmodifiableList(ordersTable); }
        public List<OrderItem> getOrderItems() { return Collections.unmodifiableList(orderItemsTable); }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 33 (PARTE 1) - INSERINDO LINHAS HIERÁRQUICAS (PAI/FILHO)  ");
        System.out.println("=========================================================================================\n");

        RelationalDatabaseSession session = new RelationalDatabaseSession();

        // --------------------------------------------------------------------------------------
        // PASSO 1: INSERINDO O REGISTRO PAI NA TABELA orders
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 1: INSERÇÃO DO REGISTRO PAI (orders) ---");
        System.out.println("SQL: INSERT INTO orders (customer_id, order_date, status) VALUES (1, '2019-01-02', 1);");

        int generatedId = session.insertOrder(1, LocalDate.of(2019, 1, 2), (byte) 1);
        System.out.println("-> MySQL Console: Query OK, 1 row affected (0.01 sec)");
        System.out.println("-> Chave Primária gerada automaticamente pelo AUTO_INCREMENT: " + generatedId + "\n");

        // --------------------------------------------------------------------------------------
        // PASSO 2: EXECUTANDO SELECT LAST_INSERT_ID()
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 2: RECUPERAÇÃO DA CHAVE COM LAST_INSERT_ID() ---");
        System.out.println("SQL: SELECT LAST_INSERT_ID();");
        System.out.println("+------------------+");
        System.out.println("| LAST_INSERT_ID() |");
        System.out.println("+------------------+");
        System.out.printf("| %16d |%n", session.getLastInsertId());
        System.out.println("+------------------+\n");

        // --------------------------------------------------------------------------------------
        // PASSO 3: INSERINDO OS FILHOS (order_items) AMARRADOS AO PAI
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 3: INSERÇÃO DOS REGISTROS FILHOS (order_items) ---");
        System.out.println("SQL: INSERT INTO order_items VALUES");
        System.out.println("     (LAST_INSERT_ID(), 1, 1, 2.95),");
        System.out.println("     (LAST_INSERT_ID(), 2, 1, 3.95);");

        int parentId = session.getLastInsertId();
        List<OrderItem> items = List.of(
            new OrderItem(parentId, 1, 1, new BigDecimal("2.95")),
            new OrderItem(parentId, 2, 1, new BigDecimal("3.95"))
        );
        session.insertOrderItems(items);
        System.out.println("-> MySQL Console: Query OK, 2 rows affected (0.00 sec)");
        System.out.println("->                Records: 2  Duplicates: 0  Warnings: 0\n");

        // --------------------------------------------------------------------------------------
        // PASSO 4: EXIBIÇÃO DAS TABELAS NO BANCO
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 4: VERIFICAÇÃO DAS TABELAS RELACIONADAS ---");
        System.out.println(">>> TABELA orders (PAI):");
        System.out.println("+----------+-------------+------------+--------+--------------+--------------+------------+");
        System.out.println("| order_id | customer_id | order_date | status | comments     | shipped_date | shipper_id |");
        System.out.println("+----------+-------------+------------+--------+--------------+--------------+------------+");
        for (Order o : session.getOrders()) {
            System.out.println(o);
        }
        System.out.println("+----------+-------------+------------+--------+--------------+--------------+------------+\n");

        System.out.println(">>> TABELA order_items (FILHOS):");
        System.out.println("+----------+------------+----------+------------+");
        System.out.println("| order_id | product_id | quantity | unit_price |");
        System.out.println("+----------+------------+----------+------------+");
        for (OrderItem oi : session.getOrderItems()) {
            System.out.println(oi);
        }
        System.out.println("+----------+------------+----------+------------+\n");

        // --------------------------------------------------------------------------------------
        // PASSO 5: A PONTE COM O DESENVOLVIMENTO JAVA CORPORATIVO (JDBC vs JPA)
        // --------------------------------------------------------------------------------------
        System.out.println("--- PASSO 5: COMO FAZEMOS ISSO NO JAVA PROFISSIONAL ---");
        System.out.println("1. No JDBC Nativo (Statement.RETURN_GENERATED_KEYS):");
        System.out.println("   String sqlOrder = \"INSERT INTO orders (customer_id, order_date, status) VALUES (?, ?, ?)\";");
        System.out.println("   try (PreparedStatement ps = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {");
        System.out.println("       ps.setInt(1, 1);");
        System.out.println("       ps.setDate(2, java.sql.Date.valueOf(\"2019-01-02\"));");
        System.out.println("       ps.setByte(3, (byte) 1);");
        System.out.println("       ps.executeUpdate();");
        System.out.println("       try (ResultSet rs = ps.getGeneratedKeys()) {");
        System.out.println("           if (rs.next()) {");
        System.out.println("               int novoOrderId = rs.getInt(1); // Exatamente o LAST_INSERT_ID()!");
        System.out.println("               // Grava os order_items usando 'novoOrderId'");
        System.out.println("           }");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println("\n2. No Spring Data JPA / Hibernate (CascadeType.ALL):");
        System.out.println("   @Entity public class Order {");
        System.out.println("       @Id @GeneratedValue(strategy = GenerationType.IDENTITY)");
        System.out.println("       private Long orderId;");
        System.out.println("       @OneToMany(mappedBy = \"order\", cascade = CascadeType.ALL)");
        System.out.println("       private List<OrderItem> items = new ArrayList<>();");
        System.out.println("   }");
        System.out.println("   // O Hibernate salva o Pai, descobre o ID e salva os Filhos em cascata automaticamente!");
        System.out.println("=========================================================================================\n");
    }
}
