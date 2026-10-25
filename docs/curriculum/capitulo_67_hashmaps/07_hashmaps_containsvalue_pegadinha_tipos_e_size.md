# Curso de Java — Bro Code (12 Horas)
## Capítulo 67: `hashmaps 🗺️` (Parte 7 — Os Métodos `.containsValue()`, a Pegadinha de Tipos `1.00` vs `1`, e o Tamanho com `.size()`)

---

> [!NOTE]
> **Contexto para o Google Docs**: Este documento contém a reconstituição textual completa das **5 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **10:59:38** e **11:00:10** de 12:00:00). Você não precisa das imagens originais: todos os painéis do IntelliJ IDEA, códigos, erros em tempo real (onduinhas vermelhas), avisos amarelos, parâmetros e saídas do console foram reconstituídos em blocos de texto fiéis para o seu documento.

---

## 1. Reconstituição Textual das Telas do Vídeo (Google Docs Ready)

### Tela 1: Erro de Sintaxe ao Chamar `.containsValue()` Vazio (Timestamp: 10:59:38)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 10:59:38 |
+--------------------------------------------------------------------------------------------------+
| Project Tree      |  Main.java                                                                   |
| > .idea           |  16        map.put("coconut", [1.00]); <-- 1.00 SELECIONADO PELO AUTOR       |
| > out             |  17                                                                          |
| v src             |  18        //map.remove("apple");                                            |
|   (c) Main        |  19        //System.out.println(map.get("coconut"));                         |
|   .gitignore      |  20        //System.out.println(map.containsKey("pineapple"));               |
|                   |  21                                                                          |
|                   |  22 💡     System.out.println(map.containsValue(~~)); <-- ONDINHA VERMELHA!  |
|                   |  23    }                                                                     |
|                   |  24 }                                                                        |
+--------------------------------------------------------------------------------------------------+
| O autor apagou o bloco if/else e digitou map.containsValue() com os parênteses vazios.           |
| O IntelliJ sublinhou em vermelho (~~) avisando: "Ei! Você precisa me dizer qual valor quer achar!"|
| O autor então seleciona com o mouse o preço 1.00 na linha 16 para usá-lo como teste.             |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: Passando o Parâmetro Correto `1.00` (Timestamp: 10:59:42)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 10:59:42 |
+--------------------------------------------------------------------------------------------------+
| Project Tree      |  Main.java                                                                   |
| > .idea           |  16        map.put("coconut", 1.00);                                         |
| > out             |  17                                                                          |
| v src             |  18        //map.remove("apple");                                            |
|   (c) Main        |  19        //System.out.println(map.get("coconut"));                         |
|   .gitignore      |  20        //System.out.println(map.containsKey("pineapple"));               |
|                   |  21                                                                          |
|                   |  22 💡     System.out.println(map.containsValue(1.00));                     |
|                   |  23    }                                                                     |
|                   |  24 }                                                                        |
+--------------------------------------------------------------------------------------------------+
| O autor digitou 1.00 dentro dos parênteses. O erro vermelho sumiu instantaneamente!              |
| No canto superior direito, o autor leva o cursor até o botão verde de Play ▶️ para executar.     |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: Resultado Positivo no Console (`true`) (Timestamp: 10:59:44)
```text
+--------------------------------------------------------------------------------------------------+
|  Run / Execution Window (Console do IntelliJ)                                 Timestamp 10:59:44 |
+--------------------------------------------------------------------------------------------------+
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ... Main                                              |
|                                                                                                  |
| [true] <-- Saída destacada em verde e selecionada em azul pelo autor!                            |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O console imprimiu true! Como existe o coco cadastrado com o preço exato de 1.00 na linha 16,    |
| o método map.containsValue(1.00) confirmou: "Sim, existe um produto com esse valor!".           |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: A Grande Armadilha do Java: Testando `1` Inteiro (`false`) (Timestamp ~10:59:55)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - O Teste do Inteiro e o Retorno Surpreendente                                    |
+--------------------------------------------------------------------------------------------------+
| Editor:                                                                                          |
|  22 💡     System.out.println(map.containsValue([1])); <-- 1 com AVISO AMARELO EMBAIXO!         |
+--------------------------------------------------------------------------------------------------+
| Console / Run Output:                                                                            |
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ... Main                                              |
|                                                                                                  |
| [false] <-- O console imprimiu FALSE!                                                            |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| ATENÇÃO MÁXIMA AQUI: O autor trocou 1.00 por 1 (número inteiro sem vírgula).                    |
| O IntelliJ colocou um aviso amarelo sob o 1 ("Suspicious call to containsValue").                |
| Ao rodar, o console deu FALSE! Por quê? Porque o mapa guarda Doubles (1.0) e NÃO Integers (1)!    |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 5: Comentando o `.containsValue()` e Chamando `.size()` (Timestamp 11:00:10)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:00:10 |
+--------------------------------------------------------------------------------------------------+
| Project Tree      |  Main.java                                                                   |
| > .idea           |  18        //map.remove("apple");                                            |
| > out             |  19        //System.out.println(map.get("coconut"));                         |
| v src             |  20        //System.out.println(map.containsKey("pineapple"));               |
|   (c) Main        |  21        //System.out.println(map.containsValue(1.00));  <-- COMENTADO!   |
|   .gitignore      |  22                                                                          |
|                   |  23 💡     System.out.println(map.size());                                   |
|                   |  24    }                                                                     |
|                   |  25 }                                                                        |
+--------------------------------------------------------------------------------------------------+
| O autor comentou a linha 21 e adicionou o novo método na linha 23: map.size().                   |
| O método .size() vai contar quantos pares de produtos existem cadastrados na tabela.            |
| O autor está com o mouse pronto para clicar em Play ▶️.                                          |
+--------------------------------------------------------------------------------------------------+
```

