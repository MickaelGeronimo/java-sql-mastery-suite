# ➡️ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 32: Inserir Entrada do Usuário em um Array (`enter user input into an array`) — Parte 6: Aumentando a Capacidade para 4 (`new String[4]`) e o Superpoder do `foods.length` Dinâmico
**Timestamps do vídeo:** `5:17:15` até `5:17:39 / 12:00:00`  
**Tema:** O professor altera a capacidade do array de 3 para 4 (`new String[4]`), demonstra por que **NENHUM dos dois laços precisou ser alterado** graças ao uso da propriedade inteligente `foods.length`, cadastra uma quarta comida (`hotdog`) e faz a reflexão sobre o próximo grande passo: permitir que o usuário defina o tamanho do array.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está a descrição fotográfica exata de cada um dos momentos do vídeo:

### 1. Imagem 1 (`media_1791241908515.png`): A Alteração de 1 Caractere no Editor
* **O Que o Professor Fez na Linha 8:**
  * O professor dá um duplo clique no número `3` dentro dos colchetes e digita `4`:
    ```java
    String[] foods = new String[4];
    ```
  * O número `4` aparece selecionado em azul-ciano.
* **Repare nas Linhas 10 e 15 (Os Laços `for`):**
  * O laço de entrada continua exatamente assim:
    ```java
    for(int i = 0; i < foods.length; i++){ ... }
    ```
  * E o laço de saída continua exatamente assim:
    ```java
    for(String food : foods){ ... }
    ```
  * O professor aponta para a tela e diz: *"Viram isso? Eu só mexi em um único número na Linha 8! Eu não precisei mudar NADA dentro dos dois laços de repetição!"*.

---

### 2. Imagem 2 (`media_1791241929016.png` — Timestamp `5:17:39`): A Execução com 4 Comidas
* **A Execução ao Vivo:**
  * O professor clica no Play verde.
  * O console abre e, de forma 100% automática, o programa faz **4 perguntas seguidas**:
    * Pergunta 1: `Enter a food: pizza`
    * Pergunta 2: `Enter a food: taco`
    * Pergunta 3: `Enter a food: hamburger`
    * Pergunta 4: `Enter a food: hotdog`
* **A Saída da Lista Completa:**
  * Logo abaixo das 4 perguntas, o laço `for-each` imprime todas as 4 comidas em verde brilhante:
    ```text
    pizza
    taco
    hamburger
    hotdog
    ```
  * O processo encerra perfeitamente: `Process finished with exit code 0`.

---

## 🧠 Por Que o Professor Fez Essa Mudança de 3 para 4? (A Mágica do `foods.length`)

Para quem é iniciante amador ("o lezado do Java"), por que isso é tão importante que mereceu uma cena exclusiva no vídeo do Bro Code?

### A Comparação Entre o Código Amador e o Código Profissional:

#### ❌ O Jeito Amador e "Engessado" (Hardcoded):
Se você tivesse escrito o laço `for` cravando o número 3 no código:
```java
// JEITO RUIM:
for(int i = 0; i < 3; i++){ ... }
```
Se amanhã você mudasse o array para `new String[4]`, o laço continuaria rodando apenas 3 vezes! A quarta comida (`hotdog`) ficaria de fora, abandonada com o valor `null` na memória, e você teria que caçar onde no código estava o número 3 para trocar por 4!

#### ✅ O Jeito Inteligente e Dinâmico com `.length`:
Quando você escreve:
```java
for(int i = 0; i < foods.length; i++){ ... }
```
A cada volta, o Java pergunta diretamente para o próprio array:  
> *"Ei array, qual é o seu tamanho atual?"*

* Se o array tem 3 vagas $\rightarrow$ O laço roda 3 vezes.
* Se o array tem 4 vagas $\rightarrow$ O laço roda 4 vezes.
* Se o array tiver 1.000 vagas $\rightarrow$ O laço roda 1.000 vezes sem você mexer em uma única vírgula do laço!

---

## 🚌 A Metáfora do Micro-ônibus e do Cobrador

Imagine uma linha de micro-ônibus:
1. No início do dia, o micro-ônibus tinha **3 poltronas** (`new String[3]`).
2. O cobrador (o laço `for`) tem uma regra de ouro simples na cabeça:  
   *"Enquanto houver assento vago no veículo (`i < foods.length`), chame o próximo passageiro para entrar!"*.
3. À tarde, a empresa troca o veículo por uma van maior de **4 poltronas** (`new String[4]`).
4. O cobrador precisa mudar de emprego ou reaprender a trabalhar? **Não!** Ele simplesmente continua chamando passageiros até preencher os 4 lugares!
   * Entra a Pizza (Poltrona 0)
   * Entra o Taco (Poltrona 1)
   * Entra o Hambúrguer (Poltrona 2)
   * Entra o Cachorro-quente / Hotdog (Poltrona 3)

