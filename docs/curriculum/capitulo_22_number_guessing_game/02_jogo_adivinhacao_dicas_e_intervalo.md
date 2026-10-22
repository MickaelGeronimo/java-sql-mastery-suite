# 🎮 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 22: Jogo de Adivinhação de Números (Parte 2: Dicas, Placar e Intervalo Dinâmico)
**Timestamps do vídeo:** `3:40:46` até `3:41:30 / 12:00:00`  
**Tema:** Adicionando Inteligência ao Jogo (`if / else if / else`), Placar de Tentativas (`attempts`), Intervalo Dinâmico (`min` e `max`) e Formatação com `printf`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 3:40:46):**
   * O professor entra dentro do bloco do laço `do-while`.
   * Logo abaixo de `attempts++;`, ele adiciona a lógica de dicas:
     * Se o chute for menor que o número secreto: avisa que foi muito baixo.
     * Se o chute for maior que o número secreto: avisa que foi muito alto.
     * Se não for nenhum dos dois (ou seja, acertou!): parabeniza e começa a digitar a linha que mostra quantas tentativas o jogador levou.
2. **Imagem 2 (Minuto 3:41:04):**
   * O código completo das dicas está pronto.
   * Ao acertar, o programa imprime:
     `"CORRECT! The number was " + randomNumber`
     `"# of attempts: " + attempts`
   * Abaixo do laço `do-while`, na linha 37, ele fecha o leitor com `scanner.close();`.
3. **Imagem 3 (Minuto 3:41:30):**
   * O professor roda o jogo no console (sorteio de 1 a 10).
   * O jogador tenta vários números até chutar `9`.
   * O console responde: `TOO LOW! Try again` (Muito baixo! Tente de novo).
   * O jogador então digita: `10`.
   * O console comemora: `CORRECT! The number was 10` e revela `# of attempts: 5` (Foram 5 tentativas no total). O jogo encerra com sucesso.
4. **Imagem 4 (Evolução para Intervalo Dinâmico 1 a 100):**
   * O professor melhora o código para não ficar preso apenas aos números de 1 a 10.
   * Ele cria duas variáveis: `int min = 1;` e `int max = 100;`.
   * Atualiza o sorteio para: `int randomNumber = random.nextInt(min, max + 1);`.
   * Usa `System.out.printf("Guess a number between %d-%d\n", min, max);` para mostrar o intervalo na tela de forma automática e elegante.
   * No console, vemos um novo teste onde o jogador chuta `3` (`TOO LOW! Try again`) e em seguida `4` (`CORRECT! The number was 4`, `# of attempts: 6`).

---

## 🧠 Por que o professor fez essas melhorias? (A lógica explicada de forma simples)

### 1. O Problema da Parte 1: "O Jogo Mudo e Sem Graça"
Na Parte 1, o computador sorteava um número e o laço só repetia silenciosamente até você acertar. Você não sabia se tinha chutado perto, longe, para cima ou para baixo, e quando finalmente acertava, o programa simplesmente fechava sem falar nada! Parecia que o programa tinha travado.

### 2. A Solução: Dicas Direcionais ("Mais quente, mais frio")
Para transformar isso em um jogo de verdade, o programa precisa dar **feedback** (respostas imediatas) para cada palpite:
* Se `guess < randomNumber`: Seu chute foi **menor** que o segredo. Mensagem: `"TOO LOW! Try again"` (Muito baixo! Tente um número maior).
* Se `guess > randomNumber`: Seu chute foi **maior** que o segredo. Mensagem: `"TOO HIGH! Try again"` (Muito alto! Tente um número menor).
* Se não for menor nem maior (`else`): Só sobra uma possibilidade matemática: **você acertou na mosca!** Mensagem: `"CORRECT! The number was " + randomNumber` e o total de chutes `# of attempts: " + attempts`.

