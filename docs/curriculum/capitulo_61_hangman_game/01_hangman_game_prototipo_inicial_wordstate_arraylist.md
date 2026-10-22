# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 1: Protótipo Inicial, `ArrayList<Character>` e Estado da Palavra

---

## 🧭 Visão Geral e Contexto do Projeto

Bem-vindo ao **Capítulo 61** do curso completo de Java do Bro Code! Aqui começamos a construir um dos projetos mais divertidos e clássicos de toda a programação: o **Java Hangman Game (Jogo da Forca em Java)**!

Nesta aula, o instrutor divide a construção do jogo em etapas lógicas e didáticas:
1. **Teaser / Demonstração do Jogo Completo** (Imagens 1 e 2): Mostra o jogo funcionando com carregamento dinâmico de palavras de um arquivo de texto (`words.txt`), interface com cabeçalho estilizado, verificação de palpites e arte ASCII da forca e do bonequinho.
2. **Tela Limpa e Arquitetura Inicial** (Imagem 3): Volta à estaca zero no IntelliJ IDEA para construir tudo do absoluto zero, passo a passo.
3. **Protótipo Inicial do Estado do Jogo** (Imagem 4): Configura a palavra secreta fixa de teste (`"pizza"`), o leitor de teclado (`Scanner`), o contador de erros (`wrongGuesses`) e a estrutura de dados central que guarda o que o jogador está vendo: um `ArrayList<Character> wordState` preenchido com sublinhados (`'_'`).

---

## 📸 Reconstituição Visual Completa das 4 Imagens (Sem Perder Nenhum Detalhe)

Como você vai copiar este material para o **Google Docs**, todas as informações visuais das telas e capturas de tela do vídeo foram reconstituídas textualmente abaixo com riqueza de detalhes:

```
====================================================================================================
IMAGEM 1: TEASER / PREVIEW DO JOGO PRONTO EM EXECUÇÃO (TIMESTAMP INICIAL)
====================================================================================================
Árvore do Projeto (Project Explorer à esquerda):
📁 MyFirstProject
  ├── 📁 .idea
  ├── 📁 src
  │     └── ☕ Main.java
  ├── 📄 words.txt   <-- [Arquivo de texto com o banco de palavras na raiz do projeto!]
  └── 📄 MyFirstProject.iml

Editor de Código (Main.java - Visão parcial do código final revelado):
  String filePath = "words.txt";
  ArrayList<String> words = new ArrayList<>();
  try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
      String line;
      while((line = reader.readLine()) != null){
          words.add(line);
      }
  }

Console / Terminal de Execução (O jogo rodando interativamente):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ _
  Guess a letter: a
  Word: _ a _ a _ a

====================================================================================================
IMAGEM 2: TEASER DA TELA DE VITÓRIA COM ARTE ASCII (ASCII ART FORCA)
====================================================================================================
Console / Terminal de Execução:
  +---+
  |   |
      |
      |
      |
      |
  =========
  YOU WIN!
  The word was: banana

  Process finished with exit code 0

====================================================================================================
IMAGEM 3: TELA LIMPA / PONTO DE PARTIDA DO TUTORIAL (TIMESTAMP ~9:45:00)
====================================================================================================
Editor de Código (Main.java completamente limpo para começar a digitar):
  1: public class Main {
  2:     public static void main(String[] args) {
  3: 
  4:         // JAVA HANGMAN GAME
  5: 
  6:     }
  7: }

====================================================================================================
IMAGEM 4: O PRIMEIRO PASSO DO CÓDIGO — PROTÓTIPO COM "pizza" (TIMESTAMP 9:46:09)
====================================================================================================
Editor de Código (Main.java com a estrutura de dados básica criada):
  1: import java.util.ArrayList;
  2: import java.util.Scanner;
  3: 
  4: public class Main {
  5:     public static void main(String[] args) {
  6: 
  7:         // JAVA HANGMAN GAME
  8: 
  9:         String word = "pizza";
 10: 
 11:         Scanner scanner = new Scanner(System.in);
 12:         ArrayList<Character> wordState = new ArrayList<>();
 13:         int wrongGuesses = 0;
 14: 
 15:         for(int i = 0; i < word.length(); i++){
 16:             wordState.add('_');
 17:         }
 18: 
 19:         System.out.println(wordState);
 20: 
 21:         scanner.close();
 22:     }
 23: }

Janela "Run" do IntelliJ IDEA (Console inferior):
  [_, _, _, _, _]

  Process finished with exit code 0
```

