package com.mickaelgeronimo.mastery.core.oop.inheritance;

/**
 * Subclasse Canina que herda de {@link Animal} (Capítulo 44).
 * Adiciona a vida canina exclusiva (1 vida) e a vocalização com {@code bark()}.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Dog extends Animal {

    public int lives = 1;

    public Dog() {
        super();
        this.lives = 1;
    }

    public Dog(int lives) {
        super();
        this.lives = lives;
    }

    /**
     * Emite o latido característico do cachorro.
     */
    public void bark() {
        System.out.println("The dog goes *woof*");
    }

    /**
     * Retorna a mensagem de latido para testes e auditoria.
     *
     * @return texto onomatopeico do latido
     */
    public String getBarkMessage() {
        return "The dog goes *woof*";
    }
}
