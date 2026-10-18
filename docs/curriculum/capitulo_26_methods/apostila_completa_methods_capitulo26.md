# 📞 Capítulo 26: O Guia Definitivo e Completo de Métodos em Java (Methods) — Partes 1 a 17

> **Documento Oficial de Estudos para Cópia no Google Docs:** Este material foi estruturado para ser **100% autossuficiente**. Ele descreve todos os contextos visuais das aulas do canal Bro Code (*Java Full Course for free*), inclui diagramas de chamada telefônica, a metáfora da persiana, a diferença entre argumentos e parâmetros, métodos com `return`, chamadas aninhadas, mapas de memória Stack e códigos Java compiláveis sem erros.

---

## 📑 Índice da Apostila
1. [Visão Geral: O Que É um Método e a Metáfora do Telefone 📞](#1-visão-geral)
2. [O Princípio DRY (*Don't Repeat Yourself*) e Reutilização](#2-o-princípio-dry)
3. [O Meme da Persiana e o Erro `cannot find symbol`](#3-o-meme-da-persiana)
4. [Argumentos vs. Parâmetros: A Diferença Crucial](#4-argumentos-vs-parâmetros)
5. [Múltiplos Parâmetros, Ordem dos Tipos e Nomes Diferentes](#5-múltiplos-parâmetros)
6. [Métodos com Retorno de Valores (`return`) vs. Métodos `void`](#6-retorno-de-valores-return)
7. [Métodos Matemáticos e Chamadas Aninhadas (`square` e `cube`)](#7-métodos-matemáticos)
8. [Métodos com Retorno de Texto (`getFullName`)](#8-retorno-de-texto)
9. [Métodos Booleanos e a Refatoração Ninja de 1 Linha (`ageCheck`)](#9-métodos-booleanos)
10. [Mapa Mental de Memória RAM: Empilhamento na Stack](#10-mapa-mental-de-memória-ram)
11. [Código-Fonte Completo e Compilável de Todo o Capítulo 26](#11-código-fonte-completo)

---

<a name="1-visão-geral"></a>
## 📞 1. Visão Geral: O Que É um Método e a Metáfora do Telefone

O autor abre o capítulo com o emoji de telefone 📞 no título. Por quê?
Porque um método funciona exatamente como uma **chamada telefônica**:
> **Método (*Method*):** É um bloco de código pré-programado que fica guardado quieto na memória. Ele **só executa** quando alguém "disca" o seu nome seguido de parênteses `()`!

```text
       MÉTODO MAIN                              MÉTODO HAPPYBIRTHDAY
┌─────────────────────────┐                   ┌─────────────────────────┐
│ System.out.println(...);│                   │                         │
│ happyBirthday(); ───────┼─── Disca o número ┼─► Imprime os parabéns   │
│                         │   da chamada!     │                         │
│ ◄───────────────────────┼───────────────────┤ Fim do método (desliga) │
│ Continua o programa...  │                   └─────────────────────────┘
└─────────────────────────┘
```

* **Declaração:** Escrever o método é como cadastrar um contato na agenda do celular.
* **Chamada:** Digitar `happyBirthday();` é apertar o botão verde de ligar.
* **Os Parênteses `()`:** São o botão de chamada. Sem parênteses, o Java não executa a ação!

---

<a name="2-o-princípio-dry"></a>
## 🔁 2. O Princípio DRY (*Don't Repeat Yourself*) e Reutilização

Nas Partes 1 e 2, o autor cria uma canção de aniversário:
```java
static void happyBirthday() {
    System.out.println("Happy birthday to you!");
    System.out.println("Happy birthday to you!");
    System.out.println("Happy birthday dear you!");
    System.out.println("Happy birthday to you!\n");
}
```

Imagine que você precisa cantar parabéns para 3 pessoas na mesma festa:
* **Sem métodos (Jeito Amador):** Você teria que copiar e colar as 4 linhas três vezes (12 linhas de código). Se quisesse mudar a letra da música, teria que alterar em 3 lugares diferentes!
* **Com métodos (Princípio DRY):**
  ```java
  public static void main(String[] args) {
      happyBirthday(); // Canta 1ª vez
      happyBirthday(); // Canta 2ª vez
      happyBirthday(); // Canta 3ª vez
  }
  ```
* **Vantagem:** O código fica 3 vezes menor, e se precisar mudar a música, você altera em **um único lugar**!

---

<a name="3-o-meme-da-persiana"></a>
## 🙈 3. O Meme da Persiana e o Erro `cannot find symbol`

Nas Partes 3 e 4, o autor comete um erro intencional muito comum entre iniciantes:
```java
public static void main(String[] args) {
    String name = "Bro";
    happyBirthday();
}

static void happyBirthday() {
    System.out.println("Happy birthday dear " + name); // ERRO!
}
```

### 🚫 A Explosão no Compilador:
```text
java: cannot find symbol
  symbol:   variable name
  location: class Main
```

### 🪟 O Meme do Homem Espiando Pela Persiana:
O autor coloca na tela o famoso meme do homem tentando espiar pela fresta da persiana fechada:
* O método `happyBirthday` é um quarto fechado com cortinas pretas.
* Ele **não consegue enxergar** o que está dentro do quarto `main`!
* Uma variável criada dentro do `main` pertence **apenas** ao `main`. Para que o `happyBirthday` saiba o nome da pessoa, o `main` precisa **entregar o valor em mãos** através de **parâmetros**!

---

<a name="4-argumentos-vs-parâmetros"></a>
## 📦 4. Argumentos vs. Parâmetros: A Diferença Crucial

Esta é uma das perguntas mais cobradas em entrevistas de emprego:

| Termo | Onde Fica? | O Que É? | Analogia |
| :--- | :--- | :--- | :--- |
| **Argumento (*Argument*)** | Na **chamada** do método (dentro do `main`) | O valor real concreto enviado | A **carta** que você posta no correio |
| **Parâmetro (*Parameter*)** | No **cabeçalho** do método (`static void ...`) | A variável receptora que guarda o valor | A **caixa de correio** que recebe a carta |

```java
// O "Bro" é o ARGUMENTO (o dado real sendo enviado):
happyBirthday("Bro");

// O "String name" é o PARÂMETRO (a variável que recebe o dado):
static void happyBirthday(String name) {
    System.out.println("Happy birthday dear " + name);
}
```

---

<a name="5-múltiplos-parâmetros"></a>
## 🔢 5. Múltiplos Parâmetros, Ordem dos Tipos e Nomes Diferentes

Nas Partes 7 a 10, o autor adiciona a idade do aniversariante:
```java
public static void main(String[] args) {
    String name = "Spongebob";
    int age = 30;

    happyBirthday(name, age); // Passa 2 argumentos
}

static void happyBirthday(String name, int age) {
    System.out.println("Happy birthday to you!");
    System.out.println("Happy birthday dear " + name);
    System.out.println("You are " + age + " years old!\n");
}
```

### ⚠️ Regras Sagradas dos Parâmetros Múltiplos:
1. **A Ordem Importa Rigorosamente:** Se o método espera `(String, int)`, você não pode passar `(30, "Spongebob")`. O Java não tenta adivinhar e gera erro de tipo incompatível!
2. **A Quantidade Precisa Ser Idêntica:** Passar 1 argumento para um método de 2 parâmetros gera erro imediato de compilação.
3. **Os Nomes Podem Ser Diferentes:** No `main` a variável pode se chamar `name`, e no método ela pode se chamar `birthdayBoy`. O Java só se importa com o **valor** e com o **tipo**!

---

<a name="6-retorno-de-valores-return"></a>
## 🔄 6. Métodos com Retorno de Valores (`return`) vs. Métodos `void`

Até aqui, todos os métodos tinham a palavra **`void`**.
* **`void` (Vazio):** Significa que o método faz um trabalho e **vai embora de mãos vazias**. Ele não entrega nenhuma resposta matemática para quem o chamou.

Mas e se precisarmos de um método que faça uma conta e **devolva o resultado** para usarmos em outras partes do programa?
Usamos o comando **`return`**!

### 🔁 Trocando `void` pelo Tipo de Devolução:
```java
// Em vez de "void", colocamos o tipo que o método DEVOLVE (double):
static double square(double number) {
    return number * number; // Devolve o resultado da conta!
}
```

---

<a name="7-métodos-matemáticos"></a>
## 📐 7. Métodos Matemáticos e Chamadas Aninhadas (`square` e `cube`)

Nas Partes 11 a 14, o autor implementa o cálculo do quadrado ($x^2$) e do cubo ($x^3$):

### 7.1 O Método `square` (Elevar ao Quadrado):
```java
public class Main {
    public static void main(String[] args) {

        double x = 3.0;

        // Opção A: Guardar o retorno em uma variável
        double result = square(x);
        System.out.println(result); // Imprime: 9.0

        // Opção B: Chamada Aninhada (Direto dentro do println!)
        System.out.println(square(x)); // Imprime: 9.0
    }

    static double square(double number) {
        return number * number;
    }
}
```

### 7.2 O Método `cube` (Elevar ao Cubo):
```java
static double cube(double number) {
    return number * number * number;
}
```
Se passarmos `3.0`: $3 \times 3 \times 3 = \mathbf{27.0}$!

---

<a name="8-retorno-de-texto"></a>
## 🔤 8. Métodos com Retorno de Texto (`getFullName`)

Na Parte 15, o autor mostra que métodos podem retornar qualquer tipo, inclusive `String`:
```java
public class Main {
    public static void main(String[] args) {

        String fullName = getFullName("Spongebob", "Squarepants");
        System.out.println(fullName); // Imprime: Spongebob Squarepants
    }

    static String getFullName(String first, String last) {
        return first + " " + last;
    }
}
```
* O método recebe o primeiro nome e o sobrenome, junta os dois com um espaço no meio e devolve a frase pronta!

---

<a name="9-métodos-booleanos"></a>
## 🚦 9. Métodos Booleanos e a Refatoração Ninja de 1 Linha (`ageCheck`)

Nas Partes 16 e 17, o autor ensina a verificar se uma pessoa é maior de idade:

### 9.1 A Versão Inicial com `if-else`:
```java
static boolean ageCheck(int age) {
    if (age >= 18) {
        return true;
    }
    else {
        return false;
    }
}
```

### 🥷 9.2 A Refatoração Ninja de 1 Linha:
O autor mostra que a expressão `age >= 18` **já é uma pergunta que resulta em `true` ou `false`**! Portanto, você pode retornar a própria expressão diretamente:
```java
static boolean ageCheck(int age) {
    return age >= 18; // Genial! Uma única linha limpa!
}
```

### 9.3 Usando o Método Booleano Direto Dentro de um `if`:
```java
public static void main(String[] args) {
    int age = 21;

    if (ageCheck(age)) {
        System.out.println("You may enter!");
    }
    else {
        System.out.println("You must be 18+ to enter");
    }
}
```

---

<a name="10-mapa-mental-de-memória-ram"></a>
## 🧠 10. Mapa Mental de Memória RAM: Empilhamento na Stack

Veja como a memória do computador funciona durante uma chamada com `return`:

```text
1. O main empilha suas variáveis:
   ┌────────────────────────────────┐
   │ FRAME: main                    │
   │ - x = 3.0                      │
   └────────────────────────────────┘

2. Ao chamar square(x), um novo frame nasce NO TOPO:
   ┌────────────────────────────────┐
   │ FRAME: square                  │
   │ - number = 3.0                 │
   │ - return 9.0                   │
   ├────────────────────────────────┤
   │ FRAME: main (aguarda retorno)  │
   │ - x = 3.0                      │
   └────────────────────────────────┘

3. O método square devolve 9.0 e seu frame é DESTRUÍDO:
   ┌────────────────────────────────┐
   │ FRAME: main                    │
   │ - x = 3.0                      │
   │ - result = 9.0                 │
   └────────────────────────────────┘
```

---

<a name="11-código-fonte-completo"></a>
## 💻 11. Código-Fonte Completo e Compilável de Todo o Capítulo 26

Este código reúne todas as funções aprendidas no capítulo para você testar no seu IntelliJ IDEA:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("=== 1. Teste de Método void com Parâmetros ===");
        happyBirthday("Spongebob", 30);
        happyBirthday("Patrick", 35);

        System.out.println("=== 2. Teste de Retorno Numérico (square e cube) ===");
        double num = 3.0;
        System.out.println("O quadrado de " + num + " é: " + square(num));
        System.out.println("O cubo de " + num + " é: " + cube(num));

        System.out.println("\n=== 3. Teste de Retorno de Texto (getFullName) ===");
        String user = getFullName("Squidward", "Tentacles");
        System.out.println("Nome completo: " + user);

        System.out.println("\n=== 4. Teste de Retorno Booleano (ageCheck) ===");
        int userAge = 21;
        if (ageCheck(userAge)) {
            System.out.println("Acesso liberado! Você tem " + userAge + " anos.");
        }
        else {
            System.out.println("Acesso negado! Idade insuficiente.");
        }
    }

    // 1. Método void (não devolve nada, apenas imprime)
    static void happyBirthday(String name, int age) {
        System.out.println("Happy birthday to you!");
        System.out.println("Happy birthday dear " + name);
        System.out.println("You are " + age + " years old!\n");
    }

    // 2. Método com retorno double (elevar ao quadrado)
    static double square(double number) {
        return number * number;
    }

    // 3. Método com retorno double (elevar ao cubo)
    static double cube(double number) {
        return number * number * number;
    }

    // 4. Método com retorno String (concatenar nome e sobrenome)
    static String getFullName(String first, String last) {
        return first + " " + last;
    }

    // 5. Método com retorno boolean (refatorado em 1 linha)
    static boolean ageCheck(int age) {
        return age >= 18;
    }
}
```

---

## 🖥️ Saída Exata Gerada no Console

```text
=== 1. Teste de Método void com Parâmetros ===
Happy birthday to you!
Happy birthday dear Spongebob
You are 30 years old!

Happy birthday to you!
Happy birthday dear Patrick
You are 35 years old!

=== 2. Teste de Retorno Numérico (square e cube) ===
O quadrado de 3.0 é: 9.0
O cubo de 3.0 é: 27.0

=== 3. Teste de Retorno de Texto (getFullName) ===
Nome completo: Squidward Tentacles

=== 4. Teste de Retorno Booleano (ageCheck) ===
Acesso liberado! Você tem 21 anos.

Process finished with exit code 0
```

---

Prontinho! Esta apostila definitiva consolida e aprofunda tudo sobre **Methods**, cobrindo desde a primeira ligação do telefone até funções com retorno, chamadas aninhadas e refatoração ninja, 100% pronta para o seu Google Docs! 📞📘✨
