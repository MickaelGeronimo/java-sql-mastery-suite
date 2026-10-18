package com.mickaelgeronimo.mastery.core.loops;

/**
 * NestedLoopsGridDemo — Capítulo 25: Laços Aninhados (Nested Loops)
 * 
 * Demonstra:
 * 1. A diferença essencial entre impressão vertical (println) e horizontal (print).
 * 2. O erro de compilação clássico 'variable i is already defined' e o escopo de variáveis.
 * 3. A solução universal adotando 'j' para o laço interno e o papel do println() vazio.
 * 4. Geração de grade numérica bidimensional 3x9 (27 elementos).
 */
public class NestedLoopsGridDemo {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 25: FUNDAMENTOS DE LAÇOS ANINHADOS (NESTED LOOPS)             ");
        System.out.println("==========================================================================\n");

        // 1. Laço simples vertical (println)
        System.out.println("--- 1. IMPRESSÃO VERTICAL (println) ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Linha vertical: " + i);
        }

        // 2. Laço simples horizontal (print)
        System.out.println("\n--- 2. IMPRESSÃO HORIZONTAL (print com espaço) ---");
        for (int i = 1; i <= 9; i++) {
            System.out.print(i + " ");
        }
        System.out.println(); // Salto de linha para não colar com o próximo bloco

        // 3. Matriz 3x9 perfeita com laço aninhado
        System.out.println("\n--- 3. MATRIZ 3x9 PERFEITA (i = linhas, j = colunas) ---");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(j + " ");
            }
            System.out.println(); // "Carriage return" / Tecla Enter: final da linha
        }

        System.out.println("\n==========================================================================");
        System.out.println("   DEMONSTRAÇÃO DE LAÇOS ANINHADOS CONCLUÍDA COM SUCESSO!                 ");
        System.out.println("==========================================================================");
    }
}
