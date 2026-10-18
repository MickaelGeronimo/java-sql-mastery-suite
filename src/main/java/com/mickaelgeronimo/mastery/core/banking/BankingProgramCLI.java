package com.mickaelgeronimo.mastery.core.banking;

import java.util.Scanner;

/**
 * Interface de Linha de Comando (CLI) para o Banking Program (Capítulo 29).
 * <p>
 * Demonstra:
 * <ul>
 *   <li>Uso de {@code static Scanner} em escopo de classe</li>
 *   <li>Event loop interativo com {@code while (isRunning)}</li>
 *   <li>Controle de fluxo moderno com switch expression</li>
 *   <li>Chamadas de métodos para exibição, depósito e saque</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class BankingProgramCLI {

    // Scanner em escopo de classe conforme a aula do Capítulo 29
    private static Scanner scanner = new Scanner(System.in);

    private BankingProgramCLI() {
        // Utilitário não instanciável
    }

    public static void setScanner(Scanner customScanner) {
        scanner = customScanner;
    }

    public static void showBalance(double balance) {
        System.out.println("****************");
        System.out.printf("$%.2f%n", balance);
        System.out.println("****************\n");
    }

    public static double deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount! Deposit must be greater than zero.\n");
            return 0.0;
        }
        return amount;
    }

    public static double withdraw(double balance) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount! Amount must be greater than zero.\n");
            return 0.0;
        }
        if (amount > balance) {
            System.out.println("INSUFFICIENT FUNDS! You don't have enough balance.\n");
            return 0.0;
        }
        return amount;
    }

    /**
     * Executa a CLI do caixa eletrônico com o fluxo completo.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        double balance = 0.0;
        boolean isRunning = true;

        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 29: BANKING PROGRAM (CAIXA ELETRÔNICO / ATM)                  ");
        System.out.println("==========================================================================");

        while (isRunning) {
            System.out.println("***************");
            System.out.println("BANKING PROGRAM");
            System.out.println("***************");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("***************");
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida! Encerrando simulação.");
                break;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showBalance(balance);
                case 2 -> {
                    balance += deposit();
                    showBalance(balance);
                }
                case 3 -> {
                    balance -= withdraw(balance);
                    showBalance(balance);
                }
                case 4 -> {
                    isRunning = false;
                    System.out.println("\nThank you for banking with us! Have a nice day!\n");
                }
                default -> System.out.println("INVALID CHOICE! Please select 1, 2, 3 or 4.\n");
            }
        }

        System.out.println("==========================================================================");
    }
}
