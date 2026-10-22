# Curso de Java — Bro Code (12 Horas)
## Capítulo 68: `enums 📅` (Parte 8 — Expandindo o Enum: Criando o `PIZZADAY(8)` em `Day.java`)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa das **5 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:11:09** e **11:11:45** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: todos os painéis do IntelliJ IDEA, a transição de abas de `Main.java` para `Day.java`, a adição da constante `PIZZADAY(8)`, os consoles de teste e a explicação detalhada foram reconstituídos em formato de texto para leitura independente.

---

## 1. O Que o Autor Fez Aqui? (História Explicada para Iniciantes)

Na aula anterior, nós vimos que quando o usuário digitava `Pizzaday`, o nosso escudo `try / catch` entrava em ação e dizia com educação: `"Please enter a valid day"` (Por favor, digite um dia válido).

Mas aí o Bro Code fez uma pergunta divertida que todo programador já se fez:
> *"E se no nosso sistema nós REALMENTE quiséssemos que o 'Dia da Pizza' fosse um dia da semana oficial?!"*

Para responder a isso, ele fez uma demonstração prática do maior superpoder dos Enums: **a Centralização de Regras**.

### A Analogia do Cartório Oficial
* Imagine que a classe `Main.java` é a **rua da cidade**, onde as pessoas conversam e digitam coisas.
* A classe `Day.java` é o **Cartório Oficial do Governo**.
* Não adianta as pessoas na rua gritarem que hoje é o "Dia da Pizza". Se o Cartório não tem uma certidão de nascimento para esse dia, ele **não existe** perante a lei do Java!
* Então, o que o Bro Code fez?
  1. Ele saiu da rua (`Main.java`) e entrou no Cartório (`Day.java`).
  2. Foi até a lista oficial de dias (linha 3) e carimbou: `, PIZZADAY(8);`.
  3. Agora, `PIZZADAY` é um dia oficial registrado, reconhecido pelo compilador e com o número `8` guardado dentro dele!

---

## 2. Reconstituição Textual das 5 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: O Teste com Dia Válido (`Tuesday`) no Console (Timestamp: 11:11:09)

O autor executa o programa e testa o caminho feliz digitando uma terça-feira comum:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Run > Main)                             Timestamp 11:11:09 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...                                                   |
|                                                                                                  |
|  Enter a day of the week: Tuesday                                                                |
|  It is a weekday                                                                                 |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| RESULTADO: Como "Tuesday" existe em Day.java, ele foi reconhecido e impresso "It is a weekday".  |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: O Teste com `Pizzaday` Tratado pelo `try-catch` (Timestamp: 11:11:18)

O autor roda novamente e digita `Pizzaday`. O `try/catch` impede o programa de quebrar:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Run > Main)                             Timestamp 11:11:18 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...                                                   |
|                                                                                                  |
|  Enter a day of the week: Pizzaday                                                               |
|  Please enter a valid day                                                                        |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| RESULTADO: Como "Pizzaday" AINDA NÃO EXISTIA em Day.java, o catch interceptou a exceção e       |
| avisou educadamente: "Please enter a valid day".                                                 |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: O Ponto de Decisão do Autor no Console (Timestamp: 11:11:18)

O autor olha para a mensagem no console e decide oficializar o `Pizzaday` no código:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console com o texto "Pizzaday" destacado                     Timestamp 11:11:18 |
+--------------------------------------------------------------------------------------------------+
|  Enter a day of the week: Pizzaday | <-- O autor destaca a palavra "Pizzaday" no console         |
|  Please enter a valid day                                                                        |
+--------------------------------------------------------------------------------------------------+
| O autor pensa: "Como fazemos para que o Pizzaday deixe de ser um erro e vire um dia aceito?!"    |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: Abrindo a Aba `Day.java` no Editor (Timestamp: 11:11:30)

