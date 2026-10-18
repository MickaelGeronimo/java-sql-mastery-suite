# ☕ Curso de Java — Capítulo 34: `varargs` (Parte 6)
## O Método `average` (Média), a Propriedade `numbers.length` e o Fenômeno Misterioso do `NaN` no Console!

> **Baseado na aula do canal Bro Code (Minuto `5:32:44` a `5:33:30` de 12:00:00)**  
> **Tema:** O professor eleva o nível da aula criando um segundo método com `varargs`: o cálculo da **Média Aritmética** (`average`). Ele ensina a usar a propriedade `.length` do array do varargs, troca o tipo para `double`, e faz um teste de fogo chamando o método sem nenhum argumento (`average()`), fazendo o console imprimir o misterioso texto **`NaN`**!

---

## 📌 Sumário
1. [Contexto Visual Detalhado das 3 Telas (Para o Google Docs)](#1-contexto-visual-detalhado-das-3-telas-para-o-google-docs)
2. [O que Mudou do Método `add` para o Método `average`?](#2-o-que-mudou-do-método-add-para-o-método-average)
3. [Como o Java Calcula a Média com `numbers.length`?](#3-como-o-java-calcula-a-média-com-numberslength)
4. [O Grande Enigma do Console: O que Raios Significa `NaN`?](#4-o-grande-enigma-do-console-o-que-raios-significa-nan)
5. [Por que `0 / 0` Não Travou o Programa com Erro Vermelho? (A Regra do `double`)](#5-por-que-0--0-não-travou-o-programa-com-erro-vermelho-a-regra-do-double)
6. [O Código Java Completo, Numerado e Definitivo](#6-o-código-java-completo-numerado-e-definitivo)
7. [Explicação Cirúrgica Linha por Linha](#7-explicação-cirúrgica-linha-por-linha)
8. [Teste de Mesa Comparativo: Média de 6 Números (`3.5`) vs. Média de Zero Números (`NaN`)](#8-teste-de-mesa-comparativo-média-de-6-números-35-vs-média-de-zero-números-nan)
9. [O que Sai no Console em Cada Caso](#9-o-que-sai-no-console-em-cada-caso)
10. [Resumo Prático em Uma Tabela Mental](#10-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual Detalhado das 3 Telas (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o **Google Docs**, aqui está o raio-X visual de tudo o que acontece na tela do professor em cada imagem:

### 📸 Imagem 1: Construindo o Método `average` (`media_1791242818990.png` — Minuto `5:32:44`)
* **No editor do IntelliJ IDEA:**
  * O professor escreve um método novinho em folha na linha 13:  
    `static double average(double... numbers) {`
  * Ele usa o acumulador: `double sum = 0;`.
  * Cria o laço `for-each`: `for(double number : numbers) { sum += number; }`.
  * Na linha 21, ele digita a divisão: `return sum / numbers.length;`.
* **Detalhe visual importante:**  
  Ao lado de `static double average(...)`, a IDE exibe o texto cinza `no usages` *(nenhum uso)*. Isso significa que o método acabou de ser escrito, mas ainda não foi chamado pelo método `main`.

---

### 📸 Imagem 2: Chamando a Média com 6 Argumentos (`media_1791242829377.png` — Minuto `5:33:10`)
* **No método `main` (Linha 10):**
  * O professor escreve:  
    `System.out.println(average(1, 2, 3, 4, 5, 6));`
  * O texto cinza na linha 13 muda imediatamente de `no usages` para **`1 usage`** *(1 uso)*!
* **O objetivo dele:**  
  Calcular a média dos números de 1 a 6. A soma é 21.0 e a quantidade é 6, o que dá uma média exata de `3.5`.

---

### 📸 Imagem 3: O Teste com Zero Argumentos e a Saída `NaN` (`media_1791242839978.png`)
* **No método `main` (Linha 10):**  
  O professor apaga todos os números dentro dos parênteses e chama:  
  `System.out.println(average());`  
  *(Passando absolutamente NADA entre os parênteses!).*
* **No console (Painel Inferior):**  
  O professor clica no Play e o console imprime uma resposta verde muito misteriosa:  
  ```text
  NaN

  Process finished with exit code 0
  ```
* **O impacto no aluno:**  
  Não deu erro vermelho, o programa não travou, mas apareceu essa palavra estranha: `NaN`. O professor para a aula para explicar o que aconteceu matematicamente dentro do computador!

---

## 2. O que Mudou do Método `add` para o Método `average`?

Observe as duas assinaturas lado a lado:

```java
// Método antigo de soma:
static int add(int... numbers)

// Novo método de média:
static double average(double... numbers)
```

Por que o professor trocou **`int`** por **`double`**?
1. **Casas Decimais:** Médias aritméticas quase nunca resultam em números inteiros redondos. Se você somar 10 + 7, dá 17; dividido por 2, a média é **`8.5`**. Se usássemos `int`, o Java descartaria o `.5` e diria que a média é 8 (truncamento inteiro incorreto!).
2. **Precisão Real:** O tipo `double` permite trabalhar com números de ponto flutuante de dupla precisão (com vírgula/ponto flutuante).

---

## 3. Como o Java Calcula a Média com `numbers.length`?

A fórmula matemática da média é:
$$\text{Média} = \frac{\text{Soma de Todos os Valores}}{\text{Quantidade Total de Valores}}$$

Como o `varargs` transforma os valores passados em um Array, nós temos acesso automático à propriedade:
```java
numbers.length
```
* Se passamos `1, 2, 3, 4, 5, 6`, o Java cria um array de tamanho 6, logo `numbers.length` vale **`6`**!
* O comando de retorno divide o valor total da soma pelo tamanho do array:
```java
return sum / numbers.length;
```

---

## 4. O Grande Enigma do Console: O que Raios Significa `NaN`?

Na Imagem 3, quando o professor chamou `average()` vazio, o console imprimiu:
```text
NaN
```

### O que significa a sigla `NaN`?
A palavra **`NaN`** vem do inglês e significa:
> **`N`ot `a` `N`umber** *(Não é um Número)*.

É um valor especial definido na ciência da computação para representar uma **indeterminação matemática** (uma conta impossível de calcular no mundo dos números reais)!

### De Onde Veio Esse `NaN`? Acompanhe a Matemática:
Quando você chama `average()` sem passar nada:
1. O Java cria um array com **zero** elementos (`numbers.length = 0`).
2. A variável `double sum` começa em `0.0`.
3. O laço `for-each` não dá nenhuma volta (porque não há elementos para percorrer).
4. Chegamos na linha do cálculo:
   $$\text{return } \frac{\text{sum}}{\text{numbers.length}} \implies \frac{0.0}{0}$$
5. **Divisão de zero por zero ($\frac{0}{0}$)!**  
   Na matemática, zero dividido por zero não existe; é uma indeterminação! Em vez de inventar um número falso, o Java responde honestamente: **`NaN`** (*Isso aqui não é um número válido!*).

---

## 5. Por que `0 / 0` Não Travou o Programa com Erro Vermelho? (A Regra do `double`)

Esta é uma pegadinha clássica do Java que confunde 99% dos iniciantes:

* **Se você dividir inteiros (`int`):**  
  ```java
  int x = 0 / 0; // 💥 CRASH! O programa quebra na hora!
  ```
  O Java dispara um erro gravíssimo: `java.lang.ArithmeticException: / by zero`.

* **Mas se você dividir decimais (`double` ou `float`):**  
  ```java
  double x = 0.0 / 0; // ✅ NÃO QUEBRA!
  ```
  O Java segue rigorosamente o padrão internacional de computação gráfica e matemática chamado **IEEE 754**.  
  Nesse padrão, divisões por zero com números decimais **não travam o sistema**; elas produzem valores especiais:
  * `qualquer_numero_positivo / 0.0` ➡️ `Infinity` *(Infinito positivo)*
  * `qualquer_numero_negativo / 0.0` ➡️ `-Infinity` *(Infinito negativo)*
  * `0.0 / 0.0` (zero sobre zero) ➡️ **`NaN`** *(Not a Number)*

---

## 6. O Código Java Completo, Numerado e Definitivo

Abaixo está o código exatamente como visto na tela da Imagem 2 e testado na Imagem 3:

```java
public class Main {
    public static void main(String[] args) {

        // varargs = allow a method to accept a varying # of arguments
        //           makes methods more flexible, no need for overloaded methods
        //           java will pack the arguments into an array
        //           ... (ellipsis)

        // Teste 1 (Imagem 2): Com 6 números (Imprime 3.5)
        // System.out.println(average(1, 2, 3, 4, 5, 6));

        // Teste 2 (Imagem 3): Sem nenhum número (Imprime NaN)
        System.out.println(average());

    }

    static double average(double... numbers) {

        double sum = 0;

        for (double number : numbers) {
            sum += number;
        }

        return sum / numbers.length;
    }
}
```

---

## 7. Explicação Cirúrgica Linha por Linha

---

### Linhas 1 a 3: Estrutura Base
```java
public class Main {
    public static void main(String[] args) {
```
* **O que faz:** Inicializa a classe e o método principal.
* **No console:** Nada é exibido.

---

### Linha 10: `System.out.println(average());`
* **O que faz:** Chama o método `average` passando **zero argumentos**.
* **Na memória:** A JVM empacota esses argumentos em um array vazio de double na Heap: `new double[0]`.
* **No console:** Imprime o resultado devolvido pelo método: `NaN`.

---

### Linha 12: `}`
* **O que faz:** Fecha o método `main`.

---

### Linha 13: `static double average(double... numbers) {`
* **O que faz:**
  * `static`: Pode ser chamado direto pelo `main`.
  * `double`: Promete devolver um número decimal com precisão dupla.
  * `average`: Nome do método (média em inglês).
  * `double... numbers`: O `varargs` que recebe qualquer quantidade de números decimais e cria o array `numbers`.
* **No console:** Nada é exibido.

---

### Linha 15: `double sum = 0;`
* **O que faz:** Cria a variável acumuladora local `sum` valendo `0.0`.
* **No console:** Nada é exibido.

---

### Linha 17: `for (double number : numbers) {`
* **O que faz:** Laço `for-each` para visitar cada número do array.
* **No caso do `average()` vazio:** Como o tamanho do array é zero, o laço simplesmente **não roda nenhuma vez**! O fluxo pula direto para a linha 21.
* **No console:** Nada é exibido.

---

### Linha 18: `sum += number;`
* **O que faz:** Acumula o valor na soma. (Não executada quando chamado vazio).

---

### Linha 19: `}`
* **O que faz:** Fecha o bloco do laço.

---

### Linha 21: `return sum / numbers.length;`
* **O que faz:** Executa a divisão matemática da soma pelo número de gavetas do array:
  $$\frac{0.0}{0} = \text{Double.NaN}$$
* **O que devolve:** Envia a constante `NaN` de volta para a linha 10.
* **No console:** Nada é impresso diretamente aqui.

---

### Linhas 22 e 23: `}` e `}`
* **O que faz:** Fecham o método e a classe. O programa encerra com sucesso.

---

## 8. Teste de Mesa Comparativo: Média de 6 Números (`3.5`) vs. Média de Zero Números (`NaN`)

| Etapa de Execução | Teste 1: `average(1, 2, 3, 4, 5, 6)` | Teste 2: `average()` (Vazio) |
| :--- | :---: | :---: |
| **Array Criado na Heap** | `[1.0, 2.0, 3.0, 4.0, 5.0, 6.0]` | `[]` *(Array vazio de tamanho 0)* |
| **Tamanho (`numbers.length`)** | **`6`** | **`0`** |
| **Voltas no `for-each`** | 6 voltas completas | **0 voltas** (pulou o laço) |
| **Valor Final de `sum`** | `21.0` | `0.0` |
| **Cálculo da Divisão** | `21.0 / 6` | `0.0 / 0` |
| **Valor Retornado** | **`3.5`** | **`NaN`** *(Not a Number)* |
| **Resultado no Console** | **`3.5`** | **`NaN`** |

---

## 9. O que Sai no Console em Cada Caso

### Cenário 1: Chamando com 6 números (`media_1791242829377.png`):
```text
3.5

Process finished with exit code 0
```

### Cenário 2: Chamando vazio (`media_1791242839978.png`):
```text
NaN

Process finished with exit code 0
```

---

## 10. Resumo Prático em Uma Tabela Mental

| Pergunta de Estudo | Resposta Clara e Objetiva |
| :--- | :--- |
| **Por que mudamos de `int` para `double`?** | Porque médias aritméticas exigem precisão decimal (com vírgula). |
| **O que é `numbers.length`?** | É o total de gavetas do array criado pelo `varargs` (a quantidade de números passados). |
| **O que significa `NaN`?** | *Not a Number* (Não é um Número). Representa uma indeterminação matemática. |
| **Por que deu `NaN`?** | Porque passamos zero argumentos, gerando a conta impossível: `0.0 / 0`. |
| **Como resolveremos esse bug na próxima aula?** | Adicionando uma verificação de segurança: `if (numbers.length == 0) return 0;`! |

---

*(Fim do guia do Capítulo 34 — Parte 6. Perfeito para copiar, colar e estudar no seu Google Docs!)*
