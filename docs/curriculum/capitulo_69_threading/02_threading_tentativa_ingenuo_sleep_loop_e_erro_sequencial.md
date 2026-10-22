# Curso de Java — Bro Code (12 Horas)
## Capítulo 69: `threading 🧵` (Parte 2 — A Tentativa Ingênua do Cronômetro com `Thread.sleep()` e a Armadilha Sequencial)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa e minuciosa das **4 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:15:13** e **11:16:57** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: os códigos de `Main.java`, a janela flutuante de erro de `Unhandled exception: java.lang.InterruptedException`, a contagem congelada no terminal e o código completo com o loop `for` foram reconstituídos em formato de texto para leitura independente das imagens originais.

---

## 1. O Que o Autor Fez Aqui? (História e Analogia para Iniciantes)

Na aula anterior (Parte 1), nosso programa dizia: *"Você tem 5 segundos para digitar seu nome"*, mas ficava esperando a vida inteira sem nenhum cronômetro de verdade.

Aí o aluno iniciante pensa:
> *"Fácil! Se eu quero dar 5 segundos, basta colocar um loop contando de 1 até 5 e mandar o computador esperar 1 segundo em cada volta usando `Thread.sleep(1000)`!"*

O Bro Code faz exatamente isso para mostrar a **maior armadilha da programação sequencial**:

### A Analogia da Prova Escolar Maluca
Imagine que você entra na sala para fazer uma prova e o professor diz:
1. *"Atenção: você tem 5 segundos para assinar seu nome na prova!"*
2. Em seguida, em vez de te dar a caneta, o professor **segura seus dois braços** e fica olhando para o relógio da parede em silêncio por 5 segundos: *1... 2... 3... 4... 5...*
3. Quando o ponteiro bate 5 segundos, ele grita: **"O TEMPO ACABOU!"**
4. E só depois de gritar que o tempo acabou é que ele te entrega a caneta e diz: *"Pronto, agora digite seu nome!"*
5. Você diz: *"Mas professor, você só me deu a caneta DEPOIS que o tempo acabou!"*

**É EXATAMENTE ISSO QUE ESSE CÓDIGO FEZ!**
Ele congelou o computador por 5 segundos, gritou `"Time's up!"` (O tempo acabou!) e só depois foi pedir para o usuário digitar o nome!

---

## 2. Reconstituição Textual das 4 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: O Console com o Problema Anterior (Timestamp: 11:15:13)

O autor observa o console da aula passada, onde o programa ficava parado esperando o usuário sem limite de tempo:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Run > Main)                             Timestamp 11:15:13 |
+--------------------------------------------------------------------------------------------------+
|  You have 5 seconds to enter your name                                                           |
|  Enter your name: Bro                                                                            |
|  Hello Bro                                                                                       |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O autor reflete: "Precisamos de um cronômetro de verdade contando os 5 segundos!"                |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: O Pop-up de Erro `Unhandled exception: InterruptedException` (Timestamp: 11:16:17)

O autor escreve o loop `for` e chama `Thread.sleep(1000);`. O IntelliJ IDEA abre uma janela flutuante escura avisando que o Java **proíbe** chamar `sleep` sem proteção:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:16:17 |
+--------------------------------------------------------------------------------------------------+
| 10          for(int i = 1; i <= 5; i++){                                                         |
| 11                                                                                               |
| 12              try{                                                                             |
| 13  🚨              Thread.sleep(1000);                                                          |
|                      ~~~~~~~~~~~~~~~~~~                                                          |
|             +-----------------------------------------------------------------------+            |
|             | Unhandled exception: java.lang.InterruptedException                   |            |
|             | Add exception to method signature  |  More actions...                 |            |
|             |-----------------------------------------------------------------------|            |
|             | public static void sleep(long millis) throws InterruptedException     |            |
|             | Causes the currently executing thread to sleep (temporarily cease     |            |
|             | execution) for the specified number of milliseconds...                |            |
|             +-----------------------------------------------------------------------+            |
| 14              }                                                                                |
| 15              catch(){                                                                         |
| 16              }                                                                                |
| 17          }                                                                                    |
+--------------------------------------------------------------------------------------------------+
| AVISO DO JAVA: "Dormir é perigoso! Alguém pode te acordar no meio do sono com uma interrupção!   |
| Você é OBRIGADO a colocar um catch(InterruptedException e)!"                                     |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: O Terminal Congelado por 5 Segundos (Timestamp: 11:16:57)

O autor roda o programa. O terminal imprime a primeira linha e fica **totalmente congelado** por 5 segundos antes de qualquer outra coisa aparecer:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Run > Main)                             Timestamp 11:16:57 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ...   |
|                                                                                                  |
|  You have 5 seconds to enter your name                                                           |
|                                                                                                  |
|  [... 5 SEGUNDOS DE SILÊNCIO TOTAL E CONGELAMENTO ...]                                           |
|                                                                                                  |
+--------------------------------------------------------------------------------------------------+
| O usuário quer digitar o nome, mas a tela está paralisada! O programa não pediu o nome ainda!    |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: O Código Completo da Tentativa com `Time's up!` (Timestamp: 11:16:53)

