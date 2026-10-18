package com.mickaelgeronimo.mastery.algorithms;

public class AnaliseCombinatoriaEnem {
    public static void main(String[] args) {
        // Dados do problema do ENEM 2025
        int totalCasais = 10;
        int totalPessoas = totalCasais * 2; // Cada casal tem 2 pessoas = 20

        // Metodo: Selecao direta pessoa por pessoa respeitando a regra do casal
        int opcoesPresidente = totalPessoas;         // 20 pessoas disponiveis
        int opcoesSecretario = totalPessoas - 2;     // Exclui o casal do presidente = 18
        int opcoesTesoureiro = totalPessoas - 4;     // Exclui os casais do presidente e secretario = 16

        // Calculo da multiplicacao pelo Principio Fundamental da Contagem (PFC)
        int totalDiretorias = opcoesPresidente * opcoesSecretario * opcoesTesoureiro;

        // Exibicao detalhada no console
        System.out.println("=== RESOLUCAO ENEM 2025 - ANALISE COMBINATORIA ===");
        System.out.println("Total de casais: " + totalCasais);
        System.out.println("Total de pessoas disponiveis: " + totalPessoas);
        System.out.println("Opcoes para Presidente: " + opcoesPresidente);
        System.out.println("Opcoes para Secretario: " + opcoesSecretario);
        System.out.println("Opcoes para Tesoureiro: " + opcoesTesoureiro);
        System.out.println("Expressao do gabarito: " + opcoesPresidente + " x " + opcoesSecretario + " x " + opcoesTesoureiro);
        System.out.println("Total numerico de diretorias possiveis: " + totalDiretorias);
        System.out.println("Gabarito correto: Letra B");
    }
}
