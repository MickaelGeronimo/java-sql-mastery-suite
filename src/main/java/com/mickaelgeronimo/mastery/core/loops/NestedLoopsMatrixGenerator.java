package com.mickaelgeronimo.mastery.core.loops;

import java.util.Scanner;

/**
 * NestedLoopsMatrixGenerator — Capítulo 25: Gerador de Matriz Dinâmico
 * 
 * Funcionalidades:
 * 1. Métodos utilitários desacoplados para criação de matriz bidimensional char[][]
 *    e formatação textual com quebra de linha.
 * 2. Método main executável interativo utilizando java.util.Scanner com o padrão
 *    scanner.next().charAt(0) para captura de caracteres únicos.
 */
public class NestedLoopsMatrixGenerator {

    /**
     * Constrói uma matriz bidimensional de caracteres preenchida com o símbolo fornecido.
     *
     * @param rows    Número de linhas da matriz (> 0)
     * @param columns Número de colunas da matriz (> 0)
     * @param symbol  Caractere preenchedor
     * @return Grade char[][] com as dimensões especificadas
     */
    public static char[][] createMatrix(int rows, int columns, char symbol) {
        if (rows <= 0 || columns <= 0) {
            return new char[0][0];
        }

        char[][] matrix = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = symbol;
            }
        }
        return matrix;
    }

    /**
     * Formata uma matriz bidimensional de caracteres em uma representação textual
     * com quebras de linha entre as linhas da matriz.
     *
     * @param matrix Grade bidimensional de caracteres
     * @return String formatada linha por linha
     */
    public static String formatMatrix(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows;
        int columns;
        char symbol;

        System.out.println("==========================================================================");
        System.out.println("   GERADOR DE MATRIZ DINÂMICO COM LAÇOS ANINHADOS                         ");
        System.out.println("==========================================================================\n");

        System.out.print("Enter the # of rows: ");
        rows = scanner.nextInt();

        System.out.print("Enter the # of columns: ");
        columns = scanner.nextInt();

        System.out.print("Enter the symbol to use: ");
        symbol = scanner.next().charAt(0);

        System.out.println("\nMatriz Gerada:\n");
        char[][] matrix = createMatrix(rows, columns, symbol);
        System.out.print(formatMatrix(matrix));

        scanner.close();
        System.out.println("\n==========================================================================");
    }
}
