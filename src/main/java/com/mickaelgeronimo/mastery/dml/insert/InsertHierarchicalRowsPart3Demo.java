package com.mickaelgeronimo.mastery.dml.insert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ==============================================================================
 * CAPÍTULO 33 (PARTE 3): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Stored Procedures para Checkout Atômico, Simulação de CallableStatement
 *       e o Padrão Moderno RETURNING / Data-Modifying CTE
 * ==============================================================================
 */
public class InsertHierarchicalRowsPart3Demo {

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

    // Emulador de Banco de Dados com Stored Procedure Interna
    public static class StoredProcedureDatabaseEngine {
        private final AtomicInteger orderSeq = new AtomicInteger(13); // Próximo: 14
        private final List<Order> orders = new ArrayList<>();
        private final List<OrderItem> orderItems = new ArrayList<>();
        private final Set<Integer> validProducts = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /**
         * Emulação da Stored Procedure 'sp_place_order':
         * Executada inteiramente no servidor de banco de dados.
         * Retorna o ID gerado pelo parâmetro OUT.
         */
        public int executeSpPlaceOrder(int customerId, int productId, int quantity, BigDecimal unitPrice) {
            // Validação interna com EXIT HANDLER
            if (!validProducts.contains(productId)) {
                // Simula o EXIT HANDLER FOR SQLEXCEPTION -> ROLLBACK; SET OUT = -1;
                return -1;
            }

            // Transação Atômica Interna
            int generatedId = orderSeq.incrementAndGet();
            Order newOrder = new Order(generatedId, customerId, LocalDate.now(), (byte) 1);
            OrderItem newItem = new OrderItem(generatedId, productId, quantity, unitPrice);

            orders.add(newOrder);
            orderItems.add(newItem);

            return generatedId; // Valor retornado no parâmetro OUT
        }

        public List<Order> getOrders() { return orders; }
        public List<OrderItem> getOrderItems() { return orderItems; }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 33 (PARTE 3) - STORED PROCEDURES & RETURNING EM JAVA 17   ");
        System.out.println("=========================================================================================\n");

        StoredProcedureDatabaseEngine engine = new StoredProcedureDatabaseEngine();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: CHECKOUT HIERÁRQUICO VIA STORED PROCEDURE (1 ÚNICA VIAGEM DE REDE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. EXECUTANDO A STORED PROCEDURE 'sp_place_order' NO JAVA ---");
        System.out.println("Código JDBC Equivalente:");
        System.out.println("String call = \"{CALL sp_place_order(?, ?, ?, ?, ?)}\";");
        System.out.println("try (CallableStatement cstmt = conn.prepareCall(call)) {");
        System.out.println("    cstmt.setInt(1, 4);                     // p_customer_id");
        System.out.println("    cstmt.setInt(2, 1);                     // p_product_id");
        System.out.println("    cstmt.setInt(3, 3);                     // p_quantity");
        System.out.println("    cstmt.setBigDecimal(4, new BigDecimal(\"2.50\")); // p_unit_price");
        System.out.println("    cstmt.registerOutParameter(5, Types.INTEGER); // p_new_order_id (OUT)");
        System.out.println("    cstmt.execute();");
        System.out.println("    int orderId = cstmt.getInt(5);");
        System.out.println("}\n");

        int generatedOrderId = engine.executeSpPlaceOrder(4, 1, 3, new BigDecimal("2.50"));

        System.out.println("-> Executando no Servidor: CALL sp_place_order(4, 1, 3, 2.50, @generated_order_id);");
        System.out.println("-> Valor retornado no Parâmetro OUT: " + generatedOrderId);
        System.out.println("-> MySQL Console: Query OK, 0 rows affected (0.01 sec)\n");

        System.out.println(">>> TABELA orders (PAI GERADO PELA PROCEDURE):");
        System.out.println("+----------+-------------+------------+--------+");
        System.out.println("| order_id | customer_id | order_date | status |");
        System.out.println("+----------+-------------+------------+--------+");
        for (Order o : engine.getOrders()) System.out.println(o);
        System.out.println("+----------+-------------+------------+--------+\n");

        System.out.println(">>> TABELA order_items (FILHO GERADO PELA PROCEDURE):");
        System.out.println("+----------+------------+----------+------------+");
        System.out.println("| order_id | product_id | quantity | unit_price |");
        System.out.println("+----------+------------+----------+------------+");
        for (OrderItem oi : engine.getOrderItems()) System.out.println(oi);
        System.out.println("+----------+------------+----------+------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: FALHA NA PROCEDURE COM EXIT HANDLER (SIMULAÇÃO COM PRODUTO 999)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. STORED PROCEDURE COM ERRO: EXIT HANDLER FOR SQLEXCEPTION ---");
        System.out.println("Tentando executar procedure com Produto 999 (Inexistente):");
        int erroOrderId = engine.executeSpPlaceOrder(4, 999, 1, new BigDecimal("10.00"));

        System.out.println("-> Parâmetro OUT retornado: " + erroOrderId);
        if (erroOrderId == -1) {
            System.out.println("-> A Procedure capturou a violação de FK internamente, executou ROLLBACK e retornou -1!");
            System.out.println("-> Aplicação Java notificada com sucesso sem deixar dados corrompidos no banco!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: O PADRÃO RETURNING DO POSTGRESQL 18 E DATA-MODIFYING CTES
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. O PADRÃO MODERNO RETURNING (POSTGRESQL 18) ---");
        System.out.println("No PostgreSQL, você pode inserir Pai e Filhos em UMA ÚNICA INSTRUÇÃO SQL usando CTEs:");
        System.out.println("WITH novo_pedido AS (");
        System.out.println("    INSERT INTO orders (customer_id, order_date, status)");
        System.out.println("    VALUES (4, CURRENT_DATE, 1)");
        System.out.println("    RETURNING order_id");
        System.out.println(")");
        System.out.println("INSERT INTO order_items (order_id, product_id, quantity, unit_price)");
        System.out.println("SELECT order_id, 1, 3, 2.50 FROM novo_pedido;");
        System.out.println("\nIsso elimina totalmente a necessidade de funções como LAST_INSERT_ID() e variáveis!");
        System.out.println("=========================================================================================\n");
    }
}
