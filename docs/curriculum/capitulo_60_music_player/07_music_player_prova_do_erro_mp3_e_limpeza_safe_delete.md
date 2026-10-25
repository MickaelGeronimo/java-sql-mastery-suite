# ⭐ Capítulo 60: Music Player em Java (Parte 7) — A Prova Real do Erro MP3, Captura no `catch` e Limpeza com `Safe Delete`

> **Curso:** Java Full Course (Bro Code) — Capítulo 60: `music player`  
> **Timestamp do Vídeo:** `[9:36:30 - 9:36:55]`  
> **Arquivo do Workspace:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)  
> **Objetivo:** Ver na prática a máquina virtual Java rejeitando um arquivo `.mp3` ao vivo, comprovar o funcionamento do bloco [`catch(UnsupportedAudioFileException e)`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java#L25) imprimindo `"Audio file is not supported"`, aprender a restaurar o caminho seguro para `.wav` e entender o mecanismo de [`Safe Delete`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) do IntelliJ IDEA para limpar arquivos temporários de teste.

---

## 📸 Reconstituição Visual do Vídeo (Para Cópia e Leitura no Google Docs)

Neste trecho do vídeo, o autor Bro Code realiza um clássico "experimento científico de código": ele provoca uma falha proposital para demonstrar aos alunos o comportamento real do Java. A seguir, o detalhamento das 5 imagens:

---

### 1. Imagem 1 (`media_1791500969229_5a9ec55a.png`): Alterando o Caminho para `.mp3`
* **Timestamp:** `9:36:30 / 12:00:00`
* **Editor de Código (Zoom na Linha 12):**
  ```java
  String filePath = "src\\A Caring Friend.mp3";
  ```
* **O Detalhe Crucial:** O autor apagou a extensão `.wav` e digitou propositalmente `.mp3`. Ele quer forçar o `AudioSystem` a tentar abrir a música comprimida que ele arrastou para a pasta `src/` no passo anterior.

---

### 2. Imagem 2 (`media_1791500981309_0453b7f5.png`): O Java Rejeita o MP3 com Sucesso Controlado!
* **Timestamp:** `9:36:34 / 12:00:00`
* **Aba de Execução (`Run - Main`):**
  O autor aperta o botão ▶️ *Run*. Imediatamente, o console do IntelliJ imprime a mensagem em letras verdes:
  ```text
  Audio file is not supported

  Process finished with exit code 0
  ```
* **A Vitória Didática:**
  * O programa **NÃO QUEBROU**! Não apareceu aquela tela vermelha assustadora cheia de erros de stack trace.
  * O bloco `catch(UnsupportedAudioFileException e)` funcionou com perfeição cirúrgica: ele interceptou a recusa do Java e exibiu a mensagem amigável programada.

---

### 3. Imagem 3 (`media_1791500991569_2786426c.png`): Restaurando o Caminho Seguro para `.wav`
* **Timestamp:** `9:36:41 / 12:00:00`
* **Editor de Código (Linha 12):**
  ```java
  String filePath = "src\\A Caring Friend.wav";
  ```
* **Ação do Autor:** Como o ponto foi provado e a lição foi assimilada, o autor volta o caminho para o arquivo convertido em `.wav`, garantindo que o player volte a operar sem erros.

---

### 4. Imagem 4 (`media_1791501003201_522f94c5.png`): O Popup `Safe Delete` do IntelliJ IDEA
* **Timestamp:** `9:36:45 / 12:00:00`
* **Ação do Autor:**
  * Na barra lateral de projetos, ele seleciona o arquivo de teste `A Caring Friend.mp3` e pressiona a tecla `Delete` do teclado.
* **Janela Pop-up exibida no centro da tela:**
  * **Título:** `Delete`
  * **Pergunta:** `Delete file "A Caring Friend.mp3"?`
  * **Caixas de seleção marcadas:**
    * `[x] Safe delete (with usage search)` *(Exclusão segura com busca de usos no projeto)*
    * `[x] Search in comments and strings` *(Buscar em comentários e textos)*
  * **Botões:** Um botão azul **`[ OK ]`** e um botão **`[ Cancel ]`**.
  * O autor clica em **`[ OK ]`**.

---

### 5. Imagem 5 (`media_1791501013155_d0b23a86.png`): A Pasta `src/` Limpa e Organizada
* **Timestamp:** `9:36:50 / 12:00:00`
* **Árvore de Arquivos (`Project Explorer`):**
  * `src/`
    * 🎵 `A Caring Friend.wav` *(apenas o arquivo de áudio correto permaneceu)*
    * © `Main`
  * O arquivo `.mp3` sumiu da árvore do projeto, mantendo o ambiente limpo e profissional.

---

## 🧠 Explicando para Leigos: O Que Aconteceu nos Bastidores?

### 1. Por que o Java imprimiu `"Audio file is not supported"`?
Quando você manda o comando:
```java
AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
```
O `AudioSystem` do Java faz uma "autópsia" nos primeiros bytes do arquivo.  
* Se for um **`.wav`**, ele encontra a assinatura mágica `RIFF....WAVE`. O Java diz: *"Conheço esse cara! É áudio sem compressão PCM"*, e cria o fluxo com sucesso.
* Se for um **`.mp3`**, ele encontra a assinatura `ID3` ou cabeçalhos MPEG. O Java puro diz: *"Eu não tenho um decodificador de MP3 instalado de fábrica. Eu não sei ler isso!"*.
* Nesse milissegundo, o Java cria um objeto do tipo:
  ```java
  throw new UnsupportedAudioFileException("could not get audio input stream from input file");
  ```
* Se não houvesse o `try-catch`, o programa morreria. Mas como o autor escreveu:
  ```java
  catch(UnsupportedAudioFileException e){
      System.out.println("Audio file is not supported");
  }
  ```
  O Java desvia a execução para este bloco, imprime o aviso amigável na tela e encerra com elegância (`exit code 0`).

---

### 2. O que é o "Safe Delete" (Exclusão Segura) do IntelliJ?
Em editores de texto comuns (como Bloco de Notas), se você apaga um arquivo, ele simplesmente é deletado e se algum código depender dele, tudo quebra.  
O IntelliJ IDEA possui o recurso chamado **Safe Delete**:
* Antes de apagar o arquivo do disco, a IDE faz uma varredura em **todos os arquivos do projeto** para checar:  
  *"Tem alguma linha de código Java, alguma String ou algum comentário que ainda menciona o nome 'A Caring Friend.mp3'?"*.
* Como o autor já havia mudado a linha 12 de volta para `"src\\A Caring Friend.wav"`, o IntelliJ constatou que ninguém mais usava o `.mp3` e apagou o arquivo com total segurança!

---

## 🔍 Análise Linha por Linha do Código do Experimento

Abaixo está o código exatamente como visto durante a execução com `.mp3` e posterior retorno a `.wav`:

```java
 1: import javax.sound.sampled.AudioInputStream;
 2: import javax.sound.sampled.AudioSystem;
 3: import javax.sound.sampled.Clip;
 4: import javax.sound.sampled.LineUnavailableException;
 5: import javax.sound.sampled.UnsupportedAudioFileException;
 6: import java.io.File;
 7: import java.io.FileNotFoundException;
 8: import java.io.IOException;
 9: 
10: public class Main {
11: 
12:     public static void main(String[] args) {
13: 
14:         // How to PLAY AUDIO with Java (.wav, .au, .aiff)
15: 
16:         String filePath = "src\\A Caring Friend.mp3"; // <-- Modificado no teste
17:         File file = new File(filePath);
18: 
19:         try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
20: 
21:             Clip clip = AudioSystem.getClip();
22:             clip.open(audioStream);
23: 
24:             System.out.println("No problems detected");
25:         }
26:         catch(FileNotFoundException e){
27:             System.out.println("Could not locate file");
28:         }
29:         catch(UnsupportedAudioFileException e){
30:             System.out.println("Audio file is not supported");
31:         }
32:         catch(LineUnavailableException e){
33:             System.out.println("Unable to access audio resource");
34:         }
35:         catch(IOException e){
36:             System.out.println("Something went wrong");
37:         }
38: 
39:     }
40: }
```

---

### Detalhamento Linha a Linha Durante o Teste com MP3

#### Linha 16 — O Caminho com a Extensão `.mp3`
* `String filePath = "src\\A Caring Friend.mp3";`
* Aponta a variável para o arquivo de teste MP3.
* **Saída no console:** Nenhuma.

---

#### Linha 17 — Objeto `File`
* `File file = new File(filePath);`
* Aponta para o arquivo `src\A Caring Friend.mp3` no disco rígido. O arquivo existe fisicamente na pasta `src/`.
* **Saída no console:** Nenhuma.

---

#### Linha 19 — A Chamada que Dispara o Erro
* `try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){`
* O `AudioSystem.getAudioInputStream(file)` tenta ler o MP3.
* Como não suporta compressão MP3, ele **aborta a execução do bloco try na hora** e joga uma `UnsupportedAudioFileException`.
* **Linhas 21, 22 e 24 NUNCA SÃO EXECUTADAS!**
* A linha `System.out.println("No problems detected");` é completamente pulada!
* **Saída no console:** Nenhuma ainda.

---

#### Linhas 26 a 28 — O Bloco `FileNotFoundException` é Consultado
* O Java pergunta: *"O erro que aconteceu é do tipo arquivo não encontrado?"*.
* Resposta: Não, o arquivo existe! Então pula esse bloco.
* **Saída no console:** Nenhuma.

---

#### Linhas 29 a 31 — O Bloco `UnsupportedAudioFileException` é Acionado!
* `catch(UnsupportedAudioFileException e){`
* `    System.out.println("Audio file is not supported");`
* `}`
* O Java pergunta: *"O erro é do tipo áudio não suportado?"*.
* Resposta: **SIM! É exatamente esse erro!**
* O Java entra no bloco e executa o print.
* **Saída no console (Imagem 2):**
  ```text
  Audio file is not supported
  ```

---

#### Linhas 32 a 37 — Os Demais Blocos São Pulados
* Como a exceção já foi capturada e resolvida na linha 29, o Java ignora os blocos `LineUnavailableException` e `IOException` e segue para o encerramento do programa.
* **Saída no console:** Nenhuma.

---

## 💻 Comparativo Direto dos Dois Testes no Console

```
+---------------------------------------------------+---------------------------------------------------+
|               TESTE 1: COM ARQUIVO .WAV           |               TESTE 2: COM ARQUIVO .MP3           |
|         (String filePath = "...Friend.wav")       |         (String filePath = "...Friend.mp3")       |
+---------------------------------------------------+---------------------------------------------------+
| Console:                                          | Console:                                          |
|                                                   |                                                   |
| No problems detected                              | Audio file is not supported                       |
|                                                   |                                                   |
| Process finished with exit code 0                 | Process finished with exit code 0                 |
+---------------------------------------------------+---------------------------------------------------+
| Explicação:                                       | Explicação:                                       |
| - Bloco try executado com sucesso até o fim.      | - Bloco try abortado na linha do AudioSystem.     |
| - Clip carregado na memória RAM.                  | - catch(UnsupportedAudioFileException) disparado.|
| - Zero exceções disparadas.                       | - O programa não cai, avisa e encerra com calma.  |
+---------------------------------------------------+---------------------------------------------------+
```

---

## 📌 Resumo Prático dos Próximos Passos

Agora que o autor comprovou:
1. Que o arquivo `.wav` funciona sem problemas (`No problems detected`);
2. Que o arquivo `.mp3` é devidamente rejeitado e tratado (`Audio file is not supported`);
3. Que o projeto está limpo apenas com o arquivo WAV correto;

O próximo passo é construir a interface interativa do player: o **`Scanner`** para ler as teclas do usuário, o menu com as opções **`P`**, **`S`**, **`R`**, **`Q`**, e o loop **`while`** com as chamadas de reprodução real!
