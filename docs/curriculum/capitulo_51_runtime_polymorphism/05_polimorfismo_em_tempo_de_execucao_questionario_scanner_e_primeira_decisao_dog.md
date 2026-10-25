# Curso de Java — Capítulo 51: Polimorfismo em Tempo de Execução (`runtime polymorphism 🤷‍♂️`)
## Parte 5: O Questionário Interativo no Console e a Primeira Decisão Dinâmica (`if(choice == 1)`)

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:17:30` até `08:18:28` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, como para quem nunca programou na vida) a construção da primeira bifurcação interativa de Polimorfismo em Tempo de Execução: o professor Bro Code escreve a pergunta no console com `System.out.print`, captura a resposta do usuário através do `scanner.nextInt()` guardando na variável `choice`, e cria a primeira estrutura condicional `if(choice == 1)`. Dentro do bloco, ele finalmente aloca dinamicamente o cachorro na memória (`animal = new Dog();`) e chama `animal.speak();`!  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (a substituição de `println` para `print`, a criação da variável de escolha, a abertura de chaves do `if`, a digitação do `new Dog()` e os cenários de teste do console) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 4 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência dinâmica (`08:17:30` a `08:18:28`), o professor Bro Code transforma um programa estático em um sistema interativo que reage às escolhas do usuário:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:17:30`<br>`media_1791401265481_a3c40dd5.png` | Editor em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java). Linha 13: `System.out.println("Would you like a dog or a cat");`. | O professor começa a escrever a pergunta inicial que o usuário verá no terminal. |
| **🖼️ Imagem 2** | `08:17:45`<br>`media_1791401310688_00a378cc.png` | Linha 13 atualizada com as opções: `"Would you like a dog or a cat? (1 = dog, 2 = cat): "`. O cursor de texto pisca em `println(` para fazer uma edição sutil. | O professor decide trocar `println` por `print` para que a digitação do usuário ocorra exatamente ao lado da pergunta no console. |
| **🖼️ Imagem 3** | `08:18:07`<br>`media_1791401333452_ed95ebc3.png` | Linha 13 finalizada com `print`. Linha 14 adicionada: `int choice = scanner.nextInt();`. Linha 16: `if(choice == 1){` com o cursor piscando dentro do bloco aberto. | O professor cria a variável inteira `choice` para receber o número digitado e abre a verificação condicional para o número 1. |
| **🖼️ Imagem 4** | `08:18:28`<br>`media_1791401358945_3ecbd9f6.png` | Linha 17: `animal = new Dog();`. Linha 18: `animal.speak();`. | **O primeiro momento de Polimorfismo Dinâmico:** a referência genérica `animal` recebe o objeto concreto `new Dog()` e executa a fala do cão! |

---

## 2. A Dissecação Didática para o Iniciante Amador

### 2.1 Por Que Trocar `println` por `print`? (Imagem 1 vs Imagem 2)
Esta é uma dica de ouro de experiência de usuário (*UX*) no terminal:
* **Com `System.out.println(...)`:**  
  O Java imprime a frase e **pula uma linha** imediatamente. O cursor do usuário fica piscando na linha de baixo:
  ```text
  Would you like a dog or a cat? (1 = dog, 2 = cat): 
  _ <-- O usuário digita aqui embaixo (fica feio e distante!)
  ```
* **Com `System.out.print(...)`:**  
  O Java imprime a frase e **mantém o cursor na mesma linha**, logo após o espaço em branco dos dois pontos:
  ```text
  Would you like a dog or a cat? (1 = dog, 2 = cat): _ <-- O usuário digita aqui ao lado (muito mais elegante!)
  ```

---

### 2.2 O Que Faz `int choice = scanner.nextInt();`? (Imagem 3)
* `scanner.nextInt()`: É um comando pausador do Java!
* Quando o computador chega nessa linha, a execução do programa **congela completamente**.
* A CPU fica em compasso de espera, aguardando que a pessoa de carne e osso digite um número inteiro no teclado e pressione a tecla **Enter**.
* Assim que o usuário aperta `Enter`, o número digitado é capturado pelo `Scanner` e guardado dentro de uma nova variável do tipo inteiro chamada `choice` (escolha).

---

### 2.3 A Mágica de `animal = new Dog();` (Imagem 4)

Lembre-se da Linha 11:
```java
Animal animal; // Criamos a gaveta vazia!
```
Agora, na Linha 16 e 17:
```java
if(choice == 1){
    animal = new Dog();  // <-- O cachorro nasce na gaveta!
    animal.speak();      // <-- O cachorro fala!
}
```

```
                                +-----------------------------+
                                |  A GAVETA: Animal animal;   |
                                +-----------------------------+
                                              |
                                   O usuário digitou 1?
                                              |
                                              v  SIM!
                                +-----------------------------+
                                |   animal = new Dog();       |
                                | (Nasce um cachorro na RAM)  |
                                +-----------------------------+
                                              |
                                              v
                                +-----------------------------+
                                |      animal.speak();        |
                                | "The dog goes *woof*"       |
                                +-----------------------------+
```

