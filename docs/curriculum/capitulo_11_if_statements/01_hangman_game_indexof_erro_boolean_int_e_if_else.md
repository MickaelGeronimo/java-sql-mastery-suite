# 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 5: Verificação de Palpites com `indexOf()`, o Erro de `boolean` vs `int` e o Bloco `if-else`

---

## 🧭 Visão Geral Desta Etapa

Nesta quinta parte do **Capítulo 61**, o instrutor Bro Code implementa o coração da lógica de acertos e erros do jogo da forca:
1. **O Erro Didático ao Vivo no IntelliJ (Imagem 1)**: O instrutor digita `if(word.indexOf(guess))` e o compilador dispara o erro:  
   `Required type: boolean. Provided: int`.  
   Entendemos por que o Java não aceita inteiros dentro de um `if`.
2. **Como o Método `indexOf()` Funciona**: Por que ele retorna um índice positivo/zero se a letra existe, e `-1` se a letra não existe.
3. **A Solução Correta (Imagem 2)**: A condição `if(word.indexOf(guess) >= 0)`.
4. **Teste de Acerto com a Letra `'z'` (Imagem 3)**: O console confirma `"Correct guess!\n"`.
5. **O Problema da Falta de Feedback (Imagem 4)**: Ao testar a letra errada `'x'`, o programa encerra sem dizer nada ao usuário.
6. **O Bloco `else` (Imagem 5)**: Adiciona `else { System.out.println("Wrong guess!\n"); }`, tratando perfeitamente tanto os acertos quanto os erros do jogador!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para o Google Docs)

```
====================================================================================================
IMAGEM 1: ERRO DE COMPILAÇÃO AO VIVO — indexOf DENTRO DO IF (TIMESTAMP 9:55:46)
====================================================================================================
Editor de Código (Main.java):
  30:         System.out.print("Guess a letter: ");
  31:         char guess = scanner.next().toLowerCase().charAt(0);
  32: 
  33:         if(word.indexOf(guess)){  <-- [Sublinhado de erro vermelho em toda a linha 33!]
  34: 
  35:         }

Janela Flutuante de Documentação / Erro do IntelliJ IDEA:
  ┌────────────────────────────────────────────────────────────────────────┐
  │ Required type: boolean                                                 │
  │ Provided:      int                                                     │
  ├────────────────────────────────────────────────────────────────────────┤
  │ @java.lang.String                                                      │
  │ public int indexOf(int ch)                                             │
  │                                                                        │
  │ Returns the index within this string of the first occurrence of the   │
  │ specified character... or -1 if the character does not occur.         │
  └────────────────────────────────────────────────────────────────────────┘

====================================================================================================
IMAGEM 2: CORREÇÃO COM >= 0 E AGUARDANDO ENTRADA (TIMESTAMP 9:56:32)
====================================================================================================
Editor de Código (Main.java):
  33:         if(word.indexOf(guess) >= 0){
  34:             System.out.println("Correct guess!\n");
  35:         }

Console "Run" (Terminal aguardando palpite):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: █

====================================================================================================
IMAGEM 3: TESTANDO ACERTO COM A LETRA 'z' (TIMESTAMP 9:56:36)
====================================================================================================
Console "Run" (O instrutor digita 'z' e o terminal responde com sucesso):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: z
  Correct guess!

  Process finished with exit code 0

====================================================================================================
IMAGEM 4: TESTANDO LETRA INCORRETA 'x' SEM BLOCO ELSE (TIMESTAMP ~9:57:15)
====================================================================================================
Console "Run" (O instrutor digita 'x', mas nada é impresso pois não há 'else'):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: x

  Process finished with exit code 0

====================================================================================================
IMAGEM 5: ADICIONANDO O BLOCO ELSE E TESTANDO COM 'x' (TIMESTAMP 9:57:47)
====================================================================================================
Editor de Código (Main.java):
  33:         if(word.indexOf(guess) >= 0){
  34:             System.out.println("Correct guess!\n");
  35:         }
  36:         else{
  37:             System.out.println("Wrong guess!\n");
  38:         }

Console "Run" (O instrutor digita 'x' e agora recebe a mensagem de erro):
  ************************
  Welcome to Java Hangman!
  ************************
  Word: _ _ _ _ _ 
  Guess a letter: x
  Wrong guess!

  Process finished with exit code 0
```

