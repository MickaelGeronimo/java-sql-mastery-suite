# Curso de Java Completo (Bro Code) — Capítulo 63: `anonymous classes 🕵️‍♂️` (Parte 4)
## Revisão Conceitual, o Destaque em `custom behavior` e Conclusão do Capítulo

> **Contexto no Vídeo**: Entre os tempos **`10:24:30`** e **`10:24:59 / 12:00:00`**, o instrutor **Bro Code** conclui com chave de ouro o **Capítulo 63: `anonymous classes 🕵️‍♂️`**.  
> Ele pausa o vídeo (`10:24:59`) e usa o cursor do mouse para **selecionar em azul as palavras `"custom behavior"`** no comentário da linha 6.  
> Ele conecta a teoria à prática: a expressão *"adicionar comportamento customizado sem precisar criar uma nova classe"* acabou de ser provada na tela! O cachorro `dog2` latiu como o Scooby-Doo sem que nenhuma classe `TalkingDog.java` existisse no projeto!

---

## 📸 Reconstituição Visual Completa das 2 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 2 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Confirmação da Execução e Cursor em 'dog2' (Tempo do Vídeo: 10:24:30)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:24:30 / 12:00:00    63.anonymous classes 🕵️‍♂️ >

Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Dog
      └── ☕ Main     <-- [Apenas os 2 arquivos essenciais no projeto!]

Editor de Código (Linhas 16 a 20 de Main.java):
16: 
17:         dog1.speak();
18:         dog2.speak();  <-- [Cursor de texto posicionado após 'dog2']
19:     }
20: }

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  The dog goes *woof*
  Scooby Doo says *Ruh Roh*
  
  Process finished with exit code 0

Análise Visual da Imagem 1:
- O autor acabou de rodar o programa com o botão verde Run.
- O console exibe em fonte verde brilhante as duas saídas:
  1. "The dog goes *woof*" (executado por dog1 a partir de Dog.java)
  2. "Scooby Doo says *Ruh Roh*" (executado por dog2 a partir da Classe Anônima)
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: O Destaque em 'custom behavior' e Fechamento do Capítulo (Tempo: 10:24:59)
====================================================================================================
Centro da Tela do YouTube:
  Grande ícone preto semitransparente de PAUSA [ ❚❚ ] sobreposto ao editor.

Barra de Progresso do YouTube (Rodapé):
  [ ▶ Reproduzir K ]  10:24:59 / 12:00:00    63.anonymous classes 🕵️‍♂️ >
  (A bolinha vermelha da linha do tempo atingiu o final do capítulo 63).

Editor de Código Completo (Main.java):
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4: 
 5:         // Anonymous class = A class that doesn't have a name. Cannot be reused.
 6:         //                   Add [custom behavior] without having to create a new class.
 7:         //                   Often used for one time uses (TimerTask, Runnable, callbacks)
 8: 
 9:         Dog dog1 = new Dog();
10:         Dog dog2 = new Dog(){
11:             @Override
12: ⎘          void speak(){
13:                 System.out.println("Scooby Doo says *Ruh Roh*");
14:             }
15:         };
16: 
17:         dog1.speak();
18:         dog2.speak();
19: 
20:     }
21: }

Destaque Visual Fundamental da Imagem 2:
- Na linha 6, o autor usou o mouse e SELECIONOU EM AZUL as palavras "custom behavior".
- Na margem esquerda da linha 6, o IntelliJ exibe um ícone amarelo de lâmpada 💡 (dica de inspeção de código).
- O autor congela o vídeo aqui para fixar na mente do aluno:
  "Viram? Foi exatamente isso que fizemos! Criamos um comportamento sob medida (custom behavior) para o dog2, direto no main(), sem precisar poluir o projeto com uma nova classe!"
====================================================================================================
```

---

## 💡 Por Que o Autor Destacou as Palavras *"custom behavior"*?

Para um programador profissional, essa é a essência das **Classes Anônimas**:

### 1. O Que Significa "Custom Behavior" (Comportamento Customizado)?
- No Java, todo objeto criado com `new Dog()` tem o **mesmo comportamento de fábrica**: ele emite `"The dog goes *woof*"`.
- Se você tem 1.000 cachorros normais, todos latem igual.
- Mas imagine que você precisa de **um único cachorro** no seu sistema que precise agir de maneira especial (ex: avisar um alarme, falar como Scooby-Doo ou emitir um som silencioso).
- Em vez de alterar a classe `Dog` (o que mudaria o som de todos os outros cachorros!) ou criar um arquivo novo `CachorroAlarme.java`, você cria um objeto com **comportamento customizado** no local exato onde ele é necessário!

```
                    ┌────────────────────────┐
                    │    Classe Base Dog     │
                    │   speak() -> *woof*    │
                    └───────────┬────────────┘
                                │
        ┌───────────────────────┴────────────────────────┐
        ▼                                                ▼
