# Curso de Java — Capítulo 48: Abstração (`abstraction 🌫️`)
## Parte 7: Especializando o Triângulo e o Retângulo — Atributos, Construtores com `this` e as Fórmulas Reais de Área

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:59:10` até `08:00:20` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) como o professor Bro Code especializa as classes filhas [`Triangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Triangle.java) e [`Rectangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Rectangle.java): definindo os atributos geométricos necessários para cada uma (`base`, `height`, `length`, `width`), construindo os construtores com atribuição via `this`, e substituindo os stubs `return 0;` pelas fórmulas matemáticas reais de cálculo de área. Inclui um alerta pedagógico de ouro sobre divisão inteira em Java (`1 / 2` vs `0.5`).  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (navegação rápida entre abas, cursores piscando, realces roxos de variáveis de instância, tooltips de caminho de arquivo e transição com o mouse para `Main.java`) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Demonstração: Cada Forma com Sua Identidade Matemática

Na Parte 6, vimos a classe [`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java) ganhar o atributo `radius` e implementar a fórmula $\pi \cdot r^2$ com `Math.PI`.

Agora, nesta sequência de 5 capturas de tela, o professor Bro Code repete esse mesmo padrão de excelência nas outras duas subclasses:

1. **[`Triangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Triangle.java) (Imagens 1 e 2):**
   * Adiciona `double base;` e `double height;`.
   * Cria o construtor `Triangle(double base, double height)`.
   * Substitui `return 0;` por `return 0.5 * base * height;`.
2. **[`Rectangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Rectangle.java) (Imagens 3, 4 e 5):**
   * Adiciona `double length;` e `double width;`.
   * Cria o construtor `Rectangle(double length, double width)`.
   * Substitui `return 0;` por `return length * width;`.
3. **A Transição Final:** O professor clica na aba [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) para finalmente alimentar cada objeto com números reais e imprimir o cálculo de cada área no console!

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando pelo Google Docs e não tem acesso visual às capturas de tela:

### Imagem 1 (`media_1791359412969.png` — 07:59:10): Criando os Atributos do Triângulo
* **Aba Ativa:** `Triangle.java` (marcada com linha azul sob o título).
* **Abas Visíveis no Topo:** `Main.java`, `Shape.java`, `Circle.java`, `Triangle.java` e `Rectangle.java`.
* **Editor de Código:**
  * Linhas 3 e 4: O autor digitou `double base;` e `double height;`.
  * Linha 6: O cursor de texto pisca em uma linha vazia (`|`), pronto para iniciar o construtor.
  * Linha 9: Na margem esquerda (gutter), vemos o ícone `I↑` confirmando que `area()` implementa o contrato herdado de `Shape`.
  * Linha 10: O método ainda tem o tapa-buraco temporário `return 0;`.

### Imagem 2 (`media_1791359437475.png` — 07:59:35): O Triângulo Concluído e a Troca de Aba
* **Editor de Código:**
  * Linhas 6 a 9: Construtor `Triangle(double base, double height)` implementado com `this.base = base;` e `this.height = height;`.
  * Linha 13: O retorno agora é `return 0.5 * base * height;`. As palavras `base` e `height` aparecem em **roxo/magenta**, indicando que são variáveis de instância do objeto!
* **Ação do Mouse:** O autor move a setinha do mouse para o topo e repousa sobre a aba `Rectangle.java`.
* **Tooltip da IDE:** Aparece uma caixinha cinza com o caminho completo do arquivo:
  `C:\Users\HP\IdeaProjects\MyFirstProject\src\Rectangle.java`.

### Imagem 3 (`media_1791359450158.png` — 07:59:45): Entrando no Retângulo
* **Aba Ativa:** `Rectangle.java` (agora com a linha azul de seleção).
* **Editor de Código:**
  * A palavra `Rectangle` na linha 1 está selecionada em azul (`public class [Rectangle] extends Shape{`).
  * Linhas 3 e 4: O autor digitou `double length;` (comprimento) e `double width;` (largura).
  * Linha 6: O cursor pisca pronto para receber o construtor.
  * Linhas 8 a 11: O método `area()` ainda contém `return 0;`.

### Imagem 4 (`media_1791359466630.png` — 08:00:00): Escrevendo o Construtor do Retângulo
* **Editor de Código:**
  * Linhas 6 a 8: O autor digitou o cabeçalho do construtor:
    ```java
    Rectangle(double length, double width){
        |
    }
    ```
  * O cursor pisca indentado na linha 7, prestes a digitar os comandos `this`.

