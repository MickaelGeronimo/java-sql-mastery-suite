# 🍕 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 27: Métodos Sobrecarregados (`overloaded methods`) — Parte 2: O Primeiro Overload na Prática! Dois Métodos `add` e o Resultado `6.0`
**Tema:** A solução mágica para o erro da aula anterior: criação do segundo método com o **MESMO NOME** (`add`), mas com **3 PARÂMETROS** (`double a, double b, double c`), como o Java escolhe o método certo automaticamente e o cálculo de $1 + 2 + 3 = 6.0$ no console.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem os prints ao lado, veja exatamente o que aconteceu na tela do professor **Bro Code**:

1. **Imagem 1 (O Segundo Método `add` Criado no Editor):**
   * Na aula anterior, a linha 9 (`add(1, 2, 3)`) estava toda vermelha com erro de compilação porque só existia um método `add` que aceitava 2 números.
   * Agora, o professor **NÃO mudou o nome da função**. Ele simplesmente foi na Linha 15 e escreveu um **segundo método também chamado `add`**:
     ```java
     static double add(double a, double b, double c){
         return a + b + c;
     }
     ```
   * **A mágica visual:** Todas as linhas vermelhas de erro da linha 9 **sumiram instantaneamente**! O código ficou 100% limpo!
   * Repare nas etiquetas cinzas inteligentes do IntelliJ IDEA:
     * Ao lado do primeiro `add(double a, double b)`: aparece `no usages` (nenhum uso), porque ninguém está passando 2 números no momento.
     * Ao lado do segundo `add(double a, double b, double c)`: aparece `1 usage` (1 uso), porque a linha 9 está chamando exatamente ele!
   * O cursor do mouse está sobre o botão verde de **Play** (`Run 'Main'`).

2. **Imagem 2 (A Execução e o Resultado `6.0` no Console):**
   * O professor clicou no botão verde de Play.
   * A janela preta do console abriu na metade inferior da tela.
   * Em destaque verde reluzente, o console exibiu a soma dos 3 números:
     ```text
     6.0
     ```
   * Logo abaixo, a clássica finalização de sucesso:
     ```text
     Process finished with exit code 0
     ```

---

## 🧠 Como o Java Sabe Qual Método Executar? (O Trabalho do Detetive)

Muita gente que está começando em Java pensa:  
> *"Peraí! Se os dois métodos têm exatamente o mesmo nome (`add`), o computador não vai ficar confuso e enlouquecer?"*

**A resposta é NÃO!**  
O Java é um detetive implacável. Quando ele lê a linha 9:
```java
System.out.println(add(1, 2, 3));
```

Ele faz a seguinte investigação:
1. **Nome procurado:** `add`.
2. **Quantos argumentos foram enviados?** Foram enviados **3 números** (`1`, `2` e `3`).
3. O Java olha para o primeiro método na Linha 12:  
   * *"Esse aqui se chama `add`, mas só tem 2 caixinhas (`a` e `b`). Não serve!"*
4. O Java olha para o segundo método na Linha 15:  
   * *"Esse aqui se chama `add` e tem EXATAMENTE 3 caixinhas (`a`, `b` e `c`). Achei o par perfeito!"*
5. Ele direciona os dados para o segundo método, calcula $1.0 + 2.0 + 3.0 = 6.0$, e pronto!

---

## 🍕 A Metáfora da Pizzaria (Por que tem um emoji de pizza no título?)

Imagine que você entra em uma pizzaria e diz para o atendente:
* *"Por favor, me dê uma pizza de calabresa."* $\rightarrow$ O atendente te entrega a pizza tradicional de **1 sabor**.
* Se você disser: *"Me dê uma pizza meio calabresa e meio queijo."* $\rightarrow$ O atendente usa a mesma habilidade de fazer pizza, mas agora dividida em **2 sabores**.
* Se você disser: *"Me dê uma pizza de 3 sabores."* $\rightarrow$ Ele prepara uma pizza com **3 sabores**.

O nome da ação é sempre o mesmo: **`fazerPizza()`**.  
Mas dependendo de **quantos sabores (parâmetros)** você pede, o cozinheiro segue a receita com 1, 2 ou 3 divisões!  
Isso é **Método Sobrecarregado**!

