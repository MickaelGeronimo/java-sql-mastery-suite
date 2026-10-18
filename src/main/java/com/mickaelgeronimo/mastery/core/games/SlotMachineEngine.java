package com.mickaelgeronimo.mastery.core.games;

import java.util.Objects;
import java.util.Random;

/**
 * Motor de regras, sorteio e pagamentos para o jogo Java Slot Machine (Capítulo 38).
 * Implementa lógica fiel ao currículo do Bro Code com suporte a Enhanced Switch Expressions.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class SlotMachineEngine {

    public static final String[] SYMBOLS = {"🍒", "🍉", "🍋", "🔔", "⭐"};
    public static final String BORDER = "*************************";

    private final Random random;

    public record SpinResult(
            String[] row,
            int bet,
            int payout,
            boolean isWin,
            boolean isThreeOfAKind,
            boolean isPair
    ) {}

    public SlotMachineEngine() {
        this.random = new Random();
    }

    public SlotMachineEngine(Random random) {
        this.random = Objects.requireNonNull(random, "Gerador Random não pode ser nulo.");
    }

    /**
     * Valida se uma aposta é estritamente válida para o saldo atual.
     *
     * @param bet     valor da aposta pretendida
     * @param balance saldo disponível do jogador
     * @return true se bet > 0 e bet <= balance
     */
    public static boolean isValidBet(int bet, int balance) {
        return bet > 0 && bet <= balance;
    }

    /**
     * Sorteia uma linha com 3 símbolos pseudoaleatórios a partir da cartela oficial.
     *
     * @return array de 3 posições contendo os símbolos sorteados
     */
    public String[] spinRow() {
        String[] row = new String[3];
        for (int i = 0; i < 3; i++) {
            row[i] = SYMBOLS[random.nextInt(SYMBOLS.length)];
        }
        return row;
    }

    /**
     * Formata visualmente a linha com a moldura de 25 asteriscos conforme especificação visual.
     *
     * @param row array de 3 símbolos
     * @return representação formatada com moldura superior e inferior
     */
    public static String formatRow(String[] row) {
        Objects.requireNonNull(row, "Linha de símbolos não pode ser nula.");
        return BORDER + System.lineSeparator() +
                " " + String.join(" | ", row) + System.lineSeparator() +
                BORDER;
    }

    /**
     * Imprime a linha formatada no console padrão.
     *
     * @param row array de 3 símbolos
     */
    public static void printRow(String[] row) {
        System.out.println(formatRow(row));
    }

    /**
     * Calcula o prêmio a ser pago ao jogador com base nos símbolos sorteados e no valor apostado.
     * <p>
     * Regras Oficiais:
     * <ul>
     *     <li>Trinca (3 símbolos iguais): 🍒=3x, 🍉=4x, 🍋=5x, 🔔=10x, ⭐=20x</li>
     *     <li>Par (2 símbolos adjacentes iguais): 🍒=2x, 🍉=3x, 🍋=4x, 🔔=5x, ⭐=10x</li>
     *     <li>Sem correspondência: $0</li>
     * </ul>
     *
     * @param row array com os 3 símbolos sorteados
     * @param bet valor em dólares apostado na rodada
     * @return valor total pago em dólares ($)
     */
    public static int getPayout(String[] row, int bet) {
        if (row == null || row.length < 3 || bet <= 0) {
            return 0;
        }

        // Checagem de Trinca (3 símbolos iguais - transitividade)
        if (row[0].equals(row[1]) && row[1].equals(row[2])) {
            return switch (row[0]) {
                case "🍒" -> bet * 3;
                case "🍉" -> bet * 4;
                case "🍋" -> bet * 5;
                case "🔔" -> bet * 10;
                case "⭐" -> bet * 20;
                default -> 0;
            };
        }
        // Checagem de Par Adjacente (posições [0]==[1] ou [1]==[2])
        else if (row[0].equals(row[1]) || row[1].equals(row[2])) {
            return switch (row[1]) {
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;
            };
        }

        return 0;
    }

    /**
     * Avalia integralmente uma rodada gerando um relatório tipado SpinResult.
     *
     * @param row array de símbolos
     * @param bet aposta realizada
     * @return resultado detalhado da rodada
     */
    public static SpinResult evaluateSpin(String[] row, int bet) {
        int payout = getPayout(row, bet);
        boolean isThree = row != null && row.length >= 3 && row[0].equals(row[1]) && row[1].equals(row[2]);
        boolean isPair = !isThree && row != null && row.length >= 3 && (row[0].equals(row[1]) || row[1].equals(row[2]));
        return new SpinResult(row, bet, payout, payout > 0, isThree, isPair);
    }
}
