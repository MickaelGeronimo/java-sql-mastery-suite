# ➿ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 25: Laços Aninhados (`nested loops`) — Parte 5: O Gerador de Matriz Completo e Funcional
**Timestamps do vídeo:** `4:02:40` até `4:04:05 / 12:00:00`  
**Tema:** Conclusão do projeto do Gerador de Matriz Dinâmico: unindo o laço externo das linhas (`rows`) com o laço interno das colunas (`columns`), o teste sem quebra de linha com 18 arrobas (`@`) e a vitória final com 5 linhas de 3 e-comerciais (`&`).

---

## 🖼️ Contexto Visual dos Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Minuto 4:02:50 (Juntando os Laços e o Teste Sem Quebra):**
   * O professor encapsula o laço das colunas dentro do laço das linhas:
     * Laço de fora: `for(int i = 0; i < rows; i++)`
     * Laço de dentro: `for(int j = 0; j < columns; j++)`
   * Mas ele faz um **teste rápido esquecendo de propósito o `System.out.println();`**:
     * Ele digita `3` para linhas.
     * Digita `6` para colunas.
     * Digita o símbolo `@`.
   * **O resultado no console:** Como não tinha a quebra de linha, o Java multiplicou $3 \times 6 = 18$ e cuspiu **dezoito arrobas seguidas na mesma linha horizontal**:
     ```text
     @@@@@@@@@@@@@@@@@@
     ```
2. **Minuto 4:03:51 (Adicionando o `println()` e o Teste Perfeito):**
   * O professor adiciona a linha mágica `System.out.println();` logo após o fechamento do laço `j`.
   * Ele roda o programa com novos valores para provar que a matriz é 100% dinâmica:
     * Digita `5` para linhas (`rows`).
     * Digita `3` para colunas (`columns`).
     * Digita o símbolo `&` (e-comercial).
   * **O resultado no console:** Uma matriz retangular perfeita de 5 linhas com 3 e-comerciais cada:
     ```text
     &&&
     &&&
     &&&
     &&&
     &&&
     ```
   * O programa encerra com sucesso absoluto (`Process finished with exit code 0`).
3. **Minuto 4:04:05 (Visão Geral Final do Código):**
   * O professor rola o código para o topo, mostrando a visão geral completa do arquivo `Main.java` limpo e organizado, pronto para finalizar o capítulo.

---

## 🧠 Como os Dois Laços Trabalham Juntos? (A Dança do `i` e do `j`)

Pense nisso como um **carimbo mecânico em uma esteira de fábrica**:

* **O laço `i` (Linhas / Rows):** É o operador que posiciona uma nova linha de papel na esteira.
* **O laço `j` (Colunas / Columns):** É o carimbo veloz que bate rapidamente os símbolos lado a lado na mesma linha.
* **O `System.out.println();`:** É o botão que ejeta o papel e desce para a linha seguinte.

### Veja o filme em câmera lenta para 5 linhas e 3 colunas (`&`):
1. **Linha 1 (`i = 0`):**
   * O laço `j` roda 3 vezes: imprime `&`, depois `&`, depois `&` $\rightarrow$ `&&&`
   * O laço `j` acaba.
   * O `System.out.println()` bate o Enter $\rightarrow$ cursor vai para o início da Linha 2.
2. **Linha 2 (`i = 1`):**
   * O laço `j` roda 3 vezes: imprime `&&&`
   * O `System.out.println()` bate o Enter $\rightarrow$ cursor vai para a Linha 3.
3. **Linha 3 (`i = 2`):**
   * O laço `j` roda 3 vezes: imprime `&&&`
   * O `System.out.println()` bate o Enter $\rightarrow$ cursor vai para a Linha 4.
4. **Linha 4 (`i = 3`):**
   * O laço `j` roda 3 vezes: imprime `&&&`
   * O `System.out.println()` bate o Enter $\rightarrow$ cursor vai para a Linha 5.
5. **Linha 5 (`i = 4`):**
   * O laço `j` roda 3 vezes: imprime `&&&`
   * O `System.out.println()` bate o Enter.
6. **Fim (`i = 5`):** `5 < 5` é falso. O laço externo morre! O retângulo está concluído com perfeição!

---

