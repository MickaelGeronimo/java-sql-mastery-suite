# Curso de Java (Bro Code) — Capítulo 41: Overloaded Constructors 🛠️
## Parte 6: Criando o Objeto `user2`, Executando o Código e a Análise Completa das 6 Saídas no Console

---

> [!NOTE]
> **Contexto de Estudo para o Google Docs:**
> Este documento foi elaborado com riqueza visual descritiva e didática descomplicada para que você possa copiar e colar diretamente no seu Google Docs. Mesmo sem as imagens originais na tela, você entenderá exatamente cada detalhe visual do IntelliJ IDEA, cada linha de código, o motivo de cada comando e como o Java pensa nos bastidores.

---

## 1. O Que Aconteceu Nesta Sequência de 2 Imagens? (Visão Geral)

Nas etapas anteriores, criamos a classe `User.java` contendo **dois construtores sobrecarregados** diferentes:
* **Construtor 1:** Aceita apenas 1 dado (`username`).
* **Construtor 2:** Aceita 2 dados (`username` e `email`).

Agora, o professor Bro Code volta para o arquivo principal [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) para colocar esses dois construtores para trabalhar juntos!

1. **Imagem 1 (`media_1791324081771.png` - Timestamp `7:05:31`):**
   * O autor escreve o código para criar o segundo usuário: `User user2 = new User("Patrick", "PStar@aol.com");`.
   * Ele adiciona 3 comandos `System.out.println` para imprimir na tela o `username`, o `email` e a `age` do `user2`.
   * O cursor do mouse é posicionado no canto superior direito do IntelliJ IDEA, em cima do botão verde triangular **Play (Run 'Main')**.
2. **Imagem 2 (`media_1791324091638.png`):**
   * O autor clica no botão Play!
   * A janela inferior do terminal **Run** se abre imediatamente.
   * O programa é executado e imprime no console **6 linhas de texto verde brilhante**, comprovando que cada construtor cumpriu seu papel com perfeição!

---

## 2. Descrição Visual Detalhada das Telas (Para Seu Google Docs)

### 📸 Imagem 1: O Código Pronto para Rodar
* **Aba ativa no topo:** `Main.java` (ao lado da aba `User.java`).
* **Árvore de arquivos à esquerda:** `MyFirstProject > src > Main` e `User`.
* **Editor de código:**
  * Linhas 5 e 6: criação dos objetos `user1` e `user2`.
  * Linhas 8 a 10: impressão dos atributos de `user1`.
  * Linhas 12 a 14: impressão dos atributos de `user2`.
* **Ação visual do autor:** A seta do cursor do mouse está pairando sobre o botão verde de play no topo superior direito (`Current File -> Run`). O rodapé do vídeo do YouTube mostra a barra vermelha de progresso no tempo `7:05:31 / 12:00:00` no capítulo `41.overloaded constructors ...`.

---

### 📸 Imagem 2: A Janela do Console (Run) com o Resultado
* **Janela inferior aberta:** Ferramenta **Run** com a aba `Main`.
* **Linha de comando do sistema:**
  `C:\Users\HP\.jdks\openjdk-23\bin\java.exe ...` (mostrando o Java 23 executando a classe `Main`).
* **Texto no console (em verde vivo):**
  ```text
  Spongebob
  Not provided
  0
  Patrick
  PStar@aol.com
  0
  ```
* **Linha final:** `Process finished with exit code 0` (significa que o programa executou do início ao fim com 100% de sucesso, sem travar nem dar erro).

---

## 3. Como o Java Sabia Qual Construtor Chamar? (A Mágica da Sobrecarga)

Essa é a pergunta de ouro de todo iniciante amador em Java:
> *"Se os dois construtores se chamam `User`, como o Java não se confundiu?"*

Olhe a comparação lado a lado:

| Linha de Criação do Objeto | Argumentos Passados nos Parênteses | Quantos? | Qual Construtor o Java Escolheu? |
| :--- | :--- | :---: | :--- |
| `new User("Spongebob");` | `"Spongebob"` (1 texto) | **1** | **Construtor 1:** `User(String username)` |
| `new User("Patrick", "PStar@aol.com");` | `"Patrick"` e `"PStar@aol.com"` (2 textos) | **2** | **Construtor 2:** `User(String username, String email)` |

O Java olha para os parênteses na hora de rodar:
1. Viu **1 texto**? Dispara o Construtor 1.
2. Viu **2 textos**? Dispara o Construtor 2.
3. Isso é **Sobrecarga de Construtores** (*Overloaded Constructors*)! É como um restaurante que tem no cardápio:
   * Pedido Simples: informe só o nome.
   * Pedido Completo: informe o nome e o e-mail.

---

## 4. Código Completo de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) Linha por Linha

Aqui está o código exato presente no arquivo:

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4:         User user1 = new User("Spongebob");
 5:         User user2 = new User("Patrick", "PStar@aol.com");
 6: 
 7:         System.out.println(user1.username);
 8:         System.out.println(user1.email);
 9:         System.out.println(user1.age);
