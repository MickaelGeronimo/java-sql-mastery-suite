# Curso de Java — Bro Code (12 Horas)
## Capítulo 69: `threading 🧵` (Parte 3 — A Criação da Classe `MyRunnable`, a Interface `Runnable` e o Método `run()`)

---

> [!NOTE]
> **Contexto para o seu Google Docs**: Este documento contém a reconstituição textual completa e minuciosa das **5 telas** exibidas pelo instrutor Bro Code no vídeo (entre os minutos **11:17:49** e **11:18:45** de 12:00:00).
> Você pode copiar e colar este material diretamente no seu **Google Docs**: a janela de criação de nova classe Java, a transição entre abas, o recorte do laço de `Main.java` e a estrutura de `MyRunnable.java` com `implements Runnable` e `@Override public void run()` foram desenhados em formato de texto para que sua apostila fique 100% autônoma.

---

## 1. O Que o Autor Fez Aqui? (História e Analogia para Iniciantes)

Na aula anterior (Parte 2), nós vimos que deixar o laço de espera dentro de `Main.java` foi um desastre: o cozinheiro chefe (`main`) congelou por 5 segundos olhando para o teto, gritou *"O tempo acabou!"* e só depois pediu para o cliente digitar o nome.

Para consertar isso de forma profissional, o Bro Code aplica a famosa **Divisão de Trabalho**:

### A Analogia da Contratação do Ajudante
1. **O Chefe Cansado (`Main.java`)**: O chefe percebe que não pode ficar cronometrando o tempo no relógio enquanto tenta atender o cliente no balcão.
2. **Criando o Crachá do Ajudante**:
   - O autor clica com o botão direito na pasta do projeto e cria um arquivo novinho em folha chamado **`MyRunnable.java`**.
3. **A Profissão do Ajudante (`implements Runnable`)**:
   - Ele dá a esse arquivo uma profissão oficial: `implements Runnable`.
   - Em inglês, *runnable* significa **"algo que pode ser executado/rodado"**. É o contrato do Java dizendo: *"Este arquivo é um trabalhador treinado para rodar tarefas em segundo plano!"*.
4. **O Manual de Instruções do Ajudante (`public void run()`)**:
   - Todo trabalhador `Runnable` é obrigado por lei a ter um método chamado `run()`.
   - O autor transfere todo o laço `for` com o `Thread.sleep(1000)` para dentro desse método `run()`.
5. **Limpando a Mesa do Chefe**:
   - O autor volta para o `Main.java` e **apaga** o laço `for` de lá!
   - Agora a mesa do `Main.java` está limpa e livre para focar exclusivamente em falar com o usuário!

---

## 2. Reconstituição Textual das 5 Telas do Vídeo (Google Docs Ready)

---

### Tela 1: Abrindo a Janela "New Java Class" no IntelliJ (Timestamp: 11:17:49)

O autor abre o menu de criação de um novo arquivo Java no projeto:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - Janela Flutuante "New Java Class"                            Timestamp 11:17:49 |
+--------------------------------------------------------------------------------------------------+
|                              New Java Class                                                      |
|                                                                                                  |
|          Name: |                                                                                 |
|                                                                                                  |
|       👉 [C] Class  <-- SELECIONADO PELO CURSOR DO MOUSE                                         |
|          [I] Interface                                                                           |
|          [R] Record                                                                              |
|          [E] Enum                                                                                |
|          [@] Annotation                                                                          |
|          [!] Exception                                                                           |
+--------------------------------------------------------------------------------------------------+
| O autor seleciona "Class" e digita o nome "MyRunnable" para criar a classe do ajudante.          |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 2: O Arquivo Recém-Criado `MyRunnable.java` Vazio (Timestamp: 11:17:55)

O arquivo nasce com a casca básica de uma classe Java comum:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [MyRunnable.java]                             Timestamp 11:17:55 |
+--------------------------------------------------------------------------------------------------+
|  1  public class MyRunnable {                                                                    |
|  2                                                                                               |
|  3  }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| Uma classe limpa e comum. Agora vamos transformá-la em uma tarefa executável para Threads!       |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 3: Escrevendo `implements Runnable` e o Método `run()` (Timestamp: 11:18:29)

O autor adiciona o contrato `implements Runnable`. O IntelliJ insere a anotação `@Override` e o método `public void run()`. Veja o ícone verde de seta circular na margem esquerda:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [MyRunnable.java]                             Timestamp 11:18:29 |
+--------------------------------------------------------------------------------------------------+
|  1  public class MyRunnable implements Runnable{                                                 |
|  2                                                                                               |
|  3      @Override                                                                                |
|  4  🟢  public void run(){                                                                       |
|  5          | <-- CURSOR DO AUTOR PISCANDO DENTRO DO MÉTODO RUN                                  |
|  6      }                                                                                        |
|  7  }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| ÍCONE 🟢: A bolinha verde com seta na linha 4 indica que este método está SOBRESCREVENDO          |
| o método abstrato run() obrigatório exigido pela interface Runnable do Java.                    |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 4: Retornando a `Main.java` e Recortando o Laço Bloqueante (Timestamp: 11:18:38)

