# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 11: Criando o Método `deposit()`, o Dilema do Scanner e a Aplicação Real do Escopo de Classe (`static Scanner`)
**Timestamps do vídeo:** `4:42:00` até `~4:44:00 / 12:00:00`  
**Tema:** Uma aula magistral de arquitetura de software conectando diretamente com o Capítulo 28! O professor Bro Code cria o método `deposit()` com retorno `double`. Ao tentar ler o valor a ser depositado, surge o dilema: o `Scanner` estava preso como variável local dentro do `main`! Para resolver de forma elegante e profissional, o autor promove o `Scanner` para **Variável de Classe** (`static Scanner`), permitindo que todos os métodos do banco compartilhem o mesmo leitor de teclado.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja o passo a passo exato do que aconteceu na tela do professor:

1. **Imagem 1 (Minuto 4:42:xx — Criando o Esqueleto do `deposit`):**
   * Logo abaixo do `showBalance` (que está recolhido em `{...}`), na Linha 47, o professor declara o novo método:
     ```java
     static double deposit(){ no usages
         double amount;

         return 0;
     }
     ```
   * Repare que o tipo de retorno é **`double`** (e não `void`).
   * Ele cria a variável `double amount;` (quantia) e coloca `return 0;` provisório para o compilador não reclamar de falta de retorno.

2. **Imagem 2 (Minuto 4:43:12 — O Problema do Scanner Local):**
   * Dentro do `deposit()`, o autor tenta pedir o valor para o cliente:
     * Linha 51: `System.out.print("Enter an amount to be deposited: ");`
     * Linha 52: `Scanner scanner = new Scanner();` *(com sublinhado vermelho ondulado de erro sob os parênteses)*.
     * Linha 53: `amount = scanner.nextDouble();`
   * O professor para e explica: *"Criar um novo Scanner aqui dentro é péssimo! Por que ter dois Scanners abrindo o teclado ao mesmo tempo se podemos usar o mesmo?"*.

3. **Imagem 3 (Minuto 4:43:24 — Voltando ao Topo do `main`):**
   * O professor rola o código para cima até o início do método `main`.
   * Ele aponta para a Linha 9: `Scanner scanner = new Scanner(System.in);`.
   * Ele relembra a aula do Capítulo 28: *"Lembram do Escopo de Variáveis? O `scanner` foi declarado aqui dentro do `main`, então ele é uma **variável local**. O método `deposit()` lá embaixo não consegue enxergá-lo!"*.

4. **Imagem 4 (Minuto ~4:43:50 — A Grande Solução: Escopo de Classe!):**
   * O autor recorta o `scanner` de dentro do `main` e o move para fora, logo abaixo de `public class Main {`, na **Linha 5**:
     ```java
     static Scanner scanner = new Scanner(System.in); 3 usages
     ```
   * Repare na etiqueta cinza **`3 usages`** (3 usos)!
   * Dentro do `main`, a Linha 10 agora começa diretamente com `double balance = 0;`.
   * Agora o `scanner` pertence à **classe inteira**! Tanto o `main` quanto o `deposit` (e futuramente o `withdraw`) podem ler o teclado usando o mesmo objeto, sem nenhum erro!

---

## 🧠 Entendendo a Arquitetura Como um Iniciante

### 1. Por Que o Método `deposit()` é `double` e Não `void`?
* O método `showBalance` era `void` porque ele só "mostrava uma foto" do saldo e não devolvia nada.
* O método `deposit()` precisa **receber dinheiro** do cliente e **devolver esse valor** para o `main` para que o saldo aumente!
* Por isso ele tem o tipo de retorno `double`. No final, faremos no `main`:  
  `balance += deposit();` (saldo = saldo + quantia depositada).

### 2. Por Que Mover o `Scanner` Para a Classe (`static Scanner scanner`)?
Pense no `Scanner` como um **telefone compartilhado**:
* **Modo Amador (Local):** Colocar um telefone trancado no quarto do `main`. Se o quarto do `deposit` quiser ligar para o teclado, ele tem que comprar outro telefone (`new Scanner`). Ter dois telefones na mesma linha dá conflito e desperdiça memória.
* **Modo Profissional (Escopo de Classe):** Colocar o telefone na **sala de estar** (`class Main`). Agora, qualquer quarto (`main`, `deposit`, `withdraw`) pode usar o mesmo telefone na hora que precisar!
* Por ser um método `static`, a variável de classe também **precisa ser `static`**!

### 3. Por Que o IntelliJ Mostra `3 usages` na Linha 5?
O IntelliJ rastreia em tempo real onde o `scanner` está sendo usado no projeto:
1. **Uso 1:** `choice = scanner.nextInt();` (no `main`, para ler o menu).
2. **Uso 2:** `scanner.close();` (no `main`, para fechar o teclado ao sair).
3. **Uso 3:** `amount = scanner.nextDouble();` (no `deposit`, para ler a quantia do depósito).

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in); // VARIÁVEL DE CLASSE (compartilhada!)

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
                case 2 -> System.out.println("DEPOSIT");
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

        return 0; // Provisório enquanto constrói o método
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Importa a classe do leitor do teclado.
* **Console:** **Nada**.

