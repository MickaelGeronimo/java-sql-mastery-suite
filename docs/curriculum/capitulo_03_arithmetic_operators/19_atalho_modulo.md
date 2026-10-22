# ⚡ O Atalho do Resto da Divisão: `x %= y;`

Nesta imagem, o Bro Code fez a **transformação final** da aula: ele trocou a forma longa (`x = x % y`) pelo **atalho oficial (`x %= y`)**!

---

## 📋 1. Código Completo (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // Augmented Assignment Operators

        int x = 10;
        int y = 2;

        //x += y;
        //x -= y;
        //x *= y;
        //x /= y;
        x %= y;

        System.out.println(x);
    }
}
```

---

## 🔍 2. O que mudou em relação à imagem anterior?

Olhe a comparação da **Linha 14**:

| Imagem Anterior (Forma Longa) | Imagem Atual (Forma Curta / Atalho) |
| :--- | :--- |
| `x = x % y;` | `x %= y;` |

Ambas fazem **exatamente a mesma conta**:
1. Pegam o valor de `x` (10).
2. Calculam o resto da divisão por `y` (2).
3. Guardam o resultado (**`0`**) de volta na variável `x`.

---

## 🎯 3. Por que os programadores preferem `x %= y`?

* **Menos digitação**: Você não precisa escrever o nome da variável `x` duas vezes.
* **Mais limpo**: Junta o operador matemático (`%`) direto com o de atribuição (`=`).
* **Padrão mundial**: Todo programador de Java, C#, C++, Python e JavaScript usa esse formato no dia a dia.

---

## 🏆 4. O Grande Quadro de Honra dos Atalhos:

Com essa imagem, você aprendeu **todos** os 5 operadores de atribuição aumentada do Java:

```
+ com =  👉  +=  (soma nele mesmo)
- com =  👉  -=  (subtrai dele mesmo)
* com =  👉  *=  (multiplica por ele mesmo)
/ com =  👉  /=  (divide ele mesmo)
% com =  👉  %=  (calcula o resto e guarda nele mesmo)
```

| Atalho | Significado Real | Exemplo (`x = 10`, `y = 2`) | Resultado em `x` |
| :---: | :---: | :---: | :---: |
| `x += y;` | `x = x + y;` | $10 + 2$ | **12** |
| `x -= y;` | `x = x - y;` | $10 - 2$ | **8** |
| `x *= y;` | `x = x * y;` | $10 \times 2$ | **20** |
| `x /= y;` | `x = x / y;` | $10 \div 2$ | **5** |
| `x %= y;` | `x = x % y;` | $10 \pmod 2$ | **0** |
