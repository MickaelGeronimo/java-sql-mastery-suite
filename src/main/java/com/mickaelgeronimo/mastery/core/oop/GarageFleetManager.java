package com.mickaelgeronimo.mastery.core.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utilitário de gerenciamento de frotas e arrays de objetos (Capítulo 42).
 * Contém operações de processamento em lote, pintura de frotas e iterações.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class GarageFleetManager {

    private GarageFleetManager() {
        // Classe utilitária
    }

    /**
     * Cria a frota canônica inicial com objetos anônimos conforme o currículo do Bro Code.
     *
     * @return array contendo Mustang Red, Corvette Blue e Charger Yellow
     */
    public static GarageVehicle[] createCanonicalFleet() {
        return new GarageVehicle[]{
                new GarageVehicle("Mustang", "Red"),
                new GarageVehicle("Corvette", "Blue"),
                new GarageVehicle("Charger", "Yellow")
        };
    }

    /**
     * Modifica a cor de todos os veículos da frota em lote via laço for-each.
     *
     * @param fleet    array de veículos
     * @param newColor nova cor a ser aplicada em cada veículo
     */
    public static void repaintFleet(GarageVehicle[] fleet, String newColor) {
        if (fleet == null) {
            return;
        }
        Objects.requireNonNull(newColor, "Nova cor não pode ser nula.");
        for (GarageVehicle vehicle : fleet) {
            if (vehicle != null) {
                vehicle.color = newColor;
            }
        }
    }

    /**
     * Executa o método drive() de todos os veículos presentes no array.
     *
     * @param fleet array de veículos
     */
    public static void driveAll(GarageVehicle[] fleet) {
        if (fleet == null) {
            return;
        }
        for (GarageVehicle vehicle : fleet) {
            if (vehicle != null) {
                vehicle.drive();
            }
        }
    }

    /**
     * Coleta todas as mensagens de condução da frota em uma lista para testes e auditoria.
     *
     * @param fleet array de veículos
     * @return lista de mensagens de condução
     */
    public static List<String> collectDriveMessages(GarageVehicle[] fleet) {
        List<String> messages = new ArrayList<>();
        if (fleet == null) {
            return messages;
        }
        for (GarageVehicle vehicle : fleet) {
            if (vehicle != null) {
                messages.add(vehicle.getDriveMessage());
            }
        }
        return messages;
    }
}
