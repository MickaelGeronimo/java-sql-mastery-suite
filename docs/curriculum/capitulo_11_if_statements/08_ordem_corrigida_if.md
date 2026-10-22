# 🏆 A Ordem Corrigida: Consertando o Bug do Idoso!

Olhe que sensacional: exatamente como previmos na aula passada, **o Bro Code consertou a pegadinha!**

Ele inverteu as posições: agora o teste de **idoso (`age >= 65`)** está lá no topo, e o teste de **adulto (`age >= 18`)** veio logo abaixo!

---

## 📋 1. O Código Perfeito e Corrigido (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // if statement = performs a block of code if its condition is true

        int age = 50;

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
    }
}
```

---

## 🔄 2. O Que Mudou? (Antes vs Depois)

Compare lado a lado onde estava o erro e onde está o acerto:

```
❌ ANTES (COM BUG):                       ✅ AGORA (CORRIGIDO):

if (age >= 18) {                          if (age >= 65) {           👈 O teste mais difícil
    "You are an adult!";                      "You are a senior!";      ficou no topo!
}                                         }
else if (age >= 65) {                     else if (age >= 18) {      👈 O teste mais amplo
    "You are a senior!";                      "You are an adult!";      ficou embaixo!
}                                         }
```

---

## 🧠 3. O Passo a Passo do Computador com `age = 50` (Teste da Imagem)

O autor colocou `age = 50` para testar. Veja como o Java pensou:

```
[LINHA 7]: int age = 50;
   💭 "A idade agora é 50 anos."

[LINHA 9]: if (age >= 65)
   💭 "Teste 1: 50 é MAIOR OU IGUAL a 65?"
   👉 "NÃO (false)! 50 ainda não é idoso. Pula pro próximo teste!"

[LINHA 12]: else if (age >= 18)
   💭 "Teste 2: 50 é MAIOR OU IGUAL a 18?"
   👉 "SIM (true)! 50 é com certeza maior que 18!"
   🎉 "Achei o bloco premiado! Entro aqui!"

[LINHA 13]: System.out.println("You are an adult!");
   🖥️ Console imprime: You are an adult!

[LINHA 15 em diante]:
   🏃‍♂️💨 "Já fiz o trabalho na linha 13! Pulo todo o resto até o fim do programa!"
```

---

## 👴 4. E se agora a idade for `70`? Vamos ver a mágica acontecer!

Se você colocar `age = 70`:

1. Chega na linha 9: `if (70 >= 65)` $\rightarrow$ **SIM (`true`)!**
2. Entra direto e imprime: **`"You are a senior!"`**! 👴🎉
3. Ignora todo o restante!

Agora sim o idoso de 70 anos é chamado de idoso, e o adulto de 50 anos é chamado de adulto. **O sistema está perfeito!**

---

## 🔍 5. Tabela de Funcionamento Definitiva

| Se a idade for... | Portão 1 (`>= 65`) | Portão 2 (`>= 18`) | Portão 3 (`< 0`) | Portão 4 (`== 0`) | O que aparece na tela? |
| :---: | :---: | :---: | :---: | :---: | :--- |
| **`75`** | ✅ **SIM** | *(ignora)* | *(ignora)* | *(ignora)* | `You are a senior!` 👴 |
| **`50`** *(Teste do vídeo)* | ❌ NÃO | ✅ **SIM** | *(ignora)* | *(ignora)* | `You are an adult!` 👨 |
| **`10`** | ❌ NÃO | ❌ NÃO | ❌ NÃO | ❌ NÃO | `You are a child!` 🧒 |
| **`0`** | ❌ NÃO | ❌ NÃO | ❌ NÃO | ✅ **SIM** | `You are a baby!` 🍼 |
| **`-2`** | ❌ NÃO | ❌ NÃO | ✅ **SIM** | *(ignora)* | `You haven't been born yet!` 🛸 |

---

## 💡 A Lição Mais Importante Desta Aula:

> 🎯 **Regra de Ouro da Ordem do `if`:**
> Sempre organize seus testes da **regra mais exigente / mais alta** para a **regra mais ampla / mais baixa**.
> 
> * 1º: Idoso ($\ge 65$)
> * 2º: Adulto ($\ge 18$)
> * 3º: Casos especiais ($< 0$ ou $== 0$)
> * 4º: O resto (`else`)
