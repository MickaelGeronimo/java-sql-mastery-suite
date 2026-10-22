package com.mickaelgeronimo.mastery.core.oop;

/**
 * Demonstração executável dos conceitos do modificador {@code static} em Java (Capítulo 43).
 * Simula a sessão do Bro Code:
 * 1. Instanciação dos 5 amigos da Fenda do Biquíni
 * 2. Contador compartilhado {@code Friend.numOfFriends}
 * 3. Invocação do método estático {@code Friend.showFriends()}
 * 4. Métodos utilitários inspirados em {@code Math.round()}
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class StaticDemo {

    private StaticDemo() {
        // Classe utilitária
    }

    public static void runDemo() {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 43: O MODIFICADOR STATIC EM JAVA (FRIENDS & MATH UTILITIES)   ");
        System.out.println("==========================================================================");

        // Reinicia contador para a demonstração
        Friend.resetFriendsCount();

        // 1. Instanciando os Amigos
        Friend friend1 = new Friend("Spongebob");
        Friend friend2 = new Friend("Patrick");
        Friend friend3 = new Friend("Squidward");
        Friend friend4 = new Friend("Sandy");
        Friend friend5 = new Friend("Gary");

        System.out.println("\n--- 1. ACESSO OFICIAL À VARIÁVEL ESTÁTICA PELA CLASSE ---");
        System.out.println("Total de amigos contados: " + Friend.numOfFriends);

        System.out.println("\n--- 2. INVOCANDO O MÉTODO ESTÁTICO DA CLASSE ---");
        Friend.showFriends();

        System.out.println("\n--- 3. UTILITÁRIOS ESTÁTICOS NO PADRÃO JAVA.LANG.MATH ---");
        double testValue = 3.99;
        System.out.println("Math.round(" + testValue + ") = " + MathUtilityDemo.round(testValue));
        System.out.println("Constante PI = " + MathUtilityDemo.PI);

        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
