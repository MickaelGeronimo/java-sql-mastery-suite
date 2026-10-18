package com.mickaelgeronimo.mastery.unions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ==============================================================================
 * CAPÍTULO 29 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Uniões (Unions) — Empilhamento Vertical de Linhas, As Regras de Ouro
 *       e Concatenação de Streams com Eliminação de Duplicidades
 * ==============================================================================
 */
public class UnionPart1Demo {

    record OrderReport(int orderId, String orderDate, String status) {}
    record RawOrder(int orderId, String orderDate) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 29 (PARTE 1) - UNIÕES VERTICAIS (UNIONS)                  ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS PEDIDOS (sql_store.orders)
        List<RawOrder> allOrders = Arrays.asList(
            new RawOrder(1, "2019-01-30"),  // >= 2019-01-01 (Active)
            new RawOrder(2, "2018-08-02"),  // < 2019-01-01  (Archived)
            new RawOrder(3, "2017-12-01"),
            new RawOrder(4, "2017-01-22"),
            new RawOrder(5, "2017-08-25"),
            new RawOrder(6, "2017-11-18"),
            new RawOrder(7, "2018-09-22"),
            new RawOrder(8, "2018-06-08"),
            new RawOrder(9, "2017-07-05"),
            new RawOrder(10, "2018-04-22")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: CONSULTA DO MOSH — PEDIDOS ATIVOS vs ARQUIVADOS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CONSULTA OFICIAL DO AUTOR: PEDIDOS ATIVOS vs ARQUIVADOS ---");
        System.out.println("+----------+------------+----------+");
        System.out.println("| order_id | order_date | status   |");
        System.out.println("+----------+------------+----------+");

        // Consulta 1: Ativos (2019 em diante)
        List<OrderReport> activeOrders = allOrders.stream()
                .filter(o -> o.orderDate().compareTo("2019-01-01") >= 0)
                .map(o -> new OrderReport(o.orderId(), o.orderDate(), "Active"))
                .collect(Collectors.toList());

        // Consulta 2: Arquivados (Antes de 2019)
        List<OrderReport> archivedOrders = allOrders.stream()
                .filter(o -> o.orderDate().compareTo("2019-01-01") < 0)
                .map(o -> new OrderReport(o.orderId(), o.orderDate(), "Archived"))
                .collect(Collectors.toList());

        // O UNION empilha os dois resultados verticalmente:
        List<OrderReport> unionOrders = Stream.concat(activeOrders.stream(), archivedOrders.stream())
                .collect(Collectors.toList());

        for (OrderReport o : unionOrders) {
            System.out.printf("| %8d | %-10s | %-8s |\n", o.orderId(), o.orderDate(), o.status());
        }
        System.out.println("+----------+------------+----------+");
        System.out.println(unionOrders.size() + " rows in set (0.00 sec) -> 1 pedido Ativo + 9 pedidos Arquivados!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: UNIÃO DE TABELAS DIFERENTES (customers + shippers)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. UNIÃO DE TABELAS DIFERENTES COM REMOÇÃO DE DUPLICATAS (UNION) ---");
        System.out.println("+--------------------------------+");
        System.out.println("| contact_name                   |");
        System.out.println("+--------------------------------+");

        List<String> customerNames = Arrays.asList(
            "Babara", "Ines", "Freddi", "Ambur", "Clemmie", "Elka", "Ilene", "Freddi", "Ruth", "Levy"
        );

        List<String> shipperNames = Arrays.asList(
            "Hettinger LLC", "Schultz, Tremblay and Hilpert", "Sallie Goodrich", "Batz-Bauch", "Kemp, Breitenberg and Goyette"
        );

        // O UNION elimina nomes duplicados (como 'Freddi' que aparece 2 vezes nos clientes):
        List<String> distinctNames = Stream.concat(customerNames.stream(), shipperNames.stream())
                .distinct()
                .collect(Collectors.toList());

        for (String name : distinctNames) {
            System.out.printf("| %-30s |\n", name);
        }
        System.out.println("+--------------------------------+");
        System.out.println(distinctNames.size() + " rows in set (0.00 sec) -> 9 clientes únicos + 5 transportadoras = 14 nomes!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: AS DUAS REGRAS DE OURO DO UNION
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. AS DUAS REGRAS DE OURO DO UNION ---");
        System.out.println("Regra 1: Quantidade idêntica de colunas!");
        System.out.println("   SELECT col1, col2 UNION SELECT col1 -> ERROR 1222: Different number of columns.");
        System.out.println("Regra 2: O primeiro SELECT manda no cabeçalho!");
        System.out.println("   SELECT first_name AS contact_name ... UNION SELECT name -> O nome final é 'contact_name'!\n");
    }
}
