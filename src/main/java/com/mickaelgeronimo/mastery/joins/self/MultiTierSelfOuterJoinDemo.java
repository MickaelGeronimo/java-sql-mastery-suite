package com.mickaelgeronimo.mastery.joins.self;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 25 (PARTE 3): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Auto-Junções Multiníveis em Cascata (Cascading Multi-Tier Self Outer Joins),
 *       A Quebra da Corrente (Zero Linhas) e Classificação de Níveis Hierárquicos
 * ==============================================================================
 */
public class MultiTierSelfOuterJoinDemo {

    static class Employee {
        int id;
        String firstName;
        String lastName;
        String jobTitle;
        Integer reportsTo; // ID do chefe direto (null para o CEO)

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
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 25 (PARTE 3) - CASCADING MULTI-TIER SELF OUTER JOINS      ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS REAIS DE sql_hr (10 Funcionários)
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", "Magdalena", "General Manager", null),      // CEO
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

        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }

        employees.sort(Comparator.comparingInt(e -> e.id));

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A CATÁSTROFE DA QUEBRA DA CORRENTE (LEFT JOIN m JOIN gm) -> 0 LINHAS!
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. A CATÁSTROFE DA QUEBRA DA CORRENTE (LEFT JOIN m JOIN gm) ---");
        System.out.println("⚠️ O desenvolvedor usou LEFT JOIN para o gerente 'm', mas usou INNER JOIN para 'gm':");
        System.out.println("+-------------+------------+----------+---------------+");
        System.out.println("| employee_id | employee   | manager  | grand_manager |");
        System.out.println("+-------------+------------+----------+---------------+");

        int brokenChainCount = 0;
        for (Employee e : employees) {
            // e -> m (LEFT JOIN: m pode ser nulo)
            Employee m = (e.reportsTo != null) ? employeeMap.get(e.reportsTo) : null;
            
            // m -> gm (INNER JOIN: gm DEVE existir e NÃO PODE SER NULO!)
            if (m != null && m.reportsTo != null && employeeMap.containsKey(m.reportsTo)) {
                Employee gm = employeeMap.get(m.reportsTo);
                System.out.printf("| %11d | %-10s | %-8s | %-13s |\n", e.id, e.firstName, m.firstName, gm.firstName);
                brokenChainCount++;
            }
        }
        System.out.println("+-------------+------------+----------+---------------+");
        System.out.println("Empty set (0.00 sec) -> ZERO LINHAS! O relatório inteiro foi aniquilado!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A SOLUÇÃO CORRETA COM DOUBLE SELF LEFT JOIN (10 LINHAS INTACTAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A SOLUÇÃO CORRETA: CASCADING DOUBLE SELF LEFT JOIN ---");
        System.out.println("✨ Todos os 10 funcionários são preservados com tratamento elegante de nulos:");
        System.out.println("+-------------+------------+--------------------+---------------------------------+");
        System.out.println("| employee_id | employee   | manager            | grand_manager                   |");
        System.out.println("+-------------+------------+--------------------+---------------------------------+");

        for (Employee e : employees) {
            Employee m = (e.reportsTo != null) ? employeeMap.get(e.reportsTo) : null;
            Employee gm = (m != null && m.reportsTo != null) ? employeeMap.get(m.reportsTo) : null;

            String managerStr = (m != null) ? m.firstName : "Nenhum (Topo)";
            String grandManagerStr = (gm != null) ? gm.firstName : "Nenhum (Chefe Imediato é o Topo)";

            System.out.printf("| %11d | %-10s | %-18s | %-31s |\n", e.id, e.firstName, managerStr, grandManagerStr);
        }
        System.out.println("+-------------+------------+--------------------+---------------------------------+");
        System.out.println("10 rows in set (0.00 sec) -> 100% da empresa contemplada com sucesso!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: CLASSIFICAÇÃO DINÂMICA DE ESCALÃO HIERÁRQUICO (CASE WHEN)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. CLASSIFICAÇÃO DE ESCALÃO HIERÁRQUICO (CASE WHEN EM JAVA) ---");
        System.out.println("+-------------+---------------------+---------------------+------------------------------------------+");
        System.out.println("| employee_id | employee_name       | job_title           | hierarchy_tier                           |");
        System.out.println("+-------------+---------------------+---------------------+------------------------------------------+");

        for (Employee e : employees) {
            Employee m = (e.reportsTo != null) ? employeeMap.get(e.reportsTo) : null;

            String tier;
            if (e.reportsTo == null) {
                tier = "👑 Nível 1: Diretoria Executiva (CEO)";
            } else if (m != null && m.reportsTo == null) {
                tier = "👔 Nível 2: Reporte Direto à Presidência";
            } else {
                tier = "💻 Nível 3: Operacional / Terceiro Escalão";
            }

            System.out.printf("| %11d | %-19s | %-19s | %-40s |\n", e.id, e.getFullName(), e.jobTitle, tier);
        }
        System.out.println("+-------------+---------------------+---------------------+------------------------------------------+");
        System.out.println("Auditoria de Organograma Concluída!\n");
    }
}
