# Curso de Java Completo (Bro Code) — Capítulo 65: `⭐ countdown timer ⏳`
## Projeto Prático Completo: `Scanner`, `scheduleAtFixedRate` e Contagem Regressiva de Ano Novo!

> **Contexto no Vídeo**: No tempo **`10:35:00`** a **`10:35:50 / 12:00:00`**, o instrutor **Bro Code** apresenta um projeto prático completo marcado com a estrela dourada (**⭐**): o **`countdown timer ⏳`** (Temporizador de Contagem Regressiva).  
> Ele une todo o conhecimento adquirido nos capítulos anteriores em um programa interativo de verdade:  
> 1. Cria um protótipo com 5 segundos fixos.  
> 2. Mostra um erro comum (usar `.schedule(task, 0)` sem o período) e corrige usando o método profissional **`.scheduleAtFixedRate(task, 0, 1000)`** para garantir precisão absoluta no relógio.  
> 3. Adiciona interatividade com **`Scanner`**, permitindo que o usuário digite de quantos segundos quer a contagem regressiva (no vídeo, ele digita `10`).  
> 4. O programa conta de 10 até 0 em tempo real (1 segundo por tick) e explode com a mensagem comemorativa: **`HAPPY NEW YEAR!`**!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 5 telas do IntelliJ IDEA e do YouTube, na ordem pedagógica dos testes.

```
====================================================================================================
IMAGEM 3: Estrutura do Protótipo com 5 Segundos (Tempo do Vídeo: 10:35:00)
====================================================================================================
Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Main
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (Main.java):
 7:         // Java COUNTDOWN TIMER PROGRAM
 8: 
 9:         Timer timer = new Timer();  <-- [Palavra 'timer' selecionada em azul]
10:         TimerTask task = new TimerTask(){
11: 
12:             int count = 5;  <-- [Contagem inicial fixa em 5 segundos]
13: 
14:             @Override
15: ⎘          public void run(){
16: 
17:                 System.out.println(count);
18:                 count--;
19:                 if(count < 0){
20:                     System.out.println("HAPPY NEW YEAR!");
21:                     timer.cancel();
22:                 }
23:             }
24:         };

Análise Visual da Imagem 3:
- O autor começa montando a estrutura base.
- Repare no comentário da linha 7: "// Java COUNTDOWN TIMER PROGRAM".
- Repare na linha 19: `if(count < 0)` — ele usa menor estrito (`< 0`) e não menor ou igual (`<= 0`) para que o número 0 ainda seja impresso na tela!
====================================================================================================
```

```
====================================================================================================
IMAGEM 4: O Erro Didático — Chamando schedule com Apenas 2 Parâmetros (Tempo: 10:35:09)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:35:09 / 12:00:00    65.countdown timer >

Editor de Código (Linha 26):
24:         };
25: 
26:         timer.schedule(task, 0);  <-- [ERRO DIDÁTICO: Falta o período de repetição!]
27: 
28:     }
29: }

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  5  <-- [Imprimiu apenas o número 5 e parou!]

Análise Visual da Imagem 4:
- O autor digitou `timer.schedule(task, 0);` e rodou.
- Como ele passou apenas 2 parâmetros (tarefa e atraso inicial zero), o cronômetro rodou uma única vez, imprimiu `5` e não continuou a contagem regressiva!
====================================================================================================
```

