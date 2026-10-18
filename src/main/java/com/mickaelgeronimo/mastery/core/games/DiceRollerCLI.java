package com.mickaelgeronimo.mastery.core.games;

import java.util.List;
import java.util.Scanner;

/**
 * Interface de Linha de Comando (CLI) para o simulador de dados (Capítulo 30).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class DiceRollerCLI {

    private DiceRollerCLI() {
        // Utilitário
    }

    public static void printDie(int roll) {
        System.out.print(DiceRollerEngine.getDieAscii(roll));
    }

    public static void runSimulation(Scanner scanner, DiceRollerEngine engine) {
        System.out.print("Enter the # of dice to roll: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Digite um número inteiro.");
            return;
        }

        int numOfDice = scanner.nextInt();

        if (numOfDice > 0) {
            System.out.printf("Rolling %d dice...%n%n", numOfDice);
            List<Integer> rolls = engine.rollDice(numOfDice);

            for (int roll : rolls) {
                printDie(roll);
                System.out.printf("You rolled: %d%n%n", roll);
            }

            int total = DiceRollerEngine.calculateTotal(rolls);
            System.out.printf("Total: %d%n", total);
        } else {
            System.out.println("# of dice must be greater than 0");
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 30: DICE ROLLER PROGRAM (SIMULADOR DE DADOS ASCII)            ");
        System.out.println("==========================================================================");

        Scanner scanner = new Scanner(System.in);
        DiceRollerEngine engine = new DiceRollerEngine();
        runSimulation(scanner, engine);

        System.out.println("==========================================================================");
    }
}
