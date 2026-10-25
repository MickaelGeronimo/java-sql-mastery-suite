# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 9: A Tela de Derrota, `GAME OVER!` e Revelação da Palavra Secreta

---

## 🧭 Visão Geral Desta Etapa

Nesta nona parte do **Capítulo 61**, o instrutor Bro Code implementa a primeira tela de encerramento do jogo: **a Condição de Derrota (Game Over)**!

Aqui aprendemos detalhes cruciais de controle de fluxo:
1. **O que acontece quando as 6 vidas acabam? (Imagens 1 e 2)**: Vimos na aula anterior que o jogador acumulou erros até que `wrongGuesses` atingiu o valor máximo de `6`.
2. **A Saída Natural do Laço `while`**: Quando `wrongGuesses` vira `6`, a condição `while(wrongGuesses < 6)` torna-se **falsa** (`6 < 6` é falso!), fazendo o computador saltar para fora do laço.
3. **A Estrutura Pós-Loop (Imagem 3)**: Fora do `while`, o instrutor adiciona o bloco:
   ```java
   if(wrongGuesses >= 6){
       System.out.print(getHangmanArt(wrongGuesses));
       System.out.println("GAME OVER!");
       System.out.println("The word was: " + word);
   }
   ```
4. **Por que redesenhar o boneco fora do laço?**: Como o laço encerrou antes de imprimir o estágio 6, precisamos chamar `getHangmanArt(wrongGuesses)` uma última vez para que o jogador veja o boneco completo com as duas pernas (`/ \`) enforcado!
5. **Execução Completa da Derrota ao Vivo (Imagem 4)**: O jogador erra o 6º palpite com `'m'`. A forca completa surge no terminal, seguida de `GAME OVER!` e `The word was: pizza`!

---

## 📸 Reconstituição Visual Completa das 4 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: A PALAVRA SENDO PREENCHIDA COM 'p' E 'i' (TIMESTAMP INICIAL)
====================================================================================================
Console "Run" (Terminal inferior):
  Word: _ _ _ _ _ 
  Guess a letter: p
  Correct guess!

  Word: p _ _ _ _ 
  Guess a letter: i

====================================================================================================
IMAGEM 2: PREENCHIMENTO COMPLETO COM 'a' ("pizza") SEM TELA FINAL AINDA
====================================================================================================
Console "Run" (Terminal inferior):
  Word: p i z z _ 
  Guess a letter: a
  Correct guess!

  Word: p i z z a 
  Guess a letter: █  <-- [O jogo continuava pedindo letra por falta do código pós-loop]

====================================================================================================
IMAGEM 3: DIGITANDO A CONDIÇÃO DE DERROTA FORA DO while (TIMESTAMP 55:57 DO VÍDEO)
====================================================================================================
Editor de Código (Main.java - Linhas 51 a 56 logo após a chave '}' do while):
  47:         }
  48: 
  49:         if(wrongGuesses >= 6){
  50:             System.out.print(getHangmanArt(wrongGuesses));
  51:             System.out.println("GAME OVER!");
  52:             System.out.println("The word was: " + word);
  53:         }
  54: 
  55:         scanner.close();

====================================================================================================
IMAGEM 4: TESTE AO VIVO DO 6º ERRO — BONECO COMPLETO E DERROTA
====================================================================================================
Console "Run" (O jogador comete o último erro digitando 'm'):
  /
  Word: _ _ z z _ 
  Guess a letter: m
  Wrong guess!

   o
  /|\
  / \
  GAME OVER!
  The word was: pizza

  Process finished with exit code 0  <-- [O jogo encerrou limpo e educadamente!]
```

---

## 🧠 Explicação Didática Como se Fosse um Iniciante Amador em Java

### 1. Por que esse código fica FORA do laço `while`?
Imagine que o laço `while` é a **partida rolando no campo**:
* Enquanto o jogo não acabar, o computador fica dando voltas dentro do `while` (mostrando a palavra, lendo o palpite, atualizando acertos e erros).
* Quando o juiz apita o fim do jogo (o jogador atingiu 6 erros), a execução **sai do campo** (sai do `while`).
* Tudo o que acontece **DEPOIS** que o jogo termina (mensagens finais, entrega de troféus ou aviso de derrota) deve ficar **fora do laço**, nas linhas subsequentes!

---

### 2. O Grande Mistério: Por que chamar `getHangmanArt` de novo fora do laço?
Esta é uma pergunta clássica de quem está começando a programar:
*"Mas professor, nós já não temos uma linha desenhando o boneco dentro do `while`? Por que precisamos chamá-la de novo na linha 50?"*

Vamos acompanhar o que acontece no exato milissegundo do 6º erro:
1. O jogador está no 5º erro (`wrongGuesses = 5`). O boneco tem apenas a perna esquerda desenhada (`/`).
2. O jogador chuta a letra errada `'m'`.
3. O Java entra no `else`:
   * Executa `wrongGuesses++` $\rightarrow$ `wrongGuesses` agora vale **`6`**!
   * Imprime `"Wrong guess!\n"`.
4. O laço `while` tenta iniciar a próxima rodada e testa a condição:  
   `wrongGuesses < 6` $\rightarrow$ `6 < 6`? **FALSO!**
5. Como a condição deu falso, o computador **PULA IMEDIATAMENTE** todo o conteúdo do `while` e vai direto para a linha 49!
6. **O Problema:** Se não tivéssemos colocado `System.out.print(getHangmanArt(wrongGuesses));` na linha 50, a linha interna do `while` que desenharia o boneco com as duas pernas seria pulada! O jogador perderia o jogo, mas na tela ficaria apenas o boneco perneta com 5 erros!
7. **A Solução:** Chamando `getHangmanArt(6)` fora do `while`, o terminal desenha o boneco enforcado completo com a cabeça, tronco, dois braços e as **duas pernas** (`/ \`)!

---

### 3. A Revelação da Palavra com Concatenação de Strings
Na linha 52:
```java
System.out.println("The word was: " + word);
```
* O operador `+` quando usado entre um texto (`String`) e uma variável junta as duas partes em uma só frase.
* Como a variável `word` guarda `"pizza"`, o console imprime:  
  `The word was: pizza`
* Isso é uma boa prática de design de jogos: quando o jogador perde, é justo revelar qual era a palavra que ele não conseguiu adivinhar!

---

## 📜 Código-Fonte Atualizado ([`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java))

