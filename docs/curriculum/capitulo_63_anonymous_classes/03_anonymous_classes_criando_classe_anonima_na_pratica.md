# Curso de Java Completo (Bro Code) — Capítulo 63: `anonymous classes 🕵️‍♂️` (Parte 3)
## Criando uma Classe Anônima na Prática: Sintaxe, `@Override` e Execução no Console

> **Contexto no Vídeo**: Entre os tempos **`10:23:23`** e **`10:24:30`**, o instrutor **Bro Code** conclui a mágica central do **Capítulo 63**:  
> 1. Ele confirma a exclusão do arquivo `TalkingDog.java` clicando no botão **`Delete Anyway`** da caixa de diálogo do IntelliJ IDEA (mesmo com o aviso de que havia 2 usos no código).  
> 2. De volta ao arquivo `Main.java`, ele renomeia o cachorro original para `dog1`.  
> 3. Em seguida, ele cria `dog2` usando a **sintaxe de uma Classe Anônima**:  
>    `Dog dog2 = new Dog() { ... };`  
> 4. Dentro das chaves `{ }`, ele insere a anotação `@Override` e reescreve o método `speak()` para dizer `"Scooby Doo says *Ruh Roh*"`.  
> 5. Ele executa o programa e obtém **o mesmo resultado exato de antes**, mas com uma enorme vitória de engenharia: **sem precisar de nenhum arquivo extra no projeto!**

---

## 📸 Reconstituição Visual Completa das 5 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 5 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: Alerta do IntelliJ: 'Usages Detected' (Tempo do Vídeo: 10:23:23)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:23:23 / 12:00:00    63.anonymous classes 🕵️‍♂️ >

Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Dog
      ├── ☕ Main
      ├── ☕ TalkingDog (Alvo da exclusão)

Editor ao Fundo (TalkingDog.java escurecido pela janela modal):
 1: public class TalkingDog extends Dog{
 2: 
 3:     @Override
 4:     void speak(){
 5:         System.out.println("Scooby Doo says *Ruh Roh*");
 6:     }
 7: }

Caixa de Diálogo Flutuante do IntelliJ IDEA (Usages Detected):
  ┌─────────────────────────────────────────────────────────────┐
  │ 📋 Usages Detected                                       ✕  │
  ├─────────────────────────────────────────────────────────────┤
  │                                                             │
  │   The following problems were found:                        │
  │   Class TalkingDog has 2 usages that are not safe to delete.│
  │                                                             │
  │                                                             │
  │                                                             │
  │   ┌─────────────┐       ┌───────────────┐   ┌────────────┐  │
  │   │ View Usages │       │ Delete Anyway │   │   Cancel   │  │
  │   └─────────────┘       └───────────────┘   └────────────┘  │
  │                                 ▲                           │
  │                         [Cursor do Mouse]                   │
  └─────────────────────────────────────────────────────────────┘

Análise Visual da Imagem 1:
- O IntelliJ percebeu que o arquivo `Main.java` ainda está usando `TalkingDog` em 2 lugares (na declaração e na chamada de método).
- Ele avisa: "Class TalkingDog has 2 usages that are not safe to delete" (A classe TalkingDog tem 2 usos que não são seguros para deletar).
- O autor passa o mouse sobre o botão "Delete Anyway" (Deletar Mesmo Assim) porque planeja reescrever o `Main.java`.
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: O Clique em 'Delete Anyway' (Exclusão Definitiva de TalkingDog.java)
====================================================================================================
Zoom no Modal 'Usages Detected':
  ┌─────────────────────────────────────────────────────────────┐
  │ 📋 Usages Detected                                       ✕  │
  ├─────────────────────────────────────────────────────────────┤
  │   The following problems were found:                        │
  │   Class TalkingDog has 2 usages that are not safe to delete.│
  │                                                             │
  │   ┌─────────────┐       ┌───────────────┐   ┌────────────┐  │
  │   │ View Usages │       │ Delete Anyway │   │   Cancel   │  │
  │   └─────────────┘       └───────────────┘   └────────────┘  │
  │                                 ▲                           │
  │                     [Ponteiro Clicando Firme]               │
  └─────────────────────────────────────────────────────────────┘

Análise Visual da Imagem 2:
- O autor clica no botão "Delete Anyway".
- O arquivo `TalkingDog.java` é imediatamente apagado do disco rígido e removido da árvore do projeto.
- A aba `TalkingDog.java` fecha automaticamente no editor.
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: O Esqueleto da Sintaxe da Classe Anônima em Main.java (Tempo: 10:24:06)
====================================================================================================
Painel de Arquivos (Esquerda):
  📁 src
      ├── ☕ Dog
      ├── ☕ Main     <-- [Note que TalkingDog sumiu por completo!]

Abas no Editor: [ ☕ Main.java ✕ (Ativa) ] [ ☕ Dog.java ]

