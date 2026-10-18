# 📞 Curso de Java para Iniciantes — Bro Code
## 📌 Capítulo 26: Métodos (`methods`) — Parte 11: Retorno de Valores (`return`), Adeus ao `void` e o Método `square`
**Timestamps do vídeo:** `4:14:25 / 12:00:00`  
**Tema:** A maior virada de chave sobre métodos: a diferença entre `void` e métodos com Retorno (`return`), o cálculo de elevar ao quadrado (`square`), guardando o resultado em variável (`double result`) vs. imprimindo direto no `println`.

---

## 🖼️ Contexto Visual dos Prints (Para quem está lendo no Google Docs)

Se você copiou este material para o seu Google Docs e não tem as imagens do vídeo ao lado, aqui está a descrição exata do que o professor **Bro Code** fez na tela:

1. **Imagem 1 (Minuto 4:14:25):**
   * O professor quer ensinar que métodos não servem apenas para imprimir frases na tela. Eles servem para **fazer contas e DEVOLVER a resposta** para quem pediu!
   * Repare que ele "encolheu/dobrou" o método `happyBirthday` anterior (ele aparece recolhido como `{...}` na linha 10 para não ocupar espaço na tela).
   * Ele cria um novo método embaixo:
     ```java
     static double square(double number){
         return number * number;
     }
     ```
   * **Repare na grande novidade:** Ele **NÃO USOU `void`**! Ele escreveu `static double`!
   * E dentro do `main` (linha 7), ele chama o método e guarda o que voltou dentro de uma variável:
     ```java
     double result = square(3);
     System.out.println();
     ```
2. **Imagem 2 (Minuto ~4:14:35):**
   * O professor mostra um jeito ainda mais rápido e direto: em vez de criar a variável `result`, ele tenta colocar a chamada do método direto dentro do `println`:
     `System.out.println(square(3));`
   * *Atenção ao detalhe crucial da imagem:* Na Imagem 2, enquanto o professor digitava rápido, ele acidentalmente deixou um ponto e vírgula `;` dentro dos parênteses: `System.out.println(square(3););`. O IntelliJ imediatamente sublinhou de vermelho e abriu o balãozinho: **`Unexpected token`** (Símbolo Inesperado).

---

## 🚨 O Erro da Imagem 2: Por Que Deu "Unexpected token"?

Repare bem na Linha 7 da **Imagem 2**:
```java
System.out.println(square(3);); // ❌ ERRO! Ponto e vírgula no meio da chamada
```

### O que o IntelliJ tentou te avisar?
* Em Java, a palavra **"token"** significa qualquer pedaço de código (uma palavra, um número, uma chave, um ponto e vírgula).
* **"Unexpected token"** traduz-se literalmente como: *"Ei, apareceu um símbolo aqui que não faz o menor sentido nesse lugar!"*.
* **A regra:** O ponto e vírgula `;` serve apenas para **finalizar uma linha de comando**. Você **NUNCA** pode colocar um ponto e vírgula dentro dos parênteses `( ... )` onde você passa valores ou argumentos para uma função.
* **A correção:** O professor simplesmente apaga aquele `;` interno:
  ```java
  System.out.println(square(3)); // ✅ PERFEITO! Ponto e vírgula só no final da linha!
  ```

---

## 🧠 A Grande Virada: `void` vs. `return` (A Metáfora da Calculadora)

Até agora, todos os métodos que você viu usavam a palavra **`void`**:
```java
static void happyBirthday(...)
```
* A palavra **`void`** em inglês significa **"Vazio / Sem retorno"**.
* O método `happyBirthday` é como um funcionário que apenas executa uma tarefa física (cantar no microfone). Quando ele acaba de cantar, ele **não te entrega nada nas suas mãos**. A música já foi para o ar e pronto.

### Mas e se você precisar de uma conta matemática?
Imagine que você pede para o seu assistente:
> *"Assistente, calcula para mim quanto é 3 ao quadrado ($3 \times 3$)!"*

Você quer que o assistente apenas grite "9" na parede e vá embora? **NÃO!**  
Você quer que ele **te entregue o número 9 na sua mão** para que você possa guardar na memória, usar em outras contas ou salvar no banco de dados!

Quando queremos que o método nos entregue uma resposta na mão:
1. **Jogamos o `void` no lixo** e colocamos o **tipo de dado** que vai voltar (ex: `double` ou `int`).
2. Usamos a palavra mágica **`return`** dentro do método para "arremessar" a resposta de volta para o `main`!

---

## ⚙️ A Anatomia do Método `square`

$$\text{static}\ \underbrace{\text{double}}_{\text{1. Tipo de Retorno}}\ \underbrace{\text{square}}_{\text{2. Nome}}(\underbrace{\text{double number}}_{\text{3. Parâmetro}})\{\ \underbrace{\text{return number * number;}}_{\text{4. A Devolução}}\ \}$$

