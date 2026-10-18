package com.mickaelgeronimo.mastery.core.games;

import java.util.Scanner;

/**
 * Interface CLI para o jogo de perguntas e respostas Java Quiz Game (Capítulo 36).
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class QuizGameCLI {

    private QuizGameCLI() {
        // Utilitário
    }

    public static int runQuiz(Scanner scanner, QuizGameEngine engine) {
        System.out.println("******************************");
        System.out.println("   WELCOME TO JAVA QUIZ GAME  ");
        System.out.println("******************************\n");

        int score = 0;

        for (int i = 0; i < engine.getQuestionCount(); i++) {
            System.out.printf("QUESTION %d: %s%n", i + 1, engine.getQuestion(i));

            for (String option : engine.getOptions(i)) {
                System.out.println(option);
            }

            System.out.print("ENTER YOUR GUESS (A, B, C, D): ");
            if (!scanner.hasNext()) {
                break;
            }

            char guess = scanner.next().charAt(0);

            if (engine.checkAnswer(i, guess)) {
                System.out.println("CORRECT!\n");
                score++;
            } else {
                System.out.printf("WRONG! The correct answer was: %c%n%n", engine.getAnswer(i));
            }
        }

        System.out.println("******************************");
        System.out.println("          FINAL SCORE         ");
        System.out.println("******************************");
        System.out.printf("You scored %d out of %d (%.1f%%)%n",
                score, engine.getQuestionCount(), engine.calculatePercentage(score));
        System.out.println("******************************\n");

        return score;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizGameEngine engine = QuizGameEngine.createDefaultCourseQuiz();
        runQuiz(scanner, engine);
    }
}
