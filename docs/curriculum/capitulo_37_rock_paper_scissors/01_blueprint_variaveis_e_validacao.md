# ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 1)
## O Início do Clássico Jogo: Esqueleto Arquitetural, Arsenal de Variáveis (`Random` e `Scanner`) e a Blindagem de Entrada com `.toLowerCase()`, `.equals()`, `!` e `&&`

> **Baseado na aula do canal Bro Code (Minuto `6:00:06` a `6:05:20` de 12:00:00)**  
> **Tema:** O professor inicia o **Capítulo 37**: a construção do clássico jogo **Pedra, Papel e Tesoura** (*Rock Paper Scissors*) contra o computador! Ele começa desenhando o esqueleto arquitetural de 6 passos, prepara a classe geradora de números aleatórios `Random`, cria o array com as 3 jogadas possíveis, captura o movimento do jogador convertendo para minúsculas com `.toLowerCase()` e aplica uma blindagem lógica refinada usando negação (`!`), `.equals()` e o operador lógico E (`&&`) para barrar jogadas inválidas!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 5 Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-5-telas-para-o-google-docs)
2. [O que é o Projeto "Rock Paper Scissors" e o que Ele Ensina?](#2-o-que-é-o-projeto-rock-paper-scissors-e-o-que-ele-ensina)
3. [As 6 Etapas do Esqueleto Arquitetural (Blueprint)](#3-as-6-etapas-do-esqueleto-arquitetural-blueprint)
4. [O Arsenal de Variáveis: Conhecendo o `Random` e as Strings de Escolha](#4-o-arsenal-de-variáveis-conhecendo-o-random-e-as-strings-de-escolha)
5. [A Captura Inteligente: O Poder do `.toLowerCase()`](#5-a-captura-inteligente-o-poder-do-tolowercase)
6. [A Lógica Booleana da Validação: Por que Usar `!` e `&&` Juntos?](#6-a-lógica-booleana-da-validação-por-que-usar--e--juntos)
7. [O Código Java Completo, Numerado e Definitivo](#7-o-código-java-completo-numerado-e-definitivo)
8. [Explicação Cirúrgica Linha por Linha](#8-explicação-cirúrgica-linha-por-linha)
9. [Dissecação do Teste Prático no Console (Telas 4 e 5)](#9-dissecação-do-teste-prático-no-console-telas-4-e-5)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das 5 Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de cada uma das 5 imagens enviadas:

### 📸 Tela 1 (`media_1791244361347.png` — Minuto `6:00:06`): O Esqueleto Inicial
* **Barra de tempo do vídeo:** `6:00:06 / 12:00:00` | Capítulo: `37. rock paper scissors >`.
* **No editor de código (`Main.java`):**
  * O professor limpou o código do Quiz Game do capítulo anterior.
  * Na linha 5, escreve o título: `// ROCK PAPER SCISSORS GAME`.
  * Das linhas 7 a 12, ele traça os 6 comentários do planejamento:
    * `// DECLARE VARIABLES`
    * `// GET CHOICE FROM THE USER`
    * `// GET RANDOM CHOICE FOR THE COMPUTER`
    * `// CHECK WIN CONDITIONS`
    * `// ASK TO PLAY AGAIN?`
    * `// GOODBYE MESSAGE`
  * O cursor de texto pisca na linha 5 ao lado do título.

---

### 📸 Tela 2 (`media_1791244400052.png` — Minuto `6:01:50`): O Arsenal de Variáveis Declarado
* **Barra de tempo do vídeo:** `6:01:50 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * Abaixo de `// ROCK PAPER SCISSORS GAME`, ele removeu `// DECLARE VARIABLES` e digitou:
    * Linha 9: `Scanner scanner = new Scanner(System.in);`
    * Linha 10: `Random random = new Random();`
    * Linha 12: `String[] choices = {"rock", "paper", "scissors"};`
    * Linha 13: `String playerChoice;`
    * Linha 14: `String computerChoice;`
    * Linha 15: `String playAgain = "yes";`
  * Linha 23: Já adicionou preventivamente `scanner.close();` antes de fechar o método `main`.
  * Na linha 15 há um pequeno marcador vermelho da IDE e o cursor está no ponto e vírgula de `playAgain = "yes";`.

---

### 📸 Tela 3 (`media_1791244445418.png` — Minuto `6:05:05`): O Prompt e a Validação
* **Barra de tempo do vídeo:** `6:05:05 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * Abaixo de `playAgain`, o professor removeu `// GET CHOICE FROM THE USER` e escreveu:
    * Linha 17: `System.out.print("Enter your move (rock, paper, scissors): ");`
    * Linha 18: `playerChoice = scanner.nextLine().toLowerCase();`
    * Linhas 20 a 24: A estrutura condicional de validação:
      ```java
      if(!playerChoice.equals("rock") &&
         !playerChoice.equals("paper") &&
         !playerChoice.equals("scissors")){
          System.out.println("Invalid choice");
      }
      ```
  * O cursor pisca dentro de `System.out.println("Invalid choice"|);`.

---

### 📸 Tela 4 (`media_1791244464479.png` — Minuto `6:05:14`): A Primeira Execução (Aguardando Jogada)
* **Barra de tempo do vídeo:** `6:05:14 / 12:00:00`.
* **Na janela de Console (Terminal Inferior):**
  * O professor clica no botão verde de **Play (▶️ Run)**.
  * O console abre e exibe em texto verde:
    ```text
    Enter your move (rock, paper, scissors): |
    ```
  * O cursor fica piscando imediatamente após o espaço dos dois pontos, pausando a execução do Java e esperando a digitação do jogador.

---

### 📸 Tela 5 (`media_1791244476471.png`): O Teste com uma Jogada Válida (`paper`)
* **No Console de Execução:**
  * O professor digita a palavra **`paper`** e aperta a tecla **ENTER**.
  * O console exibe:
    ```text
    Enter your move (rock, paper, scissors): paper

    Process finished with exit code 0
    ```
  * **O Detalhe Crítico:** A mensagem `"Invalid choice"` **NÃO apareceu**!
  * O programa encerrou silenciosamente com sucesso (código 0). Isso comprova que a validação reconheceu que `paper` é uma jogada válida e permitiu que o programa seguisse em frente sem disparar o erro!

---

## 2. O que é o Projeto "Rock Paper Scissors" e o que Ele Ensina?

Neste novo capítulo prático, o professor eleva o nível da programação em Java trazendo conceitos avançados de lógica de jogos:
1. **Inteligência Artificial Simples:** Fazer o computador tomar decisões sozinho usando a classe `Random`.
2. **Manipulação Robusta de Strings:** Tratar textos digitados pelo usuário com `.toLowerCase()` para evitar que letras maiúsculas quebrem o jogo.
3. **Comparação Segura de Objetos com `.equals()`:** Reforçando que textos em Java nunca devem ser comparados com `==`.
4. **Lógica Proposicional com `!` (Negação) e `&&` (E Lógico):** Como criar filtros de segurança infalíveis para impedir que o usuário digite palavras inválidas como `"banana"` ou `"carro"`.
5. **Laços de Repetição para Jogar Novamente (`playAgain`):** Permitir que o jogador dispute quantas partidas quiser sem precisar reabrir o programa.

---

## 3. As 6 Etapas do Esqueleto Arquitetural (Blueprint)

Assim como no capítulo anterior, o professor começa traçando o roteiro do jogo em comentários `//`:

```text
1. DECLARE VARIABLES                 -> Preparar as ferramentas e caixinhas de memória.
2. GET CHOICE FROM THE USER          -> Pedir e validar se o usuário escolheu rock, paper ou scissors.
3. GET RANDOM CHOICE FOR THE COMPUTER -> Fazer a máquina sortear a jogada dela de forma imprevisível.
4. CHECK WIN CONDITIONS              -> As regras clássicas: Pedra quebra Tesoura, Tesoura corta Papel, Papel cobre Pedra.
5. ASK TO PLAY AGAIN?                -> Perguntar se o usuário quer jogar outra rodada (yes/no).
6. GOODBYE MESSAGE                   -> Mensagem de despedida e encerramento.
```

---

## 4. O Arsenal de Variáveis: Conhecendo o `Random` e as Strings de Escolha

Veja as ferramentas que o professor instanciou no topo do método `main`:

```java
Scanner scanner = new Scanner(System.in);
Random random = new Random();

String[] choices = {"rock", "paper", "scissors"};
String playerChoice;
String computerChoice;
String playAgain = "yes";
```

### 1. `Random random = new Random();` (A Novidade!)
* A classe `Random` pertence ao pacote `java.util.Random`.
* Ela funciona como um **dado de RPG virtual** ou uma roleta de cassino.
* Mais adiante, usaremos `random.nextInt(3)` para sortear um número entre 0, 1 e 2, permitindo que a máquina escolha aleatoriamente entre `"rock"`, `"paper"` ou `"scissors"`!

### 2. `String[] choices = {"rock", "paper", "scissors"};`
* Um array 1D de 3 posições que guarda o vocabulário oficial do jogo.
  * Posição `0` = `"rock"` *(pedra)*
  * Posição `1` = `"paper"` *(papel)*
  * Posição `2` = `"scissors"` *(tesoura)*

### 3. As Variáveis de Estado:
* `playerChoice`: Guardará o que o ser humano digitou.
* `computerChoice`: Guardará o que a máquina sorteou.
* `playAgain = "yes"`: A variável de controle para manter o jogo rodando enquanto o usuário responder `"yes"`.

---

## 5. A Captura Inteligente: O Poder do `.toLowerCase()`

Observe a linha 18:
```java
playerChoice = scanner.nextLine().toLowerCase();
```

### Por que encadear `.toLowerCase()` logo após o `nextLine()`?
Imagine que você não colocasse o `.toLowerCase()`. Veja o que aconteceria:
* Se o usuário digitar `"rock"`, o Java aceita.
* Mas se o usuário digitar `"Rock"` (com R maiúsculo) ou `"ROCK"` (no Caps Lock), o Java diria: *"Jogada inválida!"*, porque em Java a letra `'R'` maiúscula é diferente de `'r'` minúscula!
* Ao chamar `.toLowerCase()`, qualquer coisa que o usuário digitar é **convertida instantaneamente para letras minúsculas** antes de ser guardada na variável `playerChoice`:
  * `"ROCK"` vira `"rock"`
  * `"RoCk"` vira `"rock"`
  * `"Paper"` vira `"paper"`
* Isso é o que chamamos de **código à prova de falhas** (*User-proof / Defensive Programming*)!

---

## 6. A Lógica Booleana da Validação: Por que Usar `!` e `&&` Juntos?

Esta é a linha mais desafiadora para quem é iniciante amador em Java. Vamos dissecá-la com calma cirúrgica:

```java
if (!playerChoice.equals("rock") &&
    !playerChoice.equals("paper") &&
    !playerChoice.equals("scissors")) {
    System.out.println("Invalid choice");
}
```

### 1. O Ponto de Exclamação `!` (Operador de Negação):
* Em Java, o ponto de exclamação antes de uma expressão significa **NÃO** (*NOT*).
* `playerChoice.equals("rock")` pergunta: *"A escolha é igual a pedra?"*.
* `!playerChoice.equals("rock")` inverte a resposta e pergunta: ***"A escolha NÃO É igual a pedra?"***.

### 2. O Operador `&&` (E Lógico):
* O operador `&&` exige que **todas as condições sejam verdadeiras ao mesmo tempo** para que o bloco seja acionado.

### 3. A Tradução para o Português Claro:
> *"Se a escolha do jogador **NÃO FOR** pedra  
> **E** a escolha do jogador **NÃO FOR** papel  
> **E** a escolha do jogador **NÃO FOR** tesoura...  
> Então significa que o jogador digitou uma besteira! Imprima: 'Invalid choice'!"*

---

### 🧪 Teste de Mesa da Lógica Booleana:

#### Cenário A: O Usuário Digitou `"paper"` (Como na Tela 5)
1. `!playerChoice.equals("rock")` $\rightarrow$ "paper" não é "rock"? **Verdadeiro (True)**.
2. `!playerChoice.equals("paper")` $\rightarrow$ "paper" não é "paper"? **FALSO (False)**, pois é igual!
3. Como uma das condições deu **FALSO**, a regra do operador `&&` anula tudo instantaneamente! O resultado final é **FALSO**!
4. O Java pula fora do `if` e **NÃO imprime** `"Invalid choice"`. A jogada foi aceita!

#### Cenário B: O Usuário Digitou `"banana"`
1. `!playerChoice.equals("rock")` $\rightarrow$ "banana" não é "rock"? **Verdadeiro (True)**.
2. `!playerChoice.equals("paper")` $\rightarrow$ "banana" não é "paper"? **Verdadeiro (True)**.
3. `!playerChoice.equals("scissors")` $\rightarrow$ "banana" não é "scissors"? **Verdadeiro (True)**.
4. Como todas as 3 deram **Verdadeiro**, o resultado do `if` é **VERDADEIRO**!
5. O Java entra no bloco e imprime: `"Invalid choice"`. O erro foi barrado com sucesso!

---

## 7. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado na Tela 3:

```java
1:  import java.util.Random;
2:  import java.util.Scanner;
3:  
4:  public class Main {
5:      public static void main(String[] args){
6:  
7:          // ROCK PAPER SCISSORS GAME
8:  
9:          Scanner scanner = new Scanner(System.in);
10:         Random random = new Random();
11: 
12:         String[] choices = {"rock", "paper", "scissors"};
13:         String playerChoice;
14:         String computerChoice;
15:         String playAgain = "yes";
16: 
17:         System.out.print("Enter your move (rock, paper, scissors): ");
18:         playerChoice = scanner.nextLine().toLowerCase();
19: 
20:         if(!playerChoice.equals("rock") &&
21:            !playerChoice.equals("paper") &&
22:            !playerChoice.equals("scissors")){
23:             System.out.println("Invalid choice");
24:         }
25: 
26:         // GET RANDOM CHOICE FOR THE COMPUTER
27:         // CHECK WIN CONDITIONS
28:         // ASK TO PLAY AGAIN?
29:         // GOODBYE MESSAGE
30: 
31:         scanner.close();
32:     }
33: }
```

---

## 8. Explicação Cirúrgica Linha por Linha

---

### Linhas 1 e 2: As Importações
```java
1:  import java.util.Random;
2:  import java.util.Scanner;
```
* Traz as ferramentas `Random` (para sortear jogadas) e `Scanner` (para ler o teclado) de dentro do pacote `java.util`.

---

### Linhas 4 e 5: A Casca do Programa
```java
4:  public class Main {
5:      public static void main(String[] args){
```
* Declara a classe pública `Main` e o ponto de entrada `main`.

---

### Linhas 9 e 10: Criação dos Objetos Utilitários
```java
9:          Scanner scanner = new Scanner(System.in);
10:         Random random = new Random();
```
* **Linha 9:** Cria o receptor de teclado conectado ao `System.in`.
* **Linha 10:** Cria o motor de números aleatórios na memória Heap.

---

### Linhas 12 a 15: O Banco de Dados de Jogadas e Variáveis
```java
12:         String[] choices = {"rock", "paper", "scissors"};
13:         String playerChoice;
14:         String computerChoice;
15:         String playAgain = "yes";
```
* **Linha 12:** Array com as 3 opções permitidas.
* **Linha 13:** Variável String para armazenar o movimento digitado pelo humano.
* **Linha 14:** Variável String para armazenar o sorteio da máquina.
* **Linha 15:** String com o valor padrão `"yes"` para o controle do laço de repetição futuro.

---

### Linhas 17 e 18: A Pergunta e a Captura em Minúsculas
```java
17:         System.out.print("Enter your move (rock, paper, scissors): ");
18:         playerChoice = scanner.nextLine().toLowerCase();
```
* **Linha 17:** Imprime o pedido na tela com `print` (mantendo o cursor na mesma linha).
* **Linha 18:** Lê a linha inteira digitada pelo usuário e aplica imediatamente `.toLowerCase()`, eliminando qualquer problema com letras maiúsculas.

---

### Linhas 20 a 24: A Barreira de Proteção contra Entradas Inválidas
```java
20:         if(!playerChoice.equals("rock") &&
21:            !playerChoice.equals("paper") &&
22:            !playerChoice.equals("scissors")){
23:             System.out.println("Invalid choice");
24:         }
```
* Testa se o texto digitado não pertence à lista de movimentos legais. Se não for nenhum dos três, avisa `"Invalid choice"`.

---

### Linhas 26 a 29: O Roteiro das Próximas Partes
```java
26:         // GET RANDOM CHOICE FOR THE COMPUTER
27:         // CHECK WIN CONDITIONS
28:         // ASK TO PLAY AGAIN?
29:         // GOODBYE MESSAGE
```
* Comentários indicando as etapas seguintes: a máquina jogar, verificar quem venceu, perguntar se quer jogar de novo e se despedir.

---

### Linhas 31 a 33: Fechamento de Recursos e Fim
```java
31:         scanner.close();
32:     }
33: }
```
* Fecha o leitor de teclado e encerra o método `main`.

---

## 9. Dissecação do Teste Prático no Console (Telas 4 e 5)

Nas Telas 4 e 5, o professor testou o programa ao vivo:

1. **Apresentação:** O console exibiu:
   ```text
   Enter your move (rock, paper, scissors): 
   ```
2. **A Entrada do Usuário:** O professor digitou:
   ```text
   paper
   ```
3. **O Comportamento:** Como `"paper"` é uma escolha válida, a condição `if` resultou em falso e o aviso `"Invalid choice"` não foi impresso. O programa terminou com código 0:
   ```text
   Process finished with exit code 0
   ```

---

## 10. Resumo Prático em Uma Tabela Mental

| Componente | O que É? | Para que Serve Neste Jogo? |
| :--- | :--- | :--- |
| **`Random random`** | Gerador de números pseudo-aleatórios | Será usado para a máquina escolher entre 0, 1 e 2. |
| **`String[] choices`** | Array 1D com 3 posições | Guarda os textos `"rock"`, `"paper"` e `"scissors"`. |
| **`.toLowerCase()`** | Método da classe String | Converte textos como `"ROCK"` ou `"Rock"` para `"rock"`. |
| **`.equals(...)`** | Comparador de conteúdo de texto | A única forma correta de comparar Strings em Java. |
| **`!` (Negação)** | Operador unário lógico | Inverte o resultado booleano (Vira "NÃO É IGUAL"). |
| **`&&` (E Lógico)** | Operador binário de conjunção | Só é verdadeiro se a jogada for diferente de **todas** as três opções! |
