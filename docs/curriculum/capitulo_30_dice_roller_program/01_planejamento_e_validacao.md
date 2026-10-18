# 🎲 Capítulo 30: Dice Roller Program (Simulador de Lançamento de Dados) — Parte 1: Estrutura, Planejamento e Validação

---

## 📌 Contexto Geral e Visual das Imagens do Curso

> **Nota para leitura no Google Docs:** Este documento foi formatado para ser **100% autossuficiente**. Todas as informações visuais, telas do IntelliJ IDEA, posições do cursor, textos do terminal e timestamps do vídeo do YouTube (*Java Full Course for free* do canal **Bro Code**) estão transcritas e descritas minuciosamente em texto.

---

### 🖼️ O Que Está Acontecendo nas 4 Capturas de Tela do Vídeo

#### 1. Imagem 1 (`media_1791092277141.png`) — Timestamp `4:51:30 / 12:00:00`
* **Título do Capítulo:** `30.dice roller program >`
* **Cenário:** O autor Bro Code abre o capítulo apresentando uma demonstração ao vivo do programa finalizado funcionando dentro do console do IntelliJ IDEA para animar o aluno.
* **O que aparece na tela do console:**
  * O programa pergunta: `Enter the number of dice to roll: ` e o autor digita `3`.
  * O console imprime: `Rolling 3 dice...`.
  * Em seguida, surge no console uma arte gráfica feita inteiramente com caracteres de texto (**ASCII Art**), desenhando a face de um dado real com bordas `-------`, laterais `|` e pontos circulares `•`:
    ```text
    -------
    | •   |
    |  •  |
    |   • |
    -------
    You rolled: 3
    ```
  * O dado com valor 3 é desenhado perfeitamente com os 3 pontos na diagonal! E logo abaixo o programa continua rolando os outros 2 dados e somando os resultados.

#### 2. Imagem 2 (`media_1791092310812.png`) — Timestamp `4:53:29 / 12:00:00`
* **Cenário:** O autor limpa a tela e começa a construir o projeto do zero na classe `Main`.
* **O que ele faz:**
  * Importa as duas classes essenciais do pacote `java.util`: `Random` (para sortear números de 1 a 6) e `Scanner` (para ler o que o usuário digita).
  * Declara as variáveis de trabalho: `scanner`, `random`, `numOfDice` (número de dados) e está digitando `int total;` (acumulador da soma).
  * **O "Roteiro em Comentários" (Pseudocódigo):** Antes de programar a lógica difícil, o autor escreve uma lista de passos em inglês usando comentários `//`:
    1. `// GET # OF DICE FROM THE USER` (Obter a quantidade de dados do usuário).
    2. `// CHECK IF # OF DICE > 0` (Verificar se a quantidade é maior que 0).
    3. `// ROLL ALL THE DICE` (Rolar todos os dados).
    4. `// GET THE TOTAL` (Calcular a soma total).
    5. `// DISPLAY ASCII OF DICE` (Exibir o desenho ASCII de cada dado).
  * No final, já coloca preventivamente `scanner.close();` para não esquecer de fechar o leitor.

#### 3. Imagem 3 (`media_1791092322105.png`) — Timestamp `4:54:48 / 12:00:00`
* **Cenário:** O autor substitui os dois primeiros comentários pelo código Java real de entrada e validação:
  * Exibe `System.out.print("Enter the # of dice to roll: ");` (repare que ele usa `print` sem o `ln`, deixando o cursor piscando na mesma linha para o usuário digitar).
  * Lê o número digitado com `numOfDice = scanner.nextInt();`.
  * Cria a estrutura condicional de proteção `if(numOfDice > 0)` com seu par `else`.
  * No `else`, já programa o aviso de erro: `System.out.println("# of dice must be greater than 0");`.
  * Dentro do `if`, ele está no meio da digitação de uma frase temporária de teste: `System.out.println("You ");`.

#### 4. Imagem 4 (`media_1791092335880.png`) — Timestamp `4:55:12 / 12:00:00`
* **Cenário:** O autor termina a frase de teste, ficando: `System.out.println("You roll the dice");` e aperta o botão verde **Run ▶** para testar imediatamente.
* **O que acontece no Console:**
  * O programa imprime: `Enter the # of dice to roll: `
  * O autor digita: `3` e aperta Enter.
  * O programa avalia que `3 > 0` é verdadeiro e executa a linha de teste dentro do `if`:
    ```text
    Enter the # of dice to roll: 3
    You roll the dice

    Process finished with exit code 0
    ```
  * O autor sorri e confirma: *"A fundação e a validação estão funcionando perfeitamente! Agora podemos construir a mecânica de sorteio e arte gráfica."*

---

