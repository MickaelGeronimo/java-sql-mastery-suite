# Curso de Java — Capítulo 50: Polimorfismo (`polymorphism 🎭`)
## Parte 4: O Trio Está Completo! — Criando `Boat.java` (Barco), o Sublinhado Vermelho do Contrato e a Mensagem `"You sail the boat"`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:09:46` até `08:09:59` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a criação da terceira e última classe filha da família de veículos: `Boat.java`. Analisaremos o momento exato em que o professor cria o arquivo, o surgimento do sublinhado vermelho na declaração da classe (uma demonstração visual de que o compilador cobra a implementação do método abstrato `go()`), a resolução do erro ao adicionar o método, o ajuste semântico do verbo em inglês para *"navegar"* (`sail`), e a consolidação da árvore completa de classes necessárias para demonstrar o Polimorfismo no [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (janela modal "New Java Class", o sublinhado ondulado vermelho de erro, a seleção de texto com o mouse, os ícones de sarjeta `(I)↑` e as legendas do vídeo) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 4 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência rápida e rica em detalhes didáticos (`08:09:46` a `08:09:59`), o professor conclui a criação das classes concretas:

### 🖼️ Imagem 1 (`media_1791399886650_687fd993.png` — 08:09:46): Criando a Classe `Boat`
* **Aba ativa:** `Bike.java`.
* **O que acontece:** O professor aciona o menu de criação de classes do IntelliJ IDEA.
* **Janela Modal "New Java Class":**
  * Campo de texto: a letra `B` digitada (ele está escrevendo `Boat`).
  * Opção selecionada em azul: `Class`.
* **Legenda no rodapé do vídeo:** Uma palavra grande em estilo cartoon branco com borda preta diz: **`Boat`** (Barco).

---

### 🖼️ Imagem 2 (`media_1791399898649_1dcf6f53.png` — 08:09:54): O Sublinhado Vermelho do Contrato Quebrado!
* **Aba ativa:** `Boat.java`.
* **Abas abertas no topo:** `Main.java`, `Vehicle.java`, `Car.java`, `Bike.java`, `Boat.java` (ativa).
* **Árvore do Projeto (em `src`):**
  * `Bike`
  * `Boat`
  * `Car`
  * `Main`
  * `Vehicle`
* **O que está escrito no editor:**
  ```java
  public class Boat extends Vehicle{
  }
  ```
* **O Alerta Visual Imediato:**
  * Toda a linha 1 (`public class Boat extends Vehicle{`) está sublinhada com uma **linha ondulada vermelha vivo**!
  * **Por que isso aconteceu?** Porque a classe `Boat` prometeu herdar de `Vehicle.java`, mas ainda não escreveu o código do método abstrato obrigatório `abstract void go();`!
  * O compilador não deixa compilar enquanto a promessa não for cumprida!

---

### 🖼️ Imagem 3 (`media_1791399907506_16b3fd39.png` — 08:09:56): O Erro Some e o Professor Seleciona `car`
* **Aba ativa:** `Boat.java`.
* **O que mudou:** O professor inseriu a estrutura do método `go()`.
* **O Milagre da Compilação:** Assim que o método `go()` foi inserido, **a linha vermelha da Linha 1 desapareceu na mesma hora**!
* **O que o professor faz:** Na linha 5:
  ```java
  System.out.println("You drive the car");
  ```
  O professor clica duas vezes sobre a palavra **`car`** com o mouse, destacando-a em azul escuro, para substituí-la pela palavra própria do barco!

---

### 🖼️ Imagem 4 (`media_1791399915730_a1e38fcd.png` — 08:09:59): O Barco Pronto com `"You sail the boat"`
* **Aba ativa:** `Boat.java`.
* **Código final ajustado:**
  ```java
  public class Boat extends Vehicle{

      @Override
      void go(){
          System.out.println("You sail the boat");
      }
  }
  ```
* **Destaques Visuais da IDE:**
  * Linha 4: À esquerda de `void go()`, aparece o ícone verde `(I)↑` confirmando que o barco implementou o método de locomoção.
  * Linha 5: A frase agora é `"You sail the boat"` (*Você navega/veleja o barco*).

---

## 2. A Analogia Didática: A Tríade da Locomoção e o Contrato da Herança

Observe a harmonia semântica e visual que o professor Bro Code construiu:

```
                            +-------------------------------+
                            |         VEÍCULO (Pai)         |
                            |       (abstract class)        |
                            |      abstract void go();      |
                            +-------------------------------+
                                            |
         +----------------------------------+----------------------------------+
         |                                  |                                  |
         v (extends)                        v (extends)                        v (extends)
+------------------+               +------------------+               +------------------+
|   CARRO (Car)    |               | BICICLETA (Bike) |               |   BARCO (Boat)   |
| Meio: Asfalto    |               | Meio: Ciclovia   |               | Meio: Água       |
| Ação: Dirigir    |               | Ação: Pedalar    |               | Ação: Navegar    |
| "You drive the   |               | "You ride the    |               | "You sail the    |
|  car"            |               |  bike"           |               |  boat"           |
+------------------+               +------------------+               +------------------+
```

