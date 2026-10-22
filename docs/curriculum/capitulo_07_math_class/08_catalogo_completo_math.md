# 🧰 O Canivete Suíço da Matemática: O Guia Definitivo dos 8 Comandos do `Math`!

Revisamos cada detalhe das imagens para garantir que **absolutamente nada fique sem explicação**. Este guia disseca o código completo da Imagem 2 de ponta a ponta!

---

## 📋 1. O Código Completo da Imagem 2 (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        System.out.println(Math.PI);
        System.out.println(Math.E);

        double result;

        result = Math.pow(2, 5);
        result = Math.abs(-5);
        result = Math.sqrt(9);
        result = Math.round(3.14);
        result = Math.ceil(3.14);
        result = Math.floor(3.99);
        result = Math.max(10, 20);
        result = Math.min(10, 20);

        System.out.println(result);
    }
}
```

---

## 🖥️ 2. O Que Realmente Aparece no Console se Você Rodar?

Repare que temos **três comandos de imprimir na tela (`println`)**:
1. Linha 5: imprime o PI.
2. Linha 6: imprime o E.
3. Linha 19: imprime a variável `result`.

O console vai mostrar exatamente isto aqui:
```
3.141592653589793
2.718281828459045
10.0
```

---

## 🧠 3. A Lousa Mágica: Por que só o `10.0` foi impresso na linha 19?

Pense na variável `double result;` como uma **pequena lousa**:

```
[LINHA 10]: Escreveu 32.0  na lousa  (Math.pow)
[LINHA 11]: APAGOU e escreveu 5.0    (Math.abs)
[LINHA 12]: APAGOU e escreveu 3.0    (Math.sqrt)
[LINHA 13]: APAGOU e escreveu 3.0    (Math.round)
[LINHA 14]: APAGOU e escreveu 4.0    (Math.ceil)
[LINHA 15]: APAGOU e escreveu 3.0    (Math.floor)
[LINHA 16]: APAGOU e escreveu 20.0   (Math.max)
[LINHA 17]: APAGOU e escreveu 10.0   (Math.min)
                               │
[LINHA 19]: System.out.println(result);
            👉 O Java lê o que sobrou na lousa: 10.0!
