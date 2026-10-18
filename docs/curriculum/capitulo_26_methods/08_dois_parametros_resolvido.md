# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 8: Dois Parâmetros Resolvidos! (`String name, int age`) e o `%d`
**Timestamps do vídeo:** `4:11:48` até `4:11:55 / 12:00:00`  
**Tema:** Passando e recebendo múltiplos parâmetros simultaneamente (`String name, int age`), a regra da ordem posicional dos argumentos, o formatador `%d` para números inteiros no `printf`, e a personalização total da música de aniversário.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 4:11:48):**
   * O professor conserta a declaração do método na linha 12:
     Ele adiciona a segunda caixinha de correio separada por vírgula: **`int age`**:
     ```java
     static void happyBirthday(String name, int age){
     ```
   * No `main`, ele altera a idade de 25 para `30`:
     ```java
     int age = 30;
     ```
   * Na linha 15, ele substitui a frase genérica pelo `printf` formatado com **`%d`**:
     ```java
     System.out.printf("You are %d years old!\n", age);
     ```
2. **Imagem 2 (Minuto 4:11:55):**
   * O professor clica em Play para executar.
   * O console roda limpo, sem nenhum erro vermelho, e imprime a música 100% personalizada:
     ```text
     Happy Birthday to you!
     Happy Birthday dear Spongebob!
     You are 30 years old!
     Happy Birthday to you!

     Process finished with exit code 0
     ```
   * O nome `"Spongebob"` entrou no lugar do `%s`, e o número `30` entrou no lugar do `%d`!

---

## 🧠 Como a Ponte Dupla Funciona na Memória?

Veja o caminho dos dois pacotes sendo entregues ao mesmo tempo:

```
        MÉTODO MAIN                              MÉTODO HAPPYBIRTHDAY
   (Quem envia os pacotes)                     (Quem recebe os pacotes)

1º Argumento: "Spongebob" (String) --------> 1º Parâmetro: String name
2º Argumento: 30 (int) --------------------> 2º Parâmetro: int age

Chamada: happyBirthday(name, age);  =======> Definição: (String name, int age)
```

### ⚠️ A Regra Sagrada da Ordem (Posição Importa!):
O Java não adivinha o que você quer fazer pelos nomes das variáveis. Ele olha para a **ORDEM DAS POSIÇÕES**:
* O que estiver na **1ª posição** vai cair na **1ª caixinha**.
* O que estiver na **2ª posição** vai cair na **2ª caixinha**.

Se você invertesse e escrevesse:
`happyBirthday(age, name);` // ❌ ERRO!
O Java tentaria enfiar o número 30 dentro de `String name` e o texto `"Spongebob"` dentro de `int age`, gerando erro de incompatibilidade de tipos!

---

## 🔤 A Diferença Entre `%s` e `%d` no `printf`

Repare como o professor usou dois marcadores diferentes:

| Marcador | Significa | Para que tipo de dado serve? | Exemplo no código |
| :---: | :---: | :---: | :---: |
| **`%s`** | **String** | Textos e palavras | `Happy Birthday dear %s!\n` $\rightarrow$ vira `"Spongebob"` |
| **`%d`** | **Decimal Integer** | Números inteiros (`int`, `long`, `byte`, `short`) | `You are %d years old!\n` $\rightarrow$ vira `30` |

Ambos terminam com `\n` para forçar o cursor a descer para a próxima linha!

---

## 💻 Código Completo e Corrigido (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        String name = "Spongebob";
        int age = 30;

        happyBirthday(name, age);
    }

    static void happyBirthday(String name, int age){
        System.out.println("Happy Birthday to you!");
        System.out.printf("Happy Birthday dear %s!\n", name);
        System.out.printf("You are %d years old!\n", age);
        System.out.println("Happy Birthday to you!\n");
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Cria a classe principal `Main`.  
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Método onde a execução do programa começa.  
* **Console:** **Nada**.

---

### **Linhas 3 a 6:** *(Linhas em branco e comentário)*

---

### **Linha 7:** `String name = "Spongebob";`
* **O que faz:** Cria a variável `name` guardando `"Spongebob"`.  
* **Console:** **Nada**.

---

### **Linha 8:** `int age = 30;`
* **O que faz:** Cria a variável `age` guardando o número `30`.  
* **Console:** **Nada**.

---

### **Linha 9:** *(Linha em branco)*

---

### **Linha 10:** `happyBirthday(name, age);`
* **O que faz:** **CHAMADA COM DOIS ARGUMENTOS.**
  * Envia o texto `"Spongebob"` e o número `30` para o método.
  * Pausa o `main` e salta para a Linha 12.  
* **Console:** Executa os versos do método.

---

### **Linha 11:** `}`
* **O que faz:** Fecha o método `main`.  
* **Console:** Finaliza com `Process finished with exit code 0`.

---

### **Linha 12:** `static void happyBirthday(String name, int age){`
* **O que faz:** **DECLARAÇÃO COM DOIS PARÂMETROS.**
  * A 1ª gaveta (`String name`) recebe `"Spongebob"`.
  * A 2ª gaveta (`int age`) recebe `30`.
  * O texto cinza `1 usage` indica que o método foi chamado 1 vez.  
* **Console:** **Nada nessa linha diretamente**.

---

### **Linha 13:** `System.out.println("Happy Birthday to you!");`
* **O que faz:** Imprime o 1º verso.  
* **Console:** Imprime:
  ```text
  Happy Birthday to you!
  ```

---

### **Linha 14:** `System.out.printf("Happy Birthday dear %s!\n", name);`
* **O que faz:** Imprime o 2º verso, substituindo `%s` por `"Spongebob"`, e pula linha com `\n`.  
* **Console:** Imprime:
  ```text
  Happy Birthday dear Spongebob!
  ```

---

### **Linha 15:** `System.out.printf("You are %d years old!\n", age);`
* **O que faz:** Imprime o 3º verso, substituindo `%d` por `30`, e pula linha com `\n`.  
* **Console:** Imprime:
  ```text
  You are 30 years old!
  ```

---

### **Linha 16:** `System.out.println("Happy Birthday to you!\n");`
* **O que faz:** Imprime o 4º verso e dá um pulo de linha extra.  
* **Console:** Imprime:
  ```text
  Happy Birthday to you!
  ```
  *(e desce 2 linhas)*.

---

### **Linha 17:** `}`
* **O que faz:** Fecha o método `happyBirthday` e devolve o Java para o `main`.  
* **Console:** **Nada**.

---

### **Linha 18:** `}`
* **O que faz:** Fecha a classe `Main`.  
* **Console:** **Nada**.

---

## 📺 O que sai no Console:

```text
Happy Birthday to you!
Happy Birthday dear Spongebob!
You are 30 years old!
Happy Birthday to you!

Process finished with exit code 0
```

---

## 🚀 O Poder Mágico dos Métodos Parametrizados:

Olhe que coisa incrível: se você quiser cantar parabéns para o **Patrick** de 28 anos, você só precisa chamar:
```java
happyBirthday("Patrick", 28);
```
E se quiser cantar para o **Lula Molusco** de 45 anos:
```java
happyBirthday("Squidward", 45);
```

Você **NUNCA MAIS** precisa reescrever as 4 linhas da música! Você só chama o método passando o nome e a idade de quem você quiser! Isso é programação profissional no mais alto nível!
