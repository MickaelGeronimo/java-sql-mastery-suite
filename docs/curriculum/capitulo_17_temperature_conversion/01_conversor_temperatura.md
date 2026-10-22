# 🌡️ Capítulo 17: Conversor de Temperatura (Temperature Converter) — Parte 1: Estrutura, Entradas e o Truque do `.toUpperCase()`

---

## 📌 1. Visão Geral: O Que Está Acontecendo Aqui?

Bem-vindo ao **Capítulo 17: Conversor de Temperatura** (*Temperature Converter*)!

Neste capítulo, o autor une **tudo o que você aprendeu até agora**:
- Leitura com `Scanner` (`nextDouble()` e `next()`).
- O **Operador Ternário** (`? :`) para escolher a fórmula matemática certa.
- Manipulação de texto com **`.toUpperCase()`** e **`.equals()`**.
- Formatação bonita com **`printf`** e o símbolo de graus `°`!

### A Ordem dos 4 Prints do Vídeo:
1. **Imagem 1 (2:41:18) — O Trailer / Demonstração do Projeto:**  
   O autor mostra o programa pronto funcionando: você digita `30.1`, escolhe converter para Fahrenheit `f` e ele responde `48.7°F`!
2. **Imagem 2 (2:43:48) — Começando do Zero:**  
   O autor apaga tudo e começa a construir a base: cria as variáveis `temp`, `newTemp`, `unit` e as perguntas na tela.
3. **Imagem 3 (2:44:08) — Colocando Testes:**  
   Adiciona `System.out.println(temp);` e `System.out.println(unit);` para checar se o Java está lendo os dados direito.
4. **Imagem 4 (2:44:48) — O Truque de Mestre:**  
   Ele adiciona **`.toUpperCase()`** na leitura da unidade (`unit = scanner.next().toUpperCase();`) para que, mesmo se o usuário digitar `c` ou `f` minúsculo, o Java converta na hora para maiúsculo!

---

## 💡 2. O Grande Truque da Imagem 4: Por Que Usar `.toUpperCase()`?

Olhe bem para a Imagem 4:
```java
unit = scanner.next().toUpperCase();
```

### O Problema do Mundo Real:
O programa pede: `"Convert to Celsius or Fahrenheit? (C or F): "`.  
O ser humano é imprevisível:
- Algumas pessoas vão digitar `C` maiúsculo.
- Outras pessoas vão digitar `c` minúsculo.

Em Java, `"C"` maiúsculo e `"c"` minúsculo **são completamente diferentes**! Se o nosso código esperar `"C"` e o usuário digitar `"c"`, o teste `unit.equals("C")` daria **FALSO** e faria a conta errada!

### A Solução:
Colocar `.toUpperCase()` logo após `scanner.next()` faz com que:
- Se a pessoa digitar `"c"`, o Java transforma em `"C"`.
- Se a pessoa digitar `"f"`, o Java transforma em `"F"`.
- Se a pessoa já digitar maiúsculo, continua maiúsculo!

É uma **blindagem inteligente** para o programa nunca dar erro por causa de letra maiúscula ou minúscula!

---

## 🖥️ 3. O Que Sai no Console em Cada Imagem? (Conforme Você Pediu)

### 🟢 Na Imagem 4 (Teste das Entradas com `c` minúsculo):
1. O programa pergunta a temperatura:
   `Enter the temperature: 75` (o autor digitou `75`).
2. O programa pergunta para qual unidade converter:
   `Convert to Celsius or Fahrenheit? (C or F): c` (o autor digitou `c` minúsculo).
3. Linha 18 imprime `temp`:
   `75.0` (aparece com `.0` porque `temp` é do tipo `double`).
4. Linha 19 imprime `unit`:
   `C` (repare que saiu maiúsculo no console, mesmo o autor tendo digitado `c` minúsculo!).

**Resultado impresso no console da Imagem 4:**
```text
Enter the temperature: 75
Convert to Celsius or Fahrenheit? (C or F): c
75.0
C
```

---

### 🔮 Na Imagem 1 (O Trailer do Programa Final):
Na demonstração inicial, o autor testou o programa completo:
1. Digitou a temperatura: `30.1`.
2. Digitou a unidade: `f` (minúsculo).
3. O programa calculou a fórmula de Fahrenheit e imprimiu:
```text
48.7°F
```

---

## 🔍 4. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 23)

Abaixo está o código exatamente como visto na tela do Bro Code (Imagem 4):

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa a ferramenta do teclado para podermos ler o que o usuário digita.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Organização visual.

### **Linha 3:** `public class Main {`
- **O que faz:** Cria a classe principal `Main`.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** Ponto de ignição onde o Java começa a rodar.

### **Linha 5:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 6:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Liga o leitor de teclado com o nome `scanner`.

### **Linha 7:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 8:** `        double temp;`
- **O que faz:** Cria a gaveta decimal `temp` (temperatura que o usuário vai digitar, ex: `75.0` ou `30.1`).

### **Linha 9:** `        double newTemp;`
- **O que faz:** Cria a gaveta decimal `newTemp` (onde vamos guardar a nova temperatura já convertida).

### **Linha 10:** `        String unit;`
- **O que faz:** Cria a gaveta de texto `unit` (unidade escolhida: `"C"` ou `"F"`).

### **Linha 11:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 12:** `        System.out.print("Enter the temperature: ");`
- **O que faz:** Pede a temperatura na tela, deixando o cursor na mesma linha esperando a digitação.

### **Linha 13:** `        temp = scanner.nextDouble();`
- **O que faz:** Lê o número digitado pelo usuário (ex: `75`) e guarda na variável `temp`.

### **Linha 14:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 15:** `        System.out.print("Convert to Celsius or Fahrenheit? (C or F): ");`
- **O que faz:** Pede para o usuário escolher a unidade de destino: `"C"` ou `"F"`.

### **Linha 16:** `        unit = scanner.next().toUpperCase();`
- **O que faz:** **A linha estrela!** Lê a palavra/letra digitada pelo usuário e chama imediatamente `.toUpperCase()` para forçar a letra a virar maiúscula antes de guardar em `unit`.

### **Linha 17:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 18:** `        System.out.println(temp);`
- **O que faz:** Linha de teste temporária: imprime o valor de `temp` no console (`75.0`).

### **Linha 19:** `        System.out.println(unit);`
- **O que faz:** Linha de teste temporária: imprime a unidade em maiúsculo no console (`C`).

### **Linha 20:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 21:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado para economizar recursos da máquina.

### **Linha 22:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 23:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 5. Código Completo para Você Copiar e Testar

Copie o código abaixo no seu IntelliJ para testar as entradas e ver a mágica do `.toUpperCase()` funcionando:

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

        System.out.println(temp);
        System.out.println(unit);

        scanner.close();
    }
}
```

> **Dica de teste:** Quando o programa perguntar `(C or F):`, digite a letra `c` minúscula ou `f` minúscula. Você vai ver que na linha de baixo o Java imprime `C` ou `F` em maiúsculo com perfeição!
