# 🍬 O Atalho do Resto com Sobra (`x %= y` com `y = 3`)

Nesta imagem, o Bro Code fez uma pequena alteração para mostrar o atalho `%=` funcionando quando **realmente sobra algo**!

---

## 📋 1. Código Completo (Pronto para copiar)

```java
public class Main {
    public static void main(String[] args) {

        // Augmented Assignment Operators

        int x = 10;
        int y = 3;

        //x += y;
        //x -= y;
        //x *= y;
        //x /= y;
        x %= y;

        System.out.println(x);
    }
}
```

---

## 🔍 2. O que mudou?

Olhe a **Linha 8**:

* **Antes**: `int y = 2;` $\rightarrow$ $10 \div 2$ não sobrava nada, o resultado dava **`0`**.
* **Agora**: `int y = 3;` $\rightarrow$ $10 \div 3$ **sobra 1**, e o resultado dá **`1`**!

---

## 🧠 3. Fazendo a conta como o computador faz:

1. `x` vale **10**.
2. `y` vale **3**.
3. O computador executa `x %= y;` (que é o mesmo que `x = x % y;`).
4. Ele divide 10 por 3:
   * $3 \times 3 = 9$
   * Para chegar em 10, **falta 1** (esse é o resto!).
5. O computador guarda esse **`1`** dentro de `x`.
6. Quando ele imprime `x`, aparece **`1`** na tela do console!

---

## 🍬 4. Analogia das Balas

```
Você tem 10 balas: 🍬🍬🍬🍬🍬🍬🍬🍬🍬🍬
Quer dividir para 3 amigos:

Amigo 1: 🍬🍬🍬 (3 balas)
Amigo 2: 🍬🍬🍬 (3 balas)
Amigo 3: 🍬🍬🍬 (3 balas)
Total distribuído: 9 balas

O que sobrou na sua mão?
👉 🍬 (1 bala restante!)
```

O `x %= y` pegou essa **1 bala que sobrou** e guardou dentro do `x`.

---

## 💡 Por que o autor fez isso?

Porque ver `0` na tela às vezes confunde quem está começando (parece que o programa zerou a variável).
Mudando para `3`, ele provou que o operador `%=` realmente calcula o **resto** da divisão e guarda de volta na variável!
