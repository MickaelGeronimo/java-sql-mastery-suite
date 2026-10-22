# 🔂 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 23: Laços de Repetição FOR (`for loop`) — Parte 3: Laço Dinâmico com Scanner
**Timestamps do vídeo:** `3:49:25` até `3:49:58 / 12:00:00`  
**Tema:** Controlando a quantidade de voltas do `for` em tempo real através da digitação do usuário (`Scanner`), a variável limite `max` e a contagem de `1` até `<= max`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 3:49:25):**
   * O professor quer dar o controle do laço para quem está usando o programa.
   * Ele cria um `Scanner` e pergunta ao usuário:
     `System.out.print("Enter how many times you want to loop: ");`  
     *(Digite quantas vezes você deseja repetir:)*
   * Ele guarda a resposta do usuário em uma variável inteira:
     `int max = scanner.nextInt();`
   * Em seguida, escreve o laço `for` usando esse `max` como condição de parada:
     ```java
     for(int i = 0; i < max; i++){
         System.out.println(i);
     }
     ```
   * E fecha o scanner na linha 15: `scanner.close();`.
2. **Imagem 2 (Minuto 3:49:53):**
   * O professor refina a contagem na linha 11 para ficar natural (contando de 1 até o número digitado):
     Muda de `int i = 0; i < max;` para:
     ```java
     for(int i = 1; i <= max; i++){
         System.out.println(i);
     }
     ```
   * Ele roda o programa. O console aguarda o usuário digitar:
     `Enter how many times you want to loop: |` (cursor piscando).
3. **Imagem 3 (Minuto 3:49:58):**
   * O professor digita o número **`6`** e aperta **Enter**.
   * Instantaneamente, o laço roda exatamente 6 vezes e imprime:
     ```text
     1
     2
     3
     4
     5
     6
     ```
   * O programa finaliza com sucesso (`Process finished with exit code 0`).

---

## 🧠 Por que essa aula é um divisor de águas? (Laço Estático vs. Laço Dinâmico)

Até agora, os nossos laços eram **estáticos** (números duros no código, como `i < 10`). Isso significa que o programa rodava 10 vezes para todo mundo, sempre igual e engessado.

Na vida real, **nenhum programa profissional é fixo assim**:
* Um aplicativo de banco não sabe quantos boletos você tem para pagar (podem ser 2 ou 20).
* A Netflix não sabe quantos filmes você tem na sua lista de favoritos.
* Um jogo não sabe quantas fases você vai querer jogar.

Ao pedir para o usuário digitar `max`, nós transformamos o laço em um **laço dinâmico**:
* Se o usuário digitar `3`, o laço roda 3 vezes.
* Se o usuário digitar `6`, o laço roda 6 vezes.
* Se o usuário digitar `1000`, o laço roda 1000 vezes!
* **Quem manda no programa agora é o usuário, não o código engessado!**

---

## ⚖️ As Duas Formas de Contar até `max`

O professor mostrou duas maneiras sutis de escrever esse `for`:

### Forma A: Começando do Zero (`i = 0; i < max;`)
```java
for(int i = 0; i < max; i++)
```
* Se `max = 6`, os valores de `i` serão: `0, 1, 2, 3, 4, 5`.
* Conte nos dedos: deu 6 números no total!
* É o padrão preferido dos programadores quando lidam com listas e matrizes (que sempre começam no índice 0).

### Forma B: Do Jeito Humano (`i = 1; i <= max;`) *(A forma final do vídeo!)*
```java
for(int i = 1; i <= max; i++)
```
* Se `max = 6`, os valores de `i` serão: `1, 2, 3, 4, 5, 6`.
* É muito mais intuitivo e amigável para quem está lendo na tela do programa.

---

## 💻 Código Completo (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter how many times you want to loop: ");
        int max = scanner.nextInt();

        for(int i = 1; i <= max; i++){
            System.out.println(i);
        }

        scanner.close();
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa a ferramenta de leitura do teclado do Java.
* **Explicação simples:** Como vamos perguntar algo ao usuário, precisamos chamar o `Scanner` da caixa de ferramentas do Java.
* **Memória:** Registra a classe `Scanner` para uso.
* **Console:** **Nada aparece no console.**

---

### **Linha 2:** *(Linha em branco para organizar o código)*
* **Console:** **Nada.**

---

### **Linha 3:** `public class Main {`
* **O que faz:** Abre a classe principal chamada `Main`.
* **Console:** **Nada.**

---

