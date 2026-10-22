# 🖩 Curso Completo de Java — Capítulo 19: Calculadora em Java (Parte 3 - Final)
## A Solução Elegante da "Flag" (`boolean validOperation`) e Todos os Testes

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 19 (*19. calculator program 🖩*), entre os minutos `3:06:38` e `3:08:58` de 12 horas de curso.  
> **O que aconteceu antes:** O professor Bro Code criou a calculadora completa com `switch` melhorado (`+`, `-`, `*`, `/`, `^`) e tratou a divisão por zero com um `if (num2 == 0)`.  
> **O Grande Problema / Bug Encontrado:**  
> Quando o usuário tentava dividir por zero (ex: `5 / 0`), o programa imprimia a mensagem de erro:  
> `Cannot divide by zero!`  
> Mas, logo na linha seguinte, imprimia:  
> `0.0`!  
> E se a pessoa digitasse um operador inválido (ex: `pizza` ou `@`), imprimia:  
> `Invalid operator!`  
> E na linha seguinte, de novo: `0.0`!  
>  
> **Por que esse `0.0` intrometido aparecia?**  
> Porque lá no topo do código, declaramos `double result = 0;`. No final do programa, tínhamos a linha `System.out.println(result);`, que era executada **sempre**, não importava se tinha dado erro ou não. Para o Java, o valor de `result` continuava sendo `0`, então ele cuspia `0.0` na tela. Isso passava uma falsa impressão de que `5 / 0 = 0`, o que é matematicamente errado!  
>  
> **A Solução Mostrada nestas Imagens:**  
> O professor resolve esse bug criando uma **variável bandeira (flag)** chamada `boolean validOperation = true;`.  
> Se tudo correr bem, ela continua `true`. Se der algum erro (divisão por zero ou operador inválido), ela é abaixada para `false`.  
> No final, o Java só imprime o resultado se a bandeira ainda estiver de pé (`if (validOperation)`).

---

## 💡 A Analogia do Guarda da Festa (Entendendo a "Flag")

Imagine que você está organizando uma festa e contrata um segurança na porta do palco:
1. **O crachá começa verde (`boolean validOperation = true;`):** O convidado entra com a presunção de que vai receber o brinde no final.
2. **Se o convidado fizer besteira (Divisão por zero ou Operador Inválido):** O segurança troca o crachá dele para vermelho (`validOperation = false;`) e avisa o que ele fez de errado.
3. **Na hora de entregar o brinde (`if (validOperation)`):** O entregador olha o crachá. Se estiver verde (`true`), entrega o resultado. Se estiver vermelho (`false`), ele simplesmente **não entrega nada** e fecha a festa!

É exatamente isso que o código faz. Simples, genial e muito profissional.

---

## 📜 Código-Fonte Completo e Atualizado (`Main.java`)

Abaixo está o código final do arquivo `Main.java` com todas as 49 linhas:

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

## 🔍 Explicação Detalhada — Linha por Linha (Enumerada de 1 a 49)

Vamos examinar com calma cada linha do programa:

* **Linha 1: `import java.util.Scanner;`**  
  Importa do armário de ferramentas do Java a classe `Scanner`, necessária para ler o que o usuário digita no teclado.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Organização visual do código para facilitar a leitura.

* **Linha 3: `public class Main {`**  
  Abre a classe principal chamada `Main`. Todo código Java executável precisa estar dentro de uma classe.

* **Linha 4: `    public static void main(String[] args) {`**  
  O método principal (*ponto de entrada*). É aqui que o Java começa a rodar quando você clica no botão "Play".

* **Linha 5: *(Linha em branco)***  
  Espaço para separar a abertura do método das instruções.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto `scanner` apontado para o teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Separação visual antes da declaração de variáveis.

* **Linha 8: `        double num1;`**  
  Cria a caixa de memória `num1` para guardar números com casas decimais (primeiro operando).

* **Linha 9: `        double num2;`**  
  Cria a caixa `num2` para guardar o segundo número decimal.

* **Linha 10: `        char operator;`**  
  Cria a caixa `operator` do tipo caractere único (`char`), que guardará o símbolo da operação (`+`, `-`, `*`, `/` ou `^`).

* **Linha 11: `        double result = 0;`**  
  Cria a caixa `result` com o valor inicial zero (`0`). Inicializar com `0` evita o erro clássico de "variável pode não ter sido inicializada".

