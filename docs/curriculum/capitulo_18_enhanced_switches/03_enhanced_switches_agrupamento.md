# 💡 Capítulo 18: Enhanced Switches (Switches Modernos) — Parte 3: O Poder do Agrupamento com Vírgulas (Código Perfeito e Compacto)

---

## 📌 1. Visão Geral: O Que o Autor Fez Aqui?

Nestas 4 imagens (das marcas **2:56:00** até **2:57:30**), o autor mostrou o **golpe de mestre do Java 14**:
1. **O Teste da Opção Inválida com Scanner (Imagem 1):**  
   Testou o programa anterior digitando `"Tacoday"`. O Java caiu no `default` e avisou: `"Tacoday is not a day"`.
2. **O Agrupamento Mágico com Vírgulas (Imagem 2):**  
   Em vez de escrever uma linha para segunda, outra para terça, outra para quarta...  
   Ele **juntou todos os dias úteis em um único `case` separado por vírgulas**:
   ```java
   case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" ->
           System.out.println("It is a weekday 😩");
   ```
   E fez a mesma coisa para o fim de semana:
   ```java
   case "Saturday", "Sunday" ->
           System.out.println("It is the weekend 😀");
   ```
3. **Os Testes Definitivos no Console:**
   - **Imagem 3:** Digitou `"Wednesday"` $\rightarrow$ Saiu no console: **`It is a weekday 😩`**.
   - **Imagem 4:** Digitou `"Hamburgerday"` $\rightarrow$ Saiu no console: **`Hamburgerday is not a day`**.

---

## 🚀 2. A Evolução do Código: Da Tartaruga ao Foguete

Olhe como a sua jornada nesse capítulo foi incrível:

| Versão | Como era feito? | Tamanho do código | Avaliação |
| :--- | :--- | :--- | :--- |
| **Versão 1 (Aula 1)** | Monte de `if`, `else if`, `else if`... | Quase 35 linhas | ❌ Péssimo, cansativo e repetitivo |
| **Versão 2 (Aula 2)** | `switch` com 7 `case` individuais com `->` | Cerca de 25 linhas | 🟡 Bom, mas ainda repetia a frase |
| **Versão 3 (Aula de Agora)** | **`switch` agrupado com vírgulas `,`** | **Apenas 8 linhas de lógica!** | 🏆 O estado da arte do Java moderno! |

---

## 🔬 3. Como Funciona a Vírgula no `case`?

No Java antigo, você não podia colocar vírgulas no switch. Se quisesse agrupar dias, era uma bagunça cheia de `case` empilhados.

No **Enhanced Switch (Java 14+)**, a vírgula funciona exatamente como a palavra **"OU"**:

```java
case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" ->
```
O Java lê assim:
> *"Se for Monday **OU** Tuesday **OU** Wednesday **OU** Thursday **OU** Friday, execute o comando da setinha!"*

Isso significa que você só precisa escrever a frase `System.out.println("It is a weekday 😩");` **UMA ÚNICA VEZ**!

---

## 🖥️ 4. O Que Sai no Console em Cada Teste? (Conforme Você Pediu)

### 🌮 Teste 1 (Imagem 1): Digitou `Tacoday`
1. O usuário digita: `Tacoday` (um dia que não existe).
2. O `switch` procura se `Tacoday` está na lista.
3. Não encontra em nenhum dos dias úteis e nem no fim de semana.
4. Cai direto no `default ->`.
5. **Saída no console:**
```text
Enter the day of the week: Tacoday
Tacoday is not a day
```

---

### 📅 Teste 2 (Imagem 3): Digitou `Wednesday`
1. O usuário digita: `Wednesday` (Quarta-feira).
2. O `switch` olha o primeiro grupo: `"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"`.
3. Ele encontra `Wednesday` ali no meio!
4. Dispara a setinha `->` daquele grupo.
5. **Saída no console:**
```text
Enter the day of the week: Wednesday
It is a weekday 😩
```

---

### 🍔 Teste 3 (Imagem 4): Digitou `Hamburgerday`
1. O usuário digita: `Hamburgerday` (outro dia inventado).
2. O `switch` não encontra esse dia em nenhum dos dois grupos.
3. Cai no `default ->`.
4. **Saída no console:**
```text
Enter the day of the week: Hamburgerday
Hamburgerday is not a day
```

---

## 🔍 5. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 24)

Abaixo está o código completo exatamente como montado pelo Bro Code:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa o leitor de teclado.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 3:** `public class Main {`
- **O que faz:** A classe principal do programa.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** Método `main`, ponto de ignição do programa.

### **Linha 5:** ` ` *(em branco)*

### **Linha 6:** `        // Enhanced switch = A replacement to many else if statements`
- **O que faz:** Comentário: *"Switch moderno = Um substituto para muitos comandos else if"*.

### **Linha 7:** `        //                   (Java14 feature)`
- **O que faz:** Comentário lembrando que é do Java 14.

### **Linha 8:** ` ` *(em branco)*

### **Linha 9:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Cria e ativa o leitor de teclado chamado `scanner`.

### **Linha 10:** ` ` *(em branco)*

### **Linha 11:** `        System.out.print("Enter the day of the week: ");`
- **O que faz:** Mostra a mensagem pedindo para digitar o dia da semana (sem pular linha).

### **Linha 12:** `        String day = scanner.nextLine();`
- **O que faz:** Pausa o programa, lê o que você digitou e guarda na variável `day`.

### **Linha 13:** ` ` *(em branco)*

### **Linha 14:** `        switch(day){`
- **O que faz:** Abre o switch para avaliar o valor guardado na variável `day`.

### **Linha 15:** `            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" ->`
- **O que faz:** **O grande truque!** Agrupa os 5 dias úteis com vírgulas. Se for qualquer um desses 5 dias, aponta a setinha para a linha 16.

### **Linha 16:** `                    System.out.println("It is a weekday 😩");`
- **O que faz:** Executa a impressão de dia útil com carinha de cansaço.

### **Linha 17:** `            case "Saturday", "Sunday" ->`
- **O que faz:** Agrupa os 2 dias de fim de semana (Sábado e Domingo).

### **Linha 18:** `                    System.out.println("It is the weekend 😀");`
- **O que faz:** Executa a impressão de fim de semana com carinha feliz.

### **Linha 19:** `            default -> System.out.println(day + " is not a day");`
- **O que faz:** Se o que a pessoa digitou não for nenhum dos 7 dias, avisa que não é um dia válido.

### **Linha 20:** `        }`
- **O que faz:** Fecha o bloco do `switch`.

### **Linha 21:** ` ` *(em branco)*

### **Linha 22:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado para liberar memória.

### **Linha 23:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 24:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 6. Código Completo Final Pronto para Copiar e Rodar

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Enhanced switch = A replacement to many else if statements
        //                   (Java14 feature)

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the day of the week: ");
        String day = scanner.nextLine();

        switch(day){
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" ->
                    System.out.println("It is a weekday 😩");
            case "Saturday", "Sunday" ->
                    System.out.println("It is the weekend 😀");
            default -> System.out.println(day + " is not a day");
        }

        scanner.close();
    }
}
```
