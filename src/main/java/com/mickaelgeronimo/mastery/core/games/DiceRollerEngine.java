package com.mickaelgeronimo.mastery.core.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Motor de simulação e geração de arte ASCII para o Dice Roller Program (Capítulo 30).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class DiceRollerEngine {

    private final Random random;

    public DiceRollerEngine() {
        this.random = new Random();
    }

    public DiceRollerEngine(Random random) {
        this.random = random;
    }

    /**
     * Executa a rolagem de N dados de 6 faces, retornando a lista dos resultados.
     *
     * @param numOfDice quantidade de dados
     * @return lista com os resultados de cada dado
     * @throws IllegalArgumentException se numOfDice for menor ou igual a 0
     */
    public List<Integer> rollDice(int numOfDice) {
        if (numOfDice <= 0) {
            throw new IllegalArgumentException("# of dice must be greater than 0");
        }
        List<Integer> rolls = new ArrayList<>(numOfDice);
        for (int i = 0; i < numOfDice; i++) {
            rolls.add(random.nextInt(1, 7)); // Gera números no intervalo [1, 6]
        }
        return Collections.unmodifiableList(rolls);
    }

    /**
     * Calcula o total somado dos dados rolados.
     *
     * @param rolls lista de resultados
     * @return soma de todos os valores
     */
    public static int calculateTotal(List<Integer> rolls) {
        if (rolls == null) {
            return 0;
        }
        int total = 0;
        for (int val : rolls) {
            total += val;
        }
        return total;
    }

    /**
     * Retorna a representação gráfica em arte ASCII da face do dado.
     *
     * @param roll valor da face (1 a 6)
     * @return string formatada em ASCII art
     */
    public static String getDieAscii(int roll) {
        return switch (roll) {
            case 1 -> """
                     ---------
                    |         |
                    |    ●    |
                    |         |
                     ---------
                    """;
            case 2 -> """
                     ---------
                    | ●       |
                    |         |
                    |       ● |
                     ---------
                    """;
            case 3 -> """
                     ---------
                    | ●       |
                    |    ●    |
                    |       ● |
                     ---------
                    """;
            case 4 -> """
                     ---------
                    | ●     ● |
                    |         |
                    | ●     ● |
                     ---------
                    """;
            case 5 -> """
                     ---------
                    | ●     ● |
                    |    ●    |
                    | ●     ● |
                     ---------
                    """;
            case 6 -> """
                     ---------
                    | ●     ● |
                    | ●     ● |
                    | ●     ● |
                     ---------
                    """;
            default -> "Invalid die roll: " + roll;
        };
    }
}
