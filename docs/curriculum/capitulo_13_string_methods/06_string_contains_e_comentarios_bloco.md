# 🧵 Capítulo 13: `String Methods` — Parte 6 (`contains` e Comentários de Bloco `/* */`)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Adicionou de Novo?

Nesta cena, o professor nos apresentou duas coisas fantásticas:
1. **O Comentário de Múltiplas Linhas (`/* ... */`)**: O jeito profissional de "desligar" várias linhas de código ao mesmo tempo sem precisar colocar `//` em cada uma.
2. **O Método `.contains("...")`**: Uma ferramenta que investiga se um pedacinho de texto existe **dentro** de outro texto!

---

## 🔍 2. O Método `.contains("...")` — O Cão Farejador!

* **O que a palavra significa:** *Contains* em inglês significa **"contém"** ou **"tem dentro dele"**.
* **Como ele funciona:** Você entrega um pedacinho de texto entre aspas dentro dos parênteses, e o Java varre a String procurando se aquele pedaço existe ali no meio.
* **O que ele devolve:** Ele devolve uma resposta do tipo **`boolean`** (`true` se achou, ou `false` se não achou).

### 🔬 O Teste do Professor:
O professor perguntou se o nome `"Bro Code"` continha um espaço em branco:
```java
name.contains(" ")
```

* Como a palavra `"Bro Code"` tem um espaço no meio (entre `"Bro"` e `"Code"`), o Java respondeu: **`true`**!
* O `if` recebeu `true` e imprimiu:
  ```text
  Your name contains a space
  ```
  *(Seu nome contém um espaço)*.

---

## 📦 3. Comentários de Linha Única (`//`) vs. Comentários de Bloco (`/* ... */`)

* **`//` (Linha única):** Desliga apenas a linha onde ele foi colocado.
* **`/*` e `*/` (Bloco inteiro):**
  * Você abre com **`/*`** no começo do trecho.
  * Você fecha com **`*/`** no final do trecho.
  * **Tudo o que estiver no meio fica congelado/invisível para o Java!**  
  O professor usou isso para desligar o teste antigo do `isEmpty()` (da linha 17 à linha 24) de uma só vez!

---

## 🔬 4. Explicação ENUMERADA Linha por Linha de Todo o Código

Aqui está a explicação com o número exato de cada linha do programa do professor:

* **Linha 1 (`public class Main {`):**  
  Cria a classe principal do programa chamada `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de partida onde o Java começa a executar os comandos.
* **Linha 5 (`String name = "Bro Code";`):**  
  Cria a caixinha de texto `name` com o valor `"Bro Code"`.
* **Linhas 7 a 10 (`//int length...` até `//int lastIndex...`):**  
  Linhas antigas comentadas com `//` (desligadas).
* **Linhas 12 a 15 (`//name.toUpperCase()...` até `//name.replace()...`):**  
  Mais métodos antigos comentados com `//` (desligados).
* **Linha 17 (`/*`):**  
  **Abre o comentário de bloco:** Desliga temporariamente tudo o que vier a partir daqui.
* **Linha 18 a 23 (`if (name.isEmpty()) { ... } else { ... }`):**  
  O bloco de código do `isEmpty()` da aula passada, agora congelado pelo comentário.
* **Linha 24 (`*/`):**  
  **Fecha o comentário de bloco:** Avisa ao Java que o código normal volta a funcionar a partir da próxima linha!
* **Linha 26 (`if (name.contains(" ")) {`):**  
  **A Pergunta Farejadora:** Pergunta ao Java: *"O texto dentro de `name` contém algum espaço em branco (`" "`)?"*.  
  Como `"Bro Code"` tem espaço, o resultado é **`true`** e o Java entra nesta porta!
* **Linha 27 (`System.out.println("Your name contains a space");`):**  
  Imprime na tela a mensagem confirmando que o nome contém um espaço.
* **Linha 28 (`}`):**  
  Fecha o bloco do `if`.
* **Linha 29 (`else {`):**  
  **O Senão:** Só entraria aqui se o nome NÃO tivesse nenhum espaço (ex: se o nome fosse `"Mickel"`).
* **Linha 30 (`System.out.println("Your name DOESN'T contain any spaces");`):**  
  Avisaria que o nome não contém nenhum espaço.
* **Linha 31 (`}`):**  
  Fecha o bloco do `else`.
* **Linha 32 (`}`):**  
  Fecha o método `main`.
* **Linha 33 (`}`):**  
  Fecha a classe `Main`.

---

## 💡 5. Onde Você Usa o `.contains()` na Vida Real?

1. **Validação de Nickname / Usuário:**  
   No Instagram ou no Twitter, o seu `@usuario` não pode ter espaços!  
   Eles usam: `if (username.contains(" "))` $\rightarrow$ *"Nome de usuário não pode ter espaços!"*.
2. **Validação de E-mail:**  
   Para saber se a pessoa digitou um e-mail válido:  
   `if (email.contains("@"))` $\rightarrow$ Verifica se existe o símbolo `@` no texto!
3. **Senhas Seguras:**  
   `if (password.contains("$") || password.contains("#"))` $\rightarrow$ Verifica se a senha tem caracteres especiais!

---

## 💻 6. Código Completo e Limpo para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste com espaços e sem espaços:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 13: Testando o método contains() com if/else

        // TESTE 1: Com espaço ("Bro Code") -> Cai no if
        // TESTE 2: Sem espaço ("Mickel")   -> Cai no else
        String name = "Bro Code";

        if (name.contains(" ")) {
            System.out.println("Your name contains a space");
        }
        else {
            System.out.println("Your name DOESN'T contain any spaces");
        }
    }
}
```
