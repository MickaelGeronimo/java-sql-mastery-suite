# 🎲 Capítulo 30: Dice Roller Program (Simulador de Lançamento de Dados) — Parte 4: O Método `printDie` e a Arte ASCII com Text Blocks (`"""`)

---

## 📌 Contexto Geral e Visual das Imagens do Curso

> **Nota para leitura no Google Docs:** Este documento é **100% autossuficiente**. Todas as ações visuais do autor no IntelliJ IDEA, a abertura da ferramenta do Windows (*Character Map* / Mapa de Caracteres), os desenhos dos dados com caracteres Unicode e as estruturas de métodos estão detalhadamente descritas em prosa.

---

### 🖼️ O Que Está Acontecendo nas 5 Capturas de Tela do Vídeo

#### 1. Imagem 1 (`media_1791092617008.png`) — Timestamp `5:00:00 / 12:00:00`
* **Cenário:** O autor agora avança para a parte gráfica do programa: desenhar os dados na tela (`// DISPLAY ASCII OF DICE`).
* **Criação do Novo Método:**
  * Fora do método `main` (abaixo da chave de fechamento na Linha 32), o autor cria um método auxiliar dedicado:
    ```java
    static void printDie(int roll) {
    ```
  * Dentro desse método, ele declara uma variável do tipo texto usando **Text Blocks** (três aspas duplas `"""`):
    ```java
    String dice1 = """
             -------
            |       |
            |       |
            |       |
             -------
            """;
    ```
  * Ele desenhou a moldura vazia do dado com traços horizontais `-------` e barras verticais `|`.
  * **A busca pelo símbolo perfeito:** Para fazer a bolinha do dado, o autor abre a caixa de diálogo "Executar" do Windows (`Win + R`), digita `charmap` e pressiona Enter.

#### 2. Imagem 2 (`media_1791092628188.png`) — Timestamp `5:00:15 / 12:00:00`
* **Cenário:** A janela do utilitário nativo do Windows chamado **Character Map** (Mapa de Caracteres) é exibida na tela.
* **O que o autor faz:**
  * Na fonte Arial, ele localiza o caractere Unicode correspondente ao código **`U+25CF: Black Circle`** (um círculo sólido e perfeitamente preenchido: `●`).
  * Ele clica em **Select** e depois em **Copy** para copiar o caractere para a área de transferência do computador.
  * Essa dica ensina como obter símbolos visuais elegantes em programas de console sem precisar de bibliotecas externas!

#### 3. Imagem 3 (`media_1791092639183.png`) — Timestamp `5:00:44 / 12:00:00`
* **Cenário:** O autor volta ao IntelliJ IDEA e cola o círculo `●` no centro exato da moldura de `dice1`:
  ```java
  String dice1 = """
           -------
          |       |
          |   ●   |
          |       |
           -------
          """;
  ```
* Logo abaixo, na Linha 43, ele insere temporariamente `System.out.println(dice1);` para verificar se a formatação multilinhas sai perfeita no terminal.

#### 4. Imagem 4 e 5 (`media_1791092679292.png` e `media_1791092668142.png`) — Timestamps `5:01:26` e adiante
* **Cenário:** O autor constrói as representações textuais de todas as 6 faces dos dados:
  * **`dice4` (Linhas 60-65):** Quatro pontos nas extremidades:
    ```text
     -------
    | ●   ● |
    |       |
    | ●   ● |
     -------
    ```
  * **`dice5` (Linhas 67-73):** Quatro pontos nos cantos mais um ponto centralizado:
    ```text
     -------
    | ●   ● |
    |   ●   |
    | ●   ● |
     -------
    ```
  * **`dice6` (Linhas 75-81):** Seis pontos dispostos em duas colunas de três:
    ```text
     -------
    | ●   ● |
    | ●   ● |
    | ●   ● |
     -------
    ```

---

## 🧠 Conceitos Explicados de Forma Amadora e Descomplicada

### 1. O que é um Método Auxiliar (`static void printDie(int roll)`)?
Pense num método como um **funcionário especialista**.
Em vez de sobrecarregar o `main` com centenas de linhas de desenhos de dados misturados com contas matemáticas e leitura de teclado, nós criamos um ajudante:
* **`static`:** Significa que o ajudante pertence à classe `Main` e pode ser chamado diretamente do `main` sem precisarmos criar um objeto (`new Main()`).
* **`void`:** Significa que esse ajudante **não devolve nenhum valor numérico** (ele não calcula nada para devolver). O trabalho dele é apenas imprimir o desenho do dado na tela.
* **`(int roll)`:** É o **parâmetro** (a ordem de serviço). Quando o `main` chama `printDie(3)`, ele está dizendo ao ajudante: *"Tome aqui o número 3 e desenhe o dado correspondente na tela!"*.

---

### 2. O Que São os Text Blocks (`""" ... """`) do Java Moderno?
Antigamente, para imprimir um desenho de 5 linhas no Java, o programador precisava sofrer com quebras de linha manuais:
```java
// Jeito antigo e sofrido:
String dado = " -------\n|       |\n|   ●   |\n|       |\n -------\n";
```
Era horrível de enxergar e de alinhar os espaços!

