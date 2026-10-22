# 🔢 int vs double na Divisão — A Grande Diferença!

Aqui o Bro Code mudou **UMA coisa** no código e o resultado ficou **completamente diferente**!

---

## O que ele mudou?

### ❌ ANTES (com `int`):
```java
int x = 10;
int y = 3;

x = x / y;

System.out.println(x);    // Resultado: 3
```

### ✅ AGORA (com `double`):
```java
double x = 10;
double y = 3;

x = x / y;

System.out.println(x);    // Resultado: 3.3333333333333335
```

**A única mudança:** trocou `int` por `double` nas linhas 7 e 8!

---

## Por que o resultado mudou tanto?

### Com `int` (número inteiro):
```
10 / 3 = 3.333...  MAS int não aceita casas decimais!
                    Então ele CORTA tudo depois do ponto.
                    Resultado: 3
```

O `int` **joga fora** as casas decimais. Ele não arredonda, ele simplesmente **corta** (trunca).

### Com `double` (número decimal):
```
10 / 3 = 3.3333333333333335  ← mantém as casas decimais!
```

O `double` **guarda** as casas decimais. O resultado é muito mais preciso!

---

## Analogia 🧠

Imagine que você divide uma pizza de 10 fatias pra 3 pessoas:

### Usando `int` (só aceita fatias inteiras):
```
🍕🍕🍕🍕🍕🍕🍕🍕🍕🍕 ÷ 3 pessoas

Cada um ganha: 3 fatias inteiras
Sobra: 1 fatia (jogada fora pelo int!)

Resultado: 3
```

### Usando `double` (aceita pedaços):
```
🍕🍕🍕🍕🍕🍕🍕🍕🍕🍕 ÷ 3 pessoas

Cada um ganha: 3.333... fatias (pode cortar em pedaços!)

Resultado: 3.3333333333333335
```

---

## Por que aparece `3.333...335` e não `3.333...333`?

O `5` no final é um **erro de precisão** do computador. O computador armazena números em binário (0 e 1), e alguns números decimais não podem ser representados perfeitamente em binário.

É como tentar escrever 1/3 = 0.333333... — os 3 nunca acabam! O computador tem um limite de casas decimais que ele consegue guardar, e o último dígito pode ficar ligeiramente errado.

> [!NOTE]
> Isso é **normal** e acontece em todas as linguagens de programação, não só Java. Por enquanto, não se preocupe com isso!

---

## Resumo: Quando usar `int` vs `double`?

| Tipo | Usa quando... | Exemplo | Divisão 10/3 |
|------|--------------|---------|-------------|
| `int` | Só precisa de números inteiros | Idade, quantidade, contagem | `3` (corta decimais) |
| `double` | Precisa de casas decimais | Nota, preço, medidas | `3.333...` (mantém decimais) |

> [!IMPORTANT]
> **Regra:** Se você vai fazer **divisão** e quer o resultado **preciso com casas decimais**, use `double`! Se usar `int`, as casas decimais serão **cortadas silenciosamente** sem aviso de erro.

---

## Código pra copiar:

```java
public class Main {
    public static void main(String[] args) {

        // Augmented Assignment Operators

        double x = 10;
        double y = 3;

        //x += y;
        //x -= y;
        //x *= y;
        x = x / y;

        System.out.println(x);
    }
}
```

Resultado: `3.3333333333333335`
