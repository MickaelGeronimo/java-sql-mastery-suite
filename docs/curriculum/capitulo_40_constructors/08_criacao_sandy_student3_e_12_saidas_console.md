# Curso de Java — Bro Code
## Capítulo 40: Construtores (*Constructors*) — A Chegada da Sandy (`student3`), a Resolução de Erros de Parâmetros e as 12 Saídas no Terminal

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e descritiva**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as nuances visuais das 3 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamps `6:59:31` até `6:59:54` de 12:00:00) foram descritas minuciosamente em texto corrido e diagramas para que você compreenda com perfeição o fluxo de raciocínio do autor.

---

## 1. O Cenário Visual das 3 Imagens (O Que o Professor Bro Code Mostra no Vídeo)

Nesta sequência de 3 telas, o professor conclui a fase de atributos do capítulo, adicionando a terceira aluna da turma (a esquilo cientista **Sandy Bochechas**) e demonstrando a independência total dos 3 objetos no terminal:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (Main.java - 6:59:31): O Erro de Argumento Incompleto ao Digitar Sandy                      |
| - Na linha 7, Bro Code começa a digitar:                                                           |
|     Student student3 = new Student("");                                                            |
| - O cursor do teclado está entre as aspas duplas: ("|").                                           |
| - ALERTA VISUAL NO INTELLIJ:                                                                       |
|   Abaixo das aspas ("") há uma ONDULAÇÃO VERMELHA de erro!                                         |
|   No canto superior direito surge o ícone vermelho de alerta: "1 erro"!                            |
| - Por quê? Porque o construtor exige 3 dados (String, int, double) e ele só abriu as aspas do      |
|   primeiro (String); ainda faltam a idade e o GPA!                                                 |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (Main.java - 6:59:51): A Sandy Pronta e os 12 Comandos println                              |
| - Bro Code completa a linha 7:                                                                     |
|     Student student3 = new Student("Sandy", 27, 4.0);                                              |
| - Todo o erro vermelho DESAPARECE! A linha fica limpa.                                             |
| - Nas linhas 19 a 22, ele adiciona as 4 impressões para student3:                                  |
|     System.out.println(student3.name);                                                             |
|     System.out.println(student3.age);                                                              |
|     System.out.println(student3.gpa);                                                              |
|     System.out.println(student3.isEnrolled);                                                       |
| - A palavra 'student3' fica em destaque cinza nas 4 linhas.                                        |
| - O cursor do mouse vai até o botão verde triangular Play (Run 'Main') no canto superior direito.  |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 3 (Console Run - 6:59:54): A Execução dos 3 Alunos com Sucesso Total!                         |
| - A janela inferior 'Run' se abre e imprime 12 linhas no total.                                    |
| - Como a janela ocupa metade da tela, o terminal rolou para baixo, exibindo com destaque:          |
|     Patrick                                                                                        |
|     34                                                                                             |
|     1.5                                                                                            |
|     true                                                                                           |
|     Sandy                                                                                          |
|     27                                                                                             |
|     4.0                                                                                            |
|     true                                                                                           |
|     Process finished with exit code 0                                                              |
| - (Os 4 dados do Bob Esponja estão logo acima na rolagem da janela).                               |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. A Tela 1: Por Que Apareceu a Ondulação Vermelha em `new Student("");`?

Na **Tela 1**, o autor está no meio da digitação da linha 7:

```java
Student student3 = new Student(""); // <--- ONDULAÇÃO VERMELHA NO ""
```

### O Que o Compilador do Java Estava Pensando?
Lembre-se da receita do construtor que criamos em `Student.java`:
```java
Student(String name, int age, double gpa)
```
Ele exige **três ingredientes obrigatórios**:
1. Um texto (`String`)
2. Um inteiro (`int`)
3. Um número decimal (`double`)

Na Tela 1, o autor digitou apenas as aspas vazias `""`.  
* O Java pensou: *"Beleza, você me deu o nome como texto vazio `""` (1 argumento). Mas cadê a idade e cadê o GPA? Você me deu 1 ingrediente de uma receita que exige 3!"*.
* Por isso o IntelliJ desenha a ondulação vermelha embaixo de `""` e marca **1 erro** no canto superior direito.  
Assim que ele colocou `, 27, 4.0)` na **Tela 2**, os 3 ingredientes foram entregues e o compilador ficou 100% satisfeito!

---

## 3. O Código Completo e Enumerado de `Main.java` (Telas 2 e 3)

Vejamos todas as 24 linhas do arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) exatamente como ficou estruturado na tela do autor:

```java
1:  public class Main {
2:      public static void main(String[] args) {
3:  
4:  
5:          Student student1 = new Student("Spongebob", 30, 3.2);
6:          Student student2 = new Student("Patrick", 34, 1.5);
7:          Student student3 = new Student("Sandy", 27, 4.0);
8:  
9:          System.out.println(student1.name);
10:         System.out.println(student1.age);
11:         System.out.println(student1.gpa);
12:         System.out.println(student1.isEnrolled);
13: 
14:         System.out.println(student2.name);
15:         System.out.println(student2.age);
16:         System.out.println(student2.gpa);
17:         System.out.println(student2.isEnrolled);
18: 
19:         System.out.println(student3.name);
20:         System.out.println(student3.age);
21:         System.out.println(student3.gpa);
22:         System.out.println(student3.isEnrolled);
23:     }
24: }
```

