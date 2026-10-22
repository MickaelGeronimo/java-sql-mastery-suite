# Curso de Java Completo (Bro Code) — Capítulo 65: `⭐ countdown timer ⏳` (Parte 2)
## A Armadilha Mortal: O Que Acontece se Você Esquecer o `timer.cancel()`?

> **Contexto no Vídeo**: O instrutor **Bro Code** faz uma demonstração didática clássica de *"o que NÃO fazer"*:  
> Ele remove (ou esquece) a linha **`timer.cancel();`** de dentro do bloco `if(count < 0)`.  
> Ao rodar o programa, algo bizarro e hilário acontece no console:  
> O cronômetro conta até zero, grita `"HAPPY NEW YEAR!"`, mas **não desliga**! Ele continua contando números negativos a cada segundo:  
> `-1`, `"HAPPY NEW YEAR!"`, `-2`, `"HAPPY NEW YEAR!"`, `-3`, `"HAPPY NEW YEAR!"`...  
> O programa entra em um loop infinito rumo ao infinito negativo!

---

## 📸 Reconstituição Visual da Imagem (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá a imagem anexada lá, abaixo está a reprodução textual e estrutural idêntica da tela do IntelliJ IDEA e do YouTube.

```
====================================================================================================
TELA DO INTELLIJ IDEA: O Loop Infinito sem 'timer.cancel()'
====================================================================================================
Painel de Arquivos (Esquerda):
  📁 MyFirstProject C:\Users\HP\...
  ├── 📁 .idea
  ├── 📁 out
  └── 📁 src
      ├── ☕ Main
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (Main.java - Linhas 19 a 24 Visíveis):
19:                 if(count < 0){
20: 💡                  System.out.println("HAPPY NEW YEAR!");
21:                     // <-- [CADÊ O timer.cancel(); ??? ESTÁ FALTANDO!]
22:                 }
23:             }
24:         };

Barra Superior / Aba Run:
  [ ⏹️ Botão vermelho 'Stop' aceso ]  [ ⏹️ Botão Stop na barra superior direita também aceso ]

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  4
  3
  2
  1
  0
  HAPPY NEW YEAR!
  -1
  HAPPY NEW YEAR!
  -2
  HAPPY NEW YEAR!|  <-- [Cursor piscando enquanto continua imprimindo sem parar!]

Rodapé:
  Webcam do Bro Code no canto inferior direito com estampa "DO IT!!!".
  21:21  LF  UTF-8  4 spaces

Análise Visual da Imagem:
- Na linha 21 do editor, a chamada `timer.cancel()` NÃO EXISTE.
- O console chegou a 0 e disparou "HAPPY NEW YEAR!".
- Mas como o despertador continuou ligado, no segundo seguinte ele imprimiu -1 e disparou "HAPPY NEW YEAR!" de novo!
- No segundo seguinte, imprimiu -2 e outro "HAPPY NEW YEAR!".
- O programa nunca para e o botão vermelho Stop continua travado aceso!
====================================================================================================
```

---

## 🔬 A Anatomia do Bug: Por Que Isso Aconteceu?

Para um iniciante em programação, este é um dos conceitos mais importantes de entender: **o computador faz exatamente o que você manda, e não o que você imagina que ele deveria fazer!**

Veja o que o código dizia:

```java
public void run(){
    System.out.println(count);
    count--;

    if(count < 0){
        System.out.println("HAPPY NEW YEAR!");
        // timer.cancel();  <-- ESTÁ FALTANDO!
    }
}
```

Vamos rastrear segundo a segundo o que a JVM faz quando o cronômetro chega em zero:

### 1. No segundo do Zero (`count = 0`):
- `System.out.println(count);` imprime **`0`**.
- `count--;` subtrai 1 de zero: **`count` vira `-1`**.
- Testa o `if`: `if(-1 < 0)` → **Verdadeiro!**
- Imprime: **`HAPPY NEW YEAR!`**.
- Como não há `timer.cancel()`, o método `run()` termina essa rodada, **mas o `Timer` continua vivo agendado para o próximo segundo!**

### 2. Um segundo depois (`count = -1`):
- O `Timer` dispara o método `run()` de novo!
- `System.out.println(count);` imprime **`-1`**!
- `count--;` subtrai 1 de -1: **`count` vira `-2`**!
- Testa o `if`: `if(-2 < 0)` → **Verdadeiro de novo!**
- Imprime: **`HAPPY NEW YEAR!`**!
- E o `Timer` continua vivo!

### 3. Outro segundo depois (`count = -2`):
- O `Timer` dispara de novo!
- Imprime **`-2`**.
- `count` vira **`-3`**.
- Testa o `if`: `if(-3 < 0)` → **Verdadeiro de novo!**
- Imprime: **`HAPPY NEW YEAR!`**!

