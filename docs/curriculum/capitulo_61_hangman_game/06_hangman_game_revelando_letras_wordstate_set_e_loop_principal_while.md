# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 6: Revelando as Letras com `wordState.set()`, Letras Repetidas e o Loop Principal `while(wrongGuesses < 6)`

---

## 🧭 Visão Geral Desta Etapa

Nesta sexta parte do **Capítulo 61**, o jogo da forca dá um salto gigantesco de maturidade com duas implementações fundamentais da ciência da computação e do desenvolvimento de jogos:
1. **Atualização Dinâmica do Tabuleiro (`wordState.set`) (Imagens 1 e 2)**: Quando o jogador acerta uma letra, não basta dizer *"Parabéns!"*; precisamos **revelar a letra na posição exata** no lugar do tracinho `_`!
2. **O Desafio das Letras Repetidas**: A palavra `"pizza"` tem duas letras `'z'`. O método `indexOf('z')` só encontra a primeira. Usando um laço `for` com `word.charAt(i) == guess`, o Java encontra e revela **todas as ocorrências** da letra simultaneamente!
3. **O Fim Prematuro do Jogo (Imagem 3)**: Ao testar com `'z'`, o jogo responde `"Correct guess!"` e imediatamente encerra a execução (`exit code 0`). O jogador não consegue continuar jogando!
4. **Criação do Game Loop Principal (Imagens 4 e 5)**: O instrutor introduz o laço `while(wrongGuesses < 6)` e envolve toda a rodada do jogo dentro dele, permitindo que o jogador chute letra após letra até atingir o limite de vidas!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: ESCREVENDO O LAÇO QUE PROCURA A LETRA NA PALAVRA (TIMESTAMP INICIAL)
====================================================================================================
Editor de Código (Main.java):
  33:         if(word.indexOf(guess) >= 0){
  34:             System.out.println("Correct guess!\n");
  35: 
  36:             for(int i = 0; i < word.length(); i++){
  37:                 if(word.charAt(i) == guess){
  38:                     █  <-- [Cursor digitando dentro do if interno]
  39:                 }
  40:             }
  41:         }
  42:         else{
  43:             System.out.println("Wrong guess!\n");
  44:         }

====================================================================================================
IMAGEM 2: SUBSTITUINDO O SUBLINHADO PELA LETRA COM wordState.set (TIMESTAMP 9:58:29)
====================================================================================================
Editor de Código (Main.java):
  36:             for(int i = 0; i < word.length(); i++){
  37:                 if(word.charAt(i) == guess){
  38:                     wordState.set(i, guess);  <-- [Substitui '_' pela letra adivinhada no índice i!]
  39:                 }
  40:             }

====================================================================================================
IMAGEM 3: TESTANDO 'z' E OBSERVANDO O TÉRMINO PREMATURO DO PROGRAMA (TIMESTAMP 9:58:37)
====================================================================================================
Console "Run" (O programa executa apenas 1 rodada e morre):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: z
  Correct guess!

  Process finished with exit code 0  <-- [O jogo acabou! Falta um laço para continuar rodando!]

====================================================================================================
IMAGEM 4: CRIANDO O LAÇO PRINCIPAL while (TIMESTAMP 9:59:06)
====================================================================================================
Editor de Código (Main.java):
  19:         System.out.println("************************");
  20:         System.out.println("Welcome to Java Hangman!");
  21:         System.out.println("************************");
  22: 
  23:         while(wrongGuesses < 6){  <-- [Início do laço do jogo enquanto tiver vidas!]
  24:             
  25:         }
  26: 
  27:         scanner.close();

====================================================================================================
IMAGEM 5: EMBUTINDO A RODADA INTEIRA DENTRO DO while (TIMESTAMP 9:59:08)
====================================================================================================
Editor de Código (Main.java - Indentação interna do while):
  23:         while(wrongGuesses < 6){
  24: 
  25:             System.out.print("Word: ");
  26:             for(char c : wordState){
  27:                 System.out.print(c + " ");
  28:             }
  29:             System.out.println();
  30: 
  31:             System.out.print("Guess a letter: ");
  32:             char guess = scanner.next().toLowerCase().charAt(0);
  33: 
  34:             if(word.indexOf(guess) >= 0){
  35:                 System.out.println("Correct guess!\n");
  36: 
  37:                 for(int i = 0; i < word.length(); i++){
  38:                     if(word.charAt(i) == guess){
  39:                         wordState.set(i, guess);
  40:                     }
  41:                 }
  42:             }
  43:             else{
  44:                 System.out.println("Wrong guess!\n");
  45:             }
  46:         }
