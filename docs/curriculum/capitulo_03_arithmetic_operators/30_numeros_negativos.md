# 🥶 Como o Java Lida com Subtração e Números Negativos

Se você ficou com dúvida de como o Java se comporta quando um número fica **negativo** (com o sinal de menos `-`), a resposta curta é:
👉 **O Java funciona EXATAMENTE como a matemática da vida real!**

O tipo `int` e o tipo `double` aceitam números positivos, zero e negativos com total tranquilidade.

---

## 🏦 1. A Melhor Analogia: Sua Conta no Banco

Pense no saldo da sua conta bancária:

```
Você tem R$ 10,00 na conta.
Você vai na padaria e gasta R$ 15,00 no cartão de débito.

Conta: 10 - 15 = -5
```

O seu saldo agora é **`-5`** (você ficou devendo 5 reais pro banco!).
O Java entende isso perfeitamente:

```java
int saldo = 10;

saldo = saldo - 15; // ou saldo -= 15;

System.out.println(saldo); // Imprime: -5
```

---

## 🌡️ 2. Outra Analogia: O Termômetro no Inverno

Imagine uma cidade muito fria:

* A temperatura está em **`2°C`**.
* À noite, a temperatura cai **`5 graus`**.
* Conta: $2 - 5 = \mathbf{-3^\circ C}$.

```java
int temperatura = 2;

temperatura -= 5;

System.out.println(temperatura); // Imprime: -3
```

---

## 🪜 3. As 3 Formas de Fazer Subtração em Java

Você pode subtrair de 3 jeitos, dependendo da sua necessidade:

### 1️⃣ Subtração entre duas variáveis normais:
```java
int preco = 100;
int desconto = 30;

int total = preco - desconto; // 100 - 30 = 70
```

### 2️⃣ Tirando um valor específico da própria variável (`-=`):
```java
int pontos = 50;

pontos -= 20; // Tirou 20 pontos de uma vez! (Pontos vira 30)
```

### 3️⃣ Tirando de 1 em 1 (`--`):
```java
int vidas = 3;

vidas--; // Perdeu 1 vida (vira 2)
vidas--; // Perdeu mais 1 vida (vira 1)
```

---

## 🧮 4. As Regrinhas de Sinais no Java (Igual na Escola!)

O Java respeita todas as regrinhas matemáticas de sinais que a gente aprendeu na escola:

| Operação | Código Java | Conta Matemática | Resultado |
| :--- | :--- | :--- | :---: |
| **Menos com Mais** | `-10 + 4` | Devo 10, pago 4 | **`-6`** |
| **Menos com Menos (Soma de dívida)** | `-5 - 5` | Devo 5 e gasto mais 5 | **`-10`** |
| **Multiplicação de Negativos** | `-3 * -2` | Negativo vezes negativo vira positivo | **`6`** |
| **Divisão com Negativo** | `-10 / 2` | 10 negativo dividido por 2 | **`-5`** |

---

## 📋 5. Código para Testar na sua IDE

Copie este código para o seu arquivo `Main.java` para ver os números negativos funcionando:

```java
public class Main {
    public static void main(String[] args) {

        int saldo = 10;
        saldo -= 15; // Ficou devendo 5

        int temperatura = 2;
        temperatura -= 5; // Ficou -3 graus

        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Temperatura: " + temperatura + "°C");
    }
}
```

### Saída no Console:
```
Saldo: R$ -5
Temperatura: -3°C
```

---

## 💡 Resumo:
* Não precisa fazer nada de especial para usar números negativos no Java.
* O sinal de menos `-` funciona tanto para **fazer a conta de diminuir** (`10 - 2`) quanto para **representar um número negativo** (`int x = -5;`).
