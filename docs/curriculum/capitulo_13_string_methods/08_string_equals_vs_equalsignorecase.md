# 🧵 Capítulo 13: Comparação de Textos em Java — O Guia Definitivo (`.equals()` vs. `.equalsIgnoreCase()`)
*(Apostila de Estudo Completa e Autossuficiente — Pronta para o Google Docs)*

---

## 🧭 1. O Cenário: O Filtro de Nomes Proibidos

Imagine que você está desenvolvendo o sistema de cadastro de um aplicativo ou jogo.  
O seu chefe chega para você e diz:
> *"Mickel, por questões básicas de segurança, **nenhum usuário pode ter o nome de usuário igual a 'password'** (senha)!"*

Você, como programador Java, senta para escrever uma regra de validação com `if / else`.  
É aqui que começa uma das lições mais importantes de toda a linguagem Java: **como o computador compara dois textos?**

---

## 🧠 2. A Regra Fundamental: Por Que NUNCA Usar `==` para Textos?

Se você tentar fazer:
```java
if (name == "password") // ❌ NUNCA FAÇA ISSO EM JAVA!
```

### 🔍 Por que o `==` é uma armadilha mortal com Strings?
No Java, nós temos dois tipos de dados:
1. **Tipos Primitivos (`int`, `double`, `boolean`, `char`):**
   * A caixinha de memória guarda o **próprio valor**.
   * Quando você faz `x == y`, o Java compara o valor numérico que está dentro da caixa.
2. **Objetos de Referência (`String`):**
   * A caixinha de memória **não** guarda o texto diretamente; ela guarda o **endereço de memória** (onde o texto mora no chip da memória RAM).
   * Quando você usa `==` em Strings, o Java compara: *"Esses dois textos estão guardados no mesmo endereço físico de memória?"*.
   * **O resultado:** Dois textos com as mesmas letras podem dar `false` simplesmente porque foram criados em pedaços diferentes da memória!

```text
       Memória do Computador
       ┌────────────────────────┐
name ─>│ Endereço 0x101: "oi"   │
       ├────────────────────────┤
outro─>│ Endereço 0x202: "oi"   │
       └────────────────────────┘
       name == outro  ->  FALSE! (Endereços diferentes!)
       name.equals(outro) -> TRUE! (As letras são iguais!)
```

> [!IMPORTANT]
> **A Regra de Ouro:**  
> Sempre que quiser comparar se duas Strings têm o mesmo conteúdo de texto, **NUNCA use `==`**. Use os métodos **`.equals()`** ou **`.equalsIgnoreCase()`**!

---

## 🎭 3. O Problema da Letra Maiúscula: O Caso `"Password"` vs. `"password"`

Imagine que você programou o filtro usando `.equals()`:

```java
if (name.equals("password")) {
    System.out.println("Your name can't be password");
}
else {
    System.out.println("Hello " + name);
}
```

### 🧪 Teste 1: O Usuário Comum
O usuário digita tudo em minúsculas: `name = "password"`.
* O Java compara `"password"` com `"password"`.
* Ambas são idênticas.
* O sistema bloqueia com sucesso: `Your name can't be password`!

---

### 🚨 Teste 2: O Usuário Espertinho (A Falha de Segurança!)
Agora um usuário decide burlar o sistema e digita a primeira letra maiúscula:
```java
String name = "Password"; // Com 'P' maiúsculo!
```

O que o Java faz ao rodar `name.equals("password")`?
* O método `.equals()` é **extremamente rígido** (*Case-Sensitive*).
* Na tabela do computador, o caractere `'P'` maiúsculo tem o código numérico `80`.
* O caractere `'p'` minúsculo tem o código numérico `112`.
* Como `80 != 112`, o Java declara: **`false`! Não são iguais!**

### 💥 A Consequência:
O Java pula o `if` e vai direto para o `else`, imprimindo:
```text
Hello Password
```
O usuário espertinho conseguiu enganar o seu sistema e criou uma conta chamada `"Password"` apenas mudando uma letrinha para maiúscula!

---

## 🛡️ 4. A Solução Heroica: `.equalsIgnoreCase()`

Para resolver de vez essa brecha de segurança, o Java possui um método irmão chamado:
> **`.equalsIgnoreCase(...)`**

* **O que a palavra significa:** *Equals* (igual) + *Ignore Case* (ignore a caixa tipográfica / ignore se é maiúscula ou minúscula).
* **Como ele funciona:** Ele não se importa se a letra foi digitada em minúscula, maiúscula ou misturada. Ele só quer saber se as letras do alfabeto são as mesmas!

```text
"password".equalsIgnoreCase("password")  ->  TRUE  ✅
"Password".equalsIgnoreCase("password")  ->  TRUE  ✅ (Pegou o espertinho!)
"PASSWORD".equalsIgnoreCase("password")  ->  TRUE  ✅ (Pegou quem gritou!)
"pAsSwOrD".equalsIgnoreCase("password")  ->  TRUE  ✅ (Pegou o hacker!)
"BroCode".equalsIgnoreCase("password")   ->  FALSE ❌ (Nome liberado!)
```

