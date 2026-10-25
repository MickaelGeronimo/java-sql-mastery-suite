# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 4: Formatação dos Tracinhos com For-Each, Diferença entre `print` e `println` e Captura do Palpite com `charAt(0)`

---

## 🧭 Visão Geral Desta Etapa

Nesta quarta parte do **Capítulo 61**, o instrutor Bro Code transforma o jogo da forca em uma experiência visual polida e interativa:
1. **Da Saída Bruta para a Interface do Jogo (Imagens 1 e 2)**: Abandonamos o formato de depuração `[_, _, _, _, _]` e usamos um **Enhanced For Loop (for-each)** para exibir `Word: _ _ _ _ _ ` com espaçamento elegante.
2. **Correção ao Vivo no Vídeo**: O instrutor inicialmente digita `System.out.println` (que quebrava uma linha para cada tracinho) e imediatamente corrige para `System.out.print` (mantendo todos na mesma linha horizontal).
3. **Quebra de Linha de Separação (Imagem 3)**: Adiciona `System.out.println();` para impedir que o próximo texto fique grudado nos tracinhos.
4. **Captura e Higienização do Palpite (Imagens 3, 4 e 5)**: Cria a linha clássica de leitura de caractere em Java:  
   `char guess = scanner.next().toLowerCase().charAt(0);`
5. **Teste Interativo no Console**: Digita a letra `'z'` e confirma que o caractere foi lido e armazenado com sucesso na variável `guess`.

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Cópia no Google Docs)

Abaixo está a reconstituição textual fiel de cada captura de tela do IntelliJ IDEA e do terminal:

```
====================================================================================================
IMAGEM 1: DIGITANDO O LOOP FOR-EACH COM println (TIMESTAMP INICIAL)
====================================================================================================
Editor de Código (Main.java):
  23:         System.out.print("Word: ");
  24: 
  25:         for(char c : wordState){
  26:             System.out.println(c + " ");  <-- [Usou println inicialmente por engano]
  27:         }
  28: 
  29:         scanner.close();

====================================================================================================
IMAGEM 2: CORREÇÃO PARA System.out.print E EXECUÇÃO (TIMESTAMP 9:53:25)
====================================================================================================
Editor de Código (Main.java):
  25:         for(char c : wordState){
  26:             System.out.print(c + " ");   <-- [Corrigido para print!]
  27:         }

Console "Run" (Terminal mostrando os tracinhos horizontais perfeitos):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 

  Process finished with exit code 0

====================================================================================================
IMAGEM 3: QUEBRA DE LINHA E LEITURA DO PALPITE COM charAt(0) (TIMESTAMP 9:54:41)
====================================================================================================
Editor de Código (Main.java):
  28:         System.out.println();
  29: 
  30:         System.out.print("Guess a letter: ");
  31:         char guess = scanner.next().toLowerCase().charAt(0);

====================================================================================================
IMAGEM 4: TESTE DE IMPRESSÃO DO PALPITE — AGUARDANDO DIGITAÇÃO (TIMESTAMP 9:54:58)
====================================================================================================
Editor de Código (Main.java):
  33:         System.out.println(guess);

Console "Run" (Terminal pausado aguardando o usuário digitar uma letra):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: █  <-- [Cursor piscando no console aguardando entrada do teclado]

====================================================================================================
IMAGEM 5: DIGITANDO 'z' E CONCLUINDO O TESTE (TIMESTAMP 9:55:08)
====================================================================================================
Console "Run" (O instrutor digita 'z', pressiona Enter e a letra é ecoada na tela):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: z
  z

  Process finished with exit code 0
```

---

## 🧠 Explicação Didática Para Iniciantes Amadores em Java

### 1. Por que abandonamos `System.out.println(wordState);`?
Na etapa anterior, quando imprimíamos o `wordState`, o terminal mostrava:
```text
[_, _, _, _, _]
```
Para quem está programando, isso é ótimo para depurar e ver que o `ArrayList` tem 5 itens. Mas para o **usuário comum que vai jogar seu jogo**, isso é feio e artificial! Um jogo de verdade não mostra colchetes `[` `]` nem vírgulas.  
Ele deve mostrar assim:
```text
Word: _ _ _ _ _ 
```
Para conseguir essa formatação limpa, precisamos percorrer a lista elemento por elemento.

---

### 2. O que é o Laço For-Each (`for(char c : wordState)`)?
Este laço especial do Java é chamado de **Enhanced For Loop** (ou laço for-each).  
Ele é lido em português da seguinte forma:
> *"Para cada caractere `c` contido dentro da lista `wordState`, faça o seguinte..."*

* Não precisamos controlar contadores manuais como `i = 0`, `i < tamanho`, `i++`.
* O Java pega automaticamente o primeiro item da lista, coloca na variável temporária `c` e roda o bloco. Depois pega o segundo, o terceiro, até o último!
* **Autounboxing em Ação:** Lembre-se de que o `wordState` guarda objetos da classe `Character`. Quando você declara `char c` (com `c` minúsculo), o Java desempacota automaticamente o objeto para o tipo primitivo `char` nos bastidores!

---

### 3. A Diferença Fundamental entre `print` e `println`
Veja o erro que o próprio Bro Code cometeu na Imagem 1:
* Se você usar **`System.out.println(c + " ")`**:
  O `ln` significa *"line new"* (nova linha). Após imprimir cada tracinho, o cursor do computador pula para a linha de baixo! O resultado ficaria uma escadinha vertical bizarra:
  ```text
  Word: _ 
  _ 
  _ 
  _ 
  _ 
  ```
