# Curso de Java — Capítulo 45: Palavra-chave `super` (`super 🔝`)
## Parte 5: Testando `student.gpa`, Criando o Método `showGPA()` e a Harmonia Entre Pai e Filho

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:37:30` até `07:38:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) como o estudante acessa sua variável exclusiva `gpa`, como o professor cria o método `showGPA()` que combina o atributo herdado do pai (`this.first`) com o atributo próprio do filho (`this.gpa`), e a execução completa no console.  
> **Formato para Google Docs:** Todo o contexto visual da interface gráfica da IDE IntelliJ IDEA foi minuciosamente convertido em texto e tabelas para cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: O Filho que Conhece as Suas Próprias Coisas e as Coisas do Pai

Nas partes anteriores deste capítulo:
* Criamos a superclasse `Person.java` com `first`, `last`, construtor e `showName()`.
* Criamos a subclasse `Student.java` com `double gpa;` e usamos `super(first, last);` para acionar o construtor do pai sem duplicar código.

Nesta sequência de 5 imagens, o professor Bro Code avança na prática:

1. **Acesso Direto à Variável `student.gpa` (Imagem 1):**  
   Em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java), ele executa `System.out.println(student.gpa);`.  
   O console imprime `3.25`. Isso prova que o valor passado para o construtor do filho foi armazenado com sucesso na memória!
2. **Criação do Método `showGPA()` em `Student.java` (Imagem 2):**  
   Em vez de ficar dando `println` solto no `main`, o professor cria um método dentro de `Student`:
   ```java
   void showGPA(){
       System.out.println(this.first + "'s gpa is: " + this.gpa);
   }
   ```
   **O Ponto Alto da Aula:** Esse método é um exemplo perfeito de POO (Programação Orientada a Objetos), pois utiliza ao mesmo tempo:
   * `this.first` $\rightarrow$ Herdado da classe pai `Person`!
   * `this.gpa` $\rightarrow$ Declarado na classe filha `Student`!
3. **Substituição e Execução em `Main.java` (Imagens 3, 4 e 5):**  
   Ele substitui o comando antigo por `student.showGPA();`.  
   O console imprime com elegância:  
   `Harry's gpa is: 3.25`.

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem 1 (`media_1791355922299.png`): O Teste de `student.gpa`
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Abas Abertas no Topo:** `Main.java` (aba ativa), `Person.java` e `Student.java`.
* **Código em `Main.java`:**
  * Linha 10: `Student student = new Student("Harry", "Potter", 3.25);`
  * Linha 12: `System.out.println(student.gpa);`
* **Painel Inferior ("Run"):**
  * O terminal exibe a saída em verde brilhante:
    ```text
    3.25
    
    Process finished with exit code 0
    ```
* **Posição do Cursor e Mouse:**
  * O cursor de texto pisca no meio de `student.gpa`.
  * O ponteiro do mouse está sobre o botão verde Play no topo direito, mostrando o tooltip: `Run 'Main.java' Shift+F10`.

---

### Imagem 2 (`media_1791356258312.png`): Criando o Método `showGPA()` em `Student.java`
* **Aba Ativa:** `Student.java` (linha azul indicando foco).
* **Código Novo Adicionado:**
  ```java
  void showGPA(){
      System.out.println(this.first + "'s gpa is: " + this.gpa);
  }
  ```
* **Destaques Visuais da IDE:**
  * Na linha 11, na margem esquerda (gutter), há uma pequena bolinha amarela (sugestão de inspeção da IDE indicando que o prefixo `this.` é opcional).
  * As variáveis `this.first` e `this.gpa` possuem fundos sombreados sutis em cinza/rosa.
  * O cursor de texto pisca logo após a aspa simples de `'s gpa is: `.

---

### Imagem 3 (`media_1791356289988.png`): Selecionando a Linha Antiga em `Main.java`
* **Aba Ativa:** De volta a `Main.java`.
* **Código Visível:**
  * Linha 9: `Person person = new Person("Tom", "Riddle");`
  * Linha 10: `Student student = new Student("Harry", "Potter", 3.25);`
  * Linha 12: `System.out.println(student.gpa);`
* **Ação do Professor:**
  * O professor clica e arrasta o mouse sobre toda a expressão da linha 12: `System.out.println(student.gpa);`.
  * Ela fica totalmente coberta por uma barra azul de seleção de texto.
  * Ele faz isso para deletar a impressão manual e colocar a chamada do novo método no lugar.

---

### Imagem 4 (`media_1791356309404.png`): Digitando `student.showGPA();`
* **Código em `Main.java`:**
  * A linha 12 agora contém:
    ```java
    student.showGPA();
    ```
* **Destaques do Cursor:**
  * O cursor de texto pisca logo após o ponto e vírgula (`student.showGPA();|`).
  * O ponteiro do mouse repousa sobre o botão verde Play (`Current File ▶`) no topo direito, pronto para rodar.
* **Linha do Tempo do Vídeo:** `7:37:58 / 12:00:00` no capítulo `45.super 🔝`.

---

### Imagem 5 (`media_1791356320160.png`): O Resultado no Console (`Harry's gpa is: 3.25`)
* **Painel Inferior ("Run"):**
  * O terminal embutido executa o comando e imprime em verde brilhante:
    ```text
    Harry's gpa is: 3.25
    
    Process finished with exit code 0
    ```
