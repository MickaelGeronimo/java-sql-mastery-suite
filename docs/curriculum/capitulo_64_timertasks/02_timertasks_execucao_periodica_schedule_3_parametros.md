# Curso de Java Completo (Bro Code) — Capítulo 64: `timertasks ⏲️` (Parte 2)
## A Sobrecarga de `.schedule()`: De Tarefa Única para Repetição Periódica (`task, 0, 1000`)

> **Contexto no Vídeo**: Entre os tempos **`10:28:05`** e **`10:29:12 / 12:00:00`**, o instrutor **Bro Code** demonstra a verdadeira potência do `Timer`:  
> 1. Ele observa a conclusão dos 3 segundos da Parte 1, onde a palavra `"Hello!"` finalmente surge na tela.  
> 2. Ele altera o atraso de `3000` para `1000` milissegundos para mostrar como reduzir o tempo de espera para apenas 1 segundo.  
> 3. Em seguida, ele introduz a **sobrecarga com 3 parâmetros** do método:  
>    `timer.schedule(task, 0, 1000);`  
> 4. Agora o cronômetro não morre após a primeira execução! Ele começa **imediatamente** (atraso `0`) e **repete a cada 1 segundo** (`1000` milissegundos), enchendo o console com `"Hello!"` continuamente como um metrônomo!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 5 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: O Primeiro 'Hello!' Aparece Após 3 Segundos de Espera (Tempo: 10:28:05)
====================================================================================================
Painel Superior (Editor - Main.java):
20:         };
21: 
22:         timer.schedule(task, 3000);
23: 
24:     }
25: }

Painel Inferior (Run Console):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Hello!|  <-- [Texto impresso em verde brilhante após a contagem de 3000ms]
  
  [ ⏹️ Botão vermelho 'Stop' continua aceso no canto esquerdo da aba Run ]

Análise Visual da Imagem 1:
- Os 3 segundos de atraso da Parte 1 se esgotaram.
- O método `run()` da classe anônima foi disparado e imprimiu "Hello!".
- Como o `Timer` não foi cancelado, a thread de segundo plano continua viva esperando novas tarefas.
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: Alteração do Delay de 3000ms para 1000ms (Tempo: 10:28:28)
====================================================================================================
Barra de Progresso do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:28:28 / 12:00:00    64.timertasks ⏲️ >

Editor de Código (Linha 22):
20:         };
21: 
22: 💡      timer.schedule(task, 1000);  <-- [Valor alterado de 3000 para 1000]
23: 
24:     }
25: }

Terminal / Run Console (Inferior):
  Hello!  (Saída remanescente do teste anterior)

Análise Visual da Imagem 2:
- O autor substitui `3000` por `1000`.
- 1.000 milissegundos equivalem a 1 segundo exato.
- Isso demonstra que o desenvolvedor tem controle total sobre o tempo de atraso inicial.
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: Introdução do 3º Parâmetro: 'timer.schedule(task, 0, 1000);' (Tempo: 10:29:06)
====================================================================================================
Barra Superior Direita:
  Current File ▾ [ ▶️ Run (Play Verde Selecionado com o Mouse) ]

Editor de Código (Linhas 14 a 25):
14:         Timer timer = new Timer();
15:         TimerTask task = new TimerTask(){
16:             @Override
17: ⎘          public void run(){
18:                 System.out.println("Hello!");
19:             }
20:         };
21: 
22:         timer.schedule(task, 0, 1000);  <-- [3 PARÂMETROS: Tarefa, Delay Zero, Período de 1s]
23: 
24:     }
25: }

Análise Visual da Imagem 3:
- O autor adiciona uma vírgula e um terceiro argumento na linha 22.
- O primeiro número (`0`) significa: "Não espere nada para começar! Execute a primeira vez agora mesmo!".
- O segundo número (`1000`) significa: "Depois da primeira vez, repita esta tarefa a cada 1.000 milissegundos!".
- O cursor do mouse clica no botão Play verde (Run) para iniciar a repetição.
====================================================================================================
```

```
====================================================================================================
IMAGEM 4: Início da Repetição Periódica — 3 'Hello!'s na Tela (Tempo: 10:29:10)
====================================================================================================
Painel Inferior (Run Console):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Hello!
  Hello!
  Hello!|  <-- [Cursor piscando na terceira linha]
  
  [ ⏹️ Botão vermelho 'Stop' ativo ]

