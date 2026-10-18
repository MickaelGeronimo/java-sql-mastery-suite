# ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 3)
## A Inteligência Artificial da Máquina: O Sorteio Aleatório com `random.nextInt(3)`, a Busca no Array `choices[...]` e o Duelo no Console!

> **Baseado na aula do canal Bro Code (Minuto `6:05:40` a `6:06:56` de 12:00:00)**  
> **Tema:** O professor implementa a jogada do computador! Ele substitui o comentário `// GET RANDOM CHOICE FOR THE COMPUTER` por uma das linhas mais elegantes do Java para iniciantes: **`computerChoice = choices[random.nextInt(3)];`**. Ao combinar o gerador de números pseudo-aleatórios com o índice do array de opções, ele faz a máquina "pensar" e sortear uma jogada imprevisível a cada partida. Em seguida, ele testa o jogo ao vivo: o jogador joga `"rock"` e o computador responde com `"paper"`!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das Duas Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-duas-telas-para-o-google-docs)
2. [O que o Professor Fez e Explicou Neste Momento](#2-o-que-o-professor-fez-e-explicou-neste-momento)
3. [Dissecação Anatômica da Fórmula: `choices[random.nextInt(3)]`](#3-dissecação-anatômica-da-fórmula-choicesrandomnextint3)
4. [Por que o Número 3 Dentro de `random.nextInt(3)`?](#4-por-que-o-número-3-dentro-de-randomnextint3)
5. [O Código Java Completo, Numerado e Definitivo](#5-o-código-java-completo-numerado-e-definitivo)
6. [Explicação Cirúrgica Linha por Linha](#6-explicação-cirúrgica-linha-por-linha)
7. [Teste de Mesa Passo a Passo: A Jogada da Imagem 2](#7-teste-de-mesa-passo-a-passo-a-jogada-da-imagem-2)
8. [O que Saiu no Console (A Partida Real)](#8-o-que-saiu-no-console-a-partida-real)
9. [Quem Ganhou Essa Rodada? (Antecipando as Regras de Vitória)](#9-quem-ganhou-essa-rodada-antecipando-as-regras-de-vitória)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das Duas Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA em ambas as imagens enviadas:

### 📸 Imagem 1 (`media_1791244525827.png` — Minuto `6:06:00`): O Código do Sorteio
* **Barra de tempo do vídeo:** ~`6:06:00 / 12:00:00` | Capítulo: `37. rock paper scissors >`.
* **No editor de código (`Main.java`):**
  * Logo abaixo do bloco `if` da validação (linha 24), o professor removeu o comentário `// GET RANDOM CHOICE FOR THE COMPUTER` e adicionou duas novas linhas:
    * **Linha 26:** `computerChoice = choices[random.nextInt(3)];`
    * **Linha 27:** `System.out.println("Computer choice: " + computerChoice);`
  * O cursor de texto pisca no final da linha 27.
  * Abaixo, continuam os comentários do esqueleto:
    * `// CHECK WIN CONDITIONS`
    * `// ASK TO PLAY AGAIN?`
    * `// GOODBYE MESSAGE`
  * Linha 33: `scanner.close();`.
  * **No topo direito da janela:** A setinha do mouse do professor está posicionada exatamente em cima do botão verde de **Play (▶️ Run)** para testar o sorteio da máquina!

---

### 📸 Imagem 2 (`media_1791244548966.png` — Minuto `6:06:56`): O Duelo Humano vs. Máquina no Console
* **Barra de tempo do vídeo:** `6:06:56 / 12:00:00`.
* **Na janela de Console (Terminal Inferior):**
  * O programa foi executado. O console exibiu o prompt:
    ```text
    Enter your move (rock, paper, scissors): rock
    ```
  * O professor digitou **`rock`** e apertou Enter.
  * O computador executou o sorteio aleatório e imprimiu na linha seguinte em texto verde:
    ```text
    Computer choice: paper
    ```
  * Logo abaixo:
    ```text
    Process finished with exit code 0
    ```
  * O cursor do mouse em forma de "I" (*I-beam*) está posicionado logo abaixo da palavra `paper`.

---

## 2. O que o Professor Fez e Explicou Neste Momento

Até a Parte 2, apenas o ser humano jogava e o programa encerrava.  
Agora, o professor cria o **adversário virtual**:

1. Ele usa a ferramenta `random` (que instanciamos lá no início com `Random random = new Random();`).
2. Faz o computador "rolar um dado de 3 faces" com `random.nextInt(3)`.
3. Usa o número sorteado (0, 1 ou 2) como **índice** para puxar uma palavra de dentro do array `choices`:
   * Se sortear 0 $\rightarrow$ vira `"rock"`
   * Se sortear 1 $\rightarrow$ vira `"paper"`
   * Se sortear 2 $\rightarrow$ vira `"scissors"`
4. Guarda o texto resultante dentro da variável `computerChoice`.
5. Exibe a jogada da máquina na tela para o jogador saber contra o que está disputando:  
   `Computer choice: paper`!

---

## 3. Dissecação Anatômica da Fórmula: `choices[random.nextInt(3)]`

Esta linha de código é um exemplo clássico de **composição de expressões** em Java:

```java
computerChoice = choices[random.nextInt(3)];
```

Vamos desmontá-la de dentro para fora, como se fosse um brinquedo de Lego:

```text
Passo 1: O sorteador gira                   -> random.nextInt(3)
Passo 2: A máquina sorteia um número        -> ex.: 1
Passo 3: A expressão vira                   -> choices[1]
Passo 4: O array choices busca a posição 1   -> "paper"
Passo 5: A variável recebe o valor final     -> computerChoice = "paper";
```

### A Metáfora da Cartola de Mágico 🎩🐇
* O array `choices` é uma cartola com 3 lenços numerados:
  * Gaveta 0: `"rock"`
  * Gaveta 1: `"paper"`
  * Gaveta 2: `"scissors"`
* O método `random.nextInt(3)` é a mão do mágico com uma venda nos olhos: ele enfia a mão na cartola e puxa aleatoriamente o número de uma das 3 gavetas.
* A máquina então lê o que estava escrito naquele lenço e guarda na variável `computerChoice`!

---

## 4. Por que o Número 3 Dentro de `random.nextInt(3)`?

Muitos iniciantes perguntam:  
*"Mas professor, os índices do array são 0, 1 e 2. Por que você colocou 3 dentro dos parênteses e não 2?"*

### A Regra do Limite Exclusivo (*Upper Bound*):
No Java, o método `random.nextInt(n)` gera números inteiros a partir do zero até **`n - 1`** (o número `n` nunca é incluído!):
* Se você escrever `random.nextInt(3)`:  
  Ele gera números de `0` até `3 - 1` $\rightarrow$ **Gera apenas `0`, `1` ou `2`**!
* Que são **exatamente** os índices válidos do nosso array de 3 posições!

> [!TIP]
> **Dica de Engenheiro Profissional:**  
> Você também poderia escrever `random.nextInt(choices.length)`. Assim, se no futuro você adicionasse `"lizard"` e `"spock"`, o sorteio continuaria funcionando sem precisar alterar o número manualmente!

---

## 5. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado na Imagem 1:

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
26:         computerChoice = choices[random.nextInt(3)];
27:         System.out.println("Computer choice: " + computerChoice);
28: 
29:         // CHECK WIN CONDITIONS
30:         // ASK TO PLAY AGAIN?
31:         // GOODBYE MESSAGE
32: 
33:         scanner.close();
34:     }
35: }
```

---

## 6. Explicação Cirúrgica Linha por Linha

---

### Linha 26: O Sorteio da Jogada da Máquina
```java
26:         computerChoice = choices[random.nextInt(3)];
```
* **O que faz:** Chama o gerador de números aleatórios `random.nextInt(3)`, que devolve `0`, `1` ou `2`. Em seguida, consulta o array `choices` naquela posição e atribui a String correspondente à variável `computerChoice`.
* **O que acontece na memória:** A variável de referência `computerChoice` na Stack passa a apontar para o objeto String correspondente na Heap (`"rock"`, `"paper"` ou `"scissors"`).
* **Saída no console:** Nenhuma nesta linha.

---

### Linha 27: A Revelação da Jogada do Computador
```java
27:         System.out.println("Computer choice: " + computerChoice);
```
* **O que faz:** Imprime o texto `"Computer choice: "` concatenado com o valor que foi sorteado para a máquina e pula para a linha de baixo (`println`).
* **Saída no console:**  
  `Computer choice: paper` (ou `rock`, ou `scissors`, dependendo da sorte!).

---

## 7. Teste de Mesa Passo a Passo: A Jogada da Imagem 2

Veja exatamente como a memória e o console se comportaram durante o teste da imagem enviada:

| Etapa | Ação Executada | O que Acontece na Memória? | O que Sai no Console? |
| :---: | :--- | :--- | :--- |
| **1** | Linha 17 | Java solicita a jogada do usuário. | `Enter your move (rock, paper, scissors): ` |
| **2** | Linha 18 | Usuário digita `"rock"` e aperta Enter. `playerChoice` recebe `"rock"`. | O cursor pula para a linha de baixo. |
| **3** | Linhas 20-24 | Validação: `"rock"` é válido? Sim! Pula o `if`. | Silêncio (não imprime erro). |
| **4** | Linha 26 | `random.nextInt(3)` sorteou o número **`1`**. | `choices[1]` é `"paper"`. `computerChoice` vira `"paper"`. |
| **5** | Linha 27 | Imprime a escolha da máquina. | **`Computer choice: paper`** |
| **6** | Linha 33 | `scanner.close()` fecha o leitor de teclado. | `Process finished with exit code 0` |

---

## 8. O que Saiu no Console (A Partida Real)

A saída real e completa exibida no terminal na Imagem 2 é:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA..."
Enter your move (rock, paper, scissors): rock
Computer choice: paper

Process finished with exit code 0
```

---

## 9. Quem Ganhou Essa Rodada? (Antecipando as Regras de Vitória)

Embora o programa ainda não tenha avisado quem venceu (pois a etapa `// CHECK WIN CONDITIONS` ainda será escrita no próximo passo), nós seres humanos já sabemos o resultado:
* **Humano jogou:** `rock` *(Pedra)*
* **Computador jogou:** `paper` *(Papel)*
* **Resultado:** **Papel cobre a Pedra! O Computador venceu essa rodada!** 🤖🏆

No próximo passo, o professor vai ensinar como programar o Java para comparar as duas escolhas e anunciar: *"You win!"*, *"You lose!"* ou *"It's a tie!"*!

---

## 10. Resumo Prático em Uma Tabela Mental

| Componente | Valor Possível | O que Representa? |
| :--- | :--- | :--- |
| **`random.nextInt(3)`** | `0`, `1` ou `2` | O sorteio aleatório de um índice válido do array. |
| **`choices[0]`** | `"rock"` | Se sortear 0, a máquina jogou Pedra. |
| **`choices[1]`** | `"paper"` | Se sortear 1, a máquina jogou Papel. |
| **`choices[2]`** | `"scissors"` | Se sortear 2, a máquina jogou Tesoura. |
| **`computerChoice`** | `"paper"` *(no teste do print)* | A variável que guarda a decisão da IA para o confronto! |
