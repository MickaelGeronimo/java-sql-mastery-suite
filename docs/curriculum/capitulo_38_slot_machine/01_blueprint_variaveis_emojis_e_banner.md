# ☕ Curso de Java — Capítulo 38: `⭐ slot machine 🎰` (Parte 1)
## O Novo Projeto: A Arquitetura do Caça-Níquel de Cassino, o Saldo Inicial (`$100`), o Segredo dos Emojis no Teclado e o Banner de Boas-Vindas!

> **Baseado na aula do canal Bro Code (Minuto `6:15:28` a `6:19:43` de 12:00:00)**  
> **Tema:** Começamos o novo e fascinante projeto do curso: uma **Slot Machine (Máquina Caça-Níquel de Cassino)** em Java! O professor apaga o projeto anterior, projeta o blueprint completo da aplicação com todas as regras de negócio de apostas e prêmios, ensina como inserir Emojis reais do teclado no código Java (`🍒 🍉 🍋 🔔 ⭐`), declara as variáveis de saldo (`balance = 100`), aposta (`bet`), pagamento (`payout`), o array dos rolos (`String[] row`) e constrói um banner de boas-vindas com moldura perfeita de 25 asteriscos!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 5 Imagens (Para o Google Docs)](#1-contexto-visual-minucioso-das-5-imagens-para-o-google-docs)
2. [O que é uma Slot Machine (Caça-Níquel) e Qual é a Mecânica do Jogo?](#2-o-que-é-uma-slot-machine-caça-níquel-e-qual-é-a-mecânica-do-jogo)
3. [O Blueprint do Caça-Níquel: O Mapa das 10 Etapas](#3-o-blueprint-do-caça-níquel-o-mapa-das-10-etapas)
4. [O Segredo dos Emojis em Java: Como Abrir o Seletor no Windows e Mac](#4-o-segredo-dos-emojis-em-java-como-abrir-o-seletor-no-windows-e-mac)
5. [Análise das Novas Variáveis e Estruturas de Dados](#5-análise-das-novas-variáveis-e-estruturas-de-dados)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Linha por Linha Cirúrgica](#7-explicação-linha-por-linha-cirúrgica)
8. [A Saída no Console e a Simetria do Banner (25 Caracteres)](#8-a-saída-no-console-e-a-simetria-do-banner-25-caracteres)
9. [Resumo das Peças e Próximos Passos](#9-resumo-das-peças-e-próximos-passos)

---

## 1. Contexto Visual Minucioso das 5 Imagens (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está a reconstituição minuciosa em texto de tudo o que acontece visualmente na tela da IDE IntelliJ IDEA nas 5 telas enviadas:

### 📸 Imagem 1 (`media_1791245206047.png` — Minuto `6:15:28`): A Folha em Branco
* **Barra de tempo do vídeo:** `6:15:28 / 12:00:00` | Título do capítulo no YouTube: `38.slot machine >`.
* **No editor (`Main.java`):**
  * O código do jogo anterior de Pedra, Papel e Tesoura foi removido.
  * Temos apenas a estrutura padrão:
    ```java
    public class Main {
        public static void main(String[] args){

            // JAVA SLOT MACHINE

            // |
        }
    }
    ```
  * O cursor do mouse está piscando na linha 7 logo após as duas barras `// `, pronto para começar o planejamento.

---

### 📸 Imagem 2 (`media_1791245217366.png` — Minuto `6:17:15`): O Blueprint Completo
* **Barra de tempo do vídeo:** `6:17:15 / 12:00:00` (cerca de 1 minuto e meio depois).
* **O Masterplan Comentado:** O professor planejou todo o fluxo do cassino através de comentários:
  ```java
  // JAVA SLOT MACHINE

  // DECLARE VARIABLES
  // DISPLAY WELCOME MESSAGE
  // PLAY IF BALANCE > 0
  // ENTER BET AMOUNT
  //     VERIFY IF BET > BALANCE
  //     VERIFY IF BET > 0
  //     SUBTRACT BET FROM BALANCE
  // SPIN ROW
  // PRINT ROW
  // GET PAYOUT
  // ASK TO PLAY AGAIN
  // DISPLAY EXIT MESSAGE
  ```
* **O Destaque Azul (Linha 7):** A frase `// DECLARE VARIABLES` está selecionada com uma faixa azul retangular. O professor mostra que a primeira missão é definir quais gavetas de memória serão necessárias.

---

### 📸 Imagem 3 (`media_1791245228131.png` — Minuto `6:18:55`): O Pop-up de Emojis do Sistema Operacional
* **Barra de tempo do vídeo:** `6:18:55 / 12:00:00`.
* **O que surge no meio da tela:** Uma janela flutuante branca oficial do Windows chamada **"Emoji - Keep typing to find an emoji"** com uma grade de carinhas e símbolos (estrela, sino, limão, melancia, cereja, lâmpada, dado, etc.).
* **O Grande Letreiro Didático do Professor Bro Code:** Na parte inferior da tela, em letras garrafais brancas estilizadas, ele ensina o atalho para todo mundo:
  * **`Windows: Win + ;`** *(ou `Win + .`)*
  * **`Mac: Control-Command-Space bar`**
* **No código atrás do pop-up:** O professor já declarou `Scanner scanner`, `int balance = 100;`, `int bet;` e está escrevendo a linha `System.out.println("Symbols: ");`.

---

### 📸 Imagem 4 (`media_1791245238274.png` — Minuto `6:19:33`): As Variáveis e os 5 Emojis no Código
* **Barra de tempo do vídeo:** `6:19:33 / 12:00:00`.
* **No editor de código:**
  ```java
  Scanner scanner = new Scanner(System.in);
  int balance = 100;
  int bet;
  int payout;
  String[] row;

  System.out.println("*************************");
  System.out.println("  Welcome to Java Slots  ");
  System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐ ");
  ```
* Os 5 símbolos oficiais do caça-níquel foram inseridos diretamente na String:
  1. 🍒 Cereja (*Cherry*)
  2. 🍉 Melancia (*Watermelon*)
  3. 🍋 Limão (*Lemon*)
  4. 🔔 Sino de Ouro (*Bell*)
  5. ⭐ Estrela (*Star*)

---

### 📸 Imagem 5 (`media_1791245259459.png` — Minuto `6:19:43`): O Teste de Execução do Banner no Console
* **Barra de tempo do vídeo:** `6:19:43 / 12:00:00`.
* **Na linha 17 do editor:** O professor adicionou a borda inferior: `System.out.println("*************************");`.
* **Na janela Run (Terminal Inferior):** O programa rodou pela primeira vez!
  ```text
  *************************
    Welcome to Java Slots  
  Symbols: 🍒 🍉 🍋 🔔 ⭐ 
  *************************

  Process finished with exit code 0
  ```
* Os emojis foram renderizados perfeitamente com suas cores naturais no terminal, dentro da moldura dourada de asteriscos!

---

## 2. O que é uma Slot Machine (Caça-Níquel) e Qual é a Mecânica do Jogo?

Uma máquina caça-níquel clássica de Las Vegas funciona da seguinte forma:
1. O jogador chega no cassino com um **saldo de dinheiro** (no nosso jogo, começaremos com **$100**).
2. Ele escolhe um valor para apostar na rodada (`bet`), por exemplo, **$10**.
3. O cassino valida:
   * O jogador tem esse dinheiro? (Não pode apostar mais do que tem na carteira).
   * A aposta é positiva? (Não pode apostar $0 ou valores negativos como -$50).
4. O valor apostado é imediatamente deduzido do saldo (`balance = balance - bet`).
5. A máquina **gira 3 rolos independentes** (`spin row`), sorteando 3 símbolos aleatórios (ex: `🍒 🍒 🍒` ou `🍒 🍋 ⭐`).
6. Se os símbolos formarem combinações premiadas, a máquina paga um prêmio (`payout`) que é somado de volta ao saldo.
7. O jogador pode continuar jogando enquanto tiver saldo (`balance > 0`). Se o dinheiro acabar, ele é expulso da máquina!

---

## 3. O Blueprint do Caça-Níquel: O Mapa das 10 Etapas

O professor Bro Code ensina que programas complexos nunca devem ser feitos no improviso. Veja o roteiro estruturado na Imagem 2:

```
1. // DECLARE VARIABLES          -> Configurar as gavetas da memória (Scanner, saldo, aposta, etc.)
2. // DISPLAY WELCOME MESSAGE    -> Exibir a moldura estilizada do cassino e os símbolos
3. // PLAY IF BALANCE > 0        -> Loop principal: o jogo só roda se o jogador tiver dinheiro!
4. // ENTER BET AMOUNT           -> Pedir o valor da aposta
   //     VERIFY IF BET > BALANCE -> Bloquear apostas maiores que o saldo disponível
   //     VERIFY IF BET > 0       -> Bloquear apostas de zero ou valores negativos
   //     SUBTRACT BET FROM BALANCE -> Descontar a aposta da carteira
5. // SPIN ROW                   -> Girar os rolos e sortear 3 símbolos aleatórios
6. // PRINT ROW                  -> Exibir os 3 símbolos sorteados lado a lado
7. // GET PAYOUT                 -> Calcular quanto o jogador ganhou de acordo com os símbolos
8. // ASK TO PLAY AGAIN          -> Perguntar se quer girar a roleta mais uma vez
9. // DISPLAY EXIT MESSAGE       -> Mensagem de despedida e saldo final
```

---

## 4. O Segredo dos Emojis em Java: Como Abrir o Seletor no Windows e Mac

Muitos programadores iniciantes acham que em linguagens antigas como C ou Java clássico só dá para imprimir letras simples e números. **Isso é um mito!**

### Como os Emojis Funcionam no Java:
* Desde versões modernas, as `Strings` em Java usam codificação **Unicode (UTF-16)**.
* Cada emoji (🍒, 🍉, 🔔) é tratado internamente pelo Java como um caractere especial ou par de caracteres Unicode válido!
* Você pode colocá-los diretamente dentro das aspas duplas `" "` como se fossem letras comuns!

### Como Abrir a Janelinha de Emojis no seu Computador (Mostrado na Imagem 3):
* **No Windows:** Segure a tecla do logotipo do Windows e aperte ponto e vírgula:  
  👉 **`Win + ;`** ou **`Win + .`**
* **No Mac:** Segure Control e Command e aperte a barra de espaço:  
  👉 **`Ctrl + Cmd + Barra de Espaço`**
* Quando a janela abrir, basta clicar no emoji desejado e ele será colado no editor de código!

---

## 5. Análise das Novas Variáveis e Estruturas de Dados

Veja as variáveis criadas nas linhas 8 a 12:

```java
Scanner scanner = new Scanner(System.in);
int balance = 100;
int bet;
int payout;
String[] row;
```

```
[ MEMÓRIA STACK (Variáveis Locais) ]
┌─────────────────────────────────────────────────────────────┐
│ scanner   ──────> Aponta para o objeto Scanner no Heap      │
│ balance   = 100 (Saldo do jogador em dólares/moedas)        │
│ bet       = ?   (Valor que será apostado a cada rodada)     │
│ payout    = ?   (Prêmio que será ganho ao acertar símbolos) │
│ row       ──────> null (No futuro apontará para 3 símbolos) │
└─────────────────────────────────────────────────────────────┘
```

1. **`Scanner scanner`:** O aparelho que vai ler os valores das apostas e respostas do teclado.
2. **`int balance = 100;`:** O saldo da conta do apostador. Começa em **100**. Como é um número inteiro, usamos o tipo primitivo `int`.
3. **`int bet;`:** A aposta (*bet*). Declarada sem valor inicial porque o valor virá do teclado a cada rodada.
4. **`int payout;`:** O prêmio (*payout*). Guardará o valor devolvido pela máquina caso o jogador tire símbolos vencedores.
5. **`String[] row;`:** Um **array de Strings** chamado `row` (linha). No caça-níquel real, os rolos giram e mostram uma linha de 3 símbolos. Esse vetor guardará, por exemplo: `["🍒", "🍒", "🍒"]`.

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) exatamente como montado e executado pelo professor na Imagem 5:

```java
1:  import java.util.Scanner;
2:  
3:  public class Main {
4:      public static void main(String[] args){
5:  
6:          // JAVA SLOT MACHINE
7:  
8:          // DECLARE VARIABLES
9:          Scanner scanner = new Scanner(System.in);
10:         int balance = 100;
11:         int bet;
12:         int payout;
13:         String[] row;
14: 
15:         // DISPLAY WELCOME MESSAGE
16:         System.out.println("*************************");
17:         System.out.println("  Welcome to Java Slots  ");
18:         System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐ ");
19:         System.out.println("*************************");
20: 
21:         // PLAY IF BALANCE > 0
22:         // ENTER BET AMOUNT
23:         //     VERIFY IF BET > BALANCE
24:         //     VERIFY IF BET > 0
25:         //     SUBTRACT BET FROM BALANCE
26:         // SPIN ROW
27:         // PRINT ROW
28:         // GET PAYOUT
29:         // ASK TO PLAY AGAIN
30:         // DISPLAY EXIT MESSAGE
31: 
32:         scanner.close();
33:     }
34: }
```

---

## 7. Explicação Linha por Linha Cirúrgica

---

### Linha 1: A Importação do Leitor
```java
1:  import java.util.Scanner;
```
* **O que faz:** Traz a ferramenta `Scanner` do pacote `java.util` para dentro do nosso programa. Sem isso, o Java não sabe como ler números digitados no teclado.

---

### Linhas 3 a 5: A Estrutura da Classe e Método
```java
3:  public class Main {
4:      public static void main(String[] args){
```
* **O que faz:** Cria a classe `Main` e o método `main`, que é a porta de entrada por onde a Máquina Virtual Java (JVM) começa a rodar o programa.

---

### Linhas 9 a 13: As Gavetas de Memória do Cassino
```java
9:          Scanner scanner = new Scanner(System.in);
10:         int balance = 100;
11:         int bet;
12:         int payout;
13:         String[] row;
```
* **Linha 9:** Cria o objeto `Scanner` ligado a `System.in` (o teclado do usuário).
* **Linha 10:** Reserva 4 bytes na memória Stack com o nome `balance` e deposita o valor `100`.
* **Linhas 11 e 12:** Declara as variáveis inteiras `bet` (aposta) e `payout` (prêmio).
* **Linha 13:** Declara a referência para o array `row`, que guardará os símbolos da linha do caça-níquel.

---

### Linhas 16 a 19: O Banner de Cassino com Moldura
```java
16:         System.out.println("*************************");
17:         System.out.println("  Welcome to Java Slots  ");
18:         System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐ ");
19:         System.out.println("*************************");
```
* **Linha 16:** Imprime uma linha superior de exatamente **25 asteriscos**.
* **Linha 17:** Imprime o texto de boas-vindas: dois espaços + `"Welcome to Java Slots"` + dois espaços, totalizando exatamente **25 caracteres de largura**, alinhando perfeitamente com a moldura!
* **Linha 18:** Exibe a legenda dos 5 símbolos oficiais do jogo:
  * 🍒 Cereja
  * 🍉 Melancia
  * 🍋 Limão
  * 🔔 Sino
  * ⭐ Estrela
* **Linha 19:** Fecha a moldura inferior com outros **25 asteriscos**.

---

### Linhas 21 a 30: Os Guias do Restante do Jogo
* Comentários preservados que servirão de trilho para as próximas aulas (apostas, giros randômicos, cálculos de prêmio e repetição).

---

### Linha 32: Fechando Recursos
```java
32:         scanner.close();
```
* Fecha o leitor para que o sistema operacional não mantenha portas abertas.

---

## 8. A Saída no Console e a Simetria do Banner (25 Caracteres)

Ao rodar o programa, a saída impressa no terminal inferior da IDE é:

```text
*************************
  Welcome to Java Slots  
Symbols: 🍒 🍉 🍋 🔔 ⭐ 
*************************

Process finished with exit code 0
```

### O Cuidado Estético do Professor:
Repare no alinhamento:
* `*************************` $\rightarrow$ 25 caracteres.
* `  Welcome to Java Slots  ` $\rightarrow$ 2 espaços + 21 letras + 2 espaços = 25 caracteres.
* `*************************` $\rightarrow$ 25 caracteres.
Esse capricho visual cria a sensação de estar diante de uma tela de máquina caça-níquel de verdade em um cassino de Las Vegas!

---

## 9. Resumo das Peças e Próximos Passos

| Variável / Elemento | Tipo | Valor Inicial | Papel no Caça-Níquel |
| :---: | :---: | :---: | :--- |
| `balance` | `int` | `100` | Carteira do apostador (o jogo acaba se chegar a 0) |
| `bet` | `int` | Indefinido | Quanto o jogador vai arriscar na rodada atual |
| `payout` | `int` | Indefinido | O prêmio pago se acertar 2 ou 3 símbolos iguais |
| `row` | `String[]` | `null` | A linha dos 3 símbolos sorteados |
| Emojis | Unicode | `🍒 🍉 🍋 🔔 ⭐` | Os 5 elementos da sorte do cassino |

### O que vem a seguir:
Na próxima etapa, programaremos o loop principal: `// PLAY IF BALANCE > 0` e o sistema de leitura e validação de apostas!