```
====================================================================================================
IMAGEM 5: A Correção Profissional — 'scheduleAtFixedRate' (Tempo: 10:35:50)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:35:50 / 12:00:00    65.countdown timer >

Editor de Código (Linhas 12 a 27):
12:             int count = 5;  <-- [Texto selecionado em azul]
...
26:         timer.scheduleAtFixedRate(task, 0, 1000); //(task, delay, period)
27: 
28:     }
29: }

Análise Visual da Imagem 5:
- O autor corrige a linha 26 para o método profissional `timer.scheduleAtFixedRate(task, 0, 1000);`.
- Ele adiciona um comentário explicativo no final: `//(task, delay, period)`.
- `scheduleAtFixedRate` é o método perfeito para relógios porque ele compensa pequenos atrasos do sistema operacional, mantendo a contagem rigorosamente a cada 1 segundo (1000ms).
====================================================================================================
```

```
====================================================================================================
IMAGEM 1: Tornando Dinâmico com Scanner — O Usuário Digita 10 (Tempo: 10:36:10)
====================================================================================================
Editor de Código (Linhas 16 a 22):
16:         TimerTask task = new TimerTask(){
17: 
18:             int count = response;  <-- [AGORA RECEBE A RESPOSTA DO USUÁRIO!]
19: 
20:             @Override
21: ⎘          public void run(){

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Enter # of seconds to countdown from: 10  <-- [O usuário digitou 10 no teclado]
  10
  9
  8|  <-- [Cursor piscando enquanto a contagem regressiva acontece ao vivo!]

Análise Visual da Imagem 1:
- O autor substituiu o número fixo `5` por `response`, vindo de um `Scanner`.
- O console pergunta: "Enter # of seconds to countdown from: ".
- O autor digitou `10`.
- O cronômetro começou imediatamente e já desceu de 10 para 9 e para 8!
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: O Clímax da Contagem e o 'HAPPY NEW YEAR!' (Tempo: 10:36:20)
====================================================================================================
Terminal / Run Console (Inferior):
  6
  5
  4
  3
  2
  1
  0
  HAPPY NEW YEAR!
  
  Process finished with exit code 0

Rodapé do Vídeo do YouTube:
  Texto de chamada: "Java Full Course for free ☕"
  Webcam do Bro Code no canto inferior direito ("DO IT!!!").

Análise Visual da Imagem 2:
- A contagem continuou sem falhas: 6, 5, 4, 3, 2, 1 e 0!
- Assim que o número 0 foi exibido, o teste `count < 0` deu verdadeiro no tick seguinte, disparando "HAPPY NEW YEAR!".
- O método `timer.cancel()` desligou a thread e o processo encerrou com `exit code 0`!
====================================================================================================
```

---

## 🔬 Análise dos 3 Grandes Segredos Deste Projeto

### 1. Por Que Usar `scheduleAtFixedRate` em Vez de `schedule`?
No Java existem dois métodos de repetição no `Timer`:
- `schedule(task, delay, period)`: Mede o tempo com atraso relativo. Se o computador ficar lento por 100 milissegundos, todos os próximos disparos atrasarão em cascata (*drift* temporal).
- **`scheduleAtFixedRate(task, delay, period)`**: Mede o tempo com base no **relógio absoluto**. Se um disparo atrasar por qualquer motivo, o Java dispara o próximo mais rápido para compensar e manter o compasso exato!  
  👉 **Para relógios, contagens regressivas e cronômetros, `scheduleAtFixedRate` é a escolha padrão da indústria!**

---

### 2. O Segredo Matemático: Por Que `if(count < 0)` e Não `count <= 0`?
Observe a ordem das instruções dentro do método `run()`:

```java
System.out.println(count);
count--;
if(count < 0){
    System.out.println("HAPPY NEW YEAR!");
    timer.cancel();
}
```

Acompanhe o que acontece quando o relógio chega perto do fim:

| Início do `run()` | O Que `System.out.println(count)` Imprime | Executa `count--` | Teste `if(count < 0)` | Resultado |
| :---: | :---: | :---: | :---: | :--- |
| `count = 1` | Imprime **`1`** | `count` vira `0` | `0 < 0` ? **Falso!** | Espera mais 1 segundo... |
| `count = 0` | Imprime **`0`** | `count` vira `-1` | `-1 < 0` ? **VERDADEIRO!** | Imprime **`HAPPY NEW YEAR!`** e cancela! |

> [!TIP]
> **A Sacada Genial do Bro Code**:  
> Se ele tivesse colocado `if(count <= 0)`, na rodada do `1`, após o `count--`, o contador viraria `0` e o `if` dispararia imediatamente!  
> O resultado seria: `1` e logo em seguida `HAPPY NEW YEAR!`, **e ninguém veria o número 0**!  
> Usando `count < 0`, garantimos que o número `0` seja impresso com orgulho antes do grito de Ano Novo!

---

### 3. A Integração com o `Scanner`: De Código Estático para Interativo
Na primeira metade da aula, o valor era fixo (`int count = 5;`).  
Na segunda metade, o autor adicionou:
```java
Scanner scanner = new Scanner(System.in);
System.out.print("Enter # of seconds to countdown from: ");
int response = scanner.nextInt();
```
E passou para a classe anônima:
```java
int count = response;
```
Isso transforma o script em um utilitário real: o usuário escolhe se quer contar 3 segundos para um ovo quente, 10 segundos para o Ano Novo ou 60 segundos para um intervalo!

---

## 🔍 Explicação Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

Abaixo está o código integral compilado e testado do projeto final:

```java
 1: import java.util.Scanner;
 2: import java.util.Timer;
 3: import java.util.TimerTask;
 4: 
 5: public class Main {
 6:     public static void main(String[] args) {
 7: 
 8:         // Java COUNTDOWN TIMER PROGRAM
 9: 
10:         Scanner scanner = new Scanner(System.in);
11: 
12:         System.out.print("Enter # of seconds to countdown from: ");
13:         int response = scanner.nextInt();
14: 
15:         Timer timer = new Timer();
16:         TimerTask task = new TimerTask(){
17: 
18:             int count = response;
19: 
20:             @Override
21:             public void run(){
22: 
23:                 System.out.println(count);
24:                 count--;
25: 
26:                 if(count < 0){
27:                     System.out.println("HAPPY NEW YEAR!");
28:                     timer.cancel();
29:                 }
30:             }
31:         };
32: 
33:         timer.scheduleAtFixedRate(task, 0, 1000);
34: 
35:     }
36: }
```

- **Linha 1 (`import java.util.Scanner;`)**: Importa o leitor do teclado do pacote `java.util`.
- **Linha 2 (`import java.util.Timer;`)**: Importa a classe do agendador utilitário.
- **Linha 3 (`import java.util.TimerTask;`)**: Importa a classe abstrata da tarefa.
- **Linha 5 (`public class Main {`)**: Declaração da classe pública do projeto.
- **Linha 6 (`public static void main(String[] args) {`)**: Método de entrada da JVM.
- **Linha 8 (`// Java COUNTDOWN TIMER PROGRAM`)**: Comentário de cabeçalho do projeto.
- **Linha 10 (`Scanner scanner = new Scanner(System.in);`)**: Instancia o leitor de entrada padrão (`System.in`).
- **Linha 12 (`System.out.print("Enter # of seconds to countdown from: ");`)**:
  - Exibe a mensagem de solicitação para o usuário.
  - Usa `.print()` (sem o `ln`) para que o cursor do teclado fique na mesma linha da pergunta!
- **Linha 13 (`int response = scanner.nextInt();`)**:
  - Pausa a execução e aguarda o usuário digitar um número inteiro e pressionar Enter.
  - No teste da Imagem 1, o usuário digita `10`.
- **Linha 15 (`Timer timer = new Timer();`)**:
  - Cria o agendador e inicia a infraestrutura de tempo.
- **Linha 16 (`TimerTask task = new TimerTask(){`)**:
  - Abre a **Classe Anônima** baseada em `TimerTask`.
- **Linha 18 (`int count = response;`)**:
  - Inicializa o campo de estado `count` com o valor digitado pelo usuário (`10`).
- **Linha 20 (`@Override`)**: Confirma a sobrescrita do método `run()`.
- **Linha 21 (`public void run(){`)**: O método disparado a cada 1.000 milissegundos.
- **Linha 23 (`System.out.println(count);`)**: Imprime o número atual da contagem regressiva no console.
- **Linha 24 (`count--;`)**: Decrementa o valor de `count` em 1.
- **Linha 26 (`if(count < 0){`)**:
  - Verifica se o contador desceu abaixo de zero (ou seja, se o zero já foi impresso na linha anterior).
- **Linha 27 (`System.out.println("HAPPY NEW YEAR!");`)**: Exibe o brinde festivo de Ano Novo!
- **Linha 28 (`timer.cancel();`)**: Destrói a thread do cronômetro para finalizar a aplicação.
- **Linha 29 (`}`)**: Fecha o bloco condicional `if`.
- **Linha 30 (`}`)**: Fecha o método `run()`.
- **Linha 31 (`};`)**: Fecha o corpo da classe anônima e encerra a atribuição da variável `task`.
- **Linha 33 (`timer.scheduleAtFixedRate(task, 0, 1000);`)**:
  - Dispara o agendamento em taxa fixa:
    - `delay = 0`: Começa instantaneamente.
    - `period = 1000`: Repete a cada 1.000 ms (1 segundo exato).
- **Linha 35 (`}`)**: Fecha o método `main`.
- **Linha 36 (`}`)**: Fecha a classe `Main`.

---

## 📊 Rastreamento Completo da Execução (Usuário Digitando 10)

```
[ CONSOLE ]
Enter # of seconds to countdown from: 10 <── [Usuário digita 10 e aperta Enter]

t = 0s  ──► run() imprime: 10 | count vira 9  | 9 < 0? Não
t = 1s  ──► run() imprime: 9  | count vira 8  | 8 < 0? Não
t = 2s  ──► run() imprime: 8  | count vira 7  | 7 < 0? Não
t = 3s  ──► run() imprime: 7  | count vira 6  | 6 < 0? Não
t = 4s  ──► run() imprime: 6  | count vira 5  | 5 < 0? Não
t = 5s  ──► run() imprime: 5  | count vira 4  | 4 < 0? Não
t = 6s  ──► run() imprime: 4  | count vira 3  | 3 < 0? Não
t = 7s  ──► run() imprime: 3  | count vira 2  | 2 < 0? Não
t = 8s  ──► run() imprime: 2  | count vira 1  | 1 < 0? Não
t = 9s  ──► run() imprime: 1  | count vira 0  | 0 < 0? Não
t = 10s ──► run() imprime: 0  | count vira -1 | -1 < 0? SIM!
            - Imprime: HAPPY NEW YEAR!
            - timer.cancel() desliga o relógio!
            - JVM encerra com exit code 0!
```

### Saída Exata Gerada no Console:
```text
Enter # of seconds to countdown from: 10
10
9
8
7
6
5
4
3
2
1
0
HAPPY NEW YEAR!

Process finished with exit code 0
```

---

## 🏆 Conclusão da Trilogia: Capítulos 63, 64 e 65

| Capítulo | Nome do Capítulo | O Que Aprendemos |
| :---: | :--- | :--- |
| **63** | `anonymous classes 🕵️‍♂️` | Como criar subclasses rápidas sem arquivo `.java` próprio. |
| **64** | `timertasks ⏲️` | Como usar `Timer` e `TimerTask` para agendar ações no tempo. |
| **65** | `⭐ countdown timer ⏳` | **O projeto prático**: uniu `Scanner`, classe anônima, `scheduleAtFixedRate` e lógica de contagem regressiva em uma aplicação completa e funcional! |

O projeto **`⭐ countdown timer ⏳`** está **100% concluído, testado e documentado**! 🎉
