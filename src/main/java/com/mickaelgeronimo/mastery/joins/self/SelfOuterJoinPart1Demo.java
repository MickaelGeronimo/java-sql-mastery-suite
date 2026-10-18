package com.mickaelgeronimo.mastery.joins.self;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 25 (PARTE 1): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Auto-Junção Externa (Self Outer Join) — O Resgate do CEO no Organograma
 * Comparação Didática: INNER JOIN vs LEFT JOIN sobre a Mesma Tabela
 * ==============================================================================
 */
public class SelfOuterJoinPart1Demo {

    // Modelo de Dados representando a tabela 'employees' do banco sql_hr
    static class Employee {
        int employeeId;
        String firstName;
        String lastName;
        String jobTitle;
        Integer reportsTo; // null quando a pessoa não responde a ninguém (CEO)

        public Employee(int employeeId, String firstName, String lastName, String jobTitle, Integer reportsTo) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.jobTitle = jobTitle;
            this.reportsTo = reportsTo;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 25 (PARTE 1) - SELF OUTER JOIN (AUTO-JUNÇÃO EXTERNA)   ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS IDÊNTICOS AO BANCO sql_hr (10 Funcionários)
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", "Magdalena", "General Manager", null),      // CEO / Sem Gerente
            new Employee(33391, "Darcy", "Nortunen", "Account Executive", 37270),
            new Employee(37851, "Sayer", "Golbus", "Internal Auditor", 37270),
            new Employee(40448, "Mindy", "Crissil", "VP Quality Control", 37270),
            new Employee(56274, "Keriann", "Alloisi", "VP Sales", 37270),
            new Employee(63196, "Alaster", "Scutchin", "Assistant Professor", 37270),
            new Employee(67009, "Gretthal", "Lindam", "VP Marketing", 37270),
            new Employee(67370, "Hermina", "Simer", "Legal Assistant", 37270),
            new Employee(68249, "Jacquelyn", "Chidlow", "Senior Developer", 37270),
            new Employee(72540, "Aristotle", "Salter", "Software Engineer", 37270)
        );

        // Mapa para busca rápida de gerentes pelo ID (simula o índice B-Tree da chave primária)
        Map<Integer, Employee> managersMap = new HashMap<>();
        for (Employee emp : employees) {
            managersMap.put(emp.employeeId, emp);
        }

        // Ordenamos por employeeId para manter estabilidade na exibição
        employees.sort(Comparator.comparingInt(e -> e.employeeId));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: INNER SELF JOIN (A FALHA: O CEO É DESAPARECIDO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- CENÁRIO 1: INNER SELF JOIN (e.reports_to = m.employee_id) ---");
        System.out.println("⚠️ Observe que apenas 9 funcionários aparecem. Yovonnda (o CEO) foi apagada!");
        System.out.println("+-------------+------------+----------+");
        System.out.println("| employee_id | first_name | manager  |");
        System.out.println("+-------------+------------+----------+");

        int innerCount = 0;
        for (Employee e : employees) {
            // No INNER JOIN, se reportsTo for nulo ou o gerente não existir, a linha é descartada!
            if (e.reportsTo != null && managersMap.containsKey(e.reportsTo)) {
                Employee m = managersMap.get(e.reportsTo);
                System.out.printf("| %11d | %-10s | %-8s |\n", e.employeeId, e.firstName, m.firstName);
                innerCount++;
            }
        }
        System.out.println("+-------------+------------+----------+");
        System.out.println(innerCount + " rows in set (0.00 sec) -> ERRO DE NEGÓCIO: O Presidente sumiu da folha!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: SELF LEFT JOIN (A SOLUÇÃO DEFINITIVA DO AUTOR)
        // --------------------------------------------------------------------------------------
        System.out.println("--- CENÁRIO 2: SELF LEFT JOIN (FROM employees e LEFT JOIN employees m) ---");
        System.out.println("✨ Todos os 10 funcionários são preservados! Yovonnda aparece com 'NULL'.");
        System.out.println("+-------------+------------+----------+");
        System.out.println("| employee_id | first_name | manager  |");
        System.out.println("+-------------+------------+----------+");

        int leftCount = 0;
        for (Employee e : employees) {
            // No LEFT JOIN, a tabela 'e' SEMPRE é impressa!
            // Se houver gerente, pegamos o nome; se não houver (reportsTo == null), colocamos "NULL"
            String managerName = (e.reportsTo != null && managersMap.containsKey(e.reportsTo))
                    ? managersMap.get(e.reportsTo).firstName
                    : "NULL";

            System.out.printf("| %11d | %-10s | %-8s |\n", e.employeeId, e.firstName, managerName);
            leftCount++;
        }
        System.out.println("+-------------+------------+----------+");
        System.out.println(leftCount + " rows in set (0.00 sec) -> PERFEITO: 100% da empresa contemplada!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: SELF LEFT JOIN COM TRATAMENTO DE NULOS (IFNULL / COALESCE)
        // --------------------------------------------------------------------------------------
        System.out.println("--- CENÁRIO 3: RELATÓRIO PROFISSIONAL COM IFNULL / Optional.orElse() ---");
        System.out.println("+-------------+------------+--------------------------------+");
        System.out.println("| employee_id | first_name | manager                        |");
        System.out.println("+-------------+------------+--------------------------------+");

        for (Employee e : employees) {
            // Em Java moderno, usamos Optional para tratar valores potencialmente nulos:
            String managerName = Optional.ofNullable(e.reportsTo)
                    .map(managersMap::get)
                    .map(m -> m.firstName)
                    .orElse("Nenhum (Líder Supremo / CEO)");

            System.out.printf("| %11d | %-10s | %-30s |\n", e.employeeId, e.firstName, managerName);
        }
        System.out.println("+-------------+------------+--------------------------------+");
        System.out.println("Relatório Gerencial Pronto para o Conselho de Administração!\n");
    }
}