* **Linha 12: `        boolean validOperation = true;`** ⭐ **(NOVIDADE DO VÍDEO)**  
  Cria a variável booleana de controle (nossa "bandeira"). Ela começa como `true` (verdadeira). Assumimos que o usuário vai digitar tudo certinho até que se prove o contrário.

* **Linha 13: *(Linha em branco)***  
  Organização visual.

* **Linha 14: `        System.out.print("Enter the first number: ");`**  
  Exibe na tela a mensagem pedindo o primeiro número. Como usa `.print()`, o cursor fica piscando na mesma linha.  
  *No console:* `Enter the first number: `

* **Linha 15: `        num1 = scanner.nextDouble();`**  
  O programa pausa e espera o usuário digitar o primeiro número (ex: `3.14` ou `10`) e apertar Enter. O valor é guardado em `num1`.

* **Linha 16: *(Linha em branco)***  
  Organização visual.

* **Linha 17: `        System.out.print("Enter an operator (+, -, *, /, ^): ");`**  
  Pede para o usuário escolher uma das 5 operações disponíveis.  
  *No console:* `Enter an operator (+, -, *, /, ^): `

* **Linha 18: `        operator = scanner.next().charAt(0);`**  
  Lê a palavra digitada com `.next()` e pesca apenas o primeiríssimo caractere usando `.charAt(0)`. Isso protege caso o usuário digite `+algo`, pegando apenas o `+`.

* **Linha 19: *(Linha em branco)***  
  Organização visual.

* **Linha 20: `        System.out.print("Enter the second number: ");`**  
  Pede o segundo número ao usuário.  
  *No console:* `Enter the second number: `

* **Linha 21: `        num2 = scanner.nextDouble();`**  
  Pausa e aguarda o usuário digitar o segundo número (ex: `0` ou `2`) e apertar Enter. Salva em `num2`.

* **Linha 22: *(Linha em branco)***  
  Organização visual.

* **Linha 23: `        switch (operator) {`**  
  Inicia a estrutura de decisão do `switch`. O Java vai olhar o que está guardado dentro de `operator` e pular diretamente para a gaveta correspondente.

* **Linha 24: `            case '+' -> result = num1 + num2;`**  
  Se for `'+'`, soma `num1 + num2` e guarda em `result`. Como usa `->` (Enhanced Switch), ele executa e sai do switch sem precisar de `break`.

* **Linha 25: `            case '-' -> result = num1 - num2;`**  
  Se for `'-'`, subtrai `num1 - num2` e guarda em `result`.

* **Linha 26: `            case '*' -> result = num1 * num2;`**  
  Se for `'*'`, multiplica `num1 * num2` e guarda em `result`.

* **Linha 27: `            case '/' -> {`**  
  Se for `'/'`, abre um bloco de chaves `{ }` porque teremos mais de uma instrução (verificação se o divisor é zero).

* **Linha 28: `                if (num2 == 0) {`**  
  Testa se o divisor `num2` é igual a zero.

* **Linha 29: `                    System.out.println("Cannot divide by zero!");`**  
  Se for zero, exibe o aviso de erro matemático.  
  *No console:* `Cannot divide by zero!`

* **Linha 30: `                    validOperation = false;`** ⭐ **(NOVIDADE DO VÍDEO)**  
  Abaixa a bandeira! Informa ao Java: *"Essa conta deu ruim, não é uma operação válida!"*.

* **Linha 31: `                }`**  
  Fecha o bloco do `if`.

* **Linha 32: `                else {`**  
  Se `num2` NÃO for zero (divisão segura).

* **Linha 33: `                    result = num1 / num2;`**  
  Realiza a divisão normal de `num1` por `num2` e armazena na variável `result`.

* **Linha 34: `                }`**  
  Fecha o bloco do `else`.

* **Linha 35: `            }`**  
  Fecha o bloco de chaves do `case '/'`.

* **Linha 36: `            case '^' -> result = Math.pow(num1, num2);`**  
  Se for `'^'`, calcula a potência $num1^{num2}$ usando o método `Math.pow()` da biblioteca matemática padrão do Java e guarda em `result`.

* **Linha 37: `            default -> {`** ⭐ **(NOVIDADE DO VÍDEO)**  
  Se o usuário digitou qualquer símbolo que não seja nenhum dos 5 cases acima. Como agora temos **duas linhas de código** aqui dentro, as chaves `{ }` se tornaram **obrigatórias**!

* **Linha 38: `                System.out.println("Invalid operator!");`**  
  Avisa que o operador não existe na calculadora.  
  *No console:* `Invalid operator!`

