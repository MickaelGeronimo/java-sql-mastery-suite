# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 10: Padronização Profissional e o Teste do Patrick (38 Anos)
**Timestamps do vídeo:** `4:12:41 / 12:00:00`  
**Tema:** Retornando o parâmetro `birthdayboi` de volta para o nome padrão e limpo `name`, boas práticas de legibilidade em código profissional, e o teste final com `"Patrick"` e `38` anos de idade.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagens 1 e 2 (Minuto 4:12:41):**
   * Depois de ter provado que o nome não precisa ser igual usando `birthdayboi`, o professor volta o código para o padrão mais limpo, profissional e elegante:
     Ele renomeia o parâmetro de volta para **`String name`**:
     ```java
     static void happyBirthday(String name, int age){
     ```
   * E no `printf` da linha 14, ele volta a usar `name`:
     ```java
     System.out.printf("Happy Birthday dear %s!\n", name);
     ```
   * Para fechar a demonstração com chave de ouro, ele troca os dados no `main` para outro personagem:
     ```java
     String name = "Patrick";
     int age = 38;
     ```
   * O código agora está 100% simétrico, limpo, legível e pronto para ser executado!

---

## 🧠 Por que ele voltou o nome para `name` se antes funcionou com `birthdayboi`?

Essa é uma lição de **Cultura de Programação Limpa** (*Clean Code*):

1. Na aula anterior, o professor usou `birthdayboi` para te dar um choque de aprendizado e provar que o Java **não exige** nomes iguais.
2. Porém, no dia a dia de um programador em uma empresa de tecnologia, **usar o mesmo nome lógico é a melhor prática do mundo!**
   * Chamar de `name` no `main` e `name` no método torna o código óbvio e fácil de ler para qualquer pessoa que olhar o projeto.
   * Se você chamasse de `x`, `coisa`, `batata` ou `birthdayboi`, outros programadores teriam que parar e pensar: *"Espera aí, o que é birthdayboi mesmo? Ah, é o nome!"*.

> 💡 **Regra do Bom Senso:**  
> O Java não se importa se os nomes são iguais ou diferentes. Mas **você e seus colegas de equipe** se importam! Por isso, use sempre nomes claros, diretos e que expliquem o que a variável guarda (`name`, `age`, `email`, `preco`).

---

## 💻 Código Completo e Padronizado (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        String name = "Patrick";
        int age = 38;

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
* **O que faz:** Método inicial onde a execução do programa começa.  
* **Console:** **Nada**.

---

### **Linhas 3 a 6:** *(Linhas em branco e comentário do professor)*

---

### **Linha 7:** `String name = "Patrick";`
* **O que faz:** Cria a variável `name` com o texto `"Patrick"`.  
* **Console:** **Nada**.

---

### **Linha 8:** `int age = 38;`
* **O que faz:** Cria a variável `age` com o número `38`.  
* **Console:** **Nada**.

---

### **Linha 9:** *(Linha em branco)*

---

### **Linha 10:** `happyBirthday(name, age);`
* **O que faz:** **CHAMADA DO MÉTODO.**  
  * Passa `"Patrick"` (1º argumento) e `38` (2º argumento) para o método `happyBirthday`.  
  * Pausa o `main` e salta para a Linha 12.  
* **Console:** Executa os versos do método.

---

### **Linha 11:** `}`
* **O que faz:** Fecha o método `main`.  
* **Console:** Finaliza com `Process finished with exit code 0`.

---

### **Linha 12:** `static void happyBirthday(String name, int age){`
* **O que faz:** **DECLARAÇÃO DO MÉTODO PADRONIZADO.**  
  * Recebe `"Patrick"` dentro do parâmetro `name`.  
  * Recebe `38` dentro do parâmetro `age`.  
  * O aviso cinza `1 usage` confirma que ele foi chamado 1 vez no código.  
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
* **O que faz:** Imprime o 2º verso trocando `%s` por `"Patrick"` e pulando linha com `\n`.  
* **Console:** Imprime:
  ```text
  Happy Birthday dear Patrick!
  ```

---

### **Linha 15:** `System.out.printf("You are %d years old!\n", age);`
* **O que faz:** Imprime o 3º verso trocando `%d` por `38` e pulando linha com `\n`.  
* **Console:** Imprime:
  ```text
  You are 38 years old!
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
* **O que faz:** Fecha o método `happyBirthday` e devolve o fluxo para o `main`.  
* **Console:** **Nada**.

---

### **Linha 18:** `}`
* **O que faz:** Fecha a classe `Main`.  
* **Console:** **Nada**.

---

## 📺 O que sai no Console:

```text
Happy Birthday to you!
Happy Birthday dear Patrick!
You are 38 years old!
Happy Birthday to you!

Process finished with exit code 0
```
