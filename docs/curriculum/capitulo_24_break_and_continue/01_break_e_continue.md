# 🚦 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 24: Comandos de Controle de Laço: `break` e `continue`
**Timestamps do vídeo:** `3:54:49` até `3:55:24 / 12:00:00`  
**Tema:** Controlando o fluxo dos laços: O freio de emergência `break` (STOP) vs. O comando de pular rodada `continue` (SKIP), e impressão horizontal com `System.out.print(i + " ")`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela durante estes minutos:

1. **Imagem 1 (Minuto 3:54:49):**
   * O professor inicia o capítulo **`24.break & continue >`**.
   * Ele escreve dois comentários resumindo a regra de ouro:
     `// break = break out of a loop (STOP)`  
     *(break = quebrar/sair fora de um laço - PARAR)*  
     `// continue = skip current iteration of a loop (SKIP)`  
     *(continue = pular a repetição atual de um laço - PULAR)*
   * Ele monta um laço `for` de 0 a 9 e coloca um teste com `break`:
     ```java
     for(int i = 0; i < 10; i++){
         if(i == 5){
             break;
         }
         System.out.print(i + " ");
     }
     ```
2. **Imagem 3 (Minuto 3:55:13):**
   * O professor altera a palavra dentro do `if`:
     Ele apaga `break;` e digita **`continue;`**:
     ```java
     if(i == 5){
         continue;
     }
     ```
   * Ele move o mouse até o botão verde de Play para executar o código com `continue`.
3. **Imagem 2 (Minuto 3:55:24):**
   * O console roda e exibe o resultado surpreendente na tela:
     ```text
     0 1 2 3 4 6 7 8 9
     ```
   * **Repare com atenção:** O número **5 sumiu**! Ele imprimiu 0, 1, 2, 3, 4, pulou o 5 como se ele não existisse, e continuou normalmente imprimindo 6, 7, 8 e 9!

---

## 🧠 A Diferença Mais Clara do Mundo: `break` vs. `continue`

Imagine que você está ouvindo uma **playlist de 10 músicas no Spotify**:

### 🛑 O `break` (O botão de DESLIGAR / PARADA TOTAL):
* É como se você estivesse ouvindo a playlist e, ao chegar na música 5, você **apertasse o botão de Parar e fechasse o aplicativo**.
* As músicas 5, 6, 7, 8, 9 e 10 **NUNCA serão tocadas**. A festa acabou ali!
* No código: O laço é **destruído na hora**, e o Java pula direto para depois da chave `}` do `for`.

### ⏭️ O `continue` (O botão de PULAR FAIXA / SKIP):
* É como se a música 5 fosse uma música chata que você odeia. Você simplesmente aperta o botão de **"Próxima Música" (Skip)**.
* O aplicativo **NÃO desliga**! Ele apenas cancela o resto da música 5 e pula direto para a música 6, 7, 8, 9...
* No código: O Java **ignora o restante daquela volta específica** e pula direto para a próxima volta (`i++`), mantendo o laço vivo!

---

## 🔍 O Detalhe Visual: Por que os números ficaram lado a lado?

Repare nesta linha:
```java
System.out.print(i + " ");
```
* **Não tem `ln`:** Ele usou `print` em vez de `println`. Lembra da diferença? O `println` pula linha após imprimir; o `print` escreve e **mantém o cursor na mesma linha**.
* **O `+ " "` (mais espaço):** Serve para dar um espaço em branco entre um número e outro. Se não tivesse o espaço, os números sairiam todos colados: `012346789`.

---

## 💻 PARTE 1: O Teste com `break` (Parada Total)

### Código:
```java
public class Main {
    public static void main(String[] args){

        // break = break out of a loop (STOP)
        // continue = skip current iteration of a loop (SKIP)

        for(int i = 0; i < 10; i++){

            if(i == 5){
                break;
            }

            System.out.print(i + " ");
        }

    }
}
```

### 📋 Tabela de Rastreio com `break`:

| Volta | Valor de `i` | Pergunta: `i == 5`? | Ação executada | O que sai no Console? |
| :---: | :---: | :---: | :---: | :---: |
| 1ª | `0` | `0 == 5`? Não | Imprime e dá espaço | `0 ` |
| 2ª | `1` | `1 == 5`? Não | Imprime e dá espaço | `0 1 ` |
| 3ª | `2` | `2 == 5`? Não | Imprime e dá espaço | `0 1 2 ` |
| 4ª | `3` | `3 == 5`? Não | Imprime e dá espaço | `0 1 2 3 ` |
| 5ª | `4` | `4 == 5`? Não | Imprime e dá espaço | `0 1 2 3 4 ` |
| 6ª | `5` | `5 == 5`? **SIM!** | **`break;` (Mata o laço!)** | *(Para tudo imediatamente)* |

