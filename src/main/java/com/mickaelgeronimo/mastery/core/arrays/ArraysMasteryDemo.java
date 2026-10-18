package com.mickaelgeronimo.mastery.core.arrays;

import java.util.Arrays;
import java.util.Objects;

/**
 * Utilitário de demonstração e operações com Arrays em Java (Capítulo 31).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class ArraysMasteryDemo {

    private ArraysMasteryDemo() {
        // Utilitário
    }

    /**
     * Retorna uma cópia ordenada de um array de Strings.
     *
     * @param input array de origem
     * @return novo array ordenado alfabeticamente
     */
    public static String[] sortCopy(String[] input) {
        Objects.requireNonNull(input, "Array de entrada não pode ser nulo.");
        String[] copy = Arrays.copyOf(input, input.length);
        Arrays.sort(copy);
        return copy;
    }

    /**
     * Retorna um array inicializado com um valor padrão em todas as posições.
     *
     * @param size  tamanho do array
     * @param value valor para preencher
     * @return novo array preenchido
     */
    public static String[] createFilledArray(int size, String value) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho do array não pode ser negativo.");
        }
        String[] arr = new String[size];
        Arrays.fill(arr, value);
        return arr;
    }

    /**
     * Concatena os elementos com separador limpo usando laço enhanced for-each.
     *
     * @param items array de itens
     * @return texto formatado
     */
    public static String joinWithForEach(String[] items) {
        if (items == null || items.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * Execução demonstrativa em linha de comando.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 31: FUNDAMENTOS DE ARRAYS (ARRAYS MASTERY SUITE)              ");
        System.out.println("==========================================================================");

        String[] fruits = {"apple", "orange", "banana", "coconut"};
        System.out.println("1. Tamanho do array (fruits.length): " + fruits.length);
        System.out.println("2. Primeiro elemento [0]: " + fruits[0]);
        System.out.println("3. Último elemento [length - 1]: " + fruits[fruits.length - 1]);

        System.out.println("\n--- ITERAÇÃO COM FOR-EACH ---");
        for (String fruit : fruits) {
            System.out.println("Fruta: " + fruit);
        }

        System.out.println("\n--- ORDENAÇÃO (Arrays.sort) ---");
        String[] sorted = sortCopy(fruits);
        System.out.println("Ordenado: " + Arrays.toString(sorted));

        System.out.println("\n--- PREENCHIMENTO (Arrays.fill) ---");
        String[] filled = createFilledArray(4, "pineapple");
        System.out.println("Preenchido: " + Arrays.toString(filled));

        System.out.println("==========================================================================");
    }
}
