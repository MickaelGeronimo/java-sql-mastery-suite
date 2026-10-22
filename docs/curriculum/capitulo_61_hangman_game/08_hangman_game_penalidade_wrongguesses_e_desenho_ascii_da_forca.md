# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 8: Penalidade de Erros com `wrongGuesses++` e Exibição Dinâmica do Boneco na Forca

---

## 🧭 Visão Geral Desta Etapa

Nesta oitava parte do **Capítulo 61**, o instrutor Bro Code resolve o primeiro grande furo que identificamos na etapa anterior: **o jogador não sofria nenhuma consequência quando errava uma letra**!

Aqui acompanhamos a ligação perfeita entre a lógica do jogo e a arte visual:
1. **Incremento de Erros (Imagem 1)**: Dentro do bloco `else`, adicionamos `wrongGuesses++;`. Agora, cada palpite incorreto consome uma das 6 vidas do jogador!
2. **Integração da Arte ASCII no Game Loop (Imagem 2)**: No topo do laço `while`, chamamos `System.out.print(getHangmanArt(wrongGuesses));`.
3. **O Boneco Invisível Inicial (Imagem 3)**: Na primeira rodada (`wrongGuesses = 0`), o método imprime as 3 linhas vazias do `case 0` (o instrutor destaca esse espaço no terminal).
4. **Primeiro Erro ao Vivo (Imagem 4)**: Ao chutar `'x'`, `wrongGuesses` vira `1`. Na rodada seguinte, a cabeça `o` do boneco aparece desenhada sobre a palavra!
5. **Acúmulo de Erros (Imagem 5)**: Após vários palpites errados (`'b'`, `'n'`), `wrongGuesses` atinge `4`, e o terminal desenha a cabeça e os dois braços do boneco (`o` e `/|\`). O perigo de enforcamento torna-se real e visível!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: ADICIONANDO wrongGuesses++ NO BLOCO ELSE (TIMESTAMP 44:32 DO CURSO)
====================================================================================================
Editor de Código (Main.java):
  43:         else{
  44:             wrongGuesses++;  <-- [Soma 1 a cada erro cometido!]
  45:             System.out.println("Wrong guess!\n");
  46:         }

====================================================================================================
IMAGEM 2: CHAMANDO getHangmanArt NO INÍCIO DO while (TIMESTAMP 24:1 DO CURSO)
====================================================================================================
Editor de Código (Main.java):
  23:         while(wrongGuesses < 6){
  24: 
  25:             System.out.print(getHangmanArt(wrongGuesses));  <-- [Desenha a forca atual!]
  26: 
  27:             System.out.print("Word: ");

====================================================================================================
IMAGEM 3: ESTÁGIO 0 — AS 3 LINHAS VAZIAS DA FORCA SEM ERROS
====================================================================================================
Console "Run" (O instrutor destaca a faixa azul vazia entre o banner e "Word:"):
  ************************
  Welcome to Java Hangman!
  ************************
  ████████████████████████  <-- [Área de 3 linhas em branco impressas pelo case 0]
  Word: _ _ _ _ _ 
  Guess a letter: █

====================================================================================================
IMAGEM 4: 1º ERRO COM 'x' — A CABEÇA 'o' APARECE NO CONSOLE (TIMESTAMP 10:00:22)
====================================================================================================
Console "Run" (Terminal inferior):
  Word: _ _ _ _ _ 
  Guess a letter: x
  Wrong guess!

   o   <-- [CABEÇA DESENHADA! wrongGuesses subiu de 0 para 1!]


  Word: _ _ _ _ _ 
  Guess a letter: █

====================================================================================================
IMAGEM 5: 4º ERRO — CABEÇA E DOIS BRAÇOS APARECEM NA TELA
====================================================================================================
Console "Run" (Terminal inferior após vários erros com 'b' e 'n'):
  Word: _ _ _ _ _ 
  Guess a letter: b
  Wrong guess!

   o
  /|\  <-- [CABEÇA, TRONCO E DOIS BRAÇOS! wrongGuesses = 4!]


  Word: _ _ _ _ _ 
  Guess a letter: n
```

---

## 🧠 Explicação Didática Como se Fosse um Iniciante Amador em Java

### 1. O que faz o comando `wrongGuesses++;` (Imagem 1)?
O operador `++` é o **operador de incremento** do Java. Ele significa exatamente:
```java
wrongGuesses = wrongGuesses + 1;
```
* No início do jogo, `wrongGuesses` vale `0`.
* Se o jogador chutar `'x'` (que não existe em `"pizza"`), o Java entra no bloco `else` e executa `wrongGuesses++`.
* O valor passa para `1`.
* Se errar de novo, vai para `2`, depois `3`, `4`, `5` e `6`.
* Quando chegar em `6`, a condição do laço `while(wrongGuesses < 6)` finalmente dará **`false`** (`6 < 6` é falso!), e o laço do jogo vai parar!

---

### 2. Por que colocar `System.out.print(getHangmanArt(wrongGuesses));` no início do `while`?
Pense em como você joga forca na vida real com papel e caneta:
1. Você olha para o papel e vê o desenho atual da forca.
2. Você olha para os tracinhos das letras descobertas.
3. Você pensa e diz: *"Eu chuto a letra B!"*.

Colocando o `getHangmanArt(wrongGuesses)` logo na primeira linha dentro do `while`, garantimos que **a cada rodada o desenho do boneco é atualizado antes de pedir a nova letra**!

---

### 3. Por que usamos `System.out.print` e NÃO `System.out.println`?
Repare com atenção na Linha 25:
```java
System.out.print(getHangmanArt(wrongGuesses)); // Usou print, SEM ln!
```
Por que não usar `println`?  
Porque no método `getHangmanArt`, cada desenho foi feito usando **Text Blocks** (`""" ... """`). Os blocos de texto do Java já incluem automaticamente as quebras de linha no final de cada desenho!  
Se usássemos `println`, o Java adicionaria **mais uma quebra de linha extra desnecessária**, empurrando a palavra `Word:` muito para baixo.

---

### 4. O que o Instrutor Demonstrou na Imagem 3 com a Barra Azul?
Na Imagem 3, antes do jogador fazer qualquer palpite, o terminal mostrou um espaço vazio entre os asteriscos e a palavra:
```text
************************
Welcome to Java Hangman!
************************



Word: _ _ _ _ _ 
Guess a letter: 
```
O instrutor selecionou com o mouse essas linhas em branco para explicar:
> *"Vejam só, o nosso método `getHangmanArt(wrongGuesses)` já está funcionando desde o primeiro segundo! Como `wrongGuesses` começou com zero, ele caiu no `case 0`, que devolve 3 linhas vazias. Essas linhas reservam o espaço exato onde o boneco vai nascer conforme o jogador for errando!"*

---

### 5. A Evolução do Boneco Durante o Jogo
* **Na Imagem 4**: O jogador digitou `'x'`. Como `'x'` é erro, `wrongGuesses` virou `1`. O laço recomeçou e chamou `getHangmanArt(1)`. O terminal desenhou a cabeça `o`!
* **Na Imagem 5**: O jogador foi errando outras letras como `'b'` e `'n'`. Quando `wrongGuesses` chegou em `4`, chamou `getHangmanArt(4)` e desenhou a cabeça e os dois braços:
  ```text
   o
  /|\
  ```

---

## 📜 Código-Fonte Atualizado ([`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java))

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

## 🔬 Análise Linha por Linha das Alterações

### **Linha 25: `System.out.print(getHangmanArt(wrongGuesses));`**
* **O que faz:** Invoca a função `getHangmanArt` passando o número de erros atual (`wrongGuesses`).
* **Se `wrongGuesses == 0`:** Imprime as linhas em branco de espera.
* **Se `wrongGuesses == 1`:** Imprime a cabeça `o`.
* **Se `wrongGuesses == 4`:** Imprime o corpo com os dois braços `/|\`.
* *Saída no console:* O desenho ASCII correspondente ao estágio da forca.

---

### **Linha 46: `wrongGuesses++;`**
* **O que faz:** Adiciona `1` à variável contadora de erros toda vez que o palpite não existir na palavra secreta.
* **Efeito na memória:** Garante que o jogador tem apenas 6 vidas antes do `while` encerrar.
* *Saída no console:* Nenhuma (operação de memória).

---

## 🖥️ Saída Exata no Terminal Após 1 Erro (`x`)

```text
************************
Welcome to Java Hangman!
************************
Word: _ _ _ _ _ 
Guess a letter: x
Wrong guess!

 o


Word: _ _ _ _ _ 
Guess a letter: █
```

*(Agora que os erros são punidos e a forca é desenhada dinamicamente na tela, o jogo só precisa de mais duas coisas para ficar perfeito: a condição de vitória para parar o loop quando a palavra for completada e a mensagem final de Game Over quando o boneco for totalmente enforcado!)*
