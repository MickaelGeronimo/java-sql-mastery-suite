package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.Car;
import com.mickaelgeronimo.mastery.core.oop.OOPCarDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 39: Testes Automatizados de Classes e Objetos (Car OOP)")
class OOPCarTest {

    @Test
    @DisplayName("Deve inicializar objeto Car com os valores padrão de fábrica (Ford Mustang 2025)")
    void shouldInitializeWithDefaultFactoryValues() {
        Car car = new Car();

        assertThat(car.make).isEqualTo("Ford");
        assertThat(car.model).isEqualTo("Mustang");
        assertThat(car.year).isEqualTo(2025);
        assertThat(car.price).isEqualTo(58000.99);
        assertThat(car.isRunning).isFalse();
    }

    @Test
    @DisplayName("Deve transitar o estado de isRunning entre ligado (true) e desligado (false)")
    void shouldChangeEngineStateWhenStartingAndStopping() {
        Car car = new Car();

        assertThat(car.isRunning).isFalse();

        car.start();
        assertThat(car.isRunning).isTrue();

        car.stop();
        assertThat(car.isRunning).isFalse();
    }

    @Test
    @DisplayName("Deve garantir independência de memória entre duas instâncias no Heap")
    void shouldEnsureMemoryIndependenceBetweenInstances() {
        Car car1 = new Car();
        Car car2 = new Car();

        // Modifica apenas car1
        car1.make = "Chevrolet";
        car1.model = "Corvette";
        car1.year = 2026;
        car1.price = 68300.00;

        // car1 deve refletir as mudanças
        assertThat(car1.make).isEqualTo("Chevrolet");
        assertThat(car1.model).isEqualTo("Corvette");
        assertThat(car1.year).isEqualTo(2026);
        assertThat(car1.price).isEqualTo(68300.00);

        // car2 deve permanecer intacto com os valores padrão
        assertThat(car2.make).isEqualTo("Ford");
        assertThat(car2.model).isEqualTo("Mustang");
        assertThat(car2.year).isEqualTo(2025);
        assertThat(car2.price).isEqualTo(58000.99);
    }

    @Test
    @DisplayName("Deve imprimir as mensagens esperadas ao executar drive() e brake()")
    void shouldPrintActionMessagesAccurately() {
        Car car = new Car();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

            car.drive();
            car.brake();
            car.start();
            car.stop();

            String output = outputStream.toString(StandardCharsets.UTF_8);

            assertThat(output)
                    .contains("You drive the Mustang")
                    .contains("You brake the Mustang")
                    .contains("You start the engine")
                    .contains("You stop the engine");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Deve permitir instanciação com construtor customizado de forma íntegra")
    void shouldSupportCustomConstructor() {
        Car customCar = new Car("Porsche", "911 Carrera", 2024, 114400.00);

        assertThat(customCar.make).isEqualTo("Porsche");
        assertThat(customCar.model).isEqualTo("911 Carrera");
        assertThat(customCar.year).isEqualTo(2024);
        assertThat(customCar.price).isEqualTo(114400.00);
        assertThat(customCar.isRunning).isFalse();
        assertThat(customCar.getFullDescription()).contains("Porsche 911 Carrera");
    }

    @Test
    @DisplayName("Deve executar a demonstração completa da classe OOPCarDemo sem erros")
    void shouldRunOOPCarDemoSuccessfully() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            OOPCarDemo.runDemo();

            String output = outputStream.toString(StandardCharsets.UTF_8);
            assertThat(output)
                    .contains("CAPÍTULO 39: PROGRAMAÇÃO ORIENTADA A OBJETOS")
                    .contains("LEITURA DE ATRIBUTOS VIA OPERADOR PONTO")
                    .contains("INDEPENDÊNCIA DE INSTÂNCIAS NO HEAP");
        } finally {
            System.setOut(originalOut);
        }
    }
}
