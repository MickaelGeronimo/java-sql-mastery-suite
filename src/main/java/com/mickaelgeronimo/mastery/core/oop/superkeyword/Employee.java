package com.mickaelgeronimo.mastery.core.oop.superkeyword;

/**
 * Subclasse Employee (Funcionário).
 * <p>
 * Demonstra a extensão de {@link Person} e a utilização de {@code super(first, last)}
 * para reaproveitar a lógica de inicialização de nomes, adicionando o atributo
 * especializado {@code salary}.
 * </p>
 */
public class Employee extends Person {

    public double salary;

    /**
     * Construtor de Employee.
     *
     * @param first  Primeiro nome
     * @param last   Sobrenome
     * @param salary Salário base do funcionário
     */
    public Employee(String first, String last, double salary) {
        super(first, last);
        this.salary = salary;
    }

    /**
     * Exibe o salário do funcionário no formato demonstrado na aula:
     * {@code "<first>'s salary is $<salary>"}.
     */
    public void showSalary() {
        System.out.println(getSalaryFormatted());
    }

    /**
     * Retorna a string formatada do salário do funcionário.
     *
     * @return representação textual do salário
     */
    public String getSalaryFormatted() {
        return this.first + "'s salary is $" + (int) this.salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{first='" + first + "', last='" + last + "', salary=" + salary + "}";
    }
}
