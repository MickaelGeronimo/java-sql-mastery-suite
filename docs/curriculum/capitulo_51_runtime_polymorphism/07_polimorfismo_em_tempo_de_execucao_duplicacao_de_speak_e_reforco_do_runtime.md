# Curso de Java — Capítulo 51: Polimorfismo em Tempo de Execução (`runtime polymorphism 🤷‍♂️`)
## Parte 7: O Momento do "Pulo do Gato" — Identificando a Redundância de `animal.speak();` e Reconectando à Palavra-Chave `runtime`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:19:15` até `08:19:30` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, de forma simples e descontraída) o momento em que o professor Bro Code pausa a aula para fazer os alunos pensarem criticamente sobre o design do código: ele seleciona a linha `animal.speak();` na Linha 22, compara com a Linha 18 e aponta que **estamos repetindo código desnecessariamente**. Em seguida, ele seleciona a palavra **`runtime`** na Linha 7 para provar que, justamente por ser Polimorfismo Dinâmico, podemos extrair essa chamada para fora dos blocos condicionais, alcançando o auge da elegância da Orientação a Objetos.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (as seleções azuis no editor, o confronto entre as Linhas 18 e 22, o retorno à Linha 7 e o raciocínio de refatoração) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 2 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta pausa reflexiva (`08:19:15` a `08:19:30`), o professor Bro Code prepara a transição do "código amador que funciona" para o "código profissional e polimórfico":

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:19:18`<br>`media_1791401530519_187a82ec.png` | Editor aberto em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java). Na Linha 22, a instrução **`animal.speak();`** está selecionada em azul brilhante. Logo acima, na Linha 18, vê-se a exata mesma instrução duplicada dentro do bloco do cachorro. | O professor destaca a redundância visual: *"Reparem nisto: nós escrevemos `animal.speak();` aqui dentro do `if` do cão e repetimos exatamente a mesma coisa aqui dentro do `else if` do gato!"*. |
| **🖼️ Imagem 2** | `08:19:26`<br>`media_1791401553112_4c35c47e.png` | No player do YouTube, o tempo marca `8:19:26 / 12:00:00`. O professor sobe o cursor e seleciona em azul a palavra **`runtime`** na Linha 7 do comentário: `...at runtime based on the actual type of the object.`. | O professor reconecta a prática à teoria: *"Se o método a ser executado é decidido em tempo de execução (`runtime`), por que diabos estamos chamando ele dentro de cada `if`? Nós podemos chamá-lo uma única vez depois!"*. |

---

## 2. A Filosofia do Bom Código: O Princípio DRY (*Don't Repeat Yourself*)

Se você é um iniciante amador em programação, memorize esta sigla para o resto da sua vida: **DRY** (*Don't Repeat Yourself* — em português: **"Não Se Repita!"**).

### O Raciocínio do Professor Bro Code:
Olhe para o código atual:
```java
if(choice == 1){
    animal = new Dog();
    animal.speak();  // <-- Repetição 1
}
else if(choice == 2){
    animal = new Cat();
    animal.speak();  // <-- Repetição 2
}
```

Imagine que o nosso programa evoluísse e passasse a suportar **10 animais diferentes**:
* `1 = Dog`
* `2 = Cat`
* `3 = Cow`
* `4 = Lion`
* `5 = Elephant`
* ... e assim por diante!

Se continuássemos nesse caminho, teríamos que escrever `animal.speak();` **10 vezes**, uma dentro de cada bloco condicional! Isso é feio, poluído e contra as boas práticas de programação.

```
+---------------------------------------------------------------------------------------------------------+
|                                    A GRANDE SACADA DO POLIMORFISMO                                      |
|                                                                                                         |
|  O papel do 'if / else if' é APENAS UM:                                                                 |
|  ---> Decidir QUAL OBJETO será colocado dentro da variável 'animal'!                                    |
|                                                                                                         |
|  O papel do Polimorfismo é:                                                                             |
|  ---> Chamar 'animal.speak();' UMA ÚNICA VEZ lá embaixo!                                                |
|       O Java olha quem está na memória e executa o som certo sozinho!                                   |
+---------------------------------------------------------------------------------------------------------+
```

---

## 3. Dissecando a Imagem 2: O Porquê da Palavra `runtime`

Na Imagem 2 (`media_1791401553112_4c35c47e.png`), o professor dá um destaque especial à palavra:
$$\texttt{//                        at }\colorbox{#264f78}{\textbf{runtime}}\texttt{ based on the actual type of the object.}$$

### Por que ele fez isso?
Porque essa é a resposta definitiva para quem duvida do poder do Java:
1. Quando escrevemos `animal.speak();`, a classe `Animal` só tem um método abstrato sem código.
2. Em tempo de compilação, o Java só quer saber se a classe `Animal` conhece o método `speak()`. Ela conhece!
3. Em tempo de execução (**`runtime`**), a JVM vai até a memória RAM e pergunta: *"Quem está guardado nessa variável agora?"*.
   * Se o usuário digitou 1, está guardado um `Dog` $\rightarrow$ o Java executa o `speak()` do Cachorro.
   * Se o usuário digitou 2, está guardado um `Cat` $\rightarrow$ o Java executa o `speak()` do Gato.
4. Logo, **não importa onde `animal.speak()` seja chamado**, desde que seja após a variável receber o objeto! Chamá-lo fora dos blocos é a forma mais pura e bonita de Polimorfismo Dinâmico.

---

## 4. O Código de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) Linha por Linha (Linhas 1 a 26)

Aqui está a visão anatômica do código no exato momento das duas capturas de tela:

```java
 1 | import java.util.Scanner;
 2 | 
 3 | public class Main {
 4 |     public static void main(String[] args) {
 5 | 
 6 |         // Runtime polymorphism = When the method that gets executed is decided
 7 |         //                        at runtime based on the actual type of the object.
 8 | 
 9 |         Scanner scanner = new Scanner(System.in);
10 | 
11 |         Animal animal;
12 | 
13 |         System.out.print("Would you like a dog or a cat? (1 = dog, 2 = cat): ");
14 |         int choice = scanner.nextInt();
15 | 
16 |         if(choice == 1){
17 |             animal = new Dog();
18 |             animal.speak();  // <-- [REDUNDÂNCIA 1]
19 |         }
20 |         else if(choice == 2){
21 |             animal = new Cat();
22 |             animal.speak();  // <-- [REDUNDÂNCIA 2 - SELECIONADA NA IMAGEM 1]
23 |         }
24 | 
25 |     }
26 | }
```

### Análise Específica das Linhas em Foco:

* **Linha 7 (`// at runtime based on the actual type of the object.`):**
  * *(Selecionada na Imagem 2)*: Reforça que o mecanismo dinâmico da JVM desvincula a chamada do método da espécie específica no código fonte.
