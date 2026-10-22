# ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 5 - Conclusão)
## Validação Completa de Cadastro com `.contains()` e `||` (OR)

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 20 (*20. logical operators ❕*), entre os minutos `3:19:40` e `3:20:49` do curso de 12 horas do Bro Code.  
> **O desafio que o autor resolve nestas 4 imagens:**  
> No vídeo anterior, o autor implementou a primeira regra de cadastro: o tamanho do nome de usuário (entre 4 e 12 caracteres). Agora, ele vai programar a segunda regra prometida no comentário da linha 9:  
> `// username must not contain spaces or underscores` (o nome **NÃO** pode ter espaços vazios nem sublinhados `_`).  
>  
> **A sequência passo a passo mostrada nas 4 imagens:**  
>  
> * **Na Imagem 1 (Minuto 3:19:40):**  
>   O autor aponta o cursor para o comentário da linha 9, mostrando que a regra de proibir espaços e underlines ainda precisa de código para existir de verdade.  
>  
> * **Na Imagem 2 (Minuto 3:20:32 — Escrevendo o `.contains()` e Testando o Espaço):**  
>   Ele adiciona entre o `if` e o `else` um novo bloco `else if`:  
>   `else if(username.contains(" ") || username.contains("_")){`  
>   `    System.out.println("Username must not contain spaces or underscores");`  
>   `}`  
>   Ele clica no Play e testa digitando: `Bro Code` (repare no espaço no meio!).  
>   Mesmo tendo 8 letras (tamanho perfeito), o Java detecta o espaço proibido e avisa no console:  
>   `Username must not contain spaces or underscores`  
>  
> * **Na Imagem 3 (Minuto 3:20:42 — Testando o Sublinhado `_`):**  
>   O autor roda o programa novamente e digita: `Bro_Code`.  
>   O Java verifica que não tem espaço, mas encontra o underline `_`. O operador `||` acusa erro de novo e o console imprime:  
>   `Username must not contain spaces or underscores`  
>  
> * **Na Imagem 4 (Minuto 3:20:49 — O Teste do Sucesso Definitivo):**  
>   Ele roda uma terceira vez e digita o nome limpo: `BroCode` (sem espaços e sem underlines).  
>   O programa passa direto pelos dois testes de erro e cai no `else`!  
>   O console imprime a saudação verde:  
>   `Welcome BroCode`

---

## 🧐 O Que é o Método `.contains()`? (Para Iniciantes Amadores)

Imagine que a palavra digitada pelo usuário é uma mochila:
- O método **`.contains()`** (que significa *"contém"* em inglês) é um cão farejador da polícia!
- Você aponta para a mochila e pergunta para o cão farejador:  
  `username.contains(" ")` $\rightarrow$ *"Tem algum espaço em branco aí dentro?"*
- O cão farejador só sabe responder duas coisas na vida:
  - **`true`**: Sim, achei!
  - **`false`**: Não, está limpo!

E por que usamos o operador **`||` (OU)** na linha 19?
```java
else if (username.contains(" ") || username.contains("_"))
```
Porque o nome de usuário é proibido se tiver um espaço **OU** se tiver um sublinhado! Basta ter um dos dois para o cadastro ser rejeitado!

---

## 🚦 A Escadinha de Decisões (`if` $\rightarrow$ `else if` $\rightarrow$ `else`)

Pense como uma esteira de triagem de aeroporto:
1. **Primeira Checagem (`if`):** Mede o tamanho da bagagem com `.length()`. Se for menor que 4 ou maior que 12, apita o alarme e para tudo!
2. **Segunda Checagem (`else if`):** Se o tamanho passou, o raio-x verifica o conteúdo com `.contains()`. Se tiver espaço ou underline, apita o alarme e para tudo!
3. **Aprovação Final (`else`):** Se não caiu em nenhum erro, o passageiro está liberado e recebe as boas-vindas: `Welcome`!

---

## 📜 Código-Fonte Completo e Definitivo (`Main.java`)

Abaixo está o código completo exatamente como está na tela do professor:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // username must be between 4-12 characters
        // username must not contain spaces or underscores

        String username;

        System.out.print("Enter your new username: ");
        username = scanner.nextLine();

        if (username.length() < 4 || username.length() > 12) {
            System.out.println("Username must be between 4-12 characters");
        }
        else if (username.contains(" ") || username.contains("_")) {
            System.out.println("Username must not contain spaces or underscores");
        }
        else {
            System.out.println("Welcome " + username);
        }

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 28)

* **Linha 1: `import java.util.Scanner;`**  
  Importa o `Scanner` da biblioteca do Java para permitir a leitura do teclado.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Organização visual.

* **Linha 3: `public class Main {`**  
  Declaração da classe principal `Main`.

* **Linha 4: `    public static void main(String[] args) {`**  
  O método principal `main` onde o Java inicia a execução.

* **Linha 5: *(Linha em branco)***  
  Organização visual.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` conectado ao teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Organização visual.

* **Linha 8: `        // username must be between 4-12 characters`**  
  Comentário da primeira regra (tamanho do texto).  
  *No console:* Não exibe nada.