### O Verbo Correto para Cada Meio de Transporte:
Em inglês, o professor teve o cuidado didático de usar o verbo exato para cada veículo:
1. **Carro (`Car`):** Você **dirige** um carro $\rightarrow$ `drive` (`"You drive the car"`).
2. **Bicicleta (`Bike`):** Você **anda/pedala** de bicicleta $\rightarrow$ `ride` (`"You ride the bike"`).
3. **Barco (`Boat`):** Você **navega/veleja** um barco $\rightarrow$ `sail` (`"You sail the boat"`).

---

## 3. Dissecação Linha por Linha: `Boat.java`

Vejamos o código completo e final de `Boat.java`:

```java
1:  public class Boat extends Vehicle{
2:  
3:      @Override
4:      void go(){
5:          System.out.println("You sail the boat");
6:      }
7:  }
```

### Explicação Passo a Passo de Cada Linha:

* **Linha 1 (`public class Boat extends Vehicle{`):**
  * `public class Boat`: Declara a classe pública chamada `Boat` (Barco).
  * `extends`: Conecta a classe `Boat` como filha de `Vehicle`.
  * `Vehicle`: A classe mãe que obriga a filha a implementar o método `go()`.
  * `{`: Abre o escopo da classe.
  * **O Erro Didático da Imagem 2:** Quando esta linha foi escrita sem o método `go()` no corpo, ela ficou **sublinhada de vermelho**. O compilador Java avisa: *"Class 'Boat' must either be declared abstract or implement abstract method 'go()' in 'Vehicle'"*. Assim que o método `go()` foi inserido, o erro sumiu!
  * **Relação de Tipos:** `Boat IS-A Vehicle` (*Barco É-UM Veículo*).
  * **Saída no Console:** Nenhuma.

* **Linha 2 (Linha em branco):**
  * Espaço visual para organização.
  * **Saída no Console:** Nenhuma.

* **Linha 3 (`    @Override`):**
  * Anotação que avisa ao compilador que este método substitui o método abstrato herdado do pai.
  * **Saída no Console:** Nenhuma.

* **Linha 4 (`    void go(){`):**
  * Assinatura concreta do método com corpo `{}`.
  * Na sarjeta à esquerda surge o ícone verde `(I)↑` comprovando que o contrato foi pago.
  * **Saída no Console:** Nenhuma na declaração.

* **Linha 5 (`        System.out.println("You sail the boat");`):**
  * Imprime no terminal a frase característica do barco: `"You sail the boat"` (*Você navega o barco*).
  * **Saída no Console (quando invocado no main):**
    ```text
    You sail the boat
    ```

* **Linha 6 (`    }`):**
  * Fecha o método `go()`.
  * **Saída no Console:** Nenhuma.

* **Linha 7 (`}`):**
  * Fecha a classe `Boat`.
  * **Saída no Console:** Nenhuma.

---

## 4. O Que Sai no Console se Executar Agora?

Se você rodar o projeto pelo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

```text
Process finished with exit code 0
```

* **Por que ainda não imprimiu as frases dos veículos?**  
  Porque as três classes de veículos ([`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java), `Bike.java` e `Boat.java`) são apenas "plantas arquitetônicas". Nós ainda não criamos nenhum objeto no [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **Código de saída `0`:** Confirma que todos os 5 arquivos do projeto compilam com perfeição e sem nenhum erro!

---

## 5. Tabela Resumo dos Três Veículos Criados

Veja como as 3 classes filhas compartilham o mesmo pai, mas cada uma tem sua identidade própria:

| Arquivo | Herança | Método Implementado | Saída Própria | Tipo Polimórfico Comum |
| :--- | :--- | :--- | :--- | :--- |
| [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) | `extends Vehicle` | `@Override void go()` | `"You drive the car"` | `Vehicle` |
| `Bike.java` | `extends Vehicle` | `@Override void go()` | `"You ride the bike"` | `Vehicle` |
| `Boat.java` | `extends Vehicle` | `@Override void go()` | `"You sail the boat"` | `Vehicle` |

---

## 6. O Cenário Está Pronto: A Mágica do Polimorfismo vai Começar!

Agora que temos as 3 classes prontas, imagine o seguinte desafio em Java:
> **"Como armazenar um Carro, uma Bicicleta e um Barco dentro do mesmo array?"**

* Se você tentar criar um array de carros:
  ```java
  Car[] racers = { car, bike, boat }; // ERRO! Bike e Boat não são Car!
  ```
* Se você tentar criar um array de bicicletas:
  ```java
  Bike[] racers = { car, bike, boat }; // ERRO! Car e Boat não são Bike!
  ```

**A RESPOSTA POLIMÓRFICA:**
Como todos os três herdam de `Vehicle`, todos eles **SÃO VEÍCULOS** (`IS-A Vehicle`)!
Portanto, nós podemos criar:
```java
Vehicle[] racers = { car, bike, boat }; // 100% VÁLIDO E ELEGANTE!
```
E podemos fazer um loop `for-each` para mandar todos se moverem:
```java
for (Vehicle x : racers) {
    x.go(); // O Carro vai dirigir, a Bike vai pedalar e o Barco vai navegar!
}
```

O seu workspace já contém todos os 5 arquivos atualizados, compilados e testados! Estamos prontos para a próxima etapa no `Main.java`! 🚀
