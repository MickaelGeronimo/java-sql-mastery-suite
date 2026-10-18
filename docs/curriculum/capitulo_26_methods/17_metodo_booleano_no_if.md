# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 17: Usando Métodos Booleanos Direto Dentro do `if` e os Testes com 21 e 12 Anos
**Tema:** Como encaixar métodos que retornam `boolean` diretamente dentro da condição `if(ageCheck(age))`, a eliminação de redundâncias como `== true` e o teste dos dois caminhos possíveis (`true` vs. `false`).

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem os prints ao lado, aqui está a sequência completa de tudo o que o professor **Bro Code** fez na tela do computador:

1. **Imagem 1 (O Teste dos 21 Anos no Editor):**
   * Dentro do método `main`, o professor escreve um fluxo de cadastro com verificação de idade:
     ```java
     int age = 21;

     if(ageCheck(age)){
         System.out.println("You may sign up!");
     }
     else{
         System.out.println("You must be 18+ to sign up");
     }
     ```
   * Repare que o método `ageCheck` foi colocado **diretamente dentro dos parênteses do `if`**!
   * O cursor do mouse está sobre o botão verde de **Play** (`Run 'Main'`).

2. **Imagem 2 (Resultado dos 21 Anos no Console):**
   * O professor executa o programa.
   * A janela preta do console abre e imprime em verde brilhante:
     ```text
     You may sign up!
     ```
   * Por quê? Porque 21 é maior que 18, o método devolveu `true` e o Java entrou no bloco do `if`!

3. **Imagem 4 (Mudando a Idade para 12 Anos no Editor):**
   * Para provar que o método realmente funciona para qualquer idade, o professor volta na Linha 7 e altera:
     ```java
     int age = 12;
     ```
   * Repare que o método `ageCheck` na linha 31 agora aparece recolhido como `{...}` para não ocupar espaço.
   * O professor move o mouse e clica novamente no botão verde de **Play**.

4. **Imagem 3 (Resultado dos 12 Anos no Console):**
   * O programa roda novamente com a nova idade de 12 anos.
   * Desta vez, o console imprime em verde brilhante:
     ```text
     You must be 18+ to sign up
     ```
   * Como 12 é menor que 18, o método devolveu `false`! O Java pulou o `if` e caiu direto no `else`!

---

## 🧠 O Conceito Chave: Por Que Podemos Colocar o Método Direto no `if`?

Todo iniciante em programação costuma pensar assim:
```java
// O jeito comprido (de iniciante):
boolean resultado = ageCheck(age);
if (resultado == true) {
    ...
}
```

Mas o Java é muito mais esperto e elegante!
Lembre-se da regra sagrada do comando `if`:
> O comando `if( ... )` só aceita uma coisa dentro dos seus parênteses: **um valor booleano (`true` ou `false`)**!

Como o nosso método `ageCheck(age)` foi criado com o tipo de retorno **`boolean`**, no momento em que ele termina de rodar, ele arremessa um `true` ou um `false` na mão do `if`:

### Cenário 1: Quando `age = 21`
$$\text{if}(\ \underbrace{\text{ageCheck}(21)}_{\text{Devolve true}}\ )\quad \Longrightarrow \quad \text{if}(\text{true})\ \rightarrow\ \text{Executa o bloco do if!}$$
* Imprime: `"You may sign up!"` (Você pode se cadastrar!).

### Cenário 2: Quando `age = 12`
$$\text{if}(\ \underbrace{\text{ageCheck}(12)}_{\text{Devolve false}}\ )\quad \Longrightarrow \quad \text{if}(\text{false})\ \rightarrow\ \text{Cai no else!}$$
* Imprime: `"You must be 18+ to sign up"` (Você deve ter 18+ para se cadastrar).

> [!IMPORTANT]
> **Nunca escreva `if (ageCheck(age) == true)`!**  
> Escrever `== true` é considerado redundante e deselegante em Java, porque o próprio método já é uma afirmação que resulta em verdadeiro ou falso. Escreva direto: `if(ageCheck(age))`.

---

