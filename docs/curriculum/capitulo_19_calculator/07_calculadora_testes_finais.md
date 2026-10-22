# 🖩 Curso Completo de Java — Capítulo 19: Calculadora em Java (Parte 4 - Finalização e Testes de Borda)
## O Teste da Potência ($4^2 = 16.0$), o Teste da "Pizza" e a Revisão Geral

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 19 (*19. calculator program 🖩*), entre os minutos `3:09:00` e `3:09:45` de 12 horas de curso.  
> **O que o autor fez nestas imagens:**  
> Depois de implementar a bandeira (`boolean validOperation`) que consertou o bug do `0.0`, o professor Bro Code agora faz a bateria de testes finais para garantir que a calculadora aguenta qualquer tranco antes de fechar o capítulo:  
> 1. **Imagem 1 (Teste da Potência):** Ele testa o operador de exponenciação `^` digitando primeiro o número `4`, depois o símbolo `^` e depois o número `2`. O console calcula $4^2$ e responde `16.0`.  
> 2. **Imagem 2 (O Teste Maluco da "Pizza"):** Para provar que a proteção contra operadores inválidos funciona mesmo se o usuário for zoeiro ou errar feio, ele digita `3.14`, depois escreve a palavra `"pizza"` como operador, e depois digita `69` como segundo número. O programa imprime com orgulho: `Invalid operator!` — e **nenhum** número indevido ou `0.0` é impresso!  
> 3. **Imagem 3 (Revisão no Código - Minuto 3:09:45):** O professor seleciona a linha do `case '*'` com o mouse e faz um resumo geral de como o `switch(operator)` com *Enhanced Switches* (`->`) orquestra perfeitamente as 5 operações matemáticas (`+`, `-`, `*`, `/`, `^`) e o caso de erro (`default`).

---

## 🍕 Por que a palavra "pizza" virou o operador `'p'`?

Você deve ter reparado que no teste da imagem 2, o autor não digitou um único símbolo; ele digitou a palavra inteira: `pizza`!  
Como o Java não deu erro de tipo?

Lembre-se da linha 18 do código:
```java
operator = scanner.next().charAt(0);
```
1. `scanner.next()` leu a palavra inteira digitada pelo usuário como uma String: `"pizza"`.
2. O método `.charAt(0)` foi lá e capturou **somente o caractere na posição 0** (a primeira letra da palavra).
3. A primeira letra de `"pizza"` é o caractere `'p'`!
4. Portanto, a variável `char operator` recebeu a letra `'p'`.
5. Quando o Java entrou no `switch (operator)`, ele procurou:
   - É `'+'`? Não.
   - É `'-'`? Não.
   - É `'*'`? Não.
   - É `'/'`? Não.
   - É `'^'`? Não.
   - Então cai direto no **`default ->`**!
6. O `default` imprimiu `Invalid operator!`, transformou `validOperation` em `false` e encerrou sem imprimir nenhum resultado numérico!

---

## 📜 O Código-Fonte Completo e Definitivo (`Main.java`)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double num1;
        double num2;
        char operator;
        double result = 0;
        boolean validOperation = true;

        System.out.print("Enter the first number: ");
        num1 = scanner.nextDouble();

        System.out.print("Enter an operator (+, -, *, /, ^): ");
        operator = scanner.next().charAt(0);

        System.out.print("Enter the second number: ");
        num2 = scanner.nextDouble();

        switch (operator) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    System.out.println("Cannot divide by zero!");
                    validOperation = false;
                } else {
                    result = num1 / num2;
                }
            }
            case '^' -> result = Math.pow(num1, num2);
            default -> {
                System.out.println("Invalid operator!");
                validOperation = false;
            }
        }

        if (validOperation) {
            System.out.println(result);
        }

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 49)

* **Linha 1: `import java.util.Scanner;`**  
  Importa o `Scanner` da biblioteca de utilitários do Java para conseguirmos ler dados digitados no terminal.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Espaço para organização e legibilidade do código.

* **Linha 3: `public class Main {`**  
  Define a classe `Main`. Em Java, todo o código do programa deve estar embrulhado dentro de uma classe com o mesmo nome do arquivo (`Main.java`).

* **Linha 4: `    public static void main(String[] args) {`**  
  O método principal `main`. É o motor de ignição do programa; quando você clica em "Run", o Java começa a ler daqui.

