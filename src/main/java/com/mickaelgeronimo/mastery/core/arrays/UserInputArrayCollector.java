package com.mickaelgeronimo.mastery.core.arrays;

import java.util.Objects;
import java.util.Scanner;

/**
 * Coletor e gerenciador de arrays com entrada de dados dinâmica (Capítulo 32).
 * <p>
 * Demonstra:
 * <ul>
 *   <li>Alocação prévia de capacidade fixa via {@code new String[size]}</li>
 *   <li>Proteção e sanitização do buffer do Scanner com {@code nextLine()}</li>
 *   <li>Preenchimento iterativo com tratamento de entradas em branco</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class UserInputArrayCollector {

    /**
     * Aloca e preenche um array de Strings a partir de um Scanner interativo.
     *
     * @param scanner leitor de entrada
     * @param size    quantidade de elementos desejada
     * @return array preenchido com as entradas coletadas
     * @throws IllegalArgumentException se o tamanho for menor ou igual a zero
     */
    public static String[] collectStrings(Scanner scanner, int size) {
        Objects.requireNonNull(scanner, "Scanner não pode ser nulo.");
        if (size <= 0) {
            throw new IllegalArgumentException("O tamanho do array deve ser maior que 0.");
        }

        String[] items = new String[size];
        for (int i = 0; i < items.length; i++) {
            System.out.printf("Enter item #%d: ", i + 1);
            items[i] = scanner.nextLine().trim();
        }
        return items;
    }

    /**
     * Valida se um determinado índice está dentro dos limites de um array de tamanho capacity.
     *
     * @param index    índice a testar
     * @param capacity capacidade do array
     * @return true se válido, false se causaria ArrayIndexOutOfBoundsException
     */
    public static boolean isIndexValid(int index, int capacity) {
        return index >= 0 && index < capacity;
    }
}
