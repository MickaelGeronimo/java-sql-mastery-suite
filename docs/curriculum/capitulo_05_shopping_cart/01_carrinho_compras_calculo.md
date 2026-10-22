# 🛒 Carrinho de Compras — Etapas 3 e 4: Quantidade e Cálculo do Total!

Nessas 4 imagens, o Bro Code fez o coração do programa funcionar: **leu a quantidade desejada** e **calculou o valor total da compra**!

---

## 📋 1. Código Completo Atual (Pronto para copiar)

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

        System.out.println(total);

        scanner.close();
    }
}
```

---

## 🔍 2. O que aconteceu em cada Imagem?

---

### 🖼️ Imagens 1 e 2: Lendo a Quantidade com `nextInt()`

```java
System.out.print("How many would you like?: ");
quantity = scanner.nextInt();
```

1. **A Pergunta:** Mostra na tela: *"Quantos você gostaria?:"*.
2. **`scanner.nextInt()`**: Como a quantidade de comida é sempre um número inteiro (ninguém pede 2.5 hotdogs, pede 1, 2 ou 12), usamos **`nextInt()`**.
3. **No teste da Imagem 2:**
   * Item: `hotdog`
   * Preço: `4.99`
   * Quantidade: `12` $\rightarrow$ O número `12` foi salvo dentro de `quantity`!

---

### 🖼️ Imagens 3 e 4: O Cálculo do Total da Compra! 🧮

Depois de coletar todas as 3 respostas, o programa faz a conta matemática:

```java
total = price * quantity;
```

#### 🧮 A Conta Matemática por trás:
No teste da Imagem 4 (comprando pizza):
* `price` = **`14.99`**
* `quantity` = **`3`**
* Conta: $14.99 \times 3 = \mathbf{44.97}$

O computador joga o valor **`44.97`** dentro da caixinha **`total`**!

---

### 🖥️ Imagem 4: Mostrando o Total na Tela

```java
System.out.println(total);
```
O console exibiu **`44.97`**! O cálculo do caixa funcionou com 100% de perfeição.

---

## 🔬 3. Curiosidade: O que acontece quando você multiplica `double` com `int`?

Olhe os tipos de dados:
* `price` é **`double`** (tem vírgula: `14.99`).
* `quantity` é **`int`** (número inteiro: `3`).
* `total` é **`double`** (resultado final: `44.97`).

> 📌 **Regra do Java:** Sempre que você mistura um número decimal (`double`) com um inteiro (`int`) numa conta de multiplicação, o Java transforma o resultado final em **`double`** para não perder os centavos!

---

## 🎬 4. O Fluxo Completo da Compra até Agora:

```
[ CONSOLE DA LANCHONETE ]

1. O caixa pergunta o item:       What item would you like to buy?: pizza
2. O caixa pergunta o preço:      What is the price for each?: 14.99
3. O caixa pergunta a quantidade: How many would you like?: 3

[ PROCESSAMENTO INVISÍVEL ]
total = 14.99 * 3 = 44.97

[ RESULTADO ]
44.97
```

---

## 🔮 5. O que falta para ficar perfeito?
Na próxima e última etapa, o autor vai formatar a mensagem final para não mostrar só o número pelado `44.97`, mas sim uma frase completa de recibo como:
> *"You have bought 3 pizza(s) for $44.97"*
