# ➡️ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 32: Inserir Entrada do Usuário em um Array (`enter user input into an array`) — Parte 8: O Destaque da Variável `size`, a Conexão em 3 Atos e o Faxineiro de Buffer na Linha 12
**Timestamps do vídeo:** `5:19:15` até `5:19:35 / 12:00:00`  
**Tema:** O autor faz uma pausa didática no código e dá um duplo clique na palavra `size` na Linha 14, ativando o recurso de realce de identificadores do IntelliJ IDEA para mostrar como `size` conecta a declaração (Linha 8), a captura (Linha 11) e a instanciação do array (Linha 14), com destaque especial para a Linha 12 (`scanner.nextLine()`).

---

## 🖼️ Contexto Visual Minucioso do Print (`media_1791242050510.png`)

Se você copiou este arquivo para o seu Google Docs e não tem a imagem ao lado, aqui está a descrição fotográfica exata de cada pixel da tela do professor **Bro Code**:

### 1. O Editor de Código (`Main.java`):
* O professor está com as Linhas 4 a 21 visíveis no centro da tela.
* **O Destaque Azul na Linha 14:**
  * O professor deu um duplo clique com o mouse sobre a palavra `size` dentro dos colchetes:
    ```java
    foods = new String[size];
    ```
  * A palavra `size` ficou com um fundo azul brilhante de seleção.
* **O Efeito Mágico do IntelliJ IDEA (*Identifier Highlighting*):**
  * Como o professor selecionou `size`, o IntelliJ IDEA imediatamente iluminou com uma caixinha de contorno cinza-arroxeado **todas as outras linhas do código onde a variável `size` aparece**:
    * **Na Linha 8:** `int size;`
    * **Na Linha 11:** `size = scanner.nextInt();`
    * **Na Linha 14:** `foods = new String[size];`
  * Isso ajuda o estudante a rastrear com os olhos o caminho completo que a informação faz!
* **A Presença Clara da Linha 12:**
  * Logo abaixo do `nextInt()`, vemos perfeitamente a linha:
    ```java
    scanner.nextLine();
    ```
  * O professor deixa essa linha visível para lembrar aos alunos: *"Nunca se esqueçam do faxineiro do buffer após o `nextInt()`!"*.
* **A Lâmpada Amarela 💡 na Margem Esquerda:**
  * Ao lado do número 14, na margem esquerda, surge um ícone de lâmpada amarela acesa 💡.  
  * Essa lâmpada é o assistente inteligente do IntelliJ dizendo: *"Psiu, você sabia que poderia juntar `String[] foods;` da Linha 7 com esta Linha 14 numa linha só?"*. O professor ignora a lâmpada de propósito porque, didaticamente, separar em duas etapas é muito mais fácil para quem está começando entender!

---

## 🧠 A Trilogia da Variável `size`: Uma História em 3 Atos (Para o Lezado do Java)

Por que a variável `size` precisou aparecer em 3 linhas diferentes do programa?

```text
  ATO 1: A Promessa             ATO 2: A Decisão               ATO 3: A Construção
   (Linha 8)                     (Linha 11)                     (Linha 14)

  ┌──────────────┐              ┌──────────────┐              ┌──────────────────────────┐
  │   int size;  │ ───────────► │ size = 5;    │ ───────────► │ foods = new String[5];   │
  └──────────────┘              └──────────────┘              └──────────────────────────┘
  Cria a caixinha               O usuário digita 5            A fábrica cria as 5
  vazia na memória.             e guarda na caixinha.         gavetas reais na Heap!
```

### 🎭 Veja a analogia da compra de um guarda-roupa planejado:

#### Ato 1 (Linha 8: `int size;`): A Pasta do Projeto
Você vai à loja de móveis planejados. O vendedor abre uma pasta de projeto em branco e escreve na capa: **"Quantidade de Gavetas: ???"** (`int size;`).  
Nenhum móvel foi fabricado ainda. Apenas reservou-se um espaço para anotar o número quando você decidir.

#### Ato 2 (Linha 11: `size = scanner.nextInt();`): A Sua Escolha
O vendedor pergunta: *"Quantas gavetas você vai querer no seu guarda-roupa?"*.  
Você responde pelo teclado: **`5`**.  
O vendedor pega a caneta e anota `5` dentro da pasta: `size = 5;`.

#### Ato Extra (Linha 12: `scanner.nextLine();`): O Faxineiro do Balcão
Quando você respondeu `5`, você bateu a mão no balcão (apertou a tecla Enter).  
A Linha 12 é o atendente que passa um pano no balcão e joga a poeira do Enter no lixo, para não sujar os próximos pedidos de comida!

#### Ato 3 (Linha 14: `foods = new String[size];`): A Fábrica Constrói o Móvel
Agora que o marceneiro sabe que `size` vale 5, a fábrica do Java (`new`) liga os motores e constrói um guarda-roupa com exatamente **5 gavetas de verdade**: `[0]`, `[1]`, `[2]`, `[3]` e `[4]`.

---

