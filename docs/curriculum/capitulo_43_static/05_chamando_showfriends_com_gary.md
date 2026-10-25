# Capítulo 43: `static` 🤝 — Parte 5: Chamando `Friend.showFriends()`, Adicionando Gary e a Saída *"You have 5 total friends"*

> **Contexto do Curso:** *Java Full Course for free* (Bro Code)  
> **Capítulo:** 43 — `static 🤝`  
> **Tempo do Vídeo:** `07:20:00` até `07:20:30` / `12:00:00`  
> **Arquivos do Projeto:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) e [Friend.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java)  
> **Objetivo Deste Documento:** Guia didático minucioso, pronto para copiar e colar no **Google Docs**, recriando com exatidão as telas do IntelliJ IDEA, dissecando cada linha de código para um iniciante absoluto e demonstrando a execução de um método estático em harmonia com uma variável estática.

---

## 1. Reconstituição Visual da Tela (Para Visualização sem as Imagens)

Nesta etapa, o instrutor Bro Code une as duas metades da moeda do modificador `static`:
1. Uma **variável estática** (`numOfFriends`), que guarda um dado compartilhado por toda a classe.
2. Um **método estático** (`showFriends()`), que executa uma ação da classe sem precisar de nenhum objeto particular.

```
+-----------------------------------------------------------------------------------------------+
| [Project] MyFirstProject > src > Friend.java | Main.java [x]                       [ ▶ Run ] |
+-----------------------------------------------------------------------------------------------+
|  8 |                                                                                          |
|  9 |         Friend friend1 = new Friend("Spongebob");                                        |
| 10 |         Friend friend2 = new Friend("Patrick");                                          |
| 11 |         Friend friend3 = new Friend("Squidward");                                        |
| 12 |         Friend friend4 = new Friend("Sandy");                                            |
| 13 |  💡     Friend friend5 = new Friend("Gary");                                             |
| 14 |                                                                                          |
| 15 |         Friend.showFriends();                                                            |
| 16 |     }                                                                                    |
| 17 | }                                                                                        |
+-----------------------------------------------------------------------------------------------+
| [Terminal Run / Console]                                                                      |
|                                                                                               |
| You have [ 5 ] total friends                                                                  |
|                                                                                               |
| Process finished with exit code 0                                                             |
+-----------------------------------------------------------------------------------------------+
```

---

### Detalhamento Quadro a Quadro (As 2 Imagens Explicadas)

#### 🖼️ Imagem 1: Substituindo o Print Manual por `Friend.showFriends()`
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que mudou no código:**
  * O autor apagou a linha `System.out.println(Friend.numOfFriends);`.
  * No lugar dela, na linha 14, ele chamou o novo método estático criado na classe `Friend`:
    ```java
    Friend.showFriends();
    ```
  * Observe como o código ficou extremamente limpo e legível. Ele não precisou passar variáveis nem criar nada novo para chamar essa ação.
* **Posição do mouse:** O cursor do mouse está sobre o botão verde de **Run** (`▶`) no canto superior direito (`Shift + F10`).

---

