# 🌡️ Capítulo 17: Conversor de Temperatura (Temperature Converter) — Parte 3: A Formatação Perfeita com `printf("%.1f°%s")`

---

## 📌 1. Visão Geral: O Que o Autor Fez Aqui?

Na aula anterior, o nosso programa já funcionava, mas tinha um defeito visual feio:  
Ele mostrava um número gigante e cheio de casas decimais: `37.77777777777778°C`! Ninguém usa um termômetro assim na vida real.

Nestas duas imagens novas (das marcas **2:48:00** até **2:48:30**), o autor:
1. **Substituiu o `println` pelo `printf` (Linha 21):**
   ```java
   System.out.printf("%.1f°%s", newTemp, unit);
   ```
2. **Usou duas máscaras especiais ao mesmo tempo:**
   - `%.1f` para arredondar a temperatura para apenas **1 casa decimal**!
   - `%s` para encaixar o texto da unidade (`"C"` ou `"F"`).
   - O símbolo de grau `°` no meio das duas.
3. **Fez o teste definitivo no console:**
   - Digitou a temperatura: `30.1`.
   - Escolheu converter para Fahrenheit: `f`.
   - O console respondeu limpo, profissional e arredondado: **`48.7°F`**!

---

## 🔬 2. Dissecando a Linha Mágica do `printf` (Linha 21)

Olhe com lupa cada pedacinho da linha 21:

$$\mathbf{System.out.printf(} \ \mathbf{"} \underbrace{\mathbf{\%.1f}}_{\text{1º Espaço Vago}} \ \underbrace{\mathbf{°}}_{\text{Bolinha}} \ \underbrace{\mathbf{\%s}}_{\text{2º Espaço Vago}} \mathbf{"} \ , \ \underbrace{\mathbf{newTemp}}_{\text{Encaixa no \%.1f}} \ , \ \underbrace{\mathbf{unit}}_{\text{Encaixa no \%s}} \ \mathbf{);}$$

| Símbolo | O que significa? | O que acontece no programa? |
| :--- | :--- | :--- |
| `printf` | *Print Formatted* (Imprimir com Formato) | Permite criar um texto com "buracos" para preencher com variáveis. |
| `"..."` | O molde do texto | Tudo o que estiver dentro das aspas será impresso, com os buracos sendo substituídos. |
| `%.1f` | **Buraco nº 1 (Float/Double):** | Pega a variável `newTemp` e corta para **apenas 1 casa decimal** após o ponto! |
| `°` | **Símbolo de Grau:** | É um caractere comum que aparece colado entre o número e a letra. |
| `%s` | **Buraco nº 2 (String/Texto):** | Pega a variável `unit` (que vale `"C"` ou `"F"`) e coloca ali. |
| `, newTemp` | 1º valor a ser encaixado | Preenche o `%.1f`. |
| `, unit` | 2º valor a ser encaixado | Preenche o `%s`. |

---

## 🖥️ 3. O Que Sai no Console? (Conforme Você Pediu)

Olhe o teste da Imagem 2 passo a passo:

1. **Primeira Pergunta:**
   ```text
   Enter the temperature: 30.1
   ```
   *(O usuário digitou `30.1`)*.

2. **Segunda Pergunta:**
   ```text
   Convert to Celsius or Fahrenheit? (C or F): f
   ```
   *(O usuário digitou a letra `f` minúscula)*.

3. **O Que Aconteceu por Trás dos Panos:**
   - O `.toUpperCase()` transformou `f` em `"F"`.
   - O operador ternário testou: `unit.equals("C")`?  
     Como `unit` é `"F"`, deu **FALSO**.
   - O Java foi para a segunda conta: `(30.1 * 5 / 9) + 32`  
     $30.1 \times 5 / 9 = 16.72222...$  
     $16.72222... + 32 = 48.72222...$
   - O `printf("%.1f")` pegou `48.72222...` e arredondou para **`48.7`**!
   - Encaixou o `°` e a letra `F`.