---

## 🧠 Explicação Didática Para Iniciantes Amadores em Java

### 1. O que é e como funciona o método `indexOf()` de uma `String`?
Pense na palavra secreta `"pizza"`. Na memória do computador, cada letra mora em uma "casinha" numerada (índice), começando do **zero**:

```
Letra:    'p'   'i'   'z'   'z'   'a'
Índice:    0     1     2     3     4
```

O método `word.indexOf(letra)` vasculha a palavra da esquerda para a direita procurando a primeira aparição daquela letra:
* Se você perguntar: `word.indexOf('p')` $\rightarrow$ Devolve `0`.
* Se você perguntar: `word.indexOf('i')` $\rightarrow$ Devolve `1`.
* Se você perguntar: `word.indexOf('z')` $\rightarrow$ Devolve `2` (a primeira vez que o `'z'` aparece).
* Se você perguntar: `word.indexOf('a')` $\rightarrow$ Devolve `4`.

**E o que acontece se você procurar uma letra que NÃO existe**, como `'x'`?  
O Java não pode devolver 0, 1, 2, 3 ou 4. Por padrão universal, o Java devolve **`-1`**!

> [!TIP]
> **A Regra de Ouro do `indexOf()`:**
> * Retornou **0 ou mais** ($\ge 0$)? $\rightarrow$ **A LETRA EXISTE NA PALAVRA!**
> * Retornou **`-1`**? $\rightarrow$ **A LETRA NÃO EXISTE!**

---

### 2. Por que `if(word.indexOf(guess))` deu erro na Imagem 1?
Em algumas linguagens como Python, JavaScript ou C, você pode colocar números dentro de um `if`. Por exemplo, em C, qualquer número diferente de zero é considerado "verdadeiro".  
**No Java NÃO É ASSIM!**  
O Java é uma linguagem fortemente tipada e com regras estritas:

> [!CAUTION]
> **Dentro dos parênteses do `if(...)`, o Java aceita ESTRITAMENTE uma expressão do tipo `boolean` (`true` ou `false`)!**

Como `word.indexOf(guess)` devolve um número inteiro (`int`), o compilador acusa:  
`Required type: boolean. Provided: int` *(Tipo esperado: boolean. Fornecido: int)*.

Para transformar esse número em um `boolean` legítimo, precisamos fazer uma **comparação relacional**:
```java
if(word.indexOf(guess) >= 0)
```
Agora sim! Se `word.indexOf(guess)` for `2`, a expressão fica: `2 >= 0`, o que resulta em **`true`**! O `if` fica satisfeito e o código compila perfeitamente.

---

### 3. Por que colocar `\n` dentro de `"Correct guess!\n"`?
O comando `System.out.println` já quebra uma linha automaticamente no final.  
Quando escrevemos `"Correct guess!\n"` com a barra invertida e `n` no final, estamos pedindo **uma quebra de linha extra**.  
Isso faz com que o terminal deixe uma linha em branco de respiro entre o palpite atual e as próximas informações do jogo, mantendo a interface visualmente limpa e espaçada.

---

### 4. Por que o Bloco `else` é Necessário (Imagens 4 e 5)?
Na Imagem 4, quando o instrutor testou a letra errada `'x'`:
* `word.indexOf('x')` resultou em `-1`.
* `-1 >= 0` deu `false`.
* Como só existia o bloco `if`, o computador ignorou o código interno e encerrou o programa. O usuário ficou no vácuo sem nenhuma resposta!
* Na Imagem 5, ao adicionar `else { System.out.println("Wrong guess!\n"); }`, qualquer letra inexistente aciona a mensagem de erro apropriada.

---

## 📜 Código-Fonte Atualizado ([`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java))

