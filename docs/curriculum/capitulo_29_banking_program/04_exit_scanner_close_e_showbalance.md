# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 4: Testando o `Exit`, Fechando o Scanner e Criando o Método `showBalance()`
**Timestamps do vídeo:** `4:38:15` até `~4:39:30 / 12:00:00`  
**Tema:** O encerramento impecável da fase de testes do menu e o início da construção dos métodos bancários reais! O professor Bro Code testa a opção `4. Exit` comprovando que o loop encerra perfeitamente, adiciona a boa prática de fechar o leitor com `scanner.close()`, e declara o primeiro método do banco: `showBalance(double balance)` usando `System.out.printf` para formatar a moeda.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:38:15 — O Teste da Opção 4 e o Fim Elegante):**
   * No console, após testar o `WITHDRAW` e ver o menu reaparecer, o professor digita `4` e dá Enter:
     ```text
     Enter your choice (1-4): 4

     Process finished with exit code 0
     ```
   * O texto `Process finished with exit code 0` fica selecionado em azul na tela.
   * O professor explica: ao digitar `4`, o `case 4` mudou a chave `isRunning` para `false`. O `while` percebeu a chave desligada, saiu do laço e encerrou o programa sem travar nem dar erro! O sistema de navegação está 100% aprovado!

2. **Imagem 2 (Minuto 4:38:27 — Fechando o Scanner com `scanner.close()`):**
   * O professor rola o código para baixo, logo depois da chave de fechamento do `while` (Linha 34).
   * Na Linha 44, ele digita: `scanner.close();` logo antes de fechar o método `main`.
   * Ele ensina uma regra de ouro de programação profissional: todo canal de leitura do teclado aberto deve ser fechado quando não for mais usado para economizar memória do computador.

3. **Imagem 3 (Minuto 4:38:52 — Declarando o Método `showBalance`):**
   * O autor vai para fora do método `main` (abaixo da chave da Linha 43), mas ainda dentro da classe `Main`.
   * Na Linha 45, ele escreve a assinatura do primeiro método de negócio do banco:
     ```java
     static void showBalance(double balance){

     }
     ```
   * Repare que o IntelliJ exibe a etiqueta cinza `no usages` (nenhum uso) ao lado da chave, porque o método foi criado mas ainda não foi chamado dentro do `case 1` do `main`.

4. **Imagem 4 (Minuto ~4:39:10 — Formatando o Saldo com `printf`):**
   * Dentro do bloco do método `showBalance`, na Linha 46, o professor digita:
     ```java
     System.out.printf("$%f", balance);
     ```
   * Ele usa `printf` (*print formatado*) com o cifrão `$` e o especificador `%f` para indicar que aquele valor de ponto flutuante (`balance`) deve ser impresso com símbolo de moeda na tela.

---

## 🧠 Entendendo os Novos Conceitos de Forma Bem Simples

### 1. Por Que Digitar `4` Encerra o Programa?
* Na Linha 31 do código: `case 4 -> isRunning = false;`.
* Quando o cliente digita `4`, a variável `isRunning` vira `false`.
* O Java chega no final do bloco do `while`, olha para o início e pergunta: *"isRunning ainda é true?"*.
* Como agora vale `false`, o `while` **não repete mais**! Ele pula para a linha seguinte fora do loop, executando `scanner.close()` e finalizando a aplicação!

### 2. Por Que Usar `scanner.close()`?
* Imagine o `Scanner` como uma **torneira de água aberta** conectada ao teclado do seu computador.
* Se você terminar o programa e deixar a torneira aberta, ela fica gastando recursos do sistema operacional (*memory leak* / vazamento de recursos).
* O comando `scanner.close();` é o ato de **fechar a torneira**. É a postura de um desenvolvedor cuidadoso e profissional!

### 3. A Conexão com o Capítulo 28: Por Que `showBalance(double balance)` Recebe Parâmetro?
* Lembre-se do que aprendemos sobre **Escopo de Variáveis**:
  * A variável `double balance;` foi declarada **dentro** do método `main`.
  * Ela é uma **variável local** exclusiva do `main`.
  * O método `showBalance` é outro cômodo da casa, ele **não consegue enxergar** o `balance` do `main`!
* Para que o método `showBalance` saiba quanto dinheiro existe na conta, o `main` precisará passar o saldo por debaixo da porta como argumento, e o `showBalance` recebe em mãos como parâmetro `(double balance)`!