* **Linha 5: *(Linha em branco)***  
  Espaçamento para separar o cabeçalho do código das instruções.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` configurado para escutar a entrada padrão do teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Espaço para separar a criação do scanner da declaração de variáveis.

* **Linha 8: `        double num1;`**  
  Declara a variável `num1` do tipo `double` (armazena números de dupla precisão com vírgula/ponto flutuante).

* **Linha 9: `        double num2;`**  
  Declara a variável `num2` do tipo `double` para o segundo número.

* **Linha 10: `        char operator;`**  
  Declara a variável `operator` do tipo `char` (guarda um único caractere entre aspas simples, como `'+'`, `'^'`, etc.).

* **Linha 11: `        double result = 0;`**  
  Declara a variável `result` do tipo `double` e já atribui o valor inicial `0`. Isso garante que a variável tenha um valor padrão definido.

* **Linha 12: `        boolean validOperation = true;`**  
  Declara a nossa "bandeira" (*flag*) do tipo `boolean` (verdadeiro ou falso). Começa como `true` porque presumimos que a conta será válida.

* **Linha 13: *(Linha em branco)***  
  Espaçamento para separar as variáveis da interação com o usuário.

* **Linha 14: `        System.out.print("Enter the first number: ");`**  
  Mostra na tela a mensagem pedindo o primeiro número. Usa `.print()` (sem o "ln") para que o cursor do teclado continue piscando logo após os dois pontos.  
  *No console:* `Enter the first number: `

* **Linha 15: `        num1 = scanner.nextDouble();`**  
  Congela a execução e fica esperando o usuário digitar o primeiro número (ex: `4` ou `3.14`) e pressionar Enter. Guarda o valor digitado dentro de `num1`.

* **Linha 16: *(Linha em branco)***  
  Espaço visual.

* **Linha 17: `        System.out.print("Enter an operator (+, -, *, /, ^): ");`**  
  Mostra as instruções com os operadores permitidos.  
  *No console:* `Enter an operator (+, -, *, /, ^): `

* **Linha 18: `        operator = scanner.next().charAt(0);`**  
  Lê o texto digitado pelo usuário (`.next()`) e pega apenas o primeiríssimo caractere que estiver na posição zero (`.charAt(0)`). Se o usuário digitar `pizza`, ele pega a letra `'p'`. Se digitar `^`, pega o `'^'`. Salva dentro de `operator`.

* **Linha 19: *(Linha em branco)***  
  Espaço visual.

* **Linha 20: `        System.out.print("Enter the second number: ");`**  
  Imprime a solicitação do segundo número na tela.  
  *No console:* `Enter the second number: `

* **Linha 21: `        num2 = scanner.nextDouble();`**  
  Congela a execução e aguarda o segundo número (ex: `2` ou `69`) e o Enter. Salva o valor em `num2`.

* **Linha 22: *(Linha em branco)***  
  Espaço visual antes da estrutura de decisão.

* **Linha 23: `        switch (operator) {`**  
  Inicia a seleção do `switch`. O Java inspeciona o caractere que está na caixinha `operator` para decidir qual ramo executar.

* **Linha 24: `            case '+' -> result = num1 + num2;`**  
  Se `operator` for igual a `'+'`, soma os dois números e armazena em `result`. Graças à seta `->`, ele sai do switch automaticamente sem precisar da palavra `break`.

* **Linha 25: `            case '-' -> result = num1 - num2;`**  
  Se `operator` for `'-'`, faz a subtração `num1 - num2` e salva em `result`.

* **Linha 26: `            case '*' -> result = num1 * num2;`** *(Linha destacada pelo autor no vídeo)*  
  Se `operator` for `'*'`, realiza a multiplicação `num1 * num2` e salva em `result`.

* **Linha 27: `            case '/' -> {`**  
  Se `operator` for `'/'`, abre um bloco de chaves `{ }` porque precisaremos executar um teste de segurança antes de dividir.

* **Linha 28: `                if (num2 == 0) {`**  
  Verifica se o segundo número (`num2`) é igual a zero.

* **Linha 29: `                    System.out.println("Cannot divide by zero!");`**  
  Se for zero, avisa no console que divisão por zero não é permitida.  
  *No console:* `Cannot divide by zero!`

* **Linha 30: `                    validOperation = false;`**  
  Abaixa a bandeira de controle! Marca que a operação falhou e não é válida.

* **Linha 31: `                }`**  
  Fecha o bloco do `if`.

* **Linha 32: `                else {`**  
  Caso `num2` seja diferente de zero (divisão segura e permitida).

* **Linha 33: `                    result = num1 / num2;`**  
  Realiza a divisão e guarda o resultado em `result`.

* **Linha 34: `                }`**  
  Fecha o bloco do `else`.

* **Linha 35: `            }`**  
  Fecha as chaves do bloco do `case '/'`.

* **Linha 36: `            case '^' -> result = Math.pow(num1, num2);`** *(Testado na Imagem 1)*  
  Se `operator` for `'^'`, chama o método matemático `Math.pow(base, expoente)` para calcular $num1^{num2}$ e salva em `result`.

* **Linha 37: `            default -> {`** *(Acionado no Teste da Imagem 2)*  
  Se o caractere em `operator` não for nenhum dos 5 anteriores (como a letra `'p'` da palavra `pizza`). Como há duas linhas de código aqui, as chaves `{ }` são obrigatórias.

* **Linha 38: `                System.out.println("Invalid operator!");`**  
  Informa na tela que aquele operador não existe na calculadora.  
  *No console:* `Invalid operator!`

* **Linha 39: `                validOperation = false;`**  
  Abaixa a bandeira de controle para `false` para impedir a exibição de resultado numérico.

* **Linha 40: `            }`**  
  Fecha o bloco do `default`.

* **Linha 41: `        }`**  
  Fecha a chave de todo o bloco `switch`.

* **Linha 42: *(Linha em branco)***  
  Espaço visual.

* **Linha 43: `        if (validOperation) {`**  
  Guarda de segurança da impressão. O Java verifica: `validOperation` ainda é `true`?  
  - No teste da potência ($4^2$), é `true` $\rightarrow$ entra no `if`.  
  - No teste da "pizza", é `false` $\rightarrow$ pula o `if` sem fazer nada!

* **Linha 44: `            System.out.println(result);`**  
  Imprime o valor numérico final na tela.  
  *No console:* Imprime o número (ex: `16.0`).

* **Linha 45: `        }`**  
  Fecha o bloco do `if (validOperation)`.

* **Linha 46: *(Linha em branco)***  
  Espaço visual.

* **Linha 47: `        scanner.close();`**  
  Fecha o recurso do scanner, liberando a memória do computador.

* **Linha 48: `    }`**  
  Fecha o método `main`.

* **Linha 49: `}`**  
  Fecha a classe `Main`.

---

## 🧪 Comparação dos Testes Exibidos nas Telas

### Cenário 1: Teste da Potência (Imagem 1 - Sucesso com `Math.pow`)
* **Entradas do usuário:**
  - Primeiro número: `4`
  - Operador: `^`
  - Segundo número: `2`
* **Saída completa no Console:**
  ```text
  Enter the first number: 4
  Enter an operator (+, -, *, /, ^): ^
  Enter the second number: 2
  16.0

  Process finished with exit code 0
  ```
* **O que aconteceu por trás dos panos:**  
  O Java executou `Math.pow(4.0, 2.0)`, que dá `16.0`. Como não houve erro, `validOperation` permaneceu `true`, e a linha 44 imprimiu com perfeição `16.0`.

---

### Cenário 2: Teste da "Pizza" (Imagem 2 - Tratamento de Erro com `default`)
* **Entradas do usuário:**
  - Primeiro número: `3.14`
  - Operador: `pizza`
  - Segundo número: `69`
* **Saída completa no Console:**
  ```text
  Enter the first number: 3.14
  Enter an operator (+, -, *, /, ^): pizza
  Enter the second number: 69
  Invalid operator!

  Process finished with exit code 0
  ```
* **O que aconteceu por trás dos panos:**  
  `scanner.next().charAt(0)` pegou apenas a letra `'p'`. No `switch`, nenhum `case` correspondia a `'p'`, então caiu no `default`. O `default` imprimiu `Invalid operator!` e setou `validOperation = false`. O `if (validOperation)` viu que a bandeira era falsa e não imprimiu o `result`. O terminal terminou limpo, sem imprimir `0.0`!

---

## 🏆 Fim do Capítulo 19: O que você dominou aqui?
1. **Entrada flexível de caracteres:** Usar `.next().charAt(0)` para pegar o primeiro caractere com segurança.
2. **Enhanced Switch (`->`):** Código muito mais limpo, sem repetições infinitas de `break`.
3. **Uso de chaves `{ }` quando necessário:** Saber que setas com 1 instrução não usam chaves, mas blocos com 2 ou mais instruções exigem `{ }`.
4. **Tratamento de Exceções Matemáticas:** Como evitar a divisão por zero com `if / else`.
5. **A técnica da Bandeira (*Flag Pattern*):** Como usar uma variável booleana para coordenar se uma mensagem de resultado deve ou não ser exibida.