Editor de Código (Main.java):
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
11:             |  <-- [Cursor piscando dentro das chaves da Classe Anônima]
12:         };
13: 
14:         dog.speak();  <-- [A palavra 'dog' está em vermelho com aviso de erro]
15: 
16:     }
17: }

Análise Visual da Imagem 3:
- Linha 9: O autor renomeou `dog` para `dog1`.
- Linha 10: Ele cria `Dog dog2 = new Dog(){`.
- Linha 12: Ele fecha a chave e coloca ponto-e-vírgula: `};`.
- Linha 14: `dog.speak()` está sublinhado em vermelho porque a variável antiga `dog` não existe mais (agora se chama `dog1`).
- O cursor de digitação está na linha 11, entre as chaves `{ }`. É aqui dentro que o corpo da classe anônima será escrito!
====================================================================================================
```

```
====================================================================================================
IMAGEM 4: Implementação Completa do '@Override void speak()' Dentro da Classe Anônima (Tempo: 10:24:15)
====================================================================================================
Editor de Código (Main.java):
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
17:         dog1.speak();  <-- [Texto selecionado em azul na tela]
18: 
19:     }
20: }

Análise Visual da Imagem 4:
- Dentro das chaves de `new Dog(){ ... };`, o autor escreveu:
  - Linha 11: `@Override`
  - Linha 12: `void speak(){` (com ícone ⎘ na margem do IntelliJ confirmando a sobrescrita de método)
  - Linha 13: `System.out.println("Scooby Doo says *Ruh Roh*");`
- Linha 17: O erro antigo foi consertado! Ele corrigiu para `dog1.speak();`.
====================================================================================================
```

```
====================================================================================================
IMAGEM 5: Chamada de Ambos os Métodos e Saída Verde no Console (Tempo: 10:24:30)
====================================================================================================
Editor de Código (Linhas 16 a 20):
16: 
17:         dog1.speak();
18:         dog2.speak();
19:     }
20: }

Terminal / Run Console (Inferior):
  C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ..."
  
  The dog goes *woof*
  Scooby Doo says *Ruh Roh*
  
  Process finished with exit code 0

Análise Visual da Imagem 5:
- O autor adicionou a linha 18 chamando `dog2.speak();`.
- Ele executou o programa.
- O console exibe perfeitamente em verde as duas linhas:
  1ª linha: "The dog goes *woof*" (emitido por dog1, cão normal)
  2ª linha: "Scooby Doo says *Ruh Roh*" (emitido por dog2, cão da classe anônima)
====================================================================================================
```

---

## 🔬 A Anatomia de uma Classe Anônima (Dessecando a Sintaxe)

Observe com atenção a linha 10 até a linha 15 de [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java):

```java
Dog dog2 = new Dog(){
    @Override
    void speak(){
        System.out.println("Scooby Doo says *Ruh Roh*");
    }
};
```

Para um iniciante, essa linha parece muito estranha porque mistura **instanciação (`new`)** com **declaração de classe (`{ ... }`)**. Vamos quebrar em 4 partes simples:

### 1. A Referência e o Construtor: `Dog dog2 = new Dog()`
- `Dog dog2`: Estamos dizendo ao compilador: *"Crie uma caixinha chamada `dog2` capaz de apontar para qualquer objeto da família `Dog`"*.
- `= new Dog()`: Normalmente, isso criaria um cachorro comum e colocaria um ponto-e-vírgula `;` logo no final.

### 2. A Mágica das Chaves `{ ... }`: Abertura da Classe Anônima
- Em vez de colocar o ponto-e-vírgula imediatamente após `new Dog()`, nós abrimos uma chave `{`.
- Essa chave diz ao Java: *"Espere! Eu não quero um `Dog` comum e genérico. Eu quero criar uma nova classe sem nome (anônima) que é filha de `Dog`, e vou escrever o código dela aqui dentro agora mesmo!"*.

### 3. A Sobrescrita: `@Override void speak()`
- Como essa classe anônima é uma filha direta de `Dog`, ela herda o método `speak()`.
- Usamos `@Override` e redefinimos o método para imprimir `"Scooby Doo says *Ruh Roh*"`.

### 4. Por Que Termina com `};` (Chave + Ponto-e-Vírgula)?
- A chave `}` fecha o corpo da classe anônima.
- O ponto-e-vírgula `;` finaliza a instrução de atribuição da variável `dog2` que começou lá na linha `Dog dog2 = ...`.
- Toda instrução em Java que declara e atribui uma variável termina com `;`. Como a classe anônima está dentro de uma atribuição, a linha fecha com `};`.

---

## 🕵️ O Segredo da JVM: O Arquivo Invisível `Main$1.class`

Mesmo que nós tenhamos deletado o arquivo `TalkingDog.java`, como o Java consegue rodar esse código se não demos um nome para a classe?

Quando o compilador do Java (`javac`) lê o nosso código com classe anônima, ele faz um truque nos bastidores:
1. Ele compila `Main.java` e gera `Main.class`.
2. Ele percebe que dentro de `Main` existe uma classe sem nome.
3. O Java cria automaticamente um arquivo binário chamado:
   ```text
   Main$1.class
   ```
4. O caractere `$` significa *"classe interna"*, e o número `1` significa *"a primeira classe anônima encontrada neste arquivo"*. Se você criasse outra classe anônima no mesmo arquivo, ela seria compilada como `Main$2.class`!

> [!TIP]
> **Benefício para o Programador**:  
> Você não precisou inventar um nome de arquivo, não precisou criar `TalkingDog.java`, e seu projeto ficou muito mais limpo e organizado!

---

## 🔍 Explicação Linha por Linha do Arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java)

Abaixo está o código final do arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) presente nas Imagens 4 e 5:

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
19:     }
20: }
```

