# 📐 Melhoria no Código — Adicionando "cm²" ao Resultado

Nessa parte do vídeo, o Bro Code fez uma **melhoria pequena mas importante** no código anterior: ele adicionou a **unidade de medida** (`cm²`) no final do resultado.

---

## O que mudou? Apenas a linha 22!

### ❌ Antes (sem unidade):
```java
System.out.println("The area is: " + area);
```
Saída: `The area is: 33.92`

### ✅ Depois (com unidade):
```java
System.out.println("The area is: " + area + "cm²");
```
Saída: `The area is: 33.92cm²`

---

## Conceito Novo: Concatenando 3 (ou mais) pedaços com `+`

Antes, a gente juntava **2 pedaços**: um texto + uma variável.

Agora estamos juntando **3 pedaços**: texto + variável + texto.

```
  Pedaço 1          Pedaço 2       Pedaço 3
     ↓                 ↓              ↓
"The area is: "   +   area    +    "cm²"
"The area is: "   +   33.92   +    "cm²"

Resultado: "The area is: 33.92cm²"
```

### Regra simples:

| O que é | Como escreve | Exemplo |
|---------|-------------|---------|
| **Texto fixo** | Entre `"aspas"` | `"The area is: "` |
| **Variável** | Sem aspas, só o nome | `area` |
| **Juntar** | Usa o `+` entre eles | `"texto" + variavel + "texto"` |

### Analogia 🧠

Imagine que você está montando uma frase com blocos de LEGO:

```
🟦 "The area is: "  🟩 33.92  🟦 "cm²"
         ↓               ↓          ↓
         CLICA     +     CLICA   +  CLICA

Resultado: 🟦🟩🟦 → "The area is: 33.92cm²"
```

Você pode encaixar **quantos blocos quiser**! Não tem limite. Exemplo com 5 pedaços:

```java
System.out.println("Nome: " + name + " | Idade: " + age + " anos");
// Resultado: "Nome: mickael | Idade: 23 anos"
```

---

## Como digitar o símbolo `²` (elevado ao quadrado)?

O Bro Code mostrou o atalho no vídeo:

### No Windows:
1. Ativa o **NumLock** (tecla no teclado numérico)
2. Segura a tecla **Alt**
3. Digita **0178** no **teclado numérico** (os números da direita)
4. Solta o **Alt**
5. O `²` aparece! ✅

### No Mac:
1. Pressiona **⌘ + Espaço** (abre o Character Viewer)
2. Pesquisa o caractere `²`
3. Clica nele

> [!TIP]
> Se não conseguir digitar o `²`, você pode simplesmente copiar e colar daqui: **²**
> Ou usar a versão escrita: `"cm^2"` (que é mais fácil de digitar).

---

## Código completo final:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Calculate area of a rectangle

        double width = 0;
        double height = 0;
        double area = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the width: ");
        width = scanner.nextDouble();

        System.out.print("Enter the height: ");
        height = scanner.nextDouble();

        area = width * height;

        System.out.println("The area is: " + area + "cm²");

        scanner.close();
    }
}
```

---

## 🖥️ Resultado no console:

```
Enter the width: 5.3
Enter the height: 6.4
The area is: 33.92cm²
```

5.3 × 6.4 = 33.92 ✅

---

## 💡 Resumo do que você aprendeu aqui

| # | Conceito | Exemplo |
|---|----------|---------|
| 1 | **Concatenar 3+ pedaços** | `"texto" + variavel + "texto"` |
| 2 | **Texto vai entre aspas** | `"cm²"` |
| 3 | **Variável vai sem aspas** | `area` |
| 4 | **Caractere especial ²** | Alt + 0178 no Windows |
| 5 | **Sem limite de pedaços** | Pode juntar quantos quiser com `+` |