Análise Visual da Imagem 4:
- O programa começou instantaneamente no segundo 0 imprimindo o primeiro "Hello!".
- No segundo 1, imprimiu o segundo "Hello!".
- No segundo 2, imprimiu o terceiro "Hello!".
- O console vai recebendo novas linhas automaticamente sem intervenção do usuário!
====================================================================================================
```

```
====================================================================================================
IMAGEM 5: A Progressão Contínua — 5 'Hello!'s Impressos (Tempo: 10:29:12)
====================================================================================================
Barra de Progresso do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:29:12 / 12:00:00    64.timertasks ⏲️ >

Painel Inferior (Run Console):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Hello!
  Hello!
  Hello!
  Hello!
  Hello!
  
  [ ⏹️ Processo continua rodando indefinidamente! ]

Análise Visual da Imagem 5:
- Passaram-se mais dois segundos (segundo 3 e segundo 4).
- Agora há 5 mensagens "Hello!" enfileiradas no console.
- Isso comprova a frase do comentário inicial: *"repetitive actions"* (ações repetitivas).
- Enquanto o usuário não clicar no botão vermelho "Stop", o Timer continuará imprimindo "Hello!" a cada segundo para sempre!
====================================================================================================
```

---

## 🔬 A Grande Diferença: `schedule` com 2 vs 3 Parâmetros

No Java, métodos com o mesmo nome que aceitam listas de parâmetros diferentes são chamados de **métodos sobrecarregados** (*overloaded methods*). Veja a diferença crucial entre as duas versões do `.schedule()`:

### 1. Versão de Execução Única (One-Shot) — 2 Parâmetros
```java
timer.schedule(task, delay);
```
- **Parâmetro 1 (`task`)**: A tarefa a executar.
- **Parâmetro 2 (`delay`)**: Quanto tempo esperar antes de executar (em milissegundos).
- **Comportamento**: Ele espera o tempo indicado, roda o método `run()` **uma única vez** e nunca mais faz nada.

---

### 2. Versão de Repetição Periódica (Recurring) — 3 Parâmetros
```java
timer.schedule(task, delay, period);
```
- **Parâmetro 1 (`task`)**: A tarefa a executar.
- **Parâmetro 2 (`delay`)**: Quanto tempo esperar antes da **PRIMEIRA** execução.
  - Se for `0`: Começa **imediatamente** no mesmo milissegundo em que a linha é executada!
  - Se for `5000`: Espera 5 segundos para dar o primeiro tiro.
- **Parâmetro 3 (`period`)**: O **intervalo de repetição** entre cada execução subsequente.
  - Se for `1000`: Repete a cada 1 segundo.
  - Se for `60000`: Repete a cada 1 minuto.
- **Comportamento**: Ele dispara a primeira vez e entra em um loop temporal infinito, acordando a thread periodicamente a cada `period` milissegundos!

```
Linha de Tempo com timer.schedule(task, 0, 1000):

t = 0s                t = 1s                t = 2s                t = 3s
  │                     │                     │                     │
  ▼                     ▼                     ▼                     ▼
