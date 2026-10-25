# 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 11)
## A Coexistência de Instâncias: `product1` (Double) e `product2` (Integer), Erro de Colisão e Execução

---

## 📑 Sumário

1. [Visão Geral do Episódio e Propósito Pedagógico](#1-visão-geral-do-episódio-e-propósito-pedagógico)
2. [Reconstituição Visual Completa dos 5 Momentos do Vídeo (Google Docs)](#2-reconstituição-visual-completa-dos-5-momentos-do-vídeo-google-docs)
   - [Momento 1 (10:51:10): Duplicação de Linha e Aviso de Nome Repetido](#momento-1-105110-duplicação-de-linha-e-aviso-de-nome-repetido)
   - [Momento 2 (10:51:15): O Teste de Erro — Tentando Colocar `0.50` num `Integer`](#momento-2-105115-o-teste-de-erro--tentando-colocar-050-num-integer)
   - [Momento 3 (10:51:30): A Correção com `"ticket"` e Preço Inteiro `15`](#momento-3-105130-a-correção-com-ticket-e-preço-inteiro-15)
   - [Momento 4 (10:51:32): Execução de `product2.getItem()` Imprime `ticket`](#momento-4-105132-execução-de-product2getitem-imprime-ticket)
   - [Momento 5 (10:51:39): Execução de `product2.getPrice()` Imprime `15`](#momento-5-105139-execução-de-product2getprice-imprime-15)
3. [Código-Fonte Completo dos Arquivos do Projeto](#3-código-fonte-completo-dos-arquivos-do-projeto)
   - [`Product.java`](#productjava)
   - [`Main.java`](#mainjava)
   - [`Box.java`](#boxjava)
4. [Explicação Linha por Linha ("Como se Fosse um Lezado Iniciante Amador")](#4-explicação-linha-por-linha-como-se-fosse-um-lezado-iniciante-amador)
   - [Dissecando `Main.java` Linha por Linha](#dissecando-mainjava-linha-por-linha)
5. [O Que Este Exemplo Revela Sobre Generics na Memória do Computador?](#5-o-que-este-exemplo-revela-sobre-generics-na-memória-do-computador)
   - [Duas Variáveis, Tipos Internos Diferentes, Mesma Classe Mãe](#duas-variáveis-tipos-internos-diferentes-mesma-classe-mãe)
   - [Diagrama de Memória (Stack vs Heap)](#diagrama-de-memória-stack-vs-heap)
   - [Por que o compilador barrou o `0.50` no `product2`?](#por-que-o-compilador-barrou-o-050-no-product2)
6. [Tabela Comparativa de `product1` vs `product2`](#6-tabela-comparativa-de-product1-vs-product2)
7. [Tabela de Saída do Console e Rastreio de Execução Passo a Passo](#7-tabela-de-saída-do-console-e-rastreio-de-execução-passo-a-passo)
8. [Conclusão e Próximos Passos](#8-conclusão-e-próximos-passos)

---

## 1. Visão Geral do Episódio e Propósito Pedagógico

Até o momento, tínhamos testado a classe `Product<T, U>` com apenas um produto: uma maçã de 50 centavos (`product` com `String` e `Double`).

Mas e se em um mesmo programa você precisar vender **produtos com naturezas de preço totalmente diferentes**?
- **Produto 1:** Um item de feira ou supermercado, onde os centavos importam (`"apple"`, custando `0.50` em `Double`).
- **Produto 2:** Um ingresso de cinema, teatro ou jogo de futebol, onde o preço é sempre um número inteiro cheio (`"ticket"`, custando `15` em `Integer`).

Nesta **Parte 11** (minutos `10:51:10` a `10:51:39`), o Bro Code demonstra o ápice da utilidade de Generics:
1. Ele cria dois produtos simultâneos no mesmo método `main`:
   ```java
   Product<String, Double> product1 = new Product<>("apple", 0.50);
   Product<String, Integer> product2 = new Product<>("ticket", 15);
   ```
2. No meio do caminho, ele nos mostra **mais um erro proposital de tipo**: ao mudar o tipo de `product2` para `Integer`, mas manter o valor `0.50`, o IntelliJ grifou imediatamente de vermelho, provando que o Java fiscaliza os tipos de cada objeto individualmente!
3. Ele corrige para o ingresso de `15`, roda o programa e imprime:
   - `product2.getItem()` ➔ **`ticket`**
   - `product2.getPrice()` ➔ **`15`**

---

## 2. Reconstituição Visual Completa dos 5 Momentos do Vídeo (Google Docs)

> [!NOTE]
> Copie e cole os blocos de texto abaixo diretamente no seu documento do **Google Docs**. Você terá uma cópia idêntica da interface do IntelliJ IDEA exibida no vídeo pelo instrutor.

---

### Momento 1 (10:51:10): Duplicação de Linha e Aviso de Nome Repetido

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:51:10 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Main.java (ATIVO)]  [Editor: Product.java]  [Editor: Box.java]
📁 MyFirstProject C:\Users\HP...         -----------------------------------------------------------
  📁 .idea                               10: 
  📁 out                                 11:         Product<String, Double> product1 = new Product<>("apple", 0.50);
  📁 src                                 12:         Product<String, 🟦Double🟦> product1 = new Product<>("apple", 0.50);
    ☕ Box                                                                      ~~~~~~~~
    ☕ Main                                                           (Erro: 'product1' is already defined)
    ☕ Product                           13: 
  📄 .gitignore                          14:     }
  📄 MyFirstProject.iml                  15: }
====================================================================================================
```

---

### Momento 2 (10:51:15): O Teste de Erro — Tentando Colocar `0.50` num `Integer`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:51:15 / 12:00:00 - Chapter: 66.generics (ERRO DE TIPO)
====================================================================================================
[Project Tree]                           [Editor: Main.java (ATIVO)]
📁 src                                   -----------------------------------------------------------
  ☕ Box                                  11:         Product<String, Double> product1 = new Product<>("apple", 0.50);
  ☕ Main                                 12: ❗️      Product<String, Integer> product2 = new Product<>(🟦"apple"🟦, 0.50);
  ☕ Product                                                                                                        ~~~~
                                                                          (Erro: double cannot be converted to Integer!)
                                         13: 
                                         14:     }
                                         15: }
====================================================================================================
```

---

### Momento 3 (10:51:30): A Correção com `"ticket"` e Preço Inteiro `15`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:51:30 / 12:00:00 - Chapter: 66.generics (CORREÇÃO PERFEITA)
====================================================================================================
[Project Tree]                           [Editor: Main.java]                     [Barra Superior: ▶️ Run]
📁 src                                   -----------------------------------------------------------
  ☕ Box                                  11:         Product<String, Double> product1 = new Product<>("apple", 0.50);
  ☕ Main                                 12:         Product<String, Integer> product2 = new Product<>("ticket", 15);
  ☕ Product                             13: 
                                         14:         System.out.println(product2.getItem());
                                         15:     }
                                         16: }
                                         -----------------------------------------------------------
                                         (Instrutor clica no botão verde Play no canto superior direito)
====================================================================================================
```

---

### Momento 4 (10:51:32): Execução de `product2.getItem()` Imprime `ticket`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:51:32 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Main.java]
📁 src                                   -----------------------------------------------------------
  ☕ Box                                  12:         Product<String, Integer> product2 = new Product<>("ticket", 15);
  ☕ Main                                 13: 
  ☕ Product                             14:         System.out.println(product2.getItem());
                                         -----------------------------------------------------------
                                         [Run Tool Window: Main]
                                         -----------------------------------------------------------
                                         C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...
                                         🟦ticket🟦

                                         Process finished with exit code 0
====================================================================================================
```

---

### Momento 5 (10:51:39): Execução de `product2.getPrice()` Imprime `15`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:51:39 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Main.java]                     [Barra Superior: ▶️ Run]
📁 src                                   -----------------------------------------------------------
  ☕ Box                                  12:         Product<String, Integer> product2 = new Product<>("ticket", 15);
  ☕ Main                                 13: 
  ☕ Product                             14:         System.out.println(product2.getPrice());
                                         15:     }
                                         16: }
                                         -----------------------------------------------------------
                                         (Instrutor clica no Run novamente -> Saída no Console: 15)
====================================================================================================
```

---

## 3. Código-Fonte Completo dos Arquivos do Projeto

Aqui estão os códigos exatos sincronizados com a máquina local:

### [`Product.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Product.java)
```java
public class Product<T, U> {

    T item;
    U price;

    Product(T item, U price){
        this.item = item;
        this.price = price;
    }

    public T getItem(){
        return this.item;
    }

    public U getPrice(){
        return this.price;
    }
}
```

### [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
```java
public class Main {
    public static void main(String[] args) {

        // Generics = A concept where you can write a class, interface, or method
        //            that is compatible with different data types.
        //            <T> type parameter (placeholder that gets replaced with a real type)
        //            <String> type argument (specifies the type)

        Product<String, Double> product1 = new Product<>("apple", 0.50);
        Product<String, Integer> product2 = new Product<>("ticket", 15);

        System.out.println(product2.getItem());
        System.out.println(product2.getPrice());

    }
}
```

### [`Box.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Box.java)
```java
public class Box<T> {

    T item;

    public void setItem(T item){
        this.item = item;
    }

    public T getItem(){
        return this.item;
    }
}
```

---

## 4. Explicação Linha por Linha ("Como se Fosse um Lezado Iniciante Amador")

Vamos explicar cada linha do arquivo principal `Main.java` como se estivéssemos ensinando para quem nunca viu programação antes.

---

### Dissecando [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) Linha por Linha

```java
1: public class Main {
2:     public static void main(String[] args) {
3: 
4:         // Comentários conceituais sobre Generics...
5: 
9:         Product<String, Double> product1 = new Product<>("apple", 0.50);
10:        Product<String, Integer> product2 = new Product<>("ticket", 15);
11: 
12:        System.out.println(product2.getItem());
13:        System.out.println(product2.getPrice());
14: 
15:     }
16: }
```

- **Linha 9 (`Product<String, Double> product1 = new Product<>("apple", 0.50);`):**
  - `Product<String, Double>`: Cria a variável `product1`. Aqui dizemos: *"Neste produto 1, o item é um texto (`String`) e o preço é decimal (`Double`)"*.
  - `new Product<>("apple", 0.50)`: Aloca o primeiro objeto na memória. O Java empacota `"apple"` e `0.50`.

- **Linha 10 (`Product<String, Integer> product2 = new Product<>("ticket", 15);`):**
  - `Product<String, Integer>`: Cria uma **segunda variável** chamada `product2`. Mas olhe a mágica: aqui dizemos: *"Neste produto 2, o item é um texto (`String`), mas o preço NÃO tem centavos, ele é um número inteiro (`Integer`)"*.
  - `new Product<>("ticket", 15)`: Aloca um **segundo objeto completamente independente** na memória.
    - `"ticket"` é entregue para `T item`.
    - `15` é um primitivo `int`, que o Java transforma automaticamente em objeto `Integer` através do **Autoboxing**!

- **Linha 12 (`System.out.println(product2.getItem());`):**
  - Chama o método `getItem()` no objeto `product2`.
  - O método devolve o valor armazenado na gaveta `item` do `product2`, que é `"ticket"`.
  - O `System.out.println` imprime na primeira linha do console:
    ```text
    ticket
    ```

- **Linha 13 (`System.out.println(product2.getPrice());`):**
  - Chama o método `getPrice()` no objeto `product2`.
  - O método devolve o valor armazenado na gaveta `price` do `product2`, que é o número inteiro `15`.
  - O `System.out.println` imprime na segunda linha do console:
    ```text
    15
    ```

- **Linha 15 (`}`):**
  - Fecha o método `main`. O programa encerra sua tarefa com código de sucesso 0.

- **Linha 16 (`}`):**
  - Fecha a classe `Main`.

---

## 5. O Que Este Exemplo Revela Sobre Generics na Memória do Computador?

### Duas Variáveis, Tipos Internos Diferentes, Mesma Classe Mãe

Observe como o Java trabalha na memória:

```text
Stack (Variáveis Locais)              Heap (Objetos Criados)
┌───────────────────────┐             ┌────────────────────────────────────────────────────────┐
│ product1 ────────────┼────────────>│ Objeto Product #1:                                     │
│ (Product<String,Dbl>) │             │   item  = "apple"   (String)                           │
└───────────────────────┘             │   price = 0.5       (Double)                           │
                                      └────────────────────────────────────────────────────────┘
┌───────────────────────┐             ┌────────────────────────────────────────────────────────┐
│ product2 ────────────┼────────────>│ Objeto Product #2:                                     │
│ (Product<String,Int>) │             │   item  = "ticket"  (String)                           │
└───────────────────────┘             │   price = 15        (Integer)                          │
                                      └────────────────────────────────────────────────────────┘
```

Ambos os objetos foram construídos a partir da mesma receita de bolo: [`Product.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Product.java). Porém, no momento da fabricação, cada um recebeu ingredientes de tipos diferentes!

---

### Por que o compilador barrou o `0.50` no `product2`?

No Momento 2 do vídeo (minuto `10:51:15`), o Bro Code digitou:
```java
Product<String, Integer> product2 = new Product<>("apple", 0.50); // ❌ ERRO!
```

Por que isso gerou erro de compilação imediato?
1. Na declaração à esquerda, definimos `Product<String, Integer>`. Ou seja:
   - `T = String`
   - `U = Integer`
2. O construtor espera:
   ```java
   Product(String item, Integer price)
   ```
3. Passar `0.50` é passar um número de ponto flutuante (`double`).
4. Um `double` **não cabe** dentro de um `Integer`! Não existe conversão automática de número quebrado com vírgula para número inteiro sem perda de dados.
5. Por isso, o compilador travou na hora! Essa é a garantia de que você nunca vai colocar centavos por engano onde um sistema espera apenas números inteiros.

---

## 6. Tabela Comparativa de `product1` vs `product2`

| Propriedade | `product1` | `product2` |
| :--- | :---: | :---: |
| **Declaração de Tipos** | `Product<String, Double>` | `Product<String, Integer>` |
| **O que a letra `T` virou?** | `String` | `String` |
| **O que a letra `U` virou?** | `Double` | `Integer` |
| **Dado do `item`** | `"apple"` | `"ticket"` |
| **Dado do `price`** | `0.50` (decimal) | `15` (inteiro) |
| **Retorno de `getItem()`** | `String` (`"apple"`) | `String` (`"ticket"`) |
| **Retorno de `getPrice()`** | `Double` (`0.5`) | `Integer` (`15`) |

---

## 7. Tabela de Saída do Console e Rastreio de Execução Passo a Passo

Abaixo está o teste exato executado na máquina local via terminal:

| Passo de Execução | Instrução Executada | O que a JVM Faz | O que Aparece no Console? |
| :---: | :--- | :--- | :---: |
| **1** | `Product<String, Double> product1 = new Product<>("apple", 0.50);` | Instancia o produto da maçã no Heap com preço `Double(0.5)`. | *(nada)* |
| **2** | `Product<String, Integer> product2 = new Product<>("ticket", 15);` | Instancia o produto do ingresso no Heap com preço `Integer(15)`. | *(nada)* |
| **3** | `System.out.println(product2.getItem());` | Executa o getter `getItem()`, busca o texto `"ticket"` e joga no console. | **`ticket`** |
| **4** | `System.out.println(product2.getPrice());` | Executa o getter `getPrice()`, busca o número inteiro `15` e joga no console. | **`15`** |

---

## 8. Conclusão e Próximos Passos

Com esta demonstração, encerramos com nota 10 a parte de **Classes Genéricas com Múltiplos Parâmetros**:
- Aprendemos a criar classes com 1 tipo genérico (`Box<T>`).
- Aprendemos a criar classes com 2 tipos genéricos (`Product<T, U>`).
- Vimos como instanciar objetos independentes usando a mesma classe genérica sem mexer em uma única linha dela.
- Provamos como o compilador Java impede misturas indevidas de tipos em tempo de compilação.

No próximo bloco do vídeo do Bro Code, avançaremos para o próximo grande tópico de Generics: **Métodos Genéricos (*Generic Methods*) e Bounded Types (*Tipos Delimitados com `extends`*)**!
