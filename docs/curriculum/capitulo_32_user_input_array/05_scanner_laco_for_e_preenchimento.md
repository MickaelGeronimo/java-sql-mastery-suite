# ➡️ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 32: Inserir Entrada do Usuário em um Array (`enter user input into an array`) — Parte 5: O Scanner em Ação, o Laço `for` com `foods[i]` e a Exibição Dinâmica Completa
**Timestamps do vídeo:** `5:16:00` até `5:17:15 / 12:00:00`  
**Tema:** Conectando o `Scanner` ao array, a razão fundamental de usarmos o laço `for` clássico com índice `i` para **escrita de dados** (`foods[i] = scanner.nextLine()`), por que o laço `for-each` só serve para **leitura**, e a execução interativa ao vivo com as entradas `pizza`, `taco` e `hamburger`.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs sem as imagens)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está a descrição fotográfica exata de cada um dos 4 momentos do vídeo:

### 1. Imagem 1 (`media_1791241856569.png`): Preparando o Terreno com o `Scanner`
* **O Código no Editor:**
  * No topo (Linha 1), o professor importa o leitor:
    ```java
    import java.util.Scanner;
    ```
  * Na Linha 6, ele liga o teclado:
    ```java
    Scanner scanner = new Scanner(System.in);
    ```
  * Na Linha 8, mantém o array de 3 vagas:
    ```java
    String[] foods = new String[3];
    ```
  * E no final (Linha 14), já deixa programado o desligamento:
    ```java
    scanner.close();
    ```
  * **O Que Sumiu:** O professor apagou aquelas 3 linhas fixas amadoras (`foods[0] = "pizza";`, etc.). Agora o array está limpo, pronto para receber dados dinâmicos do teclado!

---

### 2. Imagem 2 (`media_1791241871105.png` — Timestamp `5:17:01`): O Laço de Entrada (Input Loop)
* **O Bloco Digitado em Destaque Azul (Linhas 10 a 13):**
  * O professor digita o laço de repetição que vai alimentar o array:
    ```java
    for(int i = 0; i < foods.length; i++){
        System.out.print("Enter a food: ");
        foods[i] = scanner.nextLine();
    }
    ```
  * Ele aponta para a tela e diz: *"Vejam como isso é inteligente! Em vez de escrever 3 comandos repetidos na mão, usamos o laço `for` com o contador `i`. Ele vai rodar para `i = 0`, depois para `i = 1`, e depois para `i = 2`!"*.

---

### 3. Imagem 3 (`media_1791241879159.png` — Timestamp `5:17:11`): O Programa em Pausa Esperando Você
* **A Execução ao Vivo:**
  * O professor clica no Play verde.
  * O console inferior abre e exibe:
    ```text
    Enter a food: 
    ```
  * **O Cursor Piscando:** Repare que o cursor branco do teclado está piscando logo após a palavra `food: `. O Java parou a execução na instrução `scanner.nextLine()` esperando o usuário digitar algo e apertar a tecla Enter!

---

### 4. Imagem 4 (`media_1791241891576.png`): O Resultado Completo e Triunfal
* **O Que o Professor Digitou:**
  * Na 1ª pergunta: digitou `pizza` e deu Enter.
  * Na 2ª pergunta: digitou `taco` e deu Enter.
  * Na 3ª pergunta: digitou `hamburger` e deu Enter.
* **A Saída Imediata no Console:**
  * Assim que a terceira comida foi digitada, o primeiro laço terminou e o segundo laço (`for-each`) entrou em ação, imprimindo todas as comidas:
    ```text
    Enter a food: pizza
    Enter a food: taco
    Enter a food: hamburger
    pizza
    taco
    hamburger

    Process finished with exit code 0
    ```
  * Na imagem, a palavra `pizza` da saída está selecionada em azul pelo mouse do professor para destacar que o array foi preenchido com sucesso e lido logo em seguida!

---

