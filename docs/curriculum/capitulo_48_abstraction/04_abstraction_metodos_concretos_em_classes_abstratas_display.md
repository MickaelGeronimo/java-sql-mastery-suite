# Curso de Java — Capítulo 48: Abstração (`abstraction 🌫️`)
## Parte 4: Métodos Concretos em Classes Abstratas — A Adição de `display()` e a Chamada de `circle.display()`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:56:50` até `07:57:45` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a demonstração da Regra 5 da abstração: como uma classe abstrata pode conter **métodos concretos** (com corpo `{ }` e código funcional), como declarar o método `void display()` dentro de [`Shape.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java) e provar que todas as subclasses (como [`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java)) herdam esse comportamento de graça sem precisar escrever nada nelas.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (navegação rápida entre abas, ícones de execução, cursores e saídas de terminal) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Demonstração: O Melhor dos Dois Mundos na Mesma Classe

Muitos iniciantes pensam que uma classe abstrata só pode ter coisas abstratas (incompletas). Mas aqui está uma das maiores surpresas do Java:

> **Uma classe abstrata pode misturar métodos abstratos (que não têm corpo) com métodos concretos (que têm código completo pronto para rodar)!**

Nesta sequência de 4 imagens, o professor Bro Code prova a Regra 5 que anotamos no início do capítulo:
1. **Em [`Shape.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java):** Ele cria o método `void display(){ System.out.println("This is a shape"); }`.
2. **Nas subclasses ([`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java) e [`Rectangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Rectangle.java)):** Ele mostra que nós **não precisamos digitar nada** sobre `display()`.
3. **Em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):** Ele chama `circle.display();` e aperta o botão verde de execução. O círculo executa perfeitamente a frase herdada da mãe!

---

## 2. Radiografia Visual Minuciosa das 4 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na tela:

### Imagem 1 (`media_1791359224092.png`): O Método Concreto em `Shape.java`
* **Aba Ativa:** `Shape.java`.
* **Editor de Código:** Linhas 1 a 8:
  ```java
  1 public abstract class Shape {
  2 
  3     abstract double area(); // ABSTRACT
  4 
  5     void display(){ // CONCRETE
  6         System.out.println("This is a shape");
  7     }
  8 }
  ```
* **Destaques Visuais:**
  * Linha 3: O método abstrato `area()` (sem chaves, com `;`).
  * Linhas 5 a 7: O método concreto `display()` (com chaves `{ }` e um `println` real dentro!).
  * O cursor do mouse paira sobre a aba `Circle.java` na parte superior.

### Imagem 2 (`media_1791359238476.png` — 07:57:23): A Inspeção em `Rectangle.java`
* **Aba Ativa:** `Rectangle.java`.
* O professor clica nesta aba para conferir que ela está limpa, estendendo `Shape` e com a sua própria versão de `area()` retornando `0`.

### Imagem 3 (`media_1791359247817.png` — 07:57:26): A Inspeção em `Circle.java`
* **Aba Ativa:** `Circle.java`.
* O professor mostra que dentro de `Circle.java` **não existe nenhum método chamado `display()`**! A classe só tem `area()`.

### Imagem 4 (`media_1791359267010.png` — 07:57:40): A Chamada em `Main.java` e o Botão Run
* **Aba Ativa:** `Main.java`.
* **Editor de Código:** Na linha 16, o professor escreveu:
  ```java
  11         Circle circle = new Circle();
  12         Triangle triangle = new Triangle();
  13         Rectangle rectangle = new Rectangle();
  14 
  15 
  16         circle.display();
  17     }
  18 }
  ```
* **Gesto com o Mouse:** A setinha do mouse paira no canto superior direito exatamente sobre o botão verde de execução rápida `▶` (`Run 'Main.java' Shift+F10`).

---

## 3. Dissecando a Coexistência: Abstrato vs Concreto na Mesma Classe

Olhe para a estrutura de [`Shape.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java). Ela é um exemplo perfeito de arquitetura orientada a objetos:

```java
public abstract class Shape {

    // 1. MÉTODO ABSTRATO (Uma ORDEM para as filhas):
    abstract double area(); // Cada filha calcula do seu jeito

    // 2. MÉTODO CONCRETO (Um PRESENTE para as filhas):
    void display(){
        System.out.println("This is a shape"); // Todas as filhas herdam pronto
    }
}
```

### Quando usar cada tipo de método em uma classe abstrata?

