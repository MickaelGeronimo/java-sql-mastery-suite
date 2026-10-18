# 🎰 Capítulo 38: Slot Machine em Java (Caça-Níqueis)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 38)

---

## 🧭 Visão Geral

O projeto **Java Slot Machine (Caça-Níqueis)** sintetiza 9 conceitos fundamentais do desenvolvimento de software em Java na criação de uma simulação de cassino completa e interativa:
- **Planejamento Arquitetural (*Blueprint*)**: declaração de variáveis de estado (`balance = 100`, `bet`, `payout`, `row`, `playAgain`), banner de boas-vindas e molduras decorativas.
- **Validação de Entradas e Controle de Fluxo**:
  - Verificação de aposta excedente (`bet > balance` $\rightarrow$ `"INSUFFICIENT FUNDS"` e `continue;`).
  - Verificação de aposta nula/negativa (`bet <= 0` $\rightarrow$ `"Bet must be greater than 0"` e `continue;`).
  - Débito imediato da aposta antes do giro (`balance -= bet;`).
- **Sorteio Pseudoaleatório Modular (`spinRow`)**:
  - Array de símbolos: `{"🍒", "🍉", "🍋", "🔔", "⭐"}`.
  - Sorteio de 3 posições com `random.nextInt(symbols.length)`.
- **Renderização Visual Formatada (`printRow`)**:
  - Moldura de 25 asteriscos (`*************************`).
  - Junção limpa dos símbolos via `String.join(" | ", row)`.
- **Cérebro Financeiro com Enhanced Switch Expression (`getPayout`)**:
  - **Trinca (3 símbolos iguais)**: avaliada pela propriedade transitiva `row[0].equals(row[1]) && row[1].equals(row[2])`.
  - **Par (2 símbolos adjacentes iguais)**: avaliada por `row[0].equals(row[1]) || row[1].equals(row[2])`, aproveitando o elemento central `row[1]` em um único switch compacto.
- **Gerenciamento do Buffer do Scanner**:
  - Resolução do clássico *"bug do Scanner"* consumindo o `\n` residual com `scanner.nextLine()` após `scanner.nextInt()`.
- **Ciclo de Replay e Encerramento**:
  - Pergunta `"Do you want to play again? (Y/N): "` com normalização via `.toUpperCase()`.
  - Interrupção explícita com `break;` e mensagem de `GAME OVER` exibindo o saldo final e fechamento do leitor.

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_blueprint_variaveis_emojis_e_banner.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/01_blueprint_variaveis_emojis_e_banner.md) | O esqueleto de variáveis, banner de boas-vindas com moldura de 25 asteriscos e os 5 emojis. |
| [02_loop_balance_apostas_e_continue.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/02_loop_balance_apostas_e_continue.md) | O laço `while(balance > 0)`, entrada de aposta e blindagem com `continue` para fundos insuficientes ou valores `<= 0`. |
| [03_testes_console_e_metodo_spinrow.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/03_testes_console_e_metodo_spinrow.md) | Testes no terminal da lógica de validação e declaração inicial da assinatura do método `spinRow()`. |
| [04_spinning_indices_emojis.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/04_spinning_indices_emojis.md) | O vetor `symbols`, mapeamento por índices `[0]` a `[4]` e a mensagem de suspense `"Spinning..."`. |
| [05_sorteio_aleatorio_e_symbols_length.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/05_sorteio_aleatorio_e_symbols_length.md) | Uso da classe `Random`, geração com `.nextInt(symbols.length)` e prevenção de `ArrayIndexOutOfBoundsException`. |
| [06_loop_for_spinrow_e_retorno.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/06_loop_for_spinrow_e_retorno.md) | Preenchimento automatizado das 3 posições do vetor `row` com loop `for` e retorno do array para a `main`. |
| [07_code_folding_e_metodo_printrow.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/07_code_folding_e_metodo_printrow.md) | Criação de `printRow(row)`, formatação com `String.join(" | ", row)` e recolhimento de código (*code folding*) na IDE. |
| [08_payout_e_switch_expression.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/08_payout_e_switch_expression.md) | O cérebro de premiação: método `getPayout(row, bet)`, teste de trinca transitiva e Enhanced Switch moderno. |
| [09_conexao_main_vitoria_e_par_de_simbolos.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/09_conexao_main_vitoria_e_par_de_simbolos.md) | Ligação de `getPayout` na `main`, crédito com `+=`, mensagens de vitória/derrota e criação da regra do par. |
| [10_teste_ao_vivo_par_melancias.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/10_teste_ao_vivo_par_melancias.md) | Teste ao vivo da vitória com par de melancias (`🍉 | 🍉 | 🍋`), auditoria de variáveis e validação prática. |
| [11_play_again_buffer_scanner_e_game_over.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/11_play_again_buffer_scanner_e_game_over.md) | O loop `playAgain`, diagnóstico ao vivo do bug do buffer do Scanner, correção com `scanner.nextLine()` e `GAME OVER`. |
| [12_testes_finais_encerramento_e_conclusao.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/12_testes_finais_encerramento_e_conclusao.md) | Testes finais com lucro de $117, análise do par centralizado (`row[1]`), revisão dos 9 pilares e código completo. |
| [README.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_38_slot_machine/README.md) | Guia curricular, tabela de pagamentos e índice arquitetural. |

---

## 💰 Tabela de Pagamentos Oficial (*Paytable*)

| Símbolo | Nome | Multiplicador 3x (Trinca) | Multiplicador 2x (Par Adjacente) | Exemplo (Aposta $10) |
| :---: | :---: | :---: | :---: | :---: |
| 🍒 | Cereja (*Cherry*) | **3x** | **2x** | Trinca: $30 \| Par: $20 |
| 🍉 | Melancia (*Watermelon*) | **4x** | **3x** | Trinca: $40 \| Par: $30 |
| 🍋 | Limão (*Lemon*) | **5x** | **4x** | Trinca: $50 \| Par: $40 |
| 🔔 | Sino (*Bell*) | **10x** | **5x** | Trinca: $100 \| Par: $50 |
| ⭐ | Estrela (*Star*) | **20x** | **10x** | Trinca: $200 \| Par: $100 |

> **Nota Regulatória:** Caso nenhum par adjacente ou trinca seja formado, o prêmio devolvido é **$0** (derrota da rodada).

---

## 💻 Código-Fonte e Testes no Projeto
- Motor de Regras e Pagamentos: [`SlotMachineEngine.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/games/SlotMachineEngine.java)
- CLI Interativa de Cassino: [`SlotMachineCLI.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/games/SlotMachineCLI.java)
- Suíte de Testes Automatizados: [`SlotMachineTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/SlotMachineTest.java)
