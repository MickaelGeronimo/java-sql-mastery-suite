# ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 9 - Conclusão)
## A Sessão Triunfante no Console: 3 Rodadas ao Vivo, a Quebra do Loop com `"no"`, o Código de Saída 0 e a Volta Olímpica no Editor!

> **Baseado na aula do canal Bro Code (Minuto `6:13:45` a `6:14:20` de 12:00:00)**  
> **Tema:** O professor executa o teste de fogo definitivo do jogo completo! Ele joga 3 rodadas consecutivas no console sem reiniciar o programa: vence a 1ª com `rock` vs `scissors`, vence a 2ª com `paper` vs `rock`, perde a 3ª com `scissors` vs `rock`, digita `"no"` para encerrar, o console imprime educadamente `"Thanks for playing!"` e o processo finaliza com `Process finished with exit code 0`. No final, o professor sobe o editor e seleciona a linha 7 (`// ROCK PAPER SCISSORS GAME`) em azul como a consagração final do projeto!

---

## 📌 Sumário
1. [Contexto Visual Minucioso das 3 Imagens (Para o Google Docs)](#1-contexto-visual-minucioso-das-3-imagens-para-o-google-docs)
2. [O que o Professor Mostrou e Explicou Nesta Demonstração](#2-o-que-o-professor-mostrou-e-explicou-nesta-demonstração)
3. [Análise Cirúrgica das 3 Rodadas Jogadas no Console](#3-análise-cirúrgica-das-3-rodadas-jogadas-no-console)
4. [O que Aconteceu na Memória da Máquina Durante as 3 Rodadas?](#4-o-que-aconteceu-na-memória-da-máquina-durante-as-3-rodadas)
5. [O Significado de `Process finished with exit code 0`](#5-o-significado-de-process-finished-with-exit-code-0)
6. [Por que a Linha 7 Aparece Selecionada em Azul na Imagem 3?](#6-por-que-a-linha-7-aparece-selecionada-em-azul-na-imagem-3)
7. [O Código Java Completo, Numerado e Definitivo](#7-o-código-java-completo-numerado-e-definitivo)
8. [Checklist de Tudo o Que Aprendemos Neste Capítulo 37](#8-checklist-de-tudo-o-que-aprendemos-neste-capítulo-37)

---

## 1. Contexto Visual Minucioso das 3 Imagens (Para o Google Docs)

Para você que vai copiar este estudo diretamente para o seu **Google Docs**, aqui está a reconstituição minuciosa em texto de tudo o que acontece visualmente na tela da IDE IntelliJ IDEA nas 3 imagens enviadas:

### 📸 Imagem 1 (`media_1791245123193.png`): O Teste de Continuidade (Rodadas 1 e 2)
* **Na metade superior:** Vemos as primeiras 6 linhas de `Main.java` (`import java.util.Random;`, `import java.util.Scanner;`, `public class Main { ... }`).
* **Na metade inferior (Aba Run / Console):** O terminal está em execução contínua com texto verde do Java e respostas do usuário em ciano itálico:
  ```text
  Enter your move (rock, paper, scissors): rock
  Computer choice: scissors
  You win!
  Play again (yes/no): yes
  Enter your move (rock, paper, scissors): paper
  Computer choice: rock
  You win!
  Play again (yes/no): yes
  Enter your move (rock, paper, scissors): 
  ```
* **O Momento:** O professor jogou a primeira rodada (`rock` vs `scissors` $\rightarrow$ `You win!`), respondeu `yes`, o loop voltou ao topo, jogou a segunda rodada (`paper` vs `rock` $\rightarrow$ `You win!`), respondeu `yes` novamente e o Java acabou de imprimir o terceiro convite: `Enter your move (rock, paper, scissors): `.

---

### 📸 Imagem 2 (`media_1791245135007.png`): A 3ª Rodada, a Saída com `"no"` e o Código 0!
* **No Console do Terminal:** Vemos o desfecho da sessão:
  ```text
  Computer choice: rock
  You win!
  Play again (yes/no): yes
  Enter your move (rock, paper, scissors): scissors
  Computer choice: rock
  You lose!
  Play again (yes/no): no
  Thanks for playing!

  Process finished with exit code 0
  ```
* **O Desfecho:**
  * Na 3ª rodada, o professor digitou **`scissors`** *(Tesoura)*.
  * O robô sorteou **`rock`** *(Pedra)*.
  * A pedra esmaga a tesoura $\rightarrow$ O Java imprimiu **`You lose!`**.
  * O jogo perguntou: `Play again (yes/no): `.
  * O professor digitou: **`no`** *(Não)*.
  * O Java saiu do loop `do-while`, imprimiu a despedida: **`Thanks for playing!`**.
  * O cursor do console está piscando exatamente após o ponto de exclamação de `Thanks for playing!`.
  * Logo abaixo, o IntelliJ confirma o sucesso absoluto: **`Process finished with exit code 0`**.

---

### 📸 Imagem 3 (`media_1791245154902.png`): A Volta Olímpica no Editor
* **No Editor de Código (`Main.java`):**
  * O professor subiu o código até o início.
  * **O Grande Destaque Azul (Linha 7):** A linha 7 inteira está selecionada com uma faixa retangular azul:
    ```java
    7:         // ROCK PAPER SCISSORS GAME
    ```
  * O cursor do mouse está sobre o início do comentário.
* **O Motivo:** O professor destaca o título do projeto para comemorar com os alunos o encerramento triunfal do capítulo, mostrando que todo o programa foi construído do zero, passo a passo, e funciona sem nenhum bug!

---

## 2. O que o Professor Mostrou e Explicou Nesta Demonstração

Esta sequência de telas não adiciona novas linhas de código, mas é a **prova de fogo** de que a arquitetura do programa está perfeita:

1. **A Eficiência da Memória:** O `Scanner` e o `Random` foram criados **uma única vez** antes do loop. Eles continuam atendendo a infinitas rodadas sem sobrecarregar a memória do computador.
2. **A Independência das Rodadas:** Cada rodada é um novo duelo limpo. As variáveis `playerChoice` e `computerChoice` recebem novos valores e o resultado anterior não interfere no próximo.
3. **A Força do `do-while`:** O programa não precisou perguntar se queríamos jogar antes de começar. A primeira rodada aconteceu naturalmente, e as seguintes só aconteceram porque o usuário pediu explicitamente.
4. **O Encerramento Gracioso:** Ao digitar `"no"`, o programa não "travou" nem fechou abruptamente: ele se despediu com educação e fechou a conexão com o sistema operacional.

---

## 3. Análise Cirúrgica das 3 Rodadas Jogadas no Console

Acompanhe o que a CPU fez em cada uma das 3 partidas da demonstração:

| Rodada | Jogada do Usuário | Sorteio da CPU | Expressão Lógica Avaliada | Resultado Impresso | Resposta de Repetição | Ação do Loop |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **1ª** | `rock` | `scissors` | `player.equals("rock") && comp.equals("scissors")` $\rightarrow$ `true` | `You win!` | `yes` | Condição `true` $\rightarrow$ Volta ao topo |
| **2ª** | `paper` | `rock` | `player.equals("paper") && comp.equals("rock")` $\rightarrow$ `true` | `You win!` | `yes` | Condição `true` $\rightarrow$ Volta ao topo |
| **3ª** | `scissors` | `rock` | Nenhuma vitória e nenhum empate $\rightarrow$ Cai no `else` | `You lose!` | `no` | Condição `false` $\rightarrow$ Quebra o loop! |

---

## 4. O que Aconteceu na Memória da Máquina Durante as 3 Rodadas?

Muitos alunos iniciantes têm medo de loops porque acham que as variáveis vão "acumular lixo" ou "duplicar". Veja como a JVM gerencia isso com elegância:

```
[ MEMÓRIA STACK (Variáveis Locais) ]
┌─────────────────────────────────────────────────────────────┐
│ choices       ──────> Aponta para o array no Heap           │
│ scanner       ──────> Aponta para o Scanner no Heap         │
│ random        ──────> Aponta para o Random no Heap          │
├─────────────────────────────────────────────────────────────┤
│ playerChoice  = (Rodada 1: "rock") -> (Rodada 2: "paper") -> (Rodada 3: "scissors")
│ computerChoice= (Rodada 1: "scissors") -> (Rodada 2: "rock") -> (Rodada 3: "rock")
│ playAgain     = (Início: "yes") -> (Rodada 1: "yes") -> (Rodada 2: "yes") -> (Rodada 3: "no")
└─────────────────────────────────────────────────────────────┘
```

* As variáveis na Stack não são recriadas; elas têm seus valores **sobrescritos** a cada volta do loop.
* Quando `playAgain` é atualizado para `"no"`, a expressão `playAgain.equals("yes")` retorna `false`.
* A JVM sai do `do-while` e desce em linha reta para a instrução seguinte: linha 47 (`Thanks for playing!`).

---

## 5. O Significado de `Process finished with exit code 0`

Na Imagem 2, vemos a mensagem final gerada pelo IntelliJ IDEA:
```text
Process finished with exit code 0
```

### O que isso significa para um iniciante?
* Em todos os sistemas operacionais do planeta (Windows, Linux, macOS), quando um programa termina, ele devolve um **código numérico de saída (Exit Code)** para o sistema operacional:
  * **Código `0` (Zero):** Significa **Sucesso Absoluto!** O programa começou, executou todas as tarefas planejadas e terminou sem nenhum erro, sem exceção lançada e sem travar.
  * **Código Diferente de `0` (ex: `1`, `137`, etc.):** Significa que ocorreu algum erro fatal ou o programa foi forçado a fechar.
* Ver o **Código `0`** é a sensação mais gratificante para um programador: é a garantia de que o seu código é 100% robusto!

---

## 6. Por que a Linha 7 Aparece Selecionada em Azul na Imagem 3?

Na Imagem 3, o professor Bro Code seleciona com o mouse a linha 7:
```java
// ROCK PAPER SCISSORS GAME
```

### O Motivo Didático do Professor:
* Essa é a **"Volta Olímpica"** do professor!
* No início da aula (6:00:06), ele escreveu essa linha com o esqueleto em 6 passos comentados.
* Agora, no minuto 6:14:00, o jogo inteiro está funcionando perfeitamente:
  * Tem entrada do usuário;
  * Tem validação contra trapaças;
  * Tem inteligência artificial com sorteio randômico;
  * Tem arbitragem elegante de vitória, empate e derrota;
  * Tem repetição com `do-while`;
  * Tem encerramento limpo com liberação de memória.
* Ele seleciona o título para dizer aos alunos: *"Parabéns! Nós construímos um jogo de videogame completo do zero em Java puro!"*.

---

## 7. O Código Java Completo, Numerado e Definitivo

Abaixo está o arquivo [Main.java](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) integral com todas as 51 linhas exatas do projeto:

```java
1:  import java.util.Random;
2:  import java.util.Scanner;
3:  
4:  public class Main {
5:      public static void main(String[] args){
6:  
7:          // ROCK PAPER SCISSORS GAME
8:  
9:          Scanner scanner = new Scanner(System.in);
10:         Random random = new Random();
11: 
12:         String[] choices = {"rock", "paper", "scissors"};
13:         String playerChoice;
14:         String computerChoice;
15:         String playAgain = "yes";
16: 
17:         do{
18:             System.out.print("Enter your move (rock, paper, scissors): ");
19:             playerChoice = scanner.nextLine().toLowerCase();
20: 
21:             if(!playerChoice.equals("rock") &&
22:                !playerChoice.equals("paper") &&
23:                !playerChoice.equals("scissors")){
24:                 System.out.println("Invalid choice");
25:             }
26: 
27:             computerChoice = choices[random.nextInt(3)];
28:             System.out.println("Computer choice: " + computerChoice);
29: 
30:             if(playerChoice.equals(computerChoice)){
31:                 System.out.println("It's a tie!");
32:             }
33:             else if((playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
34:                     (playerChoice.equals("paper") && computerChoice.equals("rock")) ||
35:                     (playerChoice.equals("scissors") && computerChoice.equals("paper"))){
36:                 System.out.println("You win!");
37:             }
38:             else{
39:                 System.out.println("You lose!");
40:             }
41: 
42:             System.out.print("Play again (yes/no): ");
43:             playAgain = scanner.nextLine().toLowerCase();
44: 
45:         }while(playAgain.equals("yes"));
46: 
47:         System.out.println("Thanks for playing!");
48: 
49:         scanner.close();
50:     }
51: }
```

---

## 8. Checklist de Tudo o Que Aprendemos Neste Capítulo 37

Você acabou de dominar um arsenal de conceitos fundamentais da programação Java:

- [x] **Construção de Blueprint:** Planejar a lógica em comentários antes de digitar código.
- [x] **Geração Aleatória Bounded:** Sorteio com `random.nextInt(3)` para escolher posições `0`, `1` ou `2` de um array.
- [x] **Sanitização de Strings:** Usar `.toLowerCase()` para evitar que `"Rock"` ou `"YES"` quebrem as comparações.
- [x] **Comparação Correta de Texto:** Usar `.equals()` em vez de `==` para comparar o conteúdo das Strings.
- [x] **Lógica Booleana Invertida com `!` e `&&`:** Validar que uma escolha não é nem pedra, nem papel e nem tesoura.
- [x] **Eliminação Matemática com `else`:** Reduzir 9 combinações possíveis em 1 empate, 3 vitórias e deixar o `else` cuidar das derrotas.
- [x] **Operador OU Lógico (`||`):** Agrupar múltiplas condições em uma única linha legível (Princípio DRY).
- [x] **Loop Pós-Testado (`do-while`):** Garantir que a partida rode ao menos uma vez antes de perguntar se o usuário quer jogar de novo.
- [x] **Gestão Responsável de Recursos:** Fechar o leitor com `scanner.close()` fora do loop.
