package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.inheritance.Animal;
import com.mickaelgeronimo.mastery.core.oop.inheritance.Cat;
import com.mickaelgeronimo.mastery.core.oop.inheritance.Dog;
import com.mickaelgeronimo.mastery.core.oop.inheritance.InheritanceDemo;
import com.mickaelgeronimo.mastery.core.oop.inheritance.Organism;
import com.mickaelgeronimo.mastery.core.oop.inheritance.Plant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 44: Testes Automatizados de Herança em Java (Inheritance)")
class InheritanceTest {

    @Test
    @DisplayName("Deve herdar o atributo isAlive da superclasse raiz Organism em todos os ramos")
    void shouldInheritIsAliveFromOrganismRoot() {
        Dog dog = new Dog();
        Cat cat = new Cat();
        Plant plant = new Plant();

        assertThat(dog.isAlive).isTrue();
        assertThat(cat.isAlive).isTrue();
        assertThat(plant.isAlive).isTrue();

        assertThat(dog).isInstanceOf(Organism.class).isInstanceOf(Animal.class);
        assertThat(cat).isInstanceOf(Organism.class).isInstanceOf(Animal.class);
        assertThat(plant).isInstanceOf(Organism.class);
        assertThat(plant).isNotInstanceOf(Animal.class);
    }

    @Test
    @DisplayName("Deve herdar o método eat() da classe pai Animal para Dog e Cat")
    void shouldInheritEatMethodFromAnimalParent() {
        Dog dog = new Dog();
        Cat cat = new Cat();

        assertThat(dog.getEatingMessage()).isEqualTo("This animal is eating");
        assertThat(cat.getEatingMessage()).isEqualTo("This animal is eating");
    }

    @Test
    @DisplayName("Deve verificar os atributos e métodos exclusivos da subclasse Dog (1 vida e latido)")
    void shouldHaveExclusiveAttributesAndMethodsForDog() {
        Dog dog = new Dog();

        assertThat(dog.lives).isEqualTo(1);
        assertThat(dog.getBarkMessage()).isEqualTo("The dog goes *woof*");
    }

    @Test
    @DisplayName("Deve verificar os atributos e métodos exclusivos da subclasse Cat (9 vidas e miado)")
    void shouldHaveExclusiveAttributesAndMethodsForCat() {
        Cat cat = new Cat();

        assertThat(cat.lives).isEqualTo(9);
        assertThat(cat.getSpeakMessage()).isEqualTo("The cat goes *meow*");
    }

    @Test
    @DisplayName("Deve verificar o método exclusivo de fotossíntese do ramo Plant")
    void shouldHaveExclusivePhotosynthesizeMethodForPlant() {
        Plant plant = new Plant();

        assertThat(plant.getPhotosynthesizeMessage()).isEqualTo("The plant absorbs sunlight");
    }

    @Test
    @DisplayName("Deve suportar polimorfismo através da superclasse comum Organism")
    void shouldProvePolymorphismAndTypeHierarchy() {
        Organism[] livingThings = new Organism[]{new Dog(), new Cat(), new Plant()};

        assertThat(livingThings).hasSize(3);
        for (Organism organism : livingThings) {
            assertThat(organism.isAlive).isTrue();
        }
    }

    @Test
    @DisplayName("Deve executar a demonstração InheritanceDemo sem falhas")
    void shouldRunInheritanceDemoSuccessfully() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            InheritanceDemo.runDemo();

            String output = outputStream.toString(StandardCharsets.UTF_8);

            assertThat(output)
                    .contains("CAPÍTULO 44: HERANÇA EM JAVA")
                    .contains("dog.isAlive:   true")
                    .contains("cat.isAlive:   true")
                    .contains("plant.isAlive: true")
                    .contains("This animal is eating")
                    .contains("The dog goes *woof*")
                    .contains("The cat goes *meow*")
                    .contains("The plant absorbs sunlight")
                    .contains("dog.lives: 1")
                    .contains("cat.lives: 9");
        } finally {
            System.setOut(originalOut);
        }
    }
}
