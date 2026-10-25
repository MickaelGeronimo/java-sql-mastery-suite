# Java Full Course — Bro Code
## Capítulo 70: `multithreading 🧶` (Parte 9 Final Definitiva: O Fim do Jogo, `"GAME OVER!"` no Momento Certo e a Recapitulação Teórica Oficial)

---

### Metadados da Aula
- **Curso:** Java Full Course (Bro Code)
- **Capítulo:** 70 — `multithreading 🧶`
- **Parte:** 9 de 9 (Conclusão Definitiva do Capítulo 70)
- **Timestamp de Referência:** `11:30:30` até `11:31:10`
- **Arquivos do Projeto:**
  - [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
  - [`MyRunnable.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/MyRunnable.java)

---

### 1. Reconstituição Textual Completa das Telas do Vídeo (Para o Google Docs)

Como você irá copiar este conteúdo diretamente para o seu Google Docs sem as imagens visuais, aqui está a descrição minuciosa de cada uma das 5 imagens apresentadas pelo Bro Code:

```text
========================================================================================
TELA 1 (11:30:30) — O JOGO DE PING-PONG EM TEMPO REAL NO TERMINAL
========================================================================================
O terminal executa o código corrigido e sincronizado com os dois .join().
A cada 1 segundo exato, uma rodada de Ping-Pong é impressa na tela:
    GAME START!
    PING
    PONG
    PONG
    PING
    PING
    PONG
    PONG
    PING

Bro Code aponta para o terminal:
"Vejam como as duas threads estão competindo segundo a segundo!
Primeiro a thread1 gritou PING, depois a thread2 gritou PONG.
Na rodada seguinte, a thread2 acordou antes e gritou PONG primeiro!
E o mais importante: a mensagem 'GAME OVER!' ainda NÃO APARECEU! A thread main está
segurando a ansiedade esperando o jogo acabar!"

========================================================================================
TELA 2 (11:30:35) — A VITÓRIA DA SINCRONIZAÇÃO: "GAME OVER!" NO FINAL
========================================================================================
As duas threads completam o quinto ciclo do laço 'for' e morrem em paz.
Imediatamente, o bloqueio do .join() é liberado! A thread main acorda e executa a linha 23:
    GAME OVER!
    Process finished with exit code 0

Bro Code celebra:
"Aí está! Agora sim temos um programa profissional e sincronizado!
'GAME START!' abriu o jogo, as duas threads jogaram por 5 segundos inteiros em paralelo,
e 'GAME OVER!' apareceu apenas depois que a última bola bateu na mesa!"

========================================================================================
TELA 3 — REVISÃO TEÓRICA 1: DESTACANDO A PALAVRA "Multithreading"
========================================================================================
Bro Code sobe para o topo do arquivo Main.java e seleciona com o mouse a palavra:
    Multithreading
No comentário da linha 5:
    // Multithreading = Enables a program to run multiple threads concurrently

Ele recapitula:
"O que é Multithreading? É o recurso que permite a um único programa executar
múltiplas linhas de código (threads) ao mesmo tempo!"

========================================================================================
TELA 4 — REVISÃO TEÓRICA 2: DESTACANDO A PALAVRA "concurrently"
========================================================================================
Bro Code move a seleção do mouse para a palavra:
    concurrently  (concorrentemente / simultaneamente)
No mesmo comentário da linha 5:
    // Multithreading = Enables a program to run multiple threads concurrently

Ele reforça:
"'Concorrentemente' significa que as tarefas fazem progresso juntas!
Se você tem um processador com vários núcleos (como quase todo computador hoje em dia),
as threads rodam literalmente no mesmo instante de tempo em núcleos diferentes!"

========================================================================================
TELA 5 — REVISÃO TEÓRICA 3: DESTACANDO A PALAVRA "Thread"
========================================================================================
Bro Code seleciona com o mouse a palavra:
    Thread
No comentário da linha 6:
    //                  (Thread = A set of instructions that run independently)

Ele fecha a explicação com chave de ouro:
"E o que é uma Thread afinal? É um conjunto independente de instruções!
Cada thread tem seu próprio caminho, suas próprias variáveis locais e corre na sua própria
velocidade, sem precisar esperar as outras a menos que você mande com um .join()!"
========================================================================================
```

---

### 2. O Grande Resumo Teórico do Bro Code (Para Fixar de Vez)

Nas Telas 3, 4 e 5, o Bro Code destacou com o mouse os três pilares que definem a computação concorrente em Java:

```java
// Multithreading = Enables a program to run multiple threads concurrently
//                  (Thread = A set of instructions that run independently)
//                  Useful for background tasks or time-consuming operations
```

#### Os 3 Termos Explicados como para uma Criança:

1. **`Multithreading` (Múltiplas Linhas de Execução):**
   - *O que é?* É como ter dois ou mais braços trabalhando ao mesmo tempo. Em vez de usar uma mão só para desenhar e depois pintar, você usa uma mão para desenhar e a outra mão para pintar simultaneamente.
2. **`concurrently` (Concorrentemente / Simultaneamente):**
   - *O que é?* As tarefas disputam a atenção do processador e avançam juntas. Duas tarefas de 5 segundos rodaram juntas em **5 segundos no total**, em vez de levarem 10 segundos!
3. **`Thread` (A Linha de Instrução Independente):**
   - *O que é?* Um trabalhador contratado que recebe uma receita de bolo (`Runnable`) e executa os passos de cima para baixo de forma independente.

---

### 3. Códigos-Fonte Finais e Completos do Projeto

#### Arquivo: `Main.java`
```java
public class Main {
    public static void main(String[] args) {

        // Multithreading = Enables a program to run multiple threads concurrently
        //                  (Thread = A set of instructions that run independently)
        //                  Useful for background tasks or time-consuming operations

        Thread thread1 = new Thread(new MyRunnable("PING"));
        Thread thread2 = new Thread(new MyRunnable("PONG"));

        System.out.println("GAME START!");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted");
        }

        System.out.println("GAME OVER!");

    }
}
```

#### Arquivo: `MyRunnable.java`
```java
public class MyRunnable implements Runnable{

    private final String text;

    MyRunnable(String text){
        this.text = text;
    }

    @Override
    public void run(){
        for(int i = 1; i <= 5; i++){
            try {
                Thread.sleep(1000);
                System.out.println(text);
            }
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}
```

---

### 4. Análise Linha por Linha Numerada — `Main.java`

```java
1: public class Main {
2:     public static void main(String[] args) {
3: 
4:         // Multithreading = Enables a program to run multiple threads concurrently
5:         //                  (Thread = A set of instructions that run independently)
6:         //                  Useful for background tasks or time-consuming operations
7: 
8:         Thread thread1 = new Thread(new MyRunnable("PING"));
9:         Thread thread2 = new Thread(new MyRunnable("PONG"));
10: 
11:        System.out.println("GAME START!");
12: 
13:        thread1.start();
14:        thread2.start();
15: 
16:        try {
17:            thread1.join();
18:            thread2.join();
19:        } catch (InterruptedException e) {
20:            System.out.println("Main thread was interrupted");
21:        }
22: 
23:        System.out.println("GAME OVER!");
24: 
25:    }
26: }
```

- **Linha 1 (`public class Main {`):** Declaração da classe principal.
- **Linha 2 (`public static void main(String[] args) {`):** Ponto de entrada padrão da aplicação executado pela thread principal (`main`).
- **Linha 3 (` `):** Linha em branco.
- **Linha 4 (`// Multithreading = Enables a program to run multiple threads concurrently`):** Comentário oficial destacando que Multithreading executa múltiplos caminhos de código simultaneamente.
- **Linha 5 (`//                  (Thread = A set of instructions that run independently)`):** Comentário oficial definindo que cada Thread é um bloco de instruções independente.
- **Linha 6 (`//                  Useful for background tasks or time-consuming operations`):** Comentário oficial ressaltando o ganho de performance em tarefas pesadas de fundo.
- **Linha 7 (` `):** Linha em branco.
- **Linha 8 (`Thread thread1 = new Thread(new MyRunnable("PING"));`):** Cria o trabalhador 1 carregando a tarefa com a palavra `"PING"`.
- **Linha 9 (`Thread thread2 = new Thread(new MyRunnable("PONG"));`):** Cria o trabalhador 2 carregando a tarefa com a palavra `"PONG"`.
- **Linha 10 (` `):** Linha em branco.
- **Linha 11 (`System.out.println("GAME START!");`):** A thread principal imprime no console `"GAME START!"` com quebra de linha.
- **Linha 12 (` `):** Linha em branco.
- **Linha 13 (`thread1.start();`):** Dispara a thread do PING no sistema operacional.
- **Linha 14 (`thread2.start();`):** Dispara a thread do PONG no sistema operacional.
- **Linha 15 (` `):** Linha em branco.
- **Linha 16 (`try {`):** Abre a caixa de proteção contra interrupções.
- **Linha 17 (`thread1.join();`):** Pausa a thread `main` até que a `thread1` complete todas as suas 5 repetições.
- **Linha 18 (`thread2.join();`):** Pausa a thread `main` até que a `thread2` também complete todas as suas 5 repetições.
- **Linha 19 (`} catch (InterruptedException e) {`):** Captura uma interrupção se ela ocorrer durante a espera.
- **Linha 20 (`System.out.println("Main thread was interrupted");`):** Mensagem de aviso caso ocorra interrupção indevida.
- **Linha 21 (`}`):** Fecha o bloco `catch`.
- **Linha 22 (` `):** Linha em branco.
- **Linha 23 (`System.out.println("GAME OVER!");`):** Imprime a mensagem de encerramento do jogo com quebra de linha (executada com precisão apenas após a finalização de ambas as threads).
- **Linha 24 (` `):** Linha em branco.
- **Linha 25 (`}`):** Fim do método `main`.
- **Linha 26 (`}`):** Fim da classe `Main`.

---

### 5. Análise Linha por Linha Numerada — `MyRunnable.java`

```java
1: public class MyRunnable implements Runnable{
2: 
3:     private final String text;
4: 
5:     MyRunnable(String text){
6:         this.text = text;
7:     }
8: 
9:     @Override
10:    public void run(){
11:        for(int i = 1; i <= 5; i++){
12:            try {
13:                Thread.sleep(1000);
14:                System.out.println(text);
15:            }
16:            catch (InterruptedException e) {
17:                System.out.println("Thread was interrupted");
18:            }
19:        }
20:    }
21: }
```

- **Linha 1 (`public class MyRunnable implements Runnable{`):** Define a classe que assina o contrato da interface funcional `Runnable`.
- **Linha 2 (` `):** Linha em branco.
- **Linha 3 (`private final String text;`):** Campo imutável e privado para guardar o texto exclusivo da thread.
- **Linha 4 (` `):** Linha em branco.
- **Linha 5 (`MyRunnable(String text){`):** Construtor que recebe a String configuradora.
- **Linha 6 (`this.text = text;`):** Atribui o parâmetro ao campo da instância.
- **Linha 7 (`}`):** Fim do construtor.
- **Linha 8 (` `):** Linha em branco.
- **Linha 9 (`@Override`):** Anotação de sobrescrita obrigatória de `run()`.
- **Linha 10 (`public void run(){`):** O método executado pelas threads trabalhadoras.
- **Linha 11 (`for(int i = 1; i <= 5; i++){`):** Laço que executa 5 vezes.
- **Linha 12 (`try {`):** Bloco de proteção para a pausa.
- **Linha 13 (`Thread.sleep(1000);`):** Faz a thread dormir por 1000 milissegundos (1 segundo).
- **Linha 14 (`System.out.println(text);`):** Imprime no console a palavra configurada (`"PING"` ou `"PONG"`).
- **Linha 15 (`}`):** Fim do bloco `try`.
- **Linha 16 (`catch (InterruptedException e) {`):** Captura interrupção do sono.
- **Linha 17 (`System.out.println("Thread was interrupted");`):** Alerta em caso de interrupção forçada.
- **Linha 18 (`}`):** Fim do bloco `catch`.
- **Linha 19 (`}`):** Fim do laço `for`.
- **Linha 20 (`}`):** Fim do método `run()`. A thread encerra seu ciclo de vida.
- **Linha 21 (`}`):** Fim da classe `MyRunnable`.

---

### 6. Saída Exata no Console para Copiar no Google Docs

Abaixo está o texto exato do console que finalizou o Capítulo 70:

```text
GAME START!
PING
PONG
PONG
PING
PING
PONG
PONG
PING
PING
PONG
GAME OVER!

Process finished with exit code 0
```

---

### 7. Resumo Definitivo de Ouro do Capítulo 70 (`multithreading 🧶`)

Para fixar na sua mente e nunca mais esquecer:

1. **Threading (🧵) vs Multithreading (🧶):**
   - *Threading:* Uma thread adicional rodando ao lado de `main`.
   - *Multithreading:* Múltiplas threads trabalhando em paralelo ao mesmo tempo.
2. **Objeto Anônimo:**
   - Em vez de criar variáveis intermediárias, você pode passar `new MyRunnable("PING")` direto dentro de `new Thread(...)`.
3. **Passando Parâmetros para Threads:**
   - Como `run()` não aceita argumentos, usamos o **construtor** da classe (`MyRunnable(String text)`) para injetar dados específicos para cada thread.
4. **O Perigo do Código Sem Sincronização:**
   - Sem sincronização, a thread `main` não espera por ninguém e sai executando o final do código antes da hora (o erro de `"GAME OVER!"` sair no primeiro milissegundo).
5. **O Poder do `thread.join()`:**
   - O método `.join()` é a ferramenta do Java que faz a thread atual pausar e esperar a thread chamada terminar seu trabalho antes de continuar.
6. **Tratamento de Exceções (`InterruptedException`):**
   - O Java exige que chamadas bloqueantes como `Thread.sleep()` e `thread.join()` sejam protegidas por `try-catch` ou declaradas com `throws`.
