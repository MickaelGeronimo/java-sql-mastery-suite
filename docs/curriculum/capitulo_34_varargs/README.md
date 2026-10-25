# 🧩 Capítulo 34: Varargs em Java (Variable Arguments / Reticências `...`)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 34)

---

## 🧭 Visão Geral

O **Varargs (*Variable Arguments*)** introduz uma poderosa flexibilidade em métodos Java:
- Fim da "escadinha do terror" da sobrecarga infinita (`add(a, b)`, `add(a, b, c)`, ...)
- Operador de reticências (`Type... name`)
- Mecanismo interno da JVM: empacotamento automático de argumentos soltos em um array na memória Heap (`[I@...`)
- Cálculo de somatórios e médias com laço `for-each`
- O bug do `NaN` (*Not a Number*) em divisões `0.0 / 0` e a implementação de **Cláusula de Guarda (*Guard Clause*)**
- As 3 regras canônicas de uso do varargs na linguagem Java

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_pesadelo_metodos_sobrecarregados.md](01_pesadelo_metodos_sobrecarregados.md) | O problema da proliferação desnecessária de métodos sobrecarregados. |
| [02_experimento_array_e_hash.md](02_experimento_array_e_hash.md) | Desmistificando o `[I@7b23ec81`: prova de que a JVM aloca um array por baixo dos panos. |
| [03_somador_for_each_e_retorno.md](03_somador_for_each_e_retorno.md) | Implementação de somatório acumulado limpo com `for-each` e retorno `int`. |
| [04_flexibilidade_6_argumentos_soma_21.md](04_flexibilidade_6_argumentos_soma_21.md) | Prova prática de aridade indeterminada (2, 4, 6 ou N argumentos na mesma chamada). |
| [05_transicao_para_media.md](05_transicao_para_media.md) | Transição arquitetural para cálculo estatístico de média (`average`). |
| [06_metodo_average_e_bug_nan.md](06_metodo_average_e_bug_nan.md) | A armadilha do `average()` chamado sem nenhum argumento: `0.0 / 0 = NaN`. |
| [07_clausula_guarda_cura_nan.md](07_clausula_guarda_cura_nan.md) | A cura corporativa: Cláusula de guarda `if (numbers.length == 0) return 0.0;`. |
| [08_revisao_final_e_conclusao.md](08_revisao_final_e_conclusao.md) | Revisão completa e introdução aos arrays multidimensionais (2D Arrays). |
| [apostila_completa_varargs_capitulo34.md](apostila_completa_varargs_capitulo34.md) | Apostila definitiva e unificada de todo o capítulo 34. |
| [README.md](README.md) | Guia curricular e mapa conceitual. |

---

## 🏛️ As 3 Regras de Ouro do Varargs

1. **Apenas Um Varargs por Método:** Você **NÃO** pode declarar `void test(int... a, String... b)`.
2. **Sempre na Última Posição:** Se houver outros parâmetros, o varargs deve ser o **último**: `void log(String prefix, int... codes)`.
3. **Pode Receber Zero Argumentos:** Chamar `add()` sem passar nada é 100% válido (aloca um array vazio `length = 0`), exigindo cláusula de guarda defensiva.

---

## 💻 Código-Fonte e Testes no Projeto
- Utilitário Matemático e Estatístico: [`VarargsCalculator.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/arrays/VarargsCalculator.java)
- Suíte de Testes Automatizados: [`VarargsTest.java`](../../../src/test/java/com/mickaelgeronimo/mastery/core/VarargsTest.java)