## 💻 Código Completo e Pronto para Rodar (`Main.java`)

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        int age = 21; // Mude para 12 para testar o else!

        if(ageCheck(age)){
            System.out.println("You may sign up!");
        }
        else{
            System.out.println("You must be 18+ to sign up");
        }
    }

    static void happyBirthday(String name, int age){
        System.out.printf("Happy birthday to %s!\n", name);
        System.out.printf("You are %d years old!\n", age);
    }

    static double square(double number) { return number * number; }

    static double cube(double number) { return number * number * number; }

    static String getFullName(String first, String last) { return first + " " + last; }

    static boolean ageCheck(int age){
        return age >= 18;
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** O método principal onde a execução do programa se inicia.
* **Console:** **Nada**.

---

### **Linhas 3 a 6:** *(Linhas em branco e comentário do professor)*
* **Linha 4:** `// method = a block of reusable code that is executed when called ()`  
  Comentário que explica o conceito de método. O computador ignora.
* **Console:** **Nada**.

---

### **Linha 7:** `int age = 21;`
* **O que faz:** Cria uma variável inteira (`int`) chamada `age` e guarda o valor `21` dentro dela na memória RAM.
* **Console:** **Nada**.

---

### **Linha 8:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 9:** `if(ageCheck(age)){`
* **O que faz:** **A CHAMADA DO MÉTODO DENTRO DA CONDIÇÃO:**
  1. O `if` precisa saber se é verdadeiro ou falso para decidir o que fazer.
  2. Ele chama o método `ageCheck(age)` passando o número `21`.
  3. O Java salta para a **Linha 26** (onde está o método `ageCheck`).
  4. O método calcula: $21 \ge 18 \rightarrow$ **`true`**.
  5. O `return` devolve **`true`** para a Linha 9.
  6. A linha vira `if(true)`. Como é verdadeiro, o Java autoriza a entrada no bloco!
* **Console:** **Nada** (a tomada de decisão acontece internamente).

---

### **Linha 10:** `System.out.println("You may sign up!");`
* **O que faz:** Como o `if` deu verdadeiro, esta linha é executada e imprime a mensagem na tela preta.
* **Console:** Imprime:
  ```text
  You may sign up!
  ```

---

### **Linha 11:** `}`
* **O que faz:** Fecha o bloco do `if`.
* **Console:** **Nada**.

---

### **Linha 12:** `else{`
* **O que faz:** Início do bloco alternativo (se a condição fosse falsa).
* **Console:** **Nada** (ignorado quando `age = 21`).

---

### **Linha 13:** `System.out.println("You must be 18+ to sign up");`
* **O que faz:** Mensagem de erro que só roda se o método devolver `false` (quando `age = 12`).
* **Console:** Só imprime se a idade for menor que 18.

---

### **Linha 14:** `}`
* **O que faz:** Fecha o bloco do `else`.
* **Console:** **Nada**.

---

### **Linha 15:** `}`
* **O que faz:** Fecha o método `main`. Fim do programa.
* **Console:** O sistema encerra com `Process finished with exit code 0`.

---

### **Linhas 16 a 25:** *(Métodos anteriores adormecidos na memória)*
* `happyBirthday`, `square`, `cube`, `getFullName`: todos continuam guardados na classe, mas não são chamados nesta execução.
* **Console:** **Nada**.

---

### **Linha 26:** `static boolean ageCheck(int age){`
* **O que faz:** Cabeça do método booleano:
  * `static`: chamado diretamente pelo `main`.
  * `boolean`: promete devolver `true` ou `false`.
  * `(int age)`: recebe a idade a ser avaliada.
* **Console:** **Nada**.

---

### **Linha 27:** `return age >= 18;`
* **O que faz:** Compara se a idade recebida é maior ou igual a 18 e arremessa o valor lógico resultante (`true` ou `false`) de volta para o `if`.
* **Console:** **Nada**.

---

### **Linha 28:** `}`
* **O que faz:** Fecha o método `ageCheck`.
* **Console:** **Nada**.

---

### **Linha 29:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console em Cada Caso:

### Teste 1: Quando `age = 21;`
```text
You may sign up!

Process finished with exit code 0
```

### Teste 2: Quando `age = 12;`
```text
You must be 18+ to sign up

Process finished with exit code 0
```

---

## 🏆 Resumo Completo de Métodos com Retorno (`return`)

| Tipo de Retorno | O que Devolve? | Exemplo Real no Curso | Como Usar? |
| :---: | :--- | :--- | :--- |
| **`void`** | Nada | `happyBirthday("Patrick", 38);` | Executa ações na tela diretamente. |
| **`double`** | Número decimal | `square(3);` $\rightarrow$ `9.0`<br>`cube(3);` $\rightarrow$ `27.0` | Em contas matemáticas ou direto no `println`. |
| **`String`** | Texto | `getFullName("Spongebob", "Squarepants");` | Em variáveis de texto ou no `println`. |
| **`boolean`** | `true` ou `false` | `ageCheck(age);` | **Direto dentro de condições `if( ... )`!** |
