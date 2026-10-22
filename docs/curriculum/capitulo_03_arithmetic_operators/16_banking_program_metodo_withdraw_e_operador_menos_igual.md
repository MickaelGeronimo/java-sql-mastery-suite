# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 14: O Método `withdraw()`, a Dupla Validação de Segurança e o Operador `-=`
**Timestamps do vídeo:** `4:48:46` até `4:49:25 / 12:00:00`  
**Tema:** A construção do terceiro grande pilar do banco: o método de saque (`withdraw`)! O professor Bro Code implementa uma dupla camada de proteção com `if - else if - else` para barrar saques maiores que o saldo (`INSUFFICIENT FUNDS`) e valores negativos, e conecta no `main` usando o operador de subtração composta: `balance -= withdraw(balance);`.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:48:46 — A Dupla Blindagem do `withdraw`):**
   * No editor de código, entre as Linhas 64 e 78, o professor constrói a lógica do método `withdraw`:
     ```java
     System.out.println("Enter amount to be withdrawn: ");
     amount = scanner.nextDouble();

     if(amount > balance){
         System.out.println("INSUFFICIENT FUNDS");
         return 0;
     }
     else if(amount < 0){
         System.out.println("Amount can't be negative");
         return 0;
     }
     else{
         return amount;
     }
     ```
   * O professor explica: *"No saque temos dois perigos reais: o cliente tentar sacar mais dinheiro do que possui na conta (`amount > balance`) ou tentar sacar um número negativo (`amount < 0`). Tratamos ambos devolvendo zero!"*.

2. **Imagem 2 (Minuto 4:49:02 — Conectando no Menu do `main`):**
   * O professor rola o código para cima até o `switch` dentro do `main`.
   * Na **Linha 30**, ele apaga o texto provisório `"WITHDRAW"` e começa a digitar a subtração:  
     `case 3 -> balance = b...`

3. **Imagem 3 (Minuto 4:49:17 — A Chamada com Parâmetro):**
   * O autor completa a linha:  
     `case 3 -> balance = balance - withdraw(balance);`
   * Ele seleciona com o mouse a parte `withdraw(balance)` (ficando em azul) e enfatiza:  
     *"Reparem bem: o `deposit()` não precisava saber nosso saldo, mas o `withdraw(balance)` é **obrigado** a receber o `balance`, senão ele não tem como saber se temos saldo suficiente para liberar o saque!"*.

4. **Imagem 4 (Minuto 4:49:25 — O Operador Composto `-=`):**
   * O professor refatora a Linha 30 para a forma profissional resumida:  
     `case 3 -> balance -= withdraw(balance);`
   * Agora o menu tem uma simetria perfeita:
     * `case 2 -> balance += deposit();` (soma dinheiro)
     * `case 3 -> balance -= withdraw(balance);` (subtrai dinheiro)

---

## 🧠 Entendendo a Lógica Como um Iniciante

### 1. Por Que o `withdraw` Precisa Receber `balance`, Mas o `deposit` Não?
* **No Depósito:** Não importa se você tem R$ 0 ou R$ 1 milhão na conta; qualquer dinheiro que você colocar vai somar. O método não precisa olhar para o seu saldo anterior.
* **No Saque:** Se você tem **R$ 50,00** e tenta sacar **R$ 100,00**, o caixa eletrônico tem que travar e dizer *"Saldo Insuficiente!"*. Para poder fazer essa comparação (`amount > balance`), o método precisa receber o saldo atual nas mãos como parâmetro: `withdraw(double balance)`.

### 2. A Árvore de Decisão do Saque (`if - else if - else`):
O método avalia a quantia digitada em 3 cenários rigorosos:
1. **Cenário 1 (`amount > balance`):** O cliente quer sacar mais do que tem.  
   $\rightarrow$ Imprime `"INSUFFICIENT FUNDS"` e devolve `return 0;` (nada é descontado).
2. **Cenário 2 (`amount < 0`):** O cliente digitou um número negativo (ex: `-20`).  
   $\rightarrow$ Imprime `"Amount can't be negative"` e devolve `return 0;` (nada é descontado).
3. **Cenário 3 (`else`):** O valor é legítimo (está entre zero e o saldo da conta).  
   $\rightarrow$ Devolve `return amount;` para o `main` descontar da conta!

### 3. O Operador `-=` na Linha 30:
* `balance -= withdraw(balance);` é exatamente o mesmo que:  
  `balance = balance - withdraw(balance);`
