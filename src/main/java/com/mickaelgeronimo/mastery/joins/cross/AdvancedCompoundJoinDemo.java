package com.mickaelgeronimo.mastery.joins.cross;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * AdvancedCompoundJoinDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Compound LEFT JOIN: preservando itens que NÃO possuem anotações personalizadas.
 * 2. Junção Composta Híbrida (Igualdade + Vigência Temporal):
 *    Associando o item ao preço histórico vigente na data exata da compra.
 */
public class AdvancedCompoundJoinDemo {

    // 1. Tabela order_items
    static class OrderItem {
        int orderId;
        int productId;
        LocalDate orderDate;
        int quantity;

        OrderItem(int orderId, int productId, LocalDate orderDate, int quantity) {
            this.orderId = orderId;
            this.productId = productId;
            this.orderDate = orderDate;
            this.quantity = quantity;
        }
    }

    // 2. Tabela order_item_notes
    static class OrderItemNote {
        int orderId;
        int productId;
        String note;

        OrderItemNote(int orderId, int productId, String note) {
            this.orderId = orderId;
            this.productId = productId;
            this.note = note;
        }
    }

    // 3. Tabela de Preços Históricos (Vigência por Período)
    static class HistoricalPrice {
        int productId;
        LocalDate startDate;
        LocalDate endDate;
        double price;

        HistoricalPrice(int productId, LocalDate startDate, LocalDate endDate, double price) {
            this.productId = productId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: COMPOUND JOIN AVANÇADO (LEFT JOIN E PREÇOS HISTÓRICOS)");
        System.out.println("=========================================================================================\n");

        // Populando dados de itens de pedidos
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(2, 1, LocalDate.of(2018, 8, 2), 2));
        items.add(new OrderItem(2, 4, LocalDate.of(2018, 8, 2), 4));
        items.add(new OrderItem(2, 6, LocalDate.of(2018, 8, 2), 2)); // NÃO TEM ANOTAÇÃO!

        // Populando dados de anotações (apenas produtos 1 e 4 do pedido 2 têm anotação)
        List<OrderItemNote> notes = new ArrayList<>();
        notes.add(new OrderItemNote(2, 1, "Embalar para presente"));
        notes.add(new OrderItemNote(2, 4, "Verificar validade"));

        System.out.println("--- 1. COMPOUND LEFT JOIN (PRESERVANDO ITENS SEM ANOTAÇÃO) ---");
        System.out.println("+----------+------------+----------+--------------------------------------+");
        System.out.println("| order_id | product_id | quantity | note                                 |");
        System.out.println("+----------+------------+----------+--------------------------------------+");

        for (OrderItem oi : items) {
            String noteFound = "--- Sem anotação ---";
            for (OrderItemNote oin : notes) {
                if (oi.orderId == oin.orderId && oi.productId == oin.productId) {
                    noteFound = oin.note;
                    break;
                }
            }
            System.out.printf("| %8d | %10d | %8d | %-36s |\n",
                    oi.orderId, oi.productId, oi.quantity, noteFound);
        }
        System.out.println("+----------+------------+----------+--------------------------------------+\n");

        System.out.println("--- 2. JUNÇÃO COMPOSTA HÍBRIDA (IGUALDADE + VIGÊNCIA TEMPORAL) ---");
        List<HistoricalPrice> priceHistory = new ArrayList<>();
        // Produto 1 em 2017 custava R$ 7.50, mas a partir de 2018 passou a R$ 9.10:
        priceHistory.add(new HistoricalPrice(1, LocalDate.of(2017, 1, 1), LocalDate.of(2017, 12, 31), 7.50));
        priceHistory.add(new HistoricalPrice(1, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31), 9.10));

        System.out.println("Buscando preço do Produto 1 para compra realizada em 2018-08-02:");
        for (HistoricalPrice hp : priceHistory) {
            LocalDate compra = LocalDate.of(2018, 8, 2);
            // CONDIÇÃO COMPOSTA: produto bate E data da compra está entre startDate e endDate!
            if (hp.productId == 1 && (!compra.isBefore(hp.startDate) && !compra.isAfter(hp.endDate))) {
                System.out.printf("✨ Preço histórico aplicado com sucesso: R$ %.2f (Período: %s até %s)\n",
                        hp.price, hp.startDate, hp.endDate);
            }
        }

        System.out.println("\n=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! COMPOUND LEFT JOIN E REGRAS TEMPORAIS DOMINADOS!");
        System.out.println("=========================================================================================");
    }
}