* **Linha 39: `                validOperation = false;`** ⭐ **(NOVIDADE DO VÍDEO)**  
  Abaixa a bandeira aqui também! Operador desconhecido significa operação inválida.

* **Linha 40: `            }`**  
  Fecha o bloco de chaves do `default`.

* **Linha 41: `        }`**  
  Fecha a chave do `switch(operator)`.

* **Linha 42: *(Linha em branco)***  
  Organização visual.

* **Linha 43: `        if (validOperation) {`** ⭐ **(A GRANDE SOLUÇÃO DO BUG)**  
  O Java confere a bandeira:  
  - Se `validOperation` ainda for `true`, entra no bloco e executa a linha 44.  
  - Se for `false` (porque caiu no erro de divisão por zero ou no default), pula direto para a linha 46, **sem imprimir nada de número**!

* **Linha 44: `            System.out.println(result);`**  
  Imprime o valor final da conta na tela.  
  *No console:* Imprime o valor numérico (ex: `8.0`, `15.5`, etc.).

* **Linha 45: `        }`**  
  Fecha o bloco do `if (validOperation)`.

* **Linha 46: *(Linha em branco)***  
  Organização visual.

* **Linha 47: `        scanner.close();`**  
  Fecha o scanner liberando a memória do sistema operacional. Boa prática fundamental!

* **Linha 48: `    }`**  
  Fecha o método `main`.

* **Linha 49: `}`**  
  Fecha a classe `Main`.

---

## 🧪 Demonstração de Testes no Console (Todas as Operações)

Aqui está exatamente o que aparece na janela de execução (Console) em cada situação testada pelo autor e em todos os cenários possíveis:

### Teste 1: O Teste Mostrado no Vídeo — Divisão por Zero corrigida (Minuto 3:08:58)
O usuário digita um número com vírgula (`3.14`), escolhe a barra de divisão (`/`) e coloca zero (`0`):
```text
Enter the first number: 3.14
Enter an operator (+, -, *, /, ^): /
Enter the second number: 0
Cannot divide by zero!

Process finished with exit code 0
```
> **Repare com atenção:** A mensagem `0.0` **desapareceu completamente**! O programa mostrou apenas o aviso e finalizou com perfeição.

---

### Teste 2: Operador Inválido
O usuário digita um operador que a calculadora não suporta (ex: `?`):
```text
Enter the first number: 10
Enter an operator (+, -, *, /, ^): ?
Enter the second number: 5
Invalid operator!

Process finished with exit code 0
```
> **Resultado:** Também não imprime `0.0`, somente `Invalid operator!`.

---

### Teste 3: Adição (`+`)
```text
Enter the first number: 12.5
Enter an operator (+, -, *, /, ^): +
Enter the second number: 7.5
20.0

Process finished with exit code 0
```

---

### Teste 4: Subtração (`-`)
```text
Enter the first number: 50
Enter an operator (+, -, *, /, ^): -
Enter the second number: 15.3
34.7

Process finished with exit code 0
```

---

### Teste 5: Multiplicação (`*`)
```text
Enter the first number: 6
Enter an operator (+, -, *, /, ^): *
Enter the second number: 7
42.0

Process finished with exit code 0
```

---

### Teste 6: Divisão Normal e Válida (`/`)
```text
Enter the first number: 15
Enter an operator (+, -, *, /, ^): /
Enter the second number: 2
7.5

Process finished with exit code 0
```

---

### Teste 7: Potenciação (`^`) com `Math.pow`
O usuário calcula $2^8$ (dois elevado a oito):
```text
Enter the first number: 2
Enter an operator (+, -, *, /, ^): ^
Enter the second number: 8
256.0

Process finished with exit code 0
```

---

## 🧠 Resumo do Aprendizado do Capítulo 19

1. **Variável Flag / Bandeira (`boolean`):** É um padrão de projeto muito comum na programação real. Você inicializa como `true` e vira para `false` quando detectar qualquer inconsistência.
2. **Chaves `{ }` no Enhanced Switch:** Se um `case` ou `default` tiver apenas 1 instrução, não precisa de chaves. Mas se tiver **duas ou mais linhas**, o bloco de chaves `{ }` é estritamente obrigatório.
3. **Controle de Fluxo Limpo:** Nunca exiba saídas para o usuário sem antes checar se os dados calculados são confiáveis. O `if(validOperation)` garantiu que o usuário nunca receba um lixo ou valor padrão indevido.