4. **Saída Final no Console:**
   ```text
   48.7°F
   ```

---

## ⚖️ 4. Comparativo: `println` vs `printf`

| Método | Código Usado | O que saía no console? | Avaliação |
| :--- | :--- | :--- | :--- |
| **Antes (`println`)** | `System.out.println(newTemp + "°" + unit);` | `37.77777777777778°C` | ❌ Muito feio e poluído |
| **Agora (`printf`)** | `System.out.printf("%.1f°%s", newTemp, unit);` | `48.7°F` *(ou `37.8°C`)* | ✅ Limpo, elegante e profissional! |

---

## 🔍 5. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 25)

Abaixo está o código final do projeto, exatamente como visto no IntelliJ do Bro Code:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa o Scanner para que o Java saiba ler o teclado.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 3:** `public class Main {`
- **O que faz:** Abre a classe principal.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** O método `main`, por onde o Java começa a rodar.

### **Linha 5:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 6:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Cria e ativa o leitor de teclado chamado `scanner`.

### **Linha 7:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 8:** `        double temp;`
- **O que faz:** Cria a variável decimal `temp` (temperatura que o usuário digita).

### **Linha 9:** `        double newTemp;`
- **O que faz:** Cria a variável decimal `newTemp` (temperatura convertida).

### **Linha 10:** `        String unit;`
- **O que faz:** Cria a variável de texto `unit` (para a letra `"C"` ou `"F"`).

### **Linha 11:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 12:** `        System.out.print("Enter the temperature: ");`
- **O que faz:** Mostra na tela o pedido para digitar a temperatura (sem pular linha).

### **Linha 13:** `        temp = scanner.nextDouble();`
- **O que faz:** Lê o número digitado pelo usuário (ex: `30.1`) e guarda na variável `temp`.

### **Linha 14:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 15:** `        System.out.print("Convert to Celsius or Fahrenheit? (C or F): ");`
- **O que faz:** Mostra na tela o pedido para escolher a unidade de conversão (`C` ou `F`).

### **Linha 16:** `        unit = scanner.next().toUpperCase();`
- **O que faz:** Lê a letra digitada pelo usuário e a transforma em maiúscula usando `.toUpperCase()`.

### **Linha 17:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 18:** `        // (condition) ? true : false`
- **O que faz:** Comentário de estudo lembrando a fórmula do operador ternário.

### **Linha 19:** `        newTemp = (unit.equals("C")) ? (temp - 32) * 5 / 9 : (temp * 5 / 9) + 32;`
- **O que faz:** Se `unit` for `"C"`, aplica a fórmula de Celsius `(temp - 32) * 5 / 9`. Se for `"F"`, aplica a fórmula de Fahrenheit. Salva o resultado em `newTemp`.

### **Linha 20:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 21:** `        System.out.printf("%.1f°%s", newTemp, unit);`
- **O que faz:** **A grande novidade!** Imprime com `printf`. O `%.1f` formata `newTemp` com 1 casa decimal, coloca o símbolo `°` e o `%s` encaixa a letra da unidade `unit`.

### **Linha 22:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 23:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado para devolver a memória ao sistema.

### **Linha 24:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 25:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 6. Código Completo Pronto para Copiar e Rodar

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double temp;
        double newTemp;
        String unit;

        System.out.print("Enter the temperature: ");
        temp = scanner.nextDouble();

        System.out.print("Convert to Celsius or Fahrenheit? (C or F): ");
        unit = scanner.next().toUpperCase();

        // (condition) ? true : false
        newTemp = (unit.equals("C")) ? (temp - 32) * 5 / 9 : (temp * 5 / 9) + 32;

        System.out.printf("%.1f°%s\n", newTemp, unit);

        scanner.close();
    }
}
```

> **Dica:** Colocamos `\n` no final do formato (`"%.1f°%s\n"`) para pular uma linha no final e deixar o terminal 100% organizado!
