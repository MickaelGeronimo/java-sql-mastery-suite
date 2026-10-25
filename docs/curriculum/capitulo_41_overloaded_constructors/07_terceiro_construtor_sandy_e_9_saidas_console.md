# Curso de Java (Bro Code) — Capítulo 41: Overloaded Constructors 🛠️
## Parte 7: O Terceiro Construtor Completo, Instanciação de `user3` (Sandy) e Análise das 9 Saídas no Console

---

> [!NOTE]
> **Contexto de Estudo para o Google Docs:**
> Este documento foi elaborado com riqueza visual descritiva e didática descomplicada para que você possa copiar e colar diretamente no seu Google Docs. Mesmo sem as imagens originais na tela, você entenderá exatamente cada detalhe visual do IntelliJ IDEA, cada linha de código, o motivo de cada comando e como o Java pensa nos bastidores.

---

## 1. O Que Aconteceu Nesta Sequência de 3 Imagens? (Visão Geral)

Nas etapas anteriores, vimos como criar usuários com **1 dado** (`user1` - só nome) e com **2 dados** (`user2` - nome e e-mail). Mas e quando o usuário fornece **todos os dados** (nome, e-mail e idade)?

Nesta nova sequência de 3 imagens do vídeo:
1. **Imagem 1 (`media_1791324330070.png`):** O autor revisa a aba [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) onde `user1` e `user2` já estão criados e impressos. O cursor está piscando no final da linha 6.
2. **Imagem 2 (`media_1791324363252.png` - Timestamp `7:05:59`):** O autor volta para a aba `User.java` e digita o **terceiro construtor sobrecarregado**, que aceita 3 parâmetros: `User(String username, String email, int age)`.
3. **Imagem 3 (`media_1791324457645.png`):** O autor volta ao [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), cria o objeto `user3` passando `"Sandy"`, `"SCheeks@gmail.com"` e `27`, manda imprimir na tela e clica em Play! O console exibe **9 linhas verdes**, e o autor seleciona as últimas 3 linhas com o mouse (faixa azul) para comemorar o sucesso da Sandy!

---

## 2. Descrição Visual Detalhada das Telas (Para Seu Google Docs)

### 📸 Imagem 1: A Preparação no `Main.java`
* **Aba ativa:** `Main.java` (marcada em azul claro no topo).
* **Editor de código:**
  * Linhas 5 e 6 com `user1` e `user2`.
  * Linhas 8 a 10 com as impressões do Bob Esponja.
  * Linhas 12 a 14 com as impressões do Patrick.
* **Cursor:** Piscando logo após o ponto e vírgula de `User user2 = ...;`. O autor percebe que precisa de mais flexibilidade: quer cadastrar um usuário completo com idade!

---

### 📸 Imagem 2: A Criação do Terceiro Construtor em `User.java`
* **Tempo do vídeo:** `7:05:59 / 12:00:00` no capítulo `41.overloaded constructors ...`.
* **Aba ativa:** `User.java`.
* **Editor de código:**
  * Linhas 7 a 11: Construtor 1 (1 parâmetro: `username`).
  * Linhas 12 a 16: Construtor 2 (2 parâmetros: `username, email`).
  * Linha 17 em destaque: O autor digita:
    ```java
    User(String username, String email, int age){
    ```
  * O cursor de texto pisca exatamente após o parêntese de fechamento `int age)`.
  * Linhas 18 a 20: o corpo que receberá os 3 dados e passará para os atributos `this.username`, `this.email` e `this.age = age;`.

---

### 📸 Imagem 3: O Terminal com as 9 Linhas e a Seleção em Azul
* **Aba ativa no topo:** `Main.java`.
* **Editor visível (linhas 17 a 21):**
  ```java
  System.out.println(user3.username);
  System.out.println(user3.email);
  System.out.println(user3.age);
  ```
