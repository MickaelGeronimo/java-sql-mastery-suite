package com.mickaelgeronimo.mastery.core.arrays;

import java.util.OptionalInt;
import java.util.Scanner;

/**
 * Interface CLI para demonstração ao vivo da Busca Linear (Capítulo 33).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class LinearSearchCLI {

    private LinearSearchCLI() {
        // Utilitário
    }

    public static void runDemo(Scanner scanner) {
        String[] fruits = {"apple", "orange", "banana", "coconut"};

        System.out.println("Itens disponíveis no catálogo:");
        for (int i = 0; i < fruits.length; i++) {
            System.out.printf("[%d] %s%n", i, fruits[i]);
        }

        System.out.print("\nEnter a fruit to search for: ");
        if (!scanner.hasNextLine()) {
            return;
        }

        String target = scanner.nextLine().trim();
        OptionalInt result = LinearSearchEngine.search(fruits, target);

        if (result.isPresent()) {
            System.out.printf("Element found at index: %d%n", result.getAsInt());
        } else {
            System.out.println("Element not found in the array");
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 33: BUSCA LINEAR EM ARRAYS (SEARCH ARRAY SUITE)               ");
        System.out.println("==========================================================================");

        Scanner scanner = new Scanner(System.in);
        runDemo(scanner);

        System.out.println("==========================================================================");
    }
}
