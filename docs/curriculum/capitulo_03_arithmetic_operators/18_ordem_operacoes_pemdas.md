# 🧮 Ordem de Operações no Java: A Regra do PEMDAS (Guia Completo para Iniciantes)

Se você nunca programou na vida, não se preocupe! Este guia foi feito para explicar **cada detalhe**, linha por linha, sem deixar nenhuma dúvida.

---

## 📋 1. O Código Completo (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // ORDER OF OPERATIONS [P-E-M-D-A-S]

        double result = 3 + 4 * (7 - 5) / 2.0;

        System.out.println(result);
    }
}
```

---

## 🔍 2. Explicação Linha por Linha (Sem pular nada!)

| Linha | Código | O que o Java faz aqui? |
| :---: | :--- | :--- |
| **1** | `public class Main {` | Cria a **classe principal** do programa. Em Java, todo código precisa estar dentro de uma classe com o mesmo nome do arquivo (`Main.java`). |
| **2** | `public static void main(String[] args) {` | É a **porta de entrada** do programa. Quando você clica em "Run" (▶️), o Java começa a ler a partir daqui. |
| **5** | `// ORDER OF OPERATIONS...` | É um **comentário**. O Java ignora tudo que começa com `//`. Serve só como anotação para o programador lembrar que a aula é sobre a regra PEMDAS. |
| **7** | `double result = 3 + 4 * (7 - 5) / 2.0;` | **A linha do cálculo:** Resolve a expressão matemática usando as regras do PEMDAS e guarda a resposta (`7.0`) na variável `result`. |
| **9** | `System.out.println(result);` | **A linha de exibição:** Pega o valor guardado dentro de `result` e mostra na tela do console. |
| **10** | `}` | Fecha o bloco do método `main`. |
| **11** | `}` | Fecha o bloco da classe `Main`. |

---

## 🔬 3. Desmontando a Linha 7 Peça por Peça

A linha `double result = 3 + 4 * (7 - 5) / 2.0;` tem várias partes importantes:

```
  double     result    =    [ 3 + 4 * (7 - 5) / 2.0 ]   ;
    │          │       │               │                │
 1. Tipo    2. Nome  3. Guarda    4. Conta Matemática  5. Ponto e vírgula
 (decimal)  da caixa  o valor     (calculada primeiro)  (fim da linha)
```

1. **`double`**: Avisa ao Java que a variável vai guardar um **número com casas decimais** (com ponto flutuante).
2. **`result`**: O **nome** da caixinha onde o valor final será guardado.
3. **`=`**: O símbolo de **atribuição** ("recebe"). Ele pega o resultado do lado direito e joga dentro do `result`.
4. **`2.0`**: Escrever `2.0` (com o `.0`) garante que o Java faça a divisão no modo decimal com precisão.
5. **`;`**: Toda ordem em Java **obrigatoriamente** termina com ponto e vírgula.

---

## 📦 4. Por que o `result` está dentro do `System.out.println(result);`?

Pense na relação entre a linha 7 e a linha 9 como uma fábrica:

```
[ Linha 7: A Cozinha ] ──→ Faz a receita: 3 + 4 * (7 - 5) / 2.0
                           Coloca o bolo pronto (7.0) na caixa "result"
                                 │
                                 ▼
[ Linha 9: O Garçom ]  ──→ System.out.println(result);
                           Leva a caixa "result" até o cliente (mostra 7.0 na tela)
```

### ⚠️ A Regra das Aspas:
* `System.out.println("result");` $\rightarrow$ **COM aspas:** Mostra a palavra literal **`result`**.
* `System.out.println(result);` $\rightarrow$ **SEM aspas:** Abre a variável e mostra o valor **`7.0`**.

---

## 👑 5. O que significa PEMDAS? (A Hierarquia da Matemática)

O computador **NÃO** calcula da esquerda para a direita. Ele obedece a ordem de prioridades:

```
🥇 1º P ──→ Parênteses       ( )
🥈 2º E ──→ Expoentes        Math.pow()
🥉 3º M ──→ Multiplicação    *  ┐ (Empatados: resolve
🥉 3º D ──→ Divisão / Resto  /  ┘  quem vier primeiro)
🎖️ 4º A ──→ Adição (Soma)    +  ┐ (Empatados: resolve
🎖️ 4º S ──→ Subtração        -  ┘  quem vier primeiro)
```

---

## 🧩 6. A Animação Passo a Passo da Resolução

$$\text{Expressão Original:} \quad 3 + 4 \times (7 - 5) \div 2.0$$

```
Passo 1: Resolve os Parênteses (7 - 5)
         3 + 4 * [ 2 ] / 2.0

Passo 2: Resolve a Multiplicação 4 * 2
         3 + [ 8 ] / 2.0

Passo 3: Resolve a Divisão 8 / 2.0
         3 + [ 4.0 ]

Passo 4: Resolve a Soma 3 + 4.0
         [ 7.0 ] ✅
```

---

## 🎯 7. Mini-Desafio para Você Testar sua Mente! 🧠

Qual é o resultado de:
```java
int teste = 10 - 2 * 3;
```

* Se você fizer da esquerda para a direita: $10 - 2 = 8 \rightarrow 8 \times 3 =$ **`24`** ❌ (Errado!)
* **Pelo PEMDAS:** A multiplicação $2 \times 3 = 6$ vem primeiro! Então $10 - 6 =$ **`4`** ✅ (Correto!)
