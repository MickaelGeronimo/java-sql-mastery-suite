# 🎲🎲🎲 Rolando 3 Dados ao Mesmo Tempo!

Nestas duas imagens, o Bro Code deu mais um passo prático: em vez de rolar apenas **um dado**, ele simulou o que acontece em quase todo jogo de tabuleiro: **jogar 3 dados de uma vez na mesa**!

Além disso, ele mostrou um **erro clássico de copiar e colar** que todo programador já cometeu.

---

## 📋 1. Código Completo e Corrigido (Pronto para copiar)

```java
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        int number1;
        int number2;
        int number3;

        number1 = random.nextInt(1, 7);
        number2 = random.nextInt(1, 7);
        number3 = random.nextInt(1, 7);

        System.out.println(number1);
        System.out.println(number2);
        System.out.println(number3);
    }
}
```

---

## ⚠️ 2. O Erro do "Copiar e Colar" da Imagem 1

Olhe com atenção para a **Imagem 1**:

```java
// ❌ O que ele tinha feito:
number1 = random.nextInt(1, 7);
number1 = random.nextInt(1, 7); // Repetiu number1!
number1 = random.nextInt(1, 7); // Repetiu number1!

System.out.println(number); // ❌ O 'number' ficou vermelho!
```

### Por que deu erro?
1. **A caixinha antiga sumiu:** A variável antiga se chamava apenas `number`. Quando ele trocou o nome lá em cima para `number1`, `number2`, `number3`, a caixinha `number` deixou de existir! O Java ficou perdido no `println(number)`.
2. **Sobrescrevendo o dado:** Se você joga o dado e guarda no `number1`, e depois joga de novo e guarda no **mesmo** `number1`, o primeiro número é **jogado no lixo**! 

---

## 🛠️ 3. O Conserto na Imagem 2: Três Caixas Separadas

Na Imagem 2, ele corrigiu dando nomes únicos para cada dado:

```
🎲 Dado 1: number1 = random.nextInt(1, 7);  👉 Caiu 2!
🎲 Dado 2: number2 = random.nextInt(1, 7);  👉 Caiu 2!
🎲 Dado 3: number3 = random.nextInt(1, 7);  👉 Caiu 5!
```

Agora cada dado tem o seu **próprio copinho** na mesa para não misturar os resultados!

---

## 🤔 4. "Ué, pode sair número repetido no sorteio?" (O caso do `2, 2, 5`)

Olhe o que apareceu no console na Imagem 2:
```
2
2
5
```

Muitos iniciantes perguntam:
> *"O Java estragou? Por que saiu o número 2 duas vezes seguidas?"*

### A Resposta: O Java é PERFEITO!
Pense na vida real: quando você pega 3 dados de plástico e joga no chão, **pode sair dois dados com o número 2 virado para cima?**
👉 **CLARO QUE SIM!** Isso acontece o tempo todo em jogos de dados (tirar uma dupla!).

Cada sorteio do `random.nextInt(1, 7)` é **completamente independente**:
* O segundo dado não sabe (e não se importa) com o número que o primeiro dado tirou.
* Cada dado tem exatamente a mesma chance (1 em 6) de tirar qualquer número de 1 a 6!

---

## 🔍 5. Tabela Linha por Linha Completa

| Linha | Código | O que o computador faz? |
| :---: | :--- | :--- |
| **8-10** | `int number1; int number2; int number3;` | Separa **3 caixinhas vazias** na memória (uma para cada dado). |
| **12** | `number1 = random.nextInt(1, 7);` | Rola o **1º dado** (entre 1 e 6) e guarda em `number1`. |
| **13** | `number2 = random.nextInt(1, 7);` | Rola o **2º dado** (entre 1 e 6) e guarda em `number2`. |
| **14** | `number3 = random.nextInt(1, 7);` | Rola o **3º dado** (entre 1 e 6) e guarda em `number3`. |
| **16** | `System.out.println(number1);` | Mostra o resultado do 1º dado (`2`). |
| **17** | `System.out.println(number2);` | Mostra o resultado do 2º dado (`2`). |
| **18** | `System.out.println(number3);` | Mostra o resultado do 3º dado (`5`). |

---

## 💡 Dica Bônus para Programadores Preguiçosos 😴

Você sabia que nas linhas 8 a 10, você pode declarar as 3 variáveis em **uma única linha**?

```java
// Em vez de escrever 3 linhas:
int number1;
int number2;
int number3;

// Você pode fazer assim (separando por vírgula):
int number1, number2, number3;
```
Faz **exatamente a mesma coisa**, só economiza espaço no código! 🚀
