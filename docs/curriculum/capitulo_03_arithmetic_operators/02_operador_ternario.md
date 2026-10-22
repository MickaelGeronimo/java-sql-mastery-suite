# ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 1: Introdução e o `if-else` Tradicional

---

## 📌 1. Visão Geral: O Que Está Acontecendo Aqui?

Bem-vindo ao **Capítulo 16: Operador Ternário** (*Ternary Operator*)!

Neste começo de aula (das marcas **2:35:26** até **2:36:19**), o autor está preparando o terreno. 

Em vez de jogar a sintaxe nova direto na sua cara, ele fez o seguinte:
1. **Escreveu uma "cola" nos comentários (linhas 5 e 7):** Explicou o que é o operador ternário e qual é a fórmula mágica dele.
2. **Montou o exemplo do jeito "antigo" (com `if-else` tradicional):** Criou um sistema de boletim escolar para testar se um aluno passou (`"PASS"`) ou reprovou (`"FAIL"`).
3. **Fez dois testes de nota:**
   - Com `score = 70` $\rightarrow$ No console sai: **`PASS`**.
   - Com `score = 55` $\rightarrow$ No console sai: **`FAIL`**.

> **O objetivo do autor:**  
> Ele quer te mostrar que o `if-else` tradicional gasta **6 linhas inteiras** de código só para decidir uma palavrinha. Logo em seguida, ele vai te ensinar a enxugar tudo isso para **1 linha só**!

---

## 💡 2. O Que Raios é "Operador Ternário"?

O nome parece assustador, mas a ideia é a coisa mais simples do mundo!

### De onde vem essa palavra "Ternário"?
- **Unário:** Trabalha com **1** coisa só.  
  *(Exemplo: `x++` ou `-5`)*.
- **Binário:** Trabalha com **2** coisas.  
  *(Exemplo: `a + b`, `10 * 2` ou `x == y`)*.
- **Ternário:** Trabalha com **3** coisas juntas!

Ele é o **único operador do Java que tem 3 partes**:
1. **A Pergunta:** Uma condição que dá Verdadeiro ou Falso (ex: `score >= 60`).
2. **O Prêmio se for Verdade (`?`):** O valor que você ganha se a resposta for SIM.
3. **O Castigo se for Mentira (`:`):** O valor que você ganha se a resposta for NÃO.

---

## 🧙‍♂️ 3. A Fórmula Mágica (Linha 7 do Código)

O autor escreveu este comentário de ouro na linha 7:

```java
// variable = (condition) ? ifTrue : ifFalse;
```

Traduzindo para o português do dia a dia:

$$\text{variável} = (\text{pergunta?}) \ ? \ \text{valor se SIM} \ : \ \text{valor se NÃO};$$

* O ponto de interrogação `?` significa: *"Isso é verdade?"*.
* Os dois pontos `:` significam: *"Caso contrário, se for mentira, use isto:"*.

*(Guardamos essa fórmula com carinho porque o autor vai usá-la no próximo passo!)*

---

## 🖥️ 4. O Que Deve Sair no Console? (Conforme Você Pediu)

O autor montou um boletim com a nota de corte igual a **60**:
- Quem tirar **60 ou mais** ($\ge 60$) $\rightarrow$ Passou (`PASS`).
- Quem tirar **menos de 60** $\rightarrow$ Reprovou (`FAIL`).

### 🟢 Teste 1: Quando a nota é 70 (`score = 70`)
1. O Java olha a linha 11: `if(score >= 60)`
2. Pergunta: *"70 é maior ou igual a 60?"*
3. Resposta: **SIM! Verdadeiro (`true`)**.
4. O Java entra no `if` e ignora o `else`.
5. **O que sai no console:**
```text
PASS
```

---

### 🔴 Teste 2: Quando a nota é 55 (`score = 55`)
1. O Java olha a linha 11: `if(score >= 60)`
2. Pergunta: *"55 é maior ou igual a 60?"*
3. Resposta: **NÃO! Falso (`false`)**.
4. O Java pula o `if` e cai de cabeça no `else`.
5. **O que sai no console:**
```text
FAIL
```

