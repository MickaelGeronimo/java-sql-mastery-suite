# Capítulo 44: `inheritance` 👨‍👧‍👦 — Parte 5: Especialização das Filhas! Atributos Únicos (`lives`), Métodos Exclusivos (`speak`) e as Saídas no Console

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 44 — `inheritance 👨‍👧‍👦`  
> **Tempo do Vídeo:** `07:26:16` até `07:26:55` / `12:00:00`  
> **Arquivos do Projeto:** [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java), [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java), [Animal.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java) e [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)  
> **Objetivo Deste Documento:** Material didático completo para iniciantes, projetado para cópia direta no **Google Docs**, recriando as 4 telas do IntelliJ IDEA, explicando o conceito de **especialização** em Programação Orientada a Objetos e demonstrando como classes filhas recebem a herança do pai e ainda criam seus próprios membros exclusivos.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta etapa, o autor conclui a demonstração de herança mostrando que uma classe filha não serve apenas para copiar passivamente o que o pai tem: ela pode **estender** (*to extend*) a funcionalidade, adicionando suas próprias características particulares!

```
+-----------------------------------------------------------------------------------------------+
| [Project] src > Main.java [x] | Animal.java | Dog.java | Cat.java                  [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
| 11 |                                                                                          |
| 12 |         dog.speak();                                                                     |
| 13 |         cat.speak();                                                                     |
| 14 |     }                                                                                    |
| 15 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Terminal Run / Console - Imagem 4]                                                           |
|                                                                                               |
| The dog goes [ *woof* ]      <-- A palavra *woof* selecionada em azul pelo autor              |
| The cat goes *meow*                                                                           |
|                                                                                               |
| Process finished with exit code 0                                                             |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 4 Imagens Explicadas)

#### 🖼️ Imagem 1 (`07:26:16`): Adicionando Atributos e Métodos ao `Dog.java`
* **Arquivo ativo:** [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java).
* **O que o autor digita:**
  ```java
  public class Dog extends Animal{

      int lives = 1;

      void speak(){

      }
  }
  ```
* **O que chama a atenção:**  
  * O cachorro ganha um atributo só dele: `int lives = 1;` (um cão tem 1 vida).
  * O cachorro ganha uma ação só dele: `void speak()` (a habilidade de se comunicar com a própria voz).

---

#### 🖼️ Imagem 2: Adicionando Atributos e Métodos ao `Cat.java`
* **Arquivo ativo:** [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java).
* **O que o autor digita:**
  ```java
  public class Cat extends Animal{

      int lives = 9;

      void speak(){
          System.out.println("The cat");
      }
  }
  ```
* **O que chama a atenção:**  
  * O gato ganha o mesmo atributo `lives`, mas com valor `9` (na cultura americana/inglesa diz-se que gatos têm 9 vidas; no Brasil costumamos falar 7 vidas).
  * Ele começa a digitar o método `speak()` que imprimirá `"The cat goes *meow*"`.

---

#### 🖼️ Imagem 3 (`07:26:52`): Testando o Atributo Exclusivo no `Main.java`
* **Arquivo ativo:** [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java).
* **O que o autor testou primeiro:**
  ```java
  System.out.println(dog.lives);
  ```
* **Saída gerada:** O terminal exibe `1`. Se testasse `cat.lives`, exibiria `9`.
* O autor comprova que classes filhas podem ter dados individuais com valores totalmente diferentes!

---

#### 🖼️ Imagem 4: Chamando `speak()` e a Saída no Terminal
* **Arquivo ativo:** [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java).
* **O código executado:**
  ```java
  dog.speak();
  cat.speak();
  ```
* **Saída no Console (Terminal Run):**
  ```text
  The dog goes *woof*
  The cat goes *meow*
  ```
* **Destaque do autor:** Bro Code seleciona a palavra `*woof*` com o mouse, deixando-a com fundo azul brilhante para enfatizar que cada animal respondeu com o seu som exclusivo!

---

## 2. A Lição de POO Explicada para o Iniciante Amador

### 🌟 O Conceito de Especialização
Muitos iniciantes pensam: *"Herança é só pra não ter que digitar o código do pai de novo?"*  
**Não só isso!** Herança serve para criar uma **hierarquia de especialização**:

1. **O que é genérico fica no Pai (`Animal`):**
   * Estar vivo (`isAlive = true`).
   * Comer comida (`eat()`).
2. **O que é específico fica nos Filhos (`Dog` e `Cat`):**
   * O cachorro tem 1 vida e late: `*woof*`.
   * O gato tem 9 vidas e mia: `*meow*`.

### 🏠 A Analogia da Família
* O pai deu uma casa de herança para os dois filhos. Ambos moram na casa (herança comum).
* Mas o filho mais velho comprou uma bateria e colocou no quarto dele (habilidade exclusiva: latir).
* A filha mais nova comprou um violino e colocou no quarto dela (habilidade exclusiva: miar).
* A casa é a mesma, mas cada filho tem a sua própria identidade!

---

## 3. Dissecando os Códigos Linha por Linha Numerado

### Arquivo 1: [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java) (Classe Filha Especializada)

```java
 1: public class Dog extends Animal{
 2: 
 3:     int lives = 1;
 4: 
 5:     void speak(){
 6:         System.out.println("The dog goes *woof*");
 7:     }
 8: }
