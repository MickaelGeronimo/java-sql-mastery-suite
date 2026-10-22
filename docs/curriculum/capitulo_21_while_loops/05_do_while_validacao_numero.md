# ♾️ Curso Completo de Java — Capítulo 21: While Loops (Parte 4 - Final)
## Validação de Intervalo Numérico (1 a 10) e a Consagração do `do-while`

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 21 (*21. while loops ♾️*), entre os minutos `3:32:19` e `3:33:11` do curso de 12 horas do Bro Code.  
> **O fechamento com chave de ouro do capítulo:**  
> O professor Bro Code propõe o desafio clássico de validação: criar um programa onde o usuário é obrigado a escolher **um número entre 1 e 10**. Se o usuário digitar algo menor que 1 (como `-1` ou `0`) ou maior que 10 (como `11` ou `100`), o programa deve insistir até receber um número correto!  
>  
> **A evolução mostrada nas 4 imagens enviadas:**  
>  
> * **Imagem 1 (Minuto 3:32:19 — A Solução com "Truque" no `while` Comum):**  
>   O autor primeiro escreve o código usando o `while` tradicional:  
>   `int number = 0;`  
>   `while(number < 1 || number > 10){`  
>   `    System.out.print("Enter a number between 1 - 10: ");`  
>   `    number = scanner.nextInt();`  
>   `}`  
>   Repare no truque: ele inicializou `number = 0`. Por que zero? Porque zero é menor que 1, então a condição do `while` dá `true` logo na largada e força o loop a começar! Mas isso é uma "gambiarra" para contornar a limitação do `while`.  
>  
> * **Imagem 2 (Minuto 3:32:36 — Testando Vários Erros no Console):**  
>   O autor roda o programa e faz vários testes malucos:  
>   - Digita `-1` $\rightarrow$ o Java repete a pergunta!  
>   - Digita `11` $\rightarrow$ o Java repete a pergunta!  
>   - Digita `-2` $\rightarrow$ o Java repete a pergunta!  
>   - Digita `100000000000000000` $\rightarrow$ o Java continua insistindo!  
>  
> * **Imagem 3 (Minuto 3:33:07 — A Refatoração Perfeita com `do-while`):**  
>   O autor diz: *"Em vez de forçar `number = 0` para engambelar o `while`, a ferramenta perfeita feita sob medida para isso é o `do-while`!"*.  
>   Ele troca para:  
>   `do {`  
>   `    System.out.print("Enter a number between 1 - 10: ");`  
>   `    number = scanner.nextInt();`  
>   `} while(number < 1 || number > 10);`  
>   Ele testa no console digitando `100` (erro), `-4` (erro) e finalmente `5` (sucesso!).  
>   O console responde: `You picked 5` *(Você escolheu 5)*!  
>  
> * **Imagem 4 (Minuto 3:33:11 — Revisão e Fim de Capítulo):**  
>   O autor seleciona com o mouse a linha 13 (`} while(number < 1 || number > 10);`), faz um resumo geral de quando usar `while` e quando usar `do-while`, e conclui o Capítulo 21!

---

## 🧐 1. Entendendo a Condição: `number < 1 || number > 10`

Por que usamos o operador **`||` (OU)** para validar o intervalo de 1 a 10?

Pense na regra:  
- Os números permitidos são: **1, 2, 3, 4, 5, 6, 7, 8, 9 e 10**.  
- Qualquer coisa fora dessa ilha é proibidíssima!

Quando é que um número deve ser **REJEITADO** e fazer o loop repetir?
1. Se ele for **menor que 1** (`number < 1`) $\rightarrow$ Exemplo: `0`, `-1`, `-50`.
2. **OU** se ele for **maior que 10** (`number > 10`) $\rightarrow$ Exemplo: `11`, `25`, `100`.

Basta o número cometer **UMA** dessas duas infrações para o operador `||` dar `true` e fazer o loop rodar de novo!  
O loop **só quebra** e liberta o usuário quando o número NÃO for menor que 1 E NÃO for maior que 10 (ou seja, quando for entre 1 e 10)!

---

## 🧼 2. Por Que o `do-while` é Muito Mais Limpo e Profissional?

Compare as duas formas de pensar:

### O jeito "Gambiarra" com `while`:
```java
int number = 0; // 👈 Tivemos que forçar zero só para fazer o while ligar!
while (number < 1 || number > 10) {
    // pergunta...
}
```
Se amanhã você mudar o intervalo para "números entre -5 e 5", o zero seria um número válido, e o seu loop nem começaria! Você teria que mudar a gambiarra para -10. Isso gera bugs!

### O jeito Limpo e Natural com `do-while`:
```java
int number = 0;
do {
    // pergunta... (executa primeiro de forma 100% natural!)
} while (number < 1 || number > 10);
```
No `do-while`, você não precisa fingir nada! O programa pede o número primeiro e só julga depois. É a arquitetura perfeita para formulários e validações interativas.

---

## 📜 Código-Fonte Completo e Definitivo (`Main.java`)

