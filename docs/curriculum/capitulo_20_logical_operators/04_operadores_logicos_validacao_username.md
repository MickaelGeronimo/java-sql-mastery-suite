# ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 4)
## Aplicação Prática no Mundo Real: Validação de Nome de Usuário com `username.length()` e `||` (OR)

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 20 (*20. logical operators ❕*), entre os minutos `3:16:47` e `3:19:00` do curso de 12 horas do Bro Code.  
> **A mudança de rumo do professor:**  
> Depois de ensinar a teoria do clima com `temp` e `isSunny`, o professor Bro Code limpa a tela e diz: *"Agora vamos construir algo que você vai usar em praticamente todo sistema real: uma validação de cadastro de usuário (Username Validation)!"*.  
>  
> **O que acontece nas 4 imagens enviadas:**  
> 
> * **Imagem 1 (Minuto 3:16:47 — A Tela Limpa):**  
>   O autor apaga todo o código do clima dentro do método `main`, deixando apenas os comentários com os símbolos dos 3 operadores (`&&`, `||`, `!`) para iniciar o novo exemplo do zero.  
> 
> * **Imagem 2 (Minuto 3:18:47 — Criando as Regras de Cadastro):**  
>   Ele adiciona as regras de negócio em comentários:  
>   `// username must be between 4-12 characters` (o nome deve ter entre 4 e 12 letras)  
>   `// username must not contain spaces or underscores` (não pode ter espaços nem sublinhados)  
>   Ele cria o `Scanner`, lê o nome digitado com `scanner.nextLine()` e escreve o teste com `||`:  
>   `if(username.length() < 4 || username.length() > 12)`  
>   Se for menor que 4 **OU** maior que 12, exibe o aviso de erro!  
> 
> * **Imagem 3 (Minuto 3:18:57 — O Teste do Nome Curto):**  
>   O autor roda o programa e digita o nome: `Bro`.  
>   O console responde imediatamente com o erro:  
>   `Username must be between 4-12 characters`  
>   Ele explica: *"A palavra 'Bro' tem apenas 3 letras. Como 3 é menor que 4, o `||` acusou o erro e barrou o cadastro!"*.  
> 
> * **Imagem 4 (Minuto 3:19:30 aprox. — Adicionando o `else` e Testando o Sucesso):**  
>   O autor adiciona o bloco `else` com a mensagem `System.out.println("Welcome " + username);`.  
>   Ele roda o programa novamente e digita: `BroCode`.  
>   Como `"BroCode"` tem 7 letras (está perfeitamente entre 4 e 12), o `||` deu falso para os dois erros, caindo no `else`!  
>   O console imprime feliz: `Welcome BroCode`.

---

## 🧐 A Lógica do `username.length()` e Por Que Usamos `||` (OR)?

### 1. O que é esse tal de `.length()`?
Em Java, todo texto (`String`) tem um "contador de letrinhas embutido" chamado `.length()` (que significa "comprimento" ou "tamanho" em inglês):
- Se a variável `username` guardar `"Bro"`, `username.length()` vale **`3`**.
- Se guardar `"BroCode"`, `username.length()` vale **`7`**.
- Se guardar `"Ana"`, `username.length()` vale **`3`**.

---

### 2. Por que usamos `||` (OU) e NÃO `&&` (E) para testar o erro?
Pense como um fiscal de segurança: quando é que um nome de usuário é **inválido**?
* Ele é inválido se for **curto demais** (`username.length() < 4`)
* **OU** se for **longo demais** (`username.length() > 12`).

Poderíamos usar `&&` aqui? **JAMAIS!**  
Se você escrevesse com `&&`:  
`if (username.length() < 4 && username.length() > 12)`  
Você estaria perguntando: *"O nome tem menos de 4 letras E AO MESMO TEMPO mais de 12 letras?"*.  
Isso é uma impossibilidade física e matemática! Nenhuma palavra no universo pode ter 3 letras e 15 letras ao mesmo tempo.  
Por isso, para detectar erros de extremos (muito pequeno **OU** muito grande), o operador perfeito é o **`||` (OR)**!

---

## 📜 Código-Fonte Completo e Definitivo (`Main.java`)

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
        else {
            System.out.println("Welcome " + username);
        }

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 25)

* **Linha 1: `import java.util.Scanner;`**  
  Traz a ferramenta do `Scanner` do pacote de utilitários do Java para que o programa consiga ler o que o usuário digita no teclado.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Organização visual.

* **Linha 3: `public class Main {`**  
  Declara a classe principal `Main` onde todo o nosso código reside.

* **Linha 4: `    public static void main(String[] args) {`**  
  O método principal `main`. É onde o Java dá a partida quando você aperta o botão Play.

