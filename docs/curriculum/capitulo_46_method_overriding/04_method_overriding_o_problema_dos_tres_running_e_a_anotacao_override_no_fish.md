# Curso de Java — Capítulo 46: Sobrescrita de Método (`method overriding ♻️`)
## Parte 4: O Problema das Três Corridas no Console e a Chegada Triunfal do `@Override` no Peixe

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:43:30` até `07:44:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) o momento em que o professor roda o código e o console exibe `"This animal is running"` para todos os animais (inclusive para o peixe!), a criação do método sobrescrito em `Fish.java`, e a explicação profunda de por que devemos usar a anotação **`@Override`** em Java.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (ícones de gutter, seleções de texto, botões de execução e saídas de terminal) foi minuciosamente convertido em texto e tabelas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: O Choque de Realidade no Console e a Solução

Nas partes anteriores, tínhamos preparado:
* `Dog dog = new Dog();`
* `Cat cat = new Cat();`
* `Fish fish = new Fish();`
* E todos herdando de [`Animal.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java), que possui:
  ```java
  void move(){
      System.out.println("This animal is running");
  }
  ```

Nesta sequência de 4 imagens, o professor Bro Code fecha o ciclo do problema e aplica a solução definitiva da orientação a objetos:

1. **A Execução do Código sem Sobrescrita (Imagens 1 e 2):**  
   O professor roda o programa chamando `dog.move();`, `cat.move();` e `fish.move();`.  
   O console imprime três vezes seguidas:  
   `This animal is running`  
   `This animal is running`  
   `This animal is running`  
   O autor seleciona a frase no console com o mouse e destaca o problema para os alunos: *"Vejam só! O cachorro está correndo, o gato está correndo... e o peixe também está correndo?! Peixes não correm na terra! O peixe precisa nadar!"*.
2. **A Sobrescrita em `Fish.java` (Imagem 3):**  
   Ele abre [`Fish.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Fish.java) e reescreve o método com o mesmo nome exato:
   ```java
   void move(){
       System.out.println("This animal is swimming");
   }
   ```
   Imediatamente, na margem esquerda do editor (gutter), surge um ícone azul com uma setinha para cima indicando: *"Este método está sobrescrevendo o método de Animal!"*.
3. **A Anotação `@Override` (Imagem 4):**  
   Logo acima do método, o professor adiciona a linha:
   ```java
   @Override
   ```
   Ele ensina a regra de boas práticas mais importante do mercado de trabalho: sempre coloque `@Override` quando for sobrescrever um método para que o compilador proteja você de erros de digitação!

---

## 2. Radiografia Visual Minuciosa das 4 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem 1 (`media_1791357136944.png`): O Código Pronto em `Main.java`
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Abas Abertas no Topo:** `Main.java` (ativa), `Animal.java`, `Dog.java`, `Cat.java` e `Fish.java`.
* **Código em `Main.java`:**
  * Linhas 9 a 11: Instanciação de `Dog`, `Cat` e `Fish`.
  * Linha 13: `dog.move();`
  * Linha 14: `cat.move();`
  * Linha 15: `fish.move();`
* **Posição do Cursor e Mouse:**
  * O cursor de texto pisca na linha 15 após `fish.move();`.
  * O ponteiro do mouse repousa sobre o botão verde Play (`Current File ▶`) no canto superior direito, pronto para dar o clique.
* **Marcação de Tempo no Vídeo:** `7:43:35 / 12:00:00` no capítulo `46.method overriding ♻️`.

---

### Imagem 2 (`media_1791357149974.png`): O Console Denuncia o Peixe Corredor
* **Painel Inferior ("Run"):** O terminal do IntelliJ abre e exibe:
  ```text
  This animal is running
  This animal is running
  This animal is running
  
  Process finished with exit code 0
  ```
* **Destaque do Mouse do Professor:**
  * O professor clica e arrasta o mouse sobre a primeira frase `"This animal is running"`, deixando-a selecionada com um retângulo azul de destaque.
  * Ele aponta para o console e ri da situação: *"O peixe herdou cegamente o método do pai, então o programa está dizendo que o peixe corre!"*.

---

### Imagem 3 (`media_1791357178193.png`): Sobrescrevendo o Método em `Fish.java`
* **Aba Ativa:** `Fish.java` (linha azul no topo).
* **Código Digitado:**
  ```java
  public class Fish extends Animal{

      void move(){
          System.out.println("This animal is swimming");
      }
  }
  ```
* **O Ícone Mágico no Gutter (Margem Esquerda):**
  * Na linha 3, ao lado de `void move()`, surge um pequeno círculo azul com um alvo e uma seta apontando para cima (`@O` com seta).
  * No IntelliJ IDEA, clicar nesse ícone leva você instantaneamente para o método original correspondente na classe pai `Animal.java`!
* **A Mensagem Nova:** Em vez de `running`, agora diz `"This animal is swimming"` (*Este animal está nadando*).

---

### Imagem 4 (`media_1791357194930.png`): Adicionando a Anotação `@Override`
* **O que mudou no código:**
  * Na linha 3, antes do método `move()`, o professor adiciona a anotação:
    ```java
    @Override
    ```
* **Destaques Visuais:**
  * A palavra `@Override` aparece em cor verde/ciano de anotação especial da linguagem Java.
  * O ícone do gutter se move para a linha 4.
  * Na linha 2, surge uma pequena bolinha amarela de inspeção da IDE.
* **Marcação de Tempo no Vídeo:** `7:44:28 / 12:00:00`.

---

## 3. O Que É a Anotação `@Override` e Por Que Ela é Tão Importante?

