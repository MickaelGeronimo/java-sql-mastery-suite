# 🏦 Capítulo 11: Calculadora de Juros Compostos — Parte 3 (O `printf` Profissional)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que Mudou Nesta Etapa?

Na etapa anterior, o nosso programa já fazia a conta certa, mas a linha que mostrava o resultado na tela tinha ficado feia e improvisada:

```java
// Jeito antigo (com println e cheio de +):
System.out.println("The amount after " + years + " is: $" + amount);
```

**Os problemas desse jeito antigo:**
1. Faltava a palavra `"years"` no meio da frase.
2. Usava um monte de aspas e sinais de mais `+` (concatenação confusa).
3. O dinheiro saía com casas decimais esquisitas (ex: `$10300.0` com só uma casa decimal em vez de centavos).

Agora, o professor aplicou o que ensinou no Capítulo 10 e trocou essa linha pelo **`System.out.printf()`**!

---

## 🔬 2. A Evolução do Código (A Nova Linha 30)

Olhe como a linha 30 ficou no código final:

```java
System.out.printf("The amount after %d years is $%.2f", years, amount);
```

Vamos dissecar essa obra de arte com um microscópio:

### 🧩 Como a frase funciona (O Molde e as Peças):
Dentro das aspas temos o **molde do texto**, com dois "buracos" reservados para receber variáveis:

```text
"The amount after  %d  years is $  %.2f  " ,  years , amount
                   |               |           |        |
                   |               |        1ª Peça   2ª Peça
                   |               |           |        |
                   +---------------+-----------+        |
                                   |                    |
                                   +--------------------+
```

### 1️⃣ O Primeiro Buraco: `%d`
* **Para quem é:** Para a variável `years`.
* **Por que `%d`?** Porque a variável `years` é um número inteiro (`int`). A letra `d` vem de *decimal integer*.
* **O detalhe corrigido:** O professor escreveu a palavra **`years`** logo depois do `%d`! Agora a frase vai dizer certinho: *"after 2 years is..."*.

### 2️⃣ O Segundo Buraco: `$%.2f`
* **Para quem é:** Para a variável `amount`.
* **O que é o `$`: ** É apenas o símbolo do dólar/cifrão impresso como texto normal.
* **Por que `%.2f`?** 
  * O `f` significa *floating-point* (número decimal com vírgula/ponto, que é o nosso `double`).
  * O **`.2`** é a **precisão**: diz ao Java para cortar e arredondar para **exatamente 2 casas decimais** (os centavos do dinheiro!).

### 3️⃣ A Ordem das Variáveis (Depois da Vírgula):
Repare que depois de fechar as aspas do molde, vem uma vírgula e as variáveis na ordem exata dos buracos:
```java
..., years, amount);
```
* O 1º buraco (`%d`) puxa a 1ª variável (`years`).
* O 2º buraco (`%.2f`) puxa a 2ª variável (`amount`).

---

## 🖥️ 3. O Teste Real no Console

O professor rodou o programa com novos valores de teste:
1. **Capital Inicial (`principal`):** `10000` (Dez mil dólares)
2. **Taxa de Juros (`rate`):** `2` (2% ao ano $\rightarrow$ virou `0.02` no código)
3. **Vezes ao ano (`timesCompounded`):** `4` (Juros trimestrais, ou seja, rende 4 vezes por ano)
4. **Quantidade de anos (`years`):** `2` (O dinheiro fica rendendo por 2 anos)

### 🧮 O Cálculo Realizado pelo Java:
$$\text{amount} = 10000 \times \left(1 + \frac{0.02}{4}\right)^{4 \times 2} = 10000 \times (1.005)^8 \approx 10407.0704...$$

### 📢 O que o `printf` imprimiu na tela:
```text
The amount after 2 years is $10407.07
```
Veja que maravilha:
* O número `2` entrou perfeitamente no lugar do `%d`.
* A palavra `years` apareceu no lugar certo.
* O valor `10407.0704...` foi arredondado para exatamente **`$10407.07`** com 2 casas de centavos graças ao `%.2f`!

---

## 🧐 4. O Porquê da Seleção com o Mouse no Console!

No vídeo, o professor selecionou com o mouse o número `10407` no console.  
Por que ele fez isso?  
Para chamar a atenção de que **ainda falta a vírgula de milhar**!

* Atualmente saiu: `$10407.07`
* O padrão contábil perfeito seria: **`$10,407.07`**

Para colocar essa vírgula, basta usar a flag de vírgula que aprendemos:
```java
// Com a flag de vírgula (,):
System.out.printf("The amount after %d years is $%,.2f\n", years, amount);
```
Isso coloca a vírgula separadora de milhar automaticamente!

---

## 📊 5. Comparativo Visual: Antes vs. Depois

| Critério | Com `println` (Antigo) | Com `printf` (Novo) |
| :--- | :--- | :--- |
| **Linha de Código** | `"The amount after " + years + " is: $" + amount` | `"The amount after %d years is $%.2f", years, amount` |
| **Legibilidade do Código** | Confusa, cheia de aspas e sinais de `+` | Limpa, uma frase contínua com variáveis no final |
| **Controle de Casas Decimais** | Nulo (saiu `$10300.0` com 1 casa) | Total (`%.2f` garante sempre 2 casas de centavos) |
| **Resultado no Console** | `The amount after 1 is: $10300.0` | `The amount after 2 years is $10407.07` |

---

## 💻 6. Código Completo e Atualizado (Pronto para Copiar e Rodar)

Copie e cole no seu arquivo `Main.java`:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Compound interest calculator (Calculadora de Juros Compostos)

        Scanner scanner = new Scanner(System.in);

        double principal;
        double rate;
        int timesCompounded;
        int years;
        double amount;

        // 1. Entrada de dados
        System.out.print("Enter the principal amount: ");
        principal = scanner.nextDouble();

        System.out.print("Enter the interest rate (in %): ");
        rate = scanner.nextDouble() / 100;

        System.out.print("Enter the # of times compounded per year: ");
        timesCompounded = scanner.nextInt();

        System.out.print("Enter the # of years: ");
        years = scanner.nextInt();

        // 2. Cálculo matemático
        amount = principal * Math.pow(1 + rate / timesCompounded, timesCompounded * years);

        // 3. Exibição profissional com printf
        System.out.printf("The amount after %d years is $%.2f", years, amount);

        // 4. Fechamento do scanner
        scanner.close();
    }
}
```