### 3. A Melhoria Profissional: Limites Flexíveis (`min` e `max`)
Em vez de escrever números fixos no meio do código ("números mágicos" como 1 e 11), criamos gavetas com nomes:
* `min = 1;`
* `max = 100;`

Se amanhã você quiser que o jogo vá de 1 a 1.000, ou de 1 a 50, **você só altera uma única linha** no topo do código, e todo o resto do jogo se adapta sozinho!

> ⚠️ **Atenção ao detalhe de ouro: `max + 1`**  
> No Java, o método `random.nextInt(inicio, fim)` é **exclusivo no final**. Isso quer dizer que ele nunca inclui o número final.  
> Se você colocasse `random.nextInt(1, 100)`, o computador só sortearia até **99**!  
> Para incluir o 100, precisamos passar `max + 1` (que vale 101). Assim ele sorteia de 1 até 100 perfeitamente!

---

## 💻 Código-Fonte Completo e Atualizado (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int guess;
        int attempts = 0;
        int min = 1;
        int max = 100;
        int randomNumber = random.nextInt(min, max + 1);

        System.out.println("Number Guessing Game");
        System.out.printf("Guess a number between %d-%d\n", min, max);

        do{
            System.out.print("Enter a guess: ");
            guess = scanner.nextInt();
            attempts++;

            if(guess < randomNumber){
                System.out.println("TOO LOW! Try again");
            }
            else if(guess > randomNumber){
                System.out.println("TOO HIGH! Try again");
            }
            else{
                System.out.println("CORRECT! The number was " + randomNumber);
                System.out.println("# of attempts: " + attempts);
            }

        }while(guess != randomNumber);

        scanner.close();
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda linha enumerada)

Vamos analisar detalhadamente o que acontece em cada linha, o que acontece na memória do computador e exatamente o que aparece na tela do console.

---

### **Linha 1:** `import java.util.Random;`
* **O que faz:** Importa a ferramenta de sorteios do Java.
* **Explicação para iniciantes:** O Java básico vem enxuto para não ficar pesado. Para que o computador consiga sortear números aleatórios como um dado ou uma roleta de cassino, pedimos a ferramenta `Random` emprestada da biblioteca utilitária (`java.util`).
* **Memória:** Registra na memória que a classe `Random` está disponível.
* **Console:** **Nada aparece no console.**

---

### **Linha 2:** `import java.util.Scanner;`
* **O que faz:** Importa a ferramenta de leitura do teclado do Java.
* **Explicação para iniciantes:** Mesma coisa: o Java precisa do `Scanner` para conseguir "ouvir" o que o usuário digita no teclado e aperta Enter.
* **Memória:** Registra na memória que a classe `Scanner` está disponível.
* **Console:** **Nada aparece no console.**

---

### **Linha 3:** `public class Main {`
* **O que faz:** Abre a declaração da nossa classe principal chamada `Main`.
* **Explicação para iniciantes:** Todo código Java precisa morar dentro de uma classe, que funciona como a "casa" ou a "embalagem" do projeto. O nome `Main` deve bater com o nome do arquivo `Main.java`.
* **Memória:** O Java carrega a estrutura da classe `Main`.
* **Console:** **Nada aparece no console.**

---

### **Linha 4:** `public static void main(String[] args) {`
* **O que faz:** Cria o método principal (`main`), que é a porta de entrada da execução do programa.
* **Explicação para iniciantes:** É aqui que o Java começa a rodar de verdade. Quando você clica no botão verde de Play, o computador procura essa linha e começa a ler linha por linha de cima para baixo.
* **Memória:** Prepara a pilha de execução (Call Stack) para executar as instruções.
* **Console:** **Nada aparece no console.**

---

### **Linha 5:** *(Linha em branco)*
* **O que faz:** Linha vazia usada para organizar visualmente o código e não deixar tudo amontoado.
* **Console:** **Nada aparece no console.**

---

