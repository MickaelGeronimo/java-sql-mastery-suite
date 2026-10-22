# 🪙 Jogando Cara ou Coroa: O Sorteio com `random.nextBoolean()`!

Nesta imagem, o Bro Code ensina o sorteio mais clássico da história da humanidade: **jogar uma moeda para o alto (Cara ou Coroa)!**

E para fazer isso, ele usou a ferramenta **`random.nextBoolean()`**.

---

## 📋 1. Código Completo e Corrigido (Pronto para copiar)

```java
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        boolean isHeads;

        isHeads = random.nextBoolean();

        System.out.println(isHeads);
    }
}
```

---

## 🪙 2. O que significa `isHeads`? (Dicionário de Inglês)

Em inglês, o jogo de **Cara ou Coroa** se chama **"Heads or Tails"**:
* **Heads** = Cara 👤
* **Tails** = Coroa 🦅

Quando ele cria a variável `boolean isHeads;`, em português significa:
> *"É Cara? (Sim ou Não?)"*

---

## 🎲 3. Como Funciona o `random.nextBoolean()`?

Pense nisso como **jogar uma moeda de 1 real para cima**:

```
                 [ JOGOU A MOEDA PRO ALTO ]
                             │
             ┌───────────────┴───────────────┐
             ▼                               ▼
       Caiu CARA (50%)               Caiu COROA (50%)
          👉 true                         👉 false
```

* O robô sorteador só tem **duas respostas possíveis**:
  * Ou ele devolve **`true`** (Verdadeiro / Caiu Cara).
  * Ou ele devolve **`false`** (Falso / Caiu Coroa).
* Cada lado tem exatamente **50% de chance** de sair!

---

## 🚨 4. Por que a Linha 12 Ficou Vermelha na Imagem?

Olhe a imagem com atenção:
```java
boolean isHeads;                      // Linha 8: Criou a caixinha 'isHeads'
isHeads = random.nextBoolean();       // Linha 10: Guardou o sorteio em 'isHeads'
System.out.println(number);           // Linha 12: ❌ ERRO VERMELHO!
```

### Por que deu erro?
Porque o autor estava no meio da digitação!
* Ele apagou a variável antiga `number` lá na linha 8.
* Mas ainda não tinha atualizado a linha 12 para `isHeads`!
* O Java avisou: *"Ei, a variável `number` não existe mais!"*.
* Para corrigir, é só escrever: `System.out.println(isHeads);`.

---

## 💡 5. Como usar isso com `if-else` para ficar incrível:

Em vez de só mostrar `true` ou `false` na tela, você pode juntar com o que aprendeu no Capítulo 7:

```java
if (isHeads) {
    System.out.println("HEADS! (Deu Cara!) 👤");
} else {
    System.out.println("TAILS! (Deu Coroa!) 🦅");
}
```

---

## 🏆 6. O Grande Resumo das 3 Ferramentas do `Random`:

Com esta aula, você completou o trio de ouro dos sorteios no Java:

| Ferramenta | O que sorteia? | Exemplo de Uso na Vida Real |
| :--- | :--- | :--- |
| **`random.nextInt(1, 7)`** | Números inteiros | Dados de jogos, rifas, bilhetes de loteria |
| **`random.nextDouble()`** | Decimais entre 0.0 e 1.0 | Porcentagens, chance de acerto em jogos (20%) |
| **`random.nextBoolean()`** | Apenas `true` ou `false` | Cara ou coroa, cara ou coroa no futebol, sim ou não |
