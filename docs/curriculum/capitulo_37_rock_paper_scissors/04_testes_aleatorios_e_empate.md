# ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 4)
## A Prova da Aleatoriedade: Múltiplas Partidas no Console, o Fenômeno do Empate (*Tie*) e o Destaque Azul em `scissors`!

> **Baseado na aula do canal Bro Code (Minuto `6:07:00` a `6:07:15` de 12:00:00)**  
> **Tema:** O professor realiza novos testes práticos sucessivos para demonstrar a verdadeira aleatoriedade do gerador `Random`. Na primeira execução, o jogador escolhe `"paper"` e o computador sorteia `"scissors"`. Na segunda execução, acontece um dos momentos mais importantes de qualquer jogo de disputa: o jogador escolhe `"scissors"` e a máquina também sorteia `"scissors"`! O professor seleciona a palavra `scissors` em azul no console para destacar: **temos um EMPATE (*Tie*)!** Isso cria a justificativa perfeita para a próxima etapa: programar as condições de vitória!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das Duas Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-duas-telas-para-o-google-docs)
2. [O que o Professor Demonstrou Nestas Duas Execuções](#2-o-que-o-professor-demonstrou-nestas-duas-execuções)
3. [Por que a Palavra `scissors` Está Selecionada em Azul na Segunda Tela?](#3-por-que-a-palavra-scissors-está-selecionada-em-azul-na-segunda-tela)
4. [A Tríade de Desfechos do Jogo: Vitória, Derrota e Empate](#4-a-tríade-de-desfechos-do-jogo-vitória-derrota-e-empate)
5. [Tabela Comparativa dos Três Duelos Disputados pelo Professor](#5-tabela-comparativa-dos-três-duelos-disputados-pelo-professor)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Cirúrgica Linha por Linha](#7-explicação-cirúrgica-linha-por-linha)
8. [O que Saiu no Console em Cada Execução](#8-o-que-saiu-no-console-em-cada-execução)
9. [Antecipando a Próxima Linha de Código: Como Testar um Empate em Java?](#9-antecipando-a-próxima-linha-de-código-como-testar-um-empate-em-java)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das Duas Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA em ambas as imagens enviadas:

### 📸 Imagem 1 (`media_1791244564286.png` — Minuto `6:07:00`): O Duelo Papel vs. Tesoura
* **No editor de código (`Main.java`):**
  * O código permanece idêntico à parte anterior:
    * Linha 26: `computerChoice = choices[random.nextInt(3)];`
    * Linha 27: `System.out.println("Computer choice: " + computerChoice);`
* **Na janela de Console (Terminal Inferior):**
  * O professor clica em Run e digita **`paper`**:
    ```text
    Enter your move (rock, paper, scissors): paper
    Computer choice: scissors

    Process finished with exit code 0
    ```
  * O computador sorteou a posição 2 do array (`"scissors"`).
  * Como a tesoura corta o papel, a máquina venceu mais uma vez!

---

### 📸 Imagem 2 (`media_1791244835511.png` — Minuto `6:07:09`): O Empate com Destaque Azul!
* **Barra de status do vídeo:** `6:07:09 / 12:00:00`.
* **Na janela de Console (Terminal Inferior):**
  * O professor roda o programa novamente e desta vez digita **`scissors`**:
    ```text
    Enter your move (rock, paper, scissors): scissors
    Computer choice: scissors

    Process finished with exit code 0
    ```
  * A máquina sorteou novamente a posição 2 do array (`"scissors"`).
  * **O Destaque Azul Revelador:** A palavra **`scissors`** na linha `Computer choice: scissors` está selecionada com uma **faixa azul retangular vibrante**!
  * O professor arrastou o mouse sobre ela para apontar aos estudantes que **ambos os participantes escolheram rigorosamente a mesma jogada**!

---

## 2. O que o Professor Demonstrou Nestas Duas Execuções

Por que um professor experiente roda o mesmo código três vezes seguidas durante uma aula?

1. **Para Provar a Aleatoriedade Real:**  
   Se o computador sempre escolhesse `"paper"` ou sempre escolhesse `"rock"`, o jogo seria uma farsa previsível. Ao rodar várias vezes, os alunos veem que a cada execução a máquina escolhe uma jogada independente:
   * No 1º jogo (Parte 3): O computador escolheu `paper`.
   * No 2º jogo (Tela 1): O computador escolheu `scissors`.
   * No 3º jogo (Tela 2): O computador escolheu `scissors` novamente.
2. **Para Apresentar o Problema do Empate:**  
   Quando o ser humano jogou `scissors` e a máquina também jogou `scissors`, o programa encerrou sem dizer nada. Ficou evidente que o programa **precisa de um juiz** para analisar quem ganhou ou se houve empate!

---

## 3. Por que a Palavra `scissors` Está Selecionada em Azul na Segunda Tela?

Ao assistir ao vídeo ou analisar a Imagem 2, salta aos olhos a seleção azul sobre a palavra `scissors`.

### O Motivo Didático do Professor Bro Code:
* O professor faz isso com o mouse para chamar a atenção visual de quem está assistindo e dizer:  
  *"Olhem para a tela! O jogador digitou `scissors` lá em cima. E vejam o que a máquina sorteou aqui embaixo: `scissors` também! Nós temos escolhas idênticas!"*
* Ele usa essa seleção para fazer a transição didática perfeita para o próximo bloco de código:  
  *"Como faremos o Java perceber que os dois valores são iguais? Precisamos implementar agora o comentário `// CHECK WIN CONDITIONS`!"*

---

## 4. A Tríade de Desfechos do Jogo: Vitória, Derrota e Empate

Em qualquer jogo de Pedra, Papel e Tesoura no planeta, existem apenas **3 desfechos possíveis**:

1. **O Empate (*Tie*):**  
   Acontece quando a jogada do jogador é idêntica à do computador:
   * Pedra vs. Pedra
   * Papel vs. Papel
   * Tesoura vs. Tesoura
2. **A Vitória do Jogador (*You Win!*):**  
   * Jogador joga Pedra (`rock`) e Computador joga Tesoura (`scissors`) $\rightarrow$ Pedra quebra Tesoura!
   * Jogador joga Papel (`paper`) e Computador joga Pedra (`rock`) $\rightarrow$ Papel cobre Pedra!
   * Jogador joga Tesoura (`scissors`) e Computador joga Papel (`paper`) $\rightarrow$ Tesoura corta Papel!
3. **A Derrota do Jogador / Vitória da Máquina (*You Lose!*):**  
   Qualquer outra combinação que não seja empate nem vitória do jogador!

---

## 5. Tabela Comparativa dos Três Duelos Disputados pelo Professor

Veja o histórico das 3 partidas disputadas até agora pelo professor no vídeo:

| Partida | Jogador Humano (`playerChoice`) | Computador IA (`computerChoice`) | Índice Sorteado (`random`) | O que Aconteceu? | Desfecho do Jogo |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **Duelo 1 (Parte 3)** | `rock` *(Pedra)* | `paper` *(Papel)* | `1` | Papel cobre Pedra | 🤖 Computador Venceu! |
| **Duelo 2 (Tela 1)** | `paper` *(Papel)* | `scissors` *(Tesoura)* | `2` | Tesoura corta Papel | 🤖 Computador Venceu! |
| **Duelo 3 (Tela 2)** | `scissors` *(Tesoura)* | `scissors` *(Tesoura)* | `2` | Escolhas Idênticas! | 🤝 **EMPATE (*TIE*)!** *(Destacado em Azul)* |

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado durante essas duas execuções:

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

## 7. Explicação Cirúrgica Linha por Linha

---

### Linhas 17 e 18: A Escolha do Jogador
```java
17:         System.out.print("Enter your move (rock, paper, scissors): ");
18:         playerChoice = scanner.nextLine().toLowerCase();
```
* **No Duelo 2:** O usuário digitou `paper`. `playerChoice` recebeu `"paper"`.
* **No Duelo 3:** O usuário digitou `scissors`. `playerChoice` recebeu `"scissors"`.

---

### Linhas 20 a 24: A Validação
* Tanto `"paper"` quanto `"scissors"` são escolhas legítimas.
* O bloco do `if` deu **falso** em ambas as partidas e o aviso `"Invalid choice"` permaneceu quieto.

---

### Linha 26: O Sorteio da Máquina
```java
26:         computerChoice = choices[random.nextInt(3)];
```
* **No Duelo 2:** O gerador sorteou o índice `2`. `choices[2]` é `"scissors"`.
* **No Duelo 3:** O gerador sorteou o índice `2` novamente! `choices[2]` é `"scissors"`.

---

### Linha 27: A Divulgação da Jogada da IA
```java
27:         System.out.println("Computer choice: " + computerChoice);
```
* Imprime no console a jogada sorteada pela máquina, revelando o confronto.

---

## 8. O que Saiu no Console em Cada Execução

### Duelo 2 (Tela 1):
```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA..."
Enter your move (rock, paper, scissors): paper
Computer choice: scissors

Process finished with exit code 0
```

### Duelo 3 (Tela 2 — Com o Empate Selecionado em Azul):
```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA..."
Enter your move (rock, paper, scissors): scissors
Computer choice: scissors

Process finished with exit code 0
```

---

## 9. Antecipando a Próxima Linha de Código: Como Testar um Empate em Java?

Como o professor vai programar o juiz para detectar o empate na próxima tela?

Ele usará o método `.equals()` para comparar as duas variáveis String:
```java
if (playerChoice.equals(computerChoice)) {
    System.out.println("It's a tie!");
}
```

* Se `playerChoice` for `"scissors"` e `computerChoice` for `"scissors"`, `"scissors".equals("scissors")` dá **VERDADEIRO**!
* O Java imprimirá na tela: *"It's a tie!"* *(É um empate!)*.

---

## 10. Resumo Prático em Uma Tabela Mental

| Duelo no Vídeo | O que o Humano Jogou? | O que o Computador Jogou? | Situação do Confronto | O que Falta no Código? |
| :---: | :---: | :---: | :---: | :--- |
| **Tela 1** | `"paper"` | `"scissors"` | A máquina cortou o papel! | Falta o Java avisar: *"You lose!"*. |
| **Tela 2** | `"scissors"` | `"scissors"` | **Ambos iguais! (Empate)** | Falta o Java avisar: *"It's a tie!"*. |
| **Destaque Azul** | — | `scissors` selecionado | Mostra o empate visual para os alunos | Justifica a criação do `if` de verificação. |