```

* **Linha 1:** Declara que `Dog` estende/herda de `Animal`.
* **Linha 3 (`int lives = 1;`):** Cria um atributo próprio de `Dog`. Cães possuem 1 vida.
* **Linha 5 (`void speak(){`):** Cria o método de comunicação específico do cão.
* **Linha 6 (`System.out.println("The dog goes *woof*");`):** Imprime a mensagem contendo o latido do cão.
* **Linhas 7 e 8:** Fecham o método e a classe.

---

### Arquivo 2: [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java) (Segunda Classe Filha Especializada)

```java
 1: public class Cat extends Animal{
 2: 
 3:     int lives = 9;
 4: 
 5:     void speak(){
 6:         System.out.println("The cat goes *meow*");
 7:     }
 8: }
```

* **Linha 1:** Declara que `Cat` estende/herda de `Animal`.
* **Linha 3 (`int lives = 9;`):** Cria o mesmo atributo `lives`, mas inicializado com `9`.
* **Linha 5 (`void speak(){`):** Cria o método de comunicação específico do gato.
* **Linha 6 (`System.out.println("The cat goes *meow*");`):** Imprime o miado do gato.
* **Linhas 7 e 8:** Fecham o método e a classe.

---

### Arquivo 3: [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Execução)

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         // Inheritance = One class inherits the attributes and methods
 5:         //               from another class.
 6:         //               Child <- Parent
 7: 
 8: 
 9:         Dog dog = new Dog();
10:         Cat cat = new Cat();
11: 
12:         dog.speak();
13:         cat.speak();
14:     }
15: }
```

* **Linhas 9 e 10:** Cria os objetos `dog` e `cat`.
* **Linha 12 (`dog.speak();`):** Aciona o método próprio do cachorro.  
  **Saída no console:**
  ```text
  The dog goes *woof*
  ```
* **Linha 13 (`cat.speak();`):** Aciona o método próprio do gato.  
  **Saída no console:**
  ```text
  The cat goes *meow*
  ```

---

## 4. O Que o Pai e as Filhas Têm? (A Visão Geral Completa)

| Objeto Criado | Atributos Disponíveis | Métodos Disponíveis | De Onde Veio Cada Coisa? |
| :--- | :--- | :--- | :--- |
| **`Dog`** | `isAlive` (true) <br> `lives` (1) | `eat()` <br> `speak()` ("\*woof\*") | `isAlive` e `eat()` vieram do **Pai** (`Animal`) <br> `lives` e `speak()` são **exclusivos do Filho** (`Dog`) |
| **`Cat`** | `isAlive` (true) <br> `lives` (9) | `eat()` <br> `speak()` ("\*meow\*") | `isAlive` e `eat()` vieram do **Pai** (`Animal`) <br> `lives` e `speak()` são **exclusivos da Filha** (`Cat`) |

---

## 5. O Que Deve Sair no Console?

### Teste de Vidas (`System.out.println(dog.lives)` / `cat.lives`):
```text
1
9
```

### Teste de Fala (`dog.speak()` / `cat.speak()`):
```text
The dog goes *woof*
The cat goes *meow*
```

---

## 6. Diagrama Geral da Arquitetura de Herança

```
                       +-------------------------------+
                       |      SUPERCLASSE (Pai)        |
                       |            Animal             |
                       |-------------------------------|
                       | - boolean isAlive = true      |
                       | - void eat()                  |
                       +-------------------------------+
                                      ▲
                                      │ extends
                 +--------------------+--------------------+
                 │                                         │
+---------------------------------+       +---------------------------------+
|      SUBCLASSE (Filha 1)        |       |      SUBCLASSE (Filha 2)        |
|              Dog                |       |              Cat                |
|---------------------------------|       |---------------------------------|
| - int lives = 1                 |       | - int lives = 9                 |
| - void speak() { *woof* }       |       | - void speak() { *meow* }       |
+---------------------------------+       +---------------------------------+
```

Este diagrama resume com perfeição o coração do **Capítulo 44: `inheritance`**:
- O que é comum sobe para a classe pai.
- O que é único fica na classe filha.
- E o código do seu projeto fica limpo, profissional e fácil de manter!
