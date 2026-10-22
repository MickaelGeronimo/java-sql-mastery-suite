# 🎮 Mad Libs Game — Explicação Linha por Linha

Nesse vídeo, o Bro Code cria um **jogo Mad Libs** em Java! É o primeiro **mini-projeto** do curso — ele junta tudo que ensinou até agora (Scanner, variáveis, concatenação) pra fazer algo divertido.

---

## Primeiro: O que é "Mad Libs"? 🤔

Mad Libs é um **jogo de palavras** muito popular nos EUA. Funciona assim:

1. Alguém te pede palavras aleatórias: *"me diz um adjetivo"*, *"me diz um animal"*, *"me diz um verbo"*...
2. Você responde **sem saber** a história
3. As suas palavras são colocadas nos espaços em branco de uma história
4. O resultado é uma **história engraçada e sem sentido** 😂

### Exemplo:
- Você diz: adjetivo = "peludo", animal = "dinossauro", verbo = "dançando"
- História: *"Hoje eu fui a um zoológico **peludo**. Vi um **dinossauro**. Ele estava **dançando**!"*

Na segunda imagem do vídeo, o Bro Code mostra um exemplo clássico de Mad Libs em papel — aquela folha com espaços em branco pra preencher.

---

## O código completo final:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // MAD LIBS GAME

        Scanner scanner = new Scanner(System.in);

        String adjective1;
        String noun1;
        String adjective2;
        String verb1;
        String adjective3;

        System.out.print("Enter an adjective (description): ");
        adjective1 = scanner.nextLine();
        System.out.print("Enter a noun (animal or person): ");
        noun1 = scanner.nextLine();
        System.out.print("Enter an adjective (description): ");
        adjective2 = scanner.nextLine();
        System.out.print("Enter a verb end with -ing (action): ");
        verb1 = scanner.nextLine();
        System.out.print("Enter an adjective (description): ");
        adjective3 = scanner.nextLine();

        System.out.println("Today I went to a " + adjective1 + " zoo.");
        System.out.println("In an exhibit, I saw a " + noun1 + ".");
        System.out.println(noun1 + " was " + adjective2 + " and " + verb1 + "!");
        System.out.println("I was " + adjective3 + "!");
    }
}
```

---

## Agora vamos linha por linha! 👇

---

## Linha 5 — Comentário

```java
// MAD LIBS GAME
```

Anotação pra lembrar o que esse programa faz. O Java ignora.

---

## Linha 8 — Scanner

```java
Scanner scanner = new Scanner(System.in);
```

Cria o Scanner pra ler do teclado. Já conhecemos!

---

## Linhas 10–14 — Declarando variáveis SEM valor inicial 🆕

```java
String adjective1;
String noun1;
String adjective2;
String verb1;
String adjective3;
```

### O que é isso de novo?!

No código anterior, a gente fazia assim:
```java
double width = 0;    // cria E já coloca um valor (0)
```

Agora ele fez diferente:
```java
String adjective1;    // cria a variável MAS não coloca nada dentro!
```

### Qual a diferença?

| Forma | Exemplo | Significado |
|-------|---------|-------------|
| **Com valor** | `String name = "João";` | Cria a caixinha e já coloca "João" dentro |
| **Com valor vazio** | `double width = 0;` | Cria a caixinha e coloca 0 dentro |
| **Sem valor** | `String adjective1;` | Cria a caixinha **vazia** — vai preencher depois |

### Analogia 🧠

É como preparar **5 envelopes vazios** na mesa, cada um com uma etiqueta:

```
📨 "adjective1"  → (vazio, vai preencher depois)
📨 "noun1"       → (vazio, vai preencher depois)
📨 "adjective2"  → (vazio, vai preencher depois)
📨 "verb1"       → (vazio, vai preencher depois)
📨 "adjective3"  → (vazio, vai preencher depois)
```

### O que cada nome significa?

| Variável | Tipo de palavra | Em português |
|----------|----------------|-------------|
| `adjective1` | Adjetivo (descreve algo) | "bonito", "peludo", "grande" |
| `noun1` | Substantivo (animal/pessoa) | "gato", "dinossauro", "professor" |
| `adjective2` | Outro adjetivo | "preguiçoso", "azul", "maluco" |
| `verb1` | Verbo terminado em -ing (ação) | "dancing", "running", "eating" |
| `adjective3` | Outro adjetivo | "feliz", "assustado", "confuso" |

> [!NOTE]
> Os números no final (`adjective1`, `adjective2`, `adjective3`) são porque ele precisa de **3 adjetivos diferentes**. Em Java, cada variável precisa de um nome **único**, então não dá pra ter duas variáveis chamadas `adjective`.

---

## Linhas 16–25 — Fazendo as 5 perguntas ao usuário

```java
System.out.print("Enter an adjective (description): ");
adjective1 = scanner.nextLine();

