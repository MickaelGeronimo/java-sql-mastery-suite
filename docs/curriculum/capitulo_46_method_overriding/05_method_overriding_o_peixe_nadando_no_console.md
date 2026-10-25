# Curso de Java — Capítulo 46: Sobrescrita de Método (`method overriding ♻️`)
## Parte 5: O Peixe Finalmente Nada! O Sucesso da Sobrescrita no Console

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:44:45` até `07:45:00` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a consagração visual do Method Overriding no console da IDE IntelliJ IDEA, mostrando como a chamada de três métodos com o mesmo nome exato (`move()`) produziu comportamentos diferentes para espécies diferentes, e dissecar o raciocínio da Máquina Virtual Java ao executar cada linha.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (janela de execução, seleções com o mouse, abas e códigos de saída) foi minuciosamente convertido em texto e tabelas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: O Triunfo da Sobrescrita de Métodos

Nesta captura de tela (`media_1791357222551.png`), o professor Bro Code executa o arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) após termos adicionado a anotação `@Override` e o método personalizado dentro de [`Fish.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java).

O terminal abre na parte inferior e exibe a prova definitiva de que a programação orientada a objetos é mágica:

```text
This animal is running
This animal is running
This animal is swimming
```

### O Destaque do Autor:
O professor passa o cursor do mouse sobre a palavra **`running`** da primeira linha e a deixa selecionada em azul para chamar a atenção da turma:
* As linhas 1 e 2 dizem: `This animal is running` (porque cão e gato não sobrescreveram e usaram o pai).
* A linha 3 diz: `This animal is swimming` (porque o peixe sobrescreveu o método do pai e impôs a sua própria versão especializada!).

---

## 2. Radiografia Visual Minuciosa da Imagem

Para quem está estudando no Google Docs sem a imagem original, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem (`media_1791357222551.png`): O Console Dividido em Duas Ações
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Abas Abertas no Topo:** `Main.java` (em foco com a linha azul), `Animal.java`, `Dog.java`, `Cat.java` e `Fish.java`.
* **Painel Esquerdo (Project Tree):** A pasta `src` lista todas as 5 classes do projeto: `Animal`, `Cat`, `Dog`, `Fish` e `Main`.
* **Editor de Código (Metade Superior):**
  * Mostra as linhas 12 a 17 de `Main.java`:
    ```java
    13         dog.move();
    14         cat.move();
    15         fish.move();
    16 
    17     }
    ```
  * O cursor de texto pisca na linha 15.
* **Painel Inferior ("Run"):**
  * Aba ativa: `Main`.
  * Comando da JVM: `C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...`
  * Saída no terminal:
    ```text
    This animal is running
    This animal is running
    This animal is swimming
    
    Process finished with exit code 0
    ```
  * **A Seleção com o Mouse:** A palavra **`running`** da primeira linha está coberta por um retângulo azul de seleção do mouse (`This animal is [running]`).
* **Controles do YouTube no Rodapé:**
  * Marcação de tempo exata: `7:44:49 / 12:00:00`.
  * Capítulo: `46.method overriding ♻️`.
  * Título do curso abaixo da IDE: "Java Full Course for free ☕".

---

## 3. O Rastreamento da JVM: O Que Aconteceu por Trás dos Panos?

Para um iniciante amador em Java entender como o computador pensou ao executar essas 3 linhas:

```
                  CHAMADA 1: dog.move()
                  ─────────────────────
1. A JVM olha para a classe Dog.
2. Pergunta: "Dog tem o método move() escrito dentro dele?"
3. Resposta: NÃO! Dog está vazio.
4. A JVM sobe para o pai (Animal).
5. Pergunta: "Animal tem o método move()?"
6. Resposta: SIM! System.out.println("This animal is running");
7. Console imprime: "This animal is running"

                  CHAMADA 2: cat.move()
                  ─────────────────────
1. A JVM olha para a classe Cat.
2. Pergunta: "Cat tem o método move() escrito dentro dele?"
3. Resposta: NÃO! Cat está vazio.
4. A JVM sobe para o pai (Animal).
5. Executa a versão do pai: System.out.println("This animal is running");
6. Console imprime: "This animal is running"

                  CHAMADA 3: fish.move()
                  ──────────────────────
1. A JVM olha para a classe Fish.
2. Pergunta: "Fish tem o método move() escrito dentro dele?"
3. Resposta: SIM! Tem o método com @Override!
4. A JVM PARA AÍ MESMO! Ela NÃO sobe para o pai!
5. Executa a versão exclusiva do Fish: System.out.println("This animal is swimming");
6. Console imprime: "This animal is swimming"
```

---

## 4. Análise Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

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

### Explicação Detalhada:

* **Linhas 1 a 6:** Estrutura fundamental da classe `Main` e a definição de Sobrescrita de Métodos.
* **Linha 8 (`Dog dog = new Dog();`):** Instancia o cachorro.
* **Linha 9 (`Cat cat = new Cat();`):** Instancia o gato.
* **Linha 10 (`Fish fish = new Fish();`):** Instancia o peixe.
* **Linha 12 (`dog.move();`):**  
  * O cachorro invoca `move()`. Como não sobrescreveu, executa a versão padrão do pai `Animal`.  
  * **Saída no Console:**
    ```text
    This animal is running
    ```
* **Linha 13 (`cat.move();`):**  
  * O gato invoca `move()`. Como também não sobrescreveu, executa o pai `Animal`.  
  * **Saída no Console:**
    ```text
    This animal is running
    ```
* **Linha 14 (`fish.move();`):**  
  * O peixe invoca `move()`. Como a classe `Fish` sobrescreveu com `@Override`, o Java executa a versão local especializada do peixe!  
  * **Saída no Console:**
    ```text
    This animal is swimming
    ```
* **Linhas 15 e 16 (`}` e `}`):** Encerra o método `main` e a classe `Main`.

---

## 5. Tabela Comparativa de Execução dos Animais

| Objeto | Classe | Herda de quem? | Sobrescreveu `move()`? | De onde veio a ação executada? | Texto Impresso no Console |
| :--- | :--- | :--- | :---: | :--- | :--- |
| **`dog`** | `Dog` | [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) | ❌ Não | Superclasse [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) | `"This animal is running"` |
| **`cat`** | [`Cat`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Cat.java) | [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) | ❌ Não | Superclasse [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) | `"This animal is running"` |
| **`fish`** | [`Fish`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java) | [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java) | ✅ **Sim (`@Override`)** | Subclasse [`Fish`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java) | `"This animal is swimming"` |

---

## 6. Saída Real e Fiel do Console nesta Etapa

Ao executar o [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

```text
This animal is running
This animal is running
This animal is swimming

Process finished with exit code 0
```

* **Linha 1:** `"This animal is running"` (Cachorro correndo via `Animal`).
* **Linha 2:** `"This animal is running"` (Gato correndo via `Animal`).
* **Linha 3:** `"This animal is swimming"` (Peixe nadando via sobrescrita em `Fish`).
* **Linha 4:** `Process finished with exit code 0` (Sucesso absoluto da execução).

---

## 7. Resumo para Fixação no Google Docs

1. **Mesma interface, comportamentos diferentes:** O método chamado tem sempre o mesmo nome (`move()`), mas a ação que acontece no mundo real muda conforme a natureza do objeto (`running` vs `swimming`). Isso é a porta de entrada para o **Polimorfismo**!
2. **Prioridade local:** O método da classe filha sempre tem prioridade sobre o método da classe pai.
3. **Liberdade das subclasses:** Uma subclasse só sobrescreve o que ela realmente precisa. O que fizer sentido manter igual ao pai, ela herda sem esforço (como cão e gato fizeram com a corrida).
