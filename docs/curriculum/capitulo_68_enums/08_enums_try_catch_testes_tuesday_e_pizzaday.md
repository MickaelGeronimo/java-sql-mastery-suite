# Curso de Java — Bro Code (12 Horas)
## Capítulo 68: `enums 📅` (Parte 7 — O Escudo Protetor `try/catch`, Teste Feliz com `Tuesday` e Teste Blindado com `Pizzaday`)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa e fiel das **4 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:11:00** e **11:11:21** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: todos os códigos, painéis do IntelliJ IDEA, digitações no console e mensagens de encerramento foram desenhados em blocos de texto para que sua apostila fique 100% compreensível sem depender das imagens originais.

---

## 1. O Que o Autor Fez Aqui? (História Explicada para Iniciantes)

Imagine que o nosso programa de computador é como um **garçom de restaurante**:

1. **O Cardápio Fechado**: O restaurante só serve 7 pratos fixos: Domingo, Segunda, Terça, Quarta, Quinta, Sexta e Sábado (o nosso Enum `Day`).
2. **O Pedido do Cliente**: O cliente chega e faz o pedido pelo teclado (`scanner.nextLine()`).
3. **O Que Acontecia Antes (Sem Proteção)**:
   - Se o cliente pedia `"Pizzaday"`, o garçom entrava em desespero total, jogava os pratos no chão, gritava: `IllegalArgumentException: No enum constant Day.PIZZADAY` e desmaiava! O restaurante fechava as portas na hora com erro (`Exit code 1`).
4. **O Que o Autor Fez Agora (Com o Escudo Protetor `try / catch`)**:
   - O autor ensinou o garçom a ter modos e calma através do bloco `try / catch`.
   - **`try` (Tente)**: *"Garçom, tente buscar o prato que o cliente pediu no cardápio."*
   - **`catch` (Capture o susto)**: *"Se o cliente pediu algo que não existe no cardápio (`IllegalArgumentException`), não entre em pânico! Respire fundo e diga com calma: 'Por favor, digite um dia válido'."*
   - **Resultado Maravilhoso**: O programa atende o cliente, não quebra, não solta mensagens vermelhas assustadoras e fecha o expediente normalmente com sucesso (`Exit code 0`)!

---

## 2. Reconstituição Textual das 4 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: O Código Completo com `try / catch` Finalizado (Timestamp: 11:11:00)

O autor terminou de digitar a linha 23 (`System.out.println("Please enter a valid day");`). O cursor piscando está logo após a mensagem. Não há nenhum erro vermelho na tela.

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:11:00 |
+--------------------------------------------------------------------------------------------------+
| 10  👉        try{                                                                               |
| 11                Day day = Day.valueOf(response);                                               |
| 12                                                                                               |
| 13                switch(day){                                                                   |
| 14                    case MONDAY,                                                               |
| 15                         TUESDAY,                                                              |
| 16                         WEDNESDAY,                                                            |
| 17                         THURSDAY,                                                             |
| 18                         FRIDAY -> System.out.println("It is a weekday");                      |
| 19                    case SATURDAY, SUNDAY -> System.out.println("It is the weekend");          |
| 20                }                                                                              |
| 21            }                                                                                  |
| 22  👉        catch(IllegalArgumentException e){                                                 |
| 23  💡            System.out.println("Please enter a valid day");| <-- CURSOR DO AUTOR AQUI      |
| 24            }                                                                                  |
| 25                                                                                               |
| 26            scanner.close();                                                                   |
| 27        }                                                                                      |
| 28    }                                                                                          |
+--------------------------------------------------------------------------------------------------+
| OBSERVAÇÃO DO PAINEL:                                                                            |
| O código está limpo, compilando perfeitamente e pronto para ser executado no botão verde "Run"!  |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: Iniciando o Programa e Aguardando Digitação (Timestamp: 11:11:04)

O autor clica no botão verde de Play. O painel inferior do IntelliJ abre o console de execução. A pergunta é impressa na tela e o cursor fica piscando aguardando o usuário:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Run > Main)                             Timestamp 11:11:04 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ...   |
|                                                                                                  |
|  Enter a day of the week: | <-- CURSOR VERDE PISCANDO AGUARDANDO DIGITAÇÃO                       |
|                                                                                                  |
|                                                                                                  |
+--------------------------------------------------------------------------------------------------+
| O programa está parado nesta linha porque o método scanner.nextLine() está esperando o usuário   |
| digitar alguma coisa no teclado e pressionar a tecla [ENTER].                                    |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: O Teste de Sucesso digitando `Tuesday` (Timestamp: 11:11:09)

