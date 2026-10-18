package com.mickaelgeronimo.mastery.joins.self;

import java.util.ArrayList;
import java.util.List;

/**
 * SelfJoinHierarchyTreeDemo
 * 
 * Demonstração prática para iniciantes de como o SELF JOIN (auto-junção)
 * funciona no Java em dois formatos:
 * 1. Formato Tabular Relacional (igual ao SELECT SQL com INNER JOIN).
 * 2. Formato Hierárquico em Árvore (Organograma de Empresa com POO).
 */
public class SelfJoinHierarchyTreeDemo {

    // Modelo de Funcionário autorreferenciado
    static class Employee {
        int employeeId;
        String name;
        String jobTitle;
        Integer reportsTo; // ID do chefe no banco
        Employee manager;  // Referência direta de objeto
        List<Employee> subordinates = new ArrayList<>(); // Subordinados diretos

        Employee(int employeeId, String name, String jobTitle, Integer reportsTo) {
            this.employeeId = employeeId;
            this.name = name;
            this.jobTitle = jobTitle;
            this.reportsTo = reportsTo;
        }

        void addSubordinate(Employee sub) {
            this.subordinates.add(sub);
            sub.manager = this;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: SELF JOIN (AUTO-JUNÇÃO) E HIERARQUIA CORPORATIVA");
        System.out.println("==========================================================================\n");

        // 1. Simulação dos dados reais da tabela sql_hr.employees
        List<Employee> employeeTable = new ArrayList<>();
        Employee yovonnda = new Employee(37270, "Yovonnda Magdalena", "General Manager", null); // CEO!
        Employee darcy    = new Employee(33391, "Darcy Nortunen", "Account Executive", 37270);
        Employee sayer    = new Employee(37851, "Sayer Golbus", "Internal Auditor", 37270);
        Employee mindy    = new Employee(40448, "Mindy Crissil", "VP Quality Control", 37270);
        Employee keriann  = new Employee(56274, "Keriann Alloisi", "VP Sales", 37270);
        Employee alaster  = new Employee(63196, "Alaster Scutchin", "Assistant Professor", 37270);

        employeeTable.add(yovonnda);
        employeeTable.add(darcy);
        employeeTable.add(sayer);
        employeeTable.add(mindy);
        employeeTable.add(keriann);
        employeeTable.add(alaster);

        System.out.println("--- 1. CONSULTA SQL: SELECT e.employee_id, e.name, m.name AS manager ---");
        System.out.println("                     FROM employees e JOIN employees m ON e.reports_to = m.employee_id;\n");

        System.out.println("+-------------+----------------------+----------------------+----------------------+");
        System.out.println("| employee_id | employee_name        | job_title            | manager_name         |");
        System.out.println("+-------------+----------------------+----------------------+----------------------+");

        int linhasRetornadas = 0;
        // Simulação do SELF JOIN: Laço de funcionários (e) cruzando com laço de gerentes (m)
        for (Employee e : employeeTable) {
            if (e.reportsTo == null) {
                // No INNER JOIN, quem tem reports_to = NULL é eliminado!
                continue;
            }
            for (Employee m : employeeTable) {
                if (e.reportsTo.equals(m.employeeId)) {
                    System.out.printf("| %11d | %-20s | %-20s | %-20s |\n",
                            e.employeeId, e.name, e.jobTitle, m.name);
                    linhasRetornadas++;
                    // Vinculando na árvore
                    m.addSubordinate(e);
                    break;
                }
            }
        }
        System.out.println("+-------------+----------------------+----------------------+----------------------+");
        System.out.println("Total de linhas: " + linhasRetornadas + " (De um total de 6 funcionários cadastrados)");
        System.out.println("⚠️ NOTA: A CEO Yovonnda Magdalena foi descartada porque reports_to é NULL!\n");

        System.out.println("--- 2. VISÃO HIERÁRQUICA EM ÁRVORE NA MEMÓRIA DO JAVA (ORGANOGRAMA) ---");
        System.out.println("👑 [CEO] " + yovonnda.name + " (" + yovonnda.jobTitle + ")");
        for (Employee sub : yovonnda.subordinates) {
            System.out.println("   ├── 👔 " + sub.name + " (" + sub.jobTitle + ") [ID: " + sub.employeeId + "]");
        }

        System.out.println("\n==========================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! O SELF JOIN RESOLVE HIERARQUIAS NA MESMA TABELA!");
        System.out.println("==========================================================================");
    }
}
