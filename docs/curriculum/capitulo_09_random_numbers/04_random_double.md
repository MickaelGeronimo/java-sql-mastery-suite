# 🎲 Sorteando Números Decimais: O Poder do `random.nextDouble()`!

Nesta imagem, o Bro Code mudou de categoria: em vez de sortear números inteiros (1, 2, 3), ele ensina como sortear **números decimais quebrados com vírgula** usando o **`random.nextDouble()`**!

---

## 📋 1. Código Completo (Pronto para copiar)

```java
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        double number;

        number = random.nextDouble();

        System.out.println(number);
    }
}
```

---

## 🔍 2. As Duas Mudanças no Código:

Olhe a comparação com o código anterior:

| Antes (Números Inteiros) | Agora (Números Decimais) | O que mudou? |
| :--- | :--- | :--- |
| `int number;` | **`double number;`** | A caixinha agora aceita casas decimais com ponto flutuante. |
| `number = random.nextInt();` | **`number = random.nextDouble();`** | O robô agora gira a roleta de números decimais! |

---

## 🎯 3. A Regra do `nextDouble()`: Sempre Entre `0.0` e `1.0`!

Quando você roda `random.nextDouble()` com os parênteses vazios, o Java segue uma regra matemática muito específica:

> 📌 **Ele SEMPRE sorteia um número entre `0.0` (inclusivo) e `1.0` (exclusivo)!**

Ou seja:
* O número sempre vai começar com **`0.`** (zero ponto alguma coisa).
* Pode ser `0.0000001` até `0.9999999...`
* **NUNCA** vai ser negativo.
* **NUNCA** vai chegar no número `1.0` cravado.

Olhe o resultado no console da imagem:
```
0.7804544049612914
```
Começou com `0.` e veio com várias casas decimais super precisas!

---

## 🎮 4. Para que serve isso na vida real? (Probabilidades e Jogos!)

Pense no número entre `0.0` e `1.0` como uma **PORCENTAGEM de 0% a 100%**:

```
0.0   ──────→   0%
0.25  ──────→  25%
0.50  ──────→  50%
0.78  ──────→  78% (O número que saiu na imagem!)
1.0   ──────→ 100%
```

### Exemplo em Jogos de Videogame (Drop de Espada Rara):
Imagine que você derrotou um monstro e quer ter **20% de chance** de dropar uma espada mágica:

```java
double chance = random.nextDouble();

if (chance < 0.20) {
    System.out.println("PARABÉNS! Você ganhou a Espada Lendária! ⚔️✨");
} else {
    System.out.println("O monstro só deixou moedas comuns... 🪙");
}
```
Como o `nextDouble()` sorteia de 0 a 1, qualquer valor abaixo de `0.20` representa exatamente **20% de chance** de vitória!

---

## 🔍 5. Tabela Linha por Linha

| Linha | Código | O que faz? |
| :---: | :--- | :--- |
| **8** | `double number;` | Cria a caixinha do tipo `double` para guardar o número quebrado. |
| **10** | `number = random.nextDouble();` | Roda o sorteio decimal e gera um valor entre `0.0` e `1.0`. |
| **12** | `System.out.println(number);` | Mostra o número decimal no console (`0.7804544...`). |
