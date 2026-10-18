# 🏠 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 28: Escopo de Variáveis (`variable scope`) — Parte 3: Variáveis de Classe (`CLASS`), a Regra do Sombreamento (`Shadowing`) e o Resultado `3`
**Timestamps do vídeo:** `4:28:40` até `4:28:58 / 12:00:00`  
**Tema:** O segundo grande tipo de escopo: **Class Scope (Escopo de Classe)**! Criação de `static int x = 3;`, a regra de quem tem prioridade quando uma variável local e uma global têm o mesmo nome (*Shadowing/Sombreamento*) e como a remoção das variáveis locais fez o método `doSomething()` imprimir `3` no console.

---

## 🖼️ Contexto Visual dos 4 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja exatamente a sequência de acontecimentos que o professor **Bro Code** demonstrou na tela:

1. **Imagem 1 (Criando a Variável de Classe `x = 3`):**
   * O professor vai no topo do arquivo, logo abaixo da linha `public class Main {`, e declara:
     ```java
     static int x = 3;
     ```
   * **Repare na posição:** Ela está **FORA** do `main` e **FORA** do `doSomething()`. Ela mora diretamente dentro da classe!
   * Repare que o IntelliJ exibe a etiqueta cinza `no usages` (nenhum uso) ao lado do `x = 3`. Por quê? Porque dentro do `main` ainda existe `int x = 1;` e dentro do `doSomething` ainda existe `int x = 2;`! Os métodos ainda estão usando suas próprias variáveis locais!

2. **Imagem 2 (Minuto 4:28:40 — Identificando com Comentários):**
   * O professor adiciona comentários ao lado de cada `x` para deixar tudo cristalino para os alunos:
     * Linha 4: `static int x = 3; //CLASS`
     * Linha 8: `int x = 1; //LOCAL`
     * Linha 13: `int x = 2; //LOCAL`

3. **Imagem 3 (Minuto 4:28:54 — A Faxina: Apagando as Variáveis Locais):**
   * O professor **apaga** a linha `int x = 1;` de dentro do `main`.
   * O professor **apaga** a linha `int x = 2;` de dentro do `doSomething()`.
   * Agora, dentro do `doSomething()`, sobrou apenas:
     ```java
     System.out.println(x);
     ```
   * **A mágica do IntelliJ:** A etiqueta da Linha 4 muda instantaneamente de `no usages` para **`1 usage`**! Como o `doSomething` não tem mais um `x` próprio, ele foi obrigado a "olhar para cima" e usar a variável da classe!

4. **Imagem 4 (Minuto 4:28:58 — O Resultado no Console):**
   * O professor clica no botão verde de **Play** (`Run 'Main'`).
   * A janela preta do console abre e imprime em verde brilhante (com a seleção azul do mouse em cima):
     ```text
     3
     ```
   * Logo abaixo, a confirmação do sistema:
     ```text
     Process finished with exit code 0
     ```

---

## 🧠 A Regra de Ouro: Como o Java Escolhe Qual Variável Usar?

O que acontece quando você tem uma variável de classe (`x = 3`) E uma variável local (`x = 2`) com o mesmíssimo nome?

### 👑 O Conceito de "Shadowing" (Sombreamento):
> **O Java sempre dá prioridade máxima para a variável LOCAL!**

Pense na metáfora da sua casa:
* A variável de classe `static int x = 3` é como a **luz do Sol** lá no céu (visível para o bairro todo).
* A variável local `int x = 2` é como a **luz do abajur** dentro do seu quarto fechado.

1. **Se o abajur estiver ligado (tem variável local):**  
   Você usa a luz do seu abajur (`x = 2`). O abajur faz uma "sombra" na luz do Sol lá fora. O método nem liga para o `x = 3` da classe.
2. **Se você desligar o abajur (apagar a variável local):**  
   O quarto fica sem luz própria. Você é obrigado a abrir a janela e usar a luz do Sol lá de fora (`x = 3`)!

Foi exatamente isso que o professor fez entre a Imagem 2 e a Imagem 3: ao deletar `int x = 2`, o método `doSomething()` usou o `x = 3` da classe!

---

## 🔒 Variável Local vs 🌐 Variável de Classe (`Class Scope`)

| Propriedade | Variável Local (`LOCAL`) | Variável de Classe (`CLASS`) |
| :--- | :--- | :--- |
| **Onde é declarada?** | Dentro de um método `{ ... }` | Fora dos métodos, no corpo da classe |
| **Palavra-chave necessária?** | Nenhuma (só `int x = 1;`) | Precisa da palavra **`static`** |
| **Quem pode usar?** | Só o próprio método | **Todos os métodos da classe** |
| **Tempo de vida** | Morre quando o método acaba | Vive enquanto o programa estiver rodando |
| **Prioridade de uso** | **Alta (tem preferência)** | Secundária (só usada se não houver local) |

---

## 💻 Código Completo e Corrigido da Imagem 3 (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {

    static int x = 3; // CLASS (visível para todos os métodos da classe)

    public static void main(String[] args){

        doSomething();
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

### **Linha 4:** `static int x = 3; // CLASS`
* **O que faz:** **A DECLARAÇÃO DA VARIÁVEL DE CLASSE:**
  1. Cria uma variável na memória chamada `x` e guarda o valor `3`.
  2. `static`: significa que ela pertence à classe como um todo e pode ser acessada diretamente por métodos estáticos sem precisar instanciar objetos.
  3. Como está **fora de todos os métodos**, ela tem **Escopo de Classe (`Class Scope`)**. Tanto o `main` quanto o `doSomething` podem usá-la!
* **Console:** **Nada** (o valor fica guardado na memória).

---

### **Linha 5:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 6:** `public static void main(String[] args){`
* **O que faz:** Ponto de entrada onde o Java inicia o programa.
* **Console:** **Nada**.

---

### **Linha 7:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 8:** `doSomething();`
* **O que faz:** **A CHAMADA DO MÉTODO:**
  1. O Java vê a chamada `doSomething()`.
  2. Pausa o `main` e salta para a **Linha 10**.
* **Console:** **Nada** (o salto é interno).

---

### **Linha 9:** `}`
* **O que faz:** Fecha o método `main`. Quando o programa voltar de `doSomething`, ele finaliza aqui.
* **Console:** O sistema encerra com `Process finished with exit code 0`.

---

### **Linha 10:** `static void doSomething(){`
* **O que faz:** Abre o método `doSomething`.
* **Console:** **Nada**.

---

### **Linha 11:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 12:** `System.out.println(x);`
* **O que faz:** **A IMPRESSÃO COM ESCOPO DE CLASSE:**
  1. O Java precisa imprimir o valor de `x`.
  2. Primeiro, ele procura: *"Existe algum `x` local aqui dentro do `doSomething()`?"* $\rightarrow$ **Não existe** (o professor apagou!).
  3. Como não encontrou localmente, ele olha para fora, no escopo da classe: *"Existe algum `x` na classe?"* $\rightarrow$ **Sim! `static int x = 3;` na Linha 4!**
  4. O Java pega esse `3` da classe e imprime na tela preta do console!
* **Console:** Imprime:
  ```text
  3
  ```

---

### **Linha 13:** `}`
* **O que faz:** Fecha o método `doSomething` e retorna para a Linha 8 do `main`.
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

## 🎯 Resumo da Diferença dos Resultados:

* **Na aula anterior:** `doSomething()` tinha `int x = 2;` $\rightarrow$ O console imprimiu **`2`** (a variável local venceu).
* **Nesta aula:** O professor apagou a variável local $\rightarrow$ O console imprimiu **`3`** (o Java usou a variável de classe).
