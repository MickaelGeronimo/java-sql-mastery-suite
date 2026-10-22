# ♾️ Curso Completo de Java — Capítulo 21: While Loops (Parte 1)
## Introdução aos Laços de Repetição: A Batalha Definitiva entre `if` e `while`

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 21 (*21. while loops ♾️*), entre os minutos `3:21:31` e `3:22:50` do curso de 12 horas do Bro Code.  
> **A virada de chave no aprendizado:**  
> Até o capítulo anterior, todo código que escrevemos era executado **uma única vez** de cima para baixo. Se você usasse um `if`, o Java fazia a pergunta uma vez e ia embora. Agora, o professor Bro Code introduz um dos conceitos mais poderosos de toda a programação: os **Laços de Repetição (Loops)**!  
>  
> **O que o autor faz passo a passo nas 4 imagens enviadas:**  
>  
> * **Imagem 1 (Minuto 3:21:31 — A Definição):**  
>   O autor limpa a tela e escreve a definição em comentário:  
>   `// while loop = repeat some code forever while some condition remains true`  
>   *(while loop = repete algum código continuamente enquanto alguma condição permanecer verdadeira)*.  
>  
> * **Imagem 3 (Minuto 3:22:46 — A Tentativa Fraca com `if`):**  
>   Antes de usar o `while`, o autor cria uma variável vazia `String name = "";` e tenta resolver o problema usando um simples `if`:  
>   `if(name.isEmpty()){`  
>   `    System.out.print("Enter your name: ");`  
>   `    name = scanner.nextLine();`  
>   `}`  
>   `System.out.println("Hello " + name);`  
>  
> * **Imagem 2 (Minuto 3:22:50 — O Fracasso do `if`):**  
>   O autor roda o programa com o `if`. O console pede: `Enter your name:`.  
>   Ele explica: *"Se o usuário simplesmente apertar Enter sem digitar nada, o `if` só perguntou UMA vez! Ele não vai insistir, o programa segue em frente e imprime `Hello ` para ninguém!"*.  
>  
> * **Imagem 4 (A Solução Mágica com `while`):**  
>   O autor troca a palavra `if` pela palavra **`while`**:  
>   `while(name.isEmpty()){`  
>   `    System.out.print("Enter your name: ");`  
>   `    name = scanner.nextLine();`  
>   `}`  
>   Ele roda o programa e começa a apertar Enter sem digitar nada!  
>   Olhe o que aparece no terminal:  
>   `Enter your name:`  
>   `Enter your name:`  
>   `Enter your name:`  
>   O programa **se recusa a continuar** até que o usuário finalmente digite um nome de verdade!

---

## 🧐 O Que é um `while loop`? (A Analogia do Cobrador Teimoso)

Para entender a diferença monumental entre `if` e `while`, imagine uma situação do dia a dia:

### 1. O Guarda Preguiçoso (`if`):
Você chega na porta de um prédio sem crachá.  
O guarda olha para você uma única vez e diz: *"Por favor, coloque seu crachá"*.  
Se você ignorar e der um passo para frente, ele não faz mais nada. Você entra de qualquer jeito!  
O `if` testa a condição **apenas uma vez** e a vida segue.

### 2. O Cobrador Teimoso (`while`):
A palavra inglesa **`while`** significa **"ENQUANTO"**.  
O cobrador para na sua frente e diz:  
> *"**ENQUANTO** você não me der seu nome, eu não vou sair da sua frente!"*

- Você dá Enter vazio $\rightarrow$ O nome continua vazio? **SIM**. Ele pergunta de novo: *"Enter your name:"*.
- Você dá Enter vazio de novo $\rightarrow$ Continua vazio? **SIM**. Ele pergunta de novo: *"Enter your name:"*.
- Você tenta dar Enter 100 vezes $\rightarrow$ Ele vai perguntar 100 vezes!
- Você finalmente digita `"Bro"` $\rightarrow$ Continua vazio? **NÃO!**. A condição virou `false`, ele sai da sua frente e o programa imprime `Hello Bro`!

---

## 📭 O Que é `String name = "";` e o Método `.isEmpty()`?

Na linha 8, o autor escreveu:
```java
String name = "";
```
Repare que não há espaço entre as aspas `""`. Isso se chama **String Vazia** (em inglês, *empty string*). É como uma caixa de sapato que existe na estante, mas está totalmente sem nada dentro.

Na linha 10, ele usa o método:
```java
name.isEmpty()
```
O método **`.isEmpty()`** (que significa *"está vazio?"*) faz uma pergunta simples para a caixa:
- Se a caixa estiver sem nenhuma letra $\rightarrow$ responde **`true`** (verdadeiro, está vazio!).
- Se a caixa tiver pelo menos uma letra (ex: `"A"`, `"Bro"`) $\rightarrow$ responde **`false`** (falso, não está mais vazio!).

