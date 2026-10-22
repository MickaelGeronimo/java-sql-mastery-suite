# 🏦 Capítulo 11: Calculadora de Juros Compostos — Parte 2 (A Fórmula Matemática e o Resultado)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. Onde Estávamos e o Que Entrou de Novo?

Na etapa anterior, nós criamos as 5 caixinhas de memória e fizemos as 4 perguntas para o usuário no teclado:
1. `principal` (quanto dinheiro foi investido)
2. `rate` (taxa de juros dividida por 100)
3. `timesCompounded` (quantas vezes rende no ano)
4. `years` (quantos anos vai ficar rendendo)

Agora, o professor colocou a **fórmula matemática** para calcular o montante final (`amount`) e imprimiu o resultado no console!

---

## 📐 2. A Fórmula de Juros Compostos: Do Papel para o Java

Na lousa de matemática financeira, a fórmula clássica é escrita assim:

$$A = P \left(1 + \frac{r}{n}\right)^{nt}$$

Se você nunca viu essa fórmula na vida, **não se assuste**! Ela é apenas uma receita de bolo com 5 letrinhas:

* **$A$** (*Amount*): Montante final (o saldo acumulado com os juros).
* **$P$** (*Principal*): O valor inicial investido.
* **$r$** (*Rate*): A taxa de juros anual em decimal (ex: `0.05` para 5%).
* **$n$**: Número de vezes que o juro rende por ano (`timesCompounded`).
* **$t$**: O tempo em anos (`years`).

---

## 🔬 3. Como o Java Traduz Essa Fórmula? (Linha 28)

Olhe a linha mágica que o professor escreveu no código:

```java
amount = principal * Math.pow(1 + rate / timesCompounded, timesCompounded * years);
```

Vamos dissecar essa linha com um microscópio, passo a passo:

```text
amount = principal * Math.pow(   BASE   ,   EXPOENTE   );
                                 |                 |
                   (1 + rate / timesCompounded)    (timesCompounded * years)
```

### 1ª Conexão: Por que tem um asterisco `*` depois de `principal`?
No papel de matemática, quando duas coisas estão coladas (ex: $P [ \dots ]$), significa multiplicação invisível.  
No computador, o Java **não adivinha**! É obrigatório colocar o símbolo de multiplicação: `*`.

### 2ª Conexão: Como fazer o "número voando lá em cima" (Potência)?
No papel, escrevemos o $^{nt}$ pequenininho lá no alto.  
Mas no teclado do computador não existe tecla de "número voando".  
Por isso, lembramos do **Capítulo 9**: usamos a ferramenta **`Math.pow(base, expoente)`**!
* **O 1º argumento (a Base):** É o que está dentro dos colchetes $\rightarrow$ `1 + rate / timesCompounded`.  
  *(O Java divide `rate / timesCompounded` primeiro e depois soma `1`, respeitando as regras da matemática!)*
* **O 2º argumento (o Expoente):** É o que está lá no alto $\rightarrow$ `timesCompounded * years`.

O `Math.pow()` faz todo o cálculo pesado e multiplica pelo `principal`, guardando o resultado final na caixinha `amount`!

---

## 📢 4. Imprimindo o Resultado na Tela (Linha 30)

Depois de calcular o valor, o professor escreveu a linha de exibição:

```java
System.out.println("The amount after " + years + " is: $" + amount);
```

### O que essa linha faz?
Ela junta (concatena com `+`):
* O texto `"The amount after "`
* O número de anos guardado na variável `years`
* O texto `" is: $"`
* O valor calculado guardado na variável `amount`

---

## 🖥️ 5. O Teste Real no Console

O professor executou o programa e digitou os seguintes valores de teste:
* **Capital Inicial (`principal`):** `10000` (Dez mil dólares)
* **Taxa de Juros (`rate`):** `3` (O programa divide por 100 e vira `0.03`)
* **Vezes ao ano (`timesCompounded`):** `1`
* **Anos (`years`):** `1`

### A conta feita pelo Java nos bastidores:
$$\text{amount} = 10000 \times \left(1 + \frac{0.03}{1}\right)^{1 \times 1} = 10000 \times 1.03 = 10300.0$$

### O que apareceu na tela do console:
```text
Enter the # of times compounded per year: 1
Enter the # of years: 1
The amount after 1 is: $10300.0
```

---

## 🧐 6. O "Ouro" Pedagógico: Os 2 Problemas que o Professor Vai Corrigir!

Olhe com atenção para a frase que saiu no console:
> `The amount after 1 is: $10300.0`

Você percebeu que ela ficou meio esquisita?
1. **Falta uma palavra:** Ficou *"The amount after 1 is"* (O valor após 1 é...). Faltou a palavra **"years"** (anos)!
2. **Dinheiro mal formatado:** Apareceu `$10300.0` com apenas **uma** casa decimal e sem a vírgula separadora de milhar! Dinheiro profissional deve ser escrito como:
   $$\$10,300.00$$

> [!NOTE]
> E adivinha qual ferramenta aprendemos no Capítulo 10 exatamente para deixar esse dinheiro lindo com duas casas decimais e vírgula de milhar?  
> O **`System.out.printf()`** com a máscara **`$%,.2f`**! É exatamente isso que o professor vai fazer a seguir!

---

## 💻 7. Código Completo Desta Etapa (Pronto para Copiar e Rodar)

Copie e cole este código completo no seu arquivo `Main.java`:

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

        // 2. Cálculo matemático usando Math.pow
        amount = principal * Math.pow(1 + rate / timesCompounded, timesCompounded * years);

        // 3. Exibição do resultado
        System.out.println("The amount after " + years + " is: $" + amount);

        // 4. Fechamento do scanner
        scanner.close();
    }
}
```
