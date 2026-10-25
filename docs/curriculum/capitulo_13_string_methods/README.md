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
| :---: | :--- | :--- |\n| **01** | [**01_string_methods.md**](./01_string_methods.md) | 🧵 Capítulo 13: `String Methods` (Métodos de Texto em Java) — Parte 1 |
| **02** | [**02_string_methods_case.md**](./02_string_methods_case.md) | 🧵 Capítulo 13: `String Methods` — Parte 2 (`toUpperCase` e `toLowerCase`) |
| **03** | [**03_string_methods_trim_replace_isempty_parte3.md**](./03_string_methods_trim_replace_isempty_parte3.md) | 🧵 Capítulo 13: `String Methods` — Parte 3 (`trim`, `replace` e `isEmpty`) |
| **04** | [**04_string_methods_isempty_e_erro_charat_parte4.md**](./04_string_methods_isempty_e_erro_charat_parte4.md) | 🧵 Capítulo 13: `String Methods` — Parte 4 (O Teste de String Vazia `""` e o Erro Vermelho do `charAt`) |
| **05** | [**05_string_isempty_com_if_else.md**](./05_string_isempty_com_if_else.md) | 🧵 Capítulo 13: `String Methods` — Parte 5 (`isEmpty` na Prática com `if / else`) |
| **06** | [**06_string_contains_e_comentarios_bloco.md**](./06_string_contains_e_comentarios_bloco.md) | 🧵 Capítulo 13: `String Methods` — Parte 6 (`contains` e Comentários de Bloco `/* */`) |
| **07** | [**07_string_equals.md**](./07_string_equals.md) | 🧵 Capítulo 13: `String Methods` — Parte 7 (O Método `.equals()` e a Regra de Ouro das Strings) |
| **08** | [**08_string_equals_vs_equalsignorecase.md**](./08_string_equals_vs_equalsignorecase.md) | 🧵 Capítulo 13: Comparação de Textos em Java — O Guia Definitivo (`.equals()` vs. `.equalsIgnoreCase()`) |
| **09** | [**09_string_isempty.md**](./09_string_isempty.md) | 📭 O Comando `isEmpty()`: Verificando se o Texto está Vazio! |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
