package com.mickaelgeronimo.mastery.joins.using_clause;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 26 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Chaves Compostas com USING (col1, col2) e Resolução do Exercício Oficial
 *       do Mosh Hamedani no banco sql_invoicing (Junção Híbrida USING + ON)
 * ==============================================================================
 */
public class UsingClausePart2CompoundAndExerciseDemo {

    // --- MODELO 1: CHAVE COMPOSTA (sql_store: order_items + order_item_notes) ---
    record OrderItemId(int orderId, int productId) {}
    record OrderItem(int orderId, int productId, int quantity, double unitPrice) {}
    record OrderItemNote(int noteId, int orderId, int productId, String note) {}

    // --- MODELO 2: EXERCÍCIO OFICIAL (sql_invoicing: payments + clients + payment_methods) ---
    record Client(int clientId, String name) {}
    record PaymentMethod(int id, String name) {}
    record Payment(int paymentId, int clientId, int invoiceId, String date, double amount, int paymentMethod) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 26 (PARTE 2) - CHAVES COMPOSTAS E EXERCÍCIO OFICIAL       ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: CHAVES COMPOSTAS COM USING (order_id, product_id)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CHAVE COMPOSTA: USING (order_id, product_id) EM sql_store ---");
        System.out.println("Tabela order_items conectada com order_item_notes simultaneamente por 2 colunas:");
        System.out.println("+----------+------------+----------+------------+---------+--------------------------------------+");
        System.out.println("| order_id | product_id | quantity | unit_price | note_id | note                                 |");
        System.out.println("+----------+------------+----------+------------+---------+--------------------------------------+");

        List<OrderItem> items = Arrays.asList(
            new OrderItem(2, 1, 2, 9.10),
            new OrderItem(2, 4, 4, 1.66),
            new OrderItem(2, 6, 2, 2.94),
            new OrderItem(3, 3, 10, 9.50)
        );

        Map<OrderItemId, OrderItemNote> notes = Map.of(
            new OrderItemId(2, 1), new OrderItemNote(1, 2, 1, "Embalar para presente de aniversario"),
            new OrderItemId(2, 4), new OrderItemNote(2, 2, 4, "Verificar data de validade fresca")
        );

        int compoundMatches = 0;
        for (OrderItem oi : items) {
            OrderItemId key = new OrderItemId(oi.orderId(), oi.productId());
            if (notes.containsKey(key)) {
                OrderItemNote oin = notes.get(key);
                System.out.printf("| %8d | %10d | %8d | %10.2f | %7d | %-36s |\n",
                        oi.orderId(), oi.productId(), oi.quantity(), oi.unitPrice(), oin.noteId(), oin.note());
                compoundMatches++;
            }
        }
        System.out.println("+----------+------------+----------+------------+---------+--------------------------------------+");
        System.out.println(compoundMatches + " rows in set (0.00 sec) -> Casamento de chave composta concluído!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: EXERCÍCIO OFICIAL DO MOSH HAMEDANI (sql_invoicing)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. EXERCÍCIO OFICIAL: RELATÓRIO DE PAGAMENTOS EM sql_invoicing ---");
        System.out.println("Combinação Híbrida: USING (client_id) + ON p.payment_method = pm.payment_method_id");
        System.out.println("+------------+-------------+--------+----------------+");
        System.out.println("| date       | client      | amount | payment_method |");
        System.out.println("+------------+-------------+--------+----------------+");

        Map<Integer, Client> clients = Map.of(
            1, new Client(1, "Vinte"),
            2, new Client(2, "Myworks"),
            3, new Client(3, "Yadel"),
            4, new Client(4, "Kwim"),
            5, new Client(5, "Topiclounge")
        );

        Map<Integer, PaymentMethod> methods = Map.of(
            1, new PaymentMethod(1, "Credit Card"),
            2, new PaymentMethod(2, "Cash"),
            3, new PaymentMethod(3, "PayPal"),
            4, new PaymentMethod(4, "Wire Transfer")
        );

        List<Payment> payments = Arrays.asList(
            new Payment(1, 1, 1, "2019-01-03", 74.55, 1),
            new Payment(2, 1, 8, "2019-01-08", 32.77, 1),
            new Payment(3, 3, 10, "2019-01-08", 20.79, 1),
            new Payment(4, 5, 3, "2019-01-11", 0.03, 1),
            new Payment(5, 4, 5, "2019-01-15", 80.31, 1),
            new Payment(6, 1, 7, "2019-01-15", 40.53, 1),
            new Payment(7, 1, 4, "2019-01-26", 87.44, 1),
            new Payment(8, 1, 2, "2019-02-12", 8.18, 1)
        );

        for (Payment p : payments) {
            String clientName = clients.containsKey(p.clientId()) ? clients.get(p.clientId()).name() : "Desconhecido";
            String methodName = methods.containsKey(p.paymentMethod()) ? methods.get(p.paymentMethod()).name() : "Desconhecido";

            System.out.printf("| %-10s | %-11s | %6.2f | %-14s |\n",
                    p.date(), clientName, p.amount(), methodName);
        }
        System.out.println("+------------+-------------+--------+----------------+");
        System.out.println("8 rows in set (0.00 sec) -> Exercício Oficial do Mosh Resolvido com Sucesso!\n");
    }
}
