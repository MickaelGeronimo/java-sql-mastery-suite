# 🅿️ Guia Definitivo do `[width]` no `System.out.printf` em Java
*(Apostila de Estudo — Pronta para o Google Docs)*

---

## 📌 1. Onde Estamos na "Fórmula Mágica"?

No Java, quando usamos o comando de texto formatado `System.out.printf()`, existe uma fórmula completa de configuração que fica dentro das aspas:

$$\% \text{ [flags] } \text{ [width] } \text{ [.precision] } \text{ [specifier] }$$

Neste guia, vamos focar exclusivamente no elemento **`[width]`** (que em português significa **LARGURA** ou **TAMANHO TOTAL MÍNIMO**).

---

## 🚗 2. A Grande Analogia das 4 Vagas de Estacionamento

Para entender o `[width]`, imagine que você reservou no estacionamento da memória exatamente **4 vagas** lado a lado:

```text
[ Vaga 1 ]  [ Vaga 2 ]  [ Vaga 3 ]  [ Vaga 4 ]
```

O número **`4`** no código diz ao Java:
> *"Java, não importa se o número que eu vou te dar é pequenininho ou grande: garanta na tela um espaço de pelo menos 4 caracteres!"*

A grande dúvida é: **o que acontece se o nosso número não ocupar todas as 4 vagas?**  
É aqui que entram os **3 comportamentos práticos** que o Java oferece!

---

## 🟢 3. Comportamento 1: `%04d` — Preenchimento com Zeros (Estilo Crachá / ID)

### 💻 Código Java:
```java
int id1 = 1;
int id2 = 23;
int id3 = 456;
int id4 = 7890;

System.out.printf("%04d\n", id1);
System.out.printf("%04d\n", id2);
System.out.printf("%04d\n", id3);
System.out.printf("%04d\n", id4);
```

### 🔬 O que cada pedaço do `%04d\n` significa?
* **`%`**: Avisa ao Java: *"Atenção, aqui vem uma ordem de formatação!"*.
* **`0`**: A flag de preenchimento. Significa: *"Se sobrarem vagas vazias, preencha todas com ZEROS"*.
* **`4`**: A largura (`width`). Significa: *"O tamanho final tem que ter 4 caracteres"*.
* **`d`**: O especificador de tipo (*decimal integer*). Significa: *"Vou colocar aqui um número inteiro (`int`)"*.
* **`\n`**: Pula para a próxima linha após imprimir.

### 🧩 Como as vagas são preenchidas:
* O número `1` precisa de 1 vaga $\rightarrow$ as 3 vagas que sobraram viram `0` $\rightarrow$ **`0001`**
* O número `23` precisa de 2 vagas $\rightarrow$ as 2 vagas que sobraram viram `0` $\rightarrow$ **`0023`**
* O número `456` precisa de 3 vagas $\rightarrow$ a 1 vaga que sobrou vira `0` $\rightarrow$ **`0456`**
* O número `7890` precisa de 4 vagas $\rightarrow$ nenhuma vaga sobrou $\rightarrow$ **`7890`**

### 🖥️ Saída no Console:
```text
0001
0023
0456
7890
```

> [!TIP]
> **Onde isso é usado na vida real?**  
> Em sistemas bancários, numeração de boletos, números de série de produtos, códigos de matrícula e crachás de funcionários. Todos ficam com o mesmo tamanho padronizado!

---

## 🟢 4. Comportamento 2: `%4d` — Alinhado à Direita com Espaços em Branco

### 💻 Código Java:
```java
int id1 = 1;
int id2 = 23;
int id3 = 456;
int id4 = 7890;

System.out.printf("%4d\n", id1);
System.out.printf("%4d\n", id2);
System.out.printf("%4d\n", id3);
System.out.printf("%4d\n", id4);
```

### 🔬 Qual a diferença aqui?
Repare que **removemos o zero** antes do 4! Ficou apenas `%4d`.
* Quando não colocamos o `0`, o Java reserva as mesmas 4 vagas, mas preenche os espaços vazios com **ESPAÇOS EM BRANCO**.
* Por padrão no Java, os números são colocados no final e os espaços ficam na frente. Isso faz o texto ficar **alinhado à direita**!

### 🧩 Como as vagas são preenchidas visualmente:
```text
[   ] [   ] [   ] [ 1 ]  -> '   1' (3 espaços antes do 1)
[   ] [   ] [ 2 ] [ 3 ]  -> '  23' (2 espaços antes do 23)
[   ] [ 4 ] [ 5 ] [ 6 ]  -> ' 456' (1 espaço antes do 456)
[ 7 ] [ 8 ] [ 9 ] [ 0 ]  -> '7890' (0 espaços, ocupou tudo)
```

