# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 2: Demonstração Dinâmica, Banner de Boas-Vindas e Desenho ASCII da Forca com Switch Expression e Text Blocks

---

## 🧭 Visão Geral Desta Etapa

Nesta segunda parte do **Capítulo 61**, o instrutor Bro Code avança na construção do jogo da forca com passos cruciais:
1. **Prova da Dinâmica da Lista (Imagens 1 e 2)**: Ele demonstra como a lógica criada na Parte 1 com `word.length()` e `wordState.add('_')` não está engessada. Ao trocar `"pizza"` por `"watermelon"`, o console passa automaticamente de 5 para 10 tracinhos `[_, _, _, _, _, _, _, _, _, _]`.
2. **Criação do Banner de Boas-Vindas (Imagem 3)**: Adiciona as mensagens de cabeçalho no console com moldura de asteriscos.
3. **Criação da Arte ASCII do Boneco na Forca (Imagens 3, 4 e 5)**: Constrói um método auxiliar modular chamado `getHangmanArt(int wrongGuesses)`.
4. **Recursos Modernos do Java Utilizados**:
   * **Switch Expressions** (`return switch(...) { ... };`): O `switch` moderno com sintaxe de seta (`->`) que devolve um valor diretamente.
   * **Text Blocks** (`""" ... """`): Strings multilinhas com aspas triplas, preservando quebras de linha e espaçamento.
   * **Escape de Caractere (`\\`)**: Como desenhar a barra invertida do braço e da perna sem tomar erro do compilador.
   * **Resolução de Erro de Digitação**: O instrutor comete um erro comum ao vivo (`Duplicate label '5'`) e o corrige para `case 6`.

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Cópia no Google Docs)

Abaixo está a transcrição textual exata de cada tela do IntelliJ IDEA e do terminal para você manter o contexto completo em seu documento:

```
====================================================================================================
IMAGEM 1: TESTE COM PALAVRA "pizza" (5 LETRAS)
====================================================================================================
Editor de Código (Main.java):
   9:         String word = "pizza";
   ...
Console "Run" (Saída com cursor do mouse sobre o terceiro sublinhado):
  [_, _, _, _, _]

  Process finished with exit code 0

====================================================================================================
IMAGEM 2: TESTE DINÂMICO COM "watermelon" (TIMESTAMP 9:46:42)
====================================================================================================
Editor de Código (Main.java):
   9:         String word = "watermelon";
   ...
Console "Run" (O instrutor seleciona com o mouse os 10 sublinhados gerados dinamicamente):
  [_, _, _, _, _, _, _, _, _, _]

  Process finished with exit code 0

====================================================================================================
IMAGEM 3: BANNER DE BOAS-VINDAS E INÍCIO DO MÉTODO getHangmanArt (TIMESTAMP 9:49:03)
====================================================================================================
Editor de Código (Main.java):
  19:         System.out.println("************************");
  20:         System.out.println("Welcome to Java Hangman!");
  21:         System.out.println("************************");
  22: 
  23:         scanner.close();
  24:     }
  25: 
  26:     static String getHangmanArt(int wrongGuesses){
  27: 
  28:         return switch(wrongGuesses){
  29:             case 0 -> """
  30:                       """;
  31: 
  32:         };
  33:     }

====================================================================================================
IMAGEM 4: CONSTRUÇÃO DOS CASOS 4 E 5 COM ERRO DE DUPLICIDADE (TIMESTAMP 9:50:13)
====================================================================================================
Editor de Código (Main.java):
  48:             case 4 -> """
  49:                        o
  50:                       /|\\
  51:                       """;
  52: 
  53:             case 5 -> """
  54:                        o
  55:                       /|\\
  56:                       /
  57:                       """;
  58: 
  59:             case 5 -> """  <-- [Sublinhado em vermelho: Duplicate label '5']
  60:                        o
  61:                       /|\\
  62:                       /
  63:                       """;

====================================================================================================
IMAGEM 5: CORREÇÃO DO CASE 6 E DEFAULT COMPLETO (TIMESTAMP 9:50:27)
====================================================================================================
Editor de Código (Main.java):
  53:             case 5 -> """
  54:                        o
  55:                       /|\\
  56:                       /
  57:                       """;
  58: 
  59:             case 6 -> """  <-- [Corrigido para 6! Boneco completo com 2 pernas]
  60:                        o
  61:                       /|\\
  62:                       / \\
  63:                       """;
  64:             default -> "";
  65:         };
  66:     }
  67: }
```

---

## 🧠 Explicação Didática Para Iniciantes Amadores em Java

