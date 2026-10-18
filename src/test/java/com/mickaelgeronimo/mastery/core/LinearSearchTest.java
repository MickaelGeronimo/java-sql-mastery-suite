package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.arrays.LinearSearchCLI;
import com.mickaelgeronimo.mastery.core.arrays.LinearSearchEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.OptionalInt;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 33: Testes Automatizados de Busca Linear em Arrays")
class LinearSearchTest {

    @Test
    @DisplayName("Deve localizar elementos em array de inteiros e retornar o índice correto")
    void shouldFindElementInIntegerArray() {
        int[] numbers = {1, 9, 2, 8, 3, 5, 4};

        OptionalInt result2 = LinearSearchEngine.search(numbers, 2);
        OptionalInt result4 = LinearSearchEngine.search(numbers, 4);

        assertThat(result2).hasValue(2);
        assertThat(result4).hasValue(6);
    }

    @Test
    @DisplayName("Deve retornar vazio quando número não existir no array")
    void shouldReturnEmptyWhenIntegerNotFound() {
        int[] numbers = {1, 9, 2, 8, 3, 5, 4};

        OptionalInt notFound = LinearSearchEngine.search(numbers, 7);
        assertThat(notFound).isEmpty();
    }

    @Test
    @DisplayName("Deve localizar textos via .equals() com índice exato")
    void shouldFindStringInArrayWithEquals() {
        String[] fruits = {"apple", "orange", "banana"};

        OptionalInt orangeIndex = LinearSearchEngine.search(fruits, "orange");
        OptionalInt appleIndex = LinearSearchEngine.search(fruits, "apple");

        assertThat(orangeIndex).hasValue(1);
        assertThat(appleIndex).hasValue(0);
    }

    @Test
    @DisplayName("Deve retornar vazio quando texto não existir no array (pineapple)")
    void shouldReturnEmptyWhenStringNotFound() {
        String[] fruits = {"apple", "orange", "banana"};

        OptionalInt notFound = LinearSearchEngine.search(fruits, "pineapple");
        assertThat(notFound).isEmpty();
    }

    @Test
    @DisplayName("Deve realizar busca ignorando maiúsculas e minúsculas via searchIgnoreCase")
    void shouldSearchCaseInsensitive() {
        String[] fruits = {"apple", "Orange", "banana"};

        OptionalInt found = LinearSearchEngine.searchIgnoreCase(fruits, "ORANGE");
        assertThat(found).hasValue(1);
    }

    @Test
    @DisplayName("Deve lidar com arrays ou alvos nulos sem quebrar")
    void shouldHandleNullArraysGracefully() {
        assertThat(LinearSearchEngine.search((int[]) null, 10)).isEmpty();
        assertThat(LinearSearchEngine.search((String[]) null, "apple")).isEmpty();
        assertThat(LinearSearchEngine.search(new String[]{"a", "b"}, null)).isEmpty();
    }

    @Test
    @DisplayName("Deve simular CLI de busca com sucesso e sem sucesso sem lançar exceções")
    void shouldSimulateCLIWorkflows() {
        // Teste 1: Buscar elemento existente "banana"
        String inputSuccess = "banana\n";
        Scanner scannerSuccess = new Scanner(new ByteArrayInputStream(inputSuccess.getBytes(StandardCharsets.UTF_8)));
        LinearSearchCLI.runDemo(scannerSuccess);

        // Teste 2: Buscar elemento inexistente "pineapple"
        String inputFail = "pineapple\n";
        Scanner scannerFail = new Scanner(new ByteArrayInputStream(inputFail.getBytes(StandardCharsets.UTF_8)));
        LinearSearchCLI.runDemo(scannerFail);
    }
}
