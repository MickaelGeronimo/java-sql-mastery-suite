# ➿ Capítulo 25: O Guia Definitivo e Completo de Laços Aninhados em Java (Nested Loops) — Partes 1 a 5

> **Documento Oficial de Estudos para Cópia no Google Docs:** Este material foi construído para ser **100% autossuficiente**. Ele reconstitui todas as imagens, telas do IntelliJ IDEA e explicações do professor **Bro Code** (*Java Full Course for free*), linha por linha, com linguagem simples e acessível para iniciantes, analogias do cotidiano, tabelas de rastreamento de memória e códigos Java completos e compiláveis sem erros.

---

## 📑 Índice da Apostila
1. [Visão Geral: O Que É um Laço Aninhado (`nested loop`)?](#1-visão-geral)
2. [Contexto Visual Cronológico de Todas as Telas e Vídeos (Partes 1 a 5)](#2-contexto-visual)
3. [As 4 Metáforas Essenciais para Iniciantes Amadores](#3-metáforas-essenciais)
4. [Parte 1: Impressão Vertical vs. Horizontal e o Erro do "Copiar e Colar"](#4-parte-1-vertical-vs-horizontal)
5. [Parte 2: O Erro Vermelho `variable i is already defined` e Escopo de Variáveis](#5-parte-2-erro-variavel-duplicada)
6. [Parte 3: A Solução com `j` e o Segredo do `System.out.println()` Vazio (Tecla Enter)](#6-parte-3-solucao-j-e-println)
7. [Parte 4: Projeto do Gerador de Matriz — Por Que Não Existe `scanner.nextChar()`?](#7-parte-4-scanner-e-charat)
8. [Parte 5: O Gerador de Matriz Completo, Funcional e Dinâmico](#8-parte-5-matriz-completa)
9. [Tabela de Rastreamento da Execução em Câmera Lenta](#9-tabela-rastreamento)
10. [Armadilhas Comuns e Erros Frequentes de Iniciantes](#10-armadilhas-comuns)
11. [Códigos-Fonte Completos e 100% Compiláveis](#11-códigos-completos)

---

<a name="1-visão-geral"></a>
## ➿ 1. Visão Geral: O Que É um Laço Aninhado (`nested loop`)?

O capítulo 25 recebe o título com o emoji de laço duplo: **`nested loops ➿`**.

* **Em inglês:** A palavra *nest* significa "ninho". O verbo *to nest* significa "aninhar", colocar uma coisa abrigada dentro da outra.
* **Em programação Java:**
  > **Definição:** `nested loop = A loop inside another loop`  
  > *(Laço aninhado = Um laço de repetição inteiro colocado DENTRO de outro laço de repetição).*

### Onde isso é usado no mundo real do software?
Como o próprio comentário do professor destaca:
`// Used often with matrices or DS&A`
1. **Matrizes e Grades 2D:** Tabuleiros de xadrez, damas, batalha naval, pixels em telas de videogame, planilhas do Excel (linhas e colunas).
2. **Estruturas de Dados e Algoritmos (DS&A):** Algoritmos de ordenação (como *Bubble Sort*, *Selection Sort*), busca em grafos, inteligência artificial e processamento de imagens.

---

<a name="2-contexto-visual"></a>
## 🖼️ 2. Contexto Visual Cronológico de Todas as Telas (Partes 1 a 5)

Caso esteja lendo este material impresso ou no Google Docs sem acesso imediato aos vídeos, veja o resumo exato de tudo o que ocorreu na tela do professor ao longo dos minutos `3:55:48` até `4:04:05`:

1. **Abertura e Impressão Vertical (Minuto 3:55:48 - 3:56:40):**
   * O professor cria a classe `Main` com o método `main`.
   * Escreve um laço `for (int i = 1; i <= 9; i++)` usando `System.out.println(i);`.
   * O console imprime os números de 1 a 9 na **vertical** (um embaixo do outro).
2. **Mudança para Horizontal (Minuto 3:57:01):**
   * O professor substitui `println` por `System.out.print(i + " ");`.
   * Ao rodar, os números saem todos na **horizontal**: `1 2 3 4 5 6 7 8 9 `.
3. **A Tentativa Ruim de Copiar e Colar (Minuto 3:57:15):**
   * Para fazer 3 linhas, ele copia e cola o mesmo laço `for` três vezes seguidas.
   * O código fica poluído, feio e todos os números saem colados em uma linha gigantesca.
4. **O Erro Vermelho de Compilação (Minuto 3:57:31 - 3:58:23):**
   * Ele tenta colocar um laço dentro do outro, usando `i` em ambos:
     `for(int i = 1; i <= 3; i++) { for(int i = 1; i <= 9; i++) ... }`
   * O IntelliJ IDEA grifa o segundo `i` com uma linha ondulada vermelha.
   * Ao clicar no Play verde, a compilação é abortada com o erro:  
     `java: variable i is already defined in method main(java.lang.String[])`.
5. **A Correção Universal com `j` (Minuto 3:58:41 - 3:59:00):**
   * Ele renomeia o laço interno em **4 lugares** para a letra `j`.
   * Adiciona `System.out.println();` no final de cada volta de `i`.
   * O console exibe uma **matriz 3x9 perfeita** com 27 números organizados em 3 fileiras.
   * Em seguida, faz o teste de apagar o `println();` e mostra que os 27 números viram uma "cobra" contínua na horizontal.
6. **Início do Projeto Interativo com `Scanner` (Minuto 3:59:33 - 4:02:18):**
   * Demonstra o objetivo: pedir ao usuário a quantidade de linhas (`rows`), colunas (`columns`) e o caractere (`symbol`).
   * Ensina por que não existe `scanner.nextChar()` e apresenta a solução elegante: `scanner.next().charAt(0)`.
   * Constrói e testa primeiro apenas o laço interno das colunas para imprimir uma única linha de cifrões `$$$$$$$$`.
7. **Montagem Final do Gerador de Matriz (Minuto 4:02:40 - 4:04:05):**
   * Encapsula o laço das colunas dentro do laço das linhas.
   * Testa primeiro sem o `println()` vazio usando `@` (resultando em 18 arrobas coladas).
   * Insere o `println()` vazio e testa com `rows = 5`, `columns = 3` e `symbol = '&'`.
   * O console imprime um retângulo perfeito de 5 linhas com 3 e-comerciais cada. O capítulo termina com sucesso.

---

<a name="3-metáforas-essenciais"></a>
## 🧠 3. As 4 Metáforas Essenciais para Iniciantes Amadores

Se laços aninhados parecem confusos à primeira vista, use estas 4 comparações do mundo real:

### 1. A Boneca Russa (Matryoshka)
Você tem uma boneca grande de madeira. Ao abri-la, encontra uma boneca menor dentro. O laço de fora é a boneca grande; o laço de dentro é a boneca menor. Você só consegue fechar a boneca grande depois que a pequena estiver completamente encaixada e terminada!

### 2. O Relógio de Parede (Horas e Minutos)
Pense no mostrador de um relógio:
* O ponteiro dos **minutos** precisa dar **60 voltas completas** para que o ponteiro das **horas** ande apenas **1 único passo**!
* O laço de fora (*outer loop*) é a **Hora**.
* O laço de dentro (*inner loop*) são os **Minutos**.
* Enquanto o laço de dentro não terminar todas as suas voltas, o laço de fora não dá o próximo passo!

### 3. A Máquina de Escrever Antiga (Escrever Letras vs. O "Carriage Return")
Numa máquina de escrever:
* Você digita tecla por tecla lado a lado (isso é o `System.out.print` no laço interno).
* Quando chega no final da folha, você empurra a alavanca lateral (*plim!*), que roda o rolo e faz o papel subir uma linha (isso é o `System.out.println()` vazio no laço externo).

```text
Laço Interno (j): Digita coluna por coluna: [A] [B] [C] [D]
                                                       │
Alavanca Enter (println): Pula para a linha seguinte ◄─┘
Laço Externo (i): Avança para a próxima linha da folha.
```

### 4. A Prensa Automática da Fábrica
* **Laço de fora (`i`):** Desce uma nova folha de papel na esteira.
* **Laço de dentro (`j`):** Um carimbo mecânico bate $N$ vezes imprimindo o símbolo escolhido.
* **Tecla Enter:** Despacha a folha pronta e prepara a esteira para a próxima.

---

<a name="4-parte-1-vertical-vs-horizontal"></a>
## 💻 4. Parte 1: Impressão Vertical vs. Horizontal e o Erro do "Copiar e Colar"

### O Primeiro Laço Simples (Vertical):
```java
for (int i = 1; i <= 9; i++) {
    System.out.println(i);
}
```
* **O que acontece:** O comando `println` tem as letras `ln` no final, que significam **Line** (nova linha). Toda vez que ele imprime um número, ele obriga o cursor a descer para a linha seguinte.
* **Console:**
  ```text
  1
  2
  3
  ...
  9
  ```

### A Mudança para Horizontal:
Para desenhar tabelas e matrizes, nós precisamos que os números fiquem lado a lado na mesma linha! Para isso, usamos `print` comum (sem o `ln`):
```java
for (int i = 1; i <= 9; i++) {
    System.out.print(i + " ");
}
```
* **Console:**
  ```text
  1 2 3 4 5 6 7 8 9 
  ```

### O Jeito Errado/Amador: Copiar e Colar 3 Vezes
Para fazer 3 fileiras, o professor propositalmente mostra o que um iniciante tentaria:
```java
for (int i = 1; i <= 9; i++) { System.out.print(i + " "); }
for (int i = 1; i <= 9; i++) { System.out.print(i + " "); }
for (int i = 1; i <= 9; i++) { System.out.print(i + " "); }
```

### Por que isso é considerado péssima prática?
1. **Viola o Princípio DRY (*Don't Repeat Yourself*):** Se o mesmo bloco de código está repetido 3 vezes, ele foi mal projetado.
2. **Incapacidade de Escala:** Se o cliente pedir 500 linhas, você colaria 500 vezes? Impossível.
3. **Falta de Quebra de Linha:** Como não há quebra de linha entre os blocos, os 27 números saem emendados uns nos outros.

---

<a name="5-parte-2-erro-variavel-duplicada"></a>
## 🚫 5. Parte 2: O Erro Vermelho `variable i is already defined` e Escopo de Variáveis

O professor tenta aninhar os laços, mas comete um erro proposital clássico de todo iniciante:

```java
for (int i = 1; i <= 3; i++) {
    for (int i = 1; i <= 9; i++) { // ❌ ERRO DE COMPILAÇÃO AQUI!
        System.out.print(i + " ");
    }
    System.out.println();
}
```

Ao compilar, o Java bloqueia a execução e devolve:
```text
java: variable i is already defined in method main(java.lang.String[])
```

### Por que esse erro acontece? A Analogia dos Dois Irmãos
Imagine dois irmãos morando na mesma casa, e os pais deram o exato mesmo nome para ambos: **`i`**.  
Quando a mãe grita: *"Ô `i`, lave a louça!"*, ninguém sabe qual dos dois deve atender! Cria-se um conflito insolúvel.

Em Java, isso se chama **Conflito de Escopo Local**:
1. O laço externo abre sua chave `{` e declara: `int i = 1;`.
2. Enquanto essa chave não fechar, a variável `i` está viva e dona daquele território na memória.
3. O laço interno abre sua chave e tenta criar outra variável com o mesmo nome: `int i = 1;`.
4. O compilador Java se recusa a compilar porque não sabe a qual `i` você estará se referindo nas instruções seguintes.

```text
[ ESCOPO DO LAÇO EXTERNO ]
int i = 1;  <──────── Variável 'i' foi criada e está ativa
  │
  ├─── [ ESCOPO DO LAÇO INTERNO ]
  │    int i = 1;  <── ❌ CONFLITO! 'i' já existe aqui dentro!
```

### A Convenção Universal da Computação: `i`, `j`, `k`
Desde os anos 1950 (com a linguagem Fortran), matemáticos e programadores adotaram uma regra universal:
* **1º Laço (Linhas):** Usa a letra **`i`** (de *Index* / Índice).
* **2º Laço (Colunas):** Usa a letra seguinte do alfabeto: **`j`**.
* **3º Laço (Profundidade / 3D):** Usa a letra seguinte: **`k`**.

---

<a name="6-parte-3-solucao-j-e-println"></a>
## 🛠️ 6. Parte 3: A Solução com `j` e o Segredo do `System.out.println()` Vazio

Para corrigir o código, o laço interno precisa trocar a variável para `j`.

### ⚠️ Atenção aos 4 Lugares Críticos:
Mudar apenas a declaração não resolve. No laço interno, **4 lugares** precisam usar `j`:
1. **Declaração:** `int j = 1;`
2. **Condição:** `j <= 9;`
3. **Incremento:** `j++`
4. **Impressão:** `System.out.print(j + " ");`

Se você esquecer o `j++` e escrever `i++` por engano, você cria um **laço infinito**, pois o `j` nunca aumentará e o laço nunca chegará a 9!

### O Código Perfeito da Grade 3x9:
```java
public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(j + " ");
            }
            System.out.println(); // Pula para a próxima linha
        }

    }
}
```

### 📺 Console:
```text
1 2 3 4 5 6 7 8 9 
1 2 3 4 5 6 7 8 9 
1 2 3 4 5 6 7 8 9 
```

### 😱 O Experimento do "Sem Enter": O que acontece se apagar a linha vazia?
Se removermos a linha `System.out.println();`:
```java
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 9; j++) {
        System.out.print(j + " ");
    }
}
```
Como o `print` nunca quebra a linha, quando o `j` termina o 9 na primeira rodada, o `j` da segunda rodada recomeça imediatamente colado no mesmo local!
O resultado no console vira uma tripa horizontal:
```text
1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 
```
> **Conclusão:** O `System.out.println();` posicionado estrategicamente **entre o fim do laço interno e o fim do laço externo** funciona exatamente como a tecla **Enter** do seu teclado ao final de cada linha digitada.

---

<a name="7-parte-4-scanner-e-charat"></a>
## ⌨️ 7. Parte 4: Projeto do Gerador de Matriz — Por Que Não Existe `scanner.nextChar()`?

Na Parte 4, o professor transforma a grade fixa em um programa interativo onde o usuário digita:
* Quantas linhas quer (`rows`).
* Quantas colunas quer (`columns`).
* Qual caractere ou símbolo deseja usar (`symbol`).

### O Enigma de Entrada de Caracteres no Java:
Ao usar a classe `Scanner`, temos métodos prontos para quase todos os tipos primitivos:
* Para inteiros: `scanner.nextInt()`
* Para números com vírgula: `scanner.nextDouble()`
* Para booleanos: `scanner.nextBoolean()`
* Para textos: `scanner.nextLine()` ou `scanner.next()`

Porém, se você tentar digitar `scanner.nextChar()`, o código **não compila**. Os criadores da biblioteca padrão do Java não incluíram esse método.

### A Solução dos Engenheiros Java:
Para contornar isso, usamos uma composição em duas etapas:
```java
symbol = scanner.next().charAt(0);
```

Vamos dissecar essa linha:
1. `scanner.next()`: Lê a entrada do usuário como uma palavra de texto (`String`). Se o usuário digitou `@`, o Java lê como a String `"@"`. Se digitou `#`, lê `"#"`. Se digitou `gato`, lê `"gato"`.
2. `.charAt(0)`: O método `charAt(index)` extrai um único caractere primitivo (`char`) de dentro da String na posição numérica solicitada.
   * Na computação, a contagem sempre começa no **zero**.
   * O índice `0` representa **a primeiríssima letra** da palavra digitada.
   * Se o usuário digitou `*`, o índice 0 é `'*'`.
   * Se o usuário digitou por engano `estrela`, o índice 0 pega a letra `'e'`.

```text
Texto lido por scanner.next():  " $ "
Índice de posição na memória:     0
                                  │
.charAt(0) pinça o caractere:    '$'  (guardado na variável char symbol)
```

---

<a name="8-parte-5-matriz-completa"></a>
## 🚀 8. Parte 5: O Gerador de Matriz Completo, Funcional e Dinâmico

Unindo todos os conhecimentos adquiridos, montamos o algoritmo definitivo da matriz dinâmica.

### Por que começamos em `0` e usamos `<`?
Repare na construção do laço:
```java
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; j++) {
        System.out.print(symbol);
    }
    System.out.println();
}
```
* **Contagem baseada em 1:** `for(int i = 1; i <= rows; i++)` $\rightarrow$ Roda exatamente `rows` vezes.
* **Contagem baseada em 0 (Padrão Profissional):** `for(int i = 0; i < rows; i++)` $\rightarrow$ Também roda exatamente `rows` vezes!
  * Exemplo se `rows = 3`: os valores de `i` serão `0, 1, 2` (total de 3 voltas). Quando `i` atinge `3`, a condição `3 < 3` é falsa e o laço para.
  * Programadores Java preferem essa forma porque todos os arrays, matrizes e índices de coleções em Java começam no índice 0.

### Código Completo do Gerador:
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // nested loop = A loop inside another loop
        //               Used often with matrices or DS&A

        Scanner scanner = new Scanner(System.in);

        int rows;
        int columns;
        char symbol;

        System.out.print("Enter the # of rows: ");
        rows = scanner.nextInt();

        System.out.print("Enter the # of columns: ");
        columns = scanner.nextInt();

        System.out.print("Enter the symbol to use: ");
        symbol = scanner.next().charAt(0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }

        scanner.close();
    }
}
```

---

<a name="9-tabela-rastreamento"></a>
## 🔍 9. Tabela de Rastreamento da Execução em Câmera Lenta

Para compreender 100% como a CPU executa cada passo, imagine que o usuário digitou:
* `rows = 3` (3 linhas)
* `columns = 4` (4 colunas)
* `symbol = '#'` (hashtag)

Total de símbolos impressos: $3 \times 4 = 12$ hashtags. Veja o rastreamento passo a passo:

| Volta Externa (`i`) | Condição `i < 3` | Volta Interna (`j`) | Condição `j < 4` | Ação Executada | Saída Acumulada na Linha Atual |
| :---: | :---: | :---: | :---: | :--- | :--- |
| **0** (Linha 1) | `0 < 3` (True) | **0** | `0 < 4` (True) | `print('#')` | `#` |
| 0 | True | **1** | `1 < 4` (True) | `print('#')` | `##` |
| 0 | True | **2** | `2 < 4` (True) | `print('#')` | `###` |
| 0 | True | **3** | `3 < 4` (True) | `print('#')` | `####` |
| 0 | True | **4** | `4 < 4` (False) | Fim do laço `j`. Executa `println()`! | Cursor pula para a Linha 2 |
| **1** (Linha 2) | `1 < 3` (True) | **0** (Reinicia) | `0 < 4` (True) | `print('#')` | `#` |
| 1 | True | **1** | `1 < 4` (True) | `print('#')` | `##` |
| 1 | True | **2** | `2 < 4` (True) | `print('#')` | `###` |
| 1 | True | **3** | `3 < 4` (True) | `print('#')` | `####` |
| 1 | True | **4** | `4 < 4` (False) | Fim do laço `j`. Executa `println()`! | Cursor pula para a Linha 3 |
| **2** (Linha 3) | `2 < 3` (True) | **0** (Reinicia) | `0 < 4` (True) | `print('#')` | `#` |
| 2 | True | **1** | `1 < 4` (True) | `print('#')` | `##` |
| 2 | True | **2** | `2 < 4` (True) | `print('#')` | `###` |
| 2 | True | **3** | `3 < 4` (True) | `print('#')` | `####` |
| 2 | True | **4** | `4 < 4` (False) | Fim do laço `j`. Executa `println()`! | Cursor pula para a Linha 4 |
| **3** | `3 < 3` (False) | — | — | **Fim do laço `i`!** O programa sai dos laços. | Execução concluída com sucesso! |

### 📺 Resultado Final Exato no Console:
```text
Enter the # of rows: 3
Enter the # of columns: 4
Enter the symbol to use: #
####
####
####

Process finished with exit code 0
```

---

<a name="10-armadilhas-comuns"></a>
## ⚠️ 10. Armadilhas Comuns e Erros Frequentes de Iniciantes

Ao programar laços aninhados, fique muito atento aos seguintes erros típicos:

### 1. O Erro da Variável Cruzada (Laço Esquizofrênico)
```java
// ❌ CUIDADO: Laço interno incrementando a variável errada!
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < columns; i++) { // Olhe o 'i++' aqui!
        System.out.print("*");
    }
}
```
* **O que acontece:** O laço testa `j < columns`, mas quem é incrementado é o `i`! Como `j` continua valendo 0 para sempre, a condição nunca se torna falsa. Cria-se um **laço infinito** que trava o computador ou estoura a memória.

### 2. Confundir `print` com `println` Dentro do Laço Interno
Se você usar `System.out.println(symbol)` dentro do laço das colunas:
```java
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        System.out.println("*"); // ❌ Erro: quebra de linha a cada símbolo!
    }
}
```
Em vez de desenhar um quadrado $3 \times 3$, ele imprimirá 9 asteriscos na vertical, um embaixo do outro!

### 3. Colocar o `System.out.println()` no Lugar Errado
* Se colocado **DENTRO** do laço `j`, quebra a linha em cada coluna.
* Se colocado **FORA** do laço `i`, só pula a linha quando toda a matriz já tiver terminado.
* **Lugar correto:** Entre a chave de fechamento do `j` e a chave de fechamento do `i`:
  ```java
  for (...) {
      for (...) {
          System.out.print(...);
      } // Fim do j
      System.out.println(); // ✅ LUGAR CORRETO (Enter entre linhas)
  } // Fim do i
  ```

---

<a name="11-códigos-completos"></a>
## 💻 11. Códigos-Fonte Completos e 100% Compiláveis

### Projeto 1: Grade Numérica Fixa 3x9
```java
public class GradeNumerica {
    public static void main(String[] args) {
        
        System.out.println("=== GRADE 3x9 COM LAÇOS ANINHADOS ===");
        
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        
        System.out.println("=====================================");
    }
}
```

### Projeto 2: O Gerador de Matriz Oficial do Capítulo (Interativo)
```java
import java.util.Scanner;

public class GeradorMatrizSimbolos {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rows;
        int columns;
        char symbol;

        System.out.print("Enter the # of rows: ");
        rows = scanner.nextInt();

        System.out.print("Enter the # of columns: ");
        columns = scanner.nextInt();

        System.out.print("Enter the symbol to use: ");
        symbol = scanner.next().charAt(0);

        System.out.println("\n--- RESULTADO DA SUA MATRIZ ---");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }

        scanner.close();
    }
}
```

### Projeto Bônus Prático: Tabuada Completa (1 a 10) com Laços Aninhados
Para consolidar o aprendizado, veja como laços aninhados geram a tabuada de multiplicação inteira do 1 ao 10 com pouquíssimas linhas:
```java
public class TabuadaCompleta {
    public static void main(String[] args) {
        
        System.out.println("=== TABUADA COMPLETA (1 A 10) ===\n");

        for (int i = 1; i <= 10; i++) {
            System.out.println("--- TABUADA DO " + i + " ---");
            for (int j = 1; j <= 10; j++) {
                // Multiplica a linha 'i' pela coluna 'j'
                int resultado = i * j;
                System.out.println(i + " x " + j + " = " + resultado);
            }
            System.out.println(); // Linha em branco entre as tabuadas
        }
        
    }
}
```

---

## 🎓 Resumo e Checklist de Conhecimento do Capítulo 25

Antes de avançar para os próximos capítulos, confirme se você domina estes pontos:
- [x] Sei explicar a definição de *nested loop* como um laço dentro de outro laço.
- [x] Compreendo que o laço externo controla as **linhas** e o interno controla as **colunas**.
- [x] Sei por que dá o erro `variable i is already defined` ao repetir o nome `i`.
- [x] Uso a convenção `i` para o laço de fora e `j` para o laço de dentro.
- [x] Sei que o `System.out.println();` vazio funciona como a tecla **Enter** entre linhas.
- [x] Entendo por que não existe `scanner.nextChar()` e sei usar `scanner.next().charAt(0)`.
- [x] Compreendo a multiplicação do total de repetições: $\text{Total} = \text{Linhas} \times \text{Colunas}$.
