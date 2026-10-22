# 🧵 Capítulo 14: `substring()` — Parte 5 (O Grande Escudo de Segurança: `if (email.contains("@"))`)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Problema que Podia Destruir o Nosso Programa!

Imagine que você colocou o programa da aula passada no ar e um usuário distraído digita o e-mail dele sem o arroba:
```text
Bro123gmail.com  (esqueceu de apertar Shift + 2 para fazer o @!)
```

### 💣 O Que Aconteceria nos Bastidores sem o `if`?
1. O Java executaria: `email.indexOf("@")`.
2. Como o `@` **não existe** no texto, o Java devolveria o número **`-1`**!
3. Em seguida, o Java tentaria fazer o corte:
   ```java
   email.substring(0, -1); // 🚨 ERRO FATAL!
   ```
4. O Java não aceita fatiar gavetas negativas! O programa travaria na hora com uma mensagem vermelha gigante no console:  
   `StringIndexOutOfBoundsException: String index out of range: -1`!

Para impedir que o programa quebre na mão do usuário, o professor adicionou um **escudo de segurança com `if / else`**!

---

## 🛡️ 2. O Escudo de Segurança: `if (email.contains("@"))`

O professor usou o método farejador que aprendemos no Capítulo 13: **`.contains("@")`**!

```java
if (email.contains("@")) {
    // Só corta o e-mail se o @ realmente existir!
    username = email.substring(0, email.indexOf("@"));
    domain = email.substring(email.indexOf("@") + 1);

    System.out.println(username);
    System.out.println(domain);
}
else {
    // Se o usuário esqueceu o @, avisa educadamente:
    System.out.println("Emails must contain @");
}
```

---

## 🌳 3. A Árvore de Decisão Visual

```text
               [ Usuário digita o e-mail ]
                           │
             Pergunta: `email.contains("@")`?
                           │
            ┌──────────────┴──────────────┐
        SIM (true)                    NÃO (false)
            │                             │
            ▼                             ▼
   Corta com segurança!            Bloqueia com aviso!
   Mostra `username`              "Emails must contain @"
   Mostra `domain`               (Zero erros vermelhos!)
```

---

## 🧪 4. Os 2 Testes Reais Executados no Console

### ❌ Teste 1: O Usuário Esqueceu o `@`
* **Digitou:** `Bro123gmail.com`
* **O que o Java fez:** `email.contains("@")` deu **`false`**.
* **Resultado:** Pulou o corte, caiu no `else` e mostrou com elegância:
  ```text
  Emails must contain @
  ```

---

### ✅ Teste 2: Um E-mail Válido Qualquer (Yahoo!)
* **Digitou:** `fakeguy1@yahoo.com`
* **O que o Java fez:** `email.contains("@")` deu **`true`**.
* **Resultado:** Entrou no `if` e fatiou perfeitamente:
  ```text
  fakeguy1
  yahoo.com
  ```

---

## 🔬 5. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar a numeração exata de cada linha do programa completo:

* **Linha 1 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto onde o Java começa a rodar.
* **Linha 9 (`Scanner scanner = new Scanner(System.in);`):**  
  Prepara o leitor do teclado.
* **Linha 11 (`String email;`):**  
  Caixinha para o e-mail completo.
* **Linha 12 (`String username;`):**  
  Caixinha para o nome de usuário.
* **Linha 13 (`String domain;`):**  
  Caixinha para o domínio.
* **Linha 15 (`System.out.print("Enter your email: ");`):**  
  Pergunta o e-mail na tela.
* **Linha 17 (`email = scanner.nextLine();`):**  
  Lê o que a pessoa digitou e guarda em `email`.
* **Linha 19 (`if (email.contains("@")) {`):**  
  **O Escudo Protetor:** Pergunta se tem `@`. Se tiver, entra no bloco. Se não tiver, protege o programa contra erros!
* **Linha 20 (`username = email.substring(0, email.indexOf("@"));`):**  
  Fatia o nome de usuário antes do `@`.
* **Linha 21 (`domain = email.substring(email.indexOf("@") + 1);`):**  
  Fatia o provedor depois do `@` (+1).
* **Linha 23 (`System.out.println(username);`):**  
  Imprime o nome de usuário fatiado.
* **Linha 24 (`System.out.println(domain);`):**  
  Imprime o domínio fatiado.
* **Linha 25 (`}`):**  
  Fecha o bloco do `if`.
* **Linha 26 (`else {`):**  
  **O Senão:** Só é acionado se a pessoa esqueceu o arroba.
* **Linha 27 (`System.out.println("Emails must contain @");`):**  
  Avisa: *"E-mails precisam conter @"*.
* **Linha 28 (`}`):**  
  Fecha o bloco do `else`.
* **Linha 30 (`scanner.close();`):**  
  Fecha o leitor de teclado com segurança.
* **Linhas 31 e 32 (`}` e `}`):**  
  Fecham o método `main` e a classe `Main`.

---

## 💻 6. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste digitar uma vez com `@` e outra vez sem `@`:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Capítulo 14: Validação com contains() e recorte com substring()

        Scanner scanner = new Scanner(System.in);

        String email;
        String username;
        String domain;

        System.out.print("Enter your email: ");
        email = scanner.nextLine();

        // Escudo de proteção: só fatia se tiver o arroba!
        if (email.contains("@")) {
            username = email.substring(0, email.indexOf("@"));
            domain = email.substring(email.indexOf("@") + 1);

            System.out.println("Usuário: " + username);
            System.out.println("Domínio: " + domain);
        }
        else {
            System.out.println("Emails must contain @");
        }

        scanner.close();
    }
}
```
