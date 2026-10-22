# 🧾 Carrinho de Compras — A Nota Fiscal Final! (Projeto Concluído)

Nessas imagens, o Bro Code **finalizou o projeto com chave de ouro**: em vez de mostrar números soltos, ele montou a **nota fiscal completa e formatada** do cliente!

---

## 📋 1. Código Completo Final (Pronto para rodar na sua IDE)

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

        System.out.print("How many would you like?: ");
        quantity = scanner.nextInt();

        total = price * quantity;

        System.out.println("\nYou have bought " + quantity + " " + item + "/s");
        System.out.println("Your total is " + currency + total);

        scanner.close();
    }
}
```

---

## 🔍 2. As Duas Linhas Mágicas que ele Adicionou:

---

### 📜 Linha 27: A linha do que foi comprado
```java
System.out.println("\nYou have bought " + quantity + " " + item + "/s");
```

Vamos desmontar essa linha como blocos de LEGO:

```
"\nYou have bought "  +  quantity  +  " "  +  item     +  "/s"
        ↓                   ↓          ↓        ↓          ↓
     Texto               Número     Espaço    Texto      Sufixo
 ("Você comprou ")         (8)        (" ")  ("pizza")   ("/s")
```

#### 💡 O que é aquele `\n` no começo?
* O **`\n`** significa **"Nova Linha"** (New Line / Pular linha).
* Ele serve para dar um **espaço em branco** entre as perguntas e o recibo final, deixando o visual do programa limpo e profissional!

#### 🍕 O que o Java montou:
$$\text{"You have bought 8 pizza/s"}$$
*(O `/s` no final serve para o plural: se comprar 1 pizza fica `1 pizza/s`, se comprar 8 fica `8 pizza/s`)*.

---

### 💵 Linha 28: A linha do valor total
```java
System.out.println("Your total is " + currency + total);
```

Vamos desmontar:

```
"Your total is "  +   currency   +   total
       ↓                  ↓            ↓
     Texto              char         double
 ("Seu total é ")       ('$')      (119.92)
```

#### 💰 O que o Java montou:
$$\text{"Your total is \$119.92"}$$

Olha que legal: ele juntou a variável **`char currency`** (`'$'`) grudada no número **`double total`** (`119.92`) para formar **`$119.92`** automaticamente!

---

## 🖥️ 3. O Resultado Perfeito no Console:

```
What item would you like to buy?: pizza
What is the price for each?: 14.99
How many would you like?: 8

You have bought 8 pizza/s
Your total is $119.92

Process finished with exit code 0 ✅
```

---

## 🏆 4. Tudo o que você aprendeu com esse mini-projeto:

| # | Conceito | Onde foi usado? |
| :---: | :--- | :--- |
| **1** | **`Scanner`** | Para ler as respostas do cliente no teclado. |
| **2** | **Tipos de dados diferentes** | `String` (item), `double` (preço), `int` (quantidade) e `char` (moeda). |
| **3** | **Matemática no Java** | `total = price * quantity;` (multiplicação). |
| **4** | **Caractere de escape `\n`** | Pular uma linha para separar o recibo. |
| **5** | **Concatenação com `+`** | Juntar textos, números e símbolos em frases elegantes. |

Parabéns! Você acabou de acompanhar a construção de um programa Java completo do mundo real! 🎉