O autor conclui a escrita do código no editor com o `if(i == 5)` imprimindo que o tempo acabou:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:16:53 |
+--------------------------------------------------------------------------------------------------+
|  8  💡      System.out.println("You have 5 seconds to enter your name");                         |
|  9                                                                                               |
| 10          for(int i = 1; i <= 5; i++){                                                         |
| 11                                                                                               |
| 12              try{                                                                             |
| 13                  Thread.sleep(1000);                                                          |
| 14              }                                                                                |
| 15              catch(InterruptedException e){                                                   |
| 16                  System.out.println("Thread was interrupted");                                |
| 17              }                                                                                |
| 18                                                                                               |
| 19              if(i == 5){                                                                      |
| 20                  System.out.println("Time's up!");                                            |
| 21              }                                                                                |
| 22          }                                                                                    |
| 23                                                                                               |
| 24          System.out.print("Enter your name: ");                                               |
| 25          String name = scanner.nextLine();                                                    |
| 26          System.out.println("Hello " + name);                                                 |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Dissecando o Método `Thread.sleep(1000)` e a `InterruptedException`

Vamos entender a fundo as duas ferramentas que o autor usou no código:

### 1. O Que Faz `Thread.sleep(1000)`?
* **O que é**: É um método estático embutido na classe `Thread` do próprio Java.
* **O parâmetro `1000`**: O tempo em Java é medido em **milissegundos** (milésimos de segundo).
  * $1000\text{ milissegundos} = 1\text{ segundo}$.
  * $500\text{ milissegundos} = \text{meio segundo}$.
  * $5000\text{ milissegundos} = 5\text{ segundos}$.
* **O que ele faz com o computador**: Ele manda a thread que o chamou (neste caso, a thread `main`) deitar a cabeça no travesseiro e **dormir por 1 segundo inteiro**. Durante esse 1 segundo, essa thread não executa nenhuma linha de código e consome zero processamento da CPU.

---

### 2. Por Que o Java Exigiu `try / catch` Obrigatoriamente?
Repare na Tela 2 que o IntelliJ não deixou compilar `Thread.sleep(1000)` solto.
Isso acontece porque `InterruptedException` é uma **Exceção Checada** (*Checked Exception*):
* **O que é uma Exceção Checada?** É um erro que o compilador do Java confere antes de rodar o programa. O Java te diz: *"Eu sei que enquanto uma thread está dormindo, outra thread pode tentar acordá-la à força (chamar `.interrupt()`). Se isso acontecer, vai dar um erro chamado `InterruptedException`. Por isso, você é obrigado a colocar um `try/catch` aqui para se precaver!"*
* Se você não colocar o `try/catch`, o programa **nem compila**!

---

