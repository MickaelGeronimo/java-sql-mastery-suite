# Curso de Java — Capítulo 51: Polimorfismo em Tempo de Execução (`runtime polymorphism 🤷‍♂️`)
## Parte 3: A Fundação do Reino Animal — Criando a Classe Abstrata `Animal` e a Primeira Subclasse Concreta `Dog`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:14:59` até `08:15:47` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, como para quem nunca programou na vida) a construção da espinha dorsal do exemplo de Polimorfismo em Tempo de Execução: o professor Bro Code cria a classe pai abstrata [`Animal.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java) contendo o método sem corpo `abstract void speak();`, e em seguida cria a primeira classe filha concreta [`Dog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java), herdando de `Animal` (`extends Animal`) e sobrescrevendo o método com a impressão `"The dog goes *woof*"`.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (os popups de criação de classe, os letreiros com fontes estilizadas, os sublinhados vermelhos de digitação em andamento, os ícones de calha `(I)↑` e o código completo) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 5 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência de modelagem orientada a objetos (`08:14:59` a `08:15:47`), o professor Bro Code dá início à hierarquia que permitirá a escolha dinâmica pelo usuário:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:14:59`<br>`media_1791401021553_f62ef7d1.png` | Na tela do editor com [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) ao fundo, abre-se a janela popup **"New Java Class"**. No meio da tela, surge um letreiro gigante em fonte comic: **`Animal`**. | O professor clica com botão direito na pasta `src` $\rightarrow$ `New` $\rightarrow$ `Java Class` e digita o nome da classe pai: `Animal`. |
| **🖼️ Imagem 2** | `08:15:17`<br>`media_1791401041089_462c1e66.png` | Editor aberto em [`Animal.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java) (Linhas 1 a 4). Código: `public abstract class Animal { abstract void speak(); }`. | O professor adiciona o modificador `abstract` tanto na classe quanto no método `speak()`, definindo a regra de que todo animal do projeto deve obrigatoriamente saber falar/emitir som. |
| **🖼️ Imagem 3** | `08:15:25`<br>`media_1791401052968_34168e60.png` | Dentro de `Animal.java`, abre-se novamente o popup **"New Java Class"**. O professor digita `Dog`. Letreiro gigante no vídeo: **`Dog`**. | O professor inicia a criação da primeira espécie concreta de animal para o nosso zoológico polimórfico. |
| **🖼️ Imagem 4** | `08:15:39`<br>`media_1791401068598_b7fa68d6.png` | Editor aberto em [`Dog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java). Linha 1: `public class Dog extends Animal{`. Linha 3: `@Override`. Linha 4: `void speak()` com cursor piscando e sublinhado vermelho ondulado temporário sob os parênteses. | O professor conecta o cão ao pai via `extends Animal` e começa a digitar a assinatura do método sobrescrito antes de abrir as chaves `{`. |
| **🖼️ Imagem 5** | `08:15:47`<br>`media_1791401084495_720610cc.png` | [`Dog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java) 100% completo e sem nenhum erro! Linha 5: `System.out.println("The dog goes *woof*");`. Na calha ao lado da Linha 4, reluz o ícone verde **`(I)↑`**. | O professor finaliza a lógica do cachorro latindo (*woof*). Todos os erros vermelhos desaparecem. |

---

## 2. A Filosofia das Classes: Por Que `Animal` é Abstrato e `Dog` é Concreto?

Se você está começando em Java, imagine a seguinte situação:
Se alguém te disser: *"Desenhe um cachorro!"*, você pega o papel e desenha um animal com orelhas caídas, rabo abanando e focinho.
Mas se alguém te disser: *"Desenhe um Animal genérico, que não seja nem cachorro, nem gato, nem peixe, nem pássaro, mas apenas 'Animal'!"*... Você simplesmente não consegue!

Na biologia e no mundo real, a palavra **"Animal"** é um conceito abstrato, uma categoria mental. Quem existe de verdade no chão da sua sala é o **Cachorro** ou o **Gato**.

```
                           +-------------------------------------+
                           |         CLASSE ABSTRATA             |
                           |             Animal                  |
                           |   (Conceito genérico, não nasce)    |
                           +-------------------------------------+
                           |       abstract void speak();        |
                           | (Todo animal fala, mas COMO fala?   |
                           |  Não sabemos ainda, é abstrato!)    |
                           +-------------------------------------+
                                              ^
                                              | extends (Herança)
                           +-------------------------------------+
                           |         CLASSE CONCRETA             |
                           |              Dog                    |
                           |    (Existe de verdade na memória)   |
                           +-------------------------------------+
                           |      void speak() {                 |
                           |        "The dog goes *woof*"        |
                           |      }                              |
                           +-------------------------------------+
```

### O Que Significa `abstract void speak();`?
* Note que em [`Animal.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java), a linha termina com um ponto e vírgula `;`. Não tem chaves `{}`!
* Isso é um **decreto sagrado do Java**:  
  *"Eu, classe Animal, declaro que qualquer bicho que quiser ser meu filho é OBRIGADO a ter o método `speak()`. Mas eu não vou dizer o som que ele faz; cada filho que se vire para definir o seu próprio som!"*.

