# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 15: Métodos com Retorno de Texto (`String`) e o Método `getFullName`
**Tema:** Provando que métodos podem devolver qualquer tipo de dado (não apenas números matemáticos). Criação do método `getFullName`, passagem de múltiplos argumentos de texto, concatenação com espaço (`+ " " +`) e saída `Spongebob Squarepants` no console.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens em mãos, aqui está a descrição detalhada do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (O Novo Código Pronto):**
   * O professor compactou os métodos anteriores `square` (Linha 16) e `cube` (Linha 19) em uma única linha cada, para não ocuparem espaço vertical.
   * Ele criou um método totalmente novo no final (Linhas 22 a 24):
     ```java
     static String getFullName(String first, String last){
         return first + " " + last;
     }
     ```
   * **A grande novidade:** O tipo de retorno agora é **`String`**! Ou seja, este método devolve um texto montado!
   * Dentro do `main` (Linhas 7 e 8), ele chama o método passando o primeiro nome (`"Spongebob"`) e o sobrenome (`"Squarepants"`), guarda na variável `String fullName` e imprime com `System.out.println(fullName);`.
   * O cursor do mouse está posicionado lá em cima, sobre o ícone verde de **Play** (`Run 'Main'`).
   * *Curiosidade visual:* A palavra `"Squarepants"` tem um sublinhado ondulado amarelo bem fraquinho embaixo. Isso é só o corretor ortográfico do IntelliJ dizendo que não conhece essa palavra no dicionário de inglês padrão!

2. **Imagem 2 (A Execução e o Resultado no Console):**
   * O professor clicou no botão de Play.
   * A janela preta do console abriu na metade inferior da tela.
   * Em destaque verde brilhante, o console imprimiu os dois nomes perfeitamente colados com um espaço entre eles:
     ```text
     Spongebob Squarepants
     ```
   * Logo abaixo, a mensagem de finalização com sucesso:
     ```text
     Process finished with exit code 0
     ```

---

## 🧠 A Grande Lição: Métodos Devolvem Qualquer Tipo de Dado!

Até agora, você aprendeu que:
1. `void` $\rightarrow$ Não devolve nada (só executa uma ação, como cantar ou imprimir).
2. `double` $\rightarrow$ Devolve um número com casas decimais (como `9.0` ou `27.0`).
3. **`String` $\rightarrow$ Devolve uma frase, palavra ou texto!**

O método `getFullName` funciona como uma **"máquina de juntar nomes"**:
* Você entrega duas peças separadas para ele: `"Spongebob"` e `"Squarepants"`.
* Ele passa cola entre elas (`+ " " +`).
* E te devolve uma única etiqueta de texto pronta: `"Spongebob Squarepants"`.

$$\text{static}\ \underbrace{\text{String}}_{\text{Tipo de Retorno}}\ \underbrace{\text{getFullName}}_{\text{Nome do Método}}(\underbrace{\text{String first, String last}}_{\text{Dois Parâmetros de Texto}})\{\ \underbrace{\text{return first + " " + last;}}_{\text{Junta e Devolve o Texto}}\ \}$$

---

## 🪢 O Segredo da Concatenação: Por que colocar `+ " " +`?

Preste muita atenção na Linha 23:
```java
return first + " " + last;
```

* Se você escrevesse apenas `return first + last;`, o Java iria colar as letras grudadas:
  `"SpongebobSquarepants"` (sem espaço no meio!).
* Para não ficar embolado, colocamos uma String no meio contendo apenas um espaço em branco: `" "`.
* Assim, o Java faz:
  `"Spongebob"` $+$ `" "` $+$ `"Squarepants"` $=$ `"Spongebob Squarepants"`.

---

