# Curso de Java Completo (Bro Code) — Capítulo 64: `timertasks ⏲️` (Parte 1)
## Introdução ao `Timer`, `TimerTask` com Classe Anônima e Agendamento com Delay

> **Contexto no Vídeo**: No tempo **`10:28:17 / 12:00:00`**, o instrutor **Bro Code** inicia o **Capítulo 64: `timertasks ⏲️`**.  
> Este capítulo é a continuação direta e a prova real do que vimos no Capítulo 63! Lembra que no capítulo anterior os comentários diziam:  
> `// Often used for one time uses (TimerTask, Runnable, callbacks)`?  
> Pois bem: agora o autor nos ensina a usar exatamente o **`Timer`** e o **`TimerTask`** para agendar a execução de tarefas no futuro!  
> Ele explica a teoria, lida com o erro clássico de múltiplos pacotes ao importar `java.util.Timer`, cria uma tarefa usando uma **Classe Anônima** que sobrescreve `public void run()`, e agenda um atraso de 3 segundos (`3000` milissegundos).

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 5 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Abertura do Capítulo 64 e Comentários Teóricos em Main.java (Tempo: 10:25:48)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:25:48 / 12:00:00    64.timertasks ⏲️ >

Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Main     <-- [Dog.java foi limpo/removido do projeto]
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (Main.java):
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4: 
 5: 💡      // Timer = Class that schedules tasks at specific times or periodically
 6:         //         Useful for: sending notifications, scheduled updates, repetitive actions
 7: 
 8:         // TimerTask = Represents the task that will be executed by the Timer
 9:         //             You will extend the TimerTask class to define your task
10:         //             Create a subclass of TimerTask and @Override run()
11: 
12: 
13:     }
14: }

Análise Visual da Imagem 1:
- O autor começa conceituando os dois personagens fundamentais:
  1. Timer (o agendador que controla o relógio).
  2. TimerTask (o trabalho ou missão concreta que o relógio deve disparar).
- Linha 5 traz uma lâmpada amarela 💡 de anotação do IntelliJ.
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: Digitando 'Timer timer = new Timer();' e o Erro Vermelho de Import (Tempo: 10:26:34)
====================================================================================================
Canto Superior Direito do Editor:
  Ícone vermelho de erro: 🔴 02 ⚠️ 1 (Dois erros de compilação detectados!)

Editor de Código (Linhas 10 a 16):
10:         //             Create a subclass of TimerTask and @Override run()
11: 
12:         Timer timer = new Timer();  <-- [Ambas as palavras 'Timer' estão em VERMELHO]
13: 
14: 
15:     }
16: }

Balão de Dica Flutuante do IntelliJ (Tooltip):
  ┌─────────────────────────────────────────────────────────────┐
  │ Import class... java.util.Timer? Alt+Enter                  │
  └─────────────────────────────────────────────────────────────┘

Análise Visual da Imagem 2:
- O Java não sabe o que é `Timer` porque ele não está no pacote padrão `java.lang`.
- As palavras `Timer` ficam sublinhadas em vermelho brilhante.
- O IntelliJ detecta e sugere: "Pressione Alt+Enter para importar a classe java.util.Timer".
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: A Armadilha dos Múltiplos Timers no Java ('Class to Import') (Tempo: 10:26:44)
====================================================================================================
Menu Suspenso Flutuante (Class to Import):
  ┌─────────────────────────────────────────────────────────────┐
  │ Class to Import:                                            │
  │ ┌─────────────────────────────────────────────────────────┐ │
  │ │ ⓒ Timer (of java.util)             < openjdk-23 >       │ │ <-- [SELECIONADO EM AZUL]
  │ ├─────────────────────────────────────────────────────────┤ │
  │ │ ⓒ Timer (of javax.swing)           < openjdk-23 >       │ │
  │ ├─────────────────────────────────────────────────────────┤ │
  │ │ ⓒ Timer (of javax.management.timer)< openjdk-23 >       │ │
  │ └─────────────────────────────────────────────────────────┘ │
  └─────────────────────────────────────────────────────────────┘

Análise Visual da Imagem 3:
- Existem 3 classes diferentes chamadas `Timer` no Java!
  1. java.util.Timer -> O agendador padrão de console e tarefas em segundo plano (O CORRETO AQUI!).
  2. javax.swing.Timer -> Para interfaces gráficas e animações do Swing.
  3. javax.management.timer -> Para gerenciamento empresarial e monitoramento de servidores JMX.
