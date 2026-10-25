# Capítulo 44: `inheritance` 👨‍👧‍👦 — Parte 6: Herança Multinível! A Classe Avô `Organism` (*Child <- Parent <- Grandparent*)

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 44 — `inheritance 👨‍👧‍👦`  
> **Tempo do Vídeo:** `07:27:00` até `07:27:55` / `12:00:00`  
> **Arquivos do Projeto:** Organism.java, [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java), Dog.java, [Cat.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) e [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)  
> **Objetivo Deste Documento:** Material didático definitivo para iniciantes, projetado para cópia direta no **Google Docs**, recriando as 4 telas do IntelliJ IDEA, explicando o conceito de **Herança Multinível (em cadeia)**, a promoção de atributos para superclasses ancestrais e a árvore genealógica de 3 gerações em Java.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta etapa, o autor eleva o entendimento de herança a um novo patamar, apresentando a **Herança Multinível (*Multilevel Inheritance*)**: uma classe filha herda de uma classe pai, que por sua vez herda de uma classe avô!

```
+-----------------------------------------------------------------------------------------------+
| [Project] src > Main.java | Organism.java | Animal.java [x] | Dog.java | Cat.java  [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
|  1 | ⬇  public class Animal extends Organism{                                                |
|  2 |                                                                                          |
|  3 |     void eat(){                                                                          |
|  4 |         System.out.println("This animal is eating");                                     |
|  5 |     }                                                                                    |
|  6 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Main.java - Linha do Comentário Atualizada na Imagem 1]                                      |
|                                                                                               |
|  7 | //               Child <- [ Parent ] <- Grandparent                                      |
|                                   └─ Selecionado em azul pelo autor                           |
+-----------------------------------------------------------------------------------------------+
| [Janela Modal de Criação de Classe - Imagem 2]                                                |
|                                                                                               |
|                         +-----------------------------------+                                 |
|                         | New Java Class                    |                                 |
|                         | [ Organism                      ] |                                 |
|                         +-----------------------------------+                                 |
|                         | (C) Class                         |  <-- Selecionado em azul        |
|                         +-----------------------------------+                                 |
|                                                                                               |
|                                     Organism                                                  |
|                              (Texto em destaque no vídeo)                                     |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 4 Imagens Explicadas)

#### 🖼️ Imagem 1: A Linha Genealógica Completa (*Child <- Parent <- Grandparent*)
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que mudou no comentário:**
  * Na linha 7, o autor expandiu a explicação:
    ```java
    //               Child <- Parent <- Grandparent
    ```
  * Ele selecionou com o mouse a palavra **`Parent`** com fundo azul brilhante.
  * **O que o autor explica:**  
    Herança não para em pai e filho! Podemos ter uma linhagem completa:  
    *Filho herda do Pai, e o Pai herda do Avô!*

---

#### 🖼️ Imagem 2 (`07:27:38`): Criando a Classe Ancestral `Organism`
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que acontece:**
  * O autor abre a janela do IntelliJ `New Java Class`.
  * Digita o nome: **`Organism`** (Organismo vivo).
  * No meio do vídeo surge um letreiro gigante cartunesco escrito:  
    # **Organism**
  * O arquivo Organism.java é criado dentro da pasta `src`.

---

#### 🖼️ Imagem 3 (`07:27:48`): Conectando `Animal` a `Organism`
* **Arquivo ativo:** [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
* **O que o autor digita:**
  ```java
  public class Animal extends Organism{
  ```
* A palavra `Organism` está selecionada. Agora `Animal` não é mais a raiz da árvore: ela se torna filha de `Organism`!

---

#### 🖼️ Imagem 4 (`07:27:55`): A Grande Refatoração em `Animal.java`
* **Arquivo ativo:** [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
* **O que sumiu da tela?**
  * As linhas `boolean isAlive;` e o construtor `Animal(){ isAlive = true; }` **foram removidos de `Animal.java`**!
  * O arquivo `Animal.java` ficou com apenas 6 linhas:
    ```java
    public class Animal extends Organism{

        void eat(){
            System.out.println("This animal is eating");
        }
    }
    ```
* **Para onde foram `isAlive` e seu construtor?**  
  Foram movidos para a classe avô **`Organism.java`**!

---

## 2. A Lógica da Herança Multinível Explicada para o Iniciante Amador

### 🌳 Por Que Subir `isAlive` para a Classe `Organism`?
Pense na biologia do mundo real:
* Uma **Planta** (ou uma Árvore) está viva? **Sim!**
* Uma bactéria está viva? **Sim!**
* Mas uma planta come carne ou ração (`eat()`)? **Não!** (Plantas fazem fotossíntese).

Portanto, dizer que *"Estar Vivo"* pertence apenas à classe `Animal` era uma mentira científica e um design ruim de código!  
O conceito de **"Estar Vivo" (`isAlive = true`) pertence a qualquer SER VIVO / ORGANISMO**!

```
                                  NÍVEL 3: AVÔ (Grandparent)
                                  +------------------------+
                                  |        Organism        |
                                  +------------------------+
                                  | - boolean isAlive = true
                                  +------------------------+
                                              ▲
                                              │ extends
                                  NÍVEL 2: PAI (Parent)
                                  +------------------------+
                                  |         Animal         |
                                  +------------------------+
                                  | (Herda isAlive)        |
                                  | - void eat()           |
                                  +------------------------+
                                              ▲
                                              │ extends
                       +----------------------+----------------------+
                       │                                             │
            NÍVEL 1: FILHO (Child)                        NÍVEL 1: FILHA (Child)
            +-----------------------+                     +-----------------------+
            |          Dog          |                     |          Cat          |
            +-----------------------+                     +-----------------------+
            | (Herda isAlive do Avô)|                     | (Herda isAlive do Avô)|
            | (Herda eat() do Pai)  |                     | (Herda eat() do Pai)  |
            | - lives = 1           |                     | - lives = 9           |
            | - speak() { *woof* }  |                     | - speak() { *meow* }  |
            +-----------------------+                     +-----------------------+
```

---

### 👵 A Analogia da Herança de Família
Pense na herança entre três gerações:
1. O seu **Avô** construiu uma fazenda com lagoa (`Organism` $\rightarrow$ `isAlive`).
2. O seu **Pai** herdou a fazenda do seu avô e construiu uma oficina mecânica lá dentro (`Animal` $\rightarrow$ `eat()`).
3. **Você (o Filho / Dog)** nasceu na fazenda:
   * Você tem direito à lagoa do seu avô? **Sim!** (Você herdou do avô em cascata).
   * Você tem direito à oficina do seu pai? **Sim!** (Você herdou do pai).
   * E você ainda comprou o seu próprio skate (`speak()` e `lives = 1`)!

> **Regra de Ouro da Herança Multinível:**  
> A classe neta herda **TUDO** o que está no Pai E **TUDO** o que está no Avô! Não há limite de gerações no Java (pode ter bisavô, tataravô...).

---

## 3. Dissecando os Códigos Linha por Linha Numerado

### Arquivo 1: Organism.java (O Avô / Grandparent)

```java
 1: public class Organism {
 2: 
 3:     boolean isAlive;
 4: 
 5:     Organism(){
 6:         isAlive = true;
 7:     }
 8: 
 9: }
```

* **Linha 1:** Declara a classe pública ancestral `Organism`.
* **Linha 3 (`boolean isAlive;`):** A variável que indica o estado vital. Promovida para o topo da hierarquia, pois qualquer organismo vivo possui vida.
* **Linha 5 (`Organism(){`):** O construtor ancestral.
* **Linha 6 (`isAlive = true;`):** Define que todo organismo criado no programa já nasce vivo (`true`).

---

### Arquivo 2: [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) (O Pai / Parent)

```java
 1: public class Animal extends Organism{
 2: 
 3:     void eat(){
 4:         System.out.println("This animal is eating");
 5:     }
 6: 
 7: }
```

* **Linha 1 (`public class Animal extends Organism{`):**  
  `Animal` agora herda tudo de `Organism` (recebe `isAlive`).
* **Linha 3 (`void eat(){`):**  
  Declara o método de alimentação, exclusivo dos animais.
* **Linha 4 (`System.out.println("This animal is eating");`):**  
  Mensagem atualizada pelo autor no vídeo.
* **Linhas 5 a 7:** Fecham o método e a classe.

---

### Arquivo 3: Dog.java e [Cat.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) (Os Netos / Children)

```java
public class Dog extends Animal{
    int lives = 1;

    void speak(){
        System.out.println("The dog goes *woof*");
    }
}
```

```java
public class Cat extends Animal{
    int lives = 9;

    void speak(){
        System.out.println("The cat goes *meow*");
    }
}
```

* Continuam dizendo apenas `extends Animal`.  
* Mas como `Animal extends Organism`, o `Dog` e o `Cat` continuam tendo acesso direto a `dog.isAlive` e `cat.isAlive`!

---

### Arquivo 4: [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         // Inheritance = One class inherits the attributes and methods
 5:         //               from another class.
 6:         //               Child <- Parent <- Grandparent
 7: 
 8:         Dog dog = new Dog();
 9:         Cat cat = new Cat();
10: 
11:         dog.speak();
12:         cat.speak();
13:     }
14: }
```

- Se você adicionar `System.out.println(dog.isAlive);` aqui dentro, o Java imprimirá `true` com sucesso absoluto!

---

## 4. O Que Sai no Console?

Ao executar o [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) com o método `speak()`:

```text
The dog goes *woof*
The cat goes *meow*
```

E se você consultar a vida dos animais com `System.out.println(dog.isAlive);`:
```text
true
```
*(O cachorro continua vivo, mesmo com a variável `isAlive` tendo sido movida lá para a classe Avô `Organism`!)*

---

## 5. Resumo das Lições da Herança Multinível

1. **Herança em Cascata:** Uma classe herda tudo de seu pai, de seu avô, de seu bisavô, e assim por diante.
2. **Design Limpo e Elegante:** Se um atributo serve para mais coisas do que apenas animais (como plantas e árvores), ele deve subir na árvore genealógica para uma superclasse ancestral (`Organism`).
3. **Simplicidade para as Classes Filhas:** O `Dog` e o `Cat` não precisaram mudar nada em seu código! Eles continuam escrevendo apenas `extends Animal`, e o Java cuida de resolver toda a linhagem genealógica automaticamente.
