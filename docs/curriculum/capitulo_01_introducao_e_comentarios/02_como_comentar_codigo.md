# 🔇 Comentando Código para Testar Operadores — Explicação

Nessa parte do vídeo, o Bro Code mostra um **truque muito útil**: usar `//` pra **desativar linhas de código** sem apagar elas!

---

## O código mostrado:

```java
int x = 10;
int y = 2;
int z;

//z = x + y;      ← desativado (comentado)
//z = x - y;      ← desativado (comentado)
//z = x * y;      ← desativado (comentado)
z = x / y;        ← ATIVO! (divisão)

System.out.println(z);
```

Resultado no console: **`5`** (porque 10 ÷ 2 = 5)

---

## O que ele está fazendo?

Ele testou **cada operador** um de cada vez:

1. Primeiro testou `z = x + y;` → deu `12` → funcionou ✅ → **comentou**
2. Depois testou `z = x - y;` → deu `8` → funcionou ✅ → **comentou**
3. Depois testou `z = x * y;` → deu `20` → funcionou ✅ → **comentou**
4. Agora está testando `z = x / y;` → deu `5` ✅

---

## Conceito Novo: "Comentar código" pra desativar 🆕

### O que é?
Colocar `//` na frente de uma linha faz o Java **ignorar ela completamente**. A linha continua lá no código, mas **não executa**.

```java
z = x + y;       // ← ATIVA — o Java executa essa linha
//z = x + y;     // ← DESATIVADA — o Java IGNORA essa linha
```

### Por que fazer isso?
Em vez de **apagar** o código antigo e ter que reescrever depois, você **comenta** ele. Assim ele fica guardado ali pra quando precisar de novo.

### Analogia 🧠
É como **riscar com lápis** uma receita de bolo que você já testou, sem arrancar a página do caderno. Se quiser testar de novo, é só apagar o risco!

```
Caderno de receitas:
  ̶R̶e̶c̶e̶i̶t̶a̶ ̶d̶e̶ ̶b̶o̶l̶o̶ ̶d̶e̶ ̶c̶h̶o̶c̶o̶l̶a̶t̶e̶   ← riscado (comentado)
  ̶R̶e̶c̶e̶i̶t̶a̶ ̶d̶e̶ ̶p̶u̶d̶i̶m̶                ← riscado (comentado)
  ̶R̶e̶c̶e̶i̶t̶a̶ ̶d̶e̶ ̶p̶i̶z̶z̶a̶               ← riscado (comentado)
  Receita de lasanha              ← ESTA é a que vou testar agora!
```

---

## Resumo dos testes que ele fez:

| Operador | Código | Conta | Resultado | Status |
|----------|--------|-------|-----------|--------|
| `+` Soma | `//z = x + y;` | 10 + 2 | 12 | Comentado ✅ |
| `-` Subtração | `//z = x - y;` | 10 - 2 | 8 | Comentado ✅ |
| `*` Multiplicação | `//z = x * y;` | 10 × 2 | 20 | Comentado ✅ |
| `/` Divisão | `z = x / y;` | 10 ÷ 2 | **5** | **Ativo agora** 👈 |

---

## 💡 O que você aprendeu aqui

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **Comentar pra desativar** | `//z = x + y;` — a linha existe mas não executa |
| 2 | **Testar um de cada vez** | Comenta os anteriores, deixa só um ativo |
| 3 | **Operador `/` (divisão)** | `10 / 2 = 5` |
| 4 | **Truque útil pra debug** | Quando algo dá errado, comenta linhas pra achar o problema |

> [!TIP]
> **Atalho na IDE (IntelliJ):** Selecione a linha e aperte `Ctrl + /` pra comentar/descomentar rapidamente! Não precisa digitar `//` na mão.
