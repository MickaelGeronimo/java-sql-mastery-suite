# 🔂 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 23: Laços FOR (`for loop`) — Parte 4: Contagem Regressiva de Ano Novo com `Thread.sleep` e `Scanner`
**Timestamps do vídeo:** `3:50:49` até `3:52:58 / 12:00:00`  
**Tema:** Criando um Temporizador Real de Réveillon, Pausa de 1 Segundo com `Thread.sleep(1000)`, O que é `throws InterruptedException`, Mensagem fora do laço (`HAPPY NEW YEAR!`), e Contagem Dinâmica com `Scanner`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela durante estes minutos:

1. **Imagem 1 (Minuto 3:50:49):**
   * O professor quer criar uma contagem regressiva de Réveillon (Ano Novo).
   * Ele cria a variável `int start = 10;`.
   * Monta o `for`: `for(int i = start; i > 0; i--){ System.out.println(i); }`.
   * Logo **após** o fechamento do `for`, na linha 12, ele coloca:
     `System.out.println("HAPPY NEW YEAR!");`  
     *(Feliz Ano Novo!)*.
   * *O problema:* Ao rodar, o computador é rápido demais e cospe todos os números de 10 a 1 e o "HAPPY NEW YEAR!" em 1 milissegundo, tudo de uma vez só! Não tem emoção nenhuma de Réveillon.
2. **Imagem 2 (Minuto 3:51:44):**
   * Para consertar isso e fazer o relógio esperar 1 segundo de verdade entre cada número, ele adiciona dentro do laço:
     `Thread.sleep(1000);`  
     *(Faça o Java dormir/pausar por 1.000 milissegundos = 1 segundo).*
   * Ao fazer isso, o IntelliJ grifa a linha de vermelho acusando uma possível exceção.
   * O professor adiciona ao lado do método `main` o texto:
     `throws InterruptedException`
   * Aparece uma legenda explicativa na tela do vídeo:
     *"This exception typically occurs when a thread running the method is interrupted while it is sleeping, waiting, or otherwise paused in its execution."*  
     *(Esta exceção normalmente ocorre quando a linha de execução do método é interrompida enquanto está dormindo, esperando ou pausada).*
3. **Imagem 3 (Minuto 3:52:11):**
   * O professor roda o código! Agora o console ganha vida:
   * A cada 1 segundo exato, surge um número: `10`... (espera 1s) `9`... (espera 1s) `8`... até chegar no `1`.
   * Quando o tempo zera, explode na tela:
     `HAPPY NEW YEAR!`
4. **Imagem 4 (Minuto 3:52:58):**
   * O professor junta o `Scanner` para deixar o usuário escolher quantos segundos quer na contagem!
   * No código:
     `System.out.println("How many seconds to countdown from?: ");`  
     `int start = scanner.nextInt();`
   * No console, o usuário digita **`20`**.
   * O console começa a descer em tempo real, segundo a segundo: `20`... `19`... `18`... `17`... (o print foi tirado enquanto o relógio estava descendo!).

---

## 🧠 Por que precisamos de `Thread.sleep`? (A velocidade dos computadores)

Um computador moderno consegue fazer **bilhões** de contas por segundo.  
Se você pedir para ele contar de 10 até 1 com um `for` comum, ele vai terminar a contagem em menos de **0,0001 segundos**! Para os nossos olhos humanos, parecerá que todos os 10 números piscaram na tela exatamente no mesmo instante.

Para transformar isso em um **relógio de verdade**, precisamos dar uma ordem expressa de lentidão:
> *"Ei, Java! Depois de imprimir cada número, vá tirar um cochilo de 1 segundo antes de continuar!"*

É exatamente isso que o comando **`Thread.sleep(1000);`** faz:
* `Thread` = É a "linha de execução" ou o "trabalhador" que roda o seu programa.
* `.sleep()` = O comando de dormir / congelar / esperar.
* `(1000)` = O tempo em **milissegundos** (1 segundo = 1.000 milissegundos). Se você quisesse esperar 2 segundos, colocaria `2000`. Se quisesse meio segundo, colocaria `500`.

---

## 😱 O que é esse tal de `throws InterruptedException`? (Sem pânico!)

Quando você digita `Thread.sleep(1000);`, o IntelliJ IDEA fica desesperado e coloca uma linha vermelha embaixo do código. Por quê?

### A metáfora simples:
Imagine que você vai dormir à noite. Existe a chance de alguém tocar a campainha da sua casa ou jogar um balde de água fria em você enquanto você dorme para te acordar no susto? Existe! Isso se chama **interrupção**.

O Java é super precavido. Ele pensa:  
*"E se o sistema operacional ou outro programa me cutucar e mandar eu acordar antes de bater os 1.000 milissegundos?"*  
Isso geraria um erro chamado **`InterruptedException`** (Exceção de Interrupção).

Para o Java parar de reclamar e deixar o programa rodar, nós assinamos um "termo de responsabilidade" no cabeçalho do `main`:
```java
public static void main(String[] args) throws InterruptedException {
```
Em português claro, você está dizendo para o compilador do Java:  
*"Java, eu sei que o programa vai dormir. Se alguém acordar ele no susto, não entre em pânico, passe a culpa adiante (`throws`). Pode compilar tranquilo!"*.

---

## 🎆 Por que o `HAPPY NEW YEAR!` fica FORA do laço?

Observe a posição das chaves `{ ... }`:

