# Curso de Java — Capítulo 50: Polimorfismo (`polymorphism 🎭`)
## Parte 5: Testando os Motores! — Instanciando o Trio (`Car`, `Bike`, `Boat`), Chamadas Individuais de `go()`, a Saída no Console e a Provocação do Array Polimórfico

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:10:26` até `08:10:34` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) o momento em que o professor Bro Code volta para o arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), instancia os três veículos na memória (`new Car()`, `new Bike()`, `new Boat()`), chama o método `go()` individualmente para cada um deles, executa o programa no IntelliJ IDEA, analisa a saída gerada no console (destacando com o mouse a frase do carro em azul), e faz a grande pergunta provocativa que nos levará ao verdadeiro poder do Polimorfismo: *"E se tivéssemos 100 veículos? Teríamos que chamar um por um ou poderíamos agrupá-los todos dentro de um único array?"*.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (árvore de 5 arquivos em `src`, abas abertas, cursores de digitação, janela de execução `Run` e a seleção de texto azul no terminal) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 3 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência (`08:10:26` a `08:10:34`), o professor coloca os veículos para rodar pela primeira vez:

### 🖼️ Imagem 1 (`media_1791399945645_7ce5adb7.png` — 08:10:26): Criando os Objetos e Chamando `car.go()`
* **Aba ativa:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **Abas abertas no topo:** `Main.java` (ativa), `Vehicle.java`, `Car.java`, `Bike.java`, `Boat.java`.
* **Árvore do Projeto (à esquerda em `src`):**
  * `Bike` (ícone azul `(C)`)
  * `Boat` (ícone azul `(C)`)
  * `Car` (ícone azul `(C)`)
  * `Main` (ícone azul `(C)`)
  * `Vehicle` (ícone azul `(C)`)
* **O que foi digitado no `main`:**
  * Linhas 10 a 12: Instanciação dos três veículos:
    ```java
    Car car = new Car();
    Bike bike = new Bike();
    Boat boat = new Boat();
    ```
  * Linha 14: Primeira chamada de locomoção:
    ```java
    car.go();
    ```
  * O cursor de texto vertical está posicionado logo após o ponto e vírgula `;`.

---

### 🖼️ Imagem 2 (`media_1791399957078_75078777.png` — 08:10:30): As Três Chamadas Individuais
* **Aba ativa:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O código adicionado nas linhas 14 a 16:**
  ```java
  car.go();
  bike.go();
  boat.go();
  ```
* **O que está acontecendo:** O professor chama o método `go()` de cada um dos objetos separadamente. O cursor de texto está no final da linha 16 após `boat.go();`.

---

### 🖼️ Imagem 3 (`media_1791399968779_37c1f97a.png` — 08:10:34): A Execução no Console e o Destaque em Azul
* **Aba ativa:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **Painel inferior aberto:** Janela `Run` (Console de saída do IntelliJ).
* **Texto impresso no console (em verde neon brilhante no tema do terminal):**
  ```text
  You drive the car
  You ride the bike
  You sail the boat

  Process finished with exit code 0
  ```
* **O Destaque do Professor:**
  * O professor clica e arrasta o mouse sobre a primeira linha da saída:
    $$\colorbox{#264f78}{\textbf{You drive the car}}$$
  * Ele seleciona o texto em azul e explica: *"Vejam que cada objeto chamou o seu próprio método sobrescrito! O carro dirigiu, a bicicleta pedalou e o barco navegou!"*.

---

## 2. A Analogia Didática: O Problema dos 100 Pilotos na Corrida

Imagine que você está desenvolvendo um jogo de corrida chamado **"Need for Speed: Java Edition"**:

```
[ Garagem do Jogo ]
- Objeto 1: Car car = new Car();
- Objeto 2: Bike bike = new Bike();
- Objeto 3: Boat boat = new Boat();
```

Para fazer os 3 largarem na pista, você escreveu 3 linhas manuais:
```java
car.go();   // Carro larga!
bike.go();  // Bicicleta larga!
boat.go();  // Barco larga!
```

### Agora responda com calma:
> *"E se a sua corrida tiver **100 competidores** diferentes?"*  
> Você vai criar 100 variáveis manuais (`v1`, `v2`, `v3`... `v100`) e escrever 100 linhas chamando `v1.go();`, `v2.go();` até `v100.go();`?  
> **CLARO QUE NÃO!** Isso seria uma loucura!

### Qual seria a solução natural de qualquer programador?
Colocar todos os competidores dentro de um **Vetor / Lista (Array)** e fazer um loop `for` de uma linha só!

### O Grande Dilema do Java (O Muro que o Polimorfismo Quebra):
Em Java, arrays são de **tipo estrito**:
* Se você tentar criar um array de carros:
  ```java
  Car[] racers = { car, bike, boat }; // ERRO VERMELHO!
  ```
  O Java vai gritar: *"Ei! A bicicleta (Bike) e o barco (Boat) NÃO cabem aqui! Este array só aceita Carros!"*
* Se tentar criar um array de motos:
  ```java
  Bike[] racers = { car, bike, boat }; // ERRO VERMELHO!
  ```
  O Java grita de novo: *"O carro e o barco não são motos!"*

### A Luz no Fim do Túnel:
> *"Qual é o único tipo que abriga o Carro, a Bicicleta e o Barco ao mesmo tempo?"*  
> **A SUPERCLASSE COMUM: `Vehicle`!**  
> Como os três herdam de `Vehicle`, podemos tratá-los como veículos:
> ```java
> Vehicle[] racers = { car, bike, boat }; // PERFEITO! ISSO É POLIMORFISMO!
> ```

---

## 3. Dissecação Linha por Linha de `Main.java`

Vejamos o código completo e exato de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) nas Imagens 1, 2 e 3:

```java
1:  
2:  public class Main {
3:      public static void main(String[] args) {
4:  
5:          // Polymorphism = "POLY" = "MANY"
6:          //                "MORPH" = "SHAPE"
7:          //                Objects can identify as other objects.
8:          //                Objects can be treated as objects of a common superclass.
9:  
10:         Car car = new Car();
11:         Bike bike = new Bike();
12:         Boat boat = new Boat();
13: 
14:         car.go();
15:         bike.go();
16:         boat.go();
17: 
18:     }
19: }
```

### Explicação Passo a Passo de Cada Linha:

* **Linha 1 (Linha em branco):**
  * Espaço visual antes do início da classe.
  * **Saída no Console:** Nenhuma.

* **Linha 2 (`public class Main {`):**
  * Declara a classe pública `Main`.
  * **Saída no Console:** Nenhuma.

* **Linha 3 (`    public static void main(String[] args) {`):**
  * O método de entrada onde a execução do programa começa. Na sarjeta esquerda, aparecem os ícones verdes de execução `▶`.
  * **Saída no Console:** Nenhuma.

* **Linha 4 (Linha em branco):**
  * Espaçamento estético.
  * **Saída no Console:** Nenhuma.

* **Linhas 5 a 8 (Comentários Teóricos de Polimorfismo):**
  * `// Polymorphism = "POLY" = "MANY"`
  * `// "MORPH" = "SHAPE"`
  * `// Objects can identify as other objects.`
  * `// Objects can be treated as objects of a common superclass.`
  * Comentários explicativos ignorados pelo compilador.
  * **Saída no Console:** Nenhuma.

* **Linha 9 (Linha em branco):**
  * Espaço separando os comentários do código executável.
  * **Saída no Console:** Nenhuma.

* **Linha 10 (`        Car car = new Car();`):**
  * `Car`: O tipo da variável de referência.
  * `car`: O nome da variável que aponta para o objeto.
  * `=`: Operador de atribuição.
  * `new Car()`: Cria um novo objeto carro na memória RAM (Heap).
  * **Saída no Console:** Nenhuma na criação.

* **Linha 11 (`        Bike bike = new Bike();`):**
  * Cria um novo objeto bicicleta na memória RAM e guarda na variável `bike`.
  * **Saída no Console:** Nenhuma na criação.

* **Linha 12 (`        Boat boat = new Boat();`):**
  * Cria um novo objeto barco na memória RAM e guarda na variável `boat`.
  * **Saída no Console:** Nenhuma na criação.

* **Linha 13 (Linha em branco):**
  * Espaço de separação entre a criação dos objetos e a execução de suas ações.
  * **Saída no Console:** Nenhuma.

* **Linha 14 (`        car.go();`):**
  * O Java busca o método `go()` na classe `Car` e executa a linha interna `System.out.println("You drive the car");`.
  * **Saída no Console:**
    ```text
    You drive the car
    ```

* **Linha 15 (`        bike.go();`):**
  * O Java busca o método `go()` na classe `Bike` e executa a linha interna `System.out.println("You ride the bike");`.
  * **Saída no Console:**
    ```text
    You ride the bike
    ```

* **Linha 16 (`        boat.go();`):**
  * O Java busca o método `go()` na classe `Boat` e executa a linha interna `System.out.println("You sail the boat");`.
  * **Saída no Console:**
    ```text
    You sail the boat
    ```

* **Linha 17 (Linha em branco):**
  * Espaçamento antes de fechar o método.
  * **Saída no Console:** Nenhuma.

* **Linha 18 (`    }`):**
  * Fecha o método `main`.
  * **Saída no Console:** Nenhuma.

* **Linha 19 (`}`):**
  * Fecha a classe `Main`. O programa encerra a execução com sucesso.
  * **Saída no Console:**
    ```text
    Process finished with exit code 0
    ```

---

## 4. O Que Sai no Console (Comprovado pela Imagem 3)

Ao clicar no botão verde de Run `▶` no IntelliJ IDEA, a janela inferior `Run` abre e exibe exatamente:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.2.1\lib\idea_rt.jar=51593:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.2.1\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\Users\HP\IdeaProjects\MyFirstProject\out\production\MyFirstProject Main
You drive the car
You ride the bike
You sail the boat

Process finished with exit code 0
```

### Análise Linha por Linha da Saída:
1. **`You drive the car`** $\rightarrow$ Gerado por `car.go()` na linha 14.
2. **`You ride the bike`** $\rightarrow$ Gerado por `bike.go()` na linha 15.
3. **`You sail the boat`** $\rightarrow$ Gerado por `boat.go()` na linha 16.
4. **`Process finished with exit code 0`** $\rightarrow$ O código de encerramento da JVM confirmando que todos os métodos rodaram de ponta a ponta sem travamentos.

---

## 5. Por que o Professor Selecionou `"You drive the car"` em Azul? (Imagem 3)

Na Imagem 3 (`media_1791399968779_37c1f97a.png`), aos `8:10:34`, o professor faz questão de passar o cursor do mouse e selecionar a frase:

$$\colorbox{#264f78}{\textbf{You drive the car}}$$

Ele faz isso para chamar a atenção dos alunos para um detalhe vital:
* Cada classe filha executou **o seu próprio código customizado**!
* Embora todos tenham o método com o mesmo nome (`go()`), o Java soube exatamente ir na classe `Car` para o carro, na classe `Bike` para a bicicleta e na classe `Boat` para o barco.

---

## 6. O Próximo Grande Passo: A Virada Polimórfica!

Até agora, nós chamamos os métodos usando as variáveis específicas de cada classe:
* `car` é do tipo `Car`
* `bike` é do tipo `Bike`
* `boat` é do tipo `Boat`

Nas próximas imagens, o professor Bro Code vai dar o grande salto do capítulo:
1. Ele vai criar um array de tipo polimórfico:
   ```java
   Vehicle[] racers = { car, bike, boat };
   ```
2. E vai percorrer esse array com um loop elegante:
   ```java
   for (Vehicle x : racers) {
       x.go();
   }
   ```
E nós veremos como o Java consegue chamar a ação certa de cada um mesmo tratando todos genericamente como `Vehicle`!

Todos os arquivos no seu computador já estão atualizados, compilados e testados com a saída perfeita no console! 🚀
