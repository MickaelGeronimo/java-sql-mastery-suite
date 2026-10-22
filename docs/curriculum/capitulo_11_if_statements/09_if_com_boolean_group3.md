# 🎓 A Chegada do `boolean` e do GROUP 3: Você é Estudante?

Nessas imagens, o Bro Code adicionou **três coisas muito legais**:
1. Uma pergunta do tipo **Verdadeiro ou Falso** usando `boolean` e `scanner.nextBoolean()`.
2. O **`//GROUP 3`** com um teste de `if` super elegante: `if (isStudent)`.
3. **Emojis divertidos** nas mensagens para o programa ficar com uma cara moderna! 😎

---

## 📋 1. Código Completo com os 3 Grupos e Emojis (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // if statement = performs a block of code if its condition is true

        Scanner scanner = new Scanner(System.in);

        String name;
        int age;
        boolean isStudent;

        System.out.print("Enter your name: ");
        name = scanner.nextLine();

        System.out.print("Enter your age: ");
        age = scanner.nextInt();

        System.out.print("Are you a student (true/false): ");
        isStudent = scanner.nextBoolean();

        //GROUP 1
        if (name.isEmpty()) {
            System.out.println("You didn't enter your name! 😡");
        }
        else {
            System.out.println("Hello " + name + "! 😊");
        }

        //GROUP 2
        if (age >= 65) {
            System.out.println("You are a senior! 🧓");
        }
        else if (age >= 18) {
            System.out.println("You are an adult! 👨");
        }
        else if (age < 0) {
            System.out.println("You haven't been born yet! 👽");
        }
        else if (age == 0) {
            System.out.println("You are a baby! 👶");
        }
        else {
            System.out.println("You are a child! 🧒");
        }

        //GROUP 3
        if (isStudent) {
            System.out.println("You are a student! 🎒");
        }
        else {
            System.out.println("You are NOT a student! 💼");
        }

        scanner.close();
    }
}
```

---

## 🌟 2. As 3 Grandes Novidades Desta Aula:

---

### 1️⃣ O Tipo `boolean` e o `nextBoolean()`
Nas linhas 12 e 21:
```java
boolean isStudent;
isStudent = scanner.nextBoolean();
```
* **`boolean`**: É uma caixinha que só aceita dois valores no mundo inteiro: **`true` (verdadeiro)** ou **`false` (falso)**.
* **`scanner.nextBoolean()`**: O Java fica esperando você digitar exatamente a palavra `true` ou `false` no teclado!

---

### 2️⃣ O Truque Elegante: `if (isStudent)`
Olhe a linha 50 no **GROUP 3**:
```java
if (isStudent) {
    System.out.println("You are a student!");
}
```

#### 🤔 "Ué, cadê o `== true`?"
Muitos iniciantes acham que precisam escrever:
```java
if (isStudent == true) // Funciona, mas é redundante!
```

Mas pense com a gente:
* O `if` só quer saber se o que está dentro do parênteses é `true` ou `false`.
* A variável `isStudent` **já é** um booleano (`true` ou `false`)!
* Então escrever `if (isStudent)` já é o suficiente:
  * Se `isStudent` for **`true`** $\rightarrow$ Entra no `if`!
  * Se `isStudent` for **`false`** $\rightarrow$ Cai direto no `else`!

> 💡 **Analogia do Interruptor de Luz 💡:**
> O interruptor só pode estar **ligado (`true`)** ou **desligado (`false`)**. Não precisa perguntar: *"O interruptor ligado é igual a ligado?"*. Basta olhar: *"Está ligado?"*.

---

### 3️⃣ Emojis no Console! (Imagem 4)
O Java aceita emojis perfeitamente dentro dos textos `" "`!
* `😡` se esqueceu de digitar o nome.
* `😊` se deu tudo certo.
* `🧓` para o idoso, `👨` para o adulto, `👶` para o bebê.
* Deixa o programa muito mais vivo e interativo!

---

## 🏛️ 3. O Grande Painel dos 3 Grupos Independentes

Agora o programa faz um **cadastro completo** em 3 etapas independentes:

```
[ DADOS COLETADOS ]: Nome, Idade, É estudante (true/false)
                           │
             ┌─────────────┼─────────────┐
             ▼             ▼             ▼
        [ GROUP 1 ]   [ GROUP 2 ]   [ GROUP 3 ]
       Verifica Nome Verifica Idade  Verifica Aluno
       (isEmpty?)    (>= 65, etc)   (isStudent?)
```

Cada grupo faz a sua pergunta e mostra a sua mensagem, um após o outro, sem nenhum atrapalhar o outro!

---

## 🎬 4. Simulação de um Teste no Console:

```
Enter your name: Lucas ⏎
Enter your age: 20 ⏎
Are you a student (true/false): true ⏎

Hello Lucas! 😊              👈 (GROUP 1 respondeu)
You are an adult! 👨         👈 (GROUP 2 respondeu)
You are a student! 🎒        👈 (GROUP 3 respondeu)

Process finished with exit code 0 ✅
```