### **Linha 6:** `Random random = new Random();`
* **O que faz:** Constrói uma máquina geradora de números aleatórios e dá a ela o nome de `random`.
* **Explicação para iniciantes:** Criamos o nosso "globo de bingo" virtual. Sempre que precisarmos de um número surpresa, chamaremos `random`.
* **Memória:** Aloca um objeto do tipo `Random` na memória Heap.
* **Console:** **Nada aparece no console.**

---

### **Linha 7:** `Scanner scanner = new Scanner(System.in);`
* **O que faz:** Constrói o leitor de teclado e dá a ele o nome de `scanner`.
* **Explicação para iniciantes:** Ligamos o nosso "microfone" do teclado apontado para a entrada padrão (`System.in`).
* **Memória:** Aloca um objeto do tipo `Scanner` conectado ao teclado.
* **Console:** **Nada aparece no console.**

---

### **Linha 8:** *(Linha em branco)*
* **Console:** **Nada aparece no console.**

---

### **Linha 9:** `int guess;`
* **O que faz:** Declara uma variável do tipo número inteiro (`int`) chamada `guess` (palpite/chute), sem valor inicial.
* **Explicação para iniciantes:** Criamos uma caixinha vazia na memória chamada `guess`. Ela guardará o número que o jogador digitar.
* **Memória:** Reserva espaço para um número inteiro chamado `guess`.
* **Console:** **Nada aparece no console.**

---

### **Linha 10:** `int attempts = 0;`
* **O que faz:** Declara uma variável inteira chamada `attempts` (tentativas) e já coloca o valor `0` nela.
* **Explicação para iniciantes:** É o nosso contador ou placar de chutes. Como o jogo acabou de começar e ninguém chutou nada ainda, o placar começa marcando zero.
* **Memória:** Caixinha `attempts` recebe o valor `0`.
* **Console:** **Nada aparece no console.**

---

### **Linha 11:** `int min = 1;`
* **O que faz:** Guarda o menor número possível do jogo (o limite inferior).
* **Explicação para iniciantes:** Dizemos ao computador: "O menor número que você pode sortear é 1".
* **Memória:** Caixinha `min` recebe o valor `1`.
* **Console:** **Nada aparece no console.**

---

### **Linha 12:** `int max = 100;`
* **O que faz:** Guarda o maior número possível do jogo (o limite superior).
* **Explicação para iniciantes:** Dizemos ao computador: "O maior número que você pode sortear é 100".
* **Memória:** Caixinha `max` recebe o valor `100`.
* **Console:** **Nada aparece no console.**

---

### **Linha 13:** `int randomNumber = random.nextInt(min, max + 1);`
* **O que faz:** Roda o globo de bingo e sorteia um número secreto entre `min` (1) e `max` (100).
* **Explicação para iniciantes:** 
  * `min` vale 1.
  * `max + 1` vale `100 + 1 = 101`.
  * O método `nextInt(1, 101)` gera números de 1 até 100 (lembre-se que o último número nunca é incluído, por isso precisamos passar 101).
  * O número sorteado em segredo é guardado na caixinha `randomNumber`. O jogador NÃO sabe que número é esse!
* **Memória:** Caixinha `randomNumber` recebe o número secreto (por exemplo, `4`).
* **Console:** **Nada aparece no console.**

---

### **Linha 14:** *(Linha em branco)*
* **Console:** **Nada aparece no console.**

---

### **Linha 15:** `System.out.println("Number Guessing Game");`
* **O que faz:** Imprime o título de boas-vindas do jogo e pula para a linha de baixo.
* **Console:** Imprime:
  ```text
  Number Guessing Game
  ```

---

