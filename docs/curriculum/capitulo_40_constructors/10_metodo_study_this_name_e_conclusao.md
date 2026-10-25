# Curso de Java — Bro Code
## Capítulo 40: Construtores (*Constructors*) — Conclusão de Ouro: O Método `study()`, o Acesso a `this.name` e as Ações Personalizadas dos Objetos

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e detalhada**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as nuances visuais das 4 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamps `7:00:00` até `7:00:45` de 12:00:00) foram descritas minuciosamente em texto corrido e diagramas para que você compreenda exatamente como métodos e construtores trabalham juntos em Orientação a Objetos.

---

## 1. O Cenário Visual das 4 Imagens (O Que o Professor Bro Code Mostra no Vídeo)

Nesta sequência final de 4 telas, o professor Bro Code une tudo o que aprendemos sobre construtores com os **métodos de ação**, demonstrando como cada objeto executa o mesmo código, mas produz uma resposta totalmente personalizada com base nos seus próprios dados internos:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (Student.java): Adicionando o Método study() com 'this.name'                                |
| - Aba ativa: Student.java. Linhas visíveis: 8 a 19.                                                |
| - Abaixo do construtor, nas linhas 15 a 17, Bro Code adiciona:                                     |
|     void study(){                                                                                  |
|         System.out.println(this.name + " is studying");                                            |
|     }                                                                                              |
| - Há um ícone de lâmpada amarela na margem da linha 16 (dica de autocompletar do IntelliJ).        |
| - O método não precisa de parâmetros porque já lê diretamente o crachá do objeto: 'this.name'!    |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (Main.java - 7:00:29): Limpeza dos println Antigos e Digitando a Ação                       |
| - Bro Code apaga todos aqueles 12 comandos println antigos que poluíam o código.                   |
| - Mantém apenas as 3 linhas de criação: student1, student2 e student3.                             |
| - Na linha 9, ele começa a digitar: "student1."                                                    |
| - O IntelliJ coloca uma ONDULAÇÃO VERMELHA temporária em 'student1.' porque o comando ainda está   |
|   incompleto (ele acabou de teclar o ponto "." e o autocompletar abriu as opções).                 |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 3 (Main.java): Chamando study() para os Três Estudantes                                       |
| - Bro Code completa a invocação das ações nas linhas 9, 10 e 11:                                   |
|     student1.study();                                                                              |
|     student2.study();                                                                              |
|     student3.study();                                                                              |
| - A palavra 'student3' fica em destaque cinza no editor.                                           |
| - O cursor do mouse se move até o canto superior direito sobre o botão verde triangular Play.      |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 4 (Console Run): O Sucesso Absoluto das Ações no Terminal!                                    |
| - A janela inferior 'Run' se abre e imprime em verde brilhante:                                    |
|     Spongebob is studying                                                                          |
|     Patrick is studying                                                                            |
|     Sandy is studying                                                                              |
| - O professor Bro Code passa o mouse sobre "Spongebob is studying" e seleciona com uma faixa azul  |
|   de destaque, provando que cada objeto usou o seu próprio nome ao estudar!                        |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. A Grande Novidade da Tela 1: O Que é um Método de Instância?

Até agora, nossa classe `Student` só tinha **atributos** (que são substantivos / características: nome, idade, nota, matrícula).  
Na Tela 1, o autor adiciona um **método** (que é um verbo / ação: estudar).

```java
void study(){
    System.out.println(this.name + " is studying");
}
```

### A Metáfora dos Três Alunos na Biblioteca:
Imagine que o professor entra na biblioteca e dá a ordem: *"Por favor, estudem!"*.
* O Bob Esponja começa a estudar.
* O Patrick começa a estudar.
* A Sandy começa a estudar.

A ação é exatamente a mesma para os três: **estudar (`study()`)**.  
Porém, quando cada um se manifesta, cada um usa a sua própria voz e o seu próprio nome (`this.name`)!

### Por Que Usamos `this.name` Dentro do Método?
* O método `study()` foi escrito dentro da classe `Student`.
* Ele não sabe de antemão qual aluno vai chamá-lo no futuro.
* Ao escrever `this.name`, dizemos ao Java: *"Quando alguém mandar executar este método, olhe para o crachá de **QUEM** me chamou agora e pegue o nome dele!"*.
  * Se foi `student1`, `this.name` será `"Spongebob"`.
  * Se foi `student2`, `this.name` será `"Patrick"`.
  * Se foi `student3`, `this.name` será `"Sandy"`.

---

## 3. O Código Completo e Enumerado de `Student.java`

Vejamos como ficou o arquivo `Student.java` completo:

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
12:         this.isEnrolled = true;
13:     }
14: 
15:     void study(){
16:         System.out.println(this.name + " is studying");
17:     }
18: 
19: }
```

### Explicação Linha por Linha de `Student.java`:
* **Linhas 3 a 6 (Atributos):** Declaram os 4 campos de dados permanentes que cada objeto terá na memória Heap (`name`, `age`, `gpa`, `isEnrolled`).
* **Linhas 8 a 13 (Construtor):**
  * Recebe os 3 dados externos obrigatórios (`name`, `age`, `gpa`).
  * Salva esses dados nas gavetas do objeto atual através de `this.name = name;`, `this.age = age;`, `this.gpa = gpa;`.
  * Define automaticamente que todo aluno começa matriculado: `this.isEnrolled = true;`.
* **Linha 14:** Linha em branco separando os dados das ações.
* **Linha 15 (`void study(){`):**
  * `void`: Significa "vazio" em inglês. Indica que o método executa uma ação na tela, mas não devolve nenhum cálculo matemático de volta para quem chamou.
  * `study`: Nome da ação.
  * `()`: Parênteses vazios, porque o método não precisa de parâmetros externos (ele já conhece todos os atributos do próprio objeto!).
* **Linha 16 (`System.out.println(this.name + " is studying");`):**
  * Imprime no terminal o nome DESTE aluno específico concatenado com o texto `" is studying"`.
* **Linha 17 (`}`):** Fecha o bloco do método `study`.
* **Linha 19 (`}`):** Fecha a classe `Student`.

---

## 4. O Código Completo e Enumerado de `Main.java`

Vejamos como ficou o arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) completo:

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          Student student1 = new Student("Spongebob", 30, 3.2);
5:          Student student2 = new Student("Patrick", 34, 1.5);
6:          Student student3 = new Student("Sandy", 27, 4.0);
7:  
8:  
9:          student1.study();
10:         student2.study();
11:         student3.study();
12:     }
13: }
```