A partir do **Java 15**, a linguagem introduziu os **Text Blocks** (Blocos de Texto). Basta abrir com três aspas duplas `"""` e fechar com três aspas duplas `"""`.
* Tudo o que você digitar no meio (espaços, quebras de linha com Enter, barras) é preservado exatamente como você desenhou!
* É o chamado **WYSIWYG** (*What You See Is What You Get* — O que você vê é o que você obtém).

---

### 3. A Galeria Completa dos 6 Dados em Arte ASCII

Aqui está o mapa completo das 6 faces que o programa utiliza:

```text
Face 1:          Face 2:          Face 3:
 -------          -------          -------
|       |        | ●     |        | ●     |
|   ●   |        |       |        |   ●   |
|       |        |     ● |        |     ● |
 -------          -------          -------

Face 4:          Face 5:          Face 6:
 -------          -------          -------
| ●   ● |        | ●   ● |        | ●   ● |
|       |        |   ●   |        | ●   ● |
| ●   ● |        | ●   ● |        | ●   ● |
 -------          -------          -------
```

Cada face possui:
* Uma borda superior e inferior: ` ------- ` (7 traços com espaço nas pontas)
* Paredes laterais: barras verticais `|`
* Um interior com 7 caracteres de largura onde os círculos `●` e os espaços em branco se organizam.

---

## 💻 Código-Fonte Completo e Compilável (Capítulo 30 — Parte 4)

Aqui está a classe completa com o método `printDie` implementado:

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
            default -> System.out.println("Invalid roll");
        }
    }
}
```

---

## 🔍 Varredura Linha por Linha Enumerada

Abaixo está a análise minuciosa de cada linha, o que acontece na memória RAM e o que sai na tela (console):

```
Linha 01: import java.util.Random;
Linha 02: import java.util.Scanner;
Linha 03: 
Linha 04: public class Main {
Linha 05: 
Linha 06:     public static void main(String[] args) {
...
Linha 23:             for (int i = 0; i < numOfDice; i++) {
Linha 24:                 int roll = random.nextInt(1, 7);
Linha 25:                 printDie(roll);
Linha 26:                 System.out.println("You rolled: " + roll);
Linha 27:                 total += roll;
Linha 28:             }
...
Linha 35:     static void printDie(int roll) {
Linha 36: 
Linha 37:         String dice1 = """
Linha 38:                  -------
Linha 39:                 |       |
Linha 40:                 |   ●   |
Linha 41:                 |       |
Linha 42:                  -------
Linha 43:                 """;
Linha 44: 
...
Linha 80:         switch (roll) {
Linha 81:             case 1 -> System.out.print(dice1);
Linha 82:             case 2 -> System.out.print(dice2);
Linha 83:             case 3 -> System.out.print(dice3);
Linha 84:             case 4 -> System.out.print(dice4);
Linha 85:             case 5 -> System.out.print(dice5);
Linha 86:             case 6 -> System.out.print(dice6);
Linha 87:             default -> System.out.println("Invalid roll");
Linha 88:         }
Linha 89:     }
Linha 90: }
```

### Explicação Detalhada:

* **Linha 25 (`printDie(roll);`):**
  * **O que faz:** O `main` chama o método auxiliar `printDie`, entregando o valor sorteado em `roll` (ex: `1`).
  * **Na memória:** A execução do `main` é pausada e uma nova moldura de pilha (*stack frame*) é criada para o método `printDie`.
  * **No console:** Nada direto na chamada; o desenho será impresso dentro do método.
* **Linha 35 (`static void printDie(int roll) {`):**
  * **O que faz:** Cabeçalho do método. Recebe a variável inteira `roll`.
  * **No console:** Nada.
* **Linhas 37 a 43 (`String dice1 = """ ... """;`):**
  * **O que faz:** Cria a representação multilinhas da face 1 do dado. A bolinha central `●` representa o número 1.
  * **Na memória:** Uma cadeia de texto contendo quebras de linha embutidas é alocada na memória.
  * **No console:** Nada nesta atribuição.
* **Linhas 45 a 79 (`String dice2` até `String dice6`):**
  * **O que faz:** Cria as matrizes de caracteres para as faces de 2 a 6.
  * **No console:** Nada.
* **Linhas 80 a 88 (`switch (roll) { ... }`):**
  * **O que faz:** Avalia o valor de `roll`. Se `roll == 1`, executa `System.out.print(dice1);`. Se `roll == 5`, executa `System.out.print(dice5);`, e assim por diante.
  * **No console:** Imprime o bloco de arte ASCII correspondente à face sorteada!
* **Linha 89 (`}`):**
  * **O que faz:** Fim da execução de `printDie`. O stack frame é desalocado e o controle retorna para a Linha 26 do `main`.
  * **No console:** Nada.

---

## 🖥️ Exemplo de Saída Real no Console (Lançando 2 Dados)

Se o usuário digitar `2` e os dados sorteados forem `1` e `5`:

```text
Enter the # of dice to roll: 2
 -------
|       |
|   ●   |
|       |
 -------
You rolled: 1
 -------
| ●   ● |
|   ●   |
| ●   ● |
 -------
You rolled: 5
Total: 6

Process finished with exit code 0
```

Tudo conectado perfeitamente: a arte gráfica do dado aparece primeiro, seguida do número sorteado, e a soma final de todos os pontos encerra o programa!