Note que a chamada é feita através da variável `animal`, mas quem responde é o método real do objeto `Dog`! Isso é o coração do **Polimorfismo Dinâmico**!

---

## 3. Dissecação Código por Código, Linha por Linha

Aqui está o código exato de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) ao final da Imagem 4:

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
13 |         System.out.print("Would you like a dog or a cat? (1 = dog, 2 = cat): ");
14 |         int choice = scanner.nextInt();
15 | 
16 |         if(choice == 1){
17 |             animal = new Dog();
18 |             animal.speak();
19 |         }
20 | 
21 |     }
22 | }
```

### Explicação Passo a Passo:

* **Linha 1 (`import java.util.Scanner;`):**
  * Traz a biblioteca do `Scanner` para permitir que o Java leia o teclado.
* **Linha 2 (` `):** Linha em branco.
* **Linha 3 (`public class Main {`):** Declaração da classe executável `Main`.
* **Linha 4 (`public static void main(String[] args) {`):** Método de inicialização do programa.
* **Linha 5 (` `):** Linha em branco.
* **Linhas 6 e 7:** Comentários da definição de Polimorfismo Dinâmico.
* **Linha 8 (` `):** Linha em branco.
* **Linha 9 (`Scanner scanner = new Scanner(System.in);`):**
  * Instancia o objeto leitor conectado à entrada do teclado (`System.in`).
* **Linha 10 (` `):** Linha em branco.
* **Linha 11 (`Animal animal;`):**
  * **Declaração polimórfica:** Cria a variável de referência `animal` que pertence à superclasse abstrata `Animal`. Ela não tem objeto associado ainda (*null*).
* **Linha 12 (` `):** Linha em branco.
* **Linha 13 (`System.out.print("Would you like a dog or a cat? (1 = dog, 2 = cat): ");`):**
  * Exibe na tela o questionário com as opções de 1 para cachorro e 2 para gato, mantendo o cursor de digitação ao lado da frase.
* **Linha 14 (`int choice = scanner.nextInt();`):**
  * Pausa o programa, lê o número que o usuário digitar no console e armazena na variável inteira `choice`.
* **Linha 15 (` `):** Linha em branco.
* **Linha 16 (`if(choice == 1){`):**
  * Testa uma condição lógica: *"A variável `choice` é exatamente igual a 1?"*. Se for verdade, entra no bloco das chaves.
* **Linha 17 (`animal = new Dog();`):**
  * **Alocação dinâmica:** Cria um objeto real da classe `Dog` na memória *Heap* e coloca a sua referência dentro da variável `animal`.
* **Linha 18 (`animal.speak();`):**
  * Dispara o método `speak()`. A JVM olha para a memória, vê que quem está lá dentro é um `Dog`, e executa o código de latir do cachorro.
* **Linha 19 (`}`):** Fecha o bloco do `if`.
* **Linha 20 (` `):** Linha em branco.
* **Linha 21 (`}`):** Fecha o método `main`.
* **Linha 22 (`}`):** Fecha a classe `Main`.

---

## 4. O Que Sai no Console? (Cenários de Teste)

Ao clicar no botão verde de Run `▷` ao lado da Linha 3 ou Linha 4:

### 🧪 Cenário A: O Usuário Digita `1` (Escolhe o Cachorro)
1. O terminal exibe a pergunta:
   ```text
   Would you like a dog or a cat? (1 = dog, 2 = cat): 
   ```
2. O usuário digita `1` e aperta `Enter`:
   ```text
   Would you like a dog or a cat? (1 = dog, 2 = cat): 1
   The dog goes *woof*

   Process finished with exit code 0
   ```
3. O bloco `if(choice == 1)` foi acionado com sucesso e o cachorro latiu!

---

### 🧪 Cenário B: O Usuário Digita `2` (Escolhe o Gato) ou Qualquer Outro Número
1. O terminal exibe a pergunta:
   ```text
   Would you like a dog or a cat? (1 = dog, 2 = cat): 
   ```
2. O usuário digita `2` e aperta `Enter`:
   ```text
   Would you like a dog or a cat? (1 = dog, 2 = cat): 2

   Process finished with exit code 0
   ```
3. **Por que nada foi impresso depois do 2?**  
   Porque o código da Imagem 4 **ainda só tem o `if(choice == 1)`**! O professor ainda não escreveu o `else if(choice == 2)`. O Java vê que a condição `2 == 1` é falsa, pula o bloco do `if` e simplesmente finaliza o programa!

---

## 5. O Que o Professor Fará no Próximo Passo?

Para completar o ciclo de Polimorfismo Dinâmico, o professor Bro Code vai adicionar a seguir:
1. O bloco do gato:
   ```java
   else if(choice == 2){
       animal = new Cat();
       animal.speak();
   }
   ```
2. Um bloco de segurança `else` para caso o usuário digite um número maluco (como 3, 99 ou -5):
   ```java
   else {
       System.out.println("That choice was invalid");
   }
   ```
3. E uma refatoração elegante para não repetir a chamada `animal.speak();`!
