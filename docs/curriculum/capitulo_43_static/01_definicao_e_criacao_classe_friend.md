# Curso de Java (Bro Code) — Capítulo 43: Static 🤝
## Parte 1: Início do Capítulo, Definição do Modificador `static`, Criação da Classe `Friend.java` e o Primeiro Teste

---

> [!NOTE]
> **Contexto de Estudo para o Google Docs:**
> Este documento foi elaborado com riqueza visual descritiva e didática descomplicada para que você possa copiar e colar diretamente no seu Google Docs. Mesmo sem as imagens originais na tela, você entenderá exatamente cada detalhe visual do IntelliJ IDEA, cada linha de código, o motivo de cada comando e como o Java pensa nos bastidores.

---

## 1. O Que Aconteceu Nesta Sequência de 4 Imagens? (Visão Geral)

Seja muito bem-vindo ao **Capítulo 43 do curso do Bro Code: `static 🤝`**! O cronômetro do vídeo marca entre **7:14:39 e 7:15:28 de 12:00:00**.

Você já deve ter reparado que desde o primeiro dia em que abriu o Java, a palavra **`static`** sempre esteve presente naquela frase famosa:
```java
public static void main(String[] args)
```
Mas afinal, o que raios significa esse tal de **`static`**? É exatamente isso que o professor vai ensinar agora!

