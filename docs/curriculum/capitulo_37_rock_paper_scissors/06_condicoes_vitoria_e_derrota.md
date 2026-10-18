# ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 6)
## O Juiz Supremo: As Regras do Duelo, os 3 `else if` de Vitória Selecionados em Azul, a Eliminação Matemática do `else` e o Grito de `"You win!"`!

> **Baseado na aula do canal Bro Code (Minuto `6:10:08` a `6:10:20` de 12:00:00)**  
> **Tema:** O professor programa a inteligência de arbitragem do jogo! Ele preenche as condições que estavam vazias na Parte 5: testa o empate com `playerChoice.equals(computerChoice)`, programa as 3 combinações de vitória do ser humano (selecionando as linhas 32 a 40 em azul no editor para explicar cada regra aos alunos) e deixa o `else` final capturar a derrota por eliminação matemática. Em seguida, roda o jogo, joga `"rock"` contra `"scissors"` do robô e comemora a vitória com `"You win!"` destacado em azul no console!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das Duas Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-duas-telas-para-o-google-docs)
2. [O que o Professor Adicionou e Explicou Neste Momento](#2-o-que-o-professor-adicionou-e-explicou-neste-momento)
3. [Por que as Linhas 32 a 40 Aparecem Selecionadas em Azul no Editor?](#3-por-que-as-linhas-32-a-40-aparecem-selecionadas-em-azul-no-editor)
4. [As 3 Regras de Ouro da Vitória do Ser Humano](#4-as-3-regras-de-ouro-da-vitória-do-ser-humano)
5. [A Genialidade do `else` Final: A Eliminação Matemática das 9 Possibilidades](#5-a-genialidade-do-else-final-a-eliminação-matemática-das-9-possibilidades)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Cirúrgica Linha por Linha](#7-explicação-cirúrgica-linha-por-linha)
8. [Teste de Mesa Passo a Passo do Duelo da Tela 2](#8-teste-de-mesa-passo-a-passo-do-duelo-da-tela-2)
9. [Por que o Texto `"You win!"` Aparece Selecionado em Azul no Console?](#9-por-que-o-texto-you-win-aparece-selecionado-em-azul-no-console)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das Duas Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA em ambas as imagens enviadas:

### 📸 Imagem 1 (`media_1791244889437.png` — Minuto `6:10:08`): A Lógica do Juiz e a Seleção Azul
* **Barra de tempo do vídeo:** `6:10:08 / 12:00:00` | Capítulo: `37. rock paper scissors >`.
* **No editor de código (`Main.java`):**
  * O professor preencheu todos os parênteses que estavam vazios na Parte 5.
  * Linha 29: `if(playerChoice.equals(computerChoice)){` $\rightarrow$ imprime `"It's a tie!"`.
  * **O Grande Destaque Azul (Linhas 32 a 40):** O bloco inteiro das 3 vitórias está selecionado com fundo azul vibrante:
    * Linhas 32-34: `else if(playerChoice.equals("rock") && computerChoice.equals("scissors"))`
    * Linhas 35-37: `else if(playerChoice.equals("paper") && computerChoice.equals("rock"))`
    * Linhas 38-40: `else if(playerChoice.equals("scissors") && computerChoice.equals("paper"))`
  * Linhas 41-43: `else { System.out.println("You lose!"); }`.
  * Na linha 32 há um ícone de lâmpada amarela do IntelliJ à esquerda.
  * O cursor do mouse está sobre a palavra `if` da linha 32.

---

### 📸 Imagem 2 (`media_1791244898448.png` — Minuto `6:10:20`): A Vitória Celebrada no Console!
* **Barra de tempo do vídeo:** `6:10:20 / 12:00:00` (12 segundos depois).
* **Na janela de Console (Terminal Inferior):**
  * O professor executou o programa e jogou ao vivo:
    ```text
    Enter your move (rock, paper, scissors): rock
    Computer choice: scissors
    You win!

    Process finished with exit code 0
    ```
  * O professor escolheu **`rock`** *(Pedra)*.
  * O computador sorteou **`scissors`** *(Tesoura)*.
  * Como a Pedra quebra a Tesoura, o Java imprimiu: **`You win!`**.
  * **O Destaque Azul no Console:** A frase **`You win!`** está selecionada com uma **faixa azul retangular**, mostrando que o professor arrastou o mouse sobre ela para comemorar com os alunos a vitória do ser humano sobre a máquina!

---

## 2. O que o Professor Adicionou e Explicou Neste Momento

Nesta passagem, o professor conclui a parte mais importante de qualquer jogo: as **Regras de Vitória**:

1. **A Checagem do Empate:** Compara as duas variáveis diretamente com `playerChoice.equals(computerChoice)`. Se forem iguais, avisa que empatou e nem perde tempo calculando o resto.
2. **As 3 Formas de Vitória do Humano:** Ele cria três blocos `else if`, cobrindo as 3 combinações clássicas onde o jogador vence.
3. **A Captura Automática da Derrota (`else`):** Ele não precisa programar quando a máquina ganha! Se o jogo não empatou e o humano não ganhou, a única coisa que sobrou no universo é a vitória do robô!
4. **O Teste Prático Triunfante:** Mostra a Pedra esmagando a Tesoura na prática do console!

---

## 3. Por que as Linhas 32 a 40 Aparecem Selecionadas em Azul no Editor?

Na Imagem 1, vemos que o professor selecionou com o mouse todas as 9 linhas que contêm os três `else if` de vitória.

### O Motivo Didático do Professor Bro Code:
* O professor faz isso para isolar visualmente o conceito para quem está aprendendo:  
  *"Pessoal, reparem neste bloco azul: estas são as ÚNICAS três situações em todo o universo em que um jogador de Pedra, Papel e Tesoura sai vitorioso!"*
* Ele lê cada uma das 3 linhas com os alunos para provar que a lógica espelha com 100% de fidelidade as regras da vida real:
  1. *Se você jogou pedra E o robô jogou tesoura $\rightarrow$ Você venceu!*
  2. *Se você jogou papel E o robô jogou pedra $\rightarrow$ Você venceu!*
  3. *Se você jogou tesoura E o robô jogou papel $\rightarrow$ Você venceu!*

---

## 4. As 3 Regras de Ouro da Vitória do Ser Humano

Vamos analisar cada um dos 3 `else if`:

```java
// Regra 1: Pedra esmaga Tesoura
else if(playerChoice.equals("rock") && computerChoice.equals("scissors")){
    System.out.println("You win!");
}
// Regra 2: Papel cobre Pedra
else if(playerChoice.equals("paper") && computerChoice.equals("rock")){
    System.out.println("You win!");
}
// Regra 3: Tesoura corta Papel
else if(playerChoice.equals("scissors") && computerChoice.equals("paper")){
    System.out.println("You win!");
}
```

### Por que Usamos o Operador `&&` (E Lógico)?
Porque para você vencer, **duas coisas precisam acontecer ao mesmo tempo**:
* Não basta você jogar pedra. A máquina **tem que ter jogado tesoura**!
* Se você jogar pedra e a máquina jogar papel, você não venceu.
* O operador `&&` garante que o Java só diga `"You win!"` se ambas as metades da frase forem verdadeiras!

---

## 5. A Genialidade do `else` Final: A Eliminação Matemática das 9 Possibilidades

Em um jogo de Pedra, Papel e Tesoura, existem exatamente $3 \times 3 = 9$ confrontos possíveis na matemática:

| # | Jogador | Computador | O que Acontece? | Onde o Java Trata Isso no Código? |
| :---: | :---: | :---: | :---: | :---: |
| 1 | `rock` | `rock` | Empate | Tratado na Linha 29 (`if`) |
| 2 | `paper` | `paper` | Empate | Tratado na Linha 29 (`if`) |
| 3 | `scissors` | `scissors` | Empate | Tratado na Linha 29 (`if`) |
| 4 | `rock` | `scissors` | **Vitória do Jogador** | Tratado na Linha 32 (`else if 1`) |
| 5 | `paper` | `rock` | **Vitória do Jogador** | Tratado na Linha 35 (`else if 2`) |
| 6 | `scissors` | `paper` | **Vitória do Jogador** | Tratado na Linha 38 (`else if 3`) |
| 7 | `rock` | `paper` | Vitória da Máquina | **Cai no `else` da Linha 41!** |
| 8 | `paper` | `scissors` | Vitória da Máquina | **Cai no `else` da Linha 41!** |
| 9 | `scissors` | `rock` | Vitória da Máquina | **Cai no `else` da Linha 41!** |

### A Lição de Ouro do Programador Esperto:
* Um programador amador perderia tempo escrevendo mais 3 blocos `else if` gigantescos só para testar as derrotas.
* O programador inteligente (Bro Code) usa a **Eliminação Matemática**:  
  Se já testamos os 3 empates e já testamos as 3 vitórias do humano, os únicos 3 casos que sobraram no mundo são derrotas!  
  Portanto, basta colocar um simples **`else { System.out.println("You lose!"); }`**!

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado na tela:

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
29:         if(playerChoice.equals(computerChoice)){
30:             System.out.println("It's a tie!");
31:         }
32:         else if(playerChoice.equals("rock") && computerChoice.equals("scissors")){
33:             System.out.println("You win!");
34:         }
35:         else if(playerChoice.equals("paper") && computerChoice.equals("rock")){
36:             System.out.println("You win!");
37:         }
38:         else if(playerChoice.equals("scissors") && computerChoice.equals("paper")){
39:             System.out.println("You win!");
40:         }
41:         else{
42:             System.out.println("You lose!");
43:         }
44: 
45:         // ASK TO PLAY AGAIN?
46:         // GOODBYE MESSAGE
47: 
48:         scanner.close();
49:     }
50: }
```

---

## 7. Explicação Cirúrgica Linha por Linha

---

### Linhas 29 a 31: O Empate
```java
29:         if(playerChoice.equals(computerChoice)){
30:             System.out.println("It's a tie!");
31:         }
```
* **O que faz:** Compara se o conteúdo da String `playerChoice` é idêntico a `computerChoice`.
* Se for verdade, avisa que deu empate. Se for falso, desce para o primeiro `else if`.

---

### Linhas 32 a 34: Pedra contra Tesoura
```java
32:         else if(playerChoice.equals("rock") && computerChoice.equals("scissors")){
33:             System.out.println("You win!");
34:         }
```
* **O que faz:** Testa se o humano jogou `"rock"` e a máquina sorteou `"scissors"`.
* Em caso positivo, imprime `"You win!"` e salta direto para a linha 45, ignorando todo o resto.

---

### Linhas 35 a 37: Papel contra Pedra
```java
35:         else if(playerChoice.equals("paper") && computerChoice.equals("rock")){
36:             System.out.println("You win!");
37:         }
```
* **O que faz:** Testa a segunda possibilidade de vitória: papel cobrindo a pedra.

---

### Linhas 38 a 40: Tesoura contra Papel
```java
38:         else if(playerChoice.equals("scissors") && computerChoice.equals("paper")){
39:             System.out.println("You win!");
40:         }
```
* **O que faz:** Testa a terceira possibilidade de vitória: tesoura retalhando o papel.

---

### Linhas 41 a 43: A Derrota Inevitável (`else`)
```java
41:         else{
42:             System.out.println("You lose!");
43:         }
```
* **O que faz:** Se o código chegou até aqui, significa que não empatou e o humano não venceu em nenhuma das 3 formas. Portanto, o computador venceu!

---

## 8. Teste de Mesa Passo a Passo do Duelo da Tela 2

Acompanhe a avaliação lógica da CPU durante a partida exibida na Imagem 2:

1. **Valores na Memória Stack:**
   * `playerChoice = "rock"`
   * `computerChoice = "scissors"`
2. **Teste da Linha 29 (Empate):**  
   * `"rock".equals("scissors")`? $\rightarrow$ **FALSO (False)**!
   * O Java desce para a linha 32.
3. **Teste da Linha 32 (Vitória 1):**  
   * `playerChoice.equals("rock")` $\rightarrow$ `"rock".equals("rock")`? **VERDADEIRO (True)**!  
   * `computerChoice.equals("scissors")` $\rightarrow$ `"scissors".equals("scissors")`? **VERDADEIRO (True)**!  
   * Conjunção: `true && true` $\rightarrow$ **VERDADEIRO (`true`)**!
4. **Execução:**  
   * A porta se abre e a linha 33 roda:  
     👉 **`System.out.println("You win!");`**
5. **O Efeito do Encadeamento `else`:**  
   * As linhas 35, 38 e 41 são **completamente ignoradas**! O Java salta direto para o final da estrutura!

---

## 9. Por que o Texto `"You win!"` Aparece Selecionado em Azul no Console?

Na Imagem 2, vemos o resultado:
```text
Enter your move (rock, paper, scissors): rock
Computer choice: scissors
You win!
```
com a linha `You win!` destacada em azul pelo mouse.

### O Motivo Didático do Professor:
* O professor faz questão de selecionar `You win!` com o mouse para provar aos estudantes:  
  *"Funcionou perfeitamente! Quando jogamos Pedra contra a Tesoura do computador, nosso primeiro `else if` capturou a jogada e consagrou o jogador como vencedor!"*

---

## 10. Resumo Prático em Uma Tabela Mental

| Confronto | Quem Ganha? | Qual Linha do Java Dispara? |
| :---: | :---: | :---: |
| **Jogadas Iguais** | Ninguém (Empate) | Linha 29: `if (player.equals(computer))` |
| **`rock` vs `scissors`** | Humano Vence | Linha 32: `else if ("rock" && "scissors")` *(Duelo do vídeo!)* |
| **`paper` vs `rock`** | Humano Vence | Linha 35: `else if ("paper" && "rock")` |
| **`scissors` vs `paper`** | Humano Vence | Linha 38: `else if ("scissors" && "paper")` |
| **Qualquer Outro Caso** | Computador Vence | Linha 41: `else { "You lose!"; }` |
