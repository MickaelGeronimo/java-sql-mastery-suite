# Curso de Java — Capítulo 49: Interfaces (`interfaces 📋`)
## Parte 3: O Trio da Natureza Está Completo — A Chegada de `Hawk` (Gavião) e a Visão Geral dos 6 Arquivos

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:03:05` até `08:03:20` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a criação da última peça do nosso ecossistema de Orientação a Objetos: a classe concreta [`Hawk.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Hawk.java) (o gavião/falcão, um predador dos céus), a configuração final da barra de abas do IntelliJ IDEA com os 6 arquivos do capítulo, a volta à aba [`Prey.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Prey.java) para revisar o método `void flee();`, e a preparação imediata para o surgimento da palavra-chave **`implements`**.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (as 6 abas abertas em linha, a árvore completa de classes e interfaces na pasta `src`, os contrastes entre as bolinhas verdes `(I)` e azuis `(C)` e o cursor piscando no método de fuga) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender da imagem original.

---

## 1. Visão Geral da Demonstração: O Tabuleiro Está 100% Pronto!

Na captura de tela anterior (Parte 2), vimos a criação de `Rabbit` (coelho) e `Fish` (peixe). Mas faltava um caçador exclusivo para equilibrar o coelho!

Nesta imagem (`media_1791399165674_49e00e0b.png`), o professor Bro Code fecha o time completo de personagens:
1. **A Chegada do Gavião ([`Hawk.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Hawk.java)):** Ele cria a classe concreta `Hawk` (gavião/falcão). O gavião é o caçador aéreo por excelência, que caça coelhos e pequenos roedores. Ele será o predador puro que assinará a interface `Predator`.
2. **As 6 Abas em Linha:** A barra superior do editor de código agora exibe a coleção completa de 6 arquivos:
   * `Main.java`
   * `Prey.java`
   * `Predator.java`
   * `Rabbit.java`
   * `Fish.java`
   * `Hawk.java`
3. **A Volta para [`Prey.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Prey.java):** O autor volta o foco para a interface `Prey` e seu método `void flee();` para iniciar a conexão prática entre as classes concretas e as interfaces!

---

## 2. Radiografia Visual Minuciosa da Imagem (`media_1791399165674_49e00e0b.png`)

Para quem está estudando pelo Google Docs e não tem a imagem na tela:

* **Aba Ativa no Topo:** `Prey.java` (destacada com a linha azul na base do título).
* **As 6 Abas em Fila Horizontal:**
  1. `(C) Main.java` (bolinha azul com a letra `C`)
  2. `(I) Prey.java` (bolinha verde com a letra `I` — ativa!)
  3. `(I) Predator.java` (bolinha verde com a letra `I`)
  4. `(C) Rabbit.java` (bolinha azul com a letra `C`)
  5. `(C) Fish.java` (bolinha azul com a letra `C`)
  6. `(C) Hawk.java` (bolinha azul com a letra `C` — recém-criada!)
* **Árvore do Projeto (`src` no lado esquerdo):**
  A lista de arquivos aparece organizada em ordem alfabética:
  * `(C) Fish`
  * `(C) Hawk` **← A NOVA CLASSE CRIADA!**
  * `(C) Main`
  * `(I) Predator`
  * `(I) Prey`
  * `(C) Rabbit`
* **Editor de Código de `Prey.java`:**
  ```java
  1: public interface Prey {
  2: 
  3:     void flee();
  4: }
  ```
* O cursor em formato de traço vertical (`I`) pisca exatamente após o ponto e vírgula na linha 3 (`void flee();|`).
* Uma pequena lâmpada amarela de sugestão da IDE repousa no gutter da linha 2.

---

## 3. Dissecando a Trindade dos Animais: O Papel de Cada Um

Com a criação de `Hawk`, o design do nosso software ficou perfeito e didático:

```
                            ┌────────────────────────────────────────┐
                            │               O ECOSSISTEMA            │
                            └───────────────────┬────────────────────┘
                                                │
                     ┌──────────────────────────┴──────────────────────────┐
                     │                                                     │
        ┌────────────┴───────────┐                            ┌────────────┴───────────┐
        │     <<interface>>      │                            │     <<interface>>      │
        │          Prey          │                            │        Predator        │
        ├────────────────────────┤                            ├────────────────────────┤
        │ + void flee();         │                            │ + void hunt();         │
        └────────────┬───────────┘                            └────────────┬───────────┘
                     │                                                     │
         implements  │                                         implements  │
         ┌───────────┴───────────┐                             ┌───────────┴───────────┐
         │                       │                             │                       │
┌────────┴────────┐     ┌────────┴─────────────────────────────┴────────┐     ┌────────┴────────┐
│     Rabbit      │     │                      Fish                     │     │      Hawk       │
├─────────────────┤     ├───────────────────────────────────────────────┤     ├─────────────────┤
│ É apenas presa! │     │ É PRESA E PREDADOR AO MESMO TEMPO!            │     │ É só predador!  │
│ Só sabe fugir   │     │ Sabe fugir de tubarões E caçar peixes menores │     │ Só sabe caçar   │
└─────────────────┘     └───────────────────────────────────────────────┘     └─────────────────┘
```

### 1. `Rabbit` (Coelho):
* **O que faz?** Foge de predadores.
* **Qual contrato assina?** Apenas `Prey`.
* **Qual método terá que programar?** `flee()`.

### 2. `Hawk` (Gavião / Falcão):
* **O que faz?** Caça animais menores do alto do céu.
* **Qual contrato assina?** Apenas `Predator`.
* **Qual método terá que programar?** `hunt()`.

### 3. `Fish` (Peixe):
* **O que faz?** Foge de predadores grandes e caça animais menores.
* **Qual contrato assina?** `Prey` **E** `Predator` simultaneamente!
* **Quais métodos terá que programar?** Ambos: `flee()` e `hunt()`!

---

## 4. Análise Código por Código e Linha por Linha de [`Hawk.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Hawk.java)

