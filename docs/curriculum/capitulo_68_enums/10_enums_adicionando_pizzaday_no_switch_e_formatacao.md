# Curso de Java — Bro Code (12 Horas)
## Capítulo 68: `enums 📅` (Parte 9 — Integrando o `PIZZADAY` no `switch` e a Formatação Limpa em Múltiplas Linhas)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa das **4 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:11:38** e **11:11:50** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: todas as posições de cursor, digitações no editor, quebras de linha para legibilidade, indicadores de análise sintática (`Parsing java...`) e consoles foram reconstituídos em formato de texto para leitura independente das imagens originais.

---

## 1. O Que o Autor Fez Aqui? (História Explicada para Iniciantes)

Na aula anterior (Parte 8), o Bro Code foi até o arquivo `Day.java` e registrou o novo membro da família dos dias: o `PIZZADAY(8)`.

Agora, o autor clica de volta na aba **`Main.java`**.
Chegou a hora de responder à grande pergunta:
> *"Onde o Dia da Pizza deve se encaixar na nossa rotina? Ele é dia de trabalho ou dia de descanso?"*

O autor decide: **o Dia da Pizza faz parte do fim de semana!**

### A Analogia da Lista de Convidados da Festa
1. **O Cartório já carimbou**: No passo anterior, o `PIZZADAY` ganhou certidão de nascimento oficial em `Day.java`.
2. **A Portaria do Fim de Semana**: No `switch`, nós temos o grupo dos fins de semana:
   ```java
   case SATURDAY, SUNDAY -> System.out.println("It is the weekend");
   ```
3. **Adicionando o Convidado VIP**: O autor simplesmente coloca uma vírgula e escreve `PIZZADAY` ao lado de `SUNDAY`:
   ```java
   case SATURDAY, SUNDAY, PIZZADAY -> System.out.println("It is the weekend");
   ```
4. **Por que o Java NÃO deu erro vermelho?**
   Porque o compilador olha para `PIZZADAY`, vai até `Day.java`, encontra ele lá e diz: *"Tudo certo! Essa constante existe de verdade!"*.
   Se o autor tivesse tentado escrever `PIZZADAY` **antes** de criar em `Day.java`, o IntelliJ teria pintado a palavra de vermelho berrante com o erro: `Cannot resolve symbol 'PIZZADAY'`.
5. **O Toque de Mestre (Legibilidade)**: Como a linha estava ficando muito comprida, o autor aperta `[Enter]` e alinha cada dia um embaixo do outro:
   ```java
   case SATURDAY,
        SUNDAY,
        PIZZADAY -> System.out.println("It is the weekend");
   ```

---

## 2. Reconstituição Textual das 4 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: Retornando a `Main.java` e Posicionando o Cursor (Timestamp: 11:11:38)

O autor volta da aba `Day.java` para a aba `Main.java`. O cursor está na linha 19, piscando logo após a palavra `SUNDAY`:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:38 |
+--------------------------------------------------------------------------------------------------+
| 13            switch(day){                                                                       |
| 14                case MONDAY,                                                                   |
| 15                     TUESDAY,                                                                  |
| 16                     WEDNESDAY,                                                                |
| 17                     THURSDAY,                                                                 |
| 18                     FRIDAY -> System.out.println("It is a weekday");                          |
| 19  👉            case SATURDAY, SUNDAY| -> System.out.println("It is the weekend");             |
| 20            }                                                                                  |
| 21                                                                                               |
| 22            catch(IllegalArgumentException e){                                                 |
| 23                System.out.println("Please enter a valid day");                                |
| 24            }                                                                                  |
+--------------------------------------------------------------------------------------------------+
| POSIÇÃO DO CURSOR: O autor clica exatamente entre SUNDAY e a setinha -> pronto para digitar.    |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: Digitando `, PIZZADAY` na Mesma Linha (Timestamp: 11:11:44)