* Se você usar **`System.out.print(c + " ")`** (sem o `ln`):
  O cursor permanece na **mesma linha**! Ele imprime o primeiro `_ `, depois o segundo `_ `, o terceiro, o quarto e o quinto, formando a linha horizontal perfeita:
  ```text
  Word: _ _ _ _ _ 
  ```

---

### 4. Por que a Linha `System.out.println();` (Vazia) é Obrigatória?
Repare na Linha 28:
```java
System.out.println();
```
Como o laço anterior usou `System.out.print`, o cursor do console ficou parado exatamente no final do quinto tracinho.  
Se você chamasse o próximo comando direto (`System.out.print("Guess a letter: ")`), o console ficaria todo emendado e confuso:
```text
Word: _ _ _ _ _ Guess a letter: 
```
Um `System.out.println();` vazio funciona como apertar a **tecla Enter** no teclado: ele simplesmente move o cursor para o começo da linha seguinte!

---

### 5. O Grande Segredo da Linha 31: Por que `scanner.next().toLowerCase().charAt(0)`?
Esta é uma das perguntas mais clássicas de quem estuda Java:  
*"Por que raios não existe um comando simples como `scanner.nextChar()`?"*

> [!NOTE]
> Os criadores do Java incluíram no `Scanner` métodos para quase tudo: `nextInt()`, `nextDouble()`, `nextBoolean()`, `nextLine()`, mas **NÃO CRIARAM `nextChar()`**!

Para ler uma única letra do teclado, os programadores Java usam a técnica de **Encadeamento de Métodos (Method Chaining)**. Vamos dissecar o que acontece em 3 passos sucessivos:

```
Passo 1: scanner.next()
         Lê a palavra digitada pelo usuário como uma String.
         Exemplo: Usuário digita "Z" -> Retorna a String "Z".

Passo 2: .toLowerCase()
         Transforma todo o texto em letras minúsculas.
         Exemplo: "Z" vira "z".
         Por que isso é vital? Nossa palavra secreta é "pizza" (tudo minúsculo).
         Se o jogador digitar 'Z' maiúsculo e compararmos direto, o Java diria
         que 'Z' é diferente de 'z' por causa da tabela ASCII! Com toLowerCase,
         o jogo aceita maiúsculas e minúsculas sem erro!

Passo 3: .charAt(0)
         Pega o caractere localizado no índice 0 (a PRIMEIRA letra da String)
         e devolve como o tipo primitivo char.
         Exemplo: Da String "z", ele extrai o caractere 'z'.
         Proteção extra: Se o usuário digitar por engano uma palavra inteira
         como "zebra", o charAt(0) pega apenas a primeira letra 'z' e ignora o resto!
```

---

## 📜 Código-Fonte Completo e Atualizado ([`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java))

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

        System.out.print("Word: ");

        for(char c : wordState){
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.print("Guess a letter: ");
        char guess = scanner.next().toLowerCase().charAt(0);

        System.out.println(guess);

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

## 🔬 Análise Linha por Linha das Linhas Novas e Modificadas

### **Linha 23: `System.out.print("Word: ");`**
* Imprime o rótulo `"Word: "` sem pular linha. O cursor fica aguardando os tracinhos que virão a seguir.
* *Console:* `Word: `

---

### **Linhas 25 a 27: O Loop For-Each dos Tracinhos**
```java
        for(char c : wordState){
            System.out.print(c + " ");
        }
```
* Percorre cada caractere da lista `wordState` (que contém 5 sublinhados `'_'`).
* Em cada volta, imprime o caractere seguido de um espaço em branco `" "`.
* Como usa `print` (sem `ln`), todos os tracinhos aparecem na mesma linha horizontal.
* *Console:* `_ _ _ _ _ ` (completando a linha: `Word: _ _ _ _ _ `).

---

### **Linha 28: `System.out.println();`**
* Não imprime texto algum, apenas quebra a linha atual (pula para a próxima linha).
* *Console:* O cursor pula da linha do `Word: ` para a linha de baixo.

---

### **Linha 30: `System.out.print("Guess a letter: ");`**
* Imprime a instrução para o jogador escolher uma letra.
* Como usa `print`, o cursor fica esperando exatamente após o espaço depois dos dois pontos `: `.
* *Console:* `Guess a letter: `

---

### **Linha 31: `char guess = scanner.next().toLowerCase().charAt(0);`**
* Pausa o programa e aguarda o jogador digitar uma tecla e pressionar Enter.
* Converte a entrada para minúscula e extrai o primeiro caractere como `char`.
* Salva o caractere na variável `guess`.
* *Console:* Mostra a letra digitada pelo usuário (ex: `z`).

---

### **Linha 33: `System.out.println(guess);`**
* Imprime no terminal o valor que foi armazenado dentro da variável `guess`.
* Usado pelo instrutor como teste temporário para confirmar que a leitura do teclado funcionou.
* *Console:* `z`

---

### **Linha 35: `scanner.close();`**
* Fecha o `Scanner`, liberando a porta de entrada do teclado do sistema operacional.
* *Console:* Nada.

---

## 🖥️ Saída Exata no Terminal ao Executar Este Passo

Quando você roda o programa e digita a letra `z`:

```text
************************
Welcome to Java Hangman!
************************
Word: _ _ _ _ _ 
Guess a letter: z
z
```

*(Agora que conseguimos exibir a palavra mascarada de forma elegante e ler com precisão o palpite do jogador, o próximo passo lógico será verificar se a letra chutada existe dentro de `"pizza"`, revelar os tracinhos correspondentes ou desenhar um pedaço do boneco na forca!)*
