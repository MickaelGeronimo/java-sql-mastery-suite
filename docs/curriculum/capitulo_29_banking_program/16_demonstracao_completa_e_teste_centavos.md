# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 16: O Teste de Fogo Completo: Saldo Inicial `$0.00`, Depósito de `$1000.00` e o Saque de 1 Centavo Resultando em `$999.99`!
**Timestamps do vídeo:** `4:50:51` até `4:51:08 / 12:00:00`  
**Tema:** A consagração final do sistema bancário! O professor Bro Code executa o teste de ponta a ponta que todo desenvolvedor profissional faz antes de entregar um software: inicia a conta do zero (`$0.00`), deposita `$1000.00`, e realiza o teste definitivo de precisão decimal sacando exatamente 1 centavo (`0.01`), comprovando que o saldo é recalculado para `$999.99` sem nenhum erro matemático.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o teste da vida real que o professor realizou:

1. **Imagem 1 (Minuto 4:50:51 — A Conta Zerada Recém-Aberta):**
   * O professor inicia o programa do zero.
   * O console imprime a moldura de asteriscos e o saldo inicial da conta:
     ```text
     ***************
     $0.00
     ***************
     BANKING PROGRAM
     ...
     ```
   * A conta bancária começa limpa, exatamente com zero dólares e zero centavos (`$0.00`).

2. **Imagem 2 (Minuto 4:51:03 — O Depósito de Mil Dólares e a Escolha do Saque):**
   * O professor faz um depósito generoso de **`1000.00`**.
   * O saldo atualiza para `$1000.00`.
   * No menu reaberto, ele escolhe a opção **`3` (Withdraw)** para testar o saque.
   * O terminal pergunta na linha de baixo: `Enter amount to be withdrawn: `.

3. **Imagem 3 (Minuto ~4:51:05 — O Destaque nos Mil Dólares):**
   * Com o cursor do mouse, o professor **seleciona o texto `$1000.00` em azul**.
   * Ele mostra visualmente aos alunos: *"Vejam só: tínhamos zero, depositamos mil dólares e a conta guardou exatamente $1000.00!"*.

4. **Imagem 4 (Minuto 4:51:08 — O Teste do Centavo e o Saldo de `$999.99`):**
   * O professor digita para sacar apenas **`0.01`** (um centavo de dólar!).
   * O método `withdraw` valida que `0.01` é menor que `1000.00` e devolve `0.01`.
   * A linha `balance -= withdraw(balance);` faz a subtração: `1000.00 - 0.01 = 999.99`.
   * O professor aperta `1` (Show Balance) e o console exibe a prova viva da precisão:
     ```text
     ***************
     $999.99
     ***************
     BANKING PROGRAM
     ...
     ```
   * O saldo restante é rigorosamente **`$999.99`**! Nenhuma fração de centavo foi perdida!

---

## 🧠 Por Que Testar Sacar 1 Centavo (`0.01`)?

Na programação, cálculos com números decimais (`double` e `float`) às vezes podem sofrer com pequenos erros de arredondamento binário (por exemplo, `1000 - 0.01` virar `999.98999999999`).

O professor Bro Code fez esse teste de propósito para demonstrar aos alunos dois grandes sucessos do nosso código:
1. **A precisão do cálculo financeiro:** O Java subtraiu `0.01` de `1000.00` e obteve `999.99`.
2. **O poder do formatador `$%.2f\n`:** Ao usar `%.2f`, mesmo se houvesse uma dízima microscópica em milésimos de centavo, o Java arredondaria e exibiria os dois dígitos de centavos com perfeição absoluta: `$999.99`!

---

## 💻 Código Completo e Definitivo do Projeto (`Main.java`)

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
                case 3 -> balance -= withdraw(balance);
                case 4 -> isRunning = false;
                default -> System.out.println("INVALID CHOICE");
            }
        }

        // EXIT MESSAGE
        System.out.println("***************************");
        System.out.println("Thank you! Have a nice day!");
        System.out.println("***************************");

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

## 🔍 Explicação Linha por Linha do Fluxo Deste Teste Real

### **Passo 1: A Inicialização da Conta**
* `double balance = 0;` $\rightarrow$ A variável local `balance` do `main` começa valendo `0.0`.
* O usuário digita `1` (Show Balance) $\rightarrow$ O método `showBalance(balance)` imprime:
  ```text
  ***************
  $0.00
  ***************
  ```

---

### **Passo 2: O Depósito de Mil Dólares**
* O usuário digita `2` (Deposit).
* O `deposit()` pergunta: `Enter an amount to be deposited: `.
* O usuário digita `1000` e dá `Enter`.
* Como `1000 > 0`, cai no `else { return amount; }` e devolve `1000.0`.
* A Linha 31 do `main` faz: `balance += 1000.0;` $\rightarrow$ Agora `balance` vale `1000.0`!
* O usuário consulta com a opção `1` e o console imprime `$1000.00`!

---

### **Passo 3: O Saque Cirúrgico de 1 Centavo**
* O usuário digita `3` (Withdraw).
* A Linha 32 do `main` chama: `balance -= withdraw(balance);`, entregando o saldo de `1000.0`.
* O `withdraw` pergunta: `Enter amount to be withdrawn: `.
* O usuário digita: `0.01`.
* O método avalia as travas:
  1. `0.01 > 1000.0`? **FALSO** (temos saldo suficiente!).
  2. `0.01 < 0`? **FALSO** (não é número negativo!).
  3. Cai no `else { return amount; }` e devolve `0.01`.
* O `main` recebe `0.01` e faz a subtração: `1000.0 - 0.01 = 999.99`.
* A variável `balance` agora guarda exatamente `999.99`!

---

### **Passo 4: A Prova dos Nove com o Show Balance**
* O usuário digita `1` (Show Balance).
* O Java salta para `showBalance(999.99)`.
* Executa `printf("$%.2f\n", 999.99);`.
* **Console exibe:**
  ```text
  ***************
  $999.99
  ***************
  ```

---

## 📺 Transcrição Fiel do Console Durante Esta Demonstração:

```text
***************
$0.00
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 2
Enter an amount to be deposited: 1000.00
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 1
***************
$1000.00
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 3
Enter amount to be withdrawn: 0.01
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 1
***************
$999.99
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

## 🏅 O Resumo Desta Grande Conquista Educacional

Neste projeto de consolidação do Capítulo 29, você utilizou na prática todos os conceitos aprendidos no curso:
1. **Estrutura de Classes e Métodos:** Como dividir um programa grande em funções especializadas.
2. **Parâmetros e Argumentos:** Passando o saldo para onde ele era necessário (`showBalance` e `withdraw`).
3. **Retorno de Métodos (`return`):** Fazendo funções devolverem dinheiro calculado (`deposit` e `withdraw`).
4. **Escopo de Classe:** O `static Scanner scanner` compartilhado por todos sem duplicação.
5. **Estrutura de Repetição Contínua (`while`):** Criando o loop infinito controlado por uma flag booleana (`isRunning`).
6. **Controle de Fluxo com Enhanced Switch:** Seleção limpa sem risco de *break* esquecido.
7. **Formatação de Moeda:** Dominando o `$%.2f\n` para exibir centavos reais de banco.
8. **Validação Defensiva:** Blindando a aplicação contra saldos insuficientes e valores negativos.