### 1. Por que testar com `"watermelon"` (Melancia)?
Quando um iniciante cria um jogo da forca, é muito tentador fazer algo fixo do tipo:
*"Ah, minha palavra tem 5 letras, então vou colocar 5 tracinhos na mão."*  
Mas e quando a palavra mudar para `"banana"` (6 letras) ou `"watermelon"` (10 letras)?  
Bro Code mostra que o laço:
```java
for(int i = 0; i < word.length(); i++){
    wordState.add('_');
}
```
Não se importa com o tamanho da palavra. Se a palavra tiver 3 letras (`"cat"`), ele cria 3 tracinhos `[_, _, _]`. Se tiver 10 letras (`"watermelon"`), ele cria 10 tracinhos `[_, _, _, _, _, _, _, _, _, _]`. Isso se chama **código genérico e dinâmico**!

---

### 2. Por que criar um método separado `getHangmanArt`?
Imagine se colocássemos todas essas dezenas de linhas de bonequinhos ASCII dentro do método `main`. O `main` ficaria com mais de 100 linhas, uma confusão gigante de ler!  
Na programação limpa, dividimos responsabilidades:
* O método `main` cuida do **fluxo do jogo** (pedir letra, contar pontos, verificar vitória).
* O método `getHangmanArt` cuida exclusivamente do **desenho do boneco**.

---

### 3. Anatomia da Declaração do Método
```java
static String getHangmanArt(int wrongGuesses)
```
* **`static`**: Como estamos chamando este método a partir do `main` (que é `static`), o método também precisa ser `static`. Métodos estáticos pertencem à classe [`Main`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) e não precisam de `new Main()` para rodar.
* **`String`**: É o tipo de retorno. Quando chamamos `getHangmanArt(3)`, ele nos "devolve" um texto (`String`) com o boneco correspondente.
* **`getHangmanArt`**: Nome do método ("obter arte da forca").
* **`(int wrongGuesses)`**: É o parâmetro de entrada. Ele recebe um número inteiro (quantos palpites errados o jogador tem no momento: 0, 1, 2, 3, 4, 5 ou 6).

---

### 4. O que é uma Switch Expression (`return switch`)?
No Java clássico (antigo), o `switch` funcionava assim:
```java
// JEITO ANTIGO (Mais longo e propenso a esquecer break)
switch(wrongGuesses){
    case 0: return "";
    case 1: return " o ";
    ...
}
```
No Java moderno (Java 14+), temos a **Switch Expression**:
```java
return switch(wrongGuesses){
    case 0 -> """
              ...
              """;
    case 1 -> """
              ...
              """;
    default -> "";
};
```
* Usamos a seta `->` em vez de dois pontos `:`.
* **Não precisa de `break`** (não existe perigo de vazar para o caso de baixo!).
* O switch inteiro é uma **expressão que produz um resultado**, por isso podemos colocar `return` antes de `switch`.
* **Atenção ao ponto e vírgula `};`**: como toda a estrutura `return switch(...) { ... };` é um comando de retorno em Java, ela obrigatoriamente termina com ponto e vírgula `;` após a chave de fechamento.

---

### 5. O que são Text Blocks (`"""`)?
Antes do Java 15, para fazer um texto com várias linhas em Java, você tinha que escrever:
```java
"   o   \n" +
"  /|\\  \n" +
"  / \\  \n"
```
Isso era horrível e cheio de `\n` e `+`.  
Com os **Text Blocks** (três aspas duplas `"""`), você pode simplesmente pular linha no teclado! O Java respeita exatamente as quebras de linha e os espaços visuais que você digitou.

---

### 6. O Mistério da Barra Invertida Dupla: Por que `/|\\` e não `/|\`?
No Java, o caractere barra invertida `\` é o **caractere de escape**. Ele avisa ao compilador: *"Ei Java, o próximo caractere tem um significado especial!"*:
* `\n` = pula linha
* `\t` = tecla Tab
* `\"` = aspa dupla dentro de texto

Se você digitar apenas uma barra invertida no final do braço `/|\`, o Java acha que você quer criar um comando especial `\"` com a aspa de fechamento, o que quebra todo o código com o erro:  
`Illegal escape character in string literal`.

