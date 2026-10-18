# ➿ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 25: Laços Aninhados (`nested loops`) — Parte 1: Introdução, Linhas vs. Colunas e o Erro do "Copiar e Colar"
**Timestamps do vídeo:** `3:55:48` até `3:57:15 / 12:00:00`  
**Tema:** O que é um Laço Aninhado (*nested loop*), impressão vertical (`println`) vs. horizontal (`print`), onde são usados (matrizes e algoritmos) e por que copiar e colar o mesmo laço 3 vezes é uma péssima prática de programação.

---

## 🖼️ Contexto Visual dos Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você está lendo este documento no Google Docs sem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** faz na tela em cada segundo:

1. **Minuto 3:55:48 (Título e Comentário Inicial):**
   * O professor abre o IntelliJ IDEA com a classe `Main.java` limpa.
   * No topo do método `main`, ele escreve a definição em inglês:
     `// nested loop = A loop inside another loop`  
     *(Tradução: Laço aninhado = Um laço de repetição DENTRO de outro laço de repetição).*
2. **Minuto 3:56:40 (O Primeiro Laço Vertical):**
   * Ele digita um laço `for` simples:
     ```java
     for(int i = 1; i <= 9; i++){
         System.out.println(i);
     }
     ```
   * Clica no botão verde de Play. No console abaixo, aparecem os números de 1 a 9 na **vertical** (um embaixo do outro).
3. **Minuto 3:57:01 (Mudança para a Horizontal):**
   * O professor explica: *"Para desenharmos tabelas e matrizes, não queremos que os números desçam a linha toda vez. Queremos que fiquem lado a lado!"*.
   * Ele apaga o `ln` do `println` e adiciona um espaço: `System.out.print(i + " ");`.
   * Ao rodar, o console imprime tudo na **horizontal**: `1 2 3 4 5 6 7 8 9 `.
4. **Minuto 3:57:15 (O Erro de Copiar e Colar 3 Vezes):**
   * Ele adiciona o comentário: `// Used often with matrices or DS&A` *(Usado em matrizes e algoritmos)*.
   * O professor diz: *"Imagine que eu queira imprimir essa mesma linha de números 3 vezes seguidas. O que um iniciante faria?"*.
   * Ele copia o bloco do `for` e cola 3 vezes no código.
   * Ele aponta para a tela e alerta: *"Olhe como isso fica feio, repetitivo e desorganizado. E se precisássemos de 100 linhas? Daríamos Ctrl+C e Ctrl+V 100 vezes? Jamais!"*.

---

## 🧠 O Que É um Laço Aninhado? (Explicação para o Iniciante Amador)

A palavra em inglês **nested** vem de *nest* (ninho de passarinho). Em programação, significa **"aninhar"**, ou seja, **colocar uma estrutura inteira DENTRO de outra estrutura**.

### 👶 A Metáfora Mais Fácil do Mundo: A Boneca Russa (Matryoshka)
Sabe aquelas bonecas russas de madeira?
1. Você pega a **boneca de fora** (o laço externo).
2. Você abre ela e encontra uma **boneca menor dentro** (o laço interno).
3. Você não consegue fechar a boneca de fora enquanto a boneca de dentro não estiver perfeitamente terminada e guardada!

### 🕰️ A Metáfora do Relógio de Parede (Horas e Minutos)
Pense no relógio que fica na parede da sua sala:
* O ponteiro dos **minutos** tem que dar **60 voltas completas** para que o ponteiro das **horas** ande apenas **1 número**!
* O ponteiro das **horas** é o laço de fora (*outer loop*).
* O ponteiro dos **minutos** é o laço de dentro (*inner loop*).
* Toda vez que a hora muda (Linha 1 $\rightarrow$ Linha 2), os minutos começam tudo de novo do zero até o final!

---

## 💻 Código 1: O Laço Simples na Vertical (Minuto 3:56:40)

```java
public class Main {
    public static void main(String[] args){

        for(int i = 1; i <= 9; i++){
            System.out.println(i);
        }

    }
}
```

### 🔍 Enumeração Linha por Linha com Memória RAM e Console:

* **Linha 1:** `public class Main {`  
  * **O que faz:** Cria a classe principal `Main`. É a casca do programa.  
  * **Memória RAM:** O Java carrega a classe na memória.  
  * **Console:** Nada.
* **Linha 2:** `public static void main(String[] args){`  
  * **O que faz:** O método `main`, o ponto exato onde a execução começa.  
  * **Memória RAM:** Cria a pilha de execução principal.  
  * **Console:** Nada.
* **Linha 3:** *(Linha em branco para separar o código).*
* **Linha 4:** `for(int i = 1; i <= 9; i++){`  
  * **O que faz:** Cria a variável contadora `i` valendo `1`. Testa se `i <= 9`. A cada volta, adiciona 1 (`i++`).  
  * **Memória RAM:** Cria uma gaveta de número inteiro chamada `i` com o valor inicial `1`.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 5:** `System.out.println(i);`  
  * **O que faz:** Imprime o valor atual de `i`. Como tem `ln` (*line*), ele imprime e pula para a linha de baixo.  
  * **Memória RAM:** Lê o valor de `i`.  
  * **Console:** Imprime o número atual e pula de linha.
* **Linha 6:** `}`  
  * **O que faz:** Fim da volta do laço. Pula de volta para o `for` na Linha 4, faz `i++` e testa de novo.  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco).*
* **Linha 8:** `}` $\rightarrow$ Fecha o método `main`.
* **Linha 9:** `}` $\rightarrow$ Fecha a classe `Main`.

### 📺 O que sai no Console (Vertical):
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
```

---

## 💻 Código 2: Mudando para a Horizontal com Espaços (Minuto 3:57:01)

```java
public class Main {
    public static void main(String[] args){

        for(int i = 1; i <= 9; i++){
            System.out.print(i + " ");
        }

    }
}
```

### 🔍 O que mudou aqui?
1. **Saiu o `println` e entrou o `print`:** A instrução `print` comum **NUNCA pula de linha**. O cursor do terminal fica parado esperando o próximo caractere imediatamente à direita!
2. **Adicionou `+ " "`:** Para os números não ficarem grudados como `123456789`, nós concatenamos uma String com um espaço em branco após cada número.

### 📺 O que sai no Console (Horizontal):
```text
1 2 3 4 5 6 7 8 9 
```

---

## 💻 Código 3: O Jeito Ruim/Amador de Repetir Linhas (Minuto 3:57:15)

O professor quer imprimir 3 linhas dessa sequência de 1 a 9. Ele mostra o que um iniciante sem técnica faria:

```java
public class Main {
    public static void main(String[] args){

        // nested loop = A loop inside another loop
        //               Used often with matrices or DS&A

        for(int i = 1; i <= 9; i++){
            System.out.print(i + " ");
        }

        for(int i = 1; i <= 9; i++){
            System.out.print(i + " ");
        }

        for(int i = 1; i <= 9; i++){
            System.out.print(i + " ");
        }

    }
}
```

### 🔍 Por que o código acima é horrível?
1. **Viola o Princípio DRY (*Don't Repeat Yourself*):** Uma das primeiras regras que você aprende na faculdade ou no mercado de trabalho é: *"Se você copiou e colou o mesmo código, você está fazendo errado"*.
2. **Não tem quebra de linha:** Como nenhum dos três laços mandou pular linha, todos os números saem numa "tripa" única horizontal:
   ```text
   1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 
   ```
3. **Não tem flexibilidade:** E se o usuário quiser 50 linhas? Você vai colar 50 laços `for` no código? Se o cliente pedir para mudar de 9 para 10 números, você terá que editar 50 lugares!

---

## 💡 O Que Vamos Fazer na Próxima Parte?
Em vez de colar o mesmo laço 3 vezes, nós vamos colocar esse laço **DENTRO** de um laço externo que manda repetir 3 vezes!  
Mas cuidado: ao fazer isso de forma ingênua, um **erro vermelho clássico** vai explodir na sua tela... e nós vamos desvendá-lo na Parte 2!
