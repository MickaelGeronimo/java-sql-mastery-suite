# Curso de Java — Bro Code
## Capítulo 41: Construtores Sobrecarregados (*Overloaded Constructors 🛠️*) — Parte 4: Instanciando `user1`, Valores Padrão Amigáveis (`"Not provided"`) e a Primeira Execução no Console

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e descritiva**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as nuances visuais das 5 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamps `7:03:32` até aproximadamente `7:04:10` de 12:00:00) foram descritas minuciosamente em texto corrido e diagramas para que você compreenda exatamente o fluxo de raciocínio do autor sem precisar rever o vídeo.

---

## 1. O Cenário Visual das 5 Imagens (O Que o Professor Bro Code Faz na Tela)

Nesta sequência de 5 telas, o professor Bro Code conecta a classe `User.java` com o programa principal `Main.java`, cria o primeiro usuário e faz um refinamento de código muito importante para evitar valores nulos feios no console:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (Main.java - 7:03:32): Instanciando o Primeiro Usuário                                      |
| - Aba ativa: Main.java.                                                                            |
| - Na linha 5, Bro Code digita:                                                                     |
|     User user1 = new User("Spongebob");                                                            |
| - Ele chama o construtor que aceita 1 único argumento (o apelido "Spongebob").                     |
| - Não há erro vermelho! O Java encontrou o construtor correspondente em User.java.                 |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (User.java - 7:03:53): Refinando o Construtor com Valores Padrão Inteligentes               |
| - Bro Code clica na aba User.java para deixar o construtor mais profissional.                      |
| - Dentro de User(String username), ele adiciona:                                                   |
|     this.email = "Not provided";                                                                   |
|     this.age = 0;                                                                                  |
| - Em vez de deixar o email como 'null' (nulo), ele coloca um texto amigável: "Not provided"!       |
| - O cursor do mouse se move para o topo da tela, voltando para a aba "Main.java".                  |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 3 (Main.java): Digitando a Primeira Impressão                                                 |
| - De volta ao Main.java, o autor digita na linha 7:                                                |
|     System.out.println(user1.username);                                                            |
| - A linha inteira fica selecionada com destaque azul no editor.                                    |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 4 (Main.java): Imprimindo os 3 Atributos de user1 e Clicando em Play                          |
| - O autor completa as 3 linhas de impressão nas linhas 7, 8 e 9:                                   |
|     System.out.println(user1.username);                                                            |
|     System.out.println(user1.email);                                                               |
|     System.out.println(user1.age);                                                                 |
| - O cursor do mouse está no canto superior direito sobre o botão verde Play (Run 'Main.java').     |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 5 (Console Run): O Resultado em Verde no Terminal!                                            |
| - A janela inferior 'Run' se abre e imprime:                                                       |
|     Spongebob                                                                                      |
|     Not provided                                                                                   |
|     0                                                                                              |
|     Process finished with exit code 0                                                              |
| - O autor passa o mouse sobre a palavra "Spongebob", deixando-a em destaque azul para mostrar      |
|   que o objeto foi criado com sucesso!                                                             |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. A Grande Lição da Tela 2: Por Que `"Not provided"` em Vez de `null`?

Observe o que o autor fez dentro do construtor de `User.java`:

```java
User(String username){
    this.username = username;
    this.email = "Not provided";
    this.age = 0;
}
```

### O Problema do `null` no Mundo Real:
Se ele não tivesse colocado `this.email = "Not provided";`, o atributo `email` ficaria com o valor padrão do Java: **`null`**.
* Imprimir `null` na tela de um aplicativo de verdade é feio e amador para o cliente final.
* Mas pior ainda: se alguém tentar fazer uma operação com um texto nulo (como `user1.email.toUpperCase()`), o Java trava o programa inteiro com o erro mais temido da linguagem: **`NullPointerException`**!

### A Solução Profissional:
Ao carimbar `this.email = "Not provided"` (Não fornecido) e `this.age = 0`:
1. O objeto já nasce **100% preenchido e seguro**.
2. Quem lê o relatório ou a tela do sistema entende claramente: *"Ah, esse usuário optou por não cadastrar o email ainda!"*.

---

