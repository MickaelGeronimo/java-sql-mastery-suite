# Capítulo 44: `inheritance` 👨‍👧‍👦 — Parte 4: Herança na Prática! Testando `isAlive`, `eat()` e a Eliminação do Código Duplicado

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 44 — `inheritance 👨‍👧‍👦`  
> **Tempo do Vídeo:** `07:24:25` até `07:24:55` / `12:00:00`  
> **Arquivos do Projeto:** [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java), [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java), [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java) e [Animal.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java)  
> **Objetivo Deste Documento:** Material didático definitivo para iniciantes, projetado para cópia direta no **Google Docs**, recriando as 5 telas do IntelliJ IDEA, comprovando que o cachorro e o gato herdaram variáveis e métodos sem uma única linha redundante e mostrando o contraste entre código limpo vs. código duplicado.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta sequência espetacular, o autor testa ao vivo o poder da herança:
1. Instancia `Dog dog = new Dog();` e `Cat cat = new Cat();`.
2. Acessa a variável `isAlive` em ambos e obtém `true`.
3. Chama o método `eat()` em ambos e o console imprime duas vezes a mensagem da superclasse.
4. Faz uma demonstração dramática em [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java), colando o código repetido para mostrar o horror que seria programar sem herança.

```
+-----------------------------------------------------------------------------------------------+
| [Project] src > Main.java [x] | Animal.java | Dog.java | Cat.java                  [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
|  8 |                                                                                          |
|  9 |         Dog dog = new Dog();                                                             |
| 10 |         Cat cat = new Cat();                                                             |
| 11 |                                                                                          |
| 12 |         dog.eat();                                                                       |
| 13 |         cat.eat();                                                                       |
| 14 |                                                                                          |
| 15 |     }                                                                                    |
| 16 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Terminal Run / Console - Imagem 2]                                                           |
|                                                                                               |
| The animal is eating                                                                          |
| The animal is eating                                                                          |
|                                                                                               |
| Process finished with exit code 0                                                             |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 5 Imagens Explicadas)

#### 🖼️ Imagem 1 (`07:24:25`): Testando o Atributo Herdado `isAlive`
* **Arquivo ativo:** [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java).
* **O que o autor escreveu:**
  ```java
  Dog dog = new Dog();
  Cat cat = new Cat();

  System.out.println(dog.isAlive);
  System.out.println(cat.isAlive);
  ```
* **O que chama a atenção:**  
  Nem `Dog.java` nem `Cat.java` possuem a palavra `isAlive` digitada dentro deles! Mesmo assim, a IDE não dá erro nenhum, porque ambos herdaram esse atributo de `Animal`!

---

#### 🖼️ Imagem 2 (`07:24:45`): Chamando o Método `eat()` e a Saída no Console
* **Arquivo ativo:** [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java).
* **O que mudou no código:**
  * O autor substitui os prints de atributos pelas ações:
    ```java
    dog.eat();
    cat.eat();
    ```
* **Saída no Console (Painel Inferior Run):**
  * O console abre e imprime em verde:
    ```text
    The animal is eating
    The animal is eating
    ```
  * O cachorro comeu! E o gato comeu! Ambos usaram a lógica centralizada escrita em `Animal.java`.

---

#### 🖼️ Imagem 3: A Classe [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java) Limpa e Elegante
* **Arquivo ativo:** [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java).
* **O código exibido:**
  ```java
  public class Cat extends Animal{

  }
  ```
* A palavra `Animal` está selecionada em azul. O autor mostra como o arquivo do gato é enxuto: basta uma linha para herdar tudo o que existe no animal.

---

#### 🖼️ Imagem 4: A Classe [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java) Limpa e Elegante
* **Arquivo ativo:** [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java).
* **O código exibido:**
  ```java
  public class Dog extends Animal{

  }
  ```
* A palavra `Dog` está selecionada em azul. A mesma simplicidade: o cachorro está pronto para ser usado sem duplicação de linhas.

---

#### 🖼️ Imagem 5: O Pesadelo do Código Duplicado (O Contraexemplo Didático)
* **Arquivo ativo:** [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java).
* **O que aparece na tela:**
  * O autor colou todo o código de `Animal` dentro de `Cat`:
    ```java
    public class Cat extends Animal{
        boolean isAlive;

        Animal(){
            isAlive = true;
        }

        void eat(){
            System.out.println("The animal is eating");
        }
    }
    ```
* **Por que o autor fez isso?**  
  Para dar uma lição aos alunos:  
  *"Vejam! Sem herança, você teria que copiar e colar este bloco de código inteiro dentro de Cat.java, depois colar de novo dentro de Dog.java, depois colar de novo dentro de Bird.java... Isso é uma aberração de código duplicado! Com `extends Animal`, nós apagamos tudo isso e mantemos nosso código 100% limpo!"*

---

## 2. A Mágica Explicada para o Iniciante Amador

### 1. De onde o `dog.isAlive` veio se o arquivo `Dog.java` estava vazio?
Quando você escreveu:
```java
public class Dog extends Animal
```
O Java criou um cordão umbilical invisível entre `Dog` e `Animal`.
Quando você pergunta `dog.isAlive`:
1. O Java procura: *"Tem `isAlive` dentro de Dog?"* $\rightarrow$ Não tem.
2. O Java sobe um degrau na árvore genealógica: *"O Dog tem pai?"* $\rightarrow$ Sim, é `Animal`!
3. O Java procura: *"Tem `isAlive` dentro de Animal?"* $\rightarrow$ **Achei!** E devolve o valor `true`!

---

### 2. Por que o valor veio `true` se ninguém chamou o construtor de Animal?
Essa é uma das regras mais bonitas do Java:
> **Toda vez que você cria um objeto de uma classe filha (`new Dog()`), o Java chama AUTOMATICAMENTE o construtor da classe pai (`Animal()`) antes de qualquer outra coisa!**

Pense na biologia: antes de nascer como um "cachorro", ele precisa primeiro ser gerado como um "animal".  
O construtor `Animal()` rodou nas sombras e executou `isAlive = true;`. Por isso, o cachorro e o gato já nasceram vivos!

---

### 3. A Chamada dos Métodos: `dog.eat()` e `cat.eat()`
* O cachorro late, mas ele também come.
* O gato mia, mas ele também come.
* Ao chamar `dog.eat()`, o Java executa o método herdado e imprime: `"The animal is eating"`.
* Ao chamar `cat.eat()`, o Java executa o mesmo método herdado e imprime: `"The animal is eating"`.

---

## 3. Dissecando os Códigos Linha por Linha Numerado

### Arquivo 1: [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)

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
12:         dog.eat();
13:         cat.eat();
14:     }
15: }
```

