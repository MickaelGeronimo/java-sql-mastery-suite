# Curso de Java — Capítulo 49: Interfaces (`interfaces 📋`)
## Parte 9: O Confronto no Terminal — A Execução de `Rabbit` e `Hawk`, o Destaque em Azul e a Segurança de Tipos

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:05:00` até `08:05:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a primeira execução combinada de dois objetos implementando interfaces diferentes no arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java): a criação da instância do gavião (`Hawk hawk = new Hawk();`), o disparo das ações opostas da natureza (`rabbit.flee();` e `hawk.hunt();`), a saída dupla impressa no console, o gesto do professor selecionando em azul com o mouse a primeira linha do terminal para contrastar a presa com o predador, e a grande regra de **Segurança de Tipos** do Java (por que chamar `rabbit.hunt()` ou `hawk.flee()` causaria um erro fatal de compilação).  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (linhas digitadas em `Main.java`, abertura do terminal inferior, texto com fundo de seleção azul, linhas em verde neon e botões de execução) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Demonstração: O Ecossistema em Movimento!

Nas partes anteriores, preparamos os dois primeiros atores do nosso ecossistema:
* O coelho ([`Rabbit.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Rabbit.java)) implementou a interface [`Prey.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Prey.java) e sabe fugir (`flee()`).
* O gavião ([`Hawk.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Hawk.java)) implementou a interface [`Predator.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Predator.java) e sabe caçar (`hunt()`).

Nesta sequência de 2 capturas de tela, o professor Bro Code coloca os dois animais para atuar lado a lado:

1. **A Adição do Gavião em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Imagem 1):**  
   Ele adiciona `Hawk hawk = new Hawk();` e chama `hawk.hunt();` logo abaixo da fuga do coelho.
2. **A Execução Dupla no Terminal (Imagem 2):**  
   O console roda e imprime duas frases sucessivas:
   ```text
   *The rabbit is running away*
   *The hawk is hunting*
   ```
3. **O Destaque com o Mouse do Professor:**  
   O autor arrasta o mouse sobre a primeira frase no terminal (**`*The rabbit is running away*`**), deixando-a selecionada com uma barra retangular azul para enfatizar o contraste nítido entre o comportamento da presa e o comportamento do predador!

---

## 2. Radiografia Visual Minuciosa das 2 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na tela:

### Imagem 1 (`media_1791399371637_fca456fb.png`): O Código Atualizado em `Main.java`
* **Aba Ativa:** `Main.java`.
* **Editor de Código (Linhas 9 a 14):**
  ```java
  9           Rabbit rabbit = new Rabbit();
  10          Hawk hawk = new Hawk();
  11  
  12          rabbit.flee();
  13          hawk.hunt();
  14  
  15      }
  16  }
  ```
* O cursor pisca no final da linha 13 (`hawk.hunt();|`).
* As setinhas verdes `▶` aparecem nas linhas 2 e 3 do gutter, prontas para rodar.

### Imagem 2 (`media_1791399381328_80ff764c.png`): O Terminal e a Seleção em Azul
* **Metade Superior:** Mostra as linhas 11 a 16 de `Main.java`.
* **Metade Inferior ("Run / Console"):**
  * **Linha 1 do Terminal (Selecionada em Azul):**  
    O texto `*The rabbit is running away*` está totalmente coberto por uma barra retangular azul brilhante de seleção do mouse do professor.
  * **Linha 2 do Terminal:**  
    O texto `*The hawk is hunting*` aparece logo abaixo em verde neon.
  * **Linha de Fechamento:**  
    `Process finished with exit code 0`.

---

## 3. A Grande Lição de Segurança de Tipos: O Que o Java Proíbe?

Preste muita atenção neste detalhe que muitos iniciantes deixam passar:

```
┌─────────────────────────────────┬─────────────────────────────────┐
│        Objeto: rabbit           │          Objeto: hawk           │
├─────────────────────────────────┼─────────────────────────────────┤
│ Assinou o contrato: Prey        │ Assinou o contrato: Predator    │
│ Sabe fazer: flee()              │ Sabe fazer: hunt()              │
│                                 │                                 │
│ ✅ rabbit.flee(); (Permitido!)  │ ✅ hawk.hunt(); (Permitido!)    │
│ ❌ rabbit.hunt(); (ERRO FATAL!) │ ❌ hawk.flee(); (ERRO FATAL!)   │
└─────────────────────────────────┴─────────────────────────────────┘
```

### O Que Aconteceria se Tentássemos Inverter?
* Se você tentar digitar:
  ```java
  rabbit.hunt(); // ❌ ERRO DE COMPILAÇÃO!
  ```
  O compilador do Java diz:
  > *`error: cannot find symbol; symbol: method hunt(); location: class Rabbit`*  
  > *(Coelho não tem o método hunt! Coelho não assinou o contrato Predator!)*
* Da mesma forma, se você tentar digitar:
  ```java
  hawk.flee(); // ❌ ERRO DE COMPILAÇÃO!
  ```
  O compilador rejeita na hora, porque um gavião não implementou `Prey`!

> **A Moral da História:**  
> As interfaces funcionam como **certificados de habilidade**. Um objeto só pode executar aquilo que sua classe ou suas interfaces declararam formalmente. Isso impede que o programador cometa gafes como fazer uma presa caçar ou um caçador de topo fugir!

---

## 4. Análise Código por Código e Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

Abaixo está o código completo do arquivo principal exatamente como visto na Imagem 1:

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:  
5:          // Interface = A blueprint for a class that specifies a set of abstract methods
6:          //               that implementing classes MUST define.
7:          //               Supports multiple inheritance-like behavior.
8:  
9:          Rabbit rabbit = new Rabbit();
10:         Hawk hawk = new Hawk();
11: 
12:         rabbit.flee();
13:         hawk.hunt();
14: 
15:     }
16: }
```

### Explicação Passo a Passo:

* **Linhas 1 a 3:** Estrutura inicial do programa Java com a classe `Main` e o método `main`. *(Nada no console).*
* **Linhas 5 a 7 (Comentários):** Bloco de notas com a definição oficial das Interfaces. *(Ignorado pelo compilador).*
* **Linha 8:** Linha em branco.
* **Linha 9 (`Rabbit rabbit = new Rabbit();`):** Instancia o objeto coelho na memória Heap. *(Nada no console).*
* **Linha 10 (`Hawk hawk = new Hawk();`):** Instancia o objeto gavião na memória Heap. *(Nada no console).*
* **Linha 11:** Linha em branco para separação visual.
* **Linha 12 (`rabbit.flee();`):**
  * Invoca o método `flee()` programado dentro de [`Rabbit.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Rabbit.java).
  * **Saída no Console:**
    ```text
    *The rabbit is running away*
    ```
