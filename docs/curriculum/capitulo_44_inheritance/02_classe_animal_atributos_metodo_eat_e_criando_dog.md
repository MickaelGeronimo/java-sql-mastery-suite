# Capítulo 44: `inheritance` 👨‍👧‍👦 — Parte 2: Construindo a Superclasse `Animal` (`isAlive`, Construtor, `eat()`) e Criando a Subclasse `Dog`

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 44 — `inheritance 👨‍👧‍👦`  
> **Tempo do Vídeo:** `07:23:10` até `07:23:22` / `12:00:00`  
> **Arquivos do Projeto:** [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java), Dog.java e [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)  
> **Objetivo Deste Documento:** Material didático completo para iniciantes, projetado para cópia direta no **Google Docs**, recriando as 4 telas do IntelliJ IDEA, explicando a montagem da superclasse genérica, a inicialização do estado de vida, a habilidade de comer e a regra de ouro do relacionamento "É UM" (*IS-A*).

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta etapa, o instrutor Bro Code preenche a classe [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) com as características e comportamentos universais que qualquer animal possui, e em seguida cria o primeiro animal específico: a classe Dog.java.

```
+-----------------------------------------------------------------------------------------------+
| [Project] MyFirstProject > src > Main.java | Animal.java [x]                       [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
|  1 | public class Animal {                                                                    |
|  2 |                                                                                          |
|  3 |     boolean isAlive;                                                                     |
|  4 |                                                                                          |
|  5 |     Animal(){                                                                            |
|  6 |         isAlive = true;                                                                  |
|  7 |     }                                                                                    |
|  8 |                                                                                          |
|  9 |     void eat(){                                                                          |
| 10 |         System.out.println("The animal is eating");                                      |
| 11 |     }                                                                                    |
| 12 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Janela Modal de Criação de Classe - Imagem 4]                                                |
|                                                                                               |
|                         +-----------------------------------+                                 |
|                         | New Java Class                    |                                 |
|                         | [ Dog                           ] |                                 |
|                         +-----------------------------------+                                 |
|                         | (C) Class                         |  <-- Selecionado em azul        |
|                         | (I) Interface                     |                                 |
|                         | (R) Record                        |                                 |
|                         | (E) Enum                          |                                 |
|                         +-----------------------------------+                                 |
|                                                                                               |
|                                       Dog                                                     |
|                              (Texto em destaque no vídeo)                                     |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 4 Imagens Explicadas)

#### 🖼️ Imagem 1 (`07:23:10`): Zoom na Digitação do `println`
* **O que aparece na tela:**
  * O vídeo aplica um zoom focado no editor de código enquanto o autor digita o método `eat()`:
    ```java
    println("The animal is easting");
    ```
  * O autor cometeu um pequeno erro de digitação (*typo*), escrevendo `"easting"` com a letra `s`.
  * O cursor de texto vertical está logo após a letra `g`, pronto para apagar o `s` e deixar `"eating"` correto.
  * No meio esquerdo da tela aparece o ícone `< - 5` indicando que o autor (ou o usuário) voltou 5 segundos no player do YouTube.

---

#### 🖼️ Imagem 2 (`07:23:10`): Visão Completa da Classe `Animal.java`
* **Arquivo ativo:** [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
* **O que vemos no código:**
  * Linha 1: `public class Animal {`
  * Linha 3: `boolean isAlive;` $\rightarrow$ variável booleana que indica se o animal está vivo.
  * Linhas 5-7: O construtor `Animal()` definindo `isAlive = true;`.
  * Linhas 9-11: O método `void eat()` imprimindo a mensagem de que o animal está comendo.
* **Barra inferior do YouTube:** O marcador marca exatamente `7:23:10 / 12:00:00` no capítulo `44.inheritance 👨‍👧‍👦 >`.

---

#### 🖼️ Imagem 3 (`07:23:19`): Abrindo o Menu para Criar a Subclasse
* **Arquivo ativo:** [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
* **O que mudou no código:**
  * A correção ortográfica foi concluída: agora está devidamente escrito `"The animal is eating"`.
  * O autor clicou com o botão direito na pasta `src` ou usou o atalho `Alt + Insert` para abrir a janelinha modal:
    `New Java Class`.
  * O campo de texto para digitar o nome da classe está em branco com o cursor piscando.

---

#### 🖼️ Imagem 4 (`07:23:22`): Digitando `Dog` e o Letreiro Estilizado
* **O que aparece na tela:**
  * No campo da janela flutuante, o autor digita o nome com inicial maiúscula: **`Dog`**.
  * A opção **`Class`** (marcada com o ícone azul `C`) está selecionada.
  * No centro inferior da tela do vídeo, aparece um letreiro em fonte grossa cartunesca branca com contorno preto escrito:  
    # **Dog**
  * O autor vai pressionar `Enter` para criar o arquivo Dog.java.

---

## 2. A Filosofia da Superclasse Explicada para o Iniciante Amador

Se você está começando agora em Programação Orientada a Objetos, memorize esta regra:

> **A Superclasse (Classe Pai) guarda apenas o que é GENÉRICO e UNIVERSAL.**  
> Ela nunca deve ter coisas específicas de uma espécie só!

### O Que Todo Animal Tem em Comum?
Pergunte a si mesmo:
1. Um cachorro está vivo? **Sim.**
2. Um gato está vivo? **Sim.**
3. Um peixe está vivo? **Sim.**
4. Um cachorro precisa comer para sobreviver? **Sim.**
5. Um gato precisa comer? **Sim.**
6. Um peixe precisa comer? **Sim.**

Como **todos os animais** estão vivos e **todos os animais** comem, essas duas coisas pertencem à superclasse **`Animal`**!

### O Que NÃO Deve Ficar na Classe `Animal`?
* Um cachorro late? **Sim.** Mas um peixe late? **Não!**
* Um gato mia? **Sim.** Mas um cachorro mia? **Não!**
* Portanto, `latir()` e `miar()` **NÃO** podem ficar dentro de `Animal.java`! Eles devem ficar exclusivamente dentro das suas respectivas classes filhas (`Dog` e `Cat`).

---

## 3. A Regra Sagrada da Herança: O Teste do "É UM" (*IS-A Relationship*)

Como você sabe quando deve usar herança na programação de verdade?  
Basta fazer o **Teste da Frase "É UM"**:

```
+---------------------------------------+---------------------+
| Pergunta com "É UM"                   | Resposta / Decisão  |
+---------------------------------------+---------------------+
| Um Cachorro (Dog) É UM Animal?        | ✅ SIM! Usa Herança |
| Um Gato (Cat) É UM Animal?            | ✅ SIM! Usa Herança |
| Um Peixe (Fish) É UM Animal?          | ✅ SIM! Usa Herança |
| Um Carro (Car) É UM Animal?           | ❌ NÃO! Sem Herança |
| Uma Geladeira É UM Animal?            | ❌ NÃO! Sem Herança |
+---------------------------------------+---------------------+
```

Se a frase fizer sentido no mundo real ("Cachorro é um animal"), a herança é perfeita!

---

## 4. Dissecando o Código Linha por Linha Numerado

### Arquivo 1: [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java)

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

#### Explicação Linha por Linha de [Animal.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java):

* **Linha 1 (`public class Animal {`):**  
  Declara a classe pública `Animal`. Esta classe será a **Superclasse (Classe Pai)**.
* **Linha 2 (` `):** Linha em branco.
* **Linha 3 (`boolean isAlive;`):**  
  Declara o atributo booleano `isAlive` (verdadeiro ou falso). Por padrão, variáveis booleanas não inicializadas no Java começam como `false`.
* **Linha 4 (` `):** Linha em branco.
* **Linha 5 (`Animal(){`):**  
  O construtor da classe `Animal`. Esse bloco de instruções é executado automaticamente assim que um animal nasce no programa.
* **Linha 6 (`isAlive = true;`):**  
  Dentro do construtor, definimos `isAlive = true;`. Isso garante que todo animal comece a sua existência no jogo/aplicação com o estado **vivo**!
* **Linha 7 (`}`):** Fecha o construtor.
* **Linha 8 (` `):** Linha em branco.
* **Linha 9 (`void eat(){`):**  
  Declara o método `eat` (comer).  
  * `void`: significa que o método executa uma ação (imprime uma mensagem), mas não devolve nenhum dado com `return`.  
  * `()`: não necessita de nenhum parâmetro para ser chamado.
* **Linha 10 (`System.out.println("The animal is eating");`):**  
  Imprime no console a frase `"The animal is eating"` (O animal está comendo).
* **Linha 11 (`}`):** Fecha o método `eat()`.
* **Linha 12 e 13 (`}`):** Fecham o corpo da classe `Animal`.

---

### Arquivo 2: Dog.java

```java
1: public class Dog {
2: 
3: }
```

#### Explicação de Dog.java:

* **Linha 1 (`public class Dog {`):**  
  Declara a nova classe pública `Dog`. Neste exato instante da Imagem 4, o arquivo acabou de ser criado e ainda está vazio.
* **O que virá a seguir?**  
  No próximo segundo da aula, o autor adicionará a palavra mágica do Java:
  ```java
  public class Dog extends Animal { ... }
  ```
  O `extends` fará a mágica de puxar tudo o que está em `Animal` para dentro de `Dog`!

---

## 5. O Que Deve Sair no Console?

Nesta etapa, como estamos apenas criando as classes e seus métodos sem invocar nenhuma execução no [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), o terminal permanece sem nenhuma saída:

```text
(Nenhuma saída impressa no console)
Process finished with exit code 0
```

---

## 6. O Modelo Mental da Memória que Estamos Construindo

```
             SUPERCLASSE (Pai)
             +-------------------------+
             |         Animal          |
             +-------------------------+
             | - boolean isAlive = true|
             | - void eat()            |
             +-------------------------+
                          ▲
                          │  (Herança que conectaremos via 'extends')
                          │
             SUBCLASSE (Filho)
             +-------------------------+
             |          Dog            |
             +-------------------------+
             | (Vai herdar isAlive)    |
             | (Vai herdar eat())      |
             | + atributos próprios    |
             +-------------------------+
```

No próximo passo, veremos como a palavra **`extends`** faz essa ponte e como um objeto `Dog` poderá comer e verificar se está vivo sem que tenhamos escrito isso dentro de `Dog.java`!
