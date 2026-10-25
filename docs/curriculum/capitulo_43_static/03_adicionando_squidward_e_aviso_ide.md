# Capítulo 43: `static` 🤝 — Parte 3: Adicionando o 3º Amigo (*Squidward*), Saída 3, 3, 3 e o Alerta Amarelo da IDE

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 43 — `static 🤝`  
> **Tempo do Vídeo:** `07:17:33` até `07:17:50` / `12:00:00`  
> **Arquivos do Projeto:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e [Friend.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java)  
> **Objetivo Deste Documento:** Fornecer um guia didático visual e conceitual completo, pronto para ser colado no **Google Docs**, recriando cada detalhe da tela do IntelliJ IDEA, dissecando o código linha por linha para iniciantes, desmistificando o contador estático e explicando o aviso amarelo da IDE.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta sequência de 4 capturas de tela, o instrutor Bro Code expande o teste criando um terceiro amigo (*Squidward* / Lula Molusco) para consolidar como a variável `static` se comporta quando novos objetos são criados.

```
+-----------------------------------------------------------------------------------------------+
| [Project] MyFirstProject > src > Friend.java | Main.java [x]                       [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
|  8 |                                                                                          |
|  9 |         Friend friend1 = new Friend("Spongebob");                                        |
| 10 |         Friend friend2 = new Friend("Patrick");                                          |
| 11 |         Friend friend3 = new Friend("Squidward");                                        |
| 12 |                                                                                          |
| 13 |         System.out.println(friend1.numOfFriends); ~~~ [Aviso Amarelo da IDE]             |
| 14 |         System.out.println(friend2.numOfFriends); ~~~ [Aviso Amarelo da IDE]             |
| 15 |  💡     System.out.println(friend3.numOfFriends); ~~~ [Aviso Amarelo da IDE]             |
| 16 |     }                                                                                    |
| 17 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Terminal Run / Console]                                                                      |
| 3                                                                                             |
| 3                                                                                             |
| 3                                                                                             |
|                                                                                               |
| Process finished with exit code 0                                                             |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 4 Imagens Explicadas)

#### 🖼️ Imagem 1 (`07:17:33`): Fechando o Console Anterior
* **O que aparece na tela:**
  * O editor está aberto no arquivo [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
  * O painel inferior **Run** ainda exibe a execução anterior, que mostrava:
    ```text
    2
    2
    Process finished with exit code 0
    ```
  * O cursor do mouse está posicionado em cima do botão de fechar (`x`) da aba `Main` dentro da janela inferior `Run`.
  * No código, já se vê linhas sublinhadas com uma suave **ondinha amarela serrilhada** embaixo de `friend1.numOfFriends` e `friend2.numOfFriends`.
* **Ação do autor:** Bro Code fecha a aba do console para liberar espaço vertical no editor e poder digitar o novo código com folga.

---

#### 🖼️ Imagem 2 (`07:17:38`): Duplicando a Linha e o Erro Vermelho Temporário
* **O que aparece na tela:**
  * Bro Code usou um atalho de duplicação de linha (como `Ctrl + D` no IntelliJ) na linha do Patrick.
  * Por uma fração de segundo, o código ficou:
    ```java
    Friend friend2 = new Friend("Patrick");
    Friend friend2 = new Friend("Patrick");
    ```
  * O IntelliJ imediatamente colocou uma **ondinha vermelha** embaixo do segundo `friend2`.
* **Por que ficou vermelho?**
  * Em Java, você **nunca** pode declarar duas variáveis com o mesmo nome dentro do mesmo bloco `{ ... }`. O Java acusaria: *Variable 'friend2' is already defined in the scope*.
  * O autor selecionou o número `2` (que ficou com uma caixinha azul de seleção) para alterá-lo para `3`.

---

#### 🖼️ Imagem 3 (`07:17:45`): Adicionando Squidward e Clicando em Executar
* **O que aparece na tela:**
  * A linha 11 foi ajustada com sucesso:
    ```java
    Friend friend3 = new Friend("Squidward");
    ```
  * Uma terceira linha de impressão foi adicionada na linha 15:
    ```java
    System.out.println(friend3.numOfFriends);
    ```
  * As linhas 13, 14 e 15 agora exibem as **ondinhas amarelas** embaixo de `friend1.numOfFriends`, `friend2.numOfFriends` e `friend3.numOfFriends`.
  * O cursor do mouse (a setinha branca) está posicionado no canto superior direito, clicando no botão verde triangular **Run** (`Shift + F10`).
  * Na barra de status inferior direita do IntelliJ, aparece a mensagem: `Writing classes... [MyFirstProject]`, indicando que o compilador do Java está gerando os arquivos `.class` atualizados antes de rodar.

---

#### 🖼️ Imagem 4 (`07:17:50`): A Saída Tripla `3, 3, 3` e a Lâmpada de Aviso
* **O que aparece na tela:**
  * O painel **Run** abre na parte inferior.
  * No console aparecem três linhas com o número `3`:
    ```text
    3
    3
    3
    ```
  * Bro Code **arrasta o mouse e seleciona as três linhas do console**, deixando-as com um fundo azul brilhante para destacar esse resultado aos alunos.
  * No editor de código, ao lado do número da linha `15`, aparece um ícone de uma **lâmpada amarela (💡)**.
  * O texto `friend3.numOfFriends` está focado por uma moldura cinza com ondinha amarela.

---

## 2. A Analogia Definitiva: O Painel do Estádio com 3 Amigos

Imagine que você está em uma quadra de basquete com uma catraca na entrada:

```
                      +-----------------------------+
                      |   PLACAR GERAL NA PAREDE    |
                      |   (static int numOfFriends) |
                      |             [ 3 ]           |
                      +-----------------------------+
                                     ▲
               +---------------------+---------------------+
               │                     │                     │
        Olha pro placar       Olha pro placar       Olha pro placar
               │                     │                     │
    +--------------------+  +--------------------+  +--------------------+
    | Objeto: friend1    |  | Objeto: friend2    |  | Objeto: friend3    |
    | Nome: "Spongebob"  |  | Nome: "Patrick"    |  | Nome: "Squidward"  |
    +--------------------+  +--------------------+  +--------------------+