### **Linha 16:** `System.out.printf("Guess a number between %d-%d\n", min, max);`
* **O que faz:** Imprime uma mensagem com texto formatado mostrando o intervalo de números.
* **Explicação para iniciantes:**
  * O `printf` permite encaixar valores de variáveis dentro do texto usando "marcadores" (placeholders).
  * O `%d` é um marcador para número inteiro (`decimal integer`).
  * O primeiro `%d` é substituído pelo valor de `min` (`1`).
  * O segundo `%d` é substituído pelo valor de `max` (`100`).
  * O `\n` no final serve para pular uma linha na tela.
* **Console:** Imprime:
  ```text
  Guess a number between 1-100
  ```

---

### **Linha 17:** *(Linha em branco)*
* **Console:** **Nada aparece no console.**

---

### **Linha 18:** `do{`
* **O que faz:** Abre o bloco do laço `do-while` ("faça enquanto").
* **Explicação para iniciantes:** A palavra `do` em inglês significa "FAÇA". Ela garante que tudo o que estiver dentro das chaves `{ ... }` seja executado **pelo menos uma primeira vez**, antes de fazer qualquer pergunta ou teste.
* **Memória:** O fluxo de execução entra dentro do laço.
* **Console:** **Nada aparece no console.**

---

### **Linha 19:** `System.out.print("Enter a guess: ");`
* **O que faz:** Pede para o jogador digitar um palpite.
* **Detalhe importante:** Usa `print` (sem `ln`), o que faz com que o cursor fique piscando na mesma linha, logo após os dois pontos e o espaço, esperando o usuário digitar.
* **Console:** Imprime:
  ```text
  Enter a guess: _
  ```

---

### **Linha 20:** `guess = scanner.nextInt();`
* **O que faz:** O programa fica pausado esperando o jogador digitar um número inteiro e apertar a tecla **Enter**.
* **Explicação para iniciantes:** Assim que o jogador digita (por exemplo, `3`) e aperta Enter, o `scanner` captura o número `3` e guarda dentro da caixinha `guess`.
* **Memória:** `guess` agora vale o que foi digitado (ex: `3`).
* **Console:** O número digitado pelo jogador aparece na tela ao lado de `Enter a guess: ` (ex: `Enter a guess: 3`).

---

### **Linha 21:** `attempts++;`
* **O que faz:** Incrementa o contador de tentativas em 1. É o mesmo que escrever `attempts = attempts + 1;`.
* **Explicação para iniciantes:** Cada vez que o jogador chuta um número, o placar de tentativas soma +1. Se era 0, vira 1; se era 1, vira 2, e assim por diante.
* **Memória:** Se `attempts` valia 0, agora passa a valer `1`.
* **Console:** **Nada aparece no console.**

---

### **Linha 22:** *(Linha em branco)*
* **Console:** **Nada aparece no console.**

---

### **Linha 23:** `if(guess < randomNumber){`
* **O que faz:** Testa se o número digitado pelo jogador é **menor** que o número secreto sorteado pelo computador.
* **Explicação para iniciantes:** Suponha que o segredo seja `4` e você chutou `3`. A pergunta é: `3 < 4`? Sim, é verdadeiro (`true`)! Se for verdadeiro, o Java entra no bloco das linhas 24-25. Se for falso, ele pula para o `else if`.
* **Memória:** Avalia a expressão booleana.
* **Console:** **Nada aparece no console.**

---

### **Linha 24:** `System.out.println("TOO LOW! Try again");`
* **O que faz:** Imprime a dica de que o chute foi muito baixo.
* **Explicação para iniciantes:** Dá a dica valiosa para o jogador: "Seu chute foi BAIXO DEMAIS! Tente um número maior!".
* **Console:** (Aparece somente se `guess < randomNumber` for verdadeiro):
  ```text
  TOO LOW! Try again
  ```

---

### **Linha 25:** `}`
* **O que faz:** Fecha o bloco do primeiro `if`. Como essa condição foi atendida, o Java pula todo o resto (`else if` e `else`) e vai direto para o final do laço.
* **Console:** **Nada aparece no console.**

---