```java
for(int i = start; i > 0; i--){
    System.out.println(i);
    Thread.sleep(1000);
} // <--- O LAÇO ACABOU AQUI!

System.out.println("HAPPY NEW YEAR!"); // <--- ESTÁ DO LADO DE FORA!
```

* Se você colocasse o `"HAPPY NEW YEAR!"` **dentro** das chaves, o que aconteceria? Ele imprimiria:  
  `10` -> `HAPPY NEW YEAR!` -> `9` -> `HAPPY NEW YEAR!` -> `8` -> `HAPPY NEW YEAR!` (um desastre!).
* Como ele está **depois que o laço fecha**, o Java faz toda a contagem regressiva de 10 a 1, espera os 10 segundos no total e, **somente quando o laço termina**, ele avança para a linha seguinte e solta os fogos: `"HAPPY NEW YEAR!"`.

---

## 💻 Código Completo e Atualizado (`Main.java`)

Este é o código da **Imagem 4**, a versão final completa com `Scanner`, `Thread.sleep` e a contagem dinâmica:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("How many seconds to countdown from?: ");
        int start = scanner.nextInt();

        for(int i = start; i > 0; i--){
            System.out.println(i);
            Thread.sleep(1000);
        }

        System.out.println("HAPPY NEW YEAR!");

        scanner.close();
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa o leitor de teclado.
* **Console:** **Nada**.

---

### **Linha 2:** *(Linha em branco para organização)*

---

### **Linha 3:** `public class Main {`
* **O que faz:** Declara a classe principal onde o projeto reside.
* **Console:** **Nada**.

---

### **Linha 4:** `public static void main(String[] args) throws InterruptedException {`
* **O que faz:** Ponto de partida do programa com a autorização de sono (`throws InterruptedException`).
* **Console:** **Nada**.

---

### **Linha 5:** *(Linha em branco)*

---

### **Linha 6:** `Scanner scanner = new Scanner(System.in);`
* **O que faz:** Constrói o leitor conectado ao teclado.
* **Console:** **Nada**.

---

### **Linha 7:** *(Linha em branco)*

---

### **Linha 8:** `System.out.print("How many seconds to countdown from?: ");`
* **O que faz:** Pergunta na tela de quantos segundos a pessoa quer iniciar a contagem. Usa `print` para o cursor ficar na mesma linha.
* **Console:** Imprime:
  ```text
  How many seconds to countdown from?: 
  ```

---

### **Linha 9:** `int start = scanner.nextInt();`
* **O que faz:** O programa pausa e espera você digitar um número inteiro e dar Enter. Guarda o valor digitado na variável `start`.
* **Console:** O número digitado aparece ao lado da pergunta (exemplo: `20` ou `10`).

---

### **Linha 10:** *(Linha em branco)*

---

### **Linha 11:** `for(int i = start; i > 0; i--){`
* **O que faz:**
  * `int i = start;` -> O contador `i` começa exatamente no número que você digitou (se digitou 10, começa no 10).
  * `i > 0;` -> Continua enquanto o número for positivo (maior que zero).
  * `i--` -> Diminui 1 segundo a cada volta.
* **Console:** **Nada nessa linha.**

---

### **Linha 12:** `System.out.println(i);`
* **O que faz:** Imprime o número atual da contagem regressiva e pula de linha.
* **Console:** Imprime o segundo atual (ex: `10`, depois `9`, etc.).

---

### **Linha 13:** `Thread.sleep(1000);`
* **O que faz:** Congela o Java por 1.000 milissegundos (exatamente 1 segundo de relógio).
* **Console:** Não escreve nada, mas faz o console **esperar 1 segundo** antes de ir para o próximo número! É o que cria a animação de relógio.

---

### **Linha 14:** `}`
* **O que faz:** Fim da volta do `for`. Executa o `i--` (tira 1) e volta para testar se `i > 0`.
* **Console:** **Nada**.

---

### **Linha 15:** *(Linha em branco)*

---

### **Linha 16:** `System.out.println("HAPPY NEW YEAR!");`
* **O que faz:** É executado **apenas quando o laço termina por completo** (quando o tempo zera!).
* **Console:** Imprime a comemoração:
  ```text
  HAPPY NEW YEAR!
  ```

---

### **Linha 17:** *(Linha em branco)*

---

### **Linha 18:** `scanner.close();`
* **O que faz:** Desliga o leitor de teclado para economizar memória do computador.
* **Console:** **Nada**.

---

### **Linha 19:** `}`
* **O que faz:** Fecha o método `main`.
* **Console:** Mostra `Process finished with exit code 0`.

---

### **Linha 20:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## ⏱️ Simulação do Console Segundo a Segundo (Com `start = 10`)

Veja o que acontece na tela com o passar do tempo real:

* **Segundo 0:** Usuário digita `10` e aperta Enter.
* **Segundo 1:** Aparece `10`
* **Segundo 2:** Aparece `9`
* **Segundo 3:** Aparece `8`
* **Segundo 4:** Aparece `7`
* **Segundo 5:** Aparece `6`
* **Segundo 6:** Aparece `5`
* **Segundo 7:** Aparece `4`
* **Segundo 8:** Aparece `3`
* **Segundo 9:** Aparece `2`
* **Segundo 10:** Aparece `1`
* **Segundo 11:** Aparece:
  ```text
  HAPPY NEW YEAR!
  ```
* O programa encerra!
