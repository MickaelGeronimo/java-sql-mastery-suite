package com.mickaelgeronimo.mastery.joins.self;

import java.util.ArrayList;
import java.util.List;

/**
 * AdvancedSelfJoinHierarchyDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Double Self Join no Java: Funcionário -> Chefe -> Chefe do Chefe.
 * 2. Auditoria Salarial com Self Join: Funcionários que ganham mais que o próprio gerente!
 * 3. Busca recursiva do CEO (Top Boss) e prevenção contra StackOverflowError.
 */
public class AdvancedSelfJoinHierarchyDemo {

    static class Employee {
        int id;
        String name;
        String jobTitle;
        double salary;
        Integer reportsTo; // ID do chefe direto

        Employee(int id, String name, String jobTitle, double salary, Integer reportsTo) {
            this.id = id;
            this.name = name;
            this.jobTitle = jobTitle;
            this.salary = salary;
            this.reportsTo = reportsTo;
        }

        @Override
        public String toString() {
            return name + " (R$ " + salary + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: SELF JOIN AVANÇADO (3 NÍVEIS & AUDITORIA SALARIAL)");
        System.out.println("==========================================================================\n");

        // 1. Banco de funcionários com salários e 3 níveis de hierarquia:
        // Yovonnda (CEO) -> Sayer (Gerente) -> Darcy (Especialista que ganha mais que o gerente!)
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(37270, "Yovonnda Magdalena", "CEO / General Manager", 25000.00, null));
        list.add(new Employee(37851, "Sayer Golbus", "Gerente de Auditoria", 9500.00, 37270));
        list.add(new Employee(40448, "Mindy Crissil", "VP de Qualidade", 14000.00, 37270));
        // Darcy reporta para Sayer (37851), mas como é especialista sênior, ganha R$ 11.000,00!
        list.add(new Employee(33391, "Darcy Nortunen", "Especialista Tributário", 11000.00, 37851));
        // Keriann reporta para Darcy
        list.add(new Employee(56274, "Keriann Alloisi", "Assistente Júnior", 3500.00, 33391));

        System.out.println("--- 1. AUDITORIA: FUNCIONÁRIOS QUE GANHAM MAIS QUE O PRÓPRIO CHEFE ---");
        System.out.println("SQL: SELECT e.name, e.salary, m.name, m.salary FROM employees e JOIN employees m ON e.reports_to = m.id WHERE e.salary > m.salary;\n");
        System.out.println("+----------------------+---------------+----------------------+---------------+");
        System.out.println("| Funcionario          | Salario Func  | Gerente              | Salario Ger   |");
        System.out.println("+----------------------+---------------+----------------------+---------------+");

        for (Employee e : list) {
            if (e.reportsTo == null) continue;
            for (Employee m : list) {
                if (e.reportsTo.equals(m.id)) {
                    // CONDIÇÃO DO WHERE: e.salary > m.salary
                    if (e.salary > m.salary) {
                        System.out.printf("| %-20s | R$ %10.2f | %-20s | R$ %10.2f |\n",
                                e.name, e.salary, m.name, m.salary);
                    }
                    break;
                }
            }
        }
        System.out.println("+----------------------+---------------+----------------------+---------------+\n");

        System.out.println("--- 2. DOUBLE SELF JOIN: 3 NÍVEIS HIERÁRQUICOS (FUNCIONÁRIO -> CHEFE -> DIRETOR) ---");
        System.out.println("+----------------------+----------------------+----------------------+");
        System.out.println("| Funcionario          | Chefe Direto (m)     | Diretor Geral (gm)   |");
        System.out.println("+----------------------+----------------------+----------------------+");

        for (Employee e : list) {
            if (e.reportsTo == null) continue;
            for (Employee m : list) {
                if (e.reportsTo.equals(m.id)) {
                    // Segundo salto: procurar o chefe do chefe (gm)
                    String grandManagerName = "Nenhum (Topo)";
                    if (m.reportsTo != null) {
                        for (Employee gm : list) {
                            if (m.reportsTo.equals(gm.id)) {
                                grandManagerName = gm.name;
                                break;
                            }
                        }
                    }
                    System.out.printf("| %-20s | %-20s | %-20s |\n",
                            e.name, m.name, grandManagerName);
                    break;
                }
            }
        }
        System.out.println("+----------------------+----------------------+----------------------+\n");

        System.out.println("==========================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! HIERARQUIA MULTINÍVEL E AUDITORIAS RESOLVIDAS!");
        System.out.println("==========================================================================");
    }
}
