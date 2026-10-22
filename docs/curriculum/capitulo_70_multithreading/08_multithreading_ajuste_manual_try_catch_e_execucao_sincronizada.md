# Java Full Course — Bro Code
## Capítulo 70: `multithreading 🧶` (Parte 8: Ajuste Manual do `try-catch`, Agrupando os Dois `join()` e a Execução Sincronizada)

---

### Metadados da Aula
- **Curso:** Java Full Course (Bro Code)
- **Capítulo:** 70 — `multithreading 🧶`
- **Parte:** 8 (Refinamento do Tratamento de Exceções, Agrupamento de Threads no `try` e Início da Execução Correta)
- **Timestamp de Referência:** `11:30:10` até `11:30:30`
- **Arquivos do Projeto:**
  - [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)
  - [`MyRunnable.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/MyRunnable.java)

---

### 1. Reconstituição Textual Completa das Telas do Vídeo (Para o Google Docs)

Como você irá copiar este conteúdo diretamente para o seu Google Docs sem as imagens visuais, aqui está a descrição minuciosa de cada uma das 4 imagens apresentadas pelo Bro Code:

```text
========================================================================================
TELA 1 (11:30:10) — A PEGADINHA DO INTELLIJ: thread2.join() FICOU DE FORA!
========================================================================================
No editor IntelliJ IDEA, o arquivo Main.java mostra o resultado do atalho automático:
    try {
        thread1.join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    thread2.join();  <-- [ERRO VERMELHO CONTINUA AQUI!]

Bro Code aponta com o cursor e alerta os alunos:
"Cuidado com os atalhos automáticos das IDEs! O IntelliJ colocou o try-catch apenas
em volta do thread1.join(). O thread2.join() ficou de fora e continua com erro vermelho
de 'Unhandled exception: InterruptedException'!"

========================================================================================
TELA 2 (11:30:20) — O AJUSTE MANUAL PERFEITO
========================================================================================
Bro Code faz duas alterações manuais para deixar o código impecável:
1. Move 'thread2.join();' para DENTRO do mesmo bloco 'try', logo abaixo de 'thread1.join();'.
2. Substitui 'throw new RuntimeException(e);' por uma mensagem amigável:
   System.out.println("Main thread was interrupted");

Código resultante em Main.java:
    try {
        thread1.join();
        thread2.join();
    }
    catch (InterruptedException e) {
        System.out.println("Main thread was interrupted");
    }

    System.out.println("GAME OVER!");

Todos os erros vermelhos desaparecem! O código está 100% pronto e limpo!

========================================================================================
TELA 3 (11:30:30) — CLICANDO NO PLAY E O "GAME START!" APARECE NO CONSOLE
========================================================================================
Bro Code clica no botão verde de 'Play' (Run) no canto superior direito.
O terminal abre e imprime imediatamente:
    GAME START!

O cursor fica piscando aguardando!
Bro Code comemora:
"Olhem para o terminal! 'GAME OVER!' NÃO apareceu colado no 'GAME START!' como antes!
A thread main bateu nos dois .join() e parou! Ela está congelada esperando pacientemente
as duas threads terminarem a partida de Ping-Pong!"

========================================================================================
TELA 4 — O TERMINAL EM PLENA EXECUÇÃO DAS THREADS
========================================================================================
Mostra o console com a mensagem 'GAME START!' no topo enquanto as threads PING e PONG
começam a trocar mensagens em segundo plano a cada segundo.
A sincronização com join() funcionou com sucesso absoluto!
========================================================================================
```

---

### 2. O Que Aprendemos Com Esse Ajuste Manual?

#### Por que colocar os dois `join()` dentro do mesmo `try`?
Quando você tem mais de uma thread para esperar:
```java
// Forma RUIM e Desorganizada (dois blocos try-catch separados):
try {
    thread1.join();
} catch (InterruptedException e) { ... }

try {
    thread2.join();
} catch (InterruptedException e) { ... }

// Forma ELEGANTE e Profissional (um único bloco try para todas):
try {
    thread1.join();
    thread2.join();
} catch (InterruptedException e) { ... }
```
- Se o bloco `try` protege contra `InterruptedException`, você pode colocar quantas chamadas de `.join()` quiser dentro do mesmo bloco!
- Isso reduz pela metade a quantidade de linhas de código e deixa a leitura muito mais agradável.

#### Por que trocar `throw new RuntimeException(e)` por `System.out.println(...)`?
- O comando `throw new RuntimeException(e)` gerado pelo IntelliJ é uma resposta violenta: se a thread sofrer uma interrupção, ele **estoura uma exceção não-checada e fecha o programa abruptamente**.
- Ao substituir por `System.out.println("Main thread was interrupted");`, você apenas avisa no console o que aconteceu de forma elegante, permitindo que o programa finalize educadamente.

---

### 3. Códigos-Fonte Completos e Atualizados

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

- **Linhas 1 a 7:** Definição da classe, do método `main` e resumo conceitual de Multithreading.
- **Linhas 8 e 9:** Criação das duas threads com suas tarefas de PING e PONG.
- **Linha 10:** Linha em branco.
- **Linha 11 (`System.out.println("GAME START!");`):** A thread `main` imprime no console o texto `"GAME START!"` com quebra de linha.
- **Linha 12:** Linha em branco.
- **Linhas 13 e 14 (`thread1.start(); thread2.start();`):** Lança as duas threads na pista do processador. Ambas começam a contar e dormir em paralelo.
- **Linha 15:** Linha em branco.
- **Linha 16 (`try {`):** Início da caixa de proteção.
- **Linha 17 (`thread1.join();`):** A thread `main` para e espera a `thread1` terminar suas 5 voltas.
- **Linha 18 (`thread2.join();`):** A thread `main` também espera a `thread2` terminar suas 5 voltas.
- **Linha 19 (`} catch (InterruptedException e) {`):** Captura uma eventual interrupção externa da espera.
- **Linha 20 (`System.out.println("Main thread was interrupted");`):** Mensagem de alerta caso a espera seja violada.
- **Linha 21 (`}`):** Fim do bloco `catch`.
- **Linha 22:** Linha em branco.
- **Linha 23 (`System.out.println("GAME OVER!");`):** **O PONTO DE VITÓRIA:** Esta linha só é alcançada quando AMBAS as threads tiverem concluído suas tarefas com êxito!
- **Linha 24:** Linha em branco.
- **Linha 25 (`}`):** Fim do método `main`.
- **Linha 26 (`}`):** Fim da classe `Main`.

---

### 5. Cronograma de Execução Segundo a Segundo no Terminal

| Momento | O Que a Thread `main` Faz | O Que as Threads Filhas Fazem | O Que o Usuário Vê no Console |
| :---: | :--- | :--- | :--- |
| **0.00s** | Imprime `"GAME START!"` e chama `.start()` | Inicializam e entram em `Thread.sleep(1000)` | `GAME START!` |
| **0.01s** | Atinge `thread1.join()` e **CONGELA** | Dormindo (1º segundo) | *(Cursor piscando após GAME START!)* |
| **1.00s** | Permanece **CONGELADA** no `join()` | Acordam e imprimem PING / PONG | `PING`<br>`PONG` |
| **2.00s** | Permanece **CONGELADA** no `join()` | Acordam e imprimem PING / PONG | `PING`<br>`PONG` |
| **3.00s** | Permanece **CONGELADA** no `join()` | Acordam e imprimem PONG / PING | `PONG`<br>`PING` |
| **4.00s** | Permanece **CONGELADA** no `join()` | Acordam e imprimem PONG / PING | `PONG`<br>`PING` |
| **5.00s** | Permanece **CONGELADA** no `join()` | Acordam e imprimem a 5ª rodada e morrem | `PING`<br>`PONG` |
| **5.01s** | As threads filhas morreram! `join()` é liberado! `main` acorda e executa linha 23! | *(Finalizadas e destruídas)* | **`GAME OVER!`**<br>`Process finished with exit code 0` |

---

### 6. Saída Exata no Console para Copiar no Google Docs

```text
GAME START!
PING
PONG
PING
PONG
PONG
PING
PONG
PING
PING
PONG
GAME OVER!

Process finished with exit code 0
```

*(Perceba a harmonia perfeita da saída: o jogo abre com `GAME START!`, roda os 10 lances em 5 segundos, e fecha com `GAME OVER!` no final exato da partida!)*
