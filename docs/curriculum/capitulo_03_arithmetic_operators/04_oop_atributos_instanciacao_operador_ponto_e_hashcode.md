# 🧱 Capítulo 39: Programação Orientada a Objetos (OOP) em Java — Parte 2
## 🚘 Atributos em `Car.java`, o Operador `new`, o Enigma do `Car@6acbcfc0` e o Operador Ponto (`.`)

> **Arquivos de Estudo:** `Car.java` e `Main.java`  
> **Timestamp do Vídeo:** `6:44:11` até `6:45:45` / `12:00:00`  
> **Canal:** Bro Code — *Java Full Course for free*  
> **Objetivo:** Explicar para um iniciante absoluto cada detalhe das 5 novas telas do vídeo: como definir variáveis de instância (atributos) dentro de uma classe própria, a anatomia da criação de um objeto com a palavra-chave `new`, o susto que todo iniciante toma ao imprimir um objeto diretamente (`Car@6acbcfc0`) e como usar o **Operador Ponto (`.`)** para extrair os dados guardados dentro do objeto (`car.model`).

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Sem Precisar do Vídeo)

Se você estiver lendo este material no Google Docs ou impresso, aqui está a descrição minuciosa do que aconteceu na tela do Bro Code em cada um dos 5 momentos:

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 1 (6:44:11): Definindo os Atributos em Car.java                                           |
+---------------------------------------------------------------------------------------------------+
| NA JANELA DO CÓDIGO (Aba Car.java ativa):                                                         |
| Bro Code preenche o corpo da classe Car com 5 características essenciais de um automóvel:         |
|                                                                                                   |
| 1: public class Car {                                                                             |
| 2:                                                                                                |
| 3:     String make = "Ford";                                                                      |
| 4:     String model = "Mustang";                                                                  |
| 5:     int year = 2025;                                                                           |
| 6:     double price = 58000.99;                                                                   |
| 7:     boolean isRunning = false;                                                                 |
| 8: }                                                                                              |
|                                                                                                   |
| Observação Visual: O cursor do teclado pisca na linha 7 logo após o ponto e vírgula de "false;".  |
| As variáveis estão alinhadas com recuo (tabulação) dentro das chaves da classe.                   |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 2 (6:44:40): A Conexão Mental com Scanner e Random em Main.java                           |
+---------------------------------------------------------------------------------------------------+
| NA JANELA DO CÓDIGO (Aba Main.java ativa):                                                        |
| Dentro do método main, Bro Code digita 3 linhas para fazer o aluno "ligar os pontos":             |
|                                                                                                   |
|  8:         Scanner scanner = new Scanner(System.in);                                             |
|  9:         Random random = new Random();                                                         |
| 10:                                                                                               |
| 11:                                                                                               |
| 12:         Car car = new Car();                                                                  |
|                                                                                                   |
| O professor explica: "Vejam a semelhança! Vocês já criaram objetos antes! Quando criavam Scanner  |
| ou Random, usavam essa mesma estrutura. Agora estão criando um objeto da sua própria classe Car!" |
| (As palavras Scanner e Random aparecem em vermelho temporário porque não foram importadas).        |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 3 (6:45:20): Imprimindo o Objeto Diretamente no Main.java                                 |
+---------------------------------------------------------------------------------------------------+
| Bro Code apaga os exemplos do Scanner/Random e deixa o código limpo:                              |
|                                                                                                   |
|  9:         Car car = new Car();                                                                  |
| 10:                                                                                               |
| 11:         System.out.println(car);                                                              |
|                                                                                                   |
| Observação Visual: O cursor do mouse vai até o canto superior direito da janela do IntelliJ       |
| e paira sobre o botão verde triangular de "Run" (Play), preparando-se para rodar o código.        |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 4 (6:45:28): O Susto do Iniciante no Terminal (Car@6acbcfc0)                              |
+---------------------------------------------------------------------------------------------------+
| O programa é executado e o console do terminal integrado exibe em texto verde:                    |
|                                                                                                   |
| Car@6acbcfc0                                                                                      |
|                                                                                                   |
| Process finished with exit code 0                                                                 |
|                                                                                                   |
| DETALHE VISUAL DO PROFESSOR:                                                                      |
| Bro Code passa o mouse e seleciona com destaque AZUL o texto "Car@6acbcfc0" no terminal.          |
| Ele faz uma pausa dramática para explicar: "O que é isso? Onde está o Ford Mustang? Por que       |
| apareceu esse código estranho com um arroba no meio?".                                            |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 5 (6:45:45): A Solução com o Operador Ponto (car.model)                                   |
+---------------------------------------------------------------------------------------------------+
| Bro Code edita a linha 11 do Main.java para:                                                      |
|                                                                                                   |
| 11:         System.out.println(car.model);                                                        |
|                                                                                                   |
| Ele roda o programa novamente e veja o resultado maravilhoso no terminal:                         |
|                                                                                                   |
| Mustang                                                                                           |
|                                                                                                   |
| Process finished with exit code 0                                                                 |
|                                                                                                   |
| O professor comemora: "Para acessar qualquer dado de um objeto, nós usamos o Operador Ponto (.)!" |
+---------------------------------------------------------------------------------------------------+
```

---

## 🧠 Explicação Didática Extrema (Para Leigos Absolutos)

### 1. O que são Atributos em `Car.java`? (A Ficha Técnica do Carro)

Abra o arquivo [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) e olhe o que nós escrevemos:
```java
public class Car {

