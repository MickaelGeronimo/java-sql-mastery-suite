# ➡️ Capítulo 32: O Guia Definitivo e Completo de Inserir Entrada do Usuário em um Array (`enter user input into an array`) — Partes 1 a 7

> **Documento Oficial de Estudos para Cópia no Google Docs:**  
> Este material foi construído para ser **100% autossuficiente**. Ele reconstitui todas as imagens, telas do IntelliJ IDEA, mensagens do terminal e explicações do professor **Bro Code** (*Java Full Course for free*), linha por linha, com linguagem simples e acessível para iniciantes, analogias do cotidiano, tabelas de memória e códigos Java completos e compiláveis sem erros.

---

## 📑 Índice da Apostila

1. [Visão Geral: O Desafio de Guardar Dados do Usuário em Arrays](#1-visao-geral)
2. [Contexto Visual Cronológico de Todas as Telas do Vídeo (Partes 1 a 7)](#2-contexto-visual)
3. [A Ilusão do Iniciante: O Teste do Array Vazio `{}` e `foods.length = 0`](#3-array-vazio)
4. [O Desastre ao Vivo: `foods[0] = "pizza";` e o Erro `ArrayIndexOutOfBoundsException`](#4-erro-bounds)
5. [A Cura com `new`: Pré-Alocação de Capacidade (`new String[3]`)](#5-cura-new)
6. [Preenchimento Manual e a Leitura Elegante com `for-each`](#6-for-each)
7. [Conectando o `Scanner` e o Laço de Entrada com `foods[i]`](#7-scanner-e-for)
8. [O Superpoder do `foods.length` Dinâmico](#8-foods-length)
9. [O Tamanho Dinâmico (`size`) e a Famosa Armadilha do Buffer do Scanner](#9-bug-buffer)
10. [Código-Fonte Completo, Comentado e Compilável de Todo o Capítulo 32](#10-codigo-completo)
11. [Checklist Final de Aprendizado do Capítulo 32](#11-checklist)

---

<a name="1-visao-geral"></a>
## ➡️ 1. Visão Geral: O Desafio de Guardar Dados do Usuário em Arrays

No Capítulo 31, aprendemos a criar arrays com valores já prontos, como:
```java
String[] fruits = {"apple", "orange", "banana", "coconut"};
```
Mas em aplicativos reais (jogos, cardápios, listas de compras, formulários), os dados **NÃO vêm prontos no código**! Quem digita os dados é o **usuário final** pelo teclado através do `Scanner`.

Neste Capítulo 32, o professor Bro Code responde à pergunta:
> *"Como permitir que o usuário digite a quantidade de itens que deseja e preencha um array pelo teclado sem quebrar o programa?"*

---

<a name="2-contexto-visual"></a>
## 🖼️ 2. Contexto Visual Cronológico de Todas as Telas do Vídeo (Partes 1 a 7)

Para quem estuda pelo Google Docs sem os prints ao lado, veja o filme completo do que aconteceu na tela do professor:

1. **A Folha em Branco (Minuto 5:12:35):** O professor abre o `Main.java` limpo.
2. **O Teste do Array Vazio `{}`:** Digita `String[] foods = {};` e manda imprimir `foods.length`. O console imprime **`0`**.
3. **O Desastre ao Vivo:** Digita `foods[0] = "pizza";` e aperta Play. O Java trava em vermelho com:
   `Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0` (`exit code 1`).
4. **A Correção com `new String[3]`:** Substitui `{}` por `new String[3]`. O programa roda sem erro, imprime `3` e termina com `exit code 0`.
5. **Preenchimento Manual e `for-each` (Minuto 5:14:55 a 5:15:14):** Preenche as 3 gavetas com `"pizza"`, `"taco"` e `"hamburger"` e usa `for(String food : foods)` para imprimir tudo no terminal.
6. **Entrada Interativa com Scanner (Minuto 5:17:01):** Liga o `Scanner` e cria o laço clássico `for(int i = 0; i < foods.length; i++)` com `foods[i] = scanner.nextLine();`. O usuário digita as comidas e elas aparecem na tela.
7. **O Teste com 4 Vagas (Minuto 5:17:39):** Muda apenas `new String[4]`. Os laços se adaptam sozinhos e cadastram 4 comidas (`pizza`, `taco`, `hamburger`, `hotdog`).
8. **Tamanho Dinâmico e o Bug do Buffer (Minuto 5:19:12 a 5:20:00):** Pergunta `What # of food do you want?: ` com `size = scanner.nextInt()`. No primeiro teste com `size = 1`, o programa pula a pergunta e encerra sozinho! O professor explica o bug do `\n` solto na esteira do teclado, adiciona `scanner.nextLine();` para limpar o buffer, roda de novo com `size = 5` e cadastra 5 comidas com perfeição.

---

<a name="3-array-vazio"></a>
## 🧱 3. A Ilusão do Iniciante: O Teste do Array Vazio `{}` e `foods.length = 0`

Todo iniciante pensa:
> *"Vou criar um array com chaves vazias `String[] foods = {};` e conforme o usuário for digitando, o array vai crescendo sozinho como uma sacola plástica!"*

### A Realidade do Java: A Forma de Gelo de Pedra
No Java, um array tradicional **NÃO É uma sacola elástica**! Ele é como uma **forma de gelo esculpida em pedra pura**:
* O tamanho com que ele nasce é o tamanho com que ele morre. Ele nunca estica e nunca encolhe.
* `String[] foods = {};` pede uma forma com **ZERO gavetas**.
* Por isso, `System.out.println(foods.length);` imprime **`0`**.

---

<a name="4-erro-bounds"></a>
## 💥 4. O Desastre ao Vivo: `foods[0] = "pizza";` e o Erro `ArrayIndexOutOfBoundsException`

Ao tentar executar:
```java
String[] foods = {};
foods[0] = "pizza"; // ❌ CRASH FATAL!
```

O console explode em vermelho:
```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
	at Main.main(Main.java:7)

Process finished with exit code 1
```

### A Metáfora do Motoboy no Terreno Baldio 🍕🛵
1. Você comprou um terreno vazio sem nenhum prédio construído (`foods = {};`).
2. Pediu uma pizza no iFood e disse: *"Entregue no Apartamento 0!"* (`foods[0] = "pizza";`).
3. O motoboy chega no local, só vê mato e terra, fica furioso porque não existe apartamento 0 e joga a pizza no chão!
4. O programa encerra com **`exit code 1`** (falha com morte prematura).

---

<a name="5-cura-new"></a>
## 🪚 5. A Cura com `new`: Pré-Alocação de Capacidade (`new String[3]`)

Para consertar, você precisa contratar o marceneiro oficial do Java (a palavra mágica **`new`**) e encomendar o móvel com vagas reais:
```java
String[] foods = new String[3];
```

* **`new`:** Manda reservar espaço físico novo na memória Heap.
* **`String`:** Define que as gavetas só aceitam textos.
* **`[3]`:** Constrói exatamente 3 compartimentos numerados: `[0]`, `[1]` e `[2]`.

As gavetas que ainda não receberam valor nascem preenchidas com o valor padrão: **`null`** (vazio).  
Agora, `foods[0] = "pizza";` funciona com perfeição e o programa termina em paz com **`exit code 0`**!

---

<a name="6-for-each"></a>
## 🍽️ 6. Preenchimento Manual e a Leitura Elegante com `for-each`

Ao preencher todas as 3 vagas:
```java
String[] foods = new String[3];

foods[0] = "pizza";
foods[1] = "taco";
foods[2] = "hamburger";
```

> 🛑 **Atenção:** Em um array de tamanho $N$, o último índice válido é sempre $N - 1$. Em um array de tamanho 3, os índices são `0`, `1` e `2`. A gaveta `[3]` **NÃO EXISTE**!

### O Laço `for-each` (A Metáfora do Garçom com a Bandeja):
```java
for(String food : foods){
    System.out.println(food);
}
```
* `foods`: A mesa inteira com todos os pratos.
* `food`: A bandeja que segura um prato por vez a cada volta.
* `:`: Lê-se "dentro de" (*in*).
* O console imprime:
  ```text
  pizza
  taco
  hamburger
  ```

---

<a name="7-scanner-e-for"></a>
## ⌨️ 7. Conectando o `Scanner` e o Laço de Entrada com `foods[i]`

Para permitir que o usuário digite pelo teclado, usamos **DOIS laços** com papéis totalmente diferentes:

1. **Laço 1 (For clássico com índice `i`): O CARTEIRO (Escrita)**
   * Você precisa saber o número da gaveta para guardar a carta: `foods[i] = scanner.nextLine();`.
   * **Você NUNCA pode usar `for-each` para preencher um array**, porque ele não te dá o índice `i`!
2. **Laço 2 (For-each sem índice): O MORADOR (Leitura)**
   * Apenas percorre as gavetas já cheias para exibir na tela: `System.out.println(food);`.

---

<a name="8-foods-length"></a>
## 🚀 8. O Superpoder do `foods.length` Dinâmico

Se você alterar o tamanho do array de 3 para 4:
```java
String[] foods = new String[4];
```
Nenhum dos dois laços precisa ser modificado! Como usamos `i < foods.length`, o laço pergunta o tamanho diretamente ao array e se adapta sozinho, cadastrando as 4 comidas (`pizza`, `taco`, `hamburger`, `hotdog`).

---

<a name="9-bug-buffer"></a>
## 👻 9. O Tamanho Dinâmico (`size`) e a Famosa Armadilha do Buffer do Scanner

Para fazer o programa ser 100% dinâmico, perguntamos ao usuário o tamanho:
```java
System.out.print("What # of food do you want?: ");
size = scanner.nextInt();
foods = new String[size];
```

### O Desastre da Imagem 2 (O Salto Invisível):
Quando o usuário digita `1` e aperta Enter, o console imprime:
```text
What # of food do you want?: 1
Enter a food: 

Process finished with exit code 0
```
O programa **fecha na hora sem deixar você digitar a comida**! Por quê?

### A Metáfora da Esteira do Supermercado 🛒:
* Ao digitar `1` e dar Enter, você coloca duas coisas na esteira do teclado: o número `1` e a quebra de linha `\n`.
* O comando `scanner.nextInt()` pega apenas o número `1` e **deixa o `\n` (Enter) jogado na esteira**!
* Em seguida, o laço chama `scanner.nextLine()`.
* O `nextLine()` encontra aquele `\n` solto na esteira, acha que você já deu Enter, lê um texto vazio `""` e encerra!

### 💊 O Remédio Sagrado (A Limpeza de Buffer):
Coloque um `scanner.nextLine();` solitário logo após o `nextInt()` para jogar o Enter solto no lixo:
```java
size = scanner.nextInt();
scanner.nextLine(); // 👈 FAXINEIRO! Limpa o \n da esteira!

foods = new String[size];
```

Agora a esteira fica limpa e o programa espera você digitar todas as comidas com calma!

---

<a name="10-codigo-completo"></a>
## 💻 10. Código-Fonte Completo e Compilável de Todo o Capítulo 32

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String[] foods;
        int size;

        // 1. Pergunta ao usuário quantas comidas ele deseja cadastrar
        System.out.print("What # of food do you want?: ");
        size = scanner.nextInt();
        scanner.nextLine(); // Limpeza essencial do buffer de entrada

        // 2. Cria o array sob medida com o tamanho exato escolhido pelo usuário
        foods = new String[size];

        // 3. Laço de ENTRADA (Escrita com índice i)
        for(int i = 0; i < foods.length; i++){
            System.out.print("Enter a food: ");
            foods[i] = scanner.nextLine();
        }

        System.out.println("\n--- SUAS COMIDAS CADASTRADAS ---");

        // 4. Laço de SAÍDA (Leitura elegante com for-each)
        for(String food : foods){
            System.out.println(food);
        }

        scanner.close();
    }
}
```

### 📺 Saída Exata no Console com `size = 5`:
```text
What # of food do you want?: 5
Enter a food: pizza
Enter a food: taco
Enter a food: hamburger
Enter a food: hotdog
Enter a food: sushi

--- SUAS COMIDAS CADASTRADAS ---
pizza
taco
hamburger
hotdog
sushi

Process finished with exit code 0
```

---

<a name="11-checklist"></a>
## 🎓 11. Checklist Final de Aprendizado do Capítulo 32

- [x] Sei por que `String[] foods = {};` tem tamanho 0 e não aceita novos dados.
- [x] Entendo a causa do erro `ArrayIndexOutOfBoundsException` e a diferença entre `exit code 0` e `exit code 1`.
- [x] Sei usar a palavra-chave `new` para pré-alocar vagas (`new String[tamanho]`).
- [x] Sei que as vagas não preenchidas de um array de Strings contêm o valor padrão `null`.
- [x] Sei por que usamos o laço clássico `for(int i...)` para **escrever** dados e o laço `for-each` para **ler**.
- [x] Sei por que `foods.length` é superior a colocar números fixos (*hardcoded*) na condição do laço.
- [x] Sei criar um array de tamanho dinâmico definido pelo usuário (`foods = new String[size]`).
- [x] Sei explicar o bug do buffer do Scanner e sei colocar o `scanner.nextLine();` de limpeza após qualquer `nextInt()`.