### Explicação Linha por Linha de `Main.java`:

* **Linhas 1 a 3:** Ponto de partida padrão de qualquer aplicação Java.
* **Linha 5 (`Student student1 = new Student("Spongebob", 30, 3.2);`):**
  * Cria o primeiro objeto na Heap.
  * O construtor roda e preenche: `this.name = "Spongebob"`, `this.age = 30`, `this.gpa = 3.2` e `this.isEnrolled = true`.
* **Linha 6 (`Student student2 = new Student("Patrick", 34, 1.5);`):**
  * Cria o segundo objeto na Heap em outro endereço isolado.
  * Preenche com os dados do Patrick e matrícula ativa (`true`).
* **Linha 7 (`Student student3 = new Student("Sandy", 27, 4.0);`):**
  * Cria o terceiro objeto na Heap com os dados da Sandy:
    * Nome: `"Sandy"`
    * Idade: `27`
    * GPA: `4.0` (nota máxima perfeita!)
    * Matrícula: `true` (definida automaticamente pelo construtor).
* **Linhas 9 a 12 (Bloco do Bob Esponja):**
  * Imprime os 4 dados de `student1`.
  * **Saídas geradas:** `Spongebob`, `30`, `3.2`, `true`.
* **Linhas 14 a 17 (Bloco do Patrick):**
  * Imprime os 4 dados de `student2`.
  * **Saídas geradas:** `Patrick`, `34`, `1.5`, `true`.
* **Linhas 19 a 22 (Bloco da Sandy):**
  * Imprime os 4 dados de `student3`.
  * **Saídas geradas:** `Sandy`, `27`, `4.0`, `true`.
* **Linhas 23 e 24:** Fecham o método `main` e a classe `Main`.

---

## 4. O Mapa Completo de Memória (Stack vs Heap): Os 3 Estudantes

Veja como os 3 alunos moram nos bastidores da memória RAM do computador:

```
          MEMÓRIA STACK (Referências Locais)                MEMÓRIA HEAP (Instâncias Reais de Objetos)
       +──────────────────────────────────────+           +──────────────────────────────────────────+
       |                                      |           |  Objeto Student #1                       |
       |  student1 ───────────────────────────┼──────────►|  name: "Spongebob"                       |
       |  (Endereço Heap: @4f023edb)          |           |  age: 30                                 |
       |                                      |           |  gpa: 3.2                                |
       |                                      |           |  isEnrolled: true                        |
       +──────────────────────────────────────+           +──────────────────────────────────────────+
       |                                      |           |  Objeto Student #2                       |
       |  student2 ───────────────────────────┼──────────►|  name: "Patrick"                         |
       |  (Endereço Heap: @3a71f400)          |           |  age: 34                                 |
       |                                      |           |  gpa: 1.5                                |
       |                                      |           |  isEnrolled: true                        |
       +──────────────────────────────────────+           +──────────────────────────────────────────+
       |                                      |           |  Objeto Student #3                       |
       |  student3 ───────────────────────────┼──────────►|  name: "Sandy"                           |
       |  (Endereço Heap: @7d4991ad)          |           |  age: 27                                 |
       |                                      |           |  gpa: 4.0                                |
       |                                      |           |  isEnrolled: true                        |
       +──────────────────────────────────────+           +──────────────────────────────────────────+
```

### Por Que Esse Modelo É Perfeito?
1. Cada estudante possui seu **próprio crachá** na memória Stack (`student1`, `student2`, `student3`).
2. Cada estudante mora em uma **casa separada** na memória Heap.
3. Nenhum dado de um aluno interfere no outro! O GPA 4.0 da Sandy não aumenta a nota 1.5 do Patrick, e a idade 30 do Bob Esponja não afeta a idade 27 da Sandy.

---

## 5. A Saída Real Completa do Terminal

Quando o botão Play foi acionado na Tela 2, o console da aba `Run` imprimiu exatamente as **12 linhas** abaixo:

```text
C:\Users\HP\.jdks\openjdk-23\bin\java.exe "-javaagent:C:\Program Files\JetBrains\..." Main
Spongebob
30
3.2
true
Patrick
34
1.5
true
Sandy
27
4.0
true

Process finished with exit code 0
```

### Detalhamento das 12 Linhas:
* **Linhas 1 a 4:** Os 4 dados do Bob Esponja (`student1`).
* **Linhas 5 a 8:** Os 4 dados do Patrick (`student2`).
* **Linhas 9 a 12:** Os 4 dados da Sandy (`student3`).
* **Linha final:** `Process finished with exit code 0` (Programa executado com 100% de sucesso).

*(Nota: Na imagem 3 do vídeo, o terminal rolou para baixo para mostrar a conclusão da saída, ficando visíveis a partir do Patrick até o final).*

---

## 6. O Que Este Trecho Consolida no Seu Conhecimento de Java?

1. **Construtores garantem integridade:** O Java não permite criar um objeto pela metade (como visto na Tela 1 com `new Student("")`). Ou você passa todos os argumentos exigidos, ou o código nem compila!
2. **Reaproveitamento de Molde:** Com apenas uma única classe `Student.java`, nós criamos 3 pessoas totalmente diferentes sem duplicar nenhuma linha da definição da classe!
3. **Valores Padrão:** A propriedade `isEnrolled` foi impressa como `true` para os 3 alunos, comprovando que o construtor é o lugar perfeito para definir configurações de fábrica automáticas.