Nesta sequência de 4 imagens:
1. **Imagem 1 (`media_1791326411675.png`):** O autor abre o [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e escreve nos comentários a definição técnica oficial do modificador `static`. A palavra `main` na linha 3 está com destaque azul.
2. **Imagem 2 (`media_1791326423217.png` - Timestamp `7:14:39`):** O autor abre a janelinha `New Java Class` do IntelliJ e digita o nome da nova classe do capítulo: **`Friend`** (Amigo!). Na tela do vídeo surge uma legenda grande em branco escrito `Friend`.
3. **Imagem 3 (`media_1791326448115.png` - Timestamp `7:15:04`):** Dentro de [`Friend.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java), ele cria um atributo comum `String name;` e o construtor `Friend(String name)`. Em seguida, clica de volta na aba [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
4. **Imagem 4 (`media_1791326461749.png` - Timestamp `7:15:28`):** No [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), ele cria o primeiro amigo (`friend1 = new Friend("Spongebob");`), manda imprimir o nome na tela e roda o programa. O console exibe em verde: **`Spongebob`**.

---

## 2. Descrição Visual Detalhada das Telas (Para Seu Google Docs)

### 📸 Imagem 1: A Definição Teórica no `Main.java`
* **Aba ativa:** `Main.java`.
* **Linhas 5 a 7 (O Comentário de Ouro):**
  ```java
  // static = Makes a variable or method belong to the class
  //          rather than to any specific object.
  //          Commonly used for utility methods or shared resources.
  ```
* **Destaque:** Na linha 3, a palavra `main` de `public static void main` está com uma caixa de seleção azul cobalto. O autor está apontando para o aluno: *"Vejam, o static sempre esteve aqui no main!"*.

---

### 📸 Imagem 2: Criando a Classe `Friend`
* **Tempo do vídeo:** `7:14:39 / 12:00:00`.
* **Janela flutuante central:** `New Java Class`.
* **Campo `Name`:** O autor digitou `Friend` (com 'F' maiúsculo em PascalCase).
* **Opção selecionada:** `Class` em azul.
* **Legenda do vídeo:** Letras grandes e brancas no centro inferior escrito `Friend`.

---

### 📸 Imagem 3: O Código Inicial de `Friend.java`
* **Tempo do vídeo:** `7:15:04 / 12:00:00`.
* **Aba ativa:** `Friend.java` (ao lado de `Main.java`).
* **Editor de código (linhas 1 a 8):**
  ```java
  public class Friend {

      String name;

      Friend(String name){
          this.name = name;
      }

  }
  ```
* **Ação do mouse:** O ponteiro do mouse clica na aba superior [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).

---

### 📸 Imagem 4: Instanciando o Bob Esponja e Saída no Console
* **Tempo do vídeo:** `7:15:28 / 12:00:00`.
* **Aba ativa:** `Main.java`.
* **Editor de código:**
  * Linha 9: `Friend friend1 = new Friend("Spongebob");`
  * Linha 11: `System.out.println(friend1.name);`
* **Painel inferior do console (Run):**
  * Linha verde brilhante:
    ```text
    Spongebob
    ```
  * Linha de status: `Checking sources` e execução concluída.

---

## 3. O Que Significa `static`? (Explicado para um Lezado Amador em Java)

Vamos traduzir o comentário do Bro Code palavra por palavra:

```java
// static = Makes a variable or method belong to the class
//          rather than to any specific object.
//          Commonly used for utility methods or shared resources.
```

* **Tradução:**
  > *"static = Faz com que uma variável ou método pertença à CLASSE em si, em vez de pertencer a um objeto específico. Comumente usado para métodos utilitários ou recursos compartilhados."*

### A Analogia do Guarda-Roupa vs O Relógio de Parede:

Pense em uma casa onde moram vários amigos (Bob Esponja, Patrick, Sandy):

1. **Coisas NÃO-STATIC (Coisas Individuais do Objeto):**
   * Cada amigo tem o seu próprio **nome** (`name`).
   * O nome `"Spongebob"` pertence ao Bob. O Patrick não se chama Bob.
   * Se o Bob trocar de roupa, a roupa do Patrick não muda.
   * Isso é **variável de instância** (cada objeto tem a sua gaveta particular).

2. **Coisas STATIC (Coisas da Turma Toda / Compartilhadas):**
   * Imagine que na sala dessa casa tem um **quadro na parede** onde está escrito:
     `"Quantos amigos moram aqui nesta casa?"`
   * Esse quadro na parede não pertence só ao Bob e não pertence só ao Patrick. **Ele pertence à casa toda (à Classe `Friend`)!**
   * Se um novo amigo entra na casa, o número no quadro sobe para todo mundo ao mesmo tempo!
   * Uma variável `static` é exatamente esse quadro na parede: **só existe 1 única cópia na memória**, compartilhada por todos os objetos que existirem!

---

## 4. Código Completo de [`Friend.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java) Linha por Linha

Aqui está o molde inicial da classe `Friend`:

```java
 1: public class Friend {
 2: 
 3:     String name;
 4: 
 5:     Friend(String name){
 6:         this.name = name;
 7:     }
 8: 
 9: }
```

### Explicação Linha por Linha:

* **Linha 1 (`public class Friend {`):** Declara a classe pública `Friend` (o molde de um Amigo). *(Console: nada)*.
* **Linha 3 (`String name;`):**
  * Atributo **não-estático** (individual).
  * Cada amigo que for criado no mundo terá seu próprio nome exclusivo.
  * *(Console: nada)*.
* **Linha 5 (`Friend(String name){`):** Construtor que recebe o nome do amigo no momento do nascimento (`new Friend(...)`).
* **Linha 6 (`this.name = name;`):** Salva o nome recebido dentro do atributo `this.name` do amigo que está nascendo.
* **Linha 7 (`}`):** Fecha o construtor.
* **Linha 9 (`}`):** Fecha a classe `Friend`.

---

## 5. Código Completo de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) Linha por Linha

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         // static = Makes a variable or method belong to the class
 5:         //          rather than to any specific object.
 6:         //          Commonly used for utility methods or shared resources.
 7: 
 8: 
 9:         Friend friend1 = new Friend("Spongebob");
10: 
11:         System.out.println(friend1.name);
12:     }
13: }
```

### Explicação Linha por Linha e Saída no Console:

* **Linha 1 e 2:** Abertura da classe `Main` e método `main`. *(Console: nada)*.
* **Linhas 4 a 6:** Comentários com `//` explicando o conceito de `static`. *(Console: nada)*.
* **Linhas 7 e 8:** Linhas em branco.
* **Linha 9 (`Friend friend1 = new Friend("Spongebob");`):**
  * Instancia o primeiro amigo na memória Heap, com o nome `"Spongebob"`.
  * *(Console: nada — operação de memória)*.
* **Linha 10 (` `):** Linha em branco.
* **Linha 11 (`System.out.println(friend1.name);`):**
  * Manda exibir no terminal o conteúdo do atributo `name` do objeto `friend1`.
  * **Sai no console:**
    ```text
    Spongebob
    ```
* **Linhas 12 e 13 (`}`):** Fecham o método `main` e a classe `Main`.

---

## 6. O Que Sai no Console?

Ao rodar esse programa, a saída no terminal é exatamente 1 linha:

```text
Spongebob
```

---

## 7. O Que Vem a Seguir no Vídeo?

Até este momento, criamos apenas um amigo com uma variável normal (`name`).
Nas próximas imagens, o professor Bro Code irá adicionar a variável mágica **`static int numOfFriends`** (número de amigos) dentro de [`Friend.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java), e veremos como essa variável conta automaticamente cada amigo novo que criamos no `Main.java`!
