package com.mickaelgeronimo.mastery.core.games;

import java.util.Scanner;

/**
 * Interface CLI interativa para o jogo Java Slot Machine (Capítulo 38).
 * Gerencia o loop de apostas, limpeza de buffer do Scanner e renderização de status.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class SlotMachineCLI {

    private SlotMachineCLI() {
        // Construtor privado para classe utilitária
    }

    /**
     * Executa a sessão interativa da máquina caça-níqueis.
     *
     * @param scanner        instância de Scanner conectada à entrada padrão ou simulada
     * @param engine         motor de regras da Slot Machine
     * @param initialBalance saldo inicial do jogador em dólares
     * @return saldo final ao término da sessão
     */
    public static int runGame(Scanner scanner, SlotMachineEngine engine, int initialBalance) {
        int balance = initialBalance;
        int bet;
        int payout;
        String[] row;
        String playAgain;

        // DISPLAY WELCOME MESSAGE
        System.out.println("*************************");
        System.out.println("  Welcome to Java Slots  ");
        System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐ ");
        System.out.println("*************************");

        // PLAY IF BALANCE > 0
        while (balance > 0) {
            System.out.println("Current balance: $" + balance);
            System.out.print("Place your bet amount: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            try {
                bet = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid bet amount");
                continue;
            }

            // VERIFY IF BET > BALANCE
            if (bet > balance) {
                System.out.println("INSUFFICIENT FUNDS");
                continue;
            }
            // VERIFY IF BET <= 0
            else if (bet <= 0) {
                System.out.println("Bet must be greater than 0");
                continue;
            }
            // SUBTRACT BET FROM BALANCE
            else {
                balance -= bet;
            }

            System.out.println("Spinning...");

            // SPIN ROW
            row = engine.spinRow();

            // PRINT ROW
            SlotMachineEngine.printRow(row);

            // GET PAYOUT
            payout = SlotMachineEngine.getPayout(row, bet);

            if (payout > 0) {
                System.out.println("You won $" + payout);
                balance += payout;
            } else {
                System.out.println("Sorry you lost this round");
            }

            if (balance <= 0) {
                System.out.println("You have run out of money!");
                break;
            }

            // ASK TO PLAY AGAIN
            System.out.print("Do you want to play again? (Y/N): ");
            if (!scanner.hasNextLine()) {
                break;
            }

            playAgain = scanner.nextLine().trim().toUpperCase();

            if (!playAgain.equals("Y")) {
                break;
            }
        }

        // DISPLAY EXIT MESSAGE
        System.out.println("GAME OVER! Your final balance is $" + balance);
        return balance;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            SlotMachineEngine engine = new SlotMachineEngine();
            runGame(scanner, engine, 100);
        }
    }
}
