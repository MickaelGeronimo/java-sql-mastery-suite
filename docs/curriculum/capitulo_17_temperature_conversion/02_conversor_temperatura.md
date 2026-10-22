# 🌡️ Capítulo 17: Conversor de Temperatura (Temperature Converter) — Parte 2: O Operador Ternário Matemático e o Símbolo de Grau (`°`)

---

## 📌 1. Visão Geral: O Que o Autor Fez Aqui?

Nestas 4 imagens (das marcas **2:46:28** até **2:47:48**), o autor deu o passo mais importante do projeto:
1. **Aplicou o Operador Ternário para decidir qual conta matemática fazer (Linha 19):**  
   Se o usuário quer converter para Celsius (`C`), faz a conta de Celsius. Se quer Fahrenheit (`F`), faz a conta de Fahrenheit!
2. **O aviso de correção na tela (Imagem 1):**  
   O autor cometeu um pequeno deslize de digitação na fórmula de Fahrenheit durante a gravação e colocou um aviso na tela em letras brancas: `correction: (temp * 9 / 5) + 32`.
3. **Fez o teste prático de 100°F para Celsius (Imagem 3):**  
   Digitou `100` e escolheu `c`. O Java calculou $(100 - 32) \times 5 / 9$ e respondeu: `37.77777777777778`!
4. **Adicionou o símbolo de grau `°` e a unidade (Imagem 4):**  
   Juntou tudo na linha 21: `newTemp + "°" + unit`, fazendo o terminal mostrar `37.77777777777778°C`!

---

## 🧮 2. A Matemática das Temperaturas: Como Funciona no Mundo Real?

Converter temperaturas não é como converter quilos ou metros (não basta só multiplicar). Elas têm pontos de partida diferentes (a água congela a $0^\circ\text{C}$ mas a $32^\circ\text{F}$):

| Objetivo | Fórmula da Física | O que faz no código? |
| :--- | :--- | :--- |
| **Converter para Celsius (`C`)** | $(F - 32) \times \frac{5}{9}$ | `(temp - 32) * 5 / 9` |
| **Converter para Fahrenheit (`F`)** | $(C \times \frac{9}{5}) + 32$ | `(temp * 9 / 5) + 32` *(ou `* 1.8 + 32`)* |

### ⚠️ A Curiosidade da Imagem 1: Por que apareceu `correction: (temp * 9 / 5) + 32`?
Na hora de gravar o vídeo, o autor digitou sem querer `5 / 9` nas duas fórmulas. Depois, na edição do vídeo, ele percebeu o erro e colocou aquele texto grande na parte de baixo da tela avisando:  
`correction: (temp * 9 / 5) + 32`  
*(Pode ficar tranquilo! A conta que ele testou para Celsius estava 100% certa!)*

---

## 🔬 3. Dissecando a Linha do Operador Ternário (Linha 19)

Observe a estrutura da linha 19:

$$\mathbf{newTemp} = \mathbf{(unit.equals("C"))} \ ? \ \mathbf{(temp - 32) * 5 / 9} \ : \ \mathbf{(temp * 5 / 9) + 32};$$

Vamos quebrar em 3 pedaços:
1. **A Condição / Pergunta:** `(unit.equals("C"))`  
   - Pergunta: *"O usuário quer converter para Celsius (`C`)?"*.  
   - *(Lembre-se: em Java, para comparar textos `String`, usamos `.equals()`, e não `==`!)*.
2. **Se for VERDADEIRO (`?`):** `(temp - 32) * 5 / 9`  
   - Se o usuário escolheu `C`, aplica a fórmula para encontrar os graus Celsius.
3. **Se for FALSO (`:`):** `(temp * 5 / 9) + 32` *(ou com a correção `* 9 / 5`)*  
   - Se o usuário NÃO escolheu `C` (ou seja, escolheu `F`), aplica a fórmula para encontrar Fahrenheit.

O resultado escolhido é guardado dentro da variável `newTemp`.

---

## 🖥️ 4. O Que Sai no Console em Cada Teste? (Conforme Você Pediu)