#### 🖼️ Imagem 2 (`07:20:26`): Adicionando Gary e a Saída Formatada no Console
* **Arquivo ativo:** [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
* **O que mudou no código:**
  * Bro Code adicionou um quinto amigo na linha 13:
    ```java
    Friend friend5 = new Friend("Gary");
    ```
  * Ao lado da linha 13, aparece um ícone de lâmpada amarela (💡) que apenas sugere opções contextuais da IDE.
  * O autor executou o programa.
* **O que aparece no Console (painel inferior Run):**
  * O console imprime a frase em verde:
    ```text
    You have 5 total friends
    ```
  * Bro Code **seleciona o número `5` com o mouse**, deixando-o com um retângulo azul de seleção para enfatizar aos alunos que o número `5` foi calculado de forma dinâmica e 100% precisa pelo Java!
  * O cursor do mouse está no botão de fechar (`x`) da aba `Main` do console.

---

## 2. A Mágica Explicada para o Iniciante Amador

Se você nunca programou na vida ou se sente inseguro em Java, pense nesta cena da vida real:

### A Lista de Amigos no Clube

Imagine que você abriu um **Clube de Amigos** (`Friend`).
Na porta desse clube, existe um letreiro eletrônico luminoso (`static int numOfFriends = 0`).

1. Entra o **Bob Esponja** (`new Friend("Spongebob")`):
   * O Bob Esponja ganha um crachá com o nome dele (`this.name = "Spongebob"`).
   * Ele aperta o botão da catraca (`numOfFriends++`).
   * O letreiro na parede muda para **1**.
2. Entra o **Patrick** (`new Friend("Patrick")`):
   * Ganha o crachá dele.
   * Aperta a catraca (`numOfFriends++`).
   * O letreiro muda para **2**.
3. Entra o **Lula Molusco** (*Squidward*):
   * Aperta a catraca $\rightarrow$ o letreiro marca **3**.
4. Entra a **Sandy**:
   * Aperta a catraca $\rightarrow$ o letreiro marca **4**.
5. Entra o caracol **Gary**:
   * Aperta a catraca $\rightarrow$ o letreiro marca **5**.

Agora, você não quer ficar perguntando para cada um individualmente.  
Você simplesmente aperta o interfone da portaria do Clube e pede para a secretária anunciar o status:
```java
Friend.showFriends();
```
A portaria do clube (`Friend`) pega o número que está no letreiro central (`5`) e fala no alto-falante:
> **"You have 5 total friends"**

---

## 3. Dissecando a Sintaxe: O Que Significa `Friend.showFriends()`?

Preste muita atenção nesta única linha:

```java
Friend.showFriends();
  │         │       └─ Os parênteses indicam que estamos EXECUTANDO uma ação (método)
  │         └─ Nome do método estático que criamos dentro de Friend.java
  └─ Nome da CLASSE (com 'F' maiúsculo)
```

### Por que isso é tão revolucionário para um iniciante?
1. **Não usa a palavra `new`:** Você não precisou criar um novo amigo para chamar o método.
2. **Não usa nenhuma variável de objeto:** Você não escreveu `friend1.showFriends()` nem `friend5.showFriends()`.
3. **É uma função utilitária da própria Classe:** Assim como você chama `Math.round(4.6)` para arredondar um número, você chama `Friend.showFriends()` para exibir o total de amigos.

---

## 4. Código Completo e Linha por Linha Numerado

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
11:     static void showFriends(){
12:         System.out.println("You have " + numOfFriends + " total friends");
13:     }
14: 
15: }
```

#### Explicação Detalhada de [Friend.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Friend.java):

* **Linha 1 (`public class Friend {`):** Define a classe pública `Friend`.
* **Linha 2 (` `):** Linha em branco.
* **Linha 3 (`static int numOfFriends;`):**  
  A variável estática inteira. Não pertence a nenhum objeto em particular; fica guardada na área de classes da memória (Metaspace) e começa valendo `0`.
* **Linha 4 (`String name;`):**  
  Variável de instância. Cada amigo terá uma caixinha própria para guardar seu nome no Heap.
* **Linha 5 (` `):** Linha em branco.
* **Linha 6 (`Friend(String name){`):**  
  O construtor da classe. Ele é acionado **toda vez** que o comando `new Friend(...)` é executado no programa.
* **Linha 7 (`this.name = name;`):**  
  Copia o nome passado entre parênteses para a variável `name` do objeto específico que acabou de ser criado (`this`).
* **Linha 8 (`numOfFriends++;`):**  
  Incrementa em `+1` o contador geral da classe `Friend`.
* **Linha 9 (`}`):** Fecha o construtor.
* **Linha 10 (` `):** Linha em branco.
* **Linha 11 (`static void showFriends(){`):**  
  Declaração do método estático:
  * `static`: Pertence à classe `Friend`. Pode ser chamado como `Friend.showFriends()`.
  * `void`: Não devolve nenhum dado com `return` (apenas realiza uma impressão na tela).
  * `showFriends()`: Nome do método, seguido de parênteses vazios (não necessita de argumentos de entrada).
* **Linha 12 (`System.out.println("You have " + numOfFriends + " total friends");`):**  
  Combina a frase fixa com o valor da variável estática `numOfFriends` e imprime no console.  
  *(Atenção: sem o prefixo `this.`, pois `this` é proibido em métodos estáticos!)*.
* **Linha 13 (`}`):** Fecha o corpo do método `showFriends()`.
* **Linha 14 (` `):** Linha em branco.
* **Linha 15 (`}`):** Fecha a classe `Friend`.

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
11:         Friend friend4 = new Friend("Sandy");
12:         Friend friend5 = new Friend("Gary");
13: 
14:         Friend.showFriends();
15:     }
16: }
```

