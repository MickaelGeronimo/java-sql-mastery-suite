# 🍕 Capítulo 27: Sobrecarga de Métodos em Java (Overloaded Methods)

> **Módulo Pedagógico:** Trilha de Fundamentos em Java e Arquitetura de Software  
> **Referência:** Bro Code — *Java Full Course for free* (Capítulo 27)

---

## 🧭 Visão Geral

A **Sobrecarga de Métodos (*Method Overloading*)** é uma forma essencial de polimorfismo em tempo de compilação (*static polymorphism*):
> **Definição:** Ocorre quando dois ou mais métodos dentro da mesma classe compartilham o **mesmo nome**, mas possuem **parâmetros diferentes** (tipo, quantidade ou ordem).

Este módulo aborda as regras de assinatura de método (*Method Signature = Nome + Lista de Parâmetros*), o erro de ambiguidade `method already defined`, sobrecargas aritméticas (`add`) e o clássico exemplo culinário da fábrica de pizzas (`bakePizza`).

---

## 🗂️ Estrutura de Aulas e Documentos

| Arquivo | Título / Tema Principal |
| :--- | :--- |
| [01_introducao_e_erro_tamanho.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/01_introducao_e_erro_tamanho.md) | Introdução ao conceito de sobrecarga e erro de aridade na chamada. |
| [02_criando_segundo_metodo_add.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/02_criando_segundo_metodo_add.md) | Criação de assinaturas variadas (`add(a, b)` e `add(a, b, c)`). |
| [03_erro_already_defined_e_soma_4_parametros.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/03_erro_already_defined_e_soma_4_parametros.md) | O erro de colisão de assinaturas idênticas e expansão para 4 parâmetros. |
| [04_exemplo_pizza_uma_cobertura.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/04_exemplo_pizza_uma_cobertura.md) | Modelagem da pizza básica: massa + molho. |
| [05_pizza_com_queijo_mozzarella.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/05_pizza_com_queijo_mozzarella.md) | Sobrecarga com inclusão de queijo (`cheese`). |
| [06_pizza_com_pepperoni.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/06_pizza_com_pepperoni.md) | Sobrecarga completa com pepperoni e coberturas adicionais. |
| [apostila_completa_overloaded_methods_capitulo27.md](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/docs/curriculum/capitulo_27_overloaded_methods/apostila_completa_overloaded_methods_capitulo27.md) | Apostila unificada contendo todos os conceitos, diagramas e código-fonte completo. |

---

## 💻 Código-Fonte e Testes no Projeto
- Classe Didática: [`OverloadedMethodsMasteryDemo.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/main/java/com/mickaelgeronimo/mastery/core/methods/OverloadedMethodsMasteryDemo.java)
- Suíte de Testes Unitários: [`OverloadedMethodsMasteryTest.java`](file:///c:/Users/mickel%201/Downloads/JAVA%20APRENDER/src/test/java/com/mickaelgeronimo/mastery/core/OverloadedMethodsMasteryTest.java)