* **Linha 9: `        // username must not contain spaces or underscores`**  
  Comentário da segunda regra (proibição de espaço e underline).  
  *No console:* Não exibe nada.

* **Linha 10: *(Linha em branco)***  
  Organização visual.

* **Linha 11: `        String username;`**  
  Cria a variável de texto `username` na memória para guardar o nome digitado.  
  *No console:* Não exibe nada.

* **Linha 12: *(Linha em branco)***  
  Organização visual.

* **Linha 13: `        System.out.print("Enter your new username: ");`**  
  Exibe na tela o pedido para digitar o novo usuário, mantendo o cursor na mesma linha.  
  *No console:* `Enter your new username: `

* **Linha 14: `        username = scanner.nextLine();`**  
  Congela o programa esperando o usuário digitar o nome e pressionar Enter. Guarda tudo na variável `username`.

* **Linha 15: *(Linha em branco)***  
  Organização visual.

* **Linha 16: `        if (username.length() < 4 || username.length() > 12) {`**  
  **Primeiro Teste (Tamanho):**  
  Calcula quantas letras tem o nome. Se for menor que 4 OU maior que 12, dá `true` e entra no erro.

* **Linha 17: `            System.out.println("Username must be between 4-12 characters");`**  
  Imprime a mensagem avisando do erro de tamanho.  
  *No console:* `Username must be between 4-12 characters`

* **Linha 18: `        }`**  
  Fecha a chave do `if`.

* **Linha 19: `        else if (username.contains(" ") || username.contains("_")) {`** ⭐ **(A GRANDE NOVIDADE DO VÍDEO)**  
  **Segundo Teste (Caracteres Proibidos):**  
  - `username.contains(" ")`: Pergunta se o nome tem espaço vazio.  
  - `username.contains("_")`: Pergunta se o nome tem underline.  
  - O operador `||` (OU) conecta as duas: se tiver espaço **OU** se tiver underline, o resultado vira **`true`** e o Java entra no bloco!

* **Linha 20: `            System.out.println("Username must not contain spaces or underscores");`** ⭐  
  Imprime o aviso de caracteres inválidos.  
  *No console:* `Username must not contain spaces or underscores`

* **Linha 21: `        }`**  
  Fecha a chave do `else if`.

* **Linha 22: `        else {`**  
  **O Caminho do Sucesso:**  
  Só chega aqui se o nome NÃO for curto/longo e NÃO tiver espaço/underline.

* **Linha 23: `            System.out.println("Welcome " + username);`**  
  Imprime a mensagem de boas-vindas com o nome aprovado.  
  *No console:* `Welcome BroCode`

* **Linha 24: `        }`**  
  Fecha a chave do `else`.

* **Linha 25: *(Linha em branco)***  
  Organização visual.

* **Linha 26: `        scanner.close();`**  
  Encerra o leitor do teclado liberando memória do sistema operacional.

* **Linha 27: `    }`**  
  Fecha o método `main`.

* **Linha 28: `}`**  
  Fecha a classe `Main`.

---

## 🧪 Demonstração dos 3 Testes Feitos no Console

### Teste 1: Nome com Espaço Proibido (Imagem 2 - Minuto 3:20:32)
* **Entrada:** `Bro Code`
* **Análise do Java:**  
  - Tamanho: 8 letras (passou no primeiro `if`).  
  - Contém espaço `" "`? **Sim (`true`)**!  
  - Entra no `else if`!
* **Console:**
  ```text
  Enter your new username: Bro Code
  Username must not contain spaces or underscores

  Process finished with exit code 0
  ```

---

### Teste 2: Nome com Underline Proibido (Imagem 3 - Minuto 3:20:42)
* **Entrada:** `Bro_Code`
* **Análise do Java:**  
  - Tamanho: 8 letras (passou no primeiro `if`).  
  - Contém espaço `" "`? Não (`false`).  
  - Contém underline `"_"`? **Sim (`true`)**!  
  - `false || true` dá **`true`**! Entra no `else if`!
* **Console:**
  ```text
  Enter your new username: Bro_Code
  Username must not contain spaces or underscores

  Process finished with exit code 0
  ```

---

### Teste 3: Nome Válido e Perfeito (Imagem 4 - Minuto 3:20:49)
* **Entrada:** `BroCode`
* **Análise do Java:**  
  - Tamanho: 7 letras (OK!).  
  - Contém espaço? Não (`false`).  
  - Contém underline? Não (`false`).  
  - `false || false` dá **`false`**!  
  - Cai direto no `else`!
* **Console:**
  ```text
  Enter your new username: BroCode
  Welcome BroCode

  Process finished with exit code 0
  ```

---

## 🏆 Fim do Capítulo 20: Conquistas Desbloqueadas!
1. **Três Operadores Lógicos Dominados:**  
   - `&&` (AND): Conjunção total.  
   - `||` (OR): Disjunção flexível.  
   - `!` (NOT): Inversão total.  
2. **Métodos Práticos da String em Ação:**  
   - `.length()` para contagem de caracteres.  
   - `.contains(...)` para busca de partes de texto dentro de uma String.  
3. **Arquitetura de Validação:** Como encadear `if`, `else if` e `else` para construir um formulário profissional à prova de erros de digitação.
