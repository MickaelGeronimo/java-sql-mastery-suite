# Curso de Java — Capítulo 46: Sobrescrita de Método (`method overriding ♻️`)
## Parte 3: Instanciando Cão, Gato e Peixe em `Main.java` e a Preparação dos Movimentos

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:43:00` até `07:43:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) o que o professor fez em `Main.java`: a criação dos três objetos (`dog`, `cat`, `fish`), o truque de edição de código copiando `dog.move()` três vezes, a seleção da palavra `dog` para renomear para `cat` e `fish`, e o que acontece no console quando todos os animais usam o método herdado do pai.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (abas, seleções de texto, cursores e saídas de terminal) foi minuciosamente convertido em texto e tabelas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: Preparando os Três Atores no Palco

Nas etapas anteriores deste capítulo:
* Criamos a superclasse [`Animal.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) com o método padrão:
  ```java
  void move(){
      System.out.println("This animal is running");
  }
  ```
* Criamos as subclasses filhas: `Dog.java`, [`Cat.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) e [`Fish.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java).

Nesta captura de tela (`media_1791357103349.png`), o professor vai para [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) para colocar os três animais para se moverem:

1. **Instanciação dos Três Animais (Linhas 9, 10 e 11):**  
   * `Dog dog = new Dog();`
   * `Cat cat = new Cat();`
   * `Fish fish = new Fish();`
2. **O Truque do Programador Rápido (Linhas 13, 14 e 15):**  
   * O professor digita `dog.move();` na linha 13.
   * Em vez de digitar tudo de novo do zero para o gato e para o peixe, ele duplica a linha duas vezes (atalho `Ctrl + D` ou Copiar/Colar).
   * O código fica temporariamente com três `dog.move();` seguidos!
3. **O Momento Exato Capturado na Imagem:**  
   * O professor dá um duplo clique com o mouse na palavra **`dog`** da linha 14.
   * Ela fica selecionada com um retângulo azul. Ele está prestes a digitar `cat` por cima!
   * E em seguida, selecionará o `dog` da linha 15 para digitar `fish` por cima.

---

## 2. Radiografia Visual Minuciosa da Imagem

Para quem está estudando no Google Docs sem a imagem original, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem (`media_1791357103349.png`): O Flagrante da Edição de Código
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Abas Abertas no Topo:** Total de 5 abas abertas em fila!
  * `Main.java` (em foco com a linha azul sublinhando).
  * `Animal.java`
  * `Dog.java`
  * `Cat.java`
  * `Fish.java`
* **Painel Esquerdo (Project Tree):** A pasta `src` exibe a coleção completa de arquivos: `Animal`, `Cat`, `Dog`, `Fish` e `Main`.
* **Código Visível no Editor:**
  ```java
  1  
  2  public class Main {
  3      public static void main(String[] args) {
  4  
  5          // Method overriding = When a subclass provides its own
  6          //                     implementation of a method that is already defined.
  7          //                     Allows for code reusability and give specific implementations
  8  
  9          Dog dog = new Dog();
  10         Cat cat = new Cat();
  11         Fish fish = new Fish();
  12 
  13         dog.move();
  14         dog.move();
  15         dog.move();
  16 
  17     }
  18 }
  ```
* **Destaques e Cores da IDE:**
  * Na linha 9, a palavra `dog` possui um fundo sombreado rosa/cinza suave (a IDE destacando as ocorrências do objeto).
  * Na linha 14, a palavra **`dog`** está com uma barra de seleção azul sólida (`[dog].move();`), com o cursor de texto piscando no final da palavra.
  * Nas linhas 13 e 15, as palavras `dog` aparecem com uma caixa retangular cinza clara ao redor (indicando correspondência de texto com o que está selecionado).
  * No canto superior direito da IDE, há um aviso amarelo: `⚠️ 2` (duas advertências menores da IDE, avisando que as variáveis `cat` e `fish` foram criadas mas ainda não foram lidas no código, pois as linhas 14 e 15 ainda chamam `dog`).

---

## 3. Análise Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // Method overriding = When a subclass provides its own
5:          //                     implementation of a method that is already defined.
6:          //                     Allows for code reusability and give specific implementations
7:  
8:          Dog dog = new Dog();
9:          Cat cat = new Cat();
10:         Fish fish = new Fish();
11: 
12:         dog.move();
13:         dog.move();
14:         dog.move();
15:     }
16: }
```

### Explicação Detalhada:

* **Linhas 1 a 6:** Estrutura clássica de `Main` e os comentários teóricos de sobrescrita de métodos.
* **Linha 8 (`Dog dog = new Dog();`):**  
  * Cria o objeto cão na memória RAM.  
  * Como `Dog extends Animal`, o cão herda o método `move()` do pai.
* **Linha 9 (`Cat cat = new Cat();`):**  
  * Cria o objeto gato na memória RAM.  
  * Como `Cat extends Animal`, o gato herda o método `move()` do pai.
* **Linha 10 (`Fish fish = new Fish();`):**  
  * Cria o objeto peixe na memória RAM.  
  * Como `Fish extends Animal`, o peixe herda o método `move()` do pai.
* **Linha 12 (`dog.move();`):**  
  * Manda o cachorro se mover.  
  * Ele procura `move()` dentro de `Dog.java`. Não acha. Sobe para `Animal.java` e acha `System.out.println("This animal is running");`.  
  * **Saída:** `"This animal is running"`.
* **Linha 13 (`dog.move();` - prestes a virar `cat.move();`):**  
  * Nesta captura exata, ainda chama o cachorro. Quando o autor digitar `cat`, mandará o gato se mover.
* **Linha 14 (`dog.move();` - prestes a virar `fish.move();`):**  
  * Nesta captura exata, ainda chama o cachorro. Quando o autor digitar `fish`, mandará o peixe se mover.
* **Linhas 15 e 16 (`}` e `}`):** Encerra o método `main` e a classe `Main`.

---

## 4. O Que Acontece no Console Nesta Etapa?

### Se executarmos o código exatamente como está na tela (com 3 `dog.move()`):

```text
This animal is running
This animal is running
This animal is running

Process finished with exit code 0
```

### E o que aconteceria se trocássemos agora para `cat.move()` e `fish.move()`?

Como **nenhum dos filhos sobrescreveu o método ainda**, todos eles chamariam o método original herdado de [`Animal.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java)!

A saída no console seria **exatamente a mesma**:
```text
This animal is running  <-- Cão correndo (normal)
This animal is running  <-- Gato correndo (normal)
This animal is running  <-- Peixe correndo (UM PEIXE CORRENDO?!)
```

### O Ponto de Virada:
É exatamente ao ver essa saída tripla que o aluno se dá conta:
> *"Espera aí! O peixe está dizendo que está correndo porque ele é obrigado a usar a frase que o pai colocou lá em `Animal.java`! Nós precisamos urgentemente ensinar o peixe a falar que está nadando!"*

E como ensinamos o peixe a nadar mantendo o método chamado `move()`?  
**Com a Sobrescrita de Método (`@Override void move()`)**, que veremos nas próximas telas!

---

## 5. Dica de Produtividade do IntelliJ Mostrada na Cena

Observe como o professor programa rápido:
* Em vez de digitar manualmente:
  ```java
  dog.move();
  cat.move();
  fish.move();
  ```
* O programador profissional digita a primeira linha `dog.move();`, pressiona `Ctrl + D` duas vezes para duplicar a linha instantaneamente, e depois usa o mouse e a tecla `Tab` para apenas trocar os nomes dos objetos. Essa é uma técnica de edição que você usará todos os dias trabalhando com Java!

---

## 6. Resumo para Fixação no Google Docs

1. **Herança sem sobrescrita é passiva:** Se uma classe filha não reescrever um método herdado, ela usará a versão exata do pai, goste ou não.
2. **O problema da generalização excessiva:** Dizer que todo animal "está correndo" serve para muitos mamíferos terrestres, mas falha para aves (que voam), peixes (que nadam) e cobras (que rastejam).
3. **A solução inteligente:** Deixar a superclasse com o nome padrão universal `move()`, e permitir que cada filho especializado sobrescreva a sua ação interna.
