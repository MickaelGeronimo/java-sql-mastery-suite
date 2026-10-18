package com.mickaelgeronimo.mastery.joins.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CompoundJoinConditionsDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Chave Composta (Composite Primary Key) em Java: quando uma entidade precisa de 2 atributos para ser única.
 * 2. Condição de Junção Composta (Compound Join Condition) usando o operador AND.
 * 3. Demonstração dos dois erros graves ao tentar ligar por apenas 1 coluna:
 *    - Ligar só por order_id (Mistura produtos do mesmo pedido).
 *    - Ligar só por product_id (Mistura pedidos de clientes diferentes).
 */
public class CompoundJoinConditionsDemo {

    // 1. Classe de Chave Composta (como @Embeddable no JPA)
    static class OrderItemId {
        int orderId;
        int productId;

        OrderItemId(int orderId, int productId) {
            this.orderId = orderId;
            this.productId = productId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItemId that = (OrderItemId) o;
            return orderId == that.orderId && productId == that.productId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderId, productId);
        }

        @Override
        public String toString() {
            return "[Pedido #" + orderId + ", Produto #" + productId + "]";
        }
    }

    // 2. Tabela order_items
    static class OrderItem {
        OrderItemId id;
        int quantity;
        double unitPrice;

        OrderItem(int orderId, int productId, int quantity, double unitPrice) {
            this.id = new OrderItemId(orderId, productId);
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
    }

    // 3. Tabela order_item_notes (Anotações personalizadas por item de pedido)
    static class OrderItemNote {
        int noteId;
        int orderId;
        int productId;
        String note;

        OrderItemNote(int noteId, int orderId, int productId, String note) {
            this.noteId = noteId;
            this.orderId = orderId;
            this.productId = productId;
            this.note = note;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CONDIÇÕES DE JUNÇÃO COMPOSTAS (COMPOUND JOIN CONDITIONS)");
        System.out.println("=========================================================================================\n");

        // Populando dados da tabela order_items
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(2, 1, 2, 9.10)); // Pedido 2, Produto 1
        items.add(new OrderItem(2, 4, 4, 1.66)); // Pedido 2, Produto 4
        items.add(new OrderItem(2, 6, 2, 2.94)); // Pedido 2, Produto 6
        items.add(new OrderItem(10, 1, 1, 9.10)); // Pedido 10, Produto 1 (Outro pedido com o mesmo produto!)

        // Populando dados da tabela order_item_notes
        List<OrderItemNote> notes = new ArrayList<>();
        notes.add(new OrderItemNote(1, 2, 1, "Embalar para presente de aniversário"));
        notes.add(new OrderItemNote(2, 2, 4, "Verificar data de validade fresca"));

        System.out.println("--- 1. JUNÇÃO COMPOSTA CORRETA (ON oi.order_id = oin.order_id AND oi.product_id = oin.product_id) ---");
        System.out.println("+----------+------------+----------+------------+---------+-----------------------------------------+");
        System.out.println("| order_id | product_id | quantity | unit_price | note_id | note                                    |");
        System.out.println("+----------+------------+----------+------------+---------+-----------------------------------------+");

        for (OrderItem oi : items) {
            for (OrderItemNote oin : notes) {
                // REGRA FUNDAMENTAL: AMBAS AS CONDIÇÕES DEVEM SER VERDADEIRAS (AND)!
                if (oi.id.orderId == oin.orderId && oi.id.productId == oin.productId) {
                    System.out.printf("| %8d | %10d | %8d | R$ %6.2f | %7d | %-39s |\n",
                            oi.id.orderId, oi.id.productId, oi.quantity, oi.unitPrice, oin.noteId, oin.note);
                }
            }
        }
        System.out.println("+----------+------------+----------+------------+---------+-----------------------------------------+\n");

        System.out.println("--- 2. POR QUE LIGAR APENAS POR order_id É UM ERRO GRAVE? ---");
        System.out.println("Se ligássemos apenas por 'oi.order_id = oin.order_id':");
        int countErro1 = 0;
        for (OrderItem oi : items) {
            for (OrderItemNote oin : notes) {
                if (oi.id.orderId == oin.orderId) {
                    countErro1++;
                }
            }
        }
        System.out.println("💥 Geraria " + countErro1 + " linhas! A nota do Produto 1 seria atribuída falsamente aos Produtos 4 e 6!\n");

        System.out.println("--- 3. POR QUE LIGAR APENAS POR product_id É UM ERRO GRAVE? ---");
        System.out.println("Se ligássemos apenas por 'oi.product_id = oin.product_id':");
        int countErro2 = 0;
        for (OrderItem oi : items) {
            for (OrderItemNote oin : notes) {
                if (oi.id.productId == oin.productId) {
                    countErro2++;
                }
            }
        }
        System.out.println("💥 Geraria " + countErro2 + " linhas! A nota do Pedido 2 vazaria para o Pedido 10 de outro cliente!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! A CONDIÇÃO COMPOSTA (AND) É O ÚNICO CAMINHO SEGURO!");
        System.out.println("=========================================================================================");
    }
}
