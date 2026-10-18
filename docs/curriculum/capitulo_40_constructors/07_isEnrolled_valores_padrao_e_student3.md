# Curso de Java — Bro Code
## Capítulo 40: Construtores (*Constructors*) — Atributos com Valores Padrão (`this.isEnrolled = true`), Impressão de Booleanos e o Alerta Vermelho em `student3`

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e descritiva**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as informações visuais das 4 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamps `6:58:37` até aproximadamente `6:59:30` de 12:00:00) foram descritas minuciosamente em texto corrido e diagramas para que você compreenda com perfeição cada decisão tomada pelo autor no código.

---

## 1. O Cenário Visual das 4 Imagens (O Que o Professor Bro Code Mostra no Vídeo)

Nesta sequência de 4 telas, o professor ensina que um construtor pode fazer muito mais do que apenas repassar argumentos: ele pode **aplicar regras de negócio automáticas** aos objetos, como ativar uma matrícula logo no nascimento:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (Student.java - 6:58:37): Adicionando 'this.isEnrolled = true;'                             |
| - O autor restaura os parâmetros limpos: Student(String name, int age, double gpa)                 |
| - Mantém: this.name = name; this.age = age; this.gpa = gpa;                                        |
| - ADICIONA A LINHA 12:                                                                             |
|     this.isEnrolled = true;                                                                        |
| - Repare: 'isEnrolled' NÃO está nos parênteses do construtor!                                      |
| - Cursor do teclado pisca no final da linha 12: this.isEnrolled = true;|                            |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (Main.java - 6:58:57): Imprimindo o Status da Matrícula                                     |
| - No Main.java, o autor adiciona a impressão do quarto atributo para ambos os alunos:             |
|     Linha 11: System.out.println(student1.isEnrolled);                                             |
|     Linha 16: System.out.println(student2.isEnrolled);                                             |
| - Cursor do mouse se posiciona no canto superior direito sobre o botão Play (Run 'Main').          |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 3 (Console Run): O Resultado Completo com 4 Dados por Aluno                                   |
| - A janela inferior 'Run' se abre e imprime em verde brilhante 8 linhas de dados:                  |
|     Spongebob / 30 / 3.2 / true                                                                    |
|     Patrick / 34 / 1.5 / true                                                                      |
| - O autor seleciona com o mouse as linhas "3.2" e "true" com uma faixa azul de destaque!           |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 4 (Main.java): A Tentativa de Criar o Terceiro Aluno (student3)                               |
| - O autor digita na linha 7:                                                                       |
|     Student student3 = new Student();                                                              |
| - ALERTA VERMELHO IMEDIATO:                                                                        |
|   Os parênteses vazios "()" ficam com uma ONDULAÇÃO VERMELHA em baixo!                             |
|   No canto superior direito surge o ícone vermelho com: "1 erro"!                                  |
| - O Java avisa novamente: o construtor exige 3 parâmetros; student3 não pode nascer vazio!         |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. A Grande Lição da Tela 1: Atributos com Valores Padrão Automáticos

Observe com muita atenção o construtor na **Tela 1**:

```java
Student(String name, int age, double gpa){
    this.name = name;
    this.age = age;
    this.gpa = gpa;
    this.isEnrolled = true; // <--- OLHE COM ATENÇÃO AQUI!
}
```

### O Que Torna Essa Linha Tão Especial?
Repare nos parênteses da linha de cima: `(String name, int age, double gpa)`.  
O parâmetro `isEnrolled` **não existe** entre os parênteses!

> [!TIP]
> **Conceito Fundamental em Orientação a Objetos:**
> O construtor não serve apenas para copiar dados que o usuário digitou. Ele serve para **colocar o objeto em um estado válido de funcionamento**.
> Em um sistema escolar real:
> * O aluno não escolhe se quer nascer matriculado ou não: se ele está passando pela secretaria para ser registrado no sistema, **ele está matriculado por definição**!
> * Portanto, em vez de forçar o programador a digitar `new Student("Spongebob", 30, 3.2, true)` toda vez, o próprio construtor já carimba `this.isEnrolled = true;` de fábrica!

### Outros Exemplos do Mundo Real:
* **Conta Bancária:** Quando você abre uma conta, o saldo inicial pode ser definido automaticamente como `this.saldo = 0.0;` e o status como `this.ativa = true;`.
* **Carrinho de Compras:** Ao criar um carrinho, ele nasce automaticamente com `this.totalItens = 0;`.
* **Personagem de Jogo:** Ao nascer, o herói ganha automaticamente `this.vida = 100;` e `this.estaVivo = true;`.

---

