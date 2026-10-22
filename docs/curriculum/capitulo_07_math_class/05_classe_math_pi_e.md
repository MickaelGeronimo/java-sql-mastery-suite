# 🧮 Capítulo 9: A Classe `Math` (A Calculadora Científica do Java!)

Bem-vindo ao **Capítulo 9**! O Bro Code acabou de abrir uma nova fase: a classe **`Math`**.

Pense no `Math` como uma **calculadora científica ultra-potente da Casio** que já vem embutida de fábrica dentro do Java!

---

## 📋 1. Código Completo da Aula (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        System.out.println(Math.PI);
        System.out.println(Math.E);
    }
}
```

---

## 🤯 2. O Grande Choque: "Cadê o `import` e cadê o `new`?!"

Olhe com atenção para o código da Imagem 2. Percebeu algo muito estranho em comparação com as aulas passadas?

```
Na aula do Scanner:     import java.util.Scanner;   Scanner scanner = new Scanner(...);
Na aula do Random:      import java.util.Random;    Random random = new Random();
Agora na aula do Math:  NÃO TEM IMPORT!             NÃO TEM NEW!
```

### Por que o `Math` não precisa de `import` nem de `new`?
1. **Já vem no bolso do Java:** O `Math` faz parte do pacote central do Java (`java.lang`). O Java já carrega ele na memória automaticamente em todo programa!
2. **Não precisa montar máquina (`new`):** O `Math` é uma ferramenta estática. É como uma tabela na parede: você não precisa "construir uma tabela nova", basta olhar para ela e ler o valor!

---

## 🥧 3. O que é `Math.PI`? (Linha 5)

`Math.PI` guarda o famoso número matemático **PI ($\pi$)**:
$$\mathbf{3.141592653589793}$$

### Para que serve na vida real?
É a proporção mágica dos círculos! Você usa o PI para:
* Calcular o tamanho de uma **pizza redonda** 🍕.
* Calcular a área da **roda de um carro** 🚗.
* Calcular o volume de um **planeta** 🪐.

---

## 📈 4. O que é `Math.E`? (Linha 6)

`Math.E` guarda a constante matemática de **Euler ($e$)**:
$$\mathbf{2.718281828459045}$$

### Para que serve na vida real?
É a constante do **crescimento exponencial**:
* Usada em bancos para calcular **juros compostos** no dinheiro 💰.
* Usada na biologia para calcular a **reprodução de bactérias** 🦠.
* Usada na física para calcular decaimento radioativo.

---

## 🔤 5. Por que `PI` e `E` estão em LETRAS MAIÚSCULAS?

Você deve ter reparado que não é `Math.pi`, e sim **`Math.PI`**.

No mundo da programação Java, existe uma regra sagrada de etiqueta:
> 📌 **Valores CONSTANTES (que nunca mudam na história do universo) são SEMPRE escritos em LETRAS MAIÚSCULAS!**

Como o valor de PI nunca vai deixar de ser `3.14...`, ele foi batizado em maiúsculo para avisar todo mundo: *"Ei, esse número é sagrado, ninguém pode alterar!"*.

---

## 🔍 6. Tabela Linha por Linha

| Linha | Código | O que significa? |
| :---: | :--- | :--- |
| **5** | `System.out.println(Math.PI);` | Acessa a calculadora `Math`, pega o valor de PI e imprime: `3.141592653589793`. |
| **6** | `System.out.println(Math.E);` | Acessa a calculadora `Math`, pega o número de Euler e imprime: `2.718281828459045`. |

Nos próximos minutos, o Bro Code vai mostrar as funções de calcular potências, raízes quadradas, arredondamentos e muito mais! 🚀
