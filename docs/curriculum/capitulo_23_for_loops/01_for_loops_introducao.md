# 🔂 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 23: Laços de Repetição FOR (`for loop`) — Parte 1
**Timestamps do vídeo:** `3:45:19` até `3:46:30 / 12:00:00`  
**Tema:** O que é o laço `for`, as 3 engrenagens do cabeçalho `(início; condição; passo)`, por que usamos a variável `i`, e como contar de 0 a 9 e de 1 a 10.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** mostrou na tela durante estes minutos:

1. **Imagem 1 (Minuto 3:45:19):**
   * O professor começa um novo capítulo: **`23.for loops >`**.
   * Ele digita um comentário explicando o conceito:
     `// for loop = execute some code a CERTAIN amount of times`  
     *(Laço for = executa algum código uma quantidade DETERMINADA / CERTA de vezes).*
   * Ele escreve o primeiro laço `for` da aula:
     ```java
     for(int i = 0; i < 10; i++){
         System.out.println("pizza");
     }
     ```
2. **Imagem 2 (Minuto 3:45:29):**
   * O professor clica no Play para rodar o código.
   * O console na parte de baixo é aberto e imprime a palavra **`pizza`** exatamente 10 vezes, uma embaixo da outra!
3. **Imagem 3 (Minuto 3:46:00):**
   * O professor faz uma pequena modificação na linha 8:
     Em vez de imprimir a palavra fixa `"pizza"`, ele troca para imprimir o próprio contador:
     ```java
     System.out.println(i);
     ```
   * Ao rodar, o console mostra a contagem:
     `0, 1, 2, 3, 4, 5, 6, 7, 8, 9` (10 números no total, começando do zero).
4. **Imagem 4 (Minuto 3:46:30):**
   * O professor ajusta a contagem para ficar do jeito humano tradicional (de 1 até 10):
     Ele muda o início para `int i = 1;` e a condição para `i <= 10;`:
     ```java
     for(int i = 1; i <= 10; i++){
         System.out.println(i);
     }
     ```
   * Ao rodar, o console imprime perfeitamente:
     `1, 2, 3, 4, 5, 6, 7, 8, 9, 10`.

---

## 🧠 O que é um `for loop`? (A analogia mais fácil do mundo)

Até agora, aprendemos o laço `while`. Mas qual a diferença entre `while` e `for`?

* **`while` (Enquanto):** Usamos quando **NÃO sabemos** quantas vezes o laço vai rodar.  
  *Exemplo da vida real:* "Continue jogando basquete **enquanto** não escurecer". Pode levar 10 minutos ou 3 horas, você não sabe de antemão.
* **`for` (Para / Durante):** Usamos quando **SABEMOS EXATAMENTE** quantas vezes queremos repetir algo.  
  *Exemplo da vida real:* "Dê exatamente **10 voltas** na pista de corrida". Você sabe o início (volta 1), sabe o fim (volta 10) e sabe que conta de 1 em 1 volta.

O comentário do professor resume isso perfeitamente:
> `for loop = execute some code a CERTAIN amount of times`  
> *(Executa um código uma quantidade CERTA e pré-determinada de vezes).*

---

## ⚙️ A Anatomia Secreta do `for`: As 3 Engrenagens

Olhe para o cabeçalho do `for`:

$$\text{for}(\underbrace{\text{int i = 0}}_{\text{1. Início}};\ \underbrace{\text{i < 10}}_{\text{2. Condição de parada}};\ \underbrace{\text{i++}}_{\text{3. Passo / Atualização}})$$

Dentro dos parênteses do `for`, existem **três partes separadas por ponto e vírgula (`;`)**:

### 1. Primeira engrenagem: A Inicialização (`int i = 0;`)
* É o **ponto de partida**.
* Cria uma variável chamada `i` e dá o valor inicial a ela (ex: `0` ou `1`).
* **Regra de ouro:** Esta parte é executada **apenas uma única vez**, logo no milissegundo em que o laço começa. Depois disso, o Java nunca mais volta nessa primeira parte!
* *Por que a letra `i`?* Em programação, `i` é a abreviação mundial para **Index** (Índice) ou **Iterator** (Contador de iterações/voltas). Você pode dar outro nome se quiser (como `contador`), mas 99,9% dos programadores usam `i`.

