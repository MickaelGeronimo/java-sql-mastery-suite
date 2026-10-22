# 🎮 Juntando Tudo: `if-else` + `Scanner` (Programa 100% Interativo!)

Agora o programa virou um **aplicativo de verdade**! 

Até a aula passada, a gente tinha que ficar mudando a idade direto no código (`int age = 50;`, `int age = 70;`) e dando "Play" toda hora. 

Agora, o Bro Code trouxe de volta o **`Scanner`** para que **qualquer pessoa digite a idade no teclado** e o programa responda na hora!

---

## 📋 1. Código Completo Interativo (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // if statement = performs a block of code if its condition is true

        Scanner scanner = new Scanner(System.in);

        int age;

        System.out.print("Enter your age: ");
        age = scanner.nextInt();

        if (age >= 65) {
            System.out.println("You are a senior!");
        }
        else if (age >= 18) {
            System.out.println("You are an adult!");
        }
        else if (age < 0) {
            System.out.println("You haven't been born yet!");
        }
        else if (age == 0) {
            System.out.println("You are a baby!");
        }
        else {
            System.out.println("You are a child!");
        }

        scanner.close();
    }
}
```

---

## 🔄 2. O Que Mudou de Novo? (As 4 Peças que ele adicionou)

Olhe as novidades que transformaram o código:

### 1️⃣ Linha 1: O Import do Scanner
```java
import java.util.Scanner;
```
Traz a ferramenta da biblioteca do Java para poder escutar o teclado.

---

### 2️⃣ Linha 8: Ligando o Scanner
```java
Scanner scanner = new Scanner(System.in);
```
Cria o leitor do teclado no programa.

---

### 3️⃣ Linhas 10 a 13: A Caixinha Vazia e a Pergunta
```java
int age;

System.out.print("Enter your age: ");
age = scanner.nextInt();
```
* **`int age;`**: Não tem mais `= 50;`! A caixinha começa **vazia**.
* **`System.out.print("Enter your age: ");`**: Pede para a pessoa: *"Digite sua idade:"*.
* **`age = scanner.nextInt();`**: O programa pausa e fica esperando você digitar um número e apertar **Enter**. O número digitado cai dentro da variável `age`!

---

### 4️⃣ Linha 28: Desligando o Scanner
```java
scanner.close();
```
Fecha o leitor de teclado ao final para economizar memória do computador.

---

## 🔍 3. Tabela Linha por Linha Completa

| Linha | Código | O que significa na prática? |
| :---: | :--- | :--- |
| **1** | `import java.util.Scanner;` | Importa a ferramenta de leitura do teclado. |
| **8** | `Scanner scanner = new Scanner(System.in);` | Liga o leitor de teclado. |
| **10** | `int age;` | Cria a variável `age` vazia, esperando a resposta do usuário. |
| **12** | `System.out.print("Enter your age: ");` | Pergunta a idade na tela (com o cursor piscando ao lado). |
| **13** | `age = scanner.nextInt();` | Lê o número digitado e guarda em `age`. |
| **15** | `if (age >= 65) { ... }` | Se digitou 65 ou mais $\rightarrow$ Idoso. |
| **18** | `else if (age >= 18) { ... }` | Se digitou entre 18 e 64 $\rightarrow$ Adulto. |
| **21** | `else if (age < 0) { ... }` | Se digitou número negativo $\rightarrow$ Não nasceu. |
| **24** | `else if (age == 0) { ... }` | Se digitou exatamente 0 $\rightarrow$ Bebê. |
| **27** | `else { ... }` | Se sobrou (entre 1 e 17) $\rightarrow$ Criança. |
| **31** | `scanner.close();` | Desliga o leitor de teclado. |

---

## 🎬 4. Como é a Experiência no Console Agora?

Você roda o programa **uma vez só**, e pode testar qualquer idade digitando na hora:

```
[ Teste 1 ]:
Enter your age: 72 ⏎
You are a senior!

[ Teste 2 ]:
Enter your age: 21 ⏎
You are an adult!

[ Teste 3 ]:
Enter your age: 0 ⏎
You are a baby!

[ Teste 4 ]:
Enter your age: -4 ⏎
You haven't been born yet!
```

---

## 💡 Por que esse momento é tão importante no curso?

Você acabou de unir os dois maiores pilares de qualquer sistema de computador:
1. **Entrada de Dados (Input):** O usuário fala com o programa (`Scanner`).
2. **Tomada de Decisão (Lógica):** O programa pensa e reage de acordo com o que recebeu (`if / else`).

É assim que funcionam caixas eletrônicos, formulários de cadastro, logins e jogos! 🚀