- **Linha 1 (`public class Main {`)**: Declaração da classe pública principal do projeto.
- **Linha 2 (`public static void main(String[] args) {`)**: Ponto de entrada oficial da JVM para iniciar a execução do programa.
- **Linhas 3 e 4**: Espaçamento em branco.
- **Linhas 5 a 7**: Comentários didáticos conceituais explicando que uma classe anônima não tem nome, não pode ser reutilizada, adiciona comportamento sob medida e é muito usada em tarefas de uso único (`TimerTask`, `Runnable`, `callbacks`).
- **Linha 8**: Linha em branco.
- **Linha 9 (`Dog dog1 = new Dog();`)**:
  - Instancia um cachorro padrão da classe base [`Dog.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Dog.java).
  - Aloca memória no Heap com as características comuns da espécie canina.
- **Linha 10 (`Dog dog2 = new Dog(){`)**:
  - Declara a variável `dog2` do tipo `Dog`.
  - Começa a instanciação com `new Dog()`, mas imediatamente abre uma chave `{` para declarar a **Classe Anônima**.
- **Linha 11 (`@Override`)**:
  - Anotação que garante ao compilador que estamos sobrescrevendo o método `speak()` herdado de `Dog`.
- **Linha 12 (`void speak(){`)**:
  - Assinatura do método sobrescrito. Abre o corpo da nova ação.
- **Linha 13 (`System.out.println("Scooby Doo says *Ruh Roh*");`)**:
  - A ação personalizada exclusiva de `dog2`: imprimir no console a famosa fala do Scooby-Doo!
- **Linha 14 (`}`)**:
  - Fecha o corpo do método `speak()`.
- **Linha 15 (`};`)**:
  - Fecha a classe anônima (`}`) e conclui a instrução de atribuição da variável com o ponto-e-vírgula (`;`).
- **Linha 16**: Linha em branco para separar a criação dos objetos da execução dos seus métodos.
- **Linha 17 (`dog1.speak();`)**:
  - Executa o método `speak()` no objeto `dog1`.
  - Como `dog1` é um `Dog` comum, a JVM busca o método original em `Dog.java` e imprime: `"The dog goes *woof*"`.
- **Linha 18 (`dog2.speak();`)**:
  - Executa o método `speak()` no objeto `dog2`.
  - Como `dog2` pertence à subclasse anônima (`Main$1`), o polimorfismo do Java executa a versão sobrescrita e imprime: `"Scooby Doo says *Ruh Roh*"`.
- **Linha 19 (`}`)**: Fecha o método `main`.
- **Linha 20 (`}`)**: Fecha a classe `Main`.

---

## 📊 Tabela de Execução e Previsão do Console (Imagem 5)

| Linha | Código Executado | Objeto e Tipo Real | Saída Gerada no Terminal |
| :---: | :--- | :--- | :--- |
| **17** | `dog1.speak();` | `dog1` → Instância da classe base `Dog` | `The dog goes *woof*` |
| **18** | `dog2.speak();` | `dog2` → Instância da classe anônima `Main$1` | `Scooby Doo says *Ruh Roh*` |

### Saída Completa Exibida no Console:
```text
The dog goes *woof*
Scooby Doo says *Ruh Roh*

Process finished with exit code 0
```

---

## ⚖️ Comparação Definitiva: Por Que as Classes Anônimas Venceram Aqui?

| Critério de Comparação | Abordagem Antiga (Parte 2: `TalkingDog.java`) | Abordagem Moderna (Parte 3: Classe Anônima) |
| :--- | :--- | :--- |
| **Quantidade de Arquivos `.java`** | 2 arquivos (`Dog.java` e `TalkingDog.java`) | **Apenas 1 arquivo base** (`Dog.java`) |
| **Poluição do Projeto** | Alta. Cria arquivos para classes usadas uma só vez. | **Zero**. Tudo fica contido no próprio local de uso. |
| **Trabalho do Desenvolvedor** | Criar arquivo, dar nome, estender, salvar, importar. | **Escrever direto no `new Dog(){ ... };`**. |
| **Reutilização** | Pode ser instanciado em outros lugares. | **Não pode ser reutilizado** (ideal para uso único!). |
| **Saída no Console** | `Scooby Doo says *Ruh Roh*` | `Scooby Doo says *Ruh Roh*` (Idêntica!). |
