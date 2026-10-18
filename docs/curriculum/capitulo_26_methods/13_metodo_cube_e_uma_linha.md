# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 13: Criando o Método `cube` (Ao Cubo) e Métodos em Uma Única Linha
**Timestamp do vídeo:** `4:15:28 / 12:00:00`  
**Tema:** Criação de um segundo método matemático (`cube`), formatação de métodos compactos em uma única linha (`inline`) e cálculo de potências ($3 \times 3 \times 3 = 27.0$).

---

## 🖼️ Contexto Visual do Print (Para quem está lendo no Google Docs)

Se você copiou esta anotação para o seu Google Docs e não tem o print ao lado, aqui está tudo o que o professor **Bro Code** fez na tela:

1. **Compactou o método `square` em uma única linha (Linha 15):**
   * Antes, o método `square` ocupava 3 linhas. Para economizar espaço na tela e deixar o código mais enxuto, o professor colocou tudo numa linha só:
     ```java
     static double square(double number) { return number * number; }
     ```
   * Isso mostra que o Java não se importa se você quebra linhas ou não; desde que as chaves `{ }` e o ponto e vírgula `;` estejam no lugar certo, o código funciona perfeitamente!

2. **Criou um novo método chamado `cube` (Linhas 18 a 20):**
   * Logo abaixo de `square`, ele criou a função para elevar um número ao cubo ($x^3$):
     ```java
     static double cube(double number){
         return number * number * number;
     }
     ```
   * O IntelliJ IDEA exibe a etiqueta cinza `1 usage` ao lado do nome `cube`, avisando que esse método está sendo usado em exatamente 1 lugar do programa.

3. **Alterou a chamada no `main` (Linha 7):**
   * Em vez de chamar `square(3)`, ele alterou a chamada para:
     ```java
     System.out.println(cube(3));
     ```
   * O cursor do mouse está selecionando a palavra `cube(3)`.

---

## 🧮 O Que o Método `cube` Faz? (A Matemática por Trás)

* **Square (Quadrado):** Multiplica o número por ele mesmo **2 vezes**:
  $$3^2 = 3 \times 3 = 9.0$$
* **Cube (Cubo):** Multiplica o número por ele mesmo **3 vezes**:
  $$3^3 = 3 \times 3 \times 3 = 27.0$$

Quando o Java executa a linha 7:
$$\text{System.out.println}(\ \underbrace{\text{cube(3)}}_{\text{Gera } 3 \times 3 \times 3 = 27.0}\ ) \quad \Longrightarrow \quad \text{System.out.println}(27.0);$$

O console vai imprimir:
```text
27.0
```

---

## 💡 Dica de Ouro de Sintaxe: Métodos em Uma Única Linha

Repare na Linha 15:
```java
static double square(double number) { return number * number; }
```

No Java, você pode escrever métodos curtinhos em uma única linha se achar que fica mais limpo e organizado. 

As duas formas abaixo são **100% idênticas** para o Java:

| Forma Tradicional (Múltiplas Linhas) | Forma Compacta (Uma Linha) |
| :--- | :--- |
| ```java<br>static double square(double number){<br>    return number * number;<br>}<br>``` | ```java<br>static double square(double number) { return number * number; }<br>``` |

> [!TIP]
> Use a forma de uma única linha apenas para métodos **muito simples e curtos** (de uma única instrução de `return`). Se o método tiver várias linhas de lógica, cálculos complexos ou comandos `if`, sempre prefira quebrar em múltiplas linhas para facilitar a leitura!

---

## 💻 Código Completo e Atualizado (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        System.out.println(cube(3));
    }

    static double square(double number) { return number * number; }

    static double cube(double number){
        return number * number * number;
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **O que sai no console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto de partida obrigatório onde o Java começa a ler as ordens do programa.
* **O que sai no console:** **Nada**.

---

### **Linhas 3 a 6:** *(Linhas em branco e comentário)*
* **Linha 4:** `// method = a block of reusable code that is executed when called ()`  
  Comentário que explica o conceito de método. O computador ignora completamente.
* **O que sai no console:** **Nada**.

---

### **Linha 7:** `System.out.println(cube(3));`
* **O que faz:** **A CHAMADA ANINHADA DO MÉTODO `cube`:**
  1. O Java vê `cube(3)` e pausa o comando `println`.
  2. Pula para a **Linha 12** (onde está declarado o método `cube`).
  3. Entrega o número `3` para o parâmetro `number`.
  4. Executa a Linha 13: calcula $3 \times 3 \times 3 = 27.0$.
  5. O `return` devolve o valor `27.0` de volta para a Linha 7.
  6. A expressão vira `System.out.println(27.0);`.
  7. O `println` imprime o número `27.0` na tela e pula uma linha.
* **O que sai no console:**
  ```text
  27.0
  ```

---

### **Linha 8:** `}`
* **O que faz:** Fecha o método `main`. Fim da execução principal.
* **O que sai no console:** **Nada** (o console encerra com `Process finished with exit code 0`).

---

### **Linha 9:** *(Linha em branco)*
* **O que faz:** Separação visual.
* **O que sai no console:** **Nada**.

---

### **Linha 10:** `static double square(double number) { return number * number; }`
* **O que faz:** **O MÉTODO `square` (EM UMA LINHA):**
  * Continua existindo na classe, pronto para ser usado se alguém chamar.
  * Como o `main` agora chamou `cube(3)` e não `square(3)`, este método fica "adormecido" na memória sem ser executado desta vez.
* **O que sai no console:** **Nada**.

---

### **Linha 11:** *(Linha em branco)*
* **O que faz:** Separação visual entre os dois métodos.
* **O que sai no console:** **Nada**.

---

### **Linha 12:** `static double cube(double number){`
* **O que faz:** **A DECLARAÇÃO DO MÉTODO `cube`:**
  * `static`: para poder ser chamado diretamente de dentro do `main`.
  * `double`: o tipo de dado que ele **obrigatoriamente devolve**.
  * `cube`: o nome do método.
  * `(double number)`: o parâmetro que recebe o valor enviado (recebeu `3`).
* **O que sai no console:** **Nada**.

---

### **Linha 13:** `return number * number * number;`
* **O que faz:** Realiza a multiplicação tripla ($3 \times 3 \times 3 = 27.0$) e a palavra `return` arremessa esse `27.0` de volta para o `main`.
* **O que sai no console:** **Nada** (o retorno ocorre internamente na memória).

---

### **Linha 14:** `}`
* **O que faz:** Fecha o método `cube`.
* **O que sai no console:** **Nada**.

---

### **Linha 15:** `}`
* **O que faz:** Fecha a classe `Main`.
* **O que sai no console:** **Nada**.

---

## 📺 O que sai no Console ao Executar:

```text
27.0

Process finished with exit code 0
```

*(Sai `27.0` com ponto flutuante porque o método `cube` foi declarado com retorno do tipo `double`!)*

---

## 🏗️ Resumo dos Métodos que Construímos até Agora:

| Método | Tipo de Retorno | Parâmetros | O que faz? | Exemplo de Retorno |
| :--- | :---: | :--- | :--- | :---: |
| `happyBirthday` | `void` | `(String name, int age)` | Canta parabéns na tela (não devolve valor). | *(Nada)* |
| `square` | `double` | `(double number)` | Eleva ao quadrado ($x \times x$). | `square(3)` $\rightarrow$ `9.0` |
| `cube` | `double` | `(double number)` | Eleva ao cubo ($x \times x \times x$). | `cube(3)` $\rightarrow$ `27.0` |