## 🧠 Por Que Usamos 2 Laços Diferentes no Mesmo Código? (Para o Lezado do Java)

Observe que o código agora tem **DOIS laços `for`**, um embaixo do outro:
1. Um laço clássico: `for(int i = 0; i < foods.length; i++)`
2. Um laço moderno: `for(String food : foods)`

Por que o professor não usou o laço moderno `for-each` nos dois lugares?

### 🛑 A Regra Fundamental:
* **Para ESCOLHER e GRAVAR dados em uma gaveta:** Você **PRECISA** saber o número da gaveta (`[i]`)!  
  Você precisa dizer: *"Java, guarde isso na gaveta 0 (`foods[0]`), depois na gaveta 1 (`foods[1]`), depois na gaveta 2 (`foods[2]`)"*.  
  Como o laço `for-each` esconde o número `i`, **você NUNCA pode usar `for-each` para preencher ou alterar um array**!
* **Para apenas LER e MOSTRAR na tela:** As gavetas já estão todas cheias. Você não se importa se é a gaveta 0 ou 1, só quer ver o que tem dentro. Aí sim o laço `for-each` brilha por ser mais limpo e elegante!

```text
┌──────────────────────────────────────────────────────────────┐
│  LAÇO 1 (For Clássico com i)  ──► É o CARTEIRO               │
│                                   (Coloca cartas nas caixas  │
│                                    específicas [0], [1], [2])│
├──────────────────────────────────────────────────────────────┤
│  LAÇO 2 (For-Each sem índice) ──► É o MORADOR                │
│                                   (Apenas abre e lê tudo     │
│                                    o que já foi entregue)    │
└──────────────────────────────────────────────────────────────┘
```

---

## 🔬 A Anatomia do Laço de Entrada Passo a Passo

Vamos olhar com uma lupa para as linhas 10 a 13:

```java
for(int i = 0; i < foods.length; i++){
    System.out.print("Enter a food: ");
    foods[i] = scanner.nextLine();
}
```

### O Filme em Câmera Lenta da Memória RAM:

#### 1ª Volta (`i = 0`):
1. O Java testa: `0 < 3`? **Sim!**
2. Imprime na tela: `Enter a food: ` (usa `print` sem `ln`, então o cursor fica colado na pergunta).
3. O comando `scanner.nextLine()` congela o programa até você digitar `pizza` e dar Enter.
4. A gaveta `foods[0]` recebe `"pizza"`.
5. O laço executa `i++` (agora `i = 1`).

#### 2ª Volta (`i = 1`):
1. O Java testa: `1 < 3`? **Sim!**
2. Imprime na tela: `Enter a food: `.
3. O usuário digita `taco` e dá Enter.
4. A gaveta `foods[1]` recebe `"taco"`.
5. O laço executa `i++` (agora `i = 2`).

#### 3ª Volta (`i = 2`):
1. O Java testa: `2 < 3`? **Sim!**
2. Imprime na tela: `Enter a food: `.
3. O usuário digita `hamburger` e dá Enter.
4. A gaveta `foods[2]` recebe `"hamburger"`.
5. O laço executa `i++` (agora `i = 3`).

#### Fim do Laço de Entrada:
* O Java testa: `3 < 3`? **Falso!**
* O laço morre! Todas as 3 gavetas estão cheias e prontas na memória RAM!

---

## 💻 Código-Fonte Completo da Imagem 2

Copie este código para o seu IntelliJ IDEA para testar e interagir pelo terminal:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String[] foods = new String[3];

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

* **Linha 1:** `import java.util.Scanner;`  
  * **O que faz:** Importa a classe `Scanner` do pacote de utilitários do Java.  
  * **Console:** Nada.

* **Linha 2:** *(Linha em branco).*  
  * **Console:** Nada.

* **Linha 3:** `public class Main {`  
  * **O que faz:** Abre a classe principal `Main`.  
  * **Console:** Nada.

