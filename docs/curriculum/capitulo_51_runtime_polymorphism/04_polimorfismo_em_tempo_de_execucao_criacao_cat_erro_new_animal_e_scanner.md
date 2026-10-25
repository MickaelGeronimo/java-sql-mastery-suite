# Curso de Java — Capítulo 51: Polimorfismo em Tempo de Execução (`runtime polymorphism 🤷‍♂️`)
## Parte 4: A Criação do `Cat`, o Erro Didático do `new Animal()` (*cannot be instantiated*) e a Preparação com `Scanner` e `Animal animal;`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:15:51` até `08:17:22` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, como para quem nunca programou na vida) a sequência didática em que o professor Bro Code cria a classe [`Cat.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java), retorna ao [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e demonstra um erro proposital clássico: tentar instanciar a classe abstrata (`new Animal();`). Em seguida, o professor mostra como o compilador aceita `Animal animal = new Dog();` e, finalmente, prepara o terreno para o Polimorfismo Dinâmico importando o `Scanner` e declarando uma variável de referência vazia: `Animal animal;`.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (os popups de criação, as mensagens de erro em balão flutuante, as linhas sublinhadas de vermelho, as correções e a declaração de variáveis) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 5 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência de transição crucial (`08:15:51` a `08:17:22`), o professor Bro Code prepara todos os blocos de montar para a interação dinâmica com o usuário:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:15:51`<br>`media_1791401104491_93066df5.png` | Editor em `Dog.java`. Popup **"New Java Class"** aberto. Letreiro gigante no vídeo em fonte comic: **`Cat`**. | O professor clica com o botão direito na pasta `src` e cria a segunda classe filha: `Cat`. |
| **🖼️ Imagem 2** | `08:16:24`<br>`media_1791401151134_e6a88114.png` | Editor aberto em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java). Linha 8: `Animal animal = new Animal();` com **sublinhado vermelho ondulado sob `new Animal()`** e uma exclamação vermelha na calha esquerda. Alerta no topo: **`1 erro, 1 aviso`**. | O professor digita propositalmente a instanciação da classe abstrata para demonstrar o que acontece se o programador esquecer as regras de POO. |
| **🖼️ Imagem 3** | `08:16:30`<br>`media_1791401163983_d6d649d0.png` | Zoom sobre o balão flutuante de inspeção do IntelliJ: <br>`'Animal' is abstract; cannot be instantiated`. | O professor passa o mouse sobre o erro vermelho e enfatiza verbalmente: *"Lembre-se: Animal é abstrato, ele não pode ser instanciado diretamente!"*. |
| **🖼️ Imagem 4** | `08:16:45`<br>`media_1791401215240_78b41763.png` | Em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), a Linha 8 é alterada para: `Animal animal = new Dog();`. **O erro vermelho desaparece instantaneamente!** | O professor demonstra que a variável de tipo genérico `Animal` aceita alegremente um objeto concreto do tipo `Dog`. |
| **🖼️ Imagem 5** | `08:17:22`<br>`media_1791401230246_0ad199ac.png` | [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) preparado para receber a escolha do usuário: Linha 1: `import java.util.Scanner;`, Linha 9: `Scanner scanner = new Scanner(System.in);`, Linha 11: `Animal animal;`. | O professor remove o `new Dog()` fixo e apenas **declara** a variável `Animal animal;` sem instanciar nada ainda, aguardando o input do teclado! |

---

## 2. A Filosofia do Polimorfismo Dinâmico: A Preparação da "Caixa Vazia"

Preste muita atenção nesta evolução mental que o professor Bro Code construiu nas imagens 2, 4 e 5:

```
+---------------------------------------------------------------------------------------------------------+
|                                    A EVOLUÇÃO DO CÓDIGO NO MAIN.JAVA                                    |
|                                                                                                         |
|  1. TENTATIVA ERRADA (Imagem 2 e 3):                                                                    |
|     Animal animal = new Animal();     ---> 🚨 ERRO: 'Animal' is abstract; cannot be instantiated!       |
|                                                                                                         |
|  2. POLIMORFISMO ESTÁTICO (Imagem 4):                                                                  |
|     Animal animal = new Dog();        ---> 🟢 FUNCIONA! Mas ainda é fixo no código (sempre é Dog).      |
|                                                                                                         |
|  3. POLIMORFISMO DINÂMICO (Imagem 5):                                                                  |
|     Scanner scanner = new Scanner(System.in);                                                           |
|     Animal animal;                    ---> 🧠 A CHAVE DE OURO: Criamos apenas a gaveta vazia!          |
|                                            Quem vai preencher essa gaveta? O USUÁRIO NO SCANNER!        |
+---------------------------------------------------------------------------------------------------------+
```

### Por Que Declarar `Animal animal;` Sem o `new`?
Se você é iniciante, pode pensar: *"Mas pode declarar uma variável sem dar `new` nela?"*  
**SIM, E É EXATAMENTE ISSO QUE TORNA O POLIMORFISMO EM TEMPO DE EXECUÇÃO POSSÍVEL!**

* Pense na variável `animal` como um **crachá** ou uma **caixa organizadora** rotulada com o nome `"Animal"`.
* Se o usuário digitar `1` no teclado, nós colocamos um `new Dog()` dentro dessa caixa:
  ```java
  animal = new Dog();
  ```
* Se o usuário digitar `2` no teclado, nós colocamos um `new Cat()` dentro dessa mesma caixa:
  ```java
  animal = new Cat();
  ```
* Se tivéssemos colocado `new Dog()` logo de cara na Linha 11, o programa já nasceria amarrado a um cachorro, tirando a liberdade de escolha do usuário!

---

## 3. Dissecação Código por Código, Linha por Linha

