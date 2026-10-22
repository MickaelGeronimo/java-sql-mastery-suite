# ♾️ Curso Completo de Java — Capítulo 21: While Loops (Parte 3)
## Validação de Idade e a Introdução ao Laço `do-while` ("Atira Primeiro, Pergunta Depois")

---

### 📌 Contexto Visual e do Episódio (Para Leitura no Google Docs)

> **Onde estamos no curso:** Capítulo 21 (*21. while loops ♾️*), entre os minutos `3:28:42` e `3:30:52` do curso de 12 horas do Bro Code.  
> **A virada conceitual ensinada pelo autor:**  
> Depois de ensinar o `while` tradicional, o professor Bro Code apresenta um "primo" muito especial dele: o laço **`do-while`**. Ele usa um problema clássico de validação de dados (impedir que uma pessoa digite uma idade negativa) para demonstrar a diferença prática entre os dois.  
>  
> **A sequência passo a passo mostrada nas 4 imagens:**  
>  
> * **Imagem 1 (Minuto 3:28:42 — O Código Ingênuo):**  
>   O autor escreve um programa simples pedindo a idade do usuário:  
>   `int age = 0;`  
>   `System.out.print("Enter your age: ");`  
>   `age = scanner.nextInt();`  
>   `System.out.println("You are " + age + " years old");`  
>  
> * **Imagem 2 (Minuto 3:28:51 — O Bug da Idade Negativa):**  
>   O autor roda o programa e digita: `-1`.  
>   O console responde com a maior cara de pau:  
>   `You are -1 years old` *(Você tem -1 anos de idade)*!  
>   O autor dá risada e diz: *"Isso não faz o menor sentido no mundo real! Ninguém tem idade negativa! Precisamos prender o usuário até ele digitar uma idade válida!"*.  
>  
> * **Imagem 3 (Minuto 3:29:40 aprox. — A Solução Feia com `while` Duplicado):**  
>   Para consertar isso com o `while` tradicional, o autor teve que escrever a pergunta **duas vezes**:  
>   1. Perguntou uma vez antes do loop para pegar a primeira resposta.  
>   2. Escreveu `while(age < 0)` e, dentro dele, perguntou **de novo**!  
>   Ele testou no console digitando `-2`, `-3`, `-4`, e o programa ficava repetindo: `Your age can't be negative` / `Enter your age: `.  
>   O autor comenta: *"Funciona, mas ter que escrever `System.out.print` e `nextInt` duas vezes deixa o código feio e repetitivo!"*.  
>  
> * **Imagem 4 (Minuto 3:30:52 — A Solução Elegante com `do-while`):**  
>   O autor substitui pelo laço **`do-while`**:  
>   `do {`  
>   `    System.out.println("Your age can't be negative");`  
>   `    System.out.print("Enter your age: ");`  
>   `    age = scanner.nextInt();`  
>   `} while(age < 0);`  
>   Ele roda o programa e testa: digita `-100`, o programa repete a bronca; depois digita `25`, e o programa finaliza exibindo com perfeição: `You are 25 years old`!

---

## 🥊 A Grande Batalha: `while` vs `do-while`

Esta é uma das perguntas mais cobradas em entrevistas de emprego e provas de Java! Preste muita atenção na diferença:

```text
┌───────────────────────────────────────┬───────────────────────────────────────┐
│              WHILE COMUM              │               DO-WHILE                │
│       ("Pensa Primeiro, Age Depois")   │       ("Atira Primeiro, Pergunta Depois") │
├───────────────────────────────────────┼───────────────────────────────────────┤
│ A checagem da condição fica no TOPO.  │ A checagem da condição fica no FINAL. │
│                                       │                                       │
│ Se a condição for FALSA logo de cara, │ Executa o bloco de código             │
│ o loop NÃO roda NENHUMA VEZ!          │ OBRIGATORIAMENTE PELO MENOS UMA VEZ!  │
└───────────────────────────────────────┴───────────────────────────────────────┘
```

### 1. A Analogia da Balada e do Parque de Diversões:
* **O `while` é a Balada Chique:** Tem segurança na porta (no topo). Se você não estiver com a roupa certa, **nem entra**! Risco de rodar zero vezes.
* **O `do-while` é a Montanha-Russa:** Você entra no carrinho, dá a primeira volta completa no brinquedo (`do`). Só quando o carrinho para no final da volta é que o operador pergunta: *"Quer dar outra volta?"* (`while`). Ou seja: você **sempre brinca pelo menos uma vez**!

---

## ⚠️ Detalhe de Sintaxe que Pega Todo Mundo!

Repare no finalzinho do `do-while`:
```java
do {
    // instruções...
} while (condicao); // 👈 OLHE ESSE PONTO E VÍRGULA!
```
- No `while` normal, você **nunca** põe ponto e vírgula no final do `while(...) { }`.
- Mas no `do-while`, o ponto e vírgula `;` após o parêntese do `while(...)` é **100% OBRIGATÓRIO**! Se você esquecer, o Java não compila!

---

## 📜 Código-Fonte Completo (`Main.java` com `do-while`)

