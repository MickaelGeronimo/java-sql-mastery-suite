# Curso de Java — Capítulo 52: Getters e Setters (`getters and setters 🔐`)
## Parte 1: A Estreia do Encapsulamento — Definição Conceitual, a Criação de `Car.java` e o Acesso Direto aos Atributos

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:19:40` até `08:21:00` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, como para quem nunca programou na vida) a estreia do **Capítulo 52**: o que são **Getters** e **Setters**, por que o capítulo possui o emoji de um cadeado trancado com chave (`🔐`), o conceito supremo de **Encapsulamento** (*Encapsulation*), a criação da nova classe [`Car.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Car.java) e a demonstração do estado "Antes da Proteção" (onde os atributos ainda estão expostos e acessíveis diretamente no [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)).  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (a lousa limpa do projeto, os comentários das Linhas 5 a 7, os popups de criação, o código do construtor de `Car` e a saída impressa no console) foi minuciosamente convertido em texto, diagramas visuais e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 5 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta abertura oficial do Capítulo 52 (`08:19:40` a `08:21:00`), o professor Bro Code inicia a jornada pelo pilar mais importante de segurança da Programação Orientada a Objetos:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:19:45`<br>`media_1791401615134_cee91da1.png` | Editor aberto em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (pasta `src` limpa, apenas com `Main`). Linhas 5 a 7 contêm as definições de Getters e Setters. O professor seleciona a palavra **`GETTERS`** em azul. | O professor introduz a teoria: Getters tornam um campo legível (*readable*), enquanto Setters tornam um campo modificável/gravável (*writeable*). |
| **🖼️ Imagem 2** | `08:19:51`<br>`media_1791401630542_657a0c29.png` | No player do YouTube, o título marca `52.getters and setters 🔐 >`. Abre-se o popup **"New Java Class"**. Letreiro estilizado gigante no vídeo: **`Car`**. | O professor clica com botão direito em `src` e cria uma classe chamada `Car` para servir como exemplo didático. |
| **🖼️ Imagem 3** | `08:20:32`<br>`media_1791401665315_28f93766.png` | Editor aberto em [`Car.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Car.java) (Linhas 1 a 12). Código com 3 atributos (`model`, `color`, `price`) e um construtor `Car(...)` atribuindo com `this`. | O professor escreve a classe do carro. **Atenção:** os atributos ainda NÃO têm a palavra `private` (estão abertos/desprotegidos de propósito para comparação). |
| **🖼️ Imagem 4** | `08:20:55`<br>`media_1791401676888_d68b9cda.png` | Editor de volta em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java). Linha 9 adicionada: `Car car = new Car("Charger", "Yellow", 10000);`. | O professor instancia um Dodge Charger amarelo com preço de 10.000 dólares. |
| **🖼️ Imagem 5** | `08:21:00`<br>`media_1791401696548_aba21611.png` | Linha 11 adicionada em [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java): `System.out.println(car.color + " " + car.model + " " + car.price);`. | O professor demonstra o acesso direto aos campos através do ponto (`car.color`), preparando a explicação de por que isso é perigoso no mundo real. |

---

## 2. A Metáfora do Iniciante Amador: Por Que Esse Capítulo Tem o Emoji do Cadeado (`🔐`)?

Imagine que você tem uma conta bancária ou uma carteira física com dinheiro dentro:

### O Cenário do "Sem Cadeado" (Como está o nosso código agora):
* Se a sua carteira fica aberta no meio da calçada da rua, qualquer pessoa que passar pode:
  1. Olhar quanto dinheiro você tem.
  2. Enfiar a mão e roubar 50 reais.
  3. Ou pior: colocar uma nota falsa de 3 dólares dentro dela!
* No código atual, como os atributos `model`, `color` e `price` não têm cadeado, qualquer programador desatento no `Main.java` poderia fazer:
  ```java
  car.price = -50000; // Um carro com preço negativo?! Não faz sentido!
  car.color = "Batata"; // Uma cor chamada Batata?! Não faz sentido!
  ```
  O Java aceitaria isso sem reclamar, corrompendo os dados do nosso objeto!

### O Cenário do "Com Cadeado" (`🔐 Encapsulamento`):
* Nós colocamos os atributos dentro de um cofre com a palavra-chave **`private`** (privado).
* Ninguém de fora pode tocar diretamente neles.
* Para interagir com o cofre, nós criamos duas portinhas controladas com vigilância:
  1. **GETTER (O Leitor Seguro 👁️):** Permite que qualquer um **leia / visualize** o valor (`READABLE`), sem poder estragar nada.
  2. **SETTER (O Modificador com Regras ✍️):** Permite que alguém **modifique / escreva** um novo valor (`WRITEABLE`), mas passando por regras de validação (ex: *"só aceito preço se for maior que zero!"*).

---

## 3. Dissecando as Definições do Professor (Linhas 5 a 7)

No arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java), o professor Bro Code escreveu as três regras fundamentais do Encapsulamento:

```java
// They help protect object data and add rules for accessing or modifying them.
// GETTERS = Methods that make a field READABLE.
// SETTERS = Methods that make a field WRITEABLE.
```

| Linha | Texto Original em Inglês | Tradução em Português | O Que Isso Significa na Prática? |
| :--- | :--- | :--- | :--- |
| **Linha 5** | *They help protect object data and add rules for accessing or modifying them.* | Eles ajudam a proteger os dados do objeto e adicionam regras para acessá-los ou modificá-los. | Os dados ficam blindados contra alterações malucas ou inválidas. |
| **Linha 6** | *GETTERS = Methods that make a field READABLE.* | **GETTERS** = Métodos que tornam um campo **LEGÍVEL**. | O verbo em inglês *get* significa "obter/pegar". Ex: `car.getModel()` (me dê o modelo). |
| **Linha 7** | *SETTERS = Methods that make a field WRITEABLE.* | **SETTERS** = Métodos que tornam um campo **GRAVÁVEL / MODIFICÁVEL**. | O verbo em inglês *set* significa "definir/configurar". Ex: `car.setPrice(12000)` (defina o novo preço). |

