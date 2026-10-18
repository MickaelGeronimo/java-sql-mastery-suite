package com.mickaelgeronimo.mastery.core.methods;

/**
 * Demonstração dos fundamentos de Métodos em Java (Capítulo 26).
 * <p>
 * Conceitos cobertos:
 * <ul>
 *   <li>Princípio DRY (Don't Repeat Yourself)</li>
 *   <li>Isolamento léxico de variáveis locais</li>
 *   <li>Passagem de argumentos por valor</li>
 *   <li>Métodos com retorno tipado (int, double, String, boolean)</li>
 *   <li>Composição e chamadas aninhadas</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class MethodsMasteryDemo {

    private MethodsMasteryDemo() {
        // Utilitário não instanciável
    }

    /**
     * Gera mensagem formatada de parabéns para uma pessoa com sua idade.
     *
     * @param name nome do aniversariante
     * @param age  idade comemorada
     * @return texto completo dos parabéns
     */
    public static String buildBirthdayMessage(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        return "Happy birthday to you!\n"
                + "Happy birthday to you!\n"
                + "Happy birthday dear " + name.trim() + "!\n"
                + "You are " + age + " years old!\n";
    }

    /**
     * Calcula o quadrado de um número inteiro.
     *
     * @param x número base
     * @return x elevado ao quadrado
     */
    public static double square(double x) {
        return x * x;
    }

    /**
     * Calcula o cubo de um número inteiro ou decimal.
     *
     * @param x número base
     * @return x elevado ao cubo
     */
    public static double cube(double x) {
        return x * x * x;
    }

    /**
     * Concatena primeiro e último nome com formatação limpa.
     *
     * @param firstName primeiro nome
     * @param lastName  sobrenome
     * @return nome completo formatado
     */
    public static String getFullName(String firstName, String lastName) {
        String first = (firstName == null) ? "" : firstName.trim();
        String last = (lastName == null) ? "" : lastName.trim();
        if (first.isEmpty() && last.isEmpty()) {
            return "";
        }
        if (first.isEmpty()) {
            return last;
        }
        if (last.isEmpty()) {
            return first;
        }
        return first + " " + last;
    }

    /**
     * Verifica se uma pessoa atingiu a maioridade legal (>= 18 anos).
     *
     * @param age idade a ser verificada
     * @return true se maior de idade, false caso contrário
     */
    public static boolean isLegalAge(int age) {
        return age >= 18;
    }

    /**
     * Execução demonstrativa em linha de comando.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 26: DOMÍNIO DE MÉTODOS (METHODS MASTERY SUITE)                ");
        System.out.println("==========================================================================");

        // 1. Mensagem de aniversário
        System.out.println(buildBirthdayMessage("Bro", 25));

        // 2. Operações matemáticas
        double base = 3.0;
        System.out.printf("square(%.1f) = %.2f%n", base, square(base));
        System.out.printf("cube(%.1f) = %.2f%n", base, cube(base));

        // 3. Manipulação de String
        String fullName = getFullName("Spongebob", "Squarepants");
        System.out.println("Nome completo: " + fullName);

        // 4. Predicado booleano
        int testAge = 21;
        System.out.printf("Idade %d é maior de idade? %b%n", testAge, isLegalAge(testAge));

        System.out.println("==========================================================================");
    }
}
