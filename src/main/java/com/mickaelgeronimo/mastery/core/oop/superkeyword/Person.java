package com.mickaelgeronimo.mastery.core.oop.superkeyword;

import java.util.Objects;

/**
 * Superclasse Person (Pessoa).
 * <p>
 * Demonstra a classe base fundamental do Capítulo 45 (Bro Code: "45.super 🦸‍♂️").
 * Fornece os atributos universais {@code first} e {@code last}, além do construtor
 * parametrizado para encadeamento via {@code super(first, last)}.
 * </p>
 */
public class Person {

    public String first;
    public String last;

    /**
     * Construtor parametrizado de Person.
     *
     * @param first Primeiro nome
     * @param last  Sobrenome
     */
    public Person(String first, String last) {
        this.first = Objects.requireNonNull(first, "First name cannot be null");
        this.last = Objects.requireNonNull(last, "Last name cannot be null");
    }

    /**
     * Imprime no console o nome completo formatado.
     */
    public void showName() {
        System.out.println(this.first + " " + this.last);
    }

    public String getFullName() {
        return this.first + " " + this.last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "Person{first='" + first + "', last='" + last + "'}";
    }
}
