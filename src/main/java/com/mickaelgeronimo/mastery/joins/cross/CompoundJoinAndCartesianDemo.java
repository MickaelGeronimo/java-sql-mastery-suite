package com.mickaelgeronimo.mastery.joins.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CompoundJoinAndCartesianDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Chave Composta (Composite Key) em Java: quando um objeto precisa de 2 campos para ser único.
 * 2. Junção Composta (Compound Join) usando AND.
 * 3. O Perigo do Produto Cartesiano (Cross Join acidental) ao esquecer a condição de ligação.
 */
public class CompoundJoinAndCartesianDemo {

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
            return "[Pedido=" + orderId + ", Produto=" + productId + "]";
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

    // 3. Tabela order_item_notes (Anotações por item)
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
        System.out.println("==========================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO COMPOSTA (AND) E O PRODUTO CARTESIANO");
        System.out.println("==========================================================================\n");

        // Criando itens de pedidos
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(2, 1, 1, 9.10)); // Pedido 2, Produto 1
        items.add(new OrderItem(2, 4, 4, 1.66)); // Pedido 2, Produto 4
        items.add(new OrderItem(2, 6, 2, 2.94)); // Pedido 2, Produto 6

        // Criando anotações para itens específicos
        List<OrderItemNote> notes = new ArrayList<>();
        notes.add(new OrderItemNote(1, 2, 1, "Embalar para presente"));
        notes.add(new OrderItemNote(2, 2, 4, "Verificar data de validade"));

        System.out.println("--- 1. JUNÇÃO COMPOSTA CORRETA (ON order_id AND product_id) ---");
        System.out.println("+----------+------------+----------+--------------------------------+");
        System.out.println("| order_id | product_id | quantity | note                           |");
        System.out.println("+----------+------------+----------+--------------------------------+");

        for (OrderItem item : items) {
            for (OrderItemNote note : notes) {
                // PRECISA DAS DUAS CONDIÇÕES (AND)!
                if (item.id.orderId == note.orderId && item.id.productId == note.productId) {
                    System.out.printf("| %8d | %10d | %8d | %-30s |\n",
                            item.id.orderId, item.id.productId, item.quantity, note.note);
                }
            }
        }
        System.out.println("+----------+------------+----------+--------------------------------+\n");

        System.out.println("--- 2. O PERIGO DO PRODUTO CARTESIANO (ESQUECER O WHERE / ON) ---");
        System.out.println("Se esquecermos a ligação, cada item se multiplica por TODAS as anotações:");
        int totalCombinacoes = 0;
        for (OrderItem item : items) {
            for (OrderItemNote note : notes) {
                // SEM NENHUM IF (Esqueceu a condição!)
                totalCombinacoes++;
            }
        }
        System.out.println("Total de itens: " + items.size() + " | Total de anotações: " + notes.size());
        System.out.println("Resultado da multiplicação cega (Cartesiano): " + totalCombinacoes + " linhas geradas!");
        System.out.println("Em produção com 100.000 registros, isso geraria 10 BILHÕES de linhas e travaria o servidor!\n");

        System.out.println("==========================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! A SINTAXE EXPLÍCITA (JOIN...ON) É MAIS SEGURA!");
        System.out.println("==========================================================================");
    }
}
