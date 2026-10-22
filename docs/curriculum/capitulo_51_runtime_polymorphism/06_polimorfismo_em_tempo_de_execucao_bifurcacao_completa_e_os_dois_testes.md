# Curso de Java — Capítulo 51: Polimorfismo em Tempo de Execução (`runtime polymorphism 🤷‍♂️`)
## Parte 6: A Conclusão da Bifurcação Dinâmica (`else if(choice == 2)`), o Destaque da Variável `animal` e os Dois Testes Triunfantes no Console!

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:18:42` até `08:19:15` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, de forma simples e descontraída) a consolidação do Polimorfismo Dinâmico: o professor Bro Code implementa o bloco do gato com `else if(choice == 2)`, seleciona a variável `animal` na Linha 11 para mostrar como o IntelliJ IDEA ilumina todas as suas ocorrências ao longo do código, e em seguida roda o programa duas vezes seguidas no console — digitando `1` para ver o cão latir (*woof*) e digitando `2` para ver o gato miar (*meow*)!  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (o código do `else if`, o realce em cinza/rosa das 5 ocorrências de `animal`, os dois testes no painel `Run` e as mensagens em verde neon) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 5 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência consagradora (`08:18:42` a `08:19:15`), vemos o programa tomar decisões reais baseadas no teclado do usuário:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:18:42`<br>`media_1791401399716_03123799.png` | Editor aberto em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java). Linhas 20 a 23 adicionadas: `else if(choice == 2){ animal = new Cat(); animal.speak(); }`. O cursor pisca logo após a chave de fechamento `}` na Linha 23. | O professor finaliza a segunda opção do questionário, permitindo que a variável `animal` receba uma instância de `Cat`. |
| **🖼️ Imagem 2** | `08:18:53`<br>`media_1791401419675_e763ce8b.png` | O professor dá um duplo clique sobre o nome da variável **`animal`** na Linha 11. O IntelliJ ilumina automaticamente as **5 ocorrências** de `animal` no código com um fundo cinza/rosado. | O professor explica visualmente o núcleo do Polimorfismo: *"Vejam: declaramos uma única variável `animal` do tipo pai. Ela assume a forma de `Dog` na Linha 17 ou a forma de `Cat` na Linha 21!"*. |
| **🖼️ Imagem 3** | `08:19:03`<br>`media_1791401430217_64a348ff.png` | **1º Teste no Console:** O painel inferior `Run` abre. Pergunta: `Would you like a dog or a cat? (1 = dog, 2 = cat): `. O professor digita **`1`** e pressiona Enter. | O console imprime em verde neon: `The dog goes *woof*`, confirmando o caminho do cachorro! |
| **🖼️ Imagem 4** | `08:19:06`<br>`media_1791401442035_82376c2b.png` | **2º Teste no Console:** O professor roda o programa novamente. Pergunta repetida. Desta vez, ele digita **`2`** e pressiona Enter. | O console imprime em verde neon: `The cat goes *meow*`, confirmando o caminho do gato! |
| **🖼️ Imagem 5** | `08:19:10`<br>`media_1791401511822_f6768982.png` | O cursor do mouse se move até o botão de fechar `x` da aba `Main` no painel `Run`, preparando a tela para as considerações finais e refatoração. | O professor celebra o sucesso dos dois caminhos dinâmicos. |

---

## 2. A Mágica do Destaque do IntelliJ: As 5 Vidas da Variável `animal` (Imagem 2)

Na Imagem 2 (`media_1791401419675_e763ce8b.png`), o professor dá um duplo clique na palavra `animal` na Linha 11. Veja exatamente o que o IntelliJ IDEA faz:

```java
11 | Animal [animal];           <-- 1ª: DECLARAÇÃO DA GAVETA (Referência genérica)
...
16 | if(choice == 1){
17 |     [animal] = new Dog();  <-- 2ª: TRANSFORMAÇÃO EM CACHORRO
18 |     [animal].speak();      <-- 3ª: CACHORRO LATE (*woof*)
19 | }
20 | else if(choice == 2){
21 |     [animal] = new Cat();  <-- 4ª: TRANSFORMAÇÃO EM GATO
22 |     [animal].speak();      <-- 5ª: GATO MIA (*meow*)
23 | }
```

### O Que Essa Imagem Ensina Para o Iniciante Amador?
1. **Uma Única Variável, Duas Personalidades:** Nós não precisamos criar duas variáveis diferentes (como `Dog myDog;` e `Cat myCat;`). Nós criamos **apenas uma** variável genérica `animal`.
2. **A Decisão é Tardia (*Late Binding*):** Quando o arquivo é compilado, o Java não tem como saber se a Linha 17 ou a Linha 21 vai rodar. É o dedo do usuário teclando `1` ou `2` que decide o destino da variável em tempo de execução (*runtime*)!

---

## 3. Dissecação Linha por Linha de Todo o [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Linhas 1 a 26)

