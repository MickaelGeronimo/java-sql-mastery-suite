# 🍕 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 27: Métodos Sobrecarregados (`overloaded methods`) — Parte 4: O Exemplo Clássico da Pizzaria! O Método `bakePizza` com 1 Ingrediente
**Timestamps do vídeo:** `4:23:45` até `4:23:55 / 12:00:00`  
**Tema:** Por que o capítulo tem o emoji de pizza 🍕? Transição dos exemplos matemáticos para um cenário do mundo real: a pizzaria! Construção do método `bakePizza(String bread)`, concatenação de ingredientes (`bread + " pizza"`) e a saída `flat bread pizza` no console.

---

## 🖼️ Contexto Visual dos 3 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, aqui está tudo o que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 4:23:45 — Digitando o Código da Pizzaria):**
   * O professor apagou os métodos matemáticos `add` anteriores e começou um exemplo muito mais divertido e intuitivo do dia a dia.
   * Na Linha 14, ele cria o método para assar pizza:
     ```java
     static String bakePizza(String bread){
         return bread + " pizza";
     }
     ```
   * No `main`, ele começa a chamar o método:
     ```java
     String pizza = bakePizza("flat bread");
     System.out.println();
     ```
   * O cursor do mouse está piscando dentro do `println()`, bem no momento em que ele vai colocar a variável `pizza` dentro dele.

2. **Imagem 2 (Preparando a Execução):**
   * O professor completa a linha 11: `System.out.println(pizza);`.
   * Ele move o cursor do mouse até o topo direito da janela do IntelliJ IDEA e clica no botão verde de **Play** (`Run 'Main'`).
   * Na barra de status inferior aparece a mensagem `Starting Main`.

3. **Imagem 3 (Minuto 4:23:55 — O Resultado no Console):**
   * O programa executa e a janela preta do console sobe na metade inferior da tela.
   * Em verde brilhante, o console exibe o nome da nossa primeira pizza montada:
     ```text
     flat bread pizza
     ```
   * Logo abaixo, a confirmação do sistema:
     ```text
     Process finished with exit code 0
     ```

---

## 🍕 A Ideia da Pizzaria no Mundo Real

Por que usar uma pizzaria para ensinar **Métodos Sobrecarregados**?  
Porque poucas coisas no mundo real variam tanto de "ingredientes" quanto uma pizza!

Pense como um cliente fazendo um pedido:
* Cliente 1: *"Quero só uma massa assada simples (pão folha / flat bread)."* $\rightarrow$ 1 ingrediente (`bread`).
* Cliente 2: *"Quero massa e queijo!"* $\rightarrow$ 2 ingredientes (`bread, cheese`).
* Cliente 3: *"Quero massa, queijo e molho!"* $\rightarrow$ 3 ingredientes (`bread, cheese, sauce`).
* Cliente 4: *"Quero a pizza completa com calabresa!"* $\rightarrow$ 4 ingredientes (`bread, cheese, sauce, topping`).

Em vez de criar 4 métodos com nomes confusos (`bakePizza1`, `bakePizza2`, `bakePizza3`), nós vamos criar **o mesmo método `bakePizza` várias vezes**, cada um com uma quantidade diferente de ingredientes!

---

## ⚙️ A Anatomia do Método `bakePizza` (1 Ingrediente)

$$\text{static}\ \underbrace{\text{String}}_{\text{Devolve um Texto}}\ \underbrace{\text{bakePizza}}_{\text{Nome do Método}}(\underbrace{\text{String bread}}_{\text{Tipo de Massa}})\{\ \underbrace{\text{return bread + " pizza";}}_{\text{Monta e Devolve}}\ \}$$

* **Parâmetro:** Recebe `"flat bread"` (pão folha / massa fina).
* **Concatenação:** `"flat bread"` $+$ `" pizza"` $=$ `"flat bread pizza"`.
* **Retorno:** O `return` entrega esse texto montado de volta para quem pediu!

---

## 💻 Código Completo e Idêntico ao da Tela (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // overloaded methods = methods that share the same name,
        //                      but different parameters
        //                      signature = name + parameters

        String pizza = bakePizza("flat bread");

        System.out.println(pizza);
    }

    static String bakePizza(String bread){
        return bread + " pizza";
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal onde fica o código Java.
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto de entrada obrigatório onde o Java começa a ler os comandos.
* **Console:** **Nada**.

---

### **Linhas 3 a 8:** *(Linhas em branco e comentários conceituais)*
* **Linhas 5 a 7:** Anotações teóricas do professor sobre métodos sobrecarregados e assinatura (`signature = name + parameters`). O computador ignora.
* **Console:** **Nada**.

---

### **Linha 9:** `String pizza = bakePizza("flat bread");`
* **O que faz:** **A PRIMEIRA CHAMADA DO MÉTODO DA PIZZA:**
  1. O Java vê `bakePizza("flat bread")` e pausa o `main`.
  2. Salta até a **Linha 14** (onde o método `bakePizza` está declarado).
  3. Entrega o texto `"flat bread"` para o parâmetro `bread`.
  4. Na Linha 15, junta o texto: `"flat bread"` $+$ `" pizza"` = `"flat bread pizza"`.
  5. O comando `return` arremessa esse texto montado de volta para a Linha 9.
  6. O texto cai dentro da variável `String pizza`.
* **Console:** **Nada** (a pizza foi "montada" e guardada na memória RAM, mas ainda não foi exibida).

---

### **Linha 10:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 11:** `System.out.println(pizza);`
* **O que faz:** Pega o texto guardado na variável `pizza` (`"flat bread pizza"`) e imprime na tela preta do console, pulando uma linha no final.
* **Console:** Imprime:
  ```text
  flat bread pizza
  ```

---

### **Linha 12:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 13:** `}`
* **O que faz:** Fecha o método `main`. Fim do fluxo principal do programa.
* **Console:** O sistema encerra com `Process finished with exit code 0`.

---

### **Linha 14:** `static String bakePizza(String bread){`
* **O que faz:** **A DECLARAÇÃO DO MÉTODO DA PIZZA:**
  * `static`: permite ser chamado sem instanciar objetos.
  * `String`: o compromisso obrigatório de que ele **devolve um texto**.
  * `bakePizza`: nome da ferramenta ("assar pizza").
  * `(String bread)`: o parâmetro que recebe o tipo de massa (recebeu `"flat bread"`).
* **Console:** **Nada**.

---

### **Linha 15:** `return bread + " pizza";`
* **O que faz:** Concatena o nome do pão com a palavra `" pizza"` e o comando `return` cospe esse resultado de volta para o `main`.
* **Console:** **Nada** (o valor viaja internamente pela memória).

---

### **Linha 16:** `}`
* **O que faz:** Fecha o método `bakePizza`.
* **Console:** **Nada**.

---

### **Linha 17:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
flat bread pizza

Process finished with exit code 0
```

---

## 🔮 O Que Esperar dos Próximos Prints?
Agora que a base da pizzaria está pronta, o professor vai começar a **sobrecarregar (`overload`)** o método `bakePizza`:
* Ele vai criar outro `bakePizza(String bread, String cheese)`.
* Depois outro `bakePizza(String bread, String cheese, String sauce)`.
* E depois outro `bakePizza(String bread, String cheese, String sauce, String topping)`!

Isso vai ilustrar de forma definitiva e inesquecível como métodos sobrecarregados funcionam na vida real!