```java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // JAVA HANGMAN GAME

        String word = "pizza";

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }

        System.out.println("************************");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("************************");

        System.out.print("Word: ");

        for(char c : wordState){
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.print("Guess a letter: ");
        char guess = scanner.next().toLowerCase().charAt(0);

        if(word.indexOf(guess) >= 0){
            System.out.println("Correct guess!\n");
        }
        else{
            System.out.println("Wrong guess!\n");
        }

        scanner.close();
    }

    static String getHangmanArt(int wrongGuesses){

        return switch(wrongGuesses){
            case 0 -> """
                      
                      
                      
                      """;
            case 1 -> """
                       o
                      
                      
                      """;
            case 2 -> """
                       o
                       |
                      
                      """;
            case 3 -> """
                       o
                      /|
                      
                      """;
            case 4 -> """
                       o
                      /|\\
                      
                      """;
            case 5 -> """
                       o
                      /|\\
                      /
                      """;
            case 6 -> """
                       o
                      /|\\
                      / \\
                      """;
            default -> "";
        };
    }
}
```

---

## 🔬 Análise Linha por Linha das Alterações

### **Linha 33: `if(word.indexOf(guess) >= 0){`**
* **O que faz:** Chama o método `indexOf` passando a letra que o usuário chutou (`guess`). Compara o resultado com `>= 0`.
* **Cenário 1 (Letra Existe):** Se o usuário digitou `'z'`, `word.indexOf('z')` retorna `2`. Como `2 >= 0` é verdadeiro (`true`), entra no bloco do `if`.
* **Cenário 2 (Letra Não Existe):** Se o usuário digitou `'x'`, `word.indexOf('x')` retorna `-1`. Como `-1 >= 0` é falso (`false`), pula para o bloco do `else`.
* *Saída no console:* Nenhuma (avaliação de condição).

---

### **Linha 34: `System.out.println("Correct guess!\n");`**
* **O que faz:** Imprime a mensagem de acerto seguida de duas quebras de linha (uma do `\n` e outra do `println`).
* *Saída no console (quando o palpite está certo):*  
  `Correct guess!`  
  *(linha em branco)*

---

### **Linha 35: `}`**
* **O que faz:** Fecha o bloco do `if`.

---

### **Linha 36: `else{`**
* **O que faz:** Abre o caminho alternativo executado exclusivamente quando a condição do `if` for falsa.

---

### **Linha 37: `System.out.println("Wrong guess!\n");`**
* **O que faz:** Imprime a mensagem de erro para o jogador.
* *Saída no console (quando o palpite está errado):*  
  `Wrong guess!`  
  *(linha em branco)*

---

### **Linha 38: `}`**
* **O que faz:** Fecha o bloco do `else`.

---

## 🧪 Tabela de Testes Comparativos

Veja o comportamento do programa nos dois cenários testados pelo instrutor:

| Palpite (`guess`) | Retorno de `indexOf` | Expressão `>= 0` | Ramo Executado | Mensagem no Console |
| :---: | :---: | :---: | :---: | :--- |
| `'z'` | `2` | `2 >= 0` $\rightarrow$ **`true`** | Bloco `if` | `Correct guess!` |
| `'x'` | `-1` | `-1 >= 0` $\rightarrow$ **`false`** | Bloco `else` | `Wrong guess!` |

---

## 🖥️ Saída Exata no Terminal nos Dois Casos

### Caso 1: Palpite Correto (`z`)
```text
************************
Welcome to Java Hangman!
************************
Word: _ _ _ _ _ 
Guess a letter: z
Correct guess!

```

### Caso 2: Palpite Incorreto (`x`)
```text
************************
Welcome to Java Hangman!
************************
Word: _ _ _ _ _ 
Guess a letter: x
Wrong guess!

```

*(Agora que o jogo já sabe identificar com precisão matemática se o jogador acertou ou errou, os próximos passos serão: revelar a letra no lugar dos tracinhos `_` quando ele acertar, incrementar `wrongGuesses++` e desenhar a forca quando ele errar, e colocar tudo isso dentro de um laço `while` para o jogo não acabar em apenas uma rodada!)*