---

## 📜 Código-Fonte Completo (`Main.java`)

Abaixo está o código exatamente como o professor deixou funcionando na Imagem 4:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name = "";

        while (name.isEmpty()) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
        }

        System.out.println("Hello " + name);

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 20)

* **Linha 1: `import java.util.Scanner;`**  
  Importa o leitor de teclado `Scanner` para conseguirmos ler o nome digitado no terminal.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Organização visual.

* **Linha 3: `public class Main {`**  
  Declara a classe pública chamada `Main`. Todo o programa Java reside dentro de uma classe.

* **Linha 4: `    public static void main(String[] args) {`**  
  O ponto de partida do Java. É aqui que o programa começa a rodar quando apertamos Play.

* **Linha 5: *(Linha em branco)***  
  Organização visual.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` apontado para o teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Organização visual.

* **Linha 8: `        String name = "";`**  
  Cria a variável de texto chamada `name` e inicializa ela com uma string vazia `""` (zero letras).  
  *No console:* Não exibe nada.

* **Linha 9: *(Linha em branco)***  
  Organização visual.

* **Linha 10: `        while (name.isEmpty()) {`** ⭐ **(O CORAÇÃO DO WHILE LOOP)**  
  O Java faz a checagem: *"A variável `name` está vazia?"*.  
  - No início, `name` vale `""`, então `name.isEmpty()` dá **`true`**!  
  - Como deu verdadeiro, o Java entra pelas chaves `{` e executa as linhas 11 e 12.  
  - Ao chegar na linha 13 (a chave que fecha), o Java **NÃO vai embora**! Ele pega um elevador e **volta para a linha 10** para perguntar de novo!

* **Linha 11: `            System.out.print("Enter your name: ");`**  
  Imprime na tela o pedido do nome, mantendo o cursor na mesma linha.  
  *No console:* `Enter your name: `

* **Linha 12: `            name = scanner.nextLine();`**  
  Pausa a execução e espera o usuário digitar.  
  - Se o usuário apenas apertar **Enter**, nada é gravado em `name` (ele continua vazio `""`).  
  - Se o usuário digitar algo (ex: `Bro`), `name` passa a valer `"Bro"`.

* **Linha 13: `        }`** ⭐  
  A chave de retorno do loop! Quando a execução bate aqui, o Java é obrigado a dar a volta e retornar para a linha 10. Se a condição ainda for `true`, o ciclo se repete.

* **Linha 14: *(Linha em branco)***  
  Organização visual.

* **Linha 15: `        System.out.println("Hello " + name);`**  
  Só é alcançada quando o usuário finalmente sai da "prisão" do while! Imprime a saudação com o nome preenchido.  
  *No console:* `Hello Bro`

* **Linha 16: *(Linha em branco)***  
  Organização visual.

* **Linha 17: `        scanner.close();`**  
  Fecha o scanner liberando a memória do sistema operacional.

* **Linha 18: *(Linha em branco)***  
  Organização visual.

* **Linha 19: `    }`**  
  Fecha o método `main`.

* **Linha 20: `}`**  
  Fecha a classe `Main`.

---

## 🖥️ Demonstração Real do Console (O que Acontece na Imagem 4)

Veja exatamente o que aconteceu quando o professor Bro Code testou o programa:

```text
Enter your name: [Apertou Enter vazio!]
Enter your name: [Apertou Enter vazio de novo!]
Enter your name: [Apertou Enter vazio pela terceira vez!]
```
O console continuou travado pedindo o nome!  
Se o professor agora digitar `Bro` e teclar Enter:
```text
Enter your name: Bro
Hello Bro

Process finished with exit code 0
```

---

## ⚠️ CUIDADO: O Pesadelo do "Loop Infinito" (*Infinite Loop*)

E se nós tivéssemos esquecido de colocar a linha 12 (`name = scanner.nextLine();`)?
```java
// ❌ CÓDIGO PERIGOSO (NÃO FAÇA ISSO!):
while (name.isEmpty()) {
    System.out.print("Enter your name: ");
    // sem o scanner!
}
```
Como nunca daríamos a chance da variável `name` mudar de valor, ela seria vazia **para sempre**!  
O computador começaria a imprimir `Enter your name:` bilhões de vezes por segundo até travar o terminal ou esquentar o processador!  
> **Regra de ouro do `while`:** Sempre garanta que dentro do bloco de repetição exista algo que possa tornar a condição **falsa** no futuro!
