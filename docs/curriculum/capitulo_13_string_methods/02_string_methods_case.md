# 🧵 Capítulo 13: `String Methods` — Parte 2 (`toUpperCase` e `toLowerCase`)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Adicionou Aqui?

Nestas cenas, o professor apresentou duas ferramentas poderosíssimas para transformar texto:
1. **`.toUpperCase()`**: Transforma todas as letras em **MAIÚSCULAS** (como se você tivesse ativado o *Caps Lock*).
2. **`.toLowerCase()`**: Transforma todas as letras em **MINÚSCULAS** (letrinhas pequenas).

Mas atenção: aqui está **um dos maiores segredos do Java** que 99% dos iniciantes erram no começo:  
👉 **Por que o professor escreveu `name = name.toUpperCase();` em vez de apenas `name.toUpperCase();`?**

Vamos entender isso com uma analogia simples!

---

## 🧱 2. O Grande Segredo: As Strings no Java são IMUTÁVEIS (Feitas de Pedra!)

No Java, um texto (`String`) depois de criado **NUNCA mais pode ser modificado**. Ele é como uma escultura de pedra entalhada.

Quando você diz para o Java:
> *"Java, pegue a palavra `'Bro Code'` e passe para maiúsculas!"*

O Java **não** apaga as letras antigas da palavra `"Bro Code"`.  
O Java faz o seguinte:
1. Ele cria uma **CÓPIA NOVA** em outra parte da memória: `"BRO CODE"`.
2. Ele estende a mão e te entrega essa cópia novinha.

### ⚠️ O Erro Clássico do Iniciante:
Se você escrever apenas:
```java
name.toUpperCase(); // ❌ O Java cria a cópia em maiúsculas, mas ninguém pega!
System.out.println(name); // Continua imprimindo "Bro Code" normal!
```
A cópia nova foi jogada no lixo porque você não a guardou em lugar nenhum!

### ✅ O Jeito Certo (O que o professor fez):
```java
name = name.toUpperCase(); // ✔️ Pega a cópia maiúscula e guarda de volta na caixinha 'name'!
```
O sinal de igual `=` diz: *"Pegue essa nova versão em maiúsculas e guarde dentro da caixinha `name`, substituindo o que estava lá antes!"*.

---

## 🔬 3. Os Dois Novos Métodos em Detalhes

### 🟢 1. `name.toUpperCase()` — Todas Maiúsculas (Gritando!)
* **Origem da palavra:** *Upper* (superior/alto) + *Case* (caixa tipográfica). Em português: "Caixa Alta" ou "Letras Maiúsculas".
* **O que faz:** Varre o texto do começo ao fim e transforma todas as letras em maiúsculas. O que já for maiúsculo continua maiúsculo; espaços e números continuam iguais.
* **Exemplo:**
  * Original: `"Bro Code"`
  * Depois de `name = name.toUpperCase();`: **`"BRO CODE"`**
* **Saída no Console:**
  ```text
  BRO CODE
  ```

---

### 🟢 2. `name.toLowerCase()` — Todas Minúsculas (Sussurrando!)
* **Origem da palavra:** *Lower* (inferior/baixo) + *Case* (caixa). Em português: "Caixa Baixa" ou "Letras Minúsculas".
* **O que faz:** Transforma todas as letras em letrinhas minúsculas.
* **Exemplo:**
  * Original: `"Bro Code"`
  * Depois de `name = name.toLowerCase();`: **`"bro code"`**
* **Saída no Console:**
  ```text
  bro code
  ```

> [!TIP]
> **Onde isso é usado na vida real?**  
> Quando você faz login num site com seu e-mail: se você digitar `Mickel@Gmail.Com` ou `mickel@gmail.com`, o sistema usa `.toLowerCase()` para transformar tudo em minúsculas antes de checar no banco de dados. Assim, evita erros de digitação!

---

## 🔬 4. Explicação LINHA POR LINHA de Todo o Código

Vamos ler cada linha do código que estava na tela do professor:

* **`String name = "Bro Code";`**  
  Cria a caixinha de texto chamada `name` guardando `"Bro Code"`.
* **`int length = name.length();`**  
  Mede o tamanho total do texto (8 caracteres) e guarda em `length`.
* **`char letter = name.charAt(0);`**  
  Pega o caractere da primeira posição (índice 0), que é a letra `'B'`, e guarda em `letter`.
* **`int index = name.indexOf(" ");`**  
  Procura onde fica a primeira ocorrência do espaço em branco (índice 3) e guarda em `index`.
* **`int lastIndex = name.lastIndexOf("o");`**  
  Procura onde fica a última ocorrência da letra `"o"` (índice 5) e guarda em `lastIndex`.
* **`//name = name.toUpperCase();`**  
  A barra dupla `//` é um **comentário**. O professor comentou essa linha para "desligar" o maiúsculo temporariamente, para poder testar o minúsculo logo abaixo sem que um atrapalhe o outro!
* **`name = name.toLowerCase();`**  
  Gera a versão em minúsculas (`"bro code"`) e guarda de volta na variável `name`.
* **`System.out.println(name);`**  
  Imprime na tela o texto atualizado que está dentro de `name` (saiu `bro code`).

---

## 📊 5. Resumo Visual da Transformação

| Comando | Texto Antes | Texto Depois | O que mudou? |
| :--- | :---: | :---: | :--- |
| **`name.toUpperCase()`** | `"Bro Code"` | **`"BRO CODE"`** | O `'r'`, `'o'`, `'o'`, `'d'`, `'e'` viraram maiúsculas. |
| **`name.toLowerCase()`** | `"Bro Code"` | **`"bro code"`** | O `'B'` e o `'C'` viraram minúsculas. |

---

## 💻 6. Código Completo para Você Copiar e Testar no IntelliJ

Copie este código para o seu `Main.java` e teste rodar:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 13: String methods (toUpperCase e toLowerCase)

        String name = "Bro Code";

        // Métodos que aprendemos na etapa anterior:
        int length = name.length();
        char letter = name.charAt(0);
        int index = name.indexOf(" ");
        int lastIndex = name.lastIndexOf("o");

        // TESTE 1: Transformando em MAIÚSCULAS
        // name = name.toUpperCase();
        // System.out.println("Em maiúsculas: " + name); // BRO CODE

        // TESTE 2: Transformando em MINÚSCULAS
        name = name.toLowerCase();
        System.out.println("Em minúsculas: " + name); // bro code
    }
}
```

> **Dica de estudo:** Descomente a linha do `toUpperCase()` para ver como fica com tudo maiúsculo! 🚀
