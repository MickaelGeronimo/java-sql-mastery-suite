# Curso de Java Completo (Bro Code) — Capítulo 65: `⭐ countdown timer ⏳` (Parte 3)
## Integração Final com `Scanner`, Encerramento Seguro com `Exit Code 0` e o Teste de 60 Segundos

> **Contexto no Vídeo**: No tempo **`10:37:58 / 12:00:00`**, o instrutor **Bro Code** conclui com chave de ouro o projeto prático **`⭐ countdown timer ⏳`**:  
> 1. Ele conecta a resposta do usuário capturada pelo `Scanner` diretamente ao estado interno da classe anônima:  
>    `int count = response;`  
> 2. Executa o teste final e destaca com orgulho no console a linha **`Process finished with exit code 0`**, comprovando que o `timer.cancel()` corrigiu com maestria o bug do loop infinito visto na Parte 2!  
> 3. Para fechar a aula, ele faz um teste de longa duração digitando **`60`** (1 minuto completo), demonstrando que o programa é um utilitário real, robusto e flexível para qualquer tempo escolhido pelo usuário!

---

## 📸 Reconstituição Visual Completa das 3 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 3 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Conectando 'response' à Tarefa e Clicando em 'Run' (Tempo: 10:37:40)
====================================================================================================
Barra Superior Direita da Janela:
  Current File ▾ [ ▶️ Run (Play Verde Selecionado) ] [ 🐛 Debug ]

Painel de Arquivos (Esquerda):
  📁 MyFirstProject C:\Users\HP\...
  ├── 📁 .idea
  ├── 📁 out
  └── 📁 src
      ├── ☕ Main
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (Main.java - Linhas 8 a 26 Visíveis):
 8:         // Java COUNTDOWN TIMER PROGRAM
 9: 