---

## 🔍 5. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 19)

Abaixo está o código exatamente como está na tela do IntelliJ do Bro Code:

### **Linha 1:** ` ` *(em branco)*
- **O que faz:** Linha vazia no início do arquivo. Não precisa de `Scanner` porque desta vez a nota está escrita direto no código (valor fixo).

### **Linha 2:** `public class Main {`
- **O que faz:** Cria a classe principal `Main`, que é a estrutura onde todo o código fica guardado.

### **Linha 3:** `    public static void main(String[] args) {`
- **O que faz:** O método `main`. É o botão de ignição do Java; todo programa começa a rodar a partir daqui.

### **Linha 4:** ` ` *(em branco)*
- **O que faz:** Linha vazia para deixar o código fácil de ler.

### **Linha 5:** `        // ternary operator ? = Return 1 of 2 values if a condition is true`
- **O que faz:** Um comentário explicativo em inglês deixado pelo autor:  
  *"Operador ternário `?` = Retorna 1 de 2 valores se uma condição for verdadeira"*.  
  O Java não executa essa linha; serve apenas de anotação de aula.

### **Linha 6:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 7:** `        // variable = (condition) ? ifTrue : ifFalse;`
- **O que faz:** A fórmula matemática de como o operador ternário funciona:  
  `variável = (condição) ? valor_se_verdade : valor_se_falso;`

### **Linha 8:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 9:** `        int score = 70;` *(ou `int score = 55;` no segundo teste)*
- **O que faz:** Cria uma gaveta de número inteiro (`int`) chamada `score` (pontuação / nota do aluno) e guarda o número `70` nela (depois alterado para `55` para testar o outro caso).

### **Linha 10:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 11:** `        if(score >= 60){`
- **O que faz:** Faz o teste: *"A nota guardada em `score` é maior ou igual a 60?"*. Se for verdade, o Java entra nas chaves `{ }`.

### **Linha 12:** `            System.out.println("PASS");`
- **O que faz:** Imprime a palavra `"PASS"` (Aprovado) na tela e salta para a linha de baixo.

### **Linha 13:** `        }`
- **O que faz:** Fecha as chaves do bloco do `if`.

### **Linha 14:** `        else{`
- **O que faz:** Significa "Senão...". Se a nota **NÃO** for 60 ou mais (ou seja, for menor que 60), o Java entra aqui.

### **Linha 15:** `            System.out.println("FAIL");`
- **O que faz:** Imprime a palavra `"FAIL"` (Reprovado) na tela e salta de linha.

### **Linha 16:** `        }`
- **O que faz:** Fecha as chaves do bloco do `else`.

### **Linha 17:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 18:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 19:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 6. Código Completo para Você Copiar e Testar

### Versão para testar o Aprovado (`PASS`):
```java
public class Main {
    public static void main(String[] args) {

        // ternary operator ? = Return 1 of 2 values if a condition is true

        // variable = (condition) ? ifTrue : ifFalse;

        int score = 70;

        if(score >= 60){
            System.out.println("PASS");
        }
        else{
            System.out.println("FAIL");
        }

    }
}
```

### Versão para testar o Reprovado (`FAIL`):
*(Basta trocar a linha 9 para `int score = 55;`)*
```java
public class Main {
    public static void main(String[] args) {

        // ternary operator ? = Return 1 of 2 values if a condition is true

        // variable = (condition) ? ifTrue : ifFalse;

        int score = 55;

        if(score >= 60){
            System.out.println("PASS");
        }
        else{
            System.out.println("FAIL");
        }

    }
}
```

---

## 🎯 7. O Que Vem a Seguir?

Olhe bem para as **linhas 11 a 16** (são 6 linhas!):
```java
if(score >= 60){
    System.out.println("PASS");
}
else{
    System.out.println("FAIL");
}
```

Na próxima tela, o autor vai te mostrar como substituir todo esse pedaço gigante por **apenas 1 linha** usando o operador ternário:

```java
String passOrFail = (score >= 60) ? "PASS" : "FAIL";
System.out.println(passOrFail);
```

Fica infinitamente mais curto e elegante!
