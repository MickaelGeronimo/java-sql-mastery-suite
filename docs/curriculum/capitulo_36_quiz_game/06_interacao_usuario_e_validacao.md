# ☕ Curso de Java — Capítulo 36: `⭐ quiz game 💯` (Parte 6)
## A Interatividade do Quiz: Capturando o Chute (`scanner.nextInt()`), Validando com o Gabarito (`guess == answers[i]`) e os Banners de `CORRECT!` e `WRONG!`

> **Baseado na aula do canal Bro Code (Minuto `5:55:38` a `5:57:45` de 12:00:00)**  
> **Tema:** O professor transforma o quiz em um jogo verdadeiramente interativo! Ele posiciona o leitor de teclado logo após a exibição das alternativas de cada pergunta, captura o número digitado pelo jogador (`guess = scanner.nextInt();`) e compara com o gabarito oficial através da condição `if (guess == answers[i])`. Ele projeta molduras simétricas perfeitas de 8 caracteres para o feedback (`CORRECT!` e ` WRONG! `) e testa ambos os caminhos ao vivo no console: acerta a primeira pergunta e erra de propósito a última para mostrar o funcionamento do `else`!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 4 Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-4-telas-para-o-google-docs)
2. [O que o Professor Fez e Explicou Passo a Passo](#2-o-que-o-professor-fez-e-explicou-passo-a-passo)
3. [A Posição Crucial: Por que a Leitura Fica DENTRO do Laço Externo?](#3-a-posição-crucial-por-que-a-leitura-fica-dentro-do-laço-externo)
4. [A Engenharia da Condição: Como `guess == answers[i]` Funciona](#4-a-engenharia-da-condição-como-guess--answersi-funciona)
5. [A Perfeição Geométrica dos 8 Caracteres: O Design dos Banners](#5-a-perfeição-geométrica-dos-8-caracteres-o-design-dos-banners)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Cirúrgica Linha por Linha](#7-explicação-cirúrgica-linha-por-linha)
8. [Dissecação dos Dois Testes Práticos no Console](#8-dissecação-dos-dois-testes-práticos-no-console)
9. [Por que o Bloco `WRONG!` Está Destacado em Azul na Tela 4?](#9-por-que-o-bloco-wrong-está-destacado-em-azul-na-tela-4)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das 4 Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de cada uma das 4 imagens apresentadas no vídeo:

### 📸 Tela 1 (`media_1791243681291.png` — Minuto `5:55:38`): O Cursor Posicionado no Miolo
* **Barra de status do vídeo:** `5:55:38 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * O cursor de texto pisca sozinho na **Linha 37**, exatamente abaixo do fechamento da chave `}` do laço interno das opções e antes do fechamento da chave da linha 38 (o laço externo).
  * Abaixo, continuam os comentários de planejamento: `// GET GUESS FROM USER`, `// CHECK OUR GUESS` e `// DISPLAY FINAL SCORE`.

---

### 📸 Tela 2 (`media_1791243727473.png` — Minuto `5:57:11`): O Código de Captura e Validação Completo
* **Barra de status do vídeo:** `5:57:11 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * O professor removeu os comentários `// GET GUESS...` e `// CHECK OUR GUESS` e escreveu o código completo:
    * Linha 38: `System.out.print("Enter your guess: ");`
    * Linha 39: `guess = scanner.nextInt();`
    * Linha 41: `if(guess == answers[i]){`
    * Linhas 42 a 44: Três `println` imprimindo a moldura com `CORRECT!`.
    * Linha 46: `else{`
    * Linhas 47 a 49: Três `println` imprimindo a moldura com ` WRONG! `.
  * O cursor de texto pisca na linha 48, logo após `WRONG!`.

---

### 📸 Tela 3 (`media_1791243735465.png` — Minuto `5:57:27`): O Teste do Acerto (Pergunta 1)
* **Barra de status do vídeo:** `5:57:27 / 12:00:00`.
* **No Console de Execução:**
  * O jogo exibe a Pergunta 1 (*"What is the main function of a router?"*) e suas 4 opções.
  * O programa pausa na mensagem `Enter your guess: ` e o professor digita o número **`3`** e aperta Enter!
  * O console imprime imediatamente o bloco de parabéns:
    ```text
    ********
    CORRECT!
    ********
    ```
  * Em seguida, o laço avança automaticamente para a Pergunta 2 (*"Which part of the computer is considered the brain?"*), exibe suas opções (1. CPU, 2. Hard Drive, etc.) e fica com o cursor piscando aguardando o próximo chute: `Enter your guess: |`.

---

### 📸 Tela 4 (`media_1791243745885.png` — Minuto `5:57:45`): O Teste do Erro Proposital (Pergunta 5)
* **Barra de status do vídeo:** ~`5:57:45 / 12:00:00`.
* **No Console de Execução:**
  * O professor joga até a última questão: *"What was the first programming language?"*.
  * As opções aparecem: `1. COBOL`, `2. C`, `3. Fortran`, `4. Assembly`.
  * A resposta certa é `3` (Fortran). Mas o professor **digita de propósito o número `2`** (C) para testar se o caminho do `else` está funcionando!
  * O console imprime a moldura de erro:
    ```text
    ********
     WRONG! 
    ********
    ```
  * **O Destaque Azul:** O professor seleciona com o mouse todo o bloco do `WRONG!` em azul para provar que a condição capturou o erro perfeitamente!
  * Abaixo, aparece a mensagem de encerramento do Java: `Process finished with exit code 0`.

---

## 2. O que o Professor Fez e Explicou Passo a Passo

O professor acabou de conectar a entrada do jogador com a lógica de decisão do computador:

1. **Pediu o palpite com `System.out.print`:** Usou `print` em vez de `println` para que o jogador digite o número na mesma linha, logo após os dois pontos (`:`).
2. **Leu o número com `scanner.nextInt()`:** Capturou o número inteiro digitado e guardou dentro da variável `guess`.
3. **Fez a checagem lógica com `if (guess == answers[i])`:** Comparou o número digitado com o número que estava guardado na posição `i` do gabarito `answers`.
4. **Programou os dois desfechos possíveis:**
   * Se os números forem idênticos $\rightarrow$ Imprime o banner `CORRECT!`.
   * Se os números forem diferentes $\rightarrow$ Entra no `else` e imprime o banner ` WRONG! `.
5. **Realizou o Teste Completo:** Provou que o código responde corretamente tanto quando o usuário acerta quanto quando o usuário erra!

---

## 3. A Posição Crucial: Por que a Leitura Fica DENTRO do Laço Externo?

Observe atentamente onde as linhas 38 a 50 foram colocadas:

```java
for (int i = 0; i < questions.length; i++) { // <-- INÍCIO DO LAÇO EXTERNO
    System.out.println(questions[i]);

    for (String option : options[i]) {        // <-- LAÇO INTERNO (Só opções)
        System.out.println(option);
    }                                         // <-- FIM DO LAÇO INTERNO

    // A LEITURA E A CHECAGEM FICAM AQUI!
    System.out.print("Enter your guess: ");
    guess = scanner.nextInt();

    if (guess == answers[i]) { ... }
    else { ... }

}                                             // <-- FIM DO LAÇO EXTERNO
```

### O Raciocínio Lógico:
* **Depois** que as 4 alternativas já foram desenhadas na tela pelo laço interno, o computador precisa parar e **esperar** o ser humano pensar e digitar.
* Isso tem que acontecer **a cada volta** do laço externo, para que o jogador responda pergunta por pergunta, e não tudo de uma vez no final!

---

## 4. A Engenharia da Condição: Como `guess == answers[i]` Funciona

Lembre-se da nossa tabela do gabarito:
```java
int[] answers = {3, 1, 2, 4, 3};
```

Quando o programa está rodando:
* **Na Pergunta 0 (Roteador):** `i = 0`. O gabarito `answers[0]` vale `3`.  
  Se o jogador digitar `3` $\rightarrow$ `3 == 3` dá **VERDADEIRO** $\rightarrow$ Imprime `CORRECT!`.
* **Na Pergunta 4 (Primeira Linguagem):** `i = 4`. O gabarito `answers[4]` vale `3`.  
  Se o jogador digitar `2` $\rightarrow$ `2 == 3` dá **FALSO** $\rightarrow$ Pula para o `else` e imprime ` WRONG! `.

Tudo funciona de forma dinâmica e automática, sem você precisar escrever um monte de `if` separado para cada pergunta!

---

## 5. A Perfeição Geométrica dos 8 Caracteres: O Design dos Banners

Repare no cuidado estético que o professor teve ao desenhar as molduras de resposta:

```java
System.out.println("********"); // 8 asteriscos
System.out.println("CORRECT!"); // 8 letras
System.out.println("********"); // 8 asteriscos

System.out.println("********"); // 8 asteriscos
System.out.println(" WRONG! "); // 1 espaço + 6 letras + 1 espaço = 8 caracteres!
System.out.println("********"); // 8 asteriscos
```

### Por que ele colocou espaços antes e depois de `WRONG!`?
* A palavra `CORRECT!` tem exatamente **8 caracteres** (`C-O-R-R-E-C-T-!`).
* A palavra `WRONG!` tem apenas **6 caracteres** (`W-R-O-N-G-!`).
* Se ele não colocasse os espaços, a palavra `WRONG!` ficaria menor que a linha de 8 asteriscos e a moldura ficaria torta!
* Ao colocar `" WRONG! "`, a linha atinge exatamente **8 caracteres**, ficando perfeitamente alinhada e centralizada com as bordas superior e inferior! Isso é capricho de profissional!

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado na Tela 2:

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
45:             }
46:             else{
47:                 System.out.println("********");
48:                 System.out.println(" WRONG! ");
49:                 System.out.println("********");
50:             }
51:         }
52: 
53:         // DISPLAY FINAL SCORE
54: 
55:         scanner.close();
56:     }
57: }
```

---

## 7. Explicação Cirúrgica Linha por Linha

---

### Linha 38: O Prompt do Chute com `print`
```java
38:             System.out.print("Enter your guess: ");
```
* **O que faz:** Imprime a mensagem de convite na tela: *"Digite seu chute: "*.
* **Atenção ao `print`:** Como é `print` (e não `println`), o cursor fica parado **ao lado dos dois pontos e do espaço**. O número que o usuário digitar aparecerá na mesma linha horizontal!
* **Saída no console:** `Enter your guess: ` (aguardando digitação).

---

### Linha 39: A Captura do Teclado com `scanner.nextInt()`
```java
39:             guess = scanner.nextInt();
```
* **O que faz:** O programa **congela a execução** e fica esperando o jogador digitar um número no teclado e apertar a tecla **ENTER**.
* Assim que o usuário aperta ENTER, o método `nextInt()` extrai aquele número inteiro e o armazena na variável `guess`.
* **Exemplo:** Se o jogador digitou `3`, a variável `guess` passa a valer `3`.

---

### Linha 41: A Estrutura Condicional `if`
```java
41:             if(guess == answers[i]){
```
* **O que faz:** Testa se o chute do jogador (`guess`) é exatamente igual (`==`) ao número que está na posição `i` do array `answers`.
* **Se for igual:** Executa o bloco das linhas 42 a 45.
* **Se for diferente:** Pula direto para a linha 46 (`else`).

---

### Linhas 42 a 45: O Feedback Positivo
```java
42:                 System.out.println("********");
43:                 System.out.println("CORRECT!");
44:                 System.out.println("********");
45:             }
```
* Imprime a moldura de 8 estrelas com a palavra `CORRECT!` no centro.

---

### Linhas 46 a 50: O Feedback Negativo (`else`)
```java
46:             else{
47:                 System.out.println("********");
48:                 System.out.println(" WRONG! ");
49:                 System.out.println("********");
50:             }
```
* É acionado caso o jogador tenha chutado qualquer outro número que não seja o gabarito oficial. Imprime ` WRONG! ` entre estrelas.

---

### Linha 51: Fechamento do Laço Externo
```java
51:         }
```
* Indica o fim de uma pergunta completa. O Java executa `i++` e inicia a próxima questão, repetindo todo o ciclo (pergunta $\rightarrow$ opções $\rightarrow$ chute $\rightarrow$ correção) até `i` atingir 5.

---

## 8. Dissecação dos Dois Testes Práticos no Console

### Teste 1 (Tela 3): Acertando a Pergunta 1
* **Console exibe:**
  ```text
  What is the main function of a router?
  1. Storing files
  2. Encrypting data
  3. Directing internet traffic
  4. Managing passwords
  Enter your guess: 3
  ```
* **O que o Java avalia:**
  * `i = 0`
  * `guess = 3`
  * `answers[0] = 3`
  * Teste: `3 == 3`? **SIM (Verdadeiro)**!
* **Resultado exibido no console:**
  ```text
  ********
  CORRECT!
  ********
  ```

---

### Teste 2 (Tela 4): Errando a Pergunta 5 de Propósito
* **Console exibe:**
  ```text
  What was the first programming language?
  1. COBOL
  2. C
  3. Fortran
  4. Assembly
  Enter your guess: 2
  ```
* **O que o Java avalia:**
  * `i = 4`
  * `guess = 2`
  * `answers[4] = 3`
  * Teste: `2 == 3`? **NÃO (Falso)**!
* **Resultado exibido no console:**
  ```text
  ********
   WRONG! 
  ********
  ```

---

## 9. Por que o Bloco `WRONG!` Está Destacado em Azul na Tela 4?

Ao olhar para a Imagem 4, você nota que o bloco final:
```text
********
 WRONG! 