## 💻 Código Completo e Idêntico ao da Tela (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        String fullName = getFullName("Spongebob", "Squarepants");
        System.out.println(fullName);
    }

    static void happyBirthday(String name, int age){
        System.out.printf("Happy birthday to %s!\n", name);
        System.out.printf("You are %d years old!\n", age);
    }

    static double square(double number) { return number * number; }

    static double cube(double number) { return number * number * number; }

    static String getFullName(String first, String last){
        return first + " " + last;
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

Aqui está a análise minuciosa de cada linha, o que acontece na memória do Java e o que é exibido no console:

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **O que sai no console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** O método principal onde o computador começa a executar o programa.
* **O que sai no console:** **Nada**.

---

### **Linhas 3 a 6:** *(Linhas em branco e comentário do professor)*
* **Linha 4:** `// method = a block of reusable code that is executed when called ()`  
  Comentário que explica o que é um método. Invisível para a execução.
* **O que sai no console:** **Nada**.

---

### **Linha 7:** `String fullName = getFullName("Spongebob", "Squarepants");`
* **O que faz:** **A CHAMADA DO MÉTODO COM PASSAGEM DE TEXTO:**
  1. O Java vê a chamada `getFullName("Spongebob", "Squarepants")`.
  2. Ele **pausa** o método `main`.
  3. Pula até a **Linha 22** (onde o método `getFullName` está declarado).
  4. Entrega `"Spongebob"` para a variável `first` e `"Squarepants"` para a variável `last`.
  5. Executa a Linha 23, concatenando as palavras com um espaço no meio.
  6. O comando `return` arremessa o texto resultante `"Spongebob Squarepants"` de volta para a Linha 7.
  7. Esse texto completo é guardado dentro da variável `String fullName`.
* **O que sai no console:** **Nada** (o texto foi montado e guardado na memória RAM, mas ainda não foi exibido na tela).

---

### **Linha 8:** `System.out.println(fullName);`
* **O que faz:** Pega o conteúdo da variável `fullName` (`"Spongebob Squarepants"`) e imprime na tela preta do console, pulando o cursor para a linha seguinte.
* **O que sai no console:** Imprime:
  ```text
  Spongebob Squarepants
  ```

---

### **Linha 9:** `}`
* **O que faz:** Fecha o método `main`. Fim do fluxo de execução principal.
* **O que sai no console:** O console exibe a linha do sistema `Process finished with exit code 0`.

---

### **Linhas 10 a 15:** `static void happyBirthday(...) { ... }`
* **O que faz:** Método antigo que canta parabéns. Fica guardado na classe, mas não é chamado nesta execução.
* **O que sai no console:** **Nada**.

---

### **Linha 16:** `static double square(double number) { return number * number; }`
* **O que faz:** Método que calcula o quadrado. Não é chamado nesta execução.
* **O que sai no console:** **Nada**.

---

### **Linhas 17 e 18:** *(Linhas em branco)*
* **O que faz:** Organização visual.
* **O que sai no console:** **Nada**.

---

### **Linha 19:** `static double cube(double number) { return number * number * number; }`
* **O que faz:** Método que calcula o cubo. Não é chamado nesta execução.
* **O que sai no console:** **Nada**.

---

### **Linhas 20 e 21:** *(Linhas em branco)*
* **O que faz:** Organização visual.
* **O que sai no console:** **Nada**.

---

### **Linha 22:** `static String getFullName(String first, String last){`
* **O que faz:** **A DECLARAÇÃO DO NOVO MÉTODO:**
  * `static`: permite ser chamado sem precisar criar instâncias (objetos).
  * `String`: **O CONTRATO DE RETORNO**. Diz: *"Eu me comprometo a devolver um texto (`String`)"*.
  * `getFullName`: o nome da função ("obter nome completo").
  * `(String first, String last)`: dois parâmetros que recebem os dois textos enviados (recebeu `"Spongebob"` e `"Squarepants"`).
* **O que sai no console:** **Nada**.

---

### **Linha 23:** `return first + " " + last;`
* **O que faz:** Concatena os textos com um espaço no meio (`"Spongebob" + " " + "Squarepants"` = `"Spongebob Squarepants"`) e a palavra mágica **`return`** envia o resultado de volta para o `main`, encerrando o método na hora!
* **O que sai no console:** **Nada**.

---

### **Linha 24:** `}`
* **O que faz:** Fecha o método `getFullName`.
* **O que sai no console:** **Nada**.

---

### **Linha 25:** `}`
* **O que faz:** Fecha a classe `Main`.
* **O que sai no console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
Spongebob Squarepants

Process finished with exit code 0
```

---

## 📊 Resumo dos Tipos de Retorno que Vimos até Agora:

| Tipo Declarado | O que o método devolve? | Exemplo Real do Curso |
| :---: | :--- | :--- |
| **`void`** | **Nada** (apenas executa uma ação). | `happyBirthday("Patrick", 38);` |
| **`double`** | Um número decimal. | `square(3)` $\rightarrow$ devolve `9.0` |
| **`double`** | Um número decimal. | `cube(3)` $\rightarrow$ devolve `27.0` |
| **`String`** | Um texto ou palavra. | `getFullName("Spongebob", "Squarepants")` $\rightarrow$ devolve `"Spongebob Squarepants"` |
