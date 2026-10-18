# 🔎 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 33: Buscar em um Array (`search an array`) — Parte 3: Buscando Textos (`String[]`), o Mandamento Sagrado do `.equals()` vs `==` e o Resultado no Índice 1
**Timestamps do vídeo:** `5:25:15` até `~5:26:30 / 12:00:00`  
**Tema:** Evoluindo a busca linear de números para textos (`String[] fruits`), a mudança da variável `target` para `String`, a regra de ouro que separa iniciantes de profissionais (por que **NUNCA** comparar textos com `==` e usar obrigatoriamente `.equals()`), e a execução ao vivo encontrando `"orange"` no índice 1.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está a descrição fotográfica exata de cada um dos momentos do vídeo:

### 1. Imagem 1 (`media_1791242289499.png` — Timestamp `5:25:15`): A Transição no Editor
* **O Código no Editor (`Main.java`):**
  * O professor decide que já dominamos a busca com números e agora quer ensinar a buscar palavras.
  * Na **Linha 6**, ele adiciona um array de frutas:
    ```java
    String[] fruits = {"apple", "orange", "banana"};
    ```
  * Na **Linha 7**, o cursor do mouse seleciona a palavra `int` em:
    ```java
    int target = 7;
    ```
    *(A palavra `int` está com o fundo azul brilhante de seleção, pronta para ser trocada por `String`).*
  * **O Detalhe Sutil do IntelliJ IDEA:**
    * O array antigo `numbers` na Linha 5 ficou com o texto cinza-escuro e apagado. Isso é o editor avisando que ninguém mais está usando a variável `numbers` (*unused variable*).

---

### 2. Imagem 2 (`media_1791242336227.png` — Timestamp `~5:26:00`): O `.equals()` e o Índice 1
* **As Alterações Cirúrgicas Realizadas pelo Professor:**
  * **Na Linha 7:** Trocou o alvo para:
    ```java
    String target = "orange";
    ```
  * **Na Linha 10:** Mudou a condição do laço para olhar o tamanho das frutas:
    ```java
    for(int i = 0; i < fruits.length; i++){
    ```
  * **Na Linha 11 (O Ponto Crítico):** Ele **NÃO** usou `==`. Ele escreveu:
    ```java
    if(fruits[i].equals(target)){
    ```
* **A Execução ao Vivo no Terminal:**
  * O professor clica no Play verde.
  * A janela inferior abre e imprime em verde brilhante:
    ```text
    Element found at index: 1

    Process finished with exit code 0
    ```
* **A Explicação do Professor:**
  * O professor seleciona a Linha 11 com o mouse e faz um aviso solene:  
    *"Prestem muita atenção aqui! Quando você compara números inteiros, você usa `==`. Mas quando você compara objetos e textos em Java, você DEVE usar o método `.equals()`. Se você usar `==`, o Java vai comparar os endereços de memória e o seu programa vai falhar silenciosamente!"*.

---

## 🧠 Por Que NUNCA Podemos Usar `==` Para Textos em Java? (Para o Lezado do Java)

Esta é a pergunta número 1 das entrevistas de emprego e a pegadinha clássica das provas da faculdade:
> *"Por que `if(fruits[i] == target)` é considerado um erro grave em Java?"*

### 📖 A Metáfora dos Dois Livros Idênticos na Livraria:
Imagine que você entra em uma livraria e vê **dois exemplares novinhos** do mesmo livro do Harry Potter:
* Um livro está na prateleira da esquerda.
* O outro livro está na prateleira da direita.

Agora pense:
1. **Se você perguntar pelo CONTEÚDO (Método `.equals()`):**  
   *"As palavras, capítulos e histórias escritas dentro dos dois livros são iguais?"*  
   $\rightarrow$ **SIM!** O conteúdo é 100% idêntico! (Isso é o que o `.equals()` faz: compara letra por letra!).
2. **Se você perguntar pelo OBJETO FÍSICO (Operador `==`):**  
   *"Eles são a MESMÍSSIMA cópia física de papel ocupando o mesmo espaço no universo?"*  
   $\rightarrow$ **NÃO!** São dois livros físicos separados, em prateleiras diferentes! (Isso é o que o `==` faz: compara o endereço físico de memória RAM!).

```text
              STACK (Variáveis):                 HEAP (Memória Física):
              
              [ target ] ──────────────────► [ Endereço @100: "orange" ]
                                                         ▲
                                                         │ .equals() compara as letras: SÃO IGUAIS!
                                                         │ == compara os endereços: SÃO DIFERENTES!
                                                         ▼
              [ fruits[1] ] ───────────────► [ Endereço @200: "orange" ]
```

> 🛑 **REGRA DE OURO PARA TATUAR NO BRAÇO:**  
> * Para comparar números primitivos (`int`, `double`, `boolean`): use **`==`**.
> * Para comparar textos (`String`) ou qualquer objeto em Java: **SEMPRE USE `.equals()`**!

---

