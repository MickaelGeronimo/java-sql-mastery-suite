# Curso de Java — Bro Code (12 Horas)
## Capítulo 69: `threading 🧵` (Parte 4 — A Partida das Threads: `new Thread(myRunnable)`, `thread.start()` e a Execução Paralela Real)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa e minuciosa das **5 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:19:15** e **11:20:05** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: os códigos de `Main.java`, a criação do objeto `Thread`, o disparo com `.start()`, e os dois testes reais no console (o teste de tempo esgotado e o teste com digitação rápida) foram desenhados em formato de texto para que sua apostila fique 100% independente das imagens originais.

---

## 1. O Que o Autor Fez Aqui? (História e Analogia para Iniciantes)

Na aula passada (Parte 3), nós criamos o nosso trabalhador especialista em relógio: a classe `MyRunnable.java`. O método `run()` dela já sabe contar até 5 e dormir 1 segundo a cada volta.

Agora, o autor volta para o `Main.java` para fazer o que todo chefe de empresa faz: **contratar o funcionário e colocá-lo para trabalhar!**

### A Analogia do Apito de Largada
1. **Passo 1 — A Ficha de Trabalho (`MyRunnable myRunnable = new MyRunnable();`)**:
   - O autor cria a tarefa. É como imprimir a folha de instruções que diz: *"Conte de 1 até 5 e grite Time's up!"*.
2. **Passo 2 — O Trabalhador Físico (`Thread thread = new Thread(myRunnable);`)**:
   - A folha de papel não se mexe sozinha. Precisamos de uma pessoa de verdade para segurar o papel e executar!
   - Criamos um objeto da classe `Thread` e passamos a nossa tarefa `myRunnable` dentro dos parênteses. Agora a Thread sabe exatamente qual é o trabalho dela.
3. **Passo 3 — O Apito de Largada (`thread.start();`)**:
   - **CUIDADO!** O autor não chamou `thread.run()`, ele chamou `thread.start()`!
   - O comando `.start()` dá o apito de largada! Ele avisa ao Windows / Sistema Operacional:
     > *"Por favor, crie um NOVO FIO DE EXECUÇÃO na CPU e mande ele rodar a tarefa do `myRunnable` em segundo plano!"*
4. **O Milagre da Concorrência**:
   - A Thread 2 sai correndo em segundo plano contando o tempo.
   - Enquanto isso, a Thread 1 (`main`) NÃO TRAVA MAIS! Ela desce imediatamente para a linha seguinte, imprime `"Enter your name: "` e fica esperando você digitar!
   - **Duas coisas acontecem ao mesmo tempo no mesmo segundo!**

---

## 2. Reconstituição Textual das 5 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: Instanciando `MyRunnable` e Declarando `Thread` (Timestamp: 11:19:15)

O autor digita as linhas 8 e 9 no `Main.java`. A palavra `myRunnable` está selecionada pelo cursor:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:19:15 |
+--------------------------------------------------------------------------------------------------+
|  6          Scanner scanner = new Scanner(System.in);                                            |
|  7                                                                                               |
|  8  👉      MyRunnable [myRunnable] = new MyRunnable();  <-- OBJETO DA TAREFA CRIADO!           |
|  9  👉      Thread thread = new Thread();                                                        |
|                                        | <-- CURSOR DO AUTOR PREPARANDO PARA PASSAR myRunnable   |
| 10                                                                                               |
| 11          System.out.println("You have 5 seconds to enter your name");                         |
| 12          System.out.print("Enter your name: ");                                               |
+--------------------------------------------------------------------------------------------------+
| DETALHE: O autor prepara o construtor da Thread para receber a tarefa myRunnable.                |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: O Comando `thread.start()` e o Console Aberto Imediatamente (Timestamp: 11:19:44)

