# 🏋️ Capítulo 15: Conversor de Peso (`Weight Converter`) — Parte 1 (A Estrutura e o Menu)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. Boas-vindas ao Capítulo 15: O Mini-Projeto do Conversor de Peso!

Parabéns por chegar ao **Capítulo 15**!  
Aqui o professor começa mais um **mini-projeto prático completo**, juntando tudo o que você aprendeu:
* Criação de **Menus Interativos** no console.
* Leitura de teclado com **`Scanner`**.
* Variáveis numéricas com ponto flutuante (**`double`**) e inteiras (**`int`**).
* Tomada de decisões com **`if / else if / else`**.
* Formatação matemática elegante.

O objetivo do programa é simples e super útil:  
Converter medidas de peso entre o sistema americano (**Libras / `lbs`**) e o sistema brasileiro/métrico (**Quilos / `kgs`**).

---

## 🌎 2. O que são `lbs` e `kgs` na Vida Real?

* **Quilogramas (`kgs` ou quilos):** É a unidade que usamos no Brasil para pesar pessoas, arroz, feijão na academia, etc.
* **Libras (`lbs` ou *pounds*):** É a unidade usada nos Estados Unidos e na Inglaterra.
  * *(A abreviação `lbs` vem da palavra latina **libra**, que significa balança).*
  * $1\text{ kg}$ equivale a aproximadamente $2.20462\text{ lbs}$.

O nosso programa vai dar duas escolhas para o usuário:
1. **Opção 1:** O usuário digita o peso em Libras (`lbs`) $\rightarrow$ o Java converte para Quilos (`kgs`).
2. **Opção 2:** O usuário digita o peso em Quilos (`kgs`) $\rightarrow$ o Java converte para Libras (`lbs`).

---

## 🧠 3. A Técnica Profissional: Planejar com Comentários Antes de Programar!

Repare numa atitude genial do professor: antes de sair escrevendo contas matemáticas, ele primeiro escreveu um **roteiro em comentários** em inglês.  
Isso se chama **Pseudocódigo / Planejamento**:

```text
// 1. Mensagem de boas-vindas e exibição das opções do menu
// 2. Perguntar qual opção o usuário escolheu
// 3. Opção 1: converter lbs para kgs
// 4. Opção 2: converter kgs para lbs
// 5. Senão (else): avisar que a opção é inválida
```

Fazer esse rascunho antes é o segredo para você nunca se perder em programas maiores!

---

## 📦 4. As 3 Caixinhas de Memória Declaradas

Olhe as 3 variáveis que o programa precisa:

```java
double weight;
double newWeight;
int choice;
```

1. **`double weight;`**  
   * Guarda o peso original digitado pelo usuário.
   * É **`double`** porque peso tem vírgula/ponto e centésimos (ex: `70.5` kg ou `150.8` lbs).
2. **`double newWeight;`**  
   * Guarda o novo peso calculado após a conversão matemática.
   * Também é **`double`** porque o resultado da multiplicação ou divisão quase sempre gera números quebrados.
3. **`int choice;`**  
   * Guarda a escolha do usuário no menu (*choice* significa escolha/opção).
   * É **`int`** (número inteiro) porque as opções do menu são números redondos: ou a pessoa escolhe `1` ou escolhe `2`.

---

## 🔬 5. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar cada linha com a numeração exata do editor do professor:

* **Linha 1 (`import java.util.Scanner;`):**  
  Importa a ferramenta do Java necessária para ler o teclado.
* **Linha 3 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 4 (`public static void main(String[] args) {`):**  
  O ponto de partida do Java onde o programa começa a rodar.
* **Linha 6 (`// WEIGHT CONVERSION PROGRAM`):**  
  Comentário de título do programa: *"Programa de Conversão de Peso"*.
* **Linha 8 (`Scanner scanner = new Scanner(System.in);`):**  
  Cria o objeto `scanner` para escutar o teclado do computador.
* **Linha 10 (`double weight;`):**  
  Declara a variável que guardará o peso de entrada.
* **Linha 11 (`double newWeight;`):**  
  Declara a variável que guardará o peso convertido.
* **Linha 12 (`int choice;`):**  
  Declara a variável inteira que guardará a opção `1` ou `2`.
* **Linha 14 (`System.out.println("Weight Conversion Program");`):**  
  Imprime o cabeçalho do programa no topo do console.
* **Linha 15 (`System.out.println("1: Convert lbs to kgs");`):**  
  Mostra na tela a Opção 1: Converter libras para quilos.
* **Linha 16 (`System.out.println("2: Convert kgs to lbs");`):**  
  Mostra na tela a Opção 2: Converter quilos para libras.
* **Linha 18 (`System.out.print("Choose an option: ");`):**  
  Pergunta: *"Escolha uma opção: "*. Usou `print` (sem `ln`) para a pessoa digitar o número logo ao lado da frase.
* **Linha 19 (`choice = scanner.nextInt();`):**  
  Pausa o programa, lê o número inteiro digitado (`1` ou `2`) e guarda na variável `choice`.
* **Linhas 23 a 27 (`// option 1... // option 2... // else...`):**  
  Comentários de planejamento que serão preenchidos nas próximas etapas com as contas matemáticas!
* **Linha 28 (`}`):**  
  Fecha o método `main`.
* **Linha 29 (`}`):**  
  Fecha a classe `Main`.

---

## 🖥️ 6. O Que Aparece no Console Neste Momento

Quando você roda esse programa, o console exibe o menu limpo e organizado:

```text
Weight Conversion Program
1: Convert lbs to kgs
2: Convert kgs to lbs
Choose an option: _
```
O cursor fica piscando ao lado de `Choose an option: `, esperando o usuário digitar `1` ou `2` e apertar Enter!

---

## 💻 7. Código Completo Desta Etapa para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` para montar a base do seu conversor:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Capítulo 15: Conversor de Peso (Parte 1 - Menu e Escolha)

        Scanner scanner = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        // 1. Mensagem de boas-vindas e exibição do menu
        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");

        // 2. Pergunta a opção do usuário
        System.out.print("Choose an option: ");
        choice = scanner.nextInt();

        // Linhas de rascunho para os próximos passos:
        // option 1 convert lbs to kgs
        // option 2 convert kgs to lbs
        // else print not a valid choice

        scanner.close();
    }
}
```
