# Curso de Java — Capítulo 50: Polimorfismo (`polymorphism 🎭`)
## Parte 2: A Construção do Molde dos Veículos — A Criação da Classe Abstrata `Vehicle.java` e da Primeira Subclasse Concreta `Car.java`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:08:27` até `08:09:21` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a criação prática da base necessária para demonstrar o Polimorfismo. Nesta sequência de 5 capturas de tela, o professor Bro Code cria a superclasse mãe genérica `Vehicle.java`, define o método abstrato `abstract void go();`, cria a primeira classe filha concreta [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) herdando com `extends Vehicle`, e sobrescreve o método `go()` para exibir `"You drive the car"`.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (janelas modais "New Java Class", abas abertas, ícones de sarjeta `(O)↓` e `(I)↑`, e legendas em estilo desenho animado) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 5 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência (`08:08:27` a `08:09:21`), o professor começa a montar a hierarquia de herança que servirá de palco para o Polimorfismo:

### 🖼️ Imagem 1 (`media_1791399765081_4b5c0377.png` — 08:08:27): Criando o Pai — `Vehicle`
* **Aba ativa:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que acontece no IntelliJ:** O professor clica com o botão direito na pasta `src` e escolhe `New` $\rightarrow$ `Java Class`.
* **Janela Modal "New Java Class":**
  * Campo de texto: `Vehicle`
  * Opção selecionada em azul: `Class` (Classe).
* **Legenda no rodapé do vídeo:** Uma grande palavra em fonte de desenho animado branca com contorno preto diz: **`Vehicle`** (Veículo).

---

### 🖼️ Imagem 2 (`media_1791399787800_cada5200.png` — 08:08:45): Definindo `Vehicle.java` como Abstrato
* **Aba ativa:** `Vehicle.java`.
* **Abas abertas:** `Main.java`, `Vehicle.java` (ativa).
* **Árvore do Projeto (à esquerda em `src`):**
  * `Main` (ícone azul `(C)`)
  * `Vehicle` (ícone azul `(C)`)
* **Código digitado em `Vehicle.java`:**
  ```java
  public abstract class Vehicle {

      abstract void go();
  }
  ```
* **O Raciocínio do Professor:** O professor adiciona a palavra `abstract` antes de `class`, e cria o método abstrato `abstract void go();`. Ele explica que "Veículo" é uma ideia genérica; ninguém dirige um "veículo puro", mas sim um carro, uma moto ou um barco específico. Por isso, a classe é um molde abstrato.

---

### 🖼️ Imagem 3 (`media_1791399802212_0de79e9b.png` — 08:08:50): Criando o Primeiro Filho — `Car`
* **Aba ativa:** `Vehicle.java`.
* **O que acontece:** O professor abre novamente o menu de criação de classes.
* **Janela Modal "New Java Class":**
  * Campo de texto: `Car`
  * Opção selecionada: `Class` (com fundo azul).
* **Legenda no rodapé:** O texto animado agora exibe: **`Car`** (Carro).

---

### 🖼️ Imagem 4 (`media_1791399813785_8fa93128.png` — 08:09:00): A Aba `Car.java` Surge no Editor
* **Abas abertas no topo:** `Main.java`, `Vehicle.java` (ativa), `Car.java`.
* **Árvore do Projeto (em `src`):**
  * `Car`
  * `Main`
  * `Vehicle`
* **Ícone de Sarjeta em `Vehicle.java`:**
  * Na linha 1, ao lado de `public abstract class Vehicle`, surge o ícone azul `(O)↓` (círculo azul com seta apontando para baixo).
  * No IntelliJ, este ícone indica que a classe `Vehicle` agora possui subclasses que herdam dela!

---

### 🖼️ Imagem 5 (`media_1791399832085_fad9660f.png` — 08:09:21): Implementando `Car.java`
* **Aba ativa:** [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java).
* **Código completo digitado em `Car.java`:**
  ```java
  public class Car extends Vehicle{

      @Override
      void go(){
          System.out.println("You drive the car");
      }
  }
  ```
