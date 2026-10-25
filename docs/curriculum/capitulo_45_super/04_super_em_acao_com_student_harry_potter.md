# Curso de Java — Capítulo 45: Palavra-chave `super` (`super 🔝`)
## Parte 4: A Mágica de `super(first, last)`, Por que Não Usar `Person()` e o Teste com "Harry Potter"

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:35:00` até `07:37:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) como a chamada `super(first, last)` resolve o erro de compilação em `Student.java`, por que o Java proíbe chamar o construtor do pai pelo nome `Person(first, last)`, e como testamos o objeto Harry Potter em `Main.java`, imprimindo `"Harry Potter"` com sucesso no console.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (abas, seleções de mouse, sublinhados de erro e saídas de console) foi convertido em texto e tabelas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: O Nascimento Triunfal do `super` em Ação

Na Parte 3, tínhamos presenciado o Java travar com o erro:  
`There is no parameterless constructor available in 'Person'`.

Nesta sequência de 5 imagens, o professor Bro Code resolve o mistério e coloca a teoria em prática:

1. **A Consulta ao Pai (Imagem 1):**  
   O professor volta à classe `Person.java` e mostra o construtor `Person(String first, String last)`. Ele diz: *"Vejam, o pai já sabe receber dois textos e guardá-los. Só precisamos acionar esse construtor a partir do filho!"*.
2. **A Solução com `super(first, last)` (Imagem 2):**  
   Em `Student.java`, ele substitui as linhas duplicadas por:
   ```java
   super(first, last);
   ```
   **O erro vermelho desaparece na hora!** O código compila com perfeição.
3. **O Erro Didático Proposital: `Person(first, last)` (Imagem 3):**  
   Para tirar uma dúvida clássica de iniciantes (*"Se a classe pai se chama Person, por que não posso simplesmente chamar `Person(first, last)`?"*), o professor digita `Person(first, last);` e mostra que o Java **proíbe terminantemente** chamar um construtor de pai pelo nome da classe!
4. **Instanciando o Estudante em `Main.java` (Imagem 4):**  
   Ele desfaz o erro, volta para `Main.java` e cria o estudante Harry Potter:
   ```java
   Student student = new Student("Harry", "Potter", 3.25);
   ```
5. **Execução no Console (Imagem 5):**  
   Ele executa `student.showName();` e o console imprime em letras verdes brilhantes:  
   `Harry Potter`!

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem 1 (`media_1791355745575.png`): O Construtor do Pai em Destaque
* **Aba Ativa:** `Person.java` (no topo vemos as três abas abertas: `Main.java`, `Person.java` e `Student.java`).
* **Código Visível em `Person.java`:**
  ```java
  Person(String first, String last){
      this.first = first;
      this.last = last;
  }
  ```
* **Posição do Cursor:** O cursor de texto pisca na linha 7 logo após `this.first`.
* **Mensagem do Professor:** *"Olhem para esse construtor. Ele recebe `first` e `last`. Para usá-lo na classe filha, usamos a palavra `super`!"*.

---

### Imagem 2 (`media_1791355772734.png`): A Palavra `super` Iluminada e o Erro Sumindo
* **Aba Ativa:** `Student.java`.
* **Código em `Student.java`:**
  ```java
  public class Student extends Person{

      double gpa;

      Student(String first, String last, double gpa){
          super(first, last);
          this.gpa = gpa;
      }

  }
  ```
* **Destaques Visuais:**
  * Na linha 6, a palavra **`super`** está selecionada com uma barra de seleção azul.
  * O sublinhado vermelho ondulado que antes aterrorizava a linha do construtor **desapareceu por completo**! O código está 100% válido e limpo.

---

### Imagem 3 (`media_1791355796346.png`): O Erro de Tentar Chamar `Person(...)` Diretamente
* **Ação do Autor:**
  * O professor apaga a palavra `super` e digita no lugar o nome da classe pai:
    ```java
    Person(first, last);
    ```
* **O Alerta Vermelho Volta na Hora:**
  * A linha do construtor `Student(...)` volta a ficar sublinhada com uma cobrinha vermelha ondulada.
  * O IntelliJ destaca a palavra `Person` em cinza com cursor de erro.
* **A Lição Visual:** Em Java, você **nunca** pode chamar o construtor do pai escrevendo o nome da classe pai como se fosse uma função comum. O Java exige obrigatoriamente a palavra-chave reservada **`super`**.

---

### Imagem 4 (`media_1791355817594.png`): Criando o Harry Potter em `Main.java`
* **Aba Ativa:** `Main.java`.
* **Código Digitado:**
  ```java
  Person person = new Person("Tom", "Riddle");
  Student student = new Student("Harry", "Potter", 3.25);

  person.showName();
  ```
* **Destaques Visuais:**
  * Linha 10: Criação do objeto `student` com os argumentos `"Harry"`, `"Potter"` e a nota `3.25`.
  * O cursor de texto pisca logo após o ponto e vírgula da linha 10.
  * Mais uma referência à saga Harry Potter (depois do vilão Tom Riddle, agora temos o herói Harry Potter).

---

### Imagem 5 (`media_1791355892537.png`): O Console Imprime `Harry Potter`
* **Código em `Main.java`:**
  * O professor limpa o `person` e deixa o teste focado no estudante:
    ```java
    Student student = new Student("Harry", "Potter", 3.25);

    student.showName();
    ```
* **Painel Inferior ("Run"):**
  * O terminal embutido executa e imprime em verde brilhante:
    ```text
    Harry Potter

    Process finished with exit code 0
    ```
* **Controles do Vídeo:** Timestamp `7:37:04 / 12:00:00`, capítulo `45.super 🔝`.

