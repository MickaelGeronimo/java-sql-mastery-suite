package com.mickaelgeronimo.mastery.core.oop.superkeyword;

/**
 * Demonstração interativa dos conceitos do Capítulo 45: Palavra-chave {@code super}.
 * <p>
 * Executa os cenários didáticos do curso de Bro Code:
 * 1. Instanciação direta da superclasse Person ("Tom Riddle").
 * 2. Instanciação da subclasse Student delegando nomes via super ("Harry Potter").
 * 3. Instanciação da subclasse Employee delegando nomes via super ("Rubeus Hagrid").
 * </p>
 */
public class SuperKeywordDemo {

    public static void main(String[] args) {
        System.out.println("=== Capítulo 45: Palavra-chave super 🦸‍♂️ ===");

        // 1. Instanciando a superclasse Person
        Person person = new Person("Tom", "Riddle");
        System.out.print("Person showName: ");
        person.showName();

        // 2. Instanciando Student (passando first e last para super)
        Student student = new Student("Harry", "Potter", 3.25);
        System.out.print("Student showName (herdado de Person): ");
        student.showName();
        System.out.print("Student showGPA (próprio do Student): ");
        student.showGPA();

        // 3. Instanciando Employee (passando first e last para super)
        Employee employee = new Employee("Rubeus", "Hagrid", 50000);
        System.out.print("Employee showName (herdado de Person): ");
        employee.showName();
        System.out.print("Employee showSalary (próprio do Employee): ");
        employee.showSalary();

        System.out.println("=== Fim da Demonstração ===");
    }
}
