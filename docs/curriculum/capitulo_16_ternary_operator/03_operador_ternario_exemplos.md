# ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 3: Dois Novos Exemplos (Par/Ímpar e A.M./P.M.)

---

## 📌 1. Visão Geral: O Que o Autor Mostrou Aqui?

Depois de mostrar o exemplo da nota da escola (`PASS` ou `FAIL`), o autor mostrou que o operador ternário serve para **qualquer decisão binária (que só tem 2 respostas)** da vida real!

Nessas 4 imagens, ele ensinou **dois novos programas práticos**:

1. **Exemplo 1: Descobrir se um número é Par ou Ímpar** (Imagens 1, 2 e 3):
   - Usa o operador de resto da divisão (`% 2 == 0`).
   - Se for par $\rightarrow$ mostra **`EVEN`** (Par em inglês).
   - Se for ímpar $\rightarrow$ mostra **`ODD`** (Ímpar em inglês).
   - Testou com `number = 3` (saiu `ODD`) e depois com `number = 4` (saiu `EVEN`).

2. **Exemplo 2: Descobrir se a hora é de manhã (A.M.) ou de tarde/noite (P.M.)** (Imagem 4):
   - Usa o relógio de 24 horas (`hours < 12`).
   - Se a hora for menor que 12 $\rightarrow$ mostra **`A.M.`** (Manhã).
   - Se for 12 ou mais $\rightarrow$ mostra **`P.M.`** (Tarde/Noite).
   - Testou com `hours = 13` (saiu `P.M.`, porque 13h é 1 da tarde).

---

## 🍕 2. Relembrando o Operador de Módulo (`%`): A Analogia dos Brigadeiros

Na linha 11 do primeiro exemplo aparece: `(number % 2 == 0)`.

Se você nunca entendeu esse símbolo de porcentagem `%` em programação, pense assim:  
**O `%` não calcula porcentagem! Ele calcula o RESTO que sobra na divisão!**

### A Analogia dos Brigadeiros divididos entre 2 pessoas:
* Se você tem **4 brigadeiros** e divide para 2 pessoas:
  - Cada uma ganha 2.
  - Sobra algum brigadeiro no prato? **NÃO! Sobram 0 brigadeiros!**
  - Então: `4 % 2 == 0` é **VERDADEIRO (`true`)** $\rightarrow$ O número 4 é **PAR (`EVEN`)**!

* Se você tem **3 brigadeiros** e divide para 2 pessoas:
  - Cada uma ganha 1 brigadeiro inteiro.
  - Sobra **1 brigadeiro** no prato!
  - Como sobrou 1, o resto NÃO é 0!
  - Então: `3 % 2 == 0` é **FALSO (`false`)** $\rightarrow$ O número 3 é **ÍMPAR (`ODD`)**!

---

## 🎯 EXEMPLO 1: Par ou Ímpar (`EVEN` ou `ODD`)

### 🔬 A Linha Mágica:
```java
String evenOrOdd = (number % 2 == 0) ? "EVEN" : "ODD";
```

- `String evenOrOdd`: Cria a caixinha de texto para guardar a resposta.
- `(number % 2 == 0)`: **A pergunta:** *"O resto da divisão desse número por 2 é zero?"*.
- `? "EVEN"`: Se for **SIM** (resto zero), escolha `"EVEN"` (Par).
- `: "ODD"`: Se for **NÃO** (sobrou resto), escolha `"ODD"` (Ímpar).

---

### 🖥️ O Que Sai no Console no Exemplo 1:

#### 🔴 Teste com `int number = 3;` (Imagem 2):
1. O Java calcula $3 \% 2 = 1$.
2. Pergunta: $1 == 0$? Resposta: **NÃO (Falso)**.
3. O Java pula o `"EVEN"` e escolhe o que está depois dos dois pontos: `"ODD"`.
4. A linha `System.out.println(evenOrOdd);` imprime:
```text
ODD
```

#### 🟢 Teste com `int number = 4;` (Imagem 3):
1. O Java calcula $4 \% 2 = 0$.
2. Pergunta: $0 == 0$? Resposta: **SIM (Verdadeiro)**.
3. O Java escolhe o que está logo após a interrogação: `"EVEN"`.
4. A linha `System.out.println(evenOrOdd);` imprime:
```text
EVEN
```

---

### 📝 Linha por Linha Enumerada (Exemplo 1: Par ou Ímpar)

