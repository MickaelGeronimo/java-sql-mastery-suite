package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.games.DiceRollerCLI;
import com.mickaelgeronimo.mastery.core.games.DiceRollerEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 30: Testes Automatizados do Dice Roller Program (ASCII Dice)")
class DiceRollerTest {

    @Test
    @DisplayName("Deve rolar quantidade exata de dados e todos no intervalo [1, 6]")
    void shouldRollDiceWithinValidRange() {
        DiceRollerEngine engine = new DiceRollerEngine();
        int count = 100;
        List<Integer> rolls = engine.rollDice(count);

        assertThat(rolls).hasSize(count);
        for (int roll : rolls) {
            assertThat(roll).isBetween(1, 6);
        }
    }

    @Test
    @DisplayName("Deve rejeitar quantidade de dados menor ou igual a zero")
    void shouldRejectZeroOrNegativeDiceCount() {
        DiceRollerEngine engine = new DiceRollerEngine();
        assertThatThrownBy(() -> engine.rollDice(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> engine.rollDice(-5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Deve calcular a soma total corretamente")
    void shouldCalculateTotalCorrectly() {
        List<Integer> mockRolls = List.of(3, 5, 2, 6);
        int total = DiceRollerEngine.calculateTotal(mockRolls);
        assertThat(total).isEqualTo(16);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @DisplayName("Deve renderizar arte ASCII válida para todas as 6 faces do dado")
    void shouldRenderValidAsciiArtForFaces(int face) {
        String ascii = DiceRollerEngine.getDieAscii(face);
        assertThat(ascii)
                .contains("---------")
                .contains("|")
                .contains("●");
    }

    @Test
    @DisplayName("Deve retornar mensagem de erro para face inválida de dado")
    void shouldHandleInvalidDieFace() {
        String ascii = DiceRollerEngine.getDieAscii(7);
        assertThat(ascii).contains("Invalid die roll: 7");
    }

    @Test
    @DisplayName("Deve simular execução CLI do DiceRoller sem exceções")
    void shouldSimulateCLIWorkflow() {
        String simulatedInput = "3\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        // Mock deterministic random: sempre retornará 4
        Random mockRandom = new Random() {
            @Override
            public int nextInt(int origin, int bound) {
                return 4;
            }
        };

        DiceRollerEngine engine = new DiceRollerEngine(mockRandom);
        DiceRollerCLI.runSimulation(scanner, engine);
    }
}
