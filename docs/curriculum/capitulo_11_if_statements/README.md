# Capítulo 11: Estruturas Condicionais (if, else if, else)

Tomada de decisões em Java: ramificações lógicas, comparação de igualdade (== vs equals), armadilha da ordem de avaliação de faixas de valores e validações booleanas.

---

## 🧭 Visão Geral e Arquitetura Conceitual

Este capítulo aborda de ponta a ponta a teoria, sintaxe, boas práticas e armadilhas comuns encontradas na prática profissional ao lidar com este tópico em Java.

### 🎯 Objetivos de Aprendizagem
- Dominar a sintaxe fundamental e convenções da linguagem Java.
- Compreender a anatomia interna e comportamento na JVM.
- Reconhecer e contornar armadilhas e erros de compilação clássicos.
- Aplicar o conceito em projetos práticos de console.

---

## 📚 Índice Modular dos Materiais de Estudo

Todos os documentos detalhados de estudo e transcrição didática deste capítulo estão catalogados abaixo:

| Parte | Arquivo | Tema Principal |
| :---: | :--- | :--- |
| **01** | [**01_hangman_game_indexof_erro_boolean_int_e_if_else.md**](file:///docs/curriculum/capitulo_11_if_statements/01_hangman_game_indexof_erro_boolean_int_e_if_else.md) | ðŸŽ® CapÃ­tulo 61: Java Hangman Game (Jogo da Forca) ðŸ•º â€” Parte 5: VerificaÃ§Ã£o de Palpites com `indexOf()`, o Erro de `boolean` vs `int` e o Bloco `if-else` |
| **02** | [**02_string_isempty_com_if_else.md**](file:///docs/curriculum/capitulo_11_if_statements/02_string_isempty_com_if_else.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 5 (`isEmpty` na PrÃ¡tica com `if / else`) |
| **03** | [**03_hashmaps_if_else_containskey_apple_pineapple.md**](file:///docs/curriculum/capitulo_11_if_statements/03_hashmaps_if_else_containskey_apple_pineapple.md) | Curso de Java â€” Bro Code (12 Horas) |
| **04** | [**04_banking_program_validacao_if_else_no_deposit_e_conexao_no_main.md**](file:///docs/curriculum/capitulo_11_if_statements/04_banking_program_validacao_if_else_no_deposit_e_conexao_no_main.md) | ðŸ’° Curso de Java para Iniciantes â€” Bro Code |
| **05** | [**05_igual_igual.md**](file:///docs/curriculum/capitulo_11_if_statements/05_igual_igual.md) | ðŸ‘¶ O MistÃ©rio do `==` Revelado: O Guia Definitivo do RecÃ©m-Nascido (`age == 0`) |
| **06** | [**06_if_multiplos_grupos.md**](file:///docs/curriculum/capitulo_11_if_statements/06_if_multiplos_grupos.md) | ðŸ‘¥ Dois `if`s no Mesmo Programa: A Chegada do `name` e dos Grupos de DecisÃ£o! |
| **07** | [**07_armadilha_ordem_if.md**](file:///docs/curriculum/capitulo_11_if_statements/07_armadilha_ordem_if.md) | ðŸš¨ A Grande Pegadinha do `if`: A Ordem das CondiÃ§Ãµes Importa! |
| **08** | [**08_ordem_corrigida_if.md**](file:///docs/curriculum/capitulo_11_if_statements/08_ordem_corrigida_if.md) | ðŸ† A Ordem Corrigida: Consertando o Bug do Idoso! |
| **09** | [**09_if_com_boolean_group3.md**](file:///docs/curriculum/capitulo_11_if_statements/09_if_com_boolean_group3.md) | ðŸŽ“ A Chegada do `boolean` e do GROUP 3: VocÃª Ã© Estudante? |
| **10** | [**10_else_if.md**](file:///docs/curriculum/capitulo_11_if_statements/10_else_if.md) | ðŸªœ Tomando VÃ¡rias DecisÃµes: O Comando `else if` ("SENÃƒO SE") |
| **11** | [**11_if_else.md**](file:///docs/curriculum/capitulo_11_if_statements/11_if_else.md) | ðŸ”€ O Guia Definitivo de `if` e `else` para Leigos Absolutos |
| **12** | [**12_if_com_scanner.md**](file:///docs/curriculum/capitulo_11_if_statements/12_if_com_scanner.md) | ðŸŽ® Juntando Tudo: `if-else` + `Scanner` (Programa 100% Interativo!) |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
