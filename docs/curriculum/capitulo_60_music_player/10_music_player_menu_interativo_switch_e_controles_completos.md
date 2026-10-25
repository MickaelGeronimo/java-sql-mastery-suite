# ⭐ Capítulo 60: Music Player em Java (Parte 10 — Conclusão) — Menu Interativo, `switch` Moderno e Controles Completos de Áudio

> **Curso:** Java Full Course (Bro Code) — Capítulo 60: `music player`  
> **Timestamp do Vídeo:** `[9:40:46 - 9:43:10]`  
> **Arquivo do Workspace:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)  
> **Objetivo:** Compreender a conclusão triunfal do projeto do tocador de música: como criar o menu de opções dentro do loop [`while`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java#L24), ler o teclado sem se preocupar com maiúsculas/minúsculas usando [`.toUpperCase()`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java#L36), controlar o áudio em tempo real com o moderno [`switch`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java#L38) (*Play*, *Stop*, *Reset*, *Quit*), e analisar todo o código linha por linha com suas saídas no console.

---

## 📸 Reconstituição Visual do Vídeo (Para Cópia e Leitura no Google Docs)

Neste trecho final do projeto, o autor Bro Code une todas as peças construídas ao longo do capítulo: o `Clip` carregado na RAM, o `Scanner` para capturar os comandos do usuário e a estrutura de decisão que comanda a placa de som. A seguir, a reconstituição detalhada das 5 imagens:

---

### 1. Imagem 1 (`media_1791501276077_b30fdf92.png`): Montando o Menu Visual no `while`
* **Timestamp:** `9:40:46 / 12:00:00`
* **Editor de Código:** Dentro do bloco `try`, logo após `clip.open(audioStream);`, o autor estrutura:
  ```java
  clip.start();

  String response = "";

  while(!response.equals("Q")){

      System.out.println("P = Play");
      System.out.println("S = Stop");
      System.out.println("R = Reset");
      System.out.println("Q = Quit");
      System.out.print("Enter your choice: ");

      response = scanner.next().toUpperCase();
  }
  ```
* **O Detalhe Visual:**
  * Linha 32 usa `System.out.print` (sem o `ln`) para que o cursor do usuário fique esperando a digitação na mesma linha de `Enter your choice: `.
  * Linha 34 usa `.toUpperCase()` para aceitar qualquer caractere mesmo se o Caps Lock estiver desligado.

---

### 2. Imagem 2 (`media_1791501282449_0d7824f9.png`): Testando a Saída do Loop com `q`
* **Timestamp:** `9:41:00 / 12:00:00`
* **Aba de Execução (`Run - Main`):**
  O console do IntelliJ exibe o menu e a primeira interação:
  ```text
  P = Play
  S = Stop
  R = Reset
  Q = Quit
  Enter your choice: q
  Bye!
  ```
* **O que aconteceu:**
  1. A música começou a tocar imediatamente com `clip.start()`.
  2. O menu foi impresso na tela.
  3. O autor digitou a letra `q` minúscula.
  4. O Java converteu `q` para `"Q"`, a condição `!response.equals("Q")` virou `false`, o loop quebrou e o bloco `finally` imprimiu `Bye!`.

---

### 3. Imagem 3 (`media_1791501323996_112c3a9a.png`): O `switch` dos Controles de Áudio
* **Timestamp:** `9:42:33 / 12:00:00`
* **Editor de Código:** Logo após ler `response`, o autor insere o moderno `switch` com sintaxe de setas (`->`):
  ```java
  switch(response){
      case "P" -> clip.start();
      case "S" -> clip.stop();
      case "R" -> clip.setMicrosecondPosition(0);
      case "Q" -> clip.close();
      default -> System.out.println("Invalid choice");
  }
  ```
* **Por que essa sintaxe é especial:** O *Enhanced Switch* do Java moderno elimina a necessidade de escrever `break;` em cada linha, tornando o código incrivelmente legível e elegante!

---

### 4. Imagem 4 (`media_1791501337905_99be1d3d.png`): Demonstração Completa Interativa no Console
* **Timestamp:** `9:43:02 / 12:00:00`
* **Aba de Execução (`Run - Main`):**
  O autor testa os comandos ao vivo:
  ```text
  Enter your choice: p
  P = Play
  S = Stop
  R = Reset
  Q = Quit
  Enter your choice: r
  P = Play
  S = Stop
  R = Reset
  Q = Quit
  Enter your choice: 
  ```
* **A Mágica no Fone de Ouvido:**
  * Ao digitar `p`, a música começou a tocar nos alto-falantes!
  * O loop reexibiu o menu imediatamente enquanto a música continuava tocando em segundo plano.
  * O autor digitou `r` (reset): a música rebobinou instantaneamente para o segundo zero e recomeçou a tocar do início sem parar!

---

### 5. Imagem 5 (`media_1791501367770_e53a592a.png`): Encerrando com Sucesso Absoluto
* **Timestamp:** `9:43:10 / 12:00:00`
* **Aba de Execução (`Run - Main`):**
  ```text
  Enter your choice: q
  Bye!

  Process finished with exit code 0
  ```
* **Resultado:** O autor digitou `q`. O `switch` executou `clip.close()`, liberando a placa de som. O loop terminou, o console imprimiu `Bye!` e o programa foi finalizado com sucesso (`exit code 0`).

---

## 🎛️ Os Controles de Áudio Explicados para Iniciantes e Leigos

Vamos analisar o que cada comando dentro do `switch` faz fisicamente no computador:

```
+----------------------------------------------------------------------------------------------------+
| 1. case "P" -> clip.start();                                                                       |
|    - O que faz: Coloca a agulha no disco e começa a tocar o som.                                   |
|    - Comportamento inteligente: Se a música estava pausada no segundo 30, chamar start() faz ela   |
|      continuar exatamente a partir do segundo 30!                                                  |
+----------------------------------------------------------------------------------------------------+

+----------------------------------------------------------------------------------------------------+
| 2. case "S" -> clip.stop();                                                                        |
|    - O que faz: Levanta a agulha do disco e pausa a reprodução imediatamente.                     |
|    - O som para na hora, mas o ponto onde parou fica salvo na memória RAM.                         |
+----------------------------------------------------------------------------------------------------+

+----------------------------------------------------------------------------------------------------+
| 3. case "R" -> clip.setMicrosecondPosition(0);                                                     |
|    - O que é um microssegundo? 1 segundo = 1.000.000 de microssegundos!                            |
|    - O comando setMicrosecondPosition(0) rebobina a agulha de volta para o microssegundo ZERO.     |
|    - Se a música já estiver tocando, ela recomeça instantaneamente do primeiro acorde!             |
+----------------------------------------------------------------------------------------------------+

+----------------------------------------------------------------------------------------------------+
| 4. case "Q" -> clip.close();                                                                       |
|    - O que faz: Desliga o aparelho e devolve o canal de áudio para o sistema operacional.          |
|    - Na volta do loop, !response.equals("Q") se torna falso e o programa se despede com "Bye!".    |
+----------------------------------------------------------------------------------------------------+

+----------------------------------------------------------------------------------------------------+
| 5. default -> System.out.println("Invalid choice");                                                |
|    - O que faz: Se o usuário digitar "X", "123" ou "banana", o programa não trava.                 |
|      Ele avisa gentilmente que a opção é inválida e mostra o menu de novo!                         |
+----------------------------------------------------------------------------------------------------+
```

---

## 💡 Dois Truques Profissionais Ensinados pelo Bro Code

### 1. `scanner.next().toUpperCase()` (Imune a Erros de Digitação)
Se o seu código comparasse com `"P"`, e o usuário digitasse `p` minúsculo, o Java ignoraria a opção.  
Ao encadear o método `.toUpperCase()` logo após `scanner.next()`:
* Se o usuário digitar `p`, vira `"P"`.
* Se digitar `s`, vira `"S"`.
* Se digitar `r`, vira `"R"`.
* Se digitar `q`, vira `"Q"`.  
O usuário tem total liberdade para usar maiúsculas ou minúsculas!

### 2. `System.out.print` (sem `ln`) na Linha do Prompt
Repare na diferença:
* `System.out.println("P = Play");` $\rightarrow$ Tem `ln` no final, então pula para a linha de baixo.
* `System.out.print("Enter your choice: ");` $\rightarrow$ **NÃO** tem `ln`! O cursor do teclado fica piscando exatamente ao lado dos dois pontos, aguardando o usuário digitar ali mesmo!

---

## 🔍 Análise Linha por Linha do Código Completo Final

Abaixo está o código completo do player de música, exatamente como construído pelo Bro Code:

```java
 1: import javax.sound.sampled.AudioInputStream;
 2: import javax.sound.sampled.AudioSystem;
 3: import javax.sound.sampled.Clip;
 4: import javax.sound.sampled.LineUnavailableException;
 5: import javax.sound.sampled.UnsupportedAudioFileException;
 6: import java.io.File;
 7: import java.io.FileNotFoundException;
 8: import java.io.IOException;
 9: import java.util.Scanner;
10: 
11: public class Main {
12: 
13:     public static void main(String[] args) {
14: 
15:         // How to PLAY AUDIO with Java (.wav, .au, .aiff)
16: 
17:         String filePath = "src\\A Caring Friend.wav";
18:         File file = new File(filePath);
19: 
20:         try(Scanner scanner = new Scanner(System.in);
21:             AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
22: 
23:             Clip clip = AudioSystem.getClip();
24:             clip.open(audioStream);
25: 
26:             String response = "";
27: 
28:             while(!response.equals("Q")){
29: 
30:                 System.out.println("P = Play");
31:                 System.out.println("S = Stop");
32:                 System.out.println("R = Reset");
33:                 System.out.println("Q = Quit");
34:                 System.out.print("Enter your choice: ");
35: 
36:                 response = scanner.next().toUpperCase();
37: 
38:                 switch(response){
39:                     case "P" -> clip.start();
40:                     case "S" -> clip.stop();
41:                     case "R" -> clip.setMicrosecondPosition(0);
42:                     case "Q" -> clip.close();
43:                     default -> System.out.println("Invalid choice");
44:                 }
45:             }
46:             System.out.println("Bye!");
47:         }
48:         catch(FileNotFoundException e){
49:             System.out.println("Could not locate file");
50:         }
51:         catch(UnsupportedAudioFileException e){
52:             System.out.println("Audio file is not supported");
53:         }
54:         catch(LineUnavailableException e){
55:             System.out.println("Unable to access audio resource");
56:         }
57:         catch(IOException e){
58:             System.out.println("Something went wrong");
59:         }
60: 
61:     }
62: }
```

---

### Detalhamento Linha a Linha

#### Linhas 1 a 9 — Importações Oficiais
* Traz as ferramentas do sistema de som (`AudioInputStream`, `AudioSystem`, `Clip`), as exceções do compilador (`LineUnavailableException`, `UnsupportedAudioFileException`), os arquivos (`File`, `FileNotFoundException`, `IOException`) e a leitura do teclado (`Scanner`).
* **Saída no console:** Nenhuma.

---

#### Linha 11 — A Classe `Main`
* `public class Main {`
* Inicia o bloco da classe pública do projeto.
* **Saída no console:** Nenhuma.

---

#### Linha 13 — O Ponto de Entrada `main`
* `public static void main(String[] args) {`
* O portão principal onde a JVM inicia a execução.
* **Saída no console:** Nenhuma.

---

#### Linha 15 — Comentário Educativo
* `// How to PLAY AUDIO with Java (.wav, .au, .aiff)`
* Lembrete dos 3 formatos de som nativos.
* **Saída no console:** Nenhuma.

---

#### Linha 17 — Caminho do Arquivo
* `String filePath = "src\\A Caring Friend.wav";`
* Localização da faixa de áudio WAV dentro da subpasta `src`.
* **Saída no console:** Nenhuma.

---

#### Linha 18 — Criação do Objeto `File`
* `File file = new File(filePath);`
* Aponta para o arquivo físico no disco rígido.
* **Saída no console:** Nenhuma.

---

#### Linhas 20 e 21 — O `try-with-resources` Duplo
* `try(Scanner scanner = new Scanner(System.in);`
* `    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){`
* Abre o `Scanner` e o fluxo de áudio decodificado. Ambos serão fechados automaticamente pelo Java ao fim do bloco.
* **Saída no console:** Nenhuma.

---

#### Linhas 23 e 24 — A Criação e Carregamento do `Clip`
* `Clip clip = AudioSystem.getClip();`
* `clip.open(audioStream);`
* Cria a vitrola virtual na memória RAM e carrega a música inteira no buffer.
* **Saída no console:** Nenhuma.

---

#### Linha 26 — Inicialização da Variável de Opção
* `String response = "";`
* Cria a variável que guarda o comando do usuário, inicializada com aspas vazias para permitir a entrada segura no `while`.
* **Saída no console:** Nenhuma.

---

#### Linha 28 — O Loop Principal do Menu
* `while(!response.equals("Q")){`
* Mantém o player vivo e operando até o usuário digitar a letra `"Q"`.
* **Saída no console:** Nenhuma.

---

#### Linhas 30 a 34 — Exibição das Opções no Console
* `System.out.println("P = Play");`
* `System.out.println("S = Stop");`
* `System.out.println("R = Reset");`
* `System.out.println("Q = Quit");`
* `System.out.print("Enter your choice: ");`
* Imprime o menu com clareza e deixa o cursor esperando na mesma linha.
* **Saída no console (Imagens 2 e 4):**
  ```text
  P = Play
  S = Stop
  R = Reset
  Q = Quit
  Enter your choice: 
  ```

---

#### Linha 36 — Captura do Comando com `.toUpperCase()`
* `response = scanner.next().toUpperCase();`
* Lê a próxima letra digitada no teclado e converte automaticamente para maiúscula.
* **Saída no console:** O caractere que o usuário digitar (ex: `p`, `s`, `r` ou `q`).

---

#### Linhas 38 a 44 — O `switch` de Comandos
* `switch(response){`
* `    case "P" -> clip.start();` $\rightarrow$ Toca a música ou retoma de onde parou.
* `    case "S" -> clip.stop();` $\rightarrow$ Pausa a música na posição atual.
* `    case "R" -> clip.setMicrosecondPosition(0);` $\rightarrow$ Volta o ponteiro para o início da música.
* `    case "Q" -> clip.close();` $\rightarrow$ Fecha o clipe e libera o hardware de som.
* `    default -> System.out.println("Invalid choice");` $\rightarrow$ Alerta para opções incorretas.
* `}`
* **Saída no console:** Nenhuma para P, S, R e Q. Imprime `Invalid choice` apenas se o usuário digitar algo fora do menu.

---

#### Linha 46 — A Mensagem de Encerramento
* `System.out.println("Bye!");`
* Executada imediatamente após o loop `while` terminar (quando `response` for `"Q"`).
* **Saída no console (Imagens 2 e 5):**
  ```text
  Bye!
  ```

---

#### Linhas 48 a 59 — Os 4 Blocos de Proteção (`catch`)
* Capturam de forma organizada:
  1. `FileNotFoundException`: `"Could not locate file"`
  2. `UnsupportedAudioFileException`: `"Audio file is not supported"`
  3. `LineUnavailableException`: `"Unable to access audio resource"`
  4. `IOException`: `"Something went wrong"`
* **Saída no console:** Nenhuma quando tudo corre bem.

---

## 💻 Simulação Completa da Sessão de Uso (Imagens 4 e 5)

```text
P = Play
S = Stop
R = Reset
Q = Quit
Enter your choice: p
P = Play
S = Stop
R = Reset
Q = Quit
Enter your choice: r
P = Play
S = Stop
R = Reset
Q = Quit
Enter your choice: q
Bye!

Process finished with exit code 0
```

1. O usuário digitou `p`: O áudio começou a tocar nos fones de ouvido!
2. O menu apareceu de novo e o usuário digitou `r`: A música reiniciou instantaneamente do início!
3. O menu apareceu de novo e o usuário digitou `q`: A música parou, a placa de som foi liberada, o console exibiu `Bye!` e o programa fechou com código 0.

---

## 🏆 Resumo Final das Habilidades Conquistadas no Capítulo 60

| Habilidade Java | O que você aprendeu a fazer? |
| :--- | :--- |
| **Manipulação de Áudio Nativa** | Usar `javax.sound.sampled` para tocar `.wav`, `.au` e `.aiff` sem bibliotecas externas |
| **Arquitetura de Som** | Conectar `File` $\rightarrow$ `AudioSystem` $\rightarrow$ `AudioInputStream` $\rightarrow$ `Clip` |
| **Multithreading de Som** | Entender que o som toca em background e que precisamos de uma âncora (`Scanner`) |
| **Gestão de Recursos Dupla** | Usar `try(Scanner; AudioInputStream)` para fechamento automático sem vazamentos |
| **Enhanced Switch** | Usar a sintaxe moderna `case "P" -> ...` sem necessidade de comandos `break;` |
| **Tratamento de 4 Exceções** | Blindar o player contra arquivo ausente, formato inválido, hardware ocupado e erro de disco |
