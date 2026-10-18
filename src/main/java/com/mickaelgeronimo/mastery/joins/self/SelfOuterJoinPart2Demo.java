package com.mickaelgeronimo.mastery.joins.self;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 25 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Inversão de Auto-Junção Externa, Padrão Anti-Join e Agregação de Liderança
 * ==============================================================================
 */
public class SelfOuterJoinPart2Demo {

    static class Employee {
        int id;
        String firstName;
        String lastName;
        String jobTitle;
        Integer reportsTo; // null para o CEO/Presidente

        public Employee(int id, String firstName, String lastName, String jobTitle, Integer reportsTo) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.jobTitle = jobTitle;
            this.reportsTo = reportsTo;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 25 (PARTE 2) - SELF RIGHT JOIN & PADRÃO ANTI-JOIN        ");
        System.out.println("=========================================================================================\n");

        // 1. DADOS OFICIAIS DO BANCO sql_hr (10 Funcionários)
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", "Magdalena", "General Manager", null),
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

        // Agrupamos subordinados por ID do gerente (Índice invertido em memória)
        Map<Integer, List<Employee>> subordinatesByManager = new HashMap<>();
        for (Employee e : employees) {
            if (e.reportsTo != null) {
                subordinatesByManager.computeIfAbsent(e.reportsTo, k -> new ArrayList<>()).add(e);
            }
        }

        // Ordenar gerentes por ID para estabilidade na exibição
        List<Employee> sortedManagers = new ArrayList<>(employees);
        sortedManagers.sort(Comparator.comparingInt(e -> e.id));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A INVERSÃO (m LEFT JOIN e ON m.id = e.reports_to) -> 18 LINHAS
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. INVERSÃO SOBERANA DE GERENTES (m LEFT JOIN e ON m.employee_id = e.reports_to) ---");
        System.out.println("+------------+--------------+----------------+------------------+");
        System.out.println("| manager_id | manager_name | subordinate_id | subordinate_name |");
        System.out.println("+------------+--------------+----------------+------------------+");

        int totalRows = 0;
        for (Employee m : sortedManagers) {
            List<Employee> subs = subordinatesByManager.get(m.id);
            if (subs != null && !subs.isEmpty()) {
                // Para quem lidera pessoas (Yovonnda), imprime cada subordinado (Fan-out de 9 linhas)
                subs.sort(Comparator.comparingInt(s -> s.id));
                for (Employee sub : subs) {
                    System.out.printf("| %10d | %-12s | %14d | %-16s |\n", m.id, m.firstName, sub.id, sub.firstName);
                    totalRows++;
                }
            } else {
                // Para quem não lidera ninguém (os outros 9), imprime 1 linha com colunas NULL
                System.out.printf("| %10d | %-12s | %14s | %-16s |\n", m.id, m.firstName, "NULL", "NULL");
                totalRows++;
            }
        }
        System.out.println("+------------+--------------+----------------+------------------+");
        System.out.println(totalRows + " rows in set (0.00 sec) -> (9 liderados de Yovonnda + 9 colaboradores sem liderados = 18 linhas!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O PADRÃO ANTI-JOIN (COLABORADORES QUE NÃO LIDERAM NINGUÉM)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. O PADRÃO ANTI-JOIN (WHERE e.employee_id IS NULL) ---");
        System.out.println("🔍 Filtrando apenas especialistas operacionais (quem NÃO é gerente de ninguém):");
        System.out.println("+-------------+---------------------+---------------------+");
        System.out.println("| employee_id | employee_name       | job_title           |");
        System.out.println("+-------------+---------------------+---------------------+");

        List<Employee> nonManagers = sortedManagers.stream()
                .filter(m -> !subordinatesByManager.containsKey(m.id))
                .collect(Collectors.toList());

        for (Employee emp : nonManagers) {
            System.out.printf("| %11d | %-19s | %-19s |\n", emp.id, emp.getFullName(), emp.jobTitle);
        }
        System.out.println("+-------------+---------------------+---------------------+");
        System.out.println(nonManagers.size() + " rows in set (0.00 sec) -> Exatamente os 9 colaboradores operacionais!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: CONTAGEM DE SUBORDINADOS E A ARMADILHA DO COUNT(*) vs COUNT(col)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. AUDITORIA DE LIDERANÇA: COUNT(e.employee_id) vs A ARMADILHA DO COUNT(*) ---");
        System.out.println("+------------+--------------+---------------------+--------------------+");
        System.out.println("| manager_id | manager_name | job_title           | total_subordinates |");
        System.out.println("+------------+--------------+---------------------+--------------------+");

        // Ordenar por número de subordinados decrescente
        List<Employee> ranked = new ArrayList<>(sortedManagers);
        ranked.sort((a, b) -> {
            int countA = subordinatesByManager.getOrDefault(a.id, Collections.emptyList()).size();
            int countB = subordinatesByManager.getOrDefault(b.id, Collections.emptyList()).size();
            if (countA != countB) return Integer.compare(countB, countA);
            return Integer.compare(a.id, b.id);
        });

        for (Employee m : ranked) {
            int count = subordinatesByManager.getOrDefault(m.id, Collections.emptyList()).size();
            System.out.printf("| %10d | %-12s | %-19s | %18d |\n", m.id, m.firstName, m.jobTitle, count);
        }
        System.out.println("+------------+--------------+---------------------+--------------------+");
        System.out.println(ranked.size() + " rows in set (0.00 sec)\n");
    }
}
