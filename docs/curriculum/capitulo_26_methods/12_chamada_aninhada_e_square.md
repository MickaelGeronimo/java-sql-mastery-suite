# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 12: Chamada Aninhada Direta no `println` e Execução Real (`9.0`)
**Contexto:** O professor corrigiu o erro de digitação do ponto e vírgula da tela anterior, deixando a chamada direta `System.out.println(square(3));`, apertou o botão **Run** (Executar) e o console exibiu o resultado `9.0` com sucesso!  
**Tema:** Como o Java avalia chamadas aninhadas (de dentro para fora), a eliminação da variável temporária `result` e o fluxo completo de ida e volta do `return`.

---

## 🖼️ Contexto Visual do Print (Para quem está lendo no Google Docs)

Se você copiou este conteúdo para o seu Google Docs e não está com a imagem em mãos, aqui está exatamente o que aparece na tela do professor **Bro Code**:

1. **Janela Superior (Editor de Código):**
   * O professor corrigiu o erro da tela anterior: removeu aquele ponto e vírgula acidental de dentro dos parênteses.
   * A Linha 7 agora está perfeitamente limpa:
     ```java
     System.out.println(square(3));
     ```
   * O cursor do mouse está bem em cima do argumento `(3)`.
   * O método `happyBirthday` continua recolhido na linha 9 como `{...}`.
   * O método `square` está declarado logo abaixo (visível o início na linha 10):
     ```java
     static double square(double number){
         return number * number;
     }
     ```

2. **Janela Inferior (Console de Execução / Aba "Run"):**
   * A janela preta inferior subiu porque o professor apertou o botão verde de **Run** (Executar).
   * Na primeira linha do console aparece o comando do sistema iniciando a JVM do Java 23 (`java.exe`).
   * Logo abaixo, em destaque verde brilhante, aparece o resultado impresso na tela:
     ```text
     9.0
     ```
   * E logo abaixo a confirmação de sucesso:
     ```text
     Process finished with exit code 0
     ```

---

## 🧅 O Conceito: Chamada Aninhada (A Camada de Cebola)

Na parte 11, nós vimos que o professor fez assim:
```java
// Jeito Tradicional (2 passos):
double result = square(3);    // 1º passo: guarda na gaveta "result"
System.out.println(result);   // 2º passo: imprime o que está na gaveta
```

Agora, na parte 12, ele simplificou para **1 linha só**:
```java
// Jeito Direto / Aninhado (1 passo):
System.out.println(square(3));
```

### Como o Java "Pensa" Quando Vê Parênteses Dentro de Parênteses?
O Java sempre resolve expressões matemáticas e chamadas de método **de dentro para fora**, exatamente igual a uma expressão numérica da escola ou uma camada de cebola:

$$\text{System.out.println}(\ \underbrace{\text{square(3)}}_{\text{Passo 1: Calcula primeiro}}\ )$$

1. **Primeiro (O Miolo):** O Java olha o que está dentro do `println` e vê `square(3)`.
2. **O Salto:** Ele faz uma pausa no `println`, corre lá no método `square`, entrega o `3`, faz $3 \times 3 = 9.0$ e o `return` traz o `9.0` de volta.
3. **A Substituição:** O pedaço `square(3)` é substituído pelo valor que voltou: `9.0`.
4. **Por Fim (A Casca):** Agora o comando vira simplesmente:
   ```java
   System.out.println(9.0);
   ```
5. E a tela exibe:
   ```text
   9.0
   ```

> [!TIP]
> **Quando usar cada jeito?**
> * Se você só quer **mostrar o número na tela uma única vez**, use `System.out.println(square(3));`. É mais limpo e economiza linhas de código!
> * Se você vai **usar esse número para outras contas** depois (por exemplo: somar com outro valor, salvar no banco de dados, comparar num `if`), aí sim vale a pena guardar numa variável: `double result = square(3);`.

---

