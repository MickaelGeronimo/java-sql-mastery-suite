# Curso de Java Completo (Bro Code) — Capítulo 63: `anonymous classes 🕵️‍♂️` (Parte 2)
## A Abordagem Tradicional: Criando `TalkingDog.java`, Executando e Deletando o Arquivo

> **Contexto no Vídeo**: Entre os tempos **`10:21:51`** e **`10:23:00`**, o instrutor **Bro Code** faz uma demonstração didática brilhante:  
> Antes de mostrar como criar uma **Classe Anônima**, ele mostra a **forma tradicional (e trabalhosa)** que um programador costuma fazer quando quer customizar um cachorro.  
> 1. Ele cria uma subclasse normal com nome formal chamada **`TalkingDog.java`** que estende (`extends`) `Dog`.  
> 2. Sobrescreve (`@Override`) o método `speak()` para dizer `"Scooby Doo says *Ruh Roh*"`.  
> 3. Instancia ambos os cachorros em `Main.java` e executa no terminal.  
> 4. E então ele faz a pergunta de ouro: *"Vale mesmo a pena criar um arquivo `.java` inteiro no nosso projeto só para usar esse cachorro falante UMA ÚNICA VEZ?"*  
> 5. A resposta é **NÃO!** Ele abre o diálogo de exclusão do IntelliJ IDEA e **deleta a classe `TalkingDog`**, preparando o terreno para fazer a mesma coisa com uma **Classe Anônima**!

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 5 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Transição do Arquivo Dog.java de Volta para Main.java (Tempo: 10:21:51)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:21:51 / 12:00:00    63.anonymous classes 🕵️‍♂️ >

Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Dog
      ├── ☕ Main
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Abas Abertas no Editor:
  [ ☕ Main.java (Alvo do Clique) ] [ ☕ Dog.java ✕ (Ativa no momento) ]

Editor de Código (Dog.java):
 1: public class Dog {
 2: 
 3:     void speak(){
 4:         System.out.println("The dog goes *woof*");
 5:     }
 6: }

Análise Visual da Imagem 1:
- O autor acabou de salvar `Dog.java`.
- O cursor do mouse está clicando na aba `Main.java` para voltar ao método principal e planejar o teste.
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: Criação da Subclasse Nomeada 'TalkingDog.java' (Tempo: 10:22:31)
====================================================================================================
Barra Superior / Barra de Abas:
  [ ☕ Main.java ] [ ☕ Dog.java ] [ ☕ TalkingDog.java ✕ (Ativa) ]

Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Dog
      ├── ☕ Main
      ├── ☕ TalkingDog    <-- [NOVO ARQUIVO SEPARADO CRIADO NO DISCO]
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (TalkingDog.java):
 1: public class TalkingDog extends Dog{
 2: 
 3:     @Override
 4: ⎘  void speak(){
 5:         System.out.println("Scooby Doo says *Ruh Roh*");
 6:     }
 7: }
 8: 

Análise Visual da Imagem 2:
- O autor criou um novo arquivo físico `TalkingDog.java`.
- Linha 1 usa a palavra-chave `extends Dog` para herdar tudo de `Dog`.
- Linha 3 traz a anotação `@Override`.
- Linha 4 tem um ícone azul do IntelliJ na margem esquerda (⎘) indicando que este método sobrescreve o método `speak()` da classe pai `Dog`.
- O cursor de texto pisca após o ponto-e-vírgula da linha 5.
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: Instanciação dos Dois Objetos em Main.java e Clique no 'Run' (Tempo: 10:22:45)
====================================================================================================
Barra Superior Direita da Janela:
  Current File ▾ [ ▶️ Run (Play Verde Selecionado) ] [ 🐛 Debug ]

Editor de Código (Main.java):
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4: 
 5:         // Anonymous class = A class that doesn't have a name. Cannot be reused.
 6:         //                   Add custom behavior without having to create a new class.
 7:         //                   Often used for one time uses (TimerTask, Runnable, callbacks)
 8: 
 9:         Dog dog = new Dog();
10:         TalkingDog talkingDog = new TalkingDog();
11: 
12:         dog.speak();
13:         talkingDog.speak();
14: 
15:     }
16: }

Análise Visual da Imagem 3:
- O autor instancia o cachorro comum `dog` (linha 9) e o cachorro falante `talkingDog` (linha 10).
- Em seguida, chama `.speak()` em ambos (linhas 12 e 13).
- O ponteiro do mouse está sobre o botão triangular verde de "Run" (Play) no canto superior direito para compilar e rodar.
====================================================================================================
```

```
====================================================================================================
IMAGEM 4: Execução no Console Confirmando Ambos os Sons (Tempo: 10:22:59)
====================================================================================================
Painel Inferior (Run Console):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  The dog goes *woof*
  Scooby Doo says *Ruh Roh*
  
  Process finished with exit code 0

