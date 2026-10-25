# Curso de Java (Bro Code) — Capítulo 42: Array of Objects 🗃️
## Parte 2: A Criação da Classe `Car.java`, Instanciação dos 3 Carros, Criação do Array `Car[]` e o Laço `for`

---

> [!NOTE]
> **Contexto de Estudo para o Google Docs:**
> Este documento foi elaborado com riqueza visual descritiva e didática descomplicada para que você possa copiar e colar diretamente no seu Google Docs. Mesmo sem as imagens originais na tela, você entenderá exatamente cada detalhe visual do IntelliJ IDEA, cada linha de código, o motivo de cada comando e como o Java pensa nos bastidores.

---

## 1. O Que Aconteceu Nesta Sequência de 5 Imagens? (Visão Geral)

Esta sequência de 5 capturas de tela é o coração do **Capítulo 42 (Array of Objects)**! Aqui, o professor Bro Code constrói todo o exemplo prático do zero:

1. **Imagem 1 (`media_1791326036532.png` - Timestamp `7:08:37`):**
   * O autor cria o arquivo [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java). A tela mostra apenas a casca vazia da classe (`public class Car { }`).
2. **Imagem 2 (`media_1791326047229.png` - Timestamp `7:09:24`):**
   * O autor programa a classe [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) inteira:
     * Dois atributos: `model` (modelo) e `color` (cor).
     * O construtor `Car(String model, String color)` usando `this`.
     * Um método chamado `drive()` que imprime: `"You drive the <cor> <modelo>"`.
