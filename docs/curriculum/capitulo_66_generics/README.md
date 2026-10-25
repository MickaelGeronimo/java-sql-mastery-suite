# Capítulo 66: Genéricos em Java (generics)

Segurança de tipos em tempo de compilação sem casts manuais, classes genéricas com parâmetros de tipo <T>, métodos genéricos e anatomia interna de coleções da JDK.

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
| :---: | :--- | :--- |\n| **01** | [**01_generics_introducao_conceito_type_parameter_e_type_safety.md**](./01_generics_introducao_conceito_type_parameter_e_type_safety.md) | Curso de Java Completo (Bro Code) — Capítulo 66: `generics 📦` (Parte 1) |
| **02** | [**02_generics_analise_do_jdk_arraylist_e_criando_classe_box.md**](./02_generics_analise_do_jdk_arraylist_e_criando_classe_box.md) | Curso de Java Completo (Bro Code) — Capítulo 66: `generics 📦` (Parte 2) |
| **03** | [**03_generics_criando_classe_generica_box_e_desmistificando_t.md**](./03_generics_criando_classe_generica_box_e_desmistificando_t.md) | Curso de Java Completo (Bro Code) — Capítulo 66: `generics 📦` (Parte 3) |
| **04** | [**04_generics_instanciando_box_string_raw_types_e_getters_setters.md**](./04_generics_instanciando_box_string_raw_types_e_getters_setters.md) | Curso de Java Completo (Bro Code) — Capítulo 66: `generics 📦` (Parte 4) |
| **05** | [**05_generics_type_safety_box_integer_e_erros_de_compilacao.md**](./05_generics_type_safety_box_integer_e_erros_de_compilacao.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 5) |
| **06** | [**06_generics_box_double_autoboxing_e_reusabilidade.md**](./06_generics_box_double_autoboxing_e_reusabilidade.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 6) |
| **07** | [**07_generics_multiplos_parametros_classe_product_t_u.md**](./07_generics_multiplos_parametros_classe_product_t_u.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 7) |
| **08** | [**08_generics_construtor_product_t_u_e_preparando_instanciacao.md**](./08_generics_construtor_product_t_u_e_preparando_instanciacao.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 8) |
| **09** | [**09_generics_instanciacao_product_string_double_e_getter_getitem.md**](./09_generics_instanciacao_product_string_double_e_getter_getitem.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 9) |
| **10** | [**10_generics_getprice_execucao_apple_e_zero_ponto_cinco.md**](./10_generics_getprice_execucao_apple_e_zero_ponto_cinco.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 10) |
| **11** | [**11_generics_multiplos_produtos_combinacoes_diferentes_ticket_15.md**](./11_generics_multiplos_produtos_combinacoes_diferentes_ticket_15.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 11) |
| **12** | [**12_generics_resultado_15_integer_vs_double.md**](./12_generics_resultado_15_integer_vs_double.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 12) |
| **13** | [**13_generics_revisao_mestra_type_parameter_vs_type_argument.md**](./13_generics_revisao_mestra_type_parameter_vs_type_argument.md) | 📦 Curso de Java Completo — Capítulo 66: Generics (Parte 13) |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
