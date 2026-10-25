# Curso de Java — Capítulo 50: Polimorfismo (`polymorphism 🎭`)
## Parte 3: O Segundo Filho Chega à Família! — Criando `Bike.java` (Bicicleta), Estendendo `Vehicle` e Sobrescrevendo `go()` com `"You ride the bike"`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `08:09:28` até `08:09:43` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a criação da segunda classe filha concreta da nossa hierarquia de veículos: a classe `Bike.java`. Veremos o momento em que o professor cria o novo arquivo Java, herda de `Vehicle.java` usando `extends`, copia a estrutura de [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java), ajusta a mensagem semântica no método `go()` de *"dirigir"* (`drive`) para *"pedalar/andar"* (`ride`), e consolida mais um degrau rumo à demonstração de Polimorfismo.  
> **Formato para Google Docs:** Todo o contexto visual do IntelliJ IDEA (janela flutuante "New Java Class", árvore do projeto, abas abertas, ícones de sarjeta `(I)↑` e legendas do vídeo) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral das 3 Capturas de Tela do Vídeo (Para o Google Docs)

Nesta sequência rápida (`08:09:28` a `08:09:43`), o professor expande a família de veículos adicionando a bicicleta:

### 🖼️ Imagem 1 (`media_1791399849045_5e892fd4.png` — 08:09:28): Criando a Classe `Bike`
* **Aba ativa antes da ação:** [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java).
* **O que acontece:** O professor abre a janela modal de atalho do IntelliJ IDEA para criar uma nova classe.
* **Janela Modal "New Java Class":**
  * Campo de texto: a letra `B` digitada (o autor está no meio da digitação da palavra `Bike`).
  * Opção selecionada em azul: `Class` (Classe).
* **Legenda no rodapé do vídeo:** Uma palavra grande em estilo cartoon branco com borda preta diz: **`Bike`** (Bicicleta/Moto).

---

### 🖼️ Imagem 2 (`media_1791399861398_7906d71b.png` — 08:09:38): Estruturando `Bike.java` com a Base do Carro
* **Aba ativa:** `Bike.java`.
* **Abas abertas no topo do IntelliJ:** `Main.java`, `Vehicle.java`, `Car.java`, `Bike.java` (ativa).
* **Árvore do Projeto (à esquerda em `src`):**
  * `Bike` (ícone azul `(C)`)
  * `Car` (ícone azul `(C)`)
  * `Main` (ícone azul `(C)`)
  * `Vehicle` (ícone azul `(C)`)
* **Código em `Bike.java` durante a edição:**
  ```java
  public class Bike extends Vehicle{

      @Override
      void go(){
          System.out.println("You drive the car");
      }
  }
  ```
* **O que está acontecendo aqui:** O professor colou ou escreveu rapidamente a mesma estrutura do carro. O cursor de texto vertical está posicionado dentro da palavra `car` na linha 5. Ele vai apagar `"You drive the car"` e escrever a frase correta para uma bicicleta!

---

### 🖼️ Imagem 3 (`media_1791399872046_0ef1b0e1.png` — 08:09:43): A Frase Própria da Bicicleta!
* **Aba ativa:** `Bike.java`.
* **Código final ajustado:**
  ```java
  public class Bike extends Vehicle{

      @Override
      void go(){
          System.out.println("You ride the bike");
      }
  }
  ```
* **Destaques Visuais da IDE:**
  * Linha 3: A anotação `@Override` está selecionada/em foco.
  * Linha 4: À esquerda de `void go()`, aparece o ícone verde `(I)↑` (comprovando que o método cumpre perfeitamente o contrato do método abstrato `go()` herdado de `Vehicle`).
  * Linha 5: A frase agora é `"You ride the bike"` (*Você anda de bicicleta / Você pedala a bicicleta*).

---

## 2. A Analogia Didática: Por que Cada Veículo se Move de um Jeito?

Observe a árvore da família que estamos construindo:

```
                     +---------------------------------+
                     |         VEÍCULO (Pai)           |
                     |       (abstract class)          |
                     |       abstract void go();       |
                     +---------------------------------+
                                      |
                     +----------------+----------------+
                     |                                 |
                     v (extends)                       v (extends)
       +----------------------------+    +----------------------------+
       |        CARRO (Car)         |    |      BICICLETA (Bike)      |
       | Como faz go()?             |    | Como faz go()?             |
       | -> "You drive the car"     |    | -> "You ride the bike"     |
       +----------------------------+    +----------------------------+
```

1. **A Ordem do Pai (`Vehicle`):**  
   O pai `Vehicle` é categórico:  
   > *"Todos os meus filhos devem ter um método chamado `go()`. Nenhum filho meu pode existir sem saber se movimentar!"*
2. **A Resposta do Carro (`Car`):**  
   O carro responde:  
   > *"Pai, eu me movimento sendo dirigido por um motorista com volante e pedais: `You drive the car`!"*
3. **A Resposta da Bicicleta (`Bike`):**  
   A bicicleta responde:  
   > *"Pai, eu me movimento sendo pedalada com guidão e equilíbrio: `You ride the bike`!"*