---

## 2. O Código Completo do Arquivo `Main.java`

Aqui está o código exato presente no arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // HashMap = A data structure that stores key-value pairs
        //           Keys are unique, but Values can be duplicated
        //           Does not maintain any order, but is memory efficient
        //           HashMap<Key, Value>

        HashMap<String, Double> map = new HashMap<>();

        map.put("apple", 0.50);
        map.put("orange", 0.75);
        map.put("banana", 0.25);
        map.put("coconut", 1.00);

        //map.remove("apple");
        //System.out.println(map.get("coconut"));
        //System.out.println(map.containsKey("pineapple"));
        //System.out.println(map.containsValue(1.00));

        System.out.println(map.size());

    }
}
```

---

## 3. Explicação Linha por Linha ("Como se fosse um iniciante amador leigo")

Abaixo está o detalhamento de cada instrução, do jeito mais simples e cristalino possível:

### Linha 1: `import java.util.HashMap;`
- **O que faz**: Traz a classe `HashMap` para dentro do programa.
- **Linguagem de Leigo**: Pega a ferramenta no depósito do Java.
- **Saída no Console**: Nenhuma.

### Linhas 6 a 9: O Bloco de Comentários Conceituais
```java
// HashMap = A data structure that stores key-value pairs
//           Keys are unique, but Values can be duplicated
//           Does not maintain any order, but is memory efficient
//           HashMap<Key, Value>
```
- **O que faz**: Regras teóricas anotadas pelo professor Bro Code. O Java ignora.
- **Saída no Console**: Nenhuma.

### Linha 11: `HashMap<String, Double> map = new HashMap<>();`
- **O que faz**: Constrói a tabela de duas colunas:
  - Coluna da esquerda (Chave / Etiqueta): `String` (texto).
  - Coluna da direita (Valor / Preço): `Double` (número com ponto decimal).
- **Saída no Console**: Nenhuma.

### Linhas 13 a 16: Povoando os 4 Itens
```java
map.put("apple", 0.50);
map.put("orange", 0.75);
map.put("banana", 0.25);
map.put("coconut", 1.00);
```
- **O que faz**: Cadastra os 4 produtos e seus preços.
- **Saída no Console**: Nenhuma.

---

### Linhas 18 a 20: Os Métodos Antigos Guardados em Comentários
```java
//map.remove("apple");
//System.out.println(map.get("coconut"));
//System.out.println(map.containsKey("pineapple"));
```
- **O que faz**: Todas começam com `//`, então estão desligadas.
- **Saída no Console**: Nenhuma.

---

### Linha 21 (ou 22 nas Telas 1 a 4): `System.out.println(map.containsValue(...));`

Aqui está o coração da novidade! Vamos entender o que aconteceu em cada tela:

#### A) O que aconteceu na Tela 1 quando ficou vazio `map.containsValue()`?
- **O erro**: O Java deu erro de compilação (sublinhado vermelho `~~`).
- **Por quê?**: O método `.containsValue(...)` precisa obrigatoriamente receber um argumento dentro dos parênteses. Não dá para perguntar para o sistema *"você tem o preço?"* sem dizer qual é o preço!

#### B) O que aconteceu na Tela 2 e 3 com `map.containsValue(1.00)`?
- **A pergunta**: *"Ei tabela, existe alguma fruta que custe exatamente 1.00 dólar?"*
- **A busca**: O Java percorre a coluna dos preços (`0.50`, `0.75`, `0.25`, `1.00`). Ele encontra o `coconut` que custa `1.00`.
- **A resposta**: Ele retorna o valor booleano **`true`**!
- **O que sai no console**:
  ```text
  true
  ```

#### C) O que aconteceu na Tela 4 com `map.containsValue(1)`? (A Pegadinha!)
- **O autor digitou `1` em vez de `1.00`**.
- **A pergunta para o Java**: *"Existe algum valor que seja o número inteiro `1`?"*
- **A resposta do Java**: **`false`**!
- **Por que deu `false` se o coco custa 1 dólar?!**
  - **Atenção:** Em Java, `1` é um número inteiro (`Integer`). Mas a nossa tabela foi configurada como `HashMap<String, Double>`!
  - Para o Java, o objeto `Double` de `1.0` **NÃO É IGUAL** ao objeto `Integer` de `1`!
  - Como os tipos de dados são diferentes, o teste de igualdade falha e o Java responde `false`! O IntelliJ até coloca uma ondinha amarela de advertência avisando dessa incompatibilidade de tipos.