* **Posição do Cursor:** O cursor em formato de "I" repousa no console logo abaixo da palavra `Harry`.
* **Conclusão Visual:** O método exclusivo do estudante puxou com perfeição o nome do pai e a nota do filho, gerando uma mensagem completa e profissional.

---

## 3. A Grande Harmonia: Como o Filho Usa Coisas do Pai e Suas Próprias Coisas

Observe com atenção a linha 11 de `Student.java`:

```java
System.out.println(this.first + "'s gpa is: " + this.gpa);
```

Vamos dissecar a origem de cada pedacinho dessa frase:

1. **`this.first`:**  
   * Essa variável foi declarada dentro de `Student.java`? **Não!**  
   * Ela foi declarada em `Person.java`.  
   * Mas como `Student extends Person`, o estudante **herda** esse atributo como se fosse dele próprio.  
   * Valor retornado: `"Harry"`.
2. **`"'s gpa is: "`:**  
   * É um texto literal fixo (String) usado para formar a frase em inglês (*"o gpa de Harry é: "*).
3. **`this.gpa`:**  
   * Essa variável foi declarada em `Person.java`? **Não!**  
   * Ela foi declarada especificamente dentro de `Student.java`.  
   * Valor retornado: `3.25`.

### O Poder da Concatenação:
Ao juntar tudo com o operador `+`:
$$\text{"Harry"} + \text{"'s gpa is: "} + 3.25 \Longrightarrow \text{"Harry's gpa is: 3.25"}$$

Isso ilustra a verdadeira beleza da Herança com a palavra-chave `super`: **a classe filha não precisa reinventar a roda**. Ela aproveita toda a bagagem que o pai preparou e apenas adiciona seus próprios temperos!

---

## 4. Análise Linha por Linha de Todo o Código

### Arquivo 1: `Student.java` (Imagem 2)

```java
1:  public class Student extends Person{
2:  
3:      double gpa;
4:  
5:      Student(String first, String last, double gpa){
6:          super(first, last);
7:          this.gpa = gpa;
8:      }
9:  
10:     void showGPA(){
11:         System.out.println(this.first + "'s gpa is: " + this.gpa);
12:     }
13: }
```

#### Explicação Detalhada:
* **Linha 1:** Declara a classe pública `Student` herdando de `Person`.
* **Linha 3 (`double gpa;`):** Atributo numérico de ponto flutuante exclusivo do estudante.
* **Linha 5 (`Student(...)`):** Construtor que recebe nome, sobrenome e nota.
* **Linha 6 (`super(first, last);`):** Envia o nome e o sobrenome para o construtor do pai `Person`.
* **Linha 7 (`this.gpa = gpa;`):** Armazena a nota na gaveta própria do estudante.
* **Linha 8 (`}`):** Encerra o construtor.
* **Linha 10 (`void showGPA(){`):**  
  * Cria o método `showGPA()`.  
  * Tipo `void` porque só imprime na tela e não devolve cálculo.
* **Linha 11 (`System.out.println(this.first + "'s gpa is: " + this.gpa);`):**  
  * Imprime no terminal a frase combinando o atributo herdado (`this.first`) com o atributo exclusivo (`this.gpa`).
* **Linhas 12 e 13 (`}` e `}`):** Encerra o método e a classe.

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
8:          Person person = new Person("Tom", "Riddle");
9:          Student student = new Student("Harry", "Potter", 3.25);
10: 
11:         student.showGPA();
12:     }
13: }
```

#### Explicação Detalhada:
* **Linhas 1 a 6:** Estrutura básica e comentários conceituais de `super`.
* **Linha 8 (`Person person = new Person("Tom", "Riddle");`):** Instancia o objeto pessoa Tom Riddle.
* **Linha 9 (`Student student = new Student("Harry", "Potter", 3.25);`):** Instancia o objeto estudante Harry Potter com nota `3.25`.
* **Linha 11 (`student.showGPA();`):**  
  * Chama o método `showGPA()` do objeto estudante.
  * **O que sai no console:**
    ```text
    Harry's gpa is: 3.25
    ```
* **Linhas 12 e 13 (`}` e `}`):** Encerra o método `main` e a classe `Main`.

---

## 5. Saída Fiel do Console nos Dois Testes

### Teste 1: Acesso direto à variável (Imagem 1)
```java
System.out.println(student.gpa);
```
**Saída no Console:**
```text
3.25
```

---

### Teste 2: Invocação do método `showGPA()` (Imagem 5)
```java
student.showGPA();
```
**Saída no Console:**
```text
Harry's gpa is: 3.25
```

---

## 6. Resumo para Fixação no Google Docs

1. **Atributos herdados são acessíveis diretamente no filho:** Dentro de `Student.java`, você pode escrever `this.first` ou simplesmente `first` para acessar o nome que veio de `Person`.
2. **Métodos específicos agregam valor:** Em vez de imprimir variáveis soltas no `Main.java`, criar métodos especializados dentro da subclasse (como `showGPA()`) deixa o código muito mais limpo, reutilizável e profissional.
3. **`super` garante que os dados existam:** Se não tivéssemos chamado `super(first, last)` no construtor do estudante, a variável `this.first` estaria vazia (`null`), e o método imprimiria `"null's gpa is: 3.25"`. O `super` é quem garante que o pai foi alimentado corretamente!
