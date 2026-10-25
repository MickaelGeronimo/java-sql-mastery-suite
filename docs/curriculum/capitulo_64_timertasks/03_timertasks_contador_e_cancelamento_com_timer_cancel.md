# Curso de Java Completo (Bro Code) — Capítulo 64: `timertasks ⏲️` (Parte 3)
## Delay Inicial, Contador de Execuções e Encerramento com `timer.cancel()`

> **Contexto no Vídeo**: Entre os tempos **`10:29:35`** e **`10:31:15 / 12:00:00`**, o instrutor **Bro Code** resolve o grande problema dos cronômetros infinitos: **como fazer o `Timer` parar na hora certa!**  
> 1. Ele primeiro ajusta os parâmetros para `timer.schedule(task, 3000, 1000);` (espera 3 segundos em silêncio antes de começar, e depois repete a cada 1 segundo).  
> 2. Ele adiciona um campo de estado `int count = 3;` dentro da **Classe Anônima** para contar quantas repetições ainda restam.  
> 3. A cada segundo, a tarefa imprime `"Hello!"` e diminui o contador (`count--`).  
> 4. Quando o contador zera (`count <= 0`), ele imprime `"TASK COMPLETE!"` e chama **`timer.cancel()`**!  
> 5. Pela primeira vez no capítulo, a thread de segundo plano é destruída e o programa finaliza com **`Process finished with exit code 0`**!

---

## 📸 Reconstituição Visual Completa das 3 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 3 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Delay Inicial de 3000ms com Repetição de 1000ms (Tempo do Vídeo: 10:29:35)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:29:35 / 12:00:00    64.timertasks ⏲️ >

Painel Superior (Editor - Main.java):
20:         };
21: 
22:         timer.schedule(task, 3000, 1000);  <-- [3s de espera inicial + repetição a cada 1s]
23: 
24:     }
25: }

Painel Inferior (Run Console):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Hello!
  Hello!
  Hello!
  Hello!
  Hello!
  Hello!
  Hello!
  Hello!
  
  [ ⏹️ Botão vermelho 'Stop' ativo — O loop continuava rodando sem parar! ]

Análise Visual da Imagem 1:
- O autor mudou o primeiro número de `0` para `3000`.
- Ao rodar, o console esperou 3 segundos em total silêncio e depois começou a disparar "Hello!" a cada segundo.
- Na tela já se acumularam 8 mensagens "Hello!", evidenciando a necessidade de um freio para parar o processo.
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: Adicionando o Contador 'int count = 3;' e 'count--' (Tempo: 10:30:45)
====================================================================================================
Editor de Código (Linhas 14 a 32):
14:         Timer timer = new Timer();
15:         TimerTask task = new TimerTask(){
16: 
17:             int count = 3;  <-- [VARIÁVEL DE ESTADO DA TAREFA]
18: 
19:             @Override
20: ⎘          public void run(){
21:                 System.out.println("Hello!");
22:                 count--;
23:                 if(count <= 0){
24:                     System.out.println("TASK COMPLETE!");|  <-- [Cursor no fim da linha]
25:                 }
26:             }
27:         };
28: 
29:         timer.schedule(task, 3000, 1000);
30: 
31:     }
32: }

Análise Visual da Imagem 2:
- Linha 17: O autor declara `int count = 3;` dentro da classe anônima.
- Linha 22: Dentro de `run()`, ele subtrai 1 a cada disparo com `count--;`.
- Linhas 23 a 25: Ele prepara uma estrutura condicional `if(count <= 0)` para detectar quando a contagem acabar e imprimir "TASK COMPLETE!".
- O cursor de texto pisca logo após o ponto-e-vírgula da linha 24.
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: Chamada de 'timer.cancel()' e Finalização com Sucesso (Tempo: 10:31:15)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:31:15 / 12:00:00    64.timertasks ⏲️ >

Editor de Código (Linhas 23 a 27):
23:                 if(count <= 0){
24:                     System.out.println("TASK COMPLETE!");
25:                     timer.cancel();  <-- [O COMANDO QUE DESLIGA O CRONÔMETRO!]
26:                 }
27:             }

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  Hello!
  Hello!
  Hello!
  [ TASK COMPLETE! ]  <-- [Texto selecionado em destaque azul com letras brancas]
  
  Process finished with exit code 0  <-- [O PROGRAMA PAROU SOZINHO!]

Análise Visual da Imagem 3:
- O autor adicionou a linha 25: `timer.cancel();`.
- Ele executou o programa.
- O console imprimiu exatamente 3 vezes "Hello!", seguido de "TASK COMPLETE!".
- O botão vermelho Stop apagou e o Java exibiu: "Process finished with exit code 0".
- O cronômetro parou automaticamente após cumprir sua cota de 3 repetições!
====================================================================================================
```

---

## 🧠 Desvendando a Lógica: Como o Contador e o Cancelamento Funcionam

### 1. Por Que Declarar `int count = 3;` Fora de `run()`?
Observe a posição de `int count = 3;` nas linhas 15 a 20:

```java
TimerTask task = new TimerTask(){

    int count = 3; // <-- Declarado como CAMPO DA CLASSE ANÔNIMA

    @Override
    public void run(){
        // ...
        count--;
    }
};
```

- **Se declarássemos dentro de `run()`**: Toda vez que o relógio tocasse, uma nova variável `count` seria criada com o valor `3`, diminuiria para `2`, e morreria no final do método. Na próxima badalada, ela nasceria como `3` de novo, gerando um loop infinito!
- **Declarando fora de `run()` (no corpo da classe anônima)**: Ela se torna uma **variável de instância** (*field*). Ela nasce com valor `3` quando o objeto `task` é criado e **lembra do seu valor entre as execuções**!  
  - 1º disparo: `3` vira `2`.
  - 2º disparo: `2` vira `1`.
  - 3º disparo: `1` vira `0`.

---

### 2. O Poder de `timer.cancel()`
O método `cancel()` é o **botão de desligar da tomada** do `Timer`:
- Ele cancela todas as tarefas agendadas pendentes na fila.
- Ele avisa à **Thread de segundo plano** que ela pode morrer em paz.
- Quando todas as threads normais do Java morrem, a JVM encerra o programa automaticamente com `exit code 0`!

---

## 🔍 Explicação Linha por Linha de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

Abaixo está o código integral compilado e executado:

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
16: 
17:             int count = 3;
18: 
19:             @Override
20:             public void run(){
21:                 System.out.println("Hello!");
22:                 count--;
23:                 if(count <= 0){
24:                     System.out.println("TASK COMPLETE!");
25:                     timer.cancel();
26:                 }
27:             }
28:         };
29: 
30:         timer.schedule(task, 3000, 1000);
31: 
32:     }
33: }
```

