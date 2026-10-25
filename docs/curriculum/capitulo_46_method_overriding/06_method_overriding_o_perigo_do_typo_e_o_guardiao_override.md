# Curso de Java — Capítulo 46: Sobrescrita de Método (`method overriding ♻️`)
## Parte 6: O Grande Experimento do `@Override` — O Erro do `moves()` e o Guardião Contra Typos

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:45:20` até `07:45:45` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) uma das demonstrações didáticas mais geniais do curso: o professor introduz de propósito um erro de digitação no nome do método (`moves()` com **`s`** no final) para mostrar na prática por que a anotação `@Override` existe, como ela funciona como um cinto de segurança e por que o IntelliJ IDEA abre um aviso em vermelho na tela.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (janela de inspeção de erro, documentação oficial da JDK, seleções com o mouse, abas e códigos de saída) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Demonstração: Por que o Professor Errou de Propósito?

Até agora, você aprendeu que sobrescrever um método significa criar uma versão personalizada na classe filha com o **mesmo nome exato** e a **mesma lista de parâmetros** do método da classe pai.

Mas e se você for humano e cometer um errinho bobo de digitação ao escrever o código?
* Na classe [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java), o método se chama **`move()`** (sem 's').
* Se na classe [`Fish`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java), por distração, você digitar **`moves()`** (com 's' no final)?

Nesta sequência de 4 imagens, o professor Bro Code faz um experimento crucial para responder a uma dúvida que quase todo aluno iniciante tem:
> *"Professor, a anotação `@Override` é realmente obrigatória? Se o código funciona sem ela, por que eu deveria me dar ao trabalho de digitá-la?"*

A resposta que o professor dá é visual e contundente: **O `@Override` é o seu anjo da guarda contra bugs silenciosos!**

---

## 2. Radiografia Visual Minuciosa das 4 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na frente, aqui está a reconstrução cinematográfica do que aconteceu na tela do professor:

### Imagem 1 (`media_1791357259362.png` — 07:45:28): O Erro de Digitação Solitário
* **Arquivo em Foco:** [`Fish.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java).
* **O que o autor fez:** Ele apagou a anotação `@Override` e adicionou a letra **`s`** no nome do método:
  ```java
  public class Fish extends Animal{

      void moves(){
          System.out.println("This animal is swimming");
      }
  }
  ```
* **Posição do Cursor:** O cursor de texto pisca logo após a chave de abertura da linha 3 (`void moves(){|`).
* **Sinalizador no Canto Superior Direito:** Há um ícone de exclamação amarela com o número `1`, indicando que o IntelliJ percebeu algo suspeito (um método declarado que nunca é usado em lugar nenhum), mas **não é um erro fatal** de compilação! O Java aceitaria isso tranquilamente.

### Imagem 2 (`media_1791357271676.png`): O Preparo para o Experimento
* O professor dá um `Enter` logo acima de `void moves(){`.
* A linha 2 fica vazia e o cursor pisca ali, pronto para o professor digitar a palavra mágica.

### Imagem 3 (`media_1791357284397.png` — 07:45:38): A Explosão do Erro Vermelho!
* O professor digita `@Override` na linha 3, logo acima de `void moves(){`.
* **O IntelliJ reage imediatamente com alarmes vermelhos:**
  * Na margem esquerda (gutter), aparece um ponto vermelho com lâmpada de erro.
  * O nome `moves` fica sublinhado em vermelho ondulado.
* **O Balão Pop-up de Inspeção da IDE:** O professor coloca o mouse em cima e o IntelliJ abre uma janela escura gigante com o diagnóstico oficial:
  > **`Method does not override method from its superclass`**  
  > *(Tradução: "O método não sobrescreve nenhum método da sua superclasse")*
* **A Documentação Oficial da JDK exibida no balão:**
  Aparece a ficha técnica do Java para a anotação `@Override`:
  * Pacote: `java.lang`
  * Autores: **Peter von der Ahé, Joshua Bloch**
  * Descrição: *"Indicates that a method declaration is intended to override a method declaration in a supertype. If a method is annotated with this annotation type compilers are required to generate an error message unless..."*

