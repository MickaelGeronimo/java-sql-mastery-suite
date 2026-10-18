# ⬜ Capítulo 35: Matrizes e Arrays Bidimensionais em Java (2D Arrays)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 35)

---

## 🧭 Visão Geral

Um **2D Array** em Java é essencialmente um **array de arrays**:
> *"An array where each element is an array. Useful for storing a matrix of data."*

Este módulo ensina a transição da primeira para a segunda dimensão da computação:
- Declaração com colchetes duplos: `Type[][] matrix`
- Agrupamento de vetores unidimensionais (ex: `fruits`, `vegetables`, `meats` em `groceries`)
- Desmistificando os hashes de arrays aninhados no console: `[Ljava.lang.String;@...`
- Navegação bidimensional por **laços aninhados** (*nested for-each* e *nested indexed for*)
- O papel do `System.out.println()` após o laço interno para quebra de linha de linhas de matriz
- Coordenadas de grade: `matrix[row][col]`
- Modelagem de teclados telefônicos numéricos $4 \times 3$ com tipos primitivos `char[][]` (`1` a `9`, `*`, `0`, `#`)

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_introducao_matriz_lacos_aninhados.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/01_introducao_matriz_lacos_aninhados.md) | Introdução à matriz 2D, sintaxe `String[][]` e primeiro experimento com laços aninhados. |
| [02_quebra_de_linha_formatacao_grade.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/02_quebra_de_linha_formatacao_grade.md) | Formatação em grade visual retangular com quebra de linha `System.out.println()`. |
| [03_sintaxe_literal_modificando_elementos.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/03_sintaxe_literal_modificando_elementos.md) | Declaração literal compacta `{{...}, {...}}` e mutação de elementos pontuais. |
| [04_pratica_coordenadas_celery_eggs.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/04_pratica_coordenadas_celery_eggs.md) | Exercícios práticos de indexação por linha e coluna (`[row][col]`). |
| [05_teclado_telefone_char_nomenclatura_row.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/05_teclado_telefone_char_nomenclatura_row.md) | Modelagem do teclado numérico telefônico com matriz de caracteres `char[][]`. |
| [06_teclado_formatacao_grade_4x3.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/06_teclado_formatacao_grade_4x3.md) | Renderização da grade telefônica $4 \times 3$ com espaçamento perfeito. |
| [README.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_35_2d_arrays/README.md) | Guia curricular e sumário do módulo. |

---

## 🏛️ Grade Visual do Teclado Telefônico ($4 \times 3$)

```text
       Col 0   Col 1   Col 2
Row 0:   1       2       3
Row 1:   4       5       6
Row 2:   7       8       9
Row 3:   *       0       #
```

---

## 💻 Código-Fonte e Testes no Projeto
- Utilitário e Motor de Matrizes: [`Matrix2DMasteryDemo.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/arrays/Matrix2DMasteryDemo.java)
- Suíte de Testes Automatizados: [`Matrix2DTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/Matrix2DTest.java)
