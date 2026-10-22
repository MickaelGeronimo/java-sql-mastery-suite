# ➕ Operadores Aritméticos (Arithmetic Operators) — Explicação Linha por Linha

Nesse vídeo, o Bro Code começa a ensinar os **Operadores Aritméticos** — ou seja, como fazer **contas de matemática** no Java!

---

## O código mostrado:

```java
public class Main {
    public static void main(String[] args) {

        // Arithmetic Operators

        int x = 10;
        int y = 2;
        int z;

        z = x + y;

        System.out.println(z);
    }
}
```

---

## Agora vamos linha por linha! 👇

---

## Linha 5 — Comentário

```java
// Arithmetic Operators
```

Anotação dizendo que esse código é sobre **operadores aritméticos** (operações de matemática). O Java ignora.

---

## Linhas 7–9 — Criando 3 variáveis

```java
int x = 10;
int y = 2;
int z;
```

| Variável | Valor | Significado |
|----------|-------|-------------|
| `x` | `10` | Primeiro número (já com valor) |
| `y` | `2` | Segundo número (já com valor) |
| `z` | _(vazio)_ | Vai guardar o **resultado** da conta (sem valor por enquanto) |

### Por que `int`?
Porque são **números inteiros** (sem vírgula). `10` e `2` são inteiros.

### Por que `x`, `y`, `z`?
São nomes curtos que o autor escolheu. Em matemática, a gente usa letras como `x`, `y`, `z` pra representar valores. No Java, funciona igual!

### Analogia 🧠
Imagine 3 gavetas:

```
🗄️ Gaveta "x"  → contém: 10
🗄️ Gaveta "y"  → contém: 2
🗄️ Gaveta "z"  → (vazia, vai ser preenchida)
```

---

## Linha 11 — A CONTA! 🧮

```java
z = x + y;
```

### O que faz?
Pega o valor de `x` (que é `10`), soma com o valor de `y` (que é `2`), e guarda o resultado em `z`.

### Passo a passo:

```
z = x + y
z = 10 + 2
z = 12
```

Agora a gaveta `z` contém `12`!

### Como ler essa linha em português:
> "z **recebe** o resultado de x **mais** y"

### O `=` NÃO é "igual"!

> [!IMPORTANT]
> Em Java, o `=` significa **"recebe"** (atribuição), não "igual" como na matemática!
>
> - Na **matemática**: `z = x + y` significa "z é igual a x + y"
> - No **Java**: `z = x + y` significa "**pegue** x + y e **coloque** o resultado dentro de z"
>
> A diferença é sutil, mas importante. O lado **direito** é calculado primeiro, e depois o resultado vai pro lado **esquerdo**.

### Analogia 🧠

```
  Lado DIREITO (é calculado primeiro)
       ↓
  z = x + y
  ↑
  Lado ESQUERDO (recebe o resultado)

Passo 1: Calcula  x + y  →  10 + 2 = 12
Passo 2: Guarda   12  dentro de  z
```

---

## Linha 13 — Mostrando o resultado

```java
System.out.println(z);
```

### Percebeu algo diferente?

Antes a gente fazia:
```java
System.out.println("Hello " + name);   // texto + variável
```

Agora ele fez:
```java
System.out.println(z);   // SÓ a variável, sem texto nenhum!
```

### Isso funciona?
**Sim!** Você pode colocar **só a variável** dentro do `println()`. Ele vai imprimir o valor dela direto.

| Forma | O que aparece na tela |
|-------|----------------------|
| `System.out.println("O resultado é: " + z);` | `O resultado é: 12` |
| `System.out.println(z);` | `12` |

### Resultado no console:
```
12
```

Simples e direto!

---

## 🧮 Tabela Completa dos Operadores Aritméticos em Java

O Bro Code vai mostrar todos esses no vídeo, mas aqui está a tabela completa pra você já ir conhecendo:

| Operador | Nome | Exemplo | Resultado | Explicação |
|----------|------|---------|-----------|------------|
| `+` | Soma | `10 + 2` | `12` | Soma dois valores |
| `-` | Subtração | `10 - 2` | `8` | Subtrai um do outro |
| `*` | Multiplicação | `10 * 2` | `20` | Multiplica dois valores |
| `/` | Divisão | `10 / 2` | `5` | Divide um pelo outro |
| `%` | Módulo (resto) | `10 % 3` | `1` | Resto da divisão (10 ÷ 3 = 3 com **resto 1**) |

### O operador `%` (módulo) é o mais estranho pra quem tá começando:

```
10 % 3 = ?

10 ÷ 3 = 3 (cabe 3 vezes)
3 × 3 = 9
10 - 9 = 1  ← esse é o RESTO!

Então: 10 % 3 = 1
```

### Como testar cada operador:

Basta trocar o `+` na linha 11:

```java
z = x + y;    // Soma:          10 + 2  = 12
z = x - y;    // Subtração:     10 - 2  = 8
z = x * y;    // Multiplicação: 10 * 2  = 20
z = x / y;    // Divisão:       10 / 2  = 5
z = x % y;    // Módulo (resto):10 % 2  = 0
```

---

## 🧩 Resumo Visual

```
PROGRAMA INICIA
    │
    ▼
Cria: x = 10, y = 2, z = (vazio)
    │
    ▼
Calcula: z = x + y = 10 + 2 = 12
    │
    ▼
Mostra na tela: 12
    │
    ▼
PROGRAMA TERMINA ✅
```

---

## 💡 Conceitos que você aprendeu aqui

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **Operador `+` (soma)** | `z = x + y;` → `z = 10 + 2` → `z = 12` |
| 2 | **`=` significa "recebe"** | O lado direito é calculado e colocado no lado esquerdo |
| 3 | **println só com variável** | `System.out.println(z);` → imprime `12` direto |
| 4 | **Outros operadores** | `-`, `*`, `/`, `%` (tabela acima) |

> [!TIP]
> Experimente trocar o `+` por `-`, `*`, `/` e `%` e rode o programa pra ver o resultado de cada um! É a melhor forma de aprender.