---

## 🧠 Entendendo a Lógica da Forca Como se Fosse um Iniciante Amador

Se você nunca programou um jogo na vida, pense no jogo da forca como uma brincadeira de papel e caneta:

1. **A Palavra Secreta**: Uma pessoa pensa em uma palavra, por exemplo: `"pizza"`.
2. **Os Traços no Papel**: A palavra `"pizza"` tem **5 letras**. O que você faz no caderno? Desenha 5 tracinhos no papel: `_ _ _ _ _`.
3. **O Palpite do Jogador**: Se o amigo chuta a letra `'z'`, você procura onde tem `'z'` na palavra secreta. Como tem nas posições 2 e 3 (contando do zero), você apaga os tracinhos correspondentes e anota: `_ _ z z _`.
4. **Os Erros**: Se o amigo chutar uma letra que não existe (ex: `'x'`), você não mexe nos tracinhos, mas desenha a cabeça do boneco na forca e aumenta o contador de erros!

### Por que começar com `"pizza"` fixa e não direto com o arquivo `words.txt`?
Na programação profissional e didática, sempre começamos pelo **caminho mais simples possível** (protótipo). Antes de nos preocuparmos em abrir arquivos com `BufferedReader`, tratar `FileNotFoundException` ou sortear números aleatórios com `Random`, precisamos garantir que a **mecânica central dos tracinhos e dos palpites** funcione perfeitamente com uma palavra fixa! Depois que tudo estiver rodando liso, nós plugamos a leitura do arquivo `words.txt`.

---

## 🔍 O Que É `ArrayList<Character>` e Por Que Não `ArrayList<char>`?

Esta é uma das dúvidas mais clássicas de quem está aprendendo Java:

> [!IMPORTANT]
> **A Regra de Ouro das Collections em Java:**  
> Coleções dinâmicas como `ArrayList`, `LinkedList` e `HashSet` **NÃO ACEITAM tipos primitivos** (`int`, `double`, `char`, `boolean`) dentro dos sinais de menor e maior `< >`. Elas aceitam **apenas Objetos / Classes de Referência**!

* O tipo primitivo `char` (com `c` minúsculo) armazena apenas um caractere bruto de 16 bits na memória.
* Para colocar caracteres dentro de um `ArrayList`, o Java fornece a sua **Wrapper Class** (classe embrulhadora): `Character` (com `C` maiúsculo).
* Graças ao recurso chamado **Autoboxing** do Java, quando você escreve `wordState.add('_')` com aspas simples, o Java automaticamente transforma o primitivo `'_'` em um objeto `Character.valueOf('_')` nos bastidores!

---

## 📜 Código-Fonte Completo Deste Passo Inicial

Este é o arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) que foi implementado e testado no seu projeto:

```java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA HANGMAN GAME

        String word = "pizza";

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }

        System.out.println(wordState);

        scanner.close();
    }
}
```

---

## 🔬 Análise Linha por Linha: O Que Cada Comando Faz e O Que Sai no Console

Vamos dissecar cada uma das 23 linhas do código:

### **Linha 1: `import java.util.ArrayList;`**
* **O que faz:** Avisa ao compilador Java que vamos usar a lista dinâmica redimensionável do pacote padrão de utilitários (`java.util`).
* **Por que é necessária:** O Java básico não carrega o `ArrayList` por padrão na memória; precisamos importá-lo.
* **Saída no console:** Nenhuma.

---

### **Linha 2: `import java.util.Scanner;`**
* **O que faz:** Importa a classe `Scanner`, necessária para capturar as entradas digitadas pelo usuário no teclado através do terminal.
* **Saída no console:** Nenhuma.

---

### **Linha 3: Linha em branco**
* **O que faz:** Organização visual do código para separar os imports da declaração da classe.
* **Saída no console:** Nenhuma.

---

