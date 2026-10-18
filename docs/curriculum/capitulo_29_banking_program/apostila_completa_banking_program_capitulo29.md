# 💰 Capítulo 29: O Guia Definitivo e Completo do Banking Program (Simulador de Caixa Eletrônico / ATM em Java) — Partes 1 a 17

> **Documento Oficial de Estudos para Cópia no Google Docs:** Este material foi estruturado para ser **100% autossuficiente**. Ele descreve todos os contextos visuais das aulas do canal Bro Code (*Java Full Course for free*), inclui diagramas de fluxo do sistema bancário, tabelas de memória, fórmulas de formatação monetária com `printf`, varreduras linha por linha e códigos compiláveis sem erros.

---

## 📑 Índice da Apostila
1. [Visão Geral e Arquitetura do Caixa Eletrônico (ATM)](#1-visão-geral-e-arquitetura)
2. [Variáveis Principais e a Promoção do `Scanner` para Escopo de Classe (`static`)](#2-variáveis-e-escopo-do-scanner)
3. [O Menu Interativo e o Laço Contínuo (`while (isRunning)`)](#3-o-menu-interativo-e-o-laço-while)
4. [O Enhanced Switch Moderno (`switch (choice) ->`)](#4-o-enhanced-switch-moderno)
5. [Módulo 1: Consulta de Saldo (`showBalance`) e a Formatação Monetária (`printf`)](#5-módulo-showbalance-e-printf)
6. [Módulo 2: Depósito de Dinheiro (`deposit`), Validação e Operador `+=`](#6-módulo-deposit-e-operador-mais-igual)
7. [Módulo 3: Saque de Dinheiro (`withdraw`), Validação Dupla e Operador `-=`](#7-módulo-withdraw-e-operador-menos-igual)
8. [Módulo 4: Encerramento do Sistema (`Exit`) e Fechamento de Recursos](#8-módulo-exit-e-encerramento)
9. [Mapa Mental de Memória RAM (Stack vs. Static Class Scope)](#9-mapa-mental-de-memória-ram)
10. [Roteiro de Teste Completo ao Vivo (Com Centavos e Tratamento de Erros)](#10-roteiro-de-teste-completo)
11. [Código-Fonte Completo e Compilável de Todo o Capítulo 29](#11-código-fonte-completo)

---

<a name="1-visão-geral-e-arquitetura"></a>
## 🏦 1. Visão Geral e Arquitetura do Caixa Eletrônico (ATM)

O **Banking Program** é o primeiro grande projeto prático completo do curso de Java. Ele simula o software real de um **Terminal de Autoatendimento Bancário (ATM)**.

### 🏛️ Os 4 Pilares do Sistema:
```text
                         ┌─────────────────────────────┐
                         │      MÉTODO PRINCIPAL       │
                         │         main(...)           │
                         └──────────────┬──────────────┘
                                        │
         ┌──────────────────────────────┼──────────────────────────────┐
         ▼                              ▼                              ▼
┌──────────────────┐           ┌──────────────────┐           ┌──────────────────┐
│   showBalance()  │           │    deposit()     │           │    withdraw()    │
│  (Apenas Exibe)  │           │ (Retorna double) │           │ (Retorna double) │
└──────────────────┘           └──────────────────┘           └──────────────────┘
```

1. **`showBalance(balance)`:** Recebe o saldo atual e exibe na tela formatado como moeda (`$0.00`) dentro de uma moldura de asteriscos.
2. **`deposit()`:** Pergunta quanto o cliente quer depositar, valida se o valor é positivo e devolve a quantia para ser somada no `main` (`balance += deposit();`).
3. **`withdraw(balance)`:** Recebe o saldo atual, pergunta quanto o cliente quer sacar, verifica se há saldo suficiente e se o valor é positivo, e devolve a quantia para ser subtraída no `main` (`balance -= withdraw(balance);`).
4. **`Exit (Opção 4)`:** Desliga o laço do sistema (`isRunning = false`), agradece ao cliente e fecha o canal do teclado.

---

<a name="2-variáveis-e-escopo-do-scanner"></a>
## 🔑 2. Variáveis Principais e a Promoção do `Scanner` para Escopo de Classe (`static`)

### 2.1 As 3 Variáveis de Estado no `main`:
```java
double balance = 0;   // O cofre bancário do cliente (começa zerado)
int choice;           // A opção que o cliente digita no menu (1, 2, 3 ou 4)
boolean isRunning = true; // A chave geral que mantém o caixa eletrônico ligado
```

* **Por que `double` e não `int` para o saldo?** Porque dinheiro no mundo real tem centavos! Um banco que usa `int` não conseguiria registrar uma conta com R$ 10,50.
* **Por que `boolean isRunning = true`?** Para que o programa continue rodando sem parar até que o cliente decida explicitamente sair.

### 2.2 O Grande Dilema do `Scanner`: Onde Colocar?
No início do projeto, o autor colocou `Scanner scanner = new Scanner(System.in);` dentro do método `main`.
Mas repare no problema:
* O método `deposit()` precisa do teclado para perguntar o valor do depósito.
* O método `withdraw()` precisa do teclado para perguntar o valor do saque.

Se criássemos um `new Scanner(System.in)` dentro de cada método, teríamos múltiplos leitores brigando pelo mesmo teclado, e se fechássemos um (`scanner.close()`), mataríamos a entrada do sistema inteiro!

### 🌟 A Solução de Ouro: Escopo de Classe (`static Scanner scanner`)
O autor moveu o `Scanner` para **fora de todos os métodos**, logo abaixo da declaração da classe:
```java
public class Main {

    static Scanner scanner = new Scanner(System.in); // Escopo de Classe!

    public static void main(String[] args) {
        ...
```
* **O que `static` significa aqui?** Significa que o objeto `scanner` pertence à classe inteira. Agora, tanto o `main`, quanto o `deposit()`, quanto o `withdraw()` podem enxergar e usar o **mesmo leitor** sem conflitos!

---

<a name="3-o-menu-interativo-e-o-laço-while"></a>
## 🔄 3. O Menu Interativo e o Laço Contínuo (`while (isRunning)`)

Em programas de computador reais, o sistema não roda uma vez e fecha. Ele roda em um **laço de eventos** (*Event Loop*).

```java
while (isRunning) {
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
    ...
```

* Enquanto `isRunning` for `true`, o Java desenha o menu, espera a digitação do cliente, processa a operação solicitada e **volta para o início**, desenhando o menu novamente!
* O uso de `System.out.print` (sem `ln`) na pergunta deixa o cursor piscando diretamente ao lado dos dois pontos `: `.

---

<a name="4-o-enhanced-switch-moderno"></a>
## ⚡ 4. O Enhanced Switch Moderno (`switch (choice) ->`)

Para rotear a escolha do usuário entre as 4 opções, o autor usa a sintaxe mais moderna do Java (Java 14+): o **Switch com Seta (`->`)**:

```java
switch (choice) {
    case 1 -> showBalance(balance);
    case 2 -> balance += deposit();
    case 3 -> balance -= withdraw(balance);
    case 4 -> isRunning = false;
    default -> System.out.println("INVALID CHOICE");
}
```

### 🥊 Switch Tradicional vs. Switch Moderno:
| Característica | Switch Tradicional (`case 1:`) | Switch Moderno (`case 1 ->`) |
| :--- | :--- | :--- |
| **Palavra `break;`** | Obrigatória em cada caso! | **Desnecessária!** O Java não cai nos casos de baixo. |
| **Legibilidade** | Longo e poluído | Limpo, direto e matemático |
| **Risco de Bugs** | Alto (se esquecer o `break`, executa tudo) | Zero risco de efeito cascata |

---

<a name="5-módulo-showbalance-e-printf"></a>
## 💵 5. Módulo 1: Consulta de Saldo (`showBalance`) e a Formatação Monetária (`printf`)

### 5.1 O Código do Método:
```java
static void showBalance(double balance) {
    System.out.println("***************");
    System.out.printf("$%.2f\n", balance);
}
```

### 5.2 Dissecando o Especificador de Formato `$%.2f\n`:
Esta é uma das instruções mais elegantes do Java:
* **`$`:** É um caractere de texto comum que será impresso na tela (o cifrão da moeda).
* **`%`:** O sinal de alerta que avisa ao compilador: *"Atenção, aqui entrará uma variável formatada!"*.
* **`.2`:** Instrução de precisão: *"Exiba exatamente **duas casas decimais** após o ponto"*.
  * Se o saldo for `0`, imprime `$0.00`.
  * Se o saldo for `9.999`, arredonda e imprime `$10.00`.
  * Se o saldo for `5.5`, preenche com zero e imprime `$5.50`.
* **`f`:** Significa *floating-point* (número de ponto flutuante / decimal, como `double` ou `float`).
* **`\n`:** Quebra de linha manual, necessária porque o `printf` não pula de linha sozinho!

---

<a name="6-módulo-deposit-e-operador-mais-igual"></a>
## 📥 6. Módulo 2: Depósito de Dinheiro (`deposit`), Validação e Operador `+=`

### 6.1 O Código do Método:
```java
static double deposit() {

    double amount;

    System.out.print("Enter an amount to be deposited: ");
    amount = scanner.nextDouble();

    if (amount < 0) {
        System.out.println("Amount can't be negative");
        return 0;
    }
    else {
        return amount;
    }
}
```

### 6.2 A Conexão com o `main`:
No `main`, o retorno do método é somado diretamente ao saldo:
```java
case 2 -> balance += deposit();
```

### 🧠 Análise Passo a Passo da Lógica:
1. **Se o cliente digitar um valor válido (ex: `100.50`):**
   * O teste `100.50 < 0` dá falso.
   * O método entra no `else` e executa `return 100.50;`.
   * No `main`, o Java faz: `balance += 100.50` $\rightarrow$ o saldo aumenta em $100.50!
2. **Se o cliente tentar trapacear digitando valor negativo (ex: `-50`):**
   * O teste `-50 < 0` dá verdadeiro!
   * O método imprime o alerta: `"Amount can't be negative"`.
   * O método executa `return 0;`.
   * No `main`, o Java faz: `balance += 0` $\rightarrow$ o saldo não é alterado, protegendo o banco!

---

<a name="7-módulo-withdraw-e-operador-menos-igual"></a>
## 📤 7. Módulo 3: Saque de Dinheiro (`withdraw`), Validação Dupla e Operador `-=`)

O método de saque é o mais crítico de qualquer banco, pois ele lida com duas ameaças: clientes querendo sacar o que não têm (saldo insuficiente) e valores negativos.

### 7.1 O Código do Método:
```java
static double withdraw(double balance) {

    double amount;

    System.out.print("Enter amount to be withdrawn: ");
    amount = scanner.nextDouble();

    if (amount > balance) {
        System.out.println("INSUFFICIENT FUNDS");
        return 0;
    }
    else if (amount < 0) {
        System.out.println("Amount can't be negative");
        return 0;
    }
    else {
        return amount;
    }
}
```

### 7.2 A Conexão com o `main`:
```java
case 3 -> balance -= withdraw(balance);
```

### 🛡️ As 3 Vias de Decisão:
1. **Tentativa de Saque Maior que o Saldo (`amount > balance`):**
   * Exemplo: Saldo é `$100.00` e o cliente pede `$150.00`.
   * Dispara: `"INSUFFICIENT FUNDS"`.
   * Retorna `0` $\rightarrow$ `balance -= 0` (saldo intacto).
2. **Tentativa de Saque Negativo (`amount < 0`):**
   * Exemplo: Cliente digita `-$30.00`.
   * Dispara: `"Amount can't be negative"`.
   * Retorna `0` $\rightarrow$ `balance -= 0` (impede que um saque negativo funcione como um depósito falso!).
3. **Saque Válido (`else`):**
   * Exemplo: Saldo é `$100.00` e o cliente pede `$40.00`.
   * Retorna `40.00`.
   * No `main`: `balance -= 40.00` $\rightarrow$ Saldo atualizado para `$60.00`!

---

<a name="8-módulo-exit-e-encerramento"></a>
## 🚪 8. Módulo 4: Encerramento do Sistema (`Exit`) e Fechamento de Recursos

Quando o cliente digita a opção `4`:
```java
case 4 -> isRunning = false;
```
1. A variável `isRunning` passa a valer `false`.
2. O laço `while (isRunning)` avalia a condição, percebe que ela é falsa e encerra imediatamente.
3. Fora do laço `while`, o programa executa a rotina final de desligamento:
```java
System.out.println("***************************");
System.out.println("Thank you! Have a nice day!");
System.out.println("***************************");

scanner.close();
```
* **`scanner.close()`:** Devolve o fluxo de leitura do teclado ao sistema operacional, evitando o aviso de vazamento de recursos (*resource leak*).

---

<a name="9-mapa-mental-de-memória-ram"></a>
## 🧠 9. Mapa Mental de Memória RAM (Stack vs. Static Class Scope)

Veja como a memória do computador é gerenciada durante o funcionamento do caixa eletrônico:

```text
MEMÓRIA DA CLASSE (ÁREA ESTÁTICA)
┌─────────────────────────────────────────────────────────────┐
│ static Scanner scanner ──► Conectado ao Teclado (System.in) │
│ (Compartilhado por todos os métodos sem duplicação)         │
└─────────────────────────────────────────────────────────────┘

MEMÓRIA STACK (Durante a execução de withdraw)
┌───────────────────────────────────────┐
│ Frame do Método: withdraw             │
│ - balance (cópia recebida): 100.00    │
│ - amount: 40.00                       │
├───────────────────────────────────────┤
│ Frame do Método: main                 │
│ - balance: 100.00                     │
│ - choice: 3                           │
│ - isRunning: true                     │
└───────────────────────────────────────┘
```

* Quando `withdraw()` termina, seu espaço na Stack é destruído e o controle volta ao `main` com o valor devolvido.

---

<a name="10-roteiro-de-teste-completo"></a>
## 🧪 10. Roteiro de Teste Completo ao Vivo (Simulação Exata)

Aqui está a transcrição de um ciclo de teste completo com todas as proteções em ação:

```text
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
Enter an amount to be deposited: 100.50

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
$100.50

***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 3
Enter amount to be withdrawn: 150.00
INSUFFICIENT FUNDS

***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 3
Enter amount to be withdrawn: 40.25

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
$60.25

***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 4
***************************
Thank you! Have a nice day!
***************************

Process finished with exit code 0
```

---

<a name="11-código-fonte-completo"></a>
## 💻 11. Código-Fonte Completo e Compilável de Todo o Capítulo 29

Copie e cole este código diretamente no seu arquivo `Main.java` no IntelliJ IDEA para ter o Caixa Eletrônico completo e 100% funcional:

```java
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // JAVA BANKING PROGRAM

        double balance = 0;
        boolean isRunning = true;
        int choice;

        while (isRunning) {
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

            switch (choice) {
                case 1 -> showBalance(balance);
                case 2 -> balance += deposit();
                case 3 -> balance -= withdraw(balance);
                case 4 -> isRunning = false;
                default -> System.out.println("INVALID CHOICE");
            }
        }

        System.out.println("***************************");
        System.out.println("Thank you! Have a nice day!");
        System.out.println("***************************");

        scanner.close();
    }

    // SHOW BALANCE
    static void showBalance(double balance) {
        System.out.println("***************");
        System.out.printf("$%.2f\n", balance);
    }

    // DEPOSIT
    static double deposit() {

        double amount;

        System.out.print("Enter an amount to be deposited: ");
        amount = scanner.nextDouble();

        if (amount < 0) {
            System.out.println("Amount can't be negative");
            return 0;
        }
        else {
            return amount;
        }
    }

    // WITHDRAW
    static double withdraw(double balance) {

        double amount;

        System.out.print("Enter amount to be withdrawn: ");
        amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("INSUFFICIENT FUNDS");
            return 0;
        }
        else if (amount < 0) {
            System.out.println("Amount can't be negative");
            return 0;
        }
        else {
            return amount;
        }
    }
}
```

---

Pronto! Agora você tem em mãos a **Apostila Definitiva do Banking Program**, rica em detalhes, diagramas de memória, tratamento de erros e com o código-fonte unificado perfeitamente documentado para o seu Google Docs! 💰🏦📘✨
