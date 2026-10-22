# 🍕 Novo Mini-Projeto: Calculadora de Círculos (Circunferência com `Math.PI`)!

Depois de calcular a hipotenusa, o Bro Code subiu de nível! Agora ele começou um projeto para calcular **tudo sobre círculos e esferas** usando o **`Math.PI`**!

Nesta primeira etapa, ele programou o cálculo da **Circunferência** (o contorno do círculo)!

---

## 📋 1. Código Completo Desta Etapa (Pronto para copiar)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // circumference = 2 * Math.PI * radius;
        // area = Math.PI * Math.pow(radius, 2);
        // volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);

        Scanner scanner = new Scanner(System.in);

        double radius;
        double circumference;
        double area;
        double volume;

        System.out.print("Enter the radius: ");
        radius = scanner.nextDouble();

        circumference = 2 * Math.PI * radius;

        System.out.println("The circumference is: " + circumference + "cm");

        scanner.close();
    }
}
```

---

## 🍕 2. O que é Raio e Circunferência? (A Analogia da Pizza)

Para quem não lembra das aulas de geometria da escola:

```
                   ╭──────────────╮
                ╭──               ──╮
              ╭                       ╮
             │            RAIO         │
             │        •─────────►      │  👉 RAIO (radius): A distância do miolo
              ╰                       ╯     até a borda da pizza!
                ╰──               ──╯
                   ╰──────────────╯
                          ▲
                          │
             👉 CIRCUNFERÊNCIA (circumference):
             O comprimento de toda a borda recheada em volta da pizza!
```

* **Raio (`radius`):** É a distância do centro até a beirada.
* **Circunferência (`circumference`):** É se você pegasse uma fita métrica e desse uma volta completa em torno da pizza!

---

## 📜 3. As 3 Fórmulas Mágicas anotadas nas Linhas 6 a 8:

Olhe os comentários que o autor deixou no topo:

```java
// circumference = 2 * Math.PI * radius;
// area = Math.PI * Math.pow(radius, 2);
// volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
```

1. **Circunferência:** $2 \times \pi \times \text{raio}$ (a volta em torno do círculo).
2. **Área:** $\pi \times \text{raio}^2$ (o recheio que cobre a pizza).
3. **Volume:** $\frac{4}{3} \times \pi \times \text{raio}^3$ (o espaço de uma bola de futebol 3D).

> 🧠 **Dica de Ouro:** Repare que na fórmula do volume ele escreveu `(4.0 / 3.0)` com ponto zero!
> Lembra da aula de operadores? Se ele escrevesse `(4 / 3)` com números inteiros, o Java jogaria os decimais fora e daria `1`! Colocando `4.0 / 3.0`, o Java calcula `1.333...` perfeitamente!

---

## 🔍 4. O Que o Autor Fez Nesta Etapa?

1. **Linhas 12 a 15:** Declarou as **4 variáveis** que vamos usar no projeto:
   * `double radius;` (o raio que a pessoa vai digitar)
   * `double circumference;` (o contorno)
   * `double area;` (o tamanho da superfície)
   * `double volume;` (o tamanho da bola 3D)

2. **Linhas 17 e 18:** Perguntou o raio:
   ```java
   System.out.print("Enter the radius: ");
   radius = scanner.nextDouble();
   ```

3. **Linha 20:** Fez o cálculo usando o `Math.PI`:
   ```java
   circumference = 2 * Math.PI * radius;
   ```
   * O Java multiplica: $2 \times 3.14159265... \times \text{raio}$!

4. **Linha 22:** Mostrou o resultado formatado com `"cm"`:
   ```java
   System.out.println("The circumference is: " + circumference + "cm");
   ```

---

## 🔮 O que vem a seguir?

O autor já deixou o espaço preparado para calcular a **Área** e o **Volume** nas próximas linhas! Vamos ver o `Math.pow()` entrar em ação junto com o `Math.PI`! 🚀
