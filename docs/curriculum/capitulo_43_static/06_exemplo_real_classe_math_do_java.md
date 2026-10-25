# Capítulo 43: `static` 🤝 — Parte 6: Revelando o Código-Fonte do Java! A Classe `Math`, Construtor Privado e Constantes Estáticas

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 43 — `static 🤝`  
> **Tempo do Vídeo:** `07:20:25` até `07:21:25` / `12:00:00`  
> **Arquivos Analisados:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e o código-fonte oficial do JDK: `java.lang.Math.java`  
> **Objetivo Deste Documento:** Guia didático definitivo, pronto para copiar e colar no **Google Docs**, recriando detalhadamente as telas do IntelliJ IDEA, desmistificando o código-fonte interno do próprio Java e mostrando como os desenvolvedores da linguagem usam `static` na prática.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta sequência histórica da aula, o autor fecha o exemplo do clube dos amigos e faz uma demonstração ao vivo abrindo as entranhas do próprio Java para mostrar que você já usava `static` desde o primeiro dia de curso sem saber!

```
+-----------------------------------------------------------------------------------------------+
| [Project] > src > Main.java | Math.java [Reader Mode] | Friend.java                           |
+-----------------------------------------------------------------------------------------------+
| 126 |                                                                                         |
| 127 | public final class Math {                                                               |
| 128 |                                                                                         |
| 129 |     // Don't let anyone instantiate this class.                                         |
| 132 |     [ private ] Math() {}  <--- Construtor privado! Proíbe "new Math()"                 |
| 133 |                                                                                         |
| 138 |     public static final double E  = 2.718281828459045;                                  |
| 145 |     public static final double PI = 3.141592653589793;                                  |
| 150 |     public static final double TAU = 2.0 * PI;                                          |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 5 Imagens Explicadas)

#### 🖼️ Imagem 1 (`07:20:25`): O Fechamento do Exemplo dos Amigos
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que aparece na tela:**
  * No código, temos a criação dos 5 amigos (*Spongebob*, *Patrick*, *Squidward*, *Sandy* e *Gary*) e a chamada `Friend.showFriends();`.
  * No painel inferior **Run**, a saída em verde confirma o sucesso:
    ```text
    You have 5 total friends
    Process finished with exit code 0
    ```
* **Ação do autor:** O autor finaliza esse exemplo e se prepara para limpar o método `main`.

---

#### 🖼️ Imagem 2 (`07:20:45`): Limpando o `Main.java`
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que aparece na tela:**
  * O autor apaga todas as linhas de criação dos amigos.
  * O cursor fica piscando na linha 9, vazia.
  * Restam apenas os comentários conceituais no topo explicando o significado de `static`.

---

#### 🖼️ Imagem 3 (`07:21:11`): Escrevendo `Math.round(3.99);`
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que o autor digita:**
  ```java
  Math.round(3.99);
  ```
* **O que aparece na tela:**
  * O IntelliJ exibe um balão flutuante (popup de documentação rápida) ao passar o mouse ou focar na palavra `Math`:
    * Pacote: `java.lang`
    * Declaração: `public final class Math`
    * Descrição oficial da Oracle/Java: *"The class Math contains methods for performing basic numeric operations such as the elementary exponential, logarithm, square root, and trigonometric functions..."*
  * Repare que o método `round` aparece em **itálico**: no IntelliJ IDEA, **todos os métodos e variáveis estáticas aparecem formatados em itálico por padrão** para alertar visualmente o programador de que se trata de um membro `static`!

---

#### 🖼️ Imagem 4 (`07:21:16`): Entrando no Código Oficial do Java (`Math.java`)
* **Arquivo ativo:** `Math.java` (Aba aberta com o rótulo de *Reader Mode* / Modo Leitura).
* **Como o autor fez isso?**
  * Ele segurou a tecla `Ctrl` (ou `Cmd` no Mac) e **clicou em cima da palavra `Math`** no editor!
  * O IntelliJ navegou instantaneamente para dentro do código-fonte oficial do JDK (o código que os engenheiros criadores do Java escreveram!).
* **O que vemos na tela:**
  * Linha 127: `public final class Math {`
  * Comentário em inglês na linha 129: `Don't let anyone instantiate this class.` *(Não deixe ninguém instanciar esta classe)*.
  * Linha 132: `private Math() {}`
  * Linha 138: `public static final double E = 2.718281828459045;`
  * Linha 145: `public static final double PI = 3.141592653589793;`

---

#### 🖼️ Imagem 5 (`07:21:21`): O Construtor `private` Selecionado
* **Arquivo ativo:** `Math.java`.
* **Ação do autor:**
  * Bro Code dá um duplo clique na palavra **`private`** da linha 132 (`private Math() {}`), deixando-a selecionada com uma caixinha azul.
  * Ele usa esse momento para explicar aos alunos o motivo genial de o construtor da classe `Math` ser privado: **para impedir que qualquer pessoa no mundo faça `new Math()`**!

---

## 2. A Grande Revelação para o Iniciante Amador

### 🤯 "Por que eu NUNCA dei `new Math()` na minha vida?"
Se você já programou qualquer cálculo em Java, você sempre escreveu:
```java
double raiz = Math.sqrt(25);
long arredondado = Math.round(3.99);
double potencia = Math.pow(2, 3);
```
Você alguma vez precisou escrever isto?
```java
Math minhaCalculadora = new Math(); // ❌ NUNCA!
minhaCalculadora.sqrt(25);
```
**NUNCA!** E agora você finalmente entende o motivo:
> Todos os métodos da classe `Math` são **`static`**!  
> Eles pertencem à **classe**, e não a um objeto!

---

### 🔒 O Construtor Privado: O "Cadeado" dos Engenheiros do Java
Olhe a linha 132 do arquivo real `Math.java` do Java:
```java
// Don't let anyone instantiate this class.
private Math() {}
```

#### O que acontece se você tentar ser teimoso e escrever no seu código:
```java
Math m = new Math(); // ❌ ERRO VERMELHO DE COMPILAÇÃO!
```
O compilador do Java diz:
> `'Math()' has private access in 'java.lang.Math'`  
> *(O construtor Math() é privado! Você não tem permissão para usá-lo!)*

#### Por que os criadores do Java fizeram isso?
Porque criar um objeto da classe `Math` seria um **desperdício inútil de memória**!
* A raiz quadrada de 9 sempre será 3, não importa quem esteja calculando.
* O valor de Pi sempre será 3.14159..., não importa o computador ou o usuário.
* Uma classe matemática não tem estado ("não tem nome, nem cor, nem idade"). Ela é apenas uma **caixa de ferramentas utilitária**.
* Portanto, os engenheiros do Java colocaram o construtor como `private` para **blindar a classe**, forçando todo mundo a usar os métodos e variáveis diretamente via `static`:
  ```java
  Math.round(3.99);
  ```

---

### 🥧 O Segredo do `Math.PI` Desvendado
Você provavelmente já usou o Pi em fórmulas matemáticas:
```java
double area = Math.PI * raio * raio;
```
Agora olhe como o Java declara o `PI` na linha 145 do arquivo `Math.java`:
```java
public static final double PI = 3.141592653589793;
```
Vamos traduzir palavra por palavra:

| Palavra-Chave | O que significa na prática? |
| :--- | :--- |
| **`public`** | Qualquer pessoa em qualquer arquivo do projeto pode ver e usar. |
| **`static`** | **Pertence à classe `Math`!** Você acessa direto escrevendo `Math.PI`, sem precisar de `new`. |
| **`final`** | É uma constante! Ninguém pode alterar o valor de Pi no meio do programa (ex: `Math.PI = 5.0;` é proibido). |
| **`double`** | Tipo numérico de dupla precisão com casas decimais. |
| **`PI`** | Nome da constante (por convenção no Java, constantes são escritas em MAIÚSCULAS). |
| **`3.14159265...`** | O valor matemático do Pi. |

---

## 3. Dissecando os Códigos Linha por Linha

### Código 1: [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         // static = Makes a variable or method belong to the class
 5:         //          rather than to any specific object.
 6:         //          Commonly used for utility methods or shared resources.
 7: 
 8: 
 9:         Math.round(3.99);
10: 
11:     }
12: }
```

#### Explicação de [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

* **Linha 1:** Declara a classe pública `Main`.
* **Linha 2 (`public static void main(String[] args) {`):** O método principal de inicialização da JVM.
* **Linhas 4 a 6:** Os comentários conceituais deixados pelo Bro Code.
* **Linha 7 e 8:** Linhas em branco.
* **Linha 9 (`Math.round(3.99);`):**  
  * `Math`: Chama a classe utilitária de matemática do Java (pertencente ao pacote padrão `java.lang`).
  * `.round(...)`: Executa o método estático que arredonda um número decimal para o inteiro mais próximo ($3.99 \rightarrow 4$).  
  * Repare que o método é invocado diretamente no nome da classe com `M` maiúsculo!
* **Linha 11 e 12:** Fecham o método `main` e a classe `Main`.

---

### Código 2: Trecho Real do JDK (`java.lang.Math.java`)

```java
126: 
127: public final class Math {
128: 
129:     /** Don't let anyone instantiate this class. */
130: 
131: 
132:     private Math() {}
133: 
134: 
135:     public static final double E = 2.718281828459045;
136: 
137: 
138:     public static final double PI = 3.141592653589793;
139: 
140: }
```

#### Explicação do Trecho Oficial do JDK:

* **Linha 127 (`public final class Math {`):**  
  Declara a classe `Math`. A palavra `final` aqui significa que nenhuma outra classe pode herdar de `Math` (não aceita `extends`).
* **Linha 129 (`/** Don't let anyone instantiate this class. */`):**  
  Comentário oficial dos desenvolvedores da Sun Microsystems/Oracle: *"Não deixe ninguém instanciar (criar objetos de) esta classe"*.
* **Linha 132 (`private Math() {}`):**  
  O construtor da classe com visibilidade `private`. Por ser privado, nenhuma outra classe externa pode chamá-lo com `new`. A classe existe exclusivamente para fornecer métodos e constantes estáticas.
* **Linha 135 (`public static final double E = ...;`):**  
  A constante matemática $e$ (número de Euler / base dos logaritmos naturais), declarada como constante estática pública.
* **Linha 138 (`public static final double PI = ...;`):**  
  A constante matemática $\pi$, declarada como constante estática pública.

---

## 4. Onde o `static` é Usado no Mundo Real do Java?

Depois desta aula, você começará a enxergar `static` em todo lugar no Java:

| Exemplo no Java | O que faz? | Por que é `static`? |
| :--- | :--- | :--- |
| `Math.sqrt(49)` | Calcula a raiz quadrada (7.0) | É uma função matemática universal pura. |
| `Integer.parseInt("42")` | Converte o texto `"42"` para o número `42` | Função utilitária de conversão. |
| `Double.parseDouble("3.14")` | Converte texto para número decimal | Função utilitária de conversão. |
| `Arrays.sort(meuArray)` | Ordena um array em ordem crescente | Ação utilitária que opera sobre dados. |
| `System.out.println(...)` | Imprime na tela | `out` é uma variável estática de `System`. |
| `public static void main(...)` | Ponto de partida do programa | A JVM precisa rodar o programa sem precisar instanciar a classe `Main`. |

---

## 5. Resumo Geral do Capítulo 43: `static` 🤝

Parabéns! Você acabou de dominar um dos tópicos mais importantes de toda a Programação Orientada a Objetos:

1. **`static` significa "pertence à classe":**
   * Variáveis normais pertencem a cada objeto (ficam duplicadas no Heap).
   * Variáveis `static` pertencem à classe (existe **apenas 1 cópia** única no Metaspace, compartilhada por todos).
2. **Métodos `static`:**
   * São executados direto pelo nome da classe (`NomeDaClasse.metodo()`).
   * **NÃO** usam `this` (dá erro de compilação!).
   * Não precisam de nenhum objeto instanciado com `new`.
3. **Classes Utilitárias (como `Math`):**
   * Usam `static` para tudo.
   * Fecham o construtor com `private` para impedir que criem objetos inúteis na memória.
