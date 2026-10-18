# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 5: Inicializando o Saldo (`balance = 0`), o Erro de Argumento e a Chamada de `showBalance(balance)`
**Timestamps do vídeo:** `4:39:35` até `4:39:50 / 12:00:00`  
**Tema:** A conexão real entre a escolha do usuário e o método bancário! O professor Bro Code inicializa o saldo da conta com zero (`double balance = 0;`), demonstra intencionalmente o erro clássico de tentar chamar um método sem passar o argumento obrigatório (`showBalance()`), corrige passando a variável `showBalance(balance)`, e coloca o programa para rodar no console.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:39:35 — `balance = 0` e o Sublinhado Vermelho de Erro):**
   * Na Linha 10, o autor adiciona `= 0;` à variável de saldo:  
     `double balance = 0;`  
     *(Antes estava apenas `double balance;` sem valor)*.
   * Na Linha 28, dentro do `case 1`, ele apaga o texto provisório `"SHOW BALANCE"` e tenta chamar o método:  
     `case 1 -> showBalance();`
   * **O Alerta Vermelho:** Imediatamente a palavra `showBalance()` fica com um **sublinhado ondulado vermelho** de erro, e no canto superior direito do editor surge um círculo vermelho com o número `1`, indicando 1 erro de compilação!

2. **Imagem 2 (Minuto 4:39:41 — A Explicação do Autor com Seleção Azul):**
   * O professor seleciona a palavra `balance` na Linha 10 (ficando com uma tarja azul clara) e aponta o cursor para o `showBalance();` com erro na Linha 28.
   * Ele explica didaticamente: *"Vejam só: nós criamos lá embaixo o método exigindo um número decimal `showBalance(double balance)`. Quando tentamos chamar `showBalance()` de mãos vazias, o Java reclama porque o método está esperando receber o saldo!"*.

3. **Imagem 3 (Minuto 4:39:43 — A Correção Elegante):**
   * O autor digita a palavra `balance` dentro dos parênteses na Linha 28:  
     `case 1 -> showBalance(balance);`
   * O sublinhado vermelho **desaparece na hora**! O código volta a ficar 100% limpo e sem nenhum erro.

4. **Imagem 4 (Minuto 4:39:47 — O Programa Rodando no Console):**
   * O professor clica no botão verde de **Play** (`Run 'Main'`).
   * No canto superior direito, o ícone de Play vira o quadradinho vermelho de "Stop" (indicando que o programa está rodando ativamente).
   * O console preto abre na parte inferior e imprime o menu completo:
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
   * O cursor branco pisca aguardando o usuário digitar `1` para finalmente testar o método de saldo na prática!

---

## 🧠 Entendendo as Novidades Como um Iniciante

### 1. Por Que Precisamos de `double balance = 0;` na Linha 10?
* No Java, variáveis criadas dentro de métodos (variáveis locais) **não têm valor padrão automático**.
* Se você apenas escrever `double balance;` e tentar passar para outro método ou imprimir, o Java trava com o erro:  
  *`Variable 'balance' might not have been initialized`* (A variável 'balance' pode não ter sido inicializada).
* Adicionar `= 0;` garante que a conta bancária começa oficialmente com **R$ 0,00** (zero centavos).

### 2. Por Que `showBalance();` Deu Erro na Imagem 1?
* Lembre-se do contrato que fizemos na Linha 45:  
  `static void showBalance(double balance)`
* Esse contrato diz: *"Para usar o `showBalance`, você **é obrigado** a me entregar um número `double`!"*.
* Se você chamar `showBalance()` sem nada dentro dos parênteses, você está quebrando o contrato. O compilador Java não perdoa e coloca a linha vermelha!

### 3. A Entrega da Encomenda (`showBalance(balance)`):
* Quando escrevemos `showBalance(balance);` na Linha 28:
  1. O Java pega o valor que está guardado na variável local `balance` do `main` (que vale `0.0`).
  2. Faz uma cópia desse valor.
  3. Entrega nas mãos do parâmetro `double balance` lá do método `showBalance` (Linha 45).
  4. O método recebe o `0.0` e executa o `printf("$%f", balance);`!

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
        System.out.printf("$%f", balance);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa a classe do leitor de teclado.
* **Console:** **Nada**.

---

### **Linhas 3 e 4:** `public class Main {` e `public static void main(String[] args) {`
* **O que faz:** Abertura da classe principal e do método de partida do programa.
* **Console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **Console:** **Nada**.

