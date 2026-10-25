# Curso de Java — Capítulo 51: Polimorfismo em Tempo de Execução (`runtime polymorphism 🤷‍♂️`)
## Parte 2: O Exemplo Prático de Animais (`new Dog() or new Cat()`) e a Janela `Run` com Console Zerado

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:14:45` até `08:15:10` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, de forma simples e descontraída) a preparação do exemplo prático mais famoso de **Polimorfismo Dinâmico**: o professor Bro Code apresenta a ideia de criar um programa onde o usuário escolhe se quer instanciar um Cachorro (`new Dog()`) ou um Gato (`new Cat()`). Em seguida, ele clica no botão verde de execução `▷` para mostrar a janela inferior `Run` do IntelliJ IDEA completamente limpa, exibindo apenas `Process finished with exit code 0`, confirmando que o projeto está pronto para a nova implementação interativa.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (o letreiro `new Dog() or new Cat()` no vídeo, a numeração de linhas de 1 a 9 no editor, a abertura da janela inferior `Run` e a saída da JVM) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 2 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta etapa preparatória (`08:14:45` a `08:15:10`), o professor estabelece a ponte entre a teoria das definições e a prática que está prestes a ser codificada:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:14:49`<br>`media_1791400982989_bde5ae7d.png` | Editor aberto em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Linhas 1 a 9). O cursor pisca no final da Linha 6 (`...actual type of the object.\|`). No meio da tela do vídeo, surge um letreiro estilizado em fonte grossa: **`new Dog() or new Cat()`**. | O professor explica a intuição do exemplo: *"Por exemplo, imagine que nosso programa não sabe de antemão se vai criar um Cachorro (`new Dog()`) ou um Gato (`new Cat()`)!"*. |
| **🖼️ Imagem 2** | `08:15:00`<br>`media_1791401004755_9c2c71fa.png` | A metade inferior da tela do IntelliJ se abre com o painel **`Run`** da aba `Main`. O comando de execução da JVM aparece em cinza escura no topo e, logo abaixo, a mensagem: `Process finished with exit code 0`. | O professor roda a classe `Main` vazia para demonstrar que o ambiente de desenvolvimento está 100% configurado, sem erros antigos e pronto para a criação das novas classes. |

---

## 2. A Ideia do Exemplo: Por Que `new Dog()` ou `new Cat()`?

No capítulo anterior (Capítulo 50), nós instanciamos todos os veículos de uma vez:
```java
Car car = new Car();
Bike bike = new Bike();
Boat boat = new Boat();
```
O código já sabia que existia um carro, uma bicicleta e um barco antes mesmo de você apertar o botão de Play. Não havia surpresa nenhuma!

Mas no mundo real, os programas interagem com seres humanos imprevisíveis. Imagine um jogo de videogame no estilo Pokémon ou RPG:
```
+-------------------------------------------------------------+
| TELA DO JOGO:                                               |
| "Qual mascote você deseja escolher para a sua jornada?"     |
| [ 1 ] - Cachorro                                            |
| [ 2 ] - Gato                                                |
|                                                             |
| Digite sua escolha: _                                       |
+-------------------------------------------------------------+
```

### O Dilema do Compilador Java:
* Quando o Java compila o arquivo `.java` em código `.class`, ele **NÃO SABE** quem vai estar jogando o jogo nem qual número a pessoa vai digitar no teclado!
* O compilador não pode adivinhar se vai ser criado um `Dog` ou um `Cat`.
* É por isso que chamamos de **Tempo de Execução** (*Runtime*): a decisão só será tomada quando o programa já estiver rodando na memória RAM!

```
                                      +--------------------------+
                                      |   USUÁRIO NO TECLADO     |
                                      +--------------------------+
                                                    |
                               +--------------------+--------------------+
                               |                                         |
                        Digitou "1"                               Digitou "2"
                               |                                         |
                               v                                         v
                     +-------------------+                     +-------------------+
                     |    new Dog();     |                     |    new Cat();     |
                     +-------------------+                     +-------------------+
                               |                                         |
                               +--------------------+--------------------+
                                                    |
                                                    v
                                      +--------------------------+
                                      |     animal.speak();      |
                                      +--------------------------+
                                                    |
                               +--------------------+--------------------+
                               |                                         |
                               v                                         v
                         Se for Dog:                               Se for Cat:
                         "woof! 🐶"                                "meow! 🐱"
```

---

## 3. Dissecação Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Linhas 1 a 9)

Aqui está o código exato presente no editor durante as duas imagens:

```java
1 | 
2 | public class Main {
3 |     public static void main(String[] args) {
4 | 
5 |         // Runtime polymorphism = When the method that gets executed is decided
6 |         //                        at runtime based on the actual type of the object.
7 | 
8 |     }
9 | }
```

### Explicação Anatômica de Cada Linha:

* **Linha 1 (` `):** Linha em branco inicial para afastamento visual da barra de título.
* **Linha 2 (`public class Main {`):**
  * `public`: Modificador de acesso que permite que a classe seja acessada por toda a JVM.
  * `class`: Declaração de classe em Java.
  * `Main`: Nome da classe principal.
  * `{`: Início do escopo da classe.
  * *Calha esquerda:* Ícone verde `▷` (Run).
* **Linha 3 (`public static void main(String[] args) {`):**
  * `public`: A JVM tem permissão total para chamar este método de fora.
  * `static`: Não precisa de `new Main()` para existir; roda diretamente.
  * `void`: Não devolve nenhum valor de retorno.
  * `main`: Ponto de partida obrigatório da aplicação.
  * `String[] args`: Vetor para receber parâmetros externos.
  * `{`: Início do escopo do método principal.
  * *Calha esquerda:* Ícone verde `▷` (Run).
* **Linha 4 (` `):** Linha em branco de organização estética.
* **Linha 5 (`// Runtime polymorphism = When the method that gets executed is decided`):**
  * Comentário de documentação: define que o método que será executado é decidido...
* **Linha 6 (`// at runtime based on the actual type of the object.`):**
  * ...em tempo de execução (*runtime*), com base no tipo real (*actual type*) do objeto alocado com `new` na memória RAM.
  * Na Imagem 1, o cursor de texto `|` está exatamente no final desta linha.
* **Linha 7 (` `):** Linha em branco onde o professor vai começar a digitar a criação do objeto nas próximas cenas.
* **Linha 8 (`}`):** Fecha o método `main`.
* **Linha 9 (`}`):** Fecha a classe `Main`.

---

## 4. O Que Aparece no Console? (Análise da Imagem 2)

Na Imagem 2 (`media_1791401004755_9c2c71fa.png`), o professor clica no botão verde `▷` ao lado da Linha 3 para rodar o código.  
Veja exatamente o que surge no painel inferior do IntelliJ IDEA:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.3\lib\idea_rt.jar=51234:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.3\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\HP\IdeaProjects\MyFirstProject\out\production\MyFirstProject" Main

Process finished with exit code 0
```

### Dissecação da Janela `Run` para o Iniciante Amador:

1. **A Linha Superior Gigante em Cinza Escuro (`C:\Users\HP\.jdks...`):**
   * Você não precisa se assustar com essa linha comprida! Ela é apenas o **IntelliJ chamando o Java no Windows nos bastidores**.
   * Ela mostra o executável da JVM (`java.exe`), a versão instalada (`openjdk-23`) e a pasta onde o programa compilado está guardado (`out\production\MyFirstProject`).
2. **O Miolo Vazio do Console:**
   * Nenhuma frase apareceu no meio porque não existe nenhum comando `System.out.println()` dentro do método `main`.
   * As Linhas 5 e 6 são apenas comentários iniciados por `//`, que são 100% ignorados pelo computador.
3. **A Linha Inferior (`Process finished with exit code 0`):**
   * Em todos os sistemas operacionais do planeta (Windows, Linux, macOS), o número **`0`** significa:  
     $$\textbf{exit code 0} = \text{Execução com Sucesso Total (Sem Erros, Sem Falhas!)}$$
   * Se tivesse acontecido algum erro grave (*crash* ou exceção), o código seria diferente de zero (como `exit code 1`).
4. **O Cursor Retangular Branco `|` na Linha Vazia:**
   * É o cursor do console esperando o próximo comando.

---

## 5. O Que Vai Ser Construído a Seguir? (O Roteiro da Aula)

Para demonstrar o `new Dog() or new Cat()`, o professor Bro Code vai construir nos próximos minutos a seguinte estrutura orientada a objetos:

1. **A Classe Pai: [`Animal.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java)**
   * Terá um método genérico `public void speak()` que diz *"the animal goes *brrr*"*.
2. **A Classe Filha 1: `Dog.java`**
   * Herdará de `Animal` (`extends Animal`).
   * Sobrescreverá (`@Override`) o método `speak()` para dizer *"the dog goes *woof*"*.
3. **A Classe Filha 2: [`Cat.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java)**
   * Herdará de `Animal` (`extends Animal`).
   * Sobrescreverá (`@Override`) o método `speak()` para dizer *"the cat goes *meow*"*.
4. **A Interatividade no [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):**
   * Usará um `Scanner` para ler a escolha do usuário no console.
   * Se o usuário escolher 1, instanciará um `new Dog()`.
   * Se o usuário escolher 2, instanciará um `new Cat()`.
   * E em seguida chamará: `animal.speak();`!

---

## 6. Resumo Prático para o Seu Caderno de Estudos (Google Docs)

* **O Letreiro `new Dog() or new Cat()`:** Representa a essência do Polimorfismo Dinâmico. O tipo do objeto a ser criado é uma incógnita em tempo de compilação e só se torna realidade concreta durante a execução (*runtime*).
* **Ambiente Limpo:** O projeto foi resetado para receber um novo ecossistema de classes (`Animal`, `Dog`, `Cat`).
* **Console com `exit code 0`:** Confirma que a classe principal compila e roda com saúde perfeita, pronta para as novas linhas de código.