### **Linha 26:** `else if(guess > randomNumber){`
* **O que faz:** Se o primeiro `if` deu falso, o Java faz um segundo teste: "Será que o chute do jogador é **maior** que o número secreto?".
* **Explicação para iniciantes:** Suponha que o segredo seja `4` e você chutou `50`. A pergunta é: `50 > 4`? Sim! Então ele entra aqui.
* **Memória:** Avalia a segunda expressão booleana se a primeira falhou.
* **Console:** **Nada aparece no console.**

---

### **Linha 27:** `System.out.println("TOO HIGH! Try again");`
* **O que faz:** Imprime a dica de que o chute foi muito alto.
* **Explicação para iniciantes:** Dá a dica: "Seu chute foi ALTO DEMAIS! Tente um número menor!".
* **Console:** (Aparece somente se `guess > randomNumber` for verdadeiro):
  ```text
  TOO HIGH! Try again
  ```

---

### **Linha 28:** `}`
* **O que faz:** Fecha o bloco do `else if`.
* **Console:** **Nada aparece no console.**

---

### **Linha 29:** `else{`
* **O que faz:** Entra aqui se o chute **não** foi menor e **não** foi maior que o número secreto.
* **Explicação para iniciantes:** Se não é menor e não é maior, por pura lógica só sobrou uma única opção: **o chute é EXATAMENTE IGUAL ao número secreto! Você venceu o jogo!**
* **Memória:** Prepara para rodar a comemoração e o resultado final.
* **Console:** **Nada aparece no console.**

---

### **Linha 30:** `System.out.println("CORRECT! The number was " + randomNumber);`
* **O que faz:** Imprime a mensagem de parabéns revelando o número sorteado.
* **Console:** (Aparece somente quando você acerta):
  ```text
  CORRECT! The number was 4
  ```

---

### **Linha 31:** `System.out.println("# of attempts: " + attempts);`
* **O que faz:** Imprime quantas vezes você teve que chutar até finalmente acertar.
* **Explicação para iniciantes:** Mostra o seu placar. O `#` em inglês é uma abreviação comum para "Number" (Número/Quantidade). Então significa: "Quantidade de tentativas: 6".
* **Console:** Imprime:
  ```text
  # of attempts: 6
  ```

---

### **Linha 32:** `}`
* **O que faz:** Fecha o bloco do `else`.
* **Console:** **Nada aparece no console.**

---

### **Linha 33:** *(Linha em branco)*
* **Console:** **Nada aparece no console.**

---

### **Linha 34:** `}while(guess != randomNumber);`
* **O que faz:** É o guarda do laço! Ele verifica a condição de repetição: "O palpite digitado é diferente (`!=`) do número secreto?".
* **Explicação para iniciantes:**
  * Se `guess != randomNumber` for **verdadeiro** (ou seja, você errou o número): o Java volta imediatamente lá para a linha 18 (`do{`) e pede outro chute! O jogo continua rodando.
  * Se `guess != randomNumber` for **falso** (ou seja, `guess` é IGUAL ao `randomNumber`, você acertou!): o laço se quebra e a repetição acaba. O Java segue para a linha seguinte.
* **⚠️ Detalhe crucial:** Todo laço `do-while` é obrigado a terminar com ponto e vírgula `;` no final dessa linha!
* **Console:** **Nada aparece no console.**

---

### **Linha 35:** *(Linha em branco)*
* **Console:** **Nada aparece no console.**

---

### **Linha 36:** `scanner.close();`
* **O que faz:** Desliga o leitor de teclado.
* **Explicação para iniciantes:** É uma boa prática de programação. Quando o jogo acaba, devolvemos a memória do teclado para o sistema operacional para não deixar recursos vazando no computador.
* **Memória:** Libera o objeto `Scanner` da memória.
* **Console:** **Nada aparece no console.**

---

