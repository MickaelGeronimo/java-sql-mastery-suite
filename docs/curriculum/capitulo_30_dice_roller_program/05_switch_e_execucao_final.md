# 🎲 Capítulo 30: Dice Roller Program (Simulador de Lançamento de Dados) — Parte 5: O `switch` Moderno, Tratamento de Casos e Execução Gráfica Final

---

## 📌 Contexto Geral e Visual das Imagens do Curso

> **Nota para leitura no Google Docs:** Este documento é **100% autossuficiente**. Todas as ações visuais do autor no IntelliJ IDEA, os avisos de erro de compilação do editor (sublinhados vermelhos ondulados), as correções em tempo real e as saídas gráficas no console com os dados desenhados estão integralmente descritos em prosa.

---

### 🖼️ O Que Está Acontecendo nas 3 Capturas de Tela do Vídeo

#### 1. Imagem 1 (`media_1791092723964.png`) — Timestamp `5:02:46 / 12:00:00`
* **Cenário:** O autor está implementando a lógica de decisão dentro do método `printDie(int roll)`, conectando o número sorteado ao desenho ASCII correspondente.
* **O uso do Enhanced Switch (Switch com Seta `->`):**
  * Ele abre a estrutura `switch(roll) {`.
  * Cria os ramos de 1 a 5:
    * `case 1 -> System.out.print(dice1);`
    * `case 2 -> System.out.print(dice2);`
    * `case 3 -> System.out.print(dice3);`
    * `case 4 -> System.out.print(dice4);`
    * `case 5 -> System.out.print(dice5);`
  * **O Momento Didático do Erro:** Ao duplicar a Linha 87 para criar o caso 6, o código fica temporariamente com dois `case 4`:
    ```java
    case 4 -> System.out.print(dice4);
    ```
  * O IntelliJ IDEA imediatamente marca a Linha 89 com uma **ondulação vermelha** (*red squiggly line*) indicando erro: *"Duplicate case label: 4"*. O Java proíbe ter dois casos com o mesmo valor no mesmo `switch`!

#### 2. Imagem 2 (`media_1791092739659.png`) — Timestamp `5:03:02 / 12:00:00`
* **Cenário:** O autor corrige o erro de digitação e finaliza a estrutura condicional:
  * A Linha 89 é corrigida para:
    ```java
    case 6 -> System.out.print(dice6);
    ```
    O aviso vermelho desaparece instantaneamente!
  * **A Rede de Segurança (`default`):** Na Linha 90, ele adiciona:
    ```java
    default -> System.out.print("Invalid roll");
    ```
  * Ele explica: se por algum imprevisto for passado um número que não esteja entre 1 e 6 (como 0, 7 ou negativo), o programa não vai falhar em silêncio; ele avisará que o número sorteado é inválido.

#### 3. Imagem 3 (`media_1791092746214.png`) — Execução ao Vivo no Console com Arte ASCII
* **Cenário:** O autor conecta a chamada `printDie(roll);` dentro do laço `for` no método `main` e clica em **Run ▶**.
* **O que aparece no terminal:**
  * O console exibe os dados reais com as bolinhas `●` desenhadas perfeitamente alinhadas:
    * Aparece a face 2 (com dois pontos dispostos diagonalmente) e a frase `You rolled: 2`.
    * Aparece a face 6 (com duas colunas paralelas de três pontos cada) e a frase `You rolled: 6`.
  * Na parte inferior, o acumulador imprime o resultado consolidado:
    ```text
    Total: 14
    ```
    *(No teste completo com 3 dados: rolou 6, depois 2, depois 6 $\rightarrow$ $6 + 2 + 6 = 14$)*.
  * O autor seleciona com o mouse a arte ASCII do número 6 para destacar que o espaçamento dos Text Blocks funcionou com precisão cirúrgica no terminal!

---

## 🧠 Conceitos Explicados de Forma Amadora e Descomplicada

