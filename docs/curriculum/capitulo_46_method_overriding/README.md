# Capítulo 46: Sobrescrita de Métodos (method overriding)

Aprenda como subclasses fornecem implementações especializadas de métodos definidos na superclasse, o papel da anotação @Override, boas práticas e prevenção de erros sutis de compilação.

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
| :---: | :--- | :--- |\n| **01** | [**01_method_overriding_definicao_e_criacao_animal_dog_cat.md**](./01_method_overriding_definicao_e_criacao_animal_dog_cat.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |
| **02** | [**02_method_overriding_criando_fish_extends_animal_e_metodo_move.md**](./02_method_overriding_criando_fish_extends_animal_e_metodo_move.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |
| **03** | [**03_method_overriding_instanciando_dog_cat_fish_e_o_copia_cola_move.md**](./03_method_overriding_instanciando_dog_cat_fish_e_o_copia_cola_move.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |
| **04** | [**04_method_overriding_o_problema_dos_tres_running_e_a_anotacao_override_no_fish.md**](./04_method_overriding_o_problema_dos_tres_running_e_a_anotacao_override_no_fish.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |
| **05** | [**05_method_overriding_o_peixe_nadando_no_console.md**](./05_method_overriding_o_peixe_nadando_no_console.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |
| **06** | [**06_method_overriding_o_perigo_do_typo_e_o_guardiao_override.md**](./06_method_overriding_o_perigo_do_typo_e_o_guardiao_override.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |
| **07** | [**07_method_overriding_conclusao_reuso_e_fechamento.md**](./07_method_overriding_conclusao_reuso_e_fechamento.md) | Curso de Java â€” CapÃ­tulo 46: Sobrescrita de MÃ©todo (`method overriding â™»ï¸`) |\n\n---\n\n> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.\n