    String make = "Ford";
    String model = "Mustang";
    int year = 2025;
    double price = 58000.99;
    boolean isRunning = false;
}
```

* **Repare em algo fundamental:**  
  Não existe `public static void main` aqui dentro!  
  Por quê? Porque `Car.java` **não é um programa executável**; ele é apenas o **molde (a planta técnica)**.
* **As variáveis declaradas direto dentro da classe:**  
  Quando criamos variáveis soltas dentro da classe (fora de qualquer função), nós as chamamos de **Atributos**, **Campos (*Fields*)** ou **Variáveis de Instância**.
  1. `make` ("Ford"): A marca / montadora / fabricante do carro (tipo `String`).
  2. `model` ("Mustang"): O modelo da máquina (tipo `String`).
  3. `year` (2025): O ano de lançamento (número inteiro `int`).
  4. `price` (58000.99): O preço de tabela em dólares (número decimal `double`).
  5. `isRunning` (`false`): O motor está funcionando? Começa como `false` (desligado, tipo `boolean`).

---

### 2. A Fórmula Mágica da Instanciação: `Car car = new Car();`

No Momento 2, Bro Code mostrou que todo objeto em Java nasce da mesma maneira:
```java
Car car = new Car();
```

Vamos dissecar essa linha em 4 partes como um cirurgião:

```
    Car            car        =        new              Car();
  [ TIPO ]      [ NOME ]            [ AÇÃO ]        [ CONSTRUTOR ]
  (A Classe)   (A Variável)       (Vá ao Heap      (Inicialize as
                                 e fabrique!)     características)