* **Ícone de Sarjeta:** Na linha 4, à esquerda de `void go()`, aparece o ícone verde `(I)↑` (comprovando que o método sobrescreve e cumpre o contrato do método abstrato `go()` de `Vehicle`).
* **Cursor de texto:** O cursor vertical está posicionado no final da linha 5, logo após a aspa de fechamento de `"You drive the car"`.

---

## 2. A Analogia Didática: Por que um Molde de Veículo?

Pense na seguinte situação do mundo real:

```
                  +-----------------------------------+
                  |          VEÍCULO (Pai)            |
                  |     (Conceito Abstrato)           |
                  | Regra Obrigatória: todo mundo     |
                  | precisa ter o método: go()        |
                  +-----------------------------------+
                                    |
                                    | extends (Herança)
                                    v
                  +-----------------------------------+
                  |           CARRO (Filho)           |
                  |        (Objeto Concreto)          |
                  | Como ele se move (go)?            |
                  | -> "You drive the car" (Dirigir)  |
                  +-----------------------------------+
```

1. **A Concessionária Maluca:**  
   Imagine que você entra em uma concessionária e diz ao vendedor:  
   > *"Olá! Eu gostaria de comprar um **Veículo**!"*  
   O vendedor vai olhar para você confuso e perguntar:  
   > *"Mas qual veículo? Você quer um **Carro**, uma **Bicicleta** ou um **Barco**? Não existe para vender apenas um 'Veículo' genérico!"*  
2. **É por isso que `Vehicle` é `abstract`:**  
   A classe `Vehicle` serve apenas como um conceito organizador. Ela define que **qualquer veículo que existir no nosso sistema deve saber se mover (`go()`)**, mas deixa para cada veículo específico dizer **como** ele se move.
3. **O Carro (`Car`):**  
   O carro é um veículo real (`concreto`). Quando você chama `go()`, ele diz: *"Você dirige o carro"* (`"You drive the car"`).

---

## 3. Dissecação Linha por Linha: `Vehicle.java`

Vejamos o código completo de `Vehicle.java`:

```java
1:  public abstract class Vehicle {
2:  
3:      abstract void go();
4:  }
```

### Explicação Detalhada de Cada Linha:

* **Linha 1 (`public abstract class Vehicle {`):**
  * `public`: A classe pode ser vista e herdada por qualquer outro arquivo do projeto.
  * `abstract`: **A palavra-chave mágica!** Proíbe qualquer pessoa de fazer `new Vehicle()`. Se alguém tentar criar um veículo genérico, o Java não deixa compilar.
  * `class`: Declara que é uma classe.
  * `Vehicle`: Nome da superclasse (em maiúscula, conforme as convenções do Java).
  * `{`: Abre o bloco da classe.
  * **Na sarjeta do IntelliJ:** Surge o ícone `(O)↓` indicando que esta classe é estendida por subclasses.
  * **Saída no Console:** Nenhuma.

* **Linha 2 (Linha em branco):**
  * Espaço visual de separação.
  * **Saída no Console:** Nenhuma.

* **Linha 3 (`    abstract void go();`):**
  * `abstract`: Significa que este método **não tem corpo `{}`**, não tem código dentro dele. É apenas uma exigência, uma promessa.
  * `void`: O método executa uma ação e não retorna nenhum valor numérico ou texto.
  * `go()`: Nome do método de locomoção.
  * `;`: Ponto e vírgula obrigatório para encerrar a declaração de método abstrato.
  * **Regra Sagrada:** Qualquer classe que estender `Vehicle` será **obrigada** pelo compilador a escrever o corpo do método `go()`.
  * **Saída no Console:** Nenhuma.

* **Linha 4 (`}`):**
  * Fecha a classe `Vehicle`.
  * **Saída no Console:** Nenhuma.

---

## 4. Dissecação Linha por Linha: `Car.java`

Vejamos o código completo de [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java):

```java
1:  public class Car extends Vehicle{
2:  
3:      @Override
4:      void go(){
5:          System.out.println("You drive the car");
6:      }
7:  }
```

### Explicação Detalhada de Cada Linha:

* **Linha 1 (`public class Car extends Vehicle{`):**
  * `public class Car`: Declara a classe pública chamada `Car` (Carro).
  * `extends`: **Palavra de Herança!** Estabelece que `Car` é filha de `Vehicle`.
  * `Vehicle`: A superclasse mãe que `Car` está estendendo.
  * `{`: Abre o bloco da classe `Car`.
  * **Relação Formal:** Dizemos que `Car IS-A Vehicle` (*Carro É-UM Veículo*).
  * **Saída no Console:** Nenhuma.

* **Linha 2 (Linha em branco):**
  * Espaçamento visual limpo.
  * **Saída no Console:** Nenhuma.

* **Linha 3 (`    @Override`):**
  * Anotação que avisa ao compilador: *"Estou sobrescrevendo o método abstrato `go()` herdado da classe `Vehicle`"*.
  * Garante que você não errou a digitação do nome do método.
  * **Saída no Console:** Nenhuma.

* **Linha 4 (`    void go(){`):**
  * A assinatura do método que agora recebe um corpo real com chaves `{}`.
  * **Por que não tem `public` antes de `void`?**  
    Porque na classe mãe `Vehicle.java`, o método foi declarado como `abstract void go();` (visibilidade de pacote padrão, ou *package-private*). Em Java, uma classe filha pode manter o mesmo nível de acesso do pai.
  * Na sarjeta à esquerda, o IntelliJ coloca o ícone verde `(I)↑` confirmando a implementação do método abstrato.
  * **Saída no Console:** Nenhuma na declaração.

* **Linha 5 (`        System.out.println("You drive the car");`):**
  * `System.out.println`: Imprime uma linha de texto no console do terminal.
  * `"You drive the car"`: Texto em inglês que significa *"Você dirige o carro"*.
  * `;`: Ponto e vírgula terminando a instrução.
  * **Saída no Console (quando invocado no main):**
    ```text
    You drive the car
    ```

* **Linha 6 (`    }`):**
  * Fecha o bloco do método `go()`.
  * **Saída no Console:** Nenhuma.

* **Linha 7 (`}`):**
  * Fecha a classe `Car`.
  * **Saída no Console:** Nenhuma.

---

## 5. Dissecação Linha por Linha: `Main.java`

O arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) continua exatamente como preparado na Parte 1, guardando as definições conceituais de polimorfismo:

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
10:     }
11: }
```

* **Linhas 1 a 4:** Declaração da classe e método `main`. *(Console: Nada)*.
* **Linhas 5 a 8:** Comentários conceituais sobre "Muitas Formas" e o tratamento de objetos sob uma superclasse comum. *(Console: Nada)*.
* **Linhas 9 a 11:** Fechamento das chaves. *(Console: Nada)*.

---

## 6. O Que Sai no Console Neste Momento?

Se você rodar o projeto agora pelo `Main.java`:

```text
Process finished with exit code 0
```

* **Por que o console ainda não exibiu `"You drive the car"`?**  
  Porque nós apenas criamos o molde do carro em [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java), mas ainda não instanciamos nenhum carro dentro do método `main`! O Java só executa aquilo que for explicitamente invocado no `main`.
* **Código de saída `0`:** Confirma que tanto `Vehicle.java`, quanto [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) e [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) compilaram perfeitamente sem nenhum erro de sintaxe!

---

## 7. Como Isso Prepara o Palco para o Polimorfismo?

Observe a relação que acabou de nascer no nosso projeto:

| Objeto | Tipo Real na Memória | Ele Pode Ser Tratado Como: |
| :--- | :--- | :--- |
| `Car` | Uma instância da classe `Car` | 1. Um `Car`<br>2. Um `Vehicle` (Superclasse comum!)<br>3. Um `Object` (Raiz do Java) |

Nos próximos minutos do vídeo, o professor Bro Code vai criar mais duas classes irmãs do carro:
1. `Bicycle` (Bicicleta) $\rightarrow$ também herdará de `Vehicle`.
2. `Boat` (Barco) $\rightarrow$ também herdará de `Vehicle`.

E aí virá a grande mágica do **Polimorfismo**:  
Nós poderemos criar um **único array de Veículos** que consegue armazenar um Carro, uma Bicicleta e um Barco juntos no mesmo lugar!

Todos os arquivos no seu computador já foram criados, compilados e sincronizados com 100% de sucesso! 🚀
