# 🎲 Capítulo 30: O Guia Definitivo e Completo do Dice Roller Program (Simulador de Lançamento de Dados com Arte ASCII) — Partes 1 a 5

> **Documento Oficial de Estudos para Cópia no Google Docs:** Este material foi construído para ser **100% autossuficiente**. Ele descreve todos os contextos visuais das aulas do canal Bro Code (*Java Full Course for free*), inclui diagramas de fluxo, tabelas de rastreio de memória, galeria visual completa dos dados em ASCII art, varredura linha por linha e códigos compiláveis sem erros.

---

## 📑 Índice da Apostila
1. [Visão Geral do Projeto e o Teaser de Abertura](#1-visão-geral-do-projeto)
2. [A Estratégia de Planejamento: Pseudocódigo em Comentários](#2-a-estratégia-de-planejamento)
3. [Instanciação e Ferramentas: Scanner e Random](#3-instanciação-e-ferramentas)
4. [Validação Defensiva de Entrada (`if (numOfDice > 0)`)](#4-validação-defensiva-de-entrada)
5. [O Laço `for` e o Sorteio com `random.nextInt(1, 7)`](#5-o-laço-for-e-o-sorteio)
6. [O Acumulador `total` e o Operador Composto `+=`](#6-o-acumulador-total-e-o-operador-composto)
7. [O Método Auxiliar `static void printDie(int roll)`](#7-o-método-auxiliar-printdie)
8. [Text Blocks (`"""`) e o Truque do Mapa de Caracteres (`charmap`)](#8-text-blocks-e-o-charmap)
9. [A Galeria Completa dos 6 Dados em Arte ASCII](#9-a-galeria-completa-dos-dados)
10. [O `switch` Moderno com Seta (`->`) e a Correção do Erro Duplicate Case](#10-o-switch-moderno-e-a-correção-de-erros)
11. [Diagrama de Fluxo e Execução nos Bastidores](#11-diagrama-de-fluxo-e-memória)
12. [Código-Fonte Completo e Compilável de Todo o Capítulo 30](#12-código-fonte-completo)

---

<a name="1-visão-geral-do-projeto"></a>
## 🎲 1. Visão Geral do Projeto e o Teaser de Abertura

No timestamp `4:51:30`, o autor inicia o capítulo mostrando o jogo funcionando ao vivo no terminal do IntelliJ IDEA para demonstrar o resultado final:
* O usuário digita quantos dados quer jogar (ex: `3`).
* O console imprime a frase de ação: `Rolling 3 dice...`.
* Em seguida, para cada dado sorteado, o computador desenha na tela a representação gráfica de um dado de 6 faces usando caracteres comuns do teclado (**ASCII Art**), com bordas `-------`, paredes `|` e círculos preenchidos `●`.
* Logo abaixo de cada dado desenhado, o programa informa o valor: `You rolled: 3`.
* No final, o programa calcula a soma de todas as faces e imprime: `Total: 14`.

O objetivo pedagógico deste capítulo é consolidar a integração de **Entrada de Dados (Scanner)**, **Geração Aleatória (Random)**, **Laços de Repetição (for)**, **Controle de Escopo de Variáveis**, **Métodos Modulares (static void)** e **Estruturas de Decisão Rápidas (Enhanced Switch)**.

---

<a name="2-a-estratégia-de-planejamento"></a>
## 📝 2. A Estratégia de Planejamento: Pseudocódigo em Comentários

No timestamp `4:53:29`, o autor ensina a regra de ouro que separa programadores amadores de profissionais: **nunca comece a programar a lógica difícil diretamente**.

Antes de escrever qualquer cálculo, ele escreveu 5 linhas de comentários em inglês dentro do método `main`:
```java
// GET # OF DICE FROM THE USER
// CHECK IF # OF DICE > 0
// ROLL ALL THE DICE
// GET THE TOTAL
// DISPLAY ASCII OF DICE
```

### 🧠 Por que fazer isso?
1. **Evita o Bloqueio Mental:** Quando você olha para uma tela em branco, o cérebro trava tentando pensar em gráficos, sorteios e somas ao mesmo tempo.
2. **Desenvolvimento em Fatias (Incremental):** Você resolve um problema pequeno por vez. Você substitui o comentário 1 por 2 linhas de código, testa e vê funcionar. Depois faz o mesmo com o comentário 2.

---

<a name="3-instanciação-e-ferramentas"></a>
## ⚙️ 3. Instanciação e Ferramentas: Scanner e Random

Para este projeto, precisamos de dois "motores" fornecidos pelo pacote `java.util`:
```java
import java.util.Random;
import java.util.Scanner;
```

```java
Scanner scanner = new Scanner(System.in);
Random random = new Random();
int numOfDice;
int total = 0;
```

### 🔍 O que cada linha faz na memória RAM?
* **`Scanner scanner = new Scanner(System.in);`**:
  * Cria o leitor do teclado conectado ao canal de entrada padrão do sistema (`System.in`).
* **`Random random = new Random();`**:
  * Cria o gerador matemático pseudo-aleatório. Ele utiliza uma "semente" baseada no relógio interno em nanossegundos do computador para que os números gerados nunca se repitam de forma previsível.
* **`int numOfDice;`**:
  * Declara a variável que guardará a quantidade de dados solicitada pelo usuário.
* **`int total = 0;`**:
  * Inicializa o acumulador de pontos com zero. É fundamental iniciar com `0` porque se você tentar somar algo em uma variável vazia, o compilador do Java acusará erro de inicialização.

---

<a name="4-validação-defensiva-de-entrada"></a>
## 🛡️ 4. Validação Defensiva de Entrada (`if (numOfDice > 0)`)

Nos timestamps `4:54:48` e `4:55:12`, o autor solicita os dados ao usuário e cria a barreira de segurança:
```java
System.out.print("Enter the # of dice to roll: ");
numOfDice = scanner.nextInt();

if (numOfDice > 0) {
    System.out.println("You roll the dice"); // Linha temporária de teste!
}
else {
    System.out.println("# of dice must be greater than 0");
}
```

### 💡 Lições Críticas Desta Etapa:
1. **`print` vs `println`:** O autor usou `System.out.print` (sem `ln`) na pergunta. Isso mantém o cursor piscando na mesma linha, colado nos dois pontos `: `, proporcionando uma experiência de terminal elegante.
2. **Validação Condicional:** O que aconteceria se o usuário digitasse `-5` ou `0`? Sem o `if`, o laço `for` simplesmente não rodaria ou o programa ficaria sem resposta. O bloco `else` avisa educadamente: *"A quantidade de dados deve ser maior que zero"*.
3. **O Teste com "Passo de Bebê":** O autor não tentou fazer o sorteio imediatamente; ele colocou `"You roll the dice"` dentro do `if` e rodou o código digitando `3`. Ao ver a frase na tela, ele teve certeza de que o `if` funcionava antes de prosseguir!

---

<a name="5-o-laço-for-e-o-sorteio"></a>
## 🔄 5. O Laço `for` e o Sorteio com `random.nextInt(1, 7)`

No timestamp `4:56:36`, o autor apaga a frase temporária e implementa a rolagem real:
```java
for (int i = 0; i < numOfDice; i++) {
    int roll = random.nextInt(1, 7);
    System.out.println("You rolled: " + roll);
}
```

### 🎯 A Anatomia do Laço `for`:
* `int i = 0;` $\rightarrow$ Contador de rodadas começa em zero.
* `i < numOfDice;` $\rightarrow$ Enquanto não tiver jogado todos os dados solicitados.
* `i++` $\rightarrow$ Avança 1 na contagem a cada dado arremessado.

### ❓ Por que `random.nextInt(1, 7)` e não `(1, 6)`?
Em Java moderno, o método `nextInt(origem, limite)` opera com um **intervalo semiaberto**:
$$\text{Intervalo: } [\text{origem}, \text{limite})$$
* **O 1 é INCLUSIVO:** Pode ser sorteado.
* **O 7 é EXCLUSIVO:** **NUNCA** é sorteado. O Java para no número imediatamente anterior ($7 - 1 = 6$).
* Portanto, os números possíveis são exatamente: $\{1, 2, 3, 4, 5, 6\}$.

### 📊 Rastreio de Execução para 4 Dados (`numOfDice = 4`):
| Volta | Contador `i` | Condição `i < 4`? | Sorteio `roll` | O Que Sai no Console |
| :---: | :---: | :---: | :---: | :--- |
| **1ª** | `0` | `0 < 4` (**Sim**) | `5` | `You rolled: 5` |
| **2ª** | `1` | `1 < 4` (**Sim**) | `5` | `You rolled: 5` |
| **3ª** | `2` | `2 < 4` (**Sim**) | `1` | `You rolled: 1` |
| **4ª** | `3` | `3 < 4` (**Sim**) | `2` | `You rolled: 2` |
| **Fim** | `4` | `4 < 4` (**Não!**) | — | *(O laço encerra imediatamente)* |

---

<a name="6-o-acumulador-total-e-o-operador-composto"></a>
## ➕ 6. O Acumulador `total` e o Operador Composto `+=`

Nos timestamps `4:57:14` e `4:57:38`, o autor implementa o cálculo da pontuação total.

### 6.1 Da Forma Longa para o Operador Composto
Primeiro ele escreve:
```java
total = total + roll;
```
E logo em seguida simplifica para o padrão profissional:
```java
total += roll;
```
* **Significado:** Pegue o número que acabou de ser sorteado em `roll` e adicione ao que já estava guardado dentro de `total`.

### 6.2 A Regra de Ouro da Posição do `println`:
```java
for (int i = 0; i < numOfDice; i++) {
    int roll = random.nextInt(1, 7);
    System.out.println("You rolled: " + roll);
    total += roll; // DENTRO DO LAÇO: Acumula a cada dado!
}

// FORA DO LAÇO: Só imprime o resultado final após todos os dados rolarem!
System.out.println("Total: " + total);
```
* Se o `println` ficasse dentro do `for`, o programa gritaria a soma a cada dado jogado. Colocando **fora**, ele aguarda silenciosamente o término de todos os arremessos e imprime apenas o total final consolidado!

---

<a name="7-o-método-auxiliar-printdie"></a>
## 🧱 7. O Método Auxiliar `static void printDie(int roll)`

No timestamp `5:00:00`, para não poluir o método `main`, o autor cria um método auxiliar dedicado:
```java
static void printDie(int roll) {
    // Código de desenho aqui...
}
```

### 🔍 O que significa cada palavra do cabeçalho?
1. **`static`:** Pertence à classe `Main`. Pode ser chamado diretamente do `main` sem precisarmos criar um objeto instanciado (`new Main()`).
2. **`void`:** O método **não devolve nada** (não tem `return`). Ele é uma ação pura: sua única responsabilidade é cuspir o desenho na tela.
3. **`printDie`:** O nome do método (em inglês: *"imprima o dado"* — em inglês, o singular de dados (*dice*) é *die*).
4. **`(int roll)`:** O **parâmetro de entrada**. Quando o `main` chama `printDie(roll);`, o número que foi sorteado viaja para dentro do método para decidir qual desenho mostrar.

---

<a name="8-text-blocks-e-o-charmap"></a>
## 🎨 8. Text Blocks (`"""`) e o Truque do Mapa de Caracteres (`charmap`)

Nos timestamps `5:00:15` a `5:00:44`, o autor introduz duas técnicas espetaculares:

### 8.1 A Revolução dos Text Blocks (Aspas Triplas `"""`)
Introduzidos no Java 15+, os **Text Blocks** permitem escrever textos de múltiplas linhas exatamente como você os enxerga:
```java
String dice1 = """
         -------
        |       |
        |   ●   |
        |       |
         -------
        """;
```
* **Vantagem:** Não precisa concatenar várias linhas com `+` nem digitar `\n` manualmente! O Java preserva todas as quebras de linha e espaçamentos exatos.

### 8.2 O Truque do Windows `charmap` para Pegar o Círculo `●`
Como digitar a bolinha do dado no teclado se ela não existe nas teclas normais?
1. Pressione `Win + R` no teclado para abrir a caixa "Executar" do Windows.
2. Digite **`charmap`** e pressione `Enter`.
3. Na janela do **Character Map** (Mapa de Caracteres), selecione a fonte *Arial*.
4. Localize o símbolo **`U+25CF: Black Circle`** (um círculo sólido e preenchido).
5. Clique em **Select** e depois em **Copy**.
6. Volte ao IntelliJ IDEA e cole (`Ctrl + V`) no centro do dado!

---

<a name="9-a-galeria-completa-dos-dados"></a>
## 🖼️ 9. A Galeria Completa dos 6 Dados em Arte ASCII

Aqui estão as 6 variáveis de texto desenhadas pelo autor para cada face do dado:

```java
String dice1 = """
         -------
        |       |
        |   ●   |
        |       |
         -------
        """;

String dice2 = """
         -------
        | ●     |
        |       |
        |     ● |
         -------
        """;

String dice3 = """
         -------
        | ●     |
        |   ●   |
        |     ● |
         -------
        """;

String dice4 = """
         -------
        | ●   ● |
        |       |
        | ●   ● |
         -------
        """;

String dice5 = """
         -------
        | ●   ● |
        |   ●   |
        | ●   ● |
         -------
        """;

String dice6 = """
         -------
        | ●   ● |
        | ●   ● |
        | ●   ● |
         -------
        """;
```

### 📐 Geometria do Dado ASCII:
* Borda superior e inferior: 1 espaço + 7 traços `-------`
* Paredes laterais: barras verticais `|`
* Espaço interno: 7 colunas de largura, permitindo centralizar a bolinha na 4ª coluna ou colocá-la nas extremidades com 3 espaços de separação.

---

<a name="10-o-switch-moderno-e-a-correção-de-erros"></a>
## ⚡ 10. O `switch` Moderno com Seta (`->`) e a Correção de Erros

Nos timestamps `5:02:46` e `5:03:02`, o autor conecta o número sorteado ao desenho:

```java
switch (roll) {
    case 1 -> System.out.print(dice1);
    case 2 -> System.out.print(dice2);
    case 3 -> System.out.print(dice3);
    case 4 -> System.out.print(dice4);
    case 5 -> System.out.print(dice5);
    case 6 -> System.out.print(dice6);
    default -> System.out.print("Invalid roll");
}
```

### 10.1 A Anatomia do Erro do IntelliJ: *Duplicate case label: 4*
Ao escrever o código, o autor copiou a linha do 4 e esqueceu de mudar o número para 6:
```java
case 4 -> System.out.print(dice4);
case 4 -> System.out.print(dice4); // ERRO!
```
* O IntelliJ imediatamente colocou uma **linha ondulada vermelha** embaixo da segunda linha.
* **Por quê?** O Java não permite que dois casos tenham o mesmo rótulo dentro do mesmo `switch`. Se a variável `roll` for 4, o Java não saberia qual dos dois blocos executar! Alterar o segundo para `case 6` eliminou o erro imediatamente.

### 10.2 Por que a seta `->` é superior aos dois pontos `:`?
* No `switch` antigo com `:`, você era obrigado a colocar `break;` em cada linha. Se esquecesse, o Java caía nos casos de baixo (*fall-through*).
* No `switch` moderno com seta `->`, o Java executa **apenas** aquela instrução e sai automaticamente!

### 10.3 Por que `System.out.print` (sem `ln`)?
Porque o Text Block `""" ... """` **já possui quebras de linha embutidas no próprio desenho**. Usar `println` acrescentaria uma linha em branco feia e indesejada abaixo do dado.

---

<a name="11-diagrama-de-fluxo-e-memória"></a>
## 🗺️ 11. Diagrama de Fluxo e Execução nos Bastidores

Veja como os métodos conversam durante o ciclo de vida do programa:

```text
       MÉTODO MAIN                                   MÉTODO PRINTDIE(roll)
┌───────────────────────────┐                     ┌───────────────────────────┐
│ 1. Usuário digita numOfDice│                     │                           │
│ 2. Testa if (numOfDice > 0)│                     │                           │
│ 3. Inicia laço for:       │                     │                           │
│    - roll = random(1, 7)  │                     │                           │
│    - printDie(roll)───────┼────────────────────►│ 4. switch (roll):         │
│                           │                     │    - Imprime a arte ASCII │
│    ◄──────────────────────┼─────────────────────┤ 5. Retorna ao main        │
│    - Imprime "You rolled" │                     └───────────────────────────┘
│    - total += roll        │
│ 4. Fim do for:            │
│    - Imprime "Total: X"   │
│ 5. scanner.close()        │
└───────────────────────────┘
```

---

<a name="12-código-fonte-completo"></a>
## 💻 12. Código-Fonte Completo e Compilável de Todo o Capítulo 30

Copie e cole este código diretamente no seu arquivo `Main.java` no IntelliJ IDEA para rodar o projeto finalizado:

```java
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // JAVA DICE ROLLER PROGRAM

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numOfDice;
        int total = 0;

        // GET # OF DICE FROM THE USER
        System.out.print("Enter the # of dice to roll: ");
        numOfDice = scanner.nextInt();

        // CHECK IF # OF DICE > 0
        if (numOfDice > 0) {

            // ROLL ALL THE DICE
            for (int i = 0; i < numOfDice; i++) {
                int roll = random.nextInt(1, 7);
                printDie(roll);
                System.out.println("You rolled: " + roll);
                total += roll;
            }

            // GET THE TOTAL
            System.out.println("Total: " + total);
        }
        else {
            System.out.println("# of dice must be greater than 0");
        }

        scanner.close();
    }

    // DISPLAY ASCII OF DICE
    static void printDie(int roll) {

        String dice1 = """
                 -------
                |       |
                |   ●   |
                |       |
                 -------
                """;

        String dice2 = """
                 -------
                | ●     |
                |       |
                |     ● |
                 -------
                """;

        String dice3 = """
                 -------
                | ●     |
                |   ●   |
                |     ● |
                 -------
                """;

        String dice4 = """
                 -------
                | ●   ● |
                |       |
                | ●   ● |
                 -------
                """;

        String dice5 = """
                 -------
                | ●   ● |
                |   ●   |
                | ●   ● |
                 -------
                """;

        String dice6 = """
                 -------
                | ●   ● |
                | ●   ● |
                | ●   ● |
                 -------
                """;

        switch (roll) {
            case 1 -> System.out.print(dice1);
            case 2 -> System.out.print(dice2);
            case 3 -> System.out.print(dice3);
            case 4 -> System.out.print(dice4);
            case 5 -> System.out.print(dice5);
            case 6 -> System.out.print(dice6);
            default -> System.out.print("Invalid roll");
        }
    }
}
```

---

## 🖥️ Demonstração de Saída Real do Terminal (Exemplo com 3 Dados)

```text
Enter the # of dice to roll: 3
 -------
| ●   ● |
| ●   ● |
| ●   ● |
 -------
You rolled: 6
 -------
| ●     |
|       |
|     ● |
 -------
You rolled: 2
 -------
| ●   ● |
| ●   ● |
| ●   ● |
 -------
You rolled: 6
Total: 14

Process finished with exit code 0
```

---

Pronto! Esta apostila definitiva reúne todos os conceitos, truques e explicações detalhadas do Capítulo 30 em um formato perfeito para seu Google Docs! 🎲📘✨
