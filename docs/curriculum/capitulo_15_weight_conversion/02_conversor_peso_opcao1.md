# 🏋️ Capítulo 15: Conversor de Peso — Parte 2 (Opção 1: De Libras para Quilos)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Implementou Aqui?

Na etapa anterior, nós montamos o menu e perguntamos qual opção o usuário queria (`choice`).  
Agora, o professor programou o coração da **Opção 1**:
> *"Se o usuário escolheu 1, peça o peso em Libras (`lbs`) e converta para Quilos (`kgs`)!"*

---

## ⚖️ 2. A Matemática da Conversão: De Onde Saiu o Número `0.453592`?

No sistema de medidas internacional:
$$1\text{ libra (lb)} = 0.45359237\text{ quilogramas (kg)}$$

Ou seja, uma libra pesa **quase meio quilo** (cerca de 453 gramas).  
Para transformar qualquer peso em libras para quilos, a regra matemática é simples:
$$\text{Peso em Quilos} = \text{Peso em Libras} \times 0.453592$$

### 🧮 O Exemplo do Teste:
O professor digitou **150 libras**:
$$\text{newWeight} = 150 \times 0.453592 = \mathbf{68.0388\text{ kg}}$$
*(Uma pessoa de 150 libras pesa cerca de 68 kg!)*

---

## 🔬 3. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar linha por linha, com a numeração exata do editor do professor:

* **Linha 1 (`import java.util.Scanner;`):**  
  Traz o leitor de teclado para o nosso arquivo.
* **Linha 3 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 4 (`public static void main(String[] args) {`):**  
  O ponto de partida do Java onde a execução começa.
* **Linha 8 (`Scanner scanner = new Scanner(System.in);`):**  
  Prepara o leitor do teclado.
* **Linha 10 (`double weight;`):**  
  Caixinha para guardar o peso que o usuário vai digitar.
* **Linha 11 (`double newWeight;`):**  
  Caixinha para guardar o peso convertido.
* **Linha 12 (`int choice;`):**  
  Caixinha inteira para guardar a escolha do menu (`1` ou `2`).
* **Linha 14 (`System.out.println("Weight Conversion Program");`):**  
  Imprime o cabeçalho do programa no topo do console.
* **Linha 15 (`System.out.println("1: Convert lbs to kgs");`):**  
  Imprime a Opção 1: Converter de lbs para kgs.
* **Linha 16 (`System.out.println("2: Convert kgs to lbs");`):**  
  Imprime a Opção 2: Converter de kgs para lbs.
* **Linha 18 (`System.out.print("Choose an option: ");`):**  
  Pergunta qual opção o usuário deseja.
* **Linha 19 (`choice = scanner.nextInt();`):**  
  Lê o número digitado (`1`) e guarda na variável `choice`.
* **Linha 21 (`if(choice == 1){`):**  
  **A Decisão:** O Java pergunta: *"A escolha do usuário foi igual a 1?"* (`choice == 1`).  
  Como o usuário digitou `1`, a condição é **verdadeira (`true`)** e o Java entra dentro deste bloco!
* **Linha 22 (`System.out.print("Enter the weight in lbs: ");`):**  
  Pede o peso: *"Digite o peso em libras: "*.
* **Linha 23 (`weight = scanner.nextDouble();`):**  
  Lê o número decimal digitado (o professor digitou `150`) e guarda na variável `weight`.
* **Linha 24 (`newWeight = weight * 0.453592;`):**  
  **O Cálculo de Conversão:** Multiplica o peso por `0.453592` e guarda o resultado (`68.0388`) na variável `newWeight`.
* **Linha 25 (`System.out.println("The new weight in kgs is: " + newWeight);`):**  
  Imprime a frase de resultado com o peso convertido em quilos.
* **Linha 26 (`}`):**  
  Fecha o bloco do `if`.
* **Linha 28 (`// option 2 convert kgs to lbs`):**  
  Rascunho da Opção 2 (será feita no próximo passo com `else if`).
* **Linha 30 (`// else print not a valid choice`):**  
  Rascunho para tratar escolhas inválidas (será feito com `else`).
* **Linha 31 (`scanner.close();`):**  
  Fecha o scanner.
* **Linhas 32 e 33 (`}` e `}`):**  
  Fecham o método `main` e a classe `Main`.

---

## 🖥️ 4. O Teste Real no Console

Veja exatamente como o console interagiu com o usuário:

```text
Weight Conversion Program
1: Convert lbs to kgs
2: Convert kgs to lbs
Choose an option: 1
Enter the weight in lbs: 150
The new weight in kgs is: 68.0388
```

O programa fluiu como uma conversa natural:
1. Mostrou o menu.
2. O usuário escolheu `1`.
3. O programa pediu o peso em libras (`150`).
4. O programa calculou e entregou o resultado em quilos (`68.0388 kg`)!

---

## 💻 5. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste digitar a opção `1` e depois qualquer peso em libras:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Capítulo 15: Conversor de Peso (Parte 2 - Opção 1: lbs para kgs)

        Scanner scanner = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        // Menu de opções
        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");

        // Escolha da opção
        System.out.print("Choose an option: ");
        choice = scanner.nextInt();

        // OPÇÃO 1: Converter de lbs para kgs
        if (choice == 1) {
            System.out.print("Enter the weight in lbs: ");
            weight = scanner.nextDouble();
            newWeight = weight * 0.453592;
            System.out.println("The new weight in kgs is: " + newWeight);
        }

        // Os próximos passos serão adicionados a seguir:
        // option 2 convert kgs to lbs
        // else print not a valid choice

        scanner.close();
    }
}
```
