# ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 2: A Mágica de 1 Linha na Prática

---

## 📌 1. Visão Geral: O Que o Autor Fez Aqui?

Lembra que na aula anterior o autor tinha escrito aquele `if-else` grandão de 6 linhas?
```java
if(score >= 60){
    System.out.println("PASS");
}
else{
    System.out.println("FAIL");
}
```

Nestas duas imagens novas, **o autor apagou tudo aquilo** e substituiu pela fórmula mágica do **Operador Ternário** em **apenas 1 linha**:

```java
String passOrFail = (score >= 60) ? "PASS" : "FAIL";
System.out.println(passOrFail);
```

Ele testou dois números para provar que funciona perfeitamente:
1. **Com `score = 55`:** Imprimiu **`FAIL`** no console.
2. **Com `score = 75`:** Imprimiu **`PASS`** no console.

---

## 🔬 2. Dissecando a Linha Mágica (Peça por Peça)

Vamos quebrar a linha 11 em pedacinhos como se fosse um quebra-cabeça:

$$\mathbf{String} \quad \mathbf{passOrFail} \quad = \quad \mathbf{(score >= 60)} \quad \mathbf{?} \quad \mathbf{"PASS"} \quad \mathbf{:} \quad \mathbf{"FAIL"};$$

| Pedaço do Código | O Que Significa na Vida Real? | Função |
| :--- | :--- | :--- |
| `String passOrFail` | "Crie uma gaveta de texto chamada `passOrFail`" | A variável que vai guardar a palavra final. |
| `=` | "Guarde dentro dela o resultado da escolha:" | Operador de atribuição. |
| `(score >= 60)` | **A Pergunta:** *"A nota do aluno é 60 ou mais?"* | A condição que só pode dar Verdadeiro ou Falso. |
| `?` | **"E aí, é verdade?"** | Se for SIM, o Java pega o que está logo à direita. |
| `"PASS"` | **O Prêmio:** Valor escolhido se a resposta for SIM. | Será guardado na variável se a condição for `true`. |
| `:` | **"Senão..."** | Separa o valor verdadeiro do valor falso. |
| `"FAIL"` | **O Castigo:** Valor escolhido se a resposta for NÃO. | Será guardado na variável se a condição for `false`. |
| `;` | Ponto final da ordem em Java. | Obrigatório para encerrar a linha. |

---

## 🖥️ 3. O Que Sai no Console em Cada Imagem? (Conforme Você Pediu)

### 🔴 Imagem 1: Com `int score = 55;`
1. O Java lê a condição: `(55 >= 60)`.
2. $55$ é maior ou igual a $60$? **NÃO! (Falso / `false`)**.
3. Como deu Falso, o Java **ignora** o `"PASS"` e pula direto para o que está depois dos dois pontos `:` $\rightarrow$ pega o `"FAIL"`.
4. A variável `passOrFail` passa a valer `"FAIL"`.
5. A linha `System.out.println(passOrFail);` roda.
6. **O que sai no console:**
```text
FAIL
```

---

### 🟢 Imagem 2: Com `int score = 75;`
1. O autor mudou a nota para `75` e clicou no botão verde de Play.
2. O Java lê a condição: `(75 >= 60)`.
3. $75$ é maior ou igual a $60$? **SIM! (Verdadeiro / `true`)**.
4. Como deu Verdadeiro, o Java pega o valor que está logo após o `?` $\rightarrow$ pega o `"PASS"` e descarta o `"FAIL"`.
5. A variável `passOrFail` passa a valer `"PASS"`.
6. A linha `System.out.println(passOrFail);` roda.
7. **O que sai no console:**
```text
PASS
```

---

## 🔍 4. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 16)

Abaixo está o código completo exatamente como montado pelo Bro Code:

### **Linha 1:** ` ` *(em branco)*
- **O que faz:** Linha vazia inicial. Não precisamos de Scanner.

### **Linha 2:** `public class Main {`
- **O que faz:** Cria a classe principal `Main`.

### **Linha 3:** `    public static void main(String[] args) {`
- **O que faz:** O método `main`, ponto de ignição do Java.

### **Linha 4:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 5:** `        // ternary operator ? = Return 1 of 2 values if a condition is true`
- **O que faz:** Comentário de estudo: *"Operador ternário `?` = Retorna 1 de 2 valores se uma condição for verdadeira"*.

### **Linha 6:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 7:** `        // variable = (condition) ? ifTrue : ifFalse;`
- **O que faz:** Comentário com a fórmula que aprendemos a aplicar.

### **Linha 8:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 9:** `        int score = 55;` *(ou `75` no segundo teste)*
- **O que faz:** Cria a variável inteira chamada `score` e coloca o número `55` nela (depois alterado para `75`).

### **Linha 10:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 11:** `        String passOrFail = (score >= 60) ? "PASS" : "FAIL";`
- **O que faz:** A estrela do capítulo! O operador ternário avalia se a nota é 60 ou mais. Se for, escolhe `"PASS"`. Se não for, escolhe `"FAIL"`. O resultado escolhido é guardado na variável `passOrFail`.

### **Linha 12:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 13:** `        System.out.println(passOrFail);`
- **O que faz:** Imprime o texto guardado em `passOrFail` (`"PASS"` ou `"FAIL"`) no terminal e pula para a próxima linha.

### **Linha 14:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 15:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 16:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 5. Código Completo Pronto para Copiar e Testar

```java
public class Main {
    public static void main(String[] args) {

        // ternary operator ? = Return 1 of 2 values if a condition is true

        // variable = (condition) ? ifTrue : ifFalse;

        int score = 75; // Experimente trocar para 55 para ver o FAIL

        String passOrFail = (score >= 60) ? "PASS" : "FAIL";

        System.out.println(passOrFail);

    }
}
```

---

## ⚖️ 6. Comparativo: Jeito Antigo vs Jeito com Operador Ternário

| Critério | Com `if - else` Tradicional | Com Operador Ternário `? :` |
| :--- | :--- | :--- |
| **Linhas de código** | 6 a 8 linhas | **Apenas 1 linha!** |
| **Velocidade de leitura** | Ocupa muito espaço vertical | Rápido e direto ao ponto |
| **Quando usar?** | Quando você tem blocos grandes de tarefas | Quando você quer apenas **atribuir um valor** baseado em uma pergunta simples |
