# 🍎 Capítulo 31: Arrays em Java (Vetores, Índices e a Classe Arrays)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 31)

---

## 🧭 Visão Geral

Um **Array** em Java é uma estrutura homogênea e indexada armazenada de forma contígua na memória Heap:
- Homogeneidade estrita de tipos
- Endereço de referência em memória (`[Ljava.lang.String;@...`)
- Sistema de indexação de base zero (*Zero-Based Indexing*)
- Proteção de limites e diagnóstico de `ArrayIndexOutOfBoundsException`
- Consulta dinâmica de tamanho com o atributo `.length`
- Iteração clássica (`for (int i = 0; ...)`) vs. laço aprimorado (`for-each`)
- Algoritmos utilitários de ordenação (`Arrays.sort`) e preenchimento (`Arrays.fill`)

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_introducao_e_endereco_de_memoria.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/01_introducao_e_endereco_de_memoria.md) | O que é um array, analogia do guarda-volumes e o mistério do hash de ponteiro na Stack. |
| [02_indices_erro_out_of_bounds_e_modificacao.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/02_indices_erro_out_of_bounds_e_modificacao.md) | Indexação de base 0, modificação por índice e o erro `ArrayIndexOutOfBoundsException`. |
| [03_propriedade_length_e_laco_for.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/03_propriedade_length_e_laco_for.md) | A propriedade intrínseca `.length` e a armadilha do índice rígido (*hardcoded*). |
| [04_iteracao_com_fruits_i_e_formatacao.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/04_iteracao_com_fruits_i_e_formatacao.md) | Varredura de arrays com acesso dinâmico `array[i]`. |
| [05_for_each_sort_e_fill.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/05_for_each_sort_e_fill.md) | O laço `for (Type item : array)`, ordenação `Arrays.sort()` e preenchimento em lote `Arrays.fill()`. |
| [06_destaque_fill_e_restauracao.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/06_destaque_fill_e_restauracao.md) | Efeitos colaterais de mutabilidade em memória Heap e restauração de dados. |
| [apostila_completa_arrays_capitulo31.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/apostila_completa_arrays_capitulo31.md) | Apostila integral unificada contendo todos os diagramas Stack vs Heap e tabela de FAQs. |
| [README.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_31_arrays/README.md) | Índice do módulo e orientações de estudo. |

---

## 🏛️ Mapa de Memória RAM: Stack vs. Heap

```text
       MEMÓRIA STACK (Rápida)                         MEMÓRIA HEAP (Dinâmica)
┌─────────────────────────────────┐           ┌────────────────────────────────────────┐
│ fruits ────────────[0x7b23ec81]─┼──────────►│ [0] "apple"                            │
│ (Ponteiro de 64 bits com o GPS) │           │ [1] "orange"                           │
└─────────────────────────────────┘           │ [2] "banana"                           │
                                              │ [3] "coconut"                          │
                                              └────────────────────────────────────────┘
```

---

## 💻 Código-Fonte e Testes no Projeto
- Utilitário e Demonstração: [`ArraysMasteryDemo.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/arrays/ArraysMasteryDemo.java)
- Suíte de Testes Automatizados: [`ArraysMasteryTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/ArraysMasteryTest.java)
