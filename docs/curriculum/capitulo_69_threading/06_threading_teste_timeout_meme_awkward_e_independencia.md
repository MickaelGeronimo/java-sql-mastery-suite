# Java Full Course — Bro Code
## Capítulo 69: `threading 🧵` (Parte 6 Final: O Teste de Timeout, o Meme *"Well this is awkward"*, Mistura no Console e a Independência das Threads)

---

### Metadados da Aula
- **Curso:** Java Full Course (Bro Code)
- **Capítulo:** 69 — `threading 🧵`
- **Parte:** 6 de 6 (Conclusão do Capítulo: Comportamento quando o Tempo Esgota)
- **Timestamp de Referência:** `11:21:24` até `11:22:03`
- **Arquivos do Projeto:**
  - [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
  - [`MyRunnable.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/MyRunnable.java)

---

### 1. Reconstituição Textual Completa das Telas do Vídeo (Para o Google Docs)

Como você irá copiar este conteúdo diretamente para o seu Google Docs sem ter as imagens salvas lá, aqui está a reconstituição textual fiel de cada uma das 4 telas do vídeo do Bro Code:

```text
========================================================================================
TELA 1 (11:21:24) — O SILÊNCIO E O MEME "*Well this is awkward*"
========================================================================================
No editor IntelliJ IDEA, o arquivo Main.java está aberto nas linhas:
    Line 9:   Thread thread = new Thread(myRunnable);
    Line 10:  thread.setDaemon(true);
    Line 11:  thread.start();
    Line 12:  
    Line 13:  System.out.println("You have 10 seconds to enter your name");
    Line 14:  System.out.print("Enter your name: ");

No Console (terminal integrado):
    You have 10 seconds to enter your name
    Enter your name: |  <-- (cursor piscando aguardando o usuário)

O que o Bro Code faz:
Ele NÃO digita nada! Ele larga o teclado e espera de propósito para ver o que acontece
quando os 10 segundos se esgotam.
Na tela do vídeo aparece um texto em fonte de meme:
    *Well this is awkward*  (Tradução: "*Bem, isso é constrangedor/estranho*")
O constrangimento cômico é o silêncio de 10 segundos enquanto o relógio corre!

========================================================================================
TELA 2 (~11:21:34) — O TIMER DISPARA NO MEIO DA PERGUNTA!
========================================================================================
Exatamente quando se passam os 10 segundos, a thread do timer atinge o if(i == 10).
Ela imprime no console compartilhado:
    You have 10 seconds to enter your name
    Enter your name: Time's up!|

O que está acontecendo aqui:
1. A mensagem "Time's up!" apareceu grudada logo após "Enter your name: ".
   Isso aconteceu porque "Enter your name: " usou System.out.print (SEM pular linha)!
2. O cursor continua piscando ali! A thread main NÃO morreu! O Scanner continua aberto
   esperando que você digite algo!

========================================================================================
TELA 3 (11:21:51) — O USUÁRIO DIGITA MESMO DEPOIS DO "TIME'S UP!"
========================================================================================
Bro Code finalmente digita "Bro" no teclado e aperta Enter!
Resultado no console:
    You have 10 seconds to enter your name
    Enter your name: Time's up!
    Bro
    Hello Bro

    Process finished with exit code 0

Bro Code explica a grande lição de concorrência:
"Mesmo depois do timer ter gritado 'Time's up!', o programa ainda nos deixou digitar 'Bro'
e nos cumprimentou com 'Hello Bro'! Por quê?
Porque as duas threads são INDEPENDENTES! A thread do timer apenas imprimiu um aviso no
console. Ela não tem o poder mágico de fechar o Scanner da thread main nem de cancelar
a execução dela!"

========================================================================================
TELA 4 (11:22:03) — BRO CODE DESTACA `if(i == 10)` EM MyRunnable.java
========================================================================================
Bro Code troca para a aba MyRunnable.java e seleciona com o mouse a linha 14:
    if(i == 10){
        System.out.println("Time's up!");
    }

Ele recapitula o fluxo completo da classe MyRunnable:
- O laço for conta de 1 até 10.
- A cada segundo dorme 1000ms com Thread.sleep(1000).
- Quando i chega em 10, imprime "Time's up!".
- O método run() termina e a thread morre de forma limpa.
========================================================================================
```

---

### 2. O Grande "Pulo do Gato": O Que Aconteceu nos Bastidores?

Para quem é iniciante, essa cena pode parecer confusa:
*"Se o tempo acabou e apareceu `Time's up!`, por que o programa não fechou na hora e ainda deixou digitar `Bro`?"*

#### A Analogia do Despertador na Cozinha:
Imagine que você está na sala preenchendo um formulário em papel (esta é a **Thread `main`**).
Você colocou um alarme de cozinha de 10 minutos na mesa ao lado (esta é a **Thread `thread` / Timer**).

1. O alarme toca: **BEEP BEEP BEEP ("Time's up!")**.
2. O alarme tocou, mas o papel e a caneta continuam na sua mão! O alarme **não** arrancou a caneta da sua mão, nem rasgou a folha, nem te expulsou de casa.
3. Você simplesmente ignorou o alarme, terminou de escrever seu nome ("Bro"), assinou a folha e saiu da sala ("Hello Bro").

> [!IMPORTANT]
> **A Lição Fundamental de Threads no Java:**
> As threads compartilham a tela (`System.out`), mas **cada uma tem sua própria vida e sua própria mente**.
> - A thread do timer só tinha **uma única missão**: contar até 10 e imprimir uma frase na tela. Feito isso, o trabalho dela acabou.
> - A thread `main` tinha **outra missão**: esperar o `scanner.nextLine()` receber um texto do teclado. Ela não sabia e não se importava se a outra thread imprimiu algo na tela!
> - Uma thread **NÃO** interrompe ou cancela outra thread automaticamente, a menos que você programe um comando explícito para isso (como `System.exit(0)` ou flags de controle).

---

### 3. Códigos-Fonte Completos e Idênticos aos do Vídeo

#### Arquivo: `Main.java`
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.setDaemon(true);
        thread.start();

        System.out.println("You have 10 seconds to enter your name");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);

        scanner.close();
    }
}
```

#### Arquivo: `MyRunnable.java`
```java
public class MyRunnable implements Runnable{

    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("Thread was interrupted");
            }

            if(i == 10){
                System.out.println("Time's up!");
            }
        }
    }
}
```

---

### 4. Análise Linha por Linha Numerada — `Main.java`

```java
1: import java.util.Scanner;
2: 
3: public class Main {
4:     public static void main(String[] args) {
5: 
6:         Scanner scanner = new Scanner(System.in);
7: 
8:         MyRunnable myRunnable = new MyRunnable();
9:         Thread thread = new Thread(myRunnable);
10:         thread.setDaemon(true);
11:         thread.start();
12: 
13:         System.out.println("You have 10 seconds to enter your name");
14:         System.out.print("Enter your name: ");
15:         String name = scanner.nextLine();
16:         System.out.println("Hello " + name);
17: 
18:         scanner.close();
19:     }
20: }
```

- **Linha 1 (`import java.util.Scanner;`):** Carrega a ferramenta `Scanner` da biblioteca oficial do Java para podermos ler o teclado. Console: nada.
- **Linha 2 (` `):** Espaço para respirar o código.
- **Linha 3 (`public class Main {`):** Abre a classe `Main`.
- **Linha 4 (`public static void main(String[] args) {`):** Método de início. É aqui que o Java cria a primeira thread do programa: a thread chamada **`main`**.
- **Linha 5 (` `):** Linha em branco.
- **Linha 6 (`Scanner scanner = new Scanner(System.in);`):** Prepara o objeto leitor para escutar as teclas digitadas no console.
- **Linha 7 (` `):** Linha em branco.
- **Linha 8 (`MyRunnable myRunnable = new MyRunnable();`):** Cria a folha com as instruções da tarefa (a contagem do timer).
- **Linha 9 (`Thread thread = new Thread(myRunnable);`):** Contrata um trabalhador independente (`thread`) e entrega a tarefa `myRunnable` para ele.
- **Linha 10 (`thread.setDaemon(true);`):** Define este trabalhador como **Daemon** (segundo plano/descartável). Avisa ao Java: *"Se o `main` acabar, desligue tudo, não precisa esperar por ele!"*.
- **Linha 11 (`thread.start();`):** Dá a ordem de largada para o trabalhador. Ele sai em disparada executando o método `run()` em paralelo.
- **Linha 12 (` `):** Linha em branco.
- **Linha 13 (`System.out.println("You have 10 seconds to enter your name");`):** Escreve no console: `You have 10 seconds to enter your name` e pula para a linha de baixo.
- **Linha 14 (`System.out.print("Enter your name: ");`):** Escreve no console: `Enter your name: ` (atenção: usou `print`, **NÃO** pula linha, o cursor fica grudado ali).
- **Linha 15 (`String name = scanner.nextLine();`):** **O PONTO DE CONGELAMENTO DA THREAD MAIN!** O método `main` para completamente aqui e fica mudo esperando o usuário teclar Enter. (Enquanto ele fica congelado aqui, o relógio do timer corre na outra thread!).
- **Linha 16 (`System.out.println("Hello " + name);`):** Quando o usuário finalmente digita e dá Enter, esta linha acorda e imprime `Hello ` seguido do nome digitado.
- **Linha 17 (` `):** Linha em branco.
- **Linha 18 (`scanner.close();`):** Fecha o leitor do teclado educadamente.
- **Linha 19 (`}`):** Fim do método `main`. A thread principal morre aqui.
- **Linha 20 (`}`):** Fim da classe `Main`.

---

### 5. Análise Linha por Linha Numerada — `MyRunnable.java`

```java
1: public class MyRunnable implements Runnable{
2: 
3:     @Override
4:     public void run(){
5:         for(int i = 1; i <= 10; i++){
6: 
7:             try{
8:                 Thread.sleep(1000);
9:             }
10:             catch(InterruptedException e){
11:                 System.out.println("Thread was interrupted");
12:             }
13: 
14:             if(i == 10){
15:                 System.out.println("Time's up!");
16:             }
17:         }
18:     }
19: }
```

- **Linha 1 (`public class MyRunnable implements Runnable{`):** Define que esta classe sabe ser executada por uma thread porque implementa `Runnable`.
- **Linha 2 (` `):** Linha em branco.
- **Linha 3 (`@Override`):** Garante que estamos sobrescrevendo o método oficial da interface `Runnable`.
- **Linha 4 (`public void run(){`):** O corpo de execução que a nova thread irá percorrer.
- **Linha 5 (`for(int i = 1; i <= 10; i++){`):** O loop que vai girar 10 vezes (1 volta por segundo).
- **Linha 6 (` `):** Linha em branco.
- **Linha 7 (`try{`):** Bloco de proteção para a soneca da thread.
- **Linha 8 (`Thread.sleep(1000);`):** A thread dorme por 1000 milissegundos (1 segundo exato).
- **Linha 9 (`}`):** Fim do `try`.
- **Linha 10 (`catch(InterruptedException e){`):** Se alguém acordar a thread à força, o Java cai aqui.
- **Linha 11 (`System.out.println("Thread was interrupted");`):** Mensagem de alerta se houver interrupção abrupta.
- **Linha 12 (`}`):** Fim do `catch`.
- **Linha 13 (` `):** Linha em branco.
- **Linha 14 (`if(i == 10){`):** **A LINHA QUE O BRO CODE DESTACOU!** Verifica: *"Já se passaram 10 voltas (10 segundos)?"*.
- **Linha 15 (`System.out.println("Time's up!");`):** Se sim, grita no console compartilhado: `"Time's up!"`!
- **Linha 16 (`}`):** Fim do `if`.
- **Linha 17 (`}`):** Fim do laço `for`.
- **Linha 18 (`}`):** Fim do método `run()`. A thread do timer morre aqui porque terminou seu código.
- **Linha 19 (`}`):** Fim da classe `MyRunnable`.

---

### 6. Linha do Tempo Segundo a Segundo (O Que Cada Thread Faz)

Para você entender como se estivesse vendo um filme em câmera lenta:

| Segundo | Thread Principal (`main`) | Thread Auxiliar (`thread` / Timer) | O Que Aparece na Tela do Console |
| :---: | :--- | :--- | :--- |
| **0s** | Inicia a thread secundária com `start()` | Acorda e entra no laço `for (i=1)` | *(Nada ainda)* |
| **0s** | Imprime o aviso e o prompt | Entra em `Thread.sleep(1000)` (soneca 1) | `You have 10 seconds to enter your name`<br>`Enter your name: ` |
| **0s - 9s** | **CONGELADA** na linha 15 (`scanner.nextLine`) | Dorme e acorda a cada 1s (`i` vai de 1 a 9) | `*Well this is awkward*` (Silêncio total!) |
| **10s** | Continua **CONGELADA** esperando o teclado | Acorda no `i=10`. O `if` ativa e imprime! | `Enter your name: Time's up!` |
| **10s** | Continua esperando no `scanner` | Chega no fim do `for`, sai de `run()` e **morre** | Cursor piscando na frente de `Time's up!` |
| **12s** | O usuário digita `Bro` e dá Enter! | *(Já está morta e enterrada)* | `Bro` |
| **12s** | Lê `"Bro"`, imprime `"Hello Bro"`, fecha o scanner e **termina** | *(Morta)* | `Hello Bro`<br>`Process finished with exit code 0` |