┌──────────────────┐                           ┌──────────────────┐
│  Objeto dog1     │                           │  Objeto dog2     │
│  (Uso Padrão)    │                           │  (Classe Anônima)│
│                  │                           │                  │
│  Fala: *woof*    │                           │  Fala: *Ruh Roh* │
└──────────────────┘                           └──────────────────┘
```

### 2. A Tríade da Definição Confirmada na Prática:
1. **"Doesn't have a name"**: O código do `dog2` não tem nome como `class Scooby`. É apenas `new Dog() { ... }`.
2. **"Cannot be reused"**: Se você quiser outro cachorro igual ao `dog2` lá na linha 50, você não consegue fazer `new Dog2()`. Você teria que reescrever a classe anônima. Por isso ela é para **uso único** (*one-time use*).
3. **"Add custom behavior without having to create a new class"**: `TalkingDog.java` foi para a lixeira, mas o som do Scooby-Doo continuou funcionando perfeitamente!

---

## 🔍 Explicação Linha por Linha de Todo o Código

### Arquivo 1: `Dog.java` *(A Classe Base Permanente)*

```java
1: public class Dog {
2: 
3:     void speak(){
4:         System.out.println("The dog goes *woof*");
5:     }
6: }
```

- **Linha 1 (`public class Dog {`)**: Define a classe base pública `Dog`. Permanece no projeto como o modelo comum de todos os cães.
- **Linha 2**: Espaço em branco.
- **Linha 3 (`void speak(){`)**: Método padrão compartilhado por qualquer cão que não seja customizado.
- **Linha 4 (`System.out.println("The dog goes *woof*");`)**: A ação padrão de latir.
- **Linhas 5 e 6 (`}`)**: Fecham o método e a classe.

---

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) *(O Arquivo Executável)*

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4: 
 5:         // Anonymous class = A class that doesn't have a name. Cannot be reused.
 6:         //                   Add custom behavior without having to create a new class.
 7:         //                   Often used for one time uses (TimerTask, Runnable, callbacks)
 8: 
 9:         Dog dog1 = new Dog();
10:         Dog dog2 = new Dog(){
11:             @Override
12:             void speak(){
13:                 System.out.println("Scooby Doo says *Ruh Roh*");
14:             }
15:         };
16: 
17:         dog1.speak();
18:         dog2.speak();
19: 
20:     }
21: }
```

- **Linha 1 (`public class Main {`)**: Declaração da classe principal.
- **Linha 2 (`public static void main(String[] args) {`)**: Ponto de partida do programa.
- **Linhas 3 e 4**: Espaços em branco estéticos.
- **Linhas 5 a 7**: O bloco de documentação do Bro Code resumindo as 3 características fundamentais das classes anônimas.
- **Linha 8**: Espaço em branco.
- **Linha 9 (`Dog dog1 = new Dog();`)**:
  - Instancia um cachorro comum.
  - Na memória Heap, cria uma instância limpa da classe `Dog`.
- **Linha 10 (`Dog dog2 = new Dog(){`)**:
  - Instancia um cachorro modificado.
  - A abertura da chave `{` avisa ao compilador Java: *"Aqui começa a definição de uma classe filha anônima"*.
- **Linha 11 (`@Override`)**:
  - Anotação de segurança que confirma a substituição do método herdado.
- **Linha 12 (`void speak(){`)**:
  - Redefine a ação de falar especificamente para o objeto `dog2`.
- **Linha 13 (`System.out.println("Scooby Doo says *Ruh Roh*");`)**:
  - Imprime a frase customizada do Scooby-Doo no console.
- **Linha 14 (`}`)**: Fecha o método `speak()`.
- **Linha 15 (`};`)**: Fecha o corpo da classe anônima e encerra a instrução com ponto-e-vírgula (`;`).
- **Linha 16**: Espaço em branco.
- **Linha 17 (`dog1.speak();`)**:
  - Dispara o método `speak()` do `dog1`.
  - Saída no console: `"The dog goes *woof*"`.
- **Linha 18 (`dog2.speak();`)**:
  - Dispara o método `speak()` sobrescrito do `dog2`.
  - Saída no console: `"Scooby Doo says *Ruh Roh*"`.
- **Linhas 19 a 21 (`}`)**: Fecham o método `main` e a classe `Main`.

---

## 🖥️ Tabela de Saída do Console e Rastreamento de Execução

| Ordem | Linha em `Main.java` | Alvo da Chamada | O Que Acontece nos Bastidores | Texto Impresso no Console |
| :---: | :--- | :--- | :--- | :--- |
| **1º** | Linha 17: `dog1.speak();` | Objeto padrão `dog1` | A JVM busca o método na classe `Dog.class` e imprime a string padrão. | `The dog goes *woof*` |
| **2º** | Linha 18: `dog2.speak();` | Objeto anônimo `dog2` | O polimorfismo da JVM busca o método sobrescrito em `Main$1.class`. | `Scooby Doo says *Ruh Roh*` |

### Saída Completa no Console:
```text
The dog goes *woof*
Scooby Doo says *Ruh Roh*

Process finished with exit code 0
```

---

## 🚀 Resumo do Capítulo 63 e O Que Esperar a Seguir

Com esta lição concluída, você aprendeu:
1. **O que é**: Uma classe interna definida e instanciada em um único comando sem nome formal.
2. **Quando usar**: Sempre que precisar customizar o comportamento de um objeto para **uso único** sem justificar a criação de um arquivo `.java` separado.
3. **Como compila**: O Java gera nos bastidores um arquivo `Main$1.class`.
4. **Próximo passo na evolução do Java**:  
   No Java moderno (Java 8+), quando uma classe anônima ou interface possui **apenas um único método**, o Java nos permite encurtar ainda mais essa sintaxe usando **Expressões Lambda (`->`)**, que é outro tópico famoso nos cursos do Bro Code!

O Capítulo 63 está **100% finalizado com maestria**! 🎉