1. **Use Método Abstrato (`abstract`):**
   * Quando o comportamento for **obrigatório**, mas a lógica for **completamente diferente para cada filho**.
   * Exemplo: calcular a área (círculo usa raio, retângulo usa lados). A mãe não sabe a fórmula de cada um, então delega a implementação.
2. **Use Método Concreto (sem `abstract`):**
   * Quando o comportamento for **exatamente o mesmo para todos os filhos**.
   * Exemplo: mostrar a mensagem `"This is a shape"`, ou salvar a data de criação no banco de dados. A mãe já escreve o código uma única vez e todas as filhas herdam sem esforço!

---

## 4. O Rastreamento da JVM: O Que Acontece em `circle.display()`?

Quando a linha 16 de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) é executada:

```
               CHAMADA: circle.display()
               ─────────────────────────
1. A JVM olha para a classe Circle.
2. Pergunta: "Circle tem algum método chamado display() dentro dele?"
3. Resposta: NÃO! Circle só tem o método area().
4. A JVM sobe a árvore genealógica até a mãe: Shape.
5. Pergunta: "Shape tem o método display()?"
6. Resposta: SIM! E ele é um método concreto com código funcional!
7. A JVM executa: System.out.println("This is a shape");
8. O console imprime: "This is a shape"
```

É a beleza da **Herança** trabalhando em conjunto com a **Abstração**!

---

## 5. Análise Código por Código e Linha por Linha

### A. O Arquivo [`Shape.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java) (Linhas 1 a 8)
```java
1:  public abstract class Shape {
2:  
3:      abstract double area(); // ABSTRACT
4:  
5:      void display(){ // CONCRETE
6:          System.out.println("This is a shape");
7:      }
8:  }
```

* **Linha 1:** Declara a classe abstrata `Shape`.
* **Linha 3:** O método abstrato `area()`. Sem corpo, obriga as subclasses a implementarem.
* **Linha 5 (`void display(){ // CONCRETE`):**
  * `void`: Não retorna nenhum valor.
  * `display()`: Nome do método de exibição.
  * `{`: Abre o bloco de código funcional.
* **Linha 6 (`System.out.println("This is a shape");`):** Imprime a mensagem de identificação no console.
* **Linha 7 e 8 (`}` e `}`):** Fecham o método e a classe.

---

### B. O Arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Linhas 1 a 18)
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
15: 
16:         circle.display();
17:     }
18: }
```

* **Linhas 11 a 13:** Instanciam os 3 objetos concretos. *(Nada sai no console).*
* **Linha 16 (`circle.display();`):**
  * O objeto `circle` executa o método herdado diretamente de `Shape`.
  * **Saída no Console:**
    ```text
    This is a shape
    ```
* **Linhas 17 e 18 (`}` e `}`):** Fecham o método `main` e a classe `Main`.

---

## 6. Saída Oficial do Console Nesta Etapa

Ao executar o programa pelo botão Run:

```text
This is a shape

Process finished with exit code 0
```

* **Linha 1:** `"This is a shape"` (Produzido pelo método concreto herdado).
* **Linha 2:** `Process finished with exit code 0` (Sucesso absoluto da execução).

---

## 7. Tabela Comparativa: Os Dois Tipos de Métodos em Classes Abstratas

| Característica | Método Abstrato (`abstract double area();`) | Método Concreto (`void display(){ ... }`) |
| :--- | :--- | :--- |
| **Tem a palavra `abstract`?** | ✅ Sim | ❌ Não |
| **Tem chaves `{ }` e código dentro?** | ❌ Não (Termina com `;`) | ✅ Sim `{ System.out.println(...); }` |
| **As subclasses são forçadas a reescrever?** | ✅ **SIM! É uma obrigação contratual.** | ❌ Não! Elas herdam pronto de graça. |
| **Objetivo:** | Exigir uma funcionalidade que varia de filho para filho. | Fornecer uma funcionalidade comum a todos os filhos sem repetir código. |

---

## 8. Resumo para Fixação no Google Docs

1. **Classes abstratas são híbridas:** Elas podem misturar métodos incompletos (abstratos) com métodos prontos (concretos).
2. **Método abstrato = Regra obrigatória:** A superclasse dita o que fazer, e as filhas são forçadas a decidir como fazer (`area()`).
3. **Método concreto = Reaproveitamento de código:** A superclasse já entrega a solução pronta, e as filhas usam imediatamente (`display()`).
4. **`circle.display()` funcionou com perfeição:** Mostrando que o Círculo é, ao mesmo tempo, obrigado a ter sua própria área e livre para desfrutar dos métodos prontos herdados da mãe `Shape`!
