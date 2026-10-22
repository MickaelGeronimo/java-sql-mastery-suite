# 🔄 Testando os Atalhos na Prática — Augmented Assignment Operators (Parte 2)

Agora o Bro Code está **testando cada atalho** um por um, do mesmo jeito que fez com os operadores aritméticos: testa um, comenta, testa o próximo!

Ele também mostra que a **forma longa** e o **atalho** dão o **mesmo resultado**.

---

## O que ele fez em cada imagem:

---

### 🔹 Teste 1: `+=` (soma e atribui)

```java
int x = 10;
int y = 3;

x += y;              // atalho: x recebe x + y

System.out.println(x);   // Resultado: 13
```

**Conta:** `x = 10 + 3 = 13` ✅

Testou, funcionou → **comentou** (`//x += y;`)

---

### 🔹 Teste 2: `-=` (subtrai e atribui)

```java
int x = 10;
int y = 3;

//x += y;            // ← já testado, comentado
x -= y;              // atalho: x recebe x - y

System.out.println(x);   // Resultado: 7
```

**Conta:** `x = 10 - 3 = 7` ✅

Testou, funcionou → **comentou** (`//x -= y;`)

---

### 🔹 Teste 3: `*=` (multiplica e atribui)

Aqui ele mostrou **DUAS formas** que fazem a mesma coisa:

**Forma longa:**
```java
//x += y;
//x -= y;
x = x * y;           // forma longa: x recebe x vezes y

System.out.println(x);   // Resultado: 30
```

**Forma curta (atalho):**
```java
//x += y;
//x -= y;
x *= y;              // atalho: MESMA coisa, só mais curto!

System.out.println(x);   // Resultado: 30
```

**Conta:** `x = 10 × 3 = 30` ✅

Ambas dão **30**! São **100% iguais**. O atalho é só pra digitar menos.

Testou, funcionou → **comentou** (`//x *= y;`)

---

### 🔹 Teste 4: `/=` (divide e atribui) — em andamento

Ele mostra a forma longa:

```java
//x += y;
//x -= y;
//x *= y;
x = x / y;           // forma longa: x recebe x dividido por y

System.out.println(x);   // Resultado: 3
```

**Conta:** `x = 10 / 3 = 3` (divisão inteira, sem casas decimais!)

O atalho equivalente seria:
```java
x /= y;              // mesma coisa!
```

---

## Resumo de todos os testes:

| Atalho | Forma longa | Conta (x=10, y=3) | Resultado |
|--------|-------------|-------------------|-----------|
| `x += y;` | `x = x + y;` | 10 + 3 | **13** |
| `x -= y;` | `x = x - y;` | 10 - 3 | **7** |
| `x *= y;` | `x = x * y;` | 10 × 3 | **30** |
| `x /= y;` | `x = x / y;` | 10 ÷ 3 | **3** |
| `x %= y;` | `x = x % y;` | 10 % 3 | **1** |

---

## O padrão que ele está ensinando:

```
Forma LONGA:          Atalho:

x = x + y;    →      x += y;
x = x - y;    →      x -= y;
x = x * y;    →      x *= y;
x = x / y;    →      x /= y;
x = x % y;    →      x %= y;

        ↕ SÃO IGUAIS! ↕
```

> [!TIP]
> A lógica é simples: o atalho **junta o operador com o `=`**.
> - `+` e `=` → `+=`
> - `-` e `=` → `-=`
> - `*` e `=` → `*=`
> - `/` e `=` → `/=`
> - `%` e `=` → `%=`

---

## O código final (com todos comentados exceto o último):

```java
public class Main {
    public static void main(String[] args) {

        // Augmented Assignment Operators

        int x = 10;
        int y = 3;

        //x += y;
        //x -= y;
        //x *= y;
        x = x / y;

        System.out.println(x);
    }
}
```

---

## 💡 O que você aprendeu aqui

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **Atalho `+=`** | `x += 3;` = `x = x + 3;` |
| 2 | **Atalho `-=`** | `x -= 3;` = `x = x - 3;` |
| 3 | **Atalho `*=`** | `x *= 3;` = `x = x * 3;` |
| 4 | **Atalho `/=`** | `x /= 3;` = `x = x / 3;` |
| 5 | **Forma longa = atalho** | Resultados idênticos! O atalho é só pra digitar menos |
| 6 | **Testar um por vez** | Comenta os anteriores com `//` e testa o próximo |
