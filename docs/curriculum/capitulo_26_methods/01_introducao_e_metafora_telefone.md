# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 1: O Conceito, Reutilização e Criação do Primeiro Método
**Timestamps do vídeo:** `4:05:00` até `4:07:39 / 12:00:00`  
**Tema:** O que é um método, o princípio DRY (*Don't Repeat Yourself*), a desgraça de copiar e colar código, onde escrever um método (`fora do main, dentro da classe`), o que significa `static void`, e como chamar com `()`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela durante estes minutos:

1. **Imagem 1 (Minuto ~4:05:30):**
   * O professor inicia o capítulo **`26.methods >`**.
   * Ele digita o comentário com a definição oficial de método:
     `// method = a block of reusable code that is executed when called ()`  
     *(Método = um bloco de código reutilizável que é executado quando chamado com parênteses).*
2. **Imagem 2 (Minuto 4:06:04):**
   * O professor quer cantar "Parabéns pra Você" (*Happy Birthday*) três vezes no programa.
   * Ele mostra o **jeito errado e amador**: copia e cola o mesmo bloco de 4 linhas três vezes seguidas dentro do `main`:
     ```java
     System.out.println("Happy Birthday to you!");
     System.out.println("Happy Birthday dear you!");
     System.out.println("You are x years old!");
     System.out.println("Happy Birthday to you!\n");
     // (Repetiu esse mesmo bloco 3 vezes, ocupando as linhas 7 a 20!)
     ```
   * O professor aponta para a tela e diz: *"Olhe que código feio e poluído! Se você quisesse cantar 10 vezes, colaria 10 vezes? E se você quisesse mudar a letra da música, teria que alterar em 10 lugares diferentes?"*.
3. **Imagem 3 (Minuto 4:07:34):**
   * O professor apaga toda aquela sujeira repetida e cria o seu **primeiro método customizado**:
     * Ele vai para **fora do `main`** (na linha 9) e escreve:
       ```java
       static void happyBirthday(){
           System.out.println("Happy Birthday to you!");
           System.out.println("Happy Birthday dear you!");
           System.out.println("You are x years old!");
           System.out.println("Happy Birthday to you!\n");
       }
       ```
     * E lá dentro do `main` (na linha 7), ele apenas **chama** o método pelo nome:
       ```java
       happyBirthday();
       ```
4. **Imagem 4 (Minuto 4:07:39):**
   * O professor clica no Play para rodar.
   * O console executa e canta os parabéns perfeitamente:
     ```text
     Happy Birthday to you!
     Happy Birthday dear you!
     You are x years old!
     Happy Birthday to you!

     Process finished with exit code 0
     ```

---

## 🧠 O que é um Método? (A analogia mais fácil do mundo)

Pense num método como um **botão da cafeteira** ou uma **receita guardada no caderno**:

* **Sem Métodos (O jeito sofrido):** Toda vez que você quiser um café, você tem que comprar o grão, moer, esquentar a água, coar, adoçar... Fazer os 10 passos do zero toda vez!
* **Com Métodos (O jeito profissional):** Você programa a máquina uma única vez e dá a ela o nome de `fazerCafe()`. Daí em diante, sempre que você quiser café, você só aperta um botão: `fazerCafe();` e a máquina faz todo o trabalho duro para você!

### Outra analogia: Uma assistente pessoal
Em vez de você cantar parabéns toda hora, você ensina a sua assistente:
> *"Assistente, toda vez que eu te chamar dizendo `happyBirthday()`, cante aquelas 4 frases!"*

---

## ⚠️ Onde os Métodos Devem Ficar? (Cuidado com a Pegadinha!)

Olhe com **muita atenção** para a estrutura das chaves `{ ... }`:

```java
public class Main { // <--- A CASA TODA (CLASSE)

    public static void main(String[] args){ // <--- O QUARTO PRINCIPAL (MAIN)
        
        happyBirthday(); // CHAMA O MÉTODO AQUI DENTRO!

    } // <--- O QUARTO PRINCIPAL FECHOU AQUI!

    // 👇 O SEU MÉTODO FICA AQUI FORA DO MAIN, MAS DENTRO DA CLASSE!
    static void happyBirthday(){
        // código da música
    }

} // <--- FIM DA CLASSE
```

> 🛑 **REGRA DE OURO DO JAVA:**  
> **Você NUNCA pode criar um método dentro do `main`!**  
> O método `happyBirthday` deve ser criado **fora do `main`**, mas ainda **dentro da classe `Main`**.

---

## 🔤 O que significa `static void happyBirthday()`?

Vamos destrinchar cada palavra:

1. **`static`:** Como o método `main` é `static`, qualquer outro método que seja chamado diretamente por ele na mesma classe precisa ter a palavra `static` antes. *(Não esquente a cabeça com isso agora, o professor vai aprofundar `static` no módulo de Programação Orientada a Objetos).*
2. **`void`:** Significa **"vazio"** em inglês. Diz ao Java: *"Esse método só serve para executar tarefas (imprimir na tela); ele NÃO devolve nenhum número, conta matemática ou texto de volta"*.
3. **`happyBirthday`:** É o **nome** que nós inventamos para o método! Usamos a regra do *camelCase* (primeira letra minúscula, próximas maiúsculas).
4. **`()`:** Os parênteses são a marca registrada de um método. Eles servem para dizer "execute!" e também para receber informações (parâmetros), que veremos nas próximas aulas!
5. **`{ ... }`:** As chaves que guardam as instruções que o método vai realizar.

---

## 💻 Código Completo e Perfeito (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

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
* **O que faz:** Cria a classe principal `Main`.
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** É o ponto onde o Java começa a ler e executar o programa.
* **Console:** **Nada**.

---

### **Linha 3:** *(Linha em branco)*

---

### **Linha 4:** `// method = a block of reusable code that is executed when called ()`
* **O que faz:** Comentário explicando que métodos são blocos reutilizáveis.
* **Console:** **Nada**.

---

### **Linha 5:** *(Linha em branco)*

---

### **Linha 6:** `happyBirthday();`
* **O que faz:** **A CHAMADA DO MÉTODO!**
  * O Java lê esta linha e diz: *"Opa, mandaram chamar o método `happyBirthday`!"*.
  * O Java pausa o `main`, pula imediatamente lá para a Linha 9 e executa tudo o que estiver dentro de `happyBirthday`.
  * Quando terminar, ele volta para cá e segue a vida.
* **Console:** Executa a impressão das 4 linhas da música.

---

### **Linha 7:** `}`
* **O que faz:** Fecha o método `main`. Fim da execução principal.
* **Console:** Finaliza com `Process finished with exit code 0`.

---

### **Linha 8:** *(Linha em branco)*

---

### **Linha 9:** `static void happyBirthday(){`
* **O que faz:** **A DECLARAÇÃO DO MÉTODO.**
  * Define o método chamado `happyBirthday`.
  * `static` = pode ser chamado pelo `main`.
  * `void` = não retorna nenhum dado.
* **Console:** **Nada**.

---

### **Linha 10:** `System.out.println("Happy Birthday to you!");`
* **O que faz:** Imprime o primeiro verso da música e pula linha.
* **Console:** Imprime:
  ```text
  Happy Birthday to you!
  ```

---

### **Linha 11:** `System.out.println("Happy Birthday dear you!");`
* **O que faz:** Imprime o segundo verso e pula linha.
* **Console:** Imprime:
  ```text
  Happy Birthday dear you!
  ```

---

### **Linha 12:** `System.out.println("You are x years old!");`
* **O que faz:** Imprime o terceiro verso ("Você tem x anos de idade!").
* **Console:** Imprime:
  ```text
  You are x years old!
  ```

---

### **Linha 13:** `System.out.println("Happy Birthday to you!\n");`
* **O que faz:** Imprime o último verso. O `\n` no final dá uma quebra de linha extra para afastar a próxima mensagem.
* **Console:** Imprime:
  ```text
  Happy Birthday to you!
  ```
  *(e pula duas linhas)*.

---

### **Linha 14:** `}`
* **O que faz:** Fecha o corpo do método `happyBirthday`.
* **Console:** **Nada**.

---

### **Linha 15:** `}`
* **O que faz:** Fecha a classe `Main`.
* **Console:** **Nada**.

---

## 📺 O que sai no Console:

```text
Happy Birthday to you!
Happy Birthday dear you!
You are x years old!
Happy Birthday to you!

Process finished with exit code 0
```

---

## 🚀 O Grande Poder dos Métodos: Reutilização Instantânea!

Se você quisesse cantar parabéns **3 vezes**, olhe como o seu `main` ficaria incrivelmente curto e elegante:

```java
public static void main(String[] args){
    happyBirthday();
    happyBirthday();
    happyBirthday();
}
```

Apenas **3 linhas**, sem precisar colar 12 linhas repetidas!  
E se amanhã você quiser mudar a letra da música, você **só altera uma única vez** dentro do método `happyBirthday` e todos os 3 lugares se atualizam sozinhos! Isso é programar como um profissional!
