# 🍕 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 27: Métodos Sobrecarregados (`overloaded methods`) — Parte 6: A Pizza de Pepperoni! 3 Métodos `bakePizza` e o Retorno a Teoria
**Timestamps do vídeo:** `4:25:18` até `4:25:55 / 12:00:00`  
**Tema:** A pizzaria ganha seu 3º nível: o método `bakePizza(String bread, String cheese, String topping)`, a montagem da pizza de pepperoni com queijo e massa, o resultado `pepperoni mozzarella flat bread pizza` no console e a recapitulação da definição de métodos sobrecarregados.

---

## 🖼️ Contexto Visual dos 3 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem os prints ao lado, veja exatamente a sequência de ações do professor **Bro Code**:

1. **Imagem 1 (Minuto 4:25:18 — Adicionando o Pepperoni):**
   * O professor cria a terceira versão do método `bakePizza` nas Linhas 20 a 22:
     ```java
     static String bakePizza(String bread, String cheese, String topping){
         return topping + " " + cheese + " " + bread + " pizza";
     }
     ```
   * No `main` (Linha 9), ele chama passando os 3 ingredientes:
     ```java
     String pizza = bakePizza("flat bread", "mozzarella", "pepperoni");
     ```
   * Observe as etiquetas cinzas do IntelliJ IDEA:
     * Versão 1 (`bread`): `no usages` (adormecido).
     * Versão 2 (`bread, cheese`): `no usages` (adormecido).
     * Versão 3 (`bread, cheese, topping`): `1 usage` (ativo e conectado!).
   * O cursor do mouse está sobre o botão verde de **Play** (`Run 'Main'`).

2. **Imagem 2 (Minuto 4:25:33 — O Resultado no Console):**
   * O professor executou o programa.
   * A janela preta do console abriu na metade inferior da tela.
   * Em verde brilhante, o console exibiu a pizza montada:
     ```text
     pepperoni mozzarella flat bread pizza
     ```
   * Logo abaixo, a confirmação do sistema:
     ```text
     Process finished with exit code 0
     ```

3. **Imagem 3 (Minuto 4:25:55 — O Destaque na Definição Teórica):**
   * Após ver o código funcionando no console, o professor fecha o console e volta o olhar dos alunos para o topo do código (Linha 5).
   * Ele seleciona com o mouse em azul a expressão `overloaded methods` nos comentários:
     ```java
     // overloaded methods = methods that share the same name,
     //                      but different parameters
     //                      signature = name + parameters
     ```
   * Ele faz isso para reforçar pedagogicamente: *"Vejam só na prática o que significa a nossa definição! Temos três métodos com o mesmíssimo nome `bakePizza`, mas cada um com parâmetros diferentes. O Java nunca se confunde!"*.

---

## 🍕 O Cardápio em Expansão: 3 Métodos com o Mesmo Nome!

Olhe como a pizzaria agora tem 3 opções no cardápio:

| Opção | Assinatura Oficial | Ingredientes | O que devolve? |
| :---: | :--- | :--- | :--- |
| **1** | `bakePizza(String)` | Só Massa | `"flat bread pizza"` |
| **2** | `bakePizza(String, String)` | Massa + Queijo | `"mozzarella flat bread pizza"` |
| **3** | `bakePizza(String, String, String)` | Massa + Queijo + Recheio | `"pepperoni mozzarella flat bread pizza"` |

Quando o `main` executa:
$$\text{bakePizza}(\text{"flat bread"},\ \text{"mozzarella"},\ \text{"pepperoni"})$$

O Java faz a contagem dos argumentos:
1. Tem 3 textos passados.
2. A única versão que aceita 3 textos é a Versão 3 (`bread, cheese, topping`).
3. O Java vai direto nela, monta a frase e devolve o texto!

---

## 🔍 A Ordem dos Ingredientes na Montagem:

Olhe a Linha 21:
```java
return topping + " " + cheese + " " + bread + " pizza";
```
* `topping` = `"pepperoni"`
* `cheese` = `"mozzarella"`
* `bread` = `"flat bread"`
* Resultado: `"pepperoni"` $+$ `" "` $+$ `"mozzarella"` $+$ `" "` $+$ `"flat bread"` $+$ `" pizza"`  
  $=$ `"pepperoni mozzarella flat bread pizza"`.

---

