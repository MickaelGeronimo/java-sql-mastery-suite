package com.mickaelgeronimo.mastery.core.oop;

import java.util.Objects;

/**
 * Entidade Estudante representativa dos fundamentos de Construtores em Java (Capítulo 40).
 * Demonstra resolução de sombreamento de variáveis com a palavra-chave {@code this},
 * inicialização de estado no momento da instanciação e regras de negócio padrão.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Student {

    public String name;
    public int age;
    public double gpa;
    public boolean isEnrolled;

    /**
     * Construtor padrão (sem argumentos) utilizado para demonstrar o problema dos clones.
     */
    public Student() {
        this.name = "Spongebob";
        this.age = 30;
        this.gpa = 3.2;
        this.isEnrolled = true;
    }

    /**
     * Construtor canônico do Capítulo 40 do Bro Code.
     * Recebe nome, idade e GPA por argumento e define automaticamente a matrícula como ativa.
     *
     * @param name nome do estudante
     * @param age  idade do estudante em anos
     * @param gpa  coeficiente de rendimento acadêmico (Grade Point Average)
     */
    public Student(String name, int age, double gpa) {
        this.name = Objects.requireNonNull(name, "Nome do estudante não pode ser nulo.");
        this.age = age;
        this.gpa = gpa;
        this.isEnrolled = true;
    }

    /**
     * Construtor sobrecarregado completo que permite especificar o status de matrícula.
     *
     * @param name       nome do estudante
     * @param age        idade do estudante
     * @param gpa        coeficiente de rendimento acadêmico
     * @param isEnrolled indicador de matrícula ativa
     */
    public Student(String name, int age, double gpa, boolean isEnrolled) {
        this.name = Objects.requireNonNull(name, "Nome do estudante não pode ser nulo.");
        this.age = age;
        this.gpa = gpa;
        this.isEnrolled = isEnrolled;
    }

    /**
     * Executa a ação de estudar utilizando o atributo interno do objeto apontado por {@code this.name}.
     */
    public void study() {
        System.out.println(this.name + " is studying");
    }

    /**
     * Retorna a representação formatada do perfil do estudante.
     *
     * @return resumo cadastral do aluno
     */
    public String getSummary() {
        return name + " (" + age + " anos) - GPA: " + gpa + " - Matriculado: " + (isEnrolled ? "Sim" : "Não");
    }
}