3. **Imagem 3 (`media_1791326132275.png`):**
   * O autor volta para [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e instancia 3 carros:
     * `car1`: Mustang Vermelho (`"Mustang"`, `"Red"`).
     * `car2`: Corvette Azul (`"Corvette"`, `"Blue"`).
     * `car3`: Charger Amarelo (`"Charger"`, `"Yellow"`).
   * Na linha 9, ele começa a digitar a declaração do array de carros: `Car[] cars = new Car[];`.
4. **Imagem 4 (`media_1791326140027.png` - Timestamp `7:10:42`):**
   * O autor completa o array com tamanho 3: `Car[] cars = new Car[3];`.
   * Ele explica que esta é a primeira maneira (manual) de instanciar um array de objetos com tamanho fixo.
5. **Imagem 5 (`media_1791326152604.png` - Timestamp `7:11:23`):**
   * O autor simplifica a linha 9 usando a sintaxe literal direta:
     ```java
     Car[] cars = {car1, car2, car3};
     ```
   * Em seguida, escreve um laço `for` para percorrer a garagem inteira e mandar todos os carros dirigirem:
     ```java
     for(int i = 0; i < cars.length; i++){
         cars[i].drive();
     }
     ```
   * O mouse paira sobre o botão verde **Play (Run 'Main')** pronto para rodar!

---

## 2. Descrição Visual Detalhada das Telas (Para Seu Google Docs)

### 📸 Imagem 1: O Arquivo `Car.java` Acabou de Nascer
* **Tempo do vídeo:** `7:08:37 / 12:00:00`.
* **Aba ativa:** `Car.java` (ao lado de `Main.java`).
* **Árvore de arquivos à esquerda:** Abaixo da pasta `src`, agora vemos `Car` e `Main`.
* **Editor:** Apenas linhas 1 e 2:
  ```java
  public class Car {
  }
  ```
* O cursor pisca na linha 1 após a chave `{`.

---

### 📸 Imagem 2: A Classe `Car.java` Pronta e Turbinada
* **Tempo do vídeo:** `7:09:24 / 12:00:00`.
* **Aba ativa:** `Car.java`.
* **Editor de código (linhas 1 a 16):**
  * Linhas 3 e 4: Declaração das variáveis `String model;` e `String color;`.
  * Linhas 6 a 9: O construtor `Car(String model, String color)`.
  * Linhas 11 a 13: O método `void drive()`.
  * Na linha 12, a palavra `model` está com uma caixinha cinza de seleção com o cursor no final, indicando que o autor acabou de digitar `this.model`.

---

### 📸 Imagem 3: Os 3 Carros e a Criação do Array
* **Aba ativa:** `Main.java`.
* **Editor de código:**
  * Linha 5: `Car car1 = new Car("Mustang", "Red");`
  * Linha 6: `Car car2 = new Car("Corvette", "Blue");`
  * Linha 7: `Car car3 = new Car("Charger", "Yellow");`
  * Linha 9: `Car[] cars = new Car[];` com o cursor piscando **dentro dos colchetes `[]`**.

---

### 📸 Imagem 4: O Array com Tamanho `[3]`
* **Tempo do vídeo:** `7:10:42 / 12:00:00`.
* **Editor de código na linha 9:**
  ```java
  Car[] cars = new Car[3];
  ```
* O número `3` está destacado em azul entre os colchetes. Isso avisa ao Java: *"Reserve 3 vagas numeradas na garagem!"*.

---

### 📸 Imagem 5: A Garagem Compacta e o Laço `for`
* **Tempo do vídeo:** `7:11:23 / 12:00:00`.
* **Linha 9 alterada:** `Car[] cars = {car1, car2, car3};` (colocando os 3 carros diretamente nas chaves).
* **Linhas 11 a 13:** O laço `for` tradicional:
  ```java
  for(int i = 0; i < cars.length; i++){
      cars[i].drive();
  }
  ```
* **Ação do mouse:** O cursor do mouse está no topo superior direito, sobre o botão verde de **Play (Run 'Main')**.

---

## 3. Entendendo Cada Peça do Quebra-Cabeça (Para Iniciantes Amadores)

### A. O que é a classe `Car.java`?
É a **planta de engenharia** de um carro. Ela diz que todo carro do nosso jogo/programa terá:
1. Um modelo (`model`) como texto.
2. Uma cor (`color`) como texto.
3. Uma ação que ele sabe fazer: dirigir (`drive()`).

### B. As duas formas de criar o Array de Carros:

O Bro Code mostrou duas formas de fazer a mesma coisa:

* **Forma 1 (Em dois passos - Imagem 4):**
  ```java
  Car[] cars = new Car[3]; // 1º: Cria a garagem com 3 vagas vazias
  cars[0] = car1;          // 2º: Estaciona o Mustang na vaga 0
  cars[1] = car2;          //     Estaciona o Corvette na vaga 1
  cars[2] = car3;          //     Estaciona o Charger na vaga 2
  ```

* **Forma 2 (Direta e Rápida - Imagem 5):**
  ```java
  Car[] cars = {car1, car2, car3}; // Cria a garagem e já estaciona os 3 de uma vez!
  ```
  *(O Bro Code escolheu a Forma 2 por ser muito mais limpa e moderna!)*

### C. O que o laço `for` está fazendo?
Em vez de você escrever manualmente:
```java
car1.drive();
car2.drive();
car3.drive();
```
O laço `for` visita cada gaveta da garagem uma por uma usando o índice `i`:
1. Quando `i = 0`: pega `cars[0]` (o Mustang) e chama `drive()`.
2. Quando `i = 1`: pega `cars[1]` (o Corvette) e chama `drive()`.
3. Quando `i = 2`: pega `cars[2]` (o Charger) e chama `drive()`.

---

## 4. Código Completo de [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) Linha por Linha

```java
 1: public class Car {
 2: 
 3:     String model;
 4:     String color;
 5: 
 6:     Car(String model, String color){
 7:         this.model = model;
 8:         this.color = color;
 9:     }
10: 
11:     void drive(){
12:         System.out.println("You drive the " + this.color + " " + this.model);
13:     }
14: 
15: }
```

### Explicação Linha por Linha:

* **Linha 1 (`public class Car {`):** Declara a classe pública `Car`. *(Console: nada)*.
* **Linha 3 (`String model;`):** Atributo para guardar o modelo do carro (ex: "Mustang"). *(Console: nada)*.
* **Linha 4 (`String color;`):** Atributo para guardar a cor do carro (ex: "Red"). *(Console: nada)*.
* **Linha 6 (`Car(String model, String color){`):** Construtor que recebe o modelo e a cor quando o carro é fabricado com `new Car(...)`.
* **Linha 7 (`this.model = model;`):** Salva o modelo recebido no atributo `this.model` do objeto.
* **Linha 8 (`this.color = color;`):** Salva a cor recebida no atributo `this.color` do objeto.
* **Linha 9 (`}`):** Fecha o construtor.
* **Linha 11 (`void drive(){`):** Declaração do método `drive()` (dirigir). Ele não devolve nenhum cálculo, apenas executa uma ação (`void`).
* **Linha 12 (`System.out.println("You drive the " + this.color + " " + this.model);`):**
  * Junta o texto fixo `"You drive the "` com a cor do carro (`this.color`), um espaço `" "` e o modelo (`this.model`), imprimindo tudo na tela.
* **Linha 13 (`}`):** Fecha o método `drive`.
* **Linha 15 (`}`):** Fecha a classe `Car`.

---

## 5. Código Completo de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) Linha por Linha

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         Car car1 = new Car("Mustang", "Red");
 5:         Car car2 = new Car("Corvette", "Blue");
 6:         Car car3 = new Car("Charger", "Yellow");
 7: 
 8:         Car[] cars = {car1, car2, car3};
 9: 
10:         for(int i = 0; i < cars.length; i++){
11:             cars[i].drive();
12:         }
13: 
14:     }
15: }
```

### Explicação Linha por Linha e Saída no Console:

* **Linha 1 e 2:** Abertura da classe `Main` e método `main`. *(Console: nada)*.
* **Linha 4 (`Car car1 = new Car("Mustang", "Red");`):**
  * Cria o primeiro carro na memória Heap (Mustang Vermelho). *(Console: nada)*.
* **Linha 5 (`Car car2 = new Car("Corvette", "Blue");`):**
  * Cria o segundo carro na memória Heap (Corvette Azul). *(Console: nada)*.
* **Linha 6 (`Car car3 = new Car("Charger", "Yellow");`):**
  * Cria o terceiro carro na memória Heap (Charger Amarelo). *(Console: nada)*.
* **Linha 8 (`Car[] cars = {car1, car2, car3};`):**
  * Cria o **Array de Objetos** `cars` com 3 posições e guarda as referências de `car1`, `car2` e `car3` nos índices `[0]`, `[1]` e `[2]`.
  * *(Console: nada — operação de memória)*.
* **Linha 10 (`for(int i = 0; i < cars.length; i++){`):**
  * Inicia o laço de repetição: começa no índice `i = 0`, vai até `i < 3` (`cars.length` é 3), e avança de 1 em 1 (`i++`).
  * *(Console: nada)*.
* **Linha 11 (`cars[i].drive();`):**
  * **Volta 1 (quando `i = 0`):** pega `cars[0]` (o Mustang) e executa `drive()`.
    * **Sai no console:**
      ```text
      You drive the Red Mustang
      ```
  * **Volta 2 (quando `i = 1`):** pega `cars[1]` (o Corvette) e executa `drive()`.
    * **Sai no console:**
      ```text
      You drive the Blue Corvette
      ```
  * **Volta 3 (quando `i = 2`):** pega `cars[2]` (o Charger) e executa `drive()`.
    * **Sai no console:**
      ```text
      You drive the Yellow Charger
      ```
* **Linha 12 (`}`):** Fecha o bloco do laço `for`.
* **Linha 14 e 15 (`}`):** Fecham o método `main` e a classe `Main`.

---

## 6. O Que Sai no Console Quando Clicar no Play?

Ao clicar no botão verde Play exibido na Imagem 5, o console do IntelliJ exibirá exatamente estas 3 frases:

```text
You drive the Red Mustang
You drive the Blue Corvette
You drive the Yellow Charger
```

---

## 7. Diagrama de Memória (Stack vs Heap) do Array de Objetos

Veja como os ponteiros se conectam na memória RAM:

```text
  STACK (Variáveis)                                 HEAP (Objetos Reais)
