# Curso de Java — Capítulo 48: Abstração (`abstraction 🌫️`)
## Parte 6: O Triplo `display()`, o Atributo `radius` e a Fórmula do Círculo com `Math.PI`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:57:45` até `07:59:00` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a execução combinada do método concreto `display()` para as três formas geométricas simultâneas no console (`circle`, `triangle` e `rectangle`), a volta para a superclasse [`Shape.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java) para retomar o método abstrato `area()`, a adição do atributo `radius` e construtor na classe [`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java), e a substituição do stub `return 0;` pela fórmula matemática real da área de um círculo ($\pi \cdot r^2$) utilizando a constante nativa **`Math.PI`**.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (múltiplas linhas no terminal, cursores de edição, realces roxos de variáveis e seleções azuis com o mouse) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Cena: Da Herança Comum à Especialização Matemática

Nesta sequência de 5 capturas de tela, o professor Bro Code fecha com perfeição o ciclo da herança e inicia a implementação das fórmulas individuais de cada figura geométrica:

1. **A Prova Tripla do Método Concreto (Imagem 1):** O professor chama `display()` para o círculo, triângulo e retângulo. O console imprime 3 vezes:
   ```text
   This is a shape
   This is a shape
   This is a shape
   ```
   Isso comprova que todas as subclasses herdaram o mesmo comportamento da mãe `Shape` sem precisar escrever nada!
2. **A Hora da Especialização (Imagens 2 e 3):** O professor volta a atenção para o método abstrato: `abstract double area();`. Agora, cada forma geométrica precisa da sua própria identidade matemática! No arquivo [`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java), ele cria o atributo de raio (`double radius;`) e o construtor.
3. **A Fórmula Científica com `Math.PI` (Imagens 4 e 5):** Ele substitui o tapa-buraco `return 0;` pela fórmula mundialmente famosa da área do círculo:
   ```java
   return Math.PI * radius * radius;
   ```

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na tela:

### Imagem 1 (`media_1791359300837.png` — 07:57:56): O Triplo `display()` no Terminal
* **Aba Ativa:** `Main.java`.
* **Editor de Código (Linhas 16 a 20):**
  ```java
  16         circle.display();
  17         triangle.display();
  18         rectangle.display();
  19     }
  20 }
  ```
* **Painel Inferior ("Run / Console"):** O terminal exibe as 3 linhas verdes:
  ```text
  This is a shape
  This is a shape
  This is a shape
  
  Process finished with exit code 0
  ```
* **Cursor de Texto:** Pisca no final da linha 18 (`rectangle.display();|`).

### Imagem 2 (`media_1791359317523.png` — 07:58:09): O Foco no Método Abstrato
* **Aba Ativa:** Muda para [`Shape.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Shape.java).
* **Editor de Código:** O cursor pisca na linha 3 dentro da palavra `abstract` em `abstract double area(); // ABSTRACT`.
* **Mensagem do Professor:** *"Agora que vimos o método concreto funcionando para todo mundo, vamos implementar a área real para cada forma!"*.

### Imagem 3 (`media_1791359352102.png` — 07:58:37): Adicionando o Raio e Construtor no Círculo
* **Aba Ativa:** Muda para [`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java).
* **O que foi adicionado:**
  * Linha 3: `double radius;` (declara o raio).
  * Linhas 5 a 7: O construtor recebendo o raio e guardando com `this.radius = radius;`.
  * O cursor de texto pisca na linha 6 após `radius|`.

### Imagem 4 (`media_1791359365360.png` — 07:58:48): Digitando `Math.PI`
* **Editor de Código:** Na linha 11, o professor apagou o `0` do método `area()` e começou a digitar:
  ```java
  return Math.PI
  ```
* O cursor pisca após `Math.PI|`, e há um pequeno sublinhado vermelho avisando que a expressão aritmética ainda não terminou.

### Imagem 5 (`media_1791359386923.png`): A Fórmula Completa Selecionada
* **Editor de Código:** A linha 11 agora está completa:
  ```java
  return Math.PI * radius * radius;
  ```
* **Seleção com o Mouse:** Toda a expressão `Math.PI * radius * radius;` está selecionada com uma barra azul brilhante.
* **Cores da Sintaxe:** As palavras `radius` aparecem em roxo/rosa, confirmando que a IDE reconheceu o atributo de instância declarado na linha 3!

---

## 3. Dissecando a Matemática em Java: Como Funciona o `Math.PI * radius * radius`?

Vamos olhar para a geometria que aprendemos na escola:

$$\text{Área do Círculo} = \pi \cdot r^2 = \pi \times \text{raio} \times \text{raio}$$

Como traduzimos isso para o Java?

```
┌───────────────────────┬───┬───────────────────┬───┬───────────────────┐
│        Math.PI        │ * │      radius       │ * │      radius       │
├───────────────────────┼───┼───────────────────┼───┼───────────────────┤
│ Constante Oficial     │   │ Variável do Raio  │   │ Variável do Raio  │
│ 3.141592653589793...  │ × │      (ex: 3)      │ × │      (ex: 3)      │
└───────────────────────┴───┴───────────────────┴───┴───────────────────┘
```

### 1. O Que É `Math.PI`?
* O Java já vem com uma classe utilitária pronta chamada `Math` (do pacote padrão `java.lang`).
* Dentro dessa classe, existe uma constante pública, estática e imutável chamada **`PI`**.
* O valor de `Math.PI` é o número Pi com altíssima precisão: `3.141592653589793`.
* Você não precisa digitar `3.14159` na mão! O Java faz isso por você!

### 2. Por Que Multiplicar `radius * radius`?
* Elevar um número ao quadrado ($r^2$) nada mais é do que multiplicar o número por ele mesmo ($r \times r$).
* No Java, poderíamos usar `Math.pow(radius, 2)`, mas multiplicar `radius * radius` diretamente é mais rápido, mais legível e gasta menos memória do processador!

---

## 4. Análise Código por Código e Linha por Linha de [`Circle.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Circle.java)