---

## 💻 Código Completo e Idêntico ao da Tela (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // overloaded methods = methods that share the same name,
        //                      but different parameters
        //                      signature = name + parameters

        System.out.println(add(1, 2, 3));
    }

    static double add(double a, double b){
        return a + b;
    }

    static double add(double a, double b, double c){
        return a + b + c;
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
* **O que faz:** Ponto de entrada por onde a execução do programa começa.
* **Console:** **Nada**.

---

### **Linhas 3 a 8:** *(Linhas em branco e comentários)*
* **Linhas 5 a 7:** Comentários com a definição de métodos sobrecarregados e assinatura (`signature = name + parameters`). O computador ignora.
* **Console:** **Nada**.

---

### **Linha 9:** `System.out.println(add(1, 2, 3));`
* **O que faz:** **A CHAMADA SOBRECARREGADA:**
  1. O compilador vê a chamada `add` com **3 argumentos** (`1`, `2`, `3`).
  2. Ele busca uma assinatura compatível e ignora a Linha 12 (que só tem 2 parâmetros).
  3. Salta direto para a **Linha 15** (`add` de 3 parâmetros).
  4. Entrega `1.0` para `a`, `2.0` para `b` e `3.0` para `c`.
  5. Na Linha 16, calcula $1.0 + 2.0 + 3.0 = 6.0$.
  6. O comando `return` arremessa o número `6.0` de volta para a Linha 9.
  7. O `println` recebe `6.0` e imprime na tela do console.
* **Console:** Imprime:
  ```text
  6.0
  ```

---

### **Linha 10:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 11:** `}`
* **O que faz:** Fecha o método `main`. O programa principal concluiu sua tarefa.
* **Console:** O sistema encerra com `Process finished with exit code 0`.

---

### **Linha 12:** `static double add(double a, double b){`
* **O que faz:** **PRIMEIRA VERSÃO DO MÉTODO `add` (Assinatura: `add(double, double)`):**
  * Feito para somar 2 números.
  * Como a chamada na Linha 9 passou 3 números, este método **fica descansando na memória** (`no usages`).
* **Console:** **Nada**.

---

### **Linha 13:** `return a + b;`
* **O que faz:** Devolveria a soma de 2 números (não foi executado desta vez).
* **Console:** **Nada**.

---

### **Linha 14:** `}`
* **O que faz:** Fecha a primeira versão do método `add`.
* **Console:** **Nada**.

---

### **Linha 15:** `static double add(double a, double b, double c){`
* **O que faz:** **SEGUNDA VERSÃO DO MÉTODO `add` (Assinatura: `add(double, double, double)`):**
  * `static`: permite ser chamado sem instanciar objetos.
  * `double`: tipo de retorno (número decimal).
  * `add`: **O MESMO NOME** do método de cima!
  * `(double a, double b, double c)`: recebe 3 números decimais.
* **Console:** **Nada**.

---

### **Linha 16:** `return a + b + c;`
* **O que faz:** Soma os três números ($1.0 + 2.0 + 3.0 = 6.0$) e devolve imediatamente para o `println`.
* **Console:** **Nada** (o valor viaja internamente pela memória).

---

### **Linha 17:** `}`
* **O que faz:** Fecha a segunda versão do método `add`.
* **Console:** **Nada**.

---

### **Linha 18:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
6.0

Process finished with exit code 0
```

*(Sai `6.0` com ponto flutuante porque o método devolve o tipo `double`)*

---

## 📋 Comparação das Assinaturas dos Dois Métodos

| Método | Nome | Parâmetros | Assinatura Oficial do Java | Quando é Chamado? |
| :---: | :---: | :--- | :--- | :--- |
| **Versão 1** | `add` | `(double a, double b)` | `add(double, double)` | Quando você passa **2 números**, ex: `add(10, 20)` |
| **Versão 2** | `add` | `(double a, double b, double c)` | `add(double, double, double)` | Quando você passa **3 números**, ex: `add(1, 2, 3)` |

Ambos convivem em perfeita harmonia dentro do mesmo arquivo Java!
