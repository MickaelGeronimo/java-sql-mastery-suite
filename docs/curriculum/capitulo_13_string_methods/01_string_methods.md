# 🧵 Capítulo 13: `String Methods` (Métodos de Texto em Java) — Parte 1
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. Boas-vindas ao Capítulo 13: O Que São "String Methods"?

Em Java, uma **`String`** é uma sequência de caracteres (um texto entre aspas duplas, como `"Bro Code"` ou `"Mickel"`).

Mas aqui está o grande segredo que você precisa saber:
* Tipos primitivos como `int` ou `double` são apenas caixinhas simples que guardam um número.
* Já a **`String`** começa com **letra maiúscula** (`String`) porque ela é um **Objeto**!
* Por ser um objeto, ela vem de fábrica com um "cinto de utilidades" cheio de ferramentas mágicas embutidas chamadas **métodos**!

> [!NOTE]
> Para ativar qualquer uma dessas ferramentas mágicas, basta colocar um **ponto final `.`** depois do nome da variável:  
> `name.length()`, `name.charAt()`, `name.indexOf()`, etc.

---

## 📏 2. A Régua dos Índices: O Computador Começa a Contar do ZERO!

Esta é a regra mais importante de toda a computação moderna:  
**O ser humano começa a contar do 1. O computador SEMPRE começa a contar do 0!**

Pense na palavra `"Bro Code"` como se fossem caixinhas de um armário numeradas:

```text
Índice (Posição):   0     1     2     3     4     5     6     7
                  ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐
Caractere:        │ B │ │ r │ │ o │ │   │ │ C │ │ o │ │ d │ │ e │
                  └───┘ └───┘ └───┘ └───┘ └───┘ └───┘ └───┘ └───┘
                                      ▲
                         O espaço em branco também conta!
```

### ⚠️ Dois detalhes fundamentais para nunca mais esquecer:
1. **O espaço em branco conta como caractere!** Na posição `3` nós temos o espaço entre `"Bro"` e `"Code"`.
2. **Tamanho vs. Último Índice:**  
   O texto tem **8 caracteres no total** (comprimento 8).  
   Mas como começamos do zero, o último caractere (`e`) está na **posição 7**!

---

## 🛠️ 3. Os 4 Métodos que o Professor Ensinou

---

### 🟢 Método 1: `name.length()` — Medindo o Tamanho Total
* **O que a palavra significa:** *Length* em inglês significa **comprimento** ou tamanho.
* **O que ele faz:** Conta quantas letras e símbolos existem no texto.
* **Código:**
  ```java
  int length = name.length();
  System.out.println(length); // Imprime: 8
  ```
* **Tipo retornado:** Retorna um número inteiro (`int`), porque você não pode ter "7 letras e meia".

---

### 🟢 Método 2: `name.charAt(índice)` — Buscando a Letra na Posição
* **O que a palavra significa:** *Char* (caractere) + *At* (na posição). Significa: *"Qual é o caractere **na posição X**?"*.
* **O que ele faz:** Você entrega o número da posição, e ele te devolve a letrinha que mora lá.
* **Código:**
  ```java
  char letter = name.charAt(0);
  System.out.println(letter); // Imprime: B
  ```
* **Exemplos práticos:**
  * `name.charAt(0)` devolve `'B'` (a primeira letra).
  * `name.charAt(1)` devolve `'r'`.
  * `name.charAt(3)` devolve `' '` (o espaço em branco).
* **Tipo retornado:** Retorna um `char` (uma única letrinha entre aspas simples).

---

### 🟢 Método 3: `name.indexOf(texto)` — Onde Fica a PRIMEIRA Ocorrência?
* **O que a palavra significa:** *Index* (índice/posição) + *Of* (de). Significa: *"Qual o índice **do** caractere tal?"*.
* **O que ele faz:** É o **inverso** do `charAt`! Você entrega a letra, e ele procura onde ela aparece **pela primeira vez**, lendo da esquerda para a direita.
* **Teste 1:** Procurando a letra `"o"`:
  ```java
  int index = name.indexOf("o");
  System.out.println(index); // Imprime: 2
  ```
  *(O primeiro "o" em `"Bro Code"` mora na gaveta de número 2).*