---

## 4. Dissecação Código por Código, Linha por Linha

Vamos analisar os dois arquivos criados nesta aula inicial:

### 4.1 Arquivo: [`Car.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Car.java) (Linhas 1 a 12)

```java
 1 | public class Car {
 2 | 
 3 |     String model;
 4 |     String color;
 5 |     int price;
 6 | 
 7 |     Car(String model, String color, int price){
 8 |         this.model = model;
 9 |         this.color = color;
10 |         this.price = price;
11 |     }
12 | }
```

* **Linha 1 (`public class Car {`):** Declara a classe pública do Carro.
* **Linha 2 (` `):** Linha em branco.
* **Linhas 3 a 5 (`String model; String color; int price;`):**
  * Os três atributos (campos/variáveis de instância) do carro: modelo (texto), cor (texto) e preço (número inteiro).
  * ⚠️ **Aviso de segurança:** Repare que eles **NÃO têm a palavra `private` ainda**! Eles estão com visibilidade padrão de pacote (*package-private*).
* **Linha 6 (` `):** Linha em branco.
* **Linha 7 (`Car(String model, String color, int price){`):**
  * O método construtor da classe. Ele é chamado automaticamente no momento em que alguém digita `new Car(...)`.
  * Recebe três parâmetros: o modelo temporário, a cor temporária e o preço temporário.
* **Linhas 8 a 10 (`this.model = model; this.color = color; this.price = price;`):**
  * A palavra-chave `this` aponta para o objeto real que está nascendo na memória RAM.
  * Transfere os valores dos parâmetros para os atributos do objeto.
* **Linha 11 (`}`):** Fecha o construtor.
* **Linha 12 (`}`):** Fecha a classe `Car`.

---

### 4.2 Arquivo: [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) (Linhas 1 a 13)

```java
 1 | 
 2 | public class Main {
 3 |     public static void main(String[] args) {
 4 | 
 5 |         // They help protect object data and add rules for accessing or modifying them.
 6 |         // GETTERS = Methods that make a field READABLE.
 7 |         // SETTERS = Methods that make a field WRITEABLE.
 8 | 
 9 |         Car car = new Car("Charger", "Yellow", 10000);
10 | 
11 |         System.out.println(car.color + " " + car.model + " " + car.price);
12 |     }
13 | }
```

* **Linha 1:** Linha em branco no topo.
* **Linha 2 (`public class Main {`):** Declaração da classe executável `Main`.
* **Linha 3 (`public static void main(String[] args) {`):** Método de entrada principal da JVM.
* **Linha 4:** Espaço em branco.
* **Linhas 5 a 7:** Os comentários conceituais de Getters e Setters.
* **Linha 8:** Espaço em branco.
* **Linha 9 (`Car car = new Car("Charger", "Yellow", 10000);`):**
  * Cria um objeto do tipo `Car` na memória com:
    * `model` = `"Charger"` (Dodge Charger)
    * `color` = `"Yellow"` (Amarelo)
    * `price` = `10000` (Dez mil)
* **Linha 10:** Espaço em branco.
* **Linha 11 (`System.out.println(car.color + " " + car.model + " " + car.price);`):**
  * O professor acessa os atributos usando a notação ponto:
    * `car.color` pega o texto `"Yellow"`.
    * `car.model` pega o texto `"Charger"`.
    * `car.price` pega o número `10000`.
  * As aspas com espaço `" "` separam as palavras para não ficarem grudadas.
* **Linha 12 (`}`):** Fecha o método `main`.
* **Linha 13 (`}`):** Fecha a classe `Main`.

---

## 5. O Que Aparece no Console? (Resultado da Execução)

Ao clicar no botão verde de execução `▷` ao lado da Linha 2 ou Linha 3 de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):

```text
Yellow Charger 10000

Process finished with exit code 0
```

### Explicação Passo a Passo da Saída:
1. `car.color`: O Java lê o valor `"Yellow"`.
2. `+ " "`: Adiciona um espaço.
3. `+ car.model`: Junta com `"Charger"`.
4. `+ " "`: Adiciona outro espaço.
5. `+ car.price`: Converte o número inteiro `10000` para texto e junta no final.
6. **Resultado na tela:** `Yellow Charger 10000`.
7. `Process finished with exit code 0`: O programa rodou perfeitamente e finalizou com sucesso.

---

## 6. O Que Vai Acontecer a Seguir? (A Grande Virada de Chave)

O professor Bro Code acabou de nos mostrar o código funcionando no modo **"sem cadeado"**.  
Nos próximos minutos da aula:
1. Ele vai entrar em [`Car.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Car.java) e colocar a palavra **`private`** na frente de `model`, `color` e `price`:
   ```java
   private String model;
   private String color;
   private int price;
   ```
2. No mesmo milissegundo, a Linha 11 de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) **vai explodir em erros vermelhos**! O Java vai dizer: *"Ei, você não pode acessar esses campos diretamente porque eles são privados!"*.
3. E para resolver isso com elegância, o professor vai nos ensinar a criar os métodos **Getters** (`getColor()`, `getModel()`, `getPrice()`) e **Setters** (`setColor()`, `setModel()`, `setPrice()`).