* **Linha 4:** `public static void main(String[] args){`  
  * **O que faz:** Ponto de entrada do programa.  
  * **Console:** Nada.

* **Linha 5:** *(Linha em branco).*  
  * **Console:** Nada.

* **Linha 6:** `Scanner scanner = new Scanner(System.in);`  
  * **O que faz:** Cria o leitor de teclado conectado à entrada padrão do sistema (`System.in`).  
  * **Memória RAM:** Cria o objeto `scanner` na memória Heap.  
  * **Console:** Nada.

* **Linha 7:** *(Linha em branco).*  
  * **Console:** Nada.

* **Linha 8:** `String[] foods = new String[3];`  
  * **O que faz:** Reserva 3 gavetas para textos na memória Heap.  
  * **Memória RAM:** `foods = [null, null, null]`.  
  * **Console:** Nada.

* **Linha 9:** *(Linha em branco).*  
  * **Console:** Nada.

* **Linha 10:** `for(int i = 0; i < foods.length; i++){`  
  * **O que faz:** Inicia o laço de entrada. Como `foods.length = 3`, o contador `i` vai assumir os valores `0`, `1` e `2`.  
  * **Console:** Nada nessa linha diretamente.

* **Linha 11:** `System.out.print("Enter a food: ");`  
  * **O que faz:** Exibe a mensagem pedindo a comida. Como usa `print`, o cursor fica esperando na mesma linha.  
  * **Console:** `Enter a food: `

* **Linha 12:** `foods[i] = scanner.nextLine();`  
  * **O que faz:** Lê todo o texto digitado pelo usuário e salva na posição `i` do array `foods`.  
  * **Memória RAM:** Guarda o texto digitado na gaveta `i`.  
  * **Console:** O texto digitado aparece na frente da pergunta.

* **Linha 13:** `}`  
  * **O que faz:** Fecha o bloco do laço `for`. Faz `i++` e repete enquanto `i < 3`.

* **Linha 14:** *(Linha em branco).*  
  * **Console:** Nada.

* **Linha 15:** `for(String food : foods){`  
  * **O que faz:** Inicia o laço de saída (`for-each`) para percorrer todos os elementos já guardados.  
  * **Console:** Nada nessa linha diretamente.

* **Linha 16:** `System.out.println(food);`  
  * **O que faz:** Imprime a comida atual da volta e pula para a linha seguinte.  
  * **Console:** Imprime `pizza`, depois `taco`, depois `hamburger`.

* **Linha 17:** `}`  
  * **O que faz:** Fecha o laço `for-each`.

* **Linha 18:** *(Linha em branco).*  
  * **Console:** Nada.

* **Linha 19:** `scanner.close();`  
  * **O que faz:** Fecha o canal do `Scanner` para economizar memória e liberar o teclado do sistema operacional.  
  * **Console:** Nada.

* **Linha 20:** `}`  
  * **O que faz:** Fecha o método `main`. Fim da execução com sucesso!  
  * **Console:** `Process finished with exit code 0`.

* **Linha 21:** `}`  
  * **O que faz:** Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Exatamente (Imagem 4):

```text
Enter a food: pizza
Enter a food: taco
Enter a food: hamburger
pizza
taco
hamburger

Process finished with exit code 0
```

---

## 💡 O Gran Finale Que Vem a Seguir no Vídeo:
O nosso programa já aceita que o usuário digite as comidas!  
Mas repare: o tamanho do array ainda está cravado em **`3`** pelo programador (`new String[3]`).

Se o usuário tiver 5 comidas favoritas, ele não consegue cadastrar 5!  
No próximo print, o professor vai dar o toque final de mestre:
1. Perguntar ao usuário: *"Quantas comidas você quer cadastrar?"*.
2. Guardar a resposta em uma variável `size`: `int size = scanner.nextInt();`.
3. Criar o array sob medida: `String[] foods = new String[size];`!
4. E ensinar como desarmar a famosa pegadinha do `scanner.nextLine()` que acontece ao misturar números com textos!