## 💻 Código-Fonte Completo da Imagem (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String[] foods;
        int size;

        System.out.print("What # of food do you want?: ");
        size = scanner.nextInt();
        scanner.nextLine();

        foods = new String[size];

        for(int i = 0; i < foods.length; i++){
            System.out.print("Enter a food: ");
            foods[i] = scanner.nextLine();
        }

        for(String food : foods){
            System.out.println(food);
        }

        scanner.close();
    }
}
```

---

## 🔍 Enumeração Linha por Linha (Com Memória RAM e Console)

* **Linha 1:** `import java.util.Scanner;` $\rightarrow$ Importa o leitor. *(Console: Nada)*
* **Linha 2:** *(Linha em branco).*
* **Linha 3:** `public class Main {` $\rightarrow$ Abertura da classe. *(Console: Nada)*
* **Linha 4:** `public static void main(String[] args){` $\rightarrow$ Ponto inicial. *(Console: Nada)*
* **Linha 5:** *(Linha em branco).*
* **Linha 6:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Liga o leitor de teclado. *(Console: Nada)*
* **Linha 7:** `String[] foods;`  
  * **O que faz:** Declara a variável de referência `foods`. Ela ainda não aponta para nada (`null`).  
  * **Memória RAM:** Cria a etiqueta `foods` na memória Stack.  
  * **Console:** Nada.
* **Linha 8:** `int size;`  
  * **O que faz:** **ATO 1.** Cria a caixinha inteira `size` para receber a quantidade.  
  * **Memória RAM:** Cria a gaveta inteira `size` na Stack.  
  * **Console:** Nada.
* **Linha 9:** *(Linha em branco).*
* **Linha 10:** `System.out.print("What # of food do you want?: ");`  
  * **O que faz:** Imprime a pergunta sem quebrar linha.  
  * **Console:** `What # of food do you want?: `
* **Linha 11:** `size = scanner.nextInt();`  
  * **O que faz:** **ATO 2.** O usuário digita um número (ex: `5`). O número entra em `size` e o `\n` fica solto na esteira.  
  * **Memória RAM:** `size` agora vale `5`.  
  * **Console:** O número digitado aparece na frente da pergunta.
* **Linha 12:** `scanner.nextLine();`  
  * **O que faz:** **O FAXINEIRO.** Engole a quebra de linha `\n` e deixa a esteira do teclado limpa para as comidas.  
  * **Console:** Nada.
* **Linha 13:** *(Linha em branco).*
* **Linha 14:** `foods = new String[size];`  
  * **O que faz:** **ATO 3.** Como `size` vale 5, é o equivalente exato a `new String[5];`.  
  * **Memória RAM:** A palavra `new` aloca um array contínuo de 5 gavetas na memória Heap: `[null, null, null, null, null]`.  
  * **Console:** Nada nessa linha.
* **Linha 15:** *(Linha em branco).*
* **Linha 16:** `for(int i = 0; i < foods.length; i++){`  
  * **O que faz:** Como `foods.length = 5`, o laço vai rodar 5 voltas (índices 0, 1, 2, 3 e 4).  
  * **Console:** Nada nessa linha diretamente.
* **Linha 17:** `System.out.print("Enter a food: ");`  
  * **Console:** `Enter a food: `
* **Linha 18:** `foods[i] = scanner.nextLine();`  
  * **O que faz:** O programa espera você digitar cada comida e guarda na gaveta `i`.  
  * **Console:** O nome da comida digitada aparece na tela.
* **Linha 19:** `}` $\rightarrow$ Fecha o laço de entrada.
* **Linha 20:** *(Linha em branco).*
* **Linha 21:** `for(String food : foods){`  
  * **O que faz:** Laço `for-each` que percorre todas as comidas cadastradas.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 22:** `System.out.println(food);`  
  * **O que faz:** Imprime cada comida e pula de linha.  
  * **Console:** Imprime a lista de comidas verticalmente.
* **Linha 23:** `}` $\rightarrow$ Fecha o laço `for-each`.
* **Linha 24:** *(Linha em branco).*
* **Linha 25:** `scanner.close();` $\rightarrow$ Fecha o `Scanner`.
* **Linha 26:** `}` $\rightarrow$ Fecha o método `main`.
* **Linha 27:** `}` $\rightarrow$ Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Quando Você Roda Esse Código:

```text
What # of food do you want?: 5
Enter a food: pizza
Enter a food: taco
Enter a food: hamburger
Enter a food: hotdog
Enter a food: sushi
pizza
taco
hamburger
hotdog
sushi

Process finished with exit code 0
```

---

## 🎯 Por Que Esta Tela é Considerada Histórica no Seu Aprendizado?

Até este momento do curso, você só sabia criar programas onde os dados estavam congelados no código.  
A partir desta tela:
1. **O usuário decide a quantidade** (`size`).
2. **O array se adapta ao tamanho do usuário** (`new String[size]`).
3. **O laço se adapta automaticamente ao tamanho do array** (`foods.length`).
4. **O usuário digita cada item** (`scanner.nextLine()`).
5. **O buffer é protegido contra travamentos** (`scanner.nextLine()` de limpeza).

Você acaba de aprender a construir a arquitetura básica de **qualquer sistema de cadastro do mundo real**!
