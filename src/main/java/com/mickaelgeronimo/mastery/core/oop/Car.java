package com.mickaelgeronimo.mastery.core.oop;

/**
 * Entidade Carro representativa dos fundamentos de Programação Orientada a Objetos (Capítulo 39).
 * Demonstra atributos de instância, valores padrão de fábrica e métodos de ação sem modificador static.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class Car {

    // Atributos com valores padrão de fábrica (conforme currículo do Bro Code)
    public String make = "Ford";
    public String model = "Mustang";
    public int year = 2025;
    public double price = 58000.99;
    public boolean isRunning = false;

    /**
     * Construtor padrão (default constructor) sem argumentos.
     * Instancia o carro com as configurações padrão da fábrica ("Ford Mustang 2025").
     */
    public Car() {
        // Inicializado com os valores dos campos
    }

    /**
     * Construtor parametrizado opcional para criação flexível de instâncias distintas.
     *
     * @param make  fabricante/marca do veículo
     * @param model modelo do veículo
     * @param year  ano de fabricação
     * @param price preço de tabela
     */
    public Car(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isRunning = false;
    }

    /**
     * Dá partida no motor do veículo.
     * Altera o estado interno de {@code isRunning} para {@code true}.
     */
    public void start() {
        this.isRunning = true;
        System.out.println("You start the engine");
    }

    /**
     * Desliga o motor do veículo.
     * Altera o estado interno de {@code isRunning} para {@code false}.
     */
    public void stop() {
        this.isRunning = false;
        System.out.println("You stop the engine");
    }

    /**
     * Conduz o veículo pelo modelo atual.
     */
    public void drive() {
        System.out.println("You drive the " + model);
    }

    /**
     * Aciona os freios do veículo.
     */
    public void brake() {
        System.out.println("You brake the " + model);
    }

    /**
     * Retorna a descrição sumária do veículo.
     *
     * @return representação textual do veículo
     */
    public String getFullDescription() {
        return year + " " + make + " " + model + " ($" + price + ")";
    }
}
