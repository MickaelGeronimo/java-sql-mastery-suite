# Curso de Java (Bro Code) — Capítulo 41: Overloaded Constructors 🛠️
## Parte 8: O Quarto Construtor Sem Argumentos (`User()`), Modo Convidado (`"Guest"`) e o Objeto `user4`

---

> [!NOTE]
> **Contexto de Estudo para o Google Docs:**
> Este documento foi elaborado com riqueza visual descritiva e didática descomplicada para que você possa copiar e colar diretamente no seu Google Docs. Mesmo sem as imagens originais na tela, você entenderá exatamente cada detalhe visual do IntelliJ IDEA, cada linha de código, o motivo de cada comando e como o Java pensa nos bastidores.

---

## 1. O Que Aconteceu Nesta Sequência de 5 Imagens? (Visão Geral)

Nas etapas anteriores, criamos 3 construtores que exigiam pelo menos algum dado:
* **Construtor 1:** Exigia o nome (`String username`).
* **Construtor 2:** Exigia nome e e-mail (`String username, String email`).
* **Construtor 3:** Exigia todos os 3 dados (`String username, String email, int age`).

Mas e se um visitante entrar no nosso aplicativo sem fazer login nem cadastro nenhum? Queremos criar um usuário "Convidado" / "Anônimo", sem precisar passar absolutamente **nada** nos parênteses!

