# Java Full Course — Bro Code
## Capítulo 70: `multithreading 🧶` (Parte 2: A Linha de Base, `Thread.sleep`, `InterruptedException` com `try-catch` e a Primeira Thread)

---

### Metadados da Aula
- **Curso:** Java Full Course (Bro Code)
- **Capítulo:** 70 — `multithreading 🧶`
- **Parte:** 2 (Construção da Tarefa de Contagem e Teste Inicial com 1 Thread)
- **Timestamp de Referência:** `11:24:57` até `11:26:00`
- **Arquivos do Projeto:**
  - [`MyRunnable.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/MyRunnable.java)
  - [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

---

### 1. Reconstituição Textual Completa das Telas do Vídeo (Para o Google Docs)

Como você irá copiar este material diretamente para o seu Google Docs sem as imagens visuais, aqui está a transcrição detalhada de cada uma das 5 telas exibidas pelo Bro Code no vídeo:

```text
========================================================================================
TELA 1 (11:24:57) — O ERRO VERMELHO EM Thread.sleep(1000)
========================================================================================
No editor IntelliJ IDEA, o arquivo MyRunnable.java está aberto.
Dentro do laço 'for', Bro Code digita:
    Thread.sleep(1000);

Imediatamente, o IntelliJ sublinha 'sleep(1000)' com uma linha ondulada vermelha de erro!
Aparece a janela flutuante de aviso (tooltip):
    Unhandled exception: java.lang.InterruptedException
    Surround with try/catch Alt+Shift+Enter

Bro Code explica para os alunos:
"Toda vez que mandamos uma thread dormir com Thread.sleep(), o Java nos obriga a tratar
uma exceção chamada InterruptedException! O IntelliJ nos dá um atalho: basta clicar
em 'Surround with try/catch' e ele escreve o bloco try-catch automaticamente para nós!"

========================================================================================
TELA 2 (~11:25:10) — O BLOCO try-catch GERADO AUTOMATICAMENTE
========================================================================================
Bro Code clica na sugestão rápida do IntelliJ.
O código dentro do método run() é envolvido no bloco try-catch:
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

E logo após o try-catch (ainda dentro do laço for), ele adiciona o comando de impressão:
    System.out.println(i);

Agora o loop dorme 1 segundo e imprime o número da volta (de 1 a 5)!

========================================================================================
TELA 3 (~11:25:20) — CRIANDO O OBJETO myRunnable EM Main.java
========================================================================================
Bro Code clica na aba Main.java.
Logo abaixo dos comentários conceituais de Multithreading, ele digita a linha 9:
    MyRunnable myRunnable = new MyRunnable();

Ele explica:
"Primeiro criamos uma instância da nossa classe MyRunnable. Ela é a nossa tarefa,
o conjunto de instruções que queremos executar."

========================================================================================
TELA 4 (11:25:53) — INSTANCIANDO A Thread E CLICANDO NO BOTÃO RUN
========================================================================================
Bro Code completa a preparação em Main.java:
    MyRunnable myRunnable = new MyRunnable();
    Thread thread = new Thread(myRunnable);
    thread.start();

Ele posiciona o cursor do mouse sobre o botão verde triangular de "Play" (Run 'Main.main()')
na barra superior direita do IntelliJ IDEA e clica para rodar o código.
"Vamos primeiro rodar com UMA thread para ver como ela se comporta antes de criarmos
múltiplas threads simultâneas!"

========================================================================================
TELA 5 (~11:26:00) — A CONTAGEM DE 1 A 5 NO TERMINAL
========================================================================================
A janela do terminal (Run -> Main) abre na parte inferior da tela.
Em intervalos de exatamente 1 segundo, o console imprime:
    1
    (espera 1s)
    2
    (espera 1s)
    3
    (espera 1s)
    4
    (espera 1s)
    5

    Process finished with exit code 0

Bro Code seleciona o texto "Process finished with exit code 0" com o mouse e celebra:
"Perfeito! Nossa thread contou de 1 até 5 com 1 segundo de intervalo entre cada número!
Esta é a nossa linha de base. Agora, o que acontece se quisermos DUAS threads contando
ao mesmo tempo? É isso que faremos a seguir!"
========================================================================================
```

---

### 2. O Conceito Técnico Explicado de Forma Simples

#### Por que o IntelliJ deu erro na Tela 1? (`InterruptedException`)
Em Java, existem dois tipos de erros (exceções):
1. **Unchecked Exceptions (Não verificadas):** Erros que podem acontecer em tempo de execução sem aviso prévio (ex: dividir por zero `ArithmeticException`). O compilador não te obriga a colocar `try-catch`.
2. **Checked Exceptions (Verificadas / Obrigatórias):** O Java é rigoroso e diz: *"Eu sei que isso aqui pode dar problema se alguém interromper a soneca da thread! Você é obrigado por lei a colocar um `try-catch` em volta, senão o código nem compila!"*.
O método `Thread.sleep()` é o exemplo mais clássico de Checked Exception do Java.

#### O que é a "Linha de Base" (Baseline)?
Antes de demonstrar o **Multithreading** (múltiplas threads trabalhando juntas), o professor sempre precisa mostrar como **uma única thread** se comporta. 
- Com 1 thread: o programa conta 1, 2, 3, 4, 5 de forma isolada.
- Com 2 ou mais threads (próximo passo): veremos como as threads competem pela CPU e imprimem números intercalados ou juntos!

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
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }

            System.out.println(i);
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

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

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
6: 
7:             try {
8:                 Thread.sleep(1000);
9:             } catch (InterruptedException e) {
10:                 System.out.println("Thread was interrupted");
11:             }
12: 
13:             System.out.println(i);
14:         }
15:     }
16: }
```

- **Linha 1 (`public class MyRunnable implements Runnable{`):** Define a classe pública `MyRunnable` que implementa a interface `Runnable`. Isso diz ao Java que esta classe tem uma missão executável.
- **Linha 2 (` `):** Linha em branco.
- **Linha 3 (`@Override`):** Anotação que garante a sobrescrita do método `run()`.
- **Linha 4 (`public void run(){`):** O método principal que a thread executará quando for disparada.
- **Linha 5 (`for(int i = 1; i <= 5; i++){`):** Laço que inicializa `i = 1` e repete enquanto `i <= 5`, somando 1 a cada volta (`i++`).
- **Linha 6 (` `):** Linha em branco.
- **Linha 7 (`try {`):** Abre a caixa de segurança para proteger a pausa da thread contra interrupções externas.
- **Linha 8 (`Thread.sleep(1000);`):** Pausa a thread atual por 1000 milissegundos (exatamente 1 segundo) a cada iteração.
- **Linha 9 (`} catch (InterruptedException e) {`):** Captura a exceção caso alguma outra thread acorde esta de forma inesperada.
- **Linha 10 (`System.out.println("Thread was interrupted");`):** Mensagem de alerta caso ocorra a interrupção. *(No IntelliJ gerado automaticamente, ele cria `throw new RuntimeException(e);`, ambos os formatos tratam a exceção perfeitamente)*.
- **Linha 11 (`}`):** Fecha o bloco `catch`.
- **Linha 12 (` `):** Linha em branco.
- **Linha 13 (`System.out.println(i);`):** Imprime o valor atual de `i` no console (1, 2, 3, 4, 5) com quebra de linha após cada segundo de sono.
- **Linha 14 (`}`):** Fecha o laço `for`.
- **Linha 15 (`}`):** Fecha o método `run()`. A thread encerra suas atividades aqui.
- **Linha 16 (`}`):** Fecha a classe `MyRunnable`.

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
8:         MyRunnable myRunnable = new MyRunnable();
9:         Thread thread = new Thread(myRunnable);
10:         thread.start();
11: 
12:     }
13: }
```

- **Linha 1 (`public class Main {`):** Declaração da classe `Main`.
- **Linha 2 (`public static void main(String[] args) {`):** O método principal executado pela thread padrão (`main`).
- **Linha 3 (` `):** Linha em branco.
- **Linhas 4 a 6 (Comentários teóricos):** Resumo do conceito de Multithreading e Threads independentes.
- **Linha 7 (` `):** Linha em branco.
- **Linha 8 (`MyRunnable myRunnable = new MyRunnable();`):** Instancia a classe de tarefa. Pense nisso como "imprimir a ordem de serviço do trabalho".
- **Linha 9 (`Thread thread = new Thread(myRunnable);`):** Cria um novo trabalhador (`Thread`) e entrega a ordem de serviço (`myRunnable`) nas mãos dele.
- **Linha 10 (`thread.start();`):** **LIGA O MOTOR!** Diz ao trabalhador: *"Comece a trabalhar em paralelo agora mesmo!"*. Ele vai até `MyRunnable` e começa a rodar o método `run()`.
- **Linha 11 (` `):** Linha em branco.
- **Linha 12 (`}`):** Fim do método `main`. A thread `main` termina imediatamente, mas como `thread` é uma User Thread comum (não é daemon), a JVM continua viva até a contagem de 1 a 5 terminar!
- **Linha 13 (`}`):** Fim da classe `Main`.

---

### 6. Cronograma de Execução Segundo a Segundo

| Tempo Decorrido | Thread Principal (`main`) | Thread Trabalhadora (`thread`) | Saída no Console |
| :---: | :--- | :--- | :--- |
| **0.0s** | Inicia `myRunnable`, cria `thread` e chama `.start()` | Inicia o método `run()` e entra no `for (i=1)` | *(Nada impresso ainda)* |
| **0.01s** | Chega ao fim do método `main()` e termina | Entra em `Thread.sleep(1000)` (soneca de 1s) | *(Silêncio)* |
| **1.0s** | *(Finalizada)* | Acorda do sono 1 e executa `System.out.println(1)` | `1` |
| **2.0s** | *(Finalizada)* | Acorda do sono 2 e executa `System.out.println(2)` | `2` |
| **3.0s** | *(Finalizada)* | Acorda do sono 3 e executa `System.out.println(3)` | `3` |
| **4.0s** | *(Finalizada)* | Acorda do sono 4 e executa `System.out.println(4)` | `4` |
| **5.0s** | *(Finalizada)* | Acorda do sono 5 e executa `System.out.println(5)` | `5` |
| **5.01s** | *(Finalizada)* | O laço `for` encerra, `run()` finaliza e a thread morre | `Process finished with exit code 0` |

---

### 7. Saída Exata no Console para Copiar no Google Docs

```text
1
2
3
4
5

Process finished with exit code 0
```

---

### 8. O Que Vem a Seguir no Multithreading?

Agora que o Bro Code garantiu que **uma thread** conta perfeitamente de 1 a 5 a cada segundo, a mágica do capítulo vai começar:
- Ele vai criar uma **segunda thread**: `Thread thread2 = new Thread(myRunnable);`!
- E vai disparar as duas ao mesmo tempo: `thread.start()` e `thread2.start()`!
- Como as duas vão se comportar no console? Será que elas imprimem juntas? Em ordem? Misturadas? É isso que veremos a seguir!
