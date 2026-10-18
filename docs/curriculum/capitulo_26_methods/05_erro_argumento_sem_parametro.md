# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 5: O Erro de Argumento sem Parâmetro (`cannot be applied to given types`)
**Timestamps do vídeo:** `4:09:47 / 12:00:00`  
**Tema:** Tentando passar um argumento (`name`), o novo erro de compilação `required: no arguments, found: java.lang.String`, a diferença vital entre **Argumento** (quem envia) e **Parâmetro** (quem recebe), e a metáfora do carteiro com a caixa de correio lacrada.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem (Minuto 4:09:47):**
   * O professor pensa: *"Bom, já que o método não consegue espiar a variável `name`, vamos mandar o `name` pelos parênteses!"*.
   * Na linha 10, ele altera a chamada para:
     ```java
     happyBirthday(name);
     ```
   * **Mas repare no que ele NÃO fez:** Ele **esqueceu** de alterar a declaração do método lá embaixo na linha 12, que ainda está com os parênteses vazios:
     ```java
     static void happyBirthday(){
     ```
   * No editor do IntelliJ, aparecem duas cobrinhas vermelhas:
     1. Embaixo de `happyBirthday(name)` na linha 10.
     2. Embaixo de `name` na linha 14.
   * Ao clicar no Play, o Java cospe um erro vermelho novinho em folha no console:
     ```text
     C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:10:9
     java: method happyBirthday in class Main cannot be applied to given types;
       required: no arguments
       found:    java.lang.String
       reason: actual and formal argument lists differ in length
     ```

---

## 🧠 Por que deu esse erro agora? (A Metáfora da Caixa de Correio Lacrada)

Imagine a seguinte situação da vida real:

Você vai até a casa do seu vizinho levar uma encomenda (o pacote `name`).  
Você chega na cerca dele pronto para colocar a encomenda na caixa de correio.  
Mas quando você olha para a casa dele: **a caixa de correio está lacrada com fita adesiva, ou nem existe caixa de correio!**

Você fica ali parado com o pacote na mão sem ter onde colocar. O carteiro (o Java) fica indignado e grita:
> *"Ei! Você está tentando me entregar um pacote (`found: java.lang.String`), mas o dono da casa disse que não aceita encomenda nenhuma (`required: no arguments`)!"*

---

## ⚖️ Argumento vs. Parâmetro (A Diferença que Todo Programador Precisa Saber)

Esta é uma das perguntas mais cobradas em entrevistas de emprego e provas de faculdade:

| Nome | Onde ele fica? | O que ele é? | Analogia |
| :--- | :--- | :--- | :--- |
| **ARGUMENTO** (*Argument*) | Fica na **chamada** do método: `happyBirthday(name);` | É o dado **real** que você está enviando de fato. | É o **pacote/encomenda** que sai da sua mão. |
| **PARÂMETRO** (*Parameter*) | Fica na **declaração** do método: `static void happyBirthday(String name)` | É a **gaveta/caixinha** que o método cria para segurar o pacote. | É a **caixa de correio** que recebe a encomenda. |

> 🧠 **A Regra de Ouro da Conexão:**  
> **Os dois lados precisam concordar!**  
> Se quem envia manda **1 texto (`String`)**, quem recebe é **OBRIGADO** a ter uma caixinha preparada para receber **1 texto (`String`)**!

---

## 🔍 Traduzindo o Erro do Java para o Português Claro

Veja como a mensagem de erro do Java é super explicativa quando você aprende a ler:

```text
java: method happyBirthday in class Main cannot be applied to given types;
  required: no arguments
  found:    java.lang.String
  reason: actual and formal argument lists differ in length
```

* **`cannot be applied to given types`**: "Este método não pode ser usado com o que você me passou".
* **`required: no arguments`**: "O método `happyBirthday()` foi criado com parênteses vazios, então ele exigia **zero argumentos**".
* **`found: java.lang.String`**: "Mas você me passou **1 argumento do tipo String** (`name`)".
* **`reason: actual and formal argument lists differ in length`**: "Motivo: o tamanho das listas não bate (você mandou 1 coisa para uma função configurada para receber 0 coisas)".

---

## 💻 Código com o Erro (Como está na Imagem do Professor)

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        String name = "Bro";
        int age = 25;

        happyBirthday(name); // ❌ Enviando 'name', mas o método não tem onde receber!
    }

    static void happyBirthday(){ // ❌ Parênteses vazios! Não aceita nada!
        System.out.println("Happy Birthday to you!");
        System.out.printf("Happy Birthday dear %s!", name);
        System.out.println("You are x years old!");
        System.out.println("Happy Birthday to you!\n");
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

* **Linha 1:** `public class Main {` -> Classe principal. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` -> Método inicial. *(Console: Nada)*
* **Linha 3 a 6:** Comentários e linhas vazias. *(Console: Nada)*
* **Linha 7:** `String name = "Bro";` -> Cria a variável `name` com o texto `"Bro"`. *(Console: Nada)*
* **Linha 8:** `int age = 25;` -> Cria a variável `age` com o valor `25`. *(Console: Nada)*
* **Linha 9:** *(Linha em branco)*
* **Linha 10:** `happyBirthday(name);`  
  * 🛑 **A LINHA DO NOVO ERRO!** Você colocou `name` como argumento nos parênteses para enviar. O Java tenta entregar esse texto para o método `happyBirthday`, mas o método recusa!  
  * **Console (Build Output):** Exibe o erro de que o método exigia 0 argumentos, mas encontrou 1 String.
* **Linha 11:** `}` -> Fecha o `main`.
* **Linha 12:** `static void happyBirthday(){`  
  * 🛑 **A RAIZ DO PROBLEMA!** Os parênteses continuam vazios `()`. O método não declarou nenhum parâmetro para receber o `name`.
* **Linha 13 a 17:** As mensagens da música que não chegam a ser executadas.
* **Linha 18:** `}` -> Fecha o método.
* **Linha 19:** `}` -> Fecha a classe.

---

## 💡 Como vamos consertar isso no próximo segundo do vídeo?

Basta abrir a caixa de correio lá na linha 12!  
Em vez de deixar `()`, dizemos ao método o que ele vai receber:
```java
static void happyBirthday(String name){
```
Pronto! Agora o `main` envia um `String` e o `happyBirthday` recebe um `String`! A ponte estará conectada com sucesso!
