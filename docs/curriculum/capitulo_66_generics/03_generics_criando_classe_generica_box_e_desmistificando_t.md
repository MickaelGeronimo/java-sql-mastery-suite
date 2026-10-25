# Curso de Java Completo (Bro Code) — Capítulo 66: `generics 📦` (Parte 3)
## Criando a Classe Genérica `Box<T>`, Desmistificando o `<T>` com `<Thing>` e Preparando os Métodos

> **Contexto no Vídeo**: No tempo **`10:43:11 / 12:00:00`**, o instrutor **Bro Code** dá o passo mais emocionante do capítulo:  
> Ele para de apenas *usar* classes genéricas dos outros (como o `ArrayList`) e começa a **CONSTRUIR a sua própria classe genérica**: a classe **`Box`** (Caixa)!  
> Para garantir que até o aluno mais iniciante entenda a fundo, ele faz três coisas geniais em sequência:  
> 1. Escreve primeiro **`public class Box<Thing>`** para provar que a letra `T` **não é uma palavra mágica do Java**, e que você poderia chamar o molde de qualquer coisa!  
> 2. Mostra o que aconteceria se tentássemos fixar um tipo digitando `Integ item;` (o pesadelo de ter que criar dezenas de classes repetidas).  
> 3. Padroniza a classe com a convenção mundial **`Box<T>`**, define o atributo **`T item;`** e começa a criar o método modificador **`setItem()`**!

---

## 📸 Reconstituição Visual Completa das 3 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 3 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Desmistificando o <T> com <Thing> (Tempo do Vídeo: 10:43:11)
====================================================================================================
Sobreposição na Esquerda do Vídeo:
  [ < - 5 ]  (Ícone translúcido de retroceder 5 segundos)

Abas Abertas no Topo do Editor:
  [ ☕ Main.java ] [ ☕ Box.java ✕ (Ativa) ]

Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Box    <-- [Arquivo em edição]
      ├── ☕ Main
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (Box.java):
 1: public class Box<Thing> {
 2: 
 3: 💡      Thing [item];  <-- [A palavra 'item' está selecionada em azul]
 4: }
 5: 

Barra de Status Inferior:
  MyFirstProject > src > Box > item
  3:15 (4 chars)  CRLF  UTF-8  4 spaces

Análise Visual da Imagem 1:
- Repare no nome do tipo genérico na linha 1: `<Thing>` (Coisa/Objeto em inglês).
- Na linha 3, o atributo foi declarado como `Thing item;`.
- O autor selecionou a palavra `item` e explicou:
  "Vejam só! O Java aceita perfeitamente `<Thing>`. A letra `T` não é uma palavra-chave reservada do Java! Qualquer nome de identificador válido funciona como parâmetro de tipo!".
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: O Contraste — Por Que Não Hardcodar com 'Integer'? (Tempo: 10:43:20)
====================================================================================================
Editor de Código (Box.java):
 1: public class Box<T> {  <-- [Substituiu <Thing> pela convenção padrão <T>]
 2: 
 3:         Integ| item;  <-- [O autor digita 'Integ' com o cursor piscando após o 'g']
 4: }
 5: 

Barra de Status Inferior:
  3:9  CRLF  UTF-8  4 spaces

Análise Visual da Imagem 2:
- O autor substitui `<Thing>` pela convenção profissional `<T>`.
- Na linha 3, ele começa a digitar `Integ` para provocar a reflexão:
  "Se nós colocássemos `Integer item;`, nossa caixa só conseguiria guardar números inteiros! Se amanhã precisássemos de uma caixa para guardar textos, teríamos que criar `StringBox.java`. Se precisássemos guardar sapatos, `ShoeBox.java`! Isso geraria uma montanha de código duplicado!".
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: O Atributo Genérico 'T item;' e o Início do Método 'setItem()' (Tempo: 10:43:30)
====================================================================================================
Editor de Código (Box.java):
 1: public class Box<T> {
 2: 
 3:     T item;  <-- [O ATRIBUTO GENÉRICO DEFINITIVO!]
 4: 
 5:     public void setItem(|){  <-- [Cursor piscando dentro dos parênteses do método]
 6: 
 7:     }
 8: }
 9: 

Barra de Status Inferior:
  MyFirstProject > src > Box > setItem
  5:25  CRLF  UTF-8  4 spaces

