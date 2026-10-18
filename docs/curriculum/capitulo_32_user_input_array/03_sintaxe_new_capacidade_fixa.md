# ➡️ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 32: Inserir Entrada do Usuário em um Array (`enter user input into an array`) — Parte 3: A Solução com `new`, Pré-Alocação de Vagas (`new String[3]`) e o Sucesso com `exit code 0`
**Timestamps do vídeo:** `5:13:30` até `5:14:37 / 12:00:00`  
**Tema:** A sintaxe oficial do Java para declarar arrays com capacidade definida antecipadamente (`new tipo[tamanho]`), a alocação de 3 gavetas reais na memória Heap, a atribuição bem-sucedida de `"pizza"` no índice `[0]`, a propriedade `foods.length` confirmando o tamanho `3` e a volta da paz com o encerramento em `exit code 0`.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem está lendo no Google Docs sem as imagens)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está a descrição fotográfica exata do que o professor **Bro Code** fez na tela:

### 1. Imagem 1 (`media_1791241759577.png`): O Código Corrigido no Editor
* **A Grande Mudança na Linha 5:**
  * O professor apaga aquelas chaves vazias inúteis `{}` que causaram o erro fatal na aula anterior e escreve:
    ```java
    String[] foods = new String[3];
    ```
* **O Que Aconteceu na Linha 7:**
  * A instrução `foods[0] = "pizza";` continua exatamente no mesmo lugar!
  * **O Detalhe Visual Impressionante:** Aquele ícone circular vermelho com ponto de exclamação ❗ e a cobrinha ondulada de perigo que estavam embaixo do `[0]` **sumiram completamente**!
  * O IntelliJ IDEA agora está tranquilo, porque ele sabe que a gaveta 0 foi oficialmente construída!
* **A Linha 9:**
  * O professor adiciona: `System.out.println(foods.length);` para conferir na tela o novo tamanho da estrutura.
* **O Cursor do Mouse:**
  * O cursor está posicionado no canto superior direito sobre o botão verde de **Play** (`Run 'Main.java'`).

---

### 2. Imagem 2 (`media_1791241769841.png`): A Execução e o Retorno da Vitória (`exit code 0`)
* **Timestamp no Vídeo:** Vemos claramente no player do YouTube a marcação de tempo: `5:14:37 / 12:00:00` e o título do capítulo: `32.enter user input into an array ➡️`.
* **A Saída no Console:**
  * O terminal inferior abre e executa o código com o OpenJDK 23.
  * Em vez de qualquer erro em vermelho, o console imprime limpo e reluzente o número:
    ```text
    3
    ```
  * Logo abaixo, a mensagem sagrada que todo programador ama ver:
    ```text
    Process finished with exit code 0
    ```
* **A Explicação do Professor:**
  * O professor seleciona o número `3` com o mouse no console e comemora:  
    *"Viram a diferença? Ao usar `new String[3]`, nós criamos 3 vagas reais na memória. A gaveta 0 agora existe, a pizza foi guardada com sucesso e o tamanho total do array agora é 3!"*.

---

## 🧠 A Metáfora do Marceneiro e do Armário Sob Medida 🪚🚪

Para quem é iniciante amador ("o lezado do Java"), o que mudou da aula passada para esta?

### 1. Na Aula Passada (O Desastre do Terreno Baldio):
Você escreveu `String[] foods = {};`.  
Você mandou o motoboy entregar uma pizza no "Apartamento 0", mas não havia prédio nenhum construído. Deu o erro `ArrayIndexOutOfBoundsException` e `exit code 1`.

### 2. Nesta Aula (Contratando o Marceneiro):
Agora você chamou o marceneiro mestre do Java (a palavra mágica **`new`**) e fez uma encomenda formal:
> *"Marceneiro Java (`new`), construa para mim uma cômoda novinha de madeira (`String`) com exatamente 3 gavetas (`[3]`)!"*

O marceneiro vai até a oficina da memória RAM (a memória **Heap**) e constrói um móvel novinho com **3 gavetas físicas reais**, numeradas exatamente assim:
* **Gaveta `[0]`** $\rightarrow$ Primeira gaveta.
* **Gaveta `[1]`** $\rightarrow$ Segunda gaveta.
* **Gaveta `[2]`** $\rightarrow$ Terceira gaveta.

Quando o código executa a Linha 7:
```java
foods[0] = "pizza";
```
O Java abre a gaveta `[0]`, coloca a caixa de pizza quentinha lá dentro e fecha a gaveta com um sorriso! **Nenhum erro acontece!**

---

## 🔬 A Anatomia da Linha Mágica: `new String[3]`

Vamos dissecar cada pedacinho dessa instrução fundamental:

$$\text{String[] foods} = \underbrace{\text{new}}_{\text{1. Aloca na Heap}}\ \underbrace{\text{String}}_{\text{2. Tipo de dado}}\ \underbrace{[3]}_{\text{3. Quantidade de vagas}};$$

1. **`String[] foods`:**  
   Declara a etiqueta/variável chamada `foods` que vai apontar para um array de textos.
2. **`=` (Atribuição):**  
   Conecta a etiqueta ao objeto que está sendo criado.
