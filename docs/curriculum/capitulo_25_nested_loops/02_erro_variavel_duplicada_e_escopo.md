# ➿ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 25: Laços Aninhados (`nested loops`) — Parte 2: O Erro Clássico da Variável Duplicada (`variable i is already defined`)
**Timestamps do vídeo:** `3:57:31` até `3:58:23 / 12:00:00`  
**Tema:** A primeira tentativa real de aninhar laços, a função do `System.out.println();` vazio (a tecla Enter), o erro em vermelho `variable i is already defined`, escopo de variáveis e a convenção universal de índices `i` e `j`.

---

## 🖼️ Contexto Visual dos Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está o passo a passo exato do que o professor **Bro Code** fez na tela:

1. **Minuto 3:57:31 (O Teste dos Três Laços com Enter):**
   * O professor pega os 3 laços manuais e coloca um `System.out.println();` vazio entre cada um deles.
   * Ele roda o código e o console finalmente exibe o que queremos:
     ```text
     1 2 3 4 5 6 7 8 9 
     1 2 3 4 5 6 7 8 9 
     1 2 3 4 5 6 7 8 9 
     ```
   * Três linhas com os números de 1 a 9 alinhados. Ele diz: *"Queremos exatamente essa matriz 3x9, mas feita com elegância e com laços aninhados de verdade!"*.
2. **Minuto 3:58:19 (Tentando Montar o Laço Aninhado):**
   * Ele apaga os 3 laços repetidos e tenta escrever:
     ```java
     for(int i = 1; i <= 3; i++){
         for(int i = 1; i <= 9; i++){
             System.out.print(i + " ");
         }
         System.out.println();
     }
     ```
   * **O Alerta Visual:** Imediatamente, o IntelliJ IDEA coloca uma linha ondulada vermelha embaixo do segundo `int i` na linha interna!
3. **Minuto 3:58:23 (A Explosão do Erro Vermelho de Compilação):**
   * O professor ignora o sublinhado vermelho e clica no Play verde para forçar a execução.
   * **O Java se recusa a rodar!** A janela inferior `Build Output` abre com a mensagem de erro:
     ```text
     java: variable i is already defined in method main(java.lang.String[])
     ```
   * *(Tradução oficial: Erro! A variável i já foi definida no método main).*
4. **Legenda Explicativa do Professor:**
   * O professor aponta com a seta do mouse para os dois `i` e coloca uma legenda na tela:
     `* declare the same index`  
     *(Você declarou o mesmo índice duas vezes!).*

---

## 🧠 Por Que Deu Esse Erro Vermelho? (A Metáfora dos Dois Irmãos)

Imagine que numa família nascem dois irmãos gêmeos. Os pais decidem batizar os dois meninos com o mesmíssimo nome: **`i`**.  
Os dois irmãos dormem no **mesmo quarto** (o bloco do método `main`).

Quando a mãe chega na porta do quarto e grita:  
> *"Ô `i`, venha cá agora!"*

O que acontece?  
Os dois meninos olham assustados um para o outro e ninguém sabe quem deve responder! A casa vira uma bagunça completa.

### O Que Aconteceu Dentro do Java:
1. **Na linha externa:** O laço de fora diz: `int i = 1;`. O Java cria uma caixinha na memória RAM chamada **`i`**. Essa caixinha permanece viva durante todo o tempo em que o laço externo estiver rodando.
2. **Na linha interna:** O laço de dentro tenta criar **OUTRA** caixinha inteira chamada **`i`**: `int i = 1;`.
3. O compilador Java trava na hora e grita:
   > *"Ei! Você já criou uma caixinha chamada `i` aqui dentro desse mesmo bloco de código! Eu não posso ter duas variáveis com o mesmo nome disputando o mesmo espaço!"*

Isso em computação se chama **Conflito de Escopo Local** (*Local Scope Conflict*).

---

## 🔤 Como os Programadores Resolvem Isso no Mundo Todo?

A regra é extremamente simples e adotada por desenvolvedores de Java, C, C++, Python e JavaScript no mundo inteiro:

* Para o **1º laço (o de fora - Linhas):** Usamos a letra **`i`** (de *Index* / Índice).
* Para o **2º laço (o de dentro - Colunas):** Olhamos para o alfabeto... quem vem logo depois do `i`? É a letra **`j`**!
* *(E se você fizesse um 3º laço dentro do segundo para um jogo 3D? Usaria a letra **`k`**!)*

Portanto:
* `i` = controla as Linhas (desce a folha).
* `j` = controla as Colunas (anda para os lados).

---

## ⌨️ Para Que Serve o `System.out.println();` Vazio? (A Tecla Enter)

Repare nesta linha isolada:
```java
System.out.println();
```
* **Não tem nada dentro dos parênteses:** Nem texto, nem número, nem variável.
* O comando `println` significa *"print line"* (imprima e pule uma linha).
* Como não foi passado nada para ele imprimir, ele simplesmente **empurra o cursor para o começo da linha de baixo**!
* É o equivalente exato a você apertar a tecla **Enter** no seu teclado ou puxar a alavanca de uma máquina de escrever antiga quando a linha acaba!

---

## 💻 Código Com Erro (Como o Professor Digitou no Vídeo)

```java
public class Main {
    public static void main(String[] args){

        // nested loop = A loop inside another loop
        //               Used often with matrices or DS&A

        for(int i = 1; i <= 3; i++){
            for(int i = 1; i <= 9; i++){ // ❌ ERRO AQUI! Não pode repetir 'int i'
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}
```

### 🔍 Enumeração Linha por Linha do Código com Erro:

* **Linha 1:** `public class Main {` $\rightarrow$ Casca da classe. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` $\rightarrow$ Início do programa. *(Console: Nada)*
* **Linhas 3 a 7:** Linhas em branco e comentários explicativos. *(Console: Nada)*
* **Linha 8:** `for(int i = 1; i <= 3; i++){`  
  * **O que faz:** Cria a variável `i` para contar as 3 linhas.  
  * **Memória RAM:** Gaveta `i = 1` criada com sucesso.  
  * **Console:** Nada.
* **Linha 9:** `for(int i = 1; i <= 9; i++){`  
  * ⚠️ **ERRO DE COMPILAÇÃO!** O Java tenta criar outra gaveta `int i`, mas `i` já existe na Linha 8!  
  * **Console (Build Output):**
    ```text
    java: variable i is already defined in method main(java.lang.String[])
    ```
* **Linha 10:** `System.out.print(i + " ");` $\rightarrow$ Nunca é executada porque a compilação foi cancelada.
* **Linha 11:** `}` $\rightarrow$ Fechamento do laço interno.
* **Linha 12:** `System.out.println();` $\rightarrow$ Nunca é executada.
* **Linha 13:** `}` $\rightarrow$ Fechamento do laço externo.
* **Linhas 14 e 15:** Fechamentos normais do Java.

---

## 🛠️ Como Fica o Código Corrigido com `j`?

Para consertar, basta trocar a variável do laço interno pela letra **`j`**:

```java
public class Main {
    public static void main(String[] args){

        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 9; j++){ // ✅ CORRIGIDO! Usando 'j' para o laço interno
                System.out.print(j + " ");
            }
            System.out.println(); // Pula para a próxima linha
        }

    }
}
```

### 📺 O que sai no Console com a Correção:
```text
1 2 3 4 5 6 7 8 9 
1 2 3 4 5 6 7 8 9 
1 2 3 4 5 6 7 8 9 

Process finished with exit code 0
```

---

## 🎯 Resumo da Parte 2 para o seu Google Docs

| Conceito Aprendido | Por que funciona assim? |
| :--- | :--- |
| **Nunca declare `int i` nos dois laços** | Variáveis declaradas no mesmo bloco não podem ter nomes idênticos (conflito de escopo). |
| **Use `i` fora e `j` dentro** | Padrão mundial da programação para loops aninhados. |
| **O `System.out.println();` vazio** | É a tecla **Enter**: posiciona o cursor no início da linha de baixo para a próxima rodada. |
