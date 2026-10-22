# Capítulo 49: Interfaces em Java (interfaces)

Definição formal de interfaces, herança múltipla de contratos, a clássica analogia biológica de Presa e Predador (Prey, Predator, Hawk, Fish, Rabbit) e desacoplamento.

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
| **01** | [**01_interfaces_definicao_analogia_prey_predator.md**](file:///docs/curriculum/capitulo_49_interfaces/01_interfaces_definicao_analogia_prey_predator.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **02** | [**02_interfaces_metodo_hunt_e_classes_rabbit_fish.md**](file:///docs/curriculum/capitulo_49_interfaces/02_interfaces_metodo_hunt_e_classes_rabbit_fish.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **03** | [**03_interfaces_o_trio_de_animais_com_hawk.md**](file:///docs/curriculum/capitulo_49_interfaces/03_interfaces_o_trio_de_animais_com_hawk.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **04** | [**04_interfaces_o_meme_do_coelho_de_monty_python.md**](file:///docs/curriculum/capitulo_49_interfaces/04_interfaces_o_meme_do_coelho_de_monty_python.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **05** | [**05_interfaces_o_nascimento_do_implements_em_rabbit.md**](file:///docs/curriculum/capitulo_49_interfaces/05_interfaces_o_nascimento_do_implements_em_rabbit.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **06** | [**06_interfaces_o_erro_obrigatorio_de_flee_em_rabbit.md**](file:///docs/curriculum/capitulo_49_interfaces/06_interfaces_o_erro_obrigatorio_de_flee_em_rabbit.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **07** | [**07_interfaces_implementando_flee_no_rabbit_e_chamada_no_main.md**](file:///docs/curriculum/capitulo_49_interfaces/07_interfaces_implementando_flee_no_rabbit_e_chamada_no_main.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **08** | [**08_interfaces_sucesso_do_rabbit_e_implementando_hawk.md**](file:///docs/curriculum/capitulo_49_interfaces/08_interfaces_sucesso_do_rabbit_e_implementando_hawk.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **09** | [**09_interfaces_execucao_dupla_rabbit_e_hawk.md**](file:///docs/curriculum/capitulo_49_interfaces/09_interfaces_execucao_dupla_rabbit_e_hawk.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **10** | [**10_interfaces_o_erro_didatico_dos_papeis_invertidos.md**](file:///docs/curriculum/capitulo_49_interfaces/10_interfaces_o_erro_didatico_dos_papeis_invertidos.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **11** | [**11_interfaces_multiplas_em_fish_e_o_contrato_pendente.md**](file:///docs/curriculum/capitulo_49_interfaces/11_interfaces_multiplas_em_fish_e_o_contrato_pendente.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |
| **12** | [**12_interfaces_o_peixe_completo_e_fechamento.md**](file:///docs/curriculum/capitulo_49_interfaces/12_interfaces_o_peixe_completo_e_fechamento.md) | Curso de Java â€” CapÃ­tulo 49: Interfaces (`interfaces ðŸ“‹`) |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
