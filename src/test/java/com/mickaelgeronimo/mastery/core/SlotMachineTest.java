package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.games.SlotMachineCLI;
import com.mickaelgeronimo.mastery.core.games.SlotMachineEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 38: Testes Automatizados da Java Slot Machine")
class SlotMachineTest {

    @ParameterizedTest
    @CsvSource({
            "10, 100, true",
            "100, 100, true",
            "1, 5, true",
            "0, 100, false",
            "-10, 100, false",
            "101, 100, false",
            "50, 20, false"
    })
    @DisplayName("Deve validar limites de aposta de acordo com o saldo disponível")
    void shouldValidateBetsCorrectly(int bet, int balance, boolean expected) {
        assertThat(SlotMachineEngine.isValidBet(bet, balance)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Deve pagar multiplicadores corretos para trincas (3 símbolos iguais)")
    void shouldCalculateThreeOfAKindPayouts() {
        // 🍒 -> 3x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "🍒", "🍒"}, 10)).isEqualTo(30);
        // 🍉 -> 4x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍉", "🍉", "🍉"}, 10)).isEqualTo(40);
        // 🍋 -> 5x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍋", "🍋", "🍋"}, 10)).isEqualTo(50);
        // 🔔 -> 10x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🔔", "🔔", "🔔"}, 10)).isEqualTo(100);
        // ⭐ -> 20x
        assertThat(SlotMachineEngine.getPayout(new String[]{"⭐", "⭐", "⭐"}, 10)).isEqualTo(200);
    }

    @Test
    @DisplayName("Deve pagar multiplicadores corretos para pares adjacentes à esquerda (posições 0 e 1)")
    void shouldCalculatePairPayoutsOnLeft() {
        // 🍒 🍒 ? -> 2x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "🍒", "🔔"}, 10)).isEqualTo(20);
        // 🍉 🍉 ? -> 3x (Teste clássico do Bro Code: 🍉 🍉 🍋)
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍉", "🍉", "🍋"}, 10)).isEqualTo(30);
        // 🍋 🍋 ? -> 4x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍋", "🍋", "⭐"}, 10)).isEqualTo(40);
        // 🔔 🔔 ? -> 5x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🔔", "🔔", "🍉"}, 10)).isEqualTo(50);
        // ⭐ ⭐ ? -> 10x
        assertThat(SlotMachineEngine.getPayout(new String[]{"⭐", "⭐", "🍋"}, 10)).isEqualTo(100);
    }

    @Test
    @DisplayName("Deve pagar multiplicadores corretos para pares adjacentes à direita (posições 1 e 2)")
    void shouldCalculatePairPayoutsOnRight() {
        // ? ⭐ ⭐ -> 10x (Teste Bro Code Parte 12: 🍒 ⭐ ⭐ com aposta $1 = $10)
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "⭐", "⭐"}, 1)).isEqualTo(10);
        // ? 🍒 🍒 -> 2x (Teste Bro Code Parte 12: ⭐ 🍒 🍒 com aposta $10 = $20)
        assertThat(SlotMachineEngine.getPayout(new String[]{"⭐", "🍒", "🍒"}, 10)).isEqualTo(20);
        // ? 🍉 🍉 -> 3x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🔔", "🍉", "🍉"}, 5)).isEqualTo(15);
        // ? 🍋 🍋 -> 4x
        assertThat(SlotMachineEngine.getPayout(new String[]{"⭐", "🍋", "🍋"}, 2)).isEqualTo(8);
        // ? 🔔 🔔 -> 5x
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍉", "🔔", "🔔"}, 4)).isEqualTo(20);
    }

    @Test
    @DisplayName("Deve retornar $0 para rodadas sem trinca ou par adjacente")
    void shouldReturnZeroPayoutWhenNoMatchingPairOrThree() {
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "🍉", "🍋"}, 10)).isZero();
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍋", "⭐", "🍒"}, 5)).isZero();
        assertThat(SlotMachineEngine.getPayout(new String[]{"⭐", "🔔", "🍉"}, 20)).isZero();
        // Não adjacente (posições 0 e 2 iguais, mas meio diferente)
        assertThat(SlotMachineEngine.getPayout(new String[]{"⭐", "🍋", "⭐"}, 10)).isZero();
    }

    @Test
    @DisplayName("Deve tratar entradas inválidas de forma segura")
    void shouldHandleEdgeCasesSafely() {
        assertThat(SlotMachineEngine.getPayout(null, 10)).isZero();
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "🍒"}, 10)).isZero();
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "🍒", "🍒"}, 0)).isZero();
        assertThat(SlotMachineEngine.getPayout(new String[]{"🍒", "🍒", "🍒"}, -5)).isZero();
        assertThat(SlotMachineEngine.getPayout(new String[]{"X", "X", "X"}, 10)).isZero();
    }

    @Test
    @DisplayName("Deve sortear 3 símbolos pertencentes à cartela oficial")
    void shouldSpinRowWithValidSymbols() {
        SlotMachineEngine engine = new SlotMachineEngine();
        String[] row = engine.spinRow();

        assertThat(row).hasSize(3);
        assertThat(row).allMatch(s -> Arrays.asList(SlotMachineEngine.SYMBOLS).contains(s));
    }

    @Test
    @DisplayName("Deve formatar a moldura visual de 25 asteriscos perfeitamente")
    void shouldFormatRowNicely() {
        String[] row = {"🍒", "🍉", "🍋"};
        String formatted = SlotMachineEngine.formatRow(row);

        assertThat(formatted)
                .contains(SlotMachineEngine.BORDER)
                .contains("🍒 | 🍉 | 🍋");
    }

    @Test
    @DisplayName("Deve produzir relatório estruturado SpinResult com flags precisas")
    void shouldEvaluateSpinCorrectly() {
        // Trinca
        SlotMachineEngine.SpinResult winThree = SlotMachineEngine.evaluateSpin(new String[]{"⭐", "⭐", "⭐"}, 10);
        assertThat(winThree.isWin()).isTrue();
        assertThat(winThree.isThreeOfAKind()).isTrue();
        assertThat(winThree.isPair()).isFalse();
        assertThat(winThree.payout()).isEqualTo(200);

        // Par
        SlotMachineEngine.SpinResult winPair = SlotMachineEngine.evaluateSpin(new String[]{"🍉", "🍉", "🍋"}, 10);
        assertThat(winPair.isWin()).isTrue();
        assertThat(winPair.isThreeOfAKind()).isFalse();
        assertThat(winPair.isPair()).isTrue();
        assertThat(winPair.payout()).isEqualTo(30);

        // Derrota
        SlotMachineEngine.SpinResult loss = SlotMachineEngine.evaluateSpin(new String[]{"🍒", "🍉", "🍋"}, 10);
        assertThat(loss.isWin()).isFalse();
        assertThat(loss.isThreeOfAKind()).isFalse();
        assertThat(loss.isPair()).isFalse();
        assertThat(loss.payout()).isZero();
    }

    @Test
    @DisplayName("Deve simular sessão completa na CLI com rejeição de aposta inválida, vitórias e saída limpa")
    void shouldSimulateCLIWorkflowCompleteSession() {
        // Simulação do jogador:
        // 1. Digita aposta exorbitante 200 (INSUFFICIENT FUNDS)
        // 2. Digita aposta negativa -10 (Bet must be greater than 0)
        // 3. Aposta $10 (Perde) -> pergunta replay: Y
        // 4. Aposta $20 (Ganha par melancias) -> pergunta replay: N
        String simulatedInput = String.join(System.lineSeparator(),
                "200",
                "-10",
                "10",
                "Y",
                "20",
                "N"
        ) + System.lineSeparator();

        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        // Motor determinístico com sequências controladas
        // Giro 1: Derrota {"🍒", "🍉", "🍋"}
        // Giro 2: Par de melancias {"🍉", "🍉", "🔔"} (paga 3x20 = $60)
        final int[] callCount = {0};
        SlotMachineEngine deterministicEngine = new SlotMachineEngine() {
            @Override
            public String[] spinRow() {
                callCount[0]++;
                if (callCount[0] == 1) {
                    return new String[]{"🍒", "🍉", "🍋"};
                } else {
                    return new String[]{"🍉", "🍉", "🔔"};
                }
            }
        };

        // Saldo inicial $100:
        // Rodada 1: 100 - 10 = 90 + 0 = 90
        // Rodada 2: 90 - 20 = 70 + 60 = 130
        int finalBalance = SlotMachineCLI.runGame(scanner, deterministicEngine, 100);

        assertThat(finalBalance).isEqualTo(130);
    }
}
