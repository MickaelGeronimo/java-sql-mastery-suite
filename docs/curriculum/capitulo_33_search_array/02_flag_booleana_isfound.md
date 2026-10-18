# 🔎 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 33: Buscar em um Array (`search an array`) — Parte 2: A Solução da Flag Booleana (`isFound`), o Operador NOT (`!`) e a Mensagem de Elemento Não Encontrado
**Timestamps do vídeo:** `5:23:05` até `5:24:25 / 12:00:00`  
**Tema:** A cura do console mudo! A técnica profissional da "Bandeirinha de Alarme" (*Boolean Flag* `boolean isFound`), a atribuição `isFound = true` ao encontrar o item, a verificação pós-laço com o operador de negação `if(!isFound)`, e a exibição bem-educada de `"Element not found in the array"`.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está a descrição fotográfica exata de cada um dos momentos do vídeo:

### 1. Imagem 1 (`media_1791242190418.png` — Timestamp `5:24:10`): A Construção da Bandeira
* **O Código no Editor (`Main.java`):**
  * O professor não quer que o console continue mudo quando um elemento não for achado.
  * Na **Linha 7**, ele cria a variável booleana:
    ```java
    boolean isFound = false;
    ```
    *(A palavra `isFound` está selecionada em azul pelo mouse).*
  * Na **Linha 12**, dentro do `if`, logo antes do `break`, ele adiciona:
    ```java
    isFound = true;
    ```
  * Na **Linha 17**, logo após o fechamento do laço `for`, ele abre uma nova condicional:
    ```java
    if(!isFound){

    }
    ```
  * **O Efeito Visual do IntelliJ IDEA:**
    * Como o professor deu um duplo clique em `isFound` na Linha 7, o editor iluminou com uma caixinha cinza-arroxeada as outras duas aparições de `isFound` (na Linha 12 e na Linha 17).
    * O cursor está na Linha 18, pronto para escrever o aviso de que o elemento não foi achado.

---

### 2. Imagem 2 (`media_1791242201862.png` — Timestamp `~5:24:25`): A Execução da Vitória
* **A Conclusão do Código:**
  * O professor completa a Linha 18 com a mensagem:
    ```java
    System.out.println("Element not found in the array");
    ```
* **A Execução ao Vivo com `target = 7`:**
  * O professor clica no Play verde mantendo `int target = 7;`.
  * Na aula passada, o console havia ficado completamente vazio e silencioso.
  * **Agora olhe o terminal:** Ele imprime em verde reluzente:
    ```text
    Element not found in the array

    Process finished with exit code 0
    ```
  * O professor aponta para a tela e diz: *"Viram a diferença? Agora o nosso programa conversa com o usuário! Se o número existir, ele diz em qual gaveta está. Se o número não existir, ele avisa com toda a clareza que o elemento não foi encontrado!"*.

---

## 🧠 A Metáfora do Vigia e da Bandeira Vermelha 🚩 (Para o Lezado do Java)

Para quem está começando em Java, o que é essa tal de **Flag Booleana** (*Bandeira Booleana*)?

Imagine um presídio onde um guarda está procurando um fugitivo chamado **"Número 7"** (`target = 7`):

```text
       ESTADO DA BUSCA COM A BANDEIRA BOOLEANA:
       
       Antes de começar:        O guarda entra com a bandeira ABAIXADA:
                                boolean isFound = false; 🚩⬇️
                                
       Durante a busca:         O guarda abre cela por cela (o laço for).
                                Se ele avistar o fugitivo:
                                isFound = true; 🚩⬆️ (LEVANTA A BANDEIRA!)
                                break; (Para de procurar e vai embora)
                                
       Depois da busca:         O delegado olha para o guarda:
                                "A bandeira continuou abaixada (!isFound)?"
                                Se SIM: "Element not found in the array!"
```

### O Que Acontece Quando o Alvo é o Número 7?
1. O guarda entra com a bandeira abaixada: `isFound = false`.
2. O laço `for` vasculha todas as 7 celas (`1, 9, 2, 8, 3, 5, 4`).
3. O número 7 **não está em nenhuma cela**!
4. Portanto, a Linha 12 (`isFound = true;`) **NUNCA FOI ACIONADA**!
5. O laço termina. O guarda sai das celas com a bandeira ainda **ABAIXADA (`false`)**.
6. O código chega na Linha 17: `if(!isFound)`.
7. Como a bandeira continua abaixada, o alarme toca e o Java imprime:  
   **`Element not found in the array`**!

---

## 🔬 O Segredo do Ponto de Exclamação: `if(!isFound)`

Muitos iniciantes olham para o ponto de exclamação `!` e ficam confusos:
> *"O que significa esse ponto de exclamação antes da palavra `isFound`?"*

