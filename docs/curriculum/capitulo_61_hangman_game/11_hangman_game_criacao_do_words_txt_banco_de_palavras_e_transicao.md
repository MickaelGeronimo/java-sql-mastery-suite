# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 11: Conclusão do Protótipo, Criação do `words.txt` e Transição para Palavras Dinâmicas

---

## 🧭 Visão Geral Desta Etapa

Nesta décima primeira parte do **Capítulo 61**, o instrutor Bro Code atinge um marco importantíssimo no curso: **o protótipo com a palavra fixa `"pizza"` está 100% concluído, testado e aprovado**!

Agora começa a fase de transformação do protótipo em um **jogo real e dinâmico**:
1. **Celebração do Protótipo Perfeito (Imagem 1)**: Vemos a tela de vitória no console com 5 erros superados (`YOU WIN! The word was: pizza`).
2. **A Grande Pergunta de Design de Jogos**: De que adianta um jogo da forca se a palavra for sempre `"pizza"`? O jogador adivinharia na primeira tentativa em todas as partidas!
3. **Criação do Banco de Palavras Externo (Imagens 2, 3 e 4)**: No IntelliJ IDEA, o instrutor cria um novo arquivo de texto na raiz do projeto chamado `words.txt`.
4. **O Conteúdo de `words.txt`**: Cadastra 8 palavras de frutas em inglês (uma por linha) para servir de dicionário do jogo.
5. **Preparação no [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Imagens 4 e 5)**: O instrutor volta à aba do código principal e aponta para a linha 9 (`String word = "pizza";`), preparando os alunos para substituí-la pela leitura do arquivo e sorteio aleatório com a classe `Random`!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: CONSOLIDAÇÃO DA VITÓRIA NO PROTÓTIPO COM "pizza"
====================================================================================================
Console "Run" (Terminal inferior):
  Guess a letter: p
  Correct guess!

   o
  /|\
  /

  YOU WIN!
  The word was: pizza

  Process finished with exit code 0  <-- [Protótipo 100% validado!]

====================================================================================================
IMAGEM 2: CRIANDO O ARQUIVO words.txt NA RAIZ DO PROJETO (TIMESTAMP 10:04:29)
====================================================================================================
Árvore do Projeto (Project Explorer à esquerda):
📁 MyFirstProject
  ├── 📁 .idea
  ├── 📁 out
  ├── 📁 src
  ├── 📄 .gitignore
  └── 📄 MyFirstProject.iml

Janela Flutuante do IntelliJ IDEA (Menu New -> File):
  ┌───────────────────────────────────────────────┐
  │                   New File                    │
  │ Name: words.txt                               │
  └───────────────────────────────────────────────┘

====================================================================================================
IMAGEM 3: O CONTEÚDO DIGITADO EM words.txt (TIMESTAMP 10:05:03)
====================================================================================================
Editor de Texto (words.txt - 8 palavras, 1 por linha):
  1: apple
  2: orange
  3: banana
  4: coconut
  5: pineapple
  6: grape
  7: lime
  8: lemon

====================================================================================================
IMAGEM 4: words.txt ADICIONADO AO PROJETO E VOLTA AO Main.java (TIMESTAMP 10:05:06)
====================================================================================================
Árvore do Projeto:
📁 MyFirstProject
  ├── 📁 src
  │     └── ☕ Main.java
  └── 📄 words.txt  <-- [Arquivo de texto pronto na raiz!]

Editor de Código (Main.java - Cursor na linha 9):
   7:         // JAVA HANGMAN GAME
   8: 
   9:         String word = "pizza";  <-- [O instrutor destaca a linha que será substituída!]

====================================================================================================
IMAGEM 5: REVISÃO GERAL DO CÓDIGO ANTES DA INTEGRAÇÃO DO ARQUIVO (TIMESTAMP 10:05:15)
====================================================================================================
Editor de Código (Main.java - Rolagem pelas linhas 54 a 68):
  54:             wrongGuesses++;
  55:             System.out.println("Wrong guess!\n");
  56:         }
  57: 
  58:         if(wrongGuesses >= 6){
  59:             System.out.print(getHangmanArt(wrongGuesses));
  60:             System.out.println("GAME OVER!");
  61:             System.out.println("The word was: " + word);
  62:         }
  63: 
  64:         scanner.close();
  65:     }
  66: 
  67:     static String getHangmanArt(int wrongGuesses){...}