1. **`double` (Tipo de Retorno):** Avisa ao Java: *"Atenção! Eu prometo solenemente que, no final do meu trabalho, eu vou devolver um número com casas decimais (`double`)"*.
2. **`square`:** Nome do método ("quadrado" em inglês).
3. **`(double number)`:** A caixinha receptora que recebe o número a ser multiplicado (no nosso exemplo, recebe `3`).
4. **`return number * number;`:** O comando `return` pega a conta ($3 \times 3 = 9.0$) e **arremessa** esse resultado de volta para a linha do `main` que chamou!

---

## 🔀 As Duas Formas de Usar o Retorno no `main`

### Opção 1: Guardando em uma Gaveta (Imagem 1)
```java
double result = square(3);
System.out.println(result);
```
* O `square(3)` calcula e devolve `9.0`.
* O Java guarda `9.0` na variável `result`.
* Depois, você imprime o `result`.

### Opção 2: Imprimindo Direto (O Atalho da Imagem 2)
```java
System.out.println(square(3));
```
* O Java primeiro roda `square(3)`, que vira `9.0`.
* E o `System.out.println` imprime o `9.0` na hora, sem precisar criar a variável intermediária `result`! É super comum e muito elegante!

---

## 💻 Código Completo e Corrigido (`Main.java`)

Copie este código para o seu IntelliJ IDEA:

```java
public class Main {
    public static void main(String[] args){

        // method = a block of reusable code that is executed when called ()

        double result = square(3);
        System.out.println(result);
    }

    static double square(double number){
        return number * number;
    }
}
```

*(Obs: Se você quiser deixar o método `happyBirthday` guardadinho embaixo, pode deixar, mas como o professor focou agora no `square`, mantivemos o código limpo e direto ao ponto).*

---

## 🔍 Explicação Linha por Linha (Toda Linha Enumerada)

### **Linha 1:** `public class Main {`
* **O que faz:** Cria a classe principal `Main`.  
* **Console:** **Nada**.

---

### **Linha 2:** `public static void main(String[] args){`
* **O que faz:** Ponto onde o Java começa a rodar.  
* **Console:** **Nada**.

---

### **Linhas 3 a 5:** *(Linhas em branco e comentário conceitual)*

---

### **Linha 6:** *(Linha em branco)*

---

### **Linha 7:** `double result = square(3);`
* **O que faz:** **A CHAMADA COM RETORNO.**
  1. O Java vê `square(3)` e pausa o `main`.
  2. Salta para a Linha 12 e entrega o número `3` para o parâmetro `number`.
  3. O método calcula $3 \times 3 = 9.0$ e usa o `return` para mandar o `9.0` de volta.
  4. O valor `9.0` que voltou cai dentro da variável `result`!  
* **Console:** **Nada nessa linha** (o valor foi guardado na memória, mas ainda não foi impresso).

---

### **Linha 8:** `System.out.println(result);`
* **O que faz:** Pega o valor guardado na variável `result` (`9.0`) e imprime na tela do console.  
* **Console:** Imprime:
  ```text
  9.0
  ```

---

### **Linha 9:** `}`
* **O que faz:** Fecha o método `main`. Fim da execução principal.  
* **Console:** Finaliza com `Process finished with exit code 0`.

---

### **Linha 10:** *(Linha em branco)*

---

### **Linha 11:** *(Linha em branco)*

---

### **Linha 12:** `static double square(double number){`
* **O que faz:** **DECLARAÇÃO DO MÉTODO COM RETORNO.**
  * `static`: para poder ser chamado pelo `main`.
  * `double`: o tipo de dado que este método **promete devolver**.
  * `square`: o nome da função.
  * `double number`: a caixinha que recebe o número enviado (recebeu `3`).  
* **Console:** **Nada**.

---

### **Linha 13:** `return number * number;`
* **O que faz:** **O COMANDO RETURN.**
  * Multiplica `number` por ele mesmo ($3 \times 3 = 9.0$).
  * A palavra `return` pega esse resultado e **devolve** imediatamente para quem chamou, encerrando o método na hora!  
* **Console:** **Nada**.

---

### **Linha 14:** `}`
* **O que faz:** Fecha o método `square`.  
* **Console:** **Nada**.

---

### **Linha 15:** `}`
* **O que faz:** Fecha a classe `Main`.  
* **Console:** **Nada**.

---

## 📺 O que sai no Console:

```text
9.0

Process finished with exit code 0
```

*(Obs: Sai `9.0` com ponto zero porque usamos o tipo `double`, que é para números com casas decimais!)*

---

## 🎯 Resumo da Diferença para Nunca Mais Esquecer

| Se o método tem: | O que significa? | Precisa de `return`? | Exemplo |
| :---: | :--- | :---: | :--- |
| **`void`** | Só executa uma ação (imprime na tela, toca um som). **Não devolve nada**. | ❌ Não | `static void happyBirthday()` |
| **Um tipo (`double`, `int`, `String`)** | Faz um processamento e **devolve o resultado** para ser guardado ou usado. | ✅ **SIM, OBRIGATÓRIO!** | `static double square(double number)` |
