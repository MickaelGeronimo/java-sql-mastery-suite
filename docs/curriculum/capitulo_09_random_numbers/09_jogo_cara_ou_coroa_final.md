# 🪙 O Jogo de Cara ou Coroa Completo: `random` + `if-else`!

Exatamente como adiantamos na aula anterior, o Bro Code não queria que o programa mostrasse apenas um sem-graça `true` ou `false` na tela.

Ele juntou o **sorteio da moeda** com o **`if-else`** para criar um **jogo de Cara ou Coroa de verdade**!

---

## 📋 1. Código Completo do Jogo (Pronto para copiar)

```java
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        boolean isHeads;

        isHeads = random.nextBoolean();

        if (isHeads) {
            System.out.println("HEADS");
        }
        else {
            System.out.println("TAILS");
        }
    }
}
```

---

## ⚽ 2. A Analogia do Juiz de Futebol

Pense no início de um jogo de futebol quando o juiz chama os dois capitães no círculo central:

```
                  [ JUIZ JOGA A MOEDA PRO AR ]
                  isHeads = random.nextBoolean();
                                │
                                ▼
                   ┌───────────────────────────┐
                   │  isHeads é verdadeiro?    │
                   │  (Caiu Cara?)             │
                   └─────────────┬─────────────┘
                                 │
                   ┌─────────────┴─────────────┐
             (SIM) │                           │ (NÃO)
                   ▼                           ▼
        🖥️ Imprime: "HEADS"         🖥️ Imprime: "TAILS"
             (Deu Cara!)                 (Deu Coroa!)
```

---

## 🧠 3. O Passo a Passo do que Aconteceu na Imagem 2 (Deu `TAILS`!)

Acompanhe os pensamentos do Java na hora que o autor clicou no botão verde de "Play":

```
[LINHA 6]: Random random = new Random();
   💭 "Liguei a máquina de sorteio."

[LINHA 10]: isHeads = random.nextBoolean();
   🪙 "Joguei a moeda para cima... e ela caiu virada em COROA!"
   👉 "Portanto, a variável isHeads recebeu o valor: false!"

[LINHA 12]: if (isHeads)
   💭 "O valor de isHeads é true?"
   👉 "NÃO (é false)! Então NÃO posso entrar aqui. Pula para o else!"

[LINHA 15]: else {
   🎉 "Caiu no else! Vou executar o que tem aqui dentro!"

[LINHA 16]: System.out.println("TAILS");
   🖥️ Imprime no console: TAILS
```

---

## 🔍 4. Tabela Linha por Linha Completa

| Linha | Código | O que significa na prática? |
| :---: | :--- | :--- |
| **1** | `import java.util.Random;` | Traz a ferramenta de sorteios para o código. |
| **6** | `Random random = new Random();` | Monta o gerador de sorteios. |
| **8** | `boolean isHeads;` | Cria a caixinha do tipo booleano chamada `isHeads` ("É Cara?"). |
| **10** | `isHeads = random.nextBoolean();` | Sorteia: 50% de chance de ser `true`, 50% de ser `false`. |
| **12** | `if (isHeads) {` | **Teste:** Se deu Cara (`true`), entra aqui! |
| **13** | `    System.out.println("HEADS");` | Imprime `"HEADS"` (Cara) na tela. |
| **14** | `}` | Fecha o bloco do `if`. |
| **15** | `else {` | Se NÃO deu Cara (ou seja, deu Coroa / `false`), vem para cá! |
| **16** | `    System.out.println("TAILS");` | Imprime `"TAILS"` (Coroa) na tela. |
| **17** | `}` | Fecha o bloco do `else`. |

---

## 🎮 5. Como Testar no Seu Computador:

Se você rodar esse programa várias vezes seguidas clicando no "Play":
* Às vezes vai aparecer **`HEADS`**.
* Às vezes vai aparecer **`TAILS`**.
* Exatamente como jogar uma moeda na vida real! 🪙✨
