# Curso de Java — Capítulo 48: Abstração (`abstraction 🌫️`)
## Parte 3: O Contrato Obrigatório em Ação — O Erro nas Subclasses e a Implementação de `area()`

> **Contexto de Estudo:** Este material é uma transcrição e dissecação pedagógica minuciosa baseada no curso *"Java Full Course for free"* do canal **Bro Code** (timestamp `07:55:50` até `07:56:50` / 12:00:00).  
> **Objetivo:** Explicar para um iniciante absoluto ("do zero", passo a passo) a consequência imediata e devastadora de declarar um método abstrato na classe mãe: o surgimento do erro vermelho em todas as subclasses ([`Circle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), [`Rectangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java) e [`Triangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java)), o significado da mensagem formal da IDE (*"must either be declared abstract or implement abstract method"*), e a solução profissional usando `@Override double area(){ return 0; }`.  
> **Formato para Google Docs:** Todo o contexto visual da interface do IntelliJ IDEA (sublinhados vermelhos de erro em classes inteiras, balões modais de assistência rápida, ícones `I↑` no gutter e seleções com o mouse) foi minuciosamente convertido em texto, diagramas e tabelas para estudo e cópia direta no Google Docs sem depender das imagens originais.

---

## 1. Visão Geral da Cena: A Lei Foi Promulgada e as Filhas Devem Obedecer!

Na parte anterior, vimos o professor Bro Code criar na classe mãe [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) o método:
```java
abstract double area(); // ABSTRACT
```

Nesta sequência de 5 capturas de tela, presenciamos o verdadeiro poder da Programação Orientada a Objetos:
1. **O Alarme Geral (Imagem 1):** Ao abrir a classe filha [`Circle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), ela está inteiramente sublinhada em **vermelho vivo**! O Java se recusa a compilar e exige: *"Ou você implementa o método `area()`, ou você não pode existir como classe concreta!"*.
2. **A Obediência Contratual (Imagens 2 e 3):** O professor escreve `@Override double area(){ return 0; }`. O erro vermelho desaparece na hora e na margem acende o ícone verde/azul **`I↑`** (*Implements method in Shape*).
3. **A Propagação em Massa (Imagens 4 e 5):** O professor copia essa estrutura e cola em [`Rectangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java) (e [`Triangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java)), padronizando todas as formas geométricas do projeto!

---

## 2. Radiografia Visual Minuciosa das 5 Imagens

Para quem está estudando pelo Google Docs e não tem as imagens na tela:

### Imagem 1 (`media_1791359135284.png`): A Explosão do Erro em `Circle.java`
* **Aba Ativa:** `Circle.java` (a aba está com uma linha ondulada vermelha embaixo do nome).
* **Editor de Código:** Na linha 1:
  ```java
  public class Circle extends Shape{
  ```
  A frase inteira está com um **sublinhado vermelho serrilhado**. Na margem esquerda (gutter), há uma lâmpada vermelha de erro `!`.
* **O Balão Pop-up de Inspeção da IDE:** O IntelliJ abre uma janela escura com a notificação oficial da JVM:
  > **`Class 'Circle' must either be declared abstract or implement abstract method 'area()' in 'Shape'`**  
  > *(A classe 'Circle' deve ser declarada como abstrata ou implementar o método abstrato 'area()' de 'Shape')*  
  > Opção de correção: `Implement methods Alt+Shift+Enter`.

### Imagem 2 (`media_1791359152651.png` — 07:56:12): O Início da Correção
* O professor aperta Enter para abrir espaço e digita na linha 3:
  ```java
  @Override
  ```
* O cursor pisca na linha 4 vazia, aguardando a assinatura do método.

### Imagem 3 (`media_1791359168938.png` — 07:56:21): A Paz Restaurada!
* O professor completa a implementação temporária:
  ```java
  1 public class Circle extends Shape{
  2 
  3     @Override
  4     double area(){
  5         return 0;
  6     }
  7 }
  ```
* **O Alívio Visual:**
  * Todo o sublinhado vermelho da linha 1 sumiu!
  * Na margem esquerda da linha 4, surge o ícone verde/azul com um círculo e a letra **`I↑`** (*Implements method*).
  * O compilador está 100% satisfeito.

### Imagem 4 (`media_1791359184523.png` — 07:56:38): Selecionando para Replicar
* O professor seleciona com o mouse as linhas 3 a 6 (`@Override double area(){ return 0; }`), cobrindo-as com uma barra azul de seleção.
* Ele pressiona `Ctrl + C` no teclado para copiar esse bloco de código.

