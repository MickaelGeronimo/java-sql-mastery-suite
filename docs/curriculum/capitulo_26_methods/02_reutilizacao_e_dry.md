# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 2: O Poder da Reutilização (Chamando o Método Múltiplas Vezes)
**Timestamps do vídeo:** `4:07:39` até `4:08:04 / 12:00:00`  
**Tema:** Reutilização prática de código, chamando o método 2 e 3 vezes seguidas, o que significa o aviso cinza `usages` do IntelliJ IDEA, e o fluxo de execução do Java (pular para o método e voltar para o `main`).

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela durante estes minutos:

1. **Imagem 1 (Minuto 4:07:39):**
   * O professor quer provar na prática por que métodos são tão maravilhosos.
   * Em vez de copiar as 4 linhas da música de novo, ele simplesmente digita o nome do método uma segunda vez dentro do `main`:
     ```java
     happyBirthday();
     happyBirthday();
     ```
   * **Repare no detalhe sutil:** Bem em cima da declaração `static void happyBirthday()`, o IntelliJ IDEA exibe automaticamente um texto cinza:
     `2 usages` *(2 usos / chamado em 2 lugares).*
2. **Imagem 2 (Minuto 4:07:54):**
   * O professor clica no Play para rodar.
   * O console exibe a música cantada **duas vezes seguidas**, com um espaço perfeito entre elas (graças ao `\n`):
     ```text
     Happy Birthday to you!
     Happy Birthday dear you!
     You are x years old!
     Happy Birthday to you!

     Happy Birthday to you!
     Happy Birthday dear you!
     You are x years old!
     Happy Birthday to you!
     ```
3. **Imagem 3 (Minuto 4:08:03):**
   * O professor adiciona uma terceira chamada no `main`:
     ```java
     happyBirthday();
     happyBirthday();
     happyBirthday();
     ```
   * O texto cinza do IntelliJ agora muda automaticamente para:
     `3 usages` *(3 usos).*
4. **Imagem 4 (Minuto 4:08:04):**
   * Ele roda o código novamente.
   * O console imprime a música completa cantada **três vezes seguidas**!
   * Para fazer tudo isso, ele precisou de apenas **3 linhas** dentro do `main`, em vez de escrever 12 linhas repetidas!

---

## 🧠 A Mágica da Reutilização: Como o Java Executa Isso?

Imagine que o método `main` é o **Gerente** da empresa, e o método `happyBirthday` é o **Cantor**:

```
[Gerente (main)]                [Cantor (happyBirthday)]
       |                                   |
Linha 7: happyBirthday(); ------------> Canta os 4 versos!
       | <----------------------------- Terminou a música!
Linha 8: happyBirthday(); ------------> Canta os 4 versos de novo!
       | <----------------------------- Terminou a música!
Linha 9: happyBirthday(); ------------> Canta os 4 versos pela 3ª vez!
       | <----------------------------- Terminou a música!
Linha 10: Fim do main!
```

1. Quando o Java chega na linha 7 (`happyBirthday();`), ele **pausa** o `main`, salta para a linha 12 e executa todo o bloco do método.
2. Ao encontrar a chave de fechamento `}`, o Java sabe que a tarefa acabou, **retorna** exatamente para a linha 8 do `main` e continua!
3. Na linha 8, ele vê outro `happyBirthday();`, salta de novo para o método, canta tudo, e volta para a linha 9!
4. Isso se chama **Fluxo de Controle e Chamada de Funções** (*Call Stack*).

---

## 💡 O que significa aquele texto cinza `2 usages` ou `3 usages`?

Muitos iniciantes tentam digitar com o teclado a palavra `usages` e o código dá erro!  
**Atenção:** Você **NÃO** digita aquilo!

* O `usages` é um recurso visual exclusivo do editor IntelliJ IDEA (chamado *CodeLens*).
* Ele serve apenas como uma etiqueta informativa para o programador, dizendo: *"Atenção: este método está sendo chamado/usado em 3 lugares do seu código"*.
* É 100% automático. Se você chamar o método 10 vezes, o IntelliJ vai mostrar sozinho `10 usages`.

---

