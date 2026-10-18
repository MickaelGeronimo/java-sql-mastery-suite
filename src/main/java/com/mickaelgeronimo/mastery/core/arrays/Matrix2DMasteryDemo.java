package com.mickaelgeronimo.mastery.core.arrays;

import java.util.Objects;

/**
 * Utilitário para matrizes bidimensionais (2D Arrays) (Capítulo 35).
 * <p>
 * Demonstra:
 * <ul>
 *   <li>Criação e formatação de matrizes de Strings e de caracteres</li>
 *   <li>Navegação por laços aninhados (nested for-each e indexed)</li>
 *   <li>Modelo de teclado telefônico 4x3</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class Matrix2DMasteryDemo {

    private Matrix2DMasteryDemo() {
        // Utilitário
    }

    /**
     * Retorna a matriz clássica do teclado numérico de telefone 4x3.
     *
     * @return matriz char[][] com os botões 1-9, *, 0, #
     */
    public static char[][] getTelephoneKeypad() {
        return new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'},
                {'*', '0', '#'}
        };
    }

    /**
     * Formata uma matriz 2D em representação de grade visual com quebras de linha (\n).
     *
     * @param matrix matriz de entrada
     * @return texto formatado em grade
     */
    public static String formatGrid(String[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String[] row : matrix) {
            if (row != null) {
                for (String item : row) {
                    sb.append(item).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Formata uma matriz de char em grade com separador de espaços e linhas.
     *
     * @param keypad matriz de caracteres
     * @return representação textual
     */
    public static String formatKeypad(char[][] keypad) {
        Objects.requireNonNull(keypad, "Teclado não pode ser nulo.");
        StringBuilder sb = new StringBuilder();
        for (char[] row : keypad) {
            for (char key : row) {
                sb.append(key).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Execução demonstrativa em terminal.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 35: MATRIZES E ARRAYS BIDIMENSIONAIS (2D ARRAYS SUITE)        ");
        System.out.println("==========================================================================");

        String[] fruits = {"apple", "orange", "banana"};
        String[] vegetables = {"potato", "onion", "carrot"};
        String[] meats = {"chicken", "pork", "beef", "fish"};

        String[][] groceries = {fruits, vegetables, meats};
        System.out.println("--- LISTA DE SUPERMERCADO EM GRADE ---");
        System.out.print(formatGrid(groceries));

        System.out.println("--- TECLADO NUMÉRICO 4x3 ---");
        char[][] telephone = getTelephoneKeypad();
        System.out.print(formatKeypad(telephone));

        System.out.println("==========================================================================");
    }
}
