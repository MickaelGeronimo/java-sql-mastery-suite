package com.mickaelgeronimo.mastery.core.oop;

/**
 * Demonstração executável dos conceitos de Construtores Sobrecarregados (Capítulo 41).
 * Simula a instanciação dos 4 perfis de usuários do Bro Code.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class OverloadedConstructorsDemo {

    private OverloadedConstructorsDemo() {
        // Classe utilitária
    }

    public static void runDemo() {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 41: CONSTRUTORES SOBRECARREGADOS (OVERLOADED CONSTRUCTORS)     ");
        System.out.println("==========================================================================");

        // Instanciando os 4 usuários com assinaturas diferentes
        User user1 = new User("Spongebob");
        User user2 = new User("Patrick", "ps@gmail.com");
        User user3 = new User("Sandy", "sc@gmail.com", 27);
        User user4 = new User();

        System.out.println("\n--- PERFIL USER 1 (Apenas Username) ---");
        System.out.println(user1.username);
        System.out.println(user1.email);
        System.out.println(user1.age);

        System.out.println("\n--- PERFIL USER 2 (Username + Email) ---");
        System.out.println(user2.username);
        System.out.println(user2.email);
        System.out.println(user2.age);

        System.out.println("\n--- PERFIL USER 3 (Username + Email + Age) ---");
        System.out.println(user3.username);
        System.out.println(user3.email);
        System.out.println(user3.age);

        System.out.println("\n--- PERFIL USER 4 (Guest / Padrão Sem Argumentos) ---");
        System.out.println(user4.username);
        System.out.println(user4.email);
        System.out.println(user4.age);

        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        runDemo();
    }
}