3. **`new` (Palavra Reservada):**  
   É o comando que diz à Máquina Virtual Java: *"Vá até a memória Heap e reserve espaço físico novo para um objeto novinho em folha!"*.
4. **`String[3]`:**  
   Define o molde do objeto:  
   * O tipo de elemento que vai morar nas gavetas: textos (`String`).  
   * A quantidade exata de gavetas que serão fabricadas: **3 vagas fixas**.

---

## ❓ O Que Tem Dentro das Outras Gavetas (`foods[1]` e `foods[2]`) Agora?

Essa é uma pergunta clássica de entrevista e de prova:
> *"Se nós só colocamos a pizza na gaveta `foods[0]`, o que existe agora dentro da gaveta `foods[1]` e da gaveta `foods[2]`?"*

### A Resposta: O Valor Padrão `null`!
No Java, quando você cria um array de objetos ou de Strings com `new`, todas as gavetas nascem preenchidas com o valor padrão: **`null`** (que em alemão e em computação significa **"vazio" / "nada" / "apontando para o além"**).

```text
ESTADO ATUAL DO ARRAY FOODS NA MEMÓRIA RAM:

               [0]            [1]            [2]
         ┌──────────────┬──────────────┬──────────────┐
foods ──►│   "pizza"    │     null     │     null     │
         └──────────────┴──────────────┴──────────────┘
          (Preenchido)     (Vazio)        (Vazio)
```

Se você mandasse imprimir `foods[1]` agora, o console não daria erro! Ele imprimiria simplesmente: `null`.

---

## 💻 Código-Fonte Completo da Imagem 1

```java
public class Main {
    public static void main(String[] args){

        String[] foods = new String[3];

        foods[0] = "pizza";

        System.out.println(foods.length);
    }
}
```

---

## 🔍 Enumeração Linha por Linha (Com Memória RAM e Console)

* **Linha 1:** *(Linha em branco).*
  * **Console:** Nada.

* **Linha 2:** `public class Main {`
  * **O que faz:** Declara a classe principal `Main`.
  * **Console:** Nada.

* **Linha 3:** `public static void main(String[] args){`
  * **O que faz:** Ponto de entrada onde o programa começa a rodar.
  * **Memória RAM:** Cria o quadro de execução do `main` na memória Stack.
  * **Console:** Nada.

* **Linha 4:** *(Linha em branco).*
  * **Console:** Nada.

* **Linha 5:** `String[] foods = new String[3];`
  * **O que faz:** Cria um array de 3 posições do tipo String.
  * **Memória RAM:**
    * Na **Stack**, cria a variável de referência `foods`.
    * Na **Heap**, aloca um bloco contínuo de memória com 3 posições: `[null, null, null]`.
    * A variável `foods` recebe o endereço de memória desse bloco.
  * **Console:** Nada nessa linha diretamente.

* **Linha 6:** *(Linha em branco).*
  * **Console:** Nada.

* **Linha 7:** `foods[0] = "pizza";`
  * **O que faz:** Acessa o índice 0 do array `foods` e guarda a String `"pizza"`.
  * **Memória RAM:** A primeira gaveta, que continha `null`, agora guarda a referência para o texto `"pizza"`. As gavetas 1 e 2 continuam com `null`.
  * **Console:** Nada nessa linha diretamente.

* **Linha 8:** *(Linha em branco).*
  * **Console:** Nada.

* **Linha 9:** `System.out.println(foods.length);`
  * **O que faz:** Lê a propriedade `.length` do array (que guarda o total de compartimentos) e imprime no terminal.
  * **Memória RAM:** Lê o número inteiro `3`.
  * **Console:** Imprime o número **`3`** e pula de linha!

* **Linha 10:** `}`
  * **O que faz:** Fecha o método `main`. O programa encerra sua tarefa com sucesso.
  * **Console:** Exibe a mensagem de finalização pacífica: `Process finished with exit code 0`.

* **Linha 11:** `}`
  * **O que faz:** Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Exatamente (Imagem 2):

```text
3

Process finished with exit code 0
```

---

## 🚀 A Conexão com o Próximo Passo do Capítulo

Até aqui, o professor provou duas coisas vitais:
1. Um array vazio `{}` tem tamanho zero e não aceita dados.
2. Um array criado com `new String[3]` tem 3 vagas físicas e aceita dados sem erro!

### Mas repare num detalhe:
Nós escrevemos o número `3` fixo no código (`new String[3]`) e escrevemos a palavra `"pizza"` fixa no código (`foods[0] = "pizza"`).  
Mas o título do capítulo é:  
**`enter user input into an array ➡️`** (*Inserir entrada do USUÁRIO em um array!*)

O objetivo real é fazer o programa ser **100% interativo**:
1. Ligar o **`Scanner`**.
2. Perguntar ao usuário: *"Quantas comidas você quer cadastrar?"*.
3. O número que o usuário digitar (ex: 4) será usado para criar o array:  
   `String[] foods = new String[size];`
4. Usar um laço `for` para pedir: *"Digite a comida #1:"*, *"Digite a comida #2:"*... e guardar cada uma nas gavetas `foods[i]`!

Isso é exatamente o que o professor vai construir a seguir!
