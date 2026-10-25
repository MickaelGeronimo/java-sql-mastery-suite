# 🔨 Capítulo 40: Construtores (*Constructors*) em Java — Parte 2
## 🧽 Atributos em `Student.java`, a Sala de Aula dos Clones ("Spongebob" e "Spongebob") e a Urgência dos Construtores com Parâmetros

> **Arquivos de Estudo:** `Student.java` e `Main.java`  
> **Timestamp do Vídeo:** `6:52:30` até `6:53:20` / `12:00:00`  
> **Canal:** Bro Code — *Java Full Course for free*  
> **Objetivo:** Explicar para um iniciante absoluto cada detalhe das 3 telas do vídeo: como declarar atributos diversificados em `Student.java` (`name`, `age`, `gpa`, `isEnrolled`), o que acontece com variáveis que não recebem valor inicial (valores padrão da JVM), a demonstração prática no terminal onde dois estudantes instanciados imprimem `"Spongebob"` e `"Spongebob"`, e a motivação pedagógica definitiva para a criação de um **Construtor com Parâmetros**.

---

## 📸 Reconstituição Visual Completa das 3 Imagens (Sem Precisar do Vídeo)

Se você estiver lendo este material no Google Docs ou impresso, aqui está a descrição minuciosa do que aconteceu na tela do Bro Code em cada um dos 3 momentos:

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 1 (6:52:45): Declarando os Atributos em Student.java                                      |
+---------------------------------------------------------------------------------------------------+
| NA JANELA DO CÓDIGO (Aba Student.java ativa):                                                     |
| Bro Code digita 4 atributos fundamentais para representar um estudante:                           |
|                                                                                                   |
| 1: public class Student {                                                                         |
| 2:                                                                                                |
| 3:     String name = "Spongebob";                                                                 |
| 4:     int age;                                                                                   |
| 5:     double gpa;                                                                                |
| 6:     boolean isEnrolled;                                                                        |
| 7: }                                                                                              |
|                                                                                                   |
| Observação Visual: O atributo "name" recebe o valor fixo "Spongebob".                             |
| Já os atributos "age", "gpa" e "isEnrolled" são apenas declarados, sem valor explícito!           |
| O cursor do teclado pisca na linha 3 logo após o ponto e vírgula de "Spongebob";.                 |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 2 (6:53:13): Instanciando Dois Estudantes no Main.java                                    |
+---------------------------------------------------------------------------------------------------+
| NA JANELA DO CÓDIGO (Aba Main.java ativa):                                                        |
| Bro Code cria dois objetos estudantes e manda imprimir o nome de ambos:                           |
|                                                                                                   |
|  4:         Student student1 = new Student();                                                     |
|  5:         Student student2 = new Student();                                                     |
|  6:                                                                                               |
|  7:                                                                                               |
|  8:         System.out.println(student1.name);                                                    |
|  9:         System.out.println(student2.name);                                                    |
|                                                                                                   |
| Observação Visual: O cursor do mouse se move até o canto superior direito e clica no botão verde   |
| triangular de Play (Run) para rodar a aplicação.                                                  |
+---------------------------------------------------------------------------------------------------+
```

```
+---------------------------------------------------------------------------------------------------+
| MOMENTO 3 (6:53:20): O Problema dos Clones Escancarado no Terminal                                |
+---------------------------------------------------------------------------------------------------+
| O terminal integrado ocupa a tela exibindo a saída em texto verde:                                |
|                                                                                                   |
| Spongebob                                                                                         |
| Spongebob                                                                                         |
|                                                                                                   |
| Process finished with exit code 0                                                                 |
|                                                                                                   |
| DETALHE VISUAL DO PROFESSOR:                                                                      |
| Bro Code passa o mouse e seleciona com destaque AZUL brilhante o segundo "Spongebob" no terminal!|
| Ele faz uma pausa proposital e provoca os alunos: "Vejam que problema! O estudante 1 se chama    |
| Spongebob e o estudante 2 TAMBÉM se chama Spongebob! Numa escola de verdade, cada aluno tem o seu |
| próprio nome, sua própria idade e sua própria nota! Nós precisamos de um CONSTRUTOR!".            |
+---------------------------------------------------------------------------------------------------+
```

---

## 🧠 Explicação Didática Extrema (Para Leigos Absolutos)

### 1. Os Quatro Atributos de um Estudante

Abra o arquivo `Student.java` e observe a ficha cadastral que estamos criando:

```java
public class Student {

