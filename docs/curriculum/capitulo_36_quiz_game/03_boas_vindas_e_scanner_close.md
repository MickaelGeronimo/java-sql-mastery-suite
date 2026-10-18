# ☕ Curso de Java — Capítulo 36: `⭐ quiz game 💯` (Parte 3)
## A Apresentação do Jogo: O Banner de Boas-Vindas com Moldura de Asteriscos (`*`), a Primeira Saída no Console e a Proteção com `scanner.close()`

> **Baseado na aula do canal Bro Code (Minuto `5:52:58` a `5:53:05` de 12:00:00)**  
> **Tema:** O professor avança para a etapa seguinte do esqueleto do jogo: substitui o comentário `// WELCOME MESSAGE` por um banner de boas-vindas estilizado no console. Ele cria uma moldura simétrica com linhas de asteriscos (`*`), demonstra a técnica de duplicar linhas de código, adiciona o fechamento preventivo do `Scanner` (`scanner.close()`) no final do programa e executa o código pela primeira vez, gerando a primeira saída visual do jogo no console!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das Duas Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-duas-telas-para-o-google-docs)
2. [O que o Professor Adicionou e Explicou Neste Momento](#2-o-que-o-professor-adicionou-e-explicou-neste-momento)
3. [Por que a Linha 27 Aparece Selecionada em Azul na Primeira Imagem?](#3-por-que-a-linha-27-aparece-selecionada-em-azul-na-primeira-imagem)
4. [A Importância do Design de Console: O Banner de Asteriscos](#4-a-importância-do-design-de-console-o-banner-de-asteriscos)
5. [Por que Adicionar `scanner.close()` Antes Mesmo de Fazer o Laço?](#5-por-que-adicionar-scannerclose-antes-mesmo-de-fazer-o-laço)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Cirúrgica Linha por Linha](#7-explicação-cirúrgica-linha-por-linha)
8. [O que Saiu no Console (A Primeira Execução Real do Quiz!)](#8-o-que-saiu-no-console-a-primeira-execução-real-do-quiz)
9. [Dica de Produtividade no IntelliJ: O Superpoder do `Ctrl + D`](#9-dica-de-produtividade-no-intellij-o-superpoder-do-ctrl--d)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das Duas Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA em ambas as imagens enviadas:

### 📸 Imagem 1 (`media_1791243551183.png` — Minuto `5:52:58`): Selecionando a Linha dos Asteriscos
* **Barra de status do vídeo:** `5:52:58 / 12:00:00` | Capítulo: `36. quiz game >`.
* **No editor de código (`Main.java`):**
  * Abaixo de `Scanner scanner = new Scanner(System.in);`, o comentário `// WELCOME MESSAGE` foi removido.
  * Linha 27: `System.out.println("*******************************");`
    * **O Destaque Azul:** A linha 27 inteira está selecionada com fundo azul vibrante! O professor selecionou a linha para copiá-la (`Ctrl + C`) com o intuito de colá-la embaixo do título.
  * Linha 28: `System.out.println("Welcome to the Java Quiz Game!");`
  * Linhas 30 a 34: Continuam os comentários do esqueleto (`// QUESTION (loop)`, `// OPTIONS`, etc.).
  * Linha 36: Foi adicionado `scanner.close();` logo antes do encerramento do método `main`.
  * Barra de status inferior da IDE: Mostra `27:9 (53 chars)`, indicando que a linha 27 possui exatamente 53 caracteres ao todo.

---

### 📸 Imagem 2 (`media_1791243558649.png` — Minuto `5:53:04`): O Código Executado e o Banner no Console
* **Barra de status do vídeo:** `5:53:04 / 12:00:00` (6 segundos depois).
* **No editor de código (`Main.java`):**
  * O professor colou a linha de asteriscos na **Linha 29**. Agora há uma moldura completa:
    * Linha 27: Linha superior de asteriscos.
    * Linha 28: Título *"Welcome to the Java Quiz Game!"*.
    * Linha 29: Linha inferior de asteriscos.
* **Na janela de Console (Terminal Inferior):**
  * O professor clicou no botão verde de Play (▶️ Run).
  * O console abre e exibe, em texto verde clássico sobre fundo preto, a mensagem centralizada e emoldurada:
    ```text
    *******************************
    Welcome to the Java Quiz Game!
    *******************************

    Process finished with exit code 0
    ```
  * O cursor do mouse em formato de "I" (*I-beam*) está posicionado exatamente sobre as palavras `Java Quiz` no console.

---

## 2. O que o Professor Adicionou e Explicou Neste Momento

Nesta curta passagem de 6 segundos, o professor realizou três ações fundamentais:

1. **Substituiu o comentário de planejamento `// WELCOME MESSAGE` pelo código real:**  
   Em vez de apenas planejar, ele agora implementa as três instruções `System.out.println()` que dão as boas-vindas ao jogador.
2. **Criou uma moldura estilizada com asteriscos:**  
   Ele usa uma linha de 31 asteriscos acima e abaixo do texto para dar um acabamento profissional de jogo clássico de terminal (*arcade / retrô*).
3. **Adicionou a limpeza de memória com `scanner.close()`:**  
   Lá no final do programa (na linha 37), ele já colocou o comando que fecha o leitor de teclado, garantindo que o programa não vaze recursos do sistema operacional.
4. **Executou o programa (Run):**  
   Pela primeira vez desde o início do Capítulo 36, o console deixa de ficar em branco e imprime a linda mensagem inicial do quiz!

---

## 3. Por que a Linha 27 Aparece Selecionada em Azul na Primeira Imagem?

Ao olhar para a Imagem 1, você repara que a linha:
```java
System.out.println("*******************************");
```
está com uma faixa azul cobrindo todo o seu comprimento.

### O que o Professor Está Fazendo?
* O professor acabou de digitar a linha superior de asteriscos na linha 27.
* Ele precisa de **outra linha idêntica** logo abaixo da mensagem de boas-vindas para fechar a moldura.
* Em vez de digitar `System.out.println("*******************************");` tecla por tecla novamente (o que seria demorado e sujeito a colocar uma quantidade diferente de asteriscos), ele faz o movimento clássico de todo programador ágil:
  1. Seleciona a linha 27 (ficando azul);
  2. Aperta **`Ctrl + C`** (Copiar);
  3. Desce o cursor para depois da linha 28;
  4. Aperta **`Ctrl + V`** (Colar).
* Na Imagem 2, você vê o resultado perfeito: a linha 29 nasceu como um clone exato da linha 27!

---

## 4. A Importância do Design de Console: O Banner de Asteriscos

Por que gastar tempo colocando linhas de asteriscos `*******************************` em um programa?

1. **Experiência do Usuário (UX):** Em programas de terminal (que rodam em tela preta sem botões ou janelas coloridas), o usuário se sente desorientado se o texto aparecer cru. Uma moldura visual chama a atenção e avisa: *"O jogo começou!"*.
2. **Delimitação Visual:** Os asteriscos separam claramente o início do jogo das perguntas que virão logo a seguir.
3. **Simetria:** A frase `Welcome to the Java Quiz Game!` tem 30 caracteres. A linha de 31 asteriscos abraça o texto com perfeição geométrica!

---

## 5. Por que Adicionar `scanner.close()` Antes Mesmo de Fazer o Laço?

Observe que na linha 37 do código, o professor já escreveu:
```java
scanner.close();
```
Mesmo que ele ainda **não tenha programado o laço das perguntas nem a leitura do teclado**!

### Por que os Programadores Experientes Fazem Isso?
* Em Java, quando você abre um canal com o sistema operacional usando `new Scanner(System.in)`, você está "segurando uma porta aberta".
* Se você esquecer de fechar essa porta com `.close()`, o Java emite um aviso (*Resource leak: 'scanner' is never closed*), o que consome memória desnecessária.
* Como o professor é um desenvolvedor experiente, ele sabe que **se deixar para colocar o `.close()` no final do projeto, ele pode esquecer**!
* Portanto, ele já "grampeia" o fechamento lá embaixo. Assim, tudo o que ele programar no meio do caminho já estará protegido!

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado e executado na Imagem 2 do vídeo:

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
31:         // QUESTION (loop)
32:         //      OPTIONS
33:         //      GET GUESS FROM USER
34:         //      CHECK OUR GUESS
35:         // DISPLAY FINAL SCORE
36: 
37:         scanner.close();
38:     }
39: }
```

---

## 7. Explicação Cirúrgica Linha por Linha

---

### Linhas 1 a 25: A Estrutura e os Dados (Revisão Rápida)
* **Linha 1:** Importa o `Scanner` para leitura de dados.
* **Linhas 3 a 5:** Cria a classe `Main` e o método `main`.
* **Linhas 9 a 13:** Cria o array `questions` na memória Heap com as 5 perguntas.
* **Linhas 15 a 19:** Cria a matriz bidimensional `options` com as 20 alternativas.
* **Linha 21:** Cria o gabarito oficial `answers = {3, 1, 2, 4, 3}`.
* **Linhas 22 e 23:** Inicializa `score = 0` e declara a variável inteira `guess`.
* **Linha 25:** Instancia o leitor `scanner`.
* **Saída no console:** Nenhuma até aqui.

---

### Linha 27: A Borda Superior do Banner
```java
27:         System.out.println("*******************************");
```
* **O que faz:** Imprime uma sequência horizontal de 31 caracteres de asterisco (`*`) e pula para a linha de baixo (`println`).
* **Saída no console:**
  ```text
  *******************************
  ```

---

### Linha 28: O Título do Jogo
```java
28:         System.out.println("Welcome to the Java Quiz Game!");
```
* **O que faz:** Imprime a frase de boas-vindas: *"Bem-vindo ao Jogo de Quiz em Java!"* e pula imediatamente para a linha seguinte.
* **Saída no console:**
  ```text
  Welcome to the Java Quiz Game!
  ```

---

### Linha 29: A Borda Inferior do Banner
```java
29:         System.out.println("*******************************");
```
* **O que faz:** Imprime a segunda linha idêntica de 31 asteriscos, fechando a moldura decorativa por baixo, e pula de linha.
* **Saída no console:**
  ```text
  *******************************
  ```

---

### Linhas 30 a 35: Comentários de Planejamento Futuro
```java
30: 
31:         // QUESTION (loop)
32:         //      OPTIONS
33:         //      GET GUESS FROM USER
34:         //      CHECK OUR GUESS
35:         // DISPLAY FINAL SCORE
36: 
```
* **O que faz:** Comentários com `//` mantidos como lembrete visual para o próximo bloco de código que implementará o laço de repetição. O compilador ignora estas linhas.
* **Saída no console:** Nenhuma.

---

### Linha 37: O Fechamento Preventivo do Scanner
```java
37:         scanner.close();
```
* **O que faz:** Chama o método `close()` no objeto `scanner`. Isso avisa ao sistema operacional: *"Terminei de usar o teclado, pode liberar este recurso!"*.
* **Por que é boa prática?** Evita vazamento de recursos (*Resource Leak*).
* **Saída no console:** Nenhuma.

---

### Linhas 38 e 39: Término do Programa
```java
38:     }
39: }
```
* Fecha o método `main` e a classe `Main`. A JVM finaliza a execução com o código de sucesso 0.

---

## 8. O que Saiu no Console (A Primeira Execução Real do Quiz!)

Quando o professor clica no botão verde de Play (▶️ Run), a JVM executa as linhas 27, 28 e 29 uma após a outra.

A saída exata gerada no console da IDE é:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA..."
*******************************
Welcome to the Java Quiz Game!
*******************************

Process finished with exit code 0
```

### O que Isso Significa?
* O programa compila perfeitamente sem nenhum erro!
* Todas as variáveis e matrizes foram carregadas na memória perfeitamente.
* A interface inicial do jogo está pronta e apresentável.

---

## 9. Dica de Produtividade no IntelliJ: O Superpoder do `Ctrl + D`

No vídeo, o professor selecionou a linha 27 com o mouse (ficando azul) e deu `Ctrl + C` / `Ctrl + V`.

Mas existe um **atalho secreto de programador profissional** no IntelliJ IDEA que faz isso em menos de 1 segundo sem precisar selecionar nada:

> [!TIP]
> **Atalho Ninja do IntelliJ IDEA:**  
> Coloque o cursor piscando em qualquer lugar da linha 27 e aperte **`Ctrl + D`** (no Windows) ou **`Cmd + D`** (no Mac).  
> A letra **D** vem de **Duplicate** (*Duplicar*). A IDE instantaneamente cria uma cópia idêntica daquela linha logo abaixo, sem você precisar arrastar o mouse nem usar Ctrl+C!

---

## 10. Resumo Prático em Uma Tabela Mental

| Linha no Código | Instrução Java | Efeito no Programa | O que Aparece no Console? |
| :---: | :--- | :--- | :--- |
| **27** | `System.out.println("******...");` | Teto da moldura | `*******************************` |
| **28** | `System.out.println("Welcome...");` | Miolo com o título | `Welcome to the Java Quiz Game!` |
| **29** | `System.out.println("******...");` | Chão da moldura | `*******************************` |
| **37** | `scanner.close();` | Limpeza de recursos | Nada (trabalho interno do sistema operacional) |
| **Seleção Azul** | Mouse sobre a Linha 27 | Copiar a linha para colar na linha 29 | Mostra a duplicação rápida da moldura |
