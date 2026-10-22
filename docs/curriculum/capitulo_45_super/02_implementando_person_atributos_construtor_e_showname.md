# Curso de Java — Capítulo 45: Palavra-chave `super` (`super 🔝`)
## Parte 2: Implementando a Superclasse `Person`, Construtor com `this` e o Teste com "Tom Riddle"

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:32:00` até `07:33:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) como a superclasse `Person` foi estruturada com atributos, construtor parametrizado e método próprio, e como ela foi testada com sucesso em `Main.java`, imprimindo `"Tom Riddle"` no console.  
> **Formato para Google Docs:** Todo o contexto visual da interface gráfica da IDE IntelliJ IDEA foi minuciosamente convertido em texto e tabelas para cópia direta no Google Docs sem depender das imagens.

---

## 1. Visão Geral da Cena: Preparando a Fundação Antes do `super`

Antes de podermos usar a palavra-chave `super` em uma classe filha (como `Student` ou `Employee`), precisamos primeiro de uma **Superclasse** sólida e funcional que sirva de modelo básico para qualquer pessoa.

Nesta sequência de 5 imagens, o professor Bro Code realiza a construção e o teste dessa fundação:

1. **Construção de `Person.java` (Imagem 1):**  
   * Cria os atributos de texto: `first` (primeiro nome) e `last` (sobrenome).
   * Cria o construtor `Person(String first, String last)` usando `this.first = first;` e `this.last = last;`.
   * Cria o método `showName()` que imprime o nome completo formatado.
2. **O Retorno a `Main.java` e o Destaque Teórico (Imagem 2):**  
   * O professor volta para a aba `Main.java` e seleciona com o mouse a palavra **`superclass`** no comentário, reforçando que `Person` é a superclasse.
3. **Instanciação e Invocação (Imagens 3 e 4):**  
   * Ele cria um objeto da classe `Person` passando os nomes `"Tom"` e `"Riddle"` (referência clássica ao vilão Tom Riddle de Harry Potter).
   * Em seguida, chama `person.showName();`.
4. **Execução no Console (Imagem 5):**  
   * O console abre e exibe com sucesso: `Tom Riddle`.

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando no Google Docs sem as imagens originais, aqui está a descrição detalhada de tudo o que apareceu na tela:

### Imagem 1 (`media_1791355444719.png`): O Código Completo de `Person.java`
* **Ambiente Visual da IDE:** IntelliJ IDEA (Dark Mode).
* **Abas Abertas no Topo:** `Main.java` e `Person.java` (em foco com a linha azul embaixo).
* **Painel Esquerdo (Project Tree):** A pasta `src` exibe apenas dois arquivos: `Main` e `Person`.
* **Código Visível no Editor:**
  ```java
  public class Person {

      String first;
      String last;

      Person(String first, String last){
          this.first = first;
          this.last = last;
      }

      void showName(){
          System.out.println(this.first + " " + this.last);
      }
  }
  ```
* **Destaques e Cores da IDE:**
  * Na linha 3, o cursor de texto pisca logo após o ponto e vírgula de `String first;|`.
  * Na linha 4 e na linha 8, a palavra `last` possui um leve sombreado retangular rosa/cinza (o IntelliJ realçando onde a variável é declarada e onde é usada).
  * Na linha 12, na margem esquerda (gutter), há uma pequena bolinha amarela (sugestão de inspeção da IDE indicando que `this.` é opcional dentro do `showName`).

---

### Imagem 2 (`media_1791355456498.png`): Destacando a Palavra `superclass`
* **Aba Ativa:** O autor clica na aba `Main.java`.
* **Ação do Autor:**
  * Na linha 5 do comentário: `// super = Refers to the parent class (subclass <- superclass)`
  * O professor clica e arrasta o mouse sobre a palavra **`superclass)`**, deixando-a destacada com uma barra de seleção azul.
* **Intenção Pedagógica:** Ele enfatiza: *"Prestem atenção! Criamos a classe `Person`. Ela é a nossa `superclass` (classe pai)!"*.

---

### Imagem 3 (`media_1791355476412.png`): Criando o Objeto "Tom Riddle"
* **Código digitado na Linha 9 de `Main.java`:**
  ```java
  Person person = new Person("Tom", "Riddle");
  ```
* **Destaques Visuais:**
  * A variável `person` está com um fundo rosa suave e o cursor de texto pisca no meio dela (`pers|on`).
  * Os valores passados entre parênteses são `"Tom"` para o primeiro argumento e `"Riddle"` para o segundo argumento.

---

### Imagem 4 (`media_1791355500419.png`): Chamando o Método `showName()`
* **Código em `Main.java`:**
  * Linha 9: `Person person = new Person("Tom", "Riddle");`
  * Linha 11: `person.showName();`
* **Destaques Visuais:**
  * O cursor de texto pisca logo após o ponto e vírgula na linha 11 (`person.showName();|`).
  * O ponteiro do mouse em formato de "I" repousa à direita da linha 9.
  * O código está limpo, sem nenhum sublinhado de erro.

---

### Imagem 5 (`media_1791355509644.png`): O Terminal em Ação (`Tom Riddle`)
* **Janela Inferior ("Run"):**
  * O console do IntelliJ abre na aba `Main`.
  * Linha de comando da JVM: `C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...`
  * Texto impresso no terminal:
    ```text
    Tom Riddle
    
    Process finished with exit code 0
    ```