    String name = "Spongebob";
    int age;
    double gpa;
    boolean isEnrolled;
}
```

Vamos entender o papel de cada uma dessas 4 gavetas:
1. `String name = "Spongebob";` $\rightarrow$ Nome do aluno (texto). Aqui o professor colocou um valor fixo de propósito.
2. `int age;` $\rightarrow$ Idade do aluno em anos inteiros (ex: 18, 20, 25).
3. `double gpa;` $\rightarrow$ A média escolar do aluno.  
   *(Nota cultural: **GPA** significa **Grade Point Average**, o sistema padrão de notas em escolas e universidades americanas, variando de `0.0` a `4.0`, onde 4.0 é a nota máxima "A").*
4. `boolean isEnrolled;` $\rightarrow$ O aluno está devidamente matriculado e frequente? (`true` para sim, `false` para não).

---

### 2. O Que Acontece com Atributos SEM Valor Inicial? (Valores Padrão da JVM)

Repare que nós **não** demos valores para `age`, `gpa` e `isEnrolled`.  
Muitos iniciantes pensam:  
*"Professor, o Java vai dar erro de compilação por falta de valor?"*

**RESPOSTA: NÃO!**  
Em Java, quando uma variável é declarada dentro de uma classe (um atributo de instância), a máquina virtual (JVM) **preenche a gaveta automaticamente com um valor padrão seguro (*Default Value*)**:

| Tipo de Dado | Valor Padrão Automático da JVM | Exemplo no Nosso Objeto |
| :---: | :---: | :--- |
| `int` / `short` / `byte` / `long` | **`0`** | `student1.age` vale `0` |
| `double` / `float` | **`0.0`** | `student1.gpa` vale `0.0` |
| `boolean` | **`false`** | `student1.isEnrolled` vale `false` |
| Objetos / `String` | **`null`** (nulo / vazio) | Se não tivéssemos colocado `"Spongebob"`, seria `null` |

Portanto, sem um construtor, os nossos estudantes nascem como **fantasmas**:
* Nome: `"Spongebob"`
* Idade: `0` anos
* Nota: `0.0`
* Matriculado: `false` (desmatriculado)

---

### 3. A Sala de Aula dos Clones (Momento 3)

No Momento 2 e 3, Bro Code rodou o `Main.java` e o console imprimiu:
```text
Spongebob
Spongebob
```

Isso é o que chamamos didaticamente de **"O Problema dos Clones"**:
* Se você tiver 30 alunos em uma sala de aula:
  ```java
  Student student1 = new Student();
  Student student2 = new Student();
  ...
  Student student30 = new Student();
  ```
  Todos os 30 alunos se chamarão Spongebob, terão 0 anos e nota 0.0!
* Como você resolveria isso **sem** construtor?
  Você teria que digitar na mão:
  ```java
  student1.name = "Spongebob";
  student1.age = 30;
  student1.gpa = 3.2;
  student1.isEnrolled = true;

  student2.name = "Patrick";
  student2.age = 34;
  student2.gpa = 1.5;
  student2.isEnrolled = false;
  ```
  Isso dá **4 linhas de código para cada aluno**! Para 30 alunos, seriam **120 linhas de código repetitivo e maçante**!

---

### 4. A Missão do Construtor com Parâmetros (O Spoiler da Solução)

Bro Code destacou o segundo `"Spongebob"` em azul para deixar claro:  
**É por isso que Construtores existem!**

O Construtor nos permitirá substituir todo esse trabalho braçal por uma **única linha limpa e elegante**:
```java
Student student1 = new Student("Spongebob", 30, 3.2, true);
Student student2 = new Student("Patrick", 34, 1.5, false);
```

Na próxima tela, aprenderemos a escrever essa regra dentro de `Student.java`!

---

## 💾 Modelo Mental de Memória (Stack & Heap) Atual

```text
========================================================================================
MEMÓRIA STACK (PILHA)                         MEMÓRIA HEAP (MONTE DINÂMICO)
========================================================================================
[main()]
  student1: 0x11aa22 ----------------------->  [Objeto Student 1 em 0x11aa22]
                                               |-- name:       "Spongebob"
                                               |-- age:        0      (padrão da JVM)
                                               |-- gpa:        0.0    (padrão da JVM)
                                               |-- isEnrolled: false  (padrão da JVM)
                                               
  student2: 0x33bb44 ----------------------->  [Objeto Student 2 em 0x33bb44]
                                               |-- name:       "Spongebob" (clone!)
                                               |-- age:        0      (padrão da JVM)
                                               |-- gpa:        0.0    (padrão da JVM)
                                               |-- isEnrolled: false  (padrão da JVM)