```
Como as linhas 10 a 16 **não tinham `println`**, o computador fez as contas na velocidade da luz, mas foi sobrescrevendo a mesma variável até sobrar apenas o `10.0` da linha 17!

---

## 🧐 4. Por que o resultado é `10.0` e não `10`? (E na Imagem 1 deu `5.0`)

Você deve ter notado que na Imagem 1 saiu **`5.0`** e não apenas `5`.

### O Motivo:
Olhe a linha 8:
```java
double result;
```
Como a caixinha é do tipo **`double`**, o Java **obriga** o número a ter formato decimal com ponto flutuante!
* Mesmo que o menor número entre 10 e 20 seja o inteiro `10`, ao entrar na gaveta `double`, ele ganha a roupa de decimal: **`10.0`**!

---

## 🔬 5. Dissecando os 8 Comandos do Canivete Suíço

---

### 1️⃣ `result = Math.pow(2, 5);` — Potência
* **Significado:** **Pow**er (Potência / Elevado a).
* **Cálculo:** $2^5 = 2 \times 2 \times 2 \times 2 \times 2 = \mathbf{32.0}$.
* **Uso:** Calcular áreas, volumes, física e crescimento.

---

### 2️⃣ `result = Math.abs(-5);` — Valor Absoluto
* **Significado:** **Abs**olute (Módulo matemático).
* **Cálculo:** Apaga o sinal de negativo: $|-5| = \mathbf{5.0}$.
* 🧠 **Analogia:** Se você der ré no carro por 5 metros ($-5$), você ainda percorreu uma distância física de **5 metros**. O `abs` mede essa distância real sem ligar para o sinal de menos.

---

### 3️⃣ `result = Math.sqrt(9);` — Raiz Quadrada
* **Significado:** **Sq**uare **R**oo**t** (Raiz quadrada).
* **Cálculo:** $\sqrt{9} = \mathbf{3.0}$ (pois $3 \times 3 = 9$).
* **Uso:** Teorema de Pitágoras, cálculos geométricos.

---

### 4️⃣ `result = Math.round(3.14);` — Arredondamento da Escola
* **Significado:** **Round** (Arredondar).
* **Regra:**
  * De `.0` a `.4` $\rightarrow$ Arredonda para **baixo**.
  * De `.5` a `.9` $\rightarrow$ Arredonda para **cima**.
* Como `3.14` termina com `.14`, arredonda para **`3.0`**.

---

### 5️⃣ `result = Math.ceil(3.14);` — O Teto (Sempre pra Cima!)
* **Significado:** **Ceil**ing (Teto da sala).
* **Regra:** **Bate a cabeça no teto!** Qualquer número com vírgula (mesmo `3.0001`) é empurrado imediatamente para o próximo número inteiro acima.
* `3.14` $\rightarrow$ vira **`4.0`**.
* 📦 **Analogia:** Se você tem 3 caixas e meia de pizza, você precisa de **4 caixas** para caber tudo.

---

### 6️⃣ `result = Math.floor(3.99);` — O Chão (Sempre pra Baixo!)
* **Significado:** **Floor** (Piso / Chão).
* **Regra:** **Puxa para o chão!** Joga fora todos os decimais, não importa se for quase 4 (`3.99`).
* `3.99` $\rightarrow$ vira **`3.0`**.
* 🎂 **Analogia:** Se você tem 19 anos e 364 dias, para a lei você ainda tem **19 anos**, e não 20.

---

### 7️⃣ `result = Math.max(10, 20);` — O Campeão (Maior Valor)
* **Significado:** **Max**imum (Máximo).
* Compara os dois números dentro dos parênteses e devolve o maior: **`20.0`**.

---

### 8️⃣ `result = Math.min(10, 20);` — O Menorzinho (Menor Valor)
* **Significado:** **Min**imum (Mínimo).
* Compara os dois números dentro dos parênteses e devolve o menor: **`10.0`**.

---

## 🔍 6. Tabela Linha por Linha Completa (Do Início ao Fim)

| Linha | Código | Explicação Detalhada |
| :---: | :--- | :--- |
| **1** | `public class Main {` | Início da classe Java. |
| **2** | `public static void main(String[] args) {` | Ponto de partida do programa. |
| **4** | `System.out.println(Math.PI);` | Mostra o valor do número PI: `3.141592653589793`. |
| **5** | `System.out.println(Math.E);` | Mostra o número de Euler: `2.718281828459045`. |
| **7** | `double result;` | Cria a variável decimal chamada `result`. |
| **9** | `result = Math.pow(2, 5);` | Calcula $2^5 = 32.0$ e guarda em `result`. |
| **10** | `result = Math.abs(-5);` | Tira o sinal de menos: guarda `5.0` em `result`. |
| **11** | `result = Math.sqrt(9);` | Raiz de 9: guarda `3.0` em `result`. |
| **12** | `result = Math.round(3.14);` | Arredondamento normal: guarda `3.0` em `result`. |
| **13** | `result = Math.ceil(3.14);` | Teto: empurra para cima e guarda `4.0` em `result`. |
| **14** | `result = Math.floor(3.99);` | Chão: puxa para baixo e guarda `3.0` em `result`. |
| **15** | `result = Math.max(10, 20);` | Pega o maior: guarda `20.0` em `result`. |
| **16** | `result = Math.min(10, 20);` | Pega o menor: guarda `10.0` em `result`. |
| **18** | `System.out.println(result);` | Mostra o último valor guardado no console: **`10.0`**. |
| **19-20** | `}` | Fecham o `main` e a classe `Main`. |