O autor digita `Tuesday` e aperta Enter. O programa reconhece como dia útil, imprime `"It is a weekday"` e encerra com código zero:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Teste 1: Dia Válido)                    Timestamp 11:11:09 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...                                                   |
|                                                                                                  |
|  Enter a day of the week: Tuesday                                                                |
|  It is a weekday                                                                                 |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O QUE ACONTECEU AQUI?                                                                            |
| 1. O usuário digitou "Tuesday" (com 'T' maiúsculo e o resto minúsculo).                          |
| 2. O .toUpperCase() transformou em "TUESDAY".                                                    |
| 3. O Day.valueOf("TUESDAY") achou o Enum correspondente em Day.java com sucesso.                |
| 4. O switch caiu no case TUESDAY e imprimiu "It is a weekday".                                   |
| 5. O bloco catch foi totalmente IGNORADO (porque não houve erro).                               |
| 6. O scanner foi fechado e o programa terminou com Sucesso Total (exit code 0).                  |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: O Teste com Entrada Inválida digitando `Pizzaday` (Timestamp: 11:11:21)

O autor executa o programa novamente e digita a palavra inventada `Pizzaday`. Em vez de travar com texto vermelho, o escudo `try/catch` entra em ação:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Console de Execução (Teste 2: Dia Inválido)                  Timestamp 11:11:21 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...                                                   |
|                                                                                                  |
|  Enter a day of the week: Pizzaday                                                               |
|  Please enter a valid day                                                                        |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| A GRANDE VITÓRIA DO TRY/CATCH:                                                                   |
| 1. O usuário digitou "Pizzaday".                                                                 |
| 2. O .toUpperCase() transformou em "PIZZADAY".                                                   |
| 3. O Day.valueOf("PIZZADAY") procurou em Day.java e NÃO ACHOU.                                   |
| 4. O Java disparou o erro: IllegalArgumentException!                                             |
| 5. O try { ... } impediu que o erro destruísse o programa!                                       |
| 6. O catch(IllegalArgumentException e) CAPTUROU o erro no ar!                                   |
| 7. O catch mandou imprimir: "Please enter a valid day".                                          |
| 8. O programa saiu do catch, fechou o scanner e terminou com sucesso: exit code 0!               |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Código-Fonte Completo e Atualizado (`Main.java`)

Este é o código idêntico ao que está no editor do instrutor:

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

## 4. Explicação Linha por Linha (Com Numeração e Saída do Console)

Vamos destrinchar cada linha do código com extrema calma e detalhes:

### Linha 1: `import java.util.Scanner;`
* **Explicação**: Avisa ao compilador Java que vamos usar a classe pronta `Scanner`, que mora no pacote utilitário do Java (`java.util`). Sem esse aviso, o Java não saberia o que é um `Scanner`.
* **O que sai no console**: Absolutamente nada. Essa linha é apenas um aviso prévio para o compilador.

### Linha 3: `public class Main {`
* **Explicação**: Abre a nossa classe pública chamada `Main`. Todo programa Java vive obrigatoriamente dentro de uma classe, e o nome dela deve coincidir exatamente com o nome do arquivo (`Main.java`).
* **O que sai no console**: Nada.

### Linha 4: `public static void main(String[] args) {`
* **Explicação**: É o método principal, o coração inicial do Java. É a primeira coisa que a JVM executa quando o programa é iniciado.
* **O que sai no console**: Nada.

### Linha 6: `Scanner scanner = new Scanner(System.in);`
* **Explicação**: Cria uma nova instância (`new`) da ferramenta leitora de teclado e guarda na variável `scanner`. O `System.in` é o canal que escuta as teclas que você digita no teclado do seu computador.
* **O que sai no console**: Nada visível, mas a memória do computador já aloca espaço para ouvir o teclado.

### Linha 7: `System.out.print("Enter a day of the week: ");`
* **Explicação**: Escreve o texto de orientação na tela. Por usar `print` (e não `println`), o Java **não pula de linha**; ele mantém o cursor piscando coladinho nos dois pontos `": "` aguardando você digitar.
* **O que sai no console**:
  ```text
  Enter a day of the week: 
  ```

