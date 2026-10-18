package com.mickaelgeronimo.mastery.core.games;

import java.util.Objects;
import java.util.Random;

/**
 * Motor de lógica e regras para o jogo Pedra, Papel e Tesoura (Capítulo 37).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class RockPaperScissorsEngine {

    public enum GameResult {
        WIN,
        LOSE,
        TIE,
        INVALID
    }

    public static final String[] CHOICES = {"rock", "paper", "scissors"};

    private final Random random;

    public RockPaperScissorsEngine() {
        this.random = new Random();
    }

    public RockPaperScissorsEngine(Random random) {
        this.random = Objects.requireNonNull(random, "Gerador Random não pode ser nulo.");
    }

    /**
     * Valida se uma jogada é aceita pelas regras oficiais.
     *
     * @param move jogada do jogador
     * @return true se for rock, paper ou scissors
     */
    public static boolean isValidMove(String move) {
        if (move == null) {
            return false;
        }
        String cleanMove = move.trim().toLowerCase();
        return cleanMove.equals("rock") || cleanMove.equals("paper") || cleanMove.equals("scissors");
    }

    /**
     * Sorteia aleatoriamente a jogada do computador.
     *
     * @return "rock", "paper" ou "scissors"
     */
    public String getRandomComputerChoice() {
        int index = random.nextInt(CHOICES.length);
        return CHOICES[index];
    }

    /**
     * Avalia o resultado da rodada a partir da jogada do usuário e da máquina.
     *
     * @param playerChoice   jogada do jogador
     * @param computerChoice jogada do computador
     * @return {@link GameResult} correspondente
     */
    public static GameResult evaluateRound(String playerChoice, String computerChoice) {
        if (!isValidMove(playerChoice) || !isValidMove(computerChoice)) {
            return GameResult.INVALID;
        }

        String player = playerChoice.trim().toLowerCase();
        String computer = computerChoice.trim().toLowerCase();

        if (player.equals(computer)) {
            return GameResult.TIE;
        }

        if ((player.equals("rock") && computer.equals("scissors"))
                || (player.equals("paper") && computer.equals("rock"))
                || (player.equals("scissors") && computer.equals("paper"))) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