10: 
11:         System.out.println(user2.username);
12:         System.out.println(user2.email);
13:         System.out.println(user2.age);
14:     }
15: }
```

*(Nota: Na imagem 1 do autor há algumas linhas em branco a mais, mantendo exatamente a mesma lógica).*

---

### Explicação Minuciosa de Cada Linha:

* **Linha 1 (`public class Main {`):**
  * Declaração da classe principal onde nosso programa começa.
  * **O que sai no console?** Absolutamente nada.

* **Linha 2 (`public static void main(String[] args) {`):**
  * O método `main` — a porta de entrada obrigatória de qualquer programa Java executável.
  * **O que sai no console?** Nada.

* **Linha 3 (` `):** Linha em branco.

* **Linha 4 (`User user1 = new User("Spongebob");`):**
  * **Ação:** Cria o primeiro objeto na memória Heap e guarda a referência na variável `user1`.
  * **Construtor acionado:** O primeiro (`User(String username)`), porque só passamos 1 argumento.
  * **O que acontece lá dentro:** 
    * `username` vira `"Spongebob"`.
    * `email` recebe o valor padrão `"Not provided"`.
    * `age` recebe `0`.
  * **O que sai no console?** Nada (apenas cria o objeto em silêncio na memória).

* **Linha 5 (`User user2 = new User("Patrick", "PStar@aol.com");`):**
  * **Ação:** Cria o segundo objeto na memória Heap e guarda a referência na variável `user2`.
  * **Construtor acionado:** O segundo (`User(String username, String email)`), porque passamos 2 argumentos de texto separados por vírgula.
  * **O que acontece lá dentro:** 
    * `username` vira `"Patrick"`.
    * `email` vira `"PStar@aol.com"` (o e-mail real informado!).
    * `age` recebe `0`.
  * **O que sai no console?** Nada (criação em silêncio na memória).

* **Linha 6 (` `):** Linha em branco separadora.

---

### 🖨️ Bloco 1: Imprimindo os Dados de `user1`

* **Linha 7 (`System.out.println(user1.username);`):**
  * Manda exibir na tela o apelido guardado dentro de `user1`.
  * **O que sai no console?**
    ```text
    Spongebob
    ```

* **Linha 8 (`System.out.println(user1.email);`):**
  * Manda exibir o e-mail guardado em `user1`. Como ele foi criado com o Construtor 1, ele herdou o valor de segurança.
  * **O que sai no console?**
    ```text
    Not provided
    ```

* **Linha 9 (`System.out.println(user1.age);`):**
  * Manda exibir a idade de `user1`.
  * **O que sai no console?**
    ```text
    0
    ```

* **Linha 10 (` `):** Linha em branco separadora.

---

### 🖨️ Bloco 2: Imprimindo os Dados de `user2`

* **Linha 11 (`System.out.println(user2.username);`):**
  * Manda exibir o apelido do `user2`.
  * **O que sai no console?**
    ```text
    Patrick
    ```

* **Linha 12 (`System.out.println(user2.email);`):**
  * Manda exibir o e-mail do `user2`. Aqui está a grande vitória da sobrecarga: este objeto **não** tem `"Not provided"`, ele tem o e-mail real!
  * **O que sai no console?**
    ```text
    PStar@aol.com
    ```

* **Linha 13 (`System.out.println(user2.age);`):**
  * Manda exibir a idade do `user2`. Como o Construtor 2 ainda não aceitava idade, ele também ganhou `0` por padrão.
  * **O que sai no console?**
    ```text
    0
    ```

* **Linha 14 (`}`):** Fecha o método `main`.
* **Linha 15 (`}`):** Fecha a classe `Main`.

---

## 5. Representação Visual na Memória (Stack vs Heap)

Para você visualizar como o computador organizou tudo isso na memória RAM:

```text
       MEMÓRIA STACK (Variáveis Locais)                    MEMÓRIA HEAP (Objetos Reais)
  ┌────────────────────────────────────────┐          ┌───────────────────────────────────────┐
  │ user1 (endereço: @0x101) ─────────────┼─────────>│ Objeto User (Instância 1)            │
  │                                        │          │  - username = "Spongebob"             │
  │                                        │          │  - email    = "Not provided"          │
  │                                        │          │  - age      = 0                       │
  │                                        │          └───────────────────────────────────────┘
  │                                        │
  │ user2 (endereço: @0x202) ─────────────┼─────────>┌───────────────────────────────────────┐
  │                                        │          │ Objeto User (Instância 2)            │
  │                                        │          │  - username = "Patrick"               │
  │                                        │          │  - email    = "PStar@aol.com"         │
  │                                        │          │  - age      = 0                       │
  └────────────────────────────────────────┘          └───────────────────────────────────────┘
```

> Observe que `user1` e `user2` são **dois bonecos completamente independentes**. O que você altera ou passa para um não interfere em nada no outro!

---

## 6. Saída Completa e Exata do Console

Ao clicar em **Run**, o terminal exibe exatamente as 6 linhas abaixo, uma embaixo da outra:

```text
Spongebob
Not provided
0
Patrick
PStar@aol.com
0
```

### Resumo das 6 Linhas do Console:
1. `Spongebob` ➔ Nome do `user1` (passado pelo Construtor 1).
2. `Not provided` ➔ E-mail padrão do `user1` (preenchido pelo Construtor 1).
3. `0` ➔ Idade padrão do `user1` (preenchida pelo Construtor 1).
4. `Patrick` ➔ Nome do `user2` (passado pelo Construtor 2).
5. `PStar@aol.com` ➔ E-mail real do `user2` (passado pelo Construtor 2).
6. `0` ➔ Idade padrão do `user2` (preenchida pelo Construtor 2).

---

## 7. O Que Guardar Dessa Aula?

* **Construtores sobrecarregados oferecem flexibilidade:** você pode criar objetos mesmo quando tem pouca informação (`user1`) ou quando tem mais informação (`user2`).
* **Valores padrão evitam bugs:** quem não tem e-mail ganha `"Not provided"`, evitando o famigerado `null`.
* **Identificação automática:** o Java sabe qual construtor chamar apenas contando e checando os tipos dos valores que você colocou dentro dos parênteses do `new User(...)`.