* **Linha 5: *(Linha em branco)***  
  Organização visual.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` apontado para o teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Separação visual.

* **Linha 8: `        // username must be between 4-12 characters`**  
  Comentário de regra de negócio: o nome de usuário deve ter entre 4 e 12 caracteres.  
  *No console:* Não exibe nada.

* **Linha 9: `        // username must not contain spaces or underscores`**  
  Comentário de regra de negócio: o nome não pode conter espaços nem o caractere sublinhado (`_`). *(Essa regra será implementada no próximo passo com o `!` ou outros métodos!)*.  
  *No console:* Não exibe nada.

* **Linha 10: *(Linha em branco)***  
  Organização visual.

* **Linha 11: `        String username;`**  
  Declara a variável `username` do tipo texto (`String`) que guardará o apelido digitado pelo usuário.  
  *No console:* Não exibe nada.

* **Linha 12: *(Linha em branco)***  
  Organização visual.

* **Linha 13: `        System.out.print("Enter your new username: ");`**  
  Imprime a instrução na tela pedindo o nome de usuário. Como usa `.print()`, o cursor fica esperando na mesma linha.  
  *No console:* `Enter your new username: `

* **Linha 14: `        username = scanner.nextLine();`**  
  Pausa o programa e espera o usuário digitar o nome e apertar Enter. O texto digitado é guardado dentro de `username`.

* **Linha 15: *(Linha em branco)***  
  Organização visual.

* **Linha 16: `        if (username.length() < 4 || username.length() > 12) {`** ⭐ **(O CORAÇÃO DO TESTE COM `||`)**  
  O Java calcula o tamanho do texto com `.length()` e faz o teste com o operador OU (`||`):  
  - Se tiver menos de 4 letras $\rightarrow$ **`true`** (Dá erro!).  
  - Se tiver mais de 12 letras $\rightarrow$ **`true`** (Dá erro!).  
  - Se estiver na faixa permitida (entre 4 e 12) $\rightarrow$ Ambos dão `false`, e `false || false` resulta em **`false`** (passa para o `else`)!

* **Linha 17: `            System.out.println("Username must be between 4-12 characters");`**  
  Exibe o aviso de que o nome não atende ao tamanho exigido.  
  *No console:* `Username must be between 4-12 characters`

* **Linha 18: `        }`**  
  Fecha a chave do `if`.

* **Linha 19: `        else {`** ⭐ **(NOVIDADE DA IMAGEM 4)**  
  O caminho feliz! Executado quando o nome tem um tamanho válido.

* **Linha 20: `            System.out.println("Welcome " + username);`** ⭐ **(NOVIDADE DA IMAGEM 4)**  
  Concatena (gruda) a palavra `"Welcome "` com o nome digitado e imprime na tela.  
  *No console:* `Welcome BroCode`

* **Linha 21: `        }`**  
  Fecha a chave do `else`.

* **Linha 22: *(Linha em branco)***  
  Organização visual.

* **Linha 23: `        scanner.close();`**  
  Fecha o leitor do teclado liberando memória do computador.

* **Linha 24: `    }`**  
  Fecha o método `main`.

* **Linha 25: `}`**  
  Fecha a classe `Main`.

---

## 🖥️ Os Dois Testes Mostrados no Vídeo

### 1. Teste de Erro — Nome Muito Curto (Imagem 3 - Minuto 3:18:57):
* **O que o autor digitou:** `Bro`
* **Cálculo do Java:** `"Bro".length()` é `3`.  
  - $3 < 4$? **SIM (`true`)**!  
  - Como é um `||` (OR), deu `true` no primeiro, já entra direto!
* **Saída completa no Console:**
  ```text
  Enter your new username: Bro
  Username must be between 4-12 characters

  Process finished with exit code 0
  ```

---

### 2. Teste de Sucesso — Nome Válido (Imagem 4):
* **O que o autor digitou:** `BroCode`
* **Cálculo do Java:** `"BroCode".length()` é `7`.  
  - $7 < 4$? **NÃO (`false`)**.  
  - $7 > 12$? **NÃO (`false`)**.  
  - `false || false` resulta em **`false`**!  
  - O Java pula o `if` e corre para o `else`!
* **Saída completa no Console:**
  ```text
  Enter your new username: BroCode
  Welcome BroCode

  Process finished with exit code 0
  ```

---

## 🧠 Resumo do Aprendizado Desta Etapa

1. **Strings têm superpoderes:** O método `.length()` conta as letras do texto instantaneamente.
2. **O `||` como Caçador de Erros:** Quando queremos validar se um dado violou o limite inferior **OU** o limite superior, o `||` é a ferramenta perfeita.
3. **Estrutura `if / else` de Validação:**  
   - O `if` captura quem errou e mostra a bronca.  
   - O `else` recebe quem acertou com boas-vindas.
