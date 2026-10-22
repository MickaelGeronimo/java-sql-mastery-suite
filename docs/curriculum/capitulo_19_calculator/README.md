# Capítulo 19: Calculadora de Console com Switches

Projeto prático integrador: leitura de operandos, seleção de operador (+, -, *, /), prevenção ativa contra divisão por zero e tratamento de opções inválidas.

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
| **01** | [**01_calculadora_juros_compostos_parte1.md**](file:///docs/curriculum/capitulo_19_calculator/01_calculadora_juros_compostos_parte1.md) | ðŸ¦ CapÃ­tulo 11: Calculadora de Juros Compostos â€” Parte 1 (PreparaÃ§Ã£o e Entradas) |
| **02** | [**02_calculadora.md**](file:///docs/curriculum/capitulo_19_calculator/02_calculadora.md) | ðŸ–© CapÃ­tulo 19: Programa Calculadora (Calculator Program) â€” Parte 1: Estrutura, Leitura de `char` e OperaÃ§Ãµes no `switch` |
| **03** | [**03_calculadora_juros_compostos_parte2.md**](file:///docs/curriculum/capitulo_19_calculator/03_calculadora_juros_compostos_parte2.md) | ðŸ¦ CapÃ­tulo 11: Calculadora de Juros Compostos â€” Parte 2 (A FÃ³rmula MatemÃ¡tica e o Resultado) |
| **04** | [**04_calculadora_divisao_zero.md**](file:///docs/curriculum/capitulo_19_calculator/04_calculadora_divisao_zero.md) | ðŸ–© CapÃ­tulo 19: Programa Calculadora (Calculator Program) â€” Parte 2: O MistÃ©rio do `Infinity`, a DivisÃ£o por Zero e o Bug do `0.0` |
| **05** | [**05_calculadora_juros_compostos_parte3.md**](file:///docs/curriculum/capitulo_19_calculator/05_calculadora_juros_compostos_parte3.md) | ðŸ¦ CapÃ­tulo 11: Calculadora de Juros Compostos â€” Parte 3 (O `printf` Profissional) |
| **06** | [**06_calculadora_validacao_final.md**](file:///docs/curriculum/capitulo_19_calculator/06_calculadora_validacao_final.md) | ðŸ–© Curso Completo de Java â€” CapÃ­tulo 19: Calculadora em Java (Parte 3 - Final) |
| **07** | [**07_calculadora_testes_finais.md**](file:///docs/curriculum/capitulo_19_calculator/07_calculadora_testes_finais.md) | ðŸ–© Curso Completo de Java â€” CapÃ­tulo 19: Calculadora em Java (Parte 4 - FinalizaÃ§Ã£o e Testes de Borda) |
| **08** | [**08_calculadora_circunferencia.md**](file:///docs/curriculum/capitulo_19_calculator/08_calculadora_circunferencia.md) | ðŸ• Novo Mini-Projeto: Calculadora de CÃ­rculos (CircunferÃªncia com `Math.PI`)! |
| **09** | [**09_calculadora_hipotenusa_etapa1.md**](file:///docs/curriculum/capitulo_19_calculator/09_calculadora_hipotenusa_etapa1.md) | ðŸ“ Mini-Projeto: Calculadora de Hipotenusa (Teorema de PitÃ¡goras)! |
| **10** | [**10_calculadora_hipotenusa_final.md**](file:///docs/curriculum/capitulo_19_calculator/10_calculadora_hipotenusa_final.md) | ðŸ“ Calculadora de Hipotenusa Completa: O Encontro do `pow` com o `sqrt`! |

---

> 💡 **Dica de Estudo:** Siga as partes em ordem sequencial para uma progressão pedagógica natural do conceito à maestria prática.