### Imagem 5 (`media_1791359190919.png`): Padronizando `Rectangle.java`
* **Aba Ativa:** Muda para [`Rectangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java).
* **Editor de Código:**
  ```java
  1 public class Rectangle extends Shape{
  2 
  3     @Override
  4     double area(){
  5         return 0;
  6     }
  7 }
  ```
* Ele adiciona `extends Shape` na linha 1 e cola o método `area()`.
* O ícone **`I↑`** surge também na linha 4 de `Rectangle.java`, confirmando que o retângulo agora também é uma figura geométrica oficial do sistema!

---

## 3. Dissecando a Mensagem de Erro da Imagem 1

Vamos ler com calma a mensagem que o IntelliJ exibiu:

> **`Class 'Circle' must either be declared abstract or implement abstract method 'area()' in 'Shape'`**

O compilador Java está te dando exatamente **duas opções**:

```
                                A ENCRUZILHADA DO JAVA
                                           │
         ┌─────────────────────────────────┴─────────────────────────────────┐
         ▼                                                                   ▼
   OPÇÃO 1: IMPLEMENTAR                                            OPÇÃO 2: VIRAR ABSTRATA
"Eu vou criar o método area() com { }                           "Eu não sei calcular minha área,
e colocar a minha fórmula matemática!"                          então eu também sou abstrata!"
         │                                                                   │
         ▼                                                                   ▼
public class Circle extends Shape {                             public abstract class Circle extends Shape {
    @Override                                                       // Passa a bronca para os netos!
    double area() { return 0; }                                 }
}
         │                                                                   │
   (Classe Concreta - PODE usar 'new')                           (Classe Abstrata - NÃO PODE usar 'new')
```

Como nós queremos criar círculos de verdade no nosso programa (`new Circle()`), nós escolhemos a **Opção 1**: implementar o método!

---

## 4. O Que Significa o Ícone `I↑` no Gutter?

Repare no ícone que apareceu ao lado da linha 4:
* É um círculo verde/azul com a letra **`I`** e uma setinha apontando para cima **`↑`**.
* No IntelliJ IDEA:
  * A letra **`O↑`** significa **Overriding** (sobrescrevendo um método que já tinha código na classe pai).
  * A letra **`I↑`** significa **Implementing** (implementando um método abstrato que estava vazio na classe pai ou numa interface).
* Se você clicar com o mouse em cima desse ícone `I↑`, a IDE te transporta instantaneamente para a linha 3 de [`Shape.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java)!

---

## 5. Por Que o Professor Colocou `return 0;`? (O Conceito de *Stub*)

Por que o professor escreveu `return 0;` em vez de já colocar a fórmula da área do círculo ($\pi \cdot r^2$)?

Esta é uma técnica profissional fundamental chamada **Stub (ou Código Temporário / Tapa-Buraco)**:
1. O método `area()` promete devolver um número `double`: `double area()`.
2. Para que o compilador pare de reclamar e nos deixe trabalhar, nós precisamos devolver *qualquer* número `double`.
3. Escrever `return 0;` faz o código compilar na hora com zero erros!
4. Isso permite ao arquiteto de software montar a estrutura completa de todas as classes primeiro ([`Circle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java), [`Rectangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java), [`Triangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java)) e garantir que todos os contratos estão assinados, para só depois preencher cada classe com os seus atributos próprios (raio, base, altura) e suas fórmulas definitivas!

---

## 6. Análise Código por Código das Classes Atualizadas

### A. A Classe [`Circle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java)
```java
1:  public class Circle extends Shape{
2:  
3:      @Override
4:      double area(){
5:          return 0;
6:      }
7:  }
```
* **Linha 1:** Declara `Circle` como filha de `Shape`.
* **Linha 3 (`@Override`):** Anotação que comprova que estamos cumprindo o contrato de `Shape`.
* **Linha 4 (`double area(){`):** Implementação concreta do método que antes era abstrato.
* **Linha 5 (`return 0;`):** Retorno temporário que satisfaz o compilador.
* **Linhas 6 e 7:** Fechamento do método e da classe.

---

### B. A Classe [`Rectangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java)
```java
1:  public class Rectangle extends Shape{
2:  
3:      @Override
4:      double area(){
5:          return 0;
6:      }
7:  }
```
* **Linha 1:** Declara `Rectangle` como filha de `Shape`.
* **Linhas 3 a 6:** Cumpre rigorosamente o mesmo contrato exigido pela superclasse abstrata.

---

### C. A Classe [`Triangle.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java)
```java
1:  public class Triangle extends Shape{
2:  
3:      @Override
4:      double area(){
5:          return 0;
6:      }
7:  }
```
* A terceira forma geométrica que segue o mesmo padrão sagrado.

---

## 7. O Que Sai no Console Nesta Etapa?

Se você rodar o arquivo [`Main.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/concurrency/Main.java) agora:

```text
Process finished with exit code 0
```

* **Saída:** Nenhuma linha de texto é impressa ainda, pois os métodos `area()` ainda não foram chamados dentro de um `System.out.println`.
* O código terminou com código `0` de sucesso absoluto, confirmando que todas as 3 classes filhas cumpriram o contrato imposto pela mãe abstrata `Shape`!

---

## 8. Tabela da Relação Contratual da Abstração

| Classe | É Abstrata? | Pode usar `new`? | Método `area()` | O que o método faz nesta etapa? |
| :--- | :---: | :---: | :--- | :--- |
| [`Shape`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Shape.java) | ✅ Sim (`abstract`) | ❌ Proibido | `abstract double area();` | Não faz nada! Apenas dita a ordem (sem chaves). |
| [`Circle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Circle.java) | ❌ Não (Concreta) | ✅ Permitido | `@Override double area()` | Retorna `0` (Stub temporário). |
| [`Rectangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Rectangle.java) | ❌ Não (Concreta) | ✅ Permitido | `@Override double area()` | Retorna `0` (Stub temporário). |
| [`Triangle`](../../../src/main/java/com/mickaelgeronimo/mastery/core/geometry/Triangle.java) | ❌ Não (Concreta) | ✅ Permitido | `@Override double area()` | Retorna `0` (Stub temporário). |

---

## 9. Resumo para Fixação no Google Docs

1. **Método abstrato no pai gera obrigação no filho:** Se o pai tem um método com `abstract`, o filho é obrigado a implementá-lo com `@Override`.
2. **O erro vermelho é implacável:** O compilador não deixa passar nenhuma classe filha que tente escapar do contrato.
3. **O ícone `I↑` representa a vitória:** Mostra que a classe implementou a regra abstrata da superclasse.
4. **O uso do Stub (`return 0;`):** Uma excelente prática para estruturar as classes antes de partir para os cálculos matemáticos específicos.
5. **Próximo passo:** Vamos dar aos nossos objetos os seus atributos reais (raio para o círculo, base e altura para triângulo/retângulo) e substituir o `return 0;` pelas fórmulas de verdade!
