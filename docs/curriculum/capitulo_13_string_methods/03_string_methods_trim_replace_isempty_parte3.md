# 🧵 Capítulo 13: `String Methods` — Parte 3 (`trim`, `replace` e `isEmpty`)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Adicionou de Novo?

Nesta continuação do Capítulo 13, o professor nos apresentou mais **3 ferramentas fundamentais** do cinto de utilidades das Strings:
1. **`.trim()`**: A "tesoura" que apara os espaços em branco inúteis das pontas.
2. **`.replace(antigo, novo)`**: O "substituto" que troca letras ou palavras dentro do texto.
3. **`.isEmpty()`**: O "detetive" que pergunta se o texto está 100% vazio ou não.

Vamos aprender cada um com calma, analogias e exemplos práticos!

---

## ✂️ 2. Método 1: `name.trim()` — Aparando as Pontas!

* **O que a palavra significa:** *Trim* em inglês significa **aparar**, tosar ou cortar os excessos (como aparar a grama do jardim ou cortar as pontas do cabelo).
* **O que ele faz:** Ele remove todos os **espaços em branco que estão sobrando ANTES do início do texto e DEPOIS do final do texto**.
* **Ele mexe nos espaços do meio?**  
  **NÃO!** Ele nunca mexe nos espaços entre as palavras. Apenas nas bordas externas!

### 💡 Exemplo da Vida Real:
Imagine um formulário de cadastro onde o usuário digita o nome com a barra de espaço apertada sem querer:
```java
String name = "   Bro Code   ";
name = name.trim();
```
* **Antes:** `"   Bro Code   "` (cheio de espaços inúteis nas pontas).
* **Depois:** `"Bro Code"` (limpinho e pronto para salvar no banco de dados!).

---

## 🔄 3. Método 2: `name.replace("antigo", "novo")` — O Localizar e Substituir!

* **O que a palavra significa:** *Replace* em inglês significa **substituir** ou trocar de lugar.
* **O que ele faz:** Você entrega duas coisas para ele entre parênteses:
  1. O caractere que você quer caçar (`"o"`).
  2. O caractere novo que vai entrar no lugar dele (`"a"`).
* O Java procura **TODAS** as ocorrências daquela letra no texto inteiro e faz a troca!

### 🔬 O Teste do Professor:
```java
String name = "Bro Code";
name = name.replace("o", "a");
System.out.println(name);
```

* Na palavra `"Bro Code"`, a letra `"o"` aparece duas vezes:
  * No `"Bro"` $\rightarrow$ virou **`"Bra"`**
  * No `"Code"` $\rightarrow$ virou **`"Cade"`**
* **Saída no Console:**
  ```text
  Bra Cade
  ```

> [!NOTE]
> Lembre-se: como as Strings são imutáveis no Java, precisamos reatribuir:  
> `name = name.replace("o", "a");` para guardar a versão modificada de volta na caixinha!

---

## 🔍 4. Método 3: `name.isEmpty()` — A Caixa Está Vazia?

* **O que a palavra significa:** *Is empty* em inglês é uma pergunta direta: *"Está vazio?"*.
* **O que ele faz:** Ele investiga se a String não tem nenhuma letra, nenhum número e nenhum espaço. Se ela for apenas aspas coladas: `""`.
* **O que ele devolve:** Ele devolve uma resposta do tipo **`boolean`** (`true` ou `false`).

### 🔬 O Teste do Professor:
```java
String name = "Bro Code";
System.out.println(name.isEmpty()); // Imprime: false
```
* Como a variável `name` contém as letras `"Bro Code"`, a caixa **não** está vazia!
* Portanto, o Java respondeu com toda a certeza: **`false`** (não está vazio).

### 🧪 E quando é que ele daria `true`?
Se você criasse uma String sem nada dentro:
```java
String emptyName = "";
System.out.println(emptyName.isEmpty()); // Aqui imprimiria: true!
```
*(Usamos muito o `.isEmpty()` para verificar se o usuário esqueceu de preencher o campo do formulário antes de enviar!)*

---

## 🔬 5. Explicação LINHA POR LINHA de Todo o Código

Vamos olhar cada linha que estava no programa do professor:

* **`String name = "Bro Code";`**  
  Cria a caixinha de texto `name` com o valor `"Bro Code"`.
* **`int length = name.length();`**  
  Calcula o tamanho total (8 caracteres).
* **`char letter = name.charAt(0);`**  
  Pega o primeiro caractere (gaveta 0, letra `'B'`).
* **`int index = name.indexOf(" ");`**  
  Acha a primeira posição do espaço em branco (gaveta 3).
* **`int lastIndex = name.lastIndexOf("o");`**  
  Acha a última posição da letra 'o' (gaveta 5).
* **`//name = name.toUpperCase();`**  
  Comentário (desligado): deixaria tudo maiúsculo.
* **`//name = name.toLowerCase();`**  
  Comentário (desligado): deixaria tudo minúsculo.
* **`//name = name.trim();`**  
  Comentário (desligado): cortaria espaços sobrando antes e depois.
* **`name = name.replace("o", "a");`**  
  Procura todas as letras `"o"` e troca por `"a"`, gerando `"Bra Cade"`.
* **`System.out.println(name.isEmpty());`**  
  Pergunta se o texto está vazio e imprime a resposta booleana: **`false`**.

---

## 📊 6. Tabela Resumo dos 3 Novos Métodos

| Método | Tradução / Significado | Para que serve? | Exemplo com `"Bro Code"` | Resultado |
| :--- | :--- | :--- | :---: | :---: |
| **`.trim()`** | Aparar | Remove espaços em branco do início e do fim | `" Bro Code "` $\rightarrow$ `.trim()` | `"Bro Code"` |
| **`.replace("o", "a")`** | Substituir | Troca todas as letras antigas pela nova | `"Bro Code"` $\rightarrow$ `.replace("o", "a")` | `"Bra Cade"` |
| **`.isEmpty()`** | Está vazio? | Pergunta se o texto tem 0 caracteres | `"Bro Code"` $\rightarrow$ `.isEmpty()` | `false` |

---

## 💻 7. Os 3 Códigos Separados (Prontos para Testar um por um)

Como cada método tem uma função diferente, aqui estão os **3 códigos 100% separados e independentes**. Você pode copiar um de cada vez para o seu `Main.java`!

---

### 🔹 Código 1: Teste Exclusivo do `replace` (Gerando `"Bra Cade"`)

```java
public class Main {
    public static void main(String[] args) {

        String name = "Bro Code";

        // Troca todas as letras 'o' por 'a'
        name = name.replace("o", "a");

        System.out.println(name); // Saída no console: Bra Cade
    }
}
```

---

### 🔹 Código 2: Teste Exclusivo do `trim` (Aparando os Espaços)

```java
public class Main {
    public static void main(String[] args) {

        // Texto com espaços sobrando no começo e no fim
        String name = "   Bro Code   ";

        // O trim arranca fora os espaços das pontas
        name = name.trim();

        System.out.println(name); // Saída no console: Bro Code (sem espaços nas pontas)
    }
}
```

---

### 🔹 Código 3: Teste Exclusivo do `isEmpty` (Está Vazio?)

```java
public class Main {
    public static void main(String[] args) {

        String name = "Bro Code";

        // Pergunta se o texto está vazio
        System.out.println(name.isEmpty()); // Saída no console: false
    }
}
```

