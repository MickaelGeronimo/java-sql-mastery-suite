# ☕ Curso de Java — Capítulo 36: `⭐ quiz game 💯` (Parte 5)
## O Casamento dos Laços: O Laço Aninhado das Opções (`options[i]`), a Junção do Laço Indexado com o `for-each` e as Alternativas no Console

> **Baseado na aula do canal Bro Code (Minuto `5:55:14` a `5:55:30` de 12:00:00)**  
> **Tema:** O professor implementa a etapa `// OPTIONS`. Ele aninha um laço `for-each` dentro do laço principal das perguntas: `for(String option : options[i])`. Ao passar `options[i]` para o laço interno, ele extrai dinamicamente a linha exata da matriz correspondente à pergunta da rodada. Ao executar o programa, o console finalmente ganha vida, exibindo cada pergunta seguida imediatamente de suas 4 opções de múltipla escolha!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das Duas Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-duas-telas-para-o-google-docs)
2. [O que o Professor Adicionou e Explicou Neste Momento](#2-o-que-o-professor-adicionou-e-explicou-neste-momento)
3. [A Sacada de Mestre: Por que `options[i]` Dentro do Laço Interno?](#3-a-sacada-de-mestre-por-que-optionsi-dentro-do-laço-interno)
4. [A Combinação dos Dois Mundos: Laço Indexado por Fora, `for-each` por Dentro](#4-a-combinação-dos-dois-mundos-laço-indexado-por-fora-for-each-por-dentro)
5. [O Código Java Completo, Numerado e Definitivo](#5-o-código-java-completo-numerado-e-definitivo)
6. [Explicação Cirúrgica Linha por Linha](#6-explicação-cirúrgica-linha-por-linha)
7. [Teste de Mesa Passo a Passo: A Dança dos Laços Aninhados](#7-teste-de-mesa-passo-a-passo-a-dança-dos-laços-aninhados)
8. [O que Saiu no Console (A Visualização das Perguntas com Alternativas)](#8-o-que-saiu-no-console-a-visualização-das-perguntas-com-alternativas)
9. [Resumo Prático em Uma Tabela Mental](#9-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das Duas Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA em ambas as imagens enviadas:

### 📸 Imagem 1 (`media_1791243591731.png` — Minuto `5:55:14`): O Código do Laço Aninhado
* **Barra de status do vídeo:** `5:55:14 / 12:00:00` | Capítulo: `36. quiz game >`.
* **No editor de código (`Main.java`):**
  * Dentro do laço principal `for(int i = 0; i < questions.length; i++)`, logo abaixo de `System.out.println(questions[i]);`, o professor substituiu o comentário `// OPTIONS` pelas seguintes linhas:
    * Linha 34: `for(String option : options[i]){`
    * Linha 35: `    System.out.println(option);`
    * Linha 36: `}`
  * A palavra `option` nas linhas 34 e 35 está com um leve destaque roxo/cinza (foco do cursor da IDE).
  * Abaixo da chave de fechamento da linha 37, continuam os comentários do esqueleto:
    * `// GET GUESS FROM USER`
    * `// CHECK OUR GUESS`
    * `// DISPLAY FINAL SCORE`
  * Linha 43: `scanner.close();`.

---

### 📸 Imagem 2 (`media_1791243601102.png` — Minuto `5:55:30`): O Console Preenchido com o Questionário
* **Barra de status do vídeo:** ~`5:55:30 / 12:00:00`.
* **Na janela de Console (Terminal Inferior):**
  * O professor executou o programa (Play ▶️ Run).
  * O console exibiu tantas informações que a barra de rolagem desceu automaticamente.
  * O que está visível na janela:
    * No topo visível: `4. Managing passwords` (a 4ª opção da Pergunta 1, que rolou para cima).
    * Em seguida:
      ```text
      Which part of the computer is considered the brain?
      1. CPU
      2. Hard Drive
      3. RAM
      4. GPU
      What year was Facebook launched?
      1. 2000
      2. 2004
      3. 2006
      4. 2008
      Who is known as the father of computers?
      ```
  * O cursor de texto do mouse em forma de "I" (*I-beam*) está posicionado logo ao lado da interrogação de `What year was Facebook launched?`.
  * Na linha 35 do editor acima, há um ícone de lâmpada amarela do IntelliJ.

---

## 2. O que o Professor Adicionou e Explicou Neste Momento

Na Parte 4, o professor tinha feito o laço imprimir apenas as 5 perguntas soltas.  
Agora ele faz o **cruzamento das duas estruturas de dados**:

1. Ele pega a pergunta atual através do índice `i` (`questions[i]`).
2. Logo em seguida, ele pega a **linha correspondente da matriz** através de **`options[i]`**.
3. Usa um laço `for-each` para descarregar as 4 alternativas daquela linha na tela, uma embaixo da outra!
4. O resultado visual é espetacular: cada pergunta agora aparece na tela imediatamente acompanhada de suas 4 opções numeradas (`1.`, `2.`, `3.`, `4.`)!

---

## 3. A Sacada de Mestre: Por que `options[i]` Dentro do Laço Interno?

Esta é a parte onde o aluno que compreendeu o Capítulo 35 de matrizes abre um sorriso de orelha a orelha!

Lembre-se da definição de matriz 2D em Java:
> **"Uma matriz 2D é um array onde cada elemento é outro array!"**

Quando escrevemos:
```java
String[][] options = {
    {"1. Storing files", "2. Encrypting data", "3. Directing internet traffic", "4. Managing passwords"}, // Linha 0
    {"1. CPU", "2. Hard Drive", "3. RAM", "4. GPU"},                                                     // Linha 1
    {"1. 2000", "2. 2004", "3. 2006", "4. 2008"},                                                       // Linha 2
    ...
};
```

Se você pedir para o Java acessar **`options[0]`** (com apenas **UM colchete**):
* O Java **NÃO** te devolve uma palavra isolada!
* O Java te devolve o **array inteiro da Linha 0** contendo as 4 opções daquela linha!

Então, quando o professor escreve na linha 34:
```java
for (String option : options[i])
```
Veja o que acontece dependendo da volta do laço:
* Na volta `i = 0`: O Java entrega o array `options[0]` $\rightarrow$ As opções do Roteador!
* Na volta `i = 1`: O Java entrega o array `options[1]` $\rightarrow$ As opções da CPU!
* Na volta `i = 2`: O Java entrega o array `options[2]` $\rightarrow$ As opções do Facebook!
* Na volta `i = 3`: O Java entrega o array `options[3]` $\rightarrow$ As opções do Pai da Computação!
* Na volta `i = 4`: O Java entrega o array `options[4]` $\rightarrow$ As opções da Primeira Linguagem!

---

## 4. A Combinação dos Dois Mundos: Laço Indexado por Fora, `for-each` por Dentro

Repare na inteligência da arquitetura que o professor montou:

```java
// LAÇO EXTERNO: Indexado com contador 'i'
for (int i = 0; i < questions.length; i++) {
    System.out.println(questions[i]);

    // LAÇO INTERNO: Elegante for-each
    for (String option : options[i]) {
        System.out.println(option);
    }
}
```

* **Por que o de fora é indexado (`for int i`)?**  
  Porque precisamos do número `i` para saber em qual pergunta estamos e para puxar a linha certa da matriz (`options[i]`).
* **Por que o de dentro é `for-each` (`for String option`)?**  
  Porque uma vez que já estamos dentro da linha `i`, nós não precisamos de contadores complexos: só queremos pegar cada uma das 4 opções e cuspir na tela! O `for-each` faz isso com uma clareza visual impecável.

---

## 5. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado na Imagem 1:

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
37:         }
38: 
39:         //      GET GUESS FROM USER
40:         //      CHECK OUR GUESS
41:         // DISPLAY FINAL SCORE
42: 
43:         scanner.close();
44:     }
45: }
```

---

## 6. Explicação Cirúrgica Linha por Linha

---

### Linha 31: O Laço Externo das Perguntas
```java
31:         for(int i = 0; i < questions.length; i++){
```
* Controla a pergunta atual. `i` varia de `0` até `4`.

---

### Linha 32: Imprime o Enunciado da Pergunta
```java
32:             System.out.println(questions[i]);
```
* Imprime a pergunta no console e pula para a linha de baixo.

---

### Linha 34: O Laço Interno das 4 Opções
```java
34:             for(String option : options[i]){
```
* **O que faz:** Inicia a varredura das 4 alternativas pertencentes à linha `i` da matriz `options`.
* **Como se lê:** *"Para cada texto `option` que estiver guardado dentro da linha `options[i]`..."*
* **A variável temporária `option`:** A cada repetição deste laço interno, ela assume o valor de uma das alternativas (ex.: na primeira rodada é `"1. CPU"`, na segunda é `"2. Hard Drive"`, etc.).
* **Saída no console:** Nenhuma diretamente nesta linha.

---

### Linha 35: A Impressão de Cada Alternativa
```java
35:                 System.out.println(option);
```
* **O que faz:** Imprime o texto da alternativa atual no console e pula de linha (`println`).
* Como são 4 opções em cada linha da matriz, este comando é executado **4 vezes seguidas** para cada pergunta!
* **Saída no console:**
  ```text
  1. CPU
  2. Hard Drive
  3. RAM
  4. GPU
  ```

---

### Linha 36: Fim do Laço Interno
```java
36:             }
```
* Quando as 4 alternativas da pergunta atual foram impressas, o laço interno encerra sua missão para esta rodada.

---

### Linha 37: Fim do Laço Externo
```java
37:         }
```
* O laço externo faz `i++` e avança para a próxima pergunta da prova (se ainda houver).

---

## 7. Teste de Mesa Passo a Passo: A Dança dos Laços Aninhados

Veja o que acontece na tela durante cada segundo da execução:

| Passo | Valor de `i` | O que Executa? | O que Aparece no Console? |
| :---: | :---: | :--- | :--- |
| **1** | `0` | `println(questions[0])` | `What is the main function of a router?` |
| **2** | `0` | Laço interno roda 4x em `options[0]` | `1. Storing files`<br>`2. Encrypting data`<br>`3. Directing internet traffic`<br>`4. Managing passwords` |
| **3** | `1` | `println(questions[1])` | `Which part of the computer is considered the brain?` |
| **4** | `1` | Laço interno roda 4x em `options[1]` | `1. CPU`<br>`2. Hard Drive`<br>`3. RAM`<br>`4. GPU` |
| **5** | `2` | `println(questions[2])` | `What year was Facebook launched?` |
| **6** | `2` | Laço interno roda 4x em `options[2]` | `1. 2000`<br>`2. 2004`<br>`3. 2006`<br>`4. 2008` |
| **7** | `3` | `println(questions[3])` | `Who is known as the father of computers?` |
| **8** | `3` | Laço interno roda 4x em `options[3]` | `1. Steve Jobs`<br>`2. Bill Gates`<br>`3. Alan Turing`<br>`4. Charles Babbage` |
| **9** | `4` | `println(questions[4])` | `What was the first programming language?` |
| **10** | `4` | Laço interno roda 4x em `options[4]` | `1. COBOL`<br>`2. C`<br>`3. Fortran`<br>`4. Assembly` |
| **11** | `5` | `5 < 5` dá Falso! | Fim dos laços! |

---

## 8. O que Saiu no Console (A Visualização das Perguntas com Alternativas)

A saída completa produzida pelo programa nesta etapa é:

```text
*******************************
Welcome to the Java Quiz Game!
*******************************
What is the main function of a router?
1. Storing files
2. Encrypting data
3. Directing internet traffic
4. Managing passwords
Which part of the computer is considered the brain?
1. CPU
2. Hard Drive
3. RAM
4. GPU
What year was Facebook launched?
1. 2000
2. 2004
3. 2006
4. 2008
Who is known as the father of computers?
1. Steve Jobs
2. Bill Gates
3. Alan Turing
4. Charles Babbage
What was the first programming language?
1. COBOL
2. C
3. Fortran
4. Assembly

Process finished with exit code 0
```

---

## 9. Resumo Prático em Uma Tabela Mental

| Componente | Papel no Programa | Como se Comporta? |
| :--- | :--- | :--- |
| **`for(int i = 0; ...)`** | Laço Pai (Externo) | Roda 5 vezes (uma vez para cada pergunta). |
| **`questions[i]`** | Enunciado da questão | Imprime o texto da pergunta da rodada. |
| **`options[i]`** | Fatia da matriz 2D | Entrega a linha com as 4 opções correspondentes. |
| **`for(String option : options[i])`** | Laço Filho (Interno) | Roda 4 vezes para cada pergunta (total de $5 \times 4 = 20$ vezes). |
| **`System.out.println(option);`** | Exibição da alternativa | Imprime a opção com seu número e quebra a linha. |
