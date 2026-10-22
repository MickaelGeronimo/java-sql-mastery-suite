# 🎟️ Capítulo 12: `nested if statements` — Parte 3 (Explicação Linha por Linha e os 4 Testes Reais)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Fez Aqui?

Nestas cenas, o professor fez algo que todo bom programador faz: **ele testou o programa ao vivo em todas as situações possíveis!**

Como nós temos duas variáveis que podem ser verdadeiras ou falsas (`isStudent` e `isSenior`), existem exatamente **4 combinações possíveis** na vida real:
1. **É Estudante, mas NÃO é Idoso** $\rightarrow$ Ganha 10% de desconto.
2. **NÃO é Estudante, mas É Idoso** $\rightarrow$ Ganha 20% de desconto.
3. **É Estudante E É Idoso ao mesmo tempo** $\rightarrow$ Ganha 30% de desconto (20% + 10%).
4. **NÃO é Estudante e NÃO é Idoso** $\rightarrow$ Não ganha desconto nenhum (preço cheio).

O professor executou o código mudando os valores para provar que a lógica dos `nested if` (um `if` dentro do outro) funciona perfeitamente em 100% dos casos!

---

## 🔬 2. Explicação Linha por Linha de TODO o Código (Sem Pular Nada!)

Vamos olhar linha por linha, da primeira chave até a última, com calma e riqueza de detalhes:

### 🏠 A Estrutura Básica do Java:
* **`public class Main {`**  
  Cria a classe principal do programa chamada `Main`. Tudo no Java precisa morar dentro de uma classe. A chave `{` abre a casa do nosso programa.
* **`public static void main(String[] args) {`**  
  É o ponto de partida do Java. Quando você aperta "Play", o computador começa a ler exatamente a partir daqui.

---

### 📦 As Variáveis de Configuração:
* **`boolean isStudent = true;`**  
  Cria uma caixinha chamada `isStudent`. O tipo `boolean` só aceita `true` (verdadeiro) ou `false` (falso). Aqui estamos dizendo se o cliente é estudante ou não.
* **`boolean isSenior = true;`**  
  Cria outra caixinha `boolean` chamada `isSenior`. Guarda se o cliente é idoso/terceira idade ou não.
* **`double price = 9.99;`**  
  Cria a caixinha `price` com o valor original do ingresso: **$9.99**. O tipo `double` é obrigatório porque o preço tem ponto flutuante (centavos).

---

### 🚪 O Primeiro Grande Bloco: Se o Cliente for Estudante (`if`)

* **`if (isStudent) {`**  
  **O 1º Porteiro Externo:** Ele pergunta: *"A caixinha `isStudent` é verdadeira?"*.  
  Se for `true`, o Java entra por esta porta e **ignora completamente o `else` lá de baixo**!

  * **`if (isSenior) {`**  
    **O 2º Porteiro (Aninhado / Interno):** Como o cliente já provou que é estudante, agora o Java faz a segunda pergunta: *"Além de estudante, você também é idoso?"*.  
    Se for `true`, o cliente tem direito ao **combo máximo de desconto**:
    * **`System.out.println("You get a senior discount of 20%");`** $\rightarrow$ Imprime que ganhou 20% de idoso.
    * **`System.out.println("You get a student discount of 10%");`** $\rightarrow$ Imprime que ganhou 10% de estudante.
    * **`price *= 0.7;`** $\rightarrow$ Como ganhou $20\% + 10\% = 30\%$ de desconto, ele só paga $70\%$ do ingresso ($100\% - 30\% = 70\% = 0.7$). Multiplica o preço por `0.7`.
  * **`}`** $\rightarrow$ Fecha o bloco do estudante idoso.

  * **`else {`**  
    O `else` interno: Só entra aqui quem é estudante, mas **NÃO** é idoso (`isSenior == false`).
    * **`System.out.println("You get a student discount of 10%");`** $\rightarrow$ Avisa que ganhou os 10% de estudante.
    * **`price *= 0.9;`** $\rightarrow$ Paga $90\%$ do ingresso ($100\% - 10\% = 90\% = 0.9$). Multiplica o preço por `0.9`.
  * **`}`** $\rightarrow$ Fecha o `else` interno.

* **`}`** $\rightarrow$ Fecha o bloco do 1º `if` externo (`isStudent`).

---

### 🚪 O Segundo Grande Bloco: Se o Cliente NÃO for Estudante (`else`)