Análise Visual da Imagem 4:
- O programa executou perfeitamente sem nenhum erro.
- A primeira chamada `dog.speak()` imprimiu o latido padrão: "The dog goes *woof*".
- A segunda chamada `talkingDog.speak()` imprimiu a fala do Scooby Doo: "Scooby Doo says *Ruh Roh*".
====================================================================================================
```

```
====================================================================================================
IMAGEM 5: A Caixa de Diálogo 'Delete' Removendo TalkingDog.java (Tempo: 10:23:00)
====================================================================================================
Fundo da Janela:
  O código de `TalkingDog.java` está visível ao fundo, mas escurecido pelo modal.

Caixa de Diálogo Flutuante (Delete):
  ┌─────────────────────────────────────────────────────────────┐
  │ 🗑️ Delete                                                ✕  │
  ├─────────────────────────────────────────────────────────────┤
  │                                                             │
  │   Delete class "TalkingDog"?                                │
  │                                                             │
  │   ☑ Safe delete (with usage search)                         │
  │   ☐ Search in comments and strings                          │
  │   ☑ Search for text occurrences                             │
  │                                                             │
  │                 ┌────────┐    ┌────────┐                    │
  │                 │   OK   │    │ Cancel │                    │
  │                 └────────┘    └────────┘                    │
  │                     ▲                                       │
  │              [Cursor do Mouse]                              │
  └─────────────────────────────────────────────────────────────┘

Análise Visual da Imagem 5:
- O instrutor foi até o painel de projetos, clicou com o botão direito em `TalkingDog.java` e apertou Delete.
- O IntelliJ abriu a confirmação de exclusão segura (*Safe delete*).
- O cursor do mouse está posicionado exatamente sobre o botão azul **OK**.
- O autor explica que ter um arquivo inteiro para uma classe que só é chamada uma vez é um desperdício de arquivos no projeto.
====================================================================================================
```

---

## 🧐 O Que o Autor Demonstrou Aqui? (Explicação Para Leigos)

### 1. O Problema da Abordagem Tradicional
Imagine que você está escrevendo um joguinho ou um programa de cadastro. Você tem a classe `Dog`.  
De repente, você precisa de um cachorro especial (o Scooby-Doo) que fala *"Ruh Roh"*.  
Na orientação a objetos tradicional, para fazer isso você precisa:
1. Criar um novo arquivo físico: `TalkingDog.java`.
2. Fazer `TalkingDog extends Dog`.
3. Escrever a anotação `@Override`.
4. Reescrever o método `speak()`.
5. Salvar o arquivo no disco rígido.

Agora pergunte a si mesmo: **"Quantas vezes eu vou usar esse `TalkingDog` no meu programa inteiro?"**  
Se a resposta for: **"Apenas uma única vez nesta linha aqui"**, então parabéns: você acabou de **poluir o seu projeto com um arquivo `.java` a mais**, que agora terá que ser mantido, versionado no Git e guardado para sempre, mesmo sendo usado uma única vez!

### 2. A Solução do Bro Code: Deletar e Usar Classe Anônima!
Na **Imagem 5**, o autor clica em **OK** para apagar o arquivo `TalkingDog.java`.  
Ele diz na aula:  
> *"Isso funciona, mas se precisarmos desse cachorro falante apenas uma vez, é um incômodo criar uma classe totalmente nova em um arquivo separado. Então vamos deletar o `TalkingDog` e fazer a mesma coisa com uma classe anônima!"*

---

## 🔍 Explicação Linha por Linha do Código

### Arquivo 1: [`TalkingDog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/TalkingDog.java) *(Criado na Imagem 2 e Deletado na Imagem 5)*

```java
 1: public class TalkingDog extends Dog{
 2: 
 3:     @Override
 4:     void speak(){
 5:         System.out.println("Scooby Doo says *Ruh Roh*");
 6:     }
 7: }
```

- **Linha 1 (`public class TalkingDog extends Dog{`)**:
  - `public class TalkingDog`: Cria uma nova classe pública com o nome formal `TalkingDog`.
  - `extends Dog`: A palavra-chave da **herança** em Java. Significa que `TalkingDog` é um filho de `Dog`, herdando todas as características e métodos do pai.
  - `{`: Abre o bloco da classe.
- **Linha 2**: Linha em branco para clareza visual.
- **Linha 3 (`@Override`)**:
  - Uma **anotação** (*annotation*) do compilador Java.
  - Ela avisa formalmente à JVM: *"Atenção! Eu estou substituindo de propósito o comportamento do método `speak()` que herdei do meu pai `Dog`"*.
  - Se você errar a digitação do nome (por exemplo, escrever `speek()`), o `@Override` gerará um erro vermelho imediato, impedindo bugs bobos.