* **Teste 2:** Procurando o espaço em branco `" "`:
  ```java
  int index = name.indexOf(" ");
  System.out.println(index); // Imprime: 3
  ```
  *(O espaço em branco mora na gaveta de número 3).*

* **E se a letra NÃO existir no texto?**  
  Se você procurar por `"z"` (`name.indexOf("z")`), o Java devolve **`-1`**!  
  `-1` é o código universal do Java para dizer: *"Procurei em tudo e não encontrei nada!"*.

---

### 🟢 Método 4: `name.lastIndexOf(texto)` — Onde Fica a ÚLTIMA Ocorrência?
* **O que a palavra significa:** *Last* (último) + *Index of* (índice de).
* **O que ele faz:** Repare que na palavra `"Bro Code"`, a letra `"o"` aparece **duas vezes**:
  * O primeiro `"o"` está na posição `2`.
  * O segundo `"o"` está lá no `"Code"`, na posição `5`.
* Enquanto o `indexOf("o")` acha o **primeiro** (posição 2), o `lastIndexOf("o")` procura de trás para frente e acha o **último** (posição 5)!
* **Código:**
  ```java
  int lastIndex = name.lastIndexOf("o");
  System.out.println(lastIndex); // Imprime: 5
  ```

---

## 🔬 4. Explicação Linha por Linha de Todo o Código

Vamos olhar linha por linha do programa completo:

* **`public class Main {`**  
  Abre a classe principal chamada `Main`.
* **`public static void main(String[] args) {`**  
  O ponto de partida da execução do Java.
* **`String name = "Bro Code";`**  
  Cria a variável de texto `name` guardando a palavra `"Bro Code"`.
* **`int length = name.length();`**  
  Chama o método `.length()` para contar os caracteres (são 8) e guarda o número 8 na variável `int length`.
* **`char letter = name.charAt(0);`**  
  Chama o `.charAt(0)` para pegar o caractere da gaveta 0 (`'B'`) e guarda na variável `char letter`.
* **`int index = name.indexOf(" ");`**  
  Chama o `.indexOf(" ")` para descobrir onde está o espaço em branco (posição 3) e guarda na variável `int index`.
* **`int lastIndex = name.lastIndexOf("o");`**  
  Chama o `.lastIndexOf("o")` para achar o último "o" da frase (posição 5) e guarda na variável `int lastIndex`.
* **`System.out.println(index);`**  
  Imprime no console o valor guardado na variável `index` (mostra o número `3`).
* **`}`** $\rightarrow$ Fecha o método `main`.
* **`}`** $\rightarrow$ Fecha a classe `Main`.

---

## 📊 5. Tabela Comparativa dos 4 Métodos

| Método | Para que serve? | Exemplo com `"Bro Code"` | O que ele devolve? | Tipo |
| :--- | :--- | :---: | :---: | :---: |
| **`.length()`** | Conta o total de caracteres | `name.length()` | `8` | `int` |
| **`.charAt(0)`** | Pega a letra pelo número da gaveta | `name.charAt(0)` | `'B'` | `char` |
| **`.indexOf("o")`** | Acha a **1ª** posição da letra | `name.indexOf("o")` | `2` | `int` |
| **`.lastIndexOf("o")`** | Acha a **última** posição da letra | `name.lastIndexOf("o")` | `5` | `int` |

---

## 💻 6. Código Completo para Você Copiar e Testar no IntelliJ

Copie e cole este código no seu `Main.java` para ver todos os 4 métodos imprimindo seus resultados juntos:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 13: String methods (Métodos de Texto)

        String name = "Bro Code";

        // 1. Tamanho total do texto
        int length = name.length();

        // 2. Caractere na primeira gaveta (posição 0)
        char letter = name.charAt(0);

        // 3. Primeira posição onde aparece a letra 'o'
        int firstO = name.indexOf("o");

        // 4. Posição onde fica o espaço em branco
        int spaceIndex = name.indexOf(" ");

        // 5. Última posição onde aparece a letra 'o'
        int lastO = name.lastIndexOf("o");

        // Imprimindo todos os testes:
        System.out.println("Tamanho total: " + length);         // 8
        System.out.println("Primeira letra: " + letter);        // B
        System.out.println("Primeiro 'o' está em: " + firstO);  // 2
        System.out.println("Espaço está em: " + spaceIndex);    // 3
        System.out.println("Último 'o' está em: " + lastO);     // 5
    }
}
```