* **Painel inferior do console (Run):**
  Exibe as 9 linhas verdes brilhantes. O autor clica e arrasta o mouse sobre as 3 últimas linhas, criando uma **barra de seleção azul cobalto**:
  * `Sandy`
  * `SCheeks@gmail.com`
  * `27`
* **O significado pedagógico:** O autor selecionou essa parte para mostrar: *"Vejam! Desta vez NENHUM valor padrão foi usado. A Sandy teve todos os seus dados reais preenchidos pelo 3º construtor!"*

---

## 3. O "Cardápio de Construtores" Atualizado de `User.java`

Agora a nossa classe `User.java` é um molde superpoderoso com 3 opções de nascimento:

| Construtor | Parâmetros Recebidos | O que faz com o que falta? | Quem usou? |
| :--- | :--- | :--- | :--- |
| **Opção 1 (Básica)** | `(String username)` | `email = "Not provided"`, `age = 0` | `user1` (Spongebob) |
| **Opção 2 (Média)** | `(String username, String email)` | `age = 0` | `user2` (Patrick) |
| **Opção 3 (Completa)**| `(String username, String email, int age)`| Não falta nada! Guarda os 3 valores reais! | `user3` (Sandy) |

---

## 4. Código Completo de `User.java` Linha por Linha

Aqui está o arquivo `User.java` completo:

```java
 1: public class User {
 2: 
 3:     String username;
 4:     String email;
 5:     int age;
 6: 
 7:     User(String username){
 8:         this.username = username;
 9:         this.email = "Not provided";
10:         this.age = 0;
11:     }
12: 
13:     User(String username, String email){
14:         this.username = username;
15:         this.email = email;
16:         this.age = 0;
17:     }
18: 
19:     User(String username, String email, int age){
20:         this.username = username;
21:         this.email = email;
22:         this.age = age;
23:     }
24: 
25: }
```

### Explicação Linha por Linha dos Construtores:

* **Linhas 1 a 5:** Declaração da classe e dos 3 atributos (`username`, `email`, `age`).
* **Linhas 7 a 11 (Construtor 1):** Construtor de 1 parâmetro. Só conhece o nome, coloca valores padrão no resto.
* **Linhas 13 a 17 (Construtor 2):** Construtor de 2 parâmetros. Conhece o nome e o e-mail, coloca `0` na idade.
* **Linha 19 (`User(String username, String email, int age){`):**
  * **O Terceiro Construtor Sobrecarregado!**
  * Recebe **3 parâmetros**: 2 textos (`String username`, `String email`) e 1 número inteiro (`int age`).
* **Linha 20 (`this.username = username;`):**
  * Pega o nome recebido nos parênteses e salva no atributo `this.username` do objeto que está nascendo.
* **Linha 21 (`this.email = email;`):**
  * Pega o e-mail real recebido e salva no atributo `this.email`.
* **Linha 22 (`this.age = age;`):**
  * **A grande diferença deste construtor!** Aqui nós **não** colocamos `0`.
  * Pegamos a idade real passada pela pessoa (`age`) e guardamos no atributo `this.age`.
* **Linha 23 (`}`):** Fecha o terceiro construtor.
* **Linha 25 (`}`):** Fecha a classe `User`.

---

## 5. Código Completo de `Main.java` Linha por Linha

Aqui está o arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) completo:

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         User user1 = new User("Spongebob");
 5:         User user2 = new User("Patrick", "PStar@aol.com");
 6:         User user3 = new User("Sandy", "SCheeks@gmail.com", 27);
 7: 
 8:         System.out.println(user1.username);
 9:         System.out.println(user1.email);
