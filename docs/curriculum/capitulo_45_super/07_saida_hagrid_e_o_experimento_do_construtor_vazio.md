# Curso de Java — Capítulo 45: Palavra-chave `super` (`super 🔝`)
## Parte 7: A Saída de Hagrid no Console e o Grande Experimento do Construtor Vazio `Person(){}`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:40:00` até `07:42:00` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a saída da execução do funcionário Hagrid no console, e dissecar o experimento arquitetural feito pelo professor ao transformar o construtor de `Person` em um construtor vazio sem parâmetros (`Person(){}`), mostrando por que o compilador trava quando os parâmetros de `super(...)` não combinam com a superclasse.  
> **Formato para Google Docs:** Todo o contexto visual da interface da IDE IntelliJ IDEA (consoles, seleções de texto, botões e telas de erros da IDE) foi convertido em texto descritivo e tabelas didáticas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: O Fechamento de Hagrid e a Pergunta do "E se...?"

Neste conjunto de 3 capturas de tela, o professor Bro Code fecha o teste do funcionário e faz uma das demonstrações arquiteturais mais profundas de todo o curso de Java:

1. **A Execução de Hagrid no Console (Imagem 1):**  
   Vemos a tela do IntelliJ com o console aberto exibindo a saída em verde brilhante:  
   `Rubeus's salary is $50000`. Isso confirma que o funcionário herdou com sucesso o primeiro nome (`"Rubeus"`) de `Person` e exibiu o salário próprio (`$50000`).
2. **O Questionamento em `Person.java` (Imagem 2):**  
   O professor volta para [`Person.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Person.java) e seleciona com o mouse os parâmetros `String first, String last` dentro de `Person(...)`. Ele faz a provocação:  
   *"E se a classe pai `Person` não tivesse nenhum parâmetro? E se o construtor do pai fosse completamente vazio?"*.
3. **O Experimento do Construtor Vazio (Imagem 3):**  
   Ele apaga os parâmetros e o corpo do construtor, deixando apenas:
   ```java
   Person(){

   }
   ```
   **O Efeito Borboleta:** Imediatamente, o canto superior direito do editor acende um alerta vermelho com **5 erros de compilação** (`! 5`)!  
   Por quê? Porque agora o pai `Person` exige zero argumentos (`no arguments`), mas os filhos `Student` e `Employee` ainda estão mandando `super(first, last);` com dois textos!

---

## 2. Radiografia Visual Minuciosa das 3 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem 1 (`media_1791356505729.png`): O Console Confirmando o Salário de Hagrid
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Abas Abertas no Topo:** `Main.java` (em foco com a linha azul), `Person.java`, `Student.java` e `Employee.java`.
* **Código Visível no Editor:**
  * Linha 11: `Employee employee = new Employee("Rubeus", "Hagrid", 50000);`
  * Linha 13: `employee.showSalary();`
* **Painel Inferior ("Run"):**
  * O terminal embutido exibe em letras verdes brilhantes:
    ```text
    Rubeus's salary is $50000
    
    Process finished with exit code 0
    ```
* **Controles do Vídeo:** Marcador de tempo `7:40:57 / 12:00:00` no capítulo `45.super 🔝`.

---

### Imagem 2 (`media_1791356559987.png`): Destacando os Parâmetros em `Person.java`
* **Aba Ativa:** `Person.java`.
* **Código no Editor:**
  ```java
  Person(String first, String last){
      this.first = first;
      this.last = last;
  }
  ```
* **Ação do Autor:**
  * O professor clica e arrasta o mouse sobre as palavras `String first, String last` na linha 6.
  * Elas ficam cobertas por um retângulo azul de seleção.
* **Elemento Flutuante do YouTube:** No canto inferior esquerdo aparece a miniatura de busca rápida do player: *"Puxe para cima para uma busca precisa"*, no timestamp `7:41:26 / 12:00:00`.
* **Intenção do Autor:** O professor chama a atenção para os requisitos do construtor pai antes de apagá-los.

---

### Imagem 3 (`media_1791356583772.png`): O Construtor Vazio e a Explosão de Erros
* **Código Modificado em `Person.java`:**
  ```java
  public class Person {

      String first;
      String last;

      Person(){

      }

      void showName(){
          System.out.println(this.first + " " + this.last);
      }
  }
  ```
* **Destaques Visuais e Alertas da IDE:**
  * Linhas 6 a 8 agora contêm apenas `Person(){ }`.
  * O cursor de texto pisca vazio na linha 7.
  * No canto superior direito da IDE surge o aviso vermelho: **`! 5`** (indicando que 5 erros de compilação estouraram pelo projeto).
  * Na margem esquerda (gutter), o símbolo de classe `@` ganha uma setinha indicando alterações na estrutura da classe.

---

## 3. Por Que o Projeto Inteiro Quebrou? (A Lei da Correspondência de Construtores)

Para um iniciante em Java entender isso de forma cristalina, pense em uma **tomada e um plugue**:

* O construtor da classe pai é a **tomada** (define quantos pinos aceita).
* A chamada `super(...)` na classe filha é o **plugue** (deve ter exatamente a mesma quantidade de pinos da tomada).

### O Cenário Inicial (Tudo Funcionava Perfeitamente):
* **Pai ([`Person.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Person.java)):** Tomada de 2 pinos de texto:  
  `Person(String first, String last)`
