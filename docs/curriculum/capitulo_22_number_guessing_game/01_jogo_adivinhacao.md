# 🔢 Curso Completo de Java — Capítulo 22: Jogo da Adivinhação (Parte 1)
## Construindo o Jogo do Número Secreto com `Random`, `do-while` e `!=`

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 22 (*22. number guessing game 🔢*), entre os minutos `3:35:53` e `3:38:43` do curso de 12 horas do Bro Code.  
> **A proposta deste capítulo:**  
> O professor Bro Code inicia a criação de um jogo completo no terminal chamado **"Number Guessing Game"** (Jogo da Adivinhação de Números). O computador sorteia um número secreto entre 1 e 10, e o jogador precisa chutar palpites até acertar!  
>  
> **O que o autor faz passo a passo nas 5 imagens enviadas:**  
>  
> * **Imagem 1 (Minuto 3:35:53 — Sorteando o Número Secreto):**  
>   O autor importa as classes `Random` e `Scanner`. Ele cria o gerador aleatório e sorteia o número na linha 14:  
>   `int randomNumber = random.nextInt(1, 11);`  
>   Ele coloca temporariamente um `System.out.println(randomNumber);` na linha 16 para espiar se o Java realmente sorteou um número.  
>  
> * **Imagem 2 (Minuto 3:36:02 — Teste do Sorteio):**  
>   Ele clica no Play. O console imprime o número `5`. Isso confirma que o sorteio de 1 a 10 está funcionando perfeitamente!  
>  
> * **Imagem 3 (Minuto 3:38:27 — Construindo a Lógica do Jogo com `do-while`):**  
>   O autor apaga aquele print de teste e cria o menu inicial do jogo:  
>   `System.out.println("Number Guessing Game");`  
>   `System.out.println("Guess a number between 1-10: ");`  
>   Em seguida, cria o laço `do-while` nas linhas 19 a 24:  
>   `do {`  
>   `    System.out.print("Enter a guess: ");`  
>   `    guess = scanner.nextInt();`  
>   `    attempts++;`  
>   `} while(guess != randomNumber);`  
>   E no final: `System.out.println("You have won");`.  
>  
> * **Imagem 4 (Minuto 3:38:43 — Primeira Partida Real):**  
>   O autor joga no terminal! O número secreto sorteado pelo computador foi o `9`.  
>   Ele chuta `6` $\rightarrow$ errou!  
>   Ele chuta `7` $\rightarrow$ errou!  
>   Ele chuta `8` $\rightarrow$ errou!  
>   Ele chuta `9` $\rightarrow$ **ACERTOU!**  
>   O laço para e o console exibe com alegria: `You have won` *(Você venceu)*!  
>  
> * **Imagem 5 (Segunda Partida):**  
>   Ele joga outra partida onde o número secreto era `5`. Ele chuta `2`, `3`, `4`, e na tentativa `5` ele vence novamente!

---

## 🎲 1. Como Funciona o `random.nextInt(1, 11)`?

Lembre-se da regra de ouro do método `nextInt(origem, limite)` da classe `Random`:
```java
int randomNumber = random.nextInt(1, 11);
```
- O primeiro número (`1`) é **inclusivo** (o número 1 pode ser sorteado).
- O segundo número (`11`) é **exclusivo** (o Java para um número antes, no 10!).
- Portanto, essa linha sorteia números no intervalo fechado de **1 a 10** (`1, 2, 3, 4, 5, 6, 7, 8, 9, 10`).

---

## 🔄 2. A Lógica do Laço `do-while` com `!=` (Diferente)

Por que o professor escolheu o **`do-while`** para este jogo?
- Porque num jogo de adivinhação, o jogador **precisa chutar pelo menos uma vez** antes de sabermos se ele acertou ou não!
- O bloco `do` roda a primeira vez sem burocracia, pede o chute e soma uma tentativa (`attempts++`).

Olhe a condição de repetição no rodapé:
```java
} while (guess != randomNumber);
```
- O símbolo **`!=`** significa: **"É DIFERENTE DE"**!
- O Java pergunta: *"O palpite do jogador é DIFERENTE do número secreto?"*.
  - Se você chutou `6` e o número secreto é `9`: $6 \neq 9$? **SIM (`true`)**! Como é diferente, o jogador errou, e o `while` manda repetir!
  - Se você chutou `9` e o número secreto é `9`: $9 \neq 9$? **NÃO (`false`)**! Eles são iguais! O `while` quebra imediatamente e sai do loop!

---

## 📜 Código-Fonte Completo (`Main.java`)

Abaixo está o código exatamente como está na tela do professor nas Imagens 3, 4 e 5:

```java
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // NUMBER GUESSING GAME

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int guess;
        int attempts = 0;
        int randomNumber = random.nextInt(1, 11);

        System.out.println("Number Guessing Game");
        System.out.println("Guess a number between 1-10: ");

        do {
            System.out.print("Enter a guess: ");
            guess = scanner.nextInt();
            attempts++;
        } while (guess != randomNumber);

        System.out.println("You have won");

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 28)

* **Linha 1: `import java.util.Random;`**  
  Importa o gerador de números aleatórios da biblioteca padrão do Java.  
  *No console:* Não exibe nada.

* **Linha 2: `import java.util.Scanner;`**  
  Importa o leitor de teclado para capturar os palpites do jogador.  
  *No console:* Não exibe nada.

* **Linha 3: *(Linha em branco)***  
  Organização visual.

* **Linha 4: `public class Main {`**  
  Declara a classe principal `Main`.

* **Linha 5: `    public static void main(String[] args) {`**  
  O método principal `main` (ponto de partida do programa).

* **Linha 6: *(Linha em branco)***  
  Organização visual.

* **Linha 7: `        // NUMBER GUESSING GAME`**  
  Comentário que dá o título do projeto: Jogo da Adivinhação de Números.  
  *No console:* Não exibe nada.

* **Linha 8: *(Linha em branco)***  
  Organização visual.

* **Linha 9: `        Random random = new Random();`**  
  Cria o objeto `random` (a nossa "roleta mágica" que sorteia números).  
  *No console:* Não exibe nada.

* **Linha 10: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` conectado ao teclado.  
  *No console:* Não exibe nada.

* **Linha 11: *(Linha em branco)***  
  Organização visual.

* **Linha 12: `        int guess;`**  
  Cria a caixinha de memória `guess` (palpite) para guardar o número que o jogador digitar.  
  *No console:* Não exibe nada.

* **Linha 13: `        int attempts = 0;`**  
  Cria a variável contadora `attempts` (tentativas) começando com valor `0`.  
  *No console:* Não exibe nada.

* **Linha 14: `        int randomNumber = random.nextInt(1, 11);`** ⭐ **(O SORTEIO SECRETO)**  
  A roleta gira e escolhe um número entre 1 e 10 (o 11 não entra). Guarda o valor secreto em `randomNumber`.  
  *No console:* Não exibe nada (o jogador não pode ver!).

* **Linha 15: `        System.out.println("Number Guessing Game");`**  
  Imprime o cabeçalho do jogo na tela e pula uma linha.  
  *No console:* `Number Guessing Game`

* **Linha 16: `        System.out.println("Guess a number between 1-10: ");`**  
  Imprime as instruções de que o número está entre 1 e 10.  
  *No console:* `Guess a number between 1-10: `

* **Linha 17: *(Linha em branco)***  
  Organização visual.

* **Linha 18: *(Linha em branco)***  
  Organização visual.

* **Linha 19: `        do {`** ⭐ **(INÍCIO DA RODADA DO JOGO)**  
  O Java entra direto no bloco sem testar nada. O jogo precisa rodar a rodada antes de saber se o jogador acertou.

* **Linha 20: `            System.out.print("Enter a guess: ");`**  
  Pede o palpite mantendo o cursor na mesma linha.  
  *No console:* `Enter a guess: `

* **Linha 21: `            guess = scanner.nextInt();`**  
  Pausa o programa e espera o jogador digitar seu palpite (ex: `6`) e dar Enter. Salva o palpite em `guess`.

* **Linha 22: `            attempts++;`** ⭐ **(CONTADOR DE TENTATIVAS)**  
  O operador de incremento `++` soma +1 na variável `attempts`. Cada vez que o jogador chuta, o contador sobe: 1, 2, 3...

* **Linha 23: *(Linha em branco)***  
  Organização visual.

* **Linha 24: `        } while (guess != randomNumber);`** ⭐ **(O TESTE DO ACERTO)**  
  Pergunta: *"O palpite do jogador é DIFERENTE (`!=`) do número secreto?"*.  
  - Se for diferente $\rightarrow$ **`true`**! O jogador errou, então sobe para a linha 19 e pede outro palpite!  
  - Se for igual $\rightarrow$ **`false`**! O jogador acertou! O loop acaba e o programa desce para a linha 26!

* **Linha 25: *(Linha em branco)***  
  Organização visual.

* **Linha 26: `        System.out.println("You have won");`** ⭐ **(MENSAGEM DE VITÓRIA)**  
  Só é executada quando o jogador finalmente acerta o número secreto!  
  *No console:* `You have won`

* **Linha 27: `        scanner.close();`**  
  Fecha o scanner liberando a memória do computador.

* **Linha 28: `    }`**  
  Fecha o método `main` e a classe `Main`.

---

## 🖥️ A Partida Completa do Vídeo no Console (Imagem 4)

O computador sorteou secretamente o número **9**:

```text
Number Guessing Game
Guess a number between 1-10: 
Enter a guess: 6   (6 != 9 é verdadeiro -> errou, pede de novo!)
Enter a guess: 7   (7 != 9 é verdadeiro -> errou, pede de novo!)
Enter a guess: 8   (8 != 9 é verdadeiro -> errou, pede de novo!)
Enter a guess: 9   (9 != 9 é FALSO! -> acertou, quebra o loop!)
You have won

Process finished with exit code 0
```

---

## 🔮 O Que Falta para o Jogo Ficar Perfeito? (O Que Virá a Seguir)

Se você reparar bem, o jogo atual tem dois probleminhas divertidos:
1. Ele não dá dicas se o palpite foi **"MUITO ALTO"** ou **"MUITO BAIXO"** (o jogador fica no escuro chutando na sorte).
2. Ele calculou a variável `attempts++`, mas ainda **não imprimiu quantas tentativas** o jogador levou para ganhar!

No próximo trecho do vídeo, o professor Bro Code vai adicionar `if (guess < randomNumber)` e `if (guess > randomNumber)` e mostrar o placar final com `attempts`!