## 🧠 Explicação Didática Para Iniciantes ("Do Zero")

Se você está começando em programação, construir um jogo como este parece mágica, mas na verdade é como montar um castelo de **Lego**, pecinha por pecinha:

### 1. Por que usar `Random` junto com `Scanner`?
* O **`Scanner`** é o "ouvido" do programa. Ele escuta o teclado do ser humano.
* O **`Random`** é o "copo de dados" do computador. Ele é um motor matemático embutido no Java capaz de gerar números imprevisíveis (pseudo-aleatórios). Para um dado de 6 faces, ele vai sortear números entre 1 e 6.

### 2. A Técnica de Ouro dos Programadores: *Pseudocódigo em Comentários*
Repare no que o Bro Code fez na Imagem 2. Ele **não começou tentando fazer tudo de uma vez**. Isso é o maior erro de quem está aprendendo (a cabeça frita e a pessoa trava!).
Ele escreveu 5 frases em inglês precedidas de `//`:
```java
// 1. Pegar quantos dados o jogador quer
// 2. Verificar se o número é válido (maior que zero)
// 3. Rolar os dados
// 4. Somar o total
// 5. Desenhar o dado na tela
```
Esses comentários funcionam como um **mapa de navegação**. O programador vai trocando cada comentário por 1 ou 2 linhas de código real.

### 3. Validação Preventiva (`if (numOfDice > 0)`)
O que acontece se um engraçadinho rodar o programa e pedir para jogar `-5` dados? Ou `0` dados?
Se o programa não checar isso, o laço de repetição (`for`) vai bugar ou o programa vai fechar do nada.
Por isso colocamos uma porta com segurança:
* Se `numOfDice > 0`: o segurança deixa entrar (`"You roll the dice"`).
* Senão (`else`): o segurança barra e avisa (`"# of dice must be greater than 0"`).

### 4. O Teste "Passo de Bebê" (*Incremental Development*)
Repare que na Imagem 4 o autor ainda **não desenhou nenhum dado**. Ele colocou apenas um simples `System.out.println("You roll the dice");`.
Por que ele fez isso?
Para ter **100% de certeza** de que a leitura do teclado e o `if` estão funcionando antes de colocar um código gigante e complicado lá dentro. Se der erro agora, ele sabe que o erro foi nessas 3 linhas, e não na lógica dos dados.

---

## 💻 Código-Fonte Completo e Compilável (Capítulo 30 — Parte 1)

Você pode copiar este código exatamente como está e colar no seu arquivo `Main.java` dentro do IntelliJ IDEA:

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
            System.out.println("You roll the dice");
        }
        else {
            System.out.println("# of dice must be greater than 0");
        }

        // ROLL ALL THE DICE
        // GET THE TOTAL
        // DISPLAY ASCII OF DICE

        scanner.close();
    }
}
```

---

## 🔍 Varredura Linha por Linha Enumerada (Linha 1 a Linha 31)

Aqui está a dissecação cirúrgica de cada linha de código, o que acontece na memória do computador e o que sai na tela preta (console):

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
Linha 21:             System.out.println("You roll the dice");
Linha 22:         }
Linha 23:         else {
Linha 24:             System.out.println("# of dice must be greater than 0");
Linha 25:         }
Linha 26: 
Linha 27:         // ROLL ALL THE DICE
Linha 28:         // GET THE TOTAL
Linha 29:         // DISPLAY ASCII OF DICE
Linha 30: 
Linha 31:         scanner.close();
Linha 32:     }
Linha 33: }
```

### Explicação Detalhada Linha por Linha:

* **Linha 01 (`import java.util.Random;`):**
  * **O que faz:** Importa a classe `Random` da biblioteca padrão utilitária do Java (`java.util`). Sem essa linha, o compilador não saberia como gerar números aleatórios.
  * **O que sai no console:** Nada. A importação acontece em silêncio antes do programa iniciar.
  * **Na memória:** O compilador vincula os métodos de geração numérica ao binário da classe `Main`.

* **Linha 02 (`import java.util.Scanner;`):**
  * **O que faz:** Importa a ferramenta de captura de dados do teclado (`Scanner`).
  * **O que sai no console:** Nada.
  * **Na memória:** Carrega a definição do leitor de fluxos de entrada (`InputStream`).

* **Linha 03 (Linha em branco):**
  * **O que faz:** Apenas organiza visualmente o código, separando as importações do corpo da classe.
  * **O que sai no console:** Nada.

* **Linha 04 (`public class Main {`):**
  * **O que faz:** Abre a declaração da nossa classe pública chamada `Main`. Todo código Java executável precisa viver dentro de uma classe.
  * **O que sai no console:** Nada.

* **Linha 05 (Linha em branco):**
  * **O que faz:** Organização visual.
  * **O que sai no console:** Nada.