### **Linha 37:** `}`
* **O que faz:** Fecha o método `public static void main`. Fim das instruções do programa!
* **Console:** O IntelliJ exibe no final: `Process finished with exit code 0` (indicando que o programa encerrou perfeitamente sem nenhum erro).

---

### **Linha 38:** `}`
* **O que faz:** Fecha a classe `public class Main`.
* **Console:** **Nada aparece no console.**

---

## 🕹️ Simulação Real de Jogo no Console (Passo a Passo)

Vamos ver exatamente o que acontece na tela do console durante duas partidas completas, idênticas às que aparecem nos vídeos do Bro Code:

### Partida 1: Jogo de 1 a 10 (Imagens 2 e 3 do Professor)
* **Número secreto sorteado pelo computador:** `10`
* **Memória inicial:** `attempts = 0`

```text
Number Guessing Game
Guess a number between 1-10
Enter a guess: 5
TOO LOW! Try again
Enter a guess: 8
TOO LOW! Try again
Enter a guess: 9
TOO LOW! Try again
Enter a guess: 10
CORRECT! The number was 10
# of attempts: 5

Process finished with exit code 0
```

* **Explicação do que aconteceu:**
  1. Chutou `5` -> `5 < 10` é `true` -> imprime `TOO LOW! Try again`. `attempts` virou 1. `5 != 10` -> repete o loop.
  2. Chutou `8` -> `8 < 10` é `true` -> imprime `TOO LOW! Try again`. `attempts` virou 2. `8 != 10` -> repete o loop.
  3. Chutou `9` -> `9 < 10` é `true` -> imprime `TOO LOW! Try again`. `attempts` virou 3. `9 != 10` -> repete o loop.
  4. (Houve outro chute intermediário até chegar a 5 tentativas).
  5. Chutou `10` -> `10 < 10` é `false`, `10 > 10` é `false` -> cai no `else`!
     Imprime: `CORRECT! The number was 10`
     Imprime: `# of attempts: 5`
     Verificação do laço: `10 != 10` é **falso**! O laço termina e o programa encerra!

---

### Partida 2: Jogo Dinâmico de 1 a 100 (Imagem 4 do Professor)
* **Número secreto sorteado pelo computador:** `4`
* **Memória inicial:** `min = 1`, `max = 100`, `attempts = 0`

```text
Number Guessing Game
Guess a number between 1-100
Enter a guess: 50
TOO HIGH! Try again
Enter a guess: 25
TOO HIGH! Try again
Enter a guess: 10
TOO HIGH! Try again
Enter a guess: 2
TOO LOW! Try again
Enter a guess: 3
TOO LOW! Try again
Enter a guess: 4
CORRECT! The number was 4
# of attempts: 6

Process finished with exit code 0
```

---

## 💡 Resumo dos Novos Conceitos para Guardar na Memória

| Conceito / Código | O que significa? | Por que usamos aqui? |
| :--- | :--- | :--- |
| `if(guess < randomNumber)` | "Se chute for menor que o sorteado" | Dá a dica para o jogador chutar mais alto (`TOO LOW!`). |
| `else if(guess > randomNumber)` | "Se chute for maior que o sorteado" | Dá a dica para o jogador chutar mais baixo (`TOO HIGH!`). |
| `else` | "Se não for menor nem maior" | Só pode ser igual! Acertou na mosca (`CORRECT!`). |
| `attempts++` | Soma +1 na variável `attempts` | Conta quantos chutes o jogador deu até acertar. |
| `int min = 1; int max = 100;` | Variáveis de limite mínimo e máximo | Permite mudar a dificuldade do jogo em um só lugar. |
| `random.nextInt(min, max + 1)` | Sorteia de `min` até `max` | O Java exclui o último número, por isso somamos `+ 1` para incluir o 100. |
| `printf("%d-%d\n", min, max)` | Texto formatado | Troca os `%d` pelos números das variáveis `min` e `max`. |
| `scanner.close()` | Fecha o leitor | Boa prática para economizar memória do computador. |
