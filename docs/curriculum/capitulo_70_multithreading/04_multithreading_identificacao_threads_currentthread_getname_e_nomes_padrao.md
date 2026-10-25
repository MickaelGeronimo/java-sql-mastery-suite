# Java Full Course — Bro Code
## Capítulo 70: `multithreading 🧶` (Parte 4: Identificação de Threads com `Thread.currentThread().getName()`, Nomes Padrão e o Efeito `PING`)

---

### Metadados da Aula
- **Curso:** Java Full Course (Bro Code)
- **Capítulo:** 70 — `multithreading 🧶`
- **Parte:** 4 (Diferenciação de Threads, Leitura de Crachá com `getName()`, Concorrência Real e Nomes Padrão)
- **Timestamp de Referência:** `11:27:15` até `11:27:52`
- **Arquivos do Projeto:**
  - [`MyRunnable.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/MyRunnable.java)
  - [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

---

### 1. Reconstituição Textual Completa das Telas do Vídeo (Para o Google Docs)

Como você irá copiar este conteúdo diretamente para o seu Google Docs sem as imagens visuais, aqui está a descrição minuciosa de cada uma das 5 telas apresentadas pelo Bro Code:

```text
========================================================================================
TELA 1 (11:27:15) — DIGITANDO Thread.currentThread().get... EM MyRunnable.java
========================================================================================
No editor IntelliJ IDEA, o arquivo MyRunnable.java está aberto.
Dentro do método run(), no bloco try, Bro Code começa a digitar:
    System.out.println(Thread.currentThread().get);

Bro Code explica o dilema da aula anterior:
"Na aula anterior nós vimos os números saírem duplicados: 1, 1, 2, 2, 3, 3...
Mas como nós sabemos QUAL thread imprimiu qual número? Elas parecem gêmeas idênticas!
Para descobrir quem está trabalhando, o Java tem uma ferramenta excelente:
Thread.currentThread()!"

========================================================================================
TELA 2 (11:27:30) — VOLTANDO PARA Main.java PARA EXECUTAR
========================================================================================
Bro Code troca para a aba Main.java e move o cursor do mouse até o botão verde de 'Play'
(Run) no canto superior direito da tela do IntelliJ.

========================================================================================
TELA 3 — A LINHA COMPLETA COM Thread.currentThread().getName()
========================================================================================
Em MyRunnable.java, a linha 8 foi finalizada e aparece destacada em azul:
    System.out.println(Thread.currentThread().getName() + " " + i);

Ele explica a anatomia da linha:
- Thread.currentThread(): Pergunta à JVM: "Qual é a thread que está executando esta linha agora?"
- .getName(): Pega o nome oficial registrado dessa thread no sistema.
- + " " + i: Adiciona um espaço e o número atual do loop (de 1 a 5).

========================================================================================
TELA 4 — O TERMINAL MOSTRA OS NOMES Thread-0 E Thread-1 (COM UMA ULTRAPASSAGEM!)
========================================================================================
Bro Code roda o programa. O terminal abre e imprime:
    Thread-0 2
    Thread-1 2
    Thread-0 3
    Thread-1 3
    Thread-1 4   <-- [REPARE AQUI: Thread-1 IMPRIMIU PRIMEIRO!]
    Thread-0 4   <-- [Thread-0 IMPRIMIU DEPOIS!]
    Thread-0 5
    Thread-1 5

    Process finished with exit code 0

(Nota: os números 1 rolaram para cima na tela).
Bro Code chama a atenção para um detalhe vital da ciência da computação:
"Olhem para a volta 4! A Thread-1 imprimiu o número 4 ANTES da Thread-0!
Em programação com threads, a ordem NUNCA é 100% garantida! As threads correm
independentes, e quem acordar primeiro da soneca de 1s imprime primeiro!"

========================================================================================
TELA 5 (11:27:52) — O MEME "PING" E A PREPARAÇÃO PARA NOMES PERSONALIZADOS
========================================================================================
Bro Code volta para Main.java e seleciona a palavra 'thread1' na linha 9.
Na tela do vídeo aparece uma animação com o som e texto:
    PING
Ele explica:
"Por padrão, o Java dá nomes genéricos e sem graça: 'Thread-0' e 'Thread-1'.
Mas nós podemos batizar nossas threads com os nomes que quisermos!
Por exemplo, uma pode se chamar PING e a outra PONG!"
========================================================================================
```

---

### 2. Os Dois Grandes Conceitos Explicados para Iniciantes

#### Conceito 1: O Que é `Thread.currentThread().getName()`? (A Analogia do Crachá)
Imagine uma fábrica onde dois funcionários contratados (`thread1` e `thread2`) realizam a mesma tarefa: embalar caixas de 1 a 5 (`MyRunnable`).
- Como a tarefa é exatamente a mesma, os dois usam o mesmo manual de instruções.
- Mas cada funcionário tem um **crachá preso no peito** com seu nome gravado.
- Quando o funcionário grita no interfone da fábrica (`System.out.println`), ele lê o próprio crachá antes de falar:
  - `Thread.currentThread()` = Coloca a mão no próprio crachá.
  - `.getName()` = Lê o nome escrito no crachá.
  - Resultado no interfone: `"Thread-0: caixa 1!"`, `"Thread-1: caixa 1!"`.

#### Conceito 2: De Onde Surgiram os Nomes `Thread-0` e `Thread-1`?
Se você criar um cachorro e não der um nome para ele, o abrigo vai chamá-lo de *"Cachorro 0"*, *"Cachorro 1"*, etc.
O Java faz a mesma coisa:
- A primeira `new Thread()` criada no programa recebe automaticamente o nome **`Thread-0`**.
- A segunda `new Thread()` criada recebe o nome **`Thread-1`**.
- A terceira receberia **`Thread-2`**, e assim por diante.

#### Conceito 3: A Dança da Concorrência (Por que a Thread-1 passou na frente?)
Olhe atentamente para o que aconteceu na volta número 4:
```text
Thread-1 4
Thread-0 4
```
Nas voltas 2 e 3, a `Thread-0` imprimiu primeiro. Mas na volta 4, a `Thread-1` imprimiu antes!
- **Por que isso acontece?**
  Porque ambas as threads dormiram por 1000 milissegundos. Mas o relógio do sistema operacional e o agendador de tarefas (*Thread Scheduler*) lidam com frações minúsculas de microssegundos. Uma thread pode acordar 0.0001 segundos antes da outra.
- **Regra de Ouro:** Em Multithreading, **a ordem de execução nunca é rígida ou previsível**. Threads concorrentes são corredores livres na pista!

---

### 3. Códigos-Fonte Completos e Atualizados

#### Arquivo: `MyRunnable.java`
```java
public class MyRunnable implements Runnable{

    @Override
    public void run(){
        for(int i = 1; i <= 5; i++){
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}
```

#### Arquivo: `Main.java`
```java
public class Main {
    public static void main(String[] args) {

        // Multithreading = Enables a program to run multiple threads concurrently
        //                  (Thread = A set of instructions that run independently)
        //                  Useful for background tasks or time-consuming operations

        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());

        thread1.start();
        thread2.start();

    }
}
```

---

### 4. Análise Linha por Linha Numerada — `MyRunnable.java`

```java
1: public class MyRunnable implements Runnable{
2: 
3:     @Override
4:     public void run(){
5:         for(int i = 1; i <= 5; i++){
6:             try {
7:                 Thread.sleep(1000);
8:                 System.out.println(Thread.currentThread().getName() + " " + i);
9:             }
10:             catch (InterruptedException e) {
11:                 System.out.println("Thread was interrupted");
12:             }
13:         }
14:     }
15: }
```

- **Linha 1 (`public class MyRunnable implements Runnable{`):** Declara a classe `MyRunnable` implementando a interface `Runnable`. É o molde da tarefa que será compartilhada pelas threads.
- **Linha 2 (` `):** Linha em branco.
- **Linha 3 (`@Override`):** Anotação que valida a implementação correta do método `run()`.
- **Linha 4 (`public void run(){`):** O ponto de entrada da tarefa de cada thread individual.
- **Linha 5 (`for(int i = 1; i <= 5; i++){`):** Laço que conta de 1 a 5. Cada thread que entra aqui possui sua própria variável `i` independente na sua própria pilha de memória (*stack*).
- **Linha 6 (`try {`):** Início do bloco de proteção.
- **Linha 7 (`Thread.sleep(1000);`):** Faz a thread adormecer por 1000 milissegundos (1 segundo).
- **Linha 8 (`System.out.println(Thread.currentThread().getName() + " " + i);`):** **A GRANDE NOVIDADE DA AULA!** 
  - `Thread.currentThread()` obtém a referência da thread que está rodando essa linha no exato momento.
  - `.getName()` busca o nome atribuído a ela (ex: `Thread-0` ou `Thread-1`).
  - Imprime o nome seguido de um espaço e o número da contagem atual (`i`).
- **Linha 9 (`}`):** Fecha o bloco `try`.
- **Linha 10 (`catch (InterruptedException e) {`):** Captura uma eventual interrupção forçada do sono da thread.
- **Linha 11 (`System.out.println("Thread was interrupted");`):** Mensagem caso ocorra interrupção.
- **Linha 12 (`}`):** Fecha o bloco `catch`.
- **Linha 13 (`}`):** Fecha o laço `for`.
- **Linha 14 (`}`):** Fecha o método `run()`.
- **Linha 15 (`}`):** Fecha a classe `MyRunnable`.

---

### 5. Análise Linha por Linha Numerada — `Main.java`

```java
1: public class Main {
2:     public static void main(String[] args) {
3: 
4:         // Multithreading = Enables a program to run multiple threads concurrently
5:         //                  (Thread = A set of instructions that run independently)
6:         //                  Useful for background tasks or time-consuming operations
7: 
8:         Thread thread1 = new Thread(new MyRunnable());
9:         Thread thread2 = new Thread(new MyRunnable());
10: 
11:         thread1.start();
12:         thread2.start();
13: 
14:     }
15: }
```

- **Linha 1 (`public class Main {`):** Declara a classe principal `Main`.
- **Linha 2 (`public static void main(String[] args) {`):** Método de entrada executado pela thread principal (`main`).
- **Linha 3 (` `):** Linha em branco.
- **Linhas 4 a 6 (Comentários teóricos):** Explicação da definição e utilidade prática do Multithreading.
- **Linha 7 (` `):** Linha em branco.
- **Linha 8 (`Thread thread1 = new Thread(new MyRunnable());`):** Instancia a primeira thread. Como nenhum nome foi passado no construtor, o Java batiza ela internamente como `"Thread-0"`.
- **Linha 9 (`Thread thread2 = new Thread(new MyRunnable());`):** Instancia a segunda thread. O Java batiza ela internamente como `"Thread-1"`.
- **Linha 10 (` `):** Linha em branco.
- **Linha 11 (`thread1.start();`):** Aciona o gatilho da `thread1`.
- **Linha 12 (`thread2.start();`):** Aciona o gatilho da `thread2`. Ambas começam a correr em paralelo.
- **Linha 13 (` `):** Linha em branco.
- **Linha 14 (`}`):** Fim do método `main`.
- **Linha 15 (`}`):** Fim da classe `Main`.

---

### 6. Saída Exata no Console para o Google Docs

Abaixo está a saída completa e real gerada pela execução:

```text
Thread-0 1
Thread-1 1
Thread-0 2
Thread-1 2
Thread-0 3
Thread-1 3
Thread-1 4
Thread-0 4
Thread-0 5
Thread-1 5

Process finished with exit code 0
```

> [!NOTE]
> Observe a linha 7 e 8: no segundo 4, a `Thread-1` acordou uma fração de milissegundo antes da `Thread-0` e imprimiu primeiro! Isso prova que as duas threads rodam verdadeiramente de forma paralela e independente!

---

### 7. O Que Significa o Meme `PING` na Tela 5?

Na última tela do vídeo, o Bro Code destacou `thread1` e colocou o meme com som `PING`:
- Ele percebeu que nomes como `Thread-0` e `Thread-1` são impessoais e confusos de ler.
- Ele dará um apelido personalizado para cada uma: uma thread será o **PING** e a outra será o **PONG**!
- No próximo bloco de código, veremos como configurar o construtor da classe `MyRunnable` ou usar métodos para dar nomes personalizados a cada thread!
