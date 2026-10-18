package com.mickaelgeronimo.mastery.core.arrays;

import java.util.Scanner;

/**
 * Interface CLI para cadastro de comidas em array dinâmico (Capítulo 32).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class UserInputArrayCLI {

    private UserInputArrayCLI() {
        // Utilitário
    }

    public static void run(Scanner scanner) {
        System.out.print("What # of food do you want?: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Digite um número inteiro.");
            return;
        }

        int size = scanner.nextInt();
        scanner.nextLine(); // 👈 LIMPEZA FUNDAMENTAL DO BUFFER DO SCANNER!

        if (size <= 0) {
            System.out.println("Tamanho inválido! O número deve ser maior que 0.");
            return;
        }

        String[] foods = new String[size];

        for (int i = 0; i < foods.length; i++) {
            System.out.print("Enter a food: ");
            foods[i] = scanner.nextLine();
        }

        System.out.println("\n--- ITENS CADASTRADOS (for-each) ---");
        for (String food : foods) {
            System.out.println("Comida: " + food);
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 32: ENTRADA DO USUÁRIO EM ARRAYS (USER INPUT ARRAY SUITE)     ");
        System.out.println("==========================================================================");

        Scanner scanner = new Scanner(System.in);
        run(scanner);

        System.out.println("==========================================================================");
    }
}
