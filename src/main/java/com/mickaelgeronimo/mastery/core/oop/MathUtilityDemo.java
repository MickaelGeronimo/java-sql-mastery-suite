package com.mickaelgeronimo.mastery.core.oop;

/**
 * Demonstração do padrão de Classe Utilitária Estática inspirado em {@code java.lang.Math} (Capítulo 43).
 * Apresenta construtor privado, constantes públicas imutáveis e métodos estáticos puros.
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class MathUtilityDemo {

    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;

    /**
     * Construtor privado que impede a instanciação externa da classe utilitária.
     * Segue a mesma arquitetura do código-fonte oficial do JDK em {@code java.lang.Math}.
     */
    private MathUtilityDemo() {
        throw new UnsupportedOperationException("Classes utilitárias estáticas não devem ser instanciadas.");
    }

    /**
     * Arredonda um número decimal para o inteiro mais próximo.
     *
     * @param value valor decimal
     * @return valor arredondado
     */
    public static long round(double value) {
        return Math.round(value);
    }

    /**
     * Eleva um número ao quadrado.
     *
     * @param value valor base
     * @return base elevada ao expoente 2
     */
    public static double square(double value) {
        return value * value;
    }

    /**
     * Retorna o maior entre dois números inteiros.
     *
     * @param a primeiro valor
     * @param b segundo valor
     * @return maior valor
     */
    public static int max(int a, int b) {
        return Math.max(a, b);
    }
}
