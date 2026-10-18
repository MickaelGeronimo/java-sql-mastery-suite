# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 12: Validação de Segurança com `if-else` no `deposit()` e a Conexão no `main` (`balance = balance + deposit()`)
**Timestamps do vídeo:** `4:44:00` até `4:45:15 / 12:00:00`  
**Tema:** Segurança bancária e manipulação de saldo! O professor Bro Code implementa a validação com `if-else` no método `deposit()` para barrar depósitos negativos (`Amount can't be negative`), substitui o texto provisório `"DEPOSIT"` no `main`, e conecta a operação financeira real: `balance = balance + deposit();`.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto ~4:44:30 — A Blindagem de Segurança no `deposit`):**
   * No método `deposit()`, entre as Linhas 54 e 60, o professor programa uma estrutura de decisão com `if-else`:
     ```java
     if(amount < 0){
         System.out.println("Amount can't be negative");
         return 0;
     }
     else{
         return amount;
     }
     ```
   * Ele explica: *"Nunca confie cegamente no usuário! Se alguém tentar depositar um valor negativo como `-50`, o banco deve rejeitar, avisar o cliente e devolver zero!"*.

2. **Imagem 2 (Minuto 4:44:45 — Selecionando o Texto Provisório no `main`):**
   * O professor rola o código para cima até o `switch` dentro do `main`.
   * Na **Linha 29**, ele seleciona com o mouse todo o texto:  
     `System.out.println("DEPOSIT");` (ficando com uma faixa azul retangular).
   * Ele vai substituir esse texto falso pela operação matemática de depósito de verdade!

3. **Imagem 3 (Minuto ~4:45:00 — Escrevendo a Equação do Saldo):**
   * O autor digita a chamada na Linha 29:  
     `case 2 -> balance = balance + deposit();`
   * Ele mostra aos alunos: *"O novo saldo (`balance`) será igual ao saldo que já tínhamos antes somado com a quantia que o método `deposit()` devolver!"*.

4. **Imagem 4 (Minuto 4:45:15 — Destaque na Variável `balance`):**
   * Na Linha 29, o professor seleciona com o mouse a primeira palavra `balance` (ficando em azul).
   * Ele reforça o conceito de atribuição em Java: a parte da direita (`balance + deposit()`) é calculada primeiro, e o resultado final é guardado de volta dentro da caixinha `balance` da esquerda!

---

## 🧠 Entendendo a Lógica Como um Iniciante

### 1. Por Que Precisamos do `if(amount < 0)`? (Validação de Entrada)
Imagine se um cliente fosse ao caixa eletrônico e depositasse **-R$ 100,00**:
* Se o sistema não tivesse esse `if`, o saldo faria: `balance + (-100)` $\rightarrow$ ou seja, o dinheiro sumiria da conta!
* Para impedir trapaças ou erros de digitação:
  * Se o cliente digitar `-10` $\rightarrow$ O Java entra no `if`, imprime `"Amount can't be negative"` (A quantia não pode ser negativa) e executa `return 0;`. Somar zero ao saldo não altera nada!
  * Se o cliente digitar `50` $\rightarrow$ O Java pula o `if`, cai no `else` e executa `return amount;`, entregando os 50 reais para o banco!

### 2. Como Funciona a Linha `balance = balance + deposit();`?
O Java lê essa linha em **3 etapas**:
1. **Etapa 1:** O Java vê `deposit()` e pausa o `main`. Ele pula para a Linha 47, pergunta quanto o usuário quer depositar, valida e devolve o valor (ex: `100.0`).
2. **Etapa 2:** O Java substitui `deposit()` pelo número `100.0`. Se a conta tinha `10.99`, a conta fica: `10.99 + 100.0` = `110.99`.
3. **Etapa 3:** O sinal de igual `=` pega esse novo total `110.99` e guarda dentro da variável `balance`. O saldo cresceu de verdade!

*(Nota: Mais tarde, você verá que `balance = balance + deposit();` também pode ser escrito de forma resumida como `balance += deposit();`)*.

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
                case 2 -> balance = balance + deposit(); // Conectado com o método de depósito!
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
* **O que faz:** Leitor do teclado compartilhado por toda a classe.
* **Console:** **Nada**.

