# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 7: Formatando Moeda Profissional com `$%.2f\n` (Duas Casas de Centavos)
**Timestamps do vídeo:** `4:40:25` até `4:40:34 / 12:00:00`  
**Tema:** A perfeição visual da moeda bancária! O professor Bro Code ajusta o especificador de formato no método `showBalance`, trocando o padrão de 6 casas decimais por `%.2f`. Ele roda o programa, digita `1`, e o console exibe o saldo impecável de `$0.00`.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:40:25 — O Ajuste no Editor):**
   * No método `showBalance`, na Linha 46, o professor altera o texto dentro das aspas do `printf`:
     ```java
     System.out.printf("$%.2f\n", balance);
     ```
   * Repare que entre o `%` e o `f`, ele inseriu `.2`. O trecho `%.2f` fica com destaque de seleção no editor.
   * Abaixo, o console já foi reiniciado e mostra o menu do banco aguardando o teste em `Enter your choice (1-4): `.

2. **Imagem 2 (Minuto 4:40:34 — O Teste do Saldo Formatado e o Destaque em Azul):**
   * O professor digita `1` e pressiona `Enter`.
   * O console imprime na linha de baixo:
     ```text
     $0.00
     ```
   * Com o mouse, o professor **seleciona os dois zeros dos centavos (`00`)**, deixando-os com uma **faixa azul retangular de seleção**.
   * Ele enfatiza para os alunos: *"Olhem só a diferença! Agora não temos mais aquele monte de zeros feios (`$0.000000`). Limitamos a exibição a exatamente 2 casas decimais, que é o padrão universal para centavos de dinheiro!"*.
   * O menu é redesenhado logo abaixo e o sistema continua pronto para a próxima ação.

---

## 🧠 Desvendando o Segredo do `$%.2f\n` Peça por Peça

Para quem está começando em Java, uma linha como `"$%.2f\n"` parece um código alienígena. Mas cada pedacinho tem uma função cirúrgica:

| Símbolo | O que Significa? | Para que Serve? |
| :---: | :--- | :--- |
| **`$`** | Caractere literal de dólar | Aparece na tela exatamente como um `$`. Se fosse no Brasil, você poderia trocar por `R$ `. |
| **`%`** | Início da máscara de formato | Avisa ao Java: *"Atenção, o que vem a seguir é uma regra especial para formatar uma variável!"*. |
| **`.`** | Ponto de precisão | Avisa que queremos controlar o que vem **depois da vírgula/ponto**. |
| **`2`** | Quantidade de casas decimais | Trava a exibição em **exatamente 2 números decimais** (nem mais, nem menos). Se o número tiver mais casas, ele arredonda; se tiver menos, ele completa com zero! |
| **`f`** | *Floating-point* (ponto flutuante) | Diz ao Java que o valor a ser formatado é um número decimal (`double` ou `float`), que no nosso caso é a variável `balance`. |
| **`\n`** | *Newline* (nova linha) | O equivalente a apertar a tecla `Enter`. Faz o cursor descer para a próxima linha antes de desenhar o próximo menu. |

---

## 🧪 Comparação Prática: O Efeito da Precisão no Java

Imagine que a sua conta bancária tenha `150.5` dólares. Veja como cada formato exibiria esse saldo na tela:

* `printf("$%f\n", balance);` $\rightarrow$ `$150.500000` *(6 casas, feio e amador)*
* `printf("$%.1f\n", balance);` $\rightarrow$ `$150.5` *(1 casa)*
* `printf("$%.2f\n", balance);` $\rightarrow$ **`$150.50`** *(Perfeito para bancos e caixas eletrônicos!)*
* `printf("$%.0f\n", balance);` $\rightarrow$ `$151` *(Arredonda e esconde os centavos)*

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
        System.out.printf("$%.2f\n", balance); // Formatado com 2 casas de centavos!
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa o leitor de teclado da biblioteca utilitária do Java.
* **Console:** **Nada**.

---

### **Linhas 3 e 4:** `public class Main {` e `public static void main(String[] args) {`
* **O que faz:** Declaração da classe e do ponto de entrada do programa.
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **Console:** **Nada**.

---

### **Linhas 8 a 12:** *(As 4 Variáveis)*
* **Linha 8:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Leitor do teclado.
* **Linha 10:** `double balance = 0;` $\rightarrow$ Saldo inicial com valor `0.0`.
* **Linha 11:** `boolean isRunning = true;` $\rightarrow$ Chave geral do loop ligada.
* **Linha 12:** `int choice;` $\rightarrow$ Guarda o número da opção.
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Laço de repetição contínuo.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(Menu Visual)*
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
* **O que faz:** Desenha a caixa decorativa com asteriscos e as 4 opções.
* **Console:** Exibe o menu completo formatado.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Imprime o pedido da opção mantendo o cursor na mesma linha.
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Lê a opção digitada pelo usuário. Na Imagem 2, o autor digitou `1`.
* **Console:** O número `1` aparece na tela ao lado de `(1-4): `.

---

### **Linha 27:** `switch(choice){`
* **O que faz:** Encaminha para o `case 1`.
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`
* **O que faz:** Aciona o método `showBalance` entregando o saldo atual (`0.0`). O fluxo pula para a Linha 45.
* **Console:** *(A impressão ocorre na Linha 46)*.

---

### **Linhas 29 a 32:** *(Demais cases)*
* **Console:** Ignorados nesta rodada porque a opção foi `1`.

---

### **Linha 33:** `}` *(Fecha o switch)*
* **Console:** **Nada**.

---

### **Linha 34:** `}` *(Fecha o while)*
* **O que faz:** Como `isRunning` continua valendo `true`, o Java volta para a Linha 14 e reapresenta o menu.
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o canal do teclado.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o main)*
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){`
* **O que faz:** Recebe o saldo `0.0` no parâmetro `balance`.
* **Console:** **Nada**.

---

### **Linha 46:** `    System.out.printf("$%.2f\n", balance);`  *(⭐ A LINHA DA PERFEIÇÃO!)*
* **O que faz:**
  1. Imprime `$`.
  2. Substitui `%.2f` pelo valor de `balance` formatado com exatamente 2 casas decimais (`0.00`).
  3. O `\n` desce o cursor para a próxima linha!
* **Console:** Imprime:
  ```text
  $0.00
  ```

---

### **Linhas 47 e 48:** `}` e `}`
* **O que faz:** Fecha o método `showBalance` e fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como na Imagem 2:

```text
Enter your choice (1-4): 1
$0.00
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

* O saldo agora sai elegante e enxuto: **`$0.00`**.
* O primeiro método do sistema bancário (`showBalance`) está oficialmente concluído com nota 10!
* O próximo passo do curso será construir o método de depósito (`deposit`), para que a conta possa finalmente receber dinheiro de verdade!
