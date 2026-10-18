# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 10: Finalizando `showBalance()`, o Truque de *Code Folding* (Dobrar Código) e a Preparação para o `deposit()`
**Timestamps do vídeo:** `4:41:18` até `4:41:22 / 12:00:00`  
**Tema:** Organização e produtividade no IntelliJ IDEA! Com o método `showBalance` 100% concluído e aprovado nos testes, o professor Bro Code ensina o recurso profissional de **Code Folding** (recolher/dobrar código). Ele fecha o bloco do método em uma única linha compacta (`{...}`), limpando o editor visualmente para focar na próxima missão: construir o método `deposit()`.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja exatamente a transição que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:41:18 — O Método `showBalance` Aberto e Completo):**
   * O professor foca na parte inferior do editor de código, mostrando o método `showBalance` em sua forma final entre as Linhas 45 e 48:
     ```java
     static void showBalance(double balance){ 1 usage
         System.out.println("***************");
         System.out.printf("$%.2f\n", balance);
     }
     ```
   * Todas as 4 linhas estão visíveis. O método está testado, validado e com a etiqueta `1 usage`.
   * Logo acima, vemos o fim do `main` com `scanner.close();` e os comentários de planejamento `// withdraw()` e `// EXIT MESSAGE`.

2. **Imagem 2 (Minuto 4:41:22 — O Clique Mágico do *Code Folding*):**
   * O professor leva o mouse até a margem esquerda (calha de números) da Linha 45 e clica no pequeno ícone de seta/menos (`-`).
   * **O Código é Dobrado:** Imediatamente, as Linhas 46, 47 e 48 se "recolhem" para dentro da Linha 45!
   * A linha agora é exibida de forma ultracompacta:
     ```java
     45 >   static void showBalance(double balance){...}
     49     }
     ```
   * O símbolo `{...}` indica que o código continua ali dentro, apenas escondido da vista.
   * O cursor do mouse sobe para perto dos comentários `// deposit()` e `// withdraw()`, sinalizando que a mesa de trabalho está limpa para começar a próxima função do banco!

---

## 🧠 O Que É *Code Folding* e Por Que Ele É Tão Útil?

Imagine a sua mesa de trabalho física:
* Quando você termina de preencher um formulário importante e confere que está tudo certo, você não deixa ele aberto ocupando metade da mesa. Você dobra o formulário e guarda na pasta!
* No desenvolvimento de software, **Code Folding** (dobramento de código) é exatamente isso:
  1. **Economiza Espaço de Tela:** Conforme o projeto cresce e ganha dezenas de métodos, rolar a página para cima e para baixo fica cansativo.
  2. **Foco Mental:** O método `showBalance` já funciona perfeitamente; não precisamos ficar olhando para ele o tempo todo. Escondê-lo permite focar 100% no próximo problema.
  3. **Segurança:** Evita que você esbarre no teclado e digite caracteres acidentais dentro de um método que já estava pronto e testado!

> 💡 **Como Fazer no seu IntelliJ IDEA:**  
> * Para **dobrar (recolher)**: Clique na setinha `v` na margem esquerda da Linha 45 (ou use o atalho `Ctrl + .` / `Ctrl + -`).  
> * Para **desdobrar (abrir)**: Clique no `>` ou dê dois cliques sobre o `{...}`.

---

## 💻 Código Completo da Tela com a Visão Aberta (`Main.java`)

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

    // [MÉTODO RECOLHIDO COM CODE FOLDING NA IMAGEM 2: static void showBalance(double balance){...}]
    static void showBalance(double balance){
        System.out.println("***************");
        System.out.printf("$%.2f\n", balance);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linhas 35 a 41:** *(A Reta Final do Método main)*
* **Linha 36:** `// deposit()` $\rightarrow$ Comentário marcando o local da futura lógica de depósito.
* **Linha 38:** `// withdraw()` $\rightarrow$ Comentário para o saque.
* **Linha 40:** `// EXIT MESSAGE` $\rightarrow$ Comentário para a mensagem de despedida ao sair.
* **Linha 42:** `scanner.close();` $\rightarrow$ Fecha o leitor do teclado para não gastar memória.
* **Linha 43:** `}` $\rightarrow$ Fecha a chave do método `main`.
* **Linha 44:** *(Linha em branco para separação visual)*.
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){` *(ou `{...}` quando recolhido)*
* **O que faz:**
  * Na **Imagem 1**: Mostra a assinatura do método aberto, com a etiqueta cinza `1 usage` indicando que o `case 1` o utiliza.
  * Na **Imagem 2**: Mostra o método dobrado como `static void showBalance(double balance){...}` com a seta `>` na margem esquerda. O código interno continua existindo na memória do compilador, mas fica oculto no editor para economizar espaço de tela.
* **Console:** **Nada**.

---

### **Linhas 46 e 47:** *(O Miolo Oculto no Fold)*
* **Linha 46:** `System.out.println("***************");` $\rightarrow$ Imprime a divisória de asteriscos.
* **Linha 47:** `System.out.printf("$%.2f\n", balance);` $\rightarrow$ Imprime o saldo formatado com duas casas decimais e pula de linha.
* **Console:** **Nada** (essas linhas só executam quando o programa estiver rodando e o usuário apertar `1`).

---

### **Linha 48:** `}` *(Fecha o método showBalance)*
* **O que faz:** Fecha o bloco do método. Na Imagem 2, fica oculta dentro do `{...}`.
* **Console:** **Nada**.

---

### **Linha 49:** `}` *(Fecha a classe Main)*
* **O que faz:** Fecha a chave principal de todo o arquivo `Main.java`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console:

**Nada!**  
O console inferior está fechado/recolhido em ambos os prints. O professor está totalmente concentrado no editor de texto, organizando o código antes de começar o desenvolvimento da próxima funcionalidade.

---

## 🔮 O Que Vem no Próximo Passo: O Método `deposit()`!

Agora que a Opção 1 (`Show Balance`) está arquivada e dobrada:
1. O professor vai criar o método `static double deposit()`.
2. Repare numa diferença arquitetural fascinante que veremos a seguir:
   * O `showBalance` era do tipo `void` (não devolvia nada, só imprimia).
   * O `deposit` precisará ser do tipo `double` ou receber/alterar o saldo, porque quando o cliente deposita dinheiro, **o saldo da conta aumenta**!
   * Veremos como o Java lida com o retorno de valores e a atualização do saldo bancário!
