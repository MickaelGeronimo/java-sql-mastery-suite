package com.mickaelgeronimo.mastery.core.oop;

/**
 * Demonstração executável dos conceitos de Classes e Objetos em Java (Capítulo 39).
 * Simula a sessão do Bro Code: instanciação, operador ponto, métodos de ação e independência de instâncias.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class OOPCarDemo {

    private OOPCarDemo() {
        // Classe utilitária
    }

    public static void runDemo() {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 39: PROGRAMAÇÃO ORIENTADA A OBJETOS (CAR OOP DEMO)            ");
        System.out.println("==========================================================================");

        // 1. Instanciação de Objetos Clones (Padrão de Fábrica)
        Car car1 = new Car();
        Car car2 = new Car();

        System.out.println("\n--- 1. LEITURA DE ATRIBUTOS VIA OPERADOR PONTO (.) ---");
        System.out.println("Car 1: " + car1.make + " " + car1.model + " (" + car1.year + ") - $" + car1.price);
        System.out.println("Car 2: " + car2.make + " " + car2.model + " (" + car2.year + ") - $" + car2.price);

        // 2. Ciclo de Vida do Motor e Alternância de Estado (isRunning)
        System.out.println("\n--- 2. TRANSIÇÃO DE ESTADO DO MOTOR (isRunning) ---");
        System.out.println("Status inicial do motor: " + car1.isRunning);
        car1.start();
        System.out.println("Status após start(): " + car1.isRunning);
        car1.stop();
        System.out.println("Status após stop(): " + car1.isRunning);

        // 3. Ações Personalizadas do Veículo
        System.out.println("\n--- 3. AÇÕES PERSONALIZADAS (drive e brake) ---");
        car1.drive();
        car1.brake();

        // 4. Independência de Instâncias na Memória Heap
        System.out.println("\n--- 4. INDEPENDÊNCIA DE INSTÂNCIAS NO HEAP ---");
        System.out.println("Modificando car1 para Corvette 2026...");
        car1.make = "Chevrolet";
        car1.model = "Corvette";
        car1.year = 2026;
        car1.price = 68300.00;

        System.out.println("Car 1 modificado: " + car1.getFullDescription());
        System.out.println("Car 2 inalterado: " + car2.getFullDescription());

        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