* **Linha 13 (`hawk.hunt();`):**
  * Invoca o método `hunt()` programado dentro de [`Hawk.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/ecology/Hawk.java).
  * **Saída no Console:**
    ```text
    *The hawk is hunting*
    ```
* **Linha 14:** Linha em branco.
* **Linhas 15 e 16 (`}` e `}`):** Fecham o método `main` e a classe `Main`.

---

## 5. Saída Fiel e Real no Console Nesta Etapa

Ao clicar no botão verde de execução `▶`:

```text
*The rabbit is running away*
*The hawk is hunting*

Process finished with exit code 0
```

* **Linha 1:** `*The rabbit is running away*` (Ação disparada pela presa `rabbit`).
* **Linha 2:** `*The hawk is hunting*` (Ação disparada pelo predador `hawk`).
* **Linha 3:** `Process finished with exit code 0` (Sucesso absoluto da compilação e execução).

---

## 6. O Quadro Comparativo dos Animais Criados

| Objeto | Classe | Interface Assinada | Método Válido | Método Inválido (Erro) | Saída no Console |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `rabbit` | `Rabbit` | `Prey` | `rabbit.flee()` | `rabbit.hunt()` | `*The rabbit is running away*` |
| `hawk` | `Hawk` | `Predator` | `hawk.hunt()` | `hawk.flee()` | `*The hawk is hunting*` |

---

## 7. O Que Vem a Seguir? O Grande Clímax com `Fish`!

Repare na pergunta inevitável que surge agora na cabeça de qualquer programador iniciante:
> *"Ok, o coelho só foge e o gavião só caça. Mas e se eu tiver um animal que precisa fazer as DUAS coisas, como um Peixe (`Fish`)?"*

No próximo passo, o professor Bro Code mostrará a sintaxe mais esperada deste capítulo:
```java
public class Fish implements Prey, Predator {
    // Implementará flee() E hunt() no mesmo arquivo!
}
```
Isso provará na prática como o Java supera a limitação de herança única através de interfaces múltiplas!
