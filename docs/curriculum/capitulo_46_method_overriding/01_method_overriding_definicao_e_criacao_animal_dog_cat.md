# Curso de Java — Capítulo 46: Sobrescrita de Método (`method overriding ♻️`)
## Parte 1: O que é Method Overriding? Definição e Criação das Classes `Animal`, `Dog` e `Cat`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:41:39` até `07:42:15` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) o que significa Sobrescrita de Métodos (*Method Overriding*) em Java, o porquê de existir o emoji de reciclagem ♻️ no título, e o passo a passo da criação da superclasse `Animal` e das duas subclasses irmãs `Dog` e `Cat`.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (menus suspensos, caixas de diálogo modais, atalhos do teclado e legendas de vídeo) foi minuciosamente convertido em texto e tabelas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: Bem-vindo ao Capítulo 46 (`46.method overriding ♻️`)

Após entender como a herança funciona (Capítulo 44) e como passar dados para o pai com `super` (Capítulo 45), chegamos ao terceiro grande pilar da relação entre pais e filhos na programação: **a capacidade de um filho redefinir um comportamento que ele herdou do pai**!

Isso se chama **Method Overriding** (Sobrescrita ou Substituição de Método).

Nesta sequência inaugural de 5 imagens:
1. **O Projeto Limpo e o Conceito em `Main.java` (Imagem 1):**  
   O professor Bro Code limpa a pasta `src`, mantendo apenas [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), e escreve três linhas de comentários que definem com precisão cirúrgica o que é Sobrescrita de Métodos.
2. **Criação da Superclasse `Animal` (Imagens 2 e 3):**  
   Ele abre o menu de criação de classes do IntelliJ e cria a classe genérica [`Animal.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
3. **Criação da Subclasse `Dog` (Imagem 4):**  
   Ele cria a classe `Dog.java`.
4. **Criação da Subclasse `Cat` (Imagem 5):**  
   Ele cria a classe [`Cat.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java).

Ao final desta preparação, o palco está montado: temos uma classe pai (`Animal`) e dois filhos (`Dog` e `Cat`) prontos para demonstrar o que acontece quando o pai ensina uma coisa genérica e cada filho decide fazer do seu próprio jeito!

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem 1 (`media_1791356698619.png`): O Novo Capítulo e a Definição
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Painel Lateral Esquerdo (Project Tree):** A pasta `src` contém apenas o arquivo `Main`.
* **Aba Ativa:** `Main.java`.
* **Código Visível no Editor:**
  ```java
  public class Main {
      public static void main(String[] args) {

          // Method overriding = When a subclass provides its own
          //                     implementation of a method that is already defined.
          //                     Allows for code reusability and give specific implementations

      }
  }
  ```
* **Posição do Cursor:** O cursor de texto pisca na linha 5 logo antes de `Method overriding`.
* **Controles do YouTube no Rodapé:**
  * Marcação de tempo: `7:41:39 / 12:00:00`.
  * Nome do capítulo no índice: `46.method overriding ♻️`.

---

### Imagem 2 (`media_1791356722015.png`): Criando a Superclasse `Animal`
* **Ambiente Visual:** Janela modal flutuante cinza-escuro centralizada na tela com o título `New Java Class`.
* **Campo de Digitação:** O professor começou a digitar: `An`.
* **Opção Selecionada:** A opção `Class` está marcada com uma barra azul.
* **Legenda Gigante de Edição do Vídeo:** Na parte inferior central, aparece um letreiro em letras grossas brancas: **Animal**.
* **Marcação de Tempo no Vídeo:** `7:42:00 / 12:00:00`.

---

### Imagem 3 (`media_1791356734694.png`): O Menu Superior `File -> New`
* **Aba Ativa:** `Animal.java` recém-criada, contendo apenas o esqueleto `public class Animal { }`.
* **Ação do Autor:**
  * No menu superior do IntelliJ, o professor clica em **File**.
  * Abre-se o menu suspenso onde a opção **New >** está selecionada em azul.
  * Ele mostra essa forma alternativa de criar novas classes no projeto (pelo menu superior, além do atalho `Alt + Insert`).

---

### Imagem 4 (`media_1791356745308.png`): Criando a Subclasse `Dog`
* **Ambiente Visual:** A janela modal `New Java Class` abre sobre o arquivo `Animal.java`.
* **Campo de Digitação:** O professor digita: `Do`.
* **Painel Esquerdo (Project Tree):** Agora já vemos na pasta `src` os arquivos: `Animal` e `Main`.
* **Legenda Gigante de Edição do Vídeo:** Surge o letreiro branco garrafal: **Dog**.

---

### Imagem 5 (`media_1791356761331.png`): Criando a Subclasse `Cat`
* **Aba Ativa:** `Dog.java` (no topo vemos as abas: `Main.java`, `Animal.java` e `Dog.java`).
* **Janela Modal Flutuante:** `New Java Class` aberta novamente.
* **Campo de Digitação:** O professor digita: `Cat`.
* **Legenda Gigante de Edição do Vídeo:** Surge o letreiro branco: **Cat**.
* **Marcação de Tempo no Vídeo:** `7:42:11 / 12:00:00`.

