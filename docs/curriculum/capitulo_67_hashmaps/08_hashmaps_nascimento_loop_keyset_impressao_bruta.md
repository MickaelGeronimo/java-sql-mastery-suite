# Curso de Java — Bro Code (12 Horas)
## Capítulo 67: `hashmaps 🗺️` (Parte 8 — O Nascimento do Loop: Da Impressão Bruta `{...}` à Construção Passo a Passo de `for(String key : map.keySet())`)

---

> [!NOTE]
> **Contexto para o Google Docs**: Este documento contém a reconstituição textual completa das **5 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:00:18** e **11:01:15** de 12:00:00). Você não precisa das imagens originais: todos os painéis do IntelliJ IDEA, códigos, saídas do console, linhas comentadas, erros de digitação intermediários (onduinhas vermelhas) e o raciocínio pedagógico do autor foram reconstituídos em blocos de texto fiéis para o seu documento.

---

## 1. Reconstituição Textual das Telas do Vídeo (Google Docs Ready)

### Tela 1: O Console Exibe a Saída de `.size()` (`4`) (Timestamp: 11:00:18)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java] e Console                         Timestamp 11:00:18 |
+--------------------------------------------------------------------------------------------------+
| Editor:                                                                                          |
|  13 💡     map.put(["apple"], 0.50); <-- "apple" SELECIONADA EM AZUL                             |
|  14        map.put("orange", 0.75);                                                              |
+--------------------------------------------------------------------------------------------------+
| Console / Run Output:                                                                            |
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ... Main                                              |
|                                                                                                  |
| [4] <-- O número 4 destacado em verde e selecionado em azul no console!                          |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O programa acabou de rodar System.out.println(map.size()); da aula anterior.                    |
| O console imprimiu 4, confirmando que temos exatamente 4 produtos cadastrados na tabela.         |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: Silenciando o `.size()` para Deixar o Código Limpo (Timestamp: 11:00:23)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:00:23 |
+--------------------------------------------------------------------------------------------------+
| Project Tree      |  Main.java                                                                   |
| > .idea           |  18        //map.remove("apple");                                            |
| > out             |  19        //System.out.println(map.get("coconut"));                         |
| v src             |  20        //System.out.println(map.containsKey("pineapple"));               |
|   (c) Main        |  21        //System.out.println(map.containsValue(1.00));                    |
|   .gitignore      |  22 💡     //System.out.println(map.size());  <-- RECÉM-COMENTADO COM //     |
|                   |  23    }                                                                     |
|                   |  24 }                                                                        |
+--------------------------------------------------------------------------------------------------+
| O autor adiciona duas barras // na linha 22. Agora, todas as 5 ferramentas explicadas            |
| (remove, get, containsKey, containsValue e size) estão devidamente arquivadas e silenciadas!     |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: A Impressão Feia/Bruta com `System.out.println(map)` (Timestamp ~11:00:35)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java] e Console                         Timestamp ~11:00:35|
+--------------------------------------------------------------------------------------------------+
| Editor:                                                                                          |
|  22        //System.out.println(map.size());                                                     |
|  23                                                                                              |
|  24 💡     System.out.println([map]); <-- 'map' SELECIONADO                                      |
|  25    }                                                                                         |
|  26 }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| Console / Run Output:                                                                            |
|  C:\Users\HP\.jdks\openjdk-23\bin\java.exe ... Main                                              |
|                                                                                                  |
|  {orange=0.75, banana=0.25, apple=0.5, coconut=1.0}                                              |
|                                                                                                  |
|  Process finished with exit code 0                                                               |
+--------------------------------------------------------------------------------------------------+
| O autor imprimiu o mapa diretamente. O console despejou tudo em uma linha só:                    |
| {orange=0.75, banana=0.25, apple=0.5, coconut=1.0}.                                              |
| O autor explica: "Isso funciona para testes, mas e se quisermos exibir como um menu elegante?"   |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: Começando a Escrever o Loop (Erro de Digitação ao Vivo) (Timestamp ~11:00:50)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - O Autor Inicia o Loop for-each                               Timestamp ~11:00:50|
+--------------------------------------------------------------------------------------------------+
| Editor:                                                                                          |
|  24 💡     for([String]){~~                                                                      |
|  25                                                                                              |
|  26        }                                                                                     |
|  27    }                                                                                         |
|  28 }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| O autor apagou o System.out.println(map) e começou a digitar: for(String){.                     |
| O IntelliJ sublinha em vermelho (~~) porque o comando for está incompleto (falta a variável      |
| e a lista de onde os dados serão retirados).                                                     |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 5: O Loop Quase Pronto com `.keySet()` e Concatenação Incompleta (Timestamp ~11:01:15)
```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp ~11:01:15|
+--------------------------------------------------------------------------------------------------+
| Editor:                                                                                          |
|  24        for(String key : map.keySet()){                                                       |
|  25 💡         System.out.println(key + [ ]); <-- ONDINHA VERMELHA NO SINAL DE MAIS (+)          |
|  26        }                                                                                     |
|  27    }                                                                                         |
|  28 }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| No canto superior direito, aparece o alerta vermelho 01 ^ (1 erro de compilação temporário).    |
| O autor já completou o for: for(String key : map.keySet()).                                      |
| Na linha 25, ele começou a montar a frase: System.out.println(key + ... e está digitando o resto!|
+--------------------------------------------------------------------------------------------------+
```

---

## 2. A Grande Pergunta do Iniciante: Por que Precisamos de um Loop?

Se você olhar a **Tela 3**, vai pensar:
> *"Ué, mas por que o Bro Code apagou `System.out.println(map);` se ele já mostrava todas as frutas e preços na tela?"*

### O Problema da Impressão Direta (`System.out.println(map)`):
Quando você passa o objeto `map` para o `println`, o Java faz o melhor que pode:
```text
{orange=0.75, banana=0.25, apple=0.5, coconut=1.0}
```
1. **É feio para o usuário final**: Ninguém entra em um restaurante ou aplicativo de supermercado e vê um cardápio entre chaves `{ }`, separado por vírgulas e com sinais de igual `=`.
2. **Não tem formatação personalizada**: Não dá para colocar o símbolo de dinheiro `$`, não dá para colocar uma fruta por linha, nem alinhar o texto.
3. **Não dá para manipular item por item**: Você não consegue aplicar um desconto de 10% só na banana ou calcular a soma total.

### A Solução: O Loop `for`
Com um loop, nós podemos visitar **fruta por fruta**, pegar o seu preço individual e imprimir uma linha bonita e profissional:
```text
orange : $0.75
banana : $0.25
apple : $0.5
coconut : $1.0
```

---

## 3. O Quebra-Cabeça: Por que NÃO Podemos Usar um `for` Tradicional com `i`?

Quem já aprendeu `ArrayList` ou vetores comuns em Java está acostumado a fazer assim:

```java
// ISTO FUNCIONA EM ARRAYLIST, MAS NÃO FUNCIONA EM HASHMAP:
for(int i = 0; i < 4; i++){
    System.out.println(map.get(i)); // ERRO! NÃO EXISTE POSIÇÃO 0, 1, 2, 3!
}
```

### Por que isso falha no `HashMap`?
- Em uma lista comum, as coisas são guardadas por **números de índice**: gaveta 0, gaveta 1, gaveta 2...
- No `HashMap`, as gavetas **NÃO têm números**! Elas têm **nomes de texto** (`"apple"`, `"orange"`, `"banana"`, `"coconut"`).
- Se você pedir `map.get(0)`, o Java procura uma fruta chamada `0`. Como não existe, retorna `null`!

### A Grande Sacada: O Método `.keySet()`!
Como não temos números de 0 a 3, o Java criou uma ferramenta genial chamada:
```java
map.keySet()
```
- **O que faz o `.keySet()`?**
  Ele vai até o mapa e pega **apenas as etiquetas** (as chaves) e coloca todas dentro de uma sacola chamada `Set`.
  A sacola contém: `["orange", "banana", "apple", "coconut"]`.
- Agora nós temos uma coleção de palavras para percorrer!

---

## 4. Dissecação da Construção do Loop (Telas 4 e 5)

Vamos ver a evolução da digitação do autor e o que cada pedaço significa:

### Passo 1: O que ele começou a digitar na Tela 4
```java
for(String){
```
- **O que ele queria dizer**: *"Java, quero percorrer textos (`String`)..."*
- **Por que deu erro vermelho?**: O Java precisa de 3 informações no `for-each`:
  1. Qual é o tipo da variável? (`String`)
  2. Qual será o apelido temporário de cada item? (faltava a palavra `key`!)
  3. De onde ele vai puxar esses itens? (faltava os dois pontos `:` e o `map.keySet()`)!

### Passo 2: O cabeçalho completo na Tela 5
```java
for(String key : map.keySet()){
```
- **`String key`**: Cria uma variável temporária chamada `key` (chave). A cada volta do carrossel, ela vai segurar o nome de uma fruta diferente.
- **`:` (dois pontos)**: Significa **"EM"** ou **"DENTRO DE"**.
- **`map.keySet()`**: A sacola cheia de chaves do nosso mapa.
- **Tradução em Português Claro**:
  > *"Para cada fruta (`String key`) que estiver dentro da sacola de chaves do meu mapa (`map.keySet()`), repita os comandos abaixo:"*

### Passo 3: O `println` em construção na Tela 5
```java
System.out.println(key + );
```
- O autor digitou `key` (o nome da fruta atual).
- Colocou o sinal de mais `+` (para colar outro pedaço de texto).
- Como ele pausou a digitação no sinal de `+`, o compilador deu erro vermelho temporário (onduinha `~`), porque a frase ficou pela metade!
- Na sequência, ele vai completar com `" : $" + map.get(key)`.

---

## 5. Explicação Linha por Linha do Código Nesse Ponto

Abaixo, todas as linhas do arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) enumeradas:

### Linha 1: `import java.util.HashMap;`
- **O que faz**: Importa a classe `HashMap`.
- **Saída no Console**: Nenhuma.

### Linha 3: `public class Main {`
- **O que faz**: Declara a classe principal.
- **Saída no Console**: Nenhuma.

### Linha 4: `public static void main(String[] args) {`
- **O que faz**: Ponto de entrada do programa.
- **Saída no Console**: Nenhuma.

### Linhas 6 a 9: Os Comentários Teóricos
```java
// HashMap = A data structure that stores key-value pairs
//           Keys are unique, but Values can be duplicated
//           Does not maintain any order, but is memory efficient
//           HashMap<Key, Value>
```
- **O que faz**: Notas explicativas do autor.
- **Saída no Console**: Nenhuma.

### Linha 11: `HashMap<String, Double> map = new HashMap<>();`
- **O que faz**: Cria o mapa vazio tipado com `String` na chave e `Double` no valor.
- **Saída no Console**: Nenhuma.

### Linhas 13 a 16: Cadastro das 4 Frutas
```java
map.put("apple", 0.50);
map.put("orange", 0.75);
map.put("banana", 0.25);
map.put("coconut", 1.00);
```
- **O que faz**: Adiciona os 4 produtos na tabela.
- **Saída no Console**: Nenhuma.

