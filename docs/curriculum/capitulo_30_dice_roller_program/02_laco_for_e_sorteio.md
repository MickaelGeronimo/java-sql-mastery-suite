# 🎲 Capítulo 30: Dice Roller Program (Simulador de Lançamento de Dados) — Parte 2: O Laço `for` e o Sorteio com `random.nextInt(1, 7)`

---

## 📌 Contexto Geral e Visual das Imagens do Curso

> **Nota para leitura no Google Docs:** Este documento é **100% autossuficiente**. Todas as informações visuais das telas do IntelliJ IDEA, do cursor de digitação, cliques do mouse, variáveis destacadas e saídas do console do vídeo do canal **Bro Code** (*Java Full Course for free*) estão integralmente descritas em texto.

---

### 🖼️ O Que Está Acontecendo nas 3 Capturas de Tela do Vídeo

#### 1. Imagem 1 (`media_1791092450783.png`) — Timestamp `4:56:36 / 12:00:00`
* **Cenário:** O autor Bro Code apaga a mensagem provisória `"You roll the dice"` de dentro do `if (numOfDice > 0)` e começa a construir a mecânica real de rolagem de dados.
* **O que ele digita:**
  * Ele abre um laço de repetição: `for(int i = 0; i < numOfDice; i++){`.
  * Cria a variável de sorteio: `int roll = random.nextInt(1, 7);`.
  * Na tela, a palavra `roll` está com uma caixa de destaque azul claro ao redor.
  * Na linha de baixo, o autor está no meio da digitação da mensagem: `System.out.println("You rolled: " + );`, com o cursor piscando logo após o sinal de mais `+ `, pronto para colocar a variável `roll`.

#### 2. Imagem 2 (`media_1791092459096.png`) — Timestamp `4:56:40 / 12:00:00`
* **Cenário:** O autor conclui a linha de impressão ficando: `System.out.println("You rolled: " + roll);`.
* **Ação do Autor:** O autor move a seta do mouse até o canto superior direito da janela do IntelliJ IDEA e clica no botão verde triangular de execução (**Current File ▶** / Run) para compilar e rodar a nova versão.

#### 3. Imagem 3 (`media_1791092466907.png`) — Timestamp `4:56:45 / 12:00:00`
* **Cenário:** O terminal inferior do IntelliJ é aberto e a execução acontece em tempo real diante dos olhos do aluno.
* **O que aparece no terminal:**
  * Pergunta inicial: `Enter the # of dice to roll: `
  * O autor digita: `4` e pressiona a tecla Enter.
  * O programa imediatamente dispara o laço `for` exatamente 4 vezes e imprime os números sorteados:
    ```text
    Enter the # of dice to roll: 4
    You rolled: 5
    You rolled: 5
    You rolled: 1
    You rolled: 2

    Process finished with exit code 0
    ```
  * O autor destaca com o cursor o número `1` na terceira rolagem para mostrar que cada volta gerou um número independente entre 1 e 6. O laço rodou perfeitamente 4 vezes porque o usuário pediu 4 dados!

---

## 🧠 Explicação Didática Para Iniciantes ("Do Zero Absoluto")

### 1. Por que usar um laço `for`?
Imagine que você tem 1 dado na mão. Se você quer rolar 4 dados, o que você faz?
Você joga o dado uma vez, vê o número, anota. Joga a segunda vez, anota. Joga a terceira vez, anota. Joga a quarta vez, anota.
No computador, em vez de escrevermos 4 vezes o mesmo código com `Ctrl + C` e `Ctrl + V`, nós usamos um **laço de repetição `for`**.
O `for` funciona como um **peão de fábrica**:
* `int i = 0;` $\rightarrow$ *"Pode começar a contagem do zero"*.
* `i < numOfDice;` $\rightarrow$ *"Enquanto a contagem for menor do que a quantidade que o jogador pediu, continue trabalhando"*.
* `i++` $\rightarrow$ *"A cada volta concluída, aumente 1 na contagem (`i = i + 1`)"*.

Se o usuário pediu `4` dados (`numOfDice = 4`):
1. Volta 0 (`i = 0`): Rola o dado 1
2. Volta 1 (`i = 1`): Rola o dado 2
3. Volta 2 (`i = 2`): Rola o dado 3
4. Volta 3 (`i = 3`): Rola o dado 4
5. Quando `i` vira 4: o teste `4 < 4` dá **falso**, e o laço para na hora! Total: **4 rolagens perfeitas**.

