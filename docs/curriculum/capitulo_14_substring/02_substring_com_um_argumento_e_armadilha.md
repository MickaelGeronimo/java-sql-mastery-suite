# 🧵 Capítulo 14: `substring()` — Parte 2 (O Corte até o Final e a Armadilha dos Números Fixos)
*(Apostila de Estudo Passo a Passo — Pronta para o Google Docs)*

---

## 🧭 1. O Que o Professor Mostrou de Novo?

Nestas cenas, o professor ensinou um **atalho espetacular** do `.substring()`, mas de propósito caiu numa **armadilha clássica** para nos ensinar como o computador pensa!

Foram duas novidades principais:
1. **O `substring` com apenas UM número**: Você não precisa dizer onde termina; o Java corta até o final da frase sozinho!
2. **O bug do e-mail com tamanho diferente**: O que acontece quando o e-mail muda de tamanho e nós usamos números fixos ("na marra").

---

## ✂️ 2. O Novo Atalho: `substring(início)` com Apenas UM Número!

Até agora, nós escrevíamos:
```java
email.substring(7, 16); // Dizia o início (7) e o fim (16)
```

Mas o Java tem um atalho muito mais esperto:
> Se você quiser cortar de um ponto e ir **até o final de todo o texto**, basta passar **apenas o número de início**!

```java
email.substring(7); // "Java, comece na gaveta 7 e leve tudo até o fim!"
```
Você não precisa mais contar quantas letras tem no final. O Java vai até a última letra automaticamente!

---

## 🚨 3. A Grande Armadilha: Por que saiu `1@gmail.com`?

O professor decidiu mudar o e-mail:
* **E-mail antigo:** `"Bro123@gmail.com"` (o usuário tinha 6 letras).
* **E-mail novo:** `"BroCode1@gmail.com"` (o usuário agora tem 8 letras: B-r-o-C-o-d-e-1!).

### 📏 Olhe a régua das gavetas do NOVO e-mail:
```text
Gaveta: 0  1  2  3  4  5  6  7   8   9 10 11 12 13 14 15 16 17
Letra: [B][r][o][C][o][d][e][1] [@] [g][m][a][i][l][.][c][o][m]
                              ▲   ▲   ▲
                              │   │   └── Gaveta 9: início do gmail
                              │   └────── Gaveta 8: onde está o @
                              └────────── Gaveta 7: ainda é o número 1 do usuário!
```

### 💥 O Desastre:
O professor deixou o código antigo:
```java
String domain = email.substring(7);
```
O Java obedeceu cegamente: foi até a gaveta `7` (onde está o número `'1'`) e pegou tudo até o fim!  
O resultado no console foi um desastre cômico:
```text
1@gmail.com
```
O `'1'` e o `'@'` entraram de penetra no domínio!

---

## 🛠️ 4. O Conserto Manual (Ajustando as Gavetas)

Para consertar, o professor teve que contar as gavetas de novo na mão:
1. O usuário agora vai da gaveta `0` até antes da `8` (índices 0 a 7):
   ```java
   String username = email.substring(0, 8); // Pega: "BroCode1"
   ```
2. O domínio agora começa na gaveta `9` (depois do `@`) e vai até o final:
   ```java
   String domain = email.substring(9); // Pega: "gmail.com"
   ```

Ao rodar com `substring(9)`, o console finalmente voltou a ficar lindo:
```text
gmail.com
```

---

## 🔬 5. Explicação ENUMERADA Linha por Linha de Todo o Código

Vamos olhar a numeração exata de cada linha do programa corrigido:

* **Linha 1 (`public class Main {`):**  
  Abre a classe principal chamada `Main`.
* **Linha 2 (`public static void main(String[] args) {`):**  
  O ponto de partida da execução do Java.
* **Linha 5 (`// .substring() = A method used to extract a portion of a string`):**  
  Comentário que explica o conceito de fatia de texto.
* **Linha 6 (`//               .substring(start, end)`):**  
  Comentário lembrando a fórmula com início e fim.
* **Linha 8 (`String email = "BroCode1@gmail.com";`):**  
  Cria a variável `email` com o novo endereço mais longo.
* **Linha 9 (`String username = email.substring(0, 8);`):**  
  Corta da gaveta 0 até a 7 (para antes do 8) e guarda `"BroCode1"` na variável `username`.
* **Linha 10 (`String domain = email.substring(9);`):**  
  **O Novo Comando com 1 argumento:** Corta a partir da gaveta 9 e vai **até o final do texto sozinho**, guardando `"gmail.com"` na variável `domain`.
* **Linha 12 (`System.out.println(domain);`):**  
  Imprime a variável `domain` no console (mostrou `gmail.com`).
* **Linha 13 (`}`):**  
  Fecha o método `main`.
* **Linha 14 (`}`):**  
  Fecha a classe `Main`.

---

## 🧐 6. A Lição Filosófica de Programação (O Que Vem a Seguir?)

Você percebeu o problema desse jeito de programar?
* Se o usuário se chamar `"Ana@gmail.com"`, os números são uns.
* Se o usuário se chamar `"CarlosEduardo@gmail.com"`, os números são outros.
* **Ficar contando nos dedos e trocando os números no código na mão é IMPOSSÍVEL em um sistema real!**

> [!TIP]
> **Spoiler do Próximo Passo:**  
> Nas próximas aulas, o professor vai nos ensinar a usar o **`.indexOf("@")`** para o Java **achar sozinho onde está o arroba**, tornando o recorte 100% automático para qualquer e-mail do mundo!

---

## 💻 7. Código Completo e Pronto para Copiar e Rodar no IntelliJ

Copie este código para o seu `Main.java` e teste:

```java
public class Main {
    public static void main(String[] args) {

        // Capítulo 14: substring com 1 argumento (até o final)

        String email = "BroCode1@gmail.com";

        // Recorta do 0 até antes do 8 (pega BroCode1)
        String username = email.substring(0, 8);

        // Recorta a partir do 9 até o finalzinho (pega gmail.com)
        String domain = email.substring(9);

        System.out.println("Nome de usuário: " + username); // BroCode1
        System.out.println("Domínio: " + domain);           // gmail.com
    }
}
```
