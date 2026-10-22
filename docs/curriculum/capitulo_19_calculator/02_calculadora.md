# 🖩 Capítulo 19: Programa Calculadora (Calculator Program) — Parte 1: Estrutura, Leitura de `char` e Operações no `switch`

---

> **Nota para o seu Google Docs:**  
> Este documento foi escrito com **100% de contexto independente**. Mesmo sem ter as imagens do vídeo ao lado, você entenderá exatamente cada detalhe visual, cada linha de código, os avisos do IntelliJ e os resultados dos testes.

---

## 📌 1. Visão Geral: O Que Está Acontecendo Neste Capítulo?

Bem-vindo ao **Capítulo 19: Programa Calculadora** (*Calculator Program*)!

Aqui, o autor cria uma calculadora completa de terminal capaz de fazer 5 operações matemáticas:
- **Soma (`+`)**
- **Subtração (`-`)**
- **Multiplicação (`*`)**
- **Divisão (`/`)**
- **Potenciação / Elevação (`^`)**, como $2^3 = 8$!

### 🎬 O Que Aconteceu nas 4 Imagens do Vídeo:

1. **Imagem 1 (2:57:52) — O Trailer / Demonstração do Projeto:**  
   O autor mostra a calculadora pronta no console e testa um caso especial: tentar dividir por zero ($3.14 \div 0$). O programa avisa: `"Cannot divide by zero!"` (Não é possível dividir por zero). Mostra também a variável de controle `validOperation`.
2. **Imagem 2 (3:01:37) — Começando a Construir do Zero:**  
   Ele cria as variáveis: `num1`, `num2`, o operador (`operator`) do tipo `char`, e o resultado (`result`). Faz as três perguntas no teclado com o `Scanner`.
3. **Imagem 3 (3:03:24) — O Switch com as 5 Contas e o Sublinhado Vermelho:**  
   Ele cria o `switch(operator)` com `+`, `-`, `*`, `/` e `^`.  
   * **Um detalhe visual importantíssimo:** Na linha 30 (`System.out.println(result);`), a palavra `result` ficou com uma **cobrinha vermelha ondulada** embaixo! (Explicaremos exatamente o porquê disso no tópico 4).
4. **Imagem 4 — O Primeiro Teste Bem-Sucedido:**  
   Ele testa uma soma: primeiro número `3.14`, operador `+`, segundo número `1.1`. O console responde perfeitamente: **`4.24`**!

---

## 🔤 2. A Grande Novidade: A Variável `char` e o Truque do `.charAt(0)`

Até agora, você já usou:
- `int` para números inteiros (`10`).
- `double` para números com vírgula (`3.14`).
- `String` para textos compridos (`"Friday"`).

### O que é o tipo `char`?
`char` vem de *character* (caractere). Ele guarda **apenas UMA ÚNICA letra ou símbolo** e é escrito entre **aspas simples** `' '` (e não aspas duplas `" "`):
- Exemplo: `'+'`, `'-'`, `'*'`, `'A'`.

### Como ler um `char` com o Scanner? (Linha 17)
O `Scanner` do Java tem `nextInt()`, `nextDouble()` e `nextLine()`, mas **NÃO TEM** `nextChar()`!  
Por isso, os programadores Java usam esse truque genial:
```java
operator = scanner.next().charAt(0);
```
1. `scanner.next()`: Lê o que você digitou como se fosse um texto (`String`).
2. `.charAt(0)`: Pega a **primeira letra/símbolo** (na posição zero) desse texto!  
   Se você digitou `+`, ele pega o caractere `'+'` e guarda na gaveta `char operator`.

---

## ⚡ 3. As 5 Operações Matemáticas no `switch`

Olhe a beleza do Enhanced Switch cuidando das 5 contas:

```java
switch(operator){
    case '+' -> result = num1 + num2;
    case '-' -> result = num1 - num2;
    case '*' -> result = num1 * num2;
    case '/' -> result = num1 / num2;
    case '^' -> result = Math.pow(num1, num2);
}
```

- Se o operador for `'+'` $\rightarrow$ Soma os dois números.
- Se o operador for `'-'` $\rightarrow$ Subtrai os dois números.
- Se o operador for `'*'` $\rightarrow$ Multiplica os dois números.
- Se o operador for `'/'` $\rightarrow$ Divide os dois números.
- Se o operador for `'^'` $\rightarrow$ Usa `Math.pow(num1, num2)` para elevar a base ao expoente! (Ex: $2^3 = 8.0$).

---

## 🚨 4. O Mistério da Linha 30: Por Que Ficou Vermelho na Imagem 3?

Na Imagem 3, a linha 30 aparece com um aviso vermelho:
```java
System.out.println(result); // ⚠️ O IntelliJ sublinhou 'result' em vermelho!
```

### O que o compilador do Java está reclamando?
O Java pensou assim:
> *"Ei, programador! Você criou a variável `double result;` vazia, sem nenhum valor inicial. Se o usuário digitar um símbolo maluco como `$` ou `@`, o seu switch não vai entrar em nenhum dos 5 casos! Aí quando o programa chegar aqui na linha 30 para imprimir `result`, o que ele vai imprimir se `result` nunca recebeu nada? Vai dar erro!"*

O nome desse erro em Java é:  
**`Variable 'result' might not have been initialized`**  
*(A variável 'result' pode não ter sido inicializada)*.

### Como consertar isso?
Basta dar um valor inicial para ela lá em cima na linha 11:
```java
double result = 0; // Agora o Java fica calmo e o vermelho some!
```
*(Mais adiante no capítulo, o autor também vai adicionar a variável booleana `validOperation` e o `default` para resolver isso com maestria!)*

