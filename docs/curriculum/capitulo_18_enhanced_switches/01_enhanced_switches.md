# 💡 Capítulo 18: Enhanced Switches (Switches Modernos) — Parte 1: O Problema do `if - else if` Repetitivo

---

## 📌 1. Visão Geral: O Que o Autor Está Fazendo Aqui?

Bem-vindo ao **Capítulo 18: Enhanced Switches** (*Switches Aprimorados / Modernos*)!

O autor segue uma regra de ouro para ensinar programação:  
👉 **Primeiro ele te mostra a dor de cabeça do jeito antigo, para depois te mostrar como o jeito novo é maravilhoso e fácil!**

Nestas 4 imagens (das marcas **2:49:09** até **2:49:49**), ele:
1. **Escreveu o conceito nos comentários (Linhas 5 e 6):**
   Explicou que o *Enhanced Switch* é um recurso moderno (lançado no **Java 14**) feito para substituir aquelas correntes gigantes e cansativas de `else if`.
2. **Criou o problema clássico dos dias da semana:**
   Fez um programa para avisar se o dia é **dia de semana** (*"It is a weekday"*) ou **fim de semana** (*"It is the weekend"*).
3. **Escreveu uma montanha de código repetitivo:**
   Repetiu `else if (day.equals(...))` para segunda, terça, quarta, quinta, sexta, sábado e domingo!
4. **Fez dois testes no console:**
   - Com `day = "Friday"` $\rightarrow$ Saiu no console: **`It is a weekday`**.
   - Com `day = "Saturday"` $\rightarrow$ Saiu no console: **`It is the weekend`**.

---

## 😫 2. A Dor de Cabeça: Por Que Esse Código Antigo é Ruim?

Dê uma olhada no tamanho desse código com `if - else if`:
- Para **Segunda-feira**: `System.out.println("It is a weekday");`
- Para **Terça-feira**: `System.out.println("It is a weekday");`
- Para **Quarta-feira**: `System.out.println("It is a weekday");`
- Para **Quinta-feira**: `System.out.println("It is a weekday");`
- Para **Sexta-feira**: `System.out.println("It is a weekday");`

Você percebeu que ele teve que escrever a mesma frase `"It is a weekday"` **5 vezes seguidas**?  
Se você tiver que mudar a frase no futuro para português (*"É um dia de semana"*), você teria que alterar em 5 lugares diferentes!

É exatamente para resolver essa chatice que o **Enhanced Switch** foi inventado!

---

## 💡 3. O Que Significa "Enhanced Switch"? (Comentário das Linhas 5 e 6)

```java
// Enhanced switch = A replacement to many else if statements
//                   (Java14 feature)
```

- **Switch:** Em inglês significa "interruptor" ou "chave de desvio" (como num trilho de trem, onde o trem vai direto para a linha certa).
- **Enhanced:** Significa "melhorado", "aprimorado", "moderno".
- **Java 14 feature:** Foi uma grande melhoria adicionada na versão 14 do Java que deixou o switch muito mais limpo, sem precisar daquelas palavras chatas `break;` do Java antigo.

*(O autor vai te mostrar essa sintaxe nova no próximo passo!)*

---

## 🖥️ 4. O Que Sai no Console em Cada Teste? (Conforme Você Pediu)

### 🟢 Teste 1: Com `String day = "Friday";` (Imagens 2 e 3)
1. O Java começa na linha 10: `day.equals("Monday")`? **Falso**.
2. Linha 13: `day.equals("Tuesday")`? **Falso**.
3. Linha 16: `day.equals("Wednesday")`? **Falso**.
4. Linha 19: `day.equals("Thursday")`? **Falso**.
5. Linha 22: `day.equals("Friday")`? **VERDADEIRO (`true`)**!
6. O Java entra no bloco da linha 23 e imprime:
```text
It is a weekday
```

---

### 🟡 Teste 2: Com `String day = "Saturday";` (Imagens 3 e 4)
1. O autor mudou o texto para `"Saturday"` (Sábado) e apertou Play.
2. O Java testa de segunda a sexta-feira $\rightarrow$ Todos dão **Falso**.
3. Chega no sábado: `day.equals("Saturday")` $\rightarrow$ **VERDADEIRO (`true`)**!
4. O Java entra na linha 26 e imprime:
```text
It is the weekend
```

---

## 🔍 5. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 34)

Abaixo está o código completo do jeito tradicional com `else if`:

### **Linha 1:** ` ` *(em branco)*
- **O que faz:** Espaço visual no início.

### **Linha 2:** `public class Main {`
- **O que faz:** Abre a classe principal onde o programa mora.

