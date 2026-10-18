package com.mickaelgeronimo.mastery.core.arrays;

import java.util.Objects;
import java.util.OptionalInt;

/**
 * Motor utilitário para algoritmos de Busca Linear em arrays (Capítulo 33).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class LinearSearchEngine {

    private LinearSearchEngine() {
        // Utilitário
    }

    /**
     * Executa busca linear sobre array de inteiros.
     *
     * @param numbers array de números
     * @param target  valor a buscar
     * @return índice do primeiro elemento encontrado ou vazio se inexistente
     */
    public static OptionalInt search(int[] numbers, int target) {
        if (numbers == null) {
            return OptionalInt.empty();
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return OptionalInt.of(i); // Interrompe imediatamente com o índice
            }
        }
        return OptionalInt.empty();
    }

    /**
     * Executa busca linear sobre array de Strings comparando via {@code .equals()}.
     *
     * @param items  array de textos
     * @param target termo a buscar (case-sensitive ou com trim)
     * @return índice do primeiro elemento encontrado ou vazio se inexistente
     */
    public static OptionalInt search(String[] items, String target) {
        if (items == null || target == null) {
            return OptionalInt.empty();
        }
        for (int i = 0; i < items.length; i++) {
            if (Objects.equals(items[i], target)) {
                return OptionalInt.of(i); // Interrompe com o índice encontrado
            }
        }
        return OptionalInt.empty();
    }

    /**
     * Busca com insensibilidade a maiúsculas/minúsculas.
     *
     * @param items  array de textos
     * @param target termo a buscar
     * @return índice encontrado ou vazio
     */
    public static OptionalInt searchIgnoreCase(String[] items, String target) {
        if (items == null || target == null) {
            return OptionalInt.empty();
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].equalsIgnoreCase(target)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }
}
