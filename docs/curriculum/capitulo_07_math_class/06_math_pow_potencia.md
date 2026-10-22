# ⚡ O Comando `Math.pow()`: Calculando Potências no Java!

Nesta imagem, o Bro Code ensina como fazer cálculos de **potenciação** (um número elevado a outro) usando a ferramenta **`Math.pow()`**!

---

## 📋 1. Código Completo da Aula (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        //System.out.println(Math.PI);
        //System.out.println(Math.E);

        double result;

        result = Math.pow(2, 3);

        System.out.println(result);
    }
}
```

---

## 💡 2. O que significa `pow`? (Dicionário de Programador)

A palavra **`pow`** é uma abreviação do inglês **"Power"** (que em matemática significa **Potência** ou **"Elevado a"**).

Quando você escreve:
```java
Math.pow(2, 3);
```
O Java traduz isso para a matemática da escola como:
$$\mathbf{2^3 \quad (2 \text{ elevado a } 3)}$$

### Como a matemática calcula isso?
É o número **2** multiplicado por ele mesmo **3 vezes**:
$$2 \times 2 \times 2 = \mathbf{8.0}$$

---

## 🔍 3. A Anatomia do `Math.pow(base, expoente)`:

```
           Math.pow (  2  ,  3  ) ;
                       │     │
            1. BASE ───┘     └─── 2. EXPOENTE
       (Quem vai multiplicar)    (Quantas vezes repete)
```

* O primeiro número (`2`) é a **base**.
* O segundo número (`3`) é o **expoente** (quantas vezes a base se multiplica).

---

## ⚠️ 4. Por que a variável tem que ser `double result` e não `int`?

Repare bem na linha 8:
```java
double result; // 👈 É DOUBLE, NÃO É INT!
```

Muitos iniciantes tentam escrever:
```java
int result = Math.pow(2, 3); // ❌ DÁ ERRO VERMELHO NO JAVA!
```

### Por que dá erro com `int`?
Porque a ferramenta `Math.pow()` foi construída pelos criadores do Java para devolver **sempre um número decimal (`double`)**, mesmo quando a conta dá exata!
* O resultado que o Java cospe na tela não é `8`, é **`8.0`**!
* Como tem `.0`, o Java exige que você guarde o resultado dentro de uma caixinha do tipo **`double`**!

---

## 🧪 5. Outros Exemplos para Testar na sua Cabeça:

| Código Java | Conta Matemática | O que dá no Console? |
| :--- | :--- | :--- |
| `Math.pow(5, 2);` | $5^2 = 5 \times 5$ | `25.0` |
| `Math.pow(3, 3);` | $3^3 = 3 \times 3 \times 3$ | `27.0` |
| `Math.pow(10, 2);` | $10^2 = 10 \times 10$ | `100.0` |
| `Math.pow(4, 0.5);` | Raiz de 4 | `2.0` |

---

## 🔍 6. Tabela Linha por Linha

| Linha | Código | O que significa? |
| :---: | :--- | :--- |
| **5-6** | `//System.out.println...` | Colocou `//` na frente para **desligar** o PI e o E e limpar o console. |
| **8** | `double result;` | Cria a caixinha decimal chamada `result`. |
| **10** | `result = Math.pow(2, 3);` | Calcula $2^3$ ($2 \times 2 \times 2$) e guarda `8.0` na caixinha. |
| **12** | `System.out.println(result);` | Mostra `8.0` no console. |
