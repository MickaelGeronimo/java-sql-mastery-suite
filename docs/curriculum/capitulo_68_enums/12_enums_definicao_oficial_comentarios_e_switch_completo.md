# Curso de Java — Bro Code (12 Horas)
## Capítulo 68: `enums 📅` (Parte 11 — A Placa de Ouro: Definição Oficial, os 3 Pilares dos Enums e o Switch Completo)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa e minuciosa das **2 telas** exibidas pelo instrutor Bro Code no fechamento do **Capítulo 68** (timestamps em torno de **11:12:00** e **11:12:15** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: os códigos de `Main.java`, as seleções do cursor em `MONDAY` e `Enums`, a lâmpada amarela de inspeção do IntelliJ IDEA e a explicação linha por linha foram desenhados em formato de texto para que sua apostila fique 100% autossuficiente sem precisar das imagens originais.

---

## 1. O Que o Autor Fez Aqui? (História Explicada para Iniciantes)

Imagine que você acabou de construir uma máquina incrível chamada "Separador de Dias da Semana":

1. **A Máquina Pronta (Tela 1)**: O autor olha para a parte inferior do código (linhas 13 a 33). Tudo está no seu devido lugar:
   - A pergunta educada na tela;
   - A leitura do teclado convertida em maiúsculas;
   - O escudo `try / catch` protegendo contra palavras doidas;
   - O `switch` elegante separando os dias úteis dos fins de semana (incluindo o nosso querido `PIZZADAY`);
   - O autor passa o cursor e seleciona a palavra `MONDAY`, contemplando a clareza e a ordem do código.
2. **A Placa de Ouro na Entrada (Tela 2)**:
   - O autor sobe o editor até o topo da classe (linhas 1 a 10).
   - Todo bom professor deixa um resumo claro para que qualquer pessoa que abra o arquivo entenda imediatamente o que está acontecendo.
   - Ele escreve um bloco de comentários em inglês definindo o que são Enums e seleciona a palavra `Enums` com o mouse para explicar palavra por palavra da definição!

---

## 2. Reconstituição Textual das 2 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: O `switch` Completo e a Seleção em `MONDAY` (Timestamp: 11:12:00)

O autor exibe a metade inferior de `Main.java`. A lâmpada de inspeção 💡 aparece na margem esquerda e a palavra `MONDAY` está selecionada:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:12:00 |
+--------------------------------------------------------------------------------------------------+
| 13            System.out.print("Enter a day of the week: ");                                     |
| 14            String response = scanner.nextLine().toUpperCase();                                |
| 15                                                                                               |
| 16            try{                                                                               |
| 17                Day day = Day.valueOf(response);                                               |
| 18                                                                                               |
| 19                switch(day){                                                                   |
| 20  💡 👉             case [MONDAY],  <-- PALAVRA "MONDAY" SELECIONADA PELO CURSOR!              |
| 21                         TUESDAY,                                                              |
| 22                         WEDNESDAY,                                                            |
| 23                         THURSDAY,                                                             |
| 24                         FRIDAY -> System.out.println("It is a weekday");                      |
| 25                    case SATURDAY,                                                             |
| 26                         SUNDAY,                                                               |
| 27  👉                     PIZZADAY -> System.out.println("It is the weekend");                  |
| 28                }                                                                              |
| 29            }                                                                                  |
| 30            catch(IllegalArgumentException e){                                                 |
| 31                System.out.println("Please enter a valid day");                                |
| 32            }                                                                                  |
| 33                                                                                               |
+--------------------------------------------------------------------------------------------------+
| DETALHE: O switch agrupa os 5 dias úteis na primeira seta e os 3 dias de descanso                |
| (Sábado, Domingo e Dia da Pizza) na segunda seta. Nenhum break necessário!                       |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: Os Comentários Oficiais e a Seleção em `Enums` (Timestamp: 11:12:15)

