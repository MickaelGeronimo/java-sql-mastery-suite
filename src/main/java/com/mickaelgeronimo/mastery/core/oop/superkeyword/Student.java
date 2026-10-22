package com.mickaelgeronimo.mastery.core.oop.superkeyword;

/**
 * Subclasse Student (Estudante).
 * <p>
 * Demonstra a extensão de {@link Person} e a utilização mandatória de
 * {@code super(first, last)} para invocar o construtor da superclasse,
 * adicionando a propriedade especializada {@code gpa} (Grade Point Average).
 * </p>
 */
public class Student extends Person {

    public double gpa;

    /**
     * Construtor de Student.
     * <p>
     * A invocação {@code super(first, last)} deve obrigatoriamente ser a primeira
     * instrução do construtor para satisfazer o contrato de inicialização da superclasse.
     * </p>
     *
     * @param first Primeiro nome
     * @param last  Sobrenome
     * @param gpa   Média escolar (Grade Point Average)
     */
    public Student(String first, String last, double gpa) {
        super(first, last);
        this.gpa = gpa;
    }

    /**
     * Exibe a média do estudante no formato demonstrado na aula:
     * {@code "<first>'s gpa is: <gpa>"}.
     */
    public void showGPA() {
        System.out.println(getGpaFormatted());
    }

    /**
     * Retorna a string formatada da média do estudante.
     *
     * @return representação textual do GPA
     */
    public String getGpaFormatted() {
        return this.first + "'s gpa is: " + this.gpa;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{first='" + first + "', last='" + last + "', gpa=" + gpa + "}";
    }
}
