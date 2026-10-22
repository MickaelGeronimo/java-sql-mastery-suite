# 🕵️‍♂️ Resolvendo o Bug do Bebê Estudante: Como Bloquear Idades Impossíveis!

Você teve um **pensamento de programador de verdade**! 👏 

No código anterior, se um usuário dissesse que tem **0 anos** e digitasse **`true`** para estudante, o programa aceitava e dizia:
> *"Você é um bebê! 👶"*
> *"Você é um estudante! 🎒"* ❌ (Um bebê na faculdade?! Não faz sentido!).

Vamos aprender **duas formas elegantes** de resolver isso no Java!

---

## 💡 Opção 1: O "IF dentro de IF" (Mais Didático e Fácil de Entender)

Podemos colocar um `if` dentro do outro. Pense assim:
1. Primeiro testamos: **A pessoa disse que é estudante?**
2. Se SIM, fazemos um segundo teste: **Ela tem idade para estudar (mais de 3 anos)?**

### Veja o código do GROUP 3 atualizado:

```java
//GROUP 3
if (isStudent) {
    if (age <= 3) {
        System.out.println("You are too young to be a student! 👶🚫");
    }
    else {
        System.out.println("You are a student! 🎒");
    }
}
else {
    System.out.println("You are NOT a student! 💼");
}
```

### 🧠 Como o Java pensa aqui:
* Se `isStudent` for `true`:
  * Tem 0, 1, 2 ou 3 anos? $\rightarrow$ Mostra: *"Você é jovem demais para ser estudante! 👶🚫"*.
  * Tem mais de 3 anos? $\rightarrow$ Mostra: *"Você é um estudante! 🎒"*.
* Se `isStudent` for `false` $\rightarrow$ Cai direto no `else` lá de fora: *"Você não é estudante! 💼"*.

---

## ⚡ Opção 2: Usando o Operador Lógico `&&` (O Símbolo "E")

Na programação, o símbolo **`&&`** significa **"E"** (as duas coisas precisam ser verdadeiras ao mesmo tempo!).

```java
// Só é estudante de verdade se marcou 'true' E tiver mais de 3 anos!
if (isStudent && age > 3) {
    System.out.println("You are a student! 🎒");
}
else if (isStudent && age <= 3) {
    System.out.println("Babies cannot be students! 👶🚫");
}
else {
    System.out.println("You are NOT a student! 💼");
}
```

### Como ler `if (isStudent && age > 3)`:
> *"SE a pessoa é estudante **E TAMBÉM** tem mais de 3 anos de idade..."*

---

## 📋 Código Completo Pronto para Testar (Opção 1)

Copie e cole este código na sua IDE para ver o "bloqueio de bebê" funcionando na prática:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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

        //GROUP 1: NOME
        if (name.isEmpty()) {
            System.out.println("You didn't enter your name! 😡");
        }
        else {
            System.out.println("Hello " + name + "! 😊");
        }

        //GROUP 2: IDADE
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

        //GROUP 3: ESTUDANTE COM TRAVA DE SEGURANÇA
        if (isStudent) {
            if (age <= 3) {
                System.out.println("You cannot be a student, you're just a baby! 👶🚫");
            }
            else {
                System.out.println("You are a student! 🎒");
            }
        }
        else {
            System.out.println("You are NOT a student! 💼");
        }

        scanner.close();
    }
}
```

---

## 🎬 Testando o "Bebê Mentirosinho" no Console:

```
Enter your name: Pedrinho ⏎
Enter your age: 1 ⏎
Are you a student (true/false): true ⏎

Hello Pedrinho! 😊
You are a child! 🧒
You cannot be a student, you're just a baby! 👶🚫  👈 O PROGRAMA PEGOU A MENTIRA!
```

---

## 🏆 Resumo
Você acabou de aprender o conceito de **Validação de Regra de Negócio**: o programa não deve aceitar cegamente qualquer coisa que o usuário digita, ele precisa conferir se os dados fazem sentido na vida real! 🚀