4. **Por que isso é Polimorfismo?**  
   Ambos respondem à **mesma ordem** (`go()`), mas cada um age de forma **completamente diferente** conforme a sua própria natureza! Isso é o coração do Polimorfismo (*Muitas Formas de responder à mesma mensagem*).

---

## 3. Dissecação Linha por Linha: `Bike.java`

Vejamos o código completo de `Bike.java`:

```java
1:  public class Bike extends Vehicle{
2:  
3:      @Override
4:      void go(){
5:          System.out.println("You ride the bike");
6:      }
7:  }
```

### Explicação Passo a Passo de Cada Linha:

* **Linha 1 (`public class Bike extends Vehicle{`):**
  * `public class Bike`: Declara a classe pública chamada `Bike` (Bicicleta).
  * `extends`: Palavra de herança em Java. Conecta a classe `Bike` à classe mãe `Vehicle`.
  * `Vehicle`: A superclasse abstrata que fornece as regras gerais.
  * `{`: Abre o corpo da classe.
  * **Relação Formal de Tipos:** `Bike IS-A Vehicle` (*Bicicleta É-UM Veículo*).
  * **Saída no Console:** Nenhuma.

* **Linha 2 (Linha em branco):**
  * Linha vazia para deixar o código legível e limpo.
  * Na sarjeta surge um pequeno ponto amarelo sugerindo ações de contexto da IDE.
  * **Saída no Console:** Nenhuma.

* **Linha 3 (`    @Override`):**
  * Anotação sagrada que documenta: *"Atenção compilador! O método abaixo está sobrescrevendo o método abstrato `go()` herdado da superclasse `Vehicle`"*.
  * Se você escrever por engano `void goo()`, o compilador acusará erro imediatamente avisando que não existe `goo()` no pai para sobrescrever.
  * **Saída no Console:** Nenhuma.

* **Linha 4 (`    void go(){`):**
  * A definição do método concreto exigido pela classe mãe.
  * `void`: Não devolve nenhum número ou texto como resultado.
  * `go()`: Nome do método de locomoção.
  * Não precisa da palavra `public` porque na classe mãe foi declarado como `abstract void go();` (visibilidade padrão de pacote).
  * Na sarjeta à esquerda surge o ícone verde `(I)↑` comprovando que o contrato foi cumprido!
  * **Saída no Console:** Nenhuma na declaração.

* **Linha 5 (`        System.out.println("You ride the bike");`):**
  * Imprime no terminal a mensagem específica da bicicleta: `"You ride the bike"` (*Você anda de bicicleta*).
  * Em inglês, não se usa o verbo *drive* para bicicleta, e sim *ride* (andar a cavalo, moto ou bicicleta).
  * **Saída no Console (quando invocado no main):**
    ```text
    You ride the bike
    ```

* **Linha 6 (`    }`):**
  * Fecha o corpo do método `go()`.
  * **Saída no Console:** Nenhuma.

* **Linha 7 (`}`):**
  * Fecha a classe `Bike`.
  * **Saída no Console:** Nenhuma.

---

## 4. O Que Sai no Console se Executar o Projeto Agora?

Se você rodar o projeto pelo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java):

```text
Process finished with exit code 0
```

* **Por que ainda não imprimiu `"You ride the bike"`?**  
  Porque a classe `Bike` foi criada e compilada, mas o método `main` ainda não foi editado para criar o objeto `new Bike()` nem chamar `bike.go()`.
* **Código de saída `0`:** Confirma que todos os arquivos do projeto compilam com perfeição e sem nenhum erro de sintaxe.

---

## 5. Comparativo Visual dos Dois Filhos Concretos

Veja lado a lado a simetria elegante da arquitetura criada até agora:

| Aspecto | [`Car.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/oop/Car.java) | `Bike.java` |
| :--- | :--- | :--- |
| **Declaração** | `public class Car extends Vehicle` | `public class Bike extends Vehicle` |
| **Superclasse** | `Vehicle` | `Vehicle` |
| **Anotação** | `@Override` | `@Override` |
| **Método Implementado** | `void go()` | `void go()` |
| **Mensagem Impressa** | `"You drive the car"` | `"You ride the bike"` |
| **Identidade Polimórfica** | Pode ser tratado como `Vehicle` | Pode ser tratado como `Vehicle` |

---

## 6. O Próximo Passo: A Chegada do Terceiro Irmão (`Boat.java`)

Agora nós temos:
* Terrestre com 4 rodas: `Car`
* Terrestre com 2 rodas: `Bike`

Para fechar o trio clássico de meios de transporte, o professor Bro Code vai criar a seguir o veículo aquático:
* 🚤 **`Boat.java`** (Barco) $\rightarrow$ que também herdará de `Vehicle` e implementará `go()` dizendo `"You sail the boat"` ou similar!

Assim que o barco estiver pronto, o professor voltará ao `Main.java` para juntar os três em um único array polimórfico!

Todos os arquivos no seu computador já estão atualizados, sincronizados e testados com sucesso total! 🚀
