# Curso de Java — Bro Code (12 Horas)
## Capítulo 68: `enums 📅` (Parte 7 — O Escudo Defensivo `try/catch`, Teste `PIZZADAY`, Segurança de Tipos e Conclusão de Ouro)

---

> [!NOTE]
> **Contexto para o Google Docs**: Este documento contém a reconstituição textual completa e minuciosa de todas as telas exibidas pelo instrutor Bro Code no encerramento do **Capítulo 68** (entre os minutos **11:10:40** e **11:12:30** do vídeo de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: todos os painéis do IntelliJ IDEA, códigos-fonte, sublinhados vermelhos de erros, telas de compilação, digitações no console e os comentários finais foram reconstituídos em formato de texto.

---

## 1. O Que Aconteceu Neste Episódio? (Visão Geral para Iniciantes)

Na aula anterior (Parte 6), nosso programa já conseguia ler o dia digitado pelo usuário, transformar em maiúsculas (`.toUpperCase()`), converter para Enum com `Day.valueOf()` e responder no `switch`.

Porém, descobrimos uma **bomba-relógio**:
Se o usuário digitasse qualquer coisa fora da lista (como `Pizzaday`), o Java entrava em pânico total e jogava na tela uma montanha de texto vermelho assustador:
`Exception in thread "main" java.lang.IllegalArgumentException: No enum constant Day.PIZZADAY`
...e o programa encerrava com erro (`Exit code 1`).

Neste episódio final do capítulo de Enums, o Bro Code faz quatro movimentos geniais:

1. **Coloca um Escudo Protetor (`try / catch`)**: Captura o erro `IllegalArgumentException` e mostra uma mensagem educada (`"Please enter a valid day"`), fazendo o programa terminar em paz (`Exit code 0`).
2. **Faz o Teste do `PIZZADAY` no `switch`**: Mostra que o compilador do Java é um vigia implacável: se você tentar colocar um caso (`case PIZZADAY`) que não existe no Enum, o IntelliJ nem deixa o programa rodar (Segurança em Tempo de Compilação).
3. **Faz o Experimento de Adicionar o `PIZZADAY` no `Day.java`**: Mostra como Enums são organizados e expansíveis de forma centralizada.
4. **Desmascara o Erro de Números no Switch**: Tenta colocar `case 1, 2, 3...` no `switch(day)` e explica por que o Java proíbe misturar números com Enums.
5. **Escreve o Resumo de Ouro**: Adiciona comentários oficiais no topo do código explicando a verdadeira utilidade dos Enums no mundo real.

---

## 2. Reconstituição Textual das Telas do Vídeo (Google Docs Ready)

---

### Tela 1: Abrindo o Bloco `try {` em Volta do Código Perigoso (Timestamp: 11:10:40)

O autor coloca a palavra `try{` antes de `Day day = Day.valueOf(response);` e fecha uma chave `}` depois do `switch`. Como ainda não escreveu o `catch`, o IntelliJ avisa com sublinhado vermelho na chave de fechamento:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:10:40 |
+--------------------------------------------------------------------------------------------------+
| 13            System.out.print("Enter a day of the week: ");                                     |
| 14            String response = scanner.nextLine().toUpperCase();                                |
| 15                                                                                               |
| 16  👉        try{                                                                               |
| 17                Day day = Day.valueOf(response);                                               |
| 18                                                                                               |
| 19                switch(day){                                                                   |
| 20                    case MONDAY,                                                               |
| 21                         TUESDAY,                                                              |
| 22                         WEDNESDAY,                                                            |
| 23                         THURSDAY,                                                             |
| 24                         FRIDAY -> System.out.println("It is a weekday");                      |
| 25                    case SATURDAY, SUNDAY -> System.out.println("It is the weekend");          |
| 26                }                                                                              |
| 27  🚨        } ~~~ [ERRO: 'try' without 'catch', 'finally' or resource declarations]            |
| 28                                                                                               |
| 29            scanner.close();                                                                   |
+--------------------------------------------------------------------------------------------------+
| O IntelliJ avisa: "Ei! Você abriu um try (tentativa), mas cadê o catch para capturar o erro?!"   |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: Escrevendo o `catch(IllegalArgumentException e)` (Timestamp: 11:10:53 a 11:11:00)

O autor completa o bloco capturando o erro exato que o método `Day.valueOf()` dispara quando o texto não bate com nenhum Enum:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:00 |
+--------------------------------------------------------------------------------------------------+
| 16            try{                                                                               |
| 17                Day day = Day.valueOf(response);                                               |
| 18                                                                                               |
| 19                switch(day){                                                                   |
| 20                    case MONDAY,                                                               |
| 21                         TUESDAY,                                                              |
| 22                         WEDNESDAY,                                                            |
| 23                         THURSDAY,                                                             |
| 24                         FRIDAY -> System.out.println("It is a weekday");                      |
| 25                    case SATURDAY, SUNDAY -> System.out.println("It is the weekend");          |
| 26                }                                                                              |
| 27            }                                                                                  |
| 28  👉        catch(IllegalArgumentException e){                                                 |
| 29  👉            System.out.println("Please enter a valid day");                                |
| 30  👉        }                                                                                  |
| 31                                                                                               |
| 32            scanner.close();                                                                   |
+--------------------------------------------------------------------------------------------------+
| Nenhum erro vermelho! Código 100% protegido contra entradas inválidas do usuário!                |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: O Teste de Segurança em Tempo de Compilação com `PIZZADAY` (Timestamp: 11:11:25)

O Bro Code testa o que acontece se o programador tentar inventar um caso no `switch` que não existe no Enum:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:25 |
+--------------------------------------------------------------------------------------------------+
| 25                    case SATURDAY,                                                             |
| 26                         SUNDAY,                                                               |
| 27  💡 🚨                  PIZZADAY -> System.out.println("It is the weekend");                  |
|                             ~~~~~~~                                                              |
|                     [ERRO: Cannot resolve symbol 'PIZZADAY']                                     |
| 28                }                                                                              |
+--------------------------------------------------------------------------------------------------+
| POR QUE O INTELLIJ RECLAMOU?                                                                     |
| Porque a variável do switch é do tipo `Day`. O Java olha para o arquivo `Day.java` e diz:        |
| "Eu procurei em Day.java e lá só existem SUNDAY até SATURDAY. 'PIZZADAY' não existe!"             |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: Cadastrando `PIZZADAY` em `Day.java` e Rodando no Console (Timestamp: 11:11:50)

