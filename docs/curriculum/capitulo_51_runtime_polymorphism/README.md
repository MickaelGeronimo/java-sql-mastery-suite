# Capítulo 51: Polimorfismo em Tempo de Execução (runtime polymorphism)

Decisão de tipo e comportamento durante a execução com base em entradas do usuário (Scanner), instanciação dinâmica e prevenção de erros com verificação defensiva.

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
| :---: | :--- | :--- |
| **01** | [**01_polimorfismo_em_tempo_de_execucao_definicao_e_conceito_dinamico.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/01_polimorfismo_em_tempo_de_execucao_definicao_e_conceito_dinamico.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |
| **02** | [**02_polimorfismo_em_tempo_de_execucao_o_exemplo_dog_ou_cat_e_o_console_vazio.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/02_polimorfismo_em_tempo_de_execucao_o_exemplo_dog_ou_cat_e_o_console_vazio.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |
| **03** | [**03_polimorfismo_em_tempo_de_execucao_criacao_animal_e_dog.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/03_polimorfismo_em_tempo_de_execucao_criacao_animal_e_dog.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |
| **04** | [**04_polimorfismo_em_tempo_de_execucao_criacao_cat_erro_new_animal_e_scanner.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/04_polimorfismo_em_tempo_de_execucao_criacao_cat_erro_new_animal_e_scanner.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |
| **05** | [**05_polimorfismo_em_tempo_de_execucao_questionario_scanner_e_primeira_decisao_dog.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/05_polimorfismo_em_tempo_de_execucao_questionario_scanner_e_primeira_decisao_dog.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |
| **06** | [**06_polimorfismo_em_tempo_de_execucao_bifurcacao_completa_e_os_dois_testes.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/06_polimorfismo_em_tempo_de_execucao_bifurcacao_completa_e_os_dois_testes.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |
| **07** | [**07_polimorfismo_em_tempo_de_execucao_duplicacao_de_speak_e_reforco_do_runtime.md**](file:///docs/curriculum/capitulo_51_runtime_polymorphism/07_polimorfismo_em_tempo_de_execucao_duplicacao_de_speak_e_reforco_do_runtime.md) | Curso de Java â€” CapÃ­tulo 51: Polimorfismo em Tempo de ExecuÃ§Ã£o (`runtime polymorphism ðŸ¤·â€â™‚ï¸`) |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