Aqui está o código completo transcrito exatamente como aparece no editor:

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
18 |             animal.speak();
19 |         }
20 |         else if(choice == 2){
21 |             animal = new Cat();
22 |             animal.speak();
23 |         }
24 | 
25 |     }
26 | }
```

### Explicação Passo a Passo:

* **Linha 1 (`import java.util.Scanner;`):** Importa o leitor de teclado.
* **Linhas 3 e 4:** Declaração da classe principal e do método `main`.
* **Linhas 6 e 7:** Comentários conceituais sobre Polimorfismo Dinâmico.
* **Linha 9 (`Scanner scanner = new Scanner(System.in);`):** Prepara a escuta do teclado.
* **Linha 11 (`Animal animal;`):** Declara a variável polimórfica `animal`, inicialmente sem apontar para nenhum objeto (*null*).
* **Linha 13 (`System.out.print("Would you like a dog or a cat? (1 = dog, 2 = cat): ");`):** Exibe a pergunta interativa sem quebrar linha.
* **Linha 14 (`int choice = scanner.nextInt();`):** Congela o programa até o usuário digitar um número e teclar Enter, guardando o valor em `choice`.
* **Linha 16 (`if(choice == 1){`):** Se o usuário digitou `1`:
  * **Linha 17 (`animal = new Dog();`):** Instancia um `Dog` na memória RAM e vincula à variável `animal`.
  * **Linha 18 (`animal.speak();`):** O Java olha na memória, vê que é um cachorro e chama o método `speak()` de `Dog.java` $\rightarrow$ imprime *"The dog goes \*woof\*"*.
* **Linha 19 (`}`):** Fecha o bloco do cachorro.
* **Linha 20 (`else if(choice == 2){`):** Se a escolha não foi 1, mas foi `2`:
  * **Linha 21 (`animal = new Cat();`):** Instancia um `Cat` na memória RAM e vincula à mesmíssima variável `animal`.
  * **Linha 22 (`animal.speak();`):** O Java olha na memória, vê que é um gato e chama o método `speak()` de `Cat.java` $\rightarrow$ imprime *"The cat goes \*meow\*"*.
* **Linha 23 (`}`):** Fecha o bloco do gato.
* **Linha 25 e 26:** Fecham o método `main` e a classe `Main`.

---

## 4. O Que Aparece no Console? (Análise das Imagens 3 e 4)

Aqui está a reprodução exata das duas execuções sucessivas realizadas pelo professor no terminal:

### 🐶 1ª Execução (Imagem 3 — Usuário Digitou `1`):
```text
Would you like a dog or a cat? (1 = dog, 2 = cat): 1
The dog goes *woof*

Process finished with exit code 0
```
* **O que aconteceu na memória:**  
  $$\text{choice} = 1 \implies \text{animal} \longrightarrow [\text{ Objeto Dog na RAM }] \implies \text{speak()} = \text{"The dog goes *woof*"}$$

---

### 🐱 2ª Execução (Imagem 4 — Usuário Digitou `2`):
```text
Would you like a dog or a cat? (1 = dog, 2 = cat): 2
The cat goes *meow*

Process finished with exit code 0
```
* **O que aconteceu na memória:**  
  $$\text{choice} = 2 \implies \text{animal} \longrightarrow [\text{ Objeto Cat na RAM }] \implies \text{speak()} = \text{"The cat goes *meow*"}$$

---

## 5. Dica de Mestre: A Oportunidade de Refatoração Elegante

Observe atentamente as Linhas 18 e 22:
```java
if(choice == 1){
    animal = new Dog();
    animal.speak();  // <-- Repetiu aqui!
}
else if(choice == 2){
    animal = new Cat();
    animal.speak();  // <-- Repetiu aqui de novo!
}
```

### Qual é o segredo que o professor vai mostrar a seguir?
No Polimorfismo profissional, **nós não precisamos repetir `animal.speak();` dentro de cada `if` e `else if`**!
Basta usar os blocos condicionais **apenas para decidir qual objeto criar** (`new Dog()` ou `new Cat()`), e depois, **uma única vez lá embaixo**, chamar:
```java
animal.speak(); // Uma única chamada genérica para governar a todos!
```
Isso torna o código infinitamente mais limpo, elegante e profissional!

---

## 6. Resumo Prático para o Google Docs (Para Fixar Definitivamente)

1. **Bifurcação Completa:** Com `if(choice == 1)` e `else if(choice == 2)`, cobrimos as duas espécies de animais disponíveis no projeto.
2. **Polimorfismo Dinâmico Provado:** A mesmíssima linha de código (`animal.speak()`) gerou *"The dog goes \*woof\*"* na primeira execução e *"The cat goes \*meow\*"* na segunda execução, guiada unicamente pelo que o usuário digitou no console.
3. **Desacoplamento:** O método `main` não precisa saber os detalhes internos de como um cachorro late ou como um gato mia. Ele só precisa saber que qualquer `Animal` tem o método `speak()`.