O autor digita uma vírgula e a palavra `PIZZADAY`. Veja que o IntelliJ colore a palavra na cor correta de constante (rosa/púrpura) sem nenhum erro:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:44 |
+--------------------------------------------------------------------------------------------------+
| 18                     FRIDAY -> System.out.println("It is a weekday");                          |
| 19  👉            case SATURDAY, SUNDAY, PIZZADAY| -> System.out.println("It is the weekend");   |
| 20            }                                                                                  |
| 21                                                                                               |
| 22            catch(IllegalArgumentException e){                                                 |
+--------------------------------------------------------------------------------------------------+
| SEM ERRO VERMELHO: Como PIZZADAY(8) foi registrado em Day.java, o IntelliJ valida na hora!       |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: Quebrando em Linhas Separadas para Legibilidade (Timestamp: 11:11:49)

O autor prefere um código limpo e organizado. Ele pressiona `[Enter]` após `SATURDAY,` e após `SUNDAY,`, colocando cada dia em sua própria linha alinhada. No canto inferior direito, o IntelliJ exibe o aviso temporário `Parsing java...`:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:49 |
+--------------------------------------------------------------------------------------------------+
| 18                     FRIDAY -> System.out.println("It is a weekday");                          |
| 19  👉            case SATURDAY,                                                                 |
| 20  👉                 SUNDAY,                                                                   |
| 21  👉                 PIZZADAY -> System.out.println("It is the weekend");                      |
| 22            }                                                                                  |
| 23                                                                                               |
| 24            catch(IllegalArgumentException e){                                                 |
| 25                System.out.println("Please enter a valid day");                                |
| 26            }                                                                                  |
| 27                                                                                               |
| 28            scanner.close();                                                                   |
+--------------------------------------------------------------------------------------------------+
| RODAPÉ DIREITO: [ Parsing java... [MyFirstProject] ] <-- O IntelliJ confere a sintaxe em tempo real|
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: Código Final Limpo e Totalmente Validado (Timestamp: 11:11:50)

O IntelliJ conclui a análise de sintaxe. Não existe nenhuma linha vermelha, nenhum aviso e nenhuma advertência. O código está 100% pronto para compilar e rodar:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:50 |
+--------------------------------------------------------------------------------------------------+
| 13            switch(day){                                                                       |
| 14                case MONDAY,                                                                   |
| 15                     TUESDAY,                                                                  |
| 16                     WEDNESDAY,                                                                |
| 17                     THURSDAY,                                                                 |
| 18                     FRIDAY -> System.out.println("It is a weekday");                          |
| 19                case SATURDAY,                                                                 |
| 20                     SUNDAY,                                                                   |
| 21                     PIZZADAY -> System.out.println("It is the weekend");                      |
| 22            }                                                                                  |
+--------------------------------------------------------------------------------------------------+
| PERFEIÇÃO: MONDAY a FRIDAY são dias úteis; SATURDAY, SUNDAY e PIZZADAY são finais de semana!     |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Os Dois Códigos Oficiais do Projeto

### Arquivo 1: `Day.java` (O Enum com o Dia da Pizza)

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

### Arquivo 2: `Main.java` (O Switch Completo com o Dia da Pizza)

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

## 4. Explicação Linha por Linha de `Main.java` (com Numeração e Saída do Console)

Vamos passear por cada linha do arquivo com calma, explicando a lógica como se estivéssemos ensinando um amigo leigo:

### Linha 1: `import java.util.Scanner;`
* **O que faz**: Traz a ferramenta `Scanner` da biblioteca oficial do Java para podermos ler o teclado.
* **O que sai no console**: Nada.

### Linha 3: `public class Main {`
* **O que faz**: Declara a classe principal onde mora todo o nosso código.
* **O que sai no console**: Nada.

### Linha 4: `public static void main(String[] args) {`
* **O que faz**: O ponto de partida de qualquer aplicação Java. Quando apertamos o play, a execução começa aqui.
* **O que sai no console**: Nada.

