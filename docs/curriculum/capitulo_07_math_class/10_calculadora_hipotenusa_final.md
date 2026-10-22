# 📐 Calculadora de Hipotenusa Completa: O Encontro do `pow` com o `sqrt`!

O mini-projeto está pronto e funcionando perfeitamente! Este guia explica a fórmula, o teste do autor e também **o grande mistério do número decimal gigante** que apareceu no seu teste!

---

## 📋 1. Código Completo Final (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // HYPOTENUSE c = Math.sqrt(a² + b²)

        Scanner scanner = new Scanner(System.in);

        double a;
        double b;
        double c;

        System.out.print("Enter the length of side A: ");
        a = scanner.nextDouble();

        System.out.print("Enter the length of side B: ");
        b = scanner.nextDouble();

        c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        System.out.println("The hypotenuse (side c) is: " + c);

        scanner.close();
    }
}
```

---

## 🤯 2. O Mistério do Seu Teste: Por que deu `5.830951894845301`?

No seu teste, você digitou:
* Lado A: **`3`**
* Lado B: **`5`** *(no vídeo o autor digitou 4)*

E o console cuspiu esse número quilométrico:
```
The hypotenuse (side c) is: 5.830951894845301
```

### Por que isso aconteceu? Vamos ver a matemática por trás:

$$\text{Lado A: } 3^2 = 3 \times 3 = \mathbf{9}$$
$$\text{Lado B: } 5^2 = 5 \times 5 = \mathbf{25}$$
$$\text{Soma: } 9 + 25 = \mathbf{34}$$

Agora o Java precisa tirar a **raiz quadrada de 34 ($\sqrt{34}$)**!

---

### 🥧 A Diferença Entre Raiz Exata e Raiz Inexata:

1. **No teste do autor (Lados 3 e 4):**
   * $3^2 + 4^2 = 9 + 16 = \mathbf{25}$
   * $\sqrt{25}$ é uma conta **perfeita e exata**: $5 \times 5 = 25$!
   * Por isso o resultado dele foi limpo: **`5.0`**!

2. **No seu teste (Lados 3 e 5):**
   * $3^2 + 5^2 = 9 + 25 = \mathbf{34}$
   * Não existe nenhum número inteiro que multiplicado por ele mesmo dê 34!
     * $5 \times 5 = 25$ (muito pouco)
     * $6 \times 6 = 36$ (passou!)
   * Então o resultado é um **NÚMERO IRRACIONAL**: uma dízima infinita que nunca termina:
     $$5.83095189484530047087...$$

---

## 🔬 3. Por que o Java mostra tantas casas decimais?

A caixinha que usamos é do tipo **`double`**:
```java
double c;
```
O `double` é uma ferramenta de **alta precisão científica** usada pela NASA e por engenheiros para calcular rotas de foguetes. Ele foi feito para mostrar até **16 casas decimais** para que o cálculo nunca fique impreciso!

> 💡 **Como deixar bonitinho no futuro?**
> Mais para a frente no curso, você vai aprender a encurtar isso para apenas 2 casas decimais (ex: `5.83`) usando um comando chamado `printf("%.2f", c)`.

---

## 💥 4. O Erro da Imagem 1: Por que `a² + b²` Ficou Vermelho?

No vídeo, o autor tentou digitar a fórmula do jeito que escrevemos no caderno:

```java
c = Math.sqrt(a² + b²); // ❌ ERRO VERMELHO! NÃO COMPILA!
```

### Por que o Java não aceita isso?
O Java **não entende o símbolo `²` como conta matemática**. Para elevar um número ao quadrado no Java, você é obrigado a usar **`Math.pow(a, 2)`** ou multiplicar a variável por ela mesma **`(a * a)`**!

---

## 🧬 5. A Mega-Fórmula da Linha 20 (Efeito Dominó)

```java
c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
```

O computador resolve **de dentro para fora**:
1. `Math.pow(3, 2)` $\rightarrow$ $9.0$
2. `Math.pow(5, 2)` $\rightarrow$ $25.0$
3. Soma os dois $\rightarrow$ $34.0$
4. `Math.sqrt(34.0)` $\rightarrow$ $\mathbf{5.830951894845301}$!
5. Guarda o valor em `c` e imprime com o texto! ✅

---

## 💡 Dica Bônus de Programador Sênior ☕

Você também pode escrever a Linha 20 assim:
```java
c = Math.sqrt((a * a) + (b * b));
```
Dá exatamente o mesmo resultado e o computador processa ainda mais rápido! 🚀
