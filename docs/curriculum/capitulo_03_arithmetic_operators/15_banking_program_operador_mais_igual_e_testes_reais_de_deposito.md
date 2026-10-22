# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 13: O Operador Composto `+=`, o Teste de Defesa (`-1000000`) e o Depósito Real de `$150.05`
**Timestamps do vídeo:** `4:45:45` até `4:46:07 / 12:00:00`  
**Tema:** Testes reais de estresse no caixa eletrônico! O professor Bro Code refatora a Linha 29 usando o operador de adição composta (`+=`), testa uma tentativa de depósito negativo absurdo (`-1000000`), comprova que a validação barrou com `Amount can't be negative`, e em seguida deposita `$150.05`, consultando o saldo logo depois para provar que a conta agora tem `$150.05` de verdade!

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja a sequência eletrizante de testes que aconteceu na tela do professor:

1. **Imagem 1 (Minuto ~4:45:45 — O Operador `+=` e o Teste de Ataque com `-1000000`):**
   * No editor, na Linha 29, o professor simplificou o código:  
     `case 2 -> balance += deposit();`
   * No console, ele executa o programa e testa a segurança do banco:
     ```text
     Enter your choice (1-4): 2
     Enter an amount to be deposited: -1000000
     Amount can't be negative
     ```
   * O professor tentou depositar **menos um milhão de dólares**!
   * A linha `if(amount < 0)` detectou a trapaça imediatamente, imprimiu `Amount can't be negative`, devolveu `0` e o menu voltou na tela sem descontar um único centavo da conta!

2. **Imagem 2 (Minuto ~4:45:55 — O Segundo Teste Começando):**
   * No menu reaberto, o autor digita `2` (Deposit) novamente.
   * O console pergunta: `Enter an amount to be deposited: `.
   * O autor começa a digitar um valor positivo real: `15...`.

3. **Imagem 3 (Minuto 4:46:04 — O Depósito Real de `$150.05`):**
   * O autor completa a digitação: `150.05` e aperta `Enter`.
   * O valor é positivo, então o método executa `return 150.05;`.
   * A linha `balance += deposit();` soma esse dinheiro ao saldo da conta!
   * O menu reaparece automaticamente com o cursor piscando ao lado de `Enter your choice (1-4): `.

4. **Imagem 4 (Minuto 4:46:07 — A Prova dos Nove: O Saldo Mudou de Verdade!):**
   * Para provar que o dinheiro realmente entrou no cofre da conta, o professor digita `1` (Show Balance) e dá `Enter`.
   * **O Console Responde com Orgulho:**
     ```text
     Enter your choice (1-4): 1
     ***************
     $150.05
     ***************
     BANKING PROGRAM
     ...
     ```
   * A conta, que antes tinha zero dólares, agora exibe oficialmente **`$150.05`**!
   * A funcionalidade de depósito está 100% perfeita, segura e integrada ao saldo!

---

## 🧠 Entendendo as Novidades Como um Iniciante

### 1. O Que É o Operador `+=` na Linha 29?
No print anterior, o autor tinha escrito:
```java
balance = balance + deposit();
```
Agora ele escreveu:
```java
balance += deposit();
```
* Em Java, o operador `+=` é apenas um **atalho carinhoso** que significa exatamente: *"Pegue o que já está na variável e some com o que vem depois!"*.
* As duas formas fazem rigorosamente a mesma coisa na memória, mas `balance += deposit();` é a forma que todo desenvolvedor profissional prefere escrever.

### 2. Por Que a Tentativa de `-1000000` Foi um Sucesso Absoluto?
* Quando o professor digitou `-1000000`:
  1. O método `deposit()` recebeu `amount = -1000000.0`.
  2. O `if(-1000000 < 0)` deu **VERDADEIRO**!
  3. Imprimiu `Amount can't be negative`.
  4. Executou `return 0;`.
  5. No `main`: `balance += 0;` $\rightarrow$ O saldo não mudou! O banco se defendeu sozinho!

