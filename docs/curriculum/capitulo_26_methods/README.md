# 📞 Capítulo 26: Métodos em Java (Methods)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 26)

---

## 🧭 Visão Geral

Métodos representam a unidade elementar de reutilização e encapsulamento em Java:
> **Método (*Method*):** Bloco de código reutilizável que só é executado quando explicitamente invocado pelo seu identificador seguido de parênteses `()`.

Este módulo aborda desde a primeira declaração até parâmetros múltiplos, retornos tipados (`return`), métodos matemáticos (`square`, `cube`), métodos de validação booleana (`ageCheck`) e inspeção de pilha de chamadas (*Stack Frames*).

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_introducao_e_metafora_telefone.md](01_introducao_e_metafora_telefone.md) | O que é um método e a analogia da chamada telefônica 📞. |
| [02_reutilizacao_e_dry.md](02_reutilizacao_e_dry.md) | O Princípio DRY (*Don't Repeat Yourself*) e eliminação de duplicação. |
| [03_erro_escopo_cannot_find_symbol.md](03_erro_escopo_cannot_find_symbol.md) | O erro clássico `cannot find symbol` ao tentar ler variáveis de outro método. |
| [04_meme_da_persiana_e_escopo.md](04_meme_da_persiana_e_escopo.md) | Metáfora da persiana fechada: isolamento léxico entre métodos. |
| [05_erro_argumento_sem_parametro.md](05_erro_argumento_sem_parametro.md) | Incompatibilidade de assinatura quando argumentos são passados sem parâmetros correspondentes. |
| [06_passando_primeiro_parametro.md](06_passando_primeiro_parametro.md) | Declaração formal de parâmetros tipados (`String name`). |
| [07_multiplos_argumentos_e_erro_tamanho.md](07_multiplos_argumentos_e_erro_tamanho.md) | Contrato de aridade e erro ao passar argumentos insuficientes. |
| [08_dois_parametros_resolvido.md](08_dois_parametros_resolvido.md) | Pareamento ordenado de argumentos (`String name, int age`). |
| [09_nomes_diferentes_parametros.md](09_nomes_diferentes_parametros.md) | Diferença entre o identificador do argumento na chamada e do parâmetro na definição. |
| [10_padronizacao_e_teste_patrick.md](10_padronizacao_e_teste_patrick.md) | Testes práticos com múltiplos dados e padronização de nomenclatura. |
| [11_retorno_de_valores_return.md](11_retorno_de_valores_return.md) | Substituição de `void` por tipos concretos e a instrução `return`. |
| [12_chamada_aninhada_e_square.md](12_chamada_aninhada_e_square.md) | Cálculo de potências (`square`) e chamadas aninhadas em expressões. |
| [13_metodo_cube_e_uma_linha.md](13_metodo_cube_e_uma_linha.md) | Métodos concisos de expressão única (`return number * number * number;`). |
| [14_execucao_cube_resultado_27.md](14_execucao_cube_resultado_27.md) | Rastreamento de fluxo e execução matemática. |
| [15_retorno_string_getfullname.md](15_retorno_string_getfullname.md) | Manipulação e concatenação de strings formatadas via método (`getFullName`). |
| [16_retorno_boolean_agecheck.md](16_retorno_boolean_agecheck.md) | Predicados booleanos e a técnica idiomática `return age >= 18;`. |
| [17_metodo_booleano_no_if.md](17_metodo_booleano_no_if.md) | Integração direta de métodos predicados em estruturas condicionais `if (ageCheck(age))`. |
| [apostila_completa_methods_capitulo26.md](apostila_completa_methods_capitulo26.md) | Apostila unificada contendo todos os 17 passos, diagramas e código completo compilável. |

---

## 💻 Código-Fonte e Testes no Projeto
- Classe Didática: [`MethodsMasteryDemo.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/methods/MethodsMasteryDemo.java)
- Suíte de Testes Unitários: [`MethodsMasteryTest.java`](../../../src/test/java/com/mickaelgeronimo/mastery/core/MethodsMasteryTest.java)
