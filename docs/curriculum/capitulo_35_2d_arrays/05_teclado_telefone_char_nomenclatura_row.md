# ☕ Curso de Java — Capítulo 35: `2D arrays ⬜` (Parte 5)
## O Teclado Telefônico: Matriz de Caracteres (`char[][]`), Nomenclatura Profissional (`row`) e o Mapa do Teclado Numérico

> **Baseado na aula do canal Bro Code (Minuto `5:43:00` a `5:43:35` de 12:00:00)**  
> **Tema:** O professor apaga o exemplo das compras e introduz um clássico absoluto da computação: a modelagem de um **Teclado de Telefone** (*Telephone Keypad*) em uma matriz regular de $4 \times 3$. Ele ensina a usar o tipo primitivo `char[][]` com aspas simples, adota a nomenclatura profissional de matrizes (`row`), e demonstra a leitura completa de todas as teclas.

---

## 📌 Sumário
1. [Contexto Visual Detalhado das 3 Telas (Para o Google Docs)](#1-contexto-visual-detalhado-das-3-telas-para-o-google-docs)
2. [Por que o Teclado Telefônico é o Exemplo Perfeito de Matriz 2D?](#2-por-que-o-teclado-telefônico-é-o-exemplo-perfeito-de-matriz-2d)
3. [A Mudança de Tipo: Por que `char[][]` e Aspas Simples (`' '`)?](#3-a-mudança-de-tipo-por-que-char-e-aspas-simples--)
4. [Nomenclatura Profissional (*Clean Code*): O Significado de `row`](#4-nomenclatura-profissional-clean-code-o-significado-de-row)
5. [O Código Java Completo, Numerado e Definitivo](#5-o-código-java-completo-numerado-e-definitivo)
6. [Explicação Cirúrgica Linha por Linha](#6-explicação-cirúrgica-linha-por-linha)
7. [Teste de Mesa Passo a Passo: A Varredura das 4 Linhas](#7-teste-de-mesa-passo-a-passo-a-varredura-das-4-linhas)
8. [O que Saiu no Console e Por que Ainda Ficou em Linha Reta?](#8-o-que-saiu-no-console-e-por-que-ainda-ficou-em-linha-reta)
9. [Resumo Prático em Uma Tabela Mental](#9-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Detalhado das 3 Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que acontece na tela da IDE IntelliJ IDEA em cada uma das 3 imagens enviadas:

### 📸 Imagem 1: Limpando a Lousa (`media_1791243327599.png`)
* **No editor de código (`Main.java`):**
  * O professor seleciona e deleta todo o código anterior de `groceries`.
  * Mantém apenas a classe `Main`, o método `main` e os comentários teóricos das linhas 4 e 5.
  * O cursor de texto pisca vazio na linha 7, preparando-se para digitar o novo exemplo do zero.

---

### 📸 Imagem 2: Digitando a Matriz `telephone` (`media_1791243362838.png` — Minuto `5:43:24`)
* **No editor (Linhas 7 a 10):**
  * O professor digita a matriz com 4 linhas e 3 colunas:
    ```java
    char[][] telephone = {{'1', '2', '3'},
                          {'4', '5', '6'},
                          {'7', '8', '9'},
                          {'*', '0', '#'}};
    ```
* **Nos laços de repetição (Linhas 12 a 14):**
  * Ele escreve: `for(char[] row : telephone){`
  * E dentro dele: `for(char number : row){`
  * Na linha 14, ele está digitando `System.out.print(nu` (o recurso de autocompletar do IntelliJ exibe uma linha vermelha temporária sob `nu` enquanto ele termina de escrever a palavra `number`).

---

### 📸 Imagem 3: A Execução no Console (`media_1791243371243.png` — Minuto `5:43:32`)
* **No editor:**
  * O comando da linha 14 foi finalizado:  
    `System.out.print(number + " ");`.
  * Ele fecha as chaves dos dois laços nas linhas 15 e 16.
  * O professor ainda **não colocou** o `System.out.println();` de quebra de linha.
* **No console (Painel Inferior):**
  * O programa roda e imprime em verde todas as 12 teclas em uma linha horizontal contínua:
    ```text
    1 2 3 4 5 6 7 8 9 * 0 # 

    Process finished with exit code 0
    ```
  * O professor usa o mouse para selecionar o número `3` no console para mostrar aos alunos onde terminou a primeira linha do teclado.

---

## 2. Por que o Teclado Telefônico é o Exemplo Perfeito de Matriz 2D?

Olhe para o aplicativo de chamadas do seu celular ou para um interfone. O teclado numérico é uma **matriz matemática exata**:

$$\begin{matrix}
\text{Linha 0:} & \mathbf{1} & \mathbf{2} & \mathbf{3} \\
\text{Linha 1:} & \mathbf{4} & \mathbf{5} & \mathbf{6} \\
\text{Linha 2:} & \mathbf{7} & \mathbf{8} & \mathbf{9} \\
\text{Linha 3:} & \mathbf{*} & \mathbf{0} & \mathbf{\#}
\end{matrix}$$

* **Dimensão:** É uma matriz de **$4 \times 3$** (4 Linhas e 3 Colunas).
* **Total de elementos:** $4 \times 3 = 12$ teclas.
* Ao contrário do exemplo das compras (onde a linha das carnes tinha 4 itens e as outras tinham 3), o teclado telefônico é uma **matriz regular perfeita**, onde todas as linhas têm exatamente o mesmo tamanho!

---

## 3. A Mudança de Tipo: Por que `char[][]` e Aspas Simples (`' '`)?

Até agora, nós estávamos usando `String[][]`. Por que o professor mudou para **`char[][]`**?

| Característica | Tipo `String` | Tipo Primitivo `char` |
| :--- | :--- | :--- |
| **O que armazena?** | Textos longos, palavras ou frases. | **Um único caractere** (uma letra, um dígito ou um símbolo). |
| **Sintaxe no código** | Aspas duplas: `"apple"`, `"1"` | **Aspas simples obrigatórias:** `'1'`, `'*'`, `'#'` |
| **Consumo de Memória** | Objeto pesado na Heap com cabeçalhos. | Apenas **16 bits** de memória rápida! |
| **Por que usar no telefone?** | Uma tecla de telefone só tem um dígito; não é um texto longo. Usar `char` é muito mais eficiente e elegante! |

---

## 4. Nomenclatura Profissional (*Clean Code*): O Significado de `row`

Observe como o professor nomeou a variável do primeiro laço:

```java
for (char[] row : telephone)
```

A palavra inglesa **`row`** significa **LINHA**:
* Em vez de inventar nomes genéricos ou confusos, os programadores profissionais usam sempre `row` para representar uma linha de uma matriz.
* Lê-se em português claro:  
  *"Para cada linha (`row`) dentro da matriz `telephone`..."*

E no laço interno:
```java
for (char number : row)
```
* Lê-se: *"Para cada número/tecla (`number`) que estiver dentro desta linha (`row`)..."*.

---

## 5. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo `Main.java` integral exatamente como configurado na Imagem 3:

```java
public class Main {
    public static void main(String[] args) {

        // 2D array = An array where each element is an array
        //            Useful for storing a matrix of data

        char[][] telephone = {{'1', '2', '3'},
                              {'4', '5', '6'},
                              {'7', '8', '9'},
                              {'*', '0', '#'}};

        for (char[] row : telephone) {
            for (char number : row) {
                System.out.print(number + " ");
            }
        }
    }
}
```

---

## 6. Explicação Cirúrgica Linha por Linha

---

### Linhas 1 a 5: Estrutura Base e Comentários
* **O que faz:** Inicializa o programa e mantém as definições teóricas de 2D array.
* **No console:** Nada é exibido.

---

### Linhas 7 a 10: Declaração da Matriz `telephone`
```java
char[][] telephone = {{'1', '2', '3'},
                      {'4', '5', '6'},
                      {'7', '8', '9'},
                      {'*', '0', '#'}};
```
* **O que faz:** Cria a matriz bidimensional de caracteres na memória Heap:
  * Linha de índice `0`: `{'1', '2', '3'}`
  * Linha de índice `1`: `{'4', '5', '6'}`
  * Linha de índice `2`: `{'7', '8', '9'}`
  * Linha de índice `3`: `{'*', '0', '#'}`
* **No console:** Nada é exibido.

---

### Linha 12: `for (char[] row : telephone) {`
* **O que faz:** **Laço Externo.** Percorre as 4 linhas do teclado. Em cada ciclo, a variável `row` recebe um array de 3 caracteres (`char[]`).
* **No console:** Nada é exibido.

---

### Linha 13: `for (char number : row) {`
* **O que faz:** **Laço Interno.** Percorre os 3 botões da linha atual. A variável `number` recebe um caractere individual (`char`).
* **No console:** Nada é exibido.

---

### Linha 14: `System.out.print(number + " ");`
* **O que faz:** Imprime o caractere atual acompanhado de um espaço em branco, **sem quebrar linha**.
* **No console:** Vai cuspindo os números lado a lado na mesma linha.

---

### Linhas 15 e 16: `}` e `}`
* **O que faz:** Fecham o laço interno e o laço externo.

---

### Linhas 17 e 18: `}` e `}`
* **O que faz:** Fecham o método `main` e a classe `Main`. Fim do programa.

---

## 7. Teste de Mesa Passo a Passo: A Varredura das 4 Linhas

Acompanhe como o computador lê os 12 botões do telefone:

| Linha Atual (`row`) | Coluna Lida | Caractere (`number`) | Ação | Console Acumulado |
| :---: | :---: | :---: | :--- | :--- |
| **Linha 0** | 0 | `'1'` | Imprime | `1 ` |
| **Linha 0** | 1 | `'2'` | Imprime | `1 2 ` |
| **Linha 0** | 2 | `'3'` | Imprime | `1 2 3 ` |
| **Linha 1** | 0 | `'4'` | Imprime | `1 2 3 4 ` |
| **Linha 1** | 1 | `'5'` | Imprime | `1 2 3 4 5 ` |
| **Linha 1** | 2 | `'6'` | Imprime | `1 2 3 4 5 6 ` |
| **Linha 2** | 0-2 | `'7', '8', '9'` | Imprime | `1 2 3 4 5 6 7 8 9 ` |
| **Linha 3** | 0-2 | `'*', '0', '#'` | Imprime | `1 2 3 4 5 6 7 8 9 * 0 # ` |

---

## 8. O que Saiu no Console e Por que Ainda Ficou em Linha Reta?

Ao rodar o código da Imagem 3, a saída do console é:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA..."
1 2 3 4 5 6 7 8 9 * 0 # 

Process finished with exit code 0
```

### Por que não saiu no formato de grade 4x3 ainda?
Porque o professor ainda **não colocou o `System.out.println();`** no final de cada `row`!  
Ele fez isso de propósito para mostrar que, sem a quebra de linha manual, o Java vai imprimindo tudo em uma fila indiana reta. Na próxima aula, ele colocará a quebra de linha para fazer o teclado aparecer quadrado e perfeito na tela!

---

## 9. Resumo Prático em Uma Tabela Mental

| Pergunta para Revisão | Resposta Direta |
| :--- | :--- |
| **Qual é o novo tipo usado?** | `char[][]` (matriz de caracteres primitivos). |
| **Por que usamos aspas simples (`'1'`)?** | Porque valores do tipo `char` são delimitados obrigatoriamente por aspas simples em Java. |
| **O que significa a variável `row`?** | Significa *Linha* em inglês. É o nome padrão para a variável que segura cada linha da matriz. |
| **Quantas linhas e colunas tem essa matriz?** | 4 Linhas e 3 Colunas ($4 \times 3$). |
| **Qual tecla mora em `telephone[3][1]`?** | Linha 3 (última linha), Coluna 1 (do meio) ➡️ É a tecla `'0'`! |

---

*(Fim do guia do Capítulo 35 — Parte 5. Perfeito para copiar, colar e formatar no seu Google Docs!)*