### 🔴 Imagem 3 (Teste com `100` e `c` — Só o número):
1. O programa pergunta: `Enter the temperature: ` $\rightarrow$ O autor digita `100`.
2. O programa pergunta: `Convert to Celsius or Fahrenheit? (C or F): ` $\rightarrow$ O autor digita `c`.
3. O `.toUpperCase()` transforma `c` em `C`.
4. O operador ternário vê que `unit.equals("C")` é **VERDADEIRO**.
5. Faz a conta:
   $$(100 - 32) \times 5 / 9 = 68 \times 5 / 9 = \frac{340}{9} = 37.77777777777778$$
6. A linha 21 (`System.out.println(newTemp);`) imprime:
```text
Enter the temperature: 100
Convert to Celsius or Fahrenheit? (C or F): c
37.77777777777778
```

---

### 🟢 Imagem 4 (Teste com o Símbolo de Grau `°` e a Letra `C`):
Na linha 21, o autor mudou para:
```java
System.out.println(newTemp + "°" + unit);
```
O Java "gruda" (concatena) três coisas:
- O número: `37.77777777777778`
- O texto do grau: `"°"`
- A letra da unidade: `"C"`

**O que sai no console:**
```text
Enter the temperature: 100
Convert to Celsius or Fahrenheit? (C or F): C
37.77777777777778°C
```

> **Olhe que curioso:** O número ficou feio com um monte de casas decimais (`.77777777777778`). No próximo passo, o autor vai usar o `printf("%.1f")` para deixar lindo: `37.8°C`!

---

## 🔍 5. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 25)

Abaixo está o código exatamente como está na tela do IntelliJ do Bro Code (Imagem 4):

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa o leitor de teclado.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 3:** `public class Main {`
- **O que faz:** A classe principal do programa.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** Método `main`, ponto de partida da execução.

### **Linha 5:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 6:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Cria o objeto `scanner` para capturar digitação.

### **Linha 7:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 8:** `        double temp;`
- **O que faz:** Gaveta para a temperatura digitada pelo usuário.

### **Linha 9:** `        double newTemp;`
- **O que faz:** Gaveta para a nova temperatura calculada.

### **Linha 10:** `        String unit;`
- **O que faz:** Gaveta de texto para a letra da unidade (`"C"` ou `"F"`).

### **Linha 11:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 12:** `        System.out.print("Enter the temperature: ");`
- **O que faz:** Pede a temperatura no console (cursor na mesma linha).

### **Linha 13:** `        temp = scanner.nextDouble();`
- **O que faz:** Lê a temperatura digitada (ex: `100.0`) e salva em `temp`.

### **Linha 14:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 15:** `        System.out.print("Convert to Celsius or Fahrenheit? (C or F): ");`
- **O que faz:** Pede para escolher a unidade (`C` ou `F`).

### **Linha 16:** `        unit = scanner.next().toUpperCase();`
- **O que faz:** Lê a letra digitada e força a virar MAIÚSCULA com `.toUpperCase()`.

### **Linha 17:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 18:** `        // (condition) ? true : false`
- **O que faz:** Comentário de lembrete da fórmula do operador ternário.

### **Linha 19:** `        newTemp = (unit.equals("C")) ? (temp - 32) * 5 / 9 : (temp * 5 / 9) + 32;`
- **O que faz:** Se `unit` for igual a `"C"`, calcula Celsius com `(temp - 32) * 5 / 9`. Senão, calcula Fahrenheit com `(temp * 9 / 5) + 32`. Salva o resultado em `newTemp`.

### **Linha 20:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 21:** `        System.out.println(newTemp + "°" + unit);`
- **O que faz:** Imprime o número da temperatura, gruda a bolinha de graus `"°"` e gruda a letra `"C"` ou `"F"`.

### **Linha 22:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 23:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado para economizar recursos.

### **Linha 24:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 25:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 6. Código Completo Pronto para Copiar e Testar

Aqui está o código completo com a correção matemática oficial aplicada na linha 19:

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
        newTemp = (unit.equals("C")) ? (temp - 32) * 5 / 9 : (temp * 9 / 5) + 32;

        System.out.println(newTemp + "°" + unit);

        scanner.close();
    }
}
```

> **Dica de teste:**
> 1. Digite `100` e escolha `c` $\rightarrow$ Você verá sair: `37.77777777777778°C` (o mesmo do vídeo!).
> 2. Digite `0` e escolha `f` $\rightarrow$ Você verá sair: `32.0°F` (ponto de congelamento da água)!