---

### **Linha 7:** `public static void main(String[] args) {`
* **O que faz:** Ponto de entrada do programa.
* **Console:** **Nada**.

---

### **Linha 10:** `double balance = 0;`
* **O que faz:** Guarda o saldo bancário.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Loop do caixa eletrônico.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(Exibição do Menu Visual)*
* **Console:** Desenha o menu completo na tela.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **Console:** Lê a opção do usuário.

---

### **Linha 27:** `switch(choice){`
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`
* **Console:** Mostra o saldo formatado se a opção for 1.

---

### **Linha 29:** `    case 2 -> balance = balance + deposit();`  *(⭐ A GRANDE CONEXÃO DO PRINT!)*
* **O que faz:**
  1. Chama o método `deposit()` na Linha 47.
  2. Espera o método devolver o valor depositado.
  3. Soma esse valor ao `balance` existente e atualiza a variável.
* **Console:** As mensagens do depósito aparecem na tela durante a operação.

---

### **Linhas 30 a 32:** `case 3 -> ...`, `case 4 -> ...`, `default -> ...`
* **Console:** Executam conforme as outras escolhas.

---

### **Linhas 33 e 34:** `}` e `}` *(Fecha switch e fecha while)*
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){...}`
* **Console:** **Nada**.

---

### **Linha 47:** `static double deposit(){`
* **O que faz:** Abre o método de depósito. Retorna um `double` para quem chamou.
* **Console:** **Nada**.

---

### **Linha 49:** `    double amount;`
* **O que faz:** Cria a variável local para receber o valor que o usuário vai digitar.
* **Console:** **Nada**.

---

### **Linha 51:** `    System.out.print("Enter an amount to be deposited: ");`
* **O que faz:** Imprime a pergunta de depósito mantendo o cursor na mesma linha.
* **Console:** Exibe a frase quando o método for acionado.

---

### **Linha 52:** `    amount = scanner.nextDouble();`
* **O que faz:** Lê o número decimal com centavos digitado pelo usuário.
* **Console:** O valor digitado aparece na frente da pergunta.

---

### **Linha 54:** `    if(amount < 0){`  *(⭐ A DEFESA DO BANCO!)*
* **O que faz:** Avalia se a quantia digitada é menor do que zero (número negativo).
* **Console:** **Nada**.

---

### **Linha 55:** `        System.out.println("Amount can't be negative");`
* **O que faz:** Se for negativo, imprime a mensagem de advertência e pula a linha.
* **Console (se digitar negativo):** Imprime `Amount can't be negative`.

---

### **Linha 56:** `        return 0;`
* **O que faz:** Encerra o método devolvendo `0`. Assim, na Linha 29 o saldo somará `+ 0`, mantendo o dinheiro da conta intacto.
* **Console:** **Nada**.

---

### **Linha 57:** `    }`
* **Console:** **Nada**.

---

### **Linha 58:** `    else{`
* **O que faz:** Se o valor for válido (zero ou positivo).
* **Console:** **Nada**.

---

### **Linha 59:** `        return amount;`
* **O que faz:** Encerra o método entregando a quantia real digitada pelo cliente para ser somada ao saldo.
* **Console:** **Nada**.

---

### **Linhas 60 a 62:** `}`, `}` e `}`
* **O que faz:** Fecha o `else`, fecha o método `deposit` e fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console:

**Nada!**  
O console continua fechado em todos os 4 prints. O professor programou a validação de segurança e conectou o método ao menu no editor.

---

## 🔮 O Que Esperar do Próximo Passo?

Agora que o método `deposit()` está 100% programado e conectado na Linha 29:
* O professor vai apertar o botão verde de **Play**!
* Ele vai testar a opção `2` (Deposit), digitar um valor positivo (ex: `$50.00`), e depois digitar `1` (Show Balance) para provar para a turma que o saldo aumentou com sucesso!
* Ele também vai testar digitar um número negativo (ex: `$-20`) para mostrar a mensagem de erro em ação!
