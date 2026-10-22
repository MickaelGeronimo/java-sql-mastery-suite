# 🎯 Por que o resultado deu `$6.992999999999999`? Está Certo?
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🌟 Resposta Curta e Direta:
**SIM! ESTÁ 1000% CERTO!**  
O seu código funcionou com perfeição absoluta. O computador executou exatamente a lógica correta:
1. Reconheceu que o cliente é **estudante** e **idoso**.
2. Deu os **dois descontos (20% + 10%)**.
3. Multiplicou o preço por `0.7` ($30\%$ de desconto).

---

## 🧠 Mas de onde saíram todos esses noves no final (`6.992999999999999`)?

Na matemática do papel com caneta:
$$9.99 \times 0.7 = 6.993$$

Então por que o computador mostrou `6.992999999999999`?

### 🤖 A Mente dos Computadores (Base 10 vs. Base 2):
* **Nós, seres humanos:** Contamos na base 10 (temos 10 dedos nas mãos: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9).
* **Os computadores:** Contam em binário (base 2: apenas `0` e `1`).

### 🍰 A Analogia do Bolo Dividido por 3:
Na matemática humana, se você dividir $10$ reais entre $3$ pessoas, quanto dá?
$$10 \div 3 = 3,333333333333333...$$
Não existe um número exato para parar; os números `3` continuam para sempre (é uma dízima periódica).

**Nos computadores acontece a mesma coisa com números decimais como `0.7` e `0.99`!**  
Em binário (com bits de 0 e 1), o computador não consegue guardar `0.7` de forma 100% exata na caixinha `double`. Ele guarda uma aproximação minúscula:
$$0.7000000000000000666...$$

Quando ele multiplica $9.99 \times 0.7$, essa minúscula sobra binária aparece no finalzinho como:
```text
6.992999999999999
```

Isso acontece em **todas as linguagens de programação do planeta Terra** (Java, Python, C++, JavaScript, etc.). Isso se chama **imprecisão de ponto flutuante**.

---

## 🎩 O Grande Segredo: Como o Professor Fez para Mostrar `$6.99` Bonito?

Lembra do **Capítulo 10** que acabamos de estudar?  
É **EXATAMENTE POR ISSO** que o Java inventou o **`System.out.printf()`**!

### ❌ Se você usa `println` (o número sai cru):
```java
System.out.println("The price of a ticket is: $" + price);
// Saída: The price of a ticket is: $6.992999999999999
```

### ✅ Se você usa `printf` com `%.2f` (o Java corta os noves e arredonda):
```java
System.out.printf("The price of a ticket is: $%.2f\n", price);
// Saída: The price of a ticket is: $6.99
```

* O **`%.2f`** diz ao Java: *"Irmão, não me mostre esses noves infinitos! Pegue só as 2 primeiras casas de centavos e arredonde bonitinho para mim!"*.

---

## 💻 Faça o Teste no seu IntelliJ Agora Mesmo:

Substitua a sua última linha por:

```java
System.out.printf("The price of a ticket is: $%.2f\n", price);
```

E aperte Play. Você verá no console:
```text
You get a senior discount of 20%
You get a student discount of 10%
The price of a ticket is: $6.99
```

Fica perfeito, exatamente como o do professor! 🚀
