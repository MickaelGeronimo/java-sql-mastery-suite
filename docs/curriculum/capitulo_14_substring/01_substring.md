# 🧵 Capítulo 14: `substring()` — Fatiando Textos em Java (Parte 1)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. Boas-vindas ao Capítulo 14: O Que é uma `substring`?

A palavra **`substring`** é a união de duas palavras:
* **`Sub`** = Menor, parte de algo, pedaço (como em *subsolo*, *subdivisão* ou *submarino*).
* **`String`** = Texto.

Portanto, uma **`substring`** é simplesmente:
> **Um pedaço (uma fatia) recortado de dentro de um texto maior!**

Pense num bolo inteiro de aniversário. A palavra inteira é o bolo.  
O método **`.substring()`** é a faquinha que você usa para cortar apenas a fatia que você quer comer!

---

## ✂️ 2. Como Funciona a Faquinha: `.substring(início, fim)`

O método recebe dois números inteiros dentro dos parênteses:
```java
texto.substring(start, end);
```
* **`start` (início):** Onde o corte começa.
* **`end` (fim):** Onde o corte termina.

> [!IMPORTANT]
> ### 🚨 A REGRA DE OURO DO SUBSTRING (A Maior Pegadinha de Java):
> * O número de **início (`start`)** é **INCLUÍDO** na fatia.
> * O número de **fim (`end`)** é **EXCLUÍDO** (ele para exatamente ANTES desse número!).
>
> **A analogia da tesoura:** Imagine que você coloca a tesoura na linha do número `end` e passa a lâmina. O caractere que estava na gaveta `end` fica para trás, ele **NÃO** entra na sua fatia!
>
> $$\text{Fatia} = \text{vai do índice } start \text{ até o índice } (end - 1)$$

---

## 📏 3. A Régua dos Índices do E-mail `"Bro123@gmail.com"`

Para nunca mais errar, desenhe sempre a régua das gavetas numeradas começando do **ZERO**:

```text
Índice:     0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15
          ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
Letra:    │ B │ r │ o │ 1 │ 2 │ 3 │ @ │ g │ m │ a │ i │ l │ . │ c │ o │ m │
          └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
          ▲                   ▲   ▲   ▲                               ▲
          │   [ username ]    │   │   │         [ domain ]            │
          0 ───────────────── 5   6   7 ───────────────────────────── 15
```

O texto completo tem **16 caracteres no total** (índices de 0 a 15).

---

## 🍰 4. Os Dois Recortes Feitos pelo Professor

O objetivo era pegar o e-mail `"Bro123@gmail.com"` e separar em duas partes:
1. O **nome de usuário** (*username*): `"Bro123"`
2. O **domínio** do provedor (*domain*): `"gmail.com"`

---

### 🔹 Recorte 1: Pegando o Usuário (`email.substring(0, 6)`)
```java
String username = email.substring(0, 6);
```
* **Começa no `0`:** Pega a letra `'B'`.
* **Termina no `6`:** Pára **antes** do índice `6`.  
  O índice `6` é o símbolo `'@'`. Como o `6` fica de fora, o corte pega os índices: `0, 1, 2, 3, 4, 5`.
* **Resultado:** **`"Bro123"`**! Cortou perfeitamente sem levar o `@` junto!

---

### 🔹 Recorte 2: Pegando o Domínio (`email.substring(7, 16)`)
```java
String domain = email.substring(7, 16);
```
* **Começa no `7`:** Pega a letra `'g'` (logo depois do `@`).
* **Termina no `16`:** Pára antes do `16`.  
  A última letra `'m'` está na gaveta `15`. Para que a gaveta `15` seja incluída, temos que pedir para parar na gaveta imaginária `16`!
* **Resultado:** **`"gmail.com"`**!

Quando o professor mandou imprimir:
```java
System.out.println(domain);
```
O console mostrou com perfeição:
```text
gmail.com
```

---

## 🔬 5. Explicação ENUMERADA Linha por Linha de Todo o Código

* **Linha 1 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de partida onde o Java começa a rodar o programa.
* **Linha 5 (`// .substring() = A method used to extract a portion of a string`):**  
  Comentário explicativo em inglês: *"Um método usado para extrair uma porção de um texto"*.
* **Linha 6 (`//               .substring(start, end)`):**  
  Comentário mostrando a receita: precisa do índice de início (`start`) e de fim (`end`).
* **Linha 8 (`String email = "Bro123@gmail.com";`):**  
  Cria a caixinha de texto `email` guardando o endereço de e-mail completo.
* **Linha 9 (`String username = email.substring(0, 6);`):**  
  Recorta da posição 0 até a 5 (antes do 6) e guarda o texto `"Bro123"` na variável `username`.
* **Linha 10 (`String domain = email.substring(7, 16);`):**  
  Recorta da posição 7 até a 15 (antes do 16) e guarda o texto `"gmail.com"` na variável `domain`.
* **Linha 12 (`System.out.println(domain);`):**  
  Imprime no console a variável `domain` (saiu `gmail.com`).
* **Linha 13 (`}`):**  
  Fecha o método `main`.
* **Linha 14 (`}`):**  
  Fecha a classe `Main`.

---

## 💡 6. Onde Você Usa o `.substring()` na Vida Real?

1. **Formatadores de Documentos (CPF / Telefone):**  
   Se o usuário digita `11999887766`, você usa `substring` para pegar o DDD `(11)` e separar o número.
2. **Máscaras de Cartão de Crédito:**  
   Para mostrar apenas os 4 últimos dígitos do cartão: `cartao.substring(12, 16)` $\rightarrow$ `**** **** **** 1234`.
3. **Datas e Horários:**  
   Numa data `"2026-09-29"`, você pega o ano com `data.substring(0, 4)`.

---

## 💻 7. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste imprimir tanto o `username` quanto o `domain`:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 14: Fatiando textos com substring(start, end)

        String email = "Bro123@gmail.com";

        // 1. Pega do índice 0 até o 5 (para antes do 6)
        String username = email.substring(0, 6);

        // 2. Pega do índice 7 até o 15 (para antes do 16)
        String domain = email.substring(7, 16);

        // Mostrando os dois pedaços separados:
        System.out.println("Nome de usuário: " + username); // Bro123
        System.out.println("Domínio do e-mail: " + domain); // gmail.com
    }
}
```