### 3.1 Arquivo: [`Cat.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) (Classe Filha Concreta)

Criado a partir da Imagem 1, segue a mesma estrutura perfeita do cachorro:

```java
1 | public class Cat extends Animal{
2 | 
3 |     @Override
4 |     void speak(){
5 |         System.out.println("The cat goes *meow*");
6 |     }
7 | }
```

* **Linha 1 (`public class Cat extends Animal{`):**
  * `Cat` herda de `Animal`.
* **Linha 3 (`@Override`):**
  * Informa a sobrescrita do método `speak()`.
* **Linha 4 (`void speak(){`):**
  * Assinatura do método compatível com a superclasse.
* **Linha 5 (`System.out.println("The cat goes *meow*");`):**
  * Imprime o miado do gato (*meow*).
* **Linhas 6 e 7:** Fecham o método e a classe.

---

### 3.2 O Erro Didático em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Imagens 2 e 3)

```java
8 | Animal animal = new Animal();  // <-- 🚨 ERRO DE COMPILAÇÃO!
```

* **O que o programador tentou fazer?** Criar um objeto diretamente da classe `Animal`.
* **O que o IntelliJ acusou no balão de erro:**
  ```text
  'Animal' is abstract; cannot be instantiated
  ```
* **Por que isso é proibido pelo Java?**  
  Porque a classe `Animal` foi marcada com a palavra `abstract` e possui um método incompleto (`abstract void speak();`). Se o Java permitisse fazer `new Animal()`, o que aconteceria se alguém chamasse `animal.speak()`? O computador não saberia o que fazer, porque `Animal` não tem código nenhum dentro de `speak()`! Para proteger o sistema de travar, o Java **proíbe terminantemente** instanciar classes abstratas.

---

### 3.3 A Configuração Pronta em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Imagem 5 — Linhas 1 a 14)

Aqui está a transcrição exata de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) ao final desta sequência:

```java
 1 | import java.util.Scanner;
 2 | 
 3 | public class Main {
 4 |     public static void main(String[] args) {
 5 | 
 6 |         // Runtime polymorphism = When the method that gets executed is decided
 7 |         //                        at runtime based on the actual type of the object.
 8 | 
 9 |         Scanner scanner = new Scanner(System.in);
10 | 
11 |         Animal animal;
12 | 
13 |     }
14 | }
```

* **Linha 1 (`import java.util.Scanner;`):**
  * Importa a ferramenta utilitária `Scanner` do pacote `java.util`, necessária para ler dados digitados pelo usuário no console.
* **Linha 2 (` `):** Linha em branco.
* **Linha 3 (`public class Main {`):** Declaração da classe principal `Main`.
* **Linha 4 (`public static void main(String[] args) {`):** O método de entrada executado pela JVM.
* **Linha 5 (` `):** Linha em branco.
* **Linhas 6 e 7:** Os comentários conceituais de Polimorfismo Dinâmico preservados.
* **Linha 8 (` `):** Linha em branco.
* **Linha 9 (`Scanner scanner = new Scanner(System.in);`):**
  * `Scanner`: Tipo do objeto.
  * `scanner`: Nome da variável.
  * `= new Scanner(System.in);`: Inicializa o leitor conectado à entrada padrão do sistema (`System.in`, que é o teclado do computador).
* **Linha 10 (` `):** Linha em branco.
* **Linha 11 (`Animal animal;`):**
  * **O Ponto Alto da Lição:** Declara uma variável de referência chamada `animal` do tipo da superclasse `Animal`.
  * Ela ainda **não aponta para nenhum objeto na memória** (é uma referência nula no momento).
  * Ela está pronta e aberta para receber tanto um `new Dog()` quanto um `new Cat()`.
* **Linha 12 (` `):** Linha em branco.
* **Linha 13 (`}`):** Fecha o método `main`.
* **Linha 14 (`}`):** Fecha a classe `Main`.

---

## 4. O Que Aparece no Console? (Resultado da Execução)

Se você rodar o programa com o botão de Play verde `▷` ao lado da Linha 3:

```text

Process finished with exit code 0
```

### Por Que o Console Ainda Está Silencioso?
1. O programa inicia a leitura do `Scanner` e reserva a variável `Animal animal;`.
2. Como ainda não escrevemos a pergunta na tela (ex: `System.out.println("What animal do you want?");`), nem o comando para capturar a digitação (ex: `scanner.nextInt();`), o Java passa direto e encerra.
3. `Process finished with exit code 0` confirma que tudo compilou e rodou com **zero erros**.

---

## 5. Resumo Prático para o Google Docs (Para Levar Para a Próxima Aula)

```
                       +-----------------------------------+
                       |        REGRAS DE OURO EM POO      |
                       +-----------------------------------+
                                         |
                +------------------------+------------------------+
                |                                                 |
                v                                                 v
    [ 1. CLASSES ABSTRATAS ]                          [ 2. POLIMORFISMO DINÂMICO ]
     Animal animal = new Animal();                     Animal animal;
     🚨 PROIBIDO! Classes abstratas                   🟢 PERMITIDO! Criar uma variável
     não podem ser instanciadas.                       de referência genérica para receber
                                                       o objeto dinâmico no futuro.
```

1. **Classes abstratas não recebem `new`:** Nunca tente fazer `new` de uma classe abstrata. Use classes filhas concretas (`new Dog()`, `new Cat()`).
2. **Upcasting Polimórfico:** `Animal animal = new Dog();` é totalmente válido porque todo cão é um animal.
3. **A Estratégia do Polimorfismo Dinâmico:** Declaramos a referência pai (`Animal animal;`) antes do bloco de decisão e, conforme o input do usuário capturado pelo `Scanner`, atribuímos o objeto concreto desejado.
