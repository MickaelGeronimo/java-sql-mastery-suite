# 🏠 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 28: Escopo de Variáveis (`variable scope`) — Parte 2: A Definição Oficial, os 2 Tipos (`Local` vs `Class`) e o Primeiro Teste (`1`)
**Timestamps do vídeo:** `4:26:01` até `4:26:37 / 12:00:00`  
**Tema:** A abertura oficial do Capítulo 28! A definição formal de escopo (`where a variable can be accessed`), os dois grandes reinos das variáveis em Java exibidos em letras garrafais na tela (**Local** vs **Class**) e a execução do primeiro teste simples com `int x = 1` imprimindo `1` no console.

---

## 🖼️ Contexto Visual dos 3 Prints (Para quem está lendo no Google Docs)

Se você copiou este arquivo para o seu Google Docs e não tem as imagens ao lado, veja exatamente como o professor **Bro Code** abriu este capítulo:

1. **Imagem 1 (Minuto 4:26:01 — A Definição Escrita):**
   * O professor limpa o editor do IntelliJ IDEA e escreve a definição oficial na Linha 6 dentro do `main`:
     ```java
     // variable scope = where a variable can be accessed
     ```
   * O cursor do mouse está piscando logo após a palavra `accessed`.

2. **Imagem 2 (O Gráfico em Destaque na Tela):**
   * O professor insere uma animação com duas palavras gigantescas em fonte estilo desenho animado no centro da tela preta:
     # **Local** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; **Class**
   * Ele faz isso para avisar que todo o capítulo será dividido nessas duas categorias fundamentais de escopo que todo programador Java precisa dominar.

3. **Imagem 3 (Minuto ~4:26:37 — O Primeiro Teste Prático):**
   * O professor escreve o código mais simples possível dentro do `main`:
     ```java
     int x = 1;

     System.out.println(x);
     ```
   * Ele aperta o botão verde de **Play** (`Run 'Main'`).
   * A janela preta do console abre e imprime em verde brilhante o número:
     ```text
     1
     ```
   * Logo abaixo, a finalização de sucesso:
     ```text
     Process finished with exit code 0
     ```

---

## 🧠 A Definição Fundamental: O Que É "Variable Scope"?

O professor resumiu na Linha 6 em apenas 7 palavras em inglês:
> **`variable scope = where a variable can be accessed`**  
> *(Escopo de variável = onde uma variável pode ser acessada / enxergada dentro do programa).*

### 🗺️ A Metáfora da Carteira de Motorista vs Passaporte:
* **Escopo Local (A Carteira de Motorista Municipal):** Só vale dentro da sua própria cidade (dentro do próprio método). Se você atravessar a fronteira para outro método, ela não vale nada e ninguém sabe quem você é.
* **Escopo de Classe (O Passaporte Global):** Fica guardado do lado de fora dos métodos, no topo da classe. Qualquer método daquela classe tem acesso a ela!

---

## 🥊 Os Dois Tipos de Escopo em Java (`Local` vs `Class`)

| Tipo de Escopo | Onde é Declarada? | Quem Consegue Usar? | Tempo de Vida na Memória |
| :---: | :--- | :--- | :--- |
| **`Local`** | **DENTRO** de um método (entre as chaves `{ }` dele). | Apenas o próprio método onde ela nasceu. | Nasce quando o método começa e **morre quando o método fecha `}`**. |
| **`Class`** | **FORA** dos métodos (direto no corpo da `class Main`). | **TODOS** os métodos da classe podem enxergar e usar! | Fica viva na memória enquanto o programa estiver rodando. |

*(Nas próximas aulas veremos as variáveis de classe em detalhes; nesta aula o foco é ver a variável local funcionando no `main`).*

---

## 💻 Código Completo e Idêntico ao da Imagem 3 (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // variable scope = where a variable can be accessed

        int x = 1;

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

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto de partida obrigatório onde o Java começa a ler e rodar os comandos.
* **Console:** **Nada**.

---

### **Linhas 3 a 5:** *(Linhas em branco)*
* **Console:** **Nada**.

---

### **Linha 6:** `// variable scope = where a variable can be accessed`
* **O que faz:** Comentário conceitual deixado pelo professor Bro Code. O computador ignora completamente.
* **Console:** **Nada**.

---

### **Linha 7:** `int x = 1;`
* **O que faz:** **DECLARAÇÃO DE UMA VARIÁVEL LOCAL:**
  1. O computador reserva um espaço na memória RAM para um número inteiro (`int`).
  2. Batiza essa caixinha com o nome `x`.
  3. Guarda o valor `1` dentro dela.
  4. Como essa caixinha foi criada **dentro do `main`**, ela é uma **variável local** do `main`.
* **Console:** **Nada** (o valor foi guardado na memória).

---

### **Linha 8:** *(Linha em branco)*
* **Console:** **Nada**.

---

### **Linha 9:** `System.out.println(x);`
* **O que faz:** **A IMPRESSÃO DO VALOR:**
  1. O Java procura onde está a variável `x`.
  2. Ele encontra a variável local criada na Linha 7 contendo o valor `1`.
  3. Envia o número `1` para a janela preta do console e pula para a linha de baixo.
* **Console:** Imprime:
  ```text
  1
  ```

---

### **Linha 10:** `}`
* **O que faz:** Fecha o método `main`. Neste momento exato, a variável `x` **é destruída da memória**. Fim do programa.
* **Console:** O sistema exibe `Process finished with exit code 0`.

---

### **Linha 11:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que Sai no Console ao Executar:

```text
1

Process finished with exit code 0
```

---

## 🎯 Por Que Esse Teste Tão Simples Foi Feito?
Para provar a base antes de complicar:
* Quando você declara `int x = 1;` dentro do `main`, qualquer linha dentro do `main` (como `System.out.println(x)`) consegue usá-la com facilidade.
* A pergunta que o professor vai explorar a seguir é:  
  *"E se outro método tentar acessar esse `x`? E se quisermos que vários métodos compartilhem a mesma variável?"*  
  É aí que entra o escopo de classe (**Class Scope**)!
