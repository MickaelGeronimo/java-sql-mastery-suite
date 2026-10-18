package com.mickaelgeronimo.mastery.joins.cross;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 27 (PARTE 2 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Grand Finale de Junções Naturais — Os 2 Grandes Desastres do NATURAL JOIN
 *       (Produto Cartesiano Acidental e Colisão Silenciosa de Preços Históricos)
 * ==============================================================================
 */
public class NaturalJoinFinalMasterDemo {

    // --- MODELOS DE DADOS DO BANCO sql_store ---
    record Order(int orderId, int customerId, int status) {}
    record OrderStatus(int orderStatusId, String name) {}

    record OrderItem(int orderId, int productId, int quantity, double unitPrice) {}
    record Product(int productId, String name, double currentCatalogPrice) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 27 (PARTE 2 - FINAL) - OS DESASTRES DO NATURAL JOIN       ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // DESASTRE 1: ZERO COLUNAS EM COMUM = PRODUTO CARTESIANO ACIDENTAL (30 LINHAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. DESASTRE 1: ZERO COLUNAS EM COMUM = EXPLOSÃO CARTESIANA (orders x statuses) ---");
        System.out.println("Tabela orders não tem nenhuma coluna chamada 'orderStatusId' ou 'name'.");
        System.out.println("O NATURAL JOIN não dá erro: gera 10 pedidos x 3 status = 30 LINHAS!");
        System.out.println("+----------+-----------+");
        System.out.println("| order_id | status    |");
        System.out.println("+----------+-----------+");

        List<Order> orders = Arrays.asList(
            new Order(1, 6, 1),
            new Order(2, 7, 1),
            new Order(3, 8, 1)
        );

        List<OrderStatus> statuses = Arrays.asList(
            new OrderStatus(1, "Processed"),
            new OrderStatus(2, "Shipped"),
            new OrderStatus(3, "Delivered")
        );

        int cartesianCount = 0;
        for (Order o : orders) {
            for (OrderStatus s : statuses) {
                System.out.printf("| %8d | %-9s |\n", o.orderId(), s.name());
                cartesianCount++;
            }
        }
        System.out.println("+----------+-----------+");
        System.out.println(cartesianCount + " rows in set (Apenas para 3 pedidos gerou 9 linhas! Para 10 geraria 30 linhas!).\n");

        // --------------------------------------------------------------------------------------
        // DESASTRE 2: DUAS COLUNAS EM COMUM = O BUG DA ALTERAÇÃO DE PREÇOS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. DESASTRE 2: COLISÃO DE NOMES (order_items vs products: product_id E unit_price) ---");
        System.out.println("Cenário: O cliente comprou o produto 1 em 2019 pelo preço histórico de $9.10.");
        System.out.println("Em 2026, a inflação aumentou o preço de catálogo do produto 1 para $9.99!");

        OrderItem itemVendido = new OrderItem(2, 1, 2, 9.10); // Preço na época da venda: 9.10
        Product produtoCatalogo = new Product(1, "Foam Dinner Plate", 9.99); // Preço atualizado hoje: 9.99

        System.out.println("\n[Tentativa com NATURAL JOIN]:");
        System.out.println("O banco exige: item.product_id == prod.product_id AND item.unit_price == prod.unit_price");
        boolean naturalMatch = (itemVendido.productId() == produtoCatalogo.productId())
                && (itemVendido.unitPrice() == produtoCatalogo.currentCatalogPrice());

        System.out.println("Resultado da condição: " + naturalMatch + " -> O ITEM DESAPARECEU DO RELATÓRIO DE VENDAS!");
        System.out.println("Empty set (0.00 sec) 💥 BUG SILENCIOSO CATASTRÓFICO!\n");

        System.out.println("[Tentativa Profissional com USING (product_id)]:");
        boolean explicitMatch = (itemVendido.productId() == produtoCatalogo.productId());
        System.out.println("Resultado com USING (product_id): " + explicitMatch + " -> O item é encontrado com sucesso!");
        System.out.printf("+----------+-------------------+----------+------------------+------------------+\n");
        System.out.printf("| order_id | product_name      | quantity | historical_price | current_cat_price|\n");
        System.out.printf("+----------+-------------------+----------+------------------+------------------+\n");
        System.out.printf("| %8d | %-17s | %8d | %16.2f | %16.2f |\n",
                itemVendido.orderId(), produtoCatalogo.name(), itemVendido.quantity(), itemVendido.unitPrice(), produtoCatalogo.currentCatalogPrice());
        System.out.printf("+----------+-------------------+----------+------------------+------------------+\n");
        System.out.println("1 row in set (0.00 sec) -> Integridade Histórica e Contábil 100% Preservada!\n");

        System.out.println("Grand Finale do Capítulo 27 (Natural Joins) Concluído com Sucesso Absoluto!");
    }
}
