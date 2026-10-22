# 🏋️ Capítulo 15: Conversor de Peso (Weight Converter) — Parte 4: Opção 2 (Quilos para Libras)

---

## 📌 1. Visão Geral: O Que o Autor Fez Aqui?

Na aula anterior, o programa só sabia fazer **uma coisa**: se você escolhesse a **Opção 1**, ele convertia Libras (*lbs*) para Quilos (*kgs*).

Mas se o usuário digitasse `2`, o programa não fazia nada e terminava na hora!

Neste trecho do vídeo (marca **2:33:27** até **2:33:52**), o autor:
1. **Aproveitou o código pronto:** Em vez de digitar tudo do zero, ele copiou o bloco do `if (choice == 1)` e colou logo abaixo.
2. **Ajustou a condição para a Opção 2:** Adicionou `else if (choice == 2)`.
3. **Inverteu a lógica de conversão:**
   - Agora pede o peso em **quilos** (`kgs`).
   - Usa o número mágico da conversão reversa: multiplicar por **`2.20462`**.
   - Mostra o resultado final em **libras** (`lbs`), formatado bonitinho com duas casas decimais (`%.2f`).
4. **Fez o teste prático:** Digitou `2` no menu, colocou `68` quilos e o programa respondeu perfeitamente: `The new weight in lbs is: 149.91`!

---

## 💡 2. A Curiosidade do Vídeo: O "Copia e Cola" e o Aviso do IntelliJ

Na primeira imagem (**2:33:27**), você viu que o autor colou o código e apareceu uma linha amarela ondulada embaixo de `(choice == 1)`:

```java
else if(choice == 1){ // ⚠️ O IntelliJ avisou aqui!
```

### Por que o IntelliJ deu esse aviso amarelo?
O IntelliJ é muito esperto. Ele pensou:
> *"Ei, programador! Lá em cima na linha 21 você já testou se `choice == 1`. Se o Java chegou neste `else if`, é óbvio que `choice` NÃO É 1! Por que você está testando `choice == 1` de novo? Isso nunca vai acontecer!"*

Isso é chamado de **código redundante** ou **condição duplicada**. O autor só tinha colado rápido e logo em seguida corrigiu para `choice == 2`, e a cobrinha amarela sumiu na hora!

---

## 🧮 3. A Matemática da Conversão: Por Que Multiplicar por 2.20462?

Para não parecer mágica, veja como funciona no mundo real:

| Direção da Conversão | Regra Prática | Fator Multiplicador | Exemplo Real |
| :--- | :--- | :--- | :--- |
| **Opção 1:** Libras $\rightarrow$ Quilos | Cada libra tem quase meio quilo ($0,453\text{ kg}$) | Multiplica por `0.453592` | $150\text{ lbs} \times 0.453592 = 68.04\text{ kg}$ |
| **Opção 2:** Quilos $\rightarrow$ Libras | Cada quilo equivale a pouco mais de 2 libras ($2,204\text{ lbs}$) | Multiplica por `2.20462` | $68\text{ kg} \times 2.20462 = 149.91\text{ lbs}$ |

### A Prova Real do Teste do Vídeo:
No teste da terceira imagem:
1. O usuário escolheu a opção `2` (converter quilos para libras).
2. O usuário digitou `68` (quilos).
3. A conta que o processador fez foi:
   $$68 \times 2.20462 = 149.91416$$
4. Como o comando `printf` usou `%.2f`, ele cortou para **2 casas decimais**:
   $$149.91416 \rightarrow \mathbf{149.91}$$
5. Veja como faz todo sentido: na aula passada, $150\text{ lbs}$ deram $68.04\text{ kg}$. Agora, $68\text{ kg}$ voltaram para $149.91\text{ lbs}$ (praticamente os mesmos $150\text{ lbs}$)!

---

## 🔍 4. Explicação Linha por Linha (Toda Linha Enumerada)

Abaixo está o código exatamente como visto na tela do Bro Code, linha por linha, com explicação mastigada:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Avisa ao Java que vamos usar a ferramenta `Scanner` (o teclado do usuário). Sem essa linha, o Java não sabe como ler o que digitamos.

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Linha vazia para deixar o código limpo e organizado.

### **Linha 3:** `public class Main {`
- **O que faz:** É a casa principal do nosso programa. Em Java, todo código precisa morar dentro de uma classe (`class`).

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** A porta de entrada do programa. Quando você clica no botão verde de Play, o Java começa a ler a partir daqui.

### **Linha 5:** ` ` *(em branco)*
- **O que faz:** Espaço visual para separar as seções.

### **Linha 6:** `        // WEIGHT CONVERSION PROGRAM`
- **O que faz:** Um comentário. Serve apenas como um bilhete para o programador saber do que se trata o projeto. O computador ignora.

### **Linha 7:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 8:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Liga o leitor de teclado. Criamos um objeto chamado `scanner` pronto para capturar as respostas do usuário.

### **Linha 9:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 10:** `        double weight;`
- **O que faz:** Cria a gaveta na memória chamada `weight` (peso digitado). É do tipo `double` para aceitar números com vírgula/ponto (como `68.5`).

### **Linha 11:** `        double newWeight;`
- **O que faz:** Cria a gaveta chamada `newWeight` (novo peso convertido). Também do tipo `double`.

### **Linha 12:** `        int choice;`
- **O que faz:** Cria a gaveta chamada `choice` (escolha). É do tipo `int` porque a opção do menu é um número inteiro (`1` ou `2`).

### **Linha 13:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 14:** `        System.out.println("Weight Conversion Program");`
- **O que faz:** Imprime o título na tela e pula de linha: `"Weight Conversion Program"`.

