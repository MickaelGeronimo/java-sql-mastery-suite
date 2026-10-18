# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 3: O Erro de Escopo (`cannot find symbol: variable name`)
**Timestamps do vídeo:** `4:09:03 / 12:00:00`  
**Tema:** Tentando personalizar a música com variáveis (`name` e `age`), o erro de compilação em vermelho `cannot find symbol: variable name`, o que é Escopo de Variáveis (*Variable Scope*), e por que um método não enxerga o que está dentro do outro.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem (Minuto 4:09:03):**
   * O professor quer dar um passo além: em vez de cantar um parabéns genérico (*"Happy Birthday dear you!"*), ele quer cantar o nome da pessoa (*"Happy Birthday dear Bro!"*).
   * Dentro do `main`, ele cria duas variáveis:
     ```java
     String name = "Bro";
     int age = 25;
     ```
   * Em seguida, dentro do método `happyBirthday()`, na linha 16, ele tenta usar a variável `name` usando `printf`:
     ```java
     System.out.printf("Happy Birthday dear %s!", name);
     ```
   * **O desastre acontece:** A palavra `name` fica sublinhada de vermelho no editor!
   * Ao tentar rodar o código, o Java recusa a execução e exibe o seguinte erro em vermelho no painel de compilação (`Build Output`):
     ```text
     C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:16:54
     java: cannot find symbol
       symbol:   variable name
       location: class Main
     ```
     *(Tradução: Erro! Não consigo encontrar o símbolo: variável name. Local: classe Main).*

---

## 🧠 Por que deu esse erro em vermelho? (A Metáfora das Duas Casas)

Este é outro erro que o professor comete **de propósito** para ensinar uma das regras mais sagradas da programação: **O ESCOPO DE VARIÁVEIS** (*Variable Scope*).

### A Metáfora da Geladeira do Vizinho:
Imagine que você e o seu vizinho moram em duas casas completamente separadas pela cerca:
* A casa 1 se chama **`main`**.
* A casa 2 se chama **`happyBirthday`**.

Você (`main`) vai ao supermercado, compra uma barra de chocolate deliciosa chamada `name` e guarda dentro da **sua** geladeira.  
Aí o seu vizinho (`happyBirthday`), lá na casa dele, abre a geladeira **dele** procurando o chocolate `name`.  
O chocolate está na geladeira dele? **CLARO QUE NÃO!** O chocolate está trancado na sua casa!

O vizinho então grita: *"Eu não consigo achar esse chocolate!"* $\rightarrow$ É exatamente o que o Java faz:
> **`cannot find symbol: variable name`**  
> *(Não consigo encontrar nenhuma variável chamada name aqui dentro de mim!)*

---

## 🔒 A Regra do Escopo: "O que acontece nas chaves, fica nas chaves"

Observe com atenção as chaves `{ ... }`:

```java
public static void main(String[] args){
    String name = "Bro"; // 👈 'name' NASCEU AQUI DENTRO!
    int age = 25;        // 👈 'age' NASCEU AQUI DENTRO!

    happyBirthday();
} // 👈 'name' e 'age' SÓ EXISTEM ATÉ ESTA CHAVE FECHAR!

static void happyBirthday(){
    // ❌ AQUI DENTRO É OUTRO MUNDO! 
    // O método happyBirthday não faz a menor ideia de que 'name' existe!
    System.out.printf("Happy Birthday dear %s!", name); 
}
```

* Variáveis criadas dentro de um método são chamadas de **Variáveis Locais**.
* Elas são **invisíveis** para qualquer outro método do lado de fora!
* O método `happyBirthday` não tem permissão para espiar o que o `main` tem guardado.

---

## 💡 Como resolvemos isso? (A Solução que vem a seguir)

Se você quer que o seu vizinho coma do seu chocolate, o que você tem que fazer?  
**Você tem que levar o chocolate até ele e entregar em mãos!**

Em programação, entregar uma variável de um método para outro se chama:  
👉 **Passar Parâmetros e Argumentos (`Parameters & Arguments`)**!

Na próxima etapa, o professor vai mostrar que:
1. O `main` vai enviar o nome pelos parênteses: `happyBirthday(name);`
2. O `happyBirthday` vai estender a mão para receber: `static void happyBirthday(String name)`

---

## 💻 Código com Erro (Como está na Imagem do Professor)

```java
public class Main {
    public static void main(String[] args){

        String name = "Bro";
        int age = 25;

        happyBirthday();
        happyBirthday();
        happyBirthday();
    }

    static void happyBirthday(){
        System.out.println("Happy Birthday to you!");
        System.out.printf("Happy Birthday dear %s!", name); // ❌ ERRO AQUI!
        System.out.println("You are x years old!");
        System.out.println("Happy Birthday to you!\n");
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

* **Linha 1:** `public class Main {` -> Classe principal. *(Console: Nada)*
* **Linha 2:** `public static void main(String[] args){` -> Ponto de início. *(Console: Nada)*
* **Linha 3:** *(Linha em branco)*
* **Linha 4:** `String name = "Bro";`  
  * Cria a variável de texto `name` com o valor `"Bro"`. **Ela só existe dentro do `main`**.  
  * **Console:** Nada.
* **Linha 5:** `int age = 25;`  
  * Cria a variável inteira `age` valendo 25. **Ela só existe dentro do `main`**.  
  * **Console:** Nada.
* **Linha 6:** *(Linha em branco)*
* **Linha 7 a 9:** Chamadas do método `happyBirthday();`. *(Console: Nada)*
* **Linha 10:** `}` -> Fecha o `main`. Aqui as variáveis `name` e `age` são destruídas da memória.
* **Linha 11:** *(Linha em branco)*
* **Linha 12:** `static void happyBirthday(){` -> Declaração do método isolado em seu próprio mundo.
* **Linha 13:** `System.out.println("Happy Birthday to you!");` -> Imprimiria o 1º verso.
* **Linha 14:** `System.out.printf("Happy Birthday dear %s!", name);`  
  * 🛑 **LINHA DO ERRO!** O Java procura a caixinha `name` dentro de `happyBirthday`. Como não encontra nada, aborta o programa antes mesmo de começar!
* **Linha 15:** `System.out.println("You are x years old!");`
* **Linha 16:** `System.out.println("Happy Birthday to you!\n");`
* **Linha 17:** `}` -> Fecha o método.
* **Linha 18:** `}` -> Fecha a classe.

---

## 📺 O que sai no Console (A Mensagem de Erro do Java):

```text
C:\Users\HP\IdeaProjects\MyFirstProject\src\Main.java:16:54
java: cannot find symbol
  symbol:   variable name
  location: class Main
```

### O que essa mensagem quer dizer em português simples?
* `cannot find symbol`: "Não consegui encontrar esse símbolo/nome".
* `symbol: variable name`: "O símbolo que não encontrei é a variável chamada `name`".
* `location: class Main`: "Procurei por todo canto e ela não está disponível no local onde você mandou usar".