- O autor seleciona a primeira opção: `java.util.Timer`.
====================================================================================================
```

```
====================================================================================================
IMAGEM 4: Código Completo com Classe Anônima e Agendamento de 3000ms (Tempo: 10:28:17)
====================================================================================================
Barra Superior Direita:
  Current File ▾ [ ▶️ Run 'Main.java' Shift+F10 ]  <-- [Cursor do mouse sobre o Play verde]

Editor de Código (Linhas 12 a 25):
12: 
13: 
14:         Timer timer = new Timer();
15:         TimerTask task = new TimerTask(){
16:             @Override
17: ⎘          public void run(){
18:                 System.out.println("Hello!");
19:             }
20:         };
21: 
22:         timer.schedule(task, 3000);
23: 
24:     }
25: }

Análise Visual da Imagem 4:
- Linha 14: Cria a instância do agendador `timer`.
- Linhas 15 a 20: Cria `task` usando uma **Classe Anônima** que implementa o método obrigatório `public void run()`.
- Linha 18: A ação que será executada é imprimir `"Hello!"`.
- Linha 22: `timer.schedule(task, 3000);` agenda a execução para daqui a 3.000 milissegundos (3 segundos).
- O autor posiciona o mouse no botão verde de Run para testar!
====================================================================================================
```

```
====================================================================================================
IMAGEM 5: O Console em Branco Durante os 3 Segundos de Espera (Tempo: 10:28:24)
====================================================================================================
Painel Inferior (Run Console):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."

  (Área do console totalmente vazia/preta no momento!)
  
  [ ⏹️ Botão vermelho 'Stop' aceso no canto esquerdo da barra de ferramentas do Run ]

Análise Visual da Imagem 5:
- O autor clicou em Run, o programa iniciou, mas o console NÃO imprimiu nada ainda!
- Por que está em branco? Porque o método `schedule(task, 3000)` está contando os 3 segundos de espera no relógio!
- O botão quadrado vermelho de "Stop" (Parar) está ativado, indicando que uma thread de segundo plano do `Timer` está rodando ativamente.
- Assim que o cronômetro zerar (3 segundos), a palavra "Hello!" aparecerá na tela!
====================================================================================================
```

---

## 🧠 Entendendo os Conceitos: O Que São `Timer` e `TimerTask`?

Pense nisso como um **despertador** e uma **tarefa matinal**:

### 1. A Dupla Perfeita da Produtividade
- **`Timer` (O Despertador ⏰)**:
  É o relógio. Ele não sabe fritar ovos, não sabe lavar louça e não sabe programar. A única coisa que ele sabe fazer é **contar o tempo com precisão** e, quando a hora chega, dar um sinal sonoro: *"BEEP! BEEP! Hora da tarefa!"*.
- **`TimerTask` (A Tarefa Concreta 📋)**:
  É a lista de afazeres. O despertador sozinho não faz nada se não houver uma tarefa para ser feita quando ele tocar. `TimerTask` é uma classe abstrata do Java que possui um único método sagrado chamado:
  ```java
  public void run()
  ```
  Tudo o que você escrever dentro do método `run()` será o trabalho executado quando o alarme tocar!

```
+-----------------------------------------------------------------------------------+
|                            COMO ELES TRABALHAM JUNTOS                             |
+-----------------------------------------------------------------------------------+
|                                                                                   |
|     1. Você cria o Despertador:                                                   |
|        Timer timer = new Timer();                                                 |
|                                                                                   |
|     2. Você cria a Missão (com Classe Anônima):                                   |
|        TimerTask task = new TimerTask() {                                         |
|            public void run() {                                                    |
|                System.out.println("Hello!");                                      |
|            }                                                                      |
|        };                                                                         |
|                                                                                   |
|     3. Você programa o alarme:                                                    |
|        timer.schedule(task, 3000);   <-- "Daqui a 3 segundos, execute o run()!"   |
|                                                                                   |
+-----------------------------------------------------------------------------------+
```

---

## ⏱️ Por Que Usamos Milissegundos (`3000`)?

Computadores trabalham em velocidades ultra-rápidas. Para o processador, 1 segundo é uma eternidade!  
Por isso, a maioria dos métodos de tempo no Java usam **milissegundos** ($1\text{ segundo} = 1000\text{ milissegundos}$):

$$\text{Milissegundos} = \text{Segundos} \times 1000$$

| Tempo Desejado em Segundos | Valor Informado no Java |
| :---: | :---: |
| Meio segundo ($0{,}5\text{ s}$) | `500` |
| 1 segundo ($1\text{ s}$) | `1000` |
| **3 segundos ($3\text{ s}$)** | **`3000`** *(O valor usado pelo Bro Code!)* |
| 5 segundos ($5\text{ s}$) | `5000` |
| 1 minuto ($60\text{ s}$) | `60000` |

---

## 🔍 Explicação Linha por Linha do Código de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

Abaixo está o código integral compilado e executado nesta etapa:

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
22:         timer.schedule(task, 3000);
23: 
24:     }
25: }
```

