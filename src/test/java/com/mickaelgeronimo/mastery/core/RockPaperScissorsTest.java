package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.games.RockPaperScissorsCLI;
import com.mickaelgeronimo.mastery.core.games.RockPaperScissorsEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 37: Testes Automatizados do Jogo Rock Paper Scissors")
class RockPaperScissorsTest {

    @ParameterizedTest
    @CsvSource({
            "rock, true",
            "paper, true",
            "scissors, true",
            "ROCK, true",
            "  Paper  , true",
            "gun, false",
            "lizard, false",
            "'', false"
    })
    @DisplayName("Deve validar movimentos válidos e rejeitar jogadas ilegais")
    void shouldValidateMoves(String move, boolean expectedValid) {
        assertThat(RockPaperScissorsEngine.isValidMove(move)).isEqualTo(expectedValid);
    }

    @Test
    @DisplayName("Deve identificar empates quando as escolhas forem iguais")
    void shouldIdentifyTies() {
        assertThat(RockPaperScissorsEngine.evaluateRound("rock", "rock"))
                .isEqualTo(RockPaperScissorsEngine.GameResult.TIE);
        assertThat(RockPaperScissorsEngine.evaluateRound("paper", "paper"))
                .isEqualTo(RockPaperScissorsEngine.GameResult.TIE);
        assertThat(RockPaperScissorsEngine.evaluateRound("scissors", "scissors"))
                .isEqualTo(RockPaperScissorsEngine.GameResult.TIE);
    }

    @ParameterizedTest
    @CsvSource({
            "rock, scissors",
            "paper, rock",
            "scissors, paper"
    })
    @DisplayName("Deve determinar vitória do jogador de acordo com as regras canônicas")
    void shouldDeterminePlayerWins(String playerMove, String computerMove) {
        assertThat(RockPaperScissorsEngine.evaluateRound(playerMove, computerMove))
                .isEqualTo(RockPaperScissorsEngine.GameResult.WIN);
    }

    @ParameterizedTest
    @CsvSource({
            "scissors, rock",
            "rock, paper",
            "paper, scissors"
    })
    @DisplayName("Deve determinar vitória do computador (derrota do jogador)")
    void shouldDeterminePlayerLosses(String playerMove, String computerMove) {
        assertThat(RockPaperScissorsEngine.evaluateRound(playerMove, computerMove))
                .isEqualTo(RockPaperScissorsEngine.GameResult.LOSE);
    }

    @Test
    @DisplayName("Deve retornar INVALID para jogadas desconhecidas")
    void shouldReturnInvalidForIllegalMoves() {
        assertThat(RockPaperScissorsEngine.evaluateRound("gun", "rock"))
                .isEqualTo(RockPaperScissorsEngine.GameResult.INVALID);
    }

    @Test
    @DisplayName("Deve simular partida completa via CLI com vitória e repetição sem erros")
    void shouldSimulateCLIWorkflow() {
        // Entrada:
        // "gun" (inválido)
        // "rock" (válido)
        // "no" (não jogar de novo)
        String simulatedInput = "gun\nrock\nno\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        // Mock determinístico: computador sempre joga scissors
        Random mockRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return 2; // índice 2 = scissors
            }
        };

        RockPaperScissorsEngine engine = new RockPaperScissorsEngine(mockRandom);
        RockPaperScissorsCLI.runGame(scanner, engine);
    }
}
