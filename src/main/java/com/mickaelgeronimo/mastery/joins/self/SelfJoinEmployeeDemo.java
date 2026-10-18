package com.mickaelgeronimo.mastery.joins.self;

import java.util.ArrayList;
import java.util.List;

/**
 * SelfJoinEmployeeDemo
 * 
 * Demonstração prática para iniciantes de como o SELF JOIN (auto-junção)
 * do SQL funciona na Programação Orientada a Objetos em Java.
 * 
 * No SQL (sql_hr.employees):
 * A coluna 'reports_to' é uma chave estrangeira que aponta para o 'employee_id'
 * da MESMA tabela.
 * 
 * No Java:
 * A classe Employee possui um atributo que é do tipo Employee (ela aponta para si mesma)!
 */
public class SelfJoinEmployeeDemo {

    static class Employee {
        int employeeId;
        String firstName;
        String lastName;
        String jobTitle;
        Integer reportsTo; // ID do chefe (pode ser null se for o CEO!)

        Employee(int employeeId, String firstName, String lastName, String jobTitle, Integer reportsTo) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.jobTitle = jobTitle;
            this.reportsTo = reportsTo;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    // DTO representando o resultado do SELF JOIN
    static class EmployeeManagerDTO {
        int employeeId;
        String employeeName;
        String jobTitle;
        String managerName;

        EmployeeManagerDTO(int employeeId, String employeeName, String jobTitle, String managerName) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.jobTitle = jobTitle;
            this.managerName = managerName;
        }

        @Override
        public String toString() {
            return String.format("| %11d | %-16s | %-22s | %-16s |",
                    employeeId, employeeName, jobTitle, managerName);
        }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: COMO O 'SELF JOIN' (AUTO-JUNÇÃO) FUNCIONA NA PRÁTICA");
        System.out.println("================================================================================\n");

        // 1. Populando a tabela sql_hr.employees
        List<Employee> employees = new ArrayList<>();
        // ID 37270 é o CEO da empresa (Executive VP), não responde a ninguém (reportsTo = null)
        employees.add(new Employee(37270, "Yovonnda", "Magdalena", "General Manager", null));
        employees.add(new Employee(33391, "Darcy", "Nortunen", "Account Executive", 37270));
        employees.add(new Employee(37851, "Sayer", "Golbus", "Internal Auditor", 37270));
        employees.add(new Employee(40448, "Mindy", "Crissil", "VP Quality Control", 37270));
        employees.add(new Employee(56274, "Keriann", "Alloisi", "VP Sales", 37270));
        employees.add(new Employee(63196, "Alaster", "Scutchin", "Assistant Professor", 37270));

        System.out.println("Executando a consulta SQL:");
        System.out.println("SELECT e.employee_id, e.first_name, e.job_title, m.first_name AS manager");
        System.out.println("FROM employees e");
        System.out.println("JOIN employees m ON e.reports_to = m.employee_id;\n");

        System.out.println("+-------------+------------------+------------------------+------------------+");
        System.out.println("| employee_id | employee_name    | job_title              | manager_name     |");
        System.out.println("+-------------+------------------+------------------------+------------------+");

        List<EmployeeManagerDTO> result = new ArrayList<>();

        // Mecânica interna do SELF JOIN:
        // 'e' representa o funcionário subordinado
        // 'm' representa o mesmo funcionário atuando no papel de gerente
        for (Employee e : employees) {
            if (e.reportsTo == null) {
                // O CEO tem reportsTo = NULL. No INNER JOIN, nulos são descartados!
                continue;
            }
            for (Employee m : employees) {
                if (e.reportsTo.equals(m.employeeId)) {
                    result.add(new EmployeeManagerDTO(e.employeeId, e.firstName + " " + e.lastName, e.jobTitle, m.firstName));
                    break;
                }
            }
        }

        result.forEach(System.out::println);
        System.out.println("+-------------+------------------+------------------------+------------------+");
        System.out.println("Total de funcionários listados: " + result.size());
        System.out.println("⚠️ ATENÇÃO: Yovonnda Magdalena (CEO) sumiu porque reports_to é NULL!");
        System.out.println("No INNER JOIN, se não há correspondência, a linha é 100% descartada!\n");

        System.out.println("================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! O SELF JOIN CONECTA A TABELA CONSIGO MESMA!");
        System.out.println("================================================================================");
    }
}
