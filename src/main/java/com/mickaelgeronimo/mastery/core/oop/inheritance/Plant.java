package com.mickaelgeronimo.mastery.core.oop.inheritance;

/**
 * Subclasse Vegetal que herda de {@link Organism} (Herança Hierárquica) (Capítulo 44).
 * Demonstra a especialização do ramo vegetal com o método {@code photosynthesize()}.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Plant extends Organism {

    public Plant() {
        super();
    }

    public Plant(boolean isAlive) {
        super(isAlive);
    }

    /**
     * Executa o processo de fotossíntese vegetal.
     */
    public void photosynthesize() {
        System.out.println("The plant absorbs sunlight");
    }

    /**
     * Retorna a mensagem de fotossíntese para validação e testes.
     *
     * @return texto descritivo da fotossíntese
     */
    public String getPhotosynthesizeMessage() {
        return "The plant absorbs sunlight";
    }
}
