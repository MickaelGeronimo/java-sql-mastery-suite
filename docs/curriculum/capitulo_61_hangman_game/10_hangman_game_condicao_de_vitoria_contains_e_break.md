# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 10: A Condição de Vitória, o Método `contains()`, Negação com `!` e o Comando `break`

---

## 🧭 Visão Geral Desta Etapa

Nesta décima parte do **Capítulo 61**, o instrutor Bro Code fecha com chave de ouro os dois pilares fundamentais de qualquer jogo: **a Condição de Vitória e a Condição de Derrota**!

Aqui acompanhamos a resolução do bug do loop infinito que havíamos identificado:
1. **Identificando o Momento da Vitória (Imagens 1 e 2)**: Quando o jogador acerta uma letra, verificamos se ainda sobrou algum tracinho na lista com a condição:  
   `if(!wordState.contains('_'))`
2. **O Operador de Negação Lógica (`!`)**: Entendemos como o ponto de exclamação inverte o resultado booleano (`true` vira `false` e vice-versa).
3. **A Forca Sobrevivente**: Imprimimos `getHangmanArt(wrongGuesses)` para que o jogador veja como ficou o bonequinho no momento da vitória (quantas vidas restaram).
4. **O Poder do Comando `break` (Imagem 3)**: Interrompe imediatamente o laço `while(wrongGuesses < 6)`, impedindo que o jogo continue pedindo letras infinitamente.
5. **Testes Completos no Terminal (Imagens 4 e 5)**:
   * **Teste de Derrota (Imagem 4)**: 6 erros $\rightarrow$ `GAME OVER!`.
   * **Teste de Vitória no Limite (Imagem 5)**: O jogador com 5 erros chuta a última letra `'p'`, sobrevive à forca e recebe a consagração:  
     `YOU WIN!`  
     `The word was: pizza`!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: ESCREVENDO A CONDIÇÃO DE VITÓRIA COM !wordState.contains (TIMESTAMP 47:33)
====================================================================================================
Editor de Código (Main.java - Dentro do bloco de acerto):
  40:             for(int i = 0; i < word.length(); i++){
  41:                 if(word.charAt(i) == guess){
  42:                     wordState.set(i, guess);
  43:                 }
  44:             }
  45: 
  46:             if(!wordState.contains('_')){
  47:                 System.out.print(getHangmanArt(wrongGuesses));
  48:             }

====================================================================================================
IMAGEM 2: ADICIONANDO AS MENSAGENS DE VITÓRIA NO CONSOLE
====================================================================================================
Editor de Código (Main.java):
  46:             if(!wordState.contains('_')){
  47:                 System.out.print(getHangmanArt(wrongGuesses));
  48:                 System.out.println("YOU WIN!");
  49:                 System.out.println("The word was: " + word);
  50:             }

====================================================================================================
IMAGEM 3: O COMANDO break PARA INTERROMPER O while (TIMESTAMP 50:27 DO VÍDEO)
====================================================================================================
Editor de Código (Main.java):
  46:             if(!wordState.contains('_')){
  47:                 System.out.print(getHangmanArt(wrongGuesses));
  48:                 System.out.println("YOU WIN!");
  49:                 System.out.println("The word was: " + word);
  50:                 break;  <-- [PULAA FORA DO WHILE IMEDIATAMENTE!]
  51:             }

====================================================================================================
IMAGEM 4: TESTANDO A DERROTA (RECAPITULAÇÃO DO GAME OVER)
====================================================================================================
Console "Run" (Terminal inferior mostrando a derrota completa):
  Guess a letter: m
  Wrong guess!

   o
  /|\
  / \
  GAME OVER!
  The word was: pizza

  Process finished with exit code 0

====================================================================================================
IMAGEM 5: TESTE REAL DE VITÓRIA NO LIMITE COM 5 ERROS (TIMESTAMP 50:27)
====================================================================================================
Console "Run" (O jogador acerta a última letra 'p' com o boneco a 1 passo da morte):
  Guess a letter: p
  Correct guess!

   o
  /|\
  /

  YOU WIN!
  The word was: pizza

  Process finished with exit code 0