### Linha 6: `Scanner scanner = new Scanner(System.in);`
* **O que faz**: Liga o leitor de teclado (`System.in`) e guarda o controle dele na variável chamada `scanner`.
* **O que sai no console**: Nada.

### Linha 7: `System.out.print("Enter a day of the week: ");`
* **O que faz**: Exibe a mensagem pedindo que o usuário digite um dia da semana. Como usa `print` e não `println`, o cursor não pula de linha e fica esperando bem ao lado.
* **O que sai no console**:
  ```text
  Enter a day of the week: 
  ```

### Linha 8: `String response = scanner.nextLine().toUpperCase();`
* **O que faz**: Espera o usuário digitar uma palavra e teclar Enter (`nextLine()`), converte todas as letras para caixa alta (`.toUpperCase()`) e guarda na variável `response`.
  * Se o usuário digitar `pizzaday`, ela vira `"PIZZADAY"`.
  * Se o usuário digitar `Sunday`, ela vira `"SUNDAY"`.
* **O que sai no console**: As letras que o usuário for digitando no teclado.

---

### Linha 10: `try{`
* **O que faz**: Abre o escudo protetor. Qualquer erro que acontecer dentro desse bloco será interceptado antes que o programa trave.
* **O que sai no console**: Nada.

### Linha 11: `Day day = Day.valueOf(response);`
* **O que faz**: A ponte mágica! Procura no arquivo `Day.java` se existe uma constante com o mesmo nome do texto guardado em `response`.
  * Se o usuário digitou `"PIZZADAY"`: O Java procura em `Day.java`, **encontra** a constante `Day.PIZZADAY` que adicionamos na aula passada e guarda ela dentro da variável `day`!
  * Se o usuário digitou `"HAMBURGUER"`: O Java não encontra e joga o erro `IllegalArgumentException`, fazendo o código pular direto para a linha 24 (`catch`).
* **O que sai no console**: Nada.

### Linha 13: `switch(day){`
* **O que faz**: Começa a estrutura de decisão que vai avaliar qual é o dia da semana contido na variável `day`.
* **O que sai no console**: Nada.

### Linhas 14 a 18: O Bloco dos Dias Úteis
```java
case MONDAY,
     TUESDAY,
     WEDNESDAY,
     THURSDAY,
     FRIDAY -> System.out.println("It is a weekday");
```
* **O que faz**: Se `day` for Segunda, Terça, Quarta, Quinta ou Sexta, o Java executa o comando após a seta `->`, imprimindo que é um dia útil.
* **O que sai no console** (se for dia útil):
  ```text
  It is a weekday
  ```

---

### Linhas 19 a 21: O Novo Bloco do Fim de Semana (Com `PIZZADAY`)
```java
case SATURDAY,
     SUNDAY,
     PIZZADAY -> System.out.println("It is the weekend");
```
* **O que faz**: **Esta é a grande novidade da aula!**
  * Se `day` for Sábado (`SATURDAY`), Domingo (`SUNDAY`) ou o novo Dia da Pizza (`PIZZADAY`), o Java aciona a seta `->` e imprime a mensagem de fim de semana!
* **O que sai no console** (quando o usuário digita `Pizzaday`):
  ```text
  It is the weekend
  ```

### Linha 22: `}` (Fim do Switch)
* **O que faz**: Fecha o comando `switch`.
* **O que sai no console**: Nada.

### Linha 23: `}` (Fim do Try)
* **O que faz**: Fecha o bloco protegido do `try`. Como deu tudo certo, o Java pula o `catch` e vai direto para a linha 28.
* **O que sai no console**: Nada.

---

### Linha 24: `catch(IllegalArgumentException e){`
* **O que faz**: O colchão inflável. Só é acionado se o usuário digitar algo que realmente não existe no Enum (como `"Pastelday"` ou `"12345"`).
* **O que sai no console**: Nada.

### Linha 25: `System.out.println("Please enter a valid day");`
* **O que faz**: Mensagem amigável impressa quando ocorre uma exceção.
* **O que sai no console** (somente para palavras inválidas):
  ```text
  Please enter a valid day
  ```

