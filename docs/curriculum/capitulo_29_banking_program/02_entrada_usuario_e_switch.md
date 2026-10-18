# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 2: Lendo a Escolha com `Scanner` e Decidindo com o `switch` Moderno (`->`)
**Timestamps do vídeo:** `~4:33:30` até `~4:34:30 / 12:00:00`  
**Tema:** Dando vida e interatividade ao caixa eletrônico! O professor Bro Code adiciona a leitura do teclado com `System.out.print` e `scanner.nextInt()`, e implementa a estrutura de decisão com o moderno **Enhanced Switch** (sintaxe de setas `->`). Ele executa o programa pela primeira vez e o console congela no prompt interativo esperando a digitação do cliente.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja exatamente o que aconteceu na tela do professor:

1. **Imagem 1 (O Código Digitado e o Cursor no Botão Play):**
   * O autor adicionou o miolo que faltava logo abaixo do menu visual:
     * Linha 23: `System.out.print("Enter your choice (1-4): ");` (repare que é `print`, sem o `ln`).
     * Linha 24: `choice = scanner.nextInt();` (lendo o número inteiro digitado).
     * Linhas 26 a 32: Um bloco de `switch(choice)` com as opções `case 1`, `case 2`, `case 3`, `case 4` e `default`, usando a sintaxe moderna de setas `->`.
   * No canto superior direito, o cursor do mouse do professor está posicionado exatamente sobre o botão verde triangular de **Play** (`Run 'Main'`), pronto para dar a partida no programa.

2. **Imagem 2 (O Programa Rodando no Console Interativo):**
   * O professor apertou Play e o console preto do IntelliJ subiu na metade inferior da tela.
   * Em texto verde brilhante, todo o menu desenhado na Parte 1 é impresso perfeitamente:
     ```text
     ***************
     BANKING PROGRAM
     ***************
     1. Show Balance
     2. Deposit
     3. Withdraw
     4. Exit
     ***************
     Enter your choice (1-4): 
     ```
   * O cursor retangular branco pisca na tela **exatamente na mesma linha**, logo após o espaço depois dos dois pontos de `(1-4): `. O programa está em pausa viva, esperando que uma pessoa digite um número e aperte `Enter`!

---

## 🧠 Entendendo as Novidades Como um Iniciante

### 1. Por que usar `System.out.print` em vez de `println` na Linha 23?
* O `println` sempre joga o cursor para a **linha de baixo** após imprimir.
* O `print` (sem `ln`) imprime o texto e **deixa o cursor parado na mesma linha**!
* Para perguntas de entrada do usuário como `"Digite sua opção: "`, fica muito mais bonito e profissional se a pessoa digitar o número ao lado da pergunta, e não embaixo!

### 2. O Que Faz `choice = scanner.nextInt();`?
* Essa linha age como um **sinal vermelho** de trânsito.
* O Java literalmente **congela** e fica escutando o teclado. Ele não avança para o `switch` enquanto o usuário não digitar um número e apertar a tecla `Enter`.
* Assim que o usuário aperta `Enter`, o número digitado é guardado dentro da variável `choice`.

### 3. O Moderno `switch` com Setas (`->`) do Java
Antigamente no Java tradicional, para fazer um `switch` tínhamos que escrever:
```java
// MODO ANTIGO (cheio de pegadinhas)
switch(choice) {
    case 1:
        System.out.println("SHOW BALANCE");
        break; // se esquecer o break, ele sai executando o case 2 de graça!
}
```
O Bro Code está ensinando o **Java Moderno (Enhanced Switch)** com a seta `->`:
* Não precisa de palavra `break;`!
* O Java executa apenas a linha daquela seta e encerra o `switch` sozinho!
* É mais limpo, mais seguro e impossível de dar o famoso bug de esquecer o `break`.

