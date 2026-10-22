# 🏋️ Capítulo 15: Conversor de Peso (Weight Converter) — Parte 5: Finalização, Bloco `else` e `scanner.close()`

---

## 📌 1. Visão Geral: O Que o Autor Adicionou Aqui?

Neste final do Capítulo 15 (das marcas **2:34:33** até **2:35:08**), o autor concluiu o projeto do Conversor de Peso adicionando duas coisas fundamentais:

1. **A Rede de Proteção (`else`):**
   - E se o usuário for teimoso e digitar `3`, `99` ou `-10`?
   - O autor adicionou um bloco `else` no final:
     ```java
     else{
         System.out.println("That was not a valid choice");
     }
     ```
   - Se a pessoa não escolheu `1` e nem escolheu `2`, o programa não fica mudo nem quebra: ele avisa com educação que a opção não é válida!

2. **Fechamento do Scanner (`scanner.close()`):**
   - Na linha 37, ele colocou `scanner.close();`.
   - Isso é como "desligar o microfone" ou "fechar a tampa do teclado". Libera a memória do computador que estava ouvindo o teclado.

3. **Os Dois Testes Finais do Vídeo:**
   - **Teste 1 (Opção Inválida):** O autor digitou `3` no menu. O programa pulou o `if`, pulou o `else if` e caiu direto no `else`, imprimindo:
     `That was not a valid choice`
   - **Teste 2 (Conversão de 200 lbs):** Escolheu a opção `1` e digitou `200` libras. O programa calculou $200 \times 0.453592 = 90.7184$ e exibiu perfeitamente:
     `The new weight in kgs is: 90.72`

---

## 🛡️ 2. Como Funciona a Estrutura Completa do `if - else if - else`?

Pense nisso como um segurança de balada com uma prancheta de 3 regras:

```
[ Usuário digita um número na variável 'choice' ]
                   │
                   ▼
       É igual a 1? (choice == 1)
          ├── SIM ──► Entra no Bloco 1 (Libras para Quilos) e ENCERRA.
          │
          └── NÃO
               │
               ▼
       É igual a 2? (choice == 2)
          ├── SIM ──► Entra no Bloco 2 (Quilos para Libras) e ENCERRA.
          │
          └── NÃO
               │
               ▼
       Não é nem 1 nem 2? (else)
          └─────────► Cai no 'else': "That was not a valid choice".
```

### Por que o `else` não tem parênteses `()` com teste?
- O `if` precisa de teste: `if (choice == 1)` -> *"É 1?"*
- O `else if` precisa de teste: `else if (choice == 2)` -> *"É 2?"*
- O `else` **NÃO precisa de teste** porque ele é o resto, a sobra! É o mesmo que dizer: *"Se não foi nada do que eu perguntei antes, faz isso aqui obrigatoriamente!"*.

---

## 🧹 3. Por Que Usar `scanner.close()`?

Quando criamos `Scanner scanner = new Scanner(System.in);`, o Java abre um canal com o sistema operacional para monitorar as teclas digitadas.

Colocar `scanner.close();` no fim do programa:
- Fecha esse canal de comunicação.
- Evita o chamado **Memory Leak** (vazamento de recursos da máquina).
- Deixa o código profissional e limpo (tira aquele sublinhado amarelo de aviso que o IntelliJ às vezes coloca).

---

## 🔍 4. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 39)

Abaixo está o código completo do projeto finalizado, exatamente como mostrado nas telas do Bro Code:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa a biblioteca do Scanner para permitir a leitura de dados do teclado.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 3:** `public class Main {`
- **O que faz:** Declaração da classe principal onde o programa vive.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** O método `main`, ponto onde a execução do Java realmente começa.

### **Linha 5:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 6:** `        // WEIGHT CONVERSION PROGRAM`
- **O que faz:** Comentário de identificação do projeto.

### **Linha 7:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 8:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Constrói o leitor de teclado com o nome `scanner`.

### **Linha 9:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 10:** `        double weight;`
- **O que faz:** Reserva uma variável de ponto flutuante chamada `weight` para guardar o peso informado pelo usuário.

### **Linha 11:** `        double newWeight;`
- **O que faz:** Reserva a variável `newWeight` para guardar o resultado do cálculo de conversão.

### **Linha 12:** `        int choice;`
- **O que faz:** Reserva a variável de número inteiro `choice` para guardar a opção escolhida no menu (`1` ou `2`).

### **Linha 13:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 14:** `        System.out.println("Weight Conversion Program");`
- **O que faz:** Imprime o título do programa no console e salta para a linha de baixo.

### **Linha 15:** `        System.out.println("1: Convert lbs to kgs");`
- **O que faz:** Imprime a opção 1 do menu e salta de linha.

### **Linha 16:** `        System.out.println("2: Convert kgs to lbs");`
- **O que faz:** Imprime a opção 2 do menu e salta de linha.