* **Linha 11 (`Animal animal;`):**
  * A variável universal que serve como receptáculo polimórfico.
* **Linha 18 (`animal.speak();`):**
  * Chamada de método acionada caso a condição do cão seja verdadeira.
* **Linha 22 (`animal.speak();`):**
  * *(Selecionada na Imagem 1)*: Cópia exata da Linha 18. O professor vai deletar essas duas linhas no próximo segundo para unificá-las em uma só!

---

## 5. O Que Sai no Console Neste Momento?

Como esta etapa é uma pausa didática do professor para apontar a redundância do código, o console permanece com o histórico da execução anterior:

```text
Would you like a dog or a cat? (1 = dog, 2 = cat): 2
The cat goes *meow*

Process finished with exit code 0
```

O programa continua funcionando 100%, mas o professor está preparando o terreno para deixá-lo com nível profissional de legibilidade e elegância.

---

## 6. O Que Vai Acontecer a Seguir? (A Prévia da Refatoração)

No próximo instante da aula, o professor Bro Code vai realizar a cirurgia de limpeza:
1. **Remover a Linha 18 e a Linha 22.**
2. **Adicionar o bloco `else`** para lidar com usuários que digitam opções que não existem (ex: digitar 3, 99 ou -1):
   ```java
   else {
       animal = null; // ou mensagem de erro
       System.out.println("That choice was invalid");
   }
   ```
3. **Colocar a chamada unificada no final:**
   ```java
   animal.speak();
   ```
   E lidar com o possível caso de `null` se a escolha tiver sido inválida!
