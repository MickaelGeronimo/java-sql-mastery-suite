# 🧱 Capítulo 39: Programação Orientada a Objetos (OOP) em Java — Parte 3
## 🔍 Leitura Completa dos Atributos com o Operador Ponto (`.`): Marca, Modelo, Ano, Preço e Motor

> **Arquivos de Estudo:** `Car.java` e `Main.java`  
> **Timestamp do Vídeo:** `6:46:00` até `6:46:37` / `12:00:00`  
> **Canal:** Bro Code — *Java Full Course for free*  
> **Objetivo:** Explicar para um iniciante absoluto cada detalhe das 5 telas do vídeo: como o professor constrói progressivamente as linhas de impressão no `Main.java`, acessando sistematicamente todas as 5 variáveis guardadas dentro do objeto `car` através do **Operador Ponto (`.`)**, e como o console do terminal exibe sequencialmente os dados de tipos diferentes (`String`, `int`, `double` e `boolean`).

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Sem Precisar do Vídeo)

Se você estiver lendo este material no Google Docs ou impresso, aqui está a descrição minuciosa do que aconteceu na tela do Bro Code em cada um dos 5 momentos da aula:

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 1 (6:46:05): Imprimindo Marca e Modelo no Terminal                                        |
+---------------------------------------------------------------------------------------------------+
| NO CÓDIGO (Main.java):                                                                            |
| Bro Code adiciona a linha de impressão da marca antes do modelo:                                  |
|   System.out.println(car.make);                                                                   |
|   System.out.println(car.model);                                                                  |
|                                                                                                   |
| NO TERMINAL INTEGRADO (Console em Execução):                                                      |
| Ford                                                                                              |
| Mustang                                                                                           |
|                                                                                                   |
| DETALHE VISUAL DO PROFESSOR:                                                                      |
| Bro Code passa o mouse e seleciona com destaque AZUL a palavra "Ford" no console.                |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 2 (6:46:18): Adicionando a Impressão do Ano (car.year)                                    |
+---------------------------------------------------------------------------------------------------+
| NA JANELA DO CÓDIGO (IntelliJ IDEA):                                                              |
| Bro Code digita a terceira linha de impressão:                                                    |
|                                                                                                   |
| 10:         System.out.println(car.make);                                                         |
| 11:         System.out.println(car.model);                                                        |
| 12:         System.out.println(car.year);                                                         |
|                                                                                                   |
| Observação Visual: O cursor do teclado pisca logo após "car.year" e o mouse se move               |
| em direção ao botão verde triangular de Run no canto superior direito da tela.                    |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 3 (6:46:25): Imprimindo o Preço (car.price) no Terminal                                   |
+---------------------------------------------------------------------------------------------------+
| Bro Code executa o programa após adicionar car.price. Veja o que sai no console:                 |
|                                                                                                   |
| Ford                                                                                              |
| Mustang                                                                                           |
| 2025                                                                                              |
| 58000.99                                                                                          |
|                                                                                                   |
| DETALHE VISUAL DO PROFESSOR:                                                                      |
| Bro Code seleciona com destaque AZUL o número decimal "58000.99" no terminal.                     |
| Ele mostra que o número double de ponto flutuante foi lido e impresso com perfeição!              |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 4 (6:46:33): O Código Completo das 5 Impressões no Editor                                 |
+---------------------------------------------------------------------------------------------------+
| Bro Code completa o conjunto de acessos em Main.java:                                             |
|                                                                                                   |
|  8:         Car car = new Car();                                                                  |
|  9:                                                                                               |
| 10:         System.out.println(car.make);                                                         |
| 11:         System.out.println(car.model);                                                        |
| 12:         System.out.println(car.year);                                                         |
| 13:         System.out.println(car.price);                                                        |
| 14:         System.out.println(car.isRunning);                                                    |
|                                                                                                   |
| Observação Visual: O cursor do mouse está sobre o botão Play para rodar o teste definitivo!       |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 5 (6:46:37): A Saída Completa no Console do Terminal                                      |
+---------------------------------------------------------------------------------------------------+
| O terminal integrado ocupa a tela exibindo os 5 atributos em sequência exata:                     |
|                                                                                                   |
| Ford                                                                                              |
| Mustang                                                                                           |
| 2025                                                                                              |
| 58000.99                                                                                          |
| false                                                                                             |
|                                                                                                   |
| Process finished with exit code 0                                                                 |
|                                                                                                   |
| Observação Visual: O cursor pisca bem ao lado da palavra "false", comprovando que o objeto       |
| Car entregou todos os seus 5 atributos com 100% de sucesso!                                       |
+---------------------------------------------------------------------------------------------------+
```

---

## 🧠 Explicação Didática Extrema (Para Leigos Absolutos)

### 1. A Metáfora do "Gaveteiro com 5 Gavetas"

Imagine que o objeto `car` construído na memória é como um **gaveteiro de madeira com 5 gavetas**.  
Cada gaveta tem uma etiqueta de identificação colada na frente:

```
+-------------------------------------------------------+
|                 OBJETO "car" NO HEAP                  |
+-------------------------------------------------------+
|  [ Gaveta: make ]      -->  "Ford"          (Texto)   |
|  [ Gaveta: model ]     -->  "Mustang"       (Texto)   |
|  [ Gaveta: year ]      -->  2025            (Inteiro) |
|  [ Gaveta: price ]     -->  58000.99        (Decimal) |
|  [ Gaveta: isRunning ] -->  false           (Booleano)|
+-------------------------------------------------------+
```

* Na Parte 2, quando nós escrevemos `System.out.println(car);`, nós mandamos o Java imprimir o **móvel inteiro**. O Java olhou e disse: *"Não sei desenhar um gaveteiro na tela, então vou te dar o código de rastreamento de onde ele está na sala: `Car@6acbcfc0`"*.
* Agora, na Parte 3, nós aprendemos a usar o **Operador Ponto (`.`)**.
  * Quando escrevemos `car.make`, nós dizemos: *"Abra o gaveteiro `car` e puxe a gaveta `make`!"*.
  * O Java puxa a gaveta, pega o papel que está lá dentro escrito `"Ford"` e entrega para o `System.out.println`.

---

### 2. A Mágica da Coexistência de Tipos Diferentes

Repare no poder extraordinário da Programação Orientada a Objetos:
* Em uma única entidade (`car`), conseguimos agrupar dados de **4 tipos primitivos e estruturais diferentes**:

| Linha de Código | Nome do Atributo | Tipo em Java | O que Significa no Mundo Real | Valor Impresso no Console |
| :--- | :---: | :---: | :--- | :---: |
| `car.make` | Marca | `String` | Texto com o nome do fabricante | `Ford` |
| `car.model` | Modelo | `String` | Texto com a versão específica | `Mustang` |
| `car.year` | Ano | `int` | Número inteiro com o ano de fabricação | `2025` |
| `car.price` | Preço | `double` | Número com casas decimais (ponto flutuante) | `58000.99` |
| `car.isRunning` | Em movimento | `boolean` | Bandeira de verdadeiro/falso (motor ligado?) | `false` |

> **Comparação Didática:**  
> Se não tivéssemos Orientação a Objetos, para representar 3 carros você teria que criar 15 variáveis soltas (`make1`, `make2`, `make3`, `year1`, `year2`...).  
> Com OOP, você simplesmente cria 3 carros: `car1`, `car2` e `car3`. Cada um deles carrega suas próprias 5 gavetas organizadas!

---

### 3. Por que o Preço Imprime `58000.99` sem o Cifrão `$`?

Muitos iniciantes perguntam:  
*"Professor, por que não apareceu `$58000.99` com o símbolo de dinheiro?"*

**Resposta:**  
Porque em [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) nós declaramos:
```java
double price = 58000.99;
```
Um `double` em Java é uma **variável matemática pura**. Computadores guardam apenas números para poderem fazer contas (como calcular descontos ou impostos).  
Se você colocar um `$` dentro de um `double`, o compilador dará erro de sintaxe!  
Se você quiser que o cifrão apareça na tela do usuário, basta concatenar no `println`:
```java
System.out.println("$" + car.price); // Imprimiria: $58000.99
```

---

## 💾 Modelo Mental de Memória (Stack & Heap) Durante a Impressão

```text
========================================================================================
MEMÓRIA STACK (PILHA)                         MEMÓRIA HEAP (MONTE DINÂMICO)
========================================================================================
[main()]
  car ------------------------------------->  [Objeto Car na posição 0x6acbcfc0]
                                              |-- make:      "Ford"
                                              |-- model:     "Mustang"
                                              |-- year:      2025
                                              |-- price:     58000.99
                                              |-- isRunning: false

