package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.games.QuizGameCLI;
import com.mickaelgeronimo.mastery.core.games.QuizGameEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Capítulo 36: Testes Automatizados do Java Quiz Game")
class QuizGameTest {

    @Test
    @DisplayName("Deve inicializar o Quiz padrão com 4 perguntas, opções correspondentes e gabarito")
    void shouldInitializeDefaultQuiz() {
        QuizGameEngine engine = QuizGameEngine.createDefaultCourseQuiz();

        assertThat(engine.getQuestionCount()).isEqualTo(4);
        assertThat(engine.getQuestion(0)).contains("JVM");
        assertThat(engine.getOptions(0)).hasSize(4);
        assertThat(engine.getAnswer(0)).isEqualTo('A');
    }

    @Test
    @DisplayName("Deve avaliar acertos e erros independentemente de maiúsculas ou minúsculas")
    void shouldCheckAnswersCaseInsensitive() {
        QuizGameEngine engine = QuizGameEngine.createDefaultCourseQuiz();

        // Questão 0 gabarito: 'A'
        assertThat(engine.checkAnswer(0, 'a')).isTrue();
        assertThat(engine.checkAnswer(0, 'A')).isTrue();
        assertThat(engine.checkAnswer(0, 'B')).isFalse();

        // Questão 1 gabarito: 'B'
        assertThat(engine.checkAnswer(1, 'b')).isTrue();
        assertThat(engine.checkAnswer(1, 'C')).isFalse();
    }

    @Test
    @DisplayName("Deve calcular corretamente a porcentagem final")
    void shouldCalculatePercentageScore() {
        QuizGameEngine engine = QuizGameEngine.createDefaultCourseQuiz();

        assertThat(engine.calculatePercentage(4)).isCloseTo(100.0, within(0.001));
        assertThat(engine.calculatePercentage(2)).isCloseTo(50.0, within(0.001));
        assertThat(engine.calculatePercentage(0)).isCloseTo(0.0, within(0.001));
    }

    @Test
    @DisplayName("Deve rejeitar dimensões incompatíveis de perguntas, opções e respostas")
    void shouldRejectMismatchedDimensions() {
        String[] q = {"Q1"};
        String[][] opt = {{"A", "B"}, {"C", "D"}}; // 2 linhas vs 1 pergunta
        char[] ans = {'A'};

        assertThatThrownBy(() -> new QuizGameEngine(q, opt, ans))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Deve simular o jogo completo com respostas pelo terminal sem falhas")
    void shouldSimulateFullQuizWorkflow() {
        // Respostas: A, B, C, B (Gabarito perfeito = 4 acertos, 100%)
        String inputData = "A\nB\nC\nB\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8)));

        QuizGameEngine engine = QuizGameEngine.createDefaultCourseQuiz();
        int finalScore = QuizGameCLI.runQuiz(mockScanner, engine);

        assertThat(finalScore).isEqualTo(4);
    }
}
