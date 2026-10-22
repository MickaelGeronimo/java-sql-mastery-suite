# 💡 Capítulo 18: Enhanced Switches (Switches Modernos) — Parte 2: A Sintaxe com Setinha (`->`), o `default` e o `Scanner`

---

## 📌 1. Visão Geral: O Que o Autor Fez Aqui?

Lembra que na aula anterior o código com `if - else if` era uma loucura de quase 30 linhas repetitivas?

Nestas 4 novas imagens (das marcas **2:52:54** até **2:55:03**), o autor apresentou a **solução moderna**:
1. **Criou o primeiro `switch` moderno com setinhas (`->`) (Imagem 1):**  
   Trocou todos os `else if` por `case "Monday" -> ...`.
2. **Sem necessidade da palavra `break`:**  
   No Java antigo, você era obrigado a escrever `break;` em cada linha. Com o Enhanced Switch do Java 14, **a setinha `->` já para e sai automaticamente**!
3. **Adicionou a rede de segurança `default ->` (Imagem 2):**  
   O `default` é o irmão gêmeo do `else`! Se alguém digitar algo que não existe (o autor testou `"Pizzaday"`), o Java vai direto para o `default` e avisa: `"Pizzaday is not a day"`.
4. **Transformou o programa em interativo com `Scanner` (Imagens 3 e 4):**  
   Agora o programa não tem mais o dia fixo no código: ele pergunta para o usuário no teclado: `"Enter the day of the week: "`.

---

## 🎯 2. Como Funciona a Sintaxe do Enhanced Switch?

Pense no `switch` como um **painel de botões de elevador**:

```java
switch(day){
    case "Monday" -> System.out.println("It is a weekday 😩");
    case "Tuesday" -> System.out.println("It is a weekday 😩");
    ...
    default -> System.out.println(day + " is not a day");
}
```

- **`switch(day)`**: Significa: *"Java, olhe para o que está escrito dentro da variável `day` e aperte o botão correspondente!"*.
- **`case "Monday" ->`**: Significa: *"Se for 'Monday', aponte para essa ação e execute-a!"*.
- **`default ->`**: Significa: *"Se não for nenhum dos botões da lista, execute esta ação padrão!"* (é o equivalente ao `else`).

---

## 🖥️ 3. O Que Sai no Console em Cada Teste? (Conforme Você Pediu)

### 🟡 Teste 1 (Imagem 2): Com `String day = "Pizzaday";` (Opção Inválida)
1. A variável `day` guarda `"Pizzaday"`.
2. O `switch` procura se existe `case "Pizzaday"`.
3. Não acha na segunda, nem terça, nem quarta... nem no domingo!
4. Então ele cai obrigatoriamente no **`default ->`**.
5. **O que sai no console:**
```text
Pizzaday is not a day
```

---

### 🟢 Teste 2 (Imagem 4): Teste Interativo com Scanner
1. O programa pergunta no console:
   ```text
   Enter the day of the week: Saturday
   ```
   *(O usuário digitou `Saturday` e apertou Enter)*.
2. O `scanner.nextLine()` guardou `"Saturday"` dentro de `day`.
3. O `switch` pulou direto para a linha 20: `case "Saturday" ->`.
4. Executou a instrução correspondente.
5. **O que sai no console:**
```text
It is the weekend 😀
```

---

## 🔍 4. Explicação Linha por Linha (Toda Linha Enumerada de 1 a 27)

Abaixo está o código interativo completo da Imagem 4:

### **Linha 1:** `import java.util.Scanner;`
- **O que faz:** Importa o leitor de teclado (na Imagem 3, apareceu vermelho antes do autor importar).

### **Linha 2:** ` ` *(em branco)*
- **O que faz:** Espaço visual.

### **Linha 3:** `public class Main {`
- **O que faz:** Abre a classe principal.

### **Linha 4:** `    public static void main(String[] args) {`
- **O que faz:** Ponto onde a execução começa.

### **Linha 5:** ` ` *(em branco)*

### **Linha 6:** `        // Enhanced switch = A replacement to many else if statements`
- **O que faz:** Comentário: *"Switch moderno = Um substituto para muitos comandos else if"*.

