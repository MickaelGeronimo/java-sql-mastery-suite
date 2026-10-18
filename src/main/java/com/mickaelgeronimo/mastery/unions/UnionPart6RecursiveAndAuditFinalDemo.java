package com.mickaelgeronimo.mastery.unions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==============================================================================
 * CAPÍTULO 29 (PARTE 6 FINAL SUPREMO): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Simulação de CTEs Recursivas (WITH RECURSIVE + UNION ALL),
 *       Árvores Hierárquicas de Profundidade Infinita e Auditoria Temporal
 * ==============================================================================
 */
public class UnionPart6RecursiveAndAuditFinalDemo {

    public record Employee(int id, String firstName, String lastName, Integer reportsTo) {
        public String fullName() {
            return firstName + " " + lastName;
        }
    }

    public record HierarchyNode(int id, String fullName, int level, String path) {
        @Override
        public String toString() {
            String indent = "   ".repeat(Math.max(0, level - 1));
            return String.format("| %9d | %-28s | %5d | %-50s |", id, indent + fullName, level, path);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 29 (PARTE 6 FINAL) - UNIONS RECURSIVAS E AUDITORIA       ");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS 10 FUNCIONÁRIOS DA TABELA sql_hr.employees
        List<Employee> employees = Arrays.asList(
            new Employee(37270, "Yovonnda", "Magrannell", null),      // CEO / Raiz
            new Employee(33391, "Darcy", "Hercules", 37270),           // Gerente
            new Employee(40448, "Mindy", "Boffee", 37270),            // Gerente
            new Employee(56274, "Keriann", "Kelly", 37270),           // Gerente
            new Employee(67370, "Elladine", "Pridham", 37270),         // Gerente
            new Employee(37851, "Sayer", "Weiss", 33391),             // Subordinado de Darcy
            new Employee(68249, "Nanci", "Sneath", 33391),            // Subordinado de Darcy
            new Employee(72540, "Yuri", "van der Kolk", 33391),        // Subordinado de Darcy
            new Employee(80529, "Salome", "Sowden", 33391),           // Subordinado de Darcy
            new Employee(63196, "Alaster", "Gatrill", 67370)          // Subordinado de Elladine
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: SIMULAÇÃO DA CTE RECURSIVA (WITH RECURSIVE + UNION ALL)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. ORGANOGRAMA HIERÁRQUICO COMPLETO (WITH RECURSIVE + UNION ALL) ---");
        System.out.println("+-----------+------------------------------+-------+----------------------------------------------------+");
        System.out.println("| entity_id | organizational_chart         | level | management_path                                    |");
        System.out.println("+-----------+------------------------------+-------+----------------------------------------------------+");

        // Localiza a Raiz (Anchor Member: reports_to == null)
        Employee ceo = employees.stream()
                .filter(e -> e.reportsTo() == null)
                .findFirst()
                .orElseThrow();

        List<HierarchyNode> hierarchyList = new ArrayList<>();
        // Inicia a recursão acumulando os níveis (exatamente como o UNION ALL recursivo faz)
        buildHierarchyRecursive(ceo, 1, ceo.fullName(), employees, hierarchyList);

        for (HierarchyNode node : hierarchyList) {
            System.out.println(node);
        }
        System.out.println("+-----------+------------------------------+-------+----------------------------------------------------+");
        System.out.println(hierarchyList.size() + " colaboradores no organograma de 3 níveis de profundidade!\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: AUDITORIA TEMPORAL (UNIFICANDO DADOS QUENTES E FRIOS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. AUDITORIA TEMPORAL DE ARMAZENAMENTO (LIVE PRODUCTION vs COLD ARCHIVE) ---");
        System.out.println("+----------+-------------+------------+--------------------+");
        System.out.println("| order_id | customer_id | order_date | storage_partition  |");
        System.out.println("+----------+-------------+------------+--------------------+");

        List<String> auditLog = Arrays.asList(
            "|        1 |           6 | 2019-01-30 | LIVE_PRODUCTION    |",
            "|        7 |           8 | 2018-09-22 | COLD_ARCHIVE       |",
            "|        2 |           7 | 2018-08-02 | COLD_ARCHIVE       |",
            "|        8 |           5 | 2018-06-08 | COLD_ARCHIVE       |",
            "|       10 |          10 | 2018-04-22 | COLD_ARCHIVE       |",
            "|        3 |          10 | 2017-12-01 | COLD_ARCHIVE       |",
            "|        6 |          10 | 2017-11-18 | COLD_ARCHIVE       |",
            "|        5 |           5 | 2017-08-25 | COLD_ARCHIVE       |",
            "|        9 |           8 | 2017-07-05 | COLD_ARCHIVE       |",
            "|        4 |           8 | 2017-01-22 | COLD_ARCHIVE       |"
        );

        auditLog.forEach(System.out::println);
        System.out.println("+----------+-------------+------------+--------------------+");
        System.out.println("10 pedidos unificados transparentemente para auditoria fiscal!\n");

        System.out.println("=========================================================================================");
        System.out.println("   FINALIZAÇÃO TRIUNFAL: SEÇÃO 3 (CONSULTAS MULTI-TABELAS) CONCLUÍDA EM MAESTRIA TOTAL   ");
        System.out.println("=========================================================================================");
    }

    // Método recursivo que emula o 'UNION ALL' da CTE recursiva:
    private static void buildHierarchyRecursive(Employee current, int level, String path,
                                                List<Employee> allEmployees, List<HierarchyNode> result) {
        result.add(new HierarchyNode(current.id(), current.fullName(), level, path));

        // Encontra subordinados imediatos (Membro Recursivo)
        List<Employee> subordinates = allEmployees.stream()
                .filter(e -> current.id() == (e.reportsTo() != null ? e.reportsTo() : -1))
                .sorted(Comparator.comparing(Employee::fullName))
                .collect(Collectors.toList());

        for (Employee sub : subordinates) {
            buildHierarchyRecursive(sub, level + 1, path + " -> " + sub.fullName(), allEmployees, result);
        }
    }
}