O autor vai até o arquivo `Day.java` e adiciona `PIZZADAY`. Imediatamente o erro vermelho some e ele executa o programa:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução                                          Timestamp 11:11:50 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe Main                                                  |
|                                                                                                  |
|  Enter a day of the week: PIZZADAY                                                               |
|  It is the weekend                                                                               |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O usuário digitou PIZZADAY e o programa respondeu: "It is the weekend"!                          |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 5: O Erro de Tentar Usar Números no `switch(day)` (Timestamp: 11:12:28)

O autor apaga os nomes dos dias e tenta colocar números (`case 1, 2, 3...`) no `switch(day)`:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:12:28 |
+--------------------------------------------------------------------------------------------------+
| 19            switch(day){                                                                       |
| 20  🚨            case 1,                                                                        |
| 21  🚨                 2,                                                                        |
| 22  🚨                 3,                                                                        |
| 23  🚨                 4,                                                                        |
| 24  🚨                 5 -> System.out.println("It is a weekday");                               |
| 25  🚨            case 6,                                                                        |
| 26  🚨                 7,                                                                        |
| 27  💡 🚨              8 -> System.out.println("It is the weekend");                              |
| 28            }                                                                                  |
+--------------------------------------------------------------------------------------------------+
| O QUE DEU ERRADO AQUI?                                                                           |
| Todos os números (1 a 8) ficaram com sublinhado vermelho!                                       |
| Erro: Incompatible types. Found: 'int', required: 'Day'                                          |
| A variável 'day' é do tipo Day (um Enum), então os casos do switch PRECISAM ser constantes Day!  |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 6: Os Comentários Oficiais de Conclusão no Topo do Código (Timestamp: 11:12:30)

