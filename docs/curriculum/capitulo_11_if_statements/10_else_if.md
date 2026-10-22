# 🪜 Tomando Várias Decisões: O Comando `else if` ("SENÃO SE")

Nestas 3 imagens, o Bro Code subiu de nível! Ele ensinou como fazer o programa escolher entre **3 ou mais opções diferentes** usando o **`else if`**.

---

## 📋 1. Código Completo (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // if statement = performs a block of code if its condition is true

        int age = -1;

        if (age >= 18) {
            System.out.println("You are an adult!");
        }
        else if (age < 0) {
            System.out.println("You haven't been born yet!");
        }
        else {
            System.out.println("You are a child!");
        }
    }
}
```

---

## 🤔 2. Por que ele inventou o `else if`?

No código anterior, a gente só tinha **2 caminhos**:
* Se for $\ge 18 \rightarrow$ Adulto.
* Senão $\rightarrow$ Criança.

Mas imagine se alguém digitar idade **`-5`** ou **`-1`**?
O programa antigo diria: *"Você é uma criança!"* ❌ (Errado! Ninguém tem -5 anos!).

Para consertar esse absurdo, o autor criou uma **regra do meio**: o **`else if`**!

---

## 🌊 3. A Cascata de Decisões (Como o Java pensa)

Pense numa **escada rolante com 3 paradas**:

```
                  [ PESSOA COM IDADE ]
                           │
                           ▼
     [ 1ª PARADA ]: if (age >= 18)
     "Tem 18 anos ou mais?"
          ├── SIM (true) ──→ 🖥️ "You are an adult!" (FIM! Não olha o resto)
          └── NÃO (false)
                   │
                   ▼
     [ 2ª PARADA ]: else if (age < 0)
     "O número é menor que zero (negativo)?"
          ├── SIM (true) ──→ 🖥️ "You haven't been born yet!" (FIM!)
          └── NÃO (false)
                   │
                   ▼
     [ 3ª PARADA ]: else (O que sobrou)
          └── 🖥️ "You are a child!" (Tem entre 0 e 17 anos)
```

> 📌 **Regra de Ouro:** O Java testa de cima para baixo. **Assim que ele acha um teste VERDADEIRO (`true`), ele executa e ignora todo o resto que vem abaixo!**

---

## 🔍 4. Tabela Linha por Linha

| Linha | Código | Em Português Claro |
| :---: | :--- | :--- |
| **7** | `int age = -1;` | Guarda o número `-1` na caixinha `age`. |
| **9** | `if (age >= 18) {` | **Teste 1:** -1 é maior ou igual a 18? **NÃO!** Pula para a próxima linha. |
| **10** | `    System.out.println("You are an adult!");` | ⚠️ Pulada! |
| **12** | `else if (age < 0) {` | **Teste 2 ("SENÃO SE"):** -1 é menor que zero? **SIM!** |
| **13** | `    System.out.println("You haven't been born yet!");` | ✅ **Executa!** Mostra: *"Você nem nasceu ainda!"*. |
| **15** | `else {` | ⚠️ Ignorado! Já encontrou a resposta na linha 12. |
| **16** | `    System.out.println("You are a child!");` | ⚠️ Ignorado! |

---

## 🧪 5. Os 3 Testes do Autor no Vídeo:

| Teste | Idade (`age`) | Qual bloco executou? | O que apareceu no Console? |
| :---: | :---: | :--- | :--- |
| **Imagem 2** | `19` | Entrou no 1º `if` ($19 \ge 18$) | **`You are an adult!`** |
| **Imagem 3** | `-1` | Entrou no `else if` ($-1 < 0$) | **`You haven't been born yet!`** |
| **Imagem 1** | `12` | Caiu no `else` final | **`You are a child!`** |

---

## 💡 Dica: Quantos `else if` eu posso ter?
Você pode colocar **quantos `else if` quiser** no meio! Não tem limite:

```java
if (nota >= 9) {
    System.out.println("Excelente!");
} else if (nota >= 7) {
    System.out.println("Bom!");
} else if (nota >= 5) {
    System.out.println("Recuperação!");
} else {
    System.out.println("Reprovado!");
}
```
