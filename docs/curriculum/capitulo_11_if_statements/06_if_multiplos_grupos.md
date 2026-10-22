# 👥 Dois `if`s no Mesmo Programa: A Chegada do `name` e dos Grupos de Decisão!

Nessas 4 imagens, o Bro Code deu mais um passo incrível: agora o programa não pergunta só a idade, **ele também pergunta o seu nome**!

Além disso, ele organizou o código em **dois grupos independentes** (`//GROUP 1` e `//GROUP 2`). Vamos entender tudo nos mínimos detalhes!

---

## 📋 1. Código Atual da Imagem 4 (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // if statement = performs a block of code if its condition is true

        Scanner scanner = new Scanner(System.in);

        String name;
        int age;

        System.out.print("Enter your name: ");
        name = scanner.nextLine();

        System.out.print("Enter your age: ");
        age = scanner.nextInt();

        //GROUP 1


        //GROUP 2
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

## 🔍 2. As 3 Grandes Novidades Desta Etapa:

---

### 1️⃣ Criou a Variável de Nome (`String name;`)
Na linha 10, ele criou a caixinha de texto:
```java
String name; // Vai guardar o nome da pessoa (ex: "Carlos", "Bro")
```

---

### 2️⃣ Fez a Pergunta e Leu o Nome
Nas linhas 13 e 14:
```java
System.out.print("Enter your name: ");
name = scanner.nextLine();
```
* Mostra na tela: *"Digite seu nome:"*.
* **`scanner.nextLine()`**: Lê palavras e textos com espaços até você apertar Enter!

> 🧠 **Dica de Mestre:** Lembra daquele bug clássico do Scanner onde o `nextLine()` pulava sozinho?
> Como ele colocou o **`nextLine()` ANTES do `nextInt()`**, o bug **não acontece**! Tudo funciona lisinho!

---

### 3️⃣ A Divisão em `//GROUP 1` e `//GROUP 2`

Olhe as linhas 19 e 22:
```java
//GROUP 1


//GROUP 2
if (age >= 65) { ... }
```

#### O que são esses "Grupos"?
Pense nisso como **dois formulários separados**:

* **GROUP 2 (Já feito):** É o grupo que cuida **exclusivamente da IDADE** (`age`). Ele decide se você é sênior, adulto, bebê ou criança.
* **GROUP 1 (O que ele vai preencher a seguir):** Será um grupo de decisões **exclusivamente sobre o NOME** (`name`)!
  *(Por exemplo: testar se a pessoa esqueceu de digitar o nome e deixou em branco!)*

---

## 🏢 4. A Analogia dos Dois Guichês de Atendimento

Imagine que você entra em uma agência bancária:

```
[ VOCÊ ENTRA ] ──→ Informa seu NOME e sua IDADE
                           │
             ┌─────────────┴─────────────┐
             ▼                           ▼
      [ GUICHÊ 1: GROUP 1 ]       [ GUICHÊ 2: GROUP 2 ]
      Cuida só do seu NOME        Cuida só da sua IDADE
      (Testes sobre o nome)       (Testes sobre a idade)
```

O Guichê 1 toma decisões sobre o nome **sem atrapalhar** as decisões do Guichê 2 sobre a idade! São dois blocos de `if` independentes no mesmo programa.

---

## 🔍 5. Tabela Linha por Linha Completa

| Linha | Código | O que faz? |
| :---: | :--- | :--- |
| **8** | `Scanner scanner = new Scanner(System.in);` | Liga o leitor de teclado. |
| **10** | `String name;` | **NOVO:** Cria o pote para o nome. |
| **11** | `int age;` | Cria o pote para a idade. |
| **13** | `System.out.print("Enter your name: ");` | **NOVO:** Pede para digitar o nome. |
| **14** | `name = scanner.nextLine();` | **NOVO:** Lê o nome digitado. |
| **16** | `System.out.print("Enter your age: ");` | Pede para digitar a idade. |
| **17** | `age = scanner.nextInt();` | Lê a idade digitada. |
| **19** | `//GROUP 1` | Espaço reservado para as decisões sobre o nome. |
| **22** | `//GROUP 2` | Início das decisões sobre a idade (idoso, adulto, etc.). |
| **36** | `scanner.close();` | Desliga o leitor de teclado. |

---

## 🎬 6. O Que Aconteceu no Console (Imagem 4):

Quando ele deu "Play", o programa parou na primeira pergunta:
```
Enter your name: █
```
O cursor fica ali piscando, aguardando o usuário digitar o nome para depois pedir a idade!

Na próxima aula, vamos ver o que ele vai colocar dentro do **`//GROUP 1`**! 🚀