```

1. **Spongebob entra na quadra:**
   * Ele gira a catraca (`numOfFriends++`).
   * O placar do ginásio marca: **`1`**.
2. **Patrick entra na quadra:**
   * Ele gira a mesma catraca (`numOfFriends++`).
   * O placar do ginásio marca: **`2`**.
3. **Squidward entra na quadra:**
   * Ele gira a mesma catraca (`numOfFriends++`).
   * O placar do ginásio marca: **`3`**.

Agora, você vai até o **Spongebob** e pergunta: *"Quantos amigos têm aqui?"*  
Ele não tem um papel no bolso dele. Ele olha para o **placar da parede** e diz: **`3`**!

Você vai até o **Patrick** e pergunta a mesma coisa:  
Ele olha para o **mesmo placar da parede** e diz: **`3`**!

Você vai até o **Squidward** e pergunta a mesma coisa:  
Ele olha para o **mesmo placar da parede** e diz: **`3`**!

> **Moral da história:**  
> Não existem 3 contadores. Existe **apenas 1 contador único**, compartilhado por todos os objetos da classe `Friend`. Cada vez que qualquer objeto nasce, ele soma +1 no mesmo placar central.

---

## 3. O Alerta do IntelliJ: Por que as Ondinhas Amarelas e a Lâmpada (💡)?

Repare com atenção na imagem 4:
```java
System.out.println(friend1.numOfFriends); // <--- Ondinha amarela
System.out.println(friend2.numOfFriends); // <--- Ondinha amarela
System.out.println(friend3.numOfFriends); // <--- Ondinha amarela e Lâmpada 💡
```

### O que o IntelliJ está dizendo?
Se você passar o mouse em cima da ondinha amarela ou clicar na lâmpada 💡, o IntelliJ exibirá esta mensagem:
> **"Static member 'Friend.numOfFriends' accessed via instance reference"**  
> *(Membro estático 'Friend.numOfFriends' acessado através de uma referência de instância)*

### Por que o Java reclama disso se o código funcionou?
1. **É uma Ilusão de Óptica:**
   * Quando você escreve `friend1.name`, `name` é exclusivo do Spongebob.
   * Quando você escreve `friend1.numOfFriends`, parece que `numOfFriends` pertence só ao Spongebob! Um programador novato que ler isso vai pensar: *"Ah, essa variável pertence ao objeto friend1"*.
   * Mas isso é **mentira**! A variável pertence à **classe** `Friend`, e não ao objeto `friend1`.
2. **Por que o Java compila?**
   * O Java permite para manter compatibilidade histórica, mas o compilador por baixo dos panos simplesmente ignora o `friend1` e busca a variável direto na classe `Friend`.
3. **Qual é o jeito correto e profissional?**
   * Em vez de acessar pelo nome do objeto (`friend1.numOfFriends`), você deve acessar diretamente pelo **nome da classe**:
   ```java
   Friend.numOfFriends
   ```
   *(Bro Code mostra exatamente essa correção logo no próximo momento da aula!)*

---

## 4. Código Completo e Explicado Linha por Linha

### Arquivo 1: [Friend.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java)

```java
 1: public class Friend {
 2: 
 3:     static int numOfFriends;
 4:     String name;
 5: 
 6:     Friend(String name){
 7:         this.name = name;
 8:         numOfFriends++;
 9:     }
10: 
11: }
```

#### Explicação Linha por Linha de [Friend.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java):

* **Linha 1 (`public class Friend {`):**  
  Declara a classe pública `Friend` (o modelo/fôrma para criar amigos).
* **Linha 2 (` `):**  
  Linha em branco para organização visual.
* **Linha 3 (`static int numOfFriends;`):**  
  **O Coração do Assunto!** A palavra `static` faz com que essa variável inteira **NÃO** pertença a nenhuma pessoa específica criada. Ela pertence à classe `Friend`. Ela é inicializada automaticamente com o valor padrão `0` e guardada na memória compartilhada de classes.
* **Linha 4 (`String name;`):**  
  Uma variável comum (sem `static`). Isso significa que cada objeto terá a sua própria cópia independente da variável `name`. Spongebob terá o seu nome, Patrick terá o seu, e Squidward terá o seu.
* **Linha 5 (` `):**  
  Linha em branco.
* **Linha 6 (`Friend(String name){`):**  
  O construtor da classe. É o bloco especial de código executado **toda vez** que a palavra-chave `new Friend(...)` for chamada no programa.
* **Linha 7 (`this.name = name;`):**  
  Pega o texto recebido como parâmetro (ex: `"Squidward"`) e guarda dentro do atributo `name` do objeto específico que acabou de ser criado (`this`).
* **Linha 8 (`numOfFriends++;`):**  
  Incrementa em `+1` a variável estática compartilhada. Como ela é estática, não usamos `this.numOfFriends`. Chamamos direto `numOfFriends++`. Toda vez que um novo amigo nasce, o placar geral sobe 1 número.
* **Linha 9 (`}`):**  
  Fecha o corpo do construtor.
* **Linha 10 (` `):**  
  Linha em branco.
* **Linha 11 (`}`):**  
  Fecha a classe `Friend`.

---

### Arquivo 2: [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         // static = Makes a variable or method belong to the class
 5:         //          rather than to any specific object.
 6:         //          Commonly used for utility methods or shared resources.
 7: 
 8:         Friend friend1 = new Friend("Spongebob");
 9:         Friend friend2 = new Friend("Patrick");
10:         Friend friend3 = new Friend("Squidward");
11: 
12:         System.out.println(friend1.numOfFriends);
13:         System.out.println(friend2.numOfFriends);
14:         System.out.println(friend3.numOfFriends);
15:     }
16: }
```

