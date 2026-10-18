package com.mickaelgeronimo.mastery.core.oop;

/**
 * Demonstração executável dos conceitos de Array de Objetos (Capítulo 42).
 * Simula a evolução do código do Bro Code:
 * 1. Instanciação com objetos anônimos no array
 * 2. Condução da frota original com for-each
 * 3. Repintura de todos os veículos para "black" em lote
 * 4. Condução da frota repintada
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class ArrayOfObjectsDemo {

    private ArrayOfObjectsDemo() {
        // Classe utilitária
    }

    public static void runDemo() {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 42: ARRAY DE OBJETOS (ARRAY OF OBJECTS DEMO)                  ");
        System.out.println("==========================================================================");

        // 1. Array inicializado diretamente com objetos anônimos (Sintaxe compacta do Bro Code)
        GarageVehicle[] cars = {
                new GarageVehicle("Mustang", "Red"),
                new GarageVehicle("Corvette", "Blue"),
                new GarageVehicle("Charger", "Yellow")
        };

        System.out.println("\n--- 1. CONDUÇÃO DA FROTA ORIGINAL COM FOR-EACH ---");
        for (GarageVehicle car : cars) {
            car.drive();
        }

        System.out.println("\n--- 2. MODIFICAÇÃO EM LOTE: PINTANDO TODA A FROTA DE PRETO ---");
        for (GarageVehicle car : cars) {
            car.color = "black";
        }

        System.out.println("\n--- 3. CONDUÇÃO DA FROTA REPINTADA ---");
        for (GarageVehicle car : cars) {
            car.drive();
        }

        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