* **Linha 06 (`public static void main(String[] args) {`):**
  * **O que faz:** O método principal (`main`), que é a porta de entrada oficial da Máquina Virtual Java (JVM). É daqui que o programa começa a rodar de verdade.
  * **O que sai no console:** Nada.

* **Linha 07 (Linha em branco):**
  * **O que faz:** Separa o cabeçalho do método das instruções internas.
  * **O que sai no console:** Nada.

* **Linha 08 (`// JAVA DICE ROLLER PROGRAM`):**
  * **O que faz:** Comentário de linha única identificando o título do projeto: *Programa de Lançador de Dados em Java*.
  * **O que sai no console:** Nada (comentários são 100% ignorados pela JVM).

* **Linha 09 (Linha em branco):**
  * **O que faz:** Organização visual.
  * **O que sai no console:** Nada.

* **Linha 10 (`Scanner scanner = new Scanner(System.in);`):**
  * **O que faz:** Cria (instancia) um novo objeto `Scanner` com o nome `scanner` e o conecta ao teclado do computador (`System.in`).
  * **Na memória:** Aloca um objeto na memória Heap e guarda a referência na variável local `scanner` na Stack.
  * **O que sai no console:** Nada visível ainda.

* **Linha 11 (`Random random = new Random();`):**
  * **O que faz:** Instancia o sorteador aleatório com o nome `random`. Esse objeto possui uma semente interna (geralmente baseada no relógio em milissegundos do sistema operacional) para produzir números pseudo-aleatórios verdadeiramente imprevisíveis para o usuário.
  * **Na memória:** Aloca o gerador `Random` na memória Heap.
  * **O que sai no console:** Nada.

* **Linha 12 (`int numOfDice;`):**
  * **O que faz:** Declara uma variável inteira primitiva chamada `numOfDice` (número de dados). Ela vai guardar a quantidade de dados que o usuário solicitar.
  * **Na memória:** Reserva 32 bits (4 bytes) na memória Stack para guardar um número inteiro.
  * **O que sai no console:** Nada.

* **Linha 13 (`int total = 0;`):**
  * **O que faz:** Declara uma variável inteira primitiva chamada `total` e já a inicia com valor `0`. Ela servirá como um "balde acumulador" que vai somar os pontos de cada dado sorteado.
  * **Na memória:** Reserva 4 bytes na Stack com o valor binário correspondente a `0`.
  * **O que sai no console:** Nada.

* **Linha 14 (Linha em branco):**
  * **O que faz:** Organização visual.
  * **O que sai no console:** Nada.

* **Linha 15 (`// GET # OF DICE FROM THE USER`):**
  * **O que faz:** Comentário lembrando o passo do roteiro: obter do usuário quantos dados ele quer rolar.
  * **O que sai no console:** Nada.

* **Linha 16 (`System.out.print("Enter the # of dice to roll: ");`):**
  * **O que faz:** Imprime a mensagem de solicitação na tela. **Atenção especial:** Usa `print` (sem `ln`), o que significa que o cursor NÃO pula para a linha de baixo. Ele fica piscando exatamente após o espaço depois dos dois pontos `: `.
  * **O que sai no console:**
    ```text
    Enter the # of dice to roll: 
    ```

* **Linha 17 (`numOfDice = scanner.nextInt();`):**
  * **O que faz:** O programa **congela** e fica aguardando o usuário digitar um número inteiro no teclado e pressionar a tecla `Enter`. Assim que o usuário dá `Enter`, o número digitado é lido e guardado dentro da variável `numOfDice`.
  * **Na memória:** A caixinha `numOfDice` recebe o número digitado (por exemplo, `3`).
  * **O que sai no console:** O número que o próprio usuário digitou aparece ao lado do texto:
    ```text
    Enter the # of dice to roll: 3
    ```

* **Linha 18 (Linha em branco):**
  * **O que faz:** Organização visual.
  * **O que sai no console:** Nada.

* **Linha 19 (`// CHECK IF # OF DICE > 0`):**
  * **O que faz:** Comentário de planejamento: verificar se o número de dados é estritamente maior que zero.
  * **O que sai no console:** Nada.

* **Linha 20 (`if (numOfDice > 0) {`):**
  * **O que faz:** Realiza um teste booleano (`true` ou `false`). Pergunta ao processador: *"O valor guardado em `numOfDice` é maior do que 0?"*. Se o usuário digitou `3`, a condição é `3 > 0` (Verdadeiro / `true`). O fluxo de execução então entra nas chaves `{ }` do `if`. Se o usuário digitou `0` ou `-2`, a condição é falsa e o Java pula direto para o `else`.
  * **O que sai no console:** Nada.