- **Linha 4 (`void speak(){`)**:
  - A assinatura idêntica do método herdado de `Dog`. Não retorna nada (`void`), se chama `speak` e não recebe argumentos `()`.
- **Linha 5 (`System.out.println("Scooby Doo says *Ruh Roh*");`)**:
  - O novo comportamento customizado! Em vez de emitir o `*woof*` padrão da classe `Dog`, este cachorro falante imprime a célebre frase do desenho animado: `"Scooby Doo says *Ruh Roh*"`.
- **Linha 6 (`}`)**: Fecha o método `speak()`.
- **Linha 7 (`}`)**: Fecha a classe `TalkingDog`.

---

### Arquivo 2: [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) *(Imagem 3)*

```java
 1: public class Main {
 2:     public static void main(String[] args) {
 3: 
 4: 
 5:         // Anonymous class = A class that doesn't have a name. Cannot be reused.
 6:         //                   Add custom behavior without having to create a new class.
 7:         //                   Often used for one time uses (TimerTask, Runnable, callbacks)
 8: 
 9:         Dog dog = new Dog();
10:         TalkingDog talkingDog = new TalkingDog();
11: 
12:         dog.speak();
13:         talkingDog.speak();
14: 
15:     }
16: }
```

- **Linhas 1 a 7**: A classe `Main`, método `main` e os comentários conceituais vistos na Parte 1.
- **Linha 8**: Linha em branco separadora.
- **Linha 9 (`Dog dog = new Dog();`)**:
  - `Dog dog`: Declara uma variável de referência chamada `dog`, cujo tipo é a classe pai `Dog`.
  - `= new Dog()`: O operador `new` aloca um novo bloco de memória no Heap e executa o construtor padrão da classe `Dog`.
  - Este é o cachorro comum, que se comporta exatamente como definido em `Dog.java`.
- **Linha 10 (`TalkingDog talkingDog = new TalkingDog();`)**:
  - `TalkingDog talkingDog`: Declara uma variável de referência chamada `talkingDog`, cujo tipo é a subclasse `TalkingDog`.
  - `= new TalkingDog()`: Aloca outro bloco de memória no Heap para o objeto da classe filha especializada.
- **Linha 11**: Linha em branco para separar a criação dos objetos da execução de suas ações.
- **Linha 12 (`dog.speak();`)**:
  - Chama o método `speak()` do objeto comum `dog`.
  - A JVM consulta a classe `Dog` e executa: `System.out.println("The dog goes *woof*");`.
  - O console recebe: `The dog goes *woof*`.
- **Linha 13 (`talkingDog.speak();`)**:
  - Chama o método `speak()` do objeto especializado `talkingDog`.
  - Como o método foi sobrescrito com `@Override`, o polimorfismo do Java ignora a implementação do pai e executa a da filha: `System.out.println("Scooby Doo says *Ruh Roh*");`.
  - O console recebe: `Scooby Doo says *Ruh Roh*`.
- **Linha 14**: Linha em branco.
- **Linha 15 (`}`)**: Fecha o método `main`.
- **Linha 16 (`}`)**: Fecha a classe `Main`.

---

## 📊 Tabela de Execução e Saída do Console (Imagem 4)

| Linha Executada em `Main.java` | Objeto Alvo | Classe Pertencente | Saída Exata Produzida no Console |
| :--- | :--- | :--- | :--- |
| **Linha 12**: `dog.speak();` | `dog` (Referência Heap #1) | `Dog.java` | `The dog goes *woof*` |
| **Linha 13**: `talkingDog.speak();` | `talkingDog` (Referência Heap #2) | `TalkingDog.java` | `Scooby Doo says *Ruh Roh*` |

### Texto Completo Exibido no Console:
```text
The dog goes *woof*
Scooby Doo says *Ruh Roh*

Process finished with exit code 0
```

---

## 🗑️ Análise da Exclusão da Classe (Imagem 5)

No diálogo do IntelliJ IDEA:
```text
Delete class "TalkingDog"?
[x] Safe delete (with usage search)
[x] Search for text occurrences
[ OK ]
```
1. **O que é o Safe Delete (Exclusão Segura)**:  
   O IntelliJ examina todo o projeto para ver se alguma outra classe ainda está usando `TalkingDog`. Como `Main.java` ainda tinha a linha `TalkingDog talkingDog = ...`, ao apagar `TalkingDog.java`, o IntelliJ avisará que há código quebrado ou o próprio programador terá que substituir a linha.
2. **Por que o autor faz isso**:  
   Para provar que **podemos obter exatamente a mesma saída (`Scooby Doo says *Ruh Roh*`) sem precisar que o arquivo `TalkingDog.java` exista!**
3. **O que vem a seguir**:  
   Na próxima cena, veremos o autor declarar a classe anônima diretamente na instanciação:  
   `Dog dog2 = new Dog() { @Override void speak() { ... } };`
