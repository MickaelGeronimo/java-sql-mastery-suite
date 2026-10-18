# ☕ Curso de Java — Capítulo 36: `⭐ quiz game 💯` (Parte 7)
## A Pontuação e o Gran Finale: O Incremento de Pontos (`score++`), o Placar Final Dinâmico (`questions.length`) e o Jogo Completo em Ação!

> **Baseado na aula do canal Bro Code (Minuto `5:57:45` a `5:58:30` de 12:00:00)**  
> **Tema:** O professor finaliza as duas últimas peças que faltavam para o jogo ficar 100% completo: adiciona o incremento de pontuação **`score++;`** dentro do bloco `if` (para somar pontos apenas quando o jogador acertar) e substitui o comentário final pelo anúncio oficial da nota do jogador: **`System.out.println("Your final score is: " + score + " out of " + questions.length);`**. Em seguida, ele joga uma partida real no console, demonstrando o que acontece com a pontuação quando o usuário erra!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 3 Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-3-telas-para-o-google-docs)
2. [O que o Professor Adicionou e Explicou Neste Momento](#2-o-que-o-professor-adicionou-e-explicou-neste-momento)
3. [A Anatomia do `score++`: O Operador de Incremento em Ação](#3-a-anatomia-do-score-o-operador-de-incremento-em-ação)
4. [A Sacada de Engenharia: Por que Usar `questions.length` e Não o Número 5?](#4-a-sacada-de-engenharia-por-que-usar-questionslength-e-não-o-número-5)
5. [O Código Java Completo, Numerado e Definitivo](#5-o-código-java-completo-numerado-e-definitivo)
6. [Explicação Cirúrgica Linha por Linha](#6-explicação-cirúrgica-linha-por-linha)
7. [Teste de Mesa Passo a Passo: A Contabilidade da Pontuação na Memória](#7-teste-de-mesa-passo-a-passo-a-contabilidade-da-pontuação-na-memória)
8. [Dissecação da Partida no Console (Tela 3)](#8-dissecação-da-partida-no-console-tela-3)
9. [O que Sai no Console ao Final do Jogo](#9-o-que-sai-no-console-ao-final-do-jogo)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das 3 Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de cada uma das 3 imagens enviadas:

### 📸 Tela 1 (`media_1791244281896.png`): Adicionando o Ponto do Jogador
* **No editor de código (`Main.java`):**
  * Dentro do bloco `if (guess == answers[i])`, logo abaixo da linha 44 (que fecha a moldura do `CORRECT!`), o professor digita:
    * **Linha 45:** `score++;`
  * À esquerda da linha 45 há um pequeno marcador vermelho.
  * O cursor de texto está logo após o ponto e vírgula de `score++;`.
  * Na linha 54, ainda vemos o comentário de planejamento: `// DISPLAY FINAL SCORE`.

---

### 📸 Tela 2 (`media_1791244296284.png`): O Placar Final e o Botão de Play
* **No editor de código (`Main.java`):**
  * O professor removeu o comentário `// DISPLAY FINAL SCORE` e digitou a linha completa de exibição do placar:
    * **Linha 54:** `System.out.println("Your final score is: " + score + " out of " + questions.length);`
  * A palavra `score` na linha 54 está com um sublinhado cinza suave (foco da IDE).
  * **No topo direito da janela:** A setinha do mouse do professor está posicionada exatamente em cima do ícone verde de **Play (▶️ Run)** para iniciar a partida definitiva do jogo!

---

### 📸 Tela 3 (`media_1791244308151.png`): A Partida em Andamento no Console
* **Na janela de Console (Terminal Inferior):**
  * O professor jogou até a Pergunta 3 (*"What year was Facebook launched?"*):
    ```text
    What year was Facebook launched?
    1. 2000
    2. 2004
    3. 2006
    4. 2008
    Enter your guess: 1
    ********
     WRONG! 
    ********
    ```
  * O professor digitou **`1`** (ano 2000), que está errado (o certo é 2004, opção 2).
  * O programa exibiu o banner ` WRONG! ` e **NÃO somou ponto** no `score`!
  * Em seguida, o laço avançou imediatamente para a Pergunta 4:
    ```text
    Who is known as the father of computers?
    1. Steve Jobs
    2. Bill Gates
    3. Alan Turing
    4. Charles Babbage
    Enter your guess: |
    ```
  * O cursor de texto pisca ao lado de `Enter your guess: `, esperando o próximo palpite!

---

## 2. O que o Professor Adicionou e Explicou Neste Momento

Nesta etapa crucial, o professor fecha as duas últimas portas do algoritmo:

1. **A Contabilização dos Acertos (`score++;`):**  
   Até a Parte 6, o programa avisava `"CORRECT!"`, mas a variável `score` continuava valendo 0 porque tínhamos esquecido de incrementá-la. Agora, a cada acerto, o Java soma exatamente `+1` no placar!
2. **O Exibidor do Placar Final:**  
   Colocado **fora e depois** do laço `for`. Só roda quando o jogador respondeu todas as 5 perguntas.
3. **O Uso de Código Limpo e Escalável:**  
   Em vez de escrever `" out of 5"`, ele usou `" out of " + questions.length`.
4. **O Teste da Resiliência:**  
   Ele erra uma pergunta de propósito no meio do jogo para garantir que o placar não some pontos indevidos no caso de erro!

---

## 3. A Anatomia do `score++`: O Operador de Incremento em Ação

Na linha 45, temos:
```java
score++;
```

### O que Isso Significa em Java para um Iniciante?
* A variável `score` começou o jogo valendo `0` (lá na linha 22: `int score = 0;`).
* A expressão `score++` é um atalho elegante para:
  $$\text{score} = \text{score} + 1;$$
* Ela pega o valor atual que está guardado na caixinha `score`, soma 1 e guarda de volta na mesma caixinha!

### Por que Ele Fica Apenas Dentro do `if`?
* Se o jogador acertar $\rightarrow$ entra no `if` $\rightarrow$ o Java imprime `CORRECT!` e executa `score++;` (ganha 1 ponto!).
* Se o jogador errar $\rightarrow$ entra no `else` $\rightarrow$ o Java imprime ` WRONG! ` e **o `score++` é ignorado** (não ganha ponto!).

---

## 4. A Sacada de Engenharia: Por que Usar `questions.length` e Não o Número 5?

Olhe com atenção para a linha 54:

```java
System.out.println("Your final score is: " + score + " out of " + questions.length);
```

Por que o professor **NÃO** escreveu assim?
```java
// O jeito AMADOR e PERIGOSO:
System.out.println("Your final score is: " + score + " out of 5");
```

### A Explicação de Arquitetura (*Clean Code*):
Imagine que amanhã o seu chefe peça: *"Adicione mais 10 perguntas nesse quiz!"*.
* Se você tivesse escrito `" out of 5"`, o jogo agora teria 15 perguntas, mas continuaria dizendo na tela: *"Sua pontuação final é: 12 out of 5"* (o que seria uma vergonha!).
* Ao usar **`questions.length`**, o Java calcula o total de perguntas dinamicamente:
  * Se o array tiver 5 perguntas $\rightarrow$ mostra `out of 5`.
  * Se você colocar 50 perguntas $\rightarrow$ mostra automaticamente `out of 50` sem você precisar alterar uma única linha de código no final!
* Isso se chama **código escalável e livre de valores mágicos** (*Hardcoded Magic Numbers*)!

---

## 5. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, 100% finalizado e funcional:

```java
1:  import java.util.Scanner;
2:  
3:  public class Main {
4:  
5:      public static void main(String[] args) {
6:  
7:          // JAVA QUIZ GAME
8:  
9:          String[] questions = {"What is the main function of a router?",
10:                               "Which part of the computer is considered the brain?",
11:                               "What year was Facebook launched?",
12:                               "Who is known as the father of computers?",
13:                               "What was the first programming language?"};
14: 
15:         String[][] options = {{"1. Storing files", "2. Encrypting data", "3. Directing internet traffic", "4. Managing passwords"},
16:                               {"1. CPU", "2. Hard Drive", "3. RAM", "4. GPU"},
17:                               {"1. 2000", "2. 2004", "3. 2006", "4. 2008"},
18:                               {"1. Steve Jobs", "2. Bill Gates", "3. Alan Turing", "4. Charles Babbage"},
19:                               {"1. COBOL", "2. C", "3. Fortran", "4. Assembly"}};
20: 
21:         int[] answers = {3, 1, 2, 4, 3};
22:         int score = 0;
23:         int guess;
24: 
25:         Scanner scanner = new Scanner(System.in);
26: 
27:         System.out.println("*******************************");
28:         System.out.println("Welcome to the Java Quiz Game!");
29:         System.out.println("*******************************");
30: 
31:         for(int i = 0; i < questions.length; i++){
32:             System.out.println(questions[i]);
33: 
34:             for(String option : options[i]){
35:                 System.out.println(option);
36:             }
37: 
38:             System.out.print("Enter your guess: ");
39:             guess = scanner.nextInt();
40: 
41:             if(guess == answers[i]){
42:                 System.out.println("********");
43:                 System.out.println("CORRECT!");
44:                 System.out.println("********");
45:                 score++;
46:             }
47:             else{
48:                 System.out.println("********");
49:                 System.out.println(" WRONG! ");
50:                 System.out.println("********");
51:             }
52:         }
53: 
54:         System.out.println("Your final score is: " + score + " out of " + questions.length);
55: 
56:         scanner.close();
57:     }
58: }
```

---

## 6. Explicação Cirúrgica Linha por Linha

---

### Linha 45: A Pontuação
```java
45:                 score++;
```
* **O que faz:** Adiciona 1 ponto na variável inteira `score`.
* **Quando executa?** Apenas se a condição da linha 41 for verdadeira (`guess == answers[i]`).
* **O que acontece na memória:** O valor de 32 bits guardado na variável `score` na Stack do método `main` é incrementado em 1.

---

### Linha 52: O Fim do Laço de Repetição
```java
52:         }
```
* Fecha o laço `for (int i = 0; ...)`.
* Quando o jogador terminar de responder a 5ª pergunta (índice 4), o Java bate nesta chave, percebe que `i` virou 5 e encerra o laço definitivamente.
* A execução desce para a linha 54!

---

### Linha 54: A Exibição do Placar Final
```java
54:         System.out.println("Your final score is: " + score + " out of " + questions.length);
```
* **O que faz:** Monta uma frase bonita concatenando texto fixo com variáveis:
  * `"Your final score is: "`
  * `+ score` (substitui pelo número de acertos, ex.: 4)
  * `" out of "`
  * `+ questions.length` (substitui pelo tamanho total do array, que é 5)
* **Resultado impresso no console:**  
  `Your final score is: 4 out of 5`

---

### Linha 56: O Fechamento dos Recursos
```java
56:         scanner.close();
```
* Fecha o objeto `scanner` e libera a memória e o leitor do teclado do sistema operacional.

---

## 7. Teste de Mesa Passo a Passo: A Contabilidade da Pontuação na Memória

Veja como a variável `score` se comporta na memória Stack durante uma partida completa:

| Rodada (`i`) | Pergunta | Chute do Jogador (`guess`) | Gabarito (`answers[i]`) | Acertou? | Executa `score++`? | Valor de `score` na Stack |
| :---: | :--- | :---: | :---: | :---: | :---: | :---: |
| **Início** | *(Nenhuma)* | — | — | — | — | **`0`** |
| **`i = 0`** | Roteador | `3` | `3` | **SIM** | Sim! | `0 + 1 = ` **`1`** |
| **`i = 1`** | CPU | `1` | `1` | **SIM** | Sim! | `1 + 1 = ` **`2`** |
| **`i = 2`** | Facebook | `1` | `2` | **NÃO** | **Não!** | Mantém **`2`** |
| **`i = 3`** | Pai dos PCs | `4` | `4` | **SIM** | Sim! | `2 + 1 = ` **`3`** |
| **`i = 4`** | Fortran | `3` | `3` | **SIM** | Sim! | `3 + 1 = ` **`4`** |
| **Fim** | **Placar Final** | — | — | — | — | Imprime: **`4 out of 5`** |

---

## 8. Dissecação da Partida no Console (Tela 3)

Na Imagem 3, vemos o professor jogando ao vivo:

1. **Pergunta 3:**  
   `What year was Facebook launched?`  
   Opções: `1. 2000`, `2. 2004`, `3. 2006`, `4. 2008`.
2. **O Chute do Professor:**  
   Ele digita `1` (que corresponde ao ano 2000).
3. **A Correção:**  
   Como o gabarito oficial é `2` (2004), o Java entra no `else`:
   ```text
   ********
    WRONG! 
   ********
   ```
4. **O Laço Continua sem Travar:**  
   O Java não se abala pelo erro: avança para a Pergunta 4, desenha as opções e pergunta novamente:
   ```text
   Who is known as the father of computers?
   1. Steve Jobs
   2. Bill Gates
   3. Alan Turing
   4. Charles Babbage
   Enter your guess: |
   ```

---

## 9. O que Sai no Console ao Final do Jogo

Se o jogador acertar 4 perguntas e errar 1, a tela final de encerramento do console será:

```text
What was the first programming language?
1. COBOL
2. C
3. Fortran
4. Assembly
Enter your guess: 3
********
CORRECT!
********
Your final score is: 4 out of 5

Process finished with exit code 0
```

---

## 10. Resumo Prático em Uma Tabela Mental

| Instrução Java | Onde Mora no Código? | Qual é a Missão Dela? |
| :--- | :--- | :--- |
| **`int score = 0;`** | Antes de tudo (Linha 22) | Cria o placar zerado na memória. |
| **`score++;`** | Dentro do `if` (Linha 45) | Soma +1 ponto a cada resposta correta. |
| **`questions.length`** | Dentro do placar (Linha 54) | Informa dinamicamente o número total de questões. |
| **`System.out.println("Your final score...")`** | Fora do laço (Linha 54) | Mostra o resultado final consolidado. |
| **`scanner.close();`** | No encerramento (Linha 56) | Libera os recursos e limpa a memória. |
