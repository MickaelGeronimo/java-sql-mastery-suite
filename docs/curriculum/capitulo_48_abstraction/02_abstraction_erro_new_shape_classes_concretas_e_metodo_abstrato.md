# Curso de Java — Capítulo 48: Abstração (`abstraction 🌫️`)
## Parte 2: O Erro do `new Shape()`, as Classes Concretas e o Método Abstrato `area()`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:52:30` até `07:55:50` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a demonstração do erro didático ao tentar instanciar uma classe abstrata (`new Shape();`), a instanciação bem-sucedida das classes concretas ([`Circle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), [`Triangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java) e [`Rectangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java)), e a declaração do primeiro **Método Abstrato** da história do curso: `abstract double area(); // ABSTRACT` dentro de [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java).  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (janelas de erro em pop-up, sublinhados vermelhos, seleções azuis com o mouse, abas e códigos) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Cena: Da Proibição à Obrigação Contratual

Nesta sequência de 5 capturas de tela, o professor Bro Code nos leva por três momentos cruciais da Orientação a Objetos:

1. **A Prova do Erro (`07:54:17`):** Ele digita `Shape shape = new Shape();` em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) para comprovar a Regra 3 dos comentários. A IDE bloqueia imediatamente com um erro vermelho: `'Shape' is abstract; cannot be instantiated`!
2. **A Salvação com Classes Concretas (`07:55:13`):** Ele apaga o erro e cria instâncias reais de [`Circle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), [`Triangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java) e [`Rectangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java). O Java aceita tudo com tranquilidade porque formas específicas são concretas!
3. **A Chegada do Método Abstrato (`07:55:47`):** Ele volta para [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) e declara:
   ```java
   abstract double area(); // ABSTRACT
   ```
   Um método sem corpo `{ }`, terminado em ponto e vírgula `;`, que funciona como uma lei irrevogável para todas as classes filhas!

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na tela:

### Imagem 1 (`media_1791359020398.png`): Conectando a Herança
* **Aba Ativa:** `Circle.java`.
* **Editor de Código:** Linhas 1 e 2:
  ```java
  public class Circle extends Shape{
  }
  ```
* **Gesto com o Mouse:** O professor seleciona com o mouse as palavras **`extends Shape`** com uma barra azul de destaque. Ele está mostrando que o círculo é uma extensão direta da classe abstrata `Shape`.

### Imagem 2 (`media_1791359040234.png` — 07:54:17): O Grande Erro do `new Shape()`
* **Aba Ativa:** `Main.java`.
* **Editor de Código:** Na linha 12, o professor acabou de digitar:
  ```java
  Shape shape = new Shape();
  ```
* **O Alarme Vermelho:** A palavra `Shape()` do lado direito do igual está com um **sublinhado ondulado vermelho**, e há um ponto vermelho de erro no gutter da linha 12.
* **O Balão Pop-up de Inspeção da IDE:** O IntelliJ abre uma janela flutuante escura com o aviso formal do compilador:
  > **`'Shape' is abstract; cannot be instantiated`**  
  > *(Shape é abstrata; não pode ser instanciada)*
  > Opção de correção sugerida: `Make 'Shape' not abstract`.

### Imagem 3 (`media_1791359069517.png`): As 3 Classes Concretas Criadas
* **Editor de Código:** O professor substitui a linha do erro por três instanciações válidas:
  ```java
  12         Circle circle = new Circle();
  13         Triangle triangle = new Triangle();
  14         Rectangle rectangle = new Rectangle();
  ```
* **Gesto com o Mouse:** A palavra **`circle`** da linha 12 está selecionada em azul.
* **Sinalizador Visual:** Nenhum erro vermelho! O código é 100% válido.

### Imagem 4 (`media_1791359084990.png` — 07:55:13): O Código Limpo no `Main`
* As linhas 12 a 14 aparecem estabilizadas sem avisos. O cursor pisca no final da linha 12 (`new Circle();|`).
* O professor reforça verbalmente: *"Classes concretas podem ser instanciadas com `new`; classes abstratas NUNCA!"*.

### Imagem 5 (`media_1791359116335.png` — 07:55:47): O Método Abstrato em `Shape.java`
* **Aba Ativa:** Volta para [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java).
* **Editor de Código:**
  ```java
  1 public abstract class Shape {
  2 
  3     abstract double area(); // ABSTRACT
  4 }
  ```
* **Destaques Visuais:**
  * O cursor de texto pisca logo após o comentário `// ABSTRACT|`.
  * Na linha 1, na margem esquerda (gutter), aparece o ícone especial `@` com setinha para baixo **`↓`**, indicando que este método abstrato será herdado e sobrescrito pelas subclasses.
  * O método **não possui chaves `{ }`**; ele termina diretamente com um ponto e vírgula `;`!

---

## 3. Por Que o Java Proíbe `new Shape()`? (Dissecando o Erro da Imagem 2)

Lembre-se da 3ª Regra anotada pelo professor em `Main.java`:
> **`// Abstract classes CAN'T be instantiated directly`**

Quando você escreve `new Shape()`, você está pedindo para a JVM:
*"Por favor, aloque memória física e fabrique um objeto do tipo Shape"*.

A JVM se recusa terminantemente e diz:
*"O que é um Shape? Quanto de largura ele tem? Qual é o raio dele? Como eu calculo a área dele? Eu não sei! Shape é apenas uma ideia abstrata, um conceito geral! Você só pode fabricar formas reais do mundo real (como Circle, Triangle ou Rectangle)!"*.

```
┌────────────────────────────────────────────────────────┐
│                   A LEI DA ABSTRAÇÃO                   │
├────────────────────────────────────────────────────────┤
│ Shape shape = new Shape();         ---> ❌ ERRO FATAL! │
│ Circle circle = new Circle();       ---> ✅ PERFEITO!   │
│ Triangle triangle = new Triangle(); ---> ✅ PERFEITO!   │
│ Rectangle rect = new Rectangle();   ---> ✅ PERFEITO!   │
└────────────────────────────────────────────────────────┘
```

---

## 4. O Que É um Método Abstrato? (`abstract double area();`)

Olhe com uma lupa para a linha 3 que o professor digitou na Imagem 5:

```java
abstract double area(); // ABSTRACT
```

Repare no detalhe mais chocante para um iniciante: **ONDE ESTÃO AS CHAVES `{ }`?**
* Em todos os métodos normais que você aprendeu até hoje, você escrevia:
  ```java
  void andar() {
      // código aqui dentro
  }
  ```
* Mas no método abstrato, **NÃO EXISTEM CHAVES `{ }`**! Ele termina seco com um ponto e vírgula **`;`**!

### Por que ele não tem código dentro?
Pense como um matemático:
* Qual é a fórmula para calcular a área de "uma forma genérica"? **NÃO EXISTE!**
* Um círculo calcula área com $\pi \cdot r^2$.
* Um retângulo calcula área com $\text{largura} \cdot \text{altura}$.
* Um triângulo calcula área com $\frac{\text{base} \cdot \text{altura}}{2}$.

Como a classe mãe [`Shape`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) não tem como adivinhar qual será a fórmula de cada filho, ela simplesmente faz o papel de líder e dita uma **ordem contratual**:

> *"Atenção, todas as minhas filhas (`Circle`, `Triangle`, `Rectangle`)! Eu sou a mãe `Shape`. Eu não sei qual é a fórmula de vocês, mas eu EXIJO que cada uma de vocês crie o seu próprio método `double area()`! Se alguma de vocês esquecer de criar, o compilador não vai deixar o programa rodar!"*.

---

## 5. A Reação em Cadeia no Compilador

Assim que você declara `abstract double area();` em [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java), acontece algo fascinante em todo o projeto:

Se a classe [`Circle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java) colocar `extends Shape` e **não** implementar o método `area()`, o compilador Java imediatamente trava tudo com o seguinte erro:

```text
Circle.java:1: error: Circle is not abstract and does not override abstract method area() in Shape
public class Circle extends Shape{
       ^
```

**Tradução da bronca do Java:**
*"Ei, `Circle`! Você disse que é filha de `Shape`, mas `Shape` tem uma lei chamada `area()` que você não cumpriu! Ou você implementa a sua fórmula de `area()` agora mesmo, ou você também terá que virar uma classe abstrata!"*.

Nas próximas etapas do vídeo, nós veremos o professor implementar a fórmula matemática em cada uma das 3 classes filhas!

---

## 6. Análise Código por Código e Linha por Linha

### A. O Arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Linhas 1 a 16)
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
11:         Circle circle = new Circle();
12:         Triangle triangle = new Triangle();
13:         Rectangle rectangle = new Rectangle();
14: 
15:     }
16: }
```
* **Linhas 1 a 9:** Declaração e comentários sobre abstração.
* **Linha 11 (`Circle circle = new Circle();`):** Instancia com sucesso o objeto círculo na memória Heap.
* **Linha 12 (`Triangle triangle = new Triangle();`):** Instancia com sucesso o objeto triângulo.
* **Linha 13 (`Rectangle rectangle = new Rectangle();`):** Instancia com sucesso o objeto retângulo.
* *(Nota: no momento da foto, `new Shape()` foi removido por ser ilegal no Java).*

---

### B. O Arquivo [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) (Linhas 1 a 4)
```java
1:  public abstract class Shape {
2:  
3:      abstract double area(); // ABSTRACT
4:  }
```
* **Linha 1 (`public abstract class Shape {`):** Declara a classe mãe abstrata.
* **Linha 2:** Linha em branco.
* **Linha 3 (`abstract double area(); // ABSTRACT`):**
  * `abstract`: Declara que o método é abstrato (sem implementação).
  * `double`: Promete que o cálculo da área devolverá um número com casas decimais (ex: `78.5398`).
  * `area()`: O nome obrigatório do método.
  * `;`: Ponto e vírgula que encerra a declaração sem chaves `{ }`.
* **Linha 4 (`}`):** Encerra a classe `Shape`.

---

## 7. Tabela Comparativa: Concreto vs Abstrato

| Conceito | Pode usar `new` para criar objeto? | Possui código real / corpo `{ }`? | Exemplo no Nosso Projeto |
| :--- | :---: | :---: | :--- |
| **Classe Abstrata** | ❌ **NUNCA!** | Pode ter métodos com ou sem corpo. | [`Shape`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) |
| **Classe Concreta** | ✅ **SIM!** | Todos os métodos devem ter corpo. | [`Circle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), [`Triangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java), [`Rectangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java) |
| **Método Concreto** | *(Executado via objeto)* | ✅ **SIM** `{ ... código ... }` | `void move()`, `.toString()` |
| **Método Abstrato** | *(Não executado diretamente)* | ❌ **NÃO** (Termina com `;`) | `abstract double area();` |

---

## 8. Resumo para Fixação no Google Docs

1. **Classes abstratas são proibidas de usar `new`:** Tentar fazer `new Shape()` causa o erro imediato `'Shape' is abstract; cannot be instantiated`.
2. **Classes concretas são livres:** `new Circle()`, `new Triangle()` e `new Rectangle()` funcionam perfeitamente.
3. **Métodos abstratos não têm corpo:** São declarados com `abstract tipo nome();` e terminam com ponto e vírgula `;`.
4. **O contrato inquebrável:** Todo filho que herdar de uma classe abstrata é **obrigado pelo compilador** a implementar os métodos abstratos do pai com suas próprias fórmulas matemáticas!