#### Explicação Linha por Linha de [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):

* **Linhas 1 a 6:** Abertura da classe, método `main` e os comentários conceituais de herança.
* **Linha 9 (`Dog dog = new Dog();`):**  
  Cria uma nova instância de cachorro na memória Heap. O construtor de `Animal` roda nas sombras, garantindo que `isAlive` seja `true`.
* **Linha 10 (`Cat cat = new Cat();`):**  
  Cria uma nova instância de gato na memória Heap. O mesmo construtor de `Animal` roda, garantindo que `cat.isAlive` também seja `true`.
* **Linha 11 (` `):** Linha em branco.
* **Linha 12 (`dog.eat();`):**  
  O objeto `dog` executa o método `eat()` que herdou da classe pai `Animal`.  
  **O que sai no console:**
  ```text
  The animal is eating
  ```
* **Linha 13 (`cat.eat();`):**  
  O objeto `cat` executa o mesmo método `eat()` herdado de `Animal`.  
  **O que sai no console:**
  ```text
  The animal is eating
  ```
* **Linhas 14 e 15:** Fecham o método `main` e a classe `Main`.

---

### Arquivo 2: [Animal.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java) (Superclasse)

```java
 1: public class Animal {
 2: 
 3:     boolean isAlive;
 4: 
 5:     Animal(){
 6:         isAlive = true;
 7:     }
 8: 
 9:     void eat(){
10:         System.out.println("The animal is eating");
11:     }
12: 
13: }
```

* Fornece o modelo genérico: atributo `isAlive`, construtor que define `true` e a ação `eat()`.

---

### Arquivo 3: [Dog.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java) e [Cat.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java) (Subclasses)

```java
public class Dog extends Animal {

}
```

```java
public class Cat extends Animal {

}
```

* Ambas as classes herdam **100% de tudo** o que existe em `Animal`.

---

## 4. O Que Sai no Console?

### No teste com `System.out.println(dog.isAlive);` e `cat.isAlive` (Imagem 1):
```text
true
true
```

### No teste com `dog.eat();` e `cat.eat();` (Imagem 2):
```text
The animal is eating
The animal is eating
```

---

## 5. Tabela Comparativa: Código Sem Herança vs. Código Com Herança

| Cenário | Sem Herança (Código Ruim) | Com Herança (Código Limpo) |
| :--- | :--- | :--- |
| **Linhas no `Dog.java`** | 12 linhas repetidas | Apenas `public class Dog extends Animal {}` |
| **Linhas no `Cat.java`** | 12 linhas repetidas | Apenas `public class Cat extends Animal {}` |
| **E se criarmos 20 animais?** | Teríamos que escrever 240 linhas repetidas | Escrevemos em `Animal` uma vez e herdamos |
| **Para alterar a mensagem de comer:** | Abrir 20 arquivos e mudar um por um | Alterar **apenas 1 linha** no `Animal.java`! |
| **Princípio de Engenharia:** | Violação do DRY (*Don't Repeat Yourself*) | Respeito absoluto às boas práticas da POO |
