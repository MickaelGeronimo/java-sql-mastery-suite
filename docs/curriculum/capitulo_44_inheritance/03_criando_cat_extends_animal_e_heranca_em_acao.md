# Capítulo 44: `inheritance` 👨‍👧‍👦 — Parte 3: Criando `Cat`, a Palavra Mágica `extends` e o Que Foi Herdado de `Animal`

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 44 — `inheritance 👨‍👧‍👦`  
> **Tempo do Vídeo:** `07:23:22` até `07:23:55` / `12:00:00`  
> **Arquivos do Projeto:** Dog.java, [Cat.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java), [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) e [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)  
> **Objetivo Deste Documento:** Guia didático completo para iniciantes, projetado para cópia direta no **Google Docs**, recriando as 4 telas do IntelliJ IDEA, explicando a sintaxe do `extends`, o erro de compilação transitório e a demonstração visual do que a classe filha recebe de presente da classe pai.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta sequência, o autor conecta a teoria à prática:
1. Cria a classe filha Dog.java.
2. Cria a segunda classe filha [Cat.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java).
3. Usa a palavra-chave **`extends`** para estabelecer o vínculo de filiação entre `Dog` e `Animal`.
4. Volta para a classe [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) e **seleciona todo o código em azul** para provar que o `Dog` herdou tudo aquilo sem que precisássemos reescrever uma única vírgula!