Muitos iniciantes perguntam:
> *"Se na Imagem 3 o método já funcionava sem o `@Override`, por que o professor perdeu tempo digitando `@Override` na Imagem 4?"*

Essa é uma das perguntas mais clássicas de entrevistas de emprego para programadores Java! Vamos entender:

### 1. O `@Override` é Obrigatório para o Programa Rodar?
**Tecnicamente, NÃO.**  
Se você não colocar `@Override`, o Java ainda assim vai sobrescrever o método, desde que o nome e os parâmetros sejam exatamente iguais.

### 2. Então Por Que Todo Programador Profissional É OBRIGADO a Usar?
Por dois motivos vitais:

#### Motivo A: Proteção Contra Erros de Digitação (O Assassinato Silencioso de Bugs)
Imagine que você queira sobrescrever o método `move()`, mas sem querer você escorrega o dedo no teclado e digita:
```java
// ❌ Sem @Override:
void moves(){ // <-- Escorregou e colocou um "s" no final!
    System.out.println("This animal is swimming");
}
```
* **Sem o `@Override`:** O compilador Java acha que você simplesmente inventou um método novo chamado `moves()`. O programa compila sem nenhum aviso! Quando você rodar `fish.move()`, o Java vai chamar o `move()` do pai e o peixe vai continuar correndo! Você vai passar horas procurando onde está o erro no seu código.
* **Com o `@Override`:**
  ```java
  // ✅ Com @Override:
  @Override
  void moves(){ // <-- ERRO IMEDIATO! O COMPILADOR GRITA!
      System.out.println("This animal is swimming");
  }
  ```
  O Java verifica: *"Ei! Você jurou com `@Override` que estava sobrescrevendo um método do pai `Animal`, mas a classe `Animal` NÃO TEM nenhum método chamado `moves()`!"*.  
  O compilador **interrompe na hora** e aponta a linha exata do erro, salvando você de um bug terrível!

#### Motivo B: Legibilidade de Código
Quando outro desenvolvedor abre a classe `Fish.java` e bate o olho em `@Override`, ele sabe instantaneamente: *"Opa, este método não foi inventado aqui; ele veio de uma superclasse e está sendo customizado aqui!"*.

---

## 4. Análise Linha por Linha de Todo o Código

### Arquivo 1: [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Imagens 1 e 2)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // Method overriding = When a subclass provides its own
5:          //                     implementation of a method that is already defined.
6:          //                     Allows for code reusability and give specific implementations
7:  
8:          Dog dog = new Dog();
9:          Cat cat = new Cat();
10:         Fish fish = new Fish();
11: 
12:         dog.move();
13:         cat.move();
14:         fish.move();
15:     }
16: }
```

#### Explicação Detalhada:
* **Linhas 8, 9 e 10:** Criação dos objetos `dog`, `cat` e `fish` na memória RAM.
* **Linha 12 (`dog.move();`):** Executa o método de locomoção do cão. Como `Dog` não sobrescreveu, ele roda o método do pai `Animal`. Saída: `"This animal is running"`.
* **Linha 13 (`cat.move();`):** Executa o método de locomoção do gato. Saída: `"This animal is running"`.
* **Linha 14 (`fish.move();`):**  
  * **Antes de sobrescrever (Imagem 2):** Roda o método do pai e imprime `"This animal is running"`.  
  * **Depois de sobrescrever (Imagem 4):** O Java encontra o método próprio dentro de `Fish` e imprime `"This animal is swimming"`.

---

### Arquivo 2: [`Fish.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Fish.java) (Versão Final com `@Override` — Imagem 4)

```java
1:  public class Fish extends Animal{
2:  
3:      @Override
4:      void move(){
5:          System.out.println("This animal is swimming");
6:      }
7:  }
```

#### Explicação Detalhada:
* **Linha 1 (`public class Fish extends Animal{`):** Declara que `Fish` é subclasse de `Animal`.
* **Linha 3 (`@Override`):**  
  * Anotação oficial do compilador Java.  
  * Avisa à JVM: *"Atenção compilador! Eu estou sobrescrevendo de propósito o método `move()` da minha superclasse `Animal`. Por favor, verifique se o pai realmente tem esse método!"*.
* **Linha 4 (`void move(){`):**  
  * Declaração do método com **exatamente o mesmo nome**, tipo de retorno (`void`) e lista de parâmetros vazia `()` que existe no pai.
* **Linha 5 (`System.out.println("This animal is swimming");`):**  
  * A nova ação especializada exclusiva do peixe: avisa que está nadando!
* **Linhas 6 e 7 (`}` e `}`):** Encerra o método e a classe.

---

## 5. Saída Fiel do Console: O Antes vs. O Depois

### Antes da Sobrescrita (Imagem 2):
```text
This animal is running
This animal is running
This animal is running
```
*(Todos os animais usando a frase genérica do pai).*

---

### Depois da Sobrescrita em `Fish.java` (Imagem 4 em diante):
```text
This animal is running
This animal is running
This animal is swimming
```
*(O cachorro e o gato continuam usando o método padrão do pai, mas o peixe agora usa a sua própria versão especializada!)*

---

## 6. Resumo para Fixação no Google Docs

1. **A hierarquia de busca do Java:** Quando você chama `objeto.metodo()`, o Java procura primeiro **dentro da própria classe do objeto**. Se achar lá, ele executa e para! Ele só sobe para o pai se o método não existir no filho.
2. **Ao sobrescrever, o filho tem prioridade:** Ao declarar `void move()` dentro de `Fish`, o Java usa a versão do peixe e ignora a versão de `Animal`.
3. **Use sempre `@Override`:** É o seu cinto de segurança contra erros de digitação.