#### Explicação Linha por Linha de [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

* **Linha 1 (`public class Main {`):**  
  Declara a classe principal onde fica o ponto de partida do nosso projeto.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O método principal `main`. Repare que o próprio `main` tem a palavra `static`! Isso significa que a Máquina Virtual Java (JVM) pode chamar o `main` para iniciar o programa **sem precisar criar um objeto `new Main()` antes**.
* **Linha 3 (` `):** Linha em branco.
* **Linhas 4 a 6 (`// static = Makes a variable...`):**  
  Comentários em inglês deixados pelo Bro Code explicando:
  * `static` faz com que uma variável ou método pertença à própria classe, em vez de pertencer a um objeto específico.
  * Muito usado para métodos utilitários ou recursos compartilhados.
* **Linha 7 (` `):** Linha em branco.
* **Linha 8 (`Friend friend1 = new Friend("Spongebob");`):**  
  1. Chama o construtor de `Friend` passando `"Spongebob"`.
  2. Dentro do construtor, `numOfFriends++` é executado. `numOfFriends` sai de `0` e vai para **`1`**.
  3. A variável de referência `friend1` aponta para esse novo objeto na memória.
* **Linha 9 (`Friend friend2 = new Friend("Patrick");`):**  
  1. Chama o construtor de `Friend` passando `"Patrick"`.
  2. Dentro do construtor, `numOfFriends++` é executado novamente. O mesmo contador sai de `1` e vai para **`2`**.
  3. A variável de referência `friend2` aponta para o segundo objeto.
* **Linha 10 (`Friend friend3 = new Friend("Squidward");`):**  
  *(A novidade desta imagem!)*
  1. Chama o construtor de `Friend` passando `"Squidward"`.
  2. Dentro do construtor, `numOfFriends++` é executado pela terceira vez. O mesmo contador sai de `2` e vai para **`3`**.
  3. A variável de referência `friend3` aponta para o terceiro objeto.
* **Linha 11 (` `):** Linha em branco.
* **Linha 12 (`System.out.println(friend1.numOfFriends);`):**  
  Acessa o valor de `numOfFriends` através da variável `friend1`. Como a variável é estática, o Java consulta o valor centralizado na classe `Friend`, que vale **`3`**.  
  **O que sai no console:**
  ```text
  3
  ```
