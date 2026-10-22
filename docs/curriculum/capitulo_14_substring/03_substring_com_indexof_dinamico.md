# 🧵 Capítulo 14: `substring()` Dinâmico com `indexOf()` — Parte 3
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Grande Salto de Mestre: Adeus Contar no Dedo!

Nas etapas anteriores, a gente estava fazendo uma coisa muito amadora: contando as letrinhas nos dedos para adivinhar os números das gavetas (`0, 8` e `9`).  
Se o e-mail mudasse para `"ana@gmail.com"`, o código quebrava!

Agora o professor fez a mágica que todo programador profissional faz:  
👉 **Ele juntou dois métodos que aprendemos: o `.substring()` com o `.indexOf()`!**

Em vez de você colocar um número fixo, o próprio Java **caça onde está o `@`** e passa essa gaveta para o `substring` automaticamente!

---

## 🧩 2. Como o Java Pensa: Desmontando o Quebra-Cabeça

Olhe para a nova linha 9 escrita pelo professor:

```java
String username = email.substring(0, email.indexOf("@"));
```

Parece comprido e assustador, né? Mas veja como o Java resolve isso de dentro para fora, como uma continha de matemática com parênteses:

### 🔍 Passo 1: O Java resolve o que está lá dentro:
O Java olha para:
$$\text{email.indexOf("@")}$$
Ele varre a palavra `"BroCode1@gmail.com"` e pergunta: *"Onde mora o arroba `@`?"*.  
Na nossa régua, o `@` mora exatamente na **gaveta 8**!  
Então o Java troca aquele pedaço de código pelo número **`8`**!

### 🔍 Passo 2: A linha vira o código simples que já conhecemos:
```java
String username = email.substring(0, 8);
```
* Começa na gaveta `0`.
* Para antes da gaveta `8` (onde está o `@`).
* Pega perfeitamente: **`"BroCode1"`**!

> [!TIP]
> **A Mágica da Automação:**  
> Se o e-mail fosse `"ana@gmail.com"`, o `email.indexOf("@")` viraria `3` sozinho!  
> Se o e-mail fosse `"pedro_silva_1990@gmail.com"`, ele acharia o `@` sozinho!  
> Funciona para **QUALQUER E-MAIL DO MUNDO** sem você precisar alterar uma única linha de código!

---

## 🧐 3. A Linha 10: Por que no Console saiu `@gmail.com`?

Olhe como o professor escreveu a linha do domínio:

```java
String domain = email.substring(email.indexOf("@"));
```

Vamos desmontar essa linha também:
1. `email.indexOf("@")` devolve o número **`8`**.
2. A linha vira: `email.substring(8);`
3. Lembra da regra de ouro do `substring`?
   * **O número de início é INCLUÍDO na fatia!**
4. E o que mora exatamente na gaveta `8`? O próprio símbolo do arroba: **`'@'`**!
5. O Java começou no `@` e pegou tudo até o fim!

Por isso, no console do professor saiu:
```text
@gmail.com
```
*(O arroba veio de penetra grudado no início do domínio!)*

> [!NOTE]
> **Como tirar esse arroba penetra?**  
> Para começar uma gaveta depois do arroba, basta somar `+ 1`:  
> `email.substring(email.indexOf("@") + 1);`  
> Começando na gaveta $8 + 1 = 9$, sai apenas `"gmail.com"` perfeitamente!

---

## 🔬 4. Explicação ENUMERADA Linha por Linha de Todo o Código

* **Linha 1 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de entrada de execução do programa Java.
* **Linha 5 (`// .substring() = A method used to extract a portion of a string`):**  
  Comentário explicando o método `substring`.
* **Linha 6 (`//               .substring(start, end)`):**  
  Comentário lembrando a fórmula do método.
* **Linha 8 (`String email = "BroCode1@gmail.com";`):**  
  Cria a caixinha `email` guardando o endereço de e-mail.
* **Linha 9 (`String username = email.substring(0, email.indexOf("@"));`):**  
  **O Recorte Inteligente do Usuário:** O `indexOf("@")` acha a gaveta 8. O `substring(0, 8)` pega tudo desde o início até antes do arroba, guardando `"BroCode1"` na variável `username`.
* **Linha 10 (`String domain = email.substring(email.indexOf("@"));`):**  
  **O Recorte do Domínio:** O `indexOf("@")` acha a gaveta 8. O `substring(8)` corta da gaveta 8 (o arroba) até o finalzinho, guardando `"@gmail.com"` na variável `domain`.
* **Linha 12 (`System.out.println(domain);`):**  
  Imprime no console a variável `domain` (saiu `@gmail.com`).
* **Linha 13 (`}`):**  
  Fecha o método `main`.
* **Linha 14 (`}`):**  
  Fecha a classe `Main`.

---

## 📊 5. Comparativo: Código Fixo (Amador) vs. Código Dinâmico (Profissional)

| Tipo de Código | Como é escrito? | O que acontece se o e-mail mudar? |
| :--- | :--- | :--- |
| **Fixo (Amador)** | `email.substring(0, 8);` | **Quebra!** Se o e-mail tiver tamanho diferente, pega letras a mais ou a menos. |
| **Dinâmico (Profissional)** | `email.substring(0, email.indexOf("@"));` | **Perfeito!** O Java localiza o `@` automaticamente para qualquer e-mail do planeta! |

---

## 💻 6. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste com vários e-mails diferentes (curtos ou compridos) para ver a inteligência da automação:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 14: substring dinâmico usando indexOf

        String email = "BroCode1@gmail.com";

        // Pega do início (0) até antes do '@' automaticamente:
        String username = email.substring(0, email.indexOf("@"));

        // Pega a partir do '@' até o final:
        String domain = email.substring(email.indexOf("@"));

        System.out.println("Nome de usuário: " + username); // BroCode1
        System.out.println("Domínio: " + domain);           // @gmail.com
    }
}
```
