# 📐 Mini-Projeto: Calculadora de Hipotenusa (Teorema de Pitágoras)!

Nestas 4 imagens, o Bro Code começou um **projeto prático incrível**: criar um programa que calcula o tamanho da **hipotenusa de um triângulo**!

Ele está juntando **duas coisas gigantescas** que aprendemos no curso:
1. O **`Scanner`** (para o usuário digitar os lados do triângulo no teclado).
2. A classe **`Math`** (para calcular potências e raiz quadrada).

---

## 📋 1. Código Atual da Aula (Pronto para copiar)

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

        System.out.println(a);
        System.out.println(b);

        scanner.close();
    }
}
```

---

## 🔺 2. O que é Hipotenusa? (Para quem odiava matemática na escola!)

Olhe a **Imagem 2** com o desenho do triângulo retângulo:

```
               |\
               | \
        Lado A |  \  LADO C (HIPOTENUSA)
       (Parede)|   \ (A rampa inclinada!)
               |    \
               |_____ \
               Lado B
               (O Chão)
```

* **Lado A:** É a altura (a parede em pé).
* **Lado B:** É a base (o chão reto).
* **Lado C (Hipotenusa):** É a **rampa inclinada** que liga a ponta da parede até o chão!

---

## 📜 3. A Famosa Fórmula de Pitágoras

Na Imagem 2, aparece a fórmula matemática:
$$\mathbf{c = \sqrt{a^2 + b^2}}$$

### O que essa fórmula diz em português simples?
1. Pegue o Lado A e multiplique por ele mesmo ($a^2$).
2. Pegue o Lado B e multiplique por ele mesmo ($b^2$).
3. Some os dois resultados.
4. Tire a **raiz quadrada** dessa soma! O resultado é o tamanho da rampa ($c$)!

---

## 🔍 4. O Que o Autor Fez Nesta Etapa?

O autor é muito inteligente: antes de fazer o cálculo da matemática, ele quis **testar se o teclado estava funcionando direito**:

1. **Linha 1:** Trouxe o `Scanner` (`import java.util.Scanner;`).
2. **Linha 7:** Ligou o leitor de teclado (`new Scanner(System.in)`).
3. **Linhas 9-11:** Criou 3 caixinhas decimais: `double a`, `double b`, `double c`.
4. **Linhas 13-14:** Perguntou o tamanho do Lado A e leu com `scanner.nextDouble()`.
5. **Linhas 16-17:** Perguntou o tamanho do Lado B e leu com `scanner.nextDouble()`.
6. **Linhas 19-20:** Imprimiu `a` e `b` na tela para conferir se os números foram salvos!

---

## 🖥️ 5. O Teste no Console (Imagens 3 e 4):

No console, o autor fez o teste do **triângulo mais famoso da história** (o triângulo 3, 4 e 5):

```
Enter the length of side A: 3 ⏎
Enter the length of side B: 4 ⏎

3.0
4.0
```

* O Lado A guardou **`3.0`**.
* O Lado B guardou **`4.0`**.

---

## 🔮 O que vem nos próximos segundos?

Agora que ele já tem os dois lados guardados com sucesso, ele vai usar o `Math.pow()` e o `Math.sqrt()` para calcular o valor de **`c`**!

E adivinhe quanto vai dar a hipotenusa de 3 e 4?
* $3^2 = 9$
* $4^2 = 16$
* $9 + 16 = 25$
* $\sqrt{25} = \mathbf{5.0}$!

Vamos ver ele programar essa conta na próxima tela! 🚀
