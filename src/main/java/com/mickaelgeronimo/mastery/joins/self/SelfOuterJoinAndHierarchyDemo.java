package com.mickaelgeronimo.mastery.joins.self;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 23 (PARTE 4): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Auto-Junções Externas (Self Outer Joins) — Resgate do CEO,
 *       Contraste com RIGHT JOIN e Hierarquia Multinível em Memória
 * ==============================================================================
 */
public class SelfOuterJoinAndHierarchyDemo {

    static class Employee {
        int id;
        String name;
        Integer reportsTo; // null para a presidente/CEO

        public Employee(int id, String name, Integer reportsTo) {
            this.id = id;
            this.name = name;
            this.reportsTo = reportsTo;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: SELF OUTER JOIN (AUTO-JUNÇÃO EXTERNA) E O RESGATE DO CEO");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS (sql_hr.employees)
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", null),   // CEO / Top Executive
            new Employee(33391, "Darcy", 37270),
            new Employee(37851, "Sayer", 37270),
            new Employee(40448, "Minna", 33391),
            new Employee(56274, "Keriann", 37270)
        );

        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: INNER SELF JOIN (O PROBLEMA DO CEO SUMIDO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. INNER SELF JOIN (O CEO DESAPARECE DO ORGANOGRAMA) ---");
        System.out.println("+-------------+----------------------+----------------------+");
        System.out.println("| employee_id | first_name           | manager              |");
        System.out.println("+-------------+----------------------+----------------------+");

        int innerCount = 0;
        for (Employee e : employees) {
            for (Employee m : employees) {
                // Condição estrita de igualdade: reports_to != null e igual ao id
                if (e.reportsTo != null && e.reportsTo.equals(m.id)) {
                    System.out.printf("| %-11d | %-20s | %-20s |\n", e.id, e.name, m.name);
                    innerCount++;
                }
            }
        }
        System.out.println("+-------------+----------------------+----------------------+");
        System.out.println("Total de funcionários listados: " + innerCount + " de 5.");
        System.out.println("🚨 ATENÇÃO: A presidente da empresa (Yovonnda) FOI APAGADA do relatório!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: SELF LEFT JOIN (PRESERVAÇÃO TOTAL DOS 5 FUNCIONÁRIOS + CEO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SELF LEFT JOIN (O RESGATE DO CEO COM TRATAMENTO DE NULO) ---");
        System.out.println("+-------------+----------------------+--------------------------------------+");
        System.out.println("| employee_id | first_name           | manager                              |");
        System.out.println("+-------------+----------------------+--------------------------------------+");

        int leftCount = 0;
        for (Employee e : employees) {
            // Tenta buscar o gerente no mapa de funcionários:
            String managerName = Optional.ofNullable(e.reportsTo)
                    .map(employeeMap::get)
                    .map(m -> m.name)
                    .orElse("Nenhum (CEO / Presidente)");

            System.out.printf("| %-11d | %-20s | %-36s |\n", e.id, e.name, managerName);
            leftCount++;
        }
        System.out.println("+-------------+----------------------+--------------------------------------+");
        System.out.println("Total de funcionários listados: " + leftCount + " (100% da empresa preservada!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: SELF RIGHT JOIN (QUEM SÃO TODOS OS GERENTES DA EMPRESA?)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. SELF RIGHT JOIN (PRESERVA TODOS OS GERENTES, MESMO QUEM NÃO GERENCIA NINGUÉM) ---");
        System.out.println("+----------------------+----------------------+------------------------------------+");
        System.out.println("| Subordinado (e)      | Gerente (m)          | Diagnóstico de Liderança           |");
        System.out.println("+----------------------+----------------------+------------------------------------+");

        int rightCount = 0;
        for (Employee m : employees) {
            boolean managesAnyone = false;
            for (Employee e : employees) {
                if (e.reportsTo != null && e.reportsTo.equals(m.id)) {
                    System.out.printf("| %-20s | %-20s | %-34s |\n",
                            e.name, m.name, "Lidera equipe ativa");
                    managesAnyone = true;
                    rightCount++;
                }
            }
            if (!managesAnyone) {
                // O gerente preservado pelo RIGHT JOIN que não lidera ninguém:
                System.out.printf("| %-20s | %-20s | %-34s |\n",
                        "NULL (Sem subordinados)", m.name, "Não lidera ninguém");
                rightCount++;
            }
        }
        System.out.println("+----------------------+----------------------+------------------------------------+");
        System.out.println("Total de linhas geradas no RIGHT JOIN: " + rightCount + " linhas.");
        System.out.println("🔍 Veja: Sayer, Minna e Keriann aparecem como gerentes sem subordinados (NULL)!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 4: HIERARQUIA EM 3 NÍVEIS (DOUBLE SELF LEFT JOIN)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 4. HIERARQUIA EM 3 NÍVEIS (DOUBLE SELF LEFT JOIN: e -> m -> gm) ---");
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.println("| Funcionário (e)      | Gerente Direto (m)   | Diretor Geral (gm)   |");
        System.out.println("+----------------------+----------------------+----------------------+");

        for (Employee e : employees) {
            Employee m = (e.reportsTo != null) ? employeeMap.get(e.reportsTo) : null;
            Employee gm = (m != null && m.reportsTo != null) ? employeeMap.get(m.reportsTo) : null;

            String empName = e.name;
            String mgrName = (m != null) ? m.name : "Nenhum";
            String gmName = (gm != null) ? gm.name : "Nenhum";

            System.out.printf("| %-20s | %-20s | %-20s |\n", empName, mgrName, gmName);
        }
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.println("✨ Exemplo Perfeito: Minna reporta a Darcy, e Darcy reporta a Yovonnda!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO: AUTO-JUNÇÕES EXTERNAS RESOLVEM ORGANOGRAMAS CORPORATIVOS!");
        System.out.println("=========================================================================================");
    }
}