> [!CAUTION]
> **O Efeito Bola de Neve**:  
> Como qualquer número negativo é SEMPRE menor que zero (`-1 < 0`, `-2 < 0`, `-1000 < 0`), a condição do `if` **NUNCA MAIS SERÁ FALSA**!  
> Daqui para frente, a cada segundo o programa imprimirá o número negativo seguido de "HAPPY NEW YEAR!", até que alguém feche o programa à força ou desligue o computador!

---

## ⚖️ Comparação Lado a Lado: O Erro vs O Código Correto

```
┌──────────────────────────────────────────────┐  ┌──────────────────────────────────────────────┐
│       ❌ CÓDIGO COM BUG (DA IMAGEM)          │  │          ✅ CÓDIGO CORRETO E SEGURO          │
├──────────────────────────────────────────────┤  ├──────────────────────────────────────────────┤
│  if(count < 0){                              │  │  if(count < 0){                              │
│      System.out.println("HAPPY NEW YEAR!");  │  │      System.out.println("HAPPY NEW YEAR!");  │
│      // Faltou o cancel!                     │  │      timer.cancel(); // <-- MATA A THREAD!   │
│  }                                           │  │  }                                           │
├──────────────────────────────────────────────┤  ├──────────────────────────────────────────────┤
│  RESULTADO NO CONSOLE:                       │  │  RESULTADO NO CONSOLE:                       │
│  0                                           │  │  0                                           │
│  HAPPY NEW YEAR!                             │  │  HAPPY NEW YEAR!                             │
│  -1                                          │  │                                              │
│  HAPPY NEW YEAR!                             │  │  Process finished with exit code 0           │
│  -2                                          │  │  (Programa para na hora certa!)              │
│  HAPPY NEW YEAR!                             │  │                                              │
│  (Loop infinito para sempre!)                │  │                                              │
└──────────────────────────────────────────────┘  └──────────────────────────────────────────────┘
```

---

## 🔍 Explicação Linha por Linha do Trecho em Destaque

```java
19:                 if(count < 0){
20:                     System.out.println("HAPPY NEW YEAR!");
21: 
22:                 }
23:             }
24:         };
```

- **Linha 19 (`if(count < 0){`)**:
  - Testa se o contador atingiu valores estritamente negativos.
  - Como vimos, a intenção era disparar a comemoração assim que o `0` terminasse de ser exibido.
- **Linha 20 (`System.out.println("HAPPY NEW YEAR!");`)**:
  - Imprime a mensagem de celebração no console.
- **Linha 21 (Linha em Branco — O ERRO!)**:
  - Aqui deveria estar **`timer.cancel();`**.
  - Sem essa instrução, o objeto `Timer` não sabe que a tarefa acabou. Ele simplesmente continua obedecendo a ordem que recebeu lá embaixo: *"repita a cada 1.000 milissegundos"*.
- **Linha 22 (`}`)**: Fecha o bloco `if`.
- **Linha 23 (`}`)**: Fecha o método `run()`.
- **Linha 24 (`};`)**: Fecha a classe anônima com ponto-e-vírgula.

---

## 🖥️ Tabela de Saída do Console e Rastreamento do Bug

| Tempo ($t$) | O que `run()` faz | Novo valor de `count` | A condição `count < 0` é atendida? | O que aparece na tela |
| :---: | :--- | :---: | :---: | :--- |
| **0s** | Imprime o número | `3` | Não | `4` |
| **1s** | Imprime o número | `2` | Não | `3` |
| **2s** | Imprime o número | `1` | Não | `2` |
| **3s** | Imprime o número | `0` | Não | `1` |
| **4s** | Imprime o número | `-1` | **Sim!** | `0`<br>`HAPPY NEW YEAR!` |
| **5s** *(Deveria parar, mas continua!)* | Imprime `-1` | `-2` | **Sim!** | `-1`<br>`HAPPY NEW YEAR!` |
| **6s** *(Continua rodando!)* | Imprime `-2` | `-3` | **Sim!** | `-2`<br>`HAPPY NEW YEAR!` |
| **7s** *(Continua rodando!)* | Imprime `-3` | `-4` | **Sim!** | `-3`<br>`HAPPY NEW YEAR!` |

### Saída Exata Mostrada na Tela da Imagem:
```text
4
3
2
1
0
HAPPY NEW YEAR!
-1
HAPPY NEW YEAR!
-2
HAPPY NEW YEAR!
```

---

## 💡 Moral da Lição do Bro Code

O autor incluiu essa cena intencionalmente no vídeo para ensinar uma regra de ouro no desenvolvimento Java:
> **"Toda vez que você criar um `Timer` periódico, você DEVE ter uma porta de saída bem definida chamando `timer.cancel()`. Caso contrário, seu programa ficará preso na memória do computador executando tarefas para sempre!"**