#### Explicação Detalhada de [Main.java](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

* **Linha 1 (`public class Main {`):** Abre a classe principal.
* **Linha 2 (`public static void main(String[] args) {`):** Ponto de partida do programa.
* **Linhas 4 a 6:** Comentários conceituais sobre o modificador `static`.
* **Linha 7 (` `):** Linha em branco.
* **Linha 8 (`Friend friend1 = new Friend("Spongebob");`):**  
  Cria o objeto 1. O construtor roda: `numOfFriends` sai de 0 e passa a ser **1**.
* **Linha 9 (`Friend friend2 = new Friend("Patrick");`):**  
  Cria o objeto 2. O construtor roda: `numOfFriends` sai de 1 e passa a ser **2**.
* **Linha 10 (`Friend friend3 = new Friend("Squidward");`):**  
  Cria o objeto 3. O construtor roda: `numOfFriends` sai de 2 e passa a ser **3**.
* **Linha 11 (`Friend friend4 = new Friend("Sandy");`):**  
  Cria o objeto 4. O construtor roda: `numOfFriends` sai de 3 e passa a ser **4**.
* **Linha 12 (`Friend friend5 = new Friend("Gary");`):**  
  Cria o objeto 5 (*Gary*). O construtor roda: `numOfFriends` sai de 4 e passa a ser **5**.
* **Linha 13 (` `):** Linha em branco.
* **Linha 14 (`Friend.showFriends();`):**  
  Chama o método estático da classe `Friend`. O método lê o valor atual de `numOfFriends` (que é **5**) e dispara o `println`.
* **Linhas 15 e 16:** Fecham o método `main` e a classe `Main`.

---

## 5. Rastreamento Passo a Passo da Execução e Memória

Acompanhe o que acontece na memória do computador a cada linha executada:

| Passo | Linha Executada | Objeto Criado | Valor de `Friend.numOfFriends` |
| :---: | :--- | :---: | :---: |
| 1 | Início do Programa | Nenhum | `0` (padrão) |
| 2 | `Friend friend1 = new Friend("Spongebob");` | `friend1` ("Spongebob") | `1` |
| 3 | `Friend friend2 = new Friend("Patrick");` | `friend2` ("Patrick") | `2` |
| 4 | `Friend friend3 = new Friend("Squidward");` | `friend3` ("Squidward") | `3` |
| 5 | `Friend friend4 = new Friend("Sandy");` | `friend4` ("Sandy") | `4` |
| 6 | `Friend friend5 = new Friend("Gary");` | `friend5` ("Gary") | `5` |
| 7 | `Friend.showFriends();` | Nenhum (apenas lê a classe) | Imprime `"You have 5 total friends"` |

---

## 6. O Que Sai no Console?

Ao executar o programa completo, a saída gerada no terminal é exatamente:

```text
You have 5 total friends
```

---

## 7. Resumo das Lições de Ouro do Capítulo 43 (`static`)

1. **Variáveis Estáticas (`static variables`):**
   * Compartilhadas por **todos** os objetos daquela classe.
   * Só existe **uma única cópia** na memória inteira.
   * Ótimas para contadores globais, configurações compartilhadas ou valores universais.
2. **Métodos Estáticos (`static methods`):**
   * Pertencem à classe e podem ser chamados com `NomeDaClasse.nomeDoMetodo()`.
   * **NÃO** precisam de nenhum `new Objeto()` para funcionarem.
   * **NUNCA** podem usar `this`, porque o `this` exige um objeto individual, e o método estático opera no nível da classe inteira.
3. **Boas Práticas de Sintaxe:**
   * Evite chamar variáveis ou métodos estáticos através de um objeto (`friend1.numOfFriends` ou `friend1.showFriends()`). Isso confunde quem lê seu código e faz a IDE gritar avisos amarelos.
   * Chame sempre através do **Nome da Classe**: `Friend.numOfFriends` e `Friend.showFriends()`.
