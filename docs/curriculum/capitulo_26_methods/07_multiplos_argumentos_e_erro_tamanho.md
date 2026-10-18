# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 7: O Teste do Bob Esponja e o Erro dos Dois Argumentos (`name, age`)
**Timestamps do vídeo:** `4:10:45` até `4:11:10 / 12:00:00`  
**Tema:** A flexibilidade do método cantando para o `"Spongebob"`, a tentativa de enviar dois dados ao mesmo tempo (`name, age`), o erro `required: java.lang.String, found: java.lang.String,int`, e a regra da correspondência de parâmetros.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto ~4:10:50):**
   * O professor quer provar que o método não serve apenas para o `"Bro"`. Ele serve para **qualquer pessoa do mundo**!
   * No `main`, ele altera a variável para:
     ```java
     String name = "Spongebob";
     ```
   * Ao rodar, o console canta automaticamente para o Bob Esponja:
     ```text
     Happy Birthday to you!
     Happy Birthday dear Spongebob!
     You are x years old!
     Happy Birthday to you!
     ```
   * O método não precisou ser alterado em nada! Ele simplesmente pegou o novo nome e cantou perfeitamente.
2. **Imagem 2 (Minuto 4:11:10):**
   * Agora o professor quer resolver o verso que ainda diz `"You are x years old!"`. Ele quer colocar a idade real (25 anos)!
   * Na linha 10, ele passa **dois argumentos separados por vírgula**:
     ```java
     happyBirthday(name, age);
     ```
   * **Mas veja a pegadinha:** Lá na linha 12, o método ainda está esperando **apenas uma coisa**:
     ```java
     static void happyBirthday(String name){
     ```
   * A palavra `age` fica sublinhada de vermelho!
   * Ao clicar no Play, o Java explode com mais um erro clássico em vermelho:
     ```text
     C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:10:9
     java: method happyBirthday in class Main cannot be applied to given types;
       required: java.lang.String
       found:    java.lang.String,int
       reason: actual and formal argument lists differ in length
     ```

---

## 🧠 Por que deu esse erro agora? (A Metáfora dos Dois Pacotes e Uma Mão Só)

Imagine a seguinte cena:

O entregador chega na sua porta trazendo **DOIS pacotes**:
1. Uma caixa de pizza (`name = "Spongebob"`).
2. Uma garrafa de refrigerante de 2 litros (`age = 25`).

Você abre a porta, mas estende **APENAS UMA MÃO** para pegar: `(String name)`.  
O entregador (o Java) fica indignado e diz:
> *"Ei! Você está tentando me forçar a te entregar 2 coisas (`found: java.lang.String,int`), mas você só estendeu a mão para pegar 1 coisa (`required: java.lang.String`)! Onde eu vou enfiar esse refrigerante?!"*

Isso é exatamente o que a mensagem em inglês está dizendo:
* **`required: java.lang.String`**: O método foi programado para receber **apenas 1 texto**.
* **`found: java.lang.String,int`**: Mas o `main` tentou enviar **2 coisas** (um texto e um número inteiro)!
* **`differ in length`**: A quantidade de coisas não bate! (Você mandou 2, mas ele só aceita 1).

---

## 📜 A Regra Universal para Passar Múltiplos Dados

No Java, você pode passar quantas informações quiser para um método: 2, 3, 5 ou 10!  
Mas você tem que seguir **duas regras inquebráveis**:

1. **A Regra da Quantidade:** Se você envia 2 argumentos no `main`, o método tem que ter 2 parâmetros para receber!
2. **A Regra da Ordem e dos Tipos:** Os tipos têm que bater na mesma sequência!
   * Se o 1º for texto (`String`), o 1º parâmetro tem que ser `String`.
   * Se o 2º for número (`int`), o 2º parâmetro tem que ser `int`.
   * Eles são separados por **vírgula (`,`)**!

---

## 💻 Código do Erro (Como está na Imagem 2 do Professor)

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        String name = "Spongebob";
        int age = 25;

        happyBirthday(name, age); // ❌ Enviando 2 coisas (String e int)
    }

    static void happyBirthday(String name){ // ❌ Só tem caixinha para 1 coisa (String)!
        System.out.println("Happy Birthday to you!");
        System.out.printf("Happy Birthday dear %s!\n", name);
        System.out.println("You are x years old!");
        System.out.println("Happy Birthday to you!\n");
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

* **Linha 1:** `public class Main {` -> Classe principal. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` -> Início da execução. *(Console: Nada)*
* **Linha 3 a 6:** Comentários e linhas em branco. *(Console: Nada)*
* **Linha 7:** `String name = "Spongebob";`  
  * Cria a variável `name` com o texto `"Spongebob"`.  
  * **Console:** Nada.
* **Linha 8:** `int age = 25;`  
  * Cria a variável `age` com o número 25.  
  * **Console:** Nada.
* **Linha 9:** *(Linha em branco)*
* **Linha 10:** `happyBirthday(name, age);`  
  * 🛑 **A LINHA DO ERRO!** O `main` tenta despachar duas variáveis (`name` e `age`).  
  * **Console (Build Output):** O compilador trava e avisa que a quantidade de argumentos enviados não bate com o método.
* **Linha 11:** `}` -> Fecha o `main`.
* **Linha 12:** `static void happyBirthday(String name){`  
  * 🛑 **A CAUSA DO PROBLEMA!** O método só tem `(String name)` nos parênteses. Falta colocar a vírgula e a segunda caixinha para a idade!
* **Linha 13 a 17:** Versos da música que nem chegam a rodar.
* **Linha 18:** `}` -> Fecha o método.
* **Linha 19:** `}` -> Fecha a classe.

---

## 📺 O que sai no Console (A Mensagem de Erro de Compilação):

```text
C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:10:9
java: method happyBirthday in class Main cannot be applied to given types;
  required: java.lang.String
  found:    java.lang.String,int
  reason: actual and formal argument lists differ in length
```

---

## 💡 Como vamos consertar isso no próximo segundo do vídeo?

Basta abrir a segunda mão na Linha 12!  
Colocamos uma vírgula e adicionamos **`int age`**:
```java
static void happyBirthday(String name, int age){
```
E na Linha 15, trocamos o `"You are x years old!"` por:
```java
System.out.printf("You are %d years old!\n", age);
```
Pronto! Agora o método receberá o nome E a idade, e a música ficará 100% personalizada e perfeita!