### **Linha 4: `public class Main {`**
* **O que faz:** Declara a classe pública chamada `Main`. Em Java, todo código executável deve estar dentro de uma classe cujo nome seja idêntico ao do arquivo (`Main.java`).
* **Saída no console:** Nenhuma.

---

### **Linha 5: `    public static void main(String[] args) {`**
* **O que faz:** O método principal e ponto de entrada da aplicação. A JVM (Máquina Virtual Java) começa a execução exatamente aqui.
* **Saída no console:** Nenhuma.

---

### **Linha 6: Linha em branco**
* **O que faz:** Organização e legibilidade.
* **Saída no console:** Nenhuma.

---

### **Linha 7: `        // JAVA HANGMAN GAME`**
* **O que faz:** Comentário de linha única para documentar o título do projeto. O compilador ignora completamente.
* **Saída no console:** Nenhuma.

---

### **Linha 8: Linha em branco**
* **O que faz:** Espaçamento visual.
* **Saída no console:** Nenhuma.

---

### **Linha 9: `        String word = "pizza";`**
* **O que faz:** Declara uma variável do tipo `String` chamada `word` e atribui a ela o texto `"pizza"`.
* **Propósito no jogo:** Esta é a nossa **palavra secreta** temporária para testarmos o funcionamento do jogo.
* **Tamanho:** `"pizza"` possui exatamente 5 letras (índices 0 a 4: `p`, `i`, `z`, `z`, `a`).
* **Saída no console:** Nenhuma.

---

### **Linha 10: Linha em branco**
* **O que faz:** Espaçamento visual.
* **Saída no console:** Nenhuma.

---

### **Linha 11: `        Scanner scanner = new Scanner(System.in);`**
* **O que faz:** Instancia (cria) um novo objeto leitor chamado `scanner`, conectado ao fluxo padrão de entrada do sistema (`System.in`, que é o teclado).
* **Propósito no jogo:** Será usado nas próximas etapas para ler os palpites de letras digitados pelo jogador.
* **Saída no console:** Nenhuma.

---

### **Linha 12: `        ArrayList<Character> wordState = new ArrayList<>();`**
* **O que faz:** Cria uma nova lista dinâmica vazia na memória heap, chamada `wordState` (estado da palavra), capaz de armazenar objetos do tipo `Character`.
* **Propósito no jogo:** Esta lista vai guardar o que o jogador pode ver no momento. Se uma letra ainda não foi adivinhada, a lista terá um sublinhado `'_'`. Quando a letra for adivinhada, o sublinhado daquela posição será trocado pela letra real!
* **Estado inicial neste ponto:** `[]` (lista vazia, tamanho 0).
* **Saída no console:** Nenhuma.

---

### **Linha 13: `        int wrongGuesses = 0;`**
* **O que faz:** Cria uma variável inteira primitiva chamada `wrongGuesses` (palpites errados) com valor inicial `0`.
* **Propósito no jogo:** Contador de erros. Toda vez que o jogador chutar uma letra que não existe na palavra, somaremos `wrongGuesses++` até atingir o limite máximo (geralmente 6 partes do corpo da forca).
* **Saída no console:** Nenhuma.

---

### **Linha 14: Linha em branco**
* **O que faz:** Espaçamento visual.
* **Saída no console:** Nenhuma.

---

### **Linhas 15 a 17: O Laço `for` de Inicialização**
```java
        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }
```
* **O que faz:** Executa um loop que se repete tantas vezes quantas letras a palavra `word` tiver (`word.length()`).
  * Como a palavra é `"pizza"`, `word.length()` vale `5`.
  * O índice `i` começa em `0` e vai até `4` (enquanto `i < 5`).
