# Curso de Java — Bro Code
## Capítulo 40: Construtores (*Constructors*) — A Passagem de Argumentos, o Construtor com `this` e o Erro Clássico de Compilação

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e detalhada**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as nuances visuais das 4 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamps `6:54:00` até `6:55:39` de 12:00:00) foram descritas minuciosamente em texto corrido e diagramas para que você compreenda exatamente o que o autor estava ensinando a cada segundo sem precisar pausar ou rever o vídeo.

---

## 1. O Cenário Visual das 4 Imagens (O Passo a Passo Pedagógico do Vídeo)

Nesta sequência brilhante de 4 imagens, o professor Bro Code faz algo muito comum entre os melhores professores de programação: **ele induz um erro de propósito para ensinar como o compilador do Java pensa**.

Veja o fluxo visual das 4 telas:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (Main.java - 6:54:10): O Retorno ao Código Limpo                                            |
| - A janela de execução (Run) foi fechada pelo autor no "✖".                                       |
| - O editor de código volta a ocupar a tela inteira.                                                |
| - Linha 5: Student student1 = new Student(); (cursor posicionado no final da linha).               |
| - O autor se prepara para parar de criar alunos vazios.                                            |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (Main.java - 6:54:28): A Tentativa de Passar Argumentos e o Sublinhado Vermelho             |
| - Linha 5 alterada para: Student student1 = new Student("Spongebob", 30, 3.2);                    |
| - ALERTA VISUAL NO INTELLIJ:                                                                       |
|   Toda a parte ("Spongebob", 30, 3.2) fica com uma ONDULAÇÃO VERMELHA em baixo!                   |
| - Por quê? Porque a classe Student.java ainda NÃO POSSUI nenhum construtor que aceite esses 3 dados!|
| - Timestamp do YouTube visível na barra de progresso: 6:54:28 / 12:00:00.                          |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 3 (Student.java - 6:55:00): Criando o Construtor com 'this'                                   |
| - Bro Code clica na aba Student.java para resolver a ondulação vermelha.                           |
| - Apaga o '= "Spongebob"' da linha 3 (agora é apenas 'String name;').                             |
| - Escreve o construtor das linhas 8 a 12:                                                          |
|     Student(String name, int age, double gpa){                                                     |
|         this.name = name;                                                                          |
|         this.age = age;                                                                            |
|         this.gpa = gpa;                                                                            |
|     }                                                                                              |
| - Cursor pisca na linha 11 logo após 'this.gpa = gpa;'.                                            |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 4 (Main.java - 6:55:39): O Erro de Compilação Proposital na Aba 'Build'                      |
| - Bro Code volta para o Main.java, onde:                                                           |
|     Linha 5: Student student1 = new Student("Spongebob", 30, 3.2);  (CORRETO)                      |
|     Linha 6: Student student2 = new Student();                     (ERRO FATAL!)                   |
| - Ele clica no botão verde Play (Run) no canto superior direito.                                   |
| - A janela inferior 'Build' se abre em vermelho sangue exibindo o erro:                            |
|     java: constructor Student in class Student cannot be applied to given types;                   |
|       required: java.lang.String,int,double                                                        |
|       found:    no arguments                                                                       |
|       reason: actual and formal argument lists differ in length                                    |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. Momento 2: Por Que o IntelliJ Mostrou uma Ondulação Vermelha na Tela 2?

Na **Tela 2**, o autor digitou no `Main.java`:

```java
Student student1 = new Student("Spongebob", 30, 3.2);
```

E imediatamente o IntelliJ desenhou uma linha ondulada vermelha embaixo de `("Spongebob", 30, 3.2)`.

### A Metáfora da Tomada de Três Pinos:
Imagine que você comprou um aparelho que tem um plugue de 3 pinos (`String`, `int`, `double`), mas a parede da sua casa só tem uma tomada antiga de 2 pinos ou sem entrada nenhuma (`Student()`).  
O plugue simplesmente **não encaixa**!

* O `Main.java` estava tentando entregar 3 presentes para a classe `Student`:
  1. `"Spongebob"` (texto)
  2. `30` (número inteiro)
  3. `3.2` (número decimal)