O autor sobe para o topo de `Main.java` e seleciona a palavra `Enums` na linha 6:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:12:15 |
+--------------------------------------------------------------------------------------------------+
|  1  import java.util.Scanner;                                                                    |
|  2                                                                                               |
|  3  public class Main {                                                                          |
|  4      public static void main(String[] args) {                                                 |
|  5                                                                                               |
|  6  👉      // [Enums] = (Enumerations) A special kind of class that <-- PALAVRA "Enums" SELECIONADA|
|  7  👉      //         represents a fixed set of constants.                                      |
|  8  👉      //         They improve code readability and reliability.                            |
|  9  👉      //         More efficient with switches than Strings.                                |
| 10                                                                                               |
| 11          Scanner scanner = new Scanner(System.in);                                            |
| 12          System.out.print("Enter a day of the week: ");                                       |
| 13          String response = scanner.nextLine().toUpperCase();                                  |
| 14                                                                                               |
| 15          try{                                                                                 |
| 16              Day day = Day.valueOf(response);                                                 |
| 17                                                                                               |
| 18              switch(day){                                                                     |
| 19                  case MONDAY,                                                                 |
| 20                       TUESDAY,                                                                |
| 21                       ...                                                                     |
+--------------------------------------------------------------------------------------------------+
| O autor usa o cursor para apontar a definição e explicar aos alunos a teoria fundamental.        |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Código Completo de `Main.java`

Aqui está o código completo, sem cortes, idêntico ao do instrutor:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Enums = (Enumerations) A special kind of class that
        //         represents a fixed set of constants.
        //         They improve code readability and reliability.
        //         More efficient with switches than Strings.

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a day of the week: ");
        String response = scanner.nextLine().toUpperCase();

        try{
            Day day = Day.valueOf(response);

            switch(day){
                case MONDAY,
                     TUESDAY,
                     WEDNESDAY,
                     THURSDAY,
                     FRIDAY -> System.out.println("It is a weekday");
                case SATURDAY,
                     SUNDAY,
                     PIZZADAY -> System.out.println("It is the weekend");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Please enter a valid day");
        }

        scanner.close();

    }
}
```

---

## 4. Explicação Linha por Linha de Todo o Código

Vamos explicar linha por linha como se estivéssemos conversando com um iniciante amador absoluto:

### Linha 1: `import java.util.Scanner;`
* **O que faz**: É o pedido de empréstimo. O Java vem com milhares de classes guardadas no sótão (`java.util`). O comando `import` traz a ferramenta `Scanner` para a nossa mesa de trabalho para podermos ler o teclado.
* **O que sai no console**: Nada.

### Linha 3: `public class Main {`
* **O que faz**: Constrói a casa do nosso código. Toda lógica em Java precisa estar guardada dentro de uma classe com o mesmo nome do arquivo (`Main.java`).
* **O que sai no console**: Nada.

### Linha 4: `public static void main(String[] args) {`
* **O que faz**: É a ignição do motor. Quando o botão verde "Run" é clicado, o Java procura exatamente essa linha para ligar o programa.
* **O que sai no console**: Nada.

---

### Linhas 6 a 9: Os Comentários Oficiais dos 3 Pilares
```java
// Enums = (Enumerations) A special kind of class that
//         represents a fixed set of constants.
//         They improve code readability and reliability.
//         More efficient with switches than Strings.
```
* **O que faz**: Anotações teóricas do instrutor. O compilador do Java ignora completamente qualquer linha iniciada com `//`.
* **O que sai no console**: Nada.

---

### Linha 11: `Scanner scanner = new Scanner(System.in);`
* **O que faz**: Liga o microfone/ouvido do computador no teclado do usuário (`System.in`) e dá o nome desse leitor de `scanner`.
* **O que sai no console**: Nada.

### Linha 12: `System.out.print("Enter a day of the week: ");`
* **O que faz**: Imprime a pergunta na tela. Como usa `print` (sem o `ln` no final), o cursor fica coladinho esperando a digitação na mesma linha.
* **O que sai no console**:
  ```text
  Enter a day of the week: 
  ```

### Linha 13: `String response = scanner.nextLine().toUpperCase();`
* **O que faz**: 
  1. `scanner.nextLine()` escuta as teclas que o usuário digitou até ele apertar Enter.
  2. `.toUpperCase()` converte qualquer letra minúscula para MAIÚSCULA (ex: `monday` vira `MONDAY`).
  3. Guarda o texto na variável `response`.
* **O que sai no console**: As letras digitadas pelo usuário no teclado.

---

### Linha 15: `try{`
* **O que faz**: Cria uma redoma de vidro blindada. Qualquer linha de código aqui dentro que sofrer um erro grave não conseguirá quebrar o programa.
* **O que sai no console**: Nada.

### Linha 16: `Day day = Day.valueOf(response);`
* **O que faz**: **A ponte mágica entre Texto e Enum.**
  * O Java pega o texto de `response` e procura no arquivo `Day.java` se existe uma constante com esse exato nome.
  * Se existir (ex: `"MONDAY"` ou `"PIZZADAY"`): ele devolve a constante correspondente e guarda na variável `day`.
  * Se NÃO existir (ex: `"Hamburguer"`): ele dispara um alarme chamado `IllegalArgumentException`, interrompe o `try` e pula direto para o `catch` na linha 30!
* **O que sai no console**: Nada.

---

### Linha 18: `switch(day){`
* **O que faz**: Analisa qual constante está guardada dentro de `day` para escolher qual caminho tomar.
* **O que sai no console**: Nada.

### Linhas 19 a 24: O Bloco dos Dias de Semana
```java
case MONDAY,
     TUESDAY,
     WEDNESDAY,
     THURSDAY,
     FRIDAY -> System.out.println("It is a weekday");
```
* **O que faz**: Se `day` for Segunda, Terça, Quarta, Quinta ou Sexta, a setinha `->` executa a impressão avisando que é um dia útil.
* **O que sai no console** (se digitou segunda a sexta):
  ```text
  It is a weekday
  ```

### Linhas 25 a 27: O Bloco do Fim de Semana
```java
case SATURDAY,
     SUNDAY,
     PIZZADAY -> System.out.println("It is the weekend");
```
* **O que faz**: Se `day` for Sábado, Domingo ou Dia da Pizza, a setinha `->` avisa que é fim de semana!
* **O que sai no console** (se digitou sábado, domingo ou pizzaday):
  ```text
  It is the weekend
  ```

### Linha 28: `}` (Fim do Switch) e Linha 29: `}` (Fim do Try)
* **O que faz**: Fecham o switch e o bloco protegido do `try`. Se o dia foi válido, o Java pula o bloco `catch` e vai direto para a linha 34.
* **O que sai no console**: Nada.

---

### Linha 30: `catch(IllegalArgumentException e){`
* **O que faz**: O paraquedas de emergência. Só é acionado se a linha 16 disparou um `IllegalArgumentException` (quando o usuário digitou uma bobagem que não é dia).
* **O que sai no console**: Nada.

### Linha 31: `System.out.println("Please enter a valid day");`
* **O que faz**: Avisa ao usuário com calma e educação que ele digitou um dia inválido, sem assustá-lo com erros vermelhos.
* **O que sai no console** (somente se a entrada foi inválida):
  ```text
  Please enter a valid day
  ```

### Linha 32: `}` (Fim do Catch)
* **O que faz**: Fecha o bloco de resgate.
* **O que sai no console**: Nada.

---

### Linha 34: `scanner.close();`
* **O que faz**: Desliga o leitor de teclado. Como está fora do `try / catch`, essa linha sempre roda, garantindo que a memória seja liberada.
* **O que sai no console**: Nada.

### Linhas 35 e 36: `}` e `}`
* **O que faz**: Fecham o método `main` e a classe `Main`. O programa termina limpo com `exit code 0`.
* **O que sai no console**: Nada adicional.

---

## 5. Dissecando a Definição Oficial dos Enums Palavra por Palavra

O autor selecionou `Enums` na Tela 2 para explicar este texto sagrado:

```text
// Enums = (Enumerations) A special kind of class that
//         represents a fixed set of constants.
//         They improve code readability and reliability.
//         More efficient with switches than Strings.
```

### 1. O que significa "(Enumerations)"?
* Vem do verbo latim *enumerare*, que significa **"contar ou listar um por um"**.
* Em programação, quando você tem uma lista fechada de opções conhecidas, você está "enumerando" essas opções.

### 2. O que é uma "A special kind of class" (Classe Especial)?
* Em Java, uma classe comum você cria objetos usando a palavra `new` (ex: `new Scanner()`, `new Cachorro()`).
* Um Enum é uma classe especial onde você **NÃO pode usar `new`**! Todas as opções que podem existir no universo daquele Enum já nascem pré-criadas e prontas dentro dele (`Day.MONDAY`, `Day.SUNDAY`...). Ninguém pode inventar um novo objeto em tempo de execução.

### 3. O que é um "fixed set of constants" (Conjunto Fixo de Constantes)?
* **Fixed (Fixo)**: A quantidade não muda enquanto o programa roda. Dias da semana sempre serão aqueles.
* **Constants (Constantes)**: Valores que não podem ser alterados ou substituídos. Por convenção mundial, são escritos sempre em **MAIÚSCULAS**.

### 4. Por que "They improve code readability and reliability" (Legibilidade e Confiabilidade)?
* **Legibilidade**: Ler `case MONDAY` é autoexplicativo para qualquer humano. Ler `case 2` exige que você adivinhe se 2 é segunda ou terça.
* **Confiabilidade**: O compilador impede erros de digitação. Se você escrever `MONDAI` (com I no final), o código nem compila! Com Strings comuns (`"mondai"`), o erro passaria despercebido e causaria bugs graves.

### 5. Por que "More efficient with switches than Strings" (Mais Eficiente)?
* **Com String**: O processador precisa comparar letra por letra da palavra (`M` com `M`, `O` com `O`...) e calcular funções matemáticas chamadas *hash codes*.
* **Com Enum**: O Java atribui internamente para cada constante um número inteiro de índice sequencial chamado **ordinal** (`MONDAY = 1`, `TUESDAY = 2`...).
* No `switch`, a Máquina Virtual do Java (JVM) cria uma tabela de pulo na memória (*jump table* ou *tableswitch*). O processador dá um pulo direto para o endereço de memória do caso correspondente em um único ciclo de clock! É velocidade instantânea!

---

## 6. Tabela Completa de Saídas do Console para Cada Caso

| O Que o Usuário Digita no Teclado | Como o Java Processa | O Que Sai Impresso no Console | Exit Code |
| :--- | :--- | :--- | :---: |
| `Tuesday` | Converte para `TUESDAY` e acha em `Day.java`. | `Enter a day of the week: Tuesday`<br>`It is a weekday` | `0` |
| `FRIDAY` | Já é maiúsculo e acha em `Day.java`. | `Enter a day of the week: FRIDAY`<br>`It is a weekday` | `0` |
| `Sunday` | Converte para `SUNDAY` e acha no fim de semana. | `Enter a day of the week: Sunday`<br>`It is the weekend` | `0` |
| `pizzaday` | Converte para `PIZZADAY` e acha no fim de semana! | `Enter a day of the week: pizzaday`<br>`It is the weekend` | `0` |
| `Batata` | Não acha no Enum. O `catch` intercepta! | `Enter a day of the week: Batata`<br>`Please enter a valid day` | `0` |
| `999` | Não acha no Enum. O `catch` intercepta! | `Enter a day of the week: 999`<br>`Please enter a valid day` | `0` |
| *(Apertou Enter vazio)* | String vazia `""`. O `catch` intercepta! | `Enter a day of the week: `<br>`Please enter a valid day` | `0` |

---

## 7. Conclusão Final do Capítulo 68 (`enums 📅`)

Com esta explicação da Parte 11, você tem em mãos a visão mais completa, didática e profunda que existe sobre Enums em Java:
1. Conhece o molde em [`Day.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/models/Day.java).
2. Domina a proteção de exceções e a leitura em [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java).
3. Entende cada linha de código e cada detalhe visual exibido no curso do Bro Code.