---

### **Linha 3:** `public class Main {`
* **O que faz:** Abre a classe principal.
* **Console:** **Nada**.

---

### **Linha 5:** `static Scanner scanner = new Scanner(System.in);`  *(⭐ A GRANDE EVOLUÇÃO!)*
* **O que faz:** 
  1. Cria o leitor do teclado no **Escopo de Classe**.
  2. A palavra `static` permite que ele seja acessado por todos os métodos estáticos da classe (`main`, `deposit`, `withdraw`).
  3. A etiqueta cinza `3 usages` confirma que ele está alimentando três partes do programa ao mesmo tempo.
* **Console:** **Nada**.

---

### **Linha 7:** `public static void main(String[] args) {`
* **O que faz:** Ponto de partida da execução.
* **Console:** **Nada**.

---

### **Linhas 10 a 12:** *(As Variáveis Locais do main)*
* **Linha 10:** `double balance = 0;` $\rightarrow$ Saldo zerado inicial.
* **Linha 11:** `boolean isRunning = true;` $\rightarrow$ Chave do loop ligada.
* **Linha 12:** `int choice;` $\rightarrow$ Guarda a opção do menu.
* *(Repare que o `Scanner` não está mais aqui dentro!)*
* **Console:** **Nada**.

---

### **Linha 14:** `while(isRunning){`
* **O que faz:** Início do laço do caixa eletrônico.
* **Console:** **Nada**.

---

### **Linhas 15 a 22:** *(Exibição do Menu Visual)*
* **Console:** Imprime o menu completo com asteriscos.

---

### **Linha 24:** `System.out.print("Enter your choice (1-4): ");`
* **Console:** `Enter your choice (1-4): `

---

### **Linha 25:** `choice = scanner.nextInt();`
* **O que faz:** Usa o `scanner` compartilhado da classe para ler o número digitado.
* **Console:** O número digitado aparece na tela.

---

### **Linha 27:** `switch(choice){`
* **O que faz:** Avalia a opção.
* **Console:** **Nada**.

---

### **Linhas 28 a 32:** *(Os cases do switch)*
* **Console:** Executam conforme a opção digitada.

---

### **Linhas 33 e 34:** `}` e `}` *(Fecha switch e fecha while)*
* **Console:** **Nada**.

---

### **Linha 42:** `scanner.close();`
* **O que faz:** Fecha o `scanner` da classe ao encerrar o banco.
* **Console:** **Nada**.

---

### **Linha 43:** `}` *(Fecha o main)*
* **Console:** **Nada**.

---

### **Linha 45:** `static void showBalance(double balance){...}`
* **O que faz:** Método de exibir saldo (recolhido com Code Folding).
* **Console:** **Nada**.

---

### **Linha 47:** `static double deposit(){`  *(⭐ NOVO MÉTODO!)*
* **O que faz:**
  * `static`: Pode ser chamado diretamente sem criar objeto.
  * `double`: Promete devolver um número decimal (a quantia que o cliente depositou).
  * `deposit()`: Nome do método ("depositar").
  * Etiqueta cinza `no usages`: Ainda não foi chamado no `case 2` do `main`.
* **Console:** **Nada**.

---

### **Linha 49:** `    double amount;`
* **O que faz:** Cria a variável local `amount` (quantia) exclusiva do método `deposit` para armazenar temporariamente o valor que o usuário quer depositar.
* **Console:** **Nada**.

---

### **Linha 51:** `    System.out.print("Enter an amount to be deposited: ");`
* **O que faz:** Imprime a pergunta `"Digite a quantia a ser depositada: "` mantendo o cursor na mesma linha.
* **Console:** Quando for chamado, exibirá essa frase.

---

### **Linha 52:** *(No código corrigido)* `amount = scanner.nextDouble();`
* **O que faz:** Usa o `scanner` compartilhado da classe para ler o número decimal com centavos (ex: `50.25`) digitado pelo usuário e guarda em `amount`.
* **Console:** O valor digitado aparece na frente da pergunta.

---

### **Linha 55:** `    return 0;`
* **O que faz:** Devolve provisoriamente o valor `0` para satisfazer a promessa de retorno do tipo `double`.
* **Console:** **Nada**.

---

### **Linha 56:** `}` *(Fecha o método deposit)*
* **Console:** **Nada**.

---

### **Linha 57:** `}` *(Fecha a classe Main)*
* **Console:** **Nada**.

---

## 📺 O que Aparece no Console:

**Nada!**  
O console continua fechado em todos os 4 prints. O professor está totalmente concentrado na arquitetura do código no editor, refatorando o escopo do Scanner e construindo o método `deposit`.

---

## 🔮 O Que Falta no Método `deposit()`?

1. **Validação de Segurança:** E se o cliente digitar um valor negativo como `-50`? Não existe depositar dívida! O professor adicionará um `if(amount < 0)` para proteger o banco contra trapaças!
2. **Retornar o Valor Real:** Trocar `return 0;` por `return amount;`.
3. **Conectar ao `case 2`:** No `main`, fazer `balance += deposit();` para o saldo crescer de verdade!
