# Curso de Java — Bro Code
## Capítulo 41: Construtores Sobrecarregados (*Overloaded Constructors 🛠️*) — Parte 3: Atributos de `User.java` e o Primeiro Construtor Simplificado

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e descritiva**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as nuances visuais das 2 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamps `7:02:25` até `7:03:07` de 12:00:00) foram descritas minuciosamente em texto corrido e diagramas para que você compreenda cada linha de código sem precisar pausar ou rever o vídeo.

---

## 1. O Cenário Visual das 2 Imagens (O Que o Professor Bro Code Faz na Tela)

Nesta sequência de 2 telas, o professor entra no arquivo recém-criado [`User.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/User.java), define os 3 campos de dados do usuário e constrói o primeiro dos múltiplos construtores que teremos neste capítulo:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (User.java - 7:02:25): O Arquivo em Branco Pronto Para Edição                               |
| - Aba ativa no topo: [User.java] (marcada em azul). A aba [Main.java] está ao lado.                |
| - O arquivo foi aberto com o esqueleto básico gerado pelo IntelliJ:                                |
|                                                                                                    |
|     1: public class User {                                                                         |
|     2:     | (cursor piscando aqui na linha 2)                                                     |
|     3: }                                                                                           |
|                                                                                                    |
| - O autor se prepara para digitar os atributos do usuário.                                         |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (User.java - 7:03:07): Atributos Declarados e o 1º Construtor Criado!                       |
| - O autor digita 3 atributos essenciais:                                                           |
|     String username;                                                                               |
|     String email;                                                                                  |
|     int age;                                                                                       |
|                                                                                                    |
| - Logo abaixo, ele programa o PRIMEIRO CONSTRUTOR:                                                 |
|     User(String username){                                                                         |
|         this.username = username;                                                                  |
|     }                                                                                              |
|                                                                                                    |
| - POSIÇÃO DO MOUSE DO AUTOR:                                                                       |
|   O cursor branco do mouse está no topo da tela, pairando exatamente sobre a aba "Main.java"!      |
|   O autor está prestes a clicar na aba do Main para testar a criação de um usuário com 1 dado!    |
| - Timestamp visível na barra de reprodução do YouTube: 7:03:07 / 12:00:00.                         |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. A Anatomia dos Atributos da Classe `User`

Nas linhas 3 a 5 de [`User.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/User.java), o autor declarou:

```java
String username;
String email;
int age;
```

Para quem é iniciante amador, veja o que cada gaveta guarda na memória Heap quando um objeto nasce:

1. **`String username;`**:
   * O apelido ou nome de login do usuário (ex: `"Spongebob"`).
   * Tipo texto (`String`).
2. **`String email;`**:
   * O endereço de correio eletrônico (ex: `"sponge@bikini.com"`).
   * Também do tipo texto (`String`).
3. **`int age;`**:
   * A idade do usuário (ex: `30`).
   * Tipo numérico inteiro (`int`).

---

## 3. O Primeiro Construtor Sobrecarregado: Apenas o `username`

Nas linhas 7 a 9, o autor escreveu:

```java
User(String username){
    this.username = username;
}
```

### O Que Esse Construtor Faz?
* **Ele aceita APENAS UM ÚNICO PARÂMETRO:** `String username`.
* **Para que ele serve no mundo real?** É o construtor do **cadastro expresso/rápido**!  
  Imagine um aplicativo onde o usuário clica em *"Cadastrar rapidamente"* e o sistema só pede um apelido. Ele não quer preencher email nem idade agora.

### E O Que Acontece com o `email` e com a `age`? (Pergunta de Ouro de Iniciantes!)
Repare com atenção: nós **não passamos** `email` nem `age` para esse construtor!  
Como o Java lida com isso?

```
          O ESTADO DA MEMÓRIA HEAP AO USAR O 1º CONSTRUTOR:
       +──────────────────────────────────────────────────────────+
       | Objeto User na Memória Heap                              |
       | -------------------------------------------------------- |
       | username: "Spongebob"   ◄── [Preenchido pelo construtor] |
       | email:    null          ◄── [Valor padrão de Strings!]   |
       | age:      0             ◄── [Valor padrão de inteiros!]  |
       +──────────────────────────────────────────────────────────+
```

> [!TIP]
> **Lembrete Vital da JVM:**
> Se você não fornecer valor para um atributo em Java:
> * Objetos e textos (`String`) recebem automaticamente o valor **`null`** (nulo / vazio).
> * Números inteiros (`int`) recebem automaticamente o valor **`0`**.
> * Isso não é um erro! É apenas o Java dizendo que esses campos ainda não foram informados pelo usuário.

---

## 4. O Código Completo e Enumerado de `User.java` (Tela 2)

Vejamos como ficou o arquivo [`User.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/User.java) completo:

```java
1:  public class User {
2:  
3:      String username;
4:      String email;
5:      int age;
6:  
7:      User(String username){
8:          this.username = username;
9:      }
10: 
11: }
```

### Explicação Linha por Linha de `User.java`:
* **Linha 1 (`public class User {`):** Declara a classe pública `User` que servirá de molde para representar usuários.
* **Linha 2 (` `):** Linha em branco de organização.
* **Linha 3 (`    String username;`):** Declara o atributo de texto para armazenar o apelido do usuário.
* **Linha 4 (`    String email;`):** Declara o atributo de texto para armazenar o email do usuário.
* **Linha 5 (`    int age;`):** Declara o atributo inteiro para armazenar a idade.
* **Linha 6 (` `):** Espaço separando os atributos da área de construtores.
* **Linha 7 (`    User(String username){`):**
  * **O Construtor #1 da classe `User`!**
  * Tem o mesmo nome da classe (`User`).
  * Não possui tipo de retorno.
  * Recebe uma única `String` temporária chamada `username` pelos parênteses.
* **Linha 8 (`        this.username = username;`):**
  * Pega o valor que veio nos parênteses e salva no atributo `username` **deste objeto específico** (`this.username`).
* **Linha 9 (`    }`):** Fecha o bloco deste construtor.
* **Linha 11 (`}`):** Fecha a classe `User`.

---

## 5. O Movimento do Mouse: O Que Vem a Seguir no `Main.java`?

Na **Tela 2**, o mouse do autor está repousando sobre a aba **`Main.java`**.  
Ele vai clicar nessa aba para demonstrar como criar o primeiro usuário usando este construtor de 1 parâmetro:

```java
User user1 = new User("Spongebob");
```

E em seguida, ele adicionará os outros construtores sobrecarregados em `User.java`:
* Construtor de 2 parâmetros: `User(String username, String email)`
* Construtor de 3 parâmetros: `User(String username, String email, int age)`
* Construtor vazio: `User()`

---

## 6. O Que Sai no Console Neste Momento?

* Se você compilar e rodar o projeto agora, **nada é impresso no console** (`Process finished with exit code 0`).
* Ambos os arquivos ([`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) e [`User.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/User.java)) estão compilando com 100% de sucesso e zero erros!

---

## 7. Tabela de Resumo Mental para Fixação

| Atributo | Tipo de Dado | Foi Inicializado pelo Construtor #1? | Valor que Ficará na Memória Heap |
| :--- | :---: | :---: | :--- |
| `username` | `String` | **SIM** | O texto passado (ex: `"Spongebob"`) |
| `email` | `String` | ❌ **NÃO** | **`null`** (valor padrão da JVM para objetos) |
| `age` | `int` | ❌ **NÃO** | **`0`** (valor padrão da JVM para inteiros) |