### 🖥️ Saída no Console:
```text
   1
  23
 456
7890
```

> [!TIP]
> **Onde isso é usado na vida real?**  
> Em notas fiscais, faturas e somatórios matemáticos. Você alinha a coluna das unidades com unidades, dezenas com dezenas e centenas com centenas, deixando a leitura natural para o olho humano.

---

## 🟢 5. Comportamento 3: `%-4d` — Alinhado à Esquerda

### 💻 Código Java:
```java
int id1 = 1;
int id2 = 23;
int id3 = 456;
int id4 = 7890;

System.out.printf("%-4d\n", id1);
System.out.printf("%-4d\n", id2);
System.out.printf("%-4d\n", id3);
System.out.printf("%-4d\n", id4);
```

### ⚠️ Pegadinha de Iniciante: O que significa o sinal de menos (`-`)?
* O sinal de menos **NÃO** transforma o número em negativo!
* Dentro da formatação do `printf`, o sinal de menos é uma ordem direta: **"ALINHE À ESQUERDA"**.
* O número encosta na parede esquerda, e os espaços vazios são empurrados para **DEPOIS** do número.

### 🧩 Como as vagas são preenchidas visualmente:
```text
[ 1 ] [   ] [   ] [   ]  -> '1   ' (1 seguido de 3 espaços invisíveis)
[ 2 ] [ 3 ] [   ] [   ]  -> '23  ' (23 seguido de 2 espaços invisíveis)
[ 4 ] [ 5 ] [ 6 ] [   ]  -> '456 ' (456 seguido de 1 espaço invisível)
[ 7 ] [ 8 ] [ 9 ] [ 0 ]  -> '7890' (ocupou todas as vagas)
```

### 🖥️ Saída no Console:
```text
1   
23  
456 
7890
```
*(Se você selecionar com o mouse o texto acima no console, verá que há espaços vazios após o `1`, o `23` e o `456`)*.

> [!TIP]
> **Onde isso é usado na vida real?**  
> Em cabeçalhos de relatórios, colunas de texto ou tabelas onde a primeira coluna deve começar no mesmo ponto e dar um respiro antes da coluna seguinte.

---

## ❓ 6. Dúvida Crucial: E se o número tiver MAIS de 4 dígitos?

Imagine que um funcionário novo tem o ID `12345` (5 dígitos) e você usou a regra de `%4d`:

```java
int idExtra = 12345;
System.out.printf("%4d\n", idExtra);
```

O que o Java faz? Ele corta o número e mostra só `1234`?
> **NÃO! O Java nunca destrói nem corta os seus dados numéricos!**  
> O `width` define um tamanho **MÍNIMO**, nunca um limite máximo.  
> Se o número for maior que a largura definida, o Java ignora a reserva de 4 vagas e imprime o número completo: `12345`.

---

## 📊 7. Tabela Comparativa de Bolso (Para Consulta Rápida)

Veja a diferença exata de cada um aplicando no número `1`:

| Formatação | Nome do Efeito | Como fica o número `1`? | Explicação em uma frase |
| :---: | :--- | :---: | :--- |
| **`%04d`** | Preenchimento com Zeros | `0001` | Reserva 4 vagas e troca vazios por zeros. |
| **`%4d`** | Alinhamento à Direita | `   1` | Reserva 4 vagas e coloca espaços antes. |
| **`%-4d`** | Alinhamento à Esquerda | `1   ` | Reserva 4 vagas e coloca espaços depois. |

---

## 🚀 8. Código Completo para Copiar, Colar e Executar

Copie o código abaixo e cole no seu arquivo `Main.java` para ver os três comportamentos funcionando lado a lado:

```java
public class Main {
    public static void main(String[] args) {

        int id1 = 1;
        int id2 = 23;
        int id3 = 456;
        int id4 = 7890;

        System.out.println("--- 1. COM PREENCHIMENTO DE ZEROS (%04d) ---");
        System.out.printf("%04d\n", id1);
        System.out.printf("%04d\n", id2);
        System.out.printf("%04d\n", id3);
        System.out.printf("%04d\n", id4);

        System.out.println("\n--- 2. ALINHADO À DIREITA COM ESPAÇOS (%4d) ---");
        System.out.printf("%4d\n", id1);
        System.out.printf("%4d\n", id2);
        System.out.printf("%4d\n", id3);
        System.out.printf("%4d\n", id4);

        System.out.println("\n--- 3. ALINHADO À ESQUERDA (%-4d) ---");
        System.out.printf("%-4d\n", id1);
        System.out.printf("%-4d\n", id2);
        System.out.printf("%-4d\n", id3);
        System.out.printf("%-4d\n", id4);
    }
}
```
