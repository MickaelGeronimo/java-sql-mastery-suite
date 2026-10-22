# ➗ O Operador Módulo `%` (Resto da Divisão) — Explicação

Nessa parte do vídeo, o Bro Code explica o operador **mais estranho** pra quem tá começando: o `%` (módulo). Ele retorna o **RESTO** da divisão!

---

## O código:

```java
int x = 10;
int y = 3;
int z;

//z = x + y;       ← comentado (soma)
//z = x - y;       ← comentado (subtração)
//z = x * y;       ← comentado (multiplicação)
//z = x / y;       ← comentado (divisão)
z = x % y;         ← ATIVO! (módulo/resto)

System.out.println(z);
```

Resultado no console: **`1`**

---

## O que é o `%` (módulo)?

O `%` **NÃO** faz divisão normal. Ele retorna o **RESTO** que sobra depois da divisão.

### Exemplo: `10 % 3 = ?`

Vamos fazer a divisão na mão:

```
10 ÷ 3 = ?

3 cabe em 10 quantas vezes?
  3 × 1 = 3    ← cabe
  3 × 2 = 6    ← cabe
  3 × 3 = 9    ← cabe ✅
  3 × 4 = 12   ← NÃO cabe! Passou de 10!

Então: 3 cabe 3 vezes em 10.
  3 × 3 = 9

Quanto sobrou?
  10 - 9 = 1   ← ESSE é o resto!

Resposta: 10 % 3 = 1
```

### Analogia 🧠 — Dividindo pizza!

Imagine que você tem **10 fatias de pizza** e quer dividir entre **3 amigos** igualmente:

```
🍕🍕🍕🍕🍕🍕🍕🍕🍕🍕  (10 fatias)

Amigo 1: 🍕🍕🍕  (3 fatias)
Amigo 2: 🍕🍕🍕  (3 fatias)
Amigo 3: 🍕🍕🍕  (3 fatias)

Sobrou:  🍕  (1 fatia!)  ← ESSE é o % (resto)!
```

- **Divisão normal (`/`):** Cada amigo ganha **3** fatias → `10 / 3 = 3`
- **Módulo (`%`):** Sobrou **1** fatia → `10 % 3 = 1`

---

## Por que ele mudou `y` de 2 pra 3?

No vídeo, o Bro Code primeiro mostrou com `y = 2`:

```
10 % 2 = 0    ← resto ZERO! (10 divide certinho por 2, não sobra nada)
```

Mas com resto 0 fica difícil de entender o conceito. Então ele **mudou pra `y = 3`**:

```
10 % 3 = 1    ← agora sim, sobrou 1! Dá pra ver o resto funcionando
```

---

## Mais exemplos pra fixar:

| Conta | Divisão | Vezes que cabe | Total | Resto | Resultado do `%` |
|-------|---------|---------------|-------|-------|-------------------|
| `10 % 3` | 10 ÷ 3 | 3 vezes | 3×3 = 9 | 10-9 = **1** | `1` |
| `10 % 2` | 10 ÷ 2 | 5 vezes | 5×2 = 10 | 10-10 = **0** | `0` |
| `10 % 4` | 10 ÷ 4 | 2 vezes | 2×4 = 8 | 10-8 = **2** | `2` |
| `10 % 7` | 10 ÷ 7 | 1 vez | 1×7 = 7 | 10-7 = **3** | `3` |
| `10 % 10` | 10 ÷ 10 | 1 vez | 1×10 = 10 | 10-10 = **0** | `0` |
| `7 % 2` | 7 ÷ 2 | 3 vezes | 3×2 = 6 | 7-6 = **1** | `1` |

---

## Pra que serve o `%` na vida real? 🤔

Pode parecer inútil, mas o `%` é **super usado** em programação:

### 1. Saber se um número é PAR ou ÍMPAR
```java
if (numero % 2 == 0) {
    // É PAR! (divide por 2 sem resto)
} else {
    // É ÍMPAR! (sobra 1 ao dividir por 2)
}
```

### 2. Saber se um número é divisível por outro
```java
if (numero % 5 == 0) {
    // É divisível por 5! (resto zero)
}
```

### 3. Fazer algo a cada X vezes
```java
if (contador % 10 == 0) {
    // Faz algo a cada 10 iterações
}
```

> [!NOTE]
> Você vai usar o `%` **muito** ao longo do curso. Por enquanto, só grave: **`%` = resto da divisão**.

---

## 📋 Tabela completa dos 5 operadores aritméticos (x=10, y=3):

| Operador | Nome | Conta | Resultado |
|----------|------|-------|-----------|
| `+` | Soma | `10 + 3` | `13` |
| `-` | Subtração | `10 - 3` | `7` |
| `*` | Multiplicação | `10 × 3` | `30` |
| `/` | Divisão | `10 ÷ 3` | `3` (inteiro!) |
| `%` | Módulo (resto) | `10 % 3` | `1` |

---

## 💡 Resumo

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **`%` = resto da divisão** | `10 % 3 = 1` (sobra 1) |
| 2 | **Resto 0 = divide certinho** | `10 % 2 = 0` (não sobra nada) |
| 3 | **Uso prático: par/ímpar** | `numero % 2 == 0` → é par! |
