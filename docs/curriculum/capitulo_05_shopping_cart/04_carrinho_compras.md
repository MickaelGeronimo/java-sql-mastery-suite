# 🛒 Projeto Carrinho de Compras (`Shopping Cart Program`)

O Bro Code começou um **novo mini-projeto super prático**: simular o caixa de um supermercado/lanchonete! Você vai escolher um produto, dizer o preço, a quantidade e o programa vai calcular o total da compra.

---

## 📋 1. Código da Etapa 1 (Pronto para copiar)

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

        System.out.println(item);

        scanner.close();
    }
}
```

---

## 🔍 2. Explicação Linha por Linha

| Linha | Código | O que faz? |
| :---: | :--- | :--- |
| **1** | `import java.util.Scanner;` | Traz a ferramenta para podermos ler o teclado. |
| **6** | `// SHOPPING CART PROGRAM` | Comentário avisando o nome do projeto (Carrinho de Compras). |
| **8** | `Scanner scanner = new Scanner(System.in);` | Liga o "microfone" do teclado. |
| **10** | `String item;` | Caixinha para guardar o **nome do produto** (texto, ex: `"hamburger"`). |
| **11** | `double price;` | Caixinha para guardar o **preço unitário** (com centavos, ex: `5.99`). |
| **12** | `int quantity;` | Caixinha para guardar a **quantidade** (número inteiro, ex: `3`). |
| **13** | `char currency = '$';` | **NOVIDADE!** Guarda o símbolo da moeda (dólar `$`). |
| **14** | `double total;` | Caixinha que guardará a conta final ($\text{preço} \times \text{quantidade}$). |
| **16** | `System.out.print("What item...");` | Pergunta: *"Qual item você gostaria de comprar?:"* |
| **17** | `item = scanner.nextLine();` | Lê o texto que o usuário digitar e guarda em `item`. |
| **19** | `System.out.println(item);` | Teste: Imprime o nome digitado na tela para conferir se funcionou. |
| **21** | `scanner.close();` | Desliga o Scanner. |

---

## 🌟 3. A GRANDE NOVIDADE: O Tipo `char`!

Olhe a linha 13 com muita atenção:
```java
char currency = '$';
```

### O que é `char`?
* `char` vem da palavra **Character** (Caractere).
* Serve para guardar **APENAS UM ÚNICO caractere** (uma letra, um número ou um símbolo).

### ⚠️ A Regra das Aspas Simples:
| Tipo | O que guarda? | Qual aspas usa? | Exemplos |
| :---: | :--- | :---: | :--- |
| **`String`** | Texto longo (várias letras) | `"aspas duplas"` | `"hamburger"`, `"Olá Mundo"`, `"Java"` |
| **`char`** | Apenas 1 caractere | `'aspas simples'` | `'$'`, `'R'`, `'A'`, `'9'`, `'#'` |

> 📌 **Erro fatal de iniciante:** Se você tentar colocar `char c = "$";` (com aspas duplas), o Java vai dar erro! `char` é **sempre** com aspas simples `''`.

---

## 🎯 4. O que aconteceu no teste da Imagem 4?

O autor testou a primeira parte do programa no console:

```
What item would you like to buy?: hamburger
hamburger
```

1. O programa perguntou o que ele queria comprar.
2. Ele digitou **`hamburger`**.
3. O programa leu com `scanner.nextLine()` e imprimiu **`hamburger`** de volta para provar que a primeira parte está funcionando!

---

## 🔮 5. O que vem a seguir no projeto?
Nas próximas telas, o autor vai:
1. Perguntar o **preço** (`price`).
2. Perguntar a **quantidade** (`quantity`).
3. Fazer a multiplicação: `total = price * quantity;`.
4. Mostrar a nota fiscal completa com o símbolo `$`!