* O `-=` pega o saldo atual e **subtrai** o valor que o método devolver.

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // JAVA BANKING PROGRAM FOR BEGINNERS

        double balance = 0;
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
                case 1 -> showBalance(balance);
                case 2 -> balance += deposit();
                case 3 -> balance -= withdraw(balance); // Conectado com o método de saque!
                case 4 -> isRunning = false;
                default -> System.out.println("INVALID CHOICE");
            }
        }

        // EXIT MESSAGE

        scanner.close();
    }

    static void showBalance(double balance){
        System.out.println("***************");
        System.out.printf("$%.2f\n", balance);
    }

    static double deposit(){

        double amount;

        System.out.print("Enter an amount to be deposited: ");
        amount = scanner.nextDouble();

        if(amount < 0){
            System.out.println("Amount can't be negative");
            return 0;
        }
        else{
            return amount;
        }
    }

    static double withdraw(double balance){

        double amount;

        System.out.println("Enter amount to be withdrawn: ");
        amount = scanner.nextDouble();

        if(amount > balance){
            System.out.println("INSUFFICIENT FUNDS");
            return 0;
        }
        else if(amount < 0){
            System.out.println("Amount can't be negative");
            return 0;
        }
        else{
            return amount;
        }
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linhas 1 a 12:** *(Estrutura e Variáveis)*
* **Linha 5:** `static Scanner scanner = new Scanner(System.in);` $\rightarrow$ Leitor compartilhado.
* **Linha 10:** `double balance = 0;` $\rightarrow$ Saldo inicial.
* **Linha 11:** `boolean isRunning = true;` $\rightarrow$ Chave do loop.
* **Linha 12:** `int choice;` $\rightarrow$ Guarda a opção do menu.
* **Console:** **Nada**.

---

### **Linhas 14 a 27:** *(Loop, Menu e Leitura)*
* Exibem o menu e leem `choice = scanner.nextInt();`.
* **Console:** **Nada**.

---

### **Linhas 28 e 29:** *(Show Balance e Deposit)*
* **Linha 28:** `case 1 -> showBalance(balance);`
* **Linha 29:** `case 2 -> balance += deposit();`
* **Console:** **Nada**.

---

### **Linha 30:** `    case 3 -> balance -= withdraw(balance);`  *(⭐ A GRANDE CONEXÃO!)*
* **O que faz:**
  1. Chama o método `withdraw` entregando o saldo atual (`balance`).
  2. Aguarda o método validar e devolver a quantia aprovada para saque.
  3. Desconta essa quantia do saldo com o operador `-=`.
* **Console:** As mensagens de saque aparecerão na tela durante a operação.

---

### **Linhas 31 a 43:** *(Saída, Fechamento de Chaves e Scanner)*
* **Linha 31:** `case 4 -> isRunning = false;`
* **Linha 42:** `scanner.close();`
* **Linha 43:** `}` *(Fecha main)*.
* **Console:** **Nada**.

---

### **Linhas 63:** `static double withdraw(double balance){`  *(⭐ NOVO MÉTODO!)*
* **O que faz:**
  * `static`: Pode ser chamado direto pelo `main`.
  * `double`: Devolve o valor numérico que será subtraído do saldo.
  * `withdraw`: Nome do método ("sacar").
  * `(double balance)`: Recebe o saldo atual para poder fazer a checagem de fundos.
* **Console:** **Nada**.

---

### **Linha 64:** `    System.out.println("Enter amount to be withdrawn: ");`
* **O que faz:** Imprime o pedido `"Digite a quantia a ser sacada: "`.
* **Console:** Quando for chamado, exibirá essa frase.

---

### **Linha 65:** `    amount = scanner.nextDouble();`
* **O que faz:** Lê a quantia que o cliente deseja sacar no teclado.
* **Console:** O valor digitado aparece na tela.

---

### **Linha 67:** `    if(amount > balance){`  *(⭐ TRAVA 1: CHEQUE ESPECIAL BLOQUEADO!)*
* **O que faz:** Compara se a quantia pedida é maior do que o dinheiro que tem na conta.
* **Console:** **Nada**.

---

### **Linha 68:** `        System.out.println("INSUFFICIENT FUNDS");`
* **O que faz:** Imprime a mensagem de saldo insuficiente.
* **Console (se pedir mais do que tem):** Imprime `INSUFFICIENT FUNDS`.

---

### **Linha 69:** `        return 0;`
* **O que faz:** Devolve `0`. No `main`, `balance -= 0` não desconta nada da conta!
* **Console:** **Nada**.

---

### **Linha 70:** `    }`
* **Console:** **Nada**.

---

### **Linha 71:** `    else if(amount < 0){`  *(⭐ TRAVA 2: BLOQUEIO DE VALOR NEGATIVO!)*
* **O que faz:** Verifica se o usuário digitou um número negativo (ex: `-50`).
* **Console:** **Nada**.

---

### **Linha 72:** `        System.out.println("Amount can't be negative");`
* **O que faz:** Imprime a advertência de quantia negativa.
* **Console (se digitar negativo):** Imprime `Amount can't be negative`.

---

### **Linha 73:** `        return 0;`
* **O que faz:** Devolve `0` para não alterar o saldo.
* **Console:** **Nada**.

---

### **Linha 74:** `    }`
* **Console:** **Nada**.

---

### **Linha 75:** `    else{`  *(⭐ CAMINHO DO SUCESSO!)*
* **O que faz:** Se não for maior que o saldo e nem for negativo, o saque é legítimo!
* **Console:** **Nada**.

---

### **Linha 76:** `        return amount;`
* **O que faz:** Devolve a quantia pedida para o `main` subtrair do saldo com sucesso!
* **Console:** **Nada**.

---

### **Linhas 77 a 79:** `}`, `}` e `}`
* **O que faz:** Fecha o `else`, fecha o método `withdraw` e fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console:

**Nada!**  
O console continua fechado em todos os 4 prints. O professor esteve focado no editor de código montando a lógica e conectando o método no `switch`.

---

## 🔮 O Que Esperar do Próximo Passo?

O sistema agora possui todas as 4 operações bancárias completamente programadas:
1. `Show Balance` $\rightarrow$ Consulta com centavos.
2. `Deposit` $\rightarrow$ Depósito com proteção contra negativos.
3. `Withdraw` $\rightarrow$ Saque com proteção contra saldo insuficiente e negativos.
4. `Exit` $\rightarrow$ Encerramento seguro.

No próximo passo, o professor vai dar Play e testar todos os cenários de saque no console!