```
+-----------------------------------------------------------------------------------------------+
| [Project] src > Animal.java | Dog.java [x] | Cat.java | Main.java                  [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
|  1 | public class Dog extends Animal {                                                        |
|  2 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Aba Animal.java - Imagem 4 com Seleção em Azul]                                              |
|                                                                                               |
|  1 | ⬇  public class Animal {   <-- Ícone de seta para baixo: "Possui subclasses filhas"      |
|  2 |                                                                                          |
|  3 |    [ boolean isAlive;                                                                  ] |
|  4 |    [                                                                                   ] |
|  5 |    [ Animal(){                                                                         ] |
|  6 |    [     isAlive = true;                                                               ] |
|  7 |    [ }                                                                                 ] |
|  8 |    [                                                                                   ] |
|  9 |    [ void eat(){                                                                       ] |
| 10 |    [     System.out.println("The animal is eating");                                   ] |
| 11 |    [ }                                                                                 ] |
| 12 | }                                                                                        |
|         ▲                                                                                     |
|         └── Tudo selecionado em azul pelo autor: "O Dog herdou tudo isso de graça!"          |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 4 Imagens Explicadas)

#### 🖼️ Imagem 1 (`07:23:22`): Finalizando a Criação de `Dog`
* **O que aparece na tela:**
  * Janela `New Java Class` aberta no centro do IntelliJ com o nome `Dog` digitado.
  * O letreiro em fonte grossa cartunesca branca no vídeo anuncia: **`Dog`**.
  * Ao pressionar `Enter`, o arquivo Dog.java é gerado no projeto.

---

#### 🖼️ Imagem 2 (`07:23:27`): Criando a Segunda Classe Filha: `Cat`
* **Arquivo ativo:** Dog.java.
* **O que acontece:**
  * Imediatamente após criar `Dog`, o autor volta a clicar na pasta `src` e abre novamente a janela `New Java Class`.
  * Ele digita o nome: **`Cat`**.
  * No vídeo surge um letreiro gigante cartunesco escrito:  
    # **Cat**
  * Pressiona `Enter` e cria o arquivo [Cat.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java).
  * Agora o projeto possui 4 arquivos na pasta `src`: `Animal.java`, `Cat.java`, `Dog.java` e `Main.java`.

---

#### 🖼️ Imagem 3 (`07:23:47`): A Palavra `extends` e o Erro Vermelho Temporário
* **Arquivo ativo:** Dog.java.
* **O que o autor digitou:**
  ```java
  public class Dog extends {
  }
  ```
* **O que a IDE exibiu na hora:**
  * A palavra **`extends `** ficou sublinhada com uma **ondinha vermelha de erro de compilação**.
  * No canto superior direito do editor, apareceu o aviso de erro: `01 1` (1 erro no arquivo).
* **Por que deu erro vermelho?**
  * A palavra `extends` no Java significa: *"Estende de..."* ou *"Herda de..."*.
  * Se você só escrever `Dog extends {`, o Java fica esperando: *"Herda de quem?! Você esqueceu de me dar o nome da classe pai!"*.
  * Assim que o autor digita `Animal` (`public class Dog extends Animal`), o erro desaparece na mesma hora!

---

#### 🖼️ Imagem 4 (`07:23:55`): O Ícone de Herança e a Seleção em Azul de `Animal.java`
* **Arquivo ativo:** [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
* **O que aparece na tela:**
  * O autor troca para a aba `Animal.java`.
  * **O Ícone na Margem Esquerda (Linha 1):**  
    Ao lado de `public class Animal`, aparece um ícone azul de um círculo com uma setinha apontando para baixo (⬇).  
    No IntelliJ IDEA, esse ícone significa:  
    > **"Is subclassed by Dog"** *(Esta classe possui classes filhas herdando dela!)*.
  * **A Grande Seleção em Azul:**  
    O autor arrasta o mouse e **seleciona todas as linhas de 3 a 11** da classe `Animal`.
  * **O que o autor diz nesse momento:**  
    *"Vejam! Tudo o que está aqui dentro de Animal — a variável `isAlive`, o construtor e o método `eat()` — foi herdado pelo `Dog`! Não precisamos reescrever nada dentro de `Dog.java`!"*

---

## 2. Conceitos Explicados para o Iniciante Amador

### 1. O Que Significa a Palavra `extends`?
No mundo do Java, você nunca vai ver a palavra "herda" ou "inherits" escrita no código. A palavra oficial do Java é **`extends`** (do verbo inglês *to extend*, que significa estender, expandir, ampliar).

```java
public class Dog extends Animal {
}
```

* **Tradução literal para o português:**  
  *"A classe pública Cachorro amplia/estende a classe Animal"*.
* **O que isso significa na prática?**  
  O Cachorro é tudo o que um Animal é (ele herda todas as variáveis e métodos do Animal), mas ele é uma versão **estendida/ampliada**, porque ele poderá ter coisas que só um cachorro tem (como abanar o rabo ou latir).

---

### 2. O Teste de "Clonagem Automática": O Que o `Dog` Tem Agora?
Mesmo que o arquivo Dog.java esteja com o corpo aparentemente vazio:
```java
public class Dog extends Animal {

}
```
Por causa da palavra `extends Animal`, o Java enxerga o `Dog` como se ele tivesse tudo isso lá dentro:

```
+-----------------------------------------------------------+
| O QUE O ARQUIVO Dog.java REALMENTE POSSUI POR BAIXO:      |
|                                                           |
| 1. boolean isAlive;            <-- Herdado de Animal!     |
| 2. Animal() { isAlive = true } <-- Construtor do Pai!     |
| 3. void eat() { ... }          <-- Herdado de Animal!     |
|                                                           |
| E você não precisou digitar NENHUMA dessas linhas nele!   |
+-----------------------------------------------------------+
```

Isso é o que os programadores chamam de **Reutilização de Código** (*Code Reusability*). Você escreve uma vez na classe pai e 100 classes filhas diferentes podem usar de graça!

---

## 3. Dissecando os Códigos Linha por Linha Numerado

### Arquivo 1: [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) (Superclasse / Pai)

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

* **Linha 1:** Declara a classe `Animal`. Agora o IntelliJ coloca o ícone de setinha para baixo na margem esquerda, alertando que ela é estendida por `Dog`.
* **Linhas 3 a 11 (selecionadas em azul pelo autor):**  
  * `boolean isAlive;`
  * `isAlive = true;`
  * `void eat() { System.out.println("The animal is eating"); }`  
  Todas essas linhas agora fazem parte do patrimônio hereditário que `Dog` e `Cat` podem usar livremente.

---

### Arquivo 2: Dog.java (Subclasse / Filho)

```java
1: public class Dog extends Animal {
2: 
3: }
```

* **Linha 1 (`public class Dog extends Animal {`):**  
  * `public class Dog`: Declara a classe pública do cachorro.
  * `extends`: Estabelece a relação de herança.
  * `Animal`: Indica quem é a classe pai que doará seus atributos e métodos.
* **Linhas 2 e 3:** O corpo da classe, que por enquanto não tem código extra, mas já possui tudo de `Animal`.

---

### Arquivo 3: [Cat.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) (Segunda Subclasse)

```java
1: public class Cat {
2: 
3: }
```

* **Linha 1:** Declara a classe do gato, criada nesta etapa. Logo em seguida, o autor também adicionará `extends Animal` ao gato para que ele também herde as mesmas propriedades.

---

## 4. Diagrama da Árvore Genealógica das Classes

```
                          +------------------------+
                          |   CLASSE PAI (Parent)  |
                          |         Animal         |
                          |------------------------|
                          | - boolean isAlive      |
                          | - eat()                |
                          +------------------------+
                                      ▲
                                      │ extends
                    +-----------------+-----------------+
                    │                                   │
        +-----------------------+           +-----------------------+
        | CLASSE FILHA (Child)  |           | CLASSE FILHA (Child)  |
        |          Dog          |           |          Cat          |
        |-----------------------|           |-----------------------|
        | (Herda isAlive)       |           | (Herda isAlive)       |
        | (Herda eat())         |           | (Herda eat())         |
        +-----------------------+           +-----------------------+
```

---

## 5. O Que Deve Sair no Console?

Nesta etapa, como ainda estamos construindo a estrutura de herança entre os arquivos e nenhuma linha foi executada no [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), o terminal permanece limpo:

```text
(Nenhuma saída impressa no console)
Process finished with exit code 0
```

---

## 6. O Que Vem a Seguir?

No próximo passo da aula:
1. O autor conectará também a classe `Cat` com `extends Animal`.
2. Voltaremos para o arquivo [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e faremos:
   ```java
   Dog dog = new Dog();
   dog.eat();
   System.out.println(dog.isAlive);
   ```
3. Veremos no console a saída `"The animal is eating"` e `true`, provando que o cachorro comeu e está vivo sem termos digitado esse código no arquivo do cachorro!
