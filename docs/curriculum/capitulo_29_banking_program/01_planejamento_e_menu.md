# 💰 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 29: Programa Bancário (`banking program`) — Parte 1: O Esqueleto do Projeto, Variáveis e o Menu Visual
**Timestamps do vídeo:** `4:31:15` até `~4:33:30 / 12:00:00`  
**Tema:** Início do grande projeto prático de consolidação: Criando um **Sistema Bancário em Java para Iniciantes** (`JAVA BANKING PROGRAM FOR BEGINNERS`). O autor ensina a técnica profissional de planejar com comentários, declara as 4 variáveis essenciais do sistema e constrói o menu interativo com moldura de asteriscos.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja exatamente como o professor **Bro Code** conduziu esses primeiros minutos do Capítulo 29:

1. **Imagem 1 (Minuto 4:31:15 — A Folha em Branco e o Novo Capítulo):**
   * A barra inferior do YouTube mostra o título do novo capítulo: `29.banking program >` na marca de `4:31:15`.
   * O editor IntelliJ IDEA está limpo após o término do capítulo anterior. Temos apenas a classe `Main` e o método `main`.
   * Na Linha 6, o autor escreve o título do projeto como comentário:  
     `// JAVA BANKING PROGRAM FOR BEGINNERS` (Programa Bancário em Java para Iniciantes).
   * O cursor pisca na Linha 8, pronto para começar o planejamento.

2. **Imagem 2 (Minuto 4:32:01 — O "Mapa da Mina" com Comentários):**
   * Antes de escrever uma única linha de código complexo, o autor digita vários comentários em inglês, dividindo o problema em pequenos pedaços:
     * Linha 8: `// DECLARE VARIABLES` (Declarar as variáveis necessárias)
     * Linha 10: `// DISPLAY MENU` (Exibir o menu de opções na tela)
     * Linha 12: `// GET AND PROCESS USERS CHOICE` (Pegar e processar a escolha do usuário)
     * Linha 14: `// showBalance()` (Futuro método para mostrar o saldo)
     * Linha 16: `// deposit` (Digitando o futuro método de depósito)
   * Essa técnica mostra ao aluno como pensar como um programador: planejar a rota antes de acelerar o carro!

3. **Imagem 3 (Minuto ~4:33:00 — Criando as Variáveis e Desenhando o Menu):**
   * O autor preenche a seção de variáveis com 4 elementos fundamentais:
     * Linha 9: `Scanner scanner = new Scanner(System.in);`
     * Linha 10: `double balance;`
     * Linha 11: `boolean isRunning = true;`
     * Linha 12: `int choice;`
   * Em seguida, abaixo da seção do menu, ele começa a usar `System.out.println` com linhas decorativas de asteriscos (`"***************"`) e as opções:
     * `1. Show Balance` (Ver saldo)
     * `2. Deposit` (Depositar)
     * `3. Withdraw` (Sacar)
   * Na Linha 20, o cursor está terminando de digitar: `System.out.println("4. Ex");` (prestes a completar `Exit`).

4. **Imagem 4 (Minuto ~4:33:30 — O Menu Completo e o Roteiro Finalizado):**
   * O autor fecha a opção 4: `System.out.println("4. Exit");` e fecha a moldura de asteriscos na Linha 21: `System.out.println("***************");`.
   * Abaixo do menu, o esqueleto de comentários foi enriquecido com a visão completa do que o programa terá:
     * `// GET AND PROCESS USERS CHOICE`
     * `// showBalance()`
     * `// deposit()`
     * `// withdraw()`
     * `// EXIT MESSAGE`
   * O programa ainda não foi executado (o botão Play verde ainda não foi clicado), pois ele está montando a estrutura visual e lógica primeiro.

---

## 🧠 A Filosofia do Autor: Por Que Começar com Comentários?

Muitos iniciantes travam quando precisam criar um programa maior porque tentam fazer tudo de uma vez. O professor Bro Code ensina aqui a técnica chamada **Desenvolvimento Guiado por Comentários** (*Comment-Driven Development*):

1. **Passo 1 (O Que Preciso?):** Escreva em português (ou inglês) com `//` cada etapa que o programa deve fazer.
2. **Passo 2 (Variáveis):** De quais caixas de memória eu preciso para guardar as informações do banco?
3. **Passo 3 (Interface Visual):** Mostre para o usuário as opções disponíveis (o menu).
4. **Passo 4 (A Lógica):** Pegue o que o usuário escolheu e execute a ação certa (ver saldo, depositar, sacar ou sair).
5. **Passo 5 (Organização em Métodos):** Crie funções separadas para cada operação bancária para não virar uma "bagunça".

---

## 🏦 As 4 Variáveis Essenciais do Sistema Bancário

