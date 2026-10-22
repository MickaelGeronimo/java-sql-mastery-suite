# 🧽 O Quebra-Cabeça Completo do `printf`: Os 5 Tipos e Múltiplas Variáveis!

Nestas 4 imagens, o Bro Code fechou com chave de ouro a primeira parte do Capítulo 10! Ele fez três coisas fundamentais:
1. Usou o **`%f`** para a altura e revelou um segredo dos números decimais.
2. Usou o **`%b`** para o booleano de emprego (`true`).
3. **O Ponto Alto:** Colocou **DOIS buracos diferentes na mesma frase** (`%s` e `%d`)!

---

## 📋 1. Código Completo da Imagem 4 (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // %[flags][width][.precision][specifier-character]

        String name = "Spongebob";
        char firstLetter = 'S';
        int age = 30;
        double height = 60.5;
        boolean isEmployed = true;

        System.out.printf("Hello %s\n", name);
        System.out.printf("Your name starts with a %c\n", firstLetter);
        System.out.printf("You are %d years old\n", age);
        System.out.printf("You are %f inches tall\n", height);
        System.out.printf("Employed: %b\n", isEmployed);

        System.out.printf("%s is %d years old\n", name, age);
    }
}
```

---

## 😱 2. O Mistério do `60.500000`: Por que apareceram tantos zeros?

Na **Imagem 1**, o autor escreveu:
```java
System.out.printf("You are %f inches tall\n", height);
```
A variável `height` valia apenas **`60.5`**. Mas olhe o que apareceu no console na **Imagem 2**:
```
You are 60.500000 inches tall
```

### Por que o Java encheu de zeros?
Porque você usou apenas **`%f`** (sem colocar `.1` ou `.2` no meio).
E no Java existe uma regra de fábrica:
> 📌 **Por padrão, o `%f` SEMPRE imprime com EXATAMENTE 6 CASAS DECIMAIS!**

Como você só tinha uma casa (`.5`), o Java completou as outras 5 casas com zeros até dar 6 casas decimais: `.500000`!
*(Se você quiser apenas uma casa, é só escrever `%.1f`, como aprendemos na aula passada!).*

---

## 💼 3. O Comando `%b` para Booleanos (Imagem 3)

Na linha 19, ele imprimiu se o Bob Esponja está empregado:
```java
System.out.printf("Employed: %b\n", isEmployed);
```
* **`%b`**: Vem da palavra **Boolean**.
* Ele serve exclusivamente para imprimir **`true`** ou **`false`**!
* No console apareceu: `Employed: true`.

---

## 🎯 4. A Obra-Prima da Linha 21: Dois Buracos na Mesma Frase!

Olhe com atenção para a **Imagem 4**:

```java
System.out.printf("%s is %d years old", name, age);
```

Aqui o autor mostrou o **verdadeiro poder do `printf`**: você não precisa fazer uma linha de código para cada variável! Você pode misturar texto e várias variáveis juntas:

```
 Frase Molde:    "  %s    is    %d    years old  "
                    ▲           ▲
                    │           │
     1ª Variável ───┘           └─── 2ª Variável
       (name)                          (age)
    "Spongebob"                         30
```

### Como o Java lê isso?
O Java é extremamente organizado e lê **da esquerda para a direita**:
1. Encontrou o 1º buraco (`%s`) $\rightarrow$ Pega a 1ª variável depois da vírgula: **`name`** ("Spongebob").
2. Encontrou o 2º buraco (`%d`) $\rightarrow$ Pega a 2ª variável depois da vírgula: **`age`** (30).
3. E cospe a frase pronta no console:
   ```
   Spongebob is 30 years old
   ```

---

## 🏆 5. A Tabela Definitiva dos 5 Especificadores de Formato

Agora você conhece a família completa de chaves do `printf`:

| Letra | Significado | Tipo da Variável | Exemplo no Bob Esponja | Resultado no Console |
| :---: | :--- | :---: | :--- | :--- |
| **`%s`** | **S**tring | Texto | `name = "Spongebob"` | `Spongebob` |
| **`%c`** | **C**har | Letra única | `firstLetter = 'S'` | `S` |
| **`%d`** | **D**ecimal Integer | Número Inteiro | `age = 30` | `30` |
| **`%f`** | **F**loating-point | Decimal (6 casas) | `height = 60.5` | `60.500000` |
| **`%b`** | **B**oolean | Verdadeiro/Falso | `isEmployed = true` | `true` |

---

## 🖥️ 6. O Resultado Final Completo no Console:

```
Hello Spongebob
Your name starts with a S
You are 30 years old
You are 60.500000 inches tall
Employed: true
Spongebob is 30 years old
```

Cada variável encaixou no seu buraco com 100% de perfeição! 🚀