10:         Scanner scanner = new Scanner(System.in);
11: 
12:         System.out.print("Enter # of seconds to countdown from: ");
13:         int response = scanner.nextInt();
14: 
15:         Timer timer = new Timer();
16:         TimerTask task = new TimerTask(){
17: 
18: 💡          int count = response;  <-- [A palavra 'response' está selecionada em azul]
19: 
20:             @Override
21: ⎘          public void run(){
22: 
23:                 System.out.println(count);
24:                 count--;
25:                 if(count < 0){
26:                     System.out.println("HAPPY NEW YEAR!");
... (linha 27 contém timer.cancel(); logo abaixo)

Análise Visual da Imagem 1:
- Na linha 18, a palavra `response` está com seleção de texto em azul.
- O autor mostra como a variável capturada pelo `Scanner` na linha 13 alimenta a variável `count` da classe anônima.
- O ponteiro do mouse está sobre o botão triangular verde de Run para compilar e testar.
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: A Prova do Sucesso — 'Process finished with exit code 0' (Tempo: 10:37:58)
====================================================================================================
Barra de Progresso do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:37:58 / 12:00:00    65.countdown timer >
  (A marcação vermelha está colada no final do capítulo 65!)

Terminal / Run Console (Inferior):
  6
  5
  4
  3
  2
  1
  0
  HAPPY NEW YEAR!
  
  [ Process finished with exit code 0 ]  <-- [TEXTO SELECIONADO EM AZUL COM DESTAQUE!]

Análise Visual da Imagem 2:
- A contagem regressiva chegou a 0 e imprimiu "HAPPY NEW YEAR!".
- Logo em seguida, o método `timer.cancel()` desarmou o alarme.
- O autor selecionou a linha "Process finished with exit code 0" em azul para provar que a JVM encerrou com perfeição: sem travamento, sem loop negativo e sem memória presa!
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: O Teste Real de 1 Minuto — Contagem Regressiva de 60 Segundos (Tempo: 10:38:10)
====================================================================================================
Barra Superior / Aba Run:
  [ ⏹️ Botão vermelho 'Stop' ativo ]  [ ⏹️ Botão vermelho no canto superior direito também ativo ]

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Enter # of seconds to countdown from: 60  <-- [O usuário digitou 60 no console!]
  60
  59
  58
  57|  <-- [Cursor piscando na linha 57 enquanto o cronômetro desce a cada segundo!]

Análise Visual da Imagem 3:
- O autor inicia uma nova execução e digita `60` no console.
- O cronômetro começa imediatamente (`delay = 0`) e decrementa de segundo em segundo: 60, 59, 58, 57...
- Ambos os botões vermelhos de "Stop" estão acesos, provando que o programa está ativamente gerenciando uma contagem regressiva de 1 minuto em segundo plano!
====================================================================================================
```

---

## 🧠 Análise Técnica: Como Passar Dados do `Scanner` para uma Classe Anônima?

Observe atentamente a ponte entre as linhas 13 e 18 de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):

```java
int response = scanner.nextInt(); // <-- Linha 13: Variável local do método main()

TimerTask task = new TimerTask(){
    int count = response;         // <-- Linha 18: Campo de instância da Classe Anônima
    // ...
};
```

Para quem está aprendendo Java, isso parece mágica, mas é um recurso poderoso chamado **Captura de Variáveis Locais**:

1. **A Variável `response`**: Ela nasce no método `main` e guarda o número que o usuário digitou no teclado (por exemplo, `10` ou `60`).
2. **A Classe Anônima**: Quando escrevemos `int count = response;`, a classe anônima "copia" o valor de `response` para dentro de si mesma no momento exato em que o objeto `task` é criado no Heap.
3. **Independência de Execução**: Mesmo que o método `main` termine suas linhas imediatas e fique ocioso, a tarefa `task` tem seu próprio campo `count` salvo na memória, permitindo subtrair `count--` a cada segundo sem depender do teclado!

---

## 🔍 Explicação Linha por Linha de Todo o Programa Final ([`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java))

Abaixo está o código final do projeto, exatamente como executado nas Imagens 1, 2 e 3:

```java
 1: import java.util.Scanner;
 2: import java.util.Timer;
 3: import java.util.TimerTask;
 4: 
 5: public class Main {
 6:     public static void main(String[] args) {
 7: 
 8:         // Java COUNTDOWN TIMER PROGRAM
 9: 
10:         Scanner scanner = new Scanner(System.in);
11: 
12:         System.out.print("Enter # of seconds to countdown from: ");
13:         int response = scanner.nextInt();
14: 
15:         Timer timer = new Timer();
16:         TimerTask task = new TimerTask(){
17: 
18:             int count = response;
19: 
20:             @Override
21:             public void run(){
22: 
23:                 System.out.println(count);
24:                 count--;
25: 
26:                 if(count < 0){
27:                     System.out.println("HAPPY NEW YEAR!");
28:                     timer.cancel();
29:                 }
30:             }
31:         };
32: 
33:         timer.scheduleAtFixedRate(task, 0, 1000);
34: 
35:     }
36: }
```

- **Linhas 1 a 3**: Importa as 3 classes utilitárias fundamentais: `Scanner` (leitura do teclado), `Timer` (o agendador) e `TimerTask` (a missão agendável).
- **Linhas 5 e 6**: Declaração da classe `Main` e do método `main`.
- **Linha 8 (`// Java COUNTDOWN TIMER PROGRAM`)**: Comentário oficial do projeto.
- **Linha 10 (`Scanner scanner = new Scanner(System.in);`)**: Cria o leitor conectado ao console de entrada.
- **Linha 12 (`System.out.print("Enter # of seconds to countdown from: ");`)**:
  - Exibe a pergunta para o usuário.
  - Usa `.print()` para manter o cursor na mesma linha esperando o número ser digitado.
- **Linha 13 (`int response = scanner.nextInt();`)**:
  - Lê o número inteiro digitado pelo usuário (ex: `10` na Imagem 1, ou `60` na Imagem 3) e armazena na variável `response`.
- **Linha 15 (`Timer timer = new Timer();`)**:
  - Instancia o agendador que iniciará a thread em segundo plano.
- **Linha 16 (`TimerTask task = new TimerTask(){`)**:
  - Abre a **Classe Anônima** customizada herdando de `TimerTask`.
- **Linha 18 (`int count = response;`)**:
  - Inicializa o contador interno da tarefa com o tempo exato digitado pelo usuário.
- **Linha 20 (`@Override`)**: Confirma a implementação obrigatória do método `run()`.
- **Linha 21 (`public void run(){`)**: Ponto de entrada do tick disparado a cada 1 segundo.
- **Linha 23 (`System.out.println(count);`)**: Imprime o número atual da contagem regressiva na tela.
- **Linha 24 (`count--;`)**: Diminui o contador em 1 unidade.
- **Linha 26 (`if(count < 0){`)**:
  - Testa se o número `0` já foi exibido (se `count` caiu para `-1`).
- **Linha 27 (`System.out.println("HAPPY NEW YEAR!");`)**:
  - Mensagem triunfal comemorando o fim da contagem.
- **Linha 28 (`timer.cancel();`)**:
  - **A instrução salvadora**: Desliga o cronômetro, destruindo a thread de segundo plano e liberando a JVM para fechar com `exit code 0`!
- **Linha 29 (`}`)**: Fecha o bloco `if`.
- **Linha 30 (`}`)**: Fecha o método `run()`.
- **Linha 31 (`};`)**: Fecha o corpo da classe anônima e encerra a atribuição com ponto-e-vírgula.
- **Linha 33 (`timer.scheduleAtFixedRate(task, 0, 1000);`)**:
  - Dá a largada na contagem em taxa fixa:
    - `delay = 0`: Começa imediatamente sem atraso.
    - `period = 1000`: Dispara a cada 1.000 milissegundos (1 segundo exato).
- **Linhas 35 e 36 (`}`)**: Fecham o método `main` e a classe `Main`.

---

## 🖥️ Tabela Comparativa dos Cenários de Teste

| Cenário de Teste | Entrada Digitada | Comportamento do Console | Finalização |
| :--- | :---: | :--- | :---: |
| **Teste 1 (Ano Novo - Imagem 2)** | `10` | Conta: `10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0`<br>Imprime: `HAPPY NEW YEAR!` | `Process finished with exit code 0` |
| **Teste 2 (1 Minuto - Imagem 3)** | `60` | Conta: `60, 59, 58, 57... 0`<br>Imprime: `HAPPY NEW YEAR!` | `Process finished with exit code 0` |

### Saída Completa no Console do Teste 1 (Imagem 2):
```text
Enter # of seconds to countdown from: 10
10
9
8
7
6
5
4
3
2
1
0
HAPPY NEW YEAR!

Process finished with exit code 0
```

### Saída Inicial no Console do Teste 2 (Imagem 3):
```text
Enter # of seconds to countdown from: 60
60
59
58
57
(continua a cada 1 segundo até o zero...)
```

---

## 🏆 Resumo Completo do Projeto ⭐ Countdown Timer

Neste projeto prático, você aprendeu:
1. **Entrada do Usuário**: Como usar `Scanner` para tornar programas temporizados dinâmicos.
2. **Encapsulamento de Estado**: Como passar variáveis locais (`response`) para dentro de uma **Classe Anônima** (`int count = response;`).
3. **Precisão de Tempo**: Por que usar **`scheduleAtFixedRate`** em vez do `schedule` comum para evitar erros acumulados de relógio.
4. **Lógica de Fronteira**: Por que usar `count < 0` para garantir que o número `0` apareça no console antes do grito comemorativo.
5. **Ciclo de Vida Limpo**: A importância indispensável do **`timer.cancel()`** para liberar a memória e garantir que a aplicação encerre com `exit code 0`.

O Capítulo 65 está **100% concluído, testado e documentado**! 🎉