O autor completa a linha 9 passando `(myRunnable)` e na linha 10 digita `thread.start()`. Ao rodar o programa, veja que o terminal **NÃO CONGELA**; as duas primeiras mensagens aparecem na mesma hora:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject & Console                                     Timestamp 11:19:44 |
+--------------------------------------------------------------------------------------------------+
|  8          MyRunnable myRunnable = new MyRunnable();                                            |
|  9          Thread thread = new Thread(myRunnable);                                              |
| 10  👉      thread.start();                                                                      |
| 11                                                                                               |
| 12          System.out.println("You have 5 seconds to enter your name");                         |
| 13          System.out.print("Enter your name: ");                                               |
|--------------------------------------------------------------------------------------------------|
|  CONSOLE DE EXECUÇÃO:                                                                            |
|  You have 5 seconds to enter your name                                                           |
|  Enter your name: | <-- CURSOR VERDE PISCANDO ESPERANDO DIGITAÇÃO NA MESMA HORA!                 |
+--------------------------------------------------------------------------------------------------+
| A DIFERENÇA HISTÓRICA: O programa NÃO esperou os 5 segundos para pedir o nome!                   |
| O pedido apareceu instantaneamente porque o cronômetro está rodando em SEGUNDO PLANO!           |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: O Teste 1 — O Usuário Lento (Timeout) (Timestamp: 11:19:44+)

O autor não digita nada e fica esperando 5 segundos de propósito. Veja o que acontece no console:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console (Teste 1: Usuário Lento)                             Timestamp 11:19:44 |
+--------------------------------------------------------------------------------------------------+
|  You have 5 seconds to enter your name                                                           |
|  Enter your name: Time's up! | <-- "Time's up!" SURGIU SOZINHO AO LADO DO CURSOR!                |
+--------------------------------------------------------------------------------------------------+
| O QUE ACONTECEU AQUI?                                                                            |
| O usuário não digitou nada. A thread principal continuou esperando no scanner.nextLine().        |
| Mas a Thread secundária, contando 1, 2, 3, 4, 5 em segundo plano, bateu os 5 segundos e mandou    |
| imprimir "Time's up!" bem na tela enquanto o usuário ainda estava olhando para o cursor!          |
| PROVA VIVA DA MULTITHREADING: Duas tarefas acontecendo simultaneamente!                          |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: O Teste 2 — O Usuário Rápido Digitando "Bro" (Timestamp: 11:19:59)

O autor executa o programa novamente, mas desta vez digita `"Bro"` bem rápido (em menos de 2 segundos):

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console (Teste 2: Digitação Rápida)                          Timestamp 11:19:59 |
+--------------------------------------------------------------------------------------------------+
|  You have 5 seconds to enter your name                                                           |
|  Enter your name: Bro                                                                            |
|  Hello Bro                                                                                       |
+--------------------------------------------------------------------------------------------------+
| O usuário digitou dentro do prazo de 5 segundos! A thread principal imprimiu "Hello Bro"!       |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 5: O Cronômetro Tocando Após a Saudação (Timestamp: 11:19:59+)

Mesmo depois que a thread principal imprimiu `Hello Bro`, veja o que aparece no terminal 3 segundos depois:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console (Conclusão do Teste 2)                               Timestamp 11:20:05 |
+--------------------------------------------------------------------------------------------------+
|  You have 5 seconds to enter your name                                                           |
|  Enter your name: Bro                                                                            |
|  Hello Bro                                                                                       |
|  Time's up! | <-- O CRONÔMETRO BATEU OS 5 SEGUNDOS MESMO DEPOIS DE TERMINAR!                     |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| POR QUE O "Time's up!" APARECEU DEPOIS?                                                          |
| Porque a Thread secundária continuou dormindo e contando até 5 em segundo plano!                 |
| Quando ela completou a 5ª volta, imprimiu "Time's up!" e só aí o programa fechou de verdade!   |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Os 3 Passos de Ouro para Disparar uma Thread em Java

Observe a trinca mágica escrita nas linhas 8, 9 e 10 de `Main.java`:

