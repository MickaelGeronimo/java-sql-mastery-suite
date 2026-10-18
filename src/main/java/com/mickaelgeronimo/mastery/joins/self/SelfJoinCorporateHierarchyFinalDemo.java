package com.mickaelgeronimo.mastery.joins.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SelfJoinCorporateHierarchyFinalDemo
 * 
 * Demonstração prática para iniciantes de:
 * 1. Self Join combinado com Multi-Table Join (Funcionário + Gerente + Escritórios).
 * 2. Agrupamento (GROUP BY) no Self Join para contar subordinados por gerente.
 * 3. Detecção de trabalho remoto / escritórios distintos.
 */
public class SelfJoinCorporateHierarchyFinalDemo {

    static class Office {
        int officeId;
        String city;
        String state;

        Office(int officeId, String city, String state) {
            this.officeId = officeId;
            this.city = city;
            this.state = state;
        }

        @Override
        public String toString() {
            return city + " (" + state + ")";
        }
    }

    static class Employee {
        int id;
        String name;
        String jobTitle;
        Integer reportsTo;
        int officeId;

        Employee(int id, String name, String jobTitle, Integer reportsTo, int officeId) {
            this.id = id;
            this.name = name;
            this.jobTitle = jobTitle;
            this.reportsTo = reportsTo;
            this.officeId = officeId;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: O GRAND FINALE DAS AUTO-JUNÇÕES (SELF JOIN + MULTI-TABLE)");
        System.out.println("=========================================================================================\n");

        // 1. Tabela offices
        Map<Integer, Office> offices = new HashMap<>();
        offices.put(1, new Office(1, "San Francisco", "CA"));
        offices.put(2, new Office(2, "Boston", "MA"));
        offices.put(3, new Office(3, "Chicago", "IL"));

        // 2. Tabela employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(37270, "Yovonnda Magdalena", "General Manager", null, 1)); // SF
        employees.add(new Employee(33391, "Darcy Nortunen", "Account Executive", 37270, 1));   // SF (Mesmo que a chefe!)
        employees.add(new Employee(37851, "Sayer Golbus", "Internal Auditor", 37270, 1));      // SF (Mesmo que a chefe!)
        employees.add(new Employee(40448, "Mindy Crissil", "VP Quality Control", 37270, 1));   // SF (Mesmo que a chefe!)
        employees.add(new Employee(56274, "Keriann Alloisi", "VP Sales", 37270, 2));           // Boston (Escritório diferente!)
        employees.add(new Employee(63196, "Alaster Scutchin", "Assistant Professor", 37270, 2)); // Boston (Escritório diferente!)

        System.out.println("--- 1. RELATÓRIO EXECUTIVO: FUNCIONÁRIO + ESCRITÓRIO + GERENTE + ESCRITÓRIO DO GERENTE ---");
        System.out.println("+-------------+----------------------+--------------------+--------------------+--------------------+----------------------+");
        System.out.println("| employee_id | employee_name        | employee_office    | manager_name       | manager_office     | situacao_trabalho    |");
        System.out.println("+-------------+----------------------+--------------------+--------------------+--------------------+----------------------+");

        Map<String, Integer> subordinateCount = new HashMap<>();

        for (Employee e : employees) {
            if (e.reportsTo == null) continue; // Pula CEO no INNER JOIN

            for (Employee m : employees) {
                if (e.reportsTo.equals(m.id)) {
                    Office empOffice = offices.get(e.officeId);
                    Office mgrOffice = offices.get(m.officeId);

                    String situacao = (e.officeId == m.officeId) ? "Mesmo Escritorio" : "Escritorio Distinto";

                    System.out.printf("| %11d | %-20s | %-18s | %-18s | %-18s | %-20s |\n",
                            e.id, e.name, empOffice, m.name, mgrOffice, situacao);

                    // Contagem de subordinados
                    subordinateCount.put(m.name, subordinateCount.getOrDefault(m.name, 0) + 1);
                    break;
                }
            }
        }
        System.out.println("+-------------+----------------------+--------------------+--------------------+--------------------+----------------------+\n");

        System.out.println("--- 2. CONTAGEM DE SUBORDINADOS DIRETOS POR GERENTE (GROUP BY COUNT(*)) ---");
        System.out.println("+----------------------+------------------------+");
        System.out.println("| Nome do Gerente      | Total de Subordinados  |");
        System.out.println("+----------------------+------------------------+");
        for (Map.Entry<String, Integer> entry : subordinateCount.entrySet()) {
            System.out.printf("| %-20s | %22d |\n", entry.getKey(), entry.getValue());
        }
        System.out.println("+----------------------+------------------------+\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! SELF JOIN COMBINADO COM MULTI-TABLE É UMA ARMA PODEROSA!");
        System.out.println("=========================================================================================");
    }
}
