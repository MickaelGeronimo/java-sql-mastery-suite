# 🎟️ Capítulo 12: `nested if statements` (Estruturas `if` Aninhadas) — Parte 1
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que Significa a Palavra "Nested"?

Em inglês:
* **`Nest`** = Ninho de pássaro.
* **`Nested`** = Aninhado (guardado um dentro do outro, encaixado).

No mundo da programação, um **`nested if`** significa simplesmente:
> **Um comando `if` colocado DENTRO das chaves `{ }` de outro comando `if`!**

---

## 🪆 2. A Grande Analogia: As Bonecas Russas (Matryoshka) ou As Duas Portas da Balada VIP

Imagine uma casa de festas exclusiva:
1. **1ª Porta (A porta da rua):** O segurança pergunta: *"Você tem mais de 18 anos?"* (`if (age >= 18)`).
   * Se você tiver menos de 18, você volta para casa na hora. Nem chega a ver o que tem lá dentro.
2. **2ª Porta (A entrada do camarote VIP):** Só quem já conseguiu entrar na festa passa por essa segunda porta. O segurança pergunta: *"Você tem a pulseira VIP?"* (`if (hasVipPass)`).

```text
Porta 1: Entrada Principal
[ if (condicao_de_fora) ]
       │
       ▼ (Se for VERDADEIRO, entra...)
       │
       ├───> Porta 2: Sala VIP Interna
       │     [ if (condicao_de_dentro) ]
       │            │
       │            ▼ (Se for VERDADEIRO...)
       │            Ganha benefício especial!
```

Ou seja: **o segundo `if` só é avaliado se o primeiro `if` deixar você passar!**

---

## 🧱 3. O Esqueleto Teórico do `nested if`

Antes de programar o exemplo real, o professor desenhou a estrutura básica para mostrar o visual do código:

```java
if (condicao1) {
    // Bloco do 1º IF (Porta de fora)

    if (condicao2) {
        // Bloco do 2º IF (Porta de dentro - aninhado)
    }
    else if (condicao3) {
        // Outra possibilidade dentro do 1º IF
    }
    else {
        // Se a condicao2 e condicao3 falharem
    }
}
```

Repare no recuo das linhas (a indentação): o `if` interno fica mais para a direita, empurrado por espaços, deixando claro que ele pertence à "barriga" do `if` externo!

---

## 🎟️ 4. O Exemplo Prático: Desconto no Ingresso de Cinema

Para ensinar isso na prática, o professor começou a criar um sistema de bilheteria que dá desconto para **estudantes** e **idosos** (*seniors*).

### 💻 Código Escrito:

```java
boolean isStudent = true;
boolean isSenior;
double price = 9.99;

if (isStudent) {
    System.out.println("You get a student discount of 10%");
    price *= 0.9;
}
else {
    price *= 1;
}

System.out.println("The price of a ticket is: $" + price);
```

---

## 🔬 5. Linha por Linha com Microscópio (Passo a Passo)

Vamos entender cada letrinha e cada símbolo:

### 🔹 Linha 1: `boolean isStudent = true;`
* Cria uma caixinha chamada `isStudent` (É estudante?).
* O tipo é `boolean` (só aceita `true` ou `false`).
* Aqui definimos como `true` (sim, a pessoa é estudante).

### 🔹 Linha 2: `boolean isSenior;`
* Cria outra caixinha chamada `isSenior` (É idoso/terceira idade?).
* **Por que ela está sem valor?** O professor apenas declarou a variável, preparando o terreno para a próxima etapa da aula, onde vamos aninhar a checagem de idoso!

### 🔹 Linha 3: `double price = 9.99;`
* O preço cheio e padrão do ingresso é **$9.99**.
* É `double` porque tem centavos.

### 🔹 Linha 4: `if (isStudent) {`
* O computador olha para a variável `isStudent`.
* Como ela vale `true`, a condição é satisfeita e o computador entra no bloco entre as chaves `{ }`!

### 🔹 Linha 5: `System.out.println("You get a student discount of 10%");`
* Imprime a mensagem alegre avisando que a pessoa ganhou 10% de desconto de estudante.

### 🔹 Linha 6: `price *= 0.9;` (O Pulo do Gato Matemático! 🐱)

> [!IMPORTANT]
> **O que significa o operador `*=`?**  
> `price *= 0.9;` é um atalho elegante do Java para:  
> `price = price * 0.9;`  
> Ele pega o preço atual, multiplica por `0.9` e guarda de volta na caixinha `price`.
> 
> **Por que multiplicar por `0.9` tira 10% de desconto?**  
> Pense em porcentagem pura:
> * O preço cheio é **100%** ($1.00$).
> * Se você ganha um desconto de **10%** ($0.10$), você vai pagar apenas **90%** do valor original ($100\% - 10\% = 90\%$).
> * Em formato decimal, $90\% = 0.90 = 0.9$!
> * Logo, multiplicar por `0.9` calcula direto o valor com 10% de desconto em uma única operação!

### 🔹 Linhas 7 a 9: `else { price *= 1; }`
* Se a pessoa **NÃO** for estudante (`isStudent == false`), o programa entra no `else`.
* Multiplicar por `1` não muda nada ($9.99 \times 1 = 9.99$), ou seja, paga 100% do preço, zero desconto.

### 🔹 Linha 10: `System.out.println("The price of a ticket is: $" + price);`
* Mostra o preço final calculado na tela:  
  `The price of a ticket is: $8.991` (o valor original de 9.99 menos os 10%!).

---

## 🔮 6. O Que o Professor Vai Fazer a Seguir?

Repare que a variável **`isSenior`** ficou ali quietinha.  
Nas próximas telas, o professor vai fazer a grande mágica do capítulo:  
* E se a pessoa for estudante **E** idosa ao mesmo tempo?
* Ela ganha 10% ou ganha 20%?
* Como colocar um `if` testando `isSenior` **dentro** do `if(isStudent)` ou no `else`?

É aí que o ninho de `if` (*nested if*) vai brilhar!

---

## 💻 7. Código Completo Desta Etapa (Pronto para Copiar e Rodar)

Copie e cole no seu arquivo `Main.java`:

```java
public class Main {
    public static void main(String[] args) {

        // Nested if statements (Capítulo 12)

        boolean isStudent = true;
        boolean isSenior = false; // Deixamos false por enquanto
        double price = 9.99;

        // Verifica se a pessoa é estudante
        if (isStudent) {
            System.out.println("You get a student discount of 10%");
            price *= 0.9; // Aplica 10% de desconto (paga 90%)
        }
        else {
            price *= 1;   // Sem desconto (paga 100%)
        }

        // Mostra o preço final
        System.out.println("The price of a ticket is: $" + price);
    }
}
```
