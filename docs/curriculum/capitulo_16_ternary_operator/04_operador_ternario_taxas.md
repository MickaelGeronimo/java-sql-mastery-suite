# ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 4: A Grande Novidade com Números (`double` e Imposto)

---

## 📌 1. Visão Geral: O Que o Autor Ensinou de Novo Aqui?

Até agora, todos os exemplos que vimos retornavam **palavras** (`String`):
- `"PASS"` ou `"FAIL"`
- `"EVEN"` ou `"ODD"`
- `"A.M."` ou `"P.M."`

Nessas novas imagens (das marcas **2:40:30** até **2:40:50**), o autor trouxe a **grande revelação**:  
👉 **O operador ternário NÃO serve apenas para texto! Ele pode devolver NÚMEROS (`double`, `int`), booleanos ou qualquer outro tipo de dado!**

O autor criou um sistema da **Receita Federal (Cálculo de Imposto)**:
- Se você ganha bem (R$ 40.000 ou mais), paga **25% de imposto** (`0.25`).
- Se você ganha menos (abaixo de R$ 40.000), paga **15% de imposto** (`0.15`).

---

## 💰 2. O Cenário da Vida Real: Como Funciona a Regra do Imposto?

Imagine que você foi contratado pelo governo para criar a regra do Imposto de Renda:

| Variável no Código | Significado no Mundo Real | Tipo de Gaveta |
| :--- | :--- | :--- |
| `int income = 60000;` | Sua renda / salário anual (\$60.000) | `int` (número inteiro) |
| `double taxRate;` | A porcentagem / alíquota do imposto | `double` (número com ponto decimal) |

### A Regra:
- Ganha **40.000 ou mais** ($\ge 40000$)? $\rightarrow$ Paga taxa de **`0.25`** (25%).
- Ganha **menos que 40.000**? $\rightarrow$ Paga taxa reduzida de **`0.15`** (15%).

---

## 🔬 3. Dissecando a Linha Mágica (Peça por Peça)

Observe com atenção a linha 11:

$$\mathbf{double} \quad \mathbf{taxRate} \quad = \quad \mathbf{(income >= 40000)} \quad \mathbf{?} \quad \mathbf{0.25} \quad \mathbf{:} \quad \mathbf{0.15};$$

1. **`double taxRate`**:  
   Repare que agora **NÃO É `String`**! É **`double`**!  
   Por que? Porque os valores que vamos guardar (`0.25` e `0.15`) são números com ponto!
2. **`=`**:  
   "Guarde na caixinha `taxRate` o valor que for escolhido".
3. **`(income >= 40000)`**:  
   **A Pergunta:** *"A renda é maior ou igual a 40.000?"*.
4. **`? 0.25`**:  
   Se a resposta for **SIM (Verdadeiro)**, escolha o número decimal **`0.25`** (sem aspas, porque é número!).
5. **`: 0.15`**:  
   Se a resposta for **NÃO (Falso)**, escolha o número decimal **`0.15`**.
6. **`;`**:  
   Ponto final da instrução.

---

## 🖥️ 4. O Que Deve Sair no Console em Cada Teste? (Conforme Você Pediu)

### 🟢 Teste 1: Com `int income = 60000;` (Imagens 1 e 2)
1. O Java olha a condição: `(60000 >= 40000)`.
2. $60.000$ é maior ou igual a $40.000$? **SIM! (Verdadeiro / `true`)**.
3. O Java pega o valor logo após a interrogação `?`: **`0.25`**.
4. A variável `taxRate` recebe `0.25`.
5. A linha `System.out.println(taxRate);` é executada.
6. **O que sai no console:**
```text
0.25
```

---

### 🟡 Teste 2: Com `int income = 30000;` (Imagem 3)
1. O autor trocou o salário para `30000` e rodou o código.
2. O Java olha a condição: `(30000 >= 40000)`.
3. $30.000$ é maior ou igual a $40.000$? **NÃO! (Falso / `false`)**.
4. O Java ignora o `0.25` e pega o valor após os dois pontos `:`: **`0.15`**.
5. A variável `taxRate` recebe `0.15`.
6. A linha `System.out.println(taxRate);` é executada.
7. **O que sai no console:**
```text
0.15
```

---

## 🎯 5. Por Que o Autor Destacou a Linha 7 na Imagem 4?

Na Imagem 4, o autor selecionou com o mouse a linha:
```java
// variable = (condition) ? ifTrue : ifFalse;
```

Ele fez isso para provar para a turma que **a fórmula nunca muda**, não importa o que você esteja fazendo:

| Fórmula Geral | Exemplo das Notas (Aula 1) | Exemplo do Imposto (Aula de Agora) |
| :--- | :--- | :--- |
| `variable` | `String passOrFail` | `double taxRate` |
| `(condition)` | `(score >= 60)` | `(income >= 40000)` |
| `? ifTrue` | `? "PASS"` | `? 0.25` |
| `: ifFalse` | `: "FAIL"` | `: 0.15` |

---

## 🔍 6. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 16)

Abaixo está o código completo exatamente como montado pelo Bro Code na tela:

### **Linha 1:** ` ` *(em branco)*
- **O que faz:** Espaço visual no início do arquivo.

### **Linha 2:** `public class Main {`
- **O que faz:** A estrutura da classe onde o programa existe.

### **Linha 3:** `    public static void main(String[] args) {`
- **O que faz:** O método `main`, botão de partida de qualquer programa Java.

### **Linha 4:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 5:** `        // ternary operator ? = Return 1 of 2 values if a condition is true`
- **O que faz:** Comentário em inglês explicando o propósito do operador ternário.

### **Linha 6:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 7:** `        // variable = (condition) ? ifTrue : ifFalse;`
- **O que faz:** Comentário com a fórmula mestre do operador ternário.

### **Linha 8:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 9:** `        int income = 60000;` *(ou `30000`)*
- **O que faz:** Cria a variável inteira chamada `income` (renda anual) e guarda o valor `60000` (ou `30000`).

### **Linha 10:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 11:** `        double taxRate = (income >= 40000) ? 0.25 : 0.15;`
- **O que faz:** **A grande novidade!** Cria a variável decimal `taxRate`. Se a renda for maior ou igual a 40.000, guarda a taxa `0.25` (25%). Se não for, guarda a taxa `0.15` (15%).

### **Linha 12:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 13:** `        System.out.println(taxRate);`
- **O que faz:** Imprime o valor numérico da taxa de imposto (`0.25` ou `0.15`) no console.

### **Linha 14:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 15:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 16:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 7. Código Completo Pronto para Copiar e Testar

```java
public class Main {
    public static void main(String[] args) {

        // ternary operator ? = Return 1 of 2 values if a condition is true

        // variable = (condition) ? ifTrue : ifFalse;

        int income = 60000; // Experimente trocar para 30000 para ver imprimir 0.15!

        double taxRate = (income >= 40000) ? 0.25 : 0.15;

        System.out.println(taxRate);

    }
}
```
