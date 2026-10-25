# Curso de Java — Bro Code (12 Horas)
## Capítulo 68: `enums 📅` (Parte 5 — A Ponte Mágica: `.toUpperCase()`, `Day.valueOf()` e a Execução Interativa Completa)

---

> [!NOTE]
> **Contexto para o Google Docs**: Este documento contém a reconstituição textual completa das **3 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:09:55** e **11:10:10** de 12:00:00). Você não precisa das imagens originais: todos os painéis do IntelliJ IDEA, códigos, encadeamentos de métodos, digitações do usuário no console e a saída formatada foram reconstituídos em blocos de texto fiéis para o seu documento.

---

## 1. Reconstituição Textual das Telas do Vídeo (Google Docs Ready)

### Tela 1: Encadeando o Método `.toUpperCase()` na Leitura (Timestamp: 11:09:55)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:09:55 |
+--------------------------------------------------------------------------------------------------+
|  5            Scanner scanner = new Scanner(System.in);                                          |
|  6            System.out.print("Enter a day of the week: ");                                     |
|  7 💡         String [response] = scanner.nextLine().toUpperCase(); <-- toUpperCase() ADICIONADO!|
|  8                                                                                               |
|  9            Day day = Day.WEDNESDAY;                                                           |
| 10                                                                                               |
| 11            switch(day){ ... }                                                                 |
+--------------------------------------------------------------------------------------------------+
| O autor adicionou o método .toUpperCase() colado no final de scanner.nextLine().                 |
| Qualquer coisa que o usuário digitar no teclado será forçada a ficar em LETRAS MAIÚSCULAS!       |
| A linha 9 ainda mantém temporariamente a quarta-feira fixa (Day.WEDNESDAY).                      |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: A Ponte Mágica: `Day.valueOf(response)` (Timestamp: 11:10:05)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Substituindo o Dia Fixo pela Conversão Real                  Timestamp 11:10:05 |
+--------------------------------------------------------------------------------------------------+
|  7            String response = scanner.nextLine().toUpperCase();                                |
|  8                                                                                               |
|  9 💡         Day day = Day.valueOf(response);  <-- A LINHA MÁGICA QUE CONVERTE TEXTO EM ENUM!   |
| 10                                                                                               |
| 11            switch(day){                                                                       |
| 12                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY                              |
| 13                     -> System.out.println("It is a weekday");                                 |
| 14                case SATURDAY, SUNDAY -> System.out.println("It is the weekend");              |
| 15            }                                                                                  |
+--------------------------------------------------------------------------------------------------+
| O autor apaga Day.WEDNESDAY e coloca: Day.valueOf(response);                                     |
| O mouse do autor se desloca até o botão verde de Play ▶️ no canto superior direito para rodar!   |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: Execução Interativa: Usuário Digita `Monday` e o Console Responde! (Timestamp: 11:10:10)
```text
+--------------------------------------------------------------------------------------------------+
|  Run / Execution Window (Console do IntelliJ)                                 Timestamp 11:10:10 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ... Main                                              |
|                                                                                                  |
|  Enter a day of the week: Monday                                                                 |
|  It is a weekday                                                                                 |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O programa começou e perguntou: "Enter a day of the week: ".                                     |
| O autor digitou no teclado: Monday (com 'M' maiúsculo e 'onday' minúsculo).                      |
| O programa converteu para "MONDAY", achou Day.MONDAY e respondeu em verde: "It is a weekday"!   |
+--------------------------------------------------------------------------------------------------+
```

---

## 2. O Código Completo do Arquivo `Main.java`

