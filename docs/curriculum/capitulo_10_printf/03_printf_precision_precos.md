# 🏷️ O Mistério do `[.precision]`: O Cupom Fiscal do Supermercado!

Nestas 3 imagens, o Bro Code deu um zoom na terceira parte daquela famosa fórmula misteriosa da linha 7: o **`[.precision]`** (a precisão das casas decimais)!

Ele montou um exemplo da vida real: **preços de produtos de uma loja**.

---

## 📋 1. Código Completo da Imagem 3 (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // %[flags][width][.precision][specifier-character]

        double price1 = 9.99;
        double price2 = 100.15;
        double price3 = -54.01;

        System.out.printf("%f\n", price1);
        System.out.printf("%f\n", price2);
        System.out.printf("%f\n", price3);
    }
}
```

---

## 🔍 2. O Que Ele Destacou na Imagem 1?

Olhe a seleção azul na Imagem 1:
```
// %[flags][width] [.precision] [specifier-character]
                     ▲
                 Ele selecionou isso aqui!
```

* **`precision`** significa **Precisão** (quantas casas decimais você quer depois do ponto).
* Repare que tem um **pontinho `.`** antes da palavra precision. Isso avisa: *"coloque um ponto e um número para dizer quantas casas você quer!"*.

---

## 🛒 3. A Criação dos Três Preços (Imagem 2)

Na linha 9 a 11, ele criou 3 produtos de loja:
```java
double price1 = 9.99;    // Preço do produto 1: 9 dólares e 99 centavos
double price2 = 100.15;  // Preço do produto 2: 100 dólares e 15 centavos
double price3 = -54.01;  // Preço do produto 3: um estorno / desconto / devolução!
```

> 💡 **Por que o preço 3 é negativo (`-54.01`)?**
> No mundo real (bancos, lojas e caixas), números negativos representam **descontos**, **devoluções de dinheiro** ou **dívidas**!

---

## 😱 4. O Problema Ridículo da Imagem 3: Os 4 Zeros a Mais!

Quando o autor mandou imprimir com **`%f\n`** comum, olhe o que apareceu na tela:

```
9.990000
100.150000
-54.010000
```

### Que coisa horrível! 🤮
Imagine você ir à padaria comprar um pão de R$ 9,99 e a maquininha de cartão mostrar:
> *"Total: R$ 9,990000"* 😂

### Por que o Java fez isso?
Lembre-se da regra de fábrica do Java:
* Quando você usa o **`%f` pelado** (sem número no meio), o Java é teimoso e **SEMPRE coloca 6 casas decimais**!
* Como o preço só tinha 2 casas (`.99`), ele completou com quatro zeros: `.990000`.

---

## 🔮 5. O Que Vai Acontecer na Próxima Tela?

O autor preparou esse cenário de propósito para mostrar o problema para os alunos!
Nos próximos segundos, ele vai colocar a **`precision`** em ação:

```java
// Ele vai trocar o %f por %.2f !
System.out.printf("%.2f\n", price1); // Vai sair: 9.99 !
```

Com o **`.2`**, a tesoura do `printf` corta o excesso de zeros e deixa exatamente com os 2 centavos de uma moeda real! ✂️💵
