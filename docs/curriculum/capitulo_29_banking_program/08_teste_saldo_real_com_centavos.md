# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 8: Teste com Saldo Real (`balance = 10.99`) e Validação dos Centavos
**Timestamps do vídeo:** `4:40:44 / 12:00:00`  
**Tema:** O teste com dinheiro de verdade! O professor Bro Code altera o saldo inicial para `double balance = 10.99;` para comprovar na prática que a formatação `$%.2f\n` funciona não apenas com zeros, mas com centavos reais, exibindo `$10.99` com precisão cirúrgica.

---

## 🖼️ Contexto Visual do Print (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem a imagem ao lado, veja o cenário exato registrado na tela do professor:

1. **Na Parte Superior (O Editor de Código):**
   * Vemos as Linhas 9 a 11 do método `main`:
     ```java
     Scanner scanner = new Scanner(System.in);
     double balance = 10.99;
     boolean isRunning = true;
     ```
   * **A Mudança na Linha 10:** O professor alterou o valor inicial de `0` para `10.99`. A conta agora começa com 10 dólares e 99 centavos.
   * O cursor do mouse está na Linha 9, no canto direito.

2. **Na Parte Inferior (O Console Pronto para o Teste):**
   * O professor reiniciou a aplicação (botão Play verde).
   * O console preto desenha a moldura verde de asteriscos e as opções do banco:
     ```text
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
   * O cursor branco pisca ao lado de `(1-4): `, pronto para o professor digitar `1` e verificar se a tela imprimirá com sucesso o valor formatado de `$10.99`.

---

## 🧠 Por Que Mudar Para `10.99`? (A Mente de um Programador)

Testar um programa apenas com o número `0` é uma armadilha comum de iniciantes:
* Com `0`, se o programa der um bug e imprimir qualquer coisa vazia com zeros, você pode achar que está certo.
* Ao colocar um número quebrado real como `10.99`:
  1. Você testa se o tipo `double` armazena números com ponto flutuante corretamente.
  2. Você testa se a passagem de parâmetro `showBalance(balance)` entrega o valor exato `10.99` sem arredondar para `10` ou `11`.
  3. Você testa se o formatador `$%.2f` exibe os dois dígitos dos centavos (`.99`) perfeitamente ao lado do cifrão!

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA BANKING PROGRAM FOR BEGINNERS

        Scanner scanner = new Scanner(System.in);
        double balance = 10.99; // Alterado para 10.99 para teste com centavos reais!
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
        System.out.printf("$%.2f\n", balance);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa o leitor de teclado.
* **Console:** **Nada**.

---

### **Linhas 3 e 4:** `public class Main {` e `public static void main(String[] args) {`
* **O que faz:** Abertura da classe principal e ponto de entrada da execução.
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **Console:** **Nada**.

---

### **Linhas 8 a 12:** *(As 4 Variáveis)*
* **Linha 8:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Leitor do teclado.
* **Linha 10:** `double balance = 10.99;`  *(⭐ A ALTERAÇÃO DESTE PASSO!)*  
  * Armazena o valor decimal `10.99` na memória do computador. Agora a conta tem dez dólares e noventa e nove centavos.
* **Linha 11:** `boolean isRunning = true;` $\rightarrow$ Chave do loop ligada.
* **Linha 12:** `int choice;` $\rightarrow$ Guarda a opção do cliente.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Início da repetição. Como `isRunning` é `true`, o Java entra no loop.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(Exibição do Menu Visual)*
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
* **O que faz:** Desenha a moldura de asteriscos e as opções do banco.
* **Console:** Imprime o menu completo na tela preta.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Imprime a pergunta mantendo o cursor na mesma linha.
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Pausa a execução aguardando o usuário digitar um número e apertar `Enter`.
* **Console:** O número digitado aparece na mesma linha.

---

### **Linha 27:** `switch(choice){`
* **O que faz:** Avalia a opção escolhida.
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`
* **O que faz:** Quando o cliente digitar `1`, o Java pega o valor atual de `balance` (`10.99`) e salta para a Linha 45.
* **Console:** A impressão ocorrerá dentro do método.

---

### **Linhas 29 a 32:** *(Demais cases)*
* **Linha 29:** `case 2 -> System.out.println("DEPOSIT");`
* **Linha 30:** `case 3 -> System.out.println("WITHDRAW");`
* **Linha 31:** `case 4 -> isRunning = false;`
* **Linha 32:** `default -> System.out.println("INVALID CHOICE");`
* **Console:** Só rodam se forem escolhidas as respectivas opções.

---

### **Linha 33:** `}` *(Fecha o switch)*
* **Console:** **Nada**.

---

### **Linha 34:** `}` *(Fecha o while)*
* **O que faz:** Se `isRunning` for `true`, reinicia o ciclo na Linha 14; se for `false`, pula para a Linha 42.
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o canal do teclado ao sair do banco.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o método main)*
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){`
* **O que faz:** Recebe o valor `10.99` no seu parâmetro `balance`.
* **Console:** **Nada**.

---

### **Linha 46:** `    System.out.printf("$%.2f\n", balance);`
* **O que faz:**
  1. Imprime `$`.
  2. Formata o número `10.99` com 2 casas decimais.
  3. O `\n` pula a linha.
* **Console:** Imprime:
  ```text
  $10.99
  ```

---

### **Linhas 47 e 48:** `}` e `}`
* **O que faz:** Fecha o método `showBalance` e a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como no Print:

O programa está rodando e pronto para receber o número `1`:

```text
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): █  <-- Ao digitar 1 e dar Enter, o saldo impresso será $10.99!
```

E quando o usuário apertar `1` e teclar `Enter`:
```text
Enter your choice (1-4): 1
$10.99
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

O método `showBalance` está 100% testado, aprovado e validado com dinheiro de verdade!