O autor clica de volta na aba `Main.java` e remove o laço `for` com o `Thread.sleep` de lá:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [Main.java]                                   Timestamp 11:18:38 |
+--------------------------------------------------------------------------------------------------+
|  4      public static void main(String[] args) {                                                 |
|  5                                                                                               |
|  6          Scanner scanner = new Scanner(System.in);                                            |
|  7                                                                                               |
|  8          System.out.println("You have 5 seconds to enter your name");                         |
|  9                                                                                               |
| 10  👉      [O LAÇO FOR COM O SLEEP FOI RECORTADO / APAGADO DAQUI!]                             |
| 11                                                                                               |
| 12          System.out.print("Enter your name: ");                                               |
| 13          String name = scanner.nextLine();                                                    |
| 14          System.out.println("Hello " + name);                                                 |
| 15                                                                                               |
| 16          scanner.close();                                                                     |
| 17      }                                                                                        |
+--------------------------------------------------------------------------------------------------+
| O arquivo Main.java agora está livre do sono pesado de 5 segundos que travava tudo!              |
+--------------------------------------------------------------------------------------------------+
```

---

### Tela 5: Colando o Cronômetro dentro do Método `run()` (Timestamp: 11:18:45)

O autor cola o laço `for` inteiro dentro do método `run()` em `MyRunnable.java`. A palavra `run()` está selecionada pelo cursor:

```text
+--------------------------------------------------------------------------------------------------+
|  IntelliJ IDEA - MyFirstProject [MyRunnable.java]                             Timestamp 11:18:45 |
+--------------------------------------------------------------------------------------------------+
|  1  public class MyRunnable implements Runnable{                                                 |
|  2                                                                                               |
|  3      @Override                                                                                |
|  4  👉  public void [run](){  <-- PALAVRA "run" SELECIONADA PELO AUTOR                           |
|  5          for(int i = 1; i <= 5; i++){                                                         |
|  6                                                                                               |
|  7              try{                                                                             |
|  8                  Thread.sleep(1000);                                                          |
|  9              }                                                                                |
| 10              catch(InterruptedException e){                                                   |
| 11                  System.out.println("Thread was interrupted");                                |
| 12              }                                                                                |
| 13                                                                                               |
| 14              if(i == 5){                                                                      |
| 15                  System.out.println("Time's up!");                                            |
| 16              }                                                                                |
| 17          }                                                                                    |
| 18      }                                                                                        |
| 19  }                                                                                            |
+--------------------------------------------------------------------------------------------------+
| SUCESSO: Agora o cronômetro dorminhoco de 5 segundos tem sua própria casa exclusiva!            |
+--------------------------------------------------------------------------------------------------+
```

---

## 3. Dissecando a Teoria: A Interface `Runnable` e o Método `run()`

### 1. O Que é a Interface `Runnable`?
* Mora no coração do Java (`java.lang.Runnable`), então você não precisa fazer `import`.
* É o que os programadores chamam de uma **Interface Funcional**: ela só tem uma única exigência no mundo:
  > *"Quem assinar o contrato `implements Runnable` é obrigado a escrever o método `public void run()`."*

### 2. Por Que Usar `implements Runnable` em vez de `extends Thread`?
Lembra das duas opções que o autor explicou na aula 1?
* **Opção 1 (`extends Thread`)**: Torna sua classe uma Thread diretamente, mas "gasta" a sua única herança do Java. Sua classe nunca mais poderá herdar de outra classe!
* **Opção 2 (`implements Runnable`)**: É a melhor prática da engenharia de software! Ela separa **A TAREFA** (o que deve ser feito) do **MOTOR** (quem vai executar).
  * `MyRunnable` é a **receita do bolo** (a tarefa).
  * O objeto `Thread` que vamos criar na próxima aula será o **forno** (o motor que assa a receita).

### 3. O Que é a Anotação `@Override`?
* Significa *"Sobrescrever"*.
* É um selo de segurança que avisa ao compilador: *"Atenção compilador, eu estou cumprindo exatamente a promessa da interface `Runnable`, implementando o método `run()` com a mesma assinatura!"*.

---

## 4. Os Códigos Completos dos Dois Arquivos

### Arquivo 1: `MyRunnable.java` (O Trabalhador Especialista em Tempo)

```java
public class MyRunnable implements Runnable{