## 🔍 Como Foi a Execução Passo a Passo na Memória RAM?

Nosso array de frutas tem 3 elementos:
* `fruits[0]` = `"apple"`
* `fruits[1]` = `"orange"`
* `fruits[2]` = `"banana"`

Nosso alvo é: `target = "orange"`.

### O Laço em Câmera Lenta:

#### Volta 0 (`i = 0`):
* O Java pega `fruits[0]` que é `"apple"`.
* Pergunta: `"apple".equals("orange")`? **Falso!**
* Não entra no `if`. Faz `i++`.

#### Volta 1 (`i = 1`):
* O Java pega `fruits[1]` que é `"orange"`.
* Pergunta: `"orange".equals("orange")`? **VERDADEIRO!**
* Entra no `if`:
  1. Imprime no console: `"Element found at index: 1"`.
  2. Levanta a bandeira: `isFound = true;`.
  3. Bate no comando **`break;`** e encerra o laço imediatamente!
* O laço **nem chega a olhar** para a banana (`fruits[2]`), economizando tempo!

---

## 💻 Código-Fonte Completo da Imagem 2

Copie este código para rodar e testar no seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        String[] fruits = {"apple", "orange", "banana"};
        String target = "orange";
        boolean isFound = false;

        for(int i = 0; i < fruits.length; i++){
            if(fruits[i].equals(target)){
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
* **Linha 4:** `String[] fruits = {"apple", "orange", "banana"};`  
  * **O que faz:** Aloca um array de 3 Strings na memória Heap.  
  * **Memória RAM:**  
    * `fruits[0] = "apple"`  
    * `fruits[1] = "orange"`  
    * `fruits[2] = "banana"`  
  * **Console:** Nada nessa linha.
* **Linha 5:** `String target = "orange";`  
  * **O que faz:** Define a palavra que estamos procurando.  
  * **Memória RAM:** `target` aponta para a String `"orange"`.  
  * **Console:** Nada.
* **Linha 6:** `boolean isFound = false;`  
  * **O que faz:** A bandeira de alarme começa abaixada (`false`).  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco).*
* **Linha 8:** `for(int i = 0; i < fruits.length; i++){`  
  * **O que faz:** Inicia a busca linear percorrendo de 0 até 2 (`i < 3`).  
  * **Console:** Nada nessa linha diretamente.
* **Linha 9:** `if(fruits[i].equals(target)){`  
  * **O que faz:** Compara o conteúdo do texto da gaveta `i` com o alvo usando o método `.equals()`.  
  * **Volta 0:** `"apple".equals("orange")` $\rightarrow$ Falso.  
  * **Volta 1:** `"orange".equals("orange")` $\rightarrow$ **Verdadeiro!**  
  * **Console:** Nada.
* **Linha 10:** `System.out.println("Element found at index: " + i);`  
  * **O que faz:** Imprime o índice onde o item foi achado (índice 1).  
  * **Console:** `Element found at index: 1`
* **Linha 11:** `isFound = true;`  
  * **O que faz:** Levanta a bandeira para avisar que a busca foi um sucesso.  
  * **Memória RAM:** `isFound` muda de `false` para `true`.  
  * **Console:** Nada.
* **Linha 12:** `break;`  
  * **O que faz:** Para o laço na hora e pula para a Linha 15.  
  * **Console:** Nada.
* **Linha 13:** `}` $\rightarrow$ Fecha o `if`.
* **Linha 14:** `}` $\rightarrow$ Fecha o laço `for`.
* **Linha 15:** *(Linha em branco).*
* **Linha 16:** `if(!isFound){`  
  * **O que faz:** Testa se a bandeira continuou abaixada. Como `isFound` vale `true`, `!true` vira **`false`**! O bloco do aviso é ignorado com sucesso!  
  * **Console:** Nada.
* **Linha 17:** `System.out.println("Element not found in the array");`  
  * **O que faz:** Não é executado.
* **Linha 18:** `}` $\rightarrow$ Fecha o `if`.
* **Linha 19:** *(Linha em branco).*
* **Linha 20:** `}` $\rightarrow$ Fecha o método `main`.  
  * **Console:** `Process finished with exit code 0`.
* **Linha 21:** `}` $\rightarrow$ Fecha a classe `Main`.

---

## 📺 O Que Sai no Console Exatamente (Imagem 2):

```text
Element found at index: 1

Process finished with exit code 0
```

---

## 💡 O Que o Professor Vai Fazer a Seguir?

Agora você já é mestre em busca linear tanto com números (`==`) quanto com textos (`.equals()`)!  
Mas repare: a palavra `"orange"` ainda está cravada no código pelo programador.

O próximo passo lógico do professor no vídeo será ligar o **`Scanner`** para o usuário digitar a fruta que ele deseja procurar:
```text
Enter a fruit to search for: orange
Element found at index: 1
```
Se o usuário digitar uma fruta que não está na lista (como `"watermelon"` / melancia), o programa vai avisar com elegância:  
**`Element not found in the array`**!
