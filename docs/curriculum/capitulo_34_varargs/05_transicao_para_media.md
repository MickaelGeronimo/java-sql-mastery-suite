# ☕ Curso de Java — Capítulo 34: `varargs` (Parte 5)
## A Transição Elegante: Dobrando Código (*Code Folding*), Seleção de Argumentos e a Preparação para o Método `average`

> **Baseado na aula do canal Bro Code (Minuto `5:31:32` de 12:00:00)**  
> **Tema:** O professor finaliza a explicação do método de soma (`add`), encolhe o corpo do método com o recurso de dobrar código (`{...}`) para limpar a tela da IDE, e seleciona os argumentos no `main` para preparar a transição para o próximo grande desafio com `varargs`: o cálculo da **Média Aritmética** (`average`).

---

## 📌 Sumário
1. [Contexto Visual da Tela (Para o Google Docs)](#1-contexto-visual-da-tela-para-o-google-docs)
2. [O que é o Recurso de Dobradura de Código (*Code Folding*) no IntelliJ IDEA?](#2-o-que-é-o-recurso-de-dobradura-de-código-code-folding-no-intellij-idea)
3. [Por que o Professor Selecionou os Números `2, 3, 4, 5, 6`?](#3-por-que-o-professor-selecionou-os-números-2-3-4-5-6)
4. [O Código Java Completo, Numerado e Expandido](#4-o-código-java-completo-numerado-e-expandido)
5. [Explicação Cirúrgica Linha por Linha](#5-explicação-cirúrgica-linha-por-linha)
6. [O que Sai no Console Nesta Execução?](#6-o-que-sai-no-console-nesta-execução)
7. [A Ponte para o Próximo Exemplo: O Desafio da Média (`average`)](#7-a-ponte-para-o-próximo-exemplo-o-desafio-da-média-average)
8. [Resumo Prático em Uma Tabela Mental](#8-resumo-prático-em-uma-tabela-mental)

---

## 1. Contexto Visual da Tela (Para o Google Docs)

Para você que vai copiar este material diretamente para o seu **Google Docs**, aqui está o relatório visual minucioso de tudo o que está visível no arquivo `Main.java` no minuto `5:31:32`:

* **No Painel Superior do Editor:**
  * As linhas 5 a 8 continuam exibindo os comentários teóricos iniciais sobre `varargs`.
  * Na linha 10, está a chamada:  
    `System.out.println(add(1, 2, 3, 4, 5, 6));`
  * **O detalhe visual do cursor:**  
    O professor usou o mouse para **selecionar e pintar de azul** o trecho:  
    `2, 3, 4, 5, 6)`  
    *(O cursor vertical de texto está posicionado logo após a vírgula do número `1,`)*.
* **Abaixo do `main` (Linha 13):**
  * O método `add` não aparece mais com todas as suas linhas abertas!
  * Ele agora aparece compacto em uma única linha:  
    `static int add(int... numbers){...}`
  * Há uma setinha cinza apontando para a direita (`>`) ao lado do número da linha 13.
  * A linha 23 mostra a chave de fechamento final da classe `Main`.
* **O objetivo dessa cena:**  
  O professor limpou o espaço visual do editor para poder demonstrar variações de parâmetros e abrir espaço para o próximo método que ele vai ensinar a seguir: o `average(double... numbers)`.

---

## 2. O que é o Recurso de Dobradura de Código (*Code Folding*) no IntelliJ IDEA?

Quando você olha para a linha 13 e vê:
```java
static int add(int... numbers){...}
```
Muitos iniciantes se assustam e perguntam:  
> *"Cadê o `int sum = 0`? Cadê o laço `for-each`? O professor apagou o código?!"*

**Não! O código NÃO foi apagado!**  
Isso é um recurso visual maravilhoso dos ambientes de desenvolvimento profissionais (como o IntelliJ IDEA e o VS Code) chamado **Code Folding** (Dobradura ou Recolhimento de Código).

### Como Funciona:
1. Ao lado esquerdo de qualquer método ou bloco que tenha chaves `{ }`, a IDE coloca uma pequena setinha ou traço.
2. Ao clicar nessa setinha, a IDE "dobra" o código para dentro, como se fosse um mapa de papel dobrado, e coloca `{...}` no lugar.
3. Se você der um clique duplo em cima de `{...}` ou na setinha, o método se desdobra e todas as linhas reaparecem intactas!

> 💡 **Por que os programadores fazem isso?**  
> Para não poluir a tela! Uma vez que o método `add` já foi testado, aprovado e está funcionando perfeitamente, você dobra ele para focar a sua atenção no próximo código que vai escrever.

---

## 3. Por que o Professor Selecionou os Números `2, 3, 4, 5, 6`?

O professor destacou esses números com o cursor por dois motivos pedagógicos muito claros:

1. **Mostrar a Elasticidade do `varargs`:**  
   Ele mostra que você pode apagar quantos números quiser da lista. Se você apagar essa seleção azul, sobrará apenas `add(1)` — e o método funcionará perfeitamente, devolvendo `1`!
2. **Preparar a Mudança de Exemplo:**  
   Ele está prestes a substituir a chamada de `add` pela chamada do novo método que vai ensinar em seguida: o método `average` (calcular a média de notas ou números).

---

## 4. O Código Java Completo, Numerado e Expandido

Mesmo que a IDE esteja mostrando o método recolhido com `{...}`, por baixo dos panos o arquivo `Main.java` contém exatamente este código:

```java
public class Main {
    public static void main(String[] args) {

        // varargs = allow a method to accept a varying # of arguments
        //           makes methods more flexible, no need for overloaded methods
        //           java will pack the arguments into an array
        //           ... (ellipsis)

        System.out.println(add(1, 2, 3, 4, 5, 6));

    }

    static int add(int... numbers) {

        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }
}
```

---

## 5. Explicação Cirúrgica Linha por Linha

---

### Linhas 1 a 3: Declaração da Classe e Método `main`
```java
public class Main {
    public static void main(String[] args) {
```
* **O que faz:** Inicializa a estrutura da aplicação Java.
* **No console:** Nada é exibido.

---

### Linhas 5 a 8: Comentários Teóricos
* **O que faz:** As 4 regras de ouro do `varargs` mantidas no topo do arquivo como lembrete de estudo.
* **No console:** Nada é exibido.

---

### Linha 10: `System.out.println(add(1, 2, 3, 4, 5, 6));`
* **O que faz:**
  * O cursor do professor está selecionando `2, 3, 4, 5, 6`.
  * Se o código for executado exatamente como está na tela, a chamada envia os 6 inteiros para o método `add`.
  * O método calcula a soma total (`21`).
  * O comando `println` imprime o número `21` no console.
* **No console:** Imprime `21`.

---

### Linha 12: `}`
* **O que faz:** Fecha o método `main`.

---

### Linha 13: `static int add(int... numbers) { ... }`
* **O que faz:** É a representação dobrada (*folded*) do método que criamos nas aulas anteriores:
  * Recebe uma quantidade flexível de inteiros em um array `numbers`.
  * Tem um acumulador `int sum = 0;`.
  * Percorre o array somando com `for (int number : numbers) sum += number;`.
  * Devolve a soma acumulada com `return sum;`.
* **No console:** Nada é exibido diretamente nesta linha.

---

### Linhas 14 a 22: Linhas Ocultadas pelo Code Folding
* Estão protegidas e ativas dentro do `{...}`.

---

### Linha 23: `}`
* **O que faz:** Fecha o bloco da classe principal `Main`. Fim do arquivo.

---

## 6. O que Sai no Console Nesta Execução?

Se você clicar em **Run** mantendo a linha 10 com os 6 números:

```text
21

Process finished with exit code 0
```

Se o professor pressionar a tecla `Backspace` e apagar o trecho selecionado, ficando apenas:
```java
System.out.println(add(1));
```
O console exibirá:
```text
1

Process finished with exit code 0
```
Isso comprova que o `varargs` aceita desde 1 único número até quantos você quiser colocar!

---

## 7. A Ponte para o Próximo Exemplo: O Desafio da Média (`average`)

O professor conclui a parte de somar números e lança um novo desafio:
> *"Até agora, nós só somamos números inteiros (`add`). Mas e se precisarmos calcular a **Média Aritmética** de notas de alunos (`average`)?"*

Para calcular a média, somar os números não é suficiente! Você precisa:
1. Somar todos os números passados (`sum`).
2. Saber **quantos números** foram passados ao todo!
3. Dividir a soma pela quantidade de números:  
   $$\text{Média} = \frac{\text{sum}}{\text{quantidade}}$$
4. E trabalhar com números quebrados (**`double`**), porque notas e médias costumam ter casas decimais (ex: `7.5`, `8.2`)!

É exatamente isso que o professor vai construir a seguir:
```java
static double average(double... numbers) {
    ...
    return sum / numbers.length;
}
```

---

## 8. Resumo Prático em Uma Tabela Mental

| Dúvida Comum | Resposta Clara |
| :--- | :--- |
| **O que significa `{...}` na linha 13?** | É o recurso de *Code Folding* da IDE, que encolhe métodos prontos para economizar espaço visual na tela. |
| **O código dentro de `{...}` sumiu?** | Não! Continua existindo e funcionando normalmente; está apenas escondido. |
| **Por que o professor selecionou `2, 3, 4, 5, 6`?** | Para demonstrar que podemos alterar ou apagar os argumentos livremente, e para preparar a transição para o método `average`. |
| **Qual será o próximo método?** | `average(double... numbers)`, que calcula a média e utiliza a propriedade `.length` do array do varargs! |

---

*(Fim do guia do Capítulo 34 — Parte 5. Pronto para copiar e colar diretamente no seu Google Docs!)*