* Mas naquele segundo, a classe `Student.java` ainda era assim:
  ```java
  public class Student {
      String name;
      int age;
      double gpa;
  }
  ```
* O Java olhou para `Student.java` e disse: *"Ei, você não tem nenhum construtor preparado para receber esses 3 presentes! Eu não sei onde guardar isso!"*.  
É exatamente por isso que o autor teve que ir para a **Tela 3**!

---

## 3. Momento 3: A Construção do Construtor em `Student.java` (Tela 3)

Na **Tela 3**, o autor clica na aba `Student.java` e programa a solução.

### Código de `Student.java` na Tela 3:
```java
1:  public class Student {
2:  
3:      String name;
4:      int age;
5:      double gpa;
6:      boolean isEnrolled;
7:  
8:      Student(String name, int age, double gpa){
9:          this.name = name;
10:         this.age = age;
11:         this.gpa = gpa;
12:     }
13: 
14: }
```

### Explicação Linha por Linha de `Student.java` (Para Iniciantes Amadores):

* **Linha 1 (`public class Student {`):**
  Abre a definição da nossa classe `Student`.
* **Linhas 3 a 6 (`String name; int age; double gpa; boolean isEnrolled;`):**
  Estes são os **atributos** (ou variáveis de instância). 
  * Repare que o autor apagou `= "Spongebob"`. Agora os atributos estão limpos e desimpedidos! Cada novo estudante terá seus próprios valores preenchidos na hora do nascimento.
* **Linha 8 (`    Student(String name, int age, double gpa){`):**
  **A Criação do Construtor!**
  * O nome DEVE ser exatamente idêntico ao nome da classe: `Student`.
  * NÃO tem tipo de retorno (sem `void`, sem `int`, sem `String`).
  * Entre os parênteses `(...)`, criamos 3 parâmetros receptores:
    * `String name`: uma variável temporária para segurar o nome que vem de fora.
    * `int age`: uma variável temporária para segurar a idade.
    * `double gpa`: uma variável temporária para segurar a nota média.
* **Linha 9 (`        this.name = name;`):**
  * A palavra-chave **`this`** aponta para **este objeto específico** que está sendo construído agora na memória Heap.
  * `this.name` = O atributo da classe (gaveta permanente na memória).
  * `= name;` = O valor temporário que acabou de entrar pelos parênteses da Linha 8.
* **Linha 10 (`        this.age = age;`):**
  Pega o número inteiro que veio em `age` e guarda no atributo `this.age` deste estudante.
* **Linha 11 (`        this.gpa = gpa;`):**
  Pega o decimal que veio em `gpa` e guarda no atributo `this.gpa` deste estudante.
* **Linha 12 (`    }`):**
  Fecha o bloco do construtor.
* **Linha 14 (`}`):**
  Fecha o bloco da classe.

---

## 4. Momento 4: O Erro de Compilação na Tela 4 (A Grande Lição de Java!)

Na **Tela 4**, o autor volta para o arquivo `Main.java` e clica no botão verde **Play (Run)**.

Veja como estava o arquivo `Main.java` naquele segundo:

```java
1: public class Main {
2:     public static void main(String[] args) {
3: 
4: 
5:         Student student1 = new Student("Spongebob", 30, 3.2);
6:         Student student2 = new Student(); // <--- O MOTIVO DO DESASTRE!
7: 
8:         System.out.println(student1.name);
9:         System.out.println(student2.name);
10:    }
11: }
```

### O que Apareceu na Tela? (Aba 'Build' em Vermelho)

O programa **NÃO RODOU!** A janela inferior que se abriu não foi a aba `Run` (execução), mas sim a aba `Build` (compilação), com letras vermelhas anunciando um erro fatal:

```text
C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:6:28
java: constructor Student in class Student cannot be applied to given types;
  required: java.lang.String,int,double
  found:    no arguments
  reason: actual and formal argument lists differ in length
```

---

## 5. Traduzindo e Explicando o Erro Palavra por Palavra (Para Quem Nunca Viu Isso Antes)

Este erro assusta 100% dos iniciantes em Java. Vamos dissecar cada linha da mensagem do compilador:

