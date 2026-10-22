# 📉 Operador de Decremento (`x--`) — O Irmão Inverso do `x++`

Se o `x++` serve para **somar 1**, o `x--` faz o caminho contrário: ele serve para **subtrair 1**!

---

## 📋 1. Código da Imagem 2 (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // Increment and Decrement Operators

        int x = 1;

        x--;
        x--;
        x--;

        System.out.println(x);
    }
}
```

---

## 🔍 2. O que aconteceu em cada imagem?

### 🖼️ Imagem 1: O jeito longo (`x = x - 1;`)
```java
int x = 1;
x = x - 1;  // 1 - 1 = 0
System.out.println(x); // Imprime 0
```
Aqui o autor mostrou a forma clássica de tirar 1 de uma variável.

---

### 🖼️ Imagem 2: O atalho `x--;` três vezes (Entrando nos negativos!)
```java
int x = 1;

x--;  // 1 - 1 = 0
x--;  // 0 - 1 = -1
x--;  // -1 - 1 = -2

System.out.println(x); // Imprime -2
```

#### 🏢 A Analogia do Elevador e Garagem:
Pense num prédio:
* Você está no **1º Andar** (`x = 1`).
* Desce 1 andar (`x--`) $\rightarrow$ chega no **Térreo** (`0`).
* Desce mais 1 andar (`x--`) $\rightarrow$ chega no **1º Subsolo / Garagem** (`-1`).
* Desce mais 1 andar (`x--`) $\rightarrow$ chega no **2º Subsolo** (`-2`).

O Java lida com **números negativos** com total naturalidade!

---

### 🖼️ Imagem 3: Misturando Soma e Subtração (`x++` e `x--`)
```java
int x = 1;

x++;  // Sobe 1: 1 + 1 = 2
x--;  // Desce 1: 2 - 1 = 1

System.out.println(x); // Resultado final volta a ser 1!
```

---

## ⚔️ 3. A Grande Batalha: `++` vs `--`

| Operador | Nome | O que faz? | Exemplo com `x = 10` | Novo valor de `x` |
| :---: | :---: | :---: | :---: | :---: |
| `++` | **Incremento** | Soma $+1$ | `x++;` | **`11`** |
| `--` | **Decremento** | Subtrai $-1$ | `x--;` | **`9`** |

---

## 🎮 4. Exemplo Prático em Jogos:

```java
int vidas = 3;  // Jogador começa com 3 vidas

vidas--;  // Caiu no buraco! (Vidas = 2)
vidas--;  // Encostou no monstro! (Vidas = 1)
vidas++;  // Pegou um cogumelo verde! (Vidas = 2)
vidas--;  // Caiu na lava! (Vidas = 1)
vidas--;  // Game Over! (Vidas = 0)
```
