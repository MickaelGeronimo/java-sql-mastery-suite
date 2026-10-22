# 📖 Explicação Completa do Código Java — Linha por Linha

Este código ensina como **pedir informações ao usuário pelo teclado** e depois **mostrar essas informações na tela**, usando a linguagem Java.

Pense assim: é como um formulário. O programa pergunta seu nome, idade, nota e se é estudante, e depois mostra tudo de volta pra você.

---

## Linha 1 — O "import"

```java
import java.util.Scanner;
```

### O que faz?
Essa linha **importa** (traz) uma ferramenta chamada `Scanner` que já vem dentro do Java.

### Analogia 🧠
Imagine que você quer usar uma calculadora. Ela existe na sua mochila, mas você precisa **tirar ela de lá** antes de usar. O `import` é isso: você está dizendo ao Java *"ei, eu vou precisar usar o Scanner, traz ele pra mim"*.

### Por que precisa?
Sem essa linha, o Java não sabe o que é `Scanner`. Ele daria **erro**.

---

## Linhas 3–4 — A "casca" do programa

```java
public class Main {
    public static void main(String[] args) {
```

### O que faz?
- `public class Main` → Cria a **classe** principal do programa. Em Java, **todo código precisa estar dentro de uma classe**. O nome `Main` é o nome do arquivo também (`Main.java`).
- `public static void main(String[] args)` → É o **ponto de partida** do programa. Quando você clica em "Run" (▶️) na IDE, o Java procura exatamente essa linha e começa a executar tudo que está dentro dela.

### Analogia 🧠
Pense na `class Main` como a **capa de um livro** — todo livro precisa de uma capa.

E o `public static void main(...)` é a **primeira página** — é onde a história começa.

> [!NOTE]
> Por enquanto, não se preocupe em decorar `public static void main(String[] args)`. Só saiba que **todo programa Java começa por aqui**. Com o tempo você vai entender cada palavra dessa linha.

---

## Linha 6 — Criando o Scanner (a "máquina de ler o teclado")

```java
Scanner scanner = new Scanner(System.in);
```

### Quebrando em pedaços:

| Pedaço | Significado |
|--------|-------------|
| `Scanner` (o primeiro) | É o **tipo** — estamos dizendo que vamos criar um objeto do tipo Scanner |
| `scanner` (o segundo, minúsculo) | É o **nome** que demos pra ele. Poderia ser qualquer nome, tipo `leitor`, `teclado`, etc. |
| `new` | Significa **"crie um novo"** |
| `Scanner(System.in)` | Cria o Scanner conectado ao **teclado** (`System.in` = entrada do sistema = teclado) |

### Analogia 🧠
É como ligar um microfone. Você está dizendo: *"Cria um microfone novo e conecta ele na entrada de áudio (teclado) pra eu poder ouvir o que o usuário vai dizer"*.

---

## Linhas 8–9 — Perguntando o NOME

```java
System.out.print("Enter your name: ");
String name = scanner.nextLine();
```

### Linha 8: `System.out.print("Enter your name: ");`
- **`System.out.print`** → Mostra um texto na tela do console.
- A diferença entre `print` e `println`:
  - `print` → Escreve e o cursor **fica na mesma linha** (o usuário digita do lado)
  - `println` → Escreve e o cursor **pula pra linha de baixo**
- Nesse caso, aparece: `Enter your name: ` e o cursor fica piscando ali do lado, esperando você digitar.

### Linha 9: `String name = scanner.nextLine();`

| Pedaço | Significado |
|--------|-------------|
| `String` | **Tipo de dado** = texto (letras, palavras, frases). Exemplos: `"mickael"`, `"Bro Code"`, `"Olá mundo"` |
| `name` | É o **nome da variável** — a "caixinha" onde vamos guardar o texto que o usuário digitar |
| `=` | Significa **"recebe"** ou **"guarda o valor"** |
| `scanner.nextLine()` | Lê **uma linha inteira** que o usuário digitar e dá Enter |

### Analogia 🧠
Imagine uma caixa de correio com a etiqueta "name". O `scanner.nextLine()` pega a carta (o que você digitou) e coloca dentro dessa caixa.

### No seu teste:
Você digitou `mickael` → então a variável `name` ficou guardando `"mickael"`.

---

## Linhas 11–12 — Perguntando a IDADE

```java
System.out.print("Enter your age: ");
int age = scanner.nextInt();
```

### O que muda aqui?

| Pedaço | Significado |
|--------|-------------|
| `int` | **Tipo de dado** = número inteiro (sem vírgula). Exemplos: `23`, `18`, `100`, `-5` |
| `age` | Nome da variável (caixinha) |
| `scanner.nextInt()` | Lê um **número inteiro** do teclado (não aceita letras nem vírgula!) |

### Por que `int` e não `String`?
Porque idade é um **número**, não um texto. Se guardássemos como `String`, não poderíamos fazer contas com ele depois (tipo `age + 1`).

### No seu teste:
Você digitou `23` → então `age` ficou guardando `23`.

---

## Linhas 14–15 — Perguntando o GPA (nota média)

```java
System.out.print("What is your gpa: ");
double gpa = scanner.nextDouble();
```

| Pedaço | Significado |
|--------|-------------|
| `double` | **Tipo de dado** = número com casas decimais. Exemplos: `3.454645`, `2.1`, `9.99` |
| `gpa` | Nome da variável |
| `scanner.nextDouble()` | Lê um **número decimal** do teclado |

