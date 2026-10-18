# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 16: Retorno Booleano (`boolean`), o Método `ageCheck` e a Refatoração Ninja (`return age >= 18;`)
**Timestamps do vídeo:** `4:17:xx` até `4:18:14 / 12:00:00`  
**Tema:** Novo tipo de retorno: `boolean` (`true` ou `false`). A construção do método de verificação de maioridade `ageCheck`, a dica de inspeção do IntelliJ IDEA e a substituição mágica de um bloco `if-else` de 7 linhas por uma única linha elegante: `return age >= 18;`.

---

## 🖼️ Contexto Visual dos 3 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens em mãos, aqui está a "novelinha" completa do que o professor **Bro Code** fez ao vivo na tela:

1. **Imagem 1 (Preparando o Terreno):**
   * O professor compactou o método `getFullName` anterior em uma única linha (Linha 21):
     ```java
     static String getFullName(String first, String last) { return first + " " + last; }
     ```
   * Ele limpou o interior do `main` (deixou as linhas 6 e 7 vazias), preparando-se para criar um novo método.
   * O cursor do mouse está na linha 24, logo abaixo dos outros métodos.

2. **Imagem 2 (O Jeito "Iniciante / Amador"):**
   * O professor cria um método com retorno **`boolean`** chamado `ageCheck` (verificador de idade):
     ```java
     static boolean ageCheck(int age){
         if(age >= 18){
             return true;
         }
         else{
             return false;
         }
     }
     ```
   * **Detalhe crucial da imagem:** O IntelliJ IDEA coloca uma linha ondulada amarela sob `if(age >= 18)` e exibe uma lâmpada 💡 na calha da linha 29.
   * Por que o IntelliJ fez isso? Porque a IDE está dizendo: *"Bro Code, você escreveu 7 linhas de código quando precisava de apenas UMA! Quer que eu simplifique para você?"*.

3. **Imagem 3 (Minuto 4:18:14 — O Jeito "Programador Sênior / Ninja"):**
   * O professor apaga todo aquele `if` e `else` e escreve uma única linha:
     ```java
     static boolean ageCheck(int age){
         return age >= 18;
     }
     ```
   * Na imagem, o trecho `age >= 18` está selecionado com um fundo azul brilhante para que todo mundo preste atenção nessa mágica.
   * O IntelliJ remove todos os avisos amarelos: o código agora está perfeito, elegante e ultra-profissional!

---

## 🧠 A Grande Virada: De `if-else` para `return age >= 18;`

Essa é uma das lições mais importantes que separam um **iniciante amador** de um **desenvolvedor profissional** em Java. Preste muita atenção na lógica:

### O Jeito Amador (Redundante):
```java
if (age >= 18) {
    return true;
} else {
    return false;
}
```
Pense comigo como se fosse uma conversa no mundo real:
> — *Amigo, a idade dele é maior ou igual a 18?*  
> — *Bom... se for verdade que é maior ou igual a 18, eu te respondo "verdade". Mas se não for, eu te respondo "mentira".*

Percebe como isso é repetitivo? A própria pergunta **`age >= 18`** já é uma pergunta cuja resposta só pode ser `true` ou `false`!

### O Jeito Ninja (Direto ao Ponto):
```java
return age >= 18;
```
Aqui o Java faz:
1. Pega a idade (vamos supor que seja `21`).
2. Avalia a expressão: $21 \ge 18$? **Sim, `true`!**
3. O `return` devolve diretamente o resultado dessa comparação: `true`!
4. Se a idade fosse `15`: $15 \ge 18$? **Não, `false`!** O `return` devolve `false`!

Uma única linha fez exatamente a mesma coisa que as 7 linhas anteriores, com o dobro de velocidade e sem poluir o código!

---

## 🚦 A Família dos Tipos de Retorno no Java

Veja como o seu cinto de utilidades está ficando completo:

