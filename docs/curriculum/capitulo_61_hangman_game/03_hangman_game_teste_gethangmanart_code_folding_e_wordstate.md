# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 3: Testando a Forca ASCII, Code Folding no IntelliJ e Exibição do `wordState`

---

## 🧭 Visão Geral Desta Etapa

Nas 5 novas imagens deste passo do **Capítulo 61**, o instrutor Bro Code realiza um procedimento padrão que todo bom programador faz antes de avançar: **testar individualmente o que acabou de construir**!

Nesta aula, vemos:
1. **Dica de Produtividade no IntelliJ IDEA (Code Folding)**: O instrutor recolhe/dobra o método `getHangmanArt` para que o código de 40 linhas do boneco não ocupe espaço na tela e não atrapalhe a visão do `main`.
2. **Teste do `getHangmanArt(0)` (Imagem 2)**: Passando `0` como argumento, ele verifica se a função retorna 3 linhas em branco (jogador ainda não errou nada).
3. **Teste do `getHangmanArt(3)` e `getHangmanArt(4)` (Imagens 3 e 4)**: Passando `4`, o terminal imprime a cabeça e os dois braços (`o` e `/|\`), provando que a Switch Expression está funcionando perfeitamente.
4. **Restauração do Fluxo do Jogo (Imagem 5)**: O instrutor remove o teste do boneco e coloca a impressão do `wordState` logo abaixo do banner de boas-vindas, gerando a visualização inicial do jogo: `[_, _, _, _, _]`.

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: MÉTODO getHangmanArt RECOLHIDO (CODE FOLDING) NO INTELLIJ (TIMESTAMP 9:50:42)
====================================================================================================
Editor de Código (Main.java):
  11:         Scanner scanner = new Scanner(System.in);
  12:         ArrayList<Character> wordState = new ArrayList<>();
  13:         int wrongGuesses = 0;
  14: 
  15:         for(int i = 0; i < word.length(); i++){
  16:             wordState.add('_');
  17:         }
  18: 
  19:         System.out.println("************************");
  20:         System.out.println("Welcome to Java Hangman!");
  21:         System.out.println("************************");
  22: 
  23:         System.out.println();
  24: 
  25:         scanner.close();
  26:     }
  27: @ > static String getHangmanArt(int wrongGuesses){...}  <-- [Método dobrado de 27 a 68!]
  68: }

====================================================================================================
IMAGEM 2: TESTANDO getHangmanArt(0) COM ZERO ERROS (TIMESTAMP 9:50:49)
====================================================================================================
Editor de Código (Main.java):
  23:         System.out.println(getHangmanArt(0));

Console "Run" (Terminal inferior):
  ************************
  Welcome to Java Hangman!
  ************************
  
  
  

  Process finished with exit code 0

====================================================================================================
IMAGEM 3: DIGITANDO ARGUMENTO 3 (TIMESTAMP 9:51:08)
====================================================================================================
Editor de Código (Main.java):
  23:         System.out.println(getHangmanArt(3));  <-- [Cursor alterando o valor para 3]

====================================================================================================
IMAGEM 4: TESTANDO getHangmanArt(4) COM 4 ERROS (TIMESTAMP 9:51:11)
====================================================================================================
Editor de Código (Main.java):
  23:         System.out.println(getHangmanArt(4));

Console "Run" (Terminal inferior mostrando a cabeça e os dois braços):
  ************************
  Welcome to Java Hangman!
  ************************
   o
  /|\


  Process finished with exit code 0

====================================================================================================
IMAGEM 5: RETORNO AO ESTADO DO JOGO COM wordState (TIMESTAMP 9:51:30)
====================================================================================================
Editor de Código (Main.java):
  24:         System.out.println(wordState);

Console "Run" (Banner seguido dos 5 tracinhos da palavra "pizza"):
  ************************
  Welcome to Java Hangman!
  ************************
  [_, _, _, _, _]

  Process finished with exit code 0
```

---

## 🧠 Explicação Didática Como se Fosse um Iniciante Amador em Java

### 1. O que é o "Code Folding" (Dobragem de Código) na Imagem 1?
Na Imagem 1, repare que na linha 27 aparece:
```java
static String getHangmanArt(int wrongGuesses){...}
```
E a linha seguinte já pula direto para a **linha 68**!  
Onde foram parar as linhas 28 a 67?  
O IntelliJ IDEA possui um recurso chamado **Code Folding** (Dobragem de Código). Ao lado do número da linha existe uma setinha pequena (`v` ou `>`).
* Quando uma função ou classe é muito grande (como o nosso método de arte ASCII com 40 linhas), você pode clicar na setinha para **encolher** o método em uma única linha `{...}`.
* O código continua existindo perfeitamente para o computador; ele só fica "escondido" da tela para não cansar seus olhos e deixar o método `main` limpo para você trabalhar!

---

### 2. Por que o Instrutor Fez Testes Manuais com `getHangmanArt(0)` e `getHangmanArt(4)`?
Quando um programador cria uma função nova, ele não espera o jogo inteiro ficar pronto para ver se ela funciona.  
Ele faz o que chamamos de **Teste de Sanidade (Sanity Check)**:
1. **Chamou `getHangmanArt(0)`**:
   * O Java passou o número `0` para o parâmetro `wrongGuesses`.
   * O `switch` caiu no `case 0 -> """ ... """;`.
   * O console imprimiu apenas linhas vazias.
   * **Conclusão:** Perfeito! Se o jogador não errou nada, nenhum pedaço do corpo deve aparecer!
2. **Chamou `getHangmanArt(4)`**:
   * O Java passou o número `4` para o parâmetro `wrongGuesses`.
   * O `switch` caiu no `case 4 -> """ ... """;`.
   * O console imprimiu a cabeça e os dois braços:
     ```text
      o
     /|\
     ```
   * **Conclusão:** Perfeito! O escape duplo da barra invertida `/|\\` funcionou e gerou `/|\` no terminal!

---

### 3. Por que na Imagem 5 ele Troca o Teste por `System.out.println(wordState)`?
Os testes com números fixos (`0` e `4`) eram apenas para garantir que a arte ASCII estava desenhada corretamente.  
Agora que sabemos que ela funciona, o jogo de verdade precisa mostrar para o jogador o estado inicial da palavra secreta:
```text
************************
Welcome to Java Hangman!
************************
[_, _, _, _, _]
```
Dessa forma, o jogador sabe que a palavra tem 5 letras e que nenhuma letra foi descoberta ainda!

---

## 📜 Código-Fonte Atual ([`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java))

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

        System.out.println(wordState);

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