---

### **Linha 8:** `Scanner scanner = new Scanner(System.in);`
* **O que faz:** Constrói o objeto de leitura do teclado.
* **Console:** **Nada**.

---

### **Linha 9:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 10:** `double balance = 0;`  *(⭐ MUDANÇA NESTE PASSO!)*
* **O que faz:** Cria a variável decimal `balance` e inicializa explicitamente seu valor com `0`. Agora a conta bancária tem um saldo inicial definido.
* **Console:** **Nada**.

---

### **Linha 11:** `boolean isRunning = true;`
* **O que faz:** Liga o interruptor do loop com `true`.
* **Console:** **Nada**.

---

### **Linha 12:** `int choice;`
* **O que faz:** Reserva a caixa para guardar a opção digitada pelo cliente.
* **Console:** **Nada**.

---

### **Linha 13:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Início do laço contínuo do caixa eletrônico.
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
* **O que faz:** Desenha a caixa de opções com a moldura de asteriscos.
* **Console:** Imprime o menu completo na tela preta.

---

### **Linha 23:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **O que faz:** Imprime a pergunta mantendo o cursor na mesma linha.
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Pausa a execução do programa e aguarda o usuário digitar um número e teclar `Enter`.
* **Console:** O número digitado aparece na frente dos dois pontos.

---

### **Linha 26:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 27:** `switch(choice){`
* **O que faz:** Abre o avaliador da opção digitada.
* **Console:** **Nada**.

---

### **Linha 28:** `    case 1 -> showBalance(balance);`  *(⭐ O GRANDE DESTAQUE!)*
* **O que faz:** Se o usuário digitou `1`:
  1. O Java interrompe temporariamente o fluxo do `switch`.
  2. Pega o valor atual de `balance` (`0`).
  3. Salta para a **Linha 45** (o método `showBalance`) passando esse `0`.
* **Console:** Imprimirá o saldo formatado assim que executado.

---

### **Linhas 29 a 32:** *(Os outros cases do switch)*
* **Linha 29:** `case 2 -> System.out.println("DEPOSIT");`
* **Linha 30:** `case 3 -> System.out.println("WITHDRAW");`
* **Linha 31:** `case 4 -> isRunning = false;` $\rightarrow$ Desliga o caixa eletrônico.
* **Linha 32:** `default -> System.out.println("INVALID CHOICE");`
* **Console:** Executam suas respectivas mensagens provisórias.

---

### **Linha 33:** `}` *(Fecha o switch)*
* **Console:** **Nada**.

---

### **Linha 34:** `}` *(Fecha o while)*
* **O que faz:** Se `isRunning` for `true`, reinicia o loop na Linha 14; se for `false`, pula para a Linha 42.
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o canal do teclado ao sair do loop.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o método main)*
* **Console:** `Process finished with exit code 0` (ao encerrar).

---

### **Linha 45:** `static void showBalance(double balance){`
* **O que faz:** Recebe o valor do saldo enviado pelo `main` e o guarda em seu parâmetro `balance`. Repare que agora a etiqueta `no usages` sumiu no IntelliJ porque a Linha 28 está usando ele!
* **Console:** **Nada**.

---

### **Linha 46:** `    System.out.printf("$%f", balance);`
* **O que faz:** Imprime o caractere `$` e substitui o `%f` pelo valor decimal guardado em `balance`.
* **Console:** Imprime `$0.000000`.

---

### **Linhas 47 e 48:** `}` e `}`
* **O que faz:** Fecha o método `showBalance` e fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console Exatamente Como na Imagem 4:

No momento do print, o programa está executando perfeitamente e aguardando a digitação do cliente:

```text
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************
Enter your choice (1-4): █  <-- Cursor piscando esperando você digitar 1!
```

---

## 🔮 O Que Vai Acontecer Assim que o Professor Apertar `1`?

No próximo passo, o professor vai digitar `1` e dar `Enter`.  
O Java vai chamar o `showBalance(balance)`, que imprimirá `$0.000000`.  
Mas repare num detalhe: como ele usou `printf("$%f", balance);` sem `\n` no final e sem definir `.2`, o saldo vai sair colado com o menu seguinte e com 6 zeros depois da vírgula! O autor vai mostrar isso para a turma e ensinar como formatar para `$0.00\n`!