Nesta sequência de 5 capturas de tela do vídeo:
1. **Imagem 1 (`media_1791324474201.png`):** O autor volta para a aba `User.java` e posiciona o cursor na linha 7, logo acima do construtor de 1 parâmetro.
2. **Imagem 2 (`media_1791324763806.png` - Timestamp `7:07:11`):** O autor cria o **quarto construtor**: `User()`, com os parênteses completamente **vazios**! Ele define:
   * `this.username = "Guest";` (Convidado/Visitante!)
   * `this.email = "Not provided";`
   * `this.age = 0;`
   Em seguida, clica com o mouse na aba [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
3. **Imagem 3 (`media_1791324837557.png`):** No [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), na linha 8, o autor começa a digitar `Use` para criar a variável `user4`.
4. **Imagem 4 (`media_1791324848661.png`):** O autor completa a linha: `User user4 = new User();` (parênteses vazios!).
5. **Imagem 5 (`media_1791324868698.png`):** Para o código não ficar gigante e poluído na tela, o autor apaga os comandos `System.out.println` antigos de `user1`, `user2` e `user3`, deixando apenas a impressão dos dados do novo `user4`. Ele está editando as linhas de impressão com o cursor de texto selecionando a palavra `username`.

---

## 2. Descrição Visual Detalhada das Telas (Para Seu Google Docs)

### 📸 Imagem 1: O Ponto de Inserção em `User.java`
* **Aba ativa:** `User.java`.
* **Editor:** Vemos os atributos no topo e os 3 construtores criados anteriormente.
* **Cursor:** O mouse aponta para a linha 7, abrindo espaço para um construtor ainda mais básico.

---

### 📸 Imagem 2: O Novo Construtor Sem Argumentos (`User()`)
* **Tempo do vídeo:** `7:07:11 / 12:00:00`.
* **Aba ativa:** `User.java`.
* **Linhas 7 a 11 (O Destaque):**
  ```java
  User(){
      this.username = "Guest";
      this.email = "Not provided";
      this.age = 0;
  }
  ```
* **Ação do mouse:** O ponteiro do mouse clica na aba superior [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) para voltar ao programa principal e testar a novidade.

---

### 📸 Imagem 3: Digitando o Quarto Usuário
* **Aba ativa:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **Linhas 5 a 7:** `user1` (Spongebob), `user2` (Patrick), `user3` (Sandy).
* **Linha 8:** O autor começou a digitar: `Use|` (com o cursor piscando na letra 'e').

---

### 📸 Imagem 4: Instanciando `user4 = new User()`
* **Linha 8 completa:** 
  ```java
  User user4 = new User();
  ```
* **Destaque visual:** Note os parênteses `()` vazios depois de `new User`. É a primeira vez na aula que não passamos nenhuma aspa nem número! O Java sabe instantaneamente que deve chamar o `User()` sem argumentos.

---

### 📸 Imagem 5: Limpando a Casa e Imprimindo o `user4`
* O autor apagou as 9 linhas de impressão anteriores para manter o código limpo na gravação.
* **Linhas visíveis:**
  ```java
  System.out.println(user4.username);
  System.out.println(user4.username); // cursor aqui, prestes a mudar para .email
  System.out.println(user4.username); // prestes a mudar para .age
  ```
* Uma caixinha cinza de seleção do IntelliJ cobre a palavra `username` na linha 11, mostrando que o autor duplicou a linha e está renomeando os atributos.

---

## 3. O Que é um Construtor Sem Parâmetros? (Explicado para um Lezado Amador)

Pense na compra de um videogame online ou na entrada de um shopping:
* Se você fizer login com sua conta, o sistema sabe seu nome e e-mail.
* Mas se você entrar como **anônimo / visitante**, você não preenche nada! O sistema simplesmente te chama de:
  * Nome: `"Guest"` (Convidado)
  * E-mail: `"Not provided"` (Não informado)
  * Idade: `0`

Isso em Java é feito com um construtor de **parênteses vazios `()`**.

### 🌟 O Cardápio Definitivo da Classe `User`:
Agora a nossa classe tem **4 construtores sobrecarregados**! Veja como o Java escolhe cada um:

| Como você cria o objeto no `Main`? | Argumentos passados | Qual construtor é chamado? | O que vai para o objeto? |
| :--- | :---: | :--- | :--- |
| `new User();` | **Nenhum** | `User()` | `username="Guest"`, `email="Not provided"`, `age=0` |
| `new User("Spongebob");` | **1 texto** | `User(String)` | `username="Spongebob"`, `email="Not provided"`, `age=0` |
| `new User("Patrick", "p@aol.com");` | **2 textos** | `User(String, String)` | `username="Patrick"`, `email="p@aol.com"`, `age=0` |
| `new User("Sandy", "s@gmail.com", 27);` | **2 textos + 1 int** | `User(String, String, int)`| `username="Sandy"`, `email="s@gmail.com"`, `age=27` |

> **Regra de Ouro:** O Java nunca se perde! Ele conta quantos valores você colocou nos parênteses e checa os tipos. Se bater exatamente com um dos construtores, ele executa aquele!

---

## 4. Código Completo de `User.java` Linha por Linha

Aqui está a classe `User.java` com todos os 4 construtores:

```java
 1: public class User {
 2: 
 3:     String username;
 4:     String email;
 5:     int age;
 6: 
 7:     User(){
 8:         this.username = "Guest";
 9:         this.email = "Not provided";
10:         this.age = 0;
11:     }
12: 
13:     User(String username){
14:         this.username = username;
15:         this.email = "Not provided";
16:         this.age = 0;
17:     }
18: 
19:     User(String username, String email){
20:         this.username = username;
21:         this.email = email;
22:         this.age = 0;
23:     }
24: 
25:     User(String username, String email, int age){
26:         this.username = username;
27:         this.email = email;
28:         this.age = age;
29:     }
30: 
31: }
```

### Explicação Linha por Linha:

* **Linha 1 (`public class User {`):** Início da classe `User`. *(Console: nada)*.
* **Linhas 3 a 5:** Declaração dos 3 atributos de instância: `String username;`, `String email;`, `int age;`. *(Console: nada)*.
* **Linha 7 (`User(){`):** 
  * **O Quarto Construtor (Sem Parâmetros)!**
  * Chamado quando alguém faz `new User()`.
* **Linha 8 (`this.username = "Guest";`):** Como ninguém informou um nome, batizamos o usuário de `"Guest"` (Convidado).
* **Linha 9 (`this.email = "Not provided";`):** E-mail padrão de segurança.
* **Linha 10 (`this.age = 0;`):** Idade padrão de segurança.
* **Linha 11 (`}`):** Fim do construtor sem parâmetros.
* **Linhas 13 a 17:** Construtor 1 (1 parâmetro `username`).
* **Linhas 19 a 23:** Construtor 2 (2 parâmetros `username, email`).
* **Linhas 25 a 29:** Construtor 3 (3 parâmetros `username, email, age`).
* **Linha 31 (`}`):** Fim da classe `User`.

---

## 5. Código Completo de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) Linha por Linha

Aqui está o código do arquivo principal como ficou configurado:

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         User user1 = new User("Spongebob");
 5:         User user2 = new User("Patrick", "PStar@aol.com");
 6:         User user3 = new User("Sandy", "SCheeks@gmail.com", 27);
 7:         User user4 = new User();
 8: 
 9:         System.out.println(user4.username);
10:         System.out.println(user4.email);
11:         System.out.println(user4.age);
12:     }
13: }
```

### Explicação Linha por Linha com Saída no Console:

* **Linha 1 e 2:** Declaração da classe e método `main`. *(Console: nada)*.
* **Linha 4 (`User user1 = new User("Spongebob");`):** Cria o Bob Esponja usando o construtor de 1 texto. *(Console: nada)*.
* **Linha 5 (`User user2 = new User("Patrick", "PStar@aol.com");`):** Cria o Patrick usando o construtor de 2 textos. *(Console: nada)*.
* **Linha 6 (`User user3 = new User("Sandy", "SCheeks@gmail.com", 27);`):** Cria a Sandy usando o construtor completo de 3 argumentos. *(Console: nada)*.
* **Linha 7 (`User user4 = new User();`):**
  * **A Novidade!**
  * Cria o quarto objeto usando o construtor **sem argumentos** `User()`.
  * Na memória Heap, este objeto nasce com:
    * `username` = `"Guest"`
    * `email` = `"Not provided"`
    * `age` = `0`
  * *(Console: nada — operação de memória)*.
* **Linha 8 (` `):** Linha em branco.
* **Linha 9 (`System.out.println(user4.username);`):**
  * Pede para imprimir o atributo `username` do `user4`.
  * **Sai no console:**
    ```text
    Guest
    ```
* **Linha 10 (`System.out.println(user4.email);`):**
  * Pede para imprimir o atributo `email` do `user4`.
  * **Sai no console:**
    ```text
    Not provided
    ```
* **Linha 11 (`System.out.println(user4.age);`):**
  * Pede para imprimir o atributo `age` do `user4`.
  * **Sai no console:**
    ```text
    0
    ```
* **Linhas 12 e 13 (`}`):** Fecham o método `main` e a classe `Main`.

---

## 6. O Que Sai no Console?

Ao rodar esse código atualizado no terminal, a saída é exatamente:

```text
Guest
Not provided
0
```

### E se mandássemos imprimir os 4 usuários juntos?
Se mantivéssemos os comandos de impressão de todos os 4 usuários na tela, o terminal exibiria 12 linhas perfeitas:

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
Guest
Not provided
0
```

