# 🍕 Capítulo 27: O Guia Definitivo e Completo de Métodos Sobrecarregados em Java (Overloaded Methods) — Partes 1 a 6

> **Documento Oficial de Estudos para Cópia no Google Docs:** Este material foi estruturado para ser **100% autossuficiente**. Ele descreve todos os contextos visuais das aulas do canal Bro Code (*Java Full Course for free*), inclui diagramas de assinatura de métodos, a evolução matemática do método `add`, a metáfora da Pizzaria (`bakePizza`), mapas de compilação e códigos Java compiláveis sem erros.

---

## 📑 Índice da Apostila
1. [Visão Geral: O Que São Métodos Sobrecarregados (*Overloaded Methods*)?](#1-visão-geral)
2. [A Fórmula Sagrada: O Que É a Assinatura do Método (*Method Signature*)?](#2-a-assinatura-do-método)
3. [O Que o Java Olha vs. O Que o Java Ignora](#3-o-que-o-java-olha-vs-ignora)
4. [Exemplo 1: A Calculadora Flexível (`add` com 2, 3 e 4 Parâmetros)](#4-exemplo-calculadora-add)
5. [Exemplo 2: A Pizzaria do Bro Code 🍕 (`bakePizza` Personalizável)](#5-exemplo-pizzaria-bakepizza)
6. [Anatomia dos Erros de Compilação (*Method Already Defined*)](#6-erros-de-compilação)
7. [Polimorfismo Estático: O Que Acontece por Trás dos Panos na JVM](#7-polimorfismo-estático)
8. [Boas Práticas e FAQ: Por Que Sobrecarga É Tão Importante?](#8-boas-práticas-e-faq)
9. [Códigos-Fonte Completos e Compiláveis de Todo o Capítulo 27](#9-códigos-fonte-completos)

---

<a name="1-visão-geral"></a>
## 💡 1. Visão Geral: O Que São Métodos Sobrecarregados (*Overloaded Methods*)?

O autor abre o capítulo com uma definição essencial:
> **Métodos Sobrecarregados (*Overloaded Methods*):** São métodos que compartilham o **mesmo nome**, mas possuem **parâmetros diferentes**.

### 👶 A Analogia Para Iniciantes Amadores:
Imagine que você entra em uma cafeteria e diz para o barista:
1. *"Por favor, me dê um café."* $\rightarrow$ Ele te entrega um café puro.
2. *"Me dê um café com leite."* $\rightarrow$ Ele te entrega um café com leite.
3. *"Me dê um café com leite e canela."* $\rightarrow$ Ele te entrega um café com leite e canela.

Repare: nas 3 situações, a ação que você pediu foi a mesma: **"fazer café"**.
Você **não** precisou inventar palavras malucas como *"fazerCafé1"*, *"fazerCafé2"* ou *"fazerCafé3"*. O barista simplesmente olha para os ingredientes que você entregou e sabe exatamente qual receita seguir!

Em Java, **Sobrecarga de Métodos** é exatamente isso: dar o mesmo nome a uma ação, permitindo que ela funcione com diferentes quantidades ou tipos de dados!

---

<a name="2-a-assinatura-do-método"></a>
## 📜 2. A Fórmula Sagrada: O Que É a Assinatura do Método (*Method Signature*)?

Para o compilador do Java, dois métodos só são considerados diferentes se as suas **Assinaturas (*Signatures*)** forem diferentes:

$$\mathbf{Assinatura\ do\ M\text{é}todo} = \mathbf{Nome\ do\ M\text{é}todo} + \mathbf{Lista\ de\ Par\hat{a}metros}$$

```text
               ┌────────────────────────────── Nome do Método
               │        ┌───────────────────── Tipo dos Parâmetros
               │        │        ┌──────────── Quantidade de Parâmetros
               ▼        ▼        ▼
       static double  add (double a, double b)
```

A lista de parâmetros é avaliada por três critérios:
1. **Quantidade de parâmetros** (ex: 2 parâmetros vs. 3 parâmetros).
2. **Tipos dos parâmetros** (ex: `int` vs. `double`).
3. **Ordem dos tipos** (ex: `(int, String)` vs. `(String, int)`).

---

<a name="3-o-que-o-java-olha-vs-ignora"></a>
## 🔍 3. O Que o Java Olha vs. O Que o Java Ignora

Esta é a pegadinha número 1 em provas de certificação Java:

| Elemento | O Java Olha para Diferenciar? | Explicação |
| :--- | :---: | :--- |
| **Nome do Método** | **SIM** | Precisa ser idêntico para ser sobrecarga. |
| **Quantidade de Parâmetros** | **SIM** | `(a, b)` é diferente de `(a, b, c)`. |
| **Tipo dos Parâmetros** | **SIM** | `(int, int)` é diferente de `(double, double)`. |
| **Ordem dos Tipos** | **SIM** | `(int, double)` é diferente de `(double, int)`. |
| **Nome das Variáveis** | ❌ **NÃO!** | `(int a, int b)` e `(int x, int y)` são **idênticos** para o Java! |
| **Tipo de Retorno (`void`, `double`)** | ❌ **NÃO!** | Mudar apenas o tipo de retorno **NÃO** cria sobrecarga e gera erro de compilação! |

---

<a name="4-exemplo-calculadora-add"></a>
## ➕ 4. Exemplo 1: A Calculadora Flexível (`add` com 2, 3 e 4 Parâmetros)

Nas Partes 1 a 3 do vídeo, o autor constrói uma calculadora de soma chamada `add`.

### 4.1 O Problema Inicial:
Se você criar apenas:
```java
static double add(double a, double b) {
    return a + b;
}
```
E depois tentar somar 3 números no `main`:
```java
System.out.println(add(1, 2, 3)); // ERRO! Não aceita 3 argumentos!
```
O Java recusa porque o método só tem duas vagas (`a` e `b`).

### 4.2 A Solução com Sobrecarga:
O autor cria **3 versões irmãs** do método `add`:

```java
// Versão 1: Soma 2 números
static double add(double a, double b) {
    return a + b;
}

// Versão 2: Soma 3 números
static double add(double a, double b, double c) {
    return a + b + c;
}

// Versão 3: Soma 4 números
static double add(double a, double b, double c, double d) {
    return a + b + c + d;
}
```

### 🧠 O Roteamento Inteligente no `main`:
Quando você escreve:
```java
System.out.println(add(1, 2));       // O Java aciona a Versão 1!
System.out.println(add(1, 2, 3));    // O Java aciona a Versão 2!
System.out.println(add(1, 2, 3, 4)); // O Java aciona a Versão 3!
```
O compilador conta quantos números você passou entre parênteses e escolhe o método correto em fração de nanossegundo!

---

<a name="5-exemplo-pizzaria-bakepizza"></a>
## 🍕 5. Exemplo 2: A Pizzaria do Bro Code (`bakePizza` Personalizável)

Nas Partes 4 a 6 do vídeo, o autor apresenta o exemplo mais memorável do curso: uma pizzaria onde o cliente pode montar o pedido com quantos ingredientes quiser!

```java
public class Main {

    public static void main(String[] args) {

        // Pedido 1: Pizza só com massa
        String pizza1 = bakePizza("flatbread");
        System.out.println(pizza1);

        // Pedido 2: Pizza com massa e queijo
        String pizza2 = bakePizza("flatbread", "mozzarella");
        System.out.println(pizza2);

        // Pedido 3: Pizza completa com cobertura
        String pizza3 = bakePizza("flatbread", "mozzarella", "pepperoni");
        System.out.println(pizza3);
    }

    // 1 ingrediente
    static String bakePizza(String bread) {
        return bread + " pizza";
    }

    // 2 ingredientes
    static String bakePizza(String bread, String cheese) {
        return cheese + " " + bread + " pizza";
    }

    // 3 ingredientes
    static String bakePizza(String bread, String cheese, String topping) {
        return topping + " " + cheese + " " + bread + " pizza";
    }
}
```

### 🖥️ Saída no Console:
```text
flatbread pizza
mozzarella flatbread pizza
pepperoni mozzarella flatbread pizza

Process finished with exit code 0
```

---

<a name="6-erros-de-compilação"></a>
## 🚫 6. Anatomia dos Erros de Compilação (*Method Already Defined*)

No vídeo, o autor mostra o que acontece se o programador errar a sobrecarga.

### Erro 1: Mudar Apenas o Nome das Variáveis
```java
static double add(double a, double b) { return a + b; }
static double add(double x, double y) { return x + y; } // ERRO!
```
* **O que o IntelliJ diz:** `method add(double, double) is already defined in class Main`.
* **Explicação:** Para a JVM, ambos são `add(double, double)`. O nome que você dá para as variáveis (`a, b` ou `x, y`) é descartado na compilação.

### Erro 2: Mudar Apenas o Tipo de Retorno
```java
static int add(double a, double b) { return (int)(a + b); } // ERRO!
static double add(double a, double b) { return a + b; }     // ERRO!
```
* **Explicação:** Se alguém no `main` fizer `add(2.0, 3.0);` sem guardar o resultado em uma variável, qual dos dois o Java deveria chamar? Como não há como saber, o compilador proíbe!

---

<a name="7-polimorfismo-estático"></a>
## ⚙️ 7. Polimorfismo Estático: O Que Acontece por Trás dos Panos na JVM

Em linguagens de programação, a sobrecarga de métodos é classificada como:
$$\mathbf{Polimorfismo\ em\ Tempo\ de\ Compila\text{ç}\tilde{a}o\ (Compile-Time\ Polymorphism)}$$
*(Também chamado de Ligação Precoce ou Static Binding)*.

```text
CÓDIGO FONTE                           BYTECODE COMPILADO
add(1.0, 2.0)        ────────►  invokestatic Main.add(DD)D
add(1.0, 2.0, 3.0)   ────────►  invokestatic Main.add(DDD)D
```

* O Java **não perde tempo** decidindo qual método chamar enquanto o programa está rodando.
* No momento em que você clica no botão de compilar (Build), o compilador já "amarra" cada chamada diretamente ao endereço do método correto. Por isso, a sobrecarga tem **custo zero de performance**!

---

<a name="8-boas-práticas-e-faq"></a>
## 🏆 8. Boas Práticas e FAQ: Por Que Sobrecarga É Tão Importante?

### P1: Por que não criar nomes diferentes como `add2Numbers` e `add3Numbers`?
**Resposta:** Porque polui o código e sobrecarrega a memória do programador!
Pense no comando mais famoso do Java:
* `System.out.println("Texto");`
* `System.out.println(10);`
* `System.out.println(true);`
* `System.out.println(3.14);`
A equipe do Java usou **Sobrecarga de Métodos**! Se eles não tivessem feito isso, você teria que decorar: `printString()`, `printInt()`, `printBoolean()`, `printDouble()`. Seria insuportável!

### P2: Quantos métodos sobrecarregados posso ter com o mesmo nome?
**Resposta:** Quantos você quiser, desde que cada um tenha uma lista de parâmetros diferente!

---

<a name="9-códigos-fonte-completos"></a>
## 💻 9. Códigos-Fonte Completos e Compiláveis de Todo o Capítulo 27

### Arquivo 1: A Calculadora Completa (`AddProgram.java`)
```java
public class Main {

    public static void main(String[] args) {

        System.out.println("--- Teste de Soma Sobrecarregada ---");
        System.out.println("Soma de 2 números: " + add(1.5, 2.5));
        System.out.println("Soma de 3 números: " + add(1.0, 2.0, 3.0));
        System.out.println("Soma de 4 números: " + add(1.0, 2.0, 3.0, 4.0));
    }

    static double add(double a, double b) {
        return a + b;
    }

    static double add(double a, double b, double c) {
        return a + b + c;
    }

    static double add(double a, double b, double c, double d) {
        return a + b + c + d;
    }
}
```

### Arquivo 2: A Pizzaria Completa (`PizzaProgram.java`)
```java
public class Main {

    public static void main(String[] args) {

        System.out.println("--- Pizzaria do Bro Code ---");

        String pizza1 = bakePizza("flatbread");
        System.out.println("Pedido 1: " + pizza1);

        String pizza2 = bakePizza("flatbread", "mozzarella");
        System.out.println("Pedido 2: " + pizza2);

        String pizza3 = bakePizza("flatbread", "mozzarella", "pepperoni");
        System.out.println("Pedido 3: " + pizza3);
    }

    static String bakePizza(String bread) {
        return bread + " pizza";
    }

    static String bakePizza(String bread, String cheese) {
        return cheese + " " + bread + " pizza";
    }

    static String bakePizza(String bread, String cheese, String topping) {
        return topping + " " + cheese + " " + bread + " pizza";
    }
}
```

---

Prontinho! Esta apostila definitiva consolida e aprofunda tudo sobre **Overloaded Methods**, com a fórmula da assinatura, os erros clássicos, a analogia da pizzaria e códigos completos prontos para o seu Google Docs! 🍕➕📘✨