---

### 2. O Mistério do `random.nextInt(1, 7)` — Por que vai até 7 e não 6?
Esta é a dúvida número 1 de todo mundo que está começando em Java! Preste muita atenção:

No Java moderno, o método `random.nextInt(origem, limite)` trabalha com o que a matemática chama de **intervalo semiaberto**:
$$\text{Intervalo: } [\text{origem}, \text{limite})$$

* O primeiro número (`1`) é **inclusivo** $\rightarrow$ Ele **PODE** ser sorteado.
* O segundo número (`7`) é **exclusivo** $\rightarrow$ Ele **NUNCA** é sorteado. O sorteador para exatamente no número anterior (`7 - 1 = 6`).

Portanto:
* Menor número possível que pode sair: **`1`**
* Maior número possível que pode sair: **`6`**
* Números possíveis do sorteio: $\{1, 2, 3, 4, 5, 6\}$ (exatamente as 6 faces de um dado tradicional de tabuleiro!).

> 💡 **Nota histórica:** Em versões antigas do Java, os programadores precisavam escrever `random.nextInt(6) + 1`. Mas o método moderno `random.nextInt(1, 7)` é muito mais limpo e fácil de ler!

---

### 3. O Escopo da Variável `roll`
Veja a linha:
```java
int roll = random.nextInt(1, 7);
```
Essa variável `roll` foi criada **DENTRO** das chaves `{ }` do laço `for`.
Isso significa que ela tem **escopo local de bloco**:
* A cada nova volta do laço, uma caixinha `roll` novinha em folha nasce na memória Stack.
* Ela recebe o número sorteado (ex: `5`).
* Ela é impressa no console com `"You rolled: " + roll`.
* Quando a volta do laço atinge a chave de fechamento `}`, essa caixinha `roll` é destruída da memória. Na próxima volta, nasce outra caixinha `roll` com o próximo sorteio.

---

## 💻 Código-Fonte Completo e Compilável (Capítulo 30 — Parte 2)

