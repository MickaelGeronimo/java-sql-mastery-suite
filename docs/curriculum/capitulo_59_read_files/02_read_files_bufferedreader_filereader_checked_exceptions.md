# 📖 Java — Capítulo 59: `Read Files` (Parte 2)
## A Dupla `BufferedReader` + `FileReader`, o Erro de *Checked Exception* e o Mistério do `close()` no `try-with-resources`

---

### 📌 Metadados da Aula e Contexto do Vídeo
- **Curso:** *Java Full Course for free* (Canal: Bro Code)
- **Capítulo Oficial:** `59. read files 📖`
- **Intervalo de Tempo no Vídeo:** `09:24:52` até `09:25:55` (do total de 12:00:00)
- **Arquivo Principal de Trabalho:** [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)
- **Arquivo Alvo de Leitura:** `C:\Users\HP\Desktop\test.txt` (no Desktop do professor)
- **Ambiente de Desenvolvimento (IDE):** IntelliJ IDEA Community Edition rodando com JDK 23

---

## 📸 Reconstituição Visual Completa da Interface (Para quem vai colar no Google Docs)

Como você copiará este material diretamente para o seu **Google Docs** (onde as imagens originais não estarão incorporadas), veja abaixo a reconstituição visual e textual exata do que acontece em cada uma das 5 imagens exibidas na tela do professor:

```
===================================================================================================
IMAGEM 1 (09:24:40) — JANELA DE PROPRIEDADES DO ARQUIVO TEST.TXT NO WINDOWS DESKTOP
===================================================================================================
Na Área de Trabalho, o professor clica com o botão direito no arquivo recém-criado "test.txt"
e clica em "Properties". Abre-se a janela de propriedades:

┌────────────────────────────────────────────────────────────────────────┐
│ 📄 test Properties                                                  [✕]│ <-- 🖱️ [CLICANDO NO X]
├────────────────────────────────────────────────────────────────────────┤
│ [General]  Security  Details  Previous Versions                        │
│                                                                        │
│  📄  [ test                                                          ] │
│  --------------------------------------------------------------------- │
│  Type of file: Text Document (.txt)                                    │
│  Opens with:   Notepad                       [ Change... ]             │
│  --------------------------------------------------------------------- │
│  Location:     [C:\Users\HP\Desktop]  <-- 🖱️ [COPIADO PARA A ÁREA DE TRANSFERÊNCIA]
│  Size:         71 bytes (71 bytes)                                     │
│  Size on disk: 0 bytes                                                 │
│  --------------------------------------------------------------------- │
│  Created:      Saturday, December 14, 2024, 9:14:44 AM                 │
│  Modified:     Saturday, December 14, 2024, 9:15:06 AM                 │
│  Accessed:     Today, December 14, 2024, 9:15:06 AM                    │
│  --------------------------------------------------------------------- │
│  Attributes:   [ ] Read-only   [ ] Hidden       [ Advanced... ]        │
│  --------------------------------------------------------------------- │
│                                 [  OK  ]   [ Cancel ]   [ Apply ]      │
└────────────────────────────────────────────────────────────────────────┘
* O professor copia o caminho do campo Location para usar no Java.

===================================================================================================
IMAGEM 2 (09:24:52) — TENTANDO INSTANCIAR SEM TRY-CATCH: O ERRO VERMELHO EM FILEREADER!
===================================================================================================
O professor volta ao IntelliJ e digita a linha direta de leitura na linha 15:
"BufferedReader reader = new BufferedReader(new FileReader(filePath));"

Imediatamente, "FileReader(filePath)" fica com um SUB-LINHADO ONDULADO VERMELHO!
A IDE abre uma caixa de documentação e erro (tooltip flutuante):
┌────────────────────────────────────────────────────────────────────────┐
│ Unhandled exception: java.io.FileNotFoundException                     │
│ [Add exception to method signature Alt+Shift+Enter]  More actions...   │
│ ---------------------------------------------------------------------- │
│ java.io.FileReader                                                     │
│ public FileReader(@NotNull String fileName)                            │
│     throws java.io.FileNotFoundException                               │
│ Throws: FileNotFoundException - if the named file does not exist...    │
└────────────────────────────────────────────────────────────────────────┘

* Lição do compilador: "Você NÃO PODE simplesmente abrir um FileReader solto no código!
  O arquivo pode ter sumido do computador! Você é obrigado a tratar esse erro!".

===================================================================================================
IMAGEM 3 (09:25:29) — ENVOLVENDO EM TRY-WITH-RESOURCES E CRIANDO O BLOCO CATCH VAZIO
===================================================================================================
O professor abraça a linha 15 dentro de um try-with-resources:

 15 |         try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 16 | 
 17 |         }
 18 |         catch(){
                    ~
 19 | 
 20 |         }

* A linha 15 continua vermelha e a linha 18 fica vermelha porque o catch() ainda não
  tem nenhum parâmetro de exceção dentro dos parênteses.

===================================================================================================
IMAGEM 4 (09:25:38) — O INTELLIJ SUGERINDO A CORREÇÃO: "ADD 'CATCH' CLAUSE(S)"
===================================================================================================
O professor passa o mouse sobre a palavra "FileReader" na linha 15.
O tooltip do IntelliJ exibe a ação rápida:
┌────────────────────────────────────────────────────────────────────────┐
│ Unhandled exception: java.io.FileNotFoundException                     │
│ [Add 'catch' clause(s)  Alt+Shift+Enter]   More actions... Alt+Enter   │
└────────────────────────────────────────────────────────────────────────┘
Ao clicar nesta opção, a IDE preenche o catch automaticamente com FileNotFoundException!

===================================================================================================
IMAGEM 5 (09:25:55) — O CATCH PREENCHIDO... MAS A LINHA 16 CONTINUA COM ERRO VERMELHO?!
===================================================================================================
O professor preenche o bloco catch na linha 19: "catch(FileNotFoundException e)".
Os imports no topo do arquivo são organizados:
 1 | import java.io.BufferedReader;
 2 | import java.io.FileNotFoundException;
 3 | import java.io.FileReader;

PORÉM, OLHE COM MUITA ATENÇÃO PARA A LINHA 16:
 16 |         try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                          ~~~~~~
                          [LINHA INTEIRA CONTINUA COM SUB-LINHADO VERMELHO!]

No canto superior direito da IDE, o contador de erros aponta: 🔴 1 erro, 3 avisos!
Por que o Java ainda está reclamando se já colocamos o catch(FileNotFoundException e)?
RESPOSTA: Porque o "BufferedReader" fecha sozinho no final do try (método .close()),
e o método close() dispara "IOException", que ainda NÃO está sendo capturado pelo catch!
===================================================================================================
```

---

## 🍕 O Código do Arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) na Imagem 5

Aqui está o código exato presente no editor do professor neste instante:

```java
1: import java.io.BufferedReader;
2: import java.io.FileNotFoundException;
3: import java.io.FileReader;
4: 
5: public class Main {
6:     public static void main(String[] args) {
7: 
8:         // How to read a file using Java (3 popular options)
9: 
10:         // BufferedReader + FileReader: Best for reading text files line-by-line
11:         // FileInputStream: Best for binary files (e.g., images, audio files)
12:         // RandomAccessFile: Best for read/write specific portions of a large file
13: 
14:         String filePath = "C:\\Users\\HP\\Desktop\\test.txt";
15: 
16:         try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
17: 
18:         }
19:         catch(FileNotFoundException e){
20: 
21:         }
22: 
23:     }
24: }
```

---

## 🔍 Anatomia Linha por Linha Para Iniciantes Amadores ("Passo a Passo de Leigo")