```

---

## 🧠 Explicação Didática Para Iniciantes Amadores em Java

### 1. O que é o método `wordState.set(i, guess)`?
Em um `ArrayList` do Java, existem dois métodos parecidos mas com propósitos totalmente diferentes:
* **`add(elemento)`**: Empurra um novo elemento para o **final** da lista, aumentando o tamanho dela.
* **`set(indice, novoValor)`**: **NÃO aumenta o tamanho** da lista! Ele vai até a "gaveta" de número `indice`, joga fora o que estava lá dentro e coloca o `novoValor` no lugar!

Na forca, o `wordState` começa com 5 gavetas contendo sublinhados:
`[ '_', '_', '_', '_', '_' ]`  
Quando o jogador acerta a letra `'z'` que está na gaveta 2:
`wordState.set(2, 'z');`  
A gaveta 2 deixa de ter `'_'` e passa a guardar `'z'`!
O `wordState` se transforma em:
`[ '_', '_', 'z', '_', '_' ]`

---

### 2. Por que precisamos do `for` se já usamos `indexOf`?
Esta é uma sacada genial de programação:
* O método `word.indexOf(guess)` é ótimo para uma coisa: responder um simples **"SIM"** ou **"NÃO"** se a letra existe na palavra.
* Porém, se a palavra for `"pizza"`, a letra `'z'` aparece **DUAS VEZES** (no índice 2 e no índice 3)!
* Se usássemos apenas o `indexOf('z')`, ele pararia na primeira letra `'z'` que encontrasse (índice 2) e **NUNCA revelaria o segundo `'z'`**!
* É por isso que fazemos um laço `for` completo de `0` até o tamanho da palavra:
  ```java
  for(int i = 0; i < word.length(); i++){
      if(word.charAt(i) == guess){
          wordState.set(i, guess);
      }
  }
  ```
  Ele verifica letra por letra:
  * `i = 0`: `'p' == 'z'`? Não.
  * `i = 1`: `'i' == 'z'`? Não.
  * `i = 2`: `'z' == 'z'`? **SIM!** Substitui a posição 2 por `'z'`.
  * `i = 3`: `'z' == 'z'`? **SIM!** Substitui a posição 3 por `'z'`.
  * `i = 4`: `'a' == 'z'`? Não.
  Resultado: **os dois 'z's são revelados de uma só vez!**

---

### 3. O Problema da Imagem 3: Por que o jogo parava?
Na Imagem 3, você digita `'z'`, o programa diz `"Correct guess!"` e imediatamente aparece:
`Process finished with exit code 0`.
Por quê? Porque os programas de computador são executados de cima para baixo em linha reta. Quando a última instrução da função `main` termina, a JVM simplesmente desliga a aplicação!  
Um jogo real não pode fazer isso. Ele precisa de um **Game Loop (Ciclo do Jogo)**, que é um laço de repetição que mantém o jogo vivo rodada após rodada!

---

### 4. O Laço `while(wrongGuesses < 6)` (Imagens 4 e 5)
Como sabemos quantas chances o jogador tem?  
Lembra do nosso método de arte ASCII `getHangmanArt`? O boneco tem exatamente 6 partes do corpo:
1. Cabeça
2. Tronco
3. Braço esquerdo
4. Braço direito
5. Perna esquerda
6. Perna direita (Game Over!)

Portanto, enquanto o número de erros for **menor que 6** (`wrongGuesses < 6`):
* O jogador ainda tem vidas!
* O laço `while` repete: desenha a palavra atualizada, pede outra letra, confere acerto/erro e recomeça!

---

## 📊 Rastreamento do Estado da Memória com a Letra `'z'`

Veja a transformação da lista `wordState` na memória RAM do computador:

| Passo | Expressão Executada | Estado de `wordState` | O que o jogador vê na tela |
| :---: | :--- | :--- | :--- |
| **Início** | Inicialização com loop | `['_', '_', '_', '_', '_']` | `Word: _ _ _ _ _ ` |
| **Palpite** | Jogador digita `'z'` | `['_', '_', '_', '_', '_']` | `Guess a letter: z` |
| **Índice 2** | `wordState.set(2, 'z')` | `['_', '_', 'z', '_', '_']` | *(atualização interna)* |
| **Índice 3** | `wordState.set(3, 'z')` | `['_', '_', 'z', 'z', '_']` | *(atualização interna)* |
| **Próxima Volta** | Início do `while` | `['_', '_', 'z', 'z', '_']` | `Word: _ _ z z _ ` |

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
                System.out.println("Wrong guess!\n");
            }
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

## 🔬 Análise Linha por Linha das Novidades

### **Linha 23: `while(wrongGuesses < 6){`**
* Inicia o laço de repetição do jogo.
* Avalia se `wrongGuesses` é menor que 6. Como começou em 0, `0 < 6` é verdadeiro e a primeira rodada começa.

### **Linhas 37 a 41: O Loop de Revelação das Letras**
```java
37: for(int i = 0; i < word.length(); i++){
38:     if(word.charAt(i) == guess){
39:         wordState.set(i, guess);
40:     }
41: }
```
* **Linha 37:** Percorre cada índice `i` da palavra `"pizza"` de 0 a 4.
* **Linha 38:** Compara se a letra na posição `i` da palavra secreta é idêntica à letra palpitada (`guess`).
* **Linha 39:** Se for igual, substitui no `wordState` o sublinhado `'_'` pela letra correspondente.

### **Linha 46: `}`**
* Fecha a chave do bloco `while`. O Java volta para a Linha 23 para testar a condição de repetição novamente!

---

## 🖥️ Saída Esperada no Console com o Game Loop Ativo

Agora, quando o jogador chutar a letra `'z'`, em vez do jogo fechar, ele mostrará as letras reveladas na rodada seguinte:

```text
************************
Welcome to Java Hangman!
************************
Word: _ _ _ _ _ 
Guess a letter: z
Correct guess!

Word: _ _ z z _ 
Guess a letter: █
```

*(Perceba como a palavra mudou magicamente de `_ _ _ _ _` para `_ _ z z _`! Nas próximas imagens, veremos como somar `wrongGuesses++` para desenhar o boneco na forca quando ele errar e como verificar as condições finais de Vitória ou Derrota!)*
