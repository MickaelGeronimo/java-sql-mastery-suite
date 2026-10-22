# 🔂 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 23: Laços de Repetição FOR (`for loop`) — Parte 2: Contagem Regressiva e Saltos
**Timestamps do vídeo:** `3:46:51` até `3:47:55 / 12:00:00`  
**Tema:** Marcha ré com `i--` (contagem regressiva), saltos personalizados para frente com `i += 2` (números ímpares) e saltos para trás com `i -= 3`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 3:46:51):**
   * O professor quer ensinar como contar **de trás para frente** (como no lançamento de um foguete: 10, 9, 8... 1).
   * Ele altera a linha do `for` para:
     ```java
     for(int i = 10; i > 0; i--){
         System.out.println(i);
     }
     ```
   * No console, aparecem os números descendo:
     `10, 9, 8, 7, 6, 5, 4, 3, 2, 1`.
2. **Imagem 2 (Minuto ~3:47:15):**
   * O professor agora mostra que você não precisa andar de 1 em 1. Você pode dar **saltos largos**!
   * Ele escreve:
     ```java
     for(int i = 1; i <= 10; i+=2){
         System.out.println(i);
     }
     ```
   * O `i+=2` faz o contador somar 2 a cada volta. O laço começa no 1, depois vai para 3, 5, 7, 9.
3. **Imagem 3 (Minuto 3:47:55):**
   * O professor junta os dois conceitos: **contar para trás dando saltos largos**!
   * Ele escreve:
     ```java
     for(int i = 10; i > 0; i-=3){
         System.out.println(i);
     }
     ```
   * O console imprime:
     `10`  
     `7`  
     `4`  
     `1`  
     E para perfeitamente quando o próximo número seria `-2`.

---

## 🧠 A Grande Revelação: O `for` tem Marcha Ré e Saltos!

Até agora, você viu o `for` subindo a escada um degrau por vez (`i++`).  
Mas as 3 engrenagens do `for` são totalmente livres! Você pode:
1. **Andar para trás:** Usando `i--` (diminui 1 por volta).
2. **Pular degraus para frente:** Usando `i += 2` (soma 2), `i += 5` (soma 5), `i += 10`, etc.
3. **Pular degraus para trás:** Usando `i -= 3` (subtrai 3), `i -= 5` (subtrai 5), etc.

---

## 🚀 Caso 1: Contagem Regressiva de 10 até 1 (`i--`) (Imagem 1)

### O Código:
```java
public class Main {
    public static void main(String[] args){

        for(int i = 10; i > 0; i--){
            System.out.println(i);
        }

    }
}
```

### 🔍 O que mudou nas 3 engrenagens?
1. **Início:** `int i = 10;`  
   * Agora começamos lá no topo, valendo **10** (e não mais 0 ou 1).
2. **Condição:** `i > 0;`  
   * **Muito cuidado aqui!** Como o número está diminuindo, a pergunta mudou para **maior que zero** (`> 0`). Enquanto o número for positivo, ele continua rodando.
3. **Passo:** `i--`  
   * É o atalho para `i = i - 1;`. A cada volta, tira 1 do contador.

---

### 📋 Tabela de Rastreio (Passo a Passo na Memória):

| Volta | Valor de `i` | Pergunta: `i > 0`? | O que sai no Console? | O que o `i--` faz ao final? |
| :---: | :---: | :---: | :---: | :---: |
| **1ª** | `10` | `10 > 0`? **SIM (true)** | `10` | `10 - 1` = `9` |
| **2ª** | `9` | `9 > 0`? **SIM (true)** | `9` | `9 - 1` = `8` |
| **3ª** | `8` | `8 > 0`? **SIM (true)** | `8` | `8 - 1` = `7` |
| **4ª** | `7` | `7 > 0`? **SIM (true)** | `7` | `7 - 1` = `6` |
| **5ª** | `6` | `6 > 0`? **SIM (true)** | `6` | `6 - 1` = `5` |
| **6ª** | `5` | `5 > 0`? **SIM (true)** | `5` | `5 - 1` = `4` |
| **7ª** | `4` | `4 > 0`? **SIM (true)** | `4` | `4 - 1` = `3` |
| **8ª** | `3` | `3 > 0`? **SIM (true)** | `3` | `3 - 1` = `2` |
| **9ª** | `2` | `2 > 0`? **SIM (true)** | `2` | `2 - 1` = `1` |
| **10ª**| `1` | `1 > 0`? **SIM (true)** | `1` | `1 - 1` = `0` |
| **Fim** | `0` | `0 > 0`? **NÃO (false)** | *(Não imprime nada)* | **O laço morre e o programa acaba!** |