### **Linha 3:** `    public static void main(String[] args) {`
- **O que faz:** Ponto de ignição por onde o Java começa a rodar.

### **Linha 4:** ` ` *(em branco)*

### **Linha 5:** `        // Enhanced switch = A replacement to many else if statements`
- **O que faz:** Anotação do autor: *"Switch moderno = Um substituto para muitos comandos else if"*.

### **Linha 6:** `        //                   (Java14 feature)`
- **O que faz:** Anotação informando que é um recurso moderno do Java 14.

### **Linha 7:** ` ` *(em branco)*

### **Linha 8:** `        String day = "Friday";` *(ou `"Saturday"`)*
- **O que faz:** Cria a variável de texto `day` (dia) e guarda o nome do dia em inglês.

### **Linha 9:** ` ` *(em branco)*

### **Linha 10:** `        if(day.equals("Monday")){`
- **O que faz:** Testa se o dia é Segunda-feira (*Monday*).

### **Linha 11:** `            System.out.println("It is a weekday");`
- **O que faz:** Imprime *"É um dia de semana"*.

### **Linha 12:** `        }`
- **O que faz:** Fecha o bloco da segunda-feira.

### **Linha 13:** `        else if(day.equals("Tuesday")){`
- **O que faz:** Testa se é Terça-feira (*Tuesday*).

### **Linha 14:** `            System.out.println("It is a weekday");`
- **O que faz:** Imprime *"É um dia de semana"*.

### **Linha 15:** `        }`
- **O que faz:** Fecha o bloco da terça-feira.

### **Linha 16:** `        else if(day.equals("Wednesday")){`
- **O que faz:** Testa se é Quarta-feira (*Wednesday*).

### **Linha 17:** `            System.out.println("It is a weekday");`
- **O que faz:** Imprime *"É um dia de semana"*.

### **Linha 18:** `        }`
- **O que faz:** Fecha o bloco da quarta-feira.

### **Linha 19:** `        else if(day.equals("Thursday")){`
- **O que faz:** Testa se é Quinta-feira (*Thursday*).

### **Linha 20:** `            System.out.println("It is a weekday");`
- **O que faz:** Imprime *"É um dia de semana"*.

### **Linha 21:** `        }`
- **O que faz:** Fecha o bloco da quinta-feira.

### **Linha 22:** `        else if(day.equals("Friday")){`
- **O que faz:** Testa se é Sexta-feira (*Friday*).

### **Linha 23:** `            System.out.println("It is a weekday");`
- **O que faz:** Imprime *"É um dia de semana"*.

### **Linha 24:** `        }`
- **O que faz:** Fecha o bloco da sexta-feira.

### **Linha 25:** `        else if(day.equals("Saturday")){`
- **O que faz:** Testa se é Sábado (*Saturday*).

### **Linha 26:** `            System.out.println("It is the weekend");`
- **O que faz:** Imprime *"É fim de semana"*.

### **Linha 27:** `        }`
- **O que faz:** Fecha o bloco do sábado.

### **Linha 28:** `        else if(day.equals("Sunday")){`
- **O que faz:** Testa se é Domingo (*Sunday*).

### **Linha 29:** `            System.out.println("It is the weekend");`
- **O que faz:** Imprime *"É fim de semana"*.

### **Linha 30:** `        }`
- **O que faz:** Fecha o bloco do domingo.

### **Linha 31:** `        else{`
- **O que faz:** Se não for nenhum dia válido (ex: *"Pizza"*).

### **Linha 32:** `            System.out.println(day + " is not a day");`
- **O que faz:** Imprime *"não é um dia válido"*.

### **Linha 33:** `        }`
- **O que faz:** Fecha o bloco do `else`.

### **Linha 34:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 35:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 6. Código Completo Pronto para Copiar e Testar

```java
public class Main {
    public static void main(String[] args) {

        // Enhanced switch = A replacement to many else if statements
        //                   (Java14 feature)

        String day = "Friday"; // Troque por "Saturday" para ver o weekend!

        if(day.equals("Monday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Tuesday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Wednesday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Thursday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Friday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Saturday")){
            System.out.println("It is the weekend");
        }
        else if(day.equals("Sunday")){
            System.out.println("It is the weekend");
        }
        else{
            System.out.println(day + " is not a day");
        }

    }
}
```

---

## 🎯 7. O Que Vem a Seguir?

Olhe bem para esse código: são **quase 30 linhas** só para saber se é dia útil ou fim de semana!  
No próximo passo, o autor vai te ensinar a usar o **Enhanced Switch** com setinhas (`->`), agrupando todos os dias úteis em uma única linha!
