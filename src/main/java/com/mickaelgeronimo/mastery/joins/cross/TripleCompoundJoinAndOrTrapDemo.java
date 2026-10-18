package com.mickaelgeronimo.mastery.joins.cross;

import java.util.ArrayList;
import java.util.List;

/**
 * TripleCompoundJoinAndOrTrapDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Chaves Compostas de 3 Níveis (TenantId + OrderId + ProductId) típicas de ERPs e SaaS Multi-Tenant.
 * 2. Condição de Junção Tripla com AND.
 * 3. A Catástrofe da Armadilha do OR no JOIN: Como trocar AND por OR corrompe os dados e explode o resultado.
 */
public class TripleCompoundJoinAndOrTrapDemo {

    // 1. Chave Composta Tripla (Multi-Tenant SaaS)
    static class TenantOrderItem {
        String tenantId; // Identificador da Empresa Cliente no SaaS (ex: "EMPRESA_A", "EMPRESA_B")
        int orderId;
        int productId;
        String productName;
        int quantity;

        TenantOrderItem(String tenantId, int orderId, int productId, String productName, int quantity) {
            this.tenantId = tenantId;
            this.orderId = orderId;
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
        }
    }

    // 2. Anotação de Logística do Item
    static class ItemLogisticsNote {
        String tenantId;
        int orderId;
        int productId;
        String note;

        ItemLogisticsNote(String tenantId, int orderId, int productId, String note) {
            this.tenantId = tenantId;
            this.orderId = orderId;
            this.productId = productId;
            this.note = note;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO COMPOSTA TRIPLA E A CATÁSTROFE DA ARMADILHA DO 'OR'");
        System.out.println("=========================================================================================\n");

        // Populando dados simulando um SaaS Multi-Tenant
        List<TenantOrderItem> items = new ArrayList<>();
        // Empresa A: Pedido 10, Produto 1
        items.add(new TenantOrderItem("EMPRESA_A", 10, 1, "Teclado Mecânico", 2));
        // Empresa A: Pedido 10, Produto 2
        items.add(new TenantOrderItem("EMPRESA_A", 10, 2, "Mouse Sem Fio", 1));
        // Empresa B: Pedido 10, Produto 1 (MESMO NÚMERO DE PEDIDO E PRODUTO, MAS DE OUTRA EMPRESA!)
        items.add(new TenantOrderItem("EMPRESA_B", 10, 1, "Cabo HDMI 2.1", 5));

        // Anotação pertencente exclusivamente à Empresa A, Pedido 10, Produto 1:
        List<ItemLogisticsNote> notes = new ArrayList<>();
        notes.add(new ItemLogisticsNote("EMPRESA_A", 10, 1, "Seguro de carga frágil solicitado"));

        System.out.println("--- 1. JUNÇÃO TRIPLA CORRETA COM 'AND' (Tenant + Order + Product) ---");
        System.out.println("+------------+----------+------------+--------------------+----------+--------------------------------------+");
        System.out.println("| tenant_id  | order_id | product_id | product_name       | quantity | logistics_note                       |");
        System.out.println("+------------+----------+------------+--------------------+----------+--------------------------------------+");

        int corretos = 0;
        for (TenantOrderItem item : items) {
            for (ItemLogisticsNote note : notes) {
                // REGRA DE OURO TRIPLA: tenant E order E product devem ser iguais!
                if (item.tenantId.equals(note.tenantId) &&
                    item.orderId == note.orderId &&
                    item.productId == note.productId) {

                    System.out.printf("| %-10s | %8d | %10d | %-18s | %8d | %-36s |\n",
                            item.tenantId, item.orderId, item.productId, item.productName, item.quantity, note.note);
                    corretos++;
                }
            }
        }
        System.out.println("+------------+----------+------------+--------------------+----------+--------------------------------------+");
        System.out.println("Linhas combinadas com sucesso: " + corretos + " (Apenas o item correto da Empresa A foi associado!)\n");

        System.out.println("--- 2. A CATÁSTROFE DO 'OR' (O QUE ACONTECE SE USARMOS 'OR' NO LUGAR DE 'AND'?) ---");
        int vazamentos = 0;
        for (TenantOrderItem item : items) {
            for (ItemLogisticsNote note : notes) {
                // ERRO CATASTRÓFICO: Trocou AND por OR!
                if (item.tenantId.equals(note.tenantId) ||
                    item.orderId == note.orderId ||
                    item.productId == note.productId) {

                    vazamentos++;
                    System.out.println("🚨 VAZAMENTO DE DADOS: A nota confidencial foi atribuída a: " +
                            item.tenantId + " -> Pedido " + item.orderId + " (" + item.productName + ")");
                }
            }
        }
        System.out.println("\n💥 Total de linhas com OR: " + vazamentos + " (De 1 linha correta pulou para 3 linhas com vazamento!)");
        System.out.println("A nota confidencial da Empresa A vazou para os produtos da Empresa B concorrente!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! CHAVES TRIPLAS EXIGEM 'AND' ESTRITO PARA SEGURANÇA TOTAL!");
        System.out.println("=========================================================================================");
    }
}
