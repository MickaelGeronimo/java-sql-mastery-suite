package com.mickaelgeronimo.mastery.joins.self;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 25 (PARTE 4 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Grand Finale de Self Outer Joins — Auditoria Salarial 360°, Gaps de Liderança,
 *       Pares de Escritório e Mapeamento de Entidades JPA/Hibernate
 * ==============================================================================
 */
public class SelfOuterJoinFinalMasterDemo {

    static class Employee {
        int id;
        String firstName;
        String lastName;
        String jobTitle;
        double salary;
        Integer reportsTo; // null para o CEO
        int officeId;

        public Employee(int id, String firstName, String lastName, String jobTitle, double salary, Integer reportsTo, int officeId) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.jobTitle = jobTitle;
            this.salary = salary;
            this.reportsTo = reportsTo;
            this.officeId = officeId;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 25 (PARTE 4 - FINAL) - MASTER SELF OUTER JOINS            ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS 10 FUNCIONÁRIOS DO BANCO sql_hr
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", "Magdalena", "General Manager", 110000.0, null, 3),      // CEO
            new Employee(33391, "Darcy", "Nortunen", "Account Executive", 80000.0, 37270, 1),
            new Employee(37851, "Sayer", "Golbus", "Internal Auditor", 75000.0, 37270, 2),
            new Employee(40448, "Mindy", "Crissil", "VP Quality Control", 94000.0, 37270, 1),
            new Employee(56274, "Keriann", "Alloisi", "VP Sales", 90000.0, 37270, 1),
            new Employee(63196, "Alaster", "Scutchin", "Assistant Professor", 60000.0, 37270, 2),
            new Employee(67009, "Gretthal", "Lindam", "VP Marketing", 92000.0, 37270, 2),
            new Employee(67370, "Hermina", "Simer", "Legal Assistant", 50000.0, 37270, 1),
            new Employee(68249, "Jacquelyn", "Chidlow", "Senior Developer", 95000.0, 37270, 3),
            new Employee(72540, "Aristotle", "Salter", "Software Engineer", 88000.0, 37270, 1)
        );

        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: AUDITORIA SALARIAL 360° E CÁLCULO DE GAP COM O LÍDER
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. AUDITORIA SALARIAL 360°: GAP SALARIAL EM RELAÇÃO AO LÍDER DIRETO ---");
        System.out.println("+-------------+---------------------+---------------------+-----------+--------------+------------+");
        System.out.println("| employee_id | employee_name       | job_title           | salary    | manager_name | salary_gap |");
        System.out.println("+-------------+---------------------+---------------------+-----------+--------------+------------+");

        // Ordenar por gap decrescente
        List<Employee> sortedByGap = new ArrayList<>(employees);
        sortedByGap.sort((a, b) -> {
            double gapA = (a.reportsTo != null) ? (employeeMap.get(a.reportsTo).salary - a.salary) : 0.0;
            double gapB = (b.reportsTo != null) ? (employeeMap.get(b.reportsTo).salary - b.salary) : 0.0;
            return Double.compare(gapB, gapA);
        });

        for (Employee e : sortedByGap) {
            Employee m = (e.reportsTo != null) ? employeeMap.get(e.reportsTo) : null;
            String managerName = (m != null) ? m.firstName : "Nenhum (Topo)";
            double gap = (m != null) ? (m.salary - e.salary) : 0.0;

            System.out.printf("| %11d | %-19s | %-19s | %9.2f | %-12s | %10.2f |\n",
                    e.id, e.getFullName(), e.jobTitle, e.salary, managerName, gap);
        }
        System.out.println("+-------------+---------------------+---------------------+-----------+--------------+------------+");
        System.out.println("10 rows in set (0.00 sec) -> Hermina tem a maior distância para o topo ($60.000)!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: PEER MATCHING (COLEGAS QUE DIVIDEM A MESMA SALA/ESCRITÓRIO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. PEER MATCHING: COLEGAS QUE DIVIDEM O MESMO ESCRITÓRIO (office_id) ---");
        System.out.println("+-----------+---------------------+---------------------+");
        System.out.println("| office_id | employee_1          | office_partner      |");
        System.out.println("+-----------+---------------------+---------------------+");

        int peerCount = 0;
        for (Employee e1 : employees) {
            for (Employee e2 : employees) {
                // Condição de amarração: mesmo escritório e e1.id < e2.id para não duplicar espelho
                if (e1.officeId == e2.officeId && e1.id < e2.id) {
                    System.out.printf("| %9d | %-19s | %-19s |\n", e1.officeId, e1.getFullName(), e2.getFullName());
                    peerCount++;
                }
            }
        }
        System.out.println("+-----------+---------------------+---------------------+");
        System.out.println(peerCount + " pares de colegas encontrados em 3 escritórios!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: COMO O HIBERNATE / SPRING DATA JPA MAPEIA O SELF OUTER JOIN
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A REGRA DE OURO NO JPA / HIBERNATE ---");
        System.out.println("No Spring Boot / Hibernate:");
        System.out.println("  @ManyToOne(fetch = FetchType.LAZY, optional = true)  <-- 'optional = true' GERA LEFT JOIN!");
        System.out.println("  @JoinColumn(name = \"reports_to\")");
        System.out.println("  private Employee manager;");
        System.out.println("");
        System.out.println("⚠️ Se você configurar 'optional = false', o Hibernate emitirá um INNER JOIN no SQL!");
        System.out.println("Isso fará o CEO Yovonnda ser DELETADO de qualquer busca do seu repositório Java!\n");
        System.out.println("Grand Finale do Capítulo 25 Concluído com Sucesso Absoluto!");
    }
}