### Linha 8: `String response = scanner.nextLine().toUpperCase();`
* **Explicação**: Aqui acontecem três passos em cadeia:
  1. `scanner.nextLine()`: Pausa o programa e espera você digitar um texto e teclar `[Enter]`.
  2. `.toUpperCase()`: Pega as letras do que você acabou de digitar e converte todas para MAIÚSCULAS (ex: `"tuesday"` vira `"TUESDAY"`).
  3. `String response = ...`: Guarda o texto em maiúsculas na gaveta de texto chamada `response`.
* **O que sai no console**: Mostra as letras que você está digitando no terminal enquanto digita (por exemplo, `Tuesday` ou `Pizzaday`).

---

### Linha 10: `try{` (Início da Zona de Proteção)
* **Explicação**: A palavra reservada `try` abre um bloco de "tentativa". É como dizer ao Java: *"Execute o código que vem a seguir dentro dessas chaves. Se der tudo certo, ótimo! Mas se qualquer linha aqui dentro der um erro inesperado, não trave o programa: desvie a execução imediatamente para o bloco `catch` correspondente!"*
* **O que sai no console**: Nada.

### Linha 11: `Day day = Day.valueOf(response);`
* **Explicação**: Esta é a linha mais crítica de todo o programa!
  * O método `Day.valueOf(...)` pega a palavra que está em `response` e procura no arquivo `Day.java` se existe uma constante com aquele exato nome.
  * **Se achar** (ex: `"TUESDAY"`): Ele devolve a constante `Day.TUESDAY` e guarda na variável `day`. O programa vai em paz para a linha 13.
  * **Se NÃO achar** (ex: `"PIZZADAY"`): O método grita e atira um erro do tipo `IllegalArgumentException`! Mas como esta linha está cercada pelo `try`, o Java **não quebra**; ele cancela a execução das linhas 13 a 20 e pula direto para a linha 22!
* **O que sai no console**: Nada.

### Linha 13: `switch(day){`
* **Explicação**: Avalia qual valor de Enum está dentro da gaveta `day` para direcionar o programa para a rota certa.
* **O que sai no console**: Nada.

### Linhas 14 a 18:
```java
case MONDAY,
     TUESDAY,
     WEDNESDAY,
     THURSDAY,
     FRIDAY -> System.out.println("It is a weekday");
```
* **Explicação**: Se `day` for qualquer um dos 5 dias úteis (Segunda, Terça, Quarta, Quinta ou Sexta), a flechinha moderna (`->`) executa a linha de impressão informando que é um dia de semana.
* **O que sai no console** (quando digitado `Tuesday`):
  ```text
  It is a weekday
  ```

### Linha 19: `case SATURDAY, SUNDAY -> System.out.println("It is the weekend");`
* **Explicação**: Se `day` for Sábado ou Domingo, a flechinha executa a linha avisando que é fim de semana.
* **O que sai no console** (quando digitado `Saturday` ou `Sunday`):
  ```text
  It is the weekend
  ```

### Linha 20: `}` (Fim do Switch)
* **Explicação**: Fecha o bloco do comando `switch`.
* **O que sai no console**: Nada.

### Linha 21: `}` (Fim da Zona de Proteção `try`)
* **Explicação**: Fecha a zona protegida do `try`. Se chegamos aqui sem nenhum erro (como aconteceu com o `Tuesday`), o Java **pula completamente** o bloco `catch` e vai direto para a linha 26!
* **O que sai no console**: Nada.

---

### Linha 22: `catch(IllegalArgumentException e){` (O Paraquedas de Emergência)
* **Explicação**: A palavra `catch` (capturar) só é acionada se o `try` sofreu uma explosão!
  * O parâmetro `(IllegalArgumentException e)` especifica qual tipo de erro este paraquedas sabe segurar.
  * Se o usuário digitou `"Pizzaday"`, a linha 11 tentou fazer `Day.valueOf("PIZZADAY")`, não encontrou e disparou o `IllegalArgumentException`. O `catch` interceptou esse erro no ar e segurou ele dentro da variável `e`!
* **O que sai no console**: Nada.

