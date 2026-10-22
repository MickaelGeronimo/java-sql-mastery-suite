# 🧒 Testando com Idade 10: Como o Java Cai no `else`

Nesta imagem, o Bro Code mudou a idade para **`10`** (`int age = 10;`) para mostrar como uma criança normal (com idade positiva) passa pelos testes até cair no **`else`**.

---

## 📋 1. Código Completo com `age = 10` (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // if statement = performs a block of code if its condition is true

        int age = 10;

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

## 🚦 2. O Passo a Passo do Computador com `age = 10`

Imagine uma pessoa de **10 anos** passando por 3 portões de segurança:

```
                      [ CRIANÇA DE 10 ANOS ]
                                │
                                ▼
  [ Portão 1 ]: if (10 >= 18) ?
  💭 "10 é maior ou igual a 18?"
  👉 NÃO (false) ❌ ──→ Pula para o próximo portão!
                                │
                                ▼
  [ Portão 2 ]: else if (10 < 0) ?
  💭 "10 é menor que zero (negativo)?"
  👉 NÃO (false) ❌ ──→ Pula para o próximo portão!
                                │
                                ▼
  [ Portão 3 ]: else (O que sobrou)
  💭 "Já que não é adulto nem negativo, só pode ser criança!"
  👉 SIM (executa!) ✅ ──→ 🖥️ "You are a child!"
```

---

## 🔍 3. Tabela Comparativa de Todos os Casos

Veja como o programa agora é **perfeito e à prova de falhas**:

| Idade digitada | Teste 1 (`>= 18`) | Teste 2 (`< 0`) | Onde cai? | O que aparece na tela? |
| :---: | :---: | :---: | :---: | :--- |
| **`25`** | **SIM (`true`)** | *(nem olha)* | 1º `if` | `"You are an adult!"` |
| **`10`** | NÃO (`false`) | NÃO (`false`) | `else` | **`"You are a child!"`** *(Teste desta imagem)* |
| **`-5`** | NÃO (`false`) | **SIM (`true`)** | `else if` | `"You haven't been born yet!"` |
| **`0`** | NÃO (`false`) | NÃO (`false`) | `else` | `"You are a child!"` *(Recém-nascido)* |

---

## 💡 Resumo:
O `else` funciona como uma **"rede de segurança"**: qualquer número que não seja $\ge 18$ e não seja $< 0$ (ou seja, qualquer número de 0 até 17) vai cair automaticamente no `else`!
