package com.mickaelgeronimo.mastery.core.oop;

import java.util.Objects;

/**
 * Entidade Veículo para estudo de Array de Objetos (Capítulo 42).
 * Modela as propriedades {@code model} e {@code color} e a ação {@code drive()}.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class GarageVehicle {

    public String model;
    public String color;

    /**
     * Construtor padrão sem argumentos.
     */
    public GarageVehicle() {
        this("Mustang", "Red");
    }

    /**
     * Construtor canônico do Capítulo 42 do Bro Code.
     *
     * @param model modelo do automóvel (ex: "Mustang", "Corvette", "Charger")
     * @param color cor do automóvel (ex: "Red", "Blue", "Yellow", "black")
     */
    public GarageVehicle(String model, String color) {
        this.model = Objects.requireNonNull(model, "Modelo não pode ser nulo.");
        this.color = Objects.requireNonNull(color, "Cor não pode ser nula.");
    }

    /**
     * Retorna a frase canônica de condução do automóvel.
     *
     * @return texto no formato "You drive the <cor> <modelo>"
     */
    public String getDriveMessage() {
        return "You drive the " + color + " " + model;
    }

    /**
     * Executa a condução do veículo exibindo a mensagem no console.
     */
    public void drive() {
        System.out.println(getDriveMessage());
    }
}