O autor clica na aba de `Day.java`. Vemos a estrutura original com os 7 dias tradicionais:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Day.java]                                    Timestamp 11:11:30 |
+--------------------------------------------------------------------------------------------------+
|  1  public enum Day {                                                                            |
|  2                                                                                               |
|  3      SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);    |
|  4                                                                                               |
|  5      private final int dayNumber;                                                             |
|  6                                                                                               |
|  7      Day(int dayNumber) {                                                                     |
|  8          this.dayNumber = dayNumber;                                                          |
|  9      }                                                                                        |
| 10                                                                                               |
| 11      public int getDayNumber(){                                                               |
| 12          return this.dayNumber;                                                               |
| 13      }                                                                                        |
| 14  }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| O cursor está na linha 3, logo após "SATURDAY(7);", pronto para adicionar a nova constante.      |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 5: Adicionando `, PIZZADAY(8);` em `Day.java` (Timestamp: 11:11:45)

O autor digita `, PIZZADAY(8);` na linha 3. O cursor fica logo após o fechamento e o mouse vai em direção à aba `Main.java`:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Day.java]                                    Timestamp 11:11:45 |
+--------------------------------------------------------------------------------------------------+
|  1    public enum Day {                                                                          |
|  2                                                                                               |
|  3 👉     SUNDAY(1), ..., SATURDAY(7), PIZZADAY(8);| <-- CURSOR APÓS O NOVO DIA                  |
|  4                                                                                               |
|  5        private final int dayNumber;                                                           |
|  6                                                                                               |
|  7        Day(int dayNumber) {                                                                   |
|  8            this.dayNumber = dayNumber;                                                        |
|  9        }                                                                                      |
| 10                                                                                               |
| 11        public int getDayNumber(){ ... }                                                       |
| 14    }                                                                                          |
+--------------------------------------------------------------------------------------------------+
| MOUSE: O autor move o ponteiro do mouse para clicar na aba [Main.java] no canto superior!        |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Os Códigos Atualizados do Projeto

### Arquivo 1: `Day.java` (Agora com `PIZZADAY(8)`)

```java
public enum Day {

    SUNDAY(1), 
    MONDAY(2), 
    TUESDAY(3), 
    WEDNESDAY(4), 
    THURSDAY(5), 
    FRIDAY(6), 
    SATURDAY(7), 
    PIZZADAY(8);

    private final int dayNumber;

    Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getDayNumber(){
        return this.dayNumber;
    }
}
```

---

### Arquivo 2: `Main.java` (Pronto para Usar o Novo Dia)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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
                case SATURDAY, SUNDAY -> System.out.println("It is the weekend");
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

## 4. Explicação Linha por Linha de `Day.java` (O Molde do Enum)

Vamos explicar com paciência absoluta cada linha do arquivo que o autor editou:

### Linha 1: `public enum Day {`
* **O que faz**: Declara um tipo especial de classe chamado `enum` com o nome `Day`. A palavra `public` significa que qualquer outro arquivo do nosso projeto (como o `Main.java`) tem autorização para enxergar e usar esse Enum.
* **O que sai no console**: Nada. Essa linha é a casca exterior do arquivo.

### Linha 3: `SUNDAY(1), MONDAY(2), ..., SATURDAY(7), PIZZADAY(8);`
* **O que faz**: Esta é a linha mais importante da aula! Aqui estão listadas todas as **Constantes** permitidas no nosso universo de dias.
  * **O que é `PIZZADAY`**: É o novo membro oficial da família `Day`. Por convenção em Java, nomes de constantes em Enums são sempre escritos em **LETRAS MAIÚSCULAS**.
  * **O que é o `(8)` entre parênteses**: É o argumento passado para o construtor do Enum! Assim como Domingo recebeu o número 1 e Sábado o número 7, o Dia da Pizza recebeu o número de identificação 8!
  * **A vírgula `,`**: Separa uma constante da outra.
  * **O ponto e vírgula `;` no final**: É obrigatório! Sempre que um Enum tiver atributos, métodos ou construtores depois da lista de constantes, a lista DEVE terminar com um ponto e vírgula `;`.
* **O que sai no console**: Nada.

### Linha 5: `private final int dayNumber;`
* **O que faz**: Cria uma caixinha de memória interna para guardar o número do dia.
  * `private`: Significa que essa caixinha é trancada a sete chaves. Ninguém de fora pode mexer nela diretamente.
  * `final`: Significa que esse número é uma **tatuagem definitiva**! Uma vez que o número 8 foi dado ao `PIZZADAY`, ninguém jamais poderá alterar esse número durante a execução do programa.
  * `int`: Tipo de dado numérico inteiro.
