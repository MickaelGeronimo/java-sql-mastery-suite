# 🏦 Capítulo 11: Calculadora de Juros Compostos — Parte 1 (Preparação e Entradas)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. Boas-vindas ao Capítulo 11!

Parabéns por chegar até aqui! No Capítulo 11, o professor começa um **mini-projeto prático**.  
Em vez de aprender comandos isolados, agora vamos juntar tudo o que você já aprendeu até agora:
* **Entrada de dados com o teclado** (`Scanner`)
* **Tipos de variáveis** (`double` para dinheiro com centavos e `int` para contagens)
* **Contas matemáticas** (que virão no próximo passo com `Math.pow`)
* **Formatação de texto bonito** (`printf`)

O nome do projeto em inglês é:  
**`// Compound interest calculator`** $\rightarrow$ **Calculadora de Juros Compostos**.

---

## 💰 2. O que são "Juros Compostos" na Vida Real?

Antes do código, imagine o seguinte:
* Você coloca **R$ 1.000,00** numa conta que rende juros.
* No primeiro mês, você ganha juros sobre esses R$ 1.000,00.
* No segundo mês, você ganha juros sobre os R$ 1.000,00 **MAIS os juros do mês passado**!
* É o famoso efeito **"bola de neve"**: o dinheiro vai crescendo cada vez mais rápido porque rende juros sobre juros.

Para fazer esse cálculo financeiro, precisamos de **4 informações que o usuário vai digitar**:
1. Quanto dinheiro ele tem para investir no começo?
2. Qual a taxa de juros anual (a porcentagem)?
3. Quantas vezes por ano os juros rendem? (Todo mês? Uma vez por ano?)
4. Por quantos anos o dinheiro vai ficar guardado rendendo?

---

## 📦 3. As 5 Caixinhas de Memória (Declaração das Variáveis)

O autor começou criando 5 variáveis no Java. Vamos olhar uma por uma com calma:

```java
double principal;
double rate;
int timesCompounded;
int years;
double amount;
```

### 🔍 Por que usar `double` em algumas e `int` em outras?

1. **`double principal;`**
   * **O que é:** Em finanças, *Principal* é o **capital inicial** (o valor depositado no começo).
   * **Por que `double`?** Porque dinheiro tem centavos (ex: `1000.50`).

2. **`double rate;`**
   * **O que é:** *Interest rate* é a **taxa de juros**.
   * **Por que `double`?** Porque taxas de juros quase nunca são números inteiros puros; podem ser `5.5%`, `7.25%`, etc.

3. **`int timesCompounded;`**
   * **O que é:** Quantas vezes o juro é capitalizado (calculado) por ano.
     * Se render 1 vez ao ano = `1`
     * Se render todo mês = `12` (já que o ano tem 12 meses)
     * Se render todo dia = `365`
   * **Por que `int`?** Porque é uma **contagem inteira** de vezes. Não existe render "2 vezes e meia" no ano.

4. **`int years;`**
   * **O que é:** O tempo em **anos** que o investimento vai durar (ex: 5 anos, 10 anos, 30 anos).
   * **Por que `int`?** Porque aqui estamos contando anos inteiros.

5. **`double amount;`**
   * **O que é:** *Amount* é o **Montante Final** (o total acumulado: seu dinheiro inicial + todo o lucro dos juros).
   * **Por que `double`?** Porque o saldo final também tem reais e centavos!

---

## ⌨️ 4. O Scanner: Abrindo a Portinha do Teclado

Para o programa conseguir ouvir o que o usuário digita, o autor colocou:

```java
Scanner scanner = new Scanner(System.in);
```

> [!NOTE]
> Lembre-se: no topo de tudo (linha 1 do arquivo), fora da classe, é obrigatório ter:  
> `import java.util.Scanner;`  
> Isso traz a ferramenta do Java para dentro do nosso código.

E lá embaixo, ele já deixou preparado o fechamento:
```java
scanner.close();
```
Isso é uma boa prática para "desligar" o leitor de teclado quando terminamos de usar.

---

## 🗣️ 5. As 4 Perguntas e Leituras (Linha por Linha)

Aqui está o coração do que o autor adicionou. Vamos analisar cada par de pergunta e resposta:

### 🔹 Pergunta 1: O Capital Inicial
```java
System.out.print("Enter the principal amount: ");
principal = scanner.nextDouble();
```
* **`System.out.print(...)`**: Usou `print` (sem o `ln`) para o cursor ficar piscando **ao lado** do texto, e não na linha de baixo. Fica muito mais elegante!
* **`scanner.nextDouble()`**: O usuário digita o valor (ex: `1000`) e o Java guarda dentro da caixinha `principal`.

---

### 🔹 Pergunta 2: A Taxa de Juros (O Pulo do Gato! 🐱)
```java
System.out.print("Enter the interest rate (in %): ");
rate = scanner.nextDouble() / 100;
```

> [!IMPORTANT]
> **ATENÇÃO MÁXIMA AQUI: Por que tem um `/ 100` no final?**  
> Quando uma pessoa comum lê *"Digite a taxa em %"*, ela digita: `5` (querendo dizer 5%).  
> Só que na matemática financeira, se você fizer contas usando o número `5`, a conta vai dar errada por um fator gigantesco!  
> Na matemática:
> $$5\% = \frac{5}{100} = 0.05$$
> **A grande sacada do professor:** Ele já pegou o número digitado pelo usuário (`scanner.nextDouble()`) e **dividiu imediatamente por 100**!  
> Se o usuário digitar `5`, a variável `rate` vai guardar silenciosamente `0.05`.  
> Isso é genial porque o restante da fórmula matemática vai funcionar perfeitamente sem dor de cabeça!

---

### 🔹 Pergunta 3: Quantas vezes rende por ano?
```java
System.out.print("Enter the # of times compounded per year: ");
timesCompounded = scanner.nextInt();
```
* **O que significa o símbolo `#`?** Em inglês, o símbolo `#` (*hash* / cerquilha) é frequentemente usado como abreviação da palavra **"Number"** (Número).  
  Portanto, *"Enter the # of times..."* significa: *"Digite o **número** de vezes..."*.
* **Por que `nextInt()`?** Porque a variável `timesCompounded` foi criada como `int`. Então usamos o leitor de inteiros do scanner.

---

### 🔹 Pergunta 4: Quantidade de anos
```java
System.out.print("Enter the # of years: ");
years = scanner.nextInt();
```
* Pergunta quantos anos o dinheiro vai ficar rendendo.
* **`years = scanner.nextInt()`**: Lê o número inteiro digitado (ex: `3` para 3 anos) e guarda na caixinha `years`.

---

## 📊 Resumo Visual das Leituras

| Pergunta feita ao usuário | O que ele digita (exemplo) | Como o Java lê | O que é guardado na memória |
| :--- | :---: | :---: | :---: |
| Capital Inicial | `1000` | `scanner.nextDouble()` | `principal = 1000.0` |
| Taxa de Juros (%) | `5` | `scanner.nextDouble() / 100` | `rate = 0.05` |
| Vezes ao ano | `4` | `scanner.nextInt()` | `timesCompounded = 4` |
| Quantidade de anos | `3` | `scanner.nextInt()` | `years = 3` |

---

## 💻 Código Completo Desta Etapa (Pronto para Rodar)

Copie e cole este código no seu `Main.java` para acompanhar exatamente o que o professor fez até o minuto 2:00 do vídeo:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Compound interest calculator (Calculadora de juros compostos)

        Scanner scanner = new Scanner(System.in);

        double principal;
        double rate;
        int timesCompounded;
        int years;
        double amount;

        // 1. Pergunta o valor inicial investido
        System.out.print("Enter the principal amount: ");
        principal = scanner.nextDouble();

        // 2. Pergunta a taxa de juros anual (em %) e já converte para decimal (/ 100)
        System.out.print("Enter the interest rate (in %): ");
        rate = scanner.nextDouble() / 100;

        // 3. Pergunta quantas vezes por ano os juros são calculados
        System.out.print("Enter the # of times compounded per year: ");
        timesCompounded = scanner.nextInt();

        // 4. Pergunta o tempo em anos
        System.out.print("Enter the # of years: ");
        years = scanner.nextInt();

        // Fechamos o scanner
        scanner.close();
    }
}
```

---

## 🔮 O que vem a seguir?
Repare que a variável **`double amount;`** ainda não foi usada!  
Nas próximas telas, o professor vai ensinar a fórmula matemática dos juros compostos usando **`Math.pow()`** para calcular o valor de `amount` e imprimir o resultado final formatado com **`printf`**! 🚀
