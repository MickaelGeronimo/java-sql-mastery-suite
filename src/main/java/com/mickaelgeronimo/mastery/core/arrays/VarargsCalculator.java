package com.mickaelgeronimo.mastery.core.arrays;

/**
 * Calculadora utilitária baseada em Varargs (Argumentos de Comprimento Variável) (Capítulo 34).
 * <p>
 * Demonstra:
 * <ul>
 *   <li>Uso de reticências ({@code int... numbers}) eliminando métodos sobrecarregados redundantes</li>
 *   <li>Processamento iterativo com laço {@code for-each}</li>
 *   <li>Cláusula de guarda para evitar divisões por zero e resultados {@code NaN}</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class VarargsCalculator {

    private VarargsCalculator() {
        // Utilitário não instanciável
    }

    /**
     * Soma uma quantidade arbitrária de números inteiros.
     *
     * @param numbers números a serem somados
     * @return valor total somado (0 se nenhum número for fornecido)
     */
    public static int add(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    /**
     * Calcula a média aritmética de uma quantidade arbitrária de números decimais.
     * Inclui cláusula de guarda para prevenir o retorno de {@link Double#NaN}.
     *
     * @param numbers números para cálculo da média
     * @return média aritmética (0.0 se nenhum número for passado)
     */
    public static double average(double... numbers) {
        // Cláusula de guarda essencial contra NaN (0.0 / 0)
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }

        double sum = 0.0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    /**
     * Execução demonstrativa em linha de comando.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 34: VARARGS (ARGUMENTOS DE COMPRIMENTO VARIÁVEL)              ");
        System.out.println("==========================================================================");

        System.out.println("add(1, 2)                         = " + add(1, 2));
        System.out.println("add(1, 2, 3, 4)                   = " + add(1, 2, 3, 4));
        System.out.println("add(1, 2, 3, 4, 5, 6)             = " + add(1, 2, 3, 4, 5, 6));
        System.out.println("add() [vazio]                     = " + add());

        System.out.println("\n--- CÁLCULO DE MÉDIA (AVERAGE) ---");
        System.out.println("average(1, 2, 3, 4)               = " + average(1, 2, 3, 4));
        System.out.println("average(10.0, 20.0, 30.0)         = " + average(10.0, 20.0, 30.0));
        System.out.println("average() [protegido contra NaN]  = " + average());

        System.out.println("==========================================================================");
    }
}