Abaixo está o código completo da classe `Circle` como vista na Imagem 5:

```java
1:  public class Circle extends Shape{
2:  
3:      double radius;
4:  
5:      Circle(double radius){
6:          this.radius = radius;
7:      }
8:  
9:      @Override
10:     double area(){
11:         return Math.PI * radius * radius;
12:     }
13: }
```

### Explicação Detalhada Linha por Linha:

* **Linha 1 (`public class Circle extends Shape{`):** Declara que `Circle` é uma subclasse de `Shape`.
* **Linha 2:** Linha em branco para organização visual.
* **Linha 3 (`double radius;`):** Cria o atributo de instância `radius` (tipo ponto flutuante `double`) para guardar o raio do círculo em centímetros, metros ou pixels.
* **Linha 4:** Linha em branco.
* **Linha 5 (`Circle(double radius){`):** O construtor da classe. Ele exige que quem for criar um círculo informe qual é o seu raio.
* **Linha 6 (`this.radius = radius;`):** Pega o valor recebido no parâmetro e guarda no atributo do objeto que está sendo criado na memória.
* **Linha 7 (`}`):** Encerra o construtor.
* **Linha 8:** Linha em branco.
* **Linha 9 (`@Override`):** Anotação oficial avisando que estamos cumprindo o contrato do método abstrato de `Shape`.
* **Linha 10 (`double area(){`):** Assinatura obrigatória do método herdado. Na margem da IDE surge o ícone verde/azul `I↑`.
* **Linha 11 (`return Math.PI * radius * radius;`):** Executa o cálculo da área e devolve o resultado com precisão decimal.
* **Linha 12 (`}`):** Fecha o método `area()`.
* **Linha 13 (`}`):** Fecha a classe `Circle`.

---

## 5. Análise Código por Código de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) na Imagem 1

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
17:         triangle.display();
18:         rectangle.display();
19:     }
20: }
```

### O que sai no Console:
* **Linha 16 (`circle.display();`):** Roda o método de `Shape` -> Imprime `"This is a shape"`.
* **Linha 17 (`triangle.display();`):** Roda o método de `Shape` -> Imprime `"This is a shape"`.
* **Linha 18 (`rectangle.display();`):** Roda o método de `Shape` -> Imprime `"This is a shape"`.

---

## 6. Saída Oficial do Console Nesta Etapa

Ao executar o código da Imagem 1:

```text
This is a shape
This is a shape
This is a shape

Process finished with exit code 0
```

* Cada uma das 3 formas geométricas executou o método concreto herdado da mãe `Shape`.
* Sucesso total sem nenhum erro de compilação!

---

## 7. Tabela Comparativa da Evolução de `Circle.java`

| Etapa | Código do Método `area()` | O que ele devolvia? | Avaliação |
| :--- | :--- | :--- | :--- |
| **Parte 2** | Nem existia (classe vazia) | Nada | ❌ Erro de compilação: devia implementar `area()`. |
| **Parte 3** | `return 0;` | `0.0` | ⚠️ Stub temporário para parar os erros. |
| **Parte 6 (Agora!)** | **`return Math.PI * radius * radius;`** | **O cálculo real da área ($\pi \cdot r^2$)** | 🏆 **Implementação real, matemática e profissional!** |

---

## 8. Resumo para Fixação no Google Docs

1. **A mãe `Shape` fornece a base comum:** `display()` imprime `"This is a shape"` para todas as filhas de graça.
2. **Cada filha cuida de suas próprias características:** O círculo tem `radius`, o retângulo terá `width` e `height`, o triângulo terá `base` e `height`.
3. **`Math.PI` é a constante do Pi no Java:** Não use aproximações como `3.14`; use sempre `Math.PI` para obter a máxima precisão matemática.
4. **O contrato está quase concluído:** Agora que o Círculo sabe calcular sua área real, nas próximas cenas veremos o mesmo acontecer com o Retângulo e o Triângulo!