### 📺 O que sai no Console com `break`:
```text
0 1 2 3 4 
```
*(Repare: no segundo em que `i` virou 5, o programa freou bruscamente e o laço terminou!).*

---

## 💻 PARTE 2: O Teste com `continue` (Pular a Rodada) — [Exibido nas Imagens 2 e 3]

### Código:
```java
public class Main {
    public static void main(String[] args){

        // break = break out of a loop (STOP)
        // continue = skip current iteration of a loop (SKIP)

        for(int i = 0; i < 10; i++){

            if(i == 5){
                continue;
            }

            System.out.print(i + " ");
        }

    }
}
```

### 📋 Tabela de Rastreio com `continue`:

| Volta | Valor de `i` | Pergunta: `i == 5`? | Ação executada | O que sai no Console? |
| :---: | :---: | :---: | :---: | :---: |
| 1ª | `0` | `0 == 5`? Não | Imprime | `0 ` |
| 2ª | `1` | `1 == 5`? Não | Imprime | `0 1 ` |
| 3ª | `2` | `2 == 5`? Não | Imprime | `0 1 2 ` |
| 4ª | `3` | `3 == 5`? Não | Imprime | `0 1 2 3 ` |
| 5ª | `4` | `4 == 5`? Não | Imprime | `0 1 2 3 4 ` |
| **6ª** | **`5`** | **`5 == 5`? SIM!** | **`continue;` (Pula o print!)** | *(O 5 é ignorado! Pula para `i++`)* |
| 7ª | `6` | `6 == 5`? Não | Imprime | `0 1 2 3 4 6 ` |
| 8ª | `7` | `7 == 5`? Não | Imprime | `0 1 2 3 4 6 7 ` |
| 9ª | `8` | `8 == 5`? Não | Imprime | `0 1 2 3 4 6 7 8 ` |
| 10ª| `9` | `9 == 5`? Não | Imprime | `0 1 2 3 4 6 7 8 9 ` |
| Fim| `10`| `10 < 10`? Não | Fim natural do laço | Programa encerra |

### 📺 O que sai no Console com `continue`:
```text
0 1 2 3 4 6 7 8 9 
```
*(O número 5 foi o único ignorado!).*

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

Vamos analisar linha por linha a versão com `continue` (que é a versão final do vídeo):

* **Linha 1:** `public class Main {`  
  * Cria a classe principal `Main`.  
  * **Console:** Nada.
* **Linha 2:** `public static void main(String[] args){`  
  * Método onde o Java inicia.  
  * **Console:** Nada.
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** *(Linha em branco)*
* **Linha 5:** `// break = break out of a loop (STOP)`  
  * Comentário: break serve para parar e sair do laço.  
  * **Console:** Nada.
* **Linha 6:** `// continue = skip current iteration of a loop (SKIP)`  
  * Comentário: continue serve para pular a rodada atual do laço.  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco)*
* **Linha 8:** `for(int i = 0; i < 10; i++){`  
  * Inicia o laço de 0 até menor que 10, de 1 em 1.  
  * **Console:** Nada nessa linha.
* **Linha 9:** *(Linha em branco)*
* **Linha 10:** `if(i == 5){`  
  * Testa se a variável `i` chegou no número 5.  
  * **Console:** Nada.
* **Linha 11:** `continue;`  
  * Se `i == 5`, executa o `continue`. O Java ignora a Linha 14 e pula direto para a próxima volta (`i++`), onde `i` vira 6!  
  * *(Se aqui estivesse `break;`, o laço seria destruído imediatamente).*  
  * **Console:** Nada.
* **Linha 12:** `}`  
  * Fecha o bloco do `if`.  
  * **Console:** Nada.
* **Linha 13:** *(Linha em branco)*
* **Linha 14:** `System.out.print(i + " ");`  
  * Imprime o número atual seguido de um espaço em branco, sem quebrar de linha. Só é alcançado se o `if` não tiver pulado a linha!  
  * **Console:** Imprime o número e um espaço na mesma linha horizontal.
* **Linha 15:** `}`  
  * Fim da volta do `for`. Executa `i++` e testa `i < 10`.  
  * **Console:** Nada.
* **Linha 16:** *(Linha em branco)*
* **Linha 17:** `}`  
  * Fecha o método `main`.  
  * **Console:** Finaliza com `Process finished with exit code 0`.
* **Linha 18:** `}`  
  * Fecha a classe `Main`.  
  * **Console:** Nada.

---

## 🎯 Tabela Resumo para Decorar e Nunca Mais Confundir

| Palavra-chave | O que faz na hora? | O que acontece com o laço? | Analogia |
| :---: | :---: | :---: | :---: |
| **`break`** | Cancela tudo e sai | **Mata o laço inteiro** | Puxar o freio de mão / Fechar o app |
| **`continue`** | Cancela apenas o resto da volta atual | **Continua na próxima volta** | Pular uma faixa na playlist / Passar a vez |