Copie e cole este código diretamente no seu arquivo `Main.java` no IntelliJ IDEA:

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
                System.out.println("You rolled: " + roll);
            }

        }
        else {
            System.out.println("# of dice must be greater than 0");
        }

        // GET THE TOTAL
        // DISPLAY ASCII OF DICE

        scanner.close();
    }
}
```

---

## 🔍 Varredura Linha por Linha Enumerada (Linha 1 a Linha 37)

Abaixo está o raio-X completo de cada linha do programa com o impacto na memória RAM e o que sai na tela preta (console):

```
Linha 01: import java.util.Random;
Linha 02: import java.util.Scanner;
Linha 03: 
Linha 04: public class Main {
Linha 05: 
Linha 06:     public static void main(String[] args) {
Linha 07: 
Linha 08:         // JAVA DICE ROLLER PROGRAM
Linha 09: 
Linha 10:         Scanner scanner = new Scanner(System.in);
Linha 11:         Random random = new Random();
Linha 12:         int numOfDice;
Linha 13:         int total = 0;
Linha 14: 
Linha 15:         // GET # OF DICE FROM THE USER
Linha 16:         System.out.print("Enter the # of dice to roll: ");
Linha 17:         numOfDice = scanner.nextInt();
Linha 18: 
Linha 19:         // CHECK IF # OF DICE > 0
Linha 20:         if (numOfDice > 0) {
Linha 21: 
Linha 22:             // ROLL ALL THE DICE
Linha 23:             for (int i = 0; i < numOfDice; i++) {
Linha 24:                 int roll = random.nextInt(1, 7);
Linha 25:                 System.out.println("You rolled: " + roll);
Linha 26:             }
Linha 27: 
Linha 28:         }
Linha 29:         else {
Linha 30:             System.out.println("# of dice must be greater than 0");
Linha 31:         }
Linha 32: 
Linha 33:         // GET THE TOTAL
Linha 34:         // DISPLAY ASCII OF DICE
Linha 35: 
Linha 36:         scanner.close();
Linha 37:     }
Linha 38: }
```

### Explicação Detalhada Linha por Linha:

* **Linha 01 (`import java.util.Random;`):** Importa o gerador de números aleatórios da biblioteca padrão do Java.
  * **No console:** Nada.
* **Linha 02 (`import java.util.Scanner;`):** Importa o leitor de teclado.
  * **No console:** Nada.
* **Linha 03 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 04 (`public class Main {`):** Abre o bloco da classe pública `Main`.
  * **No console:** Nada.
* **Linha 05 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 06 (`public static void main(String[] args) {`):** O método principal onde a execução do programa se inicia.
  * **No console:** Nada.
* **Linha 07 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 08 (`// JAVA DICE ROLLER PROGRAM`):** Comentário com o título do projeto.
  * **No console:** Nada.
* **Linha 09 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 10 (`Scanner scanner = new Scanner(System.in);`):** Cria o objeto leitor conectado à entrada padrão do teclado.
  * **Na memória:** Aloca o leitor na memória Heap e guarda a referência na Stack.
  * **No console:** Nada.
* **Linha 11 (`Random random = new Random();`):** Cria o sorteador aleatório calibrado com o relógio do sistema.
  * **Na memória:** Aloca a instância de `Random` na Heap.
  * **No console:** Nada.
* **Linha 12 (`int numOfDice;`):** Declara a variável inteira que guardará a quantidade de dados.
  * **Na memória:** Reserva espaço de 4 bytes na Stack.
  * **No console:** Nada.
* **Linha 13 (`int total = 0;`):** Declara e zera o acumulador da soma final.
  * **Na memória:** Guarda o valor `0` na Stack.
  * **No console:** Nada.
* **Linha 14 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 15 (`// GET # OF DICE FROM THE USER`):** Comentário do passo 1.
  * **No console:** Nada.
* **Linha 16 (`System.out.print("Enter the # of dice to roll: ");`):** Imprime o texto pedindo a quantidade. Por usar `print`, o cursor não quebra linha e fica parado aguardando a digitação.
  * **No console:**
    ```text
    Enter the # of dice to roll: 
    ```
* **Linha 17 (`numOfDice = scanner.nextInt();`):** O programa pausa a execução até o usuário digitar o número e teclar `Enter`. O valor digitado é atribuído a `numOfDice`.
  * **Na memória:** Se o usuário digitou `4`, a variável `numOfDice` passa a valer `4`.
  * **No console:** O valor digitado aparece logo após os dois pontos (`4`).
* **Linha 18 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 19 (`// CHECK IF # OF DICE > 0`):** Comentário do passo 2.
  * **No console:** Nada.
* **Linha 20 (`if (numOfDice > 0) {`):** Avalia se `numOfDice` é estritamente maior que zero. Com `numOfDice = 4`, a expressão `4 > 0` resulta em `true`. A execução adentra as chaves do `if`.
  * **No console:** Nada.
* **Linha 21 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 22 (`// ROLL ALL THE DICE`):** Comentário do passo 3 (agora implementado!).
  * **No console:** Nada.
* **Linha 23 (`for (int i = 0; i < numOfDice; i++) {`):** 
  * **O que faz:** Inicializa o contador `i = 0`. Enquanto `i < numOfDice` (ou seja, `i < 4`), executa o bloco interno. Ao final de cada volta, incrementa `i` em 1 (`i++`).
  * **Na memória:** Cria a variável de iteração `i` na Stack.
  * **No console:** Nada direto nesta linha.
* **Linha 24 (`int roll = random.nextInt(1, 7);`):**
  * **O que faz:** Aciona o método `nextInt(1, 7)` do objeto `random`. Sorteia um número inteiro aleatório entre 1 (inclusive) e 7 (exclusive), ou seja, $\{1, 2, 3, 4, 5, 6\}$. Armazena o resultado na variável local `roll`.
  * **Na memória:** A variável `roll` recebe o valor sorteado (por exemplo, `5`).
  * **No console:** Nada.
* **Linha 25 (`System.out.println("You rolled: " + roll);`):**
  * **O que faz:** Concatena o texto fixo `"You rolled: "` com o valor numérico de `roll` e imprime na tela com uma quebra de linha (`println`).
  * **No console (na primeira volta com `roll = 5`):**
    ```text
    You rolled: 5
    ```
* **Linha 26 (`}`):**
  * **O que faz:** Fim de uma volta do laço `for`. A variável `roll` é destruída da memória. O Java executa `i++`, incrementando o contador, e retorna à Linha 23 para testar a condição `i < numOfDice`.
  * **No console:** Nada.
* **Linha 27 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 28 (`}`):**
  * **O que faz:** Fecha o bloco do `if`. Como o programa entrou no `if`, ele salta por cima do bloco `else` e segue diretamente para a Linha 32.
  * **No console:** Nada.
* **Linha 29 (`else {`):**
  * **O que faz:** Bloco alternativo para entradas inválidas (`numOfDice <= 0`). É ignorado se a entrada foi válida.
  * **No console:** Nada.
* **Linha 30 (`System.out.println("# of dice must be greater than 0");`):**
  * **O que faz:** Mensagem de erro que só é disparada se o usuário digitar 0 ou negativo.
  * **No console:** Nada (neste teste com 4).
* **Linha 31 (`}`):**
  * **O que faz:** Fecha o bloco do `else`.
  * **No console:** Nada.
* **Linha 32 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 33 (`// GET THE TOTAL`):** Comentário reservado para a próxima etapa (acumular a soma).
  * **No console:** Nada.
* **Linha 34 (`// DISPLAY ASCII OF DICE`):** Comentário reservado para a etapa gráfica (desenhar os dados).
  * **No console:** Nada.
* **Linha 35 (Linha em branco):** Separação estética.
  * **No console:** Nada.
* **Linha 36 (`scanner.close();`):** Encerra o leitor `Scanner`, liberando os recursos do teclado no sistema operacional.
  * **No console:** Nada.
* **Linha 37 (`}`):** Fecha o método `main`.
  * **No console:** Nada.
* **Linha 38 (`}`):** Fecha a classe `Main`. Fim da execução do programa.
  * **No console:**
    ```text
    Process finished with exit code 0
    ```

---

## 📊 Tabela de Rastreio Passo a Passo (O Teste do Vídeo com 4 Dados)

Aqui está exatamente o que a CPU e a memória fizeram para produzir a saída da Imagem 3:

| Passo | Contador `i` | Teste `i < 4`? | `random.nextInt(1, 7)` | Valor em `roll` | O Que Sai no Console | Ação Seguinte |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **Início** | — | — | — | — | `Enter the # of dice to roll: 4` | Usuário digita 4 |
| **Volta 1** | `0` | `0 < 4`? **Sim** | Sorteia entre 1 e 6 | **`5`** | `You rolled: 5` | Faz `i++` (`i` vira 1) |
| **Volta 2** | `1` | `1 < 4`? **Sim** | Sorteia entre 1 e 6 | **`5`** | `You rolled: 5` | Faz `i++` (`i` vira 2) |
| **Volta 3** | `2` | `2 < 4`? **Sim** | Sorteia entre 1 e 6 | **`1`** | `You rolled: 1` | Faz `i++` (`i` vira 3) |
| **Volta 4** | `3` | `3 < 4`? **Sim** | Sorteia entre 1 e 6 | **`2`** | `You rolled: 2` | Faz `i++` (`i` vira 4) |
| **Fim** | `4` | `4 < 4`? **Não! (Falso)** | — | — | *(O laço encerra)* | Pula para fora do `for` |

---

## 🖥️ Saída Exata Gerada no Console

```text
Enter the # of dice to roll: 4
You rolled: 5
You rolled: 5
You rolled: 1
You rolled: 2

Process finished with exit code 0
```

> **Atenção:** Como o sorteio é aleatório, da próxima vez que você rodar o programa com o número 4, os números sorteados serão diferentes (ex: 6, 3, 2, 4). Isso prova que o sorteador do Java está funcionando de verdade!

---

## 🔮 O Que Falta Agora?

O autor concluiu com sucesso a rolagem (`// ROLL ALL THE DICE`). As duas etapas finais serão:
1. **Calcular a Soma Total (`// GET THE TOTAL`):** Acumular cada `roll` fazendo `total += roll;` e exibir `Total: X` no final.
2. **Arte ASCII dos Dados (`// DISPLAY ASCII OF DICE`):** Criar um método que desenha as faces reais do dado com as bolinhas (`•`) para substituir ou complementar o texto `"You rolled: X"`.