Passo a Passo da Execução:
1. car.make      -> Vai até 0x6acbcfc0 -> Lê gaveta 'make'      -> Console: Ford
2. car.model     -> Vai até 0x6acbcfc0 -> Lê gaveta 'model'     -> Console: Mustang
3. car.year      -> Vai até 0x6acbcfc0 -> Lê gaveta 'year'      -> Console: 2025
4. car.price     -> Vai até 0x6acbcfc0 -> Lê gaveta 'price'     -> Console: 58000.99
5. car.isRunning -> Vai até 0x6acbcfc0 -> Lê gaveta 'isRunning' -> Console: false
========================================================================================
```

---

## 🔍 Análise Linha por Linha de Todo o Código Atualizado

### Arquivo: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

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
10:         System.out.println(car.make);
11:         System.out.println(car.model);
12:         System.out.println(car.year);
13:         System.out.println(car.price);
14:         System.out.println(car.isRunning);
15:     }
16: }
```

* **Linha 1 (`public class Main {`):** Declara a classe pública executável `Main`.  
  * No console: Nada.
* **Linha 2 (`public static void main(String[] args) {`):** Porta de entrada do programa.  
  * No console: Nada.
* **Linhas 4-6:** Comentários conceituais sobre a definição de Objeto.  
  * No console: Nada.