---

## 🖥️ 5. O Que Sai no Console? (Conforme Você Pediu)

### 🟢 Teste da Imagem 4 (Operação de Soma):
1. **Primeira Pergunta:**
   ```text
   Enter the first number: 3.14
   ```
   *(O usuário digitou `3.14` e deu Enter)*.
2. **Segunda Pergunta:**
   ```text
   Enter an operator (+, -, *, /, ^): +
   ```
   *(O usuário digitou `+` e deu Enter)*.
3. **Terceira Pergunta:**
   ```text
   Enter the second number: 1.1
   ```
   *(O usuário digitou `1.1` e deu Enter)*.
4. **O Cálculo:**  
   O Java vai no `case '+'`:  
   $$3.14 + 1.1 = \mathbf{4.24}$$
5. **Saída impressa no console:**
```text
4.24
```

---

## 🔍 6. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 34)

Abaixo está o código exatamente como visto na tela do autor na Imagem 3 e 4:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa a ferramenta para ler o teclado.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 3:** `public class Main {`
- **O que faz:** Casa principal onde o programa reside.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** Ponto de partida obrigatório do Java.

### **Linha 5:** ` ` *(em branco)*

### **Linha 6:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Liga o leitor de teclado com o nome `scanner`.

### **Linha 7:** ` ` *(em branco)*

### **Linha 8:** `        double num1;`
- **O que faz:** Gaveta para o primeiro número digitado (ex: `3.14`).

### **Linha 9:** `        double num2;`
- **O que faz:** Gaveta para o segundo número digitado (ex: `1.1`).

### **Linha 10:** `        char operator;`
- **O que faz:** **Novidade!** Gaveta do tipo `char` que guarda apenas 1 símbolo matemático (`'+'`, `'-'`, `'*'`, `'/'` ou `'^'`).

### **Linha 11:** `        double result = 0;`
- **O que faz:** Gaveta para guardar o resultado final do cálculo. (Começar valendo `0` evita o erro vermelho do Java).

### **Linha 12:** ` ` *(em branco)*

### **Linha 13:** `        System.out.print("Enter the first number: ");`
- **O que faz:** Pede o primeiro número no console.

### **Linha 14:** `        num1 = scanner.nextDouble();`
- **O que faz:** Lê o primeiro número decimal e salva em `num1`.

### **Linha 15:** ` ` *(em branco)*

### **Linha 16:** `        System.out.print("Enter an operator (+, -, *, /, ^): ");`
- **O que faz:** Mostra as opções de operadores matemáticos disponíveis.

### **Linha 17:** `        operator = scanner.next().charAt(0);`
- **O que faz:** **O truque do char!** Lê o texto e extrai o primeiro símbolo da posição zero (`charAt(0)`).

### **Linha 18:** ` ` *(em branco)*

### **Linha 19:** `        System.out.print("Enter the second number: ");`
- **O que faz:** Pede o segundo número no console.

### **Linha 20:** `        num2 = scanner.nextDouble();`
- **O que faz:** Lê o segundo número decimal e salva em `num2`.

### **Linha 21:** ` ` *(em branco)*

### **Linha 22:** `        switch(operator){`
- **O que faz:** Abre o switch para tomar a decisão baseada no símbolo guardado em `operator`.

### **Linha 23:** `            case '+' -> result = num1 + num2;`
- **O que faz:** Se o operador for `'+'`, soma `num1 + num2` e guarda em `result`.

### **Linha 24:** `            case '-' -> result = num1 - num2;`
- **O que faz:** Se o operador for `'-'`, subtrai `num1 - num2`.

### **Linha 25:** `            case '*' -> result = num1 * num2;`
- **O que faz:** Se o operador for `'*'`, multiplica `num1 * num2`.

### **Linha 26:** `            case '/' -> result = num1 / num2;`
- **O que faz:** Se o operador for `'/'`, divide `num1 / num2`.

### **Linha 27:** `            case '^' -> result = Math.pow(num1, num2);`
- **O que faz:** Se o operador for `'^'`, eleva `num1` à potência de `num2` usando a função matemática `Math.pow`.

### **Linha 28:** `        }`
- **O que faz:** Fecha o bloco do `switch`.

### **Linha 29:** ` ` *(em branco)*

### **Linha 30:** `        System.out.println(result);`
- **O que faz:** Imprime o resultado do cálculo na tela.

### **Linha 31:** ` ` *(em branco)*

### **Linha 32:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado.

### **Linha 33:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 34:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 7. Código Completo Pronto para Copiar e Rodar

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double num1;
        double num2;
        char operator;
        double result = 0;

        System.out.print("Enter the first number: ");
        num1 = scanner.nextDouble();

        System.out.print("Enter an operator (+, -, *, /, ^): ");
        operator = scanner.next().charAt(0);

        System.out.print("Enter the second number: ");
        num2 = scanner.nextDouble();

        switch(operator){
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
            case '^' -> result = Math.pow(num1, num2);
        }

        System.out.println(result);

        scanner.close();
    }
}
```

> **Dicas de teste para você rodar:**
> 1. **Soma:** `3.14`, `+`, `1.1` $\rightarrow$ Resultado: `4.24`.
> 2. **Multiplicação:** `5`, `*`, `4` $\rightarrow$ Resultado: `20.0`.
> 3. **Potência:** `2`, `^`, `3` $\rightarrow$ Resultado: `8.0` (2 elevado a 3).