Abaixo está o raio-X de cada linha e o motivo do comportamento do compilador:

| Linha | Código Exato | O que essa linha faz nos bastidores? | Situação de Compilação |
| :---: | :--- | :--- | :---: |
| **1** | `import java.io.BufferedReader;` | Traz a ferramenta de leitura com buffer (memória rápida). | Válida |
| **2** | `import java.io.FileNotFoundException;` | Traz o relatório de pane de arquivo não encontrado no disco. | Válida |
| **3** | `import java.io.FileReader;` | Traz o leitor de caracteres em disco. | Válida |
| **4** | *(Linha em branco)* | Espaço visual. | Válida |
| **5** | `public class Main {` | Declaração da classe `Main`. | Válida |
| **6** | `public static void main(String[] args) {` | Ponto de partida da execução. | Válida |
| **7** | *(Linha em branco)* | Espaço visual. | Válida |
| **8-12** | `// How to read a file...` | Comentários com o resumo das 3 opções de leitura. Ignorados pelo Java. | Válida |
| **13** | *(Linha em branco)* | Espaço visual. | Válida |
| **14** | `String filePath = "C:\\Users\\HP\\Desktop\\test.txt";` | Cria a variável de texto com o caminho absoluto do arquivo no Desktop. | Válida |
| **15** | *(Linha em branco)* | Espaço visual. | Válida |
| **16** | `try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){` | **💥 O ERRO DE COMPILAÇÃO:** Tenta instanciar o leitor com try-with-resources. Mas o `BufferedReader` fecha sozinho no final do try chamando `.close()`. O método `.close()` lança `IOException`, e nós só capturamos `FileNotFoundException` na linha 19! | 🔴 **ERRO DE COMPILAÇÃO:** `unreported exception IOException; must be caught or declared to be thrown` |
| **17** | *(Linha em branco)* | Corpo do `try` (ainda sem o loop de leitura). | Válida |
| **18** | `}` | Fim do bloco `try`. | Válida |
| **19** | `catch(FileNotFoundException e){` | Captura especificamente se o arquivo `test.txt` não existir no Desktop. | Válida |
| **20** | *(Linha em branco)* | Corpo do `catch` (vazio por enquanto). | Válida |
| **21** | `}` | Fim do bloco `catch`. | Válida |
| **22** | *(Linha em branco)* | Espaço visual. | Válida |
| **23** | `}` | Fim do método `main`. | Válida |
| **24** | `}` | Fim da classe `Main`. | Válida |

---

## 🖥️ Previsão do Console Nesta Etapa

Se você tentar clicar no botão verde de Play com o código exatamente como está na Imagem 5:
```text
Main.java:16: error: unreported exception IOException; must be caught or declared to be thrown
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                           ^
  exception thrown from implicit call to close() on resource variable 'reader'
1 error
```
> [!WARNING]
> O programa **NÃO COMPILA E NÃO EXECUTA** neste momento!
> Ele exibe exatamente `1 erro` de compilação porque falta capturar a exceção geral `IOException`. O professor resolverá isso nas próximas imagens!

---

## 🧠 Os 3 Grandes Conceitos Ensinados Pelo Professor

### 1. Por que Usamos Dois Objetos Juntos: `new BufferedReader(new FileReader(filePath))`?
Muitos iniciantes perguntam: *"Por que não usar só o FileReader ou só o BufferedReader?"*.

A resposta é o famoso padrão de projeto **Decorator** do Java (um objeto veste o outro):

```mermaid
flowchart LR
    Arquivo["Arquivo no Disco (test.txt)"] -->|Lê caractere por caractere| FR["new FileReader(filePath)"]
    FR -->|Alimenta o Buffer de Memória| BR["new BufferedReader(...)"]
    BR -->|Fornece o método readLine() de ler linha inteira!| Prog["Seu Código Java"]
```

