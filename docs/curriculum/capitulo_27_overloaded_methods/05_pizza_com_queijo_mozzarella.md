# 🍕 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 27: Métodos Sobrecarregados (`overloaded methods`) — Parte 5: Adicionando Queijo! O Método `bakePizza` com 2 Parâmetros e o Card da Mozzarella
**Timestamps do vídeo:** `4:24:46` até `4:24:xx / 12:00:00`  
**Tema:** A evolução da pizzaria: criando o segundo método sobrecarregado `bakePizza(String bread, String cheese)`, a ordem da concatenação de texto (`cheese + " " + bread + " pizza"`), o card educativo da Wikipedia sobre queijo Mozzarella que o professor colocou na tela e o resultado `mozzarella flat bread pizza` no console.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem os prints ao lado, veja exatamente a cena descontraída e didática que o professor **Bro Code** montou:

1. **Imagem 1 (Minuto 4:24:46 — O Segundo Ingrediente e a Curiosidade da Mozzarella):**
   * O professor cria a segunda versão do método na Linha 17:
     ```java
     static String bakePizza(String bread, String cheese){
         return cheese + " " + bread + " pizza";
     }
     ```
   * No `main` (Linha 9), ele atualiza o pedido para 2 ingredientes:
     ```java
     String pizza = bakePizza("flat bread", "mozzarella");
     ```
   * A palavra `"mozzarella"` está selecionada em azul.
   * **O detalhe divertido do vídeo:** No canto inferior direito da tela, o professor faz pipocar um **card ilustrado da Wikipedia** com o título *"Mozzarella (Cheese)"*, fotos reais de bolas brancas de queijo mussarela fresca com tomate e manjericão, e uma explicação gastronômica dizendo que a mussarela é um queijo de massa filada originário do sul da Itália!

2. **Imagem 2 (A Execução e o Pedido Pronto no Console):**
   * O professor clica no botão verde de **Play** (`Run 'Main'`).
   * A janela preta do console abre e imprime em verde brilhante:
     ```text
     mozzarella flat bread pizza
     ```
   * Logo abaixo, a confirmação do sistema:
     ```text
     Process finished with exit code 0
     ```

---

## 🍕 A Evolução do Cardápio da Pizzaria

Veja como a sobrecarga de métodos está crescendo de forma natural:

| Versão do Método | Parâmetros Recebidos | O que ela Monta? | Exemplo de Retorno |
| :---: | :--- | :--- | :--- |
| **Versão 1** | `(String bread)` | Apenas a massa | `"flat bread pizza"` |
| **Versão 2 (Nova!)** | `(String bread, String cheese)` | Queijo + Massa | `"mozzarella flat bread pizza"` |

Ambos os métodos possuem exatamente o **mesmo nome (`bakePizza`)**, mas assinaturas diferentes:
* Assinatura 1: `bakePizza(String)`
* Assinatura 2: `bakePizza(String, String)`

Como você passou 2 textos no `main`, o Java ignorou a primeira versão (que ficou com a etiqueta cinza `no usages`) e saltou direto para a segunda versão!

---

## 🔍 Preste Atenção na Ordem da Concatenação!

Olhe com atenção para a Linha 18:
```java
return cheese + " " + bread + " pizza";
```

O professor escolheu montar a frase colocando o **queijo primeiro**, depois um espaço `" "`, depois o **pão**, e no final a palavra `" pizza"`:
* `cheese` = `"mozzarella"`
* `bread` = `"flat bread"`
* Resultado: `"mozzarella"` $+$ `" "` $+$ `"flat bread"` $+$ `" pizza"` $=$ `"mozzarella flat bread pizza"`.

---

