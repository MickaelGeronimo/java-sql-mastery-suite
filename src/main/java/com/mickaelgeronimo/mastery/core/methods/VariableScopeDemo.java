package com.mickaelgeronimo.mastery.core.methods;

/**
 * Demonstração dos fundamentos de Escopo de Variáveis (Capítulo 28).
 * <p>
 * Conceitos cobertos:
 * <ul>
 *   <li>Escopo Local (Local Scope): Memória Stack, destruída ao sair do bloco/método</li>
 *   <li>Escopo de Classe (Class Scope): Memória Static/Metaspace, compartilhada por todos os métodos</li>
 *   <li>Sombreamento (Variable Shadowing): Quando uma variável local oculta a variável de classe homônima</li>
 *   <li>Acesso Explícito: Como contornar o sombreamento usando {@code ClassName.variable}</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public final class VariableScopeDemo {

    /**
     * Variável de Classe (Escopo Global da Classe / Sala de Estar da Casa).
     */
    public static int classX = 3;

    private VariableScopeDemo() {
        // Utilitário
    }

    /**
     * Retorna o valor resolvido no contexto onde há variável local x = 1.
     * (Demonstra o Sombreamento: a variável local tem precedência!).
     *
     * @return valor local (1)
     */
    public static int resolveWithLocalShadowing() {
        int x = 1; // Variável Local sobrepõe classX se tiverem o mesmo nome
        return x;
    }

    /**
     * Retorna explicitamente a variável de classe, contornando qualquer variável local homônima.
     *
     * @return valor da classe (classX)
     */
    public static int resolveExplicitClassVariable() {
        @SuppressWarnings("unused")
        int x = 1; // Local declarada
        return VariableScopeDemo.classX; // Acesso qualificado pela classe!
    }

    /**
     * Retorna o valor quando não há variável local declarada no método.
     * O compilador sobe para o escopo de classe e resolve diretamente.
     *
     * @return valor da classe
     */
    public static int resolveWithoutLocalVariable() {
        return classX;
    }

    /**
     * Método auxiliar simulando o cômodo 2 (doSomething).
     *
     * @return valor local (2)
     */
    public static int doSomethingLocal() {
        int x = 2; // Variável privada deste método
        return x;
    }

    /**
     * Execução demonstrativa em linha de comando.
     *
     * @param args argumentos de terminal
     */
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   CAPÍTULO 28: ESCOPO DE VARIÁVEIS (VARIABLE SCOPE SUITE)                 ");
        System.out.println("==========================================================================");

        System.out.println("1. Variável de Classe (classX na 'sala'): " + classX);
        System.out.println("2. Método com variável local (x = 1 no 'quarto'): " + resolveWithLocalShadowing());
        System.out.println("3. Método doSomething (x = 2 no 'outro quarto'): " + doSomethingLocal());
        System.out.println("4. Acesso explícito via NomeDaClasse.classX: " + resolveExplicitClassVariable());
        System.out.println("5. Método sem variável local recorrendo à classe: " + resolveWithoutLocalVariable());

        System.out.println("==========================================================================");
    }
}
