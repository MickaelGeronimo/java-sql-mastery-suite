# Curso de Java Completo (Bro Code) — Capítulo 66: `generics 📦` (Parte 4)
## Instanciando a Classe Genérica `Box<String>`, o Perigo dos *Raw Types* e os Métodos `setItem` / `getItem`

> **Contexto no Vídeo**: Entre os tempos **`10:44:56`** e **`10:46:02 / 12:00:00`**, o instrutor **Bro Code** coloca a nossa classe genérica `Box<T>` para funcionar no mundo real:  
> 1. Ele volta para o arquivo `Main.java` e começa digitando `Box box = new Box();` de propósito, para mostrar o aviso amarelo de **Raw Type** (Tipo Bruto/Cru) que o IntelliJ acusa quando nos esquecemos dos colchetes angulares `<>`.  
> 2. Ele corrige a declaração para **`Box<String> box = new Box<>();`**, fazendo o aviso desaparecer e garantindo segurança total de tipos.  
> 3. Em seguida, implementa o método modificador **`setItem("banana")`** para guardar uma banana dentro da caixa.  
> 4. E recupera o item usando o método de leitura **`getItem()`** dentro de `System.out.println(box.getItem());`, clicando no botão verde Run para testar!

---

## 📸 Reconstituição Visual Completa das 4 Imagens (Para Google Docs)

Como você copiará este guia diretamente para o seu **Google Docs** e não terá as imagens anexadas lá, abaixo está a reprodução textual e estrutural idêntica de cada uma das 4 telas do IntelliJ IDEA e do YouTube.

```
====================================================================================================
IMAGEM 1: O Aviso Amarelo de 'Raw Type' em Main.java (Tempo do Vídeo: 10:44:56)
====================================================================================================
Barra de Vídeo do YouTube (Rodapé):
  [ ⏸️ Pausa K ]  10:44:56 / 12:00:00    66.generics >

Painel de Arquivos (Esquerda):
  📁 MyFirstProject C:\Users\HP\...
  ├── 📁 .idea
  ├── 📁 out
  └── 📁 src
      ├── ☕ Box
      ├── ☕ Main
      ├── 📄 .gitignore
      └── 📄 MyFirstProject.iml

Editor de Código (Main.java - Linhas 9 a 13):
 9:         //            <String> type argument (specifies the type)
10: 
11: ❗      Box box = new Box();  <-- [O IntelliJ sublinha 'Box' com aviso amarelo ondulado!]
12:     }
13: }

Análise Visual da Imagem 1:
- Na linha 11, o autor escreveu `Box box = new Box();` sem colocar `<String>`.
- Na margem esquerda da linha 11 aparece um ícone de exclamação vermelho/amarelo ❗.
- As palavras `Box` e `Box()` estão com sublinhado amarelo ondulado.
- Isso é o que a comunidade Java chama de **Raw Type** (uso de classe genérica como tipo cru). O compilador avisa: "Cuidado! Você criou uma classe genérica, mas não especificou o tipo!".
====================================================================================================
```

```
====================================================================================================
IMAGEM 2: A Correção com o Argumento de Tipo '<String>' (Tempo: 10:45:36)
====================================================================================================
Editor de Código (Main.java - Linhas 9 a 15):
 9:         //            <String> type argument (specifies the type)
10: 
11:         Box<String> box = new Box<>();  <-- [AVISO DESAPARECEU! CÓDIGO 100% LIMPO!]
12: 
13: |       <-- [Cursor de digitação piscando na linha 13]
14:     }
15: }

Análise Visual da Imagem 2:
- O autor consertou a linha adicionando `<String>` do lado esquerdo e `<>` (*diamond operator*) do lado direito.
- Todos os sublinhados amarelos e ícones de alerta sumiram imediatamente.
- Agora o Java sabe com certeza absoluta que esta caixa específica só aceitará textos (`String`).
====================================================================================================
```