    @Override
    public void run(){
        for(int i = 1; i <= 5; i++){

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("Thread was interrupted");
            }

            if(i == 5){
                System.out.println("Time's up!");
            }
        }
    }
}
```

---

### Arquivo 2: `Main.java` (O Programa Principal Limpo)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("You have 5 seconds to enter your name");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);

        scanner.close();
    }
}
```

---

## 5. Explicação Linha por Linha de Cada Arquivo

---

### Dissecando `MyRunnable.java`

#### Linha 1: `public class MyRunnable implements Runnable{`
* **Explicação**: Declara uma classe pública chamada `MyRunnable` e assina o contrato com a interface `Runnable`. Agora essa classe tem o crachá oficial de "tarefa executável por threads".
* **O que sai no console**: Nada.

#### Linha 3: `@Override`
* **Explicação**: Anotação que confirma a implementação do método da interface.
* **O que sai no console**: Nada.

#### Linha 4: `public void run(){`
* **Explicação**: É o **ponto de partida da nova thread**! Quando dermos a partida nesse trabalhador, a JVM vai vir direto para essa linha e começar a rodar o que estiver aqui dentro.
* **O que sai no console**: Nada.

#### Linha 5: `for(int i = 1; i <= 5; i++){`
* **Explicação**: Inicia o laço de repetição que vai rodar 5 voltas (de 1 até 5).
* **O que sai no console**: Nada.

#### Linha 7: `try{`
* **Explicação**: Abre a zona de proteção contra interrupções de sono.
* **O que sai no console**: Nada.

#### Linha 8: `Thread.sleep(1000);`
* **Explicação**: Manda quem estiver rodando esse método (a futura thread secundária) dormir por 1000 milissegundos (1 segundo).
* **O que sai no console**: Nada (1 segundo de silêncio a cada volta).

#### Linhas 10 a 12: `catch(InterruptedException e){ ... }`
* **Explicação**: Segura a exceção caso alguém interrompa o sono da thread.
* **O que sai no console**: Nada em condições normais.

#### Linhas 14 a 16:
```java
if(i == 5){
    System.out.println("Time's up!");
}
```
* **Explicação**: Quando o contador `i` atingir 5 segundos, imprime que o tempo esgotou!
* **O que sai no console** (quando a thread rodar até o final):
  ```text
  Time's up!
  ```

#### Linhas 17, 18 e 19: `}`
* **Explicação**: Fecham o `for`, o método `run()` e a classe `MyRunnable`.
* **O que sai no console**: Nada.

---

### Dissecando `Main.java`

#### Linhas 1 a 6:
* **Explicação**: Importa o `Scanner`, abre a classe `Main`, abre o método `main` e inicializa o `scanner` para ler o teclado.
* **O que sai no console**: Nada.

#### Linha 8: `System.out.println("You have 5 seconds to enter your name");`
* **Explicação**: Imprime na tela o aviso de 5 segundos.
* **O que sai no console**:
  ```text
  You have 5 seconds to enter your name
  ```

#### Linha 10: `System.out.print("Enter your name: ");`
* **Explicação**: Imprime o pedido do nome sem pular de linha.
* **O que sai no console**:
  ```text
  Enter your name: 
  ```

#### Linha 11: `String name = scanner.nextLine();`
* **Explicação**: Aguarda o usuário digitar o nome e teclar Enter.
* **O que sai no console**: As letras digitadas pelo usuário (ex: `Bro`).

#### Linha 12: `System.out.println("Hello " + name);`
* **Explicação**: Imprime a saudação.
* **O que sai no console**:
  ```text
  Hello Bro
  ```

#### Linha 14: `scanner.close();`
* **Explicação**: Desliga o leitor de teclado.
* **O que sai no console**: Nada.

#### Linhas 15 e 16: `}` e `}`
* **Explicação**: Fecham o método e a classe.

---

## 6. O Que Falta para Eles Rodarem Juntos? (O Próximo Passo do Vídeo)

Repare em um detalhe muito importante:
* Nós criamos a classe `MyRunnable.java`.
* O código dentro de `run()` está perfeitamente pronto para contar 5 segundos.
* **MAS nós ainda não chamamos o `MyRunnable` dentro de `Main.java`!**

No próximo passo, o Bro Code vai voltar ao `Main.java` e fazer a conexão mágica em duas linhas:
1. Criar o objeto da tarefa:
   `MyRunnable myRunnable = new MyRunnable();`
2. Criar a nova Thread e passar a tarefa para ela:
   `Thread thread = new Thread(myRunnable);`
3. Dar a partida no motor:
   `thread.start();`

Assim que fizermos isso, o cronômetro começará a correr em segundo plano **no exato mesmo instante** em que o teclado estiver esperando a digitação do usuário!