* **O que sai no console**: Nada.

### Linhas 7 a 9: O Construtor
```java
Day(int dayNumber) {
    this.dayNumber = dayNumber;
}
```
* **O que faz**: É a fábrica que constrói cada constante de Enum.
  * Quando o Java lê `PIZZADAY(8)`, ele chama esse construtor passando o número `8` como parâmetro.
  * `this.dayNumber = dayNumber;` pega esse `8` e guarda com segurança dentro do campo interno da constante.
* **O que sai no console**: Nada.

### Linhas 11 a 13: O Método Getter
```java
public int getDayNumber(){
    return this.dayNumber;
}
```
* **O que faz**: É a janelinha de atendimento ao público. Como a caixinha `dayNumber` é privada, se alguém no `Main.java` quiser saber qual é o número do Dia da Pizza, basta chamar `Day.PIZZADAY.getDayNumber()`, e esse método devolverá o número `8`.
* **O que sai no console**: Nada.

### Linha 14: `}`
* **O que faz**: Fecha a chave do Enum `Day`.

---

## 5. O Que Muda em `Main.java` Agora que `PIZZADAY(8)` Existe?

Esta é a virada de chave fundamental que o Bro Code está ensinando:

```text
====================================================================================================
ANTES DE ADICIONAR EM Day.java                | DEPOIS DE ADICIONAR EM Day.java
====================================================================================================
Usuário digita: "Pizzaday"                    | Usuário digita: "Pizzaday"
.toUpperCase() vira: "PIZZADAY"               | .toUpperCase() vira: "PIZZADAY"
Day.valueOf("PIZZADAY") -> ERRO!              | Day.valueOf("PIZZADAY") -> SUCESSO!
Dispara: IllegalArgumentException             | Retorna a constante oficial: Day.PIZZADAY
Cai no catch: "Please enter a valid day"      | Não dá erro! day agora vale Day.PIZZADAY
====================================================================================================
```

### O Que Acontece no Próximo Passo do Vídeo?
Agora que `Day.java` já tem o `PIZZADAY`, o Bro Code vai poder voltar para o `Main.java` e incluir o `PIZZADAY` diretamente dentro dos casos do `switch` sem que o IntelliJ pinte a linha de vermelho:
```java
case SATURDAY, SUNDAY, PIZZADAY -> System.out.println("It is the weekend");
```
E quando o usuário digitar `Pizzaday`, o programa vai responder:
```text
Enter a day of the week: PIZZADAY
It is the weekend
```

---

## 6. Tabela de Estados: Como o Java Enxerga as Constantes de `Day`

| Constante Enum | Nome Oficial (`.name()`) | Número Guardado (`.getDayNumber()`) | Posição Sequencial (`.ordinal()`) |
| :--- | :---: | :---: | :---: |
| `Day.SUNDAY` | `"SUNDAY"` | `1` | `0` |
| `Day.MONDAY` | `"MONDAY"` | `2` | `1` |
| `Day.TUESDAY` | `"TUESDAY"` | `3` | `2` |
| `Day.WEDNESDAY` | `"WEDNESDAY"` | `4` | `3` |
| `Day.THURSDAY` | `"THURSDAY"` | `5` | `4` |
| `Day.FRIDAY` | `"FRIDAY"` | `6` | `5` |
| `Day.SATURDAY` | `"SATURDAY"` | `7` | `6` |
| **`Day.PIZZADAY`** | **`"PIZZADAY"`** | **`8`** | **`7`** |

---

## 7. Resumo Prático para o Aluno Iniciante

1. **Enums são extensíveis com facilidade**: Se o seu programa precisa de uma opção nova, você só mexe em um único lugar: o arquivo `.java` do Enum.
2. **Cada constante recebe os mesmos atributos**: Como nosso construtor exige um número inteiro `(int dayNumber)`, o novo dia `PIZZADAY` foi obrigado a receber um número entre parênteses: `PIZZADAY(8)`. Se você esquecesse o `(8)`, o Java daria erro!
3. **Ponto e vírgula obrigatório**: A lista de constantes termina com ponto e vírgula `;` porque o arquivo continua com atributos e métodos logo abaixo.
