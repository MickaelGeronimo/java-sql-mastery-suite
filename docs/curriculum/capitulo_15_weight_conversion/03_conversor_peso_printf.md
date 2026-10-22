# 🏋️ Capítulo 15: Conversor de Peso — Parte 3 (O `printf` com `%.2f` na Prática)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Mudou Aqui?

Nestas cenas, o professor não adicionou novos passos lógicos. Ele fez algo muito melhor:  
**Ele consertou a estética do resultado usando o `printf` que aprendemos no Capítulo 10!**

Na etapa anterior, a linha de impressão estava assim:
```java
// Jeito antigo (com println):
System.out.println("The new weight in kgs is: " + newWeight);
// Saída feia: The new weight in kgs is: 68.0388
```
Aquele monte de casas decimais (`68.0388`) parecia feio e amador. Na balança da farmácia ou da academia, ninguém pesa "68.0388 kg", a gente diz "68.04 kg"!

O professor trocou essa linha por:
```java
// Jeito novo profissional (com printf):
System.out.printf("The new weight in kgs is: %.2f", newWeight);
```

---

## 🔬 2. A Mágica do `%.2f`: Como o Java Arredonda?

Vamos olhar para a pecinha **`%.2f`** com um microscópio:

```text
       ┌──> O símbolo de aviso: "Aqui vem uma formatação!"
       │
       % .2 f
         │  │
         │  └──> Tipo: Floating-point (número decimal com vírgula)
         │
         └─────> Precisão: Exiba APENAS 2 casas decimais!
```

### 🧮 O Arredondamento Inteligente:
* O valor exato na memória era: `68.0388...`
* Como pedimos `.2` (duas casas), o Java olha para o terceiro dígito (o número `8`).
* Pela regra da matemática mundial, se o próximo número for $5$ ou maior, ele arredonda para cima!
* O `03` vira **`04`**!
* O resultado na tela fica perfeito:
  ```text
  The new weight in kgs is: 68.04
  ```

---

## 🔬 3. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar linha por linha, com a numeração exata do editor do professor:

* **Linhas 1 a 4:** A estrutura padrão do Java (`import java.util.Scanner;`, `public class Main {` e `public static void main`).
* **Linha 8 (`Scanner scanner = new Scanner(System.in);`):** Prepara o leitor de teclado.
* **Linha 10 (`double weight;`):** Caixinha para o peso digitado.
* **Linha 11 (`double newWeight;`):** Caixinha para o peso convertido.
* **Linha 12 (`int choice;`):** Caixinha para a opção `1` ou `2`.
* **Linha 14 (`System.out.println("Weight Conversion Program");`):** Imprime o título no console.
* **Linha 15 (`System.out.println("1: Convert lbs to kgs");`):** Opção 1 no menu.
* **Linha 16 (`System.out.println("2: Convert kgs to lbs");`):** Opção 2 no menu.
* **Linha 18 (`System.out.print("Choose an option: ");`):** Pede a opção.
* **Linha 19 (`choice = scanner.nextInt();`):** Lê o número digitado (`1`).
* **Linha 21 (`if(choice == 1){`):** Se a escolha for igual a 1, entra no bloco.
* **Linha 22 (`System.out.print("Enter the weight in lbs: ");`):** Pede o peso em libras.
* **Linha 23 (`weight = scanner.nextDouble();`):** Lê o peso digitado (ex: `150`).
* **Linha 24 (`newWeight = weight * 0.453592;`):** Multiplica por `0.453592` para converter em quilos.
* **Linha 25 (`System.out.printf("The new weight in kgs is: %.2f", newWeight);`):**  
  **A Linha Aprimorada:** Imprime a frase formatando o peso para exatamente **duas casas decimais** com `%.2f`!
* **Linha 26 (`}`):** Fecha o bloco do `if`.
* **Linha 28 (`// option 2 convert kgs to lbs`):** Rascunho da Opção 2.
* **Linha 30 (`// else print not a valid choice`):** Rascunho do `else`.
* **Linhas 31 a 33 (`}` e `}`):** Fechamento das chaves.

---

## 📊 4. Comparativo: Antes vs. Depois na Linha 25

| Critério | Antes (com `println`) | Depois (com `printf`) |
| :--- | :--- | :--- |
| **Linha de Código** | `System.out.println("The new weight in kgs is: " + newWeight);` | `System.out.printf("The new weight in kgs is: %.2f", newWeight);` |
| **Controle de Casas** | Nenhum (mostra todos os decimais) | Total (`.2` limita a 2 casas) |
| **Resultado no Console** | `The new weight in kgs is: 68.0388` | `The new weight in kgs is: 68.04` |
| **Qualidade Visual** | Amadora | Profissional |

---

## 💻 5. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste com o valor de `150` libras:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Capítulo 15: Conversor de Peso (Parte 3 - printf com %.2f)

        Scanner scanner = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");

        System.out.print("Choose an option: ");
        choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter the weight in lbs: ");
            weight = scanner.nextDouble();
            newWeight = weight * 0.453592;
            
            // Impressão profissional formatada com %.2f
            System.out.printf("The new weight in kgs is: %.2f", newWeight);
        }

        scanner.close();
    }
}
```