### 4. O Que Significa `System.out.printf("$%f", balance);`?
* O `printf` vem de *"print formatted"* (imprimir com formatação especial).
* O `%f` é um **marcador de posição** (*placeholder*) para números decimais (*float / double*).
* O Java pega o valor que estiver na variável `balance` e "encaixa" exatamente no lugar do `%f`.
* O símbolo `$` que está antes do `%f` é impresso literalmente como um cifrão de dinheiro!  
  *(Exemplo: se o saldo for 0, sairá `$0.000000`)*.

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

        while(isRunning){

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
        }

        // deposit()

        // withdraw()

        // EXIT MESSAGE

        scanner.close();
    }

    static void showBalance(double balance){
        System.out.printf("$%f", balance);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa o leitor do teclado.
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
* **O que faz:** Abre o método principal `main`.
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **Console:** **Nada**.

---

### **Linhas 8 a 11:** *(Variáveis Locais do main)*
* **Linha 8:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Leitor do teclado.
* **Linha 9:** `double balance;` $\rightarrow$ Variável de saldo (local do `main`).
* **Linha 10:** `boolean isRunning = true;` $\rightarrow$ Interruptor do loop.
* **Linha 11:** `int choice;` $\rightarrow$ Opção escolhida.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Início do laço contínuo do banco. Enquanto `isRunning` for `true`, o menu reaparece.
* **Console:** **Nada**.

---

### **Linhas 16 a 23:** *(Menu de Opções)*
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
* **O que faz:** Imprime o menu completo na tela preta do console a cada rodada do loop.
* **Console:** Exibe a moldura de asteriscos e as opções de 1 a 4.

---

### **Linha 25:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Imprime o pedido da opção mantendo o cursor na mesma linha.
* **Console:**
  ```text
  Enter your choice (1-4): 
  ```

---

### **Linha 26:** `choice = scanner.nextInt();`
* **O que faz:** Pausa e lê o número digitado pelo usuário.
* **Console:** O número digitado aparece na frente dos dois pontos.

---

### **Linha 28:** `switch(choice){`
* **O que faz:** Avalia a opção digitada.
* **Console:** **Nada**.

---

### **Linha 29:** `    case 1 -> System.out.println("SHOW BALANCE");`
* **O que faz:** Se digitou `1`, imprime provisoriamente `"SHOW BALANCE"`. *(No próximo passo, o autor substituirá isso pela chamada `showBalance(balance);`)*.
* **Console (se digitou 1):** Imprime `SHOW BALANCE`.

---

### **Linha 30:** `    case 2 -> System.out.println("DEPOSIT");`
* **Console (se digitou 2):** Imprime `DEPOSIT`.

---

### **Linha 31:** `    case 3 -> System.out.println("WITHDRAW");`
* **Console (se digitou 3):** Imprime `WITHDRAW`.

---

### **Linha 32:** `    case 4 -> isRunning = false;`
* **O que faz:** Se digitou `4` (Exit), altera a variável `isRunning` para `false`.
* **Console (se digitou 4):** **Nada** (alteração silenciosa na memória).

---

### **Linha 33:** `    default -> System.out.println("INVALID CHOICE");`
* **Console (se digitou número fora de 1-4):** Imprime `INVALID CHOICE`.

---

### **Linha 34:** `}` *(Fecha o switch)*
* **Console:** **Nada**.

---

### **Linha 35:** `}` *(Fecha o while)*
* **O que faz:** Se `isRunning` ainda for `true`, salta de volta para a Linha 14 e desenha o menu de novo. Se for `false` (você digitou 4), escapa do loop e vai para a Linha 42!
* **Console:** **Nada**.

---

### **Linhas 36 a 41:** *(Comentários futuros de depósito, saque e mensagem)*
* `// deposit()`, `// withdraw()`, `// EXIT MESSAGE`
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** **A LIMPEZA DE RECURSOS.** Fecha o leitor `scanner`, liberando a memória do canal de entrada do teclado no sistema operacional.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o método main)*
* **O que faz:** Encerra a execução do método `main`.
* **Console:** Exibe `Process finished with exit code 0`.

---

### **Linha 44:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){`
* **O que faz:**
  * `static`: Permite que o método seja chamado diretamente pelo `main` estático.
  * `void`: O método não retorna nenhum valor numérico; sua missão é apenas imprimir na tela.
  * `showBalance`: Nome do método ("mostrar saldo").
  * `(double balance)`: Parâmetro que recebe o valor do saldo vindo do `main`.
  * **Etiqueta cinza `no usages`:** O IntelliJ avisa que o método existe, mas ainda não foi chamado em lugar nenhum do programa.
* **Console:** **Nada**.

---

### **Linha 46:** `    System.out.printf("$%f", balance);`
* **O que faz:** Imprime o caractere de dólar `$` seguido do valor decimal guardado no parâmetro `balance`, substituindo o marcador de formato `%f`.
* **Console:** Quando for chamado, imprimirá algo como `$0.000000`. *(Neste momento ainda não sai nada porque o método ainda não foi chamado)*.

---

### **Linha 47:** `}` *(Fecha o método showBalance)*
* **O que faz:** Encerra o método `showBalance` e devolve o fluxo para quem o chamou.
* **Console:** **Nada**.

---

### **Linha 48:** `}` *(Fecha a classe Main)*
* **O que faz:** Fecha o arquivo da classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como na Imagem 1:

Veja o momento exato em que o professor testou o encerramento do programa:

```text
WITHDRAW
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 4

Process finished with exit code 0
```

---

## 🔮 O que Falta Conectar no Próximo Passo?

Repare em dois detalhes importantíssimos que o autor vai arrumar a seguir:
1. **Chamar o método:** Dentro do `case 1`, ele vai trocar `System.out.println("SHOW BALANCE");` por `showBalance(balance);`. Assim, quando o cliente apertar `1`, o método da Linha 45 será acionado e a etiqueta `no usages` vai sumir!
2. **Formatação de centavos:** O `%f` simples imprime 6 casas decimais (ex: `$0.000000`). O professor vai ensinar a usar `%.2f\n` para exibir apenas duas casas de centavos (`$0.00`) e pular linha com elegância!
