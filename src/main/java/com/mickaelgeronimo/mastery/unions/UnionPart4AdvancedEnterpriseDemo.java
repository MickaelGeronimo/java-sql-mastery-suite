package com.mickaelgeronimo.mastery.unions;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ==============================================================================
 * CAPÍTULO 29 (PARTE 4): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Emulação de FULL OUTER JOIN via UNION, Paginação (.skip & .limit)
 *       e a Tríade da Teoria dos Conjuntos: UNION, INTERSECT e EXCEPT
 * ==============================================================================
 */
public class UnionPart4AdvancedEnterpriseDemo {

    public record Customer(int id, String firstName) {}
    public record Order(int orderId, int customerId) {}
    public record FullOuterJoinRow(Integer customerId, String firstName, Integer orderId) {
        @Override
        public String toString() {
            String cid = customerId != null ? String.valueOf(customerId) : "NULL";
            String name = firstName != null ? firstName : "NULL";
            String oid = orderId != null ? String.valueOf(orderId) : "NULL";
            return String.format("| %11s | %-12s | %8s |", cid, name, oid);
        }
    }

    public record Product(int id, String name) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 29 (PARTE 4) - FULL OUTER JOIN, PAGINAÇÃO E TEORIA DOS CONJUNTOS");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: EMULAÇÃO DE FULL OUTER JOIN VIA UNION (LEFT JOIN + RIGHT JOIN)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. EMULAÇÃO DE FULL OUTER JOIN VIA UNION (Deduplicação de Matches) ---");
        System.out.println("+-------------+--------------+----------+");
        System.out.println("| customer_id | first_name   | order_id |");
        System.out.println("+-------------+--------------+----------+");

        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara"),
            new Customer(2, "Ines"),      // Sem pedidos
            new Customer(5, "Clemmie"),
            new Customer(99, "Zoro")      // Cliente sem pedido
        );

        List<Order> orders = Arrays.asList(
            new Order(101, 1),
            new Order(102, 5),
            new Order(999, 888)           // Pedido órfão (sem cliente correspondente)
        );

        // 1. Simulação do LEFT JOIN
        List<FullOuterJoinRow> leftJoin = new ArrayList<>();
        for (Customer c : customers) {
            boolean matched = false;
            for (Order o : orders) {
                if (o.customerId() == c.id()) {
                    leftJoin.add(new FullOuterJoinRow(c.id(), c.firstName(), o.orderId()));
                    matched = true;
                }
            }
            if (!matched) {
                leftJoin.add(new FullOuterJoinRow(c.id(), c.firstName(), null));
            }
        }

        // 2. Simulação do RIGHT JOIN
        List<FullOuterJoinRow> rightJoin = new ArrayList<>();
        for (Order o : orders) {
            boolean matched = false;
            for (Customer c : customers) {
                if (c.id() == o.customerId()) {
                    rightJoin.add(new FullOuterJoinRow(c.id(), c.firstName(), o.orderId()));
                    matched = true;
                }
            }
            if (!matched) {
                rightJoin.add(new FullOuterJoinRow(null, null, o.orderId()));
            }
        }

        // 3. UNION (Concatena e aplica DISTINCT para não duplicar os matches comuns):
        List<FullOuterJoinRow> fullOuterJoin = Stream.concat(leftJoin.stream(), rightJoin.stream())
                .distinct()
                .sorted(Comparator.comparing(
                        r -> r.customerId() != null ? r.customerId() : Integer.MAX_VALUE
                ))
                .collect(Collectors.toList());

        for (FullOuterJoinRow row : fullOuterJoin) {
            System.out.println(row);
        }
        System.out.println("+-------------+--------------+----------+");
        System.out.println(fullOuterJoin.size() + " rows in set (FULL OUTER JOIN perfeito!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: PAGINAÇÃO DE RESULTADO UNIFICADO (.skip e .limit)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. PAGINAÇÃO ENTERPRISE DE CONSULTA UNIFICADA (Página 2: LIMIT 3 OFFSET 3) ---");
        List<String> fullUnionNames = Arrays.asList(
            "Ambur (Bronze)", "Babara (Silver)", "Clemmie (Gold)",
            "Elka (Gold)", "Freddi (Bronze)", "Freddi (Silver)",
            "Ilene (Bronze)", "Ines (Bronze)", "Levy (Bronze)", "Ruth (Bronze)"
        );

        int pageSize = 3;
        int pageNumber = 2; // Página 2 (pula 3, pega 3)
        List<String> page2 = fullUnionNames.stream()
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        System.out.println("Registros da Página 2:");
        page2.forEach(item -> System.out.println("  -> " + item));
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A TRÍADE RELACIONAL (UNION, INTERSECT, EXCEPT)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A TRÍADE DA ÁLGEBRA RELACIONAL (MySQL 8.0.31+ / Java Stream) ---");
        Set<Integer> catalogProducts = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Set<Integer> orderedProducts = Set.of(1, 2, 3, 4, 6, 7); // Produtos 5, 8, 9, 10 nunca foram pedidos!

        // A) INTERSECT (Produtos do catálogo QUE FORAM VENDIDOS)
        List<Integer> intersect = catalogProducts.stream()
                .filter(orderedProducts::contains)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("INTERSECT (Produtos com vendas ativas): " + intersect);

        // B) EXCEPT (Produtos em estoque QUE NUNCA FORAM VENDIDOS - Estoque Encalhado)
        List<Integer> except = catalogProducts.stream()
                .filter(Predicate.not(orderedProducts::contains))
                .sorted()
                .collect(Collectors.toList());
        System.out.println("EXCEPT    (Estoque Encalhado / Sem Vendas): " + except);
        System.out.println("\nDemostração concluída com 100% de sucesso!");
    }
}
