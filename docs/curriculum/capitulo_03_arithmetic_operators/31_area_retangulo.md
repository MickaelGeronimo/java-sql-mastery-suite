# 📐 Calculando a Área de um Retângulo — Explicação Linha por Linha

Nesse vídeo, o Bro Code ensina como **fazer contas (expressões matemáticas)** em Java. Ele cria um programa que calcula a **área de um retângulo** pedindo a largura e a altura ao usuário.

---

## O código completo final:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Calculate area of a rectangle

        double width = 0;
        double height = 0;
        double area = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the width: ");
        width = scanner.nextDouble();

        System.out.print("Enter the height: ");
        height = scanner.nextDouble();

        area = width * height;

        System.out.println("The area is: " + area);

        scanner.close();
    }
}
```

---

## Agora vamos linha por linha! 👇

---

## Linha 1 — O import

```java
import java.util.Scanner;
```

Já vimos isso antes! Traz a ferramenta `Scanner` pra poder ler o que o usuário digita no teclado.

---

## Linhas 3–4 — A estrutura do programa

```java
public class Main {
    public static void main(String[] args) {
```

A "casca" do programa. Todo programa Java começa assim. Já vimos isso antes também!

---

## Linha 6 — O COMENTÁRIO

```java
// Calculate area of a rectangle
```

### O que é isso?
Tudo que começa com `//` é um **comentário**. O Java **ignora completamente** essa linha. Ela não faz nada no programa.

### Pra que serve então?
É uma **anotação pra você mesmo** (ou pra outras pessoas que lerem seu código). É como escrever um lembrete no caderno com lápis — tá lá pra ajudar a entender, mas não muda nada.

### Analogia 🧠
Imagina que você está montando um móvel. O comentário é como aquela anotação que você escreve na margem do manual: *"essa peça vai na gaveta"*. Não faz parte do móvel, mas ajuda você a se organizar.

> [!TIP]
> É uma **boa prática** colocar comentários no seu código! Principalmente quando a lógica é complicada. Você (no futuro) vai agradecer.

---

## Linhas 8, 9, 10 — Criando as variáveis com valor inicial

```java
double width = 0;
double height = 0;
double area = 0;
```

### O que está acontecendo?
Ele está criando **3 caixinhas** (variáveis) e colocando o valor **0** dentro de cada uma:

| Variável | Significado | Valor inicial |
|----------|-------------|---------------|
| `width` | Largura do retângulo | `0` |
| `height` | Altura do retângulo | `0` |
| `area` | Área do retângulo (resultado da conta) | `0` |

### Por que `double`?
Porque largura, altura e área podem ter **casas decimais** (tipo `3.2`, `4.3`, `13.76`). O tipo `int` só aceita números inteiros.

### Por que começar com `0`?
É como **preparar as caixinhas vazias** antes de usar. Ele sabe que vai preencher elas depois (com os valores que o usuário digitar), mas precisa criar primeiro.

### Analogia 🧠
É como colocar 3 potes vazios na mesa antes de cozinhar:
- Pote "largura" → vazio (0)
- Pote "altura" → vazio (0)
- Pote "área" → vazio (0)

Depois você vai enchendo cada um na hora certa.

---

## Linha 12 — Criando o Scanner

```java
Scanner scanner = new Scanner(System.in);
```

Cria o Scanner pra ler do teclado. Já vimos isso antes!

---

## Linhas 14–15 — Pedindo a LARGURA

```java
System.out.print("Enter the width: ");
width = scanner.nextDouble();
```

### Linha 14: Mostra a pergunta na tela
`"Enter the width: "` → O cursor fica piscando do lado esperando.

### Linha 15: Lê o número e guarda na variável

```java
width = scanner.nextDouble();
```

### ⚠️ ATENÇÃO! Percebeu algo diferente?

Olha a diferença:

```java
// Quando CRIAMOS a variável (linha 8):
double width = 0;        ← tem o "double" na frente

// Quando ATUALIZAMOS a variável (linha 15):
width = scanner.nextDouble();   ← NÃO tem "double" na frente!
```

### Por que não tem `double` na frente?

Porque a variável `width` **já foi criada** na linha 8! Você só precisa colocar o tipo (`double`) **uma vez** — quando cria a variável pela primeira vez.

Depois disso, pra mudar o valor dela, é só usar o nome direto: `width = novoValor;`

### Analogia 🧠
- **Linha 8:** Você vai na loja e **compra um pote novo** com a etiqueta "width" → precisa dizer que tipo de pote é (`double`)
- **Linha 15:** Você **coloca algo dentro do pote** que já existe → não precisa comprar outro pote, ele já tá na sua mesa!

### No teste do vídeo:
O usuário digitou `3.2` → `width` agora vale `3.2` (e não mais `0`).

---

## Linhas 17–18 — Pedindo a ALTURA

```java
System.out.print("Enter the height: ");
height = scanner.nextDouble();
```

Mesma coisa da largura! Pede um número, lê com `nextDouble()`, e guarda em `height`.

### No teste do vídeo:
O usuário digitou `4.3` → `height` agora vale `4.3`.

---

## Linha 20 — A CONTA! 🧮

```java
area = width * height;
```

### O que faz?
Multiplica `width` por `height` e guarda o resultado em `area`.

### Passo a passo com os números do teste:

```
area = width * height
area = 3.2   * 4.3
area = 13.76
```

### O `*` é o símbolo de multiplicação!

Em Java (e na maioria das linguagens), os símbolos de matemática são:

| Operação | Símbolo no Java | Exemplo | Resultado |
|----------|----------------|---------|-----------|
| Soma | `+` | `5 + 3` | `8` |
| Subtração | `-` | `10 - 4` | `6` |
| Multiplicação | `*` | `3.2 * 4.3` | `13.76` |
| Divisão | `/` | `20 / 4` | `5` |

### Analogia 🧠
Você tem os 3 potes na mesa:
- Pote "width" contém `3.2`
- Pote "height" contém `4.3`
- Pote "area" contém `0` (ainda vazio)

Agora você pega o valor do pote "width", multiplica pelo valor do pote "height", e coloca o resultado (`13.76`) no pote "area".

---

## Linha 22 — Mostrando o resultado

```java
System.out.println("The area is: " + area);
```

Junta o texto `"The area is: "` com o valor de `area` (`13.76`) e mostra na tela:

```
The area is: 13.76
```

---

## Linha 24 — Fechando o Scanner

```java
scanner.close();
```

Desliga o Scanner. Boa prática pra liberar memória!

---

## 🖥️ O resultado no console do vídeo:

```
Enter the width: 3.2
Enter the height: 4.3
The area is: 13.76
```

Perfeito! 3.2 × 4.3 = 13.76 ✅

---

## 🧩 Resumo Visual do Fluxo

```
PROGRAMA INICIA
    │
    ▼
Cria 3 caixinhas: width=0, height=0, area=0
    │
    ▼
Cria o Scanner (microfone do teclado)
    │
    ▼
Pergunta: "Enter the width:" → Usuário digita: 3.2
    │                           width agora = 3.2
    ▼
Pergunta: "Enter the height:" → Usuário digita: 4.3
    │                            height agora = 4.3
    ▼
Faz a conta: area = 3.2 * 4.3 = 13.76
    │
    ▼
Mostra: "The area is: 13.76"
    │
    ▼
Fecha o Scanner
    │
    ▼
PROGRAMA TERMINA ✅
```

---

## 💡 Conceitos NOVOS que você aprendeu com esse código

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **Comentários (`//`)** | `// Calculate area of a rectangle` — anotação ignorada pelo Java |
| 2 | **Variáveis com valor inicial** | `double width = 0;` — cria e já coloca um valor |
| 3 | **Reatribuição (sem o tipo)** | `width = scanner.nextDouble();` — muda o valor sem repetir `double` |
| 4 | **Operação matemática** | `area = width * height;` — o `*` multiplica |
| 5 | **Tabela de operadores** | `+` soma, `-` subtrai, `*` multiplica, `/` divide |

> [!NOTE]
> O conceito mais importante aqui é entender a diferença entre **declarar** uma variável (`double width = 0;`) e **reatribuir** (`width = 3.2;`). Você declara **uma vez**, mas pode reatribuir **quantas vezes quiser**!