## 🔬 Análise Linha por Linha das Alterações e Saídas de Console

### **Linha 19: `System.out.println("************************");`**
* Imprime a moldura superior de asteriscos.
* *Console:* `************************`

### **Linha 20: `System.out.println("Welcome to Java Hangman!");`**
* Imprime a mensagem de boas-vindas do jogo.
* *Console:* `Welcome to Java Hangman!`

### **Linha 21: `System.out.println("************************");`**
* Imprime a moldura inferior de asteriscos.
* *Console:* `************************`

### **Linha 23 (Durante os Testes das Imagens 2 e 4):**
* Quando rodou `System.out.println(getHangmanArt(0));`:
  * Invoca `getHangmanArt` com argumento `0`.
  * Retorna 3 quebras de linha vazias.
  * *Console:* Linhas em branco.
* Quando rodou `System.out.println(getHangmanArt(4));`:
  * Invoca `getHangmanArt` com argumento `4`.
  * Retorna a cabeça e os dois braços.
  * *Console:*
    ```text
     o
    /|\
    ```

### **Linha 24 (Código Final da Imagem 5): `System.out.println(wordState);`**
* Imprime a lista dinâmica `wordState` que contém 5 sublinhados criados no loop `for`.
* *Console:* `[_, _, _, _, _]`

### **Linha 26: `scanner.close();`**
* Encerra o leitor de teclado.
* *Console:* Nada.

---

## 🖥️ Saída Exata no Terminal ao Final Desta Etapa

```text
************************
Welcome to Java Hangman!
************************
[_, _, _, _, _]
```

*(O jogo agora tem seu cabeçalho pronto, o banco de arte ASCII validado e pronto na memória, e os 5 tracinhos da palavra exibidos na tela. O próximo passo será criar o loop que pede os palpites do jogador!)*