Com o `.equalsIgnoreCase("password")`, o usuário que digitar `"Password"` será **bloqueado com sucesso**, sem conseguir enganar o sistema!

---

## 🌳 5. Diagrama de Fluxo Visual das Decisões

Veja como o computador pensa ao executar o programa:

```text
                     [ Entrada do Usuário: `name` ]
                                   │
             Pergunta: `name.equalsIgnoreCase("password")`?
                                   │
              ┌────────────────────┴────────────────────┐
             SIM (true)                                NÃO (false)
              │                                         │
              ▼                                         ▼
         Bloqueado!                                Liberado!
"Your name can't be password"                    "Hello " + name
```

---

## 🔬 6. Explicação ENUMERADA Linha por Linha de Todo o Código

Abaixo está a análise linha por linha do programa completo:

* **Linha 1 (`public class Main {`):**  
  Declara a classe principal onde todo o código reside.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de entrada de execução do programa Java.
* **Linha 5 (`String name = "Password";`):**  
  Cria a caixinha de texto chamada `name` guardando o valor a ser testado.
* **Linhas 7 a 15 (`//int length...` até `//name.replace()...`):**  
  Linhas antigas da aula comentadas com `//` para ficarem desativadas.
* **Linhas 17 a 31 (`/* ... */`):**  
  Comentários de bloco (`/*` abre e `*/` fecha) usados para congelar trechos inteiros de testes anteriores de uma só vez.
* **Linha 33 (`if (name.equalsIgnoreCase("password")) {`):**  
  **A Verificação de Segurança:** Compara o texto da variável `name` com a palavra proibida `"password"`, ignorando maiúsculas e minúsculas. Se forem as mesmas letras, devolve `true` e entra no bloco.
* **Linha 34 (`System.out.println("Your name can't be password");`):**  
  Exibe a mensagem de erro alertando que o nome escolhido não é permitido.
* **Linha 35 (`}`):**  
  Fecha as chaves do bloco `if`.
* **Linha 36 (`else {`):**  
  **O Caminho dos Nomes Válidos:** Só é executado se a condição do `if` der `false` (ou seja, se o nome não for "password").
* **Linha 37 (`System.out.println("Hello " + name);`):**  
  Concatena a saudação `"Hello "` com o nome aceito e mostra na tela.
* **Linha 38 (`}`):**  
  Fecha as chaves do bloco `else`.
* **Linha 39 (`}`):**  
  Fecha o método `main`.
* **Linha 40 (`}`):**  
  Fecha a classe `Main`.

---

## 📊 7. Tabela Comparativa de Bolso: `.equals()` vs. `.equalsIgnoreCase()`

| Texto na variável `name` | Resultado com `.equals("password")` | Resultado com `.equalsIgnoreCase("password")` |
| :--- | :---: | :---: |
| `"password"` *(tudo minúsculo)* | `true` | `true` |
| `"Password"` *(primeira maiúscula)* | **`false`** ⚠️ *(brecha!)* | **`true`** 🛡️ *(seguro!)* |
| `"PASSWORD"` *(tudo maiúsculo)* | **`false`** ⚠️ *(brecha!)* | **`true`** 🛡️ *(seguro!)* |
| `"pAsSwOrD"` *(letras misturadas)* | **`false`** ⚠️ *(brecha!)* | **`true`** 🛡️ *(seguro!)* |
| `"BroCode"` *(outro nome qualquer)* | `false` | `false` |

---

## 💻 8. Códigos Práticos para Você Copiar e Testar

### 🔴 Código 1: O Teste com Falha (Usando `.equals()`)
Rode este código para ver como o `"Password"` consegue escapar do filtro:

```java
public class Main {
    public static void main(String[] args) {

        String name = "Password"; // Com 'P' maiúsculo

        // O .equals é rígido: 'P' != 'p'
        if (name.equals("password")) {
            System.out.println("Your name can't be password");
        }
        else {
            // Vai cair aqui! O sistema falhou!
            System.out.println("Hello " + name); // Imprime: Hello Password
        }
    }
}
```

---

### 🟢 Código 2: O Código Seguro e Profissional (Usando `.equalsIgnoreCase()`)
Rode este código para ver a proteção funcionando perfeitamente:

```java
public class Main {
    public static void main(String[] args) {

        String name = "Password"; // Com 'P' maiúsculo

        // O .equalsIgnoreCase ignora maiúsculas e minúsculas
        if (name.equalsIgnoreCase("password")) {
            // Agora cai aqui com segurança!
            System.out.println("Your name can't be password");
        }
        else {
            System.out.println("Hello " + name);
        }
    }
}
```
