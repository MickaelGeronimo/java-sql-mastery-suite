# ➡️ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 32: Inserir Entrada do Usuário em um Array (`enter user input into an array`) — Parte 7: Tamanho Dinâmico (`size`), a Famosa Armadilha do Buffer do Scanner e a Vitória Final com 5 Comidas!
**Timestamps do vídeo:** `5:18:00` até `5:20:00 / 12:00:00`  
**Tema:** A consagração do capítulo! Como permitir que o usuário defina o tamanho do array dinamicamente (`foods = new String[size]`), a demonstração ao vivo da famosa armadilha do `scanner.nextLine()` pulando a entrada (o bug do Enter solto no buffer), a cura com a limpeza de buffer e a execução perfeita com 5 comidas (`pizza`, `taco`, `hamburger`, `hotdog`, `sushi`).

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está a reconstituição fotográfica e cronológica exata do que o professor **Bro Code** fez na tela:

### 1. Imagem 1 (`media_1791241960480.png` — Timestamp `5:19:12`): A Reformulação do Código
* **O Código no Editor:**
  * O professor não quer mais o número 3 nem 4 fixos no código.
  * Na Linha 7, ele declara o array sem tamanho:
    ```java
    String[] foods;
    ```
  * Na Linha 8, cria uma variável inteira para guardar a resposta do usuário:
    ```java
    int size;
    ```
  * Nas Linhas 10 e 11, pergunta a quantidade desejada:
    ```java
    System.out.print("What # of food do you want?: ");
    size = scanner.nextInt();
    ```
  * Na Linha 13, instancia o array com o tamanho que o usuário acabou de digitar:
    ```java
    foods = new String[size];
    ```
  * O cursor do mouse pisca na Linha 11 após o ponto e vírgula.

---

### 2. Imagem 2 (`media_1791241975749.png`): O Flagrante da Famosa Armadilha do Scanner!
* **A Execução do Teste:**
  * O professor clica no Play verde para testar.
  * O programa pergunta: `What # of food do you want?: ` e o professor digita `1` e aperta **Enter**.
* **O Que Aconteceu no Console (O Mistério):**
  ```text
  What # of food do you want?: 1
  Enter a food: 

  Process finished with exit code 0
  ```
* **O Choque do Aluno:**
  * O programa imprimiu `Enter a food: ` e **encerrou imediatamente com exit code 0**, sem deixar o professor digitar nenhuma comida!
  * O professor aponta para a tela e pergunta com ironia:  
    *"Ué? O que aconteceu aqui?! Por que o programa pulou a pergunta da comida e fechou sozinho sem me dar chance de digitar?!"*.

---

### 3. Imagem 3 (`media_1791242001891.png` — Timestamp `5:19:54`): A Cura do Buffer e o Teste com 5 Comidas
* **A Solução:**
  * O professor explica o motivo do salto e adiciona um `scanner.nextLine();` solitário logo após o `nextInt()` para limpar a quebra de linha que ficou presa na esteira do teclado.
* **A Nova Execução:**
  * Ele roda o código novamente e agora digita que deseja **`5`** comidas.
  * Desta vez, o Java **espera pacientemente** o usuário digitar cada uma das 5 comidas:
    ```text
    What # of food do you want?: 5
    Enter a food: pizza
    Enter a food: taco
    Enter a food: hamburger
    Enter a food: hotdog
    Enter a food: sushi
    ```

---

### 4. Imagem 4 (`media_1791242008947.png` — Timestamp `5:19:55`): A Saída Triunfal Completa
* **A Exibição Final no Console:**
  * Assim que o usuário termina de digitar a 5ª comida (`sushi`), o laço de entrada finaliza e o laço `for-each` imprime todas as comidas cadastradas:
    ```text
    pizza
    taco
    hamburger
    hotdog
    sushi

    Process finished with exit code 0
    ```
  * O professor comemora: *"Conseguimos! Agora o programa é 100% dinâmico: o usuário escolhe a quantidade e o usuário escolhe cada comida!"*.

---

## 👻 O Mistério Revelado: O Fantasma da Tecla Enter (O Maior Bug de Iniciantes em Java!)

Por que na Imagem 2 o programa imprimiu `Enter a food: ` e fechou na hora sem esperar você digitar?

### A Metáfora da Esteira do Caixa de Supermercado 🛒:
Pense no teclado do seu computador como uma esteira rolante de supermercado chamada **Buffer de Entrada**:

1. Quando o programa perguntou `What # of food do you want?: `, você digitou o número **`1`** e apertou a tecla **ENTER**.
2. Na esteira do computador, você colocou duas coisas:
   * O número `1`
   * A quebra de linha `\n` (o Enter).
3. O comando `scanner.nextInt()` é **míope para números**:
   * Ele pega o número `1` e vai embora feliz.
   * Mas ele **DEIXA O `\n` (ENTER) ESQUECIDO NA ESTEIRA**!
4. Logo em seguida, o laço `for` começa e chama `scanner.nextLine()`.
5. O trabalho do `scanner.nextLine()` é ler tudo até achar um Enter (`\n`).
6. Quando ele olha para a esteira, o que ele encontra imediatamente? **O `\n` que sobrou do `nextInt()`!**
7. O `nextLine()` pensa: *"Opa, já tem um Enter aqui! O usuário não digitou nada!"*.
8. Ele lê um texto vazio `""`, guarda esse vazio em `foods[0]` e encerra! Você nem teve chance de encostar no teclado!

```text
ESTEIRA DO TECLADO (BUFFER) APÓS scanner.nextInt():

           [ 1 ]        [ \n (Enter) ]
             │                 │
             ▼                 ▼
   Pego pelo nextInt()    FICOU PRESO NA ESTEIRA!
                               │
                               ▼
                      Pego acidentalmente pelo
                        scanner.nextLine()!
```