### Linha 26: `}` (Fim do Catch)
* **O que faz**: Fecha o bloco de captura de erro.
* **O que sai no console**: Nada.

---

### Linha 28: `scanner.close();`
* **O que faz**: Desliga o leitor de teclado para economizar recursos do computador.
* **O que sai no console**: Nada.

### Linhas 29 e 30: `}` e `}`
* **O que faz**: Fecham o método `main` e a classe `Main`. O programa termina limpo com `exit code 0`.
* **O que sai no console**: Nada adicional.

---

## 5. Por que Separar em Múltiplas Linhas é uma Boa Prática?

Observe a comparação:

### Estilo em Uma Única Linha (Linha Linguiça):
```java
case SATURDAY, SUNDAY, PIZZADAY -> System.out.println("It is the weekend");
```
* **Problema**: Conforme o seu programa cresce e novos dias ou opções são adicionados, essa linha fica longa demais, ultrapassa a largura da tela do editor e obriga o programador a rolar a barra horizontal (*scroll*).

### Estilo em Múltiplas Linhas (Código Limpo do Bro Code):
```java
case SATURDAY,
     SUNDAY,
     PIZZADAY -> System.out.println("It is the weekend");
```
* **Vantagem 1 (Legibilidade)**: Bateu o olho, você enxerga claramente cada uma das 3 opções.
* **Vantagem 2 (Manutenção)**: Se amanhã você quiser remover o `PIZZADAY` ou adicionar o `BARBECUEDAY`, basta adicionar ou apagar uma única linha sem bagunçar o resto.
* **Vantagem 3 (Controle de Versão / Git)**: Sistemas como Git mostram exatamente qual linha mudou, em vez de dizer que a linha inteira foi modificada.

---

## 6. Tabela de Testes do Console com o `PIZZADAY` Oficializado

Veja o que acontece na prática quando executamos o programa agora:

| Entrada do Usuário | O que o Java Faz por Baixo dos Panos | Saída Exata Impressa no Console | Código de Saída |
| :--- | :--- | :--- | :---: |
| `Tuesday` | Vira `TUESDAY`, acha em `Day.java`, cai no caso dos dias úteis. | `Enter a day of the week: Tuesday`<br>`It is a weekday` | `0` (Sucesso) |
| `Sunday` | Vira `SUNDAY`, acha em `Day.java`, cai no caso do fim de semana. | `Enter a day of the week: Sunday`<br>`It is the weekend` | `0` (Sucesso) |
| **`Pizzaday`** | **Vira `PIZZADAY`, acha em `Day.java`, cai no novo caso do fim de semana!** | `Enter a day of the week: Pizzaday`<br>`It is the weekend` | `0` (Sucesso) |
| `PIZZADAY` | Já está maiúsculo, acha em `Day.java`, cai no novo caso do fim de semana! | `Enter a day of the week: PIZZADAY`<br>`It is the weekend` | `0` (Sucesso) |
| `Hamburguer` | Vira `HAMBURGUER`, **não acha** em `Day.java`, cai no `catch`. | `Enter a day of the week: Hamburguer`<br>`Please enter a valid day` | `0` (Sucesso) |

---

## 7. Conclusão Desta Aula

Com esta aula, você completou o ciclo de ouro do aprendizado de Enums:
1. **Modelagem**: Criou o Enum [`Day`](../../../src/main/java/com/mickaelgeronimo/mastery/core/models/Day.java).
2. **Expansão**: Adicionou uma nova constante [`PIZZADAY(8)`](../../../src/main/java/com/mickaelgeronimo/mastery/core/models/Day.java#L3).
3. **Consumo no Switch**: Usou a constante no [`switch`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java#L13) com sintaxe moderna e alinhamento profissional.
4. **Proteção**: Manteve o `try / catch` ativo para blindar o sistema contra qualquer outra palavra que não seja um dia válido.