## 3. O Código Completo e Enumerado de `User.java` (Tela 2)

```java
1:  public class User {
2:  
3:      String username;
4:      String email;
5:      int age;
6:  
7:      User(String username){
8:          this.username = username;
9:          this.email = "Not provided";
10:         this.age = 0;
11:     }
12: 
13: }
```

### Explicação Linha por Linha de `User.java`:
* **Linhas 3 a 5:** Declaram os 3 atributos da classe (`username`, `email`, `age`).
* **Linha 7 (`User(String username){`):** O construtor que aceita apenas 1 argumento de texto.
* **Linha 8 (`this.username = username;`):** Salva o apelido que veio por parâmetro na memória deste objeto.
* **Linha 9 (`this.email = "Not provided";`):** Como quem chamou não passou nenhum email, o próprio construtor define de fábrica o texto `"Not provided"`.
* **Linha 10 (`this.age = 0;`):** Como não foi passada nenhuma idade, o construtor define de fábrica o valor `0`.
* **Linha 11 (`}`):** Finaliza o construtor.
* **Linha 13 (`}`):** Finaliza a classe `User`.

---

## 4. O Código Completo e Enumerado de `Main.java` (Telas 3 e 4)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:  
5:          User user1 = new User("Spongebob");
6:  
7:          System.out.println(user1.username);
8:          System.out.println(user1.email);
9:          System.out.println(user1.age);
10:     }
11: }
```

### Explicação Linha por Linha de `Main.java`:
* **Linha 5 (`User user1 = new User("Spongebob");`):**
  * Cria uma variável `user1` na memória Stack.
  * O operador `new` aloca um bloco na memória Heap.
  * O Java procura em `User.java` um construtor que aceite **apenas 1 String**. Ele encontra `User(String username)` e o executa com perfeição!
* **Linha 7 (`System.out.println(user1.username);`):**
  * Busca o atributo `username` do objeto e imprime:
  * **Saída gerada:** `Spongebob`
* **Linha 8 (`System.out.println(user1.email);`):**
  * Busca o atributo `email` e imprime:
  * **Saída gerada:** `Not provided`
* **Linha 9 (`System.out.println(user1.age);`):**
  * Busca o atributo `age` e imprime:
  * **Saída gerada:** `0`
* **Linhas 10 e 11:** Fecham o método e a classe.

---

## 5. O Mapa de Memória (Stack vs Heap) do Objeto `user1`

Veja como o objeto `user1` está armazenado fisicamente na memória RAM do computador:

```
          MEMÓRIA STACK (Variável Local)                     MEMÓRIA HEAP (Objeto Real)
       +──────────────────────────────────+           +─────────────────────────────────────────+
       |                                  |           |  Instância #1 da classe User            |
       |  user1 ──────────────────────────┼──────────►|  username: "Spongebob" (veio de fora)   |
       |  (Endereço de memória @4f023edb) |           |  email:    "Not provided" (de fábrica)  |
       |                                  |           |  age:      0              (de fábrica)  |
       +──────────────────────────────────+           +─────────────────────────────────────────+
```

---

## 6. A Saída Real do Terminal (Reprodução Exata da Tela 5)

Quando o autor clicou no botão Play (Tela 4), o console da aba `Run` imprimiu:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\..." Main
Spongebob
Not provided
0

Process finished with exit code 0
```

### Análise das Linhas Impressas:
1. `Spongebob`: O nome passado na criação.
2. `Not provided`: O email definido como padrão seguro pelo construtor.
3. `0`: A idade definida como padrão seguro pelo construtor.
4. `Process finished with exit code 0`: O Java finalizou a execução com 100% de sucesso!

---

## 7. O Que Vem a Seguir no Capítulo 41?

Agora que o construtor de 1 parâmetro foi testado e aprovado, o professor Bro Code irá adicionar os próximos construtores sobrecarregados em `User.java`:
1. `User(String username, String email)` (para usuários que informam apelido e email).
2. `User(String username, String email, int age)` (para usuários que preenchem o cadastro completo).
3. `User()` (para usuários anônimos/convidados sem nenhum dado).

E em `Main.java`, ele criará `user2`, `user3` e `user4` demonstrando a sobrecarga em potência máxima!
