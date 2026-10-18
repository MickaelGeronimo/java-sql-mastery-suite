package com.mickaelgeronimo.mastery.core.oop;

/**
 * Demonstração executável dos conceitos de Construtores em Java (Capítulo 40).
 * Simula a turma da Fenda do Biquíni: Bob Esponja, Patrick e Sandy Bochechas.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class ConstructorsDemo {

    private ConstructorsDemo() {
        // Classe utilitária
    }

    public static void runDemo() {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 40: CONSTRUTORES EM JAVA (STUDENT CONSTRUCTORS DEMO)         ");
        System.out.println("==========================================================================");

        // 1. Instanciando os 3 Estudantes Canônicos via Construtor com Argumentos
        Student student1 = new Student("Spongebob", 30, 3.2);
        Student student2 = new Student("Patrick", 34, 1.5);
        Student student3 = new Student("Sandy", 27, 4.0);

        System.out.println("\n--- 1. LEITURA DOS 4 ATRIBUTOS DE CADA ALUNO (12 SAÍDAS) ---");
        // Student 1: Spongebob
        System.out.println(student1.name);
        System.out.println(student1.age);
        System.out.println(student1.gpa);
        System.out.println(student1.isEnrolled);

        // Student 2: Patrick
        System.out.println(student2.name);
        System.out.println(student2.age);
        System.out.println(student2.gpa);
        System.out.println(student2.isEnrolled);

        // Student 3: Sandy
        System.out.println(student3.name);
        System.out.println(student3.age);
        System.out.println(student3.gpa);
        System.out.println(student3.isEnrolled);

        // 2. Executando o Método de Ação study() com this.name
        System.out.println("\n--- 2. AÇÕES DE INSTÂNCIA DINÂMICAS VIA this.name ---");
        student1.study();
        student2.study();
        student3.study();

        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
