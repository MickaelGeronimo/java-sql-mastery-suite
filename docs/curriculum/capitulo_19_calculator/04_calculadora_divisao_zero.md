# 🖩 Capítulo 19: Programa Calculadora (Calculator Program) — Parte 2: O Mistério do `Infinity`, a Divisão por Zero e o Bug do `0.0`

---

> **Nota para o seu Google Docs:**  
> Este documento foi elaborado com **100% de contexto independente**. Você não precisa das imagens do vídeo para entender: todos os testes, telas do console, detalhes do código e armadilhas que o autor mostrou estão minuciosamente descritos aqui.

---

## 📌 1. Visão Geral: O Que o Autor Fez Nessas 4 Imagens?

Depois de criar a calculadora básica, o autor começou a fazer testes de estresse para ver onde o programa falhava!

Neste trecho do vídeo (das marcas **3:04:00** até **3:06:29**), ele descobriu e mostrou **dois grandes problemas**:

1. **O Problema do `Infinity` (Imagens 1 e 2):**  
   Ele tentou dividir $3.14$ por $0$. Em vez de dar erro, o Java imprimiu a palavra: **`Infinity`** (Infinito)!
2. **A Tentativa de Consertar com Chaves `{ }` (Imagem 3):**  
   Ele colocou um `if(num2 == 0)` dentro do `case '/'`. Se o segundo número for zero, ele avisa: `"Cannot divide by zero!"`.
3. **O Novo Bug que Surgiu (Imagem 3):**  
   Ao testar de novo com zero, o console imprimiu:
   ```text
   Cannot divide by zero!
   0.0
   ```
   *Por que saiu esse `0.0` intruso embaixo da mensagem de erro?* Porque o programa continuou rodando e executou `System.out.println(result);` no final!
4. **O Teste do Operador Maluco `"pizza"` (Imagem 4):**  
   Ele digitou `"pizza"` no lugar do operador. O programa não fez conta nenhuma, mas ainda assim imprimiu **`0.0`** no final!

---

## ♾️ 2. Por Que o Java Mostrou `Infinity` na Imagem 1?

Na matemática da escola, **dividir qualquer número por zero é proibido / impossível**.

Mas veja a diferença curiosa de como o Java lida com isso:
- Se você dividir números **inteiros (`int`)** por zero: o Java trava na hora com um erro feio chamado `ArithmeticException: / by zero`.
- Mas se você dividir números **decimais (`double`)** por zero: o Java segue o padrão internacional de computação (IEEE-754) e responde a palavra **`Infinity`**!

Embora o programa não trave, uma calculadora do mundo real **não deve responder `Infinity`**, e sim avisar educadamente: *"Não é possível dividir por zero!"*.

---

## 🧱 3. Chaves `{ }` Dentro do Switch (Imagem 3)

Olhe como o autor escreveu o caso da divisão na linha 26:

```java
case '/' -> {
    if(num2 == 0){
        System.out.println("Cannot divide by zero!");
    }
    else{
        result = num1 / num2;
    }
}
```

### O que tem de especial aqui?
Nos outros casos (soma, subtração, multiplicação), o autor só precisava de **1 linha de comando**:
`case '+' -> result = num1 + num2;`

Mas na divisão, ele precisou fazer **mais de uma coisa**: precisou testar com `if` e `else`!  
Toda vez que você quiser colocar **mais de uma linha de código dentro de um `case`**, você é obrigado a abrir e fechar chaves `{ }` logo após a setinha `->`!

---

## 🐛 4. O Bug do `0.0` Intruso: Por Que Ele Apareceu?

Observe o que aconteceu no console da Imagem 3:
```text
Enter the first number: 3.14
Enter an operator (+, -, *, /, ^): /
Enter the second number: 0
Cannot divide by zero!
0.0   <--- ⚠️ DE ONDE VEIO ESSE NÚMERO?
```

E na Imagem 4, quando ele digitou o operador `"pizza"`:
```text
Enter the first number: 3.14
Enter an operator (+, -, *, /, ^): pizza
Enter the second number: 1
0.0   <--- ⚠️ DE NOVO O 0.0 INTRUSO!
```

### A Explicação:
1. Lá no topo do programa, criamos: `double result = 0;`.
2. Quando a divisão por zero acontece, o Java entra no `if` e imprime `"Cannot divide by zero!"`. A variável `result` continua valendo **`0`**.
3. O switch termina.
4. Logo abaixo do switch está a linha: `System.out.println(result);`.
5. O Java executa essa linha cegamente e imprime o `0.0`!

> **O que o autor vai fazer a seguir para consertar isso?**  
> Ele vai criar uma variável de segurança booleana chamada `boolean validOperation = true;`.  
> Se der divisão por zero ou operador inválido, ele marca `validOperation = false;`.  
> E só imprime o resultado se a operação tiver sido válida: `if(validOperation){ System.out.println(result); }`!

---

## 🖥️ 5. O Que Sai no Console em Cada Imagem? (Conforme Você Pediu)

### 🔴 Imagem 1: Teste da Divisão Ingênua por Zero
- **Primeiro número:** `3.14`
- **Operador:** `/`
- **Segundo número:** `0`
- **Saída no console:**
```text
Enter the first number: 3.14
Enter an operator (+, -, *, /, ^): /
Enter the second number: 0
Infinity
```

---

