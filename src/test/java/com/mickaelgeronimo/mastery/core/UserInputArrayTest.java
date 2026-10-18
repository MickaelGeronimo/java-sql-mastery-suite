package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.arrays.UserInputArrayCLI;
import com.mickaelgeronimo.mastery.core.arrays.UserInputArrayCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 32: Testes Automatizados de Entrada do Usuário em Arrays")
class UserInputArrayTest {

    @Test
    @DisplayName("Deve coletar elementos do Scanner e preencher o array com tamanho dinâmico")
    void shouldCollectElementsIntoDynamicallyAllocatedArray() {
        String inputData = "pizza\ntaco\nhamburger\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8)));

        String[] result = UserInputArrayCollector.collectStrings(mockScanner, 3);

        assertThat(result)
                .hasSize(3)
                .containsExactly("pizza", "taco", "hamburger");
    }

    @Test
    @DisplayName("Deve rejeitar tamanho negativo ou zero com IllegalArgumentException")
    void shouldRejectInvalidArraySize() {
        Scanner mockScanner = new Scanner("test\n");

        assertThatThrownBy(() -> UserInputArrayCollector.collectStrings(mockScanner, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> UserInputArrayCollector.collectStrings(mockScanner, -4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Deve validar limites de índices de arrays corretamente")
    void shouldValidateIndexBoundsCorrectly() {
        int capacity = 5; // Índices válidos: 0, 1, 2, 3, 4

        assertThat(UserInputArrayCollector.isIndexValid(0, capacity)).isTrue();
        assertThat(UserInputArrayCollector.isIndexValid(4, capacity)).isTrue();
        assertThat(UserInputArrayCollector.isIndexValid(5, capacity)).isFalse();
        assertThat(UserInputArrayCollector.isIndexValid(-1, capacity)).isFalse();
    }

    @Test
    @DisplayName("Deve executar fluxo completo da CLI com tamanho e limpeza de buffer sem pular perguntas")
    void shouldExecuteCLIWithBufferClearing() {
        // Entrada: size = 3, seguido das 3 comidas
        String inputData = "3\npizza\nhotdog\nsushi\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8)));

        UserInputArrayCLI.run(mockScanner);
    }

    @Test
    @DisplayName("Deve lidar com tamanho inválido na CLI sem lançar exceções")
    void shouldHandleNegativeSizeInCLI() {
        String inputData = "-2\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8)));

        UserInputArrayCLI.run(mockScanner);
    }
}