```

1. **`Car` (com C maiúsculo):** É o **Tipo de Dado**. Assim como você escreve `int numero;` para criar um número, você escreve `Car car;` para dizer ao Java: *"A variável que estou criando vai guardar uma referência a um Carro!"*.
2. **`car` (com c minúsculo):** É o **Nome da Variável de Referência**. É a etiqueta que você dá para poder conversar com esse carro no código.
   * *Regra de Boa Convivência do Java:* Nome de **Classe** começa sempre com letra Maiúscula (`Car`, `Scanner`, `Random`). Nome de **Variável/Objeto** começa com letra minúscula (`car`, `scanner`, `random`).
3. **`=` (Atribuição):** Pega o que for fabricado do lado direito e entrega na mão da variável do lado esquerdo.
4. **`new` (A PALAVRA MÁGICA):**  
   O operador `new` é o operário da fábrica da JVM. Ele corre até a memória **Heap**, reserva um lote de terra vazio com espaço suficiente para guardar todas as 5 variáveis do carro e crava uma estaca com o endereço de memória daquele lote!
5. **`Car()`:** É a chamada do **Construtor**. Ele monta o carro preenchendo as gavetas com `"Ford"`, `"Mustang"`, `2025`, etc.

---

### 3. O Grande Enigma: Por que `System.out.println(car)` imprimiu `Car@6acbcfc0`?

No Momento 4, Bro Code mandou imprimir o carro:
```java
System.out.println(car);
```
E a tela cuspiu:
```text
Car@6acbcfc0
```

> **🚨 Dúvida do Iniciante Assustado:**  
> *"Socorro, meu código quebrou? Por que ele não imprimiu 'Ford Mustang'? O que significa esse texto alienígena?"*

**Resposta Tranquilizadora:**  
Seu código **NÃO QUEBROU!** Ele funcionou com 100% de precisão!  
Lembre-se do que aprendemos na Parte 1: **um objeto é um tipo por referência!**  
A variável `car` na memória Stack **não guarda o carro de metal**; ela guarda apenas o **endereço do galpão** onde o carro foi estacionado no Heap!

Quando você pede para o `System.out.println` imprimir um objeto diretamente, o Java usa um método padrão chamado `toString()`. A receita padrão do `toString()` do Java é:
$$\text{NomeDaClasse} + \text{"@"} + \text{EndereçoHexadecimalDeMemória}$$

* `Car`: É a classe daquele objeto.
* `@`: Significa "at" (localizado em).
* `6acbcfc0`: É o código hash em hexadecimal (base 16) representando a localização física daquele objeto dentro da memória Heap do seu computador!

---

### 4. A Chave do Cofre: O Operador Ponto (`.`)

Se imprimir `car` mostra o endereço do galpão, como fazemos para abrir as portas do galpão e ver o que tem lá dentro?  
Usamos o **Operador Ponto (`.`)**!

Pense no ponto como a palavra **"DE"** ou **"DO"**:
* `car.make` $\rightarrow$ Fabricante **DO** carro $\rightarrow$ `"Ford"`
* `car.model` $\rightarrow$ Modelo **DO** carro $\rightarrow$ `"Mustang"`
* `car.year` $\rightarrow$ Ano **DO** carro $\rightarrow$ `2025`
* `car.price` $\rightarrow$ Preço **DO** carro $\rightarrow$ `58000.99`
* `car.isRunning` $\rightarrow$ Está ligado o motor **DO** carro? $\rightarrow$ `false`

No Momento 5, quando Bro Code escreveu:
```java
System.out.println(car.model);
```
O Java fez a seguinte jornada em bilionésimos de segundo:
1. Olhou para a variável `car` na Stack e leu o endereço `6acbcfc0`.
2. Pegou a rodovia da memória e foi até o endereço `6acbcfc0` no Heap.
3. Encontrou o objeto do carro.
4. Abriu a gaveta com a etiqueta `model`.
5. Leu o texto que estava guardado lá: `"Mustang"`.
6. Cuspiu na tela do terminal com orgulho: `Mustang`!

---

## 💾 Modelo Mental de Memória (Stack & Heap) no Momento 4 e 5

```text
========================================================================================
MEMÓRIA STACK (PILHA DE EXECUÇÃO)            MEMÓRIA HEAP (MONTE DINÂMICO)
========================================================================================
[Quadro do método main]
  car = Endereço: 0x6acbcfc0 ------------->  [OBJETO REAL NA POSIÇÃO 0x6acbcfc0]
                                             +-----------------------------------------+
                                             | make:       "Ford"                      |
                                             | model:      "Mustang"                   |
                                             | year:       2025                        |
                                             | price:      58000.99                    |
                                             | isRunning:  false                       |
                                             +-----------------------------------------+

Ao fazer System.out.println(car):
-> Imprime o endereço do ponteiro: Car@6acbcfc0

