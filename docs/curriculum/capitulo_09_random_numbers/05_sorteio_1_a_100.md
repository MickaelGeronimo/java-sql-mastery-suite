# 🎯 Sorteio de 1 a 100: Criando uma Rifa com `(1, 101)`!

Nesta imagem, o Bro Code deu adeus aos dadinhos de 6 lados e transformou o programa em um **globo de bingo / sorteio de rifa de 1 a 100**!

---

## 📋 1. Código Completo de 1 a 100 (Pronto para copiar)

```java
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        int number1;
        int number2;
        int number3;

        number1 = random.nextInt(1, 101);
        number2 = random.nextInt(1, 101);
        number3 = random.nextInt(1, 101);

        System.out.println(number1);
        System.out.println(number2);
        System.out.println(number3);
    }
}
```

---

## 🔍 2. O Que Mudou?

Olhe a mudança nas linhas 12, 13 e 14:

```
Antes (Dado de 1 a 6):       number1 = random.nextInt(1, 7);
Agora (Rifa de 1 a 100):     number1 = random.nextInt(1, 101);
```

---

## 🤔 3. Por que ele escreveu `101` e não `100`?

Lembre-se da regra de ouro do Java:
👉 **"O primeiro número ENTRA, o último número NÃO ENTRA!"**

* **`1` (O início):** É **inclusivo** $\rightarrow$ O número `1` pode ser sorteado!
* **`101` (O fim):** É **exclusivo** $\rightarrow$ O Java para um número antes do `101`, ou seja, para no **`100`**!

```
[ SORTEIO DE 1 A 101 ]
  Menor número possível: 1   ✅
  Maior número possível: 100 ✅
  O número 101:          NUNCA SAI ❌ (É a parede onde o sorteio para!)
```

> ⚠️ **Cuidado:** Se você tivesse escrito `random.nextInt(1, 100)`, o número 100 **nunca iria sair** (o sorteio só iria até 99!). Por isso colocamos `101`!

---

## 🎟️ 4. Onde isso é usado no mundo real?

Sortear números de **1 a 100** é uma das coisas mais usadas em jogos e sistemas:

1. **Jogos de RPG (Chance de Acerto Crítico):**
   * Sorteia de 1 a 100. Se tirar `95` ou mais $\rightarrow$ Dano crítico no monstro!
2. **Sistemas de Rifa / Bingo:**
   * Sortear 3 bilhetes premiados entre 100 participantes.
3. **Jogo de Adivinhação:**
   * O computador pensa num número de 1 a 100 e você tem que adivinhar qual é!

---

## 🖥️ 5. O Resultado no Console da Imagem:

```
5
37
34
```

* **Bilhete 1:** Saiu o número **`5`**.
* **Bilhete 2:** Saiu o número **`37`**.
* **Bilhete 3:** Saiu o número **`34`**.

Repare que todos os números ficaram perfeitamente dentro do intervalo de 1 a 100! Nenhum número negativo, nenhum 0 e nenhum número passando de 100! 🎯
