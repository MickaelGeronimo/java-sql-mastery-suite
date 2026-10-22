package com.mickaelgeronimo.mastery.core.oop.inheritance;

/**
 * Superclasse Animal que herda de {@link Organism} (Herança Multinível) (Capítulo 44).
 * Fornece a ação comum de nutrição {@code eat()} para todas as espécies animais.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Animal extends Organism {

    public Animal() {
        super();
    }

    public Animal(boolean isAlive) {
        super(isAlive);
    }

    /**
     * Executa a ação de alimentação padrão de um animal.
     */
    public void eat() {
        System.out.println("This animal is eating");
    }

    /**
     * Retorna a mensagem de alimentação para validação e testes.
     *
     * @return texto descritivo da alimentação
     */
    public String getEatingMessage() {
        return "This animal is eating";
    }
}