---

### Linha 21 na Tela 5: `//System.out.println(map.containsValue(1.00));`
- **O que faz**: O autor coloca `//` para silenciar a linha do `containsValue`, deixando o console pronto para a próxima novidade.
- **Saída no Console**: Nenhuma.

---

### Linha 23 na Tela 5: `System.out.println(map.size());`
- **O que faz**: O método `.size()` funciona como um contador / velocímetro da tabela! Ele conta quantos pares de chave-valor existem cadastrados no momento.
- **Como ele conta**:
  1. Par 1: `"apple"` ➔ `0.50`
  2. Par 2: `"orange"` ➔ `0.75`
  3. Par 3: `"banana"` ➔ `0.25`
  4. Par 4: `"coconut"` ➔ `1.00`
- **Total de pares**: **`4`**.
- **O que sai no console quando essa linha executa**:
  ```text
  4
  ```

---

### Linhas 24 e 25: `}`
- Linha 24 fecha o método `main`.
- Linha 25 fecha a classe `Main`.
- **Saída no Console**: `Process finished with exit code 0`.

---

## 4. Diferença Crucial: `.containsKey()` vs `.containsValue()`

Muitos alunos iniciantes confundem esses dois métodos. Veja a diferença visual:

```text
       CHAVE (Key)             VALOR (Value)
     [Coluna Esquerda]       [Coluna Direita]
    +-----------------+     +-----------------+
    | "apple"         | --> | 0.50            |
    | "orange"        | --> | 0.75            |
    | "banana"        | --> | 0.25            |
    | "coconut"       | --> | 1.00            |
    +-----------------+     +-----------------+
             ^                       ^
             |                       |
     .containsKey(...)       .containsValue(...)
     Procura na etiqueta     Procura no preço
     Velocidade: O(1)        Velocidade: O(n)
     (Instantânea!)          (Olha um por um)
```

1. **`.containsKey("banana")`**: Procura pelo nome da fruta (etiqueta). Como o HashMap usa código hash (*hash code*), ele acha a chave instantaneamente ($O(1)$).
2. **`.containsValue(0.25)`**: Procura pelo preço. O HashMap não tem índice para os preços, então ele é obrigado a olhar produto por produto até encontrar ($O(n)$).

---

## 5. E se Tivermos Apagado a Maçã Antes de Chamar `.size()`?

Veja o que aconteceria se a linha 18 (`map.remove("apple")`) estivesse ativada:

```java
map.put("apple", 0.50);
map.put("orange", 0.75);
map.put("banana", 0.25);
map.put("coconut", 1.00);

map.remove("apple"); // Apaga a maçã!

System.out.println(map.size()); // Imprime: 3 !
```
O método `.size()` é dinâmico: se você adiciona itens, ele cresce; se você remove itens, ele diminui imediatamente!

---

## 6. Tabela Comparativa de Testes e Saídas no Console

| Código Executado | O que o Java faz? | Saída no Console | Explicação Didática |
| :--- | :--- | :---: | :--- |
| `map.containsValue()` | Erro de compilação | *(Não roda)* | Falta o argumento dentro dos parênteses (Tela 1). |
| `map.containsValue(1.00)` | Busca o valor decimal | `true` | Encontrou o coco que custa 1.00 (Tela 3). |
| `map.containsValue(1)` | Busca um inteiro | `false` | `Integer(1)` é diferente de `Double(1.0)` (Tela 4). |
| `map.containsValue(9.99)` | Busca valor inexistente| `false` | Nenhuma fruta custa 9.99 dólares. |
| `map.size()` | Conta todos os pares | `4` | Existem 4 frutas no mapa (Tela 5). |

---

## 7. Diagrama de Funcionamento do `.size()`

```mermaid
flowchart LR
    subgraph Itens_no_Mapa ["HashMap (4 pares cadastrados)"]
        direction TB
        E1["1. apple = 0.50"]
        E2["2. orange = 0.75"]
        E3["3. banana = 0.25"]
        E4["4. coconut = 1.00"]
    end

    Itens_no_Mapa -->|map.size()| Res["Número Inteiro: 4"]
    Res -->|System.out.println| Console["Console: 4"]
```

---

## 8. Checklist de Fixação Mental para Iniciantes

1. **O que faz o `.containsValue(valor)`?** Pergunta se algum produto na tabela possui aquele preço/conteúdo. Retorna `true` ou `false`.
2. **Por que deu erro quando os parênteses estavam vazios?** Porque o método exige saber qual valor você quer procurar.
3. **Por que `containsValue(1.00)` deu `true` e `containsValue(1)` deu `false`?** Porque o mapa foi criado como `Double`. O número `1` sem vírgula é tratado como `Integer`, e o Java não considera um `Integer` igual a um `Double`.
4. **O que faz o `.size()`?** Retorna a quantidade total de pares (chave + valor) que existem no mapa.
5. **Qual é o resultado do `.size()` no nosso código?** Sai `4`, porque temos 4 frutas cadastradas.