| Tipo de Retorno | O que ele devolve? | Exemplo no Curso |
| :---: | :--- | :--- |
| **`void`** | **Nada** (só executa). | `happyBirthday("Patrick", 38);` |
| **`double`** | Número com casas decimais. | `square(3)` $\rightarrow$ `9.0` |
| **`String`** | Texto / Frases. | `getFullName("Spongebob", "Squarepants")` $\rightarrow$ `"Spongebob Squarepants"` |
| **`boolean`** | Verdadeiro ou Falso (`true` / `false`). | `ageCheck(21)` $\rightarrow$ `true` |

---

## 💻 Código Completo da Imagem 3 (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

    }

    static void happyBirthday(String name, int age){...}

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

Aqui está o raio-X completo das linhas mostradas na Imagem 3:

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto de entrada do programa.
* **Console:** **Nada**.

---

### **Linhas 3 a 5:** *(Linhas em branco e comentário do professor)*
* **Linha 4:** `// method = a block of reusable code that is executed when called ()`  
  Comentário que explica o conceito de método. O computador ignora.
* **Console:** **Nada**.

---

### **Linhas 6 a 8:** *(Linhas em branco dentro do `main`)*
* **O que faz:** O professor limpou o interior do `main` temporariamente enquanto ensinava a criar e refatorar o método `ageCheck`. Como o `main` está vazio, ele não chama ninguém neste exato momento (por isso aparece `no usages` cinza ao lado do método).
* **Console:** **Nada**.

---

### **Linha 9:** `}`
* **O que faz:** Fecha o método `main`.
* **Console:** **Nada**.

---

### **Linha 10:** `static void happyBirthday(String name, int age){...}`
* **O que faz:** Método que canta parabéns (recolhido na tela).
* **Console:** **Nada**.

---

### **Linha 11:** `static double square(double number) { return number * number; }`
* **O que faz:** Método que eleva ao quadrado em uma linha só.
* **Console:** **Nada**.

---

### **Linha 12:** `static double cube(double number) { return number * number * number; }`
* **O que faz:** Método que eleva ao cubo em uma linha só.
* **Console:** **Nada**.

---

### **Linha 13:** `static String getFullName(String first, String last) { return first + " " + last; }`
* **O que faz:** Método que junta nomes e devolve `String`, agora também compactado em uma linha só.
* **Console:** **Nada**.

---

### **Linha 14:** `static boolean ageCheck(int age){`
* **O que faz:** **A DECLARAÇÃO DO NOVO MÉTODO BOOLEANO:**
  * `static`: para podermos chamá-lo diretamente pelo `main`.
  * `boolean`: **O CONTRATO DE RETORNO**. Diz: *"Eu juro devolver um valor lógico: ou `true` (verdadeiro) ou `false` (falso)"*.
  * `ageCheck`: nome da função ("verificação de idade").
  * `(int age)`: o parâmetro (receptor) que espera receber um número inteiro representando a idade de uma pessoa.
* **Console:** **Nada**.

---

### **Linha 15:** `return age >= 18;`
* **O que faz:** **A LINHA DESTAQUE:**
  1. O computador compara a variável `age` com o número 18: `age >= 18`.
  2. O resultado dessa comparação matemática é um valor booleano (`true` se for maior ou igual a 18, `false` se for menor).
  3. A palavra mágica `return` pega esse resultado booleano e arremessa de volta para quem chamou o método!
* **Console:** **Nada** (o valor viaja internamente pela memória).

---

### **Linha 16:** `}`
* **O que faz:** Fecha o corpo do método `ageCheck`.
* **Console:** **Nada**.

---

### **Linha 17:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que sai no Console se Rodar Agora?

Como o `main` ainda está vazio (o professor ainda não colocou a chamada do `ageCheck` dentro do `main`), se você apertar o Play agora:

```text
Process finished with exit code 0
```

*(O programa roda em 0 segundos, não imprime nada porque nenhuma ordem de impressão foi dada no `main`, e finaliza com sucesso).*

No próximo print, veremos o professor chamar `ageCheck` de dentro do `main` passando idades para testar!