```java
1: public class Hawk {
2: 
3: }
```

* **Linha 1 (`public class Hawk {`):** Declara a classe pública `Hawk` (Gavião). Note que é uma `class` concreta (ícone azul `(C)`). *(Nada no console).*
* **Linha 2:** Linha em branco dentro do corpo da classe.
* **Linha 3 (`}`):** Fecha a classe `Hawk`.
* *(No próximo passo do vídeo, o professor adicionará a palavra `implements Predator` nesta linha 1!).*

---

## 5. Análise Código por Código e Linha por Linha de [`Prey.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Prey.java)

```java
1: public interface Prey {
2: 
3:     void flee();
4: }
```

* **Linha 1 (`public interface Prey {`):** Declaração da interface `Prey` (Presa). *(Nada no console).*
* **Linha 2:** Linha em branco.
* **Linha 3 (`void flee();`):** Assinatura obrigatória do método `flee` (fugir), terminada em `;` sem chaves `{}`. *(Nada no console).*
* **Linha 4 (`}`):** Fecha a interface.

---

## 6. O Que Deve Sair no Console Nesta Etapa?

Como nenhum comando de execução foi chamado em `Main.java`:

```text
Process finished with exit code 0
```

* O projeto inteiro com seus 6 arquivos compila com sucesso absoluto (`exit code 0`).

---

## 7. Tabela Completa do Projeto: Os 6 Arquivos e Suas Responsabilidades

| Arquivo | Tipo | Ícone no IntelliJ | Papel Arquitetural | O que deve conter? |
| :--- | :--- | :--- | :--- | :--- |
| [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) | `class` | `(C)` azul | Ponto de partida | Método `main`, comentários conceituais |
| [`Prey.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Prey.java) | `interface` | `(I)` verde | Contrato de Presa | `void flee();` |
| [`Predator.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Predator.java) | `interface` | `(I)` verde | Contrato de Predador | `void hunt();` |
| [`Rabbit.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Rabbit.java) | `class` | `(C)` azul | Animal Presa pura | Implementará `Prey` |
| [`Hawk.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Hawk.java) | `class` | `(C)` azul | Animal Predador puro | Implementará `Predator` |
| [`Fish.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Fish.java) | `class` | `(C)` azul | Animal Híbrido | Implementará `Prey, Predator` |

---

## 8. Resumo da Lição para Fixação no Google Docs

1. **A Família de 6 Arquivos:** Temos 2 contratos (`Prey` e `Predator`), 3 animais (`Rabbit`, `Hawk` e `Fish`) e 1 classe executável (`Main`).
2. **A Palavra que Vem Aí:** No próximo vídeo/imagem, veremos pela primeira vez a palavra **`implements`**, que é a forma oficial com que uma classe Java assina um contrato com uma ou mais interfaces!
