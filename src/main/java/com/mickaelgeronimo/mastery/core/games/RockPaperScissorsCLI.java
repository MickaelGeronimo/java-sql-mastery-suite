package com.mickaelgeronimo.mastery.core.games;

import java.util.Scanner;

/**
 * Interface CLI para o jogo Pedra, Papel e Tesoura (Capítulo 37).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class RockPaperScissorsCLI {

    private RockPaperScissorsCLI() {
        // Utilitário
    }

    public static void runGame(Scanner scanner, RockPaperScissorsEngine engine) {
        String playAgain = "";

        System.out.println("==========================================================================");
        System.out.println("   ROCK PAPER SCISSORS GAME (BRO CODE EDITION)                            ");
        System.out.println("==========================================================================");

        do {
            System.out.print("Enter your move (rock, paper, scissors): ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String playerChoice = scanner.nextLine().trim().toLowerCase();

            if (!RockPaperScissorsEngine.isValidMove(playerChoice)) {
                System.out.println("Invalid choice\n");
                continue;
            }

            String computerChoice = engine.getRandomComputerChoice();
            System.out.println("Computer choice: " + computerChoice);

            RockPaperScissorsEngine.GameResult result = RockPaperScissorsEngine.evaluateRound(playerChoice, computerChoice);

            switch (result) {
                case TIE -> System.out.println("It's a tie!\n");
                case WIN -> System.out.println("You win!\n");
                case LOSE -> System.out.println("You lose!\n");
                default -> System.out.println("Invalid round\n");
            }

            System.out.print("Play again (yes/no): ");
            if (!scanner.hasNextLine()) {
                break;
            }
            playAgain = scanner.nextLine().trim().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("\nThanks for playing! Goodbye!");
        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RockPaperScissorsEngine engine = new RockPaperScissorsEngine();
        runGame(scanner, engine);
    }
}