### A Resposta: É o Operador de Negação (*NOT*)!
Em Java, a exclamação `!` inverte o valor de qualquer booleano:
* Se algo é `true`, `!true` vira **`false`**.
* Se algo é `false`, `!false` vira **`true`**!

Escrever:
```java
if(!isFound)
```
É o equivalente exato a escrever em português:  
> *"Se o elemento **NÃO** foi encontrado (ou seja, se `isFound == false`), execute o código abaixo!"*

---

## 💻 Código-Fonte Completo da Imagem 2

Copie este código para rodar no seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        int[] numbers = {1, 9, 2, 8, 3, 5, 4};
        int target = 7;
        boolean isFound = false;

        for(int i = 0; i < numbers.length; i++){
            if(target == numbers[i]){
                System.out.println("Element found at index: " + i);
                isFound = true;
                break;
            }
        }

        if(!isFound){
            System.out.println("Element not found in the array");
        }

    }
}
```

---

## 🔍 Enumeração Linha por Linha (Com Memória RAM e Console)

* **Linha 1:** `public class Main {` $\rightarrow$ Abre a classe. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` $\rightarrow$ Ponto inicial. *(Console: Nada)*
* **Linha 3:** *(Linha em branco).*
* **Linha 4:** `int[] numbers = {1, 9, 2, 8, 3, 5, 4};`  
  * **O que faz:** Aloca o array de 7 números na Heap.  
  * **Console:** Nada.
* **Linha 5:** `int target = 7;`  
  * **O que faz:** Define o alvo procurado (7).  
  * **Memória RAM:** `target = 7`.  
  * **Console:** Nada.
* **Linha 6:** `boolean isFound = false;`  
  * **O que faz:** Cria a bandeira de controle com o valor inicial falso.  
  * **Memória RAM:** `isFound = false`.  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco).*
* **Linha 8:** `for(int i = 0; i < numbers.length; i++){`  
  * **O que faz:** Inicia a busca linear do índice 0 ao 6.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 9:** `if(target == numbers[i]){`  
  * **O que faz:** Compara 7 com cada número. Como nenhum número é 7, o teste dá falso em todas as 7 voltas!  
  * **Console:** Nada.
* **Linha 10:** `System.out.println("Element found at index: " + i);`  
  * **O que faz:** NÃO é executado (pois 7 não existe).
* **Linha 11:** `isFound = true;`  
  * **O que faz:** NÃO é executado! A variável `isFound` continua valendo `false`.
* **Linha 12:** `break;`  
  * **O que faz:** NÃO é executado.
* **Linha 13:** `}` $\rightarrow$ Fecha o `if`.
* **Linha 14:** `}` $\rightarrow$ Fecha o laço `for`.
* **Linha 15:** *(Linha em branco).*
* **Linha 16:** `if(!isFound){`  
  * **O que faz:** Testa se a bandeira continuou abaixada. Como `isFound` é `false`, `!false` vira **`true`**! O bloco é executado!  
  * **Console:** Nada nessa linha.
* **Linha 17:** `System.out.println("Element not found in the array");`  
  * **O que faz:** Imprime o aviso amigável de que o item não foi encontrado.  
  * **Console:** Imprime `Element not found in the array`.
* **Linha 18:** `}` $\rightarrow$ Fecha o `if`.
* **Linha 19:** *(Linha em branco).*
* **Linha 20:** `}` $\rightarrow$ Fecha o método `main`.  
  * **Console:** `Process finished with exit code 0`.
* **Linha 21:** `}` $\rightarrow$ Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Exatamente (Imagem 2):

```text
Element not found in the array

Process finished with exit code 0
```

---

## ⚖️ A Tabela Comparativa dos Dois Cenários

Veja como o mesmo código reage com perfeição a qualquer situação:

| Alvo Procurado | O que acontece dentro do laço? | Valor final de `isFound` | O que sai no Console? |
| :---: | :--- | :---: | :--- |
| **`target = 2`** | Acha no índice 2, imprime e dá `break`. | **`true`** | `Element found at index: 2` *(O `!isFound` é falso e não imprime nada).* |
| **`target = 7`** | Roda todas as 7 voltas e nunca entra no `if`. | **`false`** | `Element not found in the array` *(O `!isFound` é verdadeiro e imprime o aviso).* |

---

## 💡 O Que o Professor Vai Mostrar no Próximo Print?

Até agora, fizemos a busca linear com um array de **números inteiros** (`int[]`).  
Mas e se você estiver construindo uma loja virtual ou um jogo e precisar buscar em um array de **palavras / textos** (`String[]`), como nomes de frutas ou de comidas?

Lembre-se: em Java, para comparar textos, você **NÃO PODE** usar `==`! Você tem que usar `.equals()`!  
Essa é a próxima grande aula que o professor vai iniciar a seguir!