### Por que `double` e não `int`?
Porque nota média pode ter **vírgula/ponto**. O `int` só aceita números inteiros (tipo `3`), mas `double` aceita `3.454645`.

> [!TIP]
> No Brasil usamos **vírgula** para decimais (3,45), mas no Java o padrão pode variar. No seu computador, como a configuração é brasileira, você usou `3,454645` com vírgula e funcionou! Isso depende da configuração regional (Locale) do seu sistema.

### No seu teste:
Você digitou `3,454645` → `gpa` guardou `3.454645`.

---

## Linhas 17–18 — Perguntando se é ESTUDANTE

```java
System.out.print("Are you a student? (true/false): ");
boolean isStudent = scanner.nextBoolean();
```

| Pedaço | Significado |
|--------|-------------|
| `boolean` | **Tipo de dado** = só pode ser **`true`** (verdadeiro) ou **`false`** (falso). Nada mais! |
| `isStudent` | Nome da variável |
| `scanner.nextBoolean()` | Lê `true` ou `false` do teclado |

### Analogia 🧠
Pense num interruptor de luz: ele só tem **dois estados** — ligado (`true`) ou desligado (`false`). O `boolean` é exatamente isso.

### No seu teste:
Você digitou `true` → `isStudent` guardou `true` (sim, é estudante).

---

## Resumo dos 4 tipos de dados usados

| Tipo | Serve pra | Método do Scanner | Exemplo |
|------|-----------|-------------------|---------|
| `String` | Texto (palavras, frases) | `nextLine()` | `"mickael"` |
| `int` | Número inteiro | `nextInt()` | `23` |
| `double` | Número com decimal | `nextDouble()` | `3.454645` |
| `boolean` | Verdadeiro ou falso | `nextBoolean()` | `true` |

---

## Linhas 20–22 — Mostrando as informações na tela

```java
System.out.println("Hello " + name);
System.out.println("You are " + age + " years old");
System.out.println("Your gpa is: " + gpa);
```

### O que é esse `+`?
O `+` aqui **não é soma de matemática**. Ele serve para **juntar (concatenar)** texto com variáveis.

### Exemplo passo a passo (linha 21):
```
"You are " + age + " years old"
"You are " + 23   + " years old"
"You are 23 years old"
```

O Java pega os pedaços e **cola tudo junto** formando uma frase completa.

### Resultado no seu teste:
```
Hello mickael
You are 23 years old
Your gpa is: 3.454645
```

---

## Linhas 24–29 — A decisão: `if` / `else`

```java
if(isStudent){
    System.out.println("You are enrolled as a student");
}
else{
    System.out.println("You are NOT enrolled");
}
```

### Como funciona?

```
SE (isStudent é true) {
    → executa o que está aqui dentro
}
SENÃO {
    → executa o que está aqui dentro
}
```

É como uma **bifurcação na estrada**:

```
        isStudent?
        /        \
      SIM         NÃO
       |           |
  "enrolled"   "NOT enrolled"
```

### No seu teste:
Você digitou `true` → `isStudent` é `true` → entrou no `if` → mostrou: **"You are enrolled as a student"**

Se tivesse digitado `false` → cairia no `else` → mostraria: **"You are NOT enrolled"**

> [!IMPORTANT]
> O `if/else` é uma das coisas **mais importantes** da programação. É assim que o programa toma **decisões**. Praticamente todo programa do mundo usa isso.

---

## Linha 31 — Fechando o Scanner

```java
scanner.close();
```

### O que faz?
**Desliga** o Scanner. Como você já terminou de ler tudo que precisava do teclado, você fecha ele pra liberar a memória.

### Analogia 🧠
É como desligar o microfone depois que acabou a entrevista. Você não precisa mais dele, então desliga.

---

## Linhas 32–33 — Fechando as chaves

```java
    }
}
```

- A primeira `}` fecha o método `main` (o ponto de partida)
- A segunda `}` fecha a `class Main` (a classe)

Toda `{` que abre **precisa** de uma `}` que fecha. Sempre em pares!

---

## 🎯 Resumo Visual do Fluxo Completo

```
PROGRAMA INICIA
    │
    ▼
Pergunta: "Enter your name:" ──→ Usuário digita: mickael
    │
    ▼
Pergunta: "Enter your age:" ──→ Usuário digita: 23
    │
    ▼
Pergunta: "What is your gpa:" ──→ Usuário digita: 3,454645
    │
    ▼
Pergunta: "Are you a student?" ──→ Usuário digita: true
    │
    ▼
Mostra: "Hello mickael"
Mostra: "You are 23 years old"
Mostra: "Your gpa is: 3.454645"
    │
    ▼
isStudent é true?
    ├── SIM → "You are enrolled as a student" ✅
    └── NÃO → "You are NOT enrolled"
    │
    ▼
Fecha o Scanner
    │
    ▼
PROGRAMA TERMINA (exit code 0 = sem erros)
```

---

## 💡 Conceitos que você aprendeu com esse código

1. **`import`** — Trazer ferramentas prontas do Java
2. **`Scanner`** — Ler dados do teclado
3. **Variáveis** — Caixinhas que guardam valores (`name`, `age`, `gpa`, `isStudent`)
4. **Tipos de dados** — `String`, `int`, `double`, `boolean`
5. **`System.out.print`** / **`println`** — Mostrar texto na tela
6. **Concatenação (`+`)** — Juntar texto com variáveis
7. **`if / else`** — Tomar decisões no código
8. **`.close()`** — Fechar recursos quando não precisa mais