System.out.print("Enter a noun (animal or person): ");
noun1 = scanner.nextLine();

System.out.print("Enter an adjective (description): ");
adjective2 = scanner.nextLine();

System.out.print("Enter a verb end with -ing (action): ");
verb1 = scanner.nextLine();

System.out.print("Enter an adjective (description): ");
adjective3 = scanner.nextLine();
```

### O padrão se repete 5 vezes:

```
1. Mostra a pergunta → System.out.print("pergunta");
2. Lê a resposta     → variavel = scanner.nextLine();
```

### Passo a passo (exemplo):

```
Tela: "Enter an adjective (description): "
Usuário digita: funny
→ adjective1 agora vale "funny"

Tela: "Enter a noun (animal or person): "
Usuário digita: dinosaur
→ noun1 agora vale "dinosaur"

Tela: "Enter an adjective (description): "
Usuário digita: lazy
→ adjective2 agora vale "lazy"

Tela: "Enter a verb end with -ing (action): "
Usuário digita: dancing
→ verb1 agora vale "dancing"

Tela: "Enter an adjective (description): "
Usuário digita: shocked
→ adjective3 agora vale "shocked"
```

### Percebeu que NÃO tem `double` ou `String` antes do nome?

```java
adjective1 = scanner.nextLine();    // ← sem "String" na frente!
```

Porque as variáveis **já foram criadas** nas linhas 10–14! Aqui a gente só está **preenchendo** elas. (Lembra da regra? Você declara o tipo **uma vez** só.)

---

## Linhas 27–30 — Montando a história! 📖

Aqui é a parte divertida! O programa pega todas as palavras que o usuário digitou e **encaixa** numa história:

### Linha 27:
```java
System.out.println("Today I went to a " + adjective1 + " zoo.");
```

```
"Today I went to a " + "funny" + " zoo."
=  "Today I went to a funny zoo."
```

### Linha 28:
```java
System.out.println("In an exhibit, I saw a " + noun1 + ".");
```

```
"In an exhibit, I saw a " + "dinosaur" + "."
= "In an exhibit, I saw a dinosaur."
```

### Linha 29 (a mais complexa!):
```java
System.out.println(noun1 + " was " + adjective2 + " and " + verb1 + "!");
```

São **6 pedaços** grudados com `+`:

```
"dinosaur" + " was " + "lazy" + " and " + "dancing" + "!"
= "dinosaur was lazy and dancing!"
```

### Linha 30:
```java
System.out.println("I was " + adjective3 + "!");
```

```
"I was " + "shocked" + "!"
= "I was shocked!"
```

---

## 🖥️ Exemplo de resultado no console:

```
Enter an adjective (description): funny
Enter a noun (animal or person): dinosaur
Enter an adjective (description): lazy
Enter a verb end with -ing (action): dancing
Enter an adjective (description): shocked

Today I went to a funny zoo.
In an exhibit, I saw a dinosaur.
dinosaur was lazy and dancing!
I was shocked!
```

Agora tenta com palavras malucas e veja o resultado engraçado! 😂

---

## 🧩 Resumo Visual do Fluxo

```
PROGRAMA INICIA
    │
    ▼
Cria 5 envelopes vazios:
  adjective1, noun1, adjective2, verb1, adjective3
    │
    ▼
Pergunta 1: "adjetivo?"     → adjective1 = "funny"
Pergunta 2: "substantivo?"  → noun1 = "dinosaur"
Pergunta 3: "adjetivo?"     → adjective2 = "lazy"
Pergunta 4: "verbo -ing?"   → verb1 = "dancing"
Pergunta 5: "adjetivo?"     → adjective3 = "shocked"
    │
    ▼
Monta a história encaixando as palavras:
  "Today I went to a funny zoo."
  "In an exhibit, I saw a dinosaur."
  "dinosaur was lazy and dancing!"
  "I was shocked!"
    │
    ▼
PROGRAMA TERMINA ✅
```

---

## 💡 Conceitos que você aprendeu/praticou aqui

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **Declarar variável sem valor** | `String adjective1;` (caixinha vazia, preenche depois) |
| 2 | **Nomes únicos com números** | `adjective1`, `adjective2`, `adjective3` |
| 3 | **Várias entradas do Scanner** | Perguntou 5 vezes usando `nextLine()` |
| 4 | **Concatenação de muitos pedaços** | `noun1 + " was " + adjective2 + " and " + verb1 + "!"` |
| 5 | **Mini-projeto prático** | Juntou tudo que aprendeu num programa divertido! |

> [!TIP]
> Esse tipo de projeto é ótimo pra praticar! Tenta criar sua própria versão do Mad Libs com uma história diferente. Pode ser em português! Por exemplo:
> ```java
> System.out.println("Hoje eu fui a um parque " + adjetivo1 + ".");
> System.out.println("Lá eu vi um " + animal + " que estava " + verbo + "!");
> ```