### Imagem 4 (`media_1791357308075.png` — 07:45:43): A Correção Triunfal
* O professor apaga a letra **`s`**, transformando `void moves()` de volta em **`void move()`**.
* **Resultado:**
  * Todo o vermelho desaparece instantaneamente!
  * Na linha 4 da margem esquerda (gutter), surge o ícone azul característico do IntelliJ: um círculo com uma seta apontando para cima **`O↑`**.
  * Esse ícone significa: *"Sucesso! Este método `move()` está sobrescrevendo o método `move()` da classe pai `Animal`!"*

---

## 3. O Grande Perigo: O que Acontece com e sem o `@Override`?

Para fixar na sua mente de iniciante amador, veja a diferença brutal entre os dois cenários:

```
┌────────────────────────────────────────────────────────────────────────┐
│                   CENÁRIO 1: SEM A ANOTAÇÃO @Override                  │
│                                                                        │
│  public class Fish extends Animal {                                    │
│      void moves() { // <--- Digitou errado com 's'!                     │
│          System.out.println("This animal is swimming");                │
│      }                                                                 │
│  }                                                                     │
├────────────────────────────────────────────────────────────────────────┤
│ O QUE O JAVA PENSA:                                                    │
│ "Ah, o programador criou um método novo e avulso chamado 'moves()'.     │
│ Isso não tem nada a ver com o método 'move()' do pai Animal. Tudo bem! │
│ O código compila sem nenhum erro!"                                     │
│                                                                        │
│ O QUE ACONTECE NA HORA DE RODAR (Main.java):                           │
│ Linha 14: fish.move();                                                 │
│ -> A JVM procura 'move()' no Fish. Não acha! (Só tem 'moves').         │
│ -> A JVM sobe para o pai Animal e executa o 'move()' genérico!         │
│ -> CONSOLE IMPRIME: "This animal is running"                           │
│                                                                        │
│ RESULTADO: UM BUG SILENCIOSO E TRAUMÁTICO!                             │
│ O seu peixe está correndo em vez de nadar. O compilador não deu        │
│ nenhum erro. Você passa 3 dias procurando onde errou!                  │
└────────────────────────────────────────────────────────────────────────┘

                                   VS

┌────────────────────────────────────────────────────────────────────────┐
│                   CENÁRIO 2: COM A ANOTAÇÃO @Override                  │
│                                                                        │
│  public class Fish extends Animal {                                    │
│      @Override                                                         │
│      void moves() { // <--- Digitou errado com 's'!                     │
│          System.out.println("This animal is swimming");                │
│      }                                                                 │
│  }                                                                     │
├────────────────────────────────────────────────────────────────────────┤
│ O QUE O JAVA PENSA:                                                    │
│ "Opa! O programador colocou @Override! Isso é uma promessa de que      │
│ EXISTE um método exatamente igual na classe pai Animal."               │
│ -> O compilador vai até Animal e procura por 'moves()'.                │
│ -> Não encontra nada com 's'! Só encontra 'move()'.                     │
│ -> O COMPILADOR TRAVA TUDO E GRITA EM VERMELHO:                        │
│    "ERROR: Method does not override method from its superclass!"       │
│                                                                        │
│ RESULTADO: VOCÊ DESCOBRE E CONSERTA O ERRO EM 1 SEGUNDO!               │
│ O compilador protegeu você de você mesmo!                              │
└────────────────────────────────────────────────────────────────────────┘
```

---

## 4. Dissecando a Documentação Oficial que Apareceu no Pop-up

Na Imagem 3, apareceu uma janela interna do Java. Vamos traduzir o que cada linha dela significa para que você nunca mais se sinta perdido ao ver essa tela:

```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Override
```

1. **`@Target({ElementType.METHOD})`**: Significa que você só pode usar `@Override` em cima de **métodos**. Você não pode colocar em cima de uma variável, nem de uma classe, nem de um construtor.
2. **`@Retention(RetentionPolicy.SOURCE)`**: Significa que o `@Override` só serve para a fase de compilação (enquanto você escreve o código-fonte). Ele não ocupa espaço na memória nem deixa o programa mais pesado quando ele estiver rodando.
3. **`public @interface Override`**: É a declaração de que se trata de uma *Anotação* oficial do Java.
4. **`Author: Peter von der Ahé, Joshua Bloch`**:
   * **Joshua Bloch** é uma lenda viva do Java. Ele foi o arquiteto chefe da Sun Microsystems responsável por criar a biblioteca de coleções (`ArrayList`, `HashMap`, etc.) e autor do livro mais famoso da história da linguagem, *"Java Efetivo"* (*Effective Java*).
   * Ele inseriu o `@Override` no Java 1.5 exatamente para salvar os desenvolvedores de cometerem esses erros de digitação!

