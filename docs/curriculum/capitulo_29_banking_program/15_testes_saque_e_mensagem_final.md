# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 15: Testes de Saque, Validação de Saldo e o Grand Finale: Mensagem de Despedida!
**Timestamps do vídeo:** `4:49:50` até `~4:51:00 / 12:00:00`  
**Tema:** O gran finale do projeto bancário! O professor Bro Code testa todos os cenários extremos de saque no terminal (saldo insuficiente com `$1000000`, quantia negativa com `$-420.69` e saque legítimo restando `$49.99`), comprova que a blindagem funciona 100%, e coroa o programa adicionando uma mensagem de despedida elegante com moldura de asteriscos: `"Thank you! Have a nice day!"`.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja a sequência vitoriosa registrada na tela do professor:

1. **Imagem 1 (Minuto 4:49:50 — Teste de Saldo Insuficiente):**
   * O professor escolhe a opção `3` (Withdraw).
   * O sistema pergunta: `Enter amount to be withdrawn: `.
   * O autor digita a quantia absurda de **`1000000`** (um milhão de dólares!).
   * O Java avalia `if(amount > balance)`, bloqueia o saque na hora e imprime:  
     ```text
     INSUFFICIENT FUNDS
     ```
   * O banco devolve `0`, não desconta nada e o menu reaparece seguro.

2. **Imagem 2 (Minuto ~4:50:15 — Teste de Saque Negativo):**
   * O professor escolhe `3` novamente e digita: **`-420.69`**.
   * A condição `else if(amount < 0)` é acionada e o console imprime:  
     ```text
     Amount can't be negative
     ```
   * O autor seleciona essa frase com o mouse, deixando-a com uma **faixa azul retangular de destaque**. A segunda trava de segurança está aprovada!

3. **Imagem 3 (Minuto ~4:50:35 — Saque Legítimo e Seleção do Exit):**
   * O professor faz um saque válido.
   * Em seguida, digita `1` (Show Balance) e o saldo exibe o valor restante:  
     ```text
     ***************
     $49.99
     ***************
     ```
   * No menu seguinte, o autor usa o mouse para **selecionar a opção `4. Exit` em azul**, preparando-se para demonstrar a saída da aplicação.

4. **Imagem 4 (Minuto ~4:51:00 — A Mensagem de Despedida no Editor):**
   * No editor, logo após o fechamento do laço `while` (Linha 34), o autor substitui o antigo comentário `// EXIT MESSAGE` por um banner de 27 asteriscos:
     ```java
     System.out.println("***************************");
     System.out.println("Thank you! Have a nice day!");
     System.out.println("***************************");
     ```
   * Ele dobra os métodos `showBalance` e `deposit` em `{...}` para exibir a elegância da arquitetura: o `main` limpo, a despedida ao cliente, o fechamento do `scanner.close()` e o método `withdraw` ativo com `1 usage`.

---

## 🧠 A Vitória da Engenharia de Software: Por Que Esses Testes São Críticos?

O que o professor Bro Code ensinou nesta aula é a base da segurança de sistemas no mundo corporativo:
* **Teste de Ganância:** Usuário tenta sacar mais do que tem $\rightarrow$ Bloqueado por `INSUFFICIENT FUNDS`.
* **Teste de Trapaça:** Usuário tenta sacar número negativo para tentar "somar" dinheiro $\rightarrow$ Bloqueado por `Amount can't be negative`.
* **Fluxo Normal:** Usuário saca um valor honesto $\rightarrow$ O dinheiro sai e o saldo diminui com precisão de centavos.
* **Experiência do Usuário (UX):** Ao sair, o programa não desliga do nada; ele agradece a preferência com uma mensagem carinhosa e educada!

---

## 💻 Código Completo e Final do Projeto (`Main.java`)

Este é o código completo definitivo de todo o **Capítulo 29 (Java Banking Program)**. Copie para o seu IntelliJ para ter o sistema bancário completo funcionando:

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

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa o leitor do teclado.
* **Console:** **Nada**.

---

### **Linhas 3 a 5:** `public class Main {` e `static Scanner scanner = new Scanner(System.in);`
* **O que faz:** Abre a classe principal e cria o leitor do teclado compartilhado por todos os métodos.
* **Console:** **Nada**.

---

### **Linhas 7 a 13:** `public static void main(String[] args) {`
* **O que faz:** Motor de arranque. Cria `balance = 0`, `isRunning = true` e `choice`.
* **Console:** **Nada**.

---

### **Linhas 15 a 24:** *(O Menu Visual de 4 Opções)*
* **O que faz:** Desenha a moldura de asteriscos e as opções na tela.
* **Console:** Imprime o menu completo a cada rodada.