---

## 💊 O Remédio Sagrado: Como Limpar a Esteira?

Para consertar isso para sempre, você só precisa colocar um `scanner.nextLine();` solitário logo após o `scanner.nextInt();`:

```java
size = scanner.nextInt();
scanner.nextLine(); // 👈 O FAXINEIRO! Engole o \n solto e limpa a esteira!

foods = new String[size];
```

Esse `scanner.nextLine()` solitário atua como um **aspirador de pó**: ele engole aquele Enter invisível que sobrou e joga no lixo. Quando o laço `for` começar, a esteira estará 100% limpa e o programa vai esperar você digitar a sua comida com calma!

---

## 💻 Código-Fonte Definitivo Completo (Com a Limpeza de Buffer)

Copie este código para rodar no seu IntelliJ IDEA:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String[] foods;
        int size;

        System.out.print("What # of food do you want?: ");
        size = scanner.nextInt();
        scanner.nextLine(); // LIMPA O ENTER DO BUFFER!

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
* **Linha 3:** `public class Main {` $\rightarrow$ Casca da classe. *(Console: Nada)*
* **Linha 4:** `public static void main(String[] args){` $\rightarrow$ Ponto inicial. *(Console: Nada)*
* **Linha 5:** *(Linha em branco).*
* **Linha 6:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Cria o leitor do teclado. *(Console: Nada)*
* **Linha 7:** `String[] foods;`  
  * **O que faz:** Cria apenas a etiqueta de referência `foods` na Stack, sem alocar memória na Heap ainda.  
  * **Console:** Nada.
* **Linha 8:** `int size;`  
  * **O que faz:** Cria a variável inteira `size` para guardar a quantidade escolhida pelo usuário.  
  * **Console:** Nada.
* **Linha 9:** *(Linha em branco).*
* **Linha 10:** `System.out.print("What # of food do you want?: ");`  
  * **Console:** `What # of food do you want?: `
* **Linha 11:** `size = scanner.nextInt();`  
  * **O que faz:** Lê o número digitado (ex: `5`) e guarda na variável `size`. O `\n` fica no buffer.  
  * **Console:** Mostra o `5` digitado.
* **Linha 12:** `scanner.nextLine();`  
  * **O que faz:** **A LIMPEZA DO BUFFER.** Engole o `\n` residual e esvazia a esteira do teclado.  
  * **Console:** Nada.
* **Linha 13:** *(Linha em branco).*
* **Linha 14:** `foods = new String[size];`  
  * **O que faz:** **A INSTANCIAÇÃO DINÂMICA.** O Java vai até a memória Heap e aloca um array com exatamente `size` gavetas (no nosso teste: 5 gavetas: `[null, null, null, null, null]`).  
  * **Console:** Nada nessa linha.
* **Linha 15:** *(Linha em branco).*
* **Linha 16:** `for(int i = 0; i < foods.length; i++){`  
  * **O que faz:** Laço que vai rodar de `0` até `size - 1` (voltas 0, 1, 2, 3, 4).  
  * **Console:** Nada nessa linha diretamente.
* **Linha 17:** `System.out.print("Enter a food: ");`  
  * **Console:** `Enter a food: `
* **Linha 18:** `foods[i] = scanner.nextLine();`  
  * **O que faz:** Lê a comida digitada e guarda na gaveta `i`.  
  * **Volta 0:** Guarda `"pizza"` em `foods[0]`.  
  * **Volta 1:** Guarda `"taco"` em `foods[1]`.  
  * **Volta 2:** Guarda `"hamburger"` em `foods[2]`.  
  * **Volta 3:** Guarda `"hotdog"` em `foods[3]`.  
  * **Volta 4:** Guarda `"sushi"` em `foods[4]`.
* **Linha 19:** `}` $\rightarrow$ Fecha o laço de entrada.
* **Linha 20:** *(Linha em branco).*
* **Linha 21:** `for(String food : foods){`  
  * **O que faz:** Percorre as 5 comidas guardadas.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 22:** `System.out.println(food);`  
  * **Console:** Imprime `pizza`, `taco`, `hamburger`, `hotdog` e `sushi`.
* **Linha 23:** `}` $\rightarrow$ Fecha o laço de saída.
* **Linha 24:** *(Linha em branco).*
* **Linha 25:** `scanner.close();` $\rightarrow$ Fecha o leitor de teclado.
* **Linha 26:** `}` $\rightarrow$ Fecha o `main`.  
  * **Console:** `Process finished with exit code 0`.
* **Linha 27:** `}` $\rightarrow$ Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Exatamente (Imagem 3 e 4):

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

## 🏆 Resumo Completo do Capítulo 32 para o seu Google Docs

1. **Arrays têm tamanho fixo:** Não adianta criar `String[] foods = {};` achando que ele vai crescer sozinho. Ele nasce com tamanho 0 e não aceita dados.
2. **Pré-alocação com `new`:** Para criar vagas, usamos `new String[tamanho]`.
3. **Dois laços, duas funções:**
   * Laço clássico com índice `i` (`for int i = 0...`) para **escrever** (`foods[i] = ...`).
   * Laço moderno `for-each` (`for String food : foods`) para **ler e exibir**.
4. **Tamanho dinâmico:** Perguntamos `size` com `scanner.nextInt()` e criamos `new String[size]`.
5. **Cuidado com a esteira:** Após ler um número com `nextInt()`, **SEMPRE** chame `scanner.nextLine()` para limpar a quebra de linha residual antes de ler textos!