* **Linha 13 (`System.out.println(friend2.numOfFriends);`):**  
  Acessa o valor de `numOfFriends` através de `friend2`. Olha para o mesmo endereço estático central, que vale **`3`**.  
  **O que sai no console:**
  ```text
  3
  ```
* **Linha 14 (`System.out.println(friend3.numOfFriends);`):**  
  Acessa o valor de `numOfFriends` através de `friend3`. Olha para o mesmo endereço estático central, que vale **`3`**.  
  **O que sai no console:**
  ```text
  3
  ```
* **Linha 15 (`}`):**  
  Fecha o método `main`.
* **Linha 16 (`}`):**  
  Fecha a classe `Main`.

---

## 5. Rastreamento da Memória da JVM (Stack vs. Heap vs. Metaspace)

Para entender 100% como a máquina enxerga isso:

```
[ STACK (Pilha de Variáveis) ]      [ HEAP (Objetos Criados) ]      [ METASPACE / CLASS AREA ]
==============================      ==========================      ==========================

friend1 (endereço 0x101)  ------->  Objeto Friend #1:               Classe Friend:
                                    - name = "Spongebob"            - static int numOfFriends = 3
                                                                                ▲  ▲  ▲
friend2 (endereço 0x202)  ------->  Objeto Friend #2:                           │  │  │
                                    - name = "Patrick"   -----------------------+  │  │
                                                                                   │  │
friend3 (endereço 0x303)  ------->  Objeto Friend #3:                              │  │
                                    - name = "Squidward" --------------------------+  │
                                                                                      │
                                    (friend1 acessando numOfFriends) -----------------+
```

* Os objetos no **Heap** guardam apenas o seu `name`.
* A variável `numOfFriends` **NÃO fica dentro dos objetos no Heap**!
* Ela mora no **Metaspace** (a área onde a classe `Friend` foi carregada pelo Java).
* Por isso, não importa se você pergunta através de `friend1`, `friend2` ou `friend3`: todos eles são redirecionados para a mesma e única caixinha no Metaspace que guarda o número `3`.

---

## 6. Tabela de Saída do Console e Resumo Comparativo

| Linha Executada no `Main.java` | O que o Java faz por baixo dos panos | Saída no Console | Aviso da IDE? |
| :--- | :--- | :---: | :---: |
| `System.out.println(friend1.numOfFriends);` | Lê a variável estática `Friend.numOfFriends` | `3` | ⚠️ Sim (Ondinha amarela) |
| `System.out.println(friend2.numOfFriends);` | Lê a variável estática `Friend.numOfFriends` | `3` | ⚠️ Sim (Ondinha amarela) |
| `System.out.println(friend3.numOfFriends);` | Lê a variável estática `Friend.numOfFriends` | `3` | ⚠️ Sim (Ondinha amarela) |

### Saída Exata Gerada no Console:
```text
3
3
3
```

---

## 7. Perguntas Frequentes do Iniciante Amador 🤔

### 1. "Por que o Spongebob também imprime 3 se ele foi criado primeiro?"
Porque o Spongebob **não tem** um número guardado dentro dele! Ele só tem uma "luneta" que aponta para a variável da classe. Quando você dá o `println` lá no final do programa, os três amigos já nasceram e o contador já foi incrementado 3 vezes. Ao olhar pela luneta, o Spongebob vê o valor atualizado: `3`.

### 2. "E se eu criar um quarto amigo, o `friend4 = new Friend("Sandy")`?"
Se você criar a Sandy, o construtor vai rodar mais uma vez e fará `numOfFriends++`. O contador passará a valer `4`. Se você mandar imprimir `friend1.numOfFriends`, o Spongebob agora responderá `4`!

### 3. "O aviso amarelo quebra o programa ou impede de rodar?"
**Não!** O aviso amarelo é um *Warning* (aviso de boas práticas), não é um *Error* (vermelho). O Java compila e roda perfeitamente. O IntelliJ só está sendo um "amigo chato" te dizendo: *"Ei, não seja preguiçoso chamando `friend1.numOfFriends`, chame logo `Friend.numOfFriends` que fica mais bonito e legível!"*.

---

## 8. Conclusão e Próximo Passo

Nesta etapa, o autor provou que não importa quantos amigos sejam criados (1, 2, 3 ou 1.000): a variável `static` é **única e compartilhada**.

No próximo quadro da aula, Bro Code vai atender ao pedido das ondinhas amarelas da IDE e demonstrar a sintaxe canônica e oficial do Java:  
👉 **`Friend.numOfFriends`** (Acessando o membro estático diretamente pelo nome da Classe!).