- **Linha 1:** ` ` *(em branco)*
- **Linha 2:** `public class Main {` — Casa principal da classe.
- **Linha 3:** `    public static void main(String[] args) {` — Ponto onde o programa começa.
- **Linha 4:** ` ` *(em branco)*
- **Linha 5:** `        // ternary operator ? = Return 1 of 2 values if a condition is true` — Anotação do conceito.
- **Linha 6:** ` ` *(em branco)*
- **Linha 7:** `        // variable = (condition) ? ifTrue : ifFalse;` — Anotação da fórmula.
- **Linha 8:** ` ` *(em branco)*
- **Linha 9:** `        int number = 3;` *(ou `4`)* — Guarda o número a ser testado.
- **Linha 10:** ` ` *(em branco)*
- **Linha 11:** `        String evenOrOdd = (number % 2 == 0) ? "EVEN" : "ODD";` — Se o resto da divisão por 2 for 0, guarda `"EVEN"`; senão, guarda `"ODD"`.
- **Linha 12:** ` ` *(em branco)*
- **Linha 13:** `        System.out.println(evenOrOdd);` — Imprime o resultado na tela (`ODD` ou `EVEN`).
- **Linha 14:** ` ` *(em branco)*
- **Linha 15:** `    }` — Fecha o método `main`.
- **Linha 16:** `}` — Fecha a classe `Main`.

---

## ⏰ EXEMPLO 2: Manhã ou Tarde (`A.M.` ou `P.M.`)

Na Imagem 4, o autor troca para um relógio militar de 24 horas:

```java
int hours = 13;

String timeOfDay = (hours < 12) ? "A.M." : "P.M.";

System.out.println(timeOfDay);
```

### 🔬 A Linha Mágica:
- `(hours < 12)`: **A pergunta:** *"A hora atual é menor do que 12 (meio-dia)?"*.
- `? "A.M."`: Se for **SIM** (ex: 8h, 9h, 11h), escolha `"A.M."` (Manhã).
- `: "P.M."`: Se for **NÃO** (ex: 12h, 13h, 20h), escolha `"P.M."` (Tarde/Noite).

---

### 🖥️ O Que Sai no Console no Exemplo 2:

#### 🟣 Teste com `int hours = 13;` (Imagem 4):
1. O Java avalia: $13 < 12$?
2. Resposta: **NÃO! (Falso)**, porque 13 é maior que 12.
3. O Java pula o `"A.M."` e escolhe `"P.M."`.
4. A variável `timeOfDay` recebe `"P.M."`.
5. **O que sai no console:**
```text
P.M.
```

*(Se você testar colocando `hours = 9;`, o console imprimirá `A.M.`!)*

---

### 📝 Linha por Linha Enumerada (Exemplo 2: A.M. / P.M.)

- **Linha 1:** ` ` *(em branco)*
- **Linha 2:** `public class Main {` — Declara a classe.
- **Linha 3:** `    public static void main(String[] args) {` — Ponto de entrada do Java.
- **Linha 4:** ` ` *(em branco)*
- **Linha 5:** `        // ternary operator ? = Return 1 of 2 values if a condition is true` — Comentário explicativo.
- **Linha 6:** ` ` *(em branco)*
- **Linha 7:** `        // variable = (condition) ? ifTrue : ifFalse;` — Fórmula do operador.
- **Linha 8:** ` ` *(em branco)*
- **Linha 9:** `        int hours = 13;` — Cria a variável inteira `hours` com valor 13 (13 horas).
- **Linha 10:** ` ` *(em branco)*
- **Linha 11:** `        String timeOfDay = (hours < 12) ? "A.M." : "P.M.";` — Se `hours < 12`, escolhe `"A.M."`; caso contrário, escolhe `"P.M."`.
- **Linha 12:** ` ` *(em branco)*
- **Linha 13:** `        System.out.println(timeOfDay);` — Imprime o resultado (`P.M.`) no terminal.
- **Linha 14:** ` ` *(em branco)*
- **Linha 15:** `    }` — Fecha o método `main`.
- **Linha 16:** `}` — Fecha a classe `Main`.

---

## 💻 3. Códigos Completos Prontos para Você Testar no IntelliJ

### Código 1: Par ou Ímpar (Even or Odd)
```java
public class Main {
    public static void main(String[] args) {

        // ternary operator ? = Return 1 of 2 values if a condition is true

        // variable = (condition) ? ifTrue : ifFalse;

        int number = 3; // Troque por 4 para ver imprimir EVEN!

        String evenOrOdd = (number % 2 == 0) ? "EVEN" : "ODD";

        System.out.println(evenOrOdd);

    }
}
```

---

### Código 2: Horário A.M. ou P.M.
```java
public class Main {
    public static void main(String[] args) {

        // ternary operator ? = Return 1 of 2 values if a condition is true

        // variable = (condition) ? ifTrue : ifFalse;

        int hours = 13; // Troque por 9 para ver imprimir A.M.!

        String timeOfDay = (hours < 12) ? "A.M." : "P.M.";

        System.out.println(timeOfDay);

    }
}
```