********
```
está com uma faixa azul de seleção cobrindo as 3 linhas.

### O Motivo Didático do Professor:
* O professor seleciona esse bloco com o mouse para provar aos estudantes:  
  *"Vejam! Quando digitamos uma resposta errada (como o 2 em vez do 3), o programa não trava, não quebra e não dá erro de sistema: ele simplesmente entra no bloco `else` e avisa educadamente que a resposta está incorreta!"*
* Ele demonstra que cobriu **100% dos fluxos lógicos possíveis** da rodada.

---

## 10. Resumo Prático em Uma Tabela Mental

| Linha | Código Java | O que Faz? | Detalhe Crucial |
| :---: | :--- | :--- | :--- |
| **38** | `System.out.print("Enter your guess: ");` | Pergunta a resposta | Usa `print` para o usuário digitar na mesma linha. |
| **39** | `guess = scanner.nextInt();` | Lê o número digitado | Pausa o programa até o jogador apertar Enter. |
| **41** | `if(guess == answers[i])` | Compara com o gabarito | Usa `==` para testar igualdade de números inteiros. |
| **43** | `System.out.println("CORRECT!");` | Mostra vitória | Tem 8 caracteres para bater com as 8 estrelas. |
| **48** | `System.out.println(" WRONG! ");` | Mostra derrota | Tem 8 caracteres (com espaços) para centralizar. |
