# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 6: Executando `showBalance()`, a Etiqueta `1 usage`, o Saldo `$0.000000` e a Quebra de Linha `\n`
**Timestamps do vídeo:** `~4:40:00 / 12:00:00`  
**Tema:** O método `showBalance` entra em ação pela primeira vez na tela preta! O professor Bro Code executa a opção `1. Show Balance`, o saldo inicial de `$0.000000` é impresso com sucesso, e ele ensina por que o `\n` (quebra de linha) é fundamental no `printf` para não deixar o menu colado no valor do dinheiro.

---

## 🖼️ Contexto Visual do Print (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem a imagem ao lado, veja o cenário exato registrado na tela do professor:

1. **Na Parte Superior (O Editor de Código):**
   * Vemos o método `showBalance` entre as Linhas 45 e 47:
     ```java
     static void showBalance(double balance){ 1 usage
         System.out.printf("$%f\n", balance);
     }
     ```
   * **A Etiqueta Cinza `1 usage`:** Ao lado da chave de abertura na Linha 45, o IntelliJ exibe em cinza claro `1 usage` (1 uso), comprovando que o método agora está oficialmente conectado ao `case 1` do `main`.
   * **A Adição do `\n`:** Na Linha 46, repare que o professor adicionou `\n` logo após o `%f` dentro das aspas: `"$%f\n"`.

2. **Na Parte Inferior (O Console com o Teste Real):**
   * O console mostra a sequência da operação bancária em verde brilhante:
     ```text
     Enter your choice (1-4): 1
     $0.000000
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
   * O professor digitou `1` e deu `Enter`.
   * O programa chamou o método e imprimiu `$0.000000`.
   * Graças ao `\n`, o cursor pulou para a linha seguinte antes de redesenhar os asteriscos do menu!
   * O loop `while` recomeçou e deixou o cursor piscando ao lado de `Enter your choice (1-4): `, pronto para a próxima transação!

---

## 🧠 Entendendo as Novidades Como um Iniciante

### 1. O Que Significa a Etiqueta Cinza `1 usage`?
* Nos prints anteriores, o método tinha a etiqueta `no usages` (nenhum uso), porque ele existia mas ninguém o chamava.
* Assim que colocamos `case 1 -> showBalance(balance);` no `main`, o IntelliJ detectou a conexão e atualizou automaticamente para `1 usage`!
* Isso é uma confirmação visual de que o método não é "código morto".

### 2. Por Que o `\n` é Obrigatório no `printf`?
Diferença crucial entre `println` e `printf`:
* `System.out.println()`: Imprime o texto e **pula a linha automaticamente** no final.
* `System.out.printf()`: Imprime o texto formatado, mas **NÃO pula a linha** sozinho!

> ⚠️ **O desastre se não colocar `\n`:**  
> Se o professor tivesse deixado apenas `System.out.printf("$%f", balance);`, o menu grudaria no dinheiro:  
> `$0.000000***************`  
> Com o `\n` (`escape sequence` de quebra de linha), o Java sabe que precisa pular para a linha de baixo antes de continuar!

### 3. Por Que Saiu `$0.000000` com 6 Zeros?
* O especificador de formato `%f` por padrão sempre imprime **6 casas decimais** após o ponto.
* Como `balance = 0;`, ele preencheu com seis zeros: `0.000000`.
* Na vida real, dinheiro só tem **2 casas de centavos** (ex: `$0.00`). No próximo passo, o professor ensinará a colocar um ponto e o número 2 (`%.2f`) para limitar as casas decimais!

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA BANKING PROGRAM FOR BEGINNERS

        Scanner scanner = new Scanner(System.in);
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
        System.out.printf("$%f\n", balance);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa o leitor do teclado.
* **Console:** **Nada**.

---

### **Linhas 3 e 4:** `public class Main {` e `public static void main(String[] args) {`
* **O que faz:** Estrutura principal da classe e motor de partida do programa.
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **Console:** **Nada**.

---

### **Linhas 8 a 12:** *(As 4 Variáveis)*
* **Linha 8:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Constrói o leitor do teclado.
* **Linha 10:** `double balance = 0;` $\rightarrow$ Saldo inicial da conta guardando `0.0`.
* **Linha 11:** `boolean isRunning = true;` $\rightarrow$ Chave geral ligada.
* **Linha 12:** `int choice;` $\rightarrow$ Guarda a opção do cliente.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Laço de repetição. Enquanto `isRunning` for `true`, o menu reaparece.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(O Menu de Opções)*
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
* **O que faz:** Imprime toda a moldura de asteriscos e as opções na tela preta.
* **Console:** Desenha o menu completo.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Imprime o pedido da opção mantendo o cursor na mesma linha.
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Lê o que você digita e dá Enter. No teste do print, o autor digitou `1`.
* **Console:** Aparece o número `1` ao lado dos dois pontos.

---

### **Linha 27:** `switch(choice){`
* **O que faz:** Como `choice` vale `1`, direciona o programa para o `case 1`.
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`
* **O que faz:** Chama o método `showBalance` entregando o valor de `balance` (`0.0`). O Java salta para a Linha 45.
* **Console:** *(A impressão ocorre dentro do método na Linha 46)*.

---

### **Linhas 29 a 32:** *(Demais cases)*
* Executam `"DEPOSIT"`, `"WITHDRAW"`, desligam com `isRunning = false` ou avisam `"INVALID CHOICE"`.
* **Console:** Não são executados nesta rodada porque a escolha foi `1`.

---

### **Linha 33:** `}` *(Fecha o switch)*
* **Console:** **Nada**.

---

### **Linha 34:** `}` *(Fecha o while)*
* **O que faz:** Volta para a Linha 14 porque `isRunning` continua sendo `true`! O menu inteiro é desenhado novamente.
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o canal do teclado quando o loop for encerrado.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o main)*
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){`
* **O que faz:** Ponto de entrada do método `showBalance`. Recebe o saldo no parâmetro `balance`. A etiqueta `1 usage` indica que o `case 1` o utilizou.
* **Console:** **Nada**.

---

### **Linha 46:** `    System.out.printf("$%f\n", balance);`  *(⭐ O DESTAQUE DO PRINT!)*
* **O que faz:** 
  1. Imprime o caractere literal `$`.
  2. Substitui o `%f` pelo valor decimal `0.000000`.
  3. O `\n` faz o cursor descer para a próxima linha!
* **Console:** Imprime e pula a linha:
  ```text
  $0.000000
  ```

---

### **Linhas 47 e 48:** `}` e `}`
* **O que faz:** Fecha o método `showBalance` (retornando ao `switch`) e fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como na Imagem:

```text
Enter your choice (1-4): 1
$0.000000
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): █  <-- O saldo foi exibido com sucesso e o menu voltou!
```

---

## 🔮 O Próximo Ajuste que o Autor Fará:

Ninguém gosta de ver uma conta de banco com 6 casas decimais como `$0.000000`.  
No próximo print, veremos o professor ajustar a máscara para **`$%.2f\n`**, ensinando como fazer o Java mostrar exatamente **duas casas de centavos** (`$0.00`)!