## 💻 Código Completo Atualizado (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        happyBirthday();
        happyBirthday();
        happyBirthday();
    }

    static void happyBirthday(){
        System.out.println("Happy Birthday to you!");
        System.out.println("Happy Birthday dear you!");
        System.out.println("You are x years old!");
        System.out.println("Happy Birthday to you!\n");
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
* **O que faz:** Ponto de entrada onde o programa começa a rodar.
* **Console:** **Nada**.

---

### **Linha 3:** *(Linha em branco)*

---

### **Linha 4:** `// method = a block of reusable code that is executed when called ()`
* **O que faz:** Comentário conceitual. O computador ignora.
* **Console:** **Nada**.

---

### **Linha 5:** *(Linha em branco)*

---

### **Linha 6:** `happyBirthday();`
* **O que faz:** **1ª Chamada do método.** O Java pausa o `main`, salta para a Linha 12 e canta a música pela 1ª vez.
* **Console:** Imprime o 1º bloco da música:
  ```text
  Happy Birthday to you!
  Happy Birthday dear you!
  You are x years old!
  Happy Birthday to you!
  ```
  *(e desce uma linha extra pelo `\n`)*.

---

### **Linha 7:** `happyBirthday();`
* **O que faz:** **2ª Chamada do método.** O Java salta novamente para a Linha 12 e canta a música pela 2ª vez.
* **Console:** Imprime o 2º bloco da música idêntico ao primeiro.

---

### **Linha 8:** `happyBirthday();`
* **O que faz:** **3ª Chamada do método.** O Java salta pela 3ª vez para a Linha 12 e canta a música pela 3ª vez.
* **Console:** Imprime o 3º bloco da música.

---

### **Linha 9:** `}`
* **O que faz:** Fecha o método `main`. Todas as 3 chamadas foram concluídas!
* **Console:** Finaliza com `Process finished with exit code 0`.

---

### **Linha 10:** *(Linha em branco)*

---

### **Linha 11:** `static void happyBirthday(){`
* **O que faz:** Declaração do método reutilizável. O IntelliJ mostra `3 usages` acima desta linha.
* **Console:** **Nada nessa linha diretamente**.

---

### **Linha 12:** `System.out.println("Happy Birthday to you!");`
* **O que faz:** Imprime o 1º verso.
* **Console:** Imprime o texto e pula linha.

---

### **Linha 13:** `System.out.println("Happy Birthday dear you!");`
* **O que faz:** Imprime o 2º verso.
* **Console:** Imprime o texto e pula linha.

---

### **Linha 14:** `System.out.println("You are x years old!");`
* **O que faz:** Imprime o 3º verso.
* **Console:** Imprime o texto e pula linha.

---

### **Linha 15:** `System.out.println("Happy Birthday to you!\n");`
* **O que faz:** Imprime o 4º verso. O `\n` cria um espaço em branco entre uma cantoria e a próxima.
* **Console:** Imprime o texto e pula 2 linhas.

---

### **Linha 16:** `}`
* **O que faz:** Fecha o corpo do método `happyBirthday` e devolve o controle para o `main`.
* **Console:** **Nada**.

---

### **Linha 17:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que sai no Console (As 3 Músicas Completas):

```text
Happy Birthday to you!
Happy Birthday dear you!
You are x years old!
Happy Birthday to you!

Happy Birthday to you!
Happy Birthday dear you!
You are x years old!
Happy Birthday to you!

Happy Birthday to you!
Happy Birthday dear you!
You are x years old!
Happy Birthday to you!

Process finished with exit code 0
```

---

## 🌟 Comparativo: Antes vs. Depois dos Métodos

| Sem Métodos (O Jeito Amador) | Com Métodos (O Jeito Profissional) |
| :--- | :--- |
| Ocupava **12 linhas** repetitivas no `main`. | Ocupa apenas **3 linhas** simples (`happyBirthday();`). |
| Se quisesse mudar a letra, teria que alterar em **3 lugares diferentes**. | Altera em **1 lugar só** (dentro do método) e todos os 3 se atualizam sozinhos! |
| Código confuso, poluído e difícil de ler. | Código limpo, organizado e profissional. |
