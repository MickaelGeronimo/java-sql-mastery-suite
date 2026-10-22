# 🧵 Capítulo 14: `substring()` Interativo com `Scanner` — Parte 4
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Fez Aqui? (O Programa Virou Interativo!)

Até a etapa anterior, nós deixávamos o e-mail digitado direto no código pelo programador.  
Agora, o professor transformou isso em um **aplicativo real e interativo**!

Ele chamou o **`Scanner`** para que **qualquer pessoa no mundo** possa sentar na frente do computador, digitar o e-mail que quiser no teclado, e o Java fatiar na hora em:
1. **Nome de usuário** (*username*)
2. **Domínio do provedor** (*domain*)

E o melhor de tudo: ele adicionou o famoso **`+ 1`** para arrancar o `@` que estava saindo grudado no domínio!

---

## 🎯 2. O Pulo do Gato do `+ 1` no Domínio

Na etapa anterior, o domínio estava saindo com o arroba grudado: `@gmail.com`.  
Por que isso acontecia?  
Porque o `email.indexOf("@")` acha a gaveta onde mora o `@`. Como o corte começava exatamente nessa gaveta, o `@` vinha junto.

### 🪄 A Solução com `+ 1`:
Olhe a nova linha 19:
```java
domain = email.substring(email.indexOf("@") + 1);
```

### 🧠 Como o Java calcula isso:
1. O `email.indexOf("@")` procura o arroba e acha, por exemplo, a **gaveta 6**.
2. A matemática faz: $6 + 1 = \mathbf{7}$!
3. A ordem vira: `email.substring(7);`
4. Na gaveta 7 mora a letra `'g'` do Gmail!
5. O Java começa na gaveta 7 e vai até o final, pegando apenas: **`"gmail.com"`**!

```text
Gaveta:   0   1   2   3   4   5     6     7   8   9  10  11  12  13  14  15
Letra:   [B] [r] [o] [1] [2] [3]   [@]   [g] [m] [a] [i] [l] [.] [c] [o] [m]
                                    ▲     ▲
                        indexOf("@")┘     └─> indexOf("@") + 1
                        (Gaveta 6)            (Gaveta 7: Início perfeito do domínio!)
```

---

## 🔬 3. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar linha por linha, com a numeração exata do editor do professor:

* **Linha 1 (`public class Main {`):**  
  Declaração da classe principal `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de partida do Java onde o código começa a rodar.
* **Linha 9 (`Scanner scanner = new Scanner(System.in);`):**  
  Cria o objeto `scanner` para escutar e ler o que o usuário digita no teclado.  
  *(Lembre-se de ter `import java.util.Scanner;` na linha 1 do arquivo)*.
* **Linha 11 (`String email;`):**  
  Cria a caixinha vazia que vai guardar o e-mail completo digitado pelo usuário.
* **Linha 12 (`String username;`):**  
  Cria a caixinha vazia que vai guardar o nome de usuário fatiado.
* **Linha 13 (`String domain;`):**  
  Cria a caixinha vazia que vai guardar o domínio fatiado.
* **Linha 15 (`System.out.print("Enter your email: ");`):**  
  Mostra na tela a mensagem pedindo para a pessoa digitar o e-mail. Usou `print` (sem o `ln`) para o cursor piscar na mesma linha!
* **Linha 17 (`email = scanner.nextLine();`):**  
  Pausa o programa e espera a pessoa digitar o e-mail e apertar Enter. Guarda o texto na variável `email`.
* **Linha 18 (`username = email.substring(0, email.indexOf("@"));`):**  
  **O Fatiador do Usuário:** Corta do índice 0 até antes do `@`. Guarda o nome (ex: `"Bro123"`) em `username`.
* **Linha 19 (`domain = email.substring(email.indexOf("@") + 1);`):**  
  **O Fatiador do Domínio:** Acha o `@`, pula uma gaveta para frente (`+ 1`) e pega tudo até o fim. Guarda o provedor (ex: `"gmail.com"`) em `domain`.
* **Linha 21 (`System.out.println(username);`):**  
  Imprime na tela o nome de usuário fatiado.
* **Linha 22 (`System.out.println(domain);`):**  
  Imprime na tela o domínio limpinho sem o arroba.
* **Linha 24 (`scanner.close();`):**  
  Fecha a portinha do leitor de teclado (boa prática de programação).
* **Linha 25 (`}`):**  
  Fecha o método `main`.
* **Linha 26 (`}`):**  
  Fecha a classe `Main`.

---

## 🖥️ 4. O Teste Real no Console

O professor executou o programa e digitou:
```text
Enter your email: Bro123@gmail.com
```

E o Java respondeu instantaneamente:
```text
Bro123
gmail.com
```

Ficou perfeito, elegante e 100% dinâmico! Se você digitar `mickel.dev@hotmail.com`, ele vai separar `mickel.dev` e `hotmail.com` sozinho!

---

## 💻 5. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste digitar diferentes e-mails:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Capítulo 14: Separador dinâmico de e-mail com Scanner e substring

        Scanner scanner = new Scanner(System.in);

        String email;
        String username;
        String domain;

        // 1. Pede o e-mail para o usuário
        System.out.print("Enter your email: ");
        email = scanner.nextLine();

        // 2. Fatia o nome de usuário (do começo até antes do '@')
        username = email.substring(0, email.indexOf("@"));

        // 3. Fatia o domínio (uma posição DEPOIS do '@' até o final)
        domain = email.substring(email.indexOf("@") + 1);

        // 4. Mostra os resultados separados
        System.out.println("Nome de usuário: " + username);
        System.out.println("Domínio: " + domain);

        // 5. Fecha o scanner
        scanner.close();
    }
}
```