### 3. Como o `150.05` Foi Parar no `$150.05` do Saldo?
* Quando ele digitou `150.05`:
  1. O `if(150.05 < 0)` deu **FALSO**.
  2. Caiu no `else { return amount; }`, que devolveu `150.05`.
  3. No `main`: `balance += 150.05;` transformou o saldo de `0.0` em `150.05`.
  4. Quando ele apertou `1` em seguida, o `showBalance(balance)` pegou esse `150.05` e formatou como `$150.05` com a moldura de asteriscos!

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
                case 2 -> balance += deposit(); // Refatorado para o operador +=
                case 3 -> System.out.println("WITHDRAW");
                case 4 -> isRunning = false;
                default -> System.out.println("INVALID CHOICE");
            }
        }

        // withdraw()

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
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 5:** `static Scanner scanner = new Scanner(System.in);`
* **O que faz:** Leitor do teclado compartilhado por todos os métodos.
* **Console:** **Nada**.

---

### **Linha 10:** `double balance = 0;`
* **O que faz:** Saldo inicial da conta guardando `0.0`.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Mantém o caixa eletrônico ligado rodando o menu.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(Menu Visual)*
* **Console:** Desenha o cabeçalho e as opções de 1 a 4.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Lê a opção do usuário.
* **Console:** O número digitado aparece na frente dos dois pontos.

---

### **Linha 27:** `switch(choice){`
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`
* **O que faz:** Se o usuário escolher `1`, chama o método que imprime o saldo formatado entre asteriscos.
* **Console (no teste 3 da Imagem 4):**
  ```text
  ***************
  $150.05
  ***************
  ```

---

### **Linha 29:** `    case 2 -> balance += deposit();`  *(⭐ A LINHA EM DESTAQUE!)*
* **O que faz:**
  1. Chama o método `deposit()`.
  2. Recebe a quantia devolvida.
  3. Soma ao saldo usando `+=` (`balance = balance + deposit()`).
* **Console:** As mensagens e perguntas do depósito aparecem na tela durante o processo.

---

### **Linhas 30 a 34:** `case 3 -> ...`, `case 4 -> ...`, `default -> ...`, `}` e `}`
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **Console:** **Nada**.

---

### **Linhas 45 a 48:** *(O Método showBalance)*
```java
static void showBalance(double balance){
    System.out.println("***************");
    System.out.printf("$%.2f\n", balance);
}
```
* **O que faz:** Imprime a moldura de asteriscos e o valor atual de `balance` formatado com 2 casas decimais.
* **Console:** Exibiu `$150.05` no teste final!

---

### **Linhas 50 a 62:** *(O Método deposit)*
```java
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
```
* **No teste com `-1000000` (Imagem 1):**
  * `amount < 0` $\rightarrow$ Entrou no `if`.
  * **Console imprimiu:** `Amount can't be negative`.
  * Retornou `0`.
* **No teste com `150.05` (Imagem 3):**
  * `amount < 0` $\rightarrow$ Deu falso, caiu no `else`.
  * Retornou `150.05` para ser somado ao saldo!

---

## 📺 O que Aparece no Console (A Sequência Real dos 3 Testes do Professor):

### Teste 1: A Tentativa de Depósito Negativo (Imagem 1)
```text
Enter your choice (1-4): 2
Enter an amount to be deposited: -1000000
Amount can't be negative
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

---

### Teste 2: O Depósito Válido de $150.05 (Imagens 2 e 3)
```text
Enter your choice (1-4): 2
Enter an amount to be deposited: 150.05
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

---

### Teste 3: A Consulta Comprovando o Saldo (Imagem 4)
```text
Enter your choice (1-4): 1
***************
$150.05
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): █
```

---

## 🏆 Status do Projeto: 2 de 4 Opções Prontas!

1. ✅ **`1. Show Balance`**: 100% pronto e testado.
2. ✅ **`2. Deposit`**: 100% pronto, protegido contra valores negativos e somando ao saldo com `+=`.
3. ⏳ **`3. Withdraw`**: A próxima missão! O método de saque precisará de duas validações: não aceitar saque negativo E não aceitar sacar mais do que tem na conta (saldo insuficiente)!
4. ✅ **`4. Exit`**: 100% pronto com `isRunning = false;`.
