package com.mickaelgeronimo.mastery.core.methods;

/**
 * Demonstração dos fundamentos de Sobrecarga de Métodos (Capítulo 27).
 * <p>
 * Regra: Assinatura de Método = Nome do Método + Lista de Parâmetros.
 * O tipo de retorno sozinho NÃO diferencia sobrecargas.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class OverloadedMethodsMasteryDemo {

    private OverloadedMethodsMasteryDemo() {
        // Utilitário não instanciável
    }

    /**
     * Soma 2 números inteiros.
     *
     * @param a primeiro termo
     * @param b segundo termo
     * @return a + b
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Sobrecarga: Soma 3 números inteiros.
     *
     * @param a primeiro termo
     * @param b segundo termo
     * @param c terceiro termo
     * @return a + b + c
     */
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * Sobrecarga: Soma 4 números inteiros.
     *
     * @param a primeiro termo
     * @param b segundo termo
     * @param c terceiro termo
     * @param d quarto termo
     * @return a + b + c + d
     */
    public static int add(int a, int b, int c, int d) {
        return a + b + c + d;
    }

    /**
     * Sobrecarga com tipos decimais: Soma 2 doubles.
     *
     * @param a primeiro termo double
     * @param b segundo termo double
     * @return a + b
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * Sobrecarga de Pizza 1: Pão e molho básicos.
     *
     * @param bread tipo de pão/massa
     * @param sauce tipo de molho
     * @return descrição da pizza
     */
    public static String bakePizza(String bread, String sauce) {
        return String.format("Pizza assada com massa '%s' e molho '%s'.", bread, sauce);
    }

    /**
     * Sobrecarga de Pizza 2: Pão, molho e queijo.
     *
     * @param bread  tipo de massa
     * @param sauce  tipo de molho
     * @param cheese tipo de queijo
     * @return descrição da pizza
     */
    public static String bakePizza(String bread, String sauce, String cheese) {
        return String.format("Pizza assada com massa '%s', molho '%s' e queijo '%s'.", bread, sauce, cheese);
    }

    /**
     * Sobrecarga de Pizza 3: Pão, molho, queijo e cobertura especial.
     *
     * @param bread   tipo de massa
     * @param sauce   tipo de molho
     * @param cheese  tipo de queijo
     * @param topping cobertura (ex: pepperoni, cogumelos)
     * @return descrição da pizza
     */
    public static String bakePizza(String bread, String sauce, String cheese, String topping) {
        return String.format("Pizza especial assada com massa '%s', molho '%s', queijo '%s' e cobertura '%s'.",
                bread, sauce, cheese, topping);
    }

    /**
     * Execução demonstrativa em linha de comando.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 27: SOBRECARGA DE MÉTODOS (OVERLOADED METHODS SUITE)          ");
        System.out.println("==========================================================================");

        System.out.println("add(1, 2)             = " + add(1, 2));
        System.out.println("add(1, 2, 3)          = " + add(1, 2, 3));
        System.out.println("add(1, 2, 3, 4)       = " + add(1, 2, 3, 4));
        System.out.println("add(2.5, 4.5)         = " + add(2.5, 4.5));

        System.out.println("\n--- FÁBRICA DE PIZZAS BRO CODE ---");
        System.out.println(bakePizza("Massa Fina", "Tomate"));
        System.out.println(bakePizza("Massa Tradicional", "Tomate", "Mozzarella"));
        System.out.println(bakePizza("Borda Recheada", "Barbecue", "Cheddar", "Pepperoni"));

        System.out.println("==========================================================================");
    }
}