Ambos os objetos são independentes no Heap, mas possuem os mesmos dados genéricos!
========================================================================================
```

---

## 🔍 Análise Linha por Linha de Todo o Código

### Arquivo 1: `Student.java`

```java
1: public class Student {
2: 
3:     String name = "Spongebob";
4:     int age;
5:     double gpa;
6:     boolean isEnrolled;
7: }
```

* **Linha 1 (`public class Student {`):** Declaração da classe molde `Student`.  
  * No console: Nada.
* **Linha 3 (`String name = "Spongebob";`):** Declara o atributo de texto `name` com o valor fixo inicial `"Spongebob"`.  
  * No console: Nada.
* **Linha 4 (`int age;`):** Declara o atributo numérico inteiro `age`. Como não tem valor, recebe `0` por padrão da JVM.  
  * No console: Nada.
* **Linha 5 (`double gpa;`):** Declara o atributo decimal `gpa`. Recebe `0.0` por padrão da JVM.  
  * No console: Nada.
* **Linha 6 (`boolean isEnrolled;`):** Declara a bandeira booleana `isEnrolled`. Recebe `false` por padrão da JVM.  
  * No console: Nada.
* **Linha 7 (`}`):** Encerra a classe `Student`.

---

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         Student student1 = new Student();
 5:         Student student2 = new Student();
 6: 
 7:         System.out.println(student1.name);
 8:         System.out.println(student2.name);
 9:     }
10: }
```

* **Linha 4 (`Student student1 = new Student();`):** Fabrica o primeiro aluno no Heap.  
  * No console: Nada.
* **Linha 5 (`Student student2 = new Student();`):** Fabrica o segundo aluno no Heap.  
  * No console: Nada.
* **Linha 7 (`System.out.println(student1.name);`):** Lê o atributo `name` de `student1`.  
  * No console: `Spongebob`
* **Linha 8 (`System.out.println(student2.name);`):** Lê o atributo `name` de `student2`.  
  * No console: `Spongebob`
* **Linhas 9-10:** Fechamento do método `main` e da classe.

---

## 📜 Código-Fonte Completo e Atualizado dos Arquivos

### Arquivo 1: `Student.java`
```java
public class Student {

    String name = "Spongebob";
    int age;
    double gpa;
    boolean isEnrolled;
}
```

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
```java
public class Main {
    public static void main(String[] args) {

        Student student1 = new Student();
        Student student2 = new Student();

        System.out.println(student1.name);
        System.out.println(student2.name);
    }
}
```

---

## ❓ Perguntas Frequentes de Iniciantes (Dúvidas Cruciais)

### 1. Se eu adicionar `System.out.println(student1.age);`, o código vai dar erro?
**Resposta:**  
**NÃO!** Ele vai imprimir simplesmente:
```text
0
```
Porque o Java automaticamente coloca o valor zero para qualquer número inteiro em atributos de classe que não foram inicializados!

---

### 2. E se eu imprimir `System.out.println(student1.isEnrolled);`?
**Resposta:**  
Ele imprimirá:
```text
false
```
Porque o valor padrão para booleanos não inicializados em atributos de classe é sempre `false`.

---

### 3. Como o Construtor vai consertar isso na próxima tela?
**Resposta:**  
Nós vamos apagar o `="Spongebob"` fixo da linha 3 e vamos criar um método especial chamado `Student(...)` que receberá o nome, a idade e a nota de cada estudante individualmente na hora em que usarmos o `new`!
