package com.mickaelgeronimo.mastery.joins.cross;

import java.lang.reflect.Field;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 27 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Junções Naturais (Natural Joins) — Como o Banco "Adivinha" as Colunas,
 *       Simulação de Introspecção via Reflection e o Alerta do Autor
 * ==============================================================================
 */
public class NaturalJoinPart1Demo {

    // Registros representando as tabelas do banco sql_store
    record Customer(int customerId, String firstName, String lastName) {}
    record Order(int orderId, int customerId, String orderDate) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 27 (PARTE 1) - JUNÇÕES NATURAIS (NATURAL JOINS)          ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS CLIENTES (sql_store.customers)
        Map<Integer, Customer> customers = Map.of(
            1, new Customer(1, "Babara", "MacCaffrey"),
            2, new Customer(2, "Ines", "Brushfield"),
            3, new Customer(3, "Freddi", "Boagey"),
            5, new Customer(5, "Clemmie", "Bramer"),
            6, new Customer(6, "Elka", "Twiddell"),
            7, new Customer(7, "Ilene", "Prowse"),
            8, new Customer(8, "Freddi", "Carder"),
            10, new Customer(10, "Levy", "Mynett")
        );

        // 2. CARREGAMENTO DOS PEDIDOS (sql_store.orders)
        List<Order> orders = Arrays.asList(
            new Order(1, 6, "2019-01-30"),
            new Order(2, 7, "2018-08-02"),
            new Order(3, 8, "2017-12-01"),
            new Order(4, 10, "2017-01-22"),
            new Order(5, 2, "2017-08-25"),
            new Order(6, 1, "2017-11-18"),
            new Order(7, 5, "2018-09-22"),
            new Order(8, 8, "2018-06-08"),
            new Order(9, 3, "2017-07-05"),
            new Order(10, 6, "2018-04-22")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A CONSULTA OFICIAL DO AUTOR (NATURAL JOIN)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. CONSULTA OFICIAL: 'orders o NATURAL JOIN customers c' ---");
        System.out.println("O banco examinou os campos e descobriu sozinho que ambos têm 'customerId':");
        System.out.println("+----------+------------+");
        System.out.println("| order_id | first_name |");
        System.out.println("+----------+------------+");

        for (Order o : orders) {
            Customer c = customers.get(o.customerId());
            if (c != null) {
                System.out.printf("| %8d | %-10s |\n", o.orderId(), c.firstName());
            }
        }
        System.out.println("+----------+------------+");
        System.out.println("10 rows in set (0.00 sec) -> Resultado idêntico ao ON e USING!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: COMO O BANCO "ADIVINHA" POR BAIXO DOS PANOS (SIMULAÇÃO VIA REFLECTION)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. BASTIDORES: COMO O MOTOR DESCOBRE AS COLUNAS COMUNS (JAVA REFLECTION) ---");
        Set<String> orderFields = getFieldNames(Order.class);
        Set<String> customerFields = getFieldNames(Customer.class);

        // Interseção de conjuntos (colunas que têm exatamente o mesmo nome)
        Set<String> commonFields = new HashSet<>(orderFields);
        commonFields.retainAll(customerFields);

        System.out.println("Campos da tabela orders:    " + orderFields);
        System.out.println("Campos da tabela customers: " + customerFields);
        System.out.println("🎯 Coluna comum detectada pelo banco: " + commonFields);
        System.out.println("👉 O motor gerou internamente a amarração: orders.customerId = customers.customerId\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: O SEVERO ALERTA DO PROFESSOR MOSH HAMEDANI
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. O SEVERO ALERTA DO PROFESSOR MOSH HAMEDANI ---");
        System.out.println("🚨 'Embora junções naturais sejam fáceis de escrever, elas podem produzir");
        System.out.println("    resultados inesperados! Porque você deixa o banco de dados ADIVINHAR a junção.'");
        System.out.println("💥 Se amanhã um DBA adicionar uma coluna 'status' em customers para indicar");
        System.out.println("   se o cliente está ativo (1) ou inativo (0), e orders já tem 'status',");
        System.out.println("   o NATURAL JOIN passará a exigir: customerId E status iguais!");
        System.out.println("   Isso destruirá os relatórios em produção sem nenhum erro de sintaxe!");
        System.out.println("✅ Conclusão do Mosh: Use sempre 'ON' ou 'USING' e fique longe de NATURAL JOIN!\n");
    }

    private static Set<String> getFieldNames(Class<?> clazz) {
        Set<String> names = new HashSet<>();
        for (Field f : clazz.getDeclaredFields()) {
            names.add(f.getName());
        }
        return names;
    }
}