### 1. Por que o IntelliJ deu erro na Imagem 1 (*Duplicate case label*)?
Imagine que você tem uma lista telefônica e dois números diferentes são rotulados com o nome "Pedro". Quando alguém pedir *"ligue para o Pedro"*, o computador não sabe para qual dos dois ligar e trava.
No Java é a mesma coisa:
* Cada `case` dentro de um `switch` deve ser **único**.
* Se você escrever `case 4` duas vezes, o compilador recusa compilar para evitar ambiguidades. Assim que você troca o segundo para `case 6`, a ordem é restaurada!

### 2. Por que usar o `switch` moderno com seta (`->`) em vez dos dois pontos (`:`)?
No Java clássico (antigo), você precisava escrever `case 1: ... break;`. Se esquecesse a palavra `break;`, o Java caía nos casos de baixo (o famoso efeito cascata ou *fall-through*).
Com o **Switch Moderno (Arrow Switch `->`)**:
* Você não precisa escrever `break;`! O Java executa apenas aquela linha específica e salta imediatamente para fora do `switch`.
* O código fica muito mais limpo, moderno e livre de bugs acidentais.

### 3. Por que usamos `System.out.print(dice1)` com `print` e não `println`?
Porque a nossa String multilinhas (`dice1 = """ ... """`) **já possui quebras de linha naturais dentro dela**!
Se usássemos `println`, o Java adicionaria uma linha em branco extra desnecessária após o desenho do dado. Usando `print`, a arte termina exatamente onde queremos.

---

## 💻 Código-Fonte Completo e Compilável (Capítulo 30 Final)

Este é o código completo e definitivo do simulador de dados com arte ASCII:

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

## 🔍 Varredura Linha por Linha Enumerada

Abaixo está o rastreamento das linhas centrais adicionadas e executadas nesta etapa:

```
Linha 23:             for (int i = 0; i < numOfDice; i++) {
Linha 24:                 int roll = random.nextInt(1, 7);
Linha 25:                 printDie(roll);
Linha 26:                 System.out.println("You rolled: " + roll);
Linha 27:                 total += roll;
Linha 28:             }
...
Linha 83:         switch (roll) {
Linha 84:             case 1 -> System.out.print(dice1);
Linha 85:             case 2 -> System.out.print(dice2);
Linha 86:             case 3 -> System.out.print(dice3);
Linha 87:             case 4 -> System.out.print(dice4);
Linha 88:             case 5 -> System.out.print(dice5);
Linha 89:             case 6 -> System.out.print(dice6);
Linha 90:             default -> System.out.print("Invalid roll");
Linha 91:         }
Linha 92:     }
```

### Explicação Detalhada:

* **Linha 25 (`printDie(roll);`):**
  * **O que faz:** Aciona o método `printDie`, passando o número sorteado (ex: `2`).
  * **No console:** O método vai desenhar a caixinha do dado correspondente.
* **Linha 26 (`System.out.println("You rolled: " + roll);`):**
  * **O que faz:** Imprime o texto identificando o valor e salta para a linha de baixo.
  * **No console:** `You rolled: 2`
* **Linha 83 (`switch (roll) {`):**
  * **O que faz:** Avalia a variável `roll`. Compara o valor dela contra cada um dos rótulos de `case`.
  * **No console:** Nada nesta linha diretamente.
* **Linhas 84 a 89 (`case 1 -> ... case 6 -> ...`):**
  * **O que faz:** Se `roll == 2`, salta diretamente para o `case 2` e envia o conteúdo de `dice2` para a saída de texto via `System.out.print`.
  * **No console:** A moldura do dado com as duas bolinhas é renderizada na tela.
* **Linha 90 (`default -> System.out.print("Invalid roll");`):**
  * **O que faz:** Disparado apenas se `roll` tiver um valor fora do intervalo $\{1, 2, 3, 4, 5, 6\}$.
  * **No console:** Só aparece se houver erro lógico no sorteador.

---

## 🖥️ Saída Exata Gerada no Console Durante a Demonstração (Imagem 3)

Se o usuário digitar `3` e forem sorteados os números `6`, `2` e `6`:

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

* **Matemática conferida:** $6 + 2 + 6 = 14$.
* **Visual conferido:** Cada dado teve sua arte ASCII desenhada individualmente antes da frase textual, mantendo o console bonito, limpo e perfeitamente legível!