```text
ESTADO ATUAL DO ARRAY FOODS NA MEMÓRIA RAM (HEAP):

           [0]             [1]             [2]             [3]
     ┌─────────────┬─────────────┬─────────────┬─────────────┐
     │   "pizza"   │   "taco"    │ "hamburger" │  "hotdog"   │
     └─────────────┴─────────────┴─────────────┴─────────────┘
     Tamanho total: foods.length = 4
```

---

## 💻 Código-Fonte Completo da Imagem

Copie este código para rodar no seu IntelliJ IDEA:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String[] foods = new String[4];

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

* **Linha 1:** `import java.util.Scanner;` $\rightarrow$ Importa o leitor de teclado. *(Console: Nada)*
* **Linha 2:** *(Linha em branco).*
* **Linha 3:** `public class Main {` $\rightarrow$ Casca da classe. *(Console: Nada)*
* **Linha 4:** `public static void main(String[] args){` $\rightarrow$ Ponto inicial. *(Console: Nada)*
* **Linha 5:** *(Linha em branco).*
* **Linha 6:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Liga o leitor de teclado. *(Console: Nada)*
* **Linha 7:** *(Linha em branco).*
* **Linha 8:** `String[] foods = new String[4];`  
  * **O que faz:** Reserva 4 gavetas para textos na memória Heap.  
  * **Memória RAM:** Cria o array `[null, null, null, null]`.  
  * **Console:** Nada nessa linha.
* **Linha 9:** *(Linha em branco).*
* **Linha 10:** `for(int i = 0; i < foods.length; i++){`  
  * **O que faz:** Como `foods.length` vale 4, o contador `i` vai assumir os valores `0, 1, 2, 3`.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 11:** `System.out.print("Enter a food: ");`  
  * **O que faz:** Imprime a pergunta sem quebrar linha.  
  * **Console:** `Enter a food: `
* **Linha 12:** `foods[i] = scanner.nextLine();`  
  * **O que faz:** Pausa esperando você digitar e salva na gaveta `i`.  
  * **1ª Volta (`i = 0`):** Salva `"pizza"` em `foods[0]`.  
  * **2ª Volta (`i = 1`):** Salva `"taco"` em `foods[1]`.  
  * **3ª Volta (`i = 2`):** Salva `"hamburger"` em `foods[2]`.  
  * **4ª Volta (`i = 3`):** Salva `"hotdog"` em `foods[3]`.
* **Linha 13:** `}` $\rightarrow$ Fecha o laço de entrada.
* **Linha 14:** *(Linha em branco).*
* **Linha 15:** `for(String food : foods){`  
  * **O que faz:** Laço de saída que percorre os 4 elementos.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 16:** `System.out.println(food);`  
  * **O que faz:** Imprime cada comida e pula de linha.  
  * **Console:** Imprime `pizza`, `taco`, `hamburger` e `hotdog`.
* **Linha 17:** `}` $\rightarrow$ Fecha o laço de saída.
* **Linha 18:** *(Linha em branco).*
* **Linha 19:** `scanner.close();` $\rightarrow$ Desliga o leitor de teclado.
* **Linha 20:** `}` $\rightarrow$ Fecha o método `main`.  
  * **Console:** `Process finished with exit code 0`.
* **Linha 21:** `}` $\rightarrow$ Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Exatamente (Imagem 2):

```text
Enter a food: pizza
Enter a food: taco
Enter a food: hamburger
Enter a food: hotdog
pizza
taco
hamburger
hotdog

Process finished with exit code 0
```

---

## 💡 A Provocação do Professor (O Que Falta Para Ficar Perfeito?):

Repare que, embora o nosso código tenha ficado muito mais inteligente com `foods.length`, o número **`4`** ainda foi digitado manualmente por nós (os programadores) no código-fonte (`new String[4]`).

Se o usuário final que vai usar o seu programa quiser cadastrar **10 comidas**, ele não tem como abrir o IntelliJ e editar o código Java!

### O Próximo Nível da Programação:
O professor vai fazer a pergunta de ouro ao usuário antes de criar o array:
```text
How many foods do you want? (Quantas comidas você deseja?)
```
Se o usuário digitar `10`, o programa cria um array de tamanho 10 na hora! Se digitar `2`, cria um array de tamanho 2!  
E para isso, o professor vai nos ensinar a capturar a variável `size` com `scanner.nextInt()` e desviar da famosa armadilha da quebra de linha solta!