---

### Linhas 18 a 22: O Cemitério de Testes Silenciados
```java
//map.remove("apple");
//System.out.println(map.get("coconut"));
//System.out.println(map.containsKey("pineapple"));
//System.out.println(map.containsValue(1.00));
//System.out.println(map.size());
```
- **O que faz**: Todas as 5 linhas têm `//` na frente.
- **Por que o autor não deletou?**: Para manter um registro visual de tudo que a aula já ensinou, servindo de resumo para o aluno sem interferir na nova demonstração!
- **Saída no Console**: Nenhuma.

---

### Linha 24 (Tela 3): `System.out.println(map);`
- **O que faz**: Imprime o objeto mapa na sua forma padrão.
- **Saída no Console**:
  ```text
  {orange=0.75, banana=0.25, apple=0.5, coconut=1.0}
  ```

---

### Linha 24 (Telas 4 e 5): `for(String key : map.keySet()){`
- **O que faz**: Abre o laço de repetição que vai dar 4 voltas (uma para cada fruta que existe no `keySet`).
- **Volta 1**: `key` = `"orange"`
- **Volta 2**: `key` = `"banana"`
- **Volta 3**: `key` = `"apple"`
- **Volta 4**: `key` = `"coconut"`
- **Saída no Console**: Nenhuma ainda (a impressão ocorre na linha seguinte).

---

### Linhas 26 a 28: Chaves de Fechamento `}`
- Fecham o laço `for`, o método `main` e a classe `Main`.

---

## 6. Tabela Comparativa: As Duas Formas de Imprimir um `HashMap`

| Forma | Código | Saída no Console | Vantagens | Desvantagens |
| :--- | :--- | :--- | :--- | :--- |
| **Forma 1 (Bruta)** | `System.out.println(map);` | `{orange=0.75, banana=0.25, apple=0.5, coconut=1.0}` | Muito rápida de escrever; boa para achar bugs (*debug*). | Feia para o usuário; tudo em uma linha só; não dá para customizar. |
| **Forma 2 (Loop Elegante)** | `for(String key : map.keySet())` | `orange : $0.75`<br>`banana : $0.25`<br>`apple : $0.5`<br>`coconut : $1.0` | Bonita; uma linha por item; permite adicionar `$`, textos e fazer cálculos. | Exige escrever 3 linhas de código com o laço `for`. |

---

## 7. Diagrama Visual: Como o `keySet()` Alimenta o Laço `for`

```mermaid
flowchart TD
    MAP["HashMap na Memória:<br>apple: 0.50 | orange: 0.75 | banana: 0.25 | coconut: 1.00"]
    
    MAP -->|map.keySet()| SACOLA["Sacola de Chaves (Set):<br>['orange', 'banana', 'apple', 'coconut']"]
    
    SACOLA --> LOOP["for (String key : map.keySet())"]
    
    LOOP -->|1ª Volta| V1["key = 'orange'"]
    LOOP -->|2ª Volta| V2["key = 'banana'"]
    LOOP -->|3ª Volta| V3["key = 'apple'"]
    LOOP -->|4ª Volta| V4["key = 'coconut'"]
```

---

## 8. Checklist de Fixação Mental para Iniciantes

1. **O que saiu no console quando o autor rodou `.size()` na Tela 1?** Saiu o número `4`.
2. **Por que o autor comentou as linhas 18 a 22?** Para não poluir o console com saídas antigas, deixando espaço para a explicação do loop.
3. **O que `System.out.println(map)` exibe?** Exibe o mapa em uma única linha entre chaves `{chave=valor, ...}`.
4. **Por que não usamos `for(int i=0; i<4; i++)`?** Porque o HashMap não usa índices numéricos; suas posições são acessadas por nomes de chaves.
5. **O que faz o método `map.keySet()`?** Devolve um conjunto contendo apenas os nomes de todas as chaves cadastradas.
6. **Por que apareceram ondinhas vermelhas nas Telas 4 e 5?** Porque o autor estava digitando o código ao vivo, e o compilador avisa no mesmo instante quando uma frase de programação ainda está incompleta.