## 3. O Código Completo de `Student.java` (Tela 1)

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
15: }
```

### Explicação Linha por Linha de `Student.java`:
* **Linhas 3 a 6:** Declaram os 4 atributos da classe. Como `isEnrolled` é do tipo `boolean`, ele só pode guardar dois valores: `true` (verdadeiro) ou `false` (falso).
* **Linha 8 (`Student(String name, int age, double gpa){`):** O construtor exige apenas 3 argumentos externos.
* **Linhas 9 a 11:** Salva o nome, idade e GPA específicos de cada aluno usando a palavra-chave `this.`.
* **Linha 12 (`this.isEnrolled = true;`):** Define automaticamente que o campo `isEnrolled` deste novo objeto que está na memória Heap passe a valer `true`.
* **Linha 13 (`}`):** Finaliza o construtor. O objeto está pronto para uso.

---

## 4. O Código Completo de `Main.java` (Telas 2 e 3)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          Student student1 = new Student("Spongebob", 30, 3.2);
5:          Student student2 = new Student("Patrick", 34, 1.5);
6:  
7:  
8:          System.out.println(student1.name);
9:          System.out.println(student1.age);
10:         System.out.println(student1.gpa);
11:         System.out.println(student1.isEnrolled);
12: 
13:         System.out.println(student2.name);
14:         System.out.println(student2.age);
15:         System.out.println(student2.gpa);
16:         System.out.println(student2.isEnrolled);
17:     }
18: }
```

### Explicação Linha por Linha de `Main.java`:
* **Linha 4:** Cria o objeto `student1` enviando `"Spongebob"`, `30`, `3.2`. O construtor define esses 3 valores e adiciona `isEnrolled = true`.
* **Linha 5:** Cria o objeto `student2` enviando `"Patrick"`, `34`, `1.5` e também ganha `isEnrolled = true`.
* **Linhas 8 a 11:** Imprime os 4 atributos do Bob Esponja:
  * `student1.name` -> `"Spongebob"`
  * `student1.age` -> `30`
  * `student1.gpa` -> `3.2`
  * `student1.isEnrolled` -> `true`
* **Linhas 13 a 16:** Imprime os 4 atributos do Patrick:
  * `student2.name` -> `"Patrick"`
  * `student2.age` -> `34`
  * `student2.gpa` -> `1.5`
  * `student2.isEnrolled` -> `true`

---

## 5. A Saída Real do Terminal (Tela 3)

Quando o botão verde Play foi clicado na Tela 2, a aba `Run` imprimiu:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\..." Main
Spongebob
30
3.2
true
Patrick
34
1.5
true

Process finished with exit code 0
```

### Detalhe da Tela 3:
O professor Bro Code arrasta o mouse sobre as linhas `3.2` e `true` (destaque azul) para chamar a atenção dos alunos para o fato de que:
1. O GPA `3.2` é decimal (`double`).
2. O status `true` é booleano (`boolean`).
3. Ambos foram inicializados perfeitamente pelo construtor!

---

## 6. A Tela 4: Por Que Apareceu a Ondulação Vermelha em `student3 = new Student();`?

Na **Tela 4**, o autor começa a digitar a criação do terceiro aluno:

```java
Student student3 = new Student(); // <--- ONDULAÇÃO VERMELHA NO "()"
```

E o IntelliJ marca imediatamente com alerta de erro vermelho:
* **Motivo:** O autor ainda não digitou os 3 argumentos que o construtor exige!
* O compilador do Java não perdoa: como nós criamos um construtor personalizado de 3 argumentos, **ninguém mais pode nascer sem passar esses 3 dados**.
* O autor está no meio da digitação; ele irá preencher `("Sandy", 27, 4.0)` nos próximos segundos da aula!

---

## 7. O Mapa da Memória Atualizado (Stack vs Heap)

Veja como os dois estudantes estão estruturados agora na memória RAM:

```
          MEMÓRIA STACK                                      MEMÓRIA HEAP
       +──────────────────+             +──────────────────────────────────────────────────+
       |                  |             |  Objeto Student #1                               |
       |  student1 ───────┼────────────►|  name: "Spongebob"                               |
       |                  |             |  age: 30                                         |
       |                  |             |  gpa: 3.2                                        |
       |                  |             |  isEnrolled: true  (definido automaticamente!)   |
       +──────────────────+             +──────────────────────────────────────────────────+
       |                  |             |  Objeto Student #2                               |
       |  student2 ───────┼────────────►|  name: "Patrick"                                 |
       |                  |             |  age: 34                                         |
       |                  |             |  gpa: 1.5                                        |
       |                  |             |  isEnrolled: true  (definido automaticamente!)   |
       +──────────────────+             +──────────────────────────────────────────────────+
```

---

## 8. Tabela de Resumo Mental para Fixação

| Atributo | Tipo de Dado | Veio dos Parênteses do Construtor? | Como Foi Inicializado? |
| :--- | :---: | :---: | :--- |
| `name` | `String` | **Sim** | `this.name = name;` |
| `age` | `int` | **Sim** | `this.age = age;` |
| `gpa` | `double` | **Sim** | `this.gpa = gpa;` |
| `isEnrolled` | `boolean` | ❌ **Não** | `this.isEnrolled = true;` (fixado de fábrica) |