## 💻 Código Completo e Idêntico ao da Tela (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // overloaded methods = methods that share the same name,
        //                      but different parameters
        //                      signature = name + parameters

        String pizza = bakePizza("flat bread", "mozzarella", "pepperoni");

        System.out.println(pizza);
    }

    static String bakePizza(String bread){
        return bread + " pizza";
    }

    static String bakePizza(String bread, String cheese){
        return cheese + " " + bread + " pizza";
    }

    static String bakePizza(String bread, String cheese, String topping){
        return topping + " " + cheese + " " + bread + " pizza";
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
* **O que faz:** O método principal onde o computador começa a ler os comandos.
* **Console:** **Nada**.

---

### **Linhas 3 a 8:** *(Linhas em branco e comentários conceituais)*
* **Linhas 5 a 7:** Comentários com a definição de métodos sobrecarregados e a fórmula da assinatura (`signature = name + parameters`). O computador ignora.
* **Console:** **Nada**.

---

### **Linha 9:** `String pizza = bakePizza("flat bread", "mozzarella", "pepperoni");`
* **O que faz:** **A CHAMADA COM 3 INGREDIENTES:**
  1. O compilador vê a chamada `bakePizza` com 3 textos.
  2. Ignora a Linha 14 (1 parâmetro) e a Linha 17 (2 parâmetros).
  3. Salta direto para a **Linha 20** (a versão de 3 parâmetros).
  4. Entrega `"flat bread"` para `bread`, `"mozzarella"` para `cheese` e `"pepperoni"` para `topping`.
  5. Na Linha 21, monta o texto concatenado com espaços.
  6. O `return` devolve o texto pronto para a Linha 9.
  7. O texto é guardado na variável `String pizza`.
* **Console:** **Nada** (o texto está guardado na memória RAM).

---

### **Linha 10:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 11:** `System.out.println(pizza);`
* **O que faz:** Pega o texto guardado na variável `pizza` e imprime na tela do console.
* **Console:** Imprime:
  ```text
  pepperoni mozzarella flat bread pizza
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
* **O que faz:** **VERSÃO 1 (1 Parâmetro):**
  * Feito para pizza só de massa. Fica adormecido na memória (`no usages`).
* **Console:** **Nada**.

---

### **Linha 15:** `return bread + " pizza";`
* **O que faz:** Devolveria a pizza simples (não executado).
* **Console:** **Nada**.

---

### **Linha 16:** `}`
* **O que faz:** Fecha a Versão 1.
* **Console:** **Nada**.

---

### **Linha 17:** `static String bakePizza(String bread, String cheese){`
* **O que faz:** **VERSÃO 2 (2 Parâmetros):**
  * Feito para pizza com massa e queijo. Fica adormecido na memória (`no usages`).
* **Console:** **Nada**.

---

### **Linha 18:** `return cheese + " " + bread + " pizza";`
* **O que faz:** Devolveria a pizza com queijo (não executado).
* **Console:** **Nada**.

---

### **Linha 19:** `}`
* **O que faz:** Fecha a Versão 2.
* **Console:** **Nada**.

---

### **Linha 20:** `static String bakePizza(String bread, String cheese, String topping){`
* **O que faz:** **VERSÃO 3 (3 Parâmetros):**
  * `static`: permite chamada direta pelo `main`.
  * `String`: devolve um texto.
  * `bakePizza`: o mesmo nome das outras duas versões!
  * `(String bread, String cheese, String topping)`: recebe os 3 ingredientes.
* **Console:** **Nada**.

---

### **Linha 21:** `return topping + " " + cheese + " " + bread + " pizza";`
* **O que faz:** Concatena os três ingredientes e arremessa o texto resultante de volta para quem chamou.
* **Console:** **Nada** (o valor viaja internamente pela memória).

---

### **Linha 22:** `}`
* **O que faz:** Fecha a Versão 3.
* **Console:** **Nada**.

---

### **Linha 23:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
pepperoni mozzarella flat bread pizza

Process finished with exit code 0
```

---

## 🧠 A Lição Final da Imagem 3: Por Que o Professor Voltou ao Comentário?
O professor fez questão de selecionar a linha de comentário para gravar na mente do aluno:
* Você **não precisa** inventar nomes novos como `bakePizzaWithCheese` ou `bakePizzaWithPepperoni`.
* Dê o mesmo nome intuitivo: **`bakePizza`**.
* Deixe o **Java trabalhar por você**, descobrindo qual versão rodar com base em quantos ingredientes você entregou!
