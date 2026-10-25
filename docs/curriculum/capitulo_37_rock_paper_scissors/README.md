# 🗿 Capítulo 37: Rock Paper Scissors (Pedra, Papel e Tesoura em Java)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 37)

---

## 🧭 Visão Geral

O projeto **Rock Paper Scissors** aborda o desenvolvimento de um jogo interativo completo contra a inteligência artificial da CPU:
- **Planejamento Arquitetural (*Blueprint*)**: declaração de variáveis, captura, sorteio da CPU, juiz de condições de vitória, repetição e encerramento
- Sanitização de strings com `.toLowerCase()` e `.trim()`
- Validação booleana com operadores lógicos combinados (`!` e `&&`) para barrar jogadas inválidas (ex: `"gun"`)
- Sorteio da jogada do computador com `random.nextInt(3)` mapeando em `choices[computerIndex]`
- Árvore de decisão do juiz:
  - Empate (*Tie*): `playerChoice.equals(computerChoice)`
  - Condições de Vitória do Jogador com operador lógico OU (`||`):
    - `rock` vence `scissors`
    - `paper` vence `rock`
    - `scissors` vence `paper`
  - Derrota (*You lose!*): caso contrário (`else`)
- Loop contínuo com laço `do-while (playAgain.equals("yes"))`

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_blueprint_variaveis_e_validacao.md](01_blueprint_variaveis_e_validacao.md) | O esqueleto de 6 passos, classes `Scanner` e `Random`, e a blindagem de entrada com `!`, `.equals()` e `&&`. |
| [02_teste_invalido_gun.md](02_teste_invalido_gun.md) | O teste prático com jogada proibida (`"gun"`) e a comprovação da mensagem `"Invalid choice"`. |
| [03_jogada_computador_random.md](03_jogada_computador_random.md) | Sorteio aleatório da jogada da CPU mapeado no vetor `choices = {"rock", "paper", "scissors"}`. |
| [04_testes_aleatorios_e_empate.md](04_testes_aleatorios_e_empate.md) | Testes ao vivo de empates e primeiras saídas no console. |
| [05_estrutura_condicional_juiz.md](05_estrutura_condicional_juiz.md) | A estrutura condicional do juiz para determinar o vencedor de cada rodada. |
| [06_condicoes_vitoria_e_derrota.md](06_condicoes_vitoria_e_derrota.md) | Mapeamento explícito das 3 regras canônicas de vitória do jogo. |
| [07_refatoracao_operador_or_testes.md](07_refatoracao_operador_or_testes.md) | Refatoração profissional com operador `||` agrupando as vitórias em um único bloco. |
| [08_loop_do_while_e_despedida.md](08_loop_do_while_e_despedida.md) | Envolvimento do jogo no laço `do-while` para partidas consecutivas e mensagem de despedida. |
| [09_sessao_ao_vivo_e_conclusao.md](09_sessao_ao_vivo_e_conclusao.md) | Homologação final ao vivo com múltiplas rodadas e encerramento limpo. |
| [README.md](README.md) | Guia curricular e mapa conceitual. |

---

## 🏛️ Matriz de Decisão do Juiz

| Jogador (`player`) | Computador (`computer`) | Resultado | Justificativa |
| :---: | :---: | :---: | :--- |
| *Qualquer* | *Idêntico* | **Empate (*Tie*)** | Jogadas coincidentes. |
| `rock` | `scissors` | **Vitória (*Win*)** | Pedra quebra tesoura. |
| `paper` | `rock` | **Vitória (*Win*)** | Papel embrulha pedra. |
| `scissors` | `paper` | **Vitória (*Win*)** | Tesoura corta papel. |
| *Outros* | *Outros* | **Derrota (*Lose*)** | CPU venceu a rodada. |

---

## 💻 Código-Fonte e Testes no Projeto
- Motor de Regras e Juiz: [`RockPaperScissorsEngine.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/games/RockPaperScissorsEngine.java)
- CLI Interativa: [`RockPaperScissorsCLI.java`](../../../src/main/java/com/mickaelgeronimo/mastery/core/games/RockPaperScissorsCLI.java)
- Suíte de Testes Automatizados: [`RockPaperScissorsTest.java`](../../../src/test/java/com/mickaelgeronimo/mastery/core/RockPaperScissorsTest.java)