* **Linha 21 (`System.out.println("You roll the dice");`):**
  * **O que faz:** Mensagem temporária de validação colocada pelo autor para garantir que o bloco `if` foi acionado com sucesso. Usa `println`, portanto adiciona uma quebra de linha ao final.
  * **O que sai no console (caso `numOfDice > 0`):**
    ```text
    You roll the dice
    ```

* **Linha 22 (`}`):**
  * **O que faz:** Fecha o bloco do `if`. Se o código entrou aqui, ele ignora completamente o bloco `else` e pula para a linha 26.
  * **O que sai no console:** Nada.

* **Linha 23 (`else {`):**
  * **O que faz:** Marca o caminho alternativo que só será executado se a condição do `if` tiver falhado (ou seja, se `numOfDice <= 0`).
  * **O que sai no console:** Nada.

* **Linha 24 (`System.out.println("# of dice must be greater than 0");`):**
  * **O que faz:** Alerta de erro amigável para o usuário caso ele tenha digitado zero ou um número negativo. Tradução: *"O número de dados deve ser maior do que 0"*.
  * **O que sai no console (caso `numOfDice <= 0`):**
    ```text
    # of dice must be greater than 0
    ```

* **Linha 25 (`}`):**
  * **O que faz:** Fecha o bloco do `else`.
  * **O que sai no console:** Nada.

* **Linha 26 (Linha em branco):**
  * **O que faz:** Organização visual.
  * **O que sai no console:** Nada.

* **Linha 27 (`// ROLL ALL THE DICE`):**
  * **O que faz:** Comentário reservando o espaço mental para o laço de repetição (`for`) que irá rolar cada um dos dados sorteando os valores de 1 a 6.
  * **O que sai no console:** Nada.

* **Linha 28 (`// GET THE TOTAL`):**
  * **O que faz:** Comentário reservando o espaço mental para acumular os valores sorteados (`total += roll`).
  * **O que sai no console:** Nada.

* **Linha 29 (`// DISPLAY ASCII OF DICE`):**
  * **O que faz:** Comentário reservando o espaço mental para a chamada do método que vai desenhar na tela as caixinhas dos dados em arte ASCII.
  * **O que sai no console:** Nada.

* **Linha 30 (Linha em branco):**
  * **O que faz:** Organização visual.
  * **O que sai no console:** Nada.

* **Linha 31 (`scanner.close();`):**
  * **O que faz:** Fecha o objeto `Scanner`, liberando o canal de leitura do teclado do sistema operacional. Boa prática fundamental ensinada pelo Bro Code para evitar vazamentos de memória (*resource leak*).
  * **O que sai no console:** Nada.

* **Linha 32 (`}`):**
  * **O que faz:** Fecha o método `main`.
  * **O que sai no console:** Nada.

* **Linha 33 (`}`):**
  * **O que faz:** Fecha a classe `Main`. Fim completo da execução do programa.
  * **O que sai no console:** A mensagem padrão do IntelliJ informando encerramento sem erros:
    ```text
    Process finished with exit code 0
    ```

---

## 🖥️ Telas de Simulação de Execução no Console

### Cenário 1: O Teste Feito no Vídeo pelo Autor (Imagem 4)
* **Entrada do Usuário:** `3`
* **Comportamento:** `3 > 0` é verdadeiro! Entra no `if`.
* **Saída Exata no Console:**
```text
Enter the # of dice to roll: 3
You roll the dice

Process finished with exit code 0
```

### Cenário 2: Teste com Valor Inválido (Zero ou Negativo)
* **Entrada do Usuário:** `0`
* **Comportamento:** `0 > 0` é falso! Pula para o `else`.
* **Saída Exata no Console:**
```text
Enter the # of dice to roll: 0
# of dice must be greater than 0

Process finished with exit code 0
```

---

## 🔮 O Que Vem nas Próximas Cenas (Adiantamento das Etapas)

Nos próximos minutos do vídeo, o Bro Code vai substituir a mensagem de teste `"You roll the dice"` pela lógica real:
1. **O Laço `for`:** Um loop que roda de `i = 0` até `i < numOfDice` para simular o arremesso de cada dado um por um.
2. **O Sorteio com `random.nextInt(1, 7)`:** Em Java moderno, `random.nextInt(1, 7)` gera números no intervalo fechado-aberto `[1, 7)`, ou seja: `1, 2, 3, 4, 5, 6`.
3. **O Acúmulo:** `total += roll;` somando a pontuação de cada rodada.
4. **O Método de Arte ASCII:** Um método dedicado que recebe o número sorteado (ex: `1`, `2`, `3`, `4`, `5` ou `6`) e usa um `switch` para imprimir o desenho perfeito do dado com as bolinhas (`•`) na tela!
