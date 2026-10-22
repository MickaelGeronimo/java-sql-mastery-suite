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
| :---: | :--- | :--- |\n| **01** | [**01_operadores_logicos_e_comercial.md**](./01_operadores_logicos_e_comercial.md) | â• Curso Completo de Java â€” CapÃ­tulo 20: Operadores LÃ³gicos (Parte 1) |
| **02** | [**02_operador_ternario.md**](./02_operador_ternario.md) | â“ CapÃ­tulo 16: Operador TernÃ¡rio (Ternary Operator) â€” Parte 1: IntroduÃ§Ã£o e o `if-else` Tradicional |
| **03** | [**03_operador_ternario.md**](./03_operador_ternario.md) | â“ CapÃ­tulo 16: Operador TernÃ¡rio (Ternary Operator) â€” Parte 2: A MÃ¡gica de 1 Linha na PrÃ¡tica |
| **04** | [**04_oop_atributos_instanciacao_operador_ponto_e_hashcode.md**](./04_oop_atributos_instanciacao_operador_ponto_e_hashcode.md) | ðŸ§± CapÃ­tulo 39: ProgramaÃ§Ã£o Orientada a Objetos (OOP) em Java â€” Parte 2 |
| **05** | [**05_operadores_logicos_not_encadeamento.md**](./05_operadores_logicos_not_encadeamento.md) | â• Curso Completo de Java â€” CapÃ­tulo 20: Operadores LÃ³gicos (Parte 2) |
| **06** | [**06_calculadora_divisao_zero.md**](./06_calculadora_divisao_zero.md) | ðŸ–© CapÃ­tulo 19: Programa Calculadora (Calculator Program) â€” Parte 2: O MistÃ©rio do `Infinity`, a DivisÃ£o por Zero e o Bug do `0.0` |
| **07** | [**07_operador_ternario_exemplos.md**](./07_operador_ternario_exemplos.md) | â“ CapÃ­tulo 16: Operador TernÃ¡rio (Ternary Operator) â€” Parte 3: Dois Novos Exemplos (Par/Ãmpar e A.M./P.M.) |
| **08** | [**08_operadores_logicos_or.md**](./08_operadores_logicos_or.md) | â• Curso Completo de Java â€” CapÃ­tulo 20: Operadores LÃ³gicos (Parte 3) |
| **09** | [**09_oop_leitura_completa_atributos_operador_ponto.md**](./09_oop_leitura_completa_atributos_operador_ponto.md) | ðŸ§± CapÃ­tulo 39: ProgramaÃ§Ã£o Orientada a Objetos (OOP) em Java â€” Parte 3 |
| **10** | [**10_operadores_logicos_validacao_username.md**](./10_operadores_logicos_validacao_username.md) | â• Curso Completo de Java â€” CapÃ­tulo 20: Operadores LÃ³gicos (Parte 4) |
| **11** | [**11_operador_ternario_taxas.md**](./11_operador_ternario_taxas.md) | â“ CapÃ­tulo 16: Operador TernÃ¡rio (Ternary Operator) â€” Parte 4: A Grande Novidade com NÃºmeros (`double` e Imposto) |
| **12** | [**12_wrapper_classes_metodos_parse_o_erro_do_operador_e_character_isletter.md**](./12_wrapper_classes_metodos_parse_o_erro_do_operador_e_character_isletter.md) | Curso de Java â€” CapÃ­tulo 55 (Parte 4): Wrapper Classes ðŸŽ |
| **13** | [**13_operadores_logicos_contains_validacao_completa.md**](./13_operadores_logicos_contains_validacao_completa.md) | â• Curso Completo de Java â€” CapÃ­tulo 20: Operadores LÃ³gicos (Parte 5 - ConclusÃ£o) |
| **14** | [**14_rock_paper_scissors_refatoracao_operador_or_testes.md**](./14_rock_paper_scissors_refatoracao_operador_or_testes.md) | â˜• Curso de Java â€” CapÃ­tulo 37: `â­ rock paper scissors ðŸ—¿` (Parte 7) |
| **15** | [**15_banking_program_operador_mais_igual_e_testes_reais_de_deposito.md**](./15_banking_program_operador_mais_igual_e_testes_reais_de_deposito.md) | ðŸ’° Curso de Java para Iniciantes â€” Bro Code |
| **16** | [**16_banking_program_metodo_withdraw_e_operador_menos_igual.md**](./16_banking_program_metodo_withdraw_e_operador_menos_igual.md) | ðŸ’° Curso de Java para Iniciantes â€” Bro Code |
| **17** | [**17_operador_incremento_plusplus.md**](./17_operador_incremento_plusplus.md) | ðŸš€ A EvoluÃ§Ã£o do Incremento: De `x = x + 1` atÃ© o LendÃ¡rio `x++` |
| **18** | [**18_ordem_operacoes_pemdas.md**](./18_ordem_operacoes_pemdas.md) | ðŸ§® Ordem de OperaÃ§Ãµes no Java: A Regra do PEMDAS (Guia Completo para Iniciantes) |
| **19** | [**19_atalho_modulo.md**](./19_atalho_modulo.md) | âš¡ O Atalho do Resto da DivisÃ£o: `x %= y;` |
| **20** | [**20_operador_decremento.md**](./20_operador_decremento.md) | ðŸ“‰ Operador de Decremento (`x--`) â€” O IrmÃ£o Inverso do `x++` |
| **21** | [**21_incremento_multiplo.md**](./21_incremento_multiplo.md) | ðŸ”¢ MÃºltiplos Incrementos: Como o `x++` Acumula Valor |
| **22** | [**22_int_vs_double_divisao.md**](./22_int_vs_double_divisao.md) | ðŸ”¢ int vs double na DivisÃ£o â€” A Grande DiferenÃ§a! |
| **23** | [**23_divisao_abreviada.md**](./23_divisao_abreviada.md) | âž— Operador de AtribuiÃ§Ã£o com DivisÃ£o (`/=`) â€” Guia para Iniciantes |
| **24** | [**24_incremento_decremento.md**](./24_incremento_decremento.md) | ðŸ“ˆ Operadores de Incremento e Decremento (Increment & Decrement) |
| **25** | [**25_modulo_com_resto.md**](./25_modulo_com_resto.md) | ðŸ¬ O Atalho do Resto com Sobra (`x %= y` com `y = 3`) |
| **26** | [**26_operadores_aritmeticos.md**](./26_operadores_aritmeticos.md) | âž• Operadores AritmÃ©ticos (Arithmetic Operators) â€” ExplicaÃ§Ã£o Linha por Linha |
| **27** | [**27_modulo.md**](./27_modulo.md) | âž— O Operador MÃ³dulo `%` (Resto da DivisÃ£o) â€” ExplicaÃ§Ã£o |
| **28** | [**28_modulo_atribuicao.md**](./28_modulo_atribuicao.md) | ðŸ° Operador de Resto com Auto-atribuiÃ§Ã£o (`x = x % y` ou `x %= y`) |
| **29** | [**29_atribuicao_aumentada.md**](./29_atribuicao_aumentada.md) | ðŸ”„ Operadores de AtribuiÃ§Ã£o Aumentada (Augmented Assignment Operators) |
| **30** | [**30_numeros_negativos.md**](./30_numeros_negativos.md) | ðŸ¥¶ Como o Java Lida com SubtraÃ§Ã£o e NÃºmeros Negativos |
| **31** | [**31_area_retangulo.md**](./31_area_retangulo.md) | ðŸ“ Calculando a Ãrea de um RetÃ¢ngulo â€” ExplicaÃ§Ã£o Linha por Linha |
| **32** | [**32_cm_quadrado.md**](./32_cm_quadrado.md) | ðŸ“ Melhoria no CÃ³digo â€” Adicionando "cmÂ²" ao Resultado |
| **33** | [**33_concatenacao_unidade_medida.md**](./33_concatenacao_unidade_medida.md) | ðŸ“ O Trenzinho da ConcatenaÃ§Ã£o: Adicionando Unidade de Medida (`+ "cm"`)! |\n\n---\n\n> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.\n