Ao fazer System.out.println(car.model):
-> Segue o ponteiro até o Heap, entra na gaveta 'model' e imprime: Mustang
========================================================================================
```

---

## 🔍 Análise Linha por Linha de Todo o Código

### Arquivo 1: [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java)

```java
1: public class Car {
2: 
3:     String make = "Ford";
4:     String model = "Mustang";
5:     int year = 2025;
6:     double price = 58000.99;
7:     boolean isRunning = false;
8: }
```

* **Linha 1 (`public class Car {`):**  
  * O que faz: Declara o molde público chamado `Car`.  
  * No console: Nada.
* **Linha 2 (Linha em branco):**  
  * O que faz: Espaçamento para clareza visual.  
  * No console: Nada.
* **Linha 3 (`String make = "Ford";`):**  
  * O que faz: Cria o atributo de texto `make` e define o valor padrão inicial como `"Ford"`.  
  * No console: Nada.
* **Linha 4 (`String model = "Mustang";`):**  
  * O que faz: Cria o atributo de texto `model` e define o valor padrão inicial como `"Mustang"`.  
  * No console: Nada.
* **Linha 5 (`int year = 2025;`):**  
  * O que faz: Cria o atributo numérico inteiro `year` com valor `2025`.  
  * No console: Nada.
* **Linha 6 (`double price = 58000.99;`):**  
  * O que faz: Cria o atributo numérico fracionário `price` com valor `58000.99`.  
  * No console: Nada.
* **Linha 7 (`boolean isRunning = false;`):**  
  * O que faz: Cria o atributo de bandeira booleana `isRunning` indicando que o carro inicia desligado (`false`).  
  * No console: Nada.
* **Linha 8 (`}`):**  
  * O que faz: Fecha a classe `Car`.  
  * No console: Nada.

---

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         // Object = An entity that holds data (attributes)
 5:         //          and can perform actions (methods)
 6:         //          It is a reference data type
 7: 
 8:         Car car = new Car();
 9: 
10:         System.out.println(car.model);
11:     }
12: }
```

* **Linha 1 (`public class Main {`):** Declara a classe principal executável.  
* **Linha 2 (`public static void main(String[] args) {`):** Ponto de entrada do programa.  
* **Linhas 4-6:** Comentários com a definição de Objeto.  
* **Linha 8 (`Car car = new Car();`):**  
  * O que faz: Instancia (fabrica) um novo objeto do tipo `Car` no Heap usando o operador `new` e guarda a referência na variável `car`.  
  * No console: Nada visível.
* **Linha 9 (Linha em branco):** Separação para legibilidade.  
* **Linha 10 (`System.out.println(car.model);`):**  
  * O que faz: Navega através do operador ponto (`.`) até o atributo `model` do objeto `car` e imprime seu conteúdo no terminal.  
  * No console: `Mustang`
* **Linha 11 (`}`):** Fecha o método `main`.  
* **Linha 12 (`}`):** Fecha a classe `Main`.

---

## 📜 Código-Fonte Atual dos Arquivos do Projeto

### [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java)
```java
public class Car {

    String make = "Ford";
    String model = "Mustang";
    int year = 2025;
    double price = 58000.99;
    boolean isRunning = false;
}
```

### [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
```java
public class Main {
    public static void main(String[] args) {

        // Object = An entity that holds data (attributes)
        //          and can perform actions (methods)
        //          It is a reference data type

        Car car = new Car();

        System.out.println(car.model);
    }
}
```

---

## ❓ Perguntas Frequentes de Iniciantes (Dúvidas Cruciais)

### 1. Se eu rodar o programa no meu computador, o código hash `Car@6acbcfc0` vai ser exatamente o mesmo?
**Resposta:**  
**NÃO!** O código hexadecimal depois do `@` depende de onde o sistema operacional e a JVM encontraram um pedaço livre de memória RAM no seu computador naquele exato milissegundo. No seu computador pode aparecer `Car@15db9742`, `Car@4eec7777` ou qualquer outra combinação alfanumérica. O importante é saber que aquilo é a **identidade física do objeto na memória**!

---

### 2. Posso alterar o valor de um atributo depois que o carro foi criado?
**Resposta:**  
**SIM, COM CERTEZA!** Os atributos são variáveis normais!  
Você pode escrever a qualquer momento:
```java
car.year = 2026;
car.price = 62000.00;
```
E quando imprimir `car.year`, o console mostrará `2026`!

---

### 3. Por que não precisamos fazer `import Car;` no topo do `Main.java`?
**Resposta:**  
Porque ambos os arquivos (`Car.java` e `Main.java`) estão na **mesma pasta (no mesmo pacote default / diretório)**!  
Quando dois arquivos Java estão na mesma pasta, eles se enxergam automaticamente sem precisar de comando `import`. O `import` só é obrigatório quando a classe vem de outra biblioteca ou de outro pacote (como `java.util.Scanner`).
