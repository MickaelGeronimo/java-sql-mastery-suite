# Curso de Java — Capítulo 50: Polimorfismo (`polymorphism 🎭`)
## Parte 10: Recriando `Vehicle` como Interface e o Clássico Erro de Visibilidade em `Car.java` (`attempting to assign weaker access privileges`)

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:13:00` até `08:14:00` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo, como para quem nunca programou) a transição arquitetural do Polimorfismo: o professor deleta a classe abstrata `Vehicle.java`, clica em **Delete Anyway**, recria `Vehicle` como uma **Interface**, e ao tentar fazer a classe [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) implementar a nova interface (`implements Vehicle`), o IntelliJ IDEA sublinha a linha inteira de vermelho com **2 erros**! Vamos desvendar o maior mistério que confunde todo programador iniciante em Java: **o erro de privilégios de acesso enfraquecidos** (*"attempting to assign weaker access privileges; was public"*).  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (janelas modais, seleção do tipo `Interface`, ícone verde `(I)`, sublinhados vermelhos de erro e o código de cada arquivo) foi minuciosamente convertido em texto, diagramas ASCII/Mermaid e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 5 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência fundamental (`08:13:00` a `08:14:00`), o professor Bro Code prova na prática que **Polimorfismo não depende apenas de classes e herança (`extends`), mas funciona com maestria também através de Interfaces (`implements`)**:

| Imagem | Timestamp / Arquivo | O que é mostrado na tela do IntelliJ IDEA? | Ação Realizada pelo Professor Bro Code |
| :--- | :--- | :--- | :--- |
| **🖼️ Imagem 1** | `08:13:10`<br>`media_1791400365210_25c84da2.png` | Janela modal flutuante **"Usages Detected"** alertando que `Vehicle` tem 5 dependências no projeto. | O mouse clica no botão escuro **`Delete Anyway`** (Deletar Mesmo Assim), destruindo a classe abstrata antiga. |
| **🖼️ Imagem 2** | `08:13:34`<br>`media_1791400397665_e3ba1e8b.png` | O editor [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) fica em vermelho no fundo (`Cannot resolve symbol 'Vehicle'`). Abre o menu **"New Java Class"**. | O cursor do mouse desce da opção `Class` e aponta para a opção **`Interface`** (com ícone verde `(I)`). |
| **🖼️ Imagem 3** | `08:13:38`<br>`media_1791400405881_0d34d914.png` | O menu foca na linha **`Interface`**, que ganha destaque azul escuro. | O ícone ao lado do campo de digitação muda para um círculo verde com a letra **I**. |
| **🖼️ Imagem 4** | `08:13:42`<br>`media_1791400413983_0334965f.png` | O professor digita o nome **`Vehicle`** no campo de texto da nova Interface. Legenda inferior: `"Vehicle"`. | Ele pressiona `Enter`, criando instantaneamente o arquivo `Vehicle.java` do tipo Interface! |
| **🖼️ Imagem 5** | `08:14:00`<br>`media_1791400425811_08ea96ce.png` | Editor aberto em [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java). Linha 1: `public class Car implements Vehicle{` com **sublinhado ondulado vermelho** e **2 erros** no canto superior direito! | O professor troca `extends Vehicle` por `implements Vehicle`, e a IDE acusa erro imediato porque o método `go()` não tem a palavra `public`! |

---

## 2. A Dissecação Visual: Da Janela de Deleção ao Erro Vermelho

### 2.1 Imagem 1: Confirmando a Morte da Classe Abstrata (`Delete Anyway`)
Na janela modal do IntelliJ IDEA:
```text
+-------------------------------------------------------------+
|  [x] Usages Detected                                    [X] |
+-------------------------------------------------------------+
|  The following problems were found:                         |
|  Class Vehicle has 5 usages that are not safe to delete.    |
|                                                             |
|                                                             |
|         [View Usages]    [ Delete Anyway ]*   [ Cancel ]    |
+-------------------------------------------------------------+
                            (* Cursor clica aqui!)
```
* **O que o professor pensou:** *"Eu sei que o projeto vai chiar e ficar temporariamente quebrado. Mas confia no processo: vamos reconstruir `Vehicle` do zero, agora como Interface!"*
* Ao clicar em `Delete Anyway`, o arquivo antigo `Vehicle.java` desaparece do disco.

---

### 2.2 Imagens 2, 3 e 4: O Nascimento da Interface `Vehicle`
Como `Vehicle` foi deletado, a linha 13 de [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) ficou em pânico:
$$\colorbox{#782626}{\textbf{Vehicle}}\texttt{[] vehicles = \{car, bike, boat\};}$$
O Java não sabe mais o que diabos é um `Vehicle`!

O professor clica com botão direito na pasta `src` $\rightarrow$ `New` $\rightarrow$ `Java Class`.
Aparece a caixinha mágica do IntelliJ:
```text
+------------------------------------+
|  (I) Vehicle                       |  <-- Digitou Vehicle com ícone verde (I)
+------------------------------------+
|  (C) Class                         |
| >(I) Interface<   [SELECIONADO]    |
|  (R) Record                        |
|  (E) Enum                          |
|  (@) Annotation                    |
|  (4) Exception                     |
+------------------------------------+
```
Ao apertar `Enter`, o IntelliJ gera o novo arquivo `Vehicle.java`:
```java
public interface Vehicle {
    void go();
}
```
> [!NOTE]
> Observe na árvore lateral do IntelliJ na Imagem 5: o arquivo `Vehicle` agora possui um **círculo verde com a letra 'I'** ao seu lado, enquanto `Bike`, `Boat`, `Car` e `Main` possuem um **círculo azul com a letra 'C'** (Classes normais).

---

### 2.3 Imagem 5: O Enigma do Sublinhado Vermelho em `Car.java`

O professor abre o arquivo [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) e altera a linha 1:
* **Antes (com classe abstrata):** `public class Car extends Vehicle{`
* **Agora (com interface):** `public class Car implements Vehicle{`

E de repente... **SURPRESA!** 🚨  
A linha 1 inteira fica com um sublinhado vermelho serrilhado sob `implements Vehicle`, e no canto superior direito da tela do IntelliJ aparece o alerta:
$$\colorbox{#5c0000}{\textbf{ 2 erros }} \quad \colorbox{#5c5c00}{\textbf{ 1 aviso }}$$

A tela exata do editor é esta:
```java
1 | public class Car implements Vehicle{  <-- [ERRO VERMELHO AQUI!]
2 | 
3 |     @Override
4 |     void go(){
5 |         System.out.println("You drive the car");
6 |     }
7 | }
```

---

## 3. Por Que Deu Erro? A Grande Regra dos Modificadores de Acesso do Java

Se você é iniciante em Java, este erro vai te pegar pelo menos umas dez vezes na sua carreira. Preste muita atenção na explicação abaixo porque ela vale ouro:

### 3.1 A Regra Secreta das Interfaces
Dentro de uma **Interface**, você não precisa escrever a palavra `public` nem `abstract` nos métodos. O Java faz isso por baixo dos panos!
```java
// O que você escreve:
public interface Vehicle {
    void go();
}

// O que o compilador do Java REALMENTE enxerga:
public interface Vehicle {
    public abstract void go();  // <-- É PUBLICO OBRIGATORIAMENTE!
}
```
Qualquer método declarado numa interface em Java é **automaticamente e implicitamente `public`**. Não existe método privado nem método de pacote em contratos de interface clássicos!

---

### 3.2 A Lei Suprema da Sobrescrita (`@Override`) do Java
Existe uma lei inviolável na linguagem Java para herança e implementação:
> **"Ao sobrescrever um método do pai (ou de uma interface), você NUNCA pode diminuir a visibilidade dele (atribuir privilégios mais fracos/restritos)!"**

Pense na visibilidade em Java como portas de uma casa, da mais aberta para a mais fechada:
```
+--------------------------------------------------------------+
| 1. PUBLIC           (Porta escancarada na rua, todo mundo vê)|
| 2. PROTECTED        (Porta da sala, só família e parentes)   |
| 3. PACKAGE-PRIVATE  (Porta do corredor, só quem mora na casa)| <-- Onde estava Car.go()
| 4. PRIVATE          (Cofre trancado no quarto)               |
+--------------------------------------------------------------+
```

1. Quando `Vehicle` era uma classe abstrata, o professor tinha escrito:
   ```java
   abstract void go(); // Estava sem public, ou seja: PACKAGE-PRIVATE
   ```
   Por isso, na classe `Car`, escrever `void go()` funcionava perfeitamente (package-private combinava com package-private).

2. Mas quando `Vehicle` virou uma **INTERFACE**:
   ```java
   public interface Vehicle {
       void go(); // AGORA É PUBLIC AUTOMATICAMENTE!
   }
   ```
3. Na classe `Car`, o método ainda estava escrito como:
   ```java
   void go(){ // OPA! Isso aqui é PACKAGE-PRIVATE (mais fechado que public)!
   ```
4. O compilador do Java grita com toda a força:
   ```text
   Car.java:4: error: go() in Car cannot implement go() in Vehicle
       void go(){
            ^
     attempting to assign weaker access privileges; was public
   ```
   **Tradução para português claro:**  
   *"Ei, `Car`! O contrato `Vehicle` disse que o método `go()` é PÚBLICO (todo mundo pode ver). Você não tem o direito de esconder esse método deixando ele como 'package-private'! Coloque a palavra `public` na frente do `void go()` agora mesmo!"*

E por que o IntelliJ marcou **2 erros**?
* **Erro 1:** `go()` em `Car` tem acesso mais fraco do que o `go()` público de `Vehicle`.
* **Erro 2:** Como o seu `void go()` não é aceito como uma implementação válida, para o Java a classe `Car` ainda não implementou o método obrigatório da interface, logo: *"Class 'Car' must either be declared abstract or implement abstract method 'go()' in 'Vehicle'"*.

---

## 4. O Código dos Arquivos Linha por Linha

Vamos analisar cada arquivo presente neste momento crítico.

### 4.1 `Vehicle.java` — A Nova Interface (Perfeita, Sem Erros)
```java
1 | public interface Vehicle {
2 |     void go();
3 | }
```
* **Linha 1 (`public interface Vehicle {`):**
  * `public`: Qualquer classe de qualquer pacote pode acessar esta interface.
  * `interface`: Palavra-chave que define um contrato 100% abstrato. Não é mais uma classe (`class`), não tem construtor, não pode ter `new Vehicle()`.
  * `Vehicle`: Nome da interface (com V maiúsculo).
  * `{`: Abre o bloco de especificações do contrato.
* **Linha 2 (`void go();`):**
  * `void`: Significa que a ação não retorna nenhum valor numérico ou texto após terminar.
  * `go()`: Nome do método obrigatório que todo veículo terá que ter.
  * `;`: Ponto e vírgula direto! Não tem chaves `{}` nem corpo de código.
  * **Pegadinha oculta do Java:** Mesmo sem escrever, o Java considera que essa linha é `public abstract void go();`.
* **Linha 3 (`}`):**
  * Fecha a interface.

---

### 4.2 [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) — O Estado com Erro (Como Está no Vídeo na Imagem 5)
```java
1 | public class Car implements Vehicle{
2 | 
3 |     @Override
4 |     void go(){
5 |         System.out.println("You drive the car");
6 |     }
7 | }
```
* **Linha 1 (`public class Car implements Vehicle{`):**
  * `public class Car`: A classe pública Carro.
  * `implements Vehicle`: Informa que `Car` assina o contrato da interface `Vehicle`.
  * ❌ **Sublinhado vermelho aqui:** O Java detecta que a classe ainda não tem uma implementação pública válida para o contrato.
* **Linha 2:** Linha em branco para legibilidade.
* **Linha 3 (`@Override`):**
  * Anotação informando ao compilador que estamos sobrescrevendo/implementando o método herdado.
* **Linha 4 (`void go(){`):**
  * ❌ **A Raiz do Problema:** Falta a palavra `public` aqui! Como está apenas `void`, o método é de pacote (*package-private*).
* **Linha 5 (`System.out.println("You drive the car");`):**
  * O código que deveria imprimir a mensagem do carro.
* **Linha 6 (`}`):**
  * Fecha o método `go()`.
* **Linha 7 (`}`):**
  * Fecha a classe `Car`.

---

### 4.3 A Correção que o Professor Fará no Próximo Segundinho!
Para o erro sumir instantaneamente e o sublinhado vermelho desaparecer, basta colocar `public` na Linha 4:

```java
1 | public class Car implements Vehicle{
2 | 
3 |     @Override
4 |     public void go(){  // <-- ADICIONOU 'public' AQUI! O ERRO SOME NA HORA!
5 |         System.out.println("You drive the car");
6 |     }
7 | }
```

---

## 5. Tabela Comparativa: `extends` (Classe Abstrata) vs `implements` (Interface)

Para fixar de vez no seu resumo do Google Docs:

| Característica | Com Classe Abstrata (`Vehicle`) | Com Interface (`Vehicle`) |
| :--- | :--- | :--- |
| **Palavra-chave na criação** | `public abstract class Vehicle` | `public interface Vehicle` |
| **Palavra-chave na filha** | `class Car extends Vehicle` | `class Car implements Vehicle` |
| **Visibilidade do método no pai** | A que você escolher (padrão, `protected` ou `public`) | **Sempre `public`** (mesmo se você omitir a palavra) |
| **Visibilidade obrigatória na filha** | Igual ou mais aberta que a do pai | **Obrigatoriamente `public`** |
| **O Polimorfismo funciona igual?** | **SIM!** `Vehicle[] vehicles = {car, bike, boat};` funciona idêntico! | **SIM!** `Vehicle[] vehicles = {car, bike, boat};` funciona idêntico! |
| **Herança Múltipla?** | Uma classe só pode herdar (`extends`) **uma** classe pai. | Uma classe pode implementar (`implements`) **várias** interfaces! |

---

## 6. O Que Deve Sair no Console?

Nesta etapa exata (Imagem 5):
* **Saída no Console:** **NENHUMA!**
* Se tentarmos rodar o programa agora com o botão de Play `▶`, o programa **NÃO VAI RODAR** e a aba inferior `Build` acusará o erro de compilação:
  ```text
  java: go() in Car cannot implement go() in Vehicle
    attempting to assign weaker access privileges; was public
  ```
* Assim que o professor adicionar `public` nos métodos `go()` de [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java), `Bike.java` e `Boat.java`, o projeto voltará a compilar com 0 erros e o console exibirá as mesmas 3 frases perfeitas!

---

## 7. Resumo Prático para o Seu Caderno de Estudos (Google Docs)

1. **Interfaces também geram Polimorfismo:** Polimorfismo não se importa se a "etiqueta comum" é uma Classe Abstrata ou uma Interface. Se `Car`, `Bike` e `Boat` implementam `Vehicle`, todos eles são aceitos no array `Vehicle[]`.
2. **Métodos de Interface são sempre públicos:** Em qualquer `interface`, todo método que você declara é implicitamente `public abstract`.
3. **Cuidado com o `@Override`:** Ao implementar um método de interface na classe concreta, você **é obrigado** a escrever `public void nomeDoMetodo()`. Se esquecer o `public`, o compilador barrará com o erro *"attempting to assign weaker access privileges"*.
4. **O IntelliJ é seu amigo protetor:** Ao tentar deletar um arquivo que ainda está sendo usado no projeto, o IntelliJ sempre vai te avisar com a caixa *"Usages Detected"*. Usar `Delete Anyway` só deve ser feito quando você tem certeza de que vai recriar aquele componente.
