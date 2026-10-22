# Capítulo 68: Enumerações (enums)

Conjuntos fixos de constantes nomeadas fortemente tipadas, enums com campos e construtores privados, switch expressions com enums e conversão segura via valueOf.

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
| :---: | :--- | :--- |\n| **01** | [**01_enums_conceito_criacao_day_e_erro_construtor.md**](./01_enums_conceito_criacao_day_e_erro_construtor.md) | Curso de Java â€” Bro Code (12 Horas) |
| **02** | [**02_enums_construtor_getter_e_switch_expression.md**](./02_enums_construtor_getter_e_switch_expression.md) | Curso de Java â€” Bro Code (12 Horas) |
| **03** | [**03_enums_teste_wednesday_e_erro_string_aspas_no_switch.md**](./03_enums_teste_wednesday_e_erro_string_aspas_no_switch.md) | Curso de Java â€” Bro Code (12 Horas) |
| **04** | [**04_enums_interatividade_scanner_erro_import_e_leitura_resposta.md**](./04_enums_interatividade_scanner_erro_import_e_leitura_resposta.md) | Curso de Java â€” Bro Code (12 Horas) |
| **05** | [**05_enums_touppercase_day_valueof_e_execucao_interativa.md**](./05_enums_touppercase_day_valueof_e_execucao_interativa.md) | Curso de Java â€” Bro Code (12 Horas) |
| **06** | [**06_enums_testes_sunday_pizzaday_e_illegalargumentexception.md**](./06_enums_testes_sunday_pizzaday_e_illegalargumentexception.md) | Curso de Java â€” Bro Code (12 Horas) |
| **07** | [**07_enums_try_catch_safety_pizzaday_e_resumo_final.md**](./07_enums_try_catch_safety_pizzaday_e_resumo_final.md) | Curso de Java â€” Bro Code (12 Horas) |
| **08** | [**08_enums_try_catch_testes_tuesday_e_pizzaday.md**](./08_enums_try_catch_testes_tuesday_e_pizzaday.md) | Curso de Java â€” Bro Code (12 Horas) |
| **09** | [**09_enums_adicionando_pizzaday_em_day_java.md**](./09_enums_adicionando_pizzaday_em_day_java.md) | Curso de Java â€” Bro Code (12 Horas) |
| **10** | [**10_enums_adicionando_pizzaday_no_switch_e_formatacao.md**](./10_enums_adicionando_pizzaday_no_switch_e_formatacao.md) | Curso de Java â€” Bro Code (12 Horas) |
| **11** | [**11_enums_sucesso_pizzaday_comentarios_e_erro_numeros.md**](./11_enums_sucesso_pizzaday_comentarios_e_erro_numeros.md) | Curso de Java â€” Bro Code (12 Horas) |
| **12** | [**12_enums_definicao_oficial_comentarios_e_switch_completo.md**](./12_enums_definicao_oficial_comentarios_e_switch_completo.md) | Curso de Java â€” Bro Code (12 Horas) |\n\n---\n\n> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.\n
