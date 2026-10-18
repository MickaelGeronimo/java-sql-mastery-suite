package com.mickaelgeronimo.mastery.core.games;

import java.util.Objects;

/**
 * Motor de domínio e lógica de avaliação para o Java Quiz Game (Capítulo 36).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class QuizGameEngine {

    private final String[] questions;
    private final String[][] options;
    private final char[] answers;

    public QuizGameEngine(String[] questions, String[][] options, char[] answers) {
        this.questions = Objects.requireNonNull(questions, "Perguntas não podem ser nulas.");
        this.options = Objects.requireNonNull(options, "Opções não podem ser nulas.");
        this.answers = Objects.requireNonNull(answers, "Respostas não podem ser nulas.");

        if (questions.length != options.length || questions.length != answers.length) {
            throw new IllegalArgumentException("As dimensões de perguntas, opções e respostas devem ser idênticas.");
        }
    }

    /**
     * Retorna o banco padrão oficial do curso Bro Code.
     *
     * @return instância configurada do Quiz
     */
    public static QuizGameEngine createDefaultCourseQuiz() {
        String[] questions = {
                "What is the main purpose of the JVM?",
                "Which data type is used to store a single character in Java?",
                "What is the output of 5 + 2 * 3 in Java?",
                "Which keyword is used to define a constant variable in Java?"
        };

        String[][] options = {
                {"A. Execute Java bytecode", "B. Compile Java source code", "C. Debug Java code", "D. Edit Java source code"},
                {"A. String", "B. char", "C. boolean", "D. Character"},
                {"A. 21", "B. 16", "C. 11", "D. 10"},
                {"A. static", "B. final", "C. const", "D. abstract"}
        };

        char[] answers = {'A', 'B', 'C', 'B'};

        return new QuizGameEngine(questions, options, answers);
    }

    public int getQuestionCount() {
        return questions.length;
    }

    public String getQuestion(int index) {
        return questions[index];
    }

    public String[] getOptions(int index) {
        return options[index];
    }

    public char getAnswer(int index) {
        return answers[index];
    }

    /**
     * Verifica se o palpite do jogador está correto para a pergunta indicada.
     *
     * @param questionIndex índice da questão (0 a length-1)
     * @param guess         palpite informado pelo usuário ('A', 'B', 'C' ou 'D')
     * @return true se correto, false caso contrário
     */
    public boolean checkAnswer(int questionIndex, char guess) {
        if (questionIndex < 0 || questionIndex >= questions.length) {
            return false;
        }
        return Character.toUpperCase(guess) == answers[questionIndex];
    }

    /**
     * Calcula o percentual de acerto.
     *
     * @param score pontos obtidos
     * @return porcentagem formatada de 0.0 a 100.0
     */
    public double calculatePercentage(int score) {
        if (questions.length == 0) {
            return 0.0;
        }
        return ((double) score / questions.length) * 100.0;
    }
}
