# ➿ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 25: Laços Aninhados (`nested loops`) — Parte 4: O Gerador de Matriz com Scanner e o Truque do `charAt(0)`
**Timestamps do vídeo:** `3:59:33` até `4:02:18 / 12:00:00`  
**Tema:** Início do projeto prático: Criando uma grade de caracteres dinâmica personalizada pelo usuário (`rows`, `columns`, `symbol`), por que não existe `scanner.nextChar()` no Java, o truque `scanner.next().charAt(0)` e a estratégia de testar uma linha antes da grade inteira.

---

## 🖼️ Contexto Visual dos Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Minuto 3:59:33 (A Demonstração do Projeto Final):**
   * É o teaser que o professor exibe para mostrar o resultado que vamos construir:
     * O programa pergunta: `Enter the # of rows: ` e o usuário digita `5`.
     * Pergunta: `Enter the # of columns: ` e o usuário digita `8`.
     * Pergunta: `Enter the symbol to use: ` e o usuário digita o símbolo de cifrão (`$`).
     * Imediatamente, o Java imprime na tela um retângulo perfeito de 5 linhas e 8 colunas de cifrões:
       ```text
       Enter the # of rows: 5
       Enter the # of columns: 8
       Enter the symbol to use: $
       $$$$$$$$
       $$$$$$$$
       $$$$$$$$
       $$$$$$$$
       $$$$$$$$
       ```
2. **Minuto 4:01:50 (Escrevendo o Código de Entrada):**
   * O professor começa a construir o programa:
     * Importa o `Scanner`: `import java.util.Scanner;`
     * Declara as 3 variáveis: `int rows;`, `int columns;` e `char symbol;`.
     * Pede as 3 entradas ao usuário usando `System.out.print` (para o cursor ficar na mesma linha da pergunta).
     * Lê as linhas com `rows = scanner.nextInt();`.
     * Lê as colunas com `columns = scanner.nextInt();`.
     * Lê o caractere com `symbol = scanner.next().charAt(0);`.
   * **A Estratégia de Programador Esperto:** Em vez de tentar fazer os dois laços de uma vez (o que confunde quem está aprendendo), ele escreve **apenas o laço das colunas primeiro** para ver se uma linha sai certa:
     ```java
     for(int i = 0; i < columns; i++){
         System.out.print(symbol);
     }
     ```
3. **Minuto 4:02:18 (O Teste da Linha Única):**
   * O professor roda o código intermediário para testar.
   * Ele digita `5` para linhas, `8` para colunas e `$` para o símbolo.
   * O console imprime **apenas uma única linha com 8 cifrões**:
     ```text
     $$$$$$$$
     ```
   * Por que só saiu uma linha se ele digitou 5 linhas?  
     Porque ele ainda não colocou o laço externo das linhas (`rows`)! Ele testou a engrenagem interna primeiro, comprovando que desenhar uma fileira de 8 cifrões já está funcionando 100%!

---

## 🧠 O Grande Mistério: Por Que Não Existe `scanner.nextChar()`?

Esta é uma das perguntas que **todo iniciante em Java** faz:
> *"Se para ler número inteiro usamos `scanner.nextInt()`, para número quebrado usamos `scanner.nextDouble()` e para texto usamos `scanner.nextLine()`... por que não existe `scanner.nextChar()` para ler um único caractere `char`?"*

### A Resposta:
Os engenheiros que criaram a biblioteca padrão do Java nos anos 90 simplesmente **não acharam necessário criar um método chamado `nextChar()`**. Não existe esse comando na classe `Scanner`! Se você digitar `scanner.nextChar()`, o IntelliJ vai pintar a linha de vermelho com erro.

### A Solução dos Programadores:
Para contornar isso com elegância, usamos uma composição em duas etapas:
$$\underbrace{\text{scanner.next()}}_{\text{1. Lê a palavra inteira como texto}}\ .\ \underbrace{\text{charAt(0)}}_{\text{2. Pega a primeira letra no índice 0}}$$

1. **`scanner.next()`**: Lê o que você digitou no teclado como um texto (`String`).
2. **`.charAt(0)`**: O comando `charAt(índice)` significa *"caractere na posição (índice)"*.
   * Na computação, a contagem sempre começa no **zero**.
   * A primeira letra de qualquer palavra fica sempre na posição **0**!
   * Se o usuário digitou `$`, o `.charAt(0)` pega o `'$'`.
   * Se o usuário digitou `#`, o `.charAt(0)` pega o `'#'`.
   * Se o usuário digitou por engano a palavra `abacaxi`, o `.charAt(0)` pega apenas a primeira letra: `'a'`.

```text
Entrada digitada pelo usuário:  " $ "
Posições na memória (índices):    0
                                  │
.charAt(0) extrai exatamente:    '$'  (guardado com sucesso na variável char symbol)
```

---