---

### 7. Por Que a Saída Ficou Misturada? (`Enter your name: Time's up!`)

Repare bem na imagem 2:
```text
Enter your name: Time's up!
```

Por que não ficou em linhas separadas?
- Na linha 14 de `Main.java`, foi usado:
  ```java
  System.out.print("Enter your name: "); // repare: print (SEM 'ln')
  ```
  O `print` comum deixa o cursor do console parado **no final da própria linha**, esperando o usuário digitar ali.
- Quando deram 10 segundos, a outra thread executou:
  ```java
  System.out.println("Time's up!");
  ```
- Como o cursor ainda estava parado bem na frente de `Enter your name: `, o texto `"Time's up!"` foi jogado **no mesmo lugar onde o cursor estava**!
- Esse é um clássico exemplo visual de **saída concorrente**: duas threads escrevendo no mesmo console ao mesmo tempo!

---

### 8. Saída Exata Completa no Console (Cenário do Vídeo)

Abaixo está o texto exato do console que você deve colocar no Google Docs:

```text
You have 10 seconds to enter your name
Enter your name: Time's up!
Bro
Hello Bro

Process finished with exit code 0
```

---

### 9. Conclusão Final do Capítulo 69: O Que Guardar Para Sempre

1. **O que é Threading?** É a capacidade do programa fazer mais de uma coisa ao mesmo tempo.
2. **Como criar uma Thread no Java?**
   - Forma 1: Herdar de `Thread` (`extends Thread`).
   - Forma 2 (Preferida): Implementar a interface `Runnable` (`implements Runnable`) e passar para `new Thread(meuRunnable)`.
3. **Como ligar o motor?** Com `.start()`, **NUNCA** com `.run()`. O `.start()` cria a pista paralela; o `.run()` roda na mesma pista e trava tudo.
4. **O que é Daemon Thread?** Uma thread que trabalha no fundo e morre automaticamente quando as threads principais do usuário finalizarem. Ativa-se com `thread.setDaemon(true)` **antes** do `thread.start()`.
5. **Threads são Independentes:** Uma thread imprimir uma mensagem não encerra ou interrompe outra thread por mágica; a coordenação entre elas exige regras claras de código.
