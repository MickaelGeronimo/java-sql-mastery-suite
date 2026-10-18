# 🏠 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 28: Escopo de Variáveis (`variable scope`) — Parte 4: O `main` Acessando a Variável de Classe, o Mistério do `2 usages` e a Saída `3`
**Timestamps do vídeo:** `4:29:04` até `4:29:07 / 12:00:00`  
**Tema:** Provando o verdadeiro poder do **Class Scope (Escopo de Classe)**: agora é o próprio método `main` que imprime `x` diretamente, o que significa a etiqueta cinza `2 usages` no topo do código e a confirmação de que múltiplos métodos podem compartilhar a mesma variável global.

---

## 🖼️ Contexto Visual dos 2 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem os prints ao lado, veja exatamente a mudança que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 4:29:04 — O `main` Imprimindo `x` e o `2 usages`):**
   * Na aula anterior, o `main` chamava `doSomething()`, e era o `doSomething()` que imprimia `x`.
   * Agora, o professor muda a estratégia dentro do `main` (Linha 8):
     ```java
     public static void main(String[] args){
         System.out.println(x);
     }
     ```
   * Ele não chama mais o `doSomething()`! Ele manda o próprio `main` imprimir `x`!
   * **O detalhe crucial da tela:** Olhe para a Linha 4:
     ```java
     static int x = 3; //CLASS 2 usages
     ```
     O IntelliJ IDEA agora exibe a etiqueta cinza **`2 usages`** *(2 usos)*!
     Por quê? Porque a variável `x` está sendo usada na **Linha 8** (pelo `main`) E na **Linha 12** (pelo `doSomething`).
   * Como o `doSomething` não é mais chamado pelo `main`, ele fica com a etiqueta cinza `no usages` na Linha 10.
   * O cursor do mouse está no botão verde de **Play** (`Run 'Main'`).

2. **Imagem 2 (Minuto 4:29:07 — O Resultado `3` no Console):**
   * O professor clica no Play.
   * A janela preta do console abre e imprime em verde brilhante (selecionado em azul com o mouse):
     ```text
     3
     ```
   * Logo abaixo, o encerramento com sucesso:
     ```text
     Process finished with exit code 0
     ```

---

## 🧠 A Grande Revelação: O Que Significa a Etiqueta "2 usages"?

Todo iniciante olha para aquele texto cinza flutuante no IntelliJ IDEA e se pergunta o que é. Vamos desmistificar:

$$\text{static int x = 3; //CLASS}\quad \underbrace{\text{2 usages}}_{\text{Dica do IntelliJ}}$$

O IntelliJ é uma IDE tão inteligente que ele rastreia todo o seu código em tempo real e te conta:
> *"Bro Code, essa variável `x` que você criou aqui em cima está sendo lida em **2 lugares diferentes** do seu código!"*

1. **Lugar 1:** Linha 8, dentro do `main` $\rightarrow$ `System.out.println(x);`
2. **Lugar 2:** Linha 12, dentro do `doSomething()` $\rightarrow$ `System.out.println(x);`

Isso prova na prática por que ela se chama **Variável de Classe (`Class Scope`)**:
* Ela não pertence apenas ao `main`.
* Ela não pertence apenas ao `doSomething`.
* **Ela pertence à classe inteira (`Main`)!** Qualquer método que more nessa classe pode esticar o braço e pegar o valor dela!

---

## 🏠 A Metáfora da Geladeira na Cozinha:
* **Variável Local:** É um chocolate que você guarda trancado no bolso da sua calça. Só você pode comer.
* **Variável de Classe (`CLASS`):** É uma pizza colocada na **geladeira comunitária da casa**.
  * O morador do Quarto 1 (`main`) pode ir até a geladeira e pegar um pedaço.
  * O morador do Quarto 2 (`doSomething`) também pode ir até a mesma geladeira e pegar um pedaço!
  * Ambos compartilham o mesmo alimento!

---

## 💻 Código Completo e Idêntico ao da Tela (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {

    static int x = 3; // CLASS (compartilhado por todos os métodos)

    public static void main(String[] args){

        System.out.println(x);
    }

    static void doSomething(){

        System.out.println(x);
    }
}
```

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Abre a classe principal `Main`.
* **Console:** **Nada**.

---

### **Linhas 2 e 3:** *(Linhas em branco)*
* **Console:** **Nada**.

---

### **Linha 4:** `static int x = 3; //CLASS`
* **O que faz:** **A DECLARAÇÃO DA VARIÁVEL DE CLASSE:**
  1. Cria uma variável na memória chamada `x` e guarda o valor `3`.
  2. `static`: permite ser acessada por qualquer método estático da classe.
  3. Como está fora dos métodos, ela fica disponível para todos os métodos da classe `Main`.
* **Console:** **Nada** (o valor fica guardado na memória).

---

### **Linha 5:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 6:** `public static void main(String[] args){`
* **O que faz:** Ponto de partida obrigatório onde o programa começa a rodar.
* **Console:** **Nada**.

---

### **Linha 7:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 8:** `System.out.println(x);`
* **O que faz:** **O `main` IMPRIMINDO O `x` GLOBAL:**
  1. O `main` não tem nenhuma variável local chamada `x` declarada dentro dele.
  2. O Java olha para o escopo de classe e encontra `static int x = 3;` na Linha 4.
  3. O Java pega o valor `3` e imprime na tela preta do console!
* **Console:** Imprime:
  ```text
  3
  ```

---

### **Linha 9:** `}`
* **O que faz:** Fecha o método `main`. Fim da execução principal.
* **Console:** O sistema encerra com `Process finished with exit code 0`.

---

### **Linha 10:** `static void doSomething(){`
* **O que faz:** Método secundário. Desta vez ele **não é chamado** pelo `main`, por isso fica adormecido na memória (`no usages`).
* **Console:** **Nada**.

---

### **Linha 11:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 12:** `System.out.println(x);`
* **O que faz:** Também usa o `x` da classe (o segundo uso de `x`), mas como o método não foi chamado pelo `main`, esta linha não roda nesta execução.
* **Console:** **Nada**.

---

### **Linha 13:** `}`
* **O que faz:** Fecha o método `doSomething`.
* **Console:** **Nada**.

---

### **Linha 14:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
3

Process finished with exit code 0
```

---

## 🏆 Resumo Definitivo do Capítulo 28: Escopo de Variáveis

| Característica | Variável Local (`LOCAL`) | Variável de Classe (`CLASS`) |
| :--- | :--- | :--- |
| **Onde fica?** | Dentro de um método `{ ... }` | Fora dos métodos, no corpo da classe |
| **Sintaxe** | `int x = 1;` | `static int x = 3;` |
| **Quem tem acesso?** | Apenas o próprio método onde ela foi criada. | **Qualquer método** dentro daquela classe (`main`, `doSomething`, etc.). |
| **Tempo de vida** | Nasce no início do método e morre no `}` do método. | Fica viva na memória enquanto o programa rodar. |
| **Prioridade (Shadowing)** | **Vence a disputa!** Se o método tiver uma local, ele ignora a de classe. | Só é usada se o método **não tiver** uma variável local própria. |