### Linha 23: `System.out.println("Please enter a valid day");`
* **Explicação**: Esta linha **só é executada quando o usuário digita uma entrada inválida**. Ela imprime uma mensagem clara, educada e amigável para o usuário no terminal, no lugar daquela mensagem técnica vermelha feia da JVM.
* **O que sai no console** (quando digitado `Pizzaday`):
  ```text
  Please enter a valid day
  ```

### Linha 24: `}` (Fim do Bloco `catch`)
* **Explicação**: Fecha o bloco de tratamento de erro. O Java agora retoma o fluxo normal da vida e passa para a linha seguinte.
* **O que sai no console**: Nada.

---

### Linha 26: `scanner.close();`
* **Explicação**: Encerra o leitor de teclado e devolve a posse dele para o sistema operacional.
* **Por que esta linha está fora do `try / catch`?**
  Porque fechar o `scanner` é uma obrigação do programa **tanto na alegria quanto na tristeza**! Se o dia digitado foi válido (`Tuesday`), o `scanner` precisa fechar. Se o dia digitado foi uma loucura (`Pizzaday`), o `scanner` também precisa fechar! Por isso ele fica do lado de fora, garantindo que sempre será executado.
* **O que sai no console**: Nada.

### Linha 27: `}`
* **Explicação**: Fecha o método `main`.
* **O que sai no console**: Nada.

### Linha 28: `}`
* **Explicação**: Fecha a classe `Main`. O programa é encerrado pelo Java com status de sucesso (`Process finished with exit code 0`).
* **O que sai no console**: A linha de encerramento do IntelliJ IDEA.

---

## 5. Rastreamento Passo a Passo Lado a Lado: `Tuesday` vs `Pizzaday`

Veja o mapa mental exato do que a CPU do computador faz em cada caso:

```text
====================================================================================================
FLUXO COM "Tuesday" (Caminho Feliz)            | FLUXO COM "Pizzaday" (Caminho do Erro Tratado)
====================================================================================================
1. scanner.nextLine() lê "Tuesday"             | 1. scanner.nextLine() lê "Pizzaday"
2. .toUpperCase() vira "TUESDAY"               | 2. .toUpperCase() vira "PIZZADAY"
3. ENTRA NO try {                              | 3. ENTRA NO try {
4. Day.valueOf("TUESDAY") -> ACHA!             | 4. Day.valueOf("PIZZADAY") -> NÃO ACHA!
5. day recebe Day.TUESDAY                      | 5. DISPARA: IllegalArgumentException!
6. switch(day) casa com TUESDAY                | 6. O try intercepta a explosão!
7. IMPRIME: "It is a weekday"                  | 7. PULA direto para o catch(...)
8. PULA O catch { ... } INTEIRO                | 8. IMPRIME: "Please enter a valid day"
9. scanner.close() executa normalmente         | 9. scanner.close() executa normalmente
10. Exit code 0 (Sucesso!)                     | 10. Exit code 0 (Sucesso! Sem travar!)
====================================================================================================
```

---

## 6. Vocabulário Descomplicado para Leigos

* **`try`**: Bloco de teste onde colocamos códigos que têm risco de falhar dependendo do que o usuário fizer.
* **`catch`**: Bloco de resgate que só roda se o código de dentro do `try` falhar.
* **`Exception` (Exceção)**: Um evento anormal que acontece durante a execução do programa e interrompe o fluxo normal se não for tratado.
* **`IllegalArgumentException`**: É o nome oficial da exceção que o Java usa para dizer: *"O argumento (valor) que você me passou não é aceitável!"* No nosso caso, `"PIZZADAY"` não é um argumento aceitável para o Enum `Day`.
* **`Exit code 0`**: O número que o computador mostra quando um programa começa, roda e termina sem nenhum travamento (0 = Sucesso Absoluto).
* **`Exit code 1`**: O número que o computador mostra quando um programa "bateu o carro", quebrou ou foi forçado a fechar por um erro fatal.

---

## 7. Conclusão Desta Aula

Com essa técnica de envolver a conversão `Day.valueOf()` com `try / catch`:
1. Você aprendeu a diferença entre um código amador (que trava na cara do usuário com mensagens vermelhas) e um código profissional (que trata o erro e fala educadamente).
2. Você entendeu o papel indispensável do `IllegalArgumentException`.
3. Você dominou a posição correta do `scanner.close()` para evitar vazamento de memória (*resource leak*).
