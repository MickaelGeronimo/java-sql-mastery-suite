# Curso de Java — Capítulo 48: Abstração (`abstraction 🌫️`)
## Parte 8: O Grande Clímax — Alimentando os Objetos em `Main.java`, a Impressão das Áreas no Console e a Revisão de `abstract`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:00:25` até `08:01:20` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a execução prática do polimorfismo e da abstração: como o professor Bro Code ajusta o [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) fornecendo os valores nos construtores (`new Circle(3)`, `new Triangle(4, 5)`, `new Rectangle(6, 7)`), substitui as chamadas de `display()` por `System.out.println(...area())`, confere no terminal os resultados matemáticos exatos de cada figura geométrica, e retorna para [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) e [`Triangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java) para uma revisão conceitual de fechamento.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (linhas selecionadas em azul, abas ativas, saídas no terminal em verde neon, realces de palavras-chave e navegação entre arquivos) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Demonstração: O Quebra-Cabeça Está Montado!

Nas partes anteriores, preparamos toda a arquitetura:
1. Criamos a superclasse abstrata [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) com o método abstrato `area()` e o método concreto `display()`.
2. Especializamos [`Circle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), [`Triangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java) e [`Rectangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java) com seus próprios atributos e construtores.

Nesta sequência de 5 capturas de tela, presenciamos o grande momento em que o código ganha vida no computador:

1. **Alimentando os Construtores (Imagem 1):** O professor resolve os erros de `Main.java` passando as medidas numéricas: `Circle(3)`, `Triangle(4, 5)` e `Rectangle(6, 7)`.
2. **Substituindo `display()` por `area()` (Imagens 1 e 2):** Ele apaga as chamadas antigas e escreve `System.out.println(...area())`.
3. **A Execução Triunfal no Console (Imagem 3):** O programa roda e imprime os três valores calculados pelas fórmulas individuais:
   * Círculo: `28.274333882308138`
   * Triângulo: `10.0`
   * Retângulo: `42.0`
4. **A Revisão Teórica Final (Imagens 4 e 5):** O professor volta em `Shape.java` e `Triangle.java` destacando a palavra `abstract` para fixar a regra suprema de que o molde geral define o **"O QUÊ"**, e cada classe filha define o **"COMO"**.

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na tela:

### Imagem 1 (`media_1791359521504.png` — 08:00:32): Alimentando os Objetos e Selecionando Linhas
* **Aba Ativa:** `Main.java` (linha azul sob o título).
* **Editor de Código (Linhas 11 a 18):**
  * Linha 11: `Circle circle = new Circle(3);` -> Raio = `3`.
  * Linha 12: `Triangle triangle = new Triangle(4, 5);` -> Base = `4`, Altura = `5`.
  * Linha 13: `Rectangle rectangle = new Rectangle(6, 7);` -> Comprimento = `6`, Largura = `7`.
  * Linha 16: `circle.display();`
  * **Seleção Azul com o Mouse:** As linhas 17 e 18 (`triangle.display();` e `rectangle.display();`) estão totalmente cobertas por uma faixa azul de seleção. O autor vai apagá-las para colocar a impressão das áreas!

### Imagem 2 (`media_1791359542634.png` — 08:00:45): Preparando os `println`
* **Editor de Código:** O autor substituiu as linhas por três comandos:
  ```java
  16          System.out.println(circle.area());
  17          System.out.println(circle.area());
  18          System.out.println(circle.area());
  ```
* O cursor pisca no fim da linha 18 (`circle.area());|`).
* Ele colou a mesma linha três vezes e agora vai mudar o nome do objeto nas linhas 17 e 18 para `triangle` e `rectangle`.

### Imagem 3 (`media_1791359553613.png` — 08:00:55): O Terminal Aberto e o Sucesso Matemático!
* **Editor de Código:**
  ```java
  16          System.out.println(circle.area());
  17          System.out.println(triangle.area());
  18          System.out.println(rectangle.area());
  ```
  *(A palavra `rectangle` na linha 18 está levemente destacada com uma caixinha cinza).*
* **Painel Inferior ("Run / Console"):** O terminal abriu exibindo os resultados em verde neon:
  ```text
  28.274333882308138
  10.0
  42.0

  Process finished with exit code 0
  ```

### Imagem 4 (`media_1791359570528.png` — 08:01:05): O Destaque da Palavra `abstract` em `Shape.java`
* **Aba Ativa:** O autor muda para `Shape.java`.
* **Editor de Código:** Na linha 1 (`public abstract class Shape {`), a palavra **`abstract`** está selecionada com uma barra azul brilhante!
* **Gutter:** Na margem esquerda da linha 1 e 3, ícones verdes circulares confirmam que a classe e o método abstrato possuem subclasses que os implementam (`Circle`, `Triangle`, `Rectangle`).

### Imagem 5 (`media_1791359594811.png` — 08:01:15): A Inspeção de Fechamento em `Triangle.java`
* **Aba Ativa:** O autor clica na aba `Triangle.java`.
* O mouse repousa entre as abas `Triangle.java` e `Rectangle.java`.
* O autor revisa a beleza da arquitetura: o triângulo herda de `Shape`, implementa `area()` com `0.5 * base * height`, e tudo funciona de forma independente e elegante.

---

## 3. Dissecando a Matemática dos Resultados no Console

Vamos conferir na ponta do lápis se o computador fez a conta certa:

### 1. Círculo (`circle`)
* **Dados fornecidos:** `radius = 3`.
* **Fórmula em `Circle.java`:** `Math.PI * radius * radius`
* **Cálculo:**
  $$\pi \times 3 \times 3 = 3.141592653589793 \times 9 = \mathbf{28.274333882308138}$$
* **Resultado no console:** `28.274333882308138` (Exato!).

### 2. Triângulo (`triangle`)
* **Dados fornecidos:** `base = 4`, `height = 5`.
* **Fórmula em `Triangle.java`:** `0.5 * base * height`
* **Cálculo:**
  $$0.5 \times 4 \times 5 = 2.0 \times 5 = \mathbf{10.0}$$
* **Resultado no console:** `10.0` (Exato!).

### 3. Retângulo (`rectangle`)
* **Dados fornecidos:** `length = 6`, `width = 7`.
* **Fórmula em `Rectangle.java`:** `length * width`
* **Cálculo:**
  $$6 \times 7 = \mathbf{42.0}$$
* **Resultado no console:** `42.0` (Exato! Note que é `42.0` com casa decimal porque o método retorna `double`).

---

## 4. Análise Código por Código e Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

Abaixo está o código final exato de `Main.java` como visto na Imagem 3:

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // abstract = Used to define abstract classes and methods.
5:          //            Abstraction is the process of hiding implementation details
6:          //            and showing only the essential features;
7:          //            Abstract classes CAN'T be instantiated directly
8:          //            Can contain 'abstract' methods (which must be implemented)
9:          //            Can contain 'concrete' methods (which are inherited)
10: 
11:         Circle circle = new Circle(3);
12:         Triangle triangle = new Triangle(4, 5);
13:         Rectangle rectangle = new Rectangle(6, 7);
14: 
15: 
16:         System.out.println(circle.area());
17:         System.out.println(triangle.area());
18:         System.out.println(rectangle.area());
19:     }
20: }
```

### Explicação Passo a Passo:

* **Linhas 1 a 3:** Declara a classe pública `Main` e o ponto de entrada `main`. *(Nada no console).*
* **Linhas 4 a 9 (Comentários):** As 5 regras mestras da abstração deixadas pelo professor. *(Ignoradas pelo compilador).*
* **Linha 10:** Linha em branco.
* **Linha 11 (`Circle circle = new Circle(3);`):** Cria um objeto círculo na memória Heap com raio igual a `3`. *(Nada no console).*
* **Linha 12 (`Triangle triangle = new Triangle(4, 5);`):** Cria um objeto triângulo com base `4` e altura `5`. *(Nada no console).*
* **Linha 13 (`Rectangle rectangle = new Rectangle(6, 7);`):** Cria um objeto retângulo com comprimento `6` e largura `7`. *(Nada no console).*
* **Linhas 14 e 15:** Linhas em branco para organização visual.
* **Linha 16 (`System.out.println(circle.area());`):**
  * Chama o método `area()` do círculo, que calcula $\pi \cdot 3^2$.
  * **Saída no Console:**
    ```text
    28.274333882308138
    ```
* **Linha 17 (`System.out.println(triangle.area());`):**
  * Chama o método `area()` do triângulo, que calcula $0.5 \times 4 \times 5$.
  * **Saída no Console:**
    ```text
    10.0
    ```
* **Linha 18 (`System.out.println(rectangle.area());`):**
  * Chama o método `area()` do retângulo, que calcula $6 \times 7$.
  * **Saída no Console:**
    ```text
    42.0
    ```
* **Linhas 19 e 20 (`}` e `}`):** Fecham o método `main` e a classe `Main`.

---

## 5. Saída Fiel e Real no Console Nesta Etapa

Ao clicar no botão verde de execução `▶`:

```text
28.274333882308138
10.0
42.0

Process finished with exit code 0
```

* **Linha 1:** `28.274333882308138` (Área do círculo calculada com precisão total).
* **Linha 2:** `10.0` (Área do triângulo).
* **Linha 3:** `42.0` (Área do retângulo).
* **Linha 4:** `Process finished with exit code 0` (Sucesso absoluto da compilação e execução).

---

## 6. O Mapa Mental Completo da Abstração no Java

```
                            ┌────────────────────────────────────────┐
                            │          abstract class Shape          │
                            ├────────────────────────────────────────┤
                            │ + abstract double area();  [CONTRATO]  │
                            │ + void display();          [HERDADO]   │
                            └───────────────────┬────────────────────┘
                                                │ extends
                     ┌──────────────────────────┼──────────────────────────┐
                     │                          │                          │
        ┌────────────┴───────────┐ ┌────────────┴───────────┐ ┌────────────┴───────────┐
        │      class Circle      │ │     class Triangle     │ │    class Rectangle     │
        ├────────────────────────┤ ├────────────────────────┤ ├────────────────────────┤
        │ - double radius;       │ │ - double base, height; │ │ - double length, width;│
        │ + double area() {      │ │ + double area() {      │ │ + double area() {      │
        │     return π * r * r;  │ │     return 0.5 * b * h;│ │     return l * w;      │
        │   }                    │ │   }                    │ │   }                    │
        └────────────────────────┘ └────────────────────────┘ └────────────────────────┘
```

---

## 7. Resumo da Lição para Fixação no Google Docs

1. **A Classe Abstrata (`Shape`) É Um Molde Incompleto:**  
   Não pode ser criada com `new Shape()`. Ela serve como molde para organizar e padronizar todas as subclasses da família.
2. **Método Abstrato (`abstract double area();`):**  
   Não tem chaves `{}`. É uma obrigação universal: toda subclasse concreta **tem que implementar**.
3. **Método Concreto (`void display()`):**  
   Tem corpo `{}`. Todas as subclasses herdam automaticamente de graça.
4. **Independência Total das Subclasses:**  
   Cada subclasse tem suas próprias variáveis e suas próprias contas matemáticas, mas todas respondem pelo mesmo nome de método: `.area()`. Isso é a base do **Polimorfismo** e da **Orientação a Objetos profissional**!
