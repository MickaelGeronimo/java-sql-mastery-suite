# ➿ Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 25: Laços Aninhados (`nested loops`) — Parte 3: A Solução com `j` e o Experimento do `println()` Vazio
**Timestamps do vídeo:** `3:58:41` até `3:59:00 / 12:00:00`  
**Tema:** Renomeando todos os 4 pontos de `i` para `j`, a execução impecável da grade 3x9 ($3 \times 9 = 27$ números impressos) e o teste de deletar o `System.out.println();` mostrando o efeito "cobra horizontal".

---

## 🖼️ Contexto Visual dos Prints (Para quem estuda pelo Google Docs sem as imagens)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Minuto 3:58:41 (Renomeando para `j`):**
   * O professor começa a corrigir o erro de compilação da aula anterior.
   * Ele clica sobre o segundo `i` no IntelliJ IDEA.
   * Ele mostra que **não basta mudar apenas a declaração** `int j = 1`. No laço interno, existem **4 lugares** onde a letra `i` precisa virar `j`:
     1. Na declaração: `int j = 1;`
     2. Na condição: `j <= 9;`
     3. No passo: `j++`
     4. Na impressão: `System.out.print(j + " ");`
2. **Minuto 3:58:50 (A Execução Perfeita da Grade 3x9):**
   * Ele clica no Play verde.
   * O console roda sem nenhum erro e exibe a linda matriz retangular 3x9:
     ```text
     1 2 3 4 5 6 7 8 9 
     1 2 3 4 5 6 7 8 9 
     1 2 3 4 5 6 7 8 9 
     ```
   * Ele comemora: *"Olhe que beleza! Apenas duas estruturas `for` e geramos todas as 3 linhas perfeitamente alinhadas!"*.
3. **Minuto 3:59:00 (O Experimento da Pegadinha):**
   * O professor diz: *"E se você achar que aquele `System.out.println();` não serve para nada e apagá-lo?"*.
   * Ele seleciona a linha do `System.out.println();` e aperta Backspace/Delete.
   * Ele roda o código sem o `println()` vazio e olha o que o console cuspiu:
     ```text
     1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 
     ```
   * Todos os 27 números saíram espremidos em **uma única linha horizontal infinita**!  
   * Isso prova para o aluno a importância vital daquela linha aparentemente vazia.

---

## 🔍 Os 4 Lugares Críticos Que Precisam Virar `j`

Um dos erros mais comuns de quem está aprendendo loops aninhados é trocar a variável apenas na declaração e esquecer o resto:

```java
// ❌ O "LAÇO ESQUIZOFRÊNICO" (ERRO GRAVE DE INICIANTE):
for(int i = 1; i <= 3; i++){
    for(int j = 1; i <= 9; i++){ // OLHE A CONFUSÃO AQUI!
        System.out.print(i + " ");
    }
}
```

### Por que o código acima é um desastre?
* Você começou declarando `int j = 1;`.
* Mas na hora de testar a condição, testou `i <= 9;`!
* E na hora de somar, somou `i++`!
* **Resultado:** O laço de fora e o de dentro vão brigar pela mesma variável `i`. O laço interno vai alterar o contador do laço externo e o programa vai entrar em comportamento imprevisível ou em loop infinito!

> 🧠 **Regra Clara para Nunca Mais Errar:**  
> O laço de dentro precisa ser **100% fiel à sua própria variável (`j`)**:
> 1. `int j = 1;` *(Onde o j começa)*
> 2. `j <= 9;` *(Até onde o j vai)*
> 3. `j++` *(Como o j avança)*
> 4. `System.out.print(j + " ");` *(O que o j imprime)*

---

## 💻 Código Perfeito da Grade 3x9 (Minuto 3:58:50)

```java
public class Main {
    public static void main(String[] args){

        // nested loop = A loop inside another loop
        //               Used often with matrices or DS&A

        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 9; j++){
                System.out.print(j + " ");
            }
            System.out.println(); // Pula para a próxima linha
        }

    }
}
```

### 🔍 Enumeração Linha por Linha com Memória RAM e Console:

* **Linha 1:** `public class Main {` $\rightarrow$ Casca da classe. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` $\rightarrow$ Início da execução. *(Console: Nada)*
* **Linhas 3 a 6:** Comentários explicativos. *(Console: Nada)*
* **Linha 7:** *(Linha em branco).*
* **Linha 8:** `for(int i = 1; i <= 3; i++){`  
  * **O que faz:** **LAÇO EXTERNO (Linhas).** Diz ao Java: *"Vou repetir todo o bloco interno exatamente 3 vezes (quando i valer 1, 2 e 3)"*.  
  * **Memória RAM:** Cria `i = 1`.  
  * **Console:** Nada nessa linha.
* **Linha 9:** `for(int j = 1; j <= 9; j++){`  
  * **O que faz:** **LAÇO INTERNO (Colunas).** A cada volta do `i`, esse laço zera o `j` no 1 e conta até 9.  
  * **Memória RAM:** Cria `j = 1`. A cada volta, atualiza para 2, 3... até 9.  
  * **Console:** Nada nessa linha diretamente.
* **Linha 10:** `System.out.print(j + " ");`  
  * **O que faz:** Imprime o número da coluna atual com um espaço. Como usa `print` sem `ln`, os números ficam colados lado a lado.  
  * **Console:** Imprime `1 2 3 4 5 6 7 8 9 `
* **Linha 11:** `}`  
  * **O que faz:** Fim da volta do `j`. Só sai daqui quando `j` atingir `10` (que torna `j <= 9` falso).  
  * **Memória RAM:** A variável `j` é destruída da memória.
* **Linha 12:** `System.out.println();`  
  * **O que faz:** **A ALAVANCA DO ENTER!** A primeira linha de 9 números terminou. Esta instrução move o cursor para o começo da linha de baixo!  
  * **Console:** Quebra para a próxima linha vazia.
* **Linha 13:** `}`  
  * **O que faz:** Fim da volta do `i`. Executa `i++` (agora `i = 2`) e recomeça todo o laço `j` na Linha 9!
* **Linhas 14 e 15:** Fechamento do `main` e da classe.

---

## 📊 Tabela de Rastreamento de Execução Passo a Passo

| Volta Externa (`i`) | O que está acontecendo? | Volta Interna (`j`) | Saída na Linha Atual do Console |
| :---: | :--- | :---: | :--- |
| **`i = 1`** (Linha 1) | Começa a primeira linha | `1, 2, 3, 4, 5, 6, 7, 8, 9` | `1 2 3 4 5 6 7 8 9 ` |
| `i = 1` | `j` atinge 10 $\rightarrow$ Sai do `j` | — | Executa `println()` $\rightarrow$ Pula linha! |
| **`i = 2`** (Linha 2) | Começa a segunda linha | `1, 2, 3, 4, 5, 6, 7, 8, 9` | `1 2 3 4 5 6 7 8 9 ` |
| `i = 2` | `j` atinge 10 $\rightarrow$ Sai do `j` | — | Executa `println()` $\rightarrow$ Pula linha! |
| **`i = 3`** (Linha 3) | Começa a terceira linha | `1, 2, 3, 4, 5, 6, 7, 8, 9` | `1 2 3 4 5 6 7 8 9 ` |
| `i = 3` | `j` atinge 10 $\rightarrow$ Sai do `j` | — | Executa `println()` $\rightarrow$ Pula linha! |
| **`i = 4`** | `4 <= 3` é Falso $\rightarrow$ Fim do laço `i`! | — | **Programa Finalizado com Sucesso!** |

---

## 😱 O Experimento do "Efeito Cobra" (Sem o `println`)

Se você remover a Linha 12:
```java
for(int i = 1; i <= 3; i++){
    for(int j = 1; j <= 9; j++){
        System.out.print(j + " ");
    }
    // Repare: SEM O println() AQUI!
}
```

### O que acontece no Console?
Como a instrução `System.out.print` **NUNCA pula de linha**, quando a primeira rodada de 1 a 9 termina, o cursor do terminal continua parado exatamente onde imprimiu o 9!  
A segunda rodada começa imediatamente colada na primeira, e a terceira começa imediatamente colada na segunda:

```text
1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 
```

> 💡 **Moral da História:**  
> A posição do `System.out.println();` é cirúrgica: ele deve ficar **após o fechamento do laço interno (`}` do j)** e **antes do fechamento do laço externo (`}` do i)**!
