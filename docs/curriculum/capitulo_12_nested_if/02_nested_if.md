# 🎟️ Capítulo 12: `nested if statements` — Parte 2 (A Árvore Completa de Descontos)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Fez Aqui?

Na etapa anterior, tínhamos apenas um teste simples: se era estudante ou não.  
Agora, o professor montou a **árvore completa de decisões** usando o `isSenior` (idoso / terceira idade).

Pense como o dono do cinema:
* Um estudante tem direito a **10% de desconto**.
* Um idoso tem direito a **20% de desconto**.
* E se a pessoa for um **idoso que também estuda na faculdade?** Ela tem direito aos **dois descontos somados (30% de desconto!)**.
* E se a pessoa não for estudante nem idoso? Paga o **preço normal cheio ($9.99)**.

---

## 🌳 2. A Árvore de Decisão Visual (Os 4 Caminhos Possíveis)

O computador segue um mapa de perguntas como se fossem portas com seguranças:

```text
                       [ Início: Preço = $9.99 ]
                                   │
                     Pergunta 1: É Estudante?
                                   │
              ┌────────────────────┴────────────────────┐
             SIM                                       NÃO
              │                                         │
    Pergunta 2: É Idoso?                      Pergunta 2: É Idoso?
       ┌──────┴──────┐                           ┌──────┴──────┐
      SIM           NÃO                         SIM           NÃO
       │             │                           │             │
    CASO 1        CASO 2                      CASO 3        CASO 4
 Estudante     Apenas Estudante            Apenas Idoso     Pessoa Comum
  + Idoso                                                (Sem Desconto)
 30% de Desc.   10% de Desc.               20% de Desc.   Preço Cheio
 (Paga 70%)     (Paga 90%)                 (Paga 80%)     (Paga 100%)
 `price *= 0.7` `price *= 0.9`             `price *= 0.8` `price *= 1`
```

---

## 🧮 3. A Matemática dos Descontos: Por que multiplicar por `0.7`, `0.9` e `0.8`?

Para um iniciante, ver `price *= 0.7` pode parecer um feitiço misterioso. Mas é matemática do dia a dia:

| Situação | Desconto Ganho | Quanto da conta você PAGA? | Como escrever em Java? |
| :--- | :---: | :---: | :---: |
| **Estudante + Idoso** | $10\% + 20\% = \mathbf{30\%}$ | $100\% - 30\% = \mathbf{70\%}$ ($0.70$) | `price *= 0.7;` |
| **Apenas Idoso** | $\mathbf{20\%}$ | $100\% - 20\% = \mathbf{80\%}$ ($0.80$) | `price *= 0.8;` |
| **Apenas Estudante** | $\mathbf{10\%}$ | $100\% - 10\% = \mathbf{90\%}$ ($0.90$) | `price *= 0.9;` |
| **Pessoa Comum** | $\mathbf{0\%}$ | $100\% - 0\% = \mathbf{100\%}$ ($1.00$) | `price *= 1;` |

> [!TIP]
> Multiplicar por `0.7` já abate 30% do preço diretamente, em uma única linha, sem precisar calcular o valor do desconto separado e depois subtrair!

---

## 🔬 4. O Código Explicado Linha por Linha com Microscópio

Vamos acompanhar cada linha do código que o professor escreveu:

### 🔹 As Caixinhas de Memória:
```java
boolean isStudent = false;
boolean isSenior = false;
double price = 9.99;
```
* `isStudent = false;`: Definimos que a pessoa **NÃO** é estudante.
* `isSenior = false;`: Definimos que a pessoa **NÃO** é idosa.
* `price = 9.99;`: O ingresso começa custando $9.99.

---

### 🔹 O Bloco dos Estudantes (O 1º `if` de fora):
```java
if (isStudent) {
    if (isSenior) {
        System.out.println("You get a senior discount of 20%");
        System.out.println("You get a student discount of 10%");
        price *= 0.7;
    }
    else {
        System.out.println("You get a student discount of 10%");
        price *= 0.9;
    }
}
```
* **`if (isStudent)`**: A 1ª porta de fora. Só entra quem tem carteirinha de estudante!
  * **`if (isSenior)`**: O `if` aninhado (porta de dentro). Se o estudante também for idoso, ganha 20% + 10% = 30% de desconto (`price *= 0.7`).
  * **`else` de dentro**: Se for estudante, mas **NÃO** for idoso, ganha só os 10% normais (`price *= 0.9`).

---

### 🔹 O Bloco dos NÃO-Estudantes (O `else` de fora):
```java
else {
    if (isSenior) {
        System.out.println("You get a senior discount of 20%");
        price *= 0.8;
    }
    else {
        price *= 1;
    }
}
```
* **`else`**: Quem não é estudante cai aqui!
  * **`if (isSenior)`**: Mas espera aí... será que essa pessoa, mesmo não sendo estudante, é idosa?  
    * Se for idosa, entra aqui e ganha 20% de desconto de idoso (`price *= 0.8`).
  * **`else` de dentro**: Não é estudante e também não é idosa. Não ganha desconto nenhum (`price *= 1`).

---

### 🔹 A Linha Final:
```java
System.out.println("The price of a ticket is: $" + price);
```
Mostra o valor que sobrou na variável `price`.

---

## 🖥️ 5. O Teste que o Professor Executou no Console

Nas variáveis do programa, o professor colocou:
```java
boolean isStudent = false;
boolean isSenior = false;
```

### O que o Java fez nos bastidores?
1. Chegou em `if (isStudent)`: Como é `false`, **pulou todo o bloco de estudante** e foi direto para o `else` de fora!
2. No `else`, chegou em `if (isSenior)`: Como é `false`, **pulou o bloco de idoso** e caiu no `else` interno!
3. Fez `price *= 1;` (o preço continuou $9.99).
4. Imprimiu na tela:
```text
The price of a ticket is: $9.99
```
Exatamente como apareceu na tela do vídeo! 🎯

---

## 💻 6. Código Completo e Pronto para Copiar e Rodar

Copie e cole este código no seu `Main.java`. Você pode trocar os valores de `isStudent` e `isSenior` entre `true` e `false` para ver os 4 descontos funcionando!

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 12: Nested if statements (Bilheteria com descontos)

        boolean isStudent = false;
        boolean isSenior = false;
        double price = 9.99;

        // 1. Verifica se é estudante
        if (isStudent) {
            // Se for estudante, checa se TAMBÉM é idoso
            if (isSenior) {
                System.out.println("You get a senior discount of 20%");
                System.out.println("You get a student discount of 10%");
                price *= 0.7; // Ganha 30% de desconto no total (paga 70%)
            }
            else {
                System.out.println("You get a student discount of 10%");
                price *= 0.9; // Ganha apenas 10% de estudante (paga 90%)
            }
        }
        // 2. Se NÃO for estudante
        else {
            // Checa se pelo menos é idoso
            if (isSenior) {
                System.out.println("You get a senior discount of 20%");
                price *= 0.8; // Ganha 20% de desconto de idoso (paga 80%)
            }
            else {
                price *= 1;   // Pessoa comum: sem desconto (paga 100%)
            }
        }

        // Mostra o resultado final
        System.out.println("The price of a ticket is: $" + price);
    }
}
```