---

## 7. Diagrama Completo dos 4 Objetos na Memória (Heap)

```text
  STACK (Variáveis de Referência)             HEAP (Objetos Criados)
┌────────────────────────────────┐       ┌───────────────────────────────────────┐
│ user1 ─────────────────────────┼──────>│ Objeto 1: "Spongebob"                 │
│                                │       │  email: "Not provided", age: 0        │
│                                │       └───────────────────────────────────────┘
│ user2 ─────────────────────────┼──────>┌───────────────────────────────────────┐
│                                │       │ Objeto 2: "Patrick"                   │
│                                │       │  email: "PStar@aol.com", age: 0       │
│                                │       └───────────────────────────────────────┘
│ user3 ─────────────────────────┼──────>┌───────────────────────────────────────┐
│                                │       │ Objeto 3: "Sandy"                     │
│                                │       │  email: "SCheeks@gmail.com", age: 27  │
│                                │       └───────────────────────────────────────┘
│ user4 ─────────────────────────┼──────>┌───────────────────────────────────────┐
│                                │       │ Objeto 4: "Guest" (Convidado)         │
│                                │       │  email: "Not provided", age: 0        │
└────────────────────────────────┘       └───────────────────────────────────────┘
```

---

## 8. Resumo da Obra (Para Guardar para Sempre):

1. **Construtores sobrecarregados** têm o mesmo nome da classe, mas listas de parâmetros diferentes (`()`, `(String)`, `(String, String)`, `(String, String, int)`).
2. Um construtor com parênteses vazios `()` é perfeito para definir **valores padrão completos** (como um usuário visitante `"Guest"`).
3. Isso dá ao seu programa Java a máxima flexibilidade: o usuário pode fornecer tudo, parte dos dados ou dado nenhum, e seu sistema nunca quebra!