- **Linha 1 (`import java.util.Timer;`)**:
  - Importa a classe `Timer` do pacote `java.util`.
  - Resolve o erro vermelho visto na Imagem 2. Garante que estamos usando o relógio correto e não o do Swing ou de gerenciamento empresarial.
- **Linha 2 (`import java.util.TimerTask;`)**:
  - Importa a classe abstrata base `TimerTask` que representa a tarefa a ser executada.
- **Linha 3**: Linha em branco.
- **Linha 4 (`public class Main {`)**: Declaração da classe pública principal do projeto.
- **Linha 5 (`public static void main(String[] args) {`)**: Ponto de entrada padrão da aplicação.
- **Linha 6**: Espaçamento em branco.
- **Linhas 7 e 8**: Comentários explicando que `Timer` agenda tarefas em horários específicos ou periodicamente (útil para notificações, atualizações periódicas e repetições).
- **Linhas 9 a 12**: Comentários explicando que `TimerTask` representa a tarefa, que devemos estendê-la e sobrescrever o método `run()`.
- **Linha 13**: Espaçamento em branco.
- **Linha 14 (`Timer timer = new Timer();`)**:
  - Cria um novo agendador na memória Heap.
  - Internamente, o `Timer` cria e dispara uma **Thread de segundo plano** (*background thread*) que fica dormindo até chegar a hora agendada.
- **Linha 15 (`TimerTask task = new TimerTask(){`)**:
  - Aqui está a **Classe Anônima** que aprendemos no Capítulo 63!
  - `TimerTask` é uma classe abstrata, o que significa que o Java nos proíbe de dar `new TimerTask();` sozinho.
  - Mas ao abrir a chave `{`, criamos uma subclasse anônima imediatamente, implementando o método abstrato que faltava!
- **Linha 16 (`@Override`)**:
  - Anotação que valida a implementação correta do método `run()`.
- **Linha 17 (`public void run(){`)**:
  - O método de execução exigido pela interface `Runnable` (da qual `TimerTask` é filha).
  - É a porta que o `Timer` abrirá quando o tempo estipulado terminar.
- **Linha 18 (`System.out.println("Hello!");`)**:
  - A ação da tarefa: imprimir no console o texto amigável `"Hello!"`.
- **Linha 19 (`}`)**: Fecha o método `run()`.
- **Linha 20 (`};`)**: Fecha a classe anônima e termina a atribuição da variável `task` com o ponto-e-vírgula obrigatório.
- **Linha 21**: Linha em branco.
- **Linha 22 (`timer.schedule(task, 3000);`)**:
  - O coração do agendamento!
  - Método `.schedule(tarefa, atrasoEmMilissegundos)`:
    - 1º Parâmetro: O objeto `task` contendo o que deve ser feito.
    - 2º Parâmetro: `3000` (aguardar 3.000 milissegundos antes de disparar).
- **Linha 23**: Linha em branco.
- **Linha 24 (`}`)**: Fecha o método `main`.
- **Linha 25 (`}`)**: Fecha a classe `Main`.

---

## 📊 Previsão da Linha do Tempo e Saída no Console

```
Instante t = 0s : Você clica no botão "Run".
                  O método main() roda em menos de 1 milissegundo.
                  O Timer começa a contagem de 3 segundos em segundo plano.
                  Console: [TOTALMENTE EM BRANCO] (Exatamente como na Imagem 5!)

Instante t = 1s : O Timer continua contando...
                  Console: [TOTALMENTE EM BRANCO]

Instante t = 2s : O Timer continua contando...
                  Console: [TOTALMENTE EM BRANCO]

Instante t = 3s : O tempo de 3000ms esgotou!
                  O Timer acorda a tarefa e chama task.run().
                  Console exibe:
                  Hello!
```

### Saída Final Impressa no Console:
```text
Hello!
```

> [!IMPORTANT]
> **Atenção — A Armadilha da Thread Ativa**:  
> Quando você roda esse código, você notará que após imprimir `Hello!`, o programa **continua rodando** e o botão vermelho de Stop continua aceso!  
> Isso acontece porque, por padrão, o `java.util.Timer` mantém sua thread de segundo plano viva esperando por novos agendamentos.  
> Para encerrar o programa de vez, os desenvolvedores costumam chamar `timer.cancel()` dentro do próprio `run()` ou configurar o timer como uma thread *daemon*. Veremos como o Bro Code aborda isso nas próximas cenas!