Abaixo está o código exatamente como visto na tela do professor na Imagem 4:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int age = 0;

        do {
            System.out.println("Your age can't be negative");
            System.out.print("Enter your age: ");
            age = scanner.nextInt();
        } while (age < 0);

        System.out.println("You are " + age + " years old");

        scanner.close();
    }
}
```

---

## 🔍 Explicação Detalhada Linha por Linha (Enumeradas de 1 a 20)

* **Linha 1: `import java.util.Scanner;`**  
  Traz a ferramenta do `Scanner` para dentro do código para ler números do teclado.  
  *No console:* Não exibe nada.

* **Linha 2: *(Linha em branco)***  
  Organização visual.

* **Linha 3: `public class Main {`**  
  Declara a classe principal `Main`.

* **Linha 4: `    public static void main(String[] args) {`**  
  O ponto de partida do Java (onde a execução começa).

* **Linha 5: *(Linha em branco)***  
  Organização visual.

* **Linha 6: `        Scanner scanner = new Scanner(System.in);`**  
  Cria o objeto leitor `scanner` apontado para o teclado (`System.in`).  
  *No console:* Não exibe nada.

* **Linha 7: *(Linha em branco)***  
  Organização visual.

* **Linha 8: `        int age = 0;`**  
  Cria a caixinha de número inteiro `age` (idade) guardando o valor inicial `0`.  
  *No console:* Não exibe nada.

* **Linha 9: *(Linha em branco)***  
  Organização visual.

* **Linha 10: `        do {`** ⭐ **(O INÍCIO DO DO-WHILE)**  
  A palavra **`do`** significa **"FAÇA"** em inglês.  
  O Java não faz nenhuma pergunta aqui! Ele simplesmente abre a porta e entra de olhos fechados para executar tudo o que estiver dentro das chaves pelo menos uma vez!

* **Linha 11: `            System.out.println("Your age can't be negative");`**  
  Imprime o aviso de que a idade não pode ser negativa e pula uma linha.  
  *No console:* `Your age can't be negative`

* **Linha 12: `            System.out.print("Enter your age: ");`**  
  Pede para o usuário digitar a idade, mantendo o cursor na mesma linha.  
  *No console:* `Enter your age: `

* **Linha 13: `            age = scanner.nextInt();`**  
  Pausa o programa e espera o usuário digitar um número inteiro (ex: `-100` ou `25`) e apertar Enter. Salva o número na variável `age`.

* **Linha 14: `        } while (age < 0);`** ⭐ **(A HORA DO TESTE NO RODAPÉ)**  
  Agora sim o Java faz o teste: *"A idade digitada é menor que zero (`age < 0`)?"*.  
  - Se você digitou `-100`: $-100 < 0$ é **`true`**! O Java dá meia-volta e sobe para o `do` na linha 10 para repetir tudo de novo!  
  - Se você digitou `25`: $25 < 0$ é **`false`**! O loop quebra, a repetição acaba e o Java desce para a linha 16!

* **Linha 15: *(Linha em branco)***  
  Organização visual.

* **Linha 16: `        System.out.println("You are " + age + " years old");`**  
  Imprime a frase final de sucesso com a idade validada.  
  *No console:* `You are 25 years old`

* **Linha 17: *(Linha em branco)***  
  Organização visual.

* **Linha 18: `        scanner.close();`**  
  Fecha o scanner liberando a memória do computador.

* **Linha 19: `    }`**  
  Fecha o método `main`.

* **Linha 20: `}`**  
  Fecha a classe `Main`.

---

## 🖥️ A Demonstração dos Testes no Console

### 1. O Teste do Bug (Imagem 2 - Sem Validação):
```text
Enter your age: -1
You are -1 years old

Process finished with exit code 0
```
*(Bug ridículo: o programa aceitou uma pessoa com -1 ano de vida!).*

---

### 2. O Teste Teimoso (Imagem 3 - Com `while` comum):
O usuário tentou enganar o programa 3 vezes com idades negativas:
```text
Enter your age: -2
Your age can't be negative
Enter your age: -3
Your age can't be negative
Enter your age: -4
Your age can't be negative
Enter your age: 
```
*(O programa não deixou passar!).*

---

### 3. O Teste Final (Imagem 4 - Com `do-while`):
```text
Your age can't be negative
Enter your age: -100
Your age can't be negative
Enter your age: 25
You are 25 years old

Process finished with exit code 0
```
> **Repare no que aconteceu:**  
> 1. Primeira tentativa: Digitou `-100`. A condição `-100 < 0` é verdadeira, então o loop repetiu!  
> 2. Segunda tentativa: Digitou `25`. A condição `25 < 0` é falsa, então o loop encerrou e imprimiu `You are 25 years old`!

---

## 🧠 Resumo do Aprendizado: Quando Usar Cada Um?

* Use **`while`**: Quando você só quer executar o código **SE** uma condição já for verdadeira desde o início (ex: ler arquivos enquanto houver linhas).
* Use **`do-while`**: Quando você **precisa obrigatoriamente** rodar o código pelo menos uma vez antes de checar (ex: menus interativos, formulários onde o usuário tem que digitar algo antes de você poder validar se está certo ou errado!).