┌──────────────────────┐                     ┌────────────────────────────────────────┐
│ car1 ────────────────┼────────────────────>│ Carro 1: model="Mustang", color="Red"   │
│                      │                     └────────────────────────────────────────┘
│ car2 ────────────────┼──────────┐          ┌────────────────────────────────────────┐
│                      │          └─────────>│ Carro 2: model="Corvette", color="Blue" │
│ car3 ────────────────┼─────────────┐       └────────────────────────────────────────┘
│                      │             │       ┌────────────────────────────────────────┐
│                      │             └──────>│ Carro 3: model="Charger", color="Yellow│
│                      │                     └────────────────────────────────────────┘
│                      │                                         ▲
│                      │                                         │
│ cars (Array) ────────┼────────┐                                │
└──────────────────────┘        ▼                                │
                        ┌───────────────────┐                    │
                        │ Array Car[3]:     │                    │
                        │ [0] ──────────────┼─── aponta p/ Carro 1
                        │ [1] ──────────────┼─── aponta p/ Carro 2
                        │ [2] ──────────────┼─── aponta p/ Carro 3
                        └───────────────────┘
```

> **Perceba o poder:** A variável `cars` é um array que simplesmente guarda os endereços dos 3 carros. Ao chamar `cars[i].drive()`, o Java viaja até o carro correspondente e faz ele buzinar/dirigir na tela!