### **Linha 15:** `        System.out.println("1: Convert lbs to kgs");`
- **O que faz:** Mostra a Opção 1 no menu e pula de linha.

### **Linha 16:** `        System.out.println("2: Convert kgs to lbs");`
- **O que faz:** Mostra a Opção 2 no menu e pula de linha.

### **Linha 17:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 18:** `        System.out.print("Choose an option: ");`
- **O que faz:** Mostra `"Choose an option: "` na tela. Usa `print` (sem o `ln`) para o cursor ficar piscando bem ao lado do texto.

### **Linha 19:** `        choice = scanner.nextInt();`
- **O que faz:** Pausa e espera o usuário digitar `1` ou `2`. Guarda esse número dentro da gaveta `choice`.

### **Linha 20:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 21:** `        if(choice == 1){`
- **O que faz:** Pergunta: *"O usuário escolheu a opção 1?"*. Se sim, entra nas chaves `{ }` e executa as linhas 22 a 25. Se não, pula para o `else if`.

### **Linha 22:** `            System.out.print("Enter the weight in lbs: ");`
- **O que faz:** Pede o peso em libras para o usuário.

### **Linha 23:** `            weight = scanner.nextDouble();`
- **O que faz:** Lê o peso que o usuário digitou e guarda na gaveta `weight`.

### **Linha 24:** `            newWeight = weight * 0.453592;`
- **O que faz:** Calcula o peso em quilos multiplicando por `0.453592` e guarda em `newWeight`.

### **Linha 25:** `            System.out.printf("The new weight in kgs is: %.2f", newWeight);`
- **O que faz:** Imprime a frase formatada com `printf`. Onde tem `%.2f`, ele encaixa o `newWeight` arredondado para exatamente 2 casas decimais.

### **Linha 26:** `        }`
- **O que faz:** Fecha o bloco da Opção 1.

---

### ⭐ As Linhas Novas Adicionadas Agora (Opção 2):

### **Linha 27:** `        else if(choice == 2){`
- **O que faz:** Se o usuário NÃO escolheu 1, o Java vem para cá e pergunta: *"E então, a escolha foi igual a 2?"* (`choice == 2`). Se o usuário digitou 2, ele entra neste novo bloco!

### **Linha 28:** `            System.out.print("Enter the weight in kgs: ");`
- **O que faz:** Exibe a mensagem pedindo o peso em **quilos** (`kgs`), deixando o cursor na mesma linha esperando a digitação.

### **Linha 29:** `            weight = scanner.nextDouble();`
- **O que faz:** O programa fica esperando o usuário digitar o valor em quilos (no teste do vídeo, digitou `68`) e salva esse número na gaveta `weight`.

### **Linha 30:** `            newWeight = weight * 2.20462;`
- **O que faz:** Faz o cálculo reverso! Pega os quilos digitados (`68`) e multiplica pelo fator `2.20462`. O resultado ($149.91416$) é guardado na gaveta `newWeight`.

### **Linha 31:** `            System.out.printf("The new weight in lbs is: %.2f", newWeight);`
- **O que faz:** Exibe o resultado na tela usando `printf`.
  - A máscara `%.2f` pega o número $149.91416$ e formata como `149.91`.
  - O texto exibido no console fica: `The new weight in lbs is: 149.91`.

### **Linha 32:** `        }`
- **O que faz:** Fecha o bloco de chaves da Opção 2.

### **Linha 33:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 34:** `        // option 2 convert kgs to lbs`
- **O que faz:** Um comentário antigo que o autor tinha colocado como lembrete do que fazer. Agora que ele já fez a Opção 2, esse comentário pode ser apagado.

### **Linha 35:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 36:** `        // else print not a valid choice`
- **O que faz:** Outro comentário de planejamento. Avisa o que será feito a seguir: se o usuário digitar qualquer número diferente de 1 e 2 (como 3, 99 ou -5), o programa deve avisar que a opção é inválida!

### **Linha 37:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 38:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 5. Código Completo Pronto para Copiar e Testar

Você pode copiar todo o código abaixo para o seu arquivo `Main.java` e rodar:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // WEIGHT CONVERSION PROGRAM

        Scanner scanner = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");

        System.out.print("Choose an option: ");
        choice = scanner.nextInt();

        if(choice == 1){
            System.out.print("Enter the weight in lbs: ");
            weight = scanner.nextDouble();
            newWeight = weight * 0.453592;
            System.out.printf("The new weight in kgs is: %.2f\n", newWeight);
        }
        else if(choice == 2){
            System.out.print("Enter the weight in kgs: ");
            weight = scanner.nextDouble();
            newWeight = weight * 2.20462;
            System.out.printf("The new weight in lbs is: %.2f\n", newWeight);
        }

        scanner.close();
    }
}
```

---

## 🎯 6. Resumo das Diferenças: Opção 1 vs Opção 2

| Característica | Opção 1 (`choice == 1`) | Opção 2 (`choice == 2`) |
| :--- | :--- | :--- |
| **O que o usuário digita** | Peso em Libras (`lbs`) | Peso em Quilos (`kgs`) |
| **Pergunta na tela** | `"Enter the weight in lbs: "` | `"Enter the weight in kgs: "` |
| **Multiplicador matemático** | `* 0.453592` | `* 2.20462` |
| **Texto da resposta** | `"The new weight in kgs is: "` | `"The new weight in lbs is: "` |
| **Resultado formatado** | `%.2f` (2 casas decimais) | `%.2f` (2 casas decimais) |