### 1. `C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:6:28`
* **Onde ocorreu:** No arquivo `Main.java`, na **linha 6**, na **coluna 28** (exatamente onde está escrito `new Student()`).

### 2. `java: constructor Student in class Student cannot be applied to given types;`
* **Tradução:** *"O construtor Student na classe Student não pode ser aplicado aos tipos fornecidos."*
* **Explicação:** Você tentou chamar o construtor `Student` de uma forma que ele não aceita!

### 3. `required: java.lang.String,int,double`
* **Tradução:** *"O que é exigido / obrigatório: um texto (String), um inteiro (int) e um decimal (double)."*
* **Explicação:** O construtor que você criou em `Student.java` exige OBRIGATORIAMENTE esses 3 tipos de dados para funcionar.

### 4. `found: no arguments`
* **Tradução:** *"O que foi encontrado na linha 6: NENHUM argumento (vazio!)."*
* **Explicação:** Você colocou apenas parênteses vazios `()`. Você não passou nada!

### 5. `reason: actual and formal argument lists differ in length`
* **Tradução:** *"Motivo: As listas de argumentos reais e formais diferem em tamanho/quantidade."*
* **Explicação:** O molde espera 3 itens (tamanho 3), mas você enviou 0 itens (tamanho 0). 3 é diferente de 0!

---

## 6. A Regra de Ouro Oculta do Java: O Construtor Padrão Invisível

Por que antes funcionava escrever `new Student()` com parênteses vazios, e agora de repente quebrou tudo?

> [!WARNING]
> **A Regra Mais Importante de Construtores em Java:**
> 1. **Quando você NÃO escreve nenhum construtor:** O compilador Java cria nos bastidores um construtor padrão invisível sem argumentos: `Student() { }`. Por isso, nas partes 1 e 2, `new Student()` funcionava normalmente!
> 2. **A partir do momento em que VOCÊ escreve QUALQUER construtor com parâmetros:** O Java entende: *"Opa! O programador assumiu o controle de como esse objeto deve nascer! Ele quer que os dados sejam obrigatórios!"*.
> 3. **Consequência Imediata:** O Java **DESTRÓI e ELIMINA** o construtor padrão invisível vazio!
> 4. Portanto, a chamada `new Student()` na linha 6 tentou chamar um construtor que **não existe mais**.

---

## 7. Como o Bro Code Resolve Isso a Seguir?

Para consertar esse erro de compilação, o autor tem duas escolhas na linha 6:
* **Escolha A (O que ele faz no vídeo):** Passar os argumentos para o Patrick também!
  ```java
  Student student2 = new Student("Patrick", 34, 1.5);
  ```
* **Escolha B (Construtor Sobrecarregado):** Criar um segundo construtor vazio em `Student.java` se ele quisesse permitir alunos sem dados. Mas na escola do Bro Code, todo aluno precisa de nome, idade e média!

---

## 8. Tabela de Resumo Mental: Comparando as 4 Telas

| Tela | Arquivo Ativo | O Que o Autor Fez? | Estado do Código |
| :---: | :---: | :--- | :--- |
| **1** | `Main.java` | Fechou a aba `Run` no "✖" | Código limpo, pronto para digitação |
| **2** | `Main.java` | Digitou `new Student("Spongebob", 30, 3.2)` | **Ondulação vermelha** (o construtor ainda não existia na outra classe) |
| **3** | `Student.java` | Criou `Student(String name, int age, double gpa)` com `this` | Construtor com 3 parâmetros criado com sucesso |
| **4** | `Main.java` | Clicou em Play com `student2 = new Student()` ainda vazio | **Erro de compilação**: O Java removeu o construtor vazio padrão! |

---

## 9. O que Deve Sair no Console (Aba Build)?

Nesta etapa do vídeo, **nenhum resultado de programa é impresso**, pois o programa falhou antes mesmo de rodar. O que sai no terminal/aba Build é rigorosamente:

```text
C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:6:28
java: constructor Student in class Student cannot be applied to given types;
  required: java.lang.String,int,double
  found:    no arguments
  reason: actual and formal argument lists differ in length
```

Este erro é a prova cabal de que construtores tornam a criação de objetos **segura e consistente**, impedindo que objetos nasçam incompletos no seu sistema!
