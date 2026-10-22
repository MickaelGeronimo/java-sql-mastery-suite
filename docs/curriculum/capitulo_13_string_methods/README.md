# Capítulo 13: Métodos da Classe String

Dissecação da classe String imutável: length, equals, equalsIgnoreCase, contains, isEmpty, charAt, trim, replace, toUpperCase e toLowerCase.

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
| **01** | [**01_string_methods.md**](file:///docs/curriculum/capitulo_13_string_methods/01_string_methods.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` (MÃ©todos de Texto em Java) â€” Parte 1 |
| **02** | [**02_string_methods_case.md**](file:///docs/curriculum/capitulo_13_string_methods/02_string_methods_case.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 2 (`toUpperCase` e `toLowerCase`) |
| **03** | [**03_string_methods_trim_replace_isempty_parte3.md**](file:///docs/curriculum/capitulo_13_string_methods/03_string_methods_trim_replace_isempty_parte3.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 3 (`trim`, `replace` e `isEmpty`) |
| **04** | [**04_string_methods_isempty_e_erro_charat_parte4.md**](file:///docs/curriculum/capitulo_13_string_methods/04_string_methods_isempty_e_erro_charat_parte4.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 4 (O Teste de String Vazia `""` e o Erro Vermelho do `charAt`) |
| **05** | [**05_string_isempty_com_if_else.md**](file:///docs/curriculum/capitulo_13_string_methods/05_string_isempty_com_if_else.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 5 (`isEmpty` na PrÃ¡tica com `if / else`) |
| **06** | [**06_string_contains_e_comentarios_bloco.md**](file:///docs/curriculum/capitulo_13_string_methods/06_string_contains_e_comentarios_bloco.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 6 (`contains` e ComentÃ¡rios de Bloco `/* */`) |
| **07** | [**07_string_equals.md**](file:///docs/curriculum/capitulo_13_string_methods/07_string_equals.md) | ðŸ§µ CapÃ­tulo 13: `String Methods` â€” Parte 7 (O MÃ©todo `.equals()` e a Regra de Ouro das Strings) |
| **08** | [**08_string_equals_vs_equalsignorecase.md**](file:///docs/curriculum/capitulo_13_string_methods/08_string_equals_vs_equalsignorecase.md) | ðŸ§µ CapÃ­tulo 13: ComparaÃ§Ã£o de Textos em Java â€” O Guia Definitivo (`.equals()` vs. `.equalsIgnoreCase()`) |
| **09** | [**09_string_isempty.md**](file:///docs/curriculum/capitulo_13_string_methods/09_string_isempty.md) | ðŸ“­ O Comando `isEmpty()`: Verificando se o Texto estÃ¡ Vazio! |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
