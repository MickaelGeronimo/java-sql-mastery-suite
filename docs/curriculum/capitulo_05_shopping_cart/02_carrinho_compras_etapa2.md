# 🛒 Carrinho de Compras — Etapa 2: Guia Ultra-Didático para Iniciantes

Imagine que você foi contratado para criar o **sistema do caixa do McDonald's ou de um supermercado**. Neste código, você está construindo o programa que atende o cliente no caixa!

---

## 📋 1. Código Completo da Etapa 2 (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // SHOPPING CART PROGRAM

        Scanner scanner = new Scanner(System.in);

        String item;
        double price;
        int quantity;
        char currency = '$';
        double total;

        System.out.print("What item would you like to buy?: ");
        item = scanner.nextLine();

        System.out.print("What is the price for each?: ");
        price = scanner.nextDouble();

        System.out.println(price);

        scanner.close();
    }
}
```

---

## 🏺 2. A Analogia dos Potes na Cozinha

Pense nas variáveis como **potes de vidro vazios** colocados no balcão antes de abrir a lanchonete:

```
[ Pote 1: String item       ]  👉 (Vazio: vai guardar o NOME da comida)
[ Pote 2: double price      ]  👉 (Vazio: vai guardar o PREÇO com centavos)
[ Pote 3: int quantity      ]  👉 (Vazio: vai guardar a QUANTIDADE)
[ Pote 4: char currency     ]  👉 (Já tem '$' dentro: a moeda usada)
[ Pote 5: double total      ]  👉 (Vazio: vai guardar o VALOR TOTAL da conta)
```

### O que o programa faz até agora:
1. Pergunta o que o cliente quer $\rightarrow$ Cliente responde `"hamburger"` $\rightarrow$ Guarda no **Pote 1 (`item`)**.
2. Pergunta quanto custa $\rightarrow$ Cliente responde `9.99` $\rightarrow$ Guarda no **Pote 2 (`price`)**.
3. Mostra o `price` na tela só para garantir que o número entrou no pote certinho!

---

## 🔍 3. Tabela Linha por Linha Completa

| Linha | Código | O que o computador faz? |
| :---: | :--- | :--- |
| **1** | `import java.util.Scanner;` | Traz a ferramenta `Scanner` da biblioteca do Java para podermos ler o que o usuário digita. |
| **3** | `public class Main {` | Início da classe obrigatória do Java. |
| **4** | `public static void main(...) {` | Ponto de partida onde o programa começa a rodar. |
| **6** | `// SHOPPING CART PROGRAM` | Comentário (anotação ignorada pelo Java). |
| **8** | `Scanner scanner = new Scanner(System.in);` | Liga o "leitor de teclado" (`System.in`). |
| **10** | `String item;` | Cria a variável de texto para o nome do item. |
| **11** | `double price;` | Cria a variável decimal para o preço unitário. |
| **12** | `int quantity;` | Cria a variável inteira para a quantidade de itens. |
| **13** | `char currency = '$';` | Cria a variável de 1 caractere com o símbolo do dólar (`'$'`). |
| **14** | `double total;` | Cria a variável decimal para a conta final. |
| **16** | `System.out.print("What item...");` | **Pergunta 1:** Mostra na tela a pergunta do item. |
| **17** | `item = scanner.nextLine();` | Lê o texto digitado pelo usuário e guarda em `item`. |
| **19** | `System.out.print("What is the price...");` | **Pergunta 2:** Mostra na tela a pergunta do preço. |
| **20** | `price = scanner.nextDouble();` | Lê o número decimal digitado e guarda em `price`. |
| **22** | `System.out.println(price);` | Imprime o preço no console para testar se deu certo. |
| **24** | `scanner.close();` | Desliga o leitor de teclado para economizar memória. |

---

## 🥊 4. `nextLine()` vs `nextDouble()` — Qual a diferença?

O Scanner tem ferramentas diferentes para ler cada tipo de informação:

| Método do Scanner | Tipo de Dado | O que ele sabe ler? | Exemplo no nosso código |
| :--- | :---: | :--- | :--- |
| **`scanner.nextLine()`** | `String` | Palavras, frases, textos com espaços. | `"hamburger"`, `"pizza de calabresa"` |
| **`scanner.nextDouble()`** | `double` | Números com casas decimais (centavos). | `9.99`, `4.50`, `15.75` |
| **`scanner.nextInt()`** | `int` | Apenas números inteiros (sem vírgula). | `1`, `2`, `10` *(será usado na quantidade!)* |

---

## ⚠️ 5. O Erro Mais Comum: Por que NÃO repetir `double` na Linha 20?

Muitos iniciantes tentam escrever assim na linha 20:
```java
double price = scanner.nextDouble(); // ❌ ERRADO se você já declarou na linha 11!
```

### Por que dá erro?
* **Linha 11:** `double price;` $\rightarrow$ Você foi na loja e comprou um pote novo com a etiqueta "price".
* **Linha 20:** Se você escrever `double price` de novo, o Java acha que você está tentando criar um **segundo pote com o mesmo nome**, o que causa uma confusão e gera erro de *variável duplicada*.
* **O Certo:** Na linha 20, use apenas `price = scanner.nextDouble();` (você está apenas colocando o conteúdo dentro do pote que já existe!).

---

## 🇧🇷 6. Dica Prática: Ponto (`.`) vs Vírgula (`,`) no Teclado

* Em código Java nos EUA: usa-se **ponto** para decimais (`9.99`).
* No Windows em Português do Brasil: ao digitar no console, o Java pode esperar **vírgula** (`9,99`).
* Se você digitar com ponto e der um erro vermelho chamado `InputMismatchException`, tente digitar com vírgula (`9,99`)!

---

## 🎬 7. Simulação Visual do que Acontece na Tela

```
[ Console da IDE ]

What item would you like to buy?: hamburger ⏎
What is the price for each?: 9.99 ⏎
9.99

Process finished with exit code 0 ✅
```