Abaixo está o código final do arquivo `Main.java` (versão com `do-while` das Imagens 3 e 4):

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int number = 0;

        do {
            System.out.print("Enter a number between 1 - 10: ");
            number = scanner.nextInt();
        } while (number < 1 || number > 10);

        System.out.println("You picked " + number);

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 19)

* **Linha 1: `import java.util.Scanner;`**  
  Importa o leitor de teclado `Scanner` da biblioteca de utilitários do Java.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Organização visual.

* **Linha 3: `public class Main {`**  
  Declara a classe pública `Main`.

* **Linha 4: `    public static void main(String[] args) {`**  
  O método principal `main` (ponto de partida do programa).

* **Linha 5: *(Linha em branco)***  
  Organização visual.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` escutando o teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Organização visual.

* **Linha 8: `        int number = 0;`**  
  Cria a caixinha de memória de número inteiro chamada `number` guardando o valor inicial `0`.  
  *No console:* Não exibe nada.

* **Linha 9: *(Linha em branco)***  
  Organização visual.

* **Linha 10: `        do {`** ⭐ **(A LARGADA INCONDICIONAL)**  
  Abertura do laço `do-while`. O Java entra sem fazer nenhuma pergunta, garantindo que o pedido do número apareça na tela pelo menos uma vez.  
  *No console:* Não exibe nada nesta linha.

* **Linha 11: `            System.out.print("Enter a number between 1 - 10: ");`**  
  Exibe a instrução pedindo um número de 1 a 10. Usa `.print()` para que o cursor do teclado fique piscando logo após os dois pontos.  
  *No console:* `Enter a number between 1 - 10: `

* **Linha 12: `            number = scanner.nextInt();`**  
  Pausa o programa e espera o usuário digitar um número inteiro (ex: `100`, `-4` ou `5`) e apertar Enter. O número digitado substitui o valor de `number`.

* **Linha 13: `        } while (number < 1 || number > 10);`** ⭐ **(O FILTRO COM `||` E `;`)**  
  O Java faz o teste duplo com o operador OU (`||`):  
  - O número é menor que 1?  
  - OU o número é maior que 10?  
  Se qualquer uma das duas for verdadeira, o Java sobe para a linha 10 e repete!  
  Se o número estiver entre 1 e 10 (ex: `5`), ambas as perguntas dão falso (`false || false` = `false`), quebrando o loop!  
  *(Atenção: o ponto e vírgula `;` no final é obrigatório!).*

* **Linha 14: *(Linha em branco)***  
  Organização visual.

* **Linha 15: `        System.out.println("You picked " + number);`**  
  Só é executada quando o usuário finalmente digita um número válido. Imprime a confirmação do número escolhido.  
  *No console:* `You picked 5`

* **Linha 16: *(Linha em branco)***  
  Organização visual.

* **Linha 17: `        scanner.close();`**  
  Fecha o scanner liberando a memória do computador.

* **Linha 18: `    }`**  
  Fecha o método `main`.

* **Linha 19: `}`**  
  Fecha a classe `Main`.

---

## 🖥️ A Demonstração dos Testes no Console (Imagens 2 e 3)

### Teste da Imagem 2 (Insistência com Vários Erros):
```text
Enter a number between 1 - 10: -1   (Menor que 1! Repetiu)
Enter a number between 1 - 10: 11   (Maior que 10! Repetiu)
Enter a number between 1 - 10: -2   (Menor que 1! Repetiu)
Enter a number between 1 - 10: 100000000000000000
```

---

### Teste da Imagem 3 (A Rodada Completa com Sucesso):
```text
Enter a number between 1 - 10: 100
Enter a number between 1 - 10: -4
Enter a number between 1 - 10: 5
You picked 5

Process finished with exit code 0
```

### Rastreamento do Teste Vencedor:
1. Usuário digitou `100`: $100 > 10$ é **`true`** $\rightarrow$ o loop repete!
2. Usuário digitou `-4`: $-4 < 1$ é **`true`** $\rightarrow$ o loop repete!
3. Usuário digitou `5`: $5 < 1$ é `false` e $5 > 10$ é `false`. Resultado: **`false`**! O loop encerra e o programa imprime `You picked 5`!

---

## 🏆 Fim do Capítulo 21: Tudo o que Você Dominou em Loops!

Parabéns! Você fechou o **Capítulo 21** e agora domina os laços de repetição como um verdadeiro programador Java:
1. **Conceito de Loop:** Repetir instruções sem ter que duplicar linhas de código.
2. **`while` (Checagem Prévia):** Roda de 0 a infinitas vezes. Bom para jogos (`while(!response.equals("Q"))`) e verificações que dependem de estado anterior.
3. **`do-while` (Checagem Posterior):** Roda de 1 a infinitas vezes. O campeão absoluto para validação de entrada de dados do usuário (`do { input } while(invalido);`).
4. **Fuga de Loops Infinitos:** Como usar o botão Stop do IntelliJ e a importância de alterar a variável de teste dentro do loop.