---

## 5. Análise Linha por Linha de [`Fish.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Fish.java) (Versão do Experimento vs Versão Final)

### Versão do Experimento (Com Erro Intencional):
```java
1: public class Fish extends Animal{
2: 
3:     @Override
4:     void moves(){
5:         System.out.println("This animal is swimming");
6:     }
7: }
```

* **Linha 1:** Declara a classe `Fish` como filha de [`Animal`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/inheritance/Animal.java). *(Válido).*
* **Linha 3 (`@Override`):** Instrução para o compilador verificar se a linha 4 sobrescreve um método do pai.
* **Linha 4 (`void moves(){`):** O método com erro de digitação (`s` a mais).
  * **Comportamento do Compilador:** **ERRO DE COMPILAÇÃO!**
  * Mensagem do compilador Java:
    ```text
    Fish.java:4: error: method does not override or implement a method from a supertype
        @Override
        ^
    1 error
    ```
  * O programa sequer executa!

---

### Versão Corrigida pelo Professor (Imagem 4):
```java
1: public class Fish extends Animal{
2: 
3:     @Override
4:     void move(){
5:         System.out.println("This animal is swimming");
6:     }
7: }
```

* **Linha 1:** `public class Fish extends Animal{` — `Fish` herda de `Animal`.
* **Linha 3:** `@Override` — Validação aprovada pelo compilador! O método `move()` realmente existe na superclasse `Animal`.
* **Linha 4:** `void move(){` — Nome perfeitamente alinhado com o pai. O IntelliJ exibe o ícone azul `O↑` na margem.
* **Linha 5:** `System.out.println("This animal is swimming");` — Corpo especializado que substitui a corrida pela natação.
* **Linha 6 e 7:** Fechamento das chaves do método e da classe.

---

## 6. O que Sai no Console em Cada Caso?

### Caso A: Se tentarmos compilar com `@Override` e `moves()` (Imagem 3):
* **Saída do Console:**
  ```text
  java: method does not override or implement a method from a supertype
  ```
  *(O programa é interrompido antes de nascer).*

### Caso B: Se rodássemos sem `@Override` com `moves()`:
* **Código em `Main.java` chamando `fish.move()`:**
* **Saída do Console:**
  ```text
  This animal is running
  This animal is running
  This animal is running
  ```
  *(O peixe corre vergonhosamente porque o método correto não foi sobrescrito).*

### Caso C: Com a correção de `Fish.java` para `move()` (Imagem 4):
* **Código em `Main.java`:**
  ```java
  dog.move();
  cat.move();
  fish.move();
  ```
* **Saída Fiel e Definitiva no Console:**
  ```text
  This animal is running
  This animal is running
  This animal is swimming
  
  Process finished with exit code 0
  ```

---

## 7. Tabela Comparativa de Boas Práticas

| Prática | O código roda se estiver tudo certo? | E se você cometer um erro de digitação? | Diagnóstico | Recomendação Profissional |
| :--- | :---: | :--- | :--- | :--- |
| **Sem `@Override`** | ✅ Sim | ❌ Cria um método novo indesejado; a classe continua usando o método velho do pai. | Bug Silencioso (Horas de dor de cabeça). | 🚫 **Péssima prática.** |
| **Com `@Override`** | ✅ Sim | 🛑 O compilador trava imediatamente e aponta a linha exata do erro. | Erro Imediato na Compilação (Fácil de corrigir). | 🏆 **Obrigatório e Padrão da Indústria.** |

---

## 8. Resumo para Fixação no Google Docs

1. **A anotação `@Override` não altera o que o código faz:** Ela não muda a velocidade nem o comportamento de execução do programa.
2. **Ela serve como um contrato de segurança:** Ela diz para o compilador: *"Cheque para mim se este método realmente existe na superclasse"*.
3. **Se você errar uma letrinha sequer (como o `s` em `moves`):** O compilador avisa no mesmo instante com uma linha vermelha, impedindo você de lançar um programa com defeito!
4. **Sempre use `@Override`:** Sempre que você for sobrescrever um método vindo de uma superclasse, escreva `@Override` na linha de cima. É a marca de um programador Java responsável.