### 4. Por Que Ele Escreveu `System.out.println("SHOW BALANCE");` Provisório?
* Isso em programação se chama **Placeholder** (ou código esqueleto).
* Antes de gastar tempo programando as contas matemáticas de depósito, saldo e saque, o bom programador primeiro testa se os **botões funcionam**!
* Se digitar `1` e sair `"SHOW BALANCE"`, significa que a navegação do menu está perfeita. Depois só trocaremos esse texto pelo método de verdade!

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA BANKING PROGRAM FOR BEGINNERS

        Scanner scanner = new Scanner(System.in);
        double balance;
        boolean isRunning = true;
        int choice;

        System.out.println("***************");
        System.out.println("BANKING PROGRAM");
        System.out.println("***************");
        System.out.println("1. Show Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("***************");

        System.out.print("Enter your choice (1-4): ");
        choice = scanner.nextInt();

        switch(choice){
            case 1 -> System.out.println("SHOW BALANCE");
            case 2 -> System.out.println("DEPOSIT");
            case 3 -> System.out.println("WITHDRAW");
            case 4 -> isRunning = false;
            default -> System.out.println("INVALID CHOICE");
        }

        // showBalance()

        // deposit()

        // withdraw()

        // EXIT MESSAGE
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa a classe do leitor de teclado.
* **Console:** **Nada**.

---

### **Linha 2:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 3:** `public class Main {`
* **O que faz:** Abre a classe `Main`.
* **Console:** **Nada**.

---

### **Linha 4:** `public static void main(String[] args) {`
* **O que faz:** Abre o método principal de execução do Java.
* **Console:** **Nada**.

---

### **Linha 5:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **O que faz:** Comentário de identificação do projeto.
* **Console:** **Nada**.

---

### **Linha 7:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 8:** `Scanner scanner = new Scanner(System.in);`
* **O que faz:** Constrói o objeto `scanner` para ler as teclas digitadas no teclado.
* **Console:** **Nada**.

---

### **Linha 9:** `double balance;`
* **O que faz:** Cria a variável decimal `balance` para armazenar o saldo bancário.
* **Console:** **Nada**.

---

### **Linha 10:** `boolean isRunning = true;`
* **O que faz:** Cria a variável booleana `isRunning` com valor `true`, que será a chave mestre do futuro loop.
* **Console:** **Nada**.

---

### **Linha 11:** `int choice;`
* **O que faz:** Cria a variável inteira `choice` para guardar a opção escolhida pelo cliente.
* **Console:** **Nada**.

---

### **Linha 12:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linhas 13 a 21:** *(O Menu Visual de Asteriscos e Opções)*
```java
System.out.println("***************");
System.out.println("BANKING PROGRAM");
System.out.println("***************");
System.out.println("1. Show Balance");
System.out.println("2. Deposit");
System.out.println("3. Withdraw");
System.out.println("4. Exit");
System.out.println("***************");
```
* **O que faz:** Cada linha `println` imprime seu respectivo texto decorativo ou opção e pula para a linha seguinte.
* **Console:** Exibe:
  ```text
  ***************
  BANKING PROGRAM
  ***************
  1. Show Balance
  2. Deposit
  3. Withdraw
  4. Exit
  ***************
  ```

---

### **Linha 22:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 23:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Imprime o pedido `"Enter your choice (1-4): "` (Digite sua escolha de 1 a 4:). Como é `print` (sem `ln`), o cursor **não pula de linha**; ele fica colado ao lado do espaço esperando.
* **Console:** Imprime e mantém o cursor na mesma linha:
  ```text
  Enter your choice (1-4): 
  ```

---

### **Linha 24:** `choice = scanner.nextInt();`
* **O que faz:** **A PAUSA INTERATIVA.** O programa congela sua execução e aguarda o usuário digitar um número inteiro no teclado e pressionar a tecla `Enter`. O valor digitado é atribuído à variável `choice`.
* **Console:** O número digitado pelo usuário aparece ao lado de `(1-4): `.

---

### **Linha 25:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 26:** `switch(choice){`
* **O que faz:** Abre a estrutura de decisão que avalia o valor guardado na variável `choice`. O Java compara o valor de `choice` com cada um dos `cases` abaixo.
* **Console:** **Nada**.

---

### **Linha 27:** `    case 1 -> System.out.println("SHOW BALANCE");`
* **O que faz:** Se o usuário digitou `1`, cai aqui. Imprime o texto `"SHOW BALANCE"` e pula de linha. Como usamos seta `->`, o `switch` é encerrado automaticamente sem precisar de `break`.
* **Console (se digitar 1):** Imprime `SHOW BALANCE`.

---

### **Linha 28:** `    case 2 -> System.out.println("DEPOSIT");`
* **O que faz:** Se o usuário digitou `2`, cai aqui. Imprime `"DEPOSIT"` e encerra o `switch`.
* **Console (se digitar 2):** Imprime `DEPOSIT`.

---

### **Linha 29:** `    case 3 -> System.out.println("WITHDRAW");`
* **O que faz:** Se o usuário digitou `3`, cai aqui. Imprime `"WITHDRAW"` e encerra o `switch`.
* **Console (se digitar 3):** Imprime `WITHDRAW`.

---

### **Linha 30:** `    case 4 -> isRunning = false;`
* **O que faz:** Se o usuário digitou `4` (Sair), cai aqui. Muda a variável `isRunning` de `true` para `false` (desligando a chave geral do banco).
* **Console (se digitar 4):** **Nada** (apenas altera o valor da variável na memória).

---

### **Linha 31:** `    default -> System.out.println("INVALID CHOICE");`
* **O que faz:** **A rede de proteção.** Se o usuário digitou qualquer número que não seja 1, 2, 3 ou 4 (por exemplo: `0`, `5`, `99`), cai aqui. Imprime a mensagem de erro `"INVALID CHOICE"` (Escolha inválida).
* **Console (se digitar número fora de 1-4):** Imprime `INVALID CHOICE`.

---

### **Linha 32:** `}`
* **O que faz:** Fecha o bloco do `switch`.
* **Console:** **Nada**.

---

### **Linhas 33 a 40:** *(Comentários de planejamento do autor)*
* `// showBalance()`
* `// deposit()`
* `// withdraw()`
* `// EXIT MESSAGE`
* **Console:** **Nada**.

---

### **Linha 41:** `}`
* **O que faz:** Fecha o método `main`.
* **Console:** Se o programa chegar aqui após o `switch`, ele finaliza exibindo `Process finished with exit code 0`.

---

### **Linha 42:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como na Imagem 2:

No momento do print, o programa está congelado nesta tela:

```text
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): █  <-- Cursor piscando aqui esperando a sua digitação!
```

---

## 🧪 Simulação Prática: O que Acontece se Você Digitar Cada Opção?

| Você digita e aperta Enter | O que o Switch faz? | O que aparece no console? |
| :---: | :--- | :--- |
| **`1`** | Entra no `case 1` | `SHOW BALANCE` |
| **`2`** | Entra no `case 2` | `DEPOSIT` |
| **`3`** | Entra no `case 3` | `WITHDRAW` |
| **`4`** | Entra no `case 4` e muda `isRunning = false` | *(Nada no console, programa termina)* |
| **`5`** (ou `99`, ou `-1`) | Entra no `default` | `INVALID CHOICE` |

---

## 🚨 O Problema Atual que o Autor Vai Resolver no Próximo Passo!

Repare numa coisa importantíssima:  
Se você rodar o programa agora e digitar `1`, ele vai imprimir `SHOW BALANCE` e o programa vai **morrer e fechar na sua cara**!  
No mundo real, quando você vai ao caixa eletrônico e tira um extrato, o caixa **não desliga**! Ele volta para o menu principal para você decidir se quer fazer outra operação ou retirar o cartão!

Por isso o autor já deixou preparado:
1. `boolean isRunning = true;` (Linha 10)
2. `case 4 -> isRunning = false;` (Linha 30)

No próximo print, você verá o professor colocar um **laço `while(isRunning)`** abraçando todo esse código, para que o menu continue se repetindo até que o cliente aperte a opção `4`!