* **Cores:** A frase `"Tom Riddle"` aparece em verde brilhante no terminal.
* **Conclusão:** A classe `Person` funciona de ponta a ponta com 100% de sucesso!

---

## 3. Análise Linha por Linha de Todo o Código

### Arquivo 1: [`Person.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Person.java) (Imagem 1)

```java
1:  public class Person {
2:  
3:      String first;
4:      String last;
5:  
6:      Person(String first, String last){
7:          this.first = first;
8:          this.last = last;
9:      }
10: 
11:     void showName(){
12:         System.out.println(this.first + " " + this.last);
13:     }
14: }
```

#### Explicação Detalhada:

* **Linha 1 (`public class Person {`):**  
  Declara a classe pública [`Person`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Person.java). Qualquer arquivo do projeto poderá criar pessoas ou herdar dela.
* **Linhas 3 e 4 (`String first;` e `String last;`):**  
  * São os **Atributos de Instância** (as características que toda pessoa tem).
  * `first`: Guardará o primeiro nome em formato de texto (`String`).
  * `last`: Guardará o sobrenome em formato de texto (`String`).
* **Linha 6 (`Person(String first, String last){`):**  
  * É o **Construtor Parametrizado** da classe.
  * Ele exige dois textos na hora de criar a pessoa: o primeiro nome e o sobrenome.
* **Linha 7 (`this.first = first;`):**  
  * `this.first`: Refere-se à variável oficial do objeto na memória RAM.
  * `first`: É o valor que acabou de chegar nos parênteses do construtor.
  * O sinal de `=` transfere o valor de fora para dentro do objeto.
* **Linha 8 (`this.last = last;`):**  
  * Mesma coisa: transfere o sobrenome recebido para o atributo permanente `this.last`.
* **Linha 9 (`}`):**  
  Fecha o construtor da classe.
* **Linha 11 (`void showName(){`):**  
  * É um método (ação) que exibe o nome completo.
  * `void`: Significa que ele apenas executa a exibição e não retorna nenhum dado.
* **Linha 12 (`System.out.println(this.first + " " + this.last);`):**  
  * O operador `+` aqui faz **concatenação de strings** (junta os textos).
  * Ele pega o primeiro nome (`"Tom"`), junta com um espaço em branco (`" "`), junta com o sobrenome (`"Riddle"`), e imprime o resultado na tela.
* **Linhas 13 e 14 (`}` e `}`):**  
  Fechamento do método e da classe.

---

### Arquivo 2: [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Imagens 3, 4 e 5)

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:          // super = Refers to the parent class (subclass <- superclass)
5:          //         Used in constructors and method overriding
6:          //         Calls the parent constructor to initialize attributes
7:  
8:          Person person = new Person("Tom", "Riddle");
9:  
10:         person.showName();
11:     }
12: }
```

#### Explicação Detalhada:

* **Linhas 1 a 6:** Definição padrão de `Main` e os comentários do professor sobre `super`.
* **Linha 8 (`Person person = new Person("Tom", "Riddle");`):**  
  * `Person`: Tipo do objeto.
  * `person`: Variável de referência que segura a pessoa.
  * `new Person("Tom", "Riddle")`: Reserva espaço na memória e aciona o construtor de `Person.java`.
  * `"Tom"` entra no parâmetro `first`.
  * `"Riddle"` entra no parâmetro `last`.
* **Linha 10 (`person.showName();`):**  
  * Chama o método `showName()` no objeto `person`.
  * O método lê `this.first` (`"Tom"`) e `this.last` (`"Riddle"`), juntando-os com espaço.
* **Linhas 11 e 12 (`}` e `}`):**  
  Fechamento do bloco `main` e da classe `Main`.

---

## 4. O que Acontece nos Bastidores da Memória RAM?

Quando a linha `Person person = new Person("Tom", "Riddle");` executa:

```
         ┌───────────────────────────────┐
         │       Objeto Person           │
         ├───────────────────────────────┤
         │  first: "Tom"                 │
         │  last:  "Riddle"              │
         ├───────────────────────────────┤
         │  showName() -> "Tom Riddle"   │
         └───────────────────────────────┘
                        ▲
                        │ aponta para
             [ variável: person ]
```

E quando chamamos `person.showName();`, o método simplesmente busca essas duas gavetas internas e manda para o console!

---

## 5. Saída Fiel do Console nesta Etapa

Ao clicar no botão verde Play em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):

```text
Tom Riddle

Process finished with exit code 0
```

* **Linha 1:** `"Tom Riddle"` (impresso por `person.showName()`).
* **Linha 2:** Confirmação de término com código de saída 0 (sucesso absoluto).

---

## 6. O Próximo Passo: Por que Precisamos do `super`?

Agora que a superclasse `Person` está pronta e funcionando, imagine o seguinte desafio:
* Nas próximas telas, o professor vai criar uma classe filha chamada `Student` (Estudante).
* O estudante terá `first`, `last` e também uma nota `gpa`.
* Em vez de fazer o `Student` inicializar `first` e `last` do zero, nós vamos usar a palavra-chave mágica:
  ```java
  super(first, last);
  ```
* É exatamente isso que veremos nas próximas aulas!