## 💻 Código-Fonte Final Completo (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // nested loop = A loop inside another loop
        //               Used often with matrices or DS&A

        Scanner scanner = new Scanner(System.in);

        int rows;
        int columns;
        char symbol;

        System.out.print("Enter the # of rows: ");
        rows = scanner.nextInt();

        System.out.print("Enter the # of columns: ");
        columns = scanner.nextInt();

        System.out.print("Enter the symbol to use: ");
        symbol = scanner.next().charAt(0);

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(symbol);
            }
            System.out.println();
        }

        scanner.close();
    }
}
```

---

## 🔍 Enumeração Linha por Linha com Memória RAM e Console:

* **Linha 1:** `import java.util.Scanner;` $\rightarrow$ Importa o leitor de teclado. *(Console: Nada)*
* **Linha 2:** *(Linha em branco).*
* **Linha 3:** `public class Main {` $\rightarrow$ Casca da classe. *(Console: Nada)*
* **Linha 4:** `public static void main(String[] args){` $\rightarrow$ Ponto inicial. *(Console: Nada)*
* **Linhas 5 a 7:** Comentários explicativos. *(Console: Nada)*
* **Linha 8:** *(Linha em branco).*
* **Linha 9:** `Scanner scanner = new Scanner(System.in);` $\rightarrow$ Constrói o leitor do teclado. *(Console: Nada)*
* **Linha 10:** *(Linha em branco).*
* **Linha 11:** `int rows;` $\rightarrow$ Gaveta para armazenar a quantidade de linhas. *(Console: Nada)*
* **Linha 12:** `int columns;` $\rightarrow$ Gaveta para armazenar a quantidade de colunas. *(Console: Nada)*
* **Linha 13:** `char symbol;` $\rightarrow$ Gaveta para armazenar o caractere do desenho. *(Console: Nada)*
* **Linha 14:** *(Linha em branco).*
* **Linha 15:** `System.out.print("Enter the # of rows: ");`  
  * **Console:**
    ```text
    Enter the # of rows: 
    ```
* **Linha 16:** `rows = scanner.nextInt();`  
  * **Memória RAM:** Se você digitou `5`, salva `5` em `rows`.  
  * **Console:** Mostra o `5` digitado.
* **Linha 17:** *(Linha em branco).*
* **Linha 18:** `System.out.print("Enter the # of columns: ");`  
  * **Console:**
    ```text
    Enter the # of columns: 
    ```
* **Linha 19:** `columns = scanner.nextInt();`  
  * **Memória RAM:** Se você digitou `3`, salva `3` em `columns`.  
  * **Console:** Mostra o `3` digitado.
* **Linha 20:** *(Linha em branco).*
* **Linha 21:** `System.out.print("Enter the symbol to use: ");`  
  * **Console:**
    ```text
    Enter the symbol to use: 
    ```
* **Linha 22:** `symbol = scanner.next().charAt(0);`  
  * **Memória RAM:** Se você digitou `&`, salva `'&'` em `symbol`.  
  * **Console:** Mostra o `&` digitado.
* **Linha 23:** *(Linha em branco).*
* **Linha 24:** `for(int i = 0; i < rows; i++){`  
  * **O que faz:** **LAÇO EXTERNO (Linhas).** Inicia `i = 0` e repete enquanto `i < rows` (0, 1, 2, 3, 4 $\rightarrow$ 5 voltas).  
  * **Console:** Nada nessa linha diretamente.
* **Linha 25:** `for(int j = 0; j < columns; j++){`  
  * **O que faz:** **LAÇO INTERNO (Colunas).** A cada volta de `i`, inicia `j = 0` e repete enquanto `j < columns` (0, 1, 2 $\rightarrow$ 3 voltas).  
  * **Console:** Nada nessa linha diretamente.
* **Linha 26:** `System.out.print(symbol);`  
  * **O que faz:** Imprime o símbolo sem pular de linha.  
  * **Console:** Imprime os símbolos lado a lado: `&&&`
* **Linha 27:** `}` $\rightarrow$ Fecha o laço `j` (terminou a fileira atual de colunas).
* **Linha 28:** `System.out.println();`  
  * **O que faz:** **A TECLA ENTER!** Pula para o início da linha de baixo.  
  * **Console:** Quebra de linha no terminal.
* **Linha 29:** `}` $\rightarrow$ Fecha o laço `i`. Faz `i++` e avança para a próxima linha.
* **Linha 30:** *(Linha em branco).*
* **Linha 31:** `scanner.close();` $\rightarrow$ Desliga o leitor de teclado.
* **Linha 32:** `}` $\rightarrow$ Fecha o método `main`.
* **Linha 33:** `}` $\rightarrow$ Fecha a classe `Main`.

---

## 📺 O que sai no Console no Teste Final Perfeito (Minuto 4:03:51):

```text
Enter the # of rows: 5
Enter the # of columns: 3
Enter the symbol to use: &
&&&
&&&
&&&
&&&
&&&

Process finished with exit code 0
```

Se você testar com:
* `rows = 4`, `columns = 10`, `symbol = *`: o programa desenha uma bandeira de $4 \times 10$ estrelas!
* `rows = 8`, `columns = 8`, `symbol = #`: o programa desenha um tabuleiro de xadrez de $8 \times 8$ com hashtags!
* **Quem manda na geometria e no tamanho agora é 100% o usuário através do poder dos laços aninhados!**