```

---

## 🧠 Explicação Didática Como se Fosse um Iniciante Amador em Java

### 1. A Filosofia do Desenvolvimento em Duas Fases
Veja como o instrutor Bro Code ensina programação de forma profissional:
* **Fase 1 (O Motor Lógico / Protótipo)**: Primeiro, construímos toda a mecânica do jogo com uma palavra fixa e simples (`"pizza"`). Testamos os tracinhos, a entrada do teclado, a revelação das letras, o desenho do boneco na forca, a derrota e a vitória. Tudo sem nos preocuparmos com arquivos ou exceções!
* **Fase 2 (A Alimentação de Dados)**: Agora que o motor do jogo funciona perfeitamente, podemos trocar a fonte de combustível: em vez de `"pizza"` fixa no código, conectamos o jogo a um arquivo real de texto com dezenas de palavras!

---

### 2. Por que criar o `words.txt` na raiz de `MyFirstProject` e não dentro de `src`?
No IntelliJ IDEA (e na maioria dos ambientes de desenvolvimento Java):
* O **Diretório de Trabalho Atual (Current Working Directory)** quando você clica em "Run" é a **pasta raiz do projeto** (`MyFirstProject`).
* Se colocamos `words.txt` na raiz, o caminho do arquivo no código fica extremamente simples:
  ```java
  String filePath = "words.txt";
  ```
* Isso é um **Caminho Relativo (Relative Path)**. Se você mandar essa pasta para o computador de um amigo, o jogo vai continuar funcionando na máquina dele sem dar erro de caminho!

---

### 3. Por que escrever apenas UMA palavra por linha em `words.txt`?
Repare em como o arquivo foi formatado na Imagem 3:
```text
apple
orange
banana
coconut
pineapple
grape
lime
lemon
```
* **Não usamos vírgulas, espaços nem pontos.**
* **Por quê?** Porque nos capítulos anteriores (Capítulo 59 — Read Files), aprendemos a usar o **`BufferedReader`** e o seu método mágico:
  ```java
  reader.readLine();
  ```
* O método `readLine()` lê exatamente **uma linha inteira de texto até encontrar a quebra de linha (Enter)**!
* Dessa forma, cada chamada de `readLine()` captura perfeitamente uma fruta inteira como uma `String` pronta, sem precisar fatiar texto com `split` ou se preocupar com pontuação!

---

### 4. O Que Faremos a Seguir no [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)?
Na Linha 9 do código atual, temos:
```java
String word = "pizza";
```
Nas próximas telas, o instrutor fará a mágica:
1. Criar uma lista para guardar as palavras lidas:
   ```java
   ArrayList<String> words = new ArrayList<>();
   ```
2. Abrir o arquivo `words.txt` com um bloco `try-with-resources` e `BufferedReader`.
3. Ler linha por linha e dar `words.add(line)` até o fim do arquivo.
4. Usar a classe `Random` para sortear um índice entre `0` e `words.size() - 1`.
5. Atribuir a palavra sorteada à variável `word`:
   ```java
   String word = words.get(random.nextInt(words.size()));
   ```
E como todo o nosso laço de tracinhos, `wrongGuesses`, `indexOf` e forca já foram programados de forma dinâmica na Fase 1, o jogo inteiro vai funcionar instantaneamente com a palavra sorteada!

---

## 📄 O Arquivo [`words.txt`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/words.txt) Criado no Seu Ambiente

O arquivo [`words.txt`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/words.txt) já foi criado no seu diretório de trabalho com as 8 palavras exatas do vídeo:

```text
apple
orange
banana
coconut
pineapple
grape
lime
lemon
```

---

## 🖥️ Saída do Terminal no Momento Atual

O jogo no arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) continua com o protótipo vencedor compilado e testado. O próximo passo será plugar a leitura do [`words.txt`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/words.txt) diretamente no [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)!
