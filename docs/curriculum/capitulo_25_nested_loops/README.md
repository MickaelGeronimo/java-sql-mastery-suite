# ➿ Capítulo 25: O Guia Definitivo de Laços Aninhados em Java (Nested Loops)

> Módulo oficial de estudos de controle de fluxo bidimensional em Java 17 LTS.  
> Baseado nas explicações detalhadas, rastreamento de memória e exercícios do curso completo de Java.

---

## 📑 Estrutura do Capítulo

O capítulo 25 está dividido em **5 partes pedagógicas progressivas** e uma **apostila unificada autossuficiente**:

| Arquivo | Tópico Principal | Conceitos Chave |
| :--- | :--- | :--- |
| **[`01_introducao_e_impressao_horizontal.md`](01_introducao_e_impressao_horizontal.md)** | Introdução & Impressão Horizontal | Definição de laço aninhado, `println` vs `print`, armadilha do copiar/colar |
| **[`02_erro_variavel_duplicada_e_escopo.md`](02_erro_variavel_duplicada_e_escopo.md)** | Erro de Variável Duplicada | `variable i is already defined`, escopo de blocos `{}` em Java |
| **[`03_solucao_com_j_e_quebra_de_linha.md`](03_solucao_com_j_e_quebra_de_linha.md)** | Correção com `j` & Tecla Enter | Os 4 pontos de renomeação de índice, matriz 3x9, efeito do `println()` vazio |
| **[`04_leitura_com_scanner_e_charat.md`](04_leitura_com_scanner_e_charat.md)** | Entrada com Scanner & `charAt(0)` | Por que não existe `scanner.nextChar()`, solução `scanner.next().charAt(0)` |
| **[`05_gerador_de_matriz_completo.md`](05_gerador_de_matriz_completo.md)** | Gerador de Matriz Dinâmico | Construção 2D parametrizada por `rows`, `columns` e `symbol` |
| **[`apostila_completa_nested_loops_capitulo25.md`](apostila_completa_nested_loops_capitulo25.md)** | Apostila Completa Unificada | Documento consolidado pronto para cópia e estudo contínuo |

---

## 💻 Implementações em Código Java

As implementações oficiais executáveis deste capítulo encontram-se em:

1. **[`NestedLoopsGridDemo.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/loops/NestedLoopsGridDemo.java)**:
   Demonstração comparativa entre saída vertical (`println`), saída horizontal (`print`), resolução do erro de escopo e matriz $3 \times 9$.

2. **[`NestedLoopsMatrixGenerator.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/loops/NestedLoopsMatrixGenerator.java)**:
   Gerador de matriz bidimensional desacoplado, com métodos utilitários de criação de grade `char[][]` e formatação textual, além do modo interativo via console.

3. **[`NestedLoopsTest.java`](../../../src/test/java/com/mickaelgeronimo/mastery/core/NestedLoopsTest.java)**:
   Suíte automatizada com JUnit 5 e AssertJ cobrindo dimensões, preenchimento de caracteres e limites de fronteira.
