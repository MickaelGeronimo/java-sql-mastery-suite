# Curso de Java — Bro Code
## Capítulo 41: Construtores Sobrecarregados (*Overloaded Constructors 🛠️*) — Parte 2: O Passo a Passo no IntelliJ e a Criação da Classe `User.java`

---

> [!NOTE]
> **Contexto de Estudo para Cópia no Google Docs:**
> Este documento foi elaborado de forma **100% autossuficiente e descritiva**. Você pode copiá-lo e colá-lo diretamente no seu Google Docs ou caderno digital. Todas as nuances visuais das 2 telas da aula do Bro Code no YouTube (*Java Full Course for free* — timestamp `7:02:07` até `7:02:25` de 12:00:00) foram descritas minuciosamente em texto corrido para que você aprenda exatamente como navegar na IDE e como estruturar uma nova classe em Java.

---

## 1. O Cenário Visual das 2 Imagens (O Que o Professor Bro Code Faz na Tela)

Após apresentar a teoria na Parte 1, o professor precisa de um novo arquivo para construir a classe de exemplo. Ele navega pelos menus do IntelliJ IDEA para criar a classe `User`:

```
+----------------------------------------------------------------------------------------------------+
| TELA 1 (7:02:07): A Navegação no Menu Superior do IntelliJ IDEA                                    |
| - Aba ativa ao fundo: Main.java.                                                                   |
| - O autor clica no menu superior esquerdo: File ──► New                                            |
| - Abre-se a lista de opções do submenu 'New':                                                      |
|     - Project...                                                                                   |
|     - Project from Existing Sources...                                                             |
|     - Module...                                                                                    |
|     - Java Class  (a opção desejada!)                                                              |
|     - Kotlin Class/File  (o cursor do mouse passa por aqui em direção à opção)                     |
|     - File                                                                                         |
|     - Scratch File                                                                                 |
| - No centro da tela aparece o ícone do YouTube: "<- 10" (o autor voltou 10 segundos).              |
+----------------------------------------------------------------------------------------------------+
                                                  │
                                                  ▼
+----------------------------------------------------------------------------------------------------+
| TELA 2 (7:02:15): A Janela Modal 'New Java Class' e a Palavra 'User'                               |
| - Uma caixinha retangular flutuante se abre no meio da tela com o título: "New Java Class".        |
| - No campo de texto, o autor começa a digitar: "Us"                                                |
| - Na tela do vídeo, surge um letreiro gigante estilizado com a palavra: "User".                    |
| - Na lista de tipos disponíveis dentro da caixinha:                                                |
|     (c) Class  (selecionado em azul por padrão)                                                    |
|     (i) Interface                                                                                  |
|     (R) Record                                                                                     |
|     (E) Enum                                                                                       |
|     (@) Annotation                                                                                 |
|     (!) Exception                                                                                  |
| - O autor vai apertar ENTER para confirmar a criação de 'User.java'!                               |
+----------------------------------------------------------------------------------------------------+
```

---

## 2. Por Que a Classe `User` (Usuário) Foi Escolhida? A Conexão com o Mundo Real 🌐

No Capítulo 40, usamos a classe `Student` onde todo aluno tinha que fornecer nome, idade e nota.  
Agora, para ensinar **sobrecarga de construtores**, o professor escolhe a classe **`User`** (Usuário de um sistema/site).

### Por Que a Classe `User` É o Exemplo Perfeito?
Pense em qualquer aplicativo que você usa hoje (Instagram, Netflix, Discord, Mercado Livre):
1. **Cadastro Rápido (Convidado/Anônimo):**
   Às vezes você só quer testar o app sem dar seus dados. O app cria um usuário sem nenhum dado inicial:
   ```java
   User user1 = new User();
   ```
2. **Cadastro Básico (Apenas Nome de Usuário):**
   Você cria uma conta rápida informando apenas o apelido:
   ```java
   User user2 = new User("BobEsponja");
   ```
3. **Cadastro Padrão (Nome e Email):**
   Você informa seu apelido e seu email para confirmação:
   ```java
   User user3 = new User("PatrickEstrela", "patrick@fendadobikini.com");
   ```
4. **Cadastro Completo (Nome, Email e Idade):**
   Você preenche o formulário completo com todas as informações:
   ```java
   User user4 = new User("SandyEsquilo", "sandy@texas.com", 27);
   ```

> [!TIP]
> **A Sacada de Mestre:**
> Todos os 4 casos acima são **usuários válidos**!  
> Graças aos **Construtores Sobrecarregados**, a classe `User` saberá lidar com qualquer uma dessas 4 situações sem travar o programa e sem acusar erro vermelho de compilação!

---

## 3. A Regra Sagrada de Nomenclatura em Java: *PascalCase*

Repare na **Tela 2**: o autor digitou `User` com a **primeira letra maiúscula**.

```
    Certo:  User.java   /   Car.java   /   Student.java   /   BankAccount.java
    Errado: user.java   /   car.java   /   student.java   /   bankaccount.java
```

* Em Java, **toda classe deve começar com letra maiúscula**. Se o nome for composto por mais de uma palavra, cada palavra subsequente também deve começar com maiúscula (padrão chamado de *PascalCase*).
* Variáveis e métodos começam com letra minúscula (*camelCase*, ex: `userName`, `study()`).

---

## 4. O Esqueleto Inicial Criado em `User.java`

Assim que o autor apertou a tecla **ENTER** na Tela 2, o IntelliJ IDEA criou o arquivo [`User.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/User.java) na pasta `src` com a seguinte estrutura:

```java
1: public class User {
2: 
3: }
```

### Explicação Linha por Linha de `User.java`:
* **Linha 1 (`public class User {`):**
  * `public`: Modificador de acesso que permite que qualquer outro arquivo do nosso projeto (como o `Main.java`) possa enxergar e utilizar a classe `User`.
  * `class`: Palavra-chave reservada do Java que indica que estamos criando um molde / planta baixa.
  * `User`: O nome da classe.
  * `{`: Abre o bloco onde iremos programar os atributos (dados) e os múltiplos construtores sobrecarregados.
* **Linha 2 (` `):** Linha em branco onde o cursor fica posicionado, pronto para receber o código.
* **Linha 3 (`}`):** Fecha o bloco da classe.

---

## 5. Como Ficou o Arquivo `Main.java`

Enquanto isso, o arquivo [`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) continua intacto com as definições conceituais na linha 5:

```java
1: public class Main {
2:     public static void main(String[] args) {
3: 
4: 
5:         // overloaded constructors = Allow a class to have multiple constructors
6:         //                           with different parameter lists.
7:         //                           Enable objects to be initialized in various ways.
8: 
9:     }
10: }
```

---

## 6. O Que Sai no Console Neste Momento?

Se rodarmos o projeto neste segundo:
```text
Process finished with exit code 0
```
* **Nenhuma mensagem é impressa**, porque os arquivos ainda estão apenas sendo estruturados.
* O compilador do Java compila ambos os arquivos ([`Main.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/Main.java) e [`User.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/User.java)) sem nenhum erro!

---

## 7. O Que Vem a Seguir? (Próximos Passos do Bro Code)

Nas próximas telas, o autor irá:
1. Declarar os atributos de um usuário dentro de `User.java`:
   * `String username;`
   * `String email;`
   * `int age;`
2. Escrever o primeiro construtor completo com 3 parâmetros.
3. Escrever os outros construtores com 2, 1 e 0 parâmetros (a sobrecarga em ação!).
4. Voltar para o `Main.java` e instanciar vários usuários de formas diferentes!
