# ☕ Curso de Java — Capítulo 36: `⭐ quiz game 💯` (Parte 2)
## Alimentando o Quiz: O Array de Perguntas (`String[]`), a Matriz de Opções (`String[][]`), o Gabarito (`int[] answers`) e o Mistério do `Scanner` Vermelho!

> **Baseado na aula do canal Bro Code (Minuto `5:48:44` a `5:52:30` de 12:00:00)**  
> **Tema:** O professor sai do planejamento abstrato e começa a preencher os dados reais do jogo de perguntas e respostas. Ele cria o array 1D com 5 perguntas sobre computação, a matriz 2D com as 4 alternativas de cada pergunta, o vetor de gabarito com as respostas certas (`int[] answers`), inicializa as variáveis de controle (`score`, `guess`) e resolve o clássico erro vermelho do IntelliJ ao instanciar o `Scanner`.

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 4 Telas (Para o Google Docs)](#1-contexto-visual-minucioso-das-4-telas-para-o-google-docs)
2. [O que o Professor Fez e Explicou Passo a Passo](#2-o-que-o-professor-fez-e-explicou-passo-a-passo)
3. [A Conexão Perfeita: Como Perguntas, Opções e Gabarito se Encaixam](#3-a-conexão-perfeita-como-perguntas-opções-e-gabarito-se-encaixam)
4. [Por que Usar Números (1, 2, 3, 4) em Vez de Letras (A, B, C, D)?](#4-por-que-usar-números-1-2-3-4-em-vez-de-letras-a-b-c-d)
5. [O Mistério do Scanner Vermelho: `Cannot resolve symbol 'Scanner'`](#5-o-mistério-do-scanner-vermelho-cannot-resolve-symbol-scanner)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Cirúrgica Linha por Linha](#7-explicação-cirúrgica-linha-por-linha)
8. [Tabela Mestre do Quiz: Perguntas, Alternativas e Respostas Explicadas](#8-tabela-mestre-do-quiz-perguntas-alternativas-e-respostas-explicadas)
9. [O que Sai no Console Neste Momento?](#9-o-que-sai-no-console-neste-momento)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso das 4 Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de cada uma das 4 telas da IDE IntelliJ IDEA apresentadas no vídeo:

### 📸 Tela 1 (`media_1791243480630.png` — Minuto `5:48:44`): Preenchendo Perguntas e Rascunhando a Matriz
* **Barra de tempo do vídeo:** `5:48:44 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * Abaixo de `// JAVA QUIZ GAME`, o professor substituiu o comentário `// QUESTIONS array[]` pelo array real `String[] questions` contendo 5 perguntas de tecnologia entre aspas duplas, separadas por vírgula.
  * Na linha 14, ele declara `String[][] options = {{"1. Storing files", "2. Encrypting data", ""}, {}, {}, {}, {}};`.
  * Ele coloca chaves vazias `{}` para as outras 4 perguntas como rascunho visual, e o cursor está dentro das aspas da terceira alternativa da primeira pergunta.

---

### 📸 Tela 2 (`media_1791243494822.png` — Minuto `5:50:45`): A Matriz de Opções Totalmente Preenchida
* **Barra de tempo do vídeo:** `5:50:45 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * Todas as 5 linhas da matriz `options` estão completamente preenchidas com 4 alternativas cada uma (uma grade perfeita de $5 \times 4$).
  * Na linha 18, o texto **`"Fortran"`** está com uma **seleção azul de destaque**, mostrando que o professor acabou de selecioná-lo com o mouse enquanto comentava sobre a história das linguagens de programação.
  * Na linha 11, o texto da pergunta 4 foi refinado de *"father of computer"* para *"father of computers"* (no plural).

---

### 📸 Tela 3 (`media_1791243511037.png` — Minuto `5:52:16`): O Erro Vermelho do `Scanner`
* **Barra de tempo do vídeo:** `5:52:16 / 12:00:00`.
* **No editor de código (`Main.java`):**
  * Abaixo de `options`, o professor remove o comentário `// DECLARE VARIABLES` e digita:
    * Linha 20: `int[] answers = {3, 1, 2, 4, 3};` (o gabarito da prova!).
    * Linha 21: `int score = 0;` (o placar).
    * Linha 22: `int guess;` (a resposta que o jogador vai digitar).
    * Linha 24: `Scanner scanner = new Scanner(System.in);`.
  * **O Alerta Vermelho de Erro:** A palavra **`Scanner`** está pintada em **vermelho vivo**, com uma caixinha de aviso suspensa do IntelliJ dizendo:  
    `Cannot resolve symbol 'Scanner'`  
    *(Tradução: "Não consigo reconhecer o símbolo Scanner")*.
  * O IntelliJ exibe um botão clicável azul no aviso: `import class (Alt+Shift+Enter)`.

---

### 📸 Tela 4 (`media_1791243530210.png`): O Erro Resolvido com o `import` Automático
* **No editor de código (`Main.java`):**
  * O professor clica em `import class` (ou aperta o atalho).
  * Automaticamente, a IDE adiciona na **Linha 1** (no topo absoluto do arquivo):
    ```java
    import java.util.Scanner;
    ```
  * O nome `Scanner` na linha 1 fica temporariamente selecionado em cinza.
  * O texto em vermelho da linha 24 desaparece instantaneamente e volta para a cor normal, indicando que o Java agora conhece a ferramenta!

---

## 2. O que o Professor Fez e Explicou Passo a Passo

O professor estruturou a fundação de dados do jogo em 5 movimentos mestres:

1. **Criou a lista de perguntas (`String[] questions`):** Um array unidimensional clássico com 5 frases de curiosidades sobre ciência da computação.
2. **Criou a matriz de alternativas (`String[][] options`):** Uma matriz bidimensional de 5 linhas por 4 colunas. Cada linha da matriz guarda as 4 opções correspondentes à pergunta daquela mesma linha.
3. **Criou o gabarito oficial (`int[] answers`):** Um array de inteiros contendo a resposta correta de cada questão: `{3, 1, 2, 4, 3}`.
4. **Criou as variáveis de pontuação e resposta:**
   * `int score = 0;`: Guarda quantos acertos o jogador teve (começa em zero).
   * `int guess;`: Uma variável vazia que receberá o número digitado pelo jogador em cada rodada.
5. **Preparou o `Scanner` para ler o teclado:** E explicou o motivo do erro vermelho de compilação, ensinando como importar a biblioteca `java.util.Scanner` com apenas um clique.

---

## 3. A Conexão Perfeita: Como Perguntas, Opções e Gabarito se Encaixam

Preste muita atenção nesta relação. É aqui que você entende por que passamos capítulos inteiros estudando índices `[0]`, `[1]`, `[2]`:

* **Índice 0:**
  * Pergunta `questions[0]`: *"What is the main function of a router?"*
  * Opções `options[0]`: `{"1. Storing files", "2. Encrypting data", "3. Directing internet traffic", "4. Managing passwords"}`
  * Gabarito `answers[0]`: `3` *(Directing internet traffic)*!
* **Índice 1:**
  * Pergunta `questions[1]`: *"Which part of the computer is considered the brain?"*
  * Opções `options[1]`: `{"1. CPU", "2. Hard Drive", "3. RAM", "4. GPU"}`
  * Gabarito `answers[1]`: `1` *(CPU)*!
* **Índice 2:**
  * Pergunta `questions[2]`: *"What year was Facebook launched?"*
  * Opções `options[2]`: `{"1. 2000", "2. 2004", "3. 2006", "4. 2008"}`
  * Gabarito `answers[2]`: `2` *(2004)*!
* **Índice 3:**
  * Pergunta `questions[3]`: *"Who is known as the father of computers?"*
  * Opções `options[3]`: `{"1. Steve Jobs", "2. Bill Gates", "3. Alan Turing", "4. Charles Babbage"}`
  * Gabarito `answers[3]`: `4` *(Charles Babbage)*!
* **Índice 4:**
  * Pergunta `questions[4]`: *"What was the first programming language?"*
  * Opções `options[4]`: `{"1. COBOL", "2. C", "3. Fortran", "4. Assembly"}`
  * Gabarito `answers[4]`: `3` *(Fortran)*!

> [!IMPORTANT]
> **A Mágica da Sincronia de Índices:**  
> Todos os três arrays compartilham o **mesmo índice `i`**! Quando nosso laço estiver na rodada `i = 2`, ele pegará automaticamente a pergunta `2`, as alternativas da linha `2` e testará contra o gabarito `answers[2]`. É pura elegância matemática!

---

## 4. Por que Usar Números (1, 2, 3, 4) em Vez de Letras (A, B, C, D)?

Muitos quizzes usam letras `A`, `B`, `C`, `D`. Por que o professor preferiu usar números `1`, `2`, `3`, `4`?

1. **Facilidade com o Scanner:** Ler um número inteiro com `scanner.nextInt()` é muito mais simples para um iniciante do que ler uma String, converter para maiúsculas e extrair o primeiro caractere com `scanner.next().toUpperCase().charAt(0)`.
2. **Comparação Direta com `==`:** Como a resposta é um número inteiro (`int guess`), podemos comparar diretamente usando `guess == answers[i]`. Se fossem textos (`String`), precisaríamos usar o método `.equals()`.
3. **Código Mais Limpo e Sem Riscos de Bugs:** Menos linhas de código para o estudante iniciante se preocupar!

---

## 5. O Mistério do Scanner Vermelho: `Cannot resolve symbol 'Scanner'`

Na Imagem 3, quando o professor digita:
```java
Scanner scanner = new Scanner(System.in);
```
A palavra `Scanner` fica imediatamente sublinhada em **vermelho** e o IntelliJ reclama: `Cannot resolve symbol 'Scanner'`.

### Por que isso acontece?
* Por padrão, para economizar memória e inicializar rápido, o Java **não carrega todas as ferramentas do universo** na sua aplicação.
* O Java só traz por padrão o pacote básico `java.lang` (onde moram o `System`, o `String`, a `Math`).
* A ferramenta `Scanner` mora em outro armário: um pacote chamado **`java.util`** (*utilitários do Java*).
* Como o Java ainda não sabe onde o `Scanner` mora, ele avisa: *"Ei, eu não conheço ninguém com esse nome!"*.

### A Solução Mágica (O `import`):
Quando você aceita a sugestão do IntelliJ (ou digita manualmente na primeira linha do arquivo):
```java
import java.util.Scanner;
```
Você está dizendo: *"Java, vá até o pacote `java.util` e traga a ferramenta `Scanner` para mim"*.  
Instantaneamente, o erro vermelho desaparece e o código compila com perfeição!

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, exatamente como configurado na Tela 4 do vídeo:

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
27:         // WELCOME MESSAGE
28:         // QUESTION (loop)
29:         //      OPTIONS
30:         //      GET GUESS FROM USER
31:         //      CHECK OUR GUESS
32:         // DISPLAY FINAL SCORE
33:     }
34: }
```

---

## 7. Explicação Cirúrgica Linha por Linha

---

### Linha 1: A Importação da Classe Scanner
```java
1:  import java.util.Scanner;
```
* **O que faz:** Importa a classe `Scanner` do pacote de utilitários do Java (`java.util`).
* **Analogia:** É como pegar a chave da caixa de ferramentas para poder tirar o leitor de teclado de lá.
* **Saída no console:** Nenhuma.

---

### Linhas 3 a 5: Abertura da Classe e Método `main`
```java
3:  public class Main {
4:  
5:      public static void main(String[] args) {
```
* **Linha 3:** Define a classe `Main`.
* **Linha 5:** Inicia o método principal `main`. É onde o programa ganha vida quando clicamos em Run.
* **O que acontece na memória:** A JVM prepara o quadro de pilha (*Stack Frame*) do método `main`.
* **Saída no console:** Nenhuma.

---

### Linhas 7 a 13: O Array Unidimensional de Perguntas (`String[]`)
```java
7:          // JAVA QUIZ GAME
8:  
9:          String[] questions = {"What is the main function of a router?",
10:                               "Which part of the computer is considered the brain?",
11:                               "What year was Facebook launched?",
12:                               "Who is known as the father of computers?",
13:                               "What was the first programming language?"};
```
* **O que faz:** Cria um array de textos chamado `questions` com tamanho fixo de 5 posições (índices 0 a 4).
* **O que acontece na memória:**
  * **Na Stack:** É criada a variável `questions` que guarda o endereço de memória.
  * **Na Heap:** É alocado um array contendo 5 referências apontando para as 5 perguntas no *String Pool*.
* **Saída no console:** Nenhuma.

---

### Linhas 15 a 19: A Matriz Bidimensional de Opções (`String[][]`)
```java
15:         String[][] options = {{"1. Storing files", "2. Encrypting data", "3. Directing internet traffic", "4. Managing passwords"},
16:                               {"1. CPU", "2. Hard Drive", "3. RAM", "4. GPU"},
17:                               {"1. 2000", "2. 2004", "3. 2006", "4. 2008"},
18:                               {"1. Steve Jobs", "2. Bill Gates", "3. Alan Turing", "4. Charles Babbage"},
19:                               {"1. COBOL", "2. C", "3. Fortran", "4. Assembly"}};
```
* **O que faz:** Cria a matriz $5 \times 4$ de alternativas de múltipla escolha.
  * Linha 15 (Índice 0): 4 alternativas da Pergunta 0.
  * Linha 16 (Índice 1): 4 alternativas da Pergunta 1.
  * Linha 17 (Índice 2): 4 alternativas da Pergunta 2.
  * Linha 18 (Índice 3): 4 alternativas da Pergunta 3.
  * Linha 19 (Índice 4): 4 alternativas da Pergunta 4.
* **O que acontece na memória:**
  * É criado um array principal de 5 posições. Cada uma dessas posições aponta para um array secundário de 4 posições na Heap.
* **Saída no console:** Nenhuma.

---

### Linha 21: O Array de Gabarito (`int[] answers`)
```java
21:         int[] answers = {3, 1, 2, 4, 3};
```
* **O que faz:** Cria uma lista com os números das respostas certas.
  * Pergunta 0: resposta `3`
  * Pergunta 1: resposta `1`
  * Pergunta 2: resposta `2`
  * Pergunta 3: resposta `4`
  * Pergunta 4: resposta `3`
* **Por que `int[]`?** Porque os números das alternativas são inteiros. Ocupa pouquíssima memória (apenas 32 bits por resposta).
* **Saída no console:** Nenhuma.

---

### Linhas 22 e 23: As Variáveis de Placar e Chute
```java
22:         int score = 0;
23:         int guess;
```
* **Linha 22 (`int score = 0;`):** Cria a variável que acumulará os pontos do jogador. Inicializada com `0` porque ninguém começa o jogo com pontos ganhos.
* **Linha 23 (`int guess;`):** Declara a variável `guess` (*chute/palpite*). Ela ainda não tem valor inicial porque o jogador só vai digitar quando o jogo começar.
* **O que acontece na memória:** Dois espaços de 32 bits são reservados diretamente na Stack do método `main`.
* **Saída no console:** Nenhuma.

---

### Linha 25: Criando o Scanner
```java
25:         Scanner scanner = new Scanner(System.in);
```
* **O que faz:** Cria o objeto `scanner` configurado para escutar a entrada padrão do sistema (`System.in`), que é o teclado do seu computador.
* **O que acontece na memória:** Um objeto da classe `Scanner` é instanciado na memória Heap, e a variável de referência `scanner` na Stack aponta para ele.
* **Saída no console:** Nenhuma.

---

### Linhas 27 a 32: Os Comentários das Próximas Etapas
```java
27:         // WELCOME MESSAGE
28:         // QUESTION (loop)
29:         //      OPTIONS
30:         //      GET GUESS FROM USER
31:         //      CHECK OUR GUESS
32:         // DISPLAY FINAL SCORE
```
* **O que faz:** Mantém o roteiro visual para as próximas partes do vídeo, onde o professor programará a lógica interativa!
* **Saída no console:** Nenhuma.

---

### Linhas 33 e 34: Fechamento do Programa
```java
33:     }
34: }
```
* Fecha o método `main` e a classe `Main`.

---

## 8. Tabela Mestre do Quiz: Perguntas, Alternativas e Respostas Explicadas

Para você enriquecer seus conhecimentos de informática enquanto estuda Java, veja a tabela completa do jogo:

| Índice (`i`) | Pergunta (`questions[i]`) | Alternativas (`options[i]`) | Gabarito (`answers[i]`) | Explicação da Resposta |
| :---: | :--- | :--- | :---: | :--- |
| **0** | *What is the main function of a router?*<br>(Qual a função principal de um roteador?) | 1. Storing files<br>2. Encrypting data<br>**3. Directing internet traffic**<br>4. Managing passwords | **3** | Roteadores atuam como guardas de trânsito da rede, direcionando os pacotes de dados pela melhor rota até o destino. |
| **1** | *Which part of the computer is considered the brain?*<br>(Qual parte do computador é considerada o cérebro?) | **1. CPU**<br>2. Hard Drive<br>3. RAM<br>4. GPU | **1** | A CPU (Unidade Central de Processamento) executa todos os cálculos lógicos e matemáticos do computador. |
| **2** | *What year was Facebook launched?*<br>(Em que ano o Facebook foi lançado?) | 1. 2000<br>**2. 2004**<br>3. 2006<br>4. 2008 | **2** | O Facebook foi fundado por Mark Zuckerberg em fevereiro de 2004 na Universidade de Harvard. |
| **3** | *Who is known as the father of computers?*<br>(Quem é conhecido como o pai dos computadores?) | 1. Steve Jobs<br>2. Bill Gates<br>3. Alan Turing<br>**4. Charles Babbage** | **4** | Charles Babbage projetou o primeiro computador mecânico (o Engenho Analítico) no século XIX. |
| **4** | *What was the first programming language?*<br>(Qual foi a primeira linguagem de programação?) | 1. COBOL<br>2. C<br>**3. Fortran**<br>4. Assembly | **3** | O Fortran (Formula Translation) foi criado pela IBM em 1957, sendo a primeira linguagem de alto nível comercial. |

---

## 9. O que Sai no Console Neste Momento?

Se você rodar o programa agora:

```text
Process finished with exit code 0
```

### Por que ainda não sai nada?
Porque nós **apenas preparamos os dados na memória do computador**! Nós criamos os arrays, alocamos as variáveis e preparamos o `Scanner`, mas ainda **não demos nenhum comando `System.out.println()`** para mostrar as perguntas ao usuário. O computador sabe tudo, mas está guardando em silêncio na memória!

---

## 10. Resumo Prático em Uma Tabela Mental

| Estrutura no Código | Tipo de Dado | Quantidade de Dados | Papel no Jogo |
| :--- | :--- | :--- | :--- |
| **`questions`** | `String[]` (Array 1D) | 5 perguntas | Contém os enunciados das questões. |
| **`options`** | `String[][]` (Matriz 2D) | $5 \times 4 = 20$ alternativas | Contém as 4 opções de cada pergunta. |
| **`answers`** | `int[]` (Array 1D) | 5 respostas | O gabarito oficial com o número correto. |
| **`score`** | `int` | 1 valor (inicia em 0) | O placar de acertos do jogador. |
| **`guess`** | `int` | 1 valor (digitado na rodada) | A opção (1 a 4) que o jogador escolheu. |
| **`scanner`** | Objeto `Scanner` | 1 canal de escuta | Conecta o teclado do jogador ao programa Java. |
