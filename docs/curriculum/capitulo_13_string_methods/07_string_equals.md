# 🧵 Capítulo 13: `String Methods` — Parte 7 (O Método `.equals()` e a Regra de Ouro das Strings)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Adicionou Aqui?

Nestas cenas, o professor fez duas coisas muito importantes:
1. **Fechou o teste anterior**: Ele mudou `name` para `"BroCode"` (sem espaços), provando que o `contains(" ")` caiu no `else` e imprimiu:  
   `Your name DOESN'T contain any spaces`.
2. **Apresentou o método mais importante de todos**: O método **`.equals()`**!

---

## 👑 2. A Regra de Ouro do Java: Por Que NUNCA Usar `==` com Strings?

Se você aprender apenas UMA coisa sobre Strings na sua vida inteira de programador, aprenda esta:

> [!IMPORTANT]
> **Em Java, NUNCA compare textos usando `==`!**  
> **SEMPRE use `.equals()`!**

### 🧠 Por que o `==` é perigoso para textos?
* Para números primitivos (`int`, `double`): o `==` compara o valor na caixinha (ex: `10 == 10` é verdadeiro).
* Para objetos (`String`): o `==` **NÃO** compara as letrinhas dentro da palavra! Ele compara o **endereço de memória** (se os dois objetos foram criados no mesmo pedaço de chip do computador).
* Isso gera bugs misteriosos onde duas palavras parecem idênticas na tela, mas o Java diz que são diferentes!

### 🛡️ A Solução Segura: `.equals()`
O método `.equals()` foi criado exatamente para resolver isso. Ele abre a palavra e compara **letra por letra** para ver se o conteúdo é exatamente igual!

---

## 🔒 3. O Exemplo Prático: Proibindo o Nome "password"

Imagine que no seu jogo ou aplicativo, você não quer que ninguém use a palavra `"password"` (senha) como nome de usuário.

```java
if (name.equals("password")) {
    System.out.println("Your name can't be password");
}
else {
    System.out.println("Hello " + name);
}
```

### 🔬 O Que o Java Faz:
* **Se `name = "password"`:**  
  `name.equals("password")` dá **`true`**!  
  O Java entra no `if` e dá a bronca:  
  `Your name can't be password` *(Seu nome não pode ser 'password')*.
* **Se `name = "Mickel"`:**  
  `name.equals("password")` dá **`false`**!  
  O Java pula o `if` e vai para o `else`:  
  `Hello Mickel`!

---

## 🔬 4. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar a numeração exata de cada linha que apareceu no editor do professor:

* **Linha 1 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de partida do Java onde o código começa a rodar.
* **Linha 5 (`String name = "password";`):**  
  Cria a caixinha de texto `name` com o valor `"password"`.
* **Linhas 7 a 15 (`//int length...` até `//name.replace()...`):**  
  Comandos antigos que já aprendemos, guardados com `//`.
* **Linhas 17 a 31 (`/* ... */`):**  
  O professor usou comentários de bloco para congelar os testes antigos do `isEmpty()` e do `contains()`.
* **Linha 33 (`if (name.equals("password")) {`):**  
  **O Teste de Igualdade:** Pergunta se o texto dentro da variável `name` é exatamente idêntico a `"password"`. Como agora `name` vale `"password"`, a resposta é **`true`** e ele entra no `if`!
* **Linha 34 (`System.out.println("Your name can't be password");`):**  
  Imprime a mensagem de aviso dizendo que o nome não pode ser "password".
* **Linha 35 (`}`):**  
  Fecha o bloco do `if`.
* **Linha 36 (`else {`):**  
  **O Senão:** Só entraria aqui se o nome fosse qualquer outra palavra diferente de "password".
* **Linha 37 (`System.out.println("Hello " + name);`):**  
  Daria as boas-vindas com o nome do usuário.
* **Linha 38 (`}`):**  
  Fecha o bloco do `else`.
* **Linhas 39 e 40 (`}` e `}`):**  
  Fecham o método `main` e a classe `Main`.

---

## 💡 5. Onde Você Usa o `.equals()` no Dia a Dia?

1. **Tela de Login:**  
   `if (userPassword.equals("123456"))` $\rightarrow$ Para saber se a senha digitada pelo usuário está correta!
2. **Escolhas do Usuário:**  
   `if (opcao.equals("sim"))` $\rightarrow$ Para saber se o jogador respondeu "sim" ou "não"!
3. **Filtros de Segurança:**  
   Para proibir palavras ofensivas ou apelidos proibidos em sistemas.

---

## 💻 6. Código Completo e Limpo para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste com `"password"` e com outros nomes:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 13: Testando o método equals()

        // TESTE 1: Digite "password" -> Vai dar a bronca: "Your name can't be password"
        // TESTE 2: Digite "Mickel"   -> Vai dar as boas-vindas: "Hello Mickel"
        String name = "password";

        if (name.equals("password")) {
            System.out.println("Your name can't be password");
        }
        else {
            System.out.println("Hello " + name);
        }
    }
}
```