1. **`FileReader`**: É a chave que sabe abrir a porta do arquivo físico no disco rígido do Windows. Mas ele é rústico: só sabe ler uma letra por vez.
2. **`BufferedReader`**: É o mordomo inteligente. Ele não sabe ir até o disco sozinho, mas pega o `FileReader`, suga blocos inteiros de texto para a memória RAM e te entrega uma linha inteira mastigada com o método `.readLine()`.

---

### 2. O Que é uma *Checked Exception* (Exceção Verificada)?
Na Imagem 2, quando o professor tentou escrever:
```java
BufferedReader reader = new BufferedReader(new FileReader(filePath));
```
O Java recusou na hora de compilar, com a mensagem:
`Unhandled exception: java.io.FileNotFoundException`.

- Em Java, erros que envolvem o mundo real (como procurar um arquivo no disco) são chamados de **Checked Exceptions**.
- O compilador não deixa o programa nem sequer nascer se você não colocar um `try-catch` em volta dele.
- É a forma do Java dizer: *"Eu sei que o arquivo pode ser apagado a qualquer momento pelo usuário. Trate isso agora ou não compilo!"*.

---

### 3. O Grande Mistério da Imagem 5: Por que a Linha 16 Continuou com Erro?
Esta é uma das maiores pegadinhas para programadores iniciantes e intermediários em Java:
- Na linha 19, colocamos:
  ```java
  catch(FileNotFoundException e)
  ```
- O `FileReader(filePath)` lança `FileNotFoundException`. Então parecia que o problema estava 100% resolvido, certo?
- **ERRADO!**
- Como usamos a sintaxe de **try-with-resources** `try(BufferedReader reader = ...)`:
  Quando o bloco `try` termina na linha 18, o Java chama **automaticamente e nos bastidores**:
  ```java
  reader.close();
  ```
- E na documentação do Java, o método `close()` da classe `BufferedReader` tem a assinatura:
  ```java
  public void close() throws IOException
  ```
- `FileNotFoundException` é uma **filha** de `IOException`. Ela **NÃO** consegue capturar outros tipos de `IOException` que possam acontecer quando o arquivo está sendo fechado!
- Por isso, o compilador grita:
  > *"Ei! O reader.close() pode disparar um IOException geral, e você só colocou uma rede para pegar FileNotFoundException! Você precisa colocar um catch para IOException também!"*.

---

## 💡 Analogia do Amador: A Chave de Entrada e a Chave de Saída

Imagine a seguinte situação:

1. **A Chave de Entrada (`FileNotFoundException`):** Você foi a um prédio comercial e contratou um chaveiro para abrir a porta da sala (`new FileReader`). Se a sala não existir, você já tem o plano B (`catch(FileNotFoundException)`).
2. **O Trancaço Automático na Saída (`reader.close()` do Try-With-Resources):** Quando você sai da sala, o prédio possui uma tranca automática que passa uma tranca eletrônica reforçada (`.close()`).
3. **O Alarme da Saída (`IOException`):** Se a tranca emperrar na hora de fechar, dispara um alarme geral de incêndio (`IOException`).
4. **O Problema da Imagem 5:** Você contratou um seguro que só cobre se a porta não existir, mas **esqueceu de contratar o seguro contra pane na tranca de saída**!
5. **A Solução:** Adicionar um segundo bloco `catch(IOException e)` para proteger tanto a entrada quanto o fechamento do arquivo!

---

## 🎯 Conclusão da Parte 2
Neste conjunto de imagens:
1. Vimos a cópia do caminho absoluto diretamente das Propriedades do Windows.
2. Entendemos por que o Java obriga o tratamento de `FileNotFoundException` ao instanciar um `FileReader`.
3. Vimos como encapsular o leitor dentro do `try-with-resources`.
4. Descobrimos a razão exata pela qual o código da Imagem 5 ainda apresenta 1 erro de compilação: o fechamento automático (`close()`) exige a captura de `IOException`!