```

---

## 🧠 Explicação Didática Como se Fosse um Iniciante Amador em Java

### 1. Como o computador sabe que o jogador venceu?
Na forca, o jogo começa com 5 tracinhos: `[ '_', '_', '_', '_', '_' ]`.  
Conforme o jogador vai acertando, os tracinhos vão sendo trocados pelas letras reais:
* Chutou `'z'`: `[ '_', '_', 'z', 'z', '_' ]` $\rightarrow$ Ainda tem tracinho? **SIM!**
* Chutou `'i'`: `[ '_', 'i', 'z', 'z', '_' ]` $\rightarrow$ Ainda tem tracinho? **SIM!**
* Chutou `'a'`: `[ '_', 'i', 'z', 'z', 'a' ]` $\rightarrow$ Ainda tem tracinho? **SIM!**
* Chutou `'p'`: `[ 'p', 'i', 'z', 'z', 'a' ]` $\rightarrow$ Ainda tem tracinho? **NÃO TEM MAIS NENHUM!**

Quando **não houver mais nenhum sublinhado `'_'`**, significa que **todas as letras foram descobertas** e o jogador é o campeão!

---

### 2. O que faz o método `wordState.contains('_')`?
A classe `ArrayList` possui um método pronto e muito inteligente chamado `contains()`:
* Ele vasculha a lista inteira procurando o caractere passado entre parênteses.
* Se encontrar pelo menos um `'_'`, ele devolve **`true`** *(ainda faltam letras)*.
* Se não encontrar nenhum `'_'`, ele devolve **`false`** *(todas as letras foram achadas!)*.

---

### 3. O que é o ponto de exclamação `!` (Negação Lógica)?
Em Java, o símbolo `!` antes de uma condição significa **NÃO (inversão lógica)**:
* `wordState.contains('_')` significa: *"A lista contém sublinhado?"*
* `!wordState.contains('_')` significa: *"A lista **NÃO** contém mais nenhum sublinhado?"*

Portanto:
```java
if(!wordState.contains('_'))
```
Só será executado quando a palavra estiver 100% preenchida!

---

### 4. Por que colocar essa checagem DENTRO do bloco de acerto?
Repare na estrutura do código:
* A checagem de vitória foi colocada **dentro do `if(word.indexOf(guess) >= 0)`**, logo após o laço `for` que atualiza as letras.
* **Por quê?** Porque um jogador **só pode vencer a partida logo após fazer um palpite CORRETO**!
* Não faz sentido checar se ele venceu quando ele acabou de cometer um erro no bloco `else`. Isso economiza processamento e mantém o código perfeitamente estruturado.

---

### 5. O que faz o comando `break;` (Imagem 3)?
Nos capítulos anteriores, vimos o `break` sendo usado para não vazar casos dentro de um `switch`.  
Mas dentro de um laço de repetição (`while` ou `for`), o comando `break` tem um superpoder:
> **O `break` arromba e encerra o laço na mesma hora!**

Sem o `break;`, o laço `while(wrongGuesses < 6)` continuaria rodando, porque `wrongGuesses` ainda seria menor que 6, fazendo o jogo pedir outra letra mesmo após a vitória.  
Com o `break;`, o Java **sai imediatamente do `while`** e finaliza a partida com honras!

---

### 6. Por que o `GAME OVER` não é executado quando o jogador vence?
Veja como o código pós-loop foi construído na etapa anterior:
```java
if(wrongGuesses >= 6){
    System.out.print(getHangmanArt(wrongGuesses));
    System.out.println("GAME OVER!");
    System.out.println("The word was: " + word);
}
```
Se o jogador venceu:
* Na Imagem 5, ele venceu com 5 erros (`wrongGuesses = 5`).
* O `break` jogou a execução para a linha 55.
* O Java avalia: `wrongGuesses >= 6` $\rightarrow$ `5 >= 6`? **FALSO!**
* Como é falso, o bloco de `GAME OVER!` é completamente ignorado! O programa vai direto para `scanner.close()` e termina perfeitamente.

---

## 📜 Código-Fonte Completo e Atualizado ([`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java))

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

                if(!wordState.contains('_')){
                    System.out.print(getHangmanArt(wrongGuesses));
                    System.out.println("YOU WIN!");
                    System.out.println("The word was: " + word);
                    break;
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

## 🔬 Análise Linha por Linha do Bloco de Vitória

### **Linha 46: `if(!wordState.contains('_')){`**
* **O que faz:** Verifica se a lista `wordState` não possui mais nenhum sublinhado `'_'`.
* **Se tiver tracinho:** Retorna `false` e o jogo continua normalmente.
* **Se NÃO tiver tracinho:** Retorna `true` e entra no bloco de comemoração.

---

### **Linha 47: `System.out.print(getHangmanArt(wrongGuesses));`**
* **O que faz:** Desenha no console o estado final do boneco no momento da vitória, mostrando visualmente quantas vidas o jogador conseguiu preservar.

---

### **Linha 48: `System.out.println("YOU WIN!");`**
* **O que faz:** Imprime a mensagem triunfal de vitória no console.
* *Console:* `YOU WIN!`

---

### **Linha 49: `System.out.println("The word was: " + word);`**
* **O que faz:** Confirma com o jogador qual era a palavra completa.
* *Console:* `The word was: pizza`

---

### **Linha 50: `break;`**
* **O que faz:** Interrompe imediatamente o laço `while(wrongGuesses < 6)` e salta para a linha 58 (fora do laço).

---

## 🖥️ Saída Exata no Terminal na Vitória

```text
Guess a letter: p
Correct guess!

 o
/|\
/

YOU WIN!
The word was: pizza

Process finished with exit code 0
```

*(O núcleo de regras da forca está 100% completo e à prova de falhas: o jogador pode vencer e pode perder! Agora, na próxima etapa do vídeo, o instrutor vai conectar o arquivo de texto `words.txt` e o `BufferedReader` para sortear palavras aleatórias em vez de jogar sempre com "pizza"!)*
