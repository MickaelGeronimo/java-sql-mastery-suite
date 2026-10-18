# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 9: O Toque de Design: Moldura de Asteriscos no `showBalance()` e o Resultado de `$10.99`
**Timestamps do vídeo:** `4:40:57` até `4:41:10 / 12:00:00`  
**Tema:** Design e elegância na tela preta do terminal! O professor Bro Code copia a linha de asteriscos do menu e a cola dentro do método `showBalance`. Ao rodar e digitar `1`, o saldo `$10.99` agora aparece emoldurado e perfeitamente separado da digitação do usuário, finalizando com chave de ouro a funcionalidade de consulta de saldo.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:40:57 — Copiando a Linha de Asteriscos):**
   * No editor de código, dentro do loop `while`, o professor clica e seleciona toda a **Linha 15**:  
     `System.out.println("***************");`
   * A linha fica inteira coberta por uma **faixa azul retangular de seleção**.
   * Ele copia essa linha (`Ctrl + C`) com o objetivo de levá-la para dentro do método `showBalance` lá embaixo.

2. **Imagem 2 (Minuto 4:41:10 — O Resultado Visual Perfeito no Console):**
   * O autor cola a linha de asteriscos dentro do `showBalance` (repare na barra de navegação no rodapé da janela: `MyFirstProject > src > Main > showBalance`).
   * Ele executa o programa e digita `1` no console.
   * **Veja a beleza da saída:**
     ```text
     Enter your choice (1-4): 1
     ***************
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
   * O professor usa o mouse para **selecionar o texto `$10.99`**, deixando-o destacado com uma **faixa azul retangular**.
   * Ele mostra aos alunos: agora o valor de `$10.99` não fica "jogado" nem colado na resposta do usuário; ele ganhou uma divisória de asteriscos em cima e outra embaixo, parecendo um extrato impresso de caixa eletrônico de verdade!

---

## 🧠 Por Que Esse Detalhe Visual Importa? (Experiência do Usuário em Terminal)

Muitos programadores iniciantes acham que programar é só fazer conta. Mas um bom desenvolvedor se preocupa com a **legibilidade** para o usuário que está usando o sistema:

### Antes (Sem a linha de asteriscos):
```text
Enter your choice (1-4): 1
$10.99
***************
BANKING PROGRAM
```
> *Ficava confuso: o `$10.99` parecia colado diretamente no número 1 que o cliente digitou.*

### Depois (Com o `println("***************")` dentro do `showBalance`):
```text
Enter your choice (1-4): 1
***************
$10.99
***************
BANKING PROGRAM
```
> *Fica cristalino: a linha de asteriscos separa claramente a ordem que o cliente deu da resposta financeira que o banco entregou!*

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA BANKING PROGRAM FOR BEGINNERS

        Scanner scanner = new Scanner(System.in);
        double balance = 10.99;
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
        System.out.println("***************"); // Linha divisória copiada da Linha 15!
        System.out.printf("$%.2f\n", balance);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linhas 1 a 4:** *(Estrutura Principal)*
* **Linha 1:** `import java.util.Scanner;` $\rightarrow$ Importa o leitor do teclado.
* **Linhas 3 e 4:** `public class Main {` e `public static void main(String[] args) {` $\rightarrow$ Declara a classe e o ponto de entrada `main`.
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **Console:** **Nada**.

---

### **Linhas 8 a 12:** *(As 4 Variáveis)*
* **Linha 8:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Leitor do teclado.
* **Linha 10:** `double balance = 10.99;` $\rightarrow$ Saldo de teste com centavos reais (`10.99`).
* **Linha 11:** `boolean isRunning = true;` $\rightarrow$ Chave geral ligada (`true`).
* **Linha 12:** `int choice;` $\rightarrow$ Opção escolhida.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Início da repetição. Enquanto `isRunning` for `true`, o banco atende o cliente.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(Menu de Opções)*
```java
System.out.println("***************"); // (Linha copiada na Imagem 1)
System.out.println("BANKING PROGRAM");
System.out.println("***************");
System.out.println("1. Show Balance");
System.out.println("2. Deposit");
System.out.println("3. Withdraw");
System.out.println("4. Exit");
System.out.println("***************");
```
* **O que faz:** Imprime a caixa com asteriscos e as 4 opções.
* **Console:** Desenha o menu completo na tela.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Pergunta a opção mantendo o cursor parado ao lado dos dois pontos.
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Lê a opção digitada pelo usuário. Na Imagem 2, o autor digitou `1`.
* **Console:** O número `1` aparece na tela.

---

### **Linha 27:** `switch(choice){`
* **O que faz:** Direciona o fluxo para o `case 1`.
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`
* **O que faz:** Envia o valor de `balance` (`10.99`) e salta para a Linha 45 (o método `showBalance`).
* **Console:** *(A impressão ocorre dentro do método)*.

---

### **Linhas 29 a 32:** *(Demais cases)*
* **Console:** Não rodam nesta rodada.

---

### **Linha 33:** `}` *(Fecha o switch)*
* **Console:** **Nada**.

---

### **Linha 34:** `}` *(Fecha o while)*
* **O que faz:** Volta para a Linha 14 e desenha o menu novamente porque `isRunning` continua valendo `true`.
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o canal do teclado ao sair do banco.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o main)*
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){`
* **O que faz:** Abre o método `showBalance` recebendo o saldo `10.99`.
* **Console:** **Nada**.

---

### **Linha 46:** `    System.out.println("***************");`  *(⭐ A NOVIDADE COLADA!)*
* **O que faz:** Imprime uma divisória com 15 asteriscos logo acima do valor do dinheiro e pula de linha.
* **Console:** Imprime:
  ```text
  ***************
  ```

---

### **Linha 47:** `    System.out.printf("$%.2f\n", balance);`
* **O que faz:** Imprime o caractere `$`, seguido do saldo formatado com duas casas decimais (`10.99`), e o `\n` desce o cursor para a próxima linha!
* **Console:** Imprime:
  ```text
  $10.99
  ```

---

### **Linhas 48 e 49:** `}` e `}`
* **O que faz:** Fecha o método `showBalance` (retornando ao loop) e fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como na Imagem 2:

```text
***************
Enter your choice (1-4): 1
***************
$10.99
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): █  <-- Cursor piscando pronto para a próxima operação!
```

---

## 🏆 Resumo Deste Marco: A Opção 1 Está 100% Concluída!

* O método `showBalance` agora está:
  1. Conectado ao `case 1`.
  2. Recebendo o saldo por parâmetro corretamente.
  3. Formatando a moeda com duas casas decimais (`$%.2f`).
  4. Pulando linha com `\n`.
  5. Emoldurado esteticamente com a linha de asteriscos `***************`.

Nos próximos passos do curso, o professor começará a construir a **Opção 2: `Deposit` (Depósito)**, onde o cliente poderá digitar um valor para somar ao saldo da conta!
