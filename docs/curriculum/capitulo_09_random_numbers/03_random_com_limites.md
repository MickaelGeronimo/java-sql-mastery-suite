# 🎲 O Segredo dos Limites no `Random`: Simulando um Dado de 6 Lados!

Adeus aos números malucos de 2 bilhões! Nestas duas imagens, o Bro Code ensina como **controlar a roleta** e colocar limites no sorteio.

Mas tem uma **pegadinha clássica do Java** que você precisa entender!

---

## 📋 1. Código Completo Corrigido (Pronto para copiar)

```java
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        int number;

        number = random.nextInt(1, 7); // Sorteia de 1 a 6!

        System.out.println(number);
    }
}
```

---

## 🎯 2. A Regra de Ouro do Java: "O Primeiro ENTRA, o Último NÃO ENTRA!"

Quando você coloca dois números dentro dos parênteses do `nextInt`:

```java
random.nextInt(início, fim);
```

O Java segue uma regra internacional da computação chamada **Inclusivo e Exclusivo**:

* **O 1º número (Início):** É **INCLUSIVO** $\rightarrow$ Ele **pode** ser sorteado!
* **O 2º número (Fim):** É **EXCLUSIVO** $\rightarrow$ Ele **NUNCA** é sorteado! O sorteio para um número antes!

---

## 😱 3. O Erro da Imagem 1: `random.nextInt(1, 6);`

Na Imagem 1, o autor escreveu:
```java
number = random.nextInt(1, 6);
```

Você olha para isso e pensa:
> *"Legal, vai sortear de 1 a 6, igual um dado!"*

### O Choque: O número 6 NUNCA vai sair! ❌
Pela regra do Java, o `6` está excluído! Os únicos números que esse sorteio consegue gerar são:
$$\mathbf{1, \quad 2, \quad 3, \quad 4, \quad 5}$$

Se você estivesse jogando um jogo de tabuleiro com esse código, **ninguém nunca tiraria 6 no dado**! 😂

---

## 🛠️ 4. O Conserto na Imagem 2: `random.nextInt(1, 7);`

Para conseguir sortear o número 6 de verdade, o Bro Code aumentou o limite para **`7`**:

```java
number = random.nextInt(1, 7); // ✅ AGORA SIM!
```

Como o `7` fica de fora, o sorteio vai exatamente de **1 até 6**:

```
[ SORTEIO DE 1 A 7 ]
  1 ──→ PODE SAIR ✅
  2 ──→ PODE SAIR ✅
  3 ──→ PODE SAIR ✅
  4 ──→ PODE SAIR ✅
  5 ──→ PODE SAIR ✅
  6 ──→ PODE SAIR ✅
  7 ──→ NUNCA SAI ❌ (É o muro onde o sorteio bate e volta!)
```

---

## 🎲 5. Tabela de Exemplos Práticos do Dia a Dia

Para você nunca mais errar limites no Java, veja como montar sorteios comuns:

| O que você quer sortear? | Código Java | Números que podem sair |
| :--- | :--- | :--- |
| **Dado de 6 lados** | `random.nextInt(1, 7);` | 1, 2, 3, 4, 5, 6 |
| **Dado de 20 lados (RPG)** | `random.nextInt(1, 21);` | De 1 até 20 |
| **Número de 1 a 100** | `random.nextInt(1, 101);` | De 1 até 100 |
| **Idade de um jovem (13 a 17)** | `random.nextInt(13, 18);` | 13, 14, 15, 16, 17 |
| **Dezenas da Mega-Sena (1 a 60)** | `random.nextInt(1, 61);` | De 1 até 60 |

> 📌 **Macete para lembrar sempre:** Se você quer que o sorteio vá até o número $X$, coloque sempre **$X + 1$** no final!

---

## 🔍 6. Resumo das Linhas de Código

| Linha | Código | Explicação Simples |
| :---: | :--- | :--- |
| **8** | `int number;` | Cria a gaveta para o número do dado. |
| **10** | `number = random.nextInt(1, 7);` | Rola o dado! Gera um número entre 1 e 6 (para antes do 7). |
| **12** | `System.out.println(number);` | Mostra o resultado do dado no console! |