O autor escreve a definição oficial de Enums no topo do método `main`:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:12:30 |
+--------------------------------------------------------------------------------------------------+
|  1  import java.util.Scanner;                                                                    |
|  2                                                                                               |
|  3  public class Main {                                                                          |
|  4      public static void main(String[] args) {                                                 |
|  5                                                                                               |
|  6  👉      // Enums = (Enumerations) A special kind of class that                               |
|  7  👉      //         represents a fixed set of constants.                                      |
|  8  👉      //         They improve code readability and reliability.                            |
|  9  👉      //         More efficient with switches than Strings.                                |
| 10                                                                                               |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. O Código Completo dos Dois Arquivos

Aqui estão as versões finais completas e funcionais dos dois arquivos do projeto.

### Arquivo 1: `Day.java` (O Molde do Enum)

```java
// Day.java
public enum Day {

    SUNDAY(1), 
    MONDAY(2), 
    TUESDAY(3), 
    WEDNESDAY(4), 
    THURSDAY(5), 
    FRIDAY(6), 
    SATURDAY(7);

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

### Arquivo 2: `Main.java` (O Programa com Escudo Protetor e Comentários)

```java
// Main.java
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

## 4. Explicação Linha por Linha do Arquivo `Main.java`

Vamos analisar cada linha como se fôssemos uma criança ou um iniciante absoluto aprendendo programação hoje:

### Linha 1: `import java.util.Scanner;`
- **O que faz**: Avisa ao compilador Java: *"Por favor, traga a ferramenta chamada `Scanner` da caixa de utilidades padrão (`java.util`) para dentro do nosso arquivo"*.
- **O que sai no console**: Nada. Essa linha só prepara as ferramentas nos bastidores.

### Linha 3: `public class Main {`
- **O que faz**: Declara a classe pública chamada `Main`. Em Java, todo código precisa morar dentro de uma classe, e como ela se chama `Main`, o arquivo no disco precisa se chamar `Main.java`.
- **O que sai no console**: Nada.

### Linha 4: `public static void main(String[] args) {`
- **O que faz**: É a porta de entrada mágica do programa. Quando você clica no botão verde "Run" ou digita `java Main`, a Máquina Virtual do Java (JVM) procura exatamente essa linha para começar a trabalhar.
- **O que sai no console**: Nada.

### Linhas 6 a 9: Os Comentários Oficiais de Enums
```java
// Enums = (Enumerations) A special kind of class that
//         represents a fixed set of constants.
//         They improve code readability and reliability.
//         More efficient with switches than Strings.
```
- **O que faz**: São anotações em inglês feitas pelo autor explicando o que são Enums. As duas barras `//` dizem ao Java: *"Ignore isso, é só para humanos lerem"*.
- **O que sai no console**: Nada.

### Linha 11: `Scanner scanner = new Scanner(System.in);`
- **O que faz**: Cria nosso leitor de teclado. O `System.in` representa a corrente de dados que vem das teclas que você digita no terminal.
- **O que sai no console**: Nada visualmente, mas o Java já prepara a antena para ouvir o que digitarmos.

### Linha 12: `System.out.print("Enter a day of the week: ");`
- **O que faz**: Imprime a pergunta na tela sem pular de linha (porque usou `print` em vez de `println`), deixando o cursor piscando ao lado do texto esperando a digitação.
- **O que sai no console**:
  ```text
  Enter a day of the week: 
  ```

### Linha 13: `String response = scanner.nextLine().toUpperCase();`
- **O que faz**: Lê tudo o que o usuário digitou até ele apertar `[Enter]` (`scanner.nextLine()`), transforma instantaneamente todas as letras em maiúsculas (`.toUpperCase()`) e guarda o resultado na gaveta de texto chamada `response`.
  - Exemplo: se você digitou `friday`, ela vira `"FRIDAY"`.
  - Se digitou `pizzaday`, ela vira `"PIZZADAY"`.
- **O que sai no console**: O texto que você digitou fica visível na tela enquanto você digita.

---

### Linha 15: `try{` (O Começo da Zona Protegida)
- **O que faz**: A palavra `try` significa **"Tente"**. É como dizer ao Java: *"Java, execute as próximas instruções com cuidado. Se alguma delas explodir ou der erro, não desligue o programa nem mostre aquela tela vermelha feia; pule direto para o bloco `catch`!"*
- **Analogia**: É como entrar numa pista de patinação usando capacete, joelheira e cotoveleira. Se você escorregar, você não vai quebrar os ossos.
- **O que sai no console**: Nada.

### Linha 16: `Day day = Day.valueOf(response);`
- **O que faz**: O método mágico embutido `Day.valueOf()` pega a String que o usuário digitou e procura na lista de constantes do Enum `Day`.
  - **Cenário A (Dia Válido)**: Se `response` for `"MONDAY"`, ele encontra a constante `Day.MONDAY` e guarda na gaveta `day`. O código continua normalmente para a linha seguinte.
  - **Cenário B (Dia Inválido)**: Se `response` for `"PIZZADAY"`, o Java procura em `Day.java` e não acha. Ele dispara a exceção `IllegalArgumentException`! Mas como estamos dentro do `try`, a execução é interrompida imediatamente nesta linha e o Java salta voando para o `catch` na linha 27!
- **O que sai no console**: Nada.

### Linha 18: `switch(day){`
- **O que faz**: Analisa qual constante de Enum está guardada dentro da gaveta `day` para decidir qual ramo de código seguir.
- **O que sai no console**: Nada.

### Linhas 19 a 23: Os Casos de Dias Úteis
```java
case MONDAY,
     TUESDAY,
     WEDNESDAY,
     THURSDAY,
     FRIDAY -> System.out.println("It is a weekday");
```
- **O que faz**: Se `day` for segunda, terça, quarta, quinta ou sexta-feira, a setinha moderna `->` faz executar a impressão na tela dizendo que é um dia de semana.
- **O que sai no console** (se digitou segunda a sexta):
  ```text
  It is a weekday
  ```

### Linha 24: `case SATURDAY, SUNDAY -> System.out.println("It is the weekend");`
- **O que faz**: Se `day` for sábado ou domingo, a setinha `->` executa a impressão avisando que é fim de semana!
- **O que sai no console** (se digitou sábado ou domingo):
  ```text
  It is the weekend
  ```

### Linha 25: `}` (Fim do Switch) e Linha 26: `}` (Fim do Bloco `try`)
- **O que faz**: Fecham as chaves do `switch` e da zona protegida do `try`. Se o dia foi válido, todo esse bloco rodou perfeitamente e o Java agora pula o bloco `catch` e vai direto para a linha 31.
- **O que sai no console**: Nada.

---

### Linha 27: `catch(IllegalArgumentException e){` (O Colchão Inflável)
- **O que faz**: A palavra `catch` significa **"Capture"**. Ela só entra em ação se alguma linha dentro do `try` disparar a exceção indicada nos parênteses: `IllegalArgumentException`.
  - A letrinha `e` é uma variável que guarda os dados técnicos do erro ocorrido (como a mensagem e onde aconteceu).
  - Se tudo correu bem dentro do `try`, o Java **ignora completamente** o que estiver dentro do `catch`.
  - Mas se o usuário digitou `"PIZZADAY"`, a linha 16 disparou o erro e o Java caiu exatamente aqui!
- **O que sai no console**: Nada.

### Linha 28: `System.out.println("Please enter a valid day");`
- **O que faz**: Imprime uma mensagem gentil e civilizada para o usuário em vez daquele texto vermelho assustador da JVM.
- **O que sai no console** (apenas quando o usuário digita algo inválido):
  ```text
  Please enter a valid day
  ```

### Linha 29: `}` (Fim do Bloco `catch`)
- **O que faz**: Fecha o bloco de tratamento de erro. O Java agora continua a vida normalmente no código seguinte.
- **O que sai no console**: Nada.

---

### Linha 31: `scanner.close();`
- **O que faz**: Fecha o leitor de teclado para liberar a memória e os recursos do sistema operacional.
- **Por que ficou fora do `try / catch`?**
  Porque nós **sempre** queremos fechar o Scanner, tanto se o usuário digitou um dia válido quanto se digitou uma palavra errada! Ele precisa fechar de qualquer jeito.
- **O que sai no console**: Nada.

### Linhas 33 e 34: `}` e `}`
- **O que fazem**: Fecham o método `main` e a classe `Main`. O programa termina de forma limpa com código de saída 0 (`Process finished with exit code 0`).

---

## 5. Os 4 Grandes Experimentos e Erros Explicados pelo Bro Code

---

### Experimento 1: Antes do `try-catch` vs. Depois do `try-catch`

Compare a diferença brutal de experiência do usuário:

| Situação | O que o Usuário Digitou | O que Saía no Console (Sem `try-catch`) | O que Sai no Console (Com `try-catch`) |
| :--- | :--- | :--- | :--- |
| **Dia Correto** | `friday` | `Enter a day of the week: friday`<br>`It is a weekday`<br>*(Exit code 0)* | `Enter a day of the week: friday`<br>`It is a weekday`<br>*(Exit code 0)* |
| **Dia Errado** | `Pizzaday` | 💥 **EXPLOSÃO DE TEXTO VERMELHO:**<br>`Exception in thread "main"`<br>`java.lang.IllegalArgumentException:`<br>`No enum constant Day.PIZZADAY`<br>*(Exit code 1 — Programa Quebrou!)* | 🛡️ **RESPOSTA AMIGÁVEL:**<br>`Enter a day of the week: Pizzaday`<br>`Please enter a valid day`<br>*(Exit code 0 — Programa Terminou em Paz!)* |

---

### Experimento 2: O Teste do `PIZZADAY` no `switch` (Segurança em Tempo de Compilação)

O autor tentou escrever:
```java
case SATURDAY, SUNDAY, PIZZADAY -> System.out.println("It is the weekend");
```
E o IntelliJ sublinhou `PIZZADAY` de vermelho com o erro:
`Cannot resolve symbol 'PIZZADAY'`

#### Por que isso acontece?
Porque um Enum em Java é uma lista **fechada e restrita**.
Se o switch está avaliando uma variável do tipo `Day`, o compilador só aceita os nomes que foram formalmente batizados dentro de `Day.java`.
Se você estivesse usando Strings comuns (`switch(texto)`), você poderia digitar qualquer bobagem como `"PIZZADAY"` ou `"BATATADAY"`, e o compilador nunca saberia que você cometeu um erro de digitação.
**Com Enums, o compilador te protege contra seus próprios erros de digitação antes mesmo de o código rodar!**

---

### Experimento 3: Adicionando `PIZZADAY` em `Day.java`

Para provar que os Enums são fáceis de expandir de forma centralizada, o Bro Code abriu o arquivo `Day.java` e alterou a primeira linha para:

```java
public enum Day {
    SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7), PIZZADAY(8);
    // ... restante igual ...
}
```

Assim que ele salvou `Day.java`, voltou para o `Main.java`:
- O sublinhado vermelho no `case PIZZADAY` **desapareceu na hora**!
- Ele executou o programa, digitou `PIZZADAY` e o console imprimiu alegremente:
  ```text
  Enter a day of the week: PIZZADAY
  It is the weekend
  ```
Isso ensina que: quando sua empresa criar um novo status de pedido, um novo método de pagamento ou um novo dia especial, você só precisa ir no arquivo do Enum e cadastrar lá. O resto do projeto inteiro passa a reconhecer o novo valor automaticamente!

---

### Experimento 4: O Erro de Usar Números no `switch(day)`

O autor tentou substituir os nomes dos dias por números:
```java
switch(day){
    case 1, 2, 3, 4, 5 -> System.out.println("It is a weekday");
    case 6, 7, 8 -> System.out.println("It is the weekend");
}
```
Todos os números (`1` a `8`) ficaram sublinhados de vermelho com o erro:
`Incompatible types. Found: 'int', required: 'Day'`

#### Por que o Java deu esse erro?
1. A variável colocada dentro dos parênteses do switch é `day`.
2. Qual é o tipo da variável `day`? O tipo dela é `Day` (uma classe de Enum).
3. Cada rótulo de `case` precisa ter **o mesmo tipo** da variável que está sendo testada.
4. O número `1` é um número inteiro primitivo (`int`). O Java não permite comparar um objeto `Day` com um número primitivo `int`!
5. Se você realmente quisesse comparar com números, teria que ter feito:
   `switch(day.getDayNumber())` (usando o método getter que criamos na aula 2).
6. Mas o Bro Code deixa claro: fazer isso com números perde todo o sentido de usar Enums! A beleza dos Enums é usar nomes legíveis (`MONDAY`, `TUESDAY`) em vez de números misteriosos que ninguém decora ("números mágicos").

---

## 6. Os 3 Pilares dos Enums (Dissecando o Comentário Oficial)

No final da aula, o Bro Code escreveu estas 4 linhas no topo do código:

```java
// Enums = (Enumerations) A special kind of class that
//         represents a fixed set of constants.
//         They improve code readability and reliability.
//         More efficient with switches than Strings.
```

Vamos entender profundamente cada um desses 3 pilares:

### Pilar 1: Um Conjunto Fixo de Constantes ("A fixed set of constants")
- Um Enum não serve para guardar coisas infinitas que mudam toda hora (como nomes de clientes ou números de telefone).
- Ele serve para coisas que têm opções conhecidas, contáveis e fixas na natureza ou no seu negócio:
  - Dias da semana (7 dias).
  - Meses do ano (12 meses).
  - Naipes do baralho (Copas, Espadas, Ouros, Paus).
  - Status de uma compra (`PENDENTE`, `PAGO`, `ENVIADO`, `CANCELADO`).
  - Níveis de usuário (`ADMIN`, `MODERADOR`, `VISITANTE`).

### Pilar 2: Legibilidade e Confiabilidade ("Improve code readability and reliability")
- **Legibilidade (Readability)**: Olhar para o código e ver `case MONDAY` é mil vezes mais fácil de entender do que ver `case 2` ou `if (dia == 1)`. Você não precisa adivinhar se domingo é 1 ou 7.
- **Confiabilidade (Reliability)**: Se você usar `String`, alguém pode digitar `"Segunda"`, outro `"segunda-feira"`, outro `"mon"`, e o programa não reconhece nada. Com Enum, só existe uma única constante oficial (`Day.MONDAY`), eliminando 100% dos erros de digitação e inconsistências.

### Pilar 3: Mais Eficiente no Switch do que Strings ("More efficient with switches than Strings")
- Quando você usa `switch` com `String` comum, a Máquina Virtual do Java (JVM) tem que comparar cada letra da palavra uma por uma (`'M' == 'M'`, `'O' == 'O'`, etc.) e calcular códigos hash.
- Quando você usa `switch` com `Enum`, a JVM não compara letras! Por baixo dos panos, o compilador do Java associa cada constante a um número de posição sequencial chamado **ordinal** (`MONDAY` = 0, `TUESDAY` = 1, etc.).
- Com isso, a JVM monta uma tabela de desvio direta na memória da máquina (conhecida como *jump table* ou *tableswitch*). O computador pula instantaneamente para a linha certa em nanosegundos, sendo muito mais rápido e gastando menos bateria e memória do computador!

---

## 7. Tabela Resumo: Saídas Reais de Teste no Console

| Teste | Entrada do Usuário | Saída Impressa no Console | Código de Saída (Exit Code) | O Que Aconteceu por Trás dos Panos? |
| :---: | :---: | :---: | :---: | :--- |
| **1** | `Sunday` | `Enter a day of the week: Sunday`<br>`It is the weekend` | `0` (Sucesso) | Vira `SUNDAY`, acha no Enum, cai no caso do fim de semana. |
| **2** | `WEDNESDAY` | `Enter a day of the week: WEDNESDAY`<br>`It is a weekday` | `0` (Sucesso) | Vira `WEDNESDAY`, acha no Enum, cai no caso de dia útil. |
| **3** | `tuesday` | `Enter a day of the week: tuesday`<br>`It is a weekday` | `0` (Sucesso) | O `.toUpperCase()` converte para maiúsculas antes do `valueOf`. |
| **4** | `Pizzaday` | `Enter a day of the week: Pizzaday`<br>`Please enter a valid day` | `0` (Sucesso) | `Day.valueOf()` dispara `IllegalArgumentException`. O bloco `catch` intercepta e avisa o usuário sem travar! |
| **5** | `12345` | `Enter a day of the week: 12345`<br>`Please enter a valid day` | `0` (Sucesso) | Números não são dias. O `catch` intercepta e avisa o usuário! |
| **6** | *(Apertou Enter vazio)* | `Enter a day of the week: `<br>`Please enter a valid day` | `0` (Sucesso) | String vazia não existe no Enum. O `catch` intercepta e avisa o usuário! |

---

## 8. Conclusão do Capítulo 68 (`enums 📅`)

Com este episódio, você dominou completamente um dos tópicos mais nobres e utilizados da arquitetura profissional de software em Java:
1. Sabe criar um arquivo Enum (`Day.java`).
2. Sabe adicionar campos (`final int dayNumber`), construtores e métodos getters dentro de um Enum.
3. Sabe usar a sintaxe moderna e limpa de `switch` com setinhas (`->`) e múltiplos casos agrupados por vírgula.
4. Sabe coletar texto do usuário via `Scanner` e normalizar com `.toUpperCase()`.
5. Sabe fazer a ponte mágica entre texto livre e tipo Enum com `Day.valueOf()`.
6. Sabe blindar seu programa contra erros inesperados com `try { ... } catch (IllegalArgumentException e) { ... }`.
7. Conhece o valor inestimável da segurança em tempo de compilação e a alta performance dos Enums nos bastidores da JVM!