### **Linha 17:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 18:** `        System.out.print("Choose an option: ");`
- **O que faz:** Imprime o prompt `"Choose an option: "` na mesma linha (sem pular linha), para o usuário responder ao lado.

### **Linha 19:** `        choice = scanner.nextInt();`
- **O que faz:** Lê o número inteiro digitado pelo usuário e armazena na variável `choice`.

### **Linha 20:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 21:** `        if(choice == 1){`
- **O que faz:** Início da primeira verificação: *"O valor de choice é igual a 1?"*. Se for, entra nas linhas 22 a 25.

### **Linha 22:** `            System.out.print("Enter the weight in lbs: ");`
- **O que faz:** Pede que o usuário digite o peso em libras.

### **Linha 23:** `            weight = scanner.nextDouble();`
- **O que faz:** Lê o número decimal digitado e guarda em `weight`.

### **Linha 24:** `            newWeight = weight * 0.453592;`
- **O que faz:** Multiplica as libras pelo fator `0.453592` para achar os quilos e salva em `newWeight`.

### **Linha 25:** `            System.out.printf("The new weight in kgs is: %.2f", newWeight);`
- **O que faz:** Exibe a mensagem com o peso em quilos formatado com duas casas decimais (`%.2f`).

### **Linha 26:** `        }`
- **O que faz:** Fecha o bloco da Opção 1.

### **Linha 27:** `        else if(choice == 2){`
- **O que faz:** Se não foi 1, testa: *"O valor de choice é igual a 2?"*. Se for, entra nas linhas 28 a 31.

### **Linha 28:** `            System.out.print("Enter the weight in kgs: ");`
- **O que faz:** Pede que o usuário digite o peso em quilos.

### **Linha 29:** `            weight = scanner.nextDouble();`
- **O que faz:** Lê o valor decimal digitado em quilos e guarda em `weight`.

### **Linha 30:** `            newWeight = weight * 2.20462;`
- **O que faz:** Multiplica os quilos pelo fator `2.20462` para achar as libras e salva em `newWeight`.

### **Linha 31:** `            System.out.printf("The new weight in lbs is: %.2f", newWeight);`
- **O que faz:** Exibe o novo peso em libras formatado com duas casas decimais (`%.2f`).

### **Linha 32:** `        }`
- **O que faz:** Fecha o bloco da Opção 2.

---

### ⭐ As Novidades Adicionadas Agora (Linhas 33 a 37):

### **Linha 33:** `        else{`
- **O que faz:** A cláusula de escape/exceção. Se o usuário digitou qualquer número diferente de `1` e `2` (ex: `3`, `0`, `45`), o Java cai direto aqui dentro.

### **Linha 34:** `            System.out.println("That was not a valid choice");`
- **O que faz:** Exibe a mensagem de aviso no console: *"Essa não foi uma escolha válida"*.

### **Linha 35:** `        }`
- **O que faz:** Fecha a chave do bloco `else`.

### **Linha 36:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 37:** `        scanner.close();`
- **O que faz:** Encerra e fecha o objeto `scanner`. Libera os recursos de entrada do sistema operacional e evita desperdício de memória.

### **Linha 38:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 39:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 5. Código Final Completo do Capítulo 15 (Pronto para Copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // WEIGHT CONVERSION PROGRAM

        Scanner scanner = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");

        System.out.print("Choose an option: ");
        choice = scanner.nextInt();

        if(choice == 1){
            System.out.print("Enter the weight in lbs: ");
            weight = scanner.nextDouble();
            newWeight = weight * 0.453592;
            System.out.printf("The new weight in kgs is: %.2f\n", newWeight);
        }
        else if(choice == 2){
            System.out.print("Enter the weight in kgs: ");
            weight = scanner.nextDouble();
            newWeight = weight * 2.20462;
            System.out.printf("The new weight in lbs is: %.2f\n", newWeight);
        }
        else{
            System.out.println("That was not a valid choice");
        }

        scanner.close();
    }
}
```

> **Dica de ouro:** Adicionamos `\n` dentro dos `printf` (`%.2f\n`) para garantir que o resultado final quebre linha e não fique colado nas saídas do terminal!

---

## 📊 6. Tabela Comparativa de Todos os Caminhos Possíveis do Programa

| O que você digita em `choice` | Onde o Java entra? | O que acontece em seguida? |
| :---: | :---: | :--- |
| **`1`** | Bloco `if(choice == 1)` | Pede peso em libras, multiplica por `0.453592`, mostra em quilos. |
| **`2`** | Bloco `else if(choice == 2)` | Pede peso em quilos, multiplica por `2.20462`, mostra em libras. |
| **Qualquer outro (`3`, `99`, etc)** | Bloco `else` | Não pede peso nenhum. Avisa: *"That was not a valid choice"*. |