### **Linha 4:** `public static void main(String[] args){`
* **O que faz:** Ponto de partida oficial da execução do programa Java.
* **Console:** **Nada.**

---

### **Linha 5:** *(Linha em branco)*

---

### **Linha 6:** `Scanner scanner = new Scanner(System.in);`
* **O que faz:** Cria e ativa o leitor de teclado, dando a ele o nome de `scanner`.
* **Explicação simples:** Liga o "microfone" do teclado conectado à entrada padrão (`System.in`).
* **Memória:** Aloca o objeto `Scanner`.
* **Console:** **Nada.**

---

### **Linha 7:** *(Linha em branco)*

---

### **Linha 8:** `System.out.print("Enter how many times you want to loop: ");`
* **O que faz:** Mostra na tela a mensagem pedindo a quantidade de repetições.
* **Por que `print` e não `println`?** Porque o `print` **NÃO pula linha**. Ele deixa o cursor piscando exatamente ao lado dos dois pontos, esperando o usuário digitar.
* **Console:** Imprime:
  ```text
  Enter how many times you want to loop: 
  ```

---

### **Linha 9:** `int max = scanner.nextInt();`
* **O que faz:** O programa fica pausado aguardando o usuário digitar um número inteiro e apertar **Enter**.
* **Explicação simples:** Se o usuário digitar `6` e apertar Enter, o número `6` é guardado dentro da gaveta `max`.
* **Memória:** A variável inteira `max` recebe o valor digitado (ex: `6`).
* **Console:** O número digitado aparece na mesma linha da pergunta:
  ```text
  Enter how many times you want to loop: 6
  ```

---

### **Linha 10:** *(Linha em branco)*

---

### **Linha 11:** `for(int i = 1; i <= max; i++){`
* **O que faz:** Configura o laço dinâmico com 3 engrenagens:
  1. `int i = 1;` -> O contador começa no número **1**.
  2. `i <= max;` -> Repete enquanto `i` for menor ou igual ao número que o usuário escolheu (`6`).
  3. `i++` -> Soma 1 a cada volta (`i = i + 1`).
* **Console:** **Nada nessa linha propriamente dita.**

---

### **Linha 12:** `System.out.println(i);`
* **O que faz:** Imprime o valor atual da variável `i` e pula para a linha de baixo.
* **Console:** A cada volta, imprime um número na tela (ex: na 1ª volta imprime 1, na 2ª volta imprime 2, etc.).

---

### **Linha 13:** `}`
* **O que faz:** Fecha o bloco do `for`. Executa o `i++` e retorna para a linha 11 para verificar se `i <= max`.
* **Console:** **Nada.**

---

### **Linha 14:** *(Linha em branco)*

---

### **Linha 15:** `scanner.close();`
* **O que faz:** Desliga o leitor de teclado.
* **Explicação simples:** Como já pegamos o número que precisávamos e o laço já terminou, fechamos o scanner para economizar recursos da máquina.
* **Memória:** Libera o objeto `Scanner`.
* **Console:** **Nada.**

---

### **Linha 16:** `}`
* **O que faz:** Fecha o método `main`.
* **Console:** O IntelliJ exibe `Process finished with exit code 0`.

---

### **Linha 17:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada.**

---

## 📊 Rastreio da Execução Completa (Com `max = 6`)

Veja exatamente o que acontece quando o usuário digita **6**:

| Volta | Valor de `i` | Valor de `max` | Pergunta: `i <= max`? | O que aparece no console? | Ação no final da volta (`i++`) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **1ª** | `1` | `6` | `1 <= 6`? **SIM (true)** | `1` | `i` vira `2` |
| **2ª** | `2` | `6` | `2 <= 6`? **SIM (true)** | `2` | `i` vira `3` |
| **3ª** | `3` | `6` | `3 <= 6`? **SIM (true)** | `3` | `i` vira `4` |
| **4ª** | `4` | `6` | `4 <= 6`? **SIM (true)** | `4` | `i` vira `5` |
| **5ª** | `5` | `6` | `5 <= 6`? **SIM (true)** | `5` | `i` vira `6` |
| **6ª** | `6` | `6` | `6 <= 6`? **SIM (true)** | `6` | `i` vira `7` |
| **Fim** | `7` | `6` | `7 <= 6`? **NÃO (false)** | *(Não imprime nada)* | **O laço morre! Fim do programa!** |

---

## 📺 O que aparece no Console (Exatamente como no vídeo):

```text
Enter how many times you want to loop: 6
1
2
3
4
5
6

Process finished with exit code 0
```
