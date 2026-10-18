# 🍕 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 27: Métodos Sobrecarregados (`overloaded methods`) — Parte 1: Introdução, Assinatura (`signature`) e o Erro das Listas de Tamanhos Diferentes
**Timestamps do vídeo:** `4:20:50` até `4:21:04 / 12:00:00`  
**Tema:** Início do Capítulo 27! O que são métodos sobrecarregados (`overloaded methods`), o conceito fundamental de **Assinatura do Método** (`signature = name + parameters`), a criação do método `add(double a, double b)` somando dois números (`3.0`) e a demonstração intencional do erro clássico do compilador ao tentar somar três números: `actual and formal argument lists differ in length`.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está o passo a passo exato do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (A Teoria nos Comentários):**
   * O professor limpa o código do capítulo anterior e escreve nos comentários do `main`:
     ```java
     // overloaded methods = methods that share the same name,
     //                      but different parameters
     //                      signature = name + parameters
     ```
   * Ele seleciona a expressão `overloaded methods` com o mouse em azul para destacar o novo tema do curso.

2. **Imagem 2 (Minuto 4:20:50 — Criando e Rodando o `add` de 2 Números):**
   * Ele cria um método simples para somar dois números:
     ```java
     static double add(double a, double b){
         return a + b;
     }
     ```
   * No `main`, ele chama: `System.out.println(add(1, 2));`.
   * Ele clica no botão verde de **Play**. O console abre e imprime com sucesso:
     ```text
     3.0
     ```

3. **Imagem 3 (A Provocação do Erro no Editor):**
   * O professor diz: *"Mas e se eu quiser somar TRÊS números?"*.
   * Ele altera a chamada no `main` para:
     ```java
     System.out.println(add(1, 2, 3));
     ```
   * Imediatamente o IntelliJ sublinha `(1, 2, 3)` com uma cobrinha vermelha ondulada, coloca uma bolinha vermelha com ponto de exclamação ❗ na lateral e abre um aviso:
     `Expected 2 arguments but found 3` *(Esperava 2 argumentos, mas foram encontrados 3)*.

4. **Imagem 4 (Minuto 4:21:04 — O Erro de Compilação na Tela Vermelha):**
   * Para ensinar aos alunos como identificar esse erro na vida real, o professor clica em **Play** mesmo com o erro na tela.
   * O compilador do Java barra a execução e joga um erro vermelho na janela "Build Output":
     ```text
     java: method add in class Main cannot be applied to given types;
       required: double,double
       found:    int,int,int
       reason: actual and formal argument lists differ in length
     ```
   * O professor aponta com a mão/mouse para a linha do erro, preparando o palco para explicar a solução: **a sobrecarga de métodos**!

---

## 🧠 Teoria Fundamental: O Que São Métodos Sobrecarregados?

A palavra em inglês **"Overloaded"** significa **"Sobrecarregado"**. Em Java, isso não significa que o computador está cansado ou travando! 

Significa que **UM ÚNICO NOME DE MÉTODO** tem a "tarefa sobrecarregada" de conseguir trabalhar com formatos diferentes!

> **Definição do Bro Code:**
> *"Overloaded methods são métodos que compartilham o **MESMO NOME**, mas possuem **PARÂMETROS DIFERENTES**."*

### ✍️ O Conceito Vital: O que é a "Assinatura" (`Signature`) de um Método?

Em Java, a identidade de um método não é apenas o nome dele. A identidade oficial de um método é a sua **Assinatura**:

$$\mathbf{Assinatura} = \mathbf{Nome\ do\ M\acute{e}todo} + \mathbf{Par\hat{a}metros}$$

* O tipo de retorno (`void`, `double`, `int`) **NÃO** faz parte da assinatura!
* Apenas o **Nome** e a **Lista de Parâmetros** (quantos são e quais são seus tipos).

#### Exemplo:
Se temos `add(double a, double b)`:
* A assinatura é: `add(double, double)`.
* O Java olha para isso e diz: *"Este método só atende chamadas que entreguem EXATAMENTE 2 números"*.

---

