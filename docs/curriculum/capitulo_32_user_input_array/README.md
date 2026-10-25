# ➡️ Capítulo 32: Inserir Entrada do Usuário em Arrays (User Input into an Array)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 32)

---

## 🧭 Visão Geral

Este módulo aborda o desafio central da alocação de memória para dados gerados dinamicamente pelo usuário:
- A ilusão do array vazio `{}` e por que ele possui `.length = 0`
- O erro fatal de limites: `ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0`
- Alocação formal com operador `new Type[capacity]`
- Interação com `Scanner` e o laço clássico `for (int i = 0; i < foods.length; i++)`
- Adaptação dinâmica a mudanças de capacidade
- O clássico **bug do buffer** do `Scanner` (`\n` pendente após `nextInt()`) e sua higienização via `scanner.nextLine()`

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_array_vazio_e_foods_length.md](01_array_vazio_e_foods_length.md) | A ilusão da "sacola elástica" e o tamanho `0` do array literal vazio. |
| [02_erro_arrayindexoutofbounds_index_0.md](02_erro_arrayindexoutofbounds_index_0.md) | Metáfora do motoboy no terreno baldio 🍕🛵 e o crash fatal em runtime. |
| [03_sintaxe_new_capacidade_fixa.md](03_sintaxe_new_capacidade_fixa.md) | Pré-alocação de capacidade fixa na memória Heap com a palavra `new String[3]`. |
| [04_preenchimento_manual_e_for_each.md](04_preenchimento_manual_e_for_each.md) | Atribuição manual direta e leitura elegante com laço `for-each`. |
| [05_scanner_laco_for_e_preenchimento.md](05_scanner_laco_for_e_preenchimento.md) | Captura de entrada via teclado e atribuição em `foods[i]`. |
| [06_mudando_tamanho_para_4_e_foods_length.md](06_mudando_tamanho_para_4_e_foods_length.md) | O poder do `.length` em manter os laços auto-adaptáveis sem quebras. |
| [07_tamanho_dinamico_e_bug_do_buffer.md](07_tamanho_dinamico_e_bug_do_buffer.md) | Entrada do tamanho pelo usuário (`size`) e a quebra de linha `\n` não consumida na esteira. |
| [08_destaque_size_e_limpeza_buffer.md](08_destaque_size_e_limpeza_buffer.md) | A solução definitiva: limpeza do buffer com `scanner.nextLine()` antes de iniciar o loop. |
| [apostila_completa_user_input_array_capitulo32.md](apostila_completa_user_input_array_capitulo32.md) | Apostila definitiva e unificada contendo checklist de aprendizado e código compilável. |
| [README.md](README.md) | Guia curricular e sumário do módulo. |

---

## ⚠️ A Armadilha do Buffer do Scanner

```text
Entrada digitada: "3\n"
                  ──┬─
                    │
1. scanner.nextInt() ──► Consome o '3' e devolve como int
2. O que sobra na esteira: '\n' (Quebra de Linha pendente!)
3. O próximo scanner.nextLine() ──► Vê o '\n', acha que o usuário apertou Enter vazio e PULA A ENTRADA!

Cura:
int size = scanner.nextInt();
scanner.nextLine(); // 👈 LIXEIRA DO TECLADO: Limpa o buffer!
```

---

## 💻 Código-Fonte e Testes no Projeto
- Motor de Alocação e Preenchimento: [`UserInputArrayCollector.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/arrays/UserInputArrayCollector.java)
- CLI Interativa de Coleta: [`UserInputArrayCLI.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/arrays/UserInputArrayCLI.java)
- Suíte de Testes Automatizados: [`UserInputArrayTest.java`](../../../src/test/java/com/mickaelgeronimo/mastery/core/UserInputArrayTest.java)