| Variável | Tipo | Para que Serve na Vida Real? | Analogia Simples |
| :--- | :--- | :--- | :--- |
| `scanner` | `Scanner` | Lê o que o usuário digita no teclado do computador. | O **teclado numérico** do caixa eletrônico. |
| `balance` | `double` | Guarda o saldo atual da conta bancária. É `double` porque dinheiro tem centavos (ex: `150.75`). | O **cofre** onde fica guardado o seu dinheiro. |
| `isRunning` | `boolean` | Vale `true` (verdadeiro) ou `false` (falso). Controla se o caixa eletrônico continua ligado ou se desliga. | A **chave geral** do caixa eletrônico. Enquanto estiver `true`, a tela de opções continua aparecendo. |
| `choice` | `int` | Guarda o número digitado pelo cliente: `1`, `2`, `3` ou `4`. | O **botão** da opção que o cliente apertou na tela. |

---

## 💻 Código Completo e Idêntico da Tela (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA BANKING PROGRAM FOR BEGINNERS

        Scanner scanner = new Scanner(System.in);
        double balance;
        boolean isRunning = true;
        int choice;

        System.out.println("***************");
        System.out.println("BANKING PROGRAM");
        System.out.println("***************");
        System.out.println("1. Show Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("***************");

        // GET AND PROCESS USERS CHOICE

        // showBalance()

        // deposit()

        // withdraw()

        // EXIT MESSAGE
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `import java.util.Scanner;`
* **O que faz:** Avisa ao Java que vamos usar a ferramenta `Scanner` que vem na biblioteca oficial de utilitários (`java.util`). Sem essa linha, o Java não sabe ler o teclado.
* **O que sai no console:** **Nada**.

---

### **Linha 2:** *(Linha em branco)*
* **O que faz:** Separa os imports da classe para manter o código limpo e organizado.
* **O que sai no console:** **Nada**.

---

### **Linha 3:** `public class Main {`
* **O que faz:** Cria a classe pública principal chamada `Main`. Todo código Java precisa estar dentro de uma classe.
* **O que sai no console:** **Nada**.

---

### **Linha 4:** `public static void main(String[] args) {`
* **O que faz:** Declara o método principal `main`. É o motor de arranque: quando apertamos o botão verde de Play, o computador começa a ler o programa a partir daqui.
* **O que sai no console:** **Nada**.

---

### **Linha 5:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 6:** `// JAVA BANKING PROGRAM FOR BEGINNERS`
* **O que faz:** Um comentário de uma linha informando o título do projeto ("Programa Bancário em Java para Iniciantes"). O compilador do Java ignora completamente essa linha.
* **O que sai no console:** **Nada**.

---

### **Linha 7:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 8:** `Scanner scanner = new Scanner(System.in);`
* **O que faz:** 
  1. `Scanner scanner`: Cria uma variável de referência chamada `scanner`.
  2. `new Scanner(System.in)`: Constrói na memória um objeto leitor conectado à entrada padrão do sistema (`System.in`), que é o teclado do seu computador.
* **O que sai no console:** **Nada** (o leitor fica em segundo plano, pronto para quando for chamado).

---

### **Linha 9:** `double balance;`
* **O que faz:** Reserva um espaço na memória do computador para guardar um número decimal (com ponto flutuante) com o nome `balance` (saldo). Neste momento ela ainda não tem nenhum valor atribuído (mais tarde receberá o saldo inicial, por exemplo `0`).
* **O que sai no console:** **Nada**.

---

### **Linha 10:** `boolean isRunning = true;`
* **O que faz:** Cria uma variável lógica (`boolean`) chamada `isRunning` (está rodando?) e define o valor inicial como `true` (verdadeiro). Essa variável servirá como o "interruptor" do futuro loop do banco: enquanto ela for `true`, o banco continua aberto; quando o usuário escolher a opção 4 (Exit), ela virará `false` e o banco fechará.
* **O que sai no console:** **Nada**.

---

### **Linha 11:** `int choice;`
* **O que faz:** Reserva um espaço na memória para um número inteiro chamado `choice` (escolha). Aqui será guardado o número da opção digitada pelo usuário (`1`, `2`, `3` ou `4`).
* **O que sai no console:** **Nada**.

---

### **Linha 12:** *(Linha em branco)*
* **O que faz:** Separa a declaração de variáveis do início dos comandos de exibição do menu.
* **O que sai no console:** **Nada**.

---

### **Linha 13:** `System.out.println("***************");`
* **O que faz:** Imprime no console uma linha com 15 asteriscos e pula para a linha seguinte. Funciona como a borda superior decorativa do cabeçalho do banco.
* **O que sai no console:**
  ```text
  ***************
  ```

---

### **Linha 14:** `System.out.println("BANKING PROGRAM");`
* **O que faz:** Imprime o texto centralizado do título: `"BANKING PROGRAM"` (Programa Bancário) e pula a linha.
* **O que sai no console:**
  ```text
  BANKING PROGRAM
  ```

---

### **Linha 15:** `System.out.println("***************");`
* **O que faz:** Imprime outra linha divisória com 15 asteriscos para separar o título das opções do menu e pula a linha.
* **O que sai no console:**
  ```text
  ***************
  ```

---

### **Linha 16:** `System.out.println("1. Show Balance");`
* **O que faz:** Imprime na tela a opção número 1: `"1. Show Balance"` (Mostrar Saldo) e pula para a próxima linha.
* **O que sai no console:**
  ```text
  1. Show Balance
  ```

---

### **Linha 17:** `System.out.println("2. Deposit");`
* **O que faz:** Imprime na tela a opção número 2: `"2. Deposit"` (Depositar Dinheiro) e pula para a próxima linha.
* **O que sai no console:**
  ```text
  2. Deposit
  ```

---

### **Linha 18:** `System.out.println("3. Withdraw");`
* **O que faz:** Imprime na tela a opção número 3: `"3. Withdraw"` (Sacar Dinheiro) e pula para a próxima linha.
* **O que sai no console:**
  ```text
  3. Withdraw
  ```

---

### **Linha 19:** `System.out.println("4. Exit");`
* **O que faz:** Imprime na tela a opção número 4: `"4. Exit"` (Sair do Programa) e pula para a próxima linha.
* **O que sai no console:**
  ```text
  4. Exit
  ```

---

### **Linha 20:** `System.out.println("***************");`
* **O que faz:** Imprime a moldura inferior de 15 asteriscos, fechando a caixinha do menu visual com elegância e pula a linha.
* **O que sai no console:**
  ```text
  ***************
  ```

---

### **Linha 21:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 22:** `// GET AND PROCESS USERS CHOICE`
* **O que faz:** Comentário avisando onde virá a leitura do que o usuário digitar (`scanner.nextInt()`) e a verificação (provavelmente usando um `switch` ou `if-else`).
* **O que sai no console:** **Nada**.

---

### **Linha 23:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 24:** `// showBalance()`
* **O que faz:** Comentário reservando o lugar onde o método de consultar saldo será acionado.
* **O que sai no console:** **Nada**.

---

### **Linha 25:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 26:** `// deposit()`
* **O que faz:** Comentário reservando o lugar onde o método de depósito de valores será acionado.
* **O que sai no console:** **Nada**.

---

### **Linha 27:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 28:** `// withdraw()`
* **O que faz:** Comentário reservando o lugar onde o método de saque de valores será acionado.
* **O que sai no console:** **Nada**.

---

### **Linha 29:** *(Linha em branco)*
* **O que faz:** Espaçamento.
* **O que sai no console:** **Nada**.

---

### **Linha 30:** `// EXIT MESSAGE`
* **O que faz:** Comentário reservando o lugar para a mensagem de despedida quando o cliente sair do banco (ex: *"Obrigado por usar nossos serviços!"*).
* **O que sai no console:** **Nada**.

---

### **Linha 31:** `}`
* **O que faz:** Fecha o bloco do método `main`.
* **O que sai no console:** **Nada**.

---

### **Linha 32:** `}`
* **O que faz:** Fecha o bloco da classe `Main`.
* **O que sai no console:** **Nada**.

---

## 📺 O que Sai no Console se Executar Este Código Agora:

Se você der Play agora mesmo no IntelliJ, como os comentários não geram texto e ainda não temos o loop nem a leitura do teclado, o console apenas desenha o menu visual estático perfeitamente formatado:

```text
***************
BANKING PROGRAM
***************
1. Show Balance
2. Deposit
3. Withdraw
4. Exit
***************

Process finished with exit code 0
```

---

## 🔮 O que Vem Pela Frente no Capítulo 29?

Agora que o palco está montado:
1. **O Loop Principal:** Envolveremos o menu dentro de um `while(isRunning)` para que, depois de fazer um depósito ou saque, o menu reapareça e o programa não feche na cara do usuário.
2. **A Leitura da Escolha:** Usaremos `choice = scanner.nextInt();` para pegar a opção desejada.
3. **A Estrutura de Decisão:** Um `switch(choice)` para direcionar:
   * Caso `1` $\rightarrow$ chama `showBalance(balance)`
   * Caso `2` $\rightarrow$ chama `deposit()`
   * Caso `3` $\rightarrow$ chama `withdraw(balance)`
   * Caso `4` $\rightarrow$ faz `isRunning = false;` para sair
   * Caso padrão (`default`) $\rightarrow$ exibe `"Invalid choice!"` (Opção inválida).
4. **Os Métodos Customizados:** Criaremos os métodos com tipos de retorno adequados e parâmetros, aplicando tudo que aprendemos nos Capítulos 26, 27 e 28!
