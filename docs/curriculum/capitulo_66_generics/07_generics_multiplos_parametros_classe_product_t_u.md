# 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 7)
## Múltiplos Parâmetros Genéricos: Criando a Classe `Product<T, U>` com Dois Coringas Independentes

---

## 📑 Sumário

1. [Visão Geral do Episódio e Propósito Pedagógico](#1-visão-geral-do-episódio-e-propósito-pedagógico)
2. [Reconstituição Visual Completa dos 4 Momentos do Vídeo (Google Docs)](#2-reconstituição-visual-completa-dos-4-momentos-do-vídeo-google-docs)
   - [Momento 1 (~10:47:30): Limpeza do `main` e Criando a Nova Classe no IntelliJ](#momento-1-104730-limpeza-do-main-e-criando-a-nova-classe-no-intellij)
   - [Momento 2 (10:47:40): O Arquivo `Product.java` em Branco](#momento-2-104740-o-arquivo-productjava-em-branco)
   - [Momento 3 (~10:48:00): Introduzindo Múltiplos Parâmetros `<T, U>`](#momento-3-104800-introduzindo-múltiplos-parâmetros-t-u)
   - [Momento 4 (10:48:37): Declarando `T item`, `U price` e o Esboço do Construtor](#momento-4-104837-declarando-t-item-u-price-e-o-esboço-do-construtor)
3. [Código-Fonte Completo dos Arquivos do Projeto](#3-código-fonte-completo-dos-arquivos-do-projeto)
   - [`Product.java` (O Novo Arquivo)](https://file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Product.java)
   - [`Main.java`](https://file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)
   - [`Box.java`](https://file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Box.java)
4. [Explicação Linha por Linha ("Como se Fosse um Lezado Iniciante Amador")](#4-explicação-linha-por-linha-como-se-fosse-um-lezado-iniciante-amador)
   - [Dissecando `Product.java` Linha por Linha](#dissecando-productjava-linha-por-linha)
5. [Por Que Usar `<T, U>`? A Ciência dos Múltiplos Tipos Genéricos](#5-por-que-usar-t-u-a-ciência-dos-múltiplos-tipos-genéricos)
   - [A Convenção de Letras no Java (`T`, `U`, `V`, `K`, `V`, `E`)](#a-convenção-de-letras-no-java-t-u-v-k-v-e)
   - [A Diferença Entre `<T>` (1 Tipo) e `<T, U>` (2 Tipos Independentes)](#a-diferença-entre-t-1-tipo-e-t-u-2-tipos-independentes)
   - [Exemplos da Vida Real: O Que Poderemos Guardar em `Product<T, U>`?](#exemplos-da-vida-real-o-que-poderemos-guardar-em-productt-u)
6. [Previsão de Saída do Console e Estado do Compilador](#6-previsão-de-saída-do-console-e-estado-do-compilador)
7. [O Que Vem a Seguir no Construtor e nos Próximos Segundos do Vídeo](#7-o-que-vem-a-seguir-no-construtor-e-nos-próximos-segundos-do-vídeo)

---

## 1. Visão Geral do Episódio e Propósito Pedagógico

Até o momento, nós aprendemos a criar uma classe genérica simples: a `Box<T>`. Ela funcionou maravilhosamente bem, mas tinha uma limitação conceitual: ela só guardava **uma única coisa** ou dependia de **um único tipo genérico** `T`.

Mas pense no mundo real: **as coisas no dia a dia quase sempre possuem mais de um dado com tipos diferentes!**
Por exemplo, pense em um **Produto de Loja** (`Product`):
- Um produto tem um **nome/item**: que geralmente é um texto (`String`), como `"PlayStation 5"`, `"Camiseta"`, ou até um código numérico de barras (`Integer`).
- Um produto tem um **preço** (`price`): que pode ser um número decimal com centavos (`Double`), como `499.99`, ou um valor inteiro (`Integer`), como `500`.

Se usássemos apenas um `<T>`, seríamos obrigados a fazer com que o item e o preço fossem do **mesmo** tipo!
Para resolver isso com perfeição arquitetural, o Java permite definir **MÚLTIPLOS PARÂMETROS DE TIPO** separados por vírgula dentro dos diamantes:
```java
public class Product<T, U>
```

Neste bloco de vídeo (minutos `10:47:30` até `10:48:37`), o Bro Code:
1. Limpa o método `main` em `Main.java` para preparar o novo teste.
2. Cria um novo arquivo Java chamado `Product.java`.
3. Transforma essa classe em uma classe genérica de **dois coringas independentes**: `<T, U>`.
4. Declara dois campos de instância: `T item;` e `U price;`.
5. Começa a escrever o construtor da classe `Product()`.

---

## 2. Reconstituição Visual Completa dos 4 Momentos do Vídeo (Google Docs)

> [!NOTE]
> Copie e cole os blocos abaixo diretamente no seu documento do **Google Docs**. Eles trazem todo o contexto visual e estrutural que apareceu na tela do instrutor.

---

### Momento 1 (~10:47:30): Limpeza do `main` e Criando a Nova Classe no IntelliJ

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - ~10:47:30 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Main.java]
📁 MyFirstProject C:\Users\HP...         -----------------------------------------------------------
  📁 .idea                                1: import java.util.ArrayList;
  📁 out                                  2: 
  📁 src                                  3: public class Main {
    ☕ Box                                 4:     public static void main(String[] args) {
    ☕ Main                                5: 
  📄 .gitignore                           6:         // Generics = A concept where you can write a class, interface, or met...
  📄 MyFirstProject.iml                   7:         //            that is compatible with different data types.
  📚 External Libraries                   8:         //            <T> type parameter (placeholder that gets replaced with...
                                          9:         //            <String> type argument (specifies the type)
                                         10: 
                                         11:         ┌────────────────────────────────────────────────────────┐
                                         12:     }   │ New Java Class                                         │
                                         13: }       │ ------------------------------------------------------ │
                                                     │ 🔲 Name: [Product                           ]          │
                                                     │ ------------------------------------------------------ │
                                                     │ (c) Class  <-- [SELECIONADO COM ENTER]                 │
                                                     │ (I) Interface                                          │
                                                     │ (R) Record                                             │
                                                     │ (E) Enum                                               │
                                                     │ (@) Annotation                                         │
                                                     │ (!) Exception                                          │
                                                     └────────────────────────────────────────────────────────┘
====================================================================================================
```

---

### Momento 2 (10:47:40): O Arquivo `Product.java` em Branco

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:47:40 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Main.java]  [Editor: Product.java]  [Editor: Box.java]
📁 MyFirstProject                        -----------------------------------------------------------
  📁 src                                  1: public class Product {
    ☕ Box                                 2: }
    ☕ Main                                3: 
    ☕ Product <-- (Novo arquivo criado!)
====================================================================================================
```

---

### Momento 3 (~10:48:00): Introduzindo Múltiplos Parâmetros `<T, U>`

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - ~10:48:00 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Product.java]
📁 src                                   -----------------------------------------------------------
  ☕ Box                                   1: public class Product<T, U> {
  ☕ Main                                  2: 
  ☕ Product                              3:     T|   <-- (Digitando o primeiro atributo coringa)
                                          4: }
                                          5: 
====================================================================================================
```

---

### Momento 4 (10:48:37): Declarando `T item`, `U price` e o Esboço do Construtor

```text
====================================================================================================
INTELLIJ IDEA - [MyFirstProject] - 10:48:37 / 12:00:00 - Chapter: 66.generics
====================================================================================================
[Project Tree]                           [Editor: Product.java]
📁 src                                   -----------------------------------------------------------
  ☕ Box                                   1: public class Product<T, U> {
  ☕ Main                                  2: 
  ☕ Product                              3:     T item;  <-- (Palavra 'item' selecionada/digitada)
                                          4:     U price;
                                          5: 
                                          6:     Product(){
                                          7: 
                                          8:     }
                                          9: }
                                         10: 
====================================================================================================
```

---

## 3. Código-Fonte Completo dos Arquivos do Projeto

Aqui estão os códigos atuais dos arquivos no seu projeto local:

### [`Product.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Product.java)
```java
public class Product<T, U> {

    T item;
    U price;

    Product(){

    }
}
```

### [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)
```java
public class Main {
    public static void main(String[] args) {

        // Generics = A concept where you can write a class, interface, or method
        //            that is compatible with different data types.
        //            <T> type parameter (placeholder that gets replaced with a real type)
        //            <String> type argument (specifies the type)

    }
}
```

### [`Box.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Box.java)
*(Permanece intacta no projeto como referência do exemplo anterior de parâmetro único).*

---

## 4. Explicação Linha por Linha ("Como se Fosse um Lezado Iniciante Amador")

Vamos analisar com calma cada linha do novo arquivo `Product.java`.

```java
1: public class Product<T, U> {
2: 
3:     T item;
4:     U price;
5: 
6:     Product(){
7: 
8:     }
9: }
```

- **Linha 1 (`public class Product<T, U> {`):**
  - `public`: A classe `Product` é pública, permitindo que o `Main.java` e qualquer outro arquivo do projeto a utilizem.
  - `class Product`: Cria o molde chamado "Produto".
  - `<T, U>`: **A grande novidade!** Entre os sinais de menor e maior (`< >`), colocamos **dois parâmetros genéricos** separados por vírgula.
    - `T` representa o tipo do primeiro dado (o item).
    - `U` representa o tipo do segundo dado (o preço).
    - Eles são **totalmente independentes**: `T` pode ser uma `String` enquanto `U` pode ser um `Double`!
  - `{`: Abre as chaves da classe.

- **Linha 2 (Linha em branco):**
  - Espaçamento estético para legibilidade.

- **Linha 3 (`T item;`):**
  - `T`: O tipo da variável `item`. Como ainda não sabemos o tipo exato, usamos o coringa `T`. Se o cliente criar um produto como `Product<String, Double>`, este campo vira automaticamente um `String item;`.
  - `item`: Nome do atributo de instância que guardará a identificação ou descrição do produto.
  - `;`: Ponto e vírgula obrigatório para encerrar a declaração da variável.

- **Linha 4 (`U price;`):**
  - `U`: O tipo da variável `price`. Se o cliente criar um produto como `Product<String, Double>`, este campo vira automaticamente um `Double price;`.
  - `price`: Nome do atributo de instância que guardará o preço do produto.
  - `;`: Encerra a declaração da variável.

- **Linha 5 (Linha em branco):**
  - Separa os atributos do construtor.

- **Linha 6 (`Product(){`):**
  - `Product`: Nome do **Construtor** da classe. Construtores em Java sempre têm o **exato mesmo nome da classe** e não possuem nenhum tipo de retorno (nem mesmo `void`).
  - `()`: Por enquanto, o Bro Code abriu os parênteses vazios enquanto digitava. No próximo passo, ele adicionará os parâmetros para receber os valores e atribuir a `item` e `price`.
  - `{`: Abre o bloco de código do construtor.

- **Linha 7 (Linha em branco):**
  - Espaço interno reservado para as instruções do construtor.

- **Linha 8 (`}`):**
  - Fecha o corpo do construtor `Product`.

- **Linha 9 (`}`):**
  - Fecha a classe `Product`.

---

## 5. Por Que Usar `<T, U>`? A Ciência dos Múltiplos Tipos Genéricos

### A Convenção de Letras no Java (`T`, `U`, `V`, `K`, `V`, `E`)

No Java, qualquer letra ou palavra válida poderia ser usada dentro dos diamantes (ex: `<TipoItem, TipoPreco>`). Porém, a comunidade Java e a Oracle adotaram **letras maiúsculas únicas por convenção mundial**:

| Letra | Significado em Inglês | Onde é comumente usada |
| :---: | :--- | :--- |
| **`T`** | **T**ype (Tipo geral) | O coringa padrão número 1 para qualquer classe ou método genérico. |
| **`U`**, **`V`** | Próximas letras do alfabeto | Usadas como segundo e terceiro coringas quando `T` já está em uso (como no nosso `Product<T, U>`). |
| **`E`** | **E**lement (Elemento) | Usada em coleções como `List<E>`, `ArrayList<E>`, `Set<E>`. |
| **`K`** | **K**ey (Chave) | Usada em mapas como `Map<K, V>` e dicionários. |
| **`V`** | **V**alue (Valor) | Usada em pares chave-valor com o `K` (`Map<K, V>`). |
| **`N`** | **N**umber (Número) | Usada quando o tipo obrigatoriamente representa valores numéricos. |

---

### A Diferença Entre `<T>` (1 Tipo) e `<T, U>` (2 Tipos Independentes)

Pense na diferença prática:

- Se escrevêssemos:
  ```java
  public class Product<T> {
      T item;
      T price;
  }
  ```
  Se passássemos `Product<String>`, **tanto o `item` quanto o `price` teriam que ser String**! O preço teria que ser `"19.99"` como texto, o que é péssimo para fazer cálculos matemáticos.
  Se passássemos `Product<Double>`, o item teria que ser um número decimal, o que não faz sentido para o nome de um produto!

- Ao escrevermos:
  ```java
  public class Product<T, U> {
      T item;
      U price;
  }
  ```
  Nós damos **liberdade total e independente** para quem for usar a classe! O `T` pode ser uma coisa e o `U` pode ser outra totalmente diferente.

---

### Exemplos da Vida Real: O Que Poderemos Guardar em `Product<T, U>`?

Veja como uma única classe `Product<T, U>` atende todos esses cenários reais:

```java
// 1. Um produto físico comum de supermercado:
// Item é String ("Maçã"), Preço é Double (4.50)
Product<String, Double> p1 = new Product<>("Maçã", 4.50);

// 2. Um item de jogo de RPG onde o preço é pago em moedas de ouro inteiras:
// Item é String ("Espada Mágica"), Preço é Integer (150 moedas)
Product<String, Integer> p2 = new Product<>("Espada Mágica", 150);

// 3. Um sistema industrial de estoque por código de barras numérico:
// Item é um código de barras Integer (789102), Preço é Double (89.90)
Product<Integer, Double> p3 = new Product<>(789102, 89.90);
```

Tudo isso com o **mesmo arquivo `Product.java`**, com zero duplicação de código e 100% de segurança de tipos garantida pelo compilador!

---

## 6. Previsão de Saída do Console e Estado do Compilador

| Arquivo | Estado de Compilação | Saída no Console | Explicação |
| :--- | :---: | :---: | :--- |
| **`Product.java`** | ✅ Compila com Sucesso (`exit code 0`) | *(nenhuma)* | O arquivo possui sintaxe 100% válida. O construtor vazio ainda não faz nada, mas é perfeitamente legal para o compilador. |
| **`Main.java`** | ✅ Compila com Sucesso (`exit code 0`) | *(nenhuma)* | O método `main` está atualmente com seu corpo limpo (apenas comentários explicativos). Ao rodar, ele não imprime nada e finaliza imediatamente. |

---

## 7. O Que Vem a Seguir no Construtor e nos Próximos Segundos do Vídeo

Observe que no Momento 4 (minuto `10:48:37`), o Bro Code deixou o construtor assim:
```java
Product(){

}
```
Nos próximos segundos do vídeo, ele vai transformar esse construtor padrão em um construtor parametrizado para receber os valores na criação do objeto:
```java
Product(T item, U price){
    this.item = item;
    this.price = price;
}
```
E em seguida, ele adicionará os métodos getters (`getItem()` e `getPrice()`) e instanciará produtos no `Main.java` para testar!
