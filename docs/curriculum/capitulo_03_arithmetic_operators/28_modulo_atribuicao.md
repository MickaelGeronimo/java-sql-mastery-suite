# 🍰 Operador de Resto com Auto-atribuição (`x = x % y` ou `x %= y`)

Nesse pedaço do vídeo, o Bro Code está testando a última operação matemática: o **Resto da Divisão (`%`)** atualizando a própria variável!

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
        x = x % y;

        System.out.println(x);
    }
}
```

---

## 🔍 2. O que aconteceu linha por linha?

| Linha | Código | O que acontece? |
| :--- | :--- | :--- |
| **7** | `int x = 10;` | Cria a caixinha `x` guardando o número `10`. |
| **8** | `int y = 2;` | Cria a caixinha `y` guardando o número `2`. |
| **10-13** | `//x += y;` ... | Linhas com `//` no início estão **comentadas** (desativadas). O Java finge que não existem. |
| **14** | `x = x % y;` | **A linha ativa:** Calcula o **resto** da divisão de `10` por `2` e guarda de volta em `x`. |
| **16** | `System.out.println(x);` | Mostra o valor de `x` na tela. |

---

## 🍕 3. Por que o resultado na tela deu `0`?

Lembra da regra do símbolo **`%`**?
👉 **O `%` NÃO dá o resultado da divisão. Ele dá o que SOBRA (o resto)!**

Vamos fazer a conta com fatias de pizza:

```
Você tem 10 fatias de pizza: 🍕🍕🍕🍕🍕🍕🍕🍕🍕🍕
Vai dividir para 2 amigos:

Amigo 1 ganha: 🍕🍕🍕🍕🍕 (5 fatias)
Amigo 2 ganha: 🍕🍕🍕🍕🍕 (5 fatias)

Quantas fatias SOBRARAM na mesa?
👉 NENHUMA! Sobrou ZERO (0).
```

Por isso:
`10 % 2 = 0`

O computador pega esse `0` (o que sobrou) e guarda dentro da caixinha do `x`. Quando você manda imprimir `x`, aparece **`0`** no console!

---

## ⚡ 4. O Atalho (`%=`)

Ele escreveu na forma longa:
```java
x = x % y;
```

Mas também poderia ter escrito com o **atalho**:
```java
x %= y; // Faz exatamente a mesma coisa!
```

---

## 💡 Resumo dos 5 Atalhos que fechamos nesta aula:

| Atalho | Forma Longa | Exemplo (`x = 10`, `y = 2`) | Resultado final de `x` |
| :---: | :---: | :---: | :---: |
| `x += y;` | `x = x + y;` | `10 + 2` | **12** |
| `x -= y;` | `x = x - y;` | `10 - 2` | **8** |
| `x *= y;` | `x = x * y;` | `10 * 2` | **20** |
| `x /= y;` | `x = x / y;` | `10 / 2` (divisão) | **5** |
| `x %= y;` | `x = x % y;` | `10 % 2` (resto) | **0** |
