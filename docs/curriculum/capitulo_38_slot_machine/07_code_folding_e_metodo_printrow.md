# ☕ Curso de Java — Capítulo 38: `⭐ slot machine 🎰` (Parte 7)
## O Truque do Code Folding na IDE, o Nascimento do Método `printRow()` e a Elegância do `String.join(" | ", row)`!

> **Baseado na aula do canal Bro Code (Minuto `6:30:00` a `6:30:26` de 12:00:00)**  
> **Tema:** O professor ensina um recurso essencial de produtividade da IDE IntelliJ: o **Code Folding** (recolhimento de código), dobrando o método `spinRow()` em uma única linha `{...}` para limpar a tela. Em seguida, implementa o passo 15 do blueprint criando o método especialista **`static void printRow(String[] row)`**. Ele remove a impressão feia e espremida da aula anterior e ensina um dos métodos mais modernos e elegantes do Java: **`String.join(" | ", row)`**, formatando os 3 rolos com barras verticais perfeitas de cassino (` 🍉 | ⭐ | 🍋 `) e garantindo a quebra de linha com `println`!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 3 Imagens (Para o Google Docs)](#1-contexto-visual-minucioso-das-3-imagens-para-o-google-docs)
2. [O que o Professor Mostrou e Explicou Nesta Etapa](#2-o-que-o-professor-mostrou-e-explicou-nesta-etapa)
3. [Dica de Mestre da IDE: O que é Code Folding (Recolhimento de Código)?](#3-dica-de-mestre-da-ide-o-que-é-code-folding-recolhimento-de-código)
4. [A Anatomia do Método `static void printRow(String[] row)`](#4-a-anatomia-do-método-static-void-printrowstring-row)
5. [A Mágica do `String.join(" | ", row)` Explicada para Iniciantes](#5-a-mágica-do-stringjoin---row-explicada-para-iniciantes)
6. [O Fim do Texto Grudado: O Papel do `println`](#6-o-fim-do-texto-grudado-o-papel-do-println)
7. [O Código Java Completo, Numerado e Atualizado](#7-o-código-java-completo-numerado-e-atualizado)
8. [Explicação Linha por Linha Cirúrgica](#8-explicação-linha-por-linha-cirúrgica)
9. [Como Fica a Nova Saída Visual do Cassino no Console](#9-como-fica-a-nova-saída-visual-do-cassino-no-console)
10. [Tabela Mental Resumida e Próximos Passos](#10-tabela-mental-resumida-e-próximos-passos)

---

## 1. Contexto Visual Minucioso das 3 Imagens (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está a reconstituição minuciosa em texto de tudo o que acontece visualmente na tela da IDE IntelliJ IDEA nas 3 imagens enviadas:

### 📸 Imagem 1 (`media_1791246029800.png`): O Clique na Setinha de Dobradura (Code Folding)
* **O que vemos:** Um close-up (zoom) da calha esquerda do editor de código na linha 47.
* **O Ícone:** Vemos o número da linha `47`, o símbolo de arroba circular `@` e uma **setinha apontando para baixo `\/`**.
* **O Cursor do Mouse:** O cursor em formato de mãozinha branca com o dedo indicador estendido está clicando exatamente em cima da setinha `\/`.
* **A Função:** Essa setinha serve para "fechar/dobrar" blocos de código que já estão prontos, escondendo seu conteúdo para não poluir a visão do programador.

---

### 📸 Imagem 2 (`media_1791246037136.png`): A Limpeza de `spinRow()` Antes de Dobrar
* **No editor de código (`Main.java`):**
  * O método `spinRow()` ainda está aberto exibindo suas 11 linhas (linhas 47 a 58).
  * **O que foi apagado:** A antiga linha 57 que imprimia `System.out.print(row[0] + row[1] + row[2]);` foi **completamente deletada**! A linha 57 agora está vazia.
  * O método `spinRow()` agora só faz o seu trabalho puro: sorteia os 3 símbolos e os devolve com `return row;` na linha 58.
  * O mouse do professor está sobre a setinha da linha 47, prestes a executar a dobradura.

---

### 📸 Imagem 3 (`media_1791246145087.png` — Minuto `6:30:26`): O Método Dobrado e o Novo `printRow()`!
* **Barra de tempo do vídeo:** `6:30:26 / 12:00:00` | Capítulo: `38.slot machine >`.
* **A Mágica da Dobradura (Linha 48):**
  ```java
  48 @ > static String[] spinRow(){...}
  ```
  O método inteiro foi comprimido em uma única linha horizontal limpa com uma caixinha cinza `{...}`!
* **O Novo Método Criado (Linhas 60 a 62):**
  ```java
  static void printRow(String[] row){
      System.out.println(" " + String.join(" | ", row));
  }
  ```
* **A Anotação da IDE:** Acima de `printRow`, na linha 60, o IntelliJ exibe em cinza: **`1 usage`**, confirmando que este método já está sendo chamado dentro do loop `main`!

---

## 2. O que o Professor Mostrou e Explicou Nesta Etapa

O professor resolve de forma definitiva o problema estético da aula anterior e dá uma aula de boas práticas:

1. **Cada Macaco no Seu Galho (Princípio de Responsabilidade Única):**  
   * `spinRow()` deve **apenas sortear** os símbolos. Não é função dele imprimir na tela.
   * `printRow()` deve **apenas desenhar** a linha na tela com estilo e elegância.
2. **Organização Visual na IDE:**  
   Programadores profissionais recolhem métodos prontos para que o arquivo não fique com 500 linhas abertas ao mesmo tempo.
3. **A Introdução do `String.join()`:**  
   Em vez de fazer concatenações manuais chatas e cheias de `+` e aspas, ele ensina a ferramenta nativa do Java feita sob medida para unir arrays com separadores.

---

## 3. Dica de Mestre da IDE: O que é Code Folding (Recolhimento de Código)?

No IntelliJ IDEA (e na maioria das IDEs modernas como VS Code e Eclipse), ao lado esquerdo de qualquer classe, método ou loop, existe uma pequena setinha:

```
Antes de Clicar:
47  static String[] spinRow(){
48      String[] symbols = ...;
49      String[] row = ...;
50      for(...) { ... }
51      return row;
52  }

Depois de Clicar na Setinha:
47  static String[] spinRow(){...}
```

* **O código foi apagado?** **NÃO!** Ele continua 100% lá e a JVM continua executando normalmente.
* **Para que serve?** É como fechar uma gaveta do armário depois de guardar as roupas. Serve apenas para deixar a tela limpa e você focar no método que está construindo agora!
* Se você quiser ver o código de novo, basta clicar na caixinha `{...}` ou na setinha que ela se abre instantaneamente.

---

## 4. A Anatomia do Método `static void printRow(String[] row)`

Vamos dissecar a assinatura da linha 60:

```java
static void printRow(String[] row)
```

1. **`static`:** Permite que o método seja chamado diretamente pelo `main`.
2. **`void` (Vazio):**  
   * Preste muita atenção aqui! `spinRow()` tinha `String[]` porque precisava devolver um array para o `main`.
   * `printRow()` tem **`void`** porque ele **NÃO precisa devolver nada**! O único objetivo dele na vida é jogar texto na tela do console e acabar!
3. **`printRow`:** O nome do método (*Imprimir Linha*).
4. **`(String[] row)` (Parâmetro):**  
   Para desenhar os rolos na tela, o método precisa receber a caixinha com os 3 símbolos sorteados. Quem passa essa caixinha é o `main` quando faz: `printRow(row);`.

---

## 5. A Mágica do `String.join(" | ", row)` Explicada para Iniciantes

Observe a linha 61:
```java
System.out.println(" " + String.join(" | ", row));
```

### O Jeito Antigo e Feio de Fazer (Sem `String.join`):
```java
System.out.println(" " + row[0] + " | " + row[1] + " | " + row[2]);
```
* Fica comprido, fácil de errar aspas e se o array tivesse 10 símbolos você enlouqueceria digitando.

### O Jeito Moderno e Elegante (Com `String.join`):
O método estático `String.join(delimitador, array)` faz exatamente isso:
1. Pega cada item de `row`: `row[0]`, `row[1]` e `row[2]`.
2. Cola o texto `" | "` **apenas entre eles**!
3. Não coloca barra sobrando na frente nem atrás!

```
Array:        ["🍉",       "⭐",       "🍋"]
                   \         / \         /
Delimitador:        " | "       " | "
                   ↓           ↓
Resultado:    "🍉" + " | " + "⭐" + " | " + "🍋"
               ===> "🍉 | ⭐ | 🍋"
```

E com o `" " + ` no começo:
👉 **`" 🍉 | ⭐ | 🍋"`**

---

## 6. O Fim do Texto Grudado: O Papel do `println`

Na aula anterior, os símbolos saíram colados no saldo:
```text
🍉⭐🍋Current balance: $99   <-- FEIO E ESMAGADO!
```

Agora, como o professor usou **`System.out.println`** (com `ln`), acontece o seguinte:
1. Imprime os 3 rolos com as barras divisórias: ` 🍉 | ⭐ | 🍋`.
2. O `ln` pula para a linha de baixo imediatamente!
3. Quando a próxima linha do jogo rodar, o saldo aparecerá bonito e isolado na linha seguinte:
```text
 🍉 | ⭐ | 🍋
Current balance: $99        <-- LINDO E PROFISSIONAL!
```

---

## 7. O Código Java Completo, Numerado e Atualizado

Aqui está o arquivo [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) completo e funcional:

```java
1:  import java.util.Random;
2:  import java.util.Scanner;
3:  
4:  public class Main {
5:      public static void main(String[] args){
6:  
7:          // JAVA SLOT MACHINE
8:  
9:          // DECLARE VARIABLES
10:         Scanner scanner = new Scanner(System.in);
11:         int balance = 100;
12:         int bet;
13:         int payout;
14:         String[] row;
15: 
16:         // DISPLAY WELCOME MESSAGE
17:         System.out.println("*************************");
18:         System.out.println("  Welcome to Java Slots  ");
19:         System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐ ");
20:         System.out.println("*************************");
21: 
22:         // PLAY IF BALANCE > 0
23:         while(balance > 0){
24:             System.out.println("Current balance: $" + balance);
25:             System.out.print("Place your bet amount: ");
26:             bet = scanner.nextInt();
27: 
28:             // VERIFY IF BET > BALANCE
29:             if(bet > balance){
30:                 System.out.println("INSUFFICIENT FUNDS");
31:                 continue;
32:             }
33:             // VERIFY IF BET > 0
34:             else if(bet <= 0){
35:                 System.out.println("Bet must be greater than 0");
36:                 continue;
37:             }
38:             // SUBTRACT BET FROM BALANCE
39:             else{
40:                 balance -= bet;
41:             }
42: 
43:             System.out.println("Spinning...");
44: 
45:             // SPIN ROW
46:             row = spinRow();
47: 
48:             // PRINT ROW
49:             printRow(row);
50: 
51:             // GET PAYOUT
52:             // ASK TO PLAY AGAIN
53:         }
54: 
55:         // DISPLAY EXIT MESSAGE
56: 
57:         scanner.close();
58:     }
59: 
60:     static String[] spinRow(){
61: 
62:         String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐"};
63:         String[] row = new String[3];
64:         Random random = new Random();
65: 
66:         for(int i = 0; i < 3; i++){
67:             row[i] = symbols[random.nextInt(symbols.length)];
68:         }
69: 
70:         return row;
71:     }
72: 
73:     static void printRow(String[] row){
74:         System.out.println(" " + String.join(" | ", row));
75:     }
76: }
```

---

## 8. Explicação Linha por Linha Cirúrgica

### No Método `main`:
* **Linha 46 (`row = spinRow();`):** O `main` pede para o operador girar a roleta e recebe o array com 3 símbolos.
* **Linha 49 (`printRow(row);`):** O `main` entrega a caixinha `row` para o método `printRow`, que desenha os rolos na tela.

---

### No Método `printRow` (Linhas 73 a 75):
```java
73:     static void printRow(String[] row){
74:         System.out.println(" " + String.join(" | ", row));
75:     }
```
* **Linha 73:** Declara o método estático sem retorno (`void`), que aceita um array de Strings como parâmetro chamado `row`.
* **Linha 74:** Executa a junção dos 3 elementos com a divisória `" | "`, adiciona um espaço inicial e imprime com quebra de linha.

---

## 9. Como Fica a Nova Saída Visual do Cassino no Console

Veja a diferença gritante de elegância quando você joga com o novo método:

```text
*************************
  Welcome to Java Slots  
Symbols: 🍒 🍉 🍋 🔔 ⭐ 
*************************
Current balance: $100
Place your bet amount: 1
Spinning...
 🍉 | ⭐ | 🍋
Current balance: $99
Place your bet amount: 
```

Agora sim! A linha ` 🍉 | ⭐ | 🍋` tem espaçamento, barras verticais e não gruda em nenhum outro texto!

---

## 10. Tabela Mental Resumida e Próximos Passos

| Método | Tipo de Retorno | Parâmetros Recebidos | Missão no Jogo |
| :---: | :---: | :---: | :--- |
| **`main`** | `void` | `String[] args` | Gerencia o fluxo financeiro, loop e chamadas |
| **`spinRow`** | `String[]` | Nenhum `()` | Sorteia os 3 emojis no loop `for` e devolve o array |
| **`printRow`** | `void` | `String[] row` | Formata e imprime a linha com `String.join(" | ", row)` |

### O que vem a seguir (Passo 16 do Blueprint: `// GET PAYOUT`):
Agora que os 3 rolos giram e são exibidos com perfeição na tela, o jogo precisa calcular os prêmios!
O professor vai criar o método:
```java
static int getPayout(String[] row, int bet)
```
para verificar se o jogador tirou 3 símbolos iguais (ex: `🍒 🍒 🍒` ou `⭐ ⭐ ⭐`) e pagar os prêmios multiplicados!
