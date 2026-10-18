# 🔎 Capítulo 33: Busca Linear em Vetores em Java (Search an Array)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 33)

---

## 🧭 Visão Geral

A **Busca Linear (*Linear Search*)** é o algoritmo fundamental de varredura sequencial:
- Complexidade de tempo $\mathcal{O}(N)$ no pior caso
- Otimização com `break` imediatamente após encontrar o elemento
- Padrão de sentinela (*Sentinel Flag*) booleana (`isFound`) para evitar consoles mudos quando o elemento não existe
- A distinção crítica entre comparação por valor primitivo (`==`) e comparação por conteúdo de objetos (`.equals()`)
- Busca dinâmica interativa com `Scanner`

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_busca_linear_break_e_elemento_nao_encontrado.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/01_busca_linear_break_e_elemento_nao_encontrado.md) | Princípio da busca linear em inteiros, índice retornado e interrupção com `break`. |
| [02_flag_booleana_isfound.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/02_flag_booleana_isfound.md) | A sentinela booleana `isFound` e o tratamento de "elemento não encontrado" via negação `!isFound`. |
| [03_strings_e_metodo_equals.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/03_strings_e_metodo_equals.md) | A regra sagrada: usar `.equals()` para objetos `String` e a armadilha do `==` em Heap. |
| [04_elemento_nao_encontrado_pineapple.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/04_elemento_nao_encontrado_pineapple.md) | Validação com valor inexistente (`"pineapple"`) e garantia de mensagem clara de saída. |
| [05_scanner_input_dinamico_e_refatoracao.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/05_scanner_input_dinamico_e_refatoracao.md) | Integração da busca com prompt interativo e leitura dinâmica via `Scanner.nextLine()`. |
| [06_testes_console_coconut_vs_banana.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/06_testes_console_coconut_vs_banana.md) | Bateria de testes finais: buscas com sucesso e buscas sem correspondência. |
| [apostila_completa_search_array_capitulo33.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/apostila_completa_search_array_capitulo33.md) | Apostila definitiva e unificada contendo tabelas conceituais e checklist de entrevistas. |
| [README.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_33_search_array/README.md) | Guia curricular e mapa conceitual. |

---

## ⚡ Regra de Ouro: Primitivos vs. Objetos

| Comparação | O que usar | O que realmente compara |
| :--- | :---: | :--- |
| **Primitivos** (`int`, `double`, `boolean`, `char`) | `==` | Compara diretamente o **valor numérico** gravado na memória Stack. |
| **Objetos / Referências** (`String`, `Integer`, classes) | `.equals()` | Compara o **conteúdo caractere por caractere** dentro do objeto na memória Heap. |

---

## 💻 Código-Fonte e Testes no Projeto
- Motor de Busca Genérico e Otimizado: [`LinearSearchEngine.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/arrays/LinearSearchEngine.java)
- CLI Interativa de Busca: [`LinearSearchCLI.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/arrays/LinearSearchCLI.java)
- Suíte de Testes Automatizados: [`LinearSearchTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/LinearSearchTest.java)