### **Linha 7:** `        //                   (Java14 feature)`
- **O que faz:** Comentário lembrando que foi criado no Java 14.

### **Linha 8:** ` ` *(em branco)*

### **Linha 9:** `        Scanner scanner = new Scanner(System.in);`
- **O que faz:** Cria e ativa o leitor de teclado chamado `scanner`.

### **Linha 10:** ` ` *(em branco)*

### **Linha 11:** `        System.out.print("Enter the day of the week: ");`
- **O que faz:** Pede para o usuário digitar o dia da semana (sem pular linha).

### **Linha 12:** `        String day = scanner.nextLine();`
- **O que faz:** Lê a linha inteira digitada pelo usuário e guarda na variável `day`.

### **Linha 13:** ` ` *(em branco)*

### **Linha 14:** `        switch(day){`
- **O que faz:** Abre o painel de desvios. O Java vai comparar o conteúdo de `day` com cada `case` abaixo.

### **Linha 15:** `            case "Monday" -> System.out.println("It is a weekday 😩");`
- **O que faz:** Se `day` for `"Monday"`, imprime *"It is a weekday 😩"* e sai do switch na hora.

### **Linha 16:** `            case "Tuesday" -> System.out.println("It is a weekday 😩");`
- **O que faz:** Se for `"Tuesday"`, imprime que é dia útil.

### **Linha 17:** `            case "Wednesday" -> System.out.println("It is a weekday 😩");`
- **O que faz:** Se for `"Wednesday"`, imprime que é dia útil.

### **Linha 18:** `            case "Thursday" -> System.out.println("It is a weekday 😩");`
- **O que faz:** Se for `"Thursday"`, imprime que é dia útil.

### **Linha 19:** `            case "Friday" -> System.out.println("It is a weekday 😩");`
- **O que faz:** Se for `"Friday"`, imprime que é dia útil.

### **Linha 20:** `            case "Saturday" -> System.out.println("It is the weekend 😀");`
- **O que faz:** Se for `"Saturday"`, imprime que é fim de semana!

### **Linha 21:** `            case "Sunday" -> System.out.println("It is the weekend 😀");`
- **O que faz:** Se for `"Sunday"`, imprime que é fim de semana!

### **Linha 22:** `            default -> System.out.println(day + " is not a day");`
- **O que faz:** **A rede de segurança!** Se não for nenhum dos 7 dias acima, avisa que o que foi digitado não é um dia válido.

### **Linha 23:** `        }`
- **O que faz:** Fecha o bloco do `switch`.

### **Linha 24:** ` ` *(em branco)*

### **Linha 25:** `        scanner.close();`
- **O que faz:** Fecha o leitor de teclado para devolver a memória ao sistema operacional.

### **Linha 26:** `    }`
- **O que faz:** Fecha o método `main`.

### **Linha 27:** `}`
- **O que faz:** Fecha a classe `Main`.

---

## 💻 5. Código Completo Pronto para Copiar e Testar

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Enhanced switch = A replacement to many else if statements
        //                   (Java14 feature)

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the day of the week: ");
        String day = scanner.nextLine();

        switch(day){
            case "Monday" -> System.out.println("It is a weekday 😩");
            case "Tuesday" -> System.out.println("It is a weekday 😩");
            case "Wednesday" -> System.out.println("It is a weekday 😩");
            case "Thursday" -> System.out.println("It is a weekday 😩");
            case "Friday" -> System.out.println("It is a weekday 😩");
            case "Saturday" -> System.out.println("It is the weekend 😀");
            case "Sunday" -> System.out.println("It is the weekend 😀");
            default -> System.out.println(day + " is not a day");
        }

        scanner.close();
    }
}
```

---

## 🔮 6. O Spoiler do Que Vem no Próximo Passo

Você notou que, mesmo com o switch moderno, ainda escrevemos `It is a weekday 😩` cinco vezes?  
No próximo passo, o autor vai te ensinar o **truque supremo do Enhanced Switch**: **agrupar vários casos com vírgula** em uma única linha!

```java
case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> System.out.println("It is a weekday 😩");
```
Fique de olho, isso vai deixar o código minúsculo!