---

## 3. O Que É Method Overriding? (Para um Iniciante Absoluto)

Pense na palavra em inglês:
* **Method:** Método, função, ação (ex: emitir som, mover-se, comer).
* **Override:** Passar por cima, atropelar, substituir, reescrever.

### A Metáfora do Mundo Real:
Imagine a seguinte situação:
1. Existe uma classe pai chamada [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java).
2. Todo animal faz algum som quando quer se comunicar. Então, na classe pai, criamos uma ação genérica:
   `move()` ou `speak()` $\longrightarrow$ *"This animal makes a sound"*.
3. Mas se você criar um cachorro (`Dog`) e mandar ele falar, você não quer que apareça na tela *"Este animal faz um som genérico"*. Você quer que ele diga: **`*woof*`** (au-au)!
4. E se você criar um gato ([`Cat`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java)), você quer que ele diga: **`*meow*`** (miau)!

> **Isso é Method Overriding:**  
> A classe pai fornece uma versão genérica padrão.  
> Se a classe filha achar que a versão do pai é genérica demais, **o filho reescreve o mesmo método com o mesmo nome exato**, colocando o seu próprio comportamento especializado dentro!

### Por Que Existe o Emoji de Reciclagem ♻️ no Título?
O professor Bro Code escolheu o emoji ♻️ porque você está **reciclando o mesmo nome de método**! Em vez de inventar um nome diferente para cada animal (`falarComoCachorro()`, `falarComoGato()`), todos eles usam o mesmo nome universal `speak()`, mas cada um executa a sua própria lógica interna reciclada e adaptada!

---

## 4. Dissecando os Comentários do Bro Code em `Main.java`

Vamos analisar linha por linha a explicação que o autor escreveu:

```java
// Method overriding = When a subclass provides its own
//                     implementation of a method that is already defined.
//                     Allows for code reusability and give specific implementations
```

1. **`When a subclass provides its own implementation of a method that is already defined`**:  
   * *"Quando uma subclasse (filha) fornece a sua própria implementação de um método que já foi definido (na superclasse pai)"*.  
   * O método já existia no pai, mas o filho decidiu reprogramá-lo do seu próprio jeito.
2. **`Allows for code reusability and give specific implementations`**:  
   * *"Permite a reutilização de código e fornece implementações específicas"*.  
   * O código que for genérico fica no pai, e o que for especial e único vai para cada filho.

---

## 5. Análise Linha por Linha de Todos os Arquivos Envolvidos

### Arquivo 1: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Imagem 1)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // Method overriding = When a subclass provides its own
5:          //                     implementation of a method that is already defined.
6:          //                     Allows for code reusability and give specific implementations
7:  
8:  
9:      }
10: }
```

* **Linhas 1 e 2:** Declaração clássica da classe `Main` e do método `main`.
* **Linhas 4 a 6:** Comentários conceituais sobre o que é sobrescrita de métodos.
* **Linhas 7 e 8:** Espaço reservado onde os objetos `Animal`, `Dog` e `Cat` serão instanciados e testados nas próximas aulas.
* **Linhas 9 e 10:** Fechamento dos blocos com chaves `}`.

---

### Arquivo 2: [`Animal.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) (Imagens 2 e 3)

```java
1: public class Animal {
2: 
3: }
```

* **Linha 1:** Declara a superclasse pública [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java). Ela servirá de modelo genérico para todos os animais do sistema.
* **Linha 3:** Encerra a classe.

---

### Arquivo 3: `Dog.java` (Imagem 4)

```java
1: public class Dog {
2: 
3: }
```

* **Linha 1:** Declara a classe pública `Dog`. Na próxima etapa, ela receberá `extends Animal`.
* **Linha 3:** Encerra a classe.

---

### Arquivo 4: [`Cat.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) (Imagem 5)

```java
1: public class Cat {
2: 
3: }
```

* **Linha 1:** Declara a classe pública [`Cat`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java). Também receberá `extends Animal`.
* **Linha 3:** Encerra a classe.

---

## 6. O que Sai no Console Nesta Etapa?

Como o professor realizou a preparação das 4 classes e registrou a base teórica nos comentários, nenhuma instrução de impressão (`System.out.println`) foi chamada ainda.

Se compilarmos e executarmos o projeto no terminal:
```text
Process finished with exit code 0
```
* **Significado:** O projeto compila com 0 erros de sintaxe e encerra com código de sucesso!

---

## 7. Resumo para Fixação no Google Docs

| Conceito | O que significa? | Onde ocorre? | Para que serve? |
| :--- | :--- | :--- | :--- |
| **Herança Comum** | O filho usa o método exatamente como o pai fez | Subclasse usando método da Superclasse | Reutilização pura de código |
| **Method Overriding** ♻️ | O filho **reescreve** o método com o mesmo nome e parâmetros | Subclasse criando o mesmo método da Superclasse | Personalizar o comportamento exclusivo do filho |
| **Method Overloading** 🛠️ | Métodos com o **mesmo nome, mas parâmetros diferentes** | Dentro da mesma classe | Flexibilidade ao chamar o método |