### Imagem 5 (`media_1791359489626.png` — 08:00:17): O Retângulo Completo e o Retorno a `Main.java`
* **Editor de Código:**
  * Linhas 6 a 9: Construtor finalizado com `this.length = length;` e `this.width = width;`.
  * Linha 13: A fórmula da área foi atualizada para `return length * width;`.
* **Ação do Mouse:** O cursor do mouse está no topo esquerdo, clicando na primeira aba: [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)!

---

## 3. Alerta Pedagógico de Ouro: Por Que Usar `0.5` em Vez de `1 / 2`?

Na matemática da escola, a área do triângulo é ensinada como:

$$\text{Área} = \frac{\text{base} \times \text{altura}}{2} \quad \text{ou} \quad \frac{1}{2} \times \text{base} \times \text{altura}$$

Por que o professor Bro Code escreveu **`0.5 * base * height;`** em vez de **`1 / 2 * base * height;`**?

> [!CAUTION]
> **A Armadilha da Divisão Inteira no Java:**  
> No Java, se você divide dois números inteiros (`1` e `2`), o Java faz uma **divisão inteira truncada**!  
> $$1 / 2 = 0 \quad (\text{o Java joga as casas decimais no lixo!})$$  
> Se o autor tivesse escrito:
> ```java
> return (1 / 2) * base * height; // ERRADO! 1/2 vira 0!
> ```
> O resultado seria SEMPRE **$0 \times \text{base} \times \text{height} = 0.0$**!  
> Para evitar esse erro clássico de iniciante, o autor usa sabiamente o número com ponto flutuante **`0.5`** (ou poderia usar `(base * height) / 2.0`). Isso força o computador a calcular tudo com precisão decimal (`double`)!

---

## 4. Análise Código por Código e Linha por Linha de [`Triangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Triangle.java)

Abaixo está o código final do arquivo `Triangle.java` após a edição do professor:

```java
1:  public class Triangle extends Shape{
2:  
3:      double base;
4:      double height;
5:  
6:      Triangle(double base, double height){
7:          this.base = base;
8:          this.height = height;
9:      }
10: 
11:     @Override
12:     double area(){
13:         return 0.5 * base * height;
14:     }
15: }
```

### Explicação Passo a Passo:

* **Linha 1 (`public class Triangle extends Shape{`):** Declara a classe pública `Triangle` como filha (herdeira) de [`Shape`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java). *(Nada no console).*
* **Linha 2:** Linha em branco.
* **Linha 3 (`double base;`):** Cria a variável de instância `base` para armazenar o comprimento da base do triângulo. *(Nada no console).*
* **Linha 4 (`double height;`):** Cria a variável de instância `height` para armazenar a altura do triângulo. *(Nada no console).*
* **Linha 5:** Linha em branco.
* **Linha 6 (`Triangle(double base, double height){`):** Construtor da classe. Exige que quem for instanciar um triângulo informe obrigatoriamente a base e a altura. *(Nada no console).*
* **Linha 7 (`this.base = base;`):** Diferencia o atributo do objeto (`this.base`, cor roxa) do parâmetro que chegou no método (`base`, cor laranja/branca). *(Nada no console).*
* **Linha 8 (`this.height = height;`):** Guarda a altura recebida no atributo da instância. *(Nada no console).*
* **Linha 9 (`}`):** Encerra o construtor.
* **Linha 10:** Linha em branco.
* **Linha 11 (`@Override`):** Anotação que avisa ao compilador que estamos sobrescrevendo o método abstrato herdado de `Shape`. *(Nada no console).*
* **Linha 12 (`double area(){`):** Assinatura obrigatória do contrato. Na margem da IDE, exibe o ícone `I↑` (implements). *(Nada no console).*
* **Linha 13 (`return 0.5 * base * height;`):** Faz a multiplicação decimal: metade vezes a base vezes a altura, retornando a área exata do triângulo. *(Nada no console).*
* **Linhas 14 e 15 (`}` e `}`):** Fecham o método `area()` e a classe `Triangle`.

---

## 5. Análise Código por Código e Linha por Linha de [`Rectangle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Rectangle.java)

Abaixo está o código final do arquivo `Rectangle.java` após a edição do professor:

```java
1:  public class Rectangle extends Shape{
2:  
3:      double length;
4:      double width;
5:  
6:      Rectangle(double length, double width){
7:          this.length = length;
8:          this.width = width;
9:      }
10: 
11:     @Override
12:     double area(){
13:         return length * width;
14:     }
15: }
```