* **Passo a passo das 5 iterações do laço:**
  1. **Iteração 0 (`i = 0`):** `0 < 5` é verdadeiro $\rightarrow$ executa `wordState.add('_')`. A lista fica: `['_']`.
  2. **Iteração 1 (`i = 1`):** `1 < 5` é verdadeiro $\rightarrow$ executa `wordState.add('_')`. A lista fica: `['_', '_']`.
  3. **Iteração 2 (`i = 2`):** `2 < 5` é verdadeiro $\rightarrow$ executa `wordState.add('_')`. A lista fica: `['_', '_', '_']`.
  4. **Iteração 3 (`i = 3`):** `3 < 5` é verdadeiro $\rightarrow$ executa `wordState.add('_')`. A lista fica: `['_', '_', '_', '_']`.
  5. **Iteração 4 (`i = 4`):** `4 < 5` é verdadeiro $\rightarrow$ executa `wordState.add('_')`. A lista fica: `['_', '_', '_', '_', '_']`.
  6. **Iteração 5 (`i = 5`):** `5 < 5` é falso $\rightarrow$ o laço termina e o programa avança para a linha 18.
* **Por que aspas simples `'_'` e não aspas duplas `"_"`?**  
  Porque a lista é de `Character` (caractere único). Em Java:
  * Aspas simples `'_'` = literal do tipo `char`.
  * Aspas duplas `"_"` = literal do tipo `String`.
* **Saída no console:** Nenhuma (as operações ocorrem silenciosamente na memória RAM).

---

### **Linha 18: Linha em branco**
* **O que faz:** Espaçamento visual.
* **Saída no console:** Nenhuma.

---

### **Linha 19: `        System.out.println(wordState);`**
* **O que faz:** Imprime no console a representação textual do objeto `wordState`.
* **Como o Java imprime um `ArrayList`?**  
  A classe `ArrayList` sobrescreve o método `toString()`. Ela percorre todos os seus elementos e monta um texto com colchetes `[` e `]` nas pontas, separando cada elemento por vírgula e espaço.
* **Saída EXATA no console:**
  ```text
  [_, _, _, _, _]
  ```

---

### **Linha 20: Linha em branco**
* **O que faz:** Espaçamento visual.
* **Saída no console:** Nenhuma.

---

### **Linha 21: `        scanner.close();`**
* **O que faz:** Fecha o leitor `scanner`, liberando o recurso de sistema operacional associado ao teclado.
* **Saída no console:** Nenhuma.

---

### **Linha 22: `    }`**
* **O que faz:** Fecha o bloco do método `main`.
* **Saída no console:** Nenhuma.

---

### **Linha 23: `}`**
* **O que faz:** Fecha o bloco da classe `Main`.
* **Saída no console:** Nenhuma.

---

## 📊 Tabela de Rastreamento de Memória (Memory Snapshot)

Veja como as variáveis na memória do computador ficam exatamente após a execução da linha 19:

| Variável | Tipo | Valor na Memória | Descrição |
| :--- | :--- | :--- | :--- |
| `word` | `String` | `"pizza"` | Palavra secreta fixa de 5 letras |
| `scanner` | `Scanner` | `Referência para objeto Scanner` | Leitor de teclado aberto |
| `wrongGuesses` | `int` | `0` | Contador de erros inicializado em zero |
| `wordState` | `ArrayList<Character>` | `['_', '_', '_', '_', '_']` | Lista com 5 caracteres sublinhados representando as letras ocultas |
| `i` | `int` | *Destruída* (escopo local ao laço `for`) | Variável contadora do loop |

---

## 🖥️ Resultado Final da Execução no Terminal

Quando você executa este código no terminal (`javac Main.java` seguido de `java Main`), este é o resultado exato e completo impresso na tela:

```text
[_, _, _, _, _]
```

---

## 🚀 O Que Vem a Seguir nas Próximas Partes?

Agora que a base do jogo está montada e a lista de tracinhos está funcionando:
1. **Loop Principal do Jogo (`while`)**: O jogo precisa continuar rodando enquanto o jogador não tiver perdido (ex: `wrongGuesses < 6`) e ainda houver letras para adivinhar (`wordState.contains('_')`).
2. **Entrada do Palpite (`scanner.next()`)**: Pedir ao usuário: `"Guess a letter: "`.
3. **Verificação de Acerto**: Se a letra digitada estiver dentro de `"pizza"`, vamos descobrir em quais índices ela está e atualizar o `wordState` naquela posição!
4. **Arte ASCII da Forca**: Criar um método que desenha o bonequinho na forca dependendo do número de `wrongGuesses`.
5. **Carregar de `words.txt`**: Substituir a palavra fixa `"pizza"` por uma palavra sorteada de um arquivo real de texto!