10:         System.out.println(user1.age);
11: 
12:         System.out.println(user2.username);
13:         System.out.println(user2.email);
14:         System.out.println(user2.age);
15: 
16:         System.out.println(user3.username);
17:         System.out.println(user3.email);
18:         System.out.println(user3.age);
19:     }
20: }
```

### Explicação Detalhada com Saída no Console para Cada Linha:

* **Linhas 1 a 3:** Declaração da classe e método `main`. *(Console: nada)*.
* **Linha 4 (`User user1 = new User("Spongebob");`):** Aciona o **Construtor 1** (1 argumento). *(Console: nada)*.
* **Linha 5 (`User user2 = new User("Patrick", "PStar@aol.com");`):** Aciona o **Construtor 2** (2 argumentos). *(Console: nada)*.
* **Linha 6 (`User user3 = new User("Sandy", "SCheeks@gmail.com", 27);`):**
  * Aciona o **Construtor 3** (3 argumentos: 2 Strings e 1 int).
  * Cria o objeto da Sandy na memória Heap com todos os atributos preenchidos.
  * *(Console: nada)*.
* **Linha 7 (` `):** Linha em branco.

#### 🖨️ Saídas do Bloco 1 (`user1`):
* **Linha 8 (`System.out.println(user1.username);`):**
  * **Sai no console:** `Spongebob`
* **Linha 9 (`System.out.println(user1.email);`):**
  * **Sai no console:** `Not provided`
* **Linha 10 (`System.out.println(user1.age);`):**
  * **Sai no console:** `0`
* **Linha 11 (` `):** Linha em branco.

#### 🖨️ Saídas do Bloco 2 (`user2`):
* **Linha 12 (`System.out.println(user2.username);`):**
  * **Sai no console:** `Patrick`
* **Linha 13 (`System.out.println(user2.email);`):**
  * **Sai no console:** `PStar@aol.com`
* **Linha 14 (`System.out.println(user2.age);`):**
  * **Sai no console:** `0`
* **Linha 15 (` `):** Linha em branco.

#### 🖨️ Saídas do Bloco 3 (`user3` - Sandy Bochechas):
* **Linha 16 (`System.out.println(user3.username);`):**
  * **Sai no console:** `Sandy`
* **Linha 17 (`System.out.println(user3.email);`):**
  * **Sai no console:** `SCheeks@gmail.com`
* **Linha 18 (`System.out.println(user3.age);`):**
  * **Sai no console:** `27`
* **Linhas 19 e 20 (`}`):** Fecham o método `main` e a classe `Main`.

---

## 6. O Mapa da Memória Heap (Os 3 Objetos)

```text
  STACK (Ponteiros/Variáveis)               HEAP (Objetos no Mundo Real do Java)
┌────────────────────────────┐         ┌────────────────────────────────────────┐
│ user1 ─────────────────────┼────────>│ Objeto User 1                          │
│                            │         │   username = "Spongebob"               │
│                            │         │   email    = "Not provided" (padrão)   │
│                            │         │   age      = 0 (padrão)                │
│                            │         └────────────────────────────────────────┘
│ user2 ─────────────────────┼────────>┌────────────────────────────────────────┐
│                            │         │ Objeto User 2                          │
│                            │         │   username = "Patrick"                 │
│                            │         │   email    = "PStar@aol.com" (real)    │
│                            │         │   age      = 0 (padrão)                │
│                            │         └────────────────────────────────────────┘
│ user3 ─────────────────────┼────────>┌────────────────────────────────────────┐
│                            │         │ Objeto User 3                          │
│                            │         │   username = "Sandy"                   │
│                            │         │   email    = "SCheeks@gmail.com" (real)│
│                            │         │   age      = 27 (real!)                │
└────────────────────────────┘         └────────────────────────────────────────┘
```

---

## 7. Saída Completa e Exata do Console (As 9 Linhas)

```text
Spongebob
Not provided
0
Patrick
PStar@aol.com
0
Sandy
SCheeks@gmail.com
27
```

### Por que o Bro Code destacou em azul as últimas 3 linhas?
No vídeo, ao passar o mouse e selecionar `Sandy`, `SCheeks@gmail.com` e `27`, o autor está provando visualmente:
> *"Vejam a diferença! Nos dois primeiros objetos, tivemos valores padrão (`Not provided` e `0`). Mas para a Sandy, que passou os 3 argumentos, o Java ativou o 3º construtor e ela ficou com 100% dos dados reais!"*
