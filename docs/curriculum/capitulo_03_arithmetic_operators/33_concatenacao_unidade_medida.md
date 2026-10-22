# 📏 O Trenzinho da Concatenação: Adicionando Unidade de Medida (`+ "cm"`)!

Nesta imagem, o Bro Code fez um ajuste de acabamento que todo bom programador faz: **ele colocou a unidade de medida (`cm`) no final do resultado**!

---

## 📋 1. Código Completo com a Unidade (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // HYPOTENUSE c = Math.sqrt(a² + b²)

        Scanner scanner = new Scanner(System.in);

        double a;
        double b;
        double c;

        System.out.print("Enter the length of side A: ");
        a = scanner.nextDouble();

        System.out.print("Enter the length of side B: ");
        b = scanner.nextDouble();

        c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        System.out.println("The hypotenuse (side c) is: " + c + "cm");

        scanner.close();
    }
}
```

---

## 🚂 2. O Trenzinho da Linha 22 (A Grande Mudança!)

Olhe a linha 22 antes e depois:

```
Antes:  System.out.println("The hypotenuse (side c) is: " + c);
Depois: System.out.println("The hypotenuse (side c) is: " + c + "cm");
                                                              ▲
                                                    Adicionou esse vagão!
```

### A Analogia do Trem de 3 Vagões:
O sinal de **`+`** no Java funciona como o engate de um trem. Agora temos **3 vagões colados um no outro**:

```
 ┌────────────────────────────────┐     ┌─────────┐     ┌────────┐
 │ "The hypotenuse (side c) is: " │  +  │    c    │  +  │  "cm"  │
 └────────────────────────────────┘     └─────────┘     └────────┘
             Vagão 1                      Vagão 2         Vagão 3
           (Texto inicial)              (Número 5.0)     (Unidade)
```

O Java engata os três e forma uma frase só:
> **`The hypotenuse (side c) is: 5.0cm`**

---

## 🤔 3. Por que isso é tão importante na vida real?

Se o seu programa mostrasse apenas:
```
The hypotenuse (side c) is: 5.0
```
Qualquer pessoa perguntaria:
> *"5.0 o quê? 5 metros? 5 quilômetros? 5 bananas?"* 🍌😂

Ao colocar `+ "cm"`, o programa fica **profissional, claro e sem dúvidas**!

---

## 💡 4. Dica de Espaço: Colado ou Separado?

Olhe como o autor escreveu:
* Com `+ "cm"` $\rightarrow$ O texto fica grudadinho no número: **`5.0cm`**
* Se ele quisesse um espacinho no meio, bastaria colocar um espaço dentro das aspas:
  `+ " cm"` $\rightarrow$ Ficaria: **`5.0 cm`**

Os dois jeitos estão certos, vai do seu gosto visual! 🎨