## 💻 Código Desta Etapa (Como o Professor Digitou nas Imagens 2 e 3)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int rows;
        int columns;
        char symbol;

        System.out.print("Enter the # of rows: ");
        rows = scanner.nextInt();

        System.out.print("Enter the # of columns: ");
        columns = scanner.nextInt();

        System.out.print("Enter the symbol to use: ");
        symbol = scanner.next().charAt(0);

        for(int i = 0; i < columns; i++){
            System.out.print(symbol);
        }

        scanner.close();
    }
}
```

---

## 🔍 Enumeração Linha por Linha com Memória RAM e Console:

* **Linha 1:** `import java.util.Scanner;`  
  * **O que faz:** Importa a ferramenta de leitura do teclado da biblioteca utilitária do Java.  
  * **Console:** Nada.
* **Linha 2:** *(Linha em branco).*
* **Linha 3:** `public class Main {` $\rightarrow$ Casca da classe. *(Console: Nada)*
* **Linha 4:** `public static void main(String[] args){` $\rightarrow$ Ponto inicial. *(Console: Nada)*
* **Linha 5:** *(Linha em branco).*
* **Linha 6:** `Scanner scanner = new Scanner(System.in);`  
  * **O que faz:** Cria e liga o objeto leitor do teclado.  
  * **Memória RAM:** Aloca memória para o objeto `scanner`.  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco).*
* **Linha 8:** `int rows;`  
  * **O que faz:** Declara a gaveta inteira para armazenar o número de **linhas** (*rows*).  
  * **Console:** Nada.
* **Linha 9:** `int columns;`  
  * **O que faz:** Declara a gaveta inteira para armazenar o número de **colunas** (*columns*).  
  * **Console:** Nada.
* **Linha 10:** `char symbol;`  
  * **O que faz:** Declara a gaveta para guardar um caractere único (`char`), como `$`, `@`, `*`.  
  * **Console:** Nada.
* **Linha 11:** *(Linha em branco).*
* **Linha 12:** `System.out.print("Enter the # of rows: ");`  
  * **O que faz:** Exibe a pergunta na tela sem quebrar a linha (o `#` significa quantidade/número).  
  * **Console:**
    ```text
    Enter the # of rows: 
    ```
* **Linha 13:** `rows = scanner.nextInt();`  
  * **O que faz:** Pausa o programa esperando você digitar um número e dar Enter.  
  * **Memória RAM:** Se você digitou `5`, guarda o valor `5` na gaveta `rows`.  
  * **Console:** O número digitado aparece na frente da pergunta:
    ```text
    Enter the # of rows: 5
    ```
* **Linha 14:** *(Linha em branco).*
* **Linha 15:** `System.out.print("Enter the # of columns: ");`  
  * **O que faz:** Pergunta quantas colunas serão desenhadas.  
  * **Console:**
    ```text
    Enter the # of columns: 
    ```
* **Linha 16:** `columns = scanner.nextInt();`  
  * **O que faz:** Pausa esperando você digitar o número de colunas e dar Enter.  
  * **Memória RAM:** Se digitou `8`, guarda o valor `8` na gaveta `columns`.  
  * **Console:**
    ```text
    Enter the # of columns: 8
    ```
* **Linha 17:** *(Linha em branco).*
* **Linha 18:** `System.out.print("Enter the symbol to use: ");`  
  * **O que faz:** Pergunta qual caractere deve ser usado.  
  * **Console:**
    ```text
    Enter the symbol to use: 
    ```
* **Linha 19:** `symbol = scanner.next().charAt(0);`  
  * **O que faz:** Lê o texto com `.next()`, pega o primeiro caractere com `.charAt(0)` e guarda em `symbol`.  
  * **Memória RAM:** Guarda o caractere `'$'` na variável `symbol`.  
  * **Console:**
    ```text
    Enter the symbol to use: $
    ```
* **Linha 20:** *(Linha em branco).*
* **Linha 21:** `for(int i = 0; i < columns; i++){`  
  * **O que faz:** Laço que roda `columns` vezes (de 0 até 7, totalizando 8 voltas).  
  * **Console:** Nada nessa linha.
* **Linha 22:** `System.out.print(symbol);`  
  * **O que faz:** Imprime o símbolo sem pular linha.  
  * **Console:** Imprime os 8 cifrões coladinhos: `$$$$$$$$`
* **Linha 23:** `}` $\rightarrow$ Fecha o laço `for`.
* **Linha 24:** *(Linha em branco).*
* **Linha 25:** `scanner.close();`  
  * **O que faz:** Fecha o leitor de teclado para economizar memória do computador.  
  * **Console:** Nada.
* **Linhas 26 e 27:** Fechamentos normais do Java.

---

## 📺 O que sai no Console no Teste Intermediário (Imagem 3):

```text
Enter the # of rows: 5
Enter the # of columns: 8
Enter the symbol to use: $
$$$$$$$$

Process finished with exit code 0
```

Na **Parte 5**, vamos colocar o laço das linhas (`rows`) em volta dessa fileira para multiplicar esse desenho por 5 vezes na vertical, completando o projeto com perfeição!