> [!TIP]
> **Como imprimir uma barra invertida real?**  
> Para imprimir **uma** barra invertida (`\`), você deve digitar **duas** barras invertidas (`\\`)! A primeira barra avisa: *"Java, escape a próxima barra e mostre uma barra comum!"*. Por isso digitamos `/|\\` para aparecer `/|\` na tela!

---

### 7. O Erro do Instrutor ao Vivo: `Duplicate label '5'`
Na Imagem 4, ao copiar e colar, o instrutor deixou dois blocos com `case 5`:
```java
case 5 -> """ ... """;
case 5 -> """ ... """; // ERRO!
```
O IntelliJ IDEA imediatamente sublinhou o segundo `5` de vermelho. Em um `switch`, cada rótulo (`case`) deve ser único! O computador não saberia qual dos dois blocos executar se `wrongGuesses` fosse igual a 5. Na Imagem 5, ele corrigiu o segundo para `case 6`.

---

## 🎨 Tabela Visual da Forca (Evolução dos Erros de 0 a 6)

Veja como o boneco evolui a cada palpite errado:

| Erros (`wrongGuesses`) | Parte Adicionada | Arte ASCII Produzida | Status |
| :---: | :---: | :---: | :---: |
| **0** | Nenhuma | *(três linhas em branco)* | Vivo e ileso |
| **1** | Cabeça | ` o ` | 1º erro |
| **2** | Tronco | ` o `<br>` \| ` | 2º erro |
| **3** | Braço Esquerdo | ` o `<br>`/\| ` | 3º erro |
| **4** | Braço Direito | ` o `<br>`/\|\` | 4º erro |
| **5** | Perna Esquerda | ` o `<br>`/\|\`<br>`/ ` | Quase enforcado |
| **6** | Perna Direita | ` o `<br>`/\|\`<br>`/ \` | **GAME OVER** (Enforcado!) |

---

## 📜 Código-Fonte Atualizado do Projeto ([`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java))

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

### **Linhas 19 a 21: O Banner de Abertura**
```java
19: System.out.println("************************");
20: System.out.println("Welcome to Java Hangman!");
21: System.out.println("************************");
```
* **Linha 19:** Imprime uma linha superior de 24 asteriscos para criar um cabeçalho decorativo.  
  *Saída no console:* `************************`
* **Linha 20:** Imprime a mensagem de boas-vindas do jogo.  
  *Saída no console:* `Welcome to Java Hangman!`
* **Linha 21:** Imprime a linha inferior de 24 asteriscos para fechar a moldura.  
  *Saída no console:* `************************`

---

### **Linha 25: `static String getHangmanArt(int wrongGuesses){`**
* **O que faz:** Define a função/método responsável por gerar a arte ASCII da forca.
* **`static`**: Permite ser chamada diretamente pelo `main`.
* **`String`**: O método retorna um texto contendo o boneco.
* **`wrongGuesses`**: Recebe o número de erros (0 a 6).
* *Saída no console:* Nenhuma (declaração de método).

---

### **Linha 27: `return switch(wrongGuesses){`**
* **O que faz:** Inicia a Switch Expression. O Java avalia o valor numérico de `wrongGuesses` e pula diretamente para o `case` correspondente, retornando o resultado.
* *Saída no console:* Nenhuma.

---

### **Linhas 28 a 32: `case 0 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 0 erros, devolve 3 linhas vazias (nenhuma parte do corpo desenhada).
* *Saída no console:* Nenhuma.

---

### **Linhas 33 a 37: `case 1 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 1 erro, devolve o texto com apenas a cabeça `o` e duas linhas vazias abaixo.
* *Saída no console:* Nenhuma.

---

### **Linhas 38 a 42: `case 2 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 2 erros, devolve a cabeça `o` e o tronco `|`.
* *Saída no console:* Nenhuma.

---

### **Linhas 43 a 47: `case 3 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 3 erros, devolve a cabeça `o` e o braço esquerdo conectado ao tronco `/|`.
* *Saída no console:* Nenhuma.

---

### **Linhas 48 a 52: `case 4 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 4 erros, devolve a cabeça e ambos os braços `/|\`.
* **Atenção:** Escrito como `/|\\` no código devido ao escape de string em Java.
* *Saída no console:* Nenhuma.

---

### **Linhas 53 a 57: `case 5 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 5 erros, devolve a cabeça, os dois braços e a perna esquerda `/`.
* *Saída no console:* Nenhuma.

---

### **Linhas 58 a 62: `case 6 -> """ ... """;`**
* **O que faz:** Se o jogador tiver 6 erros (limite máximo de vidas), devolve o corpo completo: cabeça, tronco, dois braços e as duas pernas `/ \`.
* **Atenção:** A perna direita usa escape `\\`.
* *Saída no console:* Nenhuma.

---

### **Linha 63: `default -> "";`**
* **O que faz:** Cláusula de segurança e completude. Se por algum motivo o método receber um número diferente de 0 a 6 (ex: `-1` ou `99`), ele retorna uma `String` vazia `""` em vez de travar o programa.
* *Saída no console:* Nenhuma.

---

### **Linha 64: `};`**
* **O que faz:** Fecha o bloco da Switch Expression com ponto e vírgula obrigatório.
* *Saída no console:* Nenhuma.

---

### **Linha 65: `}`**
* **O que faz:** Fecha o corpo do método `getHangmanArt`.
* *Saída no console:* Nenhuma.

---

## 🖥️ Saída Exata Obtida no Terminal Nesta Etapa

Ao compilar e rodar a classe [`Main`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) agora:

```text
************************
Welcome to Java Hangman!
************************
```

*(O método `getHangmanArt` já está compilado e pronto na memória da classe, aguardando para ser chamado dentro do laço principal do jogo na próxima etapa!)*