---

## 3. Dissecação Código por Código, Linha por Linha

Vamos analisar detalhadamente os dois arquivos criados pelo professor nestas imagens:

### 3.1 Arquivo: [`Animal.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java) (Classe Pai Abstrata)

```java
1 | public abstract class Animal {
2 | 
3 |     abstract void speak();
4 | }
```

* **Linha 1 (`public abstract class Animal {`):**
  * `public`: Permite que qualquer outra classe do projeto veja e herde desta classe.
  * `abstract`: **A palavra chave essencial!** Impede que alguém faça `new Animal()`. Um animal abstrato não pode ser instanciado diretamente na memória.
  * `class`: Declaração padrão de classe em Java.
  * `Animal`: Nome da superclasse (sempre com inicial maiúscula).
  * `{`: Abre o corpo da classe.
* **Linha 2 (` `):** Linha em branco para clareza visual.
* **Linha 3 (`abstract void speak();`):**
  * `abstract`: Informa que este método **não tem implementação aqui**. Ele é apenas uma promessa de método.
  * `void`: Não retorna nenhum dado (número, texto ou booleano).
  * `speak()`: Nome da ação ("falar" ou "emitir som").
  * `;`: Ponto e vírgula imediato. É proibido abrir chaves `{}` em método abstrato!
* **Linha 4 (`}`):** Fecha o bloco da classe `Animal`.

---

### 3.2 Arquivo: [`Dog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java) (Classe Filha Concreta)

```java
1 | public class Dog extends Animal{
2 | 
3 |     @Override
4 |     void speak(){
5 |         System.out.println("The dog goes *woof*");
6 |     }
7 | }
```

* **Linha 1 (`public class Dog extends Animal{`):**
  * `public class Dog`: Declara a classe pública do Cachorro.
  * `extends Animal`: Estabelece o vínculo de herança. O `Dog` agora é oficialmente um tipo de `Animal`.
  * `{`: Abre o bloco da classe.
* **Linha 2 (` `):** Linha em branco.
* **Linha 3 (`@Override`):**
  * Anotação que avisa ao compilador do Java: *"Atenção! Eu estou honrando a promessa feita pelo meu pai `Animal` e sobrescrevendo o método abstrato `speak()`!"*.
  * Se você errar o nome (por exemplo, escrever `speek()`), o `@Override` avisa com erro vermelho antes de rodar o código.
* **Linha 4 (`void speak(){`):**
  * Cumpre a assinatura exigida pelo pai.
  * *Ícone de calha à esquerda:* O círculo verde **`(I)↑`** (com setinha para cima), indicando que este método sobrescreve uma regra da superclasse.
  * `{`: Abre o corpo real da ação do cachorro.
* **Linha 5 (`System.out.println("The dog goes *woof*");`):**
  * O comando do Java que joga texto na tela.
  * Imprime em inglês: `"The dog goes *woof*"` (O cachorro faz *au au* / *woof*).
* **Linha 6 (`}`):** Fecha o método `speak()`.
* **Linha 7 (`}`):** Fecha a classe `Dog`.

---

## 4. O Mistério do Erro Vermelho na Imagem 4

Na Imagem 4 (`media_1791401068598_b7fa68d6.png`), o professor estava digitando a Linha 4 e o IntelliJ sublinhou os parênteses `()` com uma cobrinha vermelha.

### Por que aquilo aconteceu?
* No momento em que você digita `void speak()` em uma classe normal, o Java espera que você coloque chaves `{}` com o código dentro.
* Como o professor ainda não tinha digitado o `{`, a sintaxe estava temporariamente incompleta (*"Missing method body, or declare abstract"*).
* Assim que ele digitou as chaves `{` e o `System.out.println(...)` na Imagem 5, **o erro desapareceu instantaneamente**!
* **Dica para o iniciante:** Nunca se desespere quando o IntelliJ marcar algo de vermelho enquanto você ainda está no meio da digitação; espere terminar a instrução e fechar as chaves antes de se preocupar!

---

## 5. O Que Deve Sair no Console Neste Momento?

Se você apertar o botão de Play verde `▷` no [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):

```text

Process finished with exit code 0
```

### Por que o cachorro ainda não latiu no console?
* Porque as classes [`Animal.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Animal.java) e [`Dog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java) foram criadas na pasta `src`, mas **ainda não foram chamadas** dentro do método `main`!
* O arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) ainda contém apenas as linhas de comentário da aula anterior.
* O Java só executa o que está explicitamente ordenado dentro do `public static void main(String[] args)`.

---

## 6. O Próximo Passo do Quebra-Cabeça

O professor Bro Code agora precisa:
1. Criar a classe [`Cat.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Cat.java) (que fará `"The cat goes *meow*"`).
2. Ir até o [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) e criar a mecânica do `Scanner`, permitindo que o usuário digite se prefere o cão ou o felino.
3. Chamar `animal.speak();` e ver o Polimorfismo Dinâmico em ação ao vivo!