## 🚨 Dessecando o Erro de Compilação da Imagem 4

Quando o professor tentou rodar `add(1, 2, 3)`, o Java gritou:
```text
java: method add in class Main cannot be applied to given types;
  required: double,double
  found:    int,int,int
  reason: actual and formal argument lists differ in length
```

Vamos traduzir isso palavra por palavra para português de iniciante:
1. **`cannot be applied to given types`**: *"O método `add` não consegue ser aplicado para os dados que você me entregou"*.
2. **`required: double,double`**: *"Eu fui construído exigindo exatamente DOIS números"*.
3. **`found: int,int,int`**: *"Mas você me mandou TRÊS números no lugar"*.
4. **`reason: actual and formal argument lists differ in length`**:  
   * **`formal argument`** (parâmetros da declaração): são 2 (`a` e `b`).
   * **`actual argument`** (argumentos enviados na chamada): são 3 (`1`, `2` e `3`).
   * **`differ in length`**: a contagem não bate! 2 é diferente de 3!

---

## 💻 Código do Teste 1 (Que Funciona) (`Main.java`)

```java
public class Main {
    public static void main(String[] args){

        // overloaded methods = methods that share the same name,
        //                      but different parameters
        //                      signature = name + parameters

        System.out.println(add(1, 2));
    }

    static double add(double a, double b){
        return a + b;
    }
}
```

---

## 🔍 Explicação Linha por Linha do Código Funcional

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto de entrada por onde o Java começa a executar.
* **Console:** **Nada**.

---

### **Linhas 3 a 8:** *(Comentários conceituais e linhas em branco)*
* **Linhas 5 a 7:** Explicam o que são overloaded methods e o que é a assinatura (`signature = name + parameters`).
* **Console:** **Nada**.

---

### **Linha 9:** `System.out.println(add(1, 2));`
* **O que faz:** **A CHAMADA BEM-SUCEDIDA:**
  1. O compilador vê `add(1, 2)` (2 argumentos).
  2. Procura na classe um método chamado `add` que aceite 2 números.
  3. Encontra na **Linha 12**: `add(double a, double b)`.
  4. Entrega `1.0` para `a` e `2.0` para `b`.
  5. Na Linha 13, calcula $1.0 + 2.0 = 3.0$ e devolve com `return`.
  6. O `println` imprime o número `3.0` no console.
* **Console:** Imprime:
  ```text
  3.0
  ```

---

### **Linha 10:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 11:** `}`
* **O que faz:** Fecha o método `main`. Fim do programa.
* **Console:** Exibe `Process finished with exit code 0`.

---

### **Linha 12:** `static double add(double a, double b){`
* **O que faz:** Declaração do método de soma:
  * `static`: para ser acessível pelo `main`.
  * `double`: devolve um número decimal.
  * `add`: nome da função ("somar").
  * `(double a, double b)`: recebe exatamente dois números.
* **Console:** **Nada**.

---

### **Linha 13:** `return a + b;`
* **O que faz:** Soma `a + b` e cospe o resultado de volta para quem chamou.
* **Console:** **Nada**.

---

### **Linha 14:** `}`
* **O que faz:** Fecha o método `add`.
* **Console:** **Nada**.

---

### **Linha 15:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console:

### Caso 1 (Com `add(1, 2)`):
```text
3.0

Process finished with exit code 0
```

### Caso 2 (Ao tentar rodar `add(1, 2, 3)`):
```text
java: method add in class Main cannot be applied to given types;
  required: double,double
  found:    int,int,int
  reason: actual and formal argument lists differ in length
```

---

## 🔮 O Spoiler do Que Vem a Seguir:
Para resolver esse erro e fazer `add(1, 2, 3)` funcionar sem quebrar o `add(1, 2)`, nós **NÃO** precisamos inventar um nome novo e feio como `addThreeNumbers`.  
Nós vamos simplesmente **criar outro método com o MESMO NOME `add`**, mas recebendo 3 parâmetros:
```java
static double add(double a, double b, double c){
    return a + b + c;
}
```
Isso é a mágica dos **Overloaded Methods**!