## 💻 Código Completo e Idêntico ao da Tela (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // overloaded methods = methods that share the same name,
        //                      but different parameters
        //                      signature = name + parameters

        String pizza = bakePizza("flat bread", "mozzarella");

        System.out.println(pizza);
    }

    static String bakePizza(String bread){
        return bread + " pizza";
    }

    static String bakePizza(String bread, String cheese){
        return cheese + " " + bread + " pizza";
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto onde o computador inicia a execução do programa Java.
* **Console:** **Nada**.

---

### **Linhas 3 a 8:** *(Linhas em branco e comentários conceituais)*
* **Linhas 5 a 7:** Anotações sobre métodos sobrecarregados e a regra da assinatura (`signature = name + parameters`). O Java ignora.
* **Console:** **Nada**.

---

### **Linha 9:** `String pizza = bakePizza("flat bread", "mozzarella");`
* **O que faz:** **A CHAMADA COM 2 INGREDIENTES:**
  1. O compilador vê a chamada `bakePizza` com **2 argumentos de texto** (`"flat bread"` e `"mozzarella"`).
  2. Ele procura um método que aceite 2 Strings.
  3. Ignora a Linha 14 (que só aceita 1 String).
  4. Salta direto para a **Linha 17** (que aceita 2 Strings).
  5. Entrega `"flat bread"` para `bread` e `"mozzarella"` para `cheese`.
  6. Na Linha 18, monta o texto: `"mozzarella flat bread pizza"`.
  7. O `return` devolve esse texto pronto para a Linha 9.
  8. O texto é armazenado na variável `String pizza`.
* **Console:** **Nada** (o texto está guardado na memória RAM, pronto para ser impresso).

---

### **Linha 10:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 11:** `System.out.println(pizza);`
* **O que faz:** Pega o conteúdo da variável `pizza` (`"mozzarella flat bread pizza"`) e imprime na tela do console.
* **Console:** Imprime:
  ```text
  mozzarella flat bread pizza
  ```

---

### **Linha 12:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 13:** `}`
* **O que faz:** Fecha o método `main`. Fim do fluxo principal.
* **Console:** O sistema encerra com `Process finished with exit code 0`.

---

### **Linha 14:** `static String bakePizza(String bread){`
* **O que faz:** **PRIMEIRA VERSÃO DO `bakePizza` (1 Parâmetro):**
  * Feito para assar pizza apenas com massa.
  * Como a chamada da Linha 9 passou 2 argumentos, este método **fica adormecido na memória** (`no usages`).
* **Console:** **Nada**.

---

### **Linha 15:** `return bread + " pizza";`
* **O que faz:** Montaria a pizza simples de 1 ingrediente (não executada nesta rodada).
* **Console:** **Nada**.

---

### **Linha 16:** `}`
* **O que faz:** Fecha a primeira versão do método `bakePizza`.
* **Console:** **Nada**.

---

### **Linha 17:** `static String bakePizza(String bread, String cheese){`
* **O que faz:** **SEGUNDA VERSÃO DO `bakePizza` (2 Parâmetros):**
  * `static`: chamado diretamente pelo `main`.
  * `String`: devolve um texto.
  * `bakePizza`: **o mesmo nome da primeira versão!**
  * `(String bread, String cheese)`: recebe o tipo de massa e o tipo de queijo.
* **Console:** **Nada**.

---

### **Linha 18:** `return cheese + " " + bread + " pizza";`
* **O que faz:** Concatena `"mozzarella"` $+$ `" "` $+$ `"flat bread"` $+$ `" pizza"` e o comando `return` arremessa o texto resultante de volta para o `main`.
* **Console:** **Nada** (o valor viaja internamente pela memória).

---

### **Linha 19:** `}`
* **O que faz:** Fecha a segunda versão do método `bakePizza`.
* **Console:** **Nada**.

---

### **Linha 20:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
mozzarella flat bread pizza

Process finished with exit code 0
```

---

## 🧀 O Toque Cultural do Bro Code: A Mozzarella
O card da Wikipedia exibido pelo professor no vídeo explica:
* **Mussarela fresca tradicional:** É feita originalmente na Itália a partir do leite de búfala (*mozzarella di bufala*) ou leite de vaca (*fior di latte*).
* O professor usou isso para tornar a aula divertida enquanto reforça o conceito: cada novo ingrediente que você adiciona na pizza representa um **novo parâmetro** no seu método sobrecarregado!