---

## 3. O Que Aconteceu nos Bastidores? (Para um Iniciante Absoluto)

Entenda o passo a passo da mágica que aconteceu quando executamos `Student student = new Student("Harry", "Potter", 3.25);`:

```
1. Main.java chama: new Student("Harry", "Potter", 3.25)
   │
   ▼
2. Entra no Construtor de Student:
   Student(first="Harry", last="Potter", gpa=3.25)
   │
   ├──> Linha 6: super("Harry", "Potter");
   │    │
   │    └──> VIAJA PARA O CONSTRUTOR DE PERSON:
   │         Person(first="Harry", last="Potter")
   │         this.first = "Harry";  (Pai guarda o primeiro nome)
   │         this.last = "Potter";  (Pai guarda o sobrenome)
   │
   └──> Linha 7: this.gpa = 3.25;   (Filho guarda a nota)
```

E quando chamamos `student.showName();`:
1. O objeto `student` **não** tem o método `showName()` escrito dentro do arquivo `Student.java`.
2. Como `Student extends Person`, o Java busca o método herdado em `Person.java`.
3. O método executa: `System.out.println(this.first + " " + this.last);`.
4. Ele lê `"Harry"` e `"Potter"` que o construtor pai guardou perfeitamente na memória!
5. **Resultado no console:** `Harry Potter`!

---

## 4. Por Que Não Podemos Escrever `Person(first, last)`?

Muitos iniciantes perguntam:
> *"Se o construtor da classe pai se chama `Person`, por que não posso escrever `Person(first, last);` dentro do filho?"*

**Resposta:**
1. Em Java, nomes de construtores só podem ser chamados com a palavra **`new`** (quando você está criando um objeto novo do zero: `new Person(...)`).
2. Mas quando você já está **dentro de um objeto que está nascendo** (`Student`), você não está criando uma nova pessoa separada; você está apenas inicializando a parte "pessoa" daquele mesmo estudante!
3. Se você escrevesse `Person(first, last);`, o compilador Java pensaria que você está tentando chamar um método comum qualquer chamado `Person()`, e acusaria erro porque esse método não existe.
4. Por isso os criadores do Java inventaram a palavra-chave **`super(...)`**: ela é a única sintaxe permitida para acionar o construtor da superclasse a partir de uma subclasse!

---

## 5. Análise Linha por Linha de Todo o Código

### Arquivo 1: `Student.java` (Versão Final e Correta)

```java
1:  public class Student extends Person{
2:  
3:      double gpa;
4:  
5:      Student(String first, String last, double gpa){
6:          super(first, last);
7:          this.gpa = gpa;
8:      }
9:  }
```

* **Linha 1 (`public class Student extends Person{`):** Declara a subclasse `Student` que herda tudo da classe pai `Person`.
* **Linha 3 (`double gpa;`):** Declara a nota do estudante (atributo exclusivo de `Student`).
* **Linha 5 (`Student(String first, String last, double gpa){`):** Construtor que recebe os 3 dados necessários para configurar um estudante completo.
* **Linha 6 (`super(first, last);`):**  
  * **A linha mais importante da aula!**  
  * `super(...)`: Chama o construtor da superclasse imediata (`Person`).
  * Repassa `first` e `last` para o pai inicializá-los.
  * **Regra Sagrada do Java:** O comando `super(...)` deve ser, obrigatoriamente, a **primeiríssima linha de código** dentro do construtor da classe filha! Se você colocar qualquer comando antes dele, o Java dará erro de compilação.
* **Linha 7 (`this.gpa = gpa;`):** Guarda a nota na variável exclusiva `gpa` do próprio estudante.
* **Linhas 8 e 9 (`}` e `}`):** Encerra o construtor e a classe.

---

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) (Imagens 4 e 5)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // super = Refers to the parent class (subclass <- superclass)
5:          //         Used in constructors and method overriding
6:          //         Calls the parent constructor to initialize attributes
7:  
8:          Student student = new Student("Harry", "Potter", 3.25);
9:  
10:         student.showName();
11:     }
12: }
```

* **Linhas 1 a 6:** Definição padrão de `Main` e os comentários conceituais de `super`.
* **Linha 8 (`Student student = new Student("Harry", "Potter", 3.25);`):**  
  * Cria o objeto estudante `student`.
  * Envia `"Harry"` (primeiro nome), `"Potter"` (sobrenome) e `3.25` (nota).
* **Linha 10 (`student.showName();`):**  
  * Invoca o método herdado de `Person`.
  * **O que sai no console:**
    ```text
    Harry Potter
    ```
* **Linhas 11 e 12 (`}` e `}`):** Encerra o método `main` e a classe `Main`.

---

## 6. Saída Real e Fiel do Console nesta Etapa

Ao executar o [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) com `student.showName()`:

```text
Harry Potter

Process finished with exit code 0
```

* **Linha 1:** `"Harry Potter"` (impresso por `student.showName()`).
* **Linha 2:** Finalização limpa com código de saída 0.

---

## 7. Resumo para Fixação no Google Docs

1. **`super(...)` repassa a bola:** O filho recebe os dados e repassa o que for do pai usando `super(dadosDoPai)`.
2. **Primeira linha obrigatória:** A chamada `super(...)` tem que ser a **primeira linha** do construtor da subclasse. Nada pode vir antes dela.
3. **Não use o nome da classe:** Você **nunca** pode escrever `NomeDaClasse(args)` dentro do construtor filho. A palavra correta é sempre **`super(args)`**.
4. **Herança total de métodos:** Uma vez que o pai inicializou os dados via `super`, qualquer método herdado (como `showName()`) funciona como se tivesse sido escrito para o próprio filho.
