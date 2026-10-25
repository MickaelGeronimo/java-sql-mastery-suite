# Curso de Java — Capítulo 45: Palavra-chave `super` (`super 🔝`)
## Parte 3: Criando a Classe `Student`, o Atributo `gpa` e o Grande Erro do Construtor Pai

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:33:30` até `07:35:00` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) por que o Java gera um erro vermelho furioso quando tentamos criar um construtor na classe filha `Student` sem chamar o construtor da classe pai `Person`.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (menus flutuantes, seleções com o mouse, caixas de tooltip de erro e mensagens do compilador) foi convertido em texto e tabelas para estudo e cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: O Choque de Realidade no Construtor da Subclasse

Até a Parte 2, tínhamos a nossa superclasse `Person.java` funcionando perfeitamente, com `first`, `last`, construtor parametrizado e o método `showName()`.

Neste conjunto de 5 imagens, o professor Bro Code nos leva ao momento pedagógico mais importante do capítulo:

1. **Criação da Classe Filha (Imagem 1):**  
   Abre a janela modal do IntelliJ e cria a classe `Student.java`.
2. **O Primeiro Aviso da IDE (Imagem 2):**  
   Ao digitar `public class Student extends Person{`, a linha inteira fica sublinhada em vermelho! O Java já começa a reclamar antes mesmo de escrevermos o corpo da classe.
3. **A Pausa Didática em `Person.java` (Imagem 3):**  
   O professor volta para `Person.java` e seleciona com o mouse as linhas `String first;` e `String last;` para lembrar a todos: *"Um estudante é uma pessoa, então ele já herda o primeiro e o último nome do pai! Mas o estudante precisa de algo novo: a nota (`gpa`)!"*.
4. **Adicionando `double gpa;` (Imagem 4):**  
   Em `Student.java`, ele adiciona o atributo exclusivo `double gpa;` e começa a digitar o construtor `Student(...)`.
5. **O Erro Famoso (Imagem 5):**  
   O professor tenta atribuir os valores manualmente dentro do construtor:
   ```java
   this.first = first;
   this.last = last;
   this.gpa = gpa;
   ```
   Imediatamente, o IntelliJ explode em uma caixa vermelha de erro:  
   **`There is no parameterless constructor available in 'Person'`** (*Não existe nenhum construtor sem parâmetros disponível em 'Person'*).

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de cada frame:

### Imagem 1 (`media_1791355609002.png`): Criando a Nova Classe `Student`
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Janela Modal Flutuante ("New Java Class"):**
  * O menu central suspenso está aberto no meio da tela.
  * O professor digitou `Student` no campo de texto.
  * A opção `Class` está selecionada em azul.
* **Ao Fundo:** Vemos o arquivo `Main.java` aberto, onde na aula anterior ele havia testado o `Person person = new Person("Tom", "Riddle");`.

---

### Imagem 2 (`media_1791355630351.png`): O Sublinhado Vermelho Imediato
* **Aba Aberta:** `Student.java` (em foco com a linha azul no topo).
* **Código Digitado:**
  ```java
  public class Student extends Person{

  }
  ```
* **O Alerta Vermelho:**
  * Toda a linha 1 (`public class Student extends Person{`) está com um **sublinhado ondulado vermelho** contínuo de ponta a ponta!
  * O cursor de texto pisca no meio da palavra `Student` com uma caixinha cinza.
* **Por que esse erro aconteceu logo de cara?**  
  Quando você não escreve nenhum construtor, o Java cria um construtor padrão invisível `Student() { super(); }`. Mas como a classe pai `Person` **não tem** construtor vazio, o Java trava imediatamente!

---

### Imagem 3 (`media_1791355640677.png`): Relembrando o que a Pessoa Já Tem
* **Aba Ativa:** O professor clica e volta para a aba `Person.java`.
* **Ação do Autor:**
  * O professor clica e arrasta o mouse sobre as linhas 3 e 4:
    ```java
    String first;
    String last;
    ```
  * As duas linhas ficam cobertas por uma faixa azul sólida de seleção de texto.
* **Intenção Pedagógica:** Ele explica aos alunos: *"Vejam só, o pai (`Person`) já possui `first` e `last`. Não faz sentido o filho ter que recriar ou reatribuir isso tudo na mão!"*.
* **Marcação de Tempo no Vídeo:** `7:34:29 / 12:00:00` no capítulo `45.super 🔝`.

---

### Imagem 4 (`media_1791355661945.png`): Criando o Atributo `gpa` e Começando o Construtor
* **Aba Ativa:** De volta a `Student.java`.
* **Código Digitado:**
  ```java
  public class Student extends Person{

      double gpa;

      Student(S){

      }
  }
  ```
* **Detalhes Visuais:**
  * Linha 3: O autor declara `double gpa;` (Grade Point Average — a média de notas dos estudantes nos EUA).
  * Linha 5: O autor começa a digitar o construtor: `Student(S){`. A letra `S` fica com destaque de cursor e sublinhado vermelho porque o parâmetro ainda está incompleto.

---

### Imagem 5 (`media_1791355715435.png`): O Flagrante do Erro Amador
* **Zoom da Câmera:** O autor dá um zoom na janela do editor.
* **Código em `Student.java`:**
  ```java
  Student(String first, String last, double gpa){
      this.first = first;
      this.last = last;
      this.gpa = gpa;
  }
  ```
* **O Erro Explícito:**
  * A primeira linha do construtor (`Student(String first, String last, double gpa){`) tem um sublinhado ondulado vermelho grosso.
* **A Janela Flutuante de Inspeção do IntelliJ:**
  * Uma grande caixa preta de aviso surge ao lado do código:
    * **Título:** `There is no parameterless constructor available in 'Person'`
    * **Sugestão de Correção Automática (Quick-Fix):** `Insert 'super();' Alt+Shift+Enter`
    * Logo abaixo aparece o manual da classe `java.lang.String`.
* **Onde está o cursor:** Na linha 4, logo após `this.gpa = gpa;|`.

---

## 3. A Grande Lição: Por que o Java Proíbe Esse Código? (Para um Iniciante Absoluto)

Imagine a seguinte história da vida real:

> Para um **Filho** nascer, os **Pais** precisam existir primeiro!  
> Você não pode construir o telhado de uma casa antes de levantar os alicerces.

Em Java, a regra é exatamente a mesma:
1. Sempre que você cria um objeto da classe filha (`Student`), a Máquina Virtual Java (JVM) é obrigada por lei a rodar o construtor da classe pai (`Person`) **antes** de qualquer outra coisa!
2. Quando você escreve o construtor do `Student` assim:
   ```java
   Student(String first, String last, double gpa){
       this.first = first;
       this.last = last;
       this.gpa = gpa;
   }
   ```
   Como você **NÃO chamou explicitamente o pai**, o Java tenta ser "bonzinho" e insere nos bastidores uma chamada invisível na primeiríssima linha:
   ```java
   Student(String first, String last, double gpa){
       super(); // <-- O JAVA TENTA CHAMAR ISSO EM SEGREDO!
       this.first = first;
       this.last = last;
       this.gpa = gpa;
   }
   ```
3. Só que tem um problema gigantesco: a classe `Person` **NÃO tem** nenhum construtor vazio `Person()`!  
   Em `Person.java`, o único construtor que existe exige dois textos:
   ```java
   Person(String first, String last) { ... }
   ```
4. O compilador Java olha para aquilo, tenta achar `Person()` sem parâmetros, não acha e entra em desespero:  
   *`error: constructor Person in class Person cannot be applied to given types; required: String,String; found: no arguments`*  
   E no IntelliJ:  
   *`There is no parameterless constructor available in 'Person'`*.

---

## 4. Análise Linha por Linha de `Student.java` (Com o Erro Proposital)

```java
1:  public class Student extends Person{
2:  
3:      double gpa;
4:  
5:      Student(String first, String last, double gpa){
6:          this.first = first;
7:          this.last = last;
8:          this.gpa = gpa;
9:      }
10: }
```

### Explicação Linha por Linha:

* **Linha 1 (`public class Student extends Person{`):**  
  * `public class Student`: Declara a subclasse pública `Student` (Estudante).
  * `extends Person`: Diz que todo estudante é, por definição, uma pessoa.
* **Linha 2 (` `):** Linha em branco para manter a legibilidade.
* **Linha 3 (`double gpa;`):**  
  * Declara a variável de ponto flutuante `gpa` (*Grade Point Average* — nota do estudante, ex: 3.5, 4.0).
  * Essa é a característica **única** do estudante. Uma pessoa qualquer que não estuda não tem nota, mas um estudante tem!
* **Linha 5 (`Student(String first, String last, double gpa){`):**  
  * Construtor que recebe 3 informações: primeiro nome, sobrenome e a nota.
  * **Atenção:** É aqui que o Java trava e acusa erro, porque o construtor do pai não foi chamado!
* **Linha 6 (`this.first = first;`):**  
  * O programador tenta atribuir `first` diretamente no objeto atual.
  * Além de causar o erro do construtor, isso é uma má prática de programação (duplicação de código), pois `Person` já sabe como inicializar `first`.
* **Linha 7 (`this.last = last;`):**  
  * Mesma coisa: duplicação desnecessária de lógica que pertence ao pai.
* **Linha 8 (`this.gpa = gpa;`):**  
  * Atribui a nota do estudante à variável `this.gpa`. Esta linha sim é responsabilidade legítima de `Student`.
* **Linhas 9 e 10 (`}` e `}`):**  
  Fechamento do construtor e da classe.

---

## 5. O que Sai no Console se Tentarmos Compilar?

Se você abrir o terminal e tentar compilar este arquivo agora com `javac Student.java`:

```text
Student.java:5: error: constructor Person in class Person cannot be applied to given types;
    Student(String first, String last, double gpa){
                                                  ^
  required: String,String
  found:    no arguments
  reason: actual and formal argument lists differ in length
1 error
```

* **Tradução:** O construtor `Person` precisa obrigatoriamente de `String, String`. Mas o Java encontrou `no arguments` (nenhum argumento) porque você não chamou o construtor do pai explicitamente!

---

## 6. O Spoiler da Solução (O que o Bro Code Fará a Seguir)

Como consertamos isso e fazemos o erro sumir como mágica?

Basta trocar as duas linhas duplicadas:
```java
// ❌ ANTES (ERRADO E DUPLICADO):
this.first = first;
this.last = last;
```

Pela chamada mágica do **`super`**:
```java
// ✅ DEPOIS (CORRETO, ELEGANTE E EXIGIDO PELO JAVA):
super(first, last);
```

Dessa forma:
1. `super(first, last)` repassa os nomes para o construtor de `Person.java`.
2. O pai `Person` faz o trabalho dele de inicializar o nome.
3. O filho `Student` só inicializa o que é dele: `this.gpa = gpa;`.
4. O erro desaparece instantaneamente!

---

## 7. Resumo para Fixação no Google Docs

1. **Construtores não são herdados automaticamente:** Uma classe filha não herda o construtor da classe pai; ela precisa criar seu próprio construtor e **chamar** o construtor do pai.
2. **A regra do `super()` invisível:** Se a classe pai tiver apenas construtores com argumentos, a classe filha é **obrigada** a chamar `super(...)` explicitamente na primeira linha do seu construtor.
3. **Economia de Código:** `super(first, last)` delega a inicialização para quem realmente é dono das variáveis, mantendo seu código 100% limpo e sem repetições.
