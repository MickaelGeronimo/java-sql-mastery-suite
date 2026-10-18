package com.mickaelgeronimo.mastery.joins.inner;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiJoinFanOutTrapDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. O Perigo da Armadilha do Fan-Out (Multiplicação Oculta) em Multi-Table Joins.
 *    Quando juntamos uma tabela mãe com DUAS tabelas filhas (1:N), as linhas se multiplicam!
 * 2. O Erro do SUM() inflacionado gerado pelo Fan-Out.
 * 3. Projeção moderna no Java usando Records (Java 16+).
 */
public class MultiJoinFanOutTrapDemo {

    // 1. Record moderno do Java para DTO executivo
    record OrderAuditDTO(
            int orderId,
            String customerName,
            String productName,
            int quantity,
            double unitPrice,
            double lineTotal,
            String status
    ) {
        @Override
        public String toString() {
            return String.format("| %8d | %-18s | %-22s | %8d | R$ %6.2f | R$ %7.2f | %-10s |",
                    orderId, customerName, productName, quantity, unitPrice, lineTotal, status);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: ARMADILHA DO FAN-OUT E PROJEÇÃO COM RECORDS (JAVA 16+)");
        System.out.println("=========================================================================================\n");

        System.out.println("--- 1. PROJEÇÃO DE RELATÓRIO EXECUTIVO (5 TABELAS REUNIDAS) COM RECORDS ---");
        System.out.println("+----------+--------------------+------------------------+----------+-----------+-----------+------------+");
        System.out.println("| order_id | customer_name      | product_name           | quantity | unit_pric | linetotal | status     |");
        System.out.println("+----------+--------------------+------------------------+----------+-----------+-----------+------------+");

        List<OrderAuditDTO> reports = new ArrayList<>();
        reports.add(new OrderAuditDTO(2, "Mickael Geronimo", "Foam Dinner Plate", 2, 9.10, 18.20, "Processed"));
        reports.add(new OrderAuditDTO(2, "Mickael Geronimo", "Brocolli - Fresh", 4, 1.66, 6.64, "Processed"));
        reports.add(new OrderAuditDTO(2, "Mickael Geronimo", "Scallops - Large", 2, 2.94, 5.88, "Processed"));
        reports.add(new OrderAuditDTO(1, "Ambur Roseburgh", "Brocolli - Fresh", 4, 3.74, 14.96, "Processed"));

        reports.forEach(System.out::println);
        System.out.println("+----------+--------------------+------------------------+----------+-----------+-----------+------------+\n");

        System.out.println("--- 2. A ARMADILHA DO FAN-OUT (MULTIPLICAÇÃO DE LINHAS 1:N x 1:N) ---");
        System.out.println("Imagine o Pedido 100:");
        System.out.println("  • Possui 3 Itens comprados (Item A, Item B, Item C)");
        System.out.println("  • Foi pago em 2 Parcelas (Parcela 1, Parcela 2)");
        System.out.println("\nSe você fizer: FROM orders JOIN items JOIN payments:");
        System.out.println("O SQL gera: 3 itens x 2 pagamentos = 6 LINHAS NO RESULTADO!");
        System.out.println("💥 EFEITO CATASTRÓFICO: O SUM() de pagamentos será DUPLICADO!");
        System.out.println("Se cada parcela era R$ 50 (Total R$ 100), o SUM() somará R$ 50 x 3 = R$ 150!");
        System.out.println("Lição de Arquitetura: NUNCA faça JOIN de duas tabelas filhas 1:N independentes na mesma consulta!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! FAN-OUT COMPREENDIDO E RECORDS JAVA DOMINADOS!");
        System.out.println("=========================================================================================");
    }
}