```
====================================================================================================
IMAGEM 3: Guardando a Banana com 'box.setItem("banana");' (Tempo: 10:45:54)
====================================================================================================
Abas Superiores:
  [ ☕ Main.java ✕ ] [ ☕ Box.java (Ponteiro do mouse sobre a aba Box.java) ]

Editor de Código (Main.java - Linhas 11 a 17):
11:         Box<String> box = new Box<>();
12: 
13:         box.setItem("banana");  <-- [Guarda a String "banana" dentro da caixa!]
14: 
15:         System.out.println(|);  <-- [Cursor piscando dentro dos parênteses do println]
16:     }
17: }

Análise Visual da Imagem 3:
- Linha 13: O autor chama `box.setItem("banana");`.
- Linha 15: Ele digita `System.out.println();` e prepara para recuperar o item guardado.
- O ponteiro do mouse no topo está conferindo a aba `Box.java`.
====================================================================================================
```

```
====================================================================================================
IMAGEM 4: Recuperando com 'box.getItem()' e Clicando em 'Run' (Tempo: 10:46:02)
====================================================================================================
Barra Superior Direita da Janela:
  Current File ▾ [ ▶️ Run (Play Verde Selecionado) ] [ 🐛 Debug ]

Editor de Código (Main.java - Linhas 11 a 17):
11:         Box<String> box = new Box<>();
12: 
13:         box.setItem("banana");
14: 
15:         System.out.println(box.getItem());  <-- [Recupera e imprime o item da caixa!]
16:     }
17: }

Análise Visual da Imagem 4:
- Linha 15 agora tem `System.out.println(box.getItem());`.
- O cursor do mouse está posicionado sobre o botão verde de Run no topo direito.
- O autor está pronto para rodar o programa e ver a palavra `"banana"` impressa no console!
====================================================================================================
```

---

## ⚠️ A Armadilha dos *Raw Types* (Por Que Nunca Escrever `Box box = new Box();`)

Na **Imagem 1**, o IntelliJ disparou um alerta amarelo. Por que isso acontece?

### 1. O Que É um "Raw Type"?
Quando uma classe foi criada com um parâmetro de tipo (como `public class Box<T>`), mas você a instancia sem passar o `<>` (apenas `Box box = new Box();`), você está usando um **Raw Type** (Tipo Bruto).

### 2. O Que Acontece por Baixo dos Panos?
- Sem o tipo genérico, o Java finge que o `T` é um **`Object`** comum.
- Você perde o maior benefício do Java: a **Segurança em Tempo de Compilação** (*Type Safety*).
- Você poderia colocar um número, um cachorro e uma banana na mesma caixa sem o compilador reclamar, e na hora de ler o código precisaria de conversões manuais forçadas (*casting*):
  ```java
  String fruta = (String) box.getItem(); // <-- PERIGOSO! Pode travar em tempo de execução!
  ```

### 3. A Regra de Ouro:
> **Sempre informe o tipo entre os colchetes angulares!**  
> `Box<String> box = new Box<>();`  
> O compilador agora sabe o tipo exato, dispensa conversões manuais e garante que seu código nunca trave por incompatibilidade de tipos!

---

## 🔬 A Anatomia da Classe Genérica Completa: [`Box.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Box.java)

Veja como ficou o arquivo da caixa genérica com os métodos de colocar (*set*) e pegar (*get*):

```java
public class Box<T> {

    T item;

    public void setItem(T item){
        this.item = item;
    }

    public T getItem(){
        return this.item;
    }
}
```

Observe a harmonia perfeita da letra **`T`**:

1. **No Atributo**: `T item;`  
   A caixa tem um item de tipo `T`.
2. **No Setter**: `public void setItem(T item)`  
   O parâmetro recebido pelo método é de tipo `T`. Se a caixa for `Box<String>`, ela só aceita `String`. Se alguém tentar passar um número inteiro, o código nem compila!
3. **No Getter**: `public T getItem()`  
   O tipo de retorno do método é **`T`**! Se a caixa for `Box<String>`, o método devolve uma `String` pura e limpa, pronta para uso, sem necessidade de nenhum cast manual!

```
                    QUANDO INSTANCIAMOS Box<String>:
                    
                    Box<String> box = new Box<>();
                                  │
                                  ▼
        ┌──────────────────────────────────────────────────┐
        │  O Java substitui mentalmente todo 'T' por String:│
        │                                                  │
        │  String item;                                    │
        │  public void setItem(String item) { ... }        │
        │  public String getItem() { return this.item; }   │
        └──────────────────────────────────────────────────┘
```