* **`else {`**  
  **O Porteiro dos Não-Estudantes:** Se lá em cima `isStudent` era `false`, o Java pula direto para cá!

  * **`if (isSenior) {`**  
    **O Porteiro Interno do `else`:** O cliente não é estudante, mas o Java pergunta: *"Você é idoso?"*.  
    Se for `true`:
    * **`System.out.println("You get a senior discount of 20%");`** $\rightarrow$ Avisa que ganhou 20% de idoso.
    * **`price *= 0.8;`** $\rightarrow$ Paga $80\%$ do ingresso ($100\% - 20\% = 80\% = 0.8$). Multiplica o preço por `0.8`.
  * **`}`** $\rightarrow$ Fecha o bloco do idoso não-estudante.

  * **`else {`**  
    O `else` de dentro do `else`: O cliente **não é estudante** e **não é idoso** (cliente comum).
    * **`price *= 1;`** $\rightarrow$ Multiplica por 1, ou seja, paga $100\%$ do preço sem nenhum desconto.
  * **`}`** $\rightarrow$ Fecha o `else` do cliente comum.

* **`}`** $\rightarrow$ Fecha o grande bloco do `else` externo.

---

### 📢 O Finalzinho:
* **`System.out.println("The price of a ticket is: $" + price);`**  
  Mostra na tela o valor final que sobrou dentro da caixinha `price`.
* **`}`** $\rightarrow$ Fecha o método `main`.
* **`}`** $\rightarrow$ Fecha a classe `Main`.

---

## 📊 3. Tabela Comparativa dos 4 Testes Reais Executados no Console

Veja exatamente o que acontece em cada teste que o professor executou na aula:

| Teste | `isStudent` | `isSenior` | Caminho Percorrido no Código | Mensagens que saem no Console | Conta Feita | Preço Final Impresso |
| :---: | :---: | :---: | :--- | :--- | :---: | :---: |
| **1** | `true` | `false` | Entra no 1º `if`, cai no `else` interno | *"You get a student discount of 10%"* | $9.99 \times 0.9$ | **`$8.99`** |
| **2** | `false` | `true` | Pula para o `else` externo, entra no `if (isSenior)` | *"You get a senior discount of 20%"* | $9.99 \times 0.8$ | **`$7.99`** |
| **3** | `true` | `true` | Entra no 1º `if`, entra no `if (isSenior)` interno | *"You get a senior discount of 20%"*<br>*"You get a student discount of 10%"* | $9.99 \times 0.7$ | **`$6.99`** |
| **4** | `false` | `false` | Pula para o `else` externo, cai no `else` interno | *(Nenhuma mensagem de desconto)* | $9.99 \times 1$ | **`$9.99`** |

---

## 💡 4. O Que Esse Exemplo nos Ensina Sobre Lógica de Programação?

1. **Um `if` interno depende do `if` externo:**  
   O segundo `if (isSenior)` só é testado se o primeiro `if (isStudent)` permitir a entrada.
2. **Organização Visual (Indentação):**  
   O código fica "empurrado" para a direita a cada chave `{` que abrimos. Isso não é só para ficar bonito: é para os nossos olhos saberem quem manda em quem!
3. **Controle Preciso:**  
   Com `nested if`, conseguimos tratar casos complexos da vida real com facilidade e clareza.

---

## 💻 5. Código Completo para Copiar e Brincar no seu Computador

Copie o código abaixo e cole no seu arquivo `Main.java`. Altere as variáveis `isStudent` e `isSenior` nas linhas 6 e 7 para ver a mágica da tabela da verdade acontecer na sua frente:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 12: Nested if statements (Bilheteria de Cinema)

        boolean isStudent = true; // Mude para true ou false
        boolean isSenior = true;  // Mude para true ou false
        double price = 9.99;

        // 1. O cliente é estudante?
        if (isStudent) {
            // Se for estudante, ele também é idoso?
            if (isSenior) {
                System.out.println("You get a senior discount of 20%");
                System.out.println("You get a student discount of 10%");
                price *= 0.7; // Ganha 30% de desconto (paga 70%)
            }
            else {
                System.out.println("You get a student discount of 10%");
                price *= 0.9; // Ganha apenas 10% de estudante (paga 90%)
            }
        }
        // 2. O cliente NÃO é estudante
        else {
            // Mesmo não sendo estudante, ele é idoso?
            if (isSenior) {
                System.out.println("You get a senior discount of 20%");
                price *= 0.8; // Ganha 20% de desconto de idoso (paga 80%)
            }
            else {
                price *= 1;   // Cliente comum: zero desconto (paga 100%)
            }
        }

        // Mostra o preço final calculado
        System.out.println("The price of a ticket is: $" + price);
    }
}
```