```java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA HANGMAN GAME

        String word = "pizza";

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }

        System.out.println("************************");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("************************");

        while(wrongGuesses < 6){

            System.out.print(getHangmanArt(wrongGuesses));

            System.out.print("Word: ");

            for(char c : wordState){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if(word.indexOf(guess) >= 0){
                System.out.println("Correct guess!\n");

                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        wordState.set(i, guess);
                    }
                }
            }
            else{
                wrongGuesses++;
                System.out.println("Wrong guess!\n");
            }
        }

        if(wrongGuesses >= 6){
            System.out.print(getHangmanArt(wrongGuesses));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);
        }

        scanner.close();
    }

    static String getHangmanArt(int wrongGuesses){

        return switch(wrongGuesses){
            case 0 -> """
                      
                      
                      
                      """;
            case 1 -> """
                       o
                      
                      
                      """;
            case 2 -> """
                       o
                       |
                      
                      """;
            case 3 -> """
                       o
                      /|
                      
                      """;
            case 4 -> """
                       o
                      /|\\
                      
                      """;
            case 5 -> """
                       o
                      /|\\
                      /
                      """;
            case 6 -> """
                       o
                      /|\\
                      / \\
                      """;
            default -> "";
        };
    }
}
```

---

## 🔬 Análise Linha por Linha do Bloco de Derrota

### **Linha 49: `if(wrongGuesses >= 6){`**
* **O que faz:** Confirma se o motivo pelo qual o jogo saiu do laço `while` foi porque o jogador estourou o limite de erros ($\ge 6$).
* *Saída no console:* Nenhuma (avaliação condicional).

---

### **Linha 50: `System.out.print(getHangmanArt(wrongGuesses));`**
* **O que faz:** Desenha a arte ASCII final do boneco enforcado correspondente ao `case 6` (cabeça, tronco, dois braços e duas pernas).
* *Saída no console:*
  ```text
   o
  /|\
  / \
  ```

---

### **Linha 51: `System.out.println("GAME OVER!");`**
* **O que faz:** Imprime a mensagem oficial de fim de jogo.
* *Saída no console:* `GAME OVER!`

---

### **Linha 52: `System.out.println("The word was: " + word);`**
* **O que faz:** Revela para o usuário a palavra secreta original.
* *Saída no console:* `The word was: pizza`

---

### **Linha 53: `}`**
* **O que faz:** Fecha o bloco condicional de derrota.

---

### **Linha 55: `scanner.close();`**
* **O que faz:** Encerra com segurança o leitor de teclado.

---

## 🖥️ Saída Exata no Terminal na Derrota (6º Erro)

```text
Word: _ _ z z _ 
Guess a letter: m
Wrong guess!

 o
/|\
/ \
GAME OVER!
The word was: pizza

Process finished with exit code 0
```

*(O tratamento de derrota está 100% concluído! Agora só falta o outro lado da moeda: o que fazer quando o jogador vencer e descobrir todas as letras antes de morrer na forca? Isso é o que veremos no próximo passo!)*
