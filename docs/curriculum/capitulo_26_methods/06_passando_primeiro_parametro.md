# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 6: A Conexão Perfeita! Passando o Primeiro Parâmetro (`String name`)
**Timestamps do vídeo:** `4:10:35 / 12:00:00`  
**Tema:** Consertando o erro anterior: declarando o parâmetro `String name`, o fluxo do argumento até o parâmetro, o uso de `%s` com quebra de linha `\n` no `printf`, e a vitória no console com "Happy Birthday dear Bro!".

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem (Minuto 4:10:35):**
   * O professor finalmente conserta o erro que vimos na aula anterior!
   * Na linha 12, ele abre a caixa de correio do método adicionando **`(String name)`**:
     ```java
     static void happyBirthday(String name){
     ```
   * Na linha 14, ele ajusta a linha do `printf` adicionando o `\n` para pular linha:
     ```java
     System.out.printf("Happy Birthday dear %s!\n", name);
     ```
   * **O resultado no console é um sucesso absoluto:** Todo o texto em verde rodou perfeitamente:
     ```text
     Happy Birthday to you!
     Happy Birthday dear Bro!
     You are x years old!
     Happy Birthday to you!

     Process finished with exit code 0
     ```
   * O nome `"Bro"` foi encaixado perfeitamente no lugar do `%s`!

---

## 🧠 Como a Ponte Funcionou? (O Caminho da Informação)

Veja o filme do que aconteceu na memória do computador passo a passo:

```
1. No método main:
   String name = "Bro"; 

2. Na chamada do método:
   happyBirthday(name);  --------> [ENVIANDO O ARGUMENTO "Bro"]
                                               |
                                               v
3. Na declaração do método:
   static void happyBirthday(String name) <---- [RECEBENDO NO PARÂMETRO 'name']

4. Dentro do método:
   System.out.printf("Happy Birthday dear %s!\n", name);
                                          ^       |
                                          |_______| (Encaixa "Bro" aqui!)
```

* O `main` colocou `"Bro"` dentro do envelope.
* O Java viajou até o método `happyBirthday`.
* Como o método agora tem a caixinha `String name` nos parênteses, ele **recebeu o envelope com sucesso**!
* Agora, a variável `name` existe de verdade dentro do método `happyBirthday` e vale `"Bro"`!

---

## 🔍 O Detalhe do `\n` no `printf`

Repare na linha 14:
```java
System.out.printf("Happy Birthday dear %s!\n", name);
```
* **O `%s`:** É o marcador de texto (*string placeholder*). Ele é substituído pelo valor da variável `name` (`"Bro"`).
* **O `\n`:** É fundamental aqui! Diferente do `println`, o comando `printf` **NÃO pula linha sozinho**! Se o professor não tivesse colocado o `\n`, a música sairia toda colada assim:
  `Happy Birthday dear Bro!You are x years old!` (feio e tudo grudado).
  O `\n` garante que o cursor dê um "Enter" e desça para a próxima linha!

---

## 💻 Código Completo e Corrigido (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        String name = "Bro";
        int age = 25;

        happyBirthday(name);
    }

    static void happyBirthday(String name){
        System.out.println("Happy Birthday to you!");
        System.out.printf("Happy Birthday dear %s!\n", name);
        System.out.println("You are x years old!");
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
* **O que faz:** Método inicial onde o Java começa a rodar.  
* **Console:** **Nada**.

---

### **Linhas 3 a 5:** *(Linhas em branco e comentário)*

---

### **Linha 6:** *(Linha em branco)*

---

### **Linha 7:** `String name = "Bro";`
* **O que faz:** Cria a variável `name` no `main` contendo o texto `"Bro"`.  
* **Console:** **Nada**.

---

### **Linha 8:** `int age = 25;`
* **O que faz:** Cria a variável `age` no `main` contendo o número 25. *(Ainda não usamos nesta etapa, mas usaremos na próxima!)*.  
* **Console:** **Nada**.

---

### **Linha 9:** *(Linha em branco)*

---

### **Linha 10:** `happyBirthday(name);`
* **O que faz:** **CHAMADA COM ARGUMENTO.**
  * O Java pega o valor guardado em `name` (`"Bro"`) e passa para o método `happyBirthday`.
  * Pausa o `main` e salta para a Linha 12.  
* **Console:** Executa as impressões do método.

---

### **Linha 11:** `}`
* **O que faz:** Fecha o método `main`.  
* **Console:** Mostra `Process finished with exit code 0`.

---

### **Linha 12:** `static void happyBirthday(String name){`
* **O que faz:** **DECLARAÇÃO COM PARÂMETRO.**
  * `String name`: Cria a gaveta receptora do tipo texto. Ela recebe o valor `"Bro"` enviado pelo `main`.
  * O texto cinza `1 usage` acima da linha avisa que o método está sendo chamado 1 vez.  
* **Console:** **Nada nessa linha diretamente**.

---

### **Linha 13:** `System.out.println("Happy Birthday to you!");`
* **O que faz:** Imprime o 1º verso da música e desce para a próxima linha.  
* **Console:** Imprime:
  ```text
  Happy Birthday to you!
  ```

---

### **Linha 14:** `System.out.printf("Happy Birthday dear %s!\n", name);`
* **O que faz:** Imprime o 2º verso formatado, trocando `%s` por `"Bro"` e pulando linha com `\n`.  
* **Console:** Imprime:
  ```text
  Happy Birthday dear Bro!
  ```

---

### **Linha 15:** `System.out.println("You are x years old!");`
* **O que faz:** Imprime o 3º verso (ainda com o `"x"` genérico).  
* **Console:** Imprime:
  ```text
  You are x years old!
  ```

---

### **Linha 16:** `System.out.println("Happy Birthday to you!\n");`
* **O que faz:** Imprime o 4º verso e dá uma quebra de linha extra.  
* **Console:** Imprime:
  ```text
  Happy Birthday to you!
  ```
  *(e desce duas linhas)*.

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
Happy Birthday dear Bro!
You are x years old!
Happy Birthday to you!

Process finished with exit code 0
```

---

## 🔮 O que vem a seguir?
Repare na Linha 15 do console: ele cantou `"You are x years old!"` (Você tem x anos de idade!).  
A variável `int age = 25;` já está pronta esperando no `main`! No próximo print, o professor vai ensinar a passar **dois parâmetros ao mesmo tempo** (`name` e `age`) para substituir aquele `"x"` pelo número 25!

Assim que tirar os próximos prints, mande aqui para vermos essa evolução!
