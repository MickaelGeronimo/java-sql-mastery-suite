# Capítulo 03: Operadores Aritméticos e Expressões

Operações matemáticas fundamentais (+, -, *, /, %), precedência de operadores PEMDAS, operadores de incremento/decremento (++ e --) e atribuição aumentada (+=, -=, *=, /=).

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
| :---: | :--- | :--- |\n| **01** | [**01_operadores_logicos_e_comercial.md**](./01_operadores_logicos_e_comercial.md) | ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 1) |
| **02** | [**02_operador_ternario.md**](./02_operador_ternario.md) | ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 1: Introdução e o `if-else` Tradicional |
| **03** | [**03_operador_ternario.md**](./03_operador_ternario.md) | ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 2: A Mágica de 1 Linha na Prática |
| **04** | [**04_oop_atributos_instanciacao_operador_ponto_e_hashcode.md**](./04_oop_atributos_instanciacao_operador_ponto_e_hashcode.md) | 🧱 Capítulo 39: Programação Orientada a Objetos (OOP) em Java — Parte 2 |
| **05** | [**05_operadores_logicos_not_encadeamento.md**](./05_operadores_logicos_not_encadeamento.md) | ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 2) |
| **06** | [**06_calculadora_divisao_zero.md**](./06_calculadora_divisao_zero.md) | 🖩 Capítulo 19: Programa Calculadora (Calculator Program) — Parte 2: O Mistério do `Infinity`, a Divisão por Zero e o Bug do `0.0` |
| **07** | [**07_operador_ternario_exemplos.md**](./07_operador_ternario_exemplos.md) | ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 3: Dois Novos Exemplos (Par/Ímpar e A.M./P.M.) |
| **08** | [**08_operadores_logicos_or.md**](./08_operadores_logicos_or.md) | ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 3) |
| **09** | [**09_oop_leitura_completa_atributos_operador_ponto.md**](./09_oop_leitura_completa_atributos_operador_ponto.md) | 🧱 Capítulo 39: Programação Orientada a Objetos (OOP) em Java — Parte 3 |
| **10** | [**10_operadores_logicos_validacao_username.md**](./10_operadores_logicos_validacao_username.md) | ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 4) |
| **11** | [**11_operador_ternario_taxas.md**](./11_operador_ternario_taxas.md) | ❓ Capítulo 16: Operador Ternário (Ternary Operator) — Parte 4: A Grande Novidade com Números (`double` e Imposto) |
| **12** | [**12_wrapper_classes_metodos_parse_o_erro_do_operador_e_character_isletter.md**](./12_wrapper_classes_metodos_parse_o_erro_do_operador_e_character_isletter.md) | Curso de Java — Capítulo 55 (Parte 4): Wrapper Classes 🎁 |
| **13** | [**13_operadores_logicos_contains_validacao_completa.md**](./13_operadores_logicos_contains_validacao_completa.md) | ❕ Curso Completo de Java — Capítulo 20: Operadores Lógicos (Parte 5 - Conclusão) |
| **14** | [**14_rock_paper_scissors_refatoracao_operador_or_testes.md**](./14_rock_paper_scissors_refatoracao_operador_or_testes.md) | ☕ Curso de Java — Capítulo 37: `⭐ rock paper scissors 🗿` (Parte 7) |
| **15** | [**15_banking_program_operador_mais_igual_e_testes_reais_de_deposito.md**](./15_banking_program_operador_mais_igual_e_testes_reais_de_deposito.md) | 💰 Curso de Java para Iniciantes — Bro Code |
| **16** | [**16_banking_program_metodo_withdraw_e_operador_menos_igual.md**](./16_banking_program_metodo_withdraw_e_operador_menos_igual.md) | 💰 Curso de Java para Iniciantes — Bro Code |
| **17** | [**17_operador_incremento_plusplus.md**](./17_operador_incremento_plusplus.md) | 🚀 A Evolução do Incremento: De `x = x + 1` até o Lendário `x++` |
| **18** | [**18_ordem_operacoes_pemdas.md**](./18_ordem_operacoes_pemdas.md) | 🧮 Ordem de Operações no Java: A Regra do PEMDAS (Guia Completo para Iniciantes) |
| **19** | [**19_atalho_modulo.md**](./19_atalho_modulo.md) | ⚡ O Atalho do Resto da Divisão: `x %= y;` |
| **20** | [**20_operador_decremento.md**](./20_operador_decremento.md) | 📉 Operador de Decremento (`x--`) — O Irmão Inverso do `x++` |
| **21** | [**21_incremento_multiplo.md**](./21_incremento_multiplo.md) | 🔢 Múltiplos Incrementos: Como o `x++` Acumula Valor |
| **22** | [**22_int_vs_double_divisao.md**](./22_int_vs_double_divisao.md) | 🔢 int vs double na Divisão — A Grande Diferença! |
| **23** | [**23_divisao_abreviada.md**](./23_divisao_abreviada.md) | ➗ Operador de Atribuição com Divisão (`/=`) — Guia para Iniciantes |
| **24** | [**24_incremento_decremento.md**](./24_incremento_decremento.md) | 📈 Operadores de Incremento e Decremento (Increment & Decrement) |
| **25** | [**25_modulo_com_resto.md**](./25_modulo_com_resto.md) | 🍬 O Atalho do Resto com Sobra (`x %= y` com `y = 3`) |
| **26** | [**26_operadores_aritmeticos.md**](./26_operadores_aritmeticos.md) | ➕ Operadores Aritméticos (Arithmetic Operators) — Explicação Linha por Linha |
| **27** | [**27_modulo.md**](./27_modulo.md) | ➗ O Operador Módulo `%` (Resto da Divisão) — Explicação |
| **28** | [**28_modulo_atribuicao.md**](./28_modulo_atribuicao.md) | 🍰 Operador de Resto com Auto-atribuição (`x = x % y` ou `x %= y`) |
| **29** | [**29_atribuicao_aumentada.md**](./29_atribuicao_aumentada.md) | 🔄 Operadores de Atribuição Aumentada (Augmented Assignment Operators) |
| **30** | [**30_numeros_negativos.md**](./30_numeros_negativos.md) | 🥶 Como o Java Lida com Subtração e Números Negativos |
| **31** | [**31_area_retangulo.md**](./31_area_retangulo.md) | 📐 Calculando a Área de um Retângulo — Explicação Linha por Linha |
| **32** | [**32_cm_quadrado.md**](./32_cm_quadrado.md) | 📐 Melhoria no Código — Adicionando "cm²" ao Resultado |
| **33** | [**33_concatenacao_unidade_medida.md**](./33_concatenacao_unidade_medida.md) | 📏 O Trenzinho da Concatenação: Adicionando Unidade de Medida (`+ "cm"`)! |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
