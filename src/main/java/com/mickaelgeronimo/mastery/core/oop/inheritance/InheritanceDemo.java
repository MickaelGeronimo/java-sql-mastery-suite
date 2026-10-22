package com.mickaelgeronimo.mastery.core.oop.inheritance;

/**
 * Demonstração executável dos conceitos de Herança em Java (Capítulo 44).
 * Apresenta a árvore biológica completa:
 * Organism -> Animal -> Dog, Cat
 *          -> Plant
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class InheritanceDemo {

    private InheritanceDemo() {
        // Classe utilitária
    }

    public static void runDemo() {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 44: HERANÇA EM JAVA (BIOLOGICAL INHERITANCE TREE)             ");
        System.out.println("==========================================================================");

        // 1. Instanciação dos três ramos
        Dog dog = new Dog();
        Cat cat = new Cat();
        Plant plant = new Plant();

        System.out.println("\n--- 1. ATRIBUTOS HERDADOS DA RAIZ (Organism.isAlive) ---");
        System.out.println("dog.isAlive:   " + dog.isAlive);
        System.out.println("cat.isAlive:   " + cat.isAlive);
        System.out.println("plant.isAlive: " + plant.isAlive);

        System.out.println("\n--- 2. MÉTODOS HERDADOS DO RAMO ANIMAL (eat) ---");
        dog.eat();
        cat.eat();

        System.out.println("\n--- 3. AÇÕES EXCLUSIVAS DE CADA ESPÉCIE ---");
        dog.bark();
        cat.speak();
        plant.photosynthesize();

        System.out.println("\n--- 4. ATRIBUTOS EXCLUSIVOS DAS SUBCLASSES (Vidas) ---");
        System.out.println("dog.lives: " + dog.lives);
        System.out.println("cat.lives: " + cat.lives);

        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