---

### **Linhas 26 e 27:** `System.out.print(...)` e `choice = scanner.nextInt();`
* **O que faz:** Pede a opção e lê o número digitado pelo usuário.
* **Console:** Mostra a pergunta e a escolha digitada.

---

### **Linha 29:** `switch(choice){`
* **O que faz:** Direciona a execução para o método bancário correto:
  * `case 1 -> showBalance(balance);` $\rightarrow$ Mostra o saldo com `$%.2f`.
  * `case 2 -> balance += deposit();` $\rightarrow$ Adiciona dinheiro à conta.
  * `case 3 -> balance -= withdraw(balance);` $\rightarrow$ Retira dinheiro da conta com dupla segurança.
  * `case 4 -> isRunning = false;` $\rightarrow$ Desliga o loop para sair.
  * `default -> System.out.println("INVALID CHOICE");` $\rightarrow$ Trata erros de digitação.
* **Console:** Executa o que for pedido.

---

### **Linha 36:** `}` *(Fecha o while)*
* **O que faz:** Se `isRunning` virar `false` (usuário escolheu 4), o Java quebra o laço e salta para a Linha 38!
* **Console:** **Nada**.

---

### **Linha 38:** `System.out.println("***************************");`  *(⭐ NOVIDADE!)*
* **O que faz:** Imprime a moldura superior de 27 asteriscos da mensagem de despedida e pula de linha.
* **Console:** Imprime `***************************`.

---

### **Linha 39:** `System.out.println("Thank you! Have a nice day!");`  *(⭐ NOVIDADE!)*
* **O que faz:** Imprime a frase de agradecimento ao cliente: `"Obrigado! Tenha um bom dia!"` e pula de linha.
* **Console:** Imprime `Thank you! Have a nice day!`.

---

### **Linha 40:** `System.out.println("***************************");`  *(⭐ NOVIDADE!)*
* **O que faz:** Imprime a moldura inferior de 27 asteriscos, fechando o banner com elegância.
* **Console:** Imprime `***************************`.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o canal do teclado com segurança e libera a memória.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o main)*
* **O que faz:** Finaliza o programa com êxito.
* **Console:** Exibe `Process finished with exit code 0`.

---

### **Linhas 45 a 48:** *(Método showBalance)*
* Imprime o saldo formatado entre asteriscos.

---

### **Linhas 50 a 64:** *(Método deposit)*
* Lê a quantia, rejeita números negativos com `Amount can't be negative` e devolve a quantia para somar.

---

### **Linhas 66 a 84:** *(Método withdraw)*
* Lê a quantia de saque:
  * Se `amount > balance` $\rightarrow$ Imprime `INSUFFICIENT FUNDS` e devolve `0`.
  * Se `amount < 0` $\rightarrow$ Imprime `Amount can't be negative` e devolve `0`.
  * Se for válido $\rightarrow$ Devolve `amount` para ser subtraído do saldo!

---

### **Linha 85:** `}`
* **O que faz:** Fecha a chave da classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Apareceu no Console Durante os Testes:

### 1. Teste de Saque Excessivo (Imagem 1):
```text
Enter your choice (1-4): 3
Enter amount to be withdrawn: 1000000
INSUFFICIENT FUNDS
***************
BANKING PROGRAM
...
```

### 2. Teste de Saque Negativo (Imagem 2):
```text
Enter your choice (1-4): 3
Enter amount to be withdrawn: -420.69
Amount can't be negative
***************
BANKING PROGRAM
...
```

### 3. Saldo Restante e Escolha de Sair (Imagem 3):
```text
Enter your choice (1-4): 1
***************
$49.99
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): 4
```

### 4. A Despedida Final ao Digitar 4 (Imagem 4):
```text
***************************
Thank you! Have a nice day!
***************************

Process finished with exit code 0
```

---

## 🎓 Conclusão Triunfal do Capítulo 29!

Parabéns! Você acabou de acompanhar e dominar a criação de um **sistema de terminal bancário completo** em Java:
* ✅ Estrutura de repetição contínua (`while`).
* ✅ Menu com seleção limpa usando enhanced switch (`->`).
* ✅ Compartilhamento de recursos com escopo de classe (`static Scanner`).
* ✅ Isolamento de responsabilidades em métodos dedicados (`showBalance`, `deposit`, `withdraw`).
* ✅ Validações de segurança para proteger a integridade do dinheiro.
* ✅ Formatação de moeda profissional com `$%.2f\n`.
* ✅ Mensagem final educada e encerramento limpo com `scanner.close()`.

Pode mandar o próximo print quando for começar o próximo capítulo!