Aqui está o código final exato e 100% funcional de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a day of the week: ");
        String response = scanner.nextLine().toUpperCase();

        Day day = Day.valueOf(response);

        switch(day){
            case MONDAY,
                 TUESDAY,
                 WEDNESDAY,
                 THURSDAY,
                 FRIDAY -> System.out.println("It is a weekday");
            case SATURDAY, SUNDAY -> System.out.println("It is the weekend");
        }

        scanner.close();

    }
}
```

---

## 3. Explicação Linha por Linha ("Como se fosse um iniciante amador leigo")

Abaixo está o raio-x completo do código:

### Linha 1: `import java.util.Scanner;`
- **O que faz**: Traz a ferramenta do teclado para dentro do arquivo.
- **Saída no Console**: Nenhuma.

### Linhas 3 e 4: `public class Main { public static void main(...) {`
- Abertura da classe e início da execução.
- **Saída no Console**: Nenhuma.

### Linha 6: `Scanner scanner = new Scanner(System.in);`
- **O que faz**: Cria o ouvinte do teclado.
- **Saída no Console**: Nenhuma.

### Linha 7: `System.out.print("Enter a day of the week: ");`
- **O que faz**: Imprime a pergunta sem quebrar a linha.
- **Saída no Console**:
  ```text
  Enter a day of the week: _
  ```

---

### Linha 8: `String response = scanner.nextLine().toUpperCase();`
Essa linha faz **duas coisas incríveis ao mesmo tempo** através do encadeamento de métodos:

1. **`scanner.nextLine()`**:
   - Captura exatamente o que o usuário digitou no teclado.
   - Suponha que o usuário digitou com preguiça: `"monday"` (tudo minúsculo) ou `"Monday"`.
2. **`.toUpperCase()`**:
   - É um método nativo da classe `String` do Java que significa: **"Converta para Caixa Alta / Letras Maiúsculas"**!
   - Se o usuário digitou `"monday"`, ele transforma em: `"MONDAY"`.
   - Se o usuário digitou `"Monday"`, ele transforma em: `"MONDAY"`.
   - Se o usuário digitou `"mOnDaY"`, ele transforma em: `"MONDAY"`.
3. **`String response = ...`**:
   - Guarda o texto devidamente padronizado em maiúsculas dentro da variável `response`.

> [!TIP]
> **Por que isso é genial?**
> As constantes dentro do nosso arquivo `Day.java` foram escritas em maiúsculas (`MONDAY`, `TUESDAY`...). Se não usássemos `.toUpperCase()`, qualquer usuário que digitasse com letras minúsculas faria o programa quebrar! O `.toUpperCase()` torna o seu programa amigável e tolerante aos humanos!

---

### Linha 10: `Day day = Day.valueOf(response);`
**ESTA É A LINHA MAIS IMPORTANTE DE TODO O CAPÍTULO DE ENUMS!**

- **O que é o método `.valueOf(...)`?**
  - Toda vez que você cria um `enum` em Java (como o nosso `public enum Day`), o Java automaticamente cria por baixo dos panos um método estático chamado `.valueOf(String nome)`.
- **Como ele funciona**:
  - Ele recebe um texto entre parênteses (a nossa variável `response`, que vale `"MONDAY"`).
  - Ele vai até o arquivo `Day.java` e pergunta: *"Existe alguma constante aqui dentro com o nome exato MONDAY?"*.
  - Ele acha a constante `SUNDAY(1), MONDAY(2)...`.
  - Ele pega a constante de verdade (`Day.MONDAY`) e devolve para a nossa variável `day`!
- **A Mágica da Conversão**:
  - Entrou um texto comum (`String`).
  - Saiu um objeto do tipo Enum (`Day`)!
  - **A ponte entre o teclado e o Enum está concluída!**

---

### Linhas 12 a 19: O `switch(day)`
```java
switch(day){
    case MONDAY,
         TUESDAY,
         WEDNESDAY,
         THURSDAY,
         FRIDAY -> System.out.println("It is a weekday");
    case SATURDAY, SUNDAY -> System.out.println("It is the weekend");
}
```
- Agora a variável `day` é um Enum de verdade (`Day.MONDAY`)!
- O `switch` recebe esse Enum sem nenhum erro de compilação.
- Como `MONDAY` está na primeira lista, ele imprime:
  ```text
  It is a weekday
  ```

---

### Linha 21: `scanner.close();`
- Desliga a escuta do teclado e libera a memória do sistema operacional.

---

## 4. O Teste Real: O que Acontece se Você Digitar um Fim de Semana?

Se você rodar o programa e digitar:
```text
Enter a day of the week: sunday
```

### O que o Java faz por dentro:
1. `scanner.nextLine()` lê: `"sunday"`.
2. `.toUpperCase()` converte para: `"SUNDAY"`.
3. `Day.valueOf("SUNDAY")` busca no Enum e devolve: `Day.SUNDAY`.
4. O `switch` ignora os dias úteis e cai no `case SATURDAY, SUNDAY`.
5. **Saída no console**:
   ```text
   It is the weekend
   ```

---

## 5. O que Acontece se o Usuário Digitar Bobagem? (ex: `"pizza"` ou `"banana"`)

Essa é a prova definitiva da segurança dos Enums!

Se alguém rodar o programa e digitar:
```text
Enter a day of the week: pizza
```

1. `.toUpperCase()` transforma em `"PIZZA"`.
2. A linha `Day.valueOf("PIZZA")` vai até o arquivo `Day.java` procurar se existe algum dia da semana chamado `PIZZA`.
3. Como **NÃO EXISTE**, o Java se recusa a continuar e dispara uma exceção em vermelho:
   ```text
   Exception in thread "main" java.lang.IllegalArgumentException: No enum constant Day.PIZZA
   ```
4. **Por que isso é bom para um programador?**
   Porque o Enum impede que dados corrompidos ou falsos entrem no seu sistema! Um dia da semana **só pode ser um dos 7 dias cadastrados**.

---

## 6. Diagrama de Fluxo Visual da Transformação dos Dados

```mermaid
flowchart LR
    A["Teclado do Usuário:<br>'Monday'"] -->|scanner.nextLine()| B["Texto Bruto:<br>'Monday'"]
    B -->|.toUpperCase()| C["Texto Padronizado:<br>'MONDAY'"]
    C -->|Day.valueOf('MONDAY')| D["Objeto Enum Oficial:<br>Day.MONDAY"]
    D -->|switch(day)| E["Console:<br>It is a weekday"]
```

---

## 7. Tabela Comparativa de Entradas do Usuário e Resultados

| O que o Usuário Digita no Console | O que o `.toUpperCase()` gera | O que o `Day.valueOf()` retorna | O que sai no Console? |
| :--- | :--- | :--- | :--- |
| `Monday` | `"MONDAY"` | `Day.MONDAY` | `It is a weekday` |
| `tuesday` | `"TUESDAY"` | `Day.TUESDAY` | `It is a weekday` |
| `FRIDAY` | `"FRIDAY"` | `Day.FRIDAY` | `It is a weekday` |
| `Saturday` | `"SATURDAY"` | `Day.SATURDAY` | `It is the weekend` |
| `sunday` | `"SUNDAY"` | `Day.SUNDAY` | `It is the weekend` |
| `Carnaval` | `"CARNAVAL"` | *(Não existe no Enum!)* | `IllegalArgumentException` (Erro!) |

---

## 8. Checklist de Fixação Mental para Iniciantes

1. **Por que adicionamos `.toUpperCase()`?** Porque as constantes do Enum são escritas em MAIÚSCULAS (`MONDAY`), e os humanos costumam digitar com letras minúsculas.
2. **De onde veio o método `Day.valueOf()`?** Ele é gerado automaticamente pelo compilador Java para todo e qualquer Enum.
3. **Para que serve `Day.valueOf(texto)`?** Converte uma `String` (texto) no objeto `Enum` correspondente.
4. **Por que o autor apagou `Day.WEDNESDAY` da linha 10?** Porque agora o dia não é mais fixo no código; ele vem da resposta do teclado do usuário!
5. **Qual foi o resultado da execução na Tela 3?** O autor digitou `Monday` e o console respondeu `It is a weekday`.