```java
MyRunnable myRunnable = new MyRunnable();  // 1. A Tarefa (O Que Fazer)
Thread thread = new Thread(myRunnable);    // 2. O Trabalhador (Quem Vai Fazer)
thread.start();                            // 3. A Partida (Comece Agora!)
```

### Por Que Usamos `.start()` e NUNCA `.run()`?
Esta é uma das perguntas de pegadinha mais comuns em entrevistas de emprego em Java:
* **Se você chamar `thread.run()`**: O Java vai executar o método `run()` como um método comum **na mesma thread atual (`main`)**! O programa vai congelar por 5 segundos de novo e não haverá paralelismo nenhum!
* **Se você chamar `thread.start()`**: O Java faz uma mágica profunda: ele pede ao Sistema Operacional para criar uma **nova thread nativa**, com seu próprio espaço na memória, e manda essa nova thread rodar o `run()`. A thread principal fica imediatamente livre para seguir em frente!

---

## 4. O Código Completo Atualizado de `Main.java`

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        System.out.println("You have 5 seconds to enter your name");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);

        scanner.close();
    }
}
```

---

## 5. Explicação Linha por Linha de `Main.java` (com Numeração e Saídas de Console)

Vamos explicar linha por linha como um relógio de precisão:

### Linha 1: `import java.util.Scanner;`
* **O que faz**: Traz a ferramenta para escutar o teclado.
* **O que sai no console**: Nada.

### Linha 3: `public class Main {`
* **O que faz**: Abre a classe principal.
* **O que sai no console**: Nada.

### Linha 4: `public static void main(String[] args) {`
* **O que faz**: Inicia a **Thread 1 (Thread Principal / `main`)**.
* **O que sai no console**: Nada.

### Linha 6: `Scanner scanner = new Scanner(System.in);`
* **O que faz**: Prepara a leitura do teclado.
* **O que sai no console**: Nada.

---

### Linha 8: `MyRunnable myRunnable = new MyRunnable();`
* **O que faz**: Instancia a nossa classe que contém a tarefa do cronômetro.
* **O que sai no console**: Nada.

### Linha 9: `Thread thread = new Thread(myRunnable);`
* **O que faz**: Cria o objeto da **Thread 2 (Thread Secundária)** e entrega a tarefa `myRunnable` para ela.
* **O que sai no console**: Nada.

### Linha 10: `thread.start();` (O Apito de Largada!)
* **O que faz**: **Dispara a Thread 2 em segundo plano!**
  * A Thread 2 agora ganha vida própria e começa a executar o laço `for` dentro de `MyRunnable.java` (dormindo 1 segundo a cada volta).
  * A Thread 1 (`main`) NÃO ESPERA A THREAD 2! Ela desce voando para a linha 12 na mesma fração de milissegundo!
* **O que sai no console**: Nada visível neste exato milissegundo.

---

### Linha 12: `System.out.println("You have 5 seconds to enter your name");`
* **O que faz**: A Thread 1 imprime a mensagem avisando dos 5 segundos.
* **O que sai no console**:
  ```text
  You have 5 seconds to enter your name
  ```

### Linha 13: `System.out.print("Enter your name: ");`
* **O que faz**: A Thread 1 imprime o pedido do nome e deixa o cursor piscando ao lado.
* **O que sai no console**:
  ```text
  Enter your name: 
  ```

### Linha 14: `String name = scanner.nextLine();` (A Linha de Espera da Thread 1)
* **O que faz**: A Thread 1 congela esperando o usuário digitar.
  * **ENQUANTO ELA ESPERA, O QUE ESTÁ ACONTECENDO NA THREAD 2?**
  * A Thread 2 está viva em paralelo, contando:
    - Segundo 1: dormindo...
    - Segundo 2: dormindo...
    - Segundo 3: dormindo...
    - Segundo 4: dormindo...
    - Segundo 5: `if(i == 5) System.out.println("Time's up!");`
* **O que sai no console**:
  * **Cenário A (Você não digitou nada em 5 segundos)**: A Thread 2 bate 5 segundos e imprime `Time's up!` na mesma linha do cursor!
  * **Cenário B (Você digitou "Bro" rápido)**: Aparece o texto `Bro` que você digitou.

### Linha 15: `System.out.println("Hello " + name);`
* **O que faz**: Imprime a saudação se você digitou a tempo.
* **O que sai no console**:
  ```text
  Hello Bro
  ```

### Linha 17: `scanner.close();`
* **O que faz**: Fecha o leitor de teclado.
* **O que sai no console**: Nada.

### Linhas 18 e 19: `}` e `}`
* **O que fazem**: Fecham o método e a classe.

---

## 6. A Linha do Tempo Paralela: Segundo a Segundo

Veja o gráfico visual do que acontece em cada segundo de execução:

```text
Tempo       Thread 1 (Main Thread - Balcão)            Thread 2 (Worker Thread - Cronômetro)
----------------------------------------------------------------------------------------------------
0.0s        Inicia thread.start()                      Nasce e entra no run()
0.1s        Imprime: "You have 5 seconds..."           Inicia laço: i = 1 -> Thread.sleep(1000)
0.2s        Imprime: "Enter your name: "               Dormindo no 1º segundo...
0.3s        Trava em scanner.nextLine() (aguardando)   Dormindo...
1.0s        (Ainda aguardando digitação...)            i = 2 -> Dormindo no 2º segundo...
2.0s        (Ainda aguardando digitação...)            i = 3 -> Dormindo no 3º segundo...
3.0s        (Ainda aguardando digitação...)            i = 4 -> Dormindo no 4º segundo...
4.0s        (Ainda aguardando digitação...)            i = 5 -> Dormindo no 5º segundo...
5.0s        (Ainda aguardando digitação...)            Acorda! i == 5! IMPRIME: "Time's up!"
----------------------------------------------------------------------------------------------------
```

---

## 7. Quadro Comparativo dos Dois Testes Reais

| Aspecto | Teste 1: Usuário Lento (Sem Digitar) | Teste 2: Usuário Rápido (Digitou "Bro" em 1s) |
| :--- | :--- | :--- |
| **Entrada** | O usuário não aperta nada no teclado. | O usuário digita `"Bro"` e aperta Enter na hora. |
| **Ordem dos Textos no Terminal** | 1. `You have 5 seconds to enter your name`<br>2. `Enter your name: Time's up!` | 1. `You have 5 seconds to enter your name`<br>2. `Enter your name: Bro`<br>3. `Hello Bro`<br>4. `Time's up!` *(3 segundos depois)* |
| **Quem Acabou Primeiro?** | A **Thread 2** (o cronômetro deu 5s primeiro). | A **Thread 1** (o usuário digitou antes dos 5s). |
| **Status do Programa** | Fica aguardando o usuário apertar Enter para fechar. | Encerra com `Process finished with exit code 0` assim que a Thread 2 termina os 5s. |

---

## 8. A Grande Pergunta para a Próxima Aula!

Você percebeu o que aconteceu no Teste 2?
* O usuário foi rápido! Ele digitou `"Bro"` em apenas 1 segundo!
* O programa respondeu `"Hello Bro"`!
* **MAS O CRONÔMETRO CONTINUOU CORRENDO EM SEGUNDO PLANO!**
* E quando bateu 5 segundos, o cronômetro gritou: `"Time's up!"`, mesmo depois de o usuário já ter terminado com sucesso!

Na próxima aula, o Bro Code vai nos ensinar:
**Como avisar a Thread 2 para CANCELAR o cronômetro e não gritar "Time's up!" quando o usuário digitar a tempo?**
É aí que entram métodos de controle como `.interrupt()`, checagem de status ou variáveis de controle!
