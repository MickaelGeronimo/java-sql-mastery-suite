# ☕ Curso de Java — Capítulo 38: `⭐ slot machine 🎰` (Parte 3)
## O Teste de Estresse das Apostas no Console e a Criação do Método Modular `static String[] spinRow()`!

> **Baseado na aula do canal Bro Code (Minuto `6:22:46` a `6:23:30` de 12:00:00)**  
> **Tema:** O professor executa no console o teste de estresse das apostas (apostando 1 bilhão e -1 para provar que o sistema é indestrutível contra trapaças, e depois apostando $10 para ver o saldo cair para $90). Em seguida, ele fecha o terminal e ensina uma das lições mais importantes de arquitetura em Java: **dividir o código em métodos auxiliares**! Fora do método `main`, ele cria a função `static String[] spinRow()`, define o catálogo de símbolos (`symbols`), instancia o vetor de 3 posições (`row`), cria o gerador `Random` e usa um retorno temporário para compilar sem erros enquanto desenvolve a roleta!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 3 Imagens (Para o Google Docs)](#1-contexto-visual-minucioso-das-3-imagens-para-o-google-docs)
2. [O que o Professor Mostrou e Explicou Nesta Etapa](#2-o-que-o-professor-mostrou-e-explicou-nesta-etapa)
3. [Análise das 3 Apostas de Teste no Console (Imagens 1 e 2)](#3-análise-das-3-apostas-de-teste-no-console-imagens-1-e-2)
4. [A Grande Virada Arquitetural: O que é o Método `spinRow()`?](#4-a-grande-virada-arquitetural-o-que-é-o-método-spinrow)
5. [Anatomia da Assinatura: `static String[] spinRow()`](#5-anatomia-da-assinatura-static-string-spinrow)
6. [O Truque Profissional do `return new String[0];`](#6-o-truque-profissional-do-return-new-string0)
7. [O Código Java Completo, Numerado e Atualizado](#7-o-código-java-completo-numerado-e-atualizado)
8. [Explicação Linha por Linha Cirúrgica](#8-explicação-linha-por-linha-cirúrgica)
9. [Tabela Mental Resumida e Próximos Passos](#9-tabela-mental-resumida-e-próximos-passos)

---

## 1. Contexto Visual Minucioso das 3 Imagens (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA nas 3 imagens enviadas:

### 📸 Imagem 1 (`media_1791245356459.png` — Minuto `6:22:46`): O Teste de Estresse das Apostas Inválidas
* **Barra de tempo do vídeo:** `6:22:46 / 12:00:00` | Capítulo: `38.slot machine >`.
* **Na janela de execução (Terminal Run):**
  ```text
  *************************
    Welcome to Java Slots  
  Symbols: 🍒 🍉 🍋 🔔 ⭐ 
  *************************
  Current balance: $100
  Place your bet amount: 1000000000
  INSUFFICIENT FUNDS
  Current balance: $100
  Place your bet amount: -1
  Bet must be greater than 0
  Current balance: $100
  Place your bet amount: 
  ```
* **O que aconteceu:**
  1. O professor digitou uma aposta absurda de **`1000000000`** *(1 bilhão de dólares)*. Como o jogador só tem $100, o Java disparou **`INSUFFICIENT FUNDS`** e reiniciou a rodada sem descontar nada.
  2. Em seguida, ele testou o número negativo **`-1`**. O Java disparou **`Bet must be greater than 0`** e reiniciou a rodada.
  3. O saldo permaneceu firme e forte em **`$100`**, e o cursor está aguardando a terceira aposta.

---

### 📸 Imagem 2 (`media_1791245368159.png` — Minuto `6:22:54`): A Aposta Válida de $10 e o Fechamento do Console
* **Barra de tempo do vídeo:** `6:22:54 / 12:00:00` (8 segundos depois).
* **No Console do Terminal:**
  ```text
  Current balance: $100
  Place your bet amount: 10
  $90
  Current balance: $90
  Place your bet amount: 
  ```
* **O que aconteceu:**
  * O professor digitou **`10`**.
  * Como 10 é maior que 0 e menor que 100, o bloco `else` cobrou a aposta com `balance -= bet;`.
  * A linha provisória imprimiu: **`$90`**.
  * O loop `while(balance > 0)` deu uma nova volta e anunciou: **`Current balance: $90`**!
* **A Ação do Professor no IntelliJ:** No topo esquerdo da aba Run, a setinha do mouse clica no ícone de fechar (**`x`**) da aba `Main` para encerrar a janela do terminal, pois o teste financeiro foi um sucesso estrondoso!

---

### 📸 Imagem 3 (`media_1791245411510.png`): O Novo Método `spinRow()` no Editor
* **No Editor de Código (`Main.java`):**
  * O professor desceu o editor para além do fechamento do método `main` (após a linha 46).
  * Na linha 47, ele escreveu um novo método independente:
    ```java
    static String[] spinRow(){

        String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐"};
        String[] row = new String[3];
        Random random = new Random();

        System.out.println(symbols[0]);

        return new String[0];
    }
    ```
  * O cursor do mouse está piscando após a cereja `"🍒"` na linha 49.
  * Acima do método, na linha 47, o IntelliJ exibe em cinza claro a anotação: **`1 usage`**, indicando que este método já foi chamado dentro do `main`!

---

## 2. O que o Professor Mostrou e Explicou Nesta Etapa

Neste trecho da aula, passamos de um código puramente sequencial para uma **programação estruturada e modular**:

1. **A Prova Real da Robustez Financeira:** O professor provou na prática que o loop e as instruções `continue;` criadas na Parte 2 protegem o jogo contra qualquer tipo de entrada bizarra ou maliciosa.
2. **A Criação de Métodos Especializados:** Em vez de jogar toda a lógica de sorteio da roleta dentro do método `main` (o que deixaria o código uma "linguiça" gigante e confusa), o professor cria uma função dedicada chamada `spinRow()`.
3. **A Separação de Responsabilidades:**  
   * O método `main` cuida do fluxo do jogo (dinheiro, apostas e mensagens).
   * O método `spinRow` cuidará exclusivamente da física da roleta (embaralhar os símbolos e devolver 3 figuras sorteadas).

---

## 3. Análise das 3 Apostas de Teste no Console (Imagens 1 e 2)

| Teste | Entrada do Usuário | Condição Acionada | Saída Impressa | Saldo Após o Teste | Comportamento do Java |
| :---: | :---: | :---: | :---: | :---: | :--- |
| **1º** | `1000000000` | `bet > balance` | `INSUFFICIENT FUNDS` | `$100` (intacto) | `continue;` pula a subtração e volta ao topo |
| **2º** | `-1` | `bet <= 0` | `Bet must be greater than 0` | `$100` (intacto) | `continue;` pula a subtração e volta ao topo |
| **3º** | `10` | `else` (Válida) | `$90` | `$90` (descontado) | Cobra $10 e o `while` recomeça com saldo 90! |

---

## 4. A Grande Virada Arquitetural: O que é o Método `spinRow()`?

Até agora, quase tudo o que fizemos ficava dentro do bloco `public static void main(String[] args)`.

```
public class Main {
    
    public static void main(String[] args){
        // O GERENTE DO CASSINO
        // Recebe o jogador, cobra a aposta e gerencia o dinheiro.
        // Quando precisa girar a roleta, chama o especialista:
        row = spinRow(); 
    }

    static String[] spinRow(){
        // O OPERADOR DA ROLETA
        // Sua única função na vida é sortear 3 símbolos e entregar para o gerente!
    }
}
```

---

## 5. Anatomia da Assinatura: `static String[] spinRow()`

Vamos dissecar palavra por palavra da linha 47 para quem está começando:

```java
static String[] spinRow()
```

1. **`static` (Estático):**  
   Em Java, o método `main` é estático (`static`). Para que o `main` consiga chamar outro método diretamente pelo nome sem precisar criar um objeto com `new Main()`, esse método auxiliar também **deve ser declarado como `static`**!
2. **`String[]` (Tipo de Retorno):**  
   É o "contrato" do método com o compilador Java:  
   * *"Eu, método `spinRow`, prometo solenemente à JVM que, quando terminar meu trabalho, vou devolver uma caixinha contendo um **array de Strings** (`String[]`)!"*.
3. **`spinRow` (Nome da Função):**  
   Significa *"Girar Linha"*. Usa o padrão oficial do Java chamado **camelCase** (primeira palavra minúscula, segunda com inicial maiúscula).
4. **`()` (Parênteses):**  
   Estão vazios porque o operador da roleta não precisa de nenhuma informação externa para girar; ele tem seus próprios símbolos lá dentro.

---

## 6. O Truque Profissional do `return new String[0];`

Na linha 55, vemos:
```java
return new String[0];
```

### Por que o professor escreveu isso se o método ainda não está pronto?
* Em Java, quando você declara que um método retorna algo (como `String[]`), o compilador é **implacável**: se não houver um comando `return` devolvendo um array, o código **não compila** e fica sublinhado de vermelho com o erro fatal:  
  👉 **`missing return statement`** *(falta instrução de retorno)*.
* Como o professor ainda está construindo a lógica de sorteio passo a passo, ele colocou provisoriamente `return new String[0];` (que devolve um array vazio de tamanho zero).
* **Objetivo:** Acalmar o compilador para que o código continue verde e executável enquanto ele programa o restante!

---

## 7. O Código Java Completo, Numerado e Atualizado

Aqui está o arquivo [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) completo com a importação de `Random`, a chamada no `main` e o novo método:

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
43:             // SPIN ROW
44:             row = spinRow();
45: 
46:             // PRINT ROW
47:             // GET PAYOUT
48:             // ASK TO PLAY AGAIN
49:         }
50: 
51:         // DISPLAY EXIT MESSAGE
52: 
53:         scanner.close();
54:     }
55: 
56:     static String[] spinRow(){
57: 
58:         String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐"};
59:         String[] row = new String[3];
60:         Random random = new Random();
61: 
62:         System.out.println(symbols[0]);
63: 
64:         return new String[0];
65:     }
66: }
```

---

## 8. Explicação Linha por Linha Cirúrgica

### No Método `main`:
* **Linha 1 (`import java.util.Random;`):** Necessário agora para podermos usar a classe `Random` dentro do método `spinRow`.
* **Linha 44 (`row = spinRow();`):** O `main` aciona o método `spinRow()`. O resultado que a roleta devolver será guardado na variável `row`.

---

### No Método `spinRow()` (Linhas 56 a 65):
```java
56:     static String[] spinRow(){
```
* Abre o método estático que devolverá um vetor de Strings com as 3 figuras sorteadas.

```java
58:         String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐"};
```
* **O que faz:** Cria o catálogo dos 5 símbolos possíveis no caça-níquel:
  * `symbols[0]` = `"🍒"`
  * `symbols[1]` = `"🍉"`
  * `symbols[2]` = `"🍋"`
  * `symbols[3]` = `"🔔"`
  * `symbols[4]` = `"⭐"`

```java
59:         String[] row = new String[3];
```
* **O que faz:** Cria um vetor vazio de **3 posições** (`[null, null, null]`). Este vetor representará os 3 visores da máquina caça-níquel!

```java
60:         Random random = new Random();
```
* **O que faz:** Cria a ferramenta geradora de números aleatórios para sortear os índices de `0` a `4`.

```java
62:         System.out.println(symbols[0]);
```
* **O que faz:** Uma linha de teste rápido escrita pelo professor para verificar se o emoji `"🍒"` é impresso com sucesso no console ao rodar a função.

```java
64:         return new String[0];
```
* **O que faz:** Um retorno provisório de um array vazio para satisfazer o compilador enquanto a lógica definitiva de sorteio dos 3 rolos é implementada.

---

## 9. Tabela Mental Resumida e Próximos Passos

| Elemento | Onde Fica? | Função Principal |
| :---: | :---: | :--- |
| **`main`** | Linhas 5 a 54 | O cérebro do jogo: gerencia saldo, apostas, repetição e encerramento. |
| **`spinRow`** | Linhas 56 a 65 | O motor da roleta: sorteia e devolve os 3 emojis da rodada. |
| **`symbols`** | Dentro de `spinRow` | Os 5 emojis oficiais do cassino. |
| **`row`** | Dentro de `spinRow` | O array de 3 posições que receberá as 3 figuras sorteadas. |
| **`random`** | Dentro de `spinRow` | O sorteador randômico da roleta. |

### O que vem a seguir:
Na próxima etapa, o professor vai substituir o teste temporário por um **loop `for`** dentro de `spinRow()`, sorteando aleatoriamente um símbolo para `row[0]`, `row[1]` e `row[2]` com `random.nextInt(symbols.length)`!