## 4. O Código Completo Atualizado (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("You have 5 seconds to enter your name");

        for(int i = 1; i <= 5; i++){

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("Thread was interrupted");
            }

            if(i == 5){
                System.out.println("Time's up!");
            }
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);

        scanner.close();
    }
}
```

---

## 5. Explicação Linha por Linha de Todo o Código

Vamos analisar com extrema calma todas as linhas deste programa:

### Linha 1: `import java.util.Scanner;`
* **O que faz**: Importa o leitor de teclado da biblioteca padrão do Java.
* **O que sai no console**: Nada.

### Linha 3: `public class Main {`
* **O que faz**: Declara a classe principal onde mora todo o nosso código.
* **O que sai no console**: Nada.

### Linha 4: `public static void main(String[] args) {`
* **O que faz**: Cria e inicia a **Thread Principal** (`main`). Lembre-se: por enquanto, ela é o **único cozinheiro** trabalhando na cozinha!
* **O que sai no console**: Nada.

### Linha 6: `Scanner scanner = new Scanner(System.in);`
* **O que faz**: Prepara a ferramenta que escuta as teclas digitadas no teclado (`System.in`).
* **O que sai no console**: Nada.

### Linha 8: `System.out.println("You have 5 seconds to enter your name");`
* **O que faz**: Imprime a frase de abertura avisando que o usuário tem 5 segundos.
* **O que sai no console**:
  ```text
  You have 5 seconds to enter your name
  ```

---

### Linha 10: `for(int i = 1; i <= 5; i++){`
* **O que faz**: Cria um laço de repetição que vai rodar **5 vezes** (com `i` valendo 1, depois 2, depois 3, depois 4, depois 5).
* **O que sai no console**: Nada de imediato.

### Linha 12: `try{`
* **O que faz**: Abre o escudo de proteção obrigatório exigido pelo `Thread.sleep()`.
* **O que sai no console**: Nada.

### Linha 13: `Thread.sleep(1000);`
* **O que faz**: **Manda a thread `main` dormir por 1000 milissegundos (1 segundo exato).**
  * Como o laço roda 5 vezes, a thread dorme 1 segundo na primeira volta, 1 segundo na segunda... totalizando **5 segundos inteiros de sono**!
  * **O efeito colateral**: Enquanto ela dorme, ela NÃO EXECUTA NADA. O programa fica totalmente paralisado!
* **O que sai no console**: Nada (apenas silêncio e espera de 1 segundo a cada volta).

### Linhas 15 a 17: O Bloco Catch
```java
catch(InterruptedException e){
    System.out.println("Thread was interrupted");
}
```
* **O que faz**: Se alguém interromper o sono da thread, imprime `"Thread was interrupted"`. Em execuções normais, ninguém interrompe, então esse bloco é ignorado.
* **O que sai no console**: Nada em condições normais.

### Linhas 19 a 21: O Teste do Fim do Tempo
```java
if(i == 5){
    System.out.println("Time's up!");
}
```
* **O que faz**: Quando o contador `i` chegar em 5 (ou seja, depois que os 5 segundos de sono já se passaram completamente), imprime que o tempo esgotou!
* **O que sai no console** (após os 5 segundos de sono):
  ```text
  Time's up!
  ```

### Linha 22: `}` (Fim do Laço `for`)
* **O que faz**: Fecha o laço de repetição. Os 5 segundos de sono terminaram!

---

### Linha 24: `System.out.print("Enter your name: ");`
* **O que faz**: **OLHA O ABSURDO AQUI!**
  * Só agora, **DEPOIS que o tempo já acabou e já apareceu "Time's up!"**, é que o Java chega nesta linha e imprime na tela o pedido para digitar o nome!
* **O que sai no console**:
  ```text
  Enter your name: 
  ```

### Linha 25: `String name = scanner.nextLine();`
* **O que faz**: Congela o programa esperando o usuário digitar o nome e teclar Enter.
* **O que sai no console**: As letras digitadas pelo usuário (no teste do autor: `Bro`).

### Linha 26: `System.out.println("Hello " + name);`
* **O que faz**: Imprime a saudação com o nome digitado.
* **O que sai no console**:
  ```text
  Hello Bro
  ```

### Linha 28: `scanner.close();`
* **O que faz**: Desliga o leitor de teclado.
* **O que sai no console**: Nada.

### Linhas 29 e 30: `}` e `}`
* **O que fazem**: Fecham o método `main` e a classe `Main`.
* **O que sai no console**: `Process finished with exit code 0`.

---

## 6. O Resultado no Console e o Diagnóstico do Fracasso

Veja exatamente como o terminal se comporta do início ao fim:

```text
====================================================================================================
SAÍDA REAL NO TERMINAL                         | O QUE ESTÁ ACONTECENDO NOS BASTIDORES
====================================================================================================
You have 5 seconds to enter your name          | Linha 8 executou.
                                               | 
[... O usuário espera 5 segundos ...]          | O laço for está dormindo (Thread.sleep).
                                               | Nenhuma tecla digitada é aceita agora!
                                               | 
Time's up!                                     | Linha 20 executou. O tempo acabou!
Enter your name: Bro                           | SÓ AGORA a linha 24 e 25 pedem o nome!
Hello Bro                                      | Linha 26 responde educadamente.
====================================================================================================
```

### Por Que a Execução Linear / Sequencial Falhou?
Em programas de **uma única thread**, o computador só sabe ler o código de cima para baixo, linha após linha:

$$\text{Linha 8} \longrightarrow \text{Loop com Sleep (5 segundos)} \longrightarrow \text{Time's up!} \longrightarrow \text{Pedir Nome}$$

É **impossível** que a linha 13 (`sleep`) e a linha 25 (`scanner.nextLine()`) rodem ao mesmo tempo se houver apenas um fio de execução!
* Ou o computador dorme;
* Ou o computador escuta o teclado.
Ele não consegue fazer os dois juntos com um único cozinheiro!

---

## 7. O Que Faremos na Próxima Aula? (O Salto para Multi-Threading)

Para resolver esse problema de verdade, o Bro Code vai nos ensinar a criar uma **SEGUNDA THREAD**:

1. **A Thread 1 (Principal)**: Vai direto para o `scanner.nextLine()` pedir o nome para o usuário.
2. **A Thread 2 (Auxiliar / Cronômetro)**: Vai nascer no mesmo instante e rodar o laço `for` com `Thread.sleep(1000)` em **segundo plano**!
3. Se o usuário digitar antes dos 5 segundos, ele ganha! Se o cronômetro bater 5 segundos primeiro, a Thread 2 interrompe e avisa que o tempo esgotou!

Isso é o verdadeiro poder do **Multi-Threading em Java**!
