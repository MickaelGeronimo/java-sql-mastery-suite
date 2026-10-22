package com.mickaelgeronimo.mastery.core.oop.inheritance;

/**
 * Superclasse Raiz representativa da Herança Biológica (Capítulo 44).
 * Contém o atributo compartilhado {@code isAlive} transmitido para todos os seres vivos.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Organism {

    public boolean isAlive = true;

    public Organism() {
        this.isAlive = true;
    }

    public Organism(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
