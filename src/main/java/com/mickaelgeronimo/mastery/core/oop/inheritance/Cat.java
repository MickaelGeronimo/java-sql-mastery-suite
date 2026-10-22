package com.mickaelgeronimo.mastery.core.oop.inheritance;

/**
 * Subclasse Felina que herda de {@link Animal} (Capítulo 44).
 * Adiciona a vida felina mítica (9 vidas) e a vocalização com {@code speak()} (*meow*).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Cat extends Animal {

    public int lives = 9;

    public Cat() {
        super();
        this.lives = 9;
    }

    public Cat(int lives) {
        super();
        this.lives = lives;
    }

    /**
     * Emite o miado característico do gato.
     */
    public void speak() {
        System.out.println("The cat goes *meow*");
    }

    /**
     * Retorna a mensagem de miado para testes e auditoria.
     *
     * @return texto onomatopeico do miado
     */
    public String getSpeakMessage() {
        return "The cat goes *meow*";
    }
}
