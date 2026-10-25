# Capítulo 61: Jogo da Forca Completo (hangman game)

Projeto integrador de grande porte: arte ASCII interativa, switch expressions, text blocks, gestão de listas de palavras com arquivo externo e lógica de vitória/derrota.

---

## 🧭 Visão Geral e Arquitetura Conceitual

Este capítulo aborda de ponta a ponta a teoria, sintaxe, boas práticas e armadilhas comuns encontradas na prática profissional ao lidar com este tópico em Java.

### 🎯 Objetivos de Aprendizagem
- Dominar a sintaxe fundamental e convenções da linguagem Java.
- Compreender a anatomia interna e comportamento na JVM.
- Reconhecer e contornar armadilhas e erros de compilação clássicos.
- Aplicar o conceito em arquiteturas limpas e desacopladas.

---

## 📚 Índice Modular dos Materiais de Estudo

Todos os documentos detalhados de estudo e transcrição didática deste capítulo estão catalogados abaixo:

| Parte | Arquivo | Tema Principal |
| :---: | :--- | :--- |\n| **01** | [**01_hangman_game_prototipo_inicial_wordstate_arraylist.md**](./01_hangman_game_prototipo_inicial_wordstate_arraylist.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 1: Protótipo Inicial, `ArrayList<Character>` e Estado da Palavra |
| **02** | [**02_hangman_game_ascii_art_switch_expression_e_text_blocks.md**](./02_hangman_game_ascii_art_switch_expression_e_text_blocks.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 2: Demonstração Dinâmica, Banner de Boas-Vindas e Desenho ASCII da Forca com Switch Expression e Text Blocks |
| **03** | [**03_hangman_game_teste_gethangmanart_code_folding_e_wordstate.md**](./03_hangman_game_teste_gethangmanart_code_folding_e_wordstate.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 3: Testando a Forca ASCII, Code Folding no IntelliJ e Exibição do `wordState` |
| **04** | [**04_hangman_game_enhanced_for_loop_formatacao_e_scanner_charat.md**](./04_hangman_game_enhanced_for_loop_formatacao_e_scanner_charat.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 4: Formatação dos Tracinhos com For-Each, Diferença entre `print` e `println` e Captura do Palpite com `charAt(0)` |
| **05** | [**05_hangman_game_indexof_erro_boolean_int_e_if_else.md**](./05_hangman_game_indexof_erro_boolean_int_e_if_else.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 5: Verificação de Palpites com `indexOf()`, o Erro de `boolean` vs `int` e o Bloco `if-else` |
| **06** | [**06_hangman_game_revelando_letras_wordstate_set_e_loop_principal_while.md**](./06_hangman_game_revelando_letras_wordstate_set_e_loop_principal_while.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 6: Revelando as Letras com `wordState.set()`, Letras Repetidas e o Loop Principal `while(wrongGuesses < 6)` |
| **07** | [**07_hangman_game_teste_jogo_completo_problema_loop_infinito_vitoria.md**](./07_hangman_game_teste_jogo_completo_problema_loop_infinito_vitoria.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 7: Jogando a Partida de Teste, a Palavra Completa e o Bug do Loop Infinito |
| **08** | [**08_hangman_game_penalidade_wrongguesses_e_desenho_ascii_da_forca.md**](./08_hangman_game_penalidade_wrongguesses_e_desenho_ascii_da_forca.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 8: Penalidade de Erros com `wrongGuesses++` e Exibição Dinâmica do Boneco na Forca |
| **09** | [**09_hangman_game_condicao_de_derrota_game_over_e_revelacao_da_palavra.md**](./09_hangman_game_condicao_de_derrota_game_over_e_revelacao_da_palavra.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 9: A Tela de Derrota, `GAME OVER!` e Revelação da Palavra Secreta |
| **10** | [**10_hangman_game_condicao_de_vitoria_contains_e_break.md**](./10_hangman_game_condicao_de_vitoria_contains_e_break.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 10: A Condição de Vitória, o Método `contains()`, Negação com `!` e o Comando `break` |
| **11** | [**11_hangman_game_criacao_do_words_txt_banco_de_palavras_e_transicao.md**](./11_hangman_game_criacao_do_words_txt_banco_de_palavras_e_transicao.md) | 🎮 Capítulo 61: Java Hangman Game (Jogo da Forca) 🕺 — Parte 11: Conclusão do Protótipo, Criação do `words.txt` e Transição para Palavras Dinâmicas |
| **12** | [**12_hangman_game_sorteio_aleatorio_random_nextint_e_trim.md**](./12_hangman_game_sorteio_aleatorio_random_nextint_e_trim.md) | Curso de Java Completo (Bro Code) — Capítulo 61: `⭐ hangman game 🕺` (Parte 13) |
| **13** | [**13_hangman_game_jogo_completo_integrado_partida_derrota_orange.md**](./13_hangman_game_jogo_completo_integrado_partida_derrota_orange.md) | Curso de Java Completo (Bro Code) — Capítulo 61: `⭐ hangman game 🕺` (Parte 14 — O Grande Final!) |
| **14** | [**14_hangman_game_condicao_de_vitoria_real_coconut_e_encerramento.md**](./14_hangman_game_condicao_de_vitoria_real_coconut_e_encerramento.md) | Curso de Java Completo (Bro Code) — Capítulo 61: `⭐ hangman game 🕺` (Parte 15 — O Grande Encerramento!) |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