* **Filho ([`Student.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Student.java)):** Plugue de 2 pinos de texto:  
  `super(first, last);` $\longrightarrow$ **Encaixe Perfeito!**
* **Filho ([`Employee.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Employee.java)):** Plugue de 2 pinos de texto:  
  `super(first, last);` $\longrightarrow$ **Encaixe Perfeito!**

### O que Aconteceu Quando o Professor Mudou para `Person(){}`:
* O professor trocou a tomada do pai por uma **tomada de 0 pinos (sem parâmetros)**:  
  `Person(){ }`
* Mas nos filhos `Student` e `Employee`, o código ainda tentava plugar com 2 pinos:  
  `super(first, last);`!
* E no [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java), o comando ainda tentava criar:  
  `new Person("Tom", "Riddle");` com 2 pinos!

### O Grito do Compilador Java:
Se você rodar o compilador no terminal, ele grita 3 vezes o mesmo erro:

```text
Student.java:6: error: constructor Person in class Person cannot be applied to given types;
        super(first, last);
        ^
  required: no arguments
  found:    String,String
  reason: actual and formal argument lists differ in length

Employee.java:6: error: constructor Person in class Person cannot be applied to given types;
        super(first, last);
        ^
  required: no arguments
  found:    String,String
  reason: actual and formal argument lists differ in length

Main.java:8: error: constructor Person in class Person cannot be applied to given types;
        Person person = new Person("Tom", "Riddle");
                        ^
  required: no arguments
  found:    String,String
  reason: actual and formal argument lists differ in length
```

* **`required: no arguments`**: *"O pai Person agora exige ZERO argumentos!"*
* **`found: String,String`**: *"Mas você me mandou dois textos!"*
* **`reason: actual and formal argument lists differ in length`**: *"O tamanho das listas de argumentos é diferente!"*

---

## 4. Análise Linha por Linha de Todo o Código

### Arquivo 1: [`Person.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Person.java) (Versão do Experimento — Imagem 3)

```java
1:  public class Person {
2:  
3:      String first;
4:      String last;
5:  
6:      Person(){
7:  
8:      }
9:  
10:     void showName(){
11:         System.out.println(this.first + " " + this.last);
12:     }
13: }
```

#### Explicação Detalhada:
* **Linha 1:** Declara a superclasse pública `Person`.
* **Linhas 3 e 4:** Declara as variáveis `String first;` e `String last;`.
* **Linha 6 (`Person(){`):**  
  * Construtor **sem parâmetros** (também chamado de *no-argument constructor* ou construtor padrão).
  * Ele não recebe nada de fora.
* **Linhas 7 e 8 (`}`):** Corpo do construtor vazio. Como nada é atribuído a `first` e `last`, essas variáveis ficam com o valor padrão do Java: `null` (nulo).
* **Linhas 10 a 12:** Método `showName()` que junta os nomes. Se chamado nessa versão, imprimiria `"null null"`.
* **Linha 13:** Fecha a classe `Person`.

---

### Arquivo 2: [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Imagem 1)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // super = Refers to the parent class (subclass <- superclass)
5:          //         Used in constructors and method overriding
6:          //         Calls the parent constructor to initialize attributes
7:  
8:          Person person = new Person("Tom", "Riddle");
9:          Student student = new Student("Harry", "Potter", 3.25);
10:         Employee employee = new Employee("Rubeus", "Hagrid", 50000);
11: 
12:         employee.showSalary();
13:     }
14: }
```

#### Explicação Detalhada:
* **Linhas 1 a 6:** Estrutura mestre de `Main` e os comentários conceituais de `super`.
* **Linhas 8 a 10:** Instanciação das três entidades do programa: a pessoa Tom Riddle, o estudante Harry Potter e o funcionário Rubeus Hagrid.
* **Linha 12 (`employee.showSalary();`):**  
  * Invoca o método do funcionário.
  * **O que sai no console:**
    ```text
    Rubeus's salary is $50000
    ```
* **Linhas 13 e 14:** Fechamento dos blocos com chaves `}`.

---

## 5. Saída Fiel do Console nesta Etapa

### Saída da Execução com o Código Anterior (Imagem 1):
```text
Rubeus's salary is $50000

Process finished with exit code 0
```

### Saída da Tentativa de Compilação com `Person(){}` (Imagem 3):
```text
Compilation failed: 3 errors, 2 warnings
- Student.java: constructor Person in class Person cannot be applied to given types (super(first, last))
- Employee.java: constructor Person in class Person cannot be applied to given types (super(first, last))
- Main.java: constructor Person in class Person cannot be applied to given types (new Person("Tom", "Riddle"))
```

---

## 6. A Regra de Ouro Definitiva do `super(...)`

Ao fazer esse experimento, o professor Bro Code ensina aos alunos a **regra universal de construtores em Java**:

$$\mathbf{super(arg_1, arg_2, \dots) \iff Superclasse(arg_1, arg_2, \dots)}$$

1. Se a superclasse tem um construtor que recebe `(String, String)`, o filho **deve** chamar `super(String, String)`.
2. Se a superclasse tem apenas um construtor vazio `()`, o filho **deve** chamar `super()`.
3. Se a superclasse tem múltiplos construtores sobrecarregados (*overloaded constructors*), o filho pode escolher qual versão do `super` quer chamar, desde que os tipos de dados combinem perfeitamente!