---

### 🔍 Enumeração Linha por Linha (Caso 1):

* **Linha 1:** `public class Main {`  
  * Cria a classe principal.  
  * **Console:** Nada.
* **Linha 2:** `public static void main(String[] args){`  
  * Ponto onde a execução começa.  
  * **Console:** Nada.
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** `for(int i = 10; i > 0; i--){`  
  * Prepara o laço com `i` começando em 10, checa se `i > 0` e diminui `i--` a cada volta.  
  * **Console:** Nada nessa linha.
* **Linha 5:** `System.out.println(i);`  
  * Imprime o valor atual de `i` e pula para a linha de baixo.  
  * **Console:** Imprime o número da rodada.
* **Linha 6:** `}`  
  * Fim da volta. Executa o `i--` e volta para testar `i > 0`.  
  * **Console:** Nada.
* **Linha 7:** *(Linha em branco)*
* **Linha 8:** `}`  
  * Fecha o método `main`.  
  * **Console:** Encerra com `Process finished with exit code 0`.
* **Linha 9:** `}`  
  * Fecha a classe `Main`.  
  * **Console:** Nada.

### 📺 O que sai no Console (Caso 1):
```text
10
9
8
7
6
5
4
3
2
1
```

---

## 🦘 Caso 2: Saltos de 2 em 2 para Frente (`i += 2`) (Imagem 2)

### O Código:
```java
public class Main {
    public static void main(String[] args){

        for(int i = 1; i <= 10; i+=2){
            System.out.println(i);
        }

    }
}
```

### 🔍 O que mudou?
* **Início:** `int i = 1;` (começa no 1).
* **Condição:** `i <= 10;` (repete enquanto for menor ou igual a 10).
* **Passo:** `i += 2`  
  * Em vez de somar 1 (`i++`), ele soma **2**! É o mesmo que escrever `i = i + 2;`.
  * Isso faz o programa imprimir apenas os **números ímpares** de 1 a 10!

---

### 📋 Tabela de Rastreio (Caso 2):

| Volta | Valor de `i` | Pergunta: `i <= 10`? | O que imprime? | O que o `i += 2` faz no final? |
| :---: | :---: | :---: | :---: | :---: |
| **1ª** | `1` | `1 <= 10`? **SIM** | `1` | `1 + 2` = `3` |
| **2ª** | `3` | `3 <= 10`? **SIM** | `3` | `3 + 2` = `5` |
| **3ª** | `5` | `5 <= 10`? **SIM** | `5` | `5 + 2` = `7` |
| **4ª** | `7` | `7 <= 10`? **SIM** | `7` | `7 + 2` = `9` |
| **5ª** | `9` | `9 <= 10`? **SIM** | `9` | `9 + 2` = `11` |
| **Fim** | `11` | `11 <= 10`? **NÃO (false)** | *(Para)* | **Fim do laço!** |

---

### 🔍 Enumeração Linha por Linha (Caso 2):