Hello!                Hello!                Hello!                Hello!
(Delay inicial = 0)   (+1000ms)             (+1000ms)             (+1000ms)
```

---

## 🔍 Explicação Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

Abaixo está o código integral compilado e em execução nesta etapa:

```java
 1: import java.util.Timer;
 2: import java.util.TimerTask;
 3: 
 4: public class Main {
 5:     public static void main(String[] args) {
 6: 
 7:         // Timer = Class that schedules tasks at specific times or periodically
 8:         //         Useful for: sending notifications, scheduled updates, repetitive actions
 9: 
10:         // TimerTask = Represents the task that will be executed by the Timer
11:         //             You will extend the TimerTask class to define your task
12:         //             Create a subclass of TimerTask and @Override run()
13: 
14:         Timer timer = new Timer();
15:         TimerTask task = new TimerTask(){
16:             @Override
17:             public void run(){
18:                 System.out.println("Hello!");
19:             }
20:         };
21: 
22:         timer.schedule(task, 0, 1000);
23: 
24:     }
25: }
```

- **Linha 1 (`import java.util.Timer;`)**: Carrega a classe do agendador utilitário padrão do Java.
- **Linha 2 (`import java.util.TimerTask;`)**: Carrega o modelo abstrato de tarefas agendáveis.
- **Linhas 4 e 5**: Definição da classe `Main` e do método executável `main`.
- **Linhas 7 a 12**: Comentários explicativos do instrutor Bro Code.
- **Linha 14 (`Timer timer = new Timer();`)**:
  - Aloca o relógio agendador na memória Heap.
  - Inicia a infraestrutura de agendamento em segundo plano do Java.
- **Linha 15 (`TimerTask task = new TimerTask(){`)**:
  - Abre a **Classe Anônima** baseada em `TimerTask`.
- **Linha 16 (`@Override`)**:
  - Confirma que o método a seguir é a concretização do método abstrato `run()`.
- **Linha 17 (`public void run(){`)**:
  - Assinatura obrigatória da tarefa. Toda vez que o despertador tocar, é este método que a JVM executará.
- **Linha 18 (`System.out.println("Hello!");`)**:
  - Imprime a mensagem `"Hello!"` e pula uma linha no console.
- **Linhas 19 e 20 (`};`)**:
  - Fecham a classe anônima e encerram a atribuição da variável `task`.
- **Linha 22 (`timer.schedule(task, 0, 1000);`)**:
  - **A grande novidade desta lição!**
  - Solicita ao `timer` que execute `task` com:
    - `delay = 0`: Sem atraso inicial (executa na hora).
    - `period = 1000`: Repete a cada 1.000 milissegundos (1 segundo).
- **Linhas 24 e 25 (`}`)**: Fecham o método `main` e a classe `Main`.

---

## ⏱️ Tabela Segundo a Segundo da Execução no Console

| Tempo Transcorrido ($t$) | O Que a JVM Faz | Texto Adicionado ao Console | Total de Linhas Exibidas |
| :---: | :--- | :--- | :---: |
| **$t = 0\text{ s}$ (Instantâneo)** | Dispara o primeiro `run()` (delay zero) | `Hello!` | 1 linha |
| **$t = 1\text{ s}$** | Passou o primeiro período de 1000ms | `Hello!` | 2 linhas |
| **$t = 2\text{ s}$** | Passou o segundo período de 1000ms | `Hello!` | 3 linhas *(Imagem 4)* |
| **$t = 3\text{ s}$** | Passou o terceiro período de 1000ms | `Hello!` | 4 linhas |
| **$t = 4\text{ s}$** | Passou o quarto período de 1000ms | `Hello!` | 5 linhas *(Imagem 5)* |
| **$t = 5\text{ s}$ em diante...** | Continua a cada 1 segundo | `Hello!` ... | $6, 7, 8\dots$ infinitamente |

### Saída Visual no Console:
```text
Hello!
Hello!
Hello!
Hello!
Hello!
(continua imprimindo a cada 1 segundo...)
```

---

## 🛑 Como Parar Esse Loop Infinito? (O Que Esperar a Seguir)

Como você pôde ver nas Imagens 4 e 5, o programa fica imprimindo *"Hello!"* eternamente. No mundo real, quase nunca queremos um cronômetro que nunca desliga!  
Para parar um `Timer` periódico, o Java nos fornece o método:
```java
timer.cancel();
```
Nas próximas cenas, o instrutor Bro Code mostrará como criar uma **contagem regressiva** (*countdown*) ou limitar o número de repetições (por exemplo, contar de 5 até 1 e depois parar dizendo *"Feliz Ano Novo!"*), chamando `timer.cancel()` quando a contagem atingir zero!
