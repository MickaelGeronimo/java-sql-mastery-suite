# 🧘 O Guia Definitivo das 4 Bandeiras (`[flags]`) do `printf`!

Reescrevemos este guia com o máximo de detalhes, desenhos e calma para você dominar as **4 bandeiras mais famosas do Java** sem deixar nenhuma dúvida para trás!

---

## 📋 1. O Código Oficial da Imagem 4 (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // [flags]

        // + = output a plus
        // , = comma grouping separator
        // ( = negative numbers are enclosed in ()
        // space = display a minus if negative, space if positive

        double price1 = 9.99;
        double price2 = 100.15;
        double price3 = -54.01;

        System.out.printf("% .2f\n", price1);
        System.out.printf("% .2f\n", price2);
        System.out.printf("% .2f\n", price3);
    }
}
```

---

## 📍 2. Onde a Bandeira Fica Escondida no Código?

Olhe a posição exata da bandeira dentro do comando:

```
          %   [FLAG]   .2     f     \n
          │     │       │     │      │
       Início   │   Precisão Tipo  Pula
                │  (2 casas) double linha
                │
                └─ A BANDEIRA FICA BEM AQUI!
                   (Entre a porcentagem e o ponto!)
```

---

## 🔬 3. As 4 Imagens Dissecadas com Lupa:

---

### 🟢 IMAGEM 1: A Bandeira `+` (O Sinal de Mais Forçado)

```java
System.out.printf("%+.2f\n", price1);
```

* **O Problema Normal:** No dia a dia, o computador só mostra o sinal de menos nos negativos (`-54.01`). Nos positivos, ele esconde o sinal (`9.99`).
* **O Efeito da Bandeira `+`:** Obriga o Java a **carimbar o sinal de mais** em números positivos!
* **Console:**
  ```
  +9.99
  +100.15
  -54.01
  ```
* 💰 **Para que serve?** Muito usado em aplicativos de finanças e investimentos para mostrar **lucro** verde (`+150.00`)!

---

### 🟢 IMAGEM 2: A Bandeira `,` (Separador de Milhar)

> ⚠️ **Atenção ao Detalhe do Autor:**
> Se você tentar colocar a vírgula no número `9.99`, **nenhuma vírgula vai aparecer**! Por quê? Porque 9 reais não tem milhar! A vírgula só aparece quando o número passa de 1.000!
> Por isso, na Imagem 2, o autor mudou os preços para números grandes:

```java
double price1 = 9000.99;
double price2 = 100000.15;
double price3 = -54000.01;

System.out.printf("%,.2f\n", price1); // 👈 Flag de vírgula!
```

* **Console:**
  ```
  9,000.99
  100,000.15
  -54,000.01
  ```
* 👓 **Para que serve?** Fica infinitamente mais fácil de ler `100,000.15` do que tentar contar zeros em `100000.15`!

---

### 🟢 IMAGEM 3: A Bandeira `(` (O Estilo dos Contadores e Bancos)

```java
System.out.printf("%(.2f\n", price3); // 👈 Flag de parênteses!
```

* **O que acontece:**
  * Se o número for positivo $\rightarrow$ não muda nada (`9.99`).
  * Se o número for **NEGATIVO** $\rightarrow$ o sinal de menos `-` some e o número fica **dentro de parênteses**!
* **Console:**
  ```
  9.99
  100.15
  (54.01)    👈 Negativo com parênteses contábeis!
  ```
* 🏦 **Por que isso existe?** Na contabilidade mundial e em balanços bancários, números entre parênteses indicam **prejuízo ou saldo devedor**!

---

### 🟢 IMAGEM 4: A Bandeira de ESPAÇO `' '` (O Alinhamento Perfeito)

Olhe com atenção para o espacinho em branco entre o `%` e o ponto:
```java
System.out.printf("% .2f\n", price1);
```

#### 📏 Veja o Alinhamento Visual no Console:
Sem o espaço, a coluna ficaria torta porque o sinal de menos empurra o número para o lado. Com a bandeira de espaço, o Java reserva uma "vaga de garagem" para cada número:

```
[ ESPAÇO ] [ 9 ] [ . ] [ 9 ] [ 9 ]    👈 O espaço reserva o lugar!
[ ESPAÇO ] [ 1 ] [ 0 ] [ 0 ] [ . ] [ 1 ] [ 5 ]
[   -    ] [ 5 ] [ 4 ] [ . ] [ 0 ] [ 1 ]   👈 O sinal de menos ocupa a vaga!
```

Assim, todas as vírgulas e todos os dígitos ficam perfeitamente alinhados na vertical! 📐

---

## 🌟 4. BÔNUS: Podemos Combinar Bandeiras Juntas?!

**SIM! Você pode misturar bandeiras!**
Por exemplo, se você quiser a **vírgula de milhar** E TAMBÉM o **sinal de mais** no mesmo número:

```java
double money = 50000.00;
System.out.printf("%+,.2f\n", money);
```
**Resultado no Console:**
```
+50,000.00
```
Ele colocou o sinal de mais (`+`), colocou a vírgula de milhar (`,`) e cortou para 2 casas (`.2f`) ao mesmo tempo! 🎩✨

---

## 🔍 5. Tabela Resumo Linha por Linha do Código

| Linha | Código | O que significa? |
| :---: | :--- | :--- |
| **5-14** | `// [flags] ...` | As anotações do professor explicando as 4 bandeiras. |
| **16** | `double price1 = 9.99;` | Primeiro preço (positivo). |
| **17** | `double price2 = 100.15;` | Segundo preço (positivo maior). |
| **18** | `double price3 = -54.01;` | Terceiro preço (negativo / desconto). |
| **20-22** | `System.out.printf("% .2f\n", price1);` | Aplica a bandeira e imprime com 2 casas decimais pulando linha. |