* **Linha 1:** `public class Main {` -> Declara a classe. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` -> Ponto de início. *(Console: Nada)*
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** `for(int i = 1; i <= 10; i+=2){`  
  * Contador começa em 1, para quando passar de 10, e pula de 2 em 2.  
  * *(Console: Nada nessa linha)*
* **Linha 5:** `System.out.println(i);`  
  * Imprime o número ímpar da rodada.  
  * *(Console: Imprime o número e pula linha)*
* **Linha 6:** `}` -> Executa `i += 2` e volta para testar. *(Console: Nada)*
* **Linha 7:** *(Linha em branco)*
* **Linha 8:** `}` -> Fecha o `main`. *(Console: Nada)*
* **Linha 9:** `}` -> Fecha a classe `Main`. *(Console: Nada)*

### 📺 O que sai no Console (Caso 2):
```text
1
3
5
7
9
```

---

## 📉 Caso 3: Contagem Regressiva com Saltos de 3 em 3 (`i -= 3`) (Imagem 3)

### O Código:
```java
public class Main {
    public static void main(String[] args){

        for(int i = 10; i > 0; i-=3){
            System.out.println(i);
        }

    }
}
```

### 🔍 O que mudou?
* **Início:** `int i = 10;` (começa no 10).
* **Condição:** `i > 0;` (continua enquanto for maior que 0).
* **Passo:** `i -= 3`  
  * É o atalho para `i = i - 3;`. Tira 3 a cada volta!

---

### 📋 Tabela de Rastreio (Caso 3):

| Volta | Valor de `i` | Pergunta: `i > 0`? | O que imprime? | O que o `i -= 3` faz no final? |
| :---: | :---: | :---: | :---: | :---: |
| **1ª** | `10` | `10 > 0`? **SIM** | `10` | `10 - 3` = `7` |
| **2ª** | `7` | `7 > 0`? **SIM** | `7` | `7 - 3` = `4` |
| **3ª** | `4` | `4 > 0`? **SIM** | `4` | `4 - 3` = `1` |
| **4ª** | `1` | `1 > 0`? **SIM** | `1` | `1 - 3` = `-2` |
| **Fim** | `-2` | `-2 > 0`? **NÃO (false)** | *(Para)* | **Fim do laço!** |

---

### 🔍 Enumeração Linha por Linha (Caso 3):

* **Linha 1:** `public class Main {` -> Declara a classe. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` -> Ponto de início. *(Console: Nada)*
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** `for(int i = 10; i > 0; i-=3){`  
  * Contador começa em 10, continua enquanto for positivo (`> 0`), e desce de 3 em 3.  
  * *(Console: Nada nessa linha)*
* **Linha 5:** `System.out.println(i);`  
  * Imprime o número atual.  
  * *(Console: Imprime o número e pula linha)*
* **Linha 6:** `}` -> Executa `i -= 3` e volta para o teste. *(Console: Nada)*
* **Linha 7:** *(Linha em branco)*
* **Linha 8:** `}` -> Fecha o `main`. *(Console: Nada)*
* **Linha 9:** `}` -> Fecha a classe `Main`. *(Console: Nada)*

### 📺 O que sai no Console (Caso 3):
```text
10
7
4
1
```

---

## ⚠️ Cuidado com a Armadilha Mortal do Iniciante: Loop Infinito no `for`!

Veja esse erro clássico que todo iniciante comete pelo menos uma vez:

```java
// ❌ CÓDIGO COM ERRO:
for(int i = 10; i > 0; i++){ // OLHE O i++ AQUI!
    System.out.println(i);
}
```
**O que acontece aqui?**
* Você começa com `i = 10`.
* A condição diz: repita enquanto `i > 0`.
* Mas no passo você colocou `i++` (somando!).
* Então `i` vai para 11, 12, 13, 100, 1.000, 1.000.000...
* `i` **SEMPRE** será maior que zero! A condição **NUNCA** será falsa!
* **Resultado:** O computador entra em loop infinito e trava a tela com números subindo feito loucos!

> 🧠 **Regra de Ouro:**  
> * Se você quer **subir**, comece baixo, use `<` ou `<=` e use **`++`** ou **`+=`**.  
> * Se você quer **descer**, comece alto, use `>` ou `>=` e use **`--`** ou **`-=`**.