---

## 🔍 Explicação Linha por Linha de Todo o Código

### Arquivo 1: [`Box.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/generics/Box.java)

```java
 1: public class Box<T> {
 2: 
 3:     T item;
 4: 
 5:     public void setItem(T item){
 6:         this.item = item;
 7:     }
 8: 
 9:     public T getItem(){
10:         return this.item;
11:     }
12: }
```

- **Linha 1 (`public class Box<T> {`)**: Declara a classe pública `Box` com o parâmetro de tipo genérico `<T>`.
- **Linha 3 (`T item;`)**: Atributo de instância. Armazena o objeto guardado na caixa com o tipo `T`.
- **Linha 5 (`public void setItem(T item){`)**:
  - `public`: Método acessível externamente.
  - `void`: Não devolve nenhum valor de retorno.
  - `setItem`: Nome do método.
  - `(T item)`: Recebe como argumento um item do tipo genérico `T`.
- **Linha 6 (`this.item = item;`)**:
  - `this.item`: O atributo da classe (o campo de instância na memória Heap).
  - `= item`: Atribui o valor passado como parâmetro da linha 5 para dentro da caixa.
- **Linha 7 (`}`)**: Fecha o método `setItem`.
- **Linha 9 (`public T getItem(){`)**:
  - `public`: Acessível por qualquer classe.
  - `T`: **Tipo de Retorno Genérico**. O método promete devolver exatamente o tipo de dado que foi configurado na criação da caixa.
  - `getItem`: Nome do método leitor.
  - `()`: Não recebe nenhum parâmetro.
- **Linha 10 (`return this.item;`)**:
  - Devolve o item armazenado dentro da caixa.
- **Linha 11 (`}`)**: Fecha o método `getItem`.
- **Linha 12 (`}`)**: Fecha a classe `Box`.

---

### Arquivo 2: [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java)

```java
 1: import java.util.ArrayList;
 2: 
 3: public class Main {
 4:     public static void main(String[] args) {
 5: 
 6:         // Generics = A concept where you can write a class, interface, or method
 7:         //            that is compatible with different data types.
 8:         //            <T> type parameter (placeholder that gets replaced with a real type)
 9:         //            <String> type argument (specifies the type)
10: 
11:         Box<String> box = new Box<>();
12: 
13:         box.setItem("banana");
14: 
15:         System.out.println(box.getItem());
16: 
17:     }
18: }
```

- **Linhas 1 a 9**: Imports, classe `Main`, método `main` e os comentários teóricos de Generics.
- **Linha 11 (`Box<String> box = new Box<>();`)**:
  - Cria uma nova caixa configurada especificamente para conter `String`.
  - Aloca o objeto na memória Heap com o molde `<String>`.
- **Linha 13 (`box.setItem("banana");`)**:
  - Chama o método `setItem` passando o texto literal `"banana"`.
  - O valor `"banana"` é guardado com sucesso no campo `this.item`.
- **Linha 15 (`System.out.println(box.getItem());`)**:
  - Chama `box.getItem()`, que devolve `"banana"`.
  - O comando `System.out.println` recebe esse texto e o exibe no console.
- **Linhas 17 e 18 (`}`)**: Fecham o método `main` e a classe `Main`.

---

## 🖥️ Tabela de Saída no Console

Quando o botão verde Run da Imagem 4 for executado:

```bash
javac Box.java Main.java
java Main
```

### Saída Exata Produzida no Console:
```text
banana

Process finished with exit code 0
```

---

## 🔮 O Que Esperar para as Próximas Cenas?

Até agora, guardamos uma `String` (`"banana"`) na caixa.  
Nas próximas etapas, o autor demonstrará a verdadeira flexibilidade dos Generics:
- Criar caixas para guardar números: `Box<Integer> box2 = new Box<>(); box2.setItem(100);`
- Criar caixas para guardar números decimais: `Box<Double> box3 = new Box<>(); box3.setItem(3.14);`
- Criar caixas para guardar objetos personalizados: `Box<Product> box4 = new Box<>();`!
- E tudo isso usando **a mesmíssima classe `Box.java`**, sem precisar reescrever nem uma única linha de código!