### Explicação Passo a Passo:

* **Linha 1 (`public class Rectangle extends Shape{`):** Declara a classe pública `Rectangle` herdando de [`Shape`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java). *(Nada no console).*
* **Linha 2:** Linha em branco.
* **Linha 3 (`double length;`):** Atributo de instância para o comprimento (lado maior ou base) do retângulo. *(Nada no console).*
* **Linha 4 (`double width;`):** Atributo de instância para a largura (altura) do retângulo. *(Nada no console).*
* **Linha 5:** Linha em branco.
* **Linha 6 (`Rectangle(double length, double width){`):** Construtor que recebe as duas medidas do retângulo na hora da criação do objeto. *(Nada no console).*
* **Linha 7 (`this.length = length;`):** Guarda o comprimento passado no atributo `length` do objeto na memória Heap. *(Nada no console).*
* **Linha 8 (`this.width = width;`):** Guarda a largura passada no atributo `width` do objeto na memória Heap. *(Nada no console).*
* **Linha 9 (`}`):** Encerra o construtor.
* **Linha 10:** Linha em branco.
* **Linha 11 (`@Override`):** Anotação oficial de implementação do método herdado. *(Nada no console).*
* **Linha 12 (`double area(){`):** Assinatura do método abstrato com ícone `I↑` na margem. *(Nada no console).*
* **Linha 13 (`return length * width;`):** Realiza a multiplicação simples de comprimento pela largura ($\text{área} = \text{comprimento} \times \text{largura}$) e retorna o valor `double`. *(Nada no console).*
* **Linhas 14 e 15 (`}` e `}`):** Fecham o método `area()` e a classe `Rectangle`.

---

## 6. O Que Deve Sair no Console Nesta Etapa?

Como o professor estava apenas digitando o código dentro das classes de modelo (`Triangle.java` e `Rectangle.java`) e ainda não executou o programa:

```text
(Nenhuma execução foi disparada — O console permanece no estado anterior)
```

### O Que Acontecerá em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) na Próxima Etapa?
Como criamos construtores específicos com parâmetros nas 3 classes:
* `Circle(double radius)`
* `Triangle(double base, double height)`
* `Rectangle(double length, double width)`

Ao abrir `Main.java`, as chamadas antigas sem parâmetros:
```java
Circle circle = new Circle();         // ❌ ERRO! Espera 1 argumento!
Triangle triangle = new Triangle();   // ❌ ERRO! Espera 2 argumentos!
Rectangle rectangle = new Rectangle(); // ❌ ERRO! Espera 2 argumentos!
```
Estarão com erro de compilação até o professor passar os números reais (ex: `new Circle(3)`, `new Triangle(4, 5)`, `new Rectangle(3, 6)`), o que veremos exatamente no próximo passo!

---

## 7. Quadro Geral Comparativo: A Trindade das Formas Geométricas

| Classe | Atributos de Instância | Parâmetros do Construtor | Fórmula de `area()` | Tipo de Retorno |
| :--- | :--- | :--- | :--- | :--- |
| [`Circle`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java) | `double radius;` | `(double radius)` | `Math.PI * radius * radius` | `double` |
| [`Triangle`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Triangle.java) | `double base;`<br>`double height;` | `(double base, double height)` | `0.5 * base * height` | `double` |
| [`Rectangle`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Rectangle.java) | `double length;`<br>`double width;` | `(double length, double width)` | `length * width` | `double` |

---

## 8. Resumo da Lição para Fixação no Google Docs

1. **A Classe Abstrata Estabelece a Missão, as Filhas Escolhem as Ferramentas:**  
   A superclasse abstrata `Shape` exigiu: *"Todo mundo tem que ter o método `double area()`"*. Mas ela não disse **como** calcular, porque um círculo precisa de raio, um triângulo precisa de base/altura e um retângulo precisa de comprimento/largura!
2. **Uso Mestre do `this`:**  
   Em todos os construtores, `this.nomeDoAtributo = nomeDoAtributo;` foi utilizado para desambiguar as variáveis de mesmo nome com clareza cristalina.
3. **Cuidado com Divisões Matemáticas em Java:**  
   Sempre que precisar de fração (como $\frac{1}{2}$), use ponto flutuante como `0.5` ou `2.0` para nunca cair na armadilha da divisão truncada inteira (`1 / 2 == 0`).
4. **Pronto para a Ação:** Todas as 3 formas agora possuem seus cálculos reais programados e estão prontas para serem alimentadas e impressas no método principal!