Análise Visual da Imagem 3:
- Linha 3 agora está perfeitamente definida como `T item;`.
- O 'T' está colorido em ciano/verde pelo IntelliJ, reconhecendo que ele faz referência direta ao `<T>` declarado na linha 1.
- Linha 5: O autor abre a assinatura do método `public void setItem(){`.
- O cursor de digitação está exatamente no meio dos parênteses `setItem(|){`, preparando-se para receber o parâmetro `T item`.
====================================================================================================
```

---

## 🧠 Desmistificando o `<T>`: Por Que o Autor Escreveu `<Thing>`?

Muitos livros e cursos tradicionais de Java simplesmente jogam a letra `T` na cara do aluno sem explicar de onde ela veio, fazendo o iniciante achar que `T` é uma instrução rígida da linguagem como `while` ou `if`.

### 1. Um Parâmetro de Tipo é Como uma Variável!
Pense em uma função matemática comum ou em um método Java:
```java
void somar(int x, int y) { ... }
```
- Você é obrigado a chamar as variáveis de `x` e `y`? **Claro que não!** Você poderia chamá-las de `numero1` e `numero2`, `a` e `b`, ou `batata` e `cenoura`. O compilador só precisa de um **nome de identificador válido**.
- Com os Generics é a mesma coisa:
  ```java
  public class Box<Thing>   // Funciona 100%!
  public class Box<Elemento>// Funciona 100%!
  public class Box<Item>    // Funciona 100%!
  public class Box<T>       // O padrão mundial!
  ```

---

### 2. Por Que Usamos Letras Únicas Maiúsculas (`T`) por Convenção?
Se o Java aceita palavras inteiras como `<Thing>`, por que todo mundo usa apenas `<T>`?
- **Para Evitar Confusão Visual com Classes Reais**:
  Se você escrever `Box<Thing>`, quem ler o código pode achar que existe um arquivo chamado `Thing.java` no projeto!
- **A Regra de Ouro da Oracle**:
  - Nomes de classes e interfaces usam palavras completas em PascalCase: `String`, `Scanner`, `ArrayList`.
  - Parâmetros de tipo genéricos usam **uma única letra maiúscula**: `<T>`, `<E>`, `<K>`, `<V>`.
  - Assim, qualquer programador no planeta olha para `<T>` e sabe na hora: *"Isso aqui não é uma classe de verdade, é um molde genérico!"*.

---

## 🔬 A Anatomia da Classe Genérica `Box<T>`

Vamos dissecar o código que o autor está construindo:

```java
public class Box<T> {

    T item;

    public void setItem(T item){
        this.item = item;
    }
}
```

### 1. O Cabeçalho: `public class Box<T>`
- Os colchetes angulares `<T>` colocados logo após o nome da classe transformam a classe comum em uma **Classe Genérica**.
- Ela diz ao compilador: *"Atenção! Esta classe aceitará um tipo `T` que será escolhido no momento em que alguém der `new Box<String>()` ou `new Box<Integer>()`"*.

### 2. O Atributo Mutante: `T item;`
- O que é o atributo `item`? É o objeto guardado dentro da caixa!
- Qual é o tipo de `item`? É **`T`**!
- Isso significa que o tipo desse atributo se adapta automaticamente:
  - Se você instanciar `Box<String>`, o atributo `item` se torna uma `String`.
  - Se você instanciar `Box<Integer>`, o atributo `item` se torna um `Integer`.
  - Se você instanciar `Box<Double>`, o atributo `item` se torna um `Double`.

```
                  ┌──────────────────────────────┐
                  │       public class Box<T>    │
                  │             T item;          │
                  └──────────────┬───────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         ▼                       ▼                       ▼
  Quando T = String       Quando T = Integer      Quando T = Boolean
┌──────────────────┐    ┌──────────────────┐    ┌──────────────────┐
│ Box<String>      │    │ Box<Integer>     │    │ Box<Boolean>     │
│ String item;     │    │ Integer item;    │    │ Boolean item;    │
└──────────────────┘    └──────────────────┘    └──────────────────┘
```

---

## 🔍 Explicação Linha por Linha do Arquivo [`Box.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Box.java)

Abaixo está o código em desenvolvimento no final da Imagem 3:

```java
1: public class Box<T> {
2: 
3:     T item;
4: 
5:     public void setItem(){
6: 
7:     }
8: }
```

- **Linha 1 (`public class Box<T> {`)**:
  - `public`: Modificador de acesso que permite que qualquer classe use a `Box`.
  - `class`: Declara que estamos criando um molde de objeto.
  - `Box`: O nome da classe.
  - `<T>`: **O Parâmetro de Tipo Genérico** (*Type Parameter*). O marcador de posição que representa o tipo de item que a caixa guardará.
  - `{`: Abre o bloco da classe.
- **Linha 2**: Linha em branco para clareza visual.
- **Linha 3 (`T item;`)**:
  - Declaração do atributo de instância.
  - O tipo da variável é `T` (o mesmo `T` do cabeçalho da classe).
  - O nome da variável é `item`.
  - `;`: Finaliza a declaração.
- **Linha 4**: Linha em branco.
- **Linha 5 (`public void setItem(){`)**:
  - Início do método modificador (*setter*).
  - `public`: Qualquer parte do programa pode colocar um item na caixa.
  - `void`: O ato de guardar algo não precisa retornar nenhuma informação de volta.
  - `setItem`: Nome do método no padrão camelCase.
  - `()`: Na próxima cena, o autor colocará `(T item)` dentro destes parênteses para receber o item a ser guardado!
  - `{`: Abre o corpo do método.
- **Linha 7 (`}`)**: Fecha o corpo do método `setItem()`.
- **Linha 8 (`}`)**: Fecha o corpo da classe `Box`.

---

## 🖥️ Previsão do Console e Próximos Passos

Nesta etapa (imagens 1 a 3):
- O foco do autor está 100% no arquivo [`Box.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Box.java), estruturando a classe genérica.
- Como o método `main` em `Main.java` ainda não instancia a classe `Box`, nenhuma saída nova é gerada no console.

> **O Que Esperar para as Próximas Cenas**:  
> 1. O autor completará o método `setItem(T item)` com a atribuição `this.item = item;`.  
> 2. Criará o método de leitura (*getter*): `public T getItem() { return this.item; }`.  
> 3. Voltará para `Main.java` e instanciará caixas de diferentes tipos, como `Box<String> box1 = new Box<>();` e `Box<Integer> box2 = new Box<>();`, provando a flexibilidade total dos Generics!