## 💻 Código Completo e Pronto para Rodar (`Main.java`)

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        System.out.println(square(3));
    }

    static double square(double number){
        return number * number;
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

Aqui está a autópsia completa de cada linha de código, o que acontece na memória do computador e o que sai (ou não sai) no console:

### **Linha 1:** `public class Main {`
* **O que faz:** Declara a classe pública `Main`. É o contêiner geral onde mora todo o nosso código Java.
* **O que sai no console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** O método principal (`main`). É a porta de entrada por onde o Java sempre começa a executar qualquer programa.
* **O que sai no console:** **Nada**.

---

### **Linha 3:** *(Linha em branco)*
* **O que faz:** Espaço em branco para deixar a leitura agradável. O compilador ignora.
* **O que sai no console:** **Nada**.

---

### **Linha 4:** `// method = a block of reusable code that is executed when called ()`
* **O que faz:** Comentário explicativo escrito pelo professor Bro Code: *"método = um bloco de código reutilizável que é executado quando chamado ()"*.
* **O que sai no console:** **Nada** (comentários são 100% invisíveis na execução).

---

### **Linha 5:** *(Linha em branco)*
* **O que faz:** Espaçamento visual.
* **O que sai no console:** **Nada**.

---

### **Linha 6:** *(Linha em branco)*
* **O que faz:** Espaçamento visual.
* **O que sai no console:** **Nada**.

---

### **Linha 7:** `System.out.println(square(3));`
* **O que faz:** **A LINHA ESTRELA DA AULA (Chamada Aninhada)!**
  1. O compilador pausa a ordem de imprimir e avalia primeiro o miolo: `square(3)`.
  2. O Java viaja até a Linha 10 (onde está o método `square`).
  3. Entrega o valor `3` para o parâmetro `number`.
  4. Executa a Linha 11: calcula $3 \times 3 = 9.0$.
  5. O comando `return` arremessa o número `9.0` de volta para a Linha 7.
  6. A linha vira internamente `System.out.println(9.0);`.
  7. O `println` pega esse `9.0`, imprime na tela preta e pula para a próxima linha.
* **O que sai no console:**
  ```text
  9.0
  ```

---

### **Linha 8:** `}`
* **O que faz:** Fecha as chaves do método `main`. O programa principal terminou todas as suas instruções.
* **O que sai no console:** **Nada** (o console exibe a linha do sistema `Process finished with exit code 0`).

---

### **Linha 9:** *(Linha em branco)*
* **O que faz:** Separação entre o `main` e os outros métodos da classe.
* **O que sai no console:** **Nada**.

---

### **Linha 10:** `static double square(double number){`
* **O que faz:** **DECLARAÇÃO DO MÉTODO `square`:**
  * `static`: para podermos chamá-lo diretamente de dentro do `main` estático.
  * `double`: **O CONTRATO DE RETORNO**. Diz: *"Eu prometo que entrego de volta um número decimal (`double`)"*.
  * `square`: o nome da ferramenta ("elevar ao quadrado").
  * `(double number)`: o parâmetro receptor. Cria uma variável local chamada `number` que recebe o argumento enviado (recebeu `3.0`).
* **O que sai no console:** **Nada**.

---

### **Linha 11:** `return number * number;`
* **O que faz:** **O CORAÇÃO DO CÁLCULO E DEVOLUÇÃO:**
  * Multiplica o valor de `number` por ele mesmo: $3 \times 3 = 9.0$.
  * O comando `return` devolve esse `9.0` para quem chamou e encerra a execução do método imediatamente.
* **O que sai no console:** **Nada** (o retorno devolve o valor internamente pela memória, sem imprimir nada por conta própria).

---

### **Linha 12:** `}`
* **O que faz:** Fecha o corpo do método `square`.
* **O que sai no console:** **Nada**.

---

### **Linha 13:** `}`
* **O que faz:** Fecha a classe `Main`.
* **O que sai no console:** **Nada**.

---

## 📺 O que aparece no Console ao Executar:

```text
9.0

Process finished with exit code 0
```

* **Por que saiu `9.0` e não apenas `9`?**  
  Porque declaramos o método como `double`! Em Java:
  * `int` (inteiro) $\rightarrow$ exibiria `9`.
  * `double` (ponto flutuante / decimal) $\rightarrow$ sempre exibe a parte decimal, mesmo que seja zero: `9.0`.

---

## ⚡ Tabela Comparativa: As Duas Formas de Fazer

| Característica | Forma 1: Com Variável | Forma 2: Aninhada Direta (Desta Aula) |
| :--- | :--- | :--- |
| **Sintaxe** | `double result = square(3);`<br>`System.out.println(result);` | `System.out.println(square(3));` |
| **Número de Linhas** | 2 linhas | 1 linha |
| **Uso de Memória** | Cria uma variável temporária `result` | Não cria variável temporária |
| **Quando Escolher?** | Quando você vai reutilizar esse valor em outros cálculos adiante. | Quando o seu único objetivo é mostrar o valor na tela de imediato. |
| **Resultado no Console** | `9.0` | `9.0` (Exatamente o mesmo!) |
