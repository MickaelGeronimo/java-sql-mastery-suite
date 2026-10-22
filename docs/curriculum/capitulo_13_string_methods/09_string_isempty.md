# 📭 O Comando `isEmpty()`: Verificando se o Texto está Vazio!

Nessas imagens, o Bro Code preencheu o misterioso **`//GROUP 1`** com uma ferramenta incrível: o **`name.isEmpty()`**!

Agora o programa consegue saber se você realmente digitou um nome ou se teve preguiça e só apertou **Enter** deixando tudo em branco!

---

## 📋 1. Código Completo com GROUP 1 e GROUP 2 (Pronto para copiar)

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
        if (name.isEmpty()) {
            System.out.println("You didn't enter your name!");
        }
        else {
            System.out.println("Hello " + name + "!");
        }

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

## 🌟 2. A Grande Novidade: O que é `name.isEmpty()`?

Na linha 20, temos esse comando:

```java
if (name.isEmpty()) {
```

### O que significa em Português?
* **`name`**: É a caixinha onde guardamos o nome.
* **`.` (o pontinho)**: Diz ao Java: *"faça uma ação com essa caixinha"*.
* **`isEmpty()`**: Vem do inglês **"Está Vazio?"** (`is` = é/está, `empty` = vazio).

> 🧠 **Tradução:** *"SE o nome estiver VAZIO (ou seja, se a pessoa não digitou nada e só deu Enter)..."*

---

## ✉️ 3. A Analogia do Envelope de Carta

Pense na variável `name` como um **envelope de correio**:

```
[ CENÁRIO A: Você só apertou Enter sem digitar nada ]
📬 Envelope aberto: NÃO TEM NADA DENTRO!
O Java pergunta: name.isEmpty()?
👉 SIM (true)!
🖥️ Tela avisa: "You didn't enter your name!" (Você não digitou seu nome!)

──────────────────────────────────────────────────────────

[ CENÁRIO B: Você digitou "Carlos" ]
📬 Envelope aberto: Tem a palavra "Carlos" dentro!
O Java pergunta: name.isEmpty()?
👉 NÃO (false)! O envelope NÃO está vazio!
🖥️ Cai no else: "Hello Carlos!"
```

---

## 🔍 4. Tabela Linha por Linha do GROUP 1

| Linha | Código | O que o computador faz? |
| :---: | :--- | :--- |
| **19** | `//GROUP 1` | Comentário indicando o início do bloco do nome. |
| **20** | `if (name.isEmpty()) {` | **A Pergunta:** A pessoa deixou o nome em branco? |
| **21** | `    System.out.println("You didn't enter your name!");` | Se **SIM** (vazio), dá uma bronca: *"Você não digitou seu nome!"*. |
| **22** | `}` | Fecha o bloco do `if`. |
| **23** | `else {` | Se **NÃO** (a pessoa digitou um nome). |
| **24** | `    System.out.println("Hello " + name + "!");` | Dá as boas-vindas personalizadas: *"Hello [Nome]!"*. |
| **25** | `}` | Fecha o bloco do `else`. |

---

## 👥 5. Como os Dois Grupos Funcionam Juntos?

Muitos iniciantes pensam: *"Se o GROUP 1 rodar, o GROUP 2 é cancelado?"*
👉 **NÃO! Os dois grupos rodam independentemente!**

1. O computador lê as perguntas.
2. O computador avalia o **GROUP 1** (e mostra a mensagem do nome).
3. O computador desce e avalia o **GROUP 2** (e mostra a mensagem da idade).

---

## 🎬 6. Simulação de Dois Testes no Console:

### 🧪 Teste 1: Usuário que preenche tudo certinho
```
Enter your name: Bro ⏎
Enter your age: 25 ⏎

Hello Bro!             👈 (Resposta do GROUP 1)
You are an adult!      👈 (Resposta do GROUP 2)
```

### 🧪 Teste 2: Usuário preguiçoso que dá Enter no nome
```
Enter your name: [Apenas apertou Enter] ⏎
Enter your age: 70 ⏎

You didn't enter your name!   👈 (Resposta do GROUP 1: pegou o vácuo!)
You are a senior!             👈 (Resposta do GROUP 2)
```

---

## 💡 Resumo do que você aprendeu:
* Textos (`String`) têm "superpoderes" que você chama usando um pontinho `.`.
* O `.isEmpty()` devolve **VERDADEIRO (`true`)** se o texto tiver 0 letras, e **FALSO (`false`)** se tiver pelo menos 1 letra.
* Você pode ter múltiplos blocos de `if` no mesmo código para testar coisas diferentes! 🚀
