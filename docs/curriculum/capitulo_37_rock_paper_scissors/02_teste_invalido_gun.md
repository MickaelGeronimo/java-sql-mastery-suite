# ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 2)
## O Teste da Trapaça: Digitanto `"gun"` no Console, a Vitória da Lógica Booleana (`true && true && true`) e o Aviso de `"Invalid choice"`!

> **Baseado na aula do canal Bro Code (Minuto `6:05:20` a `6:05:40` de 12:00:00)**  
> **Tema:** O professor realiza o teste definitivo da sua barreira de segurança. Na Parte 1, ele havia digitado uma jogada válida (`paper`) para provar que o programa aceitava o movimento. Agora, ele digita intencionalmente uma jogada clássica de trapaça de recreio escolar: **`"gun"`** *(revólver / arma)*! O Java avalia a expressão lógica inteira como `true`, aciona o bloco `if` e exibe na tela o aviso: **`Invalid choice`**!

---

## 📌 Sumário
1. [Contexto Visual Minucioso da Tela (Para o Google Docs)](#1-contexto-visual-minucioso-da-tela-para-o-google-docs)
2. [Por que o Professor Digitou `"gun"`? (O Contexto Cultural da Trapaça)](#2-por-que-o-professor-digitou-gun-o-contexto-cultural-da-trapaça)
3. [Dissecação Matemática da Lógica Booleana: Por que `"gun"` Dá `true`?](#3-dissecação-matemática-da-lógica-booleana-por-que-gun-dá-true)
4. [Tabela Comparativa Suprema: O Teste de `"paper"` vs. O Teste de `"gun"`](#4-tabela-comparativa-suprema-o-teste-de-paper-vs-o-teste-de-gun)
5. [O Código Java Completo, Numerado e Definitivo](#5-o-código-java-completo-numerado-e-definitivo)
6. [Explicação Cirúrgica Linha por Linha](#6-explicação-cirúrgica-linha-por-linha)
7. [O que Saiu no Console (A Saída Exata da Imagem)](#7-o-que-saiu-no-console-a-saída-exata-da-imagem)
8. [Por que o Programa Encerra Logo Depois?](#8-por-que-o-programa-encerra-logo-depois)
9. [Resumo Prático em Uma Tabela Mental](#9-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Minucioso da Tela (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível na tela da IDE IntelliJ IDEA no print enviado (`media_1791244497611.png`):

* **No editor de código (`Main.java`):**
  * O código permanece idêntico à tela anterior, focado na estrutura de decisão:
    * Linha 20: `if(!playerChoice.equals("rock") &&`
    * Linha 21: `   !playerChoice.equals("paper") &&`
    * Linha 22: `   !playerChoice.equals("scissors")){`
    * Linha 23: `    System.out.println("Invalid choice");`
    * Linha 24: `}`
  * A linha 21 está com a seleção de foco ativa no editor.
* **Na janela de Console (Terminal Inferior):**
  * O prompt fez a pergunta ao jogador:
    ```text
    Enter your move (rock, paper, scissors): gun
    ```
  * O professor digitou a palavra **`gun`** em verde itálico e apertou Enter.
  * Imediatamente abaixo, o console imprimiu em verde a mensagem de erro:
    ```text
    Invalid choice
    ```
  * Logo abaixo, a mensagem padrão de término da JVM:
    ```text
    Process finished with exit code 0
    ```
  * O cursor de texto em barra vertical está piscando sozinho na linha vazia abaixo do encerramento.

---

## 2. Por que o Professor Digitou `"gun"`? (O Contexto Cultural da Trapaça)

Nos Estados Unidos e em várias partes do mundo, existe uma piada infantil clássica na hora de jogar *Rock-Paper-Scissors*:
* Duas crianças vão jogar, e uma delas faz o formato de um revólver com o dedo indicador e o polegar e grita: *"Gun! (Arma!) Arma atira na pedra, rasga o papel e destrói a tesoura, então eu ganhei!"*.
* O professor Bro Code usa essa piada de forma genial na didática:
  * O que acontece se o usuário tentar bancar o espertinho e digitar `"gun"` no terminal do nosso jogo Java?
  * O programa não cai no golpe! Ele reconhece que `"gun"` não é nem pedra, nem papel, nem tesoura, e responde na mesma hora:  
    👉 **`Invalid choice`** *(Escolha Inválida!)*

---

## 3. Dissecação Matemática da Lógica Booleana: Por que `"gun"` Dá `true`?

Vamos acompanhar o raciocínio exato da CPU do computador ao processar a palavra `"gun"`:

```java
if(!playerChoice.equals("rock") &&
   !playerChoice.equals("paper") &&
   !playerChoice.equals("scissors"))
```

### O Passo a Passo da Máquina:

1. **Entrada:** `playerChoice = "gun"`.
2. **Primeiro Teste:** `playerChoice.equals("rock")`  
   * `"gun"` é igual a `"rock"`? **FALSO (False)**.  
   * Agora entra o ponto de exclamação `!` (negação):  
     `!false` $\rightarrow$ vira **`true` (Verdadeiro)**!  
     *(Tradução: "É verdade que gun NÃO É rock!")*.
3. **Segundo Teste:** `playerChoice.equals("paper")`  
   * `"gun"` é igual a `"paper"`? **FALSO (False)**.  
   * Com o ponto de exclamação `!`:  
     `!false` $\rightarrow$ vira **`true` (Verdadeiro)**!  
     *(Tradução: "É verdade que gun NÃO É paper!")*.
4. **Terceiro Teste:** `playerChoice.equals("scissors")`  
   * `"gun"` é igual a `"scissors"`? **FALSO (False)**.  
   * Com o ponto de exclamação `!`:  
     `!false` $\rightarrow$ vira **`true` (Verdadeiro)**!  
     *(Tradução: "É verdade que gun NÃO É scissors!")*.
5. **A Conjunção Final com `&&`:**
   $$\text{true} \ \mathbf{\&\&} \ \text{true} \ \mathbf{\&\&} \ \text{true} \ = \ \mathbf{true}$$
6. **O Desfecho:** Como o resultado final foi **`true` (Verdadeiro)**, a porta do `if` se abre e a linha 23 é executada, imprimindo:  
   `Invalid choice`!

---

## 4. Tabela Comparativa Suprema: O Teste de `"paper"` vs. O Teste de `"gun"`

Compare os dois testes lado a lado para fixar a lógica para sempre na sua cabeça:

| Etapa da Validação | Teste da Parte 1 (`playerChoice = "paper"`) | Teste da Parte 2 (`playerChoice = "gun"`) |
| :--- | :---: | :---: |
| **1. Não é rock?** (`!equals("rock")`) | `true` (paper não é rock) | `true` (gun não é rock) |
| **2. Não é paper?** (`!equals("paper")`) | ❌ **`false`** (é paper sim!) | `true` (gun não é paper) |
| **3. Não é scissors?** (`!equals("scissors")`) | *(nem avalia, curto-circuito)* | `true` (gun não é scissors) |
| **Resultado do `if` com `&&`** | **`false`** (Pois teve um falso no meio) | **`true`** (Todos foram verdadeiros!) |
| **Entra no bloco `if`?** | ❌ Não entra! | ✅ **Entra com certeza!** |
| **O que sai no console?** | *(Nada! Jogada aceita)* | **`Invalid choice`** |

---

## 5. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral, testado e validado em ambos os cenários:

```java
1:  import java.util.Random;
2:  import java.util.Scanner;
3:  
4:  public class Main {
5:      public static void main(String[] args){
6:  
7:          // ROCK PAPER SCISSORS GAME
8:  
9:          Scanner scanner = new Scanner(System.in);
10:         Random random = new Random();
11: 
12:         String[] choices = {"rock", "paper", "scissors"};
13:         String playerChoice;
14:         String computerChoice;
15:         String playAgain = "yes";
16: 
17:         System.out.print("Enter your move (rock, paper, scissors): ");
18:         playerChoice = scanner.nextLine().toLowerCase();
19: 
20:         if(!playerChoice.equals("rock") &&
21:            !playerChoice.equals("paper") &&
22:            !playerChoice.equals("scissors")){
23:             System.out.println("Invalid choice");
24:         }
25: 
26:         // GET RANDOM CHOICE FOR THE COMPUTER
27:         // CHECK WIN CONDITIONS
28:         // ASK TO PLAY AGAIN?
29:         // GOODBYE MESSAGE
30: 
31:         scanner.close();
32:     }
33: }
```

---

## 6. Explicação Cirúrgica Linha por Linha

---

### Linha 17: O Prompt do Jogo
```java
17:         System.out.print("Enter your move (rock, paper, scissors): ");
```
* Imprime o texto na tela e deixa o cursor na mesma linha.
* **Saída no console:** `Enter your move (rock, paper, scissors): `

---

### Linha 18: A Leitura e Tratamento do Texto
```java
18:         playerChoice = scanner.nextLine().toLowerCase();
```
* O usuário digita `gun` e aperta ENTER.
* O Java pega a String `"gun"`, aplica `.toLowerCase()` (que continua `"gun"`) e guarda na variável `playerChoice`.

---

### Linhas 20 a 22: O Filtro de Segurança
```java
20:         if(!playerChoice.equals("rock") &&
21:            !playerChoice.equals("paper") &&
22:            !playerChoice.equals("scissors")){
```
* Avalia se a entrada é diferente de todas as três palavras oficiais.
* Como `"gun"` é diferente das 3, a condição inteira é avaliada como verdadeira.

---

### Linha 23: O Aviso de Escolha Inválida
```java
23:             System.out.println("Invalid choice");
```
* **O que faz:** Imprime o alerta de que o movimento não existe nas regras do jogo.
* **Saída no console:**
  ```text
  Invalid choice
  ```

---

### Linhas 31 a 33: O Fim do Programa
```java
31:         scanner.close();
32:     }
33: }
```
* Fecha o `scanner` e encerra a execução com `exit code 0`.

---

## 7. O que Saiu no Console (A Saída Exata da Imagem)

A saída real impressa na janela do terminal na imagem enviada é:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA..."
Enter your move (rock, paper, scissors): gun
Invalid choice

Process finished with exit code 0
```

---

## 8. Por que o Programa Encerra Logo Depois?

Você deve ter notado que, após imprimir `Invalid choice`, o programa encerrou (`Process finished with exit code 0`) em vez de pedir para o usuário digitar de novo.

### Por que isso aconteceu?
* Porque nós ainda **NÃO colocamos essa pergunta dentro de um laço `while`**!
* Neste momento da aula, o professor está testando os blocos de forma linear e isolada.
* Mais adiante no vídeo, ele colocará essa pergunta dentro de uma estrutura de repetição para que, caso o usuário digite `"gun"` ou qualquer bobagem, o jogo diga `"Invalid choice"` e **repita a pergunta** até ele digitar uma jogada correta!

---

## 9. Resumo Prático em Uma Tabela Mental

| Jogada Digitada | Passa no Filtro? | O que o Java Diz? | O que Acontece a Seguir? |
| :---: | :---: | :---: | :--- |
| **`"rock"`** | ✅ Sim | Silêncio (Não entra no `if`) | Segue para o sorteio do computador. |
| **`"paper"`** | ✅ Sim | Silêncio (Não entra no `if`) | Segue para o sorteio do computador. |
| **`"scissors"`** | ✅ Sim | Silêncio (Não entra no `if`) | Segue para o sorteio do computador. |
| **`"gun"`** | ❌ **Não!** | **`Invalid choice`** | Entra no `if` e avisa que é trapaça/inválido! |
| **`"banana"`** | ❌ **Não!** | **`Invalid choice`** | Entra no `if` e avisa que é inválido! |
| **`"ROCK"`** | ✅ Sim | Silêncio (O `.toLowerCase()` converteu!) | Segue para o sorteio do computador. |