### 2. Segunda engrenagem: A Condição (`i < 10;`)
* É a **pergunta de segurança** feita antes de cada volta: *"O `i` ainda é menor que 10?"*.
* Se a resposta for **SIM (`true`)**: o Java entra nas chaves `{ ... }` e executa o código.
* Se a resposta for **NÃO (`false`)**: o laço é cancelado imediatamente e o programa segue em frente.

### 3. Terceira engrenagem: O Incremento / Passo (`i++`)
* É o que acontece **logo após terminar cada volta**.
* `i++` é o atalho mágico para `i = i + 1;` (soma 1 na variável `i`).
* Depois de somar 1, o Java volta para a engrenagem 2 (a condição) para ver se pode dar mais uma volta.

---

## 💻 Versão 1: Imprimindo "pizza" 10 Vezes (Imagens 1 e 2)

### Código:
```java
public class Main {
    public static void main(String[] args){

        // for loop = execute some code a CERTAIN amount of times

        for(int i = 0; i < 10; i++){
            System.out.println("pizza");
        }

    }
}
```

### 🔍 Enumeração Linha por Linha (Versão 1):

* **Linha 1:** `public class Main {`  
  * **O que faz:** Cria a classe principal `Main`.  
  * **Console:** Nada.
* **Linha 2:** `public static void main(String[] args){`  
  * **O que faz:** Ponto de partida onde o Java começa a rodar.  
  * **Console:** Nada.
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** `// for loop = execute some code a CERTAIN amount of times`  
  * **O que faz:** Linha de comentário para o programador. O computador ignora completamente.  
  * **Console:** Nada.
* **Linha 5:** *(Linha em branco)*
* **Linha 6:** `for(int i = 0; i < 10; i++){`  
  * **O que faz:** Configura o laço: começa com `i = 0`, repete enquanto `i < 10`, e soma 1 em `i` a cada rodada.  
  * **Console:** Nada.
* **Linha 7:** `System.out.println("pizza");`  
  * **O que faz:** Imprime a palavra `pizza` no console e pula para a linha seguinte.  
  * **Console:** A cada volta do laço, imprime uma linha com `pizza`.
* **Linha 8:** `}`  
  * **O que faz:** Fim da volta do laço. O Java executa `i++`, incrementa `i` e volta para o teste `i < 10`.  
  * **Console:** Nada.
* **Linha 9:** *(Linha em branco)*
* **Linha 10:** `}`  
  * **O que faz:** Fecha o método `main`.  
  * **Console:** O programa finaliza com sucesso.
* **Linha 11:** `}`  
  * **O que faz:** Fecha a classe `Main`.  
  * **Console:** Nada.

### 📺 O que sai no Console na Versão 1:
```text
pizza
pizza
pizza
pizza
pizza
pizza
pizza
pizza
pizza
pizza
```
*(A palavra pizza impressa exatamente 10 vezes!)*

---

## 💻 Versão 2: Imprimindo o Contador de 0 a 9 (Imagem 3)

Na Imagem 3, o professor substitui `"pizza"` por `i`:

```java
public class Main {
    public static void main(String[] args){

        for(int i = 0; i < 10; i++){
            System.out.println(i);
        }

    }
}
```

### 🔍 Por que saem os números de 0 a 9?

Veja o filme do que acontece na memória do Java em câmera lenta:

| Volta | Valor de `i` | Teste (`i < 10`) | O que o `System.out.println(i)` imprime? | O que o `i++` faz no final da volta? |
| :---: | :---: | :---: | :---: | :---: |
| **1ª** | `0` | `0 < 10`? **SIM (true)** | Imprime: `0` | Soma 1: `i` vira `1` |
| **2ª** | `1` | `1 < 10`? **SIM (true)** | Imprime: `1` | Soma 1: `i` vira `2` |
| **3ª** | `2` | `2 < 10`? **SIM (true)** | Imprime: `2` | Soma 1: `i` vira `3` |
| **4ª** | `3` | `3 < 10`? **SIM (true)** | Imprime: `3` | Soma 1: `i` vira `4` |
| **5ª** | `4` | `4 < 10`? **SIM (true)** | Imprime: `4` | Soma 1: `i` vira `5` |
| **6ª** | `5` | `5 < 10`? **SIM (true)** | Imprime: `5` | Soma 1: `i` vira `6` |
| **7ª** | `6` | `6 < 10`? **SIM (true)** | Imprime: `6` | Soma 1: `i` vira `7` |
| **8ª** | `7` | `7 < 10`? **SIM (true)** | Imprime: `7` | Soma 1: `i` vira `8` |
| **9ª** | `8` | `8 < 10`? **SIM (true)** | Imprime: `8` | Soma 1: `i` vira `9` |
| **10ª** | `9` | `9 < 10`? **SIM (true)** | Imprime: `9` | Soma 1: `i` vira `10` |
| **Fim** | `10` | `10 < 10`? **NÃO (false)** | *Não imprime nada!* | **O laço morre e o programa acaba!** |

### 📺 O que sai no Console na Versão 2:
```text
0
1
2
3
4
5
6
7
8
9
```

> 💡 **Pergunta comum de iniciante:** *"Por que parou no 9 e não no 10 se eu escrevi 10?"*  
> **Resposta:** Porque começamos a contar do **0** e usamos `< 10` (estritamente menor). Se você contar nos dedos: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9... você contou **10 números** no total! Na computação, tudo sempre começa no índice zero.

---

## 💻 Versão 3: Contando do Jeito Humano de 1 a 10 (Imagem 4)

Na Imagem 4, o professor pensa: *"E se eu quiser contar como um ser humano conta, de 1 até 10?"*.

Ele faz duas pequenas alterações na linha do `for`:
1. Muda o início de `0` para `1`: `int i = 1;`
2. Muda a condição de `<` para `<=` (menor ou igual): `i <= 10;`

### Código:
```java
public class Main {
    public static void main(String[] args){

        for(int i = 1; i <= 10; i++){
            System.out.println(i);
        }

    }
}
```

### 🔍 Enumeração Linha por Linha (Versão 3):

* **Linha 1:** `public class Main {`  
  * **O que faz:** Declara a classe `Main`.  
  * **Console:** Nada.
* **Linha 2:** `public static void main(String[] args){`  
  * **O que faz:** Método principal que o Java executa ao iniciar.  
  * **Console:** Nada.
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** `for(int i = 1; i <= 10; i++){`  
  * **O que faz:**
    * `int i = 1;` -> O contador começa valendo **1**.
    * `i <= 10;` -> Repete enquanto `i` for menor ou **igual** a 10.
    * `i++` -> Soma 1 a cada volta.
  * **Console:** Nada nessa linha propriamente dita.
* **Linha 5:** `System.out.println(i);`  
  * **O que faz:** Imprime o número atual guardado em `i`.  
  * **Console:** Imprime o número da rodada e pula linha.
* **Linha 6:** `}`  
  * **O que faz:** Fecha o bloco do `for`. Executa o `i++` e checa a condição novamente.  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco)*
* **Linha 8:** `}`  
  * **O que faz:** Fecha o método `main`. Encerra a execução do programa.  
  * **Console:** Mostra `Process finished with exit code 0`.
* **Linha 9:** `}`  
  * **O que faz:** Fecha a classe `Main`.  
  * **Console:** Nada.

### 📺 O que sai no Console na Versão 3:
```text
1
2
3
4
5
6
7
8
9
10
```

---

## 🎯 Comparativo Rápido para Não Esquecer Nunca Mais

| Para fazer isto: | Como escrever o `for`? | O que imprime no console? |
| :--- | :--- | :--- |
| **Repetir 10 vezes uma mensagem** | `for(int i = 0; i < 10; i++)` | `pizza` (10 vezes) |
| **Contar do jeito computador (índice 0 a 9)** | `for(int i = 0; i < 10; i++)` | `0, 1, 2, 3, 4, 5, 6, 7, 8, 9` |
| **Contar do jeito humano (1 a 10)** | `for(int i = 1; i <= 10; i++)` | `1, 2, 3, 4, 5, 6, 7, 8, 9, 10` |
