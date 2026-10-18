# 🏠 Capítulo 28: Escopo de Variáveis em Java (Variable Scope)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 28)

---

## 🧭 Visão Geral

O escopo de variáveis responde à pergunta crucial de design de software:
> *"Onde no código essa variável foi declarada e quem tem permissão para enxergá-la, modificá-la e utilizá-la?"*

Este módulo organiza de forma progressiva o entendimento entre **Escopo Local (Stack Frames)**, **Escopo de Classe (`static` / Heap Metaspace)** e o fenômeno de **Sombreamento de Variáveis (*Variable Shadowing*)**, culminando em testes automatizados rigorosos e padrões de engenharia corporativa.

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_escopo_local_e_erro_already_defined.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_28_variable_scope/01_escopo_local_e_erro_already_defined.md) | Ciclo de vida na Stack e erro `variable x is already defined in method main`. |
| [02_introducao_local_vs_class.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_28_variable_scope/02_introducao_local_vs_class.md) | A metáfora dos quartos privados vs. a sala de estar da casa. |
| [03_variaveis_de_classe_e_shadowing.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_28_variable_scope/03_variaveis_de_classe_e_shadowing.md) | Declaração fora dos métodos (`static`) e a precedência da variável local sobre a global. |
| [04_main_acessando_variavel_de_classe.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_28_variable_scope/04_main_acessando_variavel_de_classe.md) | Como acessar a variável de classe quando não há sobreposição local. |
| [05_o_grande_teste_local_vs_classe.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_28_variable_scope/05_o_grande_teste_local_vs_classe.md) | A matriz de 4 cenários de teste, sobreposição mútua e acesso explícito via `ClassName.var`. |
| [apostila_completa_variable_scope_capitulo28.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_28_variable_scope/apostila_completa_variable_scope_capitulo28.md) | Apostila unificada completa com todos os diagramas ASCII, tabelas de memória e código-fonte consolidado. |

---

## 🏛️ A Metáfora Arquitetural da Casa 🏠

```text
                  A CLASSE = A CASA INTEIRA (Main)
┌─────────────────────────────────────────────────────────────┐
│ Variável de Classe: static int x = 3;                       │
│ (Está na sala de estar: visível para todos os cômodos!)     │
│                                                             │
│   CÔMODO 1: método main()       CÔMODO 2: doSomething()    │
│  ┌─────────────────────────┐   ┌─────────────────────────┐  │
│  │ int x = 1;              │   │ int x = 2;              │  │
│  │ (Privada deste quarto!) │   │ (Privada deste quarto!) │  │
│  └─────────────────────────┘   └─────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

---

## 💻 Código-Fonte e Testes no Projeto
- Classe Didática de Demonstração: [`VariableScopeDemo.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/methods/VariableScopeDemo.java)
- Suíte de Testes Unitários: [`VariableScopeTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/VariableScopeTest.java)
