# 🗺️ Curso de Java Completo — Capítulo 67: HashMaps (Parte 2)
## A Sintaxe Perfeita: `HashMap<String, Double>`, o Erro de Argumento Único e o Operador Diamante `<>`

---

## 📑 Sumário

1. [Visão Geral do Episódio e Propósito Pedagógico](#1-visão-geral-do-episódio-e-propósito-pedagógico)
2. [Reconstituição Visual Completa dos 5 Momentos do Vídeo (Google Docs)](#2-reconstituição-visual-completa-dos-5-momentos-do-vídeo-google-docs)
   - [Momento 1 (~10:53:35): O Erro de Digitação Proposital — Apenas um Tipo `<String>`](#momento-1-105335-o-erro-de-digitação-proposital--apenas-um-tipo-string)
   - [Momento 2 (~10:53:40): O Detalhe do Sublinhado Vermelho em `<String>`](#momento-2-105340-o-detalhe-do-sublinhado-vermelho-em-string)
   - [Momento 3 (10:53:51): Adicionando o Segundo Tipo `Double`, mas Sem Diamante na Direita](#momento-3-105351-adicionando-o-segundo-tipo-double-mas-sem-diamante-na-direita)
   - [Momento 4 (10:54:06): A Linha Perfeita com o Diamante — `new HashMap<>()`](#momento-4-105406-a-linha-perfeita-com-o-diamante--new-hashmap)
   - [Momento 5 (~10:54:15): Selecionando `<String, Double>` para a Explicação Conceitual](#momento-5-105415-selecionando-string-double-para-a-explicação-conceitual)
3. [Código-Fonte Completo de `Main.java`](#3-código-fonte-completo-de-mainjava)
4. [Explicação Linha por Linha ("Como se Fosse um Lezado Iniciante Amador")](#4-explicação-linha-por-linha-como-se-fosse-um-lezado-iniciante-amador)
   - [Dissecando Cada Termo de `HashMap<String, Double> map = new HashMap<>();`](#dissecando-cada-termo-de-hashmapstring-double-map--new-hashmap)
5. [Por Que o Java Exige Dois Tipos no HashMap?](#5-por-que-o-java-exige-dois-tipos-no-hashmap)
   - [O Erro de Argumento Único (`Wrong number of type arguments`)](#o-erro-de-argumento-único-wrong-number-of-type-arguments)
   - [Por que `Double` (com 'D' maiúsculo) e NUNCA `double`?](#por-que-double-com-d-maiúsculo-e-nunca-double)
   - [O Papel do Operador Diamante `<>` no Lado Direito](#o-papel-do-operador-diamante--no-lado-direito)
6. [O Cenário do Mundo Real que Vamos Construir](#6-o-cenário-do-mundo-real-que-vamos-construir)
   - [A Tabela de Preços do Supermercado](#a-tabela-de-preços-do-supermercado)
7. [Tabela de Saída do Console e Rastreio de Compilação](#7-tabela-de-saída-do-console-e-rastreio-de-compilação)
8. [Conclusão e Próximos Passos](#8-conclusão-e-próximos-passos)

---

## 1. Visão Geral do Episódio e Propósito Pedagógico

Na parte anterior, vimos a introdução teórica do `HashMap` e o aviso de *Raw Type* que acontece quando alguém tenta usá-lo sem especificar os tipos genéricos.

Agora, nesta **Parte 2** (minutos `10:53:30` a `10:54:15`), o Bro Code guia o aluno passo a passo pela construção da declaração perfeita, cometendo erros comuns e mostrando como corrigi-los:

1. **O Erro do Tipo Único (Momentos 1 e 2):** Ele digita `HashMap<String> map = new HashMap();`. O IntelliJ imediatamente grifa `<String>` com uma linha ondulada vermelha de erro de compilação! Por quê? Porque um `HashMap` armazena pares, então ele **obriga** o programador a fornecer dois tipos (`<K, V>`).
2. **A Correção Parcial (Momento 3):** Ele adiciona a vírgula e o segundo tipo: `HashMap<String, Double> map = new HashMap();`. O erro vermelho desaparece, mas o lado direito ainda fica com um aviso amarelo de aviso.
3. **O Fechamento Perfeito com Diamante (Momento 4):** Ele adiciona o operador diamante vazio `<>` após o segundo `HashMap`:
   ```java
   HashMap<String, Double> map = new HashMap<>();
   ```
   Agora todos os avisos e erros sumiram! A linha está 100% limpa, elegante e moderna.
4. **O Ensinamento Semântico (Momento 5):** Ele seleciona `<String, Double>` com o mouse e explica: a Chave será o nome de um produto (`String`) e o Valor será o preço desse produto (`Double`).

---

## 2. Reconstituição Visual Completa dos 5 Momentos do Vídeo (Google Docs)

> [!NOTE]
> Copie e cole os blocos de texto abaixo diretamente no seu documento do **Google Docs**. Você terá uma cópia idêntica da interface do IntelliJ IDEA exibida pelo Bro Code durante esta etapa.

---

### Momento 1 (~10:53:35): O Erro de Digitação Proposital — Apenas um Tipo `<String>`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - ~10:53:35 / 12:00:00 - Chapter: 67.hashmaps 🗺️ (ERRO DE COMPILAÇÃO)
====================================================================================================
[Project Tree]                           [Editor: Main.java (ATIVO)]
📁 MyFirstProject C:\Users\HP...         -----------------------------------------------------------
  📁 .idea                                1: import java.util.HashMap;
  📁 out                                  2: 
  📁 src                                  3: public class Main {
    ☕ Main                               4:     public static void main(String[] args) {
  📄 .gitignore                           5: 
  📄 MyFirstProject.iml                   6:         // HashMap = A data structure that stores key-value pairs
  📚 External Libraries                   7:         //           Keys are unique, but Values can be duplicated
                                          8:         //           Does not maintain any order, but is memory efficient
                                          9:         //           HashMap<Key, Value>
                                         10: 
                                         11: 💡      HashMap<🟦String🟦> map = new HashMap();
                                                            ~~~~~~~~
                                                     (ERRO VERMELHO: Faltando segundo tipo genérico!)
                                         12:     }
                                         13: }
====================================================================================================
```

---

### Momento 2 (~10:53:40): O Detalhe do Sublinhado Vermelho em `<String>`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - ~10:53:40 / 12:00:00 - Chapter: 67.hashmaps 🗺️
====================================================================================================
[Project Tree]                           [Editor: Main.java]
📁 src                                   -----------------------------------------------------------
  ☕ Main                                11:         HashMap<String> map = new HashMap();
                                                            ^^^^^^
                                         Mensagem do Compilador Java:
                                         "Wrong number of type arguments: 1; required: 2"
                                         (Número incorreto de argumentos de tipo: 1; necessários: 2)
====================================================================================================
```

---

### Momento 3 (10:53:51): Adicionando o Segundo Tipo `Double`, mas Sem Diamante na Direita

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:53:51 / 12:00:00 - Chapter: 67.hashmaps 🗺️
====================================================================================================
[Project Tree]                           [Editor: Main.java (ATIVO)]
📁 src                                   -----------------------------------------------------------
  ☕ Main                                11: 💡      HashMap<String, 🟦Double🟦> map = new HashMap();
                                                                                          ~~~~~~~
                                                                                 (Aviso: Raw Constructor)
                                         12:     }
                                         13: }
                                         -----------------------------------------------------------
                                         (O erro vermelho sumiu! Falta apenas o diamante no new!)
====================================================================================================
```

---

### Momento 4 (10:54:06): A Linha Perfeita com o Diamante — `new HashMap<>()`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:54:06 / 12:00:00 - Chapter: 67.hashmaps 🗺️ (100% LIMPO)
====================================================================================================
[Project Tree]                           [Editor: Main.java (ATIVO)]
📁 src                                   -----------------------------------------------------------
  ☕ Main                                 1: import java.util.HashMap;
                                          2: 
                                          3: public class Main {
                                          4:     public static void main(String[] args) {
                                          5: 
                                          6:         // HashMap = A data structure that stores key-value pairs
                                          7:         //           Keys are unique, but Values can be duplicated
                                          8:         //           Does not maintain any order, but is memory efficient
                                          9:         //           HashMap<Key, Value>
                                         10: 
                                         11:         HashMap<String, Double> map = new HashMap<>();|
                                                                                                   ^
                                                                                          (Cursor piscando)
                                         12:     }
                                         13: }
                                         -----------------------------------------------------------
                                         (Nenhum aviso amarelo! Nenhum erro vermelho! Perfeito!)
====================================================================================================
```

---

### Momento 5 (~10:54:15): Selecionando `<String, Double>` para a Explicação Conceitual

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - ~10:54:15 / 12:00:00 - Chapter: 67.hashmaps 🗺️
====================================================================================================
[Project Tree]                           [Editor: Main.java (ATIVO)]
📁 src                                   -----------------------------------------------------------
  ☕ Main                                11: 💡      HashMap<🟦String, Double🟦> map = new HashMap<>();
                                                            ^^^^^^^^^^^^^^^^
                                                      (Selecionado com o mouse pelo instrutor)
                                         12:     }
                                         13: }
                                         -----------------------------------------------------------
                                         Fala do Bro Code:
                                         "Aqui nós especificamos a nossa Chave e o nosso Valor:
                                         • Chave = String (o nome do item)
                                         • Valor = Double (o preço do item)"
====================================================================================================
```

---

## 3. Código-Fonte Completo de `Main.java`

Aqui está o código exato em execução no seu projeto local:

### [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // HashMap = A data structure that stores key-value pairs
        //           Keys are unique, but Values can be duplicated
        //           Does not maintain any order, but is memory efficient
        //           HashMap<Key, Value>

        HashMap<String, Double> map = new HashMap<>();

    }
}
```

---

## 4. Explicação Linha por Linha ("Como se Fosse um Lezado Iniciante Amador")

Vamos dissecar cada pedaço da Linha 11 como se estivéssemos desmontando uma peça mecânica:

```java
11: HashMap<String, Double> map = new HashMap<>();
```

1. **`HashMap`**:
   - É o nome da classe oficial da biblioteca do Java (`java.util.HashMap`). Ela avisa ao compilador: *"Quero usar uma tabela de espalhamento de chave-valor"*.
2. **`<String, Double>`**:
   - Estes são os **dois Argumentos de Tipo (*Type Arguments*)**:
     - `String`: É o tipo da **Chave (`Key`)**. Significa que as chaves serão textos (como `"maçã"`, `"banana"`, `"notebook"`).
     - `Double`: É o tipo do **Valor (`Value`)**. Significa que os valores serão números com casas decimais (como `0.50`, `0.75`, `2500.00`).
3. **`map`**:
   - É o nome que escolhemos para a nossa variável de referência. Poderia se chamar `produtos`, `precos`, `tabela`, mas o autor chamou de `map` (abreviação de mapa).
4. **`=`**:
   - Operador de atribuição: faz a variável `map` apontar para o objeto recém-nascido.
5. **`new`**:
   - Palavra mágica que reserva espaço na memória RAM (na área chamada Heap) para fabricar a estrutura do mapa.
6. **`HashMap<>()`**:
   - Invoca o construtor da classe `HashMap`.
   - O símbolo de menor e maior vazio **`<>`** é chamado de **Operador Diamante (*Diamond Operator*)**. Ele diz para o Java: *"Não precisa me obrigar a digitar `new HashMap<String, Double>()` de novo! Você já sabe que é `String` e `Double` porque eu já escrevi no lado esquerdo!"*.
7. **`;`**:
   - O ponto e vírgula obrigatório que finaliza toda instrução em Java.

---

## 5. Por Que o Java Exige Dois Tipos no HashMap?

### O Erro de Argumento Único (`Wrong number of type arguments`)

Nos Momentos 1 e 2, o Bro Code digitou:
```java
HashMap<String> map = new HashMap(); // ❌ ERRO DE COMPILAÇÃO!
```

Por que isso não compila de jeito nenhum?
- Compare com o `ArrayList`:
  - Um `ArrayList` é uma gaveta comum: você só guarda um elemento por divisória (`ArrayList<String>`). Um tipo basta!
- Já um `HashMap` é uma lista de pares inseparáveis:
  - Ele **precisa obrigatoriamente** saber o tipo da etiqueta (Chave) E o tipo do conteúdo (Valor).
  - Na biblioteca do Java, a classe foi definida como:
    ```java
    public class HashMap<K, V>
    ```
  - Se você der apenas um tipo, o Java fica sem saber quem é o `V` e trava a compilação na hora com o erro:
    ```text
    Wrong number of type arguments: 1; required: 2
    ```

---

### Por que `Double` (com 'D' maiúsculo) e NUNCA `double`?

Se você tentar digitar:
```java
HashMap<String, double> map = new HashMap<>(); // ❌ ERRO GRAVE!
```
O compilador não aceita!
Lembre-se da regra sagrada de Generics do Capítulo 66: **Generics em Java NÃO aceitam tipos primitivos**.
- `double` é primitivo (apenas 8 bytes crus de número na memória).
- `Double` é a **Classe Envelope (*Wrapper Class*)**, que embrulha o número dentro de um Objeto com métodos e suporte ao polimorfismo do Java.

---

### O Papel do Operador Diamante `<>` no Lado Direito

No Java antigo (Java 5 e 6), você era obrigado a escrever o tipo duas vezes:
```java
// Estilo antigo e repetitivo (Java 5/6):
HashMap<String, Double> map = new HashMap<String, Double>();
```
A partir do Java 7, os engenheiros da Sun/Oracle criaram o **Diamond Operator `<>`**:
```java
// Estilo moderno e elegante (Java 7 até hoje):
HashMap<String, Double> map = new HashMap<>();
```
Ele faz **Inferência de Tipo (*Type Inference*)**. O compilador é inteligente o suficiente para copiar os tipos da esquerda sem você ter que cansar seus dedos!

---

## 6. O Cenário do Mundo Real que Vamos Construir

Com a declaração `HashMap<String, Double> map = new HashMap<>();`, nós estamos preparando o sistema de caixa de uma quitanda ou supermercado:

```text
┌────────────────────────────────────────────────────────┐
│                   TABELA DE PREÇOS                     │
├──────────────────────────┬─────────────────────────────┤
│ CHAVE (Key: String)      │ VALOR (Value: Double)       │
├──────────────────────────┼─────────────────────────────┤
│ "apple"                  │ 0.50                        │
│ "orange"                 │ 0.75                        │
│ "banana"                 │ 0.25                        │
│ "coconut"                │ 1.25                        │
└──────────────────────────┴─────────────────────────────┘
```

No próximo passo, o Bro Code começará a povoar este mapa usando o método `put()`!

---

## 7. Tabela de Saída do Console e Rastreio de Compilação

| Instrução Atual | O que a JVM Faz | Saída no Console |
| :--- | :--- | :---: |
| `HashMap<String, Double> map = new HashMap<>();` | Instancia uma tabela de dispersão vazia na memória Heap com capacidade padrão para 16 posições, configurada para receber chaves do tipo `String` e valores do tipo `Double`. | *(nenhuma saída)* |

---

## 8. Conclusão e Próximos Passos

Nesta Parte 2, você aprendeu:
- Que o `HashMap` exige **exatamente dois tipos genéricos** (`<Key, Value>`).
- Que tentar passar apenas um tipo (`<String>`) resulta em erro de compilação imediato.
- Como o **Operador Diamante `<>`** simplifica a instanciação do lado direito do `=`.
- Que a tipagem `<String, Double>` define um dicionário de nomes de produtos para preços decimais.

Nas próximas cenas do vídeo, o Bro Code demonstrará:
1. Como inserir dados no mapa com `map.put("apple", 0.50);`.
2. O que acontece quando tentamos inserir uma chave duplicada.
3. Como recuperar o preço de um item com `map.get("apple")`.
4. Como verificar o tamanho do mapa com `map.size()`.