### 🟡 Imagem 3: Teste com o `if(num2 == 0)` Adicionado
- **Primeiro número:** `3.14`
- **Operador:** `/`
- **Segundo número:** `0`
- **O que o Java faz:** Entra no `case '/'`, vê que `num2 == 0`, imprime `"Cannot divide by zero!"`, sai do switch e imprime o `result` que vale `0.0`.
- **Saída no console:**
```text
Enter the first number: 3.14
Enter an operator (+, -, *, /, ^): /
Enter the second number: 0
Cannot divide by zero!
0.0
```

---

### 🟣 Imagem 4: Teste com o Operador Inválido `"pizza"`
- **Primeiro número:** `3.14`
- **Operador:** `pizza` *(o `charAt(0)` pega a letra `'p'`)*.
- **Segundo número:** `1`
- **O que o Java faz:** Não acha nenhum `case 'p'`. Não faz conta nenhuma. Chega no final e imprime o `result` que vale `0.0`.
- **Saída no console:**
```text
Enter the first number: 3.14
Enter an operator (+, -, *, /, ^): pizza
Enter the second number: 1
0.0
```

---

## 🔍 6. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 41)

Abaixo está o código exatamente como está na tela do IntelliJ do autor na Imagem 3:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa o leitor de teclado.

### **Linha 2:** ` ` *(em branco)*

### **Linha 3:** `public class Main {`
- **O que faz:** Abre a classe principal.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** Método `main`, ponto de ignição do programa.

### **Linha 5:** ` ` *(em branco)*

### **Linha 6:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Constrói o objeto de leitura de teclado.

### **Linha 7:** ` ` *(em branco)*

### **Linha 8:** `        double num1;`
- **O que faz:** Gaveta para o primeiro número digitado.

### **Linha 9:** `        double num2;`
- **O que faz:** Gaveta para o segundo número digitado.

### **Linha 10:** `        char operator;`
- **O que faz:** Gaveta `char` para guardar o símbolo da operação (`'+'`, `'-'`, etc.).

### **Linha 11:** `        double result = 0;`
- **O que faz:** Gaveta do resultado, inicializada com zero.

### **Linha 12:** ` ` *(em branco)*

### **Linha 13:** `        System.out.print("Enter the first number: ");`
- **O que faz:** Pede o primeiro número no terminal.

### **Linha 14:** `        num1 = scanner.nextDouble();`
- **O que faz:** Lê o primeiro número e salva em `num1`.

### **Linha 15:** ` ` *(em branco)*

### **Linha 16:** `        System.out.print("Enter an operator (+, -, *, /, ^): ");`
- **O que faz:** Mostra as opções de operadores.

### **Linha 17:** `        operator = scanner.next().charAt(0);`
- **O que faz:** Lê a palavra digitada e captura a primeira letra/símbolo com `charAt(0)`.

### **Linha 18:** ` ` *(em branco)*

### **Linha 19:** `        System.out.print("Enter the second number: ");`
- **O que faz:** Pede o segundo número no terminal.

### **Linha 20:** `        num2 = scanner.nextDouble();`
- **O que faz:** Lê o segundo número e salva em `num2`.

### **Linha 21:** ` ` *(em branco)*

### **Linha 22:** `        switch(operator){`
- **O que faz:** Inicia o switch para tomar a decisão com base em `operator`.

### **Linha 23:** `            case '+' -> result = num1 + num2;`
- **O que faz:** Se for `+`, calcula a soma.

### **Linha 24:** `            case '-' -> result = num1 - num2;`
- **O que faz:** Se for `-`, calcula a subtração.

### **Linha 25:** `            case '*' -> result = num1 * num2;`
- **O que faz:** Se for `*`, calcula a multiplicação.

### **Linha 26:** `            case '/' -> {`
- **O que faz:** Inicia o bloco de múltiplas linhas para a divisão com chaves `{`.

### **Linha 27:** `                if(num2 == 0){`
- **O que faz:** Testa: *"O segundo número é igual a zero?"*.

### **Linha 28:** `                    System.out.println("Cannot divide by zero!");`
- **O que faz:** Se o divisor for zero, imprime o aviso de erro.

### **Linha 29:** `                }`
- **O que faz:** Fecha o bloco do `if`.

### **Linha 30:** `                else{`
- **O que faz:** Senão (se o divisor não for zero, for um número válido)...

### **Linha 31:** `                    result = num1 / num2;`
- **O que faz:** Faz a divisão com segurança e salva em `result`.

### **Linha 32:** `                }`
- **O que faz:** Fecha o bloco do `else`.

### **Linha 33:** `            }`
- **O que faz:** Fecha o bloco do `case '/'`.

### **Linha 34:** `            case '^' -> result = Math.pow(num1, num2);`
- **O que faz:** Se for `^`, calcula a potência com `Math.pow`.

### **Linha 35:** `        }`
- **O que faz:** Fecha o bloco do `switch`.

### **Linha 36:** ` ` *(em branco)*

### **Linha 37:** `        System.out.println(result);`
- **O que faz:** Imprime o valor de `result` (o que causou a exibição indesejada de `0.0`).

### **Linha 38:** ` ` *(em branco)*

### **Linha 39:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado.

### **Linha 40:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 41:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 7. Código Completo Desta Etapa (Pronto para Copiar)

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
            case '/' -> {
                if(num2 == 0){
                    System.out.println("Cannot divide by zero!");
                }
                else{
                    result = num1 / num2;
                }
            }
            case '^' -> result = Math.pow(num1, num2);
        }

        System.out.println(result);

        scanner.close();
    }
}
```