- **Linhas 1 e 2**: Importam `Timer` e `TimerTask` do pacote utilitário `java.util`.
- **Linhas 4 e 5**: Assinatura da classe `Main` e método de entrada `main`.
- **Linhas 7 a 12**: Comentários didáticos conceituais da documentação.
- **Linha 14 (`Timer timer = new Timer();`)**: Aloca o agendador e inicia a infraestrutura de tempo.
- **Linha 15 (`TimerTask task = new TimerTask(){`)**: Inicia a declaração e instanciação da classe anônima.
- **Linha 16**: Linha em branco para organização visual.
- **Linha 17 (`int count = 3;`)**:
  - Variável de estado da tarefa iniciada em `3`.
  - Mantém o número de execuções restantes na memória do objeto.
- **Linha 18**: Linha em branco.
- **Linha 19 (`@Override`)**: Confirmação da implementação do método abstrato `run()`.
- **Linha 20 (`public void run(){`)**: Ponto de entrada chamado periodicamente pelo cronômetro.
- **Linha 21 (`System.out.println("Hello!");`)**: Imprime a saudação a cada repetição.
- **Linha 22 (`count--;`)**:
  - Operador de decremento. Subtrai `1` da variável `count` a cada rodada.
- **Linha 23 (`if(count <= 0){`)**:
  - Estrutura condicional de teste. Verifica se a cota de repetições se esgotou (quando `count` for menor ou igual a `0`).
- **Linha 24 (`System.out.println("TASK COMPLETE!");`)**:
  - Imprime o aviso comemorativo de encerramento da tarefa.
- **Linha 25 (`timer.cancel();`)**:
  - Destrói o timer e desativa a thread em segundo plano, liberando a memória do sistema operacional.
- **Linha 26 (`}`)**: Fecha o bloco do `if`.
- **Linha 27 (`}`)**: Fecha o método `run()`.
- **Linha 28 (`};`)**: Fecha o corpo da classe anônima e encerra a atribuição da variável `task`.
- **Linha 29**: Linha em branco.
- **Linha 30 (`timer.schedule(task, 3000, 1000);`)**:
  - Agenda `task` com **3.000 ms de atraso inicial** (3 segundos de espera) e **1.000 ms de período** (repetição a cada 1 segundo).
- **Linha 31**: Linha em branco.
- **Linhas 32 e 33 (`}`)**: Fecham o método `main` e a classe `Main`.

---

## ⏱️ Rastreamento Tick a Tick da Execução (Do Início ao Fim)

```
[ Início da Execução ] ───► t = 0.0s : timer.schedule(task, 3000, 1000) é executado.
                            count = 3 na memória.
                            O Timer inicia a contagem regressiva do delay de 3000ms.
                            Console: [SILÊNCIO ABSOLUTO]

t = 1.0s : Contagem do delay (falta 2s)... Console vazio.
t = 2.0s : Contagem do delay (falta 1s)... Console vazio.

t = 3.0s : [ DISPARO #1 ] ──► task.run() é chamado!
           - Imprime: "Hello!"
           - Executa: count-- (count passa de 3 para 2)
           - Testa: if(2 <= 0) -> Falso! Não entra no if.
           - O Timer agenda o próximo tick para daqui a 1000ms.

t = 4.0s : [ DISPARO #2 ] ──► task.run() é chamado!
           - Imprime: "Hello!"
           - Executa: count-- (count passa de 2 para 1)
           - Testa: if(1 <= 0) -> Falso! Não entra no if.
           - O Timer agenda o próximo tick para daqui a 1000ms.

t = 5.0s : [ DISPARO #3 ] ──► task.run() é chamado!
           - Imprime: "Hello!"
           - Executa: count-- (count passa de 1 para 0)
           - Testa: if(0 <= 0) -> VERDADEIRO! Entra no if!
           - Imprime: "TASK COMPLETE!"
           - Executa: timer.cancel()!
           - O Timer é desligado na hora.
           - A JVM finaliza o programa!
```

---

## 📊 Tabela de Saída do Console

| Tempo Estimado | Ação Realizada | Valor de `count` Após a Ação | Texto Impresso no Console |
| :---: | :--- | :---: | :--- |
| **0s a 3s** | Aguardando delay inicial de 3000ms | `3` | *(Em branco)* |
| **3s** | 1º Disparo do método `run()` | `2` | `Hello!` |
| **4s** | 2º Disparo do método `run()` | `1` | `Hello!` |
| **5s** | 3º Disparo do método `run()` | `0` | `Hello!` |
| **5s (imediato)** | Condição `if(count <= 0)` satisfeita | `0` | `TASK COMPLETE!` |

### Saída Completa no Console:
```text
Hello!
Hello!
Hello!
TASK COMPLETE!

Process finished with exit code 0
```