* **Linha 8 (`Car car = new Car();`):**  
  * O que faz: Instancia o objeto `car` da classe `Car` no Heap usando `new Car()` e vincula a variável de referência `car` na Stack.  
  * No console: Nada visível.
* **Linha 9 (Linha em branco):** Separação para clareza visual.  
  * No console: Nada.
* **Linha 10 (`System.out.println(car.make);`):**  
  * O que faz: Acessa o atributo `make` do objeto `car` e imprime no terminal.  
  * No console: `Ford`
* **Linha 11 (`System.out.println(car.model);`):**  
  * O que faz: Acessa o atributo `model` do objeto `car` e imprime no terminal.  
  * No console: `Mustang`
* **Linha 12 (`System.out.println(car.year);`):**  
  * O que faz: Acessa o atributo `year` do objeto `car` e imprime no terminal.  
  * No console: `2025`
* **Linha 13 (`System.out.println(car.price);`):**  
  * O que faz: Acessa o atributo `price` do objeto `car` e imprime no terminal.  
  * No console: `58000.99`
* **Linha 14 (`System.out.println(car.isRunning);`):**  
  * O que faz: Acessa o atributo booleano `isRunning` do objeto `car` e imprime no terminal.  
  * No console: `false`
* **Linha 15 (`}`):** Encerra o método `main`.  
* **Linha 16 (`}`):** Encerra a classe `Main`.

---

## 📜 Código-Fonte Completo e Atualizado dos Arquivos

### Arquivo 1: [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java)
```java
public class Car {

    String make = "Ford";
    String model = "Mustang";
    int year = 2025;
    double price = 58000.99;
    boolean isRunning = false;
}
```

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
```java
public class Main {
    public static void main(String[] args) {

        // Object = An entity that holds data (attributes)
        //          and can perform actions (methods)
        //          It is a reference data type

        Car car = new Car();

        System.out.println(car.make);
        System.out.println(car.model);
        System.out.println(car.year);
        System.out.println(car.price);
        System.out.println(car.isRunning);
    }
}
```

---

## ❓ Perguntas Frequentes de Iniciantes (Dúvidas Cruciais)

### 1. E se eu tentar acessar um atributo que não existe, como `car.color`?
**Resposta:**  
O compilador do Java acusará um **erro imediato de compilação**:  
`cannot find symbol: variable color in class Car`  
O Java é uma linguagem fortemente tipada e estrita: você só pode acessar atributos que foram explicitamente declarados dentro da classe `Car.java`!

---

### 2. Posso juntar tudo em uma frase só em vez de usar 5 `println`?
**Resposta:**  
**Com certeza!** Você pode usar concatenação com o operador `+`:
```java
System.out.println("Eu dirijo um " + car.make + " " + car.model + " ano " + car.year);
```
O console exibirá:
```text
Eu dirijo um Ford Mustang ano 2025
```

---

### 3. Até agora vimos que o objeto guarda dados (atributos). E as ações (métodos)? Onde elas entram?
**Resposta:**  
Excelente observação! Lembre-se da definição da linha 5:  
*`// and can perform actions (methods)`*  
Até agora aprendemos como o carro guarda suas características. Nos próximos minutos da aula, Bro Code vai ensinar como dar vida ao carro, criando métodos como `start()` (ligar o motor), `drive()` (dirigir) e `brake()` (frear)!