### Explicação Linha por Linha de `Main.java`:
* **Linha 4:** Cria o Bob Esponja na memória Heap com idade 30 e GPA 3.2.
* **Linha 5:** Cria o Patrick na memória Heap com idade 34 e GPA 1.5.
* **Linha 6:** Cria a Sandy na memória Heap com idade 27 e GPA 4.0.
* **Linhas 7 e 8:** Espaço em branco para separar a fase de **construção** da fase de **ação**.
* **Linha 9 (`student1.study();`):**
  * O operador ponto (`.`) manda a mensagem para `student1`: *"Execute a sua ação de estudar!"*.
  * O Java salta para o método `study()`. O `this.name` resolve para `"Spongebob"`.
  * **O que sai no console:** `Spongebob is studying`
* **Linha 10 (`student2.study();`):**
  * Manda a mesma ordem para `student2`. O `this.name` resolve para `"Patrick"`.
  * **O que sai no console:** `Patrick is studying`
* **Linha 11 (`student3.study();`):**
  * Manda a mesma ordem para `student3`. O `this.name` resolve para `"Sandy"`.
  * **O que sai no console:** `Sandy is studying`
* **Linhas 12 e 13:** Fecham o método `main` e a classe `Main`.

---

## 5. O Mapa Mental de Execução (Stack vs Heap)

Veja o que acontece na memória RAM no instante em que as 3 chamadas são feitas:

```
          MEMÓRIA STACK                                      MEMÓRIA HEAP
       +──────────────────+             +──────────────────────────────────────────────────+
       |                  |             |  Objeto Student #1                               |
       |  student1 ───────┼────────────►|  name: "Spongebob"  ◄─── [student1.study()]      |
       |                  |             |  age: 30                                         |
       |                  |             |  gpa: 3.2                                        |
       |                  |             |  isEnrolled: true                                |
       +──────────────────+             +──────────────────────────────────────────────────+
       |                  |             |  Objeto Student #2                               |
       |  student2 ───────┼────────────►|  name: "Patrick"    ◄─── [student2.study()]      |
       |                  |             |  age: 34                                         |
       |                  |             |  gpa: 1.5                                        |
       |                  |             |  isEnrolled: true                                |
       +──────────────────+             +──────────────────────────────────────────────────+
       |                  |             |  Objeto Student #3                               |
       |  student3 ───────┼────────────►|  name: "Sandy"      ◄─── [student3.study()]      |
       |                  |             |  age: 27                                         |
       |                  |             |  gpa: 4.0                                        |
       |                  |             |  isEnrolled: true                                |
       +──────────────────+             +──────────────────────────────────────────────────+
```

### O Ponto Central:
O código do método `study()` existe em um único lugar na memória, mas **ele se adapta como um camaleão** a qualquer objeto que o invoca, lendo o `this.name` correspondente!

---

## 6. A Saída Real do Terminal (Tela 4)

Quando o professor clica no botão Play verde (Tela 3), o console da aba `Run` imprime com perfeição:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\..." Main
Spongebob is studying
Patrick is studying
Sandy is studying

Process finished with exit code 0
```

### Detalhamento da Saída:
1. `Spongebob is studying` — Produzido por `student1.study()`.
2. `Patrick is studying` — Produzido por `student2.study()`.
3. `Sandy is studying` — Produzido por `student3.study()`.
4. `Process finished with exit code 0` — Finalização perfeita sem nenhum erro.

O professor Bro Code passa o mouse sobre `Spongebob is studying` deixando a linha destacada em azul para chamar a atenção dos alunos para o fato de que a palavra `"Spongebob"` veio dinamicamente dos dados inicializados pelo construtor!

---

## 7. O Grande Resumo de Ouro do Capítulo 40: Construtores (*Constructors*)

Para você fixar tudo o que aprendeu em todo este capítulo e brilhar em qualquer prova ou entrevista de Java:

| Conceito | Definição | Exemplo Prático |
| :--- | :--- | :--- |
| **O que é Construtor?** | Um método especial com o mesmo nome da classe, sem retorno, que inicializa os objetos na memória. | `Student(...) { }` |
| **Para que serve?** | Para passar argumentos no nascimento do objeto e evitar o "problema dos clones". | `new Student("Spongebob", 30, 3.2)` |
| **A palavra-chave `this`** | Pronome demonstrativo que se refere ao objeto que está sendo manipulado naquele instante. | `this.name = name;` |
| **Atributos de Fábrica** | Valores padrão definidos no construtor que não precisam vir por parâmetro. | `this.isEnrolled = true;` |
| **Construtor Padrão** | Construtor vazio fornecido de graça pelo Java, que é deletado assim que você cria um personalizado. | `Student() { }` |
| **Métodos de Instância** | Ações que o objeto sabe executar utilizando seus próprios atributos internos via `this`. | `student1.study();` |
